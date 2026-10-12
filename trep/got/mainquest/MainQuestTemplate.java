/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.mainquest;

import java.util.List;
import net.minecraft.class_2960;
import org.jetbrains.annotations.Nullable;
import trep.got.mainquest.MainQuestReward;
import trep.got.mainquest.MainQuestStep;

public record MainQuestTemplate(class_2960 id, String displayName, List<String> description, boolean autoStart, @Nullable class_2960 nextQuestId, List<MainQuestStep> steps, List<MainQuestReward> rewards) {
    public MainQuestStep stepAt(int index) {
        return index >= 0 && index < this.steps.size() ? this.steps.get(index) : null;
    }
}

