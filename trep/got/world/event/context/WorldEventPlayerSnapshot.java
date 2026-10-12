/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package trep.got.world.event.context;

import java.util.UUID;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import trep.got.world.event.context.WorldEventClaimContext;
import trep.got.world.event.context.WorldEventMapContext;
import trep.got.world.event.context.WorldEventProgressContext;
import trep.got.world.event.context.WorldEventRouteContext;
import trep.got.world.event.context.WorldEventTravelContext;
import trep.got.world.event.context.WorldEventVitalsContext;

public record WorldEventPlayerSnapshot(UUID playerUuid, String playerName, long worldTick, String biomeId, WorldEventMapContext map, WorldEventClaimContext claim, WorldEventRouteContext route, WorldEventTravelContext travel, WorldEventVitalsContext vitals, WorldEventProgressContext progress) {
    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_25927("player_uuid", this.playerUuid);
        nbt.method_10582("player_name", this.playerName);
        nbt.method_10544("world_tick", this.worldTick);
        nbt.method_10582("biome_id", this.biomeId);
        nbt.method_10566("map", (class_2520)this.map.toNbt());
        nbt.method_10566("claim", (class_2520)this.claim.toNbt());
        nbt.method_10566("route", (class_2520)this.route.toNbt());
        nbt.method_10566("travel", (class_2520)this.travel.toNbt());
        nbt.method_10566("vitals", (class_2520)this.vitals.toNbt());
        nbt.method_10566("progress", (class_2520)this.progress.toNbt());
        return nbt;
    }

    public static WorldEventPlayerSnapshot fromNbt(class_2487 nbt) {
        return new WorldEventPlayerSnapshot(nbt.method_25926("player_uuid"), nbt.method_10558("player_name"), nbt.method_10537("world_tick"), nbt.method_10558("biome_id"), WorldEventMapContext.fromNbt(nbt.method_10562("map")), WorldEventClaimContext.fromNbt(nbt.method_10562("claim")), WorldEventRouteContext.fromNbt(nbt.method_10562("route")), WorldEventTravelContext.fromNbt(nbt.method_10562("travel")), WorldEventVitalsContext.fromNbt(nbt.method_10562("vitals")), WorldEventProgressContext.fromNbt(nbt.method_10562("progress")));
    }
}

