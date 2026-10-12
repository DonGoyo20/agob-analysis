/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1007
 *  net.minecraft.class_3883
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_591
 *  net.minecraft.class_742
 *  net.minecraft.class_922
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1007;
import net.minecraft.class_3883;
import net.minecraft.class_5617;
import net.minecraft.class_591;
import net.minecraft.class_742;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.client.character.PlayerSkinBuilderFeatureRenderer;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_1007.class})
public abstract class PlayerEntityRendererSkinBuilderFeatureMixin
extends class_922<class_742, class_591<class_742>> {
    protected PlayerEntityRendererSkinBuilderFeatureMixin(class_5617.class_5618 ctx, class_591<class_742> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method={"<init>"}, at={@At(value="TAIL")})
    private void agob$addSkinBuilderFeature(class_5617.class_5618 ctx, boolean slim, CallbackInfo ci) {
        this.method_4046(new PlayerSkinBuilderFeatureRenderer((class_3883<class_742, class_591<class_742>>)this));
    }
}

