/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 */
package trep.got.registry.castle.structure;

import java.util.Set;
import net.minecraft.class_1923;
import trep.got.registry.castle.structure.ChunkExclusion;

public record ChunkSetArea(Set<Long> chunkKeys) implements ChunkExclusion
{
    @Override
    public boolean contains(class_1923 chunkPos) {
        return this.chunkKeys.contains(class_1923.method_8331((int)chunkPos.field_9181, (int)chunkPos.field_9180));
    }
}

