/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 */
package trep.got.vehicle.collision;

import java.util.List;
import net.minecraft.class_238;
import net.minecraft.class_243;
import trep.got.entity.ShipEntity;
import trep.got.vehicle.collision.VesselCollisionContact;
import trep.got.vehicle.platform.PlatformLocalBox;
import trep.got.vehicle.platform.PlatformTransform;

public final class VesselCollisionResolver {
    private static final double CONTACT_EPSILON = 0.08;
    private static final double MIN_RELATIVE_SPEED = 0.02;
    private static final double RESTITUTION = 0.18;
    private static final double FRICTION = 0.24;
    private static final double SEPARATION_SLOP = 0.05;
    private static final double MAX_SEPARATION = 1.4;
    private static final int IMPACT_COOLDOWN_TICKS = 4;

    private VesselCollisionResolver() {
    }

    public static void resolve(ShipEntity ship) {
        if (ship.getImpactCooldownTicks() > 0) {
            ship.setImpactCooldownTicks(ship.getImpactCooldownTicks() - 1);
            return;
        }
        List nearby = ship.method_37908().method_8390(ShipEntity.class, ship.agob$getPlatformBroadPhaseBox().method_1014(1.5), other -> other.method_5805() && other != ship && other.method_5628() > ship.method_5628() && other.getImpactCooldownTicks() <= 0);
        for (ShipEntity other2 : nearby) {
            VesselCollisionContact contact = VesselCollisionResolver.findContact(ship, other2);
            if (contact == null || !VesselCollisionResolver.applyCollision(ship, other2, contact)) continue;
            ship.setImpactCooldownTicks(4);
            other2.setImpactCooldownTicks(4);
        }
    }

    private static VesselCollisionContact findContact(ShipEntity a, ShipEntity b) {
        class_238 aBroadPhase = a.agob$getPlatformBroadPhaseBox();
        class_238 bBroadPhase = b.agob$getPlatformBroadPhaseBox();
        if (!aBroadPhase.method_1014(0.08).method_994(bBroadPhase.method_1014(0.08))) {
            return null;
        }
        PlatformTransform aTransform = a.agob$getPlatformTransform();
        PlatformTransform bTransform = b.agob$getPlatformTransform();
        List<PlatformLocalBox> aLocalBoxes = a.agob$getPlatformHull().boxes();
        List<PlatformLocalBox> bLocalBoxes = b.agob$getPlatformHull().boxes();
        class_238[] bWorldBoxes = new class_238[bLocalBoxes.size()];
        for (int index = 0; index < bLocalBoxes.size(); ++index) {
            bWorldBoxes[index] = bTransform.localBoxToWorldAabb(bLocalBoxes.get(index));
        }
        class_243 centerDelta = b.method_19538().method_1020(a.method_19538());
        class_243 contactNormal = new class_243(centerDelta.field_1352, 0.0, centerDelta.field_1350);
        VesselCollisionContact best = null;
        for (PlatformLocalBox aBox : aLocalBoxes) {
            class_238 aWorld = aTransform.localBoxToWorldAabb(aBox).method_1014(0.08);
            if (!aWorld.method_994(bBroadPhase)) continue;
            for (class_238 bWorld : bWorldBoxes) {
                VesselCollisionContact candidate;
                if (!aWorld.method_994(bWorld) || (candidate = VesselCollisionResolver.buildContact(contactNormal, aWorld, bWorld)) == null || best != null && !(candidate.penetration() > best.penetration())) continue;
                best = candidate;
            }
        }
        return best;
    }

    private static VesselCollisionContact buildContact(class_243 contactNormal, class_238 aWorld, class_238 bWorld) {
        double overlapX = Math.min(aWorld.field_1320, bWorld.field_1320) - Math.max(aWorld.field_1323, bWorld.field_1323);
        double overlapZ = Math.min(aWorld.field_1324, bWorld.field_1324) - Math.max(aWorld.field_1321, bWorld.field_1321);
        if (overlapX <= 0.0 || overlapZ <= 0.0) {
            return null;
        }
        class_243 normal = contactNormal;
        if (normal.method_1027() < 1.0E-6) {
            if (overlapX <= overlapZ) {
                sign = contactNormal.field_1352 >= 0.0 ? 1.0 : -1.0;
                normal = new class_243(sign, 0.0, 0.0);
            } else {
                sign = contactNormal.field_1350 >= 0.0 ? 1.0 : -1.0;
                normal = new class_243(0.0, 0.0, sign);
            }
        }
        double penetration = Math.min(overlapX, overlapZ);
        class_243 point = new class_243((Math.max(aWorld.field_1323, bWorld.field_1323) + Math.min(aWorld.field_1320, bWorld.field_1320)) * 0.5, (Math.max(aWorld.field_1322, bWorld.field_1322) + Math.min(aWorld.field_1325, bWorld.field_1325)) * 0.5, (Math.max(aWorld.field_1321, bWorld.field_1321) + Math.min(aWorld.field_1324, bWorld.field_1324)) * 0.5);
        return new VesselCollisionContact(point, normal, penetration);
    }

    private static boolean applyCollision(ShipEntity a, ShipEntity b, VesselCollisionContact contact) {
        double invMassB;
        class_243 normal = VesselCollisionResolver.horizontal(contact.normal());
        if (normal.method_1027() < 1.0E-6) {
            return false;
        }
        normal = normal.method_1029();
        double massA = a.getCollisionMass();
        double massB = b.getCollisionMass();
        double invMassA = massA <= 0.0 ? 0.0 : 1.0 / massA;
        double d = invMassB = massB <= 0.0 ? 0.0 : 1.0 / massB;
        if (invMassA + invMassB <= 0.0) {
            return false;
        }
        class_243 velocityA = VesselCollisionResolver.horizontal(a.method_18798().method_1019(a.getCollisionDriftVelocity()));
        class_243 velocityB = VesselCollisionResolver.horizontal(b.method_18798().method_1019(b.getCollisionDriftVelocity()));
        class_243 relativeVelocity = velocityB.method_1020(velocityA);
        double relativeNormalSpeed = relativeVelocity.method_1026(normal);
        if (relativeNormalSpeed > -0.02) {
            relativeNormalSpeed = -0.02;
        }
        double normalImpulseMagnitude = -1.18 * relativeNormalSpeed / (invMassA + invMassB);
        class_243 normalImpulse = normal.method_1021(Math.max(0.0, normalImpulseMagnitude));
        class_243 tangentialVelocity = relativeVelocity.method_1020(normal.method_1021(relativeNormalSpeed));
        class_243 frictionImpulse = class_243.field_1353;
        if (tangentialVelocity.method_1027() > 1.0E-6 && normalImpulseMagnitude > 0.0) {
            class_243 tangent = tangentialVelocity.method_1029();
            double frictionMagnitude = Math.min(tangentialVelocity.method_1033() / (invMassA + invMassB), normalImpulseMagnitude * 0.24);
            frictionImpulse = tangent.method_1021(-frictionMagnitude);
        }
        class_243 totalImpulse = normalImpulse.method_1019(frictionImpulse);
        class_243 impulseA = totalImpulse.method_1021(-invMassA);
        class_243 impulseB = totalImpulse.method_1021(invMassB);
        a.applyCollisionImpulse(impulseA, VesselCollisionResolver.computeTurnImpulse(a, contact.point(), impulseA));
        b.applyCollisionImpulse(impulseB, VesselCollisionResolver.computeTurnImpulse(b, contact.point(), impulseB));
        double separationMagnitude = Math.min(1.4, Math.max(0.0, contact.penetration() - 0.05));
        if (separationMagnitude > 0.0) {
            class_243 separation = normal.method_1021(separationMagnitude / (invMassA + invMassB));
            a.displaceForCollision(separation.method_1021(-invMassA));
            b.displaceForCollision(separation.method_1021(invMassB));
        }
        return true;
    }

    private static float computeTurnImpulse(ShipEntity ship, class_243 contactPoint, class_243 impulse) {
        class_243 lever = contactPoint.method_1020(ship.method_19538());
        double torque = lever.field_1352 * impulse.field_1350 - lever.field_1350 * impulse.field_1352;
        double yawImpulse = torque / Math.max(1.0, ship.getCollisionMass() * 10.0);
        return (float)yawImpulse;
    }

    private static class_243 horizontal(class_243 velocity) {
        return new class_243(velocity.field_1352, 0.0, velocity.field_1350);
    }
}

