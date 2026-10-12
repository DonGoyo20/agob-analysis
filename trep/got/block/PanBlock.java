/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_2237
 *  net.minecraft.class_2248
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  net.minecraft.class_4970$class_2251
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_4970;
import trep.got.block.KitchenwareBlock;

public final class PanBlock
extends KitchenwareBlock {
    public static final MapCodec<PanBlock> CODEC = PanBlock.method_54094(PanBlock::new);
    private static final class_265 SHAPE = class_259.method_1084((class_265)class_2248.method_9541((double)1.0, (double)0.0, (double)1.0, (double)15.0, (double)5.0, (double)15.0), (class_265)class_2248.method_9541((double)5.0, (double)3.0, (double)-9.0, (double)11.0, (double)5.0, (double)1.0));

    public PanBlock(class_4970.class_2251 settings) {
        super(settings, 4, SHAPE);
    }

    protected MapCodec<? extends class_2237> method_53969() {
        return CODEC;
    }
}

