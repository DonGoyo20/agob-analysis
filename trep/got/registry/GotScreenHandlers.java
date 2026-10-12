/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType
 *  net.minecraft.class_2378
 *  net.minecraft.class_2960
 *  net.minecraft.class_3917
 *  net.minecraft.class_7701
 *  net.minecraft.class_7923
 */
package trep.got.registry;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3917;
import net.minecraft.class_7701;
import net.minecraft.class_7923;
import trep.got.blacksmith.BlacksmithScreenHandler;
import trep.got.blacksmith.BlacksmithScreenOpeningData;
import trep.got.screen.AgingBarrelScreenHandler;
import trep.got.screen.BrewerScreenHandler;
import trep.got.screen.DragonStatsScreenHandler;
import trep.got.screen.DragonStatsScreenOpeningData;
import trep.got.screen.MarketDisplayScreenHandler;
import trep.got.screen.PotCookingScreenHandler;

public final class GotScreenHandlers {
    public static final class_3917<BrewerScreenHandler> BREWER = (class_3917)class_2378.method_10230((class_2378)class_7923.field_41187, (class_2960)class_2960.method_60655((String)"agob", (String)"beer_brewing"), (Object)new class_3917(BrewerScreenHandler::new, class_7701.field_40182));
    public static final class_3917<AgingBarrelScreenHandler> AGING_BARREL = (class_3917)class_2378.method_10230((class_2378)class_7923.field_41187, (class_2960)class_2960.method_60655((String)"agob", (String)"aging_barrel"), (Object)new class_3917(AgingBarrelScreenHandler::new, class_7701.field_40182));
    public static final class_3917<BlacksmithScreenHandler> BLACKSMITH_FURNACE = (class_3917)class_2378.method_10230((class_2378)class_7923.field_41187, (class_2960)class_2960.method_60655((String)"agob", (String)"blacksmith_furnace"), (Object)new ExtendedScreenHandlerType(BlacksmithScreenHandler::createClient, BlacksmithScreenOpeningData.PACKET_CODEC));
    public static final class_3917<DragonStatsScreenHandler> DRAGON_STATS = (class_3917)class_2378.method_10230((class_2378)class_7923.field_41187, (class_2960)class_2960.method_60655((String)"agob", (String)"dragon_stats"), (Object)new ExtendedScreenHandlerType(DragonStatsScreenHandler::createClient, DragonStatsScreenOpeningData.PACKET_CODEC));
    public static final class_3917<MarketDisplayScreenHandler> MARKET_DISPLAY = (class_3917)class_2378.method_10230((class_2378)class_7923.field_41187, (class_2960)class_2960.method_60655((String)"agob", (String)"market_display"), (Object)new class_3917(MarketDisplayScreenHandler::new, class_7701.field_40182));
    public static final class_3917<PotCookingScreenHandler> POT_COOKING = (class_3917)class_2378.method_10230((class_2378)class_7923.field_41187, (class_2960)class_2960.method_60655((String)"agob", (String)"pot_cooking"), (Object)new class_3917(PotCookingScreenHandler::new, class_7701.field_40182));

    private GotScreenHandlers() {
    }

    public static void initialize() {
    }
}

