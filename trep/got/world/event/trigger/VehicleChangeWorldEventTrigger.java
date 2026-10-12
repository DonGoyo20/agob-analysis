/*
 * Decompiled with CFR 0.152.
 */
package trep.got.world.event.trigger;

import java.util.LinkedHashMap;
import java.util.Map;
import trep.got.world.event.WorldEventOccurrence;
import trep.got.world.event.WorldEventTrigger;
import trep.got.world.event.context.WorldEventPlayerSnapshot;
import trep.got.world.event.context.WorldEventTravelContext;

public final class VehicleChangeWorldEventTrigger
implements WorldEventTrigger {
    @Override
    public String id() {
        return "player_vehicle_change";
    }

    @Override
    public WorldEventOccurrence evaluate(WorldEventPlayerSnapshot previous, WorldEventPlayerSnapshot current) {
        WorldEventTravelContext previousTravel;
        WorldEventTravelContext currentTravel = current.travel();
        WorldEventTravelContext worldEventTravelContext = previousTravel = previous == null ? new WorldEventTravelContext("", false, "", false, "") : previous.travel();
        if (previousTravel.movementMode().equals(currentTravel.movementMode()) && previousTravel.mountType().equals(currentTravel.mountType()) && previousTravel.vesselType().equals(currentTravel.vesselType())) {
            return null;
        }
        LinkedHashMap<String, String> details = new LinkedHashMap<String, String>();
        details.put("movement_mode", currentTravel.movementMode());
        details.put("mount_type", currentTravel.mountType());
        details.put("vessel_type", currentTravel.vesselType());
        String summary = switch (currentTravel.movementMode()) {
            case "mounted" -> "Mounted " + currentTravel.mountType();
            case "vessel", "voyage_vessel" -> "Boarded " + currentTravel.vesselType();
            default -> "Returned to foot travel";
        };
        return new WorldEventOccurrence(current.playerUuid(), this.id(), summary, current.worldTick(), Map.copyOf(details));
    }
}

