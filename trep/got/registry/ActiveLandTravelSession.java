/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_5321
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import java.util.List;
import java.util.UUID;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_5321;
import org.jetbrains.annotations.Nullable;
import trep.got.voyage.VoyageWorldState;

final class ActiveLandTravelSession {
    private final class_5321<class_1937> dimension;
    private final UUID playerUuid;
    private final String voyageId;
    private final String voyageTag;
    private final UUID cartUuid;
    private final UUID horseUuid;
    @Nullable
    private final UUID secondaryHorseUuid;
    private final UUID driverUuid;
    private final String sourceCastleId;
    private final String destinationCastleId;
    private final class_243 arrivalCenter;
    private final int arrivalRadiusBlocks;
    List<class_243> currentPathNodes = List.of();
    int currentPathIndex;
    long nextRepathTick;
    private long nextDebugLogTick;
    private long missingEntitySinceTick = Long.MIN_VALUE;

    ActiveLandTravelSession(class_5321<class_1937> dimension, UUID playerUuid, String voyageId, String voyageTag, UUID cartUuid, UUID horseUuid, @Nullable UUID secondaryHorseUuid, UUID driverUuid, String sourceCastleId, String destinationCastleId, class_243 arrivalCenter, int arrivalRadiusBlocks) {
        this.dimension = dimension;
        this.playerUuid = playerUuid;
        this.voyageId = voyageId;
        this.voyageTag = voyageTag;
        this.cartUuid = cartUuid;
        this.horseUuid = horseUuid;
        this.secondaryHorseUuid = secondaryHorseUuid;
        this.driverUuid = driverUuid;
        this.sourceCastleId = sourceCastleId;
        this.destinationCastleId = destinationCastleId;
        this.arrivalCenter = arrivalCenter;
        this.arrivalRadiusBlocks = arrivalRadiusBlocks;
    }

    static ActiveLandTravelSession fromPersisted(class_5321<class_1937> dimension, VoyageWorldState.PersistedLandTravelSession persisted) {
        return new ActiveLandTravelSession(dimension, persisted.playerUuid(), persisted.voyageId(), persisted.voyageTag(), persisted.cartUuid(), persisted.horseUuid(), persisted.secondaryHorseUuid(), persisted.driverUuid(), persisted.sourceCastleId(), persisted.destinationCastleId(), persisted.arrivalCenter(), persisted.arrivalRadiusBlocks());
    }

    VoyageWorldState.PersistedLandTravelSession toPersisted() {
        return new VoyageWorldState.PersistedLandTravelSession(this.playerUuid, this.voyageId, this.voyageTag, this.cartUuid, this.horseUuid, this.secondaryHorseUuid, this.driverUuid, this.sourceCastleId, this.destinationCastleId, this.arrivalCenter, this.arrivalRadiusBlocks);
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

    String voyageTag() {
        return this.voyageTag;
    }

    UUID cartUuid() {
        return this.cartUuid;
    }

    UUID horseUuid() {
        return this.horseUuid;
    }

    @Nullable
    UUID secondaryHorseUuid() {
        return this.secondaryHorseUuid;
    }

    UUID driverUuid() {
        return this.driverUuid;
    }

    class_243 arrivalCenter() {
        return this.arrivalCenter;
    }

    int arrivalRadiusBlocks() {
        return this.arrivalRadiusBlocks;
    }

    long nextDebugLogTick() {
        return this.nextDebugLogTick;
    }

    void scheduleNextDebugLogTick(long tick) {
        this.nextDebugLogTick = tick;
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

