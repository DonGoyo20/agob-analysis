/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 */
package trep.got.voyage;

import net.minecraft.class_2338;

public record VoyageHarbourNode(String id, String name, int worldX, int worldZ) {
    public class_2338 worldPos() {
        return new class_2338(this.worldX, 0, this.worldZ);
    }
}

