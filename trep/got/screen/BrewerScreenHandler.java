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
 *  net.minecraft.class_1799
 *  net.minecraft.class_3913
 *  net.minecraft.class_3919
 */
package trep.got.screen;

import net.minecraft.class_1263;
import net.minecraft.class_1277;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_3913;
import net.minecraft.class_3919;
import trep.got.registry.GotScreenHandlers;

public final class BrewerScreenHandler
extends class_1703 {
    private final class_1263 inventory;
    private final class_3913 propertyDelegate;

    public BrewerScreenHandler(int syncId, class_1661 playerInventory) {
        this(syncId, playerInventory, (class_1263)new class_1277(6), (class_3913)new class_3919(2));
    }

    public BrewerScreenHandler(int syncId, class_1661 playerInventory, class_1263 inventory, class_3913 propertyDelegate) {
        super(GotScreenHandlers.BREWER, syncId);
        BrewerScreenHandler.method_17359((class_1263)inventory, (int)6);
        BrewerScreenHandler.method_17361((class_3913)propertyDelegate, (int)2);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        inventory.method_5435(playerInventory.field_7546);
        this.method_7621(new class_1735(inventory, 0, 28, 26));
        this.method_7621(new class_1735(inventory, 1, 46, 26));
        this.method_7621(new class_1735(inventory, 2, 28, 44));
        this.method_7621(new class_1735(inventory, 3, 46, 44));
        this.method_7621(new class_1735(inventory, 4, 74, 50));
        this.method_7621(new class_1735(this, inventory, 5, 129, 34){

            public boolean method_7680(class_1799 stack) {
                return false;
            }
        });
        this.addPlayerSlots(playerInventory, 8, 83);
        this.method_17360(propertyDelegate);
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.method_17390(0);
        int total = this.propertyDelegate.method_17390(1);
        return total > 0 && progress > 0 ? progress * 24 / total : 0;
    }

    public class_1799 method_7601(class_1657 player, int slot) {
        class_1799 newStack = class_1799.field_8037;
        class_1735 source = (class_1735)this.field_7761.get(slot);
        if (source == null || !source.method_7681()) {
            return class_1799.field_8037;
        }
        class_1799 sourceStack = source.method_7677();
        newStack = sourceStack.method_7972();
        if (slot < 6 ? !this.method_7616(sourceStack, 6, this.field_7761.size(), true) : !this.method_7616(sourceStack, 0, 5, false)) {
            return class_1799.field_8037;
        }
        if (sourceStack.method_7960()) {
            source.method_53512(class_1799.field_8037);
        } else {
            source.method_7668();
        }
        return newStack;
    }

    public boolean method_7597(class_1657 player) {
        return this.inventory.method_5443(player);
    }

    public void method_7595(class_1657 player) {
        super.method_7595(player);
        this.inventory.method_5432(player);
    }

    private void addPlayerSlots(class_1661 playerInventory, int x, int y) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.method_7621(new class_1735((class_1263)playerInventory, col + row * 9 + 9, x + col * 18, y + row * 18));
            }
        }
        for (int hotbarSlot = 0; hotbarSlot < 9; ++hotbarSlot) {
            this.method_7621(new class_1735((class_1263)playerInventory, hotbarSlot, x + hotbarSlot * 18, y + 58));
        }
    }
}

