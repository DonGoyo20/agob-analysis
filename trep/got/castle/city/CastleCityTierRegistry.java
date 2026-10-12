/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle.city;

import java.util.EnumMap;
import java.util.Map;
import trep.got.castle.CastleTier;
import trep.got.castle.city.CastleCityTierPlan;

public final class CastleCityTierRegistry {
    private static final Map<CastleTier, CastleCityTierPlan> PLANS = new EnumMap<CastleTier, CastleCityTierPlan>(CastleTier.class);

    private CastleCityTierRegistry() {
    }

    private static void register(CastleCityTierPlan plan) {
        PLANS.put(plan.tier(), plan);
    }

    public static CastleCityTierPlan forTier(CastleTier tier) {
        return PLANS.getOrDefault((Object)tier, PLANS.get((Object)CastleTier.SETTLEMENT));
    }

    public static int maxClaimRadiusChunks() {
        return PLANS.values().stream().mapToInt(CastleCityTierPlan::claimRadiusChunks).max().orElse(9);
    }

    static {
        CastleCityTierRegistry.register(new CastleCityTierPlan(CastleTier.SETTLEMENT, 9, 20, 14, 2, 1, 1, 1, 1));
        CastleCityTierRegistry.register(new CastleCityTierPlan(CastleTier.LORDSHIP, 10, 22, 18, 3, 1, 1, 1, 1));
        CastleCityTierRegistry.register(new CastleCityTierPlan(CastleTier.CAPITAL, 11, 24, 21, 3, 1, 1, 1, 1));
    }
}

