/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1263
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1714
 *  net.minecraft.class_2248
 *  net.minecraft.class_3914
 */
package trep.got.screen;

import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1714;
import net.minecraft.class_2248;
import net.minecraft.class_3914;
import trep.got.recipe.RuneCraftingContext;
import trep.got.registry.GotBlocks;

public final class AltarCraftingScreenHandler
extends class_1714 {
    private final class_3914 altarContext;
    private final class_1657 owner;

    public AltarCraftingScreenHandler(int syncId, class_1661 playerInventory, class_3914 context) {
        super(syncId, playerInventory, context);
        this.altarContext = context;
        this.owner = playerInventory.field_7546;
    }

    public boolean method_7597(class_1657 player) {
        return AltarCraftingScreenHandler.method_17695((class_3914)this.altarContext, (class_1657)player, (class_2248)GotBlocks.ALTAR);
    }

    public void method_7609(class_1263 inventory) {
        RuneCraftingContext.setCurrentPlayer(this.owner);
        RuneCraftingContext.setCurrentAltarCrafting(true);
        try {
            super.method_7609(inventory);
        }
        finally {
            RuneCraftingContext.clear();
        }
    }
}

