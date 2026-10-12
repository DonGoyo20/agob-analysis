/*
 * Decompiled with CFR 0.152.
 */
package trep.got.world.event;

import java.util.List;
import trep.got.world.event.WorldEventTrigger;
import trep.got.world.event.trigger.BiomeEntryWorldEventTrigger;
import trep.got.world.event.trigger.CastleClaimWorldEventTrigger;
import trep.got.world.event.trigger.LordLetterRobberAmbushWorldEventTrigger;
import trep.got.world.event.trigger.TravelStatusWorldEventTrigger;
import trep.got.world.event.trigger.VehicleChangeWorldEventTrigger;

public final class WorldEventTriggerRegistry {
    private static final List<WorldEventTrigger> TRIGGERS = List.of(new BiomeEntryWorldEventTrigger(), new CastleClaimWorldEventTrigger(), new LordLetterRobberAmbushWorldEventTrigger(), new TravelStatusWorldEventTrigger(), new VehicleChangeWorldEventTrigger());

    private WorldEventTriggerRegistry() {
    }

    public static List<WorldEventTrigger> triggers() {
        return TRIGGERS;
    }
}

