/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle;

public record CastlePlacementRules(int footprintChunks, int scanRadiusBlocks, int surfaceSampleStep, int maxTerrainDelta, int maxWaterSamples, int padDepth, int blendRadius) {
    public static CastlePlacementRules defaultRules() {
        return new CastlePlacementRules(3, 48, 8, 10, 4, 6, 8);
    }
}

