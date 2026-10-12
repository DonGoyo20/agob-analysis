/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 */
package trep.got.client.physics;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value=EnvType.CLIENT)
public final class CapeClothState {
    private float bend;
    private float targetBend;
    private float movementSpeed;
    private int lastAge;

    public float bend() {
        return this.bend;
    }

    public void setBend(float bend) {
        this.bend = bend;
    }

    public float targetBend() {
        return this.targetBend;
    }

    public void setTargetBend(float targetBend) {
        this.targetBend = targetBend;
    }

    public float movementSpeed() {
        return this.movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int lastAge() {
        return this.lastAge;
    }

    public void setLastAge(int lastAge) {
        this.lastAge = lastAge;
    }
}

