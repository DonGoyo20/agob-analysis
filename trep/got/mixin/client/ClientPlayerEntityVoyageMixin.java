/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1294
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1294;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.client.voyage.VoyageClientState;
import trep.got.item.SpearItem;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_746.class})
public class ClientPlayerEntityVoyageMixin {
    @Inject(method={"method_5715"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$suppressSneakWhileVoyaging(CallbackInfoReturnable<Boolean> cir) {
        if (VoyageClientState.shouldSuppressSneak()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"method_48300"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$allowSpearChargeSprint(CallbackInfoReturnable<Boolean> cir) {
        class_746 self = (class_746)this;
        if (!self.method_6030().method_7960() && self.method_6030().method_7909() instanceof SpearItem && self.method_6014() > 0) {
            boolean canSprint;
            boolean bl = canSprint = !self.method_5765() && (float)self.method_7344().method_7586() > 6.0f || self.method_31549().field_7478;
            boolean walking = self.method_5869() ? self.field_3913.method_20622() : self.field_3913.field_3905 >= 0.8f;
            boolean allowed = !self.method_5624() && walking && canSprint && !self.method_6059(class_1294.field_5919) && (!self.method_5765() || self.method_5854().method_48155() && self.method_5854().method_5787()) && !self.method_6128();
            cir.setReturnValue((Object)allowed);
        }
    }
}

