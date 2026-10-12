/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1802
 *  net.minecraft.class_2168
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 */
package trep.got.relations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_1802;
import net.minecraft.class_2168;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import trep.got.advancement.ModAdvancementService;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.PlayerKingdomWorldState;
import trep.got.kingdom.ReputationTitle;
import trep.got.registry.GotCastles;
import trep.got.registry.GotGameRules;
import trep.got.registry.GotKingdomEventLogs;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotRelations;
import trep.got.relations.KingdomRelationsWorldState;
import trep.got.world.dimension.GotDimensionKeys;

public final class KingdomWarService {
    private static final long MAX_SIMULATED_WAR_DAYS_PER_TICK = 3L;
    private static final double CROSS_CONTINENT_WAR_CHANCE = 0.1;
    private static final long WAR_DURATION_DAYS = 5L;
    private static final long DECLARATION_COOLDOWN_DAYS = 3L;
    private static final long AGGRESSOR_PEACE_GRACE_DAYS = 7L;
    private static final long DEFENDER_REVENGE_GRACE_DAYS = 3L;
    private static final long WAR_DECLARATION_MIN_TIME_OF_DAY = 1800L;
    private static final long WAR_DECLARATION_MAX_TIME_OF_DAY = 22000L;
    private static final int ANNOUNCEMENT_YELLOW = -727358;
    private static final int ANNOUNCEMENT_GREEN = -10497171;
    private static final double FRONTIER_ADJACENT_DISTANCE_BLOCKS = 3200.0;
    private static final double FRONTIER_NEAR_DISTANCE_BLOCKS = 5200.0;
    private static final Map<String, Map<String, Integer>> WAR_TARGET_WEIGHTS = Map.of("agob:the_north", Map.of("agob:iron_islands", 9, "agob:the_riverlands", 5, "agob:the_vale", 3, "agob:the_crownlands", 1), "agob:the_vale", Map.of("agob:the_riverlands", 7, "agob:the_crownlands", 5, "agob:the_north", 3, "agob:the_westerlands", 1), "agob:the_riverlands", Map.of("agob:iron_islands", 9, "agob:the_westerlands", 8, "agob:the_crownlands", 5, "agob:the_vale", 4, "agob:the_north", 3, "agob:the_reach", 2, "agob:the_stormlands", 1), "agob:the_westerlands", Map.of("agob:the_riverlands", 8, "agob:the_reach", 5, "agob:iron_islands", 4, "agob:the_stormlands", 2, "agob:the_crownlands", 2, "agob:the_vale", 1), "agob:iron_islands", Map.of("agob:the_riverlands", 9, "agob:the_north", 7, "agob:the_westerlands", 6, "agob:the_reach", 4, "agob:the_crownlands", 1), "agob:the_reach", Map.of("agob:dorne", 9, "agob:the_stormlands", 7, "agob:the_westerlands", 5, "agob:the_crownlands", 4, "agob:iron_islands", 2, "agob:the_riverlands", 2), "agob:the_stormlands", Map.of("agob:dorne", 9, "agob:the_reach", 7, "agob:the_crownlands", 5, "agob:the_riverlands", 2, "agob:the_westerlands", 1), "agob:dorne", Map.of("agob:the_reach", 9, "agob:the_stormlands", 8, "agob:the_crownlands", 4, "agob:the_westerlands", 1), "agob:the_crownlands", Map.of("agob:the_stormlands", 6, "agob:the_riverlands", 5, "agob:the_reach", 5, "agob:the_vale", 4, "agob:dorne", 2, "agob:the_westerlands", 2, "agob:iron_islands", 1));

    private KingdomWarService() {
    }

    public static void tick(class_3218 world) {
        if (world == null) {
            return;
        }
        PlayerKingdomWorldState.get(world);
        KingdomRelationsWorldState state = KingdomRelationsWorldState.get(world);
        long queuedManualWarDays = state.getQueuedManualWarDays();
        boolean immediateForManualSkip = queuedManualWarDays > 0L;
        long refreshDay = KingdomWarService.resolveWarRefreshDay(state, world, queuedManualWarDays);
        if (refreshDay <= state.getLastWarRefreshDay()) {
            KingdomWarService.processPendingWarDeclarations(world, state, refreshDay, KingdomWarService.normalizedTimeOfDay(world.method_8532()), immediateForManualSkip);
            return;
        }
        long initialLastRefreshDay = state.getLastWarRefreshDay();
        long cappedRefreshDay = Math.min(refreshDay, initialLastRefreshDay + 3L);
        for (long day = initialLastRefreshDay + 1L; day <= cappedRefreshDay; ++day) {
            KingdomWarService.processWarDay(world, state, day, immediateForManualSkip);
            state.setLastWarRefreshDay(day);
        }
        KingdomWarService.processPendingWarDeclarations(world, state, cappedRefreshDay, KingdomWarService.normalizedTimeOfDay(world.method_8532()), immediateForManualSkip);
        if (queuedManualWarDays > 0L) {
            state.consumeQueuedManualWarDays(Math.min(queuedManualWarDays, state.getLastWarRefreshDay() - initialLastRefreshDay));
        }
        state.setLastObservedWarTimeOfDay(KingdomWarService.normalizedTimeOfDay(world.method_8532()));
    }

    public static void onExplicitTimeSet(class_2168 source) {
        if (source == null || source.method_9211() == null) {
            return;
        }
        class_3218 world = source.method_9211().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null || !GotGameRules.affectedByTimeSkip(world)) {
            return;
        }
        KingdomRelationsWorldState.get(world).queueManualWarDay();
    }

    public static boolean isAtWar(class_3218 world, String firstKingdomId, String secondKingdomId) {
        if (world == null) {
            return false;
        }
        return KingdomRelationsWorldState.get(world).isAtWar(KingdomWarService.normalizeKingdomId(firstKingdomId), KingdomWarService.normalizeKingdomId(secondKingdomId));
    }

    public static List<String> activeWarTargets(class_3218 world, String kingdomId) {
        if (world == null) {
            return List.of();
        }
        String normalizedKingdomId = KingdomWarService.normalizeKingdomId(kingdomId);
        if (normalizedKingdomId.isBlank()) {
            return List.of();
        }
        ArrayList<String> targets = new ArrayList<String>();
        for (KingdomRelationsWorldState.ActiveWar war : KingdomRelationsWorldState.get(world).getActiveWars()) {
            String other = war.otherKingdom(normalizedKingdomId);
            if (other.isBlank()) continue;
            targets.add(other);
        }
        return List.copyOf(targets);
    }

    public static boolean ensureWarForPlayerInvasion(class_3218 world, class_3222 player, String attackerKingdomId, String defenderKingdomId, ReputationTitle title) {
        long nextPairDeclarationDay;
        if (world == null || player == null) {
            return false;
        }
        String attacker = KingdomWarService.normalizeKingdomId(attackerKingdomId);
        String defender = KingdomWarService.normalizeKingdomId(defenderKingdomId);
        if (attacker.isBlank() || defender.isBlank() || attacker.equals(defender)) {
            return true;
        }
        if (KingdomWarService.isAtWar(world, attacker, defender)) {
            return true;
        }
        if (title == null || title.getTier() < 2) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You are not of high enough rank to enact a kingdom war.").method_27692(class_124.field_1061), true);
            return false;
        }
        KingdomRelationsWorldState state = KingdomRelationsWorldState.get(world);
        long today = KingdomWarService.currentWorldDay(world);
        if (today < (nextPairDeclarationDay = state.getPairDeclarationReadyDay(attacker, defender))) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Your kingdom cannot declare war on that kingdom yet.").method_27692(class_124.field_1061), true);
            return false;
        }
        KingdomWarService.declareWar(world, state, attacker, defender, player.method_7334().getName(), today);
        return true;
    }

    public static void handlePlayerKingDiplomacyAction(class_3222 player, String targetKingdomId, boolean proposePeace) {
        if (player == null) {
            return;
        }
        class_3218 world = player.method_51469();
        String playerKingdomId = GotCastles.playerRuledKingdomId(world, player);
        String targetKingdom = KingdomWarService.normalizeKingdomId(targetKingdomId);
        if (playerKingdomId.isBlank()) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Only a ruling king can direct kingdom diplomacy.").method_27692(class_124.field_1061), true);
            return;
        }
        if (targetKingdom.isBlank() || playerKingdomId.equals(targetKingdom)) {
            return;
        }
        KingdomRelationsWorldState state = KingdomRelationsWorldState.get(world);
        state.clearExpiredDiplomacyCooldowns(world.method_8510());
        if (proposePeace) {
            KingdomWarService.handlePlayerKingPeaceProposal(player, world, state, playerKingdomId, targetKingdom);
        } else {
            KingdomWarService.handlePlayerKingWarDeclaration(player, world, state, playerKingdomId, targetKingdom);
        }
    }

    private static void processWarDay(class_3218 world, KingdomRelationsWorldState state, long day, boolean immediateForManualSkip) {
        List<KingdomRelationsWorldState.ActiveWar> expiredWars = state.getActiveWars().stream().filter(war -> war.endsAtDay() <= day).toList();
        for (KingdomRelationsWorldState.ActiveWar war2 : expiredWars) {
            KingdomWarService.makePeace(world, state, war2, day);
        }
        if (day < 1L) {
            return;
        }
        List<String> declarationOrder = KingdomWarService.orderedAutonomousKingdoms(world, day);
        for (String attackerKingdomId : declarationOrder) {
            String defenderKingdomId;
            if (day < state.getNextDeclarationDay(attackerKingdomId) || !immediateForManualSkip && state.hasPendingWarDeclarationForAttacker(attackerKingdomId) || (defenderKingdomId = KingdomWarService.chooseWarTarget(world, state, attackerKingdomId, day)).isBlank()) continue;
            String actorName = KingdomWarService.resolveAiWarActorName(world, attackerKingdomId, day);
            if (immediateForManualSkip) {
                KingdomWarService.declareWar(world, state, attackerKingdomId, defenderKingdomId, actorName, day);
                continue;
            }
            KingdomWarService.scheduleWarDeclaration(world, state, attackerKingdomId, defenderKingdomId, actorName, day);
        }
    }

    private static void scheduleWarDeclaration(class_3218 world, KingdomRelationsWorldState state, String attackerKingdomId, String defenderKingdomId, String actorName, long day) {
        if (state.hasPendingWarDeclaration(attackerKingdomId, defenderKingdomId) || state.isAtWar(attackerKingdomId, defenderKingdomId)) {
            return;
        }
        state.addPendingWarDeclaration(new KingdomRelationsWorldState.PendingWarDeclaration(attackerKingdomId, defenderKingdomId, actorName, day, KingdomWarService.scheduledWarDeclarationTimeOfDay(world, day, attackerKingdomId, defenderKingdomId)));
    }

    private static void processPendingWarDeclarations(class_3218 world, KingdomRelationsWorldState state, long currentDay, long currentTimeOfDay, boolean immediateForManualSkip) {
        List<KingdomRelationsWorldState.PendingWarDeclaration> declarations = List.copyOf(state.getPendingWarDeclarations());
        for (KingdomRelationsWorldState.PendingWarDeclaration declaration : declarations) {
            boolean due;
            boolean bl = due = immediateForManualSkip || declaration.scheduledDay() < currentDay || declaration.scheduledDay() == currentDay && currentTimeOfDay >= declaration.scheduledTimeOfDay();
            if (!due) continue;
            state.removePendingWarDeclaration(declaration.attackerKingdomId(), declaration.defenderKingdomId());
            if (state.isAtWar(declaration.attackerKingdomId(), declaration.defenderKingdomId()) || currentDay < state.getNextDeclarationDay(declaration.attackerKingdomId()) || currentDay < state.getPairDeclarationReadyDay(declaration.attackerKingdomId(), declaration.defenderKingdomId())) continue;
            KingdomWarService.declareWar(world, state, declaration.attackerKingdomId(), declaration.defenderKingdomId(), declaration.actorName(), currentDay);
        }
    }

    private static void declareWar(class_3218 world, KingdomRelationsWorldState state, String attackerKingdomId, String defenderKingdomId, String actorName, long day) {
        KingdomRelationsWorldState.ActiveWar war = new KingdomRelationsWorldState.ActiveWar(attackerKingdomId, defenderKingdomId, day, day + 5L);
        state.addActiveWar(war);
        state.removePendingWarDeclaration(attackerKingdomId, defenderKingdomId);
        state.setNextDeclarationDay(attackerKingdomId, day + 3L);
        GotCastles.startOpeningKingdomAiInvasionForWar(world, attackerKingdomId, defenderKingdomId);
        KingdomWarService.announceWar(world, attackerKingdomId, defenderKingdomId);
        KingdomWarService.appendWarDeclarationLog(world, actorName, attackerKingdomId, defenderKingdomId);
        GotRelations.syncToPlayers(world);
    }

    private static void makePeace(class_3218 world, KingdomRelationsWorldState state, KingdomRelationsWorldState.ActiveWar war, long day) {
        if (GotCastles.hasActiveKingdomAiWarParty(world, war.attackerKingdomId(), war.defenderKingdomId())) {
            return;
        }
        if (state.removeActiveWar(war.attackerKingdomId(), war.defenderKingdomId()) == null) {
            return;
        }
        state.setPairDeclarationReadyDay(war.attackerKingdomId(), war.defenderKingdomId(), day + 7L);
        state.setPairDeclarationReadyDay(war.defenderKingdomId(), war.attackerKingdomId(), day + 3L);
        KingdomWarService.announcePeace(world, war.attackerKingdomId(), war.defenderKingdomId());
        KingdomWarService.appendPeaceLog(world, war.attackerKingdomId(), war.defenderKingdomId());
        GotRelations.syncToPlayers(world);
    }

    private static void handlePlayerKingWarDeclaration(class_3222 player, class_3218 world, KingdomRelationsWorldState state, String attackerKingdomId, String defenderKingdomId) {
        if (state.isAtWar(attackerKingdomId, defenderKingdomId)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Your kingdom is already at war with that kingdom.").method_27692(class_124.field_1061), true);
            return;
        }
        long today = KingdomWarService.currentWorldDay(world);
        if (today < state.getNextDeclarationDay(attackerKingdomId)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Your kingdom cannot declare war again yet.").method_27692(class_124.field_1061), true);
            return;
        }
        if (state.hasPendingWarDeclarationForAttacker(attackerKingdomId)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Your kingdom already has a war declaration pending.").method_27692(class_124.field_1061), true);
            return;
        }
        if (today < state.getPairDeclarationReadyDay(attackerKingdomId, defenderKingdomId)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Your kingdom cannot declare war on that kingdom yet.").method_27692(class_124.field_1061), true);
            return;
        }
        KingdomWarService.declareWar(world, state, attackerKingdomId, defenderKingdomId, player.method_7334().getName(), today);
        player.method_7353((class_2561)class_2561.method_43470((String)("War has been declared on " + KingdomWarService.readableKingdomName(defenderKingdomId) + ".")).method_27692(class_124.field_1065), true);
    }

    private static void handlePlayerKingPeaceProposal(class_3222 player, class_3218 world, KingdomRelationsWorldState state, String firstKingdomId, String secondKingdomId) {
        if (!state.isAtWar(firstKingdomId, secondKingdomId)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Your kingdom is not at war with that kingdom.").method_27692(class_124.field_1061), true);
            return;
        }
        long currentTick = world.method_8510();
        long cooldownEndsAtTick = state.getDiplomacyCooldownEndsAtTick(firstKingdomId, secondKingdomId);
        if (cooldownEndsAtTick > currentTick) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Peace negotiations are still cooling down.").method_27692(class_124.field_1061), true);
            GotRelations.syncToPlayer(player);
            return;
        }
        if (world.field_9229.method_43056()) {
            GotCastles.cancelKingdomAiWarPartiesBetween(world, firstKingdomId, secondKingdomId);
            KingdomWarService.forcePeace(world, state, firstKingdomId, secondKingdomId, KingdomWarService.currentWorldDay(world));
            player.method_7353((class_2561)class_2561.method_43470((String)("Peace was accepted by " + KingdomWarService.readableKingdomName(secondKingdomId) + ".")).method_27692(class_124.field_1060), true);
            return;
        }
        state.setDiplomacyCooldownEndsAtTick(firstKingdomId, secondKingdomId, currentTick + 6000L);
        GotRelations.syncToPlayers(world);
        player.method_7353((class_2561)class_2561.method_43470((String)"Peace was rejected. Try again in 5 minutes.").method_27692(class_124.field_1061), true);
    }

    private static void forcePeace(class_3218 world, KingdomRelationsWorldState state, String firstKingdomId, String secondKingdomId, long day) {
        KingdomRelationsWorldState.ActiveWar war = state.removeActiveWar(firstKingdomId, secondKingdomId);
        if (war == null) {
            return;
        }
        state.setDiplomacyCooldownEndsAtTick(firstKingdomId, secondKingdomId, 0L);
        state.removePendingWarDeclaration(firstKingdomId, secondKingdomId);
        state.removePendingWarDeclaration(secondKingdomId, firstKingdomId);
        state.setPairDeclarationReadyDay(war.attackerKingdomId(), war.defenderKingdomId(), day + 7L);
        state.setPairDeclarationReadyDay(war.defenderKingdomId(), war.attackerKingdomId(), day + 3L);
        KingdomWarService.announcePeace(world, war.attackerKingdomId(), war.defenderKingdomId());
        KingdomWarService.appendPeaceLog(world, war.attackerKingdomId(), war.defenderKingdomId());
        GotRelations.syncToPlayers(world);
    }

    private static List<String> orderedAutonomousKingdoms(class_3218 world, long day) {
        List<String> kingdomIds = GotKingdoms.AI_WAR_KINGDOMS.stream().map(kingdom -> kingdom.getId().toString()).filter(KingdomWarService::isAutonomousWarKingdom).filter(kingdomId -> !GotCastles.hasPlayerKingdomLeader(world, kingdomId)).sorted(Comparator.comparing(id -> KingdomWarService.deterministicWarRoll(world, day, id, "order"))).toList();
        return List.copyOf(kingdomIds);
    }

    private static String chooseWarTarget(class_3218 world, KingdomRelationsWorldState state, String attackerKingdomId, long day) {
        Map weights = WAR_TARGET_WEIGHTS.getOrDefault(attackerKingdomId, Map.of());
        List<GotCastles.StrategicCastleSnapshot> attackerCastles = GotCastles.getStrategicAiInvadersForKingdom(world, attackerKingdomId);
        if (attackerCastles.isEmpty()) {
            return "";
        }
        boolean attackerHasHarbour = KingdomWarService.hasHarbourCastle(attackerCastles);
        boolean castlelessOriginAttacker = attackerCastles.stream().anyMatch(castle -> castle.castleId() != null && castle.castleId().startsWith("castleless_origin_"));
        ArrayList<WeightedTarget> sameContinentCandidates = new ArrayList<WeightedTarget>();
        ArrayList<WeightedTarget> crossContinentCandidates = new ArrayList<WeightedTarget>();
        for (Kingdom kingdom : GotKingdoms.AI_WAR_KINGDOMS) {
            boolean harbourLinked;
            List<GotCastles.StrategicCastleSnapshot> defenderCastles;
            String defenderKingdomId = KingdomWarService.normalizeKingdomId(kingdom.getId().toString());
            if (defenderKingdomId.isBlank() || attackerKingdomId.equals(defenderKingdomId) || !KingdomWarService.isAutonomousWarKingdom(defenderKingdomId) || state.isAtWar(attackerKingdomId, defenderKingdomId) || day < state.getPairDeclarationReadyDay(attackerKingdomId, defenderKingdomId) || (defenderCastles = KingdomWarService.castlesForKingdom(world, defenderKingdomId)).isEmpty() || castlelessOriginAttacker && defenderCastles.stream().noneMatch(castle -> GotCastles.canCastlelessOriginTarget(attackerKingdomId, castle))) continue;
            boolean defenderHasHarbour = KingdomWarService.hasHarbourCastle(defenderCastles);
            FrontierScore frontier = KingdomWarService.frontierScore(attackerCastles, defenderCastles);
            boolean crossContinent = GotKingdoms.areKingdomsOnDifferentContinents(attackerKingdomId, defenderKingdomId);
            boolean bl = harbourLinked = attackerHasHarbour && defenderHasHarbour;
            if (crossContinent && !harbourLinked || !crossContinent && !castlelessOriginAttacker && !frontier.near() && !harbourLinked) continue;
            int loreWeight = Math.max(0, weights.getOrDefault(defenderKingdomId, 1));
            int adjustedWeight = KingdomWarService.computeWarTargetWeight(loreWeight, frontier, state.activeWarCount(defenderKingdomId), harbourLinked);
            if (castlelessOriginAttacker) {
                int n = adjustedWeight = GotKingdoms.isEssosKingdom(defenderKingdomId) ? adjustedWeight + 20 : Math.max(1, adjustedWeight / 2);
            }
            if (adjustedWeight <= 0) continue;
            double tieBreaker = KingdomWarService.deterministicWarRoll(world, day, attackerKingdomId, defenderKingdomId);
            WeightedTarget candidate2 = new WeightedTarget(defenderKingdomId, adjustedWeight, tieBreaker);
            if (crossContinent) {
                crossContinentCandidates.add(candidate2);
                continue;
            }
            sameContinentCandidates.add(candidate2);
        }
        List<WeightedTarget> candidates = KingdomWarService.selectWarTargetPool(world, day, attackerKingdomId, sameContinentCandidates, crossContinentCandidates);
        if (candidates.isEmpty()) {
            return "";
        }
        candidates.sort(Comparator.comparingInt(WeightedTarget::weight).reversed().thenComparingDouble(WeightedTarget::tieBreaker));
        int topWeight = candidates.getFirst().weight();
        List<WeightedTarget> best = candidates.stream().filter(candidate -> candidate.weight() >= Math.max(1, topWeight - 1)).toList();
        int totalWeight = best.stream().mapToInt(WeightedTarget::weight).sum();
        if (totalWeight <= 0) {
            return best.getFirst().kingdomId();
        }
        double roll = KingdomWarService.deterministicWarRoll(world, day, attackerKingdomId, "target_roll") * (double)totalWeight;
        int running = 0;
        for (WeightedTarget candidate3 : best) {
            if (!(roll < (double)(running += candidate3.weight()))) continue;
            return candidate3.kingdomId();
        }
        return best.getLast().kingdomId();
    }

    private static List<WeightedTarget> selectWarTargetPool(class_3218 world, long day, String attackerKingdomId, List<WeightedTarget> sameContinentCandidates, List<WeightedTarget> crossContinentCandidates) {
        if (!sameContinentCandidates.isEmpty()) {
            if (!crossContinentCandidates.isEmpty()) {
                String[] stringArray = new String[]{attackerKingdomId, "cross_continent"};
                if (KingdomWarService.deterministicWarRoll(world, day, stringArray) < 0.1) {
                    return crossContinentCandidates;
                }
            }
            return sameContinentCandidates;
        }
        return crossContinentCandidates;
    }

    private static String resolveAiWarActorName(class_3218 world, String kingdomId, long day) {
        if (world == null || kingdomId == null || kingdomId.isBlank()) {
            return KingdomWarService.readableKingdomName(kingdomId);
        }
        return GotCastles.getStrategicAiInvadersForKingdom(world, kingdomId).stream().sorted(Comparator.comparingDouble(castle -> KingdomWarService.deterministicWarRoll(world, day, kingdomId, castle.lordName(), "actor")).thenComparing(GotCastles.StrategicCastleSnapshot::castleId)).map(GotCastles.StrategicCastleSnapshot::lordName).filter(name -> name != null && !name.isBlank()).findFirst().orElse(KingdomWarService.readableKingdomName(kingdomId));
    }

    private static void appendWarDeclarationLog(class_3218 world, String actorName, String attackerKingdomId, String defenderKingdomId) {
        String line = (actorName == null || actorName.isBlank() ? KingdomWarService.readableKingdomName(attackerKingdomId) : actorName) + " declared war on " + KingdomWarService.readableKingdomName(defenderKingdomId);
        GotKingdomEventLogs.append(world, attackerKingdomId, line);
        GotKingdomEventLogs.append(world, defenderKingdomId, line);
    }

    private static void appendPeaceLog(class_3218 world, String firstKingdomId, String secondKingdomId) {
        String line = KingdomWarService.readableKingdomName(firstKingdomId) + " and " + KingdomWarService.readableKingdomName(secondKingdomId) + " have made peace";
        GotKingdomEventLogs.append(world, firstKingdomId, line);
        GotKingdomEventLogs.append(world, secondKingdomId, line);
    }

    private static void announceWar(class_3218 world, String attackerKingdomId, String defenderKingdomId) {
        if (world == null) {
            return;
        }
        String title = KingdomWarService.readableKingdomName(attackerKingdomId) + " Declared";
        String description = KingdomWarService.readableKingdomName(defenderKingdomId);
        String topTextureId = KingdomWarService.sigilTextureId(attackerKingdomId);
        String bottomTextureId = KingdomWarService.sigilTextureId(defenderKingdomId);
        for (class_3222 player : world.method_18456()) {
            ModAdvancementService.send(player, "kingdom_war_declared", title, description, class_1802.field_8371, -727358, -727358, topTextureId, bottomTextureId);
        }
    }

    private static void announcePeace(class_3218 world, String firstKingdomId, String secondKingdomId) {
        if (world == null) {
            return;
        }
        for (class_3222 player : world.method_18456()) {
            PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
            String playerKingdomId = KingdomWarService.normalizeKingdomId(profile.getSelectedKingdomId());
            String primaryKingdomId = firstKingdomId;
            String secondaryKingdomId = secondKingdomId;
            if (playerKingdomId.equals(KingdomWarService.normalizeKingdomId(secondKingdomId))) {
                primaryKingdomId = secondKingdomId;
                secondaryKingdomId = firstKingdomId;
            }
            String title = KingdomWarService.readableKingdomName(primaryKingdomId) + " Made Peace";
            String description = KingdomWarService.readableKingdomName(secondaryKingdomId);
            String topTextureId = KingdomWarService.sigilTextureId(primaryKingdomId);
            String bottomTextureId = KingdomWarService.sigilTextureId(secondaryKingdomId);
            int color = playerKingdomId.equals(firstKingdomId) || playerKingdomId.equals(secondKingdomId) ? -10497171 : -727358;
            ModAdvancementService.send(player, "kingdom_war_peace", title, description, class_1802.field_8255, color, color, topTextureId, bottomTextureId);
        }
    }

    private static String readableKingdomName(String kingdomId) {
        class_2960 parsed = class_2960.method_12829((String)kingdomId);
        Kingdom kingdom = parsed == null ? null : GotKingdoms.get(parsed);
        return kingdom == null ? kingdomId : kingdom.getName();
    }

    private static String sigilTextureId(String kingdomId) {
        return switch (KingdomWarService.normalizeKingdomId(kingdomId)) {
            case "agob:the_north" -> "agob:textures/ui/sigil/sigil_the_north.png";
            case "agob:the_nights_watch" -> "agob:textures/ui/sigil/sigil_the_nights_watch.png";
            case "agob:wildling" -> "agob:textures/ui/sigil/sigil_blank.png";
            case "agob:the_vale" -> "agob:textures/ui/sigil/sigil_the_vale.png";
            case "agob:the_riverlands" -> "agob:textures/ui/sigil/sigil_the_riverlands.png";
            case "agob:the_westerlands" -> "agob:textures/ui/sigil/sigil_the_westerlands.png";
            case "agob:iron_islands" -> "agob:textures/ui/sigil/sigil_the_ironborn.png";
            case "agob:the_reach" -> "agob:textures/ui/sigil/sigil_the_reach.png";
            case "agob:the_stormlands" -> "agob:textures/ui/sigil/sigil_the_stormlands.png";
            case "agob:dorne" -> "agob:textures/ui/sigil/sigil_dorne.png";
            case "agob:the_crownlands" -> "agob:textures/ui/sigil/sigil_the_crownlands.png";
            case "agob:targaryen" -> "agob:textures/ui/sigil/sigil_targaryen.png";
            case "agob:braavos" -> "agob:textures/ui/sigil/sigil_braavos.png";
            case "agob:pentos" -> "agob:textures/ui/sigil/sigil_pentos.png";
            case "agob:volantis" -> "agob:textures/ui/sigil/sigil_volantis.png";
            case "agob:tyrosh" -> "agob:textures/ui/sigil/sigil_tyrosh.png";
            case "agob:myr" -> "agob:textures/ui/sigil/sigil_pahl.png";
            case "agob:blackfyre" -> "agob:textures/ui/sigil/sigil_blackfyre.png";
            case "agob:golden_company" -> "agob:textures/ui/sigil/sigil_goldencompany.png";
            case "agob:meereen" -> "agob:textures/ui/sigil/sigil_meereen.png";
            case "agob:yunkai" -> "agob:textures/ui/sigil/sigil_yunkai.png";
            case "agob:astapor" -> "agob:textures/ui/sigil/sigil_astapor.png";
            case "agob:qarth" -> "agob:textures/ui/sigil/sigil_qarth.png";
            default -> "";
        };
    }

    private static long resolveWarRefreshDay(KingdomRelationsWorldState state, class_3218 world, long queuedManualWarDays) {
        long day = KingdomWarService.currentWorldDay(world);
        if (queuedManualWarDays > 0L) {
            long minimumQueuedDay = Math.max(0L, state.getLastWarRefreshDay()) + queuedManualWarDays;
            day = Math.max(day, minimumQueuedDay);
        }
        long currentTimeOfDay = KingdomWarService.normalizedTimeOfDay(world.method_8532());
        long lastObserved = state.getLastObservedWarTimeOfDay();
        if (GotGameRules.affectedByTimeSkip(world) && lastObserved >= 0L && currentTimeOfDay < lastObserved) {
            return Math.max(day, state.getLastWarRefreshDay() + 1L);
        }
        return day;
    }

    private static long currentWorldDay(class_3218 world) {
        return GotGameRules.affectedByTimeSkip(world) ? GotGameRules.skipAwareDay(world.method_8510(), world.method_8532()) : GotGameRules.regularDay(world.method_8510());
    }

    private static long normalizedTimeOfDay(long timeOfDay) {
        long normalized = timeOfDay % 24000L;
        return normalized < 0L ? normalized + 24000L : normalized;
    }

    private static long scheduledWarDeclarationTimeOfDay(class_3218 world, long day, String attackerKingdomId, String defenderKingdomId) {
        long span = Math.max(1L, 20200L);
        double roll = KingdomWarService.deterministicWarRoll(world, day, attackerKingdomId, defenderKingdomId, "war_schedule");
        return 1800L + Math.min(span, (long)Math.floor(roll * (double)span));
    }

    private static String normalizeKingdomId(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return "";
        }
        class_2960 parsed = class_2960.method_12829((String)kingdomId);
        if (parsed == null || GotKingdoms.get(parsed) == null) {
            return "";
        }
        return parsed.toString();
    }

    private static boolean isAutonomousWarKingdom(String kingdomId) {
        return !Objects.equals("agob:the_nights_watch", KingdomWarService.normalizeKingdomId(kingdomId));
    }

    private static List<GotCastles.StrategicCastleSnapshot> castlesForKingdom(class_3218 world, String kingdomId) {
        if (world == null || kingdomId == null || kingdomId.isBlank()) {
            return List.of();
        }
        class_2960 parsedKingdomId = class_2960.method_12829((String)kingdomId);
        boolean dynamicKingdom = GotKingdoms.isDynamicKingdom(parsedKingdomId);
        List<GotCastles.StrategicCastleSnapshot> castles = GotCastles.getStrategicCastlesForKingdom(world, kingdomId).stream().filter(snapshot -> dynamicKingdom || snapshot.ownerPlayerUuid() == null).toList();
        if (!castles.isEmpty() || !GotKingdoms.isCastlelessOriginKingdom(kingdomId)) {
            return castles;
        }
        GotCastles.StrategicCastleSnapshot origin = GotCastles.getCastlelessKingdomInvasionOrigin(world, kingdomId);
        return origin == null ? List.of() : List.of(origin);
    }

    private static FrontierScore frontierScore(List<GotCastles.StrategicCastleSnapshot> attackerCastles, List<GotCastles.StrategicCastleSnapshot> defenderCastles) {
        double bestSquaredDistance = Double.MAX_VALUE;
        for (GotCastles.StrategicCastleSnapshot attackerCastle : attackerCastles) {
            class_2338 attackerPos = attackerCastle.bannerPos();
            for (GotCastles.StrategicCastleSnapshot defenderCastle : defenderCastles) {
                double squaredDistance = attackerPos.method_10262((class_2382)defenderCastle.bannerPos());
                if (!(squaredDistance < bestSquaredDistance)) continue;
                bestSquaredDistance = squaredDistance;
            }
        }
        if (bestSquaredDistance == Double.MAX_VALUE) {
            return new FrontierScore(Double.MAX_VALUE, false, false);
        }
        double distanceBlocks = Math.sqrt(bestSquaredDistance);
        boolean adjacent = distanceBlocks <= 3200.0;
        boolean near = distanceBlocks <= 5200.0;
        return new FrontierScore(distanceBlocks, adjacent, near);
    }

    private static boolean hasHarbourCastle(List<GotCastles.StrategicCastleSnapshot> castles) {
        for (GotCastles.StrategicCastleSnapshot castle : castles) {
            if (!castle.harbourCastle()) continue;
            return true;
        }
        return false;
    }

    private static int computeWarTargetWeight(int loreWeight, FrontierScore frontier, int defenderWarCount, boolean harbourLinked) {
        if (frontier.distanceBlocks() == Double.MAX_VALUE) {
            return 0;
        }
        int score = loreWeight * 12;
        if (frontier.adjacent()) {
            score += 70;
        } else if (frontier.near()) {
            score += 35;
        } else if (harbourLinked) {
            score += 22;
        }
        double closenessFactor = 1.0 - Math.min(1.0, frontier.distanceBlocks() / 12000.0);
        score += (int)Math.round(closenessFactor * 45.0);
        return Math.max(1, score += Math.min(18, defenderWarCount * 3));
    }

    private static double deterministicWarRoll(class_3218 world, long day, String ... parts) {
        long seed = world.method_8412() ^ day * 341873128712L;
        for (String part : parts) {
            seed = seed * 31L ^ (part == null ? 0L : (long)part.hashCode());
        }
        seed ^= seed >>> 33;
        seed *= -49064778989728563L;
        seed ^= seed >>> 33;
        seed *= -4265267296055464877L;
        seed ^= seed >>> 33;
        return (double)(seed >>> 11 & 0x1FFFFFFFFFFFFFL) / 9.007199254740992E15;
    }

    public record FrontierScore(double distanceBlocks, boolean adjacent, boolean near) {
    }

    private record WeightedTarget(String kingdomId, int weight, double tieBreaker) {
    }
}

