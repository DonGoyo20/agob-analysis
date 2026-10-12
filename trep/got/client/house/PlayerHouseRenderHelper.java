/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2960
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.house;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import org.jetbrains.annotations.Nullable;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.house.PlayerHouseClientState;
import trep.got.client.house.PlayerSigilTextureManager;
import trep.got.network.PlayerHouseStatePayload;
import trep.got.registry.GotCharacterOptions;

@Environment(value=EnvType.CLIENT)
public final class PlayerHouseRenderHelper {
    private PlayerHouseRenderHelper() {
    }

    public static String resolveDisplayName(String houseId, String kingdomId) {
        CharacterSelectionOption option = GotCharacterOptions.get(houseId);
        if (option != null && !option.houseDisplayName().isBlank()) {
            return option.houseDisplayName();
        }
        PlayerHouseStatePayload.Entry customHouse = PlayerHouseClientState.getByHouseId(houseId);
        if (customHouse != null && !customHouse.displayName().isBlank()) {
            return customHouse.displayName();
        }
        CharacterSelectionOption fallback = PlayerHouseRenderHelper.resolveFallbackOption(kingdomId);
        return fallback == null ? "" : fallback.houseDisplayName();
    }

    @Nullable
    public static class_2960 resolveTexture(String houseId, String kingdomId) {
        CharacterSelectionOption option = GotCharacterOptions.get(houseId);
        if (option != null) {
            return option.texture();
        }
        PlayerHouseStatePayload.Entry customHouse = PlayerHouseClientState.getByHouseId(houseId);
        if (customHouse != null) {
            return PlayerSigilTextureManager.getTextureId(customHouse);
        }
        CharacterSelectionOption fallback = PlayerHouseRenderHelper.resolveFallbackOption(kingdomId);
        return fallback == null ? null : fallback.texture();
    }

    public static boolean isCustomHouse(String houseId) {
        return PlayerHouseClientState.getByHouseId(houseId) != null;
    }

    @Nullable
    private static CharacterSelectionOption resolveFallbackOption(String kingdomId) {
        class_2960 kingdomIdentifier = class_2960.method_12829((String)kingdomId);
        return kingdomIdentifier == null ? null : GotCharacterOptions.getByKingdomId(kingdomIdentifier);
    }
}

