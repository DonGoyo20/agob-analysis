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
package trep.got.world.event;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;
import trep.got.world.event.WorldEventOccurrence;
import trep.got.world.event.WorldEventPlayerState;

public final class WorldEventState
extends class_18 {
    public static final String STATE_ID = "gotmod.world_events";
    private static final int MAX_RECENT_OCCURRENCES = 128;
    private static final class_18.class_8645<WorldEventState> TYPE = new class_18.class_8645(WorldEventState::new, WorldEventState::fromNbt, class_4284.field_45077);
    private final Map<UUID, WorldEventPlayerState> players = new LinkedHashMap<UUID, WorldEventPlayerState>();
    private final LinkedList<WorldEventOccurrence> recentOccurrences = new LinkedList();

    public static WorldEventState get(class_3218 world) {
        return (WorldEventState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public WorldEventPlayerState getPlayerState(UUID playerUuid) {
        return this.players.get(playerUuid);
    }

    public void putPlayerState(WorldEventPlayerState playerState) {
        this.players.put(playerState.playerUuid(), playerState);
        this.method_80();
    }

    public void removePlayerState(UUID playerUuid) {
        if (this.players.remove(playerUuid) != null) {
            this.method_80();
        }
    }

    public Collection<WorldEventPlayerState> playerStates() {
        return this.players.values();
    }

    public void recordOccurrence(WorldEventOccurrence occurrence) {
        this.recentOccurrences.addLast(occurrence);
        while (this.recentOccurrences.size() > 128) {
            this.recentOccurrences.removeFirst();
        }
        this.method_80();
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 playerList = new class_2499();
        for (WorldEventPlayerState playerState : this.players.values()) {
            playerList.add((Object)playerState.toNbt());
        }
        nbt.method_10566("players", (class_2520)playerList);
        class_2499 occurrenceList = new class_2499();
        for (WorldEventOccurrence occurrence : this.recentOccurrences) {
            occurrenceList.add((Object)occurrence.toNbt());
        }
        nbt.method_10566("recent_occurrences", (class_2520)occurrenceList);
        return nbt;
    }

    private static WorldEventState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        WorldEventState state = new WorldEventState();
        class_2499 playerList = nbt.method_10554("players", 10);
        for (int index = 0; index < playerList.size(); ++index) {
            WorldEventPlayerState playerState = WorldEventPlayerState.fromNbt(playerList.method_10602(index));
            state.players.put(playerState.playerUuid(), playerState);
        }
        class_2499 occurrenceList = nbt.method_10554("recent_occurrences", 10);
        for (int index = 0; index < occurrenceList.size(); ++index) {
            state.recentOccurrences.add(WorldEventOccurrence.fromNbt(occurrenceList.method_10602(index)));
        }
        return state;
    }
}

