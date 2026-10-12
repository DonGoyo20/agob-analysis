/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1792$class_9635
 *  net.minecraft.class_1799
 *  net.minecraft.class_1836
 *  net.minecraft.class_1935
 *  net.minecraft.class_2248
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_9279
 *  net.minecraft.class_9280
 *  net.minecraft.class_9334
 */
package trep.got.item;

import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_9279;
import net.minecraft.class_9280;
import net.minecraft.class_9334;
import trep.got.block.GateWoodType;
import trep.got.registry.GotItems;

public final class GateMechanismItem
extends class_1747 {
    private static final String GATE_WOOD_TYPE_KEY = "GateWoodType";
    private static final String GATE_BROKEN_KEY = "GateBroken";

    public GateMechanismItem(class_2248 block, class_1792.class_1793 settings) {
        super(block, settings);
    }

    public static class_1799 createStack(GateWoodType woodType) {
        return GateMechanismItem.createStack(woodType, false);
    }

    public static class_1799 createBrokenStack(GateWoodType woodType) {
        return GateMechanismItem.createStack(woodType, true);
    }

    public static class_1799 createStack(GateWoodType woodType, boolean broken) {
        class_1799 stack = new class_1799((class_1935)GotItems.GATE_MECHANISM);
        GateMechanismItem.applyState(stack, woodType, broken);
        return stack;
    }

    public static void applyState(class_1799 stack, GateWoodType woodType, boolean broken) {
        class_2487 nbt = ((class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302)).method_57461();
        nbt.method_10582(GATE_WOOD_TYPE_KEY, woodType.method_15434());
        nbt.method_10556(GATE_BROKEN_KEY, broken);
        stack.method_57379(class_9334.field_49628, (Object)class_9279.method_57456((class_2487)nbt));
        if (broken) {
            stack.method_57379(class_9334.field_49637, (Object)new class_9280(1));
        } else {
            stack.method_57381(class_9334.field_49637);
        }
    }

    public static GateWoodType getWoodType(class_1799 stack) {
        class_2487 nbt = ((class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302)).method_57461();
        return GateWoodType.byId(nbt.method_10558(GATE_WOOD_TYPE_KEY));
    }

    public static boolean isBroken(class_1799 stack) {
        class_2487 nbt = ((class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302)).method_57461();
        return nbt.method_10577(GATE_BROKEN_KEY);
    }

    public void method_7851(class_1799 stack, class_1792.class_9635 context, List<class_2561> tooltip, class_1836 type) {
        super.method_7851(stack, context, tooltip, type);
        if (stack.method_57826(class_9334.field_49632)) {
            return;
        }
        boolean broken = GateMechanismItem.isBroken(stack);
        GateWoodType woodType = GateMechanismItem.getWoodType(stack);
        tooltip.add((class_2561)class_2561.method_43470((String)(broken ? "State: Broken" : "State: Intact")).method_27692(broken ? class_124.field_1061 : class_124.field_1077).method_27694(style -> style.method_10978(Boolean.valueOf(false))));
        tooltip.add((class_2561)class_2561.method_43470((String)("Wood: " + woodType.displayName())).method_27692(class_124.field_1080).method_27694(style -> style.method_10978(Boolean.valueOf(false))));
    }
}

