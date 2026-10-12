/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1750
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2383
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_2561
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2741
 *  net.minecraft.class_2769
 *  net.minecraft.class_3222
 *  net.minecraft.class_3715
 *  net.minecraft.class_3726
 *  net.minecraft.class_3908
 *  net.minecraft.class_3914
 *  net.minecraft.class_3965
 *  net.minecraft.class_4970$class_2251
 *  net.minecraft.class_747
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1750;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2561;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2741;
import net.minecraft.class_2769;
import net.minecraft.class_3222;
import net.minecraft.class_3715;
import net.minecraft.class_3726;
import net.minecraft.class_3908;
import net.minecraft.class_3914;
import net.minecraft.class_3965;
import net.minecraft.class_4970;
import net.minecraft.class_747;
import org.jetbrains.annotations.Nullable;
import trep.got.recipe.RuneCraftingContext;
import trep.got.screen.AltarCraftingScreenHandler;

public final class AltarBlock
extends class_2383 {
    public static final MapCodec<AltarBlock> CODEC = AltarBlock.method_54094(AltarBlock::new);

    public AltarBlock(class_4970.class_2251 settings) {
        super(settings);
        this.method_9590((class_2680)((class_2680)this.field_10647.method_11664()).method_11657((class_2769)field_11177, (Comparable)class_2350.field_11043));
    }

    protected MapCodec<? extends class_2383> method_53969() {
        return CODEC;
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{class_2741.field_12481});
    }

    @Nullable
    public class_2680 method_9605(class_1750 ctx) {
        return (class_2680)this.method_9564().method_11657((class_2769)field_11177, (Comparable)ctx.method_8042().method_10153());
    }

    protected class_2680 method_9598(class_2680 state, class_2470 rotation) {
        return (class_2680)state.method_11657((class_2769)field_11177, (Comparable)rotation.method_10503((class_2350)state.method_11654((class_2769)field_11177)));
    }

    protected class_2680 method_9569(class_2680 state, class_2415 mirror) {
        return state.method_26186(mirror.method_10345((class_2350)state.method_11654((class_2769)field_11177)));
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return AltarBlock.getLecternState(state).method_26172(world, pos, context);
    }

    protected class_265 method_9549(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return AltarBlock.getLecternState(state).method_26194(world, pos, context);
    }

    protected class_1269 method_55766(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit) {
        if (world.field_9236) {
            return class_1269.field_5812;
        }
        class_3908 factory = this.method_17454(state, world, pos);
        if (factory != null) {
            player.method_17355(factory);
        }
        return class_1269.field_21466;
    }

    protected class_3908 method_17454(class_2680 state, class_1937 world, class_2338 pos) {
        return new class_747((syncId, playerInventory, player) -> AltarBlock.createMenu(syncId, playerInventory, world, pos, player), (class_2561)class_2561.method_43471((String)"container.agob.altar"));
    }

    private static class_1703 createMenu(int syncId, class_1661 playerInventory, class_1937 world, class_2338 pos, class_1657 player) {
        AltarCraftingScreenHandler handler = new AltarCraftingScreenHandler(syncId, playerInventory, class_3914.method_17392((class_1937)world, (class_2338)pos));
        if (player instanceof class_3222) {
            RuneCraftingContext.markAltarHandler((class_1703)handler);
        }
        return handler;
    }

    private static class_2680 getLecternState(class_2680 state) {
        return (class_2680)((class_2680)class_2246.field_16330.method_9564().method_11657((class_2769)class_3715.field_16404, (Comparable)((class_2350)state.method_11654((class_2769)field_11177)))).method_11657((class_2769)class_3715.field_17366, (Comparable)Boolean.valueOf(false));
    }
}

