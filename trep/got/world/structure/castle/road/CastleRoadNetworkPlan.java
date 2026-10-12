/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 */
package trep.got.world.structure.castle.road;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1923;
import trep.got.world.structure.castle.road.CastleRoadAccess;
import trep.got.world.structure.castle.road.CastleRoadBounds;
import trep.got.world.structure.castle.road.CastleRoadCell;
import trep.got.world.structure.castle.road.CastleRoadPortal;

public record CastleRoadNetworkPlan(int version, int halfWidth, List<CastleRoadPortal> gatePortals, List<CastleRoadAccess> buildingAccesses, Map<Long, List<CastleRoadCell>> cellsByChunk, List<String> diagnostics) {
    public static final int CURRENT_VERSION = 2;
    public static final CastleRoadNetworkPlan EMPTY = new CastleRoadNetworkPlan(2, 2, List.of(), List.of(), Map.of(), List.of("road_network_unavailable"));

    public CastleRoadNetworkPlan {
        if (version <= 0) {
            throw new IllegalArgumentException("Road plan version must be positive");
        }
        halfWidth = Math.max(1, halfWidth);
        gatePortals = List.copyOf(gatePortals == null ? List.of() : gatePortals);
        buildingAccesses = List.copyOf(buildingAccesses == null ? List.of() : buildingAccesses);
        LinkedHashMap copied = new LinkedHashMap();
        if (cellsByChunk != null) {
            cellsByChunk.entrySet().stream().sorted((first, second) -> {
                int x = Integer.compare(class_1923.method_8325((long)((Long)first.getKey())), class_1923.method_8325((long)((Long)second.getKey())));
                return x != 0 ? x : Integer.compare(class_1923.method_8332((long)((Long)first.getKey())), class_1923.method_8332((long)((Long)second.getKey())));
            }).forEach(entry -> copied.put((Long)entry.getKey(), List.copyOf((Collection)entry.getValue())));
        }
        cellsByChunk = Collections.unmodifiableMap(copied);
        diagnostics = List.copyOf(diagnostics == null ? List.of() : diagnostics);
    }

    public List<CastleRoadCell> cellsForChunk(class_1923 chunkPos) {
        return chunkPos == null ? List.of() : this.cellsByChunk.getOrDefault(chunkPos.method_8324(), List.of());
    }

    public boolean reserves(CastleRoadBounds bounds) {
        if (bounds == null) {
            return false;
        }
        int minChunkX = Math.floorDiv(bounds.minX(), 16);
        int maxChunkX = Math.floorDiv(bounds.maxX(), 16);
        int minChunkZ = Math.floorDiv(bounds.minZ(), 16);
        int maxChunkZ = Math.floorDiv(bounds.maxZ(), 16);
        for (int chunkX = minChunkX; chunkX <= maxChunkX; ++chunkX) {
            for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; ++chunkZ) {
                for (CastleRoadCell cell : this.cellsByChunk.getOrDefault(class_1923.method_8331((int)chunkX, (int)chunkZ), List.of())) {
                    if (!bounds.contains(cell.x(), cell.z())) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean intersectsChunkWindow(int minChunkX, int minChunkZ, int maxChunkX, int maxChunkZ) {
        if (minChunkX > maxChunkX || minChunkZ > maxChunkZ || this.cellsByChunk.isEmpty()) {
            return false;
        }
        long windowArea = (long)(maxChunkX - minChunkX + 1) * ((long)(maxChunkZ - minChunkZ) + 1L);
        if (windowArea <= (long)this.cellsByChunk.size()) {
            for (int chunkX = minChunkX; chunkX <= maxChunkX; ++chunkX) {
                for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; ++chunkZ) {
                    if (!this.cellsByChunk.containsKey(class_1923.method_8331((int)chunkX, (int)chunkZ))) continue;
                    return true;
                }
            }
            return false;
        }
        return this.cellsByChunk.keySet().stream().anyMatch(key -> {
            int chunkX = class_1923.method_8325((long)key);
            int chunkZ = class_1923.method_8332((long)key);
            return chunkX >= minChunkX && chunkX <= maxChunkX && chunkZ >= minChunkZ && chunkZ <= maxChunkZ;
        });
    }
}

