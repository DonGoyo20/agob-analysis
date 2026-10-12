/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.kingdom;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;
import trep.got.kingdom.PlayerKingdomDefinition;
import trep.got.registry.GotKingdoms;

public final class PlayerKingdomWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.player_kingdoms";
    private static final class_18.class_8645<PlayerKingdomWorldState> TYPE = new class_18.class_8645(PlayerKingdomWorldState::new, PlayerKingdomWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, PlayerKingdomDefinition> kingdomsById = new LinkedHashMap<String, PlayerKingdomDefinition>();
    private final Map<String, String> kingdomIdByOwnerUuid = new LinkedHashMap<String, String>();

    public static PlayerKingdomWorldState get(class_3218 world) {
        PlayerKingdomWorldState state = (PlayerKingdomWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
        state.registerKnownKingdoms();
        return state;
    }

    public Collection<PlayerKingdomDefinition> getAll() {
        return this.kingdomsById.values();
    }

    public PlayerKingdomDefinition getByKingdomId(String kingdomId) {
        return kingdomId == null ? null : this.kingdomsById.get(kingdomId);
    }

    public PlayerKingdomDefinition getByOwnerUuid(String ownerUuid) {
        String kingdomId = ownerUuid == null ? null : this.kingdomIdByOwnerUuid.get(ownerUuid);
        return kingdomId == null ? null : this.kingdomsById.get(kingdomId);
    }

    public void put(PlayerKingdomDefinition definition) {
        if (definition == null || definition.kingdomId().isBlank() || definition.ownerUuid().isBlank()) {
            return;
        }
        PlayerKingdomDefinition previous = this.getByOwnerUuid(definition.ownerUuid());
        if (previous != null && !previous.kingdomId().equals(definition.kingdomId())) {
            this.kingdomsById.remove(previous.kingdomId());
        }
        this.kingdomsById.put(definition.kingdomId(), definition);
        this.kingdomIdByOwnerUuid.put(definition.ownerUuid(), definition.kingdomId());
        PlayerKingdomWorldState.registerKingdom(definition);
        this.method_80();
    }

    public boolean remove(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return false;
        }
        PlayerKingdomDefinition removed = this.kingdomsById.remove(kingdomId);
        if (removed == null) {
            return false;
        }
        this.kingdomIdByOwnerUuid.values().removeIf(kingdomId::equals);
        GotKingdoms.unregisterDynamicKingdom(class_2960.method_12829((String)kingdomId));
        this.method_80();
        return true;
    }

    private void registerKnownKingdoms() {
        for (PlayerKingdomDefinition definition : this.kingdomsById.values()) {
            PlayerKingdomWorldState.registerKingdom(definition);
        }
    }

    private static void registerKingdom(PlayerKingdomDefinition definition) {
        class_2960 kingdomId = class_2960.method_12829((String)definition.kingdomId());
        class_2960 parentKingdomId = class_2960.method_12829((String)definition.parentKingdomId());
        if (kingdomId != null && parentKingdomId != null) {
            GotKingdoms.registerDynamicKingdom(kingdomId, definition.displayName(), parentKingdomId);
        }
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 list = new class_2499();
        for (PlayerKingdomDefinition definition : this.kingdomsById.values()) {
            list.add((Object)definition.toNbt());
        }
        nbt.method_10566("kingdoms", (class_2520)list);
        return nbt;
    }

    private static PlayerKingdomWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        PlayerKingdomWorldState state = new PlayerKingdomWorldState();
        class_2499 kingdoms = nbt.method_10554("kingdoms", 10);
        for (int index = 0; index < kingdoms.size(); ++index) {
            PlayerKingdomDefinition definition = PlayerKingdomDefinition.fromNbt(kingdoms.method_10602(index));
            if (definition.kingdomId().isBlank() || definition.ownerUuid().isBlank()) continue;
            state.kingdomsById.put(definition.kingdomId(), definition);
            state.kingdomIdByOwnerUuid.put(definition.ownerUuid(), definition.kingdomId());
        }
        return state;
    }
}

