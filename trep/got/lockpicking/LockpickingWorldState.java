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
package trep.got.lockpicking;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_18;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;

public final class LockpickingWorldState
extends class_18 {
    private static final String STATE_ID = "gotmod.lockpicking";
    private static final class_18.class_8645<LockpickingWorldState> TYPE = new class_18.class_8645(LockpickingWorldState::new, LockpickingWorldState::fromNbt, class_4284.field_45077);
    private final Map<Long, LockpickSetup> setupsByPos = new LinkedHashMap<Long, LockpickSetup>();
    private final Map<Long, UUID> ownerUuidByPos = new LinkedHashMap<Long, UUID>();

    public static LockpickingWorldState get(class_3218 world) {
        return (LockpickingWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public LockpickSetup get(class_2338 pos) {
        return this.setupsByPos.get(pos.method_10063());
    }

    public void put(class_2338 pos, LockpickSetup setup) {
        if (pos == null || setup == null) {
            return;
        }
        this.setupsByPos.put(pos.method_10063(), setup);
        this.method_80();
    }

    public UUID getOwnerUuid(class_2338 pos) {
        return pos == null ? null : this.ownerUuidByPos.get(pos.method_10063());
    }

    public void setOwnerUuid(class_2338 pos, UUID ownerUuid) {
        if (pos == null || ownerUuid == null) {
            return;
        }
        this.ownerUuidByPos.put(pos.method_10063(), ownerUuid);
        this.method_80();
    }

    public void clearOwnerUuid(class_2338 pos) {
        if (pos != null && this.ownerUuidByPos.remove(pos.method_10063()) != null) {
            this.method_80();
        }
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 entries = new class_2499();
        for (Map.Entry<Long, LockpickSetup> entry : this.setupsByPos.entrySet()) {
            class_2487 setupNbt = entry.getValue().toNbt();
            setupNbt.method_10544("pos", entry.getKey().longValue());
            entries.add((Object)setupNbt);
        }
        nbt.method_10566("setups", (class_2520)entries);
        class_2499 owners = new class_2499();
        for (Map.Entry<Long, UUID> entry : this.ownerUuidByPos.entrySet()) {
            class_2487 ownerNbt = new class_2487();
            ownerNbt.method_10544("pos", entry.getKey().longValue());
            ownerNbt.method_25927("owner_uuid", entry.getValue());
            owners.add((Object)ownerNbt);
        }
        nbt.method_10566("owners", (class_2520)owners);
        return nbt;
    }

    private static LockpickingWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        LockpickingWorldState state = new LockpickingWorldState();
        class_2499 entries = nbt.method_10554("setups", 10);
        for (int index = 0; index < entries.size(); ++index) {
            class_2487 setupNbt = entries.method_10602(index);
            state.setupsByPos.put(setupNbt.method_10537("pos"), LockpickSetup.fromNbt(setupNbt));
        }
        class_2499 owners = nbt.method_10554("owners", 10);
        for (int index = 0; index < owners.size(); ++index) {
            class_2487 ownerNbt = owners.method_10602(index);
            if (!ownerNbt.method_25928("owner_uuid")) continue;
            state.ownerUuidByPos.put(ownerNbt.method_10537("pos"), ownerNbt.method_25926("owner_uuid"));
        }
        return state;
    }

    public record LockpickSetup(int lockCount, float speedMultiplier, String difficultyId, long seed) {
        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10569("lock_count", this.lockCount);
            nbt.method_10548("speed_multiplier", this.speedMultiplier);
            nbt.method_10582("difficulty", this.difficultyId);
            nbt.method_10544("seed", this.seed);
            return nbt;
        }

        public static LockpickSetup fromNbt(class_2487 nbt) {
            return new LockpickSetup(Math.max(3, Math.min(6, nbt.method_10550("lock_count"))), Math.max(1.0f, Math.min(1.25f, nbt.method_10583("speed_multiplier"))), LockpickSetup.normalizeDifficulty(nbt.method_10558("difficulty")), nbt.method_10537("seed"));
        }

        public static String normalizeDifficulty(String difficultyId) {
            return switch (difficultyId == null ? "" : difficultyId) {
                case "easy", "medium", "hard" -> difficultyId;
                default -> "medium";
            };
        }
    }
}

