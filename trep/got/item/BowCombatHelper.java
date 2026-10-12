/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1665
 *  net.minecraft.class_1753
 *  net.minecraft.class_1799
 *  net.minecraft.class_1890
 *  net.minecraft.class_1893
 *  net.minecraft.class_3532
 *  net.minecraft.class_5455
 *  net.minecraft.class_6880
 *  net.minecraft.class_7924
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.item;

import net.minecraft.class_1665;
import net.minecraft.class_1753;
import net.minecraft.class_1799;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_3532;
import net.minecraft.class_5455;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import org.jetbrains.annotations.Nullable;
import trep.got.item.LongbowItem;

public final class BowCombatHelper {
    private static final int VANILLA_FULL_DRAW_TICKS = 20;
    private static final int LONGBOW_FULL_DRAW_TICKS = 40;
    private static final float FULL_DRAW_PROJECTILE_SPEED = 3.0f;
    private static final double VANILLA_PROJECTILE_DAMAGE = 2.0;
    private static final double LONGBOW_DAMAGE_MULTIPLIER = 2.0;

    private BowCombatHelper() {
    }

    public static boolean isBow(class_1799 stack) {
        return stack.method_7909() instanceof class_1753;
    }

    public static boolean isLongbow(class_1799 stack) {
        return stack.method_7909() instanceof LongbowItem;
    }

    public static int getFullDrawTicks(class_1799 stack) {
        return BowCombatHelper.isLongbow(stack) ? 40 : 20;
    }

    public static int getAttackIntervalTicks(class_1799 stack, int baseIntervalTicks) {
        return BowCombatHelper.isLongbow(stack) ? baseIntervalTicks * 2 : baseIntervalTicks;
    }

    public static float getPullProgress(class_1799 stack, int useTicks) {
        float progress = (float)useTicks / (float)BowCombatHelper.getFullDrawTicks(stack);
        progress = (progress * progress + progress * 2.0f) / 3.0f;
        return Math.min(progress, 1.0f);
    }

    public static float getProjectileSpeed(class_1799 stack) {
        return 3.0f;
    }

    public static double getBaseProjectileDamage(class_1799 stack) {
        return BowCombatHelper.getBaseProjectileDamage(stack, null);
    }

    public static double getBaseProjectileDamage(class_1799 stack, @Nullable class_5455 registryManager) {
        double baseDamage = BowCombatHelper.isLongbow(stack) ? 4.0 : 2.0;
        return baseDamage + BowCombatHelper.getPowerBonusDamage(stack, registryManager);
    }

    public static int getAttackDamage(class_1799 stack) {
        return BowCombatHelper.getAttackDamage(stack, null);
    }

    public static int getAttackDamage(class_1799 stack, @Nullable class_5455 registryManager) {
        return BowCombatHelper.computeHitDamage(BowCombatHelper.getProjectileSpeed(stack), BowCombatHelper.getBaseProjectileDamage(stack, registryManager));
    }

    public static int computeHitDamage(float projectileSpeed, double baseProjectileDamage) {
        return class_3532.method_15384((double)class_3532.method_15350((double)((double)projectileSpeed * baseProjectileDamage), (double)0.0, (double)2.147483647E9));
    }

    public static void applyProjectileModifiers(class_1799 stack, class_1665 projectile) {
        projectile.method_7438(BowCombatHelper.getBaseProjectileDamage(stack));
    }

    public static double getPowerBonusDamage(class_1799 stack, @Nullable class_5455 registryManager) {
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

