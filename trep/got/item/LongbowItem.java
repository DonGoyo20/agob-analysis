/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1665
 *  net.minecraft.class_1676
 *  net.minecraft.class_1753
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_3218
 *  net.minecraft.class_3417
 *  net.minecraft.class_3419
 *  net.minecraft.class_3468
 */
package trep.got.item;

import java.util.List;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1665;
import net.minecraft.class_1676;
import net.minecraft.class_1753;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_3218;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3468;
import trep.got.item.BowCombatHelper;

public final class LongbowItem
extends class_1753 {
    public LongbowItem(class_1792.class_1793 settings) {
        super(settings);
    }

    public void method_7840(class_1799 stack, class_1937 world, class_1309 user, int remainingUseTicks) {
        if (!(user instanceof class_1657)) {
            return;
        }
        class_1657 player = (class_1657)user;
        class_1799 projectileStack = player.method_18808(stack);
        if (projectileStack.method_7960()) {
            return;
        }
        int useTicks = this.method_7881(stack, user) - remainingUseTicks;
        float pullProgress = BowCombatHelper.getPullProgress(stack, useTicks);
        if (pullProgress < 0.1f) {
            return;
        }
        List projectiles = LongbowItem.method_57390((class_1799)stack, (class_1799)projectileStack, (class_1309)player);
        if (world instanceof class_3218) {
            class_3218 serverWorld = (class_3218)world;
            if (!projectiles.isEmpty()) {
                this.method_57393(serverWorld, (class_1309)player, player.method_6058(), stack, projectiles, pullProgress * 3.0f, 1.0f, pullProgress == 1.0f, null);
            }
        }
        world.method_43128(null, player.method_23317(), player.method_23318(), player.method_23321(), class_3417.field_14600, class_3419.field_15248, 1.0f, 1.0f / (world.method_8409().method_43057() * 0.4f + 1.2f) + pullProgress * 0.5f);
        player.method_7259(class_3468.field_15372.method_14956((Object)this));
    }

    protected class_1676 method_57344(class_1937 world, class_1309 shooter, class_1799 weaponStack, class_1799 projectileStack, boolean critical) {
        class_1676 projectile = super.method_57344(world, shooter, weaponStack, projectileStack, critical);
        if (projectile instanceof class_1665) {
            class_1665 persistentProjectile = (class_1665)projectile;
            BowCombatHelper.applyProjectileModifiers(weaponStack, persistentProjectile);
        }
        return projectile;
    }
}

