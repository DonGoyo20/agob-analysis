/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1675
 *  net.minecraft.class_1676
 *  net.minecraft.class_1937
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_2394
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_3218
 *  net.minecraft.class_3532
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 */
package trep.got.entity;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1675;
import net.minecraft.class_1676;
import net.minecraft.class_1937;
import net.minecraft.class_239;
import net.minecraft.class_2394;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2945;
import net.minecraft.class_3218;
import net.minecraft.class_3532;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import trep.got.registry.GotEntities;
import trep.got.registry.GotParticles;

public final class DragonFireEntity
extends class_1676 {
    private static final int MAX_AGE = 18;
    private static final float DRAG = 0.985f;
    private static final float DAMAGE = 6.0f;

    public DragonFireEntity(class_1299<? extends DragonFireEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.field_5960 = true;
    }

    public DragonFireEntity(class_1937 world, class_1297 owner, class_243 velocity) {
        this(GotEntities.DRAGON_FIRE, world);
        this.method_7432(owner);
        this.method_18799(velocity);
        this.updateTrackedRotation(velocity);
    }

    public void method_5773() {
        class_239 hitResult;
        super.method_5773();
        class_243 velocity = this.method_18798();
        if (this.method_37908().field_9236) {
            this.spawnTrailParticles(velocity);
        }
        if ((hitResult = class_1675.method_49997((class_1297)this, this::method_26958)).method_17783() == class_239.class_240.field_1331) {
            this.method_7454((class_3966)hitResult);
            return;
        }
        if (hitResult.method_17783() == class_239.class_240.field_1332) {
            this.method_24920((class_3965)hitResult);
            return;
        }
        this.method_33574(this.method_19538().method_1019(velocity));
        this.method_5852();
        class_243 nextVelocity = velocity.method_1021((double)0.985f);
        this.method_18799(nextVelocity);
        this.updateTrackedRotation(nextVelocity);
        if (this.field_6012 >= 18) {
            this.method_31472();
        }
    }

    private void spawnTrailParticles(class_243 velocity) {
        class_1937 world = this.method_37908();
        class_243 basePos = this.method_19538().method_1020(velocity.method_1021(0.35));
        for (int index = 0; index < 2; ++index) {
            double progress = (double)index / 2.0;
            class_243 samplePos = basePos.method_1019(velocity.method_1021(progress * 0.5));
            world.method_8406((class_2394)GotParticles.DRAGON_FIRE, samplePos.field_1352 + (this.field_5974.method_43058() - 0.5) * 0.08, samplePos.field_1351 + (this.field_5974.method_43058() - 0.5) * 0.08, samplePos.field_1350 + (this.field_5974.method_43058() - 0.5) * 0.08, velocity.field_1352 * 0.12, velocity.field_1351 * 0.12, velocity.field_1350 * 0.12);
        }
    }

    private void updateTrackedRotation(class_243 velocity) {
        double horizontal = Math.sqrt(velocity.field_1352 * velocity.field_1352 + velocity.field_1350 * velocity.field_1350);
        if (horizontal < 1.0E-6 && Math.abs(velocity.field_1351) < 1.0E-6) {
            return;
        }
        this.method_36456((float)(class_3532.method_15349((double)velocity.field_1352, (double)velocity.field_1350) * 57.2957763671875));
        this.method_36457((float)(class_3532.method_15349((double)velocity.field_1351, (double)horizontal) * 57.2957763671875));
        this.field_5982 = this.method_36454();
        this.field_6004 = this.method_36455();
    }

    protected boolean method_26958(class_1297 entity) {
        class_1297 owner = this.method_24921();
        if (entity == owner) {
            return false;
        }
        if (owner != null && entity.method_5794(owner)) {
            return false;
        }
        return super.method_26958(entity);
    }

    protected void method_7454(class_3966 entityHitResult) {
        if (this.method_37908() instanceof class_3218) {
            class_1297 target = entityHitResult.method_17782();
            target.method_5639(3.0f);
            target.method_5643(this.method_48923().method_48811((class_1297)this, this.method_24921()), 6.0f);
        }
        this.method_31472();
    }

    protected void method_24920(class_3965 blockHitResult) {
        this.method_31472();
    }

    protected void method_5693(class_2945.class_9222 builder) {
    }

    protected void method_5749(class_2487 nbt) {
    }

    protected void method_5652(class_2487 nbt) {
    }
}

