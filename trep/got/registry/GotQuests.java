/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.networking.v1.PacketSender
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1735
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_8710
 */
package trep.got.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import trep.got.advancement.ModAdvancementService;
import trep.got.advancement.ModAdvancementType;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.day.DailyTaskContext;
import trep.got.day.DailyTaskRegistry;
import trep.got.economy.GotEconomy;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.house.PlayerHouseDefinition;
import trep.got.house.PlayerHouseNameValidator;
import trep.got.house.PlayerHouseService;
import trep.got.item.QuestDeliveryItemData;
import trep.got.item.SealedLetterItem;
import trep.got.item.ShipmentItem;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationExperienceService;
import trep.got.kingdom.ReputationPledgeOption;
import trep.got.kingdom.ReputationProgressionService;
import trep.got.kingdom.ReputationTitle;
import trep.got.network.QuestJournalStatePayload;
import trep.got.quest.BanditCampQuestService;
import trep.got.quest.PlayerQuestHolder;
import trep.got.quest.PlayerQuestProfile;
import trep.got.quest.PledgeQuestLogic;
import trep.got.quest.PledgeQuestService;
import trep.got.quest.QuestActionType;
import trep.got.quest.QuestDebugLogBuffer;
import trep.got.quest.QuestInstance;
import trep.got.quest.QuestNegotiationOutcome;
import trep.got.quest.QuestNegotiationService;
import trep.got.quest.QuestTemplate;
import trep.got.quest.QuestTemplateRegistry;
import trep.got.quest.QuestWorldState;
import trep.got.quest.RangerExploreQuestLogic;
import trep.got.quest.ValyrianDragonGiftService;
import trep.got.quest.ValyrianRumorQuestLogic;
import trep.got.quest.WildlingRaiderCampQuestService;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacters;
import trep.got.registry.GotGameRules;
import trep.got.registry.GotNpcRelations;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeType;
import trep.got.skill.SkillExperienceService;

public final class GotQuests {
    public static final int MAX_ACTIVE_QUESTS = 3;
    public static final int NPC_COMPLETION_COOLDOWN_WINDOWS = 1;
    private static final long LORD_LETTER_ACCEPT_COOLDOWN_TICKS = 6000L;
    private static final String QUEST_GIVER_NPC_ID_KEY = "quest_giver_npc_id";

    private GotQuests() {
    }

    public static void initialize() {
        QuestTemplateRegistry.initialize();
        BanditCampQuestService.initialize();
        WildlingRaiderCampQuestService.initialize();
        ValyrianDragonGiftService.initialize();
        ServerPlayerEvents.COPY_FROM.register(GotQuests::copyProfile);
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(GotQuests::onKilledEntity);
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> QuestDebugLogBuffer.flushNow());
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> GotQuests.sendQuestState(handler.field_14140, sender));
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (class_3222 player : server.method_3760().method_14571()) {
                PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
                boolean expired = GotQuests.cleanupExpired(player, profile);
                boolean cleanedItems = GotQuests.cleanupShipmentItems(player, profile);
                if (profile.activeQuestCount() > 0) {
                    RangerExploreQuestLogic.tickPlayer(player);
                }
                if (!expired && !cleanedItems) continue;
                GotQuests.sendQuestState(player);
            }
        });
        DailyTaskRegistry.register(class_2960.method_60655((String)"agob", (String)"quests"), GotQuests::runDailyQuestTask);
    }

    private static void runDailyQuestTask(class_3218 world, DailyTaskContext context) {
        boolean rotationChanged = QuestWorldState.get(world).tick(world.method_8510(), world.method_8532(), GotGameRules.affectedByTimeSkip(world));
        if (!rotationChanged) {
            return;
        }
        for (class_3222 player : world.method_8503().method_3760().method_14571()) {
            GotQuests.sendQuestState(player);
        }
    }

    private static void onKilledEntity(class_3218 world, class_1297 attacker, class_1297 killedEntity) {
        if (attacker instanceof class_3222) {
            class_3222 player = (class_3222)attacker;
            RangerExploreQuestLogic.onKilledEntity(player, killedEntity);
        }
    }

    public static void sendQuestState(class_3222 player) {
        GotQuests.clearRemovedPledgeQuests(player);
        ServerPlayNetworking.send((class_3222)player, (class_8710)GotQuests.createPayload(player));
    }

    public static void resetForPledgeRestart(class_3222 player) {
        if (player == null) {
            return;
        }
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        List<QuestInstance> lockedQuests = profile.getActiveQuests().stream().filter(quest -> "valyrian_rumor".equals(quest.templateId())).toList();
        profile.clear();
        for (QuestInstance quest2 : lockedQuests) {
            profile.addQuest(quest2);
        }
        GotQuests.sendQuestState(player);
    }

    public static boolean hasQuestOffer(AbstractKingdomNpcEntity npc, long worldTime, PlayerQuestProfile profile) {
        if (npc == null) {
            return false;
        }
        return GotQuests.previewQuest(UUID.randomUUID(), ReputationTitle.PEASANT, npc, worldTime, profile) != null;
    }

    public static void handleQuestAction(class_3222 player, int targetEntityId, String actionId) {
        QuestInstance activeQuest;
        QuestActionType actionType = QuestActionType.byId(actionId);
        class_1297 class_12972 = player.method_37908().method_8469(targetEntityId);
        if (!(class_12972 instanceof AbstractKingdomNpcEntity)) {
            QuestDebugLogBuffer.record("quest_accept", "ignored:target_not_npc", "player=" + player.method_5477().getString() + ",target_entity_id=" + targetEntityId + ",action=" + (actionId == null ? "<null>" : actionId));
            return;
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)class_12972;
        QuestDebugLogBuffer.record("quest_accept", "received", "player=" + player.method_5477().getString() + ",npc=" + npc.method_5476().getString() + ",npc_uuid=" + npc.method_5845() + ",npc_role=" + npc.getNpcRole().name() + ",npc_castle=" + npc.got$getCastle() + ",action=" + (actionId == null ? "<null>" : actionId));
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        if (GotQuests.cleanupExpired(player)) {
            GotQuests.cleanupShipmentItems(player, profile);
            GotQuests.sendQuestState(player);
        }
        if (PledgeQuestService.clearActivePledgeQuests(profile)) {
            GotQuests.sendQuestState(player);
        }
        if (actionType == QuestActionType.REVOKE_PLEDGE && npc instanceof AbstractKingdomLordNpcEntity) {
            AbstractKingdomLordNpcEntity lord = (AbstractKingdomLordNpcEntity)npc;
            if (PledgeQuestService.revokePledge(player, lord)) {
                GotQuests.sendQuestState(player);
            }
            return;
        }
        if (ValyrianRumorQuestLogic.handleAction(player, npc, actionType, profile)) {
            GotQuests.sendQuestState(player);
            return;
        }
        if (npc instanceof AbstractKingdomLordNpcEntity) {
            AbstractKingdomLordNpcEntity lord = (AbstractKingdomLordNpcEntity)npc;
            if (GotQuests.isPledgeAction(actionId, actionType)) {
                if (GotQuests.applyPledgeAction(player, lord, actionId, actionType)) {
                    GotQuests.sendQuestState(player);
                }
                return;
            }
        }
        if ((activeQuest = profile.findCompletableForNpc(npc, (class_1657)player)) != null) {
            if ("main_pledge".equals(activeQuest.templateId()) || actionType == QuestActionType.PLEDGE_COMPLETE_LORD_SIGIL) {
                return;
            }
            if (actionType != QuestActionType.DELIVER || !GotQuests.canCompleteQuest(player, activeQuest)) {
                return;
            }
            GotQuests.completeQuest(player, profile, activeQuest);
            GotQuests.sendQuestState(player);
            return;
        }
        if (actionType == QuestActionType.NEGOTIATE) {
            GotQuests.negotiateQuest(player, profile, npc);
            GotQuests.sendQuestState(player);
            return;
        }
        if (actionType != QuestActionType.ACCEPT) {
            return;
        }
        if (profile.activeQuestCount() >= 3) {
            QuestDebugLogBuffer.record("quest_accept", "rejected:max_active_quests", "player=" + player.method_5477().getString() + ",npc_uuid=" + npc.method_5845() + ",active_count=" + profile.activeQuestCount());
            player.method_7353((class_2561)class_2561.method_43470((String)"Cannot take on more quests!").method_27692(class_124.field_1061), true);
            return;
        }
        QuestInstance preview = GotQuests.previewQuest(player.method_5667(), ((PlayerReputationHolder)player).got$getReputationProfile().getTitle(), npc, player.method_37908().method_8510(), profile);
        if (preview == null) {
            QuestDebugLogBuffer.record("quest_accept", "rejected:preview_null", "player=" + player.method_5477().getString() + ",npc=" + npc.method_5476().getString() + ",npc_uuid=" + npc.method_5845() + ",npc_role=" + npc.getNpcRole().name() + ",npc_castle=" + npc.got$getCastle() + ",world_time=" + player.method_37908().method_8510() + ",offer_cycle=" + QuestWorldState.get(player.method_51469()).getOfferCycle());
            return;
        }
        if ("nearby_bandit_camp_destroy".equals(preview.templateId()) && (preview = BanditCampQuestService.activateQuest(player, npc, preview)) == null) {
            return;
        }
        if ("destroy_wildling_raider_camp".equals(preview.templateId()) && (preview = WildlingRaiderCampQuestService.activateQuest(player, npc, preview)) == null) {
            return;
        }
        profile.addQuest(preview);
        if ("lord_letter_delivery".equals(preview.templateId())) {
            profile.setTemplateNpcCooldown(npc.method_5667(), preview.templateId(), player.method_37908().method_8510() + 6000L);
        }
        GotQuests.giveQuestItem(player, preview);
        QuestDebugLogBuffer.record("quest_accept", "accepted", "player=" + player.method_5477().getString() + ",npc=" + npc.method_5476().getString() + ",npc_uuid=" + npc.method_5845() + ",template=" + preview.templateId() + ",quest_id=" + preview.id() + ",target_castle=" + preview.targetCastleId() + ",target_role=" + preview.targetRoleId());
        ModAdvancementService.send(player, ModAdvancementType.QUEST_ACCEPTED, "Quest Accepted", GotQuests.questName(preview), class_1802.field_8529);
        GotQuests.sendQuestState(player);
    }

    private static boolean isPledgeAction(String actionId, QuestActionType actionType) {
        return PledgeQuestLogic.isActionId(actionId) || actionType == QuestActionType.PLEDGE_KNIGHT || actionType == QuestActionType.PLEDGE_HEDGE_KNIGHT || actionType == QuestActionType.PLEDGE_PRIEST;
    }

    private static boolean applyPledgeAction(class_3222 player, AbstractKingdomLordNpcEntity lord, String actionId, QuestActionType actionType) {
        if (PledgeQuestLogic.isActionId(actionId)) {
            String optionId = PledgeQuestLogic.optionIdFromActionId(actionId);
            PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
            CastleInstance castle = CastleWorldState.get(player.method_51469()).get(lord.got$getCastle());
            String targetKingdomId = castle == null ? "" : castle.getOwnerKingdomId();
            for (ReputationPledgeOption option : ReputationProgressionService.sharedPledgeOptions(character.getSelectedKingdomId(), targetKingdomId, ((PlayerReputationHolder)player).got$getReputationProfile())) {
                if (!option.id().equals(optionId)) continue;
                return PledgeQuestService.pledge(player, lord, PledgeQuestLogic.PledgePath.fromOption(option));
            }
            return false;
        }
        return switch (actionType) {
            case QuestActionType.PLEDGE_KNIGHT -> PledgeQuestService.pledge(player, lord, PledgeQuestLogic.PledgePath.KNIGHT);
            case QuestActionType.PLEDGE_HEDGE_KNIGHT -> PledgeQuestService.pledge(player, lord, PledgeQuestLogic.PledgePath.HEDGE_KNIGHT);
            case QuestActionType.PLEDGE_PRIEST -> PledgeQuestService.pledge(player, lord, PledgeQuestLogic.PledgePath.PRIEST);
            default -> false;
        };
    }

    public static void handleCreatePlayerHouseRequest(class_3222 player, int targetEntityId, String requestedHouseName, int[] sigilColors, boolean completePledge) {
        QuestInstance activeQuest;
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        PlayerHouseDefinition existingHouse = PlayerHouseService.getForOwner(player.method_51469(), player.method_5667());
        boolean editingExistingHouse = existingHouse != null;
        QuestInstance questInstance = activeQuest = completePledge ? PledgeQuestService.findActivePledgeQuest(profile) : null;
        if (completePledge && (activeQuest == null || !GotQuests.canCompleteQuest(player, activeQuest))) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Your pledge is not ready to be completed yet.").method_27692(class_124.field_1061), true);
            return;
        }
        String houseName = PlayerHouseNameValidator.sanitize(requestedHouseName);
        if (!PlayerHouseNameValidator.isValid(houseName)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"House names must be 1-15 letters and spaces only.").method_27692(class_124.field_1061), true);
            return;
        }
        if (sigilColors == null || sigilColors.length == 0 || sigilColors.length > 2976) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Invalid sigil data.").method_27692(class_124.field_1061), true);
            return;
        }
        String pledgedKingdomId = character.getSelectedKingdomId();
        String pledgedHouseId = character.getSelectedOptionId();
        String kingdomId = pledgedKingdomId;
        String lordHouseId = pledgedHouseId;
        if (editingExistingHouse) {
            if (!player.method_5845().equals(existingHouse.ownerUuid())) {
                player.method_7353((class_2561)class_2561.method_43470((String)"You can only edit your own house banner.").method_27692(class_124.field_1061), true);
                return;
            }
            if (!completePledge) {
                kingdomId = existingHouse.kingdomId();
                lordHouseId = existingHouse.lordHouseId();
            }
        }
        if (completePledge) {
            GotQuests.completeQuest(player, profile, activeQuest);
        }
        PlayerHouseDefinition definition = PlayerHouseService.createOrReplace(player, kingdomId, lordHouseId, houseName, sigilColors);
        character.setSelectedOptionId(definition.houseId());
        PlayerHouseService.syncTo(player);
        GotCastles.updatePlayerOwnedHouseId(player.method_51469(), player.method_5667(), definition.houseId());
        GotCastles.updateDynastyOwnedCastlesForPlayer(player.method_51469(), player);
        GotCharacters.sendCharacterSelectionState(player);
        PlayerHouseService.broadcast(player.method_5682());
        player.method_7353((class_2561)class_2561.method_43470((String)("House " + definition.displayName() + " has been founded.")).method_27692(class_124.field_1065), true);
        GotQuests.sendQuestState(player);
    }

    private static void completeQuest(class_3222 player, PlayerQuestProfile profile, QuestInstance quest) {
        SkillAttributeType type;
        if ("nearby_bandit_camp_destroy".equals(quest.templateId())) {
            BanditCampQuestService.onQuestCompleted(player, quest);
        }
        if ("destroy_wildling_raider_camp".equals(quest.templateId())) {
            WildlingRaiderCampQuestService.onQuestCompleted(player, quest);
        }
        GotQuests.removeRequiredItems(player.method_31548(), quest);
        if (quest.reputationExperience() > 0) {
            ReputationExperienceService.addExperience(player, quest.reputationExperience());
        }
        if (quest.goldIngots() > 0) {
            GotEconomy.giveLegacyGoldIngots(player, quest.goldIngots());
        }
        if (quest.ironIngots() > 0) {
            GotQuests.giveStack(player, new class_1799((class_1935)class_1802.field_8620, quest.ironIngots()));
        }
        if (quest.skillPoints() > 0 && !quest.skillTypeId().isBlank() && (type = SkillAttributeType.byId(quest.skillTypeId())) != null && SkillExperienceService.addPoints(player, type, quest.skillPoints()) > 0) {
            GotCharacters.sendCharacterSelectionState(player);
        }
        if (quest.bonusSkillPoints() > 0 && !quest.bonusSkillTypeId().isBlank() && (type = SkillAttributeType.byId(quest.bonusSkillTypeId())) != null && SkillExperienceService.addPoints(player, type, quest.bonusSkillPoints()) > 0) {
            GotCharacters.sendCharacterSelectionState(player);
        }
        profile.removeQuest(quest.id());
        profile.markOfferCompleted(quest.offerKey());
        profile.setNpcCooldown(quest.giverNpcUuid(), GotQuests.completedQuestCooldownEndWindow(player.method_37908().method_8510()));
        GotNpcRelations.addRelation(player, GotQuests.questGiverNpcId(quest), 1);
        GotQuests.syncQuestGiverRelation(player, quest);
        ModAdvancementService.send(player, ModAdvancementType.QUEST_COMPLETED, "Quest Completed", GotQuests.questName(quest), class_1802.field_8674);
    }

    public static boolean failQuest(class_3222 player, String questId, String failureMessage) {
        if (player == null || questId == null || questId.isBlank()) {
            return false;
        }
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        QuestInstance quest = profile.findById(questId);
        if (quest == null) {
            return false;
        }
        if ("nearby_bandit_camp_destroy".equals(quest.templateId())) {
            BanditCampQuestService.onQuestFailed(player, quest);
        }
        if ("destroy_wildling_raider_camp".equals(quest.templateId())) {
            WildlingRaiderCampQuestService.onQuestFailed(player, quest);
        }
        if ("valyrian_rumor".equals(quest.templateId())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This quest cannot be cancelled.").method_27692(class_124.field_1061), true);
            return false;
        }
        profile.removeQuest(quest.id());
        profile.setNpcCooldown(quest.giverNpcUuid(), GotQuests.completedQuestCooldownEndWindow(player.method_37908().method_8510()));
        GotNpcRelations.addRelation(player, GotQuests.questGiverNpcId(quest), -1);
        GotQuests.syncQuestGiverRelation(player, quest);
        GotQuests.cleanupShipmentItems(player, profile);
        player.method_7353((class_2561)class_2561.method_43470((String)((failureMessage == null || failureMessage.isBlank() ? "Quest failed" : failureMessage) + ": " + GotQuests.questName(quest))).method_27692(class_124.field_1061), true);
        GotQuests.sendQuestState(player);
        return true;
    }

    private static void negotiateQuest(class_3222 player, PlayerQuestProfile profile, AbstractKingdomNpcEntity npc) {
        QuestInstance preview = GotQuests.previewQuest(player.method_5667(), ((PlayerReputationHolder)player).got$getReputationProfile().getTitle(), npc, player.method_37908().method_8510(), profile);
        if (preview == null || profile.hasNegotiatedOffer(preview.offerKey())) {
            return;
        }
        int charismaPoints = ((PlayerSkillHolder)player).got$getSkillProfile().get(SkillAttributeType.CHARISMA).getPoints();
        if (charismaPoints < 50) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You need 50 Charisma to negotiate quest rewards.").method_27692(class_124.field_1061), true);
            QuestDebugLogBuffer.record("quest_negotiate", "blocked:insufficient_charisma", "player=" + player.method_5477().getString() + ",npc=" + npc.method_5476().getString() + ",npc_uuid=" + npc.method_5845() + ",charisma_points=" + charismaPoints);
            return;
        }
        int successChance = QuestNegotiationService.successChance(charismaPoints);
        QuestNegotiationOutcome outcome = player.method_59922().method_43048(100) < successChance ? QuestNegotiationOutcome.SUCCESS : QuestNegotiationOutcome.FAILURE;
        profile.setNegotiationOutcome(preview.offerKey(), outcome);
        QuestDebugLogBuffer.record("quest_negotiate", "resolved:" + outcome.getId(), "player=" + player.method_5477().getString() + ",npc=" + npc.method_5476().getString() + ",npc_uuid=" + npc.method_5845() + ",charisma_points=" + charismaPoints + ",success_chance=" + successChance + ",offer_key=" + preview.offerKey());
    }

    private static void sendQuestState(class_3222 player, PacketSender sender) {
        GotQuests.clearRemovedPledgeQuests(player);
        sender.sendPacket((class_8710)GotQuests.createPayload(player));
    }

    private static boolean clearRemovedPledgeQuests(class_3222 player) {
        return player != null && PledgeQuestService.clearActivePledgeQuests(((PlayerQuestHolder)player).got$getQuestProfile());
    }

    private static String objectiveActionId(QuestInstance quest) {
        if (quest == null) {
            return "";
        }
        if ("main_pledge".equals(quest.templateId()) && PledgeQuestLogic.requiredMode(quest) != null) {
            return PledgeQuestLogic.requiredMode(quest).getId();
        }
        if ("valyrian_rumor".equals(quest.templateId())) {
            return ValyrianRumorQuestLogic.stage(quest);
        }
        if ("nights_watch_ranger_explore".equals(quest.templateId())) {
            return RangerExploreQuestLogic.stage(quest);
        }
        return "";
    }

    private static QuestJournalStatePayload createPayload(class_3222 player) {
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        List<QuestJournalStatePayload.Entry> entries = profile.getActiveQuests().stream().map(quest -> new QuestJournalStatePayload.Entry(quest.id(), quest.templateId(), quest.giverNpcUuid().toString(), quest.giverNpcName(), quest.itemId(), quest.requiredCount(), quest.acceptedAtTick(), quest.dueAtTick(), quest.reputationExperience(), quest.goldIngots(), quest.ironIngots(), quest.skillTypeId(), quest.skillPoints(), quest.bonusSkillTypeId(), quest.bonusSkillPoints(), quest.targetRoleId(), quest.targetCastleId(), quest.targetCastleName(), quest.targetNpcName(), quest.displayItemId(), "main_pledge".equals(quest.templateId()), GotQuests.objectiveActionId(quest), "nearby_bandit_camp_destroy".equals(quest.templateId()) && BanditCampQuestService.canComplete(quest) || "destroy_wildling_raider_camp".equals(quest.templateId()) && WildlingRaiderCampQuestService.canComplete(quest) || "nights_watch_ranger_explore".equals(quest.templateId()) && RangerExploreQuestLogic.canComplete(quest) || "main_pledge".equals(quest.templateId()) && PledgeQuestService.canComplete(quest), GotQuests.hasQuestLocation(quest), GotQuests.questLocationX(quest), GotQuests.questLocationY(quest), GotQuests.questLocationZ(quest))).toList();
        List<String> completedOfferKeys = profile.getCompletedOfferKeys().stream().toList();
        List<QuestJournalStatePayload.CooldownEntry> npcCooldowns = profile.getNpcCooldownEndWindows().entrySet().stream().map(entry -> new QuestJournalStatePayload.CooldownEntry(((UUID)entry.getKey()).toString(), (Long)entry.getValue())).toList();
        List<QuestJournalStatePayload.TemplateCooldownEntry> templateNpcCooldowns = profile.getTemplateNpcCooldownEndTicks().entrySet().stream().map(entry -> {
            int separator = ((String)entry.getKey()).indexOf(124);
            String npcUuid = separator < 0 ? (String)entry.getKey() : ((String)entry.getKey()).substring(0, separator);
            String templateId = separator < 0 ? "" : ((String)entry.getKey()).substring(separator + 1);
            return new QuestJournalStatePayload.TemplateCooldownEntry(npcUuid, templateId, (Long)entry.getValue());
        }).toList();
        List<QuestJournalStatePayload.NegotiationEntry> negotiations = profile.getNegotiationOutcomes().entrySet().stream().map(entry -> new QuestJournalStatePayload.NegotiationEntry((String)entry.getKey(), ((QuestNegotiationOutcome)((Object)((Object)entry.getValue()))).getId())).toList();
        long offerCycle = QuestWorldState.get(player.method_51469()).getOfferCycle();
        return new QuestJournalStatePayload(offerCycle, entries, completedOfferKeys, npcCooldowns, templateNpcCooldowns, negotiations);
    }

    private static boolean cleanupExpired(class_3222 player) {
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        return GotQuests.cleanupExpired(player, profile);
    }

    private static boolean cleanupExpired(class_3222 player, PlayerQuestProfile profile) {
        boolean removedPledgeQuests = PledgeQuestService.clearActivePledgeQuests(profile);
        long worldTime = player.method_37908().method_8510();
        long questTime = QuestWorldState.get(player.method_51469()).getTrackedDayTime();
        ArrayList<QuestInstance> expiredQuests = null;
        for (QuestInstance quest : profile.getActiveQuests()) {
            if (!quest.isExpired(questTime)) continue;
            if (expiredQuests == null) {
                expiredQuests = new ArrayList<QuestInstance>();
            }
            expiredQuests.add(quest);
        }
        if (expiredQuests == null) {
            return profile.cleanup(questTime, worldTime) || removedPledgeQuests;
        }
        for (QuestInstance quest : expiredQuests) {
            if ("valyrian_rumor".equals(quest.templateId())) continue;
            if ("nearby_bandit_camp_destroy".equals(quest.templateId())) {
                BanditCampQuestService.onQuestFailed(player, quest);
            }
            if ("destroy_wildling_raider_camp".equals(quest.templateId())) {
                WildlingRaiderCampQuestService.onQuestFailed(player, quest);
            }
            profile.removeQuest(quest.id());
            player.method_7353((class_2561)class_2561.method_43470((String)("Quest failed: " + GotQuests.questName(quest))).method_27692(class_124.field_1061), true);
        }
        profile.cleanup(questTime, worldTime);
        return true;
    }

    private static void copyProfile(class_3222 oldPlayer, class_3222 newPlayer, boolean alive) {
        ((PlayerQuestHolder)newPlayer).got$getQuestProfile().copyFrom(((PlayerQuestHolder)oldPlayer).got$getQuestProfile());
    }

    private static void removeRequiredItems(class_1661 inventory, QuestInstance quest) {
        if (quest == null || GotQuests.requiredCount(quest) <= 0) {
            return;
        }
        int remaining = GotQuests.requiredCount(quest);
        for (int slot = 0; slot < inventory.method_5439() && remaining > 0; ++slot) {
            class_1799 stack = inventory.method_5438(slot);
            if (!GotQuests.matchesRequiredStack(stack, quest)) continue;
            int removed = Math.min(remaining, stack.method_7947());
            stack.method_7934(removed);
            remaining -= removed;
        }
    }

    private static int requiredCount(QuestInstance quest) {
        return quest == null ? 0 : quest.requiredCount();
    }

    private static boolean matchesRequiredStack(class_1799 stack, QuestInstance quest) {
        if (quest.isShipmentQuest()) {
            return QuestDeliveryItemData.matchesQuest(stack, quest.id());
        }
        class_1792 item = quest.requiredItem();
        return item != null && stack.method_31574(item);
    }

    private static void giveQuestItem(class_3222 player, QuestInstance quest) {
        if (quest == null || "main_pledge".equals(quest.templateId()) || !quest.isShipmentQuest()) {
            return;
        }
        class_1799 stack = quest.itemId().equals("agob:sealed_letter") ? SealedLetterItem.createStack(quest.id(), player.method_5667().toString(), quest.targetCastleName(), quest.targetNpcName()) : ShipmentItem.createStack(quest.id(), player.method_5667().toString(), quest.displayItemId(), quest.targetCastleId(), quest.targetCastleName());
        GotQuests.giveStack(player, stack);
    }

    private static boolean cleanupShipmentItems(class_3222 player, PlayerQuestProfile profile) {
        boolean changed = GotQuests.scrubInventory(player.method_31548(), profile);
        class_1703 handler = player.field_7512;
        if (handler != null && handler != player.field_7498) {
            for (class_1735 slot : handler.field_7761) {
                class_1799 stack = slot.method_7677();
                if (stack.method_7960() || !QuestDeliveryItemData.isQuestDeliveryItem(stack) || QuestDeliveryItemData.isValidForQuest(stack, profile)) continue;
                slot.method_53512(class_1799.field_8037);
                changed = true;
            }
            class_1799 cursorStack = handler.method_34255();
            if (!cursorStack.method_7960() && QuestDeliveryItemData.isQuestDeliveryItem(cursorStack) && !QuestDeliveryItemData.isValidForQuest(cursorStack, profile)) {
                handler.method_34254(class_1799.field_8037);
                changed = true;
            }
            if (changed) {
                handler.method_7623();
            }
        }
        return changed;
    }

    private static boolean scrubInventory(class_1661 inventory, PlayerQuestProfile profile) {
        boolean changed = false;
        for (int slot = 0; slot < inventory.method_5439(); ++slot) {
            class_1799 stack = inventory.method_5438(slot);
            if (stack.method_7960() || !QuestDeliveryItemData.isQuestDeliveryItem(stack) || QuestDeliveryItemData.isValidForQuest(stack, profile)) continue;
            inventory.method_5447(slot, class_1799.field_8037);
            changed = true;
        }
        return changed;
    }

    private static void giveStack(class_3222 player, class_1799 stack) {
        if (!player.method_31548().method_7394(stack)) {
            player.method_7328(stack, false);
        }
    }

    private static String questName(QuestInstance quest) {
        if (quest != null && "main_pledge".equals(quest.templateId())) {
            return "Pledge to a Banner";
        }
        QuestTemplate template = QuestTemplateRegistry.get(quest.templateId());
        return template == null ? quest.templateId() : template.displayName();
    }

    private static boolean canCompleteQuest(class_3222 player, QuestInstance quest) {
        if ("nearby_bandit_camp_destroy".equals(quest.templateId())) {
            return BanditCampQuestService.canComplete(quest);
        }
        if ("destroy_wildling_raider_camp".equals(quest.templateId())) {
            return WildlingRaiderCampQuestService.canComplete(quest);
        }
        if ("nights_watch_ranger_explore".equals(quest.templateId())) {
            return RangerExploreQuestLogic.canComplete(quest);
        }
        if ("main_pledge".equals(quest.templateId())) {
            return PledgeQuestService.canComplete(quest);
        }
        if ("valyrian_rumor".equals(quest.templateId())) {
            return ValyrianRumorQuestLogic.canComplete(quest);
        }
        return quest.canComplete((class_1657)player);
    }

    private static boolean hasQuestLocation(QuestInstance quest) {
        return quest != null && quest.metadata().method_10545("camp_origin_x") && quest.metadata().method_10545("camp_origin_y") && quest.metadata().method_10545("camp_origin_z");
    }

    private static int questLocationX(QuestInstance quest) {
        return GotQuests.hasQuestLocation(quest) ? quest.metadata().method_10550("camp_origin_x") : 0;
    }

    private static int questLocationY(QuestInstance quest) {
        return GotQuests.hasQuestLocation(quest) ? quest.metadata().method_10550("camp_origin_y") : 0;
    }

    private static int questLocationZ(QuestInstance quest) {
        return GotQuests.hasQuestLocation(quest) ? quest.metadata().method_10550("camp_origin_z") : 0;
    }

    public static QuestInstance previewQuest(UUID playerUuid, ReputationTitle reputationTitle, AbstractKingdomNpcEntity npc, long worldTime, PlayerQuestProfile profile) {
        QuestInstance valyrianPreview;
        long questTime;
        if (npc == null || profile == null) {
            return null;
        }
        class_1937 class_19372 = npc.method_37908();
        if (class_19372 instanceof class_3218) {
            class_3218 serverWorld = (class_3218)class_19372;
            v0 = QuestWorldState.get(serverWorld).getTrackedDayTime();
        } else {
            v0 = questTime = worldTime;
        }
        if (profile.isTemplateNpcOnCooldown(npc.method_5667(), "lord_letter_delivery", worldTime)) {
            QuestDebugLogBuffer.record("quest_preview", "blocked:template_npc_cooldown", "npc=" + npc.method_5476().getString() + ",npc_uuid=" + npc.method_5845() + ",npc_role=" + npc.getNpcRole().name() + ",npc_castle=" + npc.got$getCastle() + ",template_id=lord_letter_delivery,world_time=" + worldTime);
            return null;
        }
        if (profile.findByNpc(npc.method_5667()) != null || profile.isNpcOnCooldown(npc.method_5667(), worldTime)) {
            QuestDebugLogBuffer.record("quest_preview", "blocked:" + (profile.findByNpc(npc.method_5667()) != null ? "npc_already_has_active_quest" : "npc_on_cooldown"), "npc=" + npc.method_5476().getString() + ",npc_uuid=" + npc.method_5845() + ",npc_role=" + npc.getNpcRole().name() + ",npc_castle=" + npc.got$getCastle() + ",world_time=" + worldTime);
            return null;
        }
        long offerCycle = QuestWorldState.get((class_3218)npc.method_37908()).getOfferCycle();
        QuestInstance questInstance = valyrianPreview = ValyrianRumorQuestLogic.hasCompletedQuest((class_3218)npc.method_37908(), playerUuid) ? null : GotQuests.withQuestGiverNpcId(ValyrianRumorQuestLogic.createQuest(playerUuid, npc, questTime, offerCycle, reputationTitle), npc);
        if (valyrianPreview != null && !profile.hasCompletedOffer(valyrianPreview.offerKey())) {
            return valyrianPreview;
        }
        QuestInstance preview = QuestTemplateRegistry.previewForPlayer(playerUuid, npc, offerCycle, questTime, reputationTitle);
        if ((preview = GotQuests.withQuestGiverNpcId(preview, npc)) == null || profile.hasCompletedOffer(preview.offerKey())) {
            QuestDebugLogBuffer.record("quest_preview", "blocked:" + (preview == null ? "template_preview_null" : "offer_already_completed"), "npc=" + npc.method_5476().getString() + ",npc_uuid=" + npc.method_5845() + ",npc_role=" + npc.getNpcRole().name() + ",npc_castle=" + npc.got$getCastle() + ",offer_cycle=" + offerCycle + ",world_time=" + worldTime);
            return null;
        }
        QuestNegotiationOutcome outcome = profile.getNegotiationOutcome(preview.offerKey());
        return outcome == null ? preview : QuestNegotiationService.applyOutcome(preview, outcome);
    }

    private static long completedQuestCooldownEndWindow(long worldTime) {
        return worldTime / QuestTemplateRegistry.offerWindowTicks() + 1L;
    }

    private static QuestInstance withQuestGiverNpcId(QuestInstance quest, AbstractKingdomNpcEntity npc) {
        if (quest == null || npc == null) {
            return quest;
        }
        String npcId = npc.getPersistentNpcId();
        if (npcId.isBlank()) {
            return quest;
        }
        class_2487 metadata = quest.metadata().method_10553();
        metadata.method_10582(QUEST_GIVER_NPC_ID_KEY, npcId);
        return quest.withMetadata(metadata);
    }

    private static String questGiverNpcId(QuestInstance quest) {
        if (quest == null || !quest.metadata().method_10545(QUEST_GIVER_NPC_ID_KEY)) {
            return "";
        }
        return quest.metadata().method_10558(QUEST_GIVER_NPC_ID_KEY);
    }

    private static void syncQuestGiverRelation(class_3222 player, QuestInstance quest) {
        class_1937 class_19372;
        if (player == null || quest == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        class_1297 entity = world.method_14190(quest.giverNpcUuid());
        if (entity instanceof AbstractKingdomNpcEntity) {
            AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)entity;
            GotNpcRelations.sendDialogueRelation(player, npc);
        }
    }
}

