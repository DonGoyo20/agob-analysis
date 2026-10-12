/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1263
 *  net.minecraft.class_1277
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1735
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_3222
 *  net.minecraft.class_3913
 *  net.minecraft.class_3919
 *  net.minecraft.class_7923
 */
package trep.got.screen;

import net.minecraft.class_1263;
import net.minecraft.class_1277;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_3222;
import net.minecraft.class_3913;
import net.minecraft.class_3919;
import net.minecraft.class_7923;
import trep.got.block.entity.MarketDisplayBlockEntity;
import trep.got.registry.GotScreenHandlers;

public final class MarketDisplayScreenHandler
extends class_1703 {
    public static final int SELL_SLOT_START = 0;
    public static final int SELL_SLOT_END = 9;
    public static final int RESULT_SLOT_START = 9;
    public static final int RESULT_SLOT_END = 18;
    public static final int PROPERTY_COUNT = 27;
    private static final int BUTTON_SALE_INC_BASE = 0;
    private static final int BUTTON_SALE_DEC_BASE = 16;
    private static final int BUTTON_SALE_INC_EIGHT_BASE = 32;
    private static final int BUTTON_SALE_DEC_EIGHT_BASE = 48;
    private static final int BUTTON_PRICE_INC_BASE = 64;
    private static final int BUTTON_PRICE_DEC_BASE = 80;
    private static final int BUTTON_SET_PRICE_BASE = 1000;
    private static final int BUTTON_SET_PRICE_COLUMN_MULTIPLIER = 100000;
    private final class_1263 inventory;
    private final class_3913 properties;

    public MarketDisplayScreenHandler(int syncId, class_1661 playerInventory) {
        this(syncId, playerInventory, (class_1263)new class_1277(18), (class_3913)new class_3919(27));
    }

    public MarketDisplayScreenHandler(int syncId, class_1661 playerInventory, class_1263 inventory, class_3913 properties) {
        super(GotScreenHandlers.MARKET_DISPLAY, syncId);
        int column;
        MarketDisplayScreenHandler.method_17359((class_1263)inventory, (int)18);
        MarketDisplayScreenHandler.method_17361((class_3913)properties, (int)27);
        this.inventory = inventory;
        this.properties = properties;
        inventory.method_5435(playerInventory.field_7546);
        for (column = 0; column < 9; ++column) {
            this.method_7621(new class_1735(inventory, column, 8 + column * 18, 18));
        }
        for (column = 0; column < 9; ++column) {
            this.method_7621(new ResultSlot(inventory, 9 + column, 8 + column * 18, 88));
        }
        this.addPlayerSlots(playerInventory, 8, 118);
        this.method_17360(properties);
    }

    public class_1799 getPriceStack(int column) {
        int rawId = this.properties.method_17390(9 + column);
        int amount = this.properties.method_17390(18 + column);
        if (rawId < 0 || amount <= 0) {
            return class_1799.field_8037;
        }
        class_1792 item = (class_1792)class_7923.field_41178.method_10200(rawId);
        return item == null ? class_1799.field_8037 : new class_1799((class_1935)item, amount);
    }

    public int getSaleAmount(int column) {
        return this.properties.method_17390(column);
    }

    public boolean method_7604(class_1657 player, int id) {
        class_1263 class_12632 = this.inventory;
        if (!(class_12632 instanceof MarketDisplayBlockEntity)) {
            return false;
        }
        MarketDisplayBlockEntity marketDisplay = (MarketDisplayBlockEntity)class_12632;
        if (id >= 1000) {
            int encoded = id - 1000;
            int column = encoded / 100000;
            int rawId = encoded % 100000;
            if (column < 0 || column >= 9) {
                return false;
            }
            class_1792 item = (class_1792)class_7923.field_41178.method_10200(rawId);
            if (item == null) {
                return false;
            }
            marketDisplay.setPriceItem(column, new class_1799((class_1935)item, 1));
            return true;
        }
        if (id >= 80 && id < 89) {
            marketDisplay.adjustPriceAmount(id - 80, -1);
            return true;
        }
        if (id >= 64 && id < 73) {
            marketDisplay.adjustPriceAmount(id - 64, 1);
            return true;
        }
        if (id >= 48 && id < 57) {
            marketDisplay.adjustSaleAmount(id - 48, -8);
            return true;
        }
        if (id >= 32 && id < 41) {
            marketDisplay.adjustSaleAmount(id - 32, 8);
            return true;
        }
        if (id >= 16 && id < 25) {
            marketDisplay.adjustSaleAmount(id - 16, -1);
            return true;
        }
        if (id >= 0 && id < 9) {
            marketDisplay.adjustSaleAmount(id - 0, 1);
            return true;
        }
        return false;
    }

    public class_1799 method_7601(class_1657 player, int slotIndex) {
        class_1799 copied = class_1799.field_8037;
        class_1735 slot = (class_1735)this.field_7761.get(slotIndex);
        if (slot == null || !slot.method_7681()) {
            return class_1799.field_8037;
        }
        class_1799 sourceStack = slot.method_7677();
        copied = sourceStack.method_7972();
        if (slotIndex < 18 ? !this.method_7616(sourceStack, 18, this.field_7761.size(), true) : !this.method_7616(sourceStack, 0, 9, false)) {
            return class_1799.field_8037;
        }
        if (sourceStack.method_7960()) {
            slot.method_53512(class_1799.field_8037);
        } else {
            slot.method_7668();
        }
        return copied;
    }

    public boolean method_7597(class_1657 player) {
        return this.inventory.method_5443(player);
    }

    public void method_7595(class_1657 player) {
        super.method_7595(player);
        this.inventory.method_5432(player);
        class_1263 class_12632 = this.inventory;
        if (class_12632 instanceof MarketDisplayBlockEntity) {
            class_3222 serverPlayer;
            MarketDisplayBlockEntity marketDisplay = (MarketDisplayBlockEntity)class_12632;
            if (!player.method_37908().method_8608() && marketDisplay.isGeneratedMarketSetup() && player instanceof class_3222 && (serverPlayer = (class_3222)player).method_5687(2)) {
                marketDisplay.updateGeneratedRestockTemplateFromCurrentStock();
            }
            marketDisplay.endEditing(player);
        }
    }

    private void addPlayerSlots(class_1661 playerInventory, int x, int y) {
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.method_7621(new class_1735((class_1263)playerInventory, column + row * 9 + 9, x + column * 18, y + row * 18));
            }
        }
        for (int column = 0; column < 9; ++column) {
            this.method_7621(new class_1735((class_1263)playerInventory, column, x + column * 18, y + 58));
        }
    }

    public static int saleIncreaseButton(int column, boolean shift) {
        return (shift ? 32 : 0) + column;
    }

    public static int saleDecreaseButton(int column, boolean shift) {
        return (shift ? 48 : 16) + column;
    }

    public static int priceIncreaseButton(int column) {
        return 64 + column;
    }

    public static int priceDecreaseButton(int column) {
        return 80 + column;
    }

    public static int selectPriceButton(int column, class_1792 item) {
        return 1000 + column * 100000 + class_7923.field_41178.method_10206((Object)item);
    }

    private static final class ResultSlot
    extends class_1735 {
        private ResultSlot(class_1263 inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean method_7680(class_1799 stack) {
            return false;
        }
    }
}

