/*
 * Decompiled with CFR 0.152.
 */
package trep.got.troop;

public enum CommanderActionType {
    CHARGE("charge", "Charge"),
    MOVE("move", "Move"),
    FORMATION("formation", "Formation"),
    RECALL("recall", "Recall"),
    FOLLOW_ME("follow_me", "Follow Me"),
    HALT("halt", "Halt");

    private final String id;
    private final String displayName;

    private CommanderActionType(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String id() {
        return this.id;
    }

    public String displayName() {
        return this.displayName;
    }

    public static CommanderActionType byId(String id) {
        for (CommanderActionType type : CommanderActionType.values()) {
            if (!type.id.equals(id)) continue;
            return type;
        }
        return MOVE;
    }
}

