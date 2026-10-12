/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3341
 *  net.minecraft.class_3485
 *  net.minecraft.class_3499
 *  net.minecraft.class_3532
 *  net.minecraft.class_5819
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.world.structure.castle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3485;
import net.minecraft.class_3499;
import net.minecraft.class_3532;
import net.minecraft.class_5819;
import org.jetbrains.annotations.Nullable;
import trep.got.GotMod;
import trep.got.castle.CastleSubtype;
import trep.got.castle.CastleTier;
import trep.got.castle.city.CastleCityTierPlan;
import trep.got.castle.city.CastleCityTierRegistry;
import trep.got.castle.city.CastleHarbourPlanRegistry;
import trep.got.castle.city.CastleOutskirtsPlanRegistry;
import trep.got.castle.city.CastleStructureResidentPlan;
import trep.got.registry.GotCastles;
import trep.got.registry.castle.structure.CastleStructureChunkPlanner;
import trep.got.world.structure.castle.CastleWorldgenStructurePlan;
import trep.got.world.structure.castle.DynamicCastleWallPlan;
import trep.got.world.structure.castle.road.CastleRoadBounds;
import trep.got.world.structure.castle.road.CastleRoadNetworkPlan;

public final class CastleWorldgenStructurePlanner {
    private static final int INTERIOR_STRUCTURE_PLOT_PADDING = 3;
    private static final int CLAIM_STRUCTURE_CLEARANCE_HEIGHT = 18;
    private static final int CASTLE_BARN_FORBIDDEN_BUFFER_CHUNKS = 3;
    private static final int CASTLE_BARN_SEARCH_EXTRA_CHUNKS = 4;
    private static final int CASTLE_BARN_GATE_BUFFER_CHUNKS = 2;
    private static final int HARBOUR_STRUCTURE_FORBIDDEN_BUFFER_CHUNKS = 2;
    private static final int NATURE_STRUCTURE_PLOT_SIZE = 16;
    private static final int NATURE_STRUCTURE_WALL_BUFFER_BLOCKS = 8;

    private CastleWorldgenStructurePlanner() {
    }

    public static List<CastleWorldgenStructurePlan> createPlans(class_3218 world, String castleId, @Nullable String kingdomId, CastleTier tier, Set<CastleSubtype> subtypes, class_2338 bannerPos, int floorY, List<class_1923> buildingChunks, DynamicCastleWallPlan wallPlan, @Nullable class_3341 forbiddenStructureReserve) {
        if (world == null || castleId == null || castleId.isBlank() || bannerPos == null || wallPlan == null) {
            return List.of();
        }
        class_3485 templateManager = world.method_14183();
        CastleCityTierPlan tierPlan = CastleCityTierRegistry.forTier(tier);
        class_5819 random = class_5819.method_43049((long)((bannerPos.method_10063() ^ (long)castleId.hashCode()) + 17L + (long)tierPlan.tier().ordinal() * 31L));
        ArrayList<CastleWorldgenStructurePlan> structures = new ArrayList<CastleWorldgenStructurePlan>();
        ArrayList<class_3341> reservedFootprints = new ArrayList<class_3341>();
        if (forbiddenStructureReserve != null) {
            reservedFootprints.add(forbiddenStructureReserve);
        }
        List<CastleStructureResidentPlan> interiorPlans = GotCastles.createCastleStructurePlansForWorldgen(kingdomId, tierPlan.tier(), castleId, bannerPos);
        int chunkIndex = 0;
        block0: for (CastleStructureResidentPlan interiorPlan : interiorPlans) {
            while (chunkIndex < buildingChunks.size()) {
                Optional<CastleWorldgenStructurePlan> structurePlan2;
                if ((structurePlan2 = CastleWorldgenStructurePlanner.planStructureInChunk(templateManager, interiorPlan, buildingChunks.get(chunkIndex++), floorY + 1, random)).isEmpty() || CastleWorldgenStructurePlanner.intersectsAnyXZ(structurePlan2.get().footprint(), reservedFootprints)) continue;
                structures.add(structurePlan2.get());
                reservedFootprints.add(CastleWorldgenStructurePlanner.expandXZ(structurePlan2.get().footprint(), 3));
                continue block0;
            }
        }
        CastleWorldgenStructurePlanner.planBarnStructure(templateManager, castleId, bannerPos, tierPlan, wallPlan, floorY + 1, random).filter(structurePlan -> !CastleWorldgenStructurePlanner.intersectsAnyXZ(structurePlan.footprint(), reservedFootprints)).ifPresent(structurePlan -> {
            structures.add((CastleWorldgenStructurePlan)structurePlan);
            reservedFootprints.add(CastleWorldgenStructurePlanner.expandXZ(structurePlan.footprint(), 3));
        });
        if (subtypes != null && subtypes.contains((Object)CastleSubtype.HARBOUR)) {
            CastleWorldgenStructurePlanner.planHarbourStructure(templateManager, castleId, bannerPos, tierPlan, wallPlan, floorY + 1, random).filter(structurePlan -> !CastleWorldgenStructurePlanner.intersectsAnyXZ(structurePlan.footprint(), reservedFootprints)).ifPresent(structurePlan -> {
                structures.add((CastleWorldgenStructurePlan)structurePlan);
                reservedFootprints.add(CastleWorldgenStructurePlanner.expandXZ(structurePlan.footprint(), 3));
            });
        }
        return List.copyOf(structures);
    }

    public static List<CastleWorldgenStructurePlan> createNaturePlans(class_3218 world, String castleId, CastleTier tier, class_2338 bannerPos, int floorY, DynamicCastleWallPlan wallPlan, List<CastleWorldgenStructurePlan> structures, CastleRoadNetworkPlan roadNetworkPlan, @Nullable class_3341 forbiddenStructureReserve) {
        if (world == null || castleId == null || castleId.isBlank() || bannerPos == null || wallPlan == null) {
            return List.of();
        }
        ArrayList<String> naturePaths = new ArrayList<String>(GotCastles.natureStructurePathsForWorldgen());
        if (naturePaths.isEmpty() || wallPlan.interiorChunkKeys().isEmpty()) {
            return List.of();
        }
        CastleTier resolvedTier = tier == null ? CastleTier.SETTLEMENT : tier;
        class_5819 random = class_5819.method_43049((long)((bannerPos.method_10063() ^ (long)castleId.hashCode()) + 91L + (long)resolvedTier.ordinal() * 43L));
        class_3485 templateManager = world.method_14183();
        ArrayList<class_3341> reservedFootprints = new ArrayList<class_3341>();
        if (forbiddenStructureReserve != null) {
            reservedFootprints.add(forbiddenStructureReserve);
        }
        for (CastleWorldgenStructurePlan structure : structures) {
            reservedFootprints.add(CastleWorldgenStructurePlanner.expandXZ(structure.footprint(), 3));
        }
        reservedFootprints.addAll(CastleWorldgenStructurePlanner.wallReserveFootprints(wallPlan, floorY + 1));
        List<StructurePlot> plots = CastleWorldgenStructurePlanner.naturePlots(wallPlan, reservedFootprints, floorY + 1);
        if (roadNetworkPlan != null) {
            plots.removeIf(plot -> roadNetworkPlan.reserves(new CastleRoadBounds(plot.minX(), plot.minZ(), plot.minX() + plot.width() - 1, plot.minZ() + plot.depth() - 1)));
        }
        if (plots.isEmpty()) {
            return List.of();
        }
        Collections.shuffle(plots, new Random(random.method_43055()));
        Collections.shuffle(naturePaths, new Random(random.method_43055()));
        int targetCount = Math.min(plots.size(), CastleWorldgenStructurePlanner.natureTargetCount(resolvedTier));
        ArrayList<CastleWorldgenStructurePlan> naturePlans = new ArrayList<CastleWorldgenStructurePlan>(targetCount);
        int pathIndex = Math.floorMod(castleId.hashCode(), naturePaths.size());
        int failedAttempts = 0;
        int maxFailedAttempts = Math.max(naturePaths.size(), targetCount * 4);
        while (naturePlans.size() < targetCount && !plots.isEmpty() && failedAttempts < maxFailedAttempts) {
            String structurePath = (String)naturePaths.get(pathIndex);
            pathIndex = (pathIndex + 1) % naturePaths.size();
            Optional<CastleWorldgenStructurePlan> plan = CastleWorldgenStructurePlanner.planDecorativeStructureInAvailablePlot(templateManager, structurePath, plots, floorY + 1, reservedFootprints, random);
            if (plan.isPresent()) {
                CastleWorldgenStructurePlan naturePlan = plan.get();
                naturePlans.add(naturePlan);
                reservedFootprints.add(CastleWorldgenStructurePlanner.expandXZ(naturePlan.footprint(), 3));
                failedAttempts = 0;
                continue;
            }
            ++failedAttempts;
        }
        return List.copyOf(naturePlans);
    }

    private static Optional<CastleWorldgenStructurePlan> planDecorativeStructureInAvailablePlot(class_3485 templateManager, String structurePath, List<StructurePlot> plots, int y, List<class_3341> reservedFootprints, class_5819 random) {
        class_2960 structureId = class_2960.method_60655((String)"agob", (String)structurePath);
        class_3499 template = templateManager.method_15094(structureId).orElse(null);
        if (template == null) {
            GotMod.LOGGER.warn("Missing worldgen castle nature template {}", (Object)structureId);
            return Optional.empty();
        }
        for (int index = 0; index < plots.size(); ++index) {
            StructurePlot plot = plots.get(index);
            Optional<CastleWorldgenStructurePlan> plan = CastleWorldgenStructurePlanner.planStructureInPlot(template, null, structurePath, plot, y, random);
            if (plan.isEmpty() || CastleWorldgenStructurePlanner.intersectsAnyXZ(plan.get().footprint(), reservedFootprints)) continue;
            plots.remove(index);
            return plan;
        }
        return Optional.empty();
    }

    private static int natureTargetCount(CastleTier tier) {
        return switch (tier) {
            default -> throw new MatchException(null, null);
            case CastleTier.SETTLEMENT -> 40;
            case CastleTier.LORDSHIP -> 56;
            case CastleTier.CAPITAL -> 72;
        };
    }

    private static List<StructurePlot> naturePlots(DynamicCastleWallPlan wallPlan, List<class_3341> reservedFootprints, int y) {
        ArrayList<StructurePlot> plots = new ArrayList<StructurePlot>();
        int minX = wallPlan.outerMinX() + 8;
        int maxX = wallPlan.outerMinX() + wallPlan.outerWidth() - 8 - 16;
        int minZ = wallPlan.outerMinZ() + 8;
        int maxZ = wallPlan.outerMinZ() + wallPlan.outerDepth() - 8 - 16;
        for (int x = minX; x <= maxX; x += 16) {
            for (int z = minZ; z <= maxZ; z += 16) {
                class_3341 plotBounds = new class_3341(x, y, z, x + 16 - 1, y + 18, z + 16 - 1);
                if (!CastleWorldgenStructurePlanner.isInsideWallInterior(wallPlan, plotBounds) || CastleWorldgenStructurePlanner.intersectsAnyXZ(plotBounds, reservedFootprints)) continue;
                plots.add(new StructurePlot(x, z, 16, 16));
            }
        }
        return plots;
    }

    private static boolean isInsideWallInterior(DynamicCastleWallPlan wallPlan, class_3341 bounds) {
        int minChunkX = bounds.method_35415() >> 4;
        int maxChunkX = bounds.method_35418() >> 4;
        int minChunkZ = bounds.method_35417() >> 4;
        int maxChunkZ = bounds.method_35420() >> 4;
        for (int chunkX = minChunkX; chunkX <= maxChunkX; ++chunkX) {
            for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; ++chunkZ) {
                if (wallPlan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)chunkZ))) continue;
                return false;
            }
        }
        return true;
    }

    private static List<class_3341> wallReserveFootprints(DynamicCastleWallPlan wallPlan, int y) {
        int minX = wallPlan.outerMinX() - 8;
        int maxX = wallPlan.outerMinX() + wallPlan.outerWidth() + 8;
        int minZ = wallPlan.outerMinZ() - 8;
        int maxZ = wallPlan.outerMinZ() + wallPlan.outerDepth() + 8;
        int innerMinX = wallPlan.outerMinX() + 8;
        int innerMaxX = wallPlan.outerMinX() + wallPlan.outerWidth() - 8;
        int innerMinZ = wallPlan.outerMinZ() + 8;
        int innerMaxZ = wallPlan.outerMinZ() + wallPlan.outerDepth() - 8;
        return List.of(new class_3341(minX, y, minZ, maxX, y + 18, innerMinZ), new class_3341(minX, y, innerMaxZ, maxX, y + 18, maxZ), new class_3341(minX, y, innerMinZ, innerMinX, y + 18, innerMaxZ), new class_3341(innerMaxX, y, innerMinZ, maxX, y + 18, innerMaxZ));
    }

    private static Optional<CastleWorldgenStructurePlan> planBarnStructure(class_3485 templateManager, String castleId, class_2338 bannerPos, CastleCityTierPlan tierPlan, DynamicCastleWallPlan wallPlan, int y, class_5819 random) {
        List<class_1923> candidateChunks = CastleWorldgenStructurePlanner.collectDynamicBarnStructureChunks(bannerPos, wallPlan, tierPlan.claimRadiusChunks());
        class_1923 chunk = CastleWorldgenStructurePlanner.selectNearestShuffledChunk(castleId, bannerPos, candidateChunks, 53);
        return chunk == null ? Optional.empty() : CastleWorldgenStructurePlanner.planStructureInChunk(templateManager, CastleOutskirtsPlanRegistry.barnStructure(), chunk, y, random);
    }

    private static Optional<CastleWorldgenStructurePlan> planHarbourStructure(class_3485 templateManager, String castleId, class_2338 bannerPos, CastleCityTierPlan tierPlan, DynamicCastleWallPlan wallPlan, int y, class_5819 random) {
        List<class_1923> candidateChunks = CastleWorldgenStructurePlanner.collectDynamicHarbourStructureChunks(bannerPos, wallPlan, tierPlan.claimRadiusChunks());
        class_1923 chunk = CastleWorldgenStructurePlanner.selectNearestShuffledChunk(castleId, bannerPos, CastleWorldgenStructurePlanner.collectShorelineChunks(candidateChunks), 31);
        if (chunk == null || CastleHarbourPlanRegistry.structures().isEmpty()) {
            return Optional.empty();
        }
        return CastleWorldgenStructurePlanner.planStructureInChunk(templateManager, CastleHarbourPlanRegistry.structures().getFirst(), chunk, y, random);
    }

    private static Optional<CastleWorldgenStructurePlan> planStructureInChunk(class_3485 templateManager, CastleStructureResidentPlan residentPlan, class_1923 chunk, int y, class_5819 random) {
        class_2960 structureId = class_2960.method_60655((String)"agob", (String)residentPlan.structurePath());
        class_3499 template = templateManager.method_15094(structureId).orElse(null);
        if (template == null) {
            GotMod.LOGGER.warn("Missing worldgen castle structure template {}", (Object)structureId);
            return Optional.empty();
        }
        StructurePlot plot = CastleWorldgenStructurePlanner.dynamicCastleChunkPlot(chunk, template);
        return CastleWorldgenStructurePlanner.planStructureInPlot(template, residentPlan, residentPlan.structurePath(), plot, y, random);
    }

    private static Optional<CastleWorldgenStructurePlan> planStructureInPlot(class_3499 template, @Nullable CastleStructureResidentPlan residentPlan, String structurePath, StructurePlot plot, int y, class_5819 random) {
        ChosenRotation chosen = CastleWorldgenStructurePlanner.chooseRotation(template, plot, random);
        if (chosen == null) {
            return Optional.empty();
        }
        int paddedWidth = Math.max(0, plot.width() - chosen.padding() * 2);
        int paddedDepth = Math.max(0, plot.depth() - chosen.padding() * 2);
        int plotMinX = plot.minX() + chosen.padding();
        int plotMinZ = plot.minZ() + chosen.padding();
        int footprintMinX = plotMinX + Math.max(0, (paddedWidth - chosen.size().method_10263()) / 2);
        int footprintMinZ = plotMinZ + Math.max(0, (paddedDepth - chosen.size().method_10260()) / 2);
        class_2338 footprintOrigin = new class_2338(footprintMinX, y, footprintMinZ);
        class_2338 placementOrigin = new class_2338(footprintMinX - chosen.localBounds().method_35415(), y - chosen.localBounds().method_35416(), footprintMinZ - chosen.localBounds().method_35417());
        class_3341 footprint = new class_3341(footprintMinX, y, footprintMinZ, footprintMinX + chosen.size().method_10263() - 1, y + 18, footprintMinZ + chosen.size().method_10260() - 1);
        class_2338 poi = new class_2338(footprintOrigin.method_10263() + Math.max(0, chosen.size().method_10263() / 2), footprintOrigin.method_10264(), footprintOrigin.method_10260() + Math.max(0, chosen.size().method_10260() / 2));
        return Optional.of(new CastleWorldgenStructurePlan(residentPlan, structurePath, chosen.rotation(), placementOrigin, footprint, chosen.size(), poi, poi));
    }

    private static ChosenRotation chooseRotation(class_3499 template, StructurePlot plot, class_5819 random) {
        ArrayList<class_2470> rotations = new ArrayList<class_2470>(List.of(class_2470.field_11467, class_2470.field_11463, class_2470.field_11464, class_2470.field_11465));
        Collections.shuffle(rotations, new Random(random.method_43055()));
        ChosenRotation chosen = null;
        int bestScore = Integer.MAX_VALUE;
        for (int padding = 3; padding >= 0; --padding) {
            int paddedWidth = Math.max(0, plot.width() - padding * 2);
            int paddedDepth = Math.max(0, plot.depth() - padding * 2);
            for (class_2470 rotation : rotations) {
                int score;
                class_3341 localBounds = template.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
                class_2382 rotatedSize = new class_2382(localBounds.method_35414(), localBounds.method_14660(), localBounds.method_14663());
                if (rotatedSize.method_10263() > paddedWidth || rotatedSize.method_10260() > paddedDepth || (score = padding * 1000 + (paddedWidth - rotatedSize.method_10263()) + (paddedDepth - rotatedSize.method_10260())) >= bestScore) continue;
                bestScore = score;
                chosen = new ChosenRotation(rotation, rotatedSize, localBounds, padding);
            }
        }
        return chosen;
    }

    private static StructurePlot dynamicCastleChunkPlot(class_1923 chunk, class_3499 template) {
        int plotSize = Math.max(24, CastleWorldgenStructurePlanner.dynamicCastlePlotSize(template));
        int centerX = chunk.method_8326() + 8;
        int centerZ = chunk.method_8328() + 8;
        return new StructurePlot(centerX - plotSize / 2, centerZ - plotSize / 2, plotSize, plotSize);
    }

    private static int dynamicCastlePlotSize(class_3499 template) {
        int requiredSize = 0;
        for (class_2470 rotation : List.of(class_2470.field_11467, class_2470.field_11463, class_2470.field_11464, class_2470.field_11465)) {
            class_3341 bounds = template.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
            requiredSize = Math.max(requiredSize, Math.max(bounds.method_35414(), bounds.method_14663()));
        }
        if (requiredSize <= 24) {
            return 24;
        }
        int paddedSize = requiredSize + 6;
        return Math.max(24, class_3532.method_15386((float)((float)paddedSize / 8.0f)) * 8);
    }

    private static List<class_1923> collectDynamicBarnStructureChunks(class_2338 bannerPos, DynamicCastleWallPlan wallPlan, int claimRadiusChunks) {
        int wallMinChunkX = wallPlan.outerMinX() >> 4;
        int wallMaxChunkX = wallPlan.outerMinX() + wallPlan.outerWidth() - 1 >> 4;
        int wallMinChunkZ = wallPlan.outerMinZ() >> 4;
        int wallMaxChunkZ = wallPlan.outerMinZ() + wallPlan.outerDepth() - 1 >> 4;
        int gateChunkX = wallPlan.roadX() >> 4;
        int gateChunkZ = wallPlan.roadZ() >> 4;
        int searchRadius = claimRadiusChunks + 4;
        return CastleStructureChunkPlanner.collectFilteredChunks(bannerPos, searchRadius, chunkPos -> !(GotCastles.isWaterMapChunkForWorldgen(chunkPos) || !CastleStructureChunkPlanner.isAtLeastBufferOutsideArea(chunkPos, wallMinChunkX, wallMaxChunkX, wallMinChunkZ, wallMaxChunkZ, 3) || wallPlan.northSouthGates() && Math.abs(chunkPos.field_9181 - gateChunkX) <= 2 || !wallPlan.northSouthGates() && Math.abs(chunkPos.field_9180 - gateChunkZ) <= 2));
    }

    private static List<class_1923> collectDynamicHarbourStructureChunks(class_2338 bannerPos, DynamicCastleWallPlan wallPlan, int claimRadiusChunks) {
        return CastleStructureChunkPlanner.collectFilteredChunks(bannerPos, claimRadiusChunks, chunkPos -> !GotCastles.isWaterMapChunkForWorldgen(chunkPos) && CastleWorldgenStructurePlanner.isWaterAdjacentLandChunk(chunkPos) && !CastleWorldgenStructurePlanner.isNearWallInterior(chunkPos, wallPlan, 2));
    }

    private static List<class_1923> collectShorelineChunks(List<class_1923> candidateChunks) {
        ArrayList<class_1923> shoreline = new ArrayList<class_1923>();
        for (class_1923 chunk : candidateChunks) {
            if (!CastleWorldgenStructurePlanner.isWaterAdjacentLandChunk(chunk)) continue;
            shoreline.add(chunk);
        }
        return shoreline;
    }

    private static boolean isWaterAdjacentLandChunk(class_1923 chunkPos) {
        if (GotCastles.isWaterMapChunkForWorldgen(chunkPos)) {
            return false;
        }
        for (class_1923 neighbor : List.of(new class_1923(chunkPos.field_9181 + 1, chunkPos.field_9180), new class_1923(chunkPos.field_9181 - 1, chunkPos.field_9180), new class_1923(chunkPos.field_9181, chunkPos.field_9180 + 1), new class_1923(chunkPos.field_9181, chunkPos.field_9180 - 1))) {
            if (!GotCastles.isWaterMapChunkForWorldgen(neighbor)) continue;
            return true;
        }
        return false;
    }

    private static boolean isNearWallInterior(class_1923 chunk, DynamicCastleWallPlan wallPlan, int buffer) {
        for (int dx = -buffer; dx <= buffer; ++dx) {
            for (int dz = -buffer; dz <= buffer; ++dz) {
                if (!wallPlan.interiorChunkKeys().contains(class_1923.method_8331((int)(chunk.field_9181 + dx), (int)(chunk.field_9180 + dz)))) continue;
                return true;
            }
        }
        return false;
    }

    private static class_1923 selectNearestShuffledChunk(String castleId, class_2338 bannerPos, List<class_1923> candidates, int seedSalt) {
        if (candidates.isEmpty()) {
            return null;
        }
        ArrayList<class_1923> shuffledCandidates = new ArrayList<class_1923>(candidates);
        class_5819 random = class_5819.method_43049((long)((bannerPos.method_10063() ^ (long)castleId.hashCode()) + (long)seedSalt));
        Collections.shuffle(shuffledCandidates, new Random(random.method_43055()));
        return shuffledCandidates.stream().min(Comparator.comparingDouble(chunk -> bannerPos.method_40081((double)chunk.method_33940(), (double)bannerPos.method_10264(), (double)chunk.method_33942()))).orElse(null);
    }

    private static boolean intersectsAnyXZ(class_3341 footprint, List<class_3341> reservedFootprints) {
        for (class_3341 reserved : reservedFootprints) {
            if (!CastleWorldgenStructurePlanner.intersectsXZ(footprint, reserved)) continue;
            return true;
        }
        return false;
    }

    private static boolean intersectsXZ(class_3341 first, class_3341 second) {
        return first.method_35415() <= second.method_35418() && first.method_35418() >= second.method_35415() && first.method_35417() <= second.method_35420() && first.method_35420() >= second.method_35417();
    }

    private static class_3341 expandXZ(class_3341 bounds, int radius) {
        int expansion = Math.max(0, radius);
        return new class_3341(bounds.method_35415() - expansion, bounds.method_35416(), bounds.method_35417() - expansion, bounds.method_35418() + expansion, bounds.method_35419(), bounds.method_35420() + expansion);
    }

    private record StructurePlot(int minX, int minZ, int width, int depth) {
    }

    private record ChosenRotation(class_2470 rotation, class_2382 size, class_3341 localBounds, int padding) {
    }
}

