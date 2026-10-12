/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_5321
 */
package trep.got.registry;

import net.minecraft.class_1937;
import net.minecraft.class_5321;

final class PendingNpcRespawn {
    private final class_5321<class_1937> dimension;
    private final String castleId;
    private final String profileId;
    private final long respawnAtTick;

    PendingNpcRespawn(class_5321<class_1937> dimension, String castleId, String profileId, long respawnAtTick) {
        this.dimension = dimension;
        this.castleId = castleId;
        this.profileId = profileId;
        this.respawnAtTick = respawnAtTick;
    }

    class_5321<class_1937> dimension() {
        return this.dimension;
    }

    String castleId() {
        return this.castleId;
    }

    String profileId() {
        return this.profileId;
    }

    long respawnAtTick() {
        return this.respawnAtTick;
    }
}

