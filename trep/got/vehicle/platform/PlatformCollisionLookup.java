/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 */
package trep.got.vehicle.platform;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_259;
import net.minecraft.class_265;
import trep.got.vehicle.platform.PlatformEntity;
import trep.got.vehicle.platform.PlatformLocalBox;
import trep.got.vehicle.platform.PlatformSpatialIndex;
import trep.got.vehicle.platform.PlatformTransform;

public final class PlatformCollisionLookup {
    private static final double BROAD_PHASE_MARGIN = 1.5;

    private PlatformCollisionLookup() {
    }

    public static List<class_265> collect(class_1297 entity, class_238 movementBox) {
        class_1937 world = entity.method_37908();
        class_238 queryBox = movementBox.method_1014(1.5);
        List nearby = PlatformSpatialIndex.findCandidates(entity, queryBox);
        if (nearby == null) {
            nearby = world.method_8333(entity, queryBox, other -> {
                if (!(other instanceof PlatformEntity)) return false;
                PlatformEntity platform = (PlatformEntity)other;
                if (!other.method_5805()) return false;
                if (!platform.agob$getPlatformBroadPhaseBox().method_994(movementBox)) return false;
                return true;
            });
        }
        if (nearby.isEmpty()) {
            return List.of();
        }
        ArrayList<class_265> shapes = new ArrayList<class_265>();
        for (class_1297 other2 : nearby) {
            PlatformEntity platform = (PlatformEntity)other2;
            if (!other2.method_5805() || !platform.agob$getPlatformBroadPhaseBox().method_994(movementBox)) continue;
            PlatformTransform transform = platform.agob$getPlatformTransform();
            for (PlatformLocalBox localBox : platform.agob$getPlatformHull().boxes()) {
                class_238 worldBox = transform.localBoxToWorldAabb(localBox);
                if (!worldBox.method_994(movementBox)) continue;
                shapes.add(class_259.method_1078((class_238)worldBox));
            }
        }
        return shapes;
    }
}

