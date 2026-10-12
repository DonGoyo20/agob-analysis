/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.event.player.UseBlockCallback
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2281
 *  net.minecraft.class_2323
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2353
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2680
 *  net.minecraft.class_2745
 *  net.minecraft.class_2756
 *  net.minecraft.class_2769
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3908
 *  net.minecraft.class_3965
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.registry;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2281;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2745;
import net.minecraft.class_2756;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3908;
import net.minecraft.class_3965;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.castle.CityStructureClaim;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.lockpicking.LockpickingWorldState;
import trep.got.lockpicking.PlayerLockpickingHolder;
import trep.got.network.OpenLockpickingCaughtPayload;
import trep.got.network.OpenLockpickingPayload;
import trep.got.registry.GotCastles;
import trep.got.registry.GotItems;
import trep.got.registry.GotPlayerKingdomActions;
import trep.got.registry.GotRelations;
import trep.got.relations.PlayerKingdomRelationsHolder;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeType;
import trep.got.skill.SkillExperienceService;
import trep.got.village.VillageWorldState;

public final class GotLockpicking {
    private static final long SESSION_TIMEOUT_TICKS = 900L;
    private static final double MAX_CHEST_DISTANCE_SQUARED = 64.0;
    private static final double WITNESS_VIEW_RANGE = 24.0;
    private static final double WITNESS_CATCH_RANGE = 6.0;
    private static final double GUARD_REPORT_RANGE = 64.0;
    private static final long REPORT_TIMEOUT_TICKS = 2400L;
    private static final long REPORT_GUARD_SCAN_INTERVAL_TICKS = 20L;
    private static final double MIN_WITNESS_DOT = 0.35;
    private static final Map<UUID, PendingLockpickSession> PENDING_SESSIONS = new LinkedHashMap<UUID, PendingLockpickSession>();
    private static final Map<UUID, PendingWitness> PENDING_WITNESSES = new LinkedHashMap<UUID, PendingWitness>();
    private static final Map<UUID, PendingCaughtDialogue> PENDING_DIALOGUES = new LinkedHashMap<UUID, PendingCaughtDialogue>();
    private static final Map<UUID, ActiveReport> ACTIVE_REPORTS = new LinkedHashMap<UUID, ActiveReport>();

    private GotLockpicking() {
    }

    public static void initialize() {
        UseBlockCallback.EVENT.register(GotLockpicking::handleLockedBlockUse);
        ServerTickEvents.END_SERVER_TICK.register(GotLockpicking::tickSessions);
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            UUID playerUuid = handler.field_14140.method_5667();
            PENDING_SESSIONS.remove(playerUuid);
            PENDING_WITNESSES.remove(playerUuid);
            PENDING_DIALOGUES.remove(playerUuid);
            ACTIVE_REPORTS.values().removeIf(report -> report.playerUuid().equals(playerUuid));
        });
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> ((PlayerLockpickingHolder)newPlayer).got$copyUnlockedLockpickTargetsFrom((PlayerLockpickingHolder)oldPlayer));
    }

    private static class_1269 handleLockedBlockUse(class_1657 player, class_1937 world, class_1268 hand, class_3965 hitResult) {
        class_3222 serverPlayer;
        block9: {
            block8: {
                if (world.method_8608() || !(player instanceof class_3222)) break block8;
                serverPlayer = (class_3222)player;
                if (world instanceof class_3218) break block9;
            }
            return class_1269.field_5811;
        }
        class_3218 serverWorld = (class_3218)world;
        if (player.method_7325()) {
            return class_1269.field_5811;
        }
        class_2338 pos = GotLockpicking.normalizeTargetPos(world, hitResult.method_17777());
        class_2680 state = world.method_8320(pos);
        if (!GotLockpicking.isLockpickableTarget(state) || !GotLockpicking.isInCastleOrVillageClaim(serverWorld, pos)) {
            return class_1269.field_5811;
        }
        if (GotLockpicking.isPlacedTargetOwner(serverPlayer, serverWorld, pos)) {
            GotLockpicking.openLockpickableTarget(serverPlayer, serverWorld, pos);
            return class_1269.field_5812;
        }
        if (GotLockpicking.hasUnlockedTarget(serverPlayer, serverWorld, pos)) {
            GotLockpicking.openLockpickableTarget(serverPlayer, serverWorld, pos);
            return class_1269.field_5812;
        }
        class_1268 lockpickHand = GotLockpicking.findHeldLockpickHand(serverPlayer, hand);
        if (lockpickHand == null) {
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"Locked").method_27692(class_124.field_1061), true);
            return class_1269.field_5814;
        }
        LockpickingWorldState.LockpickSetup setup = GotLockpicking.getOrCreateSetup(serverWorld, pos);
        PENDING_SESSIONS.put(serverPlayer.method_5667(), new PendingLockpickSession(pos.method_10062(), lockpickHand, serverWorld.method_8510()));
        GotLockpicking.scheduleWitnessIfSeen(serverPlayer, serverWorld, pos);
        ServerPlayNetworking.send((class_3222)serverPlayer, (class_8710)new OpenLockpickingPayload(pos.method_10062(), setup.lockCount(), setup.speedMultiplier(), setup.difficultyId(), setup.seed()));
        return class_1269.field_5812;
    }

    public static void handleResult(class_3222 player, class_2338 chestPos, boolean success) {
        PendingLockpickSession session = PENDING_SESSIONS.remove(player.method_5667());
        PENDING_WITNESSES.remove(player.method_5667());
        if (session == null || !session.chestPos().equals((Object)chestPos)) {
            return;
        }
        class_1937 class_19372 = player.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        if (world.method_8510() - session.startedAtTick() > 900L) {
            return;
        }
        if (!success) {
            GotLockpicking.consumeLockpick(player, session.hand());
            player.method_7353((class_2561)class_2561.method_43470((String)"The lockpick snapped.").method_27692(class_124.field_1061), true);
            return;
        }
        if (!GotLockpicking.hasLockpick(player)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You need a lockpick to finish opening this lock.").method_27692(class_124.field_1061), true);
            return;
        }
        if (!GotLockpicking.canStillUseTarget(player, world, chestPos)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You are too far from the lock.").method_27692(class_124.field_1061), true);
            return;
        }
        GotLockpicking.unlockTarget(player, world, chestPos);
        GotLockpicking.openLockpickableTarget(player, world, chestPos);
    }

    private static void tickSessions(MinecraftServer server) {
        Iterator<Map.Entry<UUID, PendingLockpickSession>> sessionIterator = PENDING_SESSIONS.entrySet().iterator();
        while (sessionIterator.hasNext()) {
            class_1937 class_19372;
            Map.Entry<UUID, PendingLockpickSession> entry = sessionIterator.next();
            class_3222 player = server.method_3760().method_14602(entry.getKey());
            if (player == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
                sessionIterator.remove();
                PENDING_WITNESSES.remove(entry.getKey());
                continue;
            }
            class_3218 world = (class_3218)class_19372;
            if (world.method_8510() - entry.getValue().startedAtTick() <= 900L) continue;
            sessionIterator.remove();
            PENDING_WITNESSES.remove(entry.getKey());
        }
        GotLockpicking.tickWitnesses(server);
        GotLockpicking.tickCaughtDialogues(server);
        GotLockpicking.tickReports(server);
    }

    private static void scheduleWitnessIfSeen(class_3222 player, class_3218 world, class_2338 pos) {
        PENDING_WITNESSES.remove(player.method_5667());
        WitnessContext context = GotLockpicking.findWitnessContext(player, world, pos);
        if (context == null) {
            return;
        }
        PENDING_WITNESSES.put(player.method_5667(), new PendingWitness(context.owner().method_5667(), context.castleId(), pos.method_10062(), world.method_8510()));
    }

    private static WitnessContext findWitnessContext(class_3222 player, class_3218 world, class_2338 pos) {
        CastleInstance castle = CastleWorldState.get(world).findClaimAt(pos);
        if (castle == null) {
            return null;
        }
        AbstractKingdomNpcEntity guard = GotLockpicking.findGuardWitness(world, castle, player, pos);
        if (guard != null) {
            return new WitnessContext(guard, castle.getId());
        }
        CityStructureClaim structureClaim = GotLockpicking.findCityStructureClaim(castle, pos);
        if (structureClaim == null || structureClaim.ownerProfileId().isBlank()) {
            return null;
        }
        AbstractKingdomNpcEntity owner = GotLockpicking.findStructureOwner(world, castle, structureClaim.ownerProfileId(), pos);
        if (owner == null || !GotLockpicking.canWitnessLockpicking(owner, player)) {
            return null;
        }
        return new WitnessContext(owner, castle.getId());
    }

    private static AbstractKingdomNpcEntity findGuardWitness(class_3218 world, CastleInstance castle, class_3222 player, class_2338 pos) {
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)pos), (double)48.0, (double)16.0, (double)48.0);
        AbstractKingdomNpcEntity best = null;
        double bestDistance = Double.MAX_VALUE;
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()) && npc.getNpcRole().isGuardDuty())) {
            double distance;
            if (!GotLockpicking.canWitnessLockpicking(npc2, player) || !((distance = npc2.method_5858((class_1297)player)) < bestDistance)) continue;
            best = npc2;
            bestDistance = distance;
        }
        return best;
    }

    private static CityStructureClaim findCityStructureClaim(CastleInstance castle, class_2338 pos) {
        for (CityStructureClaim claim : castle.getCityStructureClaims()) {
            if (!GotLockpicking.contains(claim, pos)) continue;
            return claim;
        }
        return null;
    }

    private static boolean contains(CityStructureClaim claim, class_2338 pos) {
        return pos.method_10263() >= claim.footprint().method_35415() && pos.method_10263() <= claim.footprint().method_35418() && pos.method_10264() >= claim.footprint().method_35416() && pos.method_10264() <= claim.footprint().method_35419() && pos.method_10260() >= claim.footprint().method_35417() && pos.method_10260() <= claim.footprint().method_35420();
    }

    private static AbstractKingdomNpcEntity findStructureOwner(class_3218 world, CastleInstance castle, String ownerProfileId, class_2338 pos) {
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)pos), (double)64.0, (double)48.0, (double)64.0);
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()) && ownerProfileId.equals(npc.getCastleNpcId()))) {
            KingdomNpcRole role = npc2.getNpcRole();
            if (role.isGuardDuty() || role == KingdomNpcRole.LORD) continue;
            return npc2;
        }
        return null;
    }

    private static boolean canWitnessLockpicking(AbstractKingdomNpcEntity owner, class_3222 player) {
        if (owner.method_5858((class_1297)player) > 576.0 || !owner.method_6057((class_1297)player)) {
            return false;
        }
        class_243 toPlayer = player.method_33571().method_1020(owner.method_33571());
        if (toPlayer.method_1027() < 1.0E-4) {
            return true;
        }
        double dot = owner.method_5828(1.0f).method_1029().method_1026(toPlayer.method_1029());
        return dot >= 0.35;
    }

    private static void tickWitnesses(MinecraftServer server) {
        Iterator<Map.Entry<UUID, PendingWitness>> iterator = PENDING_WITNESSES.entrySet().iterator();
        while (iterator.hasNext()) {
            AbstractKingdomNpcEntity owner;
            class_1937 class_19372;
            Map.Entry<UUID, PendingWitness> entry = iterator.next();
            class_3222 player = server.method_3760().method_14602(entry.getKey());
            PendingLockpickSession session = PENDING_SESSIONS.get(entry.getKey());
            if (player == null || session == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
                iterator.remove();
                continue;
            }
            class_3218 world = (class_3218)class_19372;
            PendingWitness witness = entry.getValue();
            if (world.method_8510() - witness.startedAtTick() > 900L || !session.chestPos().equals((Object)witness.chestPos())) {
                iterator.remove();
                continue;
            }
            class_1297 entity = world.method_14190(witness.ownerUuid());
            if (!(entity instanceof AbstractKingdomNpcEntity && (owner = (AbstractKingdomNpcEntity)entity).method_5805() && owner.got$getCastle().equals(witness.castleId()))) {
                iterator.remove();
                continue;
            }
            if (owner.method_5858((class_1297)player) <= 36.0) {
                PENDING_SESSIONS.remove(entry.getKey());
                iterator.remove();
                CastleInstance castle = CastleWorldState.get(world).get(witness.castleId());
                if (castle != null) {
                    GotPlayerKingdomActions.recordLockpickingCaught(player, castle.getOwnerKingdomId());
                }
                if (!owner.forceBeginDialogue(player)) continue;
                PENDING_DIALOGUES.put(entry.getKey(), new PendingCaughtDialogue(owner.method_5667(), witness.castleId(), world.method_8510()));
                ServerPlayNetworking.send((class_3222)player, (class_8710)new OpenLockpickingCaughtPayload(owner.method_5628()));
                continue;
            }
            owner.method_5942().method_6335((class_1297)player, 1.08);
            owner.method_5988().method_35111((class_1297)player);
        }
    }

    private static void tickCaughtDialogues(MinecraftServer server) {
        Iterator<Map.Entry<UUID, PendingCaughtDialogue>> iterator = PENDING_DIALOGUES.entrySet().iterator();
        while (iterator.hasNext()) {
            class_1937 class_19372;
            Map.Entry<UUID, PendingCaughtDialogue> entry = iterator.next();
            class_3222 player = server.method_3760().method_14602(entry.getKey());
            if (player == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
                iterator.remove();
                continue;
            }
            class_3218 world = (class_3218)class_19372;
            if (world.method_8510() - entry.getValue().startedAtTick() <= 900L) continue;
            GotLockpicking.endCaughtDialogue(world, player, entry.getValue());
            iterator.remove();
        }
    }

    public static void handleExcuse(class_3222 player, int ownerEntityId, String optionId) {
        boolean success;
        ExcuseOption option;
        AbstractKingdomNpcEntity owner;
        class_1937 class_19372;
        PendingCaughtDialogue dialogue = PENDING_DIALOGUES.get(player.method_5667());
        if (dialogue == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        class_1297 entity = world.method_14190(dialogue.ownerUuid());
        if (!(entity instanceof AbstractKingdomNpcEntity) || (owner = (AbstractKingdomNpcEntity)entity).method_5628() != ownerEntityId || !owner.method_5805()) {
            return;
        }
        boolean escaped = "escape".equals(optionId);
        ExcuseOption excuseOption = option = escaped ? null : ExcuseOption.byId(optionId);
        if (!escaped && option == null) {
            return;
        }
        PENDING_DIALOGUES.remove(player.method_5667());
        GotLockpicking.endCaughtDialogue(world, player, dialogue);
        int chancePercent = escaped ? 0 : Math.min(95, 10 + GotLockpicking.skillPoints(player, option.skill()) / 5);
        boolean bl = success = !escaped && world.field_9229.method_43048(100) < chancePercent;
        if (success) {
            SkillExperienceService.addPoints(player, SkillAttributeType.LUCK, 1);
            player.method_7353((class_2561)class_2561.method_43470((String)"They believe you.").method_27692(class_124.field_1060), true);
            return;
        }
        if (owner.getNpcRole().isGuardDuty()) {
            CastleInstance castle = CastleWorldState.get(world).get(dialogue.castleId());
            if (castle != null) {
                GotLockpicking.raiseCastleAlarm(world, castle, player);
            }
            return;
        }
        GotLockpicking.startReportToGuard(world, player, owner, dialogue.castleId());
        player.method_7353((class_2561)class_2561.method_43470((String)"They run to find a guard.").method_27692(class_124.field_1061), true);
    }

    public static boolean isLockpickingReportLocked(class_3222 player, class_1297 entity) {
        if (player == null || entity == null) {
            return false;
        }
        for (ActiveReport report : ACTIVE_REPORTS.values()) {
            if (!report.playerUuid().equals(player.method_5667()) || !report.ownerUuid().equals(entity.method_5667())) continue;
            return true;
        }
        return false;
    }

    private static void endCaughtDialogue(class_3218 world, class_3222 player, PendingCaughtDialogue dialogue) {
        class_1297 entity = world.method_14190(dialogue.ownerUuid());
        if (entity instanceof AbstractKingdomNpcEntity) {
            AbstractKingdomNpcEntity owner = (AbstractKingdomNpcEntity)entity;
            owner.endDialogue(player.method_5667());
        }
    }

    private static int skillPoints(class_3222 player, SkillAttributeType type) {
        return ((PlayerSkillHolder)player).got$getSkillProfile().get(type).getPoints();
    }

    private static void startReportToGuard(class_3218 world, class_3222 player, AbstractKingdomNpcEntity owner, String castleId) {
        CastleInstance castle = CastleWorldState.get(world).get(castleId);
        if (castle == null) {
            return;
        }
        AbstractKingdomNpcEntity guard = GotLockpicking.findNearestGuard(world, castle, owner.method_19538(), 64.0);
        UUID guardUuid = guard == null ? null : guard.method_5667();
        ACTIVE_REPORTS.put(owner.method_5667(), new ActiveReport(player.method_5667(), owner.method_5667(), guardUuid, castleId, world.method_8510(), 0L));
        if (guard != null) {
            owner.method_5942().method_6335((class_1297)guard, 1.15);
        } else {
            class_2338 bannerPos = castle.getBannerPos();
            owner.method_5942().method_6337((double)bannerPos.method_10263() + 0.5, (double)bannerPos.method_10264(), (double)bannerPos.method_10260() + 0.5, 1.08);
        }
    }

    private static void tickReports(MinecraftServer server) {
        Iterator<Map.Entry<UUID, ActiveReport>> iterator = ACTIVE_REPORTS.entrySet().iterator();
        while (iterator.hasNext()) {
            AbstractKingdomNpcEntity owner;
            class_1937 class_19372;
            Map.Entry<UUID, ActiveReport> entry = iterator.next();
            ActiveReport report = entry.getValue();
            class_3222 player = server.method_3760().method_14602(report.playerUuid());
            if (player == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
                iterator.remove();
                continue;
            }
            class_3218 world = (class_3218)class_19372;
            CastleInstance castle = CastleWorldState.get(world).get(report.castleId());
            class_1297 ownerEntity = world.method_14190(report.ownerUuid());
            if (castle == null || !(ownerEntity instanceof AbstractKingdomNpcEntity) || !(owner = (AbstractKingdomNpcEntity)ownerEntity).method_5805()) {
                iterator.remove();
                continue;
            }
            long now = world.method_8510();
            if (now - report.startedAtTick() > 2400L) {
                iterator.remove();
                continue;
            }
            AbstractKingdomNpcEntity guard = GotLockpicking.resolveGuard(world, report.guardUuid(), castle);
            if (guard == null && now - report.lastGuardScanTick() >= 20L) {
                guard = GotLockpicking.findNearestGuard(world, castle, owner.method_19538(), 64.0);
                report = report.withGuard(guard == null ? null : guard.method_5667(), now);
                entry.setValue(report);
            }
            if (guard != null) {
                if (owner.method_5858((class_1297)guard) <= 36.0) {
                    GotLockpicking.raiseCastleAlarm(world, castle, player);
                    iterator.remove();
                    continue;
                }
                owner.method_5942().method_6335((class_1297)guard, 1.15);
                continue;
            }
            class_2338 bannerPos = castle.getBannerPos();
            owner.method_5942().method_6337((double)bannerPos.method_10263() + 0.5, (double)bannerPos.method_10264(), (double)bannerPos.method_10260() + 0.5, 1.08);
        }
    }

    private static AbstractKingdomNpcEntity resolveGuard(class_3218 world, UUID guardUuid, CastleInstance castle) {
        AbstractKingdomNpcEntity guard;
        if (guardUuid == null) {
            return null;
        }
        class_1297 entity = world.method_14190(guardUuid);
        if (!(entity instanceof AbstractKingdomNpcEntity && (guard = (AbstractKingdomNpcEntity)entity).method_5805() && castle.getId().equals(guard.got$getCastle()))) {
            return null;
        }
        return guard.getNpcRole().isGuardDuty() ? guard : null;
    }

    private static AbstractKingdomNpcEntity findNearestGuard(class_3218 world, CastleInstance castle, class_243 origin, double range) {
        class_238 searchBox = class_238.method_30048((class_243)origin, (double)(range * 2.0), (double)48.0, (double)(range * 2.0));
        AbstractKingdomNpcEntity best = null;
        double bestDistance = Double.MAX_VALUE;
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()) && npc.getNpcRole().isGuardDuty())) {
            double distance = npc2.method_5707(origin);
            if (!(distance < bestDistance) || !(distance <= range * range)) continue;
            best = npc2;
            bestDistance = distance;
        }
        return best;
    }

    private static void raiseCastleAlarm(class_3218 world, CastleInstance castle, class_3222 player) {
        long expiresAtTick = world.method_8510() + 2400L;
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()))) {
            if (!npc2.getNpcRole().isGuardDuty() && npc2.getNpcRole() != KingdomNpcRole.LORD) continue;
            npc2.alertAgainst(player, expiresAtTick);
        }
        String kingdomId = GotCastles.canonicalKingdomId(castle.getOwnerKingdomId());
        if (!kingdomId.isBlank()) {
            int updatedRelation = ((PlayerKingdomRelationsHolder)player).got$getKingdomRelationsProfile().addRelation(kingdomId, -10);
            GotPlayerKingdomActions.recordRelation(player, kingdomId, updatedRelation);
            GotRelations.syncToPlayer(player);
        }
        player.method_7353((class_2561)class_2561.method_43470((String)"The castle alarm has been raised.").method_27692(class_124.field_1061), true);
    }

    private static boolean canStillUseTarget(class_3222 player, class_3218 world, class_2338 pos) {
        if (!GotLockpicking.isLockpickableTarget(world.method_8320(pos)) || !GotLockpicking.isInCastleOrVillageClaim(world, pos)) {
            return false;
        }
        return player.method_5707(class_243.method_24953((class_2382)pos)) <= 64.0;
    }

    private static void openLockpickableTarget(class_3222 player, class_3218 world, class_2338 pos) {
        class_2680 state = world.method_8320(pos);
        class_2248 class_22482 = state.method_26204();
        if (class_22482 instanceof class_2323) {
            class_2323 door = (class_2323)class_22482;
            door.method_10033((class_1297)player, (class_1937)world, state, pos, (Boolean)state.method_11654((class_2769)class_2323.field_10945) == false);
            return;
        }
        class_3908 factory = state.method_26196((class_1937)world, pos);
        if (factory == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This lock cannot be opened.").method_27692(class_124.field_1061), true);
            return;
        }
        player.method_17355(factory);
    }

    private static boolean isLockpickableTarget(class_2680 state) {
        return state.method_26204() instanceof class_2281 || state.method_26204() instanceof class_2323;
    }

    public static void trackPlacedLockpickableOwner(class_1937 world, class_2338 pos, class_1309 placer) {
        class_3218 serverWorld;
        block5: {
            block4: {
                if (!(world instanceof class_3218)) break block4;
                serverWorld = (class_3218)world;
                if (placer instanceof class_1657) break block5;
            }
            return;
        }
        class_1657 player = (class_1657)placer;
        class_2338 targetPos = GotLockpicking.normalizeTargetPos(world, pos);
        class_2680 targetState = world.method_8320(targetPos);
        if (!GotLockpicking.isLockpickableTarget(targetState) || !GotLockpicking.isInCastleOrVillageClaim(serverWorld, targetPos)) {
            return;
        }
        LockpickingWorldState lockpickingState = LockpickingWorldState.get(serverWorld);
        lockpickingState.setOwnerUuid(targetPos, player.method_5667());
    }

    public static void clearPlacedLockpickableOwner(class_1937 world, class_2338 pos, class_2680 oldState, class_2680 newState) {
        class_3218 serverWorld;
        block5: {
            block4: {
                if (!(world instanceof class_3218)) break block4;
                serverWorld = (class_3218)world;
                if (GotLockpicking.isLockpickableTarget(oldState) && !oldState.method_27852(newState.method_26204())) break block5;
            }
            return;
        }
        LockpickingWorldState lockpickingState = LockpickingWorldState.get(serverWorld);
        class_2338 targetPos = oldState.method_26204() instanceof class_2323 && oldState.method_28498((class_2769)class_2323.field_10946) && oldState.method_11654((class_2769)class_2323.field_10946) == class_2756.field_12609 ? pos.method_10074() : pos;
        lockpickingState.clearOwnerUuid(targetPos);
        if (oldState.method_26204() instanceof class_2323) {
            lockpickingState.clearOwnerUuid(targetPos.method_10084());
        }
    }

    private static class_2338 normalizeTargetPos(class_1937 world, class_2338 pos) {
        class_2338 lowerPos;
        class_2680 lowerState;
        class_2680 state = world.method_8320(pos);
        if (state.method_26204() instanceof class_2323 && state.method_28498((class_2769)class_2323.field_10946) && state.method_11654((class_2769)class_2323.field_10946) == class_2756.field_12609 && (lowerState = world.method_8320(lowerPos = pos.method_10074())).method_26204() instanceof class_2323) {
            return lowerPos;
        }
        return pos;
    }

    private static boolean isInCastleOrVillageClaim(class_3218 world, class_2338 pos) {
        return CastleWorldState.get(world).findClaimAt(pos) != null || VillageWorldState.get(world).findClaimAt(pos) != null;
    }

    private static String targetKey(class_3218 world, class_2338 pos) {
        return String.valueOf(world.method_27983().method_29177()) + ":" + pos.method_10063();
    }

    private static boolean hasUnlockedTarget(class_3222 player, class_3218 world, class_2338 pos) {
        PlayerLockpickingHolder holder = (PlayerLockpickingHolder)player;
        if (holder.got$hasUnlockedLockpickTarget(GotLockpicking.targetKey(world, pos))) {
            return true;
        }
        class_2338 pairedChestPos = GotLockpicking.findPairedChestPos(world, pos);
        return pairedChestPos != null && holder.got$hasUnlockedLockpickTarget(GotLockpicking.targetKey(world, pairedChestPos));
    }

    private static boolean isPlacedTargetOwner(class_3222 player, class_3218 world, class_2338 pos) {
        LockpickingWorldState state = LockpickingWorldState.get(world);
        if (player.method_5667().equals(state.getOwnerUuid(pos))) {
            return true;
        }
        class_2338 pairedChestPos = GotLockpicking.findPairedChestPos(world, pos);
        return pairedChestPos != null && player.method_5667().equals(state.getOwnerUuid(pairedChestPos));
    }

    private static void unlockTarget(class_3222 player, class_3218 world, class_2338 pos) {
        PlayerLockpickingHolder holder = (PlayerLockpickingHolder)player;
        holder.got$unlockLockpickTarget(GotLockpicking.targetKey(world, pos));
        class_2338 pairedChestPos = GotLockpicking.findPairedChestPos(world, pos);
        if (pairedChestPos != null) {
            holder.got$unlockLockpickTarget(GotLockpicking.targetKey(world, pairedChestPos));
        }
    }

    private static LockpickingWorldState.LockpickSetup getOrCreateSetup(class_3218 world, class_2338 pos) {
        LockpickingWorldState state = LockpickingWorldState.get(world);
        LockpickingWorldState.LockpickSetup setup = state.get(pos);
        if (setup != null) {
            return setup;
        }
        class_2338 pairedChestPos = GotLockpicking.findPairedChestPos(world, pos);
        if (pairedChestPos != null && (setup = state.get(pairedChestPos)) != null) {
            state.put(pos, setup);
            return setup;
        }
        int lockCount = 3 + world.field_9229.method_43048(4);
        String difficultyId = GotLockpicking.difficultyForLockCount(lockCount);
        float speedMultiplier = 1.0f + world.field_9229.method_43057() * 0.25f;
        setup = new LockpickingWorldState.LockpickSetup(lockCount, speedMultiplier, difficultyId, world.field_9229.method_43055());
        state.put(pos, setup);
        if (pairedChestPos != null) {
            state.put(pairedChestPos, setup);
        }
        return setup;
    }

    private static String difficultyForLockCount(int lockCount) {
        if (lockCount <= 3) {
            return "easy";
        }
        return lockCount >= 6 ? "hard" : "medium";
    }

    private static class_2338 findPairedChestPos(class_3218 world, class_2338 pos) {
        class_2680 state = world.method_8320(pos);
        if (!(state.method_26204() instanceof class_2281) || !state.method_28498((class_2769)class_2281.field_10770)) {
            return null;
        }
        class_2745 type = (class_2745)state.method_11654((class_2769)class_2281.field_10770);
        if (type == class_2745.field_12569) {
            return null;
        }
        for (class_2350 direction : class_2350.class_2353.field_11062) {
            class_2338 adjacentPos = pos.method_10093(direction);
            class_2680 adjacentState = world.method_8320(adjacentPos);
            if (!adjacentState.method_27852(state.method_26204()) || !adjacentState.method_28498((class_2769)class_2281.field_10770) || adjacentState.method_11654((class_2769)class_2281.field_10770) != type.method_11824()) continue;
            return adjacentPos;
        }
        return null;
    }

    private static class_1268 findHeldLockpickHand(class_3222 player, class_1268 preferredHand) {
        if (player.method_5998(preferredHand).method_31574(GotItems.LOCKPICK)) {
            return preferredHand;
        }
        class_1268 otherHand = preferredHand == class_1268.field_5808 ? class_1268.field_5810 : class_1268.field_5808;
        return player.method_5998(otherHand).method_31574(GotItems.LOCKPICK) ? otherHand : null;
    }

    private static boolean hasLockpick(class_3222 player) {
        return player.method_7337() || player.method_6047().method_31574(GotItems.LOCKPICK) || player.method_6079().method_31574(GotItems.LOCKPICK) || GotLockpicking.findLockpickSlot(player) >= 0;
    }

    private static void consumeLockpick(class_3222 player, class_1268 preferredHand) {
        if (player.method_7337()) {
            return;
        }
        class_1799 preferredStack = player.method_5998(preferredHand);
        if (preferredStack.method_31574(GotItems.LOCKPICK)) {
            preferredStack.method_7934(1);
            return;
        }
        class_1268 fallbackHand = preferredHand == class_1268.field_5808 ? class_1268.field_5810 : class_1268.field_5808;
        class_1799 fallbackStack = player.method_5998(fallbackHand);
        if (fallbackStack.method_31574(GotItems.LOCKPICK)) {
            fallbackStack.method_7934(1);
            return;
        }
        int slot = GotLockpicking.findLockpickSlot(player);
        if (slot >= 0) {
            player.method_31548().method_5438(slot).method_7934(1);
        }
    }

    private static int findLockpickSlot(class_3222 player) {
        for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
            if (!player.method_31548().method_5438(slot).method_31574(GotItems.LOCKPICK)) continue;
            return slot;
        }
        return -1;
    }

    private record PendingLockpickSession(class_2338 chestPos, class_1268 hand, long startedAtTick) {
    }

    private record WitnessContext(AbstractKingdomNpcEntity owner, String castleId) {
    }

    private record PendingWitness(UUID ownerUuid, String castleId, class_2338 chestPos, long startedAtTick) {
    }

    private record PendingCaughtDialogue(UUID ownerUuid, String castleId, long startedAtTick) {
    }

    private static enum ExcuseOption {
        LOST("lost", SkillAttributeType.CHARISMA),
        INTIMIDATE("intimidate", SkillAttributeType.INTELLIGENCE),
        LIE("lie", SkillAttributeType.LUCK);

        private final String id;
        private final SkillAttributeType skill;

        private ExcuseOption(String id, SkillAttributeType skill) {
            this.id = id;
            this.skill = skill;
        }

        private SkillAttributeType skill() {
            return this.skill;
        }

        private static ExcuseOption byId(String id) {
            for (ExcuseOption option : ExcuseOption.values()) {
                if (!option.id.equals(id)) continue;
                return option;
            }
            return null;
        }
    }

    private record ActiveReport(UUID playerUuid, UUID ownerUuid, UUID guardUuid, String castleId, long startedAtTick, long lastGuardScanTick) {
        private ActiveReport withGuard(UUID guardUuid, long lastGuardScanTick) {
            return new ActiveReport(this.playerUuid, this.ownerUuid, guardUuid, this.castleId, this.startedAtTick, lastGuardScanTick);
        }
    }
}

