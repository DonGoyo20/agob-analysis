/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.voyage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_18;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;
import org.jetbrains.annotations.Nullable;

public final class VoyageWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.voyages";
    private static final class_18.class_8645<VoyageWorldState> TYPE = new class_18.class_8645(VoyageWorldState::new, VoyageWorldState::fromNbt, class_4284.field_45077);
    private final Map<UUID, PersistedVoyageSession> sessions = new LinkedHashMap<UUID, PersistedVoyageSession>();
    private final Map<UUID, PersistedLandTravelSession> landSessions = new LinkedHashMap<UUID, PersistedLandTravelSession>();

    public static VoyageWorldState get(class_3218 world) {
        return (VoyageWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public Map<UUID, PersistedVoyageSession> sessions() {
        return Map.copyOf(this.sessions);
    }

    public Map<UUID, PersistedLandTravelSession> landSessions() {
        return Map.copyOf(this.landSessions);
    }

    public void put(PersistedVoyageSession session) {
        if (session.equals(this.sessions.get(session.playerUuid()))) {
            return;
        }
        this.sessions.put(session.playerUuid(), session);
        this.method_80();
    }

    public void putLand(PersistedLandTravelSession session) {
        if (session.equals(this.landSessions.get(session.playerUuid()))) {
            return;
        }
        this.landSessions.put(session.playerUuid(), session);
        this.method_80();
    }

    public void remove(UUID playerUuid) {
        if (this.sessions.remove(playerUuid) != null) {
            this.method_80();
        }
    }

    public void removeLand(UUID playerUuid) {
        if (this.landSessions.remove(playerUuid) != null) {
            this.method_80();
        }
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 sessionList = new class_2499();
        for (PersistedVoyageSession session : this.sessions.values()) {
            sessionList.add((Object)session.toNbt());
        }
        nbt.method_10566("sessions", (class_2520)sessionList);
        class_2499 landSessionList = new class_2499();
        for (PersistedLandTravelSession session : this.landSessions.values()) {
            landSessionList.add((Object)session.toNbt());
        }
        nbt.method_10566("land_sessions", (class_2520)landSessionList);
        return nbt;
    }

    private static VoyageWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        VoyageWorldState state = new VoyageWorldState();
        class_2499 sessionList = nbt.method_10554("sessions", 10);
        for (int index = 0; index < sessionList.size(); ++index) {
            PersistedVoyageSession session = PersistedVoyageSession.fromNbt(sessionList.method_10602(index));
            state.sessions.put(session.playerUuid(), session);
        }
        class_2499 landSessionList = nbt.method_10554("land_sessions", 10);
        for (int index = 0; index < landSessionList.size(); ++index) {
            PersistedLandTravelSession session = PersistedLandTravelSession.fromNbt(landSessionList.method_10602(index));
            state.landSessions.put(session.playerUuid(), session);
        }
        return state;
    }

    public record PersistedVoyageSession(UUID playerUuid, String voyageId, String voyageTag, UUID shipUuid, UUID captainUuid, String sourceCastleId, String destinationCastleId, List<class_243> waypoints, class_243 arrivalCenter, int arrivalRadiusBlocks, int waypointIndex, double currentSpeed, @Nullable class_243 avoidanceTarget, long avoidanceExpiresAtTick) {
        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_25927("player_uuid", this.playerUuid);
            nbt.method_10582("voyage_id", this.voyageId);
            nbt.method_10582("voyage_tag", this.voyageTag);
            nbt.method_25927("ship_uuid", this.shipUuid);
            nbt.method_25927("captain_uuid", this.captainUuid);
            nbt.method_10582("source_castle_id", this.sourceCastleId);
            nbt.method_10582("destination_castle_id", this.destinationCastleId);
            nbt.method_10566("waypoints", (class_2520)PersistedVoyageSession.writeVecList(this.waypoints));
            nbt.method_10566("arrival_center", (class_2520)PersistedVoyageSession.writeVec(this.arrivalCenter));
            nbt.method_10569("arrival_radius_blocks", this.arrivalRadiusBlocks);
            nbt.method_10569("waypoint_index", this.waypointIndex);
            nbt.method_10549("current_speed", this.currentSpeed);
            if (this.avoidanceTarget != null) {
                nbt.method_10566("avoidance_target", (class_2520)PersistedVoyageSession.writeVec(this.avoidanceTarget));
            }
            nbt.method_10544("avoidance_expires_at_tick", this.avoidanceExpiresAtTick);
            return nbt;
        }

        public static PersistedVoyageSession fromNbt(class_2487 nbt) {
            return new PersistedVoyageSession(nbt.method_25926("player_uuid"), nbt.method_10558("voyage_id"), nbt.method_10558("voyage_tag"), nbt.method_25926("ship_uuid"), nbt.method_25926("captain_uuid"), nbt.method_10558("source_castle_id"), nbt.method_10558("destination_castle_id"), PersistedVoyageSession.readVecList(nbt.method_10554("waypoints", 10)), PersistedVoyageSession.readVec(nbt.method_10562("arrival_center")), nbt.method_10550("arrival_radius_blocks"), nbt.method_10550("waypoint_index"), nbt.method_10545("current_speed") ? nbt.method_10574("current_speed") : 0.0, nbt.method_10573("avoidance_target", 10) ? PersistedVoyageSession.readVec(nbt.method_10562("avoidance_target")) : null, nbt.method_10537("avoidance_expires_at_tick"));
        }

        static class_2487 writeVec(class_243 vec) {
            class_2487 nbt = new class_2487();
            nbt.method_10549("x", vec.field_1352);
            nbt.method_10549("y", vec.field_1351);
            nbt.method_10549("z", vec.field_1350);
            return nbt;
        }

        static class_243 readVec(class_2487 nbt) {
            return new class_243(nbt.method_10574("x"), nbt.method_10574("y"), nbt.method_10574("z"));
        }

        private static class_2499 writeVecList(List<class_243> vectors) {
            class_2499 list = new class_2499();
            for (class_243 vec : vectors) {
                list.add((Object)PersistedVoyageSession.writeVec(vec));
            }
            return list;
        }

        private static List<class_243> readVecList(class_2499 list) {
            ArrayList<class_243> vectors = new ArrayList<class_243>(list.size());
            for (int index = 0; index < list.size(); ++index) {
                vectors.add(PersistedVoyageSession.readVec(list.method_10602(index)));
            }
            return List.copyOf(vectors);
        }
    }

    public record PersistedLandTravelSession(UUID playerUuid, String voyageId, String voyageTag, UUID cartUuid, UUID horseUuid, @Nullable UUID secondaryHorseUuid, UUID driverUuid, String sourceCastleId, String destinationCastleId, class_243 arrivalCenter, int arrivalRadiusBlocks) {
        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_25927("player_uuid", this.playerUuid);
            nbt.method_10582("voyage_id", this.voyageId);
            nbt.method_10582("voyage_tag", this.voyageTag);
            nbt.method_25927("cart_uuid", this.cartUuid);
            nbt.method_25927("horse_uuid", this.horseUuid);
            if (this.secondaryHorseUuid != null) {
                nbt.method_25927("secondary_horse_uuid", this.secondaryHorseUuid);
            }
            nbt.method_25927("driver_uuid", this.driverUuid);
            nbt.method_10582("source_castle_id", this.sourceCastleId);
            nbt.method_10582("destination_castle_id", this.destinationCastleId);
            nbt.method_10566("arrival_center", (class_2520)PersistedVoyageSession.writeVec(this.arrivalCenter));
            nbt.method_10569("arrival_radius_blocks", this.arrivalRadiusBlocks);
            return nbt;
        }

        public static PersistedLandTravelSession fromNbt(class_2487 nbt) {
            return new PersistedLandTravelSession(nbt.method_25926("player_uuid"), nbt.method_10558("voyage_id"), nbt.method_10558("voyage_tag"), nbt.method_25926("cart_uuid"), nbt.method_25926("horse_uuid"), nbt.method_25928("secondary_horse_uuid") ? nbt.method_25926("secondary_horse_uuid") : null, nbt.method_25926("driver_uuid"), nbt.method_10558("source_castle_id"), nbt.method_10558("destination_castle_id"), PersistedVoyageSession.readVec(nbt.method_10562("arrival_center")), nbt.method_10550("arrival_radius_blocks"));
        }
    }
}

