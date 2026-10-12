/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1262
 *  net.minecraft.class_1278
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1799
 *  net.minecraft.class_1863
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2371
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2586
 *  net.minecraft.class_2680
 *  net.minecraft.class_3218
 *  net.minecraft.class_3908
 *  net.minecraft.class_3913
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_8786
 *  net.minecraft.class_9695
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block.entity;

import net.minecraft.class_1262;
import net.minecraft.class_1278;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1863;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3908;
import net.minecraft.class_3913;
import net.minecraft.class_7225;
import net.minecraft.class_8786;
import net.minecraft.class_9695;
import org.jetbrains.annotations.Nullable;
import trep.got.inventory.ImplementedInventory;
import trep.got.recipe.BeerBrewingRecipe;
import trep.got.recipe.BrewerRecipeInput;
import trep.got.registry.GotBlockEntities;
import trep.got.registry.GotRecipes;
import trep.got.screen.BrewerScreenHandler;

public final class BrewerBlockEntity
extends class_2586
implements ImplementedInventory,
class_1278,
class_3908 {
    private static final int[] INPUT_SLOTS = new int[]{0, 1, 2, 3, 4};
    private static final int[] OUTPUT_SLOT = new int[]{5};
    private static final int BREW_TIME_TOTAL = 200;
    private final class_2371<class_1799> items = class_2371.method_10213((int)6, (Object)class_1799.field_8037);
    private final class_3913 propertyDelegate = new class_3913(){

        public int method_17390(int index) {
            return switch (index) {
                case 0 -> BrewerBlockEntity.this.brewTime;
                case 1 -> 200;
                default -> 0;
            };
        }

        public void method_17391(int index, int value) {
            if (index == 0) {
                BrewerBlockEntity.this.brewTime = value;
            }
        }

        public int method_17389() {
            return 2;
        }
    };
    private int brewTime;
    private int inventoryRevision;
    private int cachedRecipeRevision = -1;
    @Nullable
    private class_1863 cachedRecipeManager;
    @Nullable
    private BeerBrewingRecipe cachedRecipe;

    public BrewerBlockEntity(class_2338 pos, class_2680 state) {
        super(GotBlockEntities.BREWER, pos, state);
    }

    @Override
    public class_2371<class_1799> getItems() {
        return this.items;
    }

    public class_3913 getPropertyDelegate() {
        return this.propertyDelegate;
    }

    public class_2561 method_5476() {
        return class_2561.method_43471((String)"container.agob.beer_brewing");
    }

    @Override
    public class_1799 method_5441(int slot) {
        class_1799 result = class_1262.method_5428(this.items, (int)slot);
        if (!result.method_7960()) {
            this.method_5431();
        }
        return result;
    }

    @Override
    public void method_5448() {
        if (!this.method_5442()) {
            this.items.clear();
            this.method_5431();
        }
    }

    public class_1703 createMenu(int syncId, class_1661 playerInventory, class_1657 player) {
        return new BrewerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void method_5431() {
        ++this.inventoryRevision;
        super.method_5431();
        if (this.field_11863 != null) {
            this.field_11863.method_8413(this.field_11867, this.method_11010(), this.method_11010(), 2);
        }
    }

    protected void method_11007(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11007(nbt, registries);
        class_1262.method_5426((class_2487)nbt, this.items, (class_7225.class_7874)registries);
        nbt.method_10569("brew_time", this.brewTime);
    }

    protected void method_11014(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11014(nbt, registries);
        class_1262.method_5429((class_2487)nbt, this.items, (class_7225.class_7874)registries);
        this.brewTime = nbt.method_10550("brew_time");
        ++this.inventoryRevision;
    }

    public int[] method_5494(class_2350 side) {
        return side == class_2350.field_11033 ? OUTPUT_SLOT : INPUT_SLOTS;
    }

    public boolean method_5492(int slot, class_1799 stack, @Nullable class_2350 dir) {
        return slot != 5;
    }

    public boolean method_5493(int slot, class_1799 stack, class_2350 dir) {
        return slot == 5;
    }

    public static void tick(class_3218 world, class_2338 pos, class_2680 state, BrewerBlockEntity blockEntity) {
        if (!blockEntity.hasCompleteInput()) {
            blockEntity.resetBrewTime();
            return;
        }
        BeerBrewingRecipe recipe = blockEntity.getCurrentRecipe(world);
        if (recipe != null && blockEntity.canCraft(recipe)) {
            ++blockEntity.brewTime;
            if (blockEntity.brewTime >= 200) {
                blockEntity.brewTime = 0;
                blockEntity.craft(recipe, (class_7225.class_7874)world.method_30349());
                blockEntity.method_5431();
                return;
            }
            blockEntity.markDirtyForSave();
            return;
        }
        blockEntity.resetBrewTime();
    }

    @Nullable
    private BeerBrewingRecipe getCurrentRecipe(class_3218 world) {
        class_1863 recipeManager = world.method_8433();
        if (this.cachedRecipeRevision != this.inventoryRevision || this.cachedRecipeManager != recipeManager) {
            this.cachedRecipe = recipeManager.method_8132(GotRecipes.BEER_BREWING_TYPE, (class_9695)this.currentInput(), (class_1937)world).map(class_8786::comp_1933).orElse(null);
            this.cachedRecipeRevision = this.inventoryRevision;
            this.cachedRecipeManager = recipeManager;
        }
        return this.cachedRecipe;
    }

    private boolean hasCompleteInput() {
        for (int slot = 0; slot < 5; ++slot) {
            if (!((class_1799)this.items.get(slot)).method_7960()) continue;
            return false;
        }
        return true;
    }

    private BrewerRecipeInput currentInput() {
        class_1799[] input = new class_1799[5];
        for (int slot = 0; slot < 5; ++slot) {
            input[slot] = (class_1799)this.items.get(slot);
        }
        return new BrewerRecipeInput(input);
    }

    private boolean canCraft(BeerBrewingRecipe recipe) {
        class_1799 output = (class_1799)this.items.get(5);
        class_1799 crafted = recipe.result();
        if (crafted.method_7960()) {
            return false;
        }
        if (output.method_7960()) {
            return true;
        }
        if (!class_1799.method_31577((class_1799)output, (class_1799)crafted)) {
            return false;
        }
        return output.method_7947() + crafted.method_7947() <= output.method_7914();
    }

    private void craft(BeerBrewingRecipe recipe, class_7225.class_7874 registries) {
        if (!this.canCraft(recipe)) {
            return;
        }
        class_1799 crafted = recipe.craft(this.currentInput(), registries);
        class_1799 output = (class_1799)this.items.get(5);
        if (output.method_7960()) {
            this.items.set(5, (Object)crafted);
        } else {
            output.method_7933(crafted.method_7947());
        }
        for (int slot = 0; slot < 5; ++slot) {
            ((class_1799)this.items.get(slot)).method_7934(1);
        }
    }

    private void resetBrewTime() {
        if (this.brewTime != 0) {
            this.brewTime = 0;
            this.markDirtyForSave();
        }
    }

    private void markDirtyForSave() {
        super.method_5431();
    }
}

