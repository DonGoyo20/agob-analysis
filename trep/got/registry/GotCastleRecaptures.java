/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1259$class_1260
 *  net.minecraft.class_1259$class_1261
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_2960
 *  net.minecraft.class_3213
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3532
 *  net.minecraft.class_3730
 *  net.minecraft.class_5425
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_124;
import net.minecraft.class_1259;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2902;
import net.minecraft.class_2960;
import net.minecraft.class_3213;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3532;
import net.minecraft.class_3730;
import net.minecraft.class_5425;
import org.jetbrains.annotations.Nullable;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleRaidState;
import trep.got.castle.CastleRecaptureStage;
import trep.got.castle.CastleRecaptureState;
import trep.got.castle.CastleTier;
import trep.got.castle.CastleWorldState;
import trep.got.castle.KingdomAiInvasionWorldState;
import trep.got.entity.AbstractKingdomGuardNpcEntity;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.kingdom.Kingdom;
import trep.got.registry.AgobCastleRecaptureConfig;
import trep.got.registry.GotCastleOwnerNames;
import trep.got.registry.GotCastles;
import trep.got.registry.GotEntities;
import trep.got.registry.GotKingdomEventLogs;
import trep.got.registry.GotKingdoms;
import trep.got.registry.castle.overlay.CastlePresetMetadata;

final class GotCastleRecaptures {
    private static final Map<String, ActiveCastleRecapture> ACTIVE_CASTLE_RECAPTURES = new LinkedHashMap<String, ActiveCastleRecapture>();

    private GotCastleRecaptures() {
    }

    static void tick(class_3218 world) {
        CastleWorldState state = GotCastles.getWorldState(world);
        GotCastleRecaptures.syncRecaptureStates(world, state);
        GotCastleRecaptures.syncActiveRecaptureBossBars(state);
        for (CastleRecaptureState recapture : List.copyOf(state.getAllRecaptures())) {
            CastleInstance castle = state.get(recapture.getCastleId());
            if (castle == null || castle.getOwnerPlayerUuid() == null || "player_claim".equals(castle.getDefinitionId())) {
                GotCastleRecaptures.end(world, recapture.getCastleId(), false, false);
                continue;
            }
            switch (recapture.getStage()) {
                case DORMANT: {
                    GotCastleRecaptures.tickDormant(world, state, castle, recapture);
                    break;
                }
                case INCOMING: {
                    GotCastleRecaptures.tickIncoming(world, state, castle, recapture);
                    break;
                }
                case INVASION: {
                    GotCastleRecaptures.tickInvading(world, state, castle, recapture);
                }
            }
        }
    }

    static boolean startIncoming(class_3218 world, CastleInstance castle, boolean manualTrigger, @Nullable Long incomingDurationTicksOverride) {
        if (world == null || castle == null || castle.getOwnerPlayerUuid() == null || "player_claim".equals(castle.getDefinitionId())) {
            return false;
        }
        if (GotCastles.findActiveInvasionForCastle(castle.getId()) != null) {
            return false;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        CastleRecaptureState recapture = state.getOrCreateRecapture(castle.getId());
        if (recapture.getStage() != CastleRecaptureStage.DORMANT) {
            return false;
        }
        AgobCastleRecaptureConfig.Settings settings = AgobCastleRecaptureConfig.load();
        Kingdom kingdom = GotCastleRecaptures.resolveOriginalKingdom(castle);
        recapture.setPlayerUuid(castle.getOwnerPlayerUuid());
        recapture.setStage(CastleRecaptureStage.INCOMING);
        long incomingDurationTicks = incomingDurationTicksOverride != null ? Math.max(20L, incomingDurationTicksOverride) : settings.incomingDurationTicks();
        recapture.setIncomingEndsAtTick(world.method_8510() + incomingDurationTicks);
        recapture.setPlayerEnteredZone(false);
        recapture.clearAttackers();
        recapture.setAttackerCount(0);
        recapture.setAttackingKingdomId(kingdom != null ? kingdom.getId().toString() : GotCastleRecaptures.originalKingdomIdFor(castle));
        recapture.setAttackingLordName(GotCastleRecaptures.resolveRecaptureLordName(world, castle, recapture, kingdom));
        recapture.setNextCheckTick(world.method_8510() + settings.intervalTicks());
        state.method_80();
        GotCastleRecaptures.appendAttemptLog(world, recapture, castle);
        ACTIVE_CASTLE_RECAPTURES.computeIfAbsent(castle.getId(), ActiveCastleRecapture::new);
        class_3222 player = world.method_8503().method_3760().method_14602(castle.getOwnerPlayerUuid());
        if (player != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)((manualTrigger ? "Admin triggered" : "Recapture incoming") + ": defend " + GotCastles.displayNameForCastle(castle) + " before the countdown ends.")).method_27692(class_124.field_1054), false);
        }
        return true;
    }

    static void handleDisconnect(class_3218 world, UUID playerUuid) {
        GotCastleRecaptures.handleDisconnect(world, playerUuid, true);
    }

    static void handleDisconnect(class_3218 world, UUID playerUuid, boolean notifyPlayer) {
        if (world == null || playerUuid == null) {
            return;
        }
        for (CastleRecaptureState recapture : List.copyOf(GotCastles.getWorldState(world).getAllRecaptures())) {
            CastleInstance castle;
            if (recapture.getStage() != CastleRecaptureStage.INVASION || !playerUuid.equals(recapture.getPlayerUuid()) || (castle = GotCastles.getWorldState(world).get(recapture.getCastleId())) == null) continue;
            GotCastleRecaptures.resolveSuccessfulRecapture(world, castle, recapture, notifyPlayer ? class_2561.method_43470((String)"Recapture failed. You left the battle.").method_27692(class_124.field_1061) : null);
        }
    }

    static void winDefense(class_3218 world, CastleInstance castle, CastleRecaptureState recapture) {
        GotCastleRecaptures.winDefenseInternal(world, castle, recapture);
    }

    static void resolveFailure(class_3218 world, CastleInstance castle, CastleRecaptureState recapture, class_2561 failureMessage) {
        GotCastleRecaptures.resolveSuccessfulRecapture(world, castle, recapture, failureMessage);
    }

    static void resetState(class_3218 world, CastleInstance castle, @Nullable UUID playerUuid) {
        CastleWorldState state = GotCastles.getWorldState(world);
        if (castle == null || playerUuid == null || "player_claim".equals(castle.getDefinitionId())) {
            GotCastleRecaptures.end(world, castle == null ? "" : castle.getId(), false, false);
            return;
        }
        CastleRecaptureState recapture = state.getOrCreateRecapture(castle.getId());
        recapture.setPlayerUuid(playerUuid);
        recapture.setStage(CastleRecaptureStage.DORMANT);
        recapture.setNextCheckTick(world.method_8510() + AgobCastleRecaptureConfig.load().intervalTicks());
        recapture.setIncomingEndsAtTick(0L);
        recapture.setPlayerEnteredZone(false);
        recapture.clearAttackers();
        recapture.setAttackingKingdomId("");
        recapture.setAttackingLordName("");
        state.method_80();
        ActiveCastleRecapture active = ACTIVE_CASTLE_RECAPTURES.remove(castle.getId());
        if (active != null) {
            active.bossBar().method_14094();
        }
        GotCastles.syncStateToPlayers(world);
    }

    static void end(class_3218 world, String castleId, boolean keepDormantState, boolean syncPlayers) {
        ActiveCastleRecapture active;
        if (castleId == null || castleId.isBlank()) {
            return;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        CastleRecaptureState recapture = state.getRecapture(castleId);
        if (recapture != null) {
            GotCastleRecaptures.clearAttackers(world, recapture);
            if (keepDormantState) {
                recapture.setStage(CastleRecaptureStage.DORMANT);
                recapture.setIncomingEndsAtTick(0L);
                recapture.setPlayerEnteredZone(false);
                recapture.setNextCheckTick(world.method_8510() + AgobCastleRecaptureConfig.load().intervalTicks());
                recapture.setAttackingKingdomId("");
                recapture.setAttackingLordName("");
                state.method_80();
            } else {
                state.removeRecapture(castleId);
            }
        }
        if ((active = ACTIVE_CASTLE_RECAPTURES.remove(castleId)) != null) {
            active.bossBar().method_14094();
        }
        GotCastles.setCastleGatesInvasionMode(world, castleId, false);
        if (syncPlayers) {
            GotCastles.syncStateToPlayers(world);
        }
    }

    static <T extends AbstractKingdomNpcEntity> T spawnRecaptureNpc(class_3218 world, class_1299<T> entityType, CastleInstance castle, int seedOffset) {
        AbstractKingdomNpcEntity entity = (AbstractKingdomNpcEntity)entityType.method_5883((class_1937)world);
        if (entity == null) {
            return null;
        }
        class_2338 spawnPos = GotCastleRecaptures.findRecaptureSpawnPos(world, castle.getBannerPos(), seedOffset);
        entity.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
        entity.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16467, null);
        entity.got$setCastle(castle.getId());
        entity.setPointOfInterest(castle.getBannerPos());
        entity.method_5834(true);
        world.method_8649((class_1297)entity);
        return (T)entity;
    }

    static boolean hasLivingCastleDefenders(class_3218 world, String castleId) {
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            return false;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        return !world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castleId.equals(npc.got$getCastle()) && (npc instanceof AbstractKingdomGuardNpcEntity || npc instanceof AbstractKingdomLordNpcEntity) && !GotCastleRecaptures.isRecaptureAttacker(world, castleId, npc.method_5667()) && !GotCastleRecaptures.isKingdomAiAttacker(world, castleId, npc.method_5667())).isEmpty();
    }

    private static void syncRecaptureStates(class_3218 world, CastleWorldState state) {
        AgobCastleRecaptureConfig.Settings settings = AgobCastleRecaptureConfig.load();
        long nextCheckAt = world.method_8510() + settings.intervalTicks();
        LinkedHashSet<String> ownedCastles = new LinkedHashSet<String>();
        for (CastleInstance castle : state.getAll()) {
            if (castle.getOwnerPlayerUuid() == null || "player_claim".equals(castle.getDefinitionId())) continue;
            ownedCastles.add(castle.getId());
            CastleRecaptureState recapture = state.getRecapture(castle.getId());
            if (recapture == null) {
                recapture = new CastleRecaptureState(castle.getId());
                recapture.setPlayerUuid(castle.getOwnerPlayerUuid());
                recapture.setNextCheckTick(nextCheckAt);
                state.putRecapture(recapture);
                continue;
            }
            if (castle.getOwnerPlayerUuid().equals(recapture.getPlayerUuid())) continue;
            recapture.setPlayerUuid(castle.getOwnerPlayerUuid());
            recapture.setStage(CastleRecaptureStage.DORMANT);
            recapture.setPlayerEnteredZone(false);
            recapture.setIncomingEndsAtTick(0L);
            recapture.clearAttackers();
            recapture.setNextCheckTick(nextCheckAt);
            state.method_80();
        }
        for (CastleRecaptureState recapture : List.copyOf(state.getAllRecaptures())) {
            if (ownedCastles.contains(recapture.getCastleId())) continue;
            GotCastleRecaptures.end(world, recapture.getCastleId(), false, false);
        }
    }

    private static void syncActiveRecaptureBossBars(CastleWorldState state) {
        LinkedHashSet<String> activeIds = new LinkedHashSet<String>();
        for (CastleRecaptureState recapture : state.getAllRecaptures()) {
            if (recapture.getStage() == CastleRecaptureStage.DORMANT) continue;
            activeIds.add(recapture.getCastleId());
            ACTIVE_CASTLE_RECAPTURES.computeIfAbsent(recapture.getCastleId(), ActiveCastleRecapture::new);
        }
        ACTIVE_CASTLE_RECAPTURES.entrySet().removeIf(entry -> {
            if (!activeIds.contains(entry.getKey())) {
                ((ActiveCastleRecapture)entry.getValue()).bossBar().method_14094();
                return true;
            }
            return false;
        });
    }

    private static void tickDormant(class_3218 world, CastleWorldState state, CastleInstance castle, CastleRecaptureState recapture) {
        if (GotCastles.findActiveInvasionForCastle(castle.getId()) != null || castle.getRaidState() == CastleRaidState.CONTESTED) {
            return;
        }
        if (world.method_8510() < recapture.getNextCheckTick()) {
            return;
        }
        AgobCastleRecaptureConfig.Settings settings = AgobCastleRecaptureConfig.load();
        recapture.setNextCheckTick(world.method_8510() + settings.intervalTicks());
        state.method_80();
        if (world.field_9229.method_43048(100) >= settings.triggerChancePercent()) {
            return;
        }
        GotCastleRecaptures.startIncoming(world, castle, false, null);
    }

    private static void tickIncoming(class_3218 world, CastleWorldState state, CastleInstance castle, CastleRecaptureState recapture) {
        ActiveCastleRecapture active = ACTIVE_CASTLE_RECAPTURES.computeIfAbsent(castle.getId(), ActiveCastleRecapture::new);
        class_3222 player = recapture.getPlayerUuid() == null ? null : world.method_8503().method_3760().method_14602(recapture.getPlayerUuid());
        AgobCastleRecaptureConfig.Settings settings = AgobCastleRecaptureConfig.load();
        active.bossBar().method_5416(class_1259.class_1260.field_5782);
        if (player != null) {
            active.bossBar().method_14088(player);
            if (player.method_37908() == world && GotCastles.isWithinCastleClaim(world, castle.getId(), player.method_24515()) && !recapture.hasPlayerEnteredZone()) {
                recapture.setPlayerEnteredZone(true);
                state.method_80();
            }
        } else {
            active.bossBar().method_14094();
        }
        long remainingTicks = Math.max(0L, recapture.getIncomingEndsAtTick() - world.method_8510());
        active.updateIncomingBossBar(remainingTicks, settings.incomingDurationTicks());
        if (world.method_8510() < recapture.getIncomingEndsAtTick()) {
            return;
        }
        if (!recapture.hasPlayerEnteredZone()) {
            GotCastleRecaptures.resolveSuccessfulRecapture(world, castle, recapture, (class_2561)class_2561.method_43470((String)"Recapture failed. The castle fell before you arrived.").method_27692(class_124.field_1061));
            return;
        }
        GotCastleRecaptures.startInvasion(world, castle, recapture);
    }

    private static void tickInvading(class_3218 world, CastleWorldState state, CastleInstance castle, CastleRecaptureState recapture) {
        ActiveCastleRecapture active = ACTIVE_CASTLE_RECAPTURES.computeIfAbsent(castle.getId(), ActiveCastleRecapture::new);
        class_3222 player = recapture.getPlayerUuid() == null ? null : world.method_8503().method_3760().method_14602(recapture.getPlayerUuid());
        active.bossBar().method_5416(class_1259.class_1260.field_5784);
        if (player == null) {
            GotCastleRecaptures.resolveSuccessfulRecapture(world, castle, recapture, (class_2561)class_2561.method_43470((String)"Recapture failed. You left the battle.").method_27692(class_124.field_1061));
            return;
        }
        active.bossBar().method_14088(player);
        GotCastleRecaptures.pruneAttackers(world, state, recapture);
        if (recapture.getAttackerUuids().isEmpty()) {
            GotCastleRecaptures.winDefenseInternal(world, castle, recapture);
            return;
        }
        if (!GotCastleRecaptures.hasLivingCastleDefenders(world, castle.getId())) {
            GotCastleRecaptures.resolveSuccessfulRecapture(world, castle, recapture, (class_2561)class_2561.method_43470((String)"Recapture failed. Your guards were wiped out.").method_27692(class_124.field_1061));
            return;
        }
        GotCastles.directRecaptureCombat(world, castle, recapture, player);
        active.updateInvasionBossBar(recapture.getAttackerUuids().size(), Math.max(1, recapture.getAttackerCount()));
    }

    private static void startInvasion(class_3218 world, CastleInstance castle, CastleRecaptureState recapture) {
        class_3222 player;
        CastleWorldState state = GotCastles.getWorldState(world);
        GotCastleRecaptures.ensureRecaptureDefendersPresent(world, castle);
        recapture.setStage(CastleRecaptureStage.INVASION);
        recapture.clearAttackers();
        GotCastleRecaptures.spawnAttackers(world, castle, recapture);
        recapture.setAttackerCount(recapture.getAttackerUuids().size());
        GotCastles.setCastleGatesInvasionMode(world, castle.getId(), true);
        state.method_80();
        class_3222 class_32222 = player = recapture.getPlayerUuid() == null ? null : world.method_8503().method_3760().method_14602(recapture.getPlayerUuid());
        if (player != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Recapture assault started. Kill every attacker to keep the castle.").method_27692(class_124.field_1061), false);
        }
    }

    private static void ensureRecaptureDefendersPresent(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null || GotCastleRecaptures.hasLivingCastleDefenders(world, castle.getId())) {
            return;
        }
        GotCastles.respawnCastlePopulation(world, castle);
    }

    private static void spawnAttackers(class_3218 world, CastleInstance castle, CastleRecaptureState recapture) {
        AbstractKingdomLordNpcEntity lord;
        Kingdom kingdom = GotCastleRecaptures.resolveOriginalKingdom(castle);
        if (kingdom == null) {
            return;
        }
        class_1299<? extends AbstractKingdomGuardNpcEntity> guardType = GotEntities.guardTypeForKingdom(kingdom);
        class_1299<? extends AbstractKingdomLordNpcEntity> lordType = GotEntities.lordTypeForKingdom(kingdom);
        if (guardType == null) {
            return;
        }
        int guardCount = Math.max(3, GotCastles.guardCountFor(castle.getTier()) * 2);
        if (castle.getTier() == CastleTier.CAPITAL) {
            guardCount += 4;
        }
        for (int index = 0; index < guardCount; ++index) {
            AbstractKingdomGuardNpcEntity guard = GotCastleRecaptures.spawnRecaptureNpc(world, guardType, castle, 4100 + index);
            if (guard == null) continue;
            recapture.addAttackerUuid(guard.method_5667());
        }
        if (lordType != null && (lord = GotCastleRecaptures.spawnRecaptureNpc(world, lordType, castle, 4900)) != null) {
            lord.method_5665((class_2561)class_2561.method_43470((String)GotCastleRecaptures.resolveRecaptureLordName(world, castle, recapture, kingdom)));
            recapture.addAttackerUuid(lord.method_5667());
        }
    }

    private static class_2338 findRecaptureSpawnPos(class_3218 world, class_2338 bannerPos, int seedOffset) {
        for (int attempt = 0; attempt < 20; ++attempt) {
            int z;
            int y;
            double angle = (double)(seedOffset + attempt) * 0.7548776662466927 % (Math.PI * 2);
            double radius = GotCastles.recaptureAttackerMinSpawnRange() + (double)((seedOffset + attempt * 13) % Math.max(1, (int)(GotCastles.recaptureAttackerMaxSpawnRange() - GotCastles.recaptureAttackerMinSpawnRange())));
            int x = bannerPos.method_10263() + class_3532.method_15357((double)(Math.cos(angle) * radius));
            class_2338 candidate = new class_2338(x, y = world.method_8624(class_2902.class_2903.field_13203, x, z = bannerPos.method_10260() + class_3532.method_15357((double)(Math.sin(angle) * radius))), z);
            if (!(candidate.method_10262((class_2382)bannerPos) >= GotCastles.recaptureAttackerMinSpawnRange() * GotCastles.recaptureAttackerMinSpawnRange())) continue;
            return candidate;
        }
        return GotCastles.findNpcSpawnPos(world, bannerPos, 9000 + seedOffset);
    }

    private static void pruneAttackers(class_3218 world, CastleWorldState state, CastleRecaptureState recapture) {
        boolean changed = false;
        for (UUID attackerUuid : List.copyOf(recapture.getAttackerUuids())) {
            class_1309 living;
            class_1297 entity = world.method_14190(attackerUuid);
            if (entity instanceof class_1309 && (living = (class_1309)entity).method_5805()) continue;
            GotCastles.clearActiveGateBreach(attackerUuid);
            recapture.removeAttackerUuid(attackerUuid);
            changed = true;
        }
        if (changed) {
            state.method_80();
        }
    }

    private static void clearAttackers(class_3218 world, CastleRecaptureState recapture) {
        if (world == null || recapture == null) {
            return;
        }
        for (UUID attackerUuid : recapture.getAttackerUuids()) {
            GotCastles.clearActiveGateBreach(attackerUuid);
            class_1297 entity = world.method_14190(attackerUuid);
            if (entity == null || !entity.method_5805()) continue;
            entity.method_31472();
        }
        recapture.clearAttackers();
    }

    private static void winDefenseInternal(class_3218 world, CastleInstance castle, CastleRecaptureState recapture) {
        class_3222 player;
        class_3222 class_32222 = player = recapture.getPlayerUuid() == null ? null : world.method_8503().method_3760().method_14602(recapture.getPlayerUuid());
        if (player != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Recapture defeated. The castle remains yours.").method_27692(class_124.field_1065), false);
        }
        GotCastleRecaptures.appendOutcomeLog(world, recapture, castle, false);
        GotCastleRecaptures.resetState(world, castle, castle.getOwnerPlayerUuid());
    }

    private static void resolveSuccessfulRecapture(class_3218 world, CastleInstance castle, CastleRecaptureState recapture, class_2561 failureMessage) {
        class_3222 player;
        class_3222 class_32222 = player = recapture.getPlayerUuid() == null ? null : world.method_8503().method_3760().method_14602(recapture.getPlayerUuid());
        if (failureMessage != null && GotCastles.canSendPlayPackets(player)) {
            player.method_7353(failureMessage, false);
        }
        String kingdomId = GotCastleRecaptures.originalKingdomIdFor(castle);
        CastlePresetMetadata metadata = GotCastles.getPresetMetadata(castle.getId());
        String religionId = GotCastleRecaptures.originalReligionIdFor(castle, kingdomId);
        castle.setOwnerPlayerUuid(null);
        castle.setOwnerKingdomId(kingdomId);
        castle.setOwnerHouseId(GotCastles.resolveOwnedHouseId("", kingdomId, metadata != null ? metadata.houseId() : ""));
        castle.setOwnerReligionId(religionId);
        castle.setLordName("");
        GotCastleRecaptures.appendOutcomeLog(world, recapture, castle, true);
        GotCastleRecaptures.clearAttackers(world, recapture);
        GotCastles.respawnCastlePopulation(world, castle);
        GotCastleRecaptures.end(world, castle.getId(), true, true);
        GotCastles.syncStateToPlayers(world);
        GotCastles.syncCastleMapToPlayers(world);
    }

    private static Kingdom resolveOriginalKingdom(CastleInstance castle) {
        return GotKingdoms.get(class_2960.method_12829((String)GotCastleRecaptures.originalKingdomIdFor(castle)));
    }

    private static String resolveRecaptureLordName(class_3218 world, CastleInstance castle, CastleRecaptureState recapture, @Nullable Kingdom kingdom) {
        if (recapture != null && !recapture.getAttackingLordName().isBlank()) {
            return recapture.getAttackingLordName();
        }
        if (kingdom == null) {
            return "Unknown Lord";
        }
        String lordName = GotCastleOwnerNames.createFullName(kingdom, castle.getBannerPos().method_10063() ^ world.method_8510());
        if (recapture != null) {
            recapture.setAttackingLordName(lordName);
        }
        return lordName;
    }

    private static void appendAttemptLog(class_3218 world, CastleRecaptureState recapture, CastleInstance castle) {
        String lordName;
        if (world == null || recapture == null || castle == null) {
            return;
        }
        String kingdomId = recapture.getAttackingKingdomId();
        String string = lordName = recapture.getAttackingLordName().isBlank() ? "Unknown Lord" : recapture.getAttackingLordName();
        if (kingdomId.isBlank()) {
            return;
        }
        String castleName = GotCastles.displayNameForCastle(castle);
        GotKingdomEventLogs.append(world, kingdomId, lordName + " Attempts to Recapture " + castleName);
        GotCastles.announceKingdomConflictAttempt(world, castleName, "Recapture Incoming");
    }

    private static void appendOutcomeLog(class_3218 world, CastleRecaptureState recapture, CastleInstance castle, boolean successful) {
        String lordName;
        if (world == null || recapture == null || castle == null) {
            return;
        }
        String kingdomId = recapture.getAttackingKingdomId();
        String string = lordName = recapture.getAttackingLordName().isBlank() ? "Unknown Lord" : recapture.getAttackingLordName();
        if (kingdomId.isBlank()) {
            return;
        }
        String castleName = GotCastles.displayNameForCastle(castle);
        String line = successful ? lordName + " successfully Recaptured " + castleName : lordName + " unsuccessfully Recaptured " + castleName;
        GotKingdomEventLogs.append(world, kingdomId, line);
        GotCastles.announceKingdomConflictResult(world, castleName, successful ? "Recapture Successful" : "Recapture Unsuccessful", successful ? kingdomId : GotCastleRecaptures.originalKingdomIdFor(castle), successful ? GotCastleRecaptures.originalKingdomIdFor(castle) : kingdomId);
    }

    private static String originalKingdomIdFor(CastleInstance castle) {
        CastlePresetMetadata metadata = GotCastles.getPresetMetadata(castle.getId());
        String kingdomId = metadata != null ? metadata.kingdomId() : castle.getOwnerKingdomId();
        return GotCastles.canonicalKingdomId(kingdomId);
    }

    private static String originalReligionIdFor(CastleInstance castle, String fallbackKingdomId) {
        CastlePresetMetadata metadata = GotCastles.getPresetMetadata(castle.getId());
        if (metadata != null && !metadata.religionId().isBlank()) {
            return metadata.religionId();
        }
        return GotCastles.defaultReligionIdForKingdom(fallbackKingdomId);
    }

    private static boolean isRecaptureAttacker(class_3218 world, String castleId, UUID entityUuid) {
        CastleRecaptureState recapture = GotCastles.getWorldState(world).getRecapture(castleId);
        return recapture != null && recapture.isAttacker(entityUuid);
    }

    private static boolean isKingdomAiAttacker(class_3218 world, String castleId, UUID entityUuid) {
        KingdomAiInvasionWorldState.KingdomAiInvasion invasion = KingdomAiInvasionWorldState.get(world).get(castleId);
        return invasion != null && invasion.attackerUuids().contains(entityUuid);
    }

    private static final class ActiveCastleRecapture {
        private final class_3213 bossBar = new class_3213((class_2561)class_2561.method_43470((String)""), class_1259.class_1260.field_5782, class_1259.class_1261.field_5795);

        private ActiveCastleRecapture(String castleId) {
        }

        private class_3213 bossBar() {
            return this.bossBar;
        }

        private void updateIncomingBossBar(long remainingTicks, long durationTicks) {
            this.bossBar.method_5413((class_2561)class_2561.method_43470((String)("Incoming Recapture | " + GotCastles.formatRemainingTime(remainingTicks))));
            float percent = durationTicks <= 0L ? 0.0f : class_3532.method_15363((float)((float)remainingTicks / (float)durationTicks), (float)0.0f, (float)1.0f);
            this.bossBar.method_5408(percent);
        }

        private void updateInvasionBossBar(int attackersRemaining, int attackersTotal) {
            this.bossBar.method_5413((class_2561)class_2561.method_43470((String)("Recapture Assault | " + attackersRemaining + " remaining")));
            float percent = attackersTotal <= 0 ? 0.0f : class_3532.method_15363((float)((float)attackersRemaining / (float)attackersTotal), (float)0.0f, (float)1.0f);
            this.bossBar.method_5408(percent);
        }
    }
}

