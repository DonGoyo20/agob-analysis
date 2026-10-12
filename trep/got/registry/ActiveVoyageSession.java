/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_5321
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import java.util.List;
import java.util.UUID;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_5321;
import org.jetbrains.annotations.Nullable;
import trep.got.voyage.VoyageWorldState;

final class ActiveVoyageSession {
    private final class_5321<class_1937> dimension;
    private final UUID playerUuid;
    private final String voyageId;
    private final String voyageTag;
    private final UUID shipUuid;
    private final UUID captainUuid;
    private final String sourceCastleId;
    private final String destinationCastleId;
    private final List<class_243> waypoints;
    private final class_243 arrivalCenter;
    private final int arrivalRadiusBlocks;
    private int waypointIndex;
    private double currentSpeed;
    @Nullable
    private class_243 avoidanceTarget;
    private long avoidanceExpiresAtTick;
    private int materialRevision;
    private int checkpointRevision = -1;
    private long lastCheckpointTick = Long.MIN_VALUE;
    @Nullable
    private class_243 lastCheckpointPos;
    private long missingEntitySinceTick = Long.MIN_VALUE;

    ActiveVoyageSession(class_5321<class_1937> dimension, UUID playerUuid, String voyageId, String voyageTag, UUID shipUuid, UUID captainUuid, String sourceCastleId, String destinationCastleId, List<class_243> waypoints, class_243 arrivalCenter, int arrivalRadiusBlocks) {
        this.dimension = dimension;
        this.playerUuid = playerUuid;
        this.voyageId = voyageId;
        this.voyageTag = voyageTag;
        this.shipUuid = shipUuid;
        this.captainUuid = captainUuid;
        this.sourceCastleId = sourceCastleId;
        this.destinationCastleId = destinationCastleId;
        this.waypoints = List.copyOf(waypoints);
        this.arrivalCenter = arrivalCenter;
        this.arrivalRadiusBlocks = arrivalRadiusBlocks;
        this.waypointIndex = Math.min(1, Math.max(0, this.waypoints.size() - 1));
    }

    static ActiveVoyageSession fromPersisted(class_5321<class_1937> dimension, VoyageWorldState.PersistedVoyageSession persisted) {
        ActiveVoyageSession session = new ActiveVoyageSession(dimension, persisted.playerUuid(), persisted.voyageId(), persisted.voyageTag(), persisted.shipUuid(), persisted.captainUuid(), persisted.sourceCastleId(), persisted.destinationCastleId(), persisted.waypoints(), persisted.arrivalCenter(), persisted.arrivalRadiusBlocks());
        session.waypointIndex = class_3532.method_15340((int)persisted.waypointIndex(), (int)0, (int)Math.max(0, session.waypoints.size() - 1));
        session.currentSpeed = persisted.currentSpeed();
        session.avoidanceTarget = persisted.avoidanceTarget();
        session.avoidanceExpiresAtTick = persisted.avoidanceExpiresAtTick();
        return session;
    }

    VoyageWorldState.PersistedVoyageSession toPersisted() {
        return new VoyageWorldState.PersistedVoyageSession(this.playerUuid, this.voyageId, this.voyageTag, this.shipUuid, this.captainUuid, this.sourceCastleId, this.destinationCastleId, this.waypoints, this.arrivalCenter, this.arrivalRadiusBlocks, this.waypointIndex, this.currentSpeed, this.avoidanceTarget, this.avoidanceExpiresAtTick);
    }

    void alignToSpawn(class_243 spawnPos) {
        if (this.waypoints.isEmpty()) {
            this.waypointIndex = 0;
            return;
        }
        int closestIndex = 0;
        double closestDistance = Double.MAX_VALUE;
        for (int index = 0; index < this.waypoints.size(); ++index) {
            class_243 waypoint = this.waypoints.get(index);
            double distance = waypoint.method_1025(spawnPos);
            if (!(distance < closestDistance)) continue;
            closestDistance = distance;
            closestIndex = index;
        }
        this.waypointIndex = Math.min(closestIndex + 1, this.waypoints.size() - 1);
    }

    class_5321<class_1937> dimension() {
        return this.dimension;
    }

    UUID playerUuid() {
        return this.playerUuid;
    }

    String voyageId() {
        return this.voyageId;
    }

    String sourceCastleId() {
        return this.sourceCastleId;
    }

    String destinationCastleId() {
        return this.destinationCastleId;
    }

    UUID shipUuid() {
        return this.shipUuid;
    }

    UUID captainUuid() {
        return this.captainUuid;
    }

    class_243 arrivalCenter() {
        return this.arrivalCenter;
    }

    int arrivalRadiusBlocks() {
        return this.arrivalRadiusBlocks;
    }

    @Nullable
    class_243 currentTarget() {
        return this.waypointIndex >= 0 && this.waypointIndex < this.waypoints.size() ? this.waypoints.get(this.waypointIndex) : null;
    }

    boolean advanceToNextWaypoint() {
        ++this.waypointIndex;
        ++this.materialRevision;
        return this.waypointIndex >= this.waypoints.size();
    }

    double increaseSpeed(double amount) {
        this.currentSpeed = Math.min(0.55, this.currentSpeed + amount);
        return this.currentSpeed;
    }

    @Nullable
    class_243 currentSteeringTarget(class_243 fallbackTarget) {
        return this.avoidanceTarget != null ? this.avoidanceTarget : fallbackTarget;
    }

    boolean hasAvoidanceTarget() {
        return this.avoidanceTarget != null;
    }

    void setAvoidanceTarget(class_243 target, long expiresAtTick) {
        if (target.equals((Object)this.avoidanceTarget) && this.avoidanceExpiresAtTick == expiresAtTick) {
            return;
        }
        this.avoidanceTarget = target;
        this.avoidanceExpiresAtTick = expiresAtTick;
        ++this.materialRevision;
    }

    void clearAvoidanceTarget() {
        if (this.avoidanceTarget == null && this.avoidanceExpiresAtTick == 0L) {
            return;
        }
        this.avoidanceTarget = null;
        this.avoidanceExpiresAtTick = 0L;
        ++this.materialRevision;
    }

    void clearExpiredAvoidance(long currentTick) {
        if (this.avoidanceTarget != null && currentTick >= this.avoidanceExpiresAtTick) {
            this.clearAvoidanceTarget();
        }
    }

    boolean isNearAvoidanceTarget(class_243 shipPos, double distanceBlocks) {
        return this.avoidanceTarget != null && shipPos.method_1025(this.avoidanceTarget) <= distanceBlocks * distanceBlocks;
    }

    boolean shouldCheckpoint(long currentTick, @Nullable class_243 currentPos, long intervalTicks, double distanceBlocks) {
        if (this.materialRevision != this.checkpointRevision || this.lastCheckpointTick == Long.MIN_VALUE) {
            return true;
        }
        if (currentTick - this.lastCheckpointTick >= intervalTicks) {
            return true;
        }
        return currentPos != null && this.lastCheckpointPos != null && currentPos.method_1025(this.lastCheckpointPos) >= distanceBlocks * distanceBlocks;
    }

    void markCheckpoint(long currentTick, @Nullable class_243 currentPos) {
        this.checkpointRevision = this.materialRevision;
        this.lastCheckpointTick = currentTick;
        this.lastCheckpointPos = currentPos;
    }

    boolean missingEntityGraceExpired(long currentTick, long graceTicks) {
        if (this.missingEntitySinceTick == Long.MIN_VALUE) {
            this.missingEntitySinceTick = currentTick;
            return false;
        }
        return currentTick - this.missingEntitySinceTick >= graceTicks;
    }

    void clearMissingEntityGrace() {
        this.missingEntitySinceTick = Long.MIN_VALUE;
    }
}

