/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 *  net.minecraft.class_743
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_743;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.client.dialogue.DialogueSceneClientState;
import trep.got.client.voyage.VoyageClientState;
import trep.got.item.SpearItem;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_743.class})
public class KeyboardInputMixin {
    @Inject(method={"method_3129"}, at={@At(value="TAIL")})
    private void got$lockMovement(boolean slowDown, float slowDownFactor, CallbackInfo ci) {
        class_743 self = (class_743)this;
        class_746 player = class_310.method_1551().field_1724;
        if (slowDown && slowDownFactor != 0.0f && player != null && player.method_6115() && player.method_6030().method_7909() instanceof SpearItem) {
            self.field_3905 /= slowDownFactor;
            self.field_3907 /= slowDownFactor;
        }
        if (VoyageClientState.shouldSuppressSneak()) {
            self.field_3903 = false;
        }
        if (!DialogueSceneClientState.isActive()) {
            return;
        }
        self.field_3905 = 0.0f;
        self.field_3907 = 0.0f;
        self.field_3904 = false;
        self.field_3903 = false;
    }
}

