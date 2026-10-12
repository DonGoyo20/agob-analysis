/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3298
 */
package trep.got.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1011;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3298;

@Environment(value=EnvType.CLIENT)
public final class ClientTextureHelper {
    private static final Map<class_2960, TextureSize> TEXTURE_SIZE_CACHE = new ConcurrentHashMap<class_2960, TextureSize>();
    private static final Map<class_2960, TextureSize> DYNAMIC_TEXTURE_SIZE_CACHE = new ConcurrentHashMap<class_2960, TextureSize>();

    private ClientTextureHelper() {
    }

    public static TextureSize resolveTextureSize(class_310 client, class_2960 texture) {
        if (client == null || texture == null) {
            return TextureSize.DEFAULT;
        }
        TextureSize dynamicSize = DYNAMIC_TEXTURE_SIZE_CACHE.get(texture);
        if (dynamicSize != null) {
            return dynamicSize;
        }
        return TEXTURE_SIZE_CACHE.computeIfAbsent(texture, ignored -> client.method_1478().method_14486(texture).map(ClientTextureHelper::readTextureSize).orElse(TextureSize.DEFAULT));
    }

    public static void registerDynamicTextureSize(class_2960 texture, int width, int height) {
        if (texture == null || width <= 0 || height <= 0) {
            return;
        }
        DYNAMIC_TEXTURE_SIZE_CACHE.put(texture, new TextureSize(width, height));
    }

    public static void clearDynamicTextureSizes() {
        DYNAMIC_TEXTURE_SIZE_CACHE.clear();
    }

    /*
     * Enabled aggressive exception aggregation
     */
    private static TextureSize readTextureSize(class_3298 resource) {
        try (InputStream stream = resource.method_14482();){
            TextureSize textureSize;
            block14: {
                class_1011 image = class_1011.method_4309((InputStream)stream);
                try {
                    textureSize = new TextureSize(image.method_4307(), image.method_4323());
                    if (image == null) break block14;
                }
                catch (Throwable throwable) {
                    if (image != null) {
                        try {
                            image.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                image.close();
            }
            return textureSize;
        }
        catch (IOException e) {
            return TextureSize.DEFAULT;
        }
    }

    @Environment(value=EnvType.CLIENT)
    public record TextureSize(int width, int height) {
        public static final TextureSize DEFAULT = new TextureSize(16, 16);
    }
}

