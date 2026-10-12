/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package trep.got.world.event.context;

import net.minecraft.class_2487;

public record WorldEventVitalsContext(float health, float maxHealth, int hunger, float saturation) {
    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10548("health", this.health);
        nbt.method_10548("max_health", this.maxHealth);
        nbt.method_10569("hunger", this.hunger);
        nbt.method_10548("saturation", this.saturation);
        return nbt;
    }

    public static WorldEventVitalsContext fromNbt(class_2487 nbt) {
        return new WorldEventVitalsContext(nbt.method_10583("health"), nbt.method_10583("max_health"), nbt.method_10550("hunger"), nbt.method_10583("saturation"));
    }
}

