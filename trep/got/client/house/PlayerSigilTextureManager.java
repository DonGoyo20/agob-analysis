/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 *  net.minecraft.class_1043
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 */
package trep.got.client.house;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import trep.got.client.house.PlayerSigilTemplate;
import trep.got.client.util.ClientTextureHelper;
import trep.got.network.PlayerHouseStatePayload;

@Environment(value=EnvType.CLIENT)
public final class PlayerSigilTextureManager {
    private static final Map<TextureKey, class_2960> CACHE = new HashMap<TextureKey, class_2960>();

    private PlayerSigilTextureManager() {
    }

    public static class_2960 getTextureId(PlayerHouseStatePayload.Entry entry) {
        TextureKey key = new TextureKey(entry.houseId(), Arrays.hashCode(entry.sigilColors()), Arrays.copyOf(entry.sigilColors(), entry.sigilColors().length));
        return CACHE.computeIfAbsent(key, PlayerSigilTextureManager::createTexture);
    }

    public static void clear() {
        CACHE.clear();
        ClientTextureHelper.clearDynamicTextureSizes();
    }

    private static class_2960 createTexture(TextureKey key) {
        class_310 client = class_310.method_1551();
        try {
            class_1011 image = PlayerSigilTemplate.createImage(client, key.sigilColors());
            int width = image.method_4307();
            int height = image.method_4323();
            class_2960 textureId = client.method_1531().method_4617("sigil/" + key.houseId(), new class_1043(image));
            ClientTextureHelper.registerDynamicTextureSize(textureId, width, height);
            return textureId;
        }
        catch (IOException exception) {
            throw new IllegalStateException("Failed to build player sigil texture for " + key.houseId(), exception);
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record TextureKey(String houseId, int colorsHash, int[] sigilColors) {
    }
}

