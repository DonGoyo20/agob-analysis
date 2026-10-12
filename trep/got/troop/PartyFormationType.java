/*
 * Decompiled with CFR 0.152.
 */
package trep.got.troop;

public enum PartyFormationType {
    LINE("line"),
    BOX("box"),
    LOOSE("loose");

    private final String id;

    private PartyFormationType(String id) {
        this.id = id;
    }

    public String id() {
        return this.id;
    }

    public String displayName() {
        return switch (this.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> "Line Formation";
            case 1 -> "Box Formation";
            case 2 -> "Loose Formation";
        };
    }

    public PartyFormationType next() {
        PartyFormationType[] values = PartyFormationType.values();
        return values[(this.ordinal() + 1) % values.length];
    }

    public static PartyFormationType byId(String id) {
        for (PartyFormationType type : PartyFormationType.values()) {
            if (!type.id.equals(id)) continue;
            return type;
        }
        return LINE;
    }
}

