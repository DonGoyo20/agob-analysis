/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 */
package trep.got.castle;

import net.minecraft.class_2338;
import trep.got.castle.CastleTemplateKey;
import trep.got.castle.CastleTier;

public record CastlePlacementPlan(String castleId, CastleTier tier, CastleTemplateKey templateKey, class_2338 origin, int floorY, int minSurfaceY, int maxSurfaceY, int footprintChunks, int footprintBlocks, int blendRadius) {
}

