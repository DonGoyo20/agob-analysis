/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package trep.got.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import trep.got.GotMod;
import trep.got.skill.SkillAttributeHelpData;
import trep.got.skill.SkillAttributeType;

public final class GotSkillAttributes {
    private static final String RESOURCE_ROOT = "skills/attributes";
    private static Map<SkillAttributeType, SkillAttributeHelpData> byType = Map.of();

    private GotSkillAttributes() {
    }

    public static void initialize() {
        EnumMap<SkillAttributeType, SkillAttributeHelpData> loaded = new EnumMap<SkillAttributeType, SkillAttributeHelpData>(SkillAttributeType.class);
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            SkillAttributeHelpData data = GotSkillAttributes.parse(type);
            if (data == null) continue;
            loaded.put(type, data);
        }
        byType = Map.copyOf(loaded);
        GotMod.LOGGER.info("Loaded {} skill attribute help entries", (Object)byType.size());
    }

    public static SkillAttributeHelpData get(SkillAttributeType type) {
        return byType.get((Object)type);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static SkillAttributeHelpData parse(SkillAttributeType type) {
        String classpathPath = "data/agob/skills/attributes/" + type.getId() + ".json";
        try (InputStream stream = GotSkillAttributes.class.getClassLoader().getResourceAsStream(classpathPath);){
            if (stream == null) {
                GotMod.LOGGER.warn("Missing skill attribute help resource {}", (Object)classpathPath);
                SkillAttributeHelpData skillAttributeHelpData2 = null;
                return skillAttributeHelpData2;
            }
            JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            String id = root.get("id").getAsString();
            SkillAttributeType parsedType = SkillAttributeType.byId(id);
            if (parsedType != type) {
                throw new IllegalStateException("Skill attribute help id mismatch in " + classpathPath);
            }
            JsonArray helpArray = root.getAsJsonArray("help");
            ArrayList<String> helpLines = new ArrayList<String>();
            for (int index = 0; index < helpArray.size(); ++index) {
                helpLines.add(helpArray.get(index).getAsString());
            }
            if (helpLines.isEmpty()) {
                throw new IllegalStateException("Skill attribute help cannot be empty in " + classpathPath);
            }
            SkillAttributeHelpData skillAttributeHelpData = new SkillAttributeHelpData(type, helpLines);
            return skillAttributeHelpData;
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to read skill attribute help resource " + classpathPath, e);
        }
    }
}

