/*
 * Decompiled with CFR 0.152.
 */
package trep.got.world.event.trigger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import trep.got.GotMod;
import trep.got.world.event.WorldEventOccurrence;
import trep.got.world.event.WorldEventTrigger;
import trep.got.world.event.context.WorldEventPlayerSnapshot;

public final class LordLetterRobberAmbushWorldEventTrigger
implements WorldEventTrigger {
    private static final long MIN_QUEST_AGE_TICKS = 1200L;
    private static final long ROLL_INTERVAL_TICKS = 200L;
    private static final int ROLL_SUCCESS_THRESHOLD = 5;

    @Override
    public String id() {
        return "lord_letter_robber_ambush";
    }

    @Override
    public WorldEventOccurrence evaluate(WorldEventPlayerSnapshot previous, WorldEventPlayerSnapshot current) {
        long previousWindow;
        String questId = current.progress().activeLordLetterQuestId();
        if (questId.isBlank() || current.progress().activeLordLetterAmbushTriggered()) {
            return null;
        }
        if (current.claim().inClaim()) {
            return null;
        }
        if (!"on_foot".equals(current.travel().movementMode())) {
            return null;
        }
        long questAge = current.worldTick() - current.progress().activeLordLetterAcceptedAtTick();
        if (questAge < 1200L) {
            return null;
        }
        long currentWindow = current.worldTick() / 200L;
        long l = previousWindow = previous == null ? Long.MIN_VALUE : previous.worldTick() / 200L;
        if (currentWindow == previousWindow) {
            return null;
        }
        long roll = LordLetterRobberAmbushWorldEventTrigger.rollValue(current.playerUuid(), questId, currentWindow);
        boolean missed = roll >= 5L;
        GotMod.LOGGER.info("[world-event-roll] trigger={} player={} questId={} window={} roll={} threshold={} outcome={}", new Object[]{this.id(), current.playerName(), questId, currentWindow, roll, 5, missed ? "miss" : "trigger"});
        if (missed) {
            return null;
        }
        LinkedHashMap<String, String> details = new LinkedHashMap<String, String>();
        details.put("quest_id", questId);
        details.put("target_lord_name", current.progress().activeLordLetterTargetName());
        details.put("quest_age_ticks", Long.toString(questAge));
        return new WorldEventOccurrence(current.playerUuid(), this.id(), "Robbers intercept a lord's letter delivery", current.worldTick(), Map.copyOf(details));
    }

    private static long rollValue(UUID playerUuid, String questId, long window) {
        long seed = playerUuid.hashCode();
        seed = seed * 31L + (long)questId.hashCode();
        seed = seed * 31L + window;
        return Math.floorMod(seed, 100L);
    }
}

