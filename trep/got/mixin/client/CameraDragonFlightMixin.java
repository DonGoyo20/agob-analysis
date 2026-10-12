/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1922
 *  net.minecraft.class_243
 *  net.minecraft.class_4184
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector3f
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1922;
import net.minecraft.class_243;
import net.minecraft.class_4184;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.entity.DragonEntity;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_4184.class})
public abstract class CameraDragonFlightMixin {
    private static final float DRAGON_MOUNT_CAMERA_Y_OFFSET = 1.0f;
    private static final float DRAGON_MOUNT_THIRD_PERSON_DISTANCE_MULTIPLIER = 1.25f;
    @Shadow
    @Final
    private Quaternionf field_21518;
    @Shadow
    @Final
    private Vector3f field_18714;
    @Shadow
    @Final
    private Vector3f field_18715;
    @Shadow
    @Final
    private Vector3f field_18716;
    @Shadow
    private class_1297 field_18711;
    @Shadow
    private class_243 field_18712;

    @Shadow
    protected abstract void method_19322(class_243 var1);

    @ModifyArg(method={"method_19321"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_4184;method_19318(F)F"), index=0)
    private float agob$extendDragonMountThirdPersonDistance(float distance) {
        return this.agob$isFocusedDragonRider() ? distance * 1.25f : distance;
    }

    @Inject(method={"method_19321"}, at={@At(value="TAIL")})
    private void agob$applyDragonMountCamera(class_1922 area, class_1297 focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        if (!(focusedEntity instanceof class_1309)) {
            return;
        }
        class_1309 livingEntity = (class_1309)focusedEntity;
        class_1297 class_12972 = livingEntity.method_5854();
        if (!(class_12972 instanceof DragonEntity)) {
            return;
        }
        DragonEntity dragon = (DragonEntity)class_12972;
        this.method_19322(this.field_18712.method_1031(0.0, 1.0, 0.0));
        if (dragon.isFlightActive()) {
            float rollRadians = (float)Math.toRadians(dragon.getVisualFlightRoll(tickDelta));
            if (Math.abs(rollRadians) < 1.0E-4f) {
                return;
            }
            Quaternionf rollRotation = new Quaternionf().rotateLocalZ(rollRadians);
            this.field_21518.mul((Quaternionfc)rollRotation);
            this.field_18714.rotate((Quaternionfc)rollRotation);
            this.field_18715.rotate((Quaternionfc)rollRotation);
            this.field_18716.rotate((Quaternionfc)rollRotation);
        }
    }

    private boolean agob$isFocusedDragonRider() {
        class_1309 livingEntity;
        class_1297 class_12972 = this.field_18711;
        return class_12972 instanceof class_1309 && (livingEntity = (class_1309)class_12972).method_5854() instanceof DragonEntity;
    }
}

