/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_1935
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2302
 *  net.minecraft.class_2338
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2758
 *  net.minecraft.class_2769
 *  net.minecraft.class_3726
 *  net.minecraft.class_4970$class_2251
 */
package trep.got.block;

import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2302;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_4970;
import trep.got.registry.GotItems;

public final class FourStageCropBlock
extends class_2302 {
    public static final class_2758 AGE = class_2758.method_11867((String)"age", (int)0, (int)3);
    private static final class_265[] AGE_TO_SHAPE = new class_265[]{FourStageCropBlock.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)3.0, (double)16.0), FourStageCropBlock.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)6.0, (double)16.0), FourStageCropBlock.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)10.0, (double)16.0), FourStageCropBlock.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)14.0, (double)16.0)};
    private final CropKind kind;

    public FourStageCropBlock(CropKind kind, class_4970.class_2251 settings) {
        super(settings);
        this.kind = kind;
        this.method_9590((class_2680)((class_2680)this.field_10647.method_11664()).method_11657((class_2769)AGE, (Comparable)Integer.valueOf(0)));
    }

    protected class_1935 method_9832() {
        return switch (this.kind.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> GotItems.TOMATO;
            case 1 -> GotItems.ORANGE;
            case 2 -> GotItems.TURNIP;
            case 3 -> GotItems.CHAMPIGNON;
        };
    }

    public class_2758 method_9824() {
        return AGE;
    }

    public int method_9827() {
        return 3;
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{AGE});
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return AGE_TO_SHAPE[this.method_9829(state)];
    }

    protected boolean method_9695(class_2680 floor, class_1922 world, class_2338 pos) {
        return floor.method_27852(class_2246.field_10362);
    }

    public static enum CropKind {
        TOMATO,
        ORANGE,
        TURNIP,
        CHAMPIGNON;

    }
}

