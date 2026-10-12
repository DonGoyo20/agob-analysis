/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 */
package trep.got.quest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.quest.QuestInstance;
import trep.got.quest.QuestNegotiationOutcome;
import trep.got.quest.QuestTemplateRegistry;

public final class PlayerQuestProfile {
    private static final int MAX_COMPLETED_OFFER_KEYS = 128;
    private static final int MAX_NPC_COOLDOWNS = 128;
    private static final int MAX_NEGOTIATED_OFFERS = 256;
    private static final int MAX_TEMPLATE_NPC_COOLDOWNS = 256;
    private static final int MAX_ROBBER_AMBUSHED_QUESTS = 256;
    private final List<QuestInstance> activeQuests = new ArrayList<QuestInstance>();
    private final LinkedHashSet<String> completedOfferKeys = new LinkedHashSet();
    private final LinkedHashSet<String> robberAmbushedQuestIds = new LinkedHashSet();
    private final LinkedHashMap<UUID, Long> npcCooldownEndWindows = new LinkedHashMap();
    private final LinkedHashMap<String, Long> templateNpcCooldownEndTicks = new LinkedHashMap();
    private final LinkedHashMap<String, QuestNegotiationOutcome> negotiationOutcomes = new LinkedHashMap();

    public List<QuestInstance> getActiveQuests() {
        return List.copyOf(this.activeQuests);
    }

    public int activeQuestCount() {
        return this.activeQuests.size();
    }

    public QuestInstance findByNpc(UUID npcUuid) {
        if (npcUuid == null) {
            return null;
        }
        for (QuestInstance quest : this.activeQuests) {
            if (!npcUuid.equals(quest.giverNpcUuid())) continue;
            return quest;
        }
        return null;
    }

    public QuestInstance findById(String questId) {
        if (questId == null || questId.isBlank()) {
            return null;
        }
        for (QuestInstance quest : this.activeQuests) {
            if (!quest.id().equals(questId)) continue;
            return quest;
        }
        return null;
    }

    public QuestInstance findForNpc(AbstractKingdomNpcEntity npc) {
        if (npc == null) {
            return null;
        }
        for (QuestInstance quest : this.activeQuests) {
            if (!quest.matchesNpc(npc)) continue;
            return quest;
        }
        return null;
    }

    public QuestInstance findCompletableForNpc(AbstractKingdomNpcEntity npc, class_1657 player) {
        if (npc == null || player == null) {
            return null;
        }
        QuestInstance fallback = null;
        for (QuestInstance quest : this.activeQuests) {
            if (!quest.matchesNpc(npc)) continue;
            if (quest.canComplete(player)) {
                return quest;
            }
            if (fallback != null) continue;
            fallback = quest;
        }
        return fallback;
    }

    public boolean hasActiveQuest(String questId) {
        if (questId == null || questId.isBlank()) {
            return false;
        }
        for (QuestInstance quest : this.activeQuests) {
            if (!quest.id().equals(questId)) continue;
            return true;
        }
        return false;
    }

    public void addQuest(QuestInstance quest) {
        if (quest != null) {
            this.activeQuests.add(quest);
        }
    }

    public void removeQuest(String questId) {
        this.activeQuests.removeIf(quest -> quest.id().equals(questId));
    }

    public void updateQuest(QuestInstance updatedQuest) {
        if (updatedQuest == null || updatedQuest.id() == null || updatedQuest.id().isBlank()) {
            return;
        }
        for (int index = 0; index < this.activeQuests.size(); ++index) {
            if (!this.activeQuests.get(index).id().equals(updatedQuest.id())) continue;
            this.activeQuests.set(index, updatedQuest);
            return;
        }
    }

    public void markOfferCompleted(String offerKey) {
        if (offerKey == null || offerKey.isBlank()) {
            return;
        }
        this.completedOfferKeys.add(offerKey);
        while (this.completedOfferKeys.size() > 128) {
            this.completedOfferKeys.removeFirst();
        }
    }

    public boolean hasCompletedOffer(String offerKey) {
        return offerKey != null && this.completedOfferKeys.contains(offerKey);
    }

    public void markRobberAmbushTriggered(String questId) {
        if (questId == null || questId.isBlank()) {
            return;
        }
        this.robberAmbushedQuestIds.add(questId);
        while (this.robberAmbushedQuestIds.size() > 256) {
            this.robberAmbushedQuestIds.removeFirst();
        }
    }

    public boolean hasRobberAmbushTriggered(String questId) {
        return questId != null && this.robberAmbushedQuestIds.contains(questId);
    }

    public Set<String> getCompletedOfferKeys() {
        return Set.copyOf(this.completedOfferKeys);
    }

    public void setNpcCooldown(UUID npcUuid, long cooldownEndWindow) {
        if (npcUuid == null) {
            return;
        }
        this.npcCooldownEndWindows.put(npcUuid, cooldownEndWindow);
        while (this.npcCooldownEndWindows.size() > 128) {
            this.npcCooldownEndWindows.remove(this.npcCooldownEndWindows.firstEntry().getKey());
        }
    }

    public boolean isNpcOnCooldown(UUID npcUuid, long worldTime) {
        if (npcUuid == null) {
            return false;
        }
        Long cooldownEndWindow = this.npcCooldownEndWindows.get(npcUuid);
        return cooldownEndWindow != null && cooldownEndWindow > worldTime / QuestTemplateRegistry.offerWindowTicks();
    }

    public Map<UUID, Long> getNpcCooldownEndWindows() {
        return Map.copyOf(this.npcCooldownEndWindows);
    }

    public void setTemplateNpcCooldown(UUID npcUuid, String templateId, long cooldownEndTick) {
        String key = PlayerQuestProfile.templateNpcCooldownKey(npcUuid, templateId);
        if (key == null) {
            return;
        }
        this.templateNpcCooldownEndTicks.put(key, cooldownEndTick);
        while (this.templateNpcCooldownEndTicks.size() > 256) {
            this.templateNpcCooldownEndTicks.remove(this.templateNpcCooldownEndTicks.firstEntry().getKey());
        }
    }

    public boolean isTemplateNpcOnCooldown(UUID npcUuid, String templateId, long worldTime) {
        String key = PlayerQuestProfile.templateNpcCooldownKey(npcUuid, templateId);
        if (key == null) {
            return false;
        }
        Long cooldownEndTick = this.templateNpcCooldownEndTicks.get(key);
        return cooldownEndTick != null && cooldownEndTick > worldTime;
    }

    public Map<String, Long> getTemplateNpcCooldownEndTicks() {
        return Map.copyOf(this.templateNpcCooldownEndTicks);
    }

    public QuestNegotiationOutcome getNegotiationOutcome(String offerKey) {
        return offerKey == null ? null : this.negotiationOutcomes.get(offerKey);
    }

    public boolean hasNegotiatedOffer(String offerKey) {
        return this.getNegotiationOutcome(offerKey) != null;
    }

    public void setNegotiationOutcome(String offerKey, QuestNegotiationOutcome outcome) {
        if (offerKey == null || offerKey.isBlank() || outcome == null) {
            return;
        }
        this.negotiationOutcomes.put(offerKey, outcome);
        while (this.negotiationOutcomes.size() > 256) {
            this.negotiationOutcomes.remove(this.negotiationOutcomes.firstEntry().getKey());
        }
    }

    public Map<String, QuestNegotiationOutcome> getNegotiationOutcomes() {
        return Map.copyOf(this.negotiationOutcomes);
    }

    public boolean cleanup(long questTime, long worldTime) {
        boolean changed = this.activeQuests.removeIf(quest -> quest.isExpired(questTime));
        long currentWindow = questTime / QuestTemplateRegistry.offerWindowTicks();
        if (this.npcCooldownEndWindows.entrySet().removeIf(entry -> (Long)entry.getValue() <= currentWindow)) {
            changed = true;
        }
        if (this.templateNpcCooldownEndTicks.entrySet().removeIf(entry -> (Long)entry.getValue() <= worldTime)) {
            changed = true;
        }
        if (this.negotiationOutcomes.entrySet().removeIf(entry -> PlayerQuestProfile.offerCycleOf((String)entry.getKey()) < currentWindow)) {
            changed = true;
        }
        return changed;
    }

    public void copyFrom(PlayerQuestProfile other) {
        this.activeQuests.clear();
        this.activeQuests.addAll(other.activeQuests);
        this.completedOfferKeys.clear();
        this.completedOfferKeys.addAll(other.completedOfferKeys);
        this.robberAmbushedQuestIds.clear();
        this.robberAmbushedQuestIds.addAll(other.robberAmbushedQuestIds);
        this.npcCooldownEndWindows.clear();
        this.npcCooldownEndWindows.putAll(other.npcCooldownEndWindows);
        this.templateNpcCooldownEndTicks.clear();
        this.templateNpcCooldownEndTicks.putAll(other.templateNpcCooldownEndTicks);
        this.negotiationOutcomes.clear();
        this.negotiationOutcomes.putAll(other.negotiationOutcomes);
    }

    public void clear() {
        this.activeQuests.clear();
        this.completedOfferKeys.clear();
        this.robberAmbushedQuestIds.clear();
        this.npcCooldownEndWindows.clear();
        this.templateNpcCooldownEndTicks.clear();
        this.negotiationOutcomes.clear();
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        class_2499 quests = new class_2499();
        for (QuestInstance questInstance : this.activeQuests) {
            quests.add((Object)questInstance.toNbt());
        }
        nbt.method_10566("active_quests", (class_2520)quests);
        class_2499 completed = new class_2499();
        for (String string : this.completedOfferKeys) {
            completed.add((Object)class_2519.method_23256((String)string));
        }
        nbt.method_10566("completed_offer_keys", (class_2520)completed);
        class_2499 class_24992 = new class_2499();
        for (String string : this.robberAmbushedQuestIds) {
            class_24992.add((Object)class_2519.method_23256((String)string));
        }
        nbt.method_10566("robber_ambushed_quests", (class_2520)class_24992);
        class_2499 class_24993 = new class_2499();
        for (Map.Entry<UUID, Long> entry : this.npcCooldownEndWindows.entrySet()) {
            class_2487 class_24872 = new class_2487();
            class_24872.method_25927("npc_uuid", entry.getKey());
            class_24872.method_10544("end_window", entry.getValue().longValue());
            class_24993.add((Object)class_24872);
        }
        nbt.method_10566("npc_cooldowns", (class_2520)class_24993);
        class_2499 class_24994 = new class_2499();
        for (Map.Entry<String, Long> entry : this.templateNpcCooldownEndTicks.entrySet()) {
            String[] parts = entry.getKey().split("\\|", 2);
            if (parts.length != 2) continue;
            class_2487 cooldown = new class_2487();
            cooldown.method_10582("npc_uuid", parts[0]);
            cooldown.method_10582("template_id", parts[1]);
            cooldown.method_10544("end_tick", entry.getValue().longValue());
            class_24994.add((Object)cooldown);
        }
        nbt.method_10566("template_npc_cooldowns", (class_2520)class_24994);
        class_2499 class_24995 = new class_2499();
        for (Map.Entry<String, QuestNegotiationOutcome> entry : this.negotiationOutcomes.entrySet()) {
            class_2487 negotiation = new class_2487();
            negotiation.method_10582("offer_key", entry.getKey());
            negotiation.method_10582("outcome", entry.getValue().getId());
            class_24995.add((Object)negotiation);
        }
        nbt.method_10566("negotiation_outcomes", (class_2520)class_24995);
        return nbt;
    }

    public static PlayerQuestProfile fromNbt(class_2487 nbt) {
        PlayerQuestProfile profile = new PlayerQuestProfile();
        class_2499 quests = nbt.method_10554("active_quests", 10);
        for (int index = 0; index < quests.size(); ++index) {
            profile.activeQuests.add(QuestInstance.fromNbt(quests.method_10602(index)));
        }
        class_2499 completed = nbt.method_10554("completed_offer_keys", 8);
        for (int index = 0; index < completed.size(); ++index) {
            profile.completedOfferKeys.add(completed.method_10608(index));
        }
        class_2499 robberAmbushes = nbt.method_10554("robber_ambushed_quests", 8);
        for (int index = 0; index < robberAmbushes.size(); ++index) {
            String questId = robberAmbushes.method_10608(index);
            if (questId.isBlank()) continue;
            profile.robberAmbushedQuestIds.add(questId);
        }
        class_2499 cooldowns = nbt.method_10554("npc_cooldowns", 10);
        for (int index = 0; index < cooldowns.size(); ++index) {
            class_2487 cooldown = cooldowns.method_10602(index);
            if (!cooldown.method_25928("npc_uuid")) continue;
            profile.npcCooldownEndWindows.put(cooldown.method_25926("npc_uuid"), cooldown.method_10537("end_window"));
        }
        class_2499 templateCooldowns = nbt.method_10554("template_npc_cooldowns", 10);
        for (int index = 0; index < templateCooldowns.size(); ++index) {
            class_2487 cooldown = templateCooldowns.method_10602(index);
            String key = PlayerQuestProfile.templateNpcCooldownKey(cooldown.method_10545("npc_uuid") ? UUID.fromString(cooldown.method_10558("npc_uuid")) : null, cooldown.method_10558("template_id"));
            if (key == null) continue;
            profile.templateNpcCooldownEndTicks.put(key, cooldown.method_10537("end_tick"));
        }
        class_2499 negotiations = nbt.method_10554("negotiation_outcomes", 10);
        for (int index = 0; index < negotiations.size(); ++index) {
            class_2487 negotiation = negotiations.method_10602(index);
            QuestNegotiationOutcome outcome = QuestNegotiationOutcome.byId(negotiation.method_10558("outcome"));
            String offerKey = negotiation.method_10558("offer_key");
            if (outcome == null || offerKey.isBlank()) continue;
            profile.negotiationOutcomes.put(offerKey, outcome);
        }
        return profile;
    }

    private static long offerCycleOf(String offerKey) {
        if (offerKey == null) {
            return Long.MIN_VALUE;
        }
        int separator = offerKey.lastIndexOf(58);
        if (separator < 0 || separator >= offerKey.length() - 1) {
            return Long.MIN_VALUE;
        }
        try {
            return Long.parseLong(offerKey.substring(separator + 1));
        }
        catch (NumberFormatException exception) {
            return Long.MIN_VALUE;
        }
    }

    private static String templateNpcCooldownKey(UUID npcUuid, String templateId) {
        if (npcUuid == null || templateId == null || templateId.isBlank()) {
            return null;
        }
        return String.valueOf(npcUuid) + "|" + templateId;
    }
}

