/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1306
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_759
 *  net.minecraft.class_811
 *  net.minecraft.class_989
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 */
package trep.got.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_759;
import net.minecraft.class_811;
import net.minecraft.class_989;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import trep.got.client.spear.SpearClientPose;
import trep.got.compat.BetterCombatCompat;
import trep.got.item.SpearItem;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_989.class})
public abstract class HeldItemFeatureRendererSpearMixin {
    @WrapOperation(method={"method_4192"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_759;method_3233(Lnet/minecraft/class_1309;Lnet/minecraft/class_1799;Lnet/minecraft/class_811;ZLnet/minecraft/class_4587;Lnet/minecraft/class_4597;I)V")})
    private void agob$renderThirdPersonSpearPose(class_759 instance, class_1309 entity, class_1799 stack, class_811 renderMode, boolean leftHandedModel, class_4587 matrices, class_4597 consumers, int light, Operation<Void> original, class_1309 renderEntity, class_1799 renderStack, class_811 transformationMode, class_1306 arm) {
        if (!BetterCombatCompat.isLoaded() && stack.method_7909() instanceof SpearItem) {
            boolean leftHanded = arm == class_1306.field_6182;
            boolean lance = SpearClientPose.isLancePose(entity, stack);
            boolean brace = SpearClientPose.isBracePose(entity, stack);
            boolean charge = SpearClientPose.isChargePose(entity, stack);
            if (lance || brace) {
                matrices.method_22903();
                SpearClientPose.applyThirdPersonPose(matrices, leftHanded, lance, brace);
                original.call(new Object[]{instance, entity, stack, renderMode, leftHandedModel, matrices, consumers, light});
                matrices.method_22909();
                return;
            }
            if (charge) {
                matrices.method_22903();
                SpearClientPose.applyThirdPersonChargePose(matrices, leftHanded, SpearClientPose.getChargeStage(entity, (SpearItem)stack.method_7909()));
                original.call(new Object[]{instance, entity, stack, renderMode, leftHandedModel, matrices, consumers, light});
                matrices.method_22909();
                return;
            }
        }
        original.call(new Object[]{instance, entity, stack, renderMode, leftHandedModel, matrices, consumers, light});
    }
}

