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

@Environment(value=EnvType.CLIENT)
public final class UiEasing {
    private UiEasing() {
    }

    public static float easeInOutCubic(float value) {
        float clamped = Math.max(0.0f, Math.min(1.0f, value));
        if (clamped < 0.5f) {
            return 4.0f * clamped * clamped * clamped;
        }
        return 1.0f - (float)Math.pow(-2.0f * clamped + 2.0f, 3.0) / 2.0f;
    }

    public static float lerp(float delta, float start, float end) {
        return start + (end - start) * delta;
    }
}

