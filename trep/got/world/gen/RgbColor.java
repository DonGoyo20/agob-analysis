/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 */
package trep.got.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import java.util.List;

public record RgbColor(int red, int green, int blue) {
    public static final Codec<RgbColor> CODEC = Codec.INT.listOf().comapFlatMap(values -> {
        if (values.size() != 3) {
            return DataResult.error(() -> "RGB colors must have exactly 3 integer values");
        }
        return DataResult.success((Object)new RgbColor((Integer)values.get(0), (Integer)values.get(1), (Integer)values.get(2)));
    }, color -> List.of(Integer.valueOf(color.red), Integer.valueOf(color.green), Integer.valueOf(color.blue)));

    public int toRgb24() {
        return (this.red & 0xFF) << 16 | (this.green & 0xFF) << 8 | this.blue & 0xFF;
    }
}

