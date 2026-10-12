/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1266
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1282
 *  net.minecraft.class_1296
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1314
 *  net.minecraft.class_1315
 *  net.minecraft.class_1341
 *  net.minecraft.class_1345
 *  net.minecraft.class_1347
 *  net.minecraft.class_1352
 *  net.minecraft.class_1352$class_4134
 *  net.minecraft.class_1353
 *  net.minecraft.class_1361
 *  net.minecraft.class_1376
 *  net.minecraft.class_1391
 *  net.minecraft.class_1394
 *  net.minecraft.class_1429
 *  net.minecraft.class_1542
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2680
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_3218
 *  net.minecraft.class_3414
 *  net.minecraft.class_3417
 *  net.minecraft.class_3419
 *  net.minecraft.class_3489
 *  net.minecraft.class_3532
 *  net.minecraft.class_3730
 *  net.minecraft.class_5132$class_5133
 *  net.minecraft.class_5134
 *  net.minecraft.class_5147
 *  net.minecraft.class_5425
 *  net.minecraft.class_5532
 *  net.minecraft.class_5712
 *  net.minecraft.class_5819
 *  net.minecraft.class_6880
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity;

import java.util.EnumSet;
import net.minecraft.class_1266;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1296;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1314;
import net.minecraft.class_1315;
import net.minecraft.class_1341;
import net.minecraft.class_1345;
import net.minecraft.class_1347;
import net.minecraft.class_1352;
import net.minecraft.class_1353;
import net.minecraft.class_1361;
import net.minecraft.class_1376;
import net.minecraft.class_1391;
import net.minecraft.class_1394;
import net.minecraft.class_1429;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2680;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3218;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3489;
import net.minecraft.class_3532;
import net.minecraft.class_3730;
import net.minecraft.class_5132;
import net.minecraft.class_5134;
import net.minecraft.class_5147;
import net.minecraft.class_5425;
import net.minecraft.class_5532;
import net.minecraft.class_5712;
import net.minecraft.class_5819;
import net.minecraft.class_6880;
import org.jetbrains.annotations.Nullable;
import trep.got.registry.GotEntities;
import trep.got.registry.GotItems;

public class DeerEntity
extends class_1429
implements class_5147 {
    private static final class_2940<Boolean> SHEARED = class_2945.method_12791(DeerEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<Long> HORN_REGROW_TIME = class_2945.method_12791(DeerEntity.class, (class_2941)class_2943.field_39965);
    private static final class_2940<Integer> VARIANT = class_2945.method_12791(DeerEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Integer> HEADBUTT_TICKS = class_2945.method_12791(DeerEntity.class, (class_2941)class_2943.field_13327);
    private static final String SHEARED_KEY = "Sheared";
    private static final String HORN_REGROW_TIME_KEY = "HornRegrowTime";
    private static final String VARIANT_KEY = "Variant";
    private static final int VARIANT_COUNT = 3;
    private static final int MAX_GRASS_TIMER = 40;
    private static final int HORN_REGROW_TICKS = 1200;
    private static final int HEADBUTT_DURATION = 28;
    private static final int HEADBUTT_LUNGE_TICK = 10;
    private static final int HEADBUTT_IMPACT_TICK = 13;
    private static final int FLEE_TICKS = 80;
    private static final double WALK_SPEED = 0.2875;
    private static final double RUN_SPEED = 0.575;
    private int eatGrassTimer;
    private int fleeTicks;
    @Nullable
    private LivingTarget retaliationTarget;
    @Nullable
    private class_243 fleeFromPos;
    private class_1345 eatGrassGoal;

    public DeerEntity(class_1299<? extends DeerEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    public static class_5132.class_5133 createDeerAttributes() {
        return class_1308.method_26828().method_26868(class_5134.field_23716, 10.0).method_26868(class_5134.field_23719, 0.2875).method_26868(class_5134.field_23721, 3.0).method_26868(class_5134.field_23722, 0.5);
    }

    protected void method_5959() {
        this.eatGrassGoal = new class_1345((class_1308)this);
        this.field_6201.method_6277(0, (class_1352)new class_1347((class_1308)this));
        this.field_6201.method_6277(1, (class_1352)new HeadbuttGoal(this));
        this.field_6201.method_6277(2, (class_1352)new FleeAfterHeadbuttGoal(this));
        this.field_6201.method_6277(3, (class_1352)new class_1341((class_1429)this, 1.0));
        this.field_6201.method_6277(4, (class_1352)new class_1391((class_1314)this, 1.1, stack -> stack.method_31573(class_3489.field_49935), false));
        this.field_6201.method_6277(5, (class_1352)new class_1353((class_1429)this, 1.1));
        this.field_6201.method_6277(6, (class_1352)this.eatGrassGoal);
        this.field_6201.method_6277(7, (class_1352)new class_1394((class_1314)this, 1.0));
        this.field_6201.method_6277(8, (class_1352)new class_1361((class_1308)this, class_1657.class, 6.0f));
        this.field_6201.method_6277(9, (class_1352)new class_1376((class_1308)this));
    }

    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(SHEARED, (Object)false);
        builder.method_56912(HORN_REGROW_TIME, (Object)0L);
        builder.method_56912(VARIANT, (Object)1);
        builder.method_56912(HEADBUTT_TICKS, (Object)0);
    }

    public void method_5773() {
        super.method_5773();
        if (!this.method_37908().field_9236) {
            int headbuttTicks = this.getHeadbuttTicks();
            if (headbuttTicks > 0) {
                this.setHeadbuttTicks(headbuttTicks - 1);
            }
            if (this.fleeTicks > 0) {
                --this.fleeTicks;
            }
        }
    }

    protected void method_5958() {
        this.eatGrassTimer = this.eatGrassGoal.method_6258();
        super.method_5958();
    }

    public void method_6007() {
        if (this.method_37908().field_9236) {
            this.eatGrassTimer = Math.max(0, this.eatGrassTimer - 1);
        }
        super.method_6007();
    }

    public boolean method_5643(class_1282 source, float amount) {
        boolean damaged = super.method_5643(source, amount);
        if (damaged && !this.method_37908().field_9236) {
            this.prepareResponseToDamage(source);
        }
        return damaged;
    }

    private void prepareResponseToDamage(class_1282 source) {
        class_1309 livingEntity;
        class_1297 attacker = source.method_5529();
        if (attacker instanceof class_1309 && this.shouldRetaliateAgainst(livingEntity = (class_1309)attacker)) {
            this.retaliationTarget = new LivingTarget(livingEntity);
            this.fleeFromPos = livingEntity.method_19538();
            return;
        }
        class_243 sourcePos = source.method_5510();
        if (sourcePos != null) {
            this.startFleeingFrom(sourcePos);
        }
    }

    private boolean shouldRetaliateAgainst(class_1309 target) {
        if (!target.method_5805() || this.method_6109()) {
            return false;
        }
        if (target instanceof class_1657) {
            class_1657 player = (class_1657)target;
            return !player.method_7325() && !player.method_31549().field_7477;
        }
        return true;
    }

    private void startFleeingFrom(class_243 pos) {
        this.retaliationTarget = null;
        this.fleeFromPos = pos;
        this.fleeTicks = 80;
    }

    public boolean method_6481(class_1799 stack) {
        return stack.method_31573(class_3489.field_49935);
    }

    @Nullable
    public DeerEntity createChild(class_3218 world, class_1296 entity) {
        DeerEntity child = (DeerEntity)GotEntities.DEER.method_5883((class_1937)world);
        if (child != null) {
            int n;
            if (this.field_5974.method_43056() || !(entity instanceof DeerEntity)) {
                n = this.getVariant();
            } else {
                DeerEntity other = (DeerEntity)entity;
                n = other.getVariant();
            }
            child.setVariant(n);
        }
        return child;
    }

    public void method_5983() {
        super.method_5983();
        if (this.method_6109()) {
            this.method_5615(60);
        }
    }

    public class_1269 method_5992(class_1657 player, class_1268 hand) {
        class_1799 stack = player.method_5998(hand);
        if (stack.method_31574(class_1802.field_8868)) {
            if (!this.method_37908().field_9236 && this.method_27072()) {
                this.method_6636(class_3419.field_15248);
                this.method_32875((class_6880)class_5712.field_28730, (class_1297)player);
                stack.method_7970(1, (class_1309)player, DeerEntity.method_56079((class_1268)hand));
                return class_1269.field_5812;
            }
            return class_1269.field_21466;
        }
        return super.method_5992(player, hand);
    }

    public void method_6636(class_3419 shearedSoundCategory) {
        this.method_37908().method_43129(null, (class_1297)this, class_3417.field_14975, shearedSoundCategory, 1.0f, 1.0f);
        this.setSheared(true);
        class_1792 drop = this.field_5974.method_43057() < 0.9f ? class_1802.field_8606 : GotItems.DEER_ANTLERS;
        class_1542 itemEntity = this.method_5870((class_1935)drop, 1);
        if (itemEntity != null) {
            itemEntity.method_18799(itemEntity.method_18798().method_1031((double)((this.field_5974.method_43057() - this.field_5974.method_43057()) * 0.1f), (double)(this.field_5974.method_43057() * 0.05f), (double)((this.field_5974.method_43057() - this.field_5974.method_43057()) * 0.1f)));
        }
    }

    public boolean method_27072() {
        return this.method_5805() && !this.isSheared() && !this.method_6109();
    }

    public void method_5711(byte status) {
        if (status == 10) {
            this.eatGrassTimer = 40;
        } else {
            super.method_5711(status);
        }
    }

    public float getNeckAngle(float delta) {
        if (this.eatGrassTimer <= 0) {
            return 0.0f;
        }
        if (this.eatGrassTimer >= 4 && this.eatGrassTimer <= 36) {
            return 1.0f;
        }
        return this.eatGrassTimer < 4 ? ((float)this.eatGrassTimer - delta) / 4.0f : -((float)(this.eatGrassTimer - 40) - delta) / 4.0f;
    }

    public float getHeadAngle(float delta) {
        if (this.eatGrassTimer > 4 && this.eatGrassTimer <= 36) {
            float progress = ((float)(this.eatGrassTimer - 4) - delta) / 32.0f;
            return 0.62831855f + 0.21991149f * class_3532.method_15374((float)(progress * 28.7f));
        }
        return this.eatGrassTimer > 0 ? 0.62831855f : this.method_36455() * ((float)Math.PI / 180);
    }

    public float getHeadbuttAnimationProgress(float tickDelta) {
        int ticks = this.getHeadbuttTicks();
        if (ticks <= 0) {
            return 0.0f;
        }
        return class_3532.method_15363((float)(((float)(28 - ticks) + tickDelta) / 28.0f), (float)0.0f, (float)1.0f);
    }

    public boolean isSheared() {
        return (Boolean)this.field_6011.method_12789(SHEARED) != false && this.method_37908().method_8510() < (Long)this.field_6011.method_12789(HORN_REGROW_TIME);
    }

    public void setSheared(boolean sheared) {
        this.field_6011.method_12778(SHEARED, (Object)sheared);
        this.field_6011.method_12778(HORN_REGROW_TIME, (Object)(sheared ? this.method_37908().method_8510() + 1200L : 0L));
    }

    public int getVariant() {
        return class_3532.method_15340((int)((Integer)this.field_6011.method_12789(VARIANT)), (int)1, (int)3);
    }

    public void setVariant(int variant) {
        this.field_6011.method_12778(VARIANT, (Object)class_3532.method_15340((int)variant, (int)1, (int)3));
    }

    private int getHeadbuttTicks() {
        return (Integer)this.field_6011.method_12789(HEADBUTT_TICKS);
    }

    private void setHeadbuttTicks(int ticks) {
        this.field_6011.method_12778(HEADBUTT_TICKS, (Object)Math.max(0, ticks));
    }

    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10556(SHEARED_KEY, this.isSheared());
        nbt.method_10544(HORN_REGROW_TIME_KEY, this.isSheared() ? (Long)this.field_6011.method_12789(HORN_REGROW_TIME) : 0L);
        nbt.method_10569(VARIANT_KEY, this.getVariant());
    }

    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        this.field_6011.method_12778(SHEARED, (Object)nbt.method_10577(SHEARED_KEY));
        this.field_6011.method_12778(HORN_REGROW_TIME, (Object)nbt.method_10537(HORN_REGROW_TIME_KEY));
        if (nbt.method_10545(VARIANT_KEY)) {
            this.setVariant(nbt.method_10550(VARIANT_KEY));
        }
    }

    @Nullable
    public class_1315 method_5943(class_5425 world, class_1266 difficulty, class_3730 spawnReason, @Nullable class_1315 entityData) {
        this.setVariant(DeerEntity.randomVariant(world.method_8409()));
        return super.method_5943(world, difficulty, spawnReason, entityData);
    }

    private static int randomVariant(class_5819 random) {
        return 1 + random.method_43048(3);
    }

    protected class_3414 method_5994() {
        return class_3417.field_14603;
    }

    protected class_3414 method_6011(class_1282 source) {
        return class_3417.field_14730;
    }

    protected class_3414 method_6002() {
        return class_3417.field_14814;
    }

    protected void method_5712(class_2338 pos, class_2680 state) {
        this.method_5783(class_3417.field_14870, 0.15f, 1.0f);
    }

    private static final class HeadbuttGoal
    extends class_1352 {
        private final DeerEntity deer;
        private class_1309 target;
        private int ticks;
        private boolean impacted;

        private HeadbuttGoal(DeerEntity deer) {
            this.deer = deer;
            this.method_6265(EnumSet.of(class_1352.class_4134.field_18405, class_1352.class_4134.field_18406));
        }

        public boolean method_6264() {
            LivingTarget livingTarget = this.deer.retaliationTarget;
            if (livingTarget == null || !this.deer.shouldRetaliateAgainst(livingTarget.entity())) {
                return false;
            }
            this.target = livingTarget.entity();
            return this.deer.method_5858((class_1297)this.target) <= 144.0;
        }

        public boolean method_6266() {
            return this.target != null && this.target.method_5805() && this.ticks < 28;
        }

        public void method_6269() {
            this.ticks = 0;
            this.impacted = false;
            this.deer.setHeadbuttTicks(28);
            this.deer.method_5942().method_6340();
        }

        public void method_6270() {
            class_243 fleePos;
            class_243 class_2432 = fleePos = this.target != null ? this.target.method_19538() : this.deer.fleeFromPos;
            if (fleePos != null) {
                this.deer.startFleeingFrom(fleePos);
            }
            this.target = null;
            this.deer.method_5942().method_6340();
        }

        public void method_6268() {
            ++this.ticks;
            this.deer.method_5988().method_6226((class_1297)this.target, 30.0f, 30.0f);
            this.deer.fleeFromPos = this.target.method_19538();
            if (this.ticks == 1 || this.ticks == 5) {
                this.deer.method_5942().method_6335((class_1297)this.target, 0.575);
            }
            if (this.ticks == 10) {
                class_243 direction = this.target.method_19538().method_1020(this.deer.method_19538()).method_1029();
                this.deer.method_18799(this.deer.method_18798().method_1031(direction.field_1352 * 0.45, 0.05, direction.field_1350 * 0.45));
                this.deer.field_6007 = true;
            }
            if (!this.impacted && this.ticks >= 13) {
                this.impacted = true;
                if (this.deer.method_5858((class_1297)this.target) <= 5.76) {
                    this.deer.method_6121((class_1297)this.target);
                }
            }
        }
    }

    private static final class FleeAfterHeadbuttGoal
    extends class_1352 {
        private final DeerEntity deer;
        private double targetX;
        private double targetY;
        private double targetZ;

        private FleeAfterHeadbuttGoal(DeerEntity deer) {
            this.deer = deer;
            this.method_6265(EnumSet.of(class_1352.class_4134.field_18405));
        }

        public boolean method_6264() {
            if (this.deer.fleeTicks <= 0 || this.deer.fleeFromPos == null) {
                return false;
            }
            class_243 target = class_5532.method_31511((class_1314)this.deer, (int)16, (int)7, (class_243)this.deer.fleeFromPos);
            if (target == null) {
                target = class_5532.method_31510((class_1314)this.deer, (int)8, (int)4);
            }
            if (target == null) {
                return false;
            }
            this.targetX = target.field_1352;
            this.targetY = target.field_1351;
            this.targetZ = target.field_1350;
            return true;
        }

        public boolean method_6266() {
            return this.deer.fleeTicks > 0 && !this.deer.method_5942().method_6357();
        }

        public void method_6269() {
            this.deer.method_5942().method_6337(this.targetX, this.targetY, this.targetZ, 0.575);
        }

        public void method_6268() {
            this.deer.method_5942().method_6344(0.575);
        }
    }

    private record LivingTarget(class_1309 entity) {
    }
}

