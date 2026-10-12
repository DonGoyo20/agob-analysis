/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_1935
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2302
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2758
 *  net.minecraft.class_2769
 *  net.minecraft.class_4970$class_2251
 */
package trep.got.block;

import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2302;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_4970;
import trep.got.registry.GotItems;

public final class HopCropBlock
extends class_2302 {
    public static final class_2758 AGE = class_2758.method_11867((String)"age", (int)0, (int)5);

    public HopCropBlock(class_4970.class_2251 settings) {
        super(settings);
    }

    protected class_1935 method_9832() {
        return GotItems.HOP_SEEDS;
    }

    public class_2758 method_9824() {
        return AGE;
    }

    public int method_9827() {
        return 5;
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{AGE});
    }

    protected boolean method_9695(class_2680 floor, class_1922 world, class_2338 pos) {
        return floor.method_27852(class_2246.field_10362) || floor.method_27852(class_2246.field_10219) || floor.method_27852(class_2246.field_10194);
    }
}

