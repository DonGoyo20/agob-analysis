/*
 * Decompiled with CFR 0.152.
 */
package trep.got.dialogue;

public enum NpcChatAction {
    TALK("talk"),
    INTIMIDATE("intimidate"),
    COMPLIMENT("compliment"),
    FLIRT("flirt"),
    CHALLENGE_FOR_TITLE("challenge_for_title"),
    TOURNEY_EASY("tourney_easy"),
    TOURNEY_MEDIUM("tourney_medium"),
    TOURNEY_HARD("tourney_hard"),
    TRY_FOR_BABY("try_for_baby"),
    MARRY("marry"),
    DIVORCE("divorce"),
    TAKE_TO_PARTY("take_to_party"),
    ASK_TO_LEAVE("ask_to_leave");

    private final String id;

    private NpcChatAction(String id) {
        this.id = id;
    }

    public String id() {
        return this.id;
    }

    public static NpcChatAction byId(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        for (NpcChatAction action : NpcChatAction.values()) {
            if (!action.id.equals(id)) continue;
            return action;
        }
        return null;
    }
}

