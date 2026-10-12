/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle;

public enum CastleRaidState {
    PEACE,
    RAIDABLE,
    CONTESTED;


    public static CastleRaidState byId(String id) {
        for (CastleRaidState state : CastleRaidState.values()) {
            if (!state.name().equalsIgnoreCase(id)) continue;
            return state;
        }
        return PEACE;
    }
}

