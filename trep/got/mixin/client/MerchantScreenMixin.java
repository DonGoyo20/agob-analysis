/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1728
 *  net.minecraft.class_2561
 *  net.minecraft.class_2588
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_465
 *  net.minecraft.class_492
 *  net.minecraft.class_7417
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1728;
import net.minecraft.class_2561;
import net.minecraft.class_2588;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_465;
import net.minecraft.class_492;
import net.minecraft.class_7417;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_492.class})
public abstract class MerchantScreenMixin
extends class_465<class_1728> {
    private static final class_2960 MARKET_DISPLAY_SELL_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/market_display_sell.png");
    private static final String MARKET_DISPLAY_SELL_TITLE_KEY = "container.agob.market_display_sell";

    private MerchantScreenMixin(class_1728 handler, class_1661 inventory, class_2561 title) {
        super((class_1703)handler, inventory, title);
    }

    @ModifyArg(method={"method_2389"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_332;method_25291(Lnet/minecraft/class_2960;IIIFFIIII)V"), index=0)
    private class_2960 got$useMarketDisplaySellTexture(class_2960 original) {
        return this.got$isMarketDisplaySellScreen() ? MARKET_DISPLAY_SELL_TEXTURE : original;
    }

    @Inject(method={"method_2388"}, at={@At(value="HEAD")}, cancellable=true)
    private void got$hideMerchantHeaderForMarketDisplay(class_332 context, int mouseX, int mouseY, CallbackInfo ci) {
        if (!this.got$isMarketDisplaySellScreen()) {
            return;
        }
        context.method_51439(this.field_22793, this.field_29347, this.field_25269, this.field_25270, 0x404040, false);
        ci.cancel();
    }

    private boolean got$isMarketDisplaySellScreen() {
        class_2588 content;
        class_7417 class_74172 = this.field_22785.method_10851();
        return class_74172 instanceof class_2588 && MARKET_DISPLAY_SELL_TITLE_KEY.equals((content = (class_2588)class_74172).method_11022());
    }
}

