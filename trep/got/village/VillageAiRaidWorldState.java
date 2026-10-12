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
package trep.got.village;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;

public final class VillageAiRaidWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.village_ai_raids";
    private static final class_18.class_8645<VillageAiRaidWorldState> TYPE = new class_18.class_8645(VillageAiRaidWorldState::new, VillageAiRaidWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, VillageAiRaid> raidsByTargetVillageId = new LinkedHashMap<String, VillageAiRaid>();

    public static VillageAiRaidWorldState get(class_3218 world) {
        return (VillageAiRaidWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public List<VillageAiRaid> getAll() {
        return List.copyOf(this.raidsByTargetVillageId.values());
    }

    public VillageAiRaid get(String targetVillageId) {
        return this.raidsByTargetVillageId.get(targetVillageId);
    }

    public void put(VillageAiRaid raid) {
        if (raid == null || raid.targetVillageId().isBlank()) {
            return;
        }
        this.raidsByTargetVillageId.put(raid.targetVillageId(), raid);
        this.method_80();
    }

    public void remove(String targetVillageId) {
        if (this.raidsByTargetVillageId.remove(targetVillageId) != null) {
            this.method_80();
        }
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 raids = new class_2499();
        for (VillageAiRaid raid : this.raidsByTargetVillageId.values()) {
            raids.add((Object)raid.toNbt());
        }
        nbt.method_10566("raids", (class_2520)raids);
        return nbt;
    }

    private static VillageAiRaidWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        VillageAiRaidWorldState state = new VillageAiRaidWorldState();
        class_2499 raids = nbt.method_10554("raids", 10);
        for (int index = 0; index < raids.size(); ++index) {
            VillageAiRaid raid = VillageAiRaid.fromNbt(raids.method_10602(index));
            if (raid.targetVillageId().isBlank()) continue;
            state.raidsByTargetVillageId.put(raid.targetVillageId(), raid);
        }
        return state;
    }

    public static final class VillageAiRaid {
        private final String invadingCastleId;
        private final String targetVillageId;
        private final String attackerLordName;
        private final String attackerKingdomId;
        private final String defenderKingdomId;
        private final long warmupDurationTicks;
        private long warmupEndsAtTick;

        public VillageAiRaid(String invadingCastleId, String targetVillageId, String attackerLordName, String attackerKingdomId, String defenderKingdomId, long warmupDurationTicks, long warmupEndsAtTick) {
            this.invadingCastleId = invadingCastleId == null ? "" : invadingCastleId;
            this.targetVillageId = targetVillageId == null ? "" : targetVillageId;
            this.attackerLordName = attackerLordName == null ? "" : attackerLordName;
            this.attackerKingdomId = attackerKingdomId == null ? "" : attackerKingdomId;
            this.defenderKingdomId = defenderKingdomId == null ? "" : defenderKingdomId;
            this.warmupDurationTicks = Math.max(20L, warmupDurationTicks);
            this.warmupEndsAtTick = warmupEndsAtTick;
        }

        public String invadingCastleId() {
            return this.invadingCastleId;
        }

        public String targetVillageId() {
            return this.targetVillageId;
        }

        public String attackerLordName() {
            return this.attackerLordName;
        }

        public String attackerKingdomId() {
            return this.attackerKingdomId;
        }

        public String defenderKingdomId() {
            return this.defenderKingdomId;
        }

        public long warmupDurationTicks() {
            return this.warmupDurationTicks;
        }

        public long warmupEndsAtTick() {
            return this.warmupEndsAtTick;
        }

        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("invading_castle_id", this.invadingCastleId);
            nbt.method_10582("target_village_id", this.targetVillageId);
            nbt.method_10582("attacker_lord_name", this.attackerLordName);
            nbt.method_10582("attacker_kingdom_id", this.attackerKingdomId);
            nbt.method_10582("defender_kingdom_id", this.defenderKingdomId);
            nbt.method_10544("warmup_duration_ticks", this.warmupDurationTicks);
            nbt.method_10544("warmup_ends_at_tick", this.warmupEndsAtTick);
            return nbt;
        }

        public static VillageAiRaid fromNbt(class_2487 nbt) {
            return new VillageAiRaid(nbt.method_10558("invading_castle_id"), nbt.method_10558("target_village_id"), nbt.method_10558("attacker_lord_name"), nbt.method_10558("attacker_kingdom_id"), nbt.method_10558("defender_kingdom_id"), nbt.method_10545("warmup_duration_ticks") ? nbt.method_10537("warmup_duration_ticks") : 1200L, nbt.method_10537("warmup_ends_at_tick"));
        }
    }
}

