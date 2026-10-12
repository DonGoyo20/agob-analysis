/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.class_1959
 *  net.minecraft.class_1966
 *  net.minecraft.class_2338
 *  net.minecraft.class_2960
 *  net.minecraft.class_6544$class_6552
 *  net.minecraft.class_6880
 *  net.minecraft.class_6910
 *  net.minecraft.class_6916
 */
package trep.got.world.gen;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.stream.Stream;
import net.minecraft.class_1959;
import net.minecraft.class_1966;
import net.minecraft.class_2338;
import net.minecraft.class_2960;
import net.minecraft.class_6544;
import net.minecraft.class_6880;
import net.minecraft.class_6910;
import net.minecraft.class_6916;
import trep.got.GotMod;
import trep.got.world.gen.MapBiomeEntry;
import trep.got.world.map.MapImage;
import trep.got.world.map.RoadMapHelper;

public class MapBiomeSource
extends class_1966 {
    public static final MapCodec<MapBiomeSource> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group((App)class_2960.field_25139.fieldOf("map").forGetter(MapBiomeSource::mapId), (App)class_1959.field_24677.fieldOf("default_biome").forGetter(MapBiomeSource::defaultBiome), (App)MapBiomeEntry.CODEC.listOf().fieldOf("entries").forGetter(MapBiomeSource::entries), (App)Codec.INT.optionalFieldOf("horizontal_scale", (Object)8).forGetter(MapBiomeSource::horizontalScale), (App)Codec.INT.optionalFieldOf("pixel_offset_x", (Object)1500).forGetter(MapBiomeSource::pixelOffsetX), (App)Codec.INT.optionalFieldOf("pixel_offset_z", (Object)1500).forGetter(MapBiomeSource::pixelOffsetZ)).apply((Applicative)instance, MapBiomeSource::new));
    private static final class_6544.class_6552 ZERO_SAMPLER = MultiNoiseSamplerHolder.INSTANCE;
    private static final class_2960 ROAD_MAP_ID = class_2960.method_60655((String)"agob", (String)"map_road.png");
    private static final int ROAD_COLOR = 13154156;
    private static final int RESOLVED_BIOME_CACHE_SIZE = 65536;
    private final class_2960 mapId;
    private final MapImage mapImage;
    private final class_6880<class_1959> defaultBiome;
    private final List<MapBiomeEntry> entries;
    private final Map<Integer, class_6880<class_1959>> biomeByColor;
    private final Map<String, class_6880<class_1959>> biomeByPath;
    private final Map<class_6880<class_1959>, String> pathByBiome;
    private final Map<class_6880<class_1959>, class_6880<class_1959>> forestVariantByBiome;
    private final Map<class_6880<class_1959>, class_6880<class_1959>> hillsVariantByBiome;
    private final Map<class_6880<class_1959>, class_6880<class_1959>> oasisVariantByBiome;
    private final FastPixelLookup fastPixelLookup;
    private final AtomicReferenceArray<CachedBiome> resolvedBiomeCache;
    private final int horizontalScale;
    private final int pixelOffsetX;
    private final int pixelOffsetZ;

    public MapBiomeSource(class_2960 mapId, class_6880<class_1959> defaultBiome, List<MapBiomeEntry> entries, int horizontalScale, int pixelOffsetX, int pixelOffsetZ) {
        this.mapId = mapId;
        this.mapImage = MapBiomeSource.loadMapImage(mapId);
        this.defaultBiome = defaultBiome;
        this.horizontalScale = horizontalScale;
        this.pixelOffsetX = pixelOffsetX;
        this.pixelOffsetZ = pixelOffsetZ;
        this.entries = List.copyOf(entries);
        this.biomeByColor = new HashMap<Integer, class_6880<class_1959>>();
        this.biomeByPath = new HashMap<String, class_6880<class_1959>>();
        this.pathByBiome = new IdentityHashMap<class_6880<class_1959>, String>();
        this.forestVariantByBiome = new IdentityHashMap<class_6880<class_1959>, class_6880<class_1959>>();
        this.hillsVariantByBiome = new IdentityHashMap<class_6880<class_1959>, class_6880<class_1959>>();
        this.oasisVariantByBiome = new IdentityHashMap<class_6880<class_1959>, class_6880<class_1959>>();
        for (MapBiomeEntry entry : entries) {
            this.biomeByColor.putIfAbsent(entry.color().toRgb24(), entry.biome());
            entry.biome().method_40230().ifPresent(key -> {
                String path = key.method_29177().method_12832();
                this.biomeByPath.put(path, entry.biome());
                this.pathByBiome.put(entry.biome(), path);
            });
        }
        this.defaultBiome.method_40230().ifPresent(key -> {
            String path = key.method_29177().method_12832();
            this.biomeByPath.put(path, this.defaultBiome);
            this.pathByBiome.put(this.defaultBiome, path);
        });
        this.registerSuffixVariants(this.defaultBiome);
        for (MapBiomeEntry entry : entries) {
            this.registerSuffixVariants(entry.biome());
        }
        this.fastPixelLookup = FastPixelLookup.create(this.mapImage, this.defaultBiome, this.entries, this.biomeByColor);
        this.resolvedBiomeCache = new AtomicReferenceArray(65536);
    }

    public class_2960 mapId() {
        return this.mapId;
    }

    public class_6880<class_1959> defaultBiome() {
        return this.defaultBiome;
    }

    public List<MapBiomeEntry> entries() {
        return this.entries;
    }

    public int horizontalScale() {
        return this.horizontalScale;
    }

    public int pixelOffsetX() {
        return this.pixelOffsetX;
    }

    public int pixelOffsetZ() {
        return this.pixelOffsetZ;
    }

    protected Stream<class_6880<class_1959>> method_49494() {
        return Stream.concat(Stream.of(this.defaultBiome), this.entries.stream().map(MapBiomeEntry::biome)).distinct();
    }

    protected MapCodec<? extends class_1966> method_28442() {
        return CODEC;
    }

    public class_6880<class_1959> method_38109(int x, int y, int z, class_6544.class_6552 noise) {
        long cacheKey = MapBiomeSource.packCoordinates(x, z);
        int cacheIndex = MapBiomeSource.resolvedBiomeCacheIndex(cacheKey);
        CachedBiome cached = this.resolvedBiomeCache.get(cacheIndex);
        if (cached != null && cached.key() == cacheKey) {
            return cached.biome();
        }
        class_6880<class_1959> resolved = this.resolveBiome(x, z);
        this.resolvedBiomeCache.lazySet(cacheIndex, new CachedBiome(cacheKey, resolved));
        return resolved;
    }

    private class_6880<class_1959> resolveBiome(int x, int z) {
        int pixelZ;
        int pixelX;
        MapImage image = this.mapImage();
        if (!image.contains(pixelX = Math.floorDiv(x, this.horizontalScale) + this.pixelOffsetX, pixelZ = Math.floorDiv(z, this.horizontalScale) + this.pixelOffsetZ)) {
            return this.defaultBiome;
        }
        CoastalBaseSample baseSample = this.resolveCoastalBaseBiome(image, x, z, pixelX, pixelZ);
        class_6880<class_1959> baseBiome = baseSample.biome();
        if (baseBiome == this.defaultBiome) {
            return this.defaultBiome;
        }
        return this.pickVariant(baseBiome, x, z, baseSample.pixelX(), baseSample.pixelZ(), image);
    }

    double sampleSeaInfluenceAtBlock(int worldX, int worldZ, int radiusBlocks) {
        MapImage image = this.mapImage();
        return this.sampleSeaInfluenceAtBlock(image, worldX, worldZ, radiusBlocks);
    }

    double distanceToSeaAtBlock(int worldX, int worldZ, int maxSearchBlocks) {
        MapImage image = this.mapImage();
        return this.distanceToSeaAtBlock(image, worldX, worldZ, maxSearchBlocks);
    }

    double distanceToLandAtBlock(int worldX, int worldZ, int maxSearchBlocks) {
        MapImage image = this.mapImage();
        return this.distanceToLandAtBlock(image, worldX, worldZ, maxSearchBlocks);
    }

    private CoastalBaseSample resolveCoastalBaseBiome(MapImage image, int quartX, int quartZ, int pixelX, int pixelZ) {
        class_6880<class_1959> rawBaseBiome = this.getBaseBiome(image, pixelX, pixelZ);
        if (rawBaseBiome == this.defaultBiome || !this.hasMixedSeaNeighborhood(image, pixelX, pixelZ, 3)) {
            return new CoastalBaseSample(rawBaseBiome, pixelX, pixelZ);
        }
        int worldX = quartX << 2;
        int worldZ = quartZ << 2;
        double seaInfluence = this.sampleSeaInfluenceAtBlock(image, worldX, worldZ, 56);
        double edgeNoise = MapBiomeSource.normalizedNoise(worldX + 211, worldZ - 347, 88.0, 387665);
        double threshold = 0.5 + (edgeNoise - 0.5) * 0.22;
        PixelPoint warpedPoint = this.warpedPixelPointForBlock(worldX, worldZ);
        if (this.isSeaBiome(rawBaseBiome)) {
            if (seaInfluence >= threshold - 0.07) {
                return new CoastalBaseSample(this.seaBiome(), pixelX, pixelZ);
            }
            CoastalBaseSample landSample = this.findNearestLandBiome(image, warpedPoint.x(), warpedPoint.z(), 5);
            return landSample != null ? landSample : new CoastalBaseSample(rawBaseBiome, pixelX, pixelZ);
        }
        if (seaInfluence > threshold) {
            return new CoastalBaseSample(this.seaBiome(), pixelX, pixelZ);
        }
        return new CoastalBaseSample(rawBaseBiome, pixelX, pixelZ);
    }

    private class_6880<class_1959> getBaseBiome(MapImage image, int pixelX, int pixelZ) {
        if (this.fastPixelLookup != null) {
            return this.fastPixelLookup.baseBiome(pixelX, pixelZ);
        }
        if (!image.contains(pixelX, pixelZ) || !image.isOpaque(pixelX, pixelZ)) {
            return this.defaultBiome;
        }
        return this.biomeByColor.getOrDefault(image.rgb(pixelX, pixelZ), this.defaultBiome);
    }

    private class_6880<class_1959> pickVariant(class_6880<class_1959> baseBiome, int x, int z, int pixelX, int pixelZ, MapImage image) {
        String basePath = this.biomePath(baseBiome);
        if (basePath.isEmpty() || basePath.equals("sea") || basePath.equals("the_wall") || basePath.equals("the_gift")) {
            return baseBiome;
        }
        if (this.isRoadPixel(x, z)) {
            return baseBiome;
        }
        double hillNoise = MapBiomeSource.normalizedNoise(x, z, 220.0, 12638);
        double forestNoise = MapBiomeSource.normalizedNoise(x + 541, z - 213, 180.0, 15760983);
        double oasisNoise = MapBiomeSource.normalizedNoise(x - 317, z + 611, 140.0, 42261);
        double steppeNoise = MapBiomeSource.normalizedNoise(x + 91, z + 91, 260.0, 5761438);
        double clusterNoise = MapBiomeSource.normalizedNoise(x - 701, z + 433, 96.0, 12690810);
        double meadowNoise = MapBiomeSource.normalizedNoise(x + 287, z - 517, 150.0, 7253825);
        double innerness = this.sameBiomeRatio(image, pixelX, pixelZ, basePath, 10);
        if (basePath.equals("the_north")) {
            if (innerness > 0.87 && forestNoise > 0.68 && clusterNoise > 0.59) {
                return this.variantOrBase("the_north_wolfswood", baseBiome);
            }
            if (hillNoise > 0.58 && clusterNoise > 0.46) {
                return this.variantOrBase("the_north_hills", baseBiome);
            }
            if (forestNoise > 0.57 && innerness > 0.72) {
                return this.variantOrBase("the_north_forest", baseBiome);
            }
            return baseBiome;
        }
        if (basePath.equals("the_vale")) {
            if (innerness < 0.9) {
                return this.variantOrBase("the_vale_foothill", baseBiome);
            }
            if (innerness > 0.94 && this.valleyMask(x, z) > 0.67) {
                return this.variantOrBase("the_vale_valley", baseBiome);
            }
            if (innerness > 0.88 && this.valleyMask(x - 41, z + 23) > 0.58 && meadowNoise > 0.63) {
                return this.variantOrBase("the_vale_meadow", baseBiome);
            }
            if (hillNoise > 0.61 && clusterNoise > 0.48) {
                return this.variantOrBase("the_vale_hills", baseBiome);
            }
            if (forestNoise > 0.58) {
                return this.variantOrBase("the_vale_forest", baseBiome);
            }
            return baseBiome;
        }
        if (basePath.equals("the_riverlands")) {
            if (innerness > 0.84 && this.valleyMask(x + 63, z - 91) > 0.56 && meadowNoise > 0.6) {
                return this.variantOrBase("the_riverlands_marsh", baseBiome);
            }
            if (hillNoise > 0.58 && clusterNoise > 0.46) {
                return this.variantOrBase("the_riverlands_hills", baseBiome);
            }
            if (forestNoise > 0.57 && innerness > 0.72) {
                return this.variantOrBase("the_riverlands_forest", baseBiome);
            }
            return baseBiome;
        }
        if (basePath.equals("the_westerlands")) {
            if (innerness > 0.84 && meadowNoise > 0.64 && hillNoise < 0.55) {
                return this.variantOrBase("the_westerlands_goldgrass", baseBiome);
            }
            if (hillNoise > 0.58 && clusterNoise > 0.46) {
                return this.variantOrBase("the_westerlands_hills", baseBiome);
            }
            if (forestNoise > 0.57 && innerness > 0.72) {
                return this.variantOrBase("the_westerlands_forest", baseBiome);
            }
            return baseBiome;
        }
        if (basePath.equals("iron_islands")) {
            if (innerness > 0.81 && meadowNoise > 0.61 && hillNoise > 0.46 && forestNoise < 0.52) {
                return this.variantOrBase("iron_islands_moor", baseBiome);
            }
            if (hillNoise > 0.58 && clusterNoise > 0.46) {
                return this.variantOrBase("iron_islands_hills", baseBiome);
            }
            if (forestNoise > 0.57 && innerness > 0.72) {
                return this.variantOrBase("iron_islands_forest", baseBiome);
            }
            return baseBiome;
        }
        if (basePath.equals("the_reach")) {
            if (innerness > 0.86 && meadowNoise > 0.63 && clusterNoise > 0.52) {
                return this.variantOrBase("the_reach_orchard", baseBiome);
            }
            if (hillNoise > 0.58 && clusterNoise > 0.46) {
                return this.variantOrBase("the_reach_hills", baseBiome);
            }
            if (forestNoise > 0.57 && innerness > 0.72) {
                return this.variantOrBase("the_reach_forest", baseBiome);
            }
            return baseBiome;
        }
        if (basePath.equals("the_stormlands")) {
            if (innerness > 0.84 && forestNoise > 0.66 && meadowNoise > 0.46) {
                return this.variantOrBase("the_stormlands_rainwood", baseBiome);
            }
            if (hillNoise > 0.58 && clusterNoise > 0.46) {
                return this.variantOrBase("the_stormlands_hills", baseBiome);
            }
            if (forestNoise > 0.57 && innerness > 0.72) {
                return this.variantOrBase("the_stormlands_forest", baseBiome);
            }
            return baseBiome;
        }
        if (basePath.equals("the_crownlands")) {
            if (innerness > 0.84 && forestNoise > 0.62 && clusterNoise > 0.5) {
                return this.variantOrBase("the_crownlands_kingswood", baseBiome);
            }
            if (hillNoise > 0.58 && clusterNoise > 0.46) {
                return this.variantOrBase("the_crownlands_hills", baseBiome);
            }
            if (forestNoise > 0.57 && innerness > 0.72) {
                return this.variantOrBase("the_crownlands_forest", baseBiome);
            }
            return baseBiome;
        }
        if (basePath.equals("dothraki_sea")) {
            if (innerness > 0.86 && steppeNoise > 0.57) {
                return this.variantOrBase("dothraki_sea_steppe", baseBiome);
            }
            return baseBiome;
        }
        if (basePath.equals("dorne")) {
            if (innerness > 0.82 && meadowNoise > 0.58 && oasisNoise < 0.69 && hillNoise < 0.55) {
                return this.variantOrBase("dorne_scrubland", baseBiome);
            }
            if (hillNoise > 0.54) {
                return this.variantOrBase("dorne_hills", baseBiome);
            }
            return baseBiome;
        }
        if (this.isDesertBase(basePath)) {
            if (innerness > 0.88 && oasisNoise > 0.76 && clusterNoise > 0.52) {
                return MapBiomeSource.suffixVariantOrBase(this.oasisVariantByBiome, baseBiome);
            }
            if (hillNoise > 0.54) {
                return MapBiomeSource.suffixVariantOrBase(this.hillsVariantByBiome, baseBiome);
            }
            return baseBiome;
        }
        if (hillNoise > 0.58 && clusterNoise > 0.46) {
            return MapBiomeSource.suffixVariantOrBase(this.hillsVariantByBiome, baseBiome);
        }
        if (forestNoise > 0.57 && innerness > 0.72) {
            return MapBiomeSource.suffixVariantOrBase(this.forestVariantByBiome, baseBiome);
        }
        return baseBiome;
    }

    private class_6880<class_1959> variantOrBase(String path, class_6880<class_1959> fallback) {
        return this.biomeByPath.getOrDefault(path, fallback);
    }

    private void registerSuffixVariants(class_6880<class_1959> biome) {
        String path = this.biomePath(biome);
        if (path.isEmpty()) {
            return;
        }
        this.forestVariantByBiome.put(biome, this.biomeByPath.getOrDefault(path + "_forest", biome));
        this.hillsVariantByBiome.put(biome, this.biomeByPath.getOrDefault(path + "_hills", biome));
        this.oasisVariantByBiome.put(biome, this.biomeByPath.getOrDefault(path + "_oasis", biome));
    }

    private static class_6880<class_1959> suffixVariantOrBase(Map<class_6880<class_1959>, class_6880<class_1959>> variants, class_6880<class_1959> baseBiome) {
        return variants.getOrDefault(baseBiome, baseBiome);
    }

    private boolean isDesertBase(String path) {
        return path.equals("dorne") || path.equals("slavers_bay") || path.equals("step_stones");
    }

    private double sameBiomeRatio(MapImage image, int pixelX, int pixelZ, String basePath, int radius) {
        if (this.fastPixelLookup != null) {
            return this.fastPixelLookup.sameBiomeRatio(pixelX, pixelZ, basePath, radius);
        }
        int same = 0;
        int total = 0;
        for (int dx = -radius; dx <= radius; dx += Math.max(2, radius / 3)) {
            for (int dz = -radius; dz <= radius; dz += Math.max(2, radius / 3)) {
                int sampleX = pixelX + dx;
                int sampleZ = pixelZ + dz;
                if (!image.contains(sampleX, sampleZ)) continue;
                ++total;
                class_6880<class_1959> sampled = this.getBaseBiome(image, sampleX, sampleZ);
                String sampledPath = this.biomePath(sampled);
                if (!sampledPath.equals(basePath)) continue;
                ++same;
            }
        }
        return total == 0 ? 0.0 : (double)same / (double)total;
    }

    private double valleyMask(int x, int z) {
        double broad = MapBiomeSource.normalizedNoise(x + 177, z - 301, 340.0, 659943);
        double detail = MapBiomeSource.normalizedNoise(x - 611, z + 87, 120.0, 721998);
        return broad * 0.7 + detail * 0.3;
    }

    private static double normalizedNoise(int x, int z, double scale, int seed) {
        double value = Math.sin((double)(x + seed) / scale) + Math.cos((double)(z - seed) / scale * 1.113);
        double folded = Math.sin(value * 2.173 + (double)(x - z) / (scale * 0.7));
        return Math.max(0.0, Math.min(1.0, (folded + 1.0) * 0.5));
    }

    private double sampleSeaInfluenceAtBlock(MapImage image, int worldX, int worldZ, int radiusBlocks) {
        PixelPoint warpedPoint = this.warpedPixelPointForBlock(worldX, worldZ);
        double radiusPixels = Math.max(0.75, (double)radiusBlocks / this.blocksPerPixel());
        return this.sampleSeaInfluence(image, warpedPoint.x(), warpedPoint.z(), radiusPixels);
    }

    private double distanceToSeaAtBlock(MapImage image, int worldX, int worldZ, int maxSearchBlocks) {
        PixelPoint warpedPoint = this.warpedPixelPointForBlock(worldX, worldZ);
        double blocksPerPixel = this.blocksPerPixel();
        double searchRadiusPixels = Math.max(1.0, (double)maxSearchBlocks / blocksPerPixel);
        if (this.sampleSeaInfluence(image, warpedPoint.x(), warpedPoint.z(), 0.9) > 0.52) {
            return 0.0;
        }
        double nearestDistance = Double.POSITIVE_INFINITY;
        int minPixelX = (int)Math.floor(warpedPoint.x() - searchRadiusPixels - 1.5);
        int maxPixelX = (int)Math.ceil(warpedPoint.x() + searchRadiusPixels + 1.5);
        int minPixelZ = (int)Math.floor(warpedPoint.z() - searchRadiusPixels - 1.5);
        int maxPixelZ = (int)Math.ceil(warpedPoint.z() + searchRadiusPixels + 1.5);
        for (int samplePixelX = minPixelX; samplePixelX <= maxPixelX; ++samplePixelX) {
            for (int samplePixelZ = minPixelZ; samplePixelZ <= maxPixelZ; ++samplePixelZ) {
                if (!this.isSeaPixel(image, samplePixelX, samplePixelZ)) continue;
                double distancePixels = MapBiomeSource.distanceToPixelSquare(warpedPoint.x(), warpedPoint.z(), samplePixelX, samplePixelZ);
                nearestDistance = Math.min(nearestDistance, distancePixels * blocksPerPixel);
            }
        }
        return nearestDistance;
    }

    private double distanceToLandAtBlock(MapImage image, int worldX, int worldZ, int maxSearchBlocks) {
        PixelPoint warpedPoint = this.warpedPixelPointForBlock(worldX, worldZ);
        double blocksPerPixel = this.blocksPerPixel();
        double searchRadiusPixels = Math.max(1.0, (double)maxSearchBlocks / blocksPerPixel);
        if (this.sampleSeaInfluence(image, warpedPoint.x(), warpedPoint.z(), 0.9) < 0.48) {
            return 0.0;
        }
        double nearestDistance = Double.POSITIVE_INFINITY;
        int minPixelX = (int)Math.floor(warpedPoint.x() - searchRadiusPixels - 1.5);
        int maxPixelX = (int)Math.ceil(warpedPoint.x() + searchRadiusPixels + 1.5);
        int minPixelZ = (int)Math.floor(warpedPoint.z() - searchRadiusPixels - 1.5);
        int maxPixelZ = (int)Math.ceil(warpedPoint.z() + searchRadiusPixels + 1.5);
        for (int samplePixelX = minPixelX; samplePixelX <= maxPixelX; ++samplePixelX) {
            for (int samplePixelZ = minPixelZ; samplePixelZ <= maxPixelZ; ++samplePixelZ) {
                if (this.isSeaPixel(image, samplePixelX, samplePixelZ)) continue;
                double distancePixels = MapBiomeSource.distanceToPixelSquare(warpedPoint.x(), warpedPoint.z(), samplePixelX, samplePixelZ);
                nearestDistance = Math.min(nearestDistance, distancePixels * blocksPerPixel);
            }
        }
        return nearestDistance;
    }

    private PixelPoint warpedPixelPointForBlock(int worldX, int worldZ) {
        double basePixelX = (double)worldX / this.blocksPerPixel() + (double)this.pixelOffsetX;
        double basePixelZ = (double)worldZ / this.blocksPerPixel() + (double)this.pixelOffsetZ;
        double broadWarpX = (MapBiomeSource.normalizedNoise(worldX + 913, worldZ - 417, 192.0, 466769) - 0.5) * 2.0;
        double broadWarpZ = (MapBiomeSource.normalizedNoise(worldX - 287, worldZ + 661, 192.0, 254625) - 0.5) * 2.0;
        double detailWarpX = (MapBiomeSource.normalizedNoise(worldX - 149, worldZ + 277, 84.0, 89767) - 0.5) * 2.0;
        double detailWarpZ = (MapBiomeSource.normalizedNoise(worldX + 401, worldZ - 193, 84.0, 412435) - 0.5) * 2.0;
        double warpX = basePixelX + broadWarpX * 0.9 + detailWarpX * 0.35;
        double warpZ = basePixelZ + broadWarpZ * 0.9 + detailWarpZ * 0.35;
        return new PixelPoint(warpX, warpZ);
    }

    private double sampleSeaInfluence(MapImage image, double pixelX, double pixelZ, double radiusPixels) {
        int minPixelX = (int)Math.floor(pixelX - radiusPixels - 1.0);
        int maxPixelX = (int)Math.ceil(pixelX + radiusPixels + 1.0);
        int minPixelZ = (int)Math.floor(pixelZ - radiusPixels - 1.0);
        int maxPixelZ = (int)Math.ceil(pixelZ + radiusPixels + 1.0);
        double sigma = Math.max(0.9, radiusPixels * 0.7);
        double seaWeight = 0.0;
        double totalWeight = 0.0;
        for (int samplePixelX = minPixelX; samplePixelX <= maxPixelX; ++samplePixelX) {
            for (int samplePixelZ = minPixelZ; samplePixelZ <= maxPixelZ; ++samplePixelZ) {
                double dx = (double)samplePixelX + 0.5 - pixelX;
                double dz = (double)samplePixelZ + 0.5 - pixelZ;
                double distanceSquared = dx * dx + dz * dz;
                if (distanceSquared > (radiusPixels + 0.85) * (radiusPixels + 0.85)) continue;
                double weight = Math.exp(-distanceSquared / (2.0 * sigma * sigma));
                totalWeight += weight;
                if (!this.isSeaPixel(image, samplePixelX, samplePixelZ)) continue;
                seaWeight += weight;
            }
        }
        if (totalWeight <= 0.0) {
            return 0.0;
        }
        return seaWeight / totalWeight;
    }

    private boolean hasMixedSeaNeighborhood(MapImage image, int pixelX, int pixelZ, int radiusPixels) {
        if (this.fastPixelLookup != null && radiusPixels == 3) {
            return this.fastPixelLookup.hasMixedSeaNeighborhood(pixelX, pixelZ);
        }
        boolean foundSea = false;
        boolean foundLand = false;
        for (int samplePixelX = pixelX - radiusPixels; samplePixelX <= pixelX + radiusPixels; ++samplePixelX) {
            for (int samplePixelZ = pixelZ - radiusPixels; samplePixelZ <= pixelZ + radiusPixels; ++samplePixelZ) {
                if (this.isSeaPixel(image, samplePixelX, samplePixelZ)) {
                    foundSea = true;
                } else {
                    foundLand = true;
                }
                if (!foundSea || !foundLand) continue;
                return true;
            }
        }
        return false;
    }

    private CoastalBaseSample findNearestLandBiome(MapImage image, double pixelX, double pixelZ, int maxRadiusPixels) {
        CoastalBaseSample bestSample = null;
        double bestDistance = Double.POSITIVE_INFINITY;
        int centerPixelX = (int)Math.floor(pixelX);
        int centerPixelZ = (int)Math.floor(pixelZ);
        for (int samplePixelX = centerPixelX - maxRadiusPixels; samplePixelX <= centerPixelX + maxRadiusPixels; ++samplePixelX) {
            for (int samplePixelZ = centerPixelZ - maxRadiusPixels; samplePixelZ <= centerPixelZ + maxRadiusPixels; ++samplePixelZ) {
                double dz;
                double dx;
                double distanceSquared;
                class_6880<class_1959> biome;
                if (this.isSeaPixel(image, samplePixelX, samplePixelZ) || !image.contains(samplePixelX, samplePixelZ) || (biome = this.getBaseBiome(image, samplePixelX, samplePixelZ)) == this.defaultBiome || this.isSeaBiome(biome) || !((distanceSquared = (dx = (double)samplePixelX + 0.5 - pixelX) * dx + (dz = (double)samplePixelZ + 0.5 - pixelZ) * dz) < bestDistance)) continue;
                bestDistance = distanceSquared;
                bestSample = new CoastalBaseSample(biome, samplePixelX, samplePixelZ);
            }
        }
        return bestSample;
    }

    private boolean isSeaPixel(MapImage image, int pixelX, int pixelZ) {
        if (this.fastPixelLookup != null) {
            return this.fastPixelLookup.isSeaPixel(pixelX, pixelZ);
        }
        if (!image.contains(pixelX, pixelZ)) {
            return true;
        }
        if (!image.isOpaque(pixelX, pixelZ)) {
            return true;
        }
        class_6880<class_1959> biome = this.biomeByColor.get(image.rgb(pixelX, pixelZ));
        return biome == null || this.isSeaBiome(biome);
    }

    private boolean isSeaBiome(class_6880<class_1959> biome) {
        return this.biomePath(biome).equals("sea");
    }

    private String biomePath(class_6880<class_1959> biome) {
        String path = this.pathByBiome.get(biome);
        if (path != null) {
            return path;
        }
        return biome.method_40230().map(key -> key.method_29177().method_12832()).orElse("");
    }

    private class_6880<class_1959> seaBiome() {
        return this.biomeByPath.getOrDefault("sea", this.defaultBiome);
    }

    private double blocksPerPixel() {
        return (double)this.horizontalScale * 4.0;
    }

    private static double distanceToPixelSquare(double pixelX, double pixelZ, int samplePixelX, int samplePixelZ) {
        double dx = 0.0;
        if (pixelX < (double)samplePixelX) {
            dx = (double)samplePixelX - pixelX;
        } else if (pixelX > (double)samplePixelX + 1.0) {
            dx = pixelX - ((double)samplePixelX + 1.0);
        }
        double dz = 0.0;
        if (pixelZ < (double)samplePixelZ) {
            dz = (double)samplePixelZ - pixelZ;
        } else if (pixelZ > (double)samplePixelZ + 1.0) {
            dz = pixelZ - ((double)samplePixelZ + 1.0);
        }
        return Math.sqrt(dx * dx + dz * dz);
    }

    private boolean isRoadPixel(int quartX, int quartZ) {
        double pixelX = (double)quartX / (double)this.horizontalScale + (double)this.pixelOffsetX;
        double pixelZ = (double)quartZ / (double)this.horizontalScale + (double)this.pixelOffsetZ;
        return RoadMapHelper.isRoadAtPixelPosition(RoadMapHolder.IMAGE, pixelX, pixelZ, 13154156, 0.24);
    }

    public Optional<class_2338> findBiomeCenter(String biomePath) {
        MapImage image = this.mapImage();
        long sumX = 0L;
        long sumZ = 0L;
        int count = 0;
        for (int pixelX = 0; pixelX < image.width(); ++pixelX) {
            for (int pixelZ = 0; pixelZ < image.height(); ++pixelZ) {
                class_6880<class_1959> biome = this.getBaseBiome(image, pixelX, pixelZ);
                String path = this.biomePath(biome);
                if (!path.equals(biomePath)) continue;
                sumX += (long)pixelX;
                sumZ += (long)pixelZ;
                ++count;
            }
        }
        if (count == 0) {
            return Optional.empty();
        }
        int centerPixelX = Math.round((float)sumX / (float)count);
        int centerPixelZ = Math.round((float)sumZ / (float)count);
        int worldX = (centerPixelX - this.pixelOffsetX) * this.horizontalScale;
        int worldZ = (centerPixelZ - this.pixelOffsetZ) * this.horizontalScale;
        return Optional.of(new class_2338(worldX, 0, worldZ));
    }

    public List<class_2338> findSpawnCandidates(List<String> biomePaths, int maxResults) {
        MapImage image = this.mapImage();
        HashSet<String> targetPaths = new HashSet<String>(biomePaths);
        HashSet<String> targetBasePaths = new HashSet<String>();
        for (String biomePath : biomePaths) {
            targetBasePaths.add(MapBiomeSource.toBaseBiomePath(biomePath));
        }
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        for (int pixelX = 0; pixelX < image.width(); pixelX += 4) {
            for (int pixelZ = 0; pixelZ < image.height(); pixelZ += 4) {
                int quartZ;
                int quartX;
                String resolvedPath;
                class_6880<class_1959> baseBiome = this.getBaseBiome(image, pixelX, pixelZ);
                String basePath = this.biomePath(baseBiome);
                if (!targetBasePaths.contains(basePath) || !targetPaths.contains(resolvedPath = this.biomePath(this.method_38109(quartX = (pixelX - this.pixelOffsetX) * this.horizontalScale + this.horizontalScale / 2, 0, quartZ = (pixelZ - this.pixelOffsetZ) * this.horizontalScale + this.horizontalScale / 2, ZERO_SAMPLER))) && !targetBasePaths.contains(resolvedPath)) continue;
                double score = this.sameBiomeRatio(image, pixelX, pixelZ, basePath, 18);
                MapBiomeSource.insertCandidate(candidates, new Candidate(quartX * 4, quartZ * 4, score), maxResults);
            }
        }
        return candidates.stream().map(candidate -> new class_2338(candidate.worldX(), 0, candidate.worldZ())).toList();
    }

    private static void insertCandidate(List<Candidate> candidates, Candidate candidate, int maxResults) {
        int insertIndex;
        for (insertIndex = 0; insertIndex < candidates.size() && candidates.get(insertIndex).score() >= candidate.score(); ++insertIndex) {
        }
        candidates.add(insertIndex, candidate);
        if (candidates.size() > maxResults) {
            candidates.remove(candidates.size() - 1);
        }
    }

    private static String toBaseBiomePath(String biomePath) {
        String[] suffixes;
        for (String suffix : suffixes = new String[]{"_forest", "_hills", "_foothill", "_valley", "_oasis", "_steppe"}) {
            if (!biomePath.endsWith(suffix)) continue;
            return biomePath.substring(0, biomePath.length() - suffix.length());
        }
        return biomePath;
    }

    private MapImage mapImage() {
        return this.mapImage;
    }

    private static MapImage loadMapImage(class_2960 mapId) {
        try {
            return MapImage.load(mapId);
        }
        catch (RuntimeException e) {
            String resourcePath = "assets/" + mapId.method_12836() + "/" + mapId.method_12832();
            GotMod.LOGGER.error("Unable to initialize map biome source from {}", (Object)resourcePath, (Object)e);
            throw e;
        }
    }

    private static long packCoordinates(int x, int z) {
        return (long)x << 32 | (long)z & 0xFFFFFFFFL;
    }

    private static int resolvedBiomeCacheIndex(long key) {
        long mixed = key;
        mixed ^= mixed >>> 33;
        mixed *= -49064778989728563L;
        mixed ^= mixed >>> 33;
        mixed *= -4265267296055464877L;
        mixed ^= mixed >>> 33;
        return (int)mixed & 0xFFFF;
    }

    private static final class FastPixelLookup {
        private static final int UNKNOWN_PIXEL = 255;
        private static final int MAX_PALETTE_SIZE = 255;
        private static final int MIXED_SEA_RADIUS = 3;
        private static final int CACHED_RATIO_RADIUS = 10;
        private final int width;
        private final int height;
        private final class_6880<class_1959> defaultBiome;
        private final class_6880<class_1959>[] biomePalette;
        private final byte[] biomeIndexByPixel;
        private final boolean[] seaByBiomeIndex;
        private final int[] pathIdByBiomeIndex;
        private final Map<String, Integer> pathIds;
        private final long[] mixedSeaNeighborhood;
        private final AtomicIntegerArray sameBiomeCountCache;

        private FastPixelLookup(int width, int height, class_6880<class_1959> defaultBiome, class_6880<class_1959>[] biomePalette, byte[] biomeIndexByPixel, boolean[] seaByBiomeIndex, int[] pathIdByBiomeIndex, Map<String, Integer> pathIds, long[] mixedSeaNeighborhood, AtomicIntegerArray sameBiomeCountCache) {
            this.width = width;
            this.height = height;
            this.defaultBiome = defaultBiome;
            this.biomePalette = biomePalette;
            this.biomeIndexByPixel = biomeIndexByPixel;
            this.seaByBiomeIndex = seaByBiomeIndex;
            this.pathIdByBiomeIndex = pathIdByBiomeIndex;
            this.pathIds = pathIds;
            this.mixedSeaNeighborhood = mixedSeaNeighborhood;
            this.sameBiomeCountCache = sameBiomeCountCache;
        }

        private static FastPixelLookup create(MapImage image, class_6880<class_1959> defaultBiome, List<MapBiomeEntry> entries, Map<Integer, class_6880<class_1959>> biomeByColor) {
            ArrayList<class_6880<class_1959>> palette = new ArrayList<class_6880<class_1959>>();
            IdentityHashMap<class_6880<class_1959>, Integer> paletteIndices = new IdentityHashMap<class_6880<class_1959>, Integer>();
            FastPixelLookup.addToPalette(defaultBiome, palette, paletteIndices);
            for (MapBiomeEntry entry : entries) {
                FastPixelLookup.addToPalette(entry.biome(), palette, paletteIndices);
            }
            if (palette.size() > 255) {
                return null;
            }
            class_6880[] biomePalette = (class_6880[])palette.toArray(class_6880[]::new);
            HashMap<String, Integer> pathIds = new HashMap<String, Integer>();
            int[] pathIdByBiomeIndex = new int[biomePalette.length];
            boolean[] seaByBiomeIndex = new boolean[biomePalette.length];
            for (int index = 0; index < biomePalette.length; ++index) {
                int n;
                String path = biomePalette[index].method_40230().map(key -> key.method_29177().method_12832()).orElse("");
                pathIdByBiomeIndex[index] = n = pathIds.computeIfAbsent(path, ignored -> pathIds.size()).intValue();
                seaByBiomeIndex[index] = path.equals("sea");
            }
            ArrayList<ColorPaletteIndex> colors = new ArrayList<ColorPaletteIndex>(biomeByColor.size());
            for (Map.Entry entry : biomeByColor.entrySet()) {
                Integer paletteIndex = paletteIndices.get(entry.getValue());
                if (paletteIndex == null) continue;
                colors.add(new ColorPaletteIndex((Integer)entry.getKey(), paletteIndex));
            }
            colors.sort((left, right) -> Integer.compare(left.color(), right.color()));
            int[] sortedColors = new int[colors.size()];
            byte[] byArray = new byte[colors.size()];
            for (int index = 0; index < colors.size(); ++index) {
                sortedColors[index] = ((ColorPaletteIndex)colors.get(index)).color();
                byArray[index] = (byte)((ColorPaletteIndex)colors.get(index)).paletteIndex();
            }
            int width = image.width();
            int height = image.height();
            int pixelCount = Math.multiplyExact(width, height);
            byte[] biomeIndexByPixel = new byte[pixelCount];
            Arrays.fill(biomeIndexByPixel, (byte)-1);
            for (int pixelZ = 0; pixelZ < height; ++pixelZ) {
                int rowOffset = pixelZ * width;
                for (int pixelX = 0; pixelX < width; ++pixelX) {
                    int colorIndex;
                    if (!image.isOpaque(pixelX, pixelZ) || (colorIndex = Arrays.binarySearch(sortedColors, image.rgb(pixelX, pixelZ))) < 0) continue;
                    biomeIndexByPixel[rowOffset + pixelX] = byArray[colorIndex];
                }
            }
            long[] mixedSeaNeighborhood = FastPixelLookup.buildMixedSeaNeighborhood(width, height, biomeIndexByPixel, seaByBiomeIndex);
            int[] uncachedSameBiomeCounts = new int[pixelCount + 3 >>> 2];
            Arrays.fill(uncachedSameBiomeCounts, -1);
            AtomicIntegerArray sameBiomeCountCache = new AtomicIntegerArray(uncachedSameBiomeCounts);
            return new FastPixelLookup(width, height, defaultBiome, biomePalette, biomeIndexByPixel, seaByBiomeIndex, pathIdByBiomeIndex, Map.copyOf(pathIds), mixedSeaNeighborhood, sameBiomeCountCache);
        }

        private class_6880<class_1959> baseBiome(int pixelX, int pixelZ) {
            int biomeIndex = this.biomeIndex(pixelX, pixelZ);
            return biomeIndex == 255 ? this.defaultBiome : this.biomePalette[biomeIndex];
        }

        private boolean isSeaPixel(int pixelX, int pixelZ) {
            int biomeIndex = this.biomeIndex(pixelX, pixelZ);
            return biomeIndex == 255 || this.seaByBiomeIndex[biomeIndex];
        }

        private boolean hasMixedSeaNeighborhood(int pixelX, int pixelZ) {
            if (!this.contains(pixelX, pixelZ)) {
                return false;
            }
            int pixelIndex = this.pixelIndex(pixelX, pixelZ);
            return (this.mixedSeaNeighborhood[pixelIndex >>> 6] & 1L << (pixelIndex & 0x3F)) != 0L;
        }

        private double sameBiomeRatio(int pixelX, int pixelZ, String basePath, int radius) {
            Integer requestedPathId = this.pathIds.get(basePath);
            if (requestedPathId == null) {
                return 0.0;
            }
            int step = Math.max(2, radius / 3);
            int total = FastPixelLookup.sampleCount(pixelX, this.width, radius, step) * FastPixelLookup.sampleCount(pixelZ, this.height, radius, step);
            if (total == 0) {
                return 0.0;
            }
            int centerBiomeIndex = this.biomeIndex(pixelX, pixelZ);
            int centerPathId = this.pathId(centerBiomeIndex);
            if (radius == 10 && this.contains(pixelX, pixelZ) && requestedPathId == centerPathId) {
                int pixelIndex = this.pixelIndex(pixelX, pixelZ);
                int same = this.cachedSameBiomeCount(pixelIndex);
                if (same == 255) {
                    same = this.countSameBiomeSamples(pixelX, pixelZ, requestedPathId, radius, step);
                    this.cacheSameBiomeCount(pixelIndex, same);
                }
                return (double)same / (double)total;
            }
            int same = this.countSameBiomeSamples(pixelX, pixelZ, requestedPathId, radius, step);
            return (double)same / (double)total;
        }

        private int countSameBiomeSamples(int pixelX, int pixelZ, int requestedPathId, int radius, int step) {
            int same = 0;
            for (int dx = -radius; dx <= radius; dx += step) {
                int sampleX = pixelX + dx;
                if (sampleX < 0 || sampleX >= this.width) continue;
                for (int dz = -radius; dz <= radius; dz += step) {
                    int sampleZ = pixelZ + dz;
                    if (sampleZ < 0 || sampleZ >= this.height || this.pathId(this.biomeIndex(sampleX, sampleZ)) != requestedPathId) continue;
                    ++same;
                }
            }
            return same;
        }

        private int cachedSameBiomeCount(int pixelIndex) {
            int packed = this.sameBiomeCountCache.get(pixelIndex >>> 2);
            return packed >>> ((pixelIndex & 3) << 3) & 0xFF;
        }

        private void cacheSameBiomeCount(int pixelIndex, int same) {
            int packedIndex = pixelIndex >>> 2;
            int shift = (pixelIndex & 3) << 3;
            int mask = 255 << shift;
            int packed = this.sameBiomeCountCache.get(packedIndex);
            while ((packed >>> shift & 0xFF) == 255) {
                int updated = packed & ~mask | same << shift;
                if (this.sameBiomeCountCache.compareAndSet(packedIndex, packed, updated)) {
                    return;
                }
                packed = this.sameBiomeCountCache.get(packedIndex);
            }
        }

        private int pathId(int biomeIndex) {
            return biomeIndex == 255 ? this.pathIdByBiomeIndex[0] : this.pathIdByBiomeIndex[biomeIndex];
        }

        private int biomeIndex(int pixelX, int pixelZ) {
            if (!this.contains(pixelX, pixelZ)) {
                return 255;
            }
            return Byte.toUnsignedInt(this.biomeIndexByPixel[this.pixelIndex(pixelX, pixelZ)]);
        }

        private boolean contains(int pixelX, int pixelZ) {
            return pixelX >= 0 && pixelZ >= 0 && pixelX < this.width && pixelZ < this.height;
        }

        private int pixelIndex(int pixelX, int pixelZ) {
            return pixelZ * this.width + pixelX;
        }

        private static int sampleCount(int center, int limit, int radius, int step) {
            int count = 0;
            for (int delta = -radius; delta <= radius; delta += step) {
                int sample = center + delta;
                if (sample < 0 || sample >= limit) continue;
                ++count;
            }
            return count;
        }

        private static void addToPalette(class_6880<class_1959> biome, List<class_6880<class_1959>> palette, IdentityHashMap<class_6880<class_1959>, Integer> paletteIndices) {
            if (!paletteIndices.containsKey(biome)) {
                paletteIndices.put(biome, palette.size());
                palette.add(biome);
            }
        }

        private static long[] buildMixedSeaNeighborhood(int width, int height, byte[] biomeIndexByPixel, boolean[] seaByBiomeIndex) {
            int pixelCount = Math.multiplyExact(width, height);
            long[] mixed = new long[pixelCount + 63 >>> 6];
            int[] verticalLandCounts = new int[width];
            for (int sampleZ = 0; sampleZ <= 3 && sampleZ < height; ++sampleZ) {
                FastPixelLookup.updateLandCounts(verticalLandCounts, width, biomeIndexByPixel, seaByBiomeIndex, sampleZ, 1);
            }
            int neighborhoodSize = 49;
            for (int pixelZ = 0; pixelZ < height; ++pixelZ) {
                if (pixelZ > 0) {
                    int removedZ = pixelZ - 3 - 1;
                    int addedZ = pixelZ + 3;
                    if (removedZ >= 0) {
                        FastPixelLookup.updateLandCounts(verticalLandCounts, width, biomeIndexByPixel, seaByBiomeIndex, removedZ, -1);
                    }
                    if (addedZ < height) {
                        FastPixelLookup.updateLandCounts(verticalLandCounts, width, biomeIndexByPixel, seaByBiomeIndex, addedZ, 1);
                    }
                }
                int landCount = 0;
                for (int sampleX = 0; sampleX <= 3 && sampleX < width; ++sampleX) {
                    landCount += verticalLandCounts[sampleX];
                }
                for (int pixelX = 0; pixelX < width; ++pixelX) {
                    if (pixelX > 0) {
                        int removedX = pixelX - 3 - 1;
                        int addedX = pixelX + 3;
                        if (removedX >= 0) {
                            landCount -= verticalLandCounts[removedX];
                        }
                        if (addedX < width) {
                            landCount += verticalLandCounts[addedX];
                        }
                    }
                    if (landCount <= 0 || landCount >= neighborhoodSize) continue;
                    int pixelIndex = pixelZ * width + pixelX;
                    int n = pixelIndex >>> 6;
                    mixed[n] = mixed[n] | 1L << (pixelIndex & 0x3F);
                }
            }
            return mixed;
        }

        private static void updateLandCounts(int[] verticalLandCounts, int width, byte[] biomeIndexByPixel, boolean[] seaByBiomeIndex, int pixelZ, int delta) {
            int rowOffset = pixelZ * width;
            for (int pixelX = 0; pixelX < width; ++pixelX) {
                boolean sea;
                int biomeIndex = Byte.toUnsignedInt(biomeIndexByPixel[rowOffset + pixelX]);
                boolean bl = sea = biomeIndex == 255 || seaByBiomeIndex[biomeIndex];
                if (sea) continue;
                int n = pixelX;
                verticalLandCounts[n] = verticalLandCounts[n] + delta;
            }
        }

        private record ColorPaletteIndex(int color, int paletteIndex) {
        }
    }

    private record CachedBiome(long key, class_6880<class_1959> biome) {
    }

    private record CoastalBaseSample(class_6880<class_1959> biome, int pixelX, int pixelZ) {
    }

    private record PixelPoint(double x, double z) {
    }

    private static final class RoadMapHolder {
        private static final MapImage IMAGE = MapImage.load(ROAD_MAP_ID);

        private RoadMapHolder() {
        }
    }

    private record Candidate(int worldX, int worldZ, double score) {
    }

    private static final class MultiNoiseSamplerHolder {
        private static final class_6910 ZERO = class_6916.method_40480((double)0.0);
        private static final class_6544.class_6552 INSTANCE = new class_6544.class_6552(ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, List.of());

        private MultiNoiseSamplerHolder() {
        }
    }
}

