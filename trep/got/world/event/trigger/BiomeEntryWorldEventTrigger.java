/*
 * Decompiled with CFR 0.152.
 */
package trep.got.world.event.trigger;

import java.util.Map;
import trep.got.world.event.WorldEventOccurrence;
import trep.got.world.event.WorldEventTrigger;
import trep.got.world.event.context.WorldEventPlayerSnapshot;

public final class BiomeEntryWorldEventTrigger
implements WorldEventTrigger {
    @Override
    public String id() {
        return "player_enter_biome";
    }

    @Override
    public WorldEventOccurrence evaluate(WorldEventPlayerSnapshot previous, WorldEventPlayerSnapshot current) {
        if (previous != null && previous.biomeId().equals(current.biomeId())) {
            return null;
        }
        if (current.biomeId().isBlank()) {
            return null;
        }
        return new WorldEventOccurrence(current.playerUuid(), this.id(), "Entered biome " + current.biomeId(), current.worldTick(), Map.of("biome", current.biomeId()));
    }
}

