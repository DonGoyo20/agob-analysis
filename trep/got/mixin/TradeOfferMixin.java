/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1914
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin;

import net.minecraft.class_1799;
import net.minecraft.class_1914;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.access.TradeOfferSkillBonusAccess;
import trep.got.economy.GotEconomy;

@Mixin(value={class_1914.class})
public abstract class TradeOfferMixin
implements TradeOfferSkillBonusAccess {
    @Shadow
    @Final
    private class_1799 field_9148;
    @Unique
    private boolean got$tradingBonusApplied;
    @Unique
    private int got$baseSpecialPrice;
    @Unique
    private int got$baseSellCount;

    @Shadow
    public abstract int method_19277();

    @Shadow
    public abstract void method_19273(int var1);

    @Shadow
    public abstract class_1799 method_19272();

    @Shadow
    public abstract class_1799 method_8247();

    @Override
    public void got$applyTradingSkillBonus(int discount, int extraSellCount) {
        if (!this.got$tradingBonusApplied) {
            this.got$baseSpecialPrice = this.method_19277();
            this.got$baseSellCount = this.field_9148.method_7947();
            this.got$tradingBonusApplied = true;
        }
        this.method_19273(this.got$baseSpecialPrice - Math.max(0, discount));
        this.field_9148.method_7939(Math.max(1, this.got$baseSellCount + Math.max(0, extraSellCount)));
    }

    @Override
    public void got$resetTradingSkillBonus() {
        if (!this.got$tradingBonusApplied) {
            return;
        }
        this.method_19273(this.got$baseSpecialPrice);
        this.field_9148.method_7939(Math.max(1, this.got$baseSellCount));
        this.got$tradingBonusApplied = false;
    }

    @Inject(method={"method_16952"}, at={@At(value="HEAD")}, cancellable=true)
    private void got$allowHigherCurrencyPayment(class_1799 stack, class_1799 buyItem, CallbackInfoReturnable<Boolean> cir) {
        if (stack == null || buyItem == null || !buyItem.method_7960() || !this.method_8247().method_7960()) {
            return;
        }
        int priceValue = GotEconomy.currencyValue(this.method_19272());
        int paidUnitValue = GotEconomy.currencyUnitValue(stack.method_7909());
        if (priceValue > 0 && paidUnitValue > 0 && paidUnitValue * stack.method_7947() >= priceValue) {
            cir.setReturnValue((Object)true);
        }
    }
}

