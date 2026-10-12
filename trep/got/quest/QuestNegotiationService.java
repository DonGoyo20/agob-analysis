/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3532
 */
package trep.got.quest;

import net.minecraft.class_3532;
import trep.got.quest.QuestInstance;
import trep.got.quest.QuestNegotiationOutcome;

public final class QuestNegotiationService {
    private QuestNegotiationService() {
    }

    public static int successChance(int charismaPoints) {
        return class_3532.method_15340((int)(20 + charismaPoints / 2), (int)20, (int)70);
    }

    public static QuestInstance applyOutcome(QuestInstance quest, QuestNegotiationOutcome outcome) {
        if (quest == null || outcome == null) {
            return quest;
        }
        int reputationExperience = quest.reputationExperience();
        int goldIngots = quest.goldIngots();
        int ironIngots = quest.ironIngots();
        if (outcome == QuestNegotiationOutcome.SUCCESS) {
            reputationExperience = Math.max(0, Math.round((float)reputationExperience * 1.1f));
            goldIngots = goldIngots > 0 ? goldIngots + 1 : 0;
            ironIngots = ironIngots > 0 ? ironIngots + 1 : 0;
        } else {
            reputationExperience = Math.max(0, Math.round((float)reputationExperience * 0.9f));
            goldIngots = Math.max(0, goldIngots - 1);
            ironIngots = Math.max(0, ironIngots - 1);
        }
        return quest.withRewards(reputationExperience, goldIngots, ironIngots, quest.skillTypeId(), quest.skillPoints());
    }
}

