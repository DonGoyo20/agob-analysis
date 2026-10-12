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

public final class ConfigurableKingdomCartRiderNpcEntity
extends AbstractKingdomCartRiderNpcEntity {
    private class_2960 kingdomId;

    public ConfigurableKingdomCartRiderNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world, class_2960 kingdomId) {
        super(entityType, world);
        this.kingdomId = kingdomId;
    }

    @Override
    public class_2960 getKingdomId() {
        return this.kingdomId;
    }
}

