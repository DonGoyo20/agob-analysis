/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents
 *  net.minecraft.class_1297
 *  net.minecraft.class_1496
 *  net.minecraft.class_1588
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3532
 *  net.minecraft.class_7923
 */
package trep.got.skill.source;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.class_1297;
import net.minecraft.class_1496;
import net.minecraft.class_1588;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3532;
import net.minecraft.class_7923;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeType;
import trep.got.skill.SkillExperienceService;
import trep.got.skill.source.SkillExperienceSource;

public final class HealthSkillExperienceSource
implements SkillExperienceSource {
    private static final float NEAR_DEATH_THRESHOLD = 0.3f;
    private static final int NEAR_DEATH_RECOVERY_MULTIPLIER = 8;
    private static final int FOOD_HISTORY_SIZE = 5;
    private static final long FOOD_VARIETY_COOLDOWN_TICKS = 6000L;
    private static final double SPRINT_WARMUP_DISTANCE = 100.0;
    private static final double SPRINT_POINT_DISTANCE = 40.0;
    private static final int SPRINT_WARMUP_XP = 6;
    private static final int SPRINT_STEADY_XP = 2;
    private final Map<UUID, HealthSkillRuntimeState> runtimeState = new HashMap<UUID, HealthSkillRuntimeState>();

    @Override
    public SkillAttributeType type() {
        return SkillAttributeType.HEALTH;
    }

    @Override
    public void register() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(this::onKilledEntity);
    }

    @Override
    public void tick(class_3222 player) {
        float maxHealth;
        float nearDeathHealth;
        HealthSkillRuntimeState state = this.runtimeState.computeIfAbsent(player.method_5667(), uuid -> HealthSkillRuntimeState.create(player));
        float currentHealth = player.method_6032();
        if (currentHealth <= (nearDeathHealth = (maxHealth = player.method_6063()) * 0.3f)) {
            state.lowestHealth = Math.min(state.lowestHealth, currentHealth);
            state.recoveringFromNearDeath = true;
        }
        if (state.recoveringFromNearDeath && currentHealth >= maxHealth && state.lowestHealth < Float.MAX_VALUE) {
            int missingHealth = Math.max(0, Math.round(maxHealth - state.lowestHealth));
            int recoveryExperience = missingHealth * 8;
            SkillExperienceService.addExperience(player, this.type(), recoveryExperience);
            state.lowestHealth = Float.MAX_VALUE;
            state.recoveringFromNearDeath = false;
        }
        HealthSkillExperienceSource.updateSprintProgress(player, state);
    }

    @Override
    public void onFoodConsumed(class_3222 player, class_1799 stack) {
        if (stack.method_7960() || stack.method_7909() == class_1802.field_8162) {
            return;
        }
        HealthSkillRuntimeState state = this.runtimeState.computeIfAbsent(player.method_5667(), uuid -> HealthSkillRuntimeState.create(player));
        long now = player.method_51469().method_8510();
        if (now - state.lastFoodRewardTick < 6000L) {
            return;
        }
        class_2960 foodId = class_7923.field_41178.method_10221((Object)stack.method_7909());
        int experience = HealthSkillExperienceSource.getFoodVarietyExperience(foodId, state.foodHistory);
        if (experience <= 0) {
            return;
        }
        state.lastFoodRewardTick = now;
        HealthSkillExperienceSource.pushFoodHistory(state.foodHistory, foodId);
        SkillExperienceService.addExperience(player, this.type(), experience);
    }

    private void onKilledEntity(class_3218 world, class_1297 attacker, class_1297 killedEntity) {
        class_3222 player;
        block3: {
            block2: {
                if (!(attacker instanceof class_3222)) break block2;
                player = (class_3222)attacker;
                if (killedEntity instanceof class_1588) break block3;
            }
            return;
        }
        class_1588 hostileEntity = (class_1588)killedEntity;
        int playerLevel = ((PlayerSkillHolder)player).got$getSkillProfile().get(this.type()).getLevel();
        double mobTier = Math.max(1.0, (double)hostileEntity.method_6063() / 20.0);
        double baseExperience = 12.0 * mobTier;
        double experience = baseExperience / (1.0 + Math.pow((double)playerLevel / mobTier, 2.0));
        SkillExperienceService.addExperience(player, this.type(), Math.max(1, class_3532.method_15357((double)experience)));
    }

    private static int getFoodVarietyExperience(class_2960 foodId, Deque<class_2960> history) {
        class_2960 mostRecent = history.peekLast();
        if (foodId.equals((Object)mostRecent)) {
            return 0;
        }
        if (history.contains(foodId)) {
            return 2;
        }
        return 10;
    }

    private static void pushFoodHistory(Deque<class_2960> history, class_2960 foodId) {
        if (history.size() >= 5) {
            history.removeFirst();
        }
        history.addLast(foodId);
    }

    private static void updateSprintProgress(class_3222 player, HealthSkillRuntimeState state) {
        class_243 currentPosition = player.method_19538();
        double horizontalDistance = currentPosition.method_1020(state.lastPosition).method_37267();
        state.lastPosition = currentPosition;
        if (player.method_5854() instanceof class_1496 || !player.method_5624() || horizontalDistance <= 0.01) {
            state.sprintWarmupDistance = 0.0;
            state.pendingSprintDistance = 0.0;
            return;
        }
        double multiplier = player.method_7344().method_7586() <= 6 ? 0.1 : 1.0;
        double scaledDistance = horizontalDistance * multiplier;
        state.pendingSprintDistance += scaledDistance;
        state.sprintWarmupDistance += scaledDistance;
        while (state.pendingSprintDistance >= 40.0) {
            state.pendingSprintDistance -= 40.0;
            int experience = state.sprintWarmupDistance <= 100.0 ? 6 : 2;
            SkillExperienceService.addExperience(player, SkillAttributeType.HEALTH, experience);
        }
    }

    private static final class HealthSkillRuntimeState {
        private float lowestHealth = Float.MAX_VALUE;
        private boolean recoveringFromNearDeath;
        private final Deque<class_2960> foodHistory = new ArrayDeque<class_2960>();
        private long lastFoodRewardTick = -2305843009213693952L;
        private class_243 lastPosition;
        private double pendingSprintDistance;
        private double sprintWarmupDistance;

        private HealthSkillRuntimeState() {
        }

        private static HealthSkillRuntimeState create(class_3222 player) {
            HealthSkillRuntimeState state = new HealthSkillRuntimeState();
            state.lastPosition = player.method_19538();
            return state;
        }
    }
}

