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

public record MainQuestStatePayload(List<Entry> activeQuests, List<String> completedQuestIds) implements class_8710
{
    public static final class_8710.class_9154<MainQuestStatePayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"main_quest_state"));
    public static final class_9139<class_9129, MainQuestStatePayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.activeQuests.size());
        for (Entry entry : value.activeQuests) {
            buf.method_10814(entry.id());
            buf.method_10814(entry.displayName());
            buf.method_10804(entry.description().size());
            for (String line : entry.description()) {
                buf.method_10814(line);
            }
            buf.method_10804(entry.steps().size());
            for (Step step : entry.steps()) {
                buf.method_10814(step.id());
                buf.method_10814(step.text());
                buf.method_10804(step.progress());
                buf.method_10804(step.required());
            }
            buf.method_10804(entry.currentStepIndex());
            buf.method_10804(entry.rewards().size());
            for (Reward reward : entry.rewards()) {
                buf.method_10814(reward.itemId());
                buf.method_10804(reward.count());
            }
        }
        buf.method_10804(value.completedQuestIds.size());
        for (String id : value.completedQuestIds) {
            buf.method_10814(id);
        }
    }, buf -> {
        int activeSize = buf.method_10816();
        ArrayList<Entry> active = new ArrayList<Entry>(activeSize);
        for (int index = 0; index < activeSize; ++index) {
            String id = buf.method_19772();
            String displayName = buf.method_19772();
            int descriptionSize = buf.method_10816();
            ArrayList<String> description = new ArrayList<String>(descriptionSize);
            for (int line = 0; line < descriptionSize; ++line) {
                description.add(buf.method_19772());
            }
            int stepSize = buf.method_10816();
            ArrayList<Step> steps = new ArrayList<Step>(stepSize);
            for (int step = 0; step < stepSize; ++step) {
                steps.add(new Step(buf.method_19772(), buf.method_19772(), buf.method_10816(), buf.method_10816()));
            }
            int currentStepIndex = buf.method_10816();
            int rewardSize = buf.method_10816();
            ArrayList<Reward> rewards = new ArrayList<Reward>(rewardSize);
            for (int reward = 0; reward < rewardSize; ++reward) {
                rewards.add(new Reward(buf.method_19772(), buf.method_10816()));
            }
            active.add(new Entry(id, displayName, List.copyOf(description), List.copyOf(steps), currentStepIndex, List.copyOf(rewards)));
        }
        int completedSize = buf.method_10816();
        ArrayList<String> completed = new ArrayList<String>(completedSize);
        for (int index = 0; index < completedSize; ++index) {
            completed.add(buf.method_19772());
        }
        return new MainQuestStatePayload(List.copyOf(active), List.copyOf(completed));
    });

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    public record Step(String id, String text, int progress, int required) {
    }

    public record Reward(String itemId, int count) {
    }

    public record Entry(String id, String displayName, List<String> description, List<Step> steps, int currentStepIndex, List<Reward> rewards) {
    }
}

