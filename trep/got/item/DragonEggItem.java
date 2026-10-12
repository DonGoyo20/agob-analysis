/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_2248
 *  net.minecraft.class_2487
 *  net.minecraft.class_9279
 *  net.minecraft.class_9280
 *  net.minecraft.class_9334
 */
package trep.got.item;

import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_2487;
import net.minecraft.class_9279;
import net.minecraft.class_9280;
import net.minecraft.class_9334;
import trep.got.entity.dragon.DragonEggColor;

public final class DragonEggItem
extends class_1747 {
    public DragonEggItem(class_2248 block, class_1792.class_1793 settings) {
        super(block, settings);
    }

    public static DragonEggColor getColor(class_1799 stack) {
        class_2487 nbt = ((class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302)).method_57461();
        if (!nbt.method_10545("DragonEggColor")) {
            class_9280 customModelData = (class_9280)stack.method_57825(class_9334.field_49637, (Object)class_9280.field_49307);
            return DragonEggColor.byCustomModelData(customModelData.comp_2382());
        }
        return DragonEggColor.byId(nbt.method_10558("DragonEggColor"));
    }

    public static void setColor(class_1799 stack, DragonEggColor color) {
        DragonEggColor resolvedColor = color == null ? DragonEggColor.DEFAULT : color;
        class_2487 nbt = ((class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302)).method_57461();
        nbt.method_10582("DragonEggColor", resolvedColor.id());
        stack.method_57379(class_9334.field_49628, (Object)class_9279.method_57456((class_2487)nbt));
        stack.method_57379(class_9334.field_49637, (Object)new class_9280(resolvedColor.customModelData()));
    }

    public static class_1799 createStack(class_2248 block, DragonEggColor color) {
        class_1799 stack = new class_1799((class_1935)block);
        DragonEggItem.setColor(stack, color);
        return stack;
    }
}

