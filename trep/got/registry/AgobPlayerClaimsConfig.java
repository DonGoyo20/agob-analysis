/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.fabricmc.loader.api.FabricLoader
 */
package trep.got.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.fabricmc.loader.api.FabricLoader;
import trep.got.GotMod;

public final class AgobPlayerClaimsConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("agob_player_claims.json");
    private static byte[] cachedContent;
    private static Map<String, Entry> cachedEntries;

    private AgobPlayerClaimsConfig() {
    }

    public static Path getPath() {
        return CONFIG_PATH;
    }

    public static boolean exists() {
        return Files.exists(CONFIG_PATH, new LinkOption[0]);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static synchronized Map<String, Entry> load() {
        if (!Files.exists(CONFIG_PATH, new LinkOption[0])) {
            AgobPlayerClaimsConfig.invalidateCache();
            return new LinkedHashMap<String, Entry>();
        }
        try {
            byte[] content = Files.readAllBytes(CONFIG_PATH);
            if (cachedContent != null && cachedEntries != null && Arrays.equals(content, cachedContent)) {
                return new LinkedHashMap<String, Entry>(cachedEntries);
            }
            AgobPlayerClaimsConfig.invalidateCache();
            try (InputStreamReader reader = new InputStreamReader((InputStream)new ByteArrayInputStream(content), StandardCharsets.UTF_8.newDecoder());){
                JsonElement rootElement = JsonParser.parseReader((Reader)reader);
                if (!rootElement.isJsonObject()) {
                    AgobPlayerClaimsConfig.updateCache(content, Map.of());
                    LinkedHashMap<String, Entry> linkedHashMap = new LinkedHashMap<String, Entry>();
                    return linkedHashMap;
                }
                JsonObject root = rootElement.getAsJsonObject();
                JsonArray claims = root.has("claims") && root.get("claims").isJsonArray() ? root.getAsJsonArray("claims") : new JsonArray();
                LinkedHashMap<String, Entry> entries = new LinkedHashMap<String, Entry>();
                boolean cacheable = true;
                for (JsonElement claimElement : claims) {
                    JsonObject claimObject;
                    Entry entry;
                    if (!claimElement.isJsonObject() || (entry = Entry.fromJson(claimObject = claimElement.getAsJsonObject())) == null) continue;
                    entries.put(entry.claimId(), entry);
                    if (claimObject.has("created_at")) continue;
                    cacheable = false;
                }
                if (cacheable) {
                    AgobPlayerClaimsConfig.updateCache(content, entries);
                }
                LinkedHashMap<String, Entry> linkedHashMap = new LinkedHashMap<String, Entry>(entries);
                return linkedHashMap;
            }
        }
        catch (IOException exception) {
            GotMod.LOGGER.error("Failed to load {}", (Object)CONFIG_PATH, (Object)exception);
            return new LinkedHashMap<String, Entry>();
        }
    }

    public static synchronized void save(Map<String, Entry> entries) {
        JsonObject root = new JsonObject();
        JsonArray claims = new JsonArray();
        for (Entry entry : entries.values()) {
            claims.add((JsonElement)entry.toJson());
        }
        root.add("claims", (JsonElement)claims);
        AgobPlayerClaimsConfig.invalidateCache();
        try {
            Files.createDirectories(CONFIG_PATH.getParent(), new FileAttribute[0]);
            try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);){
                GSON.toJson((JsonElement)root, (Appendable)writer);
            }
        }
        catch (IOException exception) {
            GotMod.LOGGER.error("Failed to save {}", (Object)CONFIG_PATH, (Object)exception);
        }
    }

    private static void invalidateCache() {
        cachedContent = null;
        cachedEntries = null;
    }

    private static void updateCache(byte[] content, Map<String, Entry> entries) {
        cachedContent = (byte[])content.clone();
        cachedEntries = Collections.unmodifiableMap(new LinkedHashMap<String, Entry>(entries));
    }

    public static Entry create(String claimId, String username, int x, int y, int z) {
        return new Entry(Instant.now().toString(), claimId, username, x, y, z);
    }

    public record Entry(String createdAt, String claimId, String username, int x, int y, int z) {
        private static Entry fromJson(JsonObject object) {
            if (!object.has("claim_id") || !object.has("username")) {
                return null;
            }
            String claimId = object.get("claim_id").getAsString();
            String username = object.get("username").getAsString();
            if (claimId.isBlank() || username.isBlank()) {
                return null;
            }
            String createdAt = object.has("created_at") ? object.get("created_at").getAsString() : Instant.now().toString();
            int x = object.has("x") ? object.get("x").getAsInt() : 0;
            int y = object.has("y") ? object.get("y").getAsInt() : 0;
            int z = object.has("z") ? object.get("z").getAsInt() : 0;
            return new Entry(createdAt, claimId, username, x, y, z);
        }

        private JsonObject toJson() {
            JsonObject object = new JsonObject();
            object.addProperty("created_at", this.createdAt);
            object.addProperty("claim_id", this.claimId);
            object.addProperty("username", this.username);
            object.addProperty("x", (Number)this.x);
            object.addProperty("y", (Number)this.y);
            object.addProperty("z", (Number)this.z);
            return object;
        }
    }
}

