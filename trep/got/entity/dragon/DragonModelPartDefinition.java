/*
 * Decompiled with CFR 0.152.
 */
package trep.got.entity.dragon;

import trep.got.entity.DragonModelType;
import trep.got.entity.dragon.DragonModelPartGroup;
import trep.got.entity.dragon.DragonOrigin;

public record DragonModelPartDefinition(String id, DragonModelPartGroup group, DragonModelType sourceModelType) {
    public DragonOrigin origin() {
        return this.sourceModelType.origin();
    }
}

