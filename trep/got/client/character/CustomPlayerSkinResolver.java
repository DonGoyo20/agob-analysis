/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 *  net.minecraft.class_1043
 *  net.minecraft.class_1044
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_8685
 *  net.minecraft.class_8685$class_7920
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
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_8685;
import trep.got.GotMod;

@Environment(value=EnvType.CLIENT)
public final class CustomPlayerSkinResolver {
    private static final int SKIN_WIDTH = 64;
    private static final int SKIN_HEIGHT = 64;
    private static final int LEGACY_SKIN_HEIGHT = 32;
    private static final Map<String, class_8685> LOADED_SKINS = new ConcurrentHashMap<String, class_8685>();

    private CustomPlayerSkinResolver() {
    }

    public static class_8685 resolve(class_310 client, class_2960 texture, boolean smallArms, class_8685 fallback, String cacheNamespace) {
        if (texture == null) {
            return fallback;
        }
        String cacheKey = cacheNamespace + "|" + String.valueOf(texture) + "|" + smallArms;
        return LOADED_SKINS.computeIfAbsent(cacheKey, ignored -> CustomPlayerSkinResolver.load(client, texture, smallArms, fallback, cacheNamespace));
    }

    public static class_8685 resolveComposite(class_310 client, String compositeKey, boolean smallArms, class_8685 fallback, String cacheNamespace, NativeImageSupplier supplier) {
        if (compositeKey == null || compositeKey.isBlank() || supplier == null) {
            return fallback;
        }
        String cacheKey = cacheNamespace + "|" + compositeKey + "|" + smallArms;
        return LOADED_SKINS.computeIfAbsent(cacheKey, ignored -> CustomPlayerSkinResolver.loadComposite(client, compositeKey, smallArms, fallback, cacheNamespace, supplier));
    }

    private static class_8685 load(class_310 client, class_2960 texture, boolean smallArms, class_8685 fallback, String cacheNamespace) {
        class_8685 class_86852;
        block8: {
            InputStream stream = client.method_1478().open(texture);
            try {
                class_1011 image = class_1011.method_4309((InputStream)stream);
                class_86852 = CustomPlayerSkinResolver.registerLoadedSkin(client, texture.toString(), texture.method_12836() + "/" + texture.method_12832().replace('/', '_').replace('.', '_'), image, smallArms, fallback, cacheNamespace);
                if (stream == null) break block8;
            }
            catch (Throwable throwable) {
                try {
                    if (stream != null) {
                        try {
                            stream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (IOException e) {
                    GotMod.LOGGER.warn("Failed to load player skin {}", (Object)texture, (Object)e);
                    return fallback;
                }
            }
            stream.close();
        }
        return class_86852;
    }

    private static class_8685 loadComposite(class_310 client, String compositeKey, boolean smallArms, class_8685 fallback, String cacheNamespace, NativeImageSupplier supplier) {
        try {
            class_1011 image = supplier.load();
            if (image == null) {
                return fallback;
            }
            return CustomPlayerSkinResolver.registerLoadedSkin(client, compositeKey, CustomPlayerSkinResolver.sanitize(compositeKey), image, smallArms, fallback, cacheNamespace);
        }
        catch (IOException e) {
            GotMod.LOGGER.warn("Failed to load composite player skin {}", (Object)compositeKey, (Object)e);
            return fallback;
        }
    }

    private static class_8685 registerLoadedSkin(class_310 client, String logKey, String dynamicKey, class_1011 image, boolean smallArms, class_8685 fallback, String cacheNamespace) {
        class_1011 remappedImage = CustomPlayerSkinResolver.remapSkin(logKey, image);
        if (remappedImage == null) {
            return fallback;
        }
        String modelKey = smallArms ? "slim" : "wide";
        class_2960 dynamicTexture = class_2960.method_60655((String)"agob", (String)(cacheNamespace + "/" + modelKey + "/" + dynamicKey));
        client.method_1531().method_4616(dynamicTexture, (class_1044)new class_1043(remappedImage));
        return new class_8685(dynamicTexture, "", null, null, smallArms ? class_8685.class_7920.field_41122 : class_8685.class_7920.field_41123, true);
    }

    private static class_1011 remapSkin(String textureKey, class_1011 image) {
        int width = image.method_4307();
        int height = image.method_4323();
        if (width != 64 || height != 32 && height != 64) {
            GotMod.LOGGER.warn("Ignoring custom player skin {} with unsupported size {}x{}", new Object[]{textureKey, width, height});
            image.close();
            return null;
        }
        if (height == 64) {
            CustomPlayerSkinResolver.stripAlpha(image, 0, 0, 32, 16);
            CustomPlayerSkinResolver.stripAlpha(image, 0, 16, 64, 32);
            CustomPlayerSkinResolver.stripAlpha(image, 16, 48, 48, 64);
            return image;
        }
        class_1011 converted = new class_1011(64, 64, true);
        converted.method_4317(image);
        image.close();
        converted.method_4326(0, 32, 64, 32, 0);
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
        CustomPlayerSkinResolver.stripAlpha(converted, 0, 0, 32, 16);
        CustomPlayerSkinResolver.stripColor(converted, 32, 0, 64, 32);
        CustomPlayerSkinResolver.stripAlpha(converted, 0, 16, 64, 32);
        CustomPlayerSkinResolver.stripAlpha(converted, 16, 48, 48, 64);
        return converted;
    }

    private static void stripColor(class_1011 image, int x1, int y1, int x2, int y2) {
        int y;
        int x;
        for (x = x1; x < x2; ++x) {
            for (y = y1; y < y2; ++y) {
                int color = image.method_4315(x, y);
                if ((color >> 24 & 0xFF) >= 128) continue;
                return;
            }
        }
        for (x = x1; x < x2; ++x) {
            for (y = y1; y < y2; ++y) {
                image.method_4305(x, y, image.method_4315(x, y) & 0xFFFFFF);
            }
        }
    }

    private static void stripAlpha(class_1011 image, int x1, int y1, int x2, int y2) {
        for (int x = x1; x < x2; ++x) {
            for (int y = y1; y < y2; ++y) {
                image.method_4305(x, y, image.method_4315(x, y) | 0xFF000000);
            }
        }
    }

    private static String sanitize(String value) {
        return value.toLowerCase(Locale.ROOT).replace(':', '_').replace('|', '_').replace('/', '_').replace('\\', '_').replace('.', '_').replace(' ', '_');
    }

    @FunctionalInterface
    @Environment(value=EnvType.CLIENT)
    public static interface NativeImageSupplier {
        public class_1011 load() throws IOException;
    }
}

