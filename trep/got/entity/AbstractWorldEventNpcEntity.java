/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1304
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_2960
 *  net.minecraft.class_5132$class_5133
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import net.minecraft.class_5132;
import trep.got.entity.AbstractDialogueHumanoidEntity;
import trep.got.entity.AbstractKingdomHumanoidEntity;

public abstract class AbstractWorldEventNpcEntity
extends AbstractDialogueHumanoidEntity {
    private static final class_2940<Integer> SKIN_VARIANT = class_2945.method_12791(AbstractWorldEventNpcEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Boolean> EVENT_READY = class_2945.method_12791(AbstractWorldEventNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final String SKIN_VARIANT_KEY = "gotmod.event_skin_variant";
    private static final String EVENT_READY_KEY = "gotmod.event_ready";

    protected AbstractWorldEventNpcEntity(class_1299<? extends AbstractWorldEventNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    public static class_5132.class_5133 createWorldEventNpcAttributes() {
        return AbstractKingdomHumanoidEntity.createKingdomHumanoidAttributes();
    }

    @Override
    public void method_5773() {
        super.method_5773();
        if (!this.method_37908().method_8608() && !this.isEventPresentationReady()) {
            this.initializeWorldEventPresentation();
        }
    }

    @Override
    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(SKIN_VARIANT, (Object)0);
        builder.method_56912(EVENT_READY, (Object)false);
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10569(SKIN_VARIANT_KEY, this.getSkinVariant());
        nbt.method_10556(EVENT_READY_KEY, this.isEventPresentationReady());
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        this.setSkinVariant(nbt.method_10550(SKIN_VARIANT_KEY));
        this.setEventPresentationReady(nbt.method_10577(EVENT_READY_KEY));
    }

    @Override
    public boolean supportsDialogueInteraction() {
        return false;
    }

    @Override
    public class_2960 getKingdomId() {
        return class_2960.method_60655((String)"agob", (String)"the_crownlands");
    }

    public int getSkinVariant() {
        return (Integer)this.field_6011.method_12789(SKIN_VARIANT);
    }

    public abstract class_2960 getTextureId();

    protected int variantCount() {
        return 1;
    }

    protected final boolean isEventPresentationReady() {
        return (Boolean)this.field_6011.method_12789(EVENT_READY);
    }

    protected final void setSkinVariant(int skinVariant) {
        this.field_6011.method_12778(SKIN_VARIANT, (Object)Math.max(0, skinVariant));
    }

    protected final void setEventPresentationReady(boolean eventPresentationReady) {
        this.field_6011.method_12778(EVENT_READY, (Object)eventPresentationReady);
    }

    protected void initializeWorldEventPresentation() {
        if (this.isEventPresentationReady()) {
            return;
        }
        int variantCount = Math.max(1, this.variantCount());
        this.setSkinVariant(this.method_59922().method_43048(variantCount));
        this.clearEquipment();
        this.applyWorldEventLoadout();
        this.method_5952(false);
        for (class_1304 slot : class_1304.values()) {
            this.method_5946(slot, 0.0f);
        }
        this.setEventPresentationReady(true);
    }

    protected abstract void applyWorldEventLoadout();

    private void clearEquipment() {
        for (class_1304 slot : class_1304.values()) {
            this.method_5673(slot, class_1799.field_8037);
        }
    }
}

