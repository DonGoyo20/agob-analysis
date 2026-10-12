/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_2487
 *  net.minecraft.class_3341
 */
package trep.got.castle;

import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2487;
import net.minecraft.class_3341;

public record WallGateClaim(class_3341 footprint, class_2338 origin, class_2382 size, class_2338 pointOfInterest) {
    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10569("footprint_min_x", this.footprint.method_35415());
        nbt.method_10569("footprint_min_y", this.footprint.method_35416());
        nbt.method_10569("footprint_min_z", this.footprint.method_35417());
        nbt.method_10569("footprint_max_x", this.footprint.method_35418());
        nbt.method_10569("footprint_max_y", this.footprint.method_35419());
        nbt.method_10569("footprint_max_z", this.footprint.method_35420());
        nbt.method_10544("origin", this.origin.method_10063());
        nbt.method_10569("size_x", this.size.method_10263());
        nbt.method_10569("size_y", this.size.method_10264());
        nbt.method_10569("size_z", this.size.method_10260());
        nbt.method_10544("poi", this.pointOfInterest.method_10063());
        return nbt;
    }

    public static WallGateClaim fromNbt(class_2487 nbt) {
        return new WallGateClaim(new class_3341(nbt.method_10550("footprint_min_x"), nbt.method_10550("footprint_min_y"), nbt.method_10550("footprint_min_z"), nbt.method_10550("footprint_max_x"), nbt.method_10550("footprint_max_y"), nbt.method_10550("footprint_max_z")), class_2338.method_10092((long)nbt.method_10537("origin")), new class_2382(nbt.method_10550("size_x"), nbt.method_10550("size_y"), nbt.method_10550("size_z")), class_2338.method_10092((long)nbt.method_10537("poi")));
    }
}

