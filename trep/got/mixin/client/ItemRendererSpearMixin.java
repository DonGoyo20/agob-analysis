/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1087
 *  net.minecraft.class_1799
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_763
 *  net.minecraft.class_811
 *  net.minecraft.class_918
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 */
package trep.got.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1087;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_763;
import net.minecraft.class_811;
import net.minecraft.class_918;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import trep.got.item.SpearItem;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_918.class})
public abstract class ItemRendererSpearMixin {
    @Shadow
    @Final
    private class_763 field_4732;

    @WrapOperation(method={"method_23179"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_918;method_23182(Lnet/minecraft/class_1087;Lnet/minecraft/class_1799;IILnet/minecraft/class_4587;Lnet/minecraft/class_4588;)V")})
    private void agob$renderGuiSpearModel(class_918 instance, class_1087 model, class_1799 stack, int light, int overlay, class_4587 matrices, class_4588 vertices, Operation<Void> original, @Local(argsOnly=true) class_811 renderMode) {
        if (stack.method_7909() instanceof SpearItem && (renderMode == class_811.field_4317 || renderMode == class_811.field_4318 || renderMode == class_811.field_4319)) {
            original.call(new Object[]{instance, this.field_4732.method_3308(stack), stack, light, overlay, matrices, vertices});
            return;
        }
        original.call(new Object[]{instance, model, stack, light, overlay, matrices, vertices});
    }
}

