/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_1264
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1703
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2586
 *  net.minecraft.class_2680
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3417
 *  net.minecraft.class_3419
 *  net.minecraft.class_5819
 *  net.minecraft.class_8710
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.blacksmith;

import java.util.List;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1264;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_5819;
import net.minecraft.class_8710;
import org.jetbrains.annotations.Nullable;
import trep.got.blacksmith.BlacksmithCraftSession;
import trep.got.blacksmith.BlacksmithRecipe;
import trep.got.blacksmith.BlacksmithRecipeIngredient;
import trep.got.blacksmith.BlacksmithScreenHandler;
import trep.got.blacksmith.BlacksmithWorkbenchAccess;
import trep.got.block.entity.BlacksmithFurnaceBlockEntity;
import trep.got.item.BlacksmithHammerRules;
import trep.got.item.ItemStackQualityHelper;
import trep.got.kingdom.ReputationExperienceService;
import trep.got.network.BlacksmithSessionClearedPayload;
import trep.got.registry.GotCharacters;
import trep.got.registry.GotMainQuests;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.PlayerSkillProfile;
import trep.got.skill.SkillAttributeType;
import trep.got.skill.SkillExperienceService;
import trep.got.skill.source.SmithingSkillExperienceSource;

public final class BlacksmithCrafting {
    private BlacksmithCrafting() {
    }

    public static boolean isStationActive(class_3218 world, BlacksmithWorkbenchAccess.WorkbenchPair pair) {
        return BlacksmithCrafting.getSession(world, pair) != null;
    }

    @Nullable
    public static BlacksmithCraftSession getSession(class_3218 world, BlacksmithWorkbenchAccess.WorkbenchPair pair) {
        BlacksmithFurnaceBlockEntity furnace = BlacksmithCrafting.getFurnace((class_1937)world, pair.furnaceOrigin());
        return furnace == null ? null : furnace.getSession();
    }

    @Nullable
    public static BlacksmithCraftSession getSession(class_1937 world, BlacksmithWorkbenchAccess.WorkbenchPair pair) {
        if (!(world instanceof class_3218)) {
            class_2586 class_25862 = world.method_8321(pair.furnaceOrigin());
            if (class_25862 instanceof BlacksmithFurnaceBlockEntity) {
                BlacksmithFurnaceBlockEntity furnace = (BlacksmithFurnaceBlockEntity)class_25862;
                return furnace.getSession();
            }
            return null;
        }
        class_3218 serverWorld = (class_3218)world;
        return BlacksmithCrafting.getSession(serverWorld, pair);
    }

    public static boolean startSession(class_3222 player, class_3218 world, BlacksmithWorkbenchAccess.WorkbenchPair pair, class_2960 recipeId, BlacksmithRecipe recipe, List<class_1799> reservedStacks) {
        BlacksmithFurnaceBlockEntity furnace = BlacksmithCrafting.getFurnace((class_1937)world, pair.furnaceOrigin());
        if (furnace == null || furnace.getSession() != null) {
            return false;
        }
        BlacksmithCraftSession session = new BlacksmithCraftSession(recipeId, player.method_5667(), recipe.result(), reservedStacks, recipe.speed(), world.method_8510());
        furnace.startSession(pair.tableOrigin(), session);
        BlacksmithCrafting.closeRelevantScreens(world, pair);
        return true;
    }

    public static boolean handleHammerHit(class_3222 player, class_3218 world, BlacksmithWorkbenchAccess.WorkbenchPair pair) {
        BlacksmithFurnaceBlockEntity furnace = BlacksmithCrafting.getFurnace((class_1937)world, pair.furnaceOrigin());
        if (furnace == null) {
            return false;
        }
        BlacksmithCraftSession session = furnace.getSession();
        if (session == null || !session.smithUuid().equals(player.method_5667())) {
            return false;
        }
        class_1799 hammer = player.method_6047();
        if (!BlacksmithHammerRules.isHammer(hammer)) {
            return false;
        }
        if (player.method_7357().method_7904(hammer.method_7909())) {
            return false;
        }
        BlacksmithCraftSession.HitBand band = session.recordHit(world.method_8510());
        hammer.method_7970(1, (class_1309)player, class_1304.field_6173);
        player.method_7357().method_7906(hammer.method_7909(), 20);
        furnace.syncSession();
        float pitch = switch (band) {
            default -> throw new MatchException(null, null);
            case BlacksmithCraftSession.HitBand.BAD -> 0.75f;
            case BlacksmithCraftSession.HitBand.OKAY -> 0.95f;
            case BlacksmithCraftSession.HitBand.PERFECT -> 1.12f;
            case BlacksmithCraftSession.HitBand.PERFECT_CENTER -> 1.25f;
        };
        world.method_8396(null, pair.tableOrigin(), class_3417.field_14927, class_3419.field_15245, 0.85f, pitch);
        if (session.isComplete()) {
            BlacksmithCrafting.completeSession(player, world, pair, furnace, session);
        }
        return true;
    }

    public static boolean tryHandleHammerInteraction(class_1657 player, class_1937 world, class_2338 pos, class_2680 state) {
        class_3218 serverWorld;
        block5: {
            block4: {
                if (!(world instanceof class_3218)) break block4;
                serverWorld = (class_3218)world;
                if (player instanceof class_3222) break block5;
            }
            return false;
        }
        class_3222 serverPlayer = (class_3222)player;
        if (!BlacksmithHammerRules.isHammer(serverPlayer.method_6047())) {
            return false;
        }
        BlacksmithWorkbenchAccess.WorkbenchPair pair = BlacksmithWorkbenchAccess.findPair(world, pos, state);
        return pair != null && BlacksmithCrafting.handleHammerHit(serverPlayer, serverWorld, pair);
    }

    public static void cancelSession(class_3218 world, BlacksmithWorkbenchAccess.WorkbenchPair pair) {
        BlacksmithFurnaceBlockEntity furnace = BlacksmithCrafting.getFurnace((class_1937)world, pair.furnaceOrigin());
        if (furnace == null || furnace.getSession() == null) {
            return;
        }
        BlacksmithCraftSession session = furnace.getSession();
        for (class_1799 stack : session.reservedStacks()) {
            class_1264.method_5449((class_1937)world, (double)((double)pair.tableOrigin().method_10263() + 0.5), (double)((double)pair.tableOrigin().method_10264() + 1.0), (double)((double)pair.tableOrigin().method_10260() + 0.5), (class_1799)stack);
        }
        BlacksmithCrafting.notifySessionCleared(world, pair.furnaceOrigin(), "cancel");
        furnace.clearSession();
        BlacksmithCrafting.closeRelevantScreens(world, pair);
    }

    private static void completeSession(class_3222 player, class_3218 world, BlacksmithWorkbenchAccess.WorkbenchPair pair, BlacksmithFurnaceBlockEntity furnace, BlacksmithCraftSession session) {
        BlacksmithRecipe recipe = world.method_8433().method_8130(session.recipeId()).filter(entry -> entry.comp_1933() instanceof BlacksmithRecipe).map(entry -> (BlacksmithRecipe)entry.comp_1933()).orElse(null);
        class_1799 result = session.result();
        BlacksmithCrafting.applyRolledQuality(result, session, world.field_9229);
        player.method_31548().method_7398(result);
        if (recipe != null) {
            SmithingSkillExperienceSource.grantCraftingExperience(player, recipe);
            BlacksmithCrafting.grantFirstCraftIntelligence(player, session.recipeId());
            ReputationExperienceService.addExperience(player, BlacksmithCrafting.calculateCraftingReputationExperience(recipe));
        }
        GotMainQuests.onBlacksmithItemCrafted(player);
        BlacksmithCrafting.notifySessionCleared(world, pair.furnaceOrigin(), "complete");
        furnace.clearSession();
        world.method_8396(null, pair.tableOrigin(), class_3417.field_14559, class_3419.field_15245, 0.9f, 1.05f);
    }

    private static void applyRolledQuality(class_1799 result, BlacksmithCraftSession session, class_5819 random) {
        if (!ItemStackQualityHelper.isQualityApplicable(result)) {
            return;
        }
        int score = session.qualityScore();
        ItemStackQualityHelper.Quality quality = score <= 1 ? (random.method_43056() ? ItemStackQualityHelper.Quality.TERRIBLE : ItemStackQualityHelper.Quality.BAD) : (score <= 3 ? (random.method_43048(4) == 0 ? ItemStackQualityHelper.Quality.GOOD : ItemStackQualityHelper.Quality.DECENT) : (score <= 5 ? (random.method_43056() ? ItemStackQualityHelper.Quality.GOOD : ItemStackQualityHelper.Quality.DECENT) : (score <= 7 ? (random.method_43048(4) == 0 ? ItemStackQualityHelper.Quality.PERFECT : ItemStackQualityHelper.Quality.GOOD) : (random.method_43048(3) == 0 ? ItemStackQualityHelper.Quality.LEGENDARY : ItemStackQualityHelper.Quality.PERFECT))));
        ItemStackQualityHelper.setQuality(result, quality);
    }

    private static void closeRelevantScreens(class_3218 world, BlacksmithWorkbenchAccess.WorkbenchPair pair) {
        for (class_3222 other : world.method_18456()) {
            BlacksmithScreenHandler handler;
            class_1703 class_17032 = other.field_7512;
            if (!(class_17032 instanceof BlacksmithScreenHandler) || !pair.equals((handler = (BlacksmithScreenHandler)class_17032).getPair())) continue;
            other.method_7346();
        }
    }

    private static void grantFirstCraftIntelligence(class_3222 player, class_2960 recipeId) {
        PlayerSkillProfile skillProfile = ((PlayerSkillHolder)player).got$getSkillProfile();
        if (!skillProfile.markBlacksmithRecipeCrafted(recipeId)) {
            return;
        }
        if (SkillExperienceService.addPoints(player, SkillAttributeType.INTELLIGENCE, 1) > 0) {
            GotCharacters.sendCharacterSelectionState(player);
        }
    }

    private static int calculateCraftingReputationExperience(BlacksmithRecipe recipe) {
        return recipe.ingredients().stream().map(BlacksmithRecipeIngredient::createPreviewStack).filter(stack -> !stack.method_7963()).mapToInt(class_1799::method_7947).sum();
    }

    @Nullable
    private static BlacksmithFurnaceBlockEntity getFurnace(class_1937 world, class_2338 furnaceOrigin) {
        BlacksmithFurnaceBlockEntity furnace;
        class_2586 class_25862 = world.method_8321(furnaceOrigin);
        return class_25862 instanceof BlacksmithFurnaceBlockEntity ? (furnace = (BlacksmithFurnaceBlockEntity)class_25862) : null;
    }

    private static void notifySessionCleared(class_3218 world, class_2338 furnaceOrigin, String reason) {
        world.method_8503().method_3760().method_14571().stream().filter(player -> player.method_37908() == world).forEach(player -> ServerPlayNetworking.send((class_3222)player, (class_8710)new BlacksmithSessionClearedPayload(furnaceOrigin, reason)));
    }
}

