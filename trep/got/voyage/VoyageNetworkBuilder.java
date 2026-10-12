/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package trep.got.voyage;

import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import net.minecraft.class_243;
import trep.got.voyage.VoyageHarbourNode;
import trep.got.voyage.VoyageLane;
import trep.got.voyage.VoyageNetwork;

public final class VoyageNetworkBuilder {
    private static final int CELL_SIZE_PIXELS = 4;
    private static final int MIN_SAFE_SHORE_DISTANCE = 2;
    private static final int NEAREST_NEIGHBOR_TARGET = 2;
    private final BufferedImage mapImage;
    private final Set<Integer> waterColors;
    private final List<VoyageHarbourNode> nodes;
    private final int gridWidth;
    private final int gridHeight;
    private final boolean[][] waterGrid;
    private final int[][] shoreDistance;

    private VoyageNetworkBuilder(BufferedImage mapImage, Set<Integer> waterColors, List<VoyageHarbourNode> nodes) {
        this.mapImage = mapImage;
        this.waterColors = waterColors;
        this.nodes = nodes;
        this.gridWidth = Math.max(1, mapImage.getWidth() / 4);
        this.gridHeight = Math.max(1, mapImage.getHeight() / 4);
        this.waterGrid = this.buildWaterGrid();
        this.shoreDistance = this.buildShoreDistance();
    }

    public static VoyageNetwork build(BufferedImage mapImage, Set<Integer> waterColors, List<VoyageHarbourNode> nodes) {
        if (mapImage == null || waterColors == null || nodes == null || nodes.size() < 2) {
            return new VoyageNetwork(Map.of(), List.of());
        }
        return new VoyageNetworkBuilder(mapImage, waterColors, List.copyOf(nodes)).build();
    }

    private VoyageNetwork build() {
        LinkedHashMap<String, VoyageHarbourNode> nodesById = new LinkedHashMap<String, VoyageHarbourNode>();
        for (VoyageHarbourNode voyageHarbourNode : this.nodes) {
            nodesById.put(voyageHarbourNode.id(), voyageHarbourNode);
        }
        HashMap<String, GridCell> harbourCells = new HashMap<String, GridCell>();
        for (VoyageHarbourNode node : this.nodes) {
            GridCell waterCell = this.findNearestWaterCell(node);
            if (waterCell == null) continue;
            harbourCells.put(node.id(), waterCell);
        }
        if (harbourCells.size() < 2) {
            return new VoyageNetwork(nodesById, List.of());
        }
        ArrayList<LaneCandidate> arrayList = new ArrayList<LaneCandidate>();
        for (int left = 0; left < this.nodes.size(); ++left) {
            for (int right = left + 1; right < this.nodes.size(); ++right) {
                VoyageHarbourNode from = this.nodes.get(left);
                VoyageHarbourNode to = this.nodes.get(right);
                GridCell fromCell = (GridCell)harbourCells.get(from.id());
                GridCell toCell = (GridCell)harbourCells.get(to.id());
                if (fromCell == null || toCell == null) continue;
                Optional<LaneCandidate> candidate = this.buildLaneCandidate(from, to, fromCell, toCell);
                candidate.ifPresent(arrayList::add);
            }
        }
        if (arrayList.isEmpty()) {
            return new VoyageNetwork(nodesById, List.of());
        }
        arrayList.sort(Comparator.comparingDouble(LaneCandidate::length));
        HashMap<String, Integer> degree = new HashMap<String, Integer>();
        for (VoyageHarbourNode node : this.nodes) {
            degree.put(node.id(), 0);
        }
        DisjointSet components = new DisjointSet(nodesById.keySet());
        ArrayList<VoyageLane> selected = new ArrayList<VoyageLane>();
        LinkedHashSet<String> selectedKeys = new LinkedHashSet<String>();
        for (LaneCandidate candidate : arrayList) {
            if (components.connected(candidate.fromId(), candidate.toId())) continue;
            components.union(candidate.fromId(), candidate.toId());
            this.selectLane(candidate, selected, selectedKeys, degree);
        }
        for (LaneCandidate candidate : arrayList) {
            if (degree.getOrDefault(candidate.fromId(), 0) >= 2 && degree.getOrDefault(candidate.toId(), 0) >= 2) continue;
            this.selectLane(candidate, selected, selectedKeys, degree);
        }
        return new VoyageNetwork(nodesById, selected);
    }

    private void selectLane(LaneCandidate candidate, List<VoyageLane> selected, Set<String> selectedKeys, Map<String, Integer> degree) {
        String key = VoyageNetworkBuilder.laneKey(candidate.fromId(), candidate.toId());
        if (!selectedKeys.add(key)) {
            return;
        }
        selected.add(candidate.toLane());
        degree.merge(candidate.fromId(), 1, Integer::sum);
        degree.merge(candidate.toId(), 1, Integer::sum);
    }

    private Optional<LaneCandidate> buildLaneCandidate(VoyageHarbourNode from, VoyageHarbourNode to, GridCell fromCell, GridCell toCell) {
        List<GridCell> gridPath = this.findGridPath(fromCell, toCell);
        if (gridPath.isEmpty()) {
            return Optional.empty();
        }
        ArrayList<class_243> worldPoints = new ArrayList<class_243>();
        worldPoints.add(new class_243((double)from.worldX() + 0.5, 0.0, (double)from.worldZ() + 0.5));
        for (GridCell cell : gridPath) {
            worldPoints.add(this.cellCenterToWorld(cell));
        }
        worldPoints.add(new class_243((double)to.worldX() + 0.5, 0.0, (double)to.worldZ() + 0.5));
        List<class_243> simplified = this.simplify(worldPoints);
        double length = this.pathLength(simplified);
        return Optional.of(new LaneCandidate(from.id(), to.id(), List.copyOf(simplified), length));
    }

    private List<GridCell> findGridPath(GridCell start, GridCell target) {
        PriorityQueue<SearchNode> open = new PriorityQueue<SearchNode>(Comparator.comparingDouble(SearchNode::priority));
        HashMap<Integer, Double> bestCost = new HashMap<Integer, Double>();
        HashMap<Integer, Integer> previous = new HashMap<Integer, Integer>();
        int startIndex = this.toIndex(start.x(), start.z());
        int targetIndex = this.toIndex(target.x(), target.z());
        bestCost.put(startIndex, 0.0);
        open.add(new SearchNode(startIndex, 0.0, this.heuristic(start, target)));
        while (!open.isEmpty()) {
            SearchNode current = open.poll();
            if (current.index() == targetIndex) {
                return this.reconstructPath(previous, targetIndex);
            }
            if (current.cost() > bestCost.getOrDefault(current.index(), Double.POSITIVE_INFINITY)) continue;
            int currentX = current.index() % this.gridWidth;
            int currentZ = current.index() / this.gridWidth;
            for (int offsetZ = -1; offsetZ <= 1; ++offsetZ) {
                for (int offsetX = -1; offsetX <= 1; ++offsetX) {
                    int nextZ;
                    int nextX;
                    if (offsetX == 0 && offsetZ == 0 || !this.isTraversable(nextX = currentX + offsetX, nextZ = currentZ + offsetZ)) continue;
                    double stepCost = offsetX != 0 && offsetZ != 0 ? 1.41421356237 : 1.0;
                    int shoreline = this.shoreDistance[nextZ][nextX];
                    double shorelinePenalty = shoreline >= 2 ? 0.0 : (double)(2 - shoreline) * 4.0;
                    int nextIndex = this.toIndex(nextX, nextZ);
                    double nextCost = current.cost() + stepCost + shorelinePenalty;
                    if (nextCost >= bestCost.getOrDefault(nextIndex, Double.POSITIVE_INFINITY)) continue;
                    bestCost.put(nextIndex, nextCost);
                    previous.put(nextIndex, current.index());
                    open.add(new SearchNode(nextIndex, nextCost, nextCost + this.heuristic(nextX, nextZ, target.x(), target.z())));
                }
            }
        }
        return List.of();
    }

    private List<GridCell> reconstructPath(Map<Integer, Integer> previous, int targetIndex) {
        ArrayList<GridCell> cells = new ArrayList<GridCell>();
        int cursor = targetIndex;
        cells.add(this.fromIndex(cursor));
        while (previous.containsKey(cursor)) {
            cursor = previous.get(cursor);
            cells.add(this.fromIndex(cursor));
        }
        Collections.reverse(cells);
        return cells;
    }

    private GridCell findNearestWaterCell(VoyageHarbourNode node) {
        GridCell start = this.worldToCell(node.worldX(), node.worldZ());
        boolean[][] visited = new boolean[this.gridHeight][this.gridWidth];
        ArrayDeque<GridCell> queue = new ArrayDeque<GridCell>();
        queue.add(start);
        visited[start.z()][start.x()] = true;
        GridCell best = null;
        double bestScore = Double.POSITIVE_INFINITY;
        while (!queue.isEmpty()) {
            GridCell current = (GridCell)queue.removeFirst();
            if (this.waterGrid[current.z()][current.x()]) {
                double score = this.squaredCellDistance(start, current);
                int shoreline = this.shoreDistance[current.z()][current.x()];
                if (shoreline < 2) {
                    score += (double)(2 - shoreline) * 5.0;
                }
                if (score < bestScore) {
                    bestScore = score;
                    best = current;
                }
            }
            if (best != null && this.squaredCellDistance(start, current) > bestScore + 16.0) continue;
            for (int offsetZ = -1; offsetZ <= 1; ++offsetZ) {
                for (int offsetX = -1; offsetX <= 1; ++offsetX) {
                    if (Math.abs(offsetX) + Math.abs(offsetZ) != 1) continue;
                    int nextX = current.x() + offsetX;
                    int nextZ = current.z() + offsetZ;
                    if (nextX < 0 || nextZ < 0 || nextX >= this.gridWidth || nextZ >= this.gridHeight || visited[nextZ][nextX]) continue;
                    visited[nextZ][nextX] = true;
                    queue.addLast(new GridCell(nextX, nextZ));
                }
            }
        }
        return best;
    }

    private boolean isTraversable(int gridX, int gridZ) {
        return gridX >= 0 && gridZ >= 0 && gridX < this.gridWidth && gridZ < this.gridHeight && this.waterGrid[gridZ][gridX];
    }

    private boolean[][] buildWaterGrid() {
        boolean[][] grid = new boolean[this.gridHeight][this.gridWidth];
        for (int gridZ = 0; gridZ < this.gridHeight; ++gridZ) {
            for (int gridX = 0; gridX < this.gridWidth; ++gridX) {
                int waterSamples = 0;
                int totalSamples = 0;
                for (int sampleZ = 0; sampleZ < 4; sampleZ += 2) {
                    for (int sampleX = 0; sampleX < 4; sampleX += 2) {
                        int pixelX = Math.min(this.mapImage.getWidth() - 1, gridX * 4 + sampleX);
                        int pixelZ = Math.min(this.mapImage.getHeight() - 1, gridZ * 4 + sampleZ);
                        ++totalSamples;
                        if (!this.isWaterPixel(pixelX, pixelZ)) continue;
                        ++waterSamples;
                    }
                }
                grid[gridZ][gridX] = waterSamples > 0 && waterSamples >= Math.max(1, totalSamples / 2);
            }
        }
        return grid;
    }

    private int[][] buildShoreDistance() {
        int gridX;
        int gridZ;
        int[][] distance;
        for (int[] row : distance = new int[this.gridHeight][this.gridWidth]) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        ArrayDeque<GridCell> queue = new ArrayDeque<GridCell>();
        for (gridZ = 0; gridZ < this.gridHeight; ++gridZ) {
            for (gridX = 0; gridX < this.gridWidth; ++gridX) {
                if (!this.waterGrid[gridZ][gridX] || !this.touchesLand(gridX, gridZ)) continue;
                distance[gridZ][gridX] = 0;
                queue.addLast(new GridCell(gridX, gridZ));
            }
        }
        while (!queue.isEmpty()) {
            GridCell current = (GridCell)queue.removeFirst();
            int nextDistance = distance[current.z()][current.x()] + 1;
            for (int offsetZ = -1; offsetZ <= 1; ++offsetZ) {
                for (int offsetX = -1; offsetX <= 1; ++offsetX) {
                    int nextZ;
                    int nextX;
                    if (Math.abs(offsetX) + Math.abs(offsetZ) != 1 || !this.isTraversable(nextX = current.x() + offsetX, nextZ = current.z() + offsetZ) || nextDistance >= distance[nextZ][nextX]) continue;
                    distance[nextZ][nextX] = nextDistance;
                    queue.addLast(new GridCell(nextX, nextZ));
                }
            }
        }
        for (gridZ = 0; gridZ < this.gridHeight; ++gridZ) {
            for (gridX = 0; gridX < this.gridWidth; ++gridX) {
                if (!this.waterGrid[gridZ][gridX] || distance[gridZ][gridX] != Integer.MAX_VALUE) continue;
                distance[gridZ][gridX] = 6;
            }
        }
        return distance;
    }

    private boolean touchesLand(int gridX, int gridZ) {
        for (int offsetZ = -1; offsetZ <= 1; ++offsetZ) {
            for (int offsetX = -1; offsetX <= 1; ++offsetX) {
                if (Math.abs(offsetX) + Math.abs(offsetZ) != 1) continue;
                int nextX = gridX + offsetX;
                int nextZ = gridZ + offsetZ;
                if (nextX >= 0 && nextZ >= 0 && nextX < this.gridWidth && nextZ < this.gridHeight && this.waterGrid[nextZ][nextX]) continue;
                return true;
            }
        }
        return false;
    }

    private boolean isWaterPixel(int pixelX, int pixelZ) {
        int argb = this.mapImage.getRGB(pixelX, pixelZ);
        if (argb >>> 24 == 0) {
            return false;
        }
        return this.waterColors.contains(argb & 0xFFFFFF);
    }

    private GridCell worldToCell(int worldX, int worldZ) {
        int pixelX = Math.floorDiv(worldX + 48000, 32);
        int pixelZ = Math.floorDiv(worldZ + 48000, 32);
        int gridX = Math.max(0, Math.min(this.gridWidth - 1, pixelX / 4));
        int gridZ = Math.max(0, Math.min(this.gridHeight - 1, pixelZ / 4));
        return new GridCell(gridX, gridZ);
    }

    private class_243 cellCenterToWorld(GridCell cell) {
        int pixelX = cell.x() * 4 + 2;
        int pixelZ = cell.z() * 4 + 2;
        double worldX = (double)(pixelX - 1500) * 32.0 + 16.0;
        double worldZ = (double)(pixelZ - 1500) * 32.0 + 16.0;
        return new class_243(worldX, 0.0, worldZ);
    }

    private double pathLength(List<class_243> points) {
        double length = 0.0;
        for (int index = 1; index < points.size(); ++index) {
            length += points.get(index).method_1022(points.get(index - 1));
        }
        return length;
    }

    private List<class_243> simplify(List<class_243> points) {
        if (points.size() <= 2) {
            return points;
        }
        ArrayList<class_243> simplified = new ArrayList<class_243>();
        simplified.add(points.getFirst());
        for (int index = 1; index < points.size() - 1; ++index) {
            class_243 previous = points.get(index - 1);
            class_243 current = points.get(index);
            class_243 next = points.get(index + 1);
            class_243 first = current.method_1020(previous);
            class_243 second = next.method_1020(current);
            double cross = Math.abs(first.field_1352 * second.field_1350 - first.field_1350 * second.field_1352);
            if (cross < 0.001) continue;
            simplified.add(current);
        }
        simplified.add(points.getLast());
        return simplified;
    }

    private double heuristic(GridCell start, GridCell target) {
        return this.heuristic(start.x(), start.z(), target.x(), target.z());
    }

    private double heuristic(int x, int z, int targetX, int targetZ) {
        double deltaX = targetX - x;
        double deltaZ = targetZ - z;
        return Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
    }

    private double squaredCellDistance(GridCell left, GridCell right) {
        double deltaX = left.x() - right.x();
        double deltaZ = left.z() - right.z();
        return deltaX * deltaX + deltaZ * deltaZ;
    }

    private int toIndex(int gridX, int gridZ) {
        return gridZ * this.gridWidth + gridX;
    }

    private GridCell fromIndex(int index) {
        return new GridCell(index % this.gridWidth, index / this.gridWidth);
    }

    private static String laneKey(String left, String right) {
        return left.compareTo(right) <= 0 ? left + "|" + right : right + "|" + left;
    }

    private record GridCell(int x, int z) {
    }

    private static final class DisjointSet {
        private final Map<String, String> parent = new HashMap<String, String>();

        private DisjointSet(Set<String> ids) {
            for (String id : ids) {
                this.parent.put(id, id);
            }
        }

        private String find(String id) {
            String root = this.parent.get(id);
            if (root == null || root.equals(id)) {
                return id;
            }
            String resolved = this.find(root);
            this.parent.put(id, resolved);
            return resolved;
        }

        private boolean connected(String left, String right) {
            return this.find(left).equals(this.find(right));
        }

        private void union(String left, String right) {
            this.parent.put(this.find(left), this.find(right));
        }
    }

    private record LaneCandidate(String fromId, String toId, List<class_243> worldPoints, double length) {
        private VoyageLane toLane() {
            return new VoyageLane(this.fromId, this.toId, this.worldPoints, this.length);
        }
    }

    private record SearchNode(int index, double cost, double priority) {
    }
}

