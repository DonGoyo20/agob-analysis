/*
 * Decompiled with CFR 0.152.
 */
package trep.got.skill.source;

import trep.got.skill.SkillAttributeType;
import trep.got.skill.source.SkillExperienceSource;

public final class LuckSkillExperienceSource
implements SkillExperienceSource {
    @Override
    public SkillAttributeType type() {
        return SkillAttributeType.LUCK;
    }
}

