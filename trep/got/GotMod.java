/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ModInitializer
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package trep.got;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import trep.got.api.internal.AgobAddonRegistry;
import trep.got.blacksmith.BlacksmithCommands;
import trep.got.blacksmith.BlacksmithGroupRegistry;
import trep.got.block.GateLocator;
import trep.got.command.HouseAdminCommands;
import trep.got.command.KingdomAdminCommands;
import trep.got.command.QuestAdminCommands;
import trep.got.day.DailyTaskRegistry;
import trep.got.guard.GateWatcherGuardService;
import trep.got.house.HouseCommands;
import trep.got.market.MarketDisplaySetupRegistry;
import trep.got.physics.PhysicsCoreRegistry;
import trep.got.registry.GotBannerPatterns;
import trep.got.registry.GotBlockEntities;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotCastleOwnerNames;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacters;
import trep.got.registry.GotDynastyNpcs;
import trep.got.registry.GotEntities;
import trep.got.registry.GotGameRules;
import trep.got.registry.GotItemGroups;
import trep.got.registry.GotItems;
import trep.got.registry.GotKingdomEventLogs;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotLockpicking;
import trep.got.registry.GotLootFunctions;
import trep.got.registry.GotMainQuests;
import trep.got.registry.GotNetworking;
import trep.got.registry.GotNpcRelations;
import trep.got.registry.GotParticles;
import trep.got.registry.GotPlayerKingdomActions;
import trep.got.registry.GotQuests;
import trep.got.registry.GotRecipes;
import trep.got.registry.GotRelations;
import trep.got.registry.GotReputation;
import trep.got.registry.GotScreenHandlers;
import trep.got.registry.GotSkills;
import trep.got.registry.GotSounds;
import trep.got.registry.GotVillages;
import trep.got.settlement.SettlerProfessionProductionRegistry;
import trep.got.settlement.TrustCommands;
import trep.got.tailor.ClothingPatternRegistry;
import trep.got.troop.DynamicTroopRegistry;
import trep.got.troop.GotTroops;
import trep.got.vehicle.platform.PlatformSpatialIndex;
import trep.got.world.biome.GotBiomeKeys;
import trep.got.world.dimension.GotDimensionKeys;
import trep.got.world.event.LordLetterRobberAmbushService;
import trep.got.world.event.WorldEventSystem;
import trep.got.world.gen.GotBrewingWorldGeneration;
import trep.got.world.gen.MapBiomeSourceTypes;
import trep.got.world.gen.MapChunkGeneratorTypes;

public class GotMod
implements ModInitializer {
    public static final String MOD_ID = "agob";
    public static final Logger LOGGER = LoggerFactory.getLogger((String)"agob");

    public void onInitialize() {
        AgobAddonRegistry.bootstrap();
        DailyTaskRegistry.initialize();
        MapBiomeSourceTypes.initialize();
        MapChunkGeneratorTypes.initialize();
        GotGameRules.initialize();
        GotBannerPatterns.initialize();
        GotBlocks.initialize();
        GotBlockEntities.initialize();
        GotItems.initialize();
        GotLootFunctions.initialize();
        GotItemGroups.initialize();
        GotEntities.initialize();
        PlatformSpatialIndex.initialize();
        GateLocator.initialize();
        GotParticles.initialize();
        GotRecipes.initialize();
        GotScreenHandlers.initialize();
        GotBrewingWorldGeneration.initialize();
        GotKingdoms.initialize();
        BlacksmithGroupRegistry.initialize();
        BlacksmithCommands.initialize();
        HouseCommands.initialize();
        HouseAdminCommands.initialize();
        KingdomAdminCommands.initialize();
        QuestAdminCommands.initialize();
        TrustCommands.initialize();
        MarketDisplaySetupRegistry.initialize();
        ClothingPatternRegistry.initialize();
        SettlerProfessionProductionRegistry.initialize();
        GotCharacters.initialize();
        GotCastles.initialize();
        GotVillages.initialize();
        GotCastleOwnerNames.initialize();
        DynamicTroopRegistry.initialize();
        GotTroops.initialize();
        GotReputation.initialize();
        GotRelations.initialize();
        GotPlayerKingdomActions.initialize();
        GotNpcRelations.initialize();
        GotDynastyNpcs.initialize();
        GotKingdomEventLogs.initialize();
        GotSkills.initialize();
        GotSounds.initialize();
        GotLockpicking.initialize();
        GateWatcherGuardService.initialize();
        GotMainQuests.initialize();
        GotQuests.initialize();
        GotNetworking.initialize();
        PhysicsCoreRegistry.initialize();
        LordLetterRobberAmbushService.initialize();
        WorldEventSystem.initialize();
        GotBiomeKeys.initialize();
        GotDimensionKeys.initialize();
        LOGGER.info("Initialized Game of Thrones worldgen scaffolding");
    }
}

