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
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1665$class_1666
 *  net.minecraft.class_1667
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1922
 *  net.minecraft.class_1928
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_265
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_3218
 *  net.minecraft.class_3414
 *  net.minecraft.class_3417
 *  net.minecraft.class_3419
 *  net.minecraft.class_3532
 *  net.minecraft.class_4048
 *  net.minecraft.class_4050
 */
package trep.got.entity;

import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1665;
import net.minecraft.class_1667;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1922;
import net.minecraft.class_1928;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_265;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3218;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3532;
import net.minecraft.class_4048;
import net.minecraft.class_4050;
import trep.got.item.ScorpionCrossbowCombatHelper;
import trep.got.registry.GotEntities;
import trep.got.registry.GotItems;
import trep.got.vehicle.platform.PlatformEntity;
import trep.got.vehicle.platform.PlatformHull;
import trep.got.vehicle.platform.PlatformLocalBox;
import trep.got.vehicle.platform.PlatformRuntime;
import trep.got.vehicle.platform.PlatformTransform;

public final class ScorpionCrossbowEntity
extends class_1297
implements PlatformEntity {
    private static final class_2940<Boolean> LOADED = class_2945.method_12791(ScorpionCrossbowEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<Integer> ACTION_STATE = class_2945.method_12791(ScorpionCrossbowEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Integer> ACTION_TICK = class_2945.method_12791(ScorpionCrossbowEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Float> AIM_YAW = class_2945.method_12791(ScorpionCrossbowEntity.class, (class_2941)class_2943.field_13320);
    private static final class_2940<Float> AIM_PITCH = class_2945.method_12791(ScorpionCrossbowEntity.class, (class_2941)class_2943.field_13320);
    private static final int STATE_IDLE = 0;
    private static final int STATE_RELOADING = 1;
    private static final int STATE_LOADED = 2;
    private static final int STATE_FIRING = 3;
    private static final int SHOOT_ANIMATION_TICKS = 10;
    private static final float MIN_AIM_PITCH = -60.0f;
    private static final float MAX_DOWNWARD_AIM_PITCH = 15.0f;
    private static final double SUPPORT_Y = 0.0;
    private static final class_243 CROSSBOW_MODEL_PIVOT = new class_243(0.0, 18.0, 0.0);
    private static final class_243 SEAT_MODEL_PIVOT = new class_243(0.0, 8.0, 9.0);
    private static final class_243 PROJECTILE_MODEL_PIVOT = new class_243(0.0, 0.0, -14.0);
    private static final PlatformHull PLATFORM_HULL = new PlatformHull(List.of(new PlatformLocalBox(-0.8, 0.0, -0.8, 0.8, 0.12, 0.8, 3), new PlatformLocalBox(-0.9, 0.0, -0.9, 0.9, 1.15, -0.65, 2), new PlatformLocalBox(-0.9, 0.0, 0.65, 0.9, 1.15, 0.9, 2), new PlatformLocalBox(-0.9, 0.0, -0.65, -0.65, 1.15, 0.65, 2), new PlatformLocalBox(0.65, 0.0, -0.65, 0.9, 1.15, 0.65, 2)));
    private final PlatformRuntime platformRuntime = new PlatformRuntime();
    private int prevActionTick;
    private float prevAimYaw;
    private float prevAimPitch;

    public ScorpionCrossbowEntity(class_1299<? extends ScorpionCrossbowEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.field_23807 = true;
    }

    public ScorpionCrossbowEntity(class_1937 world, double x, double y, double z) {
        this(GotEntities.SCORPION_CROSSBOW, world);
        this.method_5808(x, y, z, this.method_36454(), 0.0f);
        this.setAimYaw(this.method_36454());
    }

    protected void method_5693(class_2945.class_9222 builder) {
        builder.method_56912(LOADED, (Object)false);
        builder.method_56912(ACTION_STATE, (Object)0);
        builder.method_56912(ACTION_TICK, (Object)0);
        builder.method_56912(AIM_YAW, (Object)Float.valueOf(0.0f));
        builder.method_56912(AIM_PITCH, (Object)Float.valueOf(0.0f));
    }

    public void method_5773() {
        this.prevActionTick = this.getActionTick();
        this.prevAimYaw = this.getAimYaw();
        this.prevAimPitch = this.getAimPitch();
        super.method_5773();
        this.method_18799(class_243.field_1353);
        this.settleOnGround();
        this.tickOperatorAim();
        if (!this.method_37908().field_9236) {
            this.tickActionAnimation();
        }
        this.platformRuntime.update(this, this.createPlatformTransform(), PLATFORM_HULL);
    }

    private void tickOperatorAim() {
        class_1297 passenger = this.method_31483();
        if (!(passenger instanceof class_1309)) {
            return;
        }
        class_1309 living = (class_1309)passenger;
        float pitch = class_3532.method_15363((float)living.method_36455(), (float)-60.0f, (float)15.0f);
        this.setAimYaw(living.method_36454());
        this.setAimPitch(pitch);
        if (living.method_36455() != pitch) {
            living.method_36457(pitch);
        }
    }

    private void tickActionAnimation() {
        if (this.getActionState() == 1) {
            int nextTick = this.getActionTick() + 1;
            if (nextTick >= ScorpionCrossbowCombatHelper.getReloadTicks()) {
                this.field_6011.method_12778(LOADED, (Object)true);
                this.field_6011.method_12778(ACTION_STATE, (Object)2);
                this.field_6011.method_12778(ACTION_TICK, (Object)0);
                this.method_5783((class_3414)class_3417.field_14626.comp_349(), 1.0f, 0.85f);
                return;
            }
            this.field_6011.method_12778(ACTION_TICK, (Object)nextTick);
            return;
        }
        if (this.getActionState() == 3) {
            int nextTick = this.getActionTick() + 1;
            if (nextTick >= 10) {
                this.field_6011.method_12778(ACTION_STATE, (Object)0);
                this.field_6011.method_12778(ACTION_TICK, (Object)0);
                return;
            }
            this.field_6011.method_12778(ACTION_TICK, (Object)nextTick);
        }
    }

    public class_1269 method_5688(class_1657 player, class_1268 hand) {
        if (this.method_5626((class_1297)player) && hand == class_1268.field_5808) {
            return class_1269.field_5811;
        }
        if (!player.method_21823()) {
            if (this.method_37908().field_9236) {
                return class_1269.field_5812;
            }
            if (player.method_5804((class_1297)this)) {
                this.snapAimToPassenger((class_1309)player);
                return class_1269.field_21466;
            }
            return class_1269.field_5811;
        }
        return class_1269.field_5811;
    }

    public class_1269 method_5664(class_1657 player, class_243 hitPos, class_1268 hand) {
        return this.method_5688(player, hand);
    }

    public void handleFireKey(class_1657 player) {
        if (this.method_37908().field_9236 || !this.method_5626((class_1297)player) || this.isBusy()) {
            return;
        }
        if (this.isLoaded()) {
            this.startShoot(player);
            return;
        }
        this.startReload(player);
    }

    private void startReload(class_1657 player) {
        if (!player.method_7337() && !this.consumeInventoryArrow(player)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You need an arrow to reload the scorpion crossbow.").method_27692(class_124.field_1061), true);
            return;
        }
        this.field_6011.method_12778(LOADED, (Object)false);
        this.field_6011.method_12778(ACTION_STATE, (Object)1);
        this.field_6011.method_12778(ACTION_TICK, (Object)0);
        this.method_5783((class_3414)class_3417.field_14765.comp_349(), 0.9f, 0.85f);
    }

    private boolean consumeInventoryArrow(class_1657 player) {
        for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
            class_1799 stack = player.method_31548().method_5438(slot);
            if (!stack.method_31574(class_1802.field_8107)) continue;
            stack.method_7934(1);
            return true;
        }
        return false;
    }

    private void startShoot(class_1657 player) {
        if (!this.isLoaded()) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Load the scorpion crossbow with an arrow first.").method_27692(class_124.field_1061), true);
            return;
        }
        this.setAimYaw(player.method_36454());
        this.setAimPitch(player.method_36455());
        if (!this.spawnArrowProjectile(player)) {
            return;
        }
        this.field_6011.method_12778(LOADED, (Object)false);
        this.field_6011.method_12778(ACTION_STATE, (Object)3);
        this.field_6011.method_12778(ACTION_TICK, (Object)0);
        this.method_37908().method_43128(null, this.method_23317(), this.method_23318(), this.method_23321(), class_3417.field_14600, class_3419.field_15245, 1.15f, 0.6f);
    }

    private boolean spawnArrowProjectile(class_1657 player) {
        class_1937 class_19372 = this.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return false;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        class_1667 arrow = new class_1667(class_1299.field_6122, (class_1937)serverWorld);
        arrow.method_7432((class_1297)player);
        arrow.method_7438(ScorpionCrossbowCombatHelper.getBaseProjectileDamage());
        arrow.method_5875(true);
        arrow.field_7572 = class_1665.class_1666.field_7592;
        class_243 direction = this.getAimDirection();
        class_243 spawnPos = this.getProjectileWorldPos(1.0f).method_1019(direction.method_1021(0.25));
        arrow.method_5808(spawnPos.field_1352, spawnPos.field_1351, spawnPos.field_1350, this.getAimYaw(), this.getAimPitch());
        arrow.method_7485(direction.field_1352, direction.field_1351, direction.field_1350, ScorpionCrossbowCombatHelper.getProjectileSpeed(), 0.0f);
        return serverWorld.method_8649((class_1297)arrow);
    }

    public class_243 getAimDirection() {
        return class_243.method_1030((float)this.getAimPitch(), (float)this.getAimYaw()).method_1029();
    }

    public class_243 getProjectileWorldPos(float tickDelta) {
        return this.modelPivotToWorld(PROJECTILE_MODEL_PIVOT, tickDelta);
    }

    public float getLoadProgress(float tickDelta) {
        if (this.getActionState() == 1) {
            return class_3532.method_15363((float)(class_3532.method_48781((float)tickDelta, (int)this.prevActionTick, (int)this.getActionTick()) / ScorpionCrossbowCombatHelper.getReloadTicks()), (float)0.0f, (float)1.0f);
        }
        return this.isLoaded() ? 1.0f : 0.0f;
    }

    public float getReloadAnimationProgress(float tickDelta) {
        if (this.getActionState() != 1) {
            return 0.0f;
        }
        return class_3532.method_15363((float)(class_3532.method_48781((float)tickDelta, (int)this.prevActionTick, (int)this.getActionTick()) / ScorpionCrossbowCombatHelper.getReloadTicks()), (float)0.0f, (float)1.0f);
    }

    public float getShootAnimationProgress(float tickDelta) {
        if (this.getActionState() != 3) {
            return 0.0f;
        }
        return class_3532.method_15363((float)(class_3532.method_48781((float)tickDelta, (int)this.prevActionTick, (int)this.getActionTick()) / 10), (float)0.0f, (float)1.0f);
    }

    protected boolean method_5818(class_1297 passenger) {
        return this.method_5685().isEmpty();
    }

    public void method_5865(class_1297 passenger, class_1297.class_4738 positionUpdater) {
        if (!this.method_5626(passenger)) {
            return;
        }
        class_243 seatPos = this.getSeatWorldPos();
        class_243 attachment = passenger.method_55668((class_1297)this);
        positionUpdater.accept(passenger, seatPos.field_1352 - attachment.field_1352, seatPos.field_1351 - attachment.field_1351, seatPos.field_1350 - attachment.field_1350);
        float yawDelta = class_3532.method_15393((float)(this.getAimYaw() - passenger.method_36454()));
        float passengerYaw = passenger.method_36454() + yawDelta;
        passenger.method_36456(passengerYaw);
        passenger.method_5636(passengerYaw);
        passenger.method_5847(passenger.method_5791() + yawDelta);
    }

    public class_243 method_24829(class_1309 passenger) {
        class_243 right = this.getSeatWorldPos().method_1019(ScorpionCrossbowEntity.horizontalRight(this.method_36454()).method_1021(1.25));
        class_243 left = this.getSeatWorldPos().method_1019(ScorpionCrossbowEntity.horizontalRight(this.method_36454()).method_1021(-1.25));
        return this.isDismountSpaceClear(passenger, left) ? left : right;
    }

    private class_243 getSeatWorldPos() {
        return this.modelPivotToWorld(SEAT_MODEL_PIVOT);
    }

    private class_243 modelPivotToWorld(class_243 modelPivot) {
        return this.modelPivotToWorld(modelPivot, 1.0f);
    }

    private class_243 modelPivotToWorld(class_243 modelPivot, float tickDelta) {
        float baseYaw = class_3532.method_17821((float)tickDelta, (float)this.field_5982, (float)this.method_36454());
        class_243 rotatedModelPivot = this.rotateCrossbowModelPoint(modelPivot, this.getAimYaw(tickDelta), this.getAimPitch(tickDelta), baseYaw);
        return this.modelToWorld(rotatedModelPivot, tickDelta, baseYaw);
    }

    private class_243 rotateCrossbowModelPoint(class_243 modelPivot, float aimYaw, float aimPitch, float baseYaw) {
        class_243 relative = modelPivot.method_1020(CROSSBOW_MODEL_PIVOT);
        class_243 pitched = ScorpionCrossbowEntity.rotateModelX(relative, aimPitch * ((float)Math.PI / 180));
        float relativeYaw = aimYaw + 180.0f - baseYaw;
        class_243 yawed = ScorpionCrossbowEntity.rotateModelY(pitched, relativeYaw * ((float)Math.PI / 180));
        return CROSSBOW_MODEL_PIVOT.method_1019(yawed);
    }

    private class_243 modelToWorld(class_243 modelPivot, float tickDelta, float baseYaw) {
        class_243 local = new class_243(modelPivot.field_1352 / 16.0, 1.5 - modelPivot.field_1351 / 16.0, -modelPivot.field_1350 / 16.0);
        class_243 forward = class_243.method_1030((float)0.0f, (float)(baseYaw + 180.0f)).method_1029();
        class_243 right = new class_243(forward.field_1350, 0.0, -forward.field_1352).method_1029();
        return this.getInterpolatedPos(tickDelta).method_1019(right.method_1021(local.field_1352)).method_1031(0.0, local.field_1351, 0.0).method_1019(forward.method_1021(local.field_1350));
    }

    private static class_243 rotateModelX(class_243 vector, float pitch) {
        float cos = class_3532.method_15362((float)pitch);
        float sin = class_3532.method_15374((float)pitch);
        return new class_243(vector.field_1352, vector.field_1351 * (double)cos - vector.field_1350 * (double)sin, vector.field_1351 * (double)sin + vector.field_1350 * (double)cos);
    }

    private static class_243 rotateModelY(class_243 vector, float yaw) {
        float cos = class_3532.method_15362((float)yaw);
        float sin = class_3532.method_15374((float)yaw);
        return new class_243(vector.field_1352 * (double)cos + vector.field_1350 * (double)sin, vector.field_1351, -vector.field_1352 * (double)sin + vector.field_1350 * (double)cos);
    }

    private boolean isDismountSpaceClear(class_1309 passenger, class_243 pos) {
        class_238 box = passenger.method_18377(passenger.method_18376()).method_30757(pos);
        return this.method_37908().method_8587((class_1297)this, box);
    }

    private class_243 getInterpolatedPos(float tickDelta) {
        return new class_243(class_3532.method_16436((double)tickDelta, (double)this.field_6014, (double)this.method_23317()), class_3532.method_16436((double)tickDelta, (double)this.field_6036, (double)this.method_23318()), class_3532.method_16436((double)tickDelta, (double)this.field_5969, (double)this.method_23321()));
    }

    private void settleOnGround() {
        double ground = this.sampleGroundHeight(this.method_19538());
        this.method_5814(this.method_23317(), ground, this.method_23321());
        this.method_36457(0.0f);
        this.field_6004 = 0.0f;
    }

    private double sampleGroundHeight(class_243 samplePos) {
        int blockX = class_3532.method_15357((double)samplePos.field_1352);
        int blockZ = class_3532.method_15357((double)samplePos.field_1350);
        int startY = class_3532.method_15357((double)this.method_23318()) + 2;
        int minY = this.method_37908().method_31607();
        for (int y = startY; y >= minY; --y) {
            class_2338 pos = new class_2338(blockX, y, blockZ);
            class_265 shape = this.method_37908().method_8320(pos).method_26220((class_1922)this.method_37908(), pos);
            if (shape.method_1110()) continue;
            return (double)pos.method_10264() + shape.method_1105(class_2350.class_2351.field_11052);
        }
        return this.method_37908().method_31607();
    }

    private PlatformTransform createPlatformTransform() {
        return new PlatformTransform(this.method_19538(), class_243.field_1353, 1.0, 0.0, this.method_36454(), this.method_36454());
    }

    private static class_243 horizontalRight(float yawDegrees) {
        float yawRadians = yawDegrees * ((float)Math.PI / 180);
        return new class_243((double)class_3532.method_15362((float)yawRadians), 0.0, (double)class_3532.method_15374((float)yawRadians));
    }

    public boolean isLoaded() {
        return (Boolean)this.field_6011.method_12789(LOADED);
    }

    public boolean isBusy() {
        return this.getActionState() == 1 || this.getActionState() == 3;
    }

    public boolean isReloading() {
        return this.getActionState() == 1;
    }

    public float getAimYaw() {
        return ((Float)this.field_6011.method_12789(AIM_YAW)).floatValue();
    }

    public float getAimYaw(float tickDelta) {
        return class_3532.method_16439((float)tickDelta, (float)this.prevAimYaw, (float)this.getAimYaw());
    }

    private void setAimYaw(float aimYaw) {
        float currentAimYaw = ((Float)this.field_6011.method_12789(AIM_YAW)).floatValue();
        this.field_6011.method_12778(AIM_YAW, (Object)Float.valueOf(currentAimYaw + class_3532.method_15393((float)(aimYaw - currentAimYaw))));
    }

    public float getAimPitch() {
        return ((Float)this.field_6011.method_12789(AIM_PITCH)).floatValue();
    }

    public float getAimPitch(float tickDelta) {
        return class_3532.method_16439((float)tickDelta, (float)this.prevAimPitch, (float)this.getAimPitch());
    }

    private void setAimPitch(float aimPitch) {
        this.field_6011.method_12778(AIM_PITCH, (Object)Float.valueOf(class_3532.method_15363((float)aimPitch, (float)-60.0f, (float)15.0f)));
    }

    private void snapAimToPassenger(class_1309 passenger) {
        float pitch = class_3532.method_15363((float)passenger.method_36455(), (float)-60.0f, (float)15.0f);
        this.field_6011.method_12778(AIM_YAW, (Object)Float.valueOf(passenger.method_36454()));
        this.field_6011.method_12778(AIM_PITCH, (Object)Float.valueOf(pitch));
        this.prevAimYaw = passenger.method_36454();
        this.prevAimPitch = pitch;
        passenger.method_36457(pitch);
    }

    private int getActionTick() {
        return (Integer)this.field_6011.method_12789(ACTION_TICK);
    }

    private int getActionState() {
        return (Integer)this.field_6011.method_12789(ACTION_STATE);
    }

    protected void method_5749(class_2487 nbt) {
        this.field_6011.method_12778(LOADED, (Object)nbt.method_10577("Loaded"));
        this.field_6011.method_12778(ACTION_STATE, (Object)nbt.method_10550("ActionState"));
        this.field_6011.method_12778(ACTION_TICK, (Object)nbt.method_10550("ActionTick"));
        this.setAimYaw(nbt.method_10545("AimYaw") ? nbt.method_10583("AimYaw") : this.method_36454());
        this.setAimPitch(nbt.method_10583("AimPitch"));
        this.prevActionTick = this.getActionTick();
        this.prevAimYaw = this.getAimYaw();
        this.prevAimPitch = this.getAimPitch();
    }

    protected void method_5652(class_2487 nbt) {
        nbt.method_10556("Loaded", this.isLoaded());
        nbt.method_10569("ActionState", this.getActionState());
        nbt.method_10569("ActionTick", this.getActionTick());
        nbt.method_10548("AimYaw", this.getAimYaw());
        nbt.method_10548("AimPitch", this.getAimPitch());
    }

    public class_4048 method_18377(class_4050 pose) {
        return this.method_5864().method_18386();
    }

    public boolean method_30948() {
        return false;
    }

    public boolean method_5810() {
        return false;
    }

    public boolean method_30949(class_1297 other) {
        return false;
    }

    public void method_5697(class_1297 entity) {
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
        this.method_5772();
        if (this.method_37908().method_8450().method_8355(class_1928.field_19393)) {
            this.method_5775(new class_1799((class_1935)class_1802.field_8118, 2));
            this.method_5775(new class_1799((class_1935)class_1802.field_8399));
            this.method_5775(new class_1799((class_1935)class_1802.field_8600));
            if (this.isLoaded() || this.isReloading()) {
                this.method_5775(new class_1799((class_1935)class_1802.field_8107));
            }
        }
        this.method_31472();
        return true;
    }

    public class_1792 asItem() {
        return GotItems.SCORPION_CROSSBOW;
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

