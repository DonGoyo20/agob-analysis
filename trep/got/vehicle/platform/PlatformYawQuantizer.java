/*
 * Decompiled with CFR 0.152.
 */
package trep.got.vehicle.platform;

public final class PlatformYawQuantizer {
    private static final int STEPS = 32;
    private static final float STEP_DEGREES = 11.25f;

    private PlatformYawQuantizer() {
    }

    public static float quantize(float yaw) {
        return (float)Math.round(yaw / 11.25f) * 11.25f;
    }
}

