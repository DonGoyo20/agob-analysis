/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 */
package trep.got.client.house;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1011;
import net.minecraft.class_2960;
import net.minecraft.class_310;

@Environment(value=EnvType.CLIENT)
public final class PlayerSigilTemplate {
    public static final class_2960 BASE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_blank.png");
    private static CachedTemplate cachedTemplate;

    private PlayerSigilTemplate() {
    }

    public static int drawablePixelCount(class_310 client) {
        return PlayerSigilTemplate.resolve(client).drawableIndices().length;
    }

    public static int width(class_310 client) {
        return PlayerSigilTemplate.resolve(client).width();
    }

    public static int height(class_310 client) {
        return PlayerSigilTemplate.resolve(client).height();
    }

    public static int drawableColorIndex(class_310 client, int x, int y) {
        CachedTemplate template = PlayerSigilTemplate.resolve(client);
        if (x < 0 || y < 0 || x >= template.width() || y >= template.height()) {
            return -1;
        }
        return template.drawableOrderByPixelIndex()[x + y * template.width()];
    }

    public static class_1011 createImage(class_310 client, int[] sigilColors) throws IOException {
        CachedTemplate template = PlayerSigilTemplate.resolve(client);
        class_1011 output = new class_1011(template.width(), template.height(), true);
        for (int index = 0; index < template.basePixels().length; ++index) {
            int x = index % template.width();
            int y = index / template.width();
            output.method_4305(x, y, template.basePixels()[index]);
        }
        int[] drawableIndices = template.drawableIndices();
        for (int index = 0; index < drawableIndices.length && index < sigilColors.length; ++index) {
            int color = sigilColors[index];
            if (color == 0) continue;
            int pixelIndex = drawableIndices[index];
            output.method_4305(pixelIndex % template.width(), pixelIndex / template.width(), PlayerSigilTemplate.argbToNativeColor(color));
        }
        return output;
    }

    /*
     * Enabled aggressive exception aggregation
     */
    private static CachedTemplate resolve(class_310 client) {
        if (cachedTemplate != null) {
            return cachedTemplate;
        }
        try (InputStream stream = client.method_1478().open(BASE_TEXTURE);){
            CachedTemplate cachedTemplate;
            block17: {
                class_1011 image = class_1011.method_4309((InputStream)stream);
                try {
                    int width = image.method_4307();
                    int height = image.method_4323();
                    int[] basePixels = new int[width * height];
                    int[] drawableScratch = new int[width * height];
                    int[] drawableOrderByPixelIndex = new int[width * height];
                    Arrays.fill(drawableOrderByPixelIndex, -1);
                    int drawableCount = 0;
                    for (int y = 0; y < height; ++y) {
                        for (int x = 0; x < width; ++x) {
                            int color;
                            int index = x + y * width;
                            basePixels[index] = color = image.method_4315(x, y);
                            if (!PlayerSigilTemplate.isDrawable(color)) continue;
                            drawableScratch[drawableCount++] = index;
                            drawableOrderByPixelIndex[index] = drawableCount - 1;
                        }
                    }
                    int[] drawableIndices = new int[drawableCount];
                    System.arraycopy(drawableScratch, 0, drawableIndices, 0, drawableCount);
                    cachedTemplate = PlayerSigilTemplate.cachedTemplate = new CachedTemplate(width, height, basePixels, drawableIndices, drawableOrderByPixelIndex);
                    if (image == null) break block17;
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
            return cachedTemplate;
        }
        catch (IOException exception) {
            throw new IllegalStateException("Failed to load player sigil template", exception);
        }
    }

    private static boolean isDrawable(int argb) {
        int alpha = argb >>> 24 & 0xFF;
        int red = argb >>> 16 & 0xFF;
        int green = argb >>> 8 & 0xFF;
        int blue = argb & 0xFF;
        return alpha >= 200 && red >= 240 && green >= 240 && blue >= 240;
    }

    private static int argbToNativeColor(int argb) {
        int alpha = argb >>> 24 & 0xFF;
        int red = argb >>> 16 & 0xFF;
        int green = argb >>> 8 & 0xFF;
        int blue = argb & 0xFF;
        return alpha << 24 | blue << 16 | green << 8 | red;
    }

    @Environment(value=EnvType.CLIENT)
    private record CachedTemplate(int width, int height, int[] basePixels, int[] drawableIndices, int[] drawableOrderByPixelIndex) {
    }
}

