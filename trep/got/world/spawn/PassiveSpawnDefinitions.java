/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.fabricmc.fabric.api.biome.v1.BiomeModifications
 *  net.fabricmc.fabric.api.biome.v1.BiomeSelectors
 *  net.minecraft.class_1299
 *  net.minecraft.class_1311
 *  net.minecraft.class_1959
 *  net.minecraft.class_2960
 *  net.minecraft.class_5321
 *  net.minecraft.class_7923
 */
package trep.got.world.spawn;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.class_1299;
import net.minecraft.class_1311;
import net.minecraft.class_1959;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7923;
import trep.got.world.biome.GotBiomeKeys;
import trep.got.world.gen.MapChunkGenerator;

public final class PassiveSpawnDefinitions {
    private static final Set<String> FARM_ANIMALS = Set.of("minecraft:sheep", "minecraft:cow", "minecraft:pig", "minecraft:chicken", "minecraft:horse");
    private static final Set<String> GRASS = Set.of("minecraft:grass_block");
    private static final Set<String> GOAT = Set.of("minecraft:grass_block", "minecraft:stone", "minecraft:snow", "minecraft:snow_block", "minecraft:packed_ice", "minecraft:gravel");
    private static final Set<String> FOX = Set.of("minecraft:grass_block", "minecraft:snow", "minecraft:snow_block", "minecraft:podzol", "minecraft:coarse_dirt");
    private static final Set<String> WOLF = Set.of("minecraft:grass_block", "minecraft:snow", "minecraft:snow_block", "minecraft:coarse_dirt", "minecraft:podzol");
    private static final Set<String> RABBIT = Set.of("minecraft:grass_block", "minecraft:snow", "minecraft:snow_block", "minecraft:sand");

    private PassiveSpawnDefinitions() {
    }

    public static void register(class_1299<?> sumpter, class_1299<?> rouncey, class_1299<?> destrier, class_1299<?> donkey, class_1299<?> packhorse, class_1299<?> deer, class_1299<?> direwolf) {
        List<SpawnDefinition> definitions = PassiveSpawnDefinitions.createDefinitions(sumpter, rouncey, destrier, donkey, packhorse, deer, direwolf);
        PassiveSpawnDefinitions.validateFinalPools(definitions);
        for (SpawnDefinition definition : definitions) {
            BiomeModifications.addSpawn((Predicate)BiomeSelectors.includeByKey((class_5321[])((class_5321[])definition.biomes().toArray(class_5321[]::new))), (class_1311)class_1311.field_6294, definition.entityType(), (int)definition.weight(), (int)definition.minCount(), (int)definition.maxCount());
        }
    }

    private static List<SpawnDefinition> createDefinitions(class_1299<?> sumpter, class_1299<?> rouncey, class_1299<?> destrier, class_1299<?> donkey, class_1299<?> packhorse, class_1299<?> deer, class_1299<?> direwolf) {
        List<class_5321<class_1959>> highTierBiomes = List.of(GotBiomeKeys.DORNE, GotBiomeKeys.DORNE_HILLS, GotBiomeKeys.DORNE_OASIS, GotBiomeKeys.DORNE_SCRUBLAND, GotBiomeKeys.THE_VALE, GotBiomeKeys.THE_VALE_FOREST, GotBiomeKeys.THE_VALE_HILLS, GotBiomeKeys.THE_VALE_FOOTHILL, GotBiomeKeys.THE_VALE_VALLEY, GotBiomeKeys.THE_VALE_MEADOW);
        List<class_5321<class_1959>> lowTierBiomes = List.of(GotBiomeKeys.THE_NORTH, GotBiomeKeys.THE_NORTH_FOREST, GotBiomeKeys.THE_NORTH_HILLS, GotBiomeKeys.THE_NORTH_WOLFSWOOD, GotBiomeKeys.THE_RIVERLANDS, GotBiomeKeys.THE_RIVERLANDS_FOREST, GotBiomeKeys.THE_RIVERLANDS_HILLS, GotBiomeKeys.THE_RIVERLANDS_MARSH);
        HashSet<class_5321<class_1959>> specializedBiomes = new HashSet<class_5321<class_1959>>(highTierBiomes);
        specializedBiomes.addAll(lowTierBiomes);
        List<class_5321<class_1959>> generalBiomes = GotBiomeKeys.ALL.stream().filter(key -> key != GotBiomeKeys.SEA && key != GotBiomeKeys.THE_WALL).filter(key -> key != GotBiomeKeys.ALWAYS_WINTER && key != GotBiomeKeys.ALWAYS_WINTER_FOREST && key != GotBiomeKeys.ALWAYS_WINTER_HILLS).filter(key -> !specializedBiomes.contains(key)).toList();
        ArrayList<SpawnDefinition> definitions = new ArrayList<SpawnDefinition>();
        PassiveSpawnDefinitions.addMountDefinitions(definitions, generalBiomes, sumpter, rouncey, destrier, donkey, packhorse, 4, 3, 1, 2, 1);
        PassiveSpawnDefinitions.addMountDefinitions(definitions, highTierBiomes, sumpter, rouncey, destrier, donkey, packhorse, 2, 5, 3, 2, 1);
        PassiveSpawnDefinitions.addMountDefinitions(definitions, lowTierBiomes, sumpter, rouncey, destrier, donkey, packhorse, 6, 2, 1, 2, 1);
        definitions.add(new SpawnDefinition("regional deer", List.of(GotBiomeKeys.THE_CROWNLANDS, GotBiomeKeys.THE_CROWNLANDS_FOREST, GotBiomeKeys.THE_CROWNLANDS_HILLS, GotBiomeKeys.THE_CROWNLANDS_KINGSWOOD, GotBiomeKeys.THE_STORMLANDS, GotBiomeKeys.THE_STORMLANDS_FOREST, GotBiomeKeys.THE_STORMLANDS_HILLS, GotBiomeKeys.THE_STORMLANDS_RAINWOOD), deer, 5, 2, 4));
        definitions.add(new SpawnDefinition("North direwolves", List.of(GotBiomeKeys.THE_NORTH, GotBiomeKeys.THE_NORTH_FOREST, GotBiomeKeys.THE_NORTH_HILLS, GotBiomeKeys.THE_NORTH_WOLFSWOOD), direwolf, 2, 1, 2));
        return List.copyOf(definitions);
    }

    private static void addMountDefinitions(List<SpawnDefinition> definitions, List<class_5321<class_1959>> biomes, class_1299<?> sumpter, class_1299<?> rouncey, class_1299<?> destrier, class_1299<?> donkey, class_1299<?> packhorse, int sumpterWeight, int rounceyWeight, int destrierWeight, int donkeyWeight, int packhorseWeight) {
        definitions.add(new SpawnDefinition("mounts", biomes, sumpter, sumpterWeight, 2, 4));
        definitions.add(new SpawnDefinition("mounts", biomes, rouncey, rounceyWeight, 2, 3));
        definitions.add(new SpawnDefinition("mounts", biomes, destrier, destrierWeight, 1, 2));
        definitions.add(new SpawnDefinition("mounts", biomes, donkey, donkeyWeight, 1, 3));
        definitions.add(new SpawnDefinition("mounts", biomes, packhorse, packhorseWeight, 1, 2));
    }

    private static void validateFinalPools(List<SpawnDefinition> definitions) {
        PassiveSpawnDefinitions.validateGeneratorSurfaceMatrix(definitions);
        PassiveSpawnDefinitions.validateAlwaysWinterMountExclusion(definitions);
        LinkedHashMap<SpawnPair, String> owners = new LinkedHashMap<SpawnPair, String>();
        HashMap<String, Set<String>> spawnSurfaces = new HashMap<String, Set<String>>();
        Set<String> mountSurfaces = PassiveSpawnDefinitions.readBlockTag("mounts_spawnable_on");
        Set<String> deerSurfaces = PassiveSpawnDefinitions.readBlockTag("deer_spawnable_on");
        Set<String> direwolfSurfaces = PassiveSpawnDefinitions.readBlockTag("direwolves_spawnable_on");
        for (String string : FARM_ANIMALS) {
            spawnSurfaces.put(string, GRASS);
        }
        spawnSurfaces.put("minecraft:goat", GOAT);
        spawnSurfaces.put("minecraft:fox", FOX);
        spawnSurfaces.put("minecraft:wolf", WOLF);
        spawnSurfaces.put("minecraft:rabbit", RABBIT);
        for (class_5321 class_53212 : GotBiomeKeys.ALL) {
            for (String entityId : PassiveSpawnDefinitions.readBiomeCreatures((class_5321<class_1959>)class_53212)) {
                PassiveSpawnDefinitions.registerPair(owners, new SpawnPair((class_5321<class_1959>)class_53212, entityId), "biome JSON");
                PassiveSpawnDefinitions.validateSurfaceCompatibility((class_5321<class_1959>)class_53212, entityId, (Set)spawnSurfaces.get(entityId));
            }
        }
        for (SpawnDefinition spawnDefinition : definitions) {
            Set<String> allowedSurfaces;
            class_2960 entityId = class_7923.field_41177.method_10221(spawnDefinition.entityType());
            String entityIdString = entityId.toString();
            Set<String> set = "agob:deer".equals(entityIdString) ? deerSurfaces : (allowedSurfaces = "agob:direwolf".equals(entityIdString) ? direwolfSurfaces : mountSurfaces);
            if (spawnDefinition.weight() <= 0 || spawnDefinition.minCount() <= 0 || spawnDefinition.maxCount() < spawnDefinition.minCount()) {
                throw new IllegalStateException("Invalid passive spawn counts for " + entityIdString + " in " + spawnDefinition.source());
            }
            for (class_5321<class_1959> biome : spawnDefinition.biomes()) {
                PassiveSpawnDefinitions.registerPair(owners, new SpawnPair(biome, entityIdString), "Java " + spawnDefinition.source());
                PassiveSpawnDefinitions.validateSurfaceCompatibility(biome, entityIdString, allowedSurfaces);
            }
        }
    }

    /*
     * Enabled aggressive exception aggregation
     */
    private static List<String> readBiomeCreatures(class_5321<class_1959> biome) {
        String path = "data/agob/worldgen/biome/" + biome.method_29177().method_12832() + ".json";
        try (InputStream stream = PassiveSpawnDefinitions.class.getClassLoader().getResourceAsStream(path);){
            List list;
            JsonArray creatures;
            InputStreamReader reader;
            block17: {
                if (stream == null) {
                    throw new IllegalStateException("Missing AGoB biome spawn source " + path);
                }
                reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                JsonObject root = JsonParser.parseReader((Reader)reader).getAsJsonObject();
                JsonObject spawners = root.has("spawners") ? root.getAsJsonObject("spawners") : null;
                JsonArray jsonArray = creatures = spawners != null && spawners.has("creature") ? spawners.getAsJsonArray("creature") : null;
                if (creatures != null) break block17;
                List<String> list2 = List.of();
                reader.close();
                return list2;
            }
            try {
                ArrayList<String> entityIds = new ArrayList<String>();
                for (JsonElement creature : creatures) {
                    entityIds.add(creature.getAsJsonObject().get("type").getAsString());
                }
                list = List.copyOf(entityIds);
            }
            catch (Throwable throwable) {
                try {
                    reader.close();
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            reader.close();
            return list;
        }
        catch (IOException exception) {
            throw new IllegalStateException("Failed to validate AGoB biome spawn source " + path, exception);
        }
    }

    /*
     * Enabled aggressive exception aggregation
     */
    private static Set<String> readBlockTag(String tagPath) {
        String path = "data/agob/tags/block/" + tagPath + ".json";
        try (InputStream stream = PassiveSpawnDefinitions.class.getClassLoader().getResourceAsStream(path);){
            Set set;
            if (stream == null) {
                throw new IllegalStateException("Missing AGoB passive spawn surface tag " + path);
            }
            try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);){
                JsonObject root = JsonParser.parseReader((Reader)reader).getAsJsonObject();
                JsonArray values = root.getAsJsonArray("values");
                LinkedHashSet<String> blockIds = new LinkedHashSet<String>();
                for (JsonElement value : values) {
                    if (!value.isJsonPrimitive() || value.getAsString().startsWith("#")) {
                        throw new IllegalStateException("Passive spawn surface tags must contain direct block IDs: " + path);
                    }
                    blockIds.add(value.getAsString());
                }
                if (blockIds.isEmpty()) {
                    throw new IllegalStateException("Passive spawn surface tag is empty: " + path);
                }
                set = Set.copyOf(blockIds);
            }
            return set;
        }
        catch (IOException exception) {
            throw new IllegalStateException("Failed to validate AGoB passive spawn surface tag " + path, exception);
        }
    }

    private static void registerPair(Map<SpawnPair, String> owners, SpawnPair pair, String source) {
        String previousSource = owners.putIfAbsent(pair, source);
        if (previousSource != null) {
            throw new IllegalStateException("Duplicate passive spawn " + pair.entityId() + " in " + String.valueOf(pair.biome().method_29177()) + " from " + previousSource + " and " + source);
        }
    }

    private static void validateSurfaceCompatibility(class_5321<class_1959> biome, String entityId, Set<String> allowedSurfaces) {
        if (allowedSurfaces == null) {
            return;
        }
        Set<String> generatedSurfaces = MapChunkGenerator.finalPassiveSpawnSubstrates(biome);
        if (generatedSurfaces.stream().noneMatch(allowedSurfaces::contains)) {
            throw new IllegalStateException("Passive spawn " + entityId + " has no compatible generated surface in " + String.valueOf(biome.method_29177()));
        }
    }

    private static void validateGeneratorSurfaceMatrix(List<SpawnDefinition> definitions) {
        if (GotBiomeKeys.ALL.size() != 65) {
            throw new IllegalStateException("Passive spawn surface matrix expected 65 AGoB biomes, found " + GotBiomeKeys.ALL.size());
        }
        HashSet<class_5321<class_1959>> biomesWithCreatures = new HashSet<class_5321<class_1959>>();
        for (class_5321<class_1959> biome : GotBiomeKeys.ALL) {
            if (PassiveSpawnDefinitions.readBiomeCreatures(biome).isEmpty()) continue;
            biomesWithCreatures.add(biome);
        }
        for (SpawnDefinition definition : definitions) {
            biomesWithCreatures.addAll(definition.biomes());
        }
        HashSet<class_2960> biomeIds = new HashSet<class_2960>();
        for (class_5321<class_1959> biome : GotBiomeKeys.ALL) {
            if (!biomeIds.add(biome.method_29177())) {
                throw new IllegalStateException("Duplicate AGoB biome in passive spawn surface matrix: " + String.valueOf(biome.method_29177()));
            }
            if (!MapChunkGenerator.finalPassiveSpawnSubstrates(biome).isEmpty() || !biomesWithCreatures.contains(biome)) continue;
            throw new IllegalStateException("Empty final passive spawn substrate contract for biome with CREATURE declarations: " + String.valueOf(biome.method_29177()));
        }
        MapChunkGenerator.auditNorthernPassiveSpawnSurfaces();
    }

    private static void validateAlwaysWinterMountExclusion(List<SpawnDefinition> definitions) {
        Set<class_5321<class_1959>> alwaysWinter = Set.of(GotBiomeKeys.ALWAYS_WINTER, GotBiomeKeys.ALWAYS_WINTER_FOREST, GotBiomeKeys.ALWAYS_WINTER_HILLS);
        for (SpawnDefinition definition : definitions) {
            if (!"mounts".equals(definition.source())) continue;
            if (!definition.biomes().stream().anyMatch(alwaysWinter::contains)) continue;
            throw new IllegalStateException("Always Winter mount exclusion is an intentional balance rule");
        }
    }

    private record SpawnDefinition(String source, List<class_5321<class_1959>> biomes, class_1299<?> entityType, int weight, int minCount, int maxCount) {
    }

    private record SpawnPair(class_5321<class_1959> biome, String entityId) {
    }
}

