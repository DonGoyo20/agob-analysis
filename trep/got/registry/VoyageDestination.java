/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 */
package trep.got.registry;

import net.minecraft.class_2338;

record VoyageDestination(String id, String name, class_2338 arrivalCenter, int arrivalRadiusBlocks) {
    class_2338 travelPos() {
        return this.arrivalCenter;
    }
}

