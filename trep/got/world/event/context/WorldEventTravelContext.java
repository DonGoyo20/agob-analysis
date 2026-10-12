/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package trep.got.world.event.context;

import net.minecraft.class_2487;

public record WorldEventTravelContext(String movementMode, boolean mounted, String mountType, boolean aboardVessel, String vesselType) {
    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("movement_mode", this.movementMode);
        nbt.method_10556("mounted", this.mounted);
        nbt.method_10582("mount_type", this.mountType);
        nbt.method_10556("aboard_vessel", this.aboardVessel);
        nbt.method_10582("vessel_type", this.vesselType);
        return nbt;
    }

    public static WorldEventTravelContext fromNbt(class_2487 nbt) {
        return new WorldEventTravelContext(nbt.method_10558("movement_mode"), nbt.method_10577("mounted"), nbt.method_10558("mount_type"), nbt.method_10577("aboard_vessel"), nbt.method_10558("vessel_type"));
    }
}

