/*
 * Decompiled with CFR 0.152.
 */
package trep.got.quest;

public enum QuestActionType {
    ACCEPT("accept"),
    NEGOTIATE("negotiate"),
    DELIVER("deliver"),
    PLEDGE_KNIGHT("pledge_knight"),
    PLEDGE_HEDGE_KNIGHT("pledge_hedge_knight"),
    PLEDGE_PRIEST("pledge_priest"),
    PLEDGE_COMPLETE_LORD_SIGIL("pledge_complete_lord_sigil"),
    REVOKE_PLEDGE("revoke_pledge"),
    VALYRIAN_TARGARYEN_CLAIM("valyrian_targaryen_claim"),
    VALYRIAN_CONFIRM_IDENTITY("valyrian_confirm_identity"),
    VALYRIAN_DELIVER_NEWS("valyrian_deliver_news"),
    VALYRIAN_DELIVER_DRAGON("valyrian_deliver_dragon");

    private final String id;

    private QuestActionType(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static QuestActionType byId(String id) {
        for (QuestActionType type : QuestActionType.values()) {
            if (!type.id.equals(id)) continue;
            return type;
        }
        return null;
    }
}

