/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1352
 *  net.minecraft.class_1352$class_4134
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_3218
 */
package trep.got.entity;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1352;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2902;
import net.minecraft.class_3218;
import trep.got.entity.AbstractKingdomGuardNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;

final class KingdomGuardPatrolGoal
extends class_1352 {
    private static final int CHAT_RANGE = 6;
    private static final int MIN_ROUTE_RADIUS = 18;
    private static final int TARGET_ATTEMPTS = 18;
    private final AbstractKingdomGuardNpcEntity guard;
    private int cooldown;
    private int chatTicks;
    private AbstractKingdomNpcEntity chatTarget;
    private double targetX;
    private double targetY;
    private double targetZ;

    KingdomGuardPatrolGoal(AbstractKingdomGuardNpcEntity guard) {
        this.guard = guard;
        this.method_6265(EnumSet.of(class_1352.class_4134.field_18405, class_1352.class_4134.field_18406));
    }

    public boolean method_6264() {
        KingdomNpcRole role = this.guard.getNpcRole();
        long now = this.guard.method_37908().method_8510();
        if (!role.isGuardDuty() || this.guard.method_5968() != null || this.guard.method_6150() || this.guard.isInDialogueScene() || !this.guard.canStartNpcSocialInteraction(now)) {
            return false;
        }
        if (this.cooldown > 0) {
            --this.cooldown;
            return false;
        }
        if (role != KingdomNpcRole.PATROL) {
            return this.pickStationTarget(role == KingdomNpcRole.GATE_WATCHER ? 6 : 18);
        }
        if (this.guard.method_59922().method_43057() < 0.18f && this.pickChatTarget(now)) {
            this.chatTicks = 45 + this.guard.method_59922().method_43048(75);
            return true;
        }
        return this.pickRouteTarget();
    }

    public boolean method_6266() {
        if (!this.guard.getNpcRole().isGuardDuty() || this.guard.method_5968() != null || this.guard.isInDialogueScene()) {
            return false;
        }
        if (this.chatTarget != null) {
            return this.chatTicks > 0 && this.chatTarget.method_5805() && this.guard.method_5858((class_1297)this.chatTarget) <= 36.0;
        }
        return this.guard.method_6150();
    }

    public void method_6269() {
        if (this.chatTarget != null) {
            this.guard.method_5942().method_6340();
            this.cooldown = 35 + this.guard.method_59922().method_43048(95);
            return;
        }
        double speed = this.guard.method_59922().method_43057() < 0.24f ? 0.82 : 1.04;
        this.guard.method_5942().method_6337(this.targetX, this.targetY, this.targetZ, speed);
        this.cooldown = 18 + this.guard.method_59922().method_43048(70);
    }

    public void method_6268() {
        if (this.chatTarget == null) {
            return;
        }
        --this.chatTicks;
        this.guard.method_5942().method_6340();
        if (!this.guard.agob$isNpcTalking()) {
            long talkUntil = this.guard.method_37908().method_8510() + (long)this.chatTicks;
            this.guard.startNpcTalking(this.chatTarget, talkUntil, Math.floorMod(this.guard.method_5667().hashCode() + this.guard.field_6012, 4));
            this.chatTarget.startNpcTalking(this.guard, talkUntil, Math.floorMod(this.chatTarget.method_5667().hashCode() + this.guard.field_6012 + 1, 4));
        }
        this.guard.method_5988().method_6226((class_1297)this.chatTarget, 30.0f, 30.0f);
        this.chatTarget.method_5988().method_6226((class_1297)this.guard, 30.0f, 30.0f);
    }

    public void method_6270() {
        if (this.chatTarget != null) {
            this.guard.clearNpcSocialInteraction(this.chatTarget);
            this.chatTarget.clearNpcSocialInteraction(this.guard);
        }
        this.chatTarget = null;
        this.chatTicks = 0;
    }

    private boolean pickChatTarget(long now) {
        class_1937 class_19372 = this.guard.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return false;
        }
        class_3218 world = (class_3218)class_19372;
        class_238 box = class_238.method_30048((class_243)this.guard.method_19538(), (double)12.0, (double)4.0, (double)12.0);
        List candidates = world.method_8390(AbstractKingdomNpcEntity.class, box, npc -> npc != this.guard && npc.method_5805() && this.guard.got$getCastle().equals(npc.got$getCastle()) && !npc.isInDialogueScene() && npc.canStartNpcSocialInteraction(now) && (npc.getNpcRole() == KingdomNpcRole.CIVILIAN || npc.getNpcRole() == KingdomNpcRole.PATROL));
        if (candidates.isEmpty()) {
            return false;
        }
        candidates.sort(Comparator.comparingDouble(arg_0 -> ((AbstractKingdomGuardNpcEntity)this.guard).method_5858(arg_0)));
        this.chatTarget = (AbstractKingdomNpcEntity)candidates.get(Math.min(candidates.size() - 1, this.guard.method_59922().method_43048(Math.min(3, candidates.size()))));
        long reservedUntil = world.method_8510() + 180L;
        if (!this.guard.reserveNpcSocialInteraction(this.chatTarget, reservedUntil)) {
            this.chatTarget = null;
            return false;
        }
        if (!this.chatTarget.reserveNpcSocialInteraction(this.guard, reservedUntil)) {
            this.guard.clearNpcSocialInteraction(this.chatTarget);
            this.chatTarget = null;
            return false;
        }
        return true;
    }

    private boolean pickRouteTarget() {
        class_3218 world;
        block4: {
            block3: {
                class_1937 class_19372 = this.guard.method_37908();
                if (!(class_19372 instanceof class_3218)) break block3;
                world = (class_3218)class_19372;
                if (this.guard.getPointOfInterest() != null) break block4;
            }
            return false;
        }
        class_2338 poi = this.guard.getPointOfInterest();
        int radius = Math.max(18, this.guard.getPointOfInterestRadius());
        boolean verticalRoute = (Math.abs(this.guard.method_5667().getLeastSignificantBits()) & 1L) == 0L;
        for (int attempt = 0; attempt < 18; ++attempt) {
            int z;
            int along = this.guard.method_59922().method_39332(-radius, radius);
            int side = this.guard.method_59922().method_39332(-3, 3);
            int x = verticalRoute ? poi.method_10263() + side : poi.method_10263() + along;
            class_2338 candidate = KingdomGuardPatrolGoal.surfaceWalkable(world, x, z = verticalRoute ? poi.method_10260() + along : poi.method_10260() + side);
            if (candidate == null || this.guard.method_5942().method_6348(candidate, 0) == null) continue;
            this.targetX = (double)candidate.method_10263() + 0.5;
            this.targetY = candidate.method_10264();
            this.targetZ = (double)candidate.method_10260() + 0.5;
            return true;
        }
        return false;
    }

    private boolean pickStationTarget(int radius) {
        class_3218 world;
        block4: {
            block3: {
                class_1937 class_19372 = this.guard.method_37908();
                if (!(class_19372 instanceof class_3218)) break block3;
                world = (class_3218)class_19372;
                if (this.guard.getPointOfInterest() != null) break block4;
            }
            return false;
        }
        class_2338 poi = this.guard.getPointOfInterest();
        for (int attempt = 0; attempt < 18; ++attempt) {
            class_2338 candidate;
            int x = poi.method_10263() + this.guard.method_59922().method_39332(-radius, radius);
            int z = poi.method_10260() + this.guard.method_59922().method_39332(-radius, radius);
            if (new class_2338(x, poi.method_10264(), z).method_10262((class_2382)poi) > (double)(radius * radius) || (candidate = KingdomGuardPatrolGoal.surfaceWalkable(world, x, z)) == null || this.guard.method_5942().method_6348(candidate, 0) == null) continue;
            this.targetX = (double)candidate.method_10263() + 0.5;
            this.targetY = candidate.method_10264();
            this.targetZ = (double)candidate.method_10260() + 0.5;
            return true;
        }
        return false;
    }

    private static class_2338 surfaceWalkable(class_3218 world, int x, int z) {
        int topY = world.method_8624(class_2902.class_2903.field_13203, x, z);
        if (topY <= world.method_31607()) {
            return null;
        }
        class_2338 feet = new class_2338(x, topY, z);
        class_2338 ground = feet.method_10074();
        if (!(world.method_8320(ground).method_26212((class_1922)world, ground) && world.method_8320(feet).method_26215() && world.method_8320(feet.method_10084()).method_26215())) {
            return null;
        }
        return feet;
    }
}

