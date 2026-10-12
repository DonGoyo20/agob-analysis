/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1728
 *  net.minecraft.class_1735
 *  net.minecraft.class_1914
 *  net.minecraft.class_1915
 *  net.minecraft.class_1916
 *  net.minecraft.class_2371
 *  net.minecraft.class_3419
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1728;
import net.minecraft.class_1735;
import net.minecraft.class_1914;
import net.minecraft.class_1915;
import net.minecraft.class_1916;
import net.minecraft.class_2371;
import net.minecraft.class_3419;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.access.SingleInputMerchantAccess;
import trep.got.access.TradeOfferSkillBonusAccess;
import trep.got.mixin.ScreenHandlerAccessor;
import trep.got.mixin.SlotAccessor;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.PlayerSkillProfile;
import trep.got.skill.SkillAttributeType;

@Mixin(value={class_1728.class})
public class MerchantScreenHandlerMixin {
    @Shadow
    @Final
    private class_1915 field_7863;
    @Unique
    private class_1657 got$player;

    @Inject(method={"<init>(ILnet/minecraft/class_1661;Lnet/minecraft/class_1915;)V"}, at={@At(value="TAIL")})
    private void got$capturePlayer(int syncId, class_1661 playerInventory, class_1915 merchant, CallbackInfo ci) {
        this.got$player = playerInventory.field_7546;
        class_2371<class_1735> slots = ((ScreenHandlerAccessor)((Object)this)).getSlots();
        if (merchant instanceof SingleInputMerchantAccess && slots.size() > 1) {
            class_1735 secondBuySlot = (class_1735)slots.get(1);
            ((SlotAccessor)secondBuySlot).setX(-1000);
            ((SlotAccessor)secondBuySlot).setY(-1000);
        }
        if (!this.got$player.method_37908().method_8608()) {
            this.got$applyTradingBonuses(merchant.method_8264());
        }
    }

    @Inject(method={"method_7595"}, at={@At(value="HEAD")})
    private void got$resetTradingBonuses(class_1657 player, CallbackInfo ci) {
        if (!player.method_37908().method_8608()) {
            this.got$resetTradingBonuses(this.field_7863.method_8264());
        }
    }

    @Inject(method={"method_20595"}, at={@At(value="HEAD")}, cancellable=true)
    private void got$playMerchantSoundWithoutEntityCast(CallbackInfo ci) {
        if (this.field_7863 instanceof class_1297 || this.got$player == null) {
            return;
        }
        if (!this.got$player.method_37908().method_8608()) {
            this.got$player.method_37908().method_43128(null, this.got$player.method_23317(), this.got$player.method_23318(), this.got$player.method_23321(), this.field_7863.method_18010(), class_3419.field_15254, 1.0f, 1.0f);
        }
        ci.cancel();
    }

    @Unique
    private void got$applyTradingBonuses(class_1916 offers) {
        int discount = this.got$getTradingDiscount();
        int extraSellCount = this.got$shouldGrantExtraResult() ? 1 : 0;
        for (class_1914 offer : offers) {
            int offerDiscount = offer.method_57556().comp_2425() > 1 ? discount : 0;
            int offerExtraSell = offer.method_57556().comp_2425() <= 1 ? extraSellCount : 0;
            ((TradeOfferSkillBonusAccess)offer).got$applyTradingSkillBonus(offerDiscount, offerExtraSell);
        }
    }

    @Unique
    private void got$resetTradingBonuses(class_1916 offers) {
        for (class_1914 offer : offers) {
            ((TradeOfferSkillBonusAccess)offer).got$resetTradingSkillBonus();
        }
    }

    @Unique
    private int got$getTradingDiscount() {
        class_1657 class_16572 = this.got$player;
        if (!(class_16572 instanceof PlayerSkillHolder)) {
            return 0;
        }
        PlayerSkillHolder skillHolder = (PlayerSkillHolder)class_16572;
        PlayerSkillProfile profile = skillHolder.got$getSkillProfile();
        return profile.get(SkillAttributeType.TRADING).getPoints() / 25;
    }

    @Unique
    private boolean got$shouldGrantExtraResult() {
        return this.got$getTradingDiscount() > 0;
    }
}

