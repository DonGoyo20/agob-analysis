/*
 * Decompiled with CFR 0.152.
 */
package trep.got.relations;

public enum NpcRelationTier {
    HOSTILE("Hostile"),
    UNFRIENDLY("Unfriendly"),
    NEUTRAL("Neutral"),
    FRIENDLY("Friendly"),
    TRUSTED("Trusted");

    private final String displayName;

    private NpcRelationTier(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return this.displayName;
    }

    public static NpcRelationTier fromValue(int relation) {
        if (relation <= -11) {
            return HOSTILE;
        }
        if (relation < 0) {
            return UNFRIENDLY;
        }
        if (relation == 0) {
            return NEUTRAL;
        }
        if (relation <= 10) {
            return FRIENDLY;
        }
        return TRUSTED;
    }
}

