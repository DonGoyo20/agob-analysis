/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1309
 *  net.minecraft.class_1750
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2383
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2746
 *  net.minecraft.class_2769
 *  net.minecraft.class_3726
 *  net.minecraft.class_4970$class_2251
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1309;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2746;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_4970;
import org.jetbrains.annotations.Nullable;
import trep.got.block.KitchenwareBlock;

public final class StoveBlock
extends class_2383 {
    public static final MapCodec<StoveBlock> CODEC = StoveBlock.method_54094(StoveBlock::new);
    public static final class_2746 ACTIVE = class_2746.method_11825((String)"active");
    private static final class_265 SHAPE = class_2248.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)14.0, (double)16.0);

    public StoveBlock(class_4970.class_2251 settings) {
        super(settings);
        this.method_9590((class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657((class_2769)field_11177, (Comparable)class_2350.field_11043)).method_11657((class_2769)ACTIVE, (Comparable)Boolean.valueOf(false)));
    }

    protected MapCodec<? extends class_2383> method_53969() {
        return CODEC;
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{field_11177, ACTIVE});
    }

    @Nullable
    public class_2680 method_9605(class_1750 ctx) {
        return (class_2680)((class_2680)this.method_9564().method_11657((class_2769)field_11177, (Comparable)ctx.method_8042().method_10153())).method_11657((class_2769)ACTIVE, (Comparable)Boolean.valueOf(KitchenwareBlock.isCookware(ctx.method_8045().method_8320(ctx.method_8037().method_10084()))));
    }

    public void method_9567(class_1937 world, class_2338 pos, class_2680 state, @Nullable class_1309 placer, class_1799 itemStack) {
        super.method_9567(world, pos, state, placer, itemStack);
        StoveBlock.refreshAt(world, pos);
    }

    protected void method_9612(class_2680 state, class_1937 world, class_2338 pos, class_2248 sourceBlock, class_2338 sourcePos, boolean notify) {
        super.method_9612(state, world, pos, sourceBlock, sourcePos, notify);
        if (sourcePos.equals((Object)pos.method_10084())) {
            StoveBlock.refreshAt(world, pos);
        }
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return SHAPE;
    }

    protected class_2680 method_9598(class_2680 state, class_2470 rotation) {
        return (class_2680)state.method_11657((class_2769)field_11177, (Comparable)rotation.method_10503((class_2350)state.method_11654((class_2769)field_11177)));
    }

    protected class_2680 method_9569(class_2680 state, class_2415 mirror) {
        return state.method_26186(mirror.method_10345((class_2350)state.method_11654((class_2769)field_11177)));
    }

    public static void refreshAt(class_1937 world, class_2338 pos) {
        if (world.field_9236) {
            return;
        }
        class_2680 state = world.method_8320(pos);
        if (!(state.method_26204() instanceof StoveBlock)) {
            return;
        }
        boolean active = KitchenwareBlock.isCookware(world.method_8320(pos.method_10084()));
        if ((Boolean)state.method_11654((class_2769)ACTIVE) != active) {
            world.method_8652(pos, (class_2680)state.method_11657((class_2769)ACTIVE, (Comparable)Boolean.valueOf(active)), 3);
        }
    }
}

