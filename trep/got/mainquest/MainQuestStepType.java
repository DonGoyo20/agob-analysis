/*
 * Decompiled with CFR 0.152.
 */
package trep.got.mainquest;

public enum MainQuestStepType {
    ENTER_CASTLE_CLAIM("enter_castle_claim"),
    ENTER_CITY_STRUCTURE("enter_city_structure"),
    TALK_TO_NPCS("talk_to_npcs"),
    OPEN_BLACKSMITH_SCREEN("open_blacksmith_screen"),
    CRAFT_BLACKSMITH_ITEM("craft_blacksmith_item"),
    CHAT_WITH_LORD("chat_with_lord"),
    HIRE_TROOPS("hire_troops"),
    EXECUTE_COMMANDER_ACTIONS("execute_commander_actions"),
    DISCOVER_VILLAGE_CLAIM("discover_village_claim"),
    TAKE_LAND_VOYAGE("take_land_voyage"),
    TAKE_SEA_VOYAGE("take_sea_voyage"),
    KILL_ROBBERS("kill_robbers"),
    HAVE_ITEM_COUNT("have_item_count"),
    CREATE_PLAYER_SETTLEMENT("create_player_settlement");

    private final String id;

    private MainQuestStepType(String id) {
        this.id = id;
    }

    public String id() {
        return this.id;
    }

    public static MainQuestStepType byId(String id) {
        if (id == null) {
            return null;
        }
        for (MainQuestStepType type : MainQuestStepType.values()) {
            if (!type.id.equals(id)) continue;
            return type;
        }
        return null;
    }
}

