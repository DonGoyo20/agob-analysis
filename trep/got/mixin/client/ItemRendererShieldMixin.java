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
 *  net.minecraft.class_4597
 *  net.minecraft.class_918
 *  org.spongepowered.asm.mixin.Mixin
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
import net.minecraft.class_4597;
import net.minecraft.class_918;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import trep.got.client.render.item.GotShieldBannerRenderer;
import trep.got.registry.GotItems;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_918.class})
public abstract class ItemRendererShieldMixin {
    @WrapOperation(method={"method_23179"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_918;method_23182(Lnet/minecraft/class_1087;Lnet/minecraft/class_1799;IILnet/minecraft/class_4587;Lnet/minecraft/class_4588;)V")})
    private void agob$renderShieldBannerOverlay(class_918 instance, class_1087 model, class_1799 stack, int light, int overlay, class_4587 matrices, class_4588 vertices, Operation<Void> original, @Local(argsOnly=true) class_4597 consumers) {
        original.call(new Object[]{instance, model, stack, light, overlay, matrices, vertices});
        if (GotItems.isVariantShield(stack)) {
            GotShieldBannerRenderer.render(stack, matrices, consumers, light, overlay);
        }
    }
}

