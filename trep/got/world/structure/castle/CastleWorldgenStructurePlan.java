/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_2470
 *  net.minecraft.class_3341
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.world.structure.castle;

import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2470;
import net.minecraft.class_3341;
import org.jetbrains.annotations.Nullable;
import trep.got.castle.city.CastleStructureResidentPlan;

public record CastleWorldgenStructurePlan(@Nullable CastleStructureResidentPlan residentPlan, String structurePath, class_2470 rotation, class_2338 placementOrigin, class_3341 footprint, class_2382 size, class_2338 pointOfInterest, class_2338 residentSpawnPos) {
    public boolean intersectsChunk(class_1923 chunkPos) {
        return this.footprint.method_35415() <= chunkPos.method_8327() && this.footprint.method_35418() >= chunkPos.method_8326() && this.footprint.method_35417() <= chunkPos.method_8329() && this.footprint.method_35420() >= chunkPos.method_8328();
    }
}

