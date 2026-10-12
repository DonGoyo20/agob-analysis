/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3233
 *  net.minecraft.class_3341
 *  net.minecraft.class_3485
 *  net.minecraft.class_3491
 *  net.minecraft.class_3492
 *  net.minecraft.class_3499
 *  net.minecraft.class_3532
 *  net.minecraft.class_3793
 *  net.minecraft.class_5281
 *  net.minecraft.class_5425
 *  net.minecraft.class_5819
 */
package trep.got.world.structure.castle;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3233;
import net.minecraft.class_3341;
import net.minecraft.class_3485;
import net.minecraft.class_3491;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_3532;
import net.minecraft.class_3793;
import net.minecraft.class_5281;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import trep.got.registry.GotCastles;
import trep.got.world.structure.castle.CastleWorldgenPlan;
import trep.got.world.structure.castle.CastleWorldgenStructureApplier;
import trep.got.world.structure.castle.DynamicCastleWallPlan;
import trep.got.world.structure.castle.road.CastleRoadNetworkApplier;

public final class DynamicCastleWallApplier {
    private static final int CASTLE_GENERATION_BLOCK_FLAGS = 48;

    private DynamicCastleWallApplier() {
    }

    public static void applyFull(class_3218 world, CastleWorldgenPlan plan) {
        String kingdomId = plan.structureKingdomId();
        if (GotCastles.shouldGenerateDynamicCastleWalls(kingdomId)) {
            DynamicCastleWallApplier.applyWalls((class_5281)world, null, plan.wallPlan(), plan.origin().method_10264() + 1, world.method_14183(), kingdomId);
        }
        CastleRoadNetworkApplier.applyFull(world, plan.roadNetworkPlan(), plan.anchor());
        CastleWorldgenStructureApplier.applyNatureFull(world, plan);
    }

    public static void applyChunk(class_3233 region, class_1923 chunkPos, CastleWorldgenPlan plan) {
        String kingdomId = plan.structureKingdomId();
        if (GotCastles.shouldGenerateDynamicCastleWalls(kingdomId)) {
            DynamicCastleWallApplier.applyWalls((class_5281)region, chunkPos, plan.wallPlan(), plan.origin().method_10264() + 1, region.method_8410().method_14183(), kingdomId);
        }
        CastleRoadNetworkApplier.applyChunk(region, chunkPos, plan.roadNetworkPlan(), plan.anchor());
        CastleWorldgenStructureApplier.applyNatureChunk(region, chunkPos, plan);
    }

    public static void applyWallChunk(class_3218 world, class_1923 chunkPos, CastleWorldgenPlan plan) {
        if (world == null || chunkPos == null || plan == null) {
            return;
        }
        String kingdomId = plan.structureKingdomId();
        if (GotCastles.shouldGenerateDynamicCastleWalls(kingdomId)) {
            DynamicCastleWallApplier.applyWalls((class_5281)world, chunkPos, plan.wallPlan(), plan.origin().method_10264() + 1, world.method_14183(), kingdomId);
        }
    }

    private static void applyWalls(class_5281 world, class_1923 clipChunk, DynamicCastleWallPlan plan, int y, class_3485 templateManager) {
        DynamicCastleWallApplier.applyWalls(world, clipChunk, plan, y, templateManager, null);
    }

    private static void applyWalls(class_5281 world, class_1923 clipChunk, DynamicCastleWallPlan plan, int y, class_3485 templateManager, String kingdomId) {
        class_3499 wallTemplate = DynamicCastleWallApplier.loadWallTemplate(templateManager, GotCastles.getDynamicWallStructureIdForWorldgen(kingdomId), GotCastles.getDynamicStoneWallStructureIdForWorldgen());
        class_3499 cornerTemplate = DynamicCastleWallApplier.loadWallTemplate(templateManager, GotCastles.getDynamicCornerStructureIdForWorldgen(kingdomId), GotCastles.getDynamicStoneCornerStructureIdForWorldgen());
        class_3499 gateTemplate = DynamicCastleWallApplier.loadWallTemplate(templateManager, GotCastles.getDynamicGateStructureIdForWorldgen(kingdomId), GotCastles.getDynamicStoneGateStructureIdForWorldgen());
        if (wallTemplate == null || cornerTemplate == null || gateTemplate == null) {
            return;
        }
        HashMap<Integer, List<Integer>> northEdges = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, List<Integer>> southEdges = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, List<Integer>> westEdges = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, List<Integer>> eastEdges = new HashMap<Integer, List<Integer>>();
        for (long chunkKey : plan.interiorChunkKeys()) {
            int chunkX = class_1923.method_8325((long)chunkKey);
            int chunkZ = class_1923.method_8332((long)chunkKey);
            if (!plan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)(chunkZ - 1)))) {
                DynamicCastleWallApplier.addPerimeterEdge(northEdges, chunkZ, chunkX);
            }
            if (!plan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)(chunkZ + 1)))) {
                DynamicCastleWallApplier.addPerimeterEdge(southEdges, chunkZ, chunkX);
            }
            if (!plan.interiorChunkKeys().contains(class_1923.method_8331((int)(chunkX - 1), (int)chunkZ))) {
                DynamicCastleWallApplier.addPerimeterEdge(westEdges, chunkX, chunkZ);
            }
            if (plan.interiorChunkKeys().contains(class_1923.method_8331((int)(chunkX + 1), (int)chunkZ))) continue;
            DynamicCastleWallApplier.addPerimeterEdge(eastEdges, chunkX, chunkZ);
        }
        DynamicCastleWallApplier.placeHorizontalEdgeRuns(world, clipChunk, wallTemplate, gateTemplate, plan, northEdges, y, class_2350.field_11043);
        DynamicCastleWallApplier.placeHorizontalEdgeRuns(world, clipChunk, wallTemplate, gateTemplate, plan, southEdges, y, class_2350.field_11035);
        DynamicCastleWallApplier.placeVerticalEdgeRuns(world, clipChunk, wallTemplate, gateTemplate, plan, westEdges, y, class_2350.field_11039);
        DynamicCastleWallApplier.placeVerticalEdgeRuns(world, clipChunk, wallTemplate, gateTemplate, plan, eastEdges, y, class_2350.field_11034);
        DynamicCastleWallApplier.placePerimeterCorners(world, clipChunk, cornerTemplate, plan, y);
    }

    private static class_3499 loadWallTemplate(class_3485 templateManager, class_2960 preferred, class_2960 fallback) {
        class_3499 template = templateManager.method_15094(preferred).orElse(null);
        return template != null || preferred.equals((Object)fallback) ? template : (class_3499)templateManager.method_15094(fallback).orElse(null);
    }

    private static void addPerimeterEdge(Map<Integer, List<Integer>> edges, int fixedChunk, int runChunk) {
        edges.computeIfAbsent(fixedChunk, ignored -> new ArrayList()).add(runChunk);
    }

    private static void placeHorizontalEdgeRuns(class_5281 world, class_1923 clipChunk, class_3499 wallTemplate, class_3499 gateTemplate, DynamicCastleWallPlan plan, Map<Integer, List<Integer>> edges, int y, class_2350 side) {
        class_2470 rotation = DynamicCastleWallApplier.wallRotationForSide(side);
        class_3341 wallBounds = wallTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        class_3341 gateBounds = gateTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        for (Map.Entry<Integer, List<Integer>> entry : edges.entrySet()) {
            int runStart;
            List<Integer> chunks = entry.getValue();
            Collections.sort(chunks);
            int previous = runStart = chunks.getFirst().intValue();
            for (int index = 1; index <= chunks.size(); ++index) {
                if (index < chunks.size() && chunks.get(index) == previous + 1) {
                    previous = chunks.get(index);
                    continue;
                }
                DynamicCastleWallApplier.placeHorizontalEdgeRun(world, clipChunk, wallTemplate, gateTemplate, plan, entry.getKey(), runStart, previous, y, side, rotation, wallBounds, gateBounds);
                if (index >= chunks.size()) continue;
                previous = runStart = chunks.get(index).intValue();
            }
        }
    }

    private static void placeHorizontalEdgeRun(class_5281 world, class_1923 clipChunk, class_3499 wallTemplate, class_3499 gateTemplate, DynamicCastleWallPlan plan, int chunkZ, int startChunkX, int endChunkX, int y, class_2350 side, class_2470 rotation, class_3341 wallBounds, class_3341 gateBounds) {
        boolean hasGate;
        int runMinX = startChunkX << 4;
        int runMaxX = endChunkX + 1 << 4;
        int wallZ = side == class_2350.field_11043 ? chunkZ << 4 : (chunkZ + 1 << 4) - wallBounds.method_14663();
        boolean bl = hasGate = plan.northSouthGates() && startChunkX <= plan.roadX() >> 4 && endChunkX >= plan.roadX() >> 4;
        if (!hasGate) {
            DynamicCastleWallApplier.placeStraightRunX(world, clipChunk, wallTemplate, rotation, runMinX, runMaxX, y, wallZ, wallBounds.method_35414());
            return;
        }
        int gateWidth = gateBounds.method_35414();
        int gateMinX = class_3532.method_15340((int)(plan.roadX() - gateWidth / 2), (int)runMinX, (int)Math.max(runMinX, runMaxX - gateWidth));
        int gateZ = side == class_2350.field_11043 ? chunkZ << 4 : (chunkZ + 1 << 4) - gateBounds.method_14663();
        DynamicCastleWallApplier.placeStraightRunX(world, clipChunk, wallTemplate, rotation, runMinX, gateMinX, y, wallZ, wallBounds.method_35414());
        DynamicCastleWallApplier.placeStructureAtMin(world, clipChunk, gateTemplate, rotation, gateMinX, y, gateZ);
        DynamicCastleWallApplier.placeStraightRunX(world, clipChunk, wallTemplate, rotation, gateMinX + gateWidth, runMaxX, y, wallZ, wallBounds.method_35414());
    }

    private static void placeVerticalEdgeRuns(class_5281 world, class_1923 clipChunk, class_3499 wallTemplate, class_3499 gateTemplate, DynamicCastleWallPlan plan, Map<Integer, List<Integer>> edges, int y, class_2350 side) {
        class_2470 rotation = DynamicCastleWallApplier.wallRotationForSide(side);
        class_3341 wallBounds = wallTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        class_3341 gateBounds = gateTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        for (Map.Entry<Integer, List<Integer>> entry : edges.entrySet()) {
            int runStart;
            List<Integer> chunks = entry.getValue();
            Collections.sort(chunks);
            int previous = runStart = chunks.getFirst().intValue();
            for (int index = 1; index <= chunks.size(); ++index) {
                if (index < chunks.size() && chunks.get(index) == previous + 1) {
                    previous = chunks.get(index);
                    continue;
                }
                DynamicCastleWallApplier.placeVerticalEdgeRun(world, clipChunk, wallTemplate, gateTemplate, plan, entry.getKey(), runStart, previous, y, side, rotation, wallBounds, gateBounds);
                if (index >= chunks.size()) continue;
                previous = runStart = chunks.get(index).intValue();
            }
        }
    }

    private static void placeVerticalEdgeRun(class_5281 world, class_1923 clipChunk, class_3499 wallTemplate, class_3499 gateTemplate, DynamicCastleWallPlan plan, int chunkX, int startChunkZ, int endChunkZ, int y, class_2350 side, class_2470 rotation, class_3341 wallBounds, class_3341 gateBounds) {
        boolean hasGate;
        int runMinZ = startChunkZ << 4;
        int runMaxZ = endChunkZ + 1 << 4;
        int wallX = side == class_2350.field_11039 ? chunkX << 4 : (chunkX + 1 << 4) - wallBounds.method_35414();
        boolean bl = hasGate = !plan.northSouthGates() && startChunkZ <= plan.roadZ() >> 4 && endChunkZ >= plan.roadZ() >> 4;
        if (!hasGate) {
            DynamicCastleWallApplier.placeStraightRunZ(world, clipChunk, wallTemplate, rotation, wallX, y, runMinZ, runMaxZ, wallBounds.method_14663());
            return;
        }
        int gateDepth = gateBounds.method_14663();
        int gateMinZ = class_3532.method_15340((int)(plan.roadZ() - gateDepth / 2), (int)runMinZ, (int)Math.max(runMinZ, runMaxZ - gateDepth));
        int gateX = side == class_2350.field_11039 ? chunkX << 4 : (chunkX + 1 << 4) - gateBounds.method_35414();
        DynamicCastleWallApplier.placeStraightRunZ(world, clipChunk, wallTemplate, rotation, wallX, y, runMinZ, gateMinZ, wallBounds.method_14663());
        DynamicCastleWallApplier.placeStructureAtMin(world, clipChunk, gateTemplate, rotation, gateX, y, gateMinZ);
        DynamicCastleWallApplier.placeStraightRunZ(world, clipChunk, wallTemplate, rotation, wallX, y, gateMinZ + gateDepth, runMaxZ, wallBounds.method_14663());
    }

    private static void placeStraightRunX(class_5281 world, class_1923 clipChunk, class_3499 template, class_2470 rotation, int minX, int maxXExclusive, int y, int z, int pieceLength) {
        if (maxXExclusive <= minX || pieceLength <= 0) {
            return;
        }
        int previousX = Integer.MIN_VALUE;
        int x = minX;
        while (x + pieceLength <= maxXExclusive) {
            DynamicCastleWallApplier.placeStructureAtMin(world, clipChunk, template, rotation, x, y, z);
            previousX = x;
            x += pieceLength;
        }
        if (previousX + pieceLength < maxXExclusive && (x = Math.max(minX, maxXExclusive - pieceLength)) != previousX) {
            DynamicCastleWallApplier.placeStructureAtMin(world, clipChunk, template, rotation, x, y, z);
        }
    }

    private static void placeStraightRunZ(class_5281 world, class_1923 clipChunk, class_3499 template, class_2470 rotation, int x, int y, int minZ, int maxZExclusive, int pieceLength) {
        if (maxZExclusive <= minZ || pieceLength <= 0) {
            return;
        }
        int previousZ = Integer.MIN_VALUE;
        int z = minZ;
        while (z + pieceLength <= maxZExclusive) {
            DynamicCastleWallApplier.placeStructureAtMin(world, clipChunk, template, rotation, x, y, z);
            previousZ = z;
            z += pieceLength;
        }
        if (previousZ + pieceLength < maxZExclusive && (z = Math.max(minZ, maxZExclusive - pieceLength)) != previousZ) {
            DynamicCastleWallApplier.placeStructureAtMin(world, clipChunk, template, rotation, x, y, z);
        }
    }

    private static void placePerimeterCorners(class_5281 world, class_1923 clipChunk, class_3499 cornerTemplate, DynamicCastleWallPlan plan, int y) {
        LinkedHashSet<CallSite> placedCorners = new LinkedHashSet<CallSite>();
        for (long chunkKey : plan.interiorChunkKeys()) {
            int chunkX = class_1923.method_8325((long)chunkKey);
            int chunkZ = class_1923.method_8332((long)chunkKey);
            for (int vertexX = chunkX; vertexX <= chunkX + 1; ++vertexX) {
                for (int vertexZ = chunkZ; vertexZ <= chunkZ + 1; ++vertexZ) {
                    class_2470 rotation;
                    String vertexKey = vertexX + "," + vertexZ;
                    if (!placedCorners.add((CallSite)((Object)vertexKey)) || (rotation = DynamicCastleWallApplier.dynamicCornerRotation(plan.interiorChunkKeys(), vertexX, vertexZ)) == null) continue;
                    class_3341 bounds = cornerTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
                    int x = (vertexX << 4) - bounds.method_35414() / 2;
                    int z = (vertexZ << 4) - bounds.method_14663() / 2;
                    DynamicCastleWallApplier.placeStructureAtMin(world, clipChunk, cornerTemplate, rotation, x, y, z);
                }
            }
        }
    }

    private static boolean placeStructureAtMin(class_5281 world, class_1923 clipChunk, class_3499 template, class_2470 rotation, int minX, int y, int minZ) {
        class_3341 localBounds = template.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        class_2338 origin = new class_2338(minX - localBounds.method_35415(), y - localBounds.method_35416(), minZ - localBounds.method_35417());
        class_3341 absoluteBounds = template.method_27267(origin, rotation, class_2338.field_10980, class_2415.field_11302);
        if (clipChunk != null && !DynamicCastleWallApplier.intersectsChunk(absoluteBounds, clipChunk)) {
            return false;
        }
        if (clipChunk == null && !DynamicCastleWallApplier.canPlaceBounds(world, absoluteBounds)) {
            return false;
        }
        class_3492 placement = new class_3492().method_15133(false).method_15131(false).method_27264(false).method_15123(rotation).method_16184((class_3491)class_3793.field_16721);
        if (clipChunk != null) {
            placement.method_15126(new class_3341(clipChunk.method_8326(), world.method_31607(), clipChunk.method_8328(), clipChunk.method_8327(), world.method_31600(), clipChunk.method_8329()));
        }
        return template.method_15172((class_5425)world, origin, origin, placement, class_5819.method_43049((long)origin.method_10063()), 48);
    }

    private static boolean canPlaceBounds(class_5281 world, class_3341 bounds) {
        if (world instanceof class_3233) {
            class_3233 region = (class_3233)world;
            return bounds.method_35416() < region.method_31600() && bounds.method_35419() >= region.method_31607();
        }
        return true;
    }

    private static boolean intersectsChunk(class_3341 bounds, class_1923 chunkPos) {
        return bounds.method_35415() <= chunkPos.method_8327() && bounds.method_35418() >= chunkPos.method_8326() && bounds.method_35417() <= chunkPos.method_8329() && bounds.method_35420() >= chunkPos.method_8328();
    }

    private static class_2470 wallRotationForSide(class_2350 side) {
        return switch (side) {
            case class_2350.field_11043 -> class_2470.field_11467;
            case class_2350.field_11035 -> class_2470.field_11464;
            case class_2350.field_11039 -> class_2470.field_11465;
            case class_2350.field_11034 -> class_2470.field_11463;
            default -> class_2470.field_11467;
        };
    }

    private static class_2470 dynamicCornerRotation(Set<Long> interiorChunks, int vertexX, int vertexZ) {
        boolean southeast;
        boolean southwest;
        boolean northeast;
        boolean northwest = interiorChunks.contains(class_1923.method_8331((int)(vertexX - 1), (int)(vertexZ - 1)));
        int filled = (northwest ? 1 : 0) + ((northeast = interiorChunks.contains(class_1923.method_8331((int)vertexX, (int)(vertexZ - 1)))) ? 1 : 0) + ((southwest = interiorChunks.contains(class_1923.method_8331((int)(vertexX - 1), (int)vertexZ))) ? 1 : 0) + ((southeast = interiorChunks.contains(class_1923.method_8331((int)vertexX, (int)vertexZ))) ? 1 : 0);
        if (filled == 0 || filled == 2 || filled == 4) {
            return null;
        }
        if (southeast && filled == 1 || !southeast && filled == 3) {
            return class_2470.field_11467;
        }
        if (southwest && filled == 1 || !southwest && filled == 3) {
            return class_2470.field_11463;
        }
        if (northwest && filled == 1 || !northwest && filled == 3) {
            return class_2470.field_11464;
        }
        return class_2470.field_11465;
    }
}

