/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
 *  net.fabricmc.fabric.api.resource.ResourceManagerHelper
 *  net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
 *  net.minecraft.class_1011
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3264
 *  net.minecraft.class_3298
 *  net.minecraft.class_3300
 *  net.minecraft.class_3532
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.map;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.class_1011;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3264;
import net.minecraft.class_3298;
import net.minecraft.class_3300;
import net.minecraft.class_3532;
import org.jetbrains.annotations.Nullable;
import trep.got.GotMod;
import trep.got.client.map.CastleTerritoryClientState;
import trep.got.client.map.DenseOwnerEncoding;
import trep.got.client.map.KingdomAreaClientState;
import trep.got.client.map.KingdomTerritoryClientState;
import trep.got.client.map.OwnerEncoding;
import trep.got.client.map.TerritoryOwnershipRaster;
import trep.got.network.CastleMapPayload;

@Environment(value=EnvType.CLIENT)
final class TerritoryOwnershipClientCache {
    private static final class_2960 MAP_AREAS_TEXTURE = class_2960.method_60655((String)"agob", (String)"map_areas.png");
    private static final class_2960 RELOAD_ID = class_2960.method_60655((String)"agob", (String)"territory_ownership_cache");
    private static final int SEARCH_RADIUS = 96;
    private static OwnershipKey cachedKey;
    private static TerritoryOwnershipRaster cachedRaster;
    private static int resourceGeneration;
    private static boolean initialized;

    private TerritoryOwnershipClientCache() {
    }

    static void initialize() {
        if (initialized) {
            return;
        }
        initialized = true;
        ResourceManagerHelper.get((class_3264)class_3264.field_14188).registerReloadListener((IdentifiableResourceReloadListener)new SimpleSynchronousResourceReloadListener(){

            public class_2960 getFabricId() {
                return RELOAD_ID;
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public void method_14491(class_3300 manager) {
                Class<TerritoryOwnershipClientCache> clazz = TerritoryOwnershipClientCache.class;
                synchronized (TerritoryOwnershipClientCache.class) {
                    ++resourceGeneration;
                    cachedKey = null;
                    cachedRaster = null;
                    // ** MonitorExit[var2_2] (shouldn't be in output)
                    class_310 client = class_310.method_1551();
                    client.execute(() -> {
                        CastleTerritoryClientState.clear(client);
                        KingdomTerritoryClientState.clear(client);
                        KingdomAreaClientState.clear();
                    });
                    return;
                }
            }
        });
    }

    @Nullable
    static synchronized TerritoryOwnershipRaster getOrBuild(class_310 client, List<CastleMapPayload.Entry> castles) {
        OwnershipKey key;
        if (client == null || castles == null || castles.isEmpty()) {
            cachedKey = null;
            cachedRaster = null;
            return null;
        }
        if (cachedKey != null && cachedKey.matches(resourceGeneration, castles)) {
            return cachedRaster;
        }
        cachedKey = key = OwnershipKey.of(resourceGeneration, castles);
        cachedRaster = null;
        try (class_1011 areas = TerritoryOwnershipClientCache.loadAreasImage(client);){
            cachedRaster = TerritoryOwnershipClientCache.build(castles, areas);
        }
        catch (IOException exception) {
            cachedKey = null;
            GotMod.LOGGER.warn("Failed to rebuild shared client territory ownership raster", (Throwable)exception);
        }
        return cachedRaster;
    }

    static synchronized void clear() {
        cachedKey = null;
        cachedRaster = null;
    }

    private static class_1011 loadAreasImage(class_310 client) throws IOException {
        class_3298 resource = client.method_1478().getResourceOrThrow(MAP_AREAS_TEXTURE);
        try (InputStream stream = resource.method_14482();){
            class_1011 class_10112 = class_1011.method_4309((InputStream)stream);
            return class_10112;
        }
    }

    private static TerritoryOwnershipRaster build(List<CastleMapPayload.Entry> castles, class_1011 areas) {
        if (castles.size() > Short.MAX_VALUE) {
            throw new IllegalStateException("Territory ownership palette exceeds 32767 castles");
        }
        List<CastleMapPayload.Entry> palette = List.copyOf(castles);
        ModeSeeds areaSeeds = new ModeSeeds();
        ModeSeeds castleSeeds = new ModeSeeds();
        ModeSeeds kingdomSeeds = new ModeSeeds();
        boolean areaMatchesCastle = true;
        for (int ownerIndex = 0; ownerIndex < palette.size(); ++ownerIndex) {
            PixelMatch kingdomMatch;
            CastleMapPayload.Entry castle = palette.get(ownerIndex);
            short encodedOwner = (short)ownerIndex;
            PixelMatch areaMatch = TerritoryOwnershipClientCache.findNearestLandPixel(areas, TerritoryOwnershipClientCache.worldToCastlePixel(castle.worldX(), areas.method_4307()), TerritoryOwnershipClientCache.worldToCastlePixel(castle.worldZ(), areas.method_4323()), true);
            if (areaMatch != null) {
                Seed seed = new Seed(castle, encodedOwner, areaMatch.x(), areaMatch.z(), areaMatch.color());
                areaSeeds.add(seed, areas.method_4307());
                if (!castle.originalKingdomId().isBlank()) {
                    castleSeeds.add(seed, areas.method_4307());
                } else {
                    areaMatchesCastle = false;
                }
            }
            if (castle.originalKingdomId().isBlank() || (kingdomMatch = TerritoryOwnershipClientCache.findNearestLandPixel(areas, TerritoryOwnershipClientCache.worldToKingdomPixel(castle.worldX(), areas.method_4307()), TerritoryOwnershipClientCache.worldToKingdomPixel(castle.worldZ(), areas.method_4323()), false)) == null) continue;
            kingdomSeeds.add(new Seed(castle, encodedOwner, kingdomMatch.x(), kingdomMatch.z(), kingdomMatch.color()), areas.method_4307());
        }
        int pixelCount = Math.multiplyExact(areas.method_4307(), areas.method_4323());
        short[] castleOwners = TerritoryOwnershipClientCache.emptyOwners(pixelCount);
        short[] areaOwners = areaMatchesCastle ? castleOwners : TerritoryOwnershipClientCache.emptyOwners(pixelCount);
        short[] kingdomOwners = TerritoryOwnershipClientCache.emptyOwners(pixelCount);
        boolean[] visited = new boolean[pixelCount];
        PrimitiveIntList componentPixels = new PrimitiveIntList();
        for (int z = 0; z < areas.method_4323(); ++z) {
            for (int x = 0; x < areas.method_4307(); ++x) {
                int startIndex = TerritoryOwnershipClientCache.indexOf(x, z, areas.method_4307());
                if (visited[startIndex] || !TerritoryOwnershipClientCache.isCastleLandPixel(areas.method_4315(x, z))) continue;
                TerritoryOwnershipClientCache.fillComponent(areas, visited, componentPixels, areaMatchesCastle ? null : areaSeeds, castleSeeds, kingdomSeeds, areaOwners, castleOwners, kingdomOwners, x, z);
            }
        }
        int[] castleColors = TerritoryOwnershipClientCache.resolveOwnerColors(palette.size(), castleSeeds);
        int[] kingdomColors = TerritoryOwnershipClientCache.resolveOwnerColors(palette.size(), kingdomSeeds);
        DenseOwnerEncoding castleEncoding = new DenseOwnerEncoding(castleOwners);
        DenseOwnerEncoding areaEncoding = areaMatchesCastle ? castleEncoding : OwnerEncoding.compact(areaOwners, castleOwners, castleEncoding);
        OwnerEncoding kingdomEncoding = OwnerEncoding.compact(kingdomOwners, castleOwners, castleEncoding);
        return new TerritoryOwnershipRaster(areas.method_4307(), areas.method_4323(), palette, castleEncoding, areaEncoding, kingdomEncoding, castleColors, kingdomColors);
    }

    private static void fillComponent(class_1011 areas, boolean[] visited, PrimitiveIntList pixels, @Nullable ModeSeeds areaSeeds, ModeSeeds castleSeeds, ModeSeeds kingdomSeeds, short[] areaOwners, short[] castleOwners, short[] kingdomOwners, int startX, int startZ) {
        int width = areas.method_4307();
        int height = areas.method_4323();
        int regionColor = TerritoryOwnershipClientCache.rgb(areas.method_4315(startX, startZ));
        pixels.clear();
        pixels.add(TerritoryOwnershipClientCache.indexOf(startX, startZ, width));
        visited[TerritoryOwnershipClientCache.indexOf((int)startX, (int)startZ, (int)width)] = true;
        LinkedHashMap<String, Seed> componentAreaSeeds = areaSeeds == null ? null : new LinkedHashMap<String, Seed>();
        LinkedHashMap<String, Seed> componentCastleSeeds = new LinkedHashMap<String, Seed>();
        LinkedHashMap<String, Seed> componentKingdomSeeds = regionColor == 0 ? null : new LinkedHashMap<String, Seed>();
        for (int queueIndex = 0; queueIndex < pixels.size(); ++queueIndex) {
            int pixelIndex = pixels.get(queueIndex);
            if (areaSeeds != null) {
                areaSeeds.collectAt(pixelIndex, componentAreaSeeds);
            }
            castleSeeds.collectAt(pixelIndex, componentCastleSeeds);
            if (componentKingdomSeeds != null) {
                kingdomSeeds.collectAt(pixelIndex, componentKingdomSeeds);
            }
            int x = pixelIndex % width;
            int z = pixelIndex / width;
            TerritoryOwnershipClientCache.visitNeighbor(areas, visited, pixels, width, height, x + 1, z, regionColor);
            TerritoryOwnershipClientCache.visitNeighbor(areas, visited, pixels, width, height, x - 1, z, regionColor);
            TerritoryOwnershipClientCache.visitNeighbor(areas, visited, pixels, width, height, x, z + 1, regionColor);
            TerritoryOwnershipClientCache.visitNeighbor(areas, visited, pixels, width, height, x, z - 1, regionColor);
        }
        if (areaSeeds != null) {
            TerritoryOwnershipClientCache.assignOwners(areaOwners, pixels, width, areaSeeds.candidates(componentAreaSeeds, regionColor));
        }
        TerritoryOwnershipClientCache.assignOwners(castleOwners, pixels, width, castleSeeds.candidates(componentCastleSeeds, regionColor));
        if (componentKingdomSeeds != null) {
            TerritoryOwnershipClientCache.assignOwners(kingdomOwners, pixels, width, kingdomSeeds.candidates(componentKingdomSeeds, regionColor));
        }
    }

    private static void assignOwners(short[] owners, PrimitiveIntList pixels, int width, List<Seed> candidates) {
        if (candidates.isEmpty()) {
            return;
        }
        if (candidates.size() == 1) {
            short owner = candidates.getFirst().ownerIndex();
            for (int index = 0; index < pixels.size(); ++index) {
                owners[pixels.get((int)index)] = owner;
            }
            return;
        }
        for (int index = 0; index < pixels.size(); ++index) {
            int pixel = pixels.get(index);
            owners[pixel] = TerritoryOwnershipClientCache.nearestSeed(candidates, pixel % width, pixel / width).ownerIndex();
        }
    }

    private static int[] resolveOwnerColors(int paletteSize, ModeSeeds seeds) {
        LinkedHashMap<String, Map> colorCountsByKingdom = new LinkedHashMap<String, Map>();
        for (Seed seed : seeds.ordered()) {
            colorCountsByKingdom.computeIfAbsent(seed.entry().originalKingdomId(), ignored -> new HashMap()).merge(seed.regionColor(), 1, Integer::sum);
        }
        HashMap<String, Integer> kingdomColors = new HashMap<String, Integer>();
        for (Map.Entry entry : colorCountsByKingdom.entrySet()) {
            int color = ((Map)entry.getValue()).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(0xFFFFFF);
            kingdomColors.put((String)entry.getKey(), color);
        }
        int[] nArray = new int[paletteSize];
        for (Seed seed : seeds.ordered()) {
            nArray[seed.ownerIndex()] = kingdomColors.getOrDefault(seed.entry().kingdomId(), kingdomColors.getOrDefault(seed.entry().originalKingdomId(), seed.regionColor()));
        }
        return nArray;
    }

    private static void visitNeighbor(class_1011 areas, boolean[] visited, PrimitiveIntList queue, int width, int height, int x, int z, int regionColor) {
        if (x < 0 || z < 0 || x >= width || z >= height) {
            return;
        }
        int index = TerritoryOwnershipClientCache.indexOf(x, z, width);
        if (visited[index]) {
            return;
        }
        int color = areas.method_4315(x, z);
        if (!TerritoryOwnershipClientCache.isCastleLandPixel(color) || TerritoryOwnershipClientCache.rgb(color) != regionColor) {
            return;
        }
        visited[index] = true;
        queue.add(index);
    }

    @Nullable
    private static PixelMatch findNearestLandPixel(class_1011 image, int centerX, int centerZ, boolean includeBlack) {
        int centerColor = image.method_4315(centerX, centerZ);
        if (TerritoryOwnershipClientCache.isLandPixel(centerColor, includeBlack)) {
            return new PixelMatch(centerX, centerZ, TerritoryOwnershipClientCache.rgb(centerColor));
        }
        int width = image.method_4307();
        int height = image.method_4323();
        for (int radius = 1; radius <= 96; ++radius) {
            int maxZ;
            int minZ;
            int maxX;
            int minX = Math.max(0, centerX - radius);
            PixelMatch match = TerritoryOwnershipClientCache.scanRing(image, centerX, centerZ, minX, maxX = Math.min(width - 1, centerX + radius), minZ = Math.max(0, centerZ - radius), maxZ = Math.min(height - 1, centerZ + radius), includeBlack);
            if (match == null) continue;
            return match;
        }
        return null;
    }

    @Nullable
    private static PixelMatch scanRing(class_1011 image, int centerX, int centerZ, int minX, int maxX, int minZ, int maxZ, boolean includeBlack) {
        PixelMatch best = null;
        long bestDistance = Long.MAX_VALUE;
        for (int x = minX; x <= maxX; ++x) {
            if ((best = TerritoryOwnershipClientCache.chooseCloserMatch(image, centerX, centerZ, x, minZ, best, bestDistance, includeBlack)) != null) {
                bestDistance = TerritoryOwnershipClientCache.squaredDistance(best.x(), best.z(), centerX, centerZ);
            }
            if ((best = TerritoryOwnershipClientCache.chooseCloserMatch(image, centerX, centerZ, x, maxZ, best, bestDistance, includeBlack)) == null) continue;
            bestDistance = TerritoryOwnershipClientCache.squaredDistance(best.x(), best.z(), centerX, centerZ);
        }
        for (int z = minZ + 1; z < maxZ; ++z) {
            if ((best = TerritoryOwnershipClientCache.chooseCloserMatch(image, centerX, centerZ, minX, z, best, bestDistance, includeBlack)) != null) {
                bestDistance = TerritoryOwnershipClientCache.squaredDistance(best.x(), best.z(), centerX, centerZ);
            }
            if ((best = TerritoryOwnershipClientCache.chooseCloserMatch(image, centerX, centerZ, maxX, z, best, bestDistance, includeBlack)) == null) continue;
            bestDistance = TerritoryOwnershipClientCache.squaredDistance(best.x(), best.z(), centerX, centerZ);
        }
        return best;
    }

    @Nullable
    private static PixelMatch chooseCloserMatch(class_1011 image, int centerX, int centerZ, int x, int z, @Nullable PixelMatch currentBest, long currentBestDistance, boolean includeBlack) {
        int color = image.method_4315(x, z);
        if (!TerritoryOwnershipClientCache.isLandPixel(color, includeBlack)) {
            return currentBest;
        }
        long distance = TerritoryOwnershipClientCache.squaredDistance(x, z, centerX, centerZ);
        if (currentBest == null || distance < currentBestDistance) {
            return new PixelMatch(x, z, TerritoryOwnershipClientCache.rgb(color));
        }
        return currentBest;
    }

    private static Seed nearestSeed(List<Seed> seeds, int pixelX, int pixelZ) {
        Seed closest = seeds.getFirst();
        long bestDistance = TerritoryOwnershipClientCache.squaredDistance(closest, pixelX, pixelZ);
        for (int index = 1; index < seeds.size(); ++index) {
            Seed candidate = seeds.get(index);
            long distance = TerritoryOwnershipClientCache.squaredDistance(candidate, pixelX, pixelZ);
            if (distance >= bestDistance) continue;
            bestDistance = distance;
            closest = candidate;
        }
        return closest;
    }

    private static long squaredDistance(Seed seed, int pixelX, int pixelZ) {
        return TerritoryOwnershipClientCache.squaredDistance(seed.pixelX(), seed.pixelZ(), pixelX, pixelZ);
    }

    private static long squaredDistance(int x1, int z1, int x2, int z2) {
        long dx = x1 - x2;
        long dz = z1 - z2;
        return dx * dx + dz * dz;
    }

    private static int worldToCastlePixel(int worldCoordinate, int imageSize) {
        double mapPixel = (double)(worldCoordinate + 48000) / 32.0;
        double scaled = mapPixel * ((double)imageSize / 3000.0);
        return class_3532.method_15340((int)((int)Math.floor(scaled)), (int)0, (int)(imageSize - 1));
    }

    private static int worldToKingdomPixel(int worldCoordinate, int imageSize) {
        double normalized = (double)(worldCoordinate + 48000) / 96000.0;
        return Math.max(0, Math.min(imageSize - 1, (int)Math.round(normalized * (double)(imageSize - 1))));
    }

    private static boolean isCastleLandPixel(int argb) {
        return (argb >>> 24 & 0xFF) > 0;
    }

    private static boolean isLandPixel(int argb, boolean includeBlack) {
        return TerritoryOwnershipClientCache.isCastleLandPixel(argb) && (includeBlack || TerritoryOwnershipClientCache.rgb(argb) != 0);
    }

    private static int rgb(int argb) {
        return argb & 0xFFFFFF;
    }

    private static int indexOf(int x, int z, int width) {
        return z * width + x;
    }

    private static short[] emptyOwners(int size) {
        short[] values = new short[size];
        Arrays.fill(values, (short)-1);
        return values;
    }

    @Environment(value=EnvType.CLIENT)
    private record OwnershipKey(int resourceGeneration, List<SeedKey> seeds) {
        private static OwnershipKey of(int resourceGeneration, List<CastleMapPayload.Entry> castles) {
            return new OwnershipKey(resourceGeneration, castles.stream().map(SeedKey::of).toList());
        }

        private boolean matches(int resourceGeneration, List<CastleMapPayload.Entry> castles) {
            if (this.resourceGeneration != resourceGeneration || this.seeds.size() != castles.size()) {
                return false;
            }
            for (int index = 0; index < this.seeds.size(); ++index) {
                if (this.seeds.get(index).matches(castles.get(index))) continue;
                return false;
            }
            return true;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private static final class ModeSeeds {
        private final List<Seed> ordered = new ArrayList<Seed>();
        private final Map<Integer, List<Seed>> byPixel = new HashMap<Integer, List<Seed>>();
        private final Map<Integer, List<Seed>> byRegion = new HashMap<Integer, List<Seed>>();

        private ModeSeeds() {
        }

        private void add(Seed seed, int width) {
            this.ordered.add(seed);
            this.byPixel.computeIfAbsent(TerritoryOwnershipClientCache.indexOf(seed.pixelX(), seed.pixelZ(), width), ignored -> new ArrayList()).add(seed);
            this.byRegion.computeIfAbsent(seed.regionColor(), ignored -> new ArrayList()).add(seed);
        }

        private void collectAt(int pixelIndex, Map<String, Seed> componentSeeds) {
            List<Seed> seeds = this.byPixel.get(pixelIndex);
            if (seeds == null) {
                return;
            }
            for (Seed seed : seeds) {
                componentSeeds.putIfAbsent(seed.entry().id(), seed);
            }
        }

        private List<Seed> candidates(Map<String, Seed> componentSeeds, int regionColor) {
            return componentSeeds.isEmpty() ? this.byRegion.getOrDefault(regionColor, List.of()) : List.copyOf(componentSeeds.values());
        }

        private List<Seed> ordered() {
            return this.ordered;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record PixelMatch(int x, int z, int color) {
    }

    @Environment(value=EnvType.CLIENT)
    private record Seed(CastleMapPayload.Entry entry, short ownerIndex, int pixelX, int pixelZ, int regionColor) {
    }

    @Environment(value=EnvType.CLIENT)
    private static final class PrimitiveIntList {
        private int[] values = new int[1024];
        private int size;

        private PrimitiveIntList() {
        }

        private void clear() {
            this.size = 0;
        }

        private void add(int value) {
            if (this.size == this.values.length) {
                this.values = Arrays.copyOf(this.values, this.values.length * 2);
            }
            this.values[this.size++] = value;
        }

        private int get(int index) {
            return this.values[index];
        }

        private int size() {
            return this.size;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record SeedKey(String id, int worldX, int worldZ, String kingdomId, String originalKingdomId, String name, String houseId) {
        private static SeedKey of(CastleMapPayload.Entry entry) {
            return new SeedKey(entry.id(), entry.worldX(), entry.worldZ(), entry.kingdomId(), entry.originalKingdomId(), entry.name(), entry.houseId());
        }

        private boolean matches(CastleMapPayload.Entry entry) {
            return this.worldX == entry.worldX() && this.worldZ == entry.worldZ() && this.id.equals(entry.id()) && this.kingdomId.equals(entry.kingdomId()) && this.originalKingdomId.equals(entry.originalKingdomId()) && this.name.equals(entry.name()) && this.houseId.equals(entry.houseId());
        }
    }
}

