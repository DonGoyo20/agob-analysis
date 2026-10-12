/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1297
 *  net.minecraft.class_638
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.vehicle.platform.PlatformSupportResolver;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_638.class})
public abstract class ClientWorldPlatformMixin {
    @Inject(method={"method_18646"}, at={@At(value="TAIL")})
    private void agob$refreshClientPlatformSupport(class_1297 entity, CallbackInfo ci) {
        if (!entity.method_7325() && !entity.method_5765()) {
            PlatformSupportResolver.update(entity);
        }
    }
}

