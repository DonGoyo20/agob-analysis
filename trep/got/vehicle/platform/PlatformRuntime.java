/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 */
package trep.got.vehicle.platform;

import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_243;
import trep.got.vehicle.platform.PlatformHull;
import trep.got.vehicle.platform.PlatformSpatialIndex;
import trep.got.vehicle.platform.PlatformTransform;

public final class PlatformRuntime {
    private PlatformTransform previousTransform;
    private PlatformTransform currentTransform;
    private class_238 broadPhaseBox = class_238.method_30048((class_243)class_243.field_1353, (double)0.0, (double)0.0, (double)0.0);

    public void update(class_1297 entity, PlatformTransform nextTransform, PlatformHull hull) {
        this.previousTransform = this.currentTransform;
        this.refresh(entity, nextTransform, hull);
    }

    public void refresh(class_1297 entity, PlatformTransform nextTransform, PlatformHull hull) {
        this.currentTransform = nextTransform;
        this.broadPhaseBox = nextTransform.worldBounds(hull.localBounds()).method_1014(0.5);
        PlatformSpatialIndex.update(entity, this.broadPhaseBox);
    }

    public PlatformTransform previousTransform() {
        return this.previousTransform == null ? this.currentTransform : this.previousTransform;
    }

    public PlatformTransform currentTransform() {
        return this.currentTransform;
    }

    public class_238 broadPhaseBox() {
        return this.broadPhaseBox;
    }
}

