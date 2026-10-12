/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.castle.city;

import java.util.List;
import net.minecraft.class_2960;
import trep.got.castle.city.AbstractCastleKingdomCityPlanProvider;
import trep.got.castle.city.CastleWallStructurePlan;

public final class WildlingCastleKingdomCityPlanProvider
extends AbstractCastleKingdomCityPlanProvider {
    private static final class_2960 KINGDOM_ID = class_2960.method_60655((String)"agob", (String)"wildling");
    private static final List<String> HOUSE_PATHS = List.of("new/ice_house_1", "new/ice_house_2", "new/ice_house_3", "new/ice_house_4");

    @Override
    public boolean matches(String kingdomId) {
        return KINGDOM_ID.toString().equals(kingdomId);
    }

    @Override
    protected List<String> houseStructurePaths() {
        return HOUSE_PATHS;
    }

    @Override
    protected String blacksmithStructurePath() {
        return "new/spruce_blacksmith";
    }

    @Override
    protected String tavernStructurePath() {
        return "new/plains_cafe";
    }

    @Override
    protected String arenaStructurePath() {
        return "new/plains_arena";
    }

    @Override
    public CastleWallStructurePlan wallStructurePlan() {
        return CastleWallStructurePlan.wood();
    }
}

