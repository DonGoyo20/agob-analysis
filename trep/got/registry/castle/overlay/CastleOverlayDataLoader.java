/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_2338
 */
package trep.got.registry.castle.overlay;

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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.imageio.ImageIO;
import net.minecraft.class_2338;
import trep.got.GotMod;
import trep.got.castle.CastleSubtype;
import trep.got.registry.castle.overlay.CastlePresetMetadata;
import trep.got.registry.castle.overlay.OverlayCastleSite;
import trep.got.registry.castle.overlay.OverlayPresetReference;
import trep.got.registry.castle.overlay.OverlayPresetReferences;

public final class CastleOverlayDataLoader {
    private CastleOverlayDataLoader() {
    }

    public static BufferedImage loadImage(String resourcePath, ClassLoader classLoader) {
        BufferedImage bufferedImage;
        block9: {
            InputStream stream = classLoader.getResourceAsStream(resourcePath);
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing castle overlay resource " + resourcePath);
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
                    throw new IllegalStateException("Failed to load castle overlay resource " + resourcePath, e);
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
    public static Set<Integer> loadMapColors(String dimensionPath, boolean waterOnly, ClassLoader classLoader) {
        try (InputStream stream = classLoader.getResourceAsStream(dimensionPath);){
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
                String biomeId = entry.get("biome").getAsString();
                boolean waterBiome = "agob:sea".equals(biomeId);
                if (waterOnly != waterBiome) continue;
                JsonArray rgb = entry.getAsJsonArray("rgb");
                int color = rgb.get(0).getAsInt() << 16 | rgb.get(1).getAsInt() << 8 | rgb.get(2).getAsInt();
                colors.add(color);
            }
            Set set = Set.copyOf(colors);
            return set;
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to load map biome colors from " + dimensionPath, e);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static OverlayPresetReferences loadOverlayPresetReferences(String definitionsPath, ClassLoader classLoader) {
        LinkedHashMap<Object, OverlayPresetReference> byLegacyId = new LinkedHashMap<Object, OverlayPresetReference>();
        LinkedHashMap<String, OverlayPresetReference> byCastleId = new LinkedHashMap<String, OverlayPresetReference>();
        try (InputStream stream = classLoader.getResourceAsStream(definitionsPath);){
            if (stream == null) {
                OverlayPresetReferences overlayPresetReferences2 = new OverlayPresetReferences(Map.of(), Map.of());
                return overlayPresetReferences2;
            }
            JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            JsonArray presets = root.has("overlay_presets") ? root.getAsJsonArray("overlay_presets") : new JsonArray();
            for (JsonElement element : presets) {
                JsonObject preset = element.getAsJsonObject();
                String castleId = preset.get("castle_id").getAsString();
                String definitionId = preset.has("definition_id") ? preset.get("definition_id").getAsString() : "";
                int index = CastleOverlayDataLoader.getInt(preset, "index", -1);
                Object legacyId = preset.has("legacy_id") ? preset.get("legacy_id").getAsString() : definitionId + "_" + index;
                Integer color = preset.has("color") ? CastleOverlayDataLoader.parseColor(preset.get("color").getAsString()) : null;
                OverlayPresetReference reference = new OverlayPresetReference(castleId, (String)legacyId, definitionId, index, color);
                byLegacyId.put(legacyId, reference);
                byCastleId.put(castleId, reference);
            }
            OverlayPresetReferences overlayPresetReferences = new OverlayPresetReferences(Map.copyOf(byLegacyId), Map.copyOf(byCastleId));
            return overlayPresetReferences;
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to load overlay preset references from " + definitionsPath, e);
        }
    }

    public static List<OverlayCastleSite> loadOverlaySites(Map<String, OverlayPresetReference> presetReferencesByLegacyId, String woodenOverlayPath, int woodenCastleColor, String settlementDefinitionId, String stoneOverlayPath, int stoneCastleColor, String lordshipDefinitionId, ClassLoader classLoader) {
        ArrayList<OverlayCastleSite> sites = new ArrayList<OverlayCastleSite>();
        CastleOverlayDataLoader.addOverlaySites(sites, woodenOverlayPath, woodenCastleColor, settlementDefinitionId, presetReferencesByLegacyId, classLoader);
        CastleOverlayDataLoader.addOverlaySites(sites, stoneOverlayPath, stoneCastleColor, lordshipDefinitionId, presetReferencesByLegacyId, classLoader);
        return List.copyOf(sites);
    }

    public static Map<String, CastlePresetMetadata> loadPresetMetadata(List<OverlayCastleSite> overlaySites, Map<String, OverlayPresetReference> presetReferencesByCastleId, String presetsRoot, ClassLoader classLoader) {
        LinkedHashMap castles = new LinkedHashMap();
        for (OverlayCastleSite site : overlaySites) {
            CastleOverlayDataLoader.loadCastlePreset(site, presetReferencesByCastleId, presetsRoot, classLoader).ifPresent(metadata -> castles.put(site.castleId(), metadata));
        }
        return Map.copyOf(castles);
    }

    private static void addOverlaySites(List<OverlayCastleSite> sites, String overlayPath, int targetColor, String definitionId, Map<String, OverlayPresetReference> presetReferencesByLegacyId, ClassLoader classLoader) {
        BufferedImage image = CastleOverlayDataLoader.loadImage(overlayPath, classLoader);
        List<OverlayPresetReference> references = presetReferencesByLegacyId.values().stream().filter(reference -> definitionId.equals(reference.definitionId())).sorted(Comparator.comparingInt(OverlayPresetReference::index)).toList();
        boolean hasExplicitColors = references.stream().anyMatch(reference -> reference.color() != null);
        if (hasExplicitColors) {
            Map<Integer, List<class_2338>> anchorsByColor = CastleOverlayDataLoader.findOverlayAnchorsByColor(image);
            for (OverlayPresetReference reference2 : references) {
                if (reference2.color() == null) continue;
                List<class_2338> anchors = anchorsByColor.get(reference2.color());
                if (anchors == null || anchors.isEmpty()) {
                    throw new IllegalStateException("Missing overlay anchor for castle " + reference2.castleId() + " color " + CastleOverlayDataLoader.formatColor(reference2.color()));
                }
                if (anchors.size() != 1) {
                    throw new IllegalStateException("Expected exactly one overlay anchor for castle " + reference2.castleId() + " color " + CastleOverlayDataLoader.formatColor(reference2.color()) + " but found " + anchors.size());
                }
                sites.add(new OverlayCastleSite(reference2.castleId(), definitionId, reference2.index(), anchors.getFirst()));
            }
            return;
        }
        List<class_2338> anchors = CastleOverlayDataLoader.findOverlayAnchors(image, targetColor);
        for (int i = 0; i < anchors.size(); ++i) {
            int index = i + 1;
            String legacyCastleId = definitionId + "_" + index;
            OverlayPresetReference presetReference = presetReferencesByLegacyId.get(legacyCastleId);
            Object castleId = presetReference != null ? presetReference.castleId() : legacyCastleId;
            sites.add(new OverlayCastleSite((String)castleId, definitionId, index, anchors.get(i)));
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Optional<CastlePresetMetadata> loadCastlePreset(OverlayCastleSite site, Map<String, OverlayPresetReference> presetReferencesByCastleId, String presetsRoot, ClassLoader classLoader) {
        OverlayPresetReference presetReference = presetReferencesByCastleId.get(site.castleId());
        String path = presetsRoot + (presetReference != null ? presetReference.castleId() : site.castleId()) + ".json";
        try (InputStream stream = classLoader.getResourceAsStream(path);){
            String castleId;
            if (stream == null) {
                Optional<CastlePresetMetadata> optional2 = Optional.empty();
                return optional2;
            }
            JsonObject castle = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            String string = castleId = castle.has("castle_id") ? castle.get("castle_id").getAsString() : site.castleId();
            if (!castleId.equals(site.castleId())) {
                GotMod.LOGGER.warn("Castle preset {} declares castle_id {} but expected {}", new Object[]{path, castleId, site.castleId()});
            }
            Optional<CastlePresetMetadata> optional = Optional.of(new CastlePresetMetadata(site.castleId(), castle.has("name") ? castle.get("name").getAsString() : "", castle.has("lore") ? castle.get("lore").getAsString() : "", castle.has("kingdom_id") ? castle.get("kingdom_id").getAsString() : "", castle.has("house_id") ? castle.get("house_id").getAsString() : "", castle.has("house_armor_id") ? castle.get("house_armor_id").getAsString() : "", castle.has("religion_id") ? castle.get("religion_id").getAsString() : "", castle.has("tier") ? castle.get("tier").getAsString() : "", CastleOverlayDataLoader.parseSubtypeSet(castle), !castle.has("generate_village") || castle.get("generate_village").getAsBoolean()));
            return optional;
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to load castle preset " + path, e);
        }
    }

    private static Set<CastleSubtype> parseSubtypeSet(JsonObject castle) {
        LinkedHashSet<CastleSubtype> subtypes = new LinkedHashSet<CastleSubtype>();
        if (!castle.has("subtypes")) {
            return Set.of();
        }
        for (JsonElement element : castle.getAsJsonArray("subtypes")) {
            CastleSubtype subtype = CastleSubtype.byId(element.getAsString());
            if (subtype == null) continue;
            subtypes.add(subtype);
        }
        return Set.copyOf(subtypes);
    }

    private static List<class_2338> findOverlayAnchors(BufferedImage image, int targetColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        boolean[] visited = new boolean[width * height];
        ArrayList<class_2338> anchors = new ArrayList<class_2338>();
        for (int pixelX = 0; pixelX < width; ++pixelX) {
            for (int pixelZ = 0; pixelZ < height; ++pixelZ) {
                int index = pixelZ * width + pixelX;
                if (visited[index] || !CastleOverlayDataLoader.matchesColor(image, pixelX, pixelZ, targetColor)) continue;
                ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
                queue.add(new int[]{pixelX, pixelZ});
                visited[index] = true;
                long sumX = 0L;
                long sumZ = 0L;
                int count = 0;
                while (!queue.isEmpty()) {
                    int[] current = (int[])queue.removeFirst();
                    int x = current[0];
                    int z = current[1];
                    sumX += (long)x;
                    sumZ += (long)z;
                    ++count;
                    CastleOverlayDataLoader.visitNeighbor(image, targetColor, width, height, visited, queue, x + 1, z);
                    CastleOverlayDataLoader.visitNeighbor(image, targetColor, width, height, visited, queue, x - 1, z);
                    CastleOverlayDataLoader.visitNeighbor(image, targetColor, width, height, visited, queue, x, z + 1);
                    CastleOverlayDataLoader.visitNeighbor(image, targetColor, width, height, visited, queue, x, z - 1);
                }
                int centerX = Math.toIntExact(Math.round((double)sumX / (double)count));
                int centerZ = Math.toIntExact(Math.round((double)sumZ / (double)count));
                anchors.add(CastleOverlayDataLoader.pixelToWorld(centerX, centerZ));
            }
        }
        return anchors;
    }

    private static Map<Integer, List<class_2338>> findOverlayAnchorsByColor(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        boolean[] visited = new boolean[width * height];
        HashMap<Integer, List<class_2338>> anchorsByColor = new HashMap<Integer, List<class_2338>>();
        for (int pixelX = 0; pixelX < width; ++pixelX) {
            for (int pixelZ = 0; pixelZ < height; ++pixelZ) {
                int index = pixelZ * width + pixelX;
                int argb = image.getRGB(pixelX, pixelZ);
                if (visited[index] || argb >>> 24 == 0) continue;
                int targetColor = argb & 0xFFFFFF;
                ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
                queue.add(new int[]{pixelX, pixelZ});
                visited[index] = true;
                long sumX = 0L;
                long sumZ = 0L;
                int count = 0;
                while (!queue.isEmpty()) {
                    int[] current = (int[])queue.removeFirst();
                    int x = current[0];
                    int z = current[1];
                    sumX += (long)x;
                    sumZ += (long)z;
                    ++count;
                    CastleOverlayDataLoader.visitNeighbor(image, targetColor, width, height, visited, queue, x + 1, z);
                    CastleOverlayDataLoader.visitNeighbor(image, targetColor, width, height, visited, queue, x - 1, z);
                    CastleOverlayDataLoader.visitNeighbor(image, targetColor, width, height, visited, queue, x, z + 1);
                    CastleOverlayDataLoader.visitNeighbor(image, targetColor, width, height, visited, queue, x, z - 1);
                }
                int centerX = Math.toIntExact(Math.round((double)sumX / (double)count));
                int centerZ = Math.toIntExact(Math.round((double)sumZ / (double)count));
                anchorsByColor.computeIfAbsent(targetColor, ignored -> new ArrayList()).add(CastleOverlayDataLoader.pixelToWorld(centerX, centerZ));
            }
        }
        return anchorsByColor;
    }

    private static void visitNeighbor(BufferedImage image, int targetColor, int width, int height, boolean[] visited, ArrayDeque<int[]> queue, int pixelX, int pixelZ) {
        if (pixelX < 0 || pixelZ < 0 || pixelX >= width || pixelZ >= height) {
            return;
        }
        int index = pixelZ * width + pixelX;
        if (visited[index] || !CastleOverlayDataLoader.matchesColor(image, pixelX, pixelZ, targetColor)) {
            return;
        }
        visited[index] = true;
        queue.addLast(new int[]{pixelX, pixelZ});
    }

    private static boolean matchesColor(BufferedImage image, int pixelX, int pixelZ, int targetColor) {
        int argb = image.getRGB(pixelX, pixelZ);
        if (argb >>> 24 == 0) {
            return false;
        }
        return (argb & 0xFFFFFF) == targetColor;
    }

    private static class_2338 pixelToWorld(int pixelX, int pixelZ) {
        int worldX = (pixelX - 1500) * 32 + 16;
        int worldZ = (pixelZ - 1500) * 32 + 16;
        return new class_2338(worldX, 0, worldZ);
    }

    private static int getInt(JsonObject root, String key, int fallback) {
        return root.has(key) ? root.get(key).getAsInt() : fallback;
    }

    private static Integer parseColor(String value) {
        String normalized;
        String string = normalized = value == null ? "" : value.trim();
        if (normalized.startsWith("#")) {
            normalized = normalized.substring(1);
        }
        if (normalized.startsWith("0x") || normalized.startsWith("0X")) {
            normalized = normalized.substring(2);
        }
        if (normalized.length() != 6) {
            throw new IllegalStateException("Invalid overlay color value " + value);
        }
        return Integer.parseInt(normalized, 16);
    }

    private static String formatColor(int color) {
        return String.format("#%06X", color & 0xFFFFFF);
    }
}

