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
import trep.got.entity.AbstractDynastyNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.registry.GotDynastyNpcs;

public final class DynastyNpcEntity
extends AbstractDynastyNpcEntity {
    public DynastyNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.setFamilyStage(GotDynastyNpcs.FamilyStage.DYNASTY);
        this.method_5880(true);
    }

    @Override
    public KingdomNpcRole getNpcRole() {
        return KingdomNpcRole.LORD;
    }

    @Override
    public class_1799 getIndicatorStack() {
        return new class_1799((class_1935)class_1802.field_8862);
    }
}

