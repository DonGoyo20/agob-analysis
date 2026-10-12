/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package trep.got.quest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import net.minecraft.class_2487;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.kingdom.ReputationTitle;
import trep.got.quest.QuestDebugLogBuffer;
import trep.got.quest.QuestInstance;
import trep.got.quest.QuestTemplateRegistry;
import trep.got.quest.ShipmentQuestLogic;
import trep.got.skill.SkillAttributeType;

public final class LordLetterQuestLogic {
    public static final String TEMPLATE_ID = "lord_letter_delivery";
    public static final String LETTER_ITEM_ID = "agob:sealed_letter";
    private static final int AVAILABILITY_CHANCE_PERCENT = 5;

    private LordLetterQuestLogic() {
    }

    public static QuestInstance createQuest(UUID playerUuid, AbstractKingdomNpcEntity npc, long worldTime, long offerCycle, ReputationTitle reputationTitle) {
        if (playerUuid == null) {
            LordLetterQuestLogic.logFailure(null, "player_uuid_missing");
            return null;
        }
        if (npc == null) {
            return null;
        }
        if (npc.got$getCastle().isBlank()) {
            LordLetterQuestLogic.logFailure(npc, "giver_castle_missing");
            return null;
        }
        List<ShipmentQuestLogic.CastleTarget> castles = ShipmentQuestLogic.collectTargets(npc.method_37908());
        if (castles.isEmpty()) {
            LordLetterQuestLogic.logFailure(npc, "no_castle_targets_available");
            return null;
        }
        ShipmentQuestLogic.CastleTarget source = castles.stream().filter(castle -> castle.id().equals(npc.got$getCastle())).findFirst().orElse(null);
        if (source == null) {
            LordLetterQuestLogic.logFailure(npc, "giver_castle_not_found_in_targets");
            return null;
        }
        long seed = QuestTemplateRegistry.computeOfferSeed(playerUuid, npc.method_5667(), offerCycle, TEMPLATE_ID);
        int availabilityRoll = Math.floorMod((int)(seed * 37L ^ seed >>> 13), 100);
        if (availabilityRoll >= 5) {
            LordLetterQuestLogic.logFailure(npc, "rare_roll_failed roll=" + availabilityRoll + " threshold=5");
            return null;
        }
        ArrayList<CandidateTarget> eligible = new ArrayList<CandidateTarget>();
        int skippedSameCastle = 0;
        int skippedDistance = 0;
        int skippedMissingName = 0;
        for (ShipmentQuestLogic.CastleTarget castle2 : castles) {
            if (castle2.id().equals(source.id())) {
                ++skippedSameCastle;
                continue;
            }
            double distance = LordLetterQuestLogic.distance(source, castle2);
            if (distance < 5000.0 || distance > 10000.0) {
                ++skippedDistance;
                continue;
            }
            String targetLordName = LordLetterQuestLogic.resolveLordName(npc, castle2);
            if (castle2.name().isBlank()) {
                ++skippedMissingName;
                continue;
            }
            eligible.add(new CandidateTarget(castle2, targetLordName, distance));
        }
        if (eligible.isEmpty()) {
            LordLetterQuestLogic.logFailure(npc, "no_eligible_targets total_targets=" + castles.size() + " skipped_same_castle=" + skippedSameCastle + " skipped_distance=" + skippedDistance + " skipped_missing_name=" + skippedMissingName);
            return null;
        }
        eligible.sort(Comparator.comparing(candidate -> candidate.target().id()));
        CandidateTarget chosen = (CandidateTarget)eligible.get(Math.floorMod((int)(seed ^ seed >>> 32), eligible.size()));
        int distanceBonusSteps = Math.max(0, (int)Math.floor((chosen.distance() - 5000.0) / 500.0));
        int reputationExperience = LordLetterQuestLogic.applyTitleBonus((int)Math.round(50.0 * (1.0 + (double)distanceBonusSteps * 0.05)), reputationTitle);
        int goldIngots = 3 + distanceBonusSteps;
        SkillRewardBundle skillRewards = LordLetterQuestLogic.rollSkillRewards(seed, distanceBonusSteps);
        long durationDays = 3L + Math.max(0L, Math.round((chosen.distance() - 5000.0) / 1800.0));
        long dueAtTick = worldTime + durationDays * 24000L;
        String questId = "quest_" + Math.abs((playerUuid.toString() + npc.method_5845() + worldTime + TEMPLATE_ID + chosen.target().id()).hashCode());
        LordLetterQuestLogic.logSuccess(npc, chosen, eligible.size(), castles.size(), offerCycle);
        return new QuestInstance(questId, TEMPLATE_ID, npc.method_5667(), npc.method_5476().getString(), QuestTemplateRegistry.offerKey(npc.method_5667(), offerCycle), LETTER_ITEM_ID, 1, worldTime, dueAtTick, reputationExperience, goldIngots, 0, skillRewards.primaryType().getId(), skillRewards.primaryPoints(), skillRewards.secondaryType() == null ? "" : skillRewards.secondaryType().getId(), skillRewards.secondaryPoints(), KingdomNpcRole.LORD.name(), chosen.target().id(), chosen.target().name(), chosen.targetLordName(), LETTER_ITEM_ID, new class_2487());
    }

    private static String resolveLordName(AbstractKingdomNpcEntity questGiver, ShipmentQuestLogic.CastleTarget castle) {
        if (castle == null) {
            return "";
        }
        if (!castle.lordName().isBlank()) {
            return castle.lordName();
        }
        if (!castle.name().isBlank()) {
            return "the lord of " + castle.name();
        }
        return "the local lord";
    }

    private static void logFailure(AbstractKingdomNpcEntity npc, String reason) {
        QuestDebugLogBuffer.record(TEMPLATE_ID, "no_quest:" + reason, "giver=" + (npc == null ? "<null>" : npc.method_5476().getString()) + ",giver_uuid=" + (npc == null ? "<null>" : npc.method_5845()) + ",giver_role=" + (npc == null ? "<null>" : npc.getNpcRole().name()) + ",giver_castle=" + (npc == null ? "<null>" : npc.got$getCastle()) + ",side=" + (npc == null || npc.method_37908() == null ? "<unknown>" : (npc.method_37908().method_8608() ? "client" : "server")));
    }

    private static void logSuccess(AbstractKingdomNpcEntity npc, CandidateTarget chosen, int eligibleCount, int totalTargets, long offerCycle) {
        QuestDebugLogBuffer.record(TEMPLATE_ID, "created", "giver=" + npc.method_5476().getString() + ",giver_uuid=" + npc.method_5845() + ",giver_castle=" + npc.got$getCastle() + ",target_castle=" + chosen.target().id() + ",target_lord=" + chosen.targetLordName() + ",distance=" + Math.round(chosen.distance()) + ",eligible_targets=" + eligibleCount + ",total_targets=" + totalTargets + ",offer_cycle=" + offerCycle + ",side=" + (npc.method_37908().method_8608() ? "client" : "server"));
    }

    private static double distance(ShipmentQuestLogic.CastleTarget source, ShipmentQuestLogic.CastleTarget target) {
        long dx = (long)target.worldX() - (long)source.worldX();
        long dz = (long)target.worldZ() - (long)source.worldZ();
        return Math.sqrt(dx * dx + dz * dz);
    }

    private static int applyTitleBonus(int reputationExperience, ReputationTitle title) {
        return title == ReputationTitle.PEASANT ? reputationExperience + 2 : reputationExperience;
    }

    private static SkillRewardBundle rollSkillRewards(long seed, int distanceBonusSteps) {
        int primaryPoints = 1 + distanceBonusSteps / 3;
        int secondaryPoints = 1 + distanceBonusSteps / 4;
        boolean charismaPrimary = Math.floorMod((int)(seed * 19L ^ seed >>> 21), 2) == 0;
        SkillAttributeType primaryType = charismaPrimary ? SkillAttributeType.CHARISMA : SkillAttributeType.INTELLIGENCE;
        SkillAttributeType secondaryType = charismaPrimary ? SkillAttributeType.INTELLIGENCE : SkillAttributeType.CHARISMA;
        boolean grantSecondary = Math.floorMod((int)(seed * 29L ^ seed >>> 27), 100) < Math.min(100, 20 + distanceBonusSteps * 6);
        return new SkillRewardBundle(primaryType, Math.min(3, primaryPoints), grantSecondary ? secondaryType : null, grantSecondary ? Math.min(3, secondaryPoints) : 0);
    }

    private record CandidateTarget(ShipmentQuestLogic.CastleTarget target, String targetLordName, double distance) {
    }

    private record SkillRewardBundle(SkillAttributeType primaryType, int primaryPoints, SkillAttributeType secondaryType, int secondaryPoints) {
    }
}

