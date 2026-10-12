/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1262
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2371
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2586
 *  net.minecraft.class_2596
 *  net.minecraft.class_2602
 *  net.minecraft.class_2622
 *  net.minecraft.class_2680
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3908
 *  net.minecraft.class_3913
 *  net.minecraft.class_5819
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_7923
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block.entity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import net.minecraft.class_1262;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3908;
import net.minecraft.class_3913;
import net.minecraft.class_5819;
import net.minecraft.class_7225;
import net.minecraft.class_7923;
import org.jetbrains.annotations.Nullable;
import trep.got.GotMod;
import trep.got.economy.GotEconomy;
import trep.got.inventory.ImplementedInventory;
import trep.got.market.MarketDisplaySetupDefinition;
import trep.got.market.MarketDisplaySetupRegistry;
import trep.got.registry.GotBlockEntities;
import trep.got.registry.GotGameRules;
import trep.got.screen.MarketDisplayScreenHandler;

public final class MarketDisplayBlockEntity
extends class_2586
implements ImplementedInventory,
class_3908 {
    public static final int COLUMN_COUNT = 9;
    public static final int SELL_SLOT_COUNT = 9;
    public static final int RESULT_SLOT_COUNT = 9;
    public static final int INVENTORY_SIZE = 18;
    private static final int PROPERTY_COUNT = 27;
    private final class_2371<class_1799> items = class_2371.method_10213((int)18, (Object)class_1799.field_8037);
    private final class_1799[] priceStacks = new class_1799[9];
    private final class_1799[] restockSellStacks = new class_1799[9];
    private final int[] saleAmounts = new int[9];
    private final class_3913 propertyDelegate = new class_3913(){

        public int method_17390(int index) {
            if (index < 9) {
                return MarketDisplayBlockEntity.this.saleAmounts[index];
            }
            if (index < 18) {
                class_1799 priceStack = MarketDisplayBlockEntity.this.priceStacks[index - 9];
                return priceStack.method_7960() ? -1 : class_7923.field_41178.method_10206((Object)priceStack.method_7909());
            }
            class_1799 priceStack = MarketDisplayBlockEntity.this.priceStacks[index - 18];
            return priceStack.method_7960() ? 0 : priceStack.method_7947();
        }

        public void method_17391(int index, int value) {
            if (index < 9) {
                MarketDisplayBlockEntity.this.saleAmounts[index] = MarketDisplayBlockEntity.clampAmount(value);
                return;
            }
            if (index < 18) {
                int column = index - 9;
                MarketDisplayBlockEntity.this.priceStacks[column] = value < 0 ? class_1799.field_8037 : new class_1799((class_1935)class_7923.field_41178.method_10200(value), Math.max(1, MarketDisplayBlockEntity.this.getPriceAmount(column)));
                return;
            }
            int column = index - 18;
            if (!MarketDisplayBlockEntity.this.priceStacks[column].method_7960()) {
                MarketDisplayBlockEntity.this.priceStacks[column].method_7939(MarketDisplayBlockEntity.clampAmount(value));
            }
        }

        public int method_17389() {
            return 27;
        }
    };
    @Nullable
    private UUID ownerUuid;
    @Nullable
    private class_2960 marketSetupId;
    private boolean generatedMarketSetup;
    private long lastRestockDay = -1L;
    private long lastObservedRestockTimeOfDay = -1L;
    @Nullable
    private UUID editingPlayerUuid;
    @Nullable
    private UUID tradingPlayerUuid;

    public MarketDisplayBlockEntity(class_2338 pos, class_2680 state) {
        super(GotBlockEntities.MARKET_DISPLAY, pos, state);
        for (int column = 0; column < 9; ++column) {
            this.saleAmounts[column] = 1;
            this.priceStacks[column] = class_1799.field_8037;
            this.restockSellStacks[column] = class_1799.field_8037;
        }
    }

    @Override
    public class_2371<class_1799> getItems() {
        return this.items;
    }

    @Override
    public boolean method_5443(class_1657 player) {
        return this.field_11863 != null && this.field_11863.method_8321(this.field_11867) == this && player.method_5649((double)this.field_11867.method_10263() + 0.5, (double)this.field_11867.method_10264() + 0.5, (double)this.field_11867.method_10260() + 0.5) <= 64.0;
    }

    public void method_5431() {
        class_1937 class_19372;
        this.normalizeColumns();
        super.method_5431();
        if (this.field_11863 != null) {
            this.field_11863.method_8413(this.field_11867, this.method_11010(), this.method_11010(), 3);
        }
        if ((class_19372 = this.field_11863) instanceof class_3218) {
            class_3218 serverWorld = (class_3218)class_19372;
            serverWorld.method_14178().method_14128(this.field_11867);
        }
    }

    public boolean tryBeginEditing(class_3222 player) {
        UUID playerUuid = player.method_5667();
        if (this.tradingPlayerUuid != null) {
            return false;
        }
        if (this.editingPlayerUuid != null && !this.editingPlayerUuid.equals(playerUuid)) {
            return false;
        }
        this.editingPlayerUuid = playerUuid;
        return true;
    }

    public void endEditing(class_1657 player) {
        if (this.editingPlayerUuid != null && this.editingPlayerUuid.equals(player.method_5667())) {
            this.editingPlayerUuid = null;
        }
    }

    public boolean isEditLockedFor(class_1657 player) {
        return this.editingPlayerUuid != null && !this.editingPlayerUuid.equals(player.method_5667());
    }

    public boolean isTradingLockedFor(class_1657 player) {
        return this.tradingPlayerUuid != null && !this.tradingPlayerUuid.equals(player.method_5667());
    }

    public boolean tryBeginTrading(class_3222 player) {
        if (this.editingPlayerUuid != null) {
            return false;
        }
        if (this.tradingPlayerUuid != null && !this.tradingPlayerUuid.equals(player.method_5667())) {
            return false;
        }
        this.tradingPlayerUuid = player.method_5667();
        return true;
    }

    public void endTrading(class_1657 player) {
        if (this.tradingPlayerUuid != null && this.tradingPlayerUuid.equals(player.method_5667())) {
            this.tradingPlayerUuid = null;
        }
    }

    public class_3913 getPropertyDelegate() {
        return this.propertyDelegate;
    }

    public class_1799 getPriceStack(int column) {
        return this.priceStacks[column].method_7972();
    }

    public int getSaleAmount(int column) {
        return this.saleAmounts[column];
    }

    public int getPriceAmount(int column) {
        class_1799 priceStack = this.priceStacks[column];
        return priceStack.method_7960() ? 0 : priceStack.method_7947();
    }

    public class_1799 getSellStack(int column) {
        return (class_1799)this.items.get(column);
    }

    public class_1799 getResultStack(int column) {
        return (class_1799)this.items.get(9 + column);
    }

    public class_1799 getRenderedDisplayStack() {
        for (int column = 0; column < 9; ++column) {
            class_1799 sellStack = this.getSellStack(column);
            if (sellStack.method_7960()) continue;
            return sellStack;
        }
        return class_1799.field_8037;
    }

    public void setOwnerUuid(@Nullable UUID ownerUuid) {
        if (!Objects.equals(this.ownerUuid, ownerUuid)) {
            this.ownerUuid = ownerUuid;
            this.method_5431();
        }
    }

    @Nullable
    public UUID getOwnerUuid() {
        return this.ownerUuid;
    }

    @Nullable
    public class_2960 getMarketSetupId() {
        return this.marketSetupId;
    }

    public boolean hasMarketSetup() {
        return this.marketSetupId != null;
    }

    public boolean hasAnyConfiguredTrade() {
        for (int column = 0; column < 9; ++column) {
            if (this.getSellStack(column).method_7960() || this.priceStacks[column].method_7960()) continue;
            return true;
        }
        return false;
    }

    public boolean isGeneratedMarketSetup() {
        return this.generatedMarketSetup;
    }

    public boolean canEdit(class_3222 player) {
        if (this.generatedMarketSetup) {
            return player.method_5687(2);
        }
        return this.ownerUuid == null || this.ownerUuid.equals(player.method_5667()) || player.method_5687(2);
    }

    public boolean applyMarketSetup(class_2960 setupId, boolean generated) {
        MarketDisplaySetupDefinition definition = MarketDisplaySetupRegistry.get(setupId);
        if (definition == null) {
            return false;
        }
        this.applyMarketSetup(setupId, definition, generated, this.resolveSetupRandom());
        this.method_5431();
        return true;
    }

    public void ensureGeneratedStockForToday() {
        boolean wrappedToNewMorning;
        if (!this.generatedMarketSetup || this.field_11863 == null || this.field_11863.method_8608()) {
            return;
        }
        long currentDay = this.currentDay();
        long currentTimeOfDay = this.currentNormalizedTimeOfDay();
        boolean bl = wrappedToNewMorning = this.lastObservedRestockTimeOfDay >= 0L && currentTimeOfDay < this.lastObservedRestockTimeOfDay;
        if (currentDay <= this.lastRestockDay && !wrappedToNewMorning) {
            this.lastObservedRestockTimeOfDay = currentTimeOfDay;
            return;
        }
        this.restockFromTemplate(currentDay, currentTimeOfDay);
        this.method_5431();
    }

    public void updateGeneratedRestockTemplateFromCurrentStock() {
        if (!this.generatedMarketSetup) {
            return;
        }
        for (int column = 0; column < 9; ++column) {
            class_1799 sellStack = this.getSellStack(column);
            this.restockSellStacks[column] = sellStack.method_7960() ? class_1799.field_8037 : sellStack.method_7972();
        }
        this.restockFromTemplate(this.currentDay(), this.currentNormalizedTimeOfDay());
        this.method_5431();
    }

    public void adjustSaleAmount(int column, int delta) {
        this.saleAmounts[column] = this.getSellStack(column).method_7960() ? 1 : MarketDisplayBlockEntity.clampAmount(this.saleAmounts[column] + delta);
        this.method_5431();
    }

    public void setPriceItem(int column, class_1799 stack) {
        this.priceStacks[column] = stack.method_7960() ? class_1799.field_8037 : stack.method_46651(1);
        this.method_5431();
    }

    public void adjustPriceAmount(int column, int delta) {
        if (this.priceStacks[column].method_7960()) {
            return;
        }
        this.priceStacks[column].method_7939(MarketDisplayBlockEntity.clampAmount(this.priceStacks[column].method_7947() + delta));
        this.method_5431();
    }

    public boolean canAcceptTradeResult(int column) {
        class_1799 priceStack = this.priceStacks[column];
        if (priceStack.method_7960()) {
            return false;
        }
        class_1799 resultStack = this.getResultStack(column);
        if (resultStack.method_7960()) {
            return true;
        }
        if (!class_1799.method_31577((class_1799)resultStack, (class_1799)priceStack)) {
            return false;
        }
        return resultStack.method_7947() + priceStack.method_7947() <= resultStack.method_7914();
    }

    public void normalizeColumns() {
        for (int column = 0; column < 9; ++column) {
            class_1799 priceStack;
            this.saleAmounts[column] = MarketDisplayBlockEntity.clampAmount(this.saleAmounts[column]);
            class_1799 sellStack = this.getSellStack(column);
            if (sellStack.method_7960()) {
                this.saleAmounts[column] = 1;
            }
            if ((priceStack = this.priceStacks[column]).method_7960()) continue;
            priceStack.method_7939(MarketDisplayBlockEntity.clampAmount(priceStack.method_7947()));
        }
    }

    public class_2561 method_5476() {
        return class_2561.method_43471((String)"container.agob.market_display");
    }

    public class_1703 createMenu(int syncId, class_1661 playerInventory, class_1657 player) {
        return new MarketDisplayScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    protected void method_11007(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11007(nbt, registries);
        class_1262.method_5426((class_2487)nbt, this.items, (class_7225.class_7874)registries);
        if (this.ownerUuid != null) {
            nbt.method_25927("owner_uuid", this.ownerUuid);
        }
        if (this.marketSetupId != null) {
            nbt.method_10582("market_setup", this.marketSetupId.toString());
            nbt.method_10556("market_setup_generated", this.generatedMarketSetup);
            nbt.method_10544("market_last_restock_day", this.lastRestockDay);
            nbt.method_10544("market_last_observed_restock_time", this.lastObservedRestockTimeOfDay);
        }
        for (int column = 0; column < 9; ++column) {
            nbt.method_10569("sale_amount_" + column, this.saleAmounts[column]);
            if (!this.priceStacks[column].method_7960()) {
                nbt.method_10566("price_stack_" + column, this.priceStacks[column].method_57358(registries));
            }
            if (this.restockSellStacks[column].method_7960()) continue;
            nbt.method_10566("restock_sell_stack_" + column, this.restockSellStacks[column].method_57358(registries));
        }
    }

    protected void method_11014(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11014(nbt, registries);
        class_1262.method_5429((class_2487)nbt, this.items, (class_7225.class_7874)registries);
        for (int slot = 0; slot < this.items.size(); ++slot) {
            this.items.set(slot, (Object)GotEconomy.normalizeLegacyCurrencyStack((class_1799)this.items.get(slot)));
        }
        this.ownerUuid = nbt.method_25928("owner_uuid") ? nbt.method_25926("owner_uuid") : null;
        this.marketSetupId = MarketDisplayBlockEntity.parseIdentifier(nbt, "market_setup");
        this.generatedMarketSetup = nbt.method_10577("market_setup_generated");
        this.lastRestockDay = nbt.method_10545("market_last_restock_day") ? nbt.method_10537("market_last_restock_day") : -1L;
        this.lastObservedRestockTimeOfDay = nbt.method_10545("market_last_observed_restock_time") ? nbt.method_10537("market_last_observed_restock_time") : -1L;
        for (int column = 0; column < 9; ++column) {
            this.saleAmounts[column] = nbt.method_10545("sale_amount_" + column) ? MarketDisplayBlockEntity.clampAmount(nbt.method_10550("sale_amount_" + column)) : 1;
            this.priceStacks[column] = nbt.method_10545("price_stack_" + column) ? class_1799.method_57359((class_7225.class_7874)registries, (class_2487)nbt.method_10562("price_stack_" + column)) : class_1799.field_8037;
            this.restockSellStacks[column] = nbt.method_10545("restock_sell_stack_" + column) ? class_1799.method_57359((class_7225.class_7874)registries, (class_2487)nbt.method_10562("restock_sell_stack_" + column)) : class_1799.field_8037;
            this.priceStacks[column] = GotEconomy.normalizeLegacyCurrencyStack(this.priceStacks[column]);
            this.restockSellStacks[column] = GotEconomy.normalizeLegacyCurrencyStack(this.restockSellStacks[column]);
            if (!this.priceStacks[column].method_7960()) {
                this.priceStacks[column].method_7939(MarketDisplayBlockEntity.clampAmount(this.priceStacks[column].method_7947()));
            }
            if (this.restockSellStacks[column].method_7960()) continue;
            this.restockSellStacks[column].method_7939(MarketDisplayBlockEntity.clampAmount(this.restockSellStacks[column].method_7947()));
        }
        class_2960 pendingSetupId = MarketDisplayBlockEntity.parseIdentifier(nbt, "LootTable");
        if (pendingSetupId != null) {
            MarketDisplaySetupDefinition definition = MarketDisplaySetupRegistry.get(pendingSetupId);
            if (definition != null) {
                this.applyMarketSetup(pendingSetupId, definition, true, this.resolveSetupRandom());
            } else {
                GotMod.LOGGER.warn("Unknown market setup {} for market display at {}", (Object)pendingSetupId, (Object)this.field_11867);
                this.normalizeColumns();
            }
        } else {
            this.initializeGeneratedTemplateFromCurrentStateIfMissing();
            this.normalizeColumns();
        }
    }

    public class_2487 method_16887(class_7225.class_7874 registries) {
        return this.method_38244(registries);
    }

    @Nullable
    public class_2596<class_2602> method_38235() {
        return class_2622.method_38585((class_2586)this);
    }

    private static int clampAmount(int amount) {
        return Math.max(1, Math.min(64, amount));
    }

    private void applyMarketSetup(class_2960 setupId, MarketDisplaySetupDefinition definition, boolean generated, class_5819 random) {
        for (int slot = 0; slot < 18; ++slot) {
            this.items.set(slot, (Object)class_1799.field_8037);
        }
        for (int column = 0; column < 9; ++column) {
            this.saleAmounts[column] = 1;
            this.priceStacks[column] = class_1799.field_8037;
        }
        List<MarketDisplaySetupDefinition.Entry> chosenEntries = definition.chooseEntries(random);
        for (int column = 0; column < chosenEntries.size(); ++column) {
            MarketDisplaySetupDefinition.Entry setupColumn = chosenEntries.get(column);
            class_1799 stockStack = new class_1799((class_1935)setupColumn.sellItem(), MarketDisplayBlockEntity.clampAmount(setupColumn.sellCount().roll(random)));
            this.items.set(column, (Object)stockStack.method_7972());
            this.restockSellStacks[column] = stockStack.method_7972();
            this.saleAmounts[column] = MarketDisplayBlockEntity.clampAmount(setupColumn.saleAmount().roll(random));
            this.priceStacks[column] = GotEconomy.normalizeLegacyCurrencyStack(new class_1799((class_1935)setupColumn.buyItem(), MarketDisplayBlockEntity.clampAmount(setupColumn.buyAmount().roll(random))));
        }
        this.marketSetupId = setupId;
        this.generatedMarketSetup = generated;
        this.lastRestockDay = this.currentDay();
        this.lastObservedRestockTimeOfDay = this.currentNormalizedTimeOfDay();
        this.ownerUuid = null;
        this.editingPlayerUuid = null;
        this.tradingPlayerUuid = null;
        this.normalizeColumns();
    }

    @Nullable
    private static class_2960 parseIdentifier(class_2487 nbt, String key) {
        if (!nbt.method_10545(key)) {
            return null;
        }
        try {
            return class_2960.method_60654((String)nbt.method_10558(key));
        }
        catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    private class_5819 resolveSetupRandom() {
        if (this.field_11863 != null) {
            return this.field_11863.field_9229;
        }
        return class_5819.method_43047();
    }

    private void restockFromTemplate(long day, long timeOfDay) {
        for (int column = 0; column < 9; ++column) {
            class_1799 restockStack = this.restockSellStacks[column];
            this.items.set(column, (Object)(restockStack.method_7960() ? class_1799.field_8037 : restockStack.method_7972()));
        }
        this.lastRestockDay = day;
        this.lastObservedRestockTimeOfDay = timeOfDay;
    }

    private void initializeGeneratedTemplateFromCurrentStateIfMissing() {
        if (!this.generatedMarketSetup) {
            return;
        }
        boolean hasTemplate = false;
        for (class_1799 template : this.restockSellStacks) {
            if (template.method_7960()) continue;
            hasTemplate = true;
            break;
        }
        if (hasTemplate) {
            return;
        }
        for (int column = 0; column < 9; ++column) {
            class_1799 sellStack = this.getSellStack(column);
            this.restockSellStacks[column] = sellStack.method_7960() ? class_1799.field_8037 : sellStack.method_7972();
        }
        if (this.lastRestockDay < 0L) {
            this.lastRestockDay = this.currentDay();
        }
        if (this.lastObservedRestockTimeOfDay < 0L) {
            this.lastObservedRestockTimeOfDay = this.currentNormalizedTimeOfDay();
        }
    }

    private long currentDay() {
        long l;
        class_1937 class_19372 = this.field_11863;
        if (class_19372 instanceof class_3218) {
            class_3218 serverWorld = (class_3218)class_19372;
            l = GotGameRules.trackedDay(serverWorld);
        } else {
            l = this.field_11863 == null ? 0L : Math.max(0L, this.field_11863.method_8532() / 24000L);
        }
        return l;
    }

    private long currentNormalizedTimeOfDay() {
        return this.field_11863 == null ? 0L : GotGameRules.normalizeTimeOfDay(this.field_11863.method_8532());
    }
}

