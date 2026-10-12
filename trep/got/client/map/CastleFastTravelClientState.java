/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_746
 *  net.minecraft.class_8710
 */
package trep.got.client.map;

import java.util.Locale;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_746;
import net.minecraft.class_8710;
import trep.got.network.FastTravelCooldownPayload;
import trep.got.network.RequestCastleFastTravelPayload;

@Environment(value=EnvType.CLIENT)
public final class CastleFastTravelClientState {
    private static final int WARMUP_TICKS = 100;
    private static boolean active;
    private static int ticksRemaining;
    private static String targetCastleId;
    private static double startX;
    private static double startY;
    private static double startZ;
    private static long cooldownEndsAtEpochMs;

    private CastleFastTravelClientState() {
    }

    public static void start(String castleId) {
        class_310 client = class_310.method_1551();
        class_746 player = client.field_1724;
        if (player == null || castleId == null || castleId.isBlank() || CastleFastTravelClientState.hasCooldown()) {
            return;
        }
        active = true;
        ticksRemaining = 100;
        targetCastleId = castleId;
        startX = player.method_23317();
        startY = player.method_23318();
        startZ = player.method_23321();
        client.method_1507(null);
        CastleFastTravelClientState.showCountdownTitle(client, 5);
    }

    public static void applyCooldown(FastTravelCooldownPayload payload, class_310 client) {
        client.execute(() -> {
            cooldownEndsAtEpochMs = Math.max(0L, payload.endsAtEpochMs());
        });
    }

    public static void tick(class_310 client) {
        if (!active) {
            return;
        }
        class_746 player = client.field_1724;
        if (player == null || client.field_1687 == null) {
            CastleFastTravelClientState.clear(client);
            return;
        }
        if (CastleFastTravelClientState.hasMoved(player)) {
            CastleFastTravelClientState.cancel(client);
            return;
        }
        if (--ticksRemaining <= 0) {
            ClientPlayNetworking.send((class_8710)new RequestCastleFastTravelPayload(targetCastleId));
            CastleFastTravelClientState.clear(client);
            return;
        }
        if (ticksRemaining % 20 == 0) {
            CastleFastTravelClientState.showCountdownTitle(client, ticksRemaining / 20);
        }
    }

    public static void clear(class_310 client) {
        active = false;
        ticksRemaining = 0;
        targetCastleId = "";
        if (client.field_1705 != null) {
            client.field_1705.method_34003();
        }
    }

    private static boolean hasMoved(class_746 player) {
        return player.method_5649(startX, startY, startZ) > 0.003;
    }

    private static void cancel(class_310 client) {
        active = false;
        ticksRemaining = 0;
        targetCastleId = "";
        if (client.field_1705 != null) {
            client.field_1705.method_34004((class_2561)class_2561.method_43470((String)"Cancelled").method_27692(class_124.field_1061));
            client.field_1705.method_34002((class_2561)class_2561.method_43473());
            client.field_1705.method_34001(0, 30, 10);
        }
    }

    private static void showCountdownTitle(class_310 client, int secondsRemaining) {
        if (client.field_1705 == null) {
            return;
        }
        client.field_1705.method_34004((class_2561)class_2561.method_43470((String)Integer.toString(secondsRemaining)));
        client.field_1705.method_34002((class_2561)class_2561.method_43470((String)"Do not move"));
        client.field_1705.method_34001(0, 25, 0);
    }

    public static boolean hasCooldown() {
        return CastleFastTravelClientState.getCooldownRemainingMs() > 0L;
    }

    public static long getCooldownRemainingMs() {
        return Math.max(0L, cooldownEndsAtEpochMs - System.currentTimeMillis());
    }

    public static String cooldownLabel() {
        long totalSeconds = CastleFastTravelClientState.getCooldownRemainingMs() / 1000L;
        long minutes = totalSeconds / 60L;
        long seconds = totalSeconds % 60L;
        return minutes + ":" + String.format(Locale.ROOT, "%02d", seconds);
    }

    static {
        targetCastleId = "";
    }
}

