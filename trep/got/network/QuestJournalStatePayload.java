/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record QuestJournalStatePayload(long offerCycle, List<Entry> activeQuests, List<String> completedOfferKeys, List<CooldownEntry> npcCooldowns, List<TemplateCooldownEntry> templateNpcCooldowns, List<NegotiationEntry> negotiations) implements class_8710
{
    public static final class_8710.class_9154<QuestJournalStatePayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"quest_journal_state"));
    public static final class_9139<class_9129, QuestJournalStatePayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_52974(value.offerCycle);
        buf.method_10804(value.activeQuests.size());
        for (Entry entry : value.activeQuests) {
            buf.method_10814(entry.id());
            buf.method_10814(entry.templateId());
            buf.method_10814(entry.giverNpcUuid());
            buf.method_10814(entry.giverNpcName());
            buf.method_10814(entry.itemId());
            buf.method_10804(entry.requiredCount());
            buf.method_52974(entry.acceptedAtTick());
            buf.method_52974(entry.dueAtTick());
            buf.method_10804(entry.reputationExperience());
            buf.method_10804(entry.goldIngots());
            buf.method_10804(entry.ironIngots());
            buf.method_10814(entry.skillTypeId());
            buf.method_10804(entry.skillPoints());
            buf.method_10814(entry.bonusSkillTypeId());
            buf.method_10804(entry.bonusSkillPoints());
            buf.method_10814(entry.targetRoleId());
            buf.method_10814(entry.targetCastleId());
            buf.method_10814(entry.targetCastleName());
            buf.method_10814(entry.targetNpcName());
            buf.method_10814(entry.displayItemId());
            buf.method_52964(entry.mainQuest());
            buf.method_10814(entry.objectiveActionId());
            buf.method_52964(entry.objectiveComplete());
            buf.method_52964(entry.hasLocation());
            buf.method_10804(entry.locationX());
            buf.method_10804(entry.locationY());
            buf.method_10804(entry.locationZ());
        }
        buf.method_10804(value.completedOfferKeys.size());
        for (String string : value.completedOfferKeys) {
            buf.method_10814(string);
        }
        buf.method_10804(value.npcCooldowns.size());
        for (CooldownEntry cooldownEntry : value.npcCooldowns) {
            buf.method_10814(cooldownEntry.npcUuid());
            buf.method_52974(cooldownEntry.endWindow());
        }
        buf.method_10804(value.templateNpcCooldowns.size());
        for (TemplateCooldownEntry templateCooldownEntry : value.templateNpcCooldowns) {
            buf.method_10814(templateCooldownEntry.npcUuid());
            buf.method_10814(templateCooldownEntry.templateId());
            buf.method_52974(templateCooldownEntry.endTick());
        }
        buf.method_10804(value.negotiations.size());
        for (NegotiationEntry negotiationEntry : value.negotiations) {
            buf.method_10814(negotiationEntry.offerKey());
            buf.method_10814(negotiationEntry.outcomeId());
        }
    }, buf -> {
        long offerCycle = buf.readLong();
        int size = buf.method_10816();
        ArrayList<Entry> entries = new ArrayList<Entry>(size);
        for (int index = 0; index < size; ++index) {
            entries.add(new Entry(buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_10816(), buf.readLong(), buf.readLong(), buf.method_10816(), buf.method_10816(), buf.method_10816(), buf.method_19772(), buf.method_10816(), buf.method_19772(), buf.method_10816(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.readBoolean(), buf.method_19772(), buf.readBoolean(), buf.readBoolean(), buf.method_10816(), buf.method_10816(), buf.method_10816()));
        }
        int completedSize = buf.method_10816();
        ArrayList<String> completedOfferKeys = new ArrayList<String>(completedSize);
        for (int index = 0; index < completedSize; ++index) {
            completedOfferKeys.add(buf.method_19772());
        }
        int cooldownSize = buf.method_10816();
        ArrayList<CooldownEntry> cooldowns = new ArrayList<CooldownEntry>(cooldownSize);
        for (int index = 0; index < cooldownSize; ++index) {
            cooldowns.add(new CooldownEntry(buf.method_19772(), buf.readLong()));
        }
        int templateCooldownSize = buf.method_10816();
        ArrayList<TemplateCooldownEntry> templateCooldowns = new ArrayList<TemplateCooldownEntry>(templateCooldownSize);
        for (int index = 0; index < templateCooldownSize; ++index) {
            templateCooldowns.add(new TemplateCooldownEntry(buf.method_19772(), buf.method_19772(), buf.readLong()));
        }
        int negotiationSize = buf.method_10816();
        ArrayList<NegotiationEntry> negotiations = new ArrayList<NegotiationEntry>(negotiationSize);
        for (int index = 0; index < negotiationSize; ++index) {
            negotiations.add(new NegotiationEntry(buf.method_19772(), buf.method_19772()));
        }
        return new QuestJournalStatePayload(offerCycle, List.copyOf(entries), List.copyOf(completedOfferKeys), List.copyOf(cooldowns), List.copyOf(templateCooldowns), List.copyOf(negotiations));
    });

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    public record Entry(String id, String templateId, String giverNpcUuid, String giverNpcName, String itemId, int requiredCount, long acceptedAtTick, long dueAtTick, int reputationExperience, int goldIngots, int ironIngots, String skillTypeId, int skillPoints, String bonusSkillTypeId, int bonusSkillPoints, String targetRoleId, String targetCastleId, String targetCastleName, String targetNpcName, String displayItemId, boolean mainQuest, String objectiveActionId, boolean objectiveComplete, boolean hasLocation, int locationX, int locationY, int locationZ) {
    }

    public record CooldownEntry(String npcUuid, long endWindow) {
    }

    public record TemplateCooldownEntry(String npcUuid, String templateId, long endTick) {
    }

    public record NegotiationEntry(String offerKey, String outcomeId) {
    }
}

