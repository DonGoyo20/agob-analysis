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

public final class SumpterEntity
extends AbstractTieredHorseEntity {
    private static final GotMountStats.StatRange STATS = new GotMountStats.StatRange(0.1125, 0.18, 0.4, 0.7, 16.0, 24.0);

    public SumpterEntity(class_1299<? extends SumpterEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    protected int got$getRidingRequirement() {
        return 0;
    }

    @Override
    protected GotMountStats.StatRange got$getStatRange() {
        return STATS;
    }

    @Override
    protected class_1299<? extends AbstractTieredHorseEntity> got$getDefaultChildType() {
        return GotEntities.SUMPTER;
    }
}

