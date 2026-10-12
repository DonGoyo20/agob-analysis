/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1852
 *  net.minecraft.class_1865
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_7710
 *  net.minecraft.class_9694
 */
package trep.got.recipe;

import net.minecraft.class_1799;
import net.minecraft.class_1852;
import net.minecraft.class_1865;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_7225;
import net.minecraft.class_7710;
import net.minecraft.class_9694;
import trep.got.entity.VesselMaterialProfile;
import trep.got.entity.VesselMaterialRecipes;
import trep.got.registry.GotItems;
import trep.got.registry.GotRecipes;

public final class LongshipCraftingRecipe
extends class_1852 {
    public LongshipCraftingRecipe(class_7710 category) {
        super(category);
    }

    public boolean matches(class_9694 input, class_1937 world) {
        return VesselMaterialRecipes.matchLongship(input) != null;
    }

    public class_1799 craft(class_9694 input, class_7225.class_7874 lookup) {
        VesselMaterialProfile profile = VesselMaterialRecipes.matchLongship(input);
        if (profile == null) {
            return class_1799.field_8037;
        }
        class_1799 stack = new class_1799((class_1935)GotItems.LONGSHIP);
        profile.apply(stack);
        return stack;
    }

    public boolean method_8113(int width, int height) {
        return width >= 3 && height >= 3;
    }

    public class_1865<?> method_8119() {
        return GotRecipes.LONGSHIP_CRAFTING_SERIALIZER;
    }
}

