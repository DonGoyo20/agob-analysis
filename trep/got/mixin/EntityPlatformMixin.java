/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1297
 *  net.minecraft.class_1313
 *  net.minecraft.class_1496
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1313;
import net.minecraft.class_1496;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_265;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.entity.CartEntity;
import trep.got.entity.TwoHorseCartEntity;
import trep.got.mixin.EntityPlatformAccess;
import trep.got.vehicle.platform.PlatformCollisionLookup;
import trep.got.vehicle.platform.PlatformEntity;
import trep.got.vehicle.platform.PlatformMotionController;
import trep.got.vehicle.platform.PlatformStateHolder;
import trep.got.vehicle.platform.PlatformSupportResolver;
import trep.got.vehicle.platform.PlayerPlatformState;

@Mixin(value={class_1297.class})
public abstract class EntityPlatformMixin
implements PlatformStateHolder {
    @Unique
    private final PlayerPlatformState agob$platformState = new PlayerPlatformState();

    @Override
    public PlayerPlatformState agob$getPlatformState() {
        return this.agob$platformState;
    }

    @Inject(method={"method_5773"}, at={@At(value="TAIL")})
    private void agob$tickPlatformMotion(CallbackInfo ci) {
        class_1297 self = (class_1297)this;
        if (!self.method_37908().method_8608() && this.agob$platformState.isAttached()) {
            PlatformMotionController.tickEntity(self);
        }
    }

    @Redirect(method={"method_5784"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_1297;method_17835(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;"))
    private class_243 agob$injectPlatformCollisions(class_1297 entity, class_243 movement) {
        class_238 collisionBox = entity.method_5829().method_18804(movement);
        List<class_265> platformCollisions = PlatformCollisionLookup.collect(entity, collisionBox.method_1014(0.25));
        if (!this.agob$platformState.isAttached() && platformCollisions.isEmpty()) {
            return ((EntityPlatformAccess)entity).agob$invokeAdjustMovementForCollisions(movement);
        }
        ArrayList<class_265> collisions = new ArrayList<class_265>();
        for (class_265 shape : entity.method_37908().method_20812(entity, collisionBox)) {
            collisions.add(shape);
        }
        for (class_1297 other : entity.method_37908().method_8333(entity, collisionBox.method_1014(1.0E-7), candidate -> !(candidate instanceof PlatformEntity) && (!(entity instanceof class_1657) || !CartEntity.isAttachedCartHorse(candidate) && !TwoHorseCartEntity.isAttachedCartHorse(candidate)) && candidate.method_30949(entity))) {
            collisions.add(class_259.method_1078((class_238)other.method_5829()));
        }
        collisions.addAll(platformCollisions);
        return class_1297.method_20736((class_1297)entity, (class_243)movement, (class_238)entity.method_5829(), (class_1937)entity.method_37908(), collisions);
    }

    @Inject(method={"method_5784"}, at={@At(value="TAIL")})
    private void agob$updatePlatformSupport(class_1313 movementType, class_243 movement, CallbackInfo ci) {
        class_1297 entity = (class_1297)this;
        if (entity.method_7325() || entity.method_5765()) {
            this.agob$platformState.clear();
            return;
        }
        PlatformSupportResolver.update(entity);
    }

    @Inject(method={"method_5688"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$detachCartOnLeadUse(class_1657 player, class_1268 hand, CallbackInfoReturnable<class_1269> cir) {
        class_1297 entity = (class_1297)this;
        if (!(entity instanceof class_1496)) {
            return;
        }
        class_1496 horse = (class_1496)entity;
        if (!player.method_5998(hand).method_31574(class_1802.field_8719)) {
            return;
        }
        if (entity.method_37908().field_9236) {
            if (CartEntity.isAttachedCartHorse(entity) || TwoHorseCartEntity.isAttachedCartHorse(entity)) {
                cir.setReturnValue((Object)class_1269.field_5812);
            }
            return;
        }
        if (CartEntity.detachCartForHorse(horse) || TwoHorseCartEntity.detachCartForHorse(horse)) {
            cir.setReturnValue((Object)class_1269.field_21466);
        }
    }
}

