/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_5819
 */
package trep.got.castle.city;

import java.util.List;
import net.minecraft.class_5819;
import trep.got.castle.city.CastleCityTierPlan;
import trep.got.castle.city.CastleStructureResidentPlan;
import trep.got.castle.city.CastleWallStructurePlan;

public interface CastleKingdomCityPlanProvider {
    public boolean matches(String var1);

    public List<CastleStructureResidentPlan> createBaseStructurePlan(CastleCityTierPlan var1, class_5819 var2);

    default public CastleWallStructurePlan wallStructurePlan() {
        return CastleWallStructurePlan.stone();
    }
}

