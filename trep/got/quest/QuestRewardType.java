/*
 * Decompiled with CFR 0.152.
 */
package trep.got.quest;

public enum QuestRewardType {
    REPUTATION_EXPERIENCE("reputation_experience"),
    GOLD_INGOTS("gold_ingots"),
    IRON_INGOTS("iron_ingots"),
    SKILL_POINTS("skill_points");

    private final String id;

    private QuestRewardType(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static QuestRewardType byId(String id) {
        for (QuestRewardType type : QuestRewardType.values()) {
            if (!type.id.equals(id)) continue;
            return type;
        }
        return null;
    }
}

