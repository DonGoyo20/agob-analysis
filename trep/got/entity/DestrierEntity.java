/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1937
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1937;
import trep.got.entity.AbstractTieredHorseEntity;
import trep.got.entity.GotMountStats;
import trep.got.registry.GotEntities;

public final class DestrierEntity
extends AbstractTieredHorseEntity {
    private static final GotMountStats.StatRange STATS = new GotMountStats.StatRange(0.23, 0.32, 0.85, 1.05, 28.0, 38.0);

    public DestrierEntity(class_1299<? extends DestrierEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    protected int got$getRidingRequirement() {
        return 60;
    }

    @Override
    protected GotMountStats.StatRange got$getStatRange() {
        return STATS;
    }

    @Override
    protected class_1299<? extends AbstractTieredHorseEntity> got$getDefaultChildType() {
        return GotEntities.DESTRIER;
    }
}

