/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_5819
 */
package trep.got.castle.city;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_5819;
import trep.got.castle.city.CastleCityPlanUtil;
import trep.got.castle.city.CastleCityTierPlan;
import trep.got.castle.city.CastleKingdomCityPlanProvider;
import trep.got.castle.city.CastleStructureResidentPlan;
import trep.got.entity.KingdomNpcRole;

abstract class AbstractCastleKingdomCityPlanProvider
implements CastleKingdomCityPlanProvider {
    private static final List<String> MARKET_STRUCTURE_PATHS = List.of("new/market_stalls/market_stall_1", "new/market_stalls/market_stall_2", "new/market_stalls/market_stall_3", "new/market_stalls/market_stall_4", "new/market_stalls/market_stall_5");
    private static final int HOUSE_POI_RADIUS = 64;
    private static final int MARKET_POI_RADIUS = 48;
    private static final int WORKER_POI_RADIUS = 32;

    AbstractCastleKingdomCityPlanProvider() {
    }

    @Override
    public List<CastleStructureResidentPlan> createBaseStructurePlan(CastleCityTierPlan tierPlan, class_5819 random) {
        if (tierPlan.marketCount() > MARKET_STRUCTURE_PATHS.size()) {
            throw new IllegalStateException("Castle city tier " + String.valueOf((Object)tierPlan.tier()) + " requests " + tierPlan.marketCount() + " markets, but only " + MARKET_STRUCTURE_PATHS.size() + " unique market structures exist.");
        }
        ArrayList<CastleStructureResidentPlan> plans = new ArrayList<CastleStructureResidentPlan>(tierPlan.totalBaseStructures());
        plans.addAll(CastleCityPlanUtil.expand(this.blacksmithStructurePath(), KingdomNpcRole.BLACKSMITH, tierPlan.blacksmithCount(), 32));
        plans.addAll(CastleCityPlanUtil.expand(this.tavernStructurePath(), KingdomNpcRole.CIVILIAN, tierPlan.tavernCount(), 32));
        plans.addAll(CastleCityPlanUtil.expand(this.arenaStructurePath(), KingdomNpcRole.ARENA_MASTER, tierPlan.arenaCount(), 32));
        plans.addAll(CastleCityPlanUtil.expand(CastleCityPlanUtil.pickDistinct(MARKET_STRUCTURE_PATHS, tierPlan.marketCount(), random), KingdomNpcRole.CIVILIAN, 48));
        plans.addAll(CastleCityPlanUtil.expand(CastleCityPlanUtil.pickRepeating(this.houseStructurePaths(), tierPlan.houseCount(), random), KingdomNpcRole.CIVILIAN, 64));
        return List.copyOf(plans);
    }

    protected abstract List<String> houseStructurePaths();

    protected abstract String blacksmithStructurePath();

    protected abstract String tavernStructurePath();

    protected abstract String arenaStructurePath();
}

