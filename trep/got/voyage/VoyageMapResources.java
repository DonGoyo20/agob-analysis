/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package trep.got.voyage;

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

public final class VoyageMapResources {
    private static final String WORLD_MAP_PATH = "assets/agob/map.png";
    private static final String DIMENSION_PATH = "data/agob/dimension/iceandfire.json";
    private static BufferedImage worldMapImage;
    private static Set<Integer> waterMapColors;

    private VoyageMapResources() {
    }

    public static BufferedImage worldMapImage() {
        if (worldMapImage == null) {
            worldMapImage = VoyageMapResources.loadImage(WORLD_MAP_PATH);
        }
        return worldMapImage;
    }

    public static Set<Integer> waterMapColors() {
        if (waterMapColors.isEmpty()) {
            waterMapColors = VoyageMapResources.loadWaterMapColors();
        }
        return waterMapColors;
    }

    private static BufferedImage loadImage(String resourcePath) {
        BufferedImage bufferedImage;
        block9: {
            InputStream stream = VoyageMapResources.class.getClassLoader().getResourceAsStream(resourcePath);
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing voyage map resource " + resourcePath);
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
                    throw new IllegalStateException("Failed to load voyage map resource " + resourcePath, e);
                }
            }
            stream.close();
        }
        return bufferedImage;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Set<Integer> loadWaterMapColors() {
        try (InputStream stream = VoyageMapResources.class.getClassLoader().getResourceAsStream(DIMENSION_PATH);){
            if (stream == null) {
                Set<Integer> set2 = Set.of();
                return set2;
            }
            JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            JsonObject generator = root.getAsJsonObject("generator");
            JsonObject biomeSource = generator.getAsJsonObject("biome_source");
            LinkedHashSet<Integer> colors = new LinkedHashSet<Integer>();
            for (JsonElement element : biomeSource.getAsJsonArray("entries")) {
                JsonObject entry = element.getAsJsonObject();
                if (!"agob:sea".equals(entry.get("biome").getAsString())) continue;
                JsonArray rgb = entry.getAsJsonArray("rgb");
                int color = rgb.get(0).getAsInt() << 16 | rgb.get(1).getAsInt() << 8 | rgb.get(2).getAsInt();
                colors.add(color);
            }
            Set set = Set.copyOf(colors);
            return set;
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to load water map colors from data/agob/dimension/iceandfire.json", e);
        }
    }

    static {
        waterMapColors = Set.of();
    }
}

