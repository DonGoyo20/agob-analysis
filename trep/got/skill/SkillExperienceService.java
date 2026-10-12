/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_3222
 *  net.minecraft.class_8710
 */
package trep.got.skill;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import trep.got.network.SkillPointGainPayload;
import trep.got.registry.GotSkills;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeState;
import trep.got.skill.SkillAttributeType;
import trep.got.skill.SkillLevelCurve;

public final class SkillExperienceService {
    private SkillExperienceService() {
    }

    public static int addExperience(class_3222 player, SkillAttributeType type, int experience) {
        int requiredExperience;
        if (experience <= 0) {
            return 0;
        }
        SkillAttributeState state = ((PlayerSkillHolder)player).got$getSkillProfile().get(type);
        if (state.getPoints() >= state.getMaxPoints()) {
            state.setExperience(0);
            return 0;
        }
        state.setExperience(state.getExperience() + experience);
        int gainedPoints = 0;
        SkillLevelCurve levelCurve = GotSkills.getLevelCurve();
        while (state.getPoints() < state.getMaxPoints() && (requiredExperience = levelCurve.experienceForLevel(state.getLevel() + 1)) > 0 && state.getExperience() >= requiredExperience) {
            state.setExperience(state.getExperience() - requiredExperience);
            state.setLevel(state.getLevel() + 1);
            state.setPoints(state.getPoints() + 1);
            ++gainedPoints;
        }
        if (state.getPoints() >= state.getMaxPoints()) {
            state.setExperience(0);
        }
        if (gainedPoints > 0) {
            ServerPlayNetworking.send((class_3222)player, (class_8710)new SkillPointGainPayload(player.method_7334().getName(), type.getId(), gainedPoints, state.getPoints()));
        }
        return gainedPoints;
    }

    public static int addPoints(class_3222 player, SkillAttributeType type, int points) {
        if (points <= 0) {
            return 0;
        }
        SkillAttributeState state = ((PlayerSkillHolder)player).got$getSkillProfile().get(type);
        int previousPoints = state.getPoints();
        state.setPoints(previousPoints + points);
        int gainedPoints = state.getPoints() - previousPoints;
        if (gainedPoints > 0) {
            ServerPlayNetworking.send((class_3222)player, (class_8710)new SkillPointGainPayload(player.method_7334().getName(), type.getId(), gainedPoints, state.getPoints()));
        }
        return gainedPoints;
    }

    public static int removePoints(class_3222 player, SkillAttributeType type, int points) {
        if (points <= 0) {
            return 0;
        }
        SkillAttributeState state = ((PlayerSkillHolder)player).got$getSkillProfile().get(type);
        int previousPoints = state.getPoints();
        state.setPoints(previousPoints - points);
        return previousPoints - state.getPoints();
    }
}

