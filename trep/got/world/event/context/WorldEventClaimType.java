/*
 * Decompiled with CFR 0.152.
 */
package trep.got.world.event.context;

public enum WorldEventClaimType {
    NONE("none"),
    PLAYER_SETTLEMENT("player_settlement"),
    SETTLEMENT("settlement"),
    LORDSHIP("lordship"),
    CAPITAL("capital");

    private final String id;

    private WorldEventClaimType(String id) {
        this.id = id;
    }

    public String id() {
        return this.id;
    }

    public static WorldEventClaimType byId(String id) {
        for (WorldEventClaimType type : WorldEventClaimType.values()) {
            if (!type.id.equals(id)) continue;
            return type;
        }
        return NONE;
    }
}

