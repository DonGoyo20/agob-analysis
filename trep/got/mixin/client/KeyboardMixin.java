/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_309
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.client.commander.CommanderClientState;
import trep.got.client.dialogue.DialogueSceneClientState;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_309.class})
public class KeyboardMixin {
    @Inject(method={"method_1466"}, at={@At(value="HEAD")}, cancellable=true)
    private void got$closeDialogueOnEscape(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (DialogueSceneClientState.isActive() && key == 256 && action == 1) {
            DialogueSceneClientState.close();
            ci.cancel();
            return;
        }
        if (CommanderClientState.isActive() && key == 256 && action == 1) {
            CommanderClientState.close();
            ci.cancel();
        }
    }
}

