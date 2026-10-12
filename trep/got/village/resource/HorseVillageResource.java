/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 */
package trep.got.village.resource;

import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import trep.got.skill.SkillAttributeType;
import trep.got.village.resource.VillageResource;

public class HorseVillageResource
extends VillageResource {
    public HorseVillageResource(class_2960 id, class_2561 displayName) {
        super(id, displayName);
    }

    @Override
    public class_1799 getIconStack() {
        return new class_1799((class_1935)class_1802.field_8175);
    }

    @Override
    public class_2960 getIconTexture() {
        return SkillAttributeType.RIDING.getIconTexture();
    }

    @Override
    public boolean isHorse() {
        return true;
    }
}

