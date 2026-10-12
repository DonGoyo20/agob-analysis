/*
 * Decompiled with CFR 0.152.
 */
package trep.got.character;

import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import trep.got.character.PlayerGeneticTrait;
import trep.got.character.PlayerPersonalityTrait;
import trep.got.skill.SkillAttributeType;

public final class PlayerTraitEffects {
    private PlayerTraitEffects() {
    }

    public static EnumMap<SkillAttributeType, Integer> mergeStartingPoints(Map<SkillAttributeType, Integer> baseStartingPoints, PlayerGeneticTrait geneticTrait, Collection<PlayerPersonalityTrait> personalityTraits) {
        EnumMap<SkillAttributeType, Integer> merged = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            merged.put(type, baseStartingPoints.getOrDefault((Object)type, 0));
        }
        PlayerTraitEffects.mergeInPlace(merged, PlayerTraitEffects.startingPointBonuses(geneticTrait, personalityTraits));
        return merged;
    }

    public static EnumMap<SkillAttributeType, Integer> mergeMaxCapBonuses(Map<SkillAttributeType, Integer> baseMaxCapBonuses, PlayerGeneticTrait geneticTrait) {
        EnumMap<SkillAttributeType, Integer> merged = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            merged.put(type, baseMaxCapBonuses.getOrDefault((Object)type, 0));
        }
        if (geneticTrait != null) {
            PlayerTraitEffects.mergeInPlace(merged, geneticTrait.maxCapModifiers());
        }
        return merged;
    }

    public static EnumMap<SkillAttributeType, Integer> startingPointBonuses(PlayerGeneticTrait geneticTrait, Collection<PlayerPersonalityTrait> personalityTraits) {
        EnumMap<SkillAttributeType, Integer> merged = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            merged.put(type, 0);
        }
        if (geneticTrait != null) {
            PlayerTraitEffects.mergeInPlace(merged, geneticTrait.startingPointModifiers());
        }
        if (personalityTraits != null) {
            for (PlayerPersonalityTrait trait : personalityTraits) {
                if (trait == null) continue;
                PlayerTraitEffects.mergeInPlace(merged, trait.startingPointModifiers());
            }
        }
        return merged;
    }

    public static float resolveScaleMultiplier(PlayerGeneticTrait geneticTrait) {
        return geneticTrait == null ? 1.0f : geneticTrait.scaleMultiplier();
    }

    public static Set<PlayerPersonalityTrait> resolvePersonalityTraits(Collection<String> traitIds) {
        LinkedHashSet<PlayerPersonalityTrait> traits = new LinkedHashSet<PlayerPersonalityTrait>();
        if (traitIds == null) {
            return traits;
        }
        for (String traitId : traitIds) {
            PlayerPersonalityTrait trait = PlayerPersonalityTrait.byId(traitId);
            if (trait == null || traits.size() >= 2) continue;
            traits.add(trait);
        }
        return traits;
    }

    private static void mergeInPlace(EnumMap<SkillAttributeType, Integer> target, Map<SkillAttributeType, Integer> additions) {
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            target.put(type, target.getOrDefault((Object)type, 0) + additions.getOrDefault((Object)type, 0));
        }
    }
}

