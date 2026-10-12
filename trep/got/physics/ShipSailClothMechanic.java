/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3532
 */
package trep.got.physics;

import net.minecraft.class_3532;
import trep.got.entity.ShipEntity;
import trep.got.physics.PhysicsMechanic;

public final class ShipSailClothMechanic
implements PhysicsMechanic<ShipEntity> {
    private static final float MIN_PUSH_BEND_SPEED = 0.12f;
    private static final float SPEED_EPSILON = 0.001f;
    private static final float BEND_EPSILON = 5.0E-4f;
    private static final float MAX_BEND = 0.9f;
    private static final float ACCELERATION_RESPONSE = 0.045f;
    private static final float DECAY_RESPONSE = 0.025f;
    private static final float BEND_PER_SPEED = 2.4f;

    @Override
    public void tick(ShipEntity ship) {
        float response;
        float nextBend;
        float velocitySpeed = (float)ship.method_18798().method_37267();
        float sailDriveSpeed = Math.max(velocitySpeed, Math.max(ship.getCruiseSpeed(), ship.getPersistedCruiseSpeed()));
        if (ship.isDrivingForward()) {
            sailDriveSpeed = Math.max(sailDriveSpeed, 0.12f);
        }
        if (sailDriveSpeed < 0.001f) {
            sailDriveSpeed = 0.0f;
        }
        float targetBend = class_3532.method_15363((float)(sailDriveSpeed * 2.4f), (float)0.0f, (float)0.9f);
        float currentBend = ship.getSailBend();
        if (targetBend < 5.0E-4f) {
            targetBend = 0.0f;
        }
        if (Math.abs(currentBend) < 5.0E-4f) {
            currentBend = 0.0f;
        }
        if (Math.abs(nextBend = class_3532.method_16439((float)(response = targetBend > currentBend ? 0.045f : 0.025f), (float)currentBend, (float)targetBend)) < 5.0E-4f) {
            nextBend = 0.0f;
        }
        ship.setSailBend(nextBend);
        ship.setSailPhysicsDebug(targetBend, sailDriveSpeed, response);
    }
}

