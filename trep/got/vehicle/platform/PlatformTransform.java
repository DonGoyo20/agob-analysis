/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package trep.got.vehicle.platform;

import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import trep.got.vehicle.platform.PlatformLocalBox;

public record PlatformTransform(class_243 origin, class_243 pivot, double modelScale, double modelYOffset, float renderYaw, float collisionYaw) {
    public class_243 localToWorld(class_243 local) {
        class_243 centered = local.method_1020(this.pivot);
        class_243 scaled = centered.method_1021(this.modelScale).method_1031(0.0, this.modelYOffset, 0.0);
        float radians = (float)Math.toRadians(180.0 - (double)this.collisionYaw);
        class_243 rotated = scaled.method_1024(radians);
        return this.origin.method_1019(rotated);
    }

    public class_243 worldToLocal(class_243 world) {
        class_243 translated = world.method_1020(this.origin);
        float radians = (float)Math.toRadians((double)this.collisionYaw - 180.0);
        class_243 rotated = translated.method_1024(radians);
        class_243 unscaled = new class_243(rotated.field_1352 / this.modelScale, (rotated.field_1351 - this.modelYOffset) / this.modelScale, rotated.field_1350 / this.modelScale);
        return unscaled.method_1019(this.pivot);
    }

    public class_238 worldBounds(class_238 localBounds) {
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;
        for (int xi = 0; xi < 2; ++xi) {
            for (int yi = 0; yi < 2; ++yi) {
                for (int zi = 0; zi < 2; ++zi) {
                    class_243 point = this.localToWorld(new class_243(xi == 0 ? localBounds.field_1323 : localBounds.field_1320, yi == 0 ? localBounds.field_1322 : localBounds.field_1325, zi == 0 ? localBounds.field_1321 : localBounds.field_1324));
                    minX = Math.min(minX, point.field_1352);
                    minY = Math.min(minY, point.field_1351);
                    minZ = Math.min(minZ, point.field_1350);
                    maxX = Math.max(maxX, point.field_1352);
                    maxY = Math.max(maxY, point.field_1351);
                    maxZ = Math.max(maxZ, point.field_1350);
                }
            }
        }
        return new class_238(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public class_238 localBoxToWorldAabb(PlatformLocalBox localBox) {
        return this.worldBounds(localBox.asBox());
    }

    public float deltaYaw(PlatformTransform previous) {
        return class_3532.method_15393((float)(this.collisionYaw - previous.collisionYaw));
    }
}

