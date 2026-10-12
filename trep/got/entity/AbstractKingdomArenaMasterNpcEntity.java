/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;

public abstract class AbstractKingdomArenaMasterNpcEntity
extends AbstractKingdomNpcEntity {
    protected AbstractKingdomArenaMasterNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    public KingdomNpcRole getNpcRole() {
        return KingdomNpcRole.ARENA_MASTER;
    }

    @Override
    public class_1799 getIndicatorStack() {
        return new class_1799((class_1935)class_1802.field_8255);
    }
}

