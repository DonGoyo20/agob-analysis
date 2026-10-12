/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_5321
 */
package trep.got.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_5321;
import trep.got.registry.GotCastles;

final class ActiveTourneyChallenge {
    private final class_5321<class_1937> dimension;
    private final UUID playerUuid;
    private final String tourneyId;
    private final String arenaId;
    private final class_2338 returnPos;
    private final float returnYaw;
    private final float returnPitch;
    private final GotCastles.TourneyDifficulty difficulty;
    private final List<UUID> opponentUuids;
    private final long endsAtTick;

    ActiveTourneyChallenge(class_5321<class_1937> dimension, UUID playerUuid, String tourneyId, String arenaId, class_2338 returnPos, float returnYaw, float returnPitch, GotCastles.TourneyDifficulty difficulty, List<UUID> opponentUuids, long endsAtTick) {
        this.dimension = dimension;
        this.playerUuid = playerUuid;
        this.tourneyId = tourneyId == null ? "" : tourneyId;
        this.arenaId = arenaId == null ? "" : arenaId;
        this.returnPos = returnPos == null ? class_2338.field_10980 : returnPos.method_10062();
        this.returnYaw = returnYaw;
        this.returnPitch = returnPitch;
        this.difficulty = difficulty == null ? GotCastles.TourneyDifficulty.EASY : difficulty;
        this.opponentUuids = new ArrayList<UUID>(opponentUuids == null ? List.of() : opponentUuids);
        this.endsAtTick = endsAtTick;
    }

    class_5321<class_1937> dimension() {
        return this.dimension;
    }

    UUID playerUuid() {
        return this.playerUuid;
    }

    String arenaId() {
        return this.arenaId;
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

    GotCastles.TourneyDifficulty difficulty() {
        return this.difficulty;
    }

    List<UUID> opponentUuids() {
        return this.opponentUuids;
    }

    long endsAtTick() {
        return this.endsAtTick;
    }
}

