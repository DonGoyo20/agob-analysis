/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_2248
 */
package trep.got.item;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2248;
import trep.got.item.BrewItem;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotItems;

public final class CupLiquidColors {
    private CupLiquidColors() {
    }

    public static boolean isFilledCup(class_1799 stack) {
        return stack.method_7909() instanceof BrewItem;
    }

    public static class_2248 blockFor(class_1799 stack) {
        BrewItem brew;
        class_1792 item = stack.method_7909();
        if (item == GotItems.EMPTY_SILVER_CUP) {
            return GotBlocks.SILVER_CUP_BLOCK;
        }
        if (item instanceof BrewItem && (brew = (BrewItem)item).getEmptyCupItem() == GotItems.EMPTY_SILVER_CUP) {
            return GotBlocks.SILVER_CUP_BLOCK;
        }
        return GotBlocks.CUP_BLOCK;
    }

    public static int colorFor(class_1799 stack) {
        class_1792 item = stack.method_7909();
        if (item == GotItems.BEER) {
            return 13142575;
        }
        if (item == GotItems.APPLE_JUICE) {
            return 15325050;
        }
        if (item == GotItems.VODKA) {
            return 14283263;
        }
        if (item == GotItems.NETHER_WART_STOUT) {
            return 4989976;
        }
        if (item == GotItems.CHORUS_FRUIT_CIDER) {
            return 10842082;
        }
        if (item == GotItems.MELON_MEAD) {
            return 14137438;
        }
        if (item == GotItems.CARROT_LAGER) {
            return 14060331;
        }
        if (item == GotItems.BEETROOT_BREW) {
            return 10820909;
        }
        if (item == GotItems.PUMPKIN_ALE) {
            return 12085791;
        }
        if (item == GotItems.SWEET_BERRY_WINE) {
            return 7217998;
        }
        if (item == GotItems.KELP_KOMBUCHA) {
            return 5017195;
        }
        if (item == GotItems.GLOW_BERRY_GOSE) {
            return 15777365;
        }
        if (item == GotItems.RED_WINE_SILVER_CUP) {
            return 7214626;
        }
        if (item == GotItems.WHITE_WINE_SILVER_CUP) {
            return 15128208;
        }
        if (item == GotItems.SPOILED_BREW) {
            return 6973505;
        }
        return 8375295;
    }
}

