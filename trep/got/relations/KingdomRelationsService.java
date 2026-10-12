/*
 * Decompiled with CFR 0.152.
 */
package trep.got.relations;

import trep.got.relations.KingdomRelationTier;

public final class KingdomRelationsService {
    private KingdomRelationsService() {
    }

    public static int clamp(int relation) {
        return Math.max(-100, Math.min(100, relation));
    }

    public static int computeEffectivePlayerRelation(String playerKingdomId, int rawPlayerRelation, int kingdomRelation) {
        if (playerKingdomId == null || playerKingdomId.isBlank()) {
            return KingdomRelationsService.clamp(rawPlayerRelation);
        }
        float modifier = KingdomRelationTier.fromValue(kingdomRelation).getPlayerModifier();
        return KingdomRelationsService.clamp(Math.round((float)rawPlayerRelation * modifier));
    }
}

