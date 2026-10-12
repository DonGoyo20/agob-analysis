/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_5819
 */
package trep.got.registry.castle.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_5819;
import trep.got.registry.castle.structure.ChunkArea;

public final class CastleStructureChunkPlanner {
    private CastleStructureChunkPlanner() {
    }

    public static ChunkArea chunkAreaFor(class_2338 origin, class_2382 size) {
        return new ChunkArea(origin.method_10263() >> 4, origin.method_10260() >> 4, origin.method_10263() + size.method_10263() - 1 >> 4, origin.method_10260() + size.method_10260() - 1 >> 4);
    }

    public static List<class_1923> collectClaimStructureChunks(class_2338 bannerPos, class_2338 castleOrigin, class_2382 castleSize, int claimRadiusChunks, Predicate<class_1923> isWaterChunk) {
        int centerChunkX = bannerPos.method_10263() >> 4;
        int centerChunkZ = bannerPos.method_10260() >> 4;
        int castleMinChunkX = castleOrigin.method_10263() >> 4;
        int castleMinChunkZ = castleOrigin.method_10260() >> 4;
        int castleMaxChunkX = castleOrigin.method_10263() + castleSize.method_10263() - 1 >> 4;
        int castleMaxChunkZ = castleOrigin.method_10260() + castleSize.method_10260() - 1 >> 4;
        int castleCenterChunkX = (castleMinChunkX + castleMaxChunkX) / 2;
        int castleCenterChunkZ = (castleMinChunkZ + castleMaxChunkZ) / 2;
        LinkedHashSet<Long> reservedChunks = new LinkedHashSet<Long>();
        reservedChunks.add(class_1923.method_8331((int)castleCenterChunkX, (int)(castleMinChunkZ - 1)));
        reservedChunks.add(class_1923.method_8331((int)castleCenterChunkX, (int)(castleMaxChunkZ + 1)));
        reservedChunks.add(class_1923.method_8331((int)(castleMinChunkX - 1), (int)castleCenterChunkZ));
        reservedChunks.add(class_1923.method_8331((int)(castleMaxChunkX + 1), (int)castleCenterChunkZ));
        ArrayList<class_1923> candidates = new ArrayList<class_1923>();
        CastleStructureChunkPlanner.forEachChunk(centerChunkX, centerChunkZ, claimRadiusChunks, chunkPos -> {
            if (chunkPos.field_9181 >= castleMinChunkX && chunkPos.field_9181 <= castleMaxChunkX && chunkPos.field_9180 >= castleMinChunkZ && chunkPos.field_9180 <= castleMaxChunkZ) {
                return false;
            }
            if (isWaterChunk.test((class_1923)chunkPos)) {
                return false;
            }
            return !reservedChunks.contains(class_1923.method_8331((int)chunkPos.field_9181, (int)chunkPos.field_9180));
        }, candidates);
        return candidates;
    }

    public static List<class_1923> collectFilteredChunks(class_2338 bannerPos, int radiusChunks, Predicate<class_1923> predicate) {
        ArrayList<class_1923> candidates = new ArrayList<class_1923>();
        CastleStructureChunkPlanner.forEachChunk(bannerPos.method_10263() >> 4, bannerPos.method_10260() >> 4, radiusChunks, predicate, candidates);
        return candidates;
    }

    public static List<class_1923> collectShorelineChunks(List<class_1923> candidateChunks, Predicate<class_1923> isWaterAdjacentLandChunk) {
        ArrayList<class_1923> shorelineChunks = new ArrayList<class_1923>();
        for (class_1923 chunkPos : candidateChunks) {
            if (!isWaterAdjacentLandChunk.test(chunkPos)) continue;
            shorelineChunks.add(chunkPos);
        }
        return shorelineChunks;
    }

    public static class_1923 selectNearestShuffledChunk(String castleId, class_2338 bannerPos, List<class_1923> candidates, int seedSalt, Predicate<class_1923> allowed) {
        if (candidates.isEmpty()) {
            return null;
        }
        ArrayList<class_1923> shuffledCandidates = new ArrayList<class_1923>(candidates);
        class_5819 random = class_5819.method_43049((long)((bannerPos.method_10063() ^ (long)castleId.hashCode()) + (long)seedSalt));
        Collections.shuffle(shuffledCandidates, new Random(random.method_43055()));
        shuffledCandidates.sort(Comparator.comparingDouble(chunk -> CastleStructureChunkPlanner.squaredDistanceToChunkCenter(chunk, bannerPos)));
        for (class_1923 chunkPos : shuffledCandidates) {
            if (!allowed.test(chunkPos)) continue;
            return chunkPos;
        }
        return null;
    }

    public static boolean isAtLeastBufferOutsideArea(class_1923 chunkPos, int minChunkX, int maxChunkX, int minChunkZ, int maxChunkZ, int bufferChunks) {
        return chunkPos.field_9181 <= minChunkX - bufferChunks || chunkPos.field_9181 >= maxChunkX + bufferChunks || chunkPos.field_9180 <= minChunkZ - bufferChunks || chunkPos.field_9180 >= maxChunkZ + bufferChunks;
    }

    private static void forEachChunk(int centerChunkX, int centerChunkZ, int radiusChunks, Predicate<class_1923> predicate, List<class_1923> output) {
        for (int chunkX = centerChunkX - radiusChunks; chunkX <= centerChunkX + radiusChunks; ++chunkX) {
            for (int chunkZ = centerChunkZ - radiusChunks; chunkZ <= centerChunkZ + radiusChunks; ++chunkZ) {
                class_1923 chunkPos = new class_1923(chunkX, chunkZ);
                if (!predicate.test(chunkPos)) continue;
                output.add(chunkPos);
            }
        }
    }

    private static double squaredDistanceToChunkCenter(class_1923 chunkPos, class_2338 pos) {
        double centerX = chunkPos.method_33940();
        double centerZ = chunkPos.method_33942();
        return pos.method_40081(centerX, (double)pos.method_10264(), centerZ);
    }
}

