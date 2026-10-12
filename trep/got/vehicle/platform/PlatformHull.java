/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 */
package trep.got.vehicle.platform;

import java.util.List;
import net.minecraft.class_238;
import net.minecraft.class_243;
import trep.got.vehicle.platform.PlatformLocalBox;

public final class PlatformHull {
    private final List<PlatformLocalBox> boxes;
    private final List<PlatformLocalBox> supportBoxes;
    private final class_238 localBounds;

    public PlatformHull(List<PlatformLocalBox> boxes) {
        this.boxes = List.copyOf(boxes);
        this.supportBoxes = this.boxes.stream().filter(PlatformLocalBox::isSupport).toList();
        this.localBounds = PlatformHull.computeBounds(this.boxes);
    }

    public List<PlatformLocalBox> boxes() {
        return this.boxes;
    }

    public List<PlatformLocalBox> supportBoxes() {
        return this.supportBoxes;
    }

    public class_238 localBounds() {
        return this.localBounds;
    }

    private static class_238 computeBounds(List<PlatformLocalBox> boxes) {
        if (boxes.isEmpty()) {
            return class_238.method_30048((class_243)class_243.field_1353, (double)0.0, (double)0.0, (double)0.0);
        }
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;
        for (PlatformLocalBox box : boxes) {
            minX = Math.min(minX, box.minX());
            minY = Math.min(minY, box.minY());
            minZ = Math.min(minZ, box.minZ());
            maxX = Math.max(maxX, box.maxX());
            maxY = Math.max(maxY, box.maxY());
            maxZ = Math.max(maxZ, box.maxZ());
        }
        return new class_238(minX, minY, minZ, maxX, maxY, maxZ);
    }
}

