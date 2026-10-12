/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import java.awt.image.BufferedImage;
import java.util.Set;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import org.jetbrains.annotations.Nullable;
import trep.got.registry.castle.overlay.CastleOverlayDataLoader;

final class CastleMapResourceHelper {
    private CastleMapResourceHelper() {
    }

    static BufferedImage loadImage(String resourcePath, ClassLoader classLoader) {
        return CastleOverlayDataLoader.loadImage(resourcePath, classLoader);
    }

    static Set<Integer> loadKnownMapBiomeColors(String dimensionPath, ClassLoader classLoader) {
        return CastleOverlayDataLoader.loadMapColors(dimensionPath, false, classLoader);
    }

    static Set<Integer> loadWaterMapColors(String dimensionPath, ClassLoader classLoader) {
        return CastleOverlayDataLoader.loadMapColors(dimensionPath, true, classLoader);
    }

    static class_2338 pixelToWorld(int pixelX, int pixelZ) {
        int worldX = (pixelX - 1500) * 32 + 16;
        int worldZ = (pixelZ - 1500) * 32 + 16;
        return new class_2338(worldX, 0, worldZ);
    }

    static int worldToMapPixel(int worldCoordinate) {
        return Math.floorDiv(worldCoordinate + 48000, 32);
    }

    static boolean isWaterMapChunk(class_1923 chunkPos, BufferedImage worldMapImage, String worldMapPath, Set<Integer> knownMapBiomeColors, Set<Integer> waterMapColors, ClassLoader classLoader) {
        for (int sampleX : new int[]{chunkPos.method_8326(), chunkPos.method_8326() + 8, chunkPos.method_8327()}) {
            for (int sampleZ : new int[]{chunkPos.method_8328(), chunkPos.method_8328() + 8, chunkPos.method_8329()}) {
                if (!CastleMapResourceHelper.isWaterMapPixel(CastleMapResourceHelper.worldToMapPixel(sampleX), CastleMapResourceHelper.worldToMapPixel(sampleZ), worldMapImage, worldMapPath, knownMapBiomeColors, waterMapColors, classLoader)) continue;
                return true;
            }
        }
        return false;
    }

    static boolean isWaterMapPixel(int pixelX, int pixelZ, BufferedImage worldMapImage, String worldMapPath, Set<Integer> knownMapBiomeColors, Set<Integer> waterMapColors, ClassLoader classLoader) {
        BufferedImage image;
        BufferedImage bufferedImage = image = worldMapImage != null ? worldMapImage : CastleMapResourceHelper.loadImage(worldMapPath, classLoader);
        if (pixelX < 0 || pixelZ < 0 || pixelX >= image.getWidth() || pixelZ >= image.getHeight()) {
            return true;
        }
        int argb = image.getRGB(pixelX, pixelZ);
        if (argb >>> 24 == 0) {
            return true;
        }
        int color = argb & 0xFFFFFF;
        if (waterMapColors.contains(color)) {
            return true;
        }
        return !knownMapBiomeColors.contains(color);
    }

    @Nullable
    static class_2338 mapColorOrigin(int targetColor, BufferedImage worldMapImage, String worldMapPath, ClassLoader classLoader) {
        BufferedImage image = worldMapImage != null ? worldMapImage : CastleMapResourceHelper.loadImage(worldMapPath, classLoader);
        long sumX = 0L;
        long sumZ = 0L;
        int count = 0;
        for (int pixelX = 0; pixelX < image.getWidth(); ++pixelX) {
            for (int pixelZ = 0; pixelZ < image.getHeight(); ++pixelZ) {
                int argb = image.getRGB(pixelX, pixelZ);
                if (argb >>> 24 == 0 || (argb & 0xFFFFFF) != targetColor) continue;
                sumX += (long)pixelX;
                sumZ += (long)pixelZ;
                ++count;
            }
        }
        if (count <= 0) {
            return null;
        }
        return CastleMapResourceHelper.pixelToWorld(Math.toIntExact(Math.round((double)sumX / (double)count)), Math.toIntExact(Math.round((double)sumZ / (double)count)));
    }
}

