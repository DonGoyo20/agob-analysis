/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry;

import java.util.List;
import trep.got.castle.city.CastleStructureResidentPlan;

record ClaimStructureSelection(List<CastleStructureResidentPlan> placements) {
    List<CastleStructureResidentPlan> orderedPlacements() {
        return this.placements;
    }
}

