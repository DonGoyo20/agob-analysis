/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1313
 *  net.minecraft.class_243
 */
package trep.got.vehicle.platform;

import net.minecraft.class_1297;
import net.minecraft.class_1313;
import net.minecraft.class_243;
import trep.got.vehicle.platform.PlatformEntity;
import trep.got.vehicle.platform.PlatformStateHolder;
import trep.got.vehicle.platform.PlatformTransform;
import trep.got.vehicle.platform.PlayerPlatformState;

public final class PlatformMotionController {
    private static final double MAX_CORRECTION = 1.25;

    private PlatformMotionController() {
    }

    public static void tickEntity(class_1297 entity) {
        class_243 actualPos;
        PlatformEntity platform;
        PlayerPlatformState state;
        block10: {
            block9: {
                if (!(entity instanceof PlatformStateHolder)) {
                    return;
                }
                PlatformStateHolder holder = (PlatformStateHolder)entity;
                state = holder.agob$getPlatformState();
                if (!state.isAttached() || state.platformEntityId() < 0) {
                    return;
                }
                class_1297 platformEntity = entity.method_37908().method_8469(state.platformEntityId());
                if (!(platformEntity instanceof PlatformEntity)) break block9;
                platform = (PlatformEntity)platformEntity;
                if (platformEntity.method_5805()) break block10;
            }
            state.clear();
            return;
        }
        PlatformTransform current = platform.agob$getPlatformTransform();
        PlatformTransform previous = platform.agob$getPreviousPlatformTransform();
        if (previous == null) {
            return;
        }
        class_243 expectedPos = current.localToWorld(state.localPos());
        class_243 correction = expectedPos.method_1020(actualPos = entity.method_19538());
        if (correction.method_1027() > 1.5625) {
            entity.method_5808(expectedPos.field_1352, expectedPos.field_1351, expectedPos.field_1350, entity.method_36454(), entity.method_36455());
            return;
        }
        class_243 horizontalCorrection = new class_243(correction.field_1352, 0.0, correction.field_1350);
        if (horizontalCorrection.method_1027() > 1.0E-8) {
            entity.method_5784(class_1313.field_6308, horizontalCorrection);
        }
        if (state.grounded() && Math.abs(correction.field_1351) > 1.0E-4 && entity.method_18798().field_1351 <= 0.0) {
            entity.method_5814(entity.method_23317(), entity.method_23318() + correction.field_1351, entity.method_23321());
        }
    }
}

