/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry;

import java.util.List;
import trep.got.registry.ArenaBoundary;

record ArenaBoundaryCache(long expiresAtTick, List<ArenaBoundary> arenas) {
}

