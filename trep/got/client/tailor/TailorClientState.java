/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 *  net.minecraft.class_437
 */
package trep.got.client.tailor;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_437;
import trep.got.character.PlayerSkinBuilderProfile;
import trep.got.character.PlayerTailorProfile;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.screen.TailorScreen;
import trep.got.network.TailorStatePayload;
import trep.got.skill.SkillAttributeType;

@Environment(value=EnvType.CLIENT)
public final class TailorClientState {
    private static PlayerSkinBuilderProfile skinBuilderProfile = PlayerSkinBuilderProfile.DEFAULT;
    private static Set<String> discoveredClothing = Set.of();
    private static Set<String> unlockedClothing = Set.of();
    private static Set<String> unlockedDyeClothing = Set.of();
    private static Map<String, Map<SkillAttributeType, Integer>> clothingAttributes = Map.of();

    private TailorClientState() {
    }

    public static void apply(TailorStatePayload payload, class_310 client) {
        skinBuilderProfile = payload.skinBuilderProfile();
        CharacterSelectionClientState.setSkinBuilderProfile(skinBuilderProfile);
        discoveredClothing = Arrays.stream(payload.discoveredClothing()).collect(Collectors.toUnmodifiableSet());
        unlockedClothing = Arrays.stream(payload.unlockedClothing()).collect(Collectors.toUnmodifiableSet());
        unlockedDyeClothing = Arrays.stream(payload.unlockedDyeClothing()).collect(Collectors.toUnmodifiableSet());
        clothingAttributes = TailorClientState.parseClothingAttributes(payload.clothingAttributes());
        client.execute(() -> {
            if (payload.openScreen() && !(client.field_1755 instanceof TailorScreen)) {
                client.method_1507((class_437)new TailorScreen());
            }
        });
    }

    public static PlayerSkinBuilderProfile getSkinBuilderProfile() {
        return skinBuilderProfile;
    }

    public static boolean isDiscovered(String slot, String clothingId) {
        return discoveredClothing.contains(PlayerTailorProfile.key(slot, clothingId));
    }

    public static boolean isUnlocked(String slot, String clothingId) {
        return unlockedClothing.contains(PlayerTailorProfile.key(slot, clothingId));
    }

    public static boolean isDyeUnlocked(String slot, String clothingId, String dyeId) {
        return unlockedDyeClothing.contains(PlayerTailorProfile.dyeKey(slot, clothingId, dyeId));
    }

    public static Map<SkillAttributeType, Integer> getClothingAttributes(String clothingId) {
        if (clothingId == null || clothingId.isBlank()) {
            return Map.of();
        }
        return clothingAttributes.getOrDefault(clothingId.trim().toLowerCase(Locale.ROOT), Map.of());
    }

    private static Map<String, Map<SkillAttributeType, Integer>> parseClothingAttributes(String[] values) {
        HashMap parsed = new HashMap();
        for (String value : values) {
            String[] parts;
            if (value == null || value.isBlank() || (parts = value.split("\\|", 2)).length != 2 || parts[0].isBlank()) continue;
            EnumMap<SkillAttributeType, Integer> attributes = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
            for (String pair : parts[1].split(",")) {
                SkillAttributeType type;
                String[] attributeParts = pair.split("=", 2);
                if (attributeParts.length != 2 || (type = SkillAttributeType.byId(attributeParts[0])) == null) continue;
                try {
                    int amount = Integer.parseInt(attributeParts[1]);
                    if (amount <= 0) continue;
                    attributes.put(type, amount);
                }
                catch (NumberFormatException numberFormatException) {
                    // empty catch block
                }
            }
            if (attributes.isEmpty()) continue;
            parsed.put(parts[0].trim().toLowerCase(Locale.ROOT), Map.copyOf(attributes));
        }
        return Map.copyOf(parsed);
    }
}

