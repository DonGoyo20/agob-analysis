/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.world.event;

import java.util.HashSet;
import java.util.UUID;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.server.MinecraftServer;
import trep.got.GotMod;
import trep.got.world.event.LordLetterRobberAmbushService;
import trep.got.world.event.WorldEventOccurrence;
import trep.got.world.event.WorldEventPlayerState;
import trep.got.world.event.WorldEventState;
import trep.got.world.event.WorldEventTrigger;
import trep.got.world.event.WorldEventTriggerRegistry;
import trep.got.world.event.context.WorldEventContextService;
import trep.got.world.event.context.WorldEventPlayerSnapshot;

public final class WorldEventSystem {
    private static final long WORLD_EVENT_EVALUATION_INTERVAL_TICKS = 10L;
    private static final long WORLD_EVENT_STALE_STATE_CLEANUP_INTERVAL_TICKS = 200L;

    private WorldEventSystem() {
    }

    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(WorldEventSystem::tickServer);
    }

    private static void tickServer(MinecraftServer server) {
        class_3218 stateWorld = server.method_30002();
        if (stateWorld == null) {
            return;
        }
        WorldEventState state = WorldEventState.get(stateWorld);
        long worldTick = stateWorld.method_8510();
        long evaluationBucket = Math.floorMod(worldTick, 10L);
        boolean cleanupStaleState = worldTick % 200L == 0L;
        HashSet<UUID> activePlayers = cleanupStaleState ? new HashSet<UUID>(server.method_3760().method_14571().size()) : null;
        for (class_3222 player : server.method_3760().method_14571()) {
            if (activePlayers != null) {
                activePlayers.add(player.method_5667());
            }
            if (Math.floorMod((long)player.method_5667().hashCode(), 10L) != evaluationBucket) continue;
            WorldEventPlayerSnapshot current = WorldEventContextService.capture(player, server, worldTick);
            WorldEventPlayerState previousState = state.getPlayerState(player.method_5667());
            WorldEventPlayerSnapshot previous = previousState == null ? null : previousState.snapshot();
            for (WorldEventTrigger trigger : WorldEventTriggerRegistry.triggers()) {
                WorldEventOccurrence occurrence = trigger.evaluate(previous, current);
                if (occurrence == null) continue;
                state.recordOccurrence(occurrence);
                GotMod.LOGGER.info("World event triggered for {}: {} [{}]", new Object[]{player.method_7334().getName(), occurrence.summary(), occurrence.triggerId()});
                LordLetterRobberAmbushService.handleOccurrence(player, current, occurrence);
            }
            if (previousState == null) {
                state.putPlayerState(new WorldEventPlayerState(player.method_5667(), current, worldTick));
                continue;
            }
            previousState.update(current, worldTick);
            state.putPlayerState(previousState);
        }
        if (!cleanupStaleState) {
            return;
        }
        for (WorldEventPlayerState playerState : state.playerStates().stream().toList()) {
            if (activePlayers.contains(playerState.playerUuid())) continue;
            state.removePlayerState(playerState.playerUuid());
        }
    }
}

