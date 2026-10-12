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
import java.util.List;
import java.util.Map;
import trep.got.GotMod;
import trep.got.kingdom.ReputationTitle;
import trep.got.kingdom.ReputationTitleHelpData;

public final class GotReputationTitles {
    private static final String RESOURCE_ROOT = "reputation/titles";
    private static Map<ReputationTitle, ReputationTitleHelpData> byTitle = Map.of();
    private static boolean initialized;

    private GotReputationTitles() {
    }

    public static void initialize() {
        if (initialized) {
            return;
        }
        EnumMap<ReputationTitle, ReputationTitleHelpData> loaded = new EnumMap<ReputationTitle, ReputationTitleHelpData>(ReputationTitle.class);
        for (ReputationTitle title : ReputationTitle.ALL) {
            ReputationTitleHelpData data = GotReputationTitles.parse(title);
            if (data == null) {
                data = new ReputationTitleHelpData(title, List.of("Rank: " + title.getDisplayName()));
            }
            loaded.put(title, data);
        }
        byTitle = Map.copyOf(loaded);
        initialized = true;
        GotMod.LOGGER.info("Loaded {} reputation title help entries", (Object)byTitle.size());
    }

    public static ReputationTitleHelpData get(ReputationTitle title) {
        if (!initialized) {
            GotReputationTitles.initialize();
        }
        return byTitle.getOrDefault((Object)title, new ReputationTitleHelpData(title, List.of("Rank: " + title.getDisplayName())));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static ReputationTitleHelpData parse(ReputationTitle title) {
        String classpathPath = "data/agob/reputation/titles/" + title.getId() + ".json";
        try (InputStream stream = GotReputationTitles.class.getClassLoader().getResourceAsStream(classpathPath);){
            if (stream == null) {
                ReputationTitleHelpData reputationTitleHelpData2 = null;
                return reputationTitleHelpData2;
            }
            JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            ReputationTitle parsedTitle = ReputationTitle.byId(root.get("id").getAsString());
            if (parsedTitle != title) {
                throw new IllegalStateException("Reputation title help id mismatch in " + classpathPath);
            }
            JsonArray helpArray = root.getAsJsonArray("help");
            ArrayList<String> helpLines = new ArrayList<String>();
            for (int index = 0; index < helpArray.size(); ++index) {
                helpLines.add(helpArray.get(index).getAsString());
            }
            if (helpLines.isEmpty()) {
                throw new IllegalStateException("Reputation title help cannot be empty in " + classpathPath);
            }
            ReputationTitleHelpData reputationTitleHelpData = new ReputationTitleHelpData(title, helpLines);
            return reputationTitleHelpData;
        }
        catch (IOException e) {
            GotMod.LOGGER.error("Failed to read reputation title help resource " + classpathPath, (Throwable)e);
            return null;
        }
    }
}

