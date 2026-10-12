/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_8710
 */
package trep.got.client.voyage;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_8710;
import trep.got.GotMod;
import trep.got.entity.CartEntity;
import trep.got.entity.ShipEntity;
import trep.got.entity.TwoHorseCartEntity;
import trep.got.network.RequestCancelVoyagePayload;
import trep.got.network.VoyageStatePayload;

@Environment(value=EnvType.CLIENT)
public final class VoyageClientState {
    private static final int CONTROL_LOG_WINDOW_TICKS = 100;
    private static boolean active;
    private static String voyageId;
    private static boolean cancelArmed;
    private static int cancelArmExpiresAtTick;
    private static boolean lastSneakPressed;
    private static int clientTicks;
    private static int nextControlLogTick;

    private VoyageClientState() {
    }

    public static void apply(VoyageStatePayload payload, class_310 client) {
        client.execute(() -> {
            active = payload.active();
            voyageId = payload.active() ? payload.voyageId() : "";
            cancelArmed = false;
            cancelArmExpiresAtTick = 0;
            lastSneakPressed = false;
        });
    }

    public static void tick(class_310 client) {
        ++clientTicks;
        if (client.field_1724 == null) {
            cancelArmed = false;
            lastSneakPressed = false;
            nextControlLogTick = clientTicks + 100;
            return;
        }
        boolean pressed = client.field_1690.field_1832.method_1434();
        class_1297 vehicle = client.field_1724.method_5854();
        boolean ridingShip = vehicle instanceof ShipEntity;
        boolean ridingLandTravelCart = VoyageClientState.isLandTravelVehicle(vehicle);
        boolean activeVoyageContext = VoyageClientState.isActiveVoyageContext(client);
        if (!activeVoyageContext) {
            if ((ridingShip || ridingLandTravelCart) && pressed && !lastSneakPressed) {
                VoyageClientState.logVoyageShiftPress(client, vehicle, "inactive_voyage_state");
            }
            if (!pressed) {
                lastSneakPressed = false;
            }
            cancelArmed = false;
            nextControlLogTick = clientTicks + 100;
            return;
        }
        VoyageClientState.logClientVoyageControl(client);
        if (!pressed) {
            lastSneakPressed = false;
            if (cancelArmed && clientTicks > cancelArmExpiresAtTick) {
                cancelArmed = false;
            }
            return;
        }
        if (lastSneakPressed) {
            return;
        }
        lastSneakPressed = true;
        if (ridingShip || ridingLandTravelCart) {
            VoyageClientState.logVoyageShiftPress(client, vehicle, "shift_pressed");
        }
        if (!cancelArmed || clientTicks > cancelArmExpiresAtTick) {
            cancelArmed = true;
            cancelArmExpiresAtTick = clientTicks + 40;
            GotMod.LOGGER.info("Voyage shift cancel arm clientTick={} voyageId={} vehicle={} vehicleUuid={} active={} cancelArmed={} expiresAt={}", new Object[]{clientTicks, voyageId, vehicle == null ? "null" : vehicle.method_5864(), vehicle == null ? "n/a" : vehicle.method_5845(), activeVoyageContext, cancelArmed, cancelArmExpiresAtTick});
            client.field_1724.method_7353((class_2561)class_2561.method_43470((String)(ridingLandTravelCart ? "Shift again to Cancel Ride" : "Shift again to Cancel Voyage")).method_27692(class_124.field_1061), true);
            return;
        }
        cancelArmed = false;
        cancelArmExpiresAtTick = 0;
        GotMod.LOGGER.info("Voyage shift cancel trigger clientTick={} voyageId={} vehicle={} vehicleUuid={} active={} sendingCancelPacket=true", new Object[]{clientTicks, voyageId, vehicle == null ? "null" : vehicle.method_5864(), vehicle == null ? "n/a" : vehicle.method_5845(), activeVoyageContext});
        ClientPlayNetworking.send((class_8710)new RequestCancelVoyagePayload());
    }

    private static boolean isActiveVoyageContext(class_310 client) {
        if (client == null || client.field_1724 == null) {
            return false;
        }
        class_1297 vehicle = client.field_1724.method_5854();
        if (vehicle instanceof ShipEntity) {
            ShipEntity ship = (ShipEntity)vehicle;
            return active || ship.isVoyageMode();
        }
        if (VoyageClientState.isLandTravelVehicle(vehicle)) {
            return active;
        }
        return false;
    }

    private static boolean isLandTravelVehicle(class_1297 vehicle) {
        return vehicle instanceof CartEntity || vehicle instanceof TwoHorseCartEntity;
    }

    private static void logVoyageShiftPress(class_310 client, class_1297 vehicle, String reason) {
        ShipEntity ship;
        GotMod.LOGGER.info("Voyage shift press clientTick={} reason={} voyageId={} vehicle={} vehicleUuid={} shipVoyageMode={} cancelArmed={} cancelExpiresAt={} playerVehicle={} playerPos={}", new Object[]{clientTicks, reason, voyageId, vehicle == null ? "null" : vehicle.method_5864(), vehicle == null ? "n/a" : vehicle.method_5845(), vehicle instanceof ShipEntity && (ship = (ShipEntity)vehicle).isVoyageMode(), cancelArmed, cancelArmExpiresAtTick, client.field_1724 == null || client.field_1724.method_5854() == null ? "null" : client.field_1724.method_5854().method_5864(), client.field_1724 == null ? "n/a" : client.field_1724.method_19538()});
    }

    private static void logClientVoyageControl(class_310 client) {
        if (clientTicks < nextControlLogTick || client.field_1724 == null) {
            return;
        }
        nextControlLogTick = clientTicks + 100;
        class_1297 vehicle = client.field_1724.method_5854();
        if (!(vehicle instanceof ShipEntity)) {
            GotMod.LOGGER.info("Voyage client summary windowTicks={} reason=no_ship_vehicle voyageId={} playerVehicle={}", new Object[]{100, voyageId, vehicle == null ? "n/a" : vehicle.method_5864()});
            return;
        }
        ShipEntity ship = (ShipEntity)vehicle;
        class_1297 firstPassenger = ship.method_31483();
        class_1309 controller = ship.method_5642();
        ArrayList<CallSite> passengers = new ArrayList<CallSite>();
        for (class_1297 passenger : ship.method_5685()) {
            passengers.add((CallSite)((Object)(String.valueOf(passenger.method_5864()) + ":" + passenger.method_5845())));
        }
        GotMod.LOGGER.info("Voyage client summary windowTicks={} voyageId={} firstPassenger={} firstPassengerUuid={} controller={} controllerUuid={} passengers={} playerPos={}", new Object[]{100, voyageId, firstPassenger == null ? "null" : firstPassenger.method_5864(), firstPassenger == null ? "n/a" : firstPassenger.method_5845(), controller == null ? "null" : controller.method_5864(), controller == null ? "n/a" : controller.method_5845(), passengers, client.field_1724.method_19538()});
    }

    public static void clear() {
        active = false;
        voyageId = "";
        cancelArmed = false;
        cancelArmExpiresAtTick = 0;
        lastSneakPressed = false;
        nextControlLogTick = clientTicks + 100;
    }

    public static boolean shouldSuppressSneak() {
        return VoyageClientState.isActiveVoyageContext(class_310.method_1551());
    }

    public static boolean isActive() {
        return active;
    }

    public static String getVoyageId() {
        return voyageId;
    }

    static {
        voyageId = "";
        nextControlLogTick = 100;
    }
}

