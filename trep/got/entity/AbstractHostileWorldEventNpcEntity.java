/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1314
 *  net.minecraft.class_1352
 *  net.minecraft.class_1366
 *  net.minecraft.class_1399
 *  net.minecraft.class_1657
 *  net.minecraft.class_1665
 *  net.minecraft.class_1667
 *  net.minecraft.class_1753
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_3417
 */
package trep.got.entity;

import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1314;
import net.minecraft.class_1352;
import net.minecraft.class_1366;
import net.minecraft.class_1399;
import net.minecraft.class_1657;
import net.minecraft.class_1665;
import net.minecraft.class_1667;
import net.minecraft.class_1753;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_3417;
import trep.got.entity.AbstractWorldEventNpcEntity;
import trep.got.item.BowCombatHelper;

public abstract class AbstractHostileWorldEventNpcEntity
extends AbstractWorldEventNpcEntity {
    protected AbstractHostileWorldEventNpcEntity(class_1299<? extends AbstractWorldEventNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    protected void method_5959() {
        super.method_5959();
        this.field_6201.method_6277(2, (class_1352)new class_1366((class_1314)this, 1.1, false));
        this.field_6185.method_6277(1, (class_1352)new class_1399((class_1314)this, new Class[0]));
    }

    @Override
    public void method_5773() {
        super.method_5773();
        if (this.method_37908().method_8608() || this.isInDialogueScene() || !this.method_5805()) {
            return;
        }
        this.acquireNearbyPlayerTarget();
        class_1309 target = this.method_5968();
        if (target == null || !target.method_5805() || !this.usesBowLoadout()) {
            return;
        }
        this.tickBowCombat(target);
    }

    protected boolean usesBowLoadout() {
        return BowCombatHelper.isBow(this.method_6118(class_1304.field_6173));
    }

    protected boolean isHostileToPlayers() {
        return true;
    }

    private void acquireNearbyPlayerTarget() {
        if (!this.isHostileToPlayers() || this.method_5968() != null || this.field_6012 % 10 != 0) {
            return;
        }
        class_1657 nearestPlayer = this.method_37908().method_18460((class_1297)this, 16.0);
        if (nearestPlayer != null && nearestPlayer.method_5805()) {
            this.method_5980((class_1309)nearestPlayer);
        }
    }

    private void tickBowCombat(class_1309 target) {
        this.method_5988().method_6226((class_1297)target, 30.0f, 30.0f);
        double distanceSq = this.method_5858((class_1297)target);
        if (distanceSq > 100.0) {
            this.method_5942().method_6335((class_1297)target, 1.05);
            return;
        }
        this.method_5942().method_6340();
        class_1799 bow = this.method_6118(class_1304.field_6173);
        if (this.method_6057((class_1297)target) && this.field_6012 % BowCombatHelper.getAttackIntervalTicks(bow, this.getBowAttackBaseIntervalTicks()) == 0) {
            this.shootAt(target);
        }
    }

    protected int getBowAttackBaseIntervalTicks() {
        return 20;
    }

    public void method_6078(class_1282 damageSource) {
        this.method_5980(null);
        this.method_5942().method_6340();
        super.method_6078(damageSource);
    }

    private void shootAt(class_1309 target) {
        if (!this.method_5805()) {
            return;
        }
        class_1799 bow = this.method_6118(class_1304.field_6173);
        if (!(bow.method_7909() instanceof class_1753)) {
            return;
        }
        class_1667 arrow = new class_1667(this.method_37908(), (class_1309)this, new class_1799((class_1935)class_1802.field_8107), bow);
        BowCombatHelper.applyProjectileModifiers(bow, (class_1665)arrow);
        double dx = target.method_23317() - this.method_23317();
        double dz = target.method_23321() - this.method_23321();
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
        double dy = target.method_23323(0.3333333333333333) - arrow.method_23318() + horizontalDistance * 0.2;
        arrow.method_7485(dx, dy, dz, 1.6f, 12.0f);
        this.method_37908().method_8649((class_1297)arrow);
        this.method_5783(class_3417.field_14633, 1.0f, 0.8f + this.method_59922().method_43057() * 0.4f);
    }
}

