/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_3218
 *  net.minecraft.class_3532
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.castle;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3532;
import org.jetbrains.annotations.Nullable;
import trep.got.GotMod;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;

public final class CastleAreaOwnershipResolver {
    private static final String MAP_AREAS_RESOURCE = "assets/agob/map_areas.png";
    private static final int SEARCH_RADIUS = 96;
    private static CachedOwnership cachedOwnership;

    private CastleAreaOwnershipResolver() {
    }

    @Nullable
    public static CastleInstance findCastleAt(class_3218 world, class_2338 pos) {
        CachedOwnership ownership = CastleAreaOwnershipResolver.getOrBuild(world);
        if (ownership == null) {
            return null;
        }
        return ownership.findCastleAt(pos);
    }

    public static boolean isOwnedArea(class_3218 world, class_2338 pos) {
        return CastleAreaOwnershipResolver.findCastleAt(world, pos) != null;
    }

    @Nullable
    private static CachedOwnership getOrBuild(class_3218 world) {
        CachedOwnership rebuilt;
        CastleWorldState state = CastleWorldState.get(world);
        int signature = CastleAreaOwnershipResolver.computeSignature(state.getAll());
        if (cachedOwnership != null && CastleAreaOwnershipResolver.cachedOwnership.signature == signature) {
            return cachedOwnership;
        }
        BufferedImage areasImage = CastleAreaOwnershipResolver.loadAreasImage();
        if (areasImage == null) {
            return null;
        }
        cachedOwnership = rebuilt = CastleAreaOwnershipResolver.rebuild(state.getAll(), areasImage, signature);
        return rebuilt;
    }

    private static int computeSignature(Collection<CastleInstance> castles) {
        int hash = 1;
        for (CastleInstance castle : castles) {
            hash = 31 * hash + castle.getId().hashCode();
            hash = 31 * hash + castle.getBannerPos().method_10263();
            hash = 31 * hash + castle.getBannerPos().method_10260();
            hash = 31 * hash + castle.getOwnerKingdomId().hashCode();
        }
        return hash;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Nullable
    private static BufferedImage loadAreasImage() {
        try (InputStream stream = CastleAreaOwnershipResolver.class.getClassLoader().getResourceAsStream(MAP_AREAS_RESOURCE);){
            if (stream == null) {
                GotMod.LOGGER.warn("Failed to load castle ownership map resource {}", (Object)MAP_AREAS_RESOURCE);
                BufferedImage bufferedImage2 = null;
                return bufferedImage2;
            }
            BufferedImage bufferedImage = ImageIO.read(stream);
            return bufferedImage;
        }
        catch (IOException exception) {
            GotMod.LOGGER.warn("Failed to decode castle ownership map resource {}", (Object)MAP_AREAS_RESOURCE, (Object)exception);
            return null;
        }
    }

    private static CachedOwnership rebuild(Collection<CastleInstance> castles, BufferedImage areasImage, int signature) {
        List<CastleSeed> seeds = CastleAreaOwnershipResolver.buildSeeds(castles, areasImage);
        int width = areasImage.getWidth();
        int height = areasImage.getHeight();
        short[] ownershipByPixel = new short[width * height];
        Arrays.fill(ownershipByPixel, (short)-1);
        if (seeds.isEmpty()) {
            return new CachedOwnership(signature, width, height, ownershipByPixel, List.of());
        }
        boolean[] visited = new boolean[width * height];
        Map<Integer, List<CastleSeed>> seedsByPixel = CastleAreaOwnershipResolver.indexSeedsByPixel(seeds, width);
        Map<Integer, List<CastleSeed>> seedsByRegion = CastleAreaOwnershipResolver.indexSeedsByRegion(seeds);
        for (int z = 0; z < height; ++z) {
            for (int x = 0; x < width; ++x) {
                int startIndex = CastleAreaOwnershipResolver.indexOf(x, z, width);
                int color = areasImage.getRGB(x, z);
                if (visited[startIndex] || !CastleAreaOwnershipResolver.isLandPixel(color)) continue;
                CastleAreaOwnershipResolver.fillComponent(areasImage, ownershipByPixel, visited, seedsByPixel, seedsByRegion, x, z, width, height);
            }
        }
        List<CastleInstance> castlesByIndex = seeds.stream().map(CastleSeed::castle).toList();
        return new CachedOwnership(signature, width, height, ownershipByPixel, castlesByIndex);
    }

    private static List<CastleSeed> buildSeeds(Collection<CastleInstance> castles, BufferedImage areasImage) {
        ArrayList<CastleSeed> seeds = new ArrayList<CastleSeed>();
        short castleIndex = 0;
        for (CastleInstance castle : castles) {
            int initialZ;
            int initialX = CastleAreaOwnershipResolver.worldToPixel(castle.getBannerPos().method_10263(), areasImage.getWidth());
            PixelMatch match = CastleAreaOwnershipResolver.findNearestLandPixel(areasImage, initialX, initialZ = CastleAreaOwnershipResolver.worldToPixel(castle.getBannerPos().method_10260(), areasImage.getHeight()));
            if (match == null) continue;
            short s = castleIndex;
            castleIndex = (short)(castleIndex + 1);
            seeds.add(new CastleSeed(castle, s, match.x(), match.z(), match.color()));
        }
        return List.copyOf(seeds);
    }

    private static void fillComponent(BufferedImage areasImage, short[] ownershipByPixel, boolean[] visited, Map<Integer, List<CastleSeed>> seedsByPixel, Map<Integer, List<CastleSeed>> seedsByRegion, int startX, int startZ, int width, int height) {
        List<CastleSeed> candidates;
        int regionColor = CastleAreaOwnershipResolver.rgb(areasImage.getRGB(startX, startZ));
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        ArrayList<Integer> pixels = new ArrayList<Integer>();
        LinkedHashMap<String, CastleSeed> componentSeeds = new LinkedHashMap<String, CastleSeed>();
        int startIndex = CastleAreaOwnershipResolver.indexOf(startX, startZ, width);
        queue.add(startIndex);
        visited[startIndex] = true;
        while (!queue.isEmpty()) {
            int pixelIndex = (Integer)queue.removeFirst();
            pixels.add(pixelIndex);
            List<CastleSeed> seedsAtPixel = seedsByPixel.get(pixelIndex);
            if (seedsAtPixel != null) {
                for (CastleSeed seed : seedsAtPixel) {
                    componentSeeds.putIfAbsent(seed.castle().getId(), seed);
                }
            }
            int x = pixelIndex % width;
            int z = pixelIndex / width;
            CastleAreaOwnershipResolver.visitNeighbor(areasImage, visited, queue, width, height, x + 1, z, regionColor);
            CastleAreaOwnershipResolver.visitNeighbor(areasImage, visited, queue, width, height, x - 1, z, regionColor);
            CastleAreaOwnershipResolver.visitNeighbor(areasImage, visited, queue, width, height, x, z + 1, regionColor);
            CastleAreaOwnershipResolver.visitNeighbor(areasImage, visited, queue, width, height, x, z - 1, regionColor);
        }
        List<CastleSeed> list = candidates = componentSeeds.isEmpty() ? seedsByRegion.getOrDefault(regionColor, List.of()) : List.copyOf(componentSeeds.values());
        if (candidates.isEmpty()) {
            return;
        }
        if (candidates.size() == 1) {
            short ownerIndex = ((CastleSeed)candidates.getFirst()).castleIndex();
            Iterator x = pixels.iterator();
            while (x.hasNext()) {
                int pixelIndex = (Integer)x.next();
                ownershipByPixel[pixelIndex] = ownerIndex;
            }
            return;
        }
        Iterator iterator = pixels.iterator();
        while (iterator.hasNext()) {
            int pixelIndex = (Integer)iterator.next();
            int x = pixelIndex % width;
            int z = pixelIndex / width;
            ownershipByPixel[pixelIndex] = CastleAreaOwnershipResolver.nearestSeed(candidates, x, z).castleIndex();
        }
    }

    private static Map<Integer, List<CastleSeed>> indexSeedsByPixel(List<CastleSeed> seeds, int width) {
        HashMap<Integer, List<CastleSeed>> indexed = new HashMap<Integer, List<CastleSeed>>();
        for (CastleSeed seed : seeds) {
            indexed.computeIfAbsent(CastleAreaOwnershipResolver.indexOf(seed.pixelX(), seed.pixelZ(), width), ignored -> new ArrayList()).add(seed);
        }
        return indexed;
    }

    private static Map<Integer, List<CastleSeed>> indexSeedsByRegion(List<CastleSeed> seeds) {
        HashMap<Integer, List<CastleSeed>> indexed = new HashMap<Integer, List<CastleSeed>>();
        for (CastleSeed seed : seeds) {
            indexed.computeIfAbsent(seed.regionColor(), ignored -> new ArrayList()).add(seed);
        }
        return indexed;
    }

    private static CastleSeed nearestSeed(List<CastleSeed> seeds, int pixelX, int pixelZ) {
        CastleSeed closest = seeds.getFirst();
        long bestDistance = CastleAreaOwnershipResolver.squaredDistance(closest, pixelX, pixelZ);
        for (int index = 1; index < seeds.size(); ++index) {
            CastleSeed candidate = seeds.get(index);
            long distance = CastleAreaOwnershipResolver.squaredDistance(candidate, pixelX, pixelZ);
            if (distance >= bestDistance) continue;
            bestDistance = distance;
            closest = candidate;
        }
        return closest;
    }

    private static long squaredDistance(CastleSeed seed, int pixelX, int pixelZ) {
        long dx = pixelX - seed.pixelX();
        long dz = pixelZ - seed.pixelZ();
        return dx * dx + dz * dz;
    }

    private static void visitNeighbor(BufferedImage areasImage, boolean[] visited, ArrayDeque<Integer> queue, int width, int height, int x, int z, int regionColor) {
        if (x < 0 || z < 0 || x >= width || z >= height) {
            return;
        }
        int index = CastleAreaOwnershipResolver.indexOf(x, z, width);
        if (visited[index]) {
            return;
        }
        int color = areasImage.getRGB(x, z);
        if (!CastleAreaOwnershipResolver.isLandPixel(color) || CastleAreaOwnershipResolver.rgb(color) != regionColor) {
            return;
        }
        visited[index] = true;
        queue.addLast(index);
    }

    @Nullable
    private static PixelMatch findNearestLandPixel(BufferedImage image, int centerX, int centerZ) {
        int centerColor = image.getRGB(centerX, centerZ);
        if (CastleAreaOwnershipResolver.isLandPixel(centerColor)) {
            return new PixelMatch(centerX, centerZ, CastleAreaOwnershipResolver.rgb(centerColor));
        }
        int width = image.getWidth();
        int height = image.getHeight();
        for (int radius = 1; radius <= 96; ++radius) {
            int maxZ;
            int minZ;
            int maxX;
            int minX = Math.max(0, centerX - radius);
            PixelMatch match = CastleAreaOwnershipResolver.scanRing(image, centerX, centerZ, minX, maxX = Math.min(width - 1, centerX + radius), minZ = Math.max(0, centerZ - radius), maxZ = Math.min(height - 1, centerZ + radius));
            if (match == null) continue;
            return match;
        }
        return null;
    }

    @Nullable
    private static PixelMatch scanRing(BufferedImage image, int centerX, int centerZ, int minX, int maxX, int minZ, int maxZ) {
        PixelMatch best = null;
        long bestDistance = Long.MAX_VALUE;
        for (int x = minX; x <= maxX; ++x) {
            if ((best = CastleAreaOwnershipResolver.chooseCloserMatch(image, centerX, centerZ, x, minZ, best, bestDistance)) != null) {
                bestDistance = CastleAreaOwnershipResolver.squaredDistance(best.x(), best.z(), centerX, centerZ);
            }
            if ((best = CastleAreaOwnershipResolver.chooseCloserMatch(image, centerX, centerZ, x, maxZ, best, bestDistance)) == null) continue;
            bestDistance = CastleAreaOwnershipResolver.squaredDistance(best.x(), best.z(), centerX, centerZ);
        }
        for (int z = minZ + 1; z < maxZ; ++z) {
            if ((best = CastleAreaOwnershipResolver.chooseCloserMatch(image, centerX, centerZ, minX, z, best, bestDistance)) != null) {
                bestDistance = CastleAreaOwnershipResolver.squaredDistance(best.x(), best.z(), centerX, centerZ);
            }
            if ((best = CastleAreaOwnershipResolver.chooseCloserMatch(image, centerX, centerZ, maxX, z, best, bestDistance)) == null) continue;
            bestDistance = CastleAreaOwnershipResolver.squaredDistance(best.x(), best.z(), centerX, centerZ);
        }
        return best;
    }

    @Nullable
    private static PixelMatch chooseCloserMatch(BufferedImage image, int centerX, int centerZ, int x, int z, @Nullable PixelMatch currentBest, long currentBestDistance) {
        int color = image.getRGB(x, z);
        if (!CastleAreaOwnershipResolver.isLandPixel(color)) {
            return currentBest;
        }
        long distance = CastleAreaOwnershipResolver.squaredDistance(x, z, centerX, centerZ);
        if (currentBest == null || distance < currentBestDistance) {
            return new PixelMatch(x, z, CastleAreaOwnershipResolver.rgb(color));
        }
        return currentBest;
    }

    private static long squaredDistance(int x1, int z1, int x2, int z2) {
        long dx = x1 - x2;
        long dz = z1 - z2;
        return dx * dx + dz * dz;
    }

    private static int worldToPixel(int worldCoordinate, int imageSize) {
        double mapPixel = (double)(worldCoordinate + 48000) / 32.0;
        double scaled = mapPixel * ((double)imageSize / 3000.0);
        return class_3532.method_15340((int)((int)Math.floor(scaled)), (int)0, (int)(imageSize - 1));
    }

    private static boolean isLandPixel(int argb) {
        return (argb >>> 24 & 0xFF) > 0;
    }

    private static int rgb(int argb) {
        return argb & 0xFFFFFF;
    }

    private static int indexOf(int x, int z, int width) {
        return z * width + x;
    }

    private record CachedOwnership(int signature, int width, int height, short[] ownershipByPixel, List<CastleInstance> castlesByIndex) {
        @Nullable
        private CastleInstance findCastleAt(class_2338 pos) {
            int pixelZ;
            int pixelX = CastleAreaOwnershipResolver.worldToPixel(pos.method_10263(), this.width);
            short ownerIndex = this.ownershipByPixel[CastleAreaOwnershipResolver.indexOf(pixelX, pixelZ = CastleAreaOwnershipResolver.worldToPixel(pos.method_10260(), this.height), this.width)];
            if (ownerIndex < 0 || ownerIndex >= this.castlesByIndex.size()) {
                return null;
            }
            return this.castlesByIndex.get(ownerIndex);
        }
    }

    private record PixelMatch(int x, int z, int color) {
    }

    private record CastleSeed(CastleInstance castle, short castleIndex, int pixelX, int pixelZ, int regionColor) {
    }
}

