/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.castle.city;

import java.util.List;
import net.minecraft.class_2960;
import trep.got.castle.city.AstaporCastleKingdomCityPlanProvider;
import trep.got.castle.city.BlackfyreCastleKingdomCityPlanProvider;
import trep.got.castle.city.BraavosCastleKingdomCityPlanProvider;
import trep.got.castle.city.CastleKingdomCityPlanProvider;
import trep.got.castle.city.CrownlandsCastleKingdomCityPlanProvider;
import trep.got.castle.city.DefaultCastleKingdomCityPlanProvider;
import trep.got.castle.city.DorneCastleKingdomCityPlanProvider;
import trep.got.castle.city.GoldenCompanyCastleKingdomCityPlanProvider;
import trep.got.castle.city.IronIslandsCastleKingdomCityPlanProvider;
import trep.got.castle.city.MeereenCastleKingdomCityPlanProvider;
import trep.got.castle.city.MyrCastleKingdomCityPlanProvider;
import trep.got.castle.city.NightsWatchCastleKingdomCityPlanProvider;
import trep.got.castle.city.NorthCastleKingdomCityPlanProvider;
import trep.got.castle.city.PentosCastleKingdomCityPlanProvider;
import trep.got.castle.city.QarthCastleKingdomCityPlanProvider;
import trep.got.castle.city.ReachCastleKingdomCityPlanProvider;
import trep.got.castle.city.RiverlandsCastleKingdomCityPlanProvider;
import trep.got.castle.city.StormlandsCastleKingdomCityPlanProvider;
import trep.got.castle.city.TargaryenCastleKingdomCityPlanProvider;
import trep.got.castle.city.TyroshCastleKingdomCityPlanProvider;
import trep.got.castle.city.ValeCastleKingdomCityPlanProvider;
import trep.got.castle.city.VolantisCastleKingdomCityPlanProvider;
import trep.got.castle.city.WesterlandsCastleKingdomCityPlanProvider;
import trep.got.castle.city.WildlingCastleKingdomCityPlanProvider;
import trep.got.castle.city.YunkaiCastleKingdomCityPlanProvider;
import trep.got.registry.GotKingdoms;

public final class CastleKingdomCityPlanRegistry {
    private static final List<CastleKingdomCityPlanProvider> PROVIDERS = List.of(new NorthCastleKingdomCityPlanProvider(), new NightsWatchCastleKingdomCityPlanProvider(), new WildlingCastleKingdomCityPlanProvider(), new ValeCastleKingdomCityPlanProvider(), new RiverlandsCastleKingdomCityPlanProvider(), new WesterlandsCastleKingdomCityPlanProvider(), new IronIslandsCastleKingdomCityPlanProvider(), new ReachCastleKingdomCityPlanProvider(), new StormlandsCastleKingdomCityPlanProvider(), new DorneCastleKingdomCityPlanProvider(), new CrownlandsCastleKingdomCityPlanProvider(), new TargaryenCastleKingdomCityPlanProvider(), new BraavosCastleKingdomCityPlanProvider(), new PentosCastleKingdomCityPlanProvider(), new VolantisCastleKingdomCityPlanProvider(), new TyroshCastleKingdomCityPlanProvider(), new MyrCastleKingdomCityPlanProvider(), new BlackfyreCastleKingdomCityPlanProvider(), new GoldenCompanyCastleKingdomCityPlanProvider(), new MeereenCastleKingdomCityPlanProvider(), new YunkaiCastleKingdomCityPlanProvider(), new AstaporCastleKingdomCityPlanProvider(), new QarthCastleKingdomCityPlanProvider(), new DefaultCastleKingdomCityPlanProvider());

    private CastleKingdomCityPlanRegistry() {
    }

    public static CastleKingdomCityPlanProvider resolve(String kingdomId) {
        for (CastleKingdomCityPlanProvider provider : PROVIDERS) {
            if (!provider.matches(kingdomId)) continue;
            return provider;
        }
        class_2960 parsed = class_2960.method_12829((String)kingdomId);
        class_2960 cultureKingdomId = GotKingdoms.cultureKingdomId(parsed);
        if (cultureKingdomId != null && !cultureKingdomId.toString().equals(kingdomId)) {
            for (CastleKingdomCityPlanProvider provider : PROVIDERS) {
                if (!provider.matches(cultureKingdomId.toString())) continue;
                return provider;
            }
        }
        return PROVIDERS.getLast();
    }
}

