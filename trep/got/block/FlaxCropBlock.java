/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1922
 *  net.minecraft.class_2248
 *  net.minecraft.class_2302
 *  net.minecraft.class_2338
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2741
 *  net.minecraft.class_2758
 *  net.minecraft.class_2769
 *  net.minecraft.class_3726
 *  net.minecraft.class_4970$class_2251
 */
package trep.got.block;

import net.minecraft.class_1792;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2302;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2741;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_4970;
import trep.got.registry.GotItems;

public class FlaxCropBlock
extends class_2302 {
    public static final class_2758 AGE = class_2741.field_37654;
    private static final class_265[] AGE_TO_SHAPE = new class_265[]{FlaxCropBlock.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)4.0, (double)16.0), FlaxCropBlock.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)7.0, (double)16.0), FlaxCropBlock.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)10.0, (double)16.0), FlaxCropBlock.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)13.0, (double)16.0), FlaxCropBlock.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)16.0, (double)16.0)};

    public FlaxCropBlock(class_4970.class_2251 settings) {
        super(settings);
        this.method_9590((class_2680)((class_2680)this.field_10647.method_11664()).method_11657((class_2769)AGE, (Comparable)Integer.valueOf(0)));
    }

    protected class_1792 getSeedsItem() {
        return GotItems.FLAX_SEEDS;
    }

    public class_2758 method_9824() {
        return AGE;
    }

    public int method_9827() {
        return 4;
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{AGE});
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return AGE_TO_SHAPE[this.method_9829(state)];
    }
}

