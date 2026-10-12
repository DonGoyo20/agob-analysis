/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import trep.got.entity.DynamicTroopEntity;

public final class WarPlaceholderEntity
extends DynamicTroopEntity {
    private PreviewMode previewMode = PreviewMode.NORMAL;

    public WarPlaceholderEntity(class_1299<? extends DynamicTroopEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.method_5684(true);
        this.method_5803(true);
        this.method_5875(true);
        this.field_5960 = true;
    }

    @Override
    public void method_5773() {
        this.method_18800(0.0, 0.0, 0.0);
        this.method_5942().method_6340();
    }

    @Override
    public class_2960 getKingdomId() {
        return class_2960.method_60655((String)"agob", (String)"the_crownlands");
    }

    public PreviewMode getPreviewMode() {
        return this.previewMode;
    }

    public void setPreviewMode(PreviewMode previewMode) {
        this.previewMode = previewMode == null ? PreviewMode.NORMAL : previewMode;
    }

    public static enum PreviewMode {
        NORMAL,
        INVADE,
        RAID;

    }
}

