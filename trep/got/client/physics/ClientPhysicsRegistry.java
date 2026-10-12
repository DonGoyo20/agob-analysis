/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1309
 */
package trep.got.client.physics;

import java.util.HashMap;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import trep.got.client.physics.CapeClothState;
import trep.got.client.physics.LannisterCapeClothMechanic;
import trep.got.client.physics.ScorpionCrossbowStringMechanic;
import trep.got.client.physics.ScorpionCrossbowStringState;
import trep.got.client.physics.TentClothState;
import trep.got.client.physics.TentWallClothMechanic;
import trep.got.entity.ScorpionCrossbowEntity;
import trep.got.entity.TentEntity;

@Environment(value=EnvType.CLIENT)
public final class ClientPhysicsRegistry {
    private static final LannisterCapeClothMechanic LANNISTER_CAPE = new LannisterCapeClothMechanic();
    private static final TentWallClothMechanic TENT_WALL = new TentWallClothMechanic();
    private static final ScorpionCrossbowStringMechanic SCORPION_CROSSBOW_STRING = new ScorpionCrossbowStringMechanic();
    private static final Map<Integer, CapeClothState> CAPE_STATES = new HashMap<Integer, CapeClothState>();
    private static final Map<Integer, TentClothState> TENT_STATES = new HashMap<Integer, TentClothState>();
    private static final Map<Integer, ScorpionCrossbowStringState> SCORPION_CROSSBOW_STRING_STATES = new HashMap<Integer, ScorpionCrossbowStringState>();

    private ClientPhysicsRegistry() {
    }

    public static void initialize() {
    }

    public static CapeClothState tickLannisterCape(class_1309 entity) {
        LANNISTER_CAPE.tick(entity);
        return ClientPhysicsRegistry.getCapeState(entity);
    }

    public static CapeClothState getCapeState(class_1309 entity) {
        return CAPE_STATES.computeIfAbsent(entity.method_5628(), ignored -> new CapeClothState());
    }

    public static TentClothState tickTentWalls(TentEntity entity) {
        TENT_WALL.tick(entity);
        return ClientPhysicsRegistry.getTentState(entity);
    }

    public static TentClothState getTentState(TentEntity entity) {
        return TENT_STATES.computeIfAbsent(entity.method_5628(), ignored -> new TentClothState());
    }

    public static ScorpionCrossbowStringState tickScorpionCrossbowString(ScorpionCrossbowEntity entity) {
        SCORPION_CROSSBOW_STRING.tick(entity);
        return ClientPhysicsRegistry.getScorpionCrossbowStringState(entity);
    }

    public static ScorpionCrossbowStringState getScorpionCrossbowStringState(ScorpionCrossbowEntity entity) {
        return SCORPION_CROSSBOW_STRING_STATES.computeIfAbsent(entity.method_5628(), ignored -> new ScorpionCrossbowStringState());
    }
}

