/*
 * Decompiled with CFR 0.152.
 */
package trep.got.kingdom;

import java.util.EnumSet;
import java.util.Set;
import trep.got.castle.CastleInvasionMode;
import trep.got.kingdom.ReputationRoute;
import trep.got.kingdom.ReputationTitle;

public record ReputationTitleDefinition(ReputationTitle title, String displayName, ReputationRoute route, int tier, int experienceToNextTitle, ReputationTitle nextTitle, Set<CastleInvasionMode> invasionModes) {
    public ReputationTitleDefinition {
        invasionModes = invasionModes == null || invasionModes.isEmpty() ? Set.of() : Set.copyOf(EnumSet.copyOf(invasionModes));
        experienceToNextTitle = Math.max(0, experienceToNextTitle);
        tier = Math.max(0, tier);
    }

    public boolean canPerformInvasion(CastleInvasionMode mode) {
        return mode != null && this.invasionModes.contains((Object)mode);
    }
}

