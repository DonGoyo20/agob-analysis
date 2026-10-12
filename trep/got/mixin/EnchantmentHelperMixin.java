/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1889
 *  net.minecraft.class_1890
 *  net.minecraft.class_1893
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin;

import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_1799;
import net.minecraft.class_1889;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.item.BlacksmithHammerRules;

@Mixin(value={class_1890.class})
abstract class EnchantmentHelperMixin {
    EnchantmentHelperMixin() {
    }

    @Inject(method={"method_8229"}, at={@At(value="RETURN")}, cancellable=true)
    private static void got$restrictHammerEnchantments(int level, class_1799 stack, Stream<?> possibleEnchantments, CallbackInfoReturnable<List<class_1889>> cir) {
        if (!BlacksmithHammerRules.isHammer(stack)) {
            return;
        }
        cir.setReturnValue(((List)cir.getReturnValue()).stream().filter(entry -> entry.field_9093.method_40225(class_1893.field_9119)).toList());
    }
}

