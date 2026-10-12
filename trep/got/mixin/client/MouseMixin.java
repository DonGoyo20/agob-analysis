/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 *  net.minecraft.class_312
 *  net.minecraft.class_437
 *  net.minecraft.class_490
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_437;
import net.minecraft.class_490;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.client.commander.CommanderClientState;
import trep.got.client.dialogue.DialogueSceneClientState;
import trep.got.client.gui.CharacterMenuClientState;
import trep.got.client.gui.PartyMenuPanelRenderer;
import trep.got.mixin.client.HandledScreenAccessor;
import trep.got.mixin.client.InventoryScreenAccessor;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_312.class})
public class MouseMixin {
    @Inject(method={"method_1601"}, at={@At(value="HEAD")}, cancellable=true)
    private void got$handleDialogueClick(long window, int button, int action, int mods, CallbackInfo ci) {
        if (!DialogueSceneClientState.isActive()) {
            if (button == 0 && action == 1) {
                class_312 self = (class_312)this;
                if (CommanderClientState.handleLeftClick(self.method_1603(), self.method_1604())) {
                    ci.cancel();
                }
                return;
            }
            if (button == 1 && action == 1 && CommanderClientState.handleRightClick()) {
                ci.cancel();
            }
            return;
        }
        if (button == 0 && action == 1) {
            class_312 self = (class_312)this;
            if (DialogueSceneClientState.handleClick(self.method_1603(), self.method_1604())) {
                ci.cancel();
            }
            return;
        }
        ci.cancel();
    }

    @Inject(method={"method_1598"}, at={@At(value="HEAD")}, cancellable=true)
    private void got$handlePartyScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        int panelY;
        if (vertical == 0.0 || !CharacterMenuClientState.isPartyOpen()) {
            return;
        }
        class_310 client = class_310.method_1551();
        class_437 class_4372 = client.field_1755;
        if (!(class_4372 instanceof class_490)) {
            return;
        }
        class_490 screen = (class_490)class_4372;
        class_312 self = (class_312)this;
        HandledScreenAccessor handled = (HandledScreenAccessor)screen;
        int panelX = ((InventoryScreenAccessor)screen).agob$isNarrow() ? screen.field_22789 - 144 - 8 : handled.agob$getX() + handled.agob$getBackgroundWidth() + 4;
        int n = panelY = ((InventoryScreenAccessor)screen).agob$isNarrow() ? (screen.field_22790 - 144) / 2 : handled.agob$getY() + (handled.agob$getBackgroundHeight() - 144) / 2;
        if (PartyMenuPanelRenderer.handleMouseScrolled(panelX, panelY, self.method_1603(), self.method_1604(), vertical)) {
            ci.cancel();
        }
    }
}

