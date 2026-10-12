/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_2487
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.quest;

import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;
import trep.got.quest.QuestTemplateRegistry;
import trep.got.registry.GotGameRules;

public final class QuestWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.quests";
    private static final class_18.class_8645<QuestWorldState> TYPE = new class_18.class_8645(QuestWorldState::new, QuestWorldState::fromNbt, class_4284.field_45077);
    private long offerCycle;
    private long trackedDay;
    private long lastObservedTimeOfDay;
    private boolean lastObservedAffectedByTimeSkip = true;
    private boolean initialized;

    public static QuestWorldState get(class_3218 world) {
        return (QuestWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public long getOfferCycle() {
        return this.offerCycle;
    }

    public long getTrackedDay() {
        return Math.max(0L, this.trackedDay);
    }

    public long getTrackedDayTime() {
        return this.getTrackedDay() * 24000L;
    }

    public boolean tick(long worldTime, long currentTimeOfDay, boolean affectedByTimeSkip) {
        long observedTime;
        long l = observedTime = affectedByTimeSkip ? currentTimeOfDay : worldTime;
        if (!this.initialized) {
            this.trackedDay = affectedByTimeSkip ? GotGameRules.skipAwareDay(worldTime, currentTimeOfDay) : GotGameRules.regularDay(worldTime);
            this.lastObservedTimeOfDay = observedTime;
            this.lastObservedAffectedByTimeSkip = affectedByTimeSkip;
            this.initialized = true;
            this.method_80();
            return false;
        }
        if (this.lastObservedAffectedByTimeSkip != affectedByTimeSkip) {
            this.lastObservedTimeOfDay = observedTime;
            this.lastObservedAffectedByTimeSkip = affectedByTimeSkip;
            this.method_80();
            return false;
        }
        long previousTimeOfDay = this.lastObservedTimeOfDay;
        this.lastObservedTimeOfDay = observedTime;
        long previousDay = previousTimeOfDay / QuestTemplateRegistry.offerWindowTicks();
        long currentDay = observedTime / QuestTemplateRegistry.offerWindowTicks();
        long cycleIncrease = 0L;
        if (currentDay > previousDay) {
            cycleIncrease = currentDay - previousDay;
        } else if (affectedByTimeSkip && GotGameRules.normalizeTimeOfDay(currentTimeOfDay) < GotGameRules.normalizeTimeOfDay(previousTimeOfDay)) {
            cycleIncrease = 1L;
        }
        if (cycleIncrease > 0L) {
            this.trackedDay += cycleIncrease;
            this.offerCycle += cycleIncrease;
            this.method_80();
            return true;
        }
        return false;
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        nbt.method_10544("offer_cycle", this.offerCycle);
        nbt.method_10544("tracked_day", this.trackedDay);
        nbt.method_10544("last_observed_time_of_day", this.lastObservedTimeOfDay);
        nbt.method_10556("last_observed_affected_by_time_skip", this.lastObservedAffectedByTimeSkip);
        nbt.method_10556("initialized", this.initialized);
        return nbt;
    }

    private static QuestWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        QuestWorldState state = new QuestWorldState();
        state.offerCycle = nbt.method_10537("offer_cycle");
        state.trackedDay = nbt.method_10545("tracked_day") ? nbt.method_10537("tracked_day") : state.offerCycle;
        state.lastObservedTimeOfDay = nbt.method_10537("last_observed_time_of_day");
        if (nbt.method_10545("last_observed_affected_by_time_skip")) {
            state.lastObservedAffectedByTimeSkip = nbt.method_10577("last_observed_affected_by_time_skip");
        }
        state.initialized = nbt.method_10577("initialized");
        return state;
    }
}

