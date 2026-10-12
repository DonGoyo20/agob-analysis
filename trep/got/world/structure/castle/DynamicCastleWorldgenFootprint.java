/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_3341
 */
package trep.got.world.structure.castle;

import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_3341;

public record DynamicCastleWorldgenFootprint(class_2338 origin, class_2382 size, int clearanceHeight) {
    public class_3341 blockBounds(int floorY) {
        return new class_3341(this.origin.method_10263(), floorY, this.origin.method_10260(), this.origin.method_10263() + this.size.method_10263() - 1, floorY + Math.max(1, this.clearanceHeight) - 1, this.origin.method_10260() + this.size.method_10260() - 1);
    }
}

