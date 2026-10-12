/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_2331
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_3965
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin;

import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2331;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3965;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.world.dimension.GotDimensionKeys;

@Mixin(value={class_2331.class})
abstract class EnchantingTableBlockMixin {
    EnchantingTableBlockMixin() {
    }

    @Inject(method={"method_55766"}, at={@At(value="HEAD")}, cancellable=true)
    private void got$disableEnchantingTableInIceAndFire(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit, CallbackInfoReturnable<class_1269> cir) {
        if (!world.method_27983().equals(GotDimensionKeys.ICEANDFIRE)) {
            return;
        }
        cir.setReturnValue((Object)(world.field_9236 ? class_1269.field_5812 : class_1269.field_21466));
    }
}

