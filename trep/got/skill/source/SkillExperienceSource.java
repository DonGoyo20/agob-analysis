/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_3222
 */
package trep.got.skill.source;

import net.minecraft.class_1799;
import net.minecraft.class_3222;
import trep.got.skill.SkillAttributeType;

public interface SkillExperienceSource {
    public SkillAttributeType type();

    default public void register() {
    }

    default public void tick(class_3222 player) {
    }

    default public void onFoodConsumed(class_3222 player, class_1799 stack) {
    }
}

