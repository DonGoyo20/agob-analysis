/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.class_1959
 *  net.minecraft.class_6880
 */
package trep.got.world.gen;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.class_1959;
import net.minecraft.class_6880;
import trep.got.world.gen.RgbColor;

public record MapBiomeEntry(String key, RgbColor color, class_6880<class_1959> biome) {
    public static final Codec<MapBiomeEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Codec.STRING.fieldOf("key").forGetter(MapBiomeEntry::key), (App)RgbColor.CODEC.fieldOf("rgb").forGetter(MapBiomeEntry::color), (App)class_1959.field_24677.fieldOf("biome").forGetter(MapBiomeEntry::biome)).apply((Applicative)instance, MapBiomeEntry::new));
}

