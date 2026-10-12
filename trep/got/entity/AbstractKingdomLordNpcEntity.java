/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1304
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import trep.got.entity.AbstractKingdomGuardNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;

public abstract class AbstractKingdomLordNpcEntity
extends AbstractKingdomGuardNpcEntity {
    private static final class_2940<String> LORD_HERITAGE = class_2945.method_12791(AbstractKingdomLordNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> RELATED_LORD_ID = class_2945.method_12791(AbstractKingdomLordNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final String LORD_HERITAGE_KEY = "gotmod.lord_heritage";
    private static final String RELATED_LORD_ID_KEY = "gotmod.related_lord_id";

    protected AbstractKingdomLordNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.method_5880(true);
    }

    @Override
    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(LORD_HERITAGE, (Object)"");
        builder.method_56912(RELATED_LORD_ID, (Object)"");
    }

    @Override
    public KingdomNpcRole getNpcRole() {
        return KingdomNpcRole.LORD;
    }

    public String getLordHeritage() {
        return (String)this.field_6011.method_12789(LORD_HERITAGE);
    }

    public void setLordHeritage(String heritage) {
        this.field_6011.method_12778(LORD_HERITAGE, (Object)(heritage == null ? "" : heritage));
    }

    public String getRelatedLordId() {
        return (String)this.field_6011.method_12789(RELATED_LORD_ID);
    }

    public void setRelatedLordId(String relatedLordId) {
        this.field_6011.method_12778(RELATED_LORD_ID, (Object)(relatedLordId == null ? "" : relatedLordId));
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10582(LORD_HERITAGE_KEY, this.getLordHeritage());
        nbt.method_10582(RELATED_LORD_ID_KEY, this.getRelatedLordId());
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        if (nbt.method_10545(LORD_HERITAGE_KEY)) {
            this.setLordHeritage(nbt.method_10558(LORD_HERITAGE_KEY));
        }
        if (nbt.method_10545(RELATED_LORD_ID_KEY)) {
            this.setRelatedLordId(nbt.method_10558(RELATED_LORD_ID_KEY));
        }
    }

    @Override
    public class_1799 getIndicatorStack() {
        return new class_1799((class_1935)class_1802.field_8862);
    }

    @Override
    protected boolean shouldEquipHelmet() {
        return false;
    }

    @Override
    protected void afterGuardLoadoutApplied() {
        this.method_5673(class_1304.field_6169, class_1799.field_8037);
        this.method_5946(class_1304.field_6169, 0.0f);
    }
}

