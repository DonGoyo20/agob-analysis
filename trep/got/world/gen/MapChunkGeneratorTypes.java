/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_2378
 *  net.minecraft.class_2794
 *  net.minecraft.class_2960
 *  net.minecraft.class_7923
 */
package trep.got.world.gen;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_2378;
import net.minecraft.class_2794;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import trep.got.world.gen.MapChunkGenerator;

public final class MapChunkGeneratorTypes {
    public static final MapCodec<? extends class_2794> MAP_TERRAIN = (MapCodec)class_2378.method_10230((class_2378)class_7923.field_41157, (class_2960)class_2960.method_60655((String)"agob", (String)"map_terrain"), MapChunkGenerator.CODEC);

    private MapChunkGeneratorTypes() {
    }

    public static void initialize() {
    }
}

