/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_2338
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.village;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import net.minecraft.class_18;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;
import trep.got.village.VillageInstance;

public final class VillageWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.villages";
    private static final class_18.class_8645<VillageWorldState> TYPE = new class_18.class_8645(VillageWorldState::new, VillageWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, VillageInstance> villages = new LinkedHashMap<String, VillageInstance>();
    private final Map<String, String> boundCastleIndex = new HashMap<String, String>();
    private final Map<Long, String> bannerIndex = new HashMap<Long, String>();
    private final Map<Long, String> claimIndex = new HashMap<Long, String>();

    public static VillageWorldState get(class_3218 world) {
        return (VillageWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public Collection<VillageInstance> getAll() {
        return this.villages.values();
    }

    public VillageInstance get(String villageId) {
        return this.villages.get(villageId);
    }

    public void put(VillageInstance village) {
        if (village == null) {
            return;
        }
        this.villages.put(village.getId(), village);
        this.rebuildIndexes();
        this.method_80();
    }

    public VillageInstance findByBoundCastle(String castleId) {
        if (castleId == null || castleId.isBlank()) {
            return null;
        }
        String normalizedCastleId = VillageWorldState.normalizeCastleId(castleId);
        String villageId = this.boundCastleIndex.get(normalizedCastleId);
        if (villageId != null) {
            return this.villages.get(villageId);
        }
        for (VillageInstance village : this.villages.values()) {
            if (!castleId.equals(village.getBoundCastleId()) && !normalizedCastleId.equals(VillageWorldState.normalizeCastleId(village.getBoundCastleId()))) continue;
            this.boundCastleIndex.put(normalizedCastleId, village.getId());
            return village;
        }
        return null;
    }

    public VillageInstance findByBannerPos(class_2338 pos) {
        if (pos == null) {
            return null;
        }
        String villageId = this.bannerIndex.get(pos.method_10063());
        if (villageId != null) {
            return this.villages.get(villageId);
        }
        for (VillageInstance village : this.villages.values()) {
            if (!village.getCenterPos().equals((Object)pos)) continue;
            this.bannerIndex.put(pos.method_10063(), village.getId());
            return village;
        }
        return null;
    }

    public VillageInstance findClaimAt(class_2338 pos) {
        int chunkZ;
        int chunkX = pos.method_10263() >> 4;
        String villageId = this.claimIndex.get(VillageWorldState.chunkKey(chunkX, chunkZ = pos.method_10260() >> 4));
        if (villageId != null) {
            return this.villages.get(villageId);
        }
        for (VillageInstance village : this.villages.values()) {
            if (!VillageWorldState.containsChunk(village, chunkX, chunkZ)) continue;
            this.claimIndex.put(VillageWorldState.chunkKey(chunkX, chunkZ), village.getId());
            return village;
        }
        return null;
    }

    public boolean overlapsClaim(class_2338 pos, int claimRadiusChunks) {
        int chunkX = pos.method_10263() >> 4;
        int chunkZ = pos.method_10260() >> 4;
        for (VillageInstance village : this.villages.values()) {
            boolean overlapZ;
            int centerChunkX = village.getCenterPos().method_10263() >> 4;
            int centerChunkZ = village.getCenterPos().method_10260() >> 4;
            int radius = village.getClaimRadiusChunks();
            boolean overlapX = Math.abs(chunkX - centerChunkX) <= claimRadiusChunks + radius;
            boolean bl = overlapZ = Math.abs(chunkZ - centerChunkZ) <= claimRadiusChunks + radius;
            if (!overlapX || !overlapZ) continue;
            return true;
        }
        return false;
    }

    private void rebuildIndexes() {
        this.boundCastleIndex.clear();
        this.bannerIndex.clear();
        this.claimIndex.clear();
        for (VillageInstance village : this.villages.values()) {
            this.boundCastleIndex.put(VillageWorldState.normalizeCastleId(village.getBoundCastleId()), village.getId());
            this.bannerIndex.put(village.getCenterPos().method_10063(), village.getId());
            int centerChunkX = village.getCenterPos().method_10263() >> 4;
            int centerChunkZ = village.getCenterPos().method_10260() >> 4;
            int radius = village.getClaimRadiusChunks();
            for (int chunkX = centerChunkX - radius; chunkX <= centerChunkX + radius; ++chunkX) {
                for (int chunkZ = centerChunkZ - radius; chunkZ <= centerChunkZ + radius; ++chunkZ) {
                    this.claimIndex.putIfAbsent(VillageWorldState.chunkKey(chunkX, chunkZ), village.getId());
                }
            }
        }
    }

    private static boolean containsChunk(VillageInstance village, int chunkX, int chunkZ) {
        int centerChunkX = village.getCenterPos().method_10263() >> 4;
        int centerChunkZ = village.getCenterPos().method_10260() >> 4;
        int radius = village.getClaimRadiusChunks();
        return Math.abs(chunkX - centerChunkX) <= radius && Math.abs(chunkZ - centerChunkZ) <= radius;
    }

    private static long chunkKey(int chunkX, int chunkZ) {
        return (long)chunkX << 32 ^ (long)chunkZ & 0xFFFFFFFFL;
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 list = new class_2499();
        for (VillageInstance village : this.villages.values()) {
            list.add((Object)village.toNbt());
        }
        nbt.method_10566("villages", (class_2520)list);
        return nbt;
    }

    private static VillageWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        VillageWorldState state = new VillageWorldState();
        class_2499 villages = nbt.method_10554("villages", 10);
        for (int index = 0; index < villages.size(); ++index) {
            VillageInstance village = VillageInstance.fromNbt(villages.method_10602(index));
            state.villages.put(village.getId(), village);
        }
        state.rebuildIndexes();
        return state;
    }

    private static String normalizeCastleId(String castleId) {
        return castleId == null ? "" : castleId.trim().toLowerCase(Locale.ROOT).replace(' ', '_').replace('-', '_');
    }
}

