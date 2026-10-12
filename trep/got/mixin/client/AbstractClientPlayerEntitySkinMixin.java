/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 *  net.minecraft.class_742
 *  net.minecraft.class_8685
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_742;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.character.PlayerSkinBuilderSkinResolver;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_742.class})
public abstract class AbstractClientPlayerEntitySkinMixin {
    @Inject(method={"method_52814"}, at={@At(value="RETURN")}, cancellable=true)
    private void agob$useBuiltPlayerSkin(CallbackInfoReturnable<class_8685> cir) {
        if (PlayerSkinBuilderSkinResolver.shouldSuppressOverride()) {
            return;
        }
        class_310 client = class_310.method_1551();
        if (client == null || client.field_1724 == null || this != client.field_1724) {
            return;
        }
        cir.setReturnValue((Object)PlayerSkinBuilderSkinResolver.resolve(client, (class_8685)cir.getReturnValue(), CharacterSelectionClientState.getSkinBuilderProfile()));
    }
}

