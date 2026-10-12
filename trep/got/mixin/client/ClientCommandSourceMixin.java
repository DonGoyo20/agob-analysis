/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1297
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_310
 *  net.minecraft.class_3966
 *  net.minecraft.class_637
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin.client;

import java.util.Collection;
import java.util.Collections;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_239;
import net.minecraft.class_310;
import net.minecraft.class_3966;
import net.minecraft.class_637;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.entity.AbstractKingdomNpcEntity;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_637.class})
abstract class ClientCommandSourceMixin {
    @Shadow
    @Final
    private class_310 field_3725;

    ClientCommandSourceMixin() {
    }

    @Inject(method={"method_9269"}, at={@At(value="HEAD")}, cancellable=true)
    private void got$hideKingdomNpcEntitySuggestions(CallbackInfoReturnable<Collection<String>> cir) {
        class_239 crosshairTarget = this.field_3725.field_1765;
        if (crosshairTarget == null || crosshairTarget.method_17783() != class_239.class_240.field_1331) {
            return;
        }
        class_1297 entity = ((class_3966)crosshairTarget).method_17782();
        if (entity instanceof AbstractKingdomNpcEntity) {
            cir.setReturnValue(Collections.emptyList());
        }
    }
}

