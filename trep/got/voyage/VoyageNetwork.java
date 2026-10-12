/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package trep.got.voyage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import net.minecraft.class_243;
import trep.got.voyage.VoyageHarbourNode;
import trep.got.voyage.VoyageLane;
import trep.got.voyage.VoyagePath;

public final class VoyageNetwork {
    private final Map<String, VoyageHarbourNode> nodesById;
    private final List<VoyageLane> lanes;
    private final Map<String, List<VoyageLane>> lanesByHarbour;

    public VoyageNetwork(Map<String, VoyageHarbourNode> nodesById, List<VoyageLane> lanes) {
        this.nodesById = Map.copyOf(nodesById);
        this.lanes = List.copyOf(lanes);
        HashMap<String, List> adjacency = new HashMap<String, List>();
        for (VoyageLane lane : lanes) {
            adjacency.computeIfAbsent(lane.fromId(), ignored -> new ArrayList()).add(lane);
            adjacency.computeIfAbsent(lane.toId(), ignored -> new ArrayList()).add(lane);
        }
        HashMap frozen = new HashMap();
        for (Map.Entry entry : adjacency.entrySet()) {
            frozen.put((String)entry.getKey(), List.copyOf((Collection)entry.getValue()));
        }
        this.lanesByHarbour = Map.copyOf(frozen);
    }

    public Map<String, VoyageHarbourNode> nodesById() {
        return this.nodesById;
    }

    public List<VoyageLane> lanes() {
        return this.lanes;
    }

    public Optional<VoyagePath> findPath(String fromId, String toId) {
        if (fromId == null || toId == null || fromId.isBlank() || toId.isBlank() || fromId.equals(toId)) {
            return Optional.empty();
        }
        if (!this.nodesById.containsKey(fromId) || !this.nodesById.containsKey(toId)) {
            return Optional.empty();
        }
        HashMap<String, Double> distance = new HashMap<String, Double>();
        HashMap<String, VoyageLane> previousLane = new HashMap<String, VoyageLane>();
        HashMap<String, String> previousHarbour = new HashMap<String, String>();
        PriorityQueue<RouteState> open = new PriorityQueue<RouteState>(Comparator.comparingDouble(RouteState::cost));
        distance.put(fromId, 0.0);
        open.add(new RouteState(fromId, 0.0));
        while (!open.isEmpty()) {
            RouteState state = open.poll();
            if (state.cost() > distance.getOrDefault(state.harbourId(), Double.POSITIVE_INFINITY)) continue;
            if (state.harbourId().equals(toId)) break;
            for (VoyageLane lane : this.lanesByHarbour.getOrDefault(state.harbourId(), List.of())) {
                double nextCost;
                String next = lane.other(state.harbourId());
                if (next.isBlank() || (nextCost = state.cost() + lane.lengthBlocks()) >= distance.getOrDefault(next, Double.POSITIVE_INFINITY)) continue;
                distance.put(next, nextCost);
                previousLane.put(next, lane);
                previousHarbour.put(next, state.harbourId());
                open.add(new RouteState(next, nextCost));
            }
        }
        if (!distance.containsKey(toId)) {
            return Optional.empty();
        }
        ArrayList<String> harbourStops = new ArrayList<String>();
        ArrayList<VoyageLane> lanePath = new ArrayList<VoyageLane>();
        String current = toId;
        harbourStops.add(current);
        while (!current.equals(fromId)) {
            VoyageLane lane = (VoyageLane)previousLane.get(current);
            String previous = (String)previousHarbour.get(current);
            if (lane == null || previous == null) {
                return Optional.empty();
            }
            lanePath.add(lane);
            current = previous;
            harbourStops.add(current);
        }
        Collections.reverse(harbourStops);
        Collections.reverse(lanePath);
        ArrayList<class_243> worldPoints = new ArrayList<class_243>();
        String segmentStart = fromId;
        for (VoyageLane lane : lanePath) {
            List<class_243> segment = lane.pointsFrom(segmentStart);
            if (segment.isEmpty()) {
                return Optional.empty();
            }
            if (!worldPoints.isEmpty()) {
                segment = segment.subList(1, segment.size());
            }
            worldPoints.addAll(segment);
            segmentStart = lane.other(segmentStart);
        }
        List<class_243> compact = VoyageNetwork.compactPoints(worldPoints);
        return Optional.of(new VoyagePath(fromId, toId, List.copyOf(harbourStops), List.copyOf(compact), List.copyOf(VoyageNetwork.sampleKeypoints(compact, 100.0)), (Double)distance.get(toId)));
    }

    private static List<class_243> compactPoints(List<class_243> points) {
        if (points.size() <= 2) {
            return points;
        }
        ArrayList<class_243> compact = new ArrayList<class_243>();
        compact.add(points.getFirst());
        for (int index = 1; index < points.size() - 1; ++index) {
            class_243 previous = points.get(index - 1);
            class_243 current = points.get(index);
            class_243 next = points.get(index + 1);
            class_243 a = current.method_1020(previous);
            class_243 b = next.method_1020(current);
            double cross = Math.abs(a.field_1352 * b.field_1350 - a.field_1350 * b.field_1352);
            if (cross < 0.001) continue;
            compact.add(current);
        }
        compact.add(points.getLast());
        return compact;
    }

    private static List<class_243> sampleKeypoints(List<class_243> points, double spacingBlocks) {
        if (points.isEmpty()) {
            return List.of();
        }
        ArrayList<class_243> sampled = new ArrayList<class_243>();
        sampled.add(points.getFirst());
        double remaining = spacingBlocks;
        class_243 segmentStart = points.getFirst();
        for (int index = 1; index < points.size(); ++index) {
            class_243 segmentEnd = points.get(index);
            class_243 delta = segmentEnd.method_1020(segmentStart);
            double segmentLength = delta.method_37267();
            if (segmentLength < 0.001) {
                segmentStart = segmentEnd;
                continue;
            }
            class_243 direction = delta.method_1029();
            double consumed = 0.0;
            while (consumed + remaining < segmentLength) {
                sampled.add(segmentStart.method_1019(direction.method_1021(consumed += remaining)));
                remaining = spacingBlocks;
            }
            remaining -= segmentLength - consumed;
            segmentStart = segmentEnd;
        }
        if (sampled.isEmpty() || !VoyageNetwork.samePoint((class_243)sampled.getLast(), points.getLast())) {
            sampled.add(points.getLast());
        }
        return sampled;
    }

    private static boolean samePoint(class_243 left, class_243 right) {
        return left.method_1025(right) < 1.0;
    }

    private record RouteState(String harbourId, double cost) {
    }
}

