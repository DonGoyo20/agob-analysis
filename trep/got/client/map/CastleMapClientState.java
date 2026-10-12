/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 */
package trep.got.client.map;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import trep.got.client.map.CastleTerritoryClientState;
import trep.got.network.CastleMapPayload;
import trep.got.registry.GotKingdoms;
import trep.got.voyage.VoyageHarbourNode;
import trep.got.voyage.VoyageLane;
import trep.got.voyage.VoyageNetwork;

@Environment(value=EnvType.CLIENT)
public final class CastleMapClientState {
    private static List<CastleMapPayload.Entry> castles = List.of();
    private static List<CastleMapPayload.WarPartyEntry> warParties = List.of();
    private static Map<String, CastleMapPayload.DynamicKingdomEntry> dynamicKingdomsById = Map.of();
    private static VoyageNetwork voyageNetwork;
    private static long syncedWorldTick;
    private static long syncedProgressTick;

    private CastleMapClientState() {
    }

    public static void apply(CastleMapPayload payload, class_310 client) {
        client.execute(() -> {
            for (CastleMapPayload.DynamicKingdomEntry dynamicKingdomEntry : payload.dynamicKingdoms()) {
                class_2960 class_29602 = class_2960.method_12829((String)dynamicKingdomEntry.kingdomId());
                class_2960 parentKingdomId = class_2960.method_12829((String)dynamicKingdomEntry.parentKingdomId());
                if (class_29602 == null || parentKingdomId == null) continue;
                GotKingdoms.registerDynamicKingdom(class_29602, dynamicKingdomEntry.displayName(), parentKingdomId);
            }
            LinkedHashMap<String, CastleMapPayload.DynamicKingdomEntry> nextDynamicKingdoms = new LinkedHashMap<String, CastleMapPayload.DynamicKingdomEntry>();
            for (CastleMapPayload.DynamicKingdomEntry dynamicKingdomEntry : payload.dynamicKingdoms()) {
                nextDynamicKingdoms.put(dynamicKingdomEntry.kingdomId(), dynamicKingdomEntry);
            }
            dynamicKingdomsById = Map.copyOf(nextDynamicKingdoms);
            castles = payload.castles();
            warParties = payload.warParties();
            syncedWorldTick = payload.syncedWorldTick();
            syncedProgressTick = payload.syncedProgressTick();
            CastleTerritoryClientState.apply(castles, client);
            LinkedHashMap<String, VoyageHarbourNode> linkedHashMap = new LinkedHashMap<String, VoyageHarbourNode>();
            for (CastleMapPayload.Entry castle : castles) {
                if (!castle.subtypeIds().contains("harbour")) continue;
                linkedHashMap.put(castle.id(), new VoyageHarbourNode(castle.id(), castle.name(), castle.travelX(), castle.travelZ()));
            }
            ArrayList<VoyageLane> arrayList = new ArrayList<VoyageLane>();
            for (CastleMapPayload.Lane lane : payload.lanes()) {
                List<class_243> points = lane.points().stream().map(point -> new class_243((double)point.x() + 0.5, 0.0, (double)point.z() + 0.5)).toList();
                double length = 0.0;
                for (int index = 1; index < points.size(); ++index) {
                    length += points.get(index).method_1022(points.get(index - 1));
                }
                arrayList.add(new VoyageLane(lane.fromId(), lane.toId(), points, length));
            }
            voyageNetwork = new VoyageNetwork(linkedHashMap, arrayList);
        });
    }

    public static List<CastleMapPayload.Entry> getCastles() {
        return castles;
    }

    public static CastleMapPayload.DynamicKingdomEntry getDynamicKingdom(String kingdomId) {
        return kingdomId == null ? null : dynamicKingdomsById.get(kingdomId);
    }

    public static VoyageNetwork getVoyageNetwork() {
        if (voyageNetwork == null) {
            return new VoyageNetwork(Map.of(), List.of());
        }
        return voyageNetwork;
    }

    public static List<CastleMapPayload.WarPartyEntry> getWarParties() {
        return warParties;
    }

    public static long currentProgressTick(class_310 client) {
        if (client == null || client.field_1687 == null) {
            return syncedProgressTick;
        }
        long delta = Math.max(0L, client.field_1687.method_8510() - syncedWorldTick);
        return syncedProgressTick + delta;
    }

    public static void clear() {
        castles = List.of();
        warParties = List.of();
        dynamicKingdomsById = Map.of();
        voyageNetwork = null;
        syncedWorldTick = 0L;
        syncedProgressTick = 0L;
        CastleTerritoryClientState.clear(class_310.method_1551());
    }
}

