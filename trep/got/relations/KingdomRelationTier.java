/*
 * Decompiled with CFR 0.152.
 */
package trep.got.relations;

public enum KingdomRelationTier {
    ENEMY("enemy", 0.5f),
    SUSPICIOUS("suspicious", 0.75f),
    NEUTRAL("neutral", 1.0f),
    FRIEND("friend", 1.25f),
    ALLY("ally", 1.5f);

    private final String id;
    private final float playerModifier;

    private KingdomRelationTier(String id, float playerModifier) {
        this.id = id;
        this.playerModifier = playerModifier;
    }

    public String getId() {
        return this.id;
    }

    public float getPlayerModifier() {
        return this.playerModifier;
    }

    public static KingdomRelationTier fromValue(int relation) {
        if (relation <= -60) {
            return ENEMY;
        }
        if (relation <= -20) {
            return SUSPICIOUS;
        }
        if (relation < 20) {
            return NEUTRAL;
        }
        if (relation < 60) {
            return FRIEND;
        }
        return ALLY;
    }
}

