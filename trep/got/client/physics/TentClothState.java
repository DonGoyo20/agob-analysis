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
public final class TentClothState {
    private float bend;

    public float bend() {
        return this.bend;
    }

    public void setBend(float bend) {
        this.bend = bend;
    }
}

