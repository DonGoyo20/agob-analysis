/*
 * Decompiled with CFR 0.152.
 */
package trep.got.kingdom;

public enum ReputationRoute {
    NONE("none", "None"),
    ROYAL("royal", "Royal"),
    MERCENARY("mercenary", "Mercenary"),
    RELIGIOUS("religious", "Religious"),
    GOLDEN_COMPANY("golden_company", "Golden Company"),
    NIGHTS_WATCH("nights_watch", "Night's Watch"),
    WILDLING("wildling", "Wildling");

    private final String id;
    private final String displayName;

    private ReputationRoute(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static ReputationRoute byId(String id) {
        if (id == null || id.isBlank()) {
            return NONE;
        }
        for (ReputationRoute route : ReputationRoute.values()) {
            if (!route.id.equals(id)) continue;
            return route;
        }
        return NONE;
    }
}

