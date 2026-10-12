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
package trep.got.family;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;
import trep.got.character.NpcAttributeProfile;
import trep.got.registry.GotDynastyNpcs;
import trep.got.registry.GotGameRules;

public final class DynastyNpcWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.dynasty_npcs";
    private static final class_18.class_8645<DynastyNpcWorldState> TYPE = new class_18.class_8645(DynastyNpcWorldState::new, DynastyNpcWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, FamilyMemberProfile> byId = new LinkedHashMap<String, FamilyMemberProfile>();
    private long trackedDay;
    private long lastObservedTimeOfDay;
    private boolean lastObservedAffectedByTimeSkip = true;
    private boolean initialized;
    private long revision;

    public static DynastyNpcWorldState get(class_3218 world) {
        return (DynastyNpcWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public List<FamilyMemberProfile> all() {
        return List.copyOf(this.byId.values());
    }

    public FamilyMemberProfile get(String memberId) {
        return memberId == null ? null : this.byId.get(memberId);
    }

    public long getRevision() {
        return this.revision;
    }

    public void put(FamilyMemberProfile profile) {
        if (profile == null || profile.id().isBlank()) {
            return;
        }
        if (profile.equals(this.byId.get(profile.id()))) {
            return;
        }
        this.byId.put(profile.id(), profile);
        ++this.revision;
        this.method_80();
    }

    public void remove(String memberId) {
        if (memberId == null || memberId.isBlank()) {
            return;
        }
        if (this.byId.remove(memberId) != null) {
            ++this.revision;
            this.method_80();
        }
    }

    public List<FamilyMemberProfile> getByOwner(String ownerPlayerUuid) {
        if (ownerPlayerUuid == null || ownerPlayerUuid.isBlank()) {
            return List.of();
        }
        return this.byId.values().stream().filter(profile -> ownerPlayerUuid.equals(profile.ownerPlayerUuid())).toList();
    }

    public long getTrackedDay() {
        return Math.max(0L, this.trackedDay);
    }

    public boolean tick(long worldTime, long currentTimeOfDay, boolean affectedByTimeSkip) {
        long observedTime;
        long l = observedTime = affectedByTimeSkip ? currentTimeOfDay : worldTime;
        if (!this.initialized) {
            this.trackedDay = affectedByTimeSkip ? GotGameRules.skipAwareDay(worldTime, currentTimeOfDay) : GotGameRules.regularDay(worldTime);
            this.lastObservedTimeOfDay = observedTime;
            this.lastObservedAffectedByTimeSkip = affectedByTimeSkip;
            this.initialized = true;
            ++this.revision;
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
        long dayIncrease = 0L;
        if (currentDay > previousDay) {
            dayIncrease = currentDay - previousDay;
        } else if (affectedByTimeSkip && DynastyNpcWorldState.normalizedTimeOfDay(currentTimeOfDay) < DynastyNpcWorldState.normalizedTimeOfDay(previousTimeOfDay)) {
            dayIncrease = 1L;
        }
        if (dayIncrease > 0L) {
            this.trackedDay += dayIncrease;
            ++this.revision;
            this.method_80();
            return true;
        }
        return false;
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        nbt.method_10544("tracked_day", this.trackedDay);
        nbt.method_10544("last_observed_time_of_day", this.lastObservedTimeOfDay);
        nbt.method_10556("last_observed_affected_by_time_skip", this.lastObservedAffectedByTimeSkip);
        nbt.method_10556("initialized", this.initialized);
        class_2499 list = new class_2499();
        for (FamilyMemberProfile profile : this.byId.values()) {
            list.add((Object)profile.toNbt());
        }
        nbt.method_10566("members", (class_2520)list);
        return nbt;
    }

    private static DynastyNpcWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        DynastyNpcWorldState state = new DynastyNpcWorldState();
        state.trackedDay = nbt.method_10537("tracked_day");
        state.lastObservedTimeOfDay = nbt.method_10537("last_observed_time_of_day");
        if (nbt.method_10573("last_observed_affected_by_time_skip", 1)) {
            state.lastObservedAffectedByTimeSkip = nbt.method_10577("last_observed_affected_by_time_skip");
        }
        state.initialized = nbt.method_10577("initialized");
        class_2499 members = nbt.method_10554("members", 10);
        for (int index = 0; index < members.size(); ++index) {
            FamilyMemberProfile profile = FamilyMemberProfile.fromNbt(members.method_10602(index));
            if (profile.id().isBlank()) continue;
            state.byId.put(profile.id(), profile);
        }
        return state;
    }

    private static long normalizedTimeOfDay(long timeOfDay) {
        long normalized = timeOfDay % 24000L;
        return normalized < 0L ? normalized + 24000L : normalized;
    }

    public record FamilyMemberProfile(String id, UUID entityUuid, String ownerPlayerUuid, String ownerPlayerName, String otherParentName, String boundMarriedPlayerUuid, String boundMarriedPlayerName, String kingdomId, String houseId, String displayName, long bornDay, String castleId, GotDynastyNpcs.FamilyStage stage, boolean ownerSmallArms, long summonCooldownEndsAtMs, NpcAttributeProfile attributes) {
        public FamilyMemberProfile {
            id = id == null ? "" : id;
            ownerPlayerUuid = ownerPlayerUuid == null ? "" : ownerPlayerUuid;
            ownerPlayerName = ownerPlayerName == null ? "" : ownerPlayerName;
            otherParentName = otherParentName == null ? "" : otherParentName;
            boundMarriedPlayerUuid = boundMarriedPlayerUuid == null ? "" : boundMarriedPlayerUuid;
            boundMarriedPlayerName = boundMarriedPlayerName == null ? "" : boundMarriedPlayerName;
            kingdomId = kingdomId == null ? "" : kingdomId;
            houseId = houseId == null ? "" : houseId;
            displayName = displayName == null ? "" : displayName;
            castleId = castleId == null ? "" : castleId;
            stage = stage == null ? GotDynastyNpcs.FamilyStage.CHILD : stage;
            summonCooldownEndsAtMs = Math.max(0L, summonCooldownEndsAtMs);
            attributes = attributes == null ? NpcAttributeProfile.EMPTY : attributes;
            entityUuid = entityUuid == null ? FamilyMemberProfile.stableUuid(id) : entityUuid;
            bornDay = Math.max(0L, bornDay);
        }

        public FamilyMemberProfile withDisplayName(String displayName) {
            return new FamilyMemberProfile(this.id, this.entityUuid, this.ownerPlayerUuid, this.ownerPlayerName, this.otherParentName, this.boundMarriedPlayerUuid, this.boundMarriedPlayerName, this.kingdomId, this.houseId, displayName, this.bornDay, this.castleId, this.stage, this.ownerSmallArms, this.summonCooldownEndsAtMs, this.attributes);
        }

        public FamilyMemberProfile withCastleId(String castleId) {
            return new FamilyMemberProfile(this.id, this.entityUuid, this.ownerPlayerUuid, this.ownerPlayerName, this.otherParentName, this.boundMarriedPlayerUuid, this.boundMarriedPlayerName, this.kingdomId, this.houseId, this.displayName, this.bornDay, castleId, this.stage, this.ownerSmallArms, this.summonCooldownEndsAtMs, this.attributes);
        }

        public FamilyMemberProfile withStage(GotDynastyNpcs.FamilyStage stage) {
            return new FamilyMemberProfile(this.id, this.entityUuid, this.ownerPlayerUuid, this.ownerPlayerName, this.otherParentName, this.boundMarriedPlayerUuid, this.boundMarriedPlayerName, this.kingdomId, this.houseId, this.displayName, this.bornDay, this.castleId, stage, this.ownerSmallArms, this.summonCooldownEndsAtMs, this.attributes);
        }

        public FamilyMemberProfile withHouseIdentity(String houseId, String displayName, String ownerPlayerName) {
            return new FamilyMemberProfile(this.id, this.entityUuid, this.ownerPlayerUuid, ownerPlayerName, this.otherParentName, this.boundMarriedPlayerUuid, this.boundMarriedPlayerName, this.kingdomId, houseId, displayName, this.bornDay, this.castleId, this.stage, this.ownerSmallArms, this.summonCooldownEndsAtMs, this.attributes);
        }

        public FamilyMemberProfile withKingdomHouseIdentity(String kingdomId, String houseId, String displayName, String ownerPlayerName) {
            return new FamilyMemberProfile(this.id, this.entityUuid, this.ownerPlayerUuid, ownerPlayerName, this.otherParentName, this.boundMarriedPlayerUuid, this.boundMarriedPlayerName, kingdomId, houseId, displayName, this.bornDay, this.castleId, this.stage, this.ownerSmallArms, this.summonCooldownEndsAtMs, this.attributes);
        }

        public FamilyMemberProfile withSummonCooldownEndsAtMs(long summonCooldownEndsAtMs) {
            return new FamilyMemberProfile(this.id, this.entityUuid, this.ownerPlayerUuid, this.ownerPlayerName, this.otherParentName, this.boundMarriedPlayerUuid, this.boundMarriedPlayerName, this.kingdomId, this.houseId, this.displayName, this.bornDay, this.castleId, this.stage, this.ownerSmallArms, summonCooldownEndsAtMs, this.attributes);
        }

        public FamilyMemberProfile withMarriageBinding(String boundMarriedPlayerUuid, String boundMarriedPlayerName) {
            return new FamilyMemberProfile(this.id, this.entityUuid, this.ownerPlayerUuid, this.ownerPlayerName, this.otherParentName, boundMarriedPlayerUuid, boundMarriedPlayerName, this.kingdomId, this.houseId, this.displayName, this.bornDay, this.castleId, this.stage, this.ownerSmallArms, this.summonCooldownEndsAtMs, this.attributes);
        }

        public FamilyMemberProfile clearMarriageBinding() {
            return this.withMarriageBinding("", "");
        }

        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("id", this.id);
            nbt.method_25927("uuid", this.entityUuid);
            nbt.method_10582("owner_player_uuid", this.ownerPlayerUuid);
            nbt.method_10582("owner_player_name", this.ownerPlayerName);
            nbt.method_10582("other_parent_name", this.otherParentName);
            nbt.method_10582("bound_married_player_uuid", this.boundMarriedPlayerUuid);
            nbt.method_10582("bound_married_player_name", this.boundMarriedPlayerName);
            nbt.method_10582("kingdom_id", this.kingdomId);
            nbt.method_10582("house_id", this.houseId);
            nbt.method_10582("display_name", this.displayName);
            nbt.method_10544("born_day", this.bornDay);
            nbt.method_10582("castle_id", this.castleId);
            nbt.method_10582("stage", this.stage.id());
            nbt.method_10556("owner_small_arms", this.ownerSmallArms);
            nbt.method_10544("summon_cooldown_ends_at_ms", this.summonCooldownEndsAtMs);
            nbt.method_10566("attributes", (class_2520)this.attributes.toNbt());
            return nbt;
        }

        public static FamilyMemberProfile fromNbt(class_2487 nbt) {
            return new FamilyMemberProfile(nbt.method_10558("id"), nbt.method_25928("uuid") ? nbt.method_25926("uuid") : FamilyMemberProfile.stableUuid(nbt.method_10558("id")), nbt.method_10558("owner_player_uuid"), nbt.method_10558("owner_player_name"), nbt.method_10558("other_parent_name"), nbt.method_10558("bound_married_player_uuid"), nbt.method_10558("bound_married_player_name"), nbt.method_10558("kingdom_id"), nbt.method_10558("house_id"), nbt.method_10558("display_name"), nbt.method_10537("born_day"), nbt.method_10558("castle_id"), GotDynastyNpcs.FamilyStage.byId(nbt.method_10558("stage")), nbt.method_10577("owner_small_arms"), nbt.method_10537("summon_cooldown_ends_at_ms"), nbt.method_10573("attributes", 10) ? NpcAttributeProfile.fromNbt(nbt.method_10562("attributes")) : NpcAttributeProfile.EMPTY);
        }

        private static UUID stableUuid(String id) {
            return UUID.nameUUIDFromBytes(("dynasty_member:" + (id == null ? "" : id)).getBytes(StandardCharsets.UTF_8));
        }
    }
}

