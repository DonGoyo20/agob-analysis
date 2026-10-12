/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_3532
 */
package trep.got.client.physics;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_3532;

@Environment(value=EnvType.CLIENT)
public final class ScorpionCrossbowStringState {
    private static final float PULL_SPEED_PER_TICK = 0.05f;
    private float prevDrawProgress;
    private float drawProgress;
    private int lastEntityAge = -1;
    private boolean wasReloading;

    public void update(int entityAge, boolean reloading, boolean loaded) {
        if (entityAge == this.lastEntityAge) {
            return;
        }
        this.prevDrawProgress = this.drawProgress;
        int elapsedTicks = this.elapsedTicks(entityAge);
        if (reloading) {
            if (!this.wasReloading) {
                this.prevDrawProgress = 0.0f;
                this.drawProgress = 0.0f;
            }
            this.drawProgress = Math.min(1.0f, this.drawProgress + (float)elapsedTicks * 0.05f);
        } else {
            this.drawProgress = loaded ? 1.0f : 0.0f;
        }
        this.wasReloading = reloading;
    }

    public float drawProgress() {
        return this.drawProgress;
    }

    public float drawProgress(float tickDelta) {
        return class_3532.method_16439((float)tickDelta, (float)this.prevDrawProgress, (float)this.drawProgress);
    }

    private int elapsedTicks(int entityAge) {
        if (this.lastEntityAge < 0 || entityAge < this.lastEntityAge) {
            this.lastEntityAge = entityAge;
            return 0;
        }
        int elapsedTicks = Math.max(0, entityAge - this.lastEntityAge);
        this.lastEntityAge = entityAge;
        return elapsedTicks;
    }
}

