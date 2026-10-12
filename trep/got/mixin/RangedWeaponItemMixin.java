/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_1811
 *  net.minecraft.class_3218
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 */
package trep.got.mixin;

import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1811;
import net.minecraft.class_3218;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import trep.got.item.ItemStackQualityHelper;

@Mixin(value={class_1811.class})
public abstract class RangedWeaponItemMixin {
    @ModifyVariable(method={"method_57393"}, at=@At(value="HEAD"), argsOnly=true, ordinal=0)
    private float got$scaleProjectileSpeedForQuality(float speed, class_3218 world, class_1309 shooter, class_1268 hand, class_1799 stack, List<class_1799> projectiles, float originalSpeed, float divergence, boolean critical, class_1309 target) {
        return speed * ItemStackQualityHelper.getRangedSpeedMultiplier(stack);
    }
}

