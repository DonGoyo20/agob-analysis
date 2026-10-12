/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_572
 *  net.minecraft.class_970
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_572;
import net.minecraft.class_970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.client.render.item.RuneGlintTextures;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_970.class})
abstract class ArmorFeatureRendererRuneGlintMixin<T extends class_1309, M extends class_572<T>, A extends class_572<T>> {
    ArmorFeatureRendererRuneGlintMixin() {
    }

    @Inject(method={"method_4169"}, at={@At(value="HEAD")})
    private void got$pushRuneArmorStack(class_4587 matrices, class_4597 vertexConsumers, T entity, class_1304 slot, int light, A model, CallbackInfo ci) {
        class_1799 stack = entity.method_6118(slot);
        RuneGlintTextures.pushCurrentStack(stack);
    }

    @Inject(method={"method_4169"}, at={@At(value="RETURN")})
    private void got$clearRuneArmorStack(class_4587 matrices, class_4597 vertexConsumers, T entity, class_1304 slot, int light, A model, CallbackInfo ci) {
        RuneGlintTextures.clearCurrentStack();
    }
}

