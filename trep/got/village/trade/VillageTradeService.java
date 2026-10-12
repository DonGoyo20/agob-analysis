/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1728
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1914
 *  net.minecraft.class_1915
 *  net.minecraft.class_1916
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2561
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3908
 *  net.minecraft.class_9306
 */
package trep.got.village.trade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1728;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1914;
import net.minecraft.class_1915;
import net.minecraft.class_1916;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3908;
import net.minecraft.class_9306;
import trep.got.entity.AbstractKingdomVillageElderNpcEntity;
import trep.got.registry.GotItems;
import trep.got.registry.GotVillageResources;
import trep.got.village.VillageInstance;
import trep.got.village.VillageWorldState;
import trep.got.village.economy.VillageStockService;
import trep.got.village.resource.VillageResource;
import trep.got.village.trade.PlayerVillageTradeHolder;
import trep.got.village.trade.PlayerVillageTradeProfile;

public final class VillageTradeService {
    private VillageTradeService() {
    }

    public static void openVillageTrade(class_3222 player, AbstractKingdomVillageElderNpcEntity elder) {
        if (player == null || elder == null || elder.method_31481() || elder.method_37908() != player.method_37908()) {
            return;
        }
        class_1937 class_19372 = player.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        if (player.method_5858((class_1297)elder) > 64.0) {
            return;
        }
        VillageInstance village = VillageWorldState.get(world).get(elder.got$getCastle());
        if (village == null || village.getResourceId() == null) {
            return;
        }
        VillageResource resource = GotVillageResources.get(village.getResourceId());
        if (resource == null) {
            return;
        }
        if (elder.method_8257() != null && elder.method_8257() != player) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This village elder is already trading with someone else."), true);
            return;
        }
        VillageElderTradeSession tradeSession = new VillageElderTradeSession(player, elder, village, resource);
        elder.setVillageTradeSession(tradeSession);
        elder.method_8259((class_1657)player);
        OptionalInt syncId = player.method_17355((class_3908)new Factory(elder, elder.method_5476()));
        if (syncId.isPresent()) {
            tradeSession.open(syncId.getAsInt());
        } else {
            elder.method_8259(null);
        }
    }

    private static class_1914 offer(OfferSpec spec, class_1935 buyItem) {
        return new class_1914(new class_9306(buyItem, spec.price()), Optional.empty(), spec.sellStack().method_7972(), 9999, spec.merchantExperience(), 0.0f);
    }

    private static class_1914 coinOffer(OfferSpec spec) {
        return new class_1914(new class_9306((class_1935)GotItems.GOLDEN_DRAGON, spec.price() * 2), Optional.empty(), spec.sellStack().method_7972(), 9999, spec.merchantExperience(), 0.0f);
    }

    private static List<class_1914> buildOffers(class_3218 world, VillageInstance village, VillageResource resource, int level) {
        ArrayList<class_1914> offers = new ArrayList<class_1914>();
        boolean goldVillage = resource == GotVillageResources.GOLD_ORE;
        class_1792 currency = class_1802.field_8477;
        int availableStock = VillageStockService.getAvailableStock(world, village);
        for (OfferSpec spec : VillageTradeService.offerSpecsFor(resource, level)) {
            if (spec.stockCost() > availableStock) continue;
            offers.add(goldVillage ? VillageTradeService.offer(spec, (class_1935)currency) : VillageTradeService.coinOffer(spec));
        }
        return offers;
    }

    private static List<OfferSpec> offerSpecsFor(VillageResource resource, int level) {
        List<OfferSpec> all = VillageTradeService.allOfferSpecsFor(resource);
        int unlockedCount = switch (level) {
            case 1 -> 2;
            case 2 -> 4;
            default -> 6;
        };
        return all.subList(0, Math.min(unlockedCount, all.size()));
    }

    private static List<OfferSpec> allOfferSpecsFor(VillageResource resource) {
        if (resource == GotVillageResources.HORSES) {
            return List.of(new OfferSpec(4, 4, new class_1799((class_1935)class_1802.field_8175, 1), 1), new OfferSpec(6, 6, new class_1799((class_1935)class_1802.field_8719, 2), 1), new OfferSpec(10, 8, new class_1799((class_1935)class_1802.field_17528, 8), 2), new OfferSpec(14, 10, new class_1799((class_1935)class_1802.field_8578, 1), 2), new OfferSpec(20, 12, new class_1799((class_1935)class_1802.field_8560, 1), 3), new OfferSpec(28, 16, new class_1799((class_1935)class_1802.field_8807, 1), 4));
        }
        class_1799 baseStack = resource.getIconStack().method_7972();
        if (baseStack.method_7960()) {
            return List.of();
        }
        if (resource == GotVillageResources.IRON_ORE) {
            return List.of(VillageTradeService.spec(baseStack, 5, 1, 1, 1), VillageTradeService.spec(baseStack, 9, 2, 2, 1), VillageTradeService.spec(baseStack, 13, 3, 3, 2), VillageTradeService.spec(baseStack, 18, 4, 4, 2), VillageTradeService.spec(baseStack, 24, 6, 6, 3), VillageTradeService.spec(baseStack, 32, 8, 8, 4));
        }
        if (resource == GotVillageResources.COPPER_ORE) {
            return List.of(VillageTradeService.spec(baseStack, 4, 2, 2, 1), VillageTradeService.spec(baseStack, 7, 4, 4, 1), VillageTradeService.spec(baseStack, 10, 6, 6, 2), VillageTradeService.spec(baseStack, 14, 8, 8, 2), VillageTradeService.spec(baseStack, 19, 12, 12, 3), VillageTradeService.spec(baseStack, 24, 16, 16, 4));
        }
        if (resource == GotVillageResources.GOLD_ORE) {
            return List.of(VillageTradeService.spec(baseStack, 2, 1, 1, 1), VillageTradeService.spec(baseStack, 4, 2, 2, 1), VillageTradeService.spec(baseStack, 6, 3, 3, 2), VillageTradeService.spec(baseStack, 9, 4, 4, 2), VillageTradeService.spec(baseStack, 13, 6, 6, 3), VillageTradeService.spec(baseStack, 18, 8, 8, 4));
        }
        if (VillageTradeService.isWood(resource)) {
            return List.of(VillageTradeService.spec(baseStack, 3, 4, 4, 1), VillageTradeService.spec(baseStack, 5, 8, 8, 1), VillageTradeService.spec(baseStack, 8, 12, 12, 2), VillageTradeService.spec(baseStack, 11, 16, 16, 2), VillageTradeService.spec(baseStack, 15, 24, 24, 3), VillageTradeService.spec(baseStack, 20, 32, 32, 4));
        }
        return List.of(VillageTradeService.spec(baseStack, 3, 6, 6, 1), VillageTradeService.spec(baseStack, 5, 12, 12, 1), VillageTradeService.spec(baseStack, 7, 18, 18, 2), VillageTradeService.spec(baseStack, 10, 24, 24, 2), VillageTradeService.spec(baseStack, 14, 32, 32, 3), VillageTradeService.spec(baseStack, 18, 40, 40, 4));
    }

    private static OfferSpec spec(class_1799 baseStack, int price, int count, int stockCost, int merchantExperience) {
        class_1799 sellStack = baseStack.method_7972();
        sellStack.method_7939(Math.max(1, count));
        return new OfferSpec(price, stockCost, sellStack, merchantExperience);
    }

    private static boolean isWood(VillageResource resource) {
        return resource == GotVillageResources.WOOD_OAK || resource == GotVillageResources.WOOD_SPRUCE || resource == GotVillageResources.WOOD_BIRCH || resource == GotVillageResources.WOOD_JUNGLE || resource == GotVillageResources.WOOD_ACACIA || resource == GotVillageResources.WOOD_DARK_OAK;
    }

    private static int stockCostForOffer(VillageResource resource, int level, class_1914 offer) {
        for (OfferSpec spec : VillageTradeService.offerSpecsFor(resource, level)) {
            if (!class_1799.method_31577((class_1799)spec.sellStack(), (class_1799)offer.method_8250()) || spec.sellStack().method_7947() != offer.method_8250().method_7947()) continue;
            return spec.stockCost();
        }
        return -1;
    }

    private static final class VillageElderTradeSession
    implements AbstractKingdomVillageElderNpcEntity.VillageTradeSession {
        private final class_3222 player;
        private final AbstractKingdomVillageElderNpcEntity elder;
        private final VillageInstance village;
        private final VillageResource resource;
        private int syncId = -1;

        private VillageElderTradeSession(class_3222 player, AbstractKingdomVillageElderNpcEntity elder, VillageInstance village, VillageResource resource) {
            this.player = player;
            this.elder = elder;
            this.village = village;
            this.resource = resource;
            this.rebuildOffers();
        }

        private void open(int syncId) {
            this.syncId = syncId;
            this.sendOffersToPlayer();
        }

        private void rebuildOffers() {
            int level = this.currentLevel();
            class_1916 offers = new class_1916();
            for (class_1914 offer : VillageTradeService.buildOffers(this.player.method_51469(), this.village, this.resource, level)) {
                offers.add((Object)offer);
            }
            this.elder.method_8261(offers);
            this.elder.method_19271(this.currentExperience());
        }

        private void sendOffersToPlayer() {
            class_1703 class_17032 = this.player.field_7512;
            if (class_17032 instanceof class_1728) {
                class_1728 handler = (class_1728)class_17032;
                handler.method_17437(this.elder.method_8264().method_53882());
                handler.method_19257(this.currentLevel());
                handler.method_19255(this.currentExperience());
                handler.method_19253(true);
                handler.method_20700(false);
            }
            this.player.method_17354(this.syncId, this.elder.method_8264().method_53882(), this.currentLevel(), this.currentExperience(), true, false);
        }

        private PlayerVillageTradeProfile tradeProfile() {
            return ((PlayerVillageTradeHolder)this.player).got$getVillageTradeProfile();
        }

        private int currentLevel() {
            return this.tradeProfile().getLevel(this.village.getId());
        }

        private int currentExperience() {
            PlayerVillageTradeProfile profile = this.tradeProfile();
            int required = profile.getTradesRequiredForNextLevel(this.village.getId());
            if (required <= 0) {
                return 0;
            }
            return Math.min(required, profile.getLevelProgress(this.village.getId()));
        }

        @Override
        public void trade(class_1914 offer) {
            int stockCost = VillageTradeService.stockCostForOffer(this.resource, this.currentLevel(), offer);
            if (stockCost <= 0 || !VillageStockService.consumeStock(this.player.method_51469(), this.village, stockCost)) {
                this.rebuildOffers();
                if (this.syncId >= 0) {
                    this.sendOffersToPlayer();
                }
                this.player.method_7353((class_2561)class_2561.method_43470((String)"This village is currently out of that stock."), true);
                return;
            }
            offer.method_8244();
            this.tradeProfile().recordTrade(this.village.getId());
            this.rebuildOffers();
            if (this.syncId >= 0) {
                this.sendOffersToPlayer();
            }
        }
    }

    private record Factory(AbstractKingdomVillageElderNpcEntity merchant, class_2561 displayName) implements class_3908
    {
        public class_2561 method_5476() {
            return this.displayName;
        }

        public class_1703 createMenu(int syncId, class_1661 playerInventory, class_1657 player) {
            return new class_1728(syncId, playerInventory, (class_1915)this.merchant);
        }
    }

    private record OfferSpec(int price, int stockCost, class_1799 sellStack, int merchantExperience) {
    }
}

