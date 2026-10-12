/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_2960
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.kingdom;

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
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_2960;
import org.jetbrains.annotations.Nullable;
import trep.got.GotMod;
import trep.got.castle.CastleInvasionMode;
import trep.got.kingdom.ReputationKingdomPolicy;
import trep.got.kingdom.ReputationPledgeOption;
import trep.got.kingdom.ReputationPledgeRequirement;
import trep.got.kingdom.ReputationProfile;
import trep.got.kingdom.ReputationRoute;
import trep.got.kingdom.ReputationTitle;
import trep.got.kingdom.ReputationTitleDefinition;

public final class ReputationRulesRegistry {
    private static final String TITLE_RESOURCE_ROOT = "reputation/title_definitions";
    private static final String POLICY_RESOURCE_ROOT = "reputation/policies";
    private static final Map<ReputationTitle, ReputationTitleDefinition> TITLE_DEFINITIONS = new EnumMap<ReputationTitle, ReputationTitleDefinition>(ReputationTitle.class);
    private static final Map<String, ReputationKingdomPolicy> KINGDOM_POLICIES = new LinkedHashMap<String, ReputationKingdomPolicy>();
    private static boolean initialized;

    private ReputationRulesRegistry() {
    }

    public static synchronized void initialize() {
        if (initialized) {
            return;
        }
        ReputationRulesRegistry.loadTitleDefinitions();
        ReputationRulesRegistry.loadKingdomPolicies();
        initialized = true;
    }

    private static void ensureInitialized() {
        if (!initialized) {
            ReputationRulesRegistry.initialize();
        }
    }

    public static ReputationTitleDefinition titleDefinition(ReputationTitle title) {
        ReputationRulesRegistry.ensureInitialized();
        ReputationTitleDefinition definition = TITLE_DEFINITIONS.get((Object)title);
        if (definition == null) {
            throw new IllegalStateException("Missing reputation title definition for " + title.name());
        }
        return definition;
    }

    public static ReputationKingdomPolicy kingdomPolicy(@Nullable String kingdomId) {
        ReputationKingdomPolicy fallback;
        ReputationRulesRegistry.ensureInitialized();
        if (kingdomId != null && !kingdomId.isBlank()) {
            ReputationKingdomPolicy direct = KINGDOM_POLICIES.get(kingdomId);
            if (direct != null) {
                return direct;
            }
            for (ReputationKingdomPolicy policy : KINGDOM_POLICIES.values()) {
                if (!policy.appliesTo(kingdomId)) continue;
                return policy;
            }
        }
        if ((fallback = KINGDOM_POLICIES.get("default")) == null) {
            throw new IllegalStateException("Missing default reputation kingdom policy");
        }
        return fallback;
    }

    public static boolean isKnighthoodCeremonyAvailable(@Nullable String kingdomId) {
        return ReputationRulesRegistry.kingdomPolicy(kingdomId).knighthoodCeremonyAvailable();
    }

    public static List<ReputationPledgeOption> pledgeOptionsFor(@Nullable String kingdomId) {
        return ReputationRulesRegistry.kingdomPolicy(kingdomId).pledgeOptions();
    }

    public static List<ReputationPledgeOption> availablePledgeOptions(@Nullable String kingdomId, ReputationProfile profile) {
        ArrayList<ReputationPledgeOption> available = new ArrayList<ReputationPledgeOption>();
        for (ReputationPledgeOption option : ReputationRulesRegistry.pledgeOptionsFor(kingdomId)) {
            if (!option.isAvailableFor(profile)) continue;
            available.add(option);
        }
        return List.copyOf(available);
    }

    public static List<ReputationPledgeOption> sharedPledgeOptions(@Nullable String currentKingdomId, @Nullable String targetKingdomId, ReputationProfile profile) {
        LinkedHashMap<String, ReputationPledgeOption> currentOptions = new LinkedHashMap<String, ReputationPledgeOption>();
        for (ReputationPledgeOption option : ReputationRulesRegistry.availablePledgeOptions(currentKingdomId, profile)) {
            currentOptions.put(option.id(), option);
        }
        ArrayList<ReputationPledgeOption> shared = new ArrayList<ReputationPledgeOption>();
        for (ReputationPledgeOption option : ReputationRulesRegistry.availablePledgeOptions(targetKingdomId, profile)) {
            if (!currentOptions.containsKey(option.id())) continue;
            shared.add(option);
        }
        return List.copyOf(shared);
    }

    @Nullable
    public static ReputationPledgeOption findPledgeOption(@Nullable String kingdomId, String optionId) {
        if (optionId == null || optionId.isBlank()) {
            return null;
        }
        for (ReputationPledgeOption option : ReputationRulesRegistry.pledgeOptionsFor(kingdomId)) {
            if (!option.id().equals(optionId)) continue;
            return option;
        }
        return null;
    }

    @Nullable
    public static ReputationPledgeOption findSharedPledgeOption(@Nullable String currentKingdomId, @Nullable String targetKingdomId, String optionId) {
        for (ReputationPledgeOption option : ReputationRulesRegistry.sharedPledgeOptions(currentKingdomId, targetKingdomId, new ReputationProfile())) {
            if (!option.id().equals(optionId)) continue;
            return option;
        }
        ReputationPledgeOption currentOnly = ReputationRulesRegistry.findPledgeOption(currentKingdomId, optionId);
        ReputationPledgeOption targetOnly = ReputationRulesRegistry.findPledgeOption(targetKingdomId, optionId);
        return currentOnly != null && targetOnly != null ? targetOnly : null;
    }

    @Nullable
    public static ReputationTitle nextTitle(ReputationTitle title) {
        return ReputationRulesRegistry.titleDefinition(title).nextTitle();
    }

    private static void loadTitleDefinitions() {
        TITLE_DEFINITIONS.clear();
        for (ReputationTitle title : ReputationTitle.ALL) {
            String nextTitleId;
            String classpathPath = ReputationRulesRegistry.resourcePath(TITLE_RESOURCE_ROOT, title.getId());
            JsonObject root = ReputationRulesRegistry.readJsonObject(classpathPath);
            if (root == null) {
                throw new IllegalStateException("Missing reputation title definition " + classpathPath);
            }
            ReputationTitle parsedTitle = ReputationTitle.byId(root.get("id").getAsString());
            if (parsedTitle != title) {
                throw new IllegalStateException("Reputation title definition id mismatch in " + classpathPath);
            }
            ReputationRoute route = ReputationRoute.byId(root.get("route").getAsString());
            int tier = root.get("tier").getAsInt();
            int experienceToNextTitle = root.get("experience_to_next_title").getAsInt();
            String displayName = root.get("display_name").getAsString();
            ReputationTitle nextTitle = null;
            if (root.has("next_title_id") && !(nextTitleId = root.get("next_title_id").getAsString()).isBlank()) {
                nextTitle = ReputationTitle.byId(nextTitleId);
            }
            EnumSet<CastleInvasionMode> invasionModes = EnumSet.noneOf(CastleInvasionMode.class);
            JsonArray invasionArray = root.getAsJsonArray("invasion_modes");
            for (int index = 0; index < invasionArray.size(); ++index) {
                CastleInvasionMode mode = CastleInvasionMode.byId(invasionArray.get(index).getAsString());
                if (mode == null) continue;
                invasionModes.add(mode);
            }
            TITLE_DEFINITIONS.put(title, new ReputationTitleDefinition(title, displayName, route, tier, experienceToNextTitle, nextTitle, invasionModes));
        }
    }

    private static void loadKingdomPolicies() {
        KINGDOM_POLICIES.clear();
        List<String> resources = List.of("default", "golden_company", "the_nights_watch", "wildling");
        for (String resource : resources) {
            String classpathPath = ReputationRulesRegistry.resourcePath(POLICY_RESOURCE_ROOT, resource);
            JsonObject root = ReputationRulesRegistry.readJsonObject(classpathPath);
            if (root == null) {
                throw new IllegalStateException("Missing reputation kingdom policy " + classpathPath);
            }
            String id = root.get("id").getAsString();
            Set<String> kingdomIds = ReputationRulesRegistry.parseKingdomIds(root.getAsJsonArray("kingdom_ids"));
            boolean knighthoodCeremonyAvailable = root.get("knighthood_ceremony_available").getAsBoolean();
            ArrayList<ReputationPledgeOption> options = new ArrayList<ReputationPledgeOption>();
            JsonArray optionsArray = root.getAsJsonArray("pledge_options");
            for (int index = 0; index < optionsArray.size(); ++index) {
                JsonObject optionRoot = optionsArray.get(index).getAsJsonObject();
                options.add(new ReputationPledgeOption(optionRoot.get("id").getAsString(), optionRoot.get("label").getAsString(), ReputationTitle.byId(optionRoot.get("title_id").getAsString()), CastleInvasionMode.byId(optionRoot.get("objective_action").getAsString()), ReputationPledgeRequirement.byId(optionRoot.has("requirement") ? optionRoot.get("requirement").getAsString() : "none")));
            }
            ReputationKingdomPolicy policy = new ReputationKingdomPolicy(id, kingdomIds, knighthoodCeremonyAvailable, options);
            KINGDOM_POLICIES.put(id, policy);
            for (String kingdomId : kingdomIds) {
                KINGDOM_POLICIES.putIfAbsent(kingdomId, policy);
            }
        }
    }

    private static Set<String> parseKingdomIds(JsonArray kingdomIdsArray) {
        LinkedHashSet<String> kingdomIds = new LinkedHashSet<String>();
        for (int index = 0; index < kingdomIdsArray.size(); ++index) {
            String kingdomId = kingdomIdsArray.get(index).getAsString();
            class_2960 parsed = class_2960.method_12829((String)kingdomId);
            kingdomIds.add(parsed == null ? kingdomId : parsed.toString());
        }
        return Set.copyOf(kingdomIds);
    }

    private static String resourcePath(String root, String id) {
        return "data/agob/" + root + "/" + id + ".json";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Nullable
    private static JsonObject readJsonObject(String classpathPath) {
        try (InputStream stream = ReputationRulesRegistry.class.getClassLoader().getResourceAsStream(classpathPath);){
            if (stream == null) {
                JsonObject jsonObject2 = null;
                return jsonObject2;
            }
            JsonObject jsonObject = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            return jsonObject;
        }
        catch (IOException exception) {
            GotMod.LOGGER.error("Failed to read reputation resource {}", (Object)classpathPath, (Object)exception);
            return null;
        }
    }
}

