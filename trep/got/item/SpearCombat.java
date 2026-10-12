/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Either
 *  net.minecraft.class_1268
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1496
 *  net.minecraft.class_1657
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2784
 *  net.minecraft.class_3218
 *  net.minecraft.class_3532
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 *  net.minecraft.class_5134
 */
package trep.got.item;

import com.mojang.datafixers.util.Either;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_1268;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1496;
import net.minecraft.class_1657;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2784;
import net.minecraft.class_3218;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_5134;
import trep.got.item.SpearItem;
import trep.got.item.SpearUser;
import trep.got.troop.GotTroops;

public final class SpearCombat {
    private SpearCombat() {
    }

    public static Collection<class_3966> collectPiercingCollisions(class_1309 entity, float minReach, float maxReach, float hitboxMargin, Predicate<class_1297> hitPredicate) {
        class_243 look = entity.method_5631(entity.method_36455(), entity.method_5791());
        class_243 eyePos = entity.method_33571();
        class_243 minReachPos = eyePos.method_1019(look.method_1021((double)minReach));
        double movementAlongLook = entity.method_18798().method_1026(look);
        class_243 maxReachPos = eyePos.method_1019(look.method_1021((double)maxReach + Math.max(0.0, movementAlongLook)));
        return (Collection)SpearCombat.collectPiercingCollisions((class_1297)entity, eyePos, minReachPos, hitPredicate, maxReachPos, hitboxMargin).map(hitResult -> List.of(), hitResults -> hitResults);
    }

    public static boolean canHit(class_1297 attacker, class_1297 target) {
        if (!target.method_49108() || target.method_5655() || !target.method_5805()) {
            return false;
        }
        if (GotTroops.areAlliedPartyEntities(attacker, target)) {
            return false;
        }
        if (target instanceof class_1657) {
            class_1657 playerAttacker;
            class_1657 playerTarget = (class_1657)target;
            if (attacker instanceof class_1657 && !(playerAttacker = (class_1657)attacker).method_7256(playerTarget)) {
                return false;
            }
        }
        return !attacker.method_5794(target);
    }

    public static boolean stab(class_1309 attacker, class_1268 hand, float minReach, float maxReach, float hitboxMargin, boolean dealsKnockback, boolean dismounts) {
        class_1937 class_19372 = attacker.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return false;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        float damage = (float)attacker.method_45325(class_5134.field_23721);
        boolean hitAny = false;
        for (class_3966 entityHitResult : SpearCombat.collectPiercingCollisions(attacker, minReach, maxReach, hitboxMargin, entity -> SpearCombat.canHit((class_1297)attacker, entity))) {
            class_1297 target = entityHitResult.method_17782();
            if (target == null || !target.method_5805()) continue;
            hitAny |= SpearCombat.pierce(serverWorld, attacker, target, damage, dealsKnockback, dismounts);
        }
        attacker.method_23667(hand, true);
        if (attacker instanceof class_1657) {
            class_1657 player = (class_1657)attacker;
            player.method_7350();
        }
        return hitAny;
    }

    public static boolean performChargeTick(class_1309 user, SpearItem spear, int elapsedUseTicks) {
        class_243 movement;
        class_3218 serverWorld;
        block11: {
            block10: {
                class_1937 class_19372 = user.method_37908();
                if (!(class_19372 instanceof class_3218)) break block10;
                serverWorld = (class_3218)class_19372;
                if (elapsedUseTicks >= spear.chargeDelayTicks()) break block11;
            }
            return false;
        }
        ChargeStage stage = SpearCombat.resolveChargeStage(spear, elapsedUseTicks);
        class_243 look = user.method_5720();
        double speedAlongLook = look.method_1026(movement = SpearCombat.amplifiedMovement((class_1297)user));
        if (speedAlongLook < (double)spear.minSpeedForChargeDamage()) {
            return false;
        }
        boolean hitAny = false;
        float minReach = spear.minReach();
        float maxReach = spear.maxReach();
        for (class_3966 entityHitResult : SpearCombat.collectPiercingCollisions(user, minReach, maxReach, spear.hitboxMargin(), target -> SpearCombat.canHit((class_1297)user, target))) {
            boolean dismounts;
            boolean knockback;
            float damage;
            SpearUser spearUser;
            class_1297 target2 = entityHitResult.method_17782();
            if (!(user instanceof SpearUser) || (spearUser = (SpearUser)user).agob$isInSpearPiercingCooldown(target2, spear.contactCooldownTicks())) continue;
            spearUser.agob$startSpearPiercingCooldown(target2);
            double relativeSpeed = Math.max(0.0, speedAlongLook - look.method_1026(SpearCombat.amplifiedMovement(target2)));
            float baseDamage = (float)user.method_45325(class_5134.field_23721);
            if (!SpearCombat.pierce(serverWorld, user, target2, damage = (switch (stage.ordinal()) {
                default -> throw new MatchException(null, null);
                case 0, 1 -> baseDamage + (float)(relativeSpeed * (double)spear.chargeDamageMultiplier());
                case 2 -> Math.max(1.0f, baseDamage * 0.35f);
            }), knockback = stage != ChargeStage.DISENGAGED, dismounts = stage == ChargeStage.ENGAGED && speedAlongLook >= (double)spear.minSpeedForDismount())) continue;
            hitAny = true;
        }
        if (hitAny && user instanceof SpearUser) {
            SpearUser spearUser = (SpearUser)user;
            spearUser.agob$markSpearChargeHit();
        }
        return hitAny;
    }

    public static boolean performMountedLanceTick(class_1309 user, SpearItem spear) {
        class_243 movement;
        class_3218 serverWorld;
        block7: {
            block6: {
                class_1937 class_19372 = user.method_37908();
                if (!(class_19372 instanceof class_3218)) break block6;
                serverWorld = (class_3218)class_19372;
                if (SpearCombat.isLanceActive(user, spear)) break block7;
            }
            return false;
        }
        class_243 look = user.method_5720();
        double speedAlongLook = look.method_1026(movement = SpearCombat.amplifiedMovement((class_1297)user));
        if (speedAlongLook < (double)spear.minSpeedForChargeDamage()) {
            return false;
        }
        boolean hitAny = false;
        for (class_3966 entityHitResult : SpearCombat.collectPiercingCollisions(user, spear.minReach(), spear.maxReach(), spear.hitboxMargin(), target -> SpearCombat.canHit((class_1297)user, target))) {
            SpearUser spearUser;
            class_1297 target2 = entityHitResult.method_17782();
            if (!(user instanceof SpearUser) || (spearUser = (SpearUser)user).agob$isInSpearPiercingCooldown(target2, spear.contactCooldownTicks())) continue;
            spearUser.agob$startSpearPiercingCooldown(target2);
            double relativeSpeed = Math.max(0.0, speedAlongLook - look.method_1026(SpearCombat.amplifiedMovement(target2)));
            float baseDamage = (float)user.method_45325(class_5134.field_23721);
            float damage = baseDamage + (float)class_3532.method_15357((double)(relativeSpeed * (double)spear.chargeDamageMultiplier()));
            if (!SpearCombat.pierce(serverWorld, user, target2, damage, true, false)) continue;
            hitAny = true;
        }
        if (hitAny && user instanceof SpearUser) {
            SpearUser spearUser = (SpearUser)user;
            spearUser.agob$markSpearChargeHit();
        }
        return hitAny;
    }

    public static boolean performBraceTick(class_1309 user, SpearItem spear) {
        class_3218 serverWorld;
        block6: {
            block5: {
                class_1937 class_19372 = user.method_37908();
                if (!(class_19372 instanceof class_3218)) break block5;
                serverWorld = (class_3218)class_19372;
                if (SpearCombat.isBraceActive(user)) break block6;
            }
            return false;
        }
        class_243 facing = SpearCombat.horizontalFacing(user);
        class_238 searchBox = user.method_5829().method_1009((double)spear.maxReach(), 1.5, (double)spear.maxReach());
        for (class_1309 target : serverWorld.method_8390(class_1309.class, searchBox, entity -> entity.method_5805() && entity != user && entity.method_5854() instanceof class_1496 && SpearCombat.canHit((class_1297)user, (class_1297)entity))) {
            double closingSpeed;
            SpearUser spearUser;
            if (user instanceof SpearUser && (spearUser = (SpearUser)user).agob$isInSpearPiercingCooldown((class_1297)target, spear.contactCooldownTicks())) continue;
            class_243 toTarget = target.method_19538().method_1020(user.method_19538());
            class_243 toTargetHorizontal = new class_243(toTarget.field_1352, 0.0, toTarget.field_1350);
            if (toTargetHorizontal.method_1027() > (double)(spear.maxReach() * spear.maxReach()) || toTargetHorizontal.method_1027() < 1.0E-4 || facing.method_1026(toTargetHorizontal.method_1029()) < 0.65) continue;
            class_243 targetVelocity = SpearCombat.amplifiedMovement((class_1297)target);
            class_243 towardBrace = user.method_19538().method_1020(target.method_19538());
            class_243 towardBraceHorizontal = new class_243(towardBrace.field_1352, 0.0, towardBrace.field_1350);
            if (towardBraceHorizontal.method_1027() < 1.0E-4 || (closingSpeed = targetVelocity.method_1026(towardBraceHorizontal.method_1029())) < (double)spear.minSpeedForChargeDamage()) continue;
            if (user instanceof SpearUser) {
                SpearUser spearUser2 = (SpearUser)user;
                spearUser2.agob$startSpearPiercingCooldown((class_1297)target);
                spearUser2.agob$markSpearBraceHit();
            }
            float damage = (float)user.method_45325(class_5134.field_23721) + 1.0f;
            return SpearCombat.pierce(serverWorld, user, (class_1297)target, damage, true, false);
        }
        return false;
    }

    public static class_243 amplifiedMovement(class_1297 entity) {
        class_1297 vehicleRoot = entity.method_5765() ? entity.method_5668() : entity;
        class_243 delta = vehicleRoot.method_18798();
        if (delta.method_1027() < 1.0E-6) {
            delta = vehicleRoot.method_19538().method_1023(vehicleRoot.field_6014, vehicleRoot.field_6036, vehicleRoot.field_5969);
        }
        return delta.method_1021(20.0);
    }

    public static ChargeStage resolveChargeStage(SpearItem spear, int elapsedUseTicks) {
        int activeTicks = Math.max(0, elapsedUseTicks - spear.chargeDelayTicks());
        if (activeTicks <= spear.engageWindowTicks()) {
            return ChargeStage.ENGAGED;
        }
        if (activeTicks <= spear.engageWindowTicks() * 2) {
            return ChargeStage.TIRED;
        }
        return ChargeStage.DISENGAGED;
    }

    public static boolean isLanceActive(class_1309 user, SpearItem spear) {
        if (!(user.method_5854() instanceof class_1496)) {
            return false;
        }
        class_243 facing = SpearCombat.horizontalFacing(user);
        class_243 movement = SpearCombat.amplifiedMovement(user.method_5668());
        class_243 horizontalMovement = new class_243(movement.field_1352, 0.0, movement.field_1350);
        if (horizontalMovement.method_1027() < 0.25) {
            return false;
        }
        return facing.method_1026(horizontalMovement.method_1029()) > 0.55;
    }

    public static boolean isBraceActive(class_1309 user) {
        return !user.method_5765() && user.method_5715();
    }

    public static class_243 horizontalFacing(class_1309 entity) {
        class_243 facing = entity.method_5720();
        class_243 horizontal = new class_243(facing.field_1352, 0.0, facing.field_1350);
        if (horizontal.method_1027() < 1.0E-4) {
            return new class_243(0.0, 0.0, 1.0);
        }
        return horizontal.method_1029();
    }

    private static boolean pierce(class_3218 world, class_1309 attacker, class_1297 target, float damage, boolean dealsKnockback, boolean dismounts) {
        class_1282 class_12822;
        if (GotTroops.areAlliedPartyEntities((class_1297)attacker, target)) {
            return false;
        }
        if (attacker instanceof class_1657) {
            class_1657 player = (class_1657)attacker;
            class_12822 = world.method_48963().method_48802(player);
        } else {
            class_1308 mob;
            class_12822 = world.method_48963().method_48812(attacker instanceof class_1308 ? (mob = (class_1308)attacker) : null);
        }
        class_1282 damageSource = class_12822;
        boolean damaged = target.method_5643(damageSource, damage);
        if (!damaged) {
            return false;
        }
        if (dealsKnockback) {
            class_243 look = attacker.method_5720().method_1029();
            target.method_5762(look.field_1352 * 0.45, 0.08, look.field_1350 * 0.45);
            target.field_6037 = true;
        }
        if (attacker.method_5765() && target.method_5765() && attacker.method_59922().method_43057() < 0.1f) {
            target.method_5848();
        }
        if (dismounts && target.method_5765()) {
            target.method_5848();
        }
        return true;
    }

    private static Either<class_3965, Collection<class_3966>> collectPiercingCollisions(class_1297 entity, class_243 pos, class_243 minReach, Predicate<class_1297> hitPredicate, class_243 maxReach, float hitboxMargin) {
        class_1937 world = entity.method_37908();
        class_3965 blockHitResult = SpearCombat.getCollisionsIncludingWorldBorder(world, entity, new class_3959(pos, maxReach, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, entity));
        if (blockHitResult.method_17783() != class_239.class_240.field_1333 && pos.method_1025(maxReach = blockHitResult.method_17784()) < pos.method_1025(minReach)) {
            return Either.left((Object)blockHitResult);
        }
        class_238 box = class_238.method_30048((class_243)minReach, (double)hitboxMargin, (double)hitboxMargin, (double)hitboxMargin).method_18804(maxReach.method_1020(minReach)).method_1014(1.0);
        Collection<class_3966> hits = SpearCombat.collectPiercingCollisions(world, entity, minReach, maxReach, box, hitPredicate, hitboxMargin, true);
        return !hits.isEmpty() ? Either.right(hits) : Either.left((Object)blockHitResult);
    }

    private static Collection<class_3966> collectPiercingCollisions(class_1937 world, class_1297 entity, class_243 from, class_243 to, class_238 box, Predicate<class_1297> hitPredicate, float hitboxMargin, boolean includeContained) {
        ArrayList<class_3966> hits = new ArrayList<class_3966>();
        for (class_1297 other : world.method_8333(entity, box, hitPredicate)) {
            class_243 otherCenter;
            Optional expandedHit;
            class_238 otherBox = other.method_5829();
            if (includeContained && otherBox.method_1006(from)) {
                hits.add(new class_3966(other, from));
                continue;
            }
            Optional hit = otherBox.method_992(from, to);
            if (hit.isPresent()) {
                hits.add(new class_3966(other, (class_243)hit.get()));
                continue;
            }
            if (hitboxMargin <= 0.0f || (expandedHit = otherBox.method_1014((double)hitboxMargin).method_992(from, to)).isEmpty()) continue;
            class_243 expandedPos = (class_243)expandedHit.get();
            class_3965 blockHitResult = SpearCombat.getCollisionsIncludingWorldBorder(world, entity, new class_3959(expandedPos, otherCenter = otherBox.method_1005(), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, entity));
            if (blockHitResult.method_17783() != class_239.class_240.field_1333) {
                otherCenter = blockHitResult.method_17784();
            }
            other.method_5829().method_992(expandedPos, otherCenter).ifPresent(vec3d -> hits.add(new class_3966(other, vec3d)));
        }
        return hits;
    }

    private static class_3965 getCollisionsIncludingWorldBorder(class_1937 world, class_1297 source, class_3959 context) {
        class_243 direction;
        class_2338 hitPos;
        class_243 start = context.method_17750();
        class_243 end = context.method_17747();
        class_3965 blockHitResult = world.method_17742(context);
        int guard = 0;
        while (blockHitResult.method_17783() != class_239.class_240.field_1333 && guard++ < 8 && !world.method_8320(hitPos = blockHitResult.method_17777()).method_26212((class_1922)world, hitPos) && !((direction = end.method_1020(start)).method_1027() < 1.0E-6)) {
            class_243 nextStart = blockHitResult.method_17784().method_1019(direction.method_1029().method_1021(0.05));
            blockHitResult = world.method_17742(new class_3959(nextStart, end, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, source));
        }
        class_2784 worldBorder = world.method_8621();
        if (worldBorder.method_61115(context.method_17750()) && !worldBorder.method_61115(blockHitResult.method_17784())) {
            class_243 delta = blockHitResult.method_17784().method_1020(context.method_17750());
            class_2350 direction2 = class_2350.method_10142((double)delta.field_1352, (double)delta.field_1351, (double)delta.field_1350);
            class_243 clamped = blockHitResult.method_17784();
            clamped = new class_243(class_3532.method_15350((double)clamped.field_1352, (double)worldBorder.method_11976(), (double)(worldBorder.method_11963() - (double)1.0E-5f)), clamped.field_1351, class_3532.method_15350((double)clamped.field_1350, (double)worldBorder.method_11958(), (double)(worldBorder.method_11977() - (double)1.0E-5f)));
            return new class_3965(clamped, direction2, class_2338.method_49638((class_2374)clamped), false);
        }
        return blockHitResult;
    }

    public static enum ChargeStage {
        ENGAGED,
        TIRED,
        DISENGAGED;

    }
}

