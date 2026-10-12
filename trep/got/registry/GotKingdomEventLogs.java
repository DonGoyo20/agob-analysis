/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.networking.v1.PacketSender
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_8710
 */
package trep.got.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import trep.got.kingdom.KingdomEventLogWorldState;
import trep.got.network.KingdomEventLogPayload;
import trep.got.world.dimension.GotDimensionKeys;

public final class GotKingdomEventLogs {
    private GotKingdomEventLogs() {
    }

    public static void initialize() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> GotKingdomEventLogs.syncToPlayer(handler.field_14140, sender));
    }

    public static void append(class_3218 world, String kingdomId, String line) {
        GotKingdomEventLogs.append(world, kingdomId, line, "");
    }

    public static void append(class_3218 world, String kingdomId, String line, String iconTextureId) {
        if (world == null || kingdomId == null || kingdomId.isBlank() || line == null || line.isBlank()) {
            return;
        }
        KingdomEventLogWorldState.get(world).append(kingdomId, line, iconTextureId);
        GotKingdomEventLogs.syncToPlayers(world);
    }

    public static void syncToPlayer(class_3222 player) {
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return;
        }
        ServerPlayNetworking.send((class_3222)player, (class_8710)GotKingdomEventLogs.buildPayload(world));
    }

    private static void syncToPlayer(class_3222 player, PacketSender sender) {
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return;
        }
        sender.sendPacket((class_8710)GotKingdomEventLogs.buildPayload(world));
    }

    public static void syncToPlayers(class_3218 world) {
        KingdomEventLogPayload payload = GotKingdomEventLogs.buildPayload(world);
        for (class_3222 player : world.method_8503().method_3760().method_14571()) {
            ServerPlayNetworking.send((class_3222)player, (class_8710)payload);
        }
    }

    private static KingdomEventLogPayload buildPayload(class_3218 world) {
        Map<String, List<KingdomEventLogWorldState.LogEntry>> logs = KingdomEventLogWorldState.get(world).asMap();
        ArrayList<KingdomEventLogPayload.KingdomEntry> kingdoms = new ArrayList<KingdomEventLogPayload.KingdomEntry>(logs.size());
        for (Map.Entry<String, List<KingdomEventLogWorldState.LogEntry>> entry : logs.entrySet()) {
            ArrayList<KingdomEventLogPayload.LogEntry> lines = new ArrayList<KingdomEventLogPayload.LogEntry>(entry.getValue().size());
            for (KingdomEventLogWorldState.LogEntry line : entry.getValue()) {
                lines.add(new KingdomEventLogPayload.LogEntry(line.line(), line.iconTextureId()));
            }
            kingdoms.add(new KingdomEventLogPayload.KingdomEntry(entry.getKey(), List.copyOf(lines)));
        }
        return new KingdomEventLogPayload(List.copyOf(kingdoms));
    }
}

