/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
 *  net.minecraft.class_1923
 *  net.minecraft.class_1937
 *  net.minecraft.class_3218
 *  net.minecraft.class_5321
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.world.structure.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_3218;
import net.minecraft.class_5321;
import net.minecraft.server.MinecraftServer;
import trep.got.castle.CastleWorldState;
import trep.got.registry.GotCastles;
import trep.got.registry.castle.overlay.OverlayCastleSite;
import trep.got.world.structure.castle.CastleWorldgenPlan;
import trep.got.world.structure.castle.CastleWorldgenPlanner;
import trep.got.world.structure.castle.road.CastleRoadNetworkFingerprint;

public final class CastlePlanCache {
    private static final int CANDIDATE_REACH_VERSION = 2;
    private static final int LEGACY_CASTLE_REACH_CHUNKS = 40;
    private static final int SITE_BUCKET_SIZE_CHUNKS = 32;
    private static final int CONNECTOR_REACH_CHUNKS = Math.floorDiv(1615, 16) + 1;
    private static final int CANDIDATE_PADDING_CHUNKS = 40 + CONNECTOR_REACH_CHUNKS;
    private static final Map<MinecraftServer, Map<class_5321<class_1937>, WorldCache>> CACHES_BY_SERVER = Collections.synchronizedMap(new IdentityHashMap());

    private CastlePlanCache() {
    }

    public static List<CastleWorldgenPlan> getAll(class_3218 world) {
        if (world == null) {
            return List.of();
        }
        WorldCache cache = CastlePlanCache.cache(world);
        ArrayList plans = new ArrayList();
        for (OverlayCastleSite site : cache.orderedSites()) {
            CastlePlanCache.get(world, site.castleId()).ifPresent(plans::add);
        }
        return List.copyOf(plans);
    }

    public static Optional<CastleWorldgenPlan> get(class_3218 world, String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return Optional.empty();
        }
        WorldCache cache = CastlePlanCache.cache(world);
        return cache.plans().computeIfAbsent(castleId, ignored -> CastlePlanCache.createPlan(world, cache, castleId));
    }

    public static boolean isCurrentLayoutPinned(class_3218 world, CastleWorldgenPlan plan) {
        if (world == null || plan == null) {
            return false;
        }
        CastleWorldState state = CastleWorldState.get(world);
        CastleWorldState.CastleLayoutPin pin = state.getLayoutPin(plan.castleId());
        if (pin == null) {
            boolean untouched = !state.contains(plan.castleId()) && !state.hasPendingGeneration(plan.castleId());
            int version = state.layoutPinEra() == 2 && untouched ? 2 : 1;
            pin = state.pinLayoutIfAbsent(plan.castleId(), version, version == 2 ? CastlePlanCache.cache(world).fingerprint(plan) : "");
        }
        return pin != null && pin.version() == 2 && !pin.fullPlanFingerprint().isBlank() && pin.fullPlanFingerprint().equals(CastlePlanCache.cache(world).fingerprint(plan));
    }

    public static List<CastleWorldgenPlan> getIntersectingChunk(class_3218 world, class_1923 chunkPos) {
        if (world == null || chunkPos == null) {
            return List.of();
        }
        return CastlePlanCache.getIntersectingChunkWindow(world, chunkPos.field_9181, chunkPos.field_9180, chunkPos.field_9181, chunkPos.field_9180);
    }

    public static List<CastleWorldgenPlan> getIntersectingChunkWindow(class_3218 world, int minChunkX, int minChunkZ, int maxChunkX, int maxChunkZ) {
        if (world == null || minChunkX > maxChunkX || minChunkZ > maxChunkZ) {
            return List.of();
        }
        WorldCache cache = CastlePlanCache.cache(world);
        ArrayList plans = new ArrayList();
        for (OverlayCastleSite site : cache.candidateSites(minChunkX, minChunkZ, maxChunkX, maxChunkZ)) {
            CastlePlanCache.get(world, site.castleId()).filter(plan -> plan.chunkFootprint().intersectsChunkWindow(minChunkX, minChunkZ, maxChunkX, maxChunkZ) || plan.roadNetworkPlan().intersectsChunkWindow(minChunkX, minChunkZ, maxChunkX, maxChunkZ)).ifPresent(plans::add);
        }
        return List.copyOf(plans);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void invalidate(class_3218 world) {
        if (world == null) {
            return;
        }
        Map<MinecraftServer, Map<class_5321<class_1937>, WorldCache>> map = CACHES_BY_SERVER;
        synchronized (map) {
            Map<class_5321<class_1937>, WorldCache> byDimension = CACHES_BY_SERVER.get(world.method_8503());
            if (byDimension == null) {
                return;
            }
            byDimension.remove(world.method_27983());
            if (byDimension.isEmpty()) {
                CACHES_BY_SERVER.remove(world.method_8503());
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void invalidateServer(MinecraftServer server) {
        if (server == null) {
            return;
        }
        Map<MinecraftServer, Map<class_5321<class_1937>, WorldCache>> map = CACHES_BY_SERVER;
        synchronized (map) {
            CACHES_BY_SERVER.remove(server);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static WorldCache cache(class_3218 world) {
        Map<MinecraftServer, Map<class_5321<class_1937>, WorldCache>> map = CACHES_BY_SERVER;
        synchronized (map) {
            Map byDimension = CACHES_BY_SERVER.computeIfAbsent(world.method_8503(), ignored -> new ConcurrentHashMap());
            return byDimension.computeIfAbsent(world.method_27983(), ignored -> WorldCache.create());
        }
    }

    private static Optional<CastleWorldgenPlan> createPlan(class_3218 world, WorldCache cache, String castleId) {
        OverlayCastleSite site = cache.sitesById().get(castleId);
        return CastleWorldgenPlanner.createPlan(world, site);
    }

    private static boolean siteCouldAffectWindow(OverlayCastleSite site, int minChunkX, int minChunkZ, int maxChunkX, int maxChunkZ) {
        if (site == null || site.anchor() == null) {
            return false;
        }
        int siteChunkX = Math.floorDiv(site.anchor().method_10263(), 16);
        int siteChunkZ = Math.floorDiv(site.anchor().method_10260(), 16);
        return siteChunkX >= minChunkX - CANDIDATE_PADDING_CHUNKS && siteChunkX <= maxChunkX + CANDIDATE_PADDING_CHUNKS && siteChunkZ >= minChunkZ - CANDIDATE_PADDING_CHUNKS && siteChunkZ <= maxChunkZ + CANDIDATE_PADDING_CHUNKS;
    }

    private static long bucketKey(int bucketX, int bucketZ) {
        return class_1923.method_8331((int)bucketX, (int)bucketZ);
    }

    static {
        ServerLifecycleEvents.SERVER_STOPPED.register(CastlePlanCache::invalidateServer);
    }

    private record WorldCache(int candidateReachVersion, List<OverlayCastleSite> orderedSites, Map<String, OverlayCastleSite> sitesById, Map<Long, List<OverlayCastleSite>> sitesByBucket, Map<String, Optional<CastleWorldgenPlan>> plans, Map<String, FingerprintedPlan> fingerprints) {
        private static WorldCache create() {
            ArrayList<OverlayCastleSite> sites = new ArrayList<OverlayCastleSite>(GotCastles.getOverlayCastleSites());
            sites.sort(Comparator.comparingInt(OverlayCastleSite::index).thenComparing(OverlayCastleSite::castleId));
            HashMap<String, OverlayCastleSite> byId = new HashMap<String, OverlayCastleSite>();
            HashMap<Long, List> byBucket = new HashMap<Long, List>();
            for (OverlayCastleSite site : sites) {
                byId.put(site.castleId(), site);
                if (site.anchor() == null) continue;
                int chunkX = Math.floorDiv(site.anchor().method_10263(), 16);
                int chunkZ = Math.floorDiv(site.anchor().method_10260(), 16);
                int bucketX = Math.floorDiv(chunkX, 32);
                int bucketZ = Math.floorDiv(chunkZ, 32);
                byBucket.computeIfAbsent(CastlePlanCache.bucketKey(bucketX, bucketZ), ignored -> new ArrayList()).add(site);
            }
            HashMap immutableBuckets = new HashMap();
            byBucket.forEach((key, value) -> immutableBuckets.put(key, List.copyOf(value)));
            return new WorldCache(2, List.copyOf(sites), Map.copyOf(byId), Map.copyOf(immutableBuckets), new ConcurrentHashMap<String, Optional<CastleWorldgenPlan>>(), new ConcurrentHashMap<String, FingerprintedPlan>());
        }

        private String fingerprint(CastleWorldgenPlan plan) {
            FingerprintedPlan cached = this.fingerprints.compute(plan.castleId(), (castleId, existing) -> existing != null && existing.plan() == plan ? existing : new FingerprintedPlan(plan, CastleRoadNetworkFingerprint.compute(plan)));
            return cached.fingerprint();
        }

        private List<OverlayCastleSite> candidateSites(int minChunkX, int minChunkZ, int maxChunkX, int maxChunkZ) {
            if (this.candidateReachVersion != 2) {
                throw new IllegalStateException("Unsupported castle candidate reach version " + this.candidateReachVersion);
            }
            int minBucketX = Math.floorDiv(minChunkX - CANDIDATE_PADDING_CHUNKS, 32);
            int maxBucketX = Math.floorDiv(maxChunkX + CANDIDATE_PADDING_CHUNKS, 32);
            int minBucketZ = Math.floorDiv(minChunkZ - CANDIDATE_PADDING_CHUNKS, 32);
            int maxBucketZ = Math.floorDiv(maxChunkZ + CANDIDATE_PADDING_CHUNKS, 32);
            LinkedHashSet candidates = new LinkedHashSet();
            for (int bucketX = minBucketX; bucketX <= maxBucketX; ++bucketX) {
                for (int bucketZ = minBucketZ; bucketZ <= maxBucketZ; ++bucketZ) {
                    candidates.addAll(this.sitesByBucket.getOrDefault(CastlePlanCache.bucketKey(bucketX, bucketZ), List.of()));
                }
            }
            return candidates.stream().filter(site -> CastlePlanCache.siteCouldAffectWindow(site, minChunkX, minChunkZ, maxChunkX, maxChunkZ)).sorted(Comparator.comparingInt(OverlayCastleSite::index).thenComparing(OverlayCastleSite::castleId)).toList();
        }
    }

    private record FingerprintedPlan(CastleWorldgenPlan plan, String fingerprint) {
    }
}

