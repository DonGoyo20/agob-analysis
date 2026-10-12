/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import trep.got.entity.AbstractKingdomCartRiderNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;

public final class CrownlandsCartRiderNpcEntity
extends AbstractKingdomCartRiderNpcEntity {
    public CrownlandsCartRiderNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    public class_2960 getKingdomId() {
        return class_2960.method_60655((String)"agob", (String)"the_crownlands");
    }
}

