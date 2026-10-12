/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1263
 *  net.minecraft.class_1264
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1750
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2237
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2383
 *  net.minecraft.class_2415
 *  net.minecraft.class_2464
 *  net.minecraft.class_2470
 *  net.minecraft.class_2586
 *  net.minecraft.class_2591
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2753
 *  net.minecraft.class_2769
 *  net.minecraft.class_3726
 *  net.minecraft.class_3965
 *  net.minecraft.class_4970$class_2251
 *  net.minecraft.class_5558
 *  net.minecraft.class_9062
 *  net.minecraft.class_9334
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import net.minecraft.class_1263;
import net.minecraft.class_1264;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2415;
import net.minecraft.class_2464;
import net.minecraft.class_2470;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_4970;
import net.minecraft.class_5558;
import net.minecraft.class_9062;
import net.minecraft.class_9334;
import org.jetbrains.annotations.Nullable;
import trep.got.block.StoveBlock;
import trep.got.block.entity.KitchenwareBlockEntity;
import trep.got.registry.GotBlockEntities;
import trep.got.registry.GotBlocks;

public abstract class KitchenwareBlock
extends class_2237 {
    public static final class_2753 FACING = class_2383.field_11177;
    private final int capacity;
    private final class_265 shape;

    protected KitchenwareBlock(class_4970.class_2251 settings, int capacity, class_265 shape) {
        super(settings);
        this.capacity = capacity;
        this.shape = shape;
        this.method_9590((class_2680)((class_2680)this.field_10647.method_11664()).method_11657((class_2769)FACING, (Comparable)class_2350.field_11043));
    }

    public final int capacity() {
        return this.capacity;
    }

    @Nullable
    public class_2586 method_10123(class_2338 pos, class_2680 state) {
        return new KitchenwareBlockEntity(pos, state);
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{FACING});
    }

    @Nullable
    public class_2680 method_9605(class_1750 ctx) {
        return (class_2680)this.method_9564().method_11657((class_2769)FACING, (Comparable)ctx.method_8042().method_10153());
    }

    protected class_9062 method_55765(class_1799 stack, class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit) {
        KitchenwareBlockEntity kitchenware;
        if (!KitchenwareBlock.isAcceptedIngredient(stack)) {
            return class_9062.field_47731;
        }
        class_2586 class_25862 = world.method_8321(pos);
        if (!(class_25862 instanceof KitchenwareBlockEntity) || !(kitchenware = (KitchenwareBlockEntity)class_25862).canAddIngredient(this.capacity)) {
            return class_9062.field_47731;
        }
        if (!kitchenware.canAcceptIngredient(state, stack)) {
            return class_9062.field_47731;
        }
        if (!world.field_9236) {
            class_1799 placed = stack.method_46651(1);
            kitchenware.addIngredient(placed, this.capacity);
            if (!player.method_7337()) {
                stack.method_7934(1);
            }
        }
        return class_9062.field_47728;
    }

    protected class_1269 method_55766(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit) {
        class_1799 stack;
        KitchenwareBlockEntity kitchenware;
        class_2586 class_25862 = world.method_8321(pos);
        if (!(class_25862 instanceof KitchenwareBlockEntity) || (kitchenware = (KitchenwareBlockEntity)class_25862).method_5442()) {
            return class_1269.field_5811;
        }
        if (!(world.field_9236 || (stack = kitchenware.takeLastIngredient()).method_7960() || player.method_31548().method_7394(stack))) {
            class_1264.method_5449((class_1937)world, (double)((double)pos.method_10263() + 0.5), (double)((double)pos.method_10264() + 0.5), (double)((double)pos.method_10260() + 0.5), (class_1799)stack);
        }
        return class_1269.field_5812;
    }

    protected void method_9536(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved) {
        if (!state.method_27852(newState.method_26204())) {
            class_2586 class_25862 = world.method_8321(pos);
            if (class_25862 instanceof KitchenwareBlockEntity) {
                KitchenwareBlockEntity kitchenware = (KitchenwareBlockEntity)class_25862;
                class_1264.method_5451((class_1937)world, (class_2338)pos, (class_1263)kitchenware);
            }
            StoveBlock.refreshAt(world, pos.method_10074());
        }
        super.method_9536(state, world, pos, newState, moved);
    }

    public void method_9567(class_1937 world, class_2338 pos, class_2680 state, @Nullable class_1309 placer, class_1799 itemStack) {
        super.method_9567(world, pos, state, placer, itemStack);
        StoveBlock.refreshAt(world, pos.method_10074());
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return this.shape;
    }

    protected class_2464 method_9604(class_2680 state) {
        return class_2464.field_11458;
    }

    protected class_2680 method_9598(class_2680 state, class_2470 rotation) {
        return (class_2680)state.method_11657((class_2769)FACING, (Comparable)rotation.method_10503((class_2350)state.method_11654((class_2769)FACING)));
    }

    protected class_2680 method_9569(class_2680 state, class_2415 mirror) {
        return state.method_26186(mirror.method_10345((class_2350)state.method_11654((class_2769)FACING)));
    }

    public static boolean isCookware(class_2680 state) {
        return state.method_27852(GotBlocks.POT) || state.method_27852(GotBlocks.PAN);
    }

    @Nullable
    public <T extends class_2586> class_5558<T> method_31645(class_1937 world, class_2680 state, class_2591<T> type) {
        return world.field_9236 ? null : KitchenwareBlock.method_31618(type, GotBlockEntities.KITCHENWARE, KitchenwareBlockEntity::tick);
    }

    private static boolean isAcceptedIngredient(class_1799 stack) {
        return !stack.method_7960() && stack.method_57824(class_9334.field_50075) != null;
    }
}

