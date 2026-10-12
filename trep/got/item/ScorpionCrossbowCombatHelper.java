/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package trep.got.item;

import net.minecraft.class_1799;
import trep.got.item.BowCombatHelper;
import trep.got.item.ScorpionCrossbowItem;

public final class ScorpionCrossbowCombatHelper {
    private static final double PROJECTILE_DAMAGE = 10.0;
    private static final float PROJECTILE_SPEED = 3.4f;
    private static final int RELOAD_TICKS = 20;

    private ScorpionCrossbowCombatHelper() {
    }

    public static boolean isScorpionCrossbow(class_1799 stack) {
        return stack.method_7909() instanceof ScorpionCrossbowItem;
    }

    public static double getBaseProjectileDamage() {
        return 10.0;
    }

    public static float getProjectileSpeed() {
        return 3.4f;
    }

    public static int getAttackDamage() {
        return BowCombatHelper.computeHitDamage(3.4f, 10.0);
    }

    public static int getReloadTicks() {
        return 20;
    }
}

