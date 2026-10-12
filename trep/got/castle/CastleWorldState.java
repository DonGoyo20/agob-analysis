/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_2338
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.castle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1799;
import net.minecraft.class_18;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleRecaptureState;
import trep.got.castle.PendingCastleGeneration;
import trep.got.castle.PendingCastleGenerationStage;
import trep.got.war.LordWarPartyHelper;

public final class CastleWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.castles";
    private static final class_18.class_8645<CastleWorldState> TYPE = new class_18.class_8645(CastleWorldState::new, CastleWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, CastleInstance> castles = new LinkedHashMap<String, CastleInstance>();
    private final Map<String, PendingCastleGeneration> pendingGenerations = new LinkedHashMap<String, PendingCastleGeneration>();
    private final Map<String, CastleLayoutPin> layoutPinsByCastleId = new ConcurrentHashMap<String, CastleLayoutPin>();
    private int layoutPinEra;
    private final Map<String, CastleRecaptureState> recaptures = new LinkedHashMap<String, CastleRecaptureState>();
    private final Map<String, List<class_1799>> ghostStorageByCastleId = new LinkedHashMap<String, List<class_1799>>();
    private final Set<String> rejectedCastleSiteIds = new LinkedHashSet<String>();
    private final Map<Long, String> bannerIndex = new HashMap<Long, String>();
    private final Map<Long, String> claimIndex = new HashMap<Long, String>();
    private boolean overlayCastlesGenerated;

    public static CastleWorldState get(class_3218 world) {
        return (CastleWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public Collection<CastleInstance> getAll() {
        return this.castles.values();
    }

    public CastleInstance get(String castleId) {
        return this.castles.get(castleId);
    }

    public void put(CastleInstance castle) {
        this.castles.put(castle.getId(), castle);
        this.rebuildIndexes();
        this.flushGhostStorage(castle);
        this.method_80();
    }

    public boolean contains(String castleId) {
        return this.castles.containsKey(castleId);
    }

    public void remove(String castleId) {
        if (this.castles.remove(castleId) != null) {
            this.rebuildIndexes();
            this.recaptures.remove(castleId);
            this.pendingGenerations.remove(castleId);
            this.method_80();
        }
    }

    public Collection<PendingCastleGeneration> getAllPendingGenerations() {
        return this.pendingGenerations.values();
    }

    public PendingCastleGeneration getPendingGeneration(String castleId) {
        return this.pendingGenerations.get(castleId);
    }

    public void initializeLayoutPinEra(boolean brandNewWorld) {
        if (this.layoutPinEra != 0) {
            return;
        }
        this.layoutPinEra = brandNewWorld && this.castles.isEmpty() && this.pendingGenerations.isEmpty() ? 2 : 1;
        this.method_80();
    }

    public int layoutPinEra() {
        return this.layoutPinEra == 2 ? 2 : 1;
    }

    public CastleLayoutPin getLayoutPin(String castleId) {
        return castleId == null ? null : this.layoutPinsByCastleId.get(castleId);
    }

    public CastleLayoutPin pinLayoutIfAbsent(String castleId, int version, String fullPlanFingerprint) {
        if (castleId == null || castleId.isBlank()) {
            return null;
        }
        int resolvedVersion = version == 2 ? 2 : 1;
        CastleLayoutPin pin = new CastleLayoutPin(resolvedVersion, resolvedVersion == 2 ? fullPlanFingerprint : "");
        CastleLayoutPin existing = this.layoutPinsByCastleId.putIfAbsent(castleId, pin);
        if (existing != null) {
            return existing;
        }
        this.method_80();
        return pin;
    }

    public boolean hasPendingGeneration(String castleId) {
        return this.pendingGenerations.containsKey(castleId);
    }

    public void putPendingGeneration(PendingCastleGeneration generation) {
        if (generation == null || generation.castleId().isBlank()) {
            return;
        }
        this.pendingGenerations.put(generation.castleId(), generation);
        this.method_80();
    }

    public void removePendingGeneration(String castleId) {
        if (this.pendingGenerations.remove(castleId) != null) {
            this.method_80();
        }
    }

    public int queueGhostStorage(String castleId, class_1799 stack) {
        if (castleId == null || castleId.isBlank() || stack == null || stack.method_7960()) {
            return 0;
        }
        CastleInstance castle = this.castles.get(castleId);
        if (castle != null) {
            int inserted = CastleWorldState.insertIntoStorage(castle.getStorageItems(), stack, castle.getStorageSize());
            if (inserted > 0) {
                this.method_80();
            }
            return inserted;
        }
        List ghostStorage = this.ghostStorageByCastleId.computeIfAbsent(castleId, ignored -> new ArrayList());
        int inserted = CastleWorldState.insertIntoStorage(ghostStorage, stack, Integer.MAX_VALUE);
        if (inserted > 0) {
            this.method_80();
        }
        return inserted;
    }

    public List<class_1799> getGhostStorage(String castleId) {
        List<class_1799> stacks = this.ghostStorageByCastleId.get(castleId);
        if (stacks == null) {
            return List.of();
        }
        return stacks.stream().map(class_1799::method_7972).toList();
    }

    public Collection<CastleRecaptureState> getAllRecaptures() {
        return this.recaptures.values();
    }

    public CastleRecaptureState getRecapture(String castleId) {
        return this.recaptures.get(castleId);
    }

    public CastleRecaptureState getOrCreateRecapture(String castleId) {
        CastleRecaptureState existing = this.recaptures.get(castleId);
        if (existing != null) {
            return existing;
        }
        CastleRecaptureState created = new CastleRecaptureState(castleId);
        this.recaptures.put(castleId, created);
        this.method_80();
        return created;
    }

    public void putRecapture(CastleRecaptureState recapture) {
        if (recapture == null || recapture.getCastleId().isBlank()) {
            return;
        }
        this.recaptures.put(recapture.getCastleId(), recapture);
        this.method_80();
    }

    public void removeRecapture(String castleId) {
        if (this.recaptures.remove(castleId) != null) {
            this.method_80();
        }
    }

    public CastleInstance findByBannerPos(class_2338 bannerPos) {
        String castleId = this.bannerIndex.get(bannerPos.method_10063());
        if (castleId != null) {
            return this.castles.get(castleId);
        }
        for (CastleInstance castle : this.castles.values()) {
            if (!castle.getBannerPos().equals((Object)bannerPos)) continue;
            this.bannerIndex.put(bannerPos.method_10063(), castle.getId());
            return castle;
        }
        return null;
    }

    public CastleInstance findClaimAt(class_2338 pos) {
        int chunkZ;
        int chunkX = pos.method_10263() >> 4;
        String castleId = this.claimIndex.get(CastleWorldState.chunkKey(chunkX, chunkZ = pos.method_10260() >> 4));
        if (castleId != null) {
            return this.castles.get(castleId);
        }
        for (CastleInstance castle : this.castles.values()) {
            if (!CastleWorldState.containsChunk(castle, chunkX, chunkZ)) continue;
            this.claimIndex.put(CastleWorldState.chunkKey(chunkX, chunkZ), castle.getId());
            return castle;
        }
        return null;
    }

    public List<CastleInstance> findClaimsAroundChunk(int centerChunkX, int centerChunkZ, int width, int height) {
        ArrayList<CastleInstance> claims = new ArrayList<CastleInstance>();
        LinkedHashSet<String> seenCastleIds = new LinkedHashSet<String>();
        for (long chunkKey : LordWarPartyHelper.collectChunkKeysInWindow(centerChunkX, centerChunkZ, width, height)) {
            CastleInstance castle;
            String castleId = this.claimIndex.get(chunkKey);
            if (castleId == null || !seenCastleIds.add(castleId) || (castle = this.castles.get(castleId)) == null) continue;
            claims.add(castle);
        }
        return List.copyOf(claims);
    }

    public CastleInstance findOverlappingClaim(CastleInstance candidate) {
        int candidateChunkX = candidate.getBannerPos().method_10263() >> 4;
        int candidateChunkZ = candidate.getBannerPos().method_10260() >> 4;
        int candidateRadius = candidate.getClaimRadiusChunks();
        for (CastleInstance castle : this.castles.values()) {
            boolean overlapZ;
            if (castle.getId().equals(candidate.getId())) continue;
            int castleChunkX = castle.getBannerPos().method_10263() >> 4;
            int castleChunkZ = castle.getBannerPos().method_10260() >> 4;
            int castleRadius = castle.getClaimRadiusChunks();
            boolean overlapX = Math.abs(candidateChunkX - castleChunkX) <= candidateRadius + castleRadius;
            boolean bl = overlapZ = Math.abs(candidateChunkZ - castleChunkZ) <= candidateRadius + castleRadius;
            if (!overlapX || !overlapZ) continue;
            return castle;
        }
        return null;
    }

    private void rebuildIndexes() {
        this.bannerIndex.clear();
        this.claimIndex.clear();
        for (CastleInstance castle : this.castles.values()) {
            this.bannerIndex.put(castle.getBannerPos().method_10063(), castle.getId());
            int centerChunkX = castle.getBannerPos().method_10263() >> 4;
            int centerChunkZ = castle.getBannerPos().method_10260() >> 4;
            int radius = castle.getClaimRadiusChunks();
            for (int chunkX = centerChunkX - radius; chunkX <= centerChunkX + radius; ++chunkX) {
                for (int chunkZ = centerChunkZ - radius; chunkZ <= centerChunkZ + radius; ++chunkZ) {
                    this.claimIndex.putIfAbsent(CastleWorldState.chunkKey(chunkX, chunkZ), castle.getId());
                }
            }
        }
    }

    private static boolean containsChunk(CastleInstance castle, int chunkX, int chunkZ) {
        int centerChunkX = castle.getBannerPos().method_10263() >> 4;
        int centerChunkZ = castle.getBannerPos().method_10260() >> 4;
        int radius = castle.getClaimRadiusChunks();
        return Math.abs(chunkX - centerChunkX) <= radius && Math.abs(chunkZ - centerChunkZ) <= radius;
    }

    private static long chunkKey(int chunkX, int chunkZ) {
        return (long)chunkX << 32 ^ (long)chunkZ & 0xFFFFFFFFL;
    }

    public boolean isRejected(String castleId) {
        return this.rejectedCastleSiteIds.contains(castleId);
    }

    public void markRejected(String castleId) {
        if (this.rejectedCastleSiteIds.add(castleId)) {
            this.method_80();
        }
    }

    public boolean overlayCastlesGenerated() {
        return this.overlayCastlesGenerated;
    }

    public void setOverlayCastlesGenerated(boolean overlayCastlesGenerated) {
        if (this.overlayCastlesGenerated == overlayCastlesGenerated) {
            return;
        }
        this.overlayCastlesGenerated = overlayCastlesGenerated;
        this.method_80();
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 castlesList = new class_2499();
        for (CastleInstance castleInstance : this.castles.values()) {
            castlesList.add((Object)castleInstance.toNbt(registries));
        }
        nbt.method_10566("castles", (class_2520)castlesList);
        class_2499 pendingList = new class_2499();
        for (PendingCastleGeneration pendingCastleGeneration : this.pendingGenerations.values()) {
            pendingList.add((Object)pendingCastleGeneration.toNbt());
        }
        nbt.method_10566("pending_generations", (class_2520)pendingList);
        nbt.method_10569("castle_layout_pin_era", this.layoutPinEra);
        class_2499 class_24992 = new class_2499();
        for (Map.Entry<String, CastleLayoutPin> entry : this.layoutPinsByCastleId.entrySet()) {
            class_2487 class_24872 = new class_2487();
            class_24872.method_10582("castle_id", entry.getKey());
            class_24872.method_10569("version", entry.getValue().version());
            class_24872.method_10582("full_plan_fingerprint", entry.getValue().fullPlanFingerprint());
            class_24992.add((Object)class_24872);
        }
        nbt.method_10566("castle_layout_pins", (class_2520)class_24992);
        class_2499 class_24993 = new class_2499();
        for (CastleRecaptureState castleRecaptureState : this.recaptures.values()) {
            class_24993.add((Object)castleRecaptureState.toNbt());
        }
        nbt.method_10566("recaptures", (class_2520)class_24993);
        class_2499 class_24994 = new class_2499();
        for (Map.Entry<String, List<class_1799>> entry : this.ghostStorageByCastleId.entrySet()) {
            if (entry.getValue().isEmpty()) continue;
            class_2487 castleGhostNbt = new class_2487();
            castleGhostNbt.method_10582("castle_id", entry.getKey());
            class_2499 stacks = new class_2499();
            for (class_1799 stack : entry.getValue()) {
                class_2520 class_25202;
                if (stack.method_7960() || !((class_25202 = stack.method_57358(registries)) instanceof class_2487)) continue;
                class_2487 stackNbt = (class_2487)class_25202;
                stacks.add((Object)stackNbt);
            }
            castleGhostNbt.method_10566("stacks", (class_2520)stacks);
            class_24994.add((Object)castleGhostNbt);
        }
        nbt.method_10566("ghost_storage", (class_2520)class_24994);
        class_2499 class_24995 = new class_2499();
        for (String castleId : this.rejectedCastleSiteIds) {
            class_24995.add((Object)class_2519.method_23256((String)castleId));
        }
        nbt.method_10566("rejected_castle_site_ids", (class_2520)class_24995);
        nbt.method_10556("overlay_castles_generated", this.overlayCastlesGenerated);
        return nbt;
    }

    private static CastleWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        CastleWorldState state = new CastleWorldState();
        HashMap<String, Boolean> pendingLayoutVersionKeys = new HashMap<String, Boolean>();
        class_2499 castlesList = nbt.method_10554("castles", 10);
        for (int index = 0; index < castlesList.size(); ++index) {
            CastleInstance castle = CastleInstance.fromNbt(castlesList.method_10602(index), registries);
            state.castles.put(castle.getId(), castle);
        }
        class_2499 pendingList = nbt.method_10554("pending_generations", 10);
        for (int index = 0; index < pendingList.size(); ++index) {
            class_2487 pendingNbt = pendingList.method_10602(index);
            PendingCastleGeneration pendingGeneration = PendingCastleGeneration.fromNbt(pendingNbt);
            if (pendingGeneration.castleId().isBlank()) continue;
            state.pendingGenerations.put(pendingGeneration.castleId(), pendingGeneration);
            pendingLayoutVersionKeys.put(pendingGeneration.castleId(), pendingNbt.method_10545("road_layout_version"));
        }
        state.layoutPinEra = nbt.method_10545("castle_layout_pin_era") ? nbt.method_10550("castle_layout_pin_era") : 1;
        class_2499 layoutPins = nbt.method_10554("castle_layout_pins", 10);
        for (int index = 0; index < layoutPins.size(); ++index) {
            class_2487 pinNbt = layoutPins.method_10602(index);
            String castleId = pinNbt.method_10558("castle_id");
            if (castleId.isBlank()) continue;
            int version = pinNbt.method_10550("version") == 2 ? 2 : 1;
            state.layoutPinsByCastleId.put(castleId, new CastleLayoutPin(version, version == 2 ? pinNbt.method_10558("full_plan_fingerprint") : ""));
        }
        boolean normalizedPendingLayouts = false;
        if (!nbt.method_10545("castle_layout_pins")) {
            for (String castleId : state.castles.keySet()) {
                state.layoutPinsByCastleId.put(castleId, new CastleLayoutPin(1, ""));
            }
            for (PendingCastleGeneration pending : state.pendingGenerations.values()) {
                if (pending.roadLayoutVersion() == 2 && pending.stage() != PendingCastleGenerationStage.PLAN && pendingLayoutVersionKeys.getOrDefault(pending.castleId(), false).booleanValue()) continue;
                pending.setRoadLayoutVersion(1);
                pending.setRoadPlanFingerprint("");
                state.layoutPinsByCastleId.put(pending.castleId(), new CastleLayoutPin(1, ""));
                normalizedPendingLayouts = true;
            }
        } else {
            for (PendingCastleGeneration pending : state.pendingGenerations.values()) {
                CastleLayoutPin pin = state.layoutPinsByCastleId.get(pending.castleId());
                if (pin == null) {
                    if (pending.roadLayoutVersion() == 2 && pending.stage() != PendingCastleGenerationStage.PLAN) continue;
                    state.layoutPinsByCastleId.put(pending.castleId(), new CastleLayoutPin(1, ""));
                    pending.setRoadLayoutVersion(1);
                    pending.setRoadPlanFingerprint("");
                    normalizedPendingLayouts = true;
                    continue;
                }
                if (pin.version() == 2) {
                    boolean missingOrLegacyPendingVersion = pending.roadLayoutVersion() != 2 || pendingLayoutVersionKeys.getOrDefault(pending.castleId(), false) == false;
                    if (!missingOrLegacyPendingVersion || !CastleWorldState.canUpgradePendingToDurableV2(pending)) continue;
                    pending.setRoadLayoutVersion(2);
                    pending.setRoadPlanFingerprint(pin.fullPlanFingerprint());
                    normalizedPendingLayouts = true;
                    continue;
                }
                if (pending.roadLayoutVersion() != 2) {
                    if (pending.roadPlanFingerprint().isBlank()) continue;
                    pending.setRoadPlanFingerprint("");
                    normalizedPendingLayouts = true;
                    continue;
                }
                if (pending.stage() != PendingCastleGenerationStage.PLAN) continue;
                pending.setRoadLayoutVersion(1);
                pending.setRoadPlanFingerprint("");
                normalizedPendingLayouts = true;
            }
        }
        if (normalizedPendingLayouts) {
            state.method_80();
        }
        class_2499 recapturesList = nbt.method_10554("recaptures", 10);
        for (int index = 0; index < recapturesList.size(); ++index) {
            CastleRecaptureState recapture = CastleRecaptureState.fromNbt(recapturesList.method_10602(index));
            if (recapture.getCastleId().isBlank()) continue;
            state.recaptures.put(recapture.getCastleId(), recapture);
        }
        class_2499 ghostStorageList = nbt.method_10554("ghost_storage", 10);
        for (int index = 0; index < ghostStorageList.size(); ++index) {
            class_2487 castleGhostNbt = ghostStorageList.method_10602(index);
            String castleId = castleGhostNbt.method_10558("castle_id");
            if (castleId.isBlank()) continue;
            class_2499 stacksNbt = castleGhostNbt.method_10554("stacks", 10);
            ArrayList<class_1799> stacks = new ArrayList<class_1799>();
            for (int stackIndex = 0; stackIndex < stacksNbt.size(); ++stackIndex) {
                class_1799 stack = class_1799.method_57359((class_7225.class_7874)registries, (class_2487)stacksNbt.method_10602(stackIndex));
                if (stack.method_7960()) continue;
                stacks.add(stack);
            }
            if (stacks.isEmpty()) continue;
            state.ghostStorageByCastleId.put(castleId, stacks);
        }
        class_2499 rejectedList = nbt.method_10554("rejected_castle_site_ids", 8);
        for (int index = 0; index < rejectedList.size(); ++index) {
            state.rejectedCastleSiteIds.add(rejectedList.method_10608(index));
        }
        state.overlayCastlesGenerated = nbt.method_10577("overlay_castles_generated");
        state.rebuildIndexes();
        return state;
    }

    private static boolean canUpgradePendingToDurableV2(PendingCastleGeneration pending) {
        if (pending.stage() == PendingCastleGenerationStage.PLAN) {
            return true;
        }
        return pending.stage() == PendingCastleGenerationStage.STRUCTURES && pending.dynamic() && pending.worldgenShellPlaced() && !pending.dynamicCoreUsesCastleKeep() && pending.dynamicCoreClearanceHeight() == 0 && pending.roadPlanFingerprint().isBlank() && pending.corePhase() == 0 && pending.coreCursor() == 0 && pending.nextStructureIndex() == 0 && pending.nextStructureChunkCursor() == 0 && pending.infrastructurePhase() == 0 && pending.nextWallChunkCursor() == 0 && pending.nextNatureStructureCursor() == 0 && pending.nextRoadChunkCursor() == 0 && pending.nextRoadCellCursor() == 0 && pending.plannedStructures().isEmpty() && pending.dynamicBuildingChunks().isEmpty() && pending.structureCandidateChunks().isEmpty() && pending.placedStructures().isEmpty() && pending.wallGateClaims().isEmpty() && pending.dynamicCoreNorthEdgeHeights().length == 0 && pending.dynamicCoreSouthEdgeHeights().length == 0 && pending.dynamicCoreWestEdgeHeights().length == 0 && pending.dynamicCoreEastEdgeHeights().length == 0;
    }

    private void flushGhostStorage(CastleInstance castle) {
        if (castle == null) {
            return;
        }
        List<class_1799> ghostStacks = this.ghostStorageByCastleId.get(castle.getId());
        if (ghostStacks == null || ghostStacks.isEmpty()) {
            return;
        }
        ArrayList<class_1799> remaining = new ArrayList<class_1799>();
        for (class_1799 ghostStack : ghostStacks) {
            if (ghostStack.method_7960()) continue;
            class_1799 toInsert = ghostStack.method_7972();
            CastleWorldState.insertIntoStorage(castle.getStorageItems(), toInsert, castle.getStorageSize());
            if (toInsert.method_7960()) continue;
            remaining.add(toInsert);
        }
        if (remaining.isEmpty()) {
            this.ghostStorageByCastleId.remove(castle.getId());
        } else {
            this.ghostStorageByCastleId.put(castle.getId(), remaining);
        }
    }

    private static int insertIntoStorage(List<class_1799> storage, class_1799 stack, int maxSlots) {
        if (storage == null || stack == null || stack.method_7960()) {
            return 0;
        }
        int inserted = 0;
        for (class_1799 stored : storage) {
            int space;
            if (stack.method_7960()) {
                return inserted;
            }
            if (stored.method_7960() || !class_1799.method_31577((class_1799)stored, (class_1799)stack) || (space = Math.min(stored.method_7914(), 64) - stored.method_7947()) <= 0) continue;
            int moved = Math.min(space, stack.method_7947());
            stored.method_7933(moved);
            stack.method_7934(moved);
            inserted += moved;
        }
        while (!stack.method_7960() && storage.size() < maxSlots) {
            class_1799 movedStack = stack.method_7972();
            int moved = Math.min(stack.method_7947(), movedStack.method_7914());
            movedStack.method_7939(moved);
            storage.add(movedStack);
            stack.method_7934(moved);
            inserted += moved;
        }
        return inserted;
    }

    public record CastleLayoutPin(int version, String fullPlanFingerprint) {
        public CastleLayoutPin {
            version = version == 2 ? 2 : 1;
            fullPlanFingerprint = fullPlanFingerprint == null ? "" : fullPlanFingerprint;
        }
    }
}

