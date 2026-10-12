/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_5321
 */
package trep.got.registry;

import java.util.UUID;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_5321;

final class ActiveTitleChallenge {
    private final class_5321<class_1937> dimension;
    private final UUID playerUuid;
    private final String playerName;
    private final class_2338 returnPos;
    private final float returnYaw;
    private final float returnPitch;
    private final UUID lordUuid;
    private final String lordProfileId;
    private final String castleId;
    private final String kingdomId;
    private final String houseId;
    private final String lordName;
    private final String arenaId;
    private final long endsAtTick;

    ActiveTitleChallenge(class_5321<class_1937> dimension, UUID playerUuid, String playerName, class_2338 returnPos, float returnYaw, float returnPitch, UUID lordUuid, String lordProfileId, String castleId, String kingdomId, String houseId, String lordName, String arenaId, long endsAtTick) {
        this.dimension = dimension;
        this.playerUuid = playerUuid;
        this.playerName = playerName == null ? "Unknown Player" : playerName;
        this.returnPos = returnPos == null ? class_2338.field_10980 : returnPos.method_10062();
        this.returnYaw = returnYaw;
        this.returnPitch = returnPitch;
        this.lordUuid = lordUuid;
        this.lordProfileId = lordProfileId == null ? "" : lordProfileId;
        this.castleId = castleId == null ? "" : castleId;
        this.kingdomId = kingdomId == null ? "" : kingdomId;
        this.houseId = houseId == null ? "" : houseId;
        this.lordName = lordName == null ? "" : lordName;
        this.arenaId = arenaId == null ? "" : arenaId;
        this.endsAtTick = endsAtTick;
    }

    class_5321<class_1937> dimension() {
        return this.dimension;
    }

    UUID playerUuid() {
        return this.playerUuid;
    }

    class_2338 returnPos() {
        return this.returnPos;
    }

    float returnYaw() {
        return this.returnYaw;
    }

    float returnPitch() {
        return this.returnPitch;
    }

    UUID lordUuid() {
        return this.lordUuid;
    }

    String lordProfileId() {
        return this.lordProfileId;
    }

    String castleId() {
        return this.castleId;
    }

    String kingdomId() {
        return this.kingdomId;
    }

    String houseId() {
        return this.houseId;
    }

    String lordName() {
        return this.lordName;
    }

    String arenaId() {
        return this.arenaId;
    }

    long endsAtTick() {
        return this.endsAtTick;
    }
}

