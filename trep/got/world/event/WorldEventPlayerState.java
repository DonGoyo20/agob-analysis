/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package trep.got.world.event;

import java.util.UUID;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import trep.got.world.event.context.WorldEventPlayerSnapshot;

public final class WorldEventPlayerState {
    private final UUID playerUuid;
    private WorldEventPlayerSnapshot snapshot;
    private long lastUpdatedTick;

    public WorldEventPlayerState(UUID playerUuid, WorldEventPlayerSnapshot snapshot, long lastUpdatedTick) {
        this.playerUuid = playerUuid;
        this.snapshot = snapshot;
        this.lastUpdatedTick = lastUpdatedTick;
    }

    public UUID playerUuid() {
        return this.playerUuid;
    }

    public WorldEventPlayerSnapshot snapshot() {
        return this.snapshot;
    }

    public long lastUpdatedTick() {
        return this.lastUpdatedTick;
    }

    public void update(WorldEventPlayerSnapshot snapshot, long worldTick) {
        this.snapshot = snapshot;
        this.lastUpdatedTick = worldTick;
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_25927("player_uuid", this.playerUuid);
        nbt.method_10566("snapshot", (class_2520)this.snapshot.toNbt());
        nbt.method_10544("last_updated_tick", this.lastUpdatedTick);
        return nbt;
    }

    public static WorldEventPlayerState fromNbt(class_2487 nbt) {
        return new WorldEventPlayerState(nbt.method_25926("player_uuid"), WorldEventPlayerSnapshot.fromNbt(nbt.method_10562("snapshot")), nbt.method_10537("last_updated_tick"));
    }
}

