/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1322
 *  net.minecraft.class_1322$class_1323
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1799
 *  net.minecraft.class_1832
 *  net.minecraft.class_1890
 *  net.minecraft.class_1937
 *  net.minecraft.class_3218
 *  net.minecraft.class_5134
 *  net.minecraft.class_9274
 *  net.minecraft.class_9285
 *  net.minecraft.class_9334
 *  net.minecraft.class_9362
 */
package trep.got.item;

import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1322;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1832;
import net.minecraft.class_1890;
import net.minecraft.class_1937;
import net.minecraft.class_3218;
import net.minecraft.class_5134;
import net.minecraft.class_9274;
import net.minecraft.class_9285;
import net.minecraft.class_9334;
import net.minecraft.class_9362;

public class VariantMaceItem
extends class_9362 {
    private final class_1832 material;
    private final float smashDamageMultiplier;

    public VariantMaceItem(class_1832 material, float attackDamageBonus, float attackSpeed, float smashDamageMultiplier, class_1792.class_1793 settings) {
        super(settings.method_7889(1).method_7895(material.method_8025()).method_57348(VariantMaceItem.createAttributeModifiers(material, attackDamageBonus, attackSpeed)).method_57349(class_9334.field_50077, (Object)class_9362.method_58412()));
        this.material = material;
        this.smashDamageMultiplier = smashDamageMultiplier;
    }

    public int method_7837() {
        return this.material.method_8026();
    }

    public boolean method_7878(class_1799 stack, class_1799 ingredient) {
        return this.material.method_8023().method_8093(ingredient);
    }

    public float method_58403(class_1297 target, float baseAttackDamage, class_1282 damageSource) {
        class_1309 attacker;
        class_1297 class_12972 = damageSource.method_5526();
        if (!(class_12972 instanceof class_1309) || !class_9362.method_58659((class_1309)(attacker = (class_1309)class_12972))) {
            return 0.0f;
        }
        float fallDistance = attacker.field_6017;
        float bonusDamage = fallDistance <= 3.0f ? 4.0f * fallDistance : (fallDistance <= 8.0f ? 12.0f + 2.0f * (fallDistance - 3.0f) : 22.0f + (fallDistance - 8.0f));
        class_1937 class_19372 = attacker.method_37908();
        if (class_19372 instanceof class_3218) {
            class_3218 serverWorld = (class_3218)class_19372;
            bonusDamage += class_1890.method_60160((class_3218)serverWorld, (class_1799)attacker.method_59958(), (class_1297)target, (class_1282)damageSource, (float)0.0f) * fallDistance;
        }
        return bonusDamage * this.smashDamageMultiplier;
    }

    public static class_9285 createAttributeModifiers(class_1832 material, float attackDamageBonus, float attackSpeed) {
        return class_9285.method_57480().method_57487(class_5134.field_23721, new class_1322(class_1792.field_8006, (double)(material.method_8028() + attackDamageBonus), class_1322.class_1323.field_6328), class_9274.field_49217).method_57487(class_5134.field_23723, new class_1322(class_1792.field_8001, (double)attackSpeed, class_1322.class_1323.field_6328), class_9274.field_49217).method_57486();
    }
}

