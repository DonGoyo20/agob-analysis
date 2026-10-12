/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 */
package trep.got.world.event.context;

import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;

public final class WorldEventProgressContext {
    private final Map<String, Integer> skillPoints;
    private final String reputationTitleId;
    private final int reputationExperience;
    private final boolean onQuest;
    private final int activeQuestCount;
    private final boolean onVoyage;
    private final String activeLordLetterQuestId;
    private final String activeLordLetterTargetName;
    private final long activeLordLetterAcceptedAtTick;
    private final boolean activeLordLetterAmbushTriggered;

    public WorldEventProgressContext(Map<String, Integer> skillPoints, String reputationTitleId, int reputationExperience, boolean onQuest, int activeQuestCount, boolean onVoyage, String activeLordLetterQuestId, String activeLordLetterTargetName, long activeLordLetterAcceptedAtTick, boolean activeLordLetterAmbushTriggered) {
        this.skillPoints = Map.copyOf(skillPoints);
        this.reputationTitleId = reputationTitleId;
        this.reputationExperience = reputationExperience;
        this.onQuest = onQuest;
        this.activeQuestCount = activeQuestCount;
        this.onVoyage = onVoyage;
        this.activeLordLetterQuestId = activeLordLetterQuestId == null ? "" : activeLordLetterQuestId;
        this.activeLordLetterTargetName = activeLordLetterTargetName == null ? "" : activeLordLetterTargetName;
        this.activeLordLetterAcceptedAtTick = Math.max(0L, activeLordLetterAcceptedAtTick);
        this.activeLordLetterAmbushTriggered = activeLordLetterAmbushTriggered;
    }

    public Map<String, Integer> skillPoints() {
        return this.skillPoints;
    }

    public String reputationTitleId() {
        return this.reputationTitleId;
    }

    public int reputationExperience() {
        return this.reputationExperience;
    }

    public boolean onQuest() {
        return this.onQuest;
    }

    public int activeQuestCount() {
        return this.activeQuestCount;
    }

    public boolean onVoyage() {
        return this.onVoyage;
    }

    public String activeLordLetterQuestId() {
        return this.activeLordLetterQuestId;
    }

    public String activeLordLetterTargetName() {
        return this.activeLordLetterTargetName;
    }

    public long activeLordLetterAcceptedAtTick() {
        return this.activeLordLetterAcceptedAtTick;
    }

    public boolean activeLordLetterAmbushTriggered() {
        return this.activeLordLetterAmbushTriggered;
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        class_2499 skillList = new class_2499();
        for (Map.Entry<String, Integer> entry : this.skillPoints.entrySet()) {
            class_2487 skill = new class_2487();
            skill.method_10582("id", entry.getKey());
            skill.method_10569("points", entry.getValue().intValue());
            skillList.add((Object)skill);
        }
        nbt.method_10566("skill_points", (class_2520)skillList);
        nbt.method_10582("reputation_title_id", this.reputationTitleId);
        nbt.method_10569("reputation_experience", this.reputationExperience);
        nbt.method_10556("on_quest", this.onQuest);
        nbt.method_10569("active_quest_count", this.activeQuestCount);
        nbt.method_10556("on_voyage", this.onVoyage);
        nbt.method_10582("active_lord_letter_quest_id", this.activeLordLetterQuestId);
        nbt.method_10582("active_lord_letter_target_name", this.activeLordLetterTargetName);
        nbt.method_10544("active_lord_letter_accepted_at_tick", this.activeLordLetterAcceptedAtTick);
        nbt.method_10556("active_lord_letter_ambush_triggered", this.activeLordLetterAmbushTriggered);
        return nbt;
    }

    public static WorldEventProgressContext fromNbt(class_2487 nbt) {
        LinkedHashMap<String, Integer> skillPoints = new LinkedHashMap<String, Integer>();
        class_2499 skillList = nbt.method_10554("skill_points", 10);
        for (int index = 0; index < skillList.size(); ++index) {
            class_2487 skill = skillList.method_10602(index);
            String id = skill.method_10558("id");
            if (id.isBlank()) continue;
            skillPoints.put(id, skill.method_10550("points"));
        }
        return new WorldEventProgressContext(skillPoints, nbt.method_10558("reputation_title_id"), nbt.method_10550("reputation_experience"), nbt.method_10577("on_quest"), nbt.method_10550("active_quest_count"), nbt.method_10577("on_voyage"), nbt.method_10558("active_lord_letter_quest_id"), nbt.method_10558("active_lord_letter_target_name"), nbt.method_10537("active_lord_letter_accepted_at_tick"), nbt.method_10577("active_lord_letter_ambush_triggered"));
    }
}

