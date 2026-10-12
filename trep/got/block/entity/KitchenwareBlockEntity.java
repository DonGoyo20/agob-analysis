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
 *  net.minecraft.class_1802
 *  net.minecraft.class_1863
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2371
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2561
 *  net.minecraft.class_2586
 *  net.minecraft.class_2596
 *  net.minecraft.class_2602
 *  net.minecraft.class_2622
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_3218
 *  net.minecraft.class_3908
 *  net.minecraft.class_3913
 *  net.minecraft.class_3920
 *  net.minecraft.class_3956
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_8786
 *  net.minecraft.class_9334
 *  net.minecraft.class_9695
 *  net.minecraft.class_9696
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1262;
import net.minecraft.class_1278;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1863;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3908;
import net.minecraft.class_3913;
import net.minecraft.class_3920;
import net.minecraft.class_3956;
import net.minecraft.class_7225;
import net.minecraft.class_8786;
import net.minecraft.class_9334;
import net.minecraft.class_9695;
import net.minecraft.class_9696;
import org.jetbrains.annotations.Nullable;
import trep.got.block.PotBlock;
import trep.got.inventory.ImplementedInventory;
import trep.got.recipe.PotCookingRecipe;
import trep.got.recipe.PotCookingRecipeInput;
import trep.got.registry.GotBlockEntities;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotRecipes;
import trep.got.screen.PotCookingScreenHandler;

public final class KitchenwareBlockEntity
extends class_2586
implements ImplementedInventory,
class_1278,
class_3908 {
    public static final int INVENTORY_SIZE = 6;
    public static final int CONTAINER_SLOT = 4;
    public static final int OUTPUT_SLOT = 5;
    private static final int[] NO_AVAILABLE_SLOTS = new int[0];
    private static final int[] POT_INSERT_SLOTS = new int[]{0, 1, 2, 3, 4};
    private static final int[] POT_OUTPUT_SLOTS = new int[]{5};
    private final class_2371<class_1799> items = class_2371.method_10213((int)6, (Object)class_1799.field_8037);
    private final class_3913 propertyDelegate = new class_3913(){

        public int method_17390(int index) {
            return switch (index) {
                case 0 -> KitchenwareBlockEntity.this.cookTime;
                case 1 -> KitchenwareBlockEntity.this.cookTimeTotal;
                default -> 0;
            };
        }

        public void method_17391(int index, int value) {
            if (index == 0) {
                KitchenwareBlockEntity.this.cookTime = value;
            } else if (index == 1) {
                KitchenwareBlockEntity.this.cookTimeTotal = value;
            }
        }

        public int method_17389() {
            return 2;
        }
    };
    private int cookTime;
    private int cookTimeTotal = 200;
    private final int[] panCookTime = new int[4];
    private final class_8786<?>[] cachedPanRecipes = new class_8786[4];
    private int inventoryRevision;
    private int cachedPotRecipeRevision = -1;
    private int cachedPanRecipeRevision = -1;
    @Nullable
    private class_1863 cachedPotRecipeManager;
    @Nullable
    private class_1863 cachedPanRecipeManager;
    @Nullable
    private PotCookingRecipe cachedPotRecipe;

    public KitchenwareBlockEntity(class_2338 pos, class_2680 state) {
        super(GotBlockEntities.KITCHENWARE, pos, state);
    }

    @Override
    public class_2371<class_1799> getItems() {
        return this.items;
    }

    public class_3913 getPropertyDelegate() {
        return this.propertyDelegate;
    }

    public List<class_1799> getIngredients() {
        int max = this.method_11010().method_27852(GotBlocks.POT) ? 4 : this.displayCapacity();
        return this.items.stream().limit(max).filter(stack -> !stack.method_7960()).map(class_1799::method_7972).toList();
    }

    public List<class_1799> getDisplayStacks() {
        int max = this.method_11010().method_27852(GotBlocks.POT) ? 4 : this.displayCapacity();
        ArrayList<class_1799> stacks = new ArrayList<class_1799>(max);
        for (int slot = 0; slot < max; ++slot) {
            stacks.add(((class_1799)this.items.get(slot)).method_7972());
        }
        return List.copyOf(stacks);
    }

    public boolean canAddIngredient(int capacity) {
        return this.firstEmptyDisplaySlot(capacity) >= 0;
    }

    public void addIngredient(class_1799 stack, int capacity) {
        if (stack.method_7960()) {
            return;
        }
        int slot = this.firstEmptyDisplaySlot(capacity);
        if (slot < 0) {
            return;
        }
        this.items.set(slot, (Object)stack.method_46651(1));
        this.sync();
    }

    public class_1799 takeLastIngredient() {
        for (int slot = this.displayCapacity() - 1; slot >= 0; --slot) {
            class_1799 stack = (class_1799)this.items.get(slot);
            if (stack.method_7960()) continue;
            this.items.set(slot, (Object)class_1799.field_8037);
            this.sync();
            return stack.method_46651(1);
        }
        return class_1799.field_8037;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean canAcceptIngredient(class_2680 state, class_1799 stack) {
        if (!KitchenwareBlockEntity.isPanOnStove(state, this.field_11863, this.field_11867)) {
            return true;
        }
        class_1937 class_19372 = this.field_11863;
        if (!(class_19372 instanceof class_3218)) return true;
        class_3218 serverWorld = (class_3218)class_19372;
        if (stack.method_57824(class_9334.field_50075) == null) return false;
        if (KitchenwareBlockEntity.getCampfireRecipe(serverWorld, stack) == null) return false;
        return true;
    }

    public class_2561 method_5476() {
        return class_2561.method_43471((String)"container.agob.pot_cooking");
    }

    @Nullable
    public class_1703 createMenu(int syncId, class_1661 playerInventory, class_1657 player) {
        if (!this.method_11010().method_27852(GotBlocks.POT)) {
            return null;
        }
        return new PotCookingScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public int[] method_5494(class_2350 side) {
        if (!this.method_11010().method_27852(GotBlocks.POT)) {
            return NO_AVAILABLE_SLOTS;
        }
        return side == class_2350.field_11033 ? POT_OUTPUT_SLOTS : POT_INSERT_SLOTS;
    }

    public boolean method_5492(int slot, class_1799 stack, @Nullable class_2350 dir) {
        if (!this.method_11010().method_27852(GotBlocks.POT)) {
            return false;
        }
        if (slot == 4) {
            return stack.method_31574(class_1802.field_8428);
        }
        return slot >= 0 && slot < 4 && stack.method_57824(class_9334.field_50075) != null;
    }

    public boolean method_5493(int slot, class_1799 stack, class_2350 dir) {
        return this.method_11010().method_27852(GotBlocks.POT) && slot == 5;
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
            KitchenwareBlockEntity.clearItems(this.items);
            this.method_5431();
        }
    }

    public static void tick(class_1937 world, class_2338 pos, class_2680 state, KitchenwareBlockEntity blockEntity) {
        if (!(world instanceof class_3218)) {
            return;
        }
        class_3218 serverWorld = (class_3218)world;
        if (state.method_27852(GotBlocks.PAN)) {
            KitchenwareBlockEntity.tickPan(serverWorld, pos, state, blockEntity);
            return;
        }
        if (state.method_27852(GotBlocks.POT)) {
            KitchenwareBlockEntity.tickPot(serverWorld, pos, state, blockEntity);
        }
    }

    private static void tickPan(class_3218 world, class_2338 pos, class_2680 state, KitchenwareBlockEntity blockEntity) {
        if (!KitchenwareBlockEntity.isPanOnStove(state, (class_1937)world, pos)) {
            blockEntity.clearPanCookingProgress();
            return;
        }
        boolean changed = false;
        boolean dirty = false;
        blockEntity.refreshPanRecipeCache(world);
        for (int index = 0; index < 4; ++index) {
            class_1799 stack = (class_1799)blockEntity.items.get(index);
            class_8786<class_3920> recipe = blockEntity.getCachedPanRecipe(index);
            if (recipe == null) {
                if (blockEntity.panCookTime[index] == 0) continue;
                blockEntity.panCookTime[index] = 0;
                dirty = true;
                continue;
            }
            int n = index;
            blockEntity.panCookTime[n] = blockEntity.panCookTime[n] + 1;
            if (blockEntity.panCookTime[index] >= ((class_3920)recipe.comp_1933()).method_8167()) {
                class_1799 cooked = ((class_3920)recipe.comp_1933()).method_59982(new class_9696(stack), (class_7225.class_7874)world.method_30349());
                blockEntity.items.set(index, (Object)cooked.method_46651(1));
                blockEntity.panCookTime[index] = 0;
                changed = true;
                continue;
            }
            dirty = true;
        }
        if (changed) {
            blockEntity.sync();
        } else if (dirty) {
            blockEntity.markDirtyForSave();
        }
    }

    private static void tickPot(class_3218 world, class_2338 pos, class_2680 state, KitchenwareBlockEntity blockEntity) {
        if (!KitchenwareBlockEntity.isPotOnStove(state, (class_1937)world, pos) || !blockEntity.hasCompletePotInput()) {
            blockEntity.resetPotProgress();
            KitchenwareBlockEntity.setPotActive(world, pos, state, false);
            return;
        }
        PotCookingRecipe recipe = blockEntity.getCurrentPotRecipe(world);
        if (recipe != null && blockEntity.canCraft(recipe)) {
            blockEntity.cookTimeTotal = recipe.cookingTime();
            ++blockEntity.cookTime;
            KitchenwareBlockEntity.setPotActive(world, pos, state, true);
            if (blockEntity.cookTime >= blockEntity.cookTimeTotal) {
                blockEntity.cookTime = 0;
                blockEntity.craft(recipe, (class_7225.class_7874)world.method_30349());
                KitchenwareBlockEntity.setPotActive(world, pos, world.method_8320(pos), false);
                blockEntity.sync();
                return;
            }
            blockEntity.markDirtyForSave();
            return;
        }
        blockEntity.resetPotProgress();
        KitchenwareBlockEntity.setPotActive(world, pos, state, false);
    }

    @Nullable
    private PotCookingRecipe getCurrentPotRecipe(class_3218 world) {
        class_1863 recipeManager = world.method_8433();
        if (this.cachedPotRecipeRevision != this.inventoryRevision || this.cachedPotRecipeManager != recipeManager) {
            this.cachedPotRecipe = recipeManager.method_8132(GotRecipes.POT_COOKING_TYPE, (class_9695)this.currentPotInput(), (class_1937)world).map(class_8786::comp_1933).orElse(null);
            this.cachedPotRecipeRevision = this.inventoryRevision;
            this.cachedPotRecipeManager = recipeManager;
        }
        return this.cachedPotRecipe;
    }

    private boolean hasCompletePotInput() {
        for (int slot = 0; slot < 5; ++slot) {
            if (!((class_1799)this.items.get(slot)).method_7960()) continue;
            return false;
        }
        return true;
    }

    private PotCookingRecipeInput currentPotInput() {
        class_1799[] stacks = new class_1799[5];
        for (int slot = 0; slot < 5; ++slot) {
            stacks[slot] = (class_1799)this.items.get(slot);
        }
        return new PotCookingRecipeInput(stacks);
    }

    private boolean canCraft(PotCookingRecipe recipe) {
        class_1799 crafted = recipe.result();
        if (crafted.method_7960()) {
            return false;
        }
        class_1799 output = (class_1799)this.items.get(5);
        if (output.method_7960()) {
            return true;
        }
        if (!class_1799.method_31577((class_1799)output, (class_1799)crafted)) {
            return false;
        }
        return output.method_7947() + crafted.method_7947() <= output.method_7914();
    }

    private void craft(PotCookingRecipe recipe, class_7225.class_7874 registries) {
        if (!this.canCraft(recipe)) {
            return;
        }
        class_1799 crafted = recipe.craft(this.currentPotInput(), registries);
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

    public void method_5431() {
        class_1937 class_19372;
        ++this.inventoryRevision;
        super.method_5431();
        if (this.field_11863 != null) {
            this.field_11863.method_8413(this.field_11867, this.method_11010(), this.method_11010(), 3);
        }
        if ((class_19372 = this.field_11863) instanceof class_3218) {
            class_3218 serverWorld = (class_3218)class_19372;
            serverWorld.method_14178().method_14128(this.field_11867);
        }
    }

    protected void method_11007(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11007(nbt, registries);
        class_1262.method_5426((class_2487)nbt, this.items, (class_7225.class_7874)registries);
        nbt.method_10569("cook_time", this.cookTime);
        nbt.method_10569("cook_time_total", this.cookTimeTotal);
        nbt.method_10539("pan_cook_time", this.panCookTime);
    }

    protected void method_11014(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11014(nbt, registries);
        KitchenwareBlockEntity.clearItems(this.items);
        this.clearPanCookingProgressValues();
        class_1262.method_5429((class_2487)nbt, this.items, (class_7225.class_7874)registries);
        if (nbt.method_10545("ingredients")) {
            this.readLegacyIngredients(nbt, registries);
        }
        this.cookTime = nbt.method_10550("cook_time");
        this.cookTimeTotal = nbt.method_10545("cook_time_total") ? nbt.method_10550("cook_time_total") : 200;
        int[] panTimes = nbt.method_10561("pan_cook_time");
        for (int index = 0; index < this.panCookTime.length && index < panTimes.length; ++index) {
            this.panCookTime[index] = panTimes[index];
        }
        ++this.inventoryRevision;
    }

    public class_2487 method_16887(class_7225.class_7874 registries) {
        return this.method_38244(registries);
    }

    @Nullable
    public class_2596<class_2602> method_38235() {
        return class_2622.method_38585((class_2586)this);
    }

    private int displayCapacity() {
        class_2680 state = this.method_11010();
        if (state.method_27852(GotBlocks.POT) || state.method_27852(GotBlocks.PAN)) {
            return 4;
        }
        return 1;
    }

    private int firstEmptyDisplaySlot(int capacity) {
        int max = Math.min(capacity, 4);
        for (int slot = 0; slot < max; ++slot) {
            if (!((class_1799)this.items.get(slot)).method_7960()) continue;
            return slot;
        }
        return -1;
    }

    private void sync() {
        this.method_5431();
    }

    private void markDirtyForSave() {
        super.method_5431();
    }

    private void clearPanCookingProgress() {
        boolean changed = false;
        for (int index = 0; index < this.panCookTime.length; ++index) {
            if (this.panCookTime[index] == 0) continue;
            this.panCookTime[index] = 0;
            changed = true;
        }
        if (changed) {
            this.markDirtyForSave();
        }
    }

    private void clearPanCookingProgressValues() {
        for (int index = 0; index < this.panCookTime.length; ++index) {
            this.panCookTime[index] = 0;
        }
    }

    private void resetPotProgress() {
        if (this.cookTime != 0) {
            this.cookTime = 0;
            this.markDirtyForSave();
        }
    }

    private void refreshPanRecipeCache(class_3218 world) {
        class_1863 recipeManager = world.method_8433();
        if (this.cachedPanRecipeRevision == this.inventoryRevision && this.cachedPanRecipeManager == recipeManager) {
            return;
        }
        for (int slot = 0; slot < 4; ++slot) {
            this.cachedPanRecipes[slot] = KitchenwareBlockEntity.getCampfireRecipe(world, (class_1799)this.items.get(slot));
        }
        this.cachedPanRecipeRevision = this.inventoryRevision;
        this.cachedPanRecipeManager = recipeManager;
    }

    @Nullable
    private class_8786<class_3920> getCachedPanRecipe(int slot) {
        return this.cachedPanRecipes[slot];
    }

    private static void clearItems(class_2371<class_1799> items) {
        for (int index = 0; index < items.size(); ++index) {
            items.set(index, (Object)class_1799.field_8037);
        }
    }

    private static boolean isPanOnStove(class_2680 state, @Nullable class_1937 world, class_2338 pos) {
        return world != null && state.method_27852(GotBlocks.PAN) && world.method_8320(pos.method_10074()).method_27852(GotBlocks.STOVE);
    }

    private static boolean isPotOnStove(class_2680 state, class_1937 world, class_2338 pos) {
        return state.method_27852(GotBlocks.POT) && world.method_8320(pos.method_10074()).method_27852(GotBlocks.STOVE);
    }

    @Nullable
    private static class_8786<class_3920> getCampfireRecipe(class_3218 world, class_1799 stack) {
        if (stack.method_7960() || stack.method_57824(class_9334.field_50075) == null) {
            return null;
        }
        return world.method_8433().method_8132(class_3956.field_17549, (class_9695)new class_9696(stack), (class_1937)world).orElse(null);
    }

    private static void setPotActive(class_3218 world, class_2338 pos, class_2680 state, boolean active) {
        if (state.method_27852(GotBlocks.POT) && state.method_28498((class_2769)PotBlock.ACTIVE) && (Boolean)state.method_11654((class_2769)PotBlock.ACTIVE) != active) {
            world.method_8652(pos, (class_2680)state.method_11657((class_2769)PotBlock.ACTIVE, (Comparable)Boolean.valueOf(active)), 3);
        }
    }

    private void readLegacyIngredients(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 list = nbt.method_10554("ingredients", 10);
        for (int index = 0; index < list.size() && index < 4; ++index) {
            class_1799 stack = class_1799.method_57359((class_7225.class_7874)registries, (class_2487)list.method_10602(index));
            if (stack.method_7960()) continue;
            this.items.set(index, (Object)stack.method_46651(1));
        }
    }
}

