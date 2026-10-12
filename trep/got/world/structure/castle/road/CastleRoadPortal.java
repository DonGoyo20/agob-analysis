/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 */
package trep.got.world.structure.castle.road;

import net.minecraft.class_2338;
import net.minecraft.class_2350;
import trep.got.world.structure.castle.road.CastleRoadBounds;

public record CastleRoadPortal(String id, class_2350 outwardFacing, int apertureWidth, int apronLength, class_2338 threshold, class_2338 interiorCenter, class_2338 exteriorCenter, CastleRoadBounds aperture) {
    public CastleRoadPortal {
        if (id == null || id.isBlank() || outwardFacing == null || threshold == null || interiorCenter == null || exteriorCenter == null || aperture == null) {
            throw new IllegalArgumentException("Incomplete castle road portal");
        }
        apertureWidth = Math.max(1, apertureWidth);
        apronLength = Math.max(1, apronLength);
    }
}

