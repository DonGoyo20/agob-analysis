/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry;

import trep.got.registry.GotCastles;

record WarOpeningCandidate(GotCastles.StrategicCastleSnapshot invadingCastle, GotCastles.StrategicCastleSnapshot targetCastle, int weight, double distanceBlocks, boolean relaxed) {
}

