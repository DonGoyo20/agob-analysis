/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 */
package trep.got.client.relations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import trep.got.network.KingdomRelationsPayload;
import trep.got.relations.KingdomRelationTier;
import trep.got.relations.KingdomRelationsService;

@Environment(value=EnvType.CLIENT)
public final class KingdomRelationsClientState {
    private static Map<String, Map<String, Integer>> kingdomRelations = Map.of();
    private static Map<String, Integer> playerRelations = Map.of();
    private static Map<String, List<String>> activeWarsByKingdom = Map.of();
    private static String playerKingdomId = "";
    private static boolean playerKing;
    private static Map<String, Long> diplomacyCooldownEndsAtTickByKingdom;

    private KingdomRelationsClientState() {
    }

    public static void apply(KingdomRelationsPayload payload, class_310 client) {
        client.execute(() -> {
            LinkedHashMap<String, Map> nextKingdomRelations = new LinkedHashMap<String, Map>();
            for (KingdomRelationsPayload.KingdomEntry kingdomEntry : payload.kingdomRelations()) {
                nextKingdomRelations.computeIfAbsent(kingdomEntry.fromKingdomId(), ignored -> new LinkedHashMap()).put(kingdomEntry.toKingdomId(), kingdomEntry.relation());
            }
            LinkedHashMap<String, Integer> nextPlayerRelations = new LinkedHashMap<String, Integer>();
            for (KingdomRelationsPayload.PlayerEntry playerEntry : payload.playerRelations()) {
                nextPlayerRelations.put(playerEntry.kingdomId(), playerEntry.relation());
            }
            LinkedHashMap<String, List> linkedHashMap = new LinkedHashMap<String, List>();
            for (KingdomRelationsPayload.WarEntry entry : payload.warEntries()) {
                linkedHashMap.computeIfAbsent(entry.attackerKingdomId(), ignored -> new ArrayList()).add(entry.defenderKingdomId());
                linkedHashMap.computeIfAbsent(entry.defenderKingdomId(), ignored -> new ArrayList()).add(entry.attackerKingdomId());
            }
            kingdomRelations = Map.copyOf(nextKingdomRelations);
            playerRelations = Map.copyOf(nextPlayerRelations);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                linkedHashMap2.put((String)entry.getKey(), List.copyOf((Collection)entry.getValue()));
            }
            activeWarsByKingdom = Map.copyOf(linkedHashMap2);
            playerKingdomId = payload.playerKingdomId();
            playerKing = payload.playerKing();
            diplomacyCooldownEndsAtTickByKingdom = Map.copyOf(payload.diplomacyCooldownEndsAtTickByKingdom());
        });
    }

    public static int getKingdomRelation(String fromKingdomId, String toKingdomId) {
        if (fromKingdomId == null || toKingdomId == null) {
            return 0;
        }
        if (fromKingdomId.equals(toKingdomId)) {
            return 100;
        }
        return kingdomRelations.getOrDefault(fromKingdomId, Map.of()).getOrDefault(toKingdomId, 0);
    }

    public static int getRawPlayerRelation(String kingdomId) {
        return playerRelations.getOrDefault(kingdomId, 0);
    }

    public static int getEffectivePlayerRelation(String playerKingdomId, String targetKingdomId) {
        int raw = KingdomRelationsClientState.getRawPlayerRelation(targetKingdomId);
        if (playerKingdomId == null || playerKingdomId.isBlank()) {
            return raw;
        }
        int kingdomRelation = KingdomRelationsClientState.getKingdomRelation(playerKingdomId, targetKingdomId);
        return KingdomRelationsService.computeEffectivePlayerRelation(playerKingdomId, raw, kingdomRelation);
    }

    public static KingdomRelationTier getEffectivePlayerRelationTier(String playerKingdomId, String targetKingdomId) {
        return KingdomRelationTier.fromValue(KingdomRelationsClientState.getEffectivePlayerRelation(playerKingdomId, targetKingdomId));
    }

    public static List<String> getActiveWarTargets(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return List.of();
        }
        return activeWarsByKingdom.getOrDefault(kingdomId, List.of());
    }

    public static boolean isAtWar(String firstKingdomId, String secondKingdomId) {
        if (firstKingdomId == null || secondKingdomId == null || firstKingdomId.isBlank() || secondKingdomId.isBlank()) {
            return false;
        }
        return KingdomRelationsClientState.getActiveWarTargets(firstKingdomId).contains(secondKingdomId);
    }

    public static boolean isPlayerKing() {
        return playerKing;
    }

    public static String getPlayerKingdomId() {
        return playerKingdomId;
    }

    public static long getDiplomacyCooldownEndsAtTick(String targetKingdomId) {
        if (targetKingdomId == null || targetKingdomId.isBlank()) {
            return 0L;
        }
        return diplomacyCooldownEndsAtTickByKingdom.getOrDefault(targetKingdomId, 0L);
    }

    public static void clear() {
        kingdomRelations = Map.of();
        playerRelations = Map.of();
        activeWarsByKingdom = Map.of();
        playerKingdomId = "";
        playerKing = false;
        diplomacyCooldownEndsAtTickByKingdom = Map.of();
    }

    static {
        diplomacyCooldownEndsAtTickByKingdom = Map.of();
    }
}

