/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1746
 *  net.minecraft.class_1799
 *  net.minecraft.class_1872
 *  net.minecraft.class_1937
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_9307
 *  net.minecraft.class_9334
 *  net.minecraft.class_9694
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin;

import net.minecraft.class_1746;
import net.minecraft.class_1799;
import net.minecraft.class_1872;
import net.minecraft.class_1937;
import net.minecraft.class_7225;
import net.minecraft.class_9307;
import net.minecraft.class_9334;
import net.minecraft.class_9694;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.registry.GotItems;

@Mixin(value={class_1872.class})
public abstract class ShieldDecorationRecipeMixin {
    @Inject(method={"method_17732"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$allowVariantShields(class_9694 input, class_1937 world, CallbackInfoReturnable<Boolean> cir) {
        if (!ShieldDecorationRecipeMixin.containsVariantShield(input)) {
            return;
        }
        class_1799 shield = class_1799.field_8037;
        class_1799 banner = class_1799.field_8037;
        for (int slot = 0; slot < input.method_59983(); ++slot) {
            class_1799 stack = input.method_59984(slot);
            if (stack.method_7960()) continue;
            if (stack.method_7909() instanceof class_1746) {
                if (!banner.method_7960()) {
                    cir.setReturnValue((Object)false);
                    return;
                }
                banner = stack;
                continue;
            }
            if (!GotItems.isVariantShield(stack)) {
                cir.setReturnValue((Object)false);
                return;
            }
            if (!shield.method_7960()) {
                cir.setReturnValue((Object)false);
                return;
            }
            class_9307 patterns = (class_9307)stack.method_57825(class_9334.field_49619, (Object)class_9307.field_49404);
            if (!patterns.comp_2428().isEmpty()) {
                cir.setReturnValue((Object)false);
                return;
            }
            shield = stack;
        }
        cir.setReturnValue((Object)(!shield.method_7960() && !banner.method_7960() ? 1 : 0));
    }

    @Inject(method={"method_17731"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$craftVariantShield(class_9694 input, class_7225.class_7874 registries, CallbackInfoReturnable<class_1799> cir) {
        if (!ShieldDecorationRecipeMixin.containsVariantShield(input)) {
            return;
        }
        class_1799 banner = class_1799.field_8037;
        class_1799 shield = class_1799.field_8037;
        for (int slot = 0; slot < input.method_59983(); ++slot) {
            class_1799 stack = input.method_59984(slot);
            if (stack.method_7960()) continue;
            if (stack.method_7909() instanceof class_1746) {
                banner = stack;
                continue;
            }
            if (!GotItems.isVariantShield(stack)) continue;
            shield = stack.method_7972();
        }
        if (shield.method_7960() || banner.method_7960()) {
            cir.setReturnValue((Object)class_1799.field_8037);
            return;
        }
        shield.method_57379(class_9334.field_49619, (Object)((class_9307)banner.method_57824(class_9334.field_49619)));
        shield.method_57379(class_9334.field_49620, (Object)((class_1746)banner.method_7909()).method_7706());
        cir.setReturnValue((Object)shield);
    }

    private static boolean containsVariantShield(class_9694 input) {
        for (int slot = 0; slot < input.method_59983(); ++slot) {
            if (!GotItems.isVariantShield(input.method_59984(slot))) continue;
            return true;
        }
        return false;
    }
}

