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
import trep.got.entity.AbstractTieredDonkeyEntity;
import trep.got.entity.GotMountStats;

public final class GotDonkeyEntity
extends AbstractTieredDonkeyEntity {
    private static final GotMountStats.StatRange STATS = new GotMountStats.StatRange(0.12, 0.18, 0.4, 0.6, 18.0, 28.0);

    public GotDonkeyEntity(class_1299<? extends GotDonkeyEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    protected GotMountStats.StatRange got$getStatRange() {
        return STATS;
    }
}

