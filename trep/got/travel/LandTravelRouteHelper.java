/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package trep.got.travel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.imageio.ImageIO;

public final class LandTravelRouteHelper {
    public static final int MAX_LAND_TRAVEL_DISTANCE_BLOCKS = 10000;
    private static final String WORLD_MAP_PATH = "assets/agob/map.png";
    private static final String DIMENSION_PATH = "data/agob/dimension/iceandfire.json";
    private static BufferedImage worldMapImage;
    private static Set<Integer> waterMapColors;
    private static Set<Integer> knownMapColors;

    private LandTravelRouteHelper() {
    }

    public static boolean canTravelByLand(int fromX, int fromZ, int toX, int toZ) {
        double dx = toX - fromX;
        double dz = toZ - fromZ;
        if (dx * dx + dz * dz > 1.0E8) {
            return false;
        }
        int x0 = LandTravelRouteHelper.worldToMapPixel(fromX);
        int z0 = LandTravelRouteHelper.worldToMapPixel(fromZ);
        int x1 = LandTravelRouteHelper.worldToMapPixel(toX);
        int z1 = LandTravelRouteHelper.worldToMapPixel(toZ);
        int steps = Math.max(Math.abs(x1 - x0), Math.abs(z1 - z0));
        if (steps == 0) {
            return LandTravelRouteHelper.isLandPixel(x0, z0);
        }
        for (int index = 0; index <= steps; ++index) {
            int sampleZ;
            double progress = (double)index / (double)steps;
            int sampleX = (int)Math.round((double)x0 + (double)(x1 - x0) * progress);
            if (LandTravelRouteHelper.isLandPixel(sampleX, sampleZ = (int)Math.round((double)z0 + (double)(z1 - z0) * progress))) continue;
            return false;
        }
        return true;
    }

    private static int worldToMapPixel(int worldCoordinate) {
        return Math.floorDiv(worldCoordinate + 48000, 32);
    }

    private static boolean isLandPixel(int pixelX, int pixelZ) {
        BufferedImage image = LandTravelRouteHelper.worldMapImage();
        if (pixelX < 0 || pixelZ < 0 || pixelX >= image.getWidth() || pixelZ >= image.getHeight()) {
            return false;
        }
        int argb = image.getRGB(pixelX, pixelZ);
        if (argb >>> 24 == 0) {
            return false;
        }
        int color = argb & 0xFFFFFF;
        return LandTravelRouteHelper.knownMapColors().contains(color) && !LandTravelRouteHelper.waterMapColors().contains(color);
    }

    private static BufferedImage worldMapImage() {
        if (worldMapImage == null) {
            worldMapImage = LandTravelRouteHelper.loadImage(WORLD_MAP_PATH);
        }
        return worldMapImage;
    }

    private static Set<Integer> waterMapColors() {
        LandTravelRouteHelper.ensureBiomeColorsLoaded();
        return waterMapColors;
    }

    private static Set<Integer> knownMapColors() {
        LandTravelRouteHelper.ensureBiomeColorsLoaded();
        return knownMapColors;
    }

    private static void ensureBiomeColorsLoaded() {
        if (!waterMapColors.isEmpty() && !knownMapColors.isEmpty()) {
            return;
        }
        try (InputStream stream = LandTravelRouteHelper.class.getClassLoader().getResourceAsStream(DIMENSION_PATH);){
            if (stream == null) {
                throw new IllegalStateException("Missing land travel dimension resource data/agob/dimension/iceandfire.json");
            }
            JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            JsonObject generator = root.getAsJsonObject("generator");
            JsonObject biomeSource = generator.getAsJsonObject("biome_source");
            LinkedHashSet<Integer> water = new LinkedHashSet<Integer>();
            LinkedHashSet<Integer> known = new LinkedHashSet<Integer>();
            for (JsonElement element : biomeSource.getAsJsonArray("entries")) {
                JsonObject entry = element.getAsJsonObject();
                JsonArray rgb = entry.getAsJsonArray("rgb");
                int color = rgb.get(0).getAsInt() << 16 | rgb.get(1).getAsInt() << 8 | rgb.get(2).getAsInt();
                known.add(color);
                if (!"agob:sea".equals(entry.get("biome").getAsString())) continue;
                water.add(color);
            }
            waterMapColors = Set.copyOf(water);
            knownMapColors = Set.copyOf(known);
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to load land travel biome colors from data/agob/dimension/iceandfire.json", e);
        }
    }

    private static BufferedImage loadImage(String resourcePath) {
        BufferedImage bufferedImage;
        block9: {
            InputStream stream = LandTravelRouteHelper.class.getClassLoader().getResourceAsStream(resourcePath);
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing land travel map resource " + resourcePath);
                }
                bufferedImage = ImageIO.read(stream);
                if (stream == null) break block9;
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
                    throw new IllegalStateException("Failed to load land travel map resource " + resourcePath, e);
                }
            }
            stream.close();
        }
        return bufferedImage;
    }

    static {
        waterMapColors = Set.of();
        knownMapColors = Set.of();
    }
}

