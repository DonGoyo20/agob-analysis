/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.character;

import java.util.List;
import java.util.Map;
import net.minecraft.class_2960;
import trep.got.character.CharacterEquipmentPreset;
import trep.got.skill.SkillAttributeType;

public record CharacterSelectionOption(String id, class_2960 kingdomId, class_2960 texture, class_2960 previewSkinTexture, boolean smallArms, String displayName, String houseDisplayName, boolean primary, CharacterEquipmentPreset equipment, Map<SkillAttributeType, Integer> startingSkillPoints, Map<SkillAttributeType, Integer> maxSkillCapBonuses, String houseArmorId, List<String> spawnBiomes) {
    public CharacterSelectionOption {
        startingSkillPoints = Map.copyOf(startingSkillPoints);
        maxSkillCapBonuses = Map.copyOf(maxSkillCapBonuses);
        houseArmorId = houseArmorId == null ? "" : houseArmorId;
        spawnBiomes = List.copyOf(spawnBiomes);
    }
}

