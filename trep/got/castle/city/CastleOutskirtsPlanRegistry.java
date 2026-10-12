/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle.city;

import trep.got.castle.city.CastleStructureResidentPlan;
import trep.got.entity.KingdomNpcRole;

public final class CastleOutskirtsPlanRegistry {
    private static final int BARN_POI_RADIUS = 32;
    private static final CastleStructureResidentPlan BARN_STRUCTURE = new CastleStructureResidentPlan("farm/barn", KingdomNpcRole.CART_RIDER, 32);

    private CastleOutskirtsPlanRegistry() {
    }

    public static CastleStructureResidentPlan barnStructure() {
        return BARN_STRUCTURE;
    }
}

