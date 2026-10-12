/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.guard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.economy.GotEconomy;
import trep.got.entity.AbstractKingdomGuardNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.network.OpenGateWarningPayload;
import trep.got.registry.GotNpcRelations;
import trep.got.relations.KingdomRelationTier;
import trep.got.relations.PlayerKingdomRelationsHolder;

public final class GateWatcherGuardService {
    private static final double WARNING_RANGE = 8.0;
    private static final double GATE_PAIR_RANGE = 14.0;
    private static final double REINFORCEMENT_RANGE = 32.0;
    private static final double WALK_AWAY_CLEAR_DISTANCE = 18.0;
    private static final double WALK_AWAY_CLOSER_TOLERANCE = 1.0;
    private static final long WARNING_COOLDOWN_TICKS = 80L;
    private static final long WALK_AWAY_TIMEOUT_TICKS = 240L;
    private static final long HOSTILITY_TIMEOUT_TICKS = 2400L;
    private static final long BRIBE_PASS_TICKS = 2400L;
    private static final int BRIBE_GOLD_INGOTS = 8;
    private static final int WARNING_SCAN_INTERVAL_TICKS = 20;
    private static final int ACTIVE_STATE_INTERVAL_TICKS = 20;
    private static final Map<UUID, PendingWarning> PENDING_WARNINGS = new LinkedHashMap<UUID, PendingWarning>();
    private static final Map<UUID, WalkAwayOrder> WALK_AWAY_ORDERS = new LinkedHashMap<UUID, WalkAwayOrder>();
    private static final Map<UUID, GateHostility> ACTIVE_HOSTILITIES = new LinkedHashMap<UUID, GateHostility>();
    private static final Map<String, Long> TEMPORARY_PASSES = new LinkedHashMap<String, Long>();
    private static final Map<String, Long> WARNING_COOLDOWNS = new LinkedHashMap<String, Long>();

    private GateWatcherGuardService() {
    }

    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(GateWatcherGuardService::tickScheduled);
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            UUID playerUuid = handler.field_14140.method_5667();
            String playerKeyPrefix = String.valueOf(playerUuid) + "|";
            PENDING_WARNINGS.remove(playerUuid);
            WALK_AWAY_ORDERS.remove(playerUuid);
            ACTIVE_HOSTILITIES.remove(playerUuid);
            TEMPORARY_PASSES.keySet().removeIf(key -> key.startsWith(playerKeyPrefix));
            WARNING_COOLDOWNS.keySet().removeIf(key -> key.startsWith(playerKeyPrefix));
        });
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> GateWatcherGuardService.clearRuntimeState());
    }

    private static void tickScheduled(MinecraftServer server) {
        if (server == null) {
            return;
        }
        int ticks = server.method_3780();
        if (ticks % 20 == 0 && GateWatcherGuardService.hasActiveState()) {
            GateWatcherGuardService.tickActiveStates(server);
        }
        if (ticks % 20 == 0) {
            GateWatcherGuardService.scanGateWatcherWarnings(server);
        }
    }

    private static void scanGateWatcherWarnings(MinecraftServer server) {
        if (server.method_3760().method_14571().isEmpty()) {
            return;
        }
        for (class_3218 world : server.method_3738()) {
            for (class_3222 player : world.method_18456()) {
                AbstractKingdomGuardNpcEntity guard2;
                class_238 searchBox;
                List watchers;
                if (!player.method_5805() || player.method_7325() || PENDING_WARNINGS.containsKey(player.method_5667()) || WALK_AWAY_ORDERS.containsKey(player.method_5667()) || ACTIVE_HOSTILITIES.containsKey(player.method_5667()) || (watchers = world.method_8390(AbstractKingdomGuardNpcEntity.class, searchBox = class_238.method_30048((class_243)player.method_19538(), (double)16.0, (double)5.0, (double)16.0), guard -> guard.method_5805() && guard.getNpcRole() == KingdomNpcRole.GATE_WATCHER && !guard.isInDialogueScene() && guard.method_5968() == null)).isEmpty()) continue;
                if (watchers.size() > 1) {
                    watchers.sort((first, second) -> Double.compare(first.method_5858((class_1297)player), second.method_5858((class_1297)player)));
                }
                Iterator iterator = watchers.iterator();
                while (iterator.hasNext() && !GateWatcherGuardService.tryOpenGateWarning(world, player, guard2 = (AbstractKingdomGuardNpcEntity)iterator.next())) {
                }
            }
        }
    }

    private static boolean tryOpenGateWarning(class_3218 world, class_3222 player, AbstractKingdomGuardNpcEntity guard) {
        CastleInstance castle = CastleWorldState.get(world).get(guard.got$getCastle());
        if (castle == null || castle.getOwnerKingdomId().isBlank()) {
            return false;
        }
        if (GateWatcherGuardService.hasTemporaryPass(world, player, castle.getId()) || GateWatcherGuardService.isNeutralOrAbove(player, castle)) {
            return false;
        }
        String cooldownKey = String.valueOf(player.method_5667()) + "|" + String.valueOf(guard.method_5667());
        if (WARNING_COOLDOWNS.getOrDefault(cooldownKey, 0L) > world.method_8510()) {
            return false;
        }
        if (guard.forceBeginDialogue(player)) {
            PENDING_WARNINGS.put(player.method_5667(), new PendingWarning(guard.method_5667(), castle.getId(), world.method_8510()));
            WARNING_COOLDOWNS.put(cooldownKey, world.method_8510() + 80L);
            ServerPlayNetworking.send((class_3222)player, (class_8710)new OpenGateWarningPayload(guard.method_5628()));
            return true;
        }
        return false;
    }

    private static void tickActiveStates(MinecraftServer server) {
        if (!TEMPORARY_PASSES.isEmpty() || !WARNING_COOLDOWNS.isEmpty()) {
            for (class_3218 world : server.method_3738()) {
                long now = world.method_8510();
                TEMPORARY_PASSES.entrySet().removeIf(entry -> (Long)entry.getValue() <= now);
                WARNING_COOLDOWNS.entrySet().removeIf(entry -> (Long)entry.getValue() <= now);
            }
        }
        if (!WALK_AWAY_ORDERS.isEmpty()) {
            GateWatcherGuardService.tickWalkAwayOrders(server);
        }
        if (!ACTIVE_HOSTILITIES.isEmpty()) {
            GateWatcherGuardService.tickHostilities(server);
        }
    }

    private static boolean hasActiveState() {
        return !TEMPORARY_PASSES.isEmpty() || !WARNING_COOLDOWNS.isEmpty() || !WALK_AWAY_ORDERS.isEmpty() || !ACTIVE_HOSTILITIES.isEmpty();
    }

    private static void clearRuntimeState() {
        PENDING_WARNINGS.clear();
        WALK_AWAY_ORDERS.clear();
        ACTIVE_HOSTILITIES.clear();
        TEMPORARY_PASSES.clear();
        WARNING_COOLDOWNS.clear();
    }

    public static void handleGateWarningAction(class_3222 player, int guardEntityId, String actionId) {
        AbstractKingdomGuardNpcEntity guard;
        class_1937 class_19372;
        PendingWarning warning = PENDING_WARNINGS.get(player.method_5667());
        if (warning == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        class_1297 entity = world.method_14190(warning.guardUuid());
        if (!(entity instanceof AbstractKingdomGuardNpcEntity) || (guard = (AbstractKingdomGuardNpcEntity)entity).method_5628() != guardEntityId || !guard.method_5805() || guard.getNpcRole() != KingdomNpcRole.GATE_WATCHER) {
            return;
        }
        PENDING_WARNINGS.remove(player.method_5667());
        guard.endDialogue(player.method_5667());
        if ("bribe".equals(actionId)) {
            GateWatcherGuardService.handleBribe(world, player, guard, warning.castleId());
            return;
        }
        GateWatcherGuardService.startWalkAway(world, player, guard, warning.castleId());
    }

    public static boolean isInteractionLocked(class_3222 player, class_1297 entity) {
        if (player == null || entity == null) {
            return false;
        }
        PendingWarning warning = PENDING_WARNINGS.get(player.method_5667());
        if (warning != null && warning.guardUuid().equals(entity.method_5667())) {
            return true;
        }
        GateHostility hostility = ACTIVE_HOSTILITIES.get(player.method_5667());
        return hostility != null && hostility.guardUuids().contains(entity.method_5667());
    }

    private static void handleBribe(class_3218 world, class_3222 player, AbstractKingdomGuardNpcEntity guard, String castleId) {
        if (!GotEconomy.hasLegacyGoldIngots((class_1657)player, 8)) {
            player.method_7353((class_2561)class_2561.method_43470((String)("You need " + GotEconomy.formatLegacyGoldIngots(8) + ".")).method_27692(class_124.field_1061), true);
            GateWatcherGuardService.startWalkAway(world, player, guard, castleId);
            return;
        }
        GotEconomy.payLegacyGoldIngots(player, 8);
        if (world.field_9229.method_43056()) {
            TEMPORARY_PASSES.put(GateWatcherGuardService.passKey(player, castleId), world.method_8510() + 2400L);
            GotNpcRelations.addRelation(player, guard.getPersistentNpcId(), 5);
            GotNpcRelations.sendDialogueRelation(player, guard);
            player.method_7353((class_2561)class_2561.method_43470((String)"The guard lets you pass for now.").method_27692(class_124.field_1060), true);
            return;
        }
        player.method_7353((class_2561)class_2561.method_43470((String)"The guard takes offense.").method_27692(class_124.field_1061), true);
        GateWatcherGuardService.triggerGateHostility(world, player, castleId, GateWatcherGuardService.findGateWatcherPair(world, guard, castleId));
    }

    private static void startWalkAway(class_3218 world, class_3222 player, AbstractKingdomGuardNpcEntity guard, String castleId) {
        CastleInstance castle = CastleWorldState.get(world).get(castleId);
        if (castle == null) {
            return;
        }
        List<UUID> gateWatcherUuids = GateWatcherGuardService.findGateWatcherPair(world, guard, castleId).stream().map(class_1297::method_5667).toList();
        double startBannerDistance = player.method_5707(class_243.method_24953((class_2382)castle.getBannerPos()));
        double startGateDistance = GateWatcherGuardService.nearestGateWatcherDistanceSquared(world, player, gateWatcherUuids);
        double startGuardDistance = GateWatcherGuardService.nearestGuardDutyDistanceSquared(world, player, castleId);
        WALK_AWAY_ORDERS.put(player.method_5667(), new WalkAwayOrder(castleId, gateWatcherUuids, startBannerDistance, startGateDistance, startGuardDistance, world.method_8510()));
        player.method_7353((class_2561)class_2561.method_43470((String)"Walk away from the gate.").method_27692(class_124.field_1054), true);
    }

    private static void tickWalkAwayOrders(MinecraftServer server) {
        Iterator<Map.Entry<UUID, WalkAwayOrder>> iterator = WALK_AWAY_ORDERS.entrySet().iterator();
        while (iterator.hasNext()) {
            class_3218 world;
            class_3222 player;
            Map.Entry<UUID, WalkAwayOrder> entry;
            block9: {
                block8: {
                    class_1937 class_19372;
                    entry = iterator.next();
                    player = server.method_3760().method_14602(entry.getKey());
                    if (player == null || !((class_19372 = player.method_37908()) instanceof class_3218)) break block8;
                    world = (class_3218)class_19372;
                    if (player.method_5805()) break block9;
                }
                iterator.remove();
                continue;
            }
            WalkAwayOrder order = entry.getValue();
            CastleInstance castle = CastleWorldState.get(world).get(order.castleId());
            if (castle == null) {
                iterator.remove();
                continue;
            }
            List<AbstractKingdomGuardNpcEntity> watchers = GateWatcherGuardService.resolveGateWatchers(world, order.gateWatcherUuids(), order.castleId());
            if (watchers.isEmpty()) {
                iterator.remove();
                continue;
            }
            double bannerDistance = player.method_5707(class_243.method_24953((class_2382)castle.getBannerPos()));
            double gateDistance = GateWatcherGuardService.nearestGateWatcherDistanceSquared(world, player, order.gateWatcherUuids());
            double guardDistance = GateWatcherGuardService.nearestGuardDutyDistanceSquared(world, player, order.castleId());
            if (GateWatcherGuardService.movedCloser(bannerDistance, order.startBannerDistance()) || GateWatcherGuardService.movedCloser(gateDistance, order.startGateDistance()) || GateWatcherGuardService.movedCloser(guardDistance, order.startGuardDistance())) {
                GateWatcherGuardService.triggerGateHostility(world, player, order.castleId(), watchers);
                iterator.remove();
                continue;
            }
            if (world.method_8510() - order.startedAtTick() > 240L || Math.sqrt(gateDistance) >= Math.sqrt(order.startGateDistance()) + 18.0) {
                iterator.remove();
                continue;
            }
            GateWatcherGuardService.pushPlayerAway(player, castle.getBannerPos(), watchers);
        }
    }

    private static boolean movedCloser(double currentDistanceSquared, double startDistanceSquared) {
        return Math.sqrt(currentDistanceSquared) + 1.0 < Math.sqrt(startDistanceSquared);
    }

    private static void tickHostilities(MinecraftServer server) {
        Iterator<Map.Entry<UUID, GateHostility>> iterator = ACTIVE_HOSTILITIES.entrySet().iterator();
        while (iterator.hasNext()) {
            class_3218 world;
            class_3222 player;
            Map.Entry<UUID, GateHostility> entry;
            block8: {
                block7: {
                    class_1937 class_19372;
                    entry = iterator.next();
                    player = server.method_3760().method_14602(entry.getKey());
                    if (player == null || !((class_19372 = player.method_37908()) instanceof class_3218)) break block7;
                    world = (class_3218)class_19372;
                    if (player.method_5805()) break block8;
                }
                iterator.remove();
                continue;
            }
            GateHostility hostility = entry.getValue();
            if (world.method_8510() >= hostility.expiresAtTick()) {
                iterator.remove();
                continue;
            }
            List<AbstractKingdomGuardNpcEntity> watchers = GateWatcherGuardService.resolveGateWatchers(world, hostility.guardUuids(), hostility.castleId());
            if (watchers.isEmpty()) {
                iterator.remove();
                continue;
            }
            long expiresAtTick = world.method_8510() + 2400L;
            for (AbstractKingdomGuardNpcEntity watcher : watchers) {
                watcher.alertAgainst(player, expiresAtTick);
                GateWatcherGuardService.alertNearbyGuardDuty(world, player, watcher, hostility.castleId(), expiresAtTick);
            }
        }
    }

    private static void triggerGateHostility(class_3218 world, class_3222 player, String castleId, List<? extends AbstractKingdomNpcEntity> guards) {
        if (guards.isEmpty()) {
            return;
        }
        long expiresAtTick = world.method_8510() + 2400L;
        ArrayList<UUID> guardUuids = new ArrayList<UUID>();
        for (AbstractKingdomNpcEntity abstractKingdomNpcEntity : guards) {
            abstractKingdomNpcEntity.alertAgainst(player, expiresAtTick);
            guardUuids.add(abstractKingdomNpcEntity.method_5667());
        }
        ACTIVE_HOSTILITIES.put(player.method_5667(), new GateHostility(castleId, List.copyOf(guardUuids), expiresAtTick));
    }

    private static void alertNearbyGuardDuty(class_3218 world, class_3222 player, AbstractKingdomNpcEntity source, String castleId, long expiresAtTick) {
        class_238 box = class_238.method_30048((class_243)source.method_19538(), (double)64.0, (double)12.0, (double)64.0);
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, box, npc -> npc.method_5805() && castleId.equals(npc.got$getCastle()) && npc.getNpcRole().isGuardDuty())) {
            npc2.alertAgainst(player, expiresAtTick);
        }
    }

    private static List<AbstractKingdomGuardNpcEntity> findGateWatcherPair(class_3218 world, AbstractKingdomGuardNpcEntity anchor, String castleId) {
        class_238 box = class_238.method_30048((class_243)anchor.method_19538(), (double)28.0, (double)8.0, (double)28.0);
        List watchers = world.method_8390(AbstractKingdomGuardNpcEntity.class, box, guard -> guard.method_5805() && castleId.equals(guard.got$getCastle()) && guard.getNpcRole() == KingdomNpcRole.GATE_WATCHER);
        watchers.sort((first, second) -> Double.compare(first.method_5858((class_1297)anchor), second.method_5858((class_1297)anchor)));
        return watchers.stream().limit(2L).toList();
    }

    private static List<AbstractKingdomGuardNpcEntity> resolveGateWatchers(class_3218 world, List<UUID> guardUuids, String castleId) {
        ArrayList<AbstractKingdomGuardNpcEntity> watchers = new ArrayList<AbstractKingdomGuardNpcEntity>();
        for (UUID guardUuid : guardUuids) {
            AbstractKingdomGuardNpcEntity guard;
            class_1297 entity = world.method_14190(guardUuid);
            if (!(entity instanceof AbstractKingdomGuardNpcEntity) || !(guard = (AbstractKingdomGuardNpcEntity)entity).method_5805() || !castleId.equals(guard.got$getCastle()) || guard.getNpcRole() != KingdomNpcRole.GATE_WATCHER) continue;
            watchers.add(guard);
        }
        return watchers;
    }

    private static void pushPlayerAway(class_3222 player, class_2338 bannerPos, List<AbstractKingdomGuardNpcEntity> watchers) {
        class_243 avoid = class_243.method_24953((class_2382)bannerPos);
        for (AbstractKingdomGuardNpcEntity watcher : watchers) {
            avoid = avoid.method_1019(watcher.method_19538());
        }
        avoid = avoid.method_1021(1.0 / (double)(watchers.size() + 1));
        class_243 away = player.method_19538().method_1020(avoid);
        class_243 horizontal = new class_243(away.field_1352, 0.0, away.field_1350);
        if (horizontal.method_1027() < 1.0E-4) {
            horizontal = new class_243(1.0, 0.0, 0.0);
        }
        class_243 push = horizontal.method_1029().method_1021(0.08);
        player.method_5762(push.field_1352, 0.0, push.field_1350);
        player.field_6037 = true;
    }

    private static double nearestGateWatcherDistanceSquared(class_3218 world, class_3222 player, List<UUID> gateWatcherUuids) {
        double best = Double.MAX_VALUE;
        for (UUID guardUuid : gateWatcherUuids) {
            AbstractKingdomGuardNpcEntity guard;
            class_1297 entity = world.method_14190(guardUuid);
            if (!(entity instanceof AbstractKingdomGuardNpcEntity) || !(guard = (AbstractKingdomGuardNpcEntity)entity).method_5805()) continue;
            best = Math.min(best, player.method_5858((class_1297)guard));
        }
        return best == Double.MAX_VALUE ? 0.0 : best;
    }

    private static double nearestGuardDutyDistanceSquared(class_3218 world, class_3222 player, String castleId) {
        class_238 box = class_238.method_30048((class_243)player.method_19538(), (double)96.0, (double)32.0, (double)96.0);
        double best = Double.MAX_VALUE;
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, box, npc -> npc.method_5805() && castleId.equals(npc.got$getCastle()) && npc.getNpcRole().isGuardDuty())) {
            best = Math.min(best, player.method_5858((class_1297)npc2));
        }
        return best == Double.MAX_VALUE ? 0.0 : best;
    }

    private static boolean isNeutralOrAbove(class_3222 player, CastleInstance castle) {
        String kingdomId = castle.getOwnerKingdomId();
        if (kingdomId.isBlank()) {
            return true;
        }
        int relation = ((PlayerKingdomRelationsHolder)player).got$getKingdomRelationsProfile().getRelation(kingdomId);
        return KingdomRelationTier.fromValue(relation).ordinal() >= KingdomRelationTier.NEUTRAL.ordinal();
    }

    private static boolean hasTemporaryPass(class_3218 world, class_3222 player, String castleId) {
        Long expiresAtTick = TEMPORARY_PASSES.get(GateWatcherGuardService.passKey(player, castleId));
        return expiresAtTick != null && expiresAtTick > world.method_8510();
    }

    private static String passKey(class_3222 player, String castleId) {
        return String.valueOf(player.method_5667()) + "|" + castleId;
    }

    private static boolean hasItemCount(class_3222 player, class_1792 item, int requiredCount) {
        if (player.method_7337()) {
            return true;
        }
        int count = 0;
        for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
            if (!player.method_31548().method_5438(slot).method_31574(item) || (count += player.method_31548().method_5438(slot).method_7947()) < requiredCount) continue;
            return true;
        }
        return false;
    }

    private static void removeItems(class_3222 player, class_1792 item, int count) {
        if (player.method_7337()) {
            return;
        }
        int remaining = count;
        for (int slot = 0; slot < player.method_31548().method_5439() && remaining > 0; ++slot) {
            if (!player.method_31548().method_5438(slot).method_31574(item)) continue;
            int removed = Math.min(remaining, player.method_31548().method_5438(slot).method_7947());
            player.method_31548().method_5438(slot).method_7934(removed);
            remaining -= removed;
        }
    }

    private record PendingWarning(UUID guardUuid, String castleId, long startedAtTick) {
    }

    private record GateHostility(String castleId, List<UUID> guardUuids, long expiresAtTick) {
    }

    private record WalkAwayOrder(String castleId, List<UUID> gateWatcherUuids, double startBannerDistance, double startGateDistance, double startGuardDistance, long startedAtTick) {
    }
}

