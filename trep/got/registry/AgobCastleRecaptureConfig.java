/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.fabricmc.loader.api.FabricLoader
 */
package trep.got.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import net.fabricmc.loader.api.FabricLoader;
import trep.got.GotMod;

public final class AgobCastleRecaptureConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("agob_castle_recapture.json");
    private static final Settings DEFAULT_SETTINGS = new Settings(25, 25, 180);

    private AgobCastleRecaptureConfig() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Settings load() {
        if (!Files.exists(CONFIG_PATH, new LinkOption[0])) {
            AgobCastleRecaptureConfig.save(DEFAULT_SETTINGS);
            return DEFAULT_SETTINGS;
        }
        try (BufferedReader reader = Files.newBufferedReader(CONFIG_PATH, StandardCharsets.UTF_8);){
            JsonElement rootElement = JsonParser.parseReader((Reader)reader);
            if (!rootElement.isJsonObject()) {
                AgobCastleRecaptureConfig.save(DEFAULT_SETTINGS);
                Settings settings = DEFAULT_SETTINGS;
                return settings;
            }
            JsonObject root = rootElement.getAsJsonObject();
            Settings settings = new Settings(AgobCastleRecaptureConfig.readPositiveInt(root, "check_interval_minutes", DEFAULT_SETTINGS.checkIntervalMinutes()), AgobCastleRecaptureConfig.readPercent(root, "trigger_chance_percent", DEFAULT_SETTINGS.triggerChancePercent()), AgobCastleRecaptureConfig.readPositiveInt(root, "incoming_duration_seconds", DEFAULT_SETTINGS.incomingDurationSeconds()));
            if (!settings.equals(DEFAULT_SETTINGS) || root.size() == 3) {
                Settings settings2 = settings;
                return settings2;
            }
            AgobCastleRecaptureConfig.save(settings);
            Settings settings3 = settings;
            return settings3;
        }
        catch (IOException exception) {
            GotMod.LOGGER.error("Failed to load {}", (Object)CONFIG_PATH, (Object)exception);
            return DEFAULT_SETTINGS;
        }
    }

    public static void save(Settings settings) {
        JsonObject root = new JsonObject();
        root.addProperty("check_interval_minutes", (Number)Math.max(1, settings.checkIntervalMinutes()));
        root.addProperty("trigger_chance_percent", (Number)Math.max(0, Math.min(100, settings.triggerChancePercent())));
        root.addProperty("incoming_duration_seconds", (Number)Math.max(1, settings.incomingDurationSeconds()));
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

    private static int readPositiveInt(JsonObject object, String key, int fallback) {
        if (object == null || !object.has(key)) {
            return fallback;
        }
        try {
            return Math.max(1, object.get(key).getAsInt());
        }
        catch (RuntimeException exception) {
            return fallback;
        }
    }

    private static int readPercent(JsonObject object, String key, int fallback) {
        if (object == null || !object.has(key)) {
            return fallback;
        }
        try {
            return Math.max(0, Math.min(100, object.get(key).getAsInt()));
        }
        catch (RuntimeException exception) {
            return fallback;
        }
    }

    public record Settings(int checkIntervalMinutes, int triggerChancePercent, int incomingDurationSeconds) {
        public long intervalTicks() {
            return (long)this.checkIntervalMinutes * 60L * 20L;
        }

        public long incomingDurationTicks() {
            return (long)this.incomingDurationSeconds * 20L;
        }
    }
}

