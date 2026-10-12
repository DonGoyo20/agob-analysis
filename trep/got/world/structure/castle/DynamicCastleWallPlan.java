/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_3341
 */
package trep.got.world.structure.castle;

import java.util.Set;
import net.minecraft.class_1923;
import net.minecraft.class_3341;
import trep.got.world.structure.plan.StructureChunkFootprint;

public record DynamicCastleWallPlan(int outerMinX, int outerMinZ, int outerWidth, int outerDepth, boolean northSouthGates, int roadX, int roadZ, Set<Long> interiorChunkKeys) {
    public StructureChunkFootprint chunkFootprint() {
        int minChunkX = this.outerMinX >> 4;
        int minChunkZ = this.outerMinZ >> 4;
        int maxChunkX = this.outerMinX + this.outerWidth - 1 >> 4;
        int maxChunkZ = this.outerMinZ + this.outerDepth - 1 >> 4;
        return new StructureChunkFootprint(minChunkX, minChunkZ, maxChunkX, maxChunkZ);
    }

    public class_3341 blockBounds(int y, int height) {
        return new class_3341(this.outerMinX, y, this.outerMinZ, this.outerMinX + this.outerWidth - 1, y + Math.max(1, height) - 1, this.outerMinZ + this.outerDepth - 1);
    }

    public boolean intersectsChunk(class_1923 chunkPos) {
        return this.chunkFootprint().contains(chunkPos);
    }
}

