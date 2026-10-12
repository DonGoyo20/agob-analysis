/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_243
 *  net.minecraft.class_265
 *  net.minecraft.class_3532
 */
package trep.got.entity;

import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_3532;

final class CartGroundSupport {
    private CartGroundSupport() {
    }

    static double sampleGroundHeight(class_1937 world, double cartY, class_243 samplePos) {
        int blockX = class_3532.method_15357((double)samplePos.field_1352);
        int blockZ = class_3532.method_15357((double)samplePos.field_1350);
        int startY = class_3532.method_15357((double)cartY) + 3;
        int minY = world.method_31607();
        class_2338.class_2339 pos = new class_2338.class_2339(blockX, startY, blockZ);
        for (int y = startY; y >= minY; --y) {
            pos.method_10103(blockX, y, blockZ);
            class_265 shape = world.method_8320((class_2338)pos).method_26220((class_1922)world, (class_2338)pos);
            if (shape.method_1110()) continue;
            return (double)y + shape.method_1105(class_2350.class_2351.field_11052);
        }
        return minY;
    }
}

