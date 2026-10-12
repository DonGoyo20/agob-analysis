/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import org.jetbrains.annotations.Nullable;
import trep.got.registry.HarbourNpcPlacement;

record FishermanSpawnResolution(@Nullable HarbourNpcPlacement placement, boolean success, String reason, String detail) {
    static FishermanSpawnResolution success(HarbourNpcPlacement placement, String detail) {
        return new FishermanSpawnResolution(placement, true, "spawned", detail == null ? "" : detail);
    }

    static FishermanSpawnResolution failed(String reason, String detail) {
        return new FishermanSpawnResolution(null, false, reason, detail == null ? "" : detail);
    }
}

