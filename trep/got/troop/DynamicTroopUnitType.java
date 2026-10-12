/*
 * Decompiled with CFR 0.152.
 */
package trep.got.troop;

public enum DynamicTroopUnitType {
    MELEE("melee"),
    ARCHER("archer"),
    CAVALRY("cavalry");

    private final String id;

    private DynamicTroopUnitType(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static DynamicTroopUnitType byId(String id) {
        for (DynamicTroopUnitType value : DynamicTroopUnitType.values()) {
            if (!value.id.equals(id)) continue;
            return value;
        }
        return null;
    }
}

