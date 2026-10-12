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

public final class AgingBarrelScreenHandler
extends class_1703 {
    private final class_1263 inventory;

    public AgingBarrelScreenHandler(int syncId, class_1661 playerInventory) {
        this(syncId, playerInventory, (class_1263)new class_1277(27), (class_3913)new class_3919(1));
    }

    public AgingBarrelScreenHandler(int syncId, class_1661 playerInventory, class_1263 inventory, class_3913 propertyDelegate) {
        super(GotScreenHandlers.AGING_BARREL, syncId);
        AgingBarrelScreenHandler.method_17359((class_1263)inventory, (int)27);
        AgingBarrelScreenHandler.method_17361((class_3913)propertyDelegate, (int)1);
        this.inventory = inventory;
        inventory.method_5435(playerInventory.field_7546);
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.method_7621(new class_1735(inventory, col + row * 9, 8 + col * 18, 18 + row * 18));
            }
        }
        this.addPlayerSlots(playerInventory, 8, 84);
        this.method_17360(propertyDelegate);
    }

    public class_1799 method_7601(class_1657 player, int slot) {
        class_1799 newStack = class_1799.field_8037;
        class_1735 source = (class_1735)this.field_7761.get(slot);
        if (source == null || !source.method_7681()) {
            return class_1799.field_8037;
        }
        class_1799 sourceStack = source.method_7677();
        newStack = sourceStack.method_7972();
        if (slot < 27 ? !this.method_7616(sourceStack, 27, this.field_7761.size(), true) : !this.method_7616(sourceStack, 0, 27, false)) {
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

