/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1322
 *  net.minecraft.class_1322$class_1323
 *  net.minecraft.class_1324
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 *  net.minecraft.class_5134
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1322;
import net.minecraft.class_1324;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_5134;
import trep.got.entity.AbstractDynastyNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.registry.GotDynastyNpcs;

public final class ChildNpcEntity
extends AbstractDynastyNpcEntity {
    private static final class_2960 CHILD_SCALE_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"child_npc_scale");
    private static final double CHILD_SCALE = 0.65;

    public ChildNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.setFamilyStage(GotDynastyNpcs.FamilyStage.CHILD);
        this.method_5880(true);
    }

    @Override
    public class_1799 getIndicatorStack() {
        return class_1799.field_8037;
    }

    @Override
    public void method_5773() {
        super.method_5773();
        class_1324 scaleAttribute = this.method_5996(class_5134.field_47760);
        if (scaleAttribute == null || scaleAttribute.method_6196(CHILD_SCALE_MODIFIER_ID)) {
            return;
        }
        scaleAttribute.method_26837(new class_1322(CHILD_SCALE_MODIFIER_ID, -0.35, class_1322.class_1323.field_6331));
        this.method_18382();
    }
}

