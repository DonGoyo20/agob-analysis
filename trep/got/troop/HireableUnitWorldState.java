/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.troop;

import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;

public final class HireableUnitWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.hireable_units";
    private static final class_18.class_8645<HireableUnitWorldState> TYPE = new class_18.class_8645(HireableUnitWorldState::new, HireableUnitWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, Long> castleSpawnDays = new LinkedHashMap<String, Long>();
    private long spawnCycle;
    private long lastObservedTimeOfDay;
    private boolean lastObservedAffectedByTimeSkip = true;
    private boolean initialized;

    public static HireableUnitWorldState get(class_3218 world) {
        return (HireableUnitWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public boolean hasSpawnedForDay(String castleId, long day) {
        return castleId != null && day >= 0L && this.castleSpawnDays.getOrDefault(castleId, -1L) == day;
    }

    public long getSpawnCycle() {
        return this.spawnCycle;
    }

    public boolean tick(long worldTime, long currentTimeOfDay, boolean affectedByTimeSkip) {
        long observedTime;
        long l = observedTime = affectedByTimeSkip ? currentTimeOfDay : worldTime;
        if (!this.initialized) {
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
        long previousDay = previousTimeOfDay / 24000L;
        long currentDay = observedTime / 24000L;
        long cycleIncrease = 0L;
        if (currentDay > previousDay) {
            cycleIncrease = currentDay - previousDay;
        } else if (affectedByTimeSkip && currentTimeOfDay < previousTimeOfDay) {
            cycleIncrease = 1L;
        }
        if (cycleIncrease > 0L) {
            this.spawnCycle += cycleIncrease;
            this.method_80();
            return true;
        }
        return false;
    }

    public void markSpawnedForDay(String castleId, long day) {
        if (castleId == null || castleId.isBlank() || day < 0L) {
            return;
        }
        Long previousDay = this.castleSpawnDays.put(castleId, day);
        if (previousDay == null || previousDay != day) {
            this.method_80();
        }
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        nbt.method_10544("spawn_cycle", this.spawnCycle);
        nbt.method_10544("last_observed_time_of_day", this.lastObservedTimeOfDay);
        nbt.method_10556("last_observed_affected_by_time_skip", this.lastObservedAffectedByTimeSkip);
        nbt.method_10556("initialized", this.initialized);
        class_2499 castles = new class_2499();
        for (Map.Entry<String, Long> entry : this.castleSpawnDays.entrySet()) {
            class_2487 castleNbt = new class_2487();
            castleNbt.method_10582("castle_id", entry.getKey());
            castleNbt.method_10544("spawn_day", entry.getValue().longValue());
            castles.add((Object)castleNbt);
        }
        nbt.method_10566("castles", (class_2520)castles);
        return nbt;
    }

    private static HireableUnitWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        HireableUnitWorldState state = new HireableUnitWorldState();
        state.spawnCycle = nbt.method_10537("spawn_cycle");
        state.lastObservedTimeOfDay = nbt.method_10537("last_observed_time_of_day");
        if (nbt.method_10545("last_observed_affected_by_time_skip")) {
            state.lastObservedAffectedByTimeSkip = nbt.method_10577("last_observed_affected_by_time_skip");
        }
        state.initialized = nbt.method_10577("initialized");
        class_2499 castles = nbt.method_10554("castles", 10);
        for (int index = 0; index < castles.size(); ++index) {
            class_2487 castleNbt = castles.method_10602(index);
            String castleId = castleNbt.method_10558("castle_id");
            if (castleId.isBlank()) continue;
            state.castleSpawnDays.put(castleId, castleNbt.method_10537("spawn_day"));
        }
        return state;
    }
}

