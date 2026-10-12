/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2960
 *  net.minecraft.class_3222
 *  net.minecraft.class_7923
 */
package trep.got.economy;

import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_7923;
import trep.got.registry.GotItems;

public final class GotEconomy {
    public static final int COPPER_CROWN_VALUE = 1;
    public static final int SILVER_STAG_VALUE = 9;
    public static final int GOLDEN_DRAGON_VALUE = 81;
    public static final int LEGACY_GOLD_INGOT_VALUE = 162;
    public static final int LEGACY_GOLD_NUGGET_VALUE = 18;
    public static final int LEGACY_GOLD_BLOCK_VALUE = 1458;

    private GotEconomy() {
    }

    public static boolean isCurrency(class_1792 item) {
        return item == GotItems.COPPER_CROWN || item == GotItems.SILVER_STAG || item == GotItems.GOLDEN_DRAGON;
    }

    public static int currencyUnitValue(class_1792 item) {
        if (item == GotItems.COPPER_CROWN) {
            return 1;
        }
        if (item == GotItems.SILVER_STAG) {
            return 9;
        }
        if (item == GotItems.GOLDEN_DRAGON) {
            return 81;
        }
        return 0;
    }

    public static int currencyValue(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return 0;
        }
        return GotEconomy.currencyUnitValue(stack.method_7909()) * stack.method_7947();
    }

    public static int priceValue(class_1792 item, int amount) {
        return Math.max(0, GotEconomy.currencyUnitValue(item)) * Math.max(0, amount);
    }

    public static int legacyGoldIngotsToValue(int goldIngots) {
        return Math.max(0, goldIngots) * 162;
    }

    public static boolean hasCurrencyValue(class_1657 player, int value) {
        if (value <= 0 || player != null && player.method_7337()) {
            return true;
        }
        return GotEconomy.totalCurrencyValue(player) >= value;
    }

    public static boolean hasLegacyGoldIngots(class_1657 player, int goldIngots) {
        return GotEconomy.hasCurrencyValue(player, GotEconomy.legacyGoldIngotsToValue(goldIngots));
    }

    public static int totalCurrencyValue(class_1657 player) {
        if (player == null) {
            return 0;
        }
        int value = 0;
        for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
            value += GotEconomy.currencyValue(player.method_31548().method_5438(slot));
        }
        return value;
    }

    public static boolean payCurrencyValue(class_3222 player, int value) {
        if (value <= 0 || player != null && player.method_7337()) {
            return true;
        }
        if (player == null || !GotEconomy.hasCurrencyValue((class_1657)player, value)) {
            return false;
        }
        int[] selected = GotEconomy.selectPaymentCounts((class_1657)player, value);
        int paidValue = selected[0] * 81 + selected[1] * 9 + selected[2] * 1;
        if (paidValue < value) {
            return false;
        }
        GotEconomy.removeCurrency(player, GotItems.GOLDEN_DRAGON, selected[0]);
        GotEconomy.removeCurrency(player, GotItems.SILVER_STAG, selected[1]);
        GotEconomy.removeCurrency(player, GotItems.COPPER_CROWN, selected[2]);
        GotEconomy.giveCurrencyValue(player, paidValue - value);
        return true;
    }

    public static boolean payCurrency(class_3222 player, class_1792 item, int amount) {
        return GotEconomy.payCurrencyValue(player, GotEconomy.priceValue(item, amount));
    }

    public static boolean payLegacyGoldIngots(class_3222 player, int goldIngots) {
        return GotEconomy.payCurrencyValue(player, GotEconomy.legacyGoldIngotsToValue(goldIngots));
    }

    public static void giveLegacyGoldIngots(class_3222 player, int goldIngots) {
        GotEconomy.giveCurrencyValue(player, GotEconomy.legacyGoldIngotsToValue(goldIngots));
    }

    public static void giveCurrencyValue(class_3222 player, int value) {
        if (player == null || value <= 0) {
            return;
        }
        int dragons = value / 81;
        int stags = (value %= 81) / 9;
        GotEconomy.giveStack(player, new class_1799((class_1935)GotItems.GOLDEN_DRAGON, dragons));
        GotEconomy.giveStack(player, new class_1799((class_1935)GotItems.SILVER_STAG, stags));
        GotEconomy.giveStack(player, new class_1799((class_1935)GotItems.COPPER_CROWN, value %= 9));
    }

    public static class_1799 itemStackForLegacyGoldIngots(int goldIngots) {
        return new class_1799((class_1935)GotItems.GOLDEN_DRAGON, Math.max(1, goldIngots * 2));
    }

    public static String formatLegacyGoldIngots(int goldIngots) {
        return GotEconomy.formatItemCount(GotEconomy.itemStackForLegacyGoldIngots(goldIngots));
    }

    public static String formatItemCount(class_1792 item, int amount) {
        return GotEconomy.formatItemCount(new class_1799((class_1935)item, Math.max(1, amount)));
    }

    public static String formatItemCount(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return "0 Copper Crowns";
        }
        String name = stack.method_7964().getString();
        return stack.method_7947() + " " + name + (stack.method_7947() == 1 ? "" : "s");
    }

    public static class_1799 normalizeLegacyCurrencyStack(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return class_1799.field_8037;
        }
        int value = GotEconomy.legacyCurrencyValue(stack);
        if (value <= 0) {
            return stack;
        }
        return GotEconomy.singleCoinStackForValue(value);
    }

    public static class_1799 singleCoinStackForValue(int value) {
        if ((value = Math.max(1, value)) % 81 == 0) {
            return new class_1799((class_1935)GotItems.GOLDEN_DRAGON, Math.max(1, value / 81));
        }
        if (value % 9 == 0) {
            return new class_1799((class_1935)GotItems.SILVER_STAG, Math.max(1, value / 9));
        }
        return new class_1799((class_1935)GotItems.COPPER_CROWN, value);
    }

    public static int legacyCurrencyValue(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return 0;
        }
        class_1792 item = stack.method_7909();
        int unitValue = 0;
        if (item == class_1802.field_8397) {
            unitValue = 18;
        } else if (item == class_1802.field_8695) {
            unitValue = 162;
        } else if (item == class_1802.field_8494) {
            unitValue = 1458;
        }
        return unitValue * stack.method_7947();
    }

    public static class_2960 currencyId(class_1792 item) {
        return class_7923.field_41178.method_10221((Object)item);
    }

    private static int[] selectPaymentCounts(class_1657 player, int value) {
        int selectedCrowns;
        int dragons = GotEconomy.countItem(player, GotItems.GOLDEN_DRAGON);
        int stags = GotEconomy.countItem(player, GotItems.SILVER_STAG);
        int crowns = GotEconomy.countItem(player, GotItems.COPPER_CROWN);
        int remaining = value;
        int selectedDragons = Math.min(dragons, remaining / 81);
        int selectedStags = Math.min(stags, (remaining -= selectedDragons * 81) / 9);
        remaining -= selectedStags * 9;
        if ((remaining -= (selectedCrowns = Math.min(crowns, remaining))) <= 0 || stags > selectedStags || dragons > selectedDragons) {
            // empty if block
        }
        return new int[]{++selectedDragons, ++selectedStags, selectedCrowns};
    }

    private static int countItem(class_1657 player, class_1792 item) {
        int count = 0;
        for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
            class_1799 stack = player.method_31548().method_5438(slot);
            if (!stack.method_31574(item)) continue;
            count += stack.method_7947();
        }
        return count;
    }

    private static void removeCurrency(class_3222 player, class_1792 item, int amount) {
        int remaining = amount;
        for (int slot = 0; slot < player.method_31548().method_5439() && remaining > 0; ++slot) {
            class_1799 stack = player.method_31548().method_5438(slot);
            if (!stack.method_31574(item)) continue;
            int removed = Math.min(remaining, stack.method_7947());
            stack.method_7934(removed);
            remaining -= removed;
        }
    }

    private static void giveStack(class_3222 player, class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return;
        }
        if (!player.method_31548().method_7394(stack)) {
            player.method_7328(stack, false);
        }
    }
}

