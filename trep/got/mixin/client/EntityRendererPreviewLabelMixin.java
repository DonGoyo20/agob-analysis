/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1297
 *  net.minecraft.class_897
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.client.character.CharacterPreviewPlayerEntity;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_897.class})
public abstract class EntityRendererPreviewLabelMixin {
    @Inject(method={"method_3921"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$hidePreviewPlayerLabel(class_1297 entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof CharacterPreviewPlayerEntity) {
            cir.setReturnValue((Object)false);
        }
    }
}

