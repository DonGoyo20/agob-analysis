/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_3218
 *  net.minecraft.class_5819
 */
package trep.got.entity.dragon;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_5819;
import trep.got.entity.DragonAge;
import trep.got.entity.DragonEntity;
import trep.got.entity.DragonModelType;
import trep.got.entity.dragon.DragonModelPartDefinition;
import trep.got.entity.dragon.DragonModelPartGroup;
import trep.got.entity.dragon.DragonModelPartRegistry;
import trep.got.entity.dragon.DragonModelParts;
import trep.got.entity.dragon.DragonOrigin;
import trep.got.registry.GotEntities;

public final class DragonHatchlingFactory {
    private DragonHatchlingFactory() {
    }

    public static DragonEntity create(class_3218 world, class_2338 cradlePos, UUID ownerUuid) {
        DragonEntity dragon = (DragonEntity)GotEntities.DRAGON.method_5883((class_1937)world);
        if (dragon == null) {
            return null;
        }
        class_5819 random = world.field_9229;
        dragon.method_5808((double)cradlePos.method_10263() + 0.5, (double)cradlePos.method_10264() + 1.0, (double)cradlePos.method_10260() + 0.5, random.method_43057() * 360.0f, 0.0f);
        dragon.setDragonAge(DragonAge.BABY);
        DragonOrigin origin = DragonOrigin.random(random);
        dragon.setOrigin(origin);
        dragon.setVariant(DragonEntity.randomSpawnVariant(random));
        dragon.setModelType(DragonHatchlingFactory.randomModelType(random, origin).id());
        dragon.setModelParts(DragonHatchlingFactory.randomModelParts(random, origin));
        dragon.setBoundPlayerUuid(ownerUuid);
        return dragon;
    }

    private static DragonModelType randomModelType(class_5819 random, DragonOrigin origin) {
        List<DragonModelType> spawnTypes = DragonModelType.spawnTypes(origin);
        return spawnTypes.get(random.method_43048(spawnTypes.size()));
    }

    private static DragonModelParts randomModelParts(class_5819 random, DragonOrigin origin) {
        EnumMap<DragonModelPartGroup, DragonModelPartDefinition> parts = new EnumMap<DragonModelPartGroup, DragonModelPartDefinition>(DragonModelPartGroup.class);
        for (DragonModelPartGroup group : DragonModelPartGroup.values()) {
            if (!group.isModular()) continue;
            List<DragonModelPartDefinition> definitions = List.copyOf(DragonModelPartRegistry.parts(group, origin));
            parts.put(group, definitions.get(random.method_43048(definitions.size())));
        }
        return DragonModelParts.of(parts);
    }
}

