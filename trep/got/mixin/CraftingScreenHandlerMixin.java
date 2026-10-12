/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1703
 *  net.minecraft.class_1714
 *  net.minecraft.class_1731
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1937
 *  net.minecraft.class_3955
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_8566
 *  net.minecraft.class_8786
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin;

import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1714;
import net.minecraft.class_1731;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_3955;
import net.minecraft.class_7225;
import net.minecraft.class_8566;
import net.minecraft.class_8786;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.recipe.RuneCraftingContext;
import trep.got.recipe.RuneCraftingRecipe;
import trep.got.world.dimension.GotDimensionKeys;

@Mixin(value={class_1714.class})
abstract class CraftingScreenHandlerMixin {
    CraftingScreenHandlerMixin() {
    }

    @Inject(method={"method_17399"}, at={@At(value="HEAD")})
    private static void got$setRuneCraftingPlayer(class_1703 handler, class_1937 world, class_1657 player, class_8566 craftingInventory, class_1731 resultInventory, class_8786<class_3955> recipe, CallbackInfo ci) {
        RuneCraftingContext.setCurrentPlayer(player);
        RuneCraftingContext.setCurrentAltarCrafting(RuneCraftingContext.isAltarHandler(handler));
    }

    @Inject(method={"method_17399"}, at={@At(value="RETURN")})
    private static void got$clearRuneCraftingPlayer(class_1703 handler, class_1937 world, class_1657 player, class_8566 craftingInventory, class_1731 resultInventory, class_8786<class_3955> recipe, CallbackInfo ci) {
        class_1799 result;
        if (RuneCraftingContext.isCurrentAltarCrafting()) {
            class_1799 altarResult = RuneCraftingRecipe.craftForAltar(craftingInventory, (class_7225.class_7874)world.method_30349(), RuneCraftingContext.getCurrentReligionId());
            resultInventory.method_5447(0, altarResult);
        }
        if (world.method_27983().equals(GotDimensionKeys.ICEANDFIRE) && (result = resultInventory.method_5438(0)).method_31574(class_1802.field_8657)) {
            resultInventory.method_5447(0, class_1799.field_8037);
        }
        RuneCraftingContext.clear();
    }
}

