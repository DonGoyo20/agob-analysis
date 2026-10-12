/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_638
 *  net.minecraft.class_746
 *  net.minecraft.class_7923
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.quest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_7923;
import org.jetbrains.annotations.Nullable;
import trep.got.client.dialogue.DialogueSceneClientState;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.item.QuestDeliveryItemData;
import trep.got.network.QuestJournalStatePayload;
import trep.got.quest.QuestNegotiationOutcome;
import trep.got.quest.QuestTemplate;
import trep.got.quest.QuestTemplateRegistry;
import trep.got.registry.GotGameRules;
import trep.got.registry.GotItems;

@Environment(value=EnvType.CLIENT)
public final class QuestJournalClientState {
    private static final int[] QUEST_SLOT_COLORS = new int[]{-8401921, -727670, -6756965};
    private static List<QuestJournalStatePayload.Entry> activeQuests = List.of();
    private static List<String> completedOfferKeys = List.of();
    private static Map<String, Long> npcCooldownEndWindows = Map.of();
    private static Map<String, Long> templateNpcCooldownEndTicks = Map.of();
    private static Map<String, QuestNegotiationOutcome> negotiationOutcomes = Map.of();
    private static long offerCycle;
    private static long trackedDay;
    private static long lastObservedTimeOfDay;
    private static boolean lastObservedAffectedByTimeSkip;
    private static boolean questTimeInitialized;
    private static int version;

    private QuestJournalClientState() {
    }

    public static void apply(QuestJournalStatePayload payload, class_310 client) {
        client.execute(() -> {
            offerCycle = payload.offerCycle();
            activeQuests = payload.activeQuests();
            completedOfferKeys = payload.completedOfferKeys();
            npcCooldownEndWindows = payload.npcCooldowns().stream().collect(Collectors.toUnmodifiableMap(QuestJournalStatePayload.CooldownEntry::npcUuid, QuestJournalStatePayload.CooldownEntry::endWindow, Math::max));
            templateNpcCooldownEndTicks = payload.templateNpcCooldowns().stream().collect(Collectors.toUnmodifiableMap(entry -> entry.npcUuid() + "|" + entry.templateId(), QuestJournalStatePayload.TemplateCooldownEntry::endTick, Math::max));
            negotiationOutcomes = payload.negotiations().stream().map(entry -> Map.entry(entry.offerKey(), QuestNegotiationOutcome.byId(entry.outcomeId()))).filter(entry -> entry.getValue() != null).collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue, (left, right) -> right));
            ++version;
            DialogueSceneClientState.onQuestJournalUpdated(client);
        });
    }

    public static void clear() {
        activeQuests = List.of();
        completedOfferKeys = List.of();
        npcCooldownEndWindows = Map.of();
        templateNpcCooldownEndTicks = Map.of();
        negotiationOutcomes = Map.of();
        offerCycle = 0L;
        trackedDay = 0L;
        lastObservedTimeOfDay = 0L;
        lastObservedAffectedByTimeSkip = true;
        questTimeInitialized = false;
        ++version;
    }

    public static List<QuestJournalStatePayload.Entry> getActiveQuests() {
        return activeQuests;
    }

    public static QuestJournalStatePayload.Entry findByNpc(UUID npcUuid) {
        if (npcUuid == null) {
            return null;
        }
        String id = npcUuid.toString();
        for (QuestJournalStatePayload.Entry entry : activeQuests) {
            if (!entry.giverNpcUuid().equals(id)) continue;
            return entry;
        }
        return null;
    }

    public static QuestJournalStatePayload.Entry findByNpc(AbstractKingdomNpcEntity npc) {
        if (npc == null) {
            return null;
        }
        for (QuestJournalStatePayload.Entry entry : activeQuests) {
            if (!QuestJournalClientState.matchesNpc(entry, npc)) continue;
            return entry;
        }
        return null;
    }

    public static QuestJournalStatePayload.Entry findCompletableByNpc(AbstractKingdomNpcEntity npc, class_746 player) {
        if (npc == null || player == null) {
            return null;
        }
        QuestJournalStatePayload.Entry fallback = null;
        for (QuestJournalStatePayload.Entry entry : activeQuests) {
            if (!QuestJournalClientState.matchesNpc(entry, npc)) continue;
            if (QuestJournalClientState.canCompleteQuest(player, entry)) {
                return entry;
            }
            if (fallback != null) continue;
            fallback = entry;
        }
        return fallback;
    }

    public static int findQuestIndexByNpc(UUID npcUuid) {
        if (npcUuid == null) {
            return -1;
        }
        String id = npcUuid.toString();
        for (int index = 0; index < activeQuests.size(); ++index) {
            if (!activeQuests.get(index).giverNpcUuid().equals(id)) continue;
            return index;
        }
        return -1;
    }

    public static int findQuestIndexByNpc(AbstractKingdomNpcEntity npc) {
        if (npc == null) {
            return -1;
        }
        for (int index = 0; index < activeQuests.size(); ++index) {
            if (!QuestJournalClientState.matchesNpc(activeQuests.get(index), npc)) continue;
            return index;
        }
        return -1;
    }

    public static int getQuestColorForNpc(UUID npcUuid) {
        return QuestJournalClientState.getQuestColor(QuestJournalClientState.findQuestIndexByNpc(npcUuid));
    }

    public static int getQuestColor(int questIndex) {
        return questIndex >= 0 && questIndex < QUEST_SLOT_COLORS.length ? QUEST_SLOT_COLORS[questIndex] : -1;
    }

    public static class_1799 getQuestMarkerStack(int questIndex) {
        return switch (questIndex) {
            case 0 -> new class_1799((class_1935)GotItems.QUEST_MARKER_ONE);
            case 1 -> new class_1799((class_1935)GotItems.QUEST_MARKER_TWO);
            case 2 -> new class_1799((class_1935)GotItems.QUEST_MARKER_THREE);
            default -> class_1799.field_8037;
        };
    }

    public static int getNextQuestSlotIndex() {
        return activeQuests.size() >= QUEST_SLOT_COLORS.length ? -1 : activeQuests.size();
    }

    public static int getNextQuestColor() {
        return QuestJournalClientState.getQuestColor(QuestJournalClientState.getNextQuestSlotIndex());
    }

    public static long getOfferCycle() {
        return offerCycle;
    }

    public static int getVersion() {
        return version;
    }

    public static long currentQuestTime(@Nullable class_638 world) {
        long observedTime;
        if (world == null) {
            return 0L;
        }
        boolean affectedByTimeSkip = true;
        long worldTime = world.method_8510();
        long currentTimeOfDay = world.method_8532();
        long l = observedTime = affectedByTimeSkip ? currentTimeOfDay : worldTime;
        if (!questTimeInitialized) {
            trackedDay = affectedByTimeSkip ? GotGameRules.skipAwareDay(worldTime, currentTimeOfDay) : GotGameRules.regularDay(worldTime);
            lastObservedTimeOfDay = observedTime;
            lastObservedAffectedByTimeSkip = affectedByTimeSkip;
            questTimeInitialized = true;
            return trackedDay * 24000L;
        }
        if (lastObservedAffectedByTimeSkip != affectedByTimeSkip) {
            lastObservedTimeOfDay = observedTime;
            lastObservedAffectedByTimeSkip = affectedByTimeSkip;
            return trackedDay * 24000L;
        }
        long previousTimeOfDay = lastObservedTimeOfDay;
        lastObservedTimeOfDay = observedTime;
        long previousDay = previousTimeOfDay / 24000L;
        long currentDay = observedTime / 24000L;
        long dayIncrease = 0L;
        if (currentDay > previousDay) {
            dayIncrease = currentDay - previousDay;
        } else if (affectedByTimeSkip && GotGameRules.normalizeTimeOfDay(currentTimeOfDay) < GotGameRules.normalizeTimeOfDay(previousTimeOfDay)) {
            dayIncrease = 1L;
        }
        if (dayIncrease > 0L) {
            trackedDay += dayIncrease;
        }
        return trackedDay * 24000L;
    }

    public static boolean hasCompletedOffer(String offerKey) {
        return offerKey != null && completedOfferKeys.contains(offerKey);
    }

    public static QuestNegotiationOutcome getNegotiationOutcome(String offerKey) {
        return offerKey == null ? null : negotiationOutcomes.get(offerKey);
    }

    public static boolean isNpcOnCooldown(UUID npcUuid, long worldTime) {
        if (npcUuid == null) {
            return false;
        }
        Long endWindow = npcCooldownEndWindows.get(npcUuid.toString());
        return endWindow != null && endWindow > worldTime / QuestTemplateRegistry.offerWindowTicks();
    }

    public static boolean isTemplateNpcOnCooldown(UUID npcUuid, String templateId, long worldTime) {
        if (npcUuid == null || templateId == null || templateId.isBlank()) {
            return false;
        }
        Long endTick = templateNpcCooldownEndTicks.get(String.valueOf(npcUuid) + "|" + templateId);
        return endTick != null && endTick > worldTime;
    }

    public static class_1792 resolveItem(String itemId) {
        class_2960 parsed = class_2960.method_12829((String)itemId);
        return parsed == null ? class_1802.field_8162 : (class_1792)class_7923.field_41178.method_10223(parsed);
    }

    public static boolean canCompleteQuest(class_746 player, QuestJournalStatePayload.Entry quest) {
        if (player == null || quest == null) {
            return false;
        }
        if ("main_pledge".equals(quest.templateId())) {
            return false;
        }
        if ("valyrian_rumor".equals(quest.templateId())) {
            return false;
        }
        if (!quest.targetCastleId().isBlank()) {
            int count = 0;
            for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
                class_1799 stack = player.method_31548().method_5438(slot);
                if (!QuestDeliveryItemData.matchesQuest(stack, quest.id())) continue;
                count += stack.method_7947();
            }
            return count >= quest.requiredCount();
        }
        if ("nearby_bandit_camp_destroy".equals(quest.templateId())) {
            return quest.objectiveComplete();
        }
        if ("destroy_wildling_raider_camp".equals(quest.templateId()) || "nights_watch_ranger_explore".equals(quest.templateId())) {
            return quest.objectiveComplete();
        }
        class_1792 item = QuestJournalClientState.resolveItem(quest.itemId());
        if (item == null || item == class_1802.field_8162) {
            return false;
        }
        int count = 0;
        for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
            class_1799 stack = player.method_31548().method_5438(slot);
            if (!stack.method_31574(item)) continue;
            count += stack.method_7947();
        }
        return count >= quest.requiredCount();
    }

    public static List<QuestLocationMarker> getQuestLocationMarkers() {
        ArrayList<QuestLocationMarker> markers = new ArrayList<QuestLocationMarker>();
        for (int index = 0; index < activeQuests.size(); ++index) {
            QuestJournalStatePayload.Entry entry = activeQuests.get(index);
            if (!entry.hasLocation()) continue;
            markers.add(new QuestLocationMarker(entry.id(), entry.templateId(), index, entry.locationX(), entry.locationY(), entry.locationZ()));
        }
        return List.copyOf(markers);
    }

    public static String getActivePledgeObjectiveActionId() {
        return "";
    }

    public static List<CastleQuestIndicator> getCastleQuestIndicators(String castleId) {
        if (castleId == null || castleId.isBlank()) {
            return List.of();
        }
        ArrayList<CastleQuestIndicator> indicators = new ArrayList<CastleQuestIndicator>();
        for (int index = 0; index < activeQuests.size(); ++index) {
            QuestJournalStatePayload.Entry entry = activeQuests.get(index);
            if (!castleId.equals(entry.targetCastleId())) continue;
            indicators.add(new CastleQuestIndicator(entry.id(), entry.templateId(), index, QuestJournalClientState.labelForCastleQuest(entry)));
        }
        return List.copyOf(indicators);
    }

    private static String labelForCastleQuest(QuestJournalStatePayload.Entry entry) {
        if (entry == null) {
            return "Quest";
        }
        if ("main_pledge".equals(entry.templateId())) {
            return "Pledge Quest";
        }
        QuestTemplate template = QuestTemplateRegistry.get(entry.templateId());
        return template == null ? "Quest" : template.displayName();
    }

    private static boolean matchesNpc(QuestJournalStatePayload.Entry quest, AbstractKingdomNpcEntity npc) {
        if (quest == null || npc == null) {
            return false;
        }
        if ("main_pledge".equals(quest.templateId())) {
            return quest.targetCastleId().equals(npc.got$getCastle()) && quest.targetRoleId().equalsIgnoreCase(npc.getNpcRole().name());
        }
        if ("valyrian_rumor".equals(quest.templateId())) {
            if ("confirm_identity".equals(quest.objectiveActionId())) {
                return npc.getKingdomId() != null && "agob:targaryen".equals(npc.getKingdomId().toString()) && quest.targetRoleId().equalsIgnoreCase(npc.getNpcRole().name());
            }
            if ("return_to_giver".equals(quest.objectiveActionId())) {
                return quest.giverNpcUuid().equals(npc.method_5667().toString());
            }
            return false;
        }
        if (!quest.targetCastleId().isBlank()) {
            return quest.targetCastleId().equals(npc.got$getCastle()) && quest.targetRoleId().equalsIgnoreCase(npc.getNpcRole().name());
        }
        return quest.giverNpcUuid().equals(npc.method_5667().toString());
    }

    static {
        lastObservedAffectedByTimeSkip = true;
    }

    @Environment(value=EnvType.CLIENT)
    public record QuestLocationMarker(String questId, String templateId, int questIndex, int x, int y, int z) {
    }

    @Environment(value=EnvType.CLIENT)
    public record CastleQuestIndicator(String questId, String templateId, int questIndex, String label) {
        public int color() {
            return QuestJournalClientState.getQuestColor(this.questIndex);
        }

        public class_1799 markerStack() {
            return QuestJournalClientState.getQuestMarkerStack(this.questIndex);
        }
    }
}

