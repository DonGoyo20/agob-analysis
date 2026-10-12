/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle;

public enum PlayerSettlementTier {
    PLAYER_SETTLEMENT("player_settlement", "Player Settlement"),
    SETTLEMENT("settlement", "Settlement"),
    LORDSHIP("lordship", "Lordship"),
    CAPITAL("capital", "Capital");

    private final String id;
    private final String displayName;

    private PlayerSettlementTier(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static PlayerSettlementTier byId(String id) {
        for (PlayerSettlementTier tier : PlayerSettlementTier.values()) {
            if (!tier.id.equalsIgnoreCase(id)) continue;
            return tier;
        }
        return PLAYER_SETTLEMENT;
    }
}

