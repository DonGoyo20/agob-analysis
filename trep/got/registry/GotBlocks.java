/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.player.AttackBlockCallback
 *  net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
 *  net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
 *  net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
 *  net.fabricmc.fabric.api.registry.CompostingChanceRegistry
 *  net.fabricmc.fabric.api.registry.FlammableBlockRegistry
 *  net.fabricmc.fabric.api.registry.StrippableBlockRegistry
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2320
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2378
 *  net.minecraft.class_2397
 *  net.minecraft.class_2431
 *  net.minecraft.class_2465
 *  net.minecraft.class_2482
 *  net.minecraft.class_2498
 *  net.minecraft.class_2544
 *  net.minecraft.class_2586
 *  net.minecraft.class_2680
 *  net.minecraft.class_2756
 *  net.minecraft.class_2769
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3481
 *  net.minecraft.class_3619
 *  net.minecraft.class_3620
 *  net.minecraft.class_4970
 *  net.minecraft.class_4970$class_2251
 *  net.minecraft.class_5321
 *  net.minecraft.class_6017
 *  net.minecraft.class_6019
 *  net.minecraft.class_7706
 *  net.minecraft.class_7923
 */
package trep.got.registry;

import java.util.function.Consumer;
import java.util.function.Function;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2320;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2378;
import net.minecraft.class_2397;
import net.minecraft.class_2431;
import net.minecraft.class_2465;
import net.minecraft.class_2482;
import net.minecraft.class_2498;
import net.minecraft.class_2544;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2756;
import net.minecraft.class_2769;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3481;
import net.minecraft.class_3619;
import net.minecraft.class_3620;
import net.minecraft.class_4970;
import net.minecraft.class_5321;
import net.minecraft.class_6017;
import net.minecraft.class_6019;
import net.minecraft.class_7706;
import net.minecraft.class_7923;
import trep.got.GotMod;
import trep.got.blacksmith.BlacksmithCrafting;
import trep.got.blacksmith.BlacksmithWorkbenchAccess;
import trep.got.block.AgingBarrelBlock;
import trep.got.block.AltarBlock;
import trep.got.block.BlacksmithFurnaceBlock;
import trep.got.block.BlacksmithTableBlock;
import trep.got.block.BrewerBlock;
import trep.got.block.CupBlock;
import trep.got.block.CuttingBoardBlock;
import trep.got.block.DragonCradleBlock;
import trep.got.block.DragonEggBlock;
import trep.got.block.FlaxCropBlock;
import trep.got.block.FourStageCropBlock;
import trep.got.block.GateFenceBlock;
import trep.got.block.GateMechanismBlock;
import trep.got.block.GrapeCropBlock;
import trep.got.block.HopCropBlock;
import trep.got.block.MarketDisplayBlock;
import trep.got.block.PanBlock;
import trep.got.block.PlateBlock;
import trep.got.block.PotBlock;
import trep.got.block.SeatFurnitureBlock;
import trep.got.block.StoneStairsBlock;
import trep.got.block.StoveBlock;
import trep.got.block.TentCollisionBlock;
import trep.got.block.WeirwoodBloodyFaceBlock;
import trep.got.block.WeirwoodFaceBlock;
import trep.got.block.WildCropBlock;
import trep.got.item.BlacksmithHammerRules;
import trep.got.item.DragonCradleItem;
import trep.got.item.DragonEggItem;
import trep.got.registry.GotCastles;
import trep.got.registry.GotItems;

public final class GotBlocks {
    public static final class_2248 ALTAR = GotBlocks.register("altar", (class_2248)new AltarBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_16023).method_9626(class_2498.field_11547).method_9629(2.5f, 6.0f).method_22488().method_50012(class_3619.field_15972)));
    public static final class_2248 BREWER = GotBlocks.register("brewer", (class_2248)new BrewerBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_15996).method_9626(class_2498.field_11547).method_9629(2.5f, 6.0f).method_22488().method_50012(class_3619.field_15972)));
    public static final class_2248 AGING_BARREL = GotBlocks.register("aging_barrel", (class_2248)new AgingBarrelBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_15996).method_9626(class_2498.field_11547).method_9629(2.5f, 6.0f).method_22488().method_50012(class_3619.field_15972)));
    public static final class_2248 PLATE = GotBlocks.register("plate", (class_2248)new PlateBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_15979).method_9626(class_2498.field_11544).method_9629(0.6f, 0.6f).method_22488().method_50012(class_3619.field_15971)));
    public static final class_2248 CUTTING_BOARD = GotBlocks.register("cutting_board", (class_2248)new CuttingBoardBlock(class_4970.class_2251.method_9630((class_4970)class_2246.field_10161).method_31710(class_3620.field_15996).method_9626(class_2498.field_11547).method_9629(0.8f, 1.2f).method_22488().method_50012(class_3619.field_15971)));
    public static final class_2248 POT = GotBlocks.register("pot", (class_2248)new PotBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_16005).method_9626(class_2498.field_11533).method_9629(1.2f, 3.0f).method_22488().method_50012(class_3619.field_15971)));
    public static final class_2248 PAN = GotBlocks.register("pan", (class_2248)new PanBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_16005).method_9626(class_2498.field_11533).method_9629(1.0f, 3.0f).method_22488().method_50012(class_3619.field_15971)));
    public static final class_2248 STOVE = GotBlocks.register("stove", (class_2248)new StoveBlock(class_4970.class_2251.method_9630((class_4970)class_2246.field_10056).method_31710(class_3620.field_16023).method_9626(class_2498.field_11544).method_9629(2.0f, 6.0f).method_50012(class_3619.field_15972)));
    public static final class_2248 CUP_BLOCK = GotBlocks.registerBlockOnly("cup_block", (class_2248)new CupBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_15996).method_9626(class_2498.field_11537).method_9618().method_22488().method_9634().method_50012(class_3619.field_15971)));
    public static final class_2248 SILVER_CUP_BLOCK = GotBlocks.registerBlockOnly("silver_cup_block", (class_2248)new CupBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_16005).method_9626(class_2498.field_11537).method_9618().method_22488().method_9634().method_50012(class_3619.field_15971), CupBlock.TALL_SHAPE));
    public static final class_2248 POLISHED_WOOD = GotBlocks.register("polished_wood", new class_2248(class_4970.class_2251.method_9637().method_31710(class_3620.field_15996).method_9626(class_2498.field_11547).method_9629(2.0f, 3.0f).method_50012(class_3619.field_15972)));
    public static final class_2248 TATCH = GotBlocks.register("tatch", GotBlocks.tatchBlock());
    public static final class_2248 TATCH_STAIRS = GotBlocks.register("tatch_stairs", GotBlocks.stairsBlock(TATCH));
    public static final class_2248 TATCH_SLAB = GotBlocks.register("tatch_slab", GotBlocks.slabBlock(TATCH));
    public static final class_2248 TATCH_WALL = GotBlocks.register("tatch_wall", GotBlocks.wallBlock(TATCH));
    public static final class_2248 LIMESTONE = GotBlocks.register("limestone", GotBlocks.stoneBlock(class_2246.field_10340, class_3620.field_16023));
    public static final class_2248 COBBLED_LIMESTONE = GotBlocks.register("cobbled_limestone", GotBlocks.stoneBlock(class_2246.field_10445, class_3620.field_16023));
    public static final class_2248 LIMESTONE_BRICKS = GotBlocks.register("limestone_bricks", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 LIMESTONE_TILES = GotBlocks.register("limestone_tiles", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 ASH_STONE = GotBlocks.register("ash_stone", GotBlocks.stoneBlock(class_2246.field_10340, class_3620.field_16023));
    public static final class_2248 COBBLED_ASH_STONE = GotBlocks.register("cobbled_ash_stone", GotBlocks.stoneBlock(class_2246.field_10445, class_3620.field_16023));
    public static final class_2248 ASH_STONE_BRICKS = GotBlocks.register("ash_stone_bricks", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 ASH_STONE_TILES = GotBlocks.register("ash_stone_tiles", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 MARBLE = GotBlocks.register("marble", GotBlocks.stoneBlock(class_2246.field_10340, class_3620.field_16005));
    public static final class_2248 COBBLED_MARBLE = GotBlocks.register("cobbled_marble", GotBlocks.stoneBlock(class_2246.field_10445, class_3620.field_16005));
    public static final class_2248 MARBLE_BRICKS = GotBlocks.register("marble_bricks", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16005));
    public static final class_2248 MARBLE_TILES = GotBlocks.register("marble_tiles", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16005));
    public static final class_2248 MOSSY_MARBLE = GotBlocks.register("mossy_marble", GotBlocks.stoneBlock(class_2246.field_10340, class_3620.field_16005));
    public static final class_2248 COBBLED_MOSSY_MARBLE = GotBlocks.register("cobbled_mossy_marble", GotBlocks.stoneBlock(class_2246.field_10445, class_3620.field_16005));
    public static final class_2248 MOSSY_MARBLE_BRICKS = GotBlocks.register("mossy_marble_bricks", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16005));
    public static final class_2248 MOSSY_MARBLE_TILES = GotBlocks.register("mossy_marble_tiles", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16005));
    public static final class_2248 BLUESTONE = GotBlocks.register("bluestone", GotBlocks.stoneBlock(class_2246.field_10340, class_3620.field_33532));
    public static final class_2248 COBBLED_BLUESTONE = GotBlocks.register("cobbled_bluestone", GotBlocks.stoneBlock(class_2246.field_10445, class_3620.field_33532));
    public static final class_2248 BLUESTONE_BRICKS = GotBlocks.register("bluestone_bricks", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_33532));
    public static final class_2248 BLUESTONE_TILES = GotBlocks.register("bluestone_tiles", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_33532));
    public static final class_2248 SILTSTONE = GotBlocks.register("siltstone", GotBlocks.stoneBlock(class_2246.field_10340, class_3620.field_16023));
    public static final class_2248 COBBLED_SILTSTONE = GotBlocks.register("cobbled_siltstone", GotBlocks.stoneBlock(class_2246.field_10445, class_3620.field_16023));
    public static final class_2248 SILTSTONE_BRICKS = GotBlocks.register("siltstone_bricks", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 SILTSTONE_TILES = GotBlocks.register("siltstone_tiles", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 ENCHANTED_STONE = GotBlocks.register("enchanted_stone", GotBlocks.stoneBlock(class_2246.field_10340, class_3620.field_16023));
    public static final class_2248 COBBLED_ENCHANTED_STONE = GotBlocks.register("cobbled_enchanted_stone", GotBlocks.stoneBlock(class_2246.field_10445, class_3620.field_16023));
    public static final class_2248 ENCHANTED_STONE_BRICKS = GotBlocks.register("enchanted_stone_bricks", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 ENCHANTED_STONE_TILES = GotBlocks.register("enchanted_stone_tiles", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 CHARRED_STONE = GotBlocks.register("charred_stone", GotBlocks.stoneBlock(class_2246.field_10340, class_3620.field_16023));
    public static final class_2248 COBBLED_CHARRED_STONE = GotBlocks.register("cobbled_charred_stone", GotBlocks.stoneBlock(class_2246.field_10445, class_3620.field_16023));
    public static final class_2248 CHARRED_STONE_BRICKS = GotBlocks.register("charred_stone_bricks", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 CHARRED_STONE_TILES = GotBlocks.register("charred_stone_tiles", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 VOLCANIC_STONE = GotBlocks.register("volcanic_stone", GotBlocks.stoneBlock(class_2246.field_10340, class_3620.field_16023));
    public static final class_2248 COBBLED_VOLCANIC_STONE = GotBlocks.register("cobbled_volcanic_stone", GotBlocks.stoneBlock(class_2246.field_10445, class_3620.field_16023));
    public static final class_2248 VOLCANIC_STONE_BRICKS = GotBlocks.register("volcanic_stone_bricks", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 VOLCANIC_STONE_TILES = GotBlocks.register("volcanic_stone_tiles", GotBlocks.stoneBlock(class_2246.field_10056, class_3620.field_16023));
    public static final class_2248 LIMESTONE_STAIRS = GotBlocks.register("limestone_stairs", GotBlocks.stairsBlock(LIMESTONE));
    public static final class_2248 LIMESTONE_SLAB = GotBlocks.register("limestone_slab", GotBlocks.slabBlock(LIMESTONE));
    public static final class_2248 LIMESTONE_WALL = GotBlocks.register("limestone_wall", GotBlocks.wallBlock(LIMESTONE));
    public static final class_2248 COBBLED_LIMESTONE_STAIRS = GotBlocks.register("cobbled_limestone_stairs", GotBlocks.stairsBlock(COBBLED_LIMESTONE));
    public static final class_2248 COBBLED_LIMESTONE_SLAB = GotBlocks.register("cobbled_limestone_slab", GotBlocks.slabBlock(COBBLED_LIMESTONE));
    public static final class_2248 COBBLED_LIMESTONE_WALL = GotBlocks.register("cobbled_limestone_wall", GotBlocks.wallBlock(COBBLED_LIMESTONE));
    public static final class_2248 LIMESTONE_BRICKS_STAIRS = GotBlocks.register("limestone_bricks_stairs", GotBlocks.stairsBlock(LIMESTONE_BRICKS));
    public static final class_2248 LIMESTONE_BRICKS_SLAB = GotBlocks.register("limestone_bricks_slab", GotBlocks.slabBlock(LIMESTONE_BRICKS));
    public static final class_2248 LIMESTONE_BRICKS_WALL = GotBlocks.register("limestone_bricks_wall", GotBlocks.wallBlock(LIMESTONE_BRICKS));
    public static final class_2248 LIMESTONE_TILES_STAIRS = GotBlocks.register("limestone_tiles_stairs", GotBlocks.stairsBlock(LIMESTONE_TILES));
    public static final class_2248 LIMESTONE_TILES_SLAB = GotBlocks.register("limestone_tiles_slab", GotBlocks.slabBlock(LIMESTONE_TILES));
    public static final class_2248 LIMESTONE_TILES_WALL = GotBlocks.register("limestone_tiles_wall", GotBlocks.wallBlock(LIMESTONE_TILES));
    public static final class_2248 ASH_STONE_STAIRS = GotBlocks.register("ash_stone_stairs", GotBlocks.stairsBlock(ASH_STONE));
    public static final class_2248 ASH_STONE_SLAB = GotBlocks.register("ash_stone_slab", GotBlocks.slabBlock(ASH_STONE));
    public static final class_2248 ASH_STONE_WALL = GotBlocks.register("ash_stone_wall", GotBlocks.wallBlock(ASH_STONE));
    public static final class_2248 COBBLED_ASH_STONE_STAIRS = GotBlocks.register("cobbled_ash_stone_stairs", GotBlocks.stairsBlock(COBBLED_ASH_STONE));
    public static final class_2248 COBBLED_ASH_STONE_SLAB = GotBlocks.register("cobbled_ash_stone_slab", GotBlocks.slabBlock(COBBLED_ASH_STONE));
    public static final class_2248 COBBLED_ASH_STONE_WALL = GotBlocks.register("cobbled_ash_stone_wall", GotBlocks.wallBlock(COBBLED_ASH_STONE));
    public static final class_2248 ASH_STONE_BRICKS_STAIRS = GotBlocks.register("ash_stone_bricks_stairs", GotBlocks.stairsBlock(ASH_STONE_BRICKS));
    public static final class_2248 ASH_STONE_BRICKS_SLAB = GotBlocks.register("ash_stone_bricks_slab", GotBlocks.slabBlock(ASH_STONE_BRICKS));
    public static final class_2248 ASH_STONE_BRICKS_WALL = GotBlocks.register("ash_stone_bricks_wall", GotBlocks.wallBlock(ASH_STONE_BRICKS));
    public static final class_2248 ASH_STONE_TILES_STAIRS = GotBlocks.register("ash_stone_tiles_stairs", GotBlocks.stairsBlock(ASH_STONE_TILES));
    public static final class_2248 ASH_STONE_TILES_SLAB = GotBlocks.register("ash_stone_tiles_slab", GotBlocks.slabBlock(ASH_STONE_TILES));
    public static final class_2248 ASH_STONE_TILES_WALL = GotBlocks.register("ash_stone_tiles_wall", GotBlocks.wallBlock(ASH_STONE_TILES));
    public static final class_2248 MARBLE_STAIRS = GotBlocks.register("marble_stairs", GotBlocks.stairsBlock(MARBLE));
    public static final class_2248 MARBLE_SLAB = GotBlocks.register("marble_slab", GotBlocks.slabBlock(MARBLE));
    public static final class_2248 MARBLE_WALL = GotBlocks.register("marble_wall", GotBlocks.wallBlock(MARBLE));
    public static final class_2248 COBBLED_MARBLE_STAIRS = GotBlocks.register("cobbled_marble_stairs", GotBlocks.stairsBlock(COBBLED_MARBLE));
    public static final class_2248 COBBLED_MARBLE_SLAB = GotBlocks.register("cobbled_marble_slab", GotBlocks.slabBlock(COBBLED_MARBLE));
    public static final class_2248 COBBLED_MARBLE_WALL = GotBlocks.register("cobbled_marble_wall", GotBlocks.wallBlock(COBBLED_MARBLE));
    public static final class_2248 MARBLE_BRICKS_STAIRS = GotBlocks.register("marble_bricks_stairs", GotBlocks.stairsBlock(MARBLE_BRICKS));
    public static final class_2248 MARBLE_BRICKS_SLAB = GotBlocks.register("marble_bricks_slab", GotBlocks.slabBlock(MARBLE_BRICKS));
    public static final class_2248 MARBLE_BRICKS_WALL = GotBlocks.register("marble_bricks_wall", GotBlocks.wallBlock(MARBLE_BRICKS));
    public static final class_2248 MARBLE_TILES_STAIRS = GotBlocks.register("marble_tiles_stairs", GotBlocks.stairsBlock(MARBLE_TILES));
    public static final class_2248 MARBLE_TILES_SLAB = GotBlocks.register("marble_tiles_slab", GotBlocks.slabBlock(MARBLE_TILES));
    public static final class_2248 MARBLE_TILES_WALL = GotBlocks.register("marble_tiles_wall", GotBlocks.wallBlock(MARBLE_TILES));
    public static final class_2248 MOSSY_MARBLE_STAIRS = GotBlocks.register("mossy_marble_stairs", GotBlocks.stairsBlock(MOSSY_MARBLE));
    public static final class_2248 MOSSY_MARBLE_SLAB = GotBlocks.register("mossy_marble_slab", GotBlocks.slabBlock(MOSSY_MARBLE));
    public static final class_2248 MOSSY_MARBLE_WALL = GotBlocks.register("mossy_marble_wall", GotBlocks.wallBlock(MOSSY_MARBLE));
    public static final class_2248 COBBLED_MOSSY_MARBLE_STAIRS = GotBlocks.register("cobbled_mossy_marble_stairs", GotBlocks.stairsBlock(COBBLED_MOSSY_MARBLE));
    public static final class_2248 COBBLED_MOSSY_MARBLE_SLAB = GotBlocks.register("cobbled_mossy_marble_slab", GotBlocks.slabBlock(COBBLED_MOSSY_MARBLE));
    public static final class_2248 COBBLED_MOSSY_MARBLE_WALL = GotBlocks.register("cobbled_mossy_marble_wall", GotBlocks.wallBlock(COBBLED_MOSSY_MARBLE));
    public static final class_2248 MOSSY_MARBLE_BRICKS_STAIRS = GotBlocks.register("mossy_marble_bricks_stairs", GotBlocks.stairsBlock(MOSSY_MARBLE_BRICKS));
    public static final class_2248 MOSSY_MARBLE_BRICKS_SLAB = GotBlocks.register("mossy_marble_bricks_slab", GotBlocks.slabBlock(MOSSY_MARBLE_BRICKS));
    public static final class_2248 MOSSY_MARBLE_BRICKS_WALL = GotBlocks.register("mossy_marble_bricks_wall", GotBlocks.wallBlock(MOSSY_MARBLE_BRICKS));
    public static final class_2248 MOSSY_MARBLE_TILES_STAIRS = GotBlocks.register("mossy_marble_tiles_stairs", GotBlocks.stairsBlock(MOSSY_MARBLE_TILES));
    public static final class_2248 MOSSY_MARBLE_TILES_SLAB = GotBlocks.register("mossy_marble_tiles_slab", GotBlocks.slabBlock(MOSSY_MARBLE_TILES));
    public static final class_2248 MOSSY_MARBLE_TILES_WALL = GotBlocks.register("mossy_marble_tiles_wall", GotBlocks.wallBlock(MOSSY_MARBLE_TILES));
    public static final class_2248 BLUESTONE_STAIRS = GotBlocks.register("bluestone_stairs", GotBlocks.stairsBlock(BLUESTONE));
    public static final class_2248 BLUESTONE_SLAB = GotBlocks.register("bluestone_slab", GotBlocks.slabBlock(BLUESTONE));
    public static final class_2248 BLUESTONE_WALL = GotBlocks.register("bluestone_wall", GotBlocks.wallBlock(BLUESTONE));
    public static final class_2248 COBBLED_BLUESTONE_STAIRS = GotBlocks.register("cobbled_bluestone_stairs", GotBlocks.stairsBlock(COBBLED_BLUESTONE));
    public static final class_2248 COBBLED_BLUESTONE_SLAB = GotBlocks.register("cobbled_bluestone_slab", GotBlocks.slabBlock(COBBLED_BLUESTONE));
    public static final class_2248 COBBLED_BLUESTONE_WALL = GotBlocks.register("cobbled_bluestone_wall", GotBlocks.wallBlock(COBBLED_BLUESTONE));
    public static final class_2248 BLUESTONE_BRICKS_STAIRS = GotBlocks.register("bluestone_bricks_stairs", GotBlocks.stairsBlock(BLUESTONE_BRICKS));
    public static final class_2248 BLUESTONE_BRICKS_SLAB = GotBlocks.register("bluestone_bricks_slab", GotBlocks.slabBlock(BLUESTONE_BRICKS));
    public static final class_2248 BLUESTONE_BRICKS_WALL = GotBlocks.register("bluestone_bricks_wall", GotBlocks.wallBlock(BLUESTONE_BRICKS));
    public static final class_2248 BLUESTONE_TILES_STAIRS = GotBlocks.register("bluestone_tiles_stairs", GotBlocks.stairsBlock(BLUESTONE_TILES));
    public static final class_2248 BLUESTONE_TILES_SLAB = GotBlocks.register("bluestone_tiles_slab", GotBlocks.slabBlock(BLUESTONE_TILES));
    public static final class_2248 BLUESTONE_TILES_WALL = GotBlocks.register("bluestone_tiles_wall", GotBlocks.wallBlock(BLUESTONE_TILES));
    public static final class_2248 SILTSTONE_STAIRS = GotBlocks.register("siltstone_stairs", GotBlocks.stairsBlock(SILTSTONE));
    public static final class_2248 SILTSTONE_SLAB = GotBlocks.register("siltstone_slab", GotBlocks.slabBlock(SILTSTONE));
    public static final class_2248 SILTSTONE_WALL = GotBlocks.register("siltstone_wall", GotBlocks.wallBlock(SILTSTONE));
    public static final class_2248 COBBLED_SILTSTONE_STAIRS = GotBlocks.register("cobbled_siltstone_stairs", GotBlocks.stairsBlock(COBBLED_SILTSTONE));
    public static final class_2248 COBBLED_SILTSTONE_SLAB = GotBlocks.register("cobbled_siltstone_slab", GotBlocks.slabBlock(COBBLED_SILTSTONE));
    public static final class_2248 COBBLED_SILTSTONE_WALL = GotBlocks.register("cobbled_siltstone_wall", GotBlocks.wallBlock(COBBLED_SILTSTONE));
    public static final class_2248 SILTSTONE_BRICKS_STAIRS = GotBlocks.register("siltstone_bricks_stairs", GotBlocks.stairsBlock(SILTSTONE_BRICKS));
    public static final class_2248 SILTSTONE_BRICKS_SLAB = GotBlocks.register("siltstone_bricks_slab", GotBlocks.slabBlock(SILTSTONE_BRICKS));
    public static final class_2248 SILTSTONE_BRICKS_WALL = GotBlocks.register("siltstone_bricks_wall", GotBlocks.wallBlock(SILTSTONE_BRICKS));
    public static final class_2248 SILTSTONE_TILES_STAIRS = GotBlocks.register("siltstone_tiles_stairs", GotBlocks.stairsBlock(SILTSTONE_TILES));
    public static final class_2248 SILTSTONE_TILES_SLAB = GotBlocks.register("siltstone_tiles_slab", GotBlocks.slabBlock(SILTSTONE_TILES));
    public static final class_2248 SILTSTONE_TILES_WALL = GotBlocks.register("siltstone_tiles_wall", GotBlocks.wallBlock(SILTSTONE_TILES));
    public static final class_2248 ENCHANTED_STONE_STAIRS = GotBlocks.register("enchanted_stone_stairs", GotBlocks.stairsBlock(ENCHANTED_STONE));
    public static final class_2248 ENCHANTED_STONE_SLAB = GotBlocks.register("enchanted_stone_slab", GotBlocks.slabBlock(ENCHANTED_STONE));
    public static final class_2248 ENCHANTED_STONE_WALL = GotBlocks.register("enchanted_stone_wall", GotBlocks.wallBlock(ENCHANTED_STONE));
    public static final class_2248 COBBLED_ENCHANTED_STONE_STAIRS = GotBlocks.register("cobbled_enchanted_stone_stairs", GotBlocks.stairsBlock(COBBLED_ENCHANTED_STONE));
    public static final class_2248 COBBLED_ENCHANTED_STONE_SLAB = GotBlocks.register("cobbled_enchanted_stone_slab", GotBlocks.slabBlock(COBBLED_ENCHANTED_STONE));
    public static final class_2248 COBBLED_ENCHANTED_STONE_WALL = GotBlocks.register("cobbled_enchanted_stone_wall", GotBlocks.wallBlock(COBBLED_ENCHANTED_STONE));
    public static final class_2248 ENCHANTED_STONE_BRICKS_STAIRS = GotBlocks.register("enchanted_stone_bricks_stairs", GotBlocks.stairsBlock(ENCHANTED_STONE_BRICKS));
    public static final class_2248 ENCHANTED_STONE_BRICKS_SLAB = GotBlocks.register("enchanted_stone_bricks_slab", GotBlocks.slabBlock(ENCHANTED_STONE_BRICKS));
    public static final class_2248 ENCHANTED_STONE_BRICKS_WALL = GotBlocks.register("enchanted_stone_bricks_wall", GotBlocks.wallBlock(ENCHANTED_STONE_BRICKS));
    public static final class_2248 ENCHANTED_STONE_TILES_STAIRS = GotBlocks.register("enchanted_stone_tiles_stairs", GotBlocks.stairsBlock(ENCHANTED_STONE_TILES));
    public static final class_2248 ENCHANTED_STONE_TILES_SLAB = GotBlocks.register("enchanted_stone_tiles_slab", GotBlocks.slabBlock(ENCHANTED_STONE_TILES));
    public static final class_2248 ENCHANTED_STONE_TILES_WALL = GotBlocks.register("enchanted_stone_tiles_wall", GotBlocks.wallBlock(ENCHANTED_STONE_TILES));
    public static final class_2248 CHARRED_STONE_STAIRS = GotBlocks.register("charred_stone_stairs", GotBlocks.stairsBlock(CHARRED_STONE));
    public static final class_2248 CHARRED_STONE_SLAB = GotBlocks.register("charred_stone_slab", GotBlocks.slabBlock(CHARRED_STONE));
    public static final class_2248 CHARRED_STONE_WALL = GotBlocks.register("charred_stone_wall", GotBlocks.wallBlock(CHARRED_STONE));
    public static final class_2248 COBBLED_CHARRED_STONE_STAIRS = GotBlocks.register("cobbled_charred_stone_stairs", GotBlocks.stairsBlock(COBBLED_CHARRED_STONE));
    public static final class_2248 COBBLED_CHARRED_STONE_SLAB = GotBlocks.register("cobbled_charred_stone_slab", GotBlocks.slabBlock(COBBLED_CHARRED_STONE));
    public static final class_2248 COBBLED_CHARRED_STONE_WALL = GotBlocks.register("cobbled_charred_stone_wall", GotBlocks.wallBlock(COBBLED_CHARRED_STONE));
    public static final class_2248 CHARRED_STONE_BRICKS_STAIRS = GotBlocks.register("charred_stone_bricks_stairs", GotBlocks.stairsBlock(CHARRED_STONE_BRICKS));
    public static final class_2248 CHARRED_STONE_BRICKS_SLAB = GotBlocks.register("charred_stone_bricks_slab", GotBlocks.slabBlock(CHARRED_STONE_BRICKS));
    public static final class_2248 CHARRED_STONE_BRICKS_WALL = GotBlocks.register("charred_stone_bricks_wall", GotBlocks.wallBlock(CHARRED_STONE_BRICKS));
    public static final class_2248 CHARRED_STONE_TILES_STAIRS = GotBlocks.register("charred_stone_tiles_stairs", GotBlocks.stairsBlock(CHARRED_STONE_TILES));
    public static final class_2248 CHARRED_STONE_TILES_SLAB = GotBlocks.register("charred_stone_tiles_slab", GotBlocks.slabBlock(CHARRED_STONE_TILES));
    public static final class_2248 CHARRED_STONE_TILES_WALL = GotBlocks.register("charred_stone_tiles_wall", GotBlocks.wallBlock(CHARRED_STONE_TILES));
    public static final class_2248 VOLCANIC_STONE_STAIRS = GotBlocks.register("volcanic_stone_stairs", GotBlocks.stairsBlock(VOLCANIC_STONE));
    public static final class_2248 VOLCANIC_STONE_SLAB = GotBlocks.register("volcanic_stone_slab", GotBlocks.slabBlock(VOLCANIC_STONE));
    public static final class_2248 VOLCANIC_STONE_WALL = GotBlocks.register("volcanic_stone_wall", GotBlocks.wallBlock(VOLCANIC_STONE));
    public static final class_2248 COBBLED_VOLCANIC_STONE_STAIRS = GotBlocks.register("cobbled_volcanic_stone_stairs", GotBlocks.stairsBlock(COBBLED_VOLCANIC_STONE));
    public static final class_2248 COBBLED_VOLCANIC_STONE_SLAB = GotBlocks.register("cobbled_volcanic_stone_slab", GotBlocks.slabBlock(COBBLED_VOLCANIC_STONE));
    public static final class_2248 COBBLED_VOLCANIC_STONE_WALL = GotBlocks.register("cobbled_volcanic_stone_wall", GotBlocks.wallBlock(COBBLED_VOLCANIC_STONE));
    public static final class_2248 VOLCANIC_STONE_BRICKS_STAIRS = GotBlocks.register("volcanic_stone_bricks_stairs", GotBlocks.stairsBlock(VOLCANIC_STONE_BRICKS));
    public static final class_2248 VOLCANIC_STONE_BRICKS_SLAB = GotBlocks.register("volcanic_stone_bricks_slab", GotBlocks.slabBlock(VOLCANIC_STONE_BRICKS));
    public static final class_2248 VOLCANIC_STONE_BRICKS_WALL = GotBlocks.register("volcanic_stone_bricks_wall", GotBlocks.wallBlock(VOLCANIC_STONE_BRICKS));
    public static final class_2248 VOLCANIC_STONE_TILES_STAIRS = GotBlocks.register("volcanic_stone_tiles_stairs", GotBlocks.stairsBlock(VOLCANIC_STONE_TILES));
    public static final class_2248 VOLCANIC_STONE_TILES_SLAB = GotBlocks.register("volcanic_stone_tiles_slab", GotBlocks.slabBlock(VOLCANIC_STONE_TILES));
    public static final class_2248 VOLCANIC_STONE_TILES_WALL = GotBlocks.register("volcanic_stone_tiles_wall", GotBlocks.wallBlock(VOLCANIC_STONE_TILES));
    public static final class_2248 CHAIR_BLOCK = GotBlocks.register("chair_block", (class_2248)new SeatFurnitureBlock(class_4970.class_2251.method_9630((class_4970)class_2246.field_10161).method_31710(class_3620.field_15996).method_9626(class_2498.field_11547).method_9629(2.0f, 3.0f).method_22488().method_50012(class_3619.field_15972), SeatFurnitureBlock.Kind.CHAIR));
    public static final class_2248 THRONE_BLOCK = GotBlocks.register("throne_block", (class_2248)new SeatFurnitureBlock(class_4970.class_2251.method_9630((class_4970)class_2246.field_10161).method_31710(class_3620.field_15996).method_9626(class_2498.field_11547).method_9629(2.5f, 4.0f).method_22488().method_50012(class_3619.field_15972), SeatFurnitureBlock.Kind.THRONE));
    public static final class_2248 WEIRWOOD_LOG = GotBlocks.register("weirwood_log", (class_2248)new class_2465(class_4970.class_2251.method_9630((class_4970)class_2246.field_10431).method_31710(class_3620.field_15996)));
    public static final class_2248 WEIRWOOD_WOOD = GotBlocks.register("weirwood_wood", (class_2248)new class_2465(class_4970.class_2251.method_9630((class_4970)class_2246.field_10126).method_31710(class_3620.field_15996)));
    public static final class_2248 WEIRWOOD_LOG_FACE = GotBlocks.register("weirwood_log_face", (class_2248)new WeirwoodFaceBlock(class_4970.class_2251.method_9630((class_4970)class_2246.field_10431).method_31710(class_3620.field_15996)));
    public static final class_2248 WEIRWOOD_LOG_FACE_BLOODY = GotBlocks.register("weirwood_log_face_bloody", (class_2248)new WeirwoodBloodyFaceBlock(class_4970.class_2251.method_9630((class_4970)class_2246.field_10431).method_31710(class_3620.field_16020)));
    public static final class_2248 STRIPPED_WEIRWOOD_LOG = GotBlocks.register("stripped_weirwood_log", (class_2248)new class_2465(class_4970.class_2251.method_9630((class_4970)class_2246.field_10519).method_31710(class_3620.field_15996)));
    public static final class_2248 STRIPPED_WEIRWOOD_WOOD = GotBlocks.register("stripped_weirwood_wood", (class_2248)new class_2465(class_4970.class_2251.method_9630((class_4970)class_2246.field_10250).method_31710(class_3620.field_15996)));
    public static final class_2248 WEIRWOOD_PLANKS = GotBlocks.register("weirwood_planks", new class_2248(class_4970.class_2251.method_9630((class_4970)class_2246.field_10161).method_31710(class_3620.field_15996)));
    public static final class_2248 WEIRWOOD_LEAVES = GotBlocks.register("weirwood_leaves", (class_2248)new class_2397(class_4970.class_2251.method_9630((class_4970)class_2246.field_10503).method_31710(class_3620.field_16020)));
    public static final class_2248 SILVER_ORE = GotBlocks.register("silver_ore", (class_2248)new class_2431((class_6017)class_6019.method_35017((int)0, (int)2), class_4970.class_2251.method_9637().method_31710(class_3620.field_16023).method_29292().method_9629(3.0f, 3.0f).method_9626(class_2498.field_11544)));
    public static final class_2248 DEEPSLATE_SILVER_ORE = GotBlocks.register("deepslate_silver_ore", (class_2248)new class_2431((class_6017)class_6019.method_35017((int)0, (int)2), class_4970.class_2251.method_9637().method_31710(class_3620.field_33532).method_29292().method_9629(4.5f, 3.0f).method_9626(class_2498.field_29033)));
    public static final class_2248 SILVER_BLOCK = GotBlocks.register("silver_block", new class_2248(class_4970.class_2251.method_9637().method_31710(class_3620.field_16005).method_29292().method_9629(5.0f, 6.0f).method_9626(class_2498.field_11533)));
    public static final class_2248 RAW_SILVER_BLOCK = GotBlocks.register("raw_silver_block", new class_2248(class_4970.class_2251.method_9637().method_31710(class_3620.field_16023).method_29292().method_9629(5.0f, 6.0f).method_9626(class_2498.field_11544)));
    public static final class_2248 STEEL_BLOCK = GotBlocks.register("steel_block", new class_2248(class_4970.class_2251.method_9637().method_31710(class_3620.field_16005).method_29292().method_9629(5.0f, 6.0f).method_9626(class_2498.field_11533)));
    public static final class_2248 RAW_STEEL_BLOCK = GotBlocks.register("raw_steel_block", new class_2248(class_4970.class_2251.method_9637().method_31710(class_3620.field_16023).method_29292().method_9629(5.0f, 6.0f).method_9626(class_2498.field_11544)));
    public static final class_2248 BLACKSMITH_TABLE = GotBlocks.register("blacksmith_table", new BlacksmithTableBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_15996).method_9626(class_2498.field_11547).method_9629(3.0f, 6.0f).method_29292().method_22488().method_50012(class_3619.field_15972)));
    public static final class_2248 BLACKSMITH_FURNACE = GotBlocks.register("blacksmith_furnace", new BlacksmithFurnaceBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_16023).method_9626(class_2498.field_11544).method_9629(4.0f, 6.0f).method_29292().method_22488().method_50012(class_3619.field_15972)));
    public static final class_2248 MARKET_DISPLAY = GotBlocks.register("market_display", (class_2248)new MarketDisplayBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_15996).method_9626(class_2498.field_11547).method_9629(2.5f, 6.0f).method_22488().method_50012(class_3619.field_15972)));
    public static final class_2248 MARKET_DISPLAY_REINFORCED = GotBlocks.register("market_display_reinforced", (class_2248)new MarketDisplayBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_15996).method_9626(class_2498.field_11547).method_9629(3.5f, 6.0f).method_22488().method_50012(class_3619.field_15972)));
    public static final class_2248 DRAGON_EGG = GotBlocks.register("dragon_egg", (class_2248)new DragonEggBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_16020).method_9626(class_2498.field_11544).method_9629(1.5f, 6.0f).method_22488().method_42327().method_50012(class_3619.field_15972)), block -> new DragonEggItem((class_2248)block, new class_1792.class_1793()));
    public static final class_2248 DRAGON_CRADLE = GotBlocks.register("dragon_cradle", (class_2248)new DragonCradleBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_15996).method_9626(class_2498.field_11544).method_9629(1.5f, 6.0f).method_22488().method_42327().method_50012(class_3619.field_15972)), block -> new DragonCradleItem((class_2248)block, new class_1792.class_1793()));
    public static final class_2248 TENT_COLLISION = GotBlocks.registerBlockOnly("tent_collision", new TentCollisionBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_16004).method_9629(0.8f, 3.0f).method_9626(class_2498.field_11543).method_22488().method_50012(class_3619.field_15972)));
    public static final class_2248 FLAX_CROP = GotBlocks.registerBlockOnly("flax_crop", (class_2248)new FlaxCropBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_16004).method_9634().method_9640().method_9618().method_9626(class_2498.field_17580).method_22488().method_50012(class_3619.field_15971)));
    public static final class_2248 HOP_CROP = GotBlocks.registerBlockOnly("hop_crop", (class_2248)new HopCropBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_16004).method_9634().method_9640().method_9618().method_9626(class_2498.field_17580).method_22488().method_50012(class_3619.field_15971)));
    public static final class_2248 GRAPE_CROP = GotBlocks.registerBlockOnly("grape", (class_2248)new GrapeCropBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_16004).method_9634().method_9640().method_9618().method_9626(class_2498.field_17580).method_22488().method_50012(class_3619.field_15971)));
    public static final class_2248 TOMATO_CROP = GotBlocks.registerBlockOnly("tomato_crop", GotBlocks.cropBlock(FourStageCropBlock.CropKind.TOMATO));
    public static final class_2248 ORANGE_CROP = GotBlocks.registerBlockOnly("orange_crop", GotBlocks.cropBlock(FourStageCropBlock.CropKind.ORANGE));
    public static final class_2248 TURNIP_CROP = GotBlocks.registerBlockOnly("turnip_crop", GotBlocks.cropBlock(FourStageCropBlock.CropKind.TURNIP));
    public static final class_2248 CHAMPIGNON_CROP = GotBlocks.registerBlockOnly("champignon_crop", GotBlocks.cropBlock(FourStageCropBlock.CropKind.CHAMPIGNON));
    public static final class_2248 WILD_TOMATO = GotBlocks.registerBlockOnly("wild_tomato", GotBlocks.wildCropBlock(class_3620.field_16004));
    public static final class_2248 WILD_ORANGE = GotBlocks.registerBlockOnly("wild_orange", GotBlocks.wildCropBlock(class_3620.field_15987));
    public static final class_2248 WILD_TURNIP = GotBlocks.registerBlockOnly("wild_turnip", GotBlocks.wildCropBlock(class_3620.field_16004));
    public static final class_2248 WILD_CHAMPIGNON = GotBlocks.registerBlockOnly("wild_champignon", GotBlocks.wildCropBlock(class_3620.field_15977));
    public static final class_2248 GATE_MECHANISM = GotBlocks.registerBlockOnly("gate_mechanism", new GateMechanismBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_15996).method_9626(class_2498.field_11547).method_9629(1.5f, 6.0f).method_50012(class_3619.field_15972)));
    public static final class_2248 GATE_FENCE = GotBlocks.registerBlockOnly("gate_fence", new GateFenceBlock(class_4970.class_2251.method_9637().method_31710(class_3620.field_15996).method_9626(class_2498.field_11547).method_9629(18.0f, 1200.0f).method_22488().method_50012(class_3619.field_15972)));

    private GotBlocks() {
    }

    public static void initialize() {
        StrippableBlockRegistry.register((class_2248)WEIRWOOD_LOG, (class_2248)STRIPPED_WEIRWOOD_LOG);
        StrippableBlockRegistry.register((class_2248)WEIRWOOD_WOOD, (class_2248)STRIPPED_WEIRWOOD_WOOD);
        FlammableBlockRegistry flammability = FlammableBlockRegistry.getDefaultInstance();
        flammability.add(WEIRWOOD_LOG, 5, 5);
        flammability.add(WEIRWOOD_WOOD, 5, 5);
        flammability.add(WEIRWOOD_LOG_FACE, 5, 5);
        flammability.add(WEIRWOOD_LOG_FACE_BLOODY, 5, 5);
        flammability.add(STRIPPED_WEIRWOOD_LOG, 5, 5);
        flammability.add(STRIPPED_WEIRWOOD_WOOD, 5, 5);
        flammability.add(WEIRWOOD_PLANKS, 5, 20);
        flammability.add(WEIRWOOD_LEAVES, 30, 60);
        flammability.add(CHAIR_BLOCK, 5, 20);
        flammability.add(THRONE_BLOCK, 5, 20);
        flammability.add(TATCH, 30, 60);
        flammability.add(TATCH_STAIRS, 30, 60);
        flammability.add(TATCH_SLAB, 30, 60);
        flammability.add(TATCH_WALL, 30, 60);
        CompostingChanceRegistry.INSTANCE.add((class_1935)WEIRWOOD_LEAVES, (Object)Float.valueOf(0.3f));
        CompostingChanceRegistry.INSTANCE.add((class_1935)TATCH, (Object)Float.valueOf(0.3f));
        AttackBlockCallback.EVENT.register(GotBlocks::handleTentAttack);
        AttackBlockCallback.EVENT.register(GotBlocks::handleBlacksmithTableAttack);
        PlayerBlockBreakEvents.AFTER.register(GotBlocks::handleFlaxSeedGrassDrop);
        PlayerBlockBreakEvents.AFTER.register(GotBlocks::handleDiamondOreRuneFragmentDrop);
        ItemGroupEvents.modifyEntriesEvent((class_5321)class_7706.field_40197).register(entries -> {
            entries.method_45421((class_1935)ALTAR);
            entries.method_45421((class_1935)BREWER);
            entries.method_45421((class_1935)AGING_BARREL);
            entries.method_45421((class_1935)BLACKSMITH_TABLE);
            entries.method_45421((class_1935)BLACKSMITH_FURNACE);
            entries.method_45421((class_1935)MARKET_DISPLAY);
            entries.method_45421((class_1935)MARKET_DISPLAY_REINFORCED);
            entries.method_45421((class_1935)PLATE);
            entries.method_45421((class_1935)CUTTING_BOARD);
            entries.method_45421((class_1935)POT);
            entries.method_45421((class_1935)PAN);
            entries.method_45421((class_1935)STOVE);
            entries.method_45421((class_1935)DRAGON_CRADLE);
            entries.method_45421((class_1935)GATE_MECHANISM);
            entries.method_45421((class_1935)CHAIR_BLOCK);
            entries.method_45421((class_1935)THRONE_BLOCK);
        });
        ItemGroupEvents.modifyEntriesEvent((class_5321)class_7706.field_40195).register(entries -> {
            entries.method_45421((class_1935)POLISHED_WOOD);
            entries.method_45421((class_1935)TATCH);
            entries.method_45421((class_1935)TATCH_STAIRS);
            entries.method_45421((class_1935)TATCH_SLAB);
            entries.method_45421((class_1935)TATCH_WALL);
            GotBlocks.addStoneFamilies(arg_0 -> ((FabricItemGroupEntries)entries).method_45421(arg_0));
            entries.method_45421((class_1935)PLATE);
            entries.method_45421((class_1935)CUTTING_BOARD);
            entries.method_45421((class_1935)POT);
            entries.method_45421((class_1935)PAN);
            entries.method_45421((class_1935)STOVE);
            entries.method_45421((class_1935)CHAIR_BLOCK);
            entries.method_45421((class_1935)THRONE_BLOCK);
            entries.method_45421((class_1935)WEIRWOOD_LOG);
            entries.method_45421((class_1935)WEIRWOOD_WOOD);
            entries.method_45421((class_1935)WEIRWOOD_LOG_FACE);
            entries.method_45421((class_1935)WEIRWOOD_LOG_FACE_BLOODY);
            entries.method_45421((class_1935)STRIPPED_WEIRWOOD_LOG);
            entries.method_45421((class_1935)STRIPPED_WEIRWOOD_WOOD);
            entries.method_45421((class_1935)WEIRWOOD_PLANKS);
            entries.method_45421((class_1935)SILVER_BLOCK);
            entries.method_45421((class_1935)RAW_SILVER_BLOCK);
            entries.method_45421((class_1935)STEEL_BLOCK);
            entries.method_45421((class_1935)RAW_STEEL_BLOCK);
        });
        ItemGroupEvents.modifyEntriesEvent((class_5321)class_7706.field_40743).register(entries -> {
            entries.method_45421((class_1935)WEIRWOOD_LEAVES);
            entries.method_45421((class_1935)SILVER_ORE);
            entries.method_45421((class_1935)DEEPSLATE_SILVER_ORE);
            entries.method_45421((class_1935)DRAGON_EGG);
        });
    }

    public static void addStoneFamilies(Consumer<class_1935> entries) {
        entries.accept((class_1935)LIMESTONE);
        entries.accept((class_1935)LIMESTONE_STAIRS);
        entries.accept((class_1935)LIMESTONE_SLAB);
        entries.accept((class_1935)LIMESTONE_WALL);
        entries.accept((class_1935)COBBLED_LIMESTONE);
        entries.accept((class_1935)COBBLED_LIMESTONE_STAIRS);
        entries.accept((class_1935)COBBLED_LIMESTONE_SLAB);
        entries.accept((class_1935)COBBLED_LIMESTONE_WALL);
        entries.accept((class_1935)LIMESTONE_BRICKS);
        entries.accept((class_1935)LIMESTONE_BRICKS_STAIRS);
        entries.accept((class_1935)LIMESTONE_BRICKS_SLAB);
        entries.accept((class_1935)LIMESTONE_BRICKS_WALL);
        entries.accept((class_1935)LIMESTONE_TILES);
        entries.accept((class_1935)LIMESTONE_TILES_STAIRS);
        entries.accept((class_1935)LIMESTONE_TILES_SLAB);
        entries.accept((class_1935)LIMESTONE_TILES_WALL);
        entries.accept((class_1935)ASH_STONE);
        entries.accept((class_1935)ASH_STONE_STAIRS);
        entries.accept((class_1935)ASH_STONE_SLAB);
        entries.accept((class_1935)ASH_STONE_WALL);
        entries.accept((class_1935)COBBLED_ASH_STONE);
        entries.accept((class_1935)COBBLED_ASH_STONE_STAIRS);
        entries.accept((class_1935)COBBLED_ASH_STONE_SLAB);
        entries.accept((class_1935)COBBLED_ASH_STONE_WALL);
        entries.accept((class_1935)ASH_STONE_BRICKS);
        entries.accept((class_1935)ASH_STONE_BRICKS_STAIRS);
        entries.accept((class_1935)ASH_STONE_BRICKS_SLAB);
        entries.accept((class_1935)ASH_STONE_BRICKS_WALL);
        entries.accept((class_1935)ASH_STONE_TILES);
        entries.accept((class_1935)ASH_STONE_TILES_STAIRS);
        entries.accept((class_1935)ASH_STONE_TILES_SLAB);
        entries.accept((class_1935)ASH_STONE_TILES_WALL);
        entries.accept((class_1935)MARBLE);
        entries.accept((class_1935)MARBLE_STAIRS);
        entries.accept((class_1935)MARBLE_SLAB);
        entries.accept((class_1935)MARBLE_WALL);
        entries.accept((class_1935)COBBLED_MARBLE);
        entries.accept((class_1935)COBBLED_MARBLE_STAIRS);
        entries.accept((class_1935)COBBLED_MARBLE_SLAB);
        entries.accept((class_1935)COBBLED_MARBLE_WALL);
        entries.accept((class_1935)MARBLE_BRICKS);
        entries.accept((class_1935)MARBLE_BRICKS_STAIRS);
        entries.accept((class_1935)MARBLE_BRICKS_SLAB);
        entries.accept((class_1935)MARBLE_BRICKS_WALL);
        entries.accept((class_1935)MARBLE_TILES);
        entries.accept((class_1935)MARBLE_TILES_STAIRS);
        entries.accept((class_1935)MARBLE_TILES_SLAB);
        entries.accept((class_1935)MARBLE_TILES_WALL);
        entries.accept((class_1935)MOSSY_MARBLE);
        entries.accept((class_1935)MOSSY_MARBLE_STAIRS);
        entries.accept((class_1935)MOSSY_MARBLE_SLAB);
        entries.accept((class_1935)MOSSY_MARBLE_WALL);
        entries.accept((class_1935)COBBLED_MOSSY_MARBLE);
        entries.accept((class_1935)COBBLED_MOSSY_MARBLE_STAIRS);
        entries.accept((class_1935)COBBLED_MOSSY_MARBLE_SLAB);
        entries.accept((class_1935)COBBLED_MOSSY_MARBLE_WALL);
        entries.accept((class_1935)MOSSY_MARBLE_BRICKS);
        entries.accept((class_1935)MOSSY_MARBLE_BRICKS_STAIRS);
        entries.accept((class_1935)MOSSY_MARBLE_BRICKS_SLAB);
        entries.accept((class_1935)MOSSY_MARBLE_BRICKS_WALL);
        entries.accept((class_1935)MOSSY_MARBLE_TILES);
        entries.accept((class_1935)MOSSY_MARBLE_TILES_STAIRS);
        entries.accept((class_1935)MOSSY_MARBLE_TILES_SLAB);
        entries.accept((class_1935)MOSSY_MARBLE_TILES_WALL);
        entries.accept((class_1935)BLUESTONE);
        entries.accept((class_1935)BLUESTONE_STAIRS);
        entries.accept((class_1935)BLUESTONE_SLAB);
        entries.accept((class_1935)BLUESTONE_WALL);
        entries.accept((class_1935)COBBLED_BLUESTONE);
        entries.accept((class_1935)COBBLED_BLUESTONE_STAIRS);
        entries.accept((class_1935)COBBLED_BLUESTONE_SLAB);
        entries.accept((class_1935)COBBLED_BLUESTONE_WALL);
        entries.accept((class_1935)BLUESTONE_BRICKS);
        entries.accept((class_1935)BLUESTONE_BRICKS_STAIRS);
        entries.accept((class_1935)BLUESTONE_BRICKS_SLAB);
        entries.accept((class_1935)BLUESTONE_BRICKS_WALL);
        entries.accept((class_1935)BLUESTONE_TILES);
        entries.accept((class_1935)BLUESTONE_TILES_STAIRS);
        entries.accept((class_1935)BLUESTONE_TILES_SLAB);
        entries.accept((class_1935)BLUESTONE_TILES_WALL);
        entries.accept((class_1935)SILTSTONE);
        entries.accept((class_1935)SILTSTONE_STAIRS);
        entries.accept((class_1935)SILTSTONE_SLAB);
        entries.accept((class_1935)SILTSTONE_WALL);
        entries.accept((class_1935)COBBLED_SILTSTONE);
        entries.accept((class_1935)COBBLED_SILTSTONE_STAIRS);
        entries.accept((class_1935)COBBLED_SILTSTONE_SLAB);
        entries.accept((class_1935)COBBLED_SILTSTONE_WALL);
        entries.accept((class_1935)SILTSTONE_BRICKS);
        entries.accept((class_1935)SILTSTONE_BRICKS_STAIRS);
        entries.accept((class_1935)SILTSTONE_BRICKS_SLAB);
        entries.accept((class_1935)SILTSTONE_BRICKS_WALL);
        entries.accept((class_1935)SILTSTONE_TILES);
        entries.accept((class_1935)SILTSTONE_TILES_STAIRS);
        entries.accept((class_1935)SILTSTONE_TILES_SLAB);
        entries.accept((class_1935)SILTSTONE_TILES_WALL);
        entries.accept((class_1935)ENCHANTED_STONE);
        entries.accept((class_1935)ENCHANTED_STONE_STAIRS);
        entries.accept((class_1935)ENCHANTED_STONE_SLAB);
        entries.accept((class_1935)ENCHANTED_STONE_WALL);
        entries.accept((class_1935)COBBLED_ENCHANTED_STONE);
        entries.accept((class_1935)COBBLED_ENCHANTED_STONE_STAIRS);
        entries.accept((class_1935)COBBLED_ENCHANTED_STONE_SLAB);
        entries.accept((class_1935)COBBLED_ENCHANTED_STONE_WALL);
        entries.accept((class_1935)ENCHANTED_STONE_BRICKS);
        entries.accept((class_1935)ENCHANTED_STONE_BRICKS_STAIRS);
        entries.accept((class_1935)ENCHANTED_STONE_BRICKS_SLAB);
        entries.accept((class_1935)ENCHANTED_STONE_BRICKS_WALL);
        entries.accept((class_1935)ENCHANTED_STONE_TILES);
        entries.accept((class_1935)ENCHANTED_STONE_TILES_STAIRS);
        entries.accept((class_1935)ENCHANTED_STONE_TILES_SLAB);
        entries.accept((class_1935)ENCHANTED_STONE_TILES_WALL);
        entries.accept((class_1935)CHARRED_STONE);
        entries.accept((class_1935)CHARRED_STONE_STAIRS);
        entries.accept((class_1935)CHARRED_STONE_SLAB);
        entries.accept((class_1935)CHARRED_STONE_WALL);
        entries.accept((class_1935)COBBLED_CHARRED_STONE);
        entries.accept((class_1935)COBBLED_CHARRED_STONE_STAIRS);
        entries.accept((class_1935)COBBLED_CHARRED_STONE_SLAB);
        entries.accept((class_1935)COBBLED_CHARRED_STONE_WALL);
        entries.accept((class_1935)CHARRED_STONE_BRICKS);
        entries.accept((class_1935)CHARRED_STONE_BRICKS_STAIRS);
        entries.accept((class_1935)CHARRED_STONE_BRICKS_SLAB);
        entries.accept((class_1935)CHARRED_STONE_BRICKS_WALL);
        entries.accept((class_1935)CHARRED_STONE_TILES);
        entries.accept((class_1935)CHARRED_STONE_TILES_STAIRS);
        entries.accept((class_1935)CHARRED_STONE_TILES_SLAB);
        entries.accept((class_1935)CHARRED_STONE_TILES_WALL);
        entries.accept((class_1935)VOLCANIC_STONE);
        entries.accept((class_1935)VOLCANIC_STONE_STAIRS);
        entries.accept((class_1935)VOLCANIC_STONE_SLAB);
        entries.accept((class_1935)VOLCANIC_STONE_WALL);
        entries.accept((class_1935)COBBLED_VOLCANIC_STONE);
        entries.accept((class_1935)COBBLED_VOLCANIC_STONE_STAIRS);
        entries.accept((class_1935)COBBLED_VOLCANIC_STONE_SLAB);
        entries.accept((class_1935)COBBLED_VOLCANIC_STONE_WALL);
        entries.accept((class_1935)VOLCANIC_STONE_BRICKS);
        entries.accept((class_1935)VOLCANIC_STONE_BRICKS_STAIRS);
        entries.accept((class_1935)VOLCANIC_STONE_BRICKS_SLAB);
        entries.accept((class_1935)VOLCANIC_STONE_BRICKS_WALL);
        entries.accept((class_1935)VOLCANIC_STONE_TILES);
        entries.accept((class_1935)VOLCANIC_STONE_TILES_STAIRS);
        entries.accept((class_1935)VOLCANIC_STONE_TILES_SLAB);
        entries.accept((class_1935)VOLCANIC_STONE_TILES_WALL);
    }

    private static class_2248 register(String path, class_2248 block) {
        return GotBlocks.register(path, block, registeredBlock -> new class_1747(registeredBlock, new class_1792.class_1793()));
    }

    private static class_2248 stoneBlock(class_2248 source, class_3620 mapColor) {
        return new class_2248(class_4970.class_2251.method_9630((class_4970)source).method_31710(mapColor).method_29292().method_50012(class_3619.field_15972));
    }

    private static class_2248 tatchBlock() {
        return new class_2248(class_4970.class_2251.method_9630((class_4970)class_2246.field_10503).method_31710(class_3620.field_16010).method_9632(0.5f).method_50012(class_3619.field_15971));
    }

    private static class_2248 cropBlock(FourStageCropBlock.CropKind kind) {
        return new FourStageCropBlock(kind, class_4970.class_2251.method_9637().method_31710(class_3620.field_16004).method_9634().method_9640().method_9618().method_9626(class_2498.field_17580).method_22488().method_50012(class_3619.field_15971));
    }

    private static class_2248 wildCropBlock(class_3620 mapColor) {
        return new WildCropBlock(class_4970.class_2251.method_9637().method_31710(mapColor).method_9634().method_9618().method_9626(class_2498.field_17580).method_22488().method_50012(class_3619.field_15971));
    }

    private static class_2248 stairsBlock(class_2248 base) {
        return new StoneStairsBlock(base.method_9564(), class_4970.class_2251.method_9630((class_4970)base).method_50012(class_3619.field_15972));
    }

    private static class_2248 slabBlock(class_2248 base) {
        return new class_2482(class_4970.class_2251.method_9630((class_4970)base).method_50012(class_3619.field_15972));
    }

    private static class_2248 wallBlock(class_2248 base) {
        return new class_2544(class_4970.class_2251.method_9630((class_4970)base).method_50012(class_3619.field_15972));
    }

    private static class_2248 register(String path, class_2248 block, Function<class_2248, class_1792> itemFactory) {
        class_2960 id = class_2960.method_60655((String)"agob", (String)path);
        class_2378.method_10230((class_2378)class_7923.field_41175, (class_2960)id, (Object)block);
        class_2378.method_10230((class_2378)class_7923.field_41178, (class_2960)id, (Object)itemFactory.apply(block));
        return block;
    }

    private static class_2248 registerBlockOnly(String path, class_2248 block) {
        class_2378.method_10230((class_2378)class_7923.field_41175, (class_2960)class_2960.method_60655((String)"agob", (String)path), (Object)block);
        return block;
    }

    private static void handleFlaxSeedGrassDrop(class_1937 world, class_1657 player, class_2338 pos, class_2680 state, class_2586 blockEntity) {
        if (world.field_9236 || player.method_7337()) {
            return;
        }
        if (!GotBlocks.isFlaxSeedSource(state) || player.method_6047().method_31574(class_1802.field_8868)) {
            return;
        }
        if (world.field_9229.method_43048(8) != 0) {
            return;
        }
        class_2248.method_9577((class_1937)world, (class_2338)pos, (class_1799)new class_1799((class_1935)GotItems.FLAX_SEEDS));
    }

    private static void handleDiamondOreRuneFragmentDrop(class_1937 world, class_1657 player, class_2338 pos, class_2680 state, class_2586 blockEntity) {
        if (world.field_9236 || player.method_7337()) {
            return;
        }
        if (!state.method_26164(class_3481.field_28989) || world.field_9229.method_43057() >= 0.1f) {
            return;
        }
        class_2248.method_9577((class_1937)world, (class_2338)pos, (class_1799)new class_1799((class_1935)GotItems.RUNE_FRAGMENT));
    }

    private static boolean isFlaxSeedSource(class_2680 state) {
        if (state.method_27852(class_2246.field_10479)) {
            return true;
        }
        return state.method_27852(class_2246.field_10214) && state.method_11654((class_2769)class_2320.field_10929) == class_2756.field_12607;
    }

    private static class_1269 handleTentAttack(class_1657 player, class_1937 world, class_1268 hand, class_2338 pos, class_2350 direction) {
        class_2248 class_22482 = world.method_8320(pos).method_26204();
        if (!(class_22482 instanceof TentCollisionBlock)) {
            return class_1269.field_5811;
        }
        TentCollisionBlock tentBlock = (TentCollisionBlock)class_22482;
        if (!world.field_9236) {
            GotMod.LOGGER.info("[tent-break-debug] collision-hit player={} pos={} hand={} block={}", new Object[]{player.method_5477().getString(), pos.method_23854(), hand, world.method_8320(pos).method_26204()});
            if (player instanceof class_3222) {
                class_2338 origin;
                class_3218 serverWorld;
                class_3222 serverPlayer = (class_3222)player;
                if (world instanceof class_3218 && !GotCastles.tryBreakSettlementTent(serverWorld = (class_3218)world, serverPlayer, origin = tentBlock.getOriginForScreen(pos, world.method_8320(pos)))) {
                    GotMod.LOGGER.info("[tent-break-debug] collision-hit blocked origin={} reason=claim_protection", (Object)origin.method_23854());
                    return class_1269.field_5814;
                }
            }
            GotMod.LOGGER.info("[tent-break-debug] collision-hit destroying origin={}", (Object)tentBlock.getOriginForScreen(pos, world.method_8320(pos)).method_23854());
            tentBlock.destroyStructure(world, pos, world.method_8320(pos), !player.method_7337());
        }
        return class_1269.field_5812;
    }

    private static class_1269 handleBlacksmithTableAttack(class_1657 player, class_1937 world, class_1268 hand, class_2338 pos, class_2350 direction) {
        if (!(world instanceof class_3218)) {
            return class_1269.field_5811;
        }
        class_3218 serverWorld = (class_3218)world;
        if (!(player instanceof class_3222)) {
            return class_1269.field_5811;
        }
        class_3222 serverPlayer = (class_3222)player;
        class_2680 state = world.method_8320(pos);
        if (!(state.method_26204() instanceof BlacksmithTableBlock) || !BlacksmithHammerRules.isHammer(serverPlayer.method_6047())) {
            return class_1269.field_5811;
        }
        BlacksmithWorkbenchAccess.WorkbenchPair pair = BlacksmithWorkbenchAccess.findPair(world, pos, state);
        if (pair == null || !BlacksmithCrafting.handleHammerHit(serverPlayer, serverWorld, pair)) {
            return class_1269.field_5811;
        }
        return class_1269.field_5812;
    }
}

