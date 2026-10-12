/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_3218
 */
package trep.got.world.structure.index;

import java.util.List;
import net.minecraft.class_1923;
import net.minecraft.class_3218;
import trep.got.world.structure.cache.CastlePlanCache;
import trep.got.world.structure.castle.CastleWorldgenPlan;

public final class CastleChunkIndex {
    private CastleChunkIndex() {
    }

    public static List<CastleWorldgenPlan> plansIntersectingChunk(class_3218 world, class_1923 chunkPos) {
        if (world == null || chunkPos == null) {
            return List.of();
        }
        return CastlePlanCache.getIntersectingChunk(world, chunkPos);
    }

    public static boolean intersectsChunkWindow(class_3218 world, int minChunkX, int minChunkZ, int maxChunkX, int maxChunkZ) {
        if (world == null) {
            return false;
        }
        return !CastlePlanCache.getIntersectingChunkWindow(world, minChunkX, minChunkZ, maxChunkX, maxChunkZ).isEmpty();
    }
}

