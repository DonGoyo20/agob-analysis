/*
 * Decompiled with CFR 0.152.
 */
package trep.got.mainquest;

import java.util.Locale;
import trep.got.mainquest.MainQuestStepType;

public record MainQuestStep(String id, MainQuestStepType type, String text, String structurePathContains, String itemId, int requiredCount) {
    public boolean matchesStructurePath(String structurePath) {
        return structurePath != null && !this.structurePathContains.isBlank() && structurePath.toLowerCase(Locale.ROOT).contains(this.structurePathContains.toLowerCase(Locale.ROOT));
    }
}

