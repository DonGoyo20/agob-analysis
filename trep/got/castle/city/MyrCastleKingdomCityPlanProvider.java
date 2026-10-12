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

public final class MyrCastleKingdomCityPlanProvider
extends AbstractCastleKingdomCityPlanProvider {
    private static final class_2960 KINGDOM_ID = class_2960.method_60655((String)"agob", (String)"myr");
    private static final List<String> HOUSE_PATHS = List.of("new/sandstone_house_1", "new/sandstone_house_2", "new/sandstone_house_3", "new/spruce_house_1", "new/spruce_house_2", "new/spruce_house_3", "new/diorite_house_1", "new/diorite_house_2", "new/diorite_house_3", "new/diorite_house_4", "new/plains_house_1", "new/plains_house_2", "new/plains_house_3", "new/plains_house_4", "new/plains_house_5", "new/plains_house_6", "new/essos_house_1");

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

    @Override
    public CastleWallStructurePlan wallStructurePlan() {
        return CastleWallStructurePlan.sandstone();
    }
}

