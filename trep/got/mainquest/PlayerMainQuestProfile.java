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

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import trep.got.mainquest.MainQuestProgress;

public final class PlayerMainQuestProfile {
    private final Map<class_2960, MainQuestProgress> activeQuests = new LinkedHashMap<class_2960, MainQuestProgress>();
    private final Set<class_2960> completedQuestIds = new LinkedHashSet<class_2960>();

    public List<MainQuestProgress> activeQuests() {
        return List.copyOf(this.activeQuests.values());
    }

    public Set<class_2960> completedQuestIds() {
        return Set.copyOf(this.completedQuestIds);
    }

    public MainQuestProgress getActive(class_2960 questId) {
        return this.activeQuests.get(questId);
    }

    public boolean hasActive(class_2960 questId) {
        return this.activeQuests.containsKey(questId);
    }

    public boolean hasCompleted(class_2960 questId) {
        return this.completedQuestIds.contains(questId);
    }

    public boolean addActive(MainQuestProgress progress) {
        if (progress == null || this.hasCompleted(progress.questId()) || this.hasActive(progress.questId())) {
            return false;
        }
        this.activeQuests.put(progress.questId(), progress);
        return true;
    }

    public boolean removeActive(class_2960 questId) {
        return this.activeQuests.remove(questId) != null;
    }

    public boolean complete(class_2960 questId) {
        boolean removed = this.activeQuests.remove(questId) != null;
        boolean added = this.completedQuestIds.add(questId);
        return removed || added;
    }

    public void copyFrom(PlayerMainQuestProfile other) {
        this.activeQuests.clear();
        for (MainQuestProgress progress : other.activeQuests.values()) {
            MainQuestProgress copy = MainQuestProgress.fromNbt(progress.toNbt());
            this.activeQuests.put(copy.questId(), copy);
        }
        this.completedQuestIds.clear();
        this.completedQuestIds.addAll(other.completedQuestIds);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        class_2499 active = new class_2499();
        for (MainQuestProgress progress : this.activeQuests.values()) {
            active.add((Object)progress.toNbt());
        }
        nbt.method_10566("active", (class_2520)active);
        class_2499 completed = new class_2499();
        for (class_2960 questId : this.completedQuestIds) {
            completed.add((Object)class_2519.method_23256((String)questId.toString()));
        }
        nbt.method_10566("completed", (class_2520)completed);
        return nbt;
    }

    public static PlayerMainQuestProfile fromNbt(class_2487 nbt) {
        PlayerMainQuestProfile profile = new PlayerMainQuestProfile();
        class_2499 active = nbt.method_10554("active", 10);
        for (int index = 0; index < active.size(); ++index) {
            MainQuestProgress progress = MainQuestProgress.fromNbt(active.method_10602(index));
            profile.activeQuests.put(progress.questId(), progress);
        }
        class_2499 completed = nbt.method_10554("completed", 8);
        for (int index = 0; index < completed.size(); ++index) {
            String id = completed.method_10608(index);
            if (id.isBlank()) continue;
            profile.completedQuestIds.add(class_2960.method_60654((String)id));
        }
        return profile;
    }
}

