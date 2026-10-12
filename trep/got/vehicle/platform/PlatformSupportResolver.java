/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.vehicle.platform;

import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import org.jetbrains.annotations.Nullable;
import trep.got.vehicle.platform.PlatformEntity;
import trep.got.vehicle.platform.PlatformLocalBox;
import trep.got.vehicle.platform.PlatformSpatialIndex;
import trep.got.vehicle.platform.PlatformStateHolder;
import trep.got.vehicle.platform.PlatformTransform;
import trep.got.vehicle.platform.PlayerPlatformState;

public final class PlatformSupportResolver {
    private static final double SUPPORT_EPSILON = 0.08;
    private static final double SUPPORT_BOX_HEIGHT = 0.15;

    private PlatformSupportResolver() {
    }

    public static void update(class_1297 entity) {
        if (!(entity instanceof PlatformStateHolder)) {
            return;
        }
        PlatformStateHolder holder = (PlatformStateHolder)entity;
        PlayerPlatformState state = holder.agob$getPlatformState();
        SupportContact contact = PlatformSupportResolver.findSupport(entity);
        if (contact == null) {
            PlatformSupportResolver.updateAirborneState(entity, state);
            return;
        }
        class_243 localPos = contact.transform().worldToLocal(entity.method_19538());
        class_243 localVelocity = PlatformSupportResolver.worldVelocityToLocal(entity.method_18798(), contact.transform(), contact.platform().agob$getPreviousPlatformTransform());
        state.set(contact.entity().method_5628(), localPos, localVelocity, true, contact.transform().collisionYaw(), entity.method_19538());
    }

    private static void updateAirborneState(class_1297 entity, PlayerPlatformState state) {
        PlatformEntity platform;
        block6: {
            block5: {
                if (!state.isAttached()) {
                    state.decay();
                    return;
                }
                class_1297 platformEntity = entity.method_37908().method_8469(state.platformEntityId());
                if (!(platformEntity instanceof PlatformEntity)) break block5;
                platform = (PlatformEntity)platformEntity;
                if (platformEntity.method_5805()) break block6;
            }
            state.clear();
            return;
        }
        PlatformTransform transform = platform.agob$getPlatformTransform();
        class_243 localPos = transform.worldToLocal(entity.method_19538());
        class_243 localVelocity = PlatformSupportResolver.worldVelocityToLocal(entity.method_18798(), transform, platform.agob$getPreviousPlatformTransform());
        state.setAirborne(localPos, localVelocity, entity.method_19538());
        if (entity.method_24828()) {
            state.decay();
        }
    }

    @Nullable
    public static SupportContact findSupport(class_1297 entity) {
        class_1937 world = entity.method_37908();
        class_238 entityBox = entity.method_5829();
        class_238 feetBox = new class_238(entityBox.field_1323, entityBox.field_1322 - 0.08, entityBox.field_1321, entityBox.field_1320, entityBox.field_1322 + 0.15, entityBox.field_1324);
        List nearby = PlatformSpatialIndex.findCandidates(entity, feetBox.method_1014(1.0));
        if (nearby == null) {
            nearby = world.method_8333(entity, feetBox.method_1014(1.0), other -> {
                if (!(other instanceof PlatformEntity)) return false;
                PlatformEntity platform = (PlatformEntity)other;
                if (!other.method_5805()) return false;
                if (!platform.agob$getPlatformBroadPhaseBox().method_994(feetBox)) return false;
                return true;
            });
        }
        SupportContact best = null;
        double bestY = Double.NEGATIVE_INFINITY;
        for (class_1297 other2 : nearby) {
            PlatformEntity platform = (PlatformEntity)other2;
            if (!other2.method_5805() || !platform.agob$getPlatformBroadPhaseBox().method_994(feetBox)) continue;
            PlatformTransform transform = platform.agob$getPlatformTransform();
            for (PlatformLocalBox localBox : platform.agob$getPlatformHull().supportBoxes()) {
                class_238 worldBox = transform.localBoxToWorldAabb(localBox);
                if (!worldBox.method_994(feetBox) || !(worldBox.field_1325 > bestY)) continue;
                bestY = worldBox.field_1325;
                best = new SupportContact(other2, platform, transform, worldBox.field_1325);
            }
        }
        return best;
    }

    private static class_243 worldVelocityToLocal(class_243 worldVelocity, PlatformTransform current, PlatformTransform previous) {
        if (previous == null) {
            return worldVelocity;
        }
        class_243 platformVelocity = current.origin().method_1020(previous.origin());
        class_243 relative = worldVelocity.method_1020(platformVelocity);
        float radians = (float)Math.toRadians((double)current.collisionYaw() - 180.0);
        return relative.method_1024(radians);
    }

    public record SupportContact(class_1297 entity, PlatformEntity platform, PlatformTransform transform, double supportY) {
    }
}

