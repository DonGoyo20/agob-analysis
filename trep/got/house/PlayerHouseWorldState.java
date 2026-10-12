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

import java.util.Arrays;
import java.util.Collection;
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
import trep.got.house.PlayerHouseDefinition;

public final class PlayerHouseWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.player_houses";
    private static final class_18.class_8645<PlayerHouseWorldState> TYPE = new class_18.class_8645(PlayerHouseWorldState::new, PlayerHouseWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, PlayerHouseDefinition> housesById = new LinkedHashMap<String, PlayerHouseDefinition>();
    private final Map<String, String> houseIdByOwnerUuid = new LinkedHashMap<String, String>();
    private final Map<String, String> houseIdByMemberUuid = new LinkedHashMap<String, String>();
    private long revision;

    public static PlayerHouseWorldState get(class_3218 world) {
        return (PlayerHouseWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public Collection<PlayerHouseDefinition> getAll() {
        return List.copyOf(this.housesById.values());
    }

    public PlayerHouseDefinition getByHouseId(String houseId) {
        return houseId == null ? null : this.housesById.get(houseId);
    }

    public PlayerHouseDefinition getByOwnerUuid(String ownerUuid) {
        String houseId = ownerUuid == null ? null : this.houseIdByOwnerUuid.get(ownerUuid);
        return houseId == null ? null : this.housesById.get(houseId);
    }

    public PlayerHouseDefinition getByMemberUuid(String memberUuid) {
        String houseId = memberUuid == null ? null : this.houseIdByMemberUuid.get(memberUuid);
        return houseId == null ? null : this.housesById.get(houseId);
    }

    public PlayerHouseDefinition getByParticipantUuid(String playerUuid) {
        PlayerHouseDefinition owned = this.getByOwnerUuid(playerUuid);
        return owned != null ? owned : this.getByMemberUuid(playerUuid);
    }

    public long getRevision() {
        return this.revision;
    }

    public void put(PlayerHouseDefinition definition) {
        if (definition == null || definition.houseId().isBlank() || definition.ownerUuid().isBlank()) {
            return;
        }
        PlayerHouseDefinition existing = this.housesById.get(definition.houseId());
        if (PlayerHouseWorldState.hasSameContent(existing, definition)) {
            return;
        }
        PlayerHouseDefinition previous = this.getByOwnerUuid(definition.ownerUuid());
        if (previous != null && !previous.houseId().equals(definition.houseId())) {
            this.housesById.remove(previous.houseId());
        }
        this.housesById.put(definition.houseId(), definition);
        this.rebuildIndexes();
        ++this.revision;
        this.method_80();
    }

    private static boolean hasSameContent(PlayerHouseDefinition first, PlayerHouseDefinition second) {
        return first != null && first.ownerUuid().equals(second.ownerUuid()) && first.houseId().equals(second.houseId()) && first.kingdomId().equals(second.kingdomId()) && first.lordHouseId().equals(second.lordHouseId()) && first.displayName().equals(second.displayName()) && Arrays.equals(first.sigilColors(), second.sigilColors()) && first.memberUuids().equals(second.memberUuids());
    }

    public void remove(String houseId) {
        if (houseId == null || houseId.isBlank()) {
            return;
        }
        if (this.housesById.remove(houseId) != null) {
            this.rebuildIndexes();
            ++this.revision;
            this.method_80();
        }
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 list = new class_2499();
        for (PlayerHouseDefinition definition : this.housesById.values()) {
            list.add((Object)definition.toNbt());
        }
        nbt.method_10566("houses", (class_2520)list);
        return nbt;
    }

    private static PlayerHouseWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        PlayerHouseWorldState state = new PlayerHouseWorldState();
        class_2499 houses = nbt.method_10554("houses", 10);
        for (int index = 0; index < houses.size(); ++index) {
            PlayerHouseDefinition definition = PlayerHouseDefinition.fromNbt(houses.method_10602(index));
            if (definition.houseId().isBlank() || definition.ownerUuid().isBlank()) continue;
            state.housesById.put(definition.houseId(), definition);
        }
        state.rebuildIndexes();
        return state;
    }

    private void rebuildIndexes() {
        this.houseIdByOwnerUuid.clear();
        this.houseIdByMemberUuid.clear();
        for (PlayerHouseDefinition definition : this.housesById.values()) {
            this.houseIdByOwnerUuid.put(definition.ownerUuid(), definition.houseId());
            for (String memberUuid : definition.memberUuids()) {
                if (memberUuid.isBlank() || memberUuid.equals(definition.ownerUuid())) continue;
                this.houseIdByMemberUuid.put(memberUuid, definition.houseId());
            }
        }
    }
}

