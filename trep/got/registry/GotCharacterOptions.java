/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_2960
 */
package trep.got.registry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.class_2960;
import trep.got.GotMod;
import trep.got.api.internal.AgobAddonRegistry;
import trep.got.character.CharacterEquipmentPreset;
import trep.got.character.CharacterSelectionOption;
import trep.got.kingdom.Kingdom;
import trep.got.registry.GotKingdoms;
import trep.got.skill.SkillAttributeType;
import trep.got.util.ClasspathResourceHelper;

public final class GotCharacterOptions {
    private static final String RESOURCE_ROOT = "character_options";
    private static final int MAX_STARTING_POINTS = 20;
    private static final int MAX_CAP_BONUS_TOTAL = 20;
    private static Map<String, CharacterSelectionOption> byId = Map.of();
    private static Map<class_2960, CharacterSelectionOption> byKingdomId = Map.of();
    private static Map<class_2960, List<CharacterSelectionOption>> byKingdom = Map.of();
    private static List<CharacterSelectionOption> options = List.of();
    private static List<CharacterSelectionOption> primaryOptions = List.of();

    private GotCharacterOptions() {
    }

    public static void initialize() {
        ArrayList<CharacterSelectionOption> loadedOptions = new ArrayList<CharacterSelectionOption>();
        for (Kingdom kingdom : GotKingdoms.CHARACTER_OPTION_KINGDOMS) {
            loadedOptions.addAll(GotCharacterOptions.parseOptions(kingdom));
        }
        loadedOptions.addAll(AgobAddonRegistry.characterOptions());
        LinkedHashMap<String, CharacterSelectionOption> loadedById = new LinkedHashMap<String, CharacterSelectionOption>();
        LinkedHashMap<class_2960, CharacterSelectionOption> loadedByKingdomId = new LinkedHashMap<class_2960, CharacterSelectionOption>();
        LinkedHashMap<class_2960, List> loadedByKingdom = new LinkedHashMap<class_2960, List>();
        ArrayList<CharacterSelectionOption> loadedPrimaryOptions = new ArrayList<CharacterSelectionOption>();
        for (CharacterSelectionOption option : loadedOptions) {
            if (GotKingdoms.get(option.kingdomId()) == null) {
                throw new IllegalStateException("Character option " + option.id() + " references unknown kingdom " + String.valueOf(option.kingdomId()));
            }
            CharacterSelectionOption previous = loadedById.putIfAbsent(option.id(), option);
            if (previous != null) {
                throw new IllegalStateException("Duplicate character option id: " + option.id());
            }
            loadedByKingdom.computeIfAbsent(option.kingdomId(), unused -> new ArrayList()).add(option);
            if (!option.primary()) continue;
            CharacterSelectionOption kingdomPrevious = loadedByKingdomId.putIfAbsent(option.kingdomId(), option);
            if (kingdomPrevious != null) {
                throw new IllegalStateException("Duplicate primary character option for kingdom id: " + String.valueOf(option.kingdomId()));
            }
            loadedPrimaryOptions.add(option);
        }
        for (Kingdom kingdom : GotKingdoms.CHARACTER_OPTION_KINGDOMS) {
            if (!loadedByKingdom.containsKey(kingdom.getId()) || loadedByKingdomId.containsKey(kingdom.getId())) continue;
            throw new IllegalStateException("Missing primary character option for kingdom id: " + String.valueOf(kingdom.getId()));
        }
        options = List.copyOf(loadedOptions);
        primaryOptions = List.copyOf(loadedPrimaryOptions);
        byId = Map.copyOf(loadedById);
        byKingdomId = Map.copyOf(loadedByKingdomId);
        byKingdom = loadedByKingdom.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> List.copyOf((Collection)entry.getValue())));
        GotMod.LOGGER.info("Loaded {} character options", (Object)options.size());
    }

    public static List<CharacterSelectionOption> getAll() {
        return options;
    }

    public static List<CharacterSelectionOption> getPrimaryOptions() {
        return primaryOptions;
    }

    public static CharacterSelectionOption get(String optionId) {
        return byId.get(optionId);
    }

    public static CharacterSelectionOption getByKingdomId(class_2960 kingdomId) {
        if (kingdomId == null) {
            return null;
        }
        CharacterSelectionOption option = byKingdomId.get(kingdomId);
        if (option != null) {
            return option;
        }
        class_2960 cultureKingdomId = GotKingdoms.cultureKingdomId(kingdomId);
        return cultureKingdomId == null || cultureKingdomId.equals((Object)kingdomId) ? null : byKingdomId.get(cultureKingdomId);
    }

    public static List<CharacterSelectionOption> getByKingdom(class_2960 kingdomId) {
        if (kingdomId == null) {
            return List.of();
        }
        List<CharacterSelectionOption> options = byKingdom.get(kingdomId);
        if (options != null) {
            return options;
        }
        class_2960 cultureKingdomId = GotKingdoms.cultureKingdomId(kingdomId);
        return cultureKingdomId == null || cultureKingdomId.equals((Object)kingdomId) ? List.of() : byKingdom.getOrDefault(cultureKingdomId, List.of());
    }

    public static String getPrimaryHouseId(class_2960 kingdomId) {
        CharacterSelectionOption option = GotCharacterOptions.getByKingdomId(kingdomId);
        return option != null ? option.id() : "";
    }

    public static String resolveHouseArmorId(String optionId, class_2960 kingdomId) {
        CharacterSelectionOption option = GotCharacterOptions.get(optionId);
        if (option != null && !option.houseArmorId().isBlank()) {
            return option.houseArmorId();
        }
        CharacterSelectionOption kingdomOption = GotCharacterOptions.getByKingdomId(kingdomId);
        return kingdomOption != null ? kingdomOption.houseArmorId() : "";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static List<CharacterSelectionOption> parseOptions(Kingdom kingdom) {
        class_2960 resourceId = class_2960.method_60655((String)"agob", (String)("character_options/" + kingdom.getId().method_12832() + ".json"));
        String classpathPath = "data/" + resourceId.method_12836() + "/" + resourceId.method_12832();
        if (!ClasspathResourceHelper.resourceExists(GotCharacterOptions.class.getClassLoader(), classpathPath)) {
            return List.of();
        }
        try (InputStream stream = GotCharacterOptions.class.getClassLoader().getResourceAsStream(classpathPath);){
            boolean defaultSmallArms;
            if (stream == null) {
                List<CharacterSelectionOption> list = List.of();
                return list;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            JsonObject root = JsonParser.parseReader((Reader)reader).getAsJsonObject();
            class_2960 kingdomId = class_2960.method_60654((String)root.get("kingdom_id").getAsString());
            String displayName = root.get("display_name").getAsString();
            if (GotKingdoms.get(kingdomId) == null) {
                GotMod.LOGGER.warn("Ignoring character option {} with unknown kingdom {}", (Object)resourceId, (Object)kingdomId);
                List<CharacterSelectionOption> list = List.of();
                return list;
            }
            JsonObject defaultEquipmentObject = root.has("equipment") ? root.getAsJsonObject("equipment") : new JsonObject();
            List<String> defaultSpawnBiomes = GotCharacterOptions.parseSpawnBiomes(resourceId, root);
            String defaultPreviewSkinTextureValue = GotCharacterOptions.getString(root, "preview_skin_texture");
            class_2960 defaultPreviewSkinTexture = defaultPreviewSkinTextureValue.isBlank() ? null : class_2960.method_60654((String)defaultPreviewSkinTextureValue);
            boolean bl = defaultSmallArms = root.has("small_arms") && root.get("small_arms").getAsBoolean();
            if (!root.has("houses")) {
                throw new IllegalStateException("Missing houses in " + String.valueOf(resourceId));
            }
            ArrayList<CharacterSelectionOption> loadedOptions = new ArrayList<CharacterSelectionOption>();
            boolean foundPrimary = false;
            for (JsonElement houseElement : root.getAsJsonArray("houses")) {
                JsonObject equipmentObject;
                JsonObject houseObject = houseElement.getAsJsonObject();
                String id = houseObject.get("id").getAsString();
                class_2960 texture = class_2960.method_60654((String)houseObject.get("texture").getAsString());
                String previewSkinTextureValue = GotCharacterOptions.getString(houseObject, "preview_skin_texture");
                class_2960 previewSkinTexture = previewSkinTextureValue.isBlank() ? defaultPreviewSkinTexture : class_2960.method_60654((String)previewSkinTextureValue);
                boolean smallArms = houseObject.has("small_arms") ? houseObject.get("small_arms").getAsBoolean() : defaultSmallArms;
                String houseDisplayName = houseObject.get("house_display_name").getAsString();
                boolean primary = houseObject.has("primary") && houseObject.get("primary").getAsBoolean();
                JsonObject jsonObject = equipmentObject = houseObject.has("equipment") ? houseObject.getAsJsonObject("equipment") : defaultEquipmentObject;
                if (!houseObject.has("starting_skill_points")) {
                    throw new IllegalStateException("Missing starting_skill_points for house " + id + " in " + String.valueOf(resourceId));
                }
                JsonObject skillsObject = houseObject.getAsJsonObject("starting_skill_points");
                JsonObject maxSkillCapBonusesObject = houseObject.has("max_skill_cap_bonuses") ? houseObject.getAsJsonObject("max_skill_cap_bonuses") : new JsonObject();
                List<String> spawnBiomes = houseObject.has("spawn_biomes") ? GotCharacterOptions.parseSpawnBiomes(resourceId, houseObject) : defaultSpawnBiomes;
                loadedOptions.add(new CharacterSelectionOption(id, kingdomId, texture, previewSkinTexture, smallArms, displayName, houseDisplayName, primary, new CharacterEquipmentPreset(GotCharacterOptions.getString(equipmentObject, "head"), GotCharacterOptions.getString(equipmentObject, "chest"), GotCharacterOptions.getString(equipmentObject, "legs"), GotCharacterOptions.getString(equipmentObject, "feet"), GotCharacterOptions.getString(equipmentObject, "main_hand"), GotCharacterOptions.getString(equipmentObject, "off_hand")), GotCharacterOptions.parseStartingSkillPoints(resourceId, skillsObject), GotCharacterOptions.parseMaxSkillCapBonuses(resourceId, id, maxSkillCapBonusesObject), GotCharacterOptions.getString(houseObject, "house_armor_id"), spawnBiomes));
                foundPrimary |= primary;
            }
            if (!foundPrimary) {
                throw new IllegalStateException("Missing primary house option in " + String.valueOf(resourceId));
            }
            List list = List.copyOf(loadedOptions);
            return list;
        }
        catch (IOException | IllegalStateException e) {
            GotMod.LOGGER.error("Failed to parse character option {}", (Object)resourceId, (Object)e);
            return List.of();
        }
    }

    private static Map<SkillAttributeType, Integer> parseStartingSkillPoints(class_2960 resourceId, JsonObject skillsObject) {
        EnumMap<SkillAttributeType, Integer> points = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            points.put(type, 0);
        }
        for (Map.Entry entry : skillsObject.entrySet()) {
            SkillAttributeType type = SkillAttributeType.byId((String)entry.getKey());
            if (type == null) {
                throw new IllegalStateException("Unknown skill attribute '" + (String)entry.getKey() + "' in " + String.valueOf(resourceId));
            }
            int value = ((JsonElement)entry.getValue()).getAsInt();
            if (value < 0 || value > 20) {
                throw new IllegalStateException("Invalid starting points " + value + " for " + type.getId() + " in " + String.valueOf(resourceId));
            }
            points.put(type, value);
        }
        return points;
    }

    private static List<String> parseSpawnBiomes(class_2960 resourceId, JsonObject root) {
        if (!root.has("spawn_biomes")) {
            throw new IllegalStateException("Missing spawn_biomes in " + String.valueOf(resourceId));
        }
        ArrayList<String> spawnBiomes = new ArrayList<String>();
        for (JsonElement element : root.getAsJsonArray("spawn_biomes")) {
            String biome = element.getAsString();
            if (biome.isBlank()) {
                throw new IllegalStateException("Blank spawn biome in " + String.valueOf(resourceId));
            }
            spawnBiomes.add(biome);
        }
        if (spawnBiomes.isEmpty()) {
            throw new IllegalStateException("spawn_biomes cannot be empty in " + String.valueOf(resourceId));
        }
        return List.copyOf(spawnBiomes);
    }

    private static String getString(JsonObject json, String key) {
        return json.has(key) ? json.get(key).getAsString() : "";
    }

    private static Map<SkillAttributeType, Integer> parseMaxSkillCapBonuses(class_2960 resourceId, String houseId, JsonObject bonusesObject) {
        EnumMap<SkillAttributeType, Integer> bonuses = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            bonuses.put(type, 0);
        }
        int totalBonus = 0;
        for (Map.Entry entry : bonusesObject.entrySet()) {
            SkillAttributeType type = SkillAttributeType.byId((String)entry.getKey());
            if (type == null) {
                throw new IllegalStateException("Unknown max skill cap attribute '" + (String)entry.getKey() + "' in " + String.valueOf(resourceId) + " for house " + houseId);
            }
            int value = ((JsonElement)entry.getValue()).getAsInt();
            if (value < 0 || value % 5 != 0) {
                throw new IllegalStateException("Invalid max skill cap bonus " + value + " for " + type.getId() + " in " + String.valueOf(resourceId) + " for house " + houseId + ". Must be a non-negative multiple of 5.");
            }
            totalBonus += value;
            bonuses.put(type, value);
        }
        if (totalBonus > 20) {
            throw new IllegalStateException("Total max skill cap bonus " + totalBonus + " exceeds 20 in " + String.valueOf(resourceId) + " for house " + houseId);
        }
        return bonuses;
    }
}

