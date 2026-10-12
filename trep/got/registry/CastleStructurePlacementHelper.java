/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_3218
 *  net.minecraft.class_3341
 *  net.minecraft.class_3491
 *  net.minecraft.class_3492
 *  net.minecraft.class_3499
 *  net.minecraft.class_3532
 *  net.minecraft.class_3793
 *  net.minecraft.class_5425
 */
package trep.got.registry;

import java.util.List;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2902;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3491;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_3532;
import net.minecraft.class_3793;
import net.minecraft.class_5425;

final class CastleStructurePlacementHelper {
    private CastleStructurePlacementHelper() {
    }

    static class_2338 structurePlacementOriginForMin(class_3341 localBounds, int minX, int y, int minZ) {
        return new class_2338(minX - localBounds.method_35415(), y - localBounds.method_35416(), minZ - localBounds.method_35417());
    }

    static class_3341 offsetBlockBox(class_3341 bounds, class_2338 origin) {
        return new class_3341(bounds.method_35415() + origin.method_10263(), bounds.method_35416() + origin.method_10264(), bounds.method_35417() + origin.method_10260(), bounds.method_35418() + origin.method_10263(), bounds.method_35419() + origin.method_10264(), bounds.method_35420() + origin.method_10260());
    }

    static boolean intersectsAnyBlockBox(class_3341 bounds, List<class_3341> others) {
        for (class_3341 other : others) {
            if (!CastleStructurePlacementHelper.blockBoxesIntersect(bounds, other)) continue;
            return true;
        }
        return false;
    }

    static boolean blockBoxesIntersect(class_3341 first, class_3341 second) {
        return first.method_35415() <= second.method_35418() && first.method_35418() >= second.method_35415() && first.method_35416() <= second.method_35419() && first.method_35419() >= second.method_35416() && first.method_35417() <= second.method_35420() && first.method_35420() >= second.method_35417();
    }

    static boolean placeStructureAtMin(class_3218 world, class_3499 template, class_2470 rotation, int minX, int y, int minZ) {
        class_3341 localBounds = template.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        class_2338 origin = new class_2338(minX - localBounds.method_35415(), y - localBounds.method_35416(), minZ - localBounds.method_35417());
        class_3492 placement = new class_3492().method_15133(false).method_15131(true).method_27264(false).method_15123(rotation).method_16184((class_3491)class_3793.field_16721);
        return template.method_15172((class_5425)world, origin, origin, placement, world.field_9229, 3);
    }

    static class_2382 rotatedTemplateSize(class_3499 template, class_2470 rotation) {
        class_3341 bounds = template.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        return new class_2382(bounds.method_35414(), bounds.method_14660(), bounds.method_14663());
    }

    static class_2338 findChunkStructureOrigin(class_3218 world, class_1923 chunkPos, class_2382 templateSize) {
        int availableX = Math.max(0, 16 - templateSize.method_10263());
        int availableZ = Math.max(0, 16 - templateSize.method_10260());
        int x = chunkPos.method_8326() + availableX / 2;
        int z = chunkPos.method_8328() + availableZ / 2;
        int y = world.method_8624(class_2902.class_2903.field_13203, x + templateSize.method_10263() / 2, z + templateSize.method_10260() / 2);
        return new class_2338(x, y, z);
    }

    static class_2338 findChunkStructureOriginAtY(class_1923 chunkPos, class_2382 templateSize, int y) {
        int availableX = Math.max(0, 16 - templateSize.method_10263());
        int availableZ = Math.max(0, 16 - templateSize.method_10260());
        int x = chunkPos.method_8326() + availableX / 2;
        int z = chunkPos.method_8328() + availableZ / 2;
        return new class_2338(x, y, z);
    }

    static double squaredDistanceToChunkCenter(class_1923 chunkPos, class_2338 pos) {
        double dx = (double)chunkPos.method_8326() + 8.0 - (double)pos.method_10263();
        double dz = (double)chunkPos.method_8328() + 8.0 - (double)pos.method_10260();
        return dx * dx + dz * dz;
    }

    static boolean chunkIntersectsBlockBox(class_1923 chunkPos, class_3341 bounds) {
        return chunkPos.method_8326() <= bounds.method_35418() && chunkPos.method_8327() >= bounds.method_35415() && chunkPos.method_8328() <= bounds.method_35420() && chunkPos.method_8329() >= bounds.method_35417();
    }

    static double distanceToExpandedChunk(class_1923 chunkPos, double x, double z, double expansion) {
        double minX = (double)chunkPos.method_8326() - expansion;
        double maxX = (double)chunkPos.method_8327() + expansion + 1.0;
        double minZ = (double)chunkPos.method_8328() - expansion;
        double maxZ = (double)chunkPos.method_8329() + expansion + 1.0;
        return CastleStructurePlacementHelper.distanceToRect(x, z, minX, maxX, minZ, maxZ);
    }

    static double distanceToRect(double x, double z, double minX, double maxX, double minZ, double maxZ) {
        double dx = 0.0;
        if (x < minX) {
            dx = minX - x;
        } else if (x > maxX) {
            dx = x - maxX;
        }
        double dz = 0.0;
        if (z < minZ) {
            dz = minZ - z;
        } else if (z > maxZ) {
            dz = z - maxZ;
        }
        return Math.sqrt(dx * dx + dz * dz);
    }

    static double smoothstep(double value) {
        double clamped = class_3532.method_15350((double)value, (double)0.0, (double)1.0);
        return clamped * clamped * (3.0 - 2.0 * clamped);
    }
}

