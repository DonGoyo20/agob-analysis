/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_1764
 *  net.minecraft.class_1799
 *  net.minecraft.class_1890
 *  net.minecraft.class_1893
 *  net.minecraft.class_5455
 *  net.minecraft.class_6880
 *  net.minecraft.class_7924
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.item;

import net.minecraft.class_1309;
import net.minecraft.class_1764;
import net.minecraft.class_1799;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_5455;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import org.jetbrains.annotations.Nullable;
import trep.got.item.BowCombatHelper;

public final class CrossbowCombatHelper {
    private static final float DEFAULT_PROJECTILE_SPEED = 3.15f;

    private CrossbowCombatHelper() {
    }

    public static boolean isCrossbow(class_1799 stack) {
        return stack.method_7909() instanceof class_1764;
    }

    public static float getProjectileSpeed(class_1799 stack) {
        return 3.15f;
    }

    public static double getBaseProjectileDamage(class_1799 stack) {
        return CrossbowCombatHelper.getBaseProjectileDamage(stack, null);
    }

    public static double getBaseProjectileDamage(class_1799 stack, @Nullable class_5455 registryManager) {
        return 2.0 + CrossbowCombatHelper.getPowerBonusDamage(stack, registryManager);
    }

    public static int getAttackDamage(class_1799 stack) {
        return CrossbowCombatHelper.getAttackDamage(stack, null);
    }

    public static int getAttackDamage(class_1799 stack, @Nullable class_5455 registryManager) {
        return BowCombatHelper.computeHitDamage(CrossbowCombatHelper.getProjectileSpeed(stack), CrossbowCombatHelper.getBaseProjectileDamage(stack, registryManager));
    }

    public static int getReloadTicks(class_1799 stack) {
        return CrossbowCombatHelper.getReloadTicks(stack, null);
    }

    public static int getReloadTicks(class_1799 stack, @Nullable class_1309 user) {
        return class_1764.method_7775((class_1799)stack, (class_1309)user);
    }

    private static double getPowerBonusDamage(class_1799 stack, @Nullable class_5455 registryManager) {
        if (registryManager == null || stack == null || stack.method_7960()) {
            return 0.0;
        }
        int powerLevel = class_1890.method_8225((class_6880)registryManager.method_46762(class_7924.field_41265).method_46747(class_1893.field_9103), (class_1799)stack);
        if (powerLevel <= 0) {
            return 0.0;
        }
        return 0.5 * (double)powerLevel;
    }
}

