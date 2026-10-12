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
package trep.got.castle;

import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;

public final class PendingCastleOwnershipWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.pending_castle_ownership";
    private static final class_18.class_8645<PendingCastleOwnershipWorldState> TYPE = new class_18.class_8645(PendingCastleOwnershipWorldState::new, PendingCastleOwnershipWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, PendingCastleOwnership> ownershipByCastleId = new LinkedHashMap<String, PendingCastleOwnership>();
    private long revision;

    public static PendingCastleOwnershipWorldState get(class_3218 world) {
        return (PendingCastleOwnershipWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public PendingCastleOwnership get(String castleId) {
        return this.ownershipByCastleId.get(castleId);
    }

    public long getRevision() {
        return this.revision;
    }

    public void put(PendingCastleOwnership ownership) {
        if (ownership == null || ownership.castleId().isBlank()) {
            return;
        }
        if (ownership.equals(this.ownershipByCastleId.get(ownership.castleId()))) {
            return;
        }
        this.ownershipByCastleId.put(ownership.castleId(), ownership);
        ++this.revision;
        this.method_80();
    }

    public void remove(String castleId) {
        if (this.ownershipByCastleId.remove(castleId) != null) {
            ++this.revision;
            this.method_80();
        }
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 entries = new class_2499();
        for (PendingCastleOwnership ownership : this.ownershipByCastleId.values()) {
            entries.add((Object)ownership.toNbt());
        }
        nbt.method_10566("entries", (class_2520)entries);
        return nbt;
    }

    private static PendingCastleOwnershipWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        PendingCastleOwnershipWorldState state = new PendingCastleOwnershipWorldState();
        class_2499 entries = nbt.method_10554("entries", 10);
        for (int index = 0; index < entries.size(); ++index) {
            PendingCastleOwnership ownership = PendingCastleOwnership.fromNbt(entries.method_10602(index));
            if (ownership.castleId().isBlank()) continue;
            state.ownershipByCastleId.put(ownership.castleId(), ownership);
        }
        return state;
    }

    public record PendingCastleOwnership(String castleId, String ownerPlayerUuid, String ownerKingdomId, String ownerHouseId, String ownerReligionId, String lordName, long raidEndsAtTick) {
        public PendingCastleOwnership {
            castleId = castleId == null ? "" : castleId;
            ownerPlayerUuid = ownerPlayerUuid == null ? "" : ownerPlayerUuid;
            ownerKingdomId = ownerKingdomId == null ? "" : ownerKingdomId;
            ownerHouseId = ownerHouseId == null ? "" : ownerHouseId;
            ownerReligionId = ownerReligionId == null ? "" : ownerReligionId;
            lordName = lordName == null ? "" : lordName;
            raidEndsAtTick = Math.max(0L, raidEndsAtTick);
        }

        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("castle_id", this.castleId);
            nbt.method_10582("owner_player_uuid", this.ownerPlayerUuid);
            nbt.method_10582("owner_kingdom_id", this.ownerKingdomId);
            nbt.method_10582("owner_house_id", this.ownerHouseId);
            nbt.method_10582("owner_religion_id", this.ownerReligionId);
            nbt.method_10582("lord_name", this.lordName);
            nbt.method_10544("raid_ends_at_tick", this.raidEndsAtTick);
            return nbt;
        }

        public static PendingCastleOwnership fromNbt(class_2487 nbt) {
            return new PendingCastleOwnership(nbt.method_10558("castle_id"), nbt.method_10558("owner_player_uuid"), nbt.method_10558("owner_kingdom_id"), nbt.method_10558("owner_house_id"), nbt.method_10558("owner_religion_id"), nbt.method_10558("lord_name"), nbt.method_10537("raid_ends_at_tick"));
        }
    }
}

