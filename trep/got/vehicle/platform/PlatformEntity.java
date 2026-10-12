/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_238
 */
package trep.got.vehicle.platform;

import net.minecraft.class_238;
import trep.got.vehicle.platform.PlatformHull;
import trep.got.vehicle.platform.PlatformMotion;
import trep.got.vehicle.platform.PlatformTransform;

public interface PlatformEntity {
    public PlatformHull agob$getPlatformHull();

    public PlatformTransform agob$getPlatformTransform();

    public PlatformTransform agob$getPreviousPlatformTransform();

    public class_238 agob$getPlatformBroadPhaseBox();

    default public PlatformMotion agob$getPlatformMotion() {
        PlatformTransform previous = this.agob$getPreviousPlatformTransform();
        PlatformTransform current = this.agob$getPlatformTransform();
        if (previous == null || current == null) {
            return PlatformMotion.NONE;
        }
        return new PlatformMotion(current.origin().method_1020(previous.origin()), current.deltaYaw(previous));
    }
}

