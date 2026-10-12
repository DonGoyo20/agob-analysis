/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_9323
 *  net.minecraft.class_9326
 *  net.minecraft.class_9331
 *  net.minecraft.class_9335
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin;

import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_9323;
import net.minecraft.class_9326;
import net.minecraft.class_9331;
import net.minecraft.class_9335;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.item.ItemStackQualityHelper;

@Mixin(value={class_1799.class})
public abstract class ItemStackQualityMixin {
    @Shadow
    @Final
    class_9335 field_49270;

    @Inject(method={"<init>(Lnet/minecraft/class_1935;ILnet/minecraft/class_9335;)V"}, at={@At(value="TAIL")})
    private void got$applyDefaultQuality(class_1935 item, int count, class_9335 components, CallbackInfo ci) {
        ItemStackQualityHelper.synchronize((class_1799)this, this.field_49270);
    }

    @Inject(method={"method_57379"}, at={@At(value="RETURN")})
    private <T> void got$syncQualityAfterSet(class_9331<? extends T> type, T value, CallbackInfoReturnable<T> cir) {
        ItemStackQualityHelper.synchronize((class_1799)this, this.field_49270);
    }

    @Inject(method={"method_57381"}, at={@At(value="RETURN")})
    private <T> void got$syncQualityAfterRemove(class_9331<? extends T> type, CallbackInfoReturnable<T> cir) {
        ItemStackQualityHelper.synchronize((class_1799)this, this.field_49270);
    }

    @Inject(method={"method_59692"}, at={@At(value="TAIL")})
    private void got$syncQualityAfterChanges(class_9326 changes, CallbackInfo ci) {
        ItemStackQualityHelper.synchronize((class_1799)this, this.field_49270);
    }

    @Inject(method={"method_57366"}, at={@At(value="TAIL")})
    private void got$syncQualityAfterUnsafeChanges(class_9326 changes, CallbackInfo ci) {
        ItemStackQualityHelper.synchronize((class_1799)this, this.field_49270);
    }

    @Inject(method={"method_57365"}, at={@At(value="TAIL")})
    private void got$syncQualityAfterApplyComponents(class_9323 components, CallbackInfo ci) {
        ItemStackQualityHelper.synchronize((class_1799)this, this.field_49270);
    }
}

