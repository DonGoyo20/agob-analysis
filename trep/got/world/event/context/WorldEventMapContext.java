/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package trep.got.world.event.context;

import net.minecraft.class_2487;

public record WorldEventMapContext(String dimensionId, int blockX, int blockY, int blockZ, int mapPixelX, int mapPixelZ, boolean insideKnownMap) {
    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("dimension_id", this.dimensionId);
        nbt.method_10569("block_x", this.blockX);
        nbt.method_10569("block_y", this.blockY);
        nbt.method_10569("block_z", this.blockZ);
        nbt.method_10569("map_pixel_x", this.mapPixelX);
        nbt.method_10569("map_pixel_z", this.mapPixelZ);
        nbt.method_10556("inside_known_map", this.insideKnownMap);
        return nbt;
    }

    public static WorldEventMapContext fromNbt(class_2487 nbt) {
        return new WorldEventMapContext(nbt.method_10558("dimension_id"), nbt.method_10550("block_x"), nbt.method_10550("block_y"), nbt.method_10550("block_z"), nbt.method_10550("map_pixel_x"), nbt.method_10550("map_pixel_z"), nbt.method_10577("inside_known_map"));
    }
}

