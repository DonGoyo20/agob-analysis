/*
 * Decompiled with CFR 0.152.
 */
package trep.got.skill.source;

import trep.got.skill.SkillAttributeType;
import trep.got.skill.source.SkillExperienceSource;

public final class TradingSkillExperienceSource
implements SkillExperienceSource {
    @Override
    public SkillAttributeType type() {
        return SkillAttributeType.TRADING;
    }
}

