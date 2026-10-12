/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 */
package trep.got.client.physics;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import trep.got.client.physics.ClientPhysicsMechanic;
import trep.got.client.physics.ClientPhysicsRegistry;
import trep.got.client.physics.ScorpionCrossbowStringState;
import trep.got.entity.ScorpionCrossbowEntity;

@Environment(value=EnvType.CLIENT)
public final class ScorpionCrossbowStringMechanic
implements ClientPhysicsMechanic<ScorpionCrossbowEntity> {
    @Override
    public void tick(ScorpionCrossbowEntity entity) {
        ScorpionCrossbowStringState state = ClientPhysicsRegistry.getScorpionCrossbowStringState(entity);
        state.update(entity.field_6012, entity.isReloading(), entity.isLoaded());
    }
}

