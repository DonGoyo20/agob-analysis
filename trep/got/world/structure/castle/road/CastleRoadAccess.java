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

public record CastleRoadAccess(String id, String structurePath, String obstacleId, CastleRoadBounds structureFootprint, class_2350 outwardFacing, int width, class_2338 threshold, class_2338 exteriorCenter) {
    public CastleRoadAccess {
        if (id == null || id.isBlank() || structurePath == null || structurePath.isBlank() || obstacleId == null || obstacleId.isBlank() || structureFootprint == null || outwardFacing == null || threshold == null || exteriorCenter == null) {
            throw new IllegalArgumentException("Incomplete castle structure access");
        }
        width = Math.max(1, width);
    }
}

