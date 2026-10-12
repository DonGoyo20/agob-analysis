/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1087
 *  net.minecraft.class_1799
 *  net.minecraft.class_1921
 *  net.minecraft.class_310
 *  net.minecraft.class_4583
 *  net.minecraft.class_4587
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4722
 *  net.minecraft.class_811
 *  net.minecraft.class_918
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1087;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_310;
import net.minecraft.class_4583;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4722;
import net.minecraft.class_811;
import net.minecraft.class_918;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.client.render.item.RuneGlintTextures;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_918.class})
abstract class ItemRendererRuneGlintMixin {
    ItemRendererRuneGlintMixin() {
    }

    @Inject(method={"method_23179"}, at={@At(value="HEAD")})
    private void got$pushRuneItemStack(class_1799 stack, class_811 renderMode, boolean leftHanded, class_4587 matrices, class_4597 vertexConsumers, int light, int overlay, class_1087 model, CallbackInfo ci) {
        RuneGlintTextures.pushCurrentStack(stack);
    }

    @Inject(method={"method_23179"}, at={@At(value="RETURN")})
    private void got$clearRuneItemStack(class_1799 stack, class_811 renderMode, boolean leftHanded, class_4587 matrices, class_4597 vertexConsumers, int light, int overlay, class_1087 model, CallbackInfo ci) {
        RuneGlintTextures.clearCurrentStack();
    }

    @Inject(method={"method_30114"}, at={@At(value="HEAD")}, cancellable=true)
    private static void got$useRuneDynamicDisplayGlint(class_4597 provider, class_1921 layer, class_4587.class_4665 entry, CallbackInfoReturnable<class_4588> cir) {
        if (!RuneGlintTextures.hasCurrentRuneGlint()) {
            return;
        }
        class_4583 base = new class_4583(provider.getBuffer(layer), entry, 0.0078125f);
        cir.setReturnValue((Object)RuneGlintTextures.unionWithRuneGlint(provider, (class_4588)base, RuneGlintTextures.RuneGlintLayerType.GUI_DYNAMIC));
    }

    @Inject(method={"method_23181"}, at={@At(value="HEAD")}, cancellable=true)
    private static void got$useRuneItemGlint(class_4597 provider, class_1921 layer, boolean solid, boolean glint, CallbackInfoReturnable<class_4588> cir) {
        if (!glint || !RuneGlintTextures.hasCurrentRuneGlint()) {
            return;
        }
        RuneGlintTextures.RuneGlintLayerType layerType = class_310.method_29611() && layer == class_4722.method_29382() ? RuneGlintTextures.RuneGlintLayerType.TRANSLUCENT : (solid ? RuneGlintTextures.RuneGlintLayerType.ITEM : RuneGlintTextures.RuneGlintLayerType.ENTITY);
        cir.setReturnValue((Object)RuneGlintTextures.unionWithRuneGlint(provider, provider.getBuffer(layer), layerType));
    }

    @Inject(method={"method_29711"}, at={@At(value="HEAD")}, cancellable=true)
    private static void got$useRuneDirectGlint(class_4597 provider, class_1921 layer, boolean solid, boolean glint, CallbackInfoReturnable<class_4588> cir) {
        if (!glint || !RuneGlintTextures.hasCurrentRuneGlint()) {
            return;
        }
        RuneGlintTextures.RuneGlintLayerType layerType = solid ? RuneGlintTextures.RuneGlintLayerType.ITEM : RuneGlintTextures.RuneGlintLayerType.DIRECT_ENTITY;
        cir.setReturnValue((Object)RuneGlintTextures.unionWithRuneGlint(provider, provider.getBuffer(layer), layerType));
    }
}

