/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_8786
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.blacksmith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_8786;
import org.jetbrains.annotations.Nullable;
import trep.got.blacksmith.BlacksmithCrafting;
import trep.got.blacksmith.BlacksmithKingdomUnlocks;
import trep.got.blacksmith.BlacksmithRecipe;
import trep.got.blacksmith.BlacksmithRecipeIngredient;
import trep.got.blacksmith.BlacksmithScreenOpeningData;
import trep.got.blacksmith.BlacksmithWorkbenchAccess;
import trep.got.item.BlacksmithHammerRules;
import trep.got.registry.GotRecipes;
import trep.got.registry.GotScreenHandlers;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeType;

public final class BlacksmithScreenHandler
extends class_1703 {
    private static final int UNLOCK_BUTTON_BASE = 500;
    private static final int CRAFT_BUTTON_BASE = 1000;
    @Nullable
    private final class_3218 world;
    @Nullable
    private final BlacksmithWorkbenchAccess.WorkbenchPair pair;
    private final List<class_8786<BlacksmithRecipe>> serverRecipes;
    private final BlacksmithScreenOpeningData openingData;

    private BlacksmithScreenHandler(int syncId, @Nullable class_3218 world, @Nullable BlacksmithWorkbenchAccess.WorkbenchPair pair, List<class_8786<BlacksmithRecipe>> serverRecipes, BlacksmithScreenOpeningData openingData) {
        super(GotScreenHandlers.BLACKSMITH_FURNACE, syncId);
        this.world = world;
        this.pair = pair;
        this.serverRecipes = serverRecipes;
        this.openingData = openingData;
    }

    public static BlacksmithScreenHandler createServer(int syncId, class_1661 inventory, class_3218 world, BlacksmithWorkbenchAccess.WorkbenchPair pair, BlacksmithScreenOpeningData openingData) {
        HashMap<class_2960, class_8786> recipesById = new HashMap<class_2960, class_8786>();
        for (class_8786 entry : world.method_8433().method_30027(GotRecipes.BLACKSMITH_TYPE)) {
            recipesById.put(entry.comp_1932(), entry);
        }
        List<class_8786<BlacksmithRecipe>> sortedRecipes = openingData.recipes().stream().map(display -> (class_8786)recipesById.get(display.recipeId())).filter(Objects::nonNull).toList();
        return new BlacksmithScreenHandler(syncId, world, pair, sortedRecipes, openingData);
    }

    public static BlacksmithScreenHandler createClient(int syncId, class_1661 inventory, BlacksmithScreenOpeningData openingData) {
        return new BlacksmithScreenHandler(syncId, null, null, List.of(), openingData);
    }

    public BlacksmithScreenOpeningData getOpeningData() {
        return this.openingData;
    }

    @Nullable
    public BlacksmithWorkbenchAccess.WorkbenchPair getPair() {
        return this.pair;
    }

    public int getCraftButtonId(class_2960 recipeId) {
        for (int index = 0; index < this.openingData.recipes().size(); ++index) {
            if (!this.openingData.recipes().get(index).recipeId().equals((Object)recipeId)) continue;
            return 1000 + index;
        }
        return -1;
    }

    public int getUnlockButtonId(class_2960 kingdomId) {
        for (int index = 0; index < this.openingData.groups().size(); ++index) {
            if (!this.openingData.groups().get(index).kingdomId().equals((Object)kingdomId)) continue;
            return 500 + index;
        }
        return -1;
    }

    public boolean method_7604(class_1657 player, int id) {
        if (this.world == null || this.pair == null) {
            return false;
        }
        if (id >= 500 && id < 1000) {
            return this.unlockKingdom(player, id - 500);
        }
        if (id < 1000) {
            return false;
        }
        int recipeIndex = id - 1000;
        if (recipeIndex < 0 || recipeIndex >= this.serverRecipes.size()) {
            return false;
        }
        if (!this.method_7597(player)) {
            return false;
        }
        return this.craft(player, this.serverRecipes.get(recipeIndex));
    }

    private boolean unlockKingdom(class_1657 player, int groupIndex) {
        class_3222 serverPlayer;
        block8: {
            block7: {
                if (!(player instanceof class_3222)) break block7;
                serverPlayer = (class_3222)player;
                if (this.method_7597(player)) break block8;
            }
            return false;
        }
        if (groupIndex < 0 || groupIndex >= this.openingData.groups().size()) {
            return false;
        }
        class_2960 kingdomId = this.openingData.groups().get(groupIndex).kingdomId();
        if (!BlacksmithKingdomUnlocks.canUnlock(serverPlayer, kingdomId)) {
            return false;
        }
        if (!(serverPlayer instanceof PlayerSkillHolder)) {
            return false;
        }
        PlayerSkillHolder skillHolder = (PlayerSkillHolder)serverPlayer;
        if (!skillHolder.got$getSkillProfile().unlockBlacksmithKingdom(kingdomId)) {
            return false;
        }
        BlacksmithWorkbenchAccess.openWorkbenchScreen(serverPlayer, this.world, this.pair);
        return true;
    }

    private boolean craft(class_1657 player, class_8786<BlacksmithRecipe> recipeEntry) {
        class_3222 serverPlayer;
        BlacksmithRecipe recipe;
        block12: {
            block11: {
                recipe = (BlacksmithRecipe)recipeEntry.comp_1933();
                if (!BlacksmithScreenHandler.meetsSmithingRequirement(player, recipe)) {
                    return false;
                }
                if (!(player instanceof class_3222)) break block11;
                serverPlayer = (class_3222)player;
                if (this.world != null && this.pair != null) break block12;
            }
            return false;
        }
        if (BlacksmithCrafting.isStationActive(this.world, this.pair)) {
            return false;
        }
        class_1661 inventory = player.method_31548();
        HashMap<Integer, Integer> reservations = new HashMap<Integer, Integer>();
        ArrayList<class_1799> reservedStacks = new ArrayList<class_1799>();
        for (BlacksmithRecipeIngredient blacksmithRecipeIngredient : recipe.ingredients()) {
            class_1799 preview = blacksmithRecipeIngredient.createPreviewStack();
            boolean hammerRequirement = BlacksmithHammerRules.isHammer(preview);
            int matchedSlot = -1;
            for (int slot = 0; slot < inventory.method_5439(); ++slot) {
                class_1799 stack = inventory.method_5438(slot);
                int reserved = reservations.getOrDefault(slot, 0);
                if (stack.method_7960() || !blacksmithRecipeIngredient.ingredient().method_8093(stack) || (hammerRequirement ? !BlacksmithHammerRules.isHammer(stack) || stack.method_7947() - reserved < blacksmithRecipeIngredient.count() : (stack.method_7963() ? reserved > 0 || blacksmithRecipeIngredient.count() != 1 : stack.method_7947() - reserved < blacksmithRecipeIngredient.count()))) continue;
                matchedSlot = slot;
                break;
            }
            if (matchedSlot < 0) {
                return false;
            }
            if (hammerRequirement) continue;
            reservations.put(matchedSlot, reservations.getOrDefault(matchedSlot, 0) + blacksmithRecipeIngredient.count());
            reservedStacks.add(inventory.method_5438(matchedSlot).method_46651(blacksmithRecipeIngredient.count()));
        }
        for (Map.Entry entry : reservations.entrySet()) {
            class_1799 stack = inventory.method_5438(((Integer)entry.getKey()).intValue());
            stack.method_7934(((Integer)entry.getValue()).intValue());
        }
        if (!BlacksmithCrafting.startSession(serverPlayer, this.world, this.pair, recipeEntry.comp_1932(), recipe, reservedStacks)) {
            for (int index = 0; index < reservedStacks.size(); ++index) {
                player.method_31548().method_7398((class_1799)reservedStacks.get(index));
            }
            return false;
        }
        serverPlayer.method_7346();
        return true;
    }

    private static boolean meetsSmithingRequirement(class_1657 player, BlacksmithRecipe recipe) {
        if (!(player instanceof PlayerSkillHolder)) {
            return recipe.requiredSmithingLevel() <= 0;
        }
        PlayerSkillHolder skillHolder = (PlayerSkillHolder)player;
        return skillHolder.got$getSkillProfile().get(SkillAttributeType.SMITHING).getPoints() >= recipe.requiredSmithingLevel();
    }

    private static int calculateCraftingReputationExperience(BlacksmithRecipe recipe) {
        return recipe.ingredients().stream().map(BlacksmithRecipeIngredient::createPreviewStack).filter(stack -> !stack.method_7963()).mapToInt(class_1799::method_7947).sum();
    }

    public class_1799 method_7601(class_1657 player, int slot) {
        return class_1799.field_8037;
    }

    public boolean method_7597(class_1657 player) {
        boolean valid = this.world == null || this.pair == null || BlacksmithWorkbenchAccess.isStillValid((class_1937)this.world, this.pair, player) && !BlacksmithCrafting.isStationActive(this.world, this.pair);
        return valid;
    }
}

