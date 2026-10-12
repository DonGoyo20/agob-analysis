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
import java.util.Arrays;
import net.fabricmc.loader.api.FabricLoader;
import trep.got.GotMod;
import trep.got.castle.CastleTier;

public final class AgobCastleOwnershipConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("agob_castle_ownership.json");
    private static final Limits DEFAULT_LIMITS = new Limits(1, 1, 1, 1);
    private static byte[] cachedContent;
    private static Limits cachedLimits;

    private AgobCastleOwnershipConfig() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static synchronized Limits load() {
        if (!Files.exists(CONFIG_PATH, new LinkOption[0])) {
            AgobCastleOwnershipConfig.invalidateCache();
            AgobCastleOwnershipConfig.save(DEFAULT_LIMITS);
            return DEFAULT_LIMITS;
        }
        try {
            byte[] content = Files.readAllBytes(CONFIG_PATH);
            if (cachedContent != null && cachedLimits != null && Arrays.equals(content, cachedContent)) {
                return cachedLimits;
            }
            try (InputStreamReader reader = new InputStreamReader((InputStream)new ByteArrayInputStream(content), StandardCharsets.UTF_8.newDecoder());){
                JsonElement rootElement = JsonParser.parseReader((Reader)reader);
                if (!rootElement.isJsonObject()) {
                    AgobCastleOwnershipConfig.save(DEFAULT_LIMITS);
                    Limits limits = DEFAULT_LIMITS;
                    return limits;
                }
                JsonObject root = rootElement.getAsJsonObject();
                JsonObject limitsObject = root.has("limits") && root.get("limits").isJsonObject() ? root.getAsJsonObject("limits") : new JsonObject();
                Limits limits = new Limits(AgobCastleOwnershipConfig.readNonNegativeInt(limitsObject, CastleTier.SETTLEMENT.getId(), DEFAULT_LIMITS.settlement()), AgobCastleOwnershipConfig.readNonNegativeInt(limitsObject, CastleTier.LORDSHIP.getId(), DEFAULT_LIMITS.lordship()), AgobCastleOwnershipConfig.readNonNegativeInt(limitsObject, CastleTier.CAPITAL.getId(), DEFAULT_LIMITS.capital()), AgobCastleOwnershipConfig.readNonNegativeInt(limitsObject, "player_settlement", DEFAULT_LIMITS.playerSettlement()));
                if (!limits.equals(DEFAULT_LIMITS) || limitsObject.size() == 4) {
                    AgobCastleOwnershipConfig.updateCache(content, limits);
                    Limits limits2 = limits;
                    return limits2;
                }
                AgobCastleOwnershipConfig.save(limits);
                Limits limits3 = limits;
                return limits3;
            }
        }
        catch (IOException exception) {
            GotMod.LOGGER.error("Failed to load {}", (Object)CONFIG_PATH, (Object)exception);
            return DEFAULT_LIMITS;
        }
    }

    public static synchronized void save(Limits limits) {
        Limits normalizedLimits = new Limits(Math.max(0, limits.settlement()), Math.max(0, limits.lordship()), Math.max(0, limits.capital()), Math.max(0, limits.playerSettlement()));
        JsonObject root = new JsonObject();
        JsonObject limitsObject = new JsonObject();
        limitsObject.addProperty(CastleTier.SETTLEMENT.getId(), (Number)normalizedLimits.settlement());
        limitsObject.addProperty(CastleTier.LORDSHIP.getId(), (Number)normalizedLimits.lordship());
        limitsObject.addProperty(CastleTier.CAPITAL.getId(), (Number)normalizedLimits.capital());
        limitsObject.addProperty("player_settlement", (Number)normalizedLimits.playerSettlement());
        root.add("limits", (JsonElement)limitsObject);
        AgobCastleOwnershipConfig.invalidateCache();
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
        cachedLimits = null;
    }

    private static void updateCache(byte[] content, Limits limits) {
        cachedContent = (byte[])content.clone();
        cachedLimits = limits;
    }

    private static int readNonNegativeInt(JsonObject object, String key, int fallback) {
        if (object == null || !object.has(key)) {
            return fallback;
        }
        try {
            return Math.max(0, object.get(key).getAsInt());
        }
        catch (RuntimeException exception) {
            return fallback;
        }
    }

    public record Limits(int settlement, int lordship, int capital, int playerSettlement) {
        public int limitForCastleTier(CastleTier tier) {
            return switch (tier) {
                default -> throw new MatchException(null, null);
                case CastleTier.LORDSHIP -> this.lordship;
                case CastleTier.CAPITAL -> this.capital;
                case CastleTier.SETTLEMENT -> this.settlement;
            };
        }
    }
}

