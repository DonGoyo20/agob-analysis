/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 */
package trep.got.vehicle.platform;

import java.util.List;
import net.minecraft.class_238;
import net.minecraft.class_243;
import trep.got.entity.ShipEntity;
import trep.got.vehicle.platform.PlatformLocalBox;

public final class ShipImpactResolver {
    private static final double IMPACT_EPSILON = 0.08;
    private static final double MIN_IMPACT_SPEED = 0.045;
    private static final double SIDE_TRANSFER = 0.35;

    private ShipImpactResolver() {
    }

    public static void resolve(ShipEntity ship) {
        if (ship.getImpactCooldownTicks() > 0) {
            ship.setImpactCooldownTicks(ship.getImpactCooldownTicks() - 1);
            return;
        }
        List nearby = ship.method_37908().method_8390(ShipEntity.class, ship.agob$getPlatformBroadPhaseBox().method_1014(1.5), other -> other.method_5805() && other != ship && other.method_5628() > ship.method_5628() && other.getImpactCooldownTicks() <= 0);
        for (ShipEntity other2 : nearby) {
            if (!ShipImpactResolver.modelsTouch(ship, other2) || !ShipImpactResolver.applyImpact(ship, other2)) continue;
            ship.setImpactCooldownTicks(6);
            other2.setImpactCooldownTicks(6);
        }
    }

    private static boolean modelsTouch(ShipEntity a, ShipEntity b) {
        if (!a.agob$getPlatformBroadPhaseBox().method_1014(0.08).method_994(b.agob$getPlatformBroadPhaseBox().method_1014(0.08))) {
            return false;
        }
        for (PlatformLocalBox aBox : a.agob$getPlatformHull().boxes()) {
            class_238 aWorld = a.agob$getPlatformTransform().localBoxToWorldAabb(aBox).method_1014(0.08);
            if (!aWorld.method_994(b.agob$getPlatformBroadPhaseBox())) continue;
            for (PlatformLocalBox bBox : b.agob$getPlatformHull().boxes()) {
                class_238 bWorld = b.agob$getPlatformTransform().localBoxToWorldAabb(bBox);
                if (!aWorld.method_994(bWorld)) continue;
                return true;
            }
        }
        return false;
    }

    private static boolean applyImpact(ShipEntity a, ShipEntity b) {
        double aggressorNormal;
        class_243 transferNormal;
        class_243 receiverVelocity;
        class_243 aggressorVelocity;
        ShipEntity receiver;
        ShipEntity aggressor;
        double bNormal;
        class_243 between = b.method_19538().method_1020(a.method_19538());
        class_243 normal = new class_243(between.field_1352, 0.0, between.field_1350);
        if (normal.method_1027() < 1.0E-6) {
            normal = class_243.method_1030((float)0.0f, (float)a.method_36454());
            normal = new class_243(normal.field_1352, 0.0, normal.field_1350);
        }
        normal = normal.method_1029();
        class_243 aVelocity = ShipImpactResolver.horizontal(a.method_18798());
        class_243 bVelocity = ShipImpactResolver.horizontal(b.method_18798());
        double aNormal = aVelocity.method_1026(normal);
        double closingSpeed = aNormal - (bNormal = bVelocity.method_1026(normal));
        if (Math.abs(closingSpeed) < 0.045) {
            return false;
        }
        if (closingSpeed > 0.0) {
            aggressor = a;
            receiver = b;
            aggressorVelocity = aVelocity;
            receiverVelocity = bVelocity;
            transferNormal = normal;
            aggressorNormal = aNormal;
        } else {
            aggressor = b;
            receiver = a;
            aggressorVelocity = bVelocity;
            receiverVelocity = aVelocity;
            transferNormal = normal.method_22882();
            aggressorNormal = -bNormal;
        }
        double transferredSpeed = Math.max(aggressorVelocity.method_1033(), Math.abs(aggressorNormal));
        class_243 aggressorTangential = aggressorVelocity.method_1020(transferNormal.method_1021(aggressorVelocity.method_1026(transferNormal)));
        class_243 receiverTangential = receiverVelocity.method_1020(transferNormal.method_1021(receiverVelocity.method_1026(transferNormal)));
        class_243 sideNormal = new class_243(-transferNormal.field_1350, 0.0, transferNormal.field_1352);
        double sideSign = Math.signum(aggressor.method_19538().method_1020(receiver.method_19538()).method_1026(sideNormal));
        class_243 sideImpulse = sideNormal.method_1021(sideSign * transferredSpeed * 0.35);
        aggressor.method_18800(aggressorTangential.method_1021((double)0.15).field_1352, aggressor.method_18798().field_1351, aggressorTangential.method_1021((double)0.15).field_1350);
        class_243 receiverNext = receiverTangential.method_1021(0.6).method_1019(transferNormal.method_1021(transferredSpeed)).method_1019(sideImpulse);
        receiver.method_18800(receiverNext.field_1352, receiver.method_18798().field_1351, receiverNext.field_1350);
        return true;
    }

    private static class_243 horizontal(class_243 velocity) {
        return new class_243(velocity.field_1352, 0.0, velocity.field_1350);
    }
}

