/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1496
 *  net.minecraft.class_243
 *  net.minecraft.class_3222
 *  net.minecraft.class_3532
 *  net.minecraft.class_5134
 */
package trep.got.skill.source;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1496;
import net.minecraft.class_243;
import net.minecraft.class_3222;
import net.minecraft.class_3532;
import net.minecraft.class_5134;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeType;
import trep.got.skill.SkillExperienceService;
import trep.got.skill.source.SkillExperienceSource;

public final class RidingSkillExperienceSource
implements SkillExperienceSource {
    private static final double DISTANCE_PER_REWARD = 48.0;
    private final Map<UUID, RidingRuntimeState> runtimeState = new HashMap<UUID, RidingRuntimeState>();

    @Override
    public SkillAttributeType type() {
        return SkillAttributeType.RIDING;
    }

    @Override
    public void tick(class_3222 player) {
        class_1496 horse;
        double horizontalDistance;
        RidingRuntimeState state;
        block4: {
            block3: {
                state = this.runtimeState.computeIfAbsent(player.method_5667(), uuid -> RidingRuntimeState.create(player));
                class_243 currentPosition = player.method_19538();
                horizontalDistance = currentPosition.method_1020(state.lastPosition).method_37267();
                state.lastPosition = currentPosition;
                class_1297 class_12972 = player.method_5854();
                if (!(class_12972 instanceof class_1496)) break block3;
                horse = (class_1496)class_12972;
                if (!(horizontalDistance <= 0.01)) break block4;
            }
            state.pendingDistance = 0.0;
            return;
        }
        state.pendingDistance += horizontalDistance;
        double horseTier = RidingSkillExperienceSource.computeHorseTier(horse);
        while (state.pendingDistance >= 48.0) {
            state.pendingDistance -= 48.0;
            int baseExperience = 6 + class_3532.method_15375((float)((float)(horseTier * 3.0)));
            SkillExperienceService.addExperience(player, this.type(), RidingSkillExperienceSource.scaleExperience(player, baseExperience, horseTier));
        }
    }

    public static void grantHorseBreedingExperience(class_3222 player, class_1496 firstParent, class_1496 secondParent) {
        if (player == null || firstParent == null || secondParent == null) {
            return;
        }
        double averageTier = (RidingSkillExperienceSource.computeHorseTier(firstParent) + RidingSkillExperienceSource.computeHorseTier(secondParent)) * 0.5;
        int baseExperience = 22 + class_3532.method_15375((float)((float)(averageTier * 10.0)));
        SkillExperienceService.addExperience(player, SkillAttributeType.RIDING, RidingSkillExperienceSource.scaleExperience(player, baseExperience, averageTier + 1.0));
    }

    private static int scaleExperience(class_3222 player, int baseExperience, double tier) {
        if (!(player instanceof PlayerSkillHolder)) {
            return Math.max(1, baseExperience);
        }
        PlayerSkillHolder skillHolder = (PlayerSkillHolder)player;
        int points = skillHolder.got$getSkillProfile().get(SkillAttributeType.RIDING).getPoints();
        double scaled = (double)baseExperience * tier / (1.0 + Math.pow((double)points / Math.max(10.0, tier * 12.0), 2.0));
        return Math.max(1, (int)Math.round(scaled));
    }

    private static double computeHorseTier(class_1496 horse) {
        double speed = horse.method_45325(class_5134.field_23719);
        double maxHealth = horse.method_6063();
        double jumpStrength = horse.method_45325(class_5134.field_23728);
        double speedScore = class_3532.method_15363((float)((float)((speed - 0.1125) / 0.225)), (float)0.0f, (float)1.0f);
        double healthScore = class_3532.method_15363((float)((float)((maxHealth - 15.0) / 15.0)), (float)0.0f, (float)1.0f);
        double jumpScore = class_3532.method_15363((float)((float)((jumpStrength - 0.4) / 0.6)), (float)0.0f, (float)1.0f);
        return 1.0 + (speedScore + healthScore + jumpScore) / 3.0 * 4.0;
    }

    private static final class RidingRuntimeState {
        private class_243 lastPosition;
        private double pendingDistance;

        private RidingRuntimeState() {
        }

        private static RidingRuntimeState create(class_3222 player) {
            RidingRuntimeState state = new RidingRuntimeState();
            state.lastPosition = player.method_19538();
            return state;
        }
    }
}

