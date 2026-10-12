/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_2487
 *  net.minecraft.class_2960
 *  net.minecraft.class_7923
 */
package trep.got.quest;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.kingdom.ReputationTitle;
import trep.got.quest.BanditCampQuestLogic;
import trep.got.quest.LordLetterQuestLogic;
import trep.got.quest.QuestInstance;
import trep.got.quest.QuestReward;
import trep.got.quest.QuestRewardType;
import trep.got.quest.QuestTemplateRegistry;
import trep.got.quest.RangerExploreQuestLogic;
import trep.got.quest.ShipmentQuestLogic;
import trep.got.quest.ValyrianRumorQuestLogic;
import trep.got.quest.WildlingRaiderCampQuestLogic;
import trep.got.registry.GotDynastyNpcs;
import trep.got.skill.SkillAttributeType;

public record QuestTemplate(String id, String displayName, class_2960 itemId, List<class_2960> itemIds, int minCount, int maxCount, int minDurationDays, int maxDurationDays, Set<KingdomNpcRole> allowedRoles, Set<String> allowedKingdomIds, Set<GotDynastyNpcs.FamilyStage> allowedFamilyStages, List<QuestReward> rewards) {
    public QuestTemplate {
        itemIds = itemIds == null || itemIds.isEmpty() ? List.of(itemId) : List.copyOf(itemIds);
        allowedRoles = Set.copyOf(allowedRoles);
        allowedKingdomIds = Set.copyOf(allowedKingdomIds);
        allowedFamilyStages = Set.copyOf(allowedFamilyStages);
        rewards = List.copyOf(rewards);
    }

    public boolean matches(AbstractKingdomNpcEntity npc) {
        if (npc == null) {
            return false;
        }
        if (this.allowedRoles.isEmpty() ? npc.getNpcRole() == KingdomNpcRole.BLACKSMITH || npc.getNpcRole() == KingdomNpcRole.LORD : !this.allowedRoles.contains((Object)npc.getNpcRole())) {
            return false;
        }
        if (!(this.allowedKingdomIds.isEmpty() || npc.getKingdomId() != null && this.allowedKingdomIds.contains(npc.getKingdomId().toString()))) {
            return false;
        }
        GotDynastyNpcs.FamilyStage familyStage = GotDynastyNpcs.familyStage(npc);
        if (familyStage == GotDynastyNpcs.FamilyStage.STANDARD) {
            return this.allowedFamilyStages.isEmpty() || this.allowedFamilyStages.contains((Object)GotDynastyNpcs.FamilyStage.STANDARD);
        }
        return this.allowedFamilyStages.contains((Object)familyStage);
    }

    public QuestInstance createInstance(UUID playerUuid, AbstractKingdomNpcEntity npc, long worldTime, long offerCycle, ReputationTitle reputationTitle) {
        if ("castle_shipment_delivery".equals(this.id)) {
            return ShipmentQuestLogic.createQuest(playerUuid, npc, worldTime, offerCycle, reputationTitle);
        }
        if ("lord_letter_delivery".equals(this.id)) {
            return LordLetterQuestLogic.createQuest(playerUuid, npc, worldTime, offerCycle, reputationTitle);
        }
        if ("nearby_bandit_camp_destroy".equals(this.id)) {
            return BanditCampQuestLogic.createQuest(playerUuid, npc, worldTime, offerCycle, reputationTitle);
        }
        if ("destroy_wildling_raider_camp".equals(this.id)) {
            return WildlingRaiderCampQuestLogic.createQuest(playerUuid, npc, worldTime, offerCycle, reputationTitle);
        }
        if ("nights_watch_ranger_explore".equals(this.id)) {
            return RangerExploreQuestLogic.createQuest(playerUuid, npc, worldTime, offerCycle, reputationTitle);
        }
        if ("valyrian_rumor".equals(this.id)) {
            return ValyrianRumorQuestLogic.createQuest(playerUuid, npc, worldTime, offerCycle, reputationTitle);
        }
        long seed = QuestTemplateRegistry.computeOfferSeed(playerUuid, npc.method_5667(), offerCycle, this.id);
        class_2960 requestedItemId = this.selectItemId(seed);
        int requiredCount = this.minCount >= this.maxCount ? this.minCount : this.minCount + Math.floorMod((int)seed, this.maxCount - this.minCount + 1);
        long durationDays = this.minDurationDays >= this.maxDurationDays ? (long)this.minDurationDays : (long)(this.minDurationDays + Math.floorMod((int)(seed >>> 7), this.maxDurationDays - this.minDurationDays + 1));
        long dueAtTick = worldTime + durationDays * 24000L;
        int reputationExperience = 0;
        int goldIngots = 0;
        int ironIngots = 0;
        String skillTypeId = "";
        int skillPoints = 0;
        block6: for (int index = 0; index < this.rewards.size(); ++index) {
            QuestReward reward = this.rewards.get(index);
            long rewardSeed = seed + 31L * (long)(index + 1);
            int amount = QuestTemplate.rollRewardAmount(reward, rewardSeed, requiredCount, reputationTitle);
            switch (reward.type()) {
                case REPUTATION_EXPERIENCE: {
                    reputationExperience += amount;
                    continue block6;
                }
                case GOLD_INGOTS: {
                    goldIngots += amount;
                    continue block6;
                }
                case IRON_INGOTS: {
                    ironIngots += amount;
                    continue block6;
                }
                case SKILL_POINTS: {
                    skillTypeId = reward.skillType() == null ? "" : reward.skillType().getId();
                    skillPoints += amount;
                }
            }
        }
        String questId = "quest_" + Math.abs((playerUuid.toString() + npc.method_5845() + worldTime + this.id).hashCode());
        return new QuestInstance(questId, this.id, npc.method_5667(), npc.method_5476().getString(), QuestTemplateRegistry.offerKey(npc.method_5667(), offerCycle), requestedItemId.toString(), requiredCount, worldTime, dueAtTick, reputationExperience, goldIngots, ironIngots, skillTypeId, skillPoints, "", 0, "", "", "", "", requestedItemId.toString(), new class_2487());
    }

    private static int rollRewardAmount(QuestReward reward, long seed, int requiredCount, ReputationTitle reputationTitle) {
        if (reward.type() == QuestRewardType.SKILL_POINTS && reward.skillType() == SkillAttributeType.TRADING) {
            return Math.floorMod((int)(seed * 17L ^ seed >>> 29), 4) == 0 ? 1 : 0;
        }
        return reward.roll(seed, requiredCount, reputationTitle);
    }

    public String itemName() {
        class_1792 item = (class_1792)class_7923.field_41178.method_10223(this.itemId);
        return item == null || item == class_1802.field_8162 ? this.itemId.method_12832() : item.method_7848().getString();
    }

    public class_2960 selectItemId(long seed) {
        if (this.itemIds.size() <= 1) {
            return this.itemId;
        }
        return this.itemIds.get(Math.floorMod((int)(seed >>> 11), this.itemIds.size()));
    }
}

