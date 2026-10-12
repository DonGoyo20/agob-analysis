/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.registry.GotItems;

public abstract class AbstractKingdomFishermanNpcEntity
extends AbstractKingdomNpcEntity {
    protected AbstractKingdomFishermanNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    public KingdomNpcRole getNpcRole() {
        return KingdomNpcRole.FISHERMAN;
    }

    @Override
    public class_1799 getIndicatorStack() {
        return new class_1799((class_1935)GotItems.SHIP);
    }

    @Override
    public boolean supportsDialogueInteraction() {
        return true;
    }

    @Override
    public int getPointOfInterestRadius() {
        return 5;
    }

    @Override
    public abstract class_2960 getKingdomId();
}

