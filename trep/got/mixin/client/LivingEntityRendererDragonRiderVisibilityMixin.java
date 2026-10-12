/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_922
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.entity.DragonEntity;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_922.class})
public abstract class LivingEntityRendererDragonRiderVisibilityMixin {
    @Inject(method={"method_4054"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$hideSeparateDragonRider(class_1309 entity, float yaw, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
        DragonEntity dragon;
        class_1297 class_12972 = entity.method_5854();
        if (class_12972 instanceof DragonEntity && (dragon = (DragonEntity)class_12972).method_5626((class_1297)entity)) {
            ci.cancel();
        }
    }
}

