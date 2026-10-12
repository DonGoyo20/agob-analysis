/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
 *  net.fabricmc.fabric.api.resource.ResourceManagerHelper
 *  net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
 *  net.minecraft.class_2960
 *  net.minecraft.class_3264
 *  net.minecraft.class_3298
 *  net.minecraft.class_3300
 *  net.minecraft.class_5819
 */
package trep.got.tailor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.invoke.CallSite;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.class_2960;
import net.minecraft.class_3264;
import net.minecraft.class_3298;
import net.minecraft.class_3300;
import net.minecraft.class_5819;
import trep.got.GotMod;
import trep.got.skill.SkillAttributeType;
import trep.got.tailor.ClothingPatternDefinition;

public final class ClothingPatternRegistry {
    private static final class_2960 RELOAD_ID = class_2960.method_60655((String)"agob", (String)"clothing_patterns");
    private static final String ROOT = "tailor/clothing";
    private static volatile List<ClothingPatternDefinition> patterns = List.of();

    private ClothingPatternRegistry() {
    }

    public static void initialize() {
        ResourceManagerHelper.get((class_3264)class_3264.field_14190).registerReloadListener((IdentifiableResourceReloadListener)new SimpleSynchronousResourceReloadListener(){

            public class_2960 getFabricId() {
                return RELOAD_ID;
            }

            public void method_14491(class_3300 manager) {
                patterns = ClothingPatternRegistry.loadPatterns(manager);
                int totalWeight = 0;
                for (ClothingPatternDefinition pattern : patterns) {
                    totalWeight += pattern.weight();
                }
                GotMod.LOGGER.info("Loaded {} clothing pattern definition(s) with total loot weight {}", (Object)patterns.size(), (Object)totalWeight);
            }
        });
    }

    public static ClothingPatternDefinition random(class_5819 random) {
        List<ClothingPatternDefinition> current = patterns;
        if (current.isEmpty()) {
            return null;
        }
        int totalWeight = 0;
        for (ClothingPatternDefinition pattern : current) {
            totalWeight += pattern.weight();
        }
        int roll = random.method_43048(Math.max(1, totalWeight));
        for (ClothingPatternDefinition pattern : current) {
            if ((roll -= pattern.weight()) >= 0) continue;
            return pattern;
        }
        return current.get(current.size() - 1);
    }

    public static String[] attributePayloads() {
        ArrayList<CallSite> payloads = new ArrayList<CallSite>();
        for (ClothingPatternDefinition pattern : patterns) {
            if (pattern.attributes().isEmpty()) continue;
            StringJoiner joiner = new StringJoiner(",");
            for (SkillAttributeType type : SkillAttributeType.ALL) {
                int amount = pattern.attributes().getOrDefault((Object)type, 0);
                if (amount <= 0) continue;
                joiner.add(type.getId() + "=" + amount);
            }
            payloads.add((CallSite)((Object)(pattern.clothingId() + "|" + String.valueOf(joiner))));
        }
        return (String[])payloads.toArray(String[]::new);
    }

    public static Map<SkillAttributeType, Integer> attributesFor(String clothingId) {
        if (clothingId == null || clothingId.isBlank()) {
            return Map.of();
        }
        String normalizedId = clothingId.trim().toLowerCase(Locale.ROOT).replace("..", "");
        for (ClothingPatternDefinition pattern : patterns) {
            if (!normalizedId.equals(pattern.clothingId())) continue;
            return pattern.attributes();
        }
        return Map.of();
    }

    private static List<ClothingPatternDefinition> loadPatterns(class_3300 manager) {
        Map resources = manager.method_14488(ROOT, path -> path.method_12832().endsWith(".json"));
        ArrayList<ClothingPatternDefinition> loaded = new ArrayList<ClothingPatternDefinition>();
        for (Map.Entry entry : resources.entrySet()) {
            class_2960 resourceId = (class_2960)entry.getKey();
            class_2960 patternId = class_2960.method_60655((String)resourceId.method_12836(), (String)resourceId.method_12832().substring(0, resourceId.method_12832().length() - ".json".length()));
            try (InputStreamReader reader = new InputStreamReader(((class_3298)entry.getValue()).method_14482(), StandardCharsets.UTF_8);){
                JsonElement element = JsonParser.parseReader((Reader)reader);
                if (!element.isJsonObject()) {
                    throw new IllegalStateException("Clothing pattern must be a JSON object");
                }
                loaded.add(ClothingPatternDefinition.fromJson(patternId, (JsonObject)element));
            }
            catch (IOException e) {
                throw new IllegalStateException("Failed to read clothing pattern " + String.valueOf(resourceId), e);
            }
        }
        return List.copyOf(loaded);
    }
}

