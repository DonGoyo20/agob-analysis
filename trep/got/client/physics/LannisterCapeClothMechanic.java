/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package trep.got.client.physics;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import trep.got.client.physics.CapeClothState;
import trep.got.client.physics.ClientPhysicsMechanic;
import trep.got.client.physics.ClientPhysicsRegistry;

@Environment(value=EnvType.CLIENT)
public final class LannisterCapeClothMechanic
implements ClientPhysicsMechanic<class_1309> {
    private static final float MAX_BEND = 0.7f;
    private static final float BEND_PER_SPEED = 10.0f;
    private static final float ACCELERATION_RESPONSE = 0.08f;
    private static final float DECAY_RESPONSE = 0.24f;

    @Override
    public void tick(class_1309 entity) {
        CapeClothState state = ClientPhysicsRegistry.getCapeState(entity);
        class_243 velocity = entity.method_19538().method_1023(entity.field_6014, entity.field_6036, entity.field_5969);
        class_243 horizontalVelocity = new class_243(velocity.field_1352, 0.0, velocity.field_1350);
        class_243 facing = class_243.method_1030((float)0.0f, (float)entity.method_43078()).method_1029();
        float forwardSpeed = (float)horizontalVelocity.method_1026(facing);
        float targetBend = class_3532.method_15363((float)(Math.max(0.0f, forwardSpeed) * 10.0f), (float)0.0f, (float)0.7f);
        float response = targetBend > state.bend() ? 0.08f : 0.24f;
        state.setBend(class_3532.method_16439((float)response, (float)state.bend(), (float)targetBend));
        state.setTargetBend(targetBend);
        state.setMovementSpeed(Math.max(0.0f, forwardSpeed));
        state.setLastAge(entity.field_6012);
    }
}

