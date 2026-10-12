/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
 *  net.minecraft.class_1297
 *  net.minecraft.class_1923
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_3218
 *  net.minecraft.class_3532
 *  net.minecraft.server.MinecraftServer
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.vehicle.platform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.class_1297;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_3218;
import net.minecraft.class_3532;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import trep.got.vehicle.platform.PlatformEntity;
import trep.got.vehicle.platform.PlatformTransform;

public final class PlatformSpatialIndex {
    private static final int MAX_QUERY_CHUNKS = 256;
    private static final Map<MinecraftServer, ServerIndex> SERVERS = new IdentityHashMap<MinecraftServer, ServerIndex>();
    private static boolean initialized;

    private PlatformSpatialIndex() {
    }

    public static void initialize() {
        if (initialized) {
            return;
        }
        initialized = true;
        ServerEntityEvents.ENTITY_LOAD.register(PlatformSpatialIndex::onEntityLoad);
        ServerEntityEvents.ENTITY_UNLOAD.register((entity, world) -> PlatformSpatialIndex.remove(entity, world));
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            ServerIndex removed = SERVERS.remove(server);
            if (removed != null) {
                removed.clear();
            }
        });
    }

    static void update(class_1297 entity, class_238 broadPhaseBox) {
        class_3218 world;
        block5: {
            block4: {
                class_1937 class_19372 = entity.method_37908();
                if (!(class_19372 instanceof class_3218)) break block4;
                world = (class_3218)class_19372;
                if (!entity.method_31481()) break block5;
            }
            return;
        }
        MinecraftServer server = world.method_8503();
        if (!server.method_18854()) {
            return;
        }
        PlatformSpatialIndex.serverIndex(server).update(entity, world, broadPhaseBox);
    }

    @Nullable
    static List<class_1297> findCandidates(class_1297 excluded, class_238 queryBox) {
        class_1937 class_19372 = excluded.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return null;
        }
        class_3218 world = (class_3218)class_19372;
        MinecraftServer server = world.method_8503();
        if (!server.method_18854()) {
            return null;
        }
        return PlatformSpatialIndex.serverIndex(server).findCandidates(world, excluded, queryBox);
    }

    private static void onEntityLoad(class_1297 entity, class_3218 world) {
        if (!(entity instanceof PlatformEntity)) {
            return;
        }
        PlatformEntity platform = (PlatformEntity)entity;
        PlatformTransform transform = platform.agob$getPlatformTransform();
        if (transform == null) {
            PlatformSpatialIndex.serverIndex(world.method_8503()).markPending(entity, world);
            return;
        }
        PlatformSpatialIndex.serverIndex(world.method_8503()).update(entity, world, platform.agob$getPlatformBroadPhaseBox());
    }

    private static void remove(class_1297 entity, class_3218 world) {
        ServerIndex serverIndex = SERVERS.get(world.method_8503());
        if (serverIndex != null) {
            serverIndex.remove(entity);
        }
    }

    private static ServerIndex serverIndex(MinecraftServer server) {
        return SERVERS.computeIfAbsent(server, ignored -> new ServerIndex());
    }

    private static final class ServerIndex {
        private final Map<class_3218, WorldIndex> worlds = new IdentityHashMap<class_3218, WorldIndex>();
        private final Map<class_1297, IndexedPlatform> platforms = new IdentityHashMap<class_1297, IndexedPlatform>();
        private final Map<class_1297, class_3218> pending = new IdentityHashMap<class_1297, class_3218>();

        private ServerIndex() {
        }

        private void markPending(class_1297 entity, class_3218 world) {
            this.remove(entity);
            this.pending.put(entity, world);
            ++this.world((class_3218)world).pendingCount;
        }

        private void update(class_1297 entity, class_3218 world, class_238 broadPhaseBox) {
            WorldIndex currentWorldIndex;
            this.removePending(entity);
            IndexedPlatform current = this.platforms.get(entity);
            ChunkRange nextRange = ChunkRange.of(broadPhaseBox);
            if (nextRange.exceedsChunkLimit()) {
                this.markPending(entity, world);
                return;
            }
            if (current != null && current.world == world && current.range.equals(nextRange)) {
                current.broadPhaseBox = broadPhaseBox;
                return;
            }
            if (current != null && (currentWorldIndex = this.worlds.get(current.world)) != null) {
                currentWorldIndex.remove(entity, current.range);
                this.removeWorldIfEmpty(current.world, currentWorldIndex);
            }
            this.world(world).add(entity, nextRange);
            this.platforms.put(entity, new IndexedPlatform(world, broadPhaseBox, nextRange));
        }

        @Nullable
        private List<class_1297> findCandidates(class_3218 world, class_1297 excluded, class_238 queryBox) {
            WorldIndex worldIndex = this.worlds.get(world);
            if (worldIndex == null) {
                return List.of();
            }
            if (worldIndex.pendingCount > 0) {
                return null;
            }
            ChunkRange queryRange = ChunkRange.of(queryBox);
            if (queryRange.exceedsChunkLimit()) {
                return null;
            }
            Set<class_1297> unique = null;
            for (int chunkX = queryRange.minX; chunkX <= queryRange.maxX; ++chunkX) {
                for (int chunkZ = queryRange.minZ; chunkZ <= queryRange.maxZ; ++chunkZ) {
                    Set<class_1297> chunkPlatforms = worldIndex.byChunk.get(class_1923.method_8331((int)chunkX, (int)chunkZ));
                    if (chunkPlatforms == null) continue;
                    if (unique == null) {
                        unique = Collections.newSetFromMap(new IdentityHashMap());
                    }
                    unique.addAll(chunkPlatforms);
                }
            }
            if (unique == null) {
                return List.of();
            }
            ArrayList<class_1297> candidates = new ArrayList<class_1297>(unique.size());
            for (class_1297 entity : unique) {
                IndexedPlatform indexed = this.platforms.get(entity);
                if (entity == excluded || indexed == null || indexed.world != world || entity.method_31481() || !indexed.broadPhaseBox.method_994(queryBox)) continue;
                candidates.add(entity);
            }
            return candidates;
        }

        private void remove(class_1297 entity) {
            this.removePending(entity);
            IndexedPlatform indexed = this.platforms.remove(entity);
            if (indexed == null) {
                return;
            }
            WorldIndex worldIndex = this.worlds.get(indexed.world);
            if (worldIndex != null) {
                worldIndex.remove(entity, indexed.range);
                this.removeWorldIfEmpty(indexed.world, worldIndex);
            }
        }

        private void removePending(class_1297 entity) {
            class_3218 pendingWorld = this.pending.remove(entity);
            if (pendingWorld == null) {
                return;
            }
            WorldIndex worldIndex = this.worlds.get(pendingWorld);
            if (worldIndex != null) {
                worldIndex.pendingCount = Math.max(0, worldIndex.pendingCount - 1);
                this.removeWorldIfEmpty(pendingWorld, worldIndex);
            }
        }

        private WorldIndex world(class_3218 world) {
            return this.worlds.computeIfAbsent(world, ignored -> new WorldIndex());
        }

        private void removeWorldIfEmpty(class_3218 world, WorldIndex worldIndex) {
            if (worldIndex.pendingCount == 0 && worldIndex.byChunk.isEmpty()) {
                this.worlds.remove(world);
            }
        }

        private void clear() {
            this.worlds.clear();
            this.platforms.clear();
            this.pending.clear();
        }
    }

    private record ChunkRange(int minX, int maxX, int minZ, int maxZ) {
        private static ChunkRange of(class_238 box) {
            return new ChunkRange(class_3532.method_15357((double)box.field_1323) >> 4, class_3532.method_15357((double)box.field_1320) >> 4, class_3532.method_15357((double)box.field_1321) >> 4, class_3532.method_15357((double)box.field_1324) >> 4);
        }

        private boolean exceedsChunkLimit() {
            long width = (long)this.maxX - (long)this.minX + 1L;
            long depth = (long)this.maxZ - (long)this.minZ + 1L;
            return width > 256L || depth > 256L || width * depth > 256L;
        }
    }

    private static final class IndexedPlatform {
        private final class_3218 world;
        private class_238 broadPhaseBox;
        private final ChunkRange range;

        private IndexedPlatform(class_3218 world, class_238 broadPhaseBox, ChunkRange range) {
            this.world = world;
            this.broadPhaseBox = broadPhaseBox;
            this.range = range;
        }
    }

    private static final class WorldIndex {
        private final Map<Long, Set<class_1297>> byChunk = new HashMap<Long, Set<class_1297>>();
        private int pendingCount;

        private WorldIndex() {
        }

        private void add(class_1297 entity, ChunkRange range) {
            for (int chunkX = range.minX; chunkX <= range.maxX; ++chunkX) {
                for (int chunkZ = range.minZ; chunkZ <= range.maxZ; ++chunkZ) {
                    this.byChunk.computeIfAbsent(class_1923.method_8331((int)chunkX, (int)chunkZ), ignored -> Collections.newSetFromMap(new IdentityHashMap())).add(entity);
                }
            }
        }

        private void remove(class_1297 entity, ChunkRange range) {
            for (int chunkX = range.minX; chunkX <= range.maxX; ++chunkX) {
                for (int chunkZ = range.minZ; chunkZ <= range.maxZ; ++chunkZ) {
                    long key = class_1923.method_8331((int)chunkX, (int)chunkZ);
                    Set<class_1297> entities = this.byChunk.get(key);
                    if (entities == null) continue;
                    entities.remove(entity);
                    if (!entities.isEmpty()) continue;
                    this.byChunk.remove(key);
                }
            }
        }
    }
}

