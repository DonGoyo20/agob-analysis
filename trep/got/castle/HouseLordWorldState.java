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
import trep.got.character.NpcAppearanceProfile;
import trep.got.character.NpcAttributeProfile;

public final class HouseLordWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.house_lords";
    private static final class_18.class_8645<HouseLordWorldState> TYPE = new class_18.class_8645(HouseLordWorldState::new, HouseLordWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, List<HouseLordProfile>> lordsByHouseKey = new LinkedHashMap<String, List<HouseLordProfile>>();
    private final Map<String, Integer> lordCapsByHouseKey = new LinkedHashMap<String, Integer>();
    private long revision;

    public static HouseLordWorldState get(class_3218 world) {
        return (HouseLordWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public List<HouseLordProfile> getLords(String kingdomId, String houseId) {
        String key = HouseLordWorldState.houseKey(kingdomId, houseId);
        if (key.isBlank()) {
            return List.of();
        }
        return List.copyOf(this.lordsByHouseKey.getOrDefault(key, List.of()));
    }

    public int getLordCap(String kingdomId, String houseId, int fallback) {
        String key = HouseLordWorldState.houseKey(kingdomId, houseId);
        if (key.isBlank()) {
            return Math.max(0, fallback);
        }
        return Math.max(0, this.lordCapsByHouseKey.getOrDefault(key, fallback));
    }

    public long getRevision() {
        return this.revision;
    }

    public void setLordCap(String kingdomId, String houseId, int cap) {
        String key = HouseLordWorldState.houseKey(kingdomId, houseId);
        if (key.isBlank()) {
            return;
        }
        int normalizedCap = Math.max(0, cap);
        if (this.lordCapsByHouseKey.getOrDefault(key, -1) == normalizedCap) {
            return;
        }
        this.lordCapsByHouseKey.put(key, normalizedCap);
        ++this.revision;
        this.method_80();
    }

    public void putLords(String kingdomId, String houseId, List<HouseLordProfile> lords) {
        boolean capChanged;
        String key = HouseLordWorldState.houseKey(kingdomId, houseId);
        if (key.isBlank()) {
            return;
        }
        if (lords == null || lords.isEmpty()) {
            if (this.lordsByHouseKey.remove(key) != null) {
                ++this.revision;
                this.method_80();
            }
            return;
        }
        List<HouseLordProfile> copiedLords = List.copyOf(lords);
        boolean lordsChanged = !copiedLords.equals(this.lordsByHouseKey.get(key));
        boolean bl = capChanged = !this.lordCapsByHouseKey.containsKey(key);
        if (!lordsChanged && !capChanged) {
            return;
        }
        this.lordsByHouseKey.put(key, copiedLords);
        this.lordCapsByHouseKey.putIfAbsent(key, copiedLords.size());
        ++this.revision;
        this.method_80();
    }

    public HouseLordProfile findByName(String kingdomId, String houseId, String displayName) {
        if (displayName == null || displayName.isBlank()) {
            return null;
        }
        for (HouseLordProfile lord : this.getLords(kingdomId, houseId)) {
            if (!displayName.equals(lord.displayName())) continue;
            return lord;
        }
        return null;
    }

    public static String houseKey(String kingdomId, String houseId) {
        if (kingdomId == null || kingdomId.isBlank() || houseId == null || houseId.isBlank()) {
            return "";
        }
        return kingdomId + "|" + houseId;
    }

    public static UUID stableUuid(String kingdomId, String houseId, int index) {
        return UUID.nameUUIDFromBytes(("house_lord:" + kingdomId + ":" + houseId + ":" + index).getBytes(StandardCharsets.UTF_8));
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 houseList = new class_2499();
        for (Map.Entry<String, List<HouseLordProfile>> entry : this.lordsByHouseKey.entrySet()) {
            class_2487 houseNbt = new class_2487();
            houseNbt.method_10582("house_key", entry.getKey());
            houseNbt.method_10569("lord_cap", this.lordCapsByHouseKey.getOrDefault(entry.getKey(), entry.getValue().size()).intValue());
            class_2499 lordList = new class_2499();
            for (HouseLordProfile lord : entry.getValue()) {
                lordList.add((Object)lord.toNbt());
            }
            houseNbt.method_10566("lords", (class_2520)lordList);
            houseList.add((Object)houseNbt);
        }
        nbt.method_10566("houses", (class_2520)houseList);
        return nbt;
    }

    private static HouseLordWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        HouseLordWorldState state = new HouseLordWorldState();
        class_2499 houseList = nbt.method_10554("houses", 10);
        for (int houseIndex = 0; houseIndex < houseList.size(); ++houseIndex) {
            class_2487 houseNbt = houseList.method_10602(houseIndex);
            String houseKey = houseNbt.method_10558("house_key");
            if (houseKey.isBlank()) continue;
            int lordCap = houseNbt.method_10573("lord_cap", 3) ? houseNbt.method_10550("lord_cap") : 0;
            ArrayList<HouseLordProfile> lords = new ArrayList<HouseLordProfile>();
            class_2499 lordList = houseNbt.method_10554("lords", 10);
            for (int lordIndex = 0; lordIndex < lordList.size(); ++lordIndex) {
                lords.add(HouseLordProfile.fromNbt(lordList.method_10602(lordIndex)));
            }
            if (lords.isEmpty()) continue;
            state.lordsByHouseKey.put(houseKey, List.copyOf(lords));
            state.lordCapsByHouseKey.put(houseKey, Math.max(lordCap, lords.size()));
        }
        return state;
    }

    public record HouseLordProfile(String id, UUID uuid, String kingdomId, String houseId, String displayName, boolean leader, int index, NpcAttributeProfile attributes, String spouseId, UUID spouseUuid, String spouseDisplayName, NpcAttributeProfile spouseAttributes, NpcAppearanceProfile spouseAppearance) {
        public HouseLordProfile {
            id = id == null ? "" : id;
            kingdomId = kingdomId == null ? "" : kingdomId;
            houseId = houseId == null ? "" : houseId;
            displayName = displayName == null ? "" : displayName;
            index = Math.max(0, index);
            uuid = uuid == null ? HouseLordWorldState.stableUuid(kingdomId, houseId, index) : uuid;
            attributes = attributes == null ? NpcAttributeProfile.EMPTY : attributes;
            spouseId = spouseId == null ? "" : spouseId;
            spouseDisplayName = spouseDisplayName == null ? "" : spouseDisplayName;
            spouseAttributes = spouseAttributes == null ? NpcAttributeProfile.EMPTY : spouseAttributes;
            spouseAppearance = spouseAppearance == null ? NpcAppearanceProfile.EMPTY : spouseAppearance;
        }

        public HouseLordProfile(String id, UUID uuid, String kingdomId, String houseId, String displayName, boolean leader, int index) {
            this(id, uuid, kingdomId, houseId, displayName, leader, index, NpcAttributeProfile.EMPTY, "", null, "", NpcAttributeProfile.EMPTY, NpcAppearanceProfile.EMPTY);
        }

        public boolean hasSpouse() {
            return !this.spouseId.isBlank() && !this.spouseDisplayName.isBlank();
        }

        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("id", this.id);
            nbt.method_25927("uuid", this.uuid);
            nbt.method_10582("kingdom_id", this.kingdomId);
            nbt.method_10582("house_id", this.houseId);
            nbt.method_10582("display_name", this.displayName);
            nbt.method_10556("leader", this.leader);
            nbt.method_10569("index", this.index);
            nbt.method_10566("attributes", (class_2520)this.attributes.toNbt());
            nbt.method_10582("spouse_id", this.spouseId);
            if (this.spouseUuid != null) {
                nbt.method_25927("spouse_uuid", this.spouseUuid);
            }
            nbt.method_10582("spouse_display_name", this.spouseDisplayName);
            nbt.method_10566("spouse_attributes", (class_2520)this.spouseAttributes.toNbt());
            nbt.method_10566("spouse_appearance", (class_2520)this.spouseAppearance.toNbt());
            return nbt;
        }

        public static HouseLordProfile fromNbt(class_2487 nbt) {
            NpcAttributeProfile attributes = nbt.method_10573("attributes", 10) ? NpcAttributeProfile.fromNbt(nbt.method_10562("attributes")) : NpcAttributeProfile.EMPTY;
            NpcAttributeProfile spouseAttributes = nbt.method_10573("spouse_attributes", 10) ? NpcAttributeProfile.fromNbt(nbt.method_10562("spouse_attributes")) : NpcAttributeProfile.EMPTY;
            NpcAppearanceProfile spouseAppearance = nbt.method_10573("spouse_appearance", 10) ? NpcAppearanceProfile.fromNbt(nbt.method_10562("spouse_appearance")) : NpcAppearanceProfile.EMPTY;
            return new HouseLordProfile(nbt.method_10558("id"), nbt.method_25928("uuid") ? nbt.method_25926("uuid") : null, nbt.method_10558("kingdom_id"), nbt.method_10558("house_id"), nbt.method_10558("display_name"), nbt.method_10577("leader"), nbt.method_10550("index"), attributes, nbt.method_10558("spouse_id"), nbt.method_25928("spouse_uuid") ? nbt.method_25926("spouse_uuid") : null, nbt.method_10558("spouse_display_name"), spouseAttributes, spouseAppearance);
        }
    }
}

