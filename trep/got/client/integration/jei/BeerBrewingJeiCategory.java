/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.gui.builder.IRecipeLayoutBuilder
 *  mezz.jei.api.gui.builder.IRecipeSlotBuilder
 *  mezz.jei.api.gui.drawable.IDrawable
 *  mezz.jei.api.gui.ingredient.IRecipeSlotsView
 *  mezz.jei.api.helpers.IGuiHelper
 *  mezz.jei.api.recipe.IFocusGroup
 *  mezz.jei.api.recipe.RecipeIngredientRole
 *  mezz.jei.api.recipe.RecipeType
 *  mezz.jei.api.recipe.category.IRecipeCategory
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1856
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 */
package trep.got.client.integration.jei;

import java.util.List;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import trep.got.recipe.BeerBrewingRecipe;
import trep.got.registry.GotBlocks;

@Environment(value=EnvType.CLIENT)
public final class BeerBrewingJeiCategory
implements IRecipeCategory<BeerBrewingRecipe> {
    public static final RecipeType<BeerBrewingRecipe> TYPE = RecipeType.create((String)"agob", (String)"beer_brewing", BeerBrewingRecipe.class);
    private static final class_2960 TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/barrel_gui_rei.png");
    private final IDrawable background;
    private final IDrawable icon;

    public BeerBrewingJeiCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 175, 82);
        this.icon = guiHelper.createDrawableItemLike((class_1935)GotBlocks.BREWER);
    }

    public RecipeType<BeerBrewingRecipe> getRecipeType() {
        return TYPE;
    }

    public class_2561 getTitle() {
        return class_2561.method_43471((String)"container.agob.beer_brewing");
    }

    public IDrawable getBackground() {
        return this.background;
    }

    public IDrawable getIcon() {
        return this.icon;
    }

    public void setRecipe(IRecipeLayoutBuilder builder, BeerBrewingRecipe recipe, IFocusGroup focuses) {
        List<class_1856> ingredients = recipe.ingredients();
        ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.INPUT, 28, 26).addIngredients(ingredients.get(0))).setStandardSlotBackground();
        ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.INPUT, 46, 26).addIngredients(ingredients.get(1))).setStandardSlotBackground();
        ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.INPUT, 28, 44).addIngredients(ingredients.get(2))).setStandardSlotBackground();
        ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.INPUT, 46, 44).addIngredients(ingredients.get(3))).setStandardSlotBackground();
        ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.INPUT, 74, 50).addIngredients(ingredients.get(4))).setStandardSlotBackground();
        ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.OUTPUT, 129, 34).addItemStack(recipe.result())).setOutputSlotBackground();
    }

    public void draw(BeerBrewingRecipe recipe, IRecipeSlotsView recipeSlotsView, class_332 context, double mouseX, double mouseY) {
        context.method_25291(TEXTURE, 0, 0, 0, 0.0f, 0.0f, 175, 82, 256, 256);
    }
}

