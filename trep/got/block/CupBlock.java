/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1264
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1750
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2343
 *  net.minecraft.class_2350
 *  net.minecraft.class_2383
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_2586
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2746
 *  net.minecraft.class_2769
 *  net.minecraft.class_3726
 *  net.minecraft.class_3965
 *  net.minecraft.class_4538
 *  net.minecraft.class_4970$class_2251
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1264;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2343;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2746;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_4538;
import net.minecraft.class_4970;
import org.jetbrains.annotations.Nullable;
import trep.got.block.entity.CupBlockEntity;
import trep.got.item.BrewItem;

public final class CupBlock
extends class_2383
implements class_2343 {
    public static final MapCodec<CupBlock> CODEC = CupBlock.method_54094(CupBlock::new);
    public static final class_2746 FILLED = class_2746.method_11825((String)"filled");
    public static final class_265 SHORT_SHAPE = class_259.method_17786((class_265)class_2248.method_9541((double)6.0, (double)0.0, (double)6.0, (double)10.0, (double)1.0, (double)10.0), (class_265[])new class_265[]{class_2248.method_9541((double)6.0, (double)1.0, (double)6.0, (double)10.0, (double)5.0, (double)10.0), class_2248.method_9541((double)8.0, (double)0.0, (double)10.0, (double)8.0, (double)5.0, (double)13.0)});
    public static final class_265 TALL_SHAPE = class_259.method_17786((class_265)class_2248.method_9541((double)6.0, (double)0.0, (double)6.0, (double)10.0, (double)1.0, (double)10.0), (class_265[])new class_265[]{class_2248.method_9541((double)6.0, (double)1.0, (double)6.0, (double)10.0, (double)8.0, (double)10.0), class_2248.method_9541((double)8.0, (double)0.0, (double)10.0, (double)8.0, (double)5.0, (double)13.0)});
    private final class_265 shape;

    public CupBlock(class_4970.class_2251 settings) {
        this(settings, SHORT_SHAPE);
    }

    public CupBlock(class_4970.class_2251 settings, class_265 shape) {
        super(settings);
        this.shape = shape;
        this.method_9590((class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657((class_2769)field_11177, (Comparable)class_2350.field_11043)).method_11657((class_2769)FILLED, (Comparable)Boolean.valueOf(false)));
    }

    protected MapCodec<? extends class_2383> method_53969() {
        return CODEC;
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{field_11177, FILLED});
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
        return this.shape;
    }

    protected class_1269 method_55766(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit) {
        if (!((Boolean)state.method_11654((class_2769)FILLED)).booleanValue()) {
            return class_1269.field_5811;
        }
        class_2586 blockEntity = world.method_8321(pos);
        if (!(blockEntity instanceof CupBlockEntity)) {
            return class_1269.field_5811;
        }
        CupBlockEntity cupBlockEntity = (CupBlockEntity)blockEntity;
        class_1799 storedStack = cupBlockEntity.getStoredStack();
        class_1792 class_17922 = storedStack.method_7909();
        if (!(class_17922 instanceof BrewItem)) {
            return class_1269.field_5811;
        }
        BrewItem brewItem = (BrewItem)class_17922;
        if (world.field_9236) {
            return class_1269.field_5812;
        }
        class_1799 emptiedCup = brewItem.consumePlacedCup(world, player, storedStack);
        world.method_8652(pos, (class_2680)state.method_11657((class_2769)FILLED, (Comparable)Boolean.valueOf(false)), 3);
        cupBlockEntity.setStoredStack(emptiedCup, -1);
        return class_1269.field_21466;
    }

    @Nullable
    public class_2586 method_10123(class_2338 pos, class_2680 state) {
        return new CupBlockEntity(pos, state);
    }

    public boolean method_9558(class_2680 state, class_4538 world, class_2338 pos) {
        return class_2248.method_20044((class_4538)world, (class_2338)pos.method_10074(), (class_2350)class_2350.field_11036);
    }

    protected void method_9536(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved) {
        if (!state.method_27852(newState.method_26204())) {
            CupBlockEntity cup;
            class_2586 blockEntity = world.method_8321(pos);
            if (blockEntity instanceof CupBlockEntity && !(cup = (CupBlockEntity)blockEntity).getStoredStack().method_7960()) {
                class_1264.method_5449((class_1937)world, (double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260(), (class_1799)cup.getStoredStack());
            }
            super.method_9536(state, world, pos, newState, moved);
        }
    }
}

