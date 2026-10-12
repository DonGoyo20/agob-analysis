/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1263
 *  net.minecraft.class_1264
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1750
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
 *  net.minecraft.class_2591
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2741
 *  net.minecraft.class_2769
 *  net.minecraft.class_3218
 *  net.minecraft.class_3726
 *  net.minecraft.class_3908
 *  net.minecraft.class_3965
 *  net.minecraft.class_4970$class_2251
 *  net.minecraft.class_5558
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1263;
import net.minecraft.class_1264;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
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
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2741;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3726;
import net.minecraft.class_3908;
import net.minecraft.class_3965;
import net.minecraft.class_4970;
import net.minecraft.class_5558;
import org.jetbrains.annotations.Nullable;
import trep.got.block.entity.BrewerBlockEntity;
import trep.got.registry.GotBlockEntities;

public final class BrewerBlock
extends class_2383
implements class_2343 {
    public static final MapCodec<BrewerBlock> CODEC = BrewerBlock.method_54094(BrewerBlock::new);
    private static final class_265 SHAPE = class_259.method_17786((class_265)class_2248.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)1.0, (double)16.0), (class_265[])new class_265[]{class_2248.method_9541((double)0.0, (double)15.0, (double)0.0, (double)16.0, (double)16.0, (double)16.0), class_2248.method_9541((double)1.0, (double)1.0, (double)1.0, (double)15.0, (double)15.0, (double)15.0), class_2248.method_9541((double)4.0, (double)5.0, (double)0.0, (double)12.0, (double)11.0, (double)2.0)});

    public BrewerBlock(class_4970.class_2251 settings) {
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
        return SHAPE;
    }

    protected class_1269 method_55766(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit) {
        class_3908 namedFactory;
        class_3908 factory;
        if (world.field_9236) {
            return class_1269.field_5812;
        }
        class_2586 class_25862 = world.method_8321(pos);
        class_3908 class_39082 = factory = class_25862 instanceof class_3908 ? (namedFactory = (class_3908)class_25862) : null;
        if (factory != null) {
            player.method_17355(factory);
        }
        return class_1269.field_21466;
    }

    @Nullable
    public class_2586 method_10123(class_2338 pos, class_2680 state) {
        return new BrewerBlockEntity(pos, state);
    }

    @Nullable
    public <T extends class_2586> class_5558<T> method_31645(class_1937 world, class_2680 state, class_2591<T> type) {
        if (world.field_9236 || type != GotBlockEntities.BREWER) {
            return null;
        }
        return (tickWorld, tickPos, tickState, blockEntity) -> BrewerBlockEntity.tick((class_3218)tickWorld, tickPos, tickState, (BrewerBlockEntity)blockEntity);
    }

    protected void method_9536(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved) {
        if (!state.method_27852(newState.method_26204())) {
            class_2586 blockEntity = world.method_8321(pos);
            if (blockEntity instanceof BrewerBlockEntity) {
                BrewerBlockEntity brewer = (BrewerBlockEntity)blockEntity;
                class_1264.method_5451((class_1937)world, (class_2338)pos, (class_1263)brewer);
            }
            super.method_9536(state, world, pos, newState, moved);
        }
    }
}

