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
 *  net.minecraft.class_1937
 *  net.minecraft.class_2237
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2586
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2746
 *  net.minecraft.class_2769
 *  net.minecraft.class_3908
 *  net.minecraft.class_3965
 *  net.minecraft.class_4970$class_2251
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
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2746;
import net.minecraft.class_2769;
import net.minecraft.class_3908;
import net.minecraft.class_3965;
import net.minecraft.class_4970;
import net.minecraft.class_9062;
import org.jetbrains.annotations.Nullable;
import trep.got.block.KitchenwareBlock;

public final class PotBlock
extends KitchenwareBlock {
    public static final MapCodec<PotBlock> CODEC = PotBlock.method_54094(PotBlock::new);
    public static final class_2746 ACTIVE = class_2746.method_11825((String)"active");
    private static final class_265 SHAPE = class_259.method_17786((class_265)class_2248.method_9541((double)2.0, (double)0.0, (double)2.0, (double)14.0, (double)10.0, (double)14.0), (class_265[])new class_265[]{class_2248.method_9541((double)-1.0, (double)8.0, (double)4.0, (double)2.0, (double)8.5, (double)12.0), class_2248.method_9541((double)14.0, (double)8.0, (double)4.0, (double)17.0, (double)8.5, (double)12.0)});

    public PotBlock(class_4970.class_2251 settings) {
        super(settings, 4, SHAPE);
        this.method_9590((class_2680)this.method_9564().method_11657((class_2769)ACTIVE, (Comparable)Boolean.valueOf(false)));
    }

    protected MapCodec<? extends class_2237> method_53969() {
        return CODEC;
    }

    @Override
    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        super.method_9515(builder);
        builder.method_11667(new class_2769[]{ACTIVE});
    }

    @Override
    @Nullable
    public class_2680 method_9605(class_1750 ctx) {
        class_2680 state = super.method_9605(ctx);
        return state == null ? null : (class_2680)state.method_11657((class_2769)ACTIVE, (Comparable)Boolean.valueOf(false));
    }

    @Override
    protected class_9062 method_55765(class_1799 stack, class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit) {
        return class_9062.field_47731;
    }

    @Override
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
}

