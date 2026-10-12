/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1852
 *  net.minecraft.class_1865
 *  net.minecraft.class_1937
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_7710
 *  net.minecraft.class_8566
 *  net.minecraft.class_9694
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.recipe;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1852;
import net.minecraft.class_1865;
import net.minecraft.class_1937;
import net.minecraft.class_7225;
import net.minecraft.class_7710;
import net.minecraft.class_8566;
import net.minecraft.class_9694;
import org.jetbrains.annotations.Nullable;
import trep.got.item.RuneItem;
import trep.got.recipe.RuneCraftingContext;
import trep.got.registry.GotItems;
import trep.got.registry.GotRecipes;

public final class RuneCraftingRecipe
extends class_1852 {
    public RuneCraftingRecipe(class_7710 category) {
        super(category);
    }

    public boolean matches(class_9694 input, class_1937 world) {
        if (!RuneCraftingContext.isCurrentAltarCrafting()) {
            return false;
        }
        RuneItem rune = RuneCraftingRecipe.runeForReligion(RuneCraftingContext.getCurrentReligionId());
        if (rune == null || input.method_59991() != 3 || input.method_59992() != 3) {
            return false;
        }
        return RuneCraftingRecipe.isFragmentCraft(input) || RuneCraftingRecipe.getUpgradeLevel(input, rune) > 0;
    }

    public class_1799 craft(class_9694 input, class_7225.class_7874 lookup) {
        if (!RuneCraftingContext.isCurrentAltarCrafting()) {
            return class_1799.field_8037;
        }
        RuneItem rune = RuneCraftingRecipe.runeForReligion(RuneCraftingContext.getCurrentReligionId());
        if (rune == null || input.method_59991() != 3 || input.method_59992() != 3) {
            return class_1799.field_8037;
        }
        if (RuneCraftingRecipe.isFragmentCraft(input)) {
            return rune.createStack(lookup, 1);
        }
        int upgradeLevel = RuneCraftingRecipe.getUpgradeLevel(input, rune);
        if (upgradeLevel <= 0) {
            return class_1799.field_8037;
        }
        return rune.createStack(lookup, upgradeLevel + 1);
    }

    public boolean method_8113(int width, int height) {
        return width >= 3 && height >= 3;
    }

    public class_1865<?> method_8119() {
        return GotRecipes.RUNE_CRAFTING_SERIALIZER;
    }

    private static boolean isFragmentCraft(class_9694 input) {
        for (int slot = 0; slot < input.method_59983(); ++slot) {
            if (input.method_59984(slot).method_31574(GotItems.RUNE_FRAGMENT)) continue;
            return false;
        }
        return true;
    }

    private static int getUpgradeLevel(class_9694 input, RuneItem rune) {
        for (int slot = 0; slot < input.method_59983(); ++slot) {
            class_1799 stack = input.method_59984(slot);
            if (slot == 4) {
                if (!stack.method_31574((class_1792)rune)) {
                    return 0;
                }
                int currentLevel = rune.getStoredLevel(stack);
                if (currentLevel > 0 && currentLevel < rune.getMaxRuneLevel()) continue;
                return 0;
            }
            if (stack.method_31574(GotItems.RUNE_FRAGMENT)) continue;
            return 0;
        }
        return rune.getStoredLevel(input.method_59984(4));
    }

    @Nullable
    public static RuneItem runeForReligion(@Nullable String religionId) {
        if (religionId == null || religionId.isBlank()) {
            return null;
        }
        return switch (religionId) {
            case "rhllor" -> GotItems.RUNE_FIRE;
            case "faith_of_the_drowned" -> GotItems.RUNE_UNBREAKING;
            case "faith_of_the_seven" -> GotItems.RUNE_POWER;
            case "the_old_gods" -> GotItems.RUNE_PROTECTION;
            default -> null;
        };
    }

    public static class_1799 craftForAltar(class_8566 input, class_7225.class_7874 lookup, @Nullable String religionId) {
        RuneItem rune = RuneCraftingRecipe.runeForReligion(religionId);
        if (rune == null) {
            return class_1799.field_8037;
        }
        if (input.method_17398() != 3 || input.method_17397() != 3) {
            return class_1799.field_8037;
        }
        if (RuneCraftingRecipe.isFragmentCraft(input)) {
            return rune.createStack(lookup, 1);
        }
        int upgradeLevel = RuneCraftingRecipe.getUpgradeLevel(input, rune);
        if (upgradeLevel <= 0) {
            return class_1799.field_8037;
        }
        return rune.createStack(lookup, upgradeLevel + 1);
    }

    private static boolean isFragmentCraft(class_8566 input) {
        for (int slot = 0; slot < input.method_5439(); ++slot) {
            if (input.method_5438(slot).method_31574(GotItems.RUNE_FRAGMENT)) continue;
            return false;
        }
        return true;
    }

    private static int getUpgradeLevel(class_8566 input, RuneItem rune) {
        for (int slot = 0; slot < input.method_5439(); ++slot) {
            class_1799 stack = input.method_5438(slot);
            if (slot == 4) {
                if (!stack.method_31574((class_1792)rune)) {
                    return 0;
                }
                int currentLevel = rune.getStoredLevel(stack);
                if (currentLevel > 0 && currentLevel < rune.getMaxRuneLevel()) continue;
                return 0;
            }
            if (stack.method_31574(GotItems.RUNE_FRAGMENT)) continue;
            return 0;
        }
        return rune.getStoredLevel(input.method_5438(4));
    }
}

