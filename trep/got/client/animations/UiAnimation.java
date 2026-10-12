/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 */
package trep.got.client.animations;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import trep.got.client.animations.UiEasing;

@Environment(value=EnvType.CLIENT)
public final class UiAnimation {
    private final int durationTicks;
    private float progress;
    private int direction;

    public UiAnimation(int durationTicks) {
        this.durationTicks = Math.max(1, durationTicks);
    }

    public void playForward() {
        this.direction = 1;
    }

    public void playBackward() {
        this.direction = -1;
    }

    public void tick() {
        if (this.direction == 0) {
            return;
        }
        this.progress += (float)this.direction / (float)this.durationTicks;
        if (this.progress >= 1.0f) {
            this.progress = 1.0f;
            this.direction = 0;
        } else if (this.progress <= 0.0f) {
            this.progress = 0.0f;
            this.direction = 0;
        }
    }

    public float raw() {
        return this.progress;
    }

    public float eased() {
        return UiEasing.easeInOutCubic(this.progress);
    }

    public boolean isAtStart() {
        return this.progress <= 0.0f;
    }

    public boolean isAtEnd() {
        return this.progress >= 1.0f;
    }

    public boolean isRunning() {
        return this.direction != 0;
    }

    public boolean isPlayingBackward() {
        return this.direction < 0;
    }
}

