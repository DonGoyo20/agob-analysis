/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_3341
 */
package trep.got.world.structure.plan;

import net.minecraft.class_1923;
import net.minecraft.class_3341;

public record StructureChunkFootprint(int minChunkX, int minChunkZ, int maxChunkX, int maxChunkZ) {
    public static StructureChunkFootprint fromBlockBox(class_3341 bounds) {
        return new StructureChunkFootprint(bounds.method_35415() >> 4, bounds.method_35417() >> 4, bounds.method_35418() >> 4, bounds.method_35420() >> 4);
    }

    public boolean contains(class_1923 chunkPos) {
        if (chunkPos == null) {
            return false;
        }
        return chunkPos.field_9181 >= this.minChunkX && chunkPos.field_9181 <= this.maxChunkX && chunkPos.field_9180 >= this.minChunkZ && chunkPos.field_9180 <= this.maxChunkZ;
    }

    public boolean intersectsChunkWindow(int minX, int minZ, int maxX, int maxZ) {
        return this.maxChunkX >= minX && this.minChunkX <= maxX && this.maxChunkZ >= minZ && this.minChunkZ <= maxZ;
    }
}

