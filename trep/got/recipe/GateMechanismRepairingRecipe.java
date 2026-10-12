/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1852
 *  net.minecraft.class_1865
 *  net.minecraft.class_1937
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_7710
 *  net.minecraft.class_9694
 */
package trep.got.recipe;

import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1852;
import net.minecraft.class_1865;
import net.minecraft.class_1937;
import net.minecraft.class_7225;
import net.minecraft.class_7710;
import net.minecraft.class_9694;
import trep.got.block.GateWoodType;
import trep.got.item.GateMechanismItem;
import trep.got.registry.GotItems;
import trep.got.registry.GotRecipes;

public final class GateMechanismRepairingRecipe
extends class_1852 {
    public GateMechanismRepairingRecipe(class_7710 category) {
        super(category);
    }

    public boolean matches(class_9694 input, class_1937 world) {
        return GateMechanismRepairingRecipe.findWood(input) != null;
    }

    public class_1799 craft(class_9694 input, class_7225.class_7874 lookup) {
        GateWoodType woodType = GateMechanismRepairingRecipe.findWood(input);
        return woodType == null ? class_1799.field_8037 : GateMechanismItem.createStack(woodType);
    }

    public boolean method_8113(int width, int height) {
        return width >= 3 && height >= 3;
    }

    public class_1865<?> method_8119() {
        return GotRecipes.GATE_MECHANISM_REPAIRING_SERIALIZER;
    }

    private static GateWoodType findWood(class_9694 input) {
        if (input.method_59991() != 3 || input.method_59992() != 3) {
            return null;
        }
        GateWoodType woodType = null;
        for (int slot = 0; slot < input.method_59991() * input.method_59992(); ++slot) {
            class_1799 stack = input.method_59984(slot);
            if (slot == 4) {
                if (!stack.method_31574(GotItems.GATE_MECHANISM) || !GateMechanismItem.isBroken(stack)) {
                    return null;
                }
                woodType = GateMechanismItem.getWoodType(stack);
                continue;
            }
            if (stack.method_31574(class_1802.field_8675)) continue;
            return null;
        }
        return woodType;
    }
}

