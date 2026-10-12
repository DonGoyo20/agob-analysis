/*
 * Decompiled with CFR 0.152.
 */
package trep.got.physics;

import trep.got.entity.ShipEntity;
import trep.got.physics.ShipSailClothMechanic;

public final class PhysicsCoreRegistry {
    private static final ShipSailClothMechanic SHIP_SAIL_CLOTH = new ShipSailClothMechanic();

    private PhysicsCoreRegistry() {
    }

    public static void initialize() {
    }

    public static void tickShip(ShipEntity ship) {
        SHIP_SAIL_CLOTH.tick(ship);
    }
}

