/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.castle;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;
import trep.got.castle.CastleInvasionMode;
import trep.got.castle.CastleTier;

public final class KingdomAiInvasionWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.kingdom_ai_invasions";
    private static final class_18.class_8645<KingdomAiInvasionWorldState> TYPE = new class_18.class_8645(KingdomAiInvasionWorldState::new, KingdomAiInvasionWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, KingdomAiInvasion> invasionsByTargetCastleId = new LinkedHashMap<String, KingdomAiInvasion>();
    private final Map<String, Long> nextInvasionDayByCastleId = new LinkedHashMap<String, Long>();
    private final Map<String, Long> nextInvasionDayByLordKey = new LinkedHashMap<String, Long>();
    private long queuedManualProgressDays;
    private long progressOffsetTicks;

    public static KingdomAiInvasionWorldState get(class_3218 world) {
        return (KingdomAiInvasionWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public List<KingdomAiInvasion> getAll() {
        return List.copyOf(this.invasionsByTargetCastleId.values());
    }

    public KingdomAiInvasion get(String targetCastleId) {
        return this.invasionsByTargetCastleId.get(targetCastleId);
    }

    public void put(KingdomAiInvasion invasion) {
        if (invasion == null || invasion.targetCastleId().isBlank()) {
            return;
        }
        this.invasionsByTargetCastleId.put(invasion.targetCastleId(), invasion);
        this.method_80();
    }

    public void remove(String targetCastleId) {
        if (this.invasionsByTargetCastleId.remove(targetCastleId) != null) {
            this.method_80();
        }
    }

    public long getNextInvasionDayForCastle(String castleId) {
        if (castleId == null || castleId.isBlank()) {
            return 0L;
        }
        return this.nextInvasionDayByCastleId.getOrDefault(castleId, 0L);
    }

    public void setNextInvasionDayForCastle(String castleId, long day) {
        if (castleId == null || castleId.isBlank()) {
            return;
        }
        this.nextInvasionDayByCastleId.put(castleId, Math.max(0L, day));
        this.method_80();
    }

    public long getNextInvasionDayForLord(String lordKey) {
        if (lordKey == null || lordKey.isBlank()) {
            return 0L;
        }
        return this.nextInvasionDayByLordKey.getOrDefault(lordKey, 0L);
    }

    public void setNextInvasionDayForLord(String lordKey, long day) {
        if (lordKey == null || lordKey.isBlank()) {
            return;
        }
        this.nextInvasionDayByLordKey.put(lordKey, Math.max(0L, day));
        this.method_80();
    }

    public long getQueuedManualProgressDays() {
        return this.queuedManualProgressDays;
    }

    public long getProgressOffsetTicks() {
        return this.progressOffsetTicks;
    }

    public void addProgressOffsetTicks(long ticks) {
        if (ticks <= 0L) {
            return;
        }
        this.progressOffsetTicks += ticks;
        this.method_80();
    }

    public int activeInvasionsForLord(String lordKey) {
        if (lordKey == null || lordKey.isBlank()) {
            return 0;
        }
        int count = 0;
        for (KingdomAiInvasion invasion : this.invasionsByTargetCastleId.values()) {
            if (!lordKey.equals(invasion.lordKey())) continue;
            ++count;
        }
        return count;
    }

    public void queueManualProgressDay() {
        ++this.queuedManualProgressDays;
        this.method_80();
    }

    public void consumeQueuedManualProgressDays(long amount) {
        if (amount <= 0L || this.queuedManualProgressDays <= 0L) {
            return;
        }
        this.queuedManualProgressDays = Math.max(0L, this.queuedManualProgressDays - amount);
        this.method_80();
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 invasions = new class_2499();
        for (KingdomAiInvasion kingdomAiInvasion : this.invasionsByTargetCastleId.values()) {
            invasions.add((Object)kingdomAiInvasion.toNbt());
        }
        nbt.method_10566("invasions", (class_2520)invasions);
        class_2487 castleCooldowns = new class_2487();
        for (Map.Entry<String, Long> entry : this.nextInvasionDayByCastleId.entrySet()) {
            castleCooldowns.method_10544(entry.getKey(), entry.getValue().longValue());
        }
        nbt.method_10566("next_invasion_day_by_castle_id", (class_2520)castleCooldowns);
        class_2487 class_24872 = new class_2487();
        for (Map.Entry<String, Long> entry : this.nextInvasionDayByLordKey.entrySet()) {
            class_24872.method_10544(entry.getKey(), entry.getValue().longValue());
        }
        nbt.method_10566("next_invasion_day_by_lord_key", (class_2520)class_24872);
        nbt.method_10544("queued_manual_progress_days", this.queuedManualProgressDays);
        nbt.method_10544("progress_offset_ticks", this.progressOffsetTicks);
        return nbt;
    }

    private static KingdomAiInvasionWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        KingdomAiInvasionWorldState state = new KingdomAiInvasionWorldState();
        class_2499 invasions = nbt.method_10554("invasions", 10);
        for (int index = 0; index < invasions.size(); ++index) {
            KingdomAiInvasion invasion = KingdomAiInvasion.fromNbt(invasions.method_10602(index));
            if (invasion.targetCastleId().isBlank()) continue;
            state.invasionsByTargetCastleId.put(invasion.targetCastleId(), invasion);
        }
        if (nbt.method_10573("next_invasion_day_by_castle_id", 10)) {
            class_2487 castleCooldowns = nbt.method_10562("next_invasion_day_by_castle_id");
            for (String castleId : castleCooldowns.method_10541()) {
                state.nextInvasionDayByCastleId.put(castleId, Math.max(0L, castleCooldowns.method_10537(castleId)));
            }
        }
        if (nbt.method_10573("next_invasion_day_by_lord_key", 10)) {
            class_2487 lordCooldowns = nbt.method_10562("next_invasion_day_by_lord_key");
            for (String lordKey : lordCooldowns.method_10541()) {
                state.nextInvasionDayByLordKey.put(lordKey, Math.max(0L, lordCooldowns.method_10537(lordKey)));
            }
        }
        if (nbt.method_10545("queued_manual_progress_days")) {
            state.queuedManualProgressDays = Math.max(0L, nbt.method_10537("queued_manual_progress_days"));
        }
        if (nbt.method_10545("progress_offset_ticks")) {
            state.progressOffsetTicks = Math.max(0L, nbt.method_10537("progress_offset_ticks"));
        }
        return state;
    }

    public static final class KingdomAiInvasion {
        private final String invadingCastleId;
        private final String targetCastleId;
        private final String attackerLordName;
        private final String attackerPlayerUuid;
        private final String attackerKingdomId;
        private final String attackerHouseId;
        private final String attackerReligionId;
        private final String defenderKingdomId;
        private final String modeId;
        private final String attackingCastleTierId;
        private final String attackerHighestTierId;
        private final int partyStartChunkX;
        private final int partyStartChunkZ;
        private final int partyTargetChunkX;
        private final int partyTargetChunkZ;
        private final long partyDepartureTick;
        private final long partyArrivalTick;
        private final long warmupDurationTicks;
        private long warmupEndsAtTick;
        private boolean partyArrived;
        private boolean liveBattleStarted;
        private int attackerCount;
        private int manualSkipProtectionDays;
        private final LinkedHashSet<UUID> attackerUuids = new LinkedHashSet();

        public KingdomAiInvasion(String invadingCastleId, String targetCastleId, String attackerLordName, String attackerPlayerUuid, String attackerKingdomId, String attackerHouseId, String attackerReligionId, String defenderKingdomId, String modeId, String attackingCastleTierId, String attackerHighestTierId, int partyStartChunkX, int partyStartChunkZ, int partyTargetChunkX, int partyTargetChunkZ, long partyDepartureTick, long partyArrivalTick, long warmupDurationTicks, long warmupEndsAtTick) {
            this.invadingCastleId = invadingCastleId == null ? "" : invadingCastleId;
            this.targetCastleId = targetCastleId == null ? "" : targetCastleId;
            this.attackerLordName = attackerLordName == null ? "" : attackerLordName;
            this.attackerPlayerUuid = attackerPlayerUuid == null ? "" : attackerPlayerUuid;
            this.attackerKingdomId = attackerKingdomId == null ? "" : attackerKingdomId;
            this.attackerHouseId = attackerHouseId == null ? "" : attackerHouseId;
            this.attackerReligionId = attackerReligionId == null ? "" : attackerReligionId;
            this.defenderKingdomId = defenderKingdomId == null ? "" : defenderKingdomId;
            this.modeId = modeId == null ? CastleInvasionMode.RAID.getId() : modeId;
            this.attackingCastleTierId = attackingCastleTierId == null ? "" : attackingCastleTierId;
            this.attackerHighestTierId = attackerHighestTierId == null ? CastleTier.SETTLEMENT.getId() : attackerHighestTierId;
            this.partyStartChunkX = partyStartChunkX;
            this.partyStartChunkZ = partyStartChunkZ;
            this.partyTargetChunkX = partyTargetChunkX;
            this.partyTargetChunkZ = partyTargetChunkZ;
            this.partyDepartureTick = Math.max(0L, partyDepartureTick);
            this.partyArrivalTick = Math.max(this.partyDepartureTick, partyArrivalTick);
            this.warmupDurationTicks = Math.max(20L, warmupDurationTicks);
            this.warmupEndsAtTick = warmupEndsAtTick;
        }

        public String invadingCastleId() {
            return this.invadingCastleId;
        }

        public String targetCastleId() {
            return this.targetCastleId;
        }

        public String attackerLordName() {
            return this.attackerLordName;
        }

        public String attackerPlayerUuid() {
            return this.attackerPlayerUuid;
        }

        public String attackerKingdomId() {
            return this.attackerKingdomId;
        }

        public String attackerHouseId() {
            return this.attackerHouseId;
        }

        public String attackerReligionId() {
            return this.attackerReligionId;
        }

        public String defenderKingdomId() {
            return this.defenderKingdomId;
        }

        public String modeId() {
            return this.modeId;
        }

        public String attackingCastleTierId() {
            return this.attackingCastleTierId;
        }

        public String attackerHighestTierId() {
            return this.attackerHighestTierId;
        }

        public int partyStartChunkX() {
            return this.partyStartChunkX;
        }

        public int partyStartChunkZ() {
            return this.partyStartChunkZ;
        }

        public int partyTargetChunkX() {
            return this.partyTargetChunkX;
        }

        public int partyTargetChunkZ() {
            return this.partyTargetChunkZ;
        }

        public long partyDepartureTick() {
            return this.partyDepartureTick;
        }

        public long partyArrivalTick() {
            return this.partyArrivalTick;
        }

        public boolean partyArrived() {
            return this.partyArrived;
        }

        public void setPartyArrived(boolean partyArrived) {
            this.partyArrived = partyArrived;
        }

        public long warmupEndsAtTick() {
            return this.warmupEndsAtTick;
        }

        public long warmupDurationTicks() {
            return this.warmupDurationTicks;
        }

        public void setWarmupEndsAtTick(long warmupEndsAtTick) {
            this.warmupEndsAtTick = warmupEndsAtTick;
        }

        public boolean liveBattleStarted() {
            return this.liveBattleStarted;
        }

        public void setLiveBattleStarted(boolean liveBattleStarted) {
            this.liveBattleStarted = liveBattleStarted;
        }

        public int attackerCount() {
            return this.attackerCount;
        }

        public void setAttackerCount(int attackerCount) {
            this.attackerCount = Math.max(0, attackerCount);
        }

        public int manualSkipProtectionDays() {
            return this.manualSkipProtectionDays;
        }

        public void setManualSkipProtectionDays(int manualSkipProtectionDays) {
            this.manualSkipProtectionDays = Math.max(0, manualSkipProtectionDays);
        }

        public void consumeManualSkipProtectionDay() {
            if (this.manualSkipProtectionDays > 0) {
                --this.manualSkipProtectionDays;
            }
        }

        public Set<UUID> attackerUuids() {
            return Set.copyOf(this.attackerUuids);
        }

        public void addAttackerUuid(UUID attackerUuid) {
            if (attackerUuid != null) {
                this.attackerUuids.add(attackerUuid);
            }
        }

        public void removeAttackerUuid(UUID attackerUuid) {
            this.attackerUuids.remove(attackerUuid);
        }

        public void clearAttackers() {
            this.attackerUuids.clear();
            this.attackerCount = 0;
        }

        public String lordKey() {
            if (this.attackerKingdomId.isBlank() || this.attackerLordName.isBlank()) {
                return "";
            }
            return this.attackerKingdomId + "|" + this.attackerLordName.trim().toLowerCase(Locale.ROOT);
        }

        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("invading_castle_id", this.invadingCastleId);
            nbt.method_10582("target_castle_id", this.targetCastleId);
            nbt.method_10582("attacker_lord_name", this.attackerLordName);
            nbt.method_10582("attacker_player_uuid", this.attackerPlayerUuid);
            nbt.method_10582("attacker_kingdom_id", this.attackerKingdomId);
            nbt.method_10582("attacker_house_id", this.attackerHouseId);
            nbt.method_10582("attacker_religion_id", this.attackerReligionId);
            nbt.method_10582("defender_kingdom_id", this.defenderKingdomId);
            nbt.method_10582("mode_id", this.modeId);
            nbt.method_10582("attacking_castle_tier_id", this.attackingCastleTierId);
            nbt.method_10582("attacker_highest_tier_id", this.attackerHighestTierId);
            nbt.method_10569("party_start_chunk_x", this.partyStartChunkX);
            nbt.method_10569("party_start_chunk_z", this.partyStartChunkZ);
            nbt.method_10569("party_target_chunk_x", this.partyTargetChunkX);
            nbt.method_10569("party_target_chunk_z", this.partyTargetChunkZ);
            nbt.method_10544("party_departure_tick", this.partyDepartureTick);
            nbt.method_10544("party_arrival_tick", this.partyArrivalTick);
            nbt.method_10544("warmup_duration_ticks", this.warmupDurationTicks);
            nbt.method_10544("warmup_ends_at_tick", this.warmupEndsAtTick);
            nbt.method_10556("party_arrived", this.partyArrived);
            nbt.method_10556("live_battle_started", this.liveBattleStarted);
            nbt.method_10569("attacker_count", this.attackerCount);
            nbt.method_10569("manual_skip_protection_days", this.manualSkipProtectionDays);
            class_2499 attackers = new class_2499();
            for (UUID uuid : this.attackerUuids) {
                attackers.add((Object)class_2519.method_23256((String)uuid.toString()));
            }
            nbt.method_10566("attacker_uuids", (class_2520)attackers);
            return nbt;
        }

        public static KingdomAiInvasion fromNbt(class_2487 nbt) {
            KingdomAiInvasion invasion = new KingdomAiInvasion(nbt.method_10558("invading_castle_id"), nbt.method_10558("target_castle_id"), nbt.method_10558("attacker_lord_name"), nbt.method_10558("attacker_player_uuid"), nbt.method_10558("attacker_kingdom_id"), nbt.method_10558("attacker_house_id"), nbt.method_10558("attacker_religion_id"), nbt.method_10558("defender_kingdom_id"), nbt.method_10558("mode_id"), nbt.method_10558("attacking_castle_tier_id"), nbt.method_10558("attacker_highest_tier_id"), nbt.method_10545("party_start_chunk_x") ? nbt.method_10550("party_start_chunk_x") : 0, nbt.method_10545("party_start_chunk_z") ? nbt.method_10550("party_start_chunk_z") : 0, nbt.method_10545("party_target_chunk_x") ? nbt.method_10550("party_target_chunk_x") : 0, nbt.method_10545("party_target_chunk_z") ? nbt.method_10550("party_target_chunk_z") : 0, nbt.method_10545("party_departure_tick") ? nbt.method_10537("party_departure_tick") : 0L, nbt.method_10545("party_arrival_tick") ? nbt.method_10537("party_arrival_tick") : nbt.method_10537("warmup_ends_at_tick"), nbt.method_10545("warmup_duration_ticks") ? nbt.method_10537("warmup_duration_ticks") : 1200L, nbt.method_10537("warmup_ends_at_tick"));
            invasion.setPartyArrived(nbt.method_10577("party_arrived"));
            invasion.setLiveBattleStarted(nbt.method_10577("live_battle_started"));
            invasion.setAttackerCount(nbt.method_10550("attacker_count"));
            if (nbt.method_10545("manual_skip_protection_days")) {
                invasion.setManualSkipProtectionDays(nbt.method_10550("manual_skip_protection_days"));
            }
            class_2499 attackers = nbt.method_10554("attacker_uuids", 8);
            for (int index = 0; index < attackers.size(); ++index) {
                try {
                    invasion.addAttackerUuid(UUID.fromString(attackers.method_10608(index)));
                    continue;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    // empty catch block
                }
            }
            return invasion;
        }
    }
}

