/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1723
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_364
 *  net.minecraft.class_4185
 *  net.minecraft.class_485
 *  net.minecraft.class_490
 *  net.minecraft.class_507
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1723;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_485;
import net.minecraft.class_490;
import net.minecraft.class_507;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.client.gui.CharacterMenuButtonWidget;
import trep.got.client.gui.CharacterMenuClientState;
import trep.got.client.gui.CharacterMenuPanelRenderer;
import trep.got.client.gui.PartyMenuPanelRenderer;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_490.class})
public abstract class InventoryScreenMixin
extends class_485<class_1723> {
    @Unique
    private static final class_2960 GOT_CHARACTER_ICON = class_2960.method_60655((String)"agob", (String)"textures/item/world_map.png");
    @Unique
    private static final class_2960 GOT_PARTY_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/icon_soldier.png");
    @Shadow
    private class_507 field_2929;
    @Shadow
    private boolean field_2931;
    @Unique
    private static final int GOT_PANEL_SHIFT = 86;
    @Unique
    private CharacterMenuButtonWidget got$characterButton;
    @Unique
    private CharacterMenuButtonWidget got$partyButton;
    @Unique
    private boolean got$lastRecipeOpen;

    private InventoryScreenMixin(class_1723 handler, class_1661 inventory, class_2561 title) {
        super((class_1703)handler, inventory, title);
    }

    @Inject(method={"method_25426"}, at={@At(value="TAIL")})
    private void got$initCharacterMenu(CallbackInfo ci) {
        if (CharacterMenuClientState.isInventoryOpen() && this.field_2929.method_2605()) {
            CharacterMenuClientState.setInventoryOpen(this.field_22787, false);
        }
        if (CharacterMenuClientState.isPartyOpen() && this.field_2929.method_2605()) {
            CharacterMenuClientState.setPartyOpen(this.field_22787, false);
        }
        this.got$updateInventoryLayout();
        this.got$characterButton = new CharacterMenuButtonWidget(0, 0, GOT_CHARACTER_ICON, (class_2561)class_2561.method_43470((String)"Character Menu"), this::got$toggleCharacterMenu);
        this.got$partyButton = new CharacterMenuButtonWidget(0, 0, GOT_PARTY_ICON, (class_2561)class_2561.method_43470((String)"Troop Party"), this::got$togglePartyMenu);
        this.got$updateCharacterButtonPosition();
        this.method_37063((class_364)this.got$characterButton);
        this.method_37063((class_364)this.got$partyButton);
        this.got$lastRecipeOpen = this.field_2929.method_2605();
    }

    @Inject(method={"method_37432"}, at={@At(value="TAIL")})
    private void got$syncRecipeConflict(CallbackInfo ci) {
        boolean recipeOpen = this.field_2929.method_2605();
        if (recipeOpen && (CharacterMenuClientState.isInventoryOpen() || CharacterMenuClientState.isPartyOpen())) {
            CharacterMenuClientState.setInventoryOpen(this.field_22787, false);
            CharacterMenuClientState.setPartyOpen(this.field_22787, false);
            this.got$updateInventoryLayout();
            this.got$updateCharacterButtonPosition();
        }
        if (this.got$lastRecipeOpen != recipeOpen) {
            this.got$updateInventoryLayout();
            this.got$updateCharacterButtonPosition();
        }
        this.got$lastRecipeOpen = recipeOpen;
    }

    @Inject(method={"method_25394"}, at={@At(value="HEAD")})
    private void got$syncCharacterButtonLayout(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.got$updateInventoryLayout();
        this.got$updateCharacterButtonPosition();
    }

    @Inject(method={"method_25394"}, at={@At(value="TAIL")})
    private void got$renderCharacterMenu(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        int panelY;
        int panelX;
        if (CharacterMenuClientState.isInventoryOpen()) {
            panelX = this.got$getPanelX();
            panelY = this.got$getPanelY();
            CharacterMenuPanelRenderer.render(context, panelX, panelY, mouseX, mouseY);
        }
        if (CharacterMenuClientState.isPartyOpen()) {
            panelX = this.got$getPartyPanelX();
            panelY = this.got$getPanelY();
            PartyMenuPanelRenderer.render(context, panelX, panelY, mouseX, mouseY);
        }
    }

    @Inject(method={"method_2381"}, at={@At(value="HEAD")}, cancellable=true)
    private void got$keepPanelClicksInside(double mouseX, double mouseY, int left, int top, int button, CallbackInfoReturnable<Boolean> cir) {
        if (CharacterMenuClientState.isInventoryOpen() && this.got$isWithinCharacterPanel(mouseX, mouseY)) {
            cir.setReturnValue((Object)false);
            return;
        }
        if (CharacterMenuClientState.isPartyOpen() && this.got$isWithinPartyPanel(mouseX, mouseY)) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"method_25402"}, at={@At(value="HEAD")}, cancellable=true)
    private void got$handlePartyPanelClick(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (button != 0 || !CharacterMenuClientState.isPartyOpen()) {
            return;
        }
        if (PartyMenuPanelRenderer.handleClick(this.got$getPartyPanelX(), this.got$getPanelY(), mouseX, mouseY)) {
            cir.setReturnValue((Object)true);
        }
    }

    @Unique
    private void got$toggleCharacterMenu(class_4185 button) {
        boolean opening;
        boolean bl = opening = !CharacterMenuClientState.isInventoryOpen();
        if (opening && this.field_2929.method_2605()) {
            this.field_2929.method_2591();
        }
        CharacterMenuClientState.setInventoryOpen(this.field_22787, opening);
        if (opening) {
            CharacterMenuClientState.setPartyOpen(this.field_22787, false);
        }
        this.got$updateInventoryLayout();
        this.got$updateCharacterButtonPosition();
    }

    @Unique
    private void got$togglePartyMenu(class_4185 button) {
        boolean opening;
        boolean bl = opening = !CharacterMenuClientState.isPartyOpen();
        if (opening && this.field_2929.method_2605()) {
            this.field_2929.method_2591();
        }
        CharacterMenuClientState.setPartyOpen(this.field_22787, opening);
        if (opening) {
            CharacterMenuClientState.setInventoryOpen(this.field_22787, false);
        }
        this.got$updateInventoryLayout();
        this.got$updateCharacterButtonPosition();
    }

    @Unique
    private void got$updateInventoryLayout() {
        int baseX = (this.field_22789 - this.field_2792) / 2;
        if ((CharacterMenuClientState.isInventoryOpen() || CharacterMenuClientState.isPartyOpen()) && !this.field_2931) {
            this.field_2776 = baseX - 86;
            return;
        }
        this.field_2776 = this.field_2929.method_2595(this.field_22789, this.field_2792);
    }

    @Unique
    private void got$updateCharacterButtonPosition() {
        boolean visible;
        if (this.got$characterButton != null) {
            this.got$characterButton.method_48229(this.field_2776 + 126, this.field_22790 / 2 - 22);
            this.got$characterButton.field_22764 = visible = !this.field_2929.method_2605();
            this.got$characterButton.field_22763 = visible;
        }
        if (this.got$partyButton != null) {
            this.got$partyButton.method_48229(this.field_2776 + 147, this.field_22790 / 2 - 22);
            this.got$partyButton.field_22764 = visible = !this.field_2929.method_2605();
            this.got$partyButton.field_22763 = visible;
        }
    }

    @Unique
    private int got$getPanelX() {
        if (this.field_2931) {
            return this.field_22789 - 144 - 8;
        }
        return this.field_2776 + this.field_2792 + 4;
    }

    @Unique
    private int got$getPartyPanelX() {
        if (this.field_2931) {
            return this.field_22789 - 144 - 8;
        }
        return this.field_2776 + this.field_2792 + 4;
    }

    @Unique
    private int got$getPanelY() {
        if (this.field_2931) {
            return (this.field_22790 - 144) / 2;
        }
        return this.field_2800 + (this.field_2779 - 144) / 2;
    }

    @Unique
    private boolean got$isWithinCharacterPanel(double mouseX, double mouseY) {
        int panelX = this.got$getPanelX();
        int panelY = this.got$getPanelY();
        return mouseX >= (double)panelX && mouseX < (double)(panelX + 144) && mouseY >= (double)panelY && mouseY < (double)(panelY + 144);
    }

    @Unique
    private boolean got$isWithinPartyPanel(double mouseX, double mouseY) {
        return PartyMenuPanelRenderer.isWithinPanel(this.got$getPartyPanelX(), this.got$getPanelY(), mouseX, mouseY);
    }
}

