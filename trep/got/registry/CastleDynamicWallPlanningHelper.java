/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_3341
 *  net.minecraft.class_3532
 *  net.minecraft.class_5819
 */
package trep.got.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_3341;
import net.minecraft.class_3532;
import net.minecraft.class_5819;
import trep.got.castle.city.CastleCityTierPlan;
import trep.got.registry.CastleStructurePlacementHelper;

final class CastleDynamicWallPlanningHelper {
    private CastleDynamicWallPlanningHelper() {
    }

    static List<class_1923> selectBuildingChunks(class_2338 bannerPos, CastleCityTierPlan tierPlan, boolean northSouthGates, class_3341 coreReservedFootprint, class_5819 random, int maxOffsetReductionChunks) {
        int centerChunkX = bannerPos.method_10263() >> 4;
        int centerChunkZ = bannerPos.method_10260() >> 4;
        int desiredCount = tierPlan.totalInteriorStructures();
        ArrayList<class_1923> candidates = new ArrayList<class_1923>();
        int maxOffset = Math.max(1, Math.max(2, tierPlan.claimRadiusChunks() - 1) - Math.max(0, maxOffsetReductionChunks));
        for (int offsetX = -maxOffset; offsetX <= maxOffset; ++offsetX) {
            for (int offsetZ = -maxOffset; offsetZ <= maxOffset; ++offsetZ) {
                class_1923 candidate = new class_1923(centerChunkX + offsetX, centerChunkZ + offsetZ);
                if (CastleStructurePlacementHelper.chunkIntersectsBlockBox(candidate, coreReservedFootprint) || northSouthGates && offsetX == 0 || !northSouthGates && offsetZ == 0) continue;
                candidates.add(candidate);
            }
        }
        Collections.shuffle(candidates, new Random(random.method_43055()));
        candidates.sort(Comparator.comparingInt(chunk -> Math.abs(chunk.field_9181 - centerChunkX) + Math.abs(chunk.field_9180 - centerChunkZ)));
        ArrayList<class_1923> selected = new ArrayList<class_1923>();
        for (class_1923 candidate : candidates) {
            if (CastleDynamicWallPlanningHelper.sharesSideWithAny(candidate, selected)) continue;
            selected.add(candidate);
            if (selected.size() < desiredCount) continue;
            return selected;
        }
        for (class_1923 candidate : candidates) {
            if (!selected.contains(candidate)) {
                selected.add(candidate);
            }
            if (selected.size() < desiredCount) continue;
            break;
        }
        return selected;
    }

    static boolean sharesSideWithAny(class_1923 candidate, List<class_1923> selected) {
        for (class_1923 existing : selected) {
            if (Math.abs(candidate.field_9181 - existing.field_9181) + Math.abs(candidate.field_9180 - existing.field_9180) != 1) continue;
            return true;
        }
        return false;
    }

    static WallPlan createWallPlan(class_2338 bannerPos, CastleCityTierPlan tierPlan, List<class_1923> structureChunks, boolean northSouthGates, int gateRadiusReductionChunks, int wallMarginBlocks, int wallSmoothRadius) {
        int centerChunkX = bannerPos.method_10263() >> 4;
        int centerChunkZ = bannerPos.method_10260() >> 4;
        int gateRadiusChunks = Math.max(1, tierPlan.claimRadiusChunks() - Math.max(0, gateRadiusReductionChunks));
        LinkedHashSet<Long> anchorChunks = new LinkedHashSet<Long>();
        for (int offsetX = -1; offsetX <= 1; ++offsetX) {
            for (int offsetZ = -1; offsetZ <= 1; ++offsetZ) {
                anchorChunks.add(class_1923.method_8331((int)(centerChunkX + offsetX), (int)(centerChunkZ + offsetZ)));
            }
        }
        for (class_1923 chunk : structureChunks) {
            for (int offsetX = -1; offsetX <= 1; ++offsetX) {
                for (int offsetZ = -1; offsetZ <= 1; ++offsetZ) {
                    anchorChunks.add(class_1923.method_8331((int)(chunk.field_9181 + offsetX), (int)(chunk.field_9180 + offsetZ)));
                }
            }
        }
        ChunkBounds bounds = CastleDynamicWallPlanningHelper.chunkBounds(anchorChunks, centerChunkX, centerChunkZ);
        if (northSouthGates) {
            anchorChunks.add(class_1923.method_8331((int)centerChunkX, (int)Math.max(centerChunkZ - gateRadiusChunks, bounds.minZ() - 1)));
            anchorChunks.add(class_1923.method_8331((int)centerChunkX, (int)Math.min(centerChunkZ + gateRadiusChunks, bounds.maxZ() + 1)));
        } else {
            anchorChunks.add(class_1923.method_8331((int)Math.max(centerChunkX - gateRadiusChunks, bounds.minX() - 1), (int)centerChunkZ));
            anchorChunks.add(class_1923.method_8331((int)Math.min(centerChunkX + gateRadiusChunks, bounds.maxX() + 1), (int)centerChunkZ));
        }
        Set<Long> interiorChunks = CastleDynamicWallPlanningHelper.createSmoothedWallInterior(anchorChunks, centerChunkX, centerChunkZ, northSouthGates, wallSmoothRadius);
        bounds = CastleDynamicWallPlanningHelper.chunkBounds(interiorChunks, centerChunkX, centerChunkZ);
        int outerMinX = (bounds.minX() << 4) - wallMarginBlocks;
        int outerMinZ = (bounds.minZ() << 4) - wallMarginBlocks;
        int outerWidth = (bounds.maxX() - bounds.minX() + 1 << 4) + wallMarginBlocks * 2;
        int outerDepth = (bounds.maxZ() - bounds.minZ() + 1 << 4) + wallMarginBlocks * 2;
        return new WallPlan(outerMinX, outerMinZ, outerWidth, outerDepth, northSouthGates, (centerChunkX << 4) + 8, (centerChunkZ << 4) + 8, Set.copyOf(interiorChunks));
    }

    private static Set<Long> createSmoothedWallInterior(Set<Long> anchorChunks, int centerChunkX, int centerChunkZ, boolean northSouthGates, int wallSmoothRadius) {
        ChunkBounds bounds = CastleDynamicWallPlanningHelper.chunkBounds(anchorChunks, centerChunkX, centerChunkZ);
        LinkedHashSet<Long> smoothedChunks = new LinkedHashSet<Long>();
        if (northSouthGates) {
            int rowCount = bounds.maxZ() - bounds.minZ() + 1;
            int[] minByRow = new int[rowCount];
            int[] maxByRow = new int[rowCount];
            for (int index = 0; index < rowCount; ++index) {
                int chunkZ = bounds.minZ() + index;
                int minX = Integer.MAX_VALUE;
                int maxX = Integer.MIN_VALUE;
                for (long anchorKey : anchorChunks) {
                    int anchorZ = class_1923.method_8332((long)anchorKey);
                    int distance = Math.abs(anchorZ - chunkZ);
                    if (distance > wallSmoothRadius) continue;
                    int anchorX = class_1923.method_8325((long)anchorKey);
                    minX = Math.min(minX, anchorX);
                    maxX = Math.max(maxX, anchorX);
                }
                minByRow[index] = minX == Integer.MAX_VALUE ? centerChunkX : Math.min(minX, centerChunkX);
                maxByRow[index] = maxX == Integer.MIN_VALUE ? centerChunkX : Math.max(maxX, centerChunkX);
            }
            int[] rawMinByRow = (int[])minByRow.clone();
            int[] rawMaxByRow = (int[])maxByRow.clone();
            CastleDynamicWallPlanningHelper.smoothWallSpans(minByRow, maxByRow);
            CastleDynamicWallPlanningHelper.includeRawWallSpans(minByRow, maxByRow, rawMinByRow, rawMaxByRow);
            for (int index = 0; index < rowCount; ++index) {
                int chunkZ = bounds.minZ() + index;
                for (int chunkX = minByRow[index]; chunkX <= maxByRow[index]; ++chunkX) {
                    smoothedChunks.add(class_1923.method_8331((int)chunkX, (int)chunkZ));
                }
            }
            return smoothedChunks;
        }
        int columnCount = bounds.maxX() - bounds.minX() + 1;
        int[] minByColumn = new int[columnCount];
        int[] maxByColumn = new int[columnCount];
        for (int index = 0; index < columnCount; ++index) {
            int chunkX = bounds.minX() + index;
            int minZ = Integer.MAX_VALUE;
            int maxZ = Integer.MIN_VALUE;
            for (long anchorKey : anchorChunks) {
                int anchorX = class_1923.method_8325((long)anchorKey);
                int distance = Math.abs(anchorX - chunkX);
                if (distance > wallSmoothRadius) continue;
                int anchorZ = class_1923.method_8332((long)anchorKey);
                minZ = Math.min(minZ, anchorZ);
                maxZ = Math.max(maxZ, anchorZ);
            }
            minByColumn[index] = minZ == Integer.MAX_VALUE ? centerChunkZ : Math.min(minZ, centerChunkZ);
            maxByColumn[index] = maxZ == Integer.MIN_VALUE ? centerChunkZ : Math.max(maxZ, centerChunkZ);
        }
        int[] rawMinByColumn = (int[])minByColumn.clone();
        int[] rawMaxByColumn = (int[])maxByColumn.clone();
        CastleDynamicWallPlanningHelper.smoothWallSpans(minByColumn, maxByColumn);
        CastleDynamicWallPlanningHelper.includeRawWallSpans(minByColumn, maxByColumn, rawMinByColumn, rawMaxByColumn);
        for (int index = 0; index < columnCount; ++index) {
            int chunkX = bounds.minX() + index;
            for (int chunkZ = minByColumn[index]; chunkZ <= maxByColumn[index]; ++chunkZ) {
                smoothedChunks.add(class_1923.method_8331((int)chunkX, (int)chunkZ));
            }
        }
        return smoothedChunks;
    }

    private static void smoothWallSpans(int[] mins, int[] maxes) {
        int midpoint;
        int index;
        for (index = 1; index < mins.length; ++index) {
            mins[index] = class_3532.method_15340((int)mins[index], (int)(mins[index - 1] - 1), (int)(mins[index - 1] + 1));
            maxes[index] = class_3532.method_15340((int)maxes[index], (int)(maxes[index - 1] - 1), (int)(maxes[index - 1] + 1));
            if (mins[index] <= maxes[index]) continue;
            mins[index] = midpoint = (mins[index] + maxes[index]) / 2;
            maxes[index] = midpoint;
        }
        for (index = mins.length - 2; index >= 0; --index) {
            mins[index] = class_3532.method_15340((int)mins[index], (int)(mins[index + 1] - 1), (int)(mins[index + 1] + 1));
            maxes[index] = class_3532.method_15340((int)maxes[index], (int)(maxes[index + 1] - 1), (int)(maxes[index + 1] + 1));
            if (mins[index] <= maxes[index]) continue;
            mins[index] = midpoint = (mins[index] + maxes[index]) / 2;
            maxes[index] = midpoint;
        }
    }

    private static void includeRawWallSpans(int[] mins, int[] maxes, int[] rawMins, int[] rawMaxes) {
        for (int index = 0; index < mins.length; ++index) {
            mins[index] = Math.min(mins[index], rawMins[index]);
            maxes[index] = Math.max(maxes[index], rawMaxes[index]);
        }
    }

    private static ChunkBounds chunkBounds(Set<Long> chunks, int fallbackX, int fallbackZ) {
        int minX = fallbackX;
        int maxX = fallbackX;
        int minZ = fallbackZ;
        int maxZ = fallbackZ;
        for (long chunkKey : chunks) {
            int chunkX = class_1923.method_8325((long)chunkKey);
            int chunkZ = class_1923.method_8332((long)chunkKey);
            minX = Math.min(minX, chunkX);
            maxX = Math.max(maxX, chunkX);
            minZ = Math.min(minZ, chunkZ);
            maxZ = Math.max(maxZ, chunkZ);
        }
        return new ChunkBounds(minX, maxX, minZ, maxZ);
    }

    private record ChunkBounds(int minX, int maxX, int minZ, int maxZ) {
    }

    record WallPlan(int outerMinX, int outerMinZ, int outerWidth, int outerDepth, boolean northSouthGates, int roadX, int roadZ, Set<Long> interiorChunkKeys) {
    }
}

