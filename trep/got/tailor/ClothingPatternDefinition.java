/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.class_2960
 */
package trep.got.tailor;

import com.google.gson.JsonObject;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import net.minecraft.class_2960;
import trep.got.skill.SkillAttributeType;

public record ClothingPatternDefinition(String clothingId, String slot, int weight, Map<SkillAttributeType, Integer> attributes) {
    public static ClothingPatternDefinition fromJson(class_2960 id, JsonObject json) {
        String clothingId = json.has("clothing") ? json.get("clothing").getAsString() : id.method_12832().substring(id.method_12832().lastIndexOf(47) + 1);
        String slot = json.has("slot") ? json.get("slot").getAsString() : "set";
        int weight = json.has("weight") ? json.get("weight").getAsInt() : 1;
        return new ClothingPatternDefinition(ClothingPatternDefinition.sanitize(clothingId), ClothingPatternDefinition.sanitize(slot), Math.max(1, Math.min(10, weight)), ClothingPatternDefinition.readAttributes(json));
    }

    private static String sanitize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT).replace("..", "");
    }

    private static Map<SkillAttributeType, Integer> readAttributes(JsonObject json) {
        EnumMap<SkillAttributeType, Integer> attributes = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        if (!json.has("attributes") || !json.get("attributes").isJsonObject()) {
            return Map.of();
        }
        JsonObject attributeJson = json.getAsJsonObject("attributes");
        int total = 0;
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            int value;
            if (!attributeJson.has(type.getId()) || attributes.size() >= 3 || total >= 7 || (value = Math.max(0, attributeJson.get(type.getId()).getAsInt())) <= 0) continue;
            int clamped = Math.min(value, 7 - total);
            attributes.put(type, clamped);
            total += clamped;
        }
        return Map.copyOf(attributes);
    }
}

