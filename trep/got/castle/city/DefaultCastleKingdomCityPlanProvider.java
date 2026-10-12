/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle.city;

import java.util.List;
import trep.got.castle.city.AbstractCastleKingdomCityPlanProvider;

public final class DefaultCastleKingdomCityPlanProvider
extends AbstractCastleKingdomCityPlanProvider {
    private static final List<String> HOUSE_PATHS = List.of("new/plains_house_1", "new/plains_house_2", "new/plains_house_3", "new/plains_house_4", "new/plains_house_5", "new/plains_house_6");

    @Override
    public boolean matches(String kingdomId) {
        return true;
    }

    @Override
    protected List<String> houseStructurePaths() {
        return HOUSE_PATHS;
    }

    @Override
    protected String blacksmithStructurePath() {
        return "new/plains_blacksmith";
    }

    @Override
    protected String tavernStructurePath() {
        return "new/plains_cafe";
    }

    @Override
    protected String arenaStructurePath() {
        return "new/plains_arena";
    }
}

