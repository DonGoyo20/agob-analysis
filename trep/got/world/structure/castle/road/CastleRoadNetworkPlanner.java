/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2323
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_2350$class_2353
 *  net.minecraft.class_2382
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_2680
 *  net.minecraft.class_2741
 *  net.minecraft.class_2756
 *  net.minecraft.class_2769
 *  net.minecraft.class_2794
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3341
 *  net.minecraft.class_3481
 *  net.minecraft.class_3485
 *  net.minecraft.class_3492
 *  net.minecraft.class_3499
 *  net.minecraft.class_3499$class_3501
 *  net.minecraft.class_7923
 */
package trep.got.world.structure.castle.road;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import net.minecraft.class_1923;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2756;
import net.minecraft.class_2769;
import net.minecraft.class_2794;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3481;
import net.minecraft.class_3485;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_7923;
import trep.got.block.GateMechanismBlock;
import trep.got.castle.WallGateClaim;
import trep.got.registry.GotBlocks;
import trep.got.world.gen.MapChunkGenerator;
import trep.got.world.map.MapImage;
import trep.got.world.map.RoadMapHelper;
import trep.got.world.structure.castle.CastleWorldgenStructurePlan;
import trep.got.world.structure.castle.DynamicCastleCoreWorldgenPlan;
import trep.got.world.structure.castle.DynamicCastleKeepPiecePlan;
import trep.got.world.structure.castle.DynamicCastleTerrainApplier;
import trep.got.world.structure.castle.DynamicCastleWallPlan;
import trep.got.world.structure.castle.DynamicCastleWorldgenFootprint;
import trep.got.world.structure.castle.road.CastleRoadAccess;
import trep.got.world.structure.castle.road.CastleRoadBounds;
import trep.got.world.structure.castle.road.CastleRoadCell;
import trep.got.world.structure.castle.road.CastleRoadNetworkPlan;
import trep.got.world.structure.castle.road.CastleRoadPortal;

public final class CastleRoadNetworkPlanner {
    private static final String ROAD_MAP_RESOURCE = "assets/agob/map_road.png";
    private static final int ROAD_COLOR = 13154156;
    private static final int HALF_WIDTH = 2;
    private static final int GRID = 2;
    private static final int GATE_APRON = 8;
    private static final int BUILDING_APRON = 4;
    private static final int STRUCTURE_PADDING = 3;
    private static final int MAX_CANONICAL_ROAD_RADIUS_PIXELS = 48;
    private static final int SEARCH_MARGIN = 32;
    public static final int MAX_CONNECTOR_SEARCH_RADIUS_BLOCKS = 1600;
    private static final int MAX_EXPANDED_NODES_PER_LEG = 32768;
    private static final int MAX_TOTAL_CENTER_CELLS = 16384;
    private static final int MAX_LEG_LENGTH_BLOCKS = 2048;
    private static final int MAX_BRANCHES = 32;
    private static final int MAX_STEP_HEIGHT = 6;
    private static final Comparator<Node> NODE_ORDER = Comparator.comparingInt(Node::priority).thenComparingInt(Node::heuristic).thenComparingInt(Node::cost).thenComparingInt(Node::x).thenComparingInt(Node::z).thenComparingLong(Node::sequence);

    private CastleRoadNetworkPlanner() {
    }

    public static CastleRoadNetworkPlan create(class_3218 world, String castleId, class_2338 anchor, int floorY, DynamicCastleWorldgenFootprint terrainFootprint, DynamicCastleWallPlan wallPlan, List<WallGateClaim> gateClaims, DynamicCastleCoreWorldgenPlan corePlan, List<CastleWorldgenStructurePlan> structures, class_3499 gateTemplate, class_3499 wallTemplate, class_3499 cornerTemplate) {
        if (world == null || castleId == null || castleId.isBlank() || anchor == null || wallPlan == null) {
            return CastleRoadNetworkPlan.EMPTY;
        }
        ArrayList<String> diagnostics = new ArrayList<String>();
        TerrainSampler terrain = CastleRoadNetworkPlanner.terrainSampler(world, terrainFootprint, floorY, diagnostics);
        List<CastleRoadPortal> portals = CastleRoadNetworkPlanner.discoverGatePortals(gateClaims, gateTemplate, wallPlan, floorY, diagnostics);
        if (portals.isEmpty()) {
            diagnostics.add("no_valid_gate_aperture");
            return new CastleRoadNetworkPlan(2, 2, portals, List.of(), Map.of(), diagnostics);
        }
        if (wallTemplate == null || cornerTemplate == null) {
            diagnostics.add("wall_template_contract_unavailable");
            return new CastleRoadNetworkPlan(2, 2, portals, List.of(), Map.of(), diagnostics);
        }
        List<CastleRoadBounds> wallBands = CastleRoadNetworkPlanner.collectWallBands(wallPlan, wallTemplate, cornerTemplate);
        List<RoadObstacle> structureObstacles = CastleRoadNetworkPlanner.collectStructureObstacles(world.method_14183(), corePlan, structures);
        List<CastleRoadAccess> accesses = CastleRoadNetworkPlanner.discoverBuildingAccesses(world.method_14183(), corePlan, structures, structureObstacles, wallBands, diagnostics);
        RoadPoint canonicalTarget = CastleRoadNetworkPlanner.findCanonicalRoadTarget(anchor, portals);
        PlannerContext context = new PlannerContext(wallPlan, portals, accesses, wallBands, structureObstacles, terrain, CastleRoadNetworkPlanner.searchBounds(anchor, wallPlan, canonicalTarget));
        LinkedHashMap<Long, CenterCell> network = new LinkedHashMap<Long, CenterCell>();
        CastleRoadPortal primary = CastleRoadNetworkPlanner.selectPrimaryPortal(portals, canonicalTarget == null ? anchor : canonicalTarget.pos());
        CastleRoadNetworkPlanner.addLine(network, primary.exteriorCenter(), primary.interiorCenter(), context, CastleRoadCell.Kind.GATE_APRON, primary.id(), diagnostics);
        if (canonicalTarget != null) {
            CastleRoadNetworkPlanner.addLeg(network, canonicalTarget, CastleRoadNetworkPlanner.point(primary.exteriorCenter()), context, (long)castleId.hashCode() ^ 0x524F4144L, "canonical_to_" + primary.id(), diagnostics);
        } else {
            diagnostics.add("canonical_road_target_missing");
        }
        int branches = 0;
        for (CastleRoadPortal portal : portals) {
            if (portal == primary || branches >= 32) continue;
            RoadPoint connection = CastleRoadNetworkPlanner.nearestNetworkPoint(CastleRoadNetworkPlanner.point(portal.interiorCenter()), network.values(), true, context);
            if (connection != null) {
                CastleRoadNetworkPlanner.addLeg(network, CastleRoadNetworkPlanner.point(portal.interiorCenter()), connection, context, anchor.method_10063() ^ (long)portal.id().hashCode(), portal.id() + "_branch", diagnostics);
            }
            CastleRoadNetworkPlanner.addLine(network, portal.exteriorCenter(), portal.interiorCenter(), context, CastleRoadCell.Kind.GATE_APRON, portal.id(), diagnostics);
            ++branches;
        }
        for (CastleRoadAccess access : accesses) {
            if (branches >= 32) {
                diagnostics.add("branch_budget_exhausted:" + access.id());
                break;
            }
            boolean inside = context.insideWall(access.exteriorCenter().method_10263(), access.exteriorCenter().method_10260());
            RoadPoint connection = CastleRoadNetworkPlanner.nearestNetworkPoint(CastleRoadNetworkPlanner.point(access.exteriorCenter()), network.values(), inside, context);
            if (connection != null) {
                CastleRoadNetworkPlanner.addLeg(network, CastleRoadNetworkPlanner.point(access.exteriorCenter()), connection, context, anchor.method_10063() ^ (long)access.id().hashCode(), access.id(), diagnostics);
            }
            CastleRoadNetworkPlanner.addLine(network, access.threshold(), access.exteriorCenter(), context, CastleRoadCell.Kind.BUILDING_APRON, access.id(), diagnostics);
            ++branches;
        }
        Map<Long, List<CastleRoadCell>> cellsByChunk = CastleRoadNetworkPlanner.materializeCells(network.values(), context);
        return new CastleRoadNetworkPlan(2, 2, portals, accesses, cellsByChunk, diagnostics);
    }

    private static List<CastleRoadPortal> discoverGatePortals(List<WallGateClaim> claims, class_3499 gateTemplate, DynamicCastleWallPlan wallPlan, int floorY, List<String> diagnostics) {
        if (gateTemplate == null || claims == null || claims.isEmpty()) {
            return List.of();
        }
        ArrayList<CastleRoadPortal> portals = new ArrayList<CastleRoadPortal>();
        for (WallGateClaim claim : claims) {
            class_2350 side = CastleRoadNetworkPlanner.inferWallSide(claim.footprint(), wallPlan);
            if (side == null) {
                diagnostics.add("ambiguous_gate_side:" + claim.pointOfInterest().method_23854());
                continue;
            }
            class_2470 rotation = CastleRoadNetworkPlanner.rotationFor(side);
            class_3492 placement = new class_3492().method_15123(rotation).method_15125(class_2415.field_11302);
            List<class_3499.class_3501> mechanisms = gateTemplate.method_15165(claim.origin(), placement, GotBlocks.GATE_MECHANISM, true).stream().filter(info -> info.comp_1342().method_28498((class_2769)GateMechanismBlock.FACING)).sorted(Comparator.comparingInt(info -> info.comp_1341().method_10264()).thenComparingInt(info -> info.comp_1341().method_10263()).thenComparingInt(info -> info.comp_1341().method_10260())).toList();
            if (mechanisms.isEmpty() || !CastleRoadNetworkPlanner.validMechanismLine(mechanisms, side)) {
                diagnostics.add("invalid_gate_aperture:" + side.method_15434());
                continue;
            }
            int minCross = mechanisms.stream().mapToInt(info -> CastleRoadNetworkPlanner.crossCoordinate(info.comp_1341(), side)).min().orElseThrow();
            int maxCross = mechanisms.stream().mapToInt(info -> CastleRoadNetworkPlanner.crossCoordinate(info.comp_1341(), side)).max().orElseThrow();
            int normal = mechanisms.stream().mapToInt(info -> CastleRoadNetworkPlanner.normalCoordinate(info.comp_1341(), side)).sum() / mechanisms.size();
            int cross = minCross + (maxCross - minCross) / 2;
            class_2338 threshold = side.method_10166() == class_2350.class_2351.field_11051 ? new class_2338(cross, floorY, normal) : new class_2338(normal, floorY, cross);
            int width = maxCross - minCross + 1;
            CastleRoadBounds aperture = side.method_10166() == class_2350.class_2351.field_11051 ? new CastleRoadBounds(minCross, claim.footprint().method_35417(), maxCross, claim.footprint().method_35420()) : new CastleRoadBounds(claim.footprint().method_35415(), minCross, claim.footprint().method_35418(), maxCross);
            String id = "gate_" + side.method_15434();
            portals.add(new CastleRoadPortal(id, side, width, 8, threshold, threshold.method_10079(side.method_10153(), 8), threshold.method_10079(side, 8), aperture));
        }
        return portals.stream().sorted(Comparator.comparingInt(portal -> CastleRoadNetworkPlanner.directionOrder(portal.outwardFacing()))).toList();
    }

    private static boolean validMechanismLine(List<class_3499.class_3501> mechanisms, class_2350 side) {
        int normal = CastleRoadNetworkPlanner.normalCoordinate(mechanisms.getFirst().comp_1341(), side);
        int y = mechanisms.getFirst().comp_1341().method_10264();
        class_2350.class_2351 requiredAxis = side.method_10166();
        HashSet<Integer> cross = new HashSet<Integer>();
        for (class_3499.class_3501 info : mechanisms) {
            if (CastleRoadNetworkPlanner.normalCoordinate(info.comp_1341(), side) != normal || info.comp_1341().method_10264() != y || ((class_2350)info.comp_1342().method_11654((class_2769)GateMechanismBlock.FACING)).method_10166() != requiredAxis) {
                return false;
            }
            cross.add(CastleRoadNetworkPlanner.crossCoordinate(info.comp_1341(), side));
        }
        int min = cross.stream().mapToInt(Integer::intValue).min().orElse(0);
        int max = cross.stream().mapToInt(Integer::intValue).max().orElse(-1);
        return cross.size() >= 3 && cross.size() == max - min + 1;
    }

    private static List<CastleRoadAccess> discoverBuildingAccesses(class_3485 templates, DynamicCastleCoreWorldgenPlan corePlan, List<CastleWorldgenStructurePlan> structures, List<RoadObstacle> obstacles, List<CastleRoadBounds> wallBands, List<String> diagnostics) {
        class_3499 template;
        ArrayList accesses = new ArrayList();
        if (corePlan != null) {
            class_3499 template2;
            ArrayList<TemplateComponent> components = new ArrayList<TemplateComponent>();
            if (corePlan.usesCastleKeep() && corePlan.keepPieces() != null) {
                for (DynamicCastleKeepPiecePlan piece : corePlan.keepPieces()) {
                    template = templates.method_15094(piece.structureId()).orElse(null);
                    if (template == null) continue;
                    class_2338 origin = corePlan.templateOrigin().method_10081(piece.offset());
                    class_3341 footprint = template.method_27267(origin, class_2470.field_11467, class_2338.field_10980, class_2415.field_11302);
                    components.add(new TemplateComponent(template, piece.structureId().toString(), origin, class_2470.field_11467, CastleRoadBounds.from(footprint)));
                }
            } else if (corePlan.claimTemplateId() != null && (template2 = (class_3499)templates.method_15094(corePlan.claimTemplateId()).orElse(null)) != null) {
                class_3341 footprint = template2.method_27267(corePlan.templateOrigin(), class_2470.field_11467, class_2338.field_10980, class_2415.field_11302);
                components.add(new TemplateComponent(template2, corePlan.claimTemplateId().toString(), corePlan.templateOrigin(), class_2470.field_11467, CastleRoadBounds.from(footprint)));
            }
            if (!components.isEmpty()) {
                CastleRoadBounds assembledBounds = CastleRoadBounds.from(corePlan.structureBounds(templates));
                CastleRoadNetworkPlanner.findDoorAccess(components, "core", "core", assembledBounds, obstacles, wallBands, diagnostics).ifPresent(accesses::add);
            }
        }
        if (structures != null) {
            for (CastleWorldgenStructurePlan structure : structures) {
                class_2960 id = class_2960.method_60655((String)"agob", (String)structure.structurePath());
                template = templates.method_15094(id).orElse(null);
                if (template == null) continue;
                String obstacleId = CastleRoadNetworkPlanner.structureObstacleId(structure);
                TemplateComponent component = new TemplateComponent(template, structure.structurePath(), structure.placementOrigin(), structure.rotation(), CastleRoadBounds.from(structure.footprint()));
                CastleRoadNetworkPlanner.findDoorAccess(List.of(component), structure.structurePath(), obstacleId, component.footprint(), obstacles, wallBands, diagnostics).ifPresent(accesses::add);
            }
        }
        return accesses.stream().sorted(Comparator.comparing(CastleRoadAccess::id)).limit(32L).toList();
    }

    private static Optional<CastleRoadAccess> findDoorAccess(List<TemplateComponent> components, String structurePath, String obstacleId, CastleRoadBounds assembledBounds, List<RoadObstacle> obstacles, List<CastleRoadBounds> wallBands, List<String> diagnostics) {
        List<CastleRoadBounds> assembledParts = components.stream().map(TemplateComponent::footprint).toList();
        ArrayList candidates = new ArrayList();
        for (TemplateComponent component : components) {
            class_3492 placement = new class_3492().method_15123(component.rotation()).method_15125(class_2415.field_11302);
            class_7923.field_41175.method_10220().filter(block -> block.method_9564().method_26164(class_3481.field_15495)).sorted(Comparator.comparing(block -> class_7923.field_41175.method_10221(block).toString())).forEach(block -> CastleRoadNetworkPlanner.collectDoorCandidates(component.template(), block, component.origin(), placement, assembledParts, candidates));
        }
        if (candidates.isEmpty()) {
            diagnostics.add("missing_validated_door:" + structurePath);
            return Optional.empty();
        }
        for (DoorCandidate selected : candidates.stream().sorted(Comparator.comparingInt(candidate -> candidate.pos().method_10264()).thenComparingInt(candidate -> CastleRoadNetworkPlanner.directionOrder(candidate.outward())).thenComparingInt(candidate -> candidate.pos().method_10263()).thenComparingInt(candidate -> candidate.pos().method_10260())).toList()) {
            int selectedCross;
            List<Integer> crossCoordinates = candidates.stream().filter(candidate -> candidate.outward() == selected.outward()).filter(candidate -> CastleRoadNetworkPlanner.normalCoordinate(candidate.pos(), selected.outward()) == CastleRoadNetworkPlanner.normalCoordinate(selected.pos(), selected.outward())).filter(candidate -> candidate.pos().method_10264() == selected.pos().method_10264()).map(candidate -> CastleRoadNetworkPlanner.crossCoordinate(candidate.pos(), selected.outward())).distinct().sorted().toList();
            int minCross = selectedCross = CastleRoadNetworkPlanner.crossCoordinate(selected.pos(), selected.outward());
            int maxCross = selectedCross;
            while (crossCoordinates.contains(minCross - 1)) {
                --minCross;
            }
            while (crossCoordinates.contains(maxCross + 1)) {
                ++maxCross;
            }
            int centerCross = minCross + (maxCross - minCross) / 2;
            int normal = CastleRoadNetworkPlanner.normalCoordinate(selected.pos(), selected.outward());
            int roadY = selected.pos().method_10264() - 1;
            class_2338 threshold = selected.outward().method_10166() == class_2350.class_2351.field_11051 ? new class_2338(centerCross, roadY, normal) : new class_2338(normal, roadY, centerCross);
            class_2338 exterior = threshold.method_10079(selected.outward(), 4);
            int width = maxCross - minCross + 1;
            String id = "door_" + Integer.toUnsignedString((structurePath + "|" + threshold.method_10063()).hashCode());
            CastleRoadAccess access = new CastleRoadAccess(id, structurePath, obstacleId, assembledBounds, selected.outward(), width, threshold, exterior);
            if (!CastleRoadNetworkPlanner.hasClearApron(access, assembledParts, CastleRoadNetworkPlanner.facingExitDistance(selected.pos(), selected.outward(), assembledParts), obstacles, wallBands)) continue;
            return Optional.of(access);
        }
        diagnostics.add("blocked_door_apron:" + structurePath);
        return Optional.empty();
    }

    private static void collectDoorCandidates(class_3499 template, class_2248 block, class_2338 origin, class_3492 placement, List<CastleRoadBounds> assembledParts, List<DoorCandidate> candidates) {
        for (class_3499.class_3501 info : template.method_15165(origin, placement, block, true)) {
            class_2350 outward;
            class_2680 state = info.comp_1342();
            if (!(state.method_26204() instanceof class_2323) || state.method_28498((class_2769)class_2741.field_12533) && state.method_11654((class_2769)class_2741.field_12533) != class_2756.field_12607 || !state.method_28498((class_2769)class_2741.field_12481) || !(outward = (class_2350)state.method_11654((class_2769)class_2741.field_12481)).method_10166().method_10179() || !CastleRoadNetworkPlanner.isUniqueExteriorFacing(info.comp_1341(), outward, assembledParts)) continue;
            candidates.add(new DoorCandidate(info.comp_1341(), outward));
        }
    }

    private static boolean isUniqueExteriorFacing(class_2338 door, class_2350 facing, List<CastleRoadBounds> assembledParts) {
        int facingExit = CastleRoadNetworkPlanner.exitDistance(door, facing, assembledParts);
        if (facingExit <= 0 || facingExit > 2) {
            return false;
        }
        for (class_2350 direction : class_2350.class_2353.field_11062) {
            int otherExit;
            if (direction == facing || (otherExit = CastleRoadNetworkPlanner.exitDistance(door, direction, assembledParts)) <= 0 || otherExit > facingExit) continue;
            return false;
        }
        return true;
    }

    private static int exitDistance(class_2338 origin, class_2350 direction, List<CastleRoadBounds> parts) {
        for (int distance = 1; distance <= 3; ++distance) {
            class_2338 sample = origin.method_10079(direction, distance);
            if (!parts.stream().noneMatch(part -> part.contains(sample.method_10263(), sample.method_10260()))) continue;
            return distance;
        }
        return -1;
    }

    private static int facingExitDistance(class_2338 origin, class_2350 direction, List<CastleRoadBounds> parts) {
        return Math.max(1, CastleRoadNetworkPlanner.exitDistance(origin, direction, parts));
    }

    private static boolean hasClearApron(CastleRoadAccess access, List<CastleRoadBounds> ownParts, int ownExitDistance, List<RoadObstacle> obstacles, List<CastleRoadBounds> wallBands) {
        CastleRoadBounds apron = CastleRoadNetworkPlanner.accessCorridor(access);
        if (wallBands.stream().anyMatch(apron::intersects)) {
            return false;
        }
        for (RoadObstacle obstacle : obstacles) {
            if (obstacle.id().equals(access.obstacleId()) || !obstacle.intersects(apron)) continue;
            return false;
        }
        int halfWidth = Math.max(2, access.width() / 2);
        for (int distance = ownExitDistance; distance <= 4; ++distance) {
            class_2338 center = access.threshold().method_10079(access.outwardFacing(), distance);
            for (int cross = -halfWidth; cross <= halfWidth; ++cross) {
                class_2338 sample = center.method_10079(access.outwardFacing().method_10170(), cross);
                if (!ownParts.stream().anyMatch(part -> part.contains(sample.method_10263(), sample.method_10260()))) continue;
                return false;
            }
        }
        return true;
    }

    private static CastleRoadBounds accessCorridor(CastleRoadAccess access) {
        int halfWidth = Math.max(2, access.width() / 2);
        return new CastleRoadBounds(Math.min(access.threshold().method_10263(), access.exteriorCenter().method_10263()) - halfWidth, Math.min(access.threshold().method_10260(), access.exteriorCenter().method_10260()) - halfWidth, Math.max(access.threshold().method_10263(), access.exteriorCenter().method_10263()) + halfWidth, Math.max(access.threshold().method_10260(), access.exteriorCenter().method_10260()) + halfWidth);
    }

    private static List<RoadObstacle> collectStructureObstacles(class_3485 templates, DynamicCastleCoreWorldgenPlan corePlan, List<CastleWorldgenStructurePlan> structures) {
        ArrayList<RoadObstacle> obstacles = new ArrayList<RoadObstacle>();
        if (corePlan != null) {
            ArrayList<CastleRoadBounds> coreParts = new ArrayList<CastleRoadBounds>();
            if (corePlan.usesCastleKeep() && corePlan.keepPieces() != null) {
                for (DynamicCastleKeepPiecePlan piece : corePlan.keepPieces()) {
                    class_3499 template = templates.method_15094(piece.structureId()).orElse(null);
                    if (template == null) continue;
                    class_2338 origin = corePlan.templateOrigin().method_10081(piece.offset());
                    coreParts.add(CastleRoadBounds.from(template.method_27267(origin, class_2470.field_11467, class_2338.field_10980, class_2415.field_11302)).expand(3));
                }
            }
            if (coreParts.isEmpty()) {
                coreParts.add(CastleRoadBounds.from(corePlan.structureBounds(templates)).expand(3));
            }
            obstacles.add(new RoadObstacle("core", List.copyOf(coreParts)));
        }
        if (structures != null) {
            for (CastleWorldgenStructurePlan structure : structures) {
                obstacles.add(new RoadObstacle(CastleRoadNetworkPlanner.structureObstacleId(structure), List.of(CastleRoadBounds.from(structure.footprint()).expand(3))));
            }
        }
        return List.copyOf(obstacles);
    }

    private static String structureObstacleId(CastleWorldgenStructurePlan structure) {
        return "structure|" + structure.structurePath() + "|" + structure.placementOrigin().method_10063();
    }

    private static List<CastleRoadBounds> collectWallBands(DynamicCastleWallPlan plan, class_3499 wallTemplate, class_3499 cornerTemplate) {
        ArrayList<CastleRoadBounds> bands = new ArrayList<CastleRoadBounds>();
        int northDepth = CastleRoadNetworkPlanner.templateBounds(wallTemplate, CastleRoadNetworkPlanner.rotationFor(class_2350.field_11043)).method_14663();
        int southDepth = CastleRoadNetworkPlanner.templateBounds(wallTemplate, CastleRoadNetworkPlanner.rotationFor(class_2350.field_11035)).method_14663();
        int westWidth = CastleRoadNetworkPlanner.templateBounds(wallTemplate, CastleRoadNetworkPlanner.rotationFor(class_2350.field_11039)).method_35414();
        int eastWidth = CastleRoadNetworkPlanner.templateBounds(wallTemplate, CastleRoadNetworkPlanner.rotationFor(class_2350.field_11034)).method_35414();
        for (long chunkKey : plan.interiorChunkKeys()) {
            int chunkX = class_1923.method_8325((long)chunkKey);
            int chunkZ = class_1923.method_8332((long)chunkKey);
            int minX = chunkX << 4;
            int minZ = chunkZ << 4;
            int maxX = minX + 15;
            int maxZ = minZ + 15;
            if (!plan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)(chunkZ - 1)))) {
                bands.add(new CastleRoadBounds(minX, minZ, maxX, minZ + northDepth - 1));
            }
            if (!plan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)(chunkZ + 1)))) {
                bands.add(new CastleRoadBounds(minX, maxZ - southDepth + 1, maxX, maxZ));
            }
            if (!plan.interiorChunkKeys().contains(class_1923.method_8331((int)(chunkX - 1), (int)chunkZ))) {
                bands.add(new CastleRoadBounds(minX, minZ, minX + westWidth - 1, maxZ));
            }
            if (plan.interiorChunkKeys().contains(class_1923.method_8331((int)(chunkX + 1), (int)chunkZ))) continue;
            bands.add(new CastleRoadBounds(maxX - eastWidth + 1, minZ, maxX, maxZ));
        }
        HashSet<Long> placedVertices = new HashSet<Long>();
        for (long chunkKey : plan.interiorChunkKeys()) {
            int chunkX = class_1923.method_8325((long)chunkKey);
            int chunkZ = class_1923.method_8332((long)chunkKey);
            for (int vertexX = chunkX; vertexX <= chunkX + 1; ++vertexX) {
                for (int vertexZ = chunkZ; vertexZ <= chunkZ + 1; ++vertexZ) {
                    class_2470 rotation;
                    if (!placedVertices.add(CastleRoadNetworkPlanner.key(vertexX, vertexZ)) || (rotation = CastleRoadNetworkPlanner.cornerRotation(plan.interiorChunkKeys(), vertexX, vertexZ)) == null) continue;
                    class_3341 bounds = CastleRoadNetworkPlanner.templateBounds(cornerTemplate, rotation);
                    int minX = (vertexX << 4) - bounds.method_35414() / 2;
                    int minZ = (vertexZ << 4) - bounds.method_14663() / 2;
                    bands.add(new CastleRoadBounds(minX, minZ, minX + bounds.method_35414() - 1, minZ + bounds.method_14663() - 1));
                }
            }
        }
        return List.copyOf(bands);
    }

    private static class_3341 templateBounds(class_3499 template, class_2470 rotation) {
        return template.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
    }

    private static RoadPoint findCanonicalRoadTarget(class_2338 anchor, List<CastleRoadPortal> portals) {
        MapImage image = MapImage.load(ROAD_MAP_RESOURCE);
        int centerPixelX = Math.floorDiv(anchor.method_10263() + 48000, 32);
        int centerPixelZ = Math.floorDiv(anchor.method_10260() + 48000, 32);
        RoadPoint best = null;
        long bestDistance = Long.MAX_VALUE;
        for (int radius = 0; radius <= 48; ++radius) {
            for (int pixelZ = centerPixelZ - radius; pixelZ <= centerPixelZ + radius; ++pixelZ) {
                for (int pixelX = centerPixelX - radius; pixelX <= centerPixelX + radius; ++pixelX) {
                    if (radius > 0 && Math.abs(pixelX - centerPixelX) != radius && Math.abs(pixelZ - centerPixelZ) != radius || !RoadMapHelper.isRoadPixel(image, pixelX, pixelZ, 13154156)) continue;
                    int worldX = (pixelX - 1500) * 32 + 16;
                    int worldZ = (pixelZ - 1500) * 32 + 16;
                    long distance = portals.stream().mapToLong(portal -> CastleRoadNetworkPlanner.squaredDistance(worldX, worldZ, portal.exteriorCenter().method_10263(), portal.exteriorCenter().method_10260())).min().orElse(Long.MAX_VALUE);
                    if (distance >= bestDistance && (distance != bestDistance || CastleRoadNetworkPlanner.compareCoordinates(worldX, worldZ, best) >= 0)) continue;
                    bestDistance = distance;
                    best = new RoadPoint(worldX, 0, worldZ);
                }
            }
            if (best != null && radius > 1) break;
        }
        return best;
    }

    private static boolean addLeg(LinkedHashMap<Long, CenterCell> network, RoadPoint rawStart, RoadPoint rawEnd, PlannerContext context, long seed, String id, List<String> diagnostics) {
        RoadPoint end;
        if (network.size() >= 16384) {
            diagnostics.add("network_cell_budget_exhausted:" + id);
            return false;
        }
        RoadPoint start = CastleRoadNetworkPlanner.snap(rawStart, context);
        List<RoadPoint> path = CastleRoadNetworkPlanner.findPath(start, end = CastleRoadNetworkPlanner.snap(rawEnd, context), context, seed);
        if (path.isEmpty()) {
            diagnostics.add("route_failed:" + id);
            return false;
        }
        int length = 0;
        for (int index = 1; index < path.size(); ++index) {
            length += CastleRoadNetworkPlanner.manhattan(path.get(index - 1), path.get(index));
        }
        if (length > 2048) {
            diagnostics.add("route_length_exceeded:" + id);
            return false;
        }
        for (RoadPoint point : path) {
            if (network.size() >= 16384) {
                diagnostics.add("network_cell_budget_exhausted:" + id);
                break;
            }
            network.putIfAbsent(CastleRoadNetworkPlanner.key(point.x(), point.z()), new CenterCell(point.x(), context.surfaceY(point.x(), point.z()), point.z(), CastleRoadCell.Kind.ROUTE));
        }
        return true;
    }

    private static List<RoadPoint> findPath(RoadPoint start, RoadPoint end, PlannerContext context, long seed) {
        if (start == null || end == null || context.blocked(start.x(), start.z()) || context.blocked(end.x(), end.z())) {
            return List.of();
        }
        PriorityQueue<Node> open = new PriorityQueue<Node>(NODE_ORDER);
        HashMap<Long, Integer> bestCost = new HashMap<Long, Integer>();
        HashMap<Long, Long> cameFrom = new HashMap<Long, Long>();
        HashSet<Long> closed = new HashSet<Long>();
        long sequence = 0L;
        int heuristic = CastleRoadNetworkPlanner.manhattan(start, end);
        open.add(new Node(start.x(), start.z(), 0, heuristic, heuristic, sequence++));
        bestCost.put(CastleRoadNetworkPlanner.key(start.x(), start.z()), 0);
        int expanded = 0;
        int[] directions = new int[]{2, 0, 0, 2, -2, 0, 0, -2};
        while (!open.isEmpty() && expanded++ < 32768) {
            Node current = open.poll();
            long currentKey = CastleRoadNetworkPlanner.key(current.x(), current.z());
            if (!closed.add(currentKey)) continue;
            if (current.x() == end.x() && current.z() == end.z()) {
                return CastleRoadNetworkPlanner.reconstruct(cameFrom, currentKey, CastleRoadNetworkPlanner.key(start.x(), start.z()));
            }
            int currentY = context.surfaceY(current.x(), current.z());
            for (int index = 0; index < directions.length; index += 2) {
                int nextY;
                int slope;
                int nextZ;
                int nextX = current.x() + directions[index];
                if (context.blocked(nextX, nextZ = current.z() + directions[index + 1]) || (slope = Math.abs((nextY = context.surfaceY(nextX, nextZ)) - currentY)) > 6) continue;
                int stepCost = 10 + slope * slope * 6 + CastleRoadNetworkPlanner.pathNoise(seed, nextX, nextZ);
                int nextCost = current.cost() + stepCost;
                long nextKey = CastleRoadNetworkPlanner.key(nextX, nextZ);
                if (bestCost.getOrDefault(nextKey, Integer.MAX_VALUE) <= nextCost) continue;
                bestCost.put(nextKey, nextCost);
                cameFrom.put(nextKey, currentKey);
                int nextHeuristic = CastleRoadNetworkPlanner.manhattan(new RoadPoint(nextX, nextY, nextZ), end) * 5;
                open.add(new Node(nextX, nextZ, nextCost, nextHeuristic, nextCost + nextHeuristic, sequence++));
            }
        }
        return List.of();
    }

    private static Map<Long, List<CastleRoadCell>> materializeCells(Collection<CenterCell> centers, PlannerContext context) {
        LinkedHashMap<Long, CastleRoadCell> unique = new LinkedHashMap<Long, CastleRoadCell>();
        for (CenterCell center : centers) {
            for (int dx = -2; dx <= 2; ++dx) {
                for (int dz = -2; dz <= 2; ++dz) {
                    int z;
                    int x = center.x() + dx;
                    if (context.blocked(x, z = center.z() + dz)) continue;
                    int distance = Math.max(Math.abs(dx), Math.abs(dz));
                    CastleRoadCell cell2 = new CastleRoadCell(x, context.surfaceY(x, z), z, distance, center.kind());
                    unique.merge(CastleRoadNetworkPlanner.key(x, z), cell2, (first, second) -> first.kind().ordinal() >= second.kind().ordinal() ? first : second);
                }
            }
        }
        LinkedHashMap<Long, List<CastleRoadCell>> byChunk = new LinkedHashMap<Long, List<CastleRoadCell>>();
        unique.values().stream().sorted(Comparator.comparingInt(CastleRoadCell::x).thenComparingInt(CastleRoadCell::z)).forEach(cell -> byChunk.computeIfAbsent(class_1923.method_8331((int)Math.floorDiv(cell.x(), 16), (int)Math.floorDiv(cell.z(), 16)), ignored -> new ArrayList()).add(cell));
        return byChunk;
    }

    private static boolean addLine(Map<Long, CenterCell> network, class_2338 from, class_2338 to, PlannerContext context, CastleRoadCell.Kind kind, String id, List<String> diagnostics) {
        ArrayList<CenterCell> planned = new ArrayList<CenterCell>();
        int x = from.method_10263();
        int z = from.method_10260();
        int dx = Integer.compare(to.method_10263(), x);
        int dz = Integer.compare(to.method_10260(), z);
        while (true) {
            if (context.blocked(x, z)) {
                diagnostics.add("apron_blocked:" + id);
                return false;
            }
            planned.add(new CenterCell(x, context.surfaceY(x, z), z, kind));
            if (x == to.method_10263() && z == to.method_10260()) break;
            if (x != to.method_10263()) {
                x += dx;
            }
            if (z == to.method_10260()) continue;
            z += dz;
        }
        if (network.size() + planned.size() > 16384) {
            diagnostics.add("network_cell_budget_exhausted:" + id);
            return false;
        }
        planned.forEach(cell -> network.putIfAbsent(CastleRoadNetworkPlanner.key(cell.x(), cell.z()), (CenterCell)cell));
        return true;
    }

    private static TerrainSampler terrainSampler(class_3218 world, DynamicCastleWorldgenFootprint terrainFootprint, int floorY, List<String> diagnostics) {
        class_2794 generator = world.method_14178().method_12129();
        if (generator instanceof MapChunkGenerator) {
            MapChunkGenerator mapGenerator = (MapChunkGenerator)generator;
            MapChunkGenerator.NaturalSurfaceSampler naturalSurfaces = mapGenerator.naturalSurfaceSampler();
            return (x, z) -> {
                MapChunkGenerator.NaturalSurfaceSample naturalSurface = naturalSurfaces.sample(x, z);
                int naturalY = naturalSurface.groundY();
                int plannedY = DynamicCastleTerrainApplier.plannedSurfaceY(terrainFootprint, floorY, naturalY, world.method_31607(), x, z);
                boolean water = CastleRoadNetworkPlanner.surfaceFluidBlocks(naturalSurface, terrainFootprint, floorY, world.method_31607(), x, z);
                return new TerrainSample(plannedY, water);
            };
        }
        diagnostics.add("terrain_estimator_unavailable");
        return (x, z) -> new TerrainSample(floorY, true);
    }

    private static boolean surfaceFluidBlocks(MapChunkGenerator.NaturalSurfaceSample naturalSurface, DynamicCastleWorldgenFootprint terrainFootprint, int floorY, int bottomY, int x, int z) {
        return naturalSurface == null || naturalSurface.surfaceFluidOccupied() && !DynamicCastleTerrainApplier.replacesSurface(terrainFootprint, floorY, naturalSurface.groundY(), bottomY, x, z);
    }

    public static TerrainSampleContractAudit auditTerrainSampleContract() {
        DynamicCastleWorldgenFootprint footprint = new DynamicCastleWorldgenFootprint(class_2338.field_10980, new class_2382(16, 1, 16), 28);
        MapChunkGenerator.NaturalSurfaceSample oasisWater = new MapChunkGenerator.NaturalSurfaceSample(66, class_2246.field_10102.method_9564(), true, class_2246.field_10382.method_9564(), 69);
        MapChunkGenerator.NaturalSurfaceSample drySeaLevel = new MapChunkGenerator.NaturalSurfaceSample(63, class_2246.field_10219.method_9564(), false, class_2246.field_10124.method_9564(), 63);
        boolean oasisOutsideBlocked = CastleRoadNetworkPlanner.surfaceFluidBlocks(oasisWater, footprint, 70, -64, 32, 8);
        boolean oasisOwnedCleared = !CastleRoadNetworkPlanner.surfaceFluidBlocks(oasisWater, footprint, 70, -64, 31, 8);
        boolean drySeaLevelOpen = !CastleRoadNetworkPlanner.surfaceFluidBlocks(drySeaLevel, footprint, 70, -64, 32, 8);
        int seamWest = DynamicCastleTerrainApplier.plannedSurfaceY(footprint, 70, 64, -64, 15, 8);
        int seamEast = DynamicCastleTerrainApplier.plannedSurfaceY(footprint, 70, 64, -64, 16, 8);
        boolean deterministicChunkSeam = seamWest == 70 && seamEast == 70 && seamWest == DynamicCastleTerrainApplier.plannedSurfaceY(footprint, 70, 64, -64, 15, 8) && seamEast == DynamicCastleTerrainApplier.plannedSurfaceY(footprint, 70, 64, -64, 16, 8);
        TerrainSampleContractAudit audit = new TerrainSampleContractAudit(oasisOutsideBlocked, oasisOwnedCleared, drySeaLevelOpen, deterministicChunkSeam);
        if (!audit.passes()) {
            throw new IllegalStateException("Castle road terrain-sample contract failed: " + String.valueOf(audit));
        }
        return audit;
    }

    private static SearchBounds searchBounds(class_2338 anchor, DynamicCastleWallPlan wallPlan, RoadPoint canonicalTarget) {
        int targetX = canonicalTarget == null ? anchor.method_10263() : canonicalTarget.x();
        int targetZ = canonicalTarget == null ? anchor.method_10260() : canonicalTarget.z();
        int minX = Math.max(anchor.method_10263() - 1600, Math.min(wallPlan.outerMinX(), targetX) - 32);
        int maxX = Math.min(anchor.method_10263() + 1600, Math.max(wallPlan.outerMinX() + wallPlan.outerWidth() - 1, targetX) + 32);
        int minZ = Math.max(anchor.method_10260() - 1600, Math.min(wallPlan.outerMinZ(), targetZ) - 32);
        int maxZ = Math.min(anchor.method_10260() + 1600, Math.max(wallPlan.outerMinZ() + wallPlan.outerDepth() - 1, targetZ) + 32);
        return new SearchBounds(minX, minZ, maxX, maxZ);
    }

    private static RoadPoint nearestNetworkPoint(RoadPoint point, Collection<CenterCell> network, boolean insideWall, PlannerContext context) {
        return network.stream().filter(cell -> context.insideWall(cell.x(), cell.z()) == insideWall).map(cell -> new RoadPoint(cell.x(), cell.y(), cell.z())).min(Comparator.comparingLong(candidate -> CastleRoadNetworkPlanner.squaredDistance(point.x(), point.z(), candidate.x(), candidate.z())).thenComparingInt(RoadPoint::x).thenComparingInt(RoadPoint::z)).orElse(null);
    }

    private static CastleRoadPortal selectPrimaryPortal(List<CastleRoadPortal> portals, class_2338 target) {
        return portals.stream().min(Comparator.comparingLong(portal -> CastleRoadNetworkPlanner.squaredDistance(portal.exteriorCenter().method_10263(), portal.exteriorCenter().method_10260(), target.method_10263(), target.method_10260())).thenComparing(CastleRoadPortal::id)).orElseThrow();
    }

    private static List<RoadPoint> reconstruct(Map<Long, Long> cameFrom, long end, long start) {
        ArrayList<RoadPoint> reversed = new ArrayList<RoadPoint>();
        long current = end;
        while (true) {
            reversed.add(new RoadPoint((int)(current >> 32), 0, (int)current));
            if (current == start) break;
            Long previous = cameFrom.get(current);
            if (previous == null) {
                return List.of();
            }
            current = previous;
        }
        Collections.reverse(reversed);
        return reversed;
    }

    private static RoadPoint snap(RoadPoint point, PlannerContext context) {
        int snappedZ;
        if (point == null) {
            return null;
        }
        int snappedX = Math.floorDiv(point.x(), 2) * 2;
        if (!context.blocked(snappedX, snappedZ = Math.floorDiv(point.z(), 2) * 2)) {
            return new RoadPoint(snappedX, context.surfaceY(snappedX, snappedZ), snappedZ);
        }
        for (int radius = 1; radius <= 4; ++radius) {
            for (int dx = -radius; dx <= radius; ++dx) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    int z;
                    int x;
                    if (Math.abs(dx) != radius && Math.abs(dz) != radius || context.blocked(x = snappedX + dx * 2, z = snappedZ + dz * 2)) continue;
                    return new RoadPoint(x, context.surfaceY(x, z), z);
                }
            }
        }
        return null;
    }

    private static class_2350 inferWallSide(class_3341 footprint, DynamicCastleWallPlan plan) {
        int north = Math.abs(footprint.method_35417() - plan.outerMinZ());
        int south = Math.abs(footprint.method_35420() - (plan.outerMinZ() + plan.outerDepth() - 1));
        int west = Math.abs(footprint.method_35415() - plan.outerMinX());
        int east = Math.abs(footprint.method_35418() - (plan.outerMinX() + plan.outerWidth() - 1));
        int minimum = Math.min(Math.min(north, south), Math.min(west, east));
        int matches = (north == minimum ? 1 : 0) + (south == minimum ? 1 : 0) + (west == minimum ? 1 : 0) + (east == minimum ? 1 : 0);
        if (matches != 1) {
            return null;
        }
        if (north == minimum) {
            return class_2350.field_11043;
        }
        if (east == minimum) {
            return class_2350.field_11034;
        }
        if (south == minimum) {
            return class_2350.field_11035;
        }
        return class_2350.field_11039;
    }

    private static class_2350 perimeterDirection(class_2338 pos, class_3341 footprint) {
        int north = Math.abs(pos.method_10260() - footprint.method_35417());
        int east = Math.abs(pos.method_10263() - footprint.method_35418());
        int south = Math.abs(pos.method_10260() - footprint.method_35420());
        int west = Math.abs(pos.method_10263() - footprint.method_35415());
        int minimum = Math.min(Math.min(north, south), Math.min(west, east));
        if (minimum > 1) {
            return null;
        }
        if (north == minimum) {
            return class_2350.field_11043;
        }
        if (east == minimum) {
            return class_2350.field_11034;
        }
        if (south == minimum) {
            return class_2350.field_11035;
        }
        return class_2350.field_11039;
    }

    private static class_2470 rotationFor(class_2350 side) {
        return switch (side) {
            case class_2350.field_11043 -> class_2470.field_11467;
            case class_2350.field_11034 -> class_2470.field_11463;
            case class_2350.field_11035 -> class_2470.field_11464;
            case class_2350.field_11039 -> class_2470.field_11465;
            default -> class_2470.field_11467;
        };
    }

    private static class_2470 cornerRotation(Set<Long> interiorChunks, int vertexX, int vertexZ) {
        boolean southeast;
        boolean southwest;
        boolean northeast;
        boolean northwest = interiorChunks.contains(class_1923.method_8331((int)(vertexX - 1), (int)(vertexZ - 1)));
        int filled = (northwest ? 1 : 0) + ((northeast = interiorChunks.contains(class_1923.method_8331((int)vertexX, (int)(vertexZ - 1)))) ? 1 : 0) + ((southwest = interiorChunks.contains(class_1923.method_8331((int)(vertexX - 1), (int)vertexZ))) ? 1 : 0) + ((southeast = interiorChunks.contains(class_1923.method_8331((int)vertexX, (int)vertexZ))) ? 1 : 0);
        if (filled == 0 || filled == 2 || filled == 4) {
            return null;
        }
        if (southeast && filled == 1 || !southeast && filled == 3) {
            return class_2470.field_11467;
        }
        if (southwest && filled == 1 || !southwest && filled == 3) {
            return class_2470.field_11463;
        }
        if (northwest && filled == 1 || !northwest && filled == 3) {
            return class_2470.field_11464;
        }
        return class_2470.field_11465;
    }

    private static int directionOrder(class_2350 direction) {
        return switch (direction) {
            case class_2350.field_11043 -> 0;
            case class_2350.field_11034 -> 1;
            case class_2350.field_11035 -> 2;
            case class_2350.field_11039 -> 3;
            default -> 4;
        };
    }

    private static int normalCoordinate(class_2338 pos, class_2350 side) {
        return side.method_10166() == class_2350.class_2351.field_11051 ? pos.method_10260() : pos.method_10263();
    }

    private static int crossCoordinate(class_2338 pos, class_2350 side) {
        return side.method_10166() == class_2350.class_2351.field_11051 ? pos.method_10263() : pos.method_10260();
    }

    private static RoadPoint point(class_2338 pos) {
        return new RoadPoint(pos.method_10263(), pos.method_10264(), pos.method_10260());
    }

    private static long key(int x, int z) {
        return (long)x << 32 ^ (long)z & 0xFFFFFFFFL;
    }

    private static long squaredDistance(int x1, int z1, int x2, int z2) {
        long dx = (long)x1 - (long)x2;
        long dz = (long)z1 - (long)z2;
        return dx * dx + dz * dz;
    }

    private static int manhattan(RoadPoint first, RoadPoint second) {
        return Math.abs(first.x() - second.x()) + Math.abs(first.z() - second.z());
    }

    private static int compareCoordinates(int x, int z, RoadPoint other) {
        if (other == null) {
            return -1;
        }
        int xCompare = Integer.compare(x, other.x());
        return xCompare != 0 ? xCompare : Integer.compare(z, other.z());
    }

    private static int pathNoise(long seed, int x, int z) {
        long value = seed ^ (long)x * -7046029254386353131L ^ (long)z * -4658895280553007687L;
        value ^= value >>> 30;
        value *= -4658895280553007687L;
        value ^= value >>> 27;
        return (int)(value & 3L);
    }

    @FunctionalInterface
    private static interface TerrainSampler {
        public TerrainSample sample(int var1, int var2);
    }

    private record RoadPoint(int x, int y, int z) {
        class_2338 pos() {
            return new class_2338(this.x, this.y, this.z);
        }
    }

    private static final class PlannerContext {
        private final DynamicCastleWallPlan wall;
        private final List<CastleRoadPortal> portals;
        private final List<CastleRoadAccess> accesses;
        private final List<CastleRoadBounds> wallBands;
        private final List<RoadObstacle> structures;
        private final TerrainSampler terrain;
        private final SearchBounds search;
        private final Map<Long, TerrainSample> samples = new HashMap<Long, TerrainSample>();

        private PlannerContext(DynamicCastleWallPlan wall, List<CastleRoadPortal> portals, List<CastleRoadAccess> accesses, List<CastleRoadBounds> wallBands, List<RoadObstacle> structures, TerrainSampler terrain, SearchBounds search) {
            this.wall = wall;
            this.portals = portals;
            this.accesses = accesses;
            this.wallBands = wallBands;
            this.structures = structures;
            this.terrain = terrain;
            this.search = search;
        }

        private int surfaceY(int x, int z) {
            return this.samples.computeIfAbsent(CastleRoadNetworkPlanner.key(x, z), ignored -> this.terrain.sample(x, z)).y();
        }

        private boolean water(int x, int z) {
            return this.samples.computeIfAbsent(CastleRoadNetworkPlanner.key(x, z), ignored -> this.terrain.sample(x, z)).water();
        }

        private boolean blocked(int x, int z) {
            if (!this.search.contains(x, z)) {
                return true;
            }
            if (this.water(x, z)) {
                return true;
            }
            for (RoadObstacle structure : this.structures) {
                if (!structure.contains(x, z) || this.onValidatedAccess(structure, x, z)) continue;
                return true;
            }
            if (!this.onWallBand(x, z)) {
                return false;
            }
            for (CastleRoadPortal portal : this.portals) {
                if (!portal.aperture().contains(x, z)) continue;
                return false;
            }
            return true;
        }

        private boolean insideWall(int x, int z) {
            return this.wall.interiorChunkKeys().contains(class_1923.method_8331((int)Math.floorDiv(x, 16), (int)Math.floorDiv(z, 16)));
        }

        private boolean onWallBand(int x, int z) {
            return this.wallBands.stream().anyMatch(bounds -> bounds.contains(x, z));
        }

        private boolean onValidatedAccess(RoadObstacle obstacle, int x, int z) {
            for (CastleRoadAccess access : this.accesses) {
                if (!access.obstacleId().equals(obstacle.id())) continue;
                int dx = x - access.threshold().method_10263();
                int dz = z - access.threshold().method_10260();
                int forward = dx * access.outwardFacing().method_10148() + dz * access.outwardFacing().method_10165();
                int cross = Math.abs(dx * access.outwardFacing().method_10165() - dz * access.outwardFacing().method_10148());
                if (forward < 0 || forward > 4 || cross > Math.max(2, access.width() / 2)) continue;
                return true;
            }
            return false;
        }
    }

    private record SearchBounds(int minX, int minZ, int maxX, int maxZ) {
        boolean contains(int x, int z) {
            return x >= this.minX && x <= this.maxX && z >= this.minZ && z <= this.maxZ;
        }
    }

    private record TemplateComponent(class_3499 template, String path, class_2338 origin, class_2470 rotation, CastleRoadBounds footprint) {
    }

    private record DoorCandidate(class_2338 pos, class_2350 outward) {
    }

    private record RoadObstacle(String id, List<CastleRoadBounds> parts) {
        private boolean contains(int x, int z) {
            return this.parts.stream().anyMatch(part -> part.contains(x, z));
        }

        private boolean intersects(CastleRoadBounds bounds) {
            return this.parts.stream().anyMatch(bounds::intersects);
        }
    }

    private record CenterCell(int x, int y, int z, CastleRoadCell.Kind kind) {
    }

    private record Node(int x, int z, int cost, int heuristic, int priority, long sequence) {
    }

    public record TerrainSampleContractAudit(boolean oasisOutsideBlocked, boolean oasisOwnedCleared, boolean drySeaLevelOpen, boolean deterministicChunkSeam) {
        public boolean passes() {
            return this.oasisOutsideBlocked && this.oasisOwnedCleared && this.drySeaLevelOpen && this.deterministicChunkSeam;
        }
    }

    private record TerrainSample(int y, boolean water) {
    }
}

