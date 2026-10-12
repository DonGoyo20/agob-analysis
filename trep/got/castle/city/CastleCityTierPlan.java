/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle.city;

import trep.got.castle.CastleTier;

public record CastleCityTierPlan(CastleTier tier, int claimRadiusChunks, int flattenFootprintChunks, int houseCount, int marketCount, int blacksmithCount, int tavernCount, int arenaCount, int barnCount) {
    public CastleCityTierPlan {
        if (marketCount < 1) {
            throw new IllegalArgumentException("Castle city tier plans must spawn at least 1 market structure.");
        }
    }

    public int totalBaseStructures() {
        return this.houseCount + this.marketCount + this.blacksmithCount + this.arenaCount + this.tavernCount + this.barnCount;
    }

    public int totalInteriorStructures() {
        return this.houseCount + this.marketCount + this.blacksmithCount + this.arenaCount + this.tavernCount;
    }
}

