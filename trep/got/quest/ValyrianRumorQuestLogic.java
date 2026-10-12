/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 */
package trep.got.quest;

import java.util.UUID;
import net.minecraft.class_124;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import trep.got.advancement.ModAdvancementService;
import trep.got.advancement.ModAdvancementType;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.entity.dragon.DragonEggColor;
import trep.got.item.DragonEggItem;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationTitle;
import trep.got.quest.PlayerQuestHolder;
import trep.got.quest.PlayerQuestProfile;
import trep.got.quest.QuestActionType;
import trep.got.quest.QuestInstance;
import trep.got.quest.ValyrianDragonGiftWorldState;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotCastles;
import trep.got.registry.GotItems;
import trep.got.registry.GotNpcRelations;
import trep.got.registry.GotRelations;
import trep.got.relations.PlayerKingdomRelationsHolder;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeType;

public final class ValyrianRumorQuestLogic {
    public static final String TEMPLATE_ID = "valyrian_rumor";
    public static final String STAGE_CONFIRM_IDENTITY = "confirm_identity";
    public static final String STAGE_RETURN_TO_GIVER = "return_to_giver";
    public static final String TARGARYEN_KINGDOM_ID = "agob:targaryen";
    public static final String TARGARYEN_HOUSE_ID = "targaryen_targaryen";
    public static final String PENTOS_KINGDOM_ID = "agob:pentos";
    private static final String TARGET_TARGARYEN_LORD = "__targaryen_lord__";
    private static final String METADATA_STAGE = "valyrian_stage";
    private static final String METADATA_GIVER_CASTLE = "valyrian_giver_castle";
    private static final String METADATA_GIVER_NPC_ID = "valyrian_giver_npc_id";
    private static final int TARGARYEN_CLAIM_CHARISMA = 35;

    private ValyrianRumorQuestLogic() {
    }

    public static QuestInstance createQuest(UUID playerUuid, AbstractKingdomNpcEntity npc, long worldTime, long offerCycle, ReputationTitle reputationTitle) {
        if (!(npc instanceof AbstractKingdomLordNpcEntity && ValyrianRumorQuestLogic.isPentosCastleLord(npc) && ValyrianRumorQuestLogic.hasReputation(reputationTitle))) {
            return null;
        }
        class_2487 metadata = new class_2487();
        metadata.method_10582(METADATA_STAGE, STAGE_CONFIRM_IDENTITY);
        metadata.method_10582(METADATA_GIVER_CASTLE, npc.got$getCastle());
        metadata.method_10582(METADATA_GIVER_NPC_ID, npc.getPersistentNpcId());
        return new QuestInstance("valyrian_rumor_" + Math.abs((playerUuid.toString() + npc.method_5845() + worldTime).hashCode()), TEMPLATE_ID, npc.method_5667(), npc.method_5476().getString(), ValyrianRumorQuestLogic.completedOfferKey(playerUuid), "agob:dragon_egg", 0, worldTime, Long.MAX_VALUE, 0, 0, 0, "", 0, "", 0, "LORD", TARGET_TARGARYEN_LORD, "a Targaryen castle", "a Targaryen Lord", "agob:dragon_egg", metadata);
    }

    public static boolean handleAction(class_3222 player, AbstractKingdomNpcEntity npc, QuestActionType actionType, PlayerQuestProfile profile) {
        if (player == null || npc == null || actionType == null || profile == null) {
            return false;
        }
        return switch (actionType) {
            case QuestActionType.VALYRIAN_TARGARYEN_CLAIM -> ValyrianRumorQuestLogic.handleTargaryenClaim(player, npc, profile);
            case QuestActionType.ACCEPT -> ValyrianRumorQuestLogic.handleAccept(player, npc, profile);
            case QuestActionType.VALYRIAN_CONFIRM_IDENTITY -> ValyrianRumorQuestLogic.handleConfirmIdentity(player, npc, profile);
            case QuestActionType.VALYRIAN_DELIVER_NEWS -> ValyrianRumorQuestLogic.handleDeliverNews(player, npc, profile);
            case QuestActionType.VALYRIAN_DELIVER_DRAGON -> ValyrianRumorQuestLogic.handleDeliverDragon(player, npc);
            default -> false;
        };
    }

    public static QuestInstance previewQuest(class_3222 player, AbstractKingdomNpcEntity npc, PlayerQuestProfile profile) {
        if (player == null || npc == null || profile == null) {
            return null;
        }
        if (ValyrianRumorQuestLogic.hasCompletedQuest(player) || profile.hasCompletedOffer(ValyrianRumorQuestLogic.completedOfferKey(player.method_5667())) || ValyrianRumorQuestLogic.findActive(profile) != null) {
            return null;
        }
        return ValyrianRumorQuestLogic.createQuest(player.method_5667(), npc, player.method_37908().method_8510(), 0L, ((PlayerReputationHolder)player).got$getReputationProfile().getTitle());
    }

    public static boolean matchesNpc(QuestInstance quest, AbstractKingdomNpcEntity npc) {
        if (!TEMPLATE_ID.equals(quest.templateId()) || npc == null) {
            return false;
        }
        String stage = ValyrianRumorQuestLogic.stage(quest);
        if (STAGE_CONFIRM_IDENTITY.equals(stage)) {
            return ValyrianRumorQuestLogic.isTargaryenCastleLord(npc);
        }
        if (STAGE_RETURN_TO_GIVER.equals(stage)) {
            return quest.giverNpcUuid().equals(npc.method_5667());
        }
        return false;
    }

    public static boolean canComplete(QuestInstance quest) {
        return false;
    }

    public static String stage(QuestInstance quest) {
        if (quest == null || !quest.metadata().method_10545(METADATA_STAGE)) {
            return STAGE_CONFIRM_IDENTITY;
        }
        return quest.metadata().method_10558(METADATA_STAGE);
    }

    public static boolean isOriginTargaryen(PlayerCharacterProfile profile) {
        return profile != null && TARGARYEN_KINGDOM_ID.equals(profile.getOriginalSelectedKingdomId()) && TARGARYEN_HOUSE_ID.equals(profile.getOriginalSelectedOptionId());
    }

    public static String completedOfferKey(UUID playerUuid) {
        return "valyrian_rumor:" + String.valueOf(playerUuid);
    }

    public static boolean hasCompletedQuest(class_3218 world, UUID playerUuid) {
        return world != null && ValyrianDragonGiftWorldState.get(world).hasPlayerCompletedQuest(playerUuid);
    }

    private static boolean hasCompletedQuest(class_3222 player) {
        class_3218 world;
        class_1937 class_19372;
        return player != null && (class_19372 = player.method_37908()) instanceof class_3218 && ValyrianRumorQuestLogic.hasCompletedQuest(world = (class_3218)class_19372, player.method_5667());
    }

    private static boolean handleAccept(class_3222 player, AbstractKingdomNpcEntity npc, PlayerQuestProfile profile) {
        QuestInstance preview = ValyrianRumorQuestLogic.previewQuest(player, npc, profile);
        if (preview == null) {
            return false;
        }
        if (profile.activeQuestCount() >= 3) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Cannot take on more quests!").method_27692(class_124.field_1061), true);
            return true;
        }
        profile.addQuest(preview);
        ModAdvancementService.send(player, ModAdvancementType.QUEST_ACCEPTED, "Quest Accepted", "True Valyrians", class_1802.field_8529);
        return true;
    }

    private static boolean handleTargaryenClaim(class_3222 player, AbstractKingdomNpcEntity npc, PlayerQuestProfile profile) {
        QuestInstance preview = ValyrianRumorQuestLogic.previewQuest(player, npc, profile);
        if (preview == null || !ValyrianRumorQuestLogic.isOriginTargaryen(((PlayerCharacterHolder)player).got$getCharacterProfile())) {
            return true;
        }
        int charisma = ((PlayerSkillHolder)player).got$getSkillProfile().get(SkillAttributeType.CHARISMA).getPoints();
        if (charisma > 35) {
            ValyrianRumorQuestLogic.completeQuest(player, profile, preview, true);
            player.method_7353((class_2561)class_2561.method_43470((String)"The lord believes your claim.").method_27692(class_124.field_1060), true);
            return true;
        }
        if (profile.activeQuestCount() >= 3) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Cannot take on more quests!").method_27692(class_124.field_1061), true);
            return true;
        }
        profile.addQuest(preview);
        player.method_7353((class_2561)class_2561.method_43470((String)"The lord does not believe you. Find a Targaryen lord to confirm it.").method_27692(class_124.field_1061), true);
        ModAdvancementService.send(player, ModAdvancementType.QUEST_ACCEPTED, "Quest Accepted", "True Valyrians", class_1802.field_8529);
        return true;
    }

    private static boolean handleConfirmIdentity(class_3222 player, AbstractKingdomNpcEntity npc, PlayerQuestProfile profile) {
        QuestInstance quest = ValyrianRumorQuestLogic.findActive(profile);
        if (quest == null || !STAGE_CONFIRM_IDENTITY.equals(ValyrianRumorQuestLogic.stage(quest)) || !ValyrianRumorQuestLogic.isTargaryenCastleLord(npc)) {
            return false;
        }
        profile.updateQuest(ValyrianRumorQuestLogic.withStage(quest, STAGE_RETURN_TO_GIVER));
        player.method_7353((class_2561)class_2561.method_43470((String)"Identity confirmed. Return to the Pentoshi lord.").method_27692(class_124.field_1060), true);
        return true;
    }

    private static boolean handleDeliverNews(class_3222 player, AbstractKingdomNpcEntity npc, PlayerQuestProfile profile) {
        QuestInstance quest = ValyrianRumorQuestLogic.findActive(profile);
        if (quest == null || !STAGE_RETURN_TO_GIVER.equals(ValyrianRumorQuestLogic.stage(quest)) || !quest.giverNpcUuid().equals(npc.method_5667())) {
            return false;
        }
        ValyrianRumorQuestLogic.completeQuest(player, profile, quest, true);
        return true;
    }

    private static boolean handleDeliverDragon(class_3222 player, AbstractKingdomNpcEntity npc) {
        class_3218 world;
        block8: {
            block7: {
                class_1937 class_19372 = player.method_37908();
                if (!(class_19372 instanceof class_3218)) break block7;
                world = (class_3218)class_19372;
                if (npc instanceof AbstractKingdomLordNpcEntity && ValyrianRumorQuestLogic.isTargaryenCastleLord(npc)) break block8;
            }
            return false;
        }
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        if (!profile.hasCompletedOffer(ValyrianRumorQuestLogic.completedOfferKey(player.method_5667()))) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You have no dragon delivery to offer.").method_27692(class_124.field_1061), true);
            return true;
        }
        ValyrianDragonGiftWorldState state = ValyrianDragonGiftWorldState.get(world);
        if (state.hasPlayerDelivered(player.method_5667())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You have already delivered a dragon.").method_27692(class_124.field_1061), true);
            return true;
        }
        GotCastles.ensureCastleNpcIdentity(world, npc);
        String lordKey = ValyrianRumorQuestLogic.lordKey(npc);
        if (state.hasLordGift(lordKey)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This lord already has a dragon.").method_27692(class_124.field_1061), true);
            return true;
        }
        if (!ValyrianRumorQuestLogic.hasItem(player, GotBlocks.DRAGON_EGG.method_8389()) || !ValyrianRumorQuestLogic.hasItem(player, GotBlocks.DRAGON_CRADLE.method_8389())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You need a Dragon Egg and Dragon Cradle.").method_27692(class_124.field_1061), true);
            return true;
        }
        ValyrianRumorQuestLogic.removeOne(player, GotBlocks.DRAGON_EGG.method_8389());
        ValyrianRumorQuestLogic.removeOne(player, GotBlocks.DRAGON_CRADLE.method_8389());
        ((PlayerKingdomRelationsHolder)player).got$getKingdomRelationsProfile().setRelation(TARGARYEN_KINGDOM_ID, 100);
        GotRelations.syncToPlayer(player);
        ValyrianRumorQuestLogic.giveTargaryenArmor(player);
        state.recordGift(player.method_5667(), new ValyrianDragonGiftWorldState.GiftRecord(lordKey, npc.got$getCastle(), npc.getPersistentNpcId(), npc.method_5667(), null));
        player.method_7353((class_2561)class_2561.method_43470((String)"Dragon delivered to House Targaryen.").method_27692(class_124.field_1060), true);
        return true;
    }

    private static void completeQuest(class_3222 player, PlayerQuestProfile profile, QuestInstance quest, boolean giveReward) {
        if (giveReward) {
            ValyrianRumorQuestLogic.giveStack(player, DragonEggItem.createStack(GotBlocks.DRAGON_EGG, DragonEggColor.random(player.method_59922())));
            ValyrianRumorQuestLogic.giveStack(player, new class_1799((class_1935)GotBlocks.DRAGON_CRADLE));
        }
        profile.removeQuest(quest.id());
        profile.markOfferCompleted(quest.offerKey());
        class_1937 class_19372 = player.method_37908();
        if (class_19372 instanceof class_3218) {
            class_3218 world = (class_3218)class_19372;
            ValyrianDragonGiftWorldState.get(world).markPlayerCompletedQuest(player.method_5667());
        }
        GotNpcRelations.addRelation(player, quest.metadata().method_10558(METADATA_GIVER_NPC_ID), 1);
        ModAdvancementService.send(player, ModAdvancementType.QUEST_COMPLETED, "Quest Completed", "True Valyrians", class_1802.field_8674);
    }

    private static QuestInstance findActive(PlayerQuestProfile profile) {
        for (QuestInstance quest : profile.getActiveQuests()) {
            if (!TEMPLATE_ID.equals(quest.templateId())) continue;
            return quest;
        }
        return null;
    }

    private static QuestInstance withStage(QuestInstance quest, String stage) {
        class_2487 metadata = quest.metadata().method_10553();
        metadata.method_10582(METADATA_STAGE, stage);
        boolean returning = STAGE_RETURN_TO_GIVER.equals(stage);
        return new QuestInstance(quest.id(), quest.templateId(), quest.giverNpcUuid(), quest.giverNpcName(), quest.offerKey(), quest.itemId(), quest.requiredCount(), quest.acceptedAtTick(), quest.dueAtTick(), quest.reputationExperience(), quest.goldIngots(), quest.ironIngots(), quest.skillTypeId(), quest.skillPoints(), quest.bonusSkillTypeId(), quest.bonusSkillPoints(), returning ? "" : "LORD", returning ? "" : TARGET_TARGARYEN_LORD, returning ? "" : "a Targaryen castle", returning ? "" : "a Targaryen Lord", quest.displayItemId(), metadata);
    }

    private static boolean isPentosCastleLord(AbstractKingdomNpcEntity npc) {
        return ValyrianRumorQuestLogic.isKingdomCastleLord(npc, PENTOS_KINGDOM_ID, "");
    }

    public static boolean isTargaryenCastleLord(AbstractKingdomNpcEntity npc) {
        return ValyrianRumorQuestLogic.isKingdomCastleLord(npc, TARGARYEN_KINGDOM_ID, TARGARYEN_HOUSE_ID);
    }

    private static boolean isKingdomCastleLord(AbstractKingdomNpcEntity npc, String kingdomId, String houseId) {
        class_3218 world;
        block6: {
            block5: {
                if (!(npc instanceof AbstractKingdomLordNpcEntity) || npc.getNpcRole() != KingdomNpcRole.LORD || npc.getKingdomId() == null || !kingdomId.equals(npc.getKingdomId().toString())) {
                    return false;
                }
                class_1937 class_19372 = npc.method_37908();
                if (!(class_19372 instanceof class_3218)) break block5;
                world = (class_3218)class_19372;
                if (!npc.got$getCastle().isBlank()) break block6;
            }
            return false;
        }
        CastleInstance castle = CastleWorldState.get(world).get(npc.got$getCastle());
        if (castle == null || !kingdomId.equals(castle.getOwnerKingdomId())) {
            return false;
        }
        return houseId.isBlank() || houseId.equals(castle.getOwnerHouseId());
    }

    private static boolean hasReputation(ReputationTitle title) {
        return title != null && title.getTier() >= 2;
    }

    private static boolean hasItem(class_3222 player, class_1792 item) {
        for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
            if (!player.method_31548().method_5438(slot).method_31574(item)) continue;
            return true;
        }
        return false;
    }

    private static void removeOne(class_3222 player, class_1792 item) {
        for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
            class_1799 stack = player.method_31548().method_5438(slot);
            if (!stack.method_31574(item)) continue;
            stack.method_7934(1);
            return;
        }
    }

    private static void giveTargaryenArmor(class_3222 player) {
        ValyrianRumorQuestLogic.giveStack(player, new class_1799((class_1935)GotItems.TARGARYEN_HELMET));
        ValyrianRumorQuestLogic.giveStack(player, new class_1799((class_1935)GotItems.TARGARYEN_CHESTPLATE));
        ValyrianRumorQuestLogic.giveStack(player, new class_1799((class_1935)GotItems.TARGARYEN_LEGGINGS));
        ValyrianRumorQuestLogic.giveStack(player, new class_1799((class_1935)GotItems.TARGARYEN_BOOTS));
    }

    private static void giveStack(class_3222 player, class_1799 stack) {
        if (!player.method_31548().method_7394(stack)) {
            player.method_7328(stack, false);
        }
    }

    private static String lordKey(AbstractKingdomNpcEntity npc) {
        String npcId = npc.getPersistentNpcId();
        if (npcId.isBlank()) {
            npcId = npc.method_5845();
        }
        return npc.got$getCastle() + "|" + npcId;
    }
}

