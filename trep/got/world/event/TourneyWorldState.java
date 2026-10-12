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
package trep.got.world.event;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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

public final class TourneyWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.tourneys";
    private static final class_18.class_8645<TourneyWorldState> TYPE = new class_18.class_8645(TourneyWorldState::new, TourneyWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, TourneyEntry> activeByKingdom = new LinkedHashMap<String, TourneyEntry>();
    private final Map<String, Integer> cursorByKingdom = new LinkedHashMap<String, Integer>();
    private final Set<String> attended = new LinkedHashSet<String>();

    public static TourneyWorldState get(class_3218 world) {
        return (TourneyWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public TourneyEntry getActive(String kingdomId) {
        return this.activeByKingdom.get(TourneyWorldState.normalize(kingdomId));
    }

    public Set<String> activeKingdomIds() {
        return Set.copyOf(this.activeByKingdom.keySet());
    }

    public boolean isActiveCastle(String castleId) {
        if (castleId == null || castleId.isBlank()) {
            return false;
        }
        for (TourneyEntry entry : this.activeByKingdom.values()) {
            if (!castleId.equals(entry.castleId())) continue;
            return true;
        }
        return false;
    }

    public boolean hasAttended(UUID playerUuid, String tourneyId) {
        return playerUuid != null && tourneyId != null && this.attended.contains(String.valueOf(playerUuid) + "|" + tourneyId);
    }

    public void markAttended(UUID playerUuid, String tourneyId) {
        if (playerUuid != null && tourneyId != null && !tourneyId.isBlank() && this.attended.add(String.valueOf(playerUuid) + "|" + tourneyId)) {
            this.method_80();
        }
    }

    public int cursor(String kingdomId) {
        return this.cursorByKingdom.getOrDefault(TourneyWorldState.normalize(kingdomId), 0);
    }

    public void put(String kingdomId, TourneyEntry entry, int nextCursor) {
        String key = TourneyWorldState.normalize(kingdomId);
        if (key.isBlank() || entry == null) {
            return;
        }
        this.activeByKingdom.put(key, entry);
        this.cursorByKingdom.put(key, Math.max(0, nextCursor));
        this.attended.removeIf(value -> value.endsWith("|" + entry.tourneyId()));
        this.method_80();
    }

    public void remove(String kingdomId) {
        String key = TourneyWorldState.normalize(kingdomId);
        TourneyEntry removed = this.activeByKingdom.remove(key);
        this.cursorByKingdom.remove(key);
        if (removed != null) {
            this.attended.removeIf(value -> value.endsWith("|" + removed.tourneyId()));
            this.method_80();
        }
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 activeList = new class_2499();
        for (Map.Entry<String, TourneyEntry> entry : this.activeByKingdom.entrySet()) {
            class_2487 class_24872 = entry.getValue().toNbt();
            class_24872.method_10582("kingdom_id", entry.getKey());
            activeList.add((Object)class_24872);
        }
        nbt.method_10566("active", (class_2520)activeList);
        class_2487 cursors = new class_2487();
        for (Map.Entry<String, Integer> entry : this.cursorByKingdom.entrySet()) {
            cursors.method_10569(entry.getKey(), entry.getValue().intValue());
        }
        nbt.method_10566("cursors", (class_2520)cursors);
        class_2499 class_24992 = new class_2499();
        for (String key : this.attended) {
            class_24992.add((Object)class_2519.method_23256((String)key));
        }
        nbt.method_10566("attended", (class_2520)class_24992);
        return nbt;
    }

    private static TourneyWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        TourneyWorldState state = new TourneyWorldState();
        class_2499 activeList = nbt.method_10554("active", 10);
        for (int index = 0; index < activeList.size(); ++index) {
            class_2487 compound = activeList.method_10602(index);
            state.activeByKingdom.put(TourneyWorldState.normalize(compound.method_10558("kingdom_id")), TourneyEntry.fromNbt(compound));
        }
        class_2487 cursors = nbt.method_10562("cursors");
        for (String key : cursors.method_10541()) {
            state.cursorByKingdom.put(TourneyWorldState.normalize(key), cursors.method_10550(key));
        }
        class_2499 attendedList = nbt.method_10554("attended", 8);
        for (int index = 0; index < attendedList.size(); ++index) {
            state.attended.add(attendedList.method_10608(index));
        }
        return state;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim();
    }

    public record TourneyEntry(String tourneyId, String castleId, String castleName, String kingdomId, String houseId, String lordName, long day) {
        private class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("tourney_id", this.tourneyId);
            nbt.method_10582("castle_id", this.castleId);
            nbt.method_10582("castle_name", this.castleName);
            nbt.method_10582("house_id", this.houseId);
            nbt.method_10582("lord_name", this.lordName);
            nbt.method_10544("day", this.day);
            return nbt;
        }

        private static TourneyEntry fromNbt(class_2487 nbt) {
            return new TourneyEntry(nbt.method_10558("tourney_id"), nbt.method_10558("castle_id"), nbt.method_10558("castle_name"), nbt.method_10558("kingdom_id"), nbt.method_10558("house_id"), nbt.method_10558("lord_name"), nbt.method_10537("day"));
        }
    }
}

