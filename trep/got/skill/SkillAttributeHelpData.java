/*
 * Decompiled with CFR 0.152.
 */
package trep.got.skill;

import java.util.List;
import trep.got.skill.SkillAttributeType;

public record SkillAttributeHelpData(SkillAttributeType type, List<String> helpLines) {
    public SkillAttributeHelpData {
        helpLines = List.copyOf(helpLines);
    }
}

