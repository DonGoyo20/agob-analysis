/*
 * Decompiled with CFR 0.152.
 */
package trep.got.advancement;

public enum ModAdvancementType {
    REPUTATION_RANK_UP("reputation_rank_up"),
    QUEST_ACCEPTED("quest_accepted"),
    QUEST_COMPLETED("quest_completed");

    private final String id;

    private ModAdvancementType(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}

