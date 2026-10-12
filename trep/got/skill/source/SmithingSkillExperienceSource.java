/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_3222
 */
package trep.got.skill.source;

import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_3222;
import trep.got.blacksmith.BlacksmithRecipe;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeType;
import trep.got.skill.SkillExperienceService;
import trep.got.skill.source.SkillExperienceSource;

public final class SmithingSkillExperienceSource
implements SkillExperienceSource {
    @Override
    public SkillAttributeType type() {
        return SkillAttributeType.SMITHING;
    }

    public static void grantCraftingExperience(class_3222 player, BlacksmithRecipe recipe) {
        if (player == null || recipe == null) {
            return;
        }
        int materialCount = recipe.ingredients().stream().map(ingredient -> ingredient.createPreviewStack()).filter(stack -> !stack.method_7963()).mapToInt(class_1799::method_7947).sum();
        int hammerWeight = (int)recipe.ingredients().stream().map(ingredient -> ingredient.createPreviewStack()).filter(class_1799::method_7963).count();
        int baseExperience = materialCount * 6 + hammerWeight * 2 + Math.max(0, recipe.requiredSmithingLevel() / 5);
        double recipeTier = 1.0 + (double)materialCount / 2.5 + (double)recipe.requiredSmithingLevel() / 8.0;
        SkillExperienceService.addExperience(player, SkillAttributeType.SMITHING, SmithingSkillExperienceSource.scaleExperience(player, baseExperience, recipeTier));
    }

    public static void grantSmeltingExperience(class_3222 player, class_1799 outputStack, int recipeUses) {
        if (player == null || outputStack == null || outputStack.method_7960() || recipeUses <= 0) {
            return;
        }
        int basePerItem = 0;
        if (outputStack.method_31574(class_1802.field_27022)) {
            basePerItem = 6;
        } else if (outputStack.method_31574(class_1802.field_8620)) {
            basePerItem = 8;
        } else if (outputStack.method_31574(class_1802.field_8695)) {
            basePerItem = 10;
        } else if (outputStack.method_31574(class_1802.field_22021)) {
            basePerItem = 16;
        }
        if (basePerItem <= 0) {
            return;
        }
        int totalItems = outputStack.method_7947() * recipeUses;
        double recipeTier = switch (basePerItem) {
            case 6 -> 1.0;
            case 8 -> 1.5;
            case 10 -> 2.0;
            default -> 3.0;
        };
        SkillExperienceService.addExperience(player, SkillAttributeType.SMITHING, SmithingSkillExperienceSource.scaleExperience(player, basePerItem * totalItems, recipeTier));
    }

    private static int scaleExperience(class_3222 player, int baseExperience, double tier) {
        if (!(player instanceof PlayerSkillHolder)) {
            return Math.max(1, baseExperience);
        }
        PlayerSkillHolder skillHolder = (PlayerSkillHolder)player;
        int points = skillHolder.got$getSkillProfile().get(SkillAttributeType.SMITHING).getPoints();
        double scaled = (double)baseExperience * tier / (1.0 + Math.pow((double)points / Math.max(8.0, tier * 10.0), 2.0));
        return Math.max(1, (int)Math.round(scaled));
    }
}

