/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.world.map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;
import net.minecraft.class_2960;

public final class MapImage {
    private static final Map<String, MapImage> CACHE = new ConcurrentHashMap<String, MapImage>();
    private final int width;
    private final int height;
    private final int[] argb;

    private MapImage(int width, int height, int[] argb) {
        this.width = width;
        this.height = height;
        this.argb = argb;
    }

    public static MapImage load(String resourcePath) {
        return CACHE.computeIfAbsent(resourcePath, MapImage::loadUnchecked);
    }

    public static MapImage load(class_2960 resourceId) {
        return MapImage.load("assets/" + resourceId.method_12836() + "/" + resourceId.method_12832());
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public boolean contains(int x, int z) {
        return x >= 0 && z >= 0 && x < this.width && z < this.height;
    }

    public int argb(int x, int z) {
        return this.argb[this.indexOf(x, z)];
    }

    public int rgb(int x, int z) {
        return this.argb(x, z) & 0xFFFFFF;
    }

    public boolean isOpaque(int x, int z) {
        return (this.argb(x, z) >>> 24 & 0xFF) > 0;
    }

    private int indexOf(int x, int z) {
        return z * this.width + x;
    }

    private static MapImage loadUnchecked(String resourcePath) {
        MapImage mapImage;
        block10: {
            InputStream stream = MapImage.class.getClassLoader().getResourceAsStream(resourcePath);
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing map resource " + resourcePath);
                }
                BufferedImage image = ImageIO.read(stream);
                if (image == null) {
                    throw new IllegalStateException("Failed to decode map resource " + resourcePath);
                }
                int width = image.getWidth();
                int height = image.getHeight();
                int[] argb = image.getRGB(0, 0, width, height, null, 0, width);
                mapImage = new MapImage(width, height, argb);
                if (stream == null) break block10;
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
                    throw new IllegalStateException("Failed to load map resource " + resourcePath, e);
                }
            }
            stream.close();
        }
        return mapImage;
    }
}

