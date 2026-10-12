/*
 * Decompiled with CFR 0.152.
 */
package trep.got.quest;

public enum QuestNegotiationOutcome {
    SUCCESS("success"),
    FAILURE("failure");

    private final String id;

    private QuestNegotiationOutcome(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static QuestNegotiationOutcome byId(String id) {
        for (QuestNegotiationOutcome outcome : QuestNegotiationOutcome.values()) {
            if (!outcome.id.equals(id)) continue;
            return outcome;
        }
        return null;
    }
}

