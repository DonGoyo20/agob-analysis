/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle;

public enum CastleInvasionMode {
    CRUSADE("crusade"),
    RAID("raid"),
    CHALLENGE("challenge"),
    CONQUER("conquer");

    private final String id;

    private CastleInvasionMode(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static CastleInvasionMode byId(String id) {
        for (CastleInvasionMode mode : CastleInvasionMode.values()) {
            if (!mode.id.equals(id)) continue;
            return mode;
        }
        return RAID;
    }
}

