/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1264
 *  net.minecraft.class_1268
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2237
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2586
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2960
 *  net.minecraft.class_3965
 *  net.minecraft.class_4970$class_2251
 *  net.minecraft.class_5321
 *  net.minecraft.class_6862
 *  net.minecraft.class_7924
 *  net.minecraft.class_9062
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1264;
import net.minecraft.class_1268;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3965;
import net.minecraft.class_4970;
import net.minecraft.class_5321;
import net.minecraft.class_6862;
import net.minecraft.class_7924;
import net.minecraft.class_9062;
import trep.got.block.KitchenwareBlock;
import trep.got.block.entity.KitchenwareBlockEntity;
import trep.got.registry.GotItems;

public final class CuttingBoardBlock
extends KitchenwareBlock {
    public static final MapCodec<CuttingBoardBlock> CODEC = CuttingBoardBlock.method_54094(CuttingBoardBlock::new);
    private static final class_6862<class_1792> KNIVES = class_6862.method_40092((class_5321)class_7924.field_41197, (class_2960)class_2960.method_60655((String)"agob", (String)"knives"));
    private static final class_265 SHAPE = class_259.method_1084((class_265)class_2248.method_9541((double)4.0, (double)0.0, (double)2.0, (double)15.0, (double)1.0, (double)14.0), (class_265)class_2248.method_9541((double)0.0, (double)0.0, (double)6.0, (double)4.0, (double)1.0, (double)10.0));

    public CuttingBoardBlock(class_4970.class_2251 settings) {
        super(settings, 1, SHAPE);
    }

    @Override
    protected class_9062 method_55765(class_1799 stack, class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit) {
        KitchenwareBlockEntity kitchenware;
        class_2586 class_25862;
        if (stack.method_31573(KNIVES) && (class_25862 = world.method_8321(pos)) instanceof KitchenwareBlockEntity && (kitchenware = (KitchenwareBlockEntity)class_25862).method_5438(0).method_31574(class_1802.field_8229)) {
            if (!world.field_9236) {
                kitchenware.method_5434(0, 1);
                if (!player.method_7337()) {
                    stack.method_7970(1, (class_1309)player, hand == class_1268.field_5808 ? class_1304.field_6173 : class_1304.field_6171);
                }
                class_1799 slicedBread = new class_1799((class_1935)GotItems.SLICED_BREAD, 2);
                if (!player.method_31548().method_7394(slicedBread)) {
                    class_1264.method_5449((class_1937)world, (double)((double)pos.method_10263() + 0.5), (double)((double)pos.method_10264() + 0.5), (double)((double)pos.method_10260() + 0.5), (class_1799)slicedBread);
                }
            }
            return class_9062.field_47728;
        }
        return super.method_55765(stack, state, world, pos, player, hand, hit);
    }

    protected MapCodec<? extends class_2237> method_53969() {
        return CODEC;
    }
}

