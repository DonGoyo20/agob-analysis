/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1928
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2498
 *  net.minecraft.class_2561
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3414
 *  net.minecraft.class_3417
 *  net.minecraft.class_3419
 *  net.minecraft.class_3532
 *  net.minecraft.class_7923
 */
package trep.got.entity;

import java.util.Set;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1928;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2498;
import net.minecraft.class_2561;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3532;
import net.minecraft.class_7923;
import trep.got.entity.CatapultMunitionEntity;
import trep.got.entity.TwoHorseCartEntity;
import trep.got.registry.GotEntities;
import trep.got.registry.GotItems;

public class CatapultEntity
extends TwoHorseCartEntity {
    private static final class_2940<Integer> ACTION_STATE = class_2945.method_12791(CatapultEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Integer> ACTION_TICK = class_2945.method_12791(CatapultEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<String> MUNITION_BLOCK_ID = class_2945.method_12791(CatapultEntity.class, (class_2941)class_2943.field_13326);
    private static final Set<class_2248> VALID_MUNITION_BLOCKS = Set.of(class_2246.field_10340, class_2246.field_10445, class_2246.field_10115);
    private static final int STATE_IDLE = 0;
    private static final int STATE_LOADED = 1;
    private static final int STATE_FIRING = 2;
    private static final int STATE_POST_FIRE = 3;
    private static final int STATE_RELOADING = 4;
    private static final int FIRING_DURATION_TICKS = 12;
    private static final int RELOAD_DURATION_TICKS = 24;
    private static final float MAX_BEAM_PITCH_DEGREES = 80.0f;
    private static final double PROJECTILE_SPEED = 2.3;
    private int prevActionTick;

    public CatapultEntity(class_1299<? extends CatapultEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    public CatapultEntity(class_1937 world, double x, double y, double z) {
        this((class_1299<? extends CatapultEntity>)GotEntities.CATAPULT, world);
        this.method_5808(x, y, z, this.method_36454(), this.method_36455());
    }

    @Override
    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(ACTION_STATE, (Object)0);
        builder.method_56912(ACTION_TICK, (Object)0);
        builder.method_56912(MUNITION_BLOCK_ID, (Object)"");
    }

    @Override
    public void method_5773() {
        this.prevActionTick = this.getActionTick();
        super.method_5773();
        if (!(this.method_37908() instanceof class_3218)) {
            return;
        }
        if (this.getActionState() == 2) {
            this.advanceServerAnimation(12, this::finishFiring);
        } else if (this.getActionState() == 4) {
            this.advanceServerAnimation(24, this::finishReloading);
        }
    }

    private void advanceServerAnimation(int duration, Runnable onComplete) {
        int nextTick = this.getActionTick() + 1;
        if (nextTick >= duration) {
            onComplete.run();
            return;
        }
        this.field_6011.method_12778(ACTION_TICK, (Object)nextTick);
    }

    @Override
    public class_1269 method_5688(class_1657 player, class_1268 hand) {
        class_1799 heldStack = player.method_5998(hand);
        class_2248 block = CatapultEntity.resolveMunitionBlock(heldStack);
        if (block != null) {
            if (this.method_37908().field_9236) {
                return class_1269.field_5812;
            }
            if (this.hasMunitionLoaded()) {
                return class_1269.field_21466;
            }
            if (this.getActionState() == 3 || this.getActionState() == 4 || this.getActionState() == 2) {
                player.method_7353((class_2561)class_2561.method_43470((String)"Reload the catapult arm before loading a new munition block.").method_27692(class_124.field_1061), true);
                return class_1269.field_21466;
            }
            this.loadMunition(block, player, heldStack);
            return class_1269.field_21466;
        }
        if (hand == class_1268.field_5808 && heldStack.method_31574(class_1802.field_8865)) {
            if (this.method_37908().field_9236) {
                return class_1269.field_5812;
            }
            if (this.hasAnyAttachedHorses()) {
                player.method_7353((class_2561)class_2561.method_43470((String)"Detach the catapult from its horses before operating the firing arm.").method_27692(class_124.field_1061), true);
                return class_1269.field_21466;
            }
            if (this.getActionState() == 1 && this.hasMunitionLoaded()) {
                this.startFiring();
                return class_1269.field_21466;
            }
            if (this.getActionState() == 3) {
                this.startReloading();
                return class_1269.field_21466;
            }
            if (this.getActionState() == 0 && !this.hasMunitionLoaded()) {
                player.method_7353((class_2561)class_2561.method_43470((String)"Load a stone block onto the catapult first.").method_27692(class_124.field_1061), true);
                return class_1269.field_21466;
            }
            return class_1269.field_21466;
        }
        return super.method_5688(player, hand);
    }

    private void loadMunition(class_2248 block, class_1657 player, class_1799 heldStack) {
        this.field_6011.method_12778(MUNITION_BLOCK_ID, (Object)class_7923.field_41175.method_10221((Object)block).toString());
        this.field_6011.method_12778(ACTION_STATE, (Object)1);
        this.field_6011.method_12778(ACTION_TICK, (Object)0);
        this.playLoadMunitionSound(block);
        if (!player.method_7337()) {
            heldStack.method_7934(1);
        }
    }

    private void startFiring() {
        this.field_6011.method_12778(ACTION_STATE, (Object)2);
        this.field_6011.method_12778(ACTION_TICK, (Object)0);
        this.playCatapultSound(class_3417.field_14572, 0.9f, 0.5f);
    }

    private void startReloading() {
        this.field_6011.method_12778(ACTION_STATE, (Object)4);
        this.field_6011.method_12778(ACTION_TICK, (Object)0);
        this.playCatapultSound((class_3414)class_3417.field_14765.comp_349(), 0.85f, 0.5f);
    }

    private void finishFiring() {
        class_2248 munitionBlock = this.getMunitionBlock();
        this.spawnMunitionProjectile(munitionBlock);
        this.playCatapultSound(class_3417.field_14600, 1.1f, 0.5f);
        this.field_6011.method_12778(MUNITION_BLOCK_ID, (Object)"");
        this.field_6011.method_12778(ACTION_STATE, (Object)3);
        this.field_6011.method_12778(ACTION_TICK, (Object)0);
    }

    private void finishReloading() {
        this.field_6011.method_12778(ACTION_STATE, (Object)(this.hasMunitionLoaded() ? 1 : 0));
        this.field_6011.method_12778(ACTION_TICK, (Object)0);
    }

    private void spawnMunitionProjectile(class_2248 munitionBlock) {
        class_1937 class_19372 = this.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        class_1309 owner = this.method_5642();
        CatapultMunitionEntity projectile = new CatapultMunitionEntity((class_1937)serverWorld, owner == null ? this : owner, munitionBlock);
        class_243 spawnPos = this.getMunitionReleasePos(1.0f);
        class_243 launchVelocity = this.getMunitionLaunchDirection().method_1021(2.3);
        projectile.method_5808(spawnPos.field_1352, spawnPos.field_1351, spawnPos.field_1350, this.method_36454(), this.method_36455());
        projectile.method_18799(launchVelocity);
        serverWorld.method_8649((class_1297)projectile);
    }

    private class_243 getMunitionLaunchDirection() {
        float beamPitch = this.getBeamModelPitchRadians(1.0f);
        class_243 tangentModel = new class_243(0.0, 5.0 * Math.sin(beamPitch) - 19.0 * Math.cos(beamPitch), -5.0 * Math.cos(beamPitch) - 19.0 * Math.sin(beamPitch));
        class_243 local = new class_243(tangentModel.field_1352 / 16.0, -tangentModel.field_1351 / 16.0, -tangentModel.field_1350 / 16.0).method_1029();
        class_243 worldDirection = CatapultEntity.rotateLocalToWorld(local, this.method_36454(), this.method_36455()).method_1029();
        double horizontalMagnitude = Math.sqrt(worldDirection.field_1352 * worldDirection.field_1352 + worldDirection.field_1350 * worldDirection.field_1350);
        double currentAngle = Math.atan2(worldDirection.field_1351, horizontalMagnitude);
        double adjustedAngle = currentAngle - Math.toRadians(-30.0);
        double adjustedHorizontal = Math.cos(adjustedAngle);
        return new class_243(worldDirection.field_1352 == 0.0 && worldDirection.field_1350 == 0.0 ? 0.0 : worldDirection.field_1352 / horizontalMagnitude * adjustedHorizontal, Math.sin(adjustedAngle), worldDirection.field_1352 == 0.0 && worldDirection.field_1350 == 0.0 ? adjustedHorizontal : worldDirection.field_1350 / horizontalMagnitude * adjustedHorizontal).method_1029();
    }

    public class_243 getMunitionReleasePos(float tickDelta) {
        float beamPitch = this.getBeamModelPitchRadians(tickDelta);
        class_243 surfaceCenterModel = new class_243(0.0, -5.0, 19.0);
        class_243 rotated = CatapultEntity.rotateModelX(surfaceCenterModel, beamPitch).method_1031(0.0, 9.0, 0.0);
        class_243 local = new class_243(rotated.field_1352 / 16.0, -rotated.field_1351 / 16.0, -rotated.field_1350 / 16.0).method_1031(0.0, 1.5, 0.0);
        class_243 worldOffset = CatapultEntity.rotateLocalToWorld(local, class_3532.method_17821((float)tickDelta, (float)this.field_5982, (float)this.method_36454()), class_3532.method_16439((float)tickDelta, (float)this.field_6004, (float)this.method_36455()));
        return this.getInterpolatedCatapultPos(tickDelta).method_1019(worldOffset);
    }

    private static class_243 rotateModelX(class_243 vector, float pitch) {
        float cos = class_3532.method_15362((float)pitch);
        float sin = class_3532.method_15374((float)pitch);
        return new class_243(vector.field_1352, vector.field_1351 * (double)cos - vector.field_1350 * (double)sin, vector.field_1351 * (double)sin + vector.field_1350 * (double)cos);
    }

    private static class_243 rotateLocalToWorld(class_243 local, float yawDegrees, float pitchDegrees) {
        float pitchRadians = pitchDegrees * ((float)Math.PI / 180);
        float pitchCos = class_3532.method_15362((float)pitchRadians);
        float pitchSin = class_3532.method_15374((float)pitchRadians);
        class_243 pitched = new class_243(local.field_1352, local.field_1351 * (double)pitchCos - local.field_1350 * (double)pitchSin, local.field_1351 * (double)pitchSin + local.field_1350 * (double)pitchCos);
        float yawRadians = (180.0f - yawDegrees) * ((float)Math.PI / 180);
        float yawCos = class_3532.method_15362((float)yawRadians);
        float yawSin = class_3532.method_15374((float)yawRadians);
        return new class_243(pitched.field_1352 * (double)yawCos + pitched.field_1350 * (double)yawSin, pitched.field_1351, -pitched.field_1352 * (double)yawSin + pitched.field_1350 * (double)yawCos);
    }

    private class_243 getInterpolatedCatapultPos(float tickDelta) {
        return new class_243(class_3532.method_16436((double)tickDelta, (double)this.field_6014, (double)this.method_23317()), class_3532.method_16436((double)tickDelta, (double)this.field_6036, (double)this.method_23318()), class_3532.method_16436((double)tickDelta, (double)this.field_5969, (double)this.method_23321()));
    }

    public float getBeamModelPitchRadians(float tickDelta) {
        int state = this.getActionState();
        float progress = class_3532.method_48781((float)tickDelta, (int)this.prevActionTick, (int)this.getActionTick());
        if (state == 2) {
            return CatapultEntity.easeInOut(class_3532.method_15363((float)(progress / 12.0f), (float)0.0f, (float)1.0f)) * 80.0f * ((float)Math.PI / 180);
        }
        if (state == 3) {
            return 1.3962634f;
        }
        if (state == 4) {
            float reload = CatapultEntity.easeInOut(class_3532.method_15363((float)(progress / 24.0f), (float)0.0f, (float)1.0f));
            return (1.0f - reload) * 80.0f * ((float)Math.PI / 180);
        }
        return 0.0f;
    }

    private static float easeInOut(float progress) {
        return progress < 0.5f ? 4.0f * progress * progress * progress : 1.0f - (float)Math.pow(-2.0f * progress + 2.0f, 3.0) / 2.0f;
    }

    private void playLoadMunitionSound(class_2248 block) {
        class_2498 soundGroup = block.method_9564().method_26231();
        this.playCatapultSound(soundGroup.method_10598(), (soundGroup.method_10597() + 1.0f) * 0.5f, soundGroup.method_10599());
    }

    private void playCatapultSound(class_3414 sound, float volume, float pitch) {
        this.method_37908().method_43128(null, this.method_23317(), this.method_23318(), this.method_23321(), sound, class_3419.field_15245, volume, pitch);
    }

    public boolean hasMunitionLoaded() {
        return !((String)this.field_6011.method_12789(MUNITION_BLOCK_ID)).isBlank();
    }

    public class_2248 getMunitionBlock() {
        String id = (String)this.field_6011.method_12789(MUNITION_BLOCK_ID);
        if (id.isBlank()) {
            return class_2246.field_10124;
        }
        return class_7923.field_41175.method_17966(class_2960.method_12829((String)id)).orElse(class_2246.field_10340);
    }

    public boolean shouldRenderMunitionOnBeam() {
        return this.hasMunitionLoaded() && this.getActionState() != 3 && this.getActionState() != 4;
    }

    private static class_2248 resolveMunitionBlock(class_1799 stack) {
        class_1792 class_17922 = stack.method_7909();
        if (!(class_17922 instanceof class_1747)) {
            return null;
        }
        class_1747 blockItem = (class_1747)class_17922;
        class_2248 block = blockItem.method_7711();
        return VALID_MUNITION_BLOCKS.contains(block) ? block : null;
    }

    private int getActionState() {
        return (Integer)this.field_6011.method_12789(ACTION_STATE);
    }

    private int getActionTick() {
        return (Integer)this.field_6011.method_12789(ACTION_TICK);
    }

    @Override
    protected void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        this.field_6011.method_12778(ACTION_STATE, (Object)nbt.method_10550("CatapultState"));
        this.field_6011.method_12778(ACTION_TICK, (Object)nbt.method_10550("CatapultActionTick"));
        this.field_6011.method_12778(MUNITION_BLOCK_ID, (Object)nbt.method_10558("CatapultMunitionBlock"));
        this.prevActionTick = this.getActionTick();
    }

    @Override
    protected void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10569("CatapultState", this.getActionState());
        nbt.method_10569("CatapultActionTick", this.getActionTick());
        nbt.method_10582("CatapultMunitionBlock", (String)this.field_6011.method_12789(MUNITION_BLOCK_ID));
    }

    @Override
    protected void onCartBroken(class_1282 source) {
        if (this.hasMunitionLoaded() && !this.method_37908().field_9236 && this.method_37908().method_8450().method_8355(class_1928.field_19393)) {
            this.method_5775(new class_1799((class_1935)this.getMunitionBlock()));
        }
    }

    @Override
    public class_1792 asItem() {
        return GotItems.CATAPULT;
    }
}

