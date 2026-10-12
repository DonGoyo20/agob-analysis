/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_1304
 *  net.minecraft.class_2960
 */
package trep.got.troop;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.class_1304;
import net.minecraft.class_2960;
import trep.got.GotMod;
import trep.got.api.internal.AgobAddonRegistry;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.ReputationTitle;
import trep.got.registry.GotKingdoms;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.DynamicTroopUnitType;
import trep.got.troop.HireableCampDefinition;
import trep.got.util.ClasspathResourceHelper;

public final class DynamicTroopRegistry {
    private static final String RESOURCE_ROOT = "data/agob/troops/";
    private static final String DEFAULT_RESOURCE_DIRECTORY = "data/agob/troops/default";
    private static Map<class_2960, DynamicTroopDefinition> byId = Map.of();
    private static Map<class_2960, List<DynamicTroopDefinition>> byKingdomId = Map.of();
    private static List<DynamicTroopDefinition> globalDefinitions = List.of();
    private static Map<String, HireableCampDefinition> hireableCampsById = Map.of();

    private DynamicTroopRegistry() {
    }

    public static void initialize() {
        LinkedHashMap<class_2960, DynamicTroopDefinition> loadedById = new LinkedHashMap<class_2960, DynamicTroopDefinition>();
        LinkedHashMap<class_2960, List<DynamicTroopDefinition>> loadedByKingdom = new LinkedHashMap<class_2960, List<DynamicTroopDefinition>>();
        ArrayList<DynamicTroopDefinition> loadedGlobalDefinitions = new ArrayList<DynamicTroopDefinition>();
        LinkedHashMap<String, HireableCampDefinition> loadedHireableCamps = new LinkedHashMap<String, HireableCampDefinition>();
        DynamicTroopRegistry.loadBundledKingdomTroops(loadedById, loadedByKingdom);
        DynamicTroopRegistry.loadDefaultTroops(loadedById, loadedByKingdom, loadedGlobalDefinitions, loadedHireableCamps);
        DynamicTroopRegistry.loadAddonTroops(loadedById, loadedByKingdom, loadedGlobalDefinitions, loadedHireableCamps);
        DynamicTroopRegistry.validateUpgradeTargets(loadedById);
        DynamicTroopRegistry.validateHireableCampTroops(loadedById, loadedHireableCamps);
        byId = Map.copyOf(loadedById);
        byKingdomId = loadedByKingdom.entrySet().stream().collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, entry -> List.copyOf((Collection)entry.getValue())));
        globalDefinitions = List.copyOf(loadedGlobalDefinitions);
        hireableCampsById = Map.copyOf(loadedHireableCamps);
        GotMod.LOGGER.info("Loaded {} dynamic troop definitions and {} hireable camp definition(s)", (Object)byId.size(), (Object)hireableCampsById.size());
    }

    public static DynamicTroopDefinition get(class_2960 id) {
        return id == null ? null : byId.get(id);
    }

    public static DynamicTroopDefinition get(String id) {
        class_2960 parsed = class_2960.method_12829((String)id);
        return parsed == null ? null : DynamicTroopRegistry.get(parsed);
    }

    public static List<DynamicTroopDefinition> getByKingdom(class_2960 kingdomId) {
        if (kingdomId == null) {
            return List.of();
        }
        List<DynamicTroopDefinition> definitions = byKingdomId.get(kingdomId);
        if (definitions != null) {
            return definitions;
        }
        class_2960 cultureKingdomId = GotKingdoms.cultureKingdomId(kingdomId);
        return cultureKingdomId == null || cultureKingdomId.equals((Object)kingdomId) ? List.of() : byKingdomId.getOrDefault(cultureKingdomId, List.of());
    }

    public static List<DynamicTroopDefinition> getGlobalDefinitions() {
        return globalDefinitions;
    }

    public static List<HireableCampDefinition> getHireableCampDefinitions() {
        return List.copyOf(hireableCampsById.values());
    }

    public static HireableCampDefinition getHireableCamp(String id) {
        return id == null ? null : hireableCampsById.get(id);
    }

    private static List<DynamicTroopDefinition> loadKingdomTroops(Kingdom kingdom) {
        List<String> paths = DynamicTroopRegistry.listKingdomResourceFiles(kingdom);
        if (paths.isEmpty()) {
            throw new IllegalStateException("Missing dynamic troop resources for kingdom " + String.valueOf(kingdom.getId()) + " in data/agob/troops/" + kingdom.getId().method_12832() + "/");
        }
        ArrayList<DynamicTroopDefinition> definitions = new ArrayList<DynamicTroopDefinition>();
        LinkedHashSet<class_2960> localIds = new LinkedHashSet<class_2960>();
        for (String path : paths) {
            DynamicTroopRegistry.loadDefinitionsFromFile(path, kingdom, definitions, localIds);
        }
        return List.copyOf(definitions);
    }

    private static void loadBundledKingdomTroops(Map<class_2960, DynamicTroopDefinition> loadedById, Map<class_2960, List<DynamicTroopDefinition>> loadedByKingdom) {
        List<DynamicTroopDefinition> definitions;
        LinkedHashSet<class_2960> loadedKingdomIds = new LinkedHashSet<class_2960>();
        for (Kingdom kingdom : GotKingdoms.WESTEROSI_KINGDOMS) {
            definitions = DynamicTroopRegistry.loadKingdomTroops(kingdom);
            DynamicTroopRegistry.appendDefinitions(loadedById, loadedByKingdom, definitions, false);
            loadedKingdomIds.add(kingdom.getId());
        }
        for (Kingdom kingdom : GotKingdoms.getAll()) {
            if (loadedKingdomIds.contains(kingdom.getId()) || DynamicTroopRegistry.listKingdomResourceFiles(kingdom).isEmpty()) continue;
            definitions = DynamicTroopRegistry.loadKingdomTroops(kingdom);
            DynamicTroopRegistry.appendDefinitions(loadedById, loadedByKingdom, definitions, false);
            loadedKingdomIds.add(kingdom.getId());
        }
    }

    private static void loadDefinitionsFromFile(String path, Kingdom kingdom, List<DynamicTroopDefinition> definitions, Set<class_2960> localIds) {
        try (InputStream stream = DynamicTroopRegistry.class.getClassLoader().getResourceAsStream(path);){
            class_2960 kingdomId;
            if (stream == null) {
                throw new IllegalStateException("Missing dynamic troop resource " + path);
            }
            JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            class_2960 class_29602 = kingdomId = root.has("kingdom_id") ? class_2960.method_60654((String)root.get("kingdom_id").getAsString()) : kingdom.getId();
            if (!kingdom.getId().equals((Object)kingdomId)) {
                throw new IllegalStateException("Dynamic troop file " + path + " declares kingdom " + String.valueOf(kingdomId) + " but expected " + String.valueOf(kingdom.getId()));
            }
            JsonArray troops = root.getAsJsonArray("troops");
            for (JsonElement troopElement : troops) {
                DynamicTroopDefinition definition = DynamicTroopRegistry.parseDefinition(path, kingdomId, troopElement.getAsJsonObject());
                if (!localIds.add(definition.id())) {
                    throw new IllegalStateException("Duplicate troop id " + String.valueOf(definition.id()) + " in kingdom " + String.valueOf(kingdom.getId()));
                }
                definitions.add(definition);
            }
        }
        catch (Exception exception) {
            throw new IllegalStateException("Failed to load dynamic troops from " + path, exception);
        }
    }

    private static void loadDefaultTroops(Map<class_2960, DynamicTroopDefinition> loadedById, Map<class_2960, List<DynamicTroopDefinition>> loadedByKingdom, List<DynamicTroopDefinition> loadedGlobalDefinitions, Map<String, HireableCampDefinition> loadedHireableCamps) {
        List<String> paths = DynamicTroopRegistry.listDefaultResourceFiles();
        for (String path : paths) {
            DynamicTroopRegistry.loadGlobalDefinitionFile(path, loadedById, loadedByKingdom, loadedGlobalDefinitions, loadedHireableCamps);
        }
    }

    private static void loadAddonTroops(Map<class_2960, DynamicTroopDefinition> loadedById, Map<class_2960, List<DynamicTroopDefinition>> loadedByKingdom, List<DynamicTroopDefinition> loadedGlobalDefinitions, Map<String, HireableCampDefinition> loadedHireableCamps) {
        for (String resourceDirectory : AgobAddonRegistry.troopResourceDirectories()) {
            for (String path : DynamicTroopRegistry.listResourceFiles(resourceDirectory)) {
                DynamicTroopRegistry.loadGlobalDefinitionFile(path, loadedById, loadedByKingdom, loadedGlobalDefinitions, loadedHireableCamps);
            }
        }
    }

    private static List<String> listKingdomResourceFiles(Kingdom kingdom) {
        String resourceDirectory = RESOURCE_ROOT + kingdom.getId().method_12832();
        return DynamicTroopRegistry.listResourceFiles(resourceDirectory);
    }

    private static List<String> listDefaultResourceFiles() {
        return DynamicTroopRegistry.listResourceFiles(DEFAULT_RESOURCE_DIRECTORY);
    }

    private static List<String> listResourceFiles(String resourceDirectory) {
        return ClasspathResourceHelper.listJsonResources(DynamicTroopRegistry.class.getClassLoader(), resourceDirectory);
    }

    private static void loadGlobalDefinitionFile(String path, Map<class_2960, DynamicTroopDefinition> loadedById, Map<class_2960, List<DynamicTroopDefinition>> loadedByKingdom, List<DynamicTroopDefinition> loadedGlobalDefinitions, Map<String, HireableCampDefinition> loadedHireableCamps) {
        try (InputStream stream = DynamicTroopRegistry.class.getClassLoader().getResourceAsStream(path);){
            HireableCampDefinition campDefinition;
            HireableCampDefinition previous;
            class_2960 defaultKingdomId;
            if (stream == null) {
                throw new IllegalStateException("Missing dynamic troop resource " + path);
            }
            JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            class_2960 class_29602 = defaultKingdomId = root.has("kingdom_id") ? class_2960.method_60654((String)root.get("kingdom_id").getAsString()) : class_2960.method_60655((String)"agob", (String)"the_riverlands");
            if (root.has("camp_site") && (previous = loadedHireableCamps.putIfAbsent((campDefinition = DynamicTroopRegistry.parseHireableCampDefinition(path, root.getAsJsonObject("camp_site"))).id(), campDefinition)) != null) {
                throw new IllegalStateException("Duplicate hireable camp id " + campDefinition.id() + " in " + path);
            }
            ArrayList<DynamicTroopDefinition> definitions = new ArrayList<DynamicTroopDefinition>();
            LinkedHashSet<class_2960> localTroopIds = new LinkedHashSet<class_2960>();
            JsonArray troops = root.getAsJsonArray("troops");
            for (JsonElement troopElement : troops) {
                JsonObject troopJson = troopElement.getAsJsonObject();
                class_2960 troopKingdomId = troopJson.has("kingdom_id") ? class_2960.method_60654((String)troopJson.get("kingdom_id").getAsString()) : defaultKingdomId;
                DynamicTroopDefinition definition = DynamicTroopRegistry.parseDefinition(path, troopKingdomId, troopJson);
                if (!localTroopIds.add(definition.id())) {
                    throw new IllegalStateException("Duplicate troop id " + String.valueOf(definition.id()) + " in " + path);
                }
                definitions.add(definition);
                loadedGlobalDefinitions.add(definition);
            }
            DynamicTroopRegistry.appendDefinitions(loadedById, loadedByKingdom, definitions, true);
        }
        catch (Exception exception) {
            throw new IllegalStateException("Failed to load dynamic troops from " + path, exception);
        }
    }

    private static void appendDefinitions(Map<class_2960, DynamicTroopDefinition> loadedById, Map<class_2960, List<DynamicTroopDefinition>> loadedByKingdom, List<DynamicTroopDefinition> definitions, boolean allowNewKingdomBucket) {
        for (DynamicTroopDefinition definition : definitions) {
            if (GotKingdoms.get(definition.kingdomId()) == null) {
                throw new IllegalStateException("Dynamic troop " + String.valueOf(definition.id()) + " references unknown kingdom " + String.valueOf(definition.kingdomId()));
            }
            DynamicTroopDefinition previous = loadedById.putIfAbsent(definition.id(), definition);
            if (previous != null) {
                throw new IllegalStateException("Duplicate dynamic troop id " + String.valueOf(definition.id()));
            }
            loadedByKingdom.computeIfAbsent(definition.kingdomId(), ignored -> new ArrayList()).add(definition);
        }
    }

    private static DynamicTroopDefinition parseDefinition(String path, class_2960 kingdomId, JsonObject json) {
        String value;
        String value2;
        class_2960 id = class_2960.method_60654((String)json.get("id").getAsString());
        DynamicTroopUnitType unitType = DynamicTroopUnitType.byId(json.get("unit_type").getAsString());
        if (unitType == null) {
            throw new IllegalStateException("Unknown dynamic troop unit type in " + path + ": " + String.valueOf(json.get("unit_type")));
        }
        int tier = json.get("tier").getAsInt();
        if (tier < 1) {
            throw new IllegalStateException("Dynamic troop tier must be >= 1 in " + path + " for " + String.valueOf(id));
        }
        ArrayList<String> traits = new ArrayList<String>();
        if (json.has("traits")) {
            for (Object element : json.getAsJsonArray("traits")) {
                String trait = element.getAsString();
                if (trait.isBlank()) continue;
                traits.add(trait);
            }
        }
        ArrayList<class_2960> upgradeTargets = new ArrayList<class_2960>();
        if (json.has("upgrade_targets")) {
            for (Object element : json.getAsJsonArray("upgrade_targets")) {
                upgradeTargets.add(class_2960.method_60654((String)element.getAsString()));
            }
        }
        ArrayList<String> disallowedHireKingdomIds = new ArrayList<String>();
        if (json.has("disallowed_hire_kingdom_ids")) {
            for (JsonElement element : json.getAsJsonArray("disallowed_hire_kingdom_ids")) {
                String disallowedKingdomId = element.getAsString();
                if (disallowedKingdomId.isBlank()) continue;
                disallowedHireKingdomIds.add(class_2960.method_60654((String)disallowedKingdomId).toString());
            }
        }
        class_2960 skinTexture = null;
        if (json.has("skin_texture") && !(value2 = json.get("skin_texture").getAsString()).isBlank()) {
            skinTexture = class_2960.method_60654((String)value2);
        }
        class_2960 hireCostItem = null;
        if (json.has("hire_cost_item") && !(value = json.get("hire_cost_item").getAsString()).isBlank()) {
            hireCostItem = class_2960.method_60654((String)value);
        }
        int hireCostAmount = json.has("hire_cost_amount") ? json.get("hire_cost_amount").getAsInt() : 0;
        Map<class_1304, class_2960> equipment = DynamicTroopRegistry.parseEquipment(path, json);
        Map<String, class_2960> mountEquipment = DynamicTroopRegistry.parseMountEquipment(json);
        return new DynamicTroopDefinition(id, kingdomId, json.get("display_name").getAsString(), unitType, tier, json.has("base_health") ? json.get("base_health").getAsDouble() : 0.0, json.has("base_damage") ? json.get("base_damage").getAsDouble() : 0.0, json.has("base_speed") ? json.get("base_speed").getAsDouble() : 0.0, json.has("village_spawn_only") && json.get("village_spawn_only").getAsBoolean(), json.has("required_religion_id") ? json.get("required_religion_id").getAsString() : null, json.has("required_house_id") ? json.get("required_house_id").getAsString() : null, disallowedHireKingdomIds, json.has("required_reputation_title") ? ReputationTitle.byId(json.get("required_reputation_title").getAsString()) : null, traits, upgradeTargets, skinTexture, hireCostItem, hireCostAmount, equipment, mountEquipment);
    }

    private static void validateUpgradeTargets(Map<class_2960, DynamicTroopDefinition> definitions) {
        for (DynamicTroopDefinition definition : definitions.values()) {
            for (class_2960 upgradeTarget : definition.upgradeTargets()) {
                DynamicTroopDefinition target = definitions.get(upgradeTarget);
                if (target == null) {
                    throw new IllegalStateException("Dynamic troop " + String.valueOf(definition.id()) + " references missing upgrade target " + String.valueOf(upgradeTarget));
                }
                if (target.kingdomId().equals((Object)definition.kingdomId())) continue;
                throw new IllegalStateException("Dynamic troop " + String.valueOf(definition.id()) + " cannot upgrade across kingdoms to " + String.valueOf(upgradeTarget));
            }
        }
    }

    private static void validateHireableCampTroops(Map<class_2960, DynamicTroopDefinition> definitions, Map<String, HireableCampDefinition> camps) {
        for (HireableCampDefinition camp : camps.values()) {
            for (class_2960 troopId : camp.troopIds()) {
                if (definitions.containsKey(troopId)) continue;
                throw new IllegalStateException("Hireable camp " + camp.id() + " references missing troop id " + String.valueOf(troopId));
            }
        }
    }

    private static HireableCampDefinition parseHireableCampDefinition(String path, JsonObject json) {
        String id = json.get("id").getAsString();
        String structurePath = json.get("structure_path").getAsString();
        String claimName = json.has("claim_name") ? json.get("claim_name").getAsString() : "Camp";
        String tierName = json.has("tier_name") ? json.get("tier_name").getAsString() : "Encampment";
        int claimRadiusChunks = json.has("claim_radius_chunks") ? json.get("claim_radius_chunks").getAsInt() : 1;
        int minSpawnCount = json.has("min_spawn_count") ? json.get("min_spawn_count").getAsInt() : 1;
        int maxSpawnCount = json.has("max_spawn_count") ? json.get("max_spawn_count").getAsInt() : minSpawnCount;
        boolean allowOtherKingdomSpawns = json.has("allow_other_kingdom_spawns") && json.get("allow_other_kingdom_spawns").getAsBoolean();
        int otherKingdomSpawnPercent = json.has("other_kingdom_spawn_percent") ? json.get("other_kingdom_spawn_percent").getAsInt() : 0;
        ArrayList<String> spawnKingdomIds = new ArrayList<String>();
        if (json.has("spawn_kingdom_ids")) {
            for (JsonElement element : json.getAsJsonArray("spawn_kingdom_ids")) {
                String kingdomId = element.getAsString();
                if (kingdomId.isBlank()) continue;
                spawnKingdomIds.add(class_2960.method_60654((String)kingdomId).toString());
            }
        }
        ArrayList<class_2960> troopIds = new ArrayList<class_2960>();
        if (json.has("troop_ids")) {
            for (JsonElement element : json.getAsJsonArray("troop_ids")) {
                troopIds.add(class_2960.method_60654((String)element.getAsString()));
            }
        }
        if (structurePath.isBlank()) {
            throw new IllegalStateException("Hireable camp in " + path + " is missing structure_path");
        }
        if (spawnKingdomIds.isEmpty()) {
            throw new IllegalStateException("Hireable camp " + id + " in " + path + " does not declare spawn_kingdom_ids");
        }
        if (troopIds.isEmpty()) {
            throw new IllegalStateException("Hireable camp " + id + " in " + path + " does not declare troop_ids");
        }
        return new HireableCampDefinition(id, structurePath, claimName, tierName, spawnKingdomIds, allowOtherKingdomSpawns, Math.max(0, Math.min(100, otherKingdomSpawnPercent)), Math.max(0, claimRadiusChunks), Math.max(0, minSpawnCount), Math.max(minSpawnCount, maxSpawnCount), troopIds);
    }

    private static class_1304 parseEquipmentSlot(String key) {
        return switch (key) {
            case "mainhand" -> class_1304.field_6173;
            case "offhand" -> class_1304.field_6171;
            case "head" -> class_1304.field_6169;
            case "chest" -> class_1304.field_6174;
            case "legs" -> class_1304.field_6172;
            case "feet" -> class_1304.field_6166;
            default -> throw new IllegalStateException("Unknown equipment slot: " + key);
        };
    }

    private static Map<class_1304, class_2960> parseEquipment(String path, JsonObject json) {
        LinkedHashMap<class_1304, class_2960> equipment = new LinkedHashMap<class_1304, class_2960>();
        if (!json.has("equipment")) {
            return Map.of();
        }
        JsonObject equipmentJson = json.getAsJsonObject("equipment");
        for (Map.Entry entry : equipmentJson.entrySet()) {
            String value = ((JsonElement)entry.getValue()).getAsString();
            if (value.isBlank()) continue;
            equipment.put(DynamicTroopRegistry.parseEquipmentSlot((String)entry.getKey()), class_2960.method_60654((String)value));
        }
        return Map.copyOf(equipment);
    }

    private static Map<String, class_2960> parseMountEquipment(JsonObject json) {
        LinkedHashMap<String, class_2960> equipment = new LinkedHashMap<String, class_2960>();
        if (!json.has("mount_equipment")) {
            return Map.of();
        }
        JsonObject mountJson = json.getAsJsonObject("mount_equipment");
        for (Map.Entry entry : mountJson.entrySet()) {
            String value = ((JsonElement)entry.getValue()).getAsString();
            if (value.isBlank()) continue;
            equipment.put((String)entry.getKey(), class_2960.method_60654((String)value));
        }
        return Map.copyOf(equipment);
    }
}

