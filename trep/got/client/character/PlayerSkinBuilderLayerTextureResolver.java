/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 *  net.minecraft.class_1043
 *  net.minecraft.class_1044
 *  net.minecraft.class_1767
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 */
package trep.got.client.character;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_1044;
import net.minecraft.class_1767;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import trep.got.GotMod;

@Environment(value=EnvType.CLIENT)
public final class PlayerSkinBuilderLayerTextureResolver {
    private static final int SKIN_WIDTH = 64;
    private static final int LEGACY_SKIN_HEIGHT = 32;
    private static final int SKIN_HEIGHT = 64;
    private static final Map<String, class_2960> RESOLVED = new ConcurrentHashMap<String, class_2960>();

    private PlayerSkinBuilderLayerTextureResolver() {
    }

    public static class_2960 resolve(class_310 client, class_2960 texture) {
        return PlayerSkinBuilderLayerTextureResolver.resolve(client, texture, "none");
    }

    public static class_2960 resolve(class_310 client, class_2960 texture, String dyeId) {
        if (client == null || texture == null) {
            return texture;
        }
        String safeDyeId = PlayerSkinBuilderLayerTextureResolver.sanitizeDyeId(dyeId);
        return RESOLVED.computeIfAbsent(String.valueOf(texture) + "|" + safeDyeId, ignored -> PlayerSkinBuilderLayerTextureResolver.load(client, texture, safeDyeId));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static class_2960 load(class_310 client, class_2960 texture, String dyeId) {
        try (InputStream stream = client.method_1478().open(texture);){
            class_1011 converted;
            class_1011 image = class_1011.method_4309((InputStream)stream);
            if (image.method_4307() == 64 && image.method_4323() == 64 && "none".equals(dyeId)) {
                image.close();
                class_2960 class_29602 = texture;
                return class_29602;
            }
            if (image.method_4307() != 64 || image.method_4323() != 32 && image.method_4323() != 64) {
                image.close();
                class_2960 class_29603 = texture;
                return class_29603;
            }
            class_1011 class_10112 = converted = image.method_4323() == 32 ? PlayerSkinBuilderLayerTextureResolver.convertLegacyLayer(image) : image;
            if (converted != image) {
                image.close();
            }
            PlayerSkinBuilderLayerTextureResolver.tintLayer(converted, dyeId);
            class_2960 dynamicTexture = class_2960.method_60655((String)"agob", (String)("player_skin_builder_layer/" + PlayerSkinBuilderLayerTextureResolver.sanitize(dyeId + "/" + texture.method_12832())));
            client.method_1531().method_4616(dynamicTexture, (class_1044)new class_1043(converted));
            class_2960 class_29604 = dynamicTexture;
            return class_29604;
        }
        catch (IOException e) {
            GotMod.LOGGER.warn("Failed to normalize player skin builder layer {}", (Object)texture, (Object)e);
            return texture;
        }
    }

    public static void tintLayer(class_1011 image, String dyeId) {
        if (image == null || "none".equals(PlayerSkinBuilderLayerTextureResolver.sanitizeDyeId(dyeId))) {
            return;
        }
        class_1767 color = class_1767.method_7793((String)dyeId, null);
        if (color == null) {
            return;
        }
        int rgb = color.method_7787();
        int dyeRed = rgb >> 16 & 0xFF;
        int dyeGreen = rgb >> 8 & 0xFF;
        int dyeBlue = rgb & 0xFF;
        for (int x = 0; x < image.method_4307(); ++x) {
            for (int y = 0; y < image.method_4323(); ++y) {
                int pixel = image.method_4315(x, y);
                int alpha = pixel >>> 24 & 0xFF;
                if (alpha == 0) continue;
                int red = (pixel & 0xFF) * dyeRed / 255;
                int green = (pixel >> 8 & 0xFF) * dyeGreen / 255;
                int blue = (pixel >> 16 & 0xFF) * dyeBlue / 255;
                image.method_4305(x, y, alpha << 24 | blue << 16 | green << 8 | red);
            }
        }
    }

    public static class_1011 normalizeLayerImage(class_1011 image) {
        if (image == null || image.method_4307() != 64 || image.method_4323() != 32) {
            return image;
        }
        return PlayerSkinBuilderLayerTextureResolver.convertLegacyLayer(image);
    }

    private static class_1011 convertLegacyLayer(class_1011 image) {
        class_1011 converted = new class_1011(64, 64, true);
        for (int x = 0; x < 64; ++x) {
            for (int y = 0; y < 32; ++y) {
                converted.method_4305(x, y, image.method_4315(x, y));
            }
        }
        converted.method_4304(4, 16, 16, 32, 4, 4, true, false);
        converted.method_4304(8, 16, 16, 32, 4, 4, true, false);
        converted.method_4304(0, 20, 24, 32, 4, 12, true, false);
        converted.method_4304(4, 20, 16, 32, 4, 12, true, false);
        converted.method_4304(8, 20, 8, 32, 4, 12, true, false);
        converted.method_4304(12, 20, 16, 32, 4, 12, true, false);
        converted.method_4304(44, 16, -8, 32, 4, 4, true, false);
        converted.method_4304(48, 16, -8, 32, 4, 4, true, false);
        converted.method_4304(40, 20, 0, 32, 4, 12, true, false);
        converted.method_4304(44, 20, -8, 32, 4, 12, true, false);
        converted.method_4304(48, 20, -16, 32, 4, 12, true, false);
        converted.method_4304(52, 20, -8, 32, 4, 12, true, false);
        return converted;
    }

    private static String sanitizeDyeId(String dyeId) {
        if (dyeId == null || dyeId.isBlank()) {
            return "none";
        }
        String value = dyeId.trim().toLowerCase(Locale.ROOT);
        return "none".equals(value) || class_1767.method_7793((String)value, null) != null ? value : "none";
    }

    private static String sanitize(String value) {
        return value.toLowerCase(Locale.ROOT).replace('/', '_').replace('.', '_').replace(':', '_');
    }
}

