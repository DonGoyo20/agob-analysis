/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.minecraft.class_1297
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_243
 *  net.minecraft.class_3218
 *  net.minecraft.class_5575
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.troop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_5575;
import net.minecraft.server.MinecraftServer;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.PartyTroopEntity;
import trep.got.troop.DynamicTroopUnitType;
import trep.got.troop.GotTroops;
import trep.got.troop.PartyFormationType;

final class MarriageCompanionFormationCache {
    private static final Map<MinecraftServer, Map<class_3218, WorldCache>> CACHES_BY_SERVER = new IdentityHashMap<MinecraftServer, Map<class_3218, WorldCache>>();
    private static final Comparator<PartyTroopEntity> TROOP_ORDER = Comparator.comparingInt(troop -> troop.getTroopDefinition() == null ? Integer.MAX_VALUE : GotTroops.formationGroupIndex(troop.getTroopDefinition().unitType())).thenComparingInt(PartyTroopEntity::getUnitId);
    private static boolean initialized;

    private MarriageCompanionFormationCache() {
    }

    static void initialize() {
        if (initialized) {
            return;
        }
        initialized = true;
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> MarriageCompanionFormationCache.updateForEntity(entity, world, true));
        ServerEntityEvents.ENTITY_UNLOAD.register((entity, world) -> MarriageCompanionFormationCache.updateForEntity(entity, world, false));
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {
            class_1937 patt0$temp = entity.method_37908();
            if (patt0$temp instanceof class_3218) {
                class_3218 world = (class_3218)patt0$temp;
                MarriageCompanionFormationCache.updateForEntity((class_1297)entity, world, false);
            }
        });
        ServerTickEvents.END_WORLD_TICK.register(MarriageCompanionFormationCache::clearWorld);
        ServerLifecycleEvents.SERVER_STOPPED.register(MarriageCompanionFormationCache::clearServer);
    }

    static class_2338 getSlot(class_3218 world, UUID ownerUuid, AbstractKingdomNpcEntity companion, class_243 anchorCenter, float yaw, PartyFormationType formationType) {
        class_2338 fallback = class_2338.method_49638((class_2374)anchorCenter);
        if (!MarriageCompanionFormationCache.isLiveCompanion(world, ownerUuid, companion)) {
            return fallback;
        }
        long serverTick = world.method_8503().method_3780();
        WorldSnapshot worldSnapshot = MarriageCompanionFormationCache.getWorldCache(world).getOrBuild(world, serverTick);
        OwnerSnapshot ownerSnapshot = worldSnapshot.owner(ownerUuid);
        Integer companionIndex = ownerSnapshot.companionIndexes.get(companion);
        if (companionIndex == null) {
            ownerSnapshot.includeCompanion(companion);
            companionIndex = ownerSnapshot.companionIndexes.get(companion);
            if (companionIndex == null) {
                return fallback;
            }
        }
        FormationKey key = FormationKey.of(anchorCenter, yaw, formationType);
        List slots = ownerSnapshot.slotsByFormation.computeIfAbsent(key, ignored -> GotTroops.calculateFormationSlotsForUnitTypes((class_1937)world, anchorCenter, yaw, ownerSnapshot.unitTypes, formationType, true));
        int slotIndex = ownerSnapshot.deployedTroopCount + companionIndex;
        return slotIndex < slots.size() ? (class_2338)slots.get(slotIndex) : fallback;
    }

    static void onMembershipChanged(AbstractKingdomNpcEntity companion, String previousOwnerUuid, boolean previousPartyCompanion, String currentOwnerUuid, boolean currentPartyCompanion) {
        class_1937 class_19372 = companion.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        Map<class_3218, WorldCache> byWorld = CACHES_BY_SERVER.get(world.method_8503());
        if (byWorld == null) {
            return;
        }
        WorldCache worldCache = byWorld.get(world);
        if (worldCache != null) {
            worldCache.updateMembership(companion, previousOwnerUuid, previousPartyCompanion, currentOwnerUuid, currentPartyCompanion, world.method_8469(companion.method_5628()) == companion, world.method_8503().method_3780());
        }
    }

    private static WorldSnapshot buildWorldSnapshot(class_3218 world) {
        HashMap troopsByOwner = new HashMap();
        for (Object troop : world.method_18198(class_5575.method_31795(PartyTroopEntity.class), class_1297::method_5805)) {
            UUID ownerUuid = ((PartyTroopEntity)troop).getOwnerUuid();
            if (ownerUuid == null) continue;
            troopsByOwner.computeIfAbsent(ownerUuid, ignored -> new ArrayList()).add(troop);
        }
        HashMap companionsByOwner = new HashMap();
        for (AbstractKingdomNpcEntity companion : world.method_18198(class_5575.method_31795(AbstractKingdomNpcEntity.class), entity -> entity.method_5805() && entity.isMarriagePartyCompanion())) {
            UUID ownerUuid = MarriageCompanionFormationCache.parseUuid(companion.getMarriedPlayerUuid());
            if (ownerUuid == null) continue;
            companionsByOwner.computeIfAbsent(ownerUuid, ignored -> new ArrayList()).add(companion);
        }
        HashSet owners = new HashSet(troopsByOwner.keySet());
        owners.addAll(companionsByOwner.keySet());
        HashMap<UUID, OwnerSnapshot> snapshotsByOwner = new HashMap<UUID, OwnerSnapshot>();
        for (UUID ownerUuid : owners) {
            snapshotsByOwner.put(ownerUuid, new OwnerSnapshot(troopsByOwner.getOrDefault(ownerUuid, List.of()), companionsByOwner.getOrDefault(ownerUuid, List.of())));
        }
        return new WorldSnapshot(snapshotsByOwner);
    }

    private static boolean isLiveCompanion(class_3218 world, UUID ownerUuid, AbstractKingdomNpcEntity companion) {
        return companion.method_37908() == world && companion.method_5805() && !companion.method_31481() && companion.isMarriagePartyCompanion() && ownerUuid.toString().equals(companion.getMarriedPlayerUuid());
    }

    private static WorldCache getWorldCache(class_3218 world) {
        Map byWorld = CACHES_BY_SERVER.computeIfAbsent(world.method_8503(), ignored -> new IdentityHashMap());
        return byWorld.computeIfAbsent(world, ignored -> new WorldCache());
    }

    private static void updateForEntity(class_1297 entity, class_3218 world, boolean loaded) {
        if (!(entity instanceof PartyTroopEntity) && !(entity instanceof AbstractKingdomNpcEntity)) {
            return;
        }
        Map<class_3218, WorldCache> byWorld = CACHES_BY_SERVER.get(world.method_8503());
        if (byWorld == null) {
            return;
        }
        WorldCache worldCache = byWorld.get(world);
        if (worldCache != null) {
            worldCache.updateEntity(entity, loaded, world.method_8503().method_3780());
        }
    }

    private static UUID parseUuid(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return UUID.fromString(value);
        }
        catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    private static void clearWorld(class_3218 world) {
        MinecraftServer server = world.method_8503();
        Map<class_3218, WorldCache> byWorld = CACHES_BY_SERVER.get(server);
        if (byWorld == null) {
            return;
        }
        byWorld.remove(world);
        if (byWorld.isEmpty()) {
            CACHES_BY_SERVER.remove(server);
        }
    }

    private static void clearServer(MinecraftServer server) {
        CACHES_BY_SERVER.remove(server);
    }

    private static final class WorldCache {
        private WorldSnapshot snapshot;
        private long currentTick = Long.MIN_VALUE;

        private WorldCache() {
        }

        private WorldSnapshot getOrBuild(class_3218 world, long serverTick) {
            if (this.currentTick != serverTick) {
                this.clear();
                this.currentTick = serverTick;
            }
            if (this.snapshot == null) {
                this.snapshot = MarriageCompanionFormationCache.buildWorldSnapshot(world);
            }
            return this.snapshot;
        }

        private void updateEntity(class_1297 entity, boolean loaded, long serverTick) {
            if (this.currentTick != serverTick) {
                this.clear();
                return;
            }
            if (this.snapshot != null) {
                this.snapshot.updateEntity(entity, loaded);
            }
        }

        private void updateMembership(AbstractKingdomNpcEntity companion, String previousOwnerUuid, boolean previousPartyCompanion, String currentOwnerUuid, boolean currentPartyCompanion, boolean loaded, long serverTick) {
            if (this.currentTick != serverTick) {
                this.clear();
                return;
            }
            if (this.snapshot != null) {
                this.snapshot.updateMembership(companion, previousOwnerUuid, previousPartyCompanion, currentOwnerUuid, currentPartyCompanion, loaded);
            }
        }

        private void clear() {
            this.snapshot = null;
            this.currentTick = Long.MIN_VALUE;
        }
    }

    private static final class WorldSnapshot {
        private final Map<UUID, OwnerSnapshot> snapshotsByOwner;

        private WorldSnapshot(Map<UUID, OwnerSnapshot> snapshotsByOwner) {
            this.snapshotsByOwner = snapshotsByOwner;
        }

        private OwnerSnapshot owner(UUID ownerUuid) {
            return this.snapshotsByOwner.computeIfAbsent(ownerUuid, ignored -> new OwnerSnapshot(List.of(), List.of()));
        }

        private void updateEntity(class_1297 entity, boolean loaded) {
            UUID ownerUuid;
            AbstractKingdomNpcEntity companion;
            for (OwnerSnapshot snapshot : this.snapshotsByOwner.values()) {
                if (snapshot.removeEntity(entity)) break;
            }
            if (!loaded || !entity.method_5805() || entity.method_31481()) {
                return;
            }
            if (entity instanceof PartyTroopEntity) {
                PartyTroopEntity troop = (PartyTroopEntity)entity;
                UUID ownerUuid2 = troop.getOwnerUuid();
                if (ownerUuid2 != null) {
                    this.owner(ownerUuid2).includeTroop(troop);
                }
            } else if (entity instanceof AbstractKingdomNpcEntity && (companion = (AbstractKingdomNpcEntity)entity).isMarriagePartyCompanion() && (ownerUuid = MarriageCompanionFormationCache.parseUuid(companion.getMarriedPlayerUuid())) != null) {
                this.owner(ownerUuid).includeCompanion(companion);
            }
        }

        private void updateMembership(AbstractKingdomNpcEntity companion, String previousOwnerUuid, boolean previousPartyCompanion, String currentOwnerUuid, boolean currentPartyCompanion, boolean loaded) {
            UUID ownerUuid;
            if (previousPartyCompanion) {
                OwnerSnapshot previousOwner;
                ownerUuid = MarriageCompanionFormationCache.parseUuid(previousOwnerUuid);
                OwnerSnapshot ownerSnapshot = previousOwner = ownerUuid == null ? null : this.snapshotsByOwner.get(ownerUuid);
                if (previousOwner != null) {
                    previousOwner.removeEntity((class_1297)companion);
                }
            }
            if (!currentPartyCompanion || !loaded || !companion.method_5805() || companion.method_31481()) {
                return;
            }
            ownerUuid = MarriageCompanionFormationCache.parseUuid(currentOwnerUuid);
            if (ownerUuid != null) {
                this.owner(ownerUuid).includeCompanion(companion);
            }
        }
    }

    private static final class OwnerSnapshot {
        private int deployedTroopCount;
        private final ArrayList<PartyTroopEntity> deployedTroops;
        private final ArrayList<DynamicTroopUnitType> unitTypes = new ArrayList();
        private final ArrayList<AbstractKingdomNpcEntity> companions;
        private final IdentityHashMap<AbstractKingdomNpcEntity, Integer> companionIndexes = new IdentityHashMap();
        private final Map<FormationKey, List<class_2338>> slotsByFormation = new HashMap<FormationKey, List<class_2338>>();

        private OwnerSnapshot(List<PartyTroopEntity> deployedTroops, List<AbstractKingdomNpcEntity> companions) {
            this.deployedTroops = new ArrayList<PartyTroopEntity>(deployedTroops);
            this.companions = new ArrayList<AbstractKingdomNpcEntity>(companions);
            this.rebuildMembership();
        }

        private void includeTroop(PartyTroopEntity troop) {
            if (OwnerSnapshot.containsIdentity(this.deployedTroops, troop)) {
                return;
            }
            this.deployedTroops.add(troop);
            this.rebuildMembership();
        }

        private void includeCompanion(AbstractKingdomNpcEntity companion) {
            if (this.companionIndexes.containsKey(companion)) {
                return;
            }
            this.companions.add(companion);
            this.rebuildMembership();
        }

        private boolean removeEntity(class_1297 entity) {
            AbstractKingdomNpcEntity companion;
            PartyTroopEntity troop;
            boolean changed = entity instanceof PartyTroopEntity && OwnerSnapshot.removeIdentity(this.deployedTroops, troop = (PartyTroopEntity)entity);
            if (changed |= entity instanceof AbstractKingdomNpcEntity && OwnerSnapshot.removeIdentity(this.companions, companion = (AbstractKingdomNpcEntity)entity)) {
                this.rebuildMembership();
            }
            return changed;
        }

        private void rebuildMembership() {
            this.deployedTroops.sort(TROOP_ORDER);
            this.companions.sort(Comparator.comparingInt(class_1297::method_5628));
            this.deployedTroopCount = this.deployedTroops.size();
            this.unitTypes.clear();
            for (PartyTroopEntity troop : this.deployedTroops) {
                this.unitTypes.add(troop.getTroopDefinition() == null ? DynamicTroopUnitType.MELEE : troop.getTroopDefinition().unitType());
            }
            this.companionIndexes.clear();
            for (int index = 0; index < this.companions.size(); ++index) {
                this.unitTypes.add(DynamicTroopUnitType.MELEE);
                this.companionIndexes.put(this.companions.get(index), index);
            }
            this.slotsByFormation.clear();
        }

        private static <T> boolean containsIdentity(List<T> values, T target) {
            for (T value : values) {
                if (value != target) continue;
                return true;
            }
            return false;
        }

        private static <T> boolean removeIdentity(List<T> values, T target) {
            return values.removeIf(value -> value == target);
        }
    }

    private record FormationKey(long x, long y, long z, int yaw, PartyFormationType formationType) {
        private static FormationKey of(class_243 anchorCenter, float yaw, PartyFormationType formationType) {
            return new FormationKey(Double.doubleToLongBits(anchorCenter.field_1352), Double.doubleToLongBits(anchorCenter.field_1351), Double.doubleToLongBits(anchorCenter.field_1350), Float.floatToIntBits(yaw), formationType);
        }
    }
}

