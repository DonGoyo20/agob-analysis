/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1725
 *  net.minecraft.class_1727
 *  net.minecraft.class_1799
 *  net.minecraft.class_1914
 *  net.minecraft.class_1915
 *  net.minecraft.class_3222
 *  net.minecraft.class_3468
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin;

import net.minecraft.class_1657;
import net.minecraft.class_1725;
import net.minecraft.class_1727;
import net.minecraft.class_1799;
import net.minecraft.class_1914;
import net.minecraft.class_1915;
import net.minecraft.class_3222;
import net.minecraft.class_3468;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.economy.GotEconomy;

@Mixin(value={class_1727.class})
public abstract class TradeOutputSlotMixin {
    @Shadow
    @Final
    private class_1725 field_7860;
    @Shadow
    @Final
    private class_1915 field_7858;

    @Inject(method={"method_7667"}, at={@At(value="HEAD")}, cancellable=true)
    private void got$payCurrencyWithChange(class_1657 player, class_1799 stack, CallbackInfo ci) {
        if (player == null || player.method_37908().method_8608()) {
            return;
        }
        class_1914 offer = this.field_7860.method_7642();
        if (offer == null || !offer.method_8247().method_7960()) {
            return;
        }
        int priceValue = GotEconomy.currencyValue(offer.method_19272());
        if (priceValue <= 0) {
            return;
        }
        if (this.got$tryPaySlotWithChange(player, offer, priceValue, 0) || this.got$tryPaySlotWithChange(player, offer, priceValue, 1)) {
            stack.method_7982(player.method_37908(), player, stack.method_7947());
            this.field_7858.method_8262(offer);
            player.method_7281(class_3468.field_15378);
            this.field_7858.method_19271(this.field_7858.method_19269() + offer.method_19279());
            ci.cancel();
        }
    }

    private boolean got$tryPaySlotWithChange(class_1657 player, class_1914 offer, int priceValue, int slot) {
        class_1799 input = this.field_7860.method_5438(slot);
        int unitValue = GotEconomy.currencyUnitValue(input.method_7909());
        if (unitValue <= 0 || unitValue * input.method_7947() < priceValue) {
            return false;
        }
        int coinsToConsume = Math.max(1, (int)Math.ceil((double)priceValue / (double)unitValue));
        if (coinsToConsume > input.method_7947()) {
            return false;
        }
        input.method_7934(coinsToConsume);
        int changeValue = coinsToConsume * unitValue - priceValue;
        if (player instanceof class_3222) {
            class_3222 serverPlayer = (class_3222)player;
            GotEconomy.giveCurrencyValue(serverPlayer, changeValue);
        }
        this.field_7860.method_5447(slot, input);
        this.field_7860.method_5431();
        return true;
    }
}

