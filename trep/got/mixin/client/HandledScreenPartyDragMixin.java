/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1703
 *  net.minecraft.class_465
 *  net.minecraft.class_490
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1703;
import net.minecraft.class_465;
import net.minecraft.class_490;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.client.gui.CharacterMenuClientState;
import trep.got.client.gui.PartyMenuPanelRenderer;
import trep.got.mixin.client.HandledScreenAccessor;
import trep.got.mixin.client.InventoryScreenAccessor;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_465.class})
public abstract class HandledScreenPartyDragMixin<T extends class_1703> {
    @Inject(method={"method_25403"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$handlePartyPanelDrag(double mouseX, double mouseY, int button, double deltaX, double deltaY, CallbackInfoReturnable<Boolean> cir) {
        int panelY;
        HandledScreenPartyDragMixin handledScreenPartyDragMixin;
        if (button != 0 || !CharacterMenuClientState.isPartyOpen() || !((handledScreenPartyDragMixin = this) instanceof class_490)) {
            return;
        }
        class_490 screen = (class_490)handledScreenPartyDragMixin;
        HandledScreenAccessor handled = (HandledScreenAccessor)screen;
        int panelX = ((InventoryScreenAccessor)screen).agob$isNarrow() ? screen.field_22789 - 144 - 8 : handled.agob$getX() + handled.agob$getBackgroundWidth() + 4;
        int n = panelY = ((InventoryScreenAccessor)screen).agob$isNarrow() ? (screen.field_22790 - 144) / 2 : handled.agob$getY() + (handled.agob$getBackgroundHeight() - 144) / 2;
        if (PartyMenuPanelRenderer.handleMouseDragged(panelX, panelY, mouseX, mouseY)) {
            cir.setReturnValue((Object)true);
        }
    }

    @Inject(method={"method_25406"}, at={@At(value="HEAD")})
    private void agob$handlePartyPanelRelease(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (button == 0) {
            PartyMenuPanelRenderer.endScrollbarDrag();
        }
    }
}

