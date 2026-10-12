/*
 * Decompiled with CFR 0.152.
 */
package trep.got.kingdom;

import java.util.List;
import trep.got.kingdom.ReputationTitle;

public record ReputationTitleHelpData(ReputationTitle title, List<String> helpLines) {
    public ReputationTitleHelpData {
        helpLines = List.copyOf(helpLines);
    }
}

