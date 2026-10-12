/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_2338
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.world;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_18;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;

public final class WeirwoodTreeWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.weirwoods";
    private static final class_18.class_8645<WeirwoodTreeWorldState> TYPE = new class_18.class_8645(WeirwoodTreeWorldState::new, WeirwoodTreeWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, Entry> entriesByCastleId = new LinkedHashMap<String, Entry>();

    public static WeirwoodTreeWorldState get(class_3218 world) {
        return (WeirwoodTreeWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public boolean contains(String castleId) {
        return castleId != null && this.entriesByCastleId.containsKey(castleId);
    }

    public class_2338 get(String castleId) {
        Entry entry = this.getEntry(castleId);
        return entry == null ? null : entry.pos();
    }

    public Entry getEntry(String castleId) {
        return castleId == null ? null : this.entriesByCastleId.get(castleId);
    }

    public void put(String castleId, class_2338 pos, long nextBleedAttemptTick) {
        if (castleId == null || castleId.isBlank() || pos == null) {
            return;
        }
        this.entriesByCastleId.put(castleId, new Entry(castleId, pos.method_10062(), Math.max(0L, nextBleedAttemptTick)));
        this.method_80();
    }

    public void setNextBleedAttemptTick(String castleId, long nextBleedAttemptTick) {
        Entry entry = this.getEntry(castleId);
        if (entry == null) {
            return;
        }
        long sanitizedTick = Math.max(0L, nextBleedAttemptTick);
        if (entry.nextBleedAttemptTick() == sanitizedTick) {
            return;
        }
        this.entriesByCastleId.put(castleId, new Entry(entry.castleId(), entry.pos(), sanitizedTick));
        this.method_80();
    }

    public Collection<Entry> getAll() {
        return List.copyOf(this.entriesByCastleId.values());
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 weirwoods = new class_2499();
        for (Entry entry : this.entriesByCastleId.values()) {
            class_2487 compound = new class_2487();
            compound.method_10582("castle_id", entry.castleId());
            compound.method_10544("pos", entry.pos().method_10063());
            compound.method_10544("next_bleed_attempt_tick", entry.nextBleedAttemptTick());
            weirwoods.add((Object)compound);
        }
        nbt.method_10566("weirwoods", (class_2520)weirwoods);
        return nbt;
    }

    private static WeirwoodTreeWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        WeirwoodTreeWorldState state = new WeirwoodTreeWorldState();
        class_2499 weirwoods = nbt.method_10554("weirwoods", 10);
        for (int index = 0; index < weirwoods.size(); ++index) {
            class_2487 compound = weirwoods.method_10602(index);
            String castleId = compound.method_10558("castle_id");
            if (castleId == null || castleId.isBlank() || !compound.method_10545("pos")) continue;
            long nextBleedAttemptTick = compound.method_10545("next_bleed_attempt_tick") ? compound.method_10537("next_bleed_attempt_tick") : 0L;
            state.entriesByCastleId.put(castleId, new Entry(castleId, class_2338.method_10092((long)compound.method_10537("pos")), Math.max(0L, nextBleedAttemptTick)));
        }
        return state;
    }

    public record Entry(String castleId, class_2338 pos, long nextBleedAttemptTick) {
    }
}

