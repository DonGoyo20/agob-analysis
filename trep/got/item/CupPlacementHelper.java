/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1750
 *  net.minecraft.class_1799
 *  net.minecraft.class_1838
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2586
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_3419
 *  net.minecraft.class_4538
 */
package trep.got.item;

import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1838;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3419;
import net.minecraft.class_4538;
import trep.got.block.CupBlock;
import trep.got.block.entity.CupBlockEntity;
import trep.got.item.CupLiquidColors;

public final class CupPlacementHelper {
    private CupPlacementHelper() {
    }

    public static class_1269 placeCup(class_1838 context, CupBlock cupBlock, boolean filled, int liquidColor) {
        class_2680 state;
        class_1750 placementContext;
        class_2338 pos;
        class_1657 player = context.method_8036();
        if (player == null || !player.method_5715()) {
            return class_1269.field_5811;
        }
        class_1937 world = context.method_8045();
        if (!world.method_8320(pos = (placementContext = new class_1750(context)).method_8037()).method_26166(placementContext)) {
            pos = pos.method_10093(context.method_8038());
        }
        if ((state = cupBlock.method_9605(placementContext)) == null) {
            return class_1269.field_5814;
        }
        state = (class_2680)state.method_11657((class_2769)CupBlock.FILLED, (Comparable)Boolean.valueOf(filled));
        if (!world.method_8320(pos).method_26166(placementContext) || !state.method_26184((class_4538)world, pos)) {
            return class_1269.field_5814;
        }
        if (!world.method_8652(pos, state, 3)) {
            return class_1269.field_5814;
        }
        class_2586 class_25862 = world.method_8321(pos);
        if (class_25862 instanceof CupBlockEntity) {
            CupBlockEntity cupBlockEntity = (CupBlockEntity)class_25862;
            cupBlockEntity.setStoredStack(context.method_8041(), liquidColor);
        }
        world.method_8396(player, pos, state.method_26231().method_10598(), class_3419.field_15245, 1.0f, 1.0f);
        if (!player.method_31549().field_7477) {
            context.method_8041().method_7934(1);
        }
        return class_1269.field_5812;
    }

    public static class_1269 placeCup(class_1838 context) {
        class_1799 stack = context.method_8041();
        if (!CupLiquidColors.isFilledCup(stack)) {
            return class_1269.field_5811;
        }
        return CupPlacementHelper.placeCup(context, (CupBlock)CupLiquidColors.blockFor(stack), true, CupLiquidColors.colorFor(stack));
    }
}

