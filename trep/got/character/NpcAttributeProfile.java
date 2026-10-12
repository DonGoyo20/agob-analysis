/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 */
package trep.got.character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.PlayerGeneticTrait;
import trep.got.character.PlayerPersonalityTrait;
import trep.got.character.PlayerTraitEffects;
import trep.got.skill.SkillAttributeType;

public record NpcAttributeProfile(String geneticTraitId, List<String> personalityTraitIds, Map<SkillAttributeType, Integer> skillPoints) {
    public static final NpcAttributeProfile EMPTY = new NpcAttributeProfile("", List.of(), Map.of());
    private static final int GENETIC_TRAIT_CHANCE = 5;
    private static final int PERSONALITY_TRAIT_CHANCE = 15;
    private static final int MAX_PERSONALITY_TRAITS = 2;
    private static final int DEFAULT_SKILL_BONUS_MIN = 0;
    private static final int DEFAULT_SKILL_BONUS_MAX = 10;

    public NpcAttributeProfile {
        geneticTraitId = NpcAttributeProfile.normalizeGeneticTraitId(geneticTraitId);
        personalityTraitIds = NpcAttributeProfile.normalizePersonalityTraitIds(personalityTraitIds);
        skillPoints = NpcAttributeProfile.normalizeSkillPoints(skillPoints);
    }

    public boolean isAssigned() {
        return !this.skillPoints.isEmpty();
    }

    public PlayerGeneticTrait geneticTrait() {
        return PlayerGeneticTrait.byId(this.geneticTraitId);
    }

    public List<PlayerPersonalityTrait> personalityTraits() {
        ArrayList<PlayerPersonalityTrait> traits = new ArrayList<PlayerPersonalityTrait>();
        for (String traitId : this.personalityTraitIds) {
            PlayerPersonalityTrait trait = PlayerPersonalityTrait.byId(traitId);
            if (trait == null || traits.size() >= 2) continue;
            traits.add(trait);
        }
        return List.copyOf(traits);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("genetic_trait_id", this.geneticTraitId);
        class_2499 personalityList = new class_2499();
        for (String traitId : this.personalityTraitIds) {
            personalityList.add((Object)class_2519.method_23256((String)traitId));
        }
        nbt.method_10566("personality_trait_ids", (class_2520)personalityList);
        class_2487 skills = new class_2487();
        for (Map.Entry<SkillAttributeType, Integer> entry : this.skillPoints.entrySet()) {
            skills.method_10569(entry.getKey().getId(), entry.getValue().intValue());
        }
        nbt.method_10566("skill_points", (class_2520)skills);
        return nbt;
    }

    public static NpcAttributeProfile fromNbt(class_2487 nbt) {
        if (nbt == null || nbt.method_33133()) {
            return EMPTY;
        }
        ArrayList<String> personalityTraits = new ArrayList<String>();
        class_2499 personalityList = nbt.method_10554("personality_trait_ids", 8);
        for (int index = 0; index < personalityList.size(); ++index) {
            personalityTraits.add(personalityList.method_10608(index));
        }
        EnumMap<SkillAttributeType, Integer> skills = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        class_2487 skillNbt = nbt.method_10562("skill_points");
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            if (!skillNbt.method_10573(type.getId(), 99)) continue;
            skills.put(type, skillNbt.method_10550(type.getId()));
        }
        return new NpcAttributeProfile(nbt.method_10558("genetic_trait_id"), personalityTraits, skills);
    }

    public static NpcAttributeProfile generate(CharacterSelectionOption option, String seedKey) {
        return NpcAttributeProfile.generate(option, seedKey, 0, 10);
    }

    public static NpcAttributeProfile generate(CharacterSelectionOption option, String seedKey, int skillBonusMin, int skillBonusMax) {
        Random random = new Random(NpcAttributeProfile.stableSeed(seedKey));
        PlayerGeneticTrait geneticTrait = null;
        if (!PlayerGeneticTrait.ALL.isEmpty() && random.nextInt(100) < 5) {
            geneticTrait = PlayerGeneticTrait.ALL.get(random.nextInt(PlayerGeneticTrait.ALL.size()));
        }
        ArrayList<PlayerPersonalityTrait> personalityTraits = new ArrayList<PlayerPersonalityTrait>();
        for (int roll = 0; roll < 2; ++roll) {
            if (PlayerPersonalityTrait.ALL.isEmpty() || random.nextInt(100) >= 15) continue;
            PlayerPersonalityTrait trait = PlayerPersonalityTrait.ALL.get(random.nextInt(PlayerPersonalityTrait.ALL.size()));
            for (int attempt = 0; personalityTraits.contains((Object)trait) && attempt < PlayerPersonalityTrait.ALL.size(); ++attempt) {
                trait = PlayerPersonalityTrait.ALL.get(Math.floorMod(PlayerPersonalityTrait.ALL.indexOf((Object)trait) + 1, PlayerPersonalityTrait.ALL.size()));
            }
            if (personalityTraits.contains((Object)trait)) continue;
            personalityTraits.add(trait);
        }
        Map<SkillAttributeType, Integer> baseSkills = option == null ? Map.of() : option.startingSkillPoints();
        EnumMap<SkillAttributeType, Integer> mergedSkills = PlayerTraitEffects.mergeStartingPoints(baseSkills, geneticTrait, personalityTraits);
        EnumMap<SkillAttributeType, Integer> clampedSkills = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        int normalizedMin = Math.max(0, Math.min(skillBonusMin, skillBonusMax));
        int normalizedMax = Math.max(normalizedMin, Math.max(skillBonusMin, skillBonusMax));
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            int value = mergedSkills.getOrDefault((Object)type, 0) + NpcAttributeProfile.randomInclusive(random, normalizedMin, normalizedMax);
            clampedSkills.put(type, Math.max(0, Math.min(100, value)));
        }
        List<String> personalityIds = personalityTraits.stream().map(PlayerPersonalityTrait::id).toList();
        return new NpcAttributeProfile(geneticTrait == null ? "" : geneticTrait.id(), personalityIds, clampedSkills);
    }

    private static int randomInclusive(Random random, int min, int max) {
        if (max <= min) {
            return min;
        }
        return min + random.nextInt(max - min + 1);
    }

    private static String normalizeGeneticTraitId(String traitId) {
        if (traitId == null || traitId.isBlank()) {
            return "";
        }
        PlayerGeneticTrait trait = PlayerGeneticTrait.byId(traitId);
        return trait == null ? "" : trait.id();
    }

    private static List<String> normalizePersonalityTraitIds(Collection<String> traitIds) {
        if (traitIds == null || traitIds.isEmpty()) {
            return List.of();
        }
        LinkedHashSet<String> normalized = new LinkedHashSet<String>();
        for (String traitId : traitIds) {
            PlayerPersonalityTrait trait = PlayerPersonalityTrait.byId(traitId);
            if (trait != null) {
                normalized.add(trait.id());
            }
            if (normalized.size() < 2) continue;
            break;
        }
        return List.copyOf(normalized);
    }

    private static Map<SkillAttributeType, Integer> normalizeSkillPoints(Map<SkillAttributeType, Integer> skillPoints) {
        if (skillPoints == null || skillPoints.isEmpty()) {
            return Map.of();
        }
        EnumMap<SkillAttributeType, Integer> normalized = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            if (!skillPoints.containsKey((Object)type)) continue;
            int value = skillPoints.getOrDefault((Object)type, 0);
            normalized.put(type, Math.max(0, Math.min(100, value)));
        }
        return Map.copyOf(normalized);
    }

    private static long stableSeed(String key) {
        String value = key == null ? "" : key;
        long seed = 1125899906842597L;
        for (int index = 0; index < value.length(); ++index) {
            seed = 31L * seed + (long)value.charAt(index);
        }
        seed ^= seed >>> 33;
        seed *= -49064778989728563L;
        seed ^= seed >>> 33;
        seed *= -4265267296055464877L;
        seed ^= seed >>> 33;
        return seed;
    }
}

