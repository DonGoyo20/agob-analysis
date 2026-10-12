/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle.city;

import java.util.List;
import trep.got.castle.city.CastleStructureResidentPlan;
import trep.got.entity.KingdomNpcRole;

public final class CastleHarbourPlanRegistry {
    private static final int HARBOUR_POI_RADIUS = 32;
    private static final List<CastleStructureResidentPlan> STRUCTURES = List.of(new CastleStructureResidentPlan("new/fisherman_hut", KingdomNpcRole.FISHERMAN, 32));

    private CastleHarbourPlanRegistry() {
    }

    public static List<CastleStructureResidentPlan> structures() {
        return STRUCTURES;
    }
}

