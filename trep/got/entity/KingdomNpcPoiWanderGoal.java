/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1352
 *  net.minecraft.class_1352$class_4134
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_2680
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_3218
 */
package trep.got.entity;

import java.util.EnumSet;
import net.minecraft.class_1352;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2680;
import net.minecraft.class_2902;
import net.minecraft.class_3218;
import trep.got.entity.AbstractDialogueHumanoidEntity;
import trep.got.entity.AbstractKingdomHumanoidEntity;

final class KingdomNpcPoiWanderGoal
extends class_1352 {
    private final AbstractKingdomHumanoidEntity entity;
    private final double speed;
    private final int pauseTicks;
    private double targetX;
    private double targetY;
    private double targetZ;
    private double travelSpeed;
    private int cooldown;

    KingdomNpcPoiWanderGoal(AbstractKingdomHumanoidEntity entity, double speed, int pauseTicks) {
        this.entity = entity;
        this.speed = speed;
        this.pauseTicks = pauseTicks;
        this.method_6265(EnumSet.of(class_1352.class_4134.field_18405));
    }

    public boolean method_6264() {
        class_1937 class_19372;
        if (this.entity.method_5968() != null || this.entity.method_6150() || this.isHeldInDialogue()) {
            return false;
        }
        if (this.cooldown > 0) {
            --this.cooldown;
            return false;
        }
        class_2338 poiPos = this.entity.getPointOfInterest();
        if (poiPos == null || !((class_19372 = this.entity.method_37908()) instanceof class_3218)) {
            return false;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        int radius = this.entity.getPointOfInterestRadius();
        if (this.entity.method_24515().method_10262((class_2382)poiPos) > (double)(radius * radius)) {
            return this.pickTargetNearPoi(serverWorld, poiPos, Math.max(8, radius / 4), Math.max(10, radius * 3 / 4), 16);
        }
        return this.pickTargetNearPoi(serverWorld, poiPos, 6, radius, 6);
    }

    public boolean method_6266() {
        return !this.isHeldInDialogue() && this.entity.method_6150();
    }

    public void method_6269() {
        this.travelSpeed = this.entity.method_59922().method_43057() < 0.22f ? this.speed * 0.62 : this.speed * 1.18;
        this.entity.method_5942().method_6337(this.targetX, this.targetY, this.targetZ, this.travelSpeed);
        this.cooldown = this.pauseTicks;
    }

    public void method_6270() {
        this.entity.method_5942().method_6340();
    }

    private boolean isHeldInDialogue() {
        AbstractDialogueHumanoidEntity dialogueEntity;
        AbstractKingdomHumanoidEntity abstractKingdomHumanoidEntity = this.entity;
        return abstractKingdomHumanoidEntity instanceof AbstractDialogueHumanoidEntity && (dialogueEntity = (AbstractDialogueHumanoidEntity)abstractKingdomHumanoidEntity).isInDialogueScene();
    }

    static boolean startMovingToPoiArea(AbstractKingdomHumanoidEntity entity, class_3218 world, class_2338 poiPos, int radius, double speed) {
        class_2338 target = KingdomNpcPoiWanderGoal.findWalkablePoiTarget(entity, world, poiPos, Math.max(8, radius / 4), Math.max(10, radius * 3 / 4), 20);
        if (target == null) {
            target = KingdomNpcPoiWanderGoal.findWalkablePoiTarget(entity, world, poiPos, 0, radius, 20);
        }
        if (target == null) {
            return entity.method_5942().method_6337((double)poiPos.method_10263() + 0.5, (double)poiPos.method_10264(), (double)poiPos.method_10260() + 0.5, speed);
        }
        return entity.method_5942().method_6337((double)target.method_10263() + 0.5, (double)target.method_10264(), (double)target.method_10260() + 0.5, speed);
    }

    private boolean pickTargetNearPoi(class_3218 world, class_2338 poiPos, int minRadius, int maxRadius, int attempts) {
        class_2338 target = KingdomNpcPoiWanderGoal.findWalkablePoiTarget(this.entity, world, poiPos, minRadius, maxRadius, attempts);
        if (target == null) {
            return false;
        }
        this.targetX = (double)target.method_10263() + 0.5;
        this.targetY = target.method_10264();
        this.targetZ = (double)target.method_10260() + 0.5;
        return true;
    }

    private static class_2338 findWalkablePoiTarget(AbstractKingdomHumanoidEntity entity, class_3218 world, class_2338 poiPos, int minRadius, int maxRadius, int attempts) {
        int minDistanceSquared = minRadius * minRadius;
        int maxDistanceSquared = maxRadius * maxRadius;
        for (int attempt = 0; attempt < attempts; ++attempt) {
            class_2338 feet;
            int topY;
            class_2338 targetPos;
            int offsetZ;
            int offsetX = entity.method_59922().method_39332(-maxRadius, maxRadius);
            int distanceSquared = offsetX * offsetX + (offsetZ = entity.method_59922().method_39332(-maxRadius, maxRadius)) * offsetZ;
            if (distanceSquared > maxDistanceSquared || distanceSquared < minDistanceSquared || !world.method_8393((targetPos = poiPos.method_10069(offsetX, 0, offsetZ)).method_10263() >> 4, targetPos.method_10260() >> 4) || (topY = world.method_8624(class_2902.class_2903.field_13203, targetPos.method_10263(), targetPos.method_10260())) <= world.method_31607() || !KingdomNpcPoiWanderGoal.isWalkable(world, feet = new class_2338(targetPos.method_10263(), topY, targetPos.method_10260()))) continue;
            return feet;
        }
        return null;
    }

    private static boolean isWalkable(class_3218 world, class_2338 feet) {
        class_2338 ground = feet.method_10074();
        class_2680 groundState = world.method_8320(ground);
        return groundState.method_26212((class_1922)world, ground) && world.method_8320(feet).method_26215() && world.method_8320(feet.method_10084()).method_26215();
    }
}

