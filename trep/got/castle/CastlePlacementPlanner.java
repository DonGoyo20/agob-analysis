/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1959
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2794
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_3218
 *  net.minecraft.class_6880
 */
package trep.got.castle;

import java.util.Arrays;
import java.util.Optional;
import net.minecraft.class_1959;
import net.minecraft.class_2338;
import net.minecraft.class_2794;
import net.minecraft.class_2902;
import net.minecraft.class_3218;
import net.minecraft.class_6880;
import trep.got.castle.CastleDefinition;
import trep.got.castle.CastlePlacementPlan;
import trep.got.castle.CastlePlacementRules;
import trep.got.castle.CastleTemplateKey;
import trep.got.castle.CastleTemplateResolver;
import trep.got.world.gen.MapChunkGenerator;

public final class CastlePlacementPlanner {
    private static final double RELAXED_MAX_WATER_RATIO = 0.45;

    private CastlePlacementPlanner() {
    }

    public static Optional<CastlePlacementPlan> createPlan(class_3218 world, CastleDefinition definition, class_2338 anchor) {
        return CastlePlacementPlanner.createPlan(world, definition, anchor, false);
    }

    public static Optional<CastlePlacementPlan> createPlan(class_3218 world, CastleDefinition definition, class_2338 anchor, boolean harbourPreferred) {
        Optional<CandidatePlan> anchorPlan;
        CastlePlacementRules rules = definition.placementRules();
        int searchStep = Math.max(8, rules.surfaceSampleStep() * 2);
        SurfaceYSampler surfaceYSampler = CastlePlacementPlanner.surfaceYSampler(world);
        CandidatePlan bestStrictPlan = null;
        int bestStrictScore = Integer.MAX_VALUE;
        CandidatePlan bestRelaxedPlan = null;
        int bestRelaxedScore = Integer.MAX_VALUE;
        if (harbourPreferred && (anchorPlan = CastlePlacementPlanner.tryCreatePlanAt(world, definition, anchor, true, surfaceYSampler)).isPresent() && (anchorPlan.get().strictlyValid() || anchorPlan.get().relaxedValid())) {
            return Optional.of(anchorPlan.get().plan());
        }
        for (int offsetX = -rules.scanRadiusBlocks(); offsetX <= rules.scanRadiusBlocks(); offsetX += searchStep) {
            for (int offsetZ = -rules.scanRadiusBlocks(); offsetZ <= rules.scanRadiusBlocks(); offsetZ += searchStep) {
                class_2338 candidate = anchor.method_10069(offsetX, 0, offsetZ);
                Optional<CandidatePlan> optionalPlan = CastlePlacementPlanner.tryCreatePlanAt(world, definition, candidate, harbourPreferred, surfaceYSampler);
                if (optionalPlan.isEmpty()) continue;
                CandidatePlan candidatePlan = optionalPlan.get();
                int score = candidatePlan.terrainDelta() * 100 + candidatePlan.waterHits() * 25 + Math.abs(offsetX) + Math.abs(offsetZ);
                if (candidatePlan.strictlyValid()) {
                    if (bestStrictPlan != null && score >= bestStrictScore) continue;
                    bestStrictPlan = candidatePlan;
                    bestStrictScore = score;
                    continue;
                }
                if (!candidatePlan.relaxedValid() || bestRelaxedPlan != null && score >= bestRelaxedScore) continue;
                bestRelaxedPlan = candidatePlan;
                bestRelaxedScore = score;
            }
        }
        if (bestStrictPlan != null) {
            return Optional.of(bestStrictPlan.plan());
        }
        return bestRelaxedPlan != null ? Optional.of(bestRelaxedPlan.plan()) : Optional.empty();
    }

    private static Optional<CandidatePlan> tryCreatePlanAt(class_3218 world, CastleDefinition definition, class_2338 anchor, boolean harbourPreferred, SurfaceYSampler surfaceYSampler) {
        boolean relaxedValid;
        class_6880 biome = world.method_23753(anchor);
        String biomePath = biome.method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        if (!definition.allowsBiome(biomePath)) {
            return Optional.empty();
        }
        CastlePlacementRules rules = definition.placementRules();
        int footprintBlocks = rules.footprintChunks() * 16;
        int half = footprintBlocks / 2;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int waterHits = 0;
        int axisSamples = CastlePlacementPlanner.sampleAxisCount(footprintBlocks, rules.surfaceSampleStep());
        int[] sampledHeights = new int[axisSamples * axisSamples];
        int sampleCount = 0;
        class_2338.class_2339 samplePos = new class_2338.class_2339();
        for (int x = anchor.method_10263() - half; x <= anchor.method_10263() + half; x += rules.surfaceSampleStep()) {
            for (int z = anchor.method_10260() - half; z <= anchor.method_10260() + half; z += rules.surfaceSampleStep()) {
                int surfaceY = CastlePlacementPlanner.sampleLooseSurfaceY(surfaceYSampler, x, z);
                minY = Math.min(minY, surfaceY);
                maxY = Math.max(maxY, surfaceY);
                sampledHeights[sampleCount++] = surfaceY;
                if (surfaceY <= world.method_31607() || !CastlePlacementPlanner.sampleHasFluid(world, (class_2338)samplePos.method_10103(x, surfaceY, z), surfaceY)) continue;
                ++waterHits;
            }
        }
        int terrainDelta = maxY - minY;
        boolean strictlyValid = terrainDelta <= rules.maxTerrainDelta() && waterHits <= rules.maxWaterSamples();
        boolean inlandRelaxedValid = (double)waterHits < Math.max(1.0, Math.ceil((double)sampleCount * 0.45)) && terrainDelta <= Math.max(rules.maxTerrainDelta() * 4, 40) && maxY > world.method_8615() - 2;
        boolean harbourRelaxedValid = harbourPreferred && terrainDelta <= Math.max(rules.maxTerrainDelta() * 6, 72) && maxY > world.method_31607() + 1;
        boolean bl = relaxedValid = inlandRelaxedValid || harbourRelaxedValid;
        if (!strictlyValid && !relaxedValid) {
            return Optional.empty();
        }
        int floorY = maxY <= world.method_31607() + 1 ? world.method_8615() : CastlePlacementPlanner.resolveFloorY(sampledHeights, sampleCount, minY, maxY);
        class_2338 origin = new class_2338(anchor.method_10263() - half, floorY, anchor.method_10260() - half);
        CastleTemplateKey templateKey = CastleTemplateResolver.resolve(definition, (class_6880<class_1959>)biome);
        CastlePlacementPlan plan = new CastlePlacementPlan(definition.id(), definition.tier(), templateKey, origin, floorY, minY, maxY, rules.footprintChunks(), footprintBlocks, rules.blendRadius());
        return Optional.of(new CandidatePlan(plan, terrainDelta, waterHits, strictlyValid, relaxedValid));
    }

    private static SurfaceYSampler surfaceYSampler(class_3218 world) {
        class_2794 class_27942 = world.method_14178().method_12129();
        if (class_27942 instanceof MapChunkGenerator) {
            MapChunkGenerator mapGenerator = (MapChunkGenerator)class_27942;
            MapChunkGenerator.MotionBlockingSurfaceSampler sampler = mapGenerator.motionBlockingSurfaceSampler();
            return sampler::sample;
        }
        return (x, z) -> world.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
    }

    private static int sampleLooseSurfaceY(SurfaceYSampler sampler, int x, int z) {
        return sampler.sample(x, z);
    }

    private static boolean sampleHasFluid(class_3218 world, class_2338 pos, int surfaceY) {
        if (world.method_14178().method_12129() instanceof MapChunkGenerator) {
            return surfaceY <= world.method_8615();
        }
        return !world.method_8316(pos).method_15769();
    }

    private static int resolveFloorY(int[] sampledHeights, int sampleCount, int minY, int maxY) {
        if (sampleCount <= 0) {
            return (minY + maxY) / 2;
        }
        Arrays.sort(sampledHeights, 0, sampleCount);
        int median = CastlePlacementPlanner.percentile(sampledHeights, sampleCount, 0.5);
        int upperQuartile = CastlePlacementPlanner.percentile(sampledHeights, sampleCount, 0.75);
        int floorY = Math.round((float)(median + upperQuartile) / 2.0f);
        return Math.max(median, Math.min(floorY, maxY));
    }

    private static int percentile(int[] sortedValues, int size, double percentile) {
        int index = (int)Math.round((double)(size - 1) * percentile);
        index = Math.max(0, Math.min(index, size - 1));
        return sortedValues[index];
    }

    private static int sampleAxisCount(int footprintBlocks, int sampleStep) {
        return Math.max(1, footprintBlocks / sampleStep + 1);
    }

    @FunctionalInterface
    private static interface SurfaceYSampler {
        public int sample(int var1, int var2);
    }

    private record CandidatePlan(CastlePlacementPlan plan, int terrainDelta, int waterHits, boolean strictlyValid, boolean relaxedValid) {
    }
}

