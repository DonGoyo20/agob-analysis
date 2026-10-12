/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_3222
 */
package trep.got.item;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_3222;

public final class InventoryItemHelper {
    private InventoryItemHelper() {
    }

    public static boolean hasItemCount(class_3222 player, class_1792 item, int amount) {
        int count = 0;
        for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
            class_1799 stack = player.method_31548().method_5438(slot);
            if (!stack.method_31574(item) || (count += stack.method_7947()) < amount) continue;
            return true;
        }
        return false;
    }

    public static void removeItems(class_3222 player, class_1792 item, int amount) {
        int remaining = amount;
        for (int slot = 0; slot < player.method_31548().method_5439() && remaining > 0; ++slot) {
            class_1799 stack = player.method_31548().method_5438(slot);
            if (!stack.method_31574(item)) continue;
            int removed = Math.min(remaining, stack.method_7947());
            stack.method_7934(removed);
            remaining -= removed;
        }
    }
}

