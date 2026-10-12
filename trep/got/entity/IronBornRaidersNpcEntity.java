/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1304
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import trep.got.entity.AbstractHostileWorldEventNpcEntity;
import trep.got.entity.AbstractWorldEventNpcEntity;
import trep.got.registry.GotKingdomArmor;

public final class IronBornRaidersNpcEntity
extends AbstractHostileWorldEventNpcEntity {
    private static final class_2960[] TEXTURES = new class_2960[]{class_2960.method_60655((String)"agob", (String)"textures/entity/npc/event/peasant_1.png"), class_2960.method_60655((String)"agob", (String)"textures/entity/npc/event/peasant_2.png"), class_2960.method_60655((String)"agob", (String)"textures/entity/npc/event/peasant_3.png")};

    public IronBornRaidersNpcEntity(class_1299<? extends AbstractWorldEventNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    public class_2960 getTextureId() {
        return TEXTURES[Math.floorMod(this.getSkinVariant(), TEXTURES.length)];
    }

    @Override
    public class_2960 getKingdomId() {
        return class_2960.method_60655((String)"agob", (String)"iron_islands");
    }

    @Override
    protected int variantCount() {
        return TEXTURES.length;
    }

    @Override
    protected void applyWorldEventLoadout() {
        this.method_5673(class_1304.field_6173, new class_1799((class_1935)class_1802.field_8475));
        class_1792[] armor = GotKingdomArmor.armorForKingdom(this.getKingdomId());
        if (armor == null) {
            return;
        }
        this.method_5673(class_1304.field_6169, new class_1799((class_1935)armor[0]));
        this.method_5673(class_1304.field_6174, new class_1799((class_1935)armor[1]));
        this.method_5673(class_1304.field_6172, new class_1799((class_1935)armor[2]));
        this.method_5673(class_1304.field_6166, new class_1799((class_1935)armor[3]));
    }
}

