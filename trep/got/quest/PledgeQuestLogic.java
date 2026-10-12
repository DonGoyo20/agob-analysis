/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package trep.got.quest;

import net.minecraft.class_2487;
import trep.got.castle.CastleInvasionMode;
import trep.got.kingdom.ReputationPledgeOption;
import trep.got.kingdom.ReputationProfile;
import trep.got.kingdom.ReputationProgressionService;
import trep.got.kingdom.ReputationRulesRegistry;
import trep.got.kingdom.ReputationTitle;
import trep.got.quest.QuestInstance;

public final class PledgeQuestLogic {
    public static final String GOLDEN_COMPANY_KINGDOM_ID = "agob:golden_company";
    public static final String TEMPLATE_ID = "main_pledge";
    public static final String METADATA_OBJECTIVE_COMPLETE = "pledge_objective_complete";
    public static final String METADATA_OBJECTIVE_SUCCESS = "pledge_objective_success";
    public static final String METADATA_OBJECTIVE_ACTION = "pledge_objective_action";
    public static final String METADATA_PLEDGED_TITLE = "pledged_title";
    public static final String METADATA_GIVER_CASTLE_ID = "giver_castle_id";
    public static final String METADATA_GIVER_KINGDOM_ID = "giver_kingdom_id";

    private PledgeQuestLogic() {
    }

    public static boolean hasReachedSoldierCrossroads(ReputationProfile profile) {
        return ReputationProgressionService.hasReachedSoldierCrossroads(profile);
    }

    public static boolean isPledgeQuest(QuestInstance quest) {
        return quest != null && TEMPLATE_ID.equals(quest.templateId());
    }

    public static boolean isObjectiveComplete(QuestInstance quest) {
        return PledgeQuestLogic.isPledgeQuest(quest) && quest.metadata().method_10577(METADATA_OBJECTIVE_COMPLETE);
    }

    public static CastleInvasionMode requiredMode(QuestInstance quest) {
        if (!PledgeQuestLogic.isPledgeQuest(quest)) {
            return null;
        }
        return CastleInvasionMode.byId(quest.metadata().method_10558(METADATA_OBJECTIVE_ACTION));
    }

    public static ReputationTitle pledgedTitle(QuestInstance quest) {
        if (!PledgeQuestLogic.isPledgeQuest(quest)) {
            return null;
        }
        return ReputationTitle.byId(quest.metadata().method_10558(METADATA_PLEDGED_TITLE));
    }

    public static QuestInstance markResolved(QuestInstance quest, boolean success) {
        if (!PledgeQuestLogic.isPledgeQuest(quest)) {
            return quest;
        }
        class_2487 metadata = quest.metadata().method_10553();
        metadata.method_10556(METADATA_OBJECTIVE_COMPLETE, true);
        metadata.method_10556(METADATA_OBJECTIVE_SUCCESS, success);
        return quest.withRewards(success ? 50 : 5, success ? 10 : 1, 0, "", 0).withMetadata(metadata);
    }

    public static class_2487 createMetadata(PledgePath path, String castleId, String kingdomId) {
        class_2487 metadata = new class_2487();
        metadata.method_10556(METADATA_OBJECTIVE_COMPLETE, false);
        metadata.method_10556(METADATA_OBJECTIVE_SUCCESS, false);
        metadata.method_10582(METADATA_OBJECTIVE_ACTION, path.mode().getId());
        metadata.method_10582(METADATA_PLEDGED_TITLE, path.title().getId());
        metadata.method_10582(METADATA_GIVER_CASTLE_ID, castleId == null ? "" : castleId);
        metadata.method_10582(METADATA_GIVER_KINGDOM_ID, kingdomId == null ? "" : kingdomId);
        return metadata;
    }

    public static boolean isPathAllowedForKingdom(PledgePath path, String kingdomId) {
        return path != null && ReputationRulesRegistry.findPledgeOption(kingdomId, path.id()) != null;
    }

    public static boolean isActionId(String actionId) {
        return actionId != null && actionId.startsWith("pledge_route:");
    }

    public static String optionIdFromActionId(String actionId) {
        return !PledgeQuestLogic.isActionId(actionId) ? "" : actionId.substring("pledge_route:".length());
    }

    public record PledgePath(String id, ReputationTitle title, CastleInvasionMode mode) {
        public static final PledgePath KNIGHT = new PledgePath("knight", ReputationTitle.KNIGHT, CastleInvasionMode.CONQUER);
        public static final PledgePath HEDGE_KNIGHT = new PledgePath("hedge_knight", ReputationTitle.HEDGE_KNIGHT, CastleInvasionMode.RAID);
        public static final PledgePath PRIEST = new PledgePath("priest", ReputationTitle.PRIEST, CastleInvasionMode.CRUSADE);

        public static PledgePath fromOption(ReputationPledgeOption option) {
            return new PledgePath(option.id(), option.title(), option.objectiveMode());
        }
    }
}

