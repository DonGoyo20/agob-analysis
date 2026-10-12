/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1750
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1922
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2237
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2383
 *  net.minecraft.class_2415
 *  net.minecraft.class_2464
 *  net.minecraft.class_2470
 *  net.minecraft.class_2561
 *  net.minecraft.class_2586
 *  net.minecraft.class_2591
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2746
 *  net.minecraft.class_2753
 *  net.minecraft.class_2769
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3726
 *  net.minecraft.class_3922
 *  net.minecraft.class_3965
 *  net.minecraft.class_4970$class_2251
 *  net.minecraft.class_5558
 *  net.minecraft.class_5819
 *  net.minecraft.class_9062
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2415;
import net.minecraft.class_2464;
import net.minecraft.class_2470;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3726;
import net.minecraft.class_3922;
import net.minecraft.class_3965;
import net.minecraft.class_4970;
import net.minecraft.class_5558;
import net.minecraft.class_5819;
import net.minecraft.class_9062;
import org.jetbrains.annotations.Nullable;
import trep.got.block.entity.DragonCradleBlockEntity;
import trep.got.entity.dragon.DragonBondWorldState;
import trep.got.registry.GotBlockEntities;
import trep.got.registry.GotBlocks;

public final class DragonCradleBlock
extends class_2237 {
    public static final MapCodec<DragonCradleBlock> CODEC = DragonCradleBlock.method_54094(DragonCradleBlock::new);
    public static final class_2753 FACING = class_2383.field_11177;
    public static final class_2746 ACTIVATED = class_2746.method_11825((String)"activated");
    public static final class_2746 HAS_EGG = class_2746.method_11825((String)"has_egg");
    private static final class_265 SHAPE = class_2248.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)10.0, (double)16.0);

    public DragonCradleBlock(class_4970.class_2251 settings) {
        super(settings);
        this.method_9590((class_2680)((class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657((class_2769)FACING, (Comparable)class_2350.field_11043)).method_11657((class_2769)ACTIVATED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)HAS_EGG, (Comparable)Boolean.valueOf(false)));
    }

    protected MapCodec<? extends class_2237> method_53969() {
        return CODEC;
    }

    @Nullable
    public class_2586 method_10123(class_2338 pos, class_2680 state) {
        return new DragonCradleBlockEntity(pos, state);
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{FACING, ACTIVATED, HAS_EGG});
    }

    public class_2680 method_9605(class_1750 ctx) {
        return (class_2680)this.method_9564().method_11657((class_2769)FACING, (Comparable)ctx.method_8042().method_10153());
    }

    protected class_9062 method_55765(class_1799 stack, class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit) {
        if (stack.method_31574(class_1802.field_17346) && !((Boolean)state.method_11654((class_2769)ACTIVATED)).booleanValue()) {
            if (!world.field_9236) {
                world.method_8652(pos, (class_2680)state.method_11657((class_2769)ACTIVATED, (Comparable)Boolean.valueOf(true)), 3);
                if (!player.method_7337()) {
                    stack.method_7934(1);
                }
            }
            return class_9062.field_47728;
        }
        if (stack.method_31574(GotBlocks.DRAGON_EGG.method_8389())) {
            return this.handleEggInteraction(state, world, pos, player, stack);
        }
        return class_9062.field_47731;
    }

    protected class_1269 method_55766(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit) {
        if (!((Boolean)state.method_11654((class_2769)HAS_EGG)).booleanValue()) {
            return class_1269.field_5811;
        }
        if (!world.field_9236) {
            this.takeEgg(state, world, pos, player);
        }
        return class_1269.field_5812;
    }

    private class_9062 handleEggInteraction(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_1799 heldStack) {
        class_3218 serverWorld;
        if (((Boolean)state.method_11654((class_2769)HAS_EGG)).booleanValue()) {
            if (!world.field_9236) {
                this.takeEgg(state, world, pos, player);
            }
            return class_9062.field_47728;
        }
        class_2586 class_25862 = world.method_8321(pos);
        if (!(class_25862 instanceof DragonCradleBlockEntity)) {
            return class_9062.field_47733;
        }
        DragonCradleBlockEntity cradle = (DragonCradleBlockEntity)class_25862;
        if (!world.field_9236 && world instanceof class_3218 && DragonBondWorldState.get(serverWorld = (class_3218)world).hasLivingDragon(serverWorld, player.method_5667())) {
            if (player instanceof class_3222) {
                class_3222 serverPlayer = (class_3222)player;
                serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"Player Already has Dragon"), true);
            }
            return class_9062.field_47728;
        }
        if (!world.field_9236) {
            cradle.setEgg(heldStack, player.method_5667(), world.method_8510());
            world.method_8652(pos, (class_2680)state.method_11657((class_2769)HAS_EGG, (Comparable)Boolean.valueOf(true)), 3);
            if (!player.method_7337()) {
                heldStack.method_7934(1);
            }
        }
        return class_9062.field_47728;
    }

    private void takeEgg(class_2680 state, class_1937 world, class_2338 pos, class_1657 player) {
        class_2586 class_25862 = world.method_8321(pos);
        if (!(class_25862 instanceof DragonCradleBlockEntity)) {
            return;
        }
        DragonCradleBlockEntity cradle = (DragonCradleBlockEntity)class_25862;
        class_1799 egg = cradle.takeEgg();
        if (egg.method_7960()) {
            return;
        }
        world.method_8652(pos, (class_2680)state.method_11657((class_2769)HAS_EGG, (Comparable)Boolean.valueOf(false)), 3);
        if (!player.method_31548().method_7394(egg)) {
            player.method_7328(egg, false);
        }
    }

    public void method_9556(class_1937 world, class_1657 player, class_2338 pos, class_2680 state, @Nullable class_2586 blockEntity, class_1799 tool) {
        if (!world.field_9236 && !player.method_7337()) {
            DragonCradleBlockEntity cradle;
            class_2248.method_9577((class_1937)world, (class_2338)pos, (class_1799)new class_1799((class_1935)GotBlocks.DRAGON_CRADLE));
            if (((Boolean)state.method_11654((class_2769)ACTIVATED)).booleanValue()) {
                class_2248.method_9577((class_1937)world, (class_2338)pos, (class_1799)new class_1799((class_1935)class_1802.field_17346));
            }
            if (blockEntity instanceof DragonCradleBlockEntity && (cradle = (DragonCradleBlockEntity)blockEntity).hasEgg()) {
                class_2248.method_9577((class_1937)world, (class_2338)pos, (class_1799)cradle.getEggStack().method_7972());
            }
        }
        super.method_9556(world, player, pos, state, blockEntity, tool);
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return SHAPE;
    }

    protected class_2464 method_9604(class_2680 state) {
        return class_2464.field_11458;
    }

    public void method_9496(class_2680 state, class_1937 world, class_2338 pos, class_5819 random) {
        if (((Boolean)state.method_11654((class_2769)ACTIVATED)).booleanValue()) {
            class_3922.method_17455((class_1937)world, (class_2338)pos, (boolean)true, (boolean)true);
        }
    }

    protected class_2680 method_9598(class_2680 state, class_2470 rotation) {
        return (class_2680)state.method_11657((class_2769)FACING, (Comparable)rotation.method_10503((class_2350)state.method_11654((class_2769)FACING)));
    }

    protected class_2680 method_9569(class_2680 state, class_2415 mirror) {
        return state.method_26186(mirror.method_10345((class_2350)state.method_11654((class_2769)FACING)));
    }

    @Nullable
    public <T extends class_2586> class_5558<T> method_31645(class_1937 world, class_2680 state, class_2591<T> type) {
        return world.field_9236 ? null : DragonCradleBlock.method_31618(type, GotBlockEntities.DRAGON_CRADLE, DragonCradleBlockEntity::tick);
    }
}

