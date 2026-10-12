/*
 * Decompiled with CFR 0.152.
 */
package trep.got.character;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import trep.got.skill.SkillAttributeType;

public enum PlayerGeneticTrait {
    DWARFISM("dwarfism", "Dwarfism", 0.7f, Map.of(SkillAttributeType.INTELLIGENCE, 5, SkillAttributeType.CHARISMA, 5), Map.of(SkillAttributeType.HEALTH, -20, SkillAttributeType.INTELLIGENCE, 10, SkillAttributeType.CHARISMA, 10)),
    GIANTISM("giantism", "Giantism", 1.25f, Map.of(SkillAttributeType.HEALTH, 10), Map.of(SkillAttributeType.HEALTH, 20, SkillAttributeType.INTELLIGENCE, -10, SkillAttributeType.CHARISMA, -10));

    public static final List<PlayerGeneticTrait> ALL;
    private final String id;
    private final String displayName;
    private final float scaleMultiplier;
    private final EnumMap<SkillAttributeType, Integer> startingPointModifiers;
    private final EnumMap<SkillAttributeType, Integer> maxCapModifiers;

    private PlayerGeneticTrait(String id, String displayName, float scaleMultiplier, Map<SkillAttributeType, Integer> startingPointModifiers, Map<SkillAttributeType, Integer> maxCapModifiers) {
        this.id = id;
        this.displayName = displayName;
        this.scaleMultiplier = scaleMultiplier;
        this.startingPointModifiers = new EnumMap(SkillAttributeType.class);
        this.startingPointModifiers.putAll(startingPointModifiers);
        this.maxCapModifiers = new EnumMap(SkillAttributeType.class);
        this.maxCapModifiers.putAll(maxCapModifiers);
    }

    public String id() {
        return this.id;
    }

    public String displayName() {
        return this.displayName;
    }

    public float scaleMultiplier() {
        return this.scaleMultiplier;
    }

    public Map<SkillAttributeType, Integer> startingPointModifiers() {
        return Map.copyOf(this.startingPointModifiers);
    }

    public Map<SkillAttributeType, Integer> maxCapModifiers() {
        return Map.copyOf(this.maxCapModifiers);
    }

    public static PlayerGeneticTrait byId(String id) {
        for (PlayerGeneticTrait trait : ALL) {
            if (!trait.id.equals(id)) continue;
            return trait;
        }
        return null;
    }

    static {
        ALL = List.of(PlayerGeneticTrait.values());
    }
}

