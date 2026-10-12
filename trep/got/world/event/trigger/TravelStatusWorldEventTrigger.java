/*
 * Decompiled with CFR 0.152.
 */
package trep.got.world.event.trigger;

import java.util.LinkedHashMap;
import java.util.Map;
import trep.got.world.event.WorldEventOccurrence;
import trep.got.world.event.WorldEventTrigger;
import trep.got.world.event.context.WorldEventPlayerSnapshot;

public final class TravelStatusWorldEventTrigger
implements WorldEventTrigger {
    @Override
    public String id() {
        return "player_travel_status";
    }

    @Override
    public WorldEventOccurrence evaluate(WorldEventPlayerSnapshot previous, WorldEventPlayerSnapshot current) {
        boolean previousQuest = previous != null && previous.progress().onQuest();
        boolean currentQuest = current.progress().onQuest();
        boolean previousVoyage = previous != null && previous.progress().onVoyage();
        boolean currentVoyage = current.progress().onVoyage();
        if (previousQuest == currentQuest && previousVoyage == currentVoyage) {
            return null;
        }
        LinkedHashMap<String, String> details = new LinkedHashMap<String, String>();
        details.put("on_quest", Boolean.toString(currentQuest));
        details.put("active_quest_count", Integer.toString(current.progress().activeQuestCount()));
        details.put("on_voyage", Boolean.toString(currentVoyage));
        String summary = !previousVoyage && currentVoyage ? "Started a voyage" : (previousVoyage && !currentVoyage ? "Completed or left a voyage" : (!previousQuest && currentQuest ? "Started a quest" : "Finished all active quests"));
        return new WorldEventOccurrence(current.playerUuid(), this.id(), summary, current.worldTick(), Map.copyOf(details));
    }
}

