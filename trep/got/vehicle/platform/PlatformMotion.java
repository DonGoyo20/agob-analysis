/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package trep.got.vehicle.platform;

import net.minecraft.class_243;

public record PlatformMotion(class_243 translation, float deltaYaw) {
    public static final PlatformMotion NONE = new PlatformMotion(class_243.field_1353, 0.0f);
}

