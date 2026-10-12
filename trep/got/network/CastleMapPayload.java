/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record CastleMapPayload(long syncedWorldTick, long syncedProgressTick, List<DynamicKingdomEntry> dynamicKingdoms, List<Entry> castles, List<Lane> lanes, List<WarPartyEntry> warParties) implements class_8710
{
    public static final class_8710.class_9154<CastleMapPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"castle_map"));
    public static final class_9139<class_9129, CastleMapPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_52974(value.syncedWorldTick());
        buf.method_52974(value.syncedProgressTick());
        buf.method_10804(value.dynamicKingdoms.size());
        for (DynamicKingdomEntry dynamicKingdomEntry : value.dynamicKingdoms) {
            buf.method_10814(dynamicKingdomEntry.kingdomId());
            buf.method_10814(dynamicKingdomEntry.displayName());
            buf.method_10814(dynamicKingdomEntry.parentKingdomId());
            buf.method_10814(dynamicKingdomEntry.houseId());
        }
        buf.method_10804(value.castles.size());
        for (Entry entry : value.castles) {
            buf.method_10814(entry.id());
            buf.method_10804(entry.worldX());
            buf.method_10804(entry.worldZ());
            buf.method_10804(entry.travelX());
            buf.method_10804(entry.travelZ());
            buf.method_52964(entry.stone());
            buf.method_52964(entry.capital());
            buf.method_10814(entry.tierId());
            buf.method_10814(entry.name());
            buf.method_10814(entry.lore());
            buf.method_10814(entry.kingdomId());
            buf.method_10814(entry.houseId());
            buf.method_10814(entry.originalKingdomId());
            buf.method_10814(entry.religionId());
            buf.method_10814(entry.kingdomLine());
            buf.method_10814(entry.religionLine());
            buf.method_10814(entry.lordName());
            buf.method_10804(entry.subtypeIds().size());
            for (String subtypeId : entry.subtypeIds()) {
                buf.method_10814(subtypeId);
            }
            buf.method_52964(entry.unlocked());
            buf.method_52964(entry.tourneyActive());
        }
        buf.method_10804(value.lanes.size());
        for (Lane lane : value.lanes) {
            buf.method_10814(lane.fromId());
            buf.method_10814(lane.toId());
            buf.method_10804(lane.points().size());
            for (Point point : lane.points()) {
                buf.method_10804(point.x());
                buf.method_10804(point.z());
            }
        }
        buf.method_10804(value.warParties.size());
        for (WarPartyEntry warPartyEntry : value.warParties) {
            buf.method_10814(warPartyEntry.id());
            buf.method_10814(warPartyEntry.lordName());
            buf.method_10814(warPartyEntry.attackerKingdomId());
            buf.method_10814(warPartyEntry.attackerHouseId());
            buf.method_10814(warPartyEntry.targetCastleId());
            buf.method_10814(warPartyEntry.targetCastleName());
            buf.method_10814(warPartyEntry.modeId());
            buf.method_10804(warPartyEntry.startChunkX());
            buf.method_10804(warPartyEntry.startChunkZ());
            buf.method_10804(warPartyEntry.targetChunkX());
            buf.method_10804(warPartyEntry.targetChunkZ());
            buf.method_52974(warPartyEntry.departureTick());
            buf.method_52974(warPartyEntry.arrivalTick());
            buf.method_52964(warPartyEntry.arrived());
        }
    }, buf -> {
        long syncedWorldTick = buf.readLong();
        long syncedProgressTick = buf.readLong();
        int dynamicKingdomSize = buf.method_10816();
        ArrayList<DynamicKingdomEntry> dynamicKingdoms = new ArrayList<DynamicKingdomEntry>(dynamicKingdomSize);
        for (int i = 0; i < dynamicKingdomSize; ++i) {
            dynamicKingdoms.add(new DynamicKingdomEntry(buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772()));
        }
        int castleSize = buf.method_10816();
        ArrayList<Entry> entries = new ArrayList<Entry>(castleSize);
        for (int i = 0; i < castleSize; ++i) {
            entries.add(new Entry(buf.method_19772(), buf.method_10816(), buf.method_10816(), buf.method_10816(), buf.method_10816(), buf.readBoolean(), buf.readBoolean(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), CastleMapPayload.readSubtypeIds(buf), buf.readBoolean(), buf.readBoolean()));
        }
        int laneSize = buf.method_10816();
        ArrayList<Lane> lanes = new ArrayList<Lane>(laneSize);
        for (int laneIndex = 0; laneIndex < laneSize; ++laneIndex) {
            String fromId = buf.method_19772();
            String toId = buf.method_19772();
            int pointSize = buf.method_10816();
            ArrayList<Point> points = new ArrayList<Point>(pointSize);
            for (int pointIndex = 0; pointIndex < pointSize; ++pointIndex) {
                points.add(new Point(buf.method_10816(), buf.method_10816()));
            }
            lanes.add(new Lane(fromId, toId, List.copyOf(points)));
        }
        int warPartySize = buf.method_10816();
        ArrayList<WarPartyEntry> warParties = new ArrayList<WarPartyEntry>(warPartySize);
        for (int index = 0; index < warPartySize; ++index) {
            warParties.add(new WarPartyEntry(buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_10816(), buf.method_10816(), buf.method_10816(), buf.method_10816(), buf.readLong(), buf.readLong(), buf.readBoolean()));
        }
        return new CastleMapPayload(syncedWorldTick, syncedProgressTick, List.copyOf(dynamicKingdoms), List.copyOf(entries), List.copyOf(lanes), List.copyOf(warParties));
    });

    public CastleMapPayload {
        dynamicKingdoms = List.copyOf(dynamicKingdoms == null ? List.of() : dynamicKingdoms);
        castles = List.copyOf(castles == null ? List.of() : castles);
        lanes = List.copyOf(lanes == null ? List.of() : lanes);
        warParties = List.copyOf(warParties == null ? List.of() : warParties);
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    private static List<String> readSubtypeIds(class_9129 buf) {
        int size = buf.method_10816();
        ArrayList<String> subtypeIds = new ArrayList<String>(size);
        for (int index = 0; index < size; ++index) {
            subtypeIds.add(buf.method_19772());
        }
        return List.copyOf(subtypeIds);
    }

    public record DynamicKingdomEntry(String kingdomId, String displayName, String parentKingdomId, String houseId) {
    }

    public record Entry(String id, int worldX, int worldZ, int travelX, int travelZ, boolean stone, boolean capital, String tierId, String name, String lore, String kingdomId, String houseId, String originalKingdomId, String religionId, String kingdomLine, String religionLine, String lordName, List<String> subtypeIds, boolean unlocked, boolean tourneyActive) {
    }

    public record Point(int x, int z) {
    }

    public record Lane(String fromId, String toId, List<Point> points) {
    }

    public record WarPartyEntry(String id, String lordName, String attackerKingdomId, String attackerHouseId, String targetCastleId, String targetCastleName, String modeId, int startChunkX, int startChunkZ, int targetChunkX, int targetChunkZ, long departureTick, long arrivalTick, boolean arrived) {
    }
}

