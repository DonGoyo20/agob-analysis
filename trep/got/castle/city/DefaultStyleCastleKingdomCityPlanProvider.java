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

abstract class DefaultStyleCastleKingdomCityPlanProvider
extends AbstractCastleKingdomCityPlanProvider {
    private static final List<String> HOUSE_PATHS = List.of("new/sandstone_house_1", "new/sandstone_house_2", "new/sandstone_house_3", "new/spruce_house_1", "new/spruce_house_2", "new/spruce_house_3", "new/diorite_house_1", "new/diorite_house_2", "new/diorite_house_3", "new/diorite_house_4", "new/plains_house_1", "new/plains_house_2", "new/plains_house_3", "new/plains_house_4", "new/plains_house_5", "new/plains_house_6");
    private final class_2960 kingdomId;
    private final CastleWallStructurePlan wallStructurePlan;

    protected DefaultStyleCastleKingdomCityPlanProvider(String kingdomPath) {
        this(kingdomPath, CastleWallStructurePlan.stone());
    }

    protected DefaultStyleCastleKingdomCityPlanProvider(String kingdomPath, CastleWallStructurePlan wallStructurePlan) {
        this.kingdomId = class_2960.method_60655((String)"agob", (String)kingdomPath);
        this.wallStructurePlan = wallStructurePlan;
    }

    @Override
    public boolean matches(String kingdomId) {
        return this.kingdomId.toString().equals(kingdomId);
    }

    @Override
    public CastleWallStructurePlan wallStructurePlan() {
        return this.wallStructurePlan;
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

