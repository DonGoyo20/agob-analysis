/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 */
package trep.got.client.dialogue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import trep.got.GotMod;
import trep.got.client.dialogue.DialogueResponseType;
import trep.got.kingdom.Kingdom;
import trep.got.registry.GotKingdoms;

@Environment(value=EnvType.CLIENT)
public final class DialogueResponseRegistry {
    private static final String RESOURCE_ROOT = "data/agob/dialogue_responses/";
    private static final int MAX_RESPONSE_CHARACTERS = 180;
    private static Map<String, Map<DialogueResponseType, ResponseSet>> responsesByKingdom = Map.of();
    private static Map<DialogueResponseType, ResponseSet> fallbackResponsesByType = Map.of();

    private DialogueResponseRegistry() {
    }

    public static void initialize() {
        EnumMap<DialogueResponseType, ResponseSet> fallbackLoaded = new EnumMap<DialogueResponseType, ResponseSet>(DialogueResponseType.class);
        for (DialogueResponseType type : DialogueResponseType.values()) {
            fallbackLoaded.put(type, DialogueResponseRegistry.load(RESOURCE_ROOT + type.getId() + ".json"));
        }
        fallbackResponsesByType = Map.copyOf(fallbackLoaded);
        HashMap loadedByKingdom = new HashMap();
        GotKingdoms.initialize();
        for (Kingdom kingdom : GotKingdoms.getAll()) {
            String kingdomPath = kingdom.getId().method_12832();
            EnumMap<DialogueResponseType, ResponseSet> responses = new EnumMap<DialogueResponseType, ResponseSet>(DialogueResponseType.class);
            for (DialogueResponseType type : DialogueResponseType.values()) {
                String path = RESOURCE_ROOT + kingdomPath + "/" + type.getId() + ".json";
                try {
                    responses.put(type, DialogueResponseRegistry.load(path));
                }
                catch (IllegalStateException exception) {
                    GotMod.LOGGER.warn("Using fallback dialogue responses for missing or invalid {}", (Object)path, (Object)exception);
                    responses.put(type, fallbackResponsesByType.get((Object)type));
                }
            }
            loadedByKingdom.put(kingdom.getId().toString(), Map.copyOf(responses));
            loadedByKingdom.put(kingdomPath, Map.copyOf(responses));
        }
        responsesByKingdom = Map.copyOf(loadedByKingdom);
    }

    public static String pick(DialogueResponseType type, long seed) {
        return DialogueResponseRegistry.pick(type, "", seed, "", "");
    }

    public static String pick(DialogueResponseType type, String kingdomId, long seed, String contextId, String subjectName) {
        ResponseSet responseSet = responsesByKingdom.getOrDefault(DialogueResponseRegistry.normalizeKingdomId(kingdomId), fallbackResponsesByType).get((Object)type);
        if (responseSet == null) {
            responseSet = fallbackResponsesByType.get((Object)type);
        }
        if (responseSet == null) {
            return "...";
        }
        String dynamicResponse = responseSet.pickDynamic(contextId, subjectName, seed);
        if (!dynamicResponse.isBlank() && Math.floorMod((int)seed, 100) < 45) {
            return dynamicResponse;
        }
        List<String> responses = responseSet.responses();
        if (responses.isEmpty()) {
            return "...";
        }
        int index = Math.floorMod((int)(seed ^ seed >>> 32), responses.size());
        return responses.get(index);
    }

    private static ResponseSet load(String path) {
        ResponseSet responseSet;
        block14: {
            InputStream stream = DialogueResponseRegistry.class.getClassLoader().getResourceAsStream(path);
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing dialogue response resource " + path);
                }
                JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                JsonArray array = root.getAsJsonArray("responses");
                ArrayList<String> responses = new ArrayList<String>();
                for (int index = 0; index < array.size(); ++index) {
                    String response = array.get(index).getAsString();
                    if (response.isBlank()) continue;
                    responses.add(DialogueResponseRegistry.trimToMax(response));
                }
                if (responses.isEmpty()) {
                    throw new IllegalStateException("Dialogue response file has no usable responses: " + path);
                }
                HashMap dynamicResponses = new HashMap();
                if (root.has("dynamic_responses") && root.get("dynamic_responses").isJsonObject()) {
                    JsonObject dynamicRoot = root.getAsJsonObject("dynamic_responses");
                    for (Map.Entry entry : dynamicRoot.entrySet()) {
                        if (!((JsonElement)entry.getValue()).isJsonArray()) continue;
                        ArrayList<String> templates = new ArrayList<String>();
                        JsonArray templatesJson = ((JsonElement)entry.getValue()).getAsJsonArray();
                        for (int index = 0; index < templatesJson.size(); ++index) {
                            String template = templatesJson.get(index).getAsString();
                            if (template.isBlank()) continue;
                            templates.add(DialogueResponseRegistry.trimToMax(template));
                        }
                        if (templates.isEmpty()) continue;
                        dynamicResponses.put((String)entry.getKey(), List.copyOf(templates));
                    }
                }
                responseSet = new ResponseSet(List.copyOf(responses), Map.copyOf(dynamicResponses));
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
                    throw new IllegalStateException("Failed to load dialogue responses from " + path, exception);
                }
            }
            stream.close();
        }
        return responseSet;
    }

    private static String normalizeKingdomId(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return "";
        }
        return kingdomId.startsWith("agob:") ? kingdomId : "agob:" + kingdomId;
    }

    private static String trimToMax(String response) {
        String normalized = response.replace('\n', ' ').trim();
        if (normalized.length() <= 180) {
            return normalized;
        }
        return normalized.substring(0, 179).trim() + ".";
    }

    @Environment(value=EnvType.CLIENT)
    private record ResponseSet(List<String> responses, Map<String, List<String>> dynamicResponses) {
        private String pickDynamic(String contextId, String subjectName, long seed) {
            String cleanName;
            if (contextId == null || contextId.isBlank()) {
                return "";
            }
            List templates = this.dynamicResponses.getOrDefault(contextId, List.of());
            if (templates.isEmpty()) {
                return "";
            }
            String string = cleanName = subjectName == null || subjectName.isBlank() ? "someone" : subjectName.trim();
            if (cleanName.length() > 24) {
                cleanName = cleanName.substring(0, 24);
            }
            int index = Math.floorMod((int)(seed ^ seed >>> 32), templates.size());
            return ((String)templates.get(index)).replace("{player}", cleanName);
        }
    }
}

