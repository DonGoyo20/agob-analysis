/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.vehicle.platform;

import net.minecraft.class_243;
import org.jetbrains.annotations.Nullable;

public final class PlayerPlatformState {
    private static final int MAX_AIRBORNE_GRACE_TICKS = 8;
    private int platformEntityId = -1;
    private class_243 localPos = class_243.field_1353;
    private class_243 localVelocity = class_243.field_1353;
    private boolean grounded;
    private int supportTicks;
    private int airborneGraceTicks;
    private float lastPlatformYaw;
    @Nullable
    private class_243 lastWorldPos;

    public int platformEntityId() {
        return this.platformEntityId;
    }

    public void clear() {
        this.platformEntityId = -1;
        this.localPos = class_243.field_1353;
        this.localVelocity = class_243.field_1353;
        this.grounded = false;
        this.supportTicks = 0;
        this.airborneGraceTicks = 0;
        this.lastWorldPos = null;
    }

    public void set(int platformEntityId, class_243 localPos, class_243 localVelocity, boolean grounded, float lastPlatformYaw, class_243 lastWorldPos) {
        this.platformEntityId = platformEntityId;
        this.localPos = localPos;
        this.localVelocity = localVelocity;
        this.grounded = grounded;
        this.supportTicks = Math.min(this.supportTicks + 1, 20);
        this.airborneGraceTicks = grounded ? 8 : Math.max(this.airborneGraceTicks - 1, 0);
        this.lastPlatformYaw = lastPlatformYaw;
        this.lastWorldPos = lastWorldPos;
    }

    public void setAirborne(class_243 localPos, class_243 localVelocity, class_243 lastWorldPos) {
        if (this.platformEntityId < 0) {
            return;
        }
        this.localPos = localPos;
        this.localVelocity = localVelocity;
        this.grounded = false;
        this.lastWorldPos = lastWorldPos;
        if (this.airborneGraceTicks > 0) {
            --this.airborneGraceTicks;
        }
    }

    public void decay() {
        if (this.supportTicks > 0) {
            --this.supportTicks;
        }
        if (this.airborneGraceTicks > 0) {
            --this.airborneGraceTicks;
        }
        if (this.supportTicks == 0 && this.airborneGraceTicks == 0) {
            this.platformEntityId = -1;
            this.grounded = false;
        }
    }

    public class_243 localPos() {
        return this.localPos;
    }

    public class_243 localVelocity() {
        return this.localVelocity;
    }

    public boolean grounded() {
        return this.grounded;
    }

    public int supportTicks() {
        return this.supportTicks;
    }

    public int airborneGraceTicks() {
        return this.airborneGraceTicks;
    }

    public boolean isAttached() {
        return this.platformEntityId >= 0 && (this.grounded || this.airborneGraceTicks > 0 || this.supportTicks > 0);
    }

    public float lastPlatformYaw() {
        return this.lastPlatformYaw;
    }

    @Nullable
    public class_243 lastWorldPos() {
        return this.lastWorldPos;
    }
}

