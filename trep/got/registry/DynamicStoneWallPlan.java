/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry;

import java.util.Set;

record DynamicStoneWallPlan(int outerMinX, int outerMinZ, int outerWidth, int outerDepth, boolean northSouthGates, int roadX, int roadZ, Set<Long> interiorChunkKeys) {
}

