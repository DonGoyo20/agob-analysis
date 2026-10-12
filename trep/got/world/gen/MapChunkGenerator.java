/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap
 *  it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  net.minecraft.class_1923
 *  net.minecraft.class_1948
 *  net.minecraft.class_1959
 *  net.minecraft.class_1966
 *  net.minecraft.class_2246
 *  net.minecraft.class_2320
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2353
 *  net.minecraft.class_2382
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_2488
 *  net.minecraft.class_2680
 *  net.minecraft.class_2756
 *  net.minecraft.class_2769
 *  net.minecraft.class_2791
 *  net.minecraft.class_2794
 *  net.minecraft.class_2893$class_2894
 *  net.minecraft.class_2902
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_2919
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3233
 *  net.minecraft.class_3341
 *  net.minecraft.class_3491
 *  net.minecraft.class_3492
 *  net.minecraft.class_3499
 *  net.minecraft.class_3532
 *  net.minecraft.class_3541
 *  net.minecraft.class_3793
 *  net.minecraft.class_4543
 *  net.minecraft.class_4966
 *  net.minecraft.class_5138
 *  net.minecraft.class_5321
 *  net.minecraft.class_5425
 *  net.minecraft.class_5539
 *  net.minecraft.class_5819
 *  net.minecraft.class_5820
 *  net.minecraft.class_6544$class_6552
 *  net.minecraft.class_6673
 *  net.minecraft.class_6748
 *  net.minecraft.class_6880
 *  net.minecraft.class_6910
 *  net.minecraft.class_6916
 *  net.minecraft.class_7138
 *  net.minecraft.class_7923
 */
package trep.got.world.gen;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import net.minecraft.class_1923;
import net.minecraft.class_1948;
import net.minecraft.class_1959;
import net.minecraft.class_1966;
import net.minecraft.class_2246;
import net.minecraft.class_2320;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2488;
import net.minecraft.class_2680;
import net.minecraft.class_2756;
import net.minecraft.class_2769;
import net.minecraft.class_2791;
import net.minecraft.class_2794;
import net.minecraft.class_2893;
import net.minecraft.class_2902;
import net.minecraft.class_2919;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3233;
import net.minecraft.class_3341;
import net.minecraft.class_3491;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_3532;
import net.minecraft.class_3541;
import net.minecraft.class_3793;
import net.minecraft.class_4543;
import net.minecraft.class_4966;
import net.minecraft.class_5138;
import net.minecraft.class_5321;
import net.minecraft.class_5425;
import net.minecraft.class_5539;
import net.minecraft.class_5819;
import net.minecraft.class_5820;
import net.minecraft.class_6544;
import net.minecraft.class_6673;
import net.minecraft.class_6748;
import net.minecraft.class_6880;
import net.minecraft.class_6910;
import net.minecraft.class_6916;
import net.minecraft.class_7138;
import net.minecraft.class_7923;
import trep.got.GotMod;
import trep.got.castle.CastleWorldState;
import trep.got.kingdom.Kingdom;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotCastles;
import trep.got.registry.GotKingdoms;
import trep.got.troop.DynamicTroopRegistry;
import trep.got.troop.HireableCampDefinition;
import trep.got.world.biome.GotBiomeKeys;
import trep.got.world.gen.MapBiomeSource;
import trep.got.world.map.MapImage;
import trep.got.world.map.RoadMapHelper;
import trep.got.world.structure.cache.CastlePlanCache;
import trep.got.world.structure.castle.CastleWorldgenPlan;
import trep.got.world.structure.castle.CastleWorldgenStructureApplier;
import trep.got.world.structure.castle.DynamicCastleCoreApplier;
import trep.got.world.structure.castle.DynamicCastleTerrainApplier;
import trep.got.world.structure.castle.DynamicCastleWallApplier;
import trep.got.world.structure.index.CastleChunkIndex;

public class MapChunkGenerator
extends class_2794 {
    private static final boolean CHUNK_NATIVE_CASTLE_SURFACE_ENABLED = true;
    private static final Set<String> KNOWN_BIOME_PATHS = Set.copyOf(GotBiomeKeys.ALL.stream().map(key -> key.method_29177().method_12832()).toList());
    public static final MapCodec<MapChunkGenerator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group((App)class_1966.field_24713.fieldOf("biome_source").forGetter(generator -> generator.field_12761)).apply((Applicative)instance, MapChunkGenerator::new));
    private static final int MIN_Y = -64;
    private static final int WORLD_HEIGHT = 384;
    private static final int SEA_LEVEL = 63;
    private static final int TOP_Y = 319;
    private static final class_2680 AIR = class_2246.field_10124.method_9564();
    private static final class_2680 BEDROCK = class_2246.field_9987.method_9564();
    private static final class_2680 STONE = class_2246.field_10340.method_9564();
    private static final class_2680 DEEPSLATE = class_2246.field_28888.method_9564();
    private static final class_2680 DIRT = class_2246.field_10566.method_9564();
    private static final class_2680 DIRT_PATH = class_2246.field_10194.method_9564();
    private static final class_2680 COARSE_DIRT = class_2246.field_10253.method_9564();
    private static final class_2680 GRASS = class_2246.field_10219.method_9564();
    private static final class_2680 PODZOL = class_2246.field_10520.method_9564();
    private static final class_2680 SAND = class_2246.field_10102.method_9564();
    private static final class_2680 RED_SAND = class_2246.field_10534.method_9564();
    private static final class_2680 GRAVEL = class_2246.field_10255.method_9564();
    private static final class_2680 CLAY = class_2246.field_10460.method_9564();
    private static final class_2680 WATER = class_2246.field_10382.method_9564();
    private static final class_2680 LAVA = class_2246.field_10164.method_9564();
    private static final class_2680 ICE = class_2246.field_10295.method_9564();
    private static final class_2680 SNOW = class_2246.field_10491.method_9564();
    private static final class_2680 PACKED_ICE = class_2246.field_10225.method_9564();
    private static final class_2680 BLUE_ICE = class_2246.field_10384.method_9564();
    private static final class_2680 GRANITE = class_2246.field_10474.method_9564();
    private static final class_2680 DIORITE = class_2246.field_10508.method_9564();
    private static final class_2680 ANDESITE = class_2246.field_10115.method_9564();
    private static final class_2680 TUFF = class_2246.field_27165.method_9564();
    private static final class_2680 LIMESTONE = GotBlocks.LIMESTONE.method_9564();
    private static final class_2680 SILTSTONE = GotBlocks.SILTSTONE.method_9564();
    private static final class_2680 ASH_STONE = GotBlocks.ASH_STONE.method_9564();
    private static final class_2680 CHARRED_STONE = GotBlocks.CHARRED_STONE.method_9564();
    private static final class_2680 MARBLE = GotBlocks.MARBLE.method_9564();
    private static final class_2680 MOSSY_MARBLE = GotBlocks.MOSSY_MARBLE.method_9564();
    private static final class_2680 BLUESTONE = GotBlocks.BLUESTONE.method_9564();
    private static final class_2680 VOLCANIC_STONE = GotBlocks.VOLCANIC_STONE.method_9564();
    private static final class_2680 WILD_TOMATO = GotBlocks.WILD_TOMATO.method_9564();
    private static final class_2680 WILD_ORANGE = GotBlocks.WILD_ORANGE.method_9564();
    private static final class_2680 WILD_TURNIP = GotBlocks.WILD_TURNIP.method_9564();
    private static final class_2680 WILD_CHAMPIGNON = GotBlocks.WILD_CHAMPIGNON.method_9564();
    private static final class_2680 COBBLESTONE = class_2246.field_10445.method_9564();
    private static final class_2680 BLACKSTONE = class_2246.field_23869.method_9564();
    private static final class_2680 BASALT = class_2246.field_22091.method_9564();
    private static final class_2680 MOSSY_COBBLESTONE = class_2246.field_9989.method_9564();
    private static final class_3541 BROAD_NOISE = new class_3541(class_5819.method_43049((long)12648430L));
    private static final class_3541 RIDGE_NOISE = new class_3541(class_5819.method_43049((long)659918L));
    private static final class_3541 DETAIL_NOISE = new class_3541(class_5819.method_43049((long)48879L));
    private static final class_3541 MICRO_NOISE = new class_3541(class_5819.method_43049((long)53374L));
    private static final class_3541 DECORATION_NOISE = new class_3541(class_5819.method_43049((long)20970L));
    private static final class_3541 TREE_NOISE = new class_3541(class_5819.method_43049((long)32485L));
    private static final class_3541 CAVE_NOISE_A = new class_3541(class_5819.method_43049((long)829153L));
    private static final class_3541 CAVE_NOISE_B = new class_3541(class_5819.method_43049((long)829154L));
    private static final class_3541 CAVE_NOISE_C = new class_3541(class_5819.method_43049((long)829155L));
    private static final class_3541 CAVE_NOISE_D = new class_3541(class_5819.method_43049((long)829156L));
    private static final class_3541 ORE_NOISE = new class_3541(class_5819.method_43049((long)44625L));
    private static final int CAVE_CELL_SIZE = 96;
    private static final int CAVE_CELL_MARGIN = 18;
    private static final int CAVE_SHAFT_CELL_SIZE = 192;
    private static final double CAVE_SPAGHETTI_HORIZONTAL_SCALE = 92.0;
    private static final double CAVE_SPAGHETTI_VERTICAL_SCALE = 38.0;
    private static final int STONE_CLUSTER_HORIZONTAL_SIZE = 32;
    private static final int STONE_CLUSTER_VERTICAL_SIZE = 18;
    private static final int GRAVEL_CLUSTER_HORIZONTAL_SIZE = 40;
    private static final int GRAVEL_CLUSTER_VERTICAL_SIZE = 16;
    private static final int[][] CARDINAL_FOUR_BLOCK_OFFSETS = new int[][]{{-4, 0}, {4, 0}, {0, -4}, {0, 4}};
    private static final String ROAD_MAP_RESOURCE = "assets/agob/map_road.png";
    private static final int ROAD_COLOR = 13154156;
    private static final int DEEPSLATE_FULL_Y = 0;
    private static final int DEEPSLATE_TRANSITION_TOP_Y = 8;
    private static final int NATURAL_STRUCTURE_MAX_CUT_DEPTH = 2;
    private static final int NATURAL_STRUCTURE_MAX_FLOOR_DELTA = 10;
    private static final int NATURAL_STRUCTURE_MAX_TERRAIN_DELTA = 12;
    private static final int SNOWY_STRUCTURE_MAX_CUT_DEPTH = 4;
    private static final int SNOWY_STRUCTURE_MAX_FLOOR_DELTA = 18;
    private static final int SNOWY_STRUCTURE_MAX_TERRAIN_DELTA = 20;
    private static final int WILD_STRUCTURE_REGION_SPACING_CHUNKS = 24;
    private static final int WILD_STRUCTURE_REGION_BORDER_CHUNKS = 3;
    private static final int WILD_STRUCTURE_CASTLE_BUFFER_CHUNKS = 2;
    private static final int WILD_STRUCTURE_MAX_FLOOR_DELTA = 7;
    private static final int WILD_STRUCTURE_MAX_TERRAIN_DELTA = 9;
    private static final int MOTION_BLOCKING_SAMPLE_CACHE_WINDOW = 2048;
    private static final int HIREABLE_CAMP_REGION_SPACING_CHUNKS = 200;
    private static final int HIREABLE_CAMP_REGION_BORDER_CHUNKS = 25;
    private static final int HIREABLE_CAMP_MAX_FLOOR_DELTA = 8;
    private static final int HIREABLE_CAMP_MAX_TERRAIN_DELTA = 10;
    private static final long CUSTOM_STRUCTURE_LOG_WINDOW_MS = 10000L;
    private static final Object CUSTOM_STRUCTURE_LOG_LOCK = new Object();
    private static final Map<String, CustomStructureLogCounter> CUSTOM_STRUCTURE_LOG_COUNTS = new HashMap<String, CustomStructureLogCounter>();
    private static long customStructureLogWindowStartMs = System.currentTimeMillis();
    private static volatile boolean customNaturalTemplatesValidated = false;
    private static final List<String> CUSTOM_OAK_TREE_STRUCTURES = List.of("new/oak_tree/oak_tree_1", "new/oak_tree/oak_tree_2", "new/oak_tree/oak_tree_3", "new/oak_tree/oak_tree_4", "new/oak_tree/oak_tree_5", "new/oak_tree/oak_tree_6", "new/oak_tree/oak_tree_7", "new/oak_tree/oak_tree_8", "new/oak_tree/oak_tree_9", "new/oak_tree/oak_tree_10", "new/oak_tree/oak_tree_11", "new/oak_tree/oak_tree_12", "new/oak_tree/oak_tree_13", "new/oak_tree/oak_tree_14", "new/oak_tree/oak_tree_15", "new/oak_tree/oak_tree_16", "new/oak_tree/oak_tree_17", "new/oak_tree/oak_tree_18", "new/oak_tree/oak_tree_19", "new/oak_tree/oak_tree_20", "new/oak_tree/oak_tree_21", "new/oak_tree/oak_tree_22", "new/oak_tree/oak_tree_23", "new/oak_tree/oak_tree_24", "new/oak_tree/oak_tree_25", "new/oak_tree/oak_tree_26", "new/oak_tree/oak_tree_27", "new/oak_tree/oak_tree_28", "new/oak_tree/oak_tree_29", "new/oak_tree/oak_tree_30", "new/oak_tree/oak_tree_31", "new/oak_tree/oak_tree_32", "new/oak_tree/oak_tree_33", "new/oak_tree/oak_tree_34", "new/oak_tree/oak_tree_35", "new/oak_tree/oak_tree_36", "new/oak_tree/oak_tree_37", "new/oak_tree/oak_tree_38", "new/oak_tree/oak_tree_39", "new/oak_tree/oak_tree_40", "new/oak_tree/oak_tree_41", "new/oak_tree/oak_tree_42", "new/oak_tree/oak_tree_43", "new/oak_tree/oak_tree_44", "new/oak_tree/oak_tree_45", "new/oak_tree/oak_tree_46", "new/oak_tree/oak_tree_47", "new/oak_tree/oak_tree_48", "new/oak_tree/oak_tree_49", "new/oak_tree/oak_tree_50");
    private static final List<String> CUSTOM_SNOWY_ROCK_STRUCTURES = List.of("new/snowy_rock_1", "new/snowy_rock_2", "new/snowy_rock_3");
    private static final List<String> CUSTOM_SNOWY_SPRUCE_STRUCTURES = List.of("new/snowy_spruce_tree_1", "new/snowy_spruce_tree_2");
    private static final List<String> CUSTOM_NATURE_PACK_STRUCTURES = IntStream.rangeClosed(1, 44).mapToObj(index -> "new/nature_pack/nature_pack_" + index).toList();
    private static final List<String> CUSTOM_WILD_STRUCTURES = List.of("wild/wild_camp", "wild/wild_tower_1", "wild/wild_tower_2");

    public MapChunkGenerator(class_1966 biomeSource) {
        super(biomeSource);
    }

    public static Set<String> finalPassiveSpawnSubstrates(class_5321<class_1959> biome) {
        BiomeStyle style = MapChunkGenerator.styleForBiomePath(biome.method_29177().method_12832());
        if (style == BiomeStyle.SEA) {
            return Set.of();
        }
        if (MapChunkGenerator.isAlwaysWinterStyle(style)) {
            return Set.of("minecraft:snow");
        }
        if (MapChunkGenerator.isNorthStyle(style)) {
            return Set.of("minecraft:snow", "minecraft:grass_block", "minecraft:dirt_path", "minecraft:coarse_dirt");
        }
        Set<String> base = MapChunkGenerator.basePassiveSpawnSubstrates(style);
        if (!base.contains("minecraft:grass_block") || style == BiomeStyle.WALL) {
            return base;
        }
        HashSet<String> withRoads = new HashSet<String>(base);
        withRoads.add("minecraft:dirt_path");
        withRoads.add("minecraft:coarse_dirt");
        return Set.copyOf(withRoads);
    }

    public static Map<String, NorthernSurfaceAudit> auditNorthernPassiveSpawnSurfaces() {
        LinkedHashMap<String, NorthernSurfaceAudit> audits = new LinkedHashMap<String, NorthernSurfaceAudit>();
        BiomeStyle[] styles = new BiomeStyle[]{BiomeStyle.THE_NORTH, BiomeStyle.THE_NORTH_FOREST, BiomeStyle.THE_NORTH_HILLS, BiomeStyle.THE_NORTH_WOLFSWOOD};
        for (int styleIndex = 0; styleIndex < styles.length; ++styleIndex) {
            BiomeStyle style = styles[styleIndex];
            int originX = -1536 + styleIndex * 887;
            int originZ = 2048 - styleIndex * 613;
            NorthernSurfaceAudit first = MapChunkGenerator.sampleNorthernSurfaceAudit(style, originX, originZ);
            NorthernSurfaceAudit second = MapChunkGenerator.sampleNorthernSurfaceAudit(style, originX, originZ);
            if (first.fingerprint() != second.fingerprint()) {
                throw new IllegalStateException("North passive-spawn surface sampling is not deterministic for " + String.valueOf((Object)style));
            }
            if (first.exposedGrassCount() < first.sampleCount() / 50 || first.occupiedRegionCount() < 8) {
                throw new IllegalStateException("North passive-spawn grass clearings are not spatially distributed for " + String.valueOf((Object)style) + ": " + String.valueOf(first));
            }
            if (first.roadGrassCount() != 0) {
                throw new IllegalStateException("Canonical roads retain grass passive-spawn substrate for " + String.valueOf((Object)style) + ": " + String.valueOf(first));
            }
            audits.put(style.name().toLowerCase(Locale.ROOT), first);
        }
        return Map.copyOf(audits);
    }

    protected MapCodec<? extends class_2794> method_28506() {
        return CODEC;
    }

    public void method_12108(class_3233 region, long seed, class_7138 noiseConfig, class_4543 biomeAccess, class_5138 structureAccessor, class_2791 chunk, class_2893.class_2894 carverStep) {
    }

    public void method_12110(class_3233 region, class_5138 structureAccessor, class_7138 noiseConfig, class_2791 chunk) {
        boolean placedCastle = this.applyChunkNativeCastles(region, chunk);
        if (placedCastle) {
            return;
        }
        boolean placedWildStructure = this.tryPlaceWildStructure(region, chunk);
        if (!placedWildStructure) {
            this.addCustomFoliage(region, chunk);
        }
    }

    private boolean applyChunkNativeCastles(class_3233 region, class_2791 chunk) {
        class_1923 chunkPos = region.method_33561();
        List<CastleWorldgenPlan> castlePlans = CastleChunkIndex.plansIntersectingChunk(region.method_8410(), chunkPos);
        if (castlePlans.isEmpty()) {
            return false;
        }
        boolean applied = false;
        for (CastleWorldgenPlan castlePlan : castlePlans) {
            if (!CastlePlanCache.isCurrentLayoutPinned(region.method_8410(), castlePlan)) continue;
            DynamicCastleTerrainApplier.applyChunk(region, chunkPos, castlePlan);
            if (castlePlan.corePlan() != null) {
                DynamicCastleCoreApplier.applyChunk(region, chunkPos, castlePlan.corePlan());
            }
            CastleWorldgenStructureApplier.applyChunk(region, chunkPos, castlePlan);
            DynamicCastleWallApplier.applyChunk(region, chunkPos, castlePlan);
            applied = true;
        }
        return applied;
    }

    public void method_12107(class_3233 region) {
        class_1923 chunkPos = region.method_33561();
        class_6880 biome = region.method_23753(chunkPos.method_8323().method_33096(region.method_31600() - 1));
        class_2919 random = new class_2919((class_5819)new class_5820(class_6673.method_39001()));
        random.method_12661(region.method_8412(), chunkPos.method_8326(), chunkPos.method_8328());
        class_1948.method_8661((class_5425)region, (class_6880)biome, (class_1923)chunkPos, (class_5819)random);
    }

    public CompletableFuture<class_2791> method_12088(class_6748 blender, class_7138 noiseConfig, class_5138 structureAccessor, class_2791 chunk) {
        int worldZ;
        int localZ;
        int worldX;
        int localX;
        class_1923 chunkPos = chunk.method_12004();
        class_2338.class_2339 mutable = new class_2338.class_2339();
        GenerationCache cache = new GenerationCache();
        ColumnProfile[][] columns = new ColumnProfile[16][16];
        MapImage roadMap = RoadMapHolder.IMAGE;
        for (localX = 0; localX < 16; ++localX) {
            worldX = chunkPos.method_8326() + localX;
            for (localZ = 0; localZ < 16; ++localZ) {
                int y;
                worldZ = chunkPos.method_8328() + localZ;
                BiomeStyle style = this.getStyleAt(worldX, worldZ, cache);
                ColumnProfile column = this.buildColumnProfile(worldX, worldZ, style, cache);
                CaveColumnContext caveContext = this.createCaveColumnContext(worldX, worldZ, column);
                columns[localX][localZ] = column;
                for (y = -64; y <= column.terrainTopY; ++y) {
                    class_2680 ore;
                    class_2680 state = y == -64 ? BEDROCK : (y < column.deepTopY ? column.deepBlock : (y < column.surfaceY ? column.subsurfaceBlock : column.surfaceBlock));
                    state = (state = this.applyStoneDepthTransition(state, worldX, y, worldZ)) != BEDROCK && this.shouldCarveCave(worldX, y, worldZ, caveContext) ? this.caveFluid(worldX, y, worldZ, column) : (this.isOreHost(state) && y < column.surfaceY - 3 ? ((ore = this.oreFor(column.style, worldX, y, worldZ, state)) != null ? ore : this.applyStoneClusterVariation(column.style, worldX, y, worldZ, state)) : this.applyStoneClusterVariation(column.style, worldX, y, worldZ, state));
                    chunk.method_12010((class_2338)mutable.method_10103(worldX, y, worldZ), state, false);
                }
                for (y = column.surfaceY + 1; y <= column.fluidTopY; ++y) {
                    chunk.method_12010((class_2338)mutable.method_10103(worldX, y, worldZ), column.fluidBlock, false);
                }
            }
        }
        for (localX = 2; localX < 14; ++localX) {
            worldX = chunkPos.method_8326() + localX;
            for (localZ = 2; localZ < 14; ++localZ) {
                worldZ = chunkPos.method_8328() + localZ;
                this.decorateColumn(chunk, worldX, worldZ, localX, localZ, columns[localX][localZ]);
            }
        }
        for (localX = 0; localX < 16; ++localX) {
            worldX = chunkPos.method_8326() + localX;
            for (localZ = 0; localZ < 16; ++localZ) {
                worldZ = chunkPos.method_8328() + localZ;
                this.applySurfaceClimate(chunk, worldX, worldZ, columns[localX][localZ]);
            }
        }
        for (localX = 0; localX < 16; ++localX) {
            worldX = chunkPos.method_8326() + localX;
            for (localZ = 0; localZ < 16; ++localZ) {
                worldZ = chunkPos.method_8328() + localZ;
                this.applyRoadOverlay(chunk, worldX, worldZ, columns[localX][localZ], roadMap);
            }
        }
        class_2902.method_16684((class_2791)chunk, Set.of(class_2902.class_2903.field_13195, class_2902.class_2903.field_13194, class_2902.class_2903.field_13200, class_2902.class_2903.field_13202, class_2902.class_2903.field_13197, class_2902.class_2903.field_13203));
        return CompletableFuture.completedFuture(chunk);
    }

    public int method_12104() {
        return 384;
    }

    public int method_16398() {
        return 63;
    }

    public int method_33730() {
        return -64;
    }

    public int method_12100(class_5539 world) {
        return 65;
    }

    public int method_16397(int x, int z, class_2902.class_2903 heightmap, class_5539 world, class_7138 noiseConfig) {
        GenerationCache cache = new GenerationCache();
        ColumnProfile column = this.buildColumnProfile(x, z, this.getStyleAt(x, z, cache), cache);
        return switch (heightmap) {
            default -> throw new MatchException(null, null);
            case class_2902.class_2903.field_13200, class_2902.class_2903.field_13195 -> column.surfaceY + 1;
            case class_2902.class_2903.field_13202, class_2902.class_2903.field_13194, class_2902.class_2903.field_13197, class_2902.class_2903.field_13203 -> column.motionBlockingY + 1;
        };
    }

    public int estimateMotionBlockingSurfaceY(int x, int z) {
        return this.estimateMotionBlockingSurfaceY(x, z, new GenerationCache());
    }

    public MotionBlockingSurfaceSampler motionBlockingSurfaceSampler() {
        return new CachedMotionBlockingSurfaceSampler(2048);
    }

    public MotionBlockingSurfaceSamplerAudit auditMotionBlockingSurfaceSampler() {
        int auditWindow = 3;
        int[][] coordinates = new int[][]{{0, 0}, {32, 0}, {32, 32}, {-64, 96}, {256, -384}, {-1024, 768}};
        CachedMotionBlockingSurfaceSampler sampler = new CachedMotionBlockingSurfaceSampler(auditWindow);
        for (int[] coordinate : coordinates) {
            int x = coordinate[0];
            int z = coordinate[1];
            int expected = this.estimateMotionBlockingSurfaceY(x, z);
            int actual = sampler.sample(x, z);
            if (actual != expected) {
                throw new IllegalStateException("Bulk motion-blocking surface sampling changed at " + x + "," + z + ": expected " + expected + ", got " + actual);
            }
            if (sampler.sample(x, z) == expected) continue;
            throw new IllegalStateException("Repeated bulk motion-blocking surface sample changed at " + x + "," + z);
        }
        if (sampler.cacheHits != coordinates.length || sampler.cacheResets == 0 || sampler.maxCachedSamples > auditWindow) {
            throw new IllegalStateException("Bulk motion-blocking sampler did not reuse and bound request-local state: " + String.valueOf(sampler.audit(coordinates.length)));
        }
        return sampler.audit(coordinates.length);
    }

    private int estimateMotionBlockingSurfaceY(int x, int z, GenerationCache cache) {
        ColumnProfile column = this.buildColumnProfile(x, z, this.getStyleAt(x, z, cache), cache);
        return column.motionBlockingY;
    }

    public NaturalSurfaceSample sampleNaturalSurface(int x, int z) {
        GenerationCache cache = new GenerationCache();
        return this.sampleNaturalSurface(x, z, cache, RoadMapHolder.IMAGE);
    }

    public NaturalSurfaceSampler naturalSurfaceSampler() {
        return new NaturalSurfaceSampler(){
            private static final int CACHE_SAMPLE_WINDOW = 2048;
            private final MapImage roadMap = RoadMapHolder.IMAGE;
            private final Long2ObjectOpenHashMap<NaturalSurfaceSample> sampledSurfaces = new Long2ObjectOpenHashMap();
            private GenerationCache cache = new GenerationCache();
            private int samples;

            @Override
            public NaturalSurfaceSample sample(int x, int z) {
                long key = MapChunkGenerator.columnKey(x, z);
                NaturalSurfaceSample cached = (NaturalSurfaceSample)this.sampledSurfaces.get(key);
                if (cached != null) {
                    return cached;
                }
                if (this.samples >= 2048) {
                    this.cache = new GenerationCache();
                    this.sampledSurfaces.clear();
                    this.samples = 0;
                }
                NaturalSurfaceSample resolved = MapChunkGenerator.this.sampleNaturalSurface(x, z, this.cache, this.roadMap);
                this.sampledSurfaces.put(key, (Object)resolved);
                ++this.samples;
                return resolved;
            }
        };
    }

    private NaturalSurfaceSample sampleNaturalSurface(int x, int z, GenerationCache cache, MapImage roadMap) {
        ColumnProfile column = this.buildColumnProfile(x, z, this.getStyleAt(x, z, cache), cache);
        boolean surfaceFluidOccupied = column.fluidBlock != AIR && column.fluidTopY > column.surfaceY;
        return new NaturalSurfaceSample(column.surfaceY, this.finalNaturalGroundState(column, x, z, roadMap), surfaceFluidOccupied, surfaceFluidOccupied ? column.fluidBlock : AIR, surfaceFluidOccupied ? column.fluidTopY : column.surfaceY);
    }

    private class_2680 finalNaturalGroundState(ColumnProfile column, int worldX, int worldZ, MapImage roadMap) {
        if (column.style != BiomeStyle.SEA && column.style != BiomeStyle.WALL && column.fluidBlock == AIR && column.surfaceBlock == GRASS && RoadMapHelper.isRoadAtWorldBlock(roadMap, worldX, worldZ, 13154156, 0.24)) {
            return MapChunkGenerator.roadSurface(worldX, worldZ);
        }
        return column.surfaceBlock;
    }

    public class_4966 method_26261(int x, int z, class_5539 world, class_7138 noiseConfig) {
        int y;
        GenerationCache cache = new GenerationCache();
        ColumnProfile column = this.buildColumnProfile(x, z, this.getStyleAt(x, z, cache), cache);
        CaveColumnContext caveContext = this.createCaveColumnContext(x, z, column);
        class_2680[] states = new class_2680[384];
        for (int i = 0; i < states.length; ++i) {
            states[i] = AIR;
        }
        for (y = -64; y <= column.terrainTopY; ++y) {
            class_2680 ore;
            class_2680 state = y == -64 ? BEDROCK : (y < column.deepTopY ? column.deepBlock : (y < column.surfaceY ? column.subsurfaceBlock : column.surfaceBlock));
            state = (state = this.applyStoneDepthTransition(state, x, y, z)) != BEDROCK && this.shouldCarveCave(x, y, z, caveContext) ? this.caveFluid(x, y, z, column) : (this.isOreHost(state) && y < column.surfaceY - 3 ? ((ore = this.oreFor(column.style, x, y, z, state)) != null ? ore : this.applyStoneClusterVariation(column.style, x, y, z, state)) : this.applyStoneClusterVariation(column.style, x, y, z, state));
            states[y - -64] = state;
        }
        for (y = column.surfaceY + 1; y <= column.fluidTopY; ++y) {
            states[y - -64] = column.fluidBlock;
        }
        return new class_4966(-64, states);
    }

    public void method_40450(List<String> text, class_7138 noiseConfig, class_2338 pos) {
        text.add("Generator: agob:map_terrain");
    }

    private ColumnProfile buildColumnProfile(int worldX, int worldZ, BiomeStyle style) {
        return this.buildColumnProfile(worldX, worldZ, style, null);
    }

    private ColumnProfile buildColumnProfile(int worldX, int worldZ, BiomeStyle style, GenerationCache cache) {
        double broad = MapChunkGenerator.sample(BROAD_NOISE, worldX, worldZ, 720.0);
        double ridges = Math.pow(Math.abs(MapChunkGenerator.sample(RIDGE_NOISE, worldX, worldZ, 180.0)), 1.35);
        double micro = MapChunkGenerator.sample(MICRO_NOISE, worldX, worldZ, 28.0);
        double coastalness = this.coastalness(worldX, worldZ, cache);
        boolean beach = style != BiomeStyle.SEA && style != BiomeStyle.WALL && (coastalness > 0.22 || this.nearestSeaDistance(worldX, worldZ, cache) < 72.0);
        int smoothedTop = this.applyCoastalShelf(worldX, worldZ, style, this.sampleSmoothedTop(worldX, worldZ, cache), coastalness, ridges, micro, cache);
        if (style != BiomeStyle.SEA && style != BiomeStyle.WALL) {
            smoothedTop = Math.max(63, this.limitSurfaceStepDelta(worldX, worldZ, style, smoothedTop, cache));
        }
        if (MapChunkGenerator.isOasisStyle(style)) {
            int top = smoothedTop;
            double poolNoise = MapChunkGenerator.normalizedSample(DETAIL_NOISE, worldX + 211, worldZ - 173, 38.0);
            if (poolNoise > 0.57) {
                int waterDepth = 1 + (int)((poolNoise - 0.57) * 5.0);
                int basinFloor = MapChunkGenerator.clampY(top - waterDepth);
                class_2680 basinSurface = style == BiomeStyle.DORNE_OASIS ? GRASS : SAND;
                class_2680 basinSubsurface = style == BiomeStyle.DORNE_OASIS ? DIRT : SANDSTONEHolder.SANDSTONE;
                return new ColumnProfile(basinFloor, basinFloor - 3, basinFloor, basinSurface, basinSubsurface, STONE, WATER, top, basinFloor, style);
            }
            if (style == BiomeStyle.DORNE_OASIS) {
                return this.land(top, GRASS, DIRT, STONE, style);
            }
            return this.land(top, micro > 0.72 ? GRASS : SAND, micro > 0.72 ? DIRT : SANDSTONEHolder.SANDSTONE, STONE, style);
        }
        return switch (style.ordinal()) {
            case 0 -> {
                int floor = this.seaFloorHeight(worldX, worldZ, cache);
                class_2680 seabedSurface = this.seaFloorSurface(worldX, worldZ, cache);
                class_2680 seabedSubsurface = seabedSurface == CLAY ? CLAY : (seabedSurface == GRAVEL ? GRAVEL : (seabedSurface == STONE ? STONE : SAND));
                yield new ColumnProfile(floor, floor - 4, floor, seabedSurface, seabedSubsurface, STONE, WATER, 63, 63, style);
            }
            case 1 -> {
                double wallDepth = this.wallInteriorness(worldX, worldZ, cache);
                double wallRise = MapChunkGenerator.smoothstep(class_3532.method_15350((double)((wallDepth - 0.12) / 0.76), (double)0.0, (double)1.0));
                double foothillRise = MapChunkGenerator.smoothstep(class_3532.method_15350((double)((wallDepth - 0.04) / 0.18), (double)0.0, (double)1.0));
                double crownNoise = MapChunkGenerator.sample(DETAIL_NOISE, worldX + 97, worldZ - 53, 110.0) * 5.0 + MapChunkGenerator.sample(MICRO_NOISE, worldX - 41, worldZ + 73, 34.0) * 3.0;
                int top = MapChunkGenerator.clampY((int)Math.round(96.0 + foothillRise * 12.0 + wallRise * 112.0 + wallRise * ridges * 24.0 + Math.max(0.0, wallDepth - 0.62) * 26.0 + crownNoise));
                class_2680 surface = wallDepth > 0.78 || MapChunkGenerator.normalizedSample(MICRO_NOISE, worldX + 211, worldZ - 101, 18.0) > 0.63 ? SNOW : PACKED_ICE;
                class_2680 subsurface = wallDepth > 0.52 ? PACKED_ICE : ICE;
                class_2680 deep = wallDepth > 0.66 ? BLUE_ICE : PACKED_ICE;
                yield new ColumnProfile(top, top - 28, top, surface, subsurface, deep, AIR, top, top, style);
            }
            case 2 -> {
                int top = smoothedTop;
                class_2680 surface = beach ? SAND : (ridges > 0.68 ? PODZOL : (micro > 0.58 ? COARSE_DIRT : GRASS));
                yield this.land(top, surface, beach ? SAND : DIRT, STONE, style);
            }
            case 3, 4, 5 -> {
                int top = smoothedTop;
                class_2680 surface = style == BiomeStyle.ALWAYS_WINTER_HILLS || ridges > 0.62 ? PACKED_ICE : SNOW;
                yield new ColumnProfile(top, top - 5, top, surface, PACKED_ICE, STONE, AIR, top, top, style);
            }
            case 6, 7, 8, 9 -> {
                int top = smoothedTop;
                class_2680 surface = MapChunkGenerator.northernBaseSurface(style, beach, ridges, micro);
                yield this.land(top, surface, beach ? SAND : DIRT, STONE, style);
            }
            case 10, 11, 12, 13, 14, 15 -> {
                class_2680 surface;
                int top = smoothedTop;
                switch (style.ordinal()) {
                    case 12: {
                        class_2680 v1;
                        if (beach) {
                            v1 = GRAVEL;
                            break;
                        }
                        if (micro > 0.84) {
                            v1 = LIMESTONE;
                            break;
                        }
                        v1 = GRASS;
                        break;
                    }
                    case 13: {
                        class_2680 v1;
                        if (ridges > 0.7 && micro > 0.72) {
                            v1 = GRAVEL;
                            break;
                        }
                        v1 = GRASS;
                        break;
                    }
                    case 14: {
                        class_2680 v1 = GRASS;
                        break;
                    }
                    case 15: {
                        class_2680 v1;
                        if (micro > 0.7) {
                            v1 = COARSE_DIRT;
                            break;
                        }
                        v1 = GRASS;
                        break;
                    }
                    default: {
                        class_2680 v1 = ridges > 0.82 && micro > 0.7 ? LIMESTONE : (surface = beach ? GRAVEL : GRASS);
                    }
                }
                class_2680 subsurface = surface == GRAVEL || beach ? GRAVEL : (surface == LIMESTONE ? LIMESTONE : DIRT);
                yield this.land(top, surface, subsurface, STONE, style);
            }
            case 16, 17, 18, 19 -> {
                int top = style == BiomeStyle.THE_RIVERLANDS_MARSH ? smoothedTop - 1 : smoothedTop;
                class_2680 v2 = switch (style.ordinal()) {
                    case 18 -> {
                        if (beach) {
                            yield SAND;
                        }
                        if (ridges > 0.78 && micro > 0.68) {
                            yield SILTSTONE;
                        }
                        yield GRASS;
                    }
                    case 19 -> {
                        if (micro > 0.62) {
                            yield CLAY;
                        }
                        yield GRASS;
                    }
                    default -> beach ? SAND : GRASS;
                };
                class_2680 surface = v2;
                class_2680 v3 = switch (style.ordinal()) {
                    case 19 -> {
                        if (surface == CLAY) {
                            yield CLAY;
                        }
                        yield DIRT;
                    }
                    default -> beach ? SAND : (surface == SILTSTONE ? SILTSTONE : DIRT);
                };
                class_2680 subsurface = v3;
                yield this.land(top, surface, subsurface, STONE, style);
            }
            case 20, 21, 22, 23 -> {
                int top = smoothedTop;
                class_2680 v4 = switch (style.ordinal()) {
                    case 22 -> {
                        if (ridges > 0.74 && micro > 0.66) {
                            yield MARBLE;
                        }
                        yield GRASS;
                    }
                    case 23 -> {
                        if (micro > 0.58) {
                            yield COARSE_DIRT;
                        }
                        yield GRASS;
                    }
                    case 21 -> {
                        if (ridges > 0.78 && micro > 0.72) {
                            yield MOSSY_MARBLE;
                        }
                        yield GRASS;
                    }
                    default -> ridges > 0.82 && micro > 0.72 ? MARBLE : GRASS;
                };
                class_2680 surface = v4;
                class_2680 subsurface = surface == MARBLE || surface == MOSSY_MARBLE ? surface : DIRT;
                yield this.land(top, surface, subsurface, STONE, style);
            }
            case 24, 25, 26, 27 -> {
                class_2680 surface;
                int top = smoothedTop;
                switch (style.ordinal()) {
                    case 26: {
                        class_2680 v5;
                        if (ridges > 0.76 && micro > 0.68) {
                            v5 = LIMESTONE;
                            break;
                        }
                        v5 = GRASS;
                        break;
                    }
                    case 27: {
                        class_2680 v5;
                        if (micro > 0.68) {
                            v5 = GRAVEL;
                            break;
                        }
                        if (micro > 0.58) {
                            v5 = SILTSTONE;
                            break;
                        }
                        v5 = GRASS;
                        break;
                    }
                    case 25: {
                        class_2680 v5;
                        if (ridges > 0.82 && micro > 0.74) {
                            v5 = SILTSTONE;
                            break;
                        }
                        v5 = GRASS;
                        break;
                    }
                    default: {
                        class_2680 v5 = ridges > 0.82 && micro > 0.7 ? SILTSTONE : (surface = beach ? GRAVEL : GRASS);
                    }
                }
                class_2680 subsurface = style == BiomeStyle.IRON_ISLANDS_MOOR || beach ? GRAVEL : (surface == SILTSTONE || surface == LIMESTONE ? surface : DIRT);
                yield this.land(top, surface, subsurface, STONE, style);
            }
            case 28, 29, 30, 31 -> {
                class_2680 surface;
                int top = smoothedTop;
                switch (style.ordinal()) {
                    case 30: {
                        class_2680 v6;
                        if (beach) {
                            v6 = SAND;
                            break;
                        }
                        if (ridges > 0.8 && micro > 0.72) {
                            v6 = LIMESTONE;
                            break;
                        }
                        v6 = GRASS;
                        break;
                    }
                    case 31: {
                        class_2680 v6;
                        if (micro > 0.72) {
                            v6 = COARSE_DIRT;
                            break;
                        }
                        if (beach) {
                            v6 = SAND;
                            break;
                        }
                        v6 = GRASS;
                        break;
                    }
                    default: {
                        class_2680 v6 = surface = beach ? SAND : GRASS;
                    }
                }
                yield this.land(top, surface, beach ? SAND : (surface == LIMESTONE ? LIMESTONE : DIRT), STONE, style);
            }
            case 32, 33, 34, 35 -> {
                int top = smoothedTop;
                class_2680 v7 = switch (style.ordinal()) {
                    case 34 -> {
                        if (ridges > 0.78 && micro > 0.7) {
                            yield SILTSTONE;
                        }
                        yield GRASS;
                    }
                    case 35 -> {
                        if (micro > 0.64) {
                            yield COARSE_DIRT;
                        }
                        yield GRASS;
                    }
                    default -> ridges > 0.84 && micro > 0.74 ? SILTSTONE : GRASS;
                };
                class_2680 surface = v7;
                yield this.land(top, surface, surface == SILTSTONE ? SILTSTONE : DIRT, STONE, style);
            }
            case 36, 37, 38, 39 -> {
                class_2680 surface;
                int top = smoothedTop;
                switch (style.ordinal()) {
                    case 38: {
                        class_2680 v8;
                        if (beach) {
                            v8 = SAND;
                            break;
                        }
                        if (ridges > 0.8 && micro > 0.7) {
                            v8 = LIMESTONE;
                            break;
                        }
                        v8 = GRASS;
                        break;
                    }
                    case 39: {
                        class_2680 v8;
                        if (micro > 0.66) {
                            v8 = COARSE_DIRT;
                            break;
                        }
                        if (beach) {
                            v8 = SAND;
                            break;
                        }
                        v8 = GRASS;
                        break;
                    }
                    default: {
                        class_2680 v8 = surface = beach ? SAND : GRASS;
                    }
                }
                yield this.land(top, surface, beach ? SAND : (surface == LIMESTONE ? LIMESTONE : DIRT), STONE, style);
            }
            case 40, 41, 42 -> {
                int top = smoothedTop;
                double dragonstonePatch = MapChunkGenerator.normalizedSample(MICRO_NOISE, worldX - 71, worldZ + 53, 14.0);
                class_2680 surface = dragonstonePatch > 0.88 ? CHARRED_STONE : (dragonstonePatch > 0.78 ? ASH_STONE : (dragonstonePatch > 0.62 ? COARSE_DIRT : GRASS));
                class_2680 subsurface = surface == CHARRED_STONE || surface == ASH_STONE ? ASH_STONE : DIRT;
                yield this.land(top, surface, subsurface, STONE, style);
            }
            case 43, 44, 46 -> {
                int top = smoothedTop;
                class_2680 v9 = switch (style.ordinal()) {
                    case 44 -> {
                        if (ridges > 0.82 && micro > 0.76) {
                            yield LIMESTONE;
                        }
                        yield SAND;
                    }
                    case 46 -> {
                        if (micro > 0.48) {
                            yield COARSE_DIRT;
                        }
                        yield RED_SAND;
                    }
                    default -> ridges > 0.86 && micro > 0.78 ? LIMESTONE : (micro > 0.25 ? RED_SAND : SAND);
                };
                class_2680 surface = v9;
                class_2680 v10 = switch (style.ordinal()) {
                    case 44 -> {
                        if (surface == LIMESTONE) {
                            yield LIMESTONE;
                        }
                        yield SANDSTONEHolder.SANDSTONE;
                    }
                    case 46 -> {
                        if (surface == COARSE_DIRT) {
                            yield DIRT;
                        }
                        yield RED_SAND;
                    }
                    default -> surface == LIMESTONE ? LIMESTONE : (surface == RED_SAND ? RED_SAND : SANDSTONEHolder.SANDSTONE);
                };
                class_2680 subsurface = v10;
                yield this.land(top, surface, subsurface, STONE, style);
            }
            case 47, 48, 49 -> {
                class_2680 surface;
                int top = smoothedTop;
                class_2680 v11 = style == BiomeStyle.BRAAVOS_HILLS && ridges > 0.72 && micro > 0.64 || coastalness > 0.5 && micro > 0.84 ? BLUESTONE : (surface = coastalness > 0.5 ? GRAVEL : GRASS);
                yield this.land(top, surface, surface == BLUESTONE ? BLUESTONE : (coastalness > 0.5 ? GRAVEL : DIRT), STONE, style);
            }
            case 50, 51 -> {
                int top = smoothedTop;
                class_2680 surface = style == BiomeStyle.SLAVERS_BAY_HILLS && ridges > 0.82 && micro > 0.76 ? SILTSTONE : (micro > 0.2 ? RED_SAND : SAND);
                yield this.land(top, surface, surface == SILTSTONE ? SILTSTONE : surface, STONE, style);
            }
            case 53, 54 -> {
                int top = smoothedTop;
                class_2680 surface = style == BiomeStyle.STEP_STONES_HILLS && ridges > 0.82 && micro > 0.74 ? VOLCANIC_STONE : SAND;
                class_2680 subsurface = surface == VOLCANIC_STONE ? VOLCANIC_STONE : SAND;
                yield this.land(top, surface, subsurface, STONE, style);
            }
            case 56, 57 -> {
                int top = smoothedTop;
                class_2680 surface = style == BiomeStyle.DOTHRAKI_SEA_STEPPE && ridges > 0.78 && micro > 0.82 ? SILTSTONE : (micro > 0.58 ? COARSE_DIRT : GRASS);
                class_2680 subsurface = surface == COARSE_DIRT ? COARSE_DIRT : DIRT;
                yield this.land(top, surface, surface == SILTSTONE ? SILTSTONE : subsurface, STONE, style);
            }
            case 58, 59, 60 -> {
                class_2680 surface;
                int top = smoothedTop;
                class_2680 v12 = style == BiomeStyle.VOLANTIS_HILLS && ridges > 0.82 && micro > 0.72 ? SILTSTONE : (surface = coastalness > 0.55 ? SAND : GRASS);
                class_2680 subsurface = surface == SILTSTONE ? SILTSTONE : (coastalness > 0.55 ? SAND : DIRT);
                yield this.land(top, surface, subsurface, CLAY, style);
            }
            default -> this.land(smoothedTop, GRASS, DIRT, STONE, style);
        };
    }

    private ColumnProfile land(int top, class_2680 surface, class_2680 subsurface, class_2680 deep, BiomeStyle style) {
        if (top < 63) {
            return new ColumnProfile(top, top - 4, top, surface, subsurface, deep, WATER, 63, 63, style);
        }
        return new ColumnProfile(top, top - 4, top, surface, subsurface, deep, AIR, top, top, style);
    }

    private void decorateColumn(class_2791 chunk, int worldX, int worldZ, int localX, int localZ, ColumnProfile column) {
        int y = column.surfaceY + 1;
        if (y <= -64 || y >= 311) {
            return;
        }
        if (column.style == BiomeStyle.SEA) {
            this.placeSeaDecoration(chunk, worldX, worldZ, localX, localZ, column);
            return;
        }
        if (column.style == BiomeStyle.WALL) {
            return;
        }
        if (!chunk.method_8320(new class_2338(worldX, y, worldZ)).method_26215()) {
            return;
        }
        if (this.tryPlaceTree(chunk, worldX, worldZ, localX, localZ, column)) {
            return;
        }
        if (this.tryPlaceBoulder(chunk, worldX, worldZ, localX, localZ, column)) {
            return;
        }
        this.tryPlaceFlora(chunk, worldX, worldZ, localX, localZ, column);
    }

    private void applySurfaceClimate(class_2791 chunk, int worldX, int worldZ, ColumnProfile column) {
        int y = column.surfaceY + 1;
        if (y <= -64 || y >= 315) {
            return;
        }
        if (MapChunkGenerator.isNorthernGrassClearing(column.style, column.surfaceBlock, worldX, worldZ)) {
            return;
        }
        if (column.style == BiomeStyle.ALWAYS_WINTER || column.style == BiomeStyle.ALWAYS_WINTER_FOREST || column.style == BiomeStyle.ALWAYS_WINTER_HILLS || column.style == BiomeStyle.THE_NORTH || column.style == BiomeStyle.THE_NORTH_FOREST || column.style == BiomeStyle.THE_NORTH_HILLS || column.style == BiomeStyle.THE_NORTH_WOLFSWOOD) {
            this.placeNorthernSnow(chunk, worldX, worldZ, y, column);
        }
    }

    private void applyRoadOverlay(class_2791 chunk, int worldX, int worldZ, ColumnProfile column, MapImage roadMap) {
        class_2338 belowPos;
        if (column.style == BiomeStyle.SEA || column.style == BiomeStyle.WALL || column.fluidBlock != AIR) {
            return;
        }
        if (column.surfaceBlock != GRASS) {
            return;
        }
        if (!RoadMapHelper.isRoadAtWorldBlock(roadMap, worldX, worldZ, 13154156, 0.24)) {
            return;
        }
        int topY = column.surfaceY;
        for (int clearY = topY + 1; clearY <= Math.min(topY + 3, 319); ++clearY) {
            class_2338 clearPos = new class_2338(worldX, clearY, worldZ);
            if (chunk.method_8320(clearPos).method_26215()) continue;
            chunk.method_12010(clearPos, AIR, false);
        }
        class_2680 roadSurface = MapChunkGenerator.roadSurface(worldX, worldZ);
        chunk.method_12010(new class_2338(worldX, topY, worldZ), roadSurface, false);
        if (topY - 1 > -64 && chunk.method_8320(belowPos = new class_2338(worldX, topY - 1, worldZ)) != BEDROCK) {
            chunk.method_12010(belowPos, COARSE_DIRT, false);
        }
    }

    private static class_2680 northernBaseSurface(BiomeStyle style, boolean beach, double ridges, double micro) {
        return switch (style.ordinal()) {
            case 8 -> {
                if (ridges > 0.74) {
                    yield PODZOL;
                }
                if (micro > 0.72) {
                    yield COARSE_DIRT;
                }
                yield GRASS;
            }
            case 9 -> {
                if (micro > 0.78) {
                    yield PODZOL;
                }
                if (micro > 0.66) {
                    yield COARSE_DIRT;
                }
                yield GRASS;
            }
            case 6, 7 -> {
                if (beach) {
                    yield SAND;
                }
                if (ridges > 0.78) {
                    yield PODZOL;
                }
                if (micro > 0.74) {
                    yield COARSE_DIRT;
                }
                yield GRASS;
            }
            default -> throw new IllegalArgumentException("Not a North biome style: " + String.valueOf((Object)style));
        };
    }

    private static boolean isNorthernGrassClearing(BiomeStyle style, class_2680 surface, int worldX, int worldZ) {
        if (!MapChunkGenerator.isNorthStyle(style) || surface != GRASS) {
            return false;
        }
        double field = MapChunkGenerator.normalizedSample(BROAD_NOISE, worldX - 257, worldZ + 181, 112.0);
        double shape = MapChunkGenerator.normalizedSample(DETAIL_NOISE, worldX + 149, worldZ - 223, 38.0);
        double clearingScore = field * 0.72 + shape * 0.28;
        double threshold = switch (style.ordinal()) {
            case 6 -> 0.61;
            case 7 -> 0.64;
            case 8 -> 0.65;
            case 9 -> 0.66;
            default -> 1.0;
        };
        return clearingScore > threshold;
    }

    private static class_2680 roadSurface(int worldX, int worldZ) {
        double surfaceNoise = MapChunkGenerator.normalizedSample(DETAIL_NOISE, worldX + 17, worldZ - 41, 6.0);
        return surfaceNoise > 0.75 ? COARSE_DIRT : DIRT_PATH;
    }

    private static NorthernSurfaceAudit sampleNorthernSurfaceAudit(BiomeStyle style, int originX, int originZ) {
        int samplesPerAxis = 64;
        int sampleSpacing = 4;
        boolean[] occupiedRegions = new boolean[16];
        int grassCount = 0;
        int roadGrassCount = 0;
        long fingerprint = -3750763034362895579L;
        for (int sampleX = 0; sampleX < 64; ++sampleX) {
            int worldX = originX + sampleX * 4;
            for (int sampleZ = 0; sampleZ < 64; ++sampleZ) {
                String roadFinalSurface;
                String finalSurface;
                double micro;
                int worldZ = originZ + sampleZ * 4;
                double ridges = Math.pow(Math.abs(MapChunkGenerator.sample(RIDGE_NOISE, worldX, worldZ, 180.0)), 1.35);
                class_2680 base = MapChunkGenerator.northernBaseSurface(style, false, ridges, micro = MapChunkGenerator.sample(MICRO_NOISE, worldX, worldZ, 28.0));
                boolean grass = MapChunkGenerator.isNorthernGrassClearing(style, base, worldX, worldZ);
                String string = finalSurface = grass ? "minecraft:grass_block" : "minecraft:snow";
                String string2 = base == GRASS ? (MapChunkGenerator.roadSurface(worldX, worldZ) == COARSE_DIRT ? "minecraft:coarse_dirt" : "minecraft:dirt_path") : (roadFinalSurface = "minecraft:snow");
                if (grass) {
                    ++grassCount;
                    occupiedRegions[sampleX / 16 * 4 + sampleZ / 16] = true;
                }
                if ("minecraft:grass_block".equals(roadFinalSurface)) {
                    ++roadGrassCount;
                }
                fingerprint ^= (long)finalSurface.hashCode();
                fingerprint *= 1099511628211L;
                fingerprint ^= (long)roadFinalSurface.hashCode();
                fingerprint *= 1099511628211L;
            }
        }
        int occupiedRegionCount = 0;
        for (boolean occupied : occupiedRegions) {
            if (!occupied) continue;
            ++occupiedRegionCount;
        }
        return new NorthernSurfaceAudit(4096, grassCount, occupiedRegionCount, roadGrassCount, fingerprint);
    }

    private boolean tryPlaceTree(class_2791 chunk, int worldX, int worldZ, int localX, int localZ, ColumnProfile column) {
        TreePlacementProfile profile = this.treePlacementProfile(column.style);
        if (profile == TreePlacementProfile.NONE) {
            return false;
        }
        double coverage = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX + 211, worldZ - 173, profile.coverageScale);
        double anchor = this.treeAnchorScore(worldX, worldZ, profile.anchorScale);
        double noise = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX, worldZ, 34.0);
        double mix = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX + 101, worldZ - 77, 23.0);
        double form = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX - 87, worldZ + 55, 29.0);
        int y = column.surfaceY + 1;
        if (coverage < profile.coverageThreshold) {
            return false;
        }
        if (!this.isTreeAnchor(worldX, worldZ, profile.spacingRadius, profile.anchorScale, anchor)) {
            return false;
        }
        if (anchor < profile.anchorThreshold) {
            return false;
        }
        return switch (column.style.ordinal()) {
            case 4 -> {
                if (mix > 0.58) {
                    yield this.placePine(chunk, worldX, worldZ, localX, localZ, y, 6);
                }
                yield this.placeConifer(chunk, worldX, worldZ, localX, localZ, y, 5);
            }
            case 3 -> this.placeConifer(chunk, worldX, worldZ, localX, localZ, y, 4);
            case 9 -> this.placeNorthSpruceTree(chunk, worldX, worldZ, localX, localZ, y, column.style, mix, form);
            case 7 -> this.placeNorthSpruceTree(chunk, worldX, worldZ, localX, localZ, y, column.style, mix, form);
            case 6 -> this.placeNorthSpruceTree(chunk, worldX, worldZ, localX, localZ, y, column.style, mix, form);
            case 8 -> this.placeNorthSpruceTree(chunk, worldX, worldZ, localX, localZ, y, column.style, mix, form);
            case 11 -> {
                if (mix > 0.5) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10511.method_9564(), class_2246.field_10539.method_9564(), 5);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 5);
            }
            case 13 -> {
                if (mix > 0.52) {
                    yield this.placeConifer(chunk, worldX, worldZ, localX, localZ, y, 5);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10511.method_9564(), class_2246.field_10539.method_9564(), 4);
            }
            case 15 -> this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, form > 0.5 ? class_2246.field_10511.method_9564() : class_2246.field_10431.method_9564(), form > 0.5 ? class_2246.field_10539.method_9564() : class_2246.field_10503.method_9564(), 4);
            case 10, 14 -> {
                if (form > 0.58) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10511.method_9564(), class_2246.field_10539.method_9564(), 4);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 4);
            }
            case 19 -> this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 4);
            case 17 -> {
                if (mix > 0.64) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 5);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10511.method_9564(), class_2246.field_10539.method_9564(), 4);
            }
            case 16 -> this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 4);
            case 23 -> this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, mix > 0.62 ? class_2246.field_10511.method_9564() : class_2246.field_10431.method_9564(), mix > 0.62 ? class_2246.field_10539.method_9564() : class_2246.field_10503.method_9564(), 4);
            case 21 -> {
                if (mix > 0.58) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10010.method_9564(), class_2246.field_10035.method_9564(), 4);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 5);
            }
            case 20 -> this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 4);
            case 25 -> {
                if (mix > 0.55) {
                    yield this.placePine(chunk, worldX, worldZ, localX, localZ, y, 5);
                }
                yield this.placeConifer(chunk, worldX, worldZ, localX, localZ, y, 5);
            }
            case 27 -> this.placeConifer(chunk, worldX, worldZ, localX, localZ, y, 4);
            case 24 -> this.placeConifer(chunk, worldX, worldZ, localX, localZ, y, 4);
            case 31 -> {
                if (mix > 0.48) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10010.method_9564(), class_2246.field_10035.method_9564(), 4);
                }
                yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 4);
            }
            case 29 -> {
                if (mix > 0.55) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10010.method_9564(), class_2246.field_10035.method_9564(), 5);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 5);
            }
            case 28 -> {
                if (mix > 0.64) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 4);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 5);
            }
            case 35 -> {
                if (mix > 0.44) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 6);
                }
                yield this.placePine(chunk, worldX, worldZ, localX, localZ, y, 6);
            }
            case 33 -> {
                if (mix > 0.58) {
                    yield this.placeConifer(chunk, worldX, worldZ, localX, localZ, y, 6);
                }
                yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 5);
            }
            case 32 -> {
                if (mix > 0.66) {
                    yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 4);
                }
                yield this.placeConifer(chunk, worldX, worldZ, localX, localZ, y, 5);
            }
            case 39 -> {
                if (mix > 0.52) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 5);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10511.method_9564(), class_2246.field_10539.method_9564(), 4);
            }
            case 37 -> {
                if (mix > 0.62) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 4);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10511.method_9564(), class_2246.field_10539.method_9564(), 4);
            }
            case 36 -> this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 4);
            case 41 -> this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, mix > 0.68 ? class_2246.field_10010.method_9564() : class_2246.field_10431.method_9564(), mix > 0.68 ? class_2246.field_10035.method_9564() : class_2246.field_10503.method_9564(), 4);
            case 48 -> {
                if (mix > 0.55) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10511.method_9564(), class_2246.field_10539.method_9564(), 4);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564(), 4);
            }
            case 47 -> this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10511.method_9564(), class_2246.field_10539.method_9564(), 4);
            case 46 -> this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10533.method_9564(), class_2246.field_10098.method_9564(), 4);
            case 45, 52, 55 -> {
                if (mix > 0.5) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10533.method_9564(), class_2246.field_10098.method_9564(), 4);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10533.method_9564(), class_2246.field_10098.method_9564(), 4);
            }
            case 56, 57 -> false;
            case 59 -> {
                if (mix > 0.52) {
                    yield this.placeBroadTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10533.method_9564(), class_2246.field_10098.method_9564(), 5);
                }
                yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10533.method_9564(), class_2246.field_10098.method_9564(), 5);
            }
            case 58 -> this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, y, class_2246.field_10533.method_9564(), class_2246.field_10098.method_9564(), 5);
            default -> false;
        };
    }

    private void addCustomFoliage(class_3233 region, class_2791 chunk) {
        this.validateCustomNaturalStructureTemplates(region);
        class_1923 chunkPos = region.method_33561();
        class_5819 random = class_5819.method_43049((long)(region.method_8412() ^ chunkPos.method_8324() ^ 0x4F4B54L));
        for (int localX = 0; localX < 16; ++localX) {
            int worldX = chunkPos.method_8326() + localX;
            for (int localZ = 0; localZ < 16; ++localZ) {
                int worldZ = chunkPos.method_8328() + localZ;
                class_6880<class_1959> biome = this.getBiomeAt(worldX, worldZ);
                BiomeStyle style = MapChunkGenerator.getStyle(biome);
                if (!this.isTemperateStyle(style) && !this.isSnowyStyle(style) && this.wildCropState(style, worldX, worldZ) == null) continue;
                this.tryPlaceCustomFoliage(region, chunk, chunkPos, worldX, worldZ, style, random);
            }
        }
    }

    private boolean tryPlaceWildStructure(class_3233 region, class_2791 chunk) {
        int worldZ;
        this.validateCustomNaturalStructureTemplates(region);
        class_1923 chunkPos = region.method_33561();
        int centerX = chunkPos.method_8326() + 8;
        int centerZ = chunkPos.method_8328() + 8;
        BiomeStyle style = this.getStyleAt(centerX, centerZ, null);
        if (!this.isWildStructureStyle(style)) {
            return false;
        }
        if (!this.isWildStructureAnchorChunk(chunkPos)) {
            this.logCustomStructureAttempt("wild_structure", "failed", "anchor gate rejected", "chunk=" + chunkPos.field_9181 + "," + chunkPos.field_9180, centerX, centerZ, chunkPos, null, null);
            return false;
        }
        int worldX = chunkPos.method_8326() + this.wildStructureLocalOffset(chunkPos, true);
        Optional<SurfacePlacement> optionalSurface = this.resolveCustomFoliageSurface(region, worldX, worldZ = chunkPos.method_8328() + this.wildStructureLocalOffset(chunkPos, false));
        if (optionalSurface.isEmpty()) {
            this.logCustomStructureAttempt("wild_structure", "failed", "no valid surface", this.describeSurfaceFailure(region, worldX, worldZ), worldX, worldZ, chunkPos, null, null);
            return false;
        }
        SurfacePlacement surface = optionalSurface.get();
        if (surface.floorY() <= -60 || surface.floorY() >= 303) {
            this.logCustomStructureAttempt("wild_structure", "failed", "surface outside valid y range", "floorY=" + surface.floorY(), worldX, worldZ, chunkPos, null, null);
            return false;
        }
        if (!this.isWildStructureSurface(surface.foundationState())) {
            this.logCustomStructureAttempt("wild_structure", "failed", "invalid foundation surface", String.valueOf(class_7923.field_41175.method_10221((Object)surface.foundationState().method_26204())), worldX, worldZ, chunkPos, null, null);
            return false;
        }
        class_5819 random = class_5819.method_43049((long)(region.method_8412() ^ chunkPos.method_8324() ^ 0x57494C44L));
        return this.placeCustomNaturalStructure(region, chunkPos, worldX, worldZ, surface, CUSTOM_WILD_STRUCTURES, random, 0, 0, 7, 9, 0, false, "wild_structure");
    }

    private void tryPlaceCustomFoliage(class_3233 region, class_2791 chunk, class_1923 chunkPos, int worldX, int worldZ, BiomeStyle style, class_5819 random) {
        if (style == BiomeStyle.SEA || style == BiomeStyle.WALL) {
            return;
        }
        Optional<SurfacePlacement> optionalSurface = this.resolveCustomFoliageSurface(region, worldX, worldZ);
        if (optionalSurface.isEmpty()) {
            if (this.isSnowyStyle(style)) {
                this.logCustomStructureAttempt("snowy_surface", "failed", "no valid surface", this.describeSurfaceFailure(region, worldX, worldZ), worldX, worldZ, chunkPos, null, null);
            }
            return;
        }
        SurfacePlacement surface = optionalSurface.get();
        if (surface.floorY() <= -64 || surface.floorY() >= 307) {
            if (this.isSnowyStyle(style)) {
                this.logCustomStructureAttempt("snowy_surface", "failed", "surface outside valid y range", "floorY=" + surface.floorY() + " topY=" + surface.topY(), worldX, worldZ, chunkPos, null, null);
            }
            return;
        }
        this.tryPlaceWildCropPatch(region, chunk, chunkPos, worldX, worldZ, style, surface);
        if (this.isSnowyStyle(style)) {
            boolean snowyTreeAnchor;
            TreePlacementProfile treeProfile = this.treePlacementProfile(style);
            boolean bl = snowyTreeAnchor = treeProfile != TreePlacementProfile.NONE && this.isSnowyTreeAnchor(worldX, worldZ, style, treeProfile);
            if (snowyTreeAnchor && this.passesSnowyTreeDensityGate(worldX, worldZ, style)) {
                this.placeCustomNaturalStructure(region, chunkPos, worldX, worldZ, surface, CUSTOM_SNOWY_SPRUCE_STRUCTURES, random, 1, 6, 18, 20, 4, false, "snowy_spruce_tree");
            } else if (treeProfile != TreePlacementProfile.NONE) {
                this.logCustomStructureAttempt("snowy_spruce_tree", "failed", "anchor gate rejected", "style=" + String.valueOf((Object)style), worldX, worldZ, chunkPos, null, null);
            }
            if (this.isSnowyRockAnchor(worldX, worldZ) && this.passesSnowyRockDensityGate(worldX, worldZ)) {
                this.placeCustomNaturalStructure(region, chunkPos, worldX, worldZ, surface, CUSTOM_SNOWY_ROCK_STRUCTURES, random, 1, 4, 12, 14, 3, false, "snowy_rock");
            } else {
                this.logCustomStructureAttempt("snowy_rock", "failed", "anchor gate rejected", "style=" + String.valueOf((Object)style), worldX, worldZ, chunkPos, null, null);
            }
            return;
        }
        if (this.isTemperateStyle(style)) {
            TreePlacementProfile profile = this.treePlacementProfile(style);
            if (profile != TreePlacementProfile.NONE && this.supportsTemperateCustomTreeStructures(style) && this.isTemperateCustomTreeAnchor(worldX, worldZ, style, profile)) {
                this.placeCustomNaturalStructure(region, chunkPos, worldX, worldZ, surface, CUSTOM_OAK_TREE_STRUCTURES, random, 0, 5, false, "oak_tree", profile, 0.0);
            } else if (profile != TreePlacementProfile.NONE && this.supportsTemperateCustomTreeStructures(style)) {
                this.logCustomStructureAttempt("oak_tree", "failed", "anchor gate rejected", "style=" + String.valueOf((Object)style), worldX, worldZ, chunkPos, null, null);
            }
            if (this.isNaturePackStyle(style) && this.isGrassCoveredNaturalSurface(surface.foundationState())) {
                if (this.isTemperateNaturePackAnchor(worldX, worldZ, style)) {
                    this.placeCustomNaturalStructure(region, chunkPos, worldX, worldZ, surface, CUSTOM_NATURE_PACK_STRUCTURES, random, 0, 4, false, "nature_pack", null, 0.0);
                } else {
                    this.logCustomStructureAttempt("nature_pack", "failed", "anchor gate rejected", "style=" + String.valueOf((Object)style), worldX, worldZ, chunkPos, null, null);
                }
            }
        }
    }

    private void tryPlaceWildCropPatch(class_3233 region, class_2791 chunk, class_1923 chunkPos, int worldX, int worldZ, BiomeStyle style, SurfacePlacement surface) {
        int cellZ;
        class_2680 crop = this.wildCropState(style, worldX, worldZ);
        if (crop == null || !this.isGrassCoveredNaturalSurface(surface.foundationState())) {
            return;
        }
        int spacing = this.wildCropSpacing(style, crop);
        int cellX = Math.floorDiv(worldX, spacing);
        long seed = MapChunkGenerator.cellSeed(cellX, cellZ = Math.floorDiv(worldZ, spacing), 3255787245L);
        if (MapChunkGenerator.unit(seed) > this.wildCropChance(style, crop)) {
            return;
        }
        int anchorX = cellX * spacing + 2 + (int)Math.floor(MapChunkGenerator.unit(MapChunkGenerator.mix64(seed ^ 0x71A7C40DL)) * (double)Math.max(1, spacing - 4));
        int anchorZ = cellZ * spacing + 2 + (int)Math.floor(MapChunkGenerator.unit(MapChunkGenerator.mix64(seed ^ 0x41C09E7L)) * (double)Math.max(1, spacing - 4));
        if (worldX != anchorX || worldZ != anchorZ) {
            return;
        }
        int[][] offsets = new int[][]{{0, 0}, {2, 1}, {-2, 1}, {1, -2}, {-1, -2}, {3, -1}, {-3, 0}};
        int count = 3 + (int)Math.floor(MapChunkGenerator.unit(MapChunkGenerator.mix64(seed ^ 0x51A55EEDL)) * 4.0);
        for (int i = 0; i < count && i < offsets.length; ++i) {
            class_2338 pos;
            class_2680 cover;
            Optional<SurfacePlacement> patchSurface;
            int x = worldX + offsets[i][0];
            int z = worldZ + offsets[i][1];
            if (x < chunkPos.method_8326() || x > chunkPos.method_8327() || z < chunkPos.method_8328() || z > chunkPos.method_8329()) continue;
            Optional<SurfacePlacement> optional = patchSurface = i == 0 ? Optional.of(surface) : this.resolveCustomFoliageSurface(region, x, z);
            if (patchSurface.isEmpty() || !this.isGrassCoveredNaturalSurface(patchSurface.get().foundationState()) || !(cover = chunk.method_8320(pos = new class_2338(x, patchSurface.get().floorY() + 1, z))).method_26215() && !cover.method_27852(class_2246.field_10477)) continue;
            chunk.method_12010(pos, crop, false);
        }
    }

    private class_2680 wildCropState(BiomeStyle style, int worldX, int worldZ) {
        if (this.forbidsWildCrops(style)) {
            return null;
        }
        double selector = MapChunkGenerator.unit(MapChunkGenerator.cellSeed(Math.floorDiv(worldX, 16), Math.floorDiv(worldZ, 16), 2796667407L));
        return switch (style.ordinal()) {
            case 43, 44, 45, 46, 50, 51, 52, 53, 54, 55, 58, 59, 60 -> {
                if (selector < 0.45) {
                    yield WILD_ORANGE;
                }
                yield WILD_TOMATO;
            }
            case 28, 29, 30, 31, 36, 37, 39 -> {
                if (selector < 0.35) {
                    yield WILD_ORANGE;
                }
                if (selector < 0.75) {
                    yield WILD_TOMATO;
                }
                yield WILD_TURNIP;
            }
            case 32, 33, 35 -> {
                if (selector < 0.55) {
                    yield WILD_TOMATO;
                }
                yield WILD_TURNIP;
            }
            case 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 23 -> {
                if (selector < 0.6) {
                    yield WILD_TURNIP;
                }
                yield WILD_CHAMPIGNON;
            }
            case 6, 7, 8, 9 -> {
                if (selector < 0.7) {
                    yield WILD_TURNIP;
                }
                yield WILD_CHAMPIGNON;
            }
            case 25, 27, 47, 48, 49 -> {
                if (selector < 0.75) {
                    yield WILD_CHAMPIGNON;
                }
                yield WILD_TURNIP;
            }
            default -> null;
        };
    }

    private boolean forbidsWildCrops(BiomeStyle style) {
        return style == BiomeStyle.SEA || style == BiomeStyle.WALL || style == BiomeStyle.THE_GIFT || style == BiomeStyle.ALWAYS_WINTER || style == BiomeStyle.ALWAYS_WINTER_FOREST || style == BiomeStyle.ALWAYS_WINTER_HILLS;
    }

    private int wildCropSpacing(BiomeStyle style, class_2680 crop) {
        if (crop == WILD_ORANGE) {
            return 28;
        }
        if (crop == WILD_CHAMPIGNON) {
            return 18;
        }
        return this.isSnowyStyle(style) ? 26 : 22;
    }

    private double wildCropChance(BiomeStyle style, class_2680 crop) {
        if (crop == WILD_ORANGE) {
            return style == BiomeStyle.DORNE_OASIS || style == BiomeStyle.SLAVERS_BAY_OASIS || style == BiomeStyle.STEP_STONES_OASIS ? 0.24 : 0.14;
        }
        if (crop == WILD_CHAMPIGNON) {
            return 0.28;
        }
        return crop == WILD_TURNIP && this.isSnowyStyle(style) ? 0.18 : 0.22;
    }

    private boolean placeCustomNaturalStructure(class_3233 region, class_1923 chunkPos, int worldX, int worldZ, SurfacePlacement surface, List<String> structures, class_5819 random, int blendRadius, int supportDepth, int maxFloorDelta, int maxTerrainDelta, int maxCutDepth, boolean shapeTerrain, String structureType) {
        return this.placeCustomNaturalStructure(region, chunkPos, worldX, worldZ, surface, structures, random, blendRadius, supportDepth, maxFloorDelta, maxTerrainDelta, maxCutDepth, shapeTerrain, structureType, null, 0.0);
    }

    private void placeCustomNaturalStructure(class_3233 region, class_1923 chunkPos, int worldX, int worldZ, SurfacePlacement surface, List<String> structures, class_5819 random, int blendRadius, int supportDepth, boolean shapeTerrain, String structureType, TreePlacementProfile profile, double coverageBias) {
        this.placeCustomNaturalStructure(region, chunkPos, worldX, worldZ, surface, structures, random, blendRadius, supportDepth, 10, 12, 2, shapeTerrain, structureType, profile, coverageBias);
    }

    private boolean placeCustomNaturalStructure(class_3233 region, class_1923 chunkPos, int worldX, int worldZ, SurfacePlacement surface, List<String> structures, class_5819 random, int blendRadius, int supportDepth, int maxFloorDelta, int maxTerrainDelta, int maxCutDepth, boolean shapeTerrain, String structureType, TreePlacementProfile profile, double coverageBias) {
        int floorY;
        class_2338 origin;
        double requiredCoverage;
        double coverage;
        if (structures.isEmpty()) {
            this.logCustomStructureAttempt(structureType, "failed", "no structure paths configured", "", worldX, worldZ, chunkPos, null, null);
            return false;
        }
        if (profile != null && (coverage = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX + 211, worldZ - 173, profile.coverageScale)) < (requiredCoverage = Math.max(0.0, profile.coverageThreshold - coverageBias))) {
            this.logCustomStructureAttempt(structureType, "failed", "coverage below threshold", "coverage=" + coverage + " required=" + requiredCoverage, worldX, worldZ, chunkPos, null, null);
            return false;
        }
        String structurePath = structures.get(random.method_43048(structures.size()));
        Optional optionalTemplate = region.method_8410().method_14183().method_15094(class_2960.method_60655((String)"agob", (String)structurePath));
        if (optionalTemplate.isEmpty()) {
            this.logCustomStructureAttempt(structureType, "failed", "missing template " + structurePath, "", worldX, worldZ, chunkPos, null, structurePath);
            return false;
        }
        class_3499 template = (class_3499)optionalTemplate.get();
        class_2470 rotation = this.randomNaturalStructureRotation(worldX, worldZ, structureType);
        class_2382 rotatedSize = this.rotateNaturalStructureSize(template.method_15160(), rotation);
        class_3341 localBounds = this.calculateLocalNaturalStructureBounds(template, rotation);
        class_3341 placedBounds = this.offsetNaturalStructureBounds(localBounds, origin = this.alignNaturalStructureOrigin(chunkPos, worldX, worldZ, surface.floorY(), localBounds, blendRadius, structureType));
        if (!this.canPlaceNaturalStructure(chunkPos, placedBounds, blendRadius)) {
            this.logCustomStructureAttempt(structureType, "failed", "origin outside current chunk bounds", "size=" + String.valueOf(template.method_15160()), worldX, worldZ, chunkPos, origin, structurePath);
            return false;
        }
        if (!this.canPlaceNaturalStructure(region, placedBounds)) {
            this.logCustomStructureAttempt(structureType, "failed", "origin outside chunk region bounds", "size=" + String.valueOf(template.method_15160()), worldX, worldZ, chunkPos, origin, structurePath);
            return false;
        }
        if (("wild_structure".equals(structureType) || "hireable_camp".equals(structureType)) && this.isReservedWildStructureArea(region, placedBounds)) {
            this.logCustomStructureAttempt(structureType, "failed", "reserved near castle placement", this.describeBlockBox(placedBounds), worldX, worldZ, chunkPos, origin, structurePath);
            return false;
        }
        SurfaceSampleStats surfaceStats = this.sampleNaturalStructureSurface(region, placedBounds);
        if (!this.isValidNaturalStructureFootprint(surface, surfaceStats, maxFloorDelta, maxTerrainDelta)) {
            int floorY2 = this.resolveNaturalStructureFloorY(surfaceStats);
            this.logCustomStructureAttempt(structureType, "failed", "invalid footprint terrainDelta=" + (surfaceStats.maxY() - surfaceStats.minY()) + " floorDelta=" + Math.abs(floorY2 - surface.floorY()), "surfaceFloor=" + surface.floorY() + " sampledMin=" + surfaceStats.minY() + " sampledMax=" + surfaceStats.maxY(), worldX, worldZ, chunkPos, origin, structurePath);
            return false;
        }
        int placedFloorY = floorY = this.resolveNaturalStructureFloorY(surfaceStats);
        if ("snowy_rock".equals(structureType)) {
            placedFloorY = Math.max(-62, floorY - 2);
        }
        if (!this.canPlaceNaturalStructure(region, placedBounds = this.offsetNaturalStructureBounds(localBounds, origin = new class_2338(origin.method_10263(), placedFloorY + 1, origin.method_10260())))) {
            this.logCustomStructureAttempt(structureType, "failed", "final bounds outside chunk region", this.describeBlockBox(placedBounds), worldX, worldZ, chunkPos, origin, structurePath);
            return false;
        }
        if (shapeTerrain) {
            this.prepareNaturalStructurePad(region, chunkPos, origin, rotatedSize, floorY, blendRadius, supportDepth, maxCutDepth);
        }
        class_3492 placement = new class_3492().method_15133(false).method_15131(false).method_27264(false).method_15123(rotation).method_16184((class_3491)class_3793.field_16721).method_15126(this.chunkBlockBox(region, chunkPos));
        try {
            if (!template.method_15172((class_5425)region, origin, origin, placement, random, 3)) {
                this.logCustomStructureAttempt(structureType, "failed", "template.place returned false", "size=" + String.valueOf(template.method_15160()), worldX, worldZ, chunkPos, origin, structurePath);
                return false;
            }
        }
        catch (RuntimeException exception) {
            this.logCustomStructureAttempt(structureType, "failed", "template.place threw", exception.getClass().getSimpleName() + ": " + this.summarizeExceptionMessage(exception), worldX, worldZ, chunkPos, origin, structurePath);
            return false;
        }
        if ("snowy_spruce_tree".equals(structureType)) {
            this.replaceSnowySpruceTemplateBlocks(region, placedBounds);
        }
        if (shapeTerrain) {
            this.fillNaturalStructureSupports(region, origin, rotatedSize, origin.method_10264(), supportDepth);
        }
        this.logCustomStructureAttempt(structureType, "success", "placed", "size=" + String.valueOf(template.method_15160()), worldX, worldZ, chunkPos, origin, structurePath);
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void logCustomStructureAttempt(String structureType, String result, String reason, String detail, int worldX, int worldZ, class_1923 chunkPos, class_2338 origin, String structurePath) {
        long now = System.currentTimeMillis();
        Object object = CUSTOM_STRUCTURE_LOG_LOCK;
        synchronized (object) {
            this.flushCustomStructureAttemptLogs(now, false);
            String key = structureType + "|" + result + "|" + reason;
            CustomStructureLogCounter counter = CUSTOM_STRUCTURE_LOG_COUNTS.computeIfAbsent(key, unused -> new CustomStructureLogCounter(structureType, result, reason));
            ++counter.count;
            counter.sampleWorldX = worldX;
            counter.sampleWorldZ = worldZ;
            counter.sampleChunkX = chunkPos.field_9181;
            counter.sampleChunkZ = chunkPos.field_9180;
            counter.sampleOrigin = origin == null ? "n/a" : origin.method_10263() + "," + origin.method_10264() + "," + origin.method_10260();
            counter.sampleTemplate = structurePath == null ? "n/a" : structurePath;
            counter.sampleDetail = detail == null || detail.isBlank() ? "n/a" : detail;
        }
    }

    private void flushCustomStructureAttemptLogs(long now, boolean force) {
        if (!force && now - customStructureLogWindowStartMs < 10000L) {
            return;
        }
        if (!CUSTOM_STRUCTURE_LOG_COUNTS.isEmpty()) {
            CUSTOM_STRUCTURE_LOG_COUNTS.clear();
        }
        customStructureLogWindowStartMs = now;
    }

    private boolean isTemperateCustomTreeAnchor(int worldX, int worldZ, BiomeStyle style, TreePlacementProfile profile) {
        if (this.customTreeDensityHeat(worldX, worldZ, style) < this.customTreeHeatThreshold(style)) {
            return false;
        }
        double anchorScale = profile.anchorScale * 0.92;
        double anchor = this.treeAnchorScore(worldX, worldZ, anchorScale);
        int spacingRadius = Math.max(5, profile.spacingRadius + this.customTreeSpacingBonus(style));
        if (!this.isTreeAnchor(worldX, worldZ, spacingRadius, anchorScale, anchor)) {
            return false;
        }
        return anchor >= Math.max(0.0, profile.anchorThreshold - this.customTreeAnchorRelaxation(style));
    }

    private boolean isSparseFloraAnchor(int worldX, int worldZ, int radius, double scale, double threshold) {
        double score = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX + 31, worldZ - 47, scale);
        if (score < threshold) {
            return false;
        }
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dz = -radius; dz <= radius; ++dz) {
                if (dx == 0 && dz == 0 || Math.abs(dx) + Math.abs(dz) > radius + 1) continue;
                double other = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX + dx + 31, worldZ + dz - 47, scale);
                if (other > score) {
                    return false;
                }
                if (other != score || dx >= 0 && (dx != 0 || dz >= 0)) continue;
                return false;
            }
        }
        return true;
    }

    private boolean isTemperateNaturePackAnchor(int worldX, int worldZ, BiomeStyle style) {
        double detail;
        double patch = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX + 173, worldZ - 211, 88.0);
        double heat = patch * 0.7 + (detail = MapChunkGenerator.normalizedSample(DETAIL_NOISE, worldX - 59, worldZ + 101, 31.0)) * 0.3;
        if (heat < this.naturePackHeatThreshold(style)) {
            return false;
        }
        double anchorScale = 17.0;
        double anchor = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX - 43, worldZ + 67, anchorScale);
        int spacingRadius = this.naturePackSpacingRadius(style);
        if (!this.isTreeAnchor(worldX, worldZ, spacingRadius, anchorScale, anchor)) {
            return false;
        }
        return anchor >= this.naturePackAnchorThreshold(style);
    }

    private boolean isSnowyTreeAnchor(int worldX, int worldZ, BiomeStyle style, TreePlacementProfile profile) {
        double coverageBonus = this.snowyTreeCoverageBonus(style);
        double anchorBonus = this.snowyTreeAnchorBonus(style);
        int spacingBonus = this.snowyTreeSpacingBonus(style);
        double coverage = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX + 211, worldZ - 173, profile.coverageScale * 0.82);
        if (coverage < profile.coverageThreshold + coverageBonus) {
            return false;
        }
        double anchor = this.treeAnchorScore(worldX, worldZ, profile.anchorScale * 0.9);
        if (!this.isTreeAnchor(worldX, worldZ, Math.max(4, profile.spacingRadius + spacingBonus), profile.anchorScale * 0.9, anchor)) {
            return false;
        }
        return anchor >= profile.anchorThreshold + anchorBonus;
    }

    private boolean isSnowyRockAnchor(int worldX, int worldZ) {
        double noise = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX + 37, worldZ - 29, 24.0);
        double anchor = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX - 91, worldZ + 67, 13.0);
        if (noise < 0.82 || anchor < 0.72) {
            return false;
        }
        return this.isTreeAnchor(worldX, worldZ, 7, 13.0, anchor);
    }

    private boolean passesSnowyTreeDensityGate(int worldX, int worldZ, BiomeStyle style) {
        return MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX - 143, worldZ + 89, 80.0) > this.snowyTreeDensityThreshold(style);
    }

    private boolean passesSnowyRockDensityGate(int worldX, int worldZ) {
        return MapChunkGenerator.normalizedSample(DETAIL_NOISE, worldX + 57, worldZ - 131, 72.0) > 0.76;
    }

    private boolean isCustomRockAnchor(int worldX, int worldZ) {
        double noise = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX + 37, worldZ - 29, 30.0);
        double anchor = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX - 91, worldZ + 67, 15.0);
        if (noise < 0.74 || anchor < 0.62) {
            return false;
        }
        return this.isTreeAnchor(worldX, worldZ, 4, 15.0, anchor);
    }

    private boolean isValidCustomFoliageSurface(class_2680 state) {
        return state == GRASS || state == DIRT || state == COARSE_DIRT || state == PODZOL || state == STONE || state == COBBLESTONE || state == ANDESITE || state == SNOW || state == PACKED_ICE || state.method_27852(class_2246.field_10477);
    }

    private boolean isGrassCoveredNaturalSurface(class_2680 state) {
        return state == GRASS || state == DIRT || state == COARSE_DIRT || state == PODZOL;
    }

    private boolean isSnowyStyle(BiomeStyle style) {
        return style == BiomeStyle.ALWAYS_WINTER || style == BiomeStyle.ALWAYS_WINTER_FOREST || style == BiomeStyle.ALWAYS_WINTER_HILLS || style == BiomeStyle.THE_NORTH || style == BiomeStyle.THE_NORTH_FOREST || style == BiomeStyle.THE_NORTH_HILLS || style == BiomeStyle.THE_NORTH_WOLFSWOOD;
    }

    private boolean isTemperateStyle(BiomeStyle style) {
        return !this.isSnowyStyle(style) && style != BiomeStyle.DORNE && style != BiomeStyle.DORNE_HILLS && style != BiomeStyle.DORNE_SCRUBLAND && style != BiomeStyle.DORNE_OASIS && style != BiomeStyle.SLAVERS_BAY && style != BiomeStyle.SLAVERS_BAY_HILLS && style != BiomeStyle.SLAVERS_BAY_OASIS && style != BiomeStyle.STEP_STONES && style != BiomeStyle.STEP_STONES_HILLS && style != BiomeStyle.STEP_STONES_OASIS && style != BiomeStyle.DOTHRAKI_SEA && style != BiomeStyle.DOTHRAKI_SEA_STEPPE;
    }

    private boolean supportsTemperateCustomTreeStructures(BiomeStyle style) {
        return this.isTemperateStyle(style);
    }

    private boolean isWildStructureStyle(BiomeStyle style) {
        return style != BiomeStyle.SEA && style != BiomeStyle.WALL;
    }

    private boolean isNaturePackStyle(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 10, 11, 13, 14, 15, 16, 17, 19, 20, 21, 23, 25, 27, 28, 29, 31, 32, 33, 35, 36, 37, 39, 47, 48, 56, 57, 58, 59 -> true;
            default -> false;
        };
    }

    private class_2338 alignNaturalStructureOrigin(class_1923 chunkPos, int worldX, int worldZ, int floorY, class_3341 localBounds, int blendRadius, String structureType) {
        int boundsCenterX = Math.round((float)(localBounds.method_35415() + localBounds.method_35418()) / 2.0f);
        int boundsCenterZ = Math.round((float)(localBounds.method_35417() + localBounds.method_35420()) / 2.0f);
        class_2382 boundsSize = new class_2382(localBounds.method_35418() - localBounds.method_35415() + 1, localBounds.method_35419() - localBounds.method_35416() + 1, localBounds.method_35420() - localBounds.method_35417() + 1);
        int jitterRadius = this.customNaturalStructureJitterRadius(structureType, boundsSize);
        int jitterX = this.naturalStructureJitter(worldX, worldZ, structureType, true, jitterRadius);
        int jitterZ = this.naturalStructureJitter(worldX, worldZ, structureType, false, jitterRadius);
        if (!"wild_structure".equals(structureType)) {
            return new class_2338(worldX + jitterX - boundsCenterX, floorY, worldZ + jitterZ - boundsCenterZ);
        }
        int minOriginX = chunkPos.method_8326() + blendRadius - localBounds.method_35415();
        int maxOriginX = chunkPos.method_8327() - blendRadius - localBounds.method_35418();
        int minOriginZ = chunkPos.method_8328() + blendRadius - localBounds.method_35417();
        int maxOriginZ = chunkPos.method_8329() - blendRadius - localBounds.method_35420();
        if (maxOriginX < minOriginX || maxOriginZ < minOriginZ) {
            return new class_2338(chunkPos.method_8326(), floorY, chunkPos.method_8328());
        }
        int x = class_3532.method_15340((int)(worldX + jitterX - boundsCenterX), (int)minOriginX, (int)maxOriginX);
        int z = class_3532.method_15340((int)(worldZ + jitterZ - boundsCenterZ), (int)minOriginZ, (int)maxOriginZ);
        return new class_2338(x, floorY, z);
    }

    private boolean canPlaceNaturalStructure(class_1923 chunkPos, class_3341 bounds, int blendRadius) {
        int minX = chunkPos.method_8326() + blendRadius;
        int maxX = chunkPos.method_8327() - blendRadius;
        int minZ = chunkPos.method_8328() + blendRadius;
        int maxZ = chunkPos.method_8329() - blendRadius;
        return bounds.method_35415() >= minX && bounds.method_35418() <= maxX && bounds.method_35417() >= minZ && bounds.method_35420() <= maxZ;
    }

    private boolean canPlaceNaturalStructure(class_3233 region, class_3341 bounds) {
        return this.canPlaceNaturalStructure(region.method_33561(), bounds, 0) && bounds.method_35416() < region.method_31600() && bounds.method_35419() >= region.method_31607();
    }

    private class_3341 calculateNaturalStructureBounds(class_3499 template, class_2338 origin, class_2470 rotation) {
        return template.method_27267(origin, rotation, class_2338.field_10980, class_2415.field_11302);
    }

    private class_3341 calculateLocalNaturalStructureBounds(class_3499 template, class_2470 rotation) {
        return this.calculateNaturalStructureBounds(template, class_2338.field_10980, rotation);
    }

    private class_3341 offsetNaturalStructureBounds(class_3341 localBounds, class_2338 origin) {
        return new class_3341(localBounds.method_35415() + origin.method_10263(), localBounds.method_35416() + origin.method_10264(), localBounds.method_35417() + origin.method_10260(), localBounds.method_35418() + origin.method_10263(), localBounds.method_35419() + origin.method_10264(), localBounds.method_35420() + origin.method_10260());
    }

    private String describeBlockBox(class_3341 bounds) {
        return bounds.method_35415() + "," + bounds.method_35416() + "," + bounds.method_35417() + " -> " + bounds.method_35418() + "," + bounds.method_35419() + "," + bounds.method_35420();
    }

    private String summarizeExceptionMessage(RuntimeException exception) {
        String message = exception.getMessage();
        if (message == null || message.isBlank()) {
            return "no message";
        }
        return message.length() > 160 ? message.substring(0, 160) : message;
    }

    private boolean isWildStructureAnchorChunk(class_1923 chunkPos) {
        int regionX = Math.floorDiv(chunkPos.field_9181, 24);
        int regionZ = Math.floorDiv(chunkPos.field_9180, 24);
        int localX = Math.floorMod(chunkPos.field_9181, 24);
        int localZ = Math.floorMod(chunkPos.field_9180, 24);
        int span = Math.max(1, 18);
        double targetNoiseX = MapChunkGenerator.normalizedSample(DECORATION_NOISE, regionX * 37 + 211, regionZ * 37 - 79, 1.0);
        double targetNoiseZ = MapChunkGenerator.normalizedSample(DETAIL_NOISE, regionX * 37 - 113, regionZ * 37 + 167, 1.0);
        int targetX = 3 + Math.min(span - 1, (int)Math.floor(targetNoiseX * (double)span));
        int targetZ = 3 + Math.min(span - 1, (int)Math.floor(targetNoiseZ * (double)span));
        return localX == targetX && localZ == targetZ;
    }

    private int wildStructureLocalOffset(class_1923 chunkPos, boolean xAxis) {
        double noise = MapChunkGenerator.normalizedSample(xAxis ? DETAIL_NOISE : DECORATION_NOISE, chunkPos.field_9181 * 29 + (xAxis ? 197 : -151), chunkPos.field_9180 * 29 + (xAxis ? -83 : 223), 1.0);
        return 2 + Math.min(11, (int)Math.floor(noise * 12.0));
    }

    private boolean isHireableCampAnchorChunk(class_1923 chunkPos) {
        return chunkPos.field_9181 == MapChunkGenerator.hireableCampAnchorChunkCoordinate(chunkPos.field_9181, chunkPos.field_9180, true) && chunkPos.field_9180 == MapChunkGenerator.hireableCampAnchorChunkCoordinate(chunkPos.field_9181, chunkPos.field_9180, false);
    }

    private int hireableCampLocalOffset(class_1923 chunkPos, boolean xAxis) {
        return MapChunkGenerator.hireableCampLocalOffsetFor(chunkPos, xAxis);
    }

    public static List<PredictedHireableCampSite> findNearestHireableCampSites(class_3218 world, class_2338 origin, int limit) {
        if (world == null || origin == null || limit <= 0) {
            return List.of();
        }
        class_1923 centerChunk = new class_1923(origin);
        int centerRegionX = Math.floorDiv(centerChunk.field_9181, 200);
        int centerRegionZ = Math.floorDiv(centerChunk.field_9180, 200);
        ArrayList<PredictedHireableCampSite> predictedSites = new ArrayList<PredictedHireableCampSite>();
        LinkedHashSet<Long> seenChunks = new LinkedHashSet<Long>();
        for (int radius = 0; radius <= 24 && predictedSites.size() < limit * 2; ++radius) {
            MapChunkGenerator.collectPredictedHireableCampRing(world, centerRegionX, centerRegionZ, radius, seenChunks, predictedSites);
        }
        return predictedSites.stream().sorted(Comparator.comparingDouble(site -> site.teleportPos().method_10262((class_2382)origin))).limit(limit).toList();
    }

    private static void collectPredictedHireableCampRing(class_3218 world, int centerRegionX, int centerRegionZ, int radius, Set<Long> seenChunks, List<PredictedHireableCampSite> predictedSites) {
        if (radius == 0) {
            MapChunkGenerator.addPredictedHireableCamp(world, centerRegionX, centerRegionZ, seenChunks, predictedSites);
            return;
        }
        for (int regionX = centerRegionX - radius; regionX <= centerRegionX + radius; ++regionX) {
            MapChunkGenerator.addPredictedHireableCamp(world, regionX, centerRegionZ - radius, seenChunks, predictedSites);
            MapChunkGenerator.addPredictedHireableCamp(world, regionX, centerRegionZ + radius, seenChunks, predictedSites);
        }
        for (int regionZ = centerRegionZ - radius + 1; regionZ <= centerRegionZ + radius - 1; ++regionZ) {
            MapChunkGenerator.addPredictedHireableCamp(world, centerRegionX - radius, regionZ, seenChunks, predictedSites);
            MapChunkGenerator.addPredictedHireableCamp(world, centerRegionX + radius, regionZ, seenChunks, predictedSites);
        }
    }

    private static void addPredictedHireableCamp(class_3218 world, int regionX, int regionZ, Set<Long> seenChunks, List<PredictedHireableCampSite> predictedSites) {
        int anchorChunkZ;
        int anchorChunkX = MapChunkGenerator.hireableCampAnchorChunkCoordinate(regionX * 200, regionZ * 200, true);
        class_1923 chunkPos = new class_1923(anchorChunkX, anchorChunkZ = MapChunkGenerator.hireableCampAnchorChunkCoordinate(regionX * 200, regionZ * 200, false));
        if (!seenChunks.add(chunkPos.method_8324())) {
            return;
        }
        int worldX = chunkPos.method_8326() + MapChunkGenerator.hireableCampLocalOffsetFor(chunkPos, true);
        int worldZ = chunkPos.method_8328() + MapChunkGenerator.hireableCampLocalOffsetFor(chunkPos, false);
        String biomePath = world.method_23753(new class_2338(worldX, world.method_8615(), worldZ)).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        Kingdom kingdom = GotKingdoms.byBiomePath(biomePath);
        if (kingdom == null) {
            return;
        }
        List<HireableCampDefinition> eligibleDefinitions = DynamicTroopRegistry.getHireableCampDefinitions().stream().filter(definition -> definition.canSpawnInKingdom(kingdom.getId(), MapChunkGenerator.hireableCampSpawnSeed(chunkPos))).toList();
        if (eligibleDefinitions.isEmpty()) {
            return;
        }
        HireableCampDefinition definition2 = eligibleDefinitions.get(Math.floorMod(chunkPos.field_9181 * 31 + chunkPos.field_9180, eligibleDefinitions.size()));
        class_2338 teleportPos = new class_2338(worldX, Math.max(world.method_8615() + 40, 120), worldZ);
        predictedSites.add(new PredictedHireableCampSite(definition2.id(), definition2.claimName(), kingdom.getId().toString(), chunkPos, teleportPos));
    }

    private static int hireableCampAnchorChunkCoordinate(int baseChunkX, int baseChunkZ, boolean xAxis) {
        int regionX = Math.floorDiv(baseChunkX, 200);
        int regionZ = Math.floorDiv(baseChunkZ, 200);
        int span = Math.max(1, 150);
        double targetNoise = MapChunkGenerator.normalizedSample(xAxis ? DECORATION_NOISE : DETAIL_NOISE, regionX * 43 + (xAxis ? 149 : -97), regionZ * 43 + (xAxis ? -61 : 191), 1.0);
        int localTarget = 25 + Math.min(span - 1, (int)Math.floor(targetNoise * (double)span));
        return (xAxis ? regionX : regionZ) * 200 + localTarget;
    }

    private static long hireableCampSpawnSeed(class_1923 chunkPos) {
        return (long)chunkPos.field_9181 * 73L + (long)chunkPos.field_9180 * 37L + 19L;
    }

    private static int hireableCampLocalOffsetFor(class_1923 chunkPos, boolean xAxis) {
        double noise = MapChunkGenerator.normalizedSample(xAxis ? DETAIL_NOISE : DECORATION_NOISE, chunkPos.field_9181 * 17 + (xAxis ? 83 : -127), chunkPos.field_9180 * 17 + (xAxis ? -211 : 59), 1.0);
        return 3 + Math.min(9, (int)Math.floor(noise * 10.0));
    }

    private boolean isWildStructureSurface(class_2680 state) {
        return state == GRASS || state == DIRT || state == COARSE_DIRT || state == PODZOL || state == STONE || state == COBBLESTONE || state == ANDESITE;
    }

    private int naturalStructureJitter(int worldX, int worldZ, String structureType, boolean xAxis, int radius) {
        if (radius <= 0) {
            return 0;
        }
        int xOffset = xAxis ? 173 : -91;
        int zOffset = xAxis ? -47 : 131;
        double noise = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX + xOffset + structureType.hashCode(), worldZ + zOffset - structureType.hashCode(), 11.0);
        return class_3532.method_15340((int)Math.round((float)((noise - 0.5) * (double)radius * 2.0)), (int)(-radius), (int)radius);
    }

    private int customNaturalStructureJitterRadius(String structureType, class_2382 size) {
        int maxSize = Math.max(size.method_10263(), size.method_10260());
        if ("oak_tree".equals(structureType)) {
            return Math.max(2, Math.min(4, 7 - maxSize / 2));
        }
        if ("nature_pack".equals(structureType)) {
            return Math.max(1, Math.min(3, 6 - maxSize / 2));
        }
        if ("snowy_spruce_tree".equals(structureType)) {
            return Math.max(1, Math.min(3, 6 - maxSize / 2));
        }
        if ("wild_structure".equals(structureType)) {
            return Math.max(2, Math.min(5, 8 - maxSize / 2));
        }
        if ("hireable_camp".equals(structureType)) {
            return Math.max(1, Math.min(4, 7 - maxSize / 2));
        }
        return Math.max(1, Math.min(2, 5 - maxSize / 2));
    }

    private class_2470 randomNaturalStructureRotation(int worldX, int worldZ, String structureType) {
        int hash = structureType.hashCode();
        double noise = MapChunkGenerator.normalizedSample(DETAIL_NOISE, worldX + hash * 3, worldZ - hash * 5, 9.0);
        int index = class_3532.method_15340((int)((int)Math.floor(noise * 4.0)), (int)0, (int)3);
        return switch (index) {
            case 1 -> class_2470.field_11463;
            case 2 -> class_2470.field_11464;
            case 3 -> class_2470.field_11465;
            default -> class_2470.field_11467;
        };
    }

    private class_2382 rotateNaturalStructureSize(class_2382 size, class_2470 rotation) {
        return switch (rotation) {
            case class_2470.field_11463, class_2470.field_11465 -> new class_2382(size.method_10260(), size.method_10264(), size.method_10263());
            default -> size;
        };
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void validateCustomNaturalStructureTemplates(class_3233 region) {
        if (customNaturalTemplatesValidated) {
            return;
        }
        Class<MapChunkGenerator> clazz = MapChunkGenerator.class;
        synchronized (MapChunkGenerator.class) {
            if (customNaturalTemplatesValidated) {
                // ** MonitorExit[var2_2] (shouldn't be in output)
                return;
            }
            this.validateCustomNaturalStructureTemplates(region, CUSTOM_OAK_TREE_STRUCTURES, "oak_tree");
            this.validateCustomNaturalStructureTemplates(region, CUSTOM_SNOWY_SPRUCE_STRUCTURES, "snowy_spruce_tree");
            this.validateCustomNaturalStructureTemplates(region, CUSTOM_SNOWY_ROCK_STRUCTURES, "snowy_rock");
            this.validateCustomNaturalStructureTemplates(region, CUSTOM_NATURE_PACK_STRUCTURES, "nature_pack");
            this.validateCustomNaturalStructureTemplates(region, CUSTOM_WILD_STRUCTURES, "wild_structure");
            this.validateCustomHireableCampTemplates(region);
            customNaturalTemplatesValidated = true;
            // ** MonitorExit[var2_2] (shouldn't be in output)
            return;
        }
    }

    private void validateCustomHireableCampTemplates(class_3233 region) {
        for (HireableCampDefinition definition : DynamicTroopRegistry.getHireableCampDefinitions()) {
            if (!region.method_8410().method_14183().method_15094(class_2960.method_60655((String)"agob", (String)definition.structurePath())).isEmpty()) continue;
            GotMod.LOGGER.warn("Missing configured hireable camp template id={} path={}", (Object)definition.id(), (Object)definition.structurePath());
        }
    }

    private boolean isReservedWildStructureArea(class_3233 region, class_3341 bounds) {
        CastleWorldState state = CastleWorldState.get(region.method_8410());
        int minChunkX = (bounds.method_35415() >> 4) - 2;
        int maxChunkX = (bounds.method_35418() >> 4) + 2;
        int minChunkZ = (bounds.method_35417() >> 4) - 2;
        int maxChunkZ = (bounds.method_35420() >> 4) + 2;
        for (int chunkX = minChunkX; chunkX <= maxChunkX; ++chunkX) {
            for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; ++chunkZ) {
                if (state.findClaimAt(new class_2338((chunkX << 4) + 8, 63, (chunkZ << 4) + 8)) == null) continue;
                return true;
            }
        }
        int overlayBuffer = GotCastles.getClaimRadiusChunks() + 2;
        return CastleChunkIndex.intersectsChunkWindow(region.method_8410(), minChunkX - overlayBuffer, minChunkZ - overlayBuffer, maxChunkX + overlayBuffer, maxChunkZ + overlayBuffer);
    }

    private void validateCustomNaturalStructureTemplates(class_3233 region, List<String> structures, String structureType) {
        for (String structurePath : structures) {
            if (!region.method_8410().method_14183().method_15094(class_2960.method_60655((String)"agob", (String)structurePath)).isEmpty()) continue;
            GotMod.LOGGER.warn("Missing configured custom natural structure template type={} path={}", (Object)structureType, (Object)structurePath);
        }
    }

    private SurfaceSampleStats sampleNaturalStructureSurface(class_3233 region, class_2338 origin, class_2382 size) {
        int totalY = 0;
        int samples = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        ArrayList<Integer> heights = new ArrayList<Integer>();
        for (int x = origin.method_10263(); x < origin.method_10263() + size.method_10263(); x += Math.max(1, size.method_10263() / 3)) {
            for (int z = origin.method_10260(); z < origin.method_10260() + size.method_10260(); z += Math.max(1, size.method_10260() / 3)) {
                int y = this.resolveCustomFoliageSurface(region, x, z).map(SurfacePlacement::floorY).orElse(region.method_8624(class_2902.class_2903.field_13203, x, z) - 1);
                totalY += y;
                ++samples;
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
                heights.add(y);
            }
        }
        if (samples == 0) {
            return new SurfaceSampleStats(origin.method_10264(), origin.method_10264(), origin.method_10264(), 0, List.of());
        }
        int averageY = class_3532.method_15340((int)Math.round((float)totalY / (float)samples), (int)minY, (int)maxY);
        return new SurfaceSampleStats(averageY, minY, maxY, samples, List.copyOf(heights));
    }

    private SurfaceSampleStats sampleNaturalStructureSurface(class_3233 region, class_3341 bounds) {
        int totalY = 0;
        int samples = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        ArrayList<Integer> heights = new ArrayList<Integer>();
        int width = bounds.method_35418() - bounds.method_35415() + 1;
        int depth = bounds.method_35420() - bounds.method_35417() + 1;
        for (int x = bounds.method_35415(); x <= bounds.method_35418(); x += Math.max(1, width / 3)) {
            for (int z = bounds.method_35417(); z <= bounds.method_35420(); z += Math.max(1, depth / 3)) {
                int y = this.resolveCustomFoliageSurface(region, x, z).map(SurfacePlacement::floorY).orElse(region.method_8624(class_2902.class_2903.field_13203, x, z) - 1);
                totalY += y;
                ++samples;
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
                heights.add(y);
            }
        }
        if (samples == 0) {
            return new SurfaceSampleStats(bounds.method_35416(), bounds.method_35416(), bounds.method_35416(), 0, List.of());
        }
        int averageY = class_3532.method_15340((int)Math.round((float)totalY / (float)samples), (int)minY, (int)maxY);
        return new SurfaceSampleStats(averageY, minY, maxY, samples, List.copyOf(heights));
    }

    private boolean isValidNaturalStructureFootprint(SurfacePlacement anchorSurface, SurfaceSampleStats sampledSurface, int maxFloorDelta, int maxTerrainDelta) {
        if (sampledSurface.samples() <= 0) {
            return false;
        }
        if (sampledSurface.minY() <= -60) {
            return false;
        }
        if (sampledSurface.maxY() - sampledSurface.minY() > maxTerrainDelta) {
            return false;
        }
        int floorY = this.resolveNaturalStructureFloorY(sampledSurface);
        return Math.abs(floorY - anchorSurface.floorY()) <= maxFloorDelta;
    }

    private void prepareNaturalStructurePad(class_3233 region, class_1923 chunkPos, class_2338 origin, class_2382 size, int floorY, int blendRadius, int supportDepth, int maxCutDepth) {
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        int editMinX = Math.max(minX - blendRadius, chunkPos.method_8326());
        int editMaxX = Math.min(maxX + blendRadius, chunkPos.method_8327());
        int editMinZ = Math.max(minZ - blendRadius, chunkPos.method_8328());
        int editMaxZ = Math.min(maxZ + blendRadius, chunkPos.method_8329());
        for (int x = editMinX; x <= editMaxX; ++x) {
            for (int z = editMinZ; z <= editMaxZ; ++z) {
                int y;
                int distance = this.outsideDistance(x, z, minX, maxX, minZ, maxZ);
                if (distance > blendRadius) continue;
                int currentSurfaceY = region.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
                float alpha = distance == 0 ? 1.0f : 1.0f - (float)distance / (float)(blendRadius + 1);
                int desiredY = Math.round(class_3532.method_48781((float)alpha, (int)currentSurfaceY, (int)floorY));
                int targetY = Math.max(desiredY, currentSurfaceY - maxCutDepth);
                class_2680 surface = this.naturalSurfaceBlock(region, x, z);
                class_2680 subsurface = surface == SNOW ? DIRT : (surface == PACKED_ICE ? PACKED_ICE : DIRT);
                for (int y2 = Math.max(currentSurfaceY + 6, targetY + 6); y2 > targetY; --y2) {
                    this.setRegionBlockState(region, chunkPos, new class_2338(x, y2, z), AIR, 2);
                }
                this.setRegionBlockState(region, chunkPos, new class_2338(x, targetY, z), surface, 2);
                for (int depth = 1; depth <= supportDepth && (y = targetY - depth) > -64; ++depth) {
                    this.setRegionBlockState(region, chunkPos, new class_2338(x, y, z), subsurface, 2);
                }
            }
        }
    }

    private int outsideDistance(int x, int z, int minX, int maxX, int minZ, int maxZ) {
        int dx = x < minX ? minX - x : Math.max(0, x - maxX);
        int dz = z < minZ ? minZ - z : Math.max(0, z - maxZ);
        return Math.max(dx, dz);
    }

    private void fillNaturalStructureSupports(class_3233 region, class_2338 origin, class_2382 size, int floorY, int supportDepth) {
        class_1923 chunkPos = region.method_33561();
        for (int x = origin.method_10263(); x < origin.method_10263() + size.method_10263(); ++x) {
            for (int z = origin.method_10260(); z < origin.method_10260() + size.method_10260(); ++z) {
                class_2338 pos;
                class_2680 surface = this.naturalSurfaceBlock(region, x, z);
                class_2680 fill = surface == SNOW ? DIRT : (surface == PACKED_ICE ? PACKED_ICE : DIRT);
                int placed = 0;
                for (int y = floorY - 1; y > -64 && placed < Math.max(8, supportDepth * 2) && region.method_8320(pos = new class_2338(x, y, z)).method_26215(); ++placed, --y) {
                    this.setRegionBlockState(region, chunkPos, pos, fill, 2);
                }
            }
        }
    }

    private class_2680 naturalSurfaceBlock(class_3233 region, int x, int z) {
        Optional<SurfacePlacement> optionalSurface = this.resolveCustomFoliageSurface(region, x, z);
        if (optionalSurface.isPresent()) {
            class_2680 state = optionalSurface.get().surfaceState();
            if (state == SNOW || state == PACKED_ICE || state.method_27852(class_2246.field_10477)) {
                return state;
            }
            if (state == PODZOL) {
                return PODZOL;
            }
            if (state == COARSE_DIRT) {
                return COARSE_DIRT;
            }
            if (state == STONE || state == COBBLESTONE || state == ANDESITE) {
                return state;
            }
        }
        return GRASS;
    }

    private Optional<SurfacePlacement> resolveCustomFoliageSurface(class_3233 region, int worldX, int worldZ) {
        int topY = region.method_8624(class_2902.class_2903.field_13203, worldX, worldZ) - 1;
        if (topY <= -64) {
            return Optional.empty();
        }
        int searchTop = Math.min(319, topY + 3);
        int searchFloor = Math.max(-63, topY - 12);
        Integer topSnowY = null;
        class_2680 topSnowState = null;
        for (int y = searchTop; y >= searchFloor; --y) {
            class_2338 topSurfacePos;
            class_2338 pos = new class_2338(worldX, y, worldZ);
            class_2680 state = region.method_8320(pos);
            if (state.method_26215()) continue;
            if (state.method_27852(class_2246.field_10477)) {
                if (topSnowY != null) continue;
                topSnowY = y;
                topSnowState = state;
                continue;
            }
            if (state == SNOW) {
                if (topSnowY != null) continue;
                topSnowY = y;
                topSnowState = state;
                continue;
            }
            if (!this.isValidCustomFoliageSurface(state)) continue;
            int topSurfaceY = y;
            class_2680 topSurfaceState = state;
            for (int coverY = y + 1; coverY <= Math.min(319, y + 4); ++coverY) {
                class_2680 coverState = region.method_8320(new class_2338(worldX, coverY, worldZ));
                if (!coverState.method_27852(class_2246.field_10477) && coverState != SNOW) {
                    if (coverState.method_26215()) break;
                    topSurfaceY = y;
                    topSurfaceState = state;
                    break;
                }
                topSurfaceY = coverY;
                topSurfaceState = coverState;
                topSnowY = coverY;
                topSnowState = coverState;
            }
            if (topSnowY != null && topSnowY > topSurfaceY) {
                topSurfaceY = topSnowY;
                topSurfaceState = topSnowState;
            }
            if (!this.isReplaceableCustomStructureCover(region.method_8320((topSurfacePos = new class_2338(worldX, topSurfaceY, worldZ)).method_10084()))) continue;
            return Optional.of(new SurfacePlacement(y, topSurfaceY, topSurfaceState, state));
        }
        return Optional.empty();
    }

    private String describeSurfaceFailure(class_3233 region, int worldX, int worldZ) {
        int topY = region.method_8624(class_2902.class_2903.field_13203, worldX, worldZ) - 1;
        if (topY <= -64) {
            return "topY=" + topY;
        }
        class_2680 top = region.method_8320(new class_2338(worldX, topY, worldZ));
        class_2680 above = topY + 1 <= 319 ? region.method_8320(new class_2338(worldX, topY + 1, worldZ)) : AIR;
        class_2680 below = topY - 1 > -64 ? region.method_8320(new class_2338(worldX, topY - 1, worldZ)) : AIR;
        class_2680 belowTwo = topY - 2 > -64 ? region.method_8320(new class_2338(worldX, topY - 2, worldZ)) : AIR;
        return "topY=" + topY + " top=" + String.valueOf(class_7923.field_41175.method_10221((Object)top.method_26204())) + " above=" + String.valueOf(class_7923.field_41175.method_10221((Object)above.method_26204())) + " below=" + String.valueOf(class_7923.field_41175.method_10221((Object)below.method_26204())) + " below2=" + String.valueOf(class_7923.field_41175.method_10221((Object)belowTwo.method_26204()));
    }

    private boolean isReplaceableCustomStructureCover(class_2680 state) {
        if (state.method_26215()) {
            return true;
        }
        return state.method_27852(class_2246.field_10477) || state == SNOW || state.method_27852(class_2246.field_10479) || state.method_27852(class_2246.field_10214) || state.method_27852(class_2246.field_10112) || state.method_27852(class_2246.field_10313) || state.method_27852(class_2246.field_10428) || state.method_27852(class_2246.field_10111) || state.method_26204() instanceof class_2320;
    }

    private void replaceSnowySpruceTemplateBlocks(class_3233 region, class_3341 bounds) {
        class_1923 chunkPos = region.method_33561();
        for (int x = bounds.method_35415(); x <= bounds.method_35418(); ++x) {
            for (int y = bounds.method_35416(); y <= bounds.method_35419(); ++y) {
                for (int z = bounds.method_35417(); z <= bounds.method_35420(); ++z) {
                    class_2338 pos = new class_2338(x, y, z);
                    class_2680 state = region.method_8320(pos);
                    class_2680 replacement = this.mapSnowySpruceTemplateBlock(state);
                    if (replacement == state) continue;
                    this.setRegionBlockState(region, chunkPos, pos, replacement, 2);
                }
            }
        }
    }

    private class_3341 chunkBlockBox(class_3233 region, class_1923 chunkPos) {
        return new class_3341(chunkPos.method_8326(), region.method_31607(), chunkPos.method_8328(), chunkPos.method_8327(), region.method_31600(), chunkPos.method_8329());
    }

    private boolean canEditRegionChunk(class_3233 region, class_1923 chunkPos, class_2338 pos) {
        return pos.method_10263() >= chunkPos.method_8326() && pos.method_10263() <= chunkPos.method_8327() && pos.method_10260() >= chunkPos.method_8328() && pos.method_10260() <= chunkPos.method_8329() && pos.method_10264() >= region.method_31607() && pos.method_10264() < region.method_31600();
    }

    private void setRegionBlockState(class_3233 region, class_1923 chunkPos, class_2338 pos, class_2680 state, int flags) {
        if (this.canEditRegionChunk(region, chunkPos, pos)) {
            region.method_8652(pos, state, flags);
        }
    }

    private class_2680 mapSnowySpruceTemplateBlock(class_2680 state) {
        if (state.method_27852(class_2246.field_10127) || state.method_27852(class_2246.field_10311)) {
            return AIR;
        }
        if (state.method_27852(class_2246.field_10266) || state.method_27852(class_2246.field_9986) || state.method_27852(class_2246.field_23867) || state.method_27852(class_2246.field_23866)) {
            return this.copySharedProperties(state, class_2246.field_9975.method_9564());
        }
        if (state.method_27852(class_2246.field_10390) || state.method_27852(class_2246.field_10478)) {
            return this.copySharedProperties(state, class_2246.field_10071.method_9564());
        }
        if (state.method_27852(class_2246.field_10159) || state.method_27852(class_2246.field_10497)) {
            return this.copySharedProperties(state, class_2246.field_10569.method_9564());
        }
        if (state.method_27852(class_2246.field_10364) || state.method_27852(class_2246.field_10311)) {
            return this.copySharedProperties(state, class_2246.field_10020.method_9564());
        }
        return state;
    }

    private class_2680 copySharedProperties(class_2680 source, class_2680 target) {
        class_2680 result = target;
        for (class_2769 property : source.method_28501()) {
            if (!result.method_28498(property)) continue;
            result = (class_2680)result.method_11657(property, source.method_11654(property));
        }
        return result;
    }

    private int resolveNaturalStructureFloorY(SurfaceSampleStats sampledSurface) {
        ArrayList<Integer> heights = new ArrayList<Integer>(sampledSurface.heights());
        if (heights.isEmpty()) {
            return sampledSurface.averageY();
        }
        heights.sort(Integer::compareTo);
        int median = this.percentile(heights, 0.5);
        int upperQuartile = this.percentile(heights, 0.75);
        return class_3532.method_15340((int)Math.round((float)(median + upperQuartile) / 2.0f), (int)median, (int)sampledSurface.maxY());
    }

    private int percentile(List<Integer> sortedValues, double percentile) {
        int index = (int)Math.round((double)(sortedValues.size() - 1) * percentile);
        index = Math.max(0, Math.min(index, sortedValues.size() - 1));
        return sortedValues.get(index);
    }

    private double treeAnchorScore(int worldX, int worldZ, double scale) {
        return MapChunkGenerator.normalizedSample(TREE_NOISE, worldX - 59, worldZ + 43, scale);
    }

    private boolean isTreeAnchor(int worldX, int worldZ, int radius, double scale, double score) {
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dz = -radius; dz <= radius; ++dz) {
                if (dx == 0 && dz == 0 || Math.abs(dx) + Math.abs(dz) > radius + 1) continue;
                double other = this.treeAnchorScore(worldX + dx, worldZ + dz, scale);
                if (other > score) {
                    return false;
                }
                if (other != score || dx >= 0 && (dx != 0 || dz >= 0)) continue;
                return false;
            }
        }
        return true;
    }

    private TreePlacementProfile treePlacementProfile(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 4 -> new TreePlacementProfile(0.48, 0.54, 4, 96.0, 18.0);
            case 7, 9 -> new TreePlacementProfile(0.36, 0.48, 3, 80.0, 15.0);
            case 11, 17, 21, 29, 33, 35, 37, 39, 48, 59 -> new TreePlacementProfile(0.52, 0.58, 4, 98.0, 18.0);
            case 31 -> new TreePlacementProfile(0.6, 0.63, 5, 96.0, 18.0);
            case 3, 13, 25 -> new TreePlacementProfile(0.62, 0.63, 5, 110.0, 19.0);
            case 6 -> new TreePlacementProfile(0.5, 0.55, 4, 92.0, 17.0);
            case 10, 14, 15, 16, 19, 20, 23, 28, 32, 36, 41, 45, 47, 52, 55, 58 -> new TreePlacementProfile(0.74, 0.69, 6, 132.0, 21.0);
            case 8 -> new TreePlacementProfile(0.56, 0.58, 4, 98.0, 17.0);
            case 24, 27, 38, 46 -> new TreePlacementProfile(0.78, 0.72, 6, 138.0, 21.0);
            case 22, 30, 34, 44, 49 -> new TreePlacementProfile(0.84, 0.76, 7, 144.0, 22.0);
            default -> TreePlacementProfile.NONE;
        };
    }

    private double snowyTreeCoverageBonus(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 6, 7, 9 -> -0.08;
            case 8 -> -0.05;
            default -> 0.04;
        };
    }

    private double snowyTreeAnchorBonus(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 6, 7, 9 -> -0.08;
            case 8 -> -0.06;
            default -> 0.06;
        };
    }

    private int snowyTreeSpacingBonus(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 6, 7, 9 -> 1;
            case 8 -> 2;
            default -> 3;
        };
    }

    private double snowyTreeDensityThreshold(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 6 -> 0.52;
            case 7 -> 0.5;
            case 9 -> 0.48;
            case 8 -> 0.56;
            default -> 0.7;
        };
    }

    private double customTreeDensityHeat(int worldX, int worldZ, BiomeStyle style) {
        double broad = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX - 143, worldZ + 89, 96.0);
        double detail = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX + 67, worldZ - 121, 44.0);
        double patch = MapChunkGenerator.normalizedSample(DETAIL_NOISE, worldX - 211, worldZ + 173, 28.0);
        double heat = broad * 0.5 + detail * 0.35 + patch * 0.15;
        if (style == BiomeStyle.THE_VALE_FOREST || style == BiomeStyle.THE_STORMLANDS_RAINWOOD || style == BiomeStyle.THE_CROWNLANDS_KINGSWOOD) {
            heat += 0.03;
        }
        if (style == BiomeStyle.THE_VALE || style == BiomeStyle.THE_VALE_VALLEY || style == BiomeStyle.THE_VALE_MEADOW) {
            heat -= 0.02;
        }
        if (style == BiomeStyle.THE_WESTERLANDS_HILLS || style == BiomeStyle.DORNE_HILLS || style == BiomeStyle.BRAAVOS_HILLS) {
            heat -= 0.04;
        }
        return class_3532.method_15350((double)heat, (double)0.0, (double)1.0);
    }

    private double customTreeHeatThreshold(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 11, 17, 29, 33, 35, 37, 39 -> 0.55;
            case 13, 25, 31, 48, 59 -> 0.58;
            case 10, 14, 15, 16, 19, 28, 32, 36, 41, 45, 47, 52, 55, 58 -> 0.62;
            case 20, 21, 23, 24, 27, 38, 46 -> 0.66;
            case 22, 30, 34, 44, 49 -> 0.7;
            default -> 1.0;
        };
    }

    private double customTreeAnchorRelaxation(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 11, 17, 29, 33, 35, 37, 39 -> 0.08;
            case 13, 25, 31, 48, 59 -> 0.07;
            case 10, 14, 15, 16, 19, 20, 23, 28, 32, 36, 41, 45, 47, 52, 55, 58 -> 0.06;
            default -> 0.05;
        };
    }

    private int customTreeSpacingBonus(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 11, 17, 29, 33, 35, 37, 39 -> 2;
            case 13, 25, 31, 48, 59 -> 3;
            default -> 2;
        };
    }

    private double naturePackHeatThreshold(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 11, 17, 29, 33, 35, 37, 39 -> 0.72;
            case 25, 31, 48, 59 -> 0.75;
            default -> 0.79;
        };
    }

    private int naturePackSpacingRadius(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 11, 17, 29, 33, 35, 37, 39 -> 8;
            case 25, 31, 48, 59 -> 9;
            default -> 10;
        };
    }

    private double naturePackAnchorThreshold(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 11, 17, 29, 33, 35, 37, 39 -> 0.64;
            case 25, 31, 48, 59 -> 0.66;
            default -> 0.69;
        };
    }

    private boolean tryPlaceBoulder(class_2791 chunk, int worldX, int worldZ, int localX, int localZ, ColumnProfile column) {
        double threshold;
        class_2680 boulder;
        if (!this.isWithinChunkMargin(chunk, worldX, worldZ, 1)) {
            return false;
        }
        double noise = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX + 19, worldZ - 11, 22.0);
        switch (column.style.ordinal()) {
            case 3: 
            case 5: {
                class_2680 class_26802 = PACKED_ICE;
                break;
            }
            case 6: 
            case 7: 
            case 8: 
            case 9: {
                class_2680 class_26802 = STONE;
                break;
            }
            case 10: 
            case 12: 
            case 13: 
            case 20: 
            case 22: 
            case 23: 
            case 32: 
            case 34: 
            case 35: 
            case 44: 
            case 51: 
            case 53: 
            case 54: {
                class_2680 class_26802 = STONE;
                break;
            }
            case 24: 
            case 26: 
            case 27: {
                class_2680 class_26802 = MOSSY_COBBLESTONE;
                break;
            }
            case 40: 
            case 42: {
                class_2680 class_26802 = BASALT;
                break;
            }
            default: {
                class_2680 class_26802 = boulder = null;
            }
        }
        if (boulder == null) {
            return false;
        }
        switch (column.style.ordinal()) {
            case 6: 
            case 7: 
            case 8: 
            case 9: {
                double d = 0.95;
                break;
            }
            case 24: 
            case 40: {
                double d = 0.92;
                break;
            }
            default: {
                double d = threshold = 0.965;
            }
        }
        if (noise <= threshold) {
            return false;
        }
        int baseY = column.surfaceY + 1;
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dz = -1; dz <= 1; ++dz) {
                if (Math.abs(dx) + Math.abs(dz) > 2) continue;
                this.setDecorationBlockState(chunk, new class_2338(worldX + dx, baseY, worldZ + dz), boulder);
            }
        }
        return true;
    }

    private void tryPlaceFlora(class_2791 chunk, int worldX, int worldZ, int localX, int localZ, ColumnProfile column) {
        double noise = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX, worldZ, 13.0);
        double patch = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX + 61, worldZ - 43, 17.0);
        double accent = MapChunkGenerator.normalizedSample(DETAIL_NOISE, worldX - 19, worldZ + 83, 19.0);
        int y = column.surfaceY + 1;
        switch (column.style.ordinal()) {
            case 3: 
            case 4: 
            case 5: {
                if (!(noise > 0.95)) break;
                this.placeShrub(chunk, worldX, y, worldZ, class_2246.field_10037.method_9564(), class_2246.field_9988.method_9564());
                break;
            }
            case 6: 
            case 7: 
            case 8: 
            case 9: {
                if (noise > 0.94 && column.style == BiomeStyle.THE_NORTH_WOLFSWOOD) {
                    this.placeShrub(chunk, worldX, y, worldZ, class_2246.field_10037.method_9564(), class_2246.field_9988.method_9564());
                    break;
                }
                if (!(noise > 0.78)) break;
                chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10112.method_9564(), false);
                break;
            }
            case 32: 
            case 33: 
            case 34: 
            case 35: {
                if (patch > 0.93 && column.style == BiomeStyle.THE_STORMLANDS_RAINWOOD) {
                    this.placeShrub(chunk, worldX, y, worldZ, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564());
                    break;
                }
                if (noise > 0.82) {
                    this.placeTallPlant(chunk, worldX, y, worldZ, class_2246.field_10313.method_9564());
                    break;
                }
                if (noise > 0.58) {
                    chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10112.method_9564(), false);
                    break;
                }
                if (!(noise > 0.42)) break;
                chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10479.method_9564(), false);
                break;
            }
            case 10: 
            case 11: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 19: 
            case 28: 
            case 29: 
            case 31: 
            case 36: 
            case 37: 
            case 39: 
            case 59: {
                if (patch > 0.94 && (column.style == BiomeStyle.THE_REACH_ORCHARD || column.style == BiomeStyle.THE_CROWNLANDS_KINGSWOOD)) {
                    this.placeShrub(chunk, worldX, y, worldZ, class_2246.field_10431.method_9564(), class_2246.field_10503.method_9564());
                    break;
                }
                if (noise > 0.9 || column.style == BiomeStyle.THE_VALE_MEADOW && accent > 0.7) {
                    chunk.method_12010(new class_2338(worldX, y, worldZ), this.flowerForStyle(column.style, worldX, worldZ), false);
                    break;
                }
                if (noise > 0.7) {
                    this.placeTallPlant(chunk, worldX, y, worldZ, class_2246.field_10214.method_9564());
                    break;
                }
                if (!(noise > 0.44)) break;
                if (column.style == BiomeStyle.THE_RIVERLANDS_MARSH && accent > 0.56) {
                    chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10112.method_9564(), false);
                    break;
                }
                chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10479.method_9564(), false);
                break;
            }
            case 13: 
            case 20: 
            case 21: 
            case 23: 
            case 27: 
            case 47: 
            case 48: 
            case 56: 
            case 57: 
            case 58: {
                if (noise > 0.9 && (column.style == BiomeStyle.THE_WESTERLANDS || column.style == BiomeStyle.THE_WESTERLANDS_FOREST || column.style == BiomeStyle.THE_WESTERLANDS_GOLDGRASS || column.style == BiomeStyle.BRAAVOS || column.style == BiomeStyle.BRAAVOS_FOREST)) {
                    chunk.method_12010(new class_2338(worldX, y, worldZ), this.flowerForStyle(column.style, worldX, worldZ), false);
                    break;
                }
                if (patch > 0.95 && column.style == BiomeStyle.IRON_ISLANDS_MOOR) {
                    this.placeShrub(chunk, worldX, y, worldZ, class_2246.field_10037.method_9564(), class_2246.field_9988.method_9564());
                    break;
                }
                if (noise > 0.78) {
                    this.placeTallPlant(chunk, worldX, y, worldZ, class_2246.field_10214.method_9564());
                    break;
                }
                if (!(noise > 0.45)) break;
                if (column.style == BiomeStyle.IRON_ISLANDS_MOOR && accent > 0.58) {
                    chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10112.method_9564(), false);
                    break;
                }
                chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10479.method_9564(), false);
                break;
            }
            case 43: 
            case 44: 
            case 46: 
            case 50: 
            case 51: 
            case 53: 
            case 54: {
                if (patch > 0.985 && column.style == BiomeStyle.DORNE_SCRUBLAND && this.isSparseFloraAnchor(worldX, worldZ, 7, 9.0, 0.82)) {
                    this.placeShrub(chunk, worldX, y, worldZ, class_2246.field_10533.method_9564(), class_2246.field_10098.method_9564());
                    break;
                }
                if (noise > 0.965 && this.isSparseFloraAnchor(worldX, worldZ, 8, 11.0, 0.86)) {
                    this.placeCactus(chunk, worldX, worldZ, y, 2 + (int)(accent * 2.0));
                    break;
                }
                if (noise > 0.72 && column.style == BiomeStyle.DORNE_SCRUBLAND) {
                    chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10479.method_9564(), false);
                    break;
                }
                if (!(noise > 0.8) || !this.isSparseFloraAnchor(worldX, worldZ, 5, 7.0, 0.78)) break;
                chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10428.method_9564(), false);
                break;
            }
            case 45: 
            case 52: 
            case 55: {
                if (patch > 0.93) {
                    this.placeShrub(chunk, worldX, y, worldZ, class_2246.field_10533.method_9564(), class_2246.field_10098.method_9564());
                    break;
                }
                if (noise > 0.82) {
                    this.placeTallPlant(chunk, worldX, y, worldZ, class_2246.field_10214.method_9564());
                    break;
                }
                if (noise > 0.68 && column.style == BiomeStyle.DORNE_OASIS) {
                    chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10479.method_9564(), false);
                    break;
                }
                if (noise > 0.9 && column.style != BiomeStyle.DORNE_OASIS) {
                    chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10428.method_9564(), false);
                    break;
                }
                if (!(noise > 0.5)) break;
                chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10479.method_9564(), false);
                break;
            }
            case 40: 
            case 41: 
            case 42: {
                if (noise > 0.82 && column.surfaceBlock == GRASS) {
                    chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10479.method_9564(), false);
                    break;
                }
                if (!(noise > 0.76) || column.surfaceBlock != COARSE_DIRT) break;
                chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10428.method_9564(), false);
                break;
            }
        }
    }

    private void placeSeaDecoration(class_2791 chunk, int worldX, int worldZ, int localX, int localZ, ColumnProfile column) {
        double noise = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX, worldZ, 15.0);
        int y = column.surfaceY + 1;
        if (column.surfaceY >= 61 && noise > 0.65) {
            chunk.method_12010(new class_2338(worldX, y, worldZ), class_2246.field_10376.method_9564(), false);
        }
    }

    private boolean placeRoundTree(class_2791 chunk, int worldX, int worldZ, int localX, int localZ, int baseY, class_2680 log, class_2680 leaves, int trunkHeight) {
        if (localX < 3 || localX > 12 || localZ < 3 || localZ > 12 || baseY >= 311) {
            return false;
        }
        for (int i = 0; i < trunkHeight; ++i) {
            this.setDecorationBlockState(chunk, new class_2338(worldX, baseY + i, worldZ), log);
        }
        int canopyBase = baseY + trunkHeight - 2;
        for (int dx = -2; dx <= 2; ++dx) {
            for (int dz = -2; dz <= 2; ++dz) {
                for (int dy = 0; dy <= 2; ++dy) {
                    class_2338 pos;
                    if (Math.abs(dx) + Math.abs(dz) + dy > 4 || !this.getDecorationBlockState(chunk, pos = new class_2338(worldX + dx, canopyBase + dy, worldZ + dz)).method_26215()) continue;
                    this.setDecorationBlockState(chunk, pos, leaves);
                }
            }
        }
        this.setDecorationBlockState(chunk, new class_2338(worldX, canopyBase + 3, worldZ), leaves);
        return true;
    }

    private boolean placeConifer(class_2791 chunk, int worldX, int worldZ, int localX, int localZ, int baseY, int trunkHeight) {
        if (localX < 3 || localX > 12 || localZ < 3 || localZ > 12 || baseY >= 309) {
            return false;
        }
        for (int i = 0; i < trunkHeight; ++i) {
            this.setDecorationBlockState(chunk, new class_2338(worldX, baseY + i, worldZ), class_2246.field_10037.method_9564());
        }
        int top = baseY + trunkHeight;
        for (int layer = 0; layer < 4; ++layer) {
            int radius = Math.max(1, 3 - layer);
            int y = top - layer;
            for (int dx = -radius; dx <= radius; ++dx) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    class_2338 pos;
                    if (Math.abs(dx) + Math.abs(dz) > radius + 1 || !this.getDecorationBlockState(chunk, pos = new class_2338(worldX + dx, y, worldZ + dz)).method_26215()) continue;
                    this.setDecorationBlockState(chunk, pos, class_2246.field_9988.method_9564());
                }
            }
        }
        this.setDecorationBlockState(chunk, new class_2338(worldX, top + 1, worldZ), class_2246.field_9988.method_9564());
        return true;
    }

    private boolean placePine(class_2791 chunk, int worldX, int worldZ, int localX, int localZ, int baseY, int trunkHeight) {
        if (localX < 3 || localX > 12 || localZ < 3 || localZ > 12 || baseY >= 307) {
            return false;
        }
        for (int i = 0; i < trunkHeight; ++i) {
            this.setDecorationBlockState(chunk, new class_2338(worldX, baseY + i, worldZ), class_2246.field_10037.method_9564());
        }
        int top = baseY + trunkHeight;
        for (int layer = 0; layer < 5; ++layer) {
            int radius = layer < 2 ? 1 : 2 - Math.min(1, layer - 2);
            int layerY = top - layer;
            for (int dx = -radius; dx <= radius; ++dx) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    class_2338 pos;
                    if (Math.abs(dx) + Math.abs(dz) > radius + (layer == 0 ? 0 : 1) || !this.getDecorationBlockState(chunk, pos = new class_2338(worldX + dx, layerY, worldZ + dz)).method_26215()) continue;
                    this.setDecorationBlockState(chunk, pos, class_2246.field_9988.method_9564());
                }
            }
        }
        this.setDecorationBlockState(chunk, new class_2338(worldX, top + 1, worldZ), class_2246.field_9988.method_9564());
        return true;
    }

    private boolean placeNorthSpruceTree(class_2791 chunk, int worldX, int worldZ, int localX, int localZ, int baseY, BiomeStyle style, double variantNoise, double heightNoise) {
        int heightBonus = heightNoise > 0.68 ? 1 : 0;
        return switch (style.ordinal()) {
            case 9 -> {
                if (variantNoise > 0.68) {
                    yield this.placePine(chunk, worldX, worldZ, localX, localZ, baseY, 8 + heightBonus);
                }
                if (variantNoise > 0.22) {
                    yield this.placeConifer(chunk, worldX, worldZ, localX, localZ, baseY, 7 + heightBonus);
                }
                yield this.placePine(chunk, worldX, worldZ, localX, localZ, baseY, 7);
            }
            case 7 -> {
                if (variantNoise > 0.54) {
                    yield this.placePine(chunk, worldX, worldZ, localX, localZ, baseY, 7 + heightBonus);
                }
                yield this.placeConifer(chunk, worldX, worldZ, localX, localZ, baseY, 6 + heightBonus);
            }
            case 6 -> {
                if (variantNoise > 0.96) {
                    yield this.placeRoundTree(chunk, worldX, worldZ, localX, localZ, baseY, class_2246.field_10511.method_9564(), class_2246.field_10539.method_9564(), 4);
                }
                if (variantNoise > 0.58) {
                    yield this.placePine(chunk, worldX, worldZ, localX, localZ, baseY, 6 + heightBonus);
                }
                yield this.placeConifer(chunk, worldX, worldZ, localX, localZ, baseY, 5 + heightBonus);
            }
            case 8 -> {
                if (variantNoise > 0.48) {
                    yield this.placePine(chunk, worldX, worldZ, localX, localZ, baseY, 6 + heightBonus);
                }
                yield this.placeConifer(chunk, worldX, worldZ, localX, localZ, baseY, 5 + heightBonus);
            }
            default -> this.placeConifer(chunk, worldX, worldZ, localX, localZ, baseY, 5 + heightBonus);
        };
    }

    private boolean placeBroadTree(class_2791 chunk, int worldX, int worldZ, int localX, int localZ, int baseY, class_2680 log, class_2680 leaves, int trunkHeight) {
        if (localX < 4 || localX > 11 || localZ < 4 || localZ > 11 || baseY >= 310) {
            return false;
        }
        for (int i = 0; i < trunkHeight; ++i) {
            this.setDecorationBlockState(chunk, new class_2338(worldX, baseY + i, worldZ), log);
        }
        int canopyBase = baseY + trunkHeight - 2;
        for (int dy = 0; dy <= 3; ++dy) {
            int radius = dy == 3 ? 1 : 2;
            for (int dx = -radius; dx <= radius; ++dx) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    class_2338 pos;
                    if (Math.abs(dx) + Math.abs(dz) > radius + 1 || !this.getDecorationBlockState(chunk, pos = new class_2338(worldX + dx, canopyBase + dy, worldZ + dz)).method_26215()) continue;
                    this.setDecorationBlockState(chunk, pos, leaves);
                }
            }
        }
        this.setDecorationBlockState(chunk, new class_2338(worldX, canopyBase + 4, worldZ), leaves);
        return true;
    }

    private void placeShrub(class_2791 chunk, int x, int y, int z, class_2680 log, class_2680 leaves) {
        if (y >= 317 || !this.isWithinChunkMargin(chunk, x, z, 1)) {
            return;
        }
        this.setDecorationBlockState(chunk, new class_2338(x, y, z), log);
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dz = -1; dz <= 1; ++dz) {
                class_2338 pos;
                if (Math.abs(dx) + Math.abs(dz) > 1 || !this.getDecorationBlockState(chunk, pos = new class_2338(x + dx, y + 1, z + dz)).method_26215()) continue;
                this.setDecorationBlockState(chunk, pos, leaves);
            }
        }
    }

    private void placeTallPlant(class_2791 chunk, int x, int y, int z, class_2680 plant) {
        if (y >= 319) {
            return;
        }
        this.setDecorationBlockState(chunk, new class_2338(x, y, z), (class_2680)plant.method_11657((class_2769)class_2320.field_10929, (Comparable)class_2756.field_12607));
        this.setDecorationBlockState(chunk, new class_2338(x, y + 1, z), (class_2680)plant.method_11657((class_2769)class_2320.field_10929, (Comparable)class_2756.field_12609));
    }

    private void placeCactus(class_2791 chunk, int x, int z, int y, int height) {
        if (!this.canPlaceCactus(chunk, x, z, y, height)) {
            return;
        }
        for (int i = 0; i < height && y + i <= 319; ++i) {
            chunk.method_12010(new class_2338(x, y + i, z), class_2246.field_10029.method_9564(), false);
        }
    }

    private boolean canPlaceCactus(class_2791 chunk, int x, int z, int y, int height) {
        if (!this.isWithinChunkMargin(chunk, x, z, 1)) {
            return false;
        }
        class_2680 below = this.getDecorationBlockState(chunk, new class_2338(x, y - 1, z));
        if (!below.method_27852(class_2246.field_10102) && !below.method_27852(class_2246.field_10534)) {
            return false;
        }
        for (int i = 0; i < height && y + i <= 319; ++i) {
            class_2338 pos = new class_2338(x, y + i, z);
            if (!this.getDecorationBlockState(chunk, pos).method_26215()) {
                return false;
            }
            for (class_2350 direction : class_2350.class_2353.field_11062) {
                if (this.getDecorationBlockState(chunk, pos.method_10093(direction)).method_26215()) continue;
                return false;
            }
        }
        return true;
    }

    private boolean isWithinChunkMargin(class_2791 chunk, int x, int z, int margin) {
        class_1923 chunkPos = chunk.method_12004();
        return x >= chunkPos.method_8326() + margin && x <= chunkPos.method_8327() - margin && z >= chunkPos.method_8328() + margin && z <= chunkPos.method_8329() - margin;
    }

    private class_2680 getDecorationBlockState(class_2791 chunk, class_2338 pos) {
        if (pos.method_10264() < -64 || pos.method_10264() > 319 || !this.isWithinChunkMargin(chunk, pos.method_10263(), pos.method_10260(), 0)) {
            return AIR;
        }
        return chunk.method_8320(pos);
    }

    private void setDecorationBlockState(class_2791 chunk, class_2338 pos, class_2680 state) {
        if (pos.method_10264() < -64 || pos.method_10264() > 319 || !this.isWithinChunkMargin(chunk, pos.method_10263(), pos.method_10260(), 0)) {
            return;
        }
        chunk.method_12010(pos, state, false);
    }

    private void placeNorthernSnow(class_2791 chunk, int worldX, int worldZ, int y, ColumnProfile column) {
        int snowLayers;
        class_2338 basePos = new class_2338(worldX, y, worldZ);
        if (!chunk.method_8320(basePos).method_26215()) {
            return;
        }
        double driftNoise = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX + 47, worldZ - 29, 11.0);
        double basinNoise = MapChunkGenerator.normalizedSample(BROAD_NOISE, worldX - 133, worldZ + 211, 190.0);
        double ridgeNoise = MapChunkGenerator.normalizedSample(RIDGE_NOISE, worldX, worldZ, 160.0);
        double snowFieldNoise = MapChunkGenerator.normalizedSample(BROAD_NOISE, worldX + 89, worldZ - 157, 78.0);
        double snowPatchNoise = MapChunkGenerator.normalizedSample(DECORATION_NOISE, worldX - 173, worldZ + 61, 36.0);
        double snowDetailNoise = MapChunkGenerator.normalizedSample(DETAIL_NOISE, worldX + 113, worldZ - 87, 18.0);
        double snowLayerShape = snowFieldNoise * 0.5 + snowPatchNoise * 0.3 + snowDetailNoise * 0.2;
        int snowBlocks = 0;
        switch (column.style.ordinal()) {
            case 6: {
                snowLayers = 2 + this.northSnowPatchLayers(snowLayerShape, 0.46, 0.58, 0.7, 0.82) + (driftNoise > 0.72 ? 1 : 0) + (basinNoise > 0.84 ? 1 : 0);
                if (!(snowFieldNoise > 0.86) || !(basinNoise > 0.9) || !(ridgeNoise < 0.42)) break;
                snowBlocks = 1;
                break;
            }
            case 7: {
                snowLayers = 2 + this.northSnowPatchLayers(snowLayerShape, 0.48, 0.61, 0.73, 0.84) + (driftNoise > 0.76 ? 1 : 0);
                if (!(snowFieldNoise > 0.88) || !(basinNoise > 0.92) || !(ridgeNoise < 0.36)) break;
                snowBlocks = 1;
                break;
            }
            case 9: {
                snowLayers = 2 + this.northSnowPatchLayers(snowLayerShape, 0.44, 0.56, 0.68, 0.8) + (driftNoise > 0.7 ? 1 : 0) + (basinNoise > 0.84 ? 1 : 0);
                if (!(snowFieldNoise > 0.84) || !(basinNoise > 0.88) || !(ridgeNoise < 0.4)) break;
                snowBlocks = 1;
                break;
            }
            case 8: {
                snowLayers = 1 + this.northSnowPatchLayers(snowLayerShape, 0.52, 0.66, 0.78, 0.88) + (driftNoise > 0.8 ? 1 : 0);
                break;
            }
            case 3: {
                snowLayers = 4 + (driftNoise > 0.55 ? 2 : 0) + (basinNoise > 0.7 ? 1 : 0);
                if (!(basinNoise > 0.72) || !(ridgeNoise < 0.42)) break;
                snowBlocks = 1 + (basinNoise > 0.9 ? 1 : 0);
                break;
            }
            case 4: {
                snowLayers = 3 + (driftNoise > 0.62 ? 2 : 0) + (basinNoise > 0.82 ? 1 : 0);
                if (!(basinNoise > 0.78) || !(ridgeNoise < 0.38)) break;
                snowBlocks = 1;
                break;
            }
            case 5: {
                snowLayers = 2 + (driftNoise > 0.7 ? 1 : 0);
                break;
            }
            default: {
                snowLayers = 1;
            }
        }
        snowLayers = class_3532.method_15340((int)snowLayers, (int)1, (int)8);
        for (int i = 0; i < snowBlocks && y + i <= 319; ++i) {
            chunk.method_12010(new class_2338(worldX, y + i, worldZ), SNOW, false);
        }
        int layerY = y + snowBlocks;
        if (layerY <= 319) {
            chunk.method_12010(new class_2338(worldX, layerY, worldZ), (class_2680)class_2246.field_10477.method_9564().method_11657((class_2769)class_2488.field_11518, (Comparable)Integer.valueOf(snowLayers)), false);
        }
    }

    private int northSnowPatchLayers(double layerShape, double threshold1, double threshold2, double threshold3, double threshold4) {
        int extraLayers = 0;
        if (layerShape > threshold1) {
            ++extraLayers;
        }
        if (layerShape > threshold2) {
            ++extraLayers;
        }
        if (layerShape > threshold3) {
            ++extraLayers;
        }
        if (layerShape > threshold4) {
            ++extraLayers;
        }
        return extraLayers;
    }

    private class_2680 flowerForStyle(BiomeStyle style, int worldX, int worldZ) {
        double value = MapChunkGenerator.normalizedSample(TREE_NOISE, worldX + 91, worldZ - 37, 21.0);
        if (style == BiomeStyle.THE_REACH || style == BiomeStyle.THE_REACH_FOREST || style == BiomeStyle.THE_REACH_ORCHARD) {
            if (value > 0.8) {
                return class_2246.field_10315.method_9564();
            }
            if (value > 0.6) {
                return class_2246.field_10156.method_9564();
            }
            if (value > 0.4) {
                return class_2246.field_10182.method_9564();
            }
            return class_2246.field_10554.method_9564();
        }
        if (style == BiomeStyle.THE_VALE || style == BiomeStyle.THE_VALE_FOREST || style == BiomeStyle.THE_VALE_VALLEY || style == BiomeStyle.THE_VALE_MEADOW || style == BiomeStyle.BRAAVOS || style == BiomeStyle.BRAAVOS_FOREST) {
            if (value > 0.78) {
                return class_2246.field_10573.method_9564();
            }
            if (value > 0.56) {
                return class_2246.field_10554.method_9564();
            }
            if (value > 0.34) {
                return class_2246.field_10226.method_9564();
            }
            return class_2246.field_10156.method_9564();
        }
        if (style == BiomeStyle.THE_WESTERLANDS || style == BiomeStyle.THE_WESTERLANDS_FOREST || style == BiomeStyle.THE_WESTERLANDS_GOLDGRASS || style == BiomeStyle.THE_CROWNLANDS || style == BiomeStyle.THE_CROWNLANDS_FOREST || style == BiomeStyle.THE_CROWNLANDS_KINGSWOOD) {
            if (value > 0.72) {
                return class_2246.field_10449.method_9564();
            }
            if (value > 0.5) {
                return class_2246.field_10182.method_9564();
            }
            return class_2246.field_10554.method_9564();
        }
        if (style == BiomeStyle.VOLANTIS_FOREST) {
            if (value > 0.7) {
                return class_2246.field_10048.method_9564();
            }
            if (value > 0.45) {
                return class_2246.field_10270.method_9564();
            }
            return class_2246.field_10182.method_9564();
        }
        if (value > 0.8) {
            return class_2246.field_9995.method_9564();
        }
        if (value > 0.6) {
            return class_2246.field_10554.method_9564();
        }
        if (value > 0.45) {
            return class_2246.field_10226.method_9564();
        }
        if (value > 0.3) {
            return class_2246.field_10573.method_9564();
        }
        return class_2246.field_10449.method_9564();
    }

    private class_6880<class_1959> getBiomeAt(int worldX, int worldZ) {
        return this.getBiomeAt(worldX, worldZ, null);
    }

    private class_6880<class_1959> getBiomeAt(int worldX, int worldZ, GenerationCache cache) {
        if (cache == null) {
            return this.field_12761.method_38109(worldX >> 2, 0, worldZ >> 2, MultiNoiseSamplerHolder.INSTANCE);
        }
        return (class_6880)cache.biomes.computeIfAbsent(MapChunkGenerator.columnKey(worldX >> 2, worldZ >> 2), key -> this.field_12761.method_38109(worldX >> 2, 0, worldZ >> 2, MultiNoiseSamplerHolder.INSTANCE));
    }

    private BiomeStyle getStyleAt(int worldX, int worldZ, GenerationCache cache) {
        if (cache == null) {
            return MapChunkGenerator.getStyle(this.getBiomeAt(worldX, worldZ, null));
        }
        return (BiomeStyle)((Object)cache.styles.computeIfAbsent(MapChunkGenerator.columnKey(worldX >> 2, worldZ >> 2), key -> cache.styleFor(this.getBiomeAt(worldX, worldZ, cache))));
    }

    private MapBiomeSource mapBiomeSource() {
        MapBiomeSource mapBiomeSource;
        class_1966 class_19662 = this.field_12761;
        return class_19662 instanceof MapBiomeSource ? (mapBiomeSource = (MapBiomeSource)class_19662) : null;
    }

    private TerrainBlend blendTerrain(int worldX, int worldZ) {
        return this.blendTerrain(worldX, worldZ, null);
    }

    private TerrainBlend blendTerrain(int worldX, int worldZ, GenerationCache cache) {
        TerrainBlend cached;
        if (cache != null && (cached = (TerrainBlend)cache.terrainBlends.get(MapChunkGenerator.columnKey(worldX, worldZ))) != null) {
            return cached;
        }
        double weightedBase = 0.0;
        double weightedBroad = 0.0;
        double weightedRidge = 0.0;
        double weightedDetail = 0.0;
        double weightedCoastPenalty = 0.0;
        double totalWeight = 0.0;
        for (int dx = -96; dx <= 96; dx += 96) {
            for (int dz = -96; dz <= 96; dz += 96) {
                double distance = Math.sqrt(dx * dx + dz * dz);
                double weight = distance == 0.0 ? 1.8 : 1.0 / (distance / 96.0 + 0.35);
                TerrainSettings settings = MapChunkGenerator.terrainSettings(this.getStyleAt(worldX + dx, worldZ + dz, cache));
                weightedBase += (double)settings.baseHeight * weight;
                weightedBroad += (double)settings.broadAmplitude * weight;
                weightedRidge += (double)settings.ridgeAmplitude * weight;
                weightedDetail += (double)settings.detailAmplitude * weight;
                weightedCoastPenalty += (double)settings.coastPenalty * weight;
                totalWeight += weight;
            }
        }
        TerrainBlend blend = new TerrainBlend(weightedBase / totalWeight, weightedBroad / totalWeight, weightedRidge / totalWeight, weightedDetail / totalWeight, weightedCoastPenalty / totalWeight);
        if (cache != null) {
            cache.terrainBlends.put(MapChunkGenerator.columnKey(worldX, worldZ), (Object)blend);
        }
        return blend;
    }

    private int sampleSmoothedTop(int worldX, int worldZ) {
        return this.sampleSmoothedTop(worldX, worldZ, null);
    }

    private int sampleSmoothedTop(int worldX, int worldZ, GenerationCache cache) {
        double weight;
        int cached;
        if (cache != null && (cached = cache.smoothedTops.get(MapChunkGenerator.columnKey(worldX, worldZ))) != Integer.MIN_VALUE) {
            return cached;
        }
        double sum = 0.0;
        double weightSum = 0.0;
        for (int dx = -24; dx <= 24; dx += 12) {
            for (int dz = -24; dz <= 24; dz += 12) {
                double distance = Math.abs(dx) + Math.abs(dz);
                weight = switch ((int)distance) {
                    case 0 -> 10.0;
                    case 12 -> 6.0;
                    case 24 -> 3.5;
                    case 36 -> 2.0;
                    default -> 1.0;
                };
                sum += (double)this.computeRawTop(worldX + dx, worldZ + dz, cache) * weight;
                weightSum += weight;
            }
        }
        double firstPass = sum / weightSum;
        sum = firstPass * 8.0;
        weightSum = 8.0;
        for (int dx = -6; dx <= 6; dx += 6) {
            for (int dz = -6; dz <= 6; dz += 6) {
                if (dx == 0 && dz == 0) continue;
                weight = dx == 0 || dz == 0 ? 2.5 : 1.25;
                sum += (double)this.computeRawTop(worldX + dx, worldZ + dz, cache) * weight;
                weightSum += weight;
            }
        }
        double secondPass = sum / weightSum;
        sum = secondPass * 10.0;
        weightSum = 10.0;
        for (int dx = -8; dx <= 8; dx += 8) {
            for (int dz = -8; dz <= 8; dz += 8) {
                if (dx == 0 && dz == 0) continue;
                double weight2 = dx == 0 || dz == 0 ? 2.0 : 1.0;
                sum += (double)this.computeRawTop(worldX + dx, worldZ + dz, cache) * weight2;
                weightSum += weight2;
            }
        }
        int smoothed = MapChunkGenerator.clampY((int)Math.round(sum / weightSum));
        if (cache != null) {
            cache.smoothedTops.put(MapChunkGenerator.columnKey(worldX, worldZ), smoothed);
        }
        return smoothed;
    }

    private int computeRawTop(int worldX, int worldZ) {
        return this.computeRawTop(worldX, worldZ, null);
    }

    private int computeRawTop(int worldX, int worldZ, GenerationCache cache) {
        int rawTop;
        int cached;
        if (cache != null && (cached = cache.rawTops.get(MapChunkGenerator.columnKey(worldX, worldZ))) != Integer.MIN_VALUE) {
            return cached;
        }
        BiomeStyle style = this.getStyleAt(worldX, worldZ, cache);
        if (style == BiomeStyle.WALL) {
            double broad = MapChunkGenerator.sample(BROAD_NOISE, worldX, worldZ, 720.0);
            double ridges = Math.pow(Math.abs(MapChunkGenerator.sample(RIDGE_NOISE, worldX, worldZ, 180.0)), 1.35);
            double detail = MapChunkGenerator.sample(DETAIL_NOISE, worldX, worldZ, 74.0);
            rawTop = MapChunkGenerator.clampY(118 + (int)Math.round(broad * 10.0 + ridges * 34.0 + detail * 8.0));
        } else {
            TerrainBlend blend = this.blendTerrain(worldX, worldZ, cache);
            double broad = MapChunkGenerator.sample(BROAD_NOISE, worldX, worldZ, 720.0);
            double ridges = Math.pow(Math.abs(MapChunkGenerator.sample(RIDGE_NOISE, worldX, worldZ, 180.0)), 1.35);
            double detail = MapChunkGenerator.sample(DETAIL_NOISE, worldX, worldZ, 74.0);
            double coastalness = this.coastalness(worldX, worldZ, cache);
            double micro = MapChunkGenerator.sample(MICRO_NOISE, worldX, worldZ, 28.0);
            double slopeWarp = MapChunkGenerator.sample(DETAIL_NOISE, worldX - 413, worldZ + 229, 160.0);
            double valleyDepth = MapChunkGenerator.normalizedSample(BROAD_NOISE, worldX + 177, worldZ - 301, 260.0);
            double detailAdjusted = MapChunkGenerator.isDragonstoneStyle(style) ? detail + micro * 0.7 : detail;
            double hillLift = ridges * ridges * blend.ridgeAmplitude * 0.45 + Math.max(0.0, slopeWarp) * blend.detailAmplitude * 0.35;
            double valleyCut = 0.0;
            if (style == BiomeStyle.THE_VALE_VALLEY) {
                valleyCut = 10.0 + valleyDepth * 8.0;
            } else if (MapChunkGenerator.isOasisStyle(style)) {
                valleyCut = 5.0 + valleyDepth * 4.0;
            } else if (style == BiomeStyle.THE_VALE_FOOTHILL) {
                valleyCut = valleyDepth * 2.5;
            }
            rawTop = MapChunkGenerator.clampY((int)Math.round(blend.baseHeight + broad * blend.broadAmplitude + ridges * blend.ridgeAmplitude + detailAdjusted * blend.detailAmplitude + hillLift - valleyCut - coastalness * blend.coastPenalty));
        }
        if (cache != null) {
            cache.rawTops.put(MapChunkGenerator.columnKey(worldX, worldZ), rawTop);
        }
        return rawTop;
    }

    private double immediateSeaAdjacency(int worldX, int worldZ) {
        return this.immediateSeaAdjacency(worldX, worldZ, null);
    }

    private double immediateSeaAdjacency(int worldX, int worldZ, GenerationCache cache) {
        double adjacency;
        int[] offsets;
        double cached;
        if (cache != null && !Double.isNaN(cached = cache.immediateSeaAdjacency.get(MapChunkGenerator.columnKey(worldX, worldZ)))) {
            return cached;
        }
        MapBiomeSource mapBiomeSource = this.mapBiomeSource();
        if (mapBiomeSource != null) {
            double adjacency2 = mapBiomeSource.sampleSeaInfluenceAtBlock(worldX, worldZ, 28);
            if (cache != null) {
                cache.immediateSeaAdjacency.put(MapChunkGenerator.columnKey(worldX, worldZ), adjacency2);
            }
            return adjacency2;
        }
        int seaHits = 0;
        int checks = 0;
        for (int dx : offsets = new int[]{-16, 0, 16}) {
            for (int dz : offsets) {
                if (dx == 0 && dz == 0) continue;
                ++checks;
                if (this.getStyleAt(worldX + dx, worldZ + dz, cache) != BiomeStyle.SEA) continue;
                ++seaHits;
            }
        }
        double d = adjacency = checks == 0 ? 0.0 : (double)seaHits / (double)checks;
        if (cache != null) {
            cache.immediateSeaAdjacency.put(MapChunkGenerator.columnKey(worldX, worldZ), adjacency);
        }
        return adjacency;
    }

    private double coastalness(int worldX, int worldZ) {
        return this.coastalness(worldX, worldZ, null);
    }

    private double coastalness(int worldX, int worldZ, GenerationCache cache) {
        double coastalness;
        double cached;
        if (cache != null && !Double.isNaN(cached = cache.coastalness.get(MapChunkGenerator.columnKey(worldX, worldZ)))) {
            return cached;
        }
        MapBiomeSource mapBiomeSource = this.mapBiomeSource();
        if (mapBiomeSource != null) {
            double coastalness2 = mapBiomeSource.sampleSeaInfluenceAtBlock(worldX, worldZ, 92);
            if (cache != null) {
                cache.coastalness.put(MapChunkGenerator.columnKey(worldX, worldZ), coastalness2);
            }
            return coastalness2;
        }
        double seaWeight = 0.0;
        double totalWeight = 0.0;
        for (int dx = -48; dx <= 48; dx += 16) {
            for (int dz = -48; dz <= 48; dz += 16) {
                int distance;
                if (dx == 0 && dz == 0 || (distance = Math.abs(dx) + Math.abs(dz)) > 64) continue;
                double weight = 1.0 / (1.0 + (double)distance / 16.0);
                totalWeight += weight;
                if (this.getStyleAt(worldX + dx, worldZ + dz, cache) != BiomeStyle.SEA) continue;
                seaWeight += weight;
            }
        }
        double d = coastalness = totalWeight <= 0.0 ? 0.0 : seaWeight / totalWeight;
        if (cache != null) {
            cache.coastalness.put(MapChunkGenerator.columnKey(worldX, worldZ), coastalness);
        }
        return coastalness;
    }

    private static long columnKey(int worldX, int worldZ) {
        return (long)worldX << 32 ^ (long)worldZ & 0xFFFFFFFFL;
    }

    private int applyCoastalShelf(int worldX, int worldZ, BiomeStyle style, int top, double coastalness, double ridges, double micro, GenerationCache cache) {
        if (style == BiomeStyle.SEA || style == BiomeStyle.WALL || MapChunkGenerator.isOasisStyle(style)) {
            return top;
        }
        double immediateSea = this.immediateSeaAdjacency(worldX, worldZ, cache);
        double nearestSea = this.nearestSeaDistance(worldX, worldZ, cache);
        double shorelineProximity = Double.isFinite(nearestSea) ? 1.0 - class_3532.method_15350((double)((nearestSea - 8.0) / 72.0), (double)0.0, (double)1.0) : 0.0;
        double shelfStrength = Math.max(Math.max(immediateSea * 1.1, coastalness), shorelineProximity);
        if (shelfStrength < 0.1) {
            return top;
        }
        int shorelineTop = this.applyShorelineSlope(worldX, worldZ, style, top, ridges, micro, cache);
        int coastalCap = Math.min(this.coastalSurfaceCap(style, ridges, immediateSea), shorelineTop);
        double clampProgress = class_3532.method_15350((double)((shelfStrength - 0.08) / 0.74), (double)0.0, (double)1.0);
        int clamped = (int)Math.round(class_3532.method_16436((double)clampProgress, (double)top, (double)coastalCap));
        if (shorelineTop < top) {
            clamped = Math.min(clamped, shorelineTop);
        }
        if (this.hasDirectSeaBorder(worldX, worldZ, cache)) {
            int minShoreDepth = 63;
            int withVariation = MapChunkGenerator.clampY(Math.min(clamped, shorelineTop));
            return Math.max(minShoreDepth, Math.min(top, withVariation));
        }
        return Math.max(63, Math.min(top, clamped));
    }

    private int applyShorelineSlope(int worldX, int worldZ, BiomeStyle style, int top, double ridges, double micro, GenerationCache cache) {
        double nearestSea = this.nearestSeaDistance(worldX, worldZ, cache);
        if (!Double.isFinite(nearestSea)) {
            return top;
        }
        double shorelineNoise = MapChunkGenerator.normalizedSample(DETAIL_NOISE, worldX + 149, worldZ - 83, 18.0);
        double shorelineWidth = 48.0 + shorelineNoise * 52.0 + Math.max(0.0, 0.55 - ridges) * 22.0 + this.immediateSeaAdjacency(worldX, worldZ, cache) * 26.0;
        if (nearestSea > shorelineWidth) {
            return top;
        }
        int target = 64;
        if (style == BiomeStyle.THE_NORTH_HILLS || style == BiomeStyle.THE_VALE_HILLS || style == BiomeStyle.THE_WESTERLANDS_HILLS || style == BiomeStyle.THE_STORMLANDS_HILLS || style == BiomeStyle.THE_REACH_HILLS || style == BiomeStyle.THE_RIVERLANDS_HILLS || style == BiomeStyle.IRON_ISLANDS || style == BiomeStyle.IRON_ISLANDS_HILLS || style == BiomeStyle.DRAGONSTONE || style == BiomeStyle.DRAGONSTONE_HILLS || style == BiomeStyle.STEP_STONES || style == BiomeStyle.STEP_STONES_HILLS) {
            ++target;
        }
        if (ridges > 0.72) {
            ++target;
        }
        if (shorelineNoise > 0.72) {
            ++target;
        }
        if (shorelineNoise < 0.2 && micro < 0.4) {
            --target;
        }
        target = class_3532.method_15340((int)target, (int)63, (int)67);
        double progress = 1.0 - class_3532.method_15350((double)((nearestSea - 2.0) / shorelineWidth), (double)0.0, (double)1.0);
        progress = MapChunkGenerator.smoothstep(progress);
        int shorelineTop = Math.round(class_3532.method_48781((float)((float)progress), (int)top, (int)target));
        return MapChunkGenerator.clampY(Math.min(top, shorelineTop));
    }

    private int coastalSurfaceCap(BiomeStyle style, double ridges, double immediateSea) {
        int base = 64;
        switch (style.ordinal()) {
            case 8: 
            case 12: 
            case 18: 
            case 22: 
            case 30: 
            case 34: 
            case 49: 
            case 60: {
                base = 65;
                break;
            }
            case 24: 
            case 25: 
            case 26: 
            case 40: 
            case 41: 
            case 42: 
            case 53: 
            case 54: {
                base = 66;
                break;
            }
        }
        if (ridges > 0.72 && immediateSea < 0.75) {
            ++base;
        }
        return base;
    }

    private boolean hasDirectSeaBorder(int worldX, int worldZ) {
        return this.hasDirectSeaBorder(worldX, worldZ, null);
    }

    private boolean hasDirectSeaBorder(int worldX, int worldZ, GenerationCache cache) {
        return this.nearestSeaDistance(worldX, worldZ, cache) <= 18.0;
    }

    private double nearestLandDistance(int worldX, int worldZ, GenerationCache cache) {
        double cached;
        if (cache != null && !Double.isNaN(cached = cache.nearestLandDistances.get(MapChunkGenerator.columnKey(worldX, worldZ)))) {
            return cached;
        }
        MapBiomeSource mapBiomeSource = this.mapBiomeSource();
        if (mapBiomeSource != null) {
            double nearestDistance = mapBiomeSource.distanceToLandAtBlock(worldX, worldZ, 144);
            if (cache != null) {
                cache.nearestLandDistances.put(MapChunkGenerator.columnKey(worldX, worldZ), nearestDistance);
            }
            return nearestDistance;
        }
        double nearestDistance = Double.POSITIVE_INFINITY;
        for (int dx = -48; dx <= 48; dx += 8) {
            for (int dz = -48; dz <= 48; dz += 8) {
                double distance;
                if (dx == 0 && dz == 0 || (distance = Math.sqrt(dx * dx + dz * dz)) > 52.0 || this.getStyleAt(worldX + dx, worldZ + dz, cache) == BiomeStyle.SEA) continue;
                double jitter = MapChunkGenerator.normalizedSample(MICRO_NOISE, worldX + dx - 193, worldZ + dz + 71, 18.0);
                double effectiveDistance = Math.max(0.0, distance - (jitter - 0.5) * 6.0);
                nearestDistance = Math.min(nearestDistance, effectiveDistance);
            }
        }
        if (cache != null) {
            cache.nearestLandDistances.put(MapChunkGenerator.columnKey(worldX, worldZ), nearestDistance);
        }
        return nearestDistance;
    }

    private int seaFloorHeight(int worldX, int worldZ, GenerationCache cache) {
        double distanceToLand = this.nearestLandDistance(worldX, worldZ, cache);
        double coastalSeaInfluence = 1.0 - class_3532.method_15350((double)((distanceToLand - 4.0) / 96.0), (double)0.0, (double)1.0);
        double broad = MapChunkGenerator.normalizedSample(BROAD_NOISE, worldX + 137, worldZ - 283, 260.0);
        double ridges = Math.pow(Math.abs(MapChunkGenerator.sample(RIDGE_NOISE, worldX, worldZ, 180.0)), 1.2);
        double detail = MapChunkGenerator.normalizedSample(DETAIL_NOISE, worldX - 61, worldZ + 97, 54.0);
        int deepFloor = 53 - Math.round((float)(broad * 7.0 + ridges * 4.0 + detail * 2.0));
        int shoreFloor = 62 - Math.round((float)((1.0 - detail) * 2.0));
        if (detail > 0.72) {
            ++shoreFloor;
        }
        if (detail < 0.18) {
            --shoreFloor;
        }
        int floor = Math.round(class_3532.method_48781((float)((float)MapChunkGenerator.smoothstep(coastalSeaInfluence)), (int)deepFloor, (int)shoreFloor));
        return MapChunkGenerator.clampY(Math.min(62, Math.max(45, floor)));
    }

    private class_2680 seaFloorSurface(int worldX, int worldZ, GenerationCache cache) {
        double distanceToLand = this.nearestLandDistance(worldX, worldZ, cache);
        double shoreFactor = 1.0 - class_3532.method_15350((double)((distanceToLand - 3.0) / 72.0), (double)0.0, (double)1.0);
        double textureNoise = MapChunkGenerator.normalizedSample(MICRO_NOISE, worldX + 71, worldZ - 149, 18.0);
        if (shoreFactor > 0.66) {
            if (textureNoise > 0.8) {
                return CLAY;
            }
            return textureNoise > 0.42 ? SAND : GRAVEL;
        }
        if (shoreFactor > 0.28) {
            return textureNoise > 0.58 ? GRAVEL : SAND;
        }
        return textureNoise > 0.62 ? GRAVEL : STONE;
    }

    private double nearestSeaDistance(int worldX, int worldZ, GenerationCache cache) {
        double cached;
        if (cache != null && !Double.isNaN(cached = cache.nearestSeaDistances.get(MapChunkGenerator.columnKey(worldX, worldZ)))) {
            return cached;
        }
        MapBiomeSource mapBiomeSource = this.mapBiomeSource();
        if (mapBiomeSource != null) {
            double nearestDistance = mapBiomeSource.distanceToSeaAtBlock(worldX, worldZ, 144);
            if (cache != null) {
                cache.nearestSeaDistances.put(MapChunkGenerator.columnKey(worldX, worldZ), nearestDistance);
            }
            return nearestDistance;
        }
        double nearestDistance = Double.POSITIVE_INFINITY;
        for (int dx = -48; dx <= 48; dx += 8) {
            for (int dz = -48; dz <= 48; dz += 8) {
                double distance;
                if (dx == 0 && dz == 0 || (distance = Math.sqrt(dx * dx + dz * dz)) > 52.0 || this.getStyleAt(worldX + dx, worldZ + dz, cache) != BiomeStyle.SEA) continue;
                double jitter = MapChunkGenerator.normalizedSample(MICRO_NOISE, worldX + dx + 113, worldZ + dz - 257, 18.0);
                double effectiveDistance = Math.max(0.0, distance - (jitter - 0.5) * 6.0);
                nearestDistance = Math.min(nearestDistance, effectiveDistance);
            }
        }
        if (cache != null) {
            cache.nearestSeaDistances.put(MapChunkGenerator.columnKey(worldX, worldZ), nearestDistance);
        }
        return nearestDistance;
    }

    private int limitSurfaceStepDelta(int worldX, int worldZ, BiomeStyle style, int top, GenerationCache cache) {
        double localCoastalness = this.coastalness(worldX, worldZ, cache);
        double localNearestSea = this.nearestSeaDistance(worldX, worldZ, cache);
        boolean coastalColumn = localCoastalness > 0.08 || localNearestSea < 104.0;
        int maxStepDelta = coastalColumn ? 4 : 1;
        int minAllowed = Integer.MIN_VALUE;
        int maxAllowed = Integer.MAX_VALUE;
        for (int[] offset : CARDINAL_FOUR_BLOCK_OFFSETS) {
            int neighborTop;
            int neighborX = worldX + offset[0];
            int neighborZ = worldZ + offset[1];
            BiomeStyle neighborStyle = this.getStyleAt(neighborX, neighborZ, cache);
            if (neighborStyle == BiomeStyle.SEA) {
                if (coastalColumn) continue;
                neighborTop = 64;
            } else {
                if (neighborStyle == BiomeStyle.WALL) continue;
                double neighborCoastalness = this.coastalness(neighborX, neighborZ, cache);
                double neighborRidges = Math.pow(Math.abs(MapChunkGenerator.sample(RIDGE_NOISE, neighborX, neighborZ, 180.0)), 1.35);
                neighborTop = this.applyCoastalShelf(neighborX, neighborZ, neighborStyle, this.sampleSmoothedTop(neighborX, neighborZ, cache), neighborCoastalness, neighborRidges, MapChunkGenerator.sample(MICRO_NOISE, neighborX, neighborZ, 28.0), cache);
            }
            minAllowed = Math.max(minAllowed, neighborTop - maxStepDelta);
            maxAllowed = Math.min(maxAllowed, neighborTop + maxStepDelta);
        }
        if (minAllowed > maxAllowed) {
            int target = Math.max(63, (minAllowed + maxAllowed) / 2);
            return MapChunkGenerator.clampY(target);
        }
        return MapChunkGenerator.clampY(class_3532.method_15340((int)top, (int)minAllowed, (int)maxAllowed));
    }

    private CaveColumnContext createCaveColumnContext(int worldX, int worldZ, ColumnProfile column) {
        if (column.style == BiomeStyle.SEA || column.style == BiomeStyle.WALL) {
            return null;
        }
        int caveRoof = column.surfaceY - 7;
        int caveFloor = -54;
        if (caveRoof < caveFloor) {
            return null;
        }
        double styleBias = this.caveStyleBias(column.style);
        double caveProvince = MapChunkGenerator.normalizedSample(CAVE_NOISE_A, worldX + 733, worldZ - 911, 310.0);
        if (caveProvince < this.caveProvinceThreshold(styleBias)) {
            return null;
        }
        return new CaveColumnContext(caveFloor, caveRoof, caveProvince, styleBias, Math.floorDiv(worldX, 96), Math.floorDiv(worldZ, 96), Math.floorDiv(worldX, 192), Math.floorDiv(worldZ, 192));
    }

    private boolean shouldCarveCave(int worldX, int y, int worldZ, CaveColumnContext context) {
        if (context == null || y < context.caveFloor() || y > context.caveRoof()) {
            return false;
        }
        return this.caveFieldCarves(worldX, y, worldZ, context) || this.caveRoomCarves(worldX, y, worldZ, context) || this.caveShaftCarves(worldX, y, worldZ, context) || this.cavePocketCarves(worldX, y, worldZ, context);
    }

    private class_2680 caveFluid(int worldX, int y, int worldZ, ColumnProfile column) {
        double lavaNoise;
        double fillNoise;
        if ((column.style == BiomeStyle.SEA || MapChunkGenerator.isOasisStyle(column.style)) && y <= 61 && (fillNoise = MapChunkGenerator.normalizedSample(CAVE_NOISE_B, worldX + 31, worldZ - 31 + y * 3, 26.0)) > 0.74) {
            return WATER;
        }
        if (y <= -48 && (lavaNoise = MapChunkGenerator.normalizedSample(CAVE_NOISE_D, worldX + 511, worldZ - 271 + y * 3, 34.0)) > 0.22) {
            return LAVA;
        }
        return AIR;
    }

    private boolean caveFieldCarves(int worldX, int y, int worldZ, CaveColumnContext context) {
        double relativeDepth = (double)(y - context.caveFloor()) / (double)Math.max(1, context.caveRoof() - context.caveFloor());
        double midBand = class_3532.method_15350((double)(1.0 - Math.abs(relativeDepth - 0.48) * 1.55), (double)0.0, (double)1.0);
        double lowBand = class_3532.method_15350((double)(1.0 - Math.abs(relativeDepth - 0.68) * 1.8), (double)0.0, (double)1.0);
        double upperBand = class_3532.method_15350((double)(1.0 - Math.abs(relativeDepth - 0.28) * 2.0), (double)0.0, (double)1.0);
        return this.spaghettiCaveCarves(worldX, y, worldZ, midBand, lowBand, upperBand, context.styleBias(), context.caveProvince());
    }

    private boolean spaghettiCaveCarves(int worldX, int y, int worldZ, double midBand, double lowBand, double upperBand, double styleBias, double caveProvince) {
        double roughness;
        double cutoff;
        double familyC;
        double familyB;
        double tunnelSize = 0.028 + midBand * 0.018 + lowBand * 0.012 + Math.max(0.0, styleBias) * 0.004 + (caveProvince - 0.5) * 0.006;
        double familyA = Math.min(Math.abs(MapChunkGenerator.sample3d(CAVE_NOISE_A, worldX + 31, y * 2 - 700, worldZ - 97, 92.0, 38.0)), Math.abs(MapChunkGenerator.sample3d(CAVE_NOISE_B, worldX - 401, y * 2 + 113, worldZ + 233, 92.0, 42.0)));
        double intersection = Math.min(familyA, Math.min(familyB = Math.min(Math.abs(MapChunkGenerator.sample3d(CAVE_NOISE_C, worldZ + 283, y * 2 - 173, worldX - 587, 86.0, 38.0)), Math.abs(MapChunkGenerator.sample3d(CAVE_NOISE_D, worldZ - 191, y * 2 + 509, worldX + 337, 118.0, 48.0))), familyC = Math.min(Math.abs(MapChunkGenerator.sample3d(CAVE_NOISE_A, worldX + worldZ / 2 + 719, y * 2 + 313, worldZ - worldX / 3 - 421, 104.0, 44.0)), Math.abs(MapChunkGenerator.sample3d(CAVE_NOISE_C, worldX - worldZ / 2 - 149, y * 2 - 211, worldZ + worldX / 3 + 641, 112.0, 46.0)))));
        if (intersection < (cutoff = tunnelSize * (0.78 + (roughness = MapChunkGenerator.normalizedSample3d(CAVE_NOISE_D, worldX + 17, y * 3 - 29, worldZ - 41, 26.0, 18.0)) * 0.28))) {
            return true;
        }
        double upperCutoff = 0.022 + upperBand * 0.018 + Math.max(0.0, styleBias) * 0.003;
        return upperBand > 0.18 && Math.min(familyA, familyB) < upperCutoff && MapChunkGenerator.normalizedSample3d(CAVE_NOISE_B, worldX - 83, y * 4 + 157, worldZ + 71, 44.0, 22.0) > 0.58;
    }

    private boolean caveRoomCarves(int worldX, int y, int worldZ, CaveColumnContext context) {
        int cellX = context.roomCellX();
        int cellZ = context.roomCellZ();
        double styleBias = context.styleBias();
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dz = -1; dz <= 1; ++dz) {
                double roughness;
                double radiusZ;
                double z;
                double radiusY;
                double yy;
                int cx = cellX + dx;
                int cz = cellZ + dz;
                long seed = MapChunkGenerator.cellSeed(cx, cz, 2976664222L);
                if (MapChunkGenerator.caveUnit(seed) < 0.74) continue;
                double centerX = this.caveNodeX(cx, cz);
                double centerY = class_3532.method_15350((double)(this.caveNodeY(cx, cz) + (MapChunkGenerator.caveUnit(seed + 41L) - 0.5) * 22.0), (double)((double)context.caveFloor() + 8.0), (double)Math.min((double)context.caveRoof() - 5.0, 74.0));
                double centerZ = this.caveNodeZ(cx, cz);
                double radiusX = 6.0 + MapChunkGenerator.caveUnit(seed + 11L) * 10.0 + Math.max(0.0, styleBias) * 1.2;
                double x = ((double)worldX + 0.5 - centerX) / radiusX;
                if (!(x * x + (yy = ((double)y + 0.5 - centerY) / (radiusY = 3.5 + MapChunkGenerator.caveUnit(seed + 23L) * 4.5 + Math.max(0.0, styleBias) * 0.45)) * yy + (z = ((double)worldZ + 0.5 - centerZ) / (radiusZ = 6.0 + MapChunkGenerator.caveUnit(seed + 31L) * 10.0 + Math.max(0.0, styleBias) * 1.2)) * z < 0.58 + (roughness = MapChunkGenerator.normalizedSample3d(CAVE_NOISE_D, worldX + 101, y * 3 - 197, worldZ - 73, 24.0, 18.0)) * 0.22)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean caveShaftCarves(int worldX, int y, int worldZ, CaveColumnContext context) {
        int cellX = context.shaftCellX();
        int cellZ = context.shaftCellZ();
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dz = -1; dz <= 1; ++dz) {
                double roughness;
                double radius;
                double warpedRadius;
                double centerZ;
                double dzPos;
                double centerX;
                double dxPos;
                int sxCell = cellX + dx;
                int szCell = cellZ + dz;
                long seed = MapChunkGenerator.cellSeed(sxCell, szCell, 369911L);
                if (MapChunkGenerator.caveUnit(seed) < 0.88 || (dxPos = (double)worldX + 0.5 - (centerX = (double)sxCell * 192.0 + 32.0 + MapChunkGenerator.caveUnit(seed + 17L) * 128.0)) * dxPos + (dzPos = (double)worldZ + 0.5 - (centerZ = (double)szCell * 192.0 + 32.0 + MapChunkGenerator.caveUnit(seed + 29L) * 128.0)) * dzPos > (warpedRadius = (radius = 1.8 + MapChunkGenerator.caveUnit(seed + 41L) * 2.4 + context.styleBias() * 0.35) * (0.78 + (roughness = MapChunkGenerator.normalizedSample(CAVE_NOISE_D, worldX + y * 3, worldZ - y * 4, 13.0)) * 0.4)) * warpedRadius) continue;
                int shaftTop = Math.min(context.caveRoof(), 42 + (int)Math.round(MapChunkGenerator.caveUnit(seed + 53L) * 22.0));
                int shaftBottom = Math.max(context.caveFloor(), -51 + (int)Math.round(MapChunkGenerator.caveUnit(seed + 67L) * 18.0));
                if (y < shaftBottom || y > shaftTop) continue;
                return true;
            }
        }
        return false;
    }

    private boolean cavePocketCarves(int worldX, int y, int worldZ, CaveColumnContext context) {
        double relativeDepth = (double)(y - context.caveFloor()) / (double)Math.max(1, context.caveRoof() - context.caveFloor());
        double lowerBand = 1.0 - Math.abs(relativeDepth - 0.72) * 2.1;
        if (lowerBand <= 0.1) {
            return false;
        }
        double pocket = MapChunkGenerator.normalizedSample(CAVE_NOISE_B, worldX - 121 + y * 3, worldZ + 87 - y * 4, 58.0);
        double core = MapChunkGenerator.normalizedSample(CAVE_NOISE_C, worldX + y * 7, worldZ - y * 5, 24.0);
        double threshold = 0.94 - lowerBand * 0.06 - (context.caveProvince() - 0.5) * 0.04 - context.styleBias() * 0.18;
        return pocket > threshold && core > 0.76;
    }

    private double caveNodeX(int cellX, int cellZ) {
        return (double)cellX * 96.0 + 18.0 + MapChunkGenerator.caveUnit(MapChunkGenerator.cellSeed(cellX, cellZ, 659937L)) * 60.0;
    }

    private double caveNodeZ(int cellX, int cellZ) {
        return (double)cellZ * 96.0 + 18.0 + MapChunkGenerator.caveUnit(MapChunkGenerator.cellSeed(cellX, cellZ, 659938L)) * 60.0;
    }

    private double caveNodeY(int cellX, int cellZ) {
        double roll = MapChunkGenerator.caveUnit(MapChunkGenerator.cellSeed(cellX, cellZ, 659939L));
        double levelNoise = MapChunkGenerator.normalizedSample(CAVE_NOISE_C, cellX * 31 + 13, cellZ * 31 - 17, 24.0);
        double y = -48.0 + roll * 96.0 + (levelNoise - 0.5) * 18.0;
        return class_3532.method_15350((double)y, (double)-50.0, (double)72.0);
    }

    private double caveStyleBias(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 5, 8, 12, 18, 22, 26, 30, 34, 38, 42, 44, 49, 51, 54, 60 -> 1.05;
            case 4, 7, 9, 11, 13, 17, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 46, 48, 57, 59 -> 0.42;
            case 14, 15, 19, 45, 52, 55 -> -0.55;
            default -> 0.0;
        };
    }

    private double caveProvinceThreshold(double styleBias) {
        double bias = Math.max(0.0, styleBias);
        return class_3532.method_15350((double)(0.26 - bias * 0.055), (double)0.2, (double)0.3);
    }

    private static long cellSeed(int cellX, int cellZ, long salt) {
        long value = salt;
        value ^= (long)cellX * -7046029254386353131L;
        return MapChunkGenerator.mix64(value ^= (long)cellZ * -4417276706812531889L);
    }

    private static long mix64(long value) {
        value ^= value >>> 33;
        value *= -49064778989728563L;
        value ^= value >>> 33;
        value *= -4265267296055464877L;
        value ^= value >>> 33;
        return value;
    }

    private static double caveUnit(long seed) {
        return (double)(seed >>> 11) * (double)1.110223E-16f;
    }

    private boolean isOreHost(class_2680 state) {
        return state == STONE || state == DEEPSLATE || state == BLACKSTONE || state == BASALT;
    }

    private class_2680 oreFor(BiomeStyle style, int worldX, int y, int worldZ, class_2680 hostState) {
        boolean deepslate;
        boolean bl = deepslate = hostState == DEEPSLATE;
        if (MapChunkGenerator.isDragonstoneStyle(style)) {
            if (this.matchesOreBlob(worldX, y, worldZ, 24.0, 11.0, 0.9, 0.8)) {
                return class_2246.field_10540.method_9564();
            }
            return null;
        }
        if (y < 26) {
            if (this.allowsGold(style) && this.matchesOreBlob(worldX - 61, y, worldZ + 23, 28.0, 12.0, this.goldThreshold(style), 0.72)) {
                return deepslate ? GotBlocks.DEEPSLATE_SILVER_ORE.method_9564() : GotBlocks.SILVER_ORE.method_9564();
            }
            if (deepslate && this.allowsGold(style) && this.matchesOreBlob(worldX + 37, y, worldZ - 17, 28.0, 12.0, this.goldThreshold(style), 0.72)) {
                return class_2246.field_29026.method_9564();
            }
            if (deepslate && this.matchesOreBlob(worldX - 73, y, worldZ + 51, 24.0, 10.0, 0.95, 0.77)) {
                return class_2246.field_29029.method_9564();
            }
            if (this.matchesOreBlob(worldX + 19, y, worldZ + 91, 26.0, 11.0, this.boostedCopperThreshold(style), 0.68)) {
                return deepslate ? class_2246.field_29221.method_9564() : class_2246.field_27120.method_9564();
            }
            if (deepslate && this.allowsEmerald(style) && this.matchesOreBlob(worldX - 157, y, worldZ + 133, 22.0, 10.0, this.emeraldThreshold(style), 0.74)) {
                return class_2246.field_29220.method_9564();
            }
        }
        if (y >= 18 && y <= 44 && this.matchesOreBlob(worldX - 29, y, worldZ + 41, 30.0, 13.0, this.ironThreshold(style, deepslate, y), 0.62)) {
            return deepslate ? class_2246.field_29027.method_9564() : class_2246.field_10212.method_9564();
        }
        if (y >= 24 && y <= 72 && this.matchesOreBlob(worldX + 83, y, worldZ - 27, 32.0, 14.0, this.copperThreshold(style), 0.64)) {
            return deepslate ? class_2246.field_29221.method_9564() : class_2246.field_27120.method_9564();
        }
        if (y >= 12 && y <= 96 && this.matchesOreBlob(worldX - 113, y, worldZ - 63, 34.0, 15.0, this.coalThreshold(style), 0.58)) {
            return deepslate ? class_2246.field_29219.method_9564() : class_2246.field_10418.method_9564();
        }
        if (y >= 20 && y <= 38 && this.matchesOreBlob(worldX + 131, y, worldZ + 77, 26.0, 11.0, 0.93, 0.66)) {
            return deepslate ? class_2246.field_29028.method_9564() : class_2246.field_10090.method_9564();
        }
        return null;
    }

    private class_2680 applyStoneDepthTransition(class_2680 state, int worldX, int y, int worldZ) {
        if (state != STONE) {
            return state;
        }
        if (y <= 0) {
            return class_2246.field_28888.method_9564();
        }
        if (y > 8) {
            return state;
        }
        double progress = (double)(8 - y) / 8.0;
        double blend = MapChunkGenerator.normalizedSample(ORE_NOISE, worldX + 37, worldZ - 19 + y * 3, 9.0);
        return blend < progress ? DEEPSLATE : state;
    }

    private class_2680 applyStoneClusterVariation(BiomeStyle style, int worldX, int y, int worldZ, class_2680 state) {
        if (state != STONE || y <= 8) {
            return state;
        }
        class_2680 gravel = this.gravelCluster(style, worldX, y, worldZ);
        if (gravel != null) {
            return gravel;
        }
        class_2680 variant = this.stoneVariantCluster(worldX, y, worldZ);
        return variant == null ? state : variant;
    }

    private class_2680 gravelCluster(BiomeStyle style, int worldX, int y, int worldZ) {
        int cellZ;
        int cellY;
        int cellX = Math.floorDiv(worldX, 40);
        long seed = MapChunkGenerator.clusterSeed(cellX, cellY = Math.floorDiv(y, 16), cellZ = Math.floorDiv(worldZ, 40), 6596577L);
        if (MapChunkGenerator.unit(seed) > 0.26 + this.gravelClusterBias(style)) {
            return null;
        }
        return MapChunkGenerator.insideCluster(worldX, y, worldZ, cellX, cellY, cellZ, 40, 16, seed, 5.0, 13.0, 2.5, 5.5) ? GRAVEL : null;
    }

    private double gravelClusterBias(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 10, 12, 13, 24, 26, 27, 47, 49, 53, 54 -> 0.055;
            case 40, 42, 43, 44, 50, 51 -> 0.025;
            default -> 0.0;
        };
    }

    private class_2680 stoneVariantCluster(int worldX, int y, int worldZ) {
        int cellZ;
        int cellY;
        int cellX = Math.floorDiv(worldX, 32);
        long seed = MapChunkGenerator.clusterSeed(cellX, cellY = Math.floorDiv(y, 18), cellZ = Math.floorDiv(worldZ, 32), 356382L);
        if (MapChunkGenerator.unit(seed) > 0.78) {
            return null;
        }
        if (!MapChunkGenerator.insideCluster(worldX, y, worldZ, cellX, cellY, cellZ, 32, 18, seed, 7.0, 15.0, 3.5, 7.5)) {
            return null;
        }
        double selector = MapChunkGenerator.unit(MapChunkGenerator.mix64(seed ^ 0x2B7A5D3L));
        if (selector < 0.16) {
            return GRANITE;
        }
        if (selector < 0.32) {
            return DIORITE;
        }
        if (selector < 0.48) {
            return ANDESITE;
        }
        if (selector < 0.58) {
            return LIMESTONE;
        }
        if (selector < 0.68) {
            return SILTSTONE;
        }
        if (selector < 0.78) {
            return ASH_STONE;
        }
        if (selector < 0.88) {
            return MARBLE;
        }
        if (selector < 0.97) {
            return BLUESTONE;
        }
        return TUFF;
    }

    private static boolean insideCluster(int worldX, int y, int worldZ, int cellX, int cellY, int cellZ, int horizontalSize, int verticalSize, long seed, double minHorizontalRadius, double maxHorizontalRadius, double minVerticalRadius, double maxVerticalRadius) {
        double centerZ;
        double dz;
        double centerY;
        double dy;
        double radiusX = MapChunkGenerator.lerp(minHorizontalRadius, maxHorizontalRadius, MapChunkGenerator.unit(MapChunkGenerator.mix64(seed ^ 0x5DEECE66DL)));
        double radiusY = MapChunkGenerator.lerp(minVerticalRadius, maxVerticalRadius, MapChunkGenerator.unit(MapChunkGenerator.mix64(seed ^ 0xBL)));
        double radiusZ = MapChunkGenerator.lerp(minHorizontalRadius, maxHorizontalRadius, MapChunkGenerator.unit(MapChunkGenerator.mix64(seed ^ 0xC0FFEE12L)));
        double centerX = (double)cellX * (double)horizontalSize + MapChunkGenerator.unit(MapChunkGenerator.mix64(seed ^ 0x9E3779B9L)) * (double)horizontalSize;
        double dx = ((double)worldX + 0.5 - centerX) / radiusX;
        return dx * dx + (dy = ((double)y + 0.5 - (centerY = (double)cellY * (double)verticalSize + MapChunkGenerator.unit(MapChunkGenerator.mix64(seed ^ 0x85EBCA6BL)) * (double)verticalSize)) / radiusY) * dy + (dz = ((double)worldZ + 0.5 - (centerZ = (double)cellZ * (double)horizontalSize + MapChunkGenerator.unit(MapChunkGenerator.mix64(seed ^ 0xC2B2AE35L)) * (double)horizontalSize)) / radiusZ) * dz <= 1.0;
    }

    private static long clusterSeed(int cellX, int cellY, int cellZ, long salt) {
        long value = salt;
        value ^= (long)cellX * -7046029254386353131L;
        value ^= (long)cellY * -4658895280553007687L;
        return MapChunkGenerator.mix64(value ^= (long)cellZ * -7723592293110705685L);
    }

    private static double unit(long seed) {
        return (double)(seed >>> 11) * (double)1.110223E-16f;
    }

    private static double lerp(double min, double max, double delta) {
        return min + (max - min) * delta;
    }

    private boolean matchesOreBlob(int worldX, int y, int worldZ, double primaryScale, double detailScale, double primaryThreshold, double detailThreshold) {
        double primary = MapChunkGenerator.normalizedSample(ORE_NOISE, worldX + y * 11, worldZ - y * 7, primaryScale);
        if (primary <= Math.max(0.0, primaryThreshold - 0.035)) {
            return false;
        }
        double cluster = MapChunkGenerator.normalizedSample(ORE_NOISE, worldX - 83 + y * 3, worldZ + 57 - y * 5, detailScale);
        if (cluster <= Math.max(0.0, detailThreshold - 0.03)) {
            return false;
        }
        double falloff = MapChunkGenerator.normalizedSample(ORE_NOISE, worldX + 17 - y * 2, worldZ - 29 + y * 4, detailScale * 1.65);
        if (falloff <= 0.54) {
            return false;
        }
        double core = MapChunkGenerator.normalizedSample(ORE_NOISE, worldX - 41 + y, worldZ + 73 - y * 2, detailScale * 0.8);
        if (core <= 0.71) {
            return false;
        }
        double edgeMask = MapChunkGenerator.normalizedSample(ORE_NOISE, worldX + 103 - y * 3, worldZ - 151 + y, detailScale * 0.55);
        return edgeMask > 0.62;
    }

    private boolean allowsGold(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 3, 4, 5, 6, 7, 8 -> false;
            default -> true;
        };
    }

    private double coalThreshold(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 32, 33, 34 -> 0.76;
            case 3, 4, 5, 6, 7, 8, 24, 25, 26 -> 0.79;
            case 20, 21, 22 -> 0.88;
            case 43, 44, 45, 50, 51, 52, 53, 54, 55 -> 0.87;
            default -> 0.83;
        };
    }

    private double ironThreshold(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 12, 20, 21, 22, 34 -> 0.81;
            case 43, 44, 50, 51 -> 0.87;
            default -> 0.84;
        };
    }

    private double ironThreshold(BiomeStyle style, boolean deepslate, int y) {
        double threshold = this.ironThreshold(style);
        if (!deepslate && y > 8) {
            return Math.min(0.97, threshold + 0.1);
        }
        return threshold;
    }

    private double copperThreshold(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 43, 44, 45, 50, 51, 52, 58, 59, 60 -> 0.79;
            case 3, 4, 5, 6, 7, 8 -> 0.88;
            default -> 0.84;
        };
    }

    private double boostedCopperThreshold(BiomeStyle style) {
        return Math.max(0.72, this.copperThreshold(style) - 0.08);
    }

    private double goldThreshold(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 20, 21, 22 -> 0.82;
            case 43, 44, 45, 50, 51, 52 -> 0.86;
            case 32, 33, 34 -> 0.91;
            default -> 0.89;
        };
    }

    private boolean allowsEmerald(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 8, 12, 13, 22, 34, 42 -> true;
            default -> false;
        };
    }

    private double emeraldThreshold(BiomeStyle style) {
        return switch (style.ordinal()) {
            case 12 -> 0.85;
            case 8, 22, 34 -> 0.88;
            default -> 0.9;
        };
    }

    private static Set<String> basePassiveSpawnSubstrates(BiomeStyle style) {
        return switch (style.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> Set.of();
            case 1 -> Set.of("minecraft:snow_block", "minecraft:packed_ice");
            case 2 -> Set.of("minecraft:grass_block", "minecraft:coarse_dirt", "minecraft:podzol", "minecraft:sand");
            case 3, 4 -> Set.of("minecraft:snow_block", "minecraft:packed_ice");
            case 5 -> Set.of("minecraft:packed_ice");
            case 6, 7 -> Set.of("minecraft:grass_block", "minecraft:coarse_dirt", "minecraft:podzol", "minecraft:sand");
            case 8, 9 -> Set.of("minecraft:grass_block", "minecraft:coarse_dirt", "minecraft:podzol");
            case 10, 11, 12 -> Set.of("minecraft:grass_block", "minecraft:gravel", "agob:limestone");
            case 13 -> Set.of("minecraft:grass_block", "minecraft:gravel");
            case 14 -> Set.of("minecraft:grass_block");
            case 15 -> Set.of("minecraft:grass_block", "minecraft:coarse_dirt");
            case 16, 17 -> Set.of("minecraft:grass_block", "minecraft:sand");
            case 18 -> Set.of("minecraft:grass_block", "minecraft:sand", "agob:siltstone");
            case 19 -> Set.of("minecraft:grass_block", "minecraft:clay");
            case 20, 22 -> Set.of("minecraft:grass_block", "agob:marble");
            case 21 -> Set.of("minecraft:grass_block", "agob:mossy_marble");
            case 23 -> Set.of("minecraft:grass_block", "minecraft:coarse_dirt");
            case 24 -> Set.of("minecraft:grass_block", "minecraft:gravel", "agob:siltstone");
            case 25 -> Set.of("minecraft:grass_block", "agob:siltstone");
            case 26 -> Set.of("minecraft:grass_block", "agob:limestone");
            case 27 -> Set.of("minecraft:grass_block", "minecraft:gravel", "agob:siltstone");
            case 28, 29 -> Set.of("minecraft:grass_block", "minecraft:sand");
            case 30 -> Set.of("minecraft:grass_block", "minecraft:sand", "agob:limestone");
            case 31 -> Set.of("minecraft:grass_block", "minecraft:coarse_dirt", "minecraft:sand");
            case 32, 33, 34 -> Set.of("minecraft:grass_block", "agob:siltstone");
            case 35 -> Set.of("minecraft:grass_block", "minecraft:coarse_dirt");
            case 36, 37 -> Set.of("minecraft:grass_block", "minecraft:sand");
            case 38 -> Set.of("minecraft:grass_block", "minecraft:sand", "agob:limestone");
            case 39 -> Set.of("minecraft:grass_block", "minecraft:coarse_dirt", "minecraft:sand");
            case 40, 41, 42 -> Set.of("minecraft:grass_block", "minecraft:coarse_dirt", "agob:ash_stone", "agob:charred_stone");
            case 43 -> Set.of("minecraft:sand", "minecraft:red_sand", "agob:limestone");
            case 44 -> Set.of("minecraft:sand", "agob:limestone");
            case 45 -> Set.of("minecraft:grass_block");
            case 46 -> Set.of("minecraft:coarse_dirt", "minecraft:red_sand");
            case 47, 48, 49 -> Set.of("minecraft:grass_block", "minecraft:gravel", "agob:bluestone");
            case 50 -> Set.of("minecraft:sand", "minecraft:red_sand");
            case 51 -> Set.of("minecraft:sand", "minecraft:red_sand", "agob:siltstone");
            case 52, 55 -> Set.of("minecraft:grass_block", "minecraft:sand");
            case 53 -> Set.of("minecraft:sand");
            case 54 -> Set.of("minecraft:sand", "agob:volcanic_stone");
            case 56 -> Set.of("minecraft:grass_block", "minecraft:coarse_dirt");
            case 57 -> Set.of("minecraft:grass_block", "minecraft:coarse_dirt", "agob:siltstone");
            case 58, 59 -> Set.of("minecraft:grass_block", "minecraft:sand");
            case 60 -> Set.of("minecraft:grass_block", "minecraft:sand", "agob:siltstone");
        };
    }

    private static BiomeStyle styleForBiomePath(String biomePath) {
        return switch (biomePath) {
            case "sea" -> BiomeStyle.SEA;
            case "the_wall" -> BiomeStyle.WALL;
            case "the_gift" -> BiomeStyle.THE_GIFT;
            case "always_winter" -> BiomeStyle.ALWAYS_WINTER;
            case "always_winter_forest" -> BiomeStyle.ALWAYS_WINTER_FOREST;
            case "always_winter_hills" -> BiomeStyle.ALWAYS_WINTER_HILLS;
            case "the_north" -> BiomeStyle.THE_NORTH;
            case "the_north_forest" -> BiomeStyle.THE_NORTH_FOREST;
            case "the_north_hills" -> BiomeStyle.THE_NORTH_HILLS;
            case "the_north_wolfswood" -> BiomeStyle.THE_NORTH_WOLFSWOOD;
            case "the_vale" -> BiomeStyle.THE_VALE;
            case "the_vale_forest" -> BiomeStyle.THE_VALE_FOREST;
            case "the_vale_hills" -> BiomeStyle.THE_VALE_HILLS;
            case "the_vale_foothill" -> BiomeStyle.THE_VALE_FOOTHILL;
            case "the_vale_valley" -> BiomeStyle.THE_VALE_VALLEY;
            case "the_vale_meadow" -> BiomeStyle.THE_VALE_MEADOW;
            case "the_riverlands" -> BiomeStyle.THE_RIVERLANDS;
            case "the_riverlands_forest" -> BiomeStyle.THE_RIVERLANDS_FOREST;
            case "the_riverlands_hills" -> BiomeStyle.THE_RIVERLANDS_HILLS;
            case "the_riverlands_marsh" -> BiomeStyle.THE_RIVERLANDS_MARSH;
            case "the_westerlands" -> BiomeStyle.THE_WESTERLANDS;
            case "the_westerlands_forest" -> BiomeStyle.THE_WESTERLANDS_FOREST;
            case "the_westerlands_hills" -> BiomeStyle.THE_WESTERLANDS_HILLS;
            case "the_westerlands_goldgrass" -> BiomeStyle.THE_WESTERLANDS_GOLDGRASS;
            case "iron_islands" -> BiomeStyle.IRON_ISLANDS;
            case "iron_islands_forest" -> BiomeStyle.IRON_ISLANDS_FOREST;
            case "iron_islands_hills" -> BiomeStyle.IRON_ISLANDS_HILLS;
            case "iron_islands_moor" -> BiomeStyle.IRON_ISLANDS_MOOR;
            case "the_reach" -> BiomeStyle.THE_REACH;
            case "the_reach_forest" -> BiomeStyle.THE_REACH_FOREST;
            case "the_reach_hills" -> BiomeStyle.THE_REACH_HILLS;
            case "the_reach_orchard" -> BiomeStyle.THE_REACH_ORCHARD;
            case "the_stormlands" -> BiomeStyle.THE_STORMLANDS;
            case "the_stormlands_forest" -> BiomeStyle.THE_STORMLANDS_FOREST;
            case "the_stormlands_hills" -> BiomeStyle.THE_STORMLANDS_HILLS;
            case "the_stormlands_rainwood" -> BiomeStyle.THE_STORMLANDS_RAINWOOD;
            case "the_crownlands" -> BiomeStyle.THE_CROWNLANDS;
            case "the_crownlands_forest" -> BiomeStyle.THE_CROWNLANDS_FOREST;
            case "the_crownlands_hills" -> BiomeStyle.THE_CROWNLANDS_HILLS;
            case "the_crownlands_kingswood" -> BiomeStyle.THE_CROWNLANDS_KINGSWOOD;
            case "dragonstone", "targaryen" -> BiomeStyle.DRAGONSTONE;
            case "dragonstone_forest" -> BiomeStyle.DRAGONSTONE_FOREST;
            case "dragonstone_hills" -> BiomeStyle.DRAGONSTONE_HILLS;
            case "dorne" -> BiomeStyle.DORNE;
            case "dorne_hills" -> BiomeStyle.DORNE_HILLS;
            case "dorne_oasis" -> BiomeStyle.DORNE_OASIS;
            case "dorne_scrubland" -> BiomeStyle.DORNE_SCRUBLAND;
            case "braavos" -> BiomeStyle.BRAAVOS;
            case "braavos_forest" -> BiomeStyle.BRAAVOS_FOREST;
            case "braavos_hills" -> BiomeStyle.BRAAVOS_HILLS;
            case "slavers_bay" -> BiomeStyle.SLAVERS_BAY;
            case "slavers_bay_hills" -> BiomeStyle.SLAVERS_BAY_HILLS;
            case "slavers_bay_oasis" -> BiomeStyle.SLAVERS_BAY_OASIS;
            case "step_stones", "tyrosh", "myr" -> BiomeStyle.STEP_STONES;
            case "step_stones_hills" -> BiomeStyle.STEP_STONES_HILLS;
            case "step_stones_oasis" -> BiomeStyle.STEP_STONES_OASIS;
            case "dothraki_sea" -> BiomeStyle.DOTHRAKI_SEA;
            case "dothraki_sea_steppe" -> BiomeStyle.DOTHRAKI_SEA_STEPPE;
            case "volantis", "pentos" -> BiomeStyle.VOLANTIS;
            case "volantis_forest" -> BiomeStyle.VOLANTIS_FOREST;
            case "volantis_hills" -> BiomeStyle.VOLANTIS_HILLS;
            default -> throw new IllegalStateException("No map-generator biome style for agob:" + biomePath);
        };
    }

    private static boolean isAlwaysWinterStyle(BiomeStyle style) {
        return style == BiomeStyle.ALWAYS_WINTER || style == BiomeStyle.ALWAYS_WINTER_FOREST || style == BiomeStyle.ALWAYS_WINTER_HILLS;
    }

    private static boolean isNorthStyle(BiomeStyle style) {
        return style == BiomeStyle.THE_NORTH || style == BiomeStyle.THE_NORTH_FOREST || style == BiomeStyle.THE_NORTH_HILLS || style == BiomeStyle.THE_NORTH_WOLFSWOOD;
    }

    private static BiomeStyle getStyle(class_6880<class_1959> biome) {
        return biome.method_40230().map(key -> "agob".equals(key.method_29177().method_12836()) && KNOWN_BIOME_PATHS.contains(key.method_29177().method_12832()) ? MapChunkGenerator.styleForBiomePath(key.method_29177().method_12832()) : BiomeStyle.VOLANTIS).orElse(BiomeStyle.VOLANTIS);
    }

    private double wallInteriorness(int worldX, int worldZ, GenerationCache cache) {
        int maxDistance = 48;
        int step = 16;
        int nearest = maxDistance;
        for (int distance = step; distance <= maxDistance; distance += step) {
            if (this.getStyleAt(worldX - distance, worldZ, cache) == BiomeStyle.WALL && this.getStyleAt(worldX + distance, worldZ, cache) == BiomeStyle.WALL && this.getStyleAt(worldX, worldZ - distance, cache) == BiomeStyle.WALL && this.getStyleAt(worldX, worldZ + distance, cache) == BiomeStyle.WALL) continue;
            nearest = distance;
            break;
        }
        return class_3532.method_15350((double)((double)nearest / (double)maxDistance), (double)0.0, (double)1.0);
    }

    private static TerrainSettings terrainSettings(BiomeStyle style) {
        return switch (style.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> new TerrainSettings(42, 8, -10, 4, -10);
            case 1 -> new TerrainSettings(118, 10, 34, 8, 0);
            case 2 -> new TerrainSettings(74, 10, 12, 5, 10);
            case 3 -> new TerrainSettings(86, 12, 22, 7, 6);
            case 4 -> new TerrainSettings(84, 10, 16, 5, 6);
            case 5 -> new TerrainSettings(94, 14, 28, 7, 6);
            case 6 -> new TerrainSettings(74, 10, 12, 5, 10);
            case 7 -> new TerrainSettings(74, 8, 8, 4, 8);
            case 8 -> new TerrainSettings(86, 14, 26, 7, 10);
            case 9 -> new TerrainSettings(76, 9, 10, 4, 8);
            case 10 -> new TerrainSettings(90, 18, 30, 7, 16);
            case 11 -> new TerrainSettings(86, 12, 16, 5, 14);
            case 12 -> new TerrainSettings(103, 22, 38, 10, 16);
            case 13 -> new TerrainSettings(82, 11, 14, 4, 10);
            case 14 -> new TerrainSettings(70, 5, 4, 3, 7);
            case 15 -> new TerrainSettings(73, 6, 5, 3, 6);
            case 16 -> new TerrainSettings(69, 8, 6, 4, 10);
            case 17 -> new TerrainSettings(70, 7, 5, 3, 8);
            case 18 -> new TerrainSettings(77, 11, 16, 5, 10);
            case 19 -> new TerrainSettings(67, 5, 3, 2, 8);
            case 20 -> new TerrainSettings(84, 14, 22, 6, 12);
            case 21 -> new TerrainSettings(79, 11, 10, 4, 10);
            case 22 -> new TerrainSettings(96, 18, 36, 8, 12);
            case 23 -> new TerrainSettings(80, 10, 12, 4, 10);
            case 24 -> new TerrainSettings(74, 10, 26, 6, 18);
            case 25 -> new TerrainSettings(73, 8, 18, 4, 16);
            case 26 -> new TerrainSettings(81, 12, 30, 7, 18);
            case 27 -> new TerrainSettings(75, 9, 20, 5, 18);
            case 28 -> new TerrainSettings(71, 7, 5, 4, 8);
            case 29 -> new TerrainSettings(72, 6, 4, 3, 6);
            case 30 -> new TerrainSettings(79, 10, 14, 5, 8);
            case 31 -> new TerrainSettings(72, 6, 4, 3, 6);
            case 32 -> new TerrainSettings(81, 12, 20, 6, 10);
            case 33 -> new TerrainSettings(78, 10, 12, 5, 8);
            case 34 -> new TerrainSettings(94, 16, 34, 8, 10);
            case 35 -> new TerrainSettings(79, 9, 10, 5, 8);
            case 36 -> new TerrainSettings(75, 9, 10, 4, 10);
            case 37 -> new TerrainSettings(76, 8, 8, 4, 8);
            case 38 -> new TerrainSettings(85, 12, 22, 6, 10);
            case 39 -> new TerrainSettings(77, 8, 8, 4, 8);
            case 40 -> new TerrainSettings(86, 14, 24, 7, 16);
            case 41 -> new TerrainSettings(82, 10, 12, 5, 14);
            case 42 -> new TerrainSettings(98, 16, 34, 9, 16);
            case 43 -> new TerrainSettings(71, 8, 16, 5, 6);
            case 44 -> new TerrainSettings(86, 12, 30, 7, 6);
            case 45 -> new TerrainSettings(65, 4, 2, 2, 2);
            case 46 -> new TerrainSettings(72, 6, 10, 4, 5);
            case 47 -> new TerrainSettings(66, 5, 4, 3, 4);
            case 48 -> new TerrainSettings(67, 5, 3, 3, 3);
            case 49 -> new TerrainSettings(73, 8, 12, 5, 4);
            case 50 -> new TerrainSettings(70, 8, 12, 4, 6);
            case 51 -> new TerrainSettings(84, 11, 24, 6, 6);
            case 52 -> new TerrainSettings(66, 4, 3, 2, 2);
            case 53 -> new TerrainSettings(67, 7, 18, 5, 22);
            case 54 -> new TerrainSettings(80, 10, 28, 6, 20);
            case 55 -> new TerrainSettings(64, 3, 3, 2, 4);
            case 56 -> new TerrainSettings(74, 9, 4, 4, 0);
            case 57 -> new TerrainSettings(83, 6, 5, 2, 0);
            case 58 -> new TerrainSettings(72, 7, 5, 4, 5);
            case 59 -> new TerrainSettings(73, 6, 4, 3, 4);
            case 60 -> new TerrainSettings(79, 9, 14, 5, 5);
        };
    }

    private static boolean isDragonstoneStyle(BiomeStyle style) {
        return style == BiomeStyle.DRAGONSTONE || style == BiomeStyle.DRAGONSTONE_FOREST || style == BiomeStyle.DRAGONSTONE_HILLS;
    }

    private static boolean isOasisStyle(BiomeStyle style) {
        return style == BiomeStyle.DORNE_OASIS || style == BiomeStyle.SLAVERS_BAY_OASIS || style == BiomeStyle.STEP_STONES_OASIS;
    }

    private static int clampY(int y) {
        return class_3532.method_15340((int)y, (int)-60, (int)315);
    }

    private static double sample(class_3541 sampler, int x, int z, double scale) {
        return sampler.method_15433((double)x / scale, (double)z / scale);
    }

    private static double normalizedSample(class_3541 sampler, int x, int z, double scale) {
        return class_3532.method_15350((double)((MapChunkGenerator.sample(sampler, x, z, scale) + 1.0) * 0.5), (double)0.0, (double)1.0);
    }

    private static double sample3d(class_3541 sampler, int x, int y, int z, double horizontalScale, double verticalScale) {
        return sampler.method_22416((double)x / horizontalScale, (double)y / verticalScale, (double)z / horizontalScale);
    }

    private static double normalizedSample3d(class_3541 sampler, int x, int y, int z, double horizontalScale, double verticalScale) {
        return class_3532.method_15350((double)((MapChunkGenerator.sample3d(sampler, x, y, z, horizontalScale, verticalScale) + 1.0) * 0.5), (double)0.0, (double)1.0);
    }

    private static double smoothstep(double value) {
        double clamped = class_3532.method_15350((double)value, (double)0.0, (double)1.0);
        return clamped * clamped * (3.0 - 2.0 * clamped);
    }

    private static enum BiomeStyle {
        SEA,
        WALL,
        THE_GIFT,
        ALWAYS_WINTER,
        ALWAYS_WINTER_FOREST,
        ALWAYS_WINTER_HILLS,
        THE_NORTH,
        THE_NORTH_FOREST,
        THE_NORTH_HILLS,
        THE_NORTH_WOLFSWOOD,
        THE_VALE,
        THE_VALE_FOREST,
        THE_VALE_HILLS,
        THE_VALE_FOOTHILL,
        THE_VALE_VALLEY,
        THE_VALE_MEADOW,
        THE_RIVERLANDS,
        THE_RIVERLANDS_FOREST,
        THE_RIVERLANDS_HILLS,
        THE_RIVERLANDS_MARSH,
        THE_WESTERLANDS,
        THE_WESTERLANDS_FOREST,
        THE_WESTERLANDS_HILLS,
        THE_WESTERLANDS_GOLDGRASS,
        IRON_ISLANDS,
        IRON_ISLANDS_FOREST,
        IRON_ISLANDS_HILLS,
        IRON_ISLANDS_MOOR,
        THE_REACH,
        THE_REACH_FOREST,
        THE_REACH_HILLS,
        THE_REACH_ORCHARD,
        THE_STORMLANDS,
        THE_STORMLANDS_FOREST,
        THE_STORMLANDS_HILLS,
        THE_STORMLANDS_RAINWOOD,
        THE_CROWNLANDS,
        THE_CROWNLANDS_FOREST,
        THE_CROWNLANDS_HILLS,
        THE_CROWNLANDS_KINGSWOOD,
        DRAGONSTONE,
        DRAGONSTONE_FOREST,
        DRAGONSTONE_HILLS,
        DORNE,
        DORNE_HILLS,
        DORNE_OASIS,
        DORNE_SCRUBLAND,
        BRAAVOS,
        BRAAVOS_FOREST,
        BRAAVOS_HILLS,
        SLAVERS_BAY,
        SLAVERS_BAY_HILLS,
        SLAVERS_BAY_OASIS,
        STEP_STONES,
        STEP_STONES_HILLS,
        STEP_STONES_OASIS,
        DOTHRAKI_SEA,
        DOTHRAKI_SEA_STEPPE,
        VOLANTIS,
        VOLANTIS_FOREST,
        VOLANTIS_HILLS;

    }

    public record NorthernSurfaceAudit(int sampleCount, int exposedGrassCount, int occupiedRegionCount, int roadGrassCount, long fingerprint) {
    }

    private static final class GenerationCache {
        private final Long2ObjectOpenHashMap<class_6880<class_1959>> biomes = new Long2ObjectOpenHashMap();
        private final Long2ObjectOpenHashMap<BiomeStyle> styles = new Long2ObjectOpenHashMap();
        private final Long2ObjectOpenHashMap<TerrainBlend> terrainBlends = new Long2ObjectOpenHashMap();
        private final Long2IntOpenHashMap rawTops = GenerationCache.intCache();
        private final Long2IntOpenHashMap smoothedTops = GenerationCache.intCache();
        private final Long2DoubleOpenHashMap coastalness = GenerationCache.doubleCache();
        private final Long2DoubleOpenHashMap immediateSeaAdjacency = GenerationCache.doubleCache();
        private final Long2DoubleOpenHashMap nearestSeaDistances = GenerationCache.doubleCache();
        private final Long2DoubleOpenHashMap nearestLandDistances = GenerationCache.doubleCache();

        private GenerationCache() {
        }

        private static Long2IntOpenHashMap intCache() {
            Long2IntOpenHashMap cache = new Long2IntOpenHashMap();
            cache.defaultReturnValue(Integer.MIN_VALUE);
            return cache;
        }

        private static Long2DoubleOpenHashMap doubleCache() {
            Long2DoubleOpenHashMap cache = new Long2DoubleOpenHashMap();
            cache.defaultReturnValue(Double.NaN);
            return cache;
        }

        private BiomeStyle styleFor(class_6880<class_1959> biome) {
            return MapChunkGenerator.getStyle(biome);
        }
    }

    private record ColumnProfile(int surfaceY, int deepTopY, int terrainTopY, class_2680 surfaceBlock, class_2680 subsurfaceBlock, class_2680 deepBlock, class_2680 fluidBlock, int fluidTopY, int motionBlockingY, BiomeStyle style) {
    }

    private static final class RoadMapHolder {
        private static final MapImage IMAGE = MapImage.load("assets/agob/map_road.png");

        private RoadMapHolder() {
        }
    }

    private record CaveColumnContext(int caveFloor, int caveRoof, double caveProvince, double styleBias, int roomCellX, int roomCellZ, int shaftCellX, int shaftCellZ) {
    }

    private final class CachedMotionBlockingSurfaceSampler
    implements MotionBlockingSurfaceSampler {
        private final int cacheWindow;
        private final Long2IntOpenHashMap sampledSurfaces = GenerationCache.intCache();
        private GenerationCache generationCache = new GenerationCache();
        private int cacheHits;
        private int cacheResets;
        private int maxCachedSamples;

        private CachedMotionBlockingSurfaceSampler(int cacheWindow) {
            if (cacheWindow <= 0) {
                throw new IllegalArgumentException("Motion-blocking sample cache window must be positive");
            }
            this.cacheWindow = cacheWindow;
        }

        @Override
        public int sample(int x, int z) {
            long key = MapChunkGenerator.columnKey(x, z);
            int cached = this.sampledSurfaces.get(key);
            if (cached != Integer.MIN_VALUE) {
                ++this.cacheHits;
                return cached;
            }
            if (this.sampledSurfaces.size() >= this.cacheWindow) {
                this.sampledSurfaces.clear();
                this.generationCache = new GenerationCache();
                ++this.cacheResets;
            }
            int surfaceY = MapChunkGenerator.this.estimateMotionBlockingSurfaceY(x, z, this.generationCache);
            this.sampledSurfaces.put(key, surfaceY);
            this.maxCachedSamples = Math.max(this.maxCachedSamples, this.sampledSurfaces.size());
            return surfaceY;
        }

        private MotionBlockingSurfaceSamplerAudit audit(int equivalentSamples) {
            return new MotionBlockingSurfaceSamplerAudit(equivalentSamples, this.cacheHits, this.cacheResets, this.maxCachedSamples, this.cacheWindow);
        }
    }

    public record MotionBlockingSurfaceSamplerAudit(int equivalentSamples, int cacheHits, int cacheResets, int maxCachedSamples, int cacheWindow) {
    }

    public record NaturalSurfaceSample(int groundY, class_2680 groundState, boolean surfaceFluidOccupied, class_2680 surfaceFluidState, int surfaceFluidTopY) {
        public NaturalSurfaceSample {
            groundState = groundState == null ? AIR : groundState;
            class_2680 class_26802 = surfaceFluidState = surfaceFluidState == null ? AIR : surfaceFluidState;
            if (!surfaceFluidOccupied) {
                surfaceFluidState = AIR;
                surfaceFluidTopY = groundY;
            } else if (surfaceFluidState.method_26227().method_15769() || surfaceFluidTopY <= groundY) {
                throw new IllegalArgumentException("Occupied natural surface fluid requires a fluid block above the ground");
            }
        }
    }

    private static final class SANDSTONEHolder {
        private static final class_2680 SANDSTONE = class_2246.field_9979.method_9564();

        private SANDSTONEHolder() {
        }
    }

    private record TreePlacementProfile(double coverageThreshold, double anchorThreshold, int spacingRadius, double coverageScale, double anchorScale) {
        private static final TreePlacementProfile NONE = new TreePlacementProfile(1.1, 1.1, 0, 1.0, 1.0);
    }

    private record SurfacePlacement(int floorY, int topY, class_2680 surfaceState, class_2680 foundationState) {
    }

    private record SurfaceSampleStats(int averageY, int minY, int maxY, int samples, List<Integer> heights) {
    }

    private static final class CustomStructureLogCounter {
        private final String structureType;
        private final String result;
        private final String reason;
        private int count;
        private int sampleWorldX;
        private int sampleWorldZ;
        private int sampleChunkX;
        private int sampleChunkZ;
        private String sampleOrigin = "n/a";
        private String sampleTemplate = "n/a";
        private String sampleDetail = "n/a";

        private CustomStructureLogCounter(String structureType, String result, String reason) {
            this.structureType = structureType;
            this.result = result;
            this.reason = reason;
        }
    }

    public record PredictedHireableCampSite(String definitionId, String claimName, String kingdomId, class_1923 chunkPos, class_2338 teleportPos) {
    }

    private static final class MultiNoiseSamplerHolder {
        private static final class_6910 ZERO = class_6916.method_40480((double)0.0);
        private static final class_6544.class_6552 INSTANCE = new class_6544.class_6552(ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, List.of());

        private MultiNoiseSamplerHolder() {
        }
    }

    private record TerrainBlend(double baseHeight, double broadAmplitude, double ridgeAmplitude, double detailAmplitude, double coastPenalty) {
    }

    private record TerrainSettings(int baseHeight, int broadAmplitude, int ridgeAmplitude, int detailAmplitude, int coastPenalty) {
    }

    @FunctionalInterface
    public static interface NaturalSurfaceSampler {
        public NaturalSurfaceSample sample(int var1, int var2);
    }

    @FunctionalInterface
    public static interface MotionBlockingSurfaceSampler {
        public int sample(int var1, int var2);
    }
}

