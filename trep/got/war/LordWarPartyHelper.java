/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3532
 */
package trep.got.war;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_3532;

public final class LordWarPartyHelper {
    public static final double PARTY_SPEED_CHUNKS_PER_SECOND = 10.0;
    public static final long TICKS_PER_SECOND = 20L;
    public static final int DEFAULT_NEARBY_CHUNK_WINDOW = 4;

    private LordWarPartyHelper() {
    }

    public static int blockToChunk(int blockCoordinate) {
        return Math.floorDiv(blockCoordinate, 16);
    }

    public static int chunkCenterBlock(int chunkCoordinate) {
        return (chunkCoordinate << 4) + 8;
    }

    public static int travelDistanceChunks(int startChunkX, int startChunkZ, int targetChunkX, int targetChunkZ) {
        return Math.max(Math.abs(targetChunkX - startChunkX), Math.abs(targetChunkZ - startChunkZ));
    }

    public static long travelDurationTicks(int startChunkX, int startChunkZ, int targetChunkX, int targetChunkZ) {
        int distanceChunks = LordWarPartyHelper.travelDistanceChunks(startChunkX, startChunkZ, targetChunkX, targetChunkZ);
        if (distanceChunks <= 0) {
            return 1L;
        }
        double seconds = (double)distanceChunks / 10.0;
        return Math.max(1L, Math.round(seconds * 20.0));
    }

    public static double progress(long departureTick, long arrivalTick, long currentTick) {
        if (arrivalTick <= departureTick) {
            return 1.0;
        }
        return class_3532.method_15350((double)((double)(currentTick - departureTick) / (double)(arrivalTick - departureTick)), (double)0.0, (double)1.0);
    }

    public static double currentChunkCoordinate(int startChunk, int targetChunk, long departureTick, long arrivalTick, long currentTick) {
        double progress = LordWarPartyHelper.progress(departureTick, arrivalTick, currentTick);
        return (double)startChunk + (double)(targetChunk - startChunk) * progress;
    }

    public static List<Long> collectChunkKeysInWindow(int centerChunkX, int centerChunkZ, int width, int height) {
        int clampedWidth = Math.max(1, width);
        int clampedHeight = Math.max(1, height);
        int minChunkX = centerChunkX - clampedWidth / 2;
        int minChunkZ = centerChunkZ - clampedHeight / 2;
        ArrayList<Long> keys = new ArrayList<Long>(clampedWidth * clampedHeight);
        for (int chunkX = minChunkX; chunkX < minChunkX + clampedWidth; ++chunkX) {
            for (int chunkZ = minChunkZ; chunkZ < minChunkZ + clampedHeight; ++chunkZ) {
                keys.add(LordWarPartyHelper.chunkKey(chunkX, chunkZ));
            }
        }
        return List.copyOf(keys);
    }

    public static List<Long> collectNearbyChunkKeys(int centerChunkX, int centerChunkZ) {
        return LordWarPartyHelper.collectChunkKeysInWindow(centerChunkX, centerChunkZ, 4, 4);
    }

    public static long chunkKey(int chunkX, int chunkZ) {
        return (long)chunkX << 32 ^ (long)chunkZ & 0xFFFFFFFFL;
    }
}

