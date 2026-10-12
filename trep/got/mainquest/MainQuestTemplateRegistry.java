/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_2960
 */
package trep.got.mainquest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_2960;
import trep.got.mainquest.MainQuestReward;
import trep.got.mainquest.MainQuestStep;
import trep.got.mainquest.MainQuestStepType;
import trep.got.mainquest.MainQuestTemplate;

public final class MainQuestTemplateRegistry {
    private static final String INDEX_PATH = "data/agob/quests/main/templates.json";
    private static final String QUEST_ROOT = "data/agob/quests/main/";
    private static Map<class_2960, MainQuestTemplate> byId = Map.of();
    private static List<MainQuestTemplate> templates = List.of();

    private MainQuestTemplateRegistry() {
    }

    public static void initialize() {
        LinkedHashMap<class_2960, MainQuestTemplate> loaded = new LinkedHashMap<class_2960, MainQuestTemplate>();
        for (String id : MainQuestTemplateRegistry.loadIndex()) {
            MainQuestTemplate template = MainQuestTemplateRegistry.loadTemplate(id);
            MainQuestTemplate previous = loaded.putIfAbsent(template.id(), template);
            if (previous == null) continue;
            throw new IllegalStateException("Duplicate main quest template id: " + String.valueOf(template.id()));
        }
        byId = Map.copyOf(loaded);
        templates = List.copyOf(loaded.values());
    }

    public static MainQuestTemplate get(class_2960 id) {
        return byId.get(id);
    }

    public static List<MainQuestTemplate> templates() {
        return templates;
    }

    public static List<MainQuestTemplate> autoStartTemplates() {
        return templates.stream().filter(MainQuestTemplate::autoStart).toList();
    }

    private static List<String> loadIndex() {
        List list;
        block10: {
            InputStream stream = MainQuestTemplateRegistry.class.getClassLoader().getResourceAsStream(INDEX_PATH);
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing main quest template index data/agob/quests/main/templates.json");
                }
                JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                ArrayList<String> ids = new ArrayList<String>();
                for (JsonElement element : root.getAsJsonArray("templates")) {
                    ids.add(element.getAsString());
                }
                list = List.copyOf(ids);
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
                catch (Exception exception) {
                    throw new IllegalStateException("Failed to load main quest template index data/agob/quests/main/templates.json", exception);
                }
            }
            stream.close();
        }
        return list;
    }

    private static MainQuestTemplate loadTemplate(String id) {
        MainQuestTemplate mainQuestTemplate;
        block14: {
            String path = QUEST_ROOT + id + ".json";
            String label = path.substring(QUEST_ROOT.length(), path.length() - ".json".length());
            InputStream stream = MainQuestTemplateRegistry.class.getClassLoader().getResourceAsStream(path);
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing main quest template " + path);
                }
                JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                ArrayList<String> description = new ArrayList<String>();
                for (Object element : root.getAsJsonArray("description")) {
                    description.add(element.getAsString());
                }
                ArrayList<MainQuestStep> steps = new ArrayList<MainQuestStep>();
                for (JsonElement element : root.getAsJsonArray("steps")) {
                    JsonObject step = element.getAsJsonObject();
                    MainQuestStepType type = MainQuestStepType.byId(step.get("type").getAsString());
                    if (type == null) {
                        throw new IllegalStateException("Unknown main quest step type in " + label + ": " + step.get("type").getAsString());
                    }
                    steps.add(new MainQuestStep(step.get("id").getAsString(), type, step.get("text").getAsString(), step.has("structure_path_contains") ? step.get("structure_path_contains").getAsString() : "", step.has("item_id") ? step.get("item_id").getAsString() : "", step.has("required_count") ? step.get("required_count").getAsInt() : 1));
                }
                if (steps.isEmpty()) {
                    throw new IllegalStateException("Main quest " + label + " must define at least one step");
                }
                ArrayList<MainQuestReward> rewards = new ArrayList<MainQuestReward>();
                for (JsonElement element : root.getAsJsonArray("rewards")) {
                    JsonObject reward = element.getAsJsonObject();
                    rewards.add(new MainQuestReward(class_2960.method_60654((String)reward.get("item").getAsString()), reward.get("count").getAsInt()));
                }
                mainQuestTemplate = new MainQuestTemplate(class_2960.method_60654((String)root.get("id").getAsString()), root.get("display_name").getAsString(), List.copyOf(description), root.has("auto_start") && root.get("auto_start").getAsBoolean(), root.has("next_quest") && !root.get("next_quest").getAsString().isBlank() ? class_2960.method_60654((String)root.get("next_quest").getAsString()) : null, List.copyOf(steps), List.copyOf(rewards));
                if (stream == null) break block14;
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
                catch (Exception exception) {
                    throw new IllegalStateException("Failed to load main quest template " + label, exception);
                }
            }
            stream.close();
        }
        return mainQuestTemplate;
    }
}

