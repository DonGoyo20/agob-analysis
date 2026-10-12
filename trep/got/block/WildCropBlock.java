/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
 *  net.minecraft.class_2261
 *  net.minecraft.class_2338
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_3481
 *  net.minecraft.class_3726
 *  net.minecraft.class_4970$class_2251
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2261;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3481;
import net.minecraft.class_3726;
import net.minecraft.class_4970;

public final class WildCropBlock
extends class_2261 {
    public static final MapCodec<WildCropBlock> CODEC = WildCropBlock.method_54094(WildCropBlock::new);
    private static final class_265 SHAPE = WildCropBlock.method_9541((double)1.0, (double)0.0, (double)1.0, (double)15.0, (double)14.0, (double)15.0);

    public WildCropBlock(class_4970.class_2251 settings) {
        super(settings);
    }

    public MapCodec<WildCropBlock> method_53969() {
        return CODEC;
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return SHAPE;
    }

    protected boolean method_9695(class_2680 floor, class_1922 world, class_2338 pos) {
        return floor.method_27852(class_2246.field_10219) || floor.method_27852(class_2246.field_10566) || floor.method_27852(class_2246.field_10253) || floor.method_27852(class_2246.field_28685) || floor.method_27852(class_2246.field_10520) || floor.method_26164(class_3481.field_29822);
    }
}

