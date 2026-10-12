/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_3532
 */
package trep.got.client.physics;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_3532;
import trep.got.client.physics.ClientPhysicsMechanic;
import trep.got.client.physics.ClientPhysicsRegistry;
import trep.got.client.physics.TentClothState;
import trep.got.entity.TentEntity;

@Environment(value=EnvType.CLIENT)
public final class TentWallClothMechanic
implements ClientPhysicsMechanic<TentEntity> {
    private static final float TARGET_BEND = 0.95f;
    private static final float RESPONSE = 0.08f;

    @Override
    public void tick(TentEntity entity) {
        TentClothState state = ClientPhysicsRegistry.getTentState(entity);
        state.setBend(class_3532.method_16439((float)0.08f, (float)state.bend(), (float)0.95f));
    }
}

