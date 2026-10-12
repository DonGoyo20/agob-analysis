/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.networking.v1.PacketSender
 *  net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2561
 *  net.minecraft.class_2680
 *  net.minecraft.class_2794
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2794;
import net.minecraft.class_2902;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import trep.got.GotMod;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleInvasionMode;
import trep.got.castle.PlayerCastleFastTravelCooldownHolder;
import trep.got.castle.PlayerCastleTravelHolder;
import trep.got.compat.BetterCombatCompat;
import trep.got.dialogue.NpcChatAction;
import trep.got.entity.AbstractDialogueHumanoidEntity;
import trep.got.entity.AbstractKingdomBlacksmithNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.AbstractKingdomVillageElderNpcEntity;
import trep.got.entity.DragonEntity;
import trep.got.entity.HireableUnitEntity;
import trep.got.entity.ScorpionCrossbowEntity;
import trep.got.guard.GateWatcherGuardService;
import trep.got.house.PlayerHouseService;
import trep.got.item.SpearItem;
import trep.got.kingdom.ReputationExperienceService;
import trep.got.network.BanditCampNegotiationResultPayload;
import trep.got.network.BlacksmithSessionClearedPayload;
import trep.got.network.CastleInvasionStatePayload;
import trep.got.network.CastleMapPayload;
import trep.got.network.CharacterSelectionStatePayload;
import trep.got.network.CharacterSpawnSearchPayload;
import trep.got.network.ClaimOutlinePayload;
import trep.got.network.CloseNpcDialoguePayload;
import trep.got.network.FastTravelCooldownPayload;
import trep.got.network.KingdomEventLogPayload;
import trep.got.network.KingdomRelationsPayload;
import trep.got.network.MainQuestStatePayload;
import trep.got.network.ModAdvancementPayload;
import trep.got.network.NpcChatResultPayload;
import trep.got.network.NpcDialogueRelationPayload;
import trep.got.network.OpenCastleScreenPayload;
import trep.got.network.OpenCreateHouseScreenPayload;
import trep.got.network.OpenCreateKingdomScreenPayload;
import trep.got.network.OpenGateWarningPayload;
import trep.got.network.OpenLockpickingCaughtPayload;
import trep.got.network.OpenLockpickingPayload;
import trep.got.network.OpenNpcDialoguePayload;
import trep.got.network.OpenVillageScreenPayload;
import trep.got.network.PlayerHouseStatePayload;
import trep.got.network.QuestJournalStatePayload;
import trep.got.network.RequestBanditCampDecisionPayload;
import trep.got.network.RequestBlacksmithTroopUpgradePayload;
import trep.got.network.RequestCancelVoyagePayload;
import trep.got.network.RequestCastleFastTravelPayload;
import trep.got.network.RequestCastleInvasionPayload;
import trep.got.network.RequestCastleScreenPayload;
import trep.got.network.RequestCastleStoragePayload;
import trep.got.network.RequestCommanderActionPayload;
import trep.got.network.RequestCommanderBlockTargetPayload;
import trep.got.network.RequestCommanderTargetPayload;
import trep.got.network.RequestCreateKingdomPayload;
import trep.got.network.RequestCreatePlayerHousePayload;
import trep.got.network.RequestDragonFlightPayload;
import trep.got.network.RequestGateWarningActionPayload;
import trep.got.network.RequestHireTroopPayload;
import trep.got.network.RequestKingdomDiplomacyPayload;
import trep.got.network.RequestKnighthoodPayload;
import trep.got.network.RequestLockpickingExcusePayload;
import trep.got.network.RequestLockpickingResultPayload;
import trep.got.network.RequestNpcChatActionPayload;
import trep.got.network.RequestQuestActionPayload;
import trep.got.network.RequestRemoveSettlerPayload;
import trep.got.network.RequestRobberAmbushDecisionPayload;
import trep.got.network.RequestScorpionCrossbowActionPayload;
import trep.got.network.RequestSettlementLevelUpPayload;
import trep.got.network.RequestSettlerDecisionPayload;
import trep.got.network.RequestSpearStabPayload;
import trep.got.network.RequestStoreTroopsPayload;
import trep.got.network.RequestSummonMarriedNpcPayload;
import trep.got.network.RequestSummonTroopsPayload;
import trep.got.network.RequestTailorActionPayload;
import trep.got.network.RequestTailorScreenPayload;
import trep.got.network.RequestUpgradeTroopPayload;
import trep.got.network.RequestVillageRaidPayload;
import trep.got.network.RequestVillageTradePayload;
import trep.got.network.RequestVoyagePayload;
import trep.got.network.SelectCharacterPayload;
import trep.got.network.SetNpcDialogueStatePayload;
import trep.got.network.SkillPointGainPayload;
import trep.got.network.TailorStatePayload;
import trep.got.network.TeleportMapPayload;
import trep.got.network.TroopRosterPayload;
import trep.got.network.UpdatePlayerSkinBuilderPayload;
import trep.got.network.VillageMapPayload;
import trep.got.network.VillageRaidStatePayload;
import trep.got.network.VoyageStatePayload;
import trep.got.quest.BanditCampQuestService;
import trep.got.quest.WildlingRaiderCampQuestService;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacters;
import trep.got.registry.GotDynastyNpcs;
import trep.got.registry.GotLockpicking;
import trep.got.registry.GotMainQuests;
import trep.got.registry.GotNpcRelations;
import trep.got.registry.GotQuests;
import trep.got.registry.GotVillages;
import trep.got.relations.KingdomWarService;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.source.CharismaSkillExperienceSource;
import trep.got.tailor.GotTailors;
import trep.got.troop.GotTroops;
import trep.got.village.VillageInstance;
import trep.got.village.VillageWorldState;
import trep.got.village.trade.VillageTradeService;
import trep.got.world.dimension.GotDimensionKeys;
import trep.got.world.event.LordLetterRobberAmbushService;
import trep.got.world.gen.MapChunkGenerator;

public final class GotNetworking {
    private static final long FAST_TRAVEL_COOLDOWN_MS = 120000L;
    private static final long PENDING_FAST_TRAVEL_REQUEST_TIMEOUT_TICKS = 900L;
    private static final long PENDING_FAST_TRAVEL_SCAN_INTERVAL_TICKS = 2L;
    private static final int FAST_TRAVEL_PRELOAD_RADIUS_CHUNKS = 1;
    private static final int MAP_TELEPORT_CASTLE_MATCH_RADIUS_BLOCKS = 384;
    private static final Map<UUID, PendingFastTravelRequest> PENDING_FAST_TRAVEL_REQUESTS = new LinkedHashMap<UUID, PendingFastTravelRequest>();

    private GotNetworking() {
    }

    public static void initialize() {
        PayloadTypeRegistry.playC2S().register(TeleportMapPayload.ID, TeleportMapPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestCommanderActionPayload.ID, RequestCommanderActionPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestCommanderBlockTargetPayload.ID, RequestCommanderBlockTargetPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestCommanderTargetPayload.ID, RequestCommanderTargetPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestCastleFastTravelPayload.ID, RequestCastleFastTravelPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestCastleInvasionPayload.ID, RequestCastleInvasionPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestCastleScreenPayload.ID, RequestCastleScreenPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestCastleStoragePayload.ID, RequestCastleStoragePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SelectCharacterPayload.ID, SelectCharacterPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(UpdatePlayerSkinBuilderPayload.ID, UpdatePlayerSkinBuilderPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestKnighthoodPayload.ID, RequestKnighthoodPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestNpcChatActionPayload.ID, RequestNpcChatActionPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestQuestActionPayload.ID, RequestQuestActionPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestHireTroopPayload.ID, RequestHireTroopPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestKingdomDiplomacyPayload.ID, RequestKingdomDiplomacyPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestCancelVoyagePayload.ID, RequestCancelVoyagePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestCreatePlayerHousePayload.ID, RequestCreatePlayerHousePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestCreateKingdomPayload.ID, RequestCreateKingdomPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestStoreTroopsPayload.ID, RequestStoreTroopsPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestSummonTroopsPayload.ID, RequestSummonTroopsPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestSummonMarriedNpcPayload.ID, RequestSummonMarriedNpcPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestTailorScreenPayload.ID, RequestTailorScreenPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestTailorActionPayload.ID, RequestTailorActionPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestUpgradeTroopPayload.ID, RequestUpgradeTroopPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestVoyagePayload.ID, RequestVoyagePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestVillageRaidPayload.ID, RequestVillageRaidPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestVillageTradePayload.ID, RequestVillageTradePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SetNpcDialogueStatePayload.ID, SetNpcDialogueStatePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestSpearStabPayload.ID, RequestSpearStabPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestDragonFlightPayload.ID, RequestDragonFlightPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestScorpionCrossbowActionPayload.ID, RequestScorpionCrossbowActionPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestSettlerDecisionPayload.ID, RequestSettlerDecisionPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestSettlementLevelUpPayload.ID, RequestSettlementLevelUpPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestRemoveSettlerPayload.ID, RequestRemoveSettlerPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestRobberAmbushDecisionPayload.ID, RequestRobberAmbushDecisionPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestBanditCampDecisionPayload.ID, RequestBanditCampDecisionPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestBlacksmithTroopUpgradePayload.ID, RequestBlacksmithTroopUpgradePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestLockpickingResultPayload.ID, RequestLockpickingResultPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestLockpickingExcusePayload.ID, RequestLockpickingExcusePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RequestGateWarningActionPayload.ID, RequestGateWarningActionPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(CharacterSelectionStatePayload.ID, CharacterSelectionStatePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(CharacterSpawnSearchPayload.ID, CharacterSpawnSearchPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(CastleMapPayload.ID, CastleMapPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(VillageMapPayload.ID, VillageMapPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(VillageRaidStatePayload.ID, VillageRaidStatePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(CastleInvasionStatePayload.ID, CastleInvasionStatePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(OpenCastleScreenPayload.ID, OpenCastleScreenPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(OpenCreateHouseScreenPayload.ID, OpenCreateHouseScreenPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(OpenCreateKingdomScreenPayload.ID, OpenCreateKingdomScreenPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(OpenVillageScreenPayload.ID, OpenVillageScreenPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ClaimOutlinePayload.ID, ClaimOutlinePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(BlacksmithSessionClearedPayload.ID, BlacksmithSessionClearedPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(MainQuestStatePayload.ID, MainQuestStatePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(QuestJournalStatePayload.ID, QuestJournalStatePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ModAdvancementPayload.ID, ModAdvancementPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SkillPointGainPayload.ID, SkillPointGainPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(TailorStatePayload.ID, TailorStatePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(TroopRosterPayload.ID, TroopRosterPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(VoyageStatePayload.ID, VoyageStatePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(FastTravelCooldownPayload.ID, FastTravelCooldownPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(KingdomEventLogPayload.ID, KingdomEventLogPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(KingdomRelationsPayload.ID, KingdomRelationsPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(OpenNpcDialoguePayload.ID, OpenNpcDialoguePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(CloseNpcDialoguePayload.ID, CloseNpcDialoguePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(NpcChatResultPayload.ID, NpcChatResultPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(NpcDialogueRelationPayload.ID, NpcDialogueRelationPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(BanditCampNegotiationResultPayload.ID, BanditCampNegotiationResultPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(PlayerHouseStatePayload.ID, PlayerHouseStatePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(OpenLockpickingPayload.ID, OpenLockpickingPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(OpenLockpickingCaughtPayload.ID, OpenLockpickingCaughtPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(OpenGateWarningPayload.ID, OpenGateWarningPayload.CODEC);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> GotNetworking.syncFastTravelCooldown(handler.field_14140, sender));
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> PlayerHouseService.syncOnJoin(server, handler.field_14140, sender));
        ServerTickEvents.END_SERVER_TICK.register(GotNetworking::tickPendingFastTravelRequests);
        ServerPlayNetworking.registerGlobalReceiver(TeleportMapPayload.ID, (payload, context) -> {
            if (!context.player().method_7337()) {
                return;
            }
            class_3218 world = context.player().method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
            if (world == null) {
                return;
            }
            int x = payload.x();
            int z = payload.z();
            context.server().execute(() -> {
                class_3222 player = context.player();
                class_2338 arrivalPos = GotNetworking.estimateInitialMapTeleportPos(world, x, z);
                player.method_48105(world, (double)arrivalPos.method_10263() + 0.5, (double)arrivalPos.method_10264(), (double)arrivalPos.method_10260() + 0.5, Set.of(), player.method_36454(), player.method_36455());
                player.method_24830(true);
                player.method_18800(0.0, 0.0, 0.0);
                GotCastles.ensureNearestWorldgenCastleClaim(world, new class_2338(x, 0, z), 384);
            });
        });
        ServerPlayNetworking.registerGlobalReceiver(RequestCommanderActionPayload.ID, (payload, context) -> context.server().execute(() -> GotTroops.handleCommanderAction(context.player(), payload)));
        ServerPlayNetworking.registerGlobalReceiver(RequestCommanderBlockTargetPayload.ID, (payload, context) -> context.server().execute(() -> GotTroops.handleCommanderBlockTarget(context.player(), payload.targetPos(), payload.facingYaw())));
        ServerPlayNetworking.registerGlobalReceiver(RequestCommanderTargetPayload.ID, (payload, context) -> context.server().execute(() -> GotTroops.handleCommanderEntityTarget(context.player(), payload.targetEntityId(), payload.facingYaw())));
        ServerPlayNetworking.registerGlobalReceiver(RequestCastleScreenPayload.ID, (payload, context) -> context.server().execute(() -> GotCastles.openCastleScreen(context.player(), payload.castleId())));
        ServerPlayNetworking.registerGlobalReceiver(RequestCastleStoragePayload.ID, (payload, context) -> context.server().execute(() -> GotCastles.openCastleStorage(context.player(), payload.castleId())));
        ServerPlayNetworking.registerGlobalReceiver(RequestCastleInvasionPayload.ID, (payload, context) -> context.server().execute(() -> GotCastles.handleCastleInvasionRequest(context.player(), payload.castleId(), CastleInvasionMode.byId(payload.modeId()))));
        ServerPlayNetworking.registerGlobalReceiver(RequestCastleFastTravelPayload.ID, (payload, context) -> context.server().execute(() -> {
            class_3222 player = context.player();
            class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
            if (world == null || payload.castleId() == null || payload.castleId().isBlank()) {
                return;
            }
            PlayerCastleFastTravelCooldownHolder cooldownHolder = (PlayerCastleFastTravelCooldownHolder)player;
            long nowMs = System.currentTimeMillis();
            if (cooldownHolder.got$hasFastTravelCooldown(nowMs)) {
                GotNetworking.syncFastTravelCooldown(player);
                return;
            }
            if (!((PlayerCastleTravelHolder)player).got$hasUnlockedCastle(payload.castleId())) {
                return;
            }
            CastleInstance castle = GotCastles.getWorldState(world).get(payload.castleId());
            boolean createdCastleClaim = false;
            if (castle == null && GotCastles.requestPriorityCastleGeneration(world, payload.castleId())) {
                castle = GotCastles.getWorldState(world).get(payload.castleId());
                boolean bl = createdCastleClaim = castle != null;
            }
            if (castle != null) {
                if (!GotCastles.isCastleTravelReady(world, payload.castleId())) {
                    if (!GotCastles.requestPriorityCastleGeneration(world, payload.castleId())) {
                        player.method_7353((class_2561)class_2561.method_43470((String)"That castle is not ready for travel yet.").method_27692(class_124.field_1061), true);
                        return;
                    }
                    PENDING_FAST_TRAVEL_REQUESTS.put(player.method_5667(), new PendingFastTravelRequest(payload.castleId(), true, world.method_8510(), 0));
                    player.method_7353((class_2561)class_2561.method_43470((String)("Preparing " + GotCastles.resolveCastleLocationName(world, payload.castleId()) + " for fast travel.")).method_27692(class_124.field_1065), true);
                    return;
                }
                if (createdCastleClaim || !GotNetworking.isFastTravelAreaLoaded(world, castle.getBannerPos(), 1)) {
                    PENDING_FAST_TRAVEL_REQUESTS.put(player.method_5667(), new PendingFastTravelRequest(payload.castleId(), true, world.method_8510(), 0));
                    player.method_7353((class_2561)class_2561.method_43470((String)("Preparing " + GotCastles.resolveCastleLocationName(world, payload.castleId()) + " for fast travel.")).method_27692(class_124.field_1065), true);
                    return;
                }
            } else {
                VillageInstance village = VillageWorldState.get(world).get(payload.castleId());
                if (village != null && !village.hasGeneratedStructures()) {
                    if (!GotVillages.requestPriorityVillageGeneration(world, payload.castleId())) {
                        player.method_7353((class_2561)class_2561.method_43470((String)"That village is not ready for travel yet.").method_27692(class_124.field_1061), true);
                        return;
                    }
                    PENDING_FAST_TRAVEL_REQUESTS.put(player.method_5667(), new PendingFastTravelRequest(payload.castleId(), false, world.method_8510(), 0));
                    player.method_7353((class_2561)class_2561.method_43470((String)("Preparing " + GotVillages.resolveVillageLocationName(world, payload.castleId()) + " for fast travel.")).method_27692(class_124.field_1065), true);
                    return;
                }
            }
            if (!GotNetworking.tryCompleteFastTravel(player, world, payload.castleId(), nowMs)) {
                player.method_7353((class_2561)class_2561.method_43470((String)"That destination is not ready for travel yet.").method_27692(class_124.field_1061), true);
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(SelectCharacterPayload.ID, (payload, context) -> context.server().execute(() -> GotCharacters.assignKingdom(context.player(), payload.religionId(), payload.kingdomId(), payload.optionId(), payload.geneticTraitId(), payload.personalityTraitIds(), payload.skinBuilderProfile())));
        ServerPlayNetworking.registerGlobalReceiver(UpdatePlayerSkinBuilderPayload.ID, (payload, context) -> context.server().execute(() -> GotCharacters.updatePlayerSkinBuilder(context.player(), payload.profile())));
        ServerPlayNetworking.registerGlobalReceiver(RequestTailorScreenPayload.ID, (payload, context) -> context.server().execute(() -> GotTailors.open(context.player())));
        ServerPlayNetworking.registerGlobalReceiver(RequestTailorActionPayload.ID, (payload, context) -> context.server().execute(() -> GotTailors.handleAction(context.player(), payload.action(), payload.slot(), payload.clothingId(), payload.dyeId())));
        ServerPlayNetworking.registerGlobalReceiver(RequestKnighthoodPayload.ID, (payload, context) -> context.server().execute(() -> ReputationExperienceService.completeKnighthoodCeremony(context.player(), payload.targetEntityId())));
        ServerPlayNetworking.registerGlobalReceiver(RequestNpcChatActionPayload.ID, (payload, context) -> context.server().execute(() -> {
            class_1297 entity = context.player().method_37908().method_8469(payload.targetEntityId());
            NpcChatAction action = NpcChatAction.byId(payload.actionId());
            GotNpcRelations.handleChatAction(context.player(), payload.targetEntityId(), action);
            GotMainQuests.onNpcChatAction(context.player(), entity, action);
        }));
        ServerPlayNetworking.registerGlobalReceiver(RequestQuestActionPayload.ID, (payload, context) -> context.server().execute(() -> GotQuests.handleQuestAction(context.player(), payload.targetEntityId(), payload.actionId())));
        ServerPlayNetworking.registerGlobalReceiver(RequestHireTroopPayload.ID, (payload, context) -> context.server().execute(() -> {
            class_1297 entity = context.player().method_37908().method_8469(payload.targetEntityId());
            if (entity instanceof HireableUnitEntity) {
                HireableUnitEntity hireableUnit = (HireableUnitEntity)entity;
                GotTroops.tryHire(context.player(), hireableUnit);
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(RequestKingdomDiplomacyPayload.ID, (payload, context) -> context.server().execute(() -> KingdomWarService.handlePlayerKingDiplomacyAction(context.player(), payload.targetKingdomId(), payload.proposePeace())));
        ServerPlayNetworking.registerGlobalReceiver(RequestCancelVoyagePayload.ID, (payload, context) -> context.server().execute(() -> {
            GotMod.LOGGER.info("Voyage cancel packet received player={} playerUuid={} vehicle={} vehicleUuid={}", new Object[]{context.player().method_5477().getString(), context.player().method_5845(), context.player().method_5854() == null ? "null" : context.player().method_5854().method_5864(), context.player().method_5854() == null ? "n/a" : context.player().method_5854().method_5845()});
            GotCastles.cancelVoyage(context.player(), true);
        }));
        ServerPlayNetworking.registerGlobalReceiver(RequestCreatePlayerHousePayload.ID, (payload, context) -> context.server().execute(() -> GotQuests.handleCreatePlayerHouseRequest(context.player(), payload.targetEntityId(), payload.houseName(), payload.sigilColors(), payload.completePledge())));
        ServerPlayNetworking.registerGlobalReceiver(RequestCreateKingdomPayload.ID, (payload, context) -> context.server().execute(() -> GotCastles.handleCreateKingdomRequest(context.player(), payload.castleId(), payload.kingdomName(), payload.parentKingdomId())));
        ServerPlayNetworking.registerGlobalReceiver(RequestSummonTroopsPayload.ID, (payload, context) -> context.server().execute(() -> GotTroops.trySummonTroops(context.player())));
        ServerPlayNetworking.registerGlobalReceiver(RequestSummonMarriedNpcPayload.ID, (payload, context) -> context.server().execute(() -> GotDynastyNpcs.handleSummonRequest(context.player(), payload.memberId())));
        ServerPlayNetworking.registerGlobalReceiver(RequestStoreTroopsPayload.ID, (payload, context) -> context.server().execute(() -> GotTroops.tryStoreTroops(context.player())));
        ServerPlayNetworking.registerGlobalReceiver(RequestUpgradeTroopPayload.ID, (payload, context) -> context.server().execute(() -> {
            class_2960 troopId = class_2960.method_12829((String)payload.troopId());
            if (troopId != null) {
                GotTroops.tryUpgradeTroopType(context.player(), troopId);
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(RequestVoyagePayload.ID, (payload, context) -> context.server().execute(() -> GotCastles.handleVoyageRequest(context.player(), payload.targetEntityId(), payload.castleId(), payload.shipTypeId())));
        ServerPlayNetworking.registerGlobalReceiver(RequestVillageTradePayload.ID, (payload, context) -> context.server().execute(() -> {
            class_1297 entity = context.player().method_37908().method_8469(payload.targetEntityId());
            if (entity instanceof AbstractKingdomVillageElderNpcEntity) {
                AbstractKingdomVillageElderNpcEntity elder = (AbstractKingdomVillageElderNpcEntity)entity;
                VillageTradeService.openVillageTrade(context.player(), elder);
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(RequestVillageRaidPayload.ID, (payload, context) -> context.server().execute(() -> GotVillages.handleVillageRaidRequest(context.player(), payload.villageId())));
        ServerPlayNetworking.registerGlobalReceiver(SetNpcDialogueStatePayload.ID, (payload, context) -> context.server().execute(() -> {
            class_1297 entity = context.player().method_37908().method_8469(payload.targetEntityId());
            if (!(entity instanceof AbstractDialogueHumanoidEntity)) {
                return;
            }
            AbstractDialogueHumanoidEntity dialogueEntity = (AbstractDialogueHumanoidEntity)entity;
            if (payload.active()) {
                if (GotLockpicking.isLockpickingReportLocked(context.player(), entity)) {
                    context.player().method_7353((class_2561)class_2561.method_43470((String)"They are running for the guards.").method_27692(class_124.field_1061), true);
                    ServerPlayNetworking.send((class_3222)context.player(), (class_8710)new CloseNpcDialoguePayload(payload.targetEntityId()));
                    return;
                }
                if (GateWatcherGuardService.isInteractionLocked(context.player(), entity)) {
                    context.player().method_7353((class_2561)class_2561.method_43470((String)"The guard is watching you.").method_27692(class_124.field_1061), true);
                    ServerPlayNetworking.send((class_3222)context.player(), (class_8710)new CloseNpcDialoguePayload(payload.targetEntityId()));
                    return;
                }
                if (dialogueEntity.beginDialogue(context.player()) && entity instanceof AbstractKingdomNpcEntity) {
                    class_1937 patt1$temp;
                    AbstractKingdomNpcEntity kingdomNpc = (AbstractKingdomNpcEntity)entity;
                    if (entity instanceof AbstractKingdomBlacksmithNpcEntity) {
                        AbstractKingdomBlacksmithNpcEntity blacksmithNpc = (AbstractKingdomBlacksmithNpcEntity)entity;
                        class_3222 patt0$temp = context.player();
                        if (patt0$temp instanceof PlayerSkillHolder) {
                            PlayerSkillHolder skillHolder = (PlayerSkillHolder)patt0$temp;
                            skillHolder.got$getSkillProfile().markTalkedToBlacksmithKingdom(blacksmithNpc.getKingdomId());
                        }
                    }
                    if ((patt1$temp = context.player().method_37908()) instanceof class_3218) {
                        class_3218 world = (class_3218)patt1$temp;
                        GotCastles.ensureCastleNpcIdentity(world, kingdomNpc);
                    }
                    CharismaSkillExperienceSource.onNpcInteraction(context.player(), kingdomNpc);
                    GotNpcRelations.sendDialogueRelation(context.player(), kingdomNpc);
                    GotMainQuests.onNpcDialogueStarted(context.player(), kingdomNpc);
                }
                return;
            }
            dialogueEntity.endDialogue(context.player().method_5667());
        }));
        ServerPlayNetworking.registerGlobalReceiver(RequestSpearStabPayload.ID, (payload, context) -> context.server().execute(() -> {
            class_1792 patt0$temp;
            if (!BetterCombatCompat.isLoaded() && (patt0$temp = context.player().method_6047().method_7909()) instanceof SpearItem) {
                SpearItem spear = (SpearItem)patt0$temp;
                spear.stab((class_1309)context.player(), class_1268.field_5808);
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(RequestDragonFlightPayload.ID, (payload, context) -> context.server().execute(() -> {
            class_1297 patt0$temp = context.player().method_5854();
            if (patt0$temp instanceof DragonEntity) {
                DragonEntity dragon = (DragonEntity)patt0$temp;
                dragon.requestMountedFlightInput((class_1657)context.player(), payload.toggleFlight(), payload.climbHeld(), payload.fireBreathHeld(), payload.forwardInput(), payload.sidewaysInput());
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(RequestScorpionCrossbowActionPayload.ID, (payload, context) -> context.server().execute(() -> {
            class_1297 targetEntity = context.player().method_37908().method_8469(payload.entityId());
            ScorpionCrossbowEntity scorpionCrossbow = null;
            if (targetEntity instanceof ScorpionCrossbowEntity) {
                ScorpionCrossbowEntity targetScorpionCrossbow;
                scorpionCrossbow = targetScorpionCrossbow = (ScorpionCrossbowEntity)targetEntity;
            } else {
                class_1297 patt0$temp = context.player().method_5854();
                if (patt0$temp instanceof ScorpionCrossbowEntity) {
                    ScorpionCrossbowEntity vehicleScorpionCrossbow;
                    scorpionCrossbow = vehicleScorpionCrossbow = (ScorpionCrossbowEntity)patt0$temp;
                }
            }
            if (scorpionCrossbow != null && scorpionCrossbow.method_5626((class_1297)context.player())) {
                scorpionCrossbow.handleFireKey((class_1657)context.player());
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(RequestSettlerDecisionPayload.ID, (payload, context) -> context.server().execute(() -> GotCastles.handleSettlerDecision(context.player(), payload.targetEntityId(), payload.accept())));
        ServerPlayNetworking.registerGlobalReceiver(RequestSettlementLevelUpPayload.ID, (payload, context) -> context.server().execute(() -> GotCastles.handleSettlementLevelUp(context.player(), payload.castleId())));
        ServerPlayNetworking.registerGlobalReceiver(RequestRemoveSettlerPayload.ID, (payload, context) -> context.server().execute(() -> GotCastles.handleRemoveSettler(context.player(), payload.castleId(), payload.settlerId())));
        ServerPlayNetworking.registerGlobalReceiver(RequestRobberAmbushDecisionPayload.ID, (payload, context) -> context.server().execute(() -> LordLetterRobberAmbushService.handleDecision(context.player(), payload.targetEntityId(), payload.accept())));
        ServerPlayNetworking.registerGlobalReceiver(RequestBanditCampDecisionPayload.ID, (payload, context) -> context.server().execute(() -> {
            BanditCampQuestService.handleDecision(context.player(), payload.targetEntityId(), payload.negotiate());
            WildlingRaiderCampQuestService.handleDecision(context.player(), payload.targetEntityId(), payload.negotiate());
        }));
        ServerPlayNetworking.registerGlobalReceiver(RequestBlacksmithTroopUpgradePayload.ID, (payload, context) -> context.server().execute(() -> {
            class_1297 entity = context.player().method_37908().method_8469(payload.targetEntityId());
            class_2960 troopId = class_2960.method_12829((String)payload.troopId());
            class_2960 upgradeTargetId = class_2960.method_12829((String)payload.upgradeTargetId());
            if (entity instanceof AbstractKingdomNpcEntity) {
                AbstractKingdomNpcEntity blacksmith = (AbstractKingdomNpcEntity)entity;
                if (troopId != null && upgradeTargetId != null) {
                    GotTroops.tryUpgradeTroopAtBlacksmith(context.player(), blacksmith, troopId, upgradeTargetId);
                }
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(RequestLockpickingResultPayload.ID, (payload, context) -> context.server().execute(() -> GotLockpicking.handleResult(context.player(), payload.chestPos(), payload.success())));
        ServerPlayNetworking.registerGlobalReceiver(RequestLockpickingExcusePayload.ID, (payload, context) -> context.server().execute(() -> GotLockpicking.handleExcuse(context.player(), payload.ownerEntityId(), payload.optionId())));
        ServerPlayNetworking.registerGlobalReceiver(RequestGateWarningActionPayload.ID, (payload, context) -> context.server().execute(() -> GateWatcherGuardService.handleGateWarningAction(context.player(), payload.guardEntityId(), payload.actionId())));
    }

    private static class_2338 estimateInitialMapTeleportPos(class_3218 world, int x, int z) {
        int surfaceY = world.method_8615() + 64;
        class_2794 class_27942 = world.method_14178().method_12129();
        if (class_27942 instanceof MapChunkGenerator) {
            MapChunkGenerator mapGenerator = (MapChunkGenerator)class_27942;
            surfaceY = mapGenerator.estimateMotionBlockingSurfaceY(x, z);
        }
        int y = Math.max(world.method_31607() + 2, Math.min(surfaceY + 4, world.method_31600() - 2));
        return new class_2338(x, y, z);
    }

    private static class_2338 findSafeTeleportPos(class_3218 world, class_2338 anchor, int maxRadius) {
        class_2338 direct = GotNetworking.findSafeSurfacePosInColumn(world, anchor.method_10263(), anchor.method_10260());
        if (direct != null) {
            return direct;
        }
        for (int radius = 1; radius <= maxRadius; ++radius) {
            for (int dx = -radius; dx <= radius; ++dx) {
                class_2338 north = GotNetworking.findSafeSurfacePosInColumn(world, anchor.method_10263() + dx, anchor.method_10260() - radius);
                if (north != null) {
                    return north;
                }
                class_2338 south = GotNetworking.findSafeSurfacePosInColumn(world, anchor.method_10263() + dx, anchor.method_10260() + radius);
                if (south == null) continue;
                return south;
            }
            for (int dz = -radius + 1; dz <= radius - 1; ++dz) {
                class_2338 west = GotNetworking.findSafeSurfacePosInColumn(world, anchor.method_10263() - radius, anchor.method_10260() + dz);
                if (west != null) {
                    return west;
                }
                class_2338 east = GotNetworking.findSafeSurfacePosInColumn(world, anchor.method_10263() + radius, anchor.method_10260() + dz);
                if (east == null) continue;
                return east;
            }
        }
        int fallbackY = Math.max(world.method_8615() + 1, world.method_8624(class_2902.class_2903.field_13203, anchor.method_10263(), anchor.method_10260()));
        return new class_2338(anchor.method_10263(), fallbackY, anchor.method_10260());
    }

    @Nullable
    private static class_2338 findSafeSurfacePosInColumn(class_3218 world, int x, int z) {
        world.method_8497(x >> 4, z >> 4);
        int topY = Math.min(world.method_8624(class_2902.class_2903.field_13203, x, z), world.method_31600() - 2);
        int minGroundY = Math.max(world.method_31607(), topY - 8);
        for (int groundY = topY - 1; groundY >= minGroundY; --groundY) {
            class_2680 headState;
            class_2338 groundPos = new class_2338(x, groundY, z);
            class_2338 feetPos = groundPos.method_10084();
            class_2338 headPos = feetPos.method_10084();
            class_2680 groundState = world.method_8320(groundPos);
            class_2680 feetState = world.method_8320(feetPos);
            if (!GotNetworking.isSafeSpawnSpace(feetState, headState = world.method_8320(headPos)) || !GotNetworking.isSafeSpawnGround(world, groundPos, groundState)) continue;
            return feetPos;
        }
        return null;
    }

    private static boolean isSafeSpawnSpace(class_2680 feetState, class_2680 headState) {
        return feetState.method_26215() && headState.method_26215();
    }

    private static boolean isSafeSpawnGround(class_3218 world, class_2338 groundPos, class_2680 groundState) {
        if (!groundState.method_51366()) {
            return false;
        }
        if (!world.method_8316(groundPos).method_15769()) {
            return false;
        }
        return !groundState.method_27852(class_2246.field_10092) && !groundState.method_27852(class_2246.field_17350) && !groundState.method_27852(class_2246.field_23860);
    }

    public static void syncFastTravelCooldown(class_3222 player) {
        ServerPlayNetworking.send((class_3222)player, (class_8710)new FastTravelCooldownPayload(((PlayerCastleFastTravelCooldownHolder)player).got$getFastTravelCooldownEndsAtMs()));
    }

    private static void syncFastTravelCooldown(class_3222 player, PacketSender sender) {
        sender.sendPacket((class_8710)new FastTravelCooldownPayload(((PlayerCastleFastTravelCooldownHolder)player).got$getFastTravelCooldownEndsAtMs()));
    }

    private static void tickPendingFastTravelRequests(MinecraftServer server) {
        if (PENDING_FAST_TRAVEL_REQUESTS.isEmpty() || (long)server.method_3780() % 2L != 0L) {
            return;
        }
        class_3218 world = server.method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            PENDING_FAST_TRAVEL_REQUESTS.clear();
            return;
        }
        Iterator<Map.Entry<UUID, PendingFastTravelRequest>> iterator = PENDING_FAST_TRAVEL_REQUESTS.entrySet().iterator();
        while (iterator.hasNext()) {
            PendingFastTravelRequest warmedRequest;
            Map.Entry<UUID, PendingFastTravelRequest> entry = iterator.next();
            class_3222 player = server.method_3760().method_14602(entry.getKey());
            if (player == null || player.method_31481()) {
                iterator.remove();
                continue;
            }
            PendingFastTravelRequest request = entry.getValue();
            if (world.method_8510() - request.requestedAtTick() > 900L) {
                String destinationName = request.castle() ? GotCastles.resolveCastleLocationName(world, request.destinationId()) : GotVillages.resolveVillageLocationName(world, request.destinationId());
                player.method_7353((class_2561)class_2561.method_43470((String)("Fast travel preparation timed out for " + destinationName + ".")).method_27692(class_124.field_1061), true);
                iterator.remove();
                continue;
            }
            if (!request.castle()) {
                if (!GotVillages.isVillageTravelReady(world, request.destinationId())) {
                    GotVillages.requestPriorityVillageGeneration(world, request.destinationId());
                    continue;
                }
            } else if (!GotCastles.isCastleTravelReady(world, request.destinationId())) {
                GotCastles.requestPriorityCastleGeneration(world, request.destinationId());
                continue;
            }
            if ((warmedRequest = GotNetworking.preloadFastTravelArea(world, request)).preloadCursor() < GotNetworking.fastTravelPreloadChunkCount()) {
                entry.setValue(warmedRequest);
                continue;
            }
            PlayerCastleFastTravelCooldownHolder cooldownHolder = (PlayerCastleFastTravelCooldownHolder)player;
            long nowMs = System.currentTimeMillis();
            if (cooldownHolder.got$hasFastTravelCooldown(nowMs)) {
                GotNetworking.syncFastTravelCooldown(player);
                iterator.remove();
                continue;
            }
            if (!GotNetworking.tryCompleteFastTravel(player, world, request.destinationId(), nowMs)) continue;
            iterator.remove();
        }
    }

    private static boolean tryCompleteFastTravel(class_3222 player, class_3218 world, String destinationId, long nowMs) {
        int searchRadius;
        class_2338 travelPos;
        if (player == null || world == null || destinationId == null || destinationId.isBlank()) {
            return false;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(destinationId);
        if (castle != null) {
            travelPos = castle.getBannerPos();
            searchRadius = 8;
        } else {
            VillageInstance village = VillageWorldState.get(world).get(destinationId);
            if (village == null || !village.hasGeneratedStructures()) {
                return false;
            }
            travelPos = village.getCenterPos();
            searchRadius = 16;
        }
        class_2338 arrivalPos = GotNetworking.findSafeTeleportPos(world, travelPos, searchRadius);
        player.method_48105(world, (double)arrivalPos.method_10263() + 0.5, (double)arrivalPos.method_10264(), (double)arrivalPos.method_10260() + 0.5, Set.of(), player.method_36454(), player.method_36455());
        player.method_24830(true);
        player.method_18800(0.0, 0.0, 0.0);
        ((PlayerCastleFastTravelCooldownHolder)player).got$setFastTravelCooldownEndsAtMs(nowMs + 120000L);
        GotNetworking.syncFastTravelCooldown(player);
        return true;
    }

    private static PendingFastTravelRequest preloadFastTravelArea(class_3218 world, PendingFastTravelRequest request) {
        class_2338 anchor = GotNetworking.fastTravelAnchor(world, request);
        if (anchor == null || request.preloadCursor() >= GotNetworking.preloadChunkCount(1)) {
            return request;
        }
        int side = 3;
        int index = request.preloadCursor();
        int offsetX = index % side - 1;
        int offsetZ = index / side - 1;
        world.method_8497((anchor.method_10263() >> 4) + offsetX, (anchor.method_10260() >> 4) + offsetZ);
        return new PendingFastTravelRequest(request.destinationId(), request.castle(), request.requestedAtTick(), index + 1);
    }

    private static int fastTravelPreloadChunkCount() {
        return GotNetworking.preloadChunkCount(1);
    }

    private static int preloadChunkCount(int radiusChunks) {
        int side = radiusChunks * 2 + 1;
        return side * side;
    }

    private static boolean isFastTravelAreaLoaded(class_3218 world, class_2338 anchor, int radiusChunks) {
        int centerChunkX = anchor.method_10263() >> 4;
        int centerChunkZ = anchor.method_10260() >> 4;
        for (int chunkX = centerChunkX - radiusChunks; chunkX <= centerChunkX + radiusChunks; ++chunkX) {
            for (int chunkZ = centerChunkZ - radiusChunks; chunkZ <= centerChunkZ + radiusChunks; ++chunkZ) {
                if (world.method_8393(chunkX, chunkZ)) continue;
                return false;
            }
        }
        return true;
    }

    private static class_2338 fastTravelAnchor(class_3218 world, PendingFastTravelRequest request) {
        if (request.castle()) {
            CastleInstance castle = GotCastles.getWorldState(world).get(request.destinationId());
            return castle == null ? null : castle.getBannerPos();
        }
        VillageInstance village = VillageWorldState.get(world).get(request.destinationId());
        return village == null ? null : village.getCenterPos();
    }

    private record PendingFastTravelRequest(String destinationId, boolean castle, long requestedAtTick, int preloadCursor) {
    }
}

