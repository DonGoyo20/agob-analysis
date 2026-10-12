/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2680
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_3218
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.block;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2902;
import net.minecraft.class_3218;
import net.minecraft.server.MinecraftServer;
import trep.got.block.GateMechanismBlock;

public final class GateLocator {
    private static final int COLUMNS_PER_CHUNK = 256;
    private static final Map<MinecraftServer, Map<class_3218, WorldGateIndex>> BY_SERVER = new IdentityHashMap<MinecraftServer, Map<class_3218, WorldGateIndex>>();
    private static boolean initialized;

    private GateLocator() {
    }

    public static synchronized void initialize() {
        if (initialized) {
            return;
        }
        initialized = true;
        ServerWorldEvents.UNLOAD.register((server, world) -> GateLocator.clearWorld(server, world));
        ServerLifecycleEvents.SERVER_STOPPED.register(GateLocator::clearServer);
    }

    public static synchronized void track(class_3218 world, class_2338 pos) {
        if (world == null || pos == null) {
            return;
        }
        GateLocator.index(world, true).track(pos);
    }

    public static synchronized void untrack(class_3218 world, class_2338 pos) {
        if (world == null || pos == null) {
            return;
        }
        WorldGateIndex index = GateLocator.index(world, false);
        if (index == null) {
            return;
        }
        index.untrack(pos);
        GateLocator.removeIfEmpty(world, index);
    }

    public static synchronized Collection<class_2338> positions(class_3218 world) {
        if (world == null) {
            return Collections.emptySet();
        }
        WorldGateIndex index = GateLocator.index(world, false);
        if (index == null) {
            return Collections.emptySet();
        }
        Collection<class_2338> snapshot = index.snapshot();
        GateLocator.removeIfEmpty(world, index);
        return snapshot;
    }

    public static synchronized int purgeStale(class_3218 world, int maxEntries) {
        if (world == null || maxEntries <= 0 || !world.method_8503().method_18854()) {
            return 0;
        }
        WorldGateIndex index = GateLocator.index(world, false);
        if (index == null) {
            return 0;
        }
        int removed = index.purgeStale(world, maxEntries);
        GateLocator.removeIfEmpty(world, index);
        return removed;
    }

    public static synchronized int submitLegacyDiscovery(class_3218 world, Collection<class_1923> chunks, int maxChunks) {
        if (world == null || chunks == null || chunks.isEmpty() || maxChunks <= 0) {
            return 0;
        }
        WorldGateIndex index = GateLocator.index(world, true);
        int inspected = 0;
        int added = 0;
        for (class_1923 chunk : chunks) {
            if (inspected++ >= maxChunks) break;
            if (chunk == null || !index.submit(chunk)) continue;
            ++added;
        }
        return added;
    }

    public static synchronized boolean submitLegacyDiscovery(class_3218 world, class_1923 chunk) {
        if (world == null || chunk == null) {
            return false;
        }
        return GateLocator.index(world, true).submit(chunk);
    }

    public static synchronized LegacyDiscoveryProgress advanceLegacyDiscovery(class_3218 world, int maxChunks, int maxColumns) {
        if (world == null || maxChunks <= 0 || maxColumns <= 0 || !world.method_8503().method_18854()) {
            return LegacyDiscoveryProgress.empty(GateLocator.pendingLegacyDiscoveryChunks(world));
        }
        WorldGateIndex index = GateLocator.index(world, false);
        if (index == null) {
            return LegacyDiscoveryProgress.empty(0);
        }
        LegacyDiscoveryProgress progress = index.advanceLegacyDiscovery(world, maxChunks, maxColumns);
        GateLocator.removeIfEmpty(world, index);
        return progress;
    }

    public static synchronized int pendingLegacyDiscoveryChunks(class_3218 world) {
        WorldGateIndex index = GateLocator.index(world, false);
        return index == null ? 0 : index.pendingDiscoveryChunks();
    }

    public static synchronized void cancelLegacyDiscovery(class_3218 world) {
        if (world == null) {
            return;
        }
        WorldGateIndex index = GateLocator.index(world, false);
        if (index == null) {
            return;
        }
        index.cancelLegacyDiscovery();
        GateLocator.removeIfEmpty(world, index);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void clearWorld(MinecraftServer server, class_3218 world) {
        Class<GateLocator> clazz = GateLocator.class;
        synchronized (GateLocator.class) {
            Map<class_3218, WorldGateIndex> worlds = BY_SERVER.get(server);
            if (worlds == null) {
                // ** MonitorExit[var2_2] (shouldn't be in output)
                return;
            }
            worlds.remove(world);
            if (worlds.isEmpty()) {
                BY_SERVER.remove(server);
            }
            // ** MonitorExit[var2_2] (shouldn't be in output)
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void clearServer(MinecraftServer server) {
        Class<GateLocator> clazz = GateLocator.class;
        synchronized (GateLocator.class) {
            BY_SERVER.remove(server);
            // ** MonitorExit[var1_1] (shouldn't be in output)
            return;
        }
    }

    private static WorldGateIndex index(class_3218 world, boolean create) {
        if (world == null) {
            return null;
        }
        MinecraftServer server = world.method_8503();
        Map<class_3218, WorldGateIndex> worlds = BY_SERVER.get(server);
        if (worlds == null) {
            if (!create) {
                return null;
            }
            worlds = new IdentityHashMap<class_3218, WorldGateIndex>();
            BY_SERVER.put(server, worlds);
        }
        if (create) {
            return worlds.computeIfAbsent(world, ignored -> new WorldGateIndex());
        }
        return worlds.get(world);
    }

    private static void removeIfEmpty(class_3218 world, WorldGateIndex index) {
        if (!index.isEmpty()) {
            return;
        }
        MinecraftServer server = world.method_8503();
        Map<class_3218, WorldGateIndex> worlds = BY_SERVER.get(server);
        if (worlds == null || worlds.get(world) != index) {
            return;
        }
        worlds.remove(world);
        if (worlds.isEmpty()) {
            BY_SERVER.remove(server);
        }
    }

    private static final class WorldGateIndex {
        private final LinkedHashSet<class_2338> positions = new LinkedHashSet();
        private final ArrayDeque<class_2338> validationQueue = new ArrayDeque();
        private final Set<class_2338> queuedForValidation = new LinkedHashSet<class_2338>();
        private final ArrayDeque<LegacyChunkCursor> discoveryQueue = new ArrayDeque();
        private final Set<Long> pendingDiscoveryChunks = new LinkedHashSet<Long>();

        private WorldGateIndex() {
        }

        private void track(class_2338 pos) {
            class_2338 immutable = pos.method_10062();
            if (this.positions.add(immutable) && this.queuedForValidation.add(immutable)) {
                this.validationQueue.addLast(immutable);
            }
        }

        private void untrack(class_2338 pos) {
            class_2338 immutable = pos.method_10062();
            this.positions.remove(immutable);
            if (this.queuedForValidation.remove(immutable)) {
                this.validationQueue.remove(immutable);
            }
        }

        private Collection<class_2338> snapshot() {
            if (this.positions.isEmpty()) {
                return Collections.emptySet();
            }
            return Collections.unmodifiableSet(new LinkedHashSet<class_2338>(this.positions));
        }

        private int purgeStale(class_3218 world, int maxEntries) {
            int inspected = 0;
            int removed = 0;
            while (inspected < maxEntries && !this.validationQueue.isEmpty()) {
                class_2338 pos = this.validationQueue.removeFirst();
                if (!this.queuedForValidation.remove(pos) || !this.positions.contains(pos)) continue;
                ++inspected;
                if (!world.method_8393(pos.method_10263() >> 4, pos.method_10260() >> 4)) {
                    this.queueValidation(pos);
                    continue;
                }
                if (!(world.method_8320(pos).method_26204() instanceof GateMechanismBlock)) {
                    this.positions.remove(pos);
                    ++removed;
                    continue;
                }
                this.queueValidation(pos);
            }
            return removed;
        }

        private void queueValidation(class_2338 pos) {
            if (this.queuedForValidation.add(pos)) {
                this.validationQueue.addLast(pos);
            }
        }

        private boolean submit(class_1923 chunk) {
            long key = chunk.method_8324();
            if (!this.pendingDiscoveryChunks.add(key)) {
                return false;
            }
            this.discoveryQueue.addLast(new LegacyChunkCursor(chunk.field_9181, chunk.field_9180));
            return true;
        }

        private LegacyDiscoveryProgress advanceLegacyDiscovery(class_3218 world, int maxChunks, int maxColumns) {
            int attemptedChunks = 0;
            int completedChunks = 0;
            int deferredChunks = 0;
            int scannedColumns = 0;
            int foundGates = 0;
            int cursorsThisPass = Math.min(maxChunks, this.discoveryQueue.size());
            class_2338.class_2339 mutable = new class_2338.class_2339();
            while (attemptedChunks < cursorsThisPass && scannedColumns < maxColumns) {
                LegacyChunkCursor cursor = this.discoveryQueue.removeFirst();
                ++attemptedChunks;
                if (!world.method_8393(cursor.chunkX, cursor.chunkZ)) {
                    ++deferredChunks;
                    this.pendingDiscoveryChunks.remove(class_1923.method_8331((int)cursor.chunkX, (int)cursor.chunkZ));
                    continue;
                }
                int columnBudget = Math.min(maxColumns - scannedColumns, 256 - cursor.nextColumn);
                for (int offset = 0; offset < columnBudget; ++offset) {
                    int column;
                    ++cursor.nextColumn;
                    int x = (cursor.chunkX << 4) + (column & 0xF);
                    int z = (cursor.chunkZ << 4) + (column >> 4);
                    foundGates += this.scanLegacyColumn(world, mutable, x, z);
                    ++scannedColumns;
                }
                if (cursor.nextColumn >= 256) {
                    this.pendingDiscoveryChunks.remove(class_1923.method_8331((int)cursor.chunkX, (int)cursor.chunkZ));
                    ++completedChunks;
                    continue;
                }
                this.discoveryQueue.addLast(cursor);
            }
            return new LegacyDiscoveryProgress(attemptedChunks, completedChunks, deferredChunks, scannedColumns, foundGates, this.pendingDiscoveryChunks.size());
        }

        private int scanLegacyColumn(class_3218 world, class_2338.class_2339 mutable, int x, int z) {
            int found = 0;
            int topY = world.method_8624(class_2902.class_2903.field_13203, x, z);
            int minY = Math.max(world.method_31607(), topY - 20);
            for (int y = topY + 6; y >= minY; --y) {
                mutable.method_10103(x, y, z);
                class_2680 state = world.method_8320((class_2338)mutable);
                if (!(state.method_26204() instanceof GateMechanismBlock)) continue;
                int sizeBefore = this.positions.size();
                this.track((class_2338)mutable);
                if (this.positions.size() <= sizeBefore) continue;
                ++found;
            }
            return found;
        }

        private int pendingDiscoveryChunks() {
            return this.pendingDiscoveryChunks.size();
        }

        private void cancelLegacyDiscovery() {
            this.discoveryQueue.clear();
            this.pendingDiscoveryChunks.clear();
        }

        private boolean isEmpty() {
            return this.positions.isEmpty() && this.discoveryQueue.isEmpty();
        }
    }

    public record LegacyDiscoveryProgress(int attemptedChunks, int completedChunks, int deferredUnloadedChunks, int scannedColumns, int foundGates, int pendingChunks) {
        private static LegacyDiscoveryProgress empty(int pendingChunks) {
            return new LegacyDiscoveryProgress(0, 0, 0, 0, 0, pendingChunks);
        }
    }

    private static final class LegacyChunkCursor {
        private final int chunkX;
        private final int chunkZ;
        private int nextColumn;

        private LegacyChunkCursor(int chunkX, int chunkZ) {
            this.chunkX = chunkX;
            this.chunkZ = chunkZ;
        }
    }
}

