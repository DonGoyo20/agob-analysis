/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1308
 *  net.minecraft.class_1352
 *  net.minecraft.class_1361
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1352;
import net.minecraft.class_1361;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import trep.got.entity.AbstractWorldEventNpcEntity;

public final class HostageNpcEntity
extends AbstractWorldEventNpcEntity {
    private static final class_2960 TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/entity/npc/event/lord.png");

    public HostageNpcEntity(class_1299<? extends AbstractWorldEventNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    protected void method_5959() {
        this.field_6201.method_6277(8, (class_1352)new class_1361((class_1308)this, class_1657.class, 8.0f));
    }

    @Override
    public boolean supportsDialogueInteraction() {
        return true;
    }

    @Override
    public class_2960 getTextureId() {
        return TEXTURE;
    }

    @Override
    public class_1799 getIndicatorStack() {
        return class_1799.field_8037;
    }

    @Override
    protected void applyWorldEventLoadout() {
    }

    @Override
    public void method_5773() {
        super.method_5773();
        if (!this.isInDialogueScene()) {
            this.method_5942().method_6340();
            this.method_18799(class_243.field_1353);
        }
    }

    public void method_6091(class_243 movementInput) {
        if (this.isInDialogueScene()) {
            super.method_6091(movementInput);
            return;
        }
        this.method_18799(class_243.field_1353);
    }
}

