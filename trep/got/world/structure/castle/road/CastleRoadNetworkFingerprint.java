/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_3341
 */
package trep.got.world.structure.castle.road;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.List;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_3341;
import trep.got.castle.CastlePlacementPlan;
import trep.got.castle.WallGateClaim;
import trep.got.world.structure.castle.CastleWorldgenPlan;
import trep.got.world.structure.castle.CastleWorldgenStructurePlan;
import trep.got.world.structure.castle.DynamicCastleCoreWorldgenPlan;
import trep.got.world.structure.castle.DynamicCastleKeepPiecePlan;
import trep.got.world.structure.castle.DynamicCastleWallPlan;
import trep.got.world.structure.castle.road.CastleRoadBounds;
import trep.got.world.structure.castle.road.CastleRoadNetworkPlan;

public final class CastleRoadNetworkFingerprint {
    private static final int FULL_PLAN_FINGERPRINT_VERSION = 1;

    private CastleRoadNetworkFingerprint() {
    }

    public static String compute(CastleRoadNetworkPlan plan) {
        if (plan == null) {
            return "";
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            CastleRoadNetworkFingerprint.update(digest, "version", plan.version());
            CastleRoadNetworkFingerprint.update(digest, "half_width", plan.halfWidth());
            plan.gatePortals().forEach(portal -> {
                CastleRoadNetworkFingerprint.update(digest, "portal", portal.id());
                CastleRoadNetworkFingerprint.update(digest, "facing", portal.outwardFacing().method_10151());
                CastleRoadNetworkFingerprint.update(digest, "aperture_width", portal.apertureWidth());
                CastleRoadNetworkFingerprint.update(digest, "apron_length", portal.apronLength());
                CastleRoadNetworkFingerprint.update(digest, "threshold", portal.threshold());
                CastleRoadNetworkFingerprint.update(digest, "interior", portal.interiorCenter());
                CastleRoadNetworkFingerprint.update(digest, "exterior", portal.exteriorCenter());
                CastleRoadNetworkFingerprint.update(digest, "aperture", portal.aperture());
            });
            plan.buildingAccesses().forEach(access -> {
                CastleRoadNetworkFingerprint.update(digest, "access", access.id());
                CastleRoadNetworkFingerprint.update(digest, "structure", access.structurePath());
                CastleRoadNetworkFingerprint.update(digest, "obstacle", access.obstacleId());
                CastleRoadNetworkFingerprint.update(digest, "footprint", access.structureFootprint());
                CastleRoadNetworkFingerprint.update(digest, "facing", access.outwardFacing().method_10151());
                CastleRoadNetworkFingerprint.update(digest, "width", access.width());
                CastleRoadNetworkFingerprint.update(digest, "threshold", access.threshold());
                CastleRoadNetworkFingerprint.update(digest, "exterior", access.exteriorCenter());
            });
            plan.cellsByChunk().forEach((chunkKey, cells) -> {
                CastleRoadNetworkFingerprint.update(digest, "chunk", chunkKey);
                cells.forEach(cell -> {
                    CastleRoadNetworkFingerprint.update(digest, "cell_x", cell.x());
                    CastleRoadNetworkFingerprint.update(digest, "cell_y", cell.y());
                    CastleRoadNetworkFingerprint.update(digest, "cell_z", cell.z());
                    CastleRoadNetworkFingerprint.update(digest, "cell_distance", cell.distanceFromCenter());
                    CastleRoadNetworkFingerprint.update(digest, "cell_kind", cell.kind().name());
                });
            });
            return HexFormat.of().formatHex(digest.digest());
        }
        catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("JVM does not provide SHA-256", exception);
        }
    }

    public static String compute(CastleWorldgenPlan plan) {
        if (plan == null) {
            return "";
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            CastleRoadNetworkFingerprint.update(digest, "fingerprint_schema", 1L);
            CastleRoadNetworkFingerprint.update(digest, "castle_id", plan.castleId());
            CastleRoadNetworkFingerprint.update(digest, "definition_id", plan.definitionId());
            CastleRoadNetworkFingerprint.update(digest, "display_name", plan.displayName());
            CastleRoadNetworkFingerprint.update(digest, "anchor", plan.anchor());
            CastleRoadNetworkFingerprint.update(digest, "origin", plan.origin());
            CastleRoadNetworkFingerprint.update(digest, "effective_tier", plan.effectiveTier().getId());
            plan.subtypes().stream().map(subtype -> subtype.getId()).sorted().forEach(value -> CastleRoadNetworkFingerprint.update(digest, "subtype", value));
            CastleRoadNetworkFingerprint.update(digest, "structure_kingdom", plan.structureKingdomId());
            CastleRoadNetworkFingerprint.update(digest, "north_south_gates", plan.northSouthGates() ? 1L : 0L);
            CastlePlacementPlan placement = plan.placementPlan();
            CastleRoadNetworkFingerprint.update(digest, "placement_castle", placement.castleId());
            CastleRoadNetworkFingerprint.update(digest, "placement_tier", placement.tier().getId());
            CastleRoadNetworkFingerprint.update(digest, "placement_template", String.valueOf(placement.templateKey()));
            CastleRoadNetworkFingerprint.update(digest, "placement_origin", placement.origin());
            CastleRoadNetworkFingerprint.update(digest, "placement_floor", placement.floorY());
            CastleRoadNetworkFingerprint.update(digest, "placement_min_surface", placement.minSurfaceY());
            CastleRoadNetworkFingerprint.update(digest, "placement_max_surface", placement.maxSurfaceY());
            CastleRoadNetworkFingerprint.update(digest, "placement_footprint_chunks", placement.footprintChunks());
            CastleRoadNetworkFingerprint.update(digest, "placement_footprint_blocks", placement.footprintBlocks());
            CastleRoadNetworkFingerprint.update(digest, "placement_blend", placement.blendRadius());
            if (plan.dynamicFootprint() != null) {
                CastleRoadNetworkFingerprint.update(digest, "dynamic_origin", plan.dynamicFootprint().origin());
                CastleRoadNetworkFingerprint.update(digest, "dynamic_size", plan.dynamicFootprint().size());
                CastleRoadNetworkFingerprint.update(digest, "dynamic_clearance", plan.dynamicFootprint().clearanceHeight());
            }
            if (plan.corePlan() != null) {
                DynamicCastleCoreWorldgenPlan core = plan.corePlan();
                CastleRoadNetworkFingerprint.update(digest, "core_keep", core.usesCastleKeep() ? 1L : 0L);
                CastleRoadNetworkFingerprint.update(digest, "core_search_origin", core.searchOrigin());
                CastleRoadNetworkFingerprint.update(digest, "core_search_size", core.searchSize());
                CastleRoadNetworkFingerprint.update(digest, "core_template_origin", core.templateOrigin());
                CastleRoadNetworkFingerprint.update(digest, "core_template_size", core.templateSize());
                for (DynamicCastleKeepPiecePlan piece : core.keepPieces()) {
                    CastleRoadNetworkFingerprint.update(digest, "core_piece_id", String.valueOf(piece.structureId()));
                    CastleRoadNetworkFingerprint.update(digest, "core_piece_offset", piece.offset());
                }
                CastleRoadNetworkFingerprint.update(digest, "core_claim_template", String.valueOf(core.claimTemplateId()));
                CastleRoadNetworkFingerprint.update(digest, "core_fallback_banner", core.fallbackBannerPos());
            }
            DynamicCastleWallPlan wall = plan.wallPlan();
            CastleRoadNetworkFingerprint.update(digest, "wall_min_x", wall.outerMinX());
            CastleRoadNetworkFingerprint.update(digest, "wall_min_z", wall.outerMinZ());
            CastleRoadNetworkFingerprint.update(digest, "wall_width", wall.outerWidth());
            CastleRoadNetworkFingerprint.update(digest, "wall_depth", wall.outerDepth());
            CastleRoadNetworkFingerprint.update(digest, "wall_ns_gates", wall.northSouthGates() ? 1L : 0L);
            CastleRoadNetworkFingerprint.update(digest, "wall_road_x", wall.roadX());
            CastleRoadNetworkFingerprint.update(digest, "wall_road_z", wall.roadZ());
            wall.interiorChunkKeys().stream().sorted().forEach(key -> CastleRoadNetworkFingerprint.update(digest, "wall_interior_chunk", key));
            for (class_1923 chunk : plan.buildingChunks()) {
                CastleRoadNetworkFingerprint.update(digest, "building_chunk", chunk.method_8324());
            }
            CastleRoadNetworkFingerprint.updateStructures(digest, "structure", plan.structures());
            CastleRoadNetworkFingerprint.updateStructures(digest, "nature", plan.natureStructures());
            for (WallGateClaim claim : plan.wallGateClaims()) {
                CastleRoadNetworkFingerprint.update(digest, "gate_footprint", claim.footprint());
                CastleRoadNetworkFingerprint.update(digest, "gate_origin", claim.origin());
                CastleRoadNetworkFingerprint.update(digest, "gate_size", claim.size());
                CastleRoadNetworkFingerprint.update(digest, "gate_poi", claim.pointOfInterest());
            }
            CastleRoadNetworkFingerprint.updateRoad(digest, plan.roadNetworkPlan());
            CastleRoadNetworkFingerprint.update(digest, "block_bounds", plan.blockBounds());
            CastleRoadNetworkFingerprint.update(digest, "chunk_min_x", plan.chunkFootprint().minChunkX());
            CastleRoadNetworkFingerprint.update(digest, "chunk_min_z", plan.chunkFootprint().minChunkZ());
            CastleRoadNetworkFingerprint.update(digest, "chunk_max_x", plan.chunkFootprint().maxChunkX());
            CastleRoadNetworkFingerprint.update(digest, "chunk_max_z", plan.chunkFootprint().maxChunkZ());
            return HexFormat.of().formatHex(digest.digest());
        }
        catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("JVM does not provide SHA-256", exception);
        }
    }

    private static void updateStructures(MessageDigest digest, String prefix, List<CastleWorldgenStructurePlan> structures) {
        for (int index = 0; index < structures.size(); ++index) {
            CastleWorldgenStructurePlan structure = structures.get(index);
            CastleRoadNetworkFingerprint.update(digest, prefix + "_index", index);
            CastleRoadNetworkFingerprint.update(digest, prefix + "_path", structure.structurePath());
            CastleRoadNetworkFingerprint.update(digest, prefix + "_rotation", structure.rotation().name());
            CastleRoadNetworkFingerprint.update(digest, prefix + "_origin", structure.placementOrigin());
            CastleRoadNetworkFingerprint.update(digest, prefix + "_footprint", structure.footprint());
            CastleRoadNetworkFingerprint.update(digest, prefix + "_size", structure.size());
            CastleRoadNetworkFingerprint.update(digest, prefix + "_poi", structure.pointOfInterest());
            CastleRoadNetworkFingerprint.update(digest, prefix + "_resident_spawn", structure.residentSpawnPos());
            if (structure.residentPlan() == null) continue;
            CastleRoadNetworkFingerprint.update(digest, prefix + "_resident_path", structure.residentPlan().structurePath());
            CastleRoadNetworkFingerprint.update(digest, prefix + "_resident_role", structure.residentPlan().residentRole().name());
            CastleRoadNetworkFingerprint.update(digest, prefix + "_resident_radius", structure.residentPlan().pointOfInterestRadius());
        }
    }

    private static void updateRoad(MessageDigest digest, CastleRoadNetworkPlan plan) {
        if (plan == null) {
            return;
        }
        CastleRoadNetworkFingerprint.update(digest, "road_version", plan.version());
        CastleRoadNetworkFingerprint.update(digest, "road_half_width", plan.halfWidth());
        plan.gatePortals().forEach(portal -> {
            CastleRoadNetworkFingerprint.update(digest, "road_portal", portal.id());
            CastleRoadNetworkFingerprint.update(digest, "road_portal_facing", portal.outwardFacing().method_10151());
            CastleRoadNetworkFingerprint.update(digest, "road_portal_width", portal.apertureWidth());
            CastleRoadNetworkFingerprint.update(digest, "road_portal_apron", portal.apronLength());
            CastleRoadNetworkFingerprint.update(digest, "road_portal_threshold", portal.threshold());
            CastleRoadNetworkFingerprint.update(digest, "road_portal_interior", portal.interiorCenter());
            CastleRoadNetworkFingerprint.update(digest, "road_portal_exterior", portal.exteriorCenter());
            CastleRoadNetworkFingerprint.update(digest, "road_portal_aperture", portal.aperture());
        });
        plan.buildingAccesses().forEach(access -> {
            CastleRoadNetworkFingerprint.update(digest, "road_access", access.id());
            CastleRoadNetworkFingerprint.update(digest, "road_access_structure", access.structurePath());
            CastleRoadNetworkFingerprint.update(digest, "road_access_obstacle", access.obstacleId());
            CastleRoadNetworkFingerprint.update(digest, "road_access_footprint", access.structureFootprint());
            CastleRoadNetworkFingerprint.update(digest, "road_access_facing", access.outwardFacing().method_10151());
            CastleRoadNetworkFingerprint.update(digest, "road_access_width", access.width());
            CastleRoadNetworkFingerprint.update(digest, "road_access_threshold", access.threshold());
            CastleRoadNetworkFingerprint.update(digest, "road_access_exterior", access.exteriorCenter());
        });
        plan.cellsByChunk().forEach((chunkKey, cells) -> {
            CastleRoadNetworkFingerprint.update(digest, "road_chunk", chunkKey);
            cells.forEach(cell -> {
                CastleRoadNetworkFingerprint.update(digest, "road_cell_x", cell.x());
                CastleRoadNetworkFingerprint.update(digest, "road_cell_y", cell.y());
                CastleRoadNetworkFingerprint.update(digest, "road_cell_z", cell.z());
                CastleRoadNetworkFingerprint.update(digest, "road_cell_distance", cell.distanceFromCenter());
                CastleRoadNetworkFingerprint.update(digest, "road_cell_kind", cell.kind().name());
            });
        });
    }

    private static void update(MessageDigest digest, String label, CastleRoadBounds bounds) {
        CastleRoadNetworkFingerprint.update(digest, label + "_min_x", bounds.minX());
        CastleRoadNetworkFingerprint.update(digest, label + "_min_z", bounds.minZ());
        CastleRoadNetworkFingerprint.update(digest, label + "_max_x", bounds.maxX());
        CastleRoadNetworkFingerprint.update(digest, label + "_max_z", bounds.maxZ());
    }

    private static void update(MessageDigest digest, String label, class_2338 pos) {
        CastleRoadNetworkFingerprint.update(digest, label, pos == null ? Long.MIN_VALUE : pos.method_10063());
    }

    private static void update(MessageDigest digest, String label, class_2382 value) {
        if (value == null) {
            CastleRoadNetworkFingerprint.update(digest, label, "null");
            return;
        }
        CastleRoadNetworkFingerprint.update(digest, label + "_x", value.method_10263());
        CastleRoadNetworkFingerprint.update(digest, label + "_y", value.method_10264());
        CastleRoadNetworkFingerprint.update(digest, label + "_z", value.method_10260());
    }

    private static void update(MessageDigest digest, String label, class_3341 value) {
        if (value == null) {
            CastleRoadNetworkFingerprint.update(digest, label, "null");
            return;
        }
        CastleRoadNetworkFingerprint.update(digest, label + "_min_x", value.method_35415());
        CastleRoadNetworkFingerprint.update(digest, label + "_min_y", value.method_35416());
        CastleRoadNetworkFingerprint.update(digest, label + "_min_z", value.method_35417());
        CastleRoadNetworkFingerprint.update(digest, label + "_max_x", value.method_35418());
        CastleRoadNetworkFingerprint.update(digest, label + "_max_y", value.method_35419());
        CastleRoadNetworkFingerprint.update(digest, label + "_max_z", value.method_35420());
    }

    private static void update(MessageDigest digest, String label, long value) {
        CastleRoadNetworkFingerprint.update(digest, label, Long.toString(value));
    }

    private static void update(MessageDigest digest, String label, String value) {
        digest.update(label.getBytes(StandardCharsets.UTF_8));
        digest.update((byte)61);
        digest.update((value == null ? "" : value).getBytes(StandardCharsets.UTF_8));
        digest.update((byte)10);
    }
}

