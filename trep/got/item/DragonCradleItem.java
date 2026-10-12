/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1269
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1838
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2586
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_3922
 *  net.minecraft.class_3924
 */
package trep.got.item;

import net.minecraft.class_1269;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1838;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3922;
import net.minecraft.class_3924;
import trep.got.block.DragonCradleBlock;
import trep.got.registry.GotBlocks;

public final class DragonCradleItem
extends class_1747 {
    public DragonCradleItem(class_2248 block, class_1792.class_1793 settings) {
        super(block, settings);
    }

    public class_1269 method_7884(class_1838 context) {
        class_2338 pos;
        class_1937 world = context.method_8045();
        class_2680 state = world.method_8320(pos = context.method_8037());
        if (state.method_27852(class_2246.field_17350) && DragonCradleItem.isEmptyCampfire(world, pos)) {
            if (!world.field_9236) {
                class_2680 cradleState = (class_2680)((class_2680)GotBlocks.DRAGON_CRADLE.method_9564().method_11657((class_2769)DragonCradleBlock.ACTIVATED, (Comparable)Boolean.valueOf(true))).method_11657((class_2769)DragonCradleBlock.FACING, (Comparable)((class_2350)state.method_11654((class_2769)class_3922.field_17564)));
                world.method_8652(pos, cradleState, 3);
                if (context.method_8036() == null || !context.method_8036().method_7337()) {
                    context.method_8041().method_7934(1);
                }
            }
            return class_1269.field_5812;
        }
        return super.method_7884(context);
    }

    private static boolean isEmptyCampfire(class_1937 world, class_2338 pos) {
        class_2586 class_25862 = world.method_8321(pos);
        if (!(class_25862 instanceof class_3924)) {
            return true;
        }
        class_3924 campfire = (class_3924)class_25862;
        return campfire.method_17505().stream().allMatch(stack -> stack.method_7960());
    }
}

