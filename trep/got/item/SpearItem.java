/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1271
 *  net.minecraft.class_1297
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1322
 *  net.minecraft.class_1322$class_1323
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1799
 *  net.minecraft.class_1831
 *  net.minecraft.class_1832
 *  net.minecraft.class_1839
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_2960
 *  net.minecraft.class_3417
 *  net.minecraft.class_5134
 *  net.minecraft.class_9274
 *  net.minecraft.class_9285
 */
package trep.got.item;

import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1322;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1831;
import net.minecraft.class_1832;
import net.minecraft.class_1839;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3417;
import net.minecraft.class_5134;
import net.minecraft.class_9274;
import net.minecraft.class_9285;
import trep.got.item.SpearCombat;

public class SpearItem
extends class_1831 {
    private static final class_2960 ATTACK_DAMAGE_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"spear_attack_damage");
    private static final class_2960 ATTACK_SPEED_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"spear_attack_speed");
    private static final class_2960 ATTACK_RANGE_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"spear_attack_range");
    private final float minReach;
    private final float maxReach;
    private final float hitboxMargin;
    private final float attackDamageBonus;
    private final int chargeDelayTicks;
    private final int contactCooldownTicks;
    private final float chargeDamageMultiplier;
    private final float minSpeedForChargeDamage;
    private final float minSpeedForDismount;
    private final int engageWindowTicks;

    public SpearItem(class_1832 material, float attackDamageBonus, float swingAnimationSeconds, float minReach, float maxReach, float hitboxMargin, int chargeDelayTicks, int contactCooldownTicks, float chargeDamageMultiplier, float minSpeedForChargeDamage, float minSpeedForDismount, int engageWindowTicks, class_1792.class_1793 settings) {
        super(material, settings.method_57348(SpearItem.createAttributeModifiers(material, attackDamageBonus, swingAnimationSeconds, maxReach)).method_7895(material.method_8025()));
        this.attackDamageBonus = attackDamageBonus;
        this.minReach = minReach;
        this.maxReach = maxReach;
        this.hitboxMargin = hitboxMargin;
        this.chargeDelayTicks = chargeDelayTicks;
        this.contactCooldownTicks = contactCooldownTicks;
        this.chargeDamageMultiplier = chargeDamageMultiplier;
        this.minSpeedForChargeDamage = minSpeedForChargeDamage;
        this.minSpeedForDismount = minSpeedForDismount;
        this.engageWindowTicks = engageWindowTicks;
    }

    public float minReach() {
        return this.minReach;
    }

    public float maxReach() {
        return this.maxReach;
    }

    public float hitboxMargin() {
        return this.hitboxMargin;
    }

    public float attackDamageBonus() {
        return this.attackDamageBonus;
    }

    public int chargeDelayTicks() {
        return this.chargeDelayTicks;
    }

    public int contactCooldownTicks() {
        return this.contactCooldownTicks;
    }

    public float chargeDamageMultiplier() {
        return this.chargeDamageMultiplier;
    }

    public float minSpeedForChargeDamage() {
        return this.minSpeedForChargeDamage;
    }

    public float minSpeedForDismount() {
        return this.minSpeedForDismount;
    }

    public int engageWindowTicks() {
        return this.engageWindowTicks;
    }

    public void method_7888(class_1799 stack, class_1937 world, class_1297 entity, int slot, boolean selected) {
        if (selected) {
            stack.method_27320(entity);
        } else {
            stack.method_27320(null);
        }
        super.method_7888(stack, world, entity, slot, selected);
    }

    public class_1271<class_1799> method_7836(class_1937 world, class_1657 user, class_1268 hand) {
        class_1799 stack = user.method_5998(hand);
        user.method_6019(hand);
        return class_1271.method_22428((Object)stack);
    }

    public void method_7852(class_1937 world, class_1309 user, class_1799 stack, int remainingUseTicks) {
        if (!world.method_8608()) {
            int elapsedUseTicks = this.method_7881(stack, user) - remainingUseTicks;
            SpearCombat.performChargeTick(user, SpearItem.spearFrom(stack), elapsedUseTicks);
        }
        super.method_7852(world, user, stack, remainingUseTicks);
    }

    public class_1839 method_7853(class_1799 stack) {
        return class_1839.field_8952;
    }

    public int method_7881(class_1799 stack, class_1309 user) {
        return 72000;
    }

    public boolean method_7885(class_2680 state, class_1937 world, class_2338 pos, class_1657 miner) {
        return !miner.method_7337();
    }

    public boolean method_7873(class_1799 stack, class_1309 target, class_1309 attacker) {
        return true;
    }

    public void method_59978(class_1799 stack, class_1309 target, class_1309 attacker) {
        stack.method_7970(1, attacker, class_1304.field_6173);
    }

    public boolean stab(class_1309 attacker, class_1268 hand) {
        boolean hit = SpearCombat.stab(attacker, hand, this.minReach, this.maxReach, this.hitboxMargin, true, false);
        if (hit) {
            attacker.method_5998(hand).method_7970(1, attacker, hand == class_1268.field_5808 ? class_1304.field_6173 : class_1304.field_6171);
            attacker.method_37908().method_43128(null, attacker.method_23317(), attacker.method_23318(), attacker.method_23321(), class_3417.field_15104, attacker.method_5634(), 0.7f, 1.15f);
        } else {
            attacker.method_37908().method_60511(null, attacker.method_23317(), attacker.method_23318(), attacker.method_23321(), class_3417.field_15001, attacker.method_5634(), 0.6f, 1.3f);
        }
        return hit;
    }

    private static SpearItem spearFrom(class_1799 stack) {
        return (SpearItem)stack.method_7909();
    }

    private static class_9285 createAttributeModifiers(class_1832 material, float attackDamageBonus, float swingAnimationSeconds, float maxReach) {
        return class_9285.method_57480().method_57487(class_5134.field_23721, new class_1322(ATTACK_DAMAGE_MODIFIER_ID, (double)(material.method_8028() + attackDamageBonus), class_1322.class_1323.field_6328), class_9274.field_49217).method_57487(class_5134.field_23723, new class_1322(ATTACK_SPEED_MODIFIER_ID, (double)(1.0f / swingAnimationSeconds - 4.0f), class_1322.class_1323.field_6328), class_9274.field_49217).method_57487(class_5134.field_47759, new class_1322(ATTACK_RANGE_MODIFIER_ID, (double)(maxReach - 3.0f), class_1322.class_1323.field_6328), class_9274.field_49217).method_57486();
    }
}

