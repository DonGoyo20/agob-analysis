/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle;

public enum CastleWoodType {
    OAK("oak"),
    SPRUCE("spruce"),
    BIRCH("birch"),
    ACACIA("acacia"),
    JUNGLE("jungle"),
    DARK_OAK("dark_oak"),
    MANGROVE("mangrove"),
    BAMBOO("bamboo"),
    CHERRY("cherry");

    private final String id;

    private CastleWoodType(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static CastleWoodType byId(String id) {
        for (CastleWoodType type : CastleWoodType.values()) {
            if (!type.id.equals(id)) continue;
            return type;
        }
        return OAK;
    }
}

