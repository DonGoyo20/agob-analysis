/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1966
 *  net.minecraft.class_2378
 *  net.minecraft.class_2960
 *  net.minecraft.class_7923
 */
package trep.got.world.gen;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1966;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import trep.got.world.gen.MapBiomeSource;

public final class MapBiomeSourceTypes {
    public static final MapCodec<? extends class_1966> MAP = (MapCodec)class_2378.method_10230((class_2378)class_7923.field_41156, (class_2960)class_2960.method_60655((String)"agob", (String)"map"), MapBiomeSource.CODEC);

    private MapBiomeSourceTypes() {
    }

    public static void initialize() {
    }
}

