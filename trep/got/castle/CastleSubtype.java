/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle;

public enum CastleSubtype {
    HARBOUR("harbour", "Harbour");

    private final String id;
    private final String displayName;

    private CastleSubtype(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static CastleSubtype byId(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        for (CastleSubtype subtype : CastleSubtype.values()) {
            if (!subtype.id.equalsIgnoreCase(id)) continue;
            return subtype;
        }
        return null;
    }
}

