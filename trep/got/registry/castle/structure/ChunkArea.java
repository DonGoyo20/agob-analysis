/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 */
package trep.got.registry.castle.structure;

import net.minecraft.class_1923;
import trep.got.registry.castle.structure.ChunkExclusion;

public record ChunkArea(int minChunkX, int minChunkZ, int maxChunkX, int maxChunkZ) implements ChunkExclusion
{
    @Override
    public boolean contains(class_1923 chunkPos) {
        return chunkPos.field_9181 >= this.minChunkX && chunkPos.field_9181 <= this.maxChunkX && chunkPos.field_9180 >= this.minChunkZ && chunkPos.field_9180 <= this.maxChunkZ;
    }
}

