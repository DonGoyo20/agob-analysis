/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package trep.got.voyage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.class_243;

public record VoyageLane(String fromId, String toId, List<class_243> worldPoints, double lengthBlocks) {
    public boolean connects(String harbourId) {
        return this.fromId.equals(harbourId) || this.toId.equals(harbourId);
    }

    public String other(String harbourId) {
        if (this.fromId.equals(harbourId)) {
            return this.toId;
        }
        if (this.toId.equals(harbourId)) {
            return this.fromId;
        }
        return "";
    }

    public List<class_243> pointsFrom(String harbourId) {
        if (this.fromId.equals(harbourId)) {
            return this.worldPoints;
        }
        if (!this.toId.equals(harbourId)) {
            return List.of();
        }
        ArrayList<class_243> reversed = new ArrayList<class_243>(this.worldPoints);
        Collections.reverse(reversed);
        return List.copyOf(reversed);
    }
}

