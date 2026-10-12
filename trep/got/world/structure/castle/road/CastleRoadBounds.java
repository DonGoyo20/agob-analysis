/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3341
 */
package trep.got.world.structure.castle.road;

import net.minecraft.class_3341;

public record CastleRoadBounds(int minX, int minZ, int maxX, int maxZ) {
    public CastleRoadBounds {
        if (minX > maxX || minZ > maxZ) {
            throw new IllegalArgumentException("Invalid castle road bounds");
        }
    }

    public static CastleRoadBounds from(class_3341 box) {
        return new CastleRoadBounds(box.method_35415(), box.method_35417(), box.method_35418(), box.method_35420());
    }

    public CastleRoadBounds expand(int amount) {
        int expansion = Math.max(0, amount);
        return new CastleRoadBounds(this.minX - expansion, this.minZ - expansion, this.maxX + expansion, this.maxZ + expansion);
    }

    public boolean contains(int x, int z) {
        return x >= this.minX && x <= this.maxX && z >= this.minZ && z <= this.maxZ;
    }

    public boolean intersects(CastleRoadBounds other) {
        return this.minX <= other.maxX && this.maxX >= other.minX && this.minZ <= other.maxZ && this.maxZ >= other.minZ;
    }
}

