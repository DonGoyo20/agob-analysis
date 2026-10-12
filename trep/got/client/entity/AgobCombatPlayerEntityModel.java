/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.MapMaker
 *  dev.kosmx.playerAnim.api.IPlayable
 *  dev.kosmx.playerAnim.api.layered.IAnimation
 *  dev.kosmx.playerAnim.api.layered.ModifierLayer
 *  dev.kosmx.playerAnim.impl.animation.AnimationApplier
 *  dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1309
 *  net.minecraft.class_2960
 *  net.minecraft.class_3532
 *  net.minecraft.class_591
 *  net.minecraft.class_630
 */
package trep.got.client.entity;

import com.google.common.collect.MapMaker;
import dev.kosmx.playerAnim.api.IPlayable;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.impl.animation.AnimationApplier;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import java.util.EnumMap;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_591;
import net.minecraft.class_630;
import trep.got.GotMod;
import trep.got.client.entity.NpcPlayerInteractionAnimations;
import trep.got.compat.BetterCombatCompat;
import trep.got.entity.AbstractKingdomHumanoidEntity;
import trep.got.entity.AbstractKingdomNpcEntity;

@Environment(value=EnvType.CLIENT)
public final class AgobCombatPlayerEntityModel<T extends class_1309>
extends class_591<T> {
    private static final boolean DEBUG_MOB_ATTACK_ANIMATION_LOG = false;
    private static final long DEBUG_SUMMARY_WINDOW_MS = 10000L;
    private static final Map<class_1309, NpcAttackAnimationState> ENTITY_STATES = new MapMaker().weakKeys().makeMap();

    public AgobCombatPlayerEntityModel(class_630 root, boolean thinArms) {
        super(root, thinArms);
    }

    public void method_17087(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.method_17087(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        if (BetterCombatCompat.isLoaded()) {
            this.applyBetterCombatAnimation(entity);
        }
        this.applyNpcTalkingAnimation(entity, animationProgress);
        NpcPlayerInteractionAnimations.apply(this, entity, animationProgress - (float)((class_1309)entity).field_6012);
    }

    private void applyNpcTalkingAnimation(T entity, float animationProgress) {
        AbstractKingdomNpcEntity npc;
        if (!(entity instanceof AbstractKingdomNpcEntity) || !(npc = (AbstractKingdomNpcEntity)entity).agob$isNpcTalking()) {
            return;
        }
        int variant = npc.agob$getNpcTalkAnimationVariant();
        float phase = animationProgress * (0.32f + (float)(variant % 3) * 0.035f) + (float)entity.method_5628() * 0.71f + (float)variant * 1.37f;
        float handPhase = phase + (variant >= 2 ? 1.5707964f : 0.0f);
        float headBob = class_3532.method_15374((float)phase) * 0.055f + class_3532.method_15374((float)(phase * 0.43f)) * 0.025f;
        float rightGesture = class_3532.method_15374((float)handPhase) * 0.16f;
        float leftGesture = class_3532.method_15374((float)(handPhase + (float)Math.PI)) * 0.16f;
        float openness = 0.36f + class_3532.method_15374((float)(phase * 0.5f)) * 0.06f;
        this.field_3398.field_3654 += headBob;
        this.field_3398.field_3675 += class_3532.method_15374((float)(phase * 0.37f)) * 0.035f;
        this.field_3401.field_3654 = this.field_3401.field_3654 * 0.35f - 0.42f + rightGesture;
        this.field_27433.field_3654 = this.field_27433.field_3654 * 0.35f - 0.42f + leftGesture;
        this.field_3401.field_3675 += openness;
        this.field_27433.field_3675 -= openness;
        this.field_3401.field_3674 += 0.16f + class_3532.method_15374((float)(handPhase * 0.7f)) * 0.08f;
        this.field_27433.field_3674 -= 0.16f + class_3532.method_15374((float)((handPhase + (float)Math.PI) * 0.7f)) * 0.08f;
        this.field_3394.method_17138(this.field_3398);
        this.field_3486.method_17138(this.field_3401);
        this.field_3484.method_17138(this.field_27433);
    }

    private void applyBetterCombatAnimation(T entity) {
        NpcAttackAnimationState state = ENTITY_STATES.computeIfAbsent((class_1309)entity, ignored -> new NpcAttackAnimationState());
        state.tickIfNeeded(((class_1309)entity).field_6012);
        AttackAnimationStatus status = state.startIfNeeded((class_1309)entity);
        state.applyTo(this);
        if (entity.method_31481()) {
            ENTITY_STATES.remove(entity);
        }
    }

    private void recordDebug(T entity, NpcAttackAnimationState state, AttackAnimationStatus status) {
    }

    @Environment(value=EnvType.CLIENT)
    private static final class NpcAttackAnimationState {
        private final ModifierLayer<IAnimation> animationLayer = new ModifierLayer();
        private final AnimationApplier animationApplier = new AnimationApplier(this.animationLayer);
        private int lastAge = Integer.MIN_VALUE;
        private int lastSequence = Integer.MIN_VALUE;

        private NpcAttackAnimationState() {
        }

        void tickIfNeeded(int entityAge) {
            if (entityAge == this.lastAge) {
                return;
            }
            this.lastAge = entityAge;
            this.animationLayer.tick();
        }

        AttackAnimationStatus startIfNeeded(class_1309 entity) {
            if (!BetterCombatCompat.isLoaded()) {
                return AttackAnimationStatus.BETTER_COMBAT_NOT_LOADED;
            }
            if (!(entity instanceof AbstractKingdomHumanoidEntity)) {
                return AttackAnimationStatus.NO_ANIMATION_PROFILE;
            }
            AbstractKingdomHumanoidEntity humanoid = (AbstractKingdomHumanoidEntity)entity;
            int currentSequence = humanoid.agob$getAttackAnimationSequence();
            boolean attackStarted = currentSequence != this.lastSequence;
            this.lastSequence = currentSequence;
            if (!attackStarted) {
                return this.animationLayer.isActive() ? AttackAnimationStatus.BETTER_COMBAT_ACTIVE : AttackAnimationStatus.NO_SWING_TRIGGER;
            }
            String attackAnimationId = humanoid.agob$getAttackAnimationId();
            if (attackAnimationId == null || attackAnimationId.isBlank()) {
                return AttackAnimationStatus.NO_ANIMATION_PROFILE;
            }
            class_2960 animationId = class_2960.method_12829((String)attackAnimationId);
            if (animationId == null) {
                return AttackAnimationStatus.NO_ANIMATION_PROFILE;
            }
            return PlayerAnimationRegistry.getAnimationOptional((class_2960)animationId).map(IPlayable::playAnimation).map(animation -> {
                this.animationLayer.setAnimation((IAnimation)animation);
                return AttackAnimationStatus.BETTER_COMBAT_ACTIVE;
            }).orElse(AttackAnimationStatus.MISSING_ANIMATION_ASSET);
        }

        void applyTo(class_591<?> model) {
            if (!this.animationLayer.isActive()) {
                return;
            }
            this.update("head", model.field_3398);
            this.update("torso", model.field_3391);
            this.update("rightArm", model.field_3401);
            this.update("leftArm", model.field_27433);
            this.update("rightLeg", model.field_3392);
            this.update("leftLeg", model.field_3397);
            model.field_3394.method_17138(model.field_3398);
            model.field_3483.method_17138(model.field_3391);
            model.field_3486.method_17138(model.field_3401);
            model.field_3484.method_17138(model.field_27433);
            model.field_3479.method_17138(model.field_3392);
            model.field_3482.method_17138(model.field_3397);
        }

        private void update(String partName, class_630 part) {
            this.animationApplier.updatePart(partName, part);
        }

        AttackAnimationStatus resolveVisibleStatus(AttackAnimationStatus status) {
            if (this.animationLayer.isActive()) {
                return AttackAnimationStatus.BETTER_COMBAT_ACTIVE;
            }
            return status;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private static enum AttackAnimationStatus {
        BETTER_COMBAT_ACTIVE,
        DEFAULT_SWING_ONLY,
        NO_SWING_TRIGGER,
        NO_ANIMATION_PROFILE,
        MISSING_ANIMATION_ASSET,
        BETTER_COMBAT_NOT_LOADED;

    }

    @Environment(value=EnvType.CLIENT)
    private static final class Summary {
        private int count;
        private String sample;

        private Summary() {
        }
    }

    @Environment(value=EnvType.CLIENT)
    private static final class AttackAnimationDebugTracker {
        private static final EnumMap<AttackAnimationStatus, Summary> SUMMARIES = new EnumMap(AttackAnimationStatus.class);
        private static long nextLogAtMs = System.currentTimeMillis() + 10000L;

        private AttackAnimationDebugTracker() {
        }

        static void record(class_1309 entity, AttackAnimationStatus status) {
            Summary summary = SUMMARIES.computeIfAbsent(status, ignored -> new Summary());
            ++summary.count;
            if (summary.sample == null) {
                summary.sample = entity.method_5864().method_35050() + "#" + entity.method_5628();
            }
            AttackAnimationDebugTracker.flushIfNeeded();
        }

        private static void flushIfNeeded() {
            long now = System.currentTimeMillis();
            if (now < nextLogAtMs || SUMMARIES.isEmpty()) {
                return;
            }
            StringBuilder builder = new StringBuilder("[mob-attack-animation-debug] windowMs=").append(10000L).append(" summaries=");
            boolean first = true;
            for (Map.Entry<AttackAnimationStatus, Summary> entry : SUMMARIES.entrySet()) {
                if (!first) {
                    builder.append(" | ");
                }
                first = false;
                builder.append(entry.getKey().name().toLowerCase()).append(":count=").append(entry.getValue().count).append(",sample=").append(entry.getValue().sample);
            }
            GotMod.LOGGER.info(builder.toString());
            SUMMARIES.clear();
            nextLogAtMs = now + 10000L;
        }
    }
}

