/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_2561
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3908
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.blacksmith;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3908;
import org.jetbrains.annotations.Nullable;
import trep.got.blacksmith.BlacksmithCrafting;
import trep.got.blacksmith.BlacksmithGroupDefinition;
import trep.got.blacksmith.BlacksmithGroupRegistry;
import trep.got.blacksmith.BlacksmithKingdomUnlocks;
import trep.got.blacksmith.BlacksmithRecipe;
import trep.got.blacksmith.BlacksmithRecipeDisplay;
import trep.got.blacksmith.BlacksmithRecipeIngredient;
import trep.got.blacksmith.BlacksmithRecipeSorting;
import trep.got.blacksmith.BlacksmithScreenHandler;
import trep.got.blacksmith.BlacksmithScreenOpeningData;
import trep.got.block.AbstractMultiBlockMachineBlock;
import trep.got.block.BlacksmithFurnaceBlock;
import trep.got.block.BlacksmithTableBlock;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotMainQuests;
import trep.got.registry.GotRecipes;

public final class BlacksmithWorkbenchAccess {
    private BlacksmithWorkbenchAccess() {
    }

    public static class_1269 tryOpen(class_1937 world, class_2338 pos, class_2680 state, class_1657 player) {
        class_3218 serverWorld;
        block7: {
            block6: {
                if (world.field_9236) {
                    return class_1269.field_5812;
                }
                if (!(world instanceof class_3218)) break block6;
                serverWorld = (class_3218)world;
                if (player instanceof class_3222) break block7;
            }
            return class_1269.field_5811;
        }
        class_3222 serverPlayer = (class_3222)player;
        WorkbenchPair pair = BlacksmithWorkbenchAccess.findPair((class_1937)serverWorld, pos, state);
        if (pair == null) {
            return class_1269.field_5811;
        }
        if (BlacksmithCrafting.isStationActive(serverWorld, pair)) {
            return class_1269.field_21466;
        }
        BlacksmithWorkbenchAccess.openWorkbenchScreen(serverPlayer, serverWorld, pair);
        return class_1269.field_21466;
    }

    public static void openWorkbenchScreen(class_3222 player, class_3218 world, WorkbenchPair pair) {
        player.method_17355((class_3908)new Factory(world, pair));
        GotMainQuests.onBlacksmithScreenOpened(player);
    }

    public static void cancelActiveSession(class_1937 world, class_2338 pos, class_2680 state) {
        if (!(world instanceof class_3218)) {
            return;
        }
        class_3218 serverWorld = (class_3218)world;
        WorkbenchPair pair = BlacksmithWorkbenchAccess.findPair((class_1937)serverWorld, pos, state);
        if (pair != null) {
            BlacksmithCrafting.cancelSession(serverWorld, pair);
        }
    }

    @Nullable
    public static WorkbenchPair findPair(class_1937 world, class_2338 pos, class_2680 state) {
        class_2338 tableOrigin;
        class_2248 class_22482 = state.method_26204();
        if (!(class_22482 instanceof AbstractMultiBlockMachineBlock)) {
            return null;
        }
        AbstractMultiBlockMachineBlock machineBlock = (AbstractMultiBlockMachineBlock)class_22482;
        class_2338 origin = machineBlock.getOriginForScreen(pos, state);
        class_2680 originState = world.method_8320(origin);
        if (!originState.method_27852(state.method_26204())) {
            return null;
        }
        boolean furnaceClicked = state.method_26204() instanceof BlacksmithFurnaceBlock;
        class_2248 furnaceBlock = furnaceClicked ? state.method_26204() : GotBlocks.BLACKSMITH_FURNACE;
        class_2248 tableBlock = furnaceClicked ? GotBlocks.BLACKSMITH_TABLE : state.method_26204();
        class_2338 furnaceOrigin = furnaceClicked ? origin : BlacksmithWorkbenchAccess.findAttachedOrigin(world, origin, furnaceBlock, tableBlock, false);
        class_2338 class_23382 = tableOrigin = furnaceClicked ? BlacksmithWorkbenchAccess.findAttachedOrigin(world, origin, tableBlock, furnaceBlock, true) : origin;
        if (furnaceOrigin == null || tableOrigin == null) {
            return null;
        }
        return BlacksmithWorkbenchAccess.areAttached(world, furnaceOrigin, tableOrigin) ? new WorkbenchPair(furnaceOrigin, tableOrigin) : null;
    }

    public static boolean isStillValid(class_1937 world, WorkbenchPair pair, class_1657 player) {
        double centerZ;
        double centerY;
        if (!(world instanceof class_3218) || player.method_31481()) {
            return false;
        }
        class_2680 furnaceState = world.method_8320(pair.furnaceOrigin());
        class_2680 tableState = world.method_8320(pair.tableOrigin());
        if (!(furnaceState.method_26204() instanceof BlacksmithFurnaceBlock) || !(tableState.method_26204() instanceof BlacksmithTableBlock)) {
            return false;
        }
        if (!BlacksmithWorkbenchAccess.areAttached(world, pair.furnaceOrigin(), pair.tableOrigin())) {
            return false;
        }
        double centerX = (double)(pair.furnaceOrigin().method_10263() + pair.tableOrigin().method_10263()) * 0.5 + 0.5;
        return player.method_5649(centerX, centerY = (double)Math.min(pair.furnaceOrigin().method_10264(), pair.tableOrigin().method_10264()) + 0.5, centerZ = (double)(pair.furnaceOrigin().method_10260() + pair.tableOrigin().method_10260()) * 0.5 + 0.5) <= 64.0;
    }

    @Nullable
    private static class_2338 findAttachedOrigin(class_1937 world, class_2338 primaryOrigin, class_2248 targetBlock, class_2248 primaryBlock, boolean primaryIsFurnace) {
        for (class_2338 searchPos : class_2338.method_10097((class_2338)primaryOrigin.method_10069(-3, -1, -3), (class_2338)primaryOrigin.method_10069(3, 2, 3))) {
            class_2338 tableOrigin;
            class_2338 furnaceOrigin;
            AbstractMultiBlockMachineBlock machineBlock;
            class_2338 candidateOrigin;
            class_2248 class_22482;
            class_2680 candidateState = world.method_8320(searchPos);
            if (!candidateState.method_27852(targetBlock) || !((class_22482 = candidateState.method_26204()) instanceof AbstractMultiBlockMachineBlock) || !world.method_8320(candidateOrigin = (machineBlock = (AbstractMultiBlockMachineBlock)class_22482).getOriginForScreen(searchPos, candidateState)).method_27852(targetBlock) || !BlacksmithWorkbenchAccess.areAttached(world, furnaceOrigin = primaryIsFurnace ? primaryOrigin : candidateOrigin, tableOrigin = primaryIsFurnace ? candidateOrigin : primaryOrigin)) continue;
            return candidateOrigin;
        }
        return null;
    }

    private static boolean areAttached(class_1937 world, class_2338 furnaceOrigin, class_2338 tableOrigin) {
        List<class_2338> furnaceParts = BlacksmithWorkbenchAccess.collectParts(world, furnaceOrigin, 2, 2, 2, GotBlocks.BLACKSMITH_FURNACE);
        List<class_2338> tableParts = BlacksmithWorkbenchAccess.collectParts(world, tableOrigin, 2, 2, 1, GotBlocks.BLACKSMITH_TABLE);
        if (furnaceParts.isEmpty() || tableParts.isEmpty()) {
            return false;
        }
        for (class_2338 furnacePos : furnaceParts) {
            for (class_2338 tablePos : tableParts) {
                if (furnacePos.method_19455((class_2382)tablePos) != 1) continue;
                return true;
            }
        }
        return false;
    }

    private static List<class_2338> collectParts(class_1937 world, class_2338 origin, int width, int depth, int height, class_2248 block) {
        class_2680 originState = world.method_8320(origin);
        if (!originState.method_27852(block)) {
            return List.of();
        }
        ArrayList<class_2338> positions = new ArrayList<class_2338>();
        class_2350 facing = (class_2350)originState.method_11654((class_2769)AbstractMultiBlockMachineBlock.FACING);
        for (int partY = 0; partY < height; ++partY) {
            for (int partZ = 0; partZ < depth; ++partZ) {
                for (int partX = 0; partX < width; ++partX) {
                    class_2338 partPos = BlacksmithWorkbenchAccess.offset(origin, facing, partX, partZ, partY);
                    if (!world.method_8320(partPos).method_27852(block)) {
                        return List.of();
                    }
                    positions.add(partPos);
                }
            }
        }
        positions.sort(Comparator.comparingInt(class_2382::method_10264).thenComparingInt(class_2382::method_10263).thenComparingInt(class_2382::method_10260));
        return positions;
    }

    private static class_2338 offset(class_2338 origin, class_2350 facing, int partX, int partZ, int partY) {
        class_2350 right = facing.method_10170();
        class_2350 back = facing.method_10153();
        return origin.method_10079(right, partX).method_10079(back, partZ).method_10086(partY);
    }

    public record WorkbenchPair(class_2338 furnaceOrigin, class_2338 tableOrigin) {
    }

    private record Factory(class_3218 world, WorkbenchPair pair) implements ExtendedScreenHandlerFactory<BlacksmithScreenOpeningData>
    {
        public BlacksmithScreenOpeningData getScreenOpeningData(class_3222 player) {
            List<BlacksmithGroupDefinition> groups = BlacksmithGroupRegistry.getAll().stream().filter(group -> BlacksmithKingdomUnlocks.kingdomTabs().contains(group.kingdomId())).sorted(Comparator.comparingInt(BlacksmithGroupDefinition::order).thenComparing(BlacksmithGroupDefinition::displayName)).toList();
            List<BlacksmithRecipeDisplay> recipes = this.world.method_8433().method_30027(GotRecipes.BLACKSMITH_TYPE).stream().map(entry -> {
                BlacksmithRecipe recipe = (BlacksmithRecipe)entry.comp_1933();
                List<class_1799> previews = recipe.ingredients().stream().map(BlacksmithRecipeIngredient::createPreviewStack).toList();
                return new BlacksmithRecipeDisplay(entry.comp_1932(), recipe.kingdomId(), recipe.groupId(), recipe.order(), recipe.requiredSmithingLevel(), recipe.speed(), recipe.result(), previews);
            }).filter(recipe -> BlacksmithKingdomUnlocks.kingdomTabs().contains(recipe.kingdomId())).sorted(BlacksmithRecipeSorting.displayComparator()).toList();
            class_2960 ownKingdomId = BlacksmithKingdomUnlocks.getOwnKingdomId(player);
            return new BlacksmithScreenOpeningData(this.pair.furnaceOrigin(), this.pair.tableOrigin(), groups, recipes, ownKingdomId == null ? "" : ownKingdomId.toString(), BlacksmithKingdomUnlocks.unlockedKingdomIds(player), BlacksmithKingdomUnlocks.talkedKingdomIds(player), BlacksmithKingdomUnlocks.nextIntelligenceRequirement(player));
        }

        public class_2561 method_5476() {
            return class_2561.method_43471((String)"block.agob.blacksmith_furnace");
        }

        public class_1703 createMenu(int syncId, class_1661 playerInventory, class_1657 player) {
            return BlacksmithScreenHandler.createServer(syncId, playerInventory, this.world, this.pair, this.getScreenOpeningData((class_3222)player));
        }
    }
}

