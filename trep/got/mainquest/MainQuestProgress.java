/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 */
package trep.got.mainquest;

import java.util.LinkedHashSet;
import java.util.Set;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public final class MainQuestProgress {
    private final class_2960 questId;
    private int stepIndex;
    private long acceptedAtTick;
    private final class_2487 state = new class_2487();

    public MainQuestProgress(class_2960 questId, long acceptedAtTick) {
        this.questId = questId;
        this.acceptedAtTick = acceptedAtTick;
    }

    public class_2960 questId() {
        return this.questId;
    }

    public int stepIndex() {
        return this.stepIndex;
    }

    public void setStepIndex(int stepIndex) {
        this.stepIndex = Math.max(0, stepIndex);
    }

    public long acceptedAtTick() {
        return this.acceptedAtTick;
    }

    public int getCounter(String key) {
        return this.state.method_10550(key);
    }

    public void setCounter(String key, int value) {
        this.state.method_10569(key, Math.max(0, value));
    }

    public boolean addToSet(String key, String value) {
        if (key == null || key.isBlank() || value == null || value.isBlank()) {
            return false;
        }
        Set<String> values = this.getSet(key);
        if (!values.add(value)) {
            return false;
        }
        class_2499 list = new class_2499();
        for (String entry : values) {
            list.add((Object)class_2519.method_23256((String)entry));
        }
        this.state.method_10566(key, (class_2520)list);
        return true;
    }

    public int setSize(String key) {
        return this.getSet(key).size();
    }

    public boolean hasInSet(String key, String value) {
        return value != null && this.getSet(key).contains(value);
    }

    public void advance() {
        ++this.stepIndex;
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("quest_id", this.questId.toString());
        nbt.method_10569("step_index", this.stepIndex);
        nbt.method_10544("accepted_at_tick", this.acceptedAtTick);
        nbt.method_10566("state", (class_2520)this.state.method_10553());
        return nbt;
    }

    public static MainQuestProgress fromNbt(class_2487 nbt) {
        class_2960 id = class_2960.method_60654((String)nbt.method_10558("quest_id"));
        MainQuestProgress progress = new MainQuestProgress(id, nbt.method_10537("accepted_at_tick"));
        progress.setStepIndex(nbt.method_10550("step_index"));
        if (nbt.method_10573("state", 10)) {
            class_2487 state = nbt.method_10562("state");
            for (String key : state.method_10541()) {
                class_2520 element = state.method_10580(key);
                if (element == null) continue;
                progress.state.method_10566(key, element.method_10707());
            }
        }
        return progress;
    }

    private Set<String> getSet(String key) {
        LinkedHashSet<String> values = new LinkedHashSet<String>();
        class_2499 list = this.state.method_10554(key, 8);
        for (int index = 0; index < list.size(); ++index) {
            String value = list.method_10608(index);
            if (value.isBlank()) continue;
            values.add(value);
        }
        return values;
    }
}

