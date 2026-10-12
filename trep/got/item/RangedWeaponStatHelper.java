/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_5455
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.item;

import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_5455;
import org.jetbrains.annotations.Nullable;
import trep.got.item.BowCombatHelper;
import trep.got.item.CrossbowCombatHelper;
import trep.got.item.RangedWeaponStats;
import trep.got.item.ScorpionCrossbowCombatHelper;

public final class RangedWeaponStatHelper {
    private RangedWeaponStatHelper() {
    }

    public static boolean hasRangedWeaponStats(class_1799 stack) {
        return RangedWeaponStatHelper.resolve(stack) != null;
    }

    @Nullable
    public static RangedWeaponStats resolve(class_1799 stack) {
        return RangedWeaponStatHelper.resolve(stack, null, null);
    }

    @Nullable
    public static RangedWeaponStats resolve(class_1799 stack, @Nullable class_1309 user) {
        return RangedWeaponStatHelper.resolve(stack, user, null);
    }

    @Nullable
    public static RangedWeaponStats resolve(class_1799 stack, @Nullable class_1309 user, @Nullable class_5455 registryManager) {
        if (stack == null || stack.method_7960()) {
            return null;
        }
        if (ScorpionCrossbowCombatHelper.isScorpionCrossbow(stack)) {
            return new RangedWeaponStats(ScorpionCrossbowCombatHelper.getAttackDamage(), ScorpionCrossbowCombatHelper.getReloadTicks());
        }
        if (CrossbowCombatHelper.isCrossbow(stack)) {
            return new RangedWeaponStats(CrossbowCombatHelper.getAttackDamage(stack, registryManager), CrossbowCombatHelper.getReloadTicks(stack, user));
        }
        if (BowCombatHelper.isBow(stack)) {
            return new RangedWeaponStats(BowCombatHelper.getAttackDamage(stack, registryManager), BowCombatHelper.getFullDrawTicks(stack));
        }
        return null;
    }
}

