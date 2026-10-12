/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 */
package trep.got.registry;

import net.minecraft.class_2338;

record GateBreachProgress(String castleId, class_2338 mechanismPos, class_2338 targetPos, class_2338 approachPos, class_2338 moveThroughPos, int accumulatedWork) {
    GateBreachProgress withAccumulatedWork(int work) {
        return new GateBreachProgress(this.castleId, this.mechanismPos, this.targetPos, this.approachPos, this.moveThroughPos, work);
    }
}

