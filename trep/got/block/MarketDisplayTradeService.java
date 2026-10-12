/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1728
 *  net.minecraft.class_1799
 *  net.minecraft.class_1914
 *  net.minecraft.class_1915
 *  net.minecraft.class_1916
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_3222
 *  net.minecraft.class_3414
 *  net.minecraft.class_3417
 *  net.minecraft.class_3908
 *  net.minecraft.class_9306
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1728;
import net.minecraft.class_1799;
import net.minecraft.class_1914;
import net.minecraft.class_1915;
import net.minecraft.class_1916;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3908;
import net.minecraft.class_9306;
import org.jetbrains.annotations.Nullable;
import trep.got.access.SingleInputMerchantAccess;
import trep.got.block.entity.MarketDisplayBlockEntity;

public final class MarketDisplayTradeService {
    private MarketDisplayTradeService() {
    }

    public static void open(class_3222 player, MarketDisplayBlockEntity marketDisplay) {
        if (!marketDisplay.method_5443((class_1657)player)) {
            return;
        }
        marketDisplay.ensureGeneratedStockForToday();
        if (!marketDisplay.hasMarketSetup() && !marketDisplay.hasAnyConfiguredTrade()) {
            return;
        }
        if (!marketDisplay.tryBeginTrading(player)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This market display is currently in use."), true);
            return;
        }
        MarketMerchant merchant = new MarketMerchant(player, marketDisplay);
        merchant.rebuildOffers();
        merchant.method_8259((class_1657)player);
        OptionalInt syncId = player.method_17355((class_3908)new Factory(merchant));
        if (syncId.isPresent()) {
            merchant.open(syncId.getAsInt());
        } else {
            merchant.method_8259(null);
        }
    }

    private static final class MarketMerchant
    implements class_1915,
    SingleInputMerchantAccess {
        private final class_3222 player;
        private final MarketDisplayBlockEntity marketDisplay;
        private final Map<class_1914, Integer> offerColumns = new IdentityHashMap<class_1914, Integer>();
        private class_1916 offers = new class_1916();
        @Nullable
        private class_1657 customer;
        private int syncId = -1;

        private MarketMerchant(class_3222 player, MarketDisplayBlockEntity marketDisplay) {
            this.player = player;
            this.marketDisplay = marketDisplay;
        }

        private void open(int syncId) {
            this.syncId = syncId;
            this.sendOffersToPlayer();
        }

        private void rebuildOffers() {
            this.marketDisplay.ensureGeneratedStockForToday();
            this.offers = new class_1916();
            this.offerColumns.clear();
            for (int column = 0; column < 9; ++column) {
                int resultLimitedUses;
                int stockLimitedUses;
                int maxUses;
                class_1799 sellStack = this.marketDisplay.getSellStack(column);
                class_1799 priceStack = this.marketDisplay.getPriceStack(column);
                int saleAmount = this.marketDisplay.getSaleAmount(column);
                if (sellStack.method_7960() || priceStack.method_7960() || saleAmount <= 0 || sellStack.method_7947() < saleAmount || !this.marketDisplay.canAcceptTradeResult(column) || (maxUses = Math.min(stockLimitedUses = sellStack.method_7947() / saleAmount, resultLimitedUses = this.maxResultUses(column, priceStack))) <= 0) continue;
                class_1799 sellItem = sellStack.method_7972();
                sellItem.method_7939(saleAmount);
                class_1914 offer = new class_1914(new class_9306((class_1935)priceStack.method_7909(), priceStack.method_7947()), Optional.empty(), sellItem, maxUses, 0, 0.0f);
                this.offers.add((Object)offer);
                this.offerColumns.put(offer, column);
            }
        }

        private void sendOffersToPlayer() {
            class_1703 class_17032 = this.player.field_7512;
            if (class_17032 instanceof class_1728) {
                class_1728 handler = (class_1728)class_17032;
                handler.method_17437(this.offers);
                handler.method_19257(0);
                handler.method_19255(0);
                handler.method_19253(false);
                handler.method_20700(false);
            }
            this.player.method_17354(this.syncId, this.offers.method_53882(), 0, 0, false, false);
        }

        private void refreshAfterTrade() {
            this.rebuildOffers();
            if (this.syncId >= 0 && this.customer == this.player) {
                this.sendOffersToPlayer();
            }
        }

        private static int maxResultUses(int column, class_1799 priceStack, MarketDisplayBlockEntity marketDisplay) {
            class_1799 resultStack = marketDisplay.getResultStack(column);
            if (resultStack.method_7960()) {
                return priceStack.method_7914() / priceStack.method_7947();
            }
            int freeSpace = resultStack.method_7914() - resultStack.method_7947();
            return freeSpace / priceStack.method_7947();
        }

        private int maxResultUses(int column, class_1799 priceStack) {
            return MarketMerchant.maxResultUses(column, priceStack, this.marketDisplay);
        }

        public void method_8259(@Nullable class_1657 customer) {
            this.customer = customer;
            if (customer == null) {
                this.marketDisplay.endTrading((class_1657)this.player);
            }
        }

        @Nullable
        public class_1657 method_8257() {
            return this.customer;
        }

        public class_1916 method_8264() {
            return this.offers;
        }

        public void method_8261(class_1916 offers) {
            this.offers = offers;
        }

        public void method_8262(class_1914 offer) {
            Integer column = this.offerColumns.get(offer);
            if (column == null) {
                return;
            }
            class_1799 sellStack = this.marketDisplay.getSellStack(column);
            class_1799 priceStack = this.marketDisplay.getPriceStack(column);
            int saleAmount = this.marketDisplay.getSaleAmount(column);
            if (sellStack.method_7960() || priceStack.method_7960() || sellStack.method_7947() < saleAmount || !this.marketDisplay.canAcceptTradeResult(column)) {
                this.refreshAfterTrade();
                return;
            }
            sellStack.method_7934(saleAmount);
            if (sellStack.method_7960()) {
                this.marketDisplay.method_5447(column, class_1799.field_8037);
            } else {
                this.marketDisplay.method_5447(column, sellStack);
            }
            int resultSlot = 9 + column;
            class_1799 resultStack = this.marketDisplay.method_5438(resultSlot);
            if (resultStack.method_7960()) {
                this.marketDisplay.method_5447(resultSlot, priceStack.method_7972());
            } else {
                resultStack.method_7933(priceStack.method_7947());
                this.marketDisplay.method_5447(resultSlot, resultStack);
            }
            offer.method_8244();
            this.marketDisplay.method_5431();
            this.refreshAfterTrade();
        }

        public void method_8258(class_1799 stack) {
        }

        public int method_19269() {
            return 0;
        }

        public void method_19271(int experience) {
        }

        public boolean method_19270() {
            return false;
        }

        public class_3414 method_18010() {
            return class_3417.field_14815;
        }

        public boolean method_38069() {
            return false;
        }
    }

    private static final class Factory
    implements class_3908 {
        private final MarketMerchant merchant;

        private Factory(MarketMerchant merchant) {
            this.merchant = merchant;
        }

        public class_2561 method_5476() {
            return class_2561.method_43471((String)"container.agob.market_display_sell");
        }

        public class_1703 createMenu(int syncId, class_1661 playerInventory, class_1657 player) {
            return new class_1728(syncId, playerInventory, (class_1915)this.merchant);
        }
    }
}

