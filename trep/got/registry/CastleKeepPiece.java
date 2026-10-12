/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2382
 *  net.minecraft.class_2960
 *  net.minecraft.class_3499
 */
package trep.got.registry;

import net.minecraft.class_2382;
import net.minecraft.class_2960;
import net.minecraft.class_3499;

record CastleKeepPiece(int partIndex, class_2960 structureId, class_3499 template, class_2382 offset) {
    CastleKeepPiece withOffset(class_2382 offset) {
        return new CastleKeepPiece(this.partIndex, this.structureId, this.template, offset);
    }
}

