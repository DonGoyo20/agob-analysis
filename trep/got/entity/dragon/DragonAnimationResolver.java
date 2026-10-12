/*
 * Decompiled with CFR 0.152.
 */
package trep.got.entity.dragon;

import trep.got.entity.DragonModelType;
import trep.got.entity.dragon.DragonModelPartGroup;
import trep.got.entity.dragon.DragonModelParts;
import trep.got.entity.dragon.DragonOrigin;

public final class DragonAnimationResolver {
    private DragonAnimationResolver() {
    }

    public static DragonModelType resolveAnimationModelType(DragonModelType baseModelType, DragonModelParts parts) {
        DragonModelType resolvedBaseModelType;
        DragonModelType dragonModelType = resolvedBaseModelType = baseModelType == null ? DragonModelType.DEFAULT : baseModelType;
        if (parts != null && parts.sourceModelType(DragonModelPartGroup.WINGS, resolvedBaseModelType.origin()) == DragonModelType.LONGNECK) {
            return DragonModelType.LONGNECK;
        }
        if (parts != null && !parts.isDefault()) {
            return resolvedBaseModelType.origin() == DragonOrigin.VALYRIAN ? DragonModelType.VALYRIAN : DragonModelType.DEFAULT;
        }
        return resolvedBaseModelType;
    }
}

