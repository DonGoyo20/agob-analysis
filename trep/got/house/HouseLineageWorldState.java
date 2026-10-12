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
package trep.got.house;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
import trep.got.castle.HouseLordWorldState;
import trep.got.character.NpcAppearanceProfile;
import trep.got.character.NpcAttributeProfile;

public final class HouseLineageWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.house_lineages";
    private static final class_18.class_8645<HouseLineageWorldState> TYPE = new class_18.class_8645(HouseLineageWorldState::new, HouseLineageWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, HouseLineageProfile> lineagesByHouseKey = new LinkedHashMap<String, HouseLineageProfile>();
    private long revision;

    public static HouseLineageWorldState get(class_3218 world) {
        return (HouseLineageWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public HouseLineageProfile getLineage(String kingdomId, String houseId) {
        String key = HouseLordWorldState.houseKey(kingdomId, houseId);
        return key.isBlank() ? null : this.lineagesByHouseKey.get(key);
    }

    public List<HouseLineageProfile> getAllLineages() {
        return List.copyOf(this.lineagesByHouseKey.values());
    }

    public long getRevision() {
        return this.revision;
    }

    public void putLineage(HouseLineageProfile profile) {
        if (profile == null || profile.kingdomId().isBlank() || profile.houseId().isBlank()) {
            return;
        }
        String key = HouseLordWorldState.houseKey(profile.kingdomId(), profile.houseId());
        if (profile.equals(this.lineagesByHouseKey.get(key))) {
            return;
        }
        this.lineagesByHouseKey.put(key, profile);
        ++this.revision;
        this.method_80();
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 list = new class_2499();
        for (HouseLineageProfile profile : this.lineagesByHouseKey.values()) {
            list.add((Object)profile.toNbt());
        }
        nbt.method_10566("lineages", (class_2520)list);
        return nbt;
    }

    private static HouseLineageWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        HouseLineageWorldState state = new HouseLineageWorldState();
        class_2499 list = nbt.method_10554("lineages", 10);
        for (int index = 0; index < list.size(); ++index) {
            HouseLineageProfile profile = HouseLineageProfile.fromNbt(list.method_10602(index));
            if (profile == null || profile.kingdomId().isBlank() || profile.houseId().isBlank()) continue;
            state.lineagesByHouseKey.put(HouseLordWorldState.houseKey(profile.kingdomId(), profile.houseId()), profile);
        }
        return state;
    }

    public record HouseLineageProfile(String kingdomId, String houseId, String leaderLordId, List<LordHeritageProfile> lords, List<ChildProfile> children) {
        public HouseLineageProfile {
            kingdomId = kingdomId == null ? "" : kingdomId;
            houseId = houseId == null ? "" : houseId;
            leaderLordId = leaderLordId == null ? "" : leaderLordId;
            lords = lords == null ? List.of() : List.copyOf(lords);
            children = children == null ? List.of() : List.copyOf(children);
        }

        public LordHeritageProfile lord(String lordId) {
            if (lordId == null || lordId.isBlank()) {
                return null;
            }
            for (LordHeritageProfile lord : this.lords) {
                if (!lordId.equals(lord.lordId())) continue;
                return lord;
            }
            return null;
        }

        private class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("kingdom_id", this.kingdomId);
            nbt.method_10582("house_id", this.houseId);
            nbt.method_10582("leader_lord_id", this.leaderLordId);
            class_2499 lordList = new class_2499();
            for (LordHeritageProfile lord : this.lords) {
                lordList.add((Object)lord.toNbt());
            }
            nbt.method_10566("lords", (class_2520)lordList);
            class_2499 childList = new class_2499();
            for (ChildProfile child : this.children) {
                childList.add((Object)child.toNbt());
            }
            nbt.method_10566("children", (class_2520)childList);
            return nbt;
        }

        private static HouseLineageProfile fromNbt(class_2487 nbt) {
            if (nbt == null || nbt.method_33133()) {
                return null;
            }
            ArrayList<LordHeritageProfile> lords = new ArrayList<LordHeritageProfile>();
            class_2499 lordList = nbt.method_10554("lords", 10);
            for (int index = 0; index < lordList.size(); ++index) {
                LordHeritageProfile lord = LordHeritageProfile.fromNbt(lordList.method_10602(index));
                if (lord == null || lord.lordId().isBlank()) continue;
                lords.add(lord);
            }
            ArrayList<ChildProfile> children = new ArrayList<ChildProfile>();
            class_2499 childList = nbt.method_10554("children", 10);
            for (int index = 0; index < childList.size(); ++index) {
                ChildProfile child = ChildProfile.fromNbt(childList.method_10602(index));
                if (child == null || child.id().isBlank()) continue;
                children.add(child);
            }
            return new HouseLineageProfile(nbt.method_10558("kingdom_id"), nbt.method_10558("house_id"), nbt.method_10558("leader_lord_id"), lords, children);
        }
    }

    public record ChildProfile(String id, UUID uuid, String kingdomId, String houseId, String displayName, String parentLordId, String parentName, String otherParentId, String otherParentName, long bornDay, String castleId, NpcAttributeProfile attributes, NpcAppearanceProfile appearance) {
        public ChildProfile {
            id = id == null ? "" : id;
            kingdomId = kingdomId == null ? "" : kingdomId;
            houseId = houseId == null ? "" : houseId;
            displayName = displayName == null ? "" : displayName;
            parentLordId = parentLordId == null ? "" : parentLordId;
            parentName = parentName == null ? "" : parentName;
            otherParentId = otherParentId == null ? "" : otherParentId;
            otherParentName = otherParentName == null ? "" : otherParentName;
            castleId = castleId == null ? "" : castleId;
            uuid = uuid == null ? UUID.nameUUIDFromBytes(("house_lineage_child:" + id).getBytes(StandardCharsets.UTF_8)) : uuid;
            attributes = attributes == null ? NpcAttributeProfile.EMPTY : attributes;
            appearance = appearance == null ? NpcAppearanceProfile.EMPTY : appearance;
        }

        private class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("id", this.id);
            nbt.method_25927("uuid", this.uuid);
            nbt.method_10582("kingdom_id", this.kingdomId);
            nbt.method_10582("house_id", this.houseId);
            nbt.method_10582("display_name", this.displayName);
            nbt.method_10582("parent_lord_id", this.parentLordId);
            nbt.method_10582("parent_name", this.parentName);
            nbt.method_10582("other_parent_id", this.otherParentId);
            nbt.method_10582("other_parent_name", this.otherParentName);
            nbt.method_10544("born_day", this.bornDay);
            nbt.method_10582("castle_id", this.castleId);
            nbt.method_10566("attributes", (class_2520)this.attributes.toNbt());
            nbt.method_10566("appearance", (class_2520)this.appearance.toNbt());
            return nbt;
        }

        private static ChildProfile fromNbt(class_2487 nbt) {
            if (nbt == null || nbt.method_33133()) {
                return null;
            }
            return new ChildProfile(nbt.method_10558("id"), nbt.method_25928("uuid") ? nbt.method_25926("uuid") : null, nbt.method_10558("kingdom_id"), nbt.method_10558("house_id"), nbt.method_10558("display_name"), nbt.method_10558("parent_lord_id"), nbt.method_10558("parent_name"), nbt.method_10558("other_parent_id"), nbt.method_10558("other_parent_name"), nbt.method_10537("born_day"), nbt.method_10558("castle_id"), nbt.method_10573("attributes", 10) ? NpcAttributeProfile.fromNbt(nbt.method_10562("attributes")) : NpcAttributeProfile.EMPTY, nbt.method_10573("appearance", 10) ? NpcAppearanceProfile.fromNbt(nbt.method_10562("appearance")) : NpcAppearanceProfile.EMPTY);
        }
    }

    public record LordHeritageProfile(String lordId, String gender, String heritage, String relatedLordId, long bornDay, NpcAppearanceProfile appearance) {
        public LordHeritageProfile {
            lordId = lordId == null ? "" : lordId;
            gender = new NpcAppearanceProfile(gender, 1, 1, 0).gender();
            heritage = heritage == null ? "" : heritage;
            relatedLordId = relatedLordId == null ? "" : relatedLordId;
            appearance = appearance == null ? NpcAppearanceProfile.EMPTY : appearance;
        }

        private class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("lord_id", this.lordId);
            nbt.method_10582("gender", this.gender);
            nbt.method_10582("heritage", this.heritage);
            nbt.method_10582("related_lord_id", this.relatedLordId);
            nbt.method_10544("born_day", this.bornDay);
            nbt.method_10566("appearance", (class_2520)this.appearance.toNbt());
            return nbt;
        }

        private static LordHeritageProfile fromNbt(class_2487 nbt) {
            if (nbt == null || nbt.method_33133()) {
                return null;
            }
            return new LordHeritageProfile(nbt.method_10558("lord_id"), nbt.method_10558("gender"), nbt.method_10558("heritage"), nbt.method_10558("related_lord_id"), nbt.method_10537("born_day"), nbt.method_10573("appearance", 10) ? NpcAppearanceProfile.fromNbt(nbt.method_10562("appearance")) : NpcAppearanceProfile.EMPTY);
        }
    }
}

