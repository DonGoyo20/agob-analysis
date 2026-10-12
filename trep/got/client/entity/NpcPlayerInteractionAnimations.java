/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1309
 *  net.minecraft.class_3532
 *  net.minecraft.class_591
 *  net.minecraft.class_630
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_3532;
import net.minecraft.class_591;
import net.minecraft.class_630;
import trep.got.entity.AbstractKingdomHumanoidEntity;

@Environment(value=EnvType.CLIENT)
final class NpcPlayerInteractionAnimations {
    private static final float TICKS_PER_SECOND = 20.0f;

    private NpcPlayerInteractionAnimations() {
    }

    static void apply(class_591<?> model, class_1309 entity, float tickDelta) {
        if (!(entity instanceof AbstractKingdomHumanoidEntity)) {
            return;
        }
        AbstractKingdomHumanoidEntity humanoid = (AbstractKingdomHumanoidEntity)entity;
        int animationId = humanoid.agob$getHumanoidAnimationId();
        if (animationId == 0) {
            return;
        }
        float elapsedSeconds = Math.max(0.0f, ((float)(entity.method_37908().method_8510() - humanoid.agob$getHumanoidAnimationStartTick()) + tickDelta) / 20.0f);
        switch (animationId) {
            case 1: {
                NpcPlayerInteractionAnimations.applyTalk(model, elapsedSeconds, false);
                break;
            }
            case 2: {
                NpcPlayerInteractionAnimations.applyTalk(model, elapsedSeconds, true);
                break;
            }
            case 3: {
                NpcPlayerInteractionAnimations.applyBow(model, elapsedSeconds);
                break;
            }
            case 4: {
                NpcPlayerInteractionAnimations.applyGreeting(model, elapsedSeconds);
                break;
            }
        }
        NpcPlayerInteractionAnimations.copyPlayerChildren(model);
    }

    private static void applyGreeting(class_591<?> model, float time) {
        model.field_3398.field_3674 += NpcPlayerInteractionAnimations.degrees(NpcPlayerInteractionAnimations.sample(time, 0.0f, 0.0f, 0.3333f, -25.0f, 0.7083f, 0.0f));
        NpcPlayerInteractionAnimations.setRotation(model.field_27433, 0.0f, 0.0f, NpcPlayerInteractionAnimations.sample(time, 0.0f, -147.5f, 0.125f, -110.0f, 0.375f, -147.5f, 0.5f, -110.0f, 0.7083f, -147.5f));
    }

    private static void applyTalk(class_591<?> model, float time, boolean mirrored) {
        class_630 leadingArm = mirrored ? model.field_27433 : model.field_3401;
        class_630 followingArm = mirrored ? model.field_3401 : model.field_27433;
        NpcPlayerInteractionAnimations.setRotation(leadingArm, NpcPlayerInteractionAnimations.sample(time, 0.0f, 0.0f, 0.2083f, -60.0f, 0.5f, -60.0f, 0.75f, 0.0f), 0.0f, NpcPlayerInteractionAnimations.sample(time, 0.0f, 0.0f, 0.2083f, 10.0f, 0.5f, 40.0f, 0.75f, 0.0f));
        NpcPlayerInteractionAnimations.setRotation(followingArm, NpcPlayerInteractionAnimations.sample(time, 0.0f, 0.0f, 0.375f, -70.0f, 0.75f, 0.0f), 0.0f, NpcPlayerInteractionAnimations.sample(time, 0.0f, 0.0f, 0.375f, -60.0f, 0.75f, 0.0f));
    }

    private static void applyBow(class_591<?> model, float time) {
        float waistPitch = NpcPlayerInteractionAnimations.degrees(NpcPlayerInteractionAnimations.sample(time, 0.0f, 0.0f, 0.3333f, 32.5f, 0.8333f, 0.0f));
        float headPitch = NpcPlayerInteractionAnimations.degrees(NpcPlayerInteractionAnimations.sample(time, 0.0f, 0.0f, 0.3333f, 22.5f, 0.8333f, 0.0f));
        model.field_3391.field_3654 += waistPitch;
        model.field_3398.field_3654 += waistPitch + headPitch;
        model.field_3401.field_3654 += waistPitch;
        model.field_27433.field_3654 += waistPitch;
        NpcPlayerInteractionAnimations.rotatePivotWithParent(model.field_3401, waistPitch);
        NpcPlayerInteractionAnimations.rotatePivotWithParent(model.field_27433, waistPitch);
    }

    private static void rotatePivotWithParent(class_630 part, float pitch) {
        if (Math.abs(pitch) < 1.0E-4f) {
            return;
        }
        float cos = class_3532.method_15362((float)pitch);
        float sin = class_3532.method_15374((float)pitch);
        float pivotY = part.field_3656;
        float pivotZ = part.field_3655;
        part.field_3656 = pivotY * cos - pivotZ * sin;
        part.field_3655 = pivotY * sin + pivotZ * cos;
    }

    private static void setRotation(class_630 part, float pitchDegrees, float yawDegrees, float rollDegrees) {
        part.field_3654 = NpcPlayerInteractionAnimations.degrees(pitchDegrees);
        part.field_3675 = NpcPlayerInteractionAnimations.degrees(yawDegrees);
        part.field_3674 = NpcPlayerInteractionAnimations.degrees(rollDegrees);
    }

    private static float degrees(float degrees) {
        return degrees * ((float)Math.PI / 180);
    }

    private static float sample(float time, float t0, float v0, float t1, float v1, float t2, float v2) {
        if (time <= t1) {
            return NpcPlayerInteractionAnimations.lerp(time, t0, v0, t1, v1);
        }
        return NpcPlayerInteractionAnimations.lerp(time, t1, v1, t2, v2);
    }

    private static float sample(float time, float t0, float v0, float t1, float v1, float t2, float v2, float t3, float v3) {
        if (time <= t1) {
            return NpcPlayerInteractionAnimations.lerp(time, t0, v0, t1, v1);
        }
        if (time <= t2) {
            return NpcPlayerInteractionAnimations.lerp(time, t1, v1, t2, v2);
        }
        return NpcPlayerInteractionAnimations.lerp(time, t2, v2, t3, v3);
    }

    private static float sample(float time, float t0, float v0, float t1, float v1, float t2, float v2, float t3, float v3, float t4, float v4) {
        if (time <= t1) {
            return NpcPlayerInteractionAnimations.lerp(time, t0, v0, t1, v1);
        }
        if (time <= t2) {
            return NpcPlayerInteractionAnimations.lerp(time, t1, v1, t2, v2);
        }
        if (time <= t3) {
            return NpcPlayerInteractionAnimations.lerp(time, t2, v2, t3, v3);
        }
        return NpcPlayerInteractionAnimations.lerp(time, t3, v3, t4, v4);
    }

    private static float lerp(float time, float startTime, float startValue, float endTime, float endValue) {
        if (endTime <= startTime) {
            return endValue;
        }
        float delta = class_3532.method_15363((float)((time - startTime) / (endTime - startTime)), (float)0.0f, (float)1.0f);
        return class_3532.method_16439((float)delta, (float)startValue, (float)endValue);
    }

    private static void copyPlayerChildren(class_591<?> model) {
        model.field_3394.method_17138(model.field_3398);
        model.field_3483.method_17138(model.field_3391);
        model.field_3486.method_17138(model.field_3401);
        model.field_3484.method_17138(model.field_27433);
        model.field_3479.method_17138(model.field_3392);
        model.field_3482.method_17138(model.field_3397);
    }
}

