/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1264
 *  net.minecraft.class_1269
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2237
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2586
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_3965
 *  net.minecraft.class_4174
 *  net.minecraft.class_4970$class_2251
 *  net.minecraft.class_9334
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1264;
import net.minecraft.class_1269;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3965;
import net.minecraft.class_4174;
import net.minecraft.class_4970;
import net.minecraft.class_9334;
import trep.got.block.KitchenwareBlock;
import trep.got.block.entity.KitchenwareBlockEntity;

public final class PlateBlock
extends KitchenwareBlock {
    public static final MapCodec<PlateBlock> CODEC = PlateBlock.method_54094(PlateBlock::new);
    private static final class_265 SHAPE = class_259.method_17786((class_265)class_2248.method_9541((double)1.0, (double)0.0, (double)1.0, (double)15.0, (double)1.0, (double)15.0), (class_265[])new class_265[]{class_2248.method_9541((double)0.0, (double)1.0, (double)0.0, (double)16.0, (double)2.0, (double)4.0), class_2248.method_9541((double)0.0, (double)1.0, (double)12.0, (double)16.0, (double)2.0, (double)16.0), class_2248.method_9541((double)0.0, (double)1.0, (double)4.0, (double)4.0, (double)2.0, (double)12.0), class_2248.method_9541((double)12.0, (double)1.0, (double)4.0, (double)16.0, (double)2.0, (double)12.0)});

    public PlateBlock(class_4970.class_2251 settings) {
        super(settings, 1, SHAPE);
    }

    @Override
    protected class_1269 method_55766(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit) {
        class_1799 eaten;
        class_1799 remainder;
        KitchenwareBlockEntity kitchenware;
        if (player.method_5715()) {
            return super.method_55766(state, world, pos, player, hit);
        }
        class_2586 class_25862 = world.method_8321(pos);
        if (!(class_25862 instanceof KitchenwareBlockEntity) || (kitchenware = (KitchenwareBlockEntity)class_25862).method_5442()) {
            return class_1269.field_5811;
        }
        class_1799 stack = kitchenware.method_5438(0);
        class_4174 food = (class_4174)stack.method_57824(class_9334.field_50075);
        if (food == null) {
            return class_1269.field_5811;
        }
        if (!player.method_7332(food.comp_2493())) {
            return class_1269.field_5812;
        }
        if (!(world.field_9236 || (remainder = (eaten = kitchenware.method_5434(0, 1)).method_7910(world, (class_1309)player)).method_7960() || player.method_31548().method_7394(remainder))) {
            class_1264.method_5449((class_1937)world, (double)((double)pos.method_10263() + 0.5), (double)((double)pos.method_10264() + 0.5), (double)((double)pos.method_10260() + 0.5), (class_1799)remainder);
        }
        return class_1269.field_5812;
    }

    protected MapCodec<? extends class_2237> method_53969() {
        return CODEC;
    }
}

