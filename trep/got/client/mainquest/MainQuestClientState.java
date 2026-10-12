/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 */
package trep.got.client.mainquest;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import trep.got.network.MainQuestStatePayload;

@Environment(value=EnvType.CLIENT)
public final class MainQuestClientState {
    private static final String SOCIALIZE_QUEST_ID = "agob:socialize_quest";
    private static final String SMITHING_QUEST_ID = "agob:smithing_quest";
    private static final String GREET_LORD_QUEST_ID = "agob:greet_lord_quest";
    private static List<MainQuestStatePayload.Entry> activeQuests = List.of();
    private static List<String> completedQuestIds = List.of();

    private MainQuestClientState() {
    }

    public static void apply(MainQuestStatePayload payload, class_310 client) {
        client.execute(() -> {
            activeQuests = payload.activeQuests();
            completedQuestIds = payload.completedQuestIds();
        });
    }

    public static void clear() {
        activeQuests = List.of();
        completedQuestIds = List.of();
    }

    public static List<MainQuestStatePayload.Entry> activeQuests() {
        return activeQuests;
    }

    public static List<String> completedQuestIds() {
        return completedQuestIds;
    }

    public static boolean hasCompletedPledgePrerequisites() {
        return completedQuestIds.contains(SOCIALIZE_QUEST_ID) && completedQuestIds.contains(SMITHING_QUEST_ID) && completedQuestIds.contains(GREET_LORD_QUEST_ID);
    }
}

