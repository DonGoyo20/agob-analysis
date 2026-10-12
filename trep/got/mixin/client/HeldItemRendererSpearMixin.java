/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1268
 *  net.minecraft.class_1306
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_742
 *  net.minecraft.class_759
 *  net.minecraft.class_811
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 */
package trep.got.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import net.minecraft.class_759;
import net.minecraft.class_811;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import trep.got.client.spear.SpearClientPose;
import trep.got.compat.BetterCombatCompat;
import trep.got.item.SpearItem;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_759.class})
public abstract class HeldItemRendererSpearMixin {
    @WrapOperation(method={"method_3228"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_759;method_3233(Lnet/minecraft/class_1309;Lnet/minecraft/class_1799;Lnet/minecraft/class_811;ZLnet/minecraft/class_4587;Lnet/minecraft/class_4597;I)V")})
    private void agob$renderSpearPose(class_759 instance, class_1309 entity, class_1799 stack, class_811 renderMode, boolean leftHandedModel, class_4587 matrices, class_4597 consumers, int light, Operation<Void> original, @Local(argsOnly=true) class_742 player, @Local(argsOnly=true) class_1268 hand) {
        if (!BetterCombatCompat.isLoaded() && stack.method_7909() instanceof SpearItem) {
            boolean leftHanded = hand == class_1268.field_5808 ? player.method_6068() == class_1306.field_6182 : player.method_6068() != class_1306.field_6182;
            boolean lance = SpearClientPose.isLancePose((class_1309)player, stack);
            boolean brace = SpearClientPose.isBracePose((class_1309)player, stack);
            boolean charge = SpearClientPose.isChargePose((class_1309)player, stack);
            if (lance || brace) {
                matrices.method_22903();
                SpearClientPose.applyFirstPersonPose(matrices, leftHanded, lance, brace);
                original.call(new Object[]{instance, entity, stack, renderMode, leftHandedModel, matrices, consumers, light});
                matrices.method_22909();
                return;
            }
            if (charge) {
                matrices.method_22903();
                SpearClientPose.applyFirstPersonChargePose(matrices, leftHanded, SpearClientPose.getChargeStage((class_1309)player, (SpearItem)stack.method_7909()));
                original.call(new Object[]{instance, entity, stack, renderMode, leftHandedModel, matrices, consumers, light});
                matrices.method_22909();
                return;
            }
        }
        original.call(new Object[]{instance, entity, stack, renderMode, leftHandedModel, matrices, consumers, light});
    }
}

