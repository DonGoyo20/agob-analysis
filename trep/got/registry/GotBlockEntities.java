/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
 *  net.minecraft.class_2378
 *  net.minecraft.class_2591
 *  net.minecraft.class_2591$class_2592
 *  net.minecraft.class_2960
 *  net.minecraft.class_7923
 */
package trep.got.registry;

import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2591;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import trep.got.block.entity.AgingBarrelBlockEntity;
import trep.got.block.entity.BlacksmithFurnaceBlockEntity;
import trep.got.block.entity.BlacksmithTableBlockEntity;
import trep.got.block.entity.BrewerBlockEntity;
import trep.got.block.entity.CupBlockEntity;
import trep.got.block.entity.DragonCradleBlockEntity;
import trep.got.block.entity.DragonEggBlockEntity;
import trep.got.block.entity.KitchenwareBlockEntity;
import trep.got.block.entity.MarketDisplayBlockEntity;
import trep.got.block.entity.TentCollisionBlockEntity;
import trep.got.registry.GotBlocks;

public final class GotBlockEntities {
    public static final class_2591<BrewerBlockEntity> BREWER = (class_2591)class_2378.method_10230((class_2378)class_7923.field_41181, (class_2960)class_2960.method_60655((String)"agob", (String)"brewer"), (Object)class_2591.class_2592.method_20528(BrewerBlockEntity::new, (class_2248[])new class_2248[]{GotBlocks.BREWER}).build());
    public static final class_2591<AgingBarrelBlockEntity> AGING_BARREL = (class_2591)class_2378.method_10230((class_2378)class_7923.field_41181, (class_2960)class_2960.method_60655((String)"agob", (String)"aging_barrel"), (Object)class_2591.class_2592.method_20528(AgingBarrelBlockEntity::new, (class_2248[])new class_2248[]{GotBlocks.AGING_BARREL}).build());
    public static final class_2591<BlacksmithTableBlockEntity> BLACKSMITH_TABLE = (class_2591)class_2378.method_10230((class_2378)class_7923.field_41181, (class_2960)class_2960.method_60655((String)"agob", (String)"blacksmith_table"), (Object)class_2591.class_2592.method_20528(BlacksmithTableBlockEntity::new, (class_2248[])new class_2248[]{GotBlocks.BLACKSMITH_TABLE}).build());
    public static final class_2591<BlacksmithFurnaceBlockEntity> BLACKSMITH_FURNACE = (class_2591)class_2378.method_10230((class_2378)class_7923.field_41181, (class_2960)class_2960.method_60655((String)"agob", (String)"blacksmith_furnace"), (Object)class_2591.class_2592.method_20528(BlacksmithFurnaceBlockEntity::new, (class_2248[])new class_2248[]{GotBlocks.BLACKSMITH_FURNACE}).build());
    public static final class_2591<TentCollisionBlockEntity> TENT_COLLISION = (class_2591)class_2378.method_10230((class_2378)class_7923.field_41181, (class_2960)class_2960.method_60655((String)"agob", (String)"tent_collision"), (Object)class_2591.class_2592.method_20528(TentCollisionBlockEntity::new, (class_2248[])new class_2248[]{GotBlocks.TENT_COLLISION}).build());
    public static final class_2591<CupBlockEntity> CUP = (class_2591)class_2378.method_10230((class_2378)class_7923.field_41181, (class_2960)class_2960.method_60655((String)"agob", (String)"cup"), (Object)class_2591.class_2592.method_20528(CupBlockEntity::new, (class_2248[])new class_2248[]{GotBlocks.CUP_BLOCK, GotBlocks.SILVER_CUP_BLOCK}).build());
    public static final class_2591<DragonEggBlockEntity> DRAGON_EGG = (class_2591)class_2378.method_10230((class_2378)class_7923.field_41181, (class_2960)class_2960.method_60655((String)"agob", (String)"dragon_egg"), (Object)class_2591.class_2592.method_20528(DragonEggBlockEntity::new, (class_2248[])new class_2248[]{GotBlocks.DRAGON_EGG}).build());
    public static final class_2591<DragonCradleBlockEntity> DRAGON_CRADLE = (class_2591)class_2378.method_10230((class_2378)class_7923.field_41181, (class_2960)class_2960.method_60655((String)"agob", (String)"dragon_cradle"), (Object)class_2591.class_2592.method_20528(DragonCradleBlockEntity::new, (class_2248[])new class_2248[]{GotBlocks.DRAGON_CRADLE}).build());
    public static final class_2591<MarketDisplayBlockEntity> MARKET_DISPLAY = (class_2591)class_2378.method_10230((class_2378)class_7923.field_41181, (class_2960)class_2960.method_60655((String)"agob", (String)"market_display"), (Object)class_2591.class_2592.method_20528(MarketDisplayBlockEntity::new, (class_2248[])new class_2248[]{GotBlocks.MARKET_DISPLAY, GotBlocks.MARKET_DISPLAY_REINFORCED}).build());
    public static final class_2591<KitchenwareBlockEntity> KITCHENWARE = (class_2591)class_2378.method_10230((class_2378)class_7923.field_41181, (class_2960)class_2960.method_60655((String)"agob", (String)"kitchenware"), (Object)class_2591.class_2592.method_20528(KitchenwareBlockEntity::new, (class_2248[])new class_2248[]{GotBlocks.PLATE, GotBlocks.CUTTING_BOARD, GotBlocks.POT, GotBlocks.PAN}).build());

    private GotBlockEntities() {
    }

    public static void initialize() {
    }
}

