/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_1263
 *  net.minecraft.class_1767
 *  net.minecraft.class_1769
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_3222
 *  net.minecraft.class_8710
 *  net.minecraft.class_8786
 */
package trep.got.tailor;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1263;
import net.minecraft.class_1767;
import net.minecraft.class_1769;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.class_8786;
import trep.got.blacksmith.BlacksmithRecipeIngredient;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.character.PlayerSkinBuilderProfile;
import trep.got.character.PlayerTailorProfile;
import trep.got.network.TailorStatePayload;
import trep.got.registry.GotCharacters;
import trep.got.registry.GotRecipes;
import trep.got.tailor.ClothingPatternRegistry;
import trep.got.tailor.TailorRecipe;

public final class GotTailors {
    public static final int DYE_CRAFT_COST = 4;
    public static final int LOOM_CRAFT_RANGE = 4;

    private GotTailors() {
    }

    public static void open(class_3222 player) {
        GotTailors.sync(player, true);
    }

    public static void handleAction(class_3222 player, String action, String slot, String clothingId, String dyeId) {
        if (!GotTailors.isValidSlot(slot) || clothingId == null || clothingId.isBlank()) {
            return;
        }
        dyeId = GotTailors.normalizeDye(dyeId);
        PlayerCharacterProfile characterProfile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        PlayerTailorProfile tailorProfile = characterProfile.getTailorProfile();
        GotTailors.ensureDefaultUnlocked(characterProfile);
        if (!tailorProfile.isDiscovered(slot, clothingId)) {
            GotTailors.sync(player, false);
            return;
        }
        if ("equip".equals(action)) {
            if (tailorProfile.isDyeUnlocked(slot, clothingId, dyeId)) {
                GotTailors.equip(characterProfile, slot, clothingId, dyeId);
                GotCharacters.sendCharacterSelectionState(player);
            }
            GotTailors.sync(player, false);
            return;
        }
        if (!"craft".equals(action) || tailorProfile.isDyeUnlocked(slot, clothingId, dyeId)) {
            GotTailors.sync(player, false);
            return;
        }
        Optional<TailorRecipe> recipe = GotTailors.findRecipe(player, slot, clothingId);
        if (recipe.isEmpty() || !GotTailors.hasNearbyLoom(player) || !GotTailors.hasIngredients((class_1263)player.method_31548(), recipe.get()) || !GotTailors.hasDye((class_1263)player.method_31548(), dyeId)) {
            GotTailors.sync(player, false);
            return;
        }
        GotTailors.consumeIngredients((class_1263)player.method_31548(), recipe.get());
        GotTailors.consumeDye((class_1263)player.method_31548(), dyeId);
        if ("none".equals(dyeId)) {
            tailorProfile.unlock(slot, clothingId);
        } else {
            tailorProfile.unlockDye(slot, clothingId, dyeId);
        }
        GotTailors.equip(characterProfile, slot, clothingId, dyeId);
        GotCharacters.sendCharacterSelectionState(player);
        GotTailors.sync(player, false);
    }

    public static void sync(class_3222 player, boolean openScreen) {
        PlayerCharacterProfile characterProfile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        GotTailors.ensureDefaultUnlocked(characterProfile);
        PlayerTailorProfile tailorProfile = characterProfile.getTailorProfile();
        ServerPlayNetworking.send((class_3222)player, (class_8710)new TailorStatePayload(characterProfile.getSkinBuilderProfile(), (String[])tailorProfile.getDiscoveredClothing().toArray(String[]::new), (String[])tailorProfile.getUnlockedClothing().toArray(String[]::new), (String[])tailorProfile.getUnlockedDyeClothing().toArray(String[]::new), ClothingPatternRegistry.attributePayloads(), openScreen));
    }

    private static void ensureDefaultUnlocked(PlayerCharacterProfile characterProfile) {
        characterProfile.getTailorProfile().unlockDefaultClothes(characterProfile.getSkinBuilderProfile());
    }

    public static DiscoveryResult discoverFromFabric(class_3222 player, String slot, String clothingId) {
        if (player == null || slot == null || clothingId == null || clothingId.isBlank()) {
            return DiscoveryResult.INVALID;
        }
        slot = slot.trim().toLowerCase(Locale.ROOT);
        clothingId = clothingId.trim().toLowerCase(Locale.ROOT).replace("..", "");
        PlayerCharacterProfile characterProfile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        PlayerTailorProfile tailorProfile = characterProfile.getTailorProfile();
        GotTailors.ensureDefaultUnlocked(characterProfile);
        boolean anyValid = false;
        boolean anyNew = false;
        if ("set".equals(slot)) {
            for (String partSlot : Set.of("shirt", "pants")) {
                if (!GotTailors.findRecipe(player, partSlot, clothingId).isPresent()) continue;
                anyValid = true;
                if (tailorProfile.isDiscovered(partSlot, clothingId) || tailorProfile.isUnlocked(partSlot, clothingId)) continue;
                tailorProfile.discover(partSlot, clothingId);
                anyNew = true;
            }
        } else if (GotTailors.isValidSlot(slot) && GotTailors.findRecipe(player, slot, clothingId).isPresent()) {
            anyValid = true;
            if (!tailorProfile.isDiscovered(slot, clothingId) && !tailorProfile.isUnlocked(slot, clothingId)) {
                tailorProfile.discover(slot, clothingId);
                anyNew = true;
            }
        }
        if (!anyValid) {
            return DiscoveryResult.INVALID;
        }
        GotTailors.sync(player, false);
        return anyNew ? DiscoveryResult.DISCOVERED : DiscoveryResult.ALREADY_KNOWN;
    }

    private static Optional<TailorRecipe> findRecipe(class_3222 player, String slot, String clothingId) {
        return player.method_51469().method_8433().method_30027(GotRecipes.TAILOR_TYPE).stream().map(class_8786::comp_1933).filter(recipe -> recipe.matches(slot, clothingId)).findFirst();
    }

    private static void equip(PlayerCharacterProfile characterProfile, String slot, String clothingId, String dyeId) {
        PlayerSkinBuilderProfile skin = characterProfile.getSkinBuilderProfile();
        if ("shirt".equals(slot)) {
            characterProfile.setSkinBuilderProfile(skin.withShirt(clothingId, dyeId));
        } else if ("pants".equals(slot)) {
            characterProfile.setSkinBuilderProfile(skin.withPants(clothingId, dyeId));
        }
    }

    private static boolean hasIngredients(class_1263 inventory, TailorRecipe recipe) {
        for (BlacksmithRecipeIngredient ingredient : recipe.ingredients()) {
            if (GotTailors.count(inventory, ingredient) >= ingredient.count()) continue;
            return false;
        }
        return true;
    }

    private static boolean hasNearbyLoom(class_3222 player) {
        class_2338 origin = player.method_24515();
        for (class_2338 pos : class_2338.method_25996((class_2338)origin, (int)4, (int)4, (int)4)) {
            if (!player.method_51469().method_8320(pos).method_27852(class_2246.field_10083)) continue;
            return true;
        }
        return false;
    }

    private static void consumeIngredients(class_1263 inventory, TailorRecipe recipe) {
        for (BlacksmithRecipeIngredient ingredient : recipe.ingredients()) {
            int remaining = ingredient.count();
            for (int slot = 0; slot < inventory.method_5439() && remaining > 0; ++slot) {
                class_1799 stack = inventory.method_5438(slot);
                if (!ingredient.ingredient().method_8093(stack)) continue;
                int taken = Math.min(remaining, stack.method_7947());
                stack.method_7934(taken);
                remaining -= taken;
            }
        }
    }

    private static int count(class_1263 inventory, BlacksmithRecipeIngredient ingredient) {
        int count = 0;
        for (int slot = 0; slot < inventory.method_5439(); ++slot) {
            class_1799 stack = inventory.method_5438(slot);
            if (!ingredient.ingredient().method_8093(stack)) continue;
            count += stack.method_7947();
        }
        return count;
    }

    private static boolean hasDye(class_1263 inventory, String dyeId) {
        if ("none".equals(dyeId)) {
            return true;
        }
        class_1767 color = class_1767.method_7793((String)dyeId, null);
        return color != null && GotTailors.countDye(inventory, color) >= 4;
    }

    private static void consumeDye(class_1263 inventory, String dyeId) {
        if ("none".equals(dyeId)) {
            return;
        }
        class_1767 color = class_1767.method_7793((String)dyeId, null);
        if (color == null) {
            return;
        }
        class_1769 dyeItem = class_1769.method_7803((class_1767)color);
        int remaining = 4;
        for (int slot = 0; slot < inventory.method_5439() && remaining > 0; ++slot) {
            class_1799 stack = inventory.method_5438(slot);
            if (!stack.method_31574((class_1792)dyeItem)) continue;
            int taken = Math.min(remaining, stack.method_7947());
            stack.method_7934(taken);
            remaining -= taken;
        }
    }

    private static int countDye(class_1263 inventory, class_1767 color) {
        class_1769 dyeItem = class_1769.method_7803((class_1767)color);
        int count = 0;
        for (int slot = 0; slot < inventory.method_5439(); ++slot) {
            class_1799 stack = inventory.method_5438(slot);
            if (!stack.method_31574((class_1792)dyeItem)) continue;
            count += stack.method_7947();
        }
        return count;
    }

    private static boolean isValidSlot(String slot) {
        return Set.of("shirt", "pants").contains(slot);
    }

    private static String normalizeDye(String dyeId) {
        if (dyeId == null || dyeId.isBlank()) {
            return "none";
        }
        String value = dyeId.trim().toLowerCase(Locale.ROOT);
        return "none".equals(value) || class_1767.method_7793((String)value, null) != null ? value : "none";
    }

    public static enum DiscoveryResult {
        DISCOVERED,
        ALREADY_KNOWN,
        INVALID;

    }
}

