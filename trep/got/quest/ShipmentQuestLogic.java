/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 */
package trep.got.quest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import trep.got.castle.CastleInstance;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.kingdom.ReputationTitle;
import trep.got.quest.QuestInstance;
import trep.got.quest.QuestTemplateRegistry;
import trep.got.registry.GotCastles;

public final class ShipmentQuestLogic {
    public static final String TEMPLATE_ID = "castle_shipment_delivery";
    public static final String SHIPMENT_ITEM_ID = "agob:shipment_item";
    private static Function<class_1937, List<CastleTarget>> clientCastleTargets = world -> List.of();

    private ShipmentQuestLogic() {
    }

    public static void setClientCastleTargetsProvider(Function<class_1937, List<CastleTarget>> provider) {
        clientCastleTargets = provider == null ? world -> List.of() : provider;
    }

    public static List<CastleTarget> collectTargets(class_1937 world) {
        if (world == null) {
            return List.of();
        }
        if (!world.method_8608() && world instanceof class_3218) {
            class_3218 serverWorld = (class_3218)world;
            return GotCastles.collectQuestTargets(serverWorld);
        }
        return List.copyOf((Collection)clientCastleTargets.apply(world));
    }

    public static QuestInstance createQuest(UUID playerUuid, AbstractKingdomNpcEntity npc, long worldTime, long offerCycle, ReputationTitle reputationTitle) {
        if (playerUuid == null || npc == null || npc.got$getCastle().isBlank()) {
            return null;
        }
        List<CastleTarget> castles = ShipmentQuestLogic.collectTargets(npc.method_37908());
        CastleTarget source = castles.stream().filter(castle -> castle.id().equals(npc.got$getCastle())).findFirst().orElse(null);
        if (source == null) {
            return null;
        }
        ArrayList<CandidateTarget> eligible = new ArrayList<CandidateTarget>();
        for (CastleTarget castle2 : castles) {
            double distance;
            if (castle2.id().equals(source.id()) || (distance = ShipmentQuestLogic.horizontalDistance(source, castle2)) < 5000.0 || distance > 10000.0) continue;
            eligible.add(new CandidateTarget(castle2, distance));
        }
        if (eligible.isEmpty()) {
            return null;
        }
        eligible.sort(Comparator.comparing(candidate -> candidate.target().id()));
        long seed = QuestTemplateRegistry.computeOfferSeed(playerUuid, npc.method_5667(), offerCycle, TEMPLATE_ID);
        CandidateTarget chosen = (CandidateTarget)eligible.get(Math.floorMod((int)(seed ^ seed >>> 32), eligible.size()));
        String shipmentDisplayItemId = switch (Math.floorMod((int)(seed * 31L ^ seed >>> 17), 3)) {
            case 1 -> class_2960.method_60656((String)"coal").toString();
            case 2 -> class_2960.method_60656((String)"gold_ingot").toString();
            default -> class_2960.method_60656((String)"iron_ingot").toString();
        };
        int distanceBonusSteps = Math.max(0, (int)Math.floor((chosen.distance() - 5000.0) / 500.0));
        int reputationExperience = ShipmentQuestLogic.applyTitleBonus((int)Math.round(40.0 * (1.0 + (double)distanceBonusSteps * 0.05)), reputationTitle);
        int goldIngots = 4 + distanceBonusSteps;
        int skillPoints = ShipmentQuestLogic.rollTradingSkillPoints(seed, distanceBonusSteps);
        long durationDays = 3L + Math.max(0L, Math.round((chosen.distance() - 5000.0) / 1800.0));
        long dueAtTick = worldTime + durationDays * 24000L;
        String questId = "quest_" + Math.abs((playerUuid.toString() + npc.method_5845() + worldTime + TEMPLATE_ID + chosen.target().id()).hashCode());
        return new QuestInstance(questId, TEMPLATE_ID, npc.method_5667(), npc.method_5476().getString(), QuestTemplateRegistry.offerKey(npc.method_5667(), offerCycle), SHIPMENT_ITEM_ID, 1, worldTime, dueAtTick, reputationExperience, goldIngots, 0, "trading", skillPoints, "", 0, KingdomNpcRole.BLACKSMITH.name(), chosen.target().id(), chosen.target().name(), "", shipmentDisplayItemId, new class_2487());
    }

    public static String objectiveLine(String castleName) {
        return "Deliver this shipment to the blacksmith at " + castleName + ".";
    }

    private static int applyTitleBonus(int reputationExperience, ReputationTitle title) {
        if (title == ReputationTitle.PEASANT) {
            return reputationExperience + 2;
        }
        return reputationExperience;
    }

    private static int rollTradingSkillPoints(long seed, int distanceBonusSteps) {
        int points = 1;
        if (ShipmentQuestLogic.rollPercent(seed ^ 0x5F3759D5L, 25 + distanceBonusSteps * 5)) {
            ++points;
        }
        if (points >= 2 && ShipmentQuestLogic.rollPercent(seed ^ 0x9E3779B97F4A7C15L, 10 + distanceBonusSteps * 4)) {
            ++points;
        }
        return Math.min(3, points);
    }

    private static boolean rollPercent(long seed, int percent) {
        int clamped = Math.max(0, Math.min(100, percent));
        return Math.floorMod((int)(seed * 17L ^ seed >>> 23), 100) < clamped;
    }

    private static CastleTarget toTarget(CastleInstance castle) {
        if (castle == null) {
            return null;
        }
        return new CastleTarget(castle.getId(), castle.getTitle().displayName(), castle.getLordName(), castle.getBannerPos().method_10263(), castle.getBannerPos().method_10260());
    }

    private static double horizontalDistance(CastleTarget source, CastleTarget target) {
        long dx = (long)target.worldX() - (long)source.worldX();
        long dz = (long)target.worldZ() - (long)source.worldZ();
        return Math.sqrt(dx * dx + dz * dz);
    }

    public record CastleTarget(String id, String name, String lordName, int worldX, int worldZ) {
    }

    private record CandidateTarget(CastleTarget target, double distance) {
    }
}

