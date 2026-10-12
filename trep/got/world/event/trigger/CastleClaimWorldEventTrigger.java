/*
 * Decompiled with CFR 0.152.
 */
package trep.got.world.event.trigger;

import java.util.LinkedHashMap;
import java.util.Map;
import trep.got.world.event.WorldEventOccurrence;
import trep.got.world.event.WorldEventTrigger;
import trep.got.world.event.context.WorldEventClaimContext;
import trep.got.world.event.context.WorldEventPlayerSnapshot;

public final class CastleClaimWorldEventTrigger
implements WorldEventTrigger {
    @Override
    public String id() {
        return "player_claim_transition";
    }

    @Override
    public WorldEventOccurrence evaluate(WorldEventPlayerSnapshot previous, WorldEventPlayerSnapshot current) {
        WorldEventClaimContext previousClaim;
        WorldEventClaimContext currentClaim = current.claim();
        WorldEventClaimContext worldEventClaimContext = previousClaim = previous == null ? WorldEventClaimContext.NONE : previous.claim();
        if (previousClaim.inClaim() == currentClaim.inClaim() && previousClaim.castleId().equals(currentClaim.castleId()) && previousClaim.claimType() == currentClaim.claimType()) {
            return null;
        }
        LinkedHashMap<String, String> details = new LinkedHashMap<String, String>();
        details.put("castle_id", currentClaim.castleId());
        details.put("claim_type", currentClaim.claimType().id());
        details.put("harbour_castle", Boolean.toString(currentClaim.harbourCastle()));
        String summary = currentClaim.inClaim() ? "Entered " + currentClaim.claimType().id() + " claim " + currentClaim.castleName() : "Left castle claim";
        return new WorldEventOccurrence(current.playerUuid(), this.id(), summary, current.worldTick(), Map.copyOf(details));
    }
}

