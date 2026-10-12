/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_4970
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin;

import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.registry.GotLockpicking;

@Mixin(value={class_4970.class})
public abstract class AbstractBlockLockpickingOwnerMixin {
    @Inject(method={"method_9536"}, at={@At(value="TAIL")})
    private void got$clearLockpickableOwner(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved, CallbackInfo ci) {
        GotLockpicking.clearPlacedLockpickableOwner(world, pos, state, newState);
    }
}

