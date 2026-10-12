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

public final class KingdomLeaderWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.kingdom_leaders";
    private static final class_18.class_8645<KingdomLeaderWorldState> TYPE = new class_18.class_8645(KingdomLeaderWorldState::new, KingdomLeaderWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, KingdomLeaderProfile> leadersByKingdomId = new LinkedHashMap<String, KingdomLeaderProfile>();
    private long revision;

    public static KingdomLeaderWorldState get(class_3218 world) {
        return (KingdomLeaderWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public KingdomLeaderProfile getLeader(String kingdomId) {
        return kingdomId == null ? null : this.leadersByKingdomId.get(kingdomId);
    }

    public long getRevision() {
        return this.revision;
    }

    public void putLeader(KingdomLeaderProfile profile) {
        if (profile == null || profile.kingdomId().isBlank()) {
            return;
        }
        if (profile.equals(this.leadersByKingdomId.get(profile.kingdomId()))) {
            return;
        }
        this.leadersByKingdomId.put(profile.kingdomId(), profile);
        ++this.revision;
        this.method_80();
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 leaders = new class_2499();
        for (KingdomLeaderProfile profile : this.leadersByKingdomId.values()) {
            leaders.add((Object)profile.toNbt());
        }
        nbt.method_10566("leaders", (class_2520)leaders);
        return nbt;
    }

    private static KingdomLeaderWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        KingdomLeaderWorldState state = new KingdomLeaderWorldState();
        class_2499 leaders = nbt.method_10554("leaders", 10);
        for (int index = 0; index < leaders.size(); ++index) {
            KingdomLeaderProfile profile = KingdomLeaderProfile.fromNbt(leaders.method_10602(index));
            if (profile.kingdomId().isBlank()) continue;
            state.leadersByKingdomId.put(profile.kingdomId(), profile);
        }
        return state;
    }

    public record KingdomLeaderProfile(String kingdomId, String houseId, String lordId, String lordName, String castleId) {
        public KingdomLeaderProfile {
            kingdomId = kingdomId == null ? "" : kingdomId;
            houseId = houseId == null ? "" : houseId;
            lordId = lordId == null ? "" : lordId;
            lordName = lordName == null ? "" : lordName;
            castleId = castleId == null ? "" : castleId;
        }

        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("kingdom_id", this.kingdomId);
            nbt.method_10582("house_id", this.houseId);
            nbt.method_10582("lord_id", this.lordId);
            nbt.method_10582("lord_name", this.lordName);
            nbt.method_10582("castle_id", this.castleId);
            return nbt;
        }

        public static KingdomLeaderProfile fromNbt(class_2487 nbt) {
            return new KingdomLeaderProfile(nbt.method_10558("kingdom_id"), nbt.method_10558("house_id"), nbt.method_10558("lord_id"), nbt.method_10558("lord_name"), nbt.method_10558("castle_id"));
        }
    }
}

