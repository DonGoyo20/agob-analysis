/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_4738
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1496
 *  net.minecraft.class_1542
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1928
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_3218
 *  net.minecraft.class_3532
 *  net.minecraft.class_4048
 *  net.minecraft.class_4050
 *  net.minecraft.class_5134
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1496;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1928;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3218;
import net.minecraft.class_3532;
import net.minecraft.class_4048;
import net.minecraft.class_4050;
import net.minecraft.class_5134;
import org.jetbrains.annotations.Nullable;
import trep.got.entity.AbstractKingdomCartRiderNpcEntity;
import trep.got.entity.CartEntity;
import trep.got.entity.CartGroundSupport;
import trep.got.entity.PartyHorseEntity;
import trep.got.registry.GotEntities;
import trep.got.registry.GotItems;
import trep.got.vehicle.platform.PlatformEntity;
import trep.got.vehicle.platform.PlatformHull;
import trep.got.vehicle.platform.PlatformLocalBox;
import trep.got.vehicle.platform.PlatformRuntime;
import trep.got.vehicle.platform.PlatformTransform;

public class TwoHorseCartEntity
extends class_1297
implements PlatformEntity {
    private static final class_2940<Optional<UUID>> LEFT_HORSE_UUID = class_2945.method_12791(TwoHorseCartEntity.class, (class_2941)class_2943.field_13313);
    private static final class_2940<Optional<UUID>> RIGHT_HORSE_UUID = class_2945.method_12791(TwoHorseCartEntity.class, (class_2941)class_2943.field_13313);
    private static final class_2940<Float> HEALTH = class_2945.method_12791(TwoHorseCartEntity.class, (class_2941)class_2943.field_13320);
    private static final Set<UUID> ATTACHED_CART_HORSE_UUIDS = ConcurrentHashMap.newKeySet();
    private static final float MAX_HEALTH = 30.0f;
    private static final double DRAWBAR_HITCH_OFFSET = 2.15;
    private static final double DRAWBAR_HITCH_HEIGHT = 0.72;
    private static final double HORSE_HITCH_BACKSET = 0.9;
    private static final double HORSE_SIDE_OFFSET = 1.15;
    private static final class_243 DRIVER_SEAT_OFFSET = new class_243(0.0, 1.0, -0.35);
    private static final class_243 PLAYER_SEAT_OFFSET = new class_243(0.0, 1.0, 0.55);
    private static final double WHEEL_RADIUS = 0.25;
    private static final double FRONT_AXLE_OFFSET = -0.65;
    private static final double BACK_AXLE_OFFSET = 0.85;
    private static final double AXLE_Y_OFFSET = 0.28;
    private static final double MAX_STEP_HEIGHT = 1.2;
    private static final double SUPPORT_Y = 0.42;
    private static final double DRIVER_TURN_SCALE = 20.0;
    private static final double DRIVER_REVERSE_SPEED_FACTOR = 0.45;
    private static final PlatformHull PLATFORM_HULL = new PlatformHull(List.of(new PlatformLocalBox(-0.9, 0.42, -0.95, 0.9, 0.56, 1.15, 3), new PlatformLocalBox(-1.02, 0.42, -0.95, -0.88, 1.48, 1.15, 2), new PlatformLocalBox(0.88, 0.42, -0.95, 1.02, 1.48, 1.15, 2), new PlatformLocalBox(-0.88, 0.42, -0.95, 0.88, 1.48, -0.82, 2), new PlatformLocalBox(-0.88, 0.42, 1.02, 0.88, 1.48, 1.15, 2)));
    private final PlatformRuntime platformRuntime = new PlatformRuntime();
    private class_243 lastWheelSamplePos = class_243.field_1353;
    private UUID registeredLeftHorseUuid;
    private UUID registeredRightHorseUuid;
    private float wheelRotation;
    private float prevWheelRotation;
    private class_243 lastResolvedTeamForward = new class_243(0.0, 0.0, 1.0);
    private float lastSteerYaw;
    private int clientTrackedUpdateIgnoreTicks;

    public TwoHorseCartEntity(class_1299<? extends TwoHorseCartEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.field_23807 = true;
    }

    public TwoHorseCartEntity(class_1937 world, double x, double y, double z) {
        this(GotEntities.CART_2HORSE, world);
        this.method_5808(x, y, z, this.method_36454(), this.method_36455());
    }

    protected void method_5693(class_2945.class_9222 builder) {
        builder.method_56912(LEFT_HORSE_UUID, Optional.empty());
        builder.method_56912(RIGHT_HORSE_UUID, Optional.empty());
        builder.method_56912(HEALTH, (Object)Float.valueOf(30.0f));
    }

    public void method_5773() {
        super.method_5773();
        this.prevWheelRotation = this.wheelRotation;
        this.syncAttachedHorseRegistry();
        if (!this.method_37908().field_9236) {
            this.tickServerMovement();
        } else {
            this.tickClientMovement();
        }
        this.advanceWheelRotation();
        this.platformRuntime.update(this, this.createPlatformTransform(), PLATFORM_HULL);
    }

    private void tickClientMovement() {
        if (this.clientTrackedUpdateIgnoreTicks > 0) {
            --this.clientTrackedUpdateIgnoreTicks;
        }
        if (this.isVoyageCart() || !this.hasRequiredDraftTeam()) {
            return;
        }
        if (this.isLocalPlayerDrivingDraftTeam()) {
            this.clientTrackedUpdateIgnoreTicks = 8;
            this.driveDraftTeam();
            return;
        }
        if (this.clientTrackedUpdateIgnoreTicks > 0) {
            this.syncCartToDraftTeam(this.getAttachedHorse(0), this.getAttachedHorse(1), false);
        }
    }

    private void tickServerMovement() {
        class_1496 leftHorse = this.getAttachedHorse(0);
        class_1496 rightHorse = this.getAttachedHorse(1);
        if (leftHorse == null && rightHorse == null) {
            this.settleOnGround();
            return;
        }
        if (leftHorse != null && !leftHorse.method_5805()) {
            this.detachHorse(0);
            leftHorse = null;
        }
        if (rightHorse != null && !rightHorse.method_5805()) {
            this.detachHorse(1);
            rightHorse = null;
        }
        if (leftHorse == null && rightHorse == null) {
            this.settleOnGround();
            return;
        }
        if (!this.hasRequiredDraftTeam()) {
            this.syncPartialDraftTeam(leftHorse, rightHorse);
            return;
        }
        if (!this.isVoyageCart()) {
            if (!this.driveDraftTeam()) {
                this.syncIdleDraftTeam(leftHorse, rightHorse);
            }
            return;
        }
        this.syncCartToDraftTeam(leftHorse, rightHorse, false);
    }

    private void syncPartialDraftTeam(@Nullable class_1496 leftHorse, @Nullable class_1496 rightHorse) {
        this.settleOnGround();
        class_243 cartBack = TwoHorseCartEntity.horizontalForward(this.method_36454());
        class_243 teamForward = cartBack.method_1021(-1.0).method_1029();
        class_243 teamRight = new class_243(teamForward.field_1350, 0.0, -teamForward.field_1352).method_1029();
        class_243 hitchCenter = this.method_19538().method_1020(cartBack.method_1021(2.15));
        if (leftHorse != null && leftHorse.method_5805()) {
            this.updateAttachedHorseIdleState(leftHorse, hitchCenter, teamForward, teamRight, -1);
        }
        if (rightHorse != null && rightHorse.method_5805()) {
            this.updateAttachedHorseIdleState(rightHorse, hitchCenter, teamForward, teamRight, 1);
        }
    }

    private void syncIdleDraftTeam(@Nullable class_1496 leftHorse, @Nullable class_1496 rightHorse) {
        this.settleOnGround();
        class_243 cartBack = TwoHorseCartEntity.horizontalForward(this.method_36454());
        class_243 teamForward = cartBack.method_1021(-1.0).method_1029();
        class_243 teamRight = new class_243(teamForward.field_1350, 0.0, -teamForward.field_1352).method_1029();
        class_243 hitchCenter = this.method_19538().method_1020(cartBack.method_1021(2.15));
        this.updateAttachedHorseIdleState(leftHorse, hitchCenter, teamForward, teamRight, -1);
        this.updateAttachedHorseIdleState(rightHorse, hitchCenter, teamForward, teamRight, 1);
    }

    private void syncCartToDraftTeam(@Nullable class_1496 leftHorse, @Nullable class_1496 rightHorse) {
        this.syncCartToDraftTeam(leftHorse, rightHorse, true);
    }

    private void syncCartToDraftTeam(@Nullable class_1496 leftHorse, @Nullable class_1496 rightHorse, boolean holdHorsesIdle) {
        double backGround;
        float previousYaw = this.method_36454();
        class_243 previousPos = this.method_19538();
        class_243 currentCenter = this.method_19538();
        class_243 teamCenter = this.resolveTeamCenter(leftHorse, rightHorse);
        if (teamCenter == null) {
            this.settleOnGround();
            return;
        }
        class_243 teamForward = this.resolveTeamForward(leftHorse, rightHorse, !this.isVoyageCart());
        class_243 teamRight = new class_243(teamForward.field_1350, 0.0, -teamForward.field_1352).method_1029();
        class_243 hitchCenter = teamCenter.method_1020(teamForward.method_1021(0.9));
        class_243 cartBack = new class_243(currentCenter.field_1352 - hitchCenter.field_1352, 0.0, currentCenter.field_1350 - hitchCenter.field_1350);
        cartBack = cartBack.method_1027() < 1.0E-5 ? TwoHorseCartEntity.horizontalForward(this.method_36454()) : cartBack.method_1029();
        float cartYaw = TwoHorseCartEntity.yawFromBackVector(cartBack);
        class_243 targetCenter = hitchCenter.method_1019(cartBack.method_1021(2.15));
        class_243 frontSample = targetCenter.method_1019(cartBack.method_1021(-0.65));
        class_243 backSample = targetCenter.method_1019(cartBack.method_1021(0.85));
        double frontGround = this.sampleGroundHeight(frontSample);
        double heightDelta = frontGround - (backGround = this.sampleGroundHeight(backSample));
        if (Math.abs(heightDelta) > 1.2) {
            backGround = frontGround - Math.copySign(1.2, heightDelta);
        }
        double pitchRadians = Math.atan2(frontGround - backGround, Math.abs(-1.5));
        double cartCenterGround = (frontGround + backGround) * 0.5;
        double currentY = cartCenterGround + 0.28;
        class_243 nextPos = new class_243(targetCenter.field_1352, currentY, targetCenter.field_1350);
        this.method_18799(nextPos.method_1020(previousPos));
        this.method_5814(nextPos.field_1352, nextPos.field_1351, nextPos.field_1350);
        this.method_36456(cartYaw);
        this.method_36457((float)(-Math.toDegrees(pitchRadians)));
        this.logVoyageRotationAnomaly(previousYaw, cartYaw, leftHorse, rightHorse);
        this.field_5982 = this.method_36454();
        this.field_6004 = this.method_36455();
        if (holdHorsesIdle) {
            this.updateAttachedHorseIdleState(leftHorse, hitchCenter, teamForward, teamRight, -1);
            this.updateAttachedHorseIdleState(rightHorse, hitchCenter, teamForward, teamRight, 1);
        }
    }

    public void refreshDraftFormation() {
        if (!this.method_37908().field_9236) {
            this.tickServerMovement();
            this.refreshPlatformAfterExternalTransform();
        }
    }

    public boolean steerDraftTeam(float desiredYaw, double step) {
        return this.steerDraftTeam(desiredYaw, step, true);
    }

    private boolean steerDraftTeam(float desiredYaw, double step, boolean refreshAfterExternalTransform) {
        class_1496 leftHorse = this.getAttachedHorse(0);
        class_1496 rightHorse = this.getAttachedHorse(1);
        if (leftHorse == null || rightHorse == null || step <= 0.0) {
            return false;
        }
        class_243 teamCenter = leftHorse.method_19538().method_1021(0.5).method_1019(rightHorse.method_19538().method_1021(0.5));
        class_243 direction = TwoHorseCartEntity.horizontalForward(desiredYaw);
        this.lastSteerYaw = desiredYaw;
        this.lastResolvedTeamForward = direction;
        class_243 right = TwoHorseCartEntity.horizontalRight(desiredYaw);
        class_243 nextTeamCenter = teamCenter.method_1019(direction.method_1021(step));
        class_243 leftPos = nextTeamCenter.method_1019(right.method_1021(-1.15));
        class_243 rightPos = nextTeamCenter.method_1019(right.method_1021(1.15));
        double leftY = this.sampleGroundHeight(leftPos);
        double rightY = this.sampleGroundHeight(rightPos);
        leftY = TwoHorseCartEntity.clampHorseStep(leftHorse.method_23318(), leftY);
        rightY = TwoHorseCartEntity.clampHorseStep(rightHorse.method_23318(), rightY);
        this.positionDraftHorse(leftHorse, leftPos.field_1352, leftY, leftPos.field_1350, desiredYaw, step, direction);
        this.positionDraftHorse(rightHorse, rightPos.field_1352, rightY, rightPos.field_1350, desiredYaw, step, direction);
        this.syncCartToDraftTeam(leftHorse, rightHorse, false);
        if (refreshAfterExternalTransform) {
            this.refreshPlatformAfterExternalTransform();
        }
        return true;
    }

    private void refreshPlatformAfterExternalTransform() {
        this.platformRuntime.refresh(this, this.createPlatformTransform(), PLATFORM_HULL);
    }

    private boolean driveDraftTeam() {
        double step;
        class_1309 controllingPassenger = this.method_5642();
        if (controllingPassenger == null || !this.isDriverPassenger((class_1297)controllingPassenger)) {
            return false;
        }
        double forwardInput = class_3532.method_15363((float)controllingPassenger.field_6250, (float)-1.0f, (float)1.0f);
        double sidewaysInput = class_3532.method_15363((float)controllingPassenger.field_6212, (float)-1.0f, (float)1.0f);
        if (Math.abs(forwardInput) < 0.01 && Math.abs(sidewaysInput) < 0.01) {
            return false;
        }
        float desiredYaw = controllingPassenger.method_36454() - (float)(sidewaysInput * 20.0);
        double draftSpeed = this.getDraftTravelSpeed();
        double d = step = forwardInput >= 0.0 ? draftSpeed * forwardInput : draftSpeed * 0.45 * -forwardInput;
        if (forwardInput < 0.0) {
            desiredYaw = class_3532.method_15393((float)(desiredYaw + 180.0f));
        }
        return this.steerDraftTeam(desiredYaw, step, false);
    }

    public void method_5759(double x, double y, double z, float yaw, float pitch, int interpolationSteps) {
        if (this.method_37908().field_9236 && this.clientTrackedUpdateIgnoreTicks > 0) {
            return;
        }
        super.method_5759(x, y, z, yaw, pitch, interpolationSteps);
    }

    private void updateAttachedHorseIdleState(class_1496 horse, class_243 hitchCenter, class_243 teamForward, class_243 teamRight, int sideSign) {
        if (horse == null || horse.method_5782()) {
            return;
        }
        class_243 desiredHorsePos = hitchCenter.method_1019(teamRight.method_1021(1.15 * (double)sideSign)).method_1019(teamForward.method_1021(0.9));
        double ground = this.sampleGroundHeight(desiredHorsePos);
        horse.method_5942().method_6340();
        horse.method_18799(class_243.field_1353);
        horse.method_5814(desiredHorsePos.field_1352, ground, desiredHorsePos.field_1350);
        float horseYaw = TwoHorseCartEntity.yawFromBackVector(teamForward);
        horse.method_36456(horseYaw);
        horse.method_36457(0.0f);
        horse.method_5636(horse.method_36454());
        horse.method_5847(horse.method_36454());
        horse.field_5982 = horse.method_36454();
        horse.field_6220 = horse.method_43078();
        horse.field_6259 = horse.method_5791();
        horse.field_6004 = 0.0f;
    }

    private void positionDraftHorse(class_1496 horse, double x, double y, double z, float yaw, double speed, class_243 direction) {
        horse.method_5942().method_6340();
        horse.method_18800(direction.field_1352 * speed, 0.0, direction.field_1350 * speed);
        horse.method_5808(x, y, z, yaw, 0.0f);
        horse.method_5636(yaw);
        horse.method_5847(yaw);
        horse.field_5982 = yaw;
        horse.field_6220 = yaw;
        horse.field_6259 = yaw;
    }

    private class_243 resolveTeamForward(@Nullable class_1496 leftHorse, @Nullable class_1496 rightHorse, boolean allowHorseDerivedFacing) {
        class_243 candidate;
        if (!allowHorseDerivedFacing && this.lastResolvedTeamForward.method_1027() > 1.0E-5) {
            return this.lastResolvedTeamForward.method_1029();
        }
        class_243 combinedVelocity = TwoHorseCartEntity.horizontalVelocity(leftHorse).method_1019(TwoHorseCartEntity.horizontalVelocity(rightHorse));
        class_243 class_2432 = candidate = combinedVelocity.method_1027() > 1.0E-4 ? combinedVelocity.method_1029() : TwoHorseCartEntity.horizontalFacing(leftHorse).method_1019(TwoHorseCartEntity.horizontalFacing(rightHorse));
        if (candidate.method_1027() < 1.0E-5) {
            candidate = this.lastResolvedTeamForward.method_1027() > 1.0E-5 ? this.lastResolvedTeamForward : TwoHorseCartEntity.horizontalForward(this.method_36454() + 180.0f);
        }
        candidate = candidate.method_1029();
        if (this.lastResolvedTeamForward.method_1027() > 1.0E-5 && combinedVelocity.method_1027() < 1.0E-4 && candidate.method_1026(this.lastResolvedTeamForward) < -0.5) {
            candidate = this.lastResolvedTeamForward;
        }
        this.lastResolvedTeamForward = candidate;
        return candidate;
    }

    private static class_243 horizontalVelocity(@Nullable class_1496 horse) {
        if (horse == null) {
            return class_243.field_1353;
        }
        class_243 velocity = horse.method_18798();
        return new class_243(velocity.field_1352, 0.0, velocity.field_1350);
    }

    private static class_243 horizontalFacing(@Nullable class_1496 horse) {
        return horse == null ? class_243.field_1353 : TwoHorseCartEntity.horizontalForward(horse.method_43078());
    }

    private static double getHorseTravelSpeed(@Nullable class_1496 horse) {
        return horse == null ? 0.0 : horse.method_45325(class_5134.field_23719);
    }

    private static double clampHorseStep(double currentY, double sampledY) {
        double delta = sampledY - currentY;
        if (Math.abs(delta) <= 1.1) {
            return sampledY;
        }
        return currentY + Math.copySign(1.1, delta);
    }

    @Nullable
    private class_243 resolveTeamCenter(@Nullable class_1496 leftHorse, @Nullable class_1496 rightHorse) {
        if (leftHorse != null && rightHorse != null) {
            return leftHorse.method_19538().method_1021(0.5).method_1019(rightHorse.method_19538().method_1021(0.5));
        }
        if (leftHorse != null) {
            return leftHorse.method_19538();
        }
        if (rightHorse != null) {
            return rightHorse.method_19538();
        }
        return null;
    }

    public class_243 getDraftTeamCenterPos() {
        class_243 teamCenter = this.resolveTeamCenter(this.getAttachedHorse(0), this.getAttachedHorse(1));
        return teamCenter != null ? teamCenter : this.method_19538();
    }

    public double getDraftTravelSpeed() {
        return Math.max(0.1, TwoHorseCartEntity.getHorseTravelSpeed(this.getAttachedHorse(0)) + TwoHorseCartEntity.getHorseTravelSpeed(this.getAttachedHorse(1)));
    }

    private void settleOnGround() {
        double ground = this.sampleGroundHeight(this.method_19538());
        this.method_5814(this.method_23317(), ground + 0.28, this.method_23321());
        this.method_36457(0.0f);
        this.field_6004 = 0.0f;
    }

    private void advanceWheelRotation() {
        class_243 currentPos = this.method_19538();
        if (this.field_6012 == 1 || this.lastWheelSamplePos.equals((Object)class_243.field_1353)) {
            this.lastWheelSamplePos = currentPos;
            return;
        }
        class_243 delta = currentPos.method_1020(this.lastWheelSamplePos);
        double signedDistance = delta.method_1026(TwoHorseCartEntity.horizontalForward(class_3532.method_15393((float)(this.method_36454() + 180.0f))));
        if (Math.abs(signedDistance) > 1.0E-4) {
            this.wheelRotation = TwoHorseCartEntity.wrapAngle(this.wheelRotation + (float)(signedDistance / 0.25));
        }
        this.lastWheelSamplePos = currentPos;
    }

    public float getWheelRotation(float tickDelta) {
        return class_3532.method_16439((float)tickDelta, (float)this.prevWheelRotation, (float)this.wheelRotation);
    }

    public class_1269 method_5688(class_1657 player, class_1268 hand) {
        class_1799 heldStack = player.method_5998(hand);
        if (heldStack.method_31574(class_1802.field_8719)) {
            if (this.method_37908().field_9236) {
                return class_1269.field_5812;
            }
            if (this.hasRequiredDraftTeam()) {
                return class_1269.field_5811;
            }
            class_1496 horse = this.findAttachableHorse(player);
            if (horse == null) {
                return class_1269.field_5811;
            }
            if (!this.attachHorse(horse)) {
                return class_1269.field_5811;
            }
            if (!horse.method_60953() && !player.method_7337()) {
                heldStack.method_7934(1);
            }
            if (this.hasRequiredDraftTeam()) {
                this.refreshDraftFormation();
            } else {
                this.syncPartialDraftTeam(this.getAttachedHorse(0), this.getAttachedHorse(1));
                this.refreshPlatformAfterExternalTransform();
            }
            return class_1269.field_21466;
        }
        if (hand == class_1268.field_5808 && heldStack.method_7960() && !player.method_21823()) {
            if (!this.hasRequiredDraftTeam()) {
                if (!this.method_37908().field_9236) {
                    player.method_7353((class_2561)class_2561.method_43470((String)"This cart needs two horses attached before it can be ridden.").method_27692(class_124.field_1061), true);
                }
                return class_1269.field_5812;
            }
            if (this.method_37908().field_9236) {
                return class_1269.field_5812;
            }
            return player.method_5804((class_1297)this) ? class_1269.field_21466 : class_1269.field_5811;
        }
        return class_1269.field_5811;
    }

    public class_1269 method_5664(class_1657 player, class_243 hitPos, class_1268 hand) {
        return this.method_5688(player, hand);
    }

    protected boolean method_5818(class_1297 passenger) {
        return this.method_5685().size() < 2;
    }

    public void method_5865(class_1297 passenger, class_1297.class_4738 positionUpdater) {
        int seatIndex = this.resolveSeatIndex(passenger);
        if (seatIndex < 0) {
            return;
        }
        class_243 seatPos = this.getSeatWorldPos(seatIndex);
        class_243 attachment = passenger.method_55668((class_1297)this);
        positionUpdater.accept(passenger, seatPos.field_1352 - attachment.field_1352, seatPos.field_1351 - attachment.field_1351, seatPos.field_1350 - attachment.field_1350);
        float seatYaw = seatIndex == 0 ? class_3532.method_15393((float)(this.method_36454() + 180.0f)) : this.method_36454();
        float yawDelta = class_3532.method_15393((float)(seatYaw - passenger.method_36454()));
        passenger.method_36456(passenger.method_36454() + yawDelta);
        passenger.method_5636(seatYaw);
        passenger.method_5847(passenger.method_5791() + yawDelta);
    }

    public class_243 method_24829(class_1309 passenger) {
        int seatIndex = Math.max(0, this.resolveSeatIndex((class_1297)passenger));
        class_243 seatPos = this.getSeatWorldPos(seatIndex);
        class_243 right = seatPos.method_1019(TwoHorseCartEntity.horizontalRight(this.method_36454()).method_1021(1.35));
        class_243 left = seatPos.method_1019(TwoHorseCartEntity.horizontalRight(this.method_36454()).method_1021(-1.35));
        return this.isDismountSpaceClear(passenger, left) ? left : right;
    }

    private class_1496 findAttachableHorse(class_1657 player) {
        class_238 searchBox = this.method_5829().method_1014(8.0);
        class_1496 best = null;
        boolean bestLeashed = false;
        double bestDistance = Double.MAX_VALUE;
        for (class_1496 horse2 : this.method_37908().method_8390(class_1496.class, searchBox, horse -> true)) {
            boolean leashedToPlayer;
            boolean bl = leashedToPlayer = horse2.method_60953() && horse2.method_60952() == player;
            if (!horse2.method_5805() || !horse2.method_6727() || !player.method_5667().equals(horse2.method_6139()) && !leashedToPlayer || horse2 instanceof PartyHorseEntity || CartEntity.isAttachedCartHorse((class_1297)horse2) || TwoHorseCartEntity.isAttachedCartHorse((class_1297)horse2)) continue;
            double distance = horse2.method_5858((class_1297)this);
            if (best != null && (!leashedToPlayer || bestLeashed) && (leashedToPlayer != bestLeashed || !(distance < bestDistance))) continue;
            best = horse2;
            bestLeashed = leashedToPlayer;
            bestDistance = distance;
        }
        return best;
    }

    private boolean attachHorse(class_1496 horse) {
        if (horse == null || !horse.method_5805()) {
            return false;
        }
        TwoHorseCartEntity existingTwoHorseCart = TwoHorseCartEntity.findCartAttachedToHorse(horse.method_5667(), this.method_37908(), this);
        if (existingTwoHorseCart != null) {
            existingTwoHorseCart.detachMatchingHorseAndDropLead(horse.method_5667());
        }
        CartEntity.detachCartForHorse(horse);
        if (horse.method_60953()) {
            horse.method_60970();
        }
        if (((Optional)this.field_6011.method_12789(LEFT_HORSE_UUID)).isEmpty()) {
            this.field_6011.method_12778(LEFT_HORSE_UUID, Optional.of(horse.method_5667()));
            return true;
        }
        if (((Optional)this.field_6011.method_12789(RIGHT_HORSE_UUID)).isEmpty()) {
            this.field_6011.method_12778(RIGHT_HORSE_UUID, Optional.of(horse.method_5667()));
            return true;
        }
        return false;
    }

    public void attachHorseEntity(class_1496 horse) {
        if (horse != null) {
            this.attachHorse(horse);
        }
    }

    public boolean hasRequiredDraftTeam() {
        return ((Optional)this.field_6011.method_12789(LEFT_HORSE_UUID)).isPresent() && ((Optional)this.field_6011.method_12789(RIGHT_HORSE_UUID)).isPresent();
    }

    private void detachHorse(int slot) {
        if (slot == 0) {
            this.field_6011.method_12778(LEFT_HORSE_UUID, Optional.empty());
            return;
        }
        this.field_6011.method_12778(RIGHT_HORSE_UUID, Optional.empty());
    }

    private void detachMatchingHorseAndDropLead(UUID horseUuid) {
        class_1496 horse = this.getAttachedHorseByUuid(horseUuid);
        if (horse == null) {
            return;
        }
        if (((Optional)this.field_6011.method_12789(LEFT_HORSE_UUID)).filter(horseUuid::equals).isPresent()) {
            this.detachHorse(0);
        }
        if (((Optional)this.field_6011.method_12789(RIGHT_HORSE_UUID)).filter(horseUuid::equals).isPresent()) {
            this.detachHorse(1);
        }
        if (horse.method_5805()) {
            horse.method_37908().method_8649((class_1297)new class_1542(horse.method_37908(), horse.method_23317(), horse.method_23318() + 0.5, horse.method_23321(), new class_1799((class_1935)class_1802.field_8719)));
        }
    }

    private void syncAttachedHorseRegistry() {
        this.syncAttachedHorseRegistryEntry(this.registeredLeftHorseUuid, ((Optional)this.field_6011.method_12789(LEFT_HORSE_UUID)).orElse(null), true);
        this.syncAttachedHorseRegistryEntry(this.registeredRightHorseUuid, ((Optional)this.field_6011.method_12789(RIGHT_HORSE_UUID)).orElse(null), false);
    }

    private void syncAttachedHorseRegistryEntry(UUID registeredUuid, UUID currentUuid, boolean leftSlot) {
        if (registeredUuid != null && !registeredUuid.equals(currentUuid)) {
            ATTACHED_CART_HORSE_UUIDS.remove(registeredUuid);
            if (leftSlot) {
                this.registeredLeftHorseUuid = null;
            } else {
                this.registeredRightHorseUuid = null;
            }
        }
        if (currentUuid != null && !currentUuid.equals(registeredUuid)) {
            ATTACHED_CART_HORSE_UUIDS.add(currentUuid);
            if (leftSlot) {
                this.registeredLeftHorseUuid = currentUuid;
            } else {
                this.registeredRightHorseUuid = currentUuid;
            }
        }
    }

    public static boolean isAttachedCartHorse(class_1297 entity) {
        return entity instanceof class_1496 && ATTACHED_CART_HORSE_UUIDS.contains(entity.method_5667());
    }

    public static boolean detachCartForHorse(class_1496 horse) {
        TwoHorseCartEntity cart = TwoHorseCartEntity.findCartAttachedToHorse(horse.method_5667(), horse.method_37908(), null);
        if (cart == null) {
            return false;
        }
        cart.detachMatchingHorseAndDropLead(horse.method_5667());
        return true;
    }

    private static TwoHorseCartEntity findCartAttachedToHorse(UUID horseUuid, class_1937 world, TwoHorseCartEntity excludedCart) {
        class_238 searchBox = new class_238(-3.0E7, (double)world.method_31607(), -3.0E7, 3.0E7, (double)world.method_31600(), 3.0E7);
        return world.method_8390(TwoHorseCartEntity.class, searchBox, cart -> {
            if (cart == excludedCart) return false;
            if (((Optional)cart.field_6011.method_12789(LEFT_HORSE_UUID)).filter(horseUuid::equals).isPresent()) return true;
            if (!((Optional)cart.field_6011.method_12789(RIGHT_HORSE_UUID)).filter(horseUuid::equals).isPresent()) return false;
            return true;
        }).stream().findFirst().orElse(null);
    }

    private class_1496 getAttachedHorse(int slot) {
        Optional horseUuid;
        Optional optional = horseUuid = slot == 0 ? (Optional)this.field_6011.method_12789(LEFT_HORSE_UUID) : (Optional)this.field_6011.method_12789(RIGHT_HORSE_UUID);
        if (horseUuid.isEmpty()) {
            return null;
        }
        class_1937 class_19372 = this.method_37908();
        if (class_19372 instanceof class_3218) {
            class_1496 horse2;
            class_3218 serverWorld = (class_3218)class_19372;
            class_1297 entity = serverWorld.method_14190((UUID)horseUuid.get());
            return entity instanceof class_1496 ? (horse2 = (class_1496)entity) : null;
        }
        return this.method_37908().method_8390(class_1496.class, this.method_5829().method_1014(48.0), horse -> horse.method_5667().equals(horseUuid.get())).stream().findFirst().orElse(null);
    }

    private class_1496 getAttachedHorseByUuid(UUID horseUuid) {
        class_1496 horse;
        class_1937 class_19372 = this.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return null;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        class_1297 entity = serverWorld.method_14190(horseUuid);
        return entity instanceof class_1496 ? (horse = (class_1496)entity) : null;
    }

    public class_1496 getAttachedHorseForRendering(int slot) {
        Optional horseUuid;
        Optional optional = horseUuid = slot == 0 ? (Optional)this.field_6011.method_12789(LEFT_HORSE_UUID) : (Optional)this.field_6011.method_12789(RIGHT_HORSE_UUID);
        if (horseUuid.isEmpty()) {
            return null;
        }
        return this.method_37908().method_8390(class_1496.class, this.method_5829().method_1014(24.0), horse -> horse.method_5667().equals(horseUuid.get())).stream().findFirst().orElse(null);
    }

    public class_243 getHorseHitchPos(int slot, float tickDelta) {
        double x = class_3532.method_16436((double)tickDelta, (double)this.field_6014, (double)this.method_23317());
        double y = class_3532.method_16436((double)tickDelta, (double)this.field_6036, (double)this.method_23318());
        double z = class_3532.method_16436((double)tickDelta, (double)this.field_5969, (double)this.method_23321());
        float yaw = class_3532.method_17821((float)tickDelta, (float)this.field_5982, (float)this.method_36454());
        class_243 hitchCenter = new class_243(x, y, z).method_1020(TwoHorseCartEntity.horizontalForward(yaw).method_1021(2.15)).method_1031(0.0, 0.72, 0.0);
        int sideSign = slot == 0 ? -1 : 1;
        return hitchCenter.method_1019(TwoHorseCartEntity.horizontalRight(yaw).method_1021(1.15 * (double)sideSign));
    }

    private class_243 getSeatWorldPos(int seatIndex) {
        class_243 seatOffset = seatIndex == 0 ? DRIVER_SEAT_OFFSET : PLAYER_SEAT_OFFSET;
        class_243 center = this.method_19538();
        float yaw = this.method_36454();
        class_243 forward = TwoHorseCartEntity.horizontalForward(yaw);
        class_243 right = TwoHorseCartEntity.horizontalRight(yaw);
        return center.method_1019(right.method_1021(seatOffset.field_1352)).method_1031(0.0, seatOffset.field_1351, 0.0).method_1019(forward.method_1021(seatOffset.field_1350));
    }

    private boolean isDismountSpaceClear(class_1309 passenger, class_243 pos) {
        class_238 box = passenger.method_18377(passenger.method_18376()).method_30757(pos);
        return this.method_37908().method_8587((class_1297)this, box);
    }

    private class_243 resolveHitchCenterFromHorse(class_1496 horse, int sideSign) {
        if (horse == null) {
            return null;
        }
        class_243 horseForward = TwoHorseCartEntity.horizontalForward(horse.method_43078());
        class_243 horseRight = TwoHorseCartEntity.horizontalRight(horse.method_43078());
        return horse.method_19538().method_1020(horseForward.method_1021(0.9)).method_1020(horseRight.method_1021(1.15 * (double)sideSign));
    }

    private double sampleGroundHeight(class_243 samplePos) {
        return CartGroundSupport.sampleGroundHeight(this.method_37908(), this.method_23318(), samplePos);
    }

    private PlatformTransform createPlatformTransform() {
        return new PlatformTransform(this.method_19538(), class_243.field_1353, 1.0, 0.0, this.method_36454(), this.method_36454());
    }

    private boolean hasNpcDriverPassenger() {
        return this.findNpcDriverPassenger() != null;
    }

    @Nullable
    private AbstractKingdomCartRiderNpcEntity findNpcDriverPassenger() {
        for (class_1297 passenger : this.method_5685()) {
            if (!(passenger instanceof AbstractKingdomCartRiderNpcEntity)) continue;
            AbstractKingdomCartRiderNpcEntity npcDriver = (AbstractKingdomCartRiderNpcEntity)passenger;
            return npcDriver;
        }
        return null;
    }

    private int resolveSeatIndex(class_1297 passenger) {
        AbstractKingdomCartRiderNpcEntity npcDriver = this.findNpcDriverPassenger();
        if (npcDriver != null) {
            if (passenger == npcDriver) {
                return 0;
            }
            return this.method_5685().contains(passenger) ? 1 : -1;
        }
        return this.method_5685().indexOf(passenger);
    }

    private boolean isDriverPassenger(class_1297 entity) {
        return this.resolveSeatIndex(entity) == 0;
    }

    private boolean hasActiveDraftDriver() {
        class_1309 controllingPassenger = this.method_5642();
        return controllingPassenger != null && this.isDriverPassenger((class_1297)controllingPassenger);
    }

    private boolean isLocalPlayerDrivingDraftTeam() {
        class_1657 player;
        class_1309 controllingPassenger = this.method_5642();
        return controllingPassenger instanceof class_1657 && (player = (class_1657)controllingPassenger).method_7340() && this.isDriverPassenger((class_1297)player);
    }

    public class_1309 method_5642() {
        AbstractKingdomCartRiderNpcEntity npcDriver = this.findNpcDriverPassenger();
        if (npcDriver != null) {
            return npcDriver;
        }
        for (class_1297 passenger : this.method_5685()) {
            if (!(passenger instanceof class_1309)) continue;
            class_1309 living = (class_1309)passenger;
            return living;
        }
        return null;
    }

    private static class_243 horizontalForward(float yawDegrees) {
        float yawRadians = yawDegrees * ((float)Math.PI / 180);
        return new class_243((double)(-class_3532.method_15374((float)yawRadians)), 0.0, (double)class_3532.method_15362((float)yawRadians));
    }

    private static class_243 horizontalRight(float yawDegrees) {
        float yawRadians = yawDegrees * ((float)Math.PI / 180);
        return new class_243((double)class_3532.method_15362((float)yawRadians), 0.0, (double)class_3532.method_15374((float)yawRadians));
    }

    private static float yawFromBackVector(class_243 back) {
        return (float)Math.toDegrees(Math.atan2(-back.field_1352, back.field_1350));
    }

    private static float yawFromForwardVector(class_243 forward) {
        return TwoHorseCartEntity.yawFromBackVector(forward.method_1021(-1.0));
    }

    private static float wrapAngle(float angle) {
        float tau = (float)Math.PI * 2;
        float wrapped = angle % tau;
        return wrapped < 0.0f ? wrapped + tau : wrapped;
    }

    protected void method_5749(class_2487 nbt) {
        if (nbt.method_25928("LeftHorse")) {
            this.field_6011.method_12778(LEFT_HORSE_UUID, Optional.of(nbt.method_25926("LeftHorse")));
        } else {
            this.field_6011.method_12778(LEFT_HORSE_UUID, Optional.empty());
        }
        if (nbt.method_25928("RightHorse")) {
            this.field_6011.method_12778(RIGHT_HORSE_UUID, Optional.of(nbt.method_25926("RightHorse")));
        } else {
            this.field_6011.method_12778(RIGHT_HORSE_UUID, Optional.empty());
        }
        this.prevWheelRotation = this.wheelRotation = nbt.method_10583("WheelRotation");
        this.setHealth(nbt.method_10545("Health") ? nbt.method_10583("Health") : 30.0f);
    }

    protected void method_5652(class_2487 nbt) {
        ((Optional)this.field_6011.method_12789(LEFT_HORSE_UUID)).ifPresent(uuid -> nbt.method_25927("LeftHorse", uuid));
        ((Optional)this.field_6011.method_12789(RIGHT_HORSE_UUID)).ifPresent(uuid -> nbt.method_25927("RightHorse", uuid));
        nbt.method_10548("WheelRotation", this.wheelRotation);
        nbt.method_10548("Health", this.getHealth());
    }

    protected float getHealth() {
        return ((Float)this.field_6011.method_12789(HEALTH)).floatValue();
    }

    protected void setHealth(float health) {
        this.field_6011.method_12778(HEALTH, (Object)Float.valueOf(class_3532.method_15363((float)health, (float)0.0f, (float)30.0f)));
    }

    public void method_5650(class_1297.class_5529 reason) {
        if (this.registeredLeftHorseUuid != null) {
            ATTACHED_CART_HORSE_UUIDS.remove(this.registeredLeftHorseUuid);
            this.registeredLeftHorseUuid = null;
        }
        if (this.registeredRightHorseUuid != null) {
            ATTACHED_CART_HORSE_UUIDS.remove(this.registeredRightHorseUuid);
            this.registeredRightHorseUuid = null;
        }
        super.method_5650(reason);
    }

    public class_4048 method_18377(class_4050 pose) {
        return this.method_5864().method_18386();
    }

    public boolean method_30949(class_1297 other) {
        return false;
    }

    public boolean method_5810() {
        return false;
    }

    public boolean method_30948() {
        return false;
    }

    public boolean method_5863() {
        return true;
    }

    public boolean method_5732() {
        return true;
    }

    public boolean method_5643(class_1282 source, float amount) {
        if (this.method_31481() || this.method_5679(source)) {
            return false;
        }
        if (this.method_37908().field_9236) {
            return true;
        }
        class_1297 class_12972 = source.method_5529();
        if (class_12972 instanceof class_1657) {
            class_1657 player = (class_1657)class_12972;
            if (player.method_31549().field_7477) {
                this.breakCart(source);
                return true;
            }
        }
        this.setHealth(this.getHealth() - amount);
        if (this.getHealth() > 0.0f) {
            return true;
        }
        this.breakCart(source);
        return true;
    }

    private void breakCart(class_1282 source) {
        this.onCartBroken(source);
        this.method_5772();
        this.detachHorse(0);
        this.detachHorse(1);
        if (!this.isVoyageCart() && this.method_37908().method_8450().method_8355(class_1928.field_19393)) {
            this.method_5775(new class_1799((class_1935)this.asItem()));
        }
        this.method_31472();
    }

    private boolean isVoyageCart() {
        return this.method_5752().stream().anyMatch(tag -> tag.startsWith("agob_voyage_"));
    }

    private void logVoyageRotationAnomaly(float previousYaw, float currentYaw, @Nullable class_1496 leftHorse, @Nullable class_1496 rightHorse) {
    }

    public class_1792 asItem() {
        return GotItems.CART_2HORSE;
    }

    protected void onCartBroken(class_1282 source) {
    }

    public boolean hasAnyAttachedHorses() {
        return ((Optional)this.field_6011.method_12789(LEFT_HORSE_UUID)).isPresent() || ((Optional)this.field_6011.method_12789(RIGHT_HORSE_UUID)).isPresent();
    }

    @Override
    public PlatformHull agob$getPlatformHull() {
        return PLATFORM_HULL;
    }

    @Override
    public PlatformTransform agob$getPlatformTransform() {
        return this.platformRuntime.currentTransform();
    }

    @Override
    public PlatformTransform agob$getPreviousPlatformTransform() {
        return this.platformRuntime.previousTransform();
    }

    @Override
    public class_238 agob$getPlatformBroadPhaseBox() {
        return this.platformRuntime.broadPhaseBox();
    }
}

