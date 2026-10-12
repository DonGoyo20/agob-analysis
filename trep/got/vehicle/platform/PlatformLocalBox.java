/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_238
 */
package trep.got.vehicle.platform;

import net.minecraft.class_238;

public record PlatformLocalBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, byte flags) {
    public static final byte SUPPORT = 1;
    public static final byte BLOCKING = 2;

    public class_238 asBox() {
        return new class_238(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
    }

    public boolean isSupport() {
        return (this.flags & 1) != 0;
    }

    public boolean isBlocking() {
        return (this.flags & 2) != 0;
    }
}

