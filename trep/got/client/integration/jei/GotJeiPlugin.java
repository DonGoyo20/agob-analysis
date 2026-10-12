/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.IModPlugin
 *  mezz.jei.api.JeiPlugin
 *  mezz.jei.api.constants.RecipeTypes
 *  mezz.jei.api.recipe.RecipeType
 *  mezz.jei.api.recipe.category.IRecipeCategory
 *  mezz.jei.api.recipe.vanilla.IJeiShapedRecipeBuilder
 *  mezz.jei.api.registration.IRecipeCatalystRegistration
 *  mezz.jei.api.registration.IRecipeCategoryRegistration
 *  mezz.jei.api.registration.IRecipeRegistration
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1856
 *  net.minecraft.class_1860
 *  net.minecraft.class_1935
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3955
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_7710
 *  net.minecraft.class_8786
 */
package trep.got.client.integration.jei;

import java.util.ArrayList;
import java.util.List;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.vanilla.IJeiShapedRecipeBuilder;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1860;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3955;
import net.minecraft.class_7225;
import net.minecraft.class_7710;
import net.minecraft.class_8786;
import trep.got.client.integration.jei.BeerBrewingJeiCategory;
import trep.got.client.integration.jei.PotCookingJeiCategory;
import trep.got.entity.VesselMaterialProfile;
import trep.got.item.RuneItem;
import trep.got.recipe.BeerBrewingRecipe;
import trep.got.recipe.PotCookingRecipe;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotItems;
import trep.got.registry.GotRecipes;

@Environment(value=EnvType.CLIENT)
@JeiPlugin
public final class GotJeiPlugin
implements IModPlugin {
    private static final class_2960 UID = class_2960.method_60655((String)"agob", (String)"jei_plugin");

    public class_2960 getPluginUid() {
        return UID;
    }

    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new IRecipeCategory[]{new BeerBrewingJeiCategory(registration.getJeiHelpers().getGuiHelper()), new PotCookingJeiCategory(registration.getJeiHelpers().getGuiHelper())});
    }

    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst((class_1935)GotBlocks.BREWER, new RecipeType[]{BeerBrewingJeiCategory.TYPE});
        registration.addRecipeCatalyst((class_1935)GotBlocks.POT, new RecipeType[]{PotCookingJeiCategory.TYPE});
        registration.addRecipeCatalyst((class_1935)GotBlocks.STOVE, new RecipeType[]{PotCookingJeiCategory.TYPE});
    }

    public void registerRecipes(IRecipeRegistration registration) {
        int level;
        ArrayList<class_8786<class_3955>> recipes = new ArrayList<class_8786<class_3955>>();
        for (VesselMaterialProfile.WoodFamily plankWood : VesselMaterialProfile.WoodFamily.values()) {
            for (VesselMaterialProfile.WoodFamily logWood : VesselMaterialProfile.WoodFamily.values()) {
                for (VesselMaterialProfile.WoolFamily wool : VesselMaterialProfile.WoolFamily.values()) {
                    VesselMaterialProfile profile = new VesselMaterialProfile(plankWood, logWood, wool);
                    recipes.add(GotJeiPlugin.buildShipRecipe(registration, profile));
                    recipes.add(GotJeiPlugin.buildGalleyRecipe(registration, profile));
                    recipes.add(GotJeiPlugin.buildLongshipRecipe(registration, profile));
                }
            }
        }
        recipes.add(GotJeiPlugin.buildRuneRecipe(registration));
        for (level = 1; level < GotItems.RUNE_UNBREAKING.getMaxRuneLevel(); ++level) {
            recipes.add(GotJeiPlugin.buildRuneUpgradeRecipe(registration, GotItems.RUNE_UNBREAKING, "unbreaking", level));
        }
        for (level = 1; level < GotItems.RUNE_POWER.getMaxRuneLevel(); ++level) {
            recipes.add(GotJeiPlugin.buildRuneUpgradeRecipe(registration, GotItems.RUNE_POWER, "power", level));
        }
        for (level = 1; level < GotItems.RUNE_FIRE.getMaxRuneLevel(); ++level) {
            recipes.add(GotJeiPlugin.buildRuneUpgradeRecipe(registration, GotItems.RUNE_FIRE, "fire", level));
        }
        for (level = 1; level < GotItems.RUNE_PROTECTION.getMaxRuneLevel(); ++level) {
            recipes.add(GotJeiPlugin.buildRuneUpgradeRecipe(registration, GotItems.RUNE_PROTECTION, "protection", level));
        }
        registration.addRecipes(RecipeTypes.CRAFTING, recipes);
        registration.addRecipes(BeerBrewingJeiCategory.TYPE, GotJeiPlugin.getBeerBrewingRecipes());
        registration.addRecipes(PotCookingJeiCategory.TYPE, GotJeiPlugin.getPotCookingRecipes());
    }

    private static class_8786<class_3955> buildRuneRecipe(IRecipeRegistration registration) {
        IJeiShapedRecipeBuilder builder = registration.getVanillaRecipeFactory().createShapedRecipeBuilder(class_7710.field_40251, List.of(GotJeiPlugin.runePreviewStack(GotItems.RUNE_UNBREAKING, 1), GotJeiPlugin.runePreviewStack(GotItems.RUNE_POWER, 1), GotJeiPlugin.runePreviewStack(GotItems.RUNE_FIRE, 1), GotJeiPlugin.runePreviewStack(GotItems.RUNE_PROTECTION, 1))).pattern("FFF").pattern("FFF").pattern("FFF").group("agob.runes");
        builder.define(Character.valueOf('F'), GotJeiPlugin.ingredientOf((class_1935)GotItems.RUNE_FRAGMENT));
        return new class_8786(class_2960.method_60655((String)"agob", (String)"jei/rune/base"), (class_1860)builder.build());
    }

    private static class_8786<class_3955> buildRuneUpgradeRecipe(IRecipeRegistration registration, RuneItem rune, String id, int level) {
        IJeiShapedRecipeBuilder builder = registration.getVanillaRecipeFactory().createShapedRecipeBuilder(class_7710.field_40251, List.of(GotJeiPlugin.runePreviewStack(rune, level + 1))).pattern("FFF").pattern("FRF").pattern("FFF").group("agob.runes." + id);
        builder.define(Character.valueOf('F'), GotJeiPlugin.ingredientOf((class_1935)GotItems.RUNE_FRAGMENT));
        builder.define(Character.valueOf('R'), GotJeiPlugin.ingredientOf(GotJeiPlugin.runePreviewStack(rune, level)));
        return new class_8786(class_2960.method_60655((String)"agob", (String)("jei/rune/" + id + "/" + level)), (class_1860)builder.build());
    }

    private static class_8786<class_3955> buildShipRecipe(IRecipeRegistration registration, VesselMaterialProfile profile) {
        IJeiShapedRecipeBuilder builder = registration.getVanillaRecipeFactory().createShapedRecipeBuilder(class_7710.field_40251, List.of(GotJeiPlugin.materializedStack(GotItems.SHIP, profile))).pattern(" FW").pattern(" FW").pattern("PLP").group("agob.ship");
        builder.define(Character.valueOf('F'), GotJeiPlugin.ingredientOf((class_1935)profile.plankWoodFamily().fenceItem()));
        builder.define(Character.valueOf('W'), GotJeiPlugin.ingredientOf((class_1935)profile.woolFamily().block()));
        builder.define(Character.valueOf('P'), GotJeiPlugin.ingredientOf((class_1935)profile.plankWoodFamily().plankItem()));
        builder.define(Character.valueOf('L'), GotJeiPlugin.ingredientOf((class_1935)profile.logWoodFamily().strippedLogItem()));
        return new class_8786(class_2960.method_60655((String)"agob", (String)("jei/ship/" + GotJeiPlugin.profileId(profile))), (class_1860)builder.build());
    }

    private static class_8786<class_3955> buildGalleyRecipe(IRecipeRegistration registration, VesselMaterialProfile profile) {
        IJeiShapedRecipeBuilder builder = registration.getVanillaRecipeFactory().createShapedRecipeBuilder(class_7710.field_40251, List.of(GotJeiPlugin.materializedStack(GotItems.GALLEY, profile))).pattern(" FW").pattern(" FW").pattern("PLP").group("agob.galley");
        builder.define(Character.valueOf('F'), GotJeiPlugin.ingredientOf((class_1935)profile.logWoodFamily().strippedLogItem()));
        builder.define(Character.valueOf('W'), GotJeiPlugin.ingredientOf((class_1935)profile.woolFamily().block()));
        builder.define(Character.valueOf('P'), GotJeiPlugin.ingredientOf((class_1935)profile.plankWoodFamily().plankItem()));
        builder.define(Character.valueOf('L'), GotJeiPlugin.ingredientOf(GotJeiPlugin.materializedStack(GotItems.SHIP, profile)));
        return new class_8786(class_2960.method_60655((String)"agob", (String)("jei/galley/" + GotJeiPlugin.profileId(profile))), (class_1860)builder.build());
    }

    private static class_8786<class_3955> buildLongshipRecipe(IRecipeRegistration registration, VesselMaterialProfile profile) {
        IJeiShapedRecipeBuilder builder = registration.getVanillaRecipeFactory().createShapedRecipeBuilder(class_7710.field_40251, List.of(GotJeiPlugin.materializedStack(GotItems.LONGSHIP, profile))).pattern(" FW").pattern(" FW").pattern("PLP").group("agob.longship");
        builder.define(Character.valueOf('F'), GotJeiPlugin.ingredientOf((class_1935)profile.logWoodFamily().logItem()));
        builder.define(Character.valueOf('W'), GotJeiPlugin.ingredientOf((class_1935)profile.woolFamily().block()));
        builder.define(Character.valueOf('P'), GotJeiPlugin.ingredientOf((class_1935)profile.logWoodFamily().strippedLogItem()));
        builder.define(Character.valueOf('L'), GotJeiPlugin.ingredientOf(GotJeiPlugin.materializedStack(GotItems.GALLEY, profile)));
        return new class_8786(class_2960.method_60655((String)"agob", (String)("jei/longship/" + GotJeiPlugin.profileId(profile))), (class_1860)builder.build());
    }

    private static class_1856 ingredientOf(class_1935 item) {
        return class_1856.method_8091((class_1935[])new class_1935[]{item});
    }

    private static class_1856 ingredientOf(class_1799 stack) {
        return class_1856.method_8101((class_1799[])new class_1799[]{stack});
    }

    private static class_1799 materializedStack(class_1792 item, VesselMaterialProfile profile) {
        class_1799 stack = new class_1799((class_1935)item);
        profile.apply(stack);
        return stack;
    }

    private static class_1799 runePreviewStack(RuneItem rune, int level) {
        class_310 client = class_310.method_1551();
        if (client.field_1687 != null) {
            return rune.createStack((class_7225.class_7874)client.field_1687.method_30349(), level);
        }
        return new class_1799((class_1935)rune);
    }

    private static String profileId(VesselMaterialProfile profile) {
        return profile.plankWoodFamily().plankId().method_12832() + "__" + profile.logWoodFamily().plankId().method_12832() + "__" + profile.woolFamily().blockId().method_12832();
    }

    private static List<BeerBrewingRecipe> getBeerBrewingRecipes() {
        class_310 client = class_310.method_1551();
        if (client.field_1687 == null) {
            return List.of();
        }
        return client.field_1687.method_8433().method_30027(GotRecipes.BEER_BREWING_TYPE).stream().map(class_8786::comp_1933).toList();
    }

    private static List<PotCookingRecipe> getPotCookingRecipes() {
        class_310 client = class_310.method_1551();
        if (client.field_1687 == null) {
            return List.of();
        }
        return client.field_1687.method_8433().method_30027(GotRecipes.POT_COOKING_TYPE).stream().map(class_8786::comp_1933).toList();
    }
}

