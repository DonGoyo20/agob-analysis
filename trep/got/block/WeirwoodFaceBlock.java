/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1750
 *  net.minecraft.class_2248
 *  net.minecraft.class_2350
 *  net.minecraft.class_2383
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2769
 *  net.minecraft.class_4970$class_2251
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1750;
import net.minecraft.class_2248;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2769;
import net.minecraft.class_4970;

public class WeirwoodFaceBlock
extends class_2383 {
    public static final MapCodec<WeirwoodFaceBlock> CODEC = WeirwoodFaceBlock.method_54094(WeirwoodFaceBlock::new);

    public WeirwoodFaceBlock(class_4970.class_2251 settings) {
        super(settings);
        this.method_9590((class_2680)((class_2680)this.field_10647.method_11664()).method_11657((class_2769)field_11177, (Comparable)class_2350.field_11043));
    }

    protected MapCodec<? extends class_2383> method_53969() {
        return CODEC;
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{field_11177});
    }

    public class_2680 method_9605(class_1750 ctx) {
        return (class_2680)this.method_9564().method_11657((class_2769)field_11177, (Comparable)ctx.method_8042().method_10153());
    }

    protected class_2680 method_9598(class_2680 state, class_2470 rotation) {
        return (class_2680)state.method_11657((class_2769)field_11177, (Comparable)rotation.method_10503((class_2350)state.method_11654((class_2769)field_11177)));
    }

    protected class_2680 method_9569(class_2680 state, class_2415 mirror) {
        return state.method_26186(mirror.method_10345((class_2350)state.method_11654((class_2769)field_11177)));
    }
}

