/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_1268
 *  net.minecraft.class_310
 *  net.minecraft.class_746
 *  net.minecraft.class_8710
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_1268;
import net.minecraft.class_310;
import net.minecraft.class_746;
import net.minecraft.class_8710;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.compat.BetterCombatCompat;
import trep.got.item.SpearItem;
import trep.got.network.RequestSpearStabPayload;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_310.class})
public abstract class MinecraftClientSpearMixin {
    @Shadow
    @Nullable
    public class_746 field_1724;

    @Inject(method={"method_1536"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_746;method_5998(Lnet/minecraft/class_1268;)Lnet/minecraft/class_1799;", shift=At.Shift.AFTER)}, cancellable=true)
    private void agob$handleSpearStab(CallbackInfoReturnable<Boolean> cir) {
        if (this.field_1724 == null || BetterCombatCompat.isLoaded() || !(this.field_1724.method_6047().method_7909() instanceof SpearItem)) {
            return;
        }
        if (this.field_1724.method_7261(0.0f) < 1.0f) {
            cir.setReturnValue((Object)false);
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestSpearStabPayload());
        this.field_1724.method_6104(class_1268.field_5808);
        this.field_1724.method_7350();
        cir.setReturnValue((Object)true);
    }
}

