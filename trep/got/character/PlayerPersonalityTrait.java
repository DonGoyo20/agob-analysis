/*
 * Decompiled with CFR 0.152.
 */
package trep.got.character;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import trep.got.skill.SkillAttributeType;

public enum PlayerPersonalityTrait {
    GULLIBLE("gullible", "Gullible", Map.of(SkillAttributeType.LUCK, 5, SkillAttributeType.INTELLIGENCE, -5)),
    SHORT_TEMPERED("short_tempered", "Short-tempered", Map.of(SkillAttributeType.HEALTH, 5, SkillAttributeType.SMITHING, -5)),
    LONE_WOLF("lone_wolf", "Lone Wolf", Map.of(SkillAttributeType.RIDING, 5, SkillAttributeType.TRADING, -5)),
    JESTER("jester", "Jester", Map.of(SkillAttributeType.CHARISMA, 5, SkillAttributeType.LUCK, -5)),
    THOUGH("though", "Though", Map.of(SkillAttributeType.SMITHING, 5, SkillAttributeType.HEALTH, 5, SkillAttributeType.CHARISMA, -10));

    public static final List<PlayerPersonalityTrait> ALL;
    private final String id;
    private final String displayName;
    private final EnumMap<SkillAttributeType, Integer> startingPointModifiers;

    private PlayerPersonalityTrait(String id, String displayName, Map<SkillAttributeType, Integer> startingPointModifiers) {
        this.id = id;
        this.displayName = displayName;
        this.startingPointModifiers = new EnumMap(SkillAttributeType.class);
        this.startingPointModifiers.putAll(startingPointModifiers);
    }

    public String id() {
        return this.id;
    }

    public String displayName() {
        return this.displayName;
    }

    public Map<SkillAttributeType, Integer> startingPointModifiers() {
        return Map.copyOf(this.startingPointModifiers);
    }

    public static PlayerPersonalityTrait byId(String id) {
        for (PlayerPersonalityTrait trait : ALL) {
            if (!trait.id.equals(id)) continue;
            return trait;
        }
        return null;
    }

    static {
        ALL = List.of(PlayerPersonalityTrait.values());
    }
}

