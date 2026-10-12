/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3341
 *  net.minecraft.class_3485
 *  net.minecraft.class_3499
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.world.structure.castle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3485;
import net.minecraft.class_3499;
import org.jetbrains.annotations.Nullable;
import trep.got.castle.CastlePlacementPlan;
import trep.got.castle.CastleSubtype;
import trep.got.castle.CastleTier;
import trep.got.castle.PendingCastleOwnershipWorldState;
import trep.got.castle.WallGateClaim;
import trep.got.registry.GotCastles;
import trep.got.registry.castle.overlay.CastlePresetMetadata;
import trep.got.registry.castle.overlay.OverlayCastleSite;
import trep.got.world.structure.castle.CastleWorldgenPlan;
import trep.got.world.structure.castle.CastleWorldgenStructurePlan;
import trep.got.world.structure.castle.CastleWorldgenStructurePlanner;
import trep.got.world.structure.castle.DynamicCastleCoreWorldgenPlan;
import trep.got.world.structure.castle.DynamicCastleTerrainApplier;
import trep.got.world.structure.castle.DynamicCastleWallPlan;
import trep.got.world.structure.castle.DynamicCastleWallPlanner;
import trep.got.world.structure.castle.DynamicCastleWorldgenFootprint;
import trep.got.world.structure.castle.road.CastleRoadNetworkPlan;
import trep.got.world.structure.castle.road.CastleRoadNetworkPlanner;
import trep.got.world.structure.plan.StructureChunkFootprint;

public final class CastleWorldgenPlanner {
    private static final int WALL_WORLDGEN_HEIGHT = 32;
    private static final int KEEP_CITY_STRUCTURE_CLEARANCE_BLOCKS = 32;
    private static final int WALL_ROAD_WORLDGEN_MARGIN_BLOCKS = 32;

    private CastleWorldgenPlanner() {
    }

    public static Optional<CastleWorldgenPlan> createPlan(class_3218 world, OverlayCastleSite site) {
        if (world == null || site == null) {
            return Optional.empty();
        }
        class_2338 placementAnchor = GotCastles.resolvePlacementAnchor(world, site);
        Optional<CastlePlacementPlan> placementPlan = GotCastles.planPlacement(world, site.definitionId(), placementAnchor, CastleWorldgenPlanner.isHarbourPreferred(site));
        if (placementPlan.isEmpty()) {
            return Optional.empty();
        }
        @Nullable CastlePresetMetadata metadata = GotCastles.getOverlayCastleMetadata(site.castleId());
        CastlePlacementPlan rawPlan = CastleWorldgenPlanner.normalizePlacementPlan(world, placementPlan.get());
        CastleTier effectiveTier = GotCastles.resolveEffectiveCastleTierForWorldgen(metadata, rawPlan.tier());
        Set<CastleSubtype> subtypes = GotCastles.resolveCastleSubtypesForWorldgen(site, metadata);
        String displayName = GotCastles.resolvePendingCastleDisplayName(site);
        String structureKingdomId = metadata == null ? "" : metadata.kingdomId();
        PendingCastleOwnershipWorldState.PendingCastleOwnership pendingOwnership = PendingCastleOwnershipWorldState.get(world).get(site.castleId());
        if (pendingOwnership != null && !pendingOwnership.ownerKingdomId().isBlank()) {
            structureKingdomId = pendingOwnership.ownerKingdomId();
        }
        boolean compactNightsWatchCastle = CastleWorldgenPlanner.isNightsWatchCastle(structureKingdomId);
        DynamicCastleWorldgenFootprint dynamicFootprint = GotCastles.resolveDynamicWorldgenFootprint(world, site.castleId(), rawPlan, effectiveTier).orElseGet(() -> new DynamicCastleWorldgenFootprint(rawPlan.origin(), new class_2382(rawPlan.footprintBlocks(), 12, rawPlan.footprintBlocks()), 28));
        DynamicCastleCoreWorldgenPlan corePlan = GotCastles.resolveDynamicCoreWorldgenPlan(world, site.castleId(), rawPlan, effectiveTier).orElse(null);
        class_2338 wallAnchor = corePlan != null ? corePlan.fallbackBannerPos() : CastleWorldgenPlanner.fallbackWallAnchor(dynamicFootprint, rawPlan);
        @Nullable class_3341 keepStructureBounds = corePlan == null ? null : corePlan.structureBounds(world.method_14183());
        @Nullable class_3341 keepCityStructureReserve = keepStructureBounds == null ? null : CastleWorldgenPlanner.expandXZ(keepStructureBounds, 32);
        boolean northSouthGates = DynamicCastleWallPlanner.shouldUseNorthSouthGates(site.castleId());
        List<class_1923> buildingChunks = DynamicCastleWallPlanner.selectBuildingChunks(wallAnchor, effectiveTier, northSouthGates, keepCityStructureReserve, compactNightsWatchCastle ? 1 : 0);
        DynamicCastleWallPlan wallPlan = DynamicCastleWallPlanner.createWallPlan(wallAnchor, effectiveTier, buildingChunks, northSouthGates, compactNightsWatchCastle ? 1 : 0);
        List<CastleWorldgenStructurePlan> structures = CastleWorldgenStructurePlanner.createPlans(world, site.castleId(), structureKingdomId, effectiveTier, subtypes, wallAnchor, rawPlan.floorY(), buildingChunks, wallPlan, keepCityStructureReserve);
        class_3499 gateTemplate = CastleWorldgenPlanner.resolveTemplate(world.method_14183(), GotCastles.getDynamicGateStructureIdForWorldgen(structureKingdomId), GotCastles.getDynamicStoneGateStructureIdForWorldgen());
        class_3499 wallTemplate = CastleWorldgenPlanner.resolveTemplate(world.method_14183(), GotCastles.getDynamicWallStructureIdForWorldgen(structureKingdomId), GotCastles.getDynamicStoneWallStructureIdForWorldgen());
        class_3499 cornerTemplate = CastleWorldgenPlanner.resolveTemplate(world.method_14183(), GotCastles.getDynamicCornerStructureIdForWorldgen(structureKingdomId), GotCastles.getDynamicStoneCornerStructureIdForWorldgen());
        List<WallGateClaim> wallGateClaims = GotCastles.shouldGenerateDynamicCastleWalls(structureKingdomId) && gateTemplate != null ? CastleWorldgenPlanner.collectWallGateClaims(gateTemplate, wallPlan, rawPlan.floorY() + 1) : List.of();
        CastleRoadNetworkPlan roadNetworkPlan = CastleRoadNetworkPlanner.create(world, site.castleId(), wallAnchor, rawPlan.floorY(), dynamicFootprint, wallPlan, wallGateClaims, corePlan, structures, gateTemplate, wallTemplate, cornerTemplate);
        List<CastleWorldgenStructurePlan> natureStructures = CastleWorldgenStructurePlanner.createNaturePlans(world, site.castleId(), effectiveTier, wallAnchor, rawPlan.floorY(), wallPlan, structures, roadNetworkPlan, keepCityStructureReserve);
        int terrainMergeRadius = DynamicCastleTerrainApplier.terrainMergeRadius(rawPlan.blendRadius());
        class_3341 blockBounds = CastleWorldgenPlanner.mergeBounds(CastleWorldgenPlanner.expandXZ(dynamicFootprint.blockBounds(rawPlan.floorY()), terrainMergeRadius), CastleWorldgenPlanner.expandXZ(wallPlan.blockBounds(rawPlan.floorY() + 1, 32), 32));
        if (keepStructureBounds != null) {
            blockBounds = CastleWorldgenPlanner.mergeBounds(blockBounds, keepStructureBounds);
        }
        for (CastleWorldgenStructurePlan structure : structures) {
            blockBounds = CastleWorldgenPlanner.mergeBounds(blockBounds, structure.footprint());
        }
        for (CastleWorldgenStructurePlan structure : natureStructures) {
            blockBounds = CastleWorldgenPlanner.mergeBounds(blockBounds, structure.footprint());
        }
        StructureChunkFootprint chunkFootprint = StructureChunkFootprint.fromBlockBox(blockBounds);
        return Optional.of(new CastleWorldgenPlan(site.castleId(), site.definitionId(), displayName, site, metadata, rawPlan, effectiveTier, subtypes, structureKingdomId, dynamicFootprint, corePlan, northSouthGates, buildingChunks, wallPlan, structures, roadNetworkPlan, natureStructures, wallGateClaims, blockBounds, chunkFootprint));
    }

    private static boolean isNightsWatchCastle(String kingdomId) {
        return "agob:the_nights_watch".equals(kingdomId);
    }

    @Nullable
    private static class_3499 resolveTemplate(class_3485 templateManager, class_2960 preferred, class_2960 fallback) {
        return templateManager.method_15094(preferred).or(() -> templateManager.method_15094(fallback)).orElse(null);
    }

    private static List<WallGateClaim> collectWallGateClaims(class_3499 gateTemplate, DynamicCastleWallPlan plan, int y) {
        ArrayList<WallGateClaim> claims = new ArrayList<WallGateClaim>();
        TreeMap<Integer, List<Integer>> firstEdges = new TreeMap<Integer, List<Integer>>();
        TreeMap<Integer, List<Integer>> secondEdges = new TreeMap<Integer, List<Integer>>();
        for (long chunkKey : plan.interiorChunkKeys()) {
            int chunkX = class_1923.method_8325((long)chunkKey);
            int chunkZ = class_1923.method_8332((long)chunkKey);
            if (plan.northSouthGates()) {
                if (!plan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)(chunkZ - 1)))) {
                    firstEdges.computeIfAbsent(chunkZ, ignored -> new ArrayList()).add(chunkX);
                }
                if (plan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)(chunkZ + 1)))) continue;
                secondEdges.computeIfAbsent(chunkZ, ignored -> new ArrayList()).add(chunkX);
                continue;
            }
            if (!plan.interiorChunkKeys().contains(class_1923.method_8331((int)(chunkX - 1), (int)chunkZ))) {
                firstEdges.computeIfAbsent(chunkX, ignored -> new ArrayList()).add(chunkZ);
            }
            if (plan.interiorChunkKeys().contains(class_1923.method_8331((int)(chunkX + 1), (int)chunkZ))) continue;
            secondEdges.computeIfAbsent(chunkX, ignored -> new ArrayList()).add(chunkZ);
        }
        if (plan.northSouthGates()) {
            CastleWorldgenPlanner.collectHorizontalGateClaims(claims, gateTemplate, plan, firstEdges, y, class_2350.field_11043);
            CastleWorldgenPlanner.collectHorizontalGateClaims(claims, gateTemplate, plan, secondEdges, y, class_2350.field_11035);
        } else {
            CastleWorldgenPlanner.collectVerticalGateClaims(claims, gateTemplate, plan, firstEdges, y, class_2350.field_11039);
            CastleWorldgenPlanner.collectVerticalGateClaims(claims, gateTemplate, plan, secondEdges, y, class_2350.field_11034);
        }
        return List.copyOf(claims);
    }

    private static void collectHorizontalGateClaims(List<WallGateClaim> claims, class_3499 gateTemplate, DynamicCastleWallPlan plan, Map<Integer, List<Integer>> edges, int y, class_2350 side) {
        int roadChunk = Math.floorDiv(plan.roadX(), 16);
        class_2470 rotation = side == class_2350.field_11043 ? class_2470.field_11467 : class_2470.field_11464;
        class_3341 bounds = gateTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        for (Map.Entry<Integer, List<Integer>> entry : edges.entrySet()) {
            List chunks = entry.getValue().stream().sorted().toList();
            int index = 0;
            while (index < chunks.size()) {
                int runStart;
                int runEnd = runStart = ((Integer)chunks.get(index)).intValue();
                while (++index < chunks.size() && (Integer)chunks.get(index) == runEnd + 1) {
                    runEnd = (Integer)chunks.get(index);
                }
                if (roadChunk < runStart || roadChunk > runEnd) continue;
                int runMin = runStart << 4;
                int runMax = runEnd + 1 << 4;
                int minX = Math.max(runMin, Math.min(plan.roadX() - bounds.method_35414() / 2, runMax - bounds.method_35414()));
                int minZ = side == class_2350.field_11043 ? entry.getKey() << 4 : (entry.getKey() + 1 << 4) - bounds.method_14663();
                CastleWorldgenPlanner.addGateClaimAtMin(claims, gateTemplate, rotation, minX, y, minZ);
            }
        }
    }

    private static void collectVerticalGateClaims(List<WallGateClaim> claims, class_3499 gateTemplate, DynamicCastleWallPlan plan, Map<Integer, List<Integer>> edges, int y, class_2350 side) {
        int roadChunk = Math.floorDiv(plan.roadZ(), 16);
        class_2470 rotation = side == class_2350.field_11039 ? class_2470.field_11465 : class_2470.field_11463;
        class_3341 bounds = gateTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        for (Map.Entry<Integer, List<Integer>> entry : edges.entrySet()) {
            List chunks = entry.getValue().stream().sorted().toList();
            int index = 0;
            while (index < chunks.size()) {
                int runStart;
                int runEnd = runStart = ((Integer)chunks.get(index)).intValue();
                while (++index < chunks.size() && (Integer)chunks.get(index) == runEnd + 1) {
                    runEnd = (Integer)chunks.get(index);
                }
                if (roadChunk < runStart || roadChunk > runEnd) continue;
                int runMin = runStart << 4;
                int runMax = runEnd + 1 << 4;
                int minX = side == class_2350.field_11039 ? entry.getKey() << 4 : (entry.getKey() + 1 << 4) - bounds.method_35414();
                int minZ = Math.max(runMin, Math.min(plan.roadZ() - bounds.method_14663() / 2, runMax - bounds.method_14663()));
                CastleWorldgenPlanner.addGateClaimAtMin(claims, gateTemplate, rotation, minX, y, minZ);
            }
        }
    }

    private static void addGateClaimAtMin(List<WallGateClaim> claims, class_3499 gateTemplate, class_2470 rotation, int minX, int y, int minZ) {
        class_3341 localBounds = gateTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        class_2382 size = new class_2382(localBounds.method_35414(), localBounds.method_14660(), localBounds.method_14663());
        class_2338 origin = new class_2338(minX - localBounds.method_35415(), y - localBounds.method_35416(), minZ - localBounds.method_35417());
        class_3341 footprint = new class_3341(minX, y, minZ, minX + size.method_10263() - 1, y + size.method_10264() - 1, minZ + size.method_10260() - 1);
        class_2338 pointOfInterest = new class_2338(minX + size.method_10263() / 2, y, minZ + size.method_10260() / 2);
        claims.add(new WallGateClaim(footprint, origin, size, pointOfInterest));
    }

    private static CastlePlacementPlan normalizePlacementPlan(class_3218 world, CastlePlacementPlan plan) {
        if (plan.floorY() > world.method_31607() + 4) {
            return plan;
        }
        int floorY = world.method_8615();
        return new CastlePlacementPlan(plan.castleId(), plan.tier(), plan.templateKey(), new class_2338(plan.origin().method_10263(), floorY, plan.origin().method_10260()), floorY, floorY, floorY, plan.footprintChunks(), plan.footprintBlocks(), plan.blendRadius());
    }

    private static class_2338 fallbackWallAnchor(DynamicCastleWorldgenFootprint footprint, CastlePlacementPlan plan) {
        return new class_2338(footprint.origin().method_10263() + footprint.size().method_10263() / 2, plan.floorY() + 1, footprint.origin().method_10260() + footprint.size().method_10260() / 2);
    }

    private static boolean isHarbourPreferred(OverlayCastleSite site) {
        if (site == null) {
            return false;
        }
        CastlePresetMetadata metadata = GotCastles.getOverlayCastleMetadata(site.castleId());
        Set<CastleSubtype> subtypes = GotCastles.resolveCastleSubtypesForWorldgen(site, metadata);
        return subtypes.contains((Object)CastleSubtype.HARBOUR);
    }

    private static class_3341 mergeBounds(class_3341 first, class_3341 second) {
        return new class_3341(Math.min(first.method_35415(), second.method_35415()), Math.min(first.method_35416(), second.method_35416()), Math.min(first.method_35417(), second.method_35417()), Math.max(first.method_35418(), second.method_35418()), Math.max(first.method_35419(), second.method_35419()), Math.max(first.method_35420(), second.method_35420()));
    }

    private static class_3341 expandXZ(class_3341 bounds, int radius) {
        int expansion = Math.max(0, radius);
        return new class_3341(bounds.method_35415() - expansion, bounds.method_35416(), bounds.method_35417() - expansion, bounds.method_35418() + expansion, bounds.method_35419(), bounds.method_35420() + expansion);
    }
}

