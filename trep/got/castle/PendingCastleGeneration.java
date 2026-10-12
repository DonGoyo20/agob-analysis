/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_3341
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.castle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_3341;
import org.jetbrains.annotations.Nullable;
import trep.got.castle.CastleSubtype;
import trep.got.castle.CastleTier;
import trep.got.castle.PendingCastleGenerationStage;
import trep.got.castle.WallGateClaim;
import trep.got.world.structure.castle.road.CastleRoadNetworkPlan;

public final class PendingCastleGeneration {
    public static final int LEGACY_ROAD_LAYOUT_VERSION = 1;
    public static final int CURRENT_ROAD_LAYOUT_VERSION = 2;
    private final String castleId;
    private final String definitionId;
    private final int siteIndex;
    private final class_2338 anchor;
    private PendingCastleGenerationStage stage = PendingCastleGenerationStage.PLAN;
    private CastleTier tier = CastleTier.SETTLEMENT;
    private boolean dynamic;
    private String templateId = "";
    private class_2338 planOrigin = class_2338.field_10980;
    private int floorY;
    private int footprintBlocks;
    private int blendRadius;
    private class_2338 structureOrigin = class_2338.field_10980;
    private class_2382 structureSize = class_2382.field_11176;
    private class_2338 bannerPos = class_2338.field_10980;
    private String kingdomId = "";
    private String houseId = "";
    private String religionId = "";
    private String lordName = "";
    @Nullable
    private UUID ownerPlayerUuid;
    private final List<CastleSubtype> subtypes = new ArrayList<CastleSubtype>();
    private boolean northSouthGates;
    private int nextStructureIndex;
    private int corePhase;
    private int coreCursor;
    private boolean worldgenShellPlaced;
    private boolean dynamicCoreUsesCastleKeep;
    private int dynamicCoreClearanceHeight;
    private int[] dynamicCoreNorthEdgeHeights = new int[0];
    private int[] dynamicCoreSouthEdgeHeights = new int[0];
    private int[] dynamicCoreWestEdgeHeights = new int[0];
    private int[] dynamicCoreEastEdgeHeights = new int[0];
    private int nextStructureChunkCursor;
    private int roadLayoutVersion = 1;
    private String roadPlanFingerprint = "";
    private int infrastructurePhase;
    private int nextWallChunkCursor;
    private int nextNatureStructureCursor;
    private int nextRoadChunkCursor;
    private int nextRoadCellCursor;
    @Nullable
    private transient CastleRoadNetworkPlan verifiedRoadPlan;
    private long lastBlockedLogTick = Long.MIN_VALUE;
    private final List<PlannedStructureState> plannedStructures = new ArrayList<PlannedStructureState>();
    private final List<ChunkPosState> dynamicBuildingChunks = new ArrayList<ChunkPosState>();
    private final List<ChunkPosState> structureCandidateChunks = new ArrayList<ChunkPosState>();
    private final List<PlacedStructureState> placedStructures = new ArrayList<PlacedStructureState>();
    private final List<WallGateClaim> wallGateClaims = new ArrayList<WallGateClaim>();

    public PendingCastleGeneration(String castleId, String definitionId, int siteIndex, class_2338 anchor) {
        this.castleId = castleId == null ? "" : castleId;
        this.definitionId = definitionId == null ? "" : definitionId;
        this.siteIndex = siteIndex;
        this.anchor = anchor == null ? class_2338.field_10980 : anchor.method_10062();
    }

    public String castleId() {
        return this.castleId;
    }

    public String definitionId() {
        return this.definitionId;
    }

    public int siteIndex() {
        return this.siteIndex;
    }

    public class_2338 anchor() {
        return this.anchor;
    }

    public PendingCastleGenerationStage stage() {
        return this.stage;
    }

    public void setStage(PendingCastleGenerationStage stage) {
        this.stage = stage == null ? PendingCastleGenerationStage.PLAN : stage;
    }

    public CastleTier tier() {
        return this.tier;
    }

    public void setTier(CastleTier tier) {
        this.tier = tier == null ? CastleTier.SETTLEMENT : tier;
    }

    public boolean dynamic() {
        return this.dynamic;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }

    public String templateId() {
        return this.templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? "" : templateId;
    }

    public class_2338 planOrigin() {
        return this.planOrigin;
    }

    public void setPlanOrigin(class_2338 planOrigin) {
        this.planOrigin = planOrigin == null ? class_2338.field_10980 : planOrigin.method_10062();
    }

    public int floorY() {
        return this.floorY;
    }

    public void setFloorY(int floorY) {
        this.floorY = floorY;
    }

    public int footprintBlocks() {
        return this.footprintBlocks;
    }

    public void setFootprintBlocks(int footprintBlocks) {
        this.footprintBlocks = Math.max(0, footprintBlocks);
    }

    public int blendRadius() {
        return this.blendRadius;
    }

    public void setBlendRadius(int blendRadius) {
        this.blendRadius = Math.max(0, blendRadius);
    }

    public class_2338 structureOrigin() {
        return this.structureOrigin;
    }

    public void setStructureOrigin(class_2338 structureOrigin) {
        this.structureOrigin = structureOrigin == null ? class_2338.field_10980 : structureOrigin.method_10062();
    }

    public class_2382 structureSize() {
        return this.structureSize;
    }

    public void setStructureSize(class_2382 structureSize) {
        this.structureSize = structureSize == null ? class_2382.field_11176 : structureSize;
    }

    public class_2338 bannerPos() {
        return this.bannerPos;
    }

    public void setBannerPos(class_2338 bannerPos) {
        this.bannerPos = bannerPos == null ? class_2338.field_10980 : bannerPos.method_10062();
    }

    public String kingdomId() {
        return this.kingdomId;
    }

    public void setKingdomId(String kingdomId) {
        this.kingdomId = kingdomId == null ? "" : kingdomId;
    }

    public String houseId() {
        return this.houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId == null ? "" : houseId;
    }

    public String religionId() {
        return this.religionId;
    }

    public void setReligionId(String religionId) {
        this.religionId = religionId == null ? "" : religionId;
    }

    public String lordName() {
        return this.lordName;
    }

    public void setLordName(String lordName) {
        this.lordName = lordName == null ? "" : lordName;
    }

    @Nullable
    public UUID ownerPlayerUuid() {
        return this.ownerPlayerUuid;
    }

    public void setOwnerPlayerUuid(@Nullable UUID ownerPlayerUuid) {
        this.ownerPlayerUuid = ownerPlayerUuid;
    }

    public List<CastleSubtype> subtypes() {
        return List.copyOf(this.subtypes);
    }

    public void setSubtypes(List<CastleSubtype> subtypes) {
        this.subtypes.clear();
        if (subtypes != null) {
            this.subtypes.addAll(subtypes.stream().filter(subtype -> subtype != null).toList());
        }
    }

    public boolean northSouthGates() {
        return this.northSouthGates;
    }

    public void setNorthSouthGates(boolean northSouthGates) {
        this.northSouthGates = northSouthGates;
    }

    public int nextStructureIndex() {
        return this.nextStructureIndex;
    }

    public void setNextStructureIndex(int nextStructureIndex) {
        this.nextStructureIndex = Math.max(0, nextStructureIndex);
    }

    public int corePhase() {
        return this.corePhase;
    }

    public void setCorePhase(int corePhase) {
        this.corePhase = Math.max(0, corePhase);
    }

    public int coreCursor() {
        return this.coreCursor;
    }

    public void setCoreCursor(int coreCursor) {
        this.coreCursor = Math.max(0, coreCursor);
    }

    public boolean worldgenShellPlaced() {
        return this.worldgenShellPlaced;
    }

    public void setWorldgenShellPlaced(boolean worldgenShellPlaced) {
        this.worldgenShellPlaced = worldgenShellPlaced;
    }

    public boolean dynamicCoreUsesCastleKeep() {
        return this.dynamicCoreUsesCastleKeep;
    }

    public void setDynamicCoreUsesCastleKeep(boolean dynamicCoreUsesCastleKeep) {
        this.dynamicCoreUsesCastleKeep = dynamicCoreUsesCastleKeep;
    }

    public int dynamicCoreClearanceHeight() {
        return this.dynamicCoreClearanceHeight;
    }

    public void setDynamicCoreClearanceHeight(int dynamicCoreClearanceHeight) {
        this.dynamicCoreClearanceHeight = Math.max(0, dynamicCoreClearanceHeight);
    }

    public int[] dynamicCoreNorthEdgeHeights() {
        return this.dynamicCoreNorthEdgeHeights;
    }

    public void setDynamicCoreNorthEdgeHeights(int[] dynamicCoreNorthEdgeHeights) {
        this.dynamicCoreNorthEdgeHeights = dynamicCoreNorthEdgeHeights == null ? new int[]{} : (int[])dynamicCoreNorthEdgeHeights.clone();
    }

    public int[] dynamicCoreSouthEdgeHeights() {
        return this.dynamicCoreSouthEdgeHeights;
    }

    public void setDynamicCoreSouthEdgeHeights(int[] dynamicCoreSouthEdgeHeights) {
        this.dynamicCoreSouthEdgeHeights = dynamicCoreSouthEdgeHeights == null ? new int[]{} : (int[])dynamicCoreSouthEdgeHeights.clone();
    }

    public int[] dynamicCoreWestEdgeHeights() {
        return this.dynamicCoreWestEdgeHeights;
    }

    public void setDynamicCoreWestEdgeHeights(int[] dynamicCoreWestEdgeHeights) {
        this.dynamicCoreWestEdgeHeights = dynamicCoreWestEdgeHeights == null ? new int[]{} : (int[])dynamicCoreWestEdgeHeights.clone();
    }

    public int[] dynamicCoreEastEdgeHeights() {
        return this.dynamicCoreEastEdgeHeights;
    }

    public void setDynamicCoreEastEdgeHeights(int[] dynamicCoreEastEdgeHeights) {
        this.dynamicCoreEastEdgeHeights = dynamicCoreEastEdgeHeights == null ? new int[]{} : (int[])dynamicCoreEastEdgeHeights.clone();
    }

    public int nextStructureChunkCursor() {
        return this.nextStructureChunkCursor;
    }

    public void setNextStructureChunkCursor(int nextStructureChunkCursor) {
        this.nextStructureChunkCursor = Math.max(0, nextStructureChunkCursor);
    }

    public int roadLayoutVersion() {
        return this.roadLayoutVersion;
    }

    public void setRoadLayoutVersion(int roadLayoutVersion) {
        this.roadLayoutVersion = roadLayoutVersion == 2 ? 2 : 1;
    }

    public String roadPlanFingerprint() {
        return this.roadPlanFingerprint;
    }

    public void setRoadPlanFingerprint(String roadPlanFingerprint) {
        this.roadPlanFingerprint = roadPlanFingerprint == null ? "" : roadPlanFingerprint;
        this.verifiedRoadPlan = null;
    }

    @Nullable
    public CastleRoadNetworkPlan verifiedRoadPlan() {
        return this.verifiedRoadPlan;
    }

    public void setVerifiedRoadPlan(@Nullable CastleRoadNetworkPlan verifiedRoadPlan) {
        this.verifiedRoadPlan = verifiedRoadPlan;
    }

    public int nextRoadChunkCursor() {
        return this.nextRoadChunkCursor;
    }

    public void setNextRoadChunkCursor(int nextRoadChunkCursor) {
        this.nextRoadChunkCursor = Math.max(0, nextRoadChunkCursor);
    }

    public int nextRoadCellCursor() {
        return this.nextRoadCellCursor;
    }

    public void setNextRoadCellCursor(int nextRoadCellCursor) {
        this.nextRoadCellCursor = Math.max(0, nextRoadCellCursor);
    }

    public int infrastructurePhase() {
        return this.infrastructurePhase;
    }

    public void setInfrastructurePhase(int infrastructurePhase) {
        this.infrastructurePhase = Math.max(0, infrastructurePhase);
    }

    public int nextWallChunkCursor() {
        return this.nextWallChunkCursor;
    }

    public void setNextWallChunkCursor(int nextWallChunkCursor) {
        this.nextWallChunkCursor = Math.max(0, nextWallChunkCursor);
    }

    public int nextNatureStructureCursor() {
        return this.nextNatureStructureCursor;
    }

    public void setNextNatureStructureCursor(int nextNatureStructureCursor) {
        this.nextNatureStructureCursor = Math.max(0, nextNatureStructureCursor);
    }

    public long lastBlockedLogTick() {
        return this.lastBlockedLogTick;
    }

    public void setLastBlockedLogTick(long lastBlockedLogTick) {
        this.lastBlockedLogTick = lastBlockedLogTick;
    }

    public List<PlannedStructureState> plannedStructures() {
        return List.copyOf(this.plannedStructures);
    }

    public void setPlannedStructures(List<PlannedStructureState> plannedStructures) {
        this.plannedStructures.clear();
        if (plannedStructures != null) {
            this.plannedStructures.addAll(plannedStructures);
        }
    }

    public List<ChunkPosState> dynamicBuildingChunks() {
        return List.copyOf(this.dynamicBuildingChunks);
    }

    public void setDynamicBuildingChunks(List<ChunkPosState> dynamicBuildingChunks) {
        this.dynamicBuildingChunks.clear();
        if (dynamicBuildingChunks != null) {
            this.dynamicBuildingChunks.addAll(dynamicBuildingChunks);
        }
    }

    public List<ChunkPosState> structureCandidateChunks() {
        return List.copyOf(this.structureCandidateChunks);
    }

    public void setStructureCandidateChunks(List<ChunkPosState> structureCandidateChunks) {
        this.structureCandidateChunks.clear();
        if (structureCandidateChunks != null) {
            this.structureCandidateChunks.addAll(structureCandidateChunks);
        }
    }

    public List<PlacedStructureState> placedStructures() {
        return List.copyOf(this.placedStructures);
    }

    public void addPlacedStructure(PlacedStructureState placedStructure) {
        if (placedStructure != null) {
            this.placedStructures.add(placedStructure);
        }
    }

    public List<WallGateClaim> wallGateClaims() {
        return List.copyOf(this.wallGateClaims);
    }

    public void setWallGateClaims(List<WallGateClaim> wallGateClaims) {
        this.wallGateClaims.clear();
        if (wallGateClaims != null) {
            this.wallGateClaims.addAll(wallGateClaims);
        }
    }

    public boolean hasDynamicCoreEdgeHeightCache() {
        return this.dynamicCoreNorthEdgeHeights.length > 0 && this.dynamicCoreSouthEdgeHeights.length > 0 && this.dynamicCoreWestEdgeHeights.length > 0 && this.dynamicCoreEastEdgeHeights.length > 0;
    }

    public void clearDynamicCoreEdgeHeightCache() {
        this.dynamicCoreNorthEdgeHeights = new int[0];
        this.dynamicCoreSouthEdgeHeights = new int[0];
        this.dynamicCoreWestEdgeHeights = new int[0];
        this.dynamicCoreEastEdgeHeights = new int[0];
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("castle_id", this.castleId);
        nbt.method_10582("definition_id", this.definitionId);
        nbt.method_10569("site_index", this.siteIndex);
        nbt.method_10544("anchor", this.anchor.method_10063());
        nbt.method_10582("stage", this.stage.name());
        nbt.method_10582("tier", this.tier.getId());
        nbt.method_10556("dynamic", this.dynamic);
        nbt.method_10582("template_id", this.templateId);
        nbt.method_10544("plan_origin", this.planOrigin.method_10063());
        nbt.method_10569("floor_y", this.floorY);
        nbt.method_10569("footprint_blocks", this.footprintBlocks);
        nbt.method_10569("blend_radius", this.blendRadius);
        nbt.method_10544("structure_origin", this.structureOrigin.method_10063());
        nbt.method_10569("structure_size_x", this.structureSize.method_10263());
        nbt.method_10569("structure_size_y", this.structureSize.method_10264());
        nbt.method_10569("structure_size_z", this.structureSize.method_10260());
        nbt.method_10544("banner_pos", this.bannerPos.method_10063());
        nbt.method_10582("kingdom_id", this.kingdomId);
        nbt.method_10582("house_id", this.houseId);
        nbt.method_10582("religion_id", this.religionId);
        nbt.method_10582("lord_name", this.lordName);
        if (this.ownerPlayerUuid != null) {
            nbt.method_25927("owner_player_uuid", this.ownerPlayerUuid);
        }
        class_2499 subtypeList = new class_2499();
        for (CastleSubtype castleSubtype : this.subtypes) {
            subtypeList.add((Object)class_2519.method_23256((String)castleSubtype.getId()));
        }
        nbt.method_10566("subtypes", (class_2520)subtypeList);
        nbt.method_10556("north_south_gates", this.northSouthGates);
        nbt.method_10569("next_structure_index", this.nextStructureIndex);
        nbt.method_10569("core_phase", this.corePhase);
        nbt.method_10569("core_cursor", this.coreCursor);
        nbt.method_10556("worldgen_shell_placed", this.worldgenShellPlaced);
        nbt.method_10556("dynamic_core_uses_castle_keep", this.dynamicCoreUsesCastleKeep);
        nbt.method_10569("dynamic_core_clearance_height", this.dynamicCoreClearanceHeight);
        nbt.method_10539("dynamic_core_north_edge_heights", this.dynamicCoreNorthEdgeHeights);
        nbt.method_10539("dynamic_core_south_edge_heights", this.dynamicCoreSouthEdgeHeights);
        nbt.method_10539("dynamic_core_west_edge_heights", this.dynamicCoreWestEdgeHeights);
        nbt.method_10539("dynamic_core_east_edge_heights", this.dynamicCoreEastEdgeHeights);
        nbt.method_10569("next_structure_chunk_cursor", this.nextStructureChunkCursor);
        nbt.method_10569("road_layout_version", this.roadLayoutVersion);
        nbt.method_10582("road_plan_fingerprint", this.roadPlanFingerprint);
        nbt.method_10569("next_road_chunk_cursor", this.nextRoadChunkCursor);
        nbt.method_10569("next_road_cell_cursor", this.nextRoadCellCursor);
        nbt.method_10569("infrastructure_phase", this.infrastructurePhase);
        nbt.method_10569("next_wall_chunk_cursor", this.nextWallChunkCursor);
        nbt.method_10569("next_nature_structure_cursor", this.nextNatureStructureCursor);
        nbt.method_10544("last_blocked_log_tick", this.lastBlockedLogTick);
        class_2499 plannedList = new class_2499();
        for (PlannedStructureState plannedStructureState : this.plannedStructures) {
            plannedList.add((Object)plannedStructureState.toNbt());
        }
        nbt.method_10566("planned_structures", (class_2520)plannedList);
        class_2499 class_24992 = new class_2499();
        for (ChunkPosState chunkPosState : this.dynamicBuildingChunks) {
            class_24992.add((Object)chunkPosState.toNbt());
        }
        nbt.method_10566("dynamic_building_chunks", (class_2520)class_24992);
        class_2499 class_24993 = new class_2499();
        for (ChunkPosState chunkPosState : this.structureCandidateChunks) {
            class_24993.add((Object)chunkPosState.toNbt());
        }
        nbt.method_10566("structure_candidate_chunks", (class_2520)class_24993);
        class_2499 class_24994 = new class_2499();
        for (PlacedStructureState placedStructure : this.placedStructures) {
            class_24994.add((Object)placedStructure.toNbt());
        }
        nbt.method_10566("placed_structures", (class_2520)class_24994);
        class_2499 class_24995 = new class_2499();
        for (WallGateClaim wallGateClaim : this.wallGateClaims) {
            class_24995.add((Object)wallGateClaim.toNbt());
        }
        nbt.method_10566("wall_gates", (class_2520)class_24995);
        return nbt;
    }

    public static PendingCastleGeneration fromNbt(class_2487 nbt) {
        PendingCastleGeneration generation = new PendingCastleGeneration(nbt.method_10558("castle_id"), nbt.method_10558("definition_id"), nbt.method_10550("site_index"), nbt.method_10545("anchor") ? class_2338.method_10092((long)nbt.method_10537("anchor")) : class_2338.field_10980);
        try {
            generation.setStage(PendingCastleGenerationStage.valueOf(nbt.method_10558("stage")));
        }
        catch (IllegalArgumentException ignored) {
            generation.setStage(PendingCastleGenerationStage.PLAN);
        }
        generation.setTier(CastleTier.byId(nbt.method_10558("tier")));
        generation.setDynamic(nbt.method_10577("dynamic"));
        generation.setTemplateId(nbt.method_10558("template_id"));
        generation.setPlanOrigin(nbt.method_10545("plan_origin") ? class_2338.method_10092((long)nbt.method_10537("plan_origin")) : class_2338.field_10980);
        generation.setFloorY(nbt.method_10550("floor_y"));
        generation.setFootprintBlocks(nbt.method_10550("footprint_blocks"));
        generation.setBlendRadius(nbt.method_10550("blend_radius"));
        generation.setStructureOrigin(nbt.method_10545("structure_origin") ? class_2338.method_10092((long)nbt.method_10537("structure_origin")) : class_2338.field_10980);
        generation.setStructureSize(new class_2382(nbt.method_10550("structure_size_x"), nbt.method_10550("structure_size_y"), nbt.method_10550("structure_size_z")));
        generation.setBannerPos(nbt.method_10545("banner_pos") ? class_2338.method_10092((long)nbt.method_10537("banner_pos")) : class_2338.field_10980);
        generation.setKingdomId(nbt.method_10558("kingdom_id"));
        generation.setHouseId(nbt.method_10558("house_id"));
        generation.setReligionId(nbt.method_10558("religion_id"));
        generation.setLordName(nbt.method_10558("lord_name"));
        if (nbt.method_25928("owner_player_uuid")) {
            generation.setOwnerPlayerUuid(nbt.method_25926("owner_player_uuid"));
        }
        class_2499 subtypeList = nbt.method_10554("subtypes", 8);
        ArrayList<CastleSubtype> subtypes = new ArrayList<CastleSubtype>();
        for (int index = 0; index < subtypeList.size(); ++index) {
            CastleSubtype subtype = CastleSubtype.byId(subtypeList.method_10608(index));
            if (subtype == null) continue;
            subtypes.add(subtype);
        }
        generation.setSubtypes(subtypes);
        generation.setNorthSouthGates(nbt.method_10577("north_south_gates"));
        generation.setNextStructureIndex(nbt.method_10550("next_structure_index"));
        generation.setCorePhase(nbt.method_10550("core_phase"));
        generation.setCoreCursor(nbt.method_10550("core_cursor"));
        generation.setWorldgenShellPlaced(nbt.method_10577("worldgen_shell_placed"));
        generation.setDynamicCoreUsesCastleKeep(nbt.method_10577("dynamic_core_uses_castle_keep"));
        generation.setDynamicCoreClearanceHeight(nbt.method_10550("dynamic_core_clearance_height"));
        if (nbt.method_10545("dynamic_core_north_edge_heights")) {
            generation.setDynamicCoreNorthEdgeHeights(nbt.method_10561("dynamic_core_north_edge_heights"));
        }
        if (nbt.method_10545("dynamic_core_south_edge_heights")) {
            generation.setDynamicCoreSouthEdgeHeights(nbt.method_10561("dynamic_core_south_edge_heights"));
        }
        if (nbt.method_10545("dynamic_core_west_edge_heights")) {
            generation.setDynamicCoreWestEdgeHeights(nbt.method_10561("dynamic_core_west_edge_heights"));
        }
        if (nbt.method_10545("dynamic_core_east_edge_heights")) {
            generation.setDynamicCoreEastEdgeHeights(nbt.method_10561("dynamic_core_east_edge_heights"));
        }
        generation.setNextStructureChunkCursor(nbt.method_10550("next_structure_chunk_cursor"));
        if (nbt.method_10545("road_layout_version")) {
            generation.setRoadLayoutVersion(nbt.method_10550("road_layout_version"));
        }
        generation.setRoadPlanFingerprint(nbt.method_10558("road_plan_fingerprint"));
        generation.setNextRoadChunkCursor(nbt.method_10550("next_road_chunk_cursor"));
        generation.setNextRoadCellCursor(nbt.method_10550("next_road_cell_cursor"));
        generation.setInfrastructurePhase(nbt.method_10550("infrastructure_phase"));
        generation.setNextWallChunkCursor(nbt.method_10550("next_wall_chunk_cursor"));
        generation.setNextNatureStructureCursor(nbt.method_10550("next_nature_structure_cursor"));
        if (nbt.method_10545("last_blocked_log_tick")) {
            generation.setLastBlockedLogTick(nbt.method_10537("last_blocked_log_tick"));
        }
        class_2499 plannedList = nbt.method_10554("planned_structures", 10);
        ArrayList<PlannedStructureState> plannedStructures = new ArrayList<PlannedStructureState>();
        for (int index = 0; index < plannedList.size(); ++index) {
            plannedStructures.add(PlannedStructureState.fromNbt(plannedList.method_10602(index)));
        }
        generation.setPlannedStructures(plannedStructures);
        class_2499 dynamicChunkList = nbt.method_10554("dynamic_building_chunks", 10);
        ArrayList<ChunkPosState> dynamicBuildingChunks = new ArrayList<ChunkPosState>();
        for (int index = 0; index < dynamicChunkList.size(); ++index) {
            dynamicBuildingChunks.add(ChunkPosState.fromNbt(dynamicChunkList.method_10602(index)));
        }
        generation.setDynamicBuildingChunks(dynamicBuildingChunks);
        class_2499 structureChunkList = nbt.method_10554("structure_candidate_chunks", 10);
        ArrayList<ChunkPosState> structureCandidateChunks = new ArrayList<ChunkPosState>();
        for (int index = 0; index < structureChunkList.size(); ++index) {
            structureCandidateChunks.add(ChunkPosState.fromNbt(structureChunkList.method_10602(index)));
        }
        generation.setStructureCandidateChunks(structureCandidateChunks);
        class_2499 placedList = nbt.method_10554("placed_structures", 10);
        for (int index = 0; index < placedList.size(); ++index) {
            generation.addPlacedStructure(PlacedStructureState.fromNbt(placedList.method_10602(index)));
        }
        class_2499 wallGateList = nbt.method_10554("wall_gates", 10);
        ArrayList<WallGateClaim> wallGateClaims = new ArrayList<WallGateClaim>();
        for (int index = 0; index < wallGateList.size(); ++index) {
            wallGateClaims.add(WallGateClaim.fromNbt(wallGateList.method_10602(index)));
        }
        generation.setWallGateClaims(wallGateClaims);
        return generation;
    }

    public record PlannedStructureState(String structurePath, String residentRoleId, int pointOfInterestRadius) {
        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("structure_path", this.structurePath == null ? "" : this.structurePath);
            nbt.method_10582("resident_role_id", this.residentRoleId == null ? "" : this.residentRoleId);
            nbt.method_10569("point_of_interest_radius", this.pointOfInterestRadius);
            return nbt;
        }

        public static PlannedStructureState fromNbt(class_2487 nbt) {
            return new PlannedStructureState(nbt.method_10558("structure_path"), nbt.method_10558("resident_role_id"), nbt.method_10550("point_of_interest_radius"));
        }
    }

    public record ChunkPosState(int x, int z) {
        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10569("x", this.x);
            nbt.method_10569("z", this.z);
            return nbt;
        }

        public static ChunkPosState fromNbt(class_2487 nbt) {
            return new ChunkPosState(nbt.method_10550("x"), nbt.method_10550("z"));
        }
    }

    public record PlacedStructureState(String structurePath, String residentRoleId, int pointOfInterestRadius, class_3341 footprint, class_2338 origin, class_2382 size, class_2338 pointOfInterest, class_2338 residentSpawnPos) {
        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("structure_path", this.structurePath == null ? "" : this.structurePath);
            nbt.method_10582("resident_role_id", this.residentRoleId == null ? "" : this.residentRoleId);
            nbt.method_10569("point_of_interest_radius", this.pointOfInterestRadius);
            nbt.method_10569("footprint_min_x", this.footprint.method_35415());
            nbt.method_10569("footprint_min_y", this.footprint.method_35416());
            nbt.method_10569("footprint_min_z", this.footprint.method_35417());
            nbt.method_10569("footprint_max_x", this.footprint.method_35418());
            nbt.method_10569("footprint_max_y", this.footprint.method_35419());
            nbt.method_10569("footprint_max_z", this.footprint.method_35420());
            nbt.method_10544("origin", this.origin.method_10063());
            nbt.method_10569("size_x", this.size.method_10263());
            nbt.method_10569("size_y", this.size.method_10264());
            nbt.method_10569("size_z", this.size.method_10260());
            nbt.method_10544("poi", this.pointOfInterest.method_10063());
            nbt.method_10544("spawn", this.residentSpawnPos.method_10063());
            return nbt;
        }

        public static PlacedStructureState fromNbt(class_2487 nbt) {
            return new PlacedStructureState(nbt.method_10558("structure_path"), nbt.method_10558("resident_role_id"), nbt.method_10550("point_of_interest_radius"), new class_3341(nbt.method_10550("footprint_min_x"), nbt.method_10550("footprint_min_y"), nbt.method_10550("footprint_min_z"), nbt.method_10550("footprint_max_x"), nbt.method_10550("footprint_max_y"), nbt.method_10550("footprint_max_z")), class_2338.method_10092((long)nbt.method_10537("origin")), new class_2382(nbt.method_10550("size_x"), nbt.method_10550("size_y"), nbt.method_10550("size_z")), class_2338.method_10092((long)nbt.method_10537("poi")), class_2338.method_10092((long)nbt.method_10537("spawn")));
        }
    }
}

