/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1496
 *  net.minecraft.class_1657
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_5134
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1496;
import net.minecraft.class_1657;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_5134;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.entity.CartEntity;
import trep.got.entity.DestrierEntity;
import trep.got.entity.GotDonkeyEntity;
import trep.got.entity.PackhorseEntity;
import trep.got.entity.RounceyEntity;
import trep.got.entity.SumpterEntity;
import trep.got.entity.TwoHorseCartEntity;

@Mixin(value={class_1496.class})
public abstract class AbstractHorseEntityMixin {
    @Unique
    private static final float AGOB_MIN_FORWARD_SPEED = 1.0f;
    @Unique
    private static final float AGOB_MAX_FORWARD_SPEED = 1.5f;
    @Unique
    private static final float AGOB_REVERSE_SPEED = -0.35f;
    @Unique
    private static final float AGOB_STARTUP_SECONDS = 0.35f;
    @Unique
    private static final float AGOB_COAST_STOP_SECONDS = 1.8f;
    @Unique
    private static final float AGOB_BRAKE_STOP_SECONDS = 0.7f;
    @Unique
    private static final float AGOB_MAX_TURN_RATE = 4.5f;
    @Unique
    private static final float AGOB_TURN_ACCEL = 0.8f;
    @Unique
    private static final float AGOB_TURN_DAMPING = 0.65f;
    @Unique
    private static final float AGOB_DISMOUNT_VELOCITY_FACTOR = 0.9f;
    @Unique
    private float agob$currentSpeedFactor;
    @Unique
    private float agob$turnVelocity;
    @Unique
    private boolean agob$playerControlledLastTick;

    @Inject(method={"method_49489"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$useSmoothHorseTurning(class_1309 controllingPassenger, CallbackInfoReturnable<class_241> cir) {
        if (!(controllingPassenger instanceof class_1657)) {
            return;
        }
        class_1657 player = (class_1657)controllingPassenger;
        class_1496 horse = (class_1496)this;
        float targetTurnVelocity = -player.field_6212 * 4.5f * this.agob$getTurnAuthority();
        this.agob$turnVelocity = class_3532.method_15348((float)this.agob$turnVelocity, (float)targetTurnVelocity, (float)0.8f);
        if (Math.abs(player.field_6212) < 0.01f) {
            this.agob$turnVelocity *= 0.65f;
            if (Math.abs(this.agob$turnVelocity) < 0.05f) {
                this.agob$turnVelocity = 0.0f;
            }
        }
        float yaw = horse.method_36454() + this.agob$turnVelocity;
        cir.setReturnValue((Object)new class_241(player.method_36455() * 0.5f, yaw));
    }

    @Inject(method={"method_49482"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$usePersistentHorseThrottle(class_1657 player, class_243 movementInput, CallbackInfoReturnable<class_243> cir) {
        class_1496 horse = (class_1496)this;
        this.agob$updateSpeedFactor(horse, player.field_6250);
        double forward = 0.0;
        if (this.agob$currentSpeedFactor > 0.01f) {
            forward = 1.0;
        } else if (this.agob$currentSpeedFactor < -0.01f) {
            forward = -1.0;
        }
        cir.setReturnValue((Object)new class_243(0.0, 0.0, forward));
    }

    @Inject(method={"method_49485"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$scaleHorseTopSpeed(class_1657 player, CallbackInfoReturnable<Float> cir) {
        class_1496 horse = (class_1496)this;
        float baseSpeed = (float)horse.method_45325(class_5134.field_23719);
        float factor = Math.max(Math.abs(this.agob$currentSpeedFactor), 0.0f);
        if (factor < 0.01f) {
            cir.setReturnValue((Object)Float.valueOf(0.0f));
            return;
        }
        cir.setReturnValue((Object)Float.valueOf(baseSpeed * factor));
    }

    @Inject(method={"method_5773"}, at={@At(value="TAIL")})
    private void agob$carryMomentumAfterDismount(CallbackInfo ci) {
        class_1496 horse = (class_1496)this;
        class_1309 controllingPassenger = horse.method_5642();
        boolean playerControlled = controllingPassenger instanceof class_1657;
        if (playerControlled) {
            this.agob$playerControlledLastTick = true;
            return;
        }
        if (!this.agob$playerControlledLastTick || Math.abs(this.agob$currentSpeedFactor) < 0.01f) {
            this.agob$playerControlledLastTick = false;
            this.agob$turnVelocity *= 0.8f;
            if (Math.abs(this.agob$turnVelocity) < 0.05f) {
                this.agob$turnVelocity = 0.0f;
            }
            return;
        }
        this.agob$currentSpeedFactor = class_3532.method_15348((float)this.agob$currentSpeedFactor, (float)0.0f, (float)this.agob$getCoastStep());
        if (Math.abs(this.agob$currentSpeedFactor) < 0.01f) {
            this.agob$currentSpeedFactor = 0.0f;
            this.agob$playerControlledLastTick = false;
            return;
        }
        class_243 facing = class_243.method_1030((float)0.0f, (float)horse.method_36454());
        double baseSpeed = horse.method_45325(class_5134.field_23719);
        double horizontalSpeed = baseSpeed * (double)this.agob$currentSpeedFactor * (double)0.9f;
        class_243 currentVelocity = horse.method_18798();
        class_243 desiredVelocity = new class_243(facing.field_1352 * horizontalSpeed, currentVelocity.field_1351, facing.field_1350 * horizontalSpeed);
        horse.method_18800(class_3532.method_16436((double)0.35, (double)currentVelocity.field_1352, (double)desiredVelocity.field_1352), currentVelocity.field_1351, class_3532.method_16436((double)0.35, (double)currentVelocity.field_1350, (double)desiredVelocity.field_1350));
    }

    @Inject(method={"method_5773"}, at={@At(value="TAIL")})
    private void agob$stabilizeAttachedCartHorse(CallbackInfo ci) {
        class_1496 horse = (class_1496)this;
        if (!CartEntity.isAttachedCartHorse((class_1297)horse) && !TwoHorseCartEntity.isAttachedCartHorse((class_1297)horse)) {
            return;
        }
        if (horse.method_5642() instanceof class_1657) {
            return;
        }
        float yaw = horse.method_36454();
        horse.method_5942().method_6340();
        horse.method_36457(0.0f);
        horse.method_5636(yaw);
        horse.method_5847(yaw);
        horse.field_6220 = yaw;
        horse.field_6259 = yaw;
        horse.field_6004 = 0.0f;
    }

    @Unique
    private void agob$updateSpeedFactor(class_1496 horse, float forwardInput) {
        if (forwardInput > 0.01f) {
            float startupStep = 1.0f / Math.max(1.0f, 7.0f);
            this.agob$currentSpeedFactor = this.agob$currentSpeedFactor < 1.0f ? class_3532.method_15348((float)this.agob$currentSpeedFactor, (float)1.0f, (float)startupStep) : class_3532.method_15348((float)this.agob$currentSpeedFactor, (float)1.5f, (float)this.agob$getAccelerationStep(horse));
            return;
        }
        if (forwardInput < -0.01f) {
            this.agob$currentSpeedFactor = class_3532.method_15348((float)this.agob$currentSpeedFactor, (float)0.0f, (float)this.agob$getBrakeStep());
            if (Math.abs(this.agob$currentSpeedFactor) < 0.05f) {
                this.agob$currentSpeedFactor = class_3532.method_15348((float)this.agob$currentSpeedFactor, (float)-0.35f, (float)this.agob$getReverseStep());
            }
            return;
        }
        this.agob$currentSpeedFactor = class_3532.method_15348((float)this.agob$currentSpeedFactor, (float)0.0f, (float)this.agob$getCoastStep());
    }

    @Unique
    private float agob$getTurnAuthority() {
        float absSpeed = Math.abs(this.agob$currentSpeedFactor);
        return class_3532.method_15363((float)(0.5f + absSpeed * 0.4f), (float)0.45f, (float)1.0f);
    }

    @Unique
    private float agob$getAccelerationStep(class_1496 horse) {
        float rampSeconds = this.agob$getRampSeconds(horse);
        return 0.5f / Math.max(1.0f, rampSeconds * 20.0f);
    }

    @Unique
    private float agob$getCoastStep() {
        return 1.5f / Math.max(1.0f, 36.0f);
    }

    @Unique
    private float agob$getBrakeStep() {
        return 1.5f / Math.max(1.0f, 14.0f);
    }

    @Unique
    private float agob$getReverseStep() {
        return Math.abs(-0.35f) / 12.0f;
    }

    @Unique
    private float agob$getRampSeconds(class_1496 horse) {
        if (horse instanceof DestrierEntity) {
            return 2.0f;
        }
        if (horse instanceof SumpterEntity) {
            return 3.0f;
        }
        if (horse instanceof PackhorseEntity || horse instanceof GotDonkeyEntity) {
            return 5.0f;
        }
        if (horse instanceof RounceyEntity) {
            return 2.5f;
        }
        return 3.0f;
    }
}

