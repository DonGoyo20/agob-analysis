/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_3341
 */
package trep.got.world.structure.castle;

import java.util.List;
import java.util.Set;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_3341;
import trep.got.castle.CastlePlacementPlan;
import trep.got.castle.CastleSubtype;
import trep.got.castle.CastleTier;
import trep.got.castle.WallGateClaim;
import trep.got.registry.castle.overlay.CastlePresetMetadata;
import trep.got.registry.castle.overlay.OverlayCastleSite;
import trep.got.world.structure.castle.CastleWorldgenStructurePlan;
import trep.got.world.structure.castle.DynamicCastleCoreWorldgenPlan;
import trep.got.world.structure.castle.DynamicCastleWallPlan;
import trep.got.world.structure.castle.DynamicCastleWorldgenFootprint;
import trep.got.world.structure.castle.road.CastleRoadNetworkPlan;
import trep.got.world.structure.plan.StructureChunkFootprint;
import trep.got.world.structure.plan.StructurePlan;

public record CastleWorldgenPlan(String castleId, String definitionId, String displayName, OverlayCastleSite site, CastlePresetMetadata metadata, CastlePlacementPlan placementPlan, CastleTier effectiveTier, Set<CastleSubtype> subtypes, String structureKingdomId, DynamicCastleWorldgenFootprint dynamicFootprint, DynamicCastleCoreWorldgenPlan corePlan, boolean northSouthGates, List<class_1923> buildingChunks, DynamicCastleWallPlan wallPlan, List<CastleWorldgenStructurePlan> structures, CastleRoadNetworkPlan roadNetworkPlan, List<CastleWorldgenStructurePlan> natureStructures, List<WallGateClaim> wallGateClaims, class_3341 blockBounds, StructureChunkFootprint chunkFootprint) implements StructurePlan
{
    @Override
    public String structureId() {
        return this.castleId;
    }

    public class_2338 anchor() {
        return this.site.anchor();
    }

    public class_2338 origin() {
        return this.placementPlan.origin();
    }
}

