/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_7923
 */
package trep.got.blacksmith;

import java.util.Comparator;
import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_7923;
import trep.got.blacksmith.BlacksmithRecipe;
import trep.got.blacksmith.BlacksmithRecipeDisplay;

public final class BlacksmithRecipeSorting {
    private static final List<String> ARMOR_SUFFIX_ORDER = List.of("_helmet", "_chestplate", "_leggings", "_boots");

    private BlacksmithRecipeSorting() {
    }

    public static Comparator<BlacksmithRecipeDisplay> displayComparator() {
        return Comparator.comparingInt(BlacksmithRecipeDisplay::order).thenComparingInt(display -> BlacksmithRecipeSorting.suffixOrder(display.result())).thenComparing(display -> BlacksmithRecipeSorting.baseName(display.result())).thenComparing(display -> display.result().method_7964().getString());
    }

    public static Comparator<BlacksmithRecipe> recipeComparator() {
        return Comparator.comparingInt(BlacksmithRecipe::order).thenComparingInt(recipe -> BlacksmithRecipeSorting.suffixOrder(recipe.result())).thenComparing(recipe -> BlacksmithRecipeSorting.baseName(recipe.result())).thenComparing(recipe -> recipe.result().method_7964().getString());
    }

    private static int suffixOrder(class_1799 stack) {
        String path = class_7923.field_41178.method_10221((Object)stack.method_7909()).method_12832();
        for (int index = 0; index < ARMOR_SUFFIX_ORDER.size(); ++index) {
            if (!path.endsWith(ARMOR_SUFFIX_ORDER.get(index))) continue;
            return index;
        }
        return ARMOR_SUFFIX_ORDER.size();
    }

    private static String baseName(class_1799 stack) {
        String path = class_7923.field_41178.method_10221((Object)stack.method_7909()).method_12832();
        for (String suffix : ARMOR_SUFFIX_ORDER) {
            if (!path.endsWith(suffix)) continue;
            return path.substring(0, path.length() - suffix.length());
        }
        return path;
    }
}

