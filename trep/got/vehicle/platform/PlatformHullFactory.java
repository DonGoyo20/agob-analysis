/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_238
 */
package trep.got.vehicle.platform;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.class_238;
import trep.got.vehicle.platform.PlatformHull;
import trep.got.vehicle.platform.PlatformLocalBox;

public final class PlatformHullFactory {
    private PlatformHullFactory() {
    }

    public static PlatformHull mergeBoxes(List<class_238> boxes, double supportThreshold) {
        ArrayList<class_238> sorted = new ArrayList<class_238>(boxes);
        sorted.sort(Comparator.comparingDouble(box -> box.field_1322));
        ArrayList<PlatformLocalBox> merged = new ArrayList<PlatformLocalBox>();
        for (class_238 box2 : sorted) {
            byte flags = 2;
            if (box2.field_1325 >= supportThreshold) {
                flags = (byte)(flags | 1);
            }
            merged.add(new PlatformLocalBox(box2.field_1323, box2.field_1322, box2.field_1321, box2.field_1320, box2.field_1325, box2.field_1324, flags));
        }
        return new PlatformHull(merged);
    }
}

