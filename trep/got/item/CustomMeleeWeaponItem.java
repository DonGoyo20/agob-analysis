/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1322
 *  net.minecraft.class_1322$class_1323
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1799
 *  net.minecraft.class_1829
 *  net.minecraft.class_1832
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_2960
 *  net.minecraft.class_5134
 *  net.minecraft.class_9274
 *  net.minecraft.class_9285
 */
package trep.got.item;

import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1322;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1829;
import net.minecraft.class_1832;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_5134;
import net.minecraft.class_9274;
import net.minecraft.class_9285;

public class CustomMeleeWeaponItem
extends class_1829 {
    private final float attackDamageBonus;
    private final float attackSpeed;
    private final float range;

    public CustomMeleeWeaponItem(class_1832 material, float attackDamageBonus, float attackSpeed, float range, class_1792.class_1793 settings) {
        super(material, settings.method_7889(1).method_57348(CustomMeleeWeaponItem.createAttributeModifiers(material, attackDamageBonus, attackSpeed, range)));
        this.attackDamageBonus = attackDamageBonus;
        this.attackSpeed = attackSpeed;
        this.range = range;
    }

    public float attackDamageBonus() {
        return this.attackDamageBonus;
    }

    public float attackSpeed() {
        return this.attackSpeed;
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

    public static class_9285 createAttributeModifiers(class_1832 material, float attackDamageBonus, float attackSpeed, float range) {
        return class_9285.method_57480().method_57487(class_5134.field_23721, new class_1322(class_1792.field_8006, (double)(material.method_8028() + attackDamageBonus), class_1322.class_1323.field_6328), class_9274.field_49217).method_57487(class_5134.field_23723, new class_1322(class_1792.field_8001, (double)attackSpeed, class_1322.class_1323.field_6328), class_9274.field_49217).method_57487(class_5134.field_47759, new class_1322(class_2960.method_60655((String)"got", (String)"weapon_range"), (double)(range - 3.0f), class_1322.class_1323.field_6328), class_9274.field_49217).method_57486();
    }
}

