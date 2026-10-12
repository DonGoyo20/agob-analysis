/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import java.util.Arrays;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import trep.got.character.PlayerSkinBuilderProfile;
import trep.got.kingdom.ReputationRoute;
import trep.got.skill.SkillAttributeType;

public record CharacterSelectionStatePayload(String selectedReligionId, String selectedKingdomId, String selectedOptionId, String originalSelectedKingdomId, String originalSelectedOptionId, String selectedGeneticTraitId, String[] selectedPersonalityTraitIds, PlayerSkinBuilderProfile skinBuilderProfile, int[] skillPoints, int[] skillMaxPoints, int[] shirtSkillBonuses, int[] pantsSkillBonuses, int reputationExperience, String reputationTitleId, ReputationRoute reputationRoute, boolean knighthoodCeremonyCompleted, boolean sellswordContractCompleted, boolean baptised) implements class_8710
{
    public static final class_8710.class_9154<CharacterSelectionStatePayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"character_selection_state"));
    public static final class_9139<class_9129, CharacterSelectionStatePayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10814(value.selectedReligionId);
        buf.method_10814(value.selectedKingdomId);
        buf.method_10814(value.selectedOptionId);
        buf.method_10814(value.originalSelectedKingdomId);
        buf.method_10814(value.originalSelectedOptionId);
        buf.method_10814(value.selectedGeneticTraitId);
        buf.method_10804(value.selectedPersonalityTraitIds.length);
        for (String traitId : value.selectedPersonalityTraitIds) {
            buf.method_10814(traitId);
        }
        value.skinBuilderProfile.write((class_9129)buf);
        buf.method_10804(value.skillPoints.length);
        for (int points : value.skillPoints) {
            buf.method_10804(points);
        }
        buf.method_10804(value.skillMaxPoints.length);
        for (int maxPoints : value.skillMaxPoints) {
            buf.method_10804(maxPoints);
        }
        buf.method_10804(value.shirtSkillBonuses.length);
        for (int bonus : value.shirtSkillBonuses) {
            buf.method_10804(bonus);
        }
        buf.method_10804(value.pantsSkillBonuses.length);
        for (int bonus : value.pantsSkillBonuses) {
            buf.method_10804(bonus);
        }
        buf.method_10804(value.reputationExperience);
        buf.method_10814(value.reputationTitleId);
        buf.method_10814(value.reputationRoute.name());
        buf.method_52964(value.knighthoodCeremonyCompleted);
        buf.method_52964(value.sellswordContractCompleted);
        buf.method_52964(value.baptised);
    }, buf -> {
        String selectedReligionId = buf.method_19772();
        String selectedKingdomId = buf.method_19772();
        String selectedOptionId = buf.method_19772();
        String originalSelectedKingdomId = buf.method_19772();
        String originalSelectedOptionId = buf.method_19772();
        String selectedGeneticTraitId = buf.method_19772();
        int selectedPersonalityTraitCount = buf.method_10816();
        String[] selectedPersonalityTraitIds = new String[selectedPersonalityTraitCount];
        for (int index = 0; index < selectedPersonalityTraitCount; ++index) {
            selectedPersonalityTraitIds[index] = buf.method_19772();
        }
        PlayerSkinBuilderProfile skinBuilderProfile = PlayerSkinBuilderProfile.read(buf);
        int size = buf.method_10816();
        int[] skillPoints = new int[size];
        for (int index = 0; index < size; ++index) {
            skillPoints[index] = buf.method_10816();
        }
        int maxSize = buf.method_10816();
        int[] skillMaxPoints = new int[maxSize];
        for (int index = 0; index < maxSize; ++index) {
            skillMaxPoints[index] = buf.method_10816();
        }
        int shirtBonusSize = buf.method_10816();
        int[] shirtSkillBonuses = new int[shirtBonusSize];
        for (int index = 0; index < shirtBonusSize; ++index) {
            shirtSkillBonuses[index] = buf.method_10816();
        }
        int pantsBonusSize = buf.method_10816();
        int[] pantsSkillBonuses = new int[pantsBonusSize];
        for (int index = 0; index < pantsBonusSize; ++index) {
            pantsSkillBonuses[index] = buf.method_10816();
        }
        int reputationExperience = buf.method_10816();
        String reputationTitleId = buf.method_19772();
        ReputationRoute reputationRoute = ReputationRoute.valueOf(buf.method_19772());
        boolean knighthoodCeremonyCompleted = buf.readBoolean();
        boolean sellswordContractCompleted = buf.readBoolean();
        boolean baptised = buf.readBoolean();
        return new CharacterSelectionStatePayload(selectedReligionId, selectedKingdomId, selectedOptionId, originalSelectedKingdomId, originalSelectedOptionId, selectedGeneticTraitId, selectedPersonalityTraitIds, skinBuilderProfile, skillPoints, skillMaxPoints, shirtSkillBonuses, pantsSkillBonuses, reputationExperience, reputationTitleId, reputationRoute, knighthoodCeremonyCompleted, sellswordContractCompleted, baptised);
    });

    public CharacterSelectionStatePayload {
        selectedPersonalityTraitIds = Arrays.copyOf(selectedPersonalityTraitIds, selectedPersonalityTraitIds.length);
        skinBuilderProfile = skinBuilderProfile == null ? PlayerSkinBuilderProfile.DEFAULT : skinBuilderProfile;
        skillPoints = Arrays.copyOf(skillPoints, skillPoints.length);
        skillMaxPoints = Arrays.copyOf(skillMaxPoints, skillMaxPoints.length);
        shirtSkillBonuses = Arrays.copyOf(shirtSkillBonuses, shirtSkillBonuses.length);
        pantsSkillBonuses = Arrays.copyOf(pantsSkillBonuses, pantsSkillBonuses.length);
    }

    public boolean requiresSelection() {
        return this.selectedReligionId.isBlank() || this.selectedKingdomId.isBlank();
    }

    public int getSkillPoints(SkillAttributeType type) {
        int index = type.ordinal();
        if (index < 0 || index >= this.skillPoints.length) {
            return 0;
        }
        return this.skillPoints[index];
    }

    public int getSkillMaxPoints(SkillAttributeType type) {
        int index = type.ordinal();
        if (index < 0 || index >= this.skillMaxPoints.length) {
            return 100;
        }
        return this.skillMaxPoints[index];
    }

    public int getShirtSkillBonus(SkillAttributeType type) {
        int index = type.ordinal();
        if (index < 0 || index >= this.shirtSkillBonuses.length) {
            return 0;
        }
        return this.shirtSkillBonuses[index];
    }

    public int getPantsSkillBonus(SkillAttributeType type) {
        int index = type.ordinal();
        if (index < 0 || index >= this.pantsSkillBonuses.length) {
            return 0;
        }
        return this.pantsSkillBonuses[index];
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

