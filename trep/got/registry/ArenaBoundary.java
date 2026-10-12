/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_3341
 */
package trep.got.registry;

import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3341;

record ArenaBoundary(String castleId, String id, class_3341 bounds, class_238 box) {
    boolean contains(class_243 pos) {
        return this.box.method_1006(pos);
    }
}

