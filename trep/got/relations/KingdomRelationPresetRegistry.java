/*
 * Decompiled with CFR 0.152.
 */
package trep.got.relations;

import java.util.LinkedHashMap;
import java.util.Map;
import trep.got.kingdom.Kingdom;
import trep.got.registry.GotKingdoms;
import trep.got.relations.KingdomRelationsService;

public final class KingdomRelationPresetRegistry {
    private KingdomRelationPresetRegistry() {
    }

    public static Map<String, Map<String, Integer>> createPresetMatrix() {
        LinkedHashMap<String, Map<String, Integer>> matrix = new LinkedHashMap<String, Map<String, Integer>>();
        for (Kingdom kingdom : GotKingdoms.getAll()) {
            LinkedHashMap<String, Integer> row = new LinkedHashMap<String, Integer>();
            for (Kingdom other : GotKingdoms.getAll()) {
                row.put(other.getId().toString(), kingdom.getId().equals((Object)other.getId()) ? 100 : 0);
            }
            matrix.put(kingdom.getId().toString(), row);
        }
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_north", "agob:the_westerlands", -100);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_north", "agob:the_crownlands", -60);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_north", "agob:the_iron_islands", -40);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_north", "agob:the_riverlands", 60);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_north", "agob:the_vale", 40);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_nights_watch", "agob:the_north", 40);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_nights_watch", "agob:wildling", -100);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:wildling", "agob:the_north", -20);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_vale", "agob:the_riverlands", 40);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_vale", "agob:the_crownlands", -20);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_riverlands", "agob:the_westerlands", -100);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_riverlands", "agob:the_iron_islands", -60);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_riverlands", "agob:the_vale", 40);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_westerlands", "agob:the_crownlands", 40);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_westerlands", "agob:the_reach", 20);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_iron_islands", "agob:the_reach", -20);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_reach", "agob:the_stormlands", 20);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_reach", "agob:the_crownlands", 40);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:the_stormlands", "agob:the_crownlands", 40);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:dorne", "agob:the_reach", -20);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:dorne", "agob:the_stormlands", -20);
        KingdomRelationPresetRegistry.setMutual(matrix, "agob:dorne", "agob:the_crownlands", -20);
        return matrix;
    }

    private static void setMutual(Map<String, Map<String, Integer>> matrix, String firstKingdomId, String secondKingdomId, int relation) {
        KingdomRelationPresetRegistry.set(matrix, firstKingdomId, secondKingdomId, relation);
        KingdomRelationPresetRegistry.set(matrix, secondKingdomId, firstKingdomId, relation);
    }

    private static void set(Map<String, Map<String, Integer>> matrix, String fromKingdomId, String toKingdomId, int relation) {
        Map<String, Integer> row = matrix.get(fromKingdomId);
        if (row != null && row.containsKey(toKingdomId)) {
            row.put(toKingdomId, KingdomRelationsService.clamp(relation));
        }
    }
}

