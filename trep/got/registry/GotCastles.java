/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents
 *  net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
 *  net.fabricmc.fabric.api.event.player.UseBlockCallback
 *  net.fabricmc.fabric.api.networking.v1.PacketSender
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_11
 *  net.minecraft.class_124
 *  net.minecraft.class_1259$class_1260
 *  net.minecraft.class_1263
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_1299
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1322
 *  net.minecraft.class_1322$class_1323
 *  net.minecraft.class_1324
 *  net.minecraft.class_1496
 *  net.minecraft.class_1531
 *  net.minecraft.class_1657
 *  net.minecraft.class_1676
 *  net.minecraft.class_1707
 *  net.minecraft.class_173
 *  net.minecraft.class_1738
 *  net.minecraft.class_1743
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_181
 *  net.minecraft.class_1923
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_1959
 *  net.minecraft.class_2168
 *  net.minecraft.class_2170
 *  net.minecraft.class_2170$class_5364
 *  net.minecraft.class_2185
 *  net.minecraft.class_2186
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2261
 *  net.minecraft.class_2275
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_2397
 *  net.minecraft.class_2415
 *  net.minecraft.class_243
 *  net.minecraft.class_2465
 *  net.minecraft.class_2470
 *  net.minecraft.class_2488
 *  net.minecraft.class_2541
 *  net.minecraft.class_2558
 *  net.minecraft.class_2558$class_2559
 *  net.minecraft.class_2561
 *  net.minecraft.class_2568
 *  net.minecraft.class_2568$class_5247
 *  net.minecraft.class_2583
 *  net.minecraft.class_2586
 *  net.minecraft.class_2680
 *  net.minecraft.class_2741
 *  net.minecraft.class_2769
 *  net.minecraft.class_2794
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3341
 *  net.minecraft.class_3481
 *  net.minecraft.class_3485
 *  net.minecraft.class_3486
 *  net.minecraft.class_3491
 *  net.minecraft.class_3492
 *  net.minecraft.class_3499
 *  net.minecraft.class_3532
 *  net.minecraft.class_3730
 *  net.minecraft.class_3793
 *  net.minecraft.class_3908
 *  net.minecraft.class_3917
 *  net.minecraft.class_3965
 *  net.minecraft.class_5134
 *  net.minecraft.class_52
 *  net.minecraft.class_5250
 *  net.minecraft.class_5321
 *  net.minecraft.class_5425
 *  net.minecraft.class_5575
 *  net.minecraft.class_5819
 *  net.minecraft.class_6880
 *  net.minecraft.class_7157
 *  net.minecraft.class_747
 *  net.minecraft.class_7923
 *  net.minecraft.class_7924
 *  net.minecraft.class_8567
 *  net.minecraft.class_8567$class_8568
 *  net.minecraft.class_8710
 *  net.minecraft.class_9334
 *  net.minecraft.server.MinecraftServer
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.invoke.CallSite;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_11;
import net.minecraft.class_124;
import net.minecraft.class_1259;
import net.minecraft.class_1263;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1322;
import net.minecraft.class_1324;
import net.minecraft.class_1496;
import net.minecraft.class_1531;
import net.minecraft.class_1657;
import net.minecraft.class_1676;
import net.minecraft.class_1707;
import net.minecraft.class_173;
import net.minecraft.class_1738;
import net.minecraft.class_1743;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_181;
import net.minecraft.class_1923;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_1959;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2185;
import net.minecraft.class_2186;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2261;
import net.minecraft.class_2275;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2397;
import net.minecraft.class_2415;
import net.minecraft.class_243;
import net.minecraft.class_2465;
import net.minecraft.class_2470;
import net.minecraft.class_2488;
import net.minecraft.class_2541;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2583;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2769;
import net.minecraft.class_2794;
import net.minecraft.class_2902;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3341;
import net.minecraft.class_3481;
import net.minecraft.class_3485;
import net.minecraft.class_3486;
import net.minecraft.class_3491;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_3532;
import net.minecraft.class_3730;
import net.minecraft.class_3793;
import net.minecraft.class_3908;
import net.minecraft.class_3917;
import net.minecraft.class_3965;
import net.minecraft.class_5134;
import net.minecraft.class_52;
import net.minecraft.class_5250;
import net.minecraft.class_5321;
import net.minecraft.class_5425;
import net.minecraft.class_5575;
import net.minecraft.class_5819;
import net.minecraft.class_6880;
import net.minecraft.class_7157;
import net.minecraft.class_747;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_8567;
import net.minecraft.class_8710;
import net.minecraft.class_9334;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import trep.got.GotMod;
import trep.got.advancement.ModAdvancementService;
import trep.got.block.GateFenceBlock;
import trep.got.block.GateLocator;
import trep.got.block.GateMechanismBlock;
import trep.got.block.TentCollisionBlock;
import trep.got.castle.CastleDefinition;
import trep.got.castle.CastleHolder;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleInvasionMode;
import trep.got.castle.CastleNpcProfile;
import trep.got.castle.CastlePlacementPlan;
import trep.got.castle.CastlePlacementPlanner;
import trep.got.castle.CastlePlacementRules;
import trep.got.castle.CastleRaidState;
import trep.got.castle.CastleRecaptureStage;
import trep.got.castle.CastleRecaptureState;
import trep.got.castle.CastleStorageInventory;
import trep.got.castle.CastleSubtype;
import trep.got.castle.CastleTemplateKey;
import trep.got.castle.CastleTier;
import trep.got.castle.CastleTitle;
import trep.got.castle.CastleWoodType;
import trep.got.castle.CastleWorldState;
import trep.got.castle.CityStructureClaim;
import trep.got.castle.HouseLordWorldState;
import trep.got.castle.KingdomAiInvasionWorldState;
import trep.got.castle.KingdomLeaderWorldState;
import trep.got.castle.PendingCastleGeneration;
import trep.got.castle.PendingCastleGenerationStage;
import trep.got.castle.PendingCastleOwnershipWorldState;
import trep.got.castle.PlayerCastleTravelHolder;
import trep.got.castle.PlayerSettlementTier;
import trep.got.castle.SettlerProfession;
import trep.got.castle.SettlerProfile;
import trep.got.castle.SettlerSkillTier;
import trep.got.castle.WallGateClaim;
import trep.got.castle.city.CastleCityTierPlan;
import trep.got.castle.city.CastleCityTierRegistry;
import trep.got.castle.city.CastleHarbourPlanRegistry;
import trep.got.castle.city.CastleKingdomCityPlanRegistry;
import trep.got.castle.city.CastleOutskirtsPlanRegistry;
import trep.got.castle.city.CastleStructureResidentPlan;
import trep.got.castle.city.CastleWallStructurePlan;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.NpcAppearanceProfile;
import trep.got.character.NpcAttributeProfile;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.day.DailyTaskContext;
import trep.got.day.DailyTaskRegistry;
import trep.got.economy.GotEconomy;
import trep.got.entity.AbstractDynastyNpcEntity;
import trep.got.entity.AbstractKingdomArenaMasterNpcEntity;
import trep.got.entity.AbstractKingdomCaptainNpcEntity;
import trep.got.entity.AbstractKingdomCartRiderNpcEntity;
import trep.got.entity.AbstractKingdomFishermanNpcEntity;
import trep.got.entity.AbstractKingdomGuardNpcEntity;
import trep.got.entity.AbstractKingdomHumanoidEntity;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.AbstractTourneyNpcEntity;
import trep.got.entity.CartEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.entity.PartyTroopEntity;
import trep.got.entity.SettlerNpcEntity;
import trep.got.entity.ShipEntity;
import trep.got.entity.SumpterEntity;
import trep.got.entity.TentEntity;
import trep.got.entity.TwoHorseCartEntity;
import trep.got.entity.VesselVariant;
import trep.got.family.DynastyNpcWorldState;
import trep.got.house.HouseLineageService;
import trep.got.house.HouseLineageWorldState;
import trep.got.house.PlayerHouseDefinition;
import trep.got.house.PlayerHouseService;
import trep.got.house.PlayerHouseWorldState;
import trep.got.item.InventoryItemHelper;
import trep.got.item.ItemStackQualityHelper;
import trep.got.item.TentItem;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.PlayerKingdomDefinition;
import trep.got.kingdom.PlayerKingdomWorldState;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationExperienceService;
import trep.got.kingdom.ReputationTitle;
import trep.got.network.CastleInvasionStatePayload;
import trep.got.network.CastleMapPayload;
import trep.got.network.ClaimOutlinePayload;
import trep.got.network.OpenCastleScreenPayload;
import trep.got.network.VoyageStatePayload;
import trep.got.quest.PledgeQuestService;
import trep.got.quest.ShipmentQuestLogic;
import trep.got.registry.ActiveCastleInvasion;
import trep.got.registry.ActiveKingdomAiInvasion;
import trep.got.registry.ActiveLandTravelSession;
import trep.got.registry.ActiveTitleChallenge;
import trep.got.registry.ActiveTourneyChallenge;
import trep.got.registry.ActiveVoyageSession;
import trep.got.registry.AgobCastleOwnershipConfig;
import trep.got.registry.AgobPlayerClaimsConfig;
import trep.got.registry.ArenaBoundary;
import trep.got.registry.ArenaBoundaryCache;
import trep.got.registry.ArenaChallengePositions;
import trep.got.registry.ArenaEntitySnapshot;
import trep.got.registry.AttributeOperation;
import trep.got.registry.CastleBattleParticipantSide;
import trep.got.registry.CastleDynamicWallPlanningHelper;
import trep.got.registry.CastleKeepArrangement;
import trep.got.registry.CastleKeepLayout;
import trep.got.registry.CastleKeepPiece;
import trep.got.registry.CastleKeepTemplate;
import trep.got.registry.CastleMapResourceHelper;
import trep.got.registry.CastleStructurePlacementHelper;
import trep.got.registry.ClaimStructureSelection;
import trep.got.registry.CoreStageProgress;
import trep.got.registry.DirectionChoice;
import trep.got.registry.DynamicStoneCastleLayout;
import trep.got.registry.DynamicStoneCastleTierConfig;
import trep.got.registry.DynamicStoneWallPlan;
import trep.got.registry.DynamicStructurePlacement;
import trep.got.registry.FishermanSpawnLogCounter;
import trep.got.registry.FishermanSpawnResolution;
import trep.got.registry.GateBreachProgress;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotCastleOwnerNames;
import trep.got.registry.GotCastleRecaptures;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotCharacters;
import trep.got.registry.GotDynastyNpcs;
import trep.got.registry.GotEntities;
import trep.got.registry.GotGameRules;
import trep.got.registry.GotItems;
import trep.got.registry.GotKingdomArmor;
import trep.got.registry.GotKingdomEventLogs;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotMainQuests;
import trep.got.registry.GotNpcMarriage;
import trep.got.registry.GotNpcRelations;
import trep.got.registry.GotRelations;
import trep.got.registry.GotReligionOptions;
import trep.got.registry.GotVillages;
import trep.got.registry.HarbourNpcPlacement;
import trep.got.registry.HarbourStructurePlacement;
import trep.got.registry.IntRange;
import trep.got.registry.KingdomAiBattleSide;
import trep.got.registry.LoadedCastleNpcIndex;
import trep.got.registry.PendingClaimCheck;
import trep.got.registry.PendingGenerationLoadRequirement;
import trep.got.registry.PendingNpcRespawn;
import trep.got.registry.PlacedCastleStructure;
import trep.got.registry.PlatformSurfaceStyle;
import trep.got.registry.PredictedWeirwoodSite;
import trep.got.registry.SettlerLevelRule;
import trep.got.registry.StructurePlacementResult;
import trep.got.registry.StructurePlot;
import trep.got.registry.StructureSurfaceStats;
import trep.got.registry.SurfacePalette;
import trep.got.registry.VoyageAvoidanceDecision;
import trep.got.registry.VoyageControlLogCounter;
import trep.got.registry.VoyageDestination;
import trep.got.registry.VoyageObstacle;
import trep.got.registry.WarOpeningCandidate;
import trep.got.registry.WeirwoodAnchor;
import trep.got.registry.castle.overlay.CastleOverlayDataLoader;
import trep.got.registry.castle.overlay.CastlePresetMetadata;
import trep.got.registry.castle.overlay.OverlayCastleSite;
import trep.got.registry.castle.overlay.OverlayPresetReference;
import trep.got.registry.castle.overlay.OverlayPresetReferences;
import trep.got.registry.castle.structure.CastleStructureChunkPlanner;
import trep.got.registry.castle.structure.ChunkArea;
import trep.got.registry.castle.structure.ChunkExclusion;
import trep.got.registry.castle.structure.ChunkSetArea;
import trep.got.relations.KingdomRelationsWorldState;
import trep.got.relations.KingdomWarService;
import trep.got.relations.PlayerMarriageHolder;
import trep.got.relations.PlayerMarriageProfile;
import trep.got.relations.PlayerNpcRelationsHolder;
import trep.got.religion.ReligionSelectionOption;
import trep.got.settlement.SettlerProfessionProductionRegistry;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeState;
import trep.got.skill.SkillAttributeType;
import trep.got.skill.SkillExperienceService;
import trep.got.travel.LandTravelRouteHelper;
import trep.got.troop.DynamicTroopRegistry;
import trep.got.troop.GotTroops;
import trep.got.troop.HireableCampDefinition;
import trep.got.village.VillageInstance;
import trep.got.village.VillageWorldState;
import trep.got.voyage.VoyageHarbourNode;
import trep.got.voyage.VoyageMapResources;
import trep.got.voyage.VoyageNetwork;
import trep.got.voyage.VoyageNetworkBuilder;
import trep.got.voyage.VoyagePath;
import trep.got.voyage.VoyageWorldState;
import trep.got.war.LordWarPartyHelper;
import trep.got.world.WeirwoodTreeWorldState;
import trep.got.world.biome.GotBiomeKeys;
import trep.got.world.dimension.GotDimensionKeys;
import trep.got.world.event.TourneyWorldState;
import trep.got.world.gen.MapChunkGenerator;
import trep.got.world.structure.cache.CastlePlanCache;
import trep.got.world.structure.castle.CastleWorldgenPlan;
import trep.got.world.structure.castle.CastleWorldgenStructureApplier;
import trep.got.world.structure.castle.CastleWorldgenStructurePlan;
import trep.got.world.structure.castle.DynamicCastleCoreApplier;
import trep.got.world.structure.castle.DynamicCastleCoreWorldgenPlan;
import trep.got.world.structure.castle.DynamicCastleKeepPiecePlan;
import trep.got.world.structure.castle.DynamicCastleTerrainApplier;
import trep.got.world.structure.castle.DynamicCastleWallApplier;
import trep.got.world.structure.castle.DynamicCastleWallPlan;
import trep.got.world.structure.castle.DynamicCastleWorldgenFootprint;
import trep.got.world.structure.castle.road.CastleRoadCell;
import trep.got.world.structure.castle.road.CastleRoadNetworkApplier;
import trep.got.world.structure.castle.road.CastleRoadNetworkFingerprint;
import trep.got.world.structure.castle.road.CastleRoadNetworkPlan;
import trep.got.world.structure.plan.StructureChunkFootprint;

public final class GotCastles {
    private static final String DEFINITIONS_PATH = "data/agob/castles/definitions.json";
    private static final String DIMENSION_PATH = "data/agob/dimension/iceandfire.json";
    private static final String PRESETS_ROOT = "data/agob/castles/presets/";
    private static final String DEV_PRESETS_ROOT = "src/main/resources/data/agob/castles/presets/";
    private static final String WORLD_MAP_PATH = "assets/agob/map.png";
    private static final String WOODEN_OVERLAY_PATH = "assets/agob/map_castles_wooden_exact.png";
    private static final String STONE_OVERLAY_PATH = "assets/agob/map_castles_stone_exact.png";
    private static final int WOODEN_CASTLE_COLOR = 6302572;
    private static final int STONE_CASTLE_COLOR = 65337;
    private static final int TARGARYEN_MAP_COLOR = 30326;
    private static final int PENTOS_MAP_COLOR = 5786380;
    private static final int ANNOUNCEMENT_YELLOW = -11187;
    private static final int ANNOUNCEMENT_GREEN = -10497171;
    private static final int ANNOUNCEMENT_RED = -2074022;
    private static final String SETTLEMENT_DEFINITION_ID = "overlay_settlement";
    private static final String LORDSHIP_DEFINITION_ID = "overlay_lordship";
    private static final int CASTLE_GENERATION_OPERATIONS_PER_TICK = 32;
    private static final long CASTLE_GENERATION_TIME_BUDGET_NANOS = 15000000L;
    private static final int CASTLE_GENERATION_STRUCTURE_BATCH_SIZE = 8;
    private static final int CASTLE_GENERATION_CORE_BATCH_COLUMNS = 8;
    private static final int CASTLE_GENERATION_BURST_OPERATIONS_PER_TICK = 96;
    private static final long CASTLE_GENERATION_BURST_TIME_BUDGET_NANOS = 30000000L;
    private static final int CASTLE_GENERATION_CORE_BURST_BATCH_COLUMNS = 24;
    private static final int CASTLE_GENERATION_STRUCTURE_BURST_BATCH_SIZE = 16;
    private static final int CASTLE_GENERATION_BURST_RADIUS_BLOCKS = 256;
    private static final int CASTLE_GENERATION_FORCE_LOAD_RADIUS_BLOCKS = 512;
    private static final long CASTLE_GENERATION_BLOCKED_LOG_INTERVAL_TICKS = 100L;
    private static final boolean CHUNK_NATIVE_CASTLE_WORLDGEN_ENABLED = true;
    private static final long CASTLE_DISCOVERY_INTERVAL_TICKS = 200L;
    private static final long OVERLAY_CASTLE_IDLE_SCAN_INTERVAL_TICKS = 20L;
    private static final long CASTLE_POPULATION_RECONCILE_INTERVAL_TICKS = 200L;
    private static final long PLAYER_SETTLEMENT_RECONCILE_INTERVAL_TICKS = 200L;
    private static final long CASTLE_MAINTENANCE_STEP_INTERVAL_TICKS = 200L;
    private static final int CASTLE_POPULATION_RECONCILE_BUDGET = 3;
    private static final int PLAYER_SETTLEMENT_RECONCILE_BUDGET = 2;
    private static final long CLAIM_OUTLINE_SYNC_INTERVAL_TICKS = 100L;
    private static final long KINGDOM_AI_INVASION_SCHEDULE_INTERVAL_TICKS = 100L;
    private static final int CLAIM_RADIUS_CHUNKS = CastleCityTierRegistry.maxClaimRadiusChunks();
    private static final int PLAYER_SETTLEMENT_CLAIM_RADIUS_CHUNKS = 1;
    private static final int PLAYER_KINGDOM_CLAIM_RADIUS_CHUNKS = 4;
    private static final int DYNAMIC_STONE_MIN_FOOTPRINT_CHUNKS = 9;
    private static final int HOUSE_LORD_COUNT = 3;
    private static final int MIN_SECONDARY_STRUCTURE_COUNT = 4;
    private static final int MAX_SECONDARY_STRUCTURE_COUNT = 6;
    private static final int LORDSHIP_MIN_SECONDARY_STRUCTURE_COUNT = 7;
    private static final int LORDSHIP_MAX_SECONDARY_STRUCTURE_COUNT = 9;
    private static final int MAX_CASTLE_PAD_CUT_DEPTH = 4;
    private static final int MAX_CLAIM_PAD_CUT_DEPTH = 3;
    private static final int MAX_CLAIM_TERRAIN_DELTA = 10;
    private static final int MAX_CLAIM_FLOOR_OFFSET = 4;
    private static final int SNOWY_CLAIM_TERRAIN_DELTA = 20;
    private static final int SNOWY_CLAIM_FLOOR_OFFSET = 18;
    private static final int CASTLE_BUILD_CLEARANCE_HEIGHT = 28;
    private static final int CASTLE_GENERATION_BLOCK_FLAGS = 50;
    private static final int CLAIM_STRUCTURE_CLEARANCE_HEIGHT = 18;
    private static final int INTERIOR_STRUCTURE_PLOT_PADDING = 3;
    private static final int DYNAMIC_CASTLE_CLAIM_CLEARANCE_RADIUS = 8;
    private static final int DYNAMIC_CASTLE_KEEP_BUFFER_BLOCKS = 8;
    private static final int DYNAMIC_CASTLE_ROAD_HALF_WIDTH = 2;
    private static final int DYNAMIC_CASTLE_WALL_MARGIN_BLOCKS = 6;
    private static final int DYNAMIC_CASTLE_WALL_SMOOTH_RADIUS = 2;
    private static final int DYNAMIC_CASTLE_NATURE_PLOT_SIZE = 16;
    private static final int DYNAMIC_CASTLE_NATURE_WALL_BUFFER_BLOCKS = 8;
    private static final int DYNAMIC_CASTLE_FLATTEN_EXTRA_BLOCKS = 0;
    private static final int HARBOUR_STRUCTURE_FORBIDDEN_BUFFER_CHUNKS = 2;
    private static final int HARBOUR_STRUCTURE_SEARCH_EXTRA_CHUNKS = 3;
    private static final int CASTLE_BARN_FORBIDDEN_BUFFER_CHUNKS = 3;
    private static final int CASTLE_BARN_SEARCH_EXTRA_CHUNKS = 4;
    private static final int CASTLE_BARN_GATE_BUFFER_CHUNKS = 2;
    private static final long NPC_RESPAWN_DELAY_TICKS = 2400L;
    private static final long HARBOUR_FISHERMAN_RETRY_DELAY_TICKS = 20L;
    private static final long CASTLE_STRUCTURE_RESIDENT_RETRY_DELAY_TICKS = 20L;
    private static final long CASTLE_ALERT_COOLDOWN_TICKS = 1200L;
    private static final long CASTLE_COOLDOWN_TICKS = 6000L;
    private static final long KINGDOM_AI_INVASION_DECISION_WINDOW_TICKS = 36000L;
    private static final int KINGDOM_AI_INVASION_TRIGGER_DENOMINATOR = 10;
    private static final Set<String> PRIORITY_KINGDOM_AI_RESOLUTION_QUEUE = new LinkedHashSet<String>();
    private static final int MAX_PRIORITY_KINGDOM_AI_RESOLUTIONS_PER_TICK = 8;
    private static final long CRUSADE_DURATION_TICKS = 6000L;
    private static final long RAID_DURATION_TICKS = 3600L;
    private static final long CONQUER_DURATION_TICKS = 7200L;
    private static final double RECAPTURE_COMBAT_RANGE_BLOCKS = 64.0;
    private static final double RECAPTURE_ATTACKER_MIN_SPAWN_RANGE = 36.0;
    private static final double RECAPTURE_ATTACKER_MAX_SPAWN_RANGE = 56.0;
    private static final double GATE_BREACH_SEARCH_RANGE = 200.0;
    private static final double GATE_BREACH_REACH = 3.0;
    private static final int GATE_BREACH_BASE_WORK = 60;
    private static final String VOYAGE_TAG_PREFIX = "agob_voyage_";
    private static final long FISHERMAN_SPAWN_LOG_WINDOW_MS = 5000L;
    private static final long VOYAGE_CONTROL_LOG_WINDOW_MS = 5000L;
    private static final double VOYAGE_AVOIDANCE_LOOKAHEAD_BLOCKS = 96.0;
    private static final double VOYAGE_AVOIDANCE_CLEARANCE_BLOCKS = 20.0;
    private static final double VOYAGE_AVOIDANCE_REACHED_DISTANCE_BLOCKS = 16.0;
    private static final long VOYAGE_AVOIDANCE_DURATION_TICKS = 60L;
    private static final long VOYAGE_CHECKPOINT_INTERVAL_TICKS = 200L;
    private static final double VOYAGE_CHECKPOINT_DISTANCE_BLOCKS = 64.0;
    private static final long TRAVEL_ENTITY_RESTORE_GRACE_TICKS = 200L;
    private static final boolean VOYAGE_DEBUG_LOGGING = Boolean.getBoolean("agob.debug.voyage");
    private static final int LEGACY_GATE_CHUNK_SUBMISSIONS_PER_TICK = 1;
    private static final int LEGACY_GATE_MAX_PENDING_CHUNKS = 32;
    private static final int LEGACY_GATE_SCAN_CHUNKS_PER_TICK = 2;
    private static final int LEGACY_GATE_SCAN_COLUMNS_PER_TICK = 64;
    private static final int GATE_STALE_VALIDATION_BUDGET_PER_TICK = 16;
    private static final long LEGACY_GATE_DISCOVERY_REPEAT_INTERVAL_TICKS = 1200L;
    private static final int VOYAGE_PREFERRED_SPAWN_MIN_DISTANCE_CHUNKS = 4;
    private static final int VOYAGE_MAX_SPAWN_SEARCH_DISTANCE_CHUNKS = 16;
    private static final Set<String> WEIRWOOD_ELIGIBLE_CASTLE_IDS = Set.of("deepwood_motte", "winterfell", "white_harbour", "the_dreadfort", "barrowton", "moat_calin", "last_hearth", "karhold", "torrhen_s_square", "castle_black", "white_tree", "craster_s_keep");
    private static final Set<String> CAPITAL_CASTLE_IDS = Set.of("winterfell", "eyrie", "riverrun", "casterly_rock", "pyke", "highgarden", "storm_s_end", "sunspear", "king_s_landing");
    private static final class_2960 CONQUER_CASTLE_ATTACK_DAMAGE_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"conquer_castle_attack_damage");
    private static final class_2960 CONQUER_CASTLE_HEALTH_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"conquer_castle_health");
    private static final double CONQUER_CASTLE_ATTACK_DAMAGE_BONUS = 1.0;
    private static final double CONQUER_CASTLE_HEALTH_BONUS = 2.0;
    private static final long TITLE_CHALLENGE_DURATION_TICKS = 1200L;
    private static final long TITLE_CHALLENGE_COOLDOWN_TICKS = 6000L;
    private static final long TOURNEY_CHALLENGE_DURATION_TICKS = 2400L;
    private static final long ARENA_BOUNDARY_CACHE_REFRESH_TICKS = 100L;
    private static final double ARENA_BOUNDARY_TRACKING_MARGIN = 8.0;
    private static final float NPC_PERMADEATH_CHANCE = 0.1f;
    private static final int PENDING_NPC_RESPAWN_BUDGET_PER_TICK = 2;
    private static Map<String, CastleDefinition> byId = Map.of();
    private static final Map<UUID, GateBreachProgress> ACTIVE_GATE_BREACHES = new HashMap<UUID, GateBreachProgress>();
    private static Map<String, OverlayPresetReference> presetReferencesByLegacyId = Map.of();
    private static Map<String, OverlayPresetReference> presetReferencesByCastleId = Map.of();
    private static Map<String, CastlePresetMetadata> presetMetadataByCastleId = Map.of();
    private static List<OverlayCastleSite> overlaySites = List.of();
    private static Set<Integer> knownMapBiomeColors = Set.of();
    private static Set<Integer> waterMapColors = Set.of();
    private static BufferedImage worldMapImage;
    private static final Map<Integer, Optional<class_2338>> CASTLELESS_COLOR_ORIGIN_CACHE;
    private static final ConcurrentLinkedQueue<PendingClaimCheck> PENDING_CLAIM_CHECKS;
    private static final PriorityQueue<PendingNpcRespawn> PENDING_NPC_RESPAWNS;
    private static final Map<class_5321<class_1937>, Set<Long>> FORCED_CASTLE_GENERATION_CHUNKS;
    private static final Map<class_5321<class_1937>, LinkedHashSet<String>> PRIORITIZED_CASTLE_GENERATION_IDS;
    private static final Map<class_5321<class_1937>, Map<String, class_2338>> CACHED_WEIRWOOD_SITE_PREDICTIONS;
    private static final Map<UUID, ActiveCastleInvasion> ACTIVE_CASTLE_INVASIONS;
    private static final Map<UUID, ActiveTitleChallenge> ACTIVE_TITLE_CHALLENGES;
    private static final Map<UUID, ActiveTourneyChallenge> ACTIVE_TOURNEY_CHALLENGES;
    private static final Map<UUID, Long> TITLE_CHALLENGE_COOLDOWNS;
    private static final Map<class_5321<class_1937>, ArenaBoundaryCache> ARENA_BOUNDARY_CACHES;
    private static final Map<UUID, ArenaEntitySnapshot> ARENA_ENTITY_SNAPSHOTS;
    private static final Set<UUID> ARENA_TELEPORT_GRACE;
    private static final Set<UUID> ARENA_LOGOUT_RELEASES;
    private static final Map<String, ActiveKingdomAiInvasion> ACTIVE_KINGDOM_AI_INVASIONS;
    private static final Map<UUID, ActiveVoyageSession> ACTIVE_VOYAGES;
    private static final Map<UUID, ActiveLandTravelSession> ACTIVE_LAND_TRAVELS;
    private static final Set<class_3218> HYDRATED_TRAVEL_WORLDS;
    private static final Map<class_3218, LegacyGateDiscoveryState> LEGACY_GATE_DISCOVERY;
    private static final Map<String, FishermanSpawnLogCounter> FISHERMAN_SPAWN_LOG_COUNTS;
    private static final Map<String, VoyageControlLogCounter> VOYAGE_CONTROL_LOG_COUNTS;
    private static final Map<class_5321<class_1937>, Integer> CLAIM_OUTLINE_PAYLOAD_SIGNATURES;
    @Nullable
    private static VoyageNetwork voyageNetwork;
    private static int castlePopulationCursor;
    private static int playerSettlementCursor;
    private static long fishermanSpawnLogWindowStartMs;
    private static long voyageControlLogWindowStartMs;
    private static final Set<MinecraftServer> RECONCILED_PLAYER_CLAIM_SERVERS;
    private static final Set<MinecraftServer> LOGGED_HARBOUR_CASTLE_SERVERS;
    private static final Map<String, Optional<CastleKeepTemplate>> CASTLE_KEEP_TEMPLATE_CACHE;
    private static final Set<String> LOGGED_DEFAULT_CASTLE_KEEP_CASTLES;
    private static final ThreadLocal<Integer> HOUSE_LORD_RECONCILE_DEPTH;
    private static final List<String> MARKET_STRUCTURE_PATHS;
    private static final List<String> NATURE_STRUCTURE_PATHS;
    private static final int HIREABLE_CAMPS_PER_TICK = 1;
    private static final int HIREABLE_CAMP_SCAN_LIMIT = 6;
    private static final int HIREABLE_CAMP_LOAD_RADIUS_CHUNKS = 3;
    private static final int HIREABLE_CAMP_MAX_TERRAIN_DELTA = 10;
    private static final int HIREABLE_CAMP_PAD_RADIUS = 3;
    private static final int WEIRWOODS_PER_TICK = 1;
    private static final int WEIRWOOD_SCAN_LIMIT = 4;
    private static final int WEIRWOOD_LOAD_RADIUS_CHUNKS = 6;
    private static final int WEIRWOOD_MIN_DISTANCE = 700;
    private static final int WEIRWOOD_MAX_DISTANCE = 900;
    private static final int WEIRWOOD_SITE_ATTEMPTS = 128;
    private static final int WEIRWOOD_MAX_TERRAIN_DELTA = 10;
    private static final int WEIRWOOD_MIN_CLEARANCE_Y = 10;
    private static final int WEIRWOOD_FALLBACK_RADIUS_STEP = 24;
    private static final int WEIRWOOD_FALLBACK_ANGLE_STEPS = 24;
    private static final int WEIRWOOD_FALLBACK_LOCAL_SEARCH_RADIUS = 24;
    private static final int WEIRWOOD_FALLBACK_LOCAL_SEARCH_STEP = 4;
    private static final long WEIRWOOD_FACE_BLEED_INTERVAL_TICKS = 2400L;
    private static final double WEIRWOOD_GOLDEN_ANGLE = 2.399963229728653;
    private static final class_2960 WEIRWOOD_FACE_LOOT_TABLE_ID;
    private static final String CASTLE_KEEP_RESOURCE_ROOT = "structure/castle/keep";
    private static final String DEFAULT_CASTLE_KEEP_FOLDER = "oldstones";
    private static final String NORTH_DEFAULT_CASTLE_KEEP_FOLDER = "the_dreadfort";
    private static final String DORNE_DEFAULT_CASTLE_KEEP_FOLDER = "yronwood";
    private static final String WESTERLANDS_DEFAULT_CASTLE_KEEP_FOLDER = "ashemark";
    private static final String WESTERLANDS_KINGDOM_ID = "agob:the_westerlands";
    private static final class_2960 STONE_CASTLE_CLAIM_STRUCTURE_ID;
    private static final class_2960 STONE_CASTLE_WALL_STRUCTURE_ID;
    private static final class_2960 STONE_CASTLE_CORNER_STRUCTURE_ID;
    private static final class_2960 STONE_CASTLE_GATE_STRUCTURE_ID;
    private static final class_2960 DYNAMIC_STONE_SETTLEMENT_TEMPLATE_ID;
    private static final class_2960 DYNAMIC_STONE_LORDSHIP_TEMPLATE_ID;
    private static final class_2960 DYNAMIC_STONE_CAPITAL_TEMPLATE_ID;
    private static final int DYNAMIC_STONE_SETTLEMENT_FOOTPRINT_CHUNKS = 4;
    private static final int DYNAMIC_STONE_LORDSHIP_FOOTPRINT_CHUNKS = 5;
    private static final int DYNAMIC_STONE_CAPITAL_FOOTPRINT_CHUNKS = 6;

    public static List<String> natureStructurePathsForWorldgen() {
        return NATURE_STRUCTURE_PATHS;
    }

    private GotCastles() {
    }

    public static void initialize() {
        ClassLoader classLoader = GotCastles.class.getClassLoader();
        byId = Map.copyOf(GotCastles.loadDefinitions());
        knownMapBiomeColors = GotCastles.loadKnownMapBiomeColors();
        waterMapColors = GotCastles.loadWaterMapColors();
        worldMapImage = CastleOverlayDataLoader.loadImage(WORLD_MAP_PATH, classLoader);
        CASTLELESS_COLOR_ORIGIN_CACHE.clear();
        CASTLE_KEEP_TEMPLATE_CACHE.clear();
        LOGGED_DEFAULT_CASTLE_KEEP_CASTLES.clear();
        OverlayPresetReferences presetReferences = CastleOverlayDataLoader.loadOverlayPresetReferences(DEFINITIONS_PATH, classLoader);
        presetReferencesByLegacyId = presetReferences.byLegacyId();
        presetReferencesByCastleId = presetReferences.byCastleId();
        overlaySites = CastleOverlayDataLoader.loadOverlaySites(presetReferencesByLegacyId, WOODEN_OVERLAY_PATH, 6302572, SETTLEMENT_DEFINITION_ID, STONE_OVERLAY_PATH, 65337, LORDSHIP_DEFINITION_ID, classLoader);
        GotCastles.syncInferredSubtypePresetFiles();
        presetMetadataByCastleId = CastleOverlayDataLoader.loadPresetMetadata(overlaySites, presetReferencesByCastleId, PRESETS_ROOT, classLoader);
        CommandRegistrationCallback.EVENT.register(GotCastles::registerCommands);
        DailyTaskRegistry.register(class_2960.method_60655((String)"agob", (String)"kingdom_ai_invasions"), GotCastles::runDailyKingdomAiInvasionTask);
        DailyTaskRegistry.register(class_2960.method_60655((String)"agob", (String)"tourneys"), GotCastles::runDailyTourneyTask);
        ServerPlayerEvents.COPY_FROM.register(GotCastles::copyPlayerCastle);
        ServerTickEvents.END_SERVER_TICK.register(GotCastles::tickServer);
        ServerTickEvents.END_WORLD_TICK.register(GotCastles::reconcileGateInvasionState);
        ServerLifecycleEvents.SERVER_STOPPING.register(GotCastles::checkpointActiveTravelSessions);
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> GotCastles.clearRuntimeTravelState());
        ServerWorldEvents.LOAD.register((server, world) -> {
            if (GotDimensionKeys.ICEANDFIRE.equals((Object)world.method_27983())) {
                CastleWorldState.get(world).initializeLayoutPinEra(world.method_8510() == 0L);
            }
        });
        ServerWorldEvents.UNLOAD.register((server, world) -> GotCastles.clearRuntimeWorldState(world));
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            class_3218 world = server.method_3847(GotDimensionKeys.ICEANDFIRE);
            if (world == null) {
                GotCastles.syncVoyageState(handler.field_14140, false, "", sender);
                return;
            }
            GotCastles.hydrateActiveTravelSessions(world);
            sender.sendPacket((class_8710)GotCastles.buildClaimOutlinePayload(world));
            sender.sendPacket((class_8710)GotCastles.buildCastleMapPayload(world, handler.field_14140));
            GotCastles.restoreVoyageOnJoin(world, handler.field_14140, sender);
            GotCastles.restoreLandTravelOnJoin(world, handler.field_14140, sender);
        });
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            class_3218 world = server.method_3847(GotDimensionKeys.ICEANDFIRE);
            if (world != null) {
                GotCastles.failCastleInvasion(world, handler.field_14140.method_5667(), true, false);
                GotCastles.failTourneyChallenge(world, handler.field_14140.method_5667(), false, false);
                GotCastles.handleCastleRecaptureDisconnect(world, handler.field_14140.method_5667(), false);
            }
        });
        UseBlockCallback.EVENT.register(GotCastles::queuePlayerClaimCheck);
        PlayerBlockBreakEvents.BEFORE.register(GotCastles::beforeBlockBreak);
        GotMod.LOGGER.info("Loaded {} castle definitions and {} overlay castle sites", (Object)byId.size(), (Object)overlaySites.size());
    }

    public static List<CastleDefinition> getAll() {
        return List.copyOf(byId.values());
    }

    public static CastleDefinition get(String id) {
        return byId.get(id);
    }

    public static class_1792[] resolveArmorForCastle(@Nullable class_3218 world, String castleId, @Nullable class_2960 kingdomId) {
        if (castleId != null && !castleId.isBlank()) {
            class_1792[] armor;
            CastleInstance placed;
            class_1792[] armor2;
            CastlePresetMetadata metadata = presetMetadataByCastleId.get(castleId);
            if (metadata != null && !metadata.houseArmorId().isBlank() && (armor2 = GotKingdomArmor.armorForId(metadata.houseArmorId())) != null) {
                return armor2;
            }
            String houseId = "";
            if (world != null && (placed = GotCastles.getWorldState(world).get(castleId)) != null && !placed.getOwnerHouseId().isBlank()) {
                houseId = placed.getOwnerHouseId();
            }
            if (houseId.isBlank() && metadata != null) {
                houseId = metadata.houseId();
            }
            if (!houseId.isBlank() && (armor = GotCastles.resolveArmorForHouse(houseId, kingdomId)) != null) {
                return armor;
            }
        }
        return GotKingdomArmor.armorForKingdom(kingdomId);
    }

    public static class_1792[] resolveArmorForHouse(String houseId, @Nullable class_2960 kingdomId) {
        String houseArmorId = GotCharacterOptions.resolveHouseArmorId(houseId, kingdomId);
        if (houseArmorId.isBlank()) {
            return null;
        }
        return GotKingdomArmor.armorForId(houseArmorId);
    }

    public static Map<class_2960, Integer> getCastleCountsByKingdom() {
        return presetMetadataByCastleId.values().stream().filter(metadata -> metadata != null && !metadata.kingdomId().isBlank()).map(metadata -> GotCastles.normalizeKingdomId(class_2960.method_12829((String)metadata.kingdomId()))).filter(identifier -> identifier != null).collect(Collectors.toMap(identifier -> identifier, identifier -> 1, Integer::sum, LinkedHashMap::new));
    }

    public static List<class_2338> getIndexedCastleAnchorsForKingdom(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return List.of();
        }
        return overlaySites.stream().filter(site -> {
            CastlePresetMetadata metadata = presetMetadataByCastleId.get(site.castleId());
            return metadata != null && kingdomId.equals(metadata.kingdomId());
        }).map(OverlayCastleSite::anchor).toList();
    }

    public static List<OverlayCastleSite> getOverlayCastleSites() {
        return List.copyOf(overlaySites);
    }

    @Nullable
    public static CastlePresetMetadata getOverlayCastleMetadata(String castleId) {
        return castleId == null || castleId.isBlank() ? null : presetMetadataByCastleId.get(castleId);
    }

    public static CastleTier resolveEffectiveCastleTierForWorldgen(@Nullable CastlePresetMetadata metadata, CastleTier fallback) {
        return GotCastles.resolveEffectiveCastleTier(metadata, fallback);
    }

    public static Set<CastleSubtype> resolveCastleSubtypesForWorldgen(OverlayCastleSite site, @Nullable CastlePresetMetadata metadata) {
        return GotCastles.resolveSubtypes(site, metadata);
    }

    public static String resolvePendingCastleDisplayName(OverlayCastleSite site) {
        return GotCastles.pendingDisplayName(site);
    }

    public static Optional<DynamicCastleWorldgenFootprint> resolveDynamicWorldgenFootprint(class_3218 world, String castleId, CastlePlacementPlan plan, CastleTier effectiveTier) {
        if (world == null || castleId == null || castleId.isBlank() || plan == null || effectiveTier == null) {
            return Optional.empty();
        }
        DynamicStoneCastleTierConfig config = GotCastles.dynamicStoneCastleConfig(effectiveTier);
        if (config == null) {
            return Optional.empty();
        }
        class_3485 templateManager = world.method_14183();
        Optional<CastleKeepTemplate> keepTemplate = GotCastles.loadCastleKeepTemplate(world, templateManager, castleId);
        return GotCastles.resolveDynamicWorldgenFootprint(plan, config, castleId, keepTemplate);
    }

    private static Optional<DynamicCastleWorldgenFootprint> resolveDynamicWorldgenFootprint(CastlePlacementPlan plan, DynamicStoneCastleTierConfig config, String castleId, Optional<CastleKeepTemplate> keepTemplate) {
        class_2382 footprintSize = keepTemplate.map(template -> GotCastles.expandDynamicFootprintForKeep(GotCastles.dynamicStoneFootprintSize(config), template)).orElse(GotCastles.dynamicStoneFootprintSize(config));
        int footprintOriginX = plan.origin().method_10263() - Math.max(0, (footprintSize.method_10263() - plan.footprintBlocks()) / 2);
        int footprintOriginZ = plan.origin().method_10260() - Math.max(0, (footprintSize.method_10260() - plan.footprintBlocks()) / 2);
        if (GotCastles.usesSouthOnlyCastleGeneration(castleId)) {
            int centerZ;
            footprintOriginZ = centerZ = plan.origin().method_10260() + plan.footprintBlocks() / 2;
        }
        if (GotCastles.isOriginalNightsWatchCastle(presetMetadataByCastleId.get(castleId) == null ? "" : presetMetadataByCastleId.get(castleId).kingdomId())) {
            int compactSizeX = Math.max(16, footprintSize.method_10263() - 32);
            int compactSizeZ = Math.max(16, footprintSize.method_10260() - 32);
            footprintOriginX += (footprintSize.method_10263() - compactSizeX) / 2;
            footprintOriginZ += (footprintSize.method_10260() - compactSizeZ) / 2;
            footprintSize = new class_2382(compactSizeX, footprintSize.method_10264(), compactSizeZ);
        }
        class_2338 footprintOrigin = new class_2338(footprintOriginX, plan.floorY(), footprintOriginZ);
        int clearanceHeight = keepTemplate.map(template -> Math.max(28, template.size().method_10264() + 4)).orElse(28);
        return Optional.of(new DynamicCastleWorldgenFootprint(footprintOrigin, footprintSize, clearanceHeight));
    }

    public static class_2960 getDynamicStoneWallStructureIdForWorldgen() {
        return STONE_CASTLE_WALL_STRUCTURE_ID;
    }

    public static class_2960 getDynamicStoneCornerStructureIdForWorldgen() {
        return STONE_CASTLE_CORNER_STRUCTURE_ID;
    }

    public static class_2960 getDynamicStoneGateStructureIdForWorldgen() {
        return STONE_CASTLE_GATE_STRUCTURE_ID;
    }

    public static class_2960 getDynamicWallStructureIdForWorldgen(@Nullable String kingdomId) {
        return class_2960.method_60655((String)"agob", (String)GotCastles.resolveDynamicWallStructurePlan(kingdomId).wallPath());
    }

    public static class_2960 getDynamicCornerStructureIdForWorldgen(@Nullable String kingdomId) {
        return class_2960.method_60655((String)"agob", (String)GotCastles.resolveDynamicWallStructurePlan(kingdomId).cornerPath());
    }

    public static class_2960 getDynamicGateStructureIdForWorldgen(@Nullable String kingdomId) {
        return class_2960.method_60655((String)"agob", (String)GotCastles.resolveDynamicWallStructurePlan(kingdomId).gatePath());
    }

    private static CastleWallStructurePlan resolveDynamicWallStructurePlan(@Nullable String kingdomId) {
        return CastleKingdomCityPlanRegistry.resolve(kingdomId == null ? "" : GotCastles.canonicalKingdomId(kingdomId)).wallStructurePlan();
    }

    public static Optional<DynamicCastleCoreWorldgenPlan> resolveDynamicCoreWorldgenPlan(class_3218 world, String castleId, CastlePlacementPlan plan, CastleTier effectiveTier) {
        if (world == null || castleId == null || castleId.isBlank() || plan == null || effectiveTier == null) {
            return Optional.empty();
        }
        DynamicStoneCastleTierConfig config = GotCastles.dynamicStoneCastleConfig(effectiveTier);
        if (config == null) {
            return Optional.empty();
        }
        class_3485 templateManager = world.method_14183();
        Optional<CastleKeepTemplate> keepTemplate = GotCastles.loadCastleKeepTemplate(world, templateManager, castleId);
        Optional<DynamicCastleWorldgenFootprint> optionalFootprint = GotCastles.resolveDynamicWorldgenFootprint(plan, config, castleId, keepTemplate);
        if (optionalFootprint.isEmpty()) {
            return Optional.empty();
        }
        DynamicCastleWorldgenFootprint footprint = optionalFootprint.get();
        int floorBlockY = plan.floorY() + 1;
        if (keepTemplate.isPresent()) {
            CastleKeepTemplate resolvedKeep = keepTemplate.get();
            class_2382 keepSize = resolvedKeep.size();
            class_2382 minOffset = resolvedKeep.minOffset();
            int assemblyMinX = footprint.origin().method_10263() + Math.max(0, (footprint.size().method_10263() - keepSize.method_10263()) / 2);
            int assemblyMinZ = footprint.origin().method_10260() + Math.max(0, (footprint.size().method_10260() - keepSize.method_10260()) / 2);
            class_2338 keepOrigin = new class_2338(assemblyMinX - minOffset.method_10263(), floorBlockY - minOffset.method_10264(), assemblyMinZ - minOffset.method_10260());
            class_2338 assemblyOrigin = new class_2338(assemblyMinX, floorBlockY, assemblyMinZ);
            class_2338 fallbackBannerPos = new class_2338(assemblyOrigin.method_10263() + keepSize.method_10263() / 2, assemblyOrigin.method_10264(), assemblyOrigin.method_10260() + keepSize.method_10260() / 2);
            return Optional.of(new DynamicCastleCoreWorldgenPlan(true, assemblyOrigin, keepSize, keepOrigin, keepSize, resolvedKeep.pieces().stream().map(piece -> new DynamicCastleKeepPiecePlan(piece.structureId(), piece.offset())).toList(), null, fallbackBannerPos));
        }
        class_3499 claimTemplate = templateManager.method_15094(STONE_CASTLE_CLAIM_STRUCTURE_ID).orElse(null);
        if (claimTemplate == null) {
            return Optional.empty();
        }
        class_2382 claimSize = claimTemplate.method_15160();
        class_2338 claimOrigin = new class_2338(footprint.origin().method_10263() + Math.max(0, (footprint.size().method_10263() - claimSize.method_10263()) / 2), floorBlockY, footprint.origin().method_10260() + Math.max(0, (footprint.size().method_10260() - claimSize.method_10260()) / 2));
        class_2338 fallbackBannerPos = new class_2338(claimOrigin.method_10263() + claimSize.method_10263() / 2, claimOrigin.method_10264(), claimOrigin.method_10260() + claimSize.method_10260() / 2);
        return Optional.of(new DynamicCastleCoreWorldgenPlan(false, claimOrigin, claimSize, claimOrigin, claimSize, List.of(), STONE_CASTLE_CLAIM_STRUCTURE_ID, fallbackBannerPos));
    }

    public static int getClaimRadiusChunks() {
        return CLAIM_RADIUS_CHUNKS;
    }

    static CastlePresetMetadata getPresetMetadata(String castleId) {
        return presetMetadataByCastleId.get(castleId);
    }

    static double recaptureAttackerMinSpawnRange() {
        return 36.0;
    }

    static double recaptureAttackerMaxSpawnRange() {
        return 56.0;
    }

    static void clearActiveGateBreach(UUID entityUuid) {
        ACTIVE_GATE_BREACHES.remove(entityUuid);
    }

    private static class_2960 normalizeKingdomId(class_2960 kingdomId) {
        if (kingdomId == null) {
            return null;
        }
        if (kingdomId.method_12836().equals("agob") && kingdomId.method_12832().equals("iron_islands")) {
            return GotKingdoms.THE_IRON_ISLANDS.getId();
        }
        return kingdomId;
    }

    public static CastleWorldState getWorldState(class_3218 world) {
        return CastleWorldState.get(world);
    }

    public static boolean isCastleTravelReady(class_3218 world, String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return false;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        if (state.contains(castleId)) {
            return !state.hasPendingGeneration(castleId);
        }
        return false;
    }

    public static boolean requestPriorityCastleGeneration(class_3218 world, String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return false;
        }
        CastleInstance claim = GotCastles.ensureWorldgenCastleClaim(world, castleId, true);
        if (claim == null) {
            GotCastles.clearPriorityCastleGeneration(world, castleId);
            return false;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        PendingCastleGeneration pendingGeneration = state.getPendingGeneration(castleId);
        if (pendingGeneration == null) {
            GotCastles.clearPriorityCastleGeneration(world, castleId);
            return true;
        }
        boolean layoutContractCurrent = GotCastles.isPendingLayoutContractCurrent(world, state, pendingGeneration);
        if (!GotCastles.canEnrollPriorityPending(pendingGeneration, layoutContractCurrent)) {
            GotCastles.clearPriorityCastleGeneration(world, castleId);
            return false;
        }
        GotCastles.enrollPriorityCastleGeneration((class_5321<class_1937>)world.method_27983(), castleId);
        return true;
    }

    private static boolean canEnrollPriorityPending(@Nullable PendingCastleGeneration pendingGeneration, boolean layoutContractCurrent) {
        return pendingGeneration != null && layoutContractCurrent;
    }

    @Nullable
    public static CastleInstance ensureNearestWorldgenCastleClaim(class_3218 world, class_2338 pos, int maxDistanceBlocks) {
        if (world == null || pos == null || maxDistanceBlocks < 0) {
            return null;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        OverlayCastleSite nearest = null;
        long nearestDistanceSq = Long.MAX_VALUE;
        long maxDistanceSq = (long)maxDistanceBlocks * (long)maxDistanceBlocks;
        for (OverlayCastleSite site : overlaySites) {
            long dz;
            long dx = (long)site.anchor().method_10263() - (long)pos.method_10263();
            long distanceSq = dx * dx + (dz = (long)site.anchor().method_10260() - (long)pos.method_10260()) * dz;
            if (distanceSq > maxDistanceSq || distanceSq >= nearestDistanceSq) continue;
            nearest = site;
            nearestDistanceSq = distanceSq;
        }
        if (nearest == null) {
            return null;
        }
        return GotCastles.ensureWorldgenCastleClaim(world, nearest.castleId(), true);
    }

    @Nullable
    public static CastleInstance ensureWorldgenCastleClaim(class_3218 world, String castleId, boolean syncPlayers) {
        return GotCastles.ensureWorldgenCastleClaim(world, castleId, syncPlayers, true);
    }

    @Nullable
    private static CastleInstance ensureWorldgenCastleClaim(class_3218 world, String castleId, boolean syncPlayers, boolean spawnNpcs) {
        CastleInstance instance;
        if (world == null || castleId == null || castleId.isBlank()) {
            return null;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        CastleInstance existing = state.get(castleId);
        OverlayCastleSite site = GotCastles.findOverlaySite(castleId);
        if (site == null || state.isRejected(castleId)) {
            return null;
        }
        CastleWorldgenPlan plan = CastlePlanCache.get(world, castleId).orElse(null);
        if (plan == null) {
            GotMod.LOGGER.warn("Cannot create worldgen castle claim for {} because no castle plan exists.", (Object)castleId);
            return null;
        }
        PendingCastleGeneration pendingGeneration = state.getPendingGeneration(castleId);
        if (GotCastles.isInitializedCastleClaim(existing) && pendingGeneration == null) {
            if (!GotCastles.ensureClaimLayoutContract(world, state, null, plan)) {
                return existing;
            }
            GotCastles.ensureChunkNativeWorldgenCastleShell(world, castleId);
            GotCastles.clearPriorityCastleGeneration(world, castleId);
            return existing;
        }
        if (pendingGeneration == null) {
            pendingGeneration = new PendingCastleGeneration(site.castleId(), site.definitionId(), site.index(), site.anchor());
            pendingGeneration.setRoadLayoutVersion(existing == null ? state.layoutPinEra() : 1);
            state.putPendingGeneration(pendingGeneration);
        }
        if (!GotCastles.ensureClaimLayoutContract(world, state, pendingGeneration, plan)) {
            if (pendingGeneration != null) {
                GotCastles.logPendingLayoutContractBlocked(world, state, pendingGeneration);
            }
            return GotCastles.isInitializedCastleClaim(existing) ? existing : null;
        }
        if (pendingGeneration != null) {
            state.putPendingGeneration(pendingGeneration);
        }
        if (GotCastles.isInitializedCastleClaim(existing)) {
            GotCastles.ensureChunkNativeWorldgenCastleShell(world, castleId);
            return existing;
        }
        if (plan != null && !GotCastles.ensureChunkNativeWorldgenCastleShell(world, plan)) {
            GotMod.LOGGER.warn("Cannot create worldgen castle claim for {} because its physical castle shell could not be placed.", (Object)castleId);
            return null;
        }
        CastleInstance castleInstance = instance = plan == null ? GotCastles.createLightweightCastleInstance(world, site) : GotCastles.createCastleInstanceFromWorldgenPlan(world, plan);
        if (instance == null) {
            state.markRejected(castleId);
            return null;
        }
        GotCastles.mergeExistingCastleClaimState(existing, instance);
        state.put(instance);
        if (!state.hasPendingGeneration(castleId)) {
            GotCastles.clearPriorityCastleGeneration(world, castleId);
        }
        GotCastles.ensureWorldgenCastleRuntime(world, state, instance, syncPlayers, spawnNpcs, true);
        if (!GotCastles.hasRemainingOverlayCastleWork(state)) {
            state.setOverlayCastlesGenerated(true);
        }
        return instance;
    }

    private static boolean ensureClaimLayoutContract(class_3218 world, CastleWorldState state, @Nullable PendingCastleGeneration pendingGeneration, @Nullable CastleWorldgenPlan plan) {
        if (plan == null) {
            return false;
        }
        if (pendingGeneration != null) {
            return GotCastles.initializePendingRoadLayout(world, state, pendingGeneration);
        }
        CastleWorldState.CastleLayoutPin pin = state.getLayoutPin(plan.castleId());
        if (pin == null) {
            CastlePlanCache.isCurrentLayoutPinned(world, plan);
            pin = state.getLayoutPin(plan.castleId());
        }
        if (pin == null || pin.version() != 2) {
            return pin != null;
        }
        return !pin.fullPlanFingerprint().isBlank() && pin.fullPlanFingerprint().equals(CastleRoadNetworkFingerprint.compute(plan));
    }

    private static boolean isInitializedCastleClaim(@Nullable CastleInstance castle) {
        return castle != null && !castle.getCityStructureClaims().isEmpty() && !castle.getCastleNpcProfiles().isEmpty();
    }

    private static boolean ensureChunkNativeWorldgenCastleShell(class_3218 world, String castleId) {
        if (castleId == null || castleId.isBlank()) {
            return true;
        }
        CastleWorldgenPlan plan = CastlePlanCache.get(world, castleId).orElse(null);
        return plan != null && GotCastles.ensureChunkNativeWorldgenCastleShell(world, plan);
    }

    private static boolean ensureChunkNativeWorldgenCastleShell(class_3218 world, CastleWorldgenPlan plan) {
        if (world == null || plan == null) {
            return true;
        }
        return true;
    }

    private static boolean forcePlaceWorldgenCastleShell(class_3218 world, CastleWorldgenPlan plan) {
        if (world == null || plan == null) {
            return false;
        }
        DynamicCastleTerrainApplier.applyFull(world, plan);
        if (plan.corePlan() != null && DynamicCastleCoreApplier.findExistingBanner(world, plan.corePlan()).isEmpty() && DynamicCastleCoreApplier.applyFull(world, plan.corePlan()).isEmpty()) {
            return false;
        }
        CastleWorldgenStructureApplier.applyFull(world, plan);
        DynamicCastleWallApplier.applyFull(world, plan);
        return true;
    }

    private static void ensureWorldgenCastleRuntime(class_3218 world, CastleWorldState state, CastleInstance instance, boolean syncPlayers, boolean spawnNpcs, boolean forceSync) {
        boolean changed = GotCastles.ensureCastleNpcProfiles(world, instance, null);
        if (changed) {
            state.method_80();
        }
        if (spawnNpcs && !GotCastles.isCastleUnderActivePlayerInvasion(instance.getId())) {
            changed |= GotCastles.reconcileCastlePopulation(world, instance);
        }
        if (syncPlayers && (forceSync || changed)) {
            GotCastles.syncExternalClaimOutlines(world);
            GotCastles.syncCastleMapToPlayers(world);
        }
    }

    private static void mergeExistingCastleClaimState(@Nullable CastleInstance existing, CastleInstance replacement) {
        if (existing == null || replacement == null) {
            return;
        }
        replacement.setOwnerPlayerUuid(existing.getOwnerPlayerUuid());
        if (!existing.getOwnerKingdomId().isBlank()) {
            replacement.setOwnerKingdomId(existing.getOwnerKingdomId());
        }
        if (!existing.getOwnerHouseId().isBlank()) {
            replacement.setOwnerHouseId(existing.getOwnerHouseId());
        }
        if (!existing.getOwnerReligionId().isBlank()) {
            replacement.setOwnerReligionId(existing.getOwnerReligionId());
        }
        if (!existing.getLordName().isBlank()) {
            replacement.setLordName(existing.getLordName());
        }
        replacement.setRaidState(existing.getRaidState());
        replacement.setRaidEndsAtTick(existing.getRaidEndsAtTick());
        replacement.setLastPopulationRefreshDay(existing.getLastPopulationRefreshDay());
        replacement.setLastObservedPopulationTimeOfDay(existing.getLastObservedPopulationTimeOfDay());
        replacement.setSettlementTier(existing.getSettlementTier());
        replacement.setSettlementLevel(existing.getSettlementLevel());
        replacement.setCreatedDay(existing.getCreatedDay());
        replacement.setLastSettlerOfferDay(existing.getLastSettlerOfferDay());
        replacement.setLastSettlerProductionDay(existing.getLastSettlerProductionDay());
        replacement.setSettlers(existing.getSettlers());
        for (UUID memberUuid : existing.getMemberUuids()) {
            replacement.addMember(memberUuid);
        }
    }

    @Nullable
    private static CastleInstance createLightweightCastleInstance(class_3218 world, OverlayCastleSite site) {
        if (world == null || site == null) {
            return null;
        }
        CastleDefinition definition = GotCastles.get(site.definitionId());
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(site.castleId());
        CastleTier fallbackTier = definition == null ? CastleTier.SETTLEMENT : definition.tier();
        CastleTier effectiveTier = GotCastles.resolveEffectiveCastleTier(metadata, fallbackTier);
        CastleCityTierPlan cityTierPlan = CastleCityTierRegistry.forTier(effectiveTier);
        DynamicStoneCastleTierConfig dynamicConfig = GotCastles.dynamicStoneCastleConfig(effectiveTier);
        class_2960 templateId = dynamicConfig == null ? DYNAMIC_STONE_SETTLEMENT_TEMPLATE_ID : dynamicConfig.templateId();
        class_2338 bannerPos = new class_2338(site.anchor().method_10263(), Math.max(1, world.method_8615() + 1), site.anchor().method_10260());
        String displayName = metadata != null && !metadata.name().isBlank() ? metadata.name() : GotCastles.pendingDisplayName(site);
        CastleTitle title = new CastleTitle(site.castleId() + "_title", site.castleId(), (effectiveTier == CastleTier.SETTLEMENT ? "Steward of " : "Lord of ") + displayName, effectiveTier);
        int footprintChunks = cityTierPlan.flattenFootprintChunks();
        CastleInstance instance = new CastleInstance(site.castleId(), site.definitionId(), effectiveTier, templateId, bannerPos, bannerPos, footprintChunks, cityTierPlan.claimRadiusChunks(), Math.max(24, footprintChunks * 8), true, title, effectiveTier.defaultLootTableId());
        String kingdomId = GotCastles.resolvePendingCastleKingdomId(world, site, metadata);
        String houseId = GotCastles.resolveOwnedHouseId("", kingdomId, metadata != null ? metadata.houseId() : "");
        String religionId = GotCastles.resolvePendingCastleReligionId(metadata, kingdomId);
        String lordName = GotCastles.resolvePendingLordName(world, site, metadata, kingdomId);
        PendingCastleOwnershipWorldState.PendingCastleOwnership pendingOwnership = PendingCastleOwnershipWorldState.get(world).get(site.castleId());
        if (pendingOwnership != null) {
            if (!pendingOwnership.ownerKingdomId().isBlank()) {
                kingdomId = pendingOwnership.ownerKingdomId();
            }
            houseId = GotCastles.resolveOwnedHouseId(pendingOwnership.ownerHouseId(), kingdomId, metadata != null ? metadata.houseId() : "");
            if (!pendingOwnership.ownerReligionId().isBlank()) {
                religionId = pendingOwnership.ownerReligionId();
            }
            if (!pendingOwnership.lordName().isBlank()) {
                lordName = pendingOwnership.lordName();
            }
            if (!pendingOwnership.ownerPlayerUuid().isBlank()) {
                instance.setOwnerPlayerUuid(GotCastles.parseUuid(pendingOwnership.ownerPlayerUuid()));
            }
        }
        if (!kingdomId.isBlank()) {
            instance.setOwnerKingdomId(kingdomId);
        }
        if (!houseId.isBlank()) {
            instance.setOwnerHouseId(houseId);
        }
        if (!religionId.isBlank()) {
            instance.setOwnerReligionId(religionId);
        }
        instance.setLordName(lordName);
        for (CastleSubtype subtype : GotCastles.resolveSubtypes(site, metadata)) {
            instance.addSubtype(subtype);
        }
        return instance;
    }

    @Nullable
    private static CastleInstance createCastleInstanceFromWorldgenPlan(class_3218 world, CastleWorldgenPlan plan) {
        if (world == null || plan == null || plan.dynamicFootprint() == null) {
            return null;
        }
        CastleCityTierPlan cityTierPlan = CastleCityTierRegistry.forTier(plan.effectiveTier());
        DynamicStoneCastleTierConfig dynamicConfig = GotCastles.dynamicStoneCastleConfig(plan.effectiveTier());
        class_2960 templateId = dynamicConfig == null ? plan.placementPlan().templateKey().structureId() : dynamicConfig.templateId();
        class_2338 bannerPos = plan.corePlan() == null ? plan.anchor() : plan.corePlan().fallbackBannerPos();
        String displayName = GotCastles.generatedCastleName((class_6880<class_1959>)world.method_23753(plan.site().anchor()), plan.effectiveTier(), plan.site().index());
        CastleTitle title = new CastleTitle(plan.castleId() + "_title", plan.castleId(), (plan.effectiveTier() == CastleTier.SETTLEMENT ? "Steward of " : "Lord of ") + displayName, plan.effectiveTier());
        CastleInstance instance = new CastleInstance(plan.castleId(), plan.definitionId(), plan.effectiveTier(), templateId, plan.dynamicFootprint().origin(), bannerPos, class_3532.method_15386((float)((float)Math.max(plan.dynamicFootprint().size().method_10263(), plan.dynamicFootprint().size().method_10260()) / 16.0f)), cityTierPlan.claimRadiusChunks(), Math.max(24, (plan.dynamicFootprint().size().method_10263() + plan.dynamicFootprint().size().method_10260()) / 4), true, title, plan.effectiveTier().defaultLootTableId());
        CastlePresetMetadata metadata = plan.metadata();
        Kingdom kingdom = GotCastles.resolveKingdomForBiome(world, plan.anchor());
        String kingdomId = metadata != null && !metadata.kingdomId().isBlank() ? metadata.kingdomId() : (kingdom != null ? kingdom.getId().toString() : "");
        String houseId = GotCastles.resolveOwnedHouseId("", kingdomId, metadata != null ? metadata.houseId() : "");
        String religionId = metadata != null && !metadata.religionId().isBlank() ? metadata.religionId() : GotCastles.defaultReligionIdForKingdom(kingdomId);
        String lordName = GotCastles.resolvePendingLordName(world, plan.site(), metadata, kingdomId);
        PendingCastleOwnershipWorldState.PendingCastleOwnership pendingOwnership = PendingCastleOwnershipWorldState.get(world).get(plan.castleId());
        if (pendingOwnership != null) {
            if (!pendingOwnership.ownerKingdomId().isBlank()) {
                kingdomId = pendingOwnership.ownerKingdomId();
            }
            houseId = GotCastles.resolveOwnedHouseId(pendingOwnership.ownerHouseId(), kingdomId, metadata != null ? metadata.houseId() : "");
            if (!pendingOwnership.ownerReligionId().isBlank()) {
                religionId = pendingOwnership.ownerReligionId();
            }
            if (!pendingOwnership.lordName().isBlank()) {
                lordName = pendingOwnership.lordName();
            }
            if (!pendingOwnership.ownerPlayerUuid().isBlank()) {
                instance.setOwnerPlayerUuid(GotCastles.parseUuid(pendingOwnership.ownerPlayerUuid()));
            }
        }
        if (!kingdomId.isBlank()) {
            instance.setOwnerKingdomId(kingdomId);
        }
        if (!houseId.isBlank()) {
            instance.setOwnerHouseId(houseId);
        }
        if (!religionId.isBlank()) {
            instance.setOwnerReligionId(religionId);
        }
        instance.setLordName(lordName);
        for (CastleSubtype subtype : plan.subtypes()) {
            instance.addSubtype(subtype);
        }
        instance.setWallGateClaims(plan.wallGateClaims());
        GotCastles.seedCastleStructureResidentProfiles(instance, plan.structures().stream().map(GotCastles::toPlacedCastleStructure).toList());
        return instance;
    }

    private static PlacedCastleStructure toPlacedCastleStructure(CastleWorldgenStructurePlan plan) {
        return new PlacedCastleStructure(plan.residentPlan(), new StructurePlacementResult(plan.footprint(), new class_2338(plan.footprint().method_35415(), plan.footprint().method_35416(), plan.footprint().method_35417()), plan.size(), plan.pointOfInterest(), plan.residentSpawnPos()));
    }

    private static void enrollPriorityCastleGeneration(class_5321<class_1937> worldKey, String castleId) {
        if (worldKey == null || castleId == null || castleId.isBlank()) {
            return;
        }
        PRIORITIZED_CASTLE_GENERATION_IDS.computeIfAbsent(worldKey, ignored -> new LinkedHashSet()).add(castleId);
    }

    private static void clearPriorityCastleGeneration(class_3218 world, String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return;
        }
        GotCastles.clearPriorityCastleGeneration((class_5321<class_1937>)world.method_27983(), castleId);
    }

    private static void clearPriorityCastleGeneration(class_5321<class_1937> worldKey, String castleId) {
        if (worldKey == null || castleId == null || castleId.isBlank()) {
            return;
        }
        LinkedHashSet<String> prioritizedIds = PRIORITIZED_CASTLE_GENERATION_IDS.get(worldKey);
        if (prioritizedIds == null) {
            return;
        }
        prioritizedIds.remove(castleId);
        if (prioritizedIds.isEmpty()) {
            PRIORITIZED_CASTLE_GENERATION_IDS.remove(worldKey);
        }
    }

    private static void removePendingCastleGeneration(class_3218 world, CastleWorldState state, String castleId) {
        state.removePendingGeneration(castleId);
        GotCastles.clearPriorityCastleGeneration(world, castleId);
    }

    public static Optional<CastlePlacementPlan> planPlacement(class_3218 world, String definitionId, class_2338 anchor) {
        return GotCastles.planPlacement(world, definitionId, anchor, false);
    }

    public static Optional<CastlePlacementPlan> planPlacement(class_3218 world, String definitionId, class_2338 anchor, boolean harbourPreferred) {
        CastleDefinition definition = GotCastles.get(definitionId);
        if (definition == null) {
            return Optional.empty();
        }
        return CastlePlacementPlanner.createPlan(world, definition, anchor, harbourPreferred);
    }

    public static class_2338 resolvePlacementAnchor(class_3218 world, OverlayCastleSite site) {
        if (site == null) {
            return class_2338.field_10980;
        }
        if (world == null || !"castle_black".equals(site.castleId())) {
            return site.anchor();
        }
        return GotCastles.shiftCastleBlackPlacementAnchor(world, site.anchor());
    }

    private static class_2338 shiftCastleBlackPlacementAnchor(class_3218 world, class_2338 anchor) {
        int pushX = 0;
        int pushZ = 0;
        int[] sampleDistances = new int[]{32, 64, 96};
        class_2350[] sampleDirections = new class_2350[]{class_2350.field_11043, class_2350.field_11035, class_2350.field_11039, class_2350.field_11034};
        for (int distance : sampleDistances) {
            for (class_2350 direction : sampleDirections) {
                if (!world.method_23753(anchor.method_10079(direction, distance)).method_40225(GotBiomeKeys.THE_WALL)) continue;
                pushX -= direction.method_10148();
                pushZ -= direction.method_10165();
            }
        }
        if (pushX == 0 && pushZ == 0) {
            return anchor;
        }
        int shiftDistance = 48;
        int shiftX = Integer.signum(pushX) * shiftDistance;
        int shiftZ = Integer.signum(pushZ) * shiftDistance;
        class_2338 shifted = anchor.method_10069(shiftX, 0, shiftZ);
        for (int attempt = 0; attempt < 3 && world.method_23753(shifted).method_40225(GotBiomeKeys.THE_WALL); ++attempt) {
            shifted = shifted.method_10069(shiftX, 0, shiftZ);
        }
        return shifted;
    }

    public static boolean usesSouthOnlyCastleGeneration(String castleId) {
        return "castle_black".equals(castleId);
    }

    private static void tickServer(MinecraftServer server) {
        boolean idleOverlayScanDue;
        GotCastles.processPendingClaimChecks(server);
        class_3218 world = server.method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return;
        }
        if (voyageNetwork == null) {
            voyageNetwork = GotCastles.buildVoyageNetwork();
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        long worldTime = world.method_8510();
        GotCastles.hydrateActiveTravelSessions(world);
        if (worldTime % 100L == 0L) {
            GotCastles.logHarbourCastlesOnce(server, world);
            GotCastles.flushFishermanSpawnAttemptLogs(System.currentTimeMillis(), false);
            if (VOYAGE_DEBUG_LOGGING) {
                GotCastles.flushVoyageControlLogs(System.currentTimeMillis(), false);
            }
            GotCastles.reconcilePlayerClaimsConfig(server, world);
        }
        GotCastles.tickActiveInvasions(world);
        GotCastles.tickActiveTitleChallenges(world);
        GotCastles.tickActiveTourneyChallenges(world);
        GotCastles.tickArenaBoundaries(world);
        GotCastles.tickCastleRecaptures(world);
        GotCastles.tickKingdomAiInvasions(world, false);
        if (worldTime % 100L == 0L) {
            GotCastles.scheduleKingdomAiInvasions(world);
        }
        if (worldTime % 1200L == 0L) {
            GotCastles.reconcileHouseLordAssignments(world);
        }
        GotCastles.tickActiveVoyages(world);
        GotCastles.tickActiveLandTravels(world);
        GotCastles.processPendingNpcRespawns(world);
        GotCastles.processWeirwoodFaceTimers(world, worldTime);
        if (worldTime % 200L == 0L) {
            GotCastles.updateCastleDiscovery(world);
        }
        if (worldTime % 200L == 0L) {
            GotCastles.runCastleMaintenanceBatch(world);
        }
        GotCastles.processHireableCampQueue(world, state);
        GotCastles.processWeirwoodQueue(world);
        boolean hasActiveOverlayWork = GotCastles.hasRemainingOverlayCastleWork(state);
        boolean hasPriorityOverlayWork = GotCastles.hasPriorityOverlayCastleWork(world);
        boolean bl = idleOverlayScanDue = worldTime % 20L == 0L;
        if (hasActiveOverlayWork || hasPriorityOverlayWork || idleOverlayScanDue) {
            GotCastles.processOverlayCastleQueue(world, state, hasPriorityOverlayWork || idleOverlayScanDue);
        }
        if (state.overlayCastlesGenerated() && GotCastles.hasRemainingOverlayCastleWork(state)) {
            state.setOverlayCastlesGenerated(false);
        }
        if (state.overlayCastlesGenerated()) {
            if (worldTime % 100L == 0L) {
                GotCastles.syncClaimOutlines(world);
            }
            return;
        }
        if (worldTime % 100L == 0L) {
            GotCastles.syncClaimOutlines(world);
        }
    }

    private static void runCastleMaintenanceBatch(class_3218 world) {
        CastleWorldState state = GotCastles.getWorldState(world);
        ArrayList<CastleInstance> castles = new ArrayList<CastleInstance>(state.getAll());
        if (castles.isEmpty()) {
            castlePopulationCursor = 0;
            playerSettlementCursor = 0;
            return;
        }
        boolean changed = GotCastles.reconcileLoadedCastlePopulationBatch(world, castles, 3);
        if (changed |= GotCastles.reconcilePlayerSettlementSettlersBatch(world, castles, 2)) {
            state.method_80();
        }
    }

    private static void runDailyTourneyTask(class_3218 world, DailyTaskContext context) {
        if (world == null || context == null) {
            return;
        }
        TourneyWorldState state = TourneyWorldState.get(world);
        LinkedHashSet<String> kingdoms = new LinkedHashSet<String>();
        for (StrategicCastleSnapshot castle : GotCastles.allTourneyEligibleCastles(world)) {
            if (castle.ownerKingdomId().isBlank()) continue;
            kingdoms.add(castle.ownerKingdomId());
        }
        for (String staleKingdomId : state.activeKingdomIds()) {
            if (kingdoms.contains(staleKingdomId)) continue;
            state.remove(staleKingdomId);
        }
        for (String kingdomId : kingdoms) {
            List<StrategicCastleSnapshot> castles = GotCastles.getStrategicCastlesForKingdom(world, kingdomId).stream().filter(GotCastles::isTourneyEligibleCastle).sorted(Comparator.comparing(StrategicCastleSnapshot::castleId)).toList();
            if (castles.isEmpty()) {
                state.remove(kingdomId);
                continue;
            }
            int index = Math.floorMod(state.cursor(kingdomId), castles.size());
            StrategicCastleSnapshot castle = castles.get(index);
            int nextCursor = (index + 1) % castles.size();
            String tourneyId = kingdomId + "|" + castle.castleId() + "|" + context.day();
            state.put(kingdomId, new TourneyWorldState.TourneyEntry(tourneyId, castle.castleId(), castle.displayName(), kingdomId, castle.ownerHouseId(), castle.lordName(), context.day()), nextCursor);
            GotCastles.appendKingdomEventLine(world, castle.lordName() + " has announced a tourney in " + castle.displayName(), GotCastles.houseSigilTextureId(castle.ownerHouseId(), kingdomId), kingdomId);
        }
        GotCastles.syncCastleMapToPlayers(world);
    }

    private static List<StrategicCastleSnapshot> allTourneyEligibleCastles(class_3218 world) {
        StrategicCastleSnapshot snapshot;
        ArrayList<StrategicCastleSnapshot> castles = new ArrayList<StrategicCastleSnapshot>();
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            snapshot = GotCastles.resolveStrategicCastle(world, castle.getId());
            if (!GotCastles.isTourneyEligibleCastle(snapshot)) continue;
            castles.add(snapshot);
        }
        for (OverlayCastleSite site : overlaySites) {
            if (GotCastles.getWorldState(world).contains(site.castleId()) || GotCastles.getWorldState(world).isRejected(site.castleId()) || !GotCastles.isTourneyEligibleCastle(snapshot = GotCastles.resolveStrategicCastle(world, site.castleId()))) continue;
            castles.add(snapshot);
        }
        return castles;
    }

    private static boolean isTourneyEligibleCastle(@Nullable StrategicCastleSnapshot castle) {
        return castle != null && !castle.ownerKingdomId().isBlank() && castle.tier() != CastleTier.SETTLEMENT && !castle.playerClaim();
    }

    private static void logHarbourCastlesOnce(MinecraftServer server, class_3218 world) {
        if (!LOGGED_HARBOUR_CASTLE_SERVERS.add(server)) {
            return;
        }
        List<OverlayCastleSite> harbourSites = overlaySites.stream().filter(GotCastles::isInferredHarbourSite).toList();
        GotMod.LOGGER.info("Inferred {} harbour castle(s) for {}", (Object)harbourSites.size(), (Object)world.method_27983().method_29177());
        for (OverlayCastleSite site : harbourSites) {
            CastlePresetMetadata metadata = presetMetadataByCastleId.get(site.castleId());
            String displayName = metadata != null && !metadata.name().isBlank() ? metadata.name() : GotCastles.pendingDisplayName(site);
            GotMod.LOGGER.info("Harbour castle candidate id={} name={} anchor=({}, {}) definition={}", new Object[]{site.castleId(), displayName, site.anchor().method_10263(), site.anchor().method_10260(), site.definitionId()});
        }
    }

    private static void processPendingClaimChecks(MinecraftServer server) {
        PendingClaimCheck check;
        while ((check = PENDING_CLAIM_CHECKS.poll()) != null) {
            class_3218 world = server.method_3847(check.dimension());
            class_3222 player = server.method_3760().method_14602(check.playerUuid());
            if (world == null || player == null || !player.method_37908().method_27983().equals(check.dimension())) continue;
            if (!world.method_8393(check.bannerPos().method_10263() >> 4, check.bannerPos().method_10260() >> 4)) {
                PENDING_CLAIM_CHECKS.add(check);
                return;
            }
            GotCastles.tryCreatePlayerClaim(world, player, check.bannerPos());
        }
    }

    private static void syncClaimOutlines(class_3218 world) {
        if (world.method_8510() % 100L != 0L) {
            return;
        }
        GotCastles.syncStateToPlayers(world, false);
    }

    static void syncStateToPlayers(class_3218 world) {
        GotCastles.syncStateToPlayers(world, true);
    }

    private static void syncStateToPlayers(class_3218 world, boolean force) {
        ClaimOutlinePayload payload = GotCastles.buildClaimOutlinePayload(world);
        int signature = payload.hashCode();
        Integer previousSignature = CLAIM_OUTLINE_PAYLOAD_SIGNATURES.get(world.method_27983());
        if (!force && previousSignature != null && previousSignature == signature) {
            return;
        }
        CLAIM_OUTLINE_PAYLOAD_SIGNATURES.put((class_5321<class_1937>)world.method_27983(), signature);
        for (class_3222 player : world.method_18456()) {
            if (!GotCastles.canSendPlayPackets(player)) continue;
            ServerPlayNetworking.send((class_3222)player, (class_8710)payload);
        }
    }

    public static void syncExternalClaimOutlines(class_3218 world) {
        if (world == null) {
            return;
        }
        GotCastles.syncStateToPlayers(world);
    }

    public static void syncCastleMapToPlayers(class_3218 world) {
        for (class_3222 player : world.method_18456()) {
            if (!GotCastles.canSendPlayPackets(player)) continue;
            ServerPlayNetworking.send((class_3222)player, (class_8710)GotCastles.buildCastleMapPayload(world, player));
            PlayerHouseService.syncTo(player);
        }
    }

    public static TitleChallengeAvailability describeTitleChallenge(class_3222 player, AbstractKingdomNpcEntity npc) {
        class_1937 class_19372;
        AbstractKingdomLordNpcEntity lordNpc;
        block3: {
            block2: {
                if (player == null || !(npc instanceof AbstractKingdomLordNpcEntity)) break block2;
                lordNpc = (AbstractKingdomLordNpcEntity)npc;
                class_19372 = player.method_37908();
                if (class_19372 instanceof class_3218) break block3;
            }
            return new TitleChallengeAvailability(false, 0);
        }
        class_3218 world = (class_3218)class_19372;
        long cooldownRemainingTicks = Math.max(0L, TITLE_CHALLENGE_COOLDOWNS.getOrDefault(player.method_5667(), 0L) - world.method_8510());
        int cooldownSeconds = class_3532.method_15386((float)((float)cooldownRemainingTicks / 20.0f));
        return new TitleChallengeAvailability(GotCastles.canChallengeForTitle(player, lordNpc), cooldownSeconds);
    }

    public static void handleTitleChallengeRequest(class_3222 player, AbstractKingdomNpcEntity npc) {
        class_1937 class_19372;
        AbstractKingdomLordNpcEntity lordNpc;
        block11: {
            block10: {
                if (player == null || !(npc instanceof AbstractKingdomLordNpcEntity)) break block10;
                lordNpc = (AbstractKingdomLordNpcEntity)npc;
                class_19372 = player.method_37908();
                if (class_19372 instanceof class_3218) break block11;
            }
            return;
        }
        class_3218 world = (class_3218)class_19372;
        if (ACTIVE_TITLE_CHALLENGES.containsKey(player.method_5667())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Finish your current title challenge first.").method_27692(class_124.field_1061), true);
            return;
        }
        TitleChallengeAvailability availability = GotCastles.describeTitleChallenge(player, lordNpc);
        if (!availability.available()) {
            if (availability.cooldownSeconds() > 0) {
                player.method_7353((class_2561)class_2561.method_43470((String)("Title challenge cooldown " + GotCastles.formatChallengeCooldown(availability.cooldownSeconds()) + ".")).method_27692(class_124.field_1061), true);
            }
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(lordNpc.got$getCastle());
        if (castle == null) {
            return;
        }
        CityStructureClaim arena = GotCastles.findArenaStructure(castle);
        if (arena == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This castle has no arena for title challenges.").method_27692(class_124.field_1061), true);
            return;
        }
        if (GotCastles.isArenaBusy(arena.id())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"The arena is busy.").method_27692(class_124.field_1061), true);
            return;
        }
        ArenaChallengePositions arenaPositions = GotCastles.resolveArenaChallengePositions(world, arena);
        if (arenaPositions == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"The arena is blocked and cannot host a title challenge.").method_27692(class_124.field_1061), true);
            return;
        }
        class_2338 returnPos = player.method_24515();
        float returnYaw = player.method_36454();
        float returnPitch = player.method_36455();
        lordNpc.endDialogue(player.method_5667());
        GotCastles.teleportTitleChallengeParticipants(world, player, lordNpc, arena, arenaPositions);
        ActiveTitleChallenge challenge = new ActiveTitleChallenge((class_5321<class_1937>)world.method_27983(), player.method_5667(), player.method_7334().getName(), returnPos, returnYaw, returnPitch, lordNpc.method_5667(), lordNpc.getCastleNpcId(), castle.getId(), GotCastles.canonicalKingdomId(castle.getOwnerKingdomId()), GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), castle.getOwnerKingdomId(), ""), GotCastles.resolveLordName(world, castle), arena.id(), world.method_8510() + 1200L);
        ACTIVE_TITLE_CHALLENGES.put(player.method_5667(), challenge);
        TITLE_CHALLENGE_COOLDOWNS.put(player.method_5667(), world.method_8510() + 6000L);
        lordNpc.alertAgainst(player, challenge.endsAtTick() + 40L);
        player.method_7353((class_2561)class_2561.method_43470((String)"Challenge started. Defeat the lord within 1 minute.").method_27692(class_124.field_1065), false);
    }

    public static TourneyAvailability describeTourney(class_3222 player, AbstractKingdomNpcEntity npc) {
        class_1937 class_19372;
        if (player == null || !(npc instanceof AbstractKingdomArenaMasterNpcEntity) || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return new TourneyAvailability(false, false);
        }
        class_3218 world = (class_3218)class_19372;
        TourneyWorldState state = TourneyWorldState.get(world);
        TourneyWorldState.TourneyEntry entry = state.getActive(GotCastles.canonicalKingdomId(npc.getKingdomId() == null ? "" : npc.getKingdomId().toString()));
        boolean active = entry != null && entry.castleId().equals(npc.got$getCastle());
        return new TourneyAvailability(active, active && state.hasAttended(player.method_5667(), entry.tourneyId()));
    }

    public static void handleTourneyRequest(class_3222 player, AbstractKingdomNpcEntity npc, TourneyDifficulty difficulty) {
        ArenaChallengePositions positions;
        String kingdomId;
        class_3218 world;
        block10: {
            block9: {
                class_1937 class_19372;
                if (player == null || !(npc instanceof AbstractKingdomArenaMasterNpcEntity) || !((class_19372 = player.method_37908()) instanceof class_3218)) break block9;
                world = (class_3218)class_19372;
                if (difficulty != null) break block10;
            }
            return;
        }
        if (ACTIVE_TITLE_CHALLENGES.containsKey(player.method_5667()) || ACTIVE_TOURNEY_CHALLENGES.containsKey(player.method_5667())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Finish your current arena fight first.").method_27692(class_124.field_1061), true);
            return;
        }
        TourneyWorldState state = TourneyWorldState.get(world);
        TourneyWorldState.TourneyEntry entry = state.getActive(kingdomId = GotCastles.canonicalKingdomId(npc.getKingdomId() == null ? "" : npc.getKingdomId().toString()));
        if (entry == null || !entry.castleId().equals(npc.got$getCastle())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"No tourney is hosted here today.").method_27692(class_124.field_1061), true);
            return;
        }
        if (state.hasAttended(player.method_5667(), entry.tourneyId())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You have already attempted this tourney.").method_27692(class_124.field_1061), true);
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(entry.castleId());
        if (castle == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This tourney cannot be started right now.").method_27692(class_124.field_1061), true);
            return;
        }
        CityStructureClaim arena = GotCastles.findArenaStructure(castle);
        ArenaChallengePositions arenaChallengePositions = positions = arena == null ? null : GotCastles.resolveArenaChallengePositions(world, arena);
        if (arena == null || positions == null || GotCastles.isArenaBusy(arena.id())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"The arena is busy.").method_27692(class_124.field_1061), true);
            return;
        }
        class_2338 returnPos = player.method_24515();
        float returnYaw = player.method_36454();
        float returnPitch = player.method_36455();
        npc.endDialogue(player.method_5667());
        GotCastles.markArenaTeleport((class_1297)player);
        player.method_48105(world, (double)positions.playerPos().method_10263() + 0.5, (double)positions.playerPos().method_10264(), (double)positions.playerPos().method_10260() + 0.5, Set.of(), GotCastles.yawBetween(positions.playerPos(), positions.lordPos()), player.method_36455());
        GotCastles.recordArenaSnapshot((class_1297)player, arena, true);
        List<UUID> opponents = GotCastles.spawnTourneyOpponents(world, player, difficulty, positions.lordPos(), positions.playerPos());
        if (opponents.isEmpty()) {
            GotCastles.teleportTourneyPlayerBack(world, player, returnPos, returnYaw, returnPitch);
            return;
        }
        state.markAttended(player.method_5667(), entry.tourneyId());
        ACTIVE_TOURNEY_CHALLENGES.put(player.method_5667(), new ActiveTourneyChallenge((class_5321<class_1937>)world.method_27983(), player.method_5667(), entry.tourneyId(), arena.id(), returnPos, returnYaw, returnPitch, difficulty, opponents, world.method_8510() + 2400L));
        player.method_7353((class_2561)class_2561.method_43470((String)"Tourney started. Defeat your opponents.").method_27692(class_124.field_1065), false);
    }

    private static boolean isArenaBusy(String arenaId) {
        for (ActiveTitleChallenge activeTitleChallenge : ACTIVE_TITLE_CHALLENGES.values()) {
            if (!arenaId.equals(activeTitleChallenge.arenaId())) continue;
            return true;
        }
        for (ActiveTourneyChallenge activeTourneyChallenge : ACTIVE_TOURNEY_CHALLENGES.values()) {
            if (!arenaId.equals(activeTourneyChallenge.arenaId())) continue;
            return true;
        }
        return false;
    }

    private static List<UUID> spawnTourneyOpponents(class_3218 world, class_3222 player, TourneyDifficulty difficulty, class_2338 center, class_2338 face) {
        ArrayList<UUID> opponents = new ArrayList<UUID>();
        for (int index = 0; index < difficulty.opponentCount(); ++index) {
            class_2338 spawnPos = center.method_10069((index % 2 == 0 ? 1 : -1) * (index / 2), 0, index / 2);
            AbstractTourneyNpcEntity npc = (AbstractTourneyNpcEntity)GotEntities.TOURNEY_NPC.method_5883((class_1937)world);
            if (npc == null) continue;
            npc.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, GotCastles.yawBetween(spawnPos, face), 0.0f);
            GotCastles.applyTourneyLoadout(npc, player, difficulty);
            world.method_8649((class_1297)npc);
            GotCastles.markArenaTeleport((class_1297)npc);
            opponents.add(npc.method_5667());
        }
        return opponents;
    }

    private static void applyTourneyLoadout(AbstractTourneyNpcEntity npc, class_3222 player, TourneyDifficulty difficulty) {
        class_1799 boots;
        class_1799 leggings;
        class_1799 chestplate;
        class_1799 helmet;
        class_1799 weapon;
        switch (difficulty.ordinal()) {
            default: {
                throw new MatchException(null, null);
            }
            case 0: {
                class_1799 class_17992 = GotCastles.randomStack(player, List.of(class_1802.field_8371, GotItems.IRON_WARHAMMER, GotItems.IRON_MACE));
                break;
            }
            case 1: {
                class_1799 class_17992 = GotCastles.randomStack(player, List.of(GotItems.STEEL_SWORD, GotItems.STEEL_WARHAMMER, GotItems.STEEL_MACE));
                break;
            }
            case 2: {
                class_1799 class_17992 = weapon = new class_1799((class_1935)GotItems.STEEL_SWORD);
            }
        }
        if (difficulty == TourneyDifficulty.EASY) {
            helmet = new class_1799((class_1935)class_1802.field_8743);
            chestplate = new class_1799((class_1935)class_1802.field_8523);
            leggings = new class_1799((class_1935)class_1802.field_8396);
            boots = new class_1799((class_1935)class_1802.field_8660);
        } else if (difficulty == TourneyDifficulty.HARD) {
            helmet = new class_1799((class_1935)GotItems.KINGSGUARD_HELMET);
            chestplate = new class_1799((class_1935)GotItems.KINGSGUARD_CHESTPLATE);
            leggings = new class_1799((class_1935)GotItems.KINGSGUARD_LEGGINGS);
            boots = new class_1799((class_1935)GotItems.KINGSGUARD_BOOTS);
        } else {
            helmet = new class_1799((class_1935)GotItems.STEEL_HELMET);
            chestplate = new class_1799((class_1935)GotItems.STEEL_CHESTPLATE);
            leggings = new class_1799((class_1935)GotItems.STEEL_LEGGINGS);
            boots = new class_1799((class_1935)GotItems.STEEL_BOOTS);
        }
        npc.configureForTourney(player, GotCastles.randomTourneyTexture(player), difficulty.health(), 0.24, weapon, helmet, chestplate, leggings, boots);
    }

    private static class_2960 randomTourneyTexture(class_3222 player) {
        String[] textures = new String[]{"textures/entity/npc/event/peasant_1.png", "textures/entity/npc/event/peasant_2.png", "textures/entity/npc/event/peasant_3.png", "textures/entity/npc/peasant_north.png", "textures/entity/npc/peasant_dorne.png", "textures/entity/npc/peasant_reach.png", "textures/entity/npc/female/wildling_female.png"};
        return class_2960.method_60655((String)"agob", (String)textures[player.method_59922().method_43048(textures.length)]);
    }

    private static class_1799 randomStack(class_3222 player, List<class_1792> items) {
        return new class_1799((class_1935)items.get(player.method_59922().method_43048(items.size())));
    }

    private static void giveTourneyReward(class_3222 player, TourneyDifficulty difficulty) {
        player.method_31548().method_7398(new class_1799((class_1935)GotItems.GOLDEN_DRAGON, difficulty.goldDragons()));
        class_1799 reward = GotCastles.createTourneyEquipmentReward(player, difficulty);
        if (!reward.method_7960()) {
            player.method_31548().method_7398(reward);
        }
    }

    private static class_1799 createTourneyEquipmentReward(class_3222 player, TourneyDifficulty difficulty) {
        List<class_1738> pool = switch (difficulty.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> List.of(GotItems.COPPER_SWORD, GotItems.COPPER_WARHAMMER, GotItems.COPPER_MACE, GotItems.COPPER_HELMET, GotItems.COPPER_CHESTPLATE, GotItems.COPPER_LEGGINGS, GotItems.COPPER_BOOTS);
            case 1 -> List.of(class_1802.field_8371, GotItems.IRON_WARHAMMER, GotItems.IRON_MACE, class_1802.field_8743, class_1802.field_8523, class_1802.field_8396, class_1802.field_8660);
            case 2 -> List.of(GotItems.STEEL_SWORD, GotItems.STEEL_WARHAMMER, GotItems.STEEL_MACE, GotItems.STEEL_HELMET, GotItems.STEEL_CHESTPLATE, GotItems.STEEL_LEGGINGS, GotItems.STEEL_BOOTS);
        };
        class_1799 stack = new class_1799((class_1935)pool.get(player.method_59922().method_43048(pool.size())));
        ItemStackQualityHelper.Quality[] qualities = new ItemStackQualityHelper.Quality[]{ItemStackQualityHelper.Quality.GOOD, ItemStackQualityHelper.Quality.PERFECT, ItemStackQualityHelper.Quality.LEGENDARY};
        ItemStackQualityHelper.setQuality(stack, qualities[player.method_59922().method_43048(qualities.length)]);
        stack.method_57379(class_9334.field_49629, (Object)0);
        if (!(stack.method_7909() instanceof class_1738)) {
            stack.method_57379(class_9334.field_49631, (Object)class_2561.method_43470((String)GotCastles.randomSwordName(player)));
        }
        return stack;
    }

    private static String randomSwordName(class_3222 player) {
        List<String> names = GotCastles.loadSwordNames();
        return names.get(player.method_59922().method_43048(names.size()));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static List<String> loadSwordNames() {
        try (InputStream stream = GotCastles.class.getClassLoader().getResourceAsStream("data/agob/sword_names.json");){
            if (stream == null) {
                List<String> list = List.of("Dawnwatch", "Lionfang", "Stormcaller");
                return list;
            }
            JsonArray array = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonArray();
            ArrayList<String> names = new ArrayList<String>();
            for (JsonElement element : array) {
                if (!element.isJsonPrimitive() || element.getAsString().isBlank()) continue;
                names.add(element.getAsString());
            }
            List<String> object = names.isEmpty() ? List.of("Dawnwatch", "Lionfang", "Stormcaller") : List.copyOf(names);
            return object;
        }
        catch (Exception ignored) {
            return List.of("Dawnwatch", "Lionfang", "Stormcaller");
        }
    }

    @Nullable
    private static CityStructureClaim findArenaStructure(CastleInstance castle) {
        if (castle == null) {
            return null;
        }
        for (CityStructureClaim claim : castle.getCityStructureClaims()) {
            if (!GotCastles.isArenaStructure(claim)) continue;
            return claim;
        }
        return null;
    }

    private static boolean isArenaStructure(CityStructureClaim claim) {
        return claim != null && claim.structurePath() != null && claim.structurePath().toLowerCase(Locale.ROOT).contains("arena");
    }

    @Nullable
    private static ArenaChallengePositions resolveArenaChallengePositions(class_3218 world, CityStructureClaim arena) {
        class_3341 bounds = arena.footprint();
        int centerX = (bounds.method_35415() + bounds.method_35418()) / 2;
        int centerZ = (bounds.method_35417() + bounds.method_35420()) / 2;
        int arenaY = arena.origin().method_10264();
        boolean horizontalX = arena.size().method_10263() >= arena.size().method_10260();
        int maxDistance = Math.max(3, (horizontalX ? bounds.method_35414() : bounds.method_14663()) / 2 - 2);
        int maxLateral = Math.max(0, (horizontalX ? bounds.method_14663() : bounds.method_35414()) / 2 - 2);
        for (int lateralDistance = 0; lateralDistance <= maxLateral; ++lateralDistance) {
            for (int lateral : GotCastles.lateralOffsets(lateralDistance)) {
                for (int distance = 3; distance <= maxDistance; ++distance) {
                    ArenaChallengePositions columns = GotCastles.arenaChallengePositionsOnLine(centerX, arenaY, centerZ, horizontalX, distance, lateral);
                    class_2338 playerPos = GotCastles.resolveArenaChallengeStandPos(world, arena, columns.playerPos());
                    class_2338 lordPos = GotCastles.resolveArenaChallengeStandPos(world, arena, columns.lordPos());
                    if (playerPos == null || lordPos == null) continue;
                    return new ArenaChallengePositions(playerPos, lordPos);
                }
            }
        }
        return null;
    }

    private static int[] lateralOffsets(int distance) {
        if (distance == 0) {
            return new int[]{0};
        }
        return new int[]{distance, -distance};
    }

    private static ArenaChallengePositions arenaChallengePositionsOnLine(int centerX, int arenaY, int centerZ, boolean horizontalX, int distance, int lateral) {
        if (horizontalX) {
            return new ArenaChallengePositions(new class_2338(centerX - distance, arenaY, centerZ + lateral), new class_2338(centerX + distance, arenaY, centerZ + lateral));
        }
        return new ArenaChallengePositions(new class_2338(centerX + lateral, arenaY, centerZ - distance), new class_2338(centerX + lateral, arenaY, centerZ + distance));
    }

    @Nullable
    private static class_2338 resolveArenaChallengeStandPos(class_3218 world, CityStructureClaim arena, class_2338 column) {
        class_3341 bounds = arena.footprint();
        int minY = bounds.method_35416() - 2;
        int maxY = bounds.method_35419() + 2;
        int originY = arena.origin().method_10264();
        int maxOffset = Math.max(originY - minY, maxY - originY);
        for (int offset = 0; offset <= maxOffset; ++offset) {
            class_2338 lower;
            class_2338 higher = new class_2338(column.method_10263(), originY + offset, column.method_10260());
            if (higher.method_10264() <= maxY && GotCastles.isValidArenaChallengeStandPos(world, arena, higher)) {
                return higher;
            }
            if (offset == 0 || (lower = new class_2338(column.method_10263(), originY - offset, column.method_10260())).method_10264() < minY || !GotCastles.isValidArenaChallengeStandPos(world, arena, lower)) continue;
            return lower;
        }
        return null;
    }

    private static boolean isValidArenaChallengeStandPos(class_3218 world, CityStructureClaim arena, class_2338 candidate) {
        class_3341 bounds = arena.footprint();
        if (candidate.method_10264() < bounds.method_35416() - 2 || candidate.method_10264() > bounds.method_35419() + 2) {
            return false;
        }
        if (candidate.method_10263() <= bounds.method_35415() || candidate.method_10263() >= bounds.method_35418() || candidate.method_10260() <= bounds.method_35417() || candidate.method_10260() >= bounds.method_35420()) {
            return false;
        }
        if (!GotCastles.isValidNpcStandPos(world, candidate)) {
            return false;
        }
        class_238 bodyBox = new class_238((double)candidate.method_10263() + 0.2, (double)candidate.method_10264(), (double)candidate.method_10260() + 0.2, (double)candidate.method_10263() + 0.8, (double)candidate.method_10264() + 1.8, (double)candidate.method_10260() + 0.8);
        return world.method_18026(bodyBox);
    }

    private static void teleportTitleChallengeParticipants(class_3218 world, class_3222 player, AbstractKingdomLordNpcEntity lord, CityStructureClaim arena, ArenaChallengePositions positions) {
        float playerYaw = GotCastles.yawBetween(positions.playerPos(), positions.lordPos());
        float lordYaw = GotCastles.yawBetween(positions.lordPos(), positions.playerPos());
        GotCastles.markArenaTeleport((class_1297)player);
        player.method_48105(world, (double)positions.playerPos().method_10263() + 0.5, (double)positions.playerPos().method_10264(), (double)positions.playerPos().method_10260() + 0.5, Set.of(), playerYaw, player.method_36455());
        GotCastles.recordArenaSnapshot((class_1297)player, arena, true);
        lord.method_5942().method_6340();
        GotCastles.markArenaTeleport((class_1297)lord);
        lord.method_48105(world, (double)positions.lordPos().method_10263() + 0.5, positions.lordPos().method_10264(), (double)positions.lordPos().method_10260() + 0.5, Set.of(), lordYaw, 0.0f);
        GotCastles.recordArenaSnapshot((class_1297)lord, arena, true);
    }

    private static float yawBetween(class_2338 from, class_2338 to) {
        double dx = (double)to.method_10263() + 0.5 - ((double)from.method_10263() + 0.5);
        double dz = (double)to.method_10260() + 0.5 - ((double)from.method_10260() + 0.5);
        return (float)(class_3532.method_15349((double)dx, (double)dz) * 57.2957763671875);
    }

    private static boolean canChallengeForTitle(class_3222 player, AbstractKingdomLordNpcEntity lordNpc) {
        class_1937 class_19372;
        if (player == null || lordNpc == null || !lordNpc.method_5805() || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return false;
        }
        class_3218 world = (class_3218)class_19372;
        ReputationTitle title = ((PlayerReputationHolder)player).got$getReputationProfile().getTitle();
        if (title == null || !title.isAtLeast(ReputationTitle.LORD)) {
            return false;
        }
        if (ACTIVE_TITLE_CHALLENGES.containsKey(player.method_5667())) {
            return false;
        }
        if (world.method_8510() < TITLE_CHALLENGE_COOLDOWNS.getOrDefault(player.method_5667(), 0L)) {
            return false;
        }
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        String playerKingdomId = GotCastles.canonicalKingdomId(character.getSelectedKingdomId());
        String npcKingdomId = GotCastles.canonicalKingdomId(lordNpc.getKingdomId() == null ? "" : lordNpc.getKingdomId().toString());
        if (playerKingdomId.isBlank() || !playerKingdomId.equals(npcKingdomId)) {
            return false;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(lordNpc.got$getCastle());
        if (castle == null || castle.getOwnerPlayerUuid() != null) {
            return false;
        }
        if (GotCastles.findArenaStructure(castle) == null) {
            return false;
        }
        String castleHouseId = GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), castle.getOwnerKingdomId(), "");
        if (castleHouseId.isBlank()) {
            return false;
        }
        return lordNpc.getCastleNpcId().equals(GotCastles.resolveHouseLordProfileId(world, castle, lordNpc.method_5477().getString()));
    }

    private static String resolveHouseLordProfileId(class_3218 world, CastleInstance castle, String lordName) {
        if (world == null || castle == null || lordName == null || lordName.isBlank()) {
            return "";
        }
        String kingdomId = GotCastles.canonicalKingdomId(castle.getOwnerKingdomId());
        String houseId = GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), kingdomId, "");
        HouseLordWorldState.HouseLordProfile lord = HouseLordWorldState.get(world).findByName(kingdomId, houseId, lordName);
        return lord == null ? "" : lord.id();
    }

    private static String formatChallengeCooldown(int totalSeconds) {
        int clamped = Math.max(0, totalSeconds);
        return String.format(Locale.ROOT, "%02d:%02d", clamped / 60, clamped % 60);
    }

    private static void tickActiveInvasions(class_3218 world) {
        long worldTime = world.method_8510();
        for (ActiveCastleInvasion invasion : List.copyOf(ACTIVE_CASTLE_INVASIONS.values())) {
            if (!world.method_27983().equals(invasion.dimension())) continue;
            class_3222 player = world.method_8503().method_3760().method_14602(invasion.playerUuid());
            CastleInstance castle = GotCastles.getWorldState(world).get(invasion.castleId());
            if (player == null || !player.method_5805() || player.method_37908() != world || castle == null) {
                GotCastles.failCastleInvasion(world, invasion.playerUuid(), true);
                continue;
            }
            long remainingTicks = Math.max(0L, invasion.endsAtTick() - worldTime);
            invasion.updateBossBar(remainingTicks);
            if (worldTime < invasion.endsAtTick()) continue;
            if (invasion.mode() == CastleInvasionMode.RAID) {
                GotCastles.completeRaidInvasion(world, invasion, false);
                continue;
            }
            GotCastles.failCastleInvasion(world, invasion.playerUuid(), true);
        }
        boolean changed = false;
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if (GotCastles.findActiveInvasionForCastle(castle.getId()) != null || castle.getRaidState() == CastleRaidState.PEACE || castle.getRaidEndsAtTick() <= 0L || worldTime < castle.getRaidEndsAtTick()) continue;
            castle.setRaidState(CastleRaidState.PEACE);
            castle.setRaidEndsAtTick(0L);
            changed = true;
        }
        if (changed) {
            GotCastles.syncStateToPlayers(world);
        }
    }

    private static void tickActiveTitleChallenges(class_3218 world) {
        for (ActiveTitleChallenge challenge : List.copyOf(ACTIVE_TITLE_CHALLENGES.values())) {
            AbstractKingdomLordNpcEntity lordNpc;
            AbstractKingdomLordNpcEntity lord;
            if (!world.method_27983().equals(challenge.dimension())) continue;
            class_3222 player = world.method_8503().method_3760().method_14602(challenge.playerUuid());
            class_1297 entity = world.method_14190(challenge.lordUuid());
            AbstractKingdomLordNpcEntity abstractKingdomLordNpcEntity = lord = entity instanceof AbstractKingdomLordNpcEntity ? (lordNpc = (AbstractKingdomLordNpcEntity)entity) : null;
            if (player == null || !player.method_5805() || player.method_37908() != world) {
                if (player == null) {
                    ARENA_LOGOUT_RELEASES.add(challenge.playerUuid());
                }
                GotCastles.failTitleChallenge(world, challenge, false);
                continue;
            }
            if (lord == null || !lord.method_5805()) continue;
            lord.alertAgainst(player, world.method_8510() + 40L);
            if (world.method_8510() < challenge.endsAtTick()) continue;
            GotCastles.failTitleChallenge(world, challenge, true);
        }
        TITLE_CHALLENGE_COOLDOWNS.entrySet().removeIf(entry -> (Long)entry.getValue() <= world.method_8510());
    }

    private static void tickActiveTourneyChallenges(class_3218 world) {
        for (ActiveTourneyChallenge challenge : List.copyOf(ACTIVE_TOURNEY_CHALLENGES.values())) {
            if (!world.method_27983().equals(challenge.dimension())) continue;
            class_3222 player = world.method_8503().method_3760().method_14602(challenge.playerUuid());
            if (player == null || !player.method_5805() || player.method_37908() != world || world.method_8510() >= challenge.endsAtTick()) {
                GotCastles.failTourneyChallenge(world, challenge.playerUuid(), world.method_8510() >= challenge.endsAtTick());
                continue;
            }
            challenge.opponentUuids().removeIf(uuid -> {
                AbstractTourneyNpcEntity npc;
                class_1297 entity = world.method_14190(uuid);
                return !(entity instanceof AbstractTourneyNpcEntity) || !(npc = (AbstractTourneyNpcEntity)entity).method_5805();
            });
            if (!challenge.opponentUuids().isEmpty()) continue;
            GotCastles.completeTourneyChallenge(world, challenge);
        }
    }

    public static void onTourneyNpcDefeated(class_3218 world, AbstractTourneyNpcEntity npc) {
        if (world == null || npc == null) {
            return;
        }
        for (ActiveTourneyChallenge challenge : List.copyOf(ACTIVE_TOURNEY_CHALLENGES.values())) {
            if (!challenge.opponentUuids().remove(npc.method_5667()) || !challenge.opponentUuids().isEmpty()) continue;
            GotCastles.completeTourneyChallenge(world, challenge);
            return;
        }
    }

    private static void failTourneyChallenge(class_3218 world, UUID playerUuid, boolean timeExpired) {
        GotCastles.failTourneyChallenge(world, playerUuid, timeExpired, true);
    }

    private static void failTourneyChallenge(class_3218 world, UUID playerUuid, boolean timeExpired, boolean notifyPlayer) {
        ActiveTourneyChallenge challenge = ACTIVE_TOURNEY_CHALLENGES.remove(playerUuid);
        if (challenge == null) {
            return;
        }
        for (UUID opponentUuid : challenge.opponentUuids()) {
            class_1297 entity = world.method_14190(opponentUuid);
            if (entity == null) continue;
            entity.method_31472();
        }
        class_3222 player = world.method_8503().method_3760().method_14602(playerUuid);
        if (notifyPlayer && player != null && player.method_5805() && GotCastles.canSendPlayPackets(player)) {
            GotCastles.teleportTourneyPlayerBack(world, player, challenge.returnPos(), challenge.returnYaw(), challenge.returnPitch());
            player.method_7353((class_2561)class_2561.method_43470((String)(timeExpired ? "Tourney failed." : "Tourney ended.")).method_27692(class_124.field_1061), false);
        } else {
            GotCastles.clearArenaPlayerState(playerUuid);
        }
    }

    private static void completeTourneyChallenge(class_3218 world, ActiveTourneyChallenge challenge) {
        if (challenge == null || ACTIVE_TOURNEY_CHALLENGES.remove(challenge.playerUuid()) == null) {
            return;
        }
        class_3222 player = world.method_8503().method_3760().method_14602(challenge.playerUuid());
        if (player == null) {
            return;
        }
        GotCastles.giveTourneyReward(player, challenge.difficulty());
        GotCastles.teleportTourneyPlayerBack(world, player, challenge.returnPos(), challenge.returnYaw(), challenge.returnPitch());
        player.method_7353((class_2561)class_2561.method_43470((String)"Tourney won.").method_27692(class_124.field_1065), false);
    }

    private static void teleportTourneyPlayerBack(class_3218 world, class_3222 player, class_2338 pos, float yaw, float pitch) {
        GotCastles.markArenaTeleport((class_1297)player);
        player.method_48105(world, (double)pos.method_10263() + 0.5, (double)pos.method_10264(), (double)pos.method_10260() + 0.5, Set.of(), yaw, pitch);
        GotCastles.clearArenaPlayerState(player.method_5667());
    }

    private static void clearArenaPlayerState(UUID playerUuid) {
        if (playerUuid == null) {
            return;
        }
        ARENA_ENTITY_SNAPSHOTS.remove(playerUuid);
        ARENA_TELEPORT_GRACE.remove(playerUuid);
        ARENA_LOGOUT_RELEASES.remove(playerUuid);
    }

    static boolean canSendPlayPackets(class_3222 player) {
        return player != null && !player.method_14239() && player.field_13987 != null && player.field_13987.method_48106();
    }

    private static void tickArenaBoundaries(class_3218 world) {
        List<ArenaBoundary> arenas = GotCastles.arenaBoundaries(world);
        if (arenas.isEmpty()) {
            return;
        }
        HashSet<CallSite> processed = new HashSet<CallSite>();
        for (ArenaBoundary arena : arenas) {
            class_238 trackingBox = arena.box().method_1014(8.0);
            for (class_1297 entity2 : world.method_8333(null, trackingBox, entity -> entity != null && !entity.method_31481())) {
                class_3222 player;
                boolean previousInside;
                String processedKey = arena.id() + "|" + entity2.method_5845();
                if (!processed.add((CallSite)((Object)processedKey)) || GotCastles.canIgnoreArenaBoundary(entity2)) continue;
                boolean inside = arena.contains(entity2.method_19538());
                ArenaEntitySnapshot previous = ARENA_ENTITY_SNAPSHOTS.get(entity2.method_5667());
                boolean bl = previousInside = previous != null && arena.id().equals(previous.arenaId()) && previous.inside();
                if (previous == null) {
                    if (!(!inside || GotCastles.canEnterArenaBoundary(entity2, arena) || entity2 instanceof class_3222 && ARENA_LOGOUT_RELEASES.contains((player = (class_3222)entity2).method_5667()))) {
                        GotCastles.ejectArenaIntruder(entity2, arena);
                        continue;
                    }
                    GotCastles.recordArenaSnapshot(entity2, arena, inside);
                    continue;
                }
                if (!previousInside && inside && !GotCastles.canEnterArenaBoundary(entity2, arena)) {
                    GotCastles.rejectArenaBoundaryCrossing(entity2, previous);
                    continue;
                }
                if (previousInside && !inside && !GotCastles.canExitArenaBoundary(entity2, arena)) {
                    GotCastles.rejectArenaBoundaryCrossing(entity2, previous);
                    continue;
                }
                GotCastles.recordArenaSnapshot(entity2, arena, inside);
                if (!(entity2 instanceof class_3222)) continue;
                player = (class_3222)entity2;
                if (inside) continue;
                ARENA_LOGOUT_RELEASES.remove(player.method_5667());
            }
        }
        if (world.method_8510() % 200L == 0L) {
            ARENA_ENTITY_SNAPSHOTS.entrySet().removeIf(entry -> world.method_27983().equals(((ArenaEntitySnapshot)entry.getValue()).dimension()) && world.method_14190((UUID)entry.getKey()) == null);
        }
    }

    private static boolean canIgnoreArenaBoundary(class_1297 entity) {
        class_3222 player;
        return entity instanceof class_3222 && (player = (class_3222)entity).method_5687(2);
    }

    private static boolean canEnterArenaBoundary(class_1297 entity, ArenaBoundary arena) {
        if (ARENA_TELEPORT_GRACE.remove(entity.method_5667())) {
            return true;
        }
        return GotCastles.isActiveTitleChallengeParticipant(entity, arena);
    }

    private static boolean canExitArenaBoundary(class_1297 entity, ArenaBoundary arena) {
        if (ARENA_TELEPORT_GRACE.remove(entity.method_5667())) {
            return true;
        }
        if (entity instanceof class_3222) {
            class_3222 player = (class_3222)entity;
            return ARENA_LOGOUT_RELEASES.contains(player.method_5667()) || !GotCastles.isActiveTitleChallengeParticipant(entity, arena);
        }
        return false;
    }

    private static boolean isActiveTitleChallengeParticipant(class_1297 entity, ArenaBoundary arena) {
        if (entity == null || arena == null) {
            return false;
        }
        ActiveTitleChallenge playerChallenge = ACTIVE_TITLE_CHALLENGES.get(entity.method_5667());
        if (playerChallenge != null) {
            return arena.id().equals(playerChallenge.arenaId());
        }
        ActiveTourneyChallenge tourneyChallenge = ACTIVE_TOURNEY_CHALLENGES.get(entity.method_5667());
        if (tourneyChallenge != null) {
            return arena.id().equals(tourneyChallenge.arenaId());
        }
        for (ActiveTitleChallenge activeTitleChallenge : ACTIVE_TITLE_CHALLENGES.values()) {
            if (!entity.method_5667().equals(activeTitleChallenge.lordUuid())) continue;
            return arena.id().equals(activeTitleChallenge.arenaId());
        }
        for (ActiveTourneyChallenge activeTourneyChallenge : ACTIVE_TOURNEY_CHALLENGES.values()) {
            if (!activeTourneyChallenge.opponentUuids().contains(entity.method_5667())) continue;
            return arena.id().equals(activeTourneyChallenge.arenaId());
        }
        return false;
    }

    private static void rejectArenaBoundaryCrossing(class_1297 entity, ArenaEntitySnapshot previous) {
        if (entity instanceof class_1676) {
            entity.method_31472();
            return;
        }
        entity.method_18799(class_243.field_1353);
        entity.field_6037 = true;
        if (entity instanceof class_3222) {
            class_3222 player = (class_3222)entity;
            class_1937 class_19372 = entity.method_37908();
            if (class_19372 instanceof class_3218) {
                class_3218 world = (class_3218)class_19372;
                player.method_48105(world, previous.pos().field_1352, previous.pos().field_1351, previous.pos().field_1350, Set.of(), player.method_36454(), player.method_36455());
                return;
            }
        }
        entity.method_5808(previous.pos().field_1352, previous.pos().field_1351, previous.pos().field_1350, entity.method_36454(), entity.method_36455());
    }

    private static void ejectArenaIntruder(class_1297 entity, ArenaBoundary arena) {
        if (entity instanceof class_1676) {
            entity.method_31472();
            return;
        }
        class_243 outside = GotCastles.nearestArenaOutsidePos(entity.method_19538(), arena);
        entity.method_18799(class_243.field_1353);
        entity.field_6037 = true;
        if (entity instanceof class_3222) {
            class_3222 player = (class_3222)entity;
            class_1937 class_19372 = entity.method_37908();
            if (class_19372 instanceof class_3218) {
                class_3218 world = (class_3218)class_19372;
                player.method_48105(world, outside.field_1352, outside.field_1351, outside.field_1350, Set.of(), player.method_36454(), player.method_36455());
                ARENA_TELEPORT_GRACE.remove(player.method_5667());
                ARENA_LOGOUT_RELEASES.remove(player.method_5667());
                return;
            }
        }
        entity.method_5808(outside.field_1352, outside.field_1351, outside.field_1350, entity.method_36454(), entity.method_36455());
    }

    private static class_243 nearestArenaOutsidePos(class_243 pos, ArenaBoundary arena) {
        class_3341 bounds = arena.bounds();
        double west = Math.abs(pos.field_1352 - (double)bounds.method_35415());
        double east = Math.abs((double)bounds.method_35418() + 1.0 - pos.field_1352);
        double north = Math.abs(pos.field_1350 - (double)bounds.method_35417());
        double south = Math.abs((double)bounds.method_35420() + 1.0 - pos.field_1350);
        double best = Math.min(Math.min(west, east), Math.min(north, south));
        if (best == west) {
            return new class_243((double)bounds.method_35415() - 0.25, pos.field_1351, pos.field_1350);
        }
        if (best == east) {
            return new class_243((double)bounds.method_35418() + 1.25, pos.field_1351, pos.field_1350);
        }
        if (best == north) {
            return new class_243(pos.field_1352, pos.field_1351, (double)bounds.method_35417() - 0.25);
        }
        return new class_243(pos.field_1352, pos.field_1351, (double)bounds.method_35420() + 1.25);
    }

    private static void markArenaTeleport(class_1297 entity) {
        if (entity != null) {
            ARENA_TELEPORT_GRACE.add(entity.method_5667());
        }
    }

    private static void recordArenaSnapshot(class_1297 entity, CityStructureClaim arena, boolean inside) {
        class_1937 class_19372;
        if (entity == null || arena == null || !((class_19372 = entity.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        ARENA_TELEPORT_GRACE.remove(entity.method_5667());
        ARENA_ENTITY_SNAPSHOTS.put(entity.method_5667(), new ArenaEntitySnapshot((class_5321<class_1937>)world.method_27983(), arena.id(), entity.method_19538(), inside));
    }

    private static void recordArenaSnapshot(class_1297 entity, ArenaBoundary arena, boolean inside) {
        class_1937 class_19372;
        if (entity == null || arena == null || !((class_19372 = entity.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        ARENA_TELEPORT_GRACE.remove(entity.method_5667());
        ARENA_ENTITY_SNAPSHOTS.put(entity.method_5667(), new ArenaEntitySnapshot((class_5321<class_1937>)world.method_27983(), arena.id(), entity.method_19538(), inside));
    }

    private static List<ArenaBoundary> arenaBoundaries(class_3218 world) {
        ArenaBoundaryCache cache = ARENA_BOUNDARY_CACHES.get(world.method_27983());
        if (cache != null && world.method_8510() < cache.expiresAtTick()) {
            return cache.arenas();
        }
        ArrayList<ArenaBoundary> arenas = new ArrayList<ArenaBoundary>();
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            for (CityStructureClaim claim : castle.getCityStructureClaims()) {
                if (!GotCastles.isArenaStructure(claim)) continue;
                class_3341 bounds = claim.footprint();
                arenas.add(new ArenaBoundary(castle.getId(), claim.id(), bounds, new class_238((double)bounds.method_35415(), (double)bounds.method_35416(), (double)bounds.method_35417(), (double)bounds.method_35418() + 1.0, (double)bounds.method_35419() + 1.0, (double)bounds.method_35420() + 1.0)));
            }
        }
        List<ArenaBoundary> immutable = List.copyOf(arenas);
        ARENA_BOUNDARY_CACHES.put((class_5321<class_1937>)world.method_27983(), new ArenaBoundaryCache(world.method_8510() + 100L, immutable));
        return immutable;
    }

    private static void tickCastleRecaptures(class_3218 world) {
        GotCastleRecaptures.tick(world);
    }

    private static void runDailyKingdomAiInvasionTask(class_3218 world, DailyTaskContext context) {
        if (!GotDimensionKeys.ICEANDFIRE.equals((Object)world.method_27983())) {
            return;
        }
        GotCastles.scheduleKingdomAiInvasions(world);
        GotCastles.tickKingdomAiInvasions(world, true);
        GotCastles.drainPriorityKingdomAiResolutionQueue(world, Integer.MAX_VALUE);
    }

    private static void tickKingdomAiInvasions(class_3218 world, boolean applyQueuedManualProgress) {
        boolean appliedQueuedManualProgress;
        KingdomAiInvasionWorldState state = KingdomAiInvasionWorldState.get(world);
        boolean hadInvasionsAtStart = !state.getAll().isEmpty();
        long queuedManualProgressDays = applyQueuedManualProgress ? state.getQueuedManualProgressDays() : 0L;
        boolean bl = appliedQueuedManualProgress = queuedManualProgressDays > 0L && hadInvasionsAtStart;
        if (appliedQueuedManualProgress) {
            state.addProgressOffsetTicks(queuedManualProgressDays * 24000L);
        }
        long currentProgressTick = GotCastles.currentKingdomAiProgressTick(world);
        for (KingdomAiInvasionWorldState.KingdomAiInvasion invasion : List.copyOf(state.getAll())) {
            StrategicCastleSnapshot targetCastle = GotCastles.resolveStrategicCastle(world, invasion.targetCastleId());
            if (targetCastle == null) {
                PRIORITY_KINGDOM_AI_RESOLUTION_QUEUE.remove(invasion.targetCastleId());
                GotCastles.endKingdomAiInvasion(world, invasion.targetCastleId(), true);
                continue;
            }
            if (!invasion.partyArrived()) {
                if (currentProgressTick < invasion.partyArrivalTick()) continue;
                invasion.setPartyArrived(true);
                state.method_80();
                GotCastles.syncCastleMapToPlayers(world);
            }
            PRIORITY_KINGDOM_AI_RESOLUTION_QUEUE.add(invasion.targetCastleId());
        }
        GotCastles.drainPriorityKingdomAiResolutionQueue(world, applyQueuedManualProgress ? Integer.MAX_VALUE : 8);
        if (appliedQueuedManualProgress) {
            state.consumeQueuedManualProgressDays(queuedManualProgressDays);
        }
    }

    private static void drainPriorityKingdomAiResolutionQueue(class_3218 world, int maxResolutions) {
        if (PRIORITY_KINGDOM_AI_RESOLUTION_QUEUE.isEmpty() || maxResolutions <= 0) {
            return;
        }
        KingdomAiInvasionWorldState state = KingdomAiInvasionWorldState.get(world);
        int resolved = 0;
        for (String targetCastleId : List.copyOf(PRIORITY_KINGDOM_AI_RESOLUTION_QUEUE)) {
            if (resolved >= maxResolutions) {
                return;
            }
            PRIORITY_KINGDOM_AI_RESOLUTION_QUEUE.remove(targetCastleId);
            KingdomAiInvasionWorldState.KingdomAiInvasion invasion = state.get(targetCastleId);
            if (invasion == null) continue;
            StrategicCastleSnapshot targetCastle = GotCastles.resolveStrategicCastle(world, invasion.targetCastleId());
            if (targetCastle == null) {
                GotCastles.endKingdomAiInvasion(world, invasion.targetCastleId(), true);
                ++resolved;
                continue;
            }
            GotCastles.clearKingdomAiBossBar(invasion.targetCastleId());
            GotCastles.resolvePassiveKingdomAiInvasion(world, invasion, targetCastle);
            ++resolved;
        }
    }

    private static long currentKingdomAiProgressTick(class_3218 world) {
        if (world == null) {
            return 0L;
        }
        KingdomAiInvasionWorldState state = KingdomAiInvasionWorldState.get(world);
        return world.method_8510() + state.getProgressOffsetTicks();
    }

    private static void tickLiveKingdomAiInvasion(class_3218 world, KingdomAiInvasionWorldState state, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, CastleInstance castle, StrategicCastleSnapshot targetCastle) {
        List<class_3222> playersInClaim = GotCastles.playersInCastleClaim(world, castle);
        GotCastles.updateKingdomAiAssaultBossBar(invasion, playersInClaim);
        if (playersInClaim.isEmpty()) {
            GotCastles.resolvePassiveKingdomAiInvasion(world, invasion, targetCastle);
            return;
        }
        GotCastles.pruneKingdomAiAttackers(world, state, invasion);
        if (invasion.attackerUuids().isEmpty()) {
            GotCastles.failKingdomAiInvasion(world, invasion, targetCastle);
            return;
        }
        if (!GotCastles.hasLivingCastleDefenders(world, castle.getId())) {
            GotCastles.completeKingdomAiInvasion(world, invasion, targetCastle);
            return;
        }
        GotCastles.directKingdomAiCombat(world, castle, invasion, playersInClaim.getFirst());
    }

    private static void scheduleKingdomAiInvasions(class_3218 world) {
        StrategicCastleSnapshot invadingOrigin;
        String kingdomId;
        StrategicCastleSnapshot targetCastle;
        if (world.method_8510() <= 0L) {
            return;
        }
        PlayerKingdomWorldState.get(world);
        StrategicCastleSnapshotSet strategicCastles = GotCastles.snapshotStrategicCastles(world);
        int startedThisTick = 0;
        LinkedHashSet<String> consideredInvaders = new LinkedHashSet<String>();
        for (Kingdom kingdom : GotKingdoms.AI_WAR_KINGDOMS) {
            for (StrategicCastleSnapshot invadingCastle : GotCastles.getStrategicAiInvadersForKingdom(world, kingdom.getId().toString(), strategicCastles)) {
                if (!consideredInvaders.add(invadingCastle.castleId()) || !GotCastles.isEligibleAiInvader(invadingCastle) || !GotCastles.shouldRollKingdomAiInvasion(world, invadingCastle, strategicCastles) || world.field_9229.method_43048(10) != 0 || (targetCastle = GotCastles.findKingdomAiTargetCastle(world, invadingCastle, strategicCastles)) == null || !GotCastles.startKingdomAiInvasion(world, invadingCastle, targetCastle, null, strategicCastles) || ++startedThisTick < 1) continue;
                return;
            }
        }
        for (Kingdom kingdom : GotKingdoms.CASTLELESS_ORIGIN_KINGDOMS) {
            kingdomId = kingdom.getId().toString();
            if (!GotCastles.shouldRollCastlelessKingdomAiInvasion(world, kingdomId, strategicCastles) || !GotCastles.isEligibleAiInvader(invadingOrigin = GotCastles.getCastlelessKingdomInvasionOrigin(world, kingdomId, strategicCastles)) || !GotCastles.shouldRollKingdomAiInvasion(world, invadingOrigin, strategicCastles) || world.field_9229.method_43048(10) != 0 || (targetCastle = GotCastles.findKingdomAiTargetCastle(world, invadingOrigin, strategicCastles)) == null || !GotCastles.startKingdomAiInvasion(world, invadingOrigin, targetCastle, null, strategicCastles) || ++startedThisTick < 1) continue;
            return;
        }
        for (Kingdom kingdom : GotKingdoms.AI_WAR_KINGDOMS) {
            kingdomId = kingdom.getId().toString();
            if (!GotCastles.isDispossessedKingdom(world, kingdomId, strategicCastles) || (invadingOrigin = GotCastles.buildDispossessedOriginSnapshot(world, kingdomId)) == null || !GotCastles.isEligibleAiInvader(invadingOrigin) || !GotCastles.shouldRollKingdomAiInvasion(world, invadingOrigin.castleId(), invadingOrigin.ownerKingdomId(), invadingOrigin.lordName(), strategicCastles) || world.field_9229.method_43048(10) != 0 || (targetCastle = GotCastles.findKingdomAiTargetCastle(world, invadingOrigin, strategicCastles)) == null || !GotCastles.startKingdomAiInvasion(world, invadingOrigin, targetCastle, null, strategicCastles) || ++startedThisTick < 1) continue;
            return;
        }
    }

    private static boolean shouldRollKingdomAiInvasion(class_3218 world, StrategicCastleSnapshot invadingCastle) {
        if (world == null || invadingCastle == null) {
            return false;
        }
        return GotCastles.shouldRollKingdomAiInvasion(world, invadingCastle.castleId(), invadingCastle.ownerKingdomId(), invadingCastle.lordName());
    }

    private static boolean shouldRollKingdomAiInvasion(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshotSet strategicCastles) {
        return invadingCastle != null && GotCastles.shouldRollKingdomAiInvasion(world, invadingCastle.castleId(), invadingCastle.ownerKingdomId(), invadingCastle.lordName(), strategicCastles);
    }

    private static boolean shouldRollCastlelessKingdomAiInvasion(class_3218 world, String kingdomId) {
        return GotCastles.shouldRollCastlelessKingdomAiInvasion(world, kingdomId, null);
    }

    private static boolean shouldRollCastlelessKingdomAiInvasion(class_3218 world, String kingdomId, @Nullable StrategicCastleSnapshotSet strategicCastles) {
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        if (!GotKingdoms.isCastlelessOriginKingdom(canonicalKingdomId)) {
            return false;
        }
        return GotCastles.shouldRollKingdomAiInvasion(world, GotCastles.castlelessOriginCastleId(canonicalKingdomId), canonicalKingdomId, GotCastles.castlelessOriginLordName(canonicalKingdomId), strategicCastles);
    }

    private static boolean shouldRollKingdomAiInvasion(class_3218 world, String castleId, String ownerKingdomId, String lordName) {
        return GotCastles.shouldRollKingdomAiInvasion(world, castleId, ownerKingdomId, lordName, null);
    }

    private static boolean shouldRollKingdomAiInvasion(class_3218 world, String castleId, String ownerKingdomId, String lordName, @Nullable StrategicCastleSnapshotSet strategicCastles) {
        int partyCapacity;
        if (world == null || castleId == null || castleId.isBlank()) {
            return false;
        }
        KingdomAiInvasionWorldState invasionState = KingdomAiInvasionWorldState.get(world);
        long today = GotGameRules.trackedDay(world);
        if (today < invasionState.getNextInvasionDayForCastle(castleId)) {
            return false;
        }
        int n = partyCapacity = strategicCastles == null ? GotCastles.lordPartyCapacity(world, ownerKingdomId, lordName, castleId) : GotCastles.lordPartyCapacity(strategicCastles, ownerKingdomId, lordName, castleId);
        if (invasionState.activeInvasionsForLord(GotCastles.lordInvasionCooldownKey(ownerKingdomId, lordName)) >= partyCapacity) {
            return false;
        }
        long window = Math.max(20L, 36000L);
        long offset = Math.floorMod((long)castleId.hashCode(), window);
        return world.method_8510() % window == offset;
    }

    public static void onExplicitTimeSet(class_3218 world) {
        if (world == null || !GotGameRules.affectedByTimeSkip(world)) {
            return;
        }
        KingdomAiInvasionWorldState.get(world).queueManualProgressDay();
    }

    public static boolean startOpeningKingdomAiInvasionForWar(class_3218 world, String attackerKingdomId, String defenderKingdomId) {
        if (world == null) {
            return false;
        }
        String attacker = GotCastles.canonicalKingdomId(attackerKingdomId);
        String defender = GotCastles.canonicalKingdomId(defenderKingdomId);
        if (attacker.isBlank() || defender.isBlank() || attacker.equals(defender)) {
            return false;
        }
        WarOpeningCandidate selected = GotCastles.selectWarOpeningCandidate(world, attacker, defender, false);
        if (selected == null) {
            selected = GotCastles.selectWarOpeningCandidate(world, attacker, defender, true);
        }
        if (selected == null) {
            return false;
        }
        if (!GotCastles.startKingdomAiInvasion(world, selected.invadingCastle(), selected.targetCastle(), (Long)20L, selected.relaxed())) {
            return false;
        }
        KingdomAiInvasionWorldState.KingdomAiInvasion invasion = KingdomAiInvasionWorldState.get(world).get(selected.targetCastle().castleId());
        if (invasion != null) {
            invasion.setManualSkipProtectionDays(1);
            KingdomAiInvasionWorldState.get(world).method_80();
        }
        return true;
    }

    @Nullable
    private static WarOpeningCandidate selectWarOpeningCandidate(class_3218 world, String attackerKingdomId, String defenderKingdomId, boolean relaxed) {
        StrategicCastleSnapshot origin;
        List<StrategicCastleSnapshot> attackerCastles = GotCastles.getStrategicAiInvadersForKingdom(world, attackerKingdomId).stream().filter(candidate -> relaxed ? GotCastles.isWarOpeningAiInvader(candidate) : GotCastles.isEligibleAiInvader(candidate)).toList();
        if (attackerCastles.isEmpty() && (origin = GotCastles.getCastlelessKingdomInvasionOrigin(world, attackerKingdomId)) != null && (relaxed ? GotCastles.isWarOpeningAiInvader(origin) : GotCastles.isEligibleAiInvader(origin))) {
            attackerCastles = List.of(origin);
        }
        if (attackerCastles.isEmpty()) {
            return null;
        }
        List<StrategicCastleSnapshot> defenderCastles = GotCastles.getStrategicCastlesForKingdom(world, defenderKingdomId).stream().filter(candidate -> !candidate.playerClaim()).toList();
        if (defenderCastles.isEmpty()) {
            return null;
        }
        if (GotKingdoms.areKingdomsOnDifferentContinents(attackerKingdomId, defenderKingdomId) && !GotCastles.hasKingdomFootholdOnContinentOf(world, attackerKingdomId, defenderKingdomId) && (defenderCastles = defenderCastles.stream().filter(StrategicCastleSnapshot::harbourCastle).toList()).isEmpty()) {
            return null;
        }
        ArrayList<WarOpeningCandidate> candidates = new ArrayList<WarOpeningCandidate>();
        for (StrategicCastleSnapshot invadingCastle : attackerCastles) {
            for (StrategicCastleSnapshot targetCastle : defenderCastles) {
                int weight;
                if (!GotCastles.canStartKingdomAiInvasion(world, invadingCastle, targetCastle, relaxed) || (weight = GotCastles.kingdomAiTargetWeight(invadingCastle, targetCastle)) <= 0) continue;
                double distance = Math.sqrt(invadingCastle.bannerPos().method_10262((class_2382)targetCastle.bannerPos()));
                candidates.add(new WarOpeningCandidate(invadingCastle, targetCastle, weight, distance, relaxed));
            }
        }
        if (candidates.isEmpty()) {
            return null;
        }
        int totalWeight = candidates.stream().mapToInt(WarOpeningCandidate::weight).sum();
        if (totalWeight <= 0) {
            return (WarOpeningCandidate)candidates.getFirst();
        }
        int roll = world.field_9229.method_43048(totalWeight);
        int running = 0;
        for (WarOpeningCandidate candidate2 : candidates) {
            if (roll >= (running += candidate2.weight())) continue;
            return candidate2;
        }
        return (WarOpeningCandidate)candidates.getLast();
    }

    private static boolean startKingdomAiInvasion(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle, @Nullable Long warmupOverrideTicks) {
        return GotCastles.startKingdomAiInvasion(world, invadingCastle, targetCastle, warmupOverrideTicks, false, null);
    }

    private static boolean startKingdomAiInvasion(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle, @Nullable Long warmupOverrideTicks, StrategicCastleSnapshotSet strategicCastles) {
        return GotCastles.startKingdomAiInvasion(world, invadingCastle, targetCastle, warmupOverrideTicks, false, strategicCastles);
    }

    private static boolean startKingdomAiInvasion(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle, @Nullable Long warmupOverrideTicks, boolean relaxedForWarOpening) {
        return GotCastles.startKingdomAiInvasion(world, invadingCastle, targetCastle, warmupOverrideTicks, relaxedForWarOpening, null);
    }

    private static boolean startKingdomAiInvasion(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle, @Nullable Long warmupOverrideTicks, boolean relaxedForWarOpening, @Nullable StrategicCastleSnapshotSet strategicCastles) {
        StrategicCastleSnapshot partyOriginCastle;
        if (world == null || invadingCastle == null || targetCastle == null) {
            return false;
        }
        if (!GotCastles.canStartKingdomAiInvasion(world, invadingCastle, targetCastle, relaxedForWarOpening, strategicCastles)) {
            return false;
        }
        String attackerKingdomId = GotCastles.canonicalKingdomId(invadingCastle.ownerKingdomId());
        String defenderKingdomId = GotCastles.canonicalKingdomId(targetCastle.ownerKingdomId());
        CastleInvasionMode mode = GotCastles.chooseKingdomAiInvasionMode(world, invadingCastle, targetCastle, strategicCastles);
        if (mode == null) {
            return false;
        }
        String attackerLordName = invadingCastle.lordName();
        if (attackerLordName.isBlank()) {
            attackerLordName = "Unknown Lord";
        }
        String attackerHouseId = GotCastles.resolveOwnedHouseId(invadingCastle.ownerHouseId(), attackerKingdomId, "");
        StrategicCastleSnapshot strategicCastleSnapshot = partyOriginCastle = strategicCastles == null ? GotCastles.resolveCurrentLordOriginCastle(world, attackerKingdomId, attackerLordName, invadingCastle.castleId()) : GotCastles.resolveCurrentLordOriginCastle(strategicCastles, world, attackerKingdomId, attackerLordName, invadingCastle.castleId());
        if (partyOriginCastle == null) {
            partyOriginCastle = invadingCastle;
        }
        int partyStartChunkX = LordWarPartyHelper.blockToChunk(partyOriginCastle.bannerPos().method_10263());
        int partyStartChunkZ = LordWarPartyHelper.blockToChunk(partyOriginCastle.bannerPos().method_10260());
        int partyTargetChunkX = LordWarPartyHelper.blockToChunk(targetCastle.bannerPos().method_10263());
        int partyTargetChunkZ = LordWarPartyHelper.blockToChunk(targetCastle.bannerPos().method_10260());
        long progressTick = GotCastles.currentKingdomAiProgressTick(world);
        long travelDurationTicks = LordWarPartyHelper.travelDurationTicks(partyStartChunkX, partyStartChunkZ, partyTargetChunkX, partyTargetChunkZ);
        CastleTier attackerHighestTier = strategicCastles == null ? GotCastles.highestOwnedTierForLord(world, attackerKingdomId, attackerLordName, invadingCastle.castleId()) : GotCastles.highestOwnedTierForLord(strategicCastles, attackerKingdomId, attackerLordName, invadingCastle.castleId());
        long invasionDay = GotGameRules.trackedDay(world);
        KingdomAiInvasionWorldState.KingdomAiInvasion invasion = new KingdomAiInvasionWorldState.KingdomAiInvasion(invadingCastle.castleId(), targetCastle.castleId(), attackerLordName, invadingCastle.ownerPlayerUuid() == null ? "" : invadingCastle.ownerPlayerUuid().toString(), attackerKingdomId, attackerHouseId, GotCastles.religionIdForKingdomAiInvasion(attackerKingdomId, invadingCastle.ownerReligionId()), defenderKingdomId, mode.getId(), invadingCastle.tier().getId(), attackerHighestTier.getId(), partyStartChunkX, partyStartChunkZ, partyTargetChunkX, partyTargetChunkZ, progressTick, progressTick + Math.max(warmupOverrideTicks != null ? Math.max(1L, warmupOverrideTicks) : 1L, travelDurationTicks), Math.max(warmupOverrideTicks != null ? Math.max(1L, warmupOverrideTicks) : 1L, travelDurationTicks), progressTick + Math.max(warmupOverrideTicks != null ? Math.max(1L, warmupOverrideTicks) : 1L, travelDurationTicks));
        KingdomAiInvasionWorldState invasionState = KingdomAiInvasionWorldState.get(world);
        invasionState.put(invasion);
        invasionState.setNextInvasionDayForCastle(invadingCastle.castleId(), invasionDay + 4L);
        invasionState.setNextInvasionDayForLord(GotCastles.lordInvasionCooldownKey(attackerKingdomId, attackerLordName), invasionDay + 1L);
        GotCastles.setStrategicRaidEndsAtTick(world, targetCastle.castleId(), world.method_8510() + invasion.warmupDurationTicks());
        GotCastles.appendKingdomAiInvasionAttemptLog(world, invasion, targetCastle);
        GotCastles.syncStateToPlayers(world);
        GotCastles.syncCastleMapToPlayers(world);
        return true;
    }

    private static void startLiveKingdomAiInvasion(class_3218 world, KingdomAiInvasionWorldState state, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, CastleInstance castle, class_3222 defendingPlayer) {
        if (invasion.liveBattleStarted()) {
            return;
        }
        GotCastles.ensureKingdomAiDefendersPresent(world, castle);
        StrategicCastleSnapshot targetCastle = GotCastles.resolveStrategicCastle(world, castle.getId());
        class_3222 defenderTarget = null;
        if (targetCastle != null) {
            for (class_3222 player : GotCastles.playersInCastleClaim(world, castle)) {
                if (GotCastles.resolvePlayerSideForKingdomAiInvasion(world, invasion, targetCastle, player) != KingdomAiBattleSide.DEFENDER) continue;
                defenderTarget = player;
                break;
            }
        }
        GotCastles.spawnKingdomAiAttackers(world, invasion, castle, defenderTarget);
        invasion.setLiveBattleStarted(true);
        invasion.setAttackerCount(invasion.attackerUuids().size());
        GotCastles.setCastleGatesInvasionMode(world, castle.getId(), true);
        state.method_80();
        Iterator<class_3222> iterator = GotCastles.playersInCastleClaim(world, castle).iterator();
        while (iterator.hasNext()) {
            class_3222 player;
            KingdomAiBattleSide side = GotCastles.resolvePlayerSideForKingdomAiInvasion(world, invasion, targetCastle, player = iterator.next());
            player.method_7353((class_2561)class_2561.method_43470((String)(side == KingdomAiBattleSide.ATTACKER ? "Kingdom invasion started. Attack the castle." : "Kingdom invasion started. Defend the castle.")).method_27692(class_124.field_1061), false);
        }
    }

    private static void resolvePassiveKingdomAiInvasion(class_3218 world, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, StrategicCastleSnapshot castle) {
        boolean successful;
        CastleTier attackingCastleTier = CastleTier.byId(invasion.attackingCastleTierId());
        if (attackingCastleTier == null) {
            attackingCastleTier = CastleTier.byId(invasion.attackerHighestTierId());
        }
        double attackerChance = GotCastles.attackerChanceForAiInvasion(CastleInvasionMode.byId(invasion.modeId()), castle.tier(), attackingCastleTier);
        if (GotCastles.isCastlelessOriginId(invasion.invadingCastleId())) {
            attackerChance = Math.max(0.0, attackerChance - 0.05);
        }
        boolean bl = successful = world.field_9229.method_43058() < attackerChance;
        if (successful) {
            GotCastles.completeKingdomAiInvasion(world, invasion, castle);
        } else {
            GotCastles.failKingdomAiInvasion(world, invasion, castle);
        }
    }

    private static void completeKingdomAiInvasion(class_3218 world, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, StrategicCastleSnapshot castle) {
        CastleInvasionMode mode = CastleInvasionMode.byId(invasion.modeId());
        CastleInstance generatedCastle = GotCastles.getWorldState(world).get(castle.castleId());
        String attackerReligionId = GotCastles.religionIdForKingdomAiInvasion(invasion.attackerKingdomId(), invasion.attackerReligionId());
        String attackerHouseId = GotCastles.resolveOwnedHouseId(invasion.attackerHouseId(), invasion.attackerKingdomId(), "");
        UUID attackerPlayerUuid = GotCastles.parseUuid(invasion.attackerPlayerUuid());
        boolean dynastyConquest = attackerPlayerUuid != null && GotDynastyNpcs.isDynastyOwnedLordName(world, attackerPlayerUuid, invasion.attackerLordName());
        switch (mode) {
            case CRUSADE: {
                if (generatedCastle != null) {
                    generatedCastle.setOwnerReligionId(attackerReligionId);
                    GotCastles.respawnCastlePopulation(world, generatedCastle);
                }
                GotCastles.putPendingCastleOwnership(world, castle.castleId(), "", castle.ownerKingdomId(), castle.ownerHouseId(), attackerReligionId, castle.lordName(), 0L);
                break;
            }
            case RAID: {
                if (generatedCastle == null) break;
                GotCastles.respawnCastlePopulation(world, generatedCastle);
                break;
            }
            case CONQUER: {
                if (generatedCastle != null) {
                    generatedCastle.setOwnerPlayerUuid(dynastyConquest ? attackerPlayerUuid : null);
                    generatedCastle.setOwnerKingdomId(invasion.attackerKingdomId());
                    generatedCastle.setOwnerHouseId(attackerHouseId);
                    generatedCastle.setOwnerReligionId(attackerReligionId);
                    generatedCastle.setLordName(invasion.attackerLordName());
                    GotCastles.respawnCastlePopulation(world, generatedCastle);
                    GotCastles.discardMovedHouseLordFromOldSeat(world, invasion);
                    GotCastles.resetCastleRecaptureState(world, generatedCastle, null);
                    GotCastles.endCastleRecapture(world, generatedCastle.getId(), false, false);
                }
                GotCastles.putPendingCastleOwnership(world, castle.castleId(), dynastyConquest ? invasion.attackerPlayerUuid() : "", invasion.attackerKingdomId(), attackerHouseId, attackerReligionId, invasion.attackerLordName(), 0L);
                break;
            }
            case CHALLENGE: {
                return;
            }
        }
        GotCastles.appendKingdomAiInvasionOutcomeLog(world, invasion, castle, true);
        GotCastles.endKingdomAiInvasion(world, castle.castleId(), true, mode == CastleInvasionMode.CONQUER);
        GotCastles.syncStateToPlayers(world);
        GotCastles.syncCastleMapToPlayers(world);
    }

    private static void failKingdomAiInvasion(class_3218 world, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, StrategicCastleSnapshot castle) {
        GotCastles.appendKingdomAiInvasionOutcomeLog(world, invasion, castle, false);
        GotCastles.endKingdomAiInvasion(world, castle.castleId(), true, false);
        GotCastles.syncStateToPlayers(world);
    }

    private static void endKingdomAiInvasion(class_3218 world, String targetCastleId, boolean startCooldown) {
        GotCastles.endKingdomAiInvasion(world, targetCastleId, startCooldown, false);
    }

    private static void endKingdomAiInvasion(class_3218 world, String targetCastleId, boolean startCooldown, boolean preserveResolvedOwnership) {
        CastleInstance castle;
        if (world == null || targetCastleId == null || targetCastleId.isBlank()) {
            return;
        }
        KingdomAiInvasionWorldState state = KingdomAiInvasionWorldState.get(world);
        KingdomAiInvasionWorldState.KingdomAiInvasion invasion = state.get(targetCastleId);
        if (invasion != null) {
            GotCastles.clearKingdomAiAttackers(world, invasion);
            state.remove(targetCastleId);
        }
        if ((castle = GotCastles.getWorldState(world).get(targetCastleId)) != null) {
            if (startCooldown) {
                GotCastles.startCastleCooldown(world, castle);
            } else {
                castle.setRaidState(CastleRaidState.PEACE);
                castle.setRaidEndsAtTick(0L);
            }
        }
        StrategicCastleSnapshot snapshot = GotCastles.resolveStrategicCastle(world, targetCastleId);
        if (!preserveResolvedOwnership && snapshot != null && !snapshot.generated()) {
            GotCastles.putPendingCastleOwnership(world, snapshot.castleId(), snapshot.ownerPlayerUuid() == null ? "" : snapshot.ownerPlayerUuid().toString(), snapshot.ownerKingdomId(), snapshot.ownerHouseId(), snapshot.ownerReligionId(), snapshot.lordName(), startCooldown ? world.method_8510() + 6000L : 0L);
        }
        GotCastles.clearKingdomAiBossBar(targetCastleId);
        GotCastles.setCastleGatesInvasionMode(world, targetCastleId, false);
        GotCastles.syncCastleMapToPlayers(world);
    }

    private static void ensureKingdomAiDefendersPresent(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null || GotCastles.hasLivingCastleDefenders(world, castle.getId())) {
            return;
        }
        GotCastles.respawnCastlePopulation(world, castle);
    }

    private static void spawnKingdomAiAttackers(class_3218 world, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, CastleInstance targetCastle, @Nullable class_3222 defendingPlayer) {
        AbstractKingdomLordNpcEntity attackerLord;
        Kingdom kingdom = GotKingdoms.get(class_2960.method_12829((String)invasion.attackerKingdomId()));
        if (kingdom == null) {
            return;
        }
        class_1299<? extends AbstractKingdomGuardNpcEntity> guardType = GotEntities.guardTypeForKingdom(kingdom);
        class_1299<? extends AbstractKingdomLordNpcEntity> lordType = GotEntities.lordTypeForKingdom(kingdom);
        if (guardType == null) {
            return;
        }
        long expiresAtTick = world.method_8510() + 1200L;
        int attackerCount = GotCastles.attackerTroopCountFor(targetCastle.getTier());
        for (int index = 0; index < attackerCount; ++index) {
            AbstractKingdomGuardNpcEntity attacker = GotCastles.spawnRecaptureNpc(world, guardType, targetCastle, 6200 + index);
            if (attacker == null) continue;
            if (defendingPlayer != null) {
                attacker.alertAgainst(defendingPlayer, expiresAtTick);
            }
            if (CastleInvasionMode.byId(invasion.modeId()) == CastleInvasionMode.CONQUER) {
                GotCastles.applyConquerBuffs(attacker);
            }
            invasion.addAttackerUuid(attacker.method_5667());
        }
        if (lordType != null && (attackerLord = GotCastles.spawnRecaptureNpc(world, lordType, targetCastle, 6900)) != null) {
            attackerLord.method_5665((class_2561)class_2561.method_43470((String)invasion.attackerLordName()));
            if (defendingPlayer != null) {
                attackerLord.alertAgainst(defendingPlayer, expiresAtTick);
            }
            if (CastleInvasionMode.byId(invasion.modeId()) == CastleInvasionMode.CONQUER) {
                GotCastles.applyConquerBuffs(attackerLord);
            }
            invasion.addAttackerUuid(attackerLord.method_5667());
        }
    }

    private static void pruneKingdomAiAttackers(class_3218 world, KingdomAiInvasionWorldState state, KingdomAiInvasionWorldState.KingdomAiInvasion invasion) {
        boolean changed = false;
        for (UUID attackerUuid : List.copyOf(invasion.attackerUuids())) {
            class_1309 living;
            class_1297 entity = world.method_14190(attackerUuid);
            if (entity instanceof class_1309 && (living = (class_1309)entity).method_5805()) continue;
            ACTIVE_GATE_BREACHES.remove(attackerUuid);
            invasion.removeAttackerUuid(attackerUuid);
            changed = true;
        }
        if (changed) {
            state.method_80();
        }
    }

    private static void clearKingdomAiAttackers(class_3218 world, KingdomAiInvasionWorldState.KingdomAiInvasion invasion) {
        for (UUID attackerUuid : invasion.attackerUuids()) {
            ACTIVE_GATE_BREACHES.remove(attackerUuid);
            class_1297 entity = world.method_14190(attackerUuid);
            if (entity == null) continue;
            entity.method_31472();
        }
        invasion.clearAttackers();
    }

    private static void discardMovedHouseLordFromOldSeat(class_3218 world, KingdomAiInvasionWorldState.KingdomAiInvasion invasion) {
        if (world == null || invasion == null || invasion.attackerLordName().isBlank()) {
            return;
        }
        String sourceCastleId = GotCastles.realCastleIdForStrategicId(invasion.invadingCastleId());
        if (sourceCastleId.isBlank() || sourceCastleId.equals(invasion.targetCastleId())) {
            return;
        }
        CastleInstance sourceCastle = GotCastles.getWorldState(world).get(sourceCastleId);
        if (sourceCastle == null) {
            return;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)sourceCastle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        for (AbstractKingdomLordNpcEntity lord2 : world.method_8390(AbstractKingdomLordNpcEntity.class, searchBox, lord -> lord.method_5805() && sourceCastleId.equals(lord.got$getCastle()) && lord.method_5797() != null && invasion.attackerLordName().equals(lord.method_5797().getString()))) {
            lord2.method_31472();
        }
    }

    private static void directKingdomAiCombat(class_3218 world, CastleInstance castle, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, class_3222 defendingPlayer) {
        StrategicCastleSnapshot targetCastle = GotCastles.resolveStrategicCastle(world, castle.getId());
        if (targetCastle == null) {
            return;
        }
        for (UUID attackerUuid : invasion.attackerUuids()) {
            class_1309 target;
            class_1308 attacker;
            class_1297 entity = world.method_14190(attackerUuid);
            if (!(entity instanceof class_1308) || !(attacker = (class_1308)entity).method_5805() || GotCastles.handleGateBreach(world, castle, attacker)) continue;
            if (attacker.method_5968() != null && !GotCastles.canBattleSee((class_1309)attacker, attacker.method_5968())) {
                attacker.method_5980(null);
            }
            if ((target = GotCastles.findNearestBattleTarget(world, castle.getId(), (class_1309)attacker, CastleBattleParticipantSide.ATTACKER, 64.0)) != null) {
                attacker.method_5980(target);
                attacker.method_5942().method_6335((class_1297)target, 1.2);
                continue;
            }
            attacker.method_5980(null);
            class_243 bannerCenter = class_243.method_24953((class_2382)castle.getBannerPos());
            attacker.method_5942().method_6337(bannerCenter.field_1352, bannerCenter.field_1351, bannerCenter.field_1350, 1.1);
        }
        class_238 defenseBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)32.0, (double)128.0);
        for (AbstractKingdomNpcEntity defender : world.method_8390(AbstractKingdomNpcEntity.class, defenseBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()) && !invasion.attackerUuids().contains(npc.method_5667()) && npc instanceof class_1308)) {
            AbstractKingdomNpcEntity mobDefender;
            if (defender instanceof class_1308 && (mobDefender = defender).method_5968() != null && !GotCastles.canBattleSee((class_1309)mobDefender, mobDefender.method_5968())) {
                mobDefender.method_5980(null);
            }
            class_1309 target = GotCastles.findNearestBattleParticipantOnSide(world, castle.getId(), (class_1309)defender, CastleBattleParticipantSide.ATTACKER, 64.0);
            if (!(defender instanceof class_1308)) continue;
            AbstractKingdomNpcEntity mobDefender2 = defender;
            mobDefender2.method_5980(target);
        }
    }

    private static boolean handleGateBreach(class_3218 world, CastleInstance castle, class_1308 attacker) {
        class_2680 targetState;
        boolean breached;
        if (world == null || castle == null || attacker == null || !attacker.method_5805()) {
            return false;
        }
        GateBreachProgress progress = ACTIVE_GATE_BREACHES.get(attacker.method_5667());
        if (progress == null || !castle.getId().equals(progress.castleId())) {
            GotCastles.clearGateBreakOverlay(world, attacker, progress);
            progress = GotCastles.acquireGateBreachProgress(world, castle, (class_1309)attacker);
            if (progress == null) {
                ACTIVE_GATE_BREACHES.remove(attacker.method_5667());
                return false;
            }
            ACTIVE_GATE_BREACHES.put(attacker.method_5667(), progress);
        }
        boolean bl = breached = !GotCastles.isGateBreachTarget(targetState = world.method_8320(progress.targetPos()));
        if (!breached) {
            class_243 breachCenter = class_243.method_24953((class_2382)progress.approachPos());
            attacker.method_5980(null);
            if (attacker.method_5707(breachCenter) > 1.5625) {
                attacker.method_5942().method_6337(breachCenter.field_1352, breachCenter.field_1351, breachCenter.field_1350, 1.1);
                return true;
            }
            int updatedWork = progress.accumulatedWork() + GotCastles.gateBreakWorkPerTick(attacker, targetState);
            progress = progress.withAccumulatedWork(updatedWork);
            ACTIVE_GATE_BREACHES.put(attacker.method_5667(), progress);
            world.method_8517(attacker.method_5628(), progress.targetPos(), GotCastles.gateBreakStage(updatedWork, 60));
            if (updatedWork < 60) {
                return true;
            }
            GotCastles.breakGateTarget(world, progress);
            GotCastles.clearGateBreakOverlay(world, attacker, progress);
            breached = true;
        }
        class_243 moveThroughCenter = class_243.method_24953((class_2382)progress.moveThroughPos());
        attacker.method_5980(null);
        if (attacker.method_5707(moveThroughCenter) > 6.25) {
            attacker.method_5942().method_6337(moveThroughCenter.field_1352, moveThroughCenter.field_1351, moveThroughCenter.field_1350, 1.15);
            return true;
        }
        GotCastles.clearGateBreakOverlay(world, attacker, progress);
        ACTIVE_GATE_BREACHES.remove(attacker.method_5667());
        return false;
    }

    @Nullable
    private static GateBreachProgress acquireGateBreachProgress(class_3218 world, CastleInstance castle, class_1309 attacker) {
        GateBreachProgress blockingGate = GotCastles.findBlockingGateBreachProgress(world, castle, attacker, class_243.method_24953((class_2382)castle.getBannerPos()));
        if (blockingGate != null) {
            return blockingGate;
        }
        class_2338 bestMechanism = null;
        double bestDistance = Double.MAX_VALUE;
        for (class_2338 mechanismPos : GateLocator.positions(world)) {
            double bannerDistance;
            double attackerDistance;
            double distanceScore;
            class_2680 mechanismState;
            if (!world.method_8393(mechanismPos.method_10263() >> 4, mechanismPos.method_10260() >> 4) || class_243.method_24953((class_2382)mechanismPos).method_1025(class_243.method_24953((class_2382)castle.getBannerPos())) > 40000.0 || !((mechanismState = world.method_8320(mechanismPos)).method_26204() instanceof GateMechanismBlock) || ((Boolean)mechanismState.method_11654((class_2769)GateMechanismBlock.BROKEN)).booleanValue() || (Integer)mechanismState.method_11654((class_2769)GateMechanismBlock.EXTENSION) <= 0 || !((distanceScore = (attackerDistance = attacker.method_5707(class_243.method_24953((class_2382)mechanismPos))) + (bannerDistance = class_243.method_24953((class_2382)mechanismPos).method_1025(class_243.method_24953((class_2382)castle.getBannerPos()))) * 1.5) < bestDistance)) continue;
            bestDistance = distanceScore;
            bestMechanism = mechanismPos.method_10062();
        }
        return GotCastles.buildGateBreachProgress(world, castle, bestMechanism);
    }

    @Nullable
    private static GateBreachProgress findBlockingGateBreachProgress(class_3218 world, CastleInstance castle, class_1309 attacker, class_243 targetPos) {
        if (world == null || castle == null || attacker == null || targetPos == null) {
            return null;
        }
        class_243 from = attacker.method_33571();
        class_243 delta = targetPos.method_1020(from);
        double length = delta.method_1033();
        if (length < 1.0E-4) {
            return null;
        }
        class_243 step = delta.method_1029().method_1021(0.4);
        class_243 cursor = from;
        for (double traveled = 0.0; traveled <= length; traveled += 0.4) {
            class_2338 pos = class_2338.method_49638((class_2374)cursor);
            class_2338 mechanismPos = GotCastles.resolveGateMechanismForBreach(world, pos);
            if (mechanismPos != null) {
                return GotCastles.buildGateBreachProgress(world, castle, mechanismPos);
            }
            cursor = cursor.method_1019(step);
        }
        return null;
    }

    @Nullable
    private static class_2338 resolveGateMechanismForBreach(class_3218 world, class_2338 pos) {
        if (world == null || pos == null) {
            return null;
        }
        class_2680 state = world.method_8320(pos);
        if (state.method_27852(GotBlocks.GATE_FENCE)) {
            for (int offset = 1; offset <= 6; ++offset) {
                class_2338 mechanismPos = pos.method_10086(offset);
                class_2680 mechanismState = world.method_8320(mechanismPos);
                if (mechanismState.method_26204() instanceof GateMechanismBlock && !((Boolean)mechanismState.method_11654((class_2769)GateMechanismBlock.BROKEN)).booleanValue() && (Integer)mechanismState.method_11654((class_2769)GateMechanismBlock.EXTENSION) > 0) {
                    return mechanismPos.method_10062();
                }
                if (!mechanismState.method_27852(GotBlocks.GATE_FENCE)) break;
            }
            return null;
        }
        if (state.method_26204() instanceof GateMechanismBlock && !((Boolean)state.method_11654((class_2769)GateMechanismBlock.BROKEN)).booleanValue() && (Integer)state.method_11654((class_2769)GateMechanismBlock.EXTENSION) > 0 && !GateMechanismBlock.isPassable(state)) {
            return pos.method_10062();
        }
        return null;
    }

    @Nullable
    private static GateBreachProgress buildGateBreachProgress(class_3218 world, CastleInstance castle, @Nullable class_2338 mechanismPos) {
        if (world == null || castle == null || mechanismPos == null) {
            return null;
        }
        class_2680 mechanismState = world.method_8320(mechanismPos);
        if (!(mechanismState.method_26204() instanceof GateMechanismBlock) || ((Boolean)mechanismState.method_11654((class_2769)GateMechanismBlock.BROKEN)).booleanValue() || (Integer)mechanismState.method_11654((class_2769)GateMechanismBlock.EXTENSION) <= 0) {
            return null;
        }
        int extension = (Integer)mechanismState.method_11654((class_2769)GateMechanismBlock.EXTENSION);
        class_2338 breachTarget = extension > 0 ? mechanismPos.method_10087(extension) : mechanismPos;
        class_2350 insideDirection = GotCastles.directionTowardBanner(mechanismPos, (class_2350)mechanismState.method_11654((class_2769)GateMechanismBlock.FACING), castle.getBannerPos());
        class_2338 approachPos = breachTarget.method_10079(insideDirection.method_10153(), 1);
        class_2338 moveThroughPos = breachTarget.method_10079(insideDirection, 3);
        return new GateBreachProgress(castle.getId(), mechanismPos.method_10062(), breachTarget, approachPos, moveThroughPos, 0);
    }

    private static boolean isGateBreachTarget(class_2680 state) {
        if (state.method_26204() instanceof GateFenceBlock) {
            return true;
        }
        return state.method_26204() instanceof GateMechanismBlock && (Boolean)state.method_11654((class_2769)GateMechanismBlock.BROKEN) == false && (Integer)state.method_11654((class_2769)GateMechanismBlock.EXTENSION) > 0;
    }

    private static int gateBreakWorkPerTick(class_1308 attacker, class_2680 state) {
        float speed;
        class_1799 stack = attacker.method_6047();
        float f = speed = stack.method_7960() ? 1.0f : Math.max(1.0f, stack.method_7924(state));
        if (stack.method_7909() instanceof class_1743) {
            speed *= 1.5f;
        }
        return Math.max(1, class_3532.method_15375((float)speed));
    }

    private static void breakGateTarget(class_3218 world, GateBreachProgress progress) {
        class_2680 targetState = world.method_8320(progress.targetPos());
        if (targetState.method_26204() instanceof GateFenceBlock) {
            GateFenceBlock.breakGateColumn(world, progress.targetPos());
            return;
        }
        class_2680 mechanismState = world.method_8320(progress.mechanismPos());
        if (mechanismState.method_26204() instanceof GateMechanismBlock) {
            GateMechanismBlock.breakFromFence(world, progress.mechanismPos(), mechanismState);
        }
    }

    private static int gateBreakStage(int accumulatedWork, int requiredWork) {
        if (requiredWork <= 0) {
            return 9;
        }
        return class_3532.method_15340((int)(accumulatedWork * 10 / requiredWork), (int)0, (int)9);
    }

    private static void clearGateBreakOverlay(class_3218 world, class_1308 attacker, @Nullable GateBreachProgress progress) {
        if (world != null && attacker != null && progress != null) {
            world.method_8517(attacker.method_5628(), progress.targetPos(), -1);
        }
    }

    static void setCastleGatesInvasionMode(class_3218 world, String castleId, boolean invasionActive) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            return;
        }
        for (class_2338 mechanismPos : GateLocator.positions(world)) {
            class_2680 state;
            if (!world.method_8393(mechanismPos.method_10263() >> 4, mechanismPos.method_10260() >> 4) || !GotCastles.isWithinCastleClaim(world, castleId, mechanismPos) || !((state = world.method_8320(mechanismPos)).method_26204() instanceof GateMechanismBlock)) continue;
            GateLocator.track(world, mechanismPos);
            if (invasionActive) {
                GateMechanismBlock.forceOpenForInvasion(world, mechanismPos, state);
                continue;
            }
            GateMechanismBlock.restoreAfterInvasion(world, mechanismPos, state);
        }
    }

    public static boolean shouldGateStayInvasionLocked(class_3218 world, class_2338 pos) {
        if (world == null || pos == null) {
            return false;
        }
        CastleInstance castle = GotCastles.getWorldState(world).findClaimAt(pos);
        if (castle == null || !GotCastles.isWithinCastleClaim(world, castle.getId(), pos)) {
            return false;
        }
        return GotCastles.isCastleUnderAnyInvasion(world, castle.getId());
    }

    public static boolean isCastleUnderAnyInvasion(class_3218 world, String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return false;
        }
        CastleInstance claim = GotCastles.getWorldState(world).get(castleId);
        if (GotCastles.isHireableCampClaim(claim)) {
            return false;
        }
        if (GotCastles.findActiveInvasionForCastle(castleId) != null) {
            return true;
        }
        CastleRecaptureState recapture = GotCastles.getWorldState(world).getRecapture(castleId);
        if (recapture != null && recapture.getStage() != CastleRecaptureStage.DORMANT) {
            return true;
        }
        return KingdomAiInvasionWorldState.get(world).get(castleId) != null;
    }

    private static void reconcileGateInvasionState(class_3218 world) {
        if (world == null || !GotDimensionKeys.ICEANDFIRE.equals((Object)world.method_27983())) {
            return;
        }
        GotCastles.advanceLegacyGateDiscovery(world);
        GateLocator.purgeStale(world, 16);
        if (world.method_8510() % 100L != 0L) {
            return;
        }
        for (class_2338 mechanismPos : GateLocator.positions(world)) {
            class_2680 state;
            if (!world.method_8393(mechanismPos.method_10263() >> 4, mechanismPos.method_10260() >> 4) || !((state = world.method_8320(mechanismPos)).method_26204() instanceof GateMechanismBlock)) continue;
            GateLocator.track(world, mechanismPos);
            GateMechanismBlock.reconcileInvasionState(world, mechanismPos, state);
        }
    }

    private static void advanceLegacyGateDiscovery(class_3218 world) {
        long worldTime;
        LegacyGateDiscoveryState discovery = LEGACY_GATE_DISCOVERY.computeIfAbsent(world, ignored -> new LegacyGateDiscoveryState());
        if (discovery.shouldStartSweep(worldTime = world.method_8510())) {
            discovery.beginSweep(GotCastles.getWorldState(world).getAll(), worldTime);
        }
        discovery.submitLoadedClaimChunks(world, worldTime, 1, 32);
        if (GateLocator.pendingLegacyDiscoveryChunks(world) > 0) {
            GateLocator.advanceLegacyDiscovery(world, 2, 64);
        }
    }

    private static class_2350 directionTowardBanner(class_2338 mechanismPos, class_2350 gateFacing, class_2338 bannerPos) {
        class_2350[] class_2350Array;
        if (gateFacing.method_10166() == class_2350.class_2351.field_11051) {
            class_2350[] class_2350Array2 = new class_2350[2];
            class_2350Array2[0] = class_2350.field_11043;
            class_2350Array = class_2350Array2;
            class_2350Array2[1] = class_2350.field_11035;
        } else {
            class_2350[] class_2350Array3 = new class_2350[2];
            class_2350Array3[0] = class_2350.field_11034;
            class_2350Array = class_2350Array3;
            class_2350Array3[1] = class_2350.field_11039;
        }
        class_2350[] candidates = class_2350Array;
        class_2350 best = candidates[0];
        double bestDistance = Double.MAX_VALUE;
        for (class_2350 candidate : candidates) {
            double distance = class_243.method_24953((class_2382)mechanismPos.method_10079(candidate, 4)).method_1025(class_243.method_24953((class_2382)bannerPos));
            if (!(distance < bestDistance)) continue;
            bestDistance = distance;
            best = candidate;
        }
        return best;
    }

    @Nullable
    private static class_1309 findKingdomAiTarget(class_3218 world, CastleInstance castle, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, StrategicCastleSnapshot targetCastle, class_1309 attacker, List<class_3222> playersInClaim) {
        ArrayList<Object> candidates = new ArrayList<Object>();
        for (class_3222 player : playersInClaim) {
            if (GotCastles.resolvePlayerSideForKingdomAiInvasion(world, invasion, targetCastle, player) != KingdomAiBattleSide.DEFENDER || !(attacker.method_5858((class_1297)player) <= 4096.0)) continue;
            candidates.add(player);
        }
        class_238 searchBox = class_238.method_30048((class_243)attacker.method_19538(), (double)128.0, (double)24.0, (double)128.0);
        for (AbstractKingdomNpcEntity defender : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()) && !invasion.attackerUuids().contains(npc.method_5667()) && !npc.method_5667().equals(attacker.method_5667()))) {
            if (!(defender instanceof AbstractKingdomGuardNpcEntity) && !(defender instanceof AbstractKingdomLordNpcEntity)) continue;
            candidates.add(defender);
        }
        return candidates.stream().min(Comparator.comparingDouble(arg_0 -> ((class_1309)attacker).method_5858(arg_0))).orElse(null);
    }

    @Nullable
    private static class_1309 findNearestKingdomAiAttackerTarget(class_3218 world, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, StrategicCastleSnapshot targetCastle, class_243 pos, List<class_3222> playersInClaim) {
        class_1309 nearest = null;
        double bestDistance = Double.MAX_VALUE;
        for (UUID attackerUuid : invasion.attackerUuids()) {
            double distance;
            class_1309 living;
            class_1297 entity = world.method_14190(attackerUuid);
            if (!(entity instanceof class_1309) || !(living = (class_1309)entity).method_5805() || !((distance = living.method_19538().method_1025(pos)) < bestDistance)) continue;
            bestDistance = distance;
            nearest = living;
        }
        for (class_3222 player : playersInClaim) {
            double distance;
            if (GotCastles.resolvePlayerSideForKingdomAiInvasion(world, invasion, targetCastle, player) != KingdomAiBattleSide.ATTACKER || !((distance = player.method_19538().method_1025(pos)) < bestDistance)) continue;
            bestDistance = distance;
            nearest = player;
        }
        return nearest;
    }

    private static boolean startIncomingCastleRecapture(class_3218 world, CastleInstance castle, boolean manualTrigger, @Nullable Long incomingDurationTicksOverride) {
        return GotCastleRecaptures.startIncoming(world, castle, manualTrigger, incomingDurationTicksOverride);
    }

    static <T extends AbstractKingdomNpcEntity> T spawnRecaptureNpc(class_3218 world, class_1299<T> entityType, CastleInstance castle, int seedOffset) {
        return GotCastleRecaptures.spawnRecaptureNpc(world, entityType, castle, seedOffset);
    }

    static void directRecaptureCombat(class_3218 world, CastleInstance castle, CastleRecaptureState recapture, class_3222 player) {
        class_1308 attacker;
        for (UUID attackerUuid : recapture.getAttackerUuids()) {
            class_1309 target;
            class_1297 entity = world.method_14190(attackerUuid);
            if (!(entity instanceof class_1308) || !(attacker = (class_1308)entity).method_5805() || GotCastles.handleGateBreach(world, castle, attacker)) continue;
            if (attacker.method_5968() != null && !GotCastles.canBattleSee((class_1309)attacker, attacker.method_5968())) {
                attacker.method_5980(null);
            }
            if ((target = GotCastles.findNearestBattleTarget(world, castle.getId(), (class_1309)attacker, CastleBattleParticipantSide.ATTACKER, 64.0)) != null) {
                attacker.method_5980(target);
                attacker.method_5942().method_6335((class_1297)target, 1.2);
                continue;
            }
            attacker.method_5980(null);
            class_243 bannerCenter = class_243.method_24953((class_2382)castle.getBannerPos());
            attacker.method_5942().method_6337(bannerCenter.field_1352, bannerCenter.field_1351, bannerCenter.field_1350, 1.1);
        }
        class_238 defenseBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)32.0, (double)128.0);
        for (AbstractKingdomNpcEntity defender : world.method_8390(AbstractKingdomNpcEntity.class, defenseBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()) && !recapture.isAttacker(npc.method_5667()) && npc instanceof class_1308)) {
            AbstractKingdomNpcEntity mobDefender;
            if (defender instanceof class_1308 && (mobDefender = defender).method_5968() != null && !GotCastles.canBattleSee((class_1309)mobDefender, mobDefender.method_5968())) {
                mobDefender.method_5980(null);
            }
            attacker = GotCastles.findNearestBattleParticipantOnSide(world, castle.getId(), (class_1309)defender, CastleBattleParticipantSide.ATTACKER, 64.0);
            if (!(defender instanceof class_1308)) continue;
            AbstractKingdomNpcEntity mobDefender2 = defender;
            mobDefender2.method_5980((class_1309)attacker);
        }
    }

    static boolean hasLivingCastleDefenders(class_3218 world, String castleId) {
        return GotCastleRecaptures.hasLivingCastleDefenders(world, castleId);
    }

    private static void resetCastleRecaptureState(class_3218 world, CastleInstance castle, @Nullable UUID playerUuid) {
        GotCastleRecaptures.resetState(world, castle, playerUuid);
    }

    private static void endCastleRecapture(class_3218 world, String castleId, boolean keepDormantState, boolean syncPlayers) {
        GotCastleRecaptures.end(world, castleId, keepDormantState, syncPlayers);
    }

    private static void handleCastleRecaptureDisconnect(class_3218 world, UUID playerUuid) {
        GotCastles.handleCastleRecaptureDisconnect(world, playerUuid, true);
    }

    private static void handleCastleRecaptureDisconnect(class_3218 world, UUID playerUuid, boolean notifyPlayer) {
        GotCastleRecaptures.handleDisconnect(world, playerUuid, notifyPlayer);
    }

    private static void winCastleRecaptureDefense(class_3218 world, CastleInstance castle, CastleRecaptureState recapture) {
        GotCastleRecaptures.winDefense(world, castle, recapture);
    }

    private static void resolveSuccessfulRecapture(class_3218 world, CastleInstance castle, CastleRecaptureState recapture, class_2561 failureMessage) {
        GotCastleRecaptures.resolveFailure(world, castle, recapture, failureMessage);
    }

    private static String originalKingdomIdFor(CastleInstance castle) {
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(castle.getId());
        String kingdomId = metadata != null ? metadata.kingdomId() : castle.getOwnerKingdomId();
        return GotCastles.canonicalKingdomId(kingdomId);
    }

    private static String originalReligionIdFor(CastleInstance castle, String fallbackKingdomId) {
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(castle.getId());
        if (metadata != null && !metadata.religionId().isBlank()) {
            return metadata.religionId();
        }
        return GotCastles.defaultReligionIdForKingdom(fallbackKingdomId);
    }

    private static ClaimOutlinePayload buildClaimOutlinePayload(class_3218 world) {
        CastleWorldState state = GotCastles.getWorldState(world);
        ArrayList<ClaimOutlinePayload.Entry> claims = new ArrayList<ClaimOutlinePayload.Entry>(state.getAll().stream().map(castle -> {
            String houseId;
            String kingdomId;
            String name;
            HireableCampDefinition camp = GotCastles.getHireableCampDefinition(castle);
            CastlePresetMetadata metadata = presetMetadataByCastleId.get(castle.getId());
            String string = camp != null ? camp.claimName() : (name = metadata != null && !metadata.name().isBlank() ? metadata.name() : castle.getTitle().displayName());
            String string2 = camp != null ? "" : (kingdomId = GotCastles.canonicalKingdomId(!castle.getOwnerKingdomId().isBlank() ? castle.getOwnerKingdomId() : (metadata != null ? metadata.kingdomId() : "")));
            String string3 = camp != null ? "" : (houseId = GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), kingdomId, metadata != null ? metadata.houseId() : ""));
            String religionId = camp != null ? "" : (!castle.getOwnerReligionId().isBlank() ? castle.getOwnerReligionId() : (metadata != null ? metadata.religionId() : ""));
            String tier = camp != null ? "encampment" : castle.getTier().getId();
            return new ClaimOutlinePayload.Entry(castle.getId(), castle.getBannerPos(), castle.getClaimRadiusChunks(), name, kingdomId, houseId, religionId, tier, GotCastles.castleCooldownSeconds(world, castle), GotCastles.buildCityStructureOutlineEntries(castle));
        }).toList());
        claims.addAll(VillageWorldState.get(world).getAll().stream().map(village -> {
            CastleInstance boundCastle = state.get(village.getBoundCastleId());
            String kingdomId = GotCastles.canonicalKingdomId(boundCastle != null && !boundCastle.getOwnerKingdomId().isBlank() ? boundCastle.getOwnerKingdomId() : village.getKingdomId());
            String houseId = boundCastle == null ? "" : GotCastles.resolveOwnedHouseId(boundCastle.getOwnerHouseId(), kingdomId, "");
            String religionId = boundCastle == null ? "" : boundCastle.getOwnerReligionId();
            return new ClaimOutlinePayload.Entry(village.getId(), village.getCenterPos(), village.getClaimRadiusChunks(), village.getName(), kingdomId, houseId, religionId, "village", GotVillages.villageCooldownSeconds(world, village), List.of());
        }).toList());
        return new ClaimOutlinePayload(List.copyOf(claims));
    }

    private static List<ClaimOutlinePayload.StructureEntry> buildCityStructureOutlineEntries(CastleInstance castle) {
        List<CityStructureClaim> structureClaims = castle.getCityStructureClaims();
        if (structureClaims.isEmpty()) {
            return List.of();
        }
        Map profilesById = castle.getCastleNpcProfiles().stream().collect(Collectors.toMap(CastleNpcProfile::id, profile -> profile, (first, second) -> first, LinkedHashMap::new));
        ArrayList<ClaimOutlinePayload.StructureEntry> entries = new ArrayList<ClaimOutlinePayload.StructureEntry>(structureClaims.size());
        for (CityStructureClaim claim : structureClaims) {
            CastleNpcProfile owner = (CastleNpcProfile)profilesById.get(claim.ownerProfileId());
            class_3341 footprint = claim.footprint();
            entries.add(new ClaimOutlinePayload.StructureEntry(claim.id(), new class_2338(footprint.method_35415(), footprint.method_35416(), footprint.method_35417()), new class_2338(footprint.method_35418(), footprint.method_35419(), footprint.method_35420()), claim.structurePath(), claim.ownerProfileId(), owner == null ? "" : owner.uuid().toString(), owner == null ? "" : owner.displayName()));
        }
        return List.copyOf(entries);
    }

    private static int castleCooldownSeconds(class_3218 world, CastleInstance castle) {
        long endsAt;
        if (world == null || castle == null) {
            return 0;
        }
        ActiveCastleInvasion invasion = GotCastles.findActiveInvasionForCastle(castle.getId());
        long l = endsAt = invasion != null ? invasion.endsAtTick() : castle.getRaidEndsAtTick();
        if (endsAt <= 0L) {
            return 0;
        }
        long remaining = Math.max(0L, endsAt - world.method_8510());
        return class_3532.method_15386((float)((float)remaining / 20.0f));
    }

    private static CastleMapPayload buildCastleMapPayload(class_3218 world, class_3222 player) {
        GotCastles.indexCastleLordNames(world);
        CastleWorldState state = GotCastles.getWorldState(world);
        PlayerCastleTravelHolder travelHolder = (PlayerCastleTravelHolder)player;
        ArrayList<CastleMapPayload.Entry> castles = new ArrayList<CastleMapPayload.Entry>(overlaySites.stream().filter(site -> !state.isRejected(site.castleId())).map(site -> GotCastles.toStrategicCastleMapEntry(world, site, travelHolder)).filter(Objects::nonNull).toList());
        for (CastleInstance castle : state.getAll()) {
            CastleMapPayload.Entry entry = GotCastles.toPlayerKingdomCastleMapEntry(world, castle, travelHolder);
            if (entry == null) continue;
            castles.add(entry);
        }
        List<CastleMapPayload.Lane> lanes = GotCastles.getVoyageNetwork().lanes().stream().map(lane -> new CastleMapPayload.Lane(lane.fromId(), lane.toId(), lane.worldPoints().stream().map(point -> new CastleMapPayload.Point(class_3532.method_15357((double)point.field_1352), class_3532.method_15357((double)point.field_1350))).toList())).toList();
        List<CastleMapPayload.DynamicKingdomEntry> dynamicKingdoms = PlayerKingdomWorldState.get(world).getAll().stream().map(definition -> new CastleMapPayload.DynamicKingdomEntry(definition.kingdomId(), definition.displayName(), definition.parentKingdomId(), definition.houseId())).toList();
        return new CastleMapPayload(world.method_8510(), GotCastles.currentKingdomAiProgressTick(world), dynamicKingdoms, castles, lanes, GotCastles.buildWarPartyEntries(world));
    }

    private static List<CastleMapPayload.WarPartyEntry> buildWarPartyEntries(class_3218 world) {
        KingdomAiInvasionWorldState state = KingdomAiInvasionWorldState.get(world);
        ArrayList<CastleMapPayload.WarPartyEntry> entries = new ArrayList<CastleMapPayload.WarPartyEntry>();
        for (KingdomAiInvasionWorldState.KingdomAiInvasion invasion : state.getAll()) {
            StrategicCastleSnapshot targetCastle = GotCastles.resolveStrategicCastle(world, invasion.targetCastleId());
            if (targetCastle == null) continue;
            entries.add(new CastleMapPayload.WarPartyEntry(invasion.attackerKingdomId() + "|" + invasion.attackerLordName() + "|" + invasion.targetCastleId(), invasion.attackerLordName(), invasion.attackerKingdomId(), invasion.attackerHouseId(), invasion.targetCastleId(), targetCastle.displayName(), invasion.modeId(), invasion.partyStartChunkX(), invasion.partyStartChunkZ(), invasion.partyTargetChunkX(), invasion.partyTargetChunkZ(), invasion.partyDepartureTick(), invasion.partyArrivalTick(), invasion.partyArrived()));
        }
        return List.copyOf(entries);
    }

    @Nullable
    private static CastleMapPayload.Entry toStrategicCastleMapEntry(class_3218 world, OverlayCastleSite site, PlayerCastleTravelHolder travelHolder) {
        StrategicCastleSnapshot snapshot = GotCastles.resolveStrategicCastle(world, site.castleId());
        if (snapshot == null) {
            return null;
        }
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(snapshot.castleId());
        String name = metadata != null && !metadata.name().isBlank() ? metadata.name() : snapshot.displayName();
        String lore = metadata != null ? metadata.lore() : "";
        String originalKingdomId = GotCastles.canonicalKingdomId(metadata != null && !metadata.kingdomId().isBlank() ? metadata.kingdomId() : snapshot.ownerKingdomId());
        List<String> subtypeIds = GotCastles.resolveSubtypeIds(site, metadata);
        boolean unlocked = travelHolder.got$hasUnlockedCastle(snapshot.castleId());
        return new CastleMapPayload.Entry(snapshot.castleId(), snapshot.bannerPos().method_10263(), snapshot.bannerPos().method_10260(), snapshot.bannerPos().method_10263(), snapshot.bannerPos().method_10260(), snapshot.tier() == CastleTier.LORDSHIP || snapshot.tier() == CastleTier.CAPITAL, snapshot.tier() == CastleTier.CAPITAL, snapshot.tier().getId(), name, lore, GotCastles.canonicalKingdomId(snapshot.ownerKingdomId()), GotCastles.resolveOwnedHouseId(snapshot.ownerHouseId(), snapshot.ownerKingdomId(), metadata != null ? metadata.houseId() : ""), originalKingdomId, snapshot.ownerReligionId(), "", "", snapshot.lordName(), subtypeIds, unlocked, TourneyWorldState.get(world).isActiveCastle(snapshot.castleId()));
    }

    @Nullable
    private static CastleMapPayload.Entry toPlayerKingdomCastleMapEntry(class_3218 world, CastleInstance castle, PlayerCastleTravelHolder travelHolder) {
        if (world == null || castle == null || !"player_claim".equals(castle.getDefinitionId()) || castle.getOwnerKingdomId().isBlank()) {
            return null;
        }
        PlayerKingdomDefinition kingdom = PlayerKingdomWorldState.get(world).getByKingdomId(castle.getOwnerKingdomId());
        if (kingdom == null || !castle.getId().equals(kingdom.castleId())) {
            return null;
        }
        boolean unlocked = travelHolder.got$hasUnlockedCastle(castle.getId());
        return new CastleMapPayload.Entry(castle.getId(), castle.getBannerPos().method_10263(), castle.getBannerPos().method_10260(), castle.getBannerPos().method_10263(), castle.getBannerPos().method_10260(), false, false, CastleTier.SETTLEMENT.getId(), GotCastles.displayNameForCastle(castle), "", GotCastles.canonicalKingdomId(castle.getOwnerKingdomId()), GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), castle.getOwnerKingdomId(), kingdom.houseId()), GotCastles.canonicalKingdomId(kingdom.parentKingdomId()), castle.getOwnerReligionId().isBlank() ? GotCastles.defaultReligionIdForKingdom(castle.getOwnerKingdomId()) : castle.getOwnerReligionId(), "", "", GotCastles.resolveLordName(world, castle), List.of(), unlocked, TourneyWorldState.get(world).isActiveCastle(castle.getId()));
    }

    private static VoyageNetwork buildVoyageNetwork() {
        ArrayList<VoyageHarbourNode> harbours = new ArrayList<VoyageHarbourNode>();
        for (OverlayCastleSite site : overlaySites) {
            CastlePresetMetadata metadata;
            Set<CastleSubtype> subtypes = GotCastles.resolveSubtypes(site, metadata = presetMetadataByCastleId.get(site.castleId()));
            if (!subtypes.contains((Object)CastleSubtype.HARBOUR)) continue;
            String name = metadata != null && !metadata.name().isBlank() ? metadata.name() : GotCastles.pendingDisplayName(site);
            harbours.add(new VoyageHarbourNode(site.castleId(), name, site.anchor().method_10263(), site.anchor().method_10260()));
        }
        return VoyageNetworkBuilder.build(VoyageMapResources.worldMapImage(), VoyageMapResources.waterMapColors(), harbours);
    }

    private static VoyageNetwork getVoyageNetwork() {
        if (voyageNetwork == null) {
            voyageNetwork = GotCastles.buildVoyageNetwork();
        }
        return voyageNetwork;
    }

    public static List<ShipmentQuestLogic.CastleTarget> collectQuestTargets(class_3218 world) {
        if (world == null) {
            return List.of();
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        return overlaySites.stream().filter(site -> !state.isRejected(site.castleId())).filter(site -> {
            CastlePresetMetadata metadata = presetMetadataByCastleId.get(site.castleId());
            return metadata == null || metadata.generateVillage();
        }).map(site -> {
            CastleInstance placed = state.get(site.castleId());
            if (placed != null) {
                CastlePresetMetadata metadata = presetMetadataByCastleId.get(placed.getId());
                String name = metadata != null && !metadata.name().isBlank() ? metadata.name() : placed.getTitle().displayName();
                return new ShipmentQuestLogic.CastleTarget(placed.getId(), name, GotCastles.resolveLordName(world, placed), placed.getBannerPos().method_10263(), placed.getBannerPos().method_10260());
            }
            CastlePresetMetadata metadata = presetMetadataByCastleId.get(site.castleId());
            String name = metadata != null && !metadata.name().isBlank() ? metadata.name() : GotCastles.pendingDisplayName(site);
            return new ShipmentQuestLogic.CastleTarget(site.castleId(), name, GotCastles.resolvePendingLordName(world, site, metadata, metadata != null && !metadata.kingdomId().isBlank() ? metadata.kingdomId() : ""), site.anchor().method_10263(), site.anchor().method_10260());
        }).toList();
    }

    public static List<VillageAnchorTarget> collectVillageAnchorTargets(class_3218 world) {
        if (world == null) {
            return List.of();
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        return overlaySites.stream().filter(site -> !state.isRejected(site.castleId())).map(site -> {
            CastleInstance placed = state.get(site.castleId());
            if (placed != null) {
                String castleName;
                CastlePresetMetadata metadata = presetMetadataByCastleId.get(placed.getId());
                String string = castleName = metadata != null && !metadata.name().isBlank() ? metadata.name() : GotCastles.displayNameForCastle(placed);
                String kingdomId = GotCastles.canonicalKingdomId(!placed.getOwnerKingdomId().isBlank() ? placed.getOwnerKingdomId() : (metadata != null ? metadata.kingdomId() : ""));
                return new VillageAnchorTarget(placed.getId(), castleName, kingdomId, placed.getBannerPos().method_10263(), placed.getBannerPos().method_10260(), true);
            }
            CastlePresetMetadata metadata = presetMetadataByCastleId.get(site.castleId());
            String castleName = metadata != null && !metadata.name().isBlank() ? metadata.name() : GotCastles.pendingDisplayName(site);
            Kingdom kingdom = GotCastles.resolveKingdomForBiome(world, site.anchor());
            String defaultKingdomId = kingdom != null ? kingdom.getId().toString() : "";
            String kingdomId = GotCastles.canonicalKingdomId(metadata != null && !metadata.kingdomId().isBlank() ? metadata.kingdomId() : defaultKingdomId);
            return new VillageAnchorTarget(site.castleId(), castleName, kingdomId, site.anchor().method_10263(), site.anchor().method_10260(), false);
        }).toList();
    }

    private static void indexCastleLordNames(class_3218 world) {
        CastleWorldState state = GotCastles.getWorldState(world);
        boolean changed = false;
        for (CastleInstance castle : state.getAll()) {
            String resolved;
            if (GotCastles.isHireableCampClaim(castle) || castle.getOwnerPlayerUuid() != null || !castle.getLordName().isBlank() || (resolved = GotCastles.generateFallbackLordName(castle)).isBlank()) continue;
            castle.setLordName(resolved);
            changed = true;
        }
        if (changed) {
            state.method_80();
        }
    }

    private static void updateCastleDiscovery(class_3218 world) {
        CastleWorldState state = GotCastles.getWorldState(world);
        for (class_3222 player : world.method_18456()) {
            PlayerCastleTravelHolder holder;
            GotCastles.ensureNearestWorldgenCastleClaim(world, player.method_24515(), 512);
            CastleInstance claim = state.findClaimAt(player.method_24515());
            if (claim == null || !(holder = (PlayerCastleTravelHolder)player).got$unlockCastle(claim.getId())) continue;
            ServerPlayNetworking.send((class_3222)player, (class_8710)GotCastles.buildCastleMapPayload(world, player));
        }
    }

    private static void processOverlayCastleQueue(class_3218 world, CastleWorldState state, boolean allowPlanning) {
        List<class_243> activePlayerPositions = GotCastles.snapshotActivePlayerPositions(world);
        PendingCastleGeneration burstGeneration = GotCastles.findBurstPendingGeneration(world, state, activePlayerPositions);
        GotCastles.processChunkNativeOverlayCastleClaims(world, state, burstGeneration, activePlayerPositions);
        boolean burstProcessing = burstGeneration != null;
        long deadlineNanos = System.nanoTime() + (burstProcessing ? 30000000L : 15000000L);
        int operationBudget = burstProcessing ? 96 : 32;
        PendingGenerationBatchResult batchResult = GotCastles.processPendingOverlayCastleGenerations(world, state, operationBudget, deadlineNanos, allowPlanning, activePlayerPositions);
        int operations = batchResult.operations();
        if (allowPlanning && !batchResult.planned() && operations < operationBudget && System.nanoTime() < deadlineNanos) {
            OverlayCastleSite nextSite = null;
            double nextSiteDistanceSq = Double.MAX_VALUE;
            boolean nextSiteReadyForPlanning = false;
            for (OverlayCastleSite site : overlaySites) {
                if (state.contains(site.castleId()) || state.isRejected(site.castleId()) || state.hasPendingGeneration(site.castleId())) continue;
                if (GotCastles.isPriorityPendingGeneration(world, site.castleId())) {
                    nextSite = site;
                    nextSiteReadyForPlanning = GotCastles.isOverlaySiteReadyForPlanning(world, site);
                    break;
                }
                if (!GotCastles.isOverlaySiteReadyForPlanning(world, site)) continue;
                double siteDistanceSq = GotCastles.nearestActivePlayerDistanceSq(activePlayerPositions, site.anchor());
                if (nextSite != null && !(siteDistanceSq < nextSiteDistanceSq)) continue;
                nextSite = site;
                nextSiteDistanceSq = siteDistanceSq;
                nextSiteReadyForPlanning = true;
            }
            if (nextSite != null && System.nanoTime() < deadlineNanos) {
                PendingCastleGeneration newGeneration = new PendingCastleGeneration(nextSite.castleId(), nextSite.definitionId(), nextSite.index(), nextSite.anchor());
                newGeneration.setRoadLayoutVersion(state.layoutPinEra());
                state.putPendingGeneration(newGeneration);
                if (nextSiteReadyForPlanning && System.nanoTime() < deadlineNanos) {
                    operations += GotCastles.processPendingOverlayCastleGeneration(world, state, newGeneration, 1, deadlineNanos);
                }
            }
        }
        if (!GotCastles.hasRemainingOverlayCastleWork(state) && !state.overlayCastlesGenerated()) {
            state.setOverlayCastlesGenerated(true);
            GotCastles.reconcileHouseLordAssignments(world, true);
            GotMod.LOGGER.info("Finished generating {} overlay castles in {}", (Object)state.getAll().size(), (Object)world.method_27983().method_29177());
        } else if (operations > 0) {
            GotMod.LOGGER.debug("Processed {} overlay castle generation step(s) this tick in {}", (Object)operations, (Object)world.method_27983().method_29177());
        }
    }

    private static void processChunkNativeOverlayCastleClaims(class_3218 world, CastleWorldState state, @Nullable PendingCastleGeneration burstGeneration, List<class_243> activePlayerPositions) {
        GotCastles.updateForcedCastleGenerationChunks(world, state, burstGeneration, activePlayerPositions);
    }

    private static boolean hasRemainingOverlayCastleWork(CastleWorldState state) {
        return !state.getAllPendingGenerations().isEmpty();
    }

    private static boolean hasPriorityOverlayCastleWork(class_3218 world) {
        LinkedHashSet<String> prioritizedIds = PRIORITIZED_CASTLE_GENERATION_IDS.get(world.method_27983());
        return prioritizedIds != null && !prioritizedIds.isEmpty();
    }

    private static PendingGenerationBatchResult processPendingOverlayCastleGenerations(class_3218 world, CastleWorldState state, int budget, long deadlineNanos, boolean allowPlanning, List<class_243> activePlayerPositions) {
        PendingCastleGeneration nextGeneration;
        int processed;
        int consumed;
        if (budget <= 0) {
            return new PendingGenerationBatchResult(0, false);
        }
        boolean planned = false;
        for (processed = 0; processed < budget && System.nanoTime() < deadlineNanos && (nextGeneration = GotCastles.selectNextPendingOverlayCastleGeneration(world, state, allowPlanning && !planned, activePlayerPositions)) != null; processed += consumed) {
            boolean planning;
            boolean bl = planning = nextGeneration.stage() == PendingCastleGenerationStage.PLAN;
            if (planning && System.nanoTime() >= deadlineNanos) break;
            consumed = GotCastles.processPendingOverlayCastleGeneration(world, state, nextGeneration, budget - processed, deadlineNanos);
            planned |= planning;
            if (consumed <= 0) break;
            if (!planning) continue;
            break;
        }
        return new PendingGenerationBatchResult(processed, planned);
    }

    private static double nearestActivePlayerDistanceSq(class_3218 world, class_2338 pos) {
        return GotCastles.nearestActivePlayerDistanceSq(GotCastles.snapshotActivePlayerPositions(world), pos);
    }

    private static List<class_243> snapshotActivePlayerPositions(class_3218 world) {
        ArrayList<class_243> positions = new ArrayList<class_243>();
        for (class_3222 player : world.method_18456()) {
            if (player.method_7325()) continue;
            positions.add(player.method_19538());
        }
        return List.copyOf(positions);
    }

    private static double nearestActivePlayerDistanceSq(List<class_243> activePlayerPositions, class_2338 pos) {
        double nearestDistanceSq = Double.MAX_VALUE;
        class_243 center = class_243.method_24953((class_2382)pos);
        for (class_243 playerPos : activePlayerPositions) {
            double distanceSq = playerPos.method_1025(center);
            if (!(distanceSq < nearestDistanceSq)) continue;
            nearestDistanceSq = distanceSq;
        }
        return nearestDistanceSq;
    }

    @Nullable
    private static class_3222 nearestActivePlayer(class_3218 world, class_2338 pos) {
        class_3222 nearestPlayer = null;
        double nearestDistanceSq = Double.MAX_VALUE;
        for (class_3222 player : world.method_18456()) {
            double distanceSq;
            if (player.method_7325() || !((distanceSq = player.method_19538().method_1025(class_243.method_24953((class_2382)pos))) < nearestDistanceSq)) continue;
            nearestDistanceSq = distanceSq;
            nearestPlayer = player;
        }
        return nearestPlayer;
    }

    @Nullable
    private static PendingCastleGeneration findBurstPendingGeneration(class_3218 world, CastleWorldState state, List<class_243> activePlayerPositions) {
        PendingCastleGeneration prioritizedGeneration = GotCastles.findPriorityPendingGeneration(world, state, false, true, activePlayerPositions);
        if (prioritizedGeneration != null) {
            return prioritizedGeneration;
        }
        PendingCastleGeneration burstGeneration = null;
        double nearestDistanceSq = Double.MAX_VALUE;
        for (PendingCastleGeneration generation : state.getAllPendingGenerations()) {
            double distanceSq = GotCastles.generationInterestDistanceSq(activePlayerPositions, generation);
            if (distanceSq > 65536.0 || burstGeneration != null && !(distanceSq < nearestDistanceSq)) continue;
            burstGeneration = generation;
            nearestDistanceSq = distanceSq;
        }
        return burstGeneration;
    }

    private static boolean shouldUseBurstProcessing(class_3218 world, PendingCastleGeneration generation) {
        return GotCastles.shouldAcceleratePendingGeneration(GotCastles.isPriorityPendingGeneration(world, generation.castleId()), GotCastles.generationInterestDistanceSq(world, generation), 65536.0);
    }

    private static boolean shouldAcceleratePendingGeneration(boolean prioritized, double interestDistanceSq, double maxDistanceSq) {
        return prioritized || interestDistanceSq <= maxDistanceSq;
    }

    @Nullable
    private static PendingCastleGeneration selectNextPendingOverlayCastleGeneration(class_3218 world, CastleWorldState state, boolean allowPlanStage, List<class_243> activePlayerPositions) {
        PendingCastleGeneration prioritizedGeneration = GotCastles.findPriorityPendingGeneration(world, state, true, allowPlanStage, activePlayerPositions);
        if (prioritizedGeneration != null) {
            return prioritizedGeneration;
        }
        PendingCastleGeneration nextGeneration = null;
        double nearestDistanceSq = Double.MAX_VALUE;
        ArrayList<String> dormantPlanGenerations = new ArrayList<String>();
        double maxDormantDistanceSq = 262144.0;
        for (PendingCastleGeneration generation : state.getAllPendingGenerations()) {
            if (generation.stage() == PendingCastleGenerationStage.PLAN && !allowPlanStage) continue;
            if (!GotCastles.isPendingGenerationAreaLoaded(world, generation, activePlayerPositions)) {
                if (GotCastles.shouldRemoveDormantPlan(generation, GotCastles.isPriorityPendingGeneration(world, generation.castleId()), GotCastles.generationInterestDistanceSq(activePlayerPositions, generation), maxDormantDistanceSq)) {
                    dormantPlanGenerations.add(generation.castleId());
                    continue;
                }
                GotCastles.logBlockedPendingGeneration(world, generation);
                continue;
            }
            double distanceSq = GotCastles.nearestActivePlayerDistanceSq(activePlayerPositions, GotCastles.pendingGenerationActivityCenter(generation));
            if (nextGeneration != null && !(distanceSq < nearestDistanceSq)) continue;
            nextGeneration = generation;
            nearestDistanceSq = distanceSq;
        }
        for (String castleId : dormantPlanGenerations) {
            GotCastles.removePendingCastleGeneration(world, state, castleId);
        }
        return nextGeneration;
    }

    private static boolean shouldRemoveDormantPlan(PendingCastleGeneration generation, boolean prioritized, double interestDistanceSq, double maxDormantDistanceSq) {
        return generation != null && generation.stage() == PendingCastleGenerationStage.PLAN && !prioritized && interestDistanceSq > maxDormantDistanceSq;
    }

    @Nullable
    private static PendingCastleGeneration findPriorityPendingGeneration(class_3218 world, CastleWorldState state, boolean requireLoadedArea, boolean allowPlanStage, List<class_243> activePlayerPositions) {
        LinkedHashSet<String> prioritizedIds = PRIORITIZED_CASTLE_GENERATION_IDS.get(world.method_27983());
        if (prioritizedIds == null || prioritizedIds.isEmpty()) {
            return null;
        }
        ArrayList<String> staleIds = new ArrayList<String>();
        for (String castleId : prioritizedIds) {
            if (castleId == null || castleId.isBlank()) {
                staleIds.add(castleId);
                continue;
            }
            if (state.contains(castleId) && !state.hasPendingGeneration(castleId)) {
                staleIds.add(castleId);
                continue;
            }
            PendingCastleGeneration generation = state.getPendingGeneration(castleId);
            if (generation == null) {
                staleIds.add(castleId);
                continue;
            }
            if (generation.stage() == PendingCastleGenerationStage.PLAN && !allowPlanStage) continue;
            if (requireLoadedArea && !GotCastles.isPendingGenerationAreaLoaded(world, generation, activePlayerPositions)) {
                GotCastles.logBlockedPendingGeneration(world, generation);
                continue;
            }
            if (!staleIds.isEmpty()) {
                staleIds.forEach(id -> GotCastles.clearPriorityCastleGeneration(world, id));
            }
            return generation;
        }
        if (!staleIds.isEmpty()) {
            staleIds.forEach(id -> GotCastles.clearPriorityCastleGeneration(world, id));
        }
        return null;
    }

    private static boolean isPriorityPendingGeneration(class_3218 world, String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return false;
        }
        LinkedHashSet<String> prioritizedIds = PRIORITIZED_CASTLE_GENERATION_IDS.get(world.method_27983());
        return prioritizedIds != null && prioritizedIds.contains(castleId);
    }

    private static double generationInterestDistanceSq(class_3218 world, PendingCastleGeneration generation) {
        return GotCastles.generationInterestDistanceSq(GotCastles.snapshotActivePlayerPositions(world), generation);
    }

    private static double generationInterestDistanceSq(List<class_243> activePlayerPositions, PendingCastleGeneration generation) {
        return Math.min(GotCastles.nearestActivePlayerDistanceSq(activePlayerPositions, generation.anchor()), GotCastles.nearestActivePlayerDistanceSq(activePlayerPositions, GotCastles.pendingGenerationActivityCenter(generation)));
    }

    private static int processPendingOverlayCastleGeneration(class_3218 world, CastleWorldState state, PendingCastleGeneration pendingGeneration, int budget, long deadlineNanos) {
        if (pendingGeneration.stage() != PendingCastleGenerationStage.PLAN && !GotCastles.isPendingLayoutContractCurrent(world, state, pendingGeneration)) {
            GotCastles.logPendingLayoutContractBlocked(world, state, pendingGeneration);
            return 0;
        }
        OverlayCastleSite site = GotCastles.findOverlaySite(pendingGeneration.castleId());
        if (site == null) {
            GotCastles.removePendingCastleGeneration(world, state, pendingGeneration.castleId());
            return 1;
        }
        return switch (pendingGeneration.stage()) {
            default -> throw new MatchException(null, null);
            case PendingCastleGenerationStage.PLAN -> {
                if (GotCastles.processPendingCastlePlanStage(world, state, site, pendingGeneration)) {
                    yield 1;
                }
                yield 0;
            }
            case PendingCastleGenerationStage.CORE -> GotCastles.processPendingCastleCoreStage(world, state, site, pendingGeneration, budget, deadlineNanos);
            case PendingCastleGenerationStage.STRUCTURES -> GotCastles.processPendingCastleStructureStage(world, state, pendingGeneration, budget, deadlineNanos);
            case PendingCastleGenerationStage.INFRASTRUCTURE -> GotCastles.processPendingCastleInfrastructureStage(world, state, pendingGeneration, budget, deadlineNanos);
            case PendingCastleGenerationStage.ROADS -> GotCastles.processPendingCastleRoadStage(world, state, pendingGeneration, budget, deadlineNanos);
            case PendingCastleGenerationStage.FINALIZE -> GotCastles.processPendingCastleFinalizeStage(world, state, site, pendingGeneration) ? 1 : 0;
        };
    }

    private static boolean processPendingCastlePlanStage(class_3218 world, CastleWorldState state, OverlayCastleSite site, PendingCastleGeneration pendingGeneration) {
        class_2338 placementAnchor = GotCastles.resolvePlacementAnchor(world, site);
        Optional<CastlePlacementPlan> optionalPlan = GotCastles.planPlacement(world, site.definitionId(), placementAnchor, GotCastles.isInferredHarbourSite(site));
        if (optionalPlan.isEmpty()) {
            GotMod.LOGGER.warn("Skipping castle overlay marker at {} because no valid placement plan was found", (Object)site.anchor().method_23854());
            GotCastles.removePendingCastleGeneration(world, state, site.castleId());
            state.markRejected(site.castleId());
            return true;
        }
        CastlePlacementPlan plan = optionalPlan.get();
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(site.castleId());
        CastleTier effectiveTier = GotCastles.resolveEffectiveCastleTier(metadata, plan.tier());
        CastleTemplateKey templateKey = effectiveTier == plan.tier() ? plan.templateKey() : new CastleTemplateKey(effectiveTier, plan.templateKey().woodType());
        DynamicStoneCastleTierConfig dynamicConfig = GotCastles.dynamicStoneCastleConfig(effectiveTier);
        Kingdom kingdom = GotCastles.resolveKingdomForBiome(world, placementAnchor);
        String kingdomId = metadata != null && !metadata.kingdomId().isBlank() ? metadata.kingdomId() : (kingdom != null ? kingdom.getId().toString() : "");
        String houseId = GotCastles.resolveOwnedHouseId("", kingdomId, metadata != null ? metadata.houseId() : "");
        String religionId = metadata != null && !metadata.religionId().isBlank() ? metadata.religionId() : GotCastles.defaultReligionIdForKingdom(kingdomId);
        String lordName = GotCastles.resolvePendingLordName(world, site, metadata, kingdomId);
        PendingCastleOwnershipWorldState.PendingCastleOwnership pendingOwnership = PendingCastleOwnershipWorldState.get(world).get(site.castleId());
        if (pendingOwnership != null) {
            if (!pendingOwnership.ownerKingdomId().isBlank()) {
                kingdomId = pendingOwnership.ownerKingdomId();
            }
            houseId = GotCastles.resolveOwnedHouseId(pendingOwnership.ownerHouseId(), kingdomId, metadata != null ? metadata.houseId() : "");
            if (!pendingOwnership.ownerReligionId().isBlank()) {
                religionId = pendingOwnership.ownerReligionId();
            }
            if (!pendingOwnership.lordName().isBlank()) {
                lordName = pendingOwnership.lordName();
            }
            if (!pendingOwnership.ownerPlayerUuid().isBlank()) {
                pendingGeneration.setOwnerPlayerUuid(GotCastles.parseUuid(pendingOwnership.ownerPlayerUuid()));
            }
        }
        pendingGeneration.setTier(effectiveTier);
        pendingGeneration.setDynamic(dynamicConfig != null);
        pendingGeneration.setTemplateId((dynamicConfig != null ? dynamicConfig.templateId() : templateKey.structureId()).toString());
        pendingGeneration.setPlanOrigin(plan.origin());
        pendingGeneration.setFloorY(plan.floorY());
        pendingGeneration.setFootprintBlocks(plan.footprintBlocks());
        pendingGeneration.setBlendRadius(plan.blendRadius());
        pendingGeneration.setKingdomId(kingdomId);
        pendingGeneration.setHouseId(houseId);
        pendingGeneration.setReligionId(religionId);
        pendingGeneration.setLordName(lordName);
        pendingGeneration.setSubtypes(new ArrayList<CastleSubtype>(GotCastles.resolveSubtypes(site, metadata)));
        pendingGeneration.setNorthSouthGates((site.castleId().hashCode() >>> 1 & 1) == 0);
        if (!GotCastles.initializePendingRoadLayout(world, state, pendingGeneration)) {
            GotCastles.logPendingLayoutContractBlocked(world, state, pendingGeneration);
            state.putPendingGeneration(pendingGeneration);
            return true;
        }
        if (GotCastles.tryAdoptWorldgenCastleShell(world, state, pendingGeneration)) {
            state.putPendingGeneration(pendingGeneration);
            return true;
        }
        pendingGeneration.setStage(PendingCastleGenerationStage.CORE);
        state.putPendingGeneration(pendingGeneration);
        return true;
    }

    private static boolean tryAdoptWorldgenCastleShell(class_3218 world, CastleWorldState state, PendingCastleGeneration pendingGeneration) {
        if (world == null || pendingGeneration == null || !pendingGeneration.dynamic()) {
            return false;
        }
        Optional<CastleWorldgenPlan> cachedPlan = CastlePlanCache.get(world, pendingGeneration.castleId());
        if (cachedPlan.isEmpty() || cachedPlan.get().corePlan() == null || cachedPlan.get().dynamicFootprint() == null) {
            return false;
        }
        Optional<class_2338> existingBannerPos = DynamicCastleCoreApplier.findExistingBanner(world, cachedPlan.get().corePlan());
        if (existingBannerPos.isEmpty()) {
            return false;
        }
        String currentFingerprint = CastleRoadNetworkFingerprint.compute(cachedPlan.get());
        if (!GotCastles.matchesPendingLayoutContract(pendingGeneration, state.getLayoutPin(pendingGeneration.castleId()), currentFingerprint)) {
            return false;
        }
        if (pendingGeneration.roadLayoutVersion() == 2) {
            pendingGeneration.setVerifiedRoadPlan(cachedPlan.get().roadNetworkPlan());
        }
        pendingGeneration.setStructureOrigin(cachedPlan.get().dynamicFootprint().origin());
        pendingGeneration.setStructureSize(cachedPlan.get().dynamicFootprint().size());
        pendingGeneration.setBannerPos(existingBannerPos.get());
        pendingGeneration.setWorldgenShellPlaced(true);
        pendingGeneration.setCorePhase(0);
        pendingGeneration.setCoreCursor(0);
        pendingGeneration.clearDynamicCoreEdgeHeightCache();
        pendingGeneration.setStage(PendingCastleGenerationStage.STRUCTURES);
        return true;
    }

    private static boolean initializePendingRoadLayout(class_3218 world, CastleWorldState state, PendingCastleGeneration pendingGeneration) {
        CastleWorldState.CastleLayoutPin existingPin = state.getLayoutPin(pendingGeneration.castleId());
        if (existingPin != null && existingPin.version() != 2) {
            if (pendingGeneration.stage() != PendingCastleGenerationStage.PLAN && pendingGeneration.roadLayoutVersion() != existingPin.version()) {
                return false;
            }
            GotCastles.pinLegacyPendingRoadLayout(pendingGeneration);
            return true;
        }
        CastleWorldgenPlan plan = CastlePlanCache.get(world, pendingGeneration.castleId()).orElse(null);
        if (existingPin != null) {
            if (!(pendingGeneration.stage() == PendingCastleGenerationStage.PLAN || pendingGeneration.roadLayoutVersion() == existingPin.version() && pendingGeneration.roadPlanFingerprint().equals(existingPin.fullPlanFingerprint()))) {
                return false;
            }
            pendingGeneration.setRoadLayoutVersion(2);
            pendingGeneration.setRoadPlanFingerprint(existingPin.fullPlanFingerprint());
            if (!GotCastles.isUsableV2WorldgenPlan(plan)) {
                return false;
            }
            String currentFingerprint = CastleRoadNetworkFingerprint.compute(plan);
            if (!GotCastles.matchesPendingLayoutContract(pendingGeneration, existingPin, currentFingerprint)) {
                return false;
            }
            GotCastles.applyPendingV2PlanContract(pendingGeneration, plan, false);
            return true;
        }
        if (pendingGeneration.stage() != PendingCastleGenerationStage.PLAN) {
            return false;
        }
        if (pendingGeneration.roadLayoutVersion() != 2) {
            state.pinLayoutIfAbsent(pendingGeneration.castleId(), 1, "");
            GotCastles.pinLegacyPendingRoadLayout(pendingGeneration);
            return true;
        }
        if (!GotCastles.isUsableV2WorldgenPlan(plan)) {
            if (pendingGeneration.stage() == PendingCastleGenerationStage.PLAN) {
                GotCastles.pinLegacyPendingRoadLayout(pendingGeneration);
                state.pinLayoutIfAbsent(pendingGeneration.castleId(), 1, "");
                return true;
            }
            return false;
        }
        String fullPlanFingerprint = CastleRoadNetworkFingerprint.compute(plan);
        CastleWorldState.CastleLayoutPin pin = state.pinLayoutIfAbsent(pendingGeneration.castleId(), 2, fullPlanFingerprint);
        if (pin == null) {
            return false;
        }
        pendingGeneration.setRoadLayoutVersion(pin.version());
        pendingGeneration.setRoadPlanFingerprint(pin.fullPlanFingerprint());
        if (!GotCastles.matchesPendingLayoutContract(pendingGeneration, pin, fullPlanFingerprint)) {
            return false;
        }
        GotCastles.applyPendingV2PlanContract(pendingGeneration, plan, true);
        return true;
    }

    private static boolean isUsableV2WorldgenPlan(@Nullable CastleWorldgenPlan plan) {
        CastleRoadNetworkPlan roadPlan = plan == null ? null : plan.roadNetworkPlan();
        return plan != null && plan.dynamicFootprint() != null && roadPlan != null && roadPlan.version() == 2 && !roadPlan.cellsByChunk().isEmpty() && !roadPlan.gatePortals().isEmpty();
    }

    private static void applyPendingV2PlanContract(PendingCastleGeneration pendingGeneration, CastleWorldgenPlan plan, boolean resetRoadCursor) {
        CastlePlacementPlan placementPlan = plan.placementPlan();
        pendingGeneration.setTier(plan.effectiveTier());
        pendingGeneration.setPlanOrigin(placementPlan.origin());
        pendingGeneration.setFloorY(placementPlan.floorY());
        pendingGeneration.setFootprintBlocks(placementPlan.footprintBlocks());
        pendingGeneration.setBlendRadius(placementPlan.blendRadius());
        pendingGeneration.setStructureOrigin(plan.dynamicFootprint().origin());
        pendingGeneration.setStructureSize(plan.dynamicFootprint().size());
        pendingGeneration.setVerifiedRoadPlan(plan.roadNetworkPlan());
        if (resetRoadCursor) {
            pendingGeneration.setNextRoadChunkCursor(0);
            pendingGeneration.setNextRoadCellCursor(0);
        }
    }

    private static void pinLegacyPendingRoadLayout(PendingCastleGeneration pendingGeneration) {
        pendingGeneration.setRoadLayoutVersion(1);
        pendingGeneration.setRoadPlanFingerprint("");
        pendingGeneration.setNextRoadChunkCursor(0);
        pendingGeneration.setNextRoadCellCursor(0);
    }

    @Nullable
    private static CastleWorldgenPlan resolvePinnedPendingRoadPlan(class_3218 world, PendingCastleGeneration pendingGeneration) {
        String currentFingerprint;
        if (world == null || pendingGeneration == null) {
            return null;
        }
        CastleWorldgenPlan plan = CastlePlanCache.get(world, pendingGeneration.castleId()).orElse(null);
        if (!GotCastles.isUsableV2WorldgenPlan(plan)) {
            return null;
        }
        CastleWorldState.CastleLayoutPin durablePin = CastleWorldState.get(world).getLayoutPin(pendingGeneration.castleId());
        if (!GotCastles.matchesPendingLayoutContract(pendingGeneration, durablePin, currentFingerprint = CastleRoadNetworkFingerprint.compute(plan))) {
            return null;
        }
        pendingGeneration.setVerifiedRoadPlan(plan.roadNetworkPlan());
        return plan;
    }

    private static boolean isPendingLayoutContractCurrent(class_3218 world, CastleWorldState state, PendingCastleGeneration pendingGeneration) {
        CastleWorldState.CastleLayoutPin durablePin = state.getLayoutPin(pendingGeneration.castleId());
        if (durablePin == null || pendingGeneration.roadLayoutVersion() != durablePin.version()) {
            return false;
        }
        if (durablePin.version() != 2) {
            return GotCastles.matchesPendingLayoutContract(pendingGeneration, durablePin, "");
        }
        CastleWorldgenPlan plan = CastlePlanCache.get(world, pendingGeneration.castleId()).orElse(null);
        return GotCastles.isUsableV2WorldgenPlan(plan) && GotCastles.matchesPendingLayoutContract(pendingGeneration, durablePin, CastleRoadNetworkFingerprint.compute(plan));
    }

    private static boolean matchesPendingLayoutContract(PendingCastleGeneration pendingGeneration, @Nullable CastleWorldState.CastleLayoutPin durablePin, String currentFingerprint) {
        if (pendingGeneration == null || durablePin == null || pendingGeneration.roadLayoutVersion() != durablePin.version()) {
            return false;
        }
        if (durablePin.version() != 2) {
            return true;
        }
        return !durablePin.fullPlanFingerprint().isBlank() && durablePin.fullPlanFingerprint().equals(pendingGeneration.roadPlanFingerprint()) && durablePin.fullPlanFingerprint().equals(currentFingerprint);
    }

    private static void logPendingLayoutContractBlocked(class_3218 world, CastleWorldState state, PendingCastleGeneration pendingGeneration) {
        long currentTick = world.method_8510();
        if (pendingGeneration.lastBlockedLogTick() != Long.MIN_VALUE && currentTick >= pendingGeneration.lastBlockedLogTick() && currentTick - pendingGeneration.lastBlockedLogTick() < 100L) {
            return;
        }
        GotMod.LOGGER.error("Paused {} stage for castle {} because its pending and durable layout contracts do not exactly match; refusing to mix layouts", (Object)pendingGeneration.stage(), (Object)pendingGeneration.castleId());
        pendingGeneration.setLastBlockedLogTick(currentTick);
        state.putPendingGeneration(pendingGeneration);
    }

    private static int processPendingCastleCoreStage(class_3218 world, CastleWorldState state, OverlayCastleSite site, PendingCastleGeneration pendingGeneration, int budget, long deadlineNanos) {
        int processed;
        if (pendingGeneration.planOrigin().equals((Object)class_2338.field_10980) && pendingGeneration.floorY() == 0 && pendingGeneration.footprintBlocks() == 0) {
            class_2338 placementAnchor = GotCastles.resolvePlacementAnchor(world, site);
            Optional<CastlePlacementPlan> optionalPlan = GotCastles.planPlacement(world, site.definitionId(), placementAnchor, GotCastles.isInferredHarbourSite(site));
            if (optionalPlan.isEmpty()) {
                GotCastles.removePendingCastleGeneration(world, state, site.castleId());
                state.markRejected(site.castleId());
                return 1;
            }
            CastlePlacementPlan plan = optionalPlan.get();
            pendingGeneration.setPlanOrigin(plan.origin());
            pendingGeneration.setFloorY(plan.floorY());
            pendingGeneration.setFootprintBlocks(plan.footprintBlocks());
            pendingGeneration.setBlendRadius(plan.blendRadius());
        }
        DynamicStoneCastleTierConfig dynamicConfig = GotCastles.dynamicStoneCastleConfig(pendingGeneration.tier());
        int coreBatchLimit = GotCastles.shouldUseBurstProcessing(world, pendingGeneration) ? 24 : 8;
        int maxBatch = Math.max(1, Math.min(budget, coreBatchLimit));
        if (dynamicConfig != null) {
            for (processed = 0; processed < maxBatch && System.nanoTime() < deadlineNanos; ++processed) {
                CoreStageProgress progress = GotCastles.progressDynamicCastleCore(world, site.castleId(), pendingGeneration, dynamicConfig);
                if (!progress.hasFailed()) continue;
                GotCastles.removePendingCastleGeneration(world, state, site.castleId());
                state.markRejected(site.castleId());
                return Math.max(1, processed);
            }
            state.putPendingGeneration(pendingGeneration);
            return processed;
        }
        class_3499 template = world.method_14183().method_15094(class_2960.method_60654((String)pendingGeneration.templateId())).orElse(null);
        if (template == null) {
            GotCastles.removePendingCastleGeneration(world, state, site.castleId());
            state.markRejected(site.castleId());
            return 1;
        }
        class_2338 structureOrigin = GotCastles.alignPlanOrigin(pendingGeneration.planOrigin(), pendingGeneration.floorY(), pendingGeneration.footprintBlocks(), template);
        class_2382 structureSize = template.method_15160();
        while (processed < maxBatch && System.nanoTime() < deadlineNanos) {
            CoreStageProgress progress = GotCastles.progressStaticCastleCore(world, pendingGeneration, structureOrigin, structureSize, template);
            if (progress.hasFailed()) {
                GotCastles.removePendingCastleGeneration(world, state, site.castleId());
                state.markRejected(site.castleId());
                return Math.max(1, processed);
            }
            ++processed;
            if (!progress.isCompleted()) continue;
            pendingGeneration.setStage(PendingCastleGenerationStage.STRUCTURES);
            state.putPendingGeneration(pendingGeneration);
            return processed;
        }
        state.putPendingGeneration(pendingGeneration);
        return processed;
    }

    private static int processPendingCastleStructureStage(class_3218 world, CastleWorldState state, PendingCastleGeneration pendingGeneration, int budget, long deadlineNanos) {
        int processed;
        if (pendingGeneration.roadLayoutVersion() == 2) {
            return GotCastles.processPendingV2CastleStructureStage(world, state, pendingGeneration, budget, deadlineNanos);
        }
        CastleCityTierPlan cityTierPlan = CastleCityTierRegistry.forTier(pendingGeneration.tier());
        List<CastleStructureResidentPlan> plannedStructures = GotCastles.cachedPendingStructurePlans(pendingGeneration, cityTierPlan);
        if (plannedStructures.isEmpty()) {
            pendingGeneration.setStage(PendingCastleGenerationStage.INFRASTRUCTURE);
            state.putPendingGeneration(pendingGeneration);
            return 1;
        }
        if (pendingGeneration.nextStructureIndex() >= plannedStructures.size()) {
            pendingGeneration.setStage(PendingCastleGenerationStage.INFRASTRUCTURE);
            state.putPendingGeneration(pendingGeneration);
            return 1;
        }
        int structureBatchLimit = GotCastles.shouldUseBurstProcessing(world, pendingGeneration) ? 16 : 8;
        int maxBatch = Math.min(budget, structureBatchLimit);
        for (processed = 0; pendingGeneration.nextStructureIndex() < plannedStructures.size() && processed < maxBatch && System.nanoTime() < deadlineNanos; ++processed) {
            PlacedCastleStructure placedStructure;
            PlacedCastleStructure placedCastleStructure = placedStructure = pendingGeneration.dynamic() ? GotCastles.placeNextPendingDynamicStructure(world, pendingGeneration, plannedStructures) : GotCastles.placeNextPendingClaimStructure(world, pendingGeneration, plannedStructures, cityTierPlan.claimRadiusChunks());
            if (placedStructure != null) {
                pendingGeneration.addPlacedStructure(GotCastles.toPendingPlacedStructureState(placedStructure));
            }
            pendingGeneration.setNextStructureIndex(pendingGeneration.nextStructureIndex() + 1);
        }
        if (pendingGeneration.nextStructureIndex() >= plannedStructures.size()) {
            pendingGeneration.setStage(PendingCastleGenerationStage.INFRASTRUCTURE);
        }
        state.putPendingGeneration(pendingGeneration);
        return processed;
    }

    private static int processPendingV2CastleStructureStage(class_3218 world, CastleWorldState state, PendingCastleGeneration pendingGeneration, int budget, long deadlineNanos) {
        if (budget <= 0 || System.nanoTime() >= deadlineNanos) {
            return 0;
        }
        CastleWorldgenPlan plan = GotCastles.resolvePinnedPendingRoadPlan(world, pendingGeneration);
        if (plan == null) {
            GotCastles.logPendingLayoutContractBlocked(world, state, pendingGeneration);
            return 0;
        }
        List<CastleWorldgenStructurePlan> structures = plan.structures();
        if (pendingGeneration.nextStructureIndex() >= structures.size()) {
            pendingGeneration.setStage(PendingCastleGenerationStage.INFRASTRUCTURE);
            state.putPendingGeneration(pendingGeneration);
            return 1;
        }
        CastleWorldgenStructurePlan structurePlan = structures.get(pendingGeneration.nextStructureIndex());
        CastleWorldgenStructureApplier.applyFull(world, structurePlan);
        if (structurePlan.residentPlan() != null) {
            pendingGeneration.addPlacedStructure(GotCastles.toPendingPlacedStructureState(structurePlan));
        }
        pendingGeneration.setNextStructureIndex(pendingGeneration.nextStructureIndex() + 1);
        if (pendingGeneration.nextStructureIndex() >= structures.size()) {
            pendingGeneration.setStage(PendingCastleGenerationStage.INFRASTRUCTURE);
        }
        state.putPendingGeneration(pendingGeneration);
        return 1;
    }

    private static CoreStageProgress progressDynamicCastleCore(class_3218 world, String castleId, PendingCastleGeneration pendingGeneration, DynamicStoneCastleTierConfig config) {
        if (!GotCastles.ensureDynamicCastleCoreCache(world, castleId, pendingGeneration, config)) {
            return CoreStageProgress.error();
        }
        class_2338 footprintOrigin = pendingGeneration.structureOrigin();
        class_2382 footprintSize = pendingGeneration.structureSize();
        int floorBlockY = pendingGeneration.floorY() + 1;
        if (pendingGeneration.corePhase() == 0) {
            if (GotCastles.prepareCastlePadBatch(world, footprintOrigin, footprintSize, pendingGeneration.floorY(), 0, pendingGeneration, null, GotCastles.usesSouthOnlyCastleGeneration(castleId))) {
                pendingGeneration.setCorePhase(1);
                pendingGeneration.setCoreCursor(0);
            }
            return CoreStageProgress.inProgress();
        }
        if (pendingGeneration.corePhase() == 1) {
            if (GotCastles.flattenDynamicCastleClaimSurfaceBatch(world, GotCastles.expandDynamicFlattenOrigin(footprintOrigin), GotCastles.expandDynamicFlattenSize(footprintSize), pendingGeneration.floorY(), pendingGeneration)) {
                pendingGeneration.setCorePhase(2);
                pendingGeneration.setCoreCursor(0);
            }
            return CoreStageProgress.inProgress();
        }
        if (pendingGeneration.corePhase() == 2) {
            if (GotCastles.mergeCastlePlatformBoundaryBatch(world, footprintOrigin, footprintSize, pendingGeneration.floorY(), DynamicCastleTerrainApplier.terrainMergeRadius(pendingGeneration.blendRadius()), pendingGeneration, GotCastles.usesSouthOnlyCastleGeneration(castleId))) {
                pendingGeneration.setCorePhase(3);
                pendingGeneration.setCoreCursor(0);
            }
            return CoreStageProgress.inProgress();
        }
        if (pendingGeneration.corePhase() == 3) {
            if (GotCastles.clearBuildVolumeBatch(world, footprintOrigin, footprintSize, floorBlockY, pendingGeneration.dynamicCoreClearanceHeight(), pendingGeneration)) {
                pendingGeneration.setCorePhase(4);
                pendingGeneration.setCoreCursor(0);
            }
            return CoreStageProgress.inProgress();
        }
        CastlePlacementPlan plan = new CastlePlacementPlan(castleId, pendingGeneration.tier(), null, pendingGeneration.planOrigin(), pendingGeneration.floorY(), pendingGeneration.floorY(), pendingGeneration.floorY(), Math.max(1, GotCastles.ceilDiv(pendingGeneration.footprintBlocks(), 16)), pendingGeneration.footprintBlocks(), pendingGeneration.blendRadius());
        Optional<DynamicCastleCoreWorldgenPlan> corePlan = GotCastles.resolveDynamicCoreWorldgenPlan(world, castleId, plan, pendingGeneration.tier());
        if (corePlan.isEmpty()) {
            GotMod.LOGGER.warn("Missing dynamic core worldgen plan for {}", (Object)castleId);
            return CoreStageProgress.error();
        }
        Optional<class_2338> existingBannerPos = DynamicCastleCoreApplier.findExistingBanner(world, corePlan.get());
        if (existingBannerPos.isPresent()) {
            pendingGeneration.setBannerPos(existingBannerPos.get());
            pendingGeneration.setWorldgenShellPlaced(true);
        } else {
            Optional<class_2338> placedBannerPos = DynamicCastleCoreApplier.applyFull(world, corePlan.get());
            if (placedBannerPos.isEmpty()) {
                GotMod.LOGGER.warn("Failed to place dynamic castle core for {}", (Object)castleId);
                return CoreStageProgress.error();
            }
            pendingGeneration.setBannerPos(placedBannerPos.get());
        }
        pendingGeneration.setCorePhase(0);
        pendingGeneration.setCoreCursor(0);
        pendingGeneration.clearDynamicCoreEdgeHeightCache();
        return CoreStageProgress.done();
    }

    private static CoreStageProgress progressStaticCastleCore(class_3218 world, PendingCastleGeneration pendingGeneration, class_2338 structureOrigin, class_2382 structureSize, class_3499 template) {
        pendingGeneration.setStructureOrigin(structureOrigin);
        pendingGeneration.setStructureSize(structureSize);
        if (pendingGeneration.corePhase() == 0) {
            if (!GotCastles.prepareCastlePadBatch(world, structureOrigin, structureSize, pendingGeneration.floorY(), pendingGeneration.blendRadius(), pendingGeneration, null, GotCastles.usesSouthOnlyCastleGeneration(pendingGeneration.castleId()))) {
                return CoreStageProgress.inProgress();
            }
            pendingGeneration.setCorePhase(1);
            pendingGeneration.setCoreCursor(0);
            return CoreStageProgress.inProgress();
        }
        class_3492 placement = new class_3492().method_15133(false).method_15131(true).method_27264(false);
        if (!template.method_15172((class_5425)world, structureOrigin, structureOrigin, placement, world.field_9229, 3)) {
            return CoreStageProgress.error();
        }
        pendingGeneration.setBannerPos(GotCastles.findClaimBanner(world, structureOrigin, structureSize).orElse(structureOrigin));
        pendingGeneration.setCorePhase(0);
        pendingGeneration.setCoreCursor(0);
        return CoreStageProgress.done();
    }

    private static int processPendingCastleInfrastructureStage(class_3218 world, CastleWorldState state, PendingCastleGeneration pendingGeneration, int budget, long deadlineNanos) {
        CastleWorldgenPlan pinnedPlan;
        boolean v2RoadLayout = pendingGeneration.roadLayoutVersion() == 2;
        CastleWorldgenPlan castleWorldgenPlan = pinnedPlan = v2RoadLayout ? GotCastles.resolvePinnedPendingRoadPlan(world, pendingGeneration) : null;
        if (v2RoadLayout && pinnedPlan == null) {
            GotCastles.logPendingLayoutContractBlocked(world, state, pendingGeneration);
            return 0;
        }
        if (v2RoadLayout) {
            return GotCastles.processPendingV2CastleInfrastructureStage(world, state, pendingGeneration, pinnedPlan, budget, deadlineNanos);
        }
        if (pendingGeneration.dynamic()) {
            DynamicStoneWallPlan wallPlan;
            List<class_1923> buildingChunks;
            CastleCityTierPlan tierPlan = CastleCityTierRegistry.forTier(pendingGeneration.tier());
            List<class_1923> list = buildingChunks = v2RoadLayout ? pinnedPlan.buildingChunks() : GotCastles.collectDynamicWallStructureChunksFromPending(pendingGeneration);
            if (buildingChunks.isEmpty()) {
                CastleWorldgenPlan cachedPlan = pendingGeneration.worldgenShellPlaced() ? (CastleWorldgenPlan)CastlePlanCache.get(world, pendingGeneration.castleId()).orElse(null) : null;
                buildingChunks = cachedPlan != null ? cachedPlan.buildingChunks() : GotCastles.cachedPendingDynamicBuildingChunks(world, pendingGeneration, tierPlan);
            }
            class_3341 coreReservedFootprint = GotCastles.dynamicStoneCoreReservedFootprint(world, world.method_14183(), pendingGeneration.castleId(), pendingGeneration.bannerPos(), pendingGeneration.structureOrigin(), pendingGeneration.structureSize());
            List<class_1923> anchorChunks = GotCastles.collectDynamicCastleAnchorChunks(coreReservedFootprint, buildingChunks);
            DynamicStoneWallPlan dynamicStoneWallPlan = v2RoadLayout ? GotCastles.toPendingDynamicStoneWallPlan(pinnedPlan.wallPlan()) : (wallPlan = GotCastles.createDynamicStoneWallPlan(world, pendingGeneration.bannerPos(), tierPlan, anchorChunks, pendingGeneration.northSouthGates(), GotCastles.isOriginalNightsWatchCastle(pendingGeneration.kingdomId()) ? 1 : 0));
            if (!v2RoadLayout) {
                GotCastles.placeDynamicCastleRoads(world, pendingGeneration.bannerPos(), anchorChunks, wallPlan);
            }
            pendingGeneration.setWallGateClaims(List.of());
            if (GotCastles.shouldGenerateDynamicCastleWalls(pendingGeneration.kingdomId())) {
                ArrayList<WallGateClaim> wallGateClaims = new ArrayList<WallGateClaim>();
                if (!GotCastles.placeDynamicStoneWallPlan(world, world.method_14183(), wallPlan, pendingGeneration.structureOrigin().method_10264() + 1, pendingGeneration.kingdomId(), wallGateClaims)) {
                    GotMod.LOGGER.warn("Failed to place dynamic stone castle wall outline for {}", (Object)pendingGeneration.castleId());
                }
                pendingGeneration.setWallGateClaims(wallGateClaims);
            }
            if (!v2RoadLayout && !GotCastles.pendingGenerationHasPlacedRole(pendingGeneration, KingdomNpcRole.CART_RIDER)) {
                GotCastles.placeDedicatedBarnStructure(world, world.method_14183(), pendingGeneration.castleId(), pendingGeneration.bannerPos(), pendingGeneration.kingdomId(), tierPlan, wallPlan, pendingGeneration.floorY()).ifPresent(placedStructure -> pendingGeneration.addPlacedStructure(GotCastles.toPendingPlacedStructureState(placedStructure)));
            }
            if (!v2RoadLayout && pendingGeneration.subtypes().contains((Object)CastleSubtype.HARBOUR) && !GotCastles.pendingGenerationHasPlacedRole(pendingGeneration, KingdomNpcRole.FISHERMAN)) {
                List<class_1923> harbourChunks = GotCastles.collectDynamicHarbourStructureChunks(pendingGeneration.bannerPos(), wallPlan, tierPlan.claimRadiusChunks());
                class_5819 random = class_5819.method_43049((long)((pendingGeneration.bannerPos().method_10063() ^ (long)pendingGeneration.castleId().hashCode()) + 17L + (long)pendingGeneration.tier().ordinal() * 31L));
                Collections.shuffle(harbourChunks, new Random(random.method_43055()));
                GotCastles.placeDedicatedHarbourStructure(world, world.method_14183(), pendingGeneration.castleId(), pendingGeneration.kingdomId(), pendingGeneration.bannerPos(), harbourChunks, new ChunkSetArea(wallPlan.interiorChunkKeys()), tierPlan.claimRadiusChunks(), pendingGeneration.floorY()).ifPresent(placedStructure -> pendingGeneration.addPlacedStructure(GotCastles.toPendingPlacedStructureState(placedStructure)));
            }
            if (v2RoadLayout) {
                CastleWorldgenStructureApplier.applyNatureFull(world, pinnedPlan);
            } else {
                ArrayList<class_3341> reservedNatureFootprints = new ArrayList<class_3341>(GotCastles.dynamicStoneReservedFootprints(coreReservedFootprint));
                for (PendingCastleGeneration.PlacedStructureState placedStructure2 : pendingGeneration.placedStructures()) {
                    reservedNatureFootprints.add(placedStructure2.footprint());
                }
                class_5819 natureRandom = class_5819.method_43049((long)((pendingGeneration.bannerPos().method_10063() ^ (long)pendingGeneration.castleId().hashCode()) + 91L + (long)pendingGeneration.tier().ordinal() * 43L));
                GotCastles.placeDynamicCastleNatureFillers(world, world.method_14183(), pendingGeneration.castleId(), pendingGeneration.tier(), wallPlan, anchorChunks, reservedNatureFootprints, natureRandom, pendingGeneration.floorY());
            }
        } else {
            CastleCityTierPlan tierPlan = CastleCityTierRegistry.forTier(pendingGeneration.tier());
            if (!GotCastles.pendingGenerationHasPlacedRole(pendingGeneration, KingdomNpcRole.CART_RIDER)) {
                GotCastles.placeDedicatedBarnStructure(world, world.method_14183(), pendingGeneration.castleId(), pendingGeneration.kingdomId(), pendingGeneration.bannerPos(), pendingGeneration.structureOrigin(), pendingGeneration.structureSize(), tierPlan, pendingGeneration.floorY()).ifPresent(placedStructure -> pendingGeneration.addPlacedStructure(GotCastles.toPendingPlacedStructureState(placedStructure)));
            }
            if (pendingGeneration.subtypes().contains((Object)CastleSubtype.HARBOUR) && !GotCastles.pendingGenerationHasPlacedRole(pendingGeneration, KingdomNpcRole.FISHERMAN)) {
                List<class_1923> harbourChunks = GotCastles.collectClaimHarbourStructureChunks(pendingGeneration.bannerPos(), pendingGeneration.structureOrigin(), pendingGeneration.structureSize(), tierPlan.claimRadiusChunks());
                GotCastles.placeDedicatedHarbourStructure(world, world.method_14183(), pendingGeneration.castleId(), pendingGeneration.kingdomId(), pendingGeneration.bannerPos(), harbourChunks, GotCastles.chunkAreaFor(pendingGeneration.structureOrigin(), pendingGeneration.structureSize()), tierPlan.claimRadiusChunks(), pendingGeneration.floorY()).ifPresent(placedStructure -> pendingGeneration.addPlacedStructure(GotCastles.toPendingPlacedStructureState(placedStructure)));
            }
        }
        pendingGeneration.setStage(v2RoadLayout ? PendingCastleGenerationStage.ROADS : PendingCastleGenerationStage.FINALIZE);
        state.putPendingGeneration(pendingGeneration);
        return 1;
    }

    private static int processPendingV2CastleInfrastructureStage(class_3218 world, CastleWorldState state, PendingCastleGeneration pendingGeneration, CastleWorldgenPlan plan, int budget, long deadlineNanos) {
        if (budget <= 0 || System.nanoTime() >= deadlineNanos) {
            return 0;
        }
        int processed = 0;
        List<class_1923> wallChunks = GotCastles.chunksForFootprint(plan.wallPlan().chunkFootprint());
        if (pendingGeneration.infrastructurePhase() == 0) {
            if (!GotCastles.shouldGenerateDynamicCastleWalls(pendingGeneration.kingdomId())) {
                pendingGeneration.setNextWallChunkCursor(wallChunks.size());
            }
            while (pendingGeneration.nextWallChunkCursor() < wallChunks.size() && processed < budget && System.nanoTime() < deadlineNanos) {
                class_1923 nextChunk = wallChunks.get(pendingGeneration.nextWallChunkCursor());
                if (!world.method_8393(nextChunk.field_9181, nextChunk.field_9180)) {
                    state.putPendingGeneration(pendingGeneration);
                    return processed;
                }
                DynamicCastleWallApplier.applyWallChunk(world, nextChunk, plan);
                pendingGeneration.setNextWallChunkCursor(pendingGeneration.nextWallChunkCursor() + 1);
                ++processed;
            }
            if (pendingGeneration.nextWallChunkCursor() < wallChunks.size()) {
                state.putPendingGeneration(pendingGeneration);
                return processed;
            }
            pendingGeneration.setWallGateClaims(plan.wallGateClaims());
            pendingGeneration.setInfrastructurePhase(1);
        }
        List<CastleWorldgenStructurePlan> nature = plan.natureStructures();
        if (pendingGeneration.infrastructurePhase() == 1) {
            while (pendingGeneration.nextNatureStructureCursor() < nature.size() && processed < budget && System.nanoTime() < deadlineNanos) {
                CastleWorldgenStructurePlan nextNature = nature.get(pendingGeneration.nextNatureStructureCursor());
                if (!GotCastles.areBoundsChunksLoaded(world, nextNature.footprint())) {
                    state.putPendingGeneration(pendingGeneration);
                    return processed;
                }
                CastleWorldgenStructureApplier.applyNatureFull(world, nextNature);
                pendingGeneration.setNextNatureStructureCursor(pendingGeneration.nextNatureStructureCursor() + 1);
                ++processed;
            }
            if (pendingGeneration.nextNatureStructureCursor() < nature.size()) {
                state.putPendingGeneration(pendingGeneration);
                return processed;
            }
            pendingGeneration.setInfrastructurePhase(2);
        }
        pendingGeneration.setStage(PendingCastleGenerationStage.ROADS);
        state.putPendingGeneration(pendingGeneration);
        return Math.max(1, processed);
    }

    private static List<class_1923> chunksForFootprint(StructureChunkFootprint footprint) {
        ArrayList<class_1923> chunks = new ArrayList<class_1923>();
        for (int chunkX = footprint.minChunkX(); chunkX <= footprint.maxChunkX(); ++chunkX) {
            for (int chunkZ = footprint.minChunkZ(); chunkZ <= footprint.maxChunkZ(); ++chunkZ) {
                chunks.add(new class_1923(chunkX, chunkZ));
            }
        }
        return List.copyOf(chunks);
    }

    private static boolean areBoundsChunksLoaded(class_3218 world, class_3341 bounds) {
        for (int chunkX = bounds.method_35415() >> 4; chunkX <= bounds.method_35418() >> 4; ++chunkX) {
            for (int chunkZ = bounds.method_35417() >> 4; chunkZ <= bounds.method_35420() >> 4; ++chunkZ) {
                if (world.method_8393(chunkX, chunkZ)) continue;
                return false;
            }
        }
        return true;
    }

    private static DynamicStoneWallPlan toPendingDynamicStoneWallPlan(DynamicCastleWallPlan plan) {
        return new DynamicStoneWallPlan(plan.outerMinX(), plan.outerMinZ(), plan.outerWidth(), plan.outerDepth(), plan.northSouthGates(), plan.roadX(), plan.roadZ(), plan.interiorChunkKeys());
    }

    private static int processPendingCastleRoadStage(class_3218 world, CastleWorldState state, PendingCastleGeneration pendingGeneration, int budget, long deadlineNanos) {
        if (budget <= 0) {
            return 0;
        }
        CastleWorldgenPlan plan = GotCastles.resolvePinnedPendingRoadPlan(world, pendingGeneration);
        if (plan == null) {
            GotCastles.logPendingLayoutContractBlocked(world, state, pendingGeneration);
            return 0;
        }
        ArrayList<Map.Entry<Long, List<CastleRoadCell>>> chunks = new ArrayList<Map.Entry<Long, List<CastleRoadCell>>>(plan.roadNetworkPlan().cellsByChunk().entrySet());
        int processed = 0;
        int chunkCursor = pendingGeneration.nextRoadChunkCursor();
        int cellCursor = pendingGeneration.nextRoadCellCursor();
        while (chunkCursor < chunks.size() && processed < budget && System.nanoTime() < deadlineNanos) {
            int targetChunkZ;
            List cells = (List)((Map.Entry)chunks.get(chunkCursor)).getValue();
            if (cellCursor >= cells.size()) {
                ++chunkCursor;
                cellCursor = 0;
                continue;
            }
            long chunkKey = (Long)((Map.Entry)chunks.get(chunkCursor)).getKey();
            int targetChunkX = class_1923.method_8325((long)chunkKey);
            if (!world.method_8393(targetChunkX, targetChunkZ = class_1923.method_8332((long)chunkKey))) break;
            CastleRoadNetworkApplier.applyCell(world, (CastleRoadCell)cells.get(cellCursor), plan.anchor());
            ++cellCursor;
            ++processed;
        }
        while (chunkCursor < chunks.size() && cellCursor >= ((List)((Map.Entry)chunks.get(chunkCursor)).getValue()).size()) {
            ++chunkCursor;
            cellCursor = 0;
        }
        pendingGeneration.setNextRoadChunkCursor(chunkCursor);
        pendingGeneration.setNextRoadCellCursor(cellCursor);
        if (chunkCursor >= chunks.size()) {
            pendingGeneration.setStage(PendingCastleGenerationStage.FINALIZE);
        }
        state.putPendingGeneration(pendingGeneration);
        return Math.max(1, processed);
    }

    private static boolean processPendingCastleFinalizeStage(class_3218 world, CastleWorldState state, OverlayCastleSite site, PendingCastleGeneration pendingGeneration) {
        CastleCityTierPlan cityTierPlan = CastleCityTierRegistry.forTier(pendingGeneration.tier());
        String displayName = GotCastles.generatedCastleName((class_6880<class_1959>)world.method_23753(site.anchor()), pendingGeneration.tier(), pendingGeneration.siteIndex());
        CastleTitle title = new CastleTitle(site.castleId() + "_title", site.castleId(), (pendingGeneration.tier() == CastleTier.SETTLEMENT ? "Steward of " : "Lord of ") + displayName, pendingGeneration.tier());
        CastleInstance instance = new CastleInstance(site.castleId(), site.definitionId(), pendingGeneration.tier(), class_2960.method_60654((String)pendingGeneration.templateId()), pendingGeneration.structureOrigin(), pendingGeneration.bannerPos(), class_3532.method_15386((float)((float)pendingGeneration.structureSize().method_10263() / 16.0f)), cityTierPlan.claimRadiusChunks(), Math.max(24, (pendingGeneration.structureSize().method_10263() + pendingGeneration.structureSize().method_10260()) / 4), true, title, pendingGeneration.tier().defaultLootTableId());
        if (!pendingGeneration.kingdomId().isBlank()) {
            instance.setOwnerKingdomId(pendingGeneration.kingdomId());
        }
        if (!pendingGeneration.houseId().isBlank()) {
            instance.setOwnerHouseId(pendingGeneration.houseId());
        }
        if (!pendingGeneration.religionId().isBlank()) {
            instance.setOwnerReligionId(pendingGeneration.religionId());
        }
        if (pendingGeneration.ownerPlayerUuid() != null) {
            instance.setOwnerPlayerUuid(pendingGeneration.ownerPlayerUuid());
        }
        instance.setLordName(pendingGeneration.lordName());
        for (CastleSubtype subtype : pendingGeneration.subtypes()) {
            instance.addSubtype(subtype);
        }
        instance.setWallGateClaims(pendingGeneration.wallGateClaims());
        GotCastles.seedCastleStructureResidentProfiles(instance, GotCastles.fromPendingPlacedStructureStates(pendingGeneration.placedStructures()));
        state.put(instance);
        GotCastles.removePendingCastleGeneration(world, state, site.castleId());
        GotCastles.reconcileHouseLordAssignments(world, !state.overlayCastlesGenerated());
        Kingdom kingdom = GotCastles.resolveKingdomForCastle(instance);
        if (kingdom != null) {
            GotCastles.spawnCastleNpcs(world, instance, kingdom);
        }
        return true;
    }

    private static boolean isAreaLoaded(class_3218 world, class_2338 anchor, int radiusChunks) {
        int centerChunkX = anchor.method_10263() >> 4;
        int centerChunkZ = anchor.method_10260() >> 4;
        for (int chunkX = centerChunkX - radiusChunks; chunkX <= centerChunkX + radiusChunks; ++chunkX) {
            for (int chunkZ = centerChunkZ - radiusChunks; chunkZ <= centerChunkZ + radiusChunks; ++chunkZ) {
                if (world.method_8393(chunkX, chunkZ)) continue;
                return false;
            }
        }
        return true;
    }

    private static boolean isOverlaySiteReadyForPlanning(class_3218 world, OverlayCastleSite site) {
        CastleDefinition definition = GotCastles.get(site.definitionId());
        if (definition == null) {
            return false;
        }
        int footprintRadiusChunks = Math.max(1, (definition.placementRules().footprintChunks() + 1) / 2);
        int scanRadiusChunks = GotCastles.ceilDiv(definition.placementRules().scanRadiusBlocks() + footprintRadiusChunks * 16, 16);
        return GotCastles.isAreaLoaded(world, site.anchor(), Math.max(footprintRadiusChunks, scanRadiusChunks));
    }

    private static boolean isPendingGenerationAreaLoaded(class_3218 world, PendingCastleGeneration generation) {
        return GotCastles.isPendingGenerationAreaLoaded(world, generation, GotCastles.snapshotActivePlayerPositions(world));
    }

    private static boolean isPendingGenerationAreaLoaded(class_3218 world, PendingCastleGeneration generation, List<class_243> activePlayerPositions) {
        return switch (generation.stage()) {
            default -> throw new MatchException(null, null);
            case PendingCastleGenerationStage.PLAN -> {
                OverlayCastleSite site = GotCastles.findOverlaySite(generation.castleId());
                if (site != null && GotCastles.isOverlaySiteReadyForPlanning(world, site)) {
                    yield true;
                }
                yield false;
            }
            case PendingCastleGenerationStage.CORE -> GotCastles.isAreaLoaded(world, generation.planOrigin().equals((Object)class_2338.field_10980) ? generation.anchor() : GotCastles.coreLoadAnchor(generation), GotCastles.coreLoadRadiusChunks(generation));
            case PendingCastleGenerationStage.STRUCTURES -> {
                if (GotCastles.canProcessNearbyDynamicGeneration(world, generation, activePlayerPositions) || GotCastles.isAreaLoaded(world, generation.bannerPos().equals((Object)class_2338.field_10980) ? generation.anchor() : generation.bannerPos(), CastleCityTierRegistry.forTier(generation.tier()).claimRadiusChunks())) {
                    yield true;
                }
                yield false;
            }
            case PendingCastleGenerationStage.INFRASTRUCTURE, PendingCastleGenerationStage.ROADS -> {
                if (generation.roadLayoutVersion() == 2) {
                    yield GotCastles.isNextV2InfrastructureWorkLoaded(world, generation);
                }
                if (GotCastles.canProcessNearbyDynamicGeneration(world, generation, activePlayerPositions) || GotCastles.isAreaLoaded(world, generation.bannerPos().equals((Object)class_2338.field_10980) ? generation.anchor() : generation.bannerPos(), Math.max(2, GotCastles.infrastructureLoadRadiusChunks(generation)))) {
                    yield true;
                }
                yield false;
            }
            case PendingCastleGenerationStage.FINALIZE -> GotCastles.canProcessNearbyDynamicGeneration(world, generation, activePlayerPositions) || GotCastles.isAreaLoaded(world, generation.bannerPos().equals((Object)class_2338.field_10980) ? generation.anchor() : generation.bannerPos(), 1);
        };
    }

    private static boolean isNextV2InfrastructureWorkLoaded(class_3218 world, PendingCastleGeneration generation) {
        CastleWorldgenPlan plan = GotCastles.resolvePinnedPendingRoadPlan(world, generation);
        if (plan == null) {
            return false;
        }
        if (generation.stage() == PendingCastleGenerationStage.ROADS) {
            Long key = GotCastles.nextRoadWorkChunkKey(plan, generation);
            if (key == null) {
                return true;
            }
            return world.method_8393(class_1923.method_8325((long)key), class_1923.method_8332((long)key));
        }
        if (generation.infrastructurePhase() == 0) {
            List<class_1923> chunks = GotCastles.chunksForFootprint(plan.wallPlan().chunkFootprint());
            return generation.nextWallChunkCursor() >= chunks.size() || world.method_8393(chunks.get((int)generation.nextWallChunkCursor()).field_9181, chunks.get((int)generation.nextWallChunkCursor()).field_9180);
        }
        if (generation.infrastructurePhase() == 1) {
            return generation.nextNatureStructureCursor() >= plan.natureStructures().size() || GotCastles.areBoundsChunksLoaded(world, plan.natureStructures().get(generation.nextNatureStructureCursor()).footprint());
        }
        return true;
    }

    private static boolean canProcessNearbyDynamicGeneration(class_3218 world, PendingCastleGeneration generation) {
        return GotCastles.canProcessNearbyDynamicGeneration(world, generation, GotCastles.snapshotActivePlayerPositions(world));
    }

    private static boolean canProcessNearbyDynamicGeneration(class_3218 world, PendingCastleGeneration generation, List<class_243> activePlayerPositions) {
        if (world == null || generation == null || !generation.dynamic()) {
            return false;
        }
        double maxDistanceSq = 262144.0;
        return GotCastles.shouldAcceleratePendingGeneration(GotCastles.isPriorityPendingGeneration(world, generation.castleId()), GotCastles.generationInterestDistanceSq(activePlayerPositions, generation), maxDistanceSq);
    }

    private static class_2338 pendingGenerationActivityCenter(PendingCastleGeneration generation) {
        return switch (generation.stage()) {
            default -> throw new MatchException(null, null);
            case PendingCastleGenerationStage.PLAN -> generation.anchor();
            case PendingCastleGenerationStage.CORE -> GotCastles.coreLoadAnchor(generation);
            case PendingCastleGenerationStage.STRUCTURES, PendingCastleGenerationStage.INFRASTRUCTURE, PendingCastleGenerationStage.ROADS, PendingCastleGenerationStage.FINALIZE -> {
                if (generation.bannerPos().equals((Object)class_2338.field_10980)) {
                    if (generation.structureOrigin().equals((Object)class_2338.field_10980)) {
                        yield generation.anchor();
                    }
                    yield generation.structureOrigin();
                }
                yield generation.bannerPos();
            }
        };
    }

    private static class_2338 coreLoadAnchor(PendingCastleGeneration generation) {
        if (!generation.structureOrigin().equals((Object)class_2338.field_10980)) {
            return generation.structureOrigin();
        }
        if (!generation.planOrigin().equals((Object)class_2338.field_10980)) {
            return generation.planOrigin();
        }
        return generation.anchor();
    }

    private static int coreLoadRadiusChunks(PendingCastleGeneration generation) {
        int blocks = generation.structureSize().equals((Object)class_2382.field_11176) ? Math.max(16, generation.footprintBlocks()) : Math.max(generation.structureSize().method_10263(), generation.structureSize().method_10260());
        int blendBlocks = generation.dynamic() ? Math.max(12, generation.blendRadius() + 4) : generation.blendRadius();
        return Math.max(2, GotCastles.ceilDiv(blocks / 2 + blendBlocks, 16) + 1);
    }

    private static int infrastructureLoadRadiusChunks(PendingCastleGeneration generation) {
        int claimRadius = CastleCityTierRegistry.forTier(generation.tier()).claimRadiusChunks();
        return generation.dynamic() ? Math.max(3, Math.min(claimRadius, GotCastles.coreLoadRadiusChunks(generation) + 2)) : claimRadius;
    }

    private static void logBlockedPendingGeneration(class_3218 world, PendingCastleGeneration generation) {
        if (!GotCastles.isPriorityPendingGeneration(world, generation.castleId())) {
            double maxLogDistanceSq = 262144.0;
            if (GotCastles.generationInterestDistanceSq(world, generation) > maxLogDistanceSq) {
                return;
            }
        }
        long currentTick = world.method_8510();
        if (generation.lastBlockedLogTick() != Long.MIN_VALUE && currentTick >= generation.lastBlockedLogTick() && currentTick - generation.lastBlockedLogTick() < 100L) {
            return;
        }
        PendingGenerationLoadRequirement requirement = GotCastles.pendingGenerationLoadRequirement(world, generation);
        if (requirement == null) {
            return;
        }
        class_3222 nearestPlayer = GotCastles.nearestActivePlayer(world, requirement.center());
        String nearestPlayerPos = nearestPlayer == null ? "none" : nearestPlayer.method_24515().method_23854();
        double nearestDistance = nearestPlayer == null ? -1.0 : Math.sqrt(nearestPlayer.method_19538().method_1025(class_243.method_24953((class_2382)requirement.center())));
        GotMod.LOGGER.info("Waiting on overlay castle {} stage={} center={} radiusChunks={} nearestPlayer={} distanceBlocks={}", new Object[]{generation.castleId(), generation.stage(), requirement.center().method_23854(), requirement.radiusChunks(), nearestPlayerPos, nearestPlayer == null ? "n/a" : String.format(Locale.ROOT, "%.1f", nearestDistance)});
        generation.setLastBlockedLogTick(currentTick);
    }

    private static void updateForcedCastleGenerationChunks(class_3218 world, CastleWorldState state, @Nullable PendingCastleGeneration prioritizedGeneration, List<class_243> activePlayerPositions) {
        PendingCastleGeneration generationToForce;
        LinkedHashSet<Long> desiredChunks = new LinkedHashSet<Long>();
        PendingCastleGeneration pendingCastleGeneration = generationToForce = prioritizedGeneration != null ? prioritizedGeneration : GotCastles.findForceLoadedPendingGeneration(world, state, activePlayerPositions);
        if (generationToForce != null) {
            PendingGenerationLoadRequirement requirement = GotCastles.pendingGenerationLoadRequirement(world, generationToForce);
            boolean shouldForceLoad = GotCastles.shouldAcceleratePendingGeneration(GotCastles.isPriorityPendingGeneration(world, generationToForce.castleId()), GotCastles.generationInterestDistanceSq(activePlayerPositions, generationToForce), 262144.0);
            if (requirement != null && shouldForceLoad) {
                for (int chunkX = (requirement.center().method_10263() >> 4) - requirement.radiusChunks(); chunkX <= (requirement.center().method_10263() >> 4) + requirement.radiusChunks(); ++chunkX) {
                    for (int chunkZ = (requirement.center().method_10260() >> 4) - requirement.radiusChunks(); chunkZ <= (requirement.center().method_10260() >> 4) + requirement.radiusChunks(); ++chunkZ) {
                        desiredChunks.add(class_1923.method_8331((int)chunkX, (int)chunkZ));
                    }
                }
            }
        }
        Set previousChunks = FORCED_CASTLE_GENERATION_CHUNKS.computeIfAbsent((class_5321<class_1937>)world.method_27983(), ignored -> new LinkedHashSet());
        Iterator iterator = previousChunks.iterator();
        while (iterator.hasNext()) {
            long packed = (Long)iterator.next();
            if (desiredChunks.contains(packed)) continue;
            world.method_17988(class_1923.method_8325((long)packed), class_1923.method_8332((long)packed), false);
        }
        iterator = desiredChunks.iterator();
        while (iterator.hasNext()) {
            long packed = (Long)iterator.next();
            if (previousChunks.contains(packed)) continue;
            world.method_17988(class_1923.method_8325((long)packed), class_1923.method_8332((long)packed), true);
        }
        previousChunks.clear();
        previousChunks.addAll(desiredChunks);
    }

    @Nullable
    private static PendingCastleGeneration findForceLoadedPendingGeneration(class_3218 world, CastleWorldState state, List<class_243> activePlayerPositions) {
        PendingCastleGeneration prioritizedGeneration = GotCastles.findPriorityPendingGeneration(world, state, false, true, activePlayerPositions);
        if (prioritizedGeneration != null) {
            return prioritizedGeneration;
        }
        PendingCastleGeneration bestGeneration = null;
        double nearestDistanceSq = Double.MAX_VALUE;
        for (PendingCastleGeneration generation : state.getAllPendingGenerations()) {
            double distanceSq = GotCastles.generationInterestDistanceSq(activePlayerPositions, generation);
            if (bestGeneration != null && !(distanceSq < nearestDistanceSq)) continue;
            bestGeneration = generation;
            nearestDistanceSq = distanceSq;
        }
        return bestGeneration;
    }

    @Nullable
    private static PendingGenerationLoadRequirement pendingGenerationLoadRequirement(class_3218 world, PendingCastleGeneration generation) {
        return switch (generation.stage()) {
            default -> throw new MatchException(null, null);
            case PendingCastleGenerationStage.PLAN -> {
                OverlayCastleSite site = GotCastles.findOverlaySite(generation.castleId());
                if (site == null) {
                    yield null;
                }
                CastleDefinition definition = GotCastles.get(site.definitionId());
                if (definition == null) {
                    yield null;
                }
                int footprintRadiusChunks = Math.max(1, (definition.placementRules().footprintChunks() + 1) / 2);
                int scanRadiusChunks = GotCastles.ceilDiv(definition.placementRules().scanRadiusBlocks() + footprintRadiusChunks * 16, 16);
                yield new PendingGenerationLoadRequirement(site.anchor(), Math.max(footprintRadiusChunks, scanRadiusChunks));
            }
            case PendingCastleGenerationStage.CORE -> new PendingGenerationLoadRequirement(GotCastles.coreLoadAnchor(generation), GotCastles.coreLoadRadiusChunks(generation));
            case PendingCastleGenerationStage.STRUCTURES -> new PendingGenerationLoadRequirement(generation.bannerPos().equals((Object)class_2338.field_10980) ? GotCastles.pendingGenerationActivityCenter(generation) : generation.bannerPos(), CastleCityTierRegistry.forTier(generation.tier()).claimRadiusChunks());
            case PendingCastleGenerationStage.INFRASTRUCTURE, PendingCastleGenerationStage.ROADS -> {
                if (generation.roadLayoutVersion() == 2) {
                    yield GotCastles.nextV2LoadRequirement(world, generation);
                }
                yield new PendingGenerationLoadRequirement(generation.bannerPos().equals((Object)class_2338.field_10980) ? GotCastles.pendingGenerationActivityCenter(generation) : generation.bannerPos(), Math.max(2, GotCastles.infrastructureLoadRadiusChunks(generation)));
            }
            case PendingCastleGenerationStage.FINALIZE -> new PendingGenerationLoadRequirement(generation.bannerPos().equals((Object)class_2338.field_10980) ? GotCastles.pendingGenerationActivityCenter(generation) : generation.bannerPos(), 1);
        };
    }

    @Nullable
    private static PendingGenerationLoadRequirement nextV2LoadRequirement(class_3218 world, PendingCastleGeneration generation) {
        CastleWorldgenPlan plan = GotCastles.resolvePinnedPendingRoadPlan(world, generation);
        if (plan == null) {
            return null;
        }
        if (generation.stage() == PendingCastleGenerationStage.ROADS) {
            Long key = GotCastles.nextRoadWorkChunkKey(plan, generation);
            if (key == null) {
                return new PendingGenerationLoadRequirement(generation.bannerPos(), 0);
            }
            return new PendingGenerationLoadRequirement(new class_1923(class_1923.method_8325((long)key), class_1923.method_8332((long)key)).method_8323(), 0);
        }
        if (generation.infrastructurePhase() == 0) {
            List<class_1923> chunks = GotCastles.chunksForFootprint(plan.wallPlan().chunkFootprint());
            if (generation.nextWallChunkCursor() >= chunks.size()) {
                return new PendingGenerationLoadRequirement(generation.bannerPos(), 0);
            }
            return new PendingGenerationLoadRequirement(chunks.get(generation.nextWallChunkCursor()).method_8323(), 0);
        }
        if (generation.infrastructurePhase() == 1 && generation.nextNatureStructureCursor() < plan.natureStructures().size()) {
            class_3341 bounds = plan.natureStructures().get(generation.nextNatureStructureCursor()).footprint();
            int minX = bounds.method_35415() >> 4;
            int maxX = bounds.method_35418() >> 4;
            int minZ = bounds.method_35417() >> 4;
            int maxZ = bounds.method_35420() >> 4;
            int centerX = minX + maxX >> 1;
            int centerZ = minZ + maxZ >> 1;
            return new PendingGenerationLoadRequirement(new class_1923(centerX, centerZ).method_8323(), Math.max(maxX - centerX, maxZ - centerZ));
        }
        return new PendingGenerationLoadRequirement(generation.bannerPos(), 0);
    }

    @Nullable
    private static Long nextRoadWorkChunkKey(CastleWorldgenPlan plan, PendingCastleGeneration generation) {
        ArrayList<Map.Entry<Long, List<CastleRoadCell>>> chunks = new ArrayList<Map.Entry<Long, List<CastleRoadCell>>>(plan.roadNetworkPlan().cellsByChunk().entrySet());
        int cellCursor = generation.nextRoadCellCursor();
        for (int chunkCursor = generation.nextRoadChunkCursor(); chunkCursor < chunks.size(); ++chunkCursor) {
            if (cellCursor < ((List)((Map.Entry)chunks.get(chunkCursor)).getValue()).size()) {
                return (Long)((Map.Entry)chunks.get(chunkCursor)).getKey();
            }
            cellCursor = 0;
        }
        return null;
    }

    private static boolean ensureDynamicCastleCoreCache(class_3218 world, String castleId, PendingCastleGeneration pendingGeneration, DynamicStoneCastleTierConfig config) {
        if (pendingGeneration.structureOrigin().equals((Object)class_2338.field_10980) || pendingGeneration.structureSize().equals((Object)class_2382.field_11176) || pendingGeneration.dynamicCoreClearanceHeight() <= 0) {
            class_3499 claimTemplate;
            class_3485 templateManager = world.method_14183();
            Optional<CastleKeepTemplate> keepTemplate = GotCastles.loadCastleKeepTemplate(world, templateManager, castleId);
            class_3499 class_34992 = claimTemplate = keepTemplate.isPresent() ? null : (class_3499)templateManager.method_15094(STONE_CASTLE_CLAIM_STRUCTURE_ID).orElse(null);
            if (keepTemplate.isEmpty() && claimTemplate == null) {
                GotMod.LOGGER.warn("Missing dynamic stone castle claim piece for {}", (Object)castleId);
                return false;
            }
            class_2382 footprintSize = keepTemplate.map(template -> GotCastles.expandDynamicFootprintForKeep(GotCastles.dynamicStoneFootprintSize(config), template)).orElse(GotCastles.dynamicStoneFootprintSize(config));
            int footprintOriginX = pendingGeneration.planOrigin().method_10263() - Math.max(0, (footprintSize.method_10263() - pendingGeneration.footprintBlocks()) / 2);
            int footprintOriginZ = pendingGeneration.planOrigin().method_10260() - Math.max(0, (footprintSize.method_10260() - pendingGeneration.footprintBlocks()) / 2);
            if (GotCastles.usesSouthOnlyCastleGeneration(castleId)) {
                int centerZ;
                footprintOriginZ = centerZ = pendingGeneration.planOrigin().method_10260() + pendingGeneration.footprintBlocks() / 2;
            }
            class_2338 footprintOrigin = new class_2338(footprintOriginX, pendingGeneration.floorY(), footprintOriginZ);
            pendingGeneration.setStructureOrigin(footprintOrigin);
            pendingGeneration.setStructureSize(footprintSize);
            pendingGeneration.setDynamicCoreUsesCastleKeep(keepTemplate.isPresent());
            pendingGeneration.setDynamicCoreClearanceHeight(keepTemplate.map(template -> Math.max(28, template.size().method_10264() + 4)).orElse(28));
        }
        return true;
    }

    private static int ceilDiv(int value, int divisor) {
        return divisor <= 0 ? 0 : Math.floorDiv(value + divisor - 1, divisor);
    }

    private static void processHireableCampQueue(class_3218 world, CastleWorldState state) {
        if (world.method_8510() % 20L != 0L || DynamicTroopRegistry.getHireableCampDefinitions().isEmpty()) {
            return;
        }
        int placedThisTick = 0;
        for (class_3222 player : world.method_18456()) {
            if (player.method_7325()) continue;
            List<MapChunkGenerator.PredictedHireableCampSite> sites = MapChunkGenerator.findNearestHireableCampSites(world, player.method_24515(), 6);
            for (MapChunkGenerator.PredictedHireableCampSite site : sites) {
                String claimId = GotCastles.hireableCampClaimId(site.definitionId(), site.chunkPos());
                if (state.contains(claimId) || state.isRejected(claimId)) continue;
                HireableCampDefinition definition = DynamicTroopRegistry.getHireableCamp(site.definitionId());
                if (definition == null) {
                    state.markRejected(claimId);
                    continue;
                }
                if (!GotCastles.isAreaLoaded(world, site.teleportPos(), Math.max(3, definition.claimRadiusChunks() + 2))) continue;
                if (state.findClaimAt(site.teleportPos()) != null) {
                    state.markRejected(claimId);
                    continue;
                }
                if (!GotCastles.placePredictedHireableCamp(world, definition, site)) {
                    state.markRejected(claimId);
                    continue;
                }
                GotCastles.registerHireableCampClaim(world, definition, site.chunkPos());
                if (!state.contains(claimId)) {
                    state.markRejected(claimId);
                    continue;
                }
                if (++placedThisTick < 1) continue;
                return;
            }
        }
    }

    private static boolean placePredictedHireableCamp(class_3218 world, HireableCampDefinition definition, MapChunkGenerator.PredictedHireableCampSite site) {
        class_3499 template = world.method_14183().method_15094(class_2960.method_60655((String)"agob", (String)definition.structurePath())).orElse(null);
        if (template == null) {
            GotMod.LOGGER.warn("Missing hireable camp template {} for {}", (Object)definition.structurePath(), (Object)definition.id());
            return false;
        }
        class_2382 size = template.method_15160();
        class_2338 predictedCenter = site.teleportPos();
        class_2338 origin = new class_2338(predictedCenter.method_10263() - size.method_10263() / 2, predictedCenter.method_10264(), predictedCenter.method_10260() - size.method_10260() / 2);
        StructureSurfaceStats surfaceStats = GotCastles.sampleStructureSurface(world, origin, size);
        if (surfaceStats.heights().isEmpty() || surfaceStats.maxY() - surfaceStats.minY() > 10) {
            GotMod.LOGGER.info("Skipping hireable camp {} at {} due to terrain delta {}", new Object[]{definition.id(), predictedCenter.method_23854(), surfaceStats.maxY() - surfaceStats.minY()});
            return false;
        }
        int floorY = GotCastles.resolveStructureFloorY(surfaceStats);
        origin = new class_2338(origin.method_10263(), floorY + 1, origin.method_10260());
        GotCastles.prepareStructurePad(world, origin, size, floorY, 3);
        class_3492 placement = new class_3492().method_15133(false).method_15131(true).method_27264(false);
        if (!template.method_15172((class_5425)world, origin, origin, placement, world.field_9229, 3)) {
            GotMod.LOGGER.warn("Failed to place hireable camp {} at {}", (Object)definition.id(), (Object)origin.method_23854());
            return false;
        }
        GotCastles.stabilizeStructureFooting(world, origin, size, origin.method_10264());
        return true;
    }

    private static void processWeirwoodQueue(class_3218 world) {
        if (world.method_8510() % 20L != 0L) {
            return;
        }
        WeirwoodTreeWorldState state = WeirwoodTreeWorldState.get(world);
        int placedThisTick = 0;
        for (class_3222 player : world.method_18456()) {
            if (player.method_7325()) continue;
            List<PredictedWeirwoodSite> sites = GotCastles.findNearestWeirwoodSites(world, player.method_24515(), 4);
            for (PredictedWeirwoodSite site : sites) {
                class_2338 placedBase;
                if (state.contains(site.castleId()) || !GotCastles.isAreaLoaded(world, site.pos(), 6) || (placedBase = GotCastles.placeWeirwoodTreeNear(world, site.pos())) == null) continue;
                state.put(site.castleId(), placedBase, world.method_8510() + 2400L);
                if (++placedThisTick < 1) continue;
                return;
            }
        }
    }

    private static void processWeirwoodFaceTimers(class_3218 world, long worldTime) {
        if (world == null || worldTime % 2400L != 0L) {
            return;
        }
        WeirwoodTreeWorldState state = WeirwoodTreeWorldState.get(world);
        for (WeirwoodTreeWorldState.Entry entry : state.getAll()) {
            if (entry.nextBleedAttemptTick() <= 0L || worldTime < entry.nextBleedAttemptTick()) continue;
            class_2338 facePos = GotCastles.weirwoodFacePos(entry.pos());
            class_2680 faceState = world.method_8320(facePos);
            if (faceState.method_27852(GotBlocks.WEIRWOOD_LOG_FACE_BLOODY)) {
                state.setNextBleedAttemptTick(entry.castleId(), 0L);
                continue;
            }
            if (!faceState.method_27852(GotBlocks.WEIRWOOD_LOG_FACE)) {
                state.setNextBleedAttemptTick(entry.castleId(), worldTime + 2400L);
                continue;
            }
            if (world.field_9229.method_43048(16) == 0) {
                world.method_8652(facePos, GotCastles.copyWeirwoodFaceState(faceState, GotBlocks.WEIRWOOD_LOG_FACE_BLOODY.method_9564()), 3);
                state.setNextBleedAttemptTick(entry.castleId(), 0L);
                continue;
            }
            state.setNextBleedAttemptTick(entry.castleId(), worldTime + 2400L);
        }
    }

    private static List<PredictedWeirwoodSite> findNearestWeirwoodSites(class_3218 world, class_2338 origin, int limit) {
        if (world == null || origin == null || limit <= 0) {
            return List.of();
        }
        CastleWorldState castleState = GotCastles.getWorldState(world);
        WeirwoodTreeWorldState weirwoodState = WeirwoodTreeWorldState.get(world);
        Map cachedPredictions = CACHED_WEIRWOOD_SITE_PREDICTIONS.computeIfAbsent((class_5321<class_1937>)world.method_27983(), ignored -> new HashMap());
        ArrayList<PredictedWeirwoodSite> sites = new ArrayList<PredictedWeirwoodSite>();
        ArrayList<WeirwoodAnchor> anchors = new ArrayList<WeirwoodAnchor>(GotCastles.collectWeirwoodAnchors(world, castleState));
        anchors.sort(Comparator.comparingDouble(anchor -> anchor.anchorPos().method_10262((class_2382)origin)));
        for (WeirwoodAnchor anchor2 : anchors) {
            class_2338 pos = weirwoodState.get(anchor2.castleId());
            if (pos == null) {
                pos = (class_2338)cachedPredictions.get(anchor2.castleId());
            }
            if (pos == null && (pos = GotCastles.predictDisplayedWeirwoodSite(world, anchor2)) != null) {
                cachedPredictions.put(anchor2.castleId(), pos.method_10062());
            }
            if (pos == null) continue;
            sites.add(new PredictedWeirwoodSite(anchor2.castleId(), anchor2.castleName(), pos));
        }
        sites.sort(Comparator.comparingDouble(site -> site.pos().method_10262((class_2382)origin)));
        if (sites.size() <= limit) {
            return List.copyOf(sites);
        }
        return List.copyOf(sites.subList(0, limit));
    }

    private static List<WeirwoodAnchor> collectWeirwoodAnchors(class_3218 world, CastleWorldState state) {
        WeirwoodAnchor anchor;
        if (world == null) {
            return List.of();
        }
        ArrayList<WeirwoodAnchor> anchors = new ArrayList<WeirwoodAnchor>();
        LinkedHashSet<String> seenCastleIds = new LinkedHashSet<String>();
        for (CastleInstance castle : state.getAll()) {
            anchor = GotCastles.createGeneratedWeirwoodAnchor(world, castle);
            if (anchor == null || !seenCastleIds.add(anchor.castleId())) continue;
            anchors.add(anchor);
        }
        for (OverlayCastleSite site : overlaySites) {
            if (site == null || state.isRejected(site.castleId()) || !seenCastleIds.add(site.castleId()) || (anchor = GotCastles.createPendingWeirwoodAnchor(world, site)) == null) continue;
            anchors.add(anchor);
        }
        return List.copyOf(anchors);
    }

    @Nullable
    private static class_2338 predictDisplayedWeirwoodSite(class_3218 world, WeirwoodAnchor anchor) {
        int fallbackZ;
        int preferredRadius;
        if (world == null || anchor == null) {
            return null;
        }
        long seed = (anchor.anchorPos().method_10063() ^ (long)anchor.castleId().hashCode()) + 1464158546L;
        class_5819 random = class_5819.method_43049((long)seed);
        double baseAngle = random.method_43058() * Math.PI * 2.0;
        class_2338 strictCandidate = GotCastles.findBestDisplayedWeirwoodCandidate(world, anchor, baseAngle, preferredRadius = 700 + random.method_43048(201), true);
        if (strictCandidate != null) {
            return strictCandidate;
        }
        class_2338 relaxedCandidate = GotCastles.findBestDisplayedWeirwoodCandidate(world, anchor, baseAngle, preferredRadius, false);
        if (relaxedCandidate != null) {
            return relaxedCandidate;
        }
        int fallbackX = anchor.anchorPos().method_10263() + class_3532.method_15357((double)(Math.cos(baseAngle) * (double)preferredRadius));
        class_2338 fallbackSurface = GotCastles.forceWeirwoodSurfacePos(world, fallbackX, fallbackZ = anchor.anchorPos().method_10260() + class_3532.method_15357((double)(Math.sin(baseAngle) * (double)preferredRadius)));
        if (fallbackSurface != null) {
            return fallbackSurface;
        }
        int fallbackY = Math.max(anchor.anchorPos().method_10264(), world.method_8615() + 2);
        return new class_2338(fallbackX, fallbackY, fallbackZ);
    }

    @Nullable
    private static class_2338 findBestDisplayedWeirwoodCandidate(class_3218 world, WeirwoodAnchor anchor, double baseAngle, int preferredRadius, boolean requireNorthernBiome) {
        class_2338 bestPos = null;
        double bestScore = Double.NEGATIVE_INFINITY;
        for (int radiusOffset = -96; radiusOffset <= 96; radiusOffset += 24) {
            int radius = class_3532.method_15340((int)(preferredRadius + radiusOffset), (int)700, (int)900);
            for (int angleStep = 0; angleStep < 24; ++angleStep) {
                double angle = baseAngle + Math.PI * 2 * (double)angleStep / 24.0;
                int sampleX = anchor.anchorPos().method_10263() + class_3532.method_15357((double)(Math.cos(angle) * (double)radius));
                int sampleZ = anchor.anchorPos().method_10260() + class_3532.method_15357((double)(Math.sin(angle) * (double)radius));
                for (int offsetX = -24; offsetX <= 24; offsetX += 4) {
                    for (int offsetZ = -24; offsetZ <= 24; offsetZ += 4) {
                        double score;
                        class_2338 surfacePos = GotCastles.forceWeirwoodSurfacePos(world, sampleX + offsetX, sampleZ + offsetZ);
                        if (surfacePos == null || !GotCastles.isDisplayedWeirwoodSite(world, anchor, surfacePos, requireNorthernBiome) || !((score = GotCastles.scoreDisplayedWeirwoodSite(world, anchor, surfacePos, preferredRadius)) > bestScore)) continue;
                        bestScore = score;
                        bestPos = surfacePos;
                    }
                }
            }
        }
        return bestPos;
    }

    private static boolean isDisplayedWeirwoodSite(class_3218 world, WeirwoodAnchor anchor, class_2338 pos, boolean requireNorthernBiome) {
        if (world == null || anchor == null || pos == null) {
            return false;
        }
        long distanceSq = GotCastles.horizontalSquaredDistance(anchor.anchorPos(), pos);
        if (distanceSq < 490000L || distanceSq > 810000L) {
            return false;
        }
        String biomePath = GotCastles.biomePathAt(world, pos);
        if (requireNorthernBiome && !GotCastles.isNorthernWeirwoodBiomePath(biomePath)) {
            return false;
        }
        if (biomePath.contains("sea") || biomePath.contains("ocean") || biomePath.contains("river") || biomePath.contains("lake")) {
            return false;
        }
        class_2680 ground = world.method_8320(pos);
        return !ground.method_26215() && ground.method_26227().method_15769();
    }

    private static double scoreDisplayedWeirwoodSite(class_3218 world, WeirwoodAnchor anchor, class_2338 pos, int preferredRadius) {
        String biomePath = GotCastles.biomePathAt(world, pos);
        double distanceBias = Math.abs(Math.sqrt(GotCastles.horizontalSquaredDistance(anchor.anchorPos(), pos)) - (double)preferredRadius);
        double forestBonus = biomePath.contains("wolfswood") || biomePath.contains("forest") ? 8.0 : 0.0;
        double northBonus = GotCastles.isNorthernWeirwoodBiomePath(biomePath) ? 12.0 : 0.0;
        return 1000.0 - distanceBias + forestBonus + northBonus;
    }

    @Nullable
    private static class_2338 forceWeirwoodSurfacePos(class_3218 world, int x, int z) {
        if (world == null) {
            return null;
        }
        int topY = world.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
        if (topY <= world.method_31607() + 1) {
            return null;
        }
        class_2338 pos = new class_2338(x, topY, z);
        class_2680 ground = world.method_8320(pos);
        if (ground.method_26215() || !ground.method_26227().method_15769()) {
            for (int y = topY - 1; y > world.method_31607() + 1; --y) {
                class_2338 candidate = new class_2338(x, y, z);
                class_2680 candidateState = world.method_8320(candidate);
                if (candidateState.method_26215() || !candidateState.method_26227().method_15769()) continue;
                return candidate;
            }
            return null;
        }
        return pos;
    }

    @Nullable
    private static WeirwoodAnchor createGeneratedWeirwoodAnchor(class_3218 world, @Nullable CastleInstance castle) {
        if (world == null || castle == null) {
            return null;
        }
        if (GotCastles.isHireableCampClaim(castle) || "player_claim".equals(castle.getDefinitionId())) {
            return null;
        }
        class_2338 anchorPos = castle.getBannerPos();
        if (!GotCastles.isWeirwoodAnchorBiome(world, castle.getId(), anchorPos)) {
            return null;
        }
        String castleName = GotCastles.displayNameForCastle(castle);
        if (castleName == null || castleName.isBlank()) {
            castleName = castle.getId();
        }
        return new WeirwoodAnchor(castle.getId(), castleName, anchorPos);
    }

    @Nullable
    private static WeirwoodAnchor createPendingWeirwoodAnchor(class_3218 world, @Nullable OverlayCastleSite site) {
        String castleName;
        if (world == null || site == null) {
            return null;
        }
        class_2338 anchorPos = GotCastles.resolvePlacementAnchor(world, site);
        if (!GotCastles.isWeirwoodAnchorBiome(world, site.castleId(), anchorPos)) {
            return null;
        }
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(site.castleId());
        String string = castleName = metadata != null && !metadata.name().isBlank() ? metadata.name() : GotCastles.pendingDisplayName(site);
        if (castleName == null || castleName.isBlank()) {
            castleName = site.castleId();
        }
        return new WeirwoodAnchor(site.castleId(), castleName, anchorPos);
    }

    private static boolean isWeirwoodAnchorBiome(class_3218 world, @Nullable String castleId, @Nullable class_2338 anchorPos) {
        if (castleId != null && WEIRWOOD_ELIGIBLE_CASTLE_IDS.contains(castleId)) {
            return true;
        }
        if (world != null && anchorPos != null && GotCastles.isNorthernWeirwoodBiomePath(GotCastles.biomePathAt(world, anchorPos))) {
            return true;
        }
        if (castleId == null || castleId.isBlank()) {
            return false;
        }
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(castleId);
        return metadata != null && GotKingdoms.THE_NORTH.getId().toString().equals(GotCastles.canonicalKingdomId(metadata.kingdomId()));
    }

    @Nullable
    private static class_2338 predictWeirwoodSite(class_3218 world, WeirwoodAnchor anchor, CastleWorldState castleState, VillageWorldState villageState, WeirwoodTreeWorldState weirwoodState) {
        class_5819 random = class_5819.method_43049((long)((anchor.anchorPos().method_10063() ^ (long)anchor.castleId().hashCode()) + 1464158546L));
        double baseAngle = random.method_43058() * Math.PI * 2.0;
        class_2338 bestPos = null;
        double bestScore = Double.NEGATIVE_INFINITY;
        for (int attempt = 0; attempt < 128; ++attempt) {
            double score;
            int z;
            double angle = baseAngle + (double)attempt * 2.399963229728653;
            int radius = 700 + random.method_43048(201);
            int x = anchor.anchorPos().method_10263() + class_3532.method_15357((double)(Math.cos(angle) * (double)radius));
            class_2338 surfacePos = GotCastles.resolveWeirwoodSurfacePos(world, x, z = anchor.anchorPos().method_10260() + class_3532.method_15357((double)(Math.sin(angle) * (double)radius)));
            if (surfacePos == null || !GotCastles.isValidWeirwoodSite(world, anchor, castleState, villageState, weirwoodState, surfacePos) || !((score = GotCastles.scoreWeirwoodSite(world, anchor, surfacePos)) > bestScore)) continue;
            bestScore = score;
            bestPos = surfacePos;
        }
        if (bestPos != null) {
            return bestPos;
        }
        return GotCastles.predictWeirwoodSiteFallback(world, anchor, castleState, villageState, weirwoodState, baseAngle);
    }

    @Nullable
    private static class_2338 predictWeirwoodSiteFallback(class_3218 world, WeirwoodAnchor anchor, CastleWorldState castleState, VillageWorldState villageState, WeirwoodTreeWorldState weirwoodState, double baseAngle) {
        class_2338 bestPos = null;
        double bestScore = Double.NEGATIVE_INFINITY;
        for (int radius = 700; radius <= 900; radius += 24) {
            for (int angleStep = 0; angleStep < 24; ++angleStep) {
                double angle = baseAngle + Math.PI * 2 * (double)angleStep / 24.0;
                int sampleX = anchor.anchorPos().method_10263() + class_3532.method_15357((double)(Math.cos(angle) * (double)radius));
                int sampleZ = anchor.anchorPos().method_10260() + class_3532.method_15357((double)(Math.sin(angle) * (double)radius));
                for (int offsetX = -24; offsetX <= 24; offsetX += 4) {
                    for (int offsetZ = -24; offsetZ <= 24; offsetZ += 4) {
                        double score;
                        class_2338 surfacePos = GotCastles.resolveWeirwoodSurfacePos(world, sampleX + offsetX, sampleZ + offsetZ);
                        if (surfacePos == null || !GotCastles.isValidWeirwoodSite(world, anchor, castleState, villageState, weirwoodState, surfacePos) || !((score = GotCastles.scoreWeirwoodSite(world, anchor, surfacePos)) > bestScore)) continue;
                        bestScore = score;
                        bestPos = surfacePos;
                    }
                }
            }
        }
        return bestPos;
    }

    @Nullable
    private static class_2338 resolveWeirwoodSurfacePos(class_3218 world, int x, int z) {
        int surfaceY = world.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
        if (surfaceY <= world.method_31607() + 2 || surfaceY >= world.method_31600() - 16) {
            return null;
        }
        class_2338 pos = new class_2338(x, surfaceY, z);
        class_2680 ground = world.method_8320(pos);
        if (ground.method_26215() || !ground.method_26227().method_15769() || ground.method_26164(class_3481.field_15475) || ground.method_26164(class_3481.field_15503)) {
            return null;
        }
        if (!GotCastles.canReplaceWeirwoodBlock(world.method_8320(pos.method_10084())) || !GotCastles.canReplaceWeirwoodBlock(world.method_8320(pos.method_10086(2)))) {
            return null;
        }
        return pos;
    }

    private static boolean isValidWeirwoodSite(class_3218 world, WeirwoodAnchor anchor, CastleWorldState castleState, VillageWorldState villageState, WeirwoodTreeWorldState weirwoodState, class_2338 pos) {
        if (world == null || anchor == null || pos == null) {
            return false;
        }
        long distanceSq = GotCastles.horizontalSquaredDistance(anchor.anchorPos(), pos);
        if (distanceSq < 490000L || distanceSq > 810000L) {
            return false;
        }
        if (!GotCastles.isNorthernWeirwoodBiomePath(GotCastles.biomePathAt(world, pos))) {
            return false;
        }
        if (castleState.findClaimAt(pos) != null) {
            return false;
        }
        VillageInstance villageClaim = villageState.findClaimAt(pos);
        if (GotCastles.isBlockingWeirwoodVillageClaim(villageClaim)) {
            return false;
        }
        StructureSurfaceStats surfaceStats = GotCastles.sampleStructureSurface(world, pos.method_10069(-4, 0, -4), new class_2382(9, 1, 9));
        if (surfaceStats.heights().isEmpty() || surfaceStats.maxY() - surfaceStats.minY() > 10) {
            return false;
        }
        for (WeirwoodTreeWorldState.Entry entry : weirwoodState.getAll()) {
            if (entry.castleId().equals(anchor.castleId()) || !(entry.pos().method_10262((class_2382)pos) < 102400.0)) continue;
            return false;
        }
        return GotCastles.hasWeirwoodClearance(world, pos);
    }

    private static double scoreWeirwoodSite(class_3218 world, WeirwoodAnchor anchor, class_2338 pos) {
        StructureSurfaceStats surfaceStats = GotCastles.sampleStructureSurface(world, pos.method_10069(-4, 0, -4), new class_2382(9, 1, 9));
        int terrainDelta = surfaceStats.maxY() - surfaceStats.minY();
        double distanceBias = Math.abs(Math.sqrt(GotCastles.horizontalSquaredDistance(anchor.anchorPos(), pos)) - 800.0);
        String biomePath = GotCastles.biomePathAt(world, pos);
        double forestBonus = biomePath.contains("wolfswood") || biomePath.contains("forest") ? 8.0 : 0.0;
        return 1000.0 - (double)terrainDelta * 40.0 - distanceBias + forestBonus;
    }

    private static boolean hasWeirwoodClearance(class_3218 world, class_2338 base) {
        for (int x = base.method_10263() - 2; x <= base.method_10263() + 2; ++x) {
            for (int z = base.method_10260() - 2; z <= base.method_10260() + 2; ++z) {
                for (int y = base.method_10264() + 1; y <= base.method_10264() + 10; ++y) {
                    class_2680 state = world.method_8320(new class_2338(x, y, z));
                    if (GotCastles.canReplaceWeirwoodBlock(state)) continue;
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isBlockingWeirwoodVillageClaim(@Nullable VillageInstance villageClaim) {
        return villageClaim != null && villageClaim.hasGeneratedStructures();
    }

    @Nullable
    private static class_2338 placeWeirwoodTree(class_3218 world, class_2338 anchorPos) {
        int dy;
        class_2338 base = GotCastles.resolveWeirwoodSurfacePos(world, anchorPos.method_10263(), anchorPos.method_10260());
        if (base == null) {
            return null;
        }
        StructureSurfaceStats surfaceStats = GotCastles.sampleStructureSurface(world, base.method_10069(-4, 0, -4), new class_2382(9, 1, 9));
        if (surfaceStats.heights().isEmpty()) {
            return null;
        }
        int floorY = GotCastles.resolveStructureFloorY(surfaceStats);
        class_2338 padOrigin = new class_2338(base.method_10263() - 4, floorY + 1, base.method_10260() - 4);
        GotCastles.prepareStructurePad(world, padOrigin, new class_2382(9, 1, 9), floorY, 2);
        GotCastles.stabilizeStructureFooting(world, padOrigin, new class_2382(9, 1, 9), floorY);
        base = new class_2338(base.method_10263(), floorY + 1, base.method_10260());
        if (!GotCastles.hasWeirwoodClearance(world, base)) {
            return null;
        }
        GotCastles.clearWeirwoodVolume(world, base, 8, 13);
        GotCastles.placeWeirwoodRootPath(world, base, new int[][]{{0, 0}, {0, -1}, {0, -2}, {-1, -3}, {-1, -4}, {-2, -5}});
        GotCastles.placeWeirwoodRootPath(world, base, new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, -1}, {4, -1}, {5, -2}});
        GotCastles.placeWeirwoodRootPath(world, base, new int[][]{{0, 0}, {-1, 0}, {-2, 1}, {-3, 1}, {-4, 2}, {-5, 2}});
        GotCastles.placeWeirwoodRootPath(world, base, new int[][]{{0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3}});
        GotCastles.placeWeirwoodRootPath(world, base, new int[][]{{0, 0}, {-1, 1}, {-2, 2}, {-3, 3}, {-4, 3}});
        GotCastles.placeWeirwoodRootPath(world, base, new int[][]{{0, 0}, {0, 1}, {1, 2}, {1, 3}, {2, 4}});
        GotCastles.placeWeirwoodRootPath(world, base, new int[][]{{0, 0}, {0, -1}, {1, -2}, {2, -3}, {3, -3}});
        class_2680 verticalLog = GotBlocks.WEIRWOOD_LOG.method_9564();
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dz = -1; dz <= 1; ++dz) {
                int surfaceY;
                int y;
                int x = base.method_10263() + dx;
                int z = base.method_10260() + dz;
                for (y = surfaceY = world.method_8624(class_2902.class_2903.field_13203, x, z) - 1; y < base.method_10264(); ++y) {
                    GotCastles.setWeirwoodBlock(world, new class_2338(x, y, z), verticalLog);
                }
                for (y = base.method_10264(); y <= base.method_10264() + 6; ++y) {
                    GotCastles.setWeirwoodBlock(world, new class_2338(x, y, z), verticalLog);
                }
            }
        }
        for (dy = 1; dy <= 4; ++dy) {
            GotCastles.setWeirwoodBlock(world, base.method_10069(2, dy, 0), verticalLog);
            GotCastles.setWeirwoodBlock(world, base.method_10069(-2, dy, 0), verticalLog);
            GotCastles.setWeirwoodBlock(world, base.method_10069(0, dy, 2), verticalLog);
        }
        GotCastles.setWeirwoodBlock(world, base.method_10069(0, 4, -2), verticalLog);
        GotCastles.setWeirwoodBlock(world, base.method_10069(0, 5, -2), verticalLog);
        for (dy = 3; dy <= 5; ++dy) {
            GotCastles.setWeirwoodBlock(world, base.method_10069(2, dy, 1), verticalLog);
            GotCastles.setWeirwoodBlock(world, base.method_10069(-2, dy, -1), verticalLog);
            GotCastles.setWeirwoodBlock(world, base.method_10069(1, dy, 2), verticalLog);
            GotCastles.setWeirwoodBlock(world, base.method_10069(-1, dy, -2), verticalLog);
        }
        for (int y = base.method_10264() + 5; y <= base.method_10264() + 7; ++y) {
            GotCastles.setWeirwoodBlock(world, new class_2338(base.method_10263(), y, base.method_10260()), verticalLog);
            GotCastles.setWeirwoodBlock(world, new class_2338(base.method_10263() + 1, y, base.method_10260()), verticalLog);
            GotCastles.setWeirwoodBlock(world, new class_2338(base.method_10263() - 1, y, base.method_10260()), verticalLog);
            GotCastles.setWeirwoodBlock(world, new class_2338(base.method_10263(), y, base.method_10260() + 1), verticalLog);
            GotCastles.setWeirwoodBlock(world, new class_2338(base.method_10263(), y, base.method_10260() - 1), verticalLog);
            if (y > base.method_10264() + 6) continue;
            GotCastles.setWeirwoodBlock(world, new class_2338(base.method_10263() + 1, y, base.method_10260() + 1), verticalLog);
            GotCastles.setWeirwoodBlock(world, new class_2338(base.method_10263() - 1, y, base.method_10260() - 1), verticalLog);
            GotCastles.setWeirwoodBlock(world, new class_2338(base.method_10263() + 1, y, base.method_10260() - 1), verticalLog);
            GotCastles.setWeirwoodBlock(world, new class_2338(base.method_10263() - 1, y, base.method_10260() + 1), verticalLog);
        }
        GotCastles.setWeirwoodBlock(world, new class_2338(base.method_10263(), base.method_10264() + 1, base.method_10260() - 1), GotBlocks.WEIRWOOD_LOG_FACE_BLOODY.method_9564());
        GotCastles.placeWeirwoodLimbPath(world, base, new int[][]{{0, 5, -1}, {0, 5, -2}, {0, 6, -3}, {-1, 6, -4}, {-1, 7, -5}});
        GotCastles.placeWeirwoodLimbPath(world, base, new int[][]{{1, 5, 0}, {2, 5, 0}, {3, 6, 0}, {4, 6, 1}, {5, 7, 1}});
        GotCastles.placeWeirwoodLimbPath(world, base, new int[][]{{-1, 5, 0}, {-2, 5, 0}, {-3, 6, -1}, {-4, 6, -1}, {-5, 7, -2}});
        GotCastles.placeWeirwoodLimbPath(world, base, new int[][]{{0, 4, 1}, {0, 5, 2}, {1, 5, 3}, {1, 6, 4}});
        GotCastles.placeWeirwoodLimbPath(world, base, new int[][]{{0, 4, -1}, {1, 5, -2}, {2, 5, -3}, {3, 6, -4}});
        GotCastles.placeWeirwoodLimbPath(world, base, new int[][]{{0, 4, 1}, {-1, 5, 2}, {-2, 5, 3}, {-3, 6, 4}});
        GotCastles.placeWeirwoodCanopyLayer(world, base, 4, 5.2, true);
        GotCastles.placeWeirwoodCanopyLayer(world, base, 5, 6.8, false);
        GotCastles.placeWeirwoodCanopyLayer(world, base, 6, 7.8, false);
        GotCastles.placeWeirwoodCanopyLayer(world, base, 7, 8.4, false);
        GotCastles.placeWeirwoodCanopyLayer(world, base, 8, 7.4, false);
        GotCastles.placeWeirwoodCanopyLayer(world, base, 9, 5.8, false);
        GotCastles.placeWeirwoodCanopyLayer(world, base, 10, 4.0, false);
        GotCastles.placeWeirwoodLeafCluster(world, base.method_10069(-2, 8, -5), 2);
        GotCastles.placeWeirwoodLeafCluster(world, base.method_10069(4, 8, 2), 2);
        GotCastles.placeWeirwoodLeafCluster(world, base.method_10069(-5, 8, 1), 2);
        GotCastles.placeWeirwoodLeafCluster(world, base.method_10069(1, 7, 5), 2);
        GotCastles.placeWeirwoodLeafCurtain(world, base.method_10069(7, 7, 0), 2);
        GotCastles.placeWeirwoodLeafCurtain(world, base.method_10069(-7, 7, 1), 2);
        GotCastles.placeWeirwoodLeafCurtain(world, base.method_10069(0, 6, 7), 2);
        GotCastles.placeWeirwoodLeafCurtain(world, base.method_10069(-1, 6, -7), 2);
        return base.method_10062();
    }

    @Nullable
    private static class_2338 placeWeirwoodTreeNear(class_3218 world, class_2338 predictedPos) {
        if (world == null || predictedPos == null) {
            return null;
        }
        class_2338 placed = GotCastles.placeWeirwoodTree(world, predictedPos);
        if (placed != null) {
            return placed;
        }
        for (int radius = 4; radius <= 32; radius += 4) {
            for (int offsetX = -radius; offsetX <= radius; offsetX += 4) {
                for (int offsetZ = -radius; offsetZ <= radius; offsetZ += 4) {
                    if (Math.abs(offsetX) != radius && Math.abs(offsetZ) != radius || (placed = GotCastles.placeWeirwoodTree(world, predictedPos.method_10069(offsetX, 0, offsetZ))) == null) continue;
                    return placed;
                }
            }
        }
        return null;
    }

    private static void clearWeirwoodVolume(class_3218 world, class_2338 base, int horizontalRadius, int height) {
        for (int x = base.method_10263() - horizontalRadius; x <= base.method_10263() + horizontalRadius; ++x) {
            for (int z = base.method_10260() - horizontalRadius; z <= base.method_10260() + horizontalRadius; ++z) {
                for (int y = base.method_10264() + 1; y <= base.method_10264() + height; ++y) {
                    class_2338 pos = new class_2338(x, y, z);
                    class_2680 state = world.method_8320(pos);
                    if (!GotCastles.canReplaceWeirwoodBlock(state) || state.method_26215()) continue;
                    world.method_8652(pos, class_2246.field_10124.method_9564(), 2);
                }
            }
        }
    }

    private static void placeWeirwoodRootPath(class_3218 world, class_2338 base, int[][] offsets) {
        for (int index = 0; index < offsets.length; ++index) {
            int dx = offsets[index][0];
            int dz = offsets[index][1];
            int x = base.method_10263() + dx;
            int z = base.method_10260() + dz;
            int surfaceY = world.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
            int y = Math.min(base.method_10264(), surfaceY);
            class_2350.class_2351 axis = Math.abs(dx) >= Math.abs(dz) ? class_2350.class_2351.field_11048 : class_2350.class_2351.field_11051;
            class_2680 state = (class_2680)GotBlocks.WEIRWOOD_LOG.method_9564().method_11657((class_2769)class_2465.field_11459, (Comparable)axis);
            GotCastles.setWeirwoodBlock(world, new class_2338(x, y, z), state);
            if (index != offsets.length - 1 && index < 2) continue;
            GotCastles.setWeirwoodBlock(world, new class_2338(x, y - 1, z), GotBlocks.WEIRWOOD_LOG.method_9564());
        }
    }

    private static void placeWeirwoodBranch(class_3218 world, class_2338 start, class_2350 direction, int length) {
        class_2680 state = (class_2680)GotBlocks.WEIRWOOD_LOG.method_9564().method_11657((class_2769)class_2465.field_11459, (Comparable)direction.method_10166());
        for (int step = 0; step < length; ++step) {
            class_2338 pos = start.method_10079(direction, step);
            GotCastles.setWeirwoodBlock(world, pos, state);
            if (step != length - 1) continue;
            GotCastles.setWeirwoodBlock(world, pos.method_10084(), GotBlocks.WEIRWOOD_LOG.method_9564());
        }
    }

    private static void placeWeirwoodLimbPath(class_3218 world, class_2338 base, int[][] offsets) {
        if (offsets.length == 0) {
            return;
        }
        class_2338 previous = base.method_10069(offsets[0][0], offsets[0][1], offsets[0][2]);
        GotCastles.setWeirwoodBlock(world, previous, GotBlocks.WEIRWOOD_LOG.method_9564());
        for (int index = 1; index < offsets.length; ++index) {
            class_2338 current = base.method_10069(offsets[index][0], offsets[index][1], offsets[index][2]);
            class_2350.class_2351 axis = GotCastles.dominantAxis(previous, current);
            GotCastles.setWeirwoodBlock(world, current, (class_2680)GotBlocks.WEIRWOOD_LOG.method_9564().method_11657((class_2769)class_2465.field_11459, (Comparable)axis));
            previous = current;
        }
    }

    private static class_2350.class_2351 dominantAxis(class_2338 from, class_2338 to) {
        int dx = Math.abs(to.method_10263() - from.method_10263());
        int dy = Math.abs(to.method_10264() - from.method_10264());
        int dz = Math.abs(to.method_10260() - from.method_10260());
        if (dy >= dx && dy >= dz) {
            return class_2350.class_2351.field_11052;
        }
        return dx >= dz ? class_2350.class_2351.field_11048 : class_2350.class_2351.field_11051;
    }

    private static void placeWeirwoodCanopyLayer(class_3218 world, class_2338 base, int yOffset, double radius, boolean rimOnly) {
        int ceilRadius = class_3532.method_15384((double)radius);
        for (int dx = -ceilRadius; dx <= ceilRadius; ++dx) {
            for (int dz = -ceilRadius; dz <= ceilRadius; ++dz) {
                double distance = Math.sqrt(dx * dx + dz * dz);
                if (distance > radius || rimOnly && distance < radius - 1.5 || !rimOnly && distance > radius - 0.35 && (Math.abs(dx) + Math.abs(dz) + yOffset) % 3 == 0 || Math.abs(dx) <= 1 && Math.abs(dz) <= 1 && yOffset <= 5) continue;
                GotCastles.placePersistentWeirwoodLeaf(world, base.method_10069(dx, yOffset, dz));
            }
        }
    }

    private static void placeWeirwoodLeafCluster(class_3218 world, class_2338 center, int radius) {
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dy = -1; dy <= 1; ++dy) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    double distance = Math.sqrt((double)(dx * dx + dz * dz) + (double)(dy * dy) * 1.6);
                    if (distance > (double)radius + 0.35) continue;
                    GotCastles.placePersistentWeirwoodLeaf(world, center.method_10069(dx, dy, dz));
                }
            }
        }
    }

    private static void placeWeirwoodLeafCurtain(class_3218 world, class_2338 start, int length) {
        for (int step = 0; step < length; ++step) {
            GotCastles.placePersistentWeirwoodLeaf(world, start.method_10087(step));
        }
    }

    private static void placePersistentWeirwoodLeaf(class_3218 world, class_2338 pos) {
        class_2680 current = world.method_8320(pos);
        if (!GotCastles.canReplaceWeirwoodBlock(current)) {
            return;
        }
        world.method_8652(pos, (class_2680)GotBlocks.WEIRWOOD_LEAVES.method_9564().method_11657((class_2769)class_2397.field_11200, (Comparable)Boolean.valueOf(true)), 2);
    }

    private static void setWeirwoodBlock(class_3218 world, class_2338 pos, class_2680 state) {
        class_2680 current = world.method_8320(pos);
        if (!GotCastles.canOverwriteWithWeirwoodWood(current)) {
            return;
        }
        world.method_8652(pos, state, 2);
    }

    private static boolean canOverwriteWithWeirwoodWood(class_2680 state) {
        return GotCastles.canReplaceWeirwoodBlock(state) || state.method_27852(class_2246.field_10219) || state.method_27852(class_2246.field_10566) || state.method_27852(class_2246.field_10253) || state.method_27852(class_2246.field_10520) || state.method_27852(class_2246.field_28685) || state.method_27852(class_2246.field_28681) || state.method_27852(class_2246.field_10255) || state.method_27852(class_2246.field_10340) || state.method_27852(class_2246.field_10491) || state.method_27852(GotBlocks.WEIRWOOD_LOG) || state.method_27852(GotBlocks.WEIRWOOD_LEAVES);
    }

    private static boolean canReplaceWeirwoodBlock(class_2680 state) {
        return state.method_26215() || state.method_26164(class_3481.field_15503) || state.method_26164(class_3481.field_15475) || state.method_26204() instanceof class_2261 || state.method_26204() instanceof class_2541 || state.method_27852(class_2246.field_10477) || state.method_27852(class_2246.field_10491) || state.method_27852(class_2246.field_10479) || state.method_27852(class_2246.field_10214) || state.method_27852(class_2246.field_10112) || state.method_27852(class_2246.field_10313) || state.method_27852(class_2246.field_10428);
    }

    private static boolean isNorthernWeirwoodBiomePath(String biomePath) {
        if (biomePath == null || biomePath.isBlank()) {
            return false;
        }
        if (biomePath.contains("always_winter") || biomePath.contains("wildling") || biomePath.contains("the_wall") || biomePath.contains("gift") || biomePath.contains("sea")) {
            return false;
        }
        return biomePath.contains("the_north") || biomePath.contains("wolfswood");
    }

    private static String biomePathAt(class_3218 world, class_2338 pos) {
        return world.method_23753(pos).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
    }

    private static long horizontalSquaredDistance(class_2338 first, class_2338 second) {
        long dx = (long)first.method_10263() - (long)second.method_10263();
        long dz = (long)first.method_10260() - (long)second.method_10260();
        return dx * dx + dz * dz;
    }

    private static class_1269 queuePlayerClaimCheck(class_1657 player, class_1937 world, class_1268 hand, class_3965 hitResult) {
        class_2338 placePos;
        CastleInstance claimAtPos;
        if (world.method_8608() || !(player instanceof class_3222)) {
            return class_1269.field_5811;
        }
        class_3222 serverPlayer = (class_3222)player;
        if (!world.method_27983().equals(GotDimensionKeys.ICEANDFIRE)) {
            return class_1269.field_5811;
        }
        if (!(world instanceof class_3218)) {
            return class_1269.field_5811;
        }
        class_3218 serverWorld = (class_3218)world;
        CastleWorldState castleState = GotCastles.getWorldState(serverWorld);
        CastleInstance clickedCastle = castleState.findByBannerPos(hitResult.method_17777());
        if (clickedCastle == null && world.method_8320(hitResult.method_17777()).method_26204() instanceof class_2185 && GotCastles.isValidClaimBanner(serverWorld, hitResult.method_17777()) && (claimAtPos = castleState.findClaimAt(hitResult.method_17777())) != null) {
            clickedCastle = GotCastles.repairClaimBannerReference(serverWorld, castleState, claimAtPos, hitResult.method_17777());
        }
        if (clickedCastle != null) {
            GotCastles.openCastleScreen(serverPlayer, clickedCastle.getId());
            return class_1269.field_5812;
        }
        VillageWorldState villageState = VillageWorldState.get(serverWorld);
        VillageInstance clickedVillage = villageState.findByBannerPos(hitResult.method_17777());
        if (clickedVillage == null && world.method_8320(hitResult.method_17777()).method_26204() instanceof class_2185 && GotCastles.isValidClaimBanner(serverWorld, hitResult.method_17777())) {
            clickedVillage = villageState.findClaimAt(hitResult.method_17777());
        }
        if (clickedVillage != null) {
            GotVillages.openVillageScreen(serverPlayer, clickedVillage.getId());
            return class_1269.field_5812;
        }
        class_2338 interactionPos = hitResult.method_17777();
        if (world.method_8320(interactionPos).method_27852(GotBlocks.WEIRWOOD_LOG_FACE_BLOODY)) {
            return class_1269.field_5811;
        }
        class_2338 class_23382 = placePos = world.method_8320(interactionPos).method_45474() ? interactionPos : interactionPos.method_10093(hitResult.method_17780());
        if (GotCastles.findReligiousSiteAt(serverWorld, placePos) != null && !serverPlayer.method_5687(2) && !GotCastles.isSingleplayerOwner(serverPlayer)) {
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"This Religious Site is protected.").method_27692(class_124.field_1061), true);
            return class_1269.field_5814;
        }
        if (GotCastles.isPlacementRestricted(serverPlayer, placePos, serverPlayer.method_5998(hand))) {
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"This land is claimed.").method_27692(class_124.field_1061), true);
            return class_1269.field_5814;
        }
        return class_1269.field_5811;
    }

    public static void openCastleScreen(class_3222 player, String castleId) {
        class_3218 serverWorld;
        block5: {
            block4: {
                class_1937 class_19372 = player.method_37908();
                if (!(class_19372 instanceof class_3218)) break block4;
                serverWorld = (class_3218)class_19372;
                if (castleId != null && !castleId.isBlank()) break block5;
            }
            return;
        }
        OpenCastleScreenPayload payload = GotCastles.buildCastleScreenPayload(serverWorld, player, castleId);
        if (payload != null) {
            ServerPlayNetworking.send((class_3222)player, (class_8710)payload);
        }
    }

    public static void openCastleStorage(class_3222 player, String castleId) {
        class_3218 world;
        block5: {
            block4: {
                class_1937 class_19372 = player.method_37908();
                if (!(class_19372 instanceof class_3218)) break block4;
                world = (class_3218)class_19372;
                if (castleId != null && !castleId.isBlank()) break block5;
            }
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null || !GotCastles.canAccessCastleStorage(player, world, castle)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You cannot access this castle storage.").method_27692(class_124.field_1061), true);
            return;
        }
        CastleStorageInventory inventory = new CastleStorageInventory(world, castle);
        class_747 factory = new class_747((syncId, playerInventory, ignored) -> switch (castle.getStorageRows()) {
            case 1 -> new class_1707(class_3917.field_18664, syncId, playerInventory, (class_1263)inventory, 1);
            case 2 -> new class_1707(class_3917.field_18665, syncId, playerInventory, (class_1263)inventory, 2);
            case 3 -> new class_1707(class_3917.field_17326, syncId, playerInventory, (class_1263)inventory, 3);
            default -> new class_1707(class_3917.field_18666, syncId, playerInventory, (class_1263)inventory, 4);
        }, (class_2561)class_2561.method_43470((String)(GotCastles.displayNameForCastle(castle) + " Storage")));
        player.method_17355((class_3908)factory);
    }

    public static boolean canAccessCastleStorage(class_3222 player, class_3218 world, CastleInstance castle) {
        if (player == null || world == null || castle == null) {
            return false;
        }
        return GotCastles.canModifyCastleClaim(player, castle);
    }

    public static boolean canPlaceVehicleInCastleClaim(class_3222 player, class_3218 world, class_2338 pos) {
        if (player == null || world == null || pos == null) {
            return false;
        }
        if (player.method_5687(2)) {
            return true;
        }
        CastleInstance claim = GotCastles.getWorldState(world).findClaimAt(pos);
        return claim == null || GotCastles.canModifyCastleClaim(player, claim);
    }

    public static class_1269 handleWeirwoodBloodyFaceUse(class_3222 player, class_2338 pos) {
        class_1937 class_19372;
        if (player == null || pos == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return class_1269.field_5811;
        }
        class_3218 world = (class_3218)class_19372;
        WeirwoodTreeWorldState.Entry site = GotCastles.findReligiousSiteAt(world, pos);
        if (site == null || !pos.equals((Object)GotCastles.weirwoodFacePos(site.pos()))) {
            return class_1269.field_5811;
        }
        if (!GotCastles.canAccessWeirwoodBloodyFace(player)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Only followers of the Old Gods may pray here.").method_27692(class_124.field_1061), true);
            return class_1269.field_21466;
        }
        class_52 lootTable = world.method_8503().method_58576().method_58295(class_5321.method_29179((class_5321)class_7924.field_50079, (class_2960)WEIRWOOD_FACE_LOOT_TABLE_ID));
        class_8567 parameters = new class_8567.class_8568(world).method_51874(class_181.field_24424, (Object)class_243.method_24953((class_2382)pos)).method_51874(class_181.field_1226, (Object)player).method_51871(player.method_7292()).method_51875(class_173.field_1179);
        for (class_1799 stack : lootTable.method_51878(parameters)) {
            player.method_31548().method_7398(stack);
        }
        world.method_8652(pos, GotCastles.copyWeirwoodFaceState(world.method_8320(pos), GotBlocks.WEIRWOOD_LOG_FACE.method_9564()), 3);
        WeirwoodTreeWorldState.get(world).setNextBleedAttemptTick(site.castleId(), world.method_8510() + 2400L);
        return class_1269.field_21466;
    }

    private static boolean canAccessWeirwoodBloodyFace(class_3222 player) {
        if (player == null) {
            return false;
        }
        if (player.method_5687(2) || GotCastles.isSingleplayerOwner(player)) {
            return true;
        }
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        return profile != null && "the_old_gods".equals(profile.getSelectedReligionId());
    }

    @Nullable
    private static WeirwoodTreeWorldState.Entry findReligiousSiteAt(class_3218 world, class_2338 pos) {
        if (world == null || pos == null) {
            return null;
        }
        WeirwoodTreeWorldState state = WeirwoodTreeWorldState.get(world);
        for (WeirwoodTreeWorldState.Entry entry : state.getAll()) {
            class_2338 base = entry.pos();
            if (Math.abs(pos.method_10263() - base.method_10263()) > 1 || Math.abs(pos.method_10260() - base.method_10260()) > 1) continue;
            return entry;
        }
        class_2680 stateAtPos = world.method_8320(pos);
        if (stateAtPos.method_27852(GotBlocks.WEIRWOOD_LOG_FACE) || stateAtPos.method_27852(GotBlocks.WEIRWOOD_LOG_FACE_BLOODY)) {
            class_2338 derivedBase = pos.method_10072().method_10074();
            for (WeirwoodTreeWorldState.Entry entry : state.getAll()) {
                class_2338 base = entry.pos();
                if (Math.abs(derivedBase.method_10263() - base.method_10263()) > 3 || Math.abs(derivedBase.method_10264() - base.method_10264()) > 2 || Math.abs(derivedBase.method_10260() - base.method_10260()) > 3) continue;
                return entry;
            }
        }
        return null;
    }

    private static class_2338 weirwoodFacePos(class_2338 base) {
        return base.method_10069(0, 1, -1);
    }

    private static class_2680 copyWeirwoodFaceState(class_2680 source, class_2680 target) {
        return source.method_28498((class_2769)class_2741.field_12481) && target.method_28498((class_2769)class_2741.field_12481) ? (class_2680)target.method_11657((class_2769)class_2741.field_12481, (Comparable)((class_2350)source.method_11654((class_2769)class_2741.field_12481))) : target;
    }

    private static boolean isSingleplayerOwner(class_3222 player) {
        return player != null && player.method_5682() != null && player.method_5682().method_3724();
    }

    public static void handleCastleInvasionRequest(class_3222 player, String castleId, CastleInvasionMode mode) {
        String playerReligion;
        String playerKingdom;
        class_3218 world;
        class_1937 class_19372 = player.method_37908();
        if (!(class_19372 instanceof class_3218) || !(world = (class_3218)class_19372).method_27983().equals(GotDimensionKeys.ICEANDFIRE)) {
            return;
        }
        if (castleId == null || castleId.isBlank() || mode == null) {
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            return;
        }
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        ReputationTitle title = ((PlayerReputationHolder)player).got$getReputationProfile().getTitle();
        if (!GotCastles.canStartInvasion(player, castle, mode, title, playerKingdom = character.getSelectedKingdomId(), playerReligion = character.getSelectedReligionId())) {
            return;
        }
        if (!KingdomWarService.ensureWarForPlayerInvasion(world, player, playerKingdom, castle.getOwnerKingdomId(), title)) {
            return;
        }
        long durationTicks = GotCastles.durationTicksFor(mode);
        ActiveCastleInvasion invasion = new ActiveCastleInvasion((class_5321<class_1937>)world.method_27983(), player.method_5667(), player.method_7334().getName(), playerKingdom, GotCastles.resolveOwnedHouseId(character.getSelectedOptionId(), playerKingdom, ""), castle.getOwnerKingdomId(), castleId, mode, world.method_8510() + durationTicks, durationTicks);
        invasion.bossBar().method_14088(player);
        invasion.updateBossBar(durationTicks);
        ACTIVE_CASTLE_INVASIONS.put(player.method_5667(), invasion);
        GotCastles.appendPlayerInvasionAttemptLog(world, invasion, castle);
        GotCastles.clearPendingNpcRespawns(castleId);
        GotCastles.markCastlePopulationHandledForToday(world, castleId);
        castle.setRaidState(CastleRaidState.CONTESTED);
        castle.setRaidEndsAtTick(world.method_8510() + durationTicks);
        GotCastles.setCastleGatesInvasionMode(world, castle.getId(), true);
        GotCastles.markCastleNpcGlowing(world, castle.getId(), true);
        GotCastles.alertCastleToPlayer(world, castle, player);
        if (mode == CastleInvasionMode.CONQUER || mode == CastleInvasionMode.CHALLENGE) {
            GotCastles.setConquerCastleBuffs(world, castle.getId(), true);
        }
        if (mode == CastleInvasionMode.CONQUER && GotCastles.isCastleUncaptured(castle) && GotCastles.countLivingConquerTargets(world, castle.getId()) <= 0) {
            invasion.setAwaitingBannerBreak(true);
            player.method_7353((class_2561)class_2561.method_43470((String)"The castle is uncaptured. Break the banner to conquer it.").method_27692(class_124.field_1065), false);
        }
        GotTroops.forceSummonTroops(player);
        ServerPlayNetworking.send((class_3222)player, (class_8710)new CastleInvasionStatePayload(true, castleId, mode.getId(), GotCastles.objectiveTextFor(world, invasion)));
        GotCastles.syncStateToPlayers(world);
        GotCastles.checkCastleInvasionObjectiveProgress(world, invasion);
    }

    private static long durationTicksFor(CastleInvasionMode mode) {
        return switch (mode) {
            default -> throw new MatchException(null, null);
            case CastleInvasionMode.CRUSADE -> 6000L;
            case CastleInvasionMode.RAID -> 3600L;
            case CastleInvasionMode.CONQUER, CastleInvasionMode.CHALLENGE -> 7200L;
        };
    }

    private static String displayNameFor(CastleInvasionMode mode) {
        return switch (mode) {
            default -> throw new MatchException(null, null);
            case CastleInvasionMode.CRUSADE -> "Crusade";
            case CastleInvasionMode.RAID -> "Raid";
            case CastleInvasionMode.CHALLENGE -> "Challenge";
            case CastleInvasionMode.CONQUER -> "Conquer";
        };
    }

    static String formatRemainingTime(long remainingTicks) {
        int totalSeconds = Math.max(0, class_3532.method_15386((float)((float)remainingTicks / 20.0f)));
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return minutes + "m " + seconds + "s";
    }

    private static boolean canStartInvasion(class_3222 player, CastleInstance castle, CastleInvasionMode mode, ReputationTitle title, String playerKingdom, String playerReligion) {
        if (ACTIVE_CASTLE_INVASIONS.containsKey(player.method_5667())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Finish your current invasion first.").method_27692(class_124.field_1061), true);
            return false;
        }
        if (GotCastles.findActiveInvasionForCastle(castle.getId()) != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This castle is already contested.").method_27692(class_124.field_1061), true);
            return false;
        }
        CastleRecaptureState recapture = GotCastles.getWorldState(player.method_51469()).getRecapture(castle.getId());
        if (recapture != null && recapture.getStage() != CastleRecaptureStage.DORMANT) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This castle is already under recapture pressure.").method_27692(class_124.field_1061), true);
            return false;
        }
        if (GotCastles.castleCooldownSeconds(player.method_51469(), castle) > 0) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This castle was attacked recently.").method_27692(class_124.field_1061), true);
            return false;
        }
        if (GotCastles.getWorldState(player.method_51469()).findClaimAt(player.method_24515()) != null || VillageWorldState.get(player.method_51469()).findClaimAt(player.method_24515()) != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Start invasions from outside castle or village claims.").method_27692(class_124.field_1061), true);
            return false;
        }
        boolean canBypassTitleRequirement = PledgeQuestService.canBypassTitleRequirement(player, mode);
        return switch (mode) {
            default -> throw new MatchException(null, null);
            case CastleInvasionMode.CRUSADE -> {
                if (!title.canPerformInvasion(mode) && !canBypassTitleRequirement) {
                    player.method_7353((class_2561)class_2561.method_43470((String)"This title cannot Crusade.").method_27692(class_124.field_1061), true);
                    yield false;
                }
                if (playerReligion.isBlank() || playerReligion.equals(castle.getOwnerReligionId())) {
                    player.method_7353((class_2561)class_2561.method_43470((String)"Crusade needs a different faith.").method_27692(class_124.field_1061), true);
                    yield false;
                }
                yield true;
            }
            case CastleInvasionMode.RAID -> {
                if (!title.canPerformInvasion(mode) && !canBypassTitleRequirement) {
                    player.method_7353((class_2561)class_2561.method_43470((String)"This title cannot Raid.").method_27692(class_124.field_1061), true);
                    yield false;
                }
                yield true;
            }
            case CastleInvasionMode.CHALLENGE -> {
                if (!title.canPerformInvasion(mode) && !canBypassTitleRequirement) {
                    player.method_7353((class_2561)class_2561.method_43470((String)"This title cannot Conquer.").method_27692(class_124.field_1061), true);
                    yield false;
                }
                if (playerKingdom.isBlank() || !playerKingdom.equals(castle.getOwnerKingdomId())) {
                    player.method_7353((class_2561)class_2561.method_43470((String)"Challenge needs your own kingdom.").method_27692(class_124.field_1061), true);
                    yield false;
                }
                if (!GotCastles.canAcquireConqueredCastle(player.method_51469(), player, castle.getTier(), castle.getId())) {
                    yield false;
                }
                yield true;
            }
            case CastleInvasionMode.CONQUER -> {
                if (!title.canPerformInvasion(mode) && !canBypassTitleRequirement) {
                    player.method_7353((class_2561)class_2561.method_43470((String)"This title cannot Conquer.").method_27692(class_124.field_1061), true);
                    yield false;
                }
                if (playerKingdom.isBlank()) {
                    player.method_7353((class_2561)class_2561.method_43470((String)"Conquer needs a kingdom.").method_27692(class_124.field_1061), true);
                    yield false;
                }
                if (!GotCastles.isCastleUncaptured(castle) && playerKingdom.equals(castle.getOwnerKingdomId())) {
                    player.method_7353((class_2561)class_2561.method_43470((String)"Conquer needs a foreign kingdom.").method_27692(class_124.field_1061), true);
                    yield false;
                }
                yield GotCastles.canAcquireConqueredCastle(player.method_51469(), player, castle.getTier(), castle.getId());
            }
        };
    }

    private static boolean canStartKingdomAiInvasion(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle) {
        return GotCastles.canStartKingdomAiInvasion(world, invadingCastle, targetCastle, false);
    }

    private static boolean canStartKingdomAiInvasion(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle, boolean relaxedForWarOpening) {
        return GotCastles.canStartKingdomAiInvasion(world, invadingCastle, targetCastle, relaxedForWarOpening, null);
    }

    private static boolean canStartKingdomAiInvasion(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle, boolean relaxedForWarOpening, @Nullable StrategicCastleSnapshotSet strategicCastles) {
        class_3222 defendingPlayer;
        int partyCapacity;
        KingdomAiInvasionWorldState invasionState;
        if (world == null || invadingCastle == null || targetCastle == null) {
            return false;
        }
        if (relaxedForWarOpening ? !GotCastles.isWarOpeningAiInvader(invadingCastle) : !GotCastles.isEligibleAiInvader(invadingCastle)) {
            return false;
        }
        if (GotCastles.realCastleIdForStrategicId(invadingCastle.castleId()).equals(targetCastle.castleId())) {
            return false;
        }
        if (GotCastles.findActiveInvasionForCastle(targetCastle.castleId()) != null) {
            return false;
        }
        if (KingdomAiInvasionWorldState.get(world).get(targetCastle.castleId()) != null) {
            return false;
        }
        CastleRecaptureState recapture = GotCastles.getWorldState(world).getRecapture(targetCastle.castleId());
        if (recapture != null && recapture.getStage() != CastleRecaptureStage.DORMANT) {
            return false;
        }
        if (!relaxedForWarOpening && GotCastles.strategicCastleCooldownSeconds(world, targetCastle) > 0) {
            return false;
        }
        String attackerKingdomId = GotCastles.canonicalKingdomId(invadingCastle.ownerKingdomId());
        String defenderKingdomId = GotCastles.canonicalKingdomId(targetCastle.ownerKingdomId());
        if (attackerKingdomId.isBlank() || defenderKingdomId.isBlank() || attackerKingdomId.equals(defenderKingdomId)) {
            return false;
        }
        if (GotCastles.requiresCrossContinentHarbourTarget(world, invadingCastle, targetCastle, strategicCastles) && !targetCastle.harbourCastle()) {
            return false;
        }
        if (GotCastles.isCastlelessOriginAttack(invadingCastle) && !GotCastles.canCastlelessOriginTarget(attackerKingdomId, targetCastle)) {
            return false;
        }
        long today = GotGameRules.trackedDay(world);
        if (today < (invasionState = KingdomAiInvasionWorldState.get(world)).getNextInvasionDayForCastle(invadingCastle.castleId())) {
            return false;
        }
        int n = partyCapacity = strategicCastles == null ? GotCastles.lordPartyCapacity(world, attackerKingdomId, invadingCastle.lordName(), invadingCastle.castleId()) : GotCastles.lordPartyCapacity(strategicCastles, attackerKingdomId, invadingCastle.lordName(), invadingCastle.castleId());
        if (invasionState.activeInvasionsForLord(GotCastles.lordInvasionCooldownKey(attackerKingdomId, invadingCastle.lordName())) >= partyCapacity) {
            return false;
        }
        if (!KingdomWarService.isAtWar(world, attackerKingdomId, defenderKingdomId)) {
            return false;
        }
        class_3222 class_32222 = defendingPlayer = targetCastle.ownerPlayerUuid() == null ? null : world.method_8503().method_3760().method_14602(targetCastle.ownerPlayerUuid());
        return relaxedForWarOpening || targetCastle.ownerPlayerUuid() == null || defendingPlayer != null;
    }

    public static boolean canCastlelessOriginTarget(String attackerKingdomId, StrategicCastleSnapshot targetCastle) {
        if (targetCastle == null) {
            return false;
        }
        return targetCastle.harbourCastle() || GotCastles.isOriginalCastleForKingdom(targetCastle.castleId(), attackerKingdomId);
    }

    public static boolean hasActiveKingdomAiWarParty(class_3218 world, String firstKingdomId, String secondKingdomId) {
        if (world == null) {
            return false;
        }
        String first = GotCastles.canonicalKingdomId(firstKingdomId);
        String second = GotCastles.canonicalKingdomId(secondKingdomId);
        for (KingdomAiInvasionWorldState.KingdomAiInvasion invasion : KingdomAiInvasionWorldState.get(world).getAll()) {
            String attacker = GotCastles.canonicalKingdomId(invasion.attackerKingdomId());
            String defender = GotCastles.canonicalKingdomId(invasion.defenderKingdomId());
            if ((!first.equals(attacker) || !second.equals(defender)) && (!first.equals(defender) || !second.equals(attacker))) continue;
            return true;
        }
        return false;
    }

    private static boolean isCastlelessOriginAttack(StrategicCastleSnapshot invadingCastle) {
        return invadingCastle != null && GotCastles.isCastlelessOriginId(invadingCastle.castleId());
    }

    private static boolean isOriginalCastleForKingdom(String castleId, String kingdomId) {
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        if (castleId == null || castleId.isBlank() || canonicalKingdomId.isBlank()) {
            return false;
        }
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(castleId);
        return metadata != null && canonicalKingdomId.equals(GotCastles.canonicalKingdomId(metadata.kingdomId()));
    }

    private static boolean isEligibleAiInvader(StrategicCastleSnapshot castle) {
        return castle != null && !castle.playerClaim() && castle.ownerPlayerUuid() == null && !castle.ownerKingdomId().isBlank() && !castle.lordName().isBlank();
    }

    private static boolean isWarOpeningAiInvader(StrategicCastleSnapshot castle) {
        return castle != null && !castle.playerClaim() && castle.ownerPlayerUuid() == null && !castle.ownerKingdomId().isBlank();
    }

    private static String lordInvasionCooldownKey(String kingdomId, String lordName) {
        String normalizedLordName;
        String normalizedKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String string = normalizedLordName = lordName == null ? "" : lordName.trim().toLowerCase(Locale.ROOT);
        if (normalizedKingdomId.isBlank() || normalizedLordName.isBlank()) {
            return "";
        }
        return normalizedKingdomId + "|" + normalizedLordName;
    }

    @Nullable
    private static class_3222 defendingPlayerFor(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null || castle.getOwnerPlayerUuid() == null) {
            return null;
        }
        return world.method_8503().method_3760().method_14602(castle.getOwnerPlayerUuid());
    }

    private static List<class_3222> playersInCastleClaim(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null) {
            return List.of();
        }
        ArrayList<class_3222> players = new ArrayList<class_3222>();
        for (class_3222 player : world.method_18456()) {
            if (!player.method_5805() || player.method_37908() != world || !GotCastles.isWithinCastleClaim(world, castle.getId(), player.method_24515())) continue;
            players.add(player);
        }
        return players;
    }

    private static KingdomAiBattleSide resolvePlayerSideForKingdomAiInvasion(class_3218 world, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, StrategicCastleSnapshot targetCastle, class_3222 player) {
        if (world == null || invasion == null || targetCastle == null || player == null) {
            return KingdomAiBattleSide.DEFENDER;
        }
        String playerKingdomId = GotCastles.canonicalKingdomId(((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedKingdomId());
        if (playerKingdomId.isBlank()) {
            return KingdomAiBattleSide.DEFENDER;
        }
        if (playerKingdomId.equals(GotCastles.canonicalKingdomId(invasion.attackerKingdomId()))) {
            return KingdomAiBattleSide.ATTACKER;
        }
        if (playerKingdomId.equals(GotCastles.canonicalKingdomId(targetCastle.ownerKingdomId()))) {
            return KingdomAiBattleSide.DEFENDER;
        }
        return KingdomWarService.isAtWar(world, playerKingdomId, GotCastles.canonicalKingdomId(targetCastle.ownerKingdomId())) ? KingdomAiBattleSide.ATTACKER : KingdomAiBattleSide.DEFENDER;
    }

    @Nullable
    public static class_1309 findBattleTargetForTroop(class_3218 world, PartyTroopEntity troop) {
        if (world == null || troop == null || !troop.method_5805()) {
            return null;
        }
        CastleInstance battleCastle = GotCastles.resolveBattleCastleForTroop(world, troop);
        if (battleCastle == null) {
            return null;
        }
        CastleBattleParticipantSide side = GotCastles.resolveBattleSide(world, battleCastle.getId(), (class_1309)troop);
        if (side == CastleBattleParticipantSide.NONE) {
            return null;
        }
        return GotCastles.findNearestBattleTarget(world, battleCastle.getId(), (class_1309)troop, side, 14.0);
    }

    @Nullable
    public static class_1309 findBattleTargetForMarriageCompanion(class_3218 world, AbstractKingdomNpcEntity npc) {
        if (world == null || npc == null || !npc.method_5805() || !npc.isMarriagePartyCompanion()) {
            return null;
        }
        CastleInstance battleCastle = GotCastles.resolveBattleCastleForMarriageCompanion(world, npc);
        if (battleCastle == null) {
            return null;
        }
        CastleBattleParticipantSide side = GotCastles.resolveBattleSide(world, battleCastle.getId(), (class_1309)npc);
        if (side == CastleBattleParticipantSide.NONE) {
            return null;
        }
        return GotCastles.findNearestBattleTarget(world, battleCastle.getId(), (class_1309)npc, side, 14.0);
    }

    @Nullable
    private static CastleInstance resolveBattleCastleForTroop(class_3218 world, PartyTroopEntity troop) {
        ActiveCastleInvasion invasion;
        if (world == null || troop == null) {
            return null;
        }
        if (troop.getOwnerUuid() != null && (invasion = ACTIVE_CASTLE_INVASIONS.get(troop.getOwnerUuid())) != null) {
            return GotCastles.getWorldState(world).get(invasion.castleId());
        }
        CastleInstance castle = GotCastles.getWorldState(world).findClaimAt(troop.method_24515());
        if (castle == null) {
            return null;
        }
        if (GotCastles.isLiveRecaptureBattle(world, castle.getId()) || GotCastles.isLiveKingdomAiBattle(world, castle.getId())) {
            return castle;
        }
        return null;
    }

    @Nullable
    private static CastleInstance resolveBattleCastleForMarriageCompanion(class_3218 world, AbstractKingdomNpcEntity npc) {
        CastleInstance castle;
        if (world == null || npc == null) {
            return null;
        }
        if (!npc.getMarriedPlayerUuid().isBlank()) {
            try {
                UUID ownerUuid = UUID.fromString(npc.getMarriedPlayerUuid());
                ActiveCastleInvasion invasion = ACTIVE_CASTLE_INVASIONS.get(ownerUuid);
                if (invasion != null) {
                    return GotCastles.getWorldState(world).get(invasion.castleId());
                }
            }
            catch (IllegalArgumentException ownerUuid) {
                // empty catch block
            }
        }
        if ((castle = GotCastles.getWorldState(world).findClaimAt(npc.method_24515())) == null) {
            return null;
        }
        if (GotCastles.isLiveRecaptureBattle(world, castle.getId()) || GotCastles.isLiveKingdomAiBattle(world, castle.getId())) {
            return castle;
        }
        return null;
    }

    @Nullable
    private static class_1309 findNearestBattleTarget(class_3218 world, String castleId, class_1309 source, CastleBattleParticipantSide sourceSide, double horizontalRange) {
        CastleBattleParticipantSide targetSide = sourceSide == CastleBattleParticipantSide.ATTACKER ? CastleBattleParticipantSide.DEFENDER : CastleBattleParticipantSide.ATTACKER;
        class_238 searchBox = source.method_5829().method_1009(horizontalRange, 8.0, horizontalRange);
        return world.method_8390(class_1309.class, searchBox, entity -> entity.method_5805() && entity != source && GotCastles.isBattleTargetCandidate(entity) && GotCastles.canBattleSee(source, entity) && !GotCastles.isGateBlockingCombat(world, source.method_33571(), entity.method_33571()) && GotCastles.resolveBattleSide(world, castleId, entity) == targetSide).stream().min(Comparator.comparingInt(GotCastles::battleTargetPriority).thenComparingDouble(arg_0 -> ((class_1309)source).method_5858(arg_0))).orElse(null);
    }

    @Nullable
    private static class_1309 findNearestBattleParticipantOnSide(class_3218 world, String castleId, class_1309 source, CastleBattleParticipantSide side, double horizontalRange) {
        class_243 pos = source.method_19538();
        class_238 searchBox = class_238.method_30048((class_243)pos, (double)(horizontalRange * 2.0), (double)24.0, (double)(horizontalRange * 2.0));
        return world.method_8390(class_1309.class, searchBox, entity -> entity.method_5805() && entity != source && GotCastles.isBattleTargetCandidate(entity) && GotCastles.canBattleSee(source, entity) && !GotCastles.isGateBlockingCombat(world, pos.method_1031(0.0, 1.0, 0.0), entity.method_33571()) && GotCastles.resolveBattleSide(world, castleId, entity) == side).stream().min(Comparator.comparingInt(GotCastles::battleTargetPriority).thenComparingDouble(entity -> entity.method_19538().method_1025(pos))).orElse(null);
    }

    private static boolean canBattleSee(class_1309 source, class_1309 target) {
        return source != null && target != null && source.method_6057((class_1297)target);
    }

    private static boolean isBattleTargetCandidate(class_1309 entity) {
        return !(entity instanceof SumpterEntity) && !(entity instanceof class_1496) && !(entity instanceof class_1531);
    }

    private static boolean isGateBlockingCombat(class_3218 world, class_243 from, class_243 to) {
        if (world == null || from == null || to == null) {
            return false;
        }
        class_243 delta = to.method_1020(from);
        double length = delta.method_1033();
        if (length < 1.0E-4) {
            return false;
        }
        class_243 step = delta.method_1029().method_1021(0.4);
        class_243 cursor = from;
        for (double traveled = 0.0; traveled <= length; traveled += 0.4) {
            class_2338 pos = class_2338.method_49638((class_2374)cursor);
            class_2680 state = world.method_8320(pos);
            if (state.method_27852(GotBlocks.GATE_FENCE)) {
                return true;
            }
            if (state.method_26204() instanceof GateMechanismBlock && !GateMechanismBlock.isPassable(state)) {
                return true;
            }
            cursor = cursor.method_1019(step);
        }
        return false;
    }

    private static int battleTargetPriority(class_1309 entity) {
        if (entity instanceof AbstractKingdomGuardNpcEntity || entity instanceof AbstractKingdomLordNpcEntity) {
            return 0;
        }
        if (entity instanceof PartyTroopEntity) {
            return 1;
        }
        if (entity instanceof class_3222) {
            return 2;
        }
        if (entity instanceof AbstractKingdomNpcEntity) {
            return 3;
        }
        return 4;
    }

    private static boolean isLiveRecaptureBattle(class_3218 world, String castleId) {
        CastleRecaptureState recapture = GotCastles.getWorldState(world).getRecapture(castleId);
        return recapture != null && recapture.getStage() == CastleRecaptureStage.INVASION;
    }

    private static boolean isLiveKingdomAiBattle(class_3218 world, String castleId) {
        KingdomAiInvasionWorldState.KingdomAiInvasion invasion = KingdomAiInvasionWorldState.get(world).get(castleId);
        return invasion != null && invasion.liveBattleStarted();
    }

    private static CastleBattleParticipantSide resolveBattleSide(class_3218 world, String castleId, class_1309 entity) {
        if (world == null || castleId == null || castleId.isBlank() || entity == null) {
            return CastleBattleParticipantSide.NONE;
        }
        ActiveCastleInvasion activeInvasion = GotCastles.findActiveInvasionForCastle(castleId);
        if (activeInvasion != null) {
            AbstractKingdomNpcEntity npc;
            class_3222 player;
            if (entity instanceof class_3222 && (player = (class_3222)entity).method_5667().equals(activeInvasion.playerUuid())) {
                return CastleBattleParticipantSide.ATTACKER;
            }
            if (entity instanceof PartyTroopEntity) {
                PartyTroopEntity troop = (PartyTroopEntity)entity;
                if (activeInvasion.playerUuid().equals(troop.getOwnerUuid())) {
                    return CastleBattleParticipantSide.ATTACKER;
                }
            }
            if (entity instanceof AbstractKingdomNpcEntity && (npc = (AbstractKingdomNpcEntity)entity).isMarriagePartyCompanion()) {
                try {
                    if (activeInvasion.playerUuid().equals(UUID.fromString(npc.getMarriedPlayerUuid()))) {
                        return CastleBattleParticipantSide.ATTACKER;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    // empty catch block
                }
            }
            if (entity instanceof AbstractKingdomNpcEntity && castleId.equals((npc = (AbstractKingdomNpcEntity)entity).got$getCastle())) {
                return CastleBattleParticipantSide.DEFENDER;
            }
            return CastleBattleParticipantSide.NONE;
        }
        CastleRecaptureState recapture = GotCastles.getWorldState(world).getRecapture(castleId);
        if (recapture != null && recapture.getStage() == CastleRecaptureStage.INVASION) {
            AbstractKingdomNpcEntity npc;
            class_3222 player;
            if (recapture.isAttacker(entity.method_5667())) {
                return CastleBattleParticipantSide.ATTACKER;
            }
            if (entity instanceof class_3222 && (player = (class_3222)entity).method_5667().equals(recapture.getPlayerUuid())) {
                return CastleBattleParticipantSide.DEFENDER;
            }
            if (entity instanceof PartyTroopEntity) {
                PartyTroopEntity troop = (PartyTroopEntity)entity;
                if (recapture.getPlayerUuid() != null && recapture.getPlayerUuid().equals(troop.getOwnerUuid())) {
                    return CastleBattleParticipantSide.DEFENDER;
                }
            }
            if (entity instanceof AbstractKingdomNpcEntity && (npc = (AbstractKingdomNpcEntity)entity).isMarriagePartyCompanion()) {
                try {
                    if (recapture.getPlayerUuid() != null && recapture.getPlayerUuid().equals(UUID.fromString(npc.getMarriedPlayerUuid()))) {
                        return CastleBattleParticipantSide.DEFENDER;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    // empty catch block
                }
            }
            if (entity instanceof AbstractKingdomNpcEntity && castleId.equals((npc = (AbstractKingdomNpcEntity)entity).got$getCastle())) {
                return CastleBattleParticipantSide.DEFENDER;
            }
            return CastleBattleParticipantSide.NONE;
        }
        KingdomAiInvasionWorldState.KingdomAiInvasion aiInvasion = KingdomAiInvasionWorldState.get(world).get(castleId);
        if (aiInvasion != null && aiInvasion.liveBattleStarted()) {
            AbstractKingdomNpcEntity npc;
            class_3222 owner;
            PartyTroopEntity troop;
            if (aiInvasion.attackerUuids().contains(entity.method_5667())) {
                return CastleBattleParticipantSide.ATTACKER;
            }
            StrategicCastleSnapshot targetCastle = GotCastles.resolveStrategicCastle(world, castleId);
            if (entity instanceof class_3222) {
                class_3222 player = (class_3222)entity;
                if (targetCastle != null) {
                    return GotCastles.resolvePlayerSideForKingdomAiInvasion(world, aiInvasion, targetCastle, player) == KingdomAiBattleSide.ATTACKER ? CastleBattleParticipantSide.ATTACKER : CastleBattleParticipantSide.DEFENDER;
                }
            }
            if (entity instanceof PartyTroopEntity && (troop = (PartyTroopEntity)entity).getOwnerUuid() != null && targetCastle != null && (owner = world.method_8503().method_3760().method_14602(troop.getOwnerUuid())) != null) {
                return GotCastles.resolvePlayerSideForKingdomAiInvasion(world, aiInvasion, targetCastle, owner) == KingdomAiBattleSide.ATTACKER ? CastleBattleParticipantSide.ATTACKER : CastleBattleParticipantSide.DEFENDER;
            }
            if (entity instanceof AbstractKingdomNpcEntity && (npc = (AbstractKingdomNpcEntity)entity).isMarriagePartyCompanion() && targetCastle != null && (owner = npc.resolveMarriedPlayer(world)) != null) {
                return GotCastles.resolvePlayerSideForKingdomAiInvasion(world, aiInvasion, targetCastle, owner) == KingdomAiBattleSide.ATTACKER ? CastleBattleParticipantSide.ATTACKER : CastleBattleParticipantSide.DEFENDER;
            }
            if (entity instanceof AbstractKingdomNpcEntity && castleId.equals((npc = (AbstractKingdomNpcEntity)entity).got$getCastle())) {
                return CastleBattleParticipantSide.DEFENDER;
            }
        }
        return CastleBattleParticipantSide.NONE;
    }

    @Nullable
    private static StrategicCastleSnapshot findKingdomAiTargetCastle(class_3218 world, StrategicCastleSnapshot invadingCastle) {
        return GotCastles.findKingdomAiTargetCastle(world, invadingCastle, GotCastles.snapshotStrategicCastles(world));
    }

    @Nullable
    private static StrategicCastleSnapshot findKingdomAiTargetCastle(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshotSet strategicCastles) {
        List harbourCandidates;
        boolean seaProjection;
        List<StrategicCastleSnapshot> candidates = new ArrayList<StrategicCastleSnapshot>();
        String attackerKingdomId = GotCastles.canonicalKingdomId(invadingCastle.ownerKingdomId());
        CastleWorldState state = GotCastles.getWorldState(world);
        for (OverlayCastleSite site : overlaySites) {
            StrategicCastleSnapshot targetCastle;
            if (state.isRejected(site.castleId()) || (targetCastle = strategicCastles.resolve(site.castleId())) == null || !GotCastles.canStartKingdomAiInvasion(world, invadingCastle, targetCastle, false, strategicCastles)) continue;
            candidates.add(targetCastle);
        }
        if (candidates.isEmpty()) {
            return null;
        }
        List sameContinentCandidates = candidates.stream().filter(candidate -> GotCastles.areCastlesOnSameContinent(invadingCastle, candidate)).collect(Collectors.toCollection(ArrayList::new));
        if (!sameContinentCandidates.isEmpty()) {
            candidates = sameContinentCandidates;
        }
        candidates.sort(Comparator.comparingDouble(candidate -> candidate.bannerPos().method_10262((class_2382)invadingCastle.bannerPos())).thenComparing(StrategicCastleSnapshot::castleId));
        double closestDistance = Math.sqrt(((StrategicCastleSnapshot)candidates.getFirst()).bannerPos().method_10262((class_2382)invadingCastle.bannerPos()));
        boolean bl = seaProjection = closestDistance > 5200.0 && invadingCastle.harbourCastle();
        if (seaProjection && !(harbourCandidates = (List)candidates.stream().filter(StrategicCastleSnapshot::harbourCastle).collect(Collectors.toCollection(ArrayList::new))).isEmpty()) {
            candidates = harbourCandidates;
            closestDistance = Math.sqrt(((StrategicCastleSnapshot)candidates.getFirst()).bannerPos().method_10262((class_2382)invadingCastle.bannerPos()));
        }
        double frontierCutoff = Math.max(closestDistance + 1800.0, closestDistance * 1.45);
        List<StrategicCastleSnapshot> frontierCandidates = candidates.stream().filter(candidate -> Math.sqrt(candidate.bannerPos().method_10262((class_2382)invadingCastle.bannerPos())) <= frontierCutoff).toList();
        List<StrategicCastleSnapshot> weightedPool = GotCastles.isCastlelessOriginAttack(invadingCastle) || frontierCandidates.isEmpty() || seaProjection ? candidates : frontierCandidates;
        int totalWeight = 0;
        ArrayList<Integer> weights = new ArrayList<Integer>(weightedPool.size());
        for (StrategicCastleSnapshot candidate2 : weightedPool) {
            int weight = GotCastles.kingdomAiTargetWeight(invadingCastle, candidate2);
            weights.add(weight);
            totalWeight += weight;
        }
        if (totalWeight <= 0) {
            return (StrategicCastleSnapshot)weightedPool.getFirst();
        }
        int roll = world.field_9229.method_43048(totalWeight);
        int running = 0;
        for (int index = 0; index < weightedPool.size(); ++index) {
            if (roll >= (running += ((Integer)weights.get(index)).intValue())) continue;
            return (StrategicCastleSnapshot)weightedPool.get(index);
        }
        return (StrategicCastleSnapshot)weightedPool.getLast();
    }

    private static int kingdomAiTargetWeight(StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle) {
        boolean sameContinent = GotCastles.areCastlesOnSameContinent(invadingCastle, targetCastle);
        double distance = Math.sqrt(invadingCastle.bannerPos().method_10262((class_2382)targetCastle.bannerPos()));
        double closenessFactor = 1.0 - Math.min(1.0, distance / 9000.0);
        int score = 20 + (int)Math.round(closenessFactor * 80.0);
        if (distance > 5200.0) {
            score = Math.max(1, score / 3);
        }
        if (!sameContinent) {
            score = Math.max(1, score / 2);
        }
        if (targetCastle.harbourCastle()) {
            score += 18;
        }
        if (!invadingCastle.ownerReligionId().isBlank() && !invadingCastle.ownerReligionId().equals(targetCastle.ownerReligionId())) {
            score += 10;
        }
        score += (switch (targetCastle.tier()) {
            default -> throw new MatchException(null, null);
            case CastleTier.CAPITAL -> 4;
            case CastleTier.LORDSHIP -> 10;
            case CastleTier.SETTLEMENT -> 14;
        });
        if (targetCastle.ownerPlayerUuid() != null) {
            score += 6;
        }
        if (GotCastles.isCastlelessOriginAttack(invadingCastle)) {
            score = GotKingdoms.isEssosKingdom(targetCastle.ownerKingdomId()) ? (score += 70) : Math.max(1, score / 2);
            if (targetCastle.harbourCastle()) {
                score += 50;
            }
            if (GotCastles.isOriginalCastleForKingdom(targetCastle.castleId(), invadingCastle.ownerKingdomId())) {
                score += 90;
            }
        }
        return Math.max(1, score);
    }

    private static boolean requiresCrossContinentHarbourTarget(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle) {
        return GotCastles.requiresCrossContinentHarbourTarget(world, invadingCastle, targetCastle, null);
    }

    private static boolean requiresCrossContinentHarbourTarget(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle, @Nullable StrategicCastleSnapshotSet strategicCastles) {
        return !GotCastles.areCastlesOnSameContinent(invadingCastle, targetCastle) && !(strategicCastles != null ? GotCastles.hasKingdomFootholdOnContinentOf(strategicCastles, GotCastles.canonicalKingdomId(invadingCastle.ownerKingdomId()), GotCastles.continentReferenceKingdomId(targetCastle)) : GotCastles.hasKingdomFootholdOnContinentOf(world, GotCastles.canonicalKingdomId(invadingCastle.ownerKingdomId()), GotCastles.continentReferenceKingdomId(targetCastle)));
    }

    private static boolean hasKingdomFootholdOnContinentOf(class_3218 world, String attackerKingdomId, String targetKingdomId) {
        if (world == null || attackerKingdomId == null || attackerKingdomId.isBlank() || targetKingdomId == null || targetKingdomId.isBlank()) {
            return false;
        }
        return GotCastles.getStrategicCastlesForKingdom(world, attackerKingdomId).stream().anyMatch(castle -> GotKingdoms.areKingdomsOnSameContinent(GotCastles.continentReferenceKingdomId(castle), targetKingdomId));
    }

    private static boolean hasKingdomFootholdOnContinentOf(StrategicCastleSnapshotSet strategicCastles, String attackerKingdomId, String targetKingdomId) {
        return strategicCastles.castlesForKingdom(attackerKingdomId).stream().anyMatch(castle -> GotKingdoms.areKingdomsOnSameContinent(GotCastles.continentReferenceKingdomId(castle), targetKingdomId));
    }

    private static boolean areCastlesOnSameContinent(StrategicCastleSnapshot firstCastle, StrategicCastleSnapshot secondCastle) {
        if (firstCastle == null || secondCastle == null) {
            return false;
        }
        return GotKingdoms.areKingdomsOnSameContinent(GotCastles.continentReferenceKingdomId(firstCastle), GotCastles.continentReferenceKingdomId(secondCastle));
    }

    private static String continentReferenceKingdomId(StrategicCastleSnapshot castle) {
        if (castle == null) {
            return "";
        }
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(GotCastles.realCastleIdForStrategicId(castle.castleId()));
        if (metadata != null && metadata.kingdomId() != null && !metadata.kingdomId().isBlank()) {
            return GotCastles.canonicalKingdomId(metadata.kingdomId());
        }
        return GotCastles.canonicalKingdomId(castle.ownerKingdomId());
    }

    @Nullable
    private static CastleInvasionMode chooseKingdomAiInvasionMode(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle) {
        return GotCastles.chooseKingdomAiInvasionMode(world, invadingCastle, targetCastle, null);
    }

    @Nullable
    private static CastleInvasionMode chooseKingdomAiInvasionMode(class_3218 world, StrategicCastleSnapshot invadingCastle, StrategicCastleSnapshot targetCastle, @Nullable StrategicCastleSnapshotSet strategicCastles) {
        if (world == null || invadingCastle == null || targetCastle == null) {
            return null;
        }
        String attackerKingdomId = GotCastles.canonicalKingdomId(invadingCastle.ownerKingdomId());
        boolean differentReligion = !invadingCastle.ownerReligionId().isBlank() && !invadingCastle.ownerReligionId().equals(targetCastle.ownerReligionId());
        boolean canConquer = strategicCastles == null ? GotCastles.canLordAcquireCastleTier(world, attackerKingdomId, invadingCastle.lordName(), targetCastle.tier(), targetCastle.castleId()) : GotCastles.canLordAcquireCastleTier(strategicCastles, attackerKingdomId, invadingCastle.lordName(), targetCastle.tier(), targetCastle.castleId());
        int roll = world.field_9229.method_43048(100);
        boolean isDispossessedAttack = invadingCastle.castleId().startsWith("dispossessed_origin_");
        if (isDispossessedAttack) {
            return canConquer ? CastleInvasionMode.CONQUER : CastleInvasionMode.RAID;
        }
        if (GotCastles.isCastlelessOriginAttack(invadingCastle)) {
            boolean targetIsSettlement;
            boolean recapturingOriginalCastle = GotCastles.isOriginalCastleForKingdom(targetCastle.castleId(), attackerKingdomId);
            boolean bl = targetIsSettlement = targetCastle.tier() == CastleTier.SETTLEMENT;
            if (recapturingOriginalCastle && canConquer) {
                if (roll < 55) {
                    return CastleInvasionMode.CONQUER;
                }
                if (differentReligion && roll < 75) {
                    return CastleInvasionMode.CRUSADE;
                }
                return CastleInvasionMode.RAID;
            }
            if (targetIsSettlement && canConquer) {
                if (roll < 60) {
                    return CastleInvasionMode.CONQUER;
                }
                if (differentReligion && roll < 70) {
                    return CastleInvasionMode.CRUSADE;
                }
                return CastleInvasionMode.RAID;
            }
            if (differentReligion) {
                if (roll < 45) {
                    return CastleInvasionMode.CRUSADE;
                }
                if (roll < 90 || !canConquer) {
                    return CastleInvasionMode.RAID;
                }
                return CastleInvasionMode.CONQUER;
            }
            if (roll < 85 || !canConquer) {
                return CastleInvasionMode.RAID;
            }
            return CastleInvasionMode.CONQUER;
        }
        if (differentReligion) {
            if (canConquer && roll < 34) {
                return CastleInvasionMode.CONQUER;
            }
            if (roll < 67) {
                return CastleInvasionMode.CRUSADE;
            }
            return CastleInvasionMode.RAID;
        }
        if (canConquer && roll < 50) {
            return CastleInvasionMode.CONQUER;
        }
        if (roll < 100) {
            return CastleInvasionMode.RAID;
        }
        return CastleInvasionMode.RAID;
    }

    private static String religionIdForKingdomAiInvasion(String kingdomId, String fallbackReligionId) {
        String defaultReligionId = GotCastles.defaultReligionIdForKingdom(kingdomId);
        if (defaultReligionId != null && !defaultReligionId.isBlank()) {
            return defaultReligionId;
        }
        return fallbackReligionId == null ? "" : fallbackReligionId;
    }

    private static boolean canLordAcquireCastleTier(class_3218 world, String kingdomId, String lordName, CastleTier tier, @Nullable String excludedCastleId) {
        if (world == null || tier == null || lordName == null || lordName.isBlank() || kingdomId == null || kingdomId.isBlank()) {
            return false;
        }
        int limit = AgobCastleOwnershipConfig.load().limitForCastleTier(tier);
        if (limit <= 0) {
            return false;
        }
        return GotCastles.countOwnedCastlesByLordNameAndTier(world, kingdomId, lordName, tier, excludedCastleId) < limit;
    }

    private static boolean canLordAcquireCastleTier(StrategicCastleSnapshotSet strategicCastles, String kingdomId, String lordName, CastleTier tier, @Nullable String excludedCastleId) {
        if (tier == null || lordName == null || lordName.isBlank() || kingdomId == null || kingdomId.isBlank()) {
            return false;
        }
        int limit = AgobCastleOwnershipConfig.load().limitForCastleTier(tier);
        return limit > 0 && GotCastles.countOwnedCastlesByLordNameAndTier(strategicCastles, kingdomId, lordName, tier, excludedCastleId) < limit;
    }

    private static int countOwnedCastlesByLordNameAndTier(class_3218 world, String kingdomId, String lordName, CastleTier tier, @Nullable String excludedCastleId) {
        int ownedCount = 0;
        CastleWorldState state = GotCastles.getWorldState(world);
        for (OverlayCastleSite site : overlaySites) {
            StrategicCastleSnapshot castle;
            if (state.isRejected(site.castleId()) || (castle = GotCastles.resolveStrategicCastle(world, site.castleId())) == null || castle.playerClaim() || excludedCastleId != null && excludedCastleId.equals(castle.castleId()) || castle.tier() != tier || !GotCastles.canonicalKingdomId(kingdomId).equals(GotCastles.canonicalKingdomId(castle.ownerKingdomId())) || !lordName.equals(castle.lordName())) continue;
            ++ownedCount;
        }
        return ownedCount;
    }

    private static int countOwnedCastlesByLordNameAndTier(StrategicCastleSnapshotSet strategicCastles, String kingdomId, String lordName, CastleTier tier, @Nullable String excludedCastleId) {
        int ownedCount = 0;
        for (OverlayCastleSite site : overlaySites) {
            StrategicCastleSnapshot castle = strategicCastles.resolve(site.castleId());
            if (castle == null || castle.playerClaim() || excludedCastleId != null && excludedCastleId.equals(castle.castleId()) || castle.tier() != tier || !GotCastles.canonicalKingdomId(kingdomId).equals(GotCastles.canonicalKingdomId(castle.ownerKingdomId())) || !lordName.equals(castle.lordName())) continue;
            ++ownedCount;
        }
        return ownedCount;
    }

    private static CastleTier highestOwnedTierForLord(class_3218 world, String kingdomId, String lordName, @Nullable String preferredCastleId) {
        return GotCastles.highestOwnedTierForLord(GotCastles.snapshotStrategicCastles(world), kingdomId, lordName, preferredCastleId);
    }

    private static CastleTier highestOwnedTierForLord(StrategicCastleSnapshotSet strategicCastles, String kingdomId, String lordName, @Nullable String preferredCastleId) {
        StrategicCastleSnapshot preferredCastle;
        CastleTier highest = CastleTier.SETTLEMENT;
        for (OverlayCastleSite site : overlaySites) {
            StrategicCastleSnapshot castle = strategicCastles.resolve(site.castleId());
            if (castle == null || castle.playerClaim() || !GotCastles.canonicalKingdomId(kingdomId).equals(GotCastles.canonicalKingdomId(castle.ownerKingdomId())) || !lordName.equals(castle.lordName()) || castle.tier().ordinal() <= highest.ordinal()) continue;
            highest = castle.tier();
        }
        if (preferredCastleId != null && (preferredCastle = strategicCastles.resolve(preferredCastleId)) != null && preferredCastle.tier().ordinal() > highest.ordinal()) {
            highest = preferredCastle.tier();
        }
        return highest;
    }

    private static int attackerTroopCountFor(CastleTier targetTier) {
        return switch (targetTier) {
            default -> throw new MatchException(null, null);
            case CastleTier.SETTLEMENT -> 15;
            case CastleTier.LORDSHIP -> 25;
            case CastleTier.CAPITAL -> 35;
        };
    }

    private static double attackerChanceForAiInvasion(CastleInvasionMode mode, CastleTier defenderTier, CastleTier attackerTier) {
        if (mode == CastleInvasionMode.CONQUER) {
            return switch (defenderTier) {
                default -> throw new MatchException(null, null);
                case CastleTier.SETTLEMENT -> {
                    switch (attackerTier) {
                        default: {
                            throw new MatchException(null, null);
                        }
                        case SETTLEMENT: {
                            yield 0.05;
                        }
                        case LORDSHIP: {
                            yield 0.3;
                        }
                        case CAPITAL: 
                    }
                    yield 0.35;
                }
                case CastleTier.LORDSHIP -> {
                    switch (attackerTier) {
                        default: {
                            throw new MatchException(null, null);
                        }
                        case SETTLEMENT: {
                            yield 0.1;
                        }
                        case LORDSHIP: {
                            yield 0.15;
                        }
                        case CAPITAL: 
                    }
                    yield 0.4;
                }
                case CastleTier.CAPITAL -> {
                    switch (attackerTier) {
                        default: {
                            throw new MatchException(null, null);
                        }
                        case SETTLEMENT: {
                            yield 0.05;
                        }
                        case LORDSHIP: {
                            yield 0.15;
                        }
                        case CAPITAL: 
                    }
                    yield 0.3;
                }
            };
        }
        return switch (defenderTier) {
            default -> throw new MatchException(null, null);
            case CastleTier.SETTLEMENT -> {
                switch (attackerTier) {
                    default: {
                        throw new MatchException(null, null);
                    }
                    case SETTLEMENT: {
                        yield 0.25;
                    }
                    case LORDSHIP: {
                        yield 0.35;
                    }
                    case CAPITAL: 
                }
                yield 0.5;
            }
            case CastleTier.LORDSHIP -> {
                switch (attackerTier) {
                    default: {
                        throw new MatchException(null, null);
                    }
                    case SETTLEMENT: {
                        yield 0.05;
                    }
                    case LORDSHIP: {
                        yield 0.15;
                    }
                    case CAPITAL: 
                }
                yield 0.3;
            }
            case CastleTier.CAPITAL -> {
                switch (attackerTier) {
                    default: {
                        throw new MatchException(null, null);
                    }
                    case SETTLEMENT: {
                        yield 0.01;
                    }
                    case LORDSHIP: {
                        yield 0.05;
                    }
                    case CAPITAL: 
                }
                yield 0.2;
            }
        };
    }

    private static void appendKingdomAiInvasionAttemptLog(class_3218 world, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, StrategicCastleSnapshot castle) {
        String line = invasion.attackerLordName() + " has sent a party to " + GotCastles.displayNameFor(CastleInvasionMode.byId(invasion.modeId())) + " " + castle.displayName();
        GotCastles.appendKingdomEventLine(world, line, GotCastles.houseSigilTextureId(invasion.attackerHouseId(), invasion.attackerKingdomId()), invasion.attackerKingdomId(), invasion.defenderKingdomId());
        GotCastles.announceKingdomConflictAttempt(world, castle.displayName(), GotCastles.displayNameFor(CastleInvasionMode.byId(invasion.modeId())) + " Party Sent");
    }

    private static void appendKingdomAiInvasionOutcomeLog(class_3218 world, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, StrategicCastleSnapshot castle, boolean successful) {
        String line = invasion.attackerLordName() + " " + (successful ? "successfully" : "unsuccessfully") + " " + GotCastles.pastTenseFor(CastleInvasionMode.byId(invasion.modeId())) + " " + castle.displayName();
        GotCastles.appendKingdomEventLine(world, line, GotCastles.houseSigilTextureId(invasion.attackerHouseId(), invasion.attackerKingdomId()), invasion.attackerKingdomId(), invasion.defenderKingdomId());
        GotCastles.announceKingdomConflictResult(world, castle.displayName(), GotCastles.displayNameFor(CastleInvasionMode.byId(invasion.modeId())) + (successful ? " Successful" : " Unsuccessful"), successful ? invasion.attackerKingdomId() : invasion.defenderKingdomId(), successful ? invasion.defenderKingdomId() : invasion.attackerKingdomId());
    }

    private static void updateKingdomAiWarmupBossBar(class_3218 world, KingdomAiInvasionWorldState.KingdomAiInvasion invasion, CastleInstance castle, List<class_3222> players) {
        ActiveKingdomAiInvasion active = ACTIVE_KINGDOM_AI_INVASIONS.computeIfAbsent(castle.getId(), ignored -> new ActiveKingdomAiInvasion(castle.getId()));
        active.bossBar().method_5416(class_1259.class_1260.field_5782);
        active.syncPlayers(players);
        long remainingTicks = Math.max(0L, invasion.warmupEndsAtTick() - world.method_8510());
        active.updateIncomingBossBar(remainingTicks, invasion.warmupDurationTicks(), GotCastles.displayNameFor(CastleInvasionMode.byId(invasion.modeId())));
    }

    private static void updateKingdomAiAssaultBossBar(KingdomAiInvasionWorldState.KingdomAiInvasion invasion, List<class_3222> players) {
        ActiveKingdomAiInvasion active = ACTIVE_KINGDOM_AI_INVASIONS.computeIfAbsent(invasion.targetCastleId(), ignored -> new ActiveKingdomAiInvasion(invasion.targetCastleId()));
        active.bossBar().method_5416(class_1259.class_1260.field_5784);
        active.syncPlayers(players);
        active.updateInvasionBossBar(invasion.attackerUuids().size(), Math.max(1, invasion.attackerCount()), GotCastles.displayNameFor(CastleInvasionMode.byId(invasion.modeId())));
    }

    private static void clearKingdomAiBossBar(String castleId) {
        ActiveKingdomAiInvasion active = ACTIVE_KINGDOM_AI_INVASIONS.remove(castleId);
        if (active != null) {
            active.bossBar().method_14094();
        }
    }

    private static void alertCastleToPlayer(class_3218 world, CastleInstance castle, class_3222 player) {
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        long expiresAtTick = world.method_8510() + 6000L;
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()))) {
            npc2.alertAgainst(player, expiresAtTick);
        }
    }

    private static boolean beforeBlockBreak(class_1937 world, class_1657 player, class_2338 pos, class_2680 state, class_2586 blockEntity) {
        CastleInstance claimAtPos;
        class_3218 serverWorld;
        block23: {
            block22: {
                if (world.method_8608() || !(world instanceof class_3218)) break block22;
                serverWorld = (class_3218)world;
                if (player instanceof class_3222) break block23;
            }
            return true;
        }
        class_3222 serverPlayer = (class_3222)player;
        if (!world.method_27983().equals(GotDimensionKeys.ICEANDFIRE)) {
            return true;
        }
        if (!serverPlayer.method_5687(2) && !GotCastles.isSingleplayerOwner(serverPlayer) && GotCastles.findReligiousSiteAt(serverWorld, pos) != null) {
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"This Religious Site is protected.").method_27692(class_124.field_1061), true);
            return false;
        }
        CastleWorldState castleState = GotCastles.getWorldState(serverWorld);
        VillageInstance villageClaim = VillageWorldState.get(serverWorld).findClaimAt(pos);
        if (villageClaim != null && !GotCastles.canModifyVillageClaim(serverPlayer, serverWorld, villageClaim)) {
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"This land is claimed.").method_27692(class_124.field_1061), true);
            return false;
        }
        CastleInstance claimAtBreakPos = castleState.findClaimAt(pos);
        if (GotCastles.isHireableCampClaim(claimAtBreakPos)) {
            if (GotCastles.isHireableCampAnchorBlock(claimAtBreakPos, pos, state)) {
                GotCastles.removeClaim(serverWorld, castleState, claimAtBreakPos);
                serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"Encampment destroyed.").method_27692(class_124.field_1054), true);
                return true;
            }
            if (serverPlayer.method_5687(2)) {
                return true;
            }
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"This encampment is claimed.").method_27692(class_124.field_1061), true);
            return false;
        }
        if (claimAtBreakPos != null && "player_claim".equals(claimAtBreakPos.getDefinitionId()) && state.method_26204() instanceof TentCollisionBlock && !GotCastles.canDestroyClaim(serverPlayer, claimAtBreakPos)) {
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"You cannot break this claim tent.").method_27692(class_124.field_1061), true);
            return false;
        }
        CastleInstance foundationClaim = GotCastles.findClaimByFoundationBlock(castleState, pos);
        if (foundationClaim != null) {
            if (serverPlayer.method_5687(2)) {
                GotCastles.removeClaim(serverWorld, castleState, foundationClaim);
                GotCastles.removeSettlementTentStructureIfNeeded(serverWorld, foundationClaim);
                return true;
            }
            if (!GotCastles.canDestroyClaim(serverPlayer, foundationClaim)) {
                serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"This land is claimed.").method_27692(class_124.field_1061), true);
                return false;
            }
            GotCastles.removeClaim(serverWorld, castleState, foundationClaim);
            GotCastles.removeSettlementTentStructureIfNeeded(serverWorld, foundationClaim);
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"Claim removed.").method_27692(class_124.field_1054), true);
            return true;
        }
        CastleInstance bannerClaim = castleState.findByBannerPos(pos);
        if (bannerClaim == null && state.method_26204() instanceof class_2185 && (claimAtPos = castleState.findClaimAt(pos)) != null && GotCastles.isValidClaimBanner(serverWorld, pos)) {
            bannerClaim = GotCastles.repairClaimBannerReference(serverWorld, castleState, claimAtPos, pos);
        }
        if (bannerClaim != null) {
            ActiveCastleInvasion invasion = ACTIVE_CASTLE_INVASIONS.get(serverPlayer.method_5667());
            if (invasion != null && invasion.awaitingBannerBreak() && invasion.castleId().equals(bannerClaim.getId())) {
                GotCastles.completeConquest(serverWorld, serverPlayer, bannerClaim);
                return false;
            }
            if (serverPlayer.method_5687(2)) {
                GotCastles.removeClaim(serverWorld, castleState, bannerClaim);
                return true;
            }
            if (bannerClaim.isNaturalClaim()) {
                serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"Kingdom claim banners can only be broken by admins.").method_27692(class_124.field_1061), true);
                return false;
            }
            if (!GotCastles.canDestroyClaim(serverPlayer, bannerClaim)) {
                serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"You cannot break this claim banner.").method_27692(class_124.field_1061), true);
                return false;
            }
            GotCastles.removeClaim(serverWorld, castleState, bannerClaim);
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"Claim removed.").method_27692(class_124.field_1054), true);
            return true;
        }
        if (serverPlayer.method_5687(2)) {
            return true;
        }
        CastleInstance claim = castleState.findClaimAt(pos);
        if (claim == null) {
            return true;
        }
        if (!GotCastles.canBreakInClaim(serverPlayer, claim)) {
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"This land is claimed.").method_27692(class_124.field_1061), true);
            return false;
        }
        return true;
    }

    private static void removeClaim(class_3218 world, CastleWorldState state, CastleInstance claim) {
        GotCastles.endCastleRecapture(world, claim.getId(), false, false);
        if (GotCastles.isHireableCampClaim(claim)) {
            GotTroops.clearHireablesForSite(world, claim.getId());
        }
        GotCastles.removeBoundSettlers(world, claim);
        state.remove(claim.getId());
        GotCastles.clearPriorityCastleGeneration(world, claim.getId());
        if (claim.isNaturalClaim()) {
            state.markRejected(claim.getId());
        }
        GotCastles.removePlayerClaimConfigEntryIfNeeded(claim);
        GotCastles.syncStateToPlayers(world);
    }

    private static boolean canBreakInClaim(class_3222 player, CastleInstance claim) {
        if (player.method_5687(2)) {
            return true;
        }
        return GotCastles.canEditBlocksInCastleClaim(player, claim);
    }

    private static boolean canDestroyClaim(class_3222 player, CastleInstance claim) {
        if (player.method_5687(2)) {
            return true;
        }
        return GotCastles.canModifyCastleClaim(player, claim);
    }

    private static boolean canEditBlocksInCastleClaim(class_3222 player, CastleInstance claim) {
        return GotCastles.canModifyCastleClaim(player, claim) || player != null && claim != null && "player_claim".equals(claim.getDefinitionId()) && claim.getMemberUuids().contains(player.method_5667());
    }

    private static boolean canModifyCastleClaim(class_3222 player, CastleInstance claim) {
        return player != null && claim != null && claim.getOwnerPlayerUuid() != null && claim.getOwnerPlayerUuid().equals(player.method_5667());
    }

    public static boolean shouldAttemptPlayerSettlementClaim(class_3218 world, class_2338 tentPos) {
        return GotCastles.isValidPlayerSettlementTent(world, tentPos);
    }

    public static boolean canCreatePlayerSettlementClaim(class_3218 world, class_3222 player, class_2338 tentPos) {
        CastleWorldState state = GotCastles.getWorldState(world);
        if (!GotCastles.canAcquirePlayerSettlement(world, player)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Max player settlements reached.").method_27692(class_124.field_1061), true);
            return false;
        }
        if (state.findByBannerPos(tentPos) != null || state.findClaimAt(tentPos) != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Overlaps with existing claim").method_27692(class_124.field_1061), true);
            return false;
        }
        CastleInstance claim = GotCastles.buildPlayerSettlementClaim(player, tentPos);
        if (state.findOverlappingClaim(claim) != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Overlaps with existing claim").method_27692(class_124.field_1061), true);
            return false;
        }
        return true;
    }

    public static void createPlayerSettlementClaim(class_3218 world, class_3222 player, class_2338 tentPos) {
        CastleWorldState state = GotCastles.getWorldState(world);
        CastleInstance claim = GotCastles.buildPlayerSettlementClaim(player, tentPos);
        claim.setCreatedDay(Math.max(0L, world.method_8532() / 24000L));
        claim.setSettlementTier(PlayerSettlementTier.PLAYER_SETTLEMENT);
        claim.setSettlementLevel(1);
        state.put(claim);
        GotCastles.upsertPlayerClaimConfig(player, claim, tentPos);
        player.method_7353((class_2561)class_2561.method_43470((String)"Land claimed in a 3x3 chunk area.").method_27692(class_124.field_1065), true);
        GotCastles.syncStateToPlayers(world);
        GotMainQuests.onPlayerSettlementCreated(player);
    }

    public static boolean tryBreakSettlementTent(class_3218 world, class_3222 player, class_2338 tentPos) {
        CastleWorldState state = GotCastles.getWorldState(world);
        CastleInstance claim = state.findByBannerPos(tentPos);
        if (claim == null || claim.isNaturalClaim()) {
            GotMod.LOGGER.info("[tent-break-debug] claim-check origin={} player={} result=allowed reason=no_player_claim", (Object)tentPos.method_23854(), (Object)player.method_5477().getString());
            return true;
        }
        if (!GotCastles.canDestroyClaim(player, claim)) {
            GotMod.LOGGER.info("[tent-break-debug] claim-check origin={} player={} result=blocked reason=not_owner claimId={}", new Object[]{tentPos.method_23854(), player.method_5477().getString(), claim.getId()});
            player.method_7353((class_2561)class_2561.method_43470((String)"You cannot break this claim tent.").method_27692(class_124.field_1061), true);
            return false;
        }
        GotMod.LOGGER.info("[tent-break-debug] claim-check origin={} player={} result=allowed reason=owner claimId={}", new Object[]{tentPos.method_23854(), player.method_5477().getString(), claim.getId()});
        GotCastles.removeClaim(world, state, claim);
        player.method_7353((class_2561)class_2561.method_43470((String)"Claim removed.").method_27692(class_124.field_1054), true);
        return true;
    }

    private static void tryCreatePlayerClaim(class_3218 world, class_3222 player, class_2338 bannerPos) {
        if (!GotCastles.isValidPlayerSettlementTent(world, bannerPos)) {
            return;
        }
        if (!GotCastles.canCreatePlayerSettlementClaim(world, player, bannerPos)) {
            return;
        }
        GotCastles.createPlayerSettlementClaim(world, player, bannerPos);
    }

    private static CastleInstance buildPlayerSettlementClaim(class_3222 player, class_2338 tentPos) {
        String claimId = GotCastles.playerSettlementClaimId(player.method_7334().getName(), tentPos);
        CastleTitle title = new CastleTitle(claimId + "_title", claimId, "Player Settlement", CastleTier.SETTLEMENT);
        CastleInstance claim = new CastleInstance(claimId, "player_claim", CastleTier.SETTLEMENT, class_2960.method_60655((String)"agob", (String)"player_claim"), tentPos, tentPos, 1, 1, 40, false, title, CastleTier.SETTLEMENT.defaultLootTableId());
        claim.setOwnerPlayerUuid(player.method_5667());
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        claim.setOwnerKingdomId(profile.getSelectedKingdomId());
        claim.setOwnerHouseId(profile.getSelectedOptionId());
        claim.setOwnerReligionId(profile.getSelectedReligionId());
        return claim;
    }

    private static String playerSettlementClaimId(String username, class_2338 tentPos) {
        String sanitized;
        String string = sanitized = username == null ? "" : username.trim().toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9_]", "_");
        if (sanitized.isBlank()) {
            sanitized = "player";
        }
        if (tentPos == null) {
            return sanitized + "_settlement";
        }
        return sanitized + "_settlement_" + tentPos.method_10263() + "_" + tentPos.method_10264() + "_" + tentPos.method_10260();
    }

    private static void reconcilePlayerClaimsConfig(MinecraftServer server, class_3218 world) {
        if (!RECONCILED_PLAYER_CLAIM_SERVERS.add(server)) {
            return;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        Map<String, AgobPlayerClaimsConfig.Entry> configClaims = AgobPlayerClaimsConfig.load();
        if (!AgobPlayerClaimsConfig.exists()) {
            GotCastles.importExistingPlayerClaims(state);
            return;
        }
        boolean changed = false;
        for (CastleInstance claim : List.copyOf(state.getAll())) {
            if (!"player_claim".equals(claim.getDefinitionId()) || configClaims.containsKey(claim.getId())) continue;
            GotCastles.removeBoundSettlers(world, claim);
            GotCastles.removeSettlementTentEntity(world, claim.getBannerPos());
            state.remove(claim.getId());
            GotCastles.clearPriorityCastleGeneration(world, claim.getId());
            changed = true;
        }
        if (changed) {
            GotCastles.syncStateToPlayers(world);
        }
    }

    private static void importExistingPlayerClaims(CastleWorldState state) {
        LinkedHashMap<String, AgobPlayerClaimsConfig.Entry> entries = new LinkedHashMap<String, AgobPlayerClaimsConfig.Entry>();
        for (CastleInstance claim : state.getAll()) {
            if (!"player_claim".equals(claim.getDefinitionId())) continue;
            String username = GotCastles.playerSettlementUsernameFromClaimId(claim.getId());
            class_2338 tentPos = claim.getBannerPos();
            entries.put(claim.getId(), AgobPlayerClaimsConfig.create(claim.getId(), username, tentPos.method_10263(), tentPos.method_10264(), tentPos.method_10260()));
        }
        AgobPlayerClaimsConfig.save(entries);
    }

    private static String playerSettlementUsernameFromClaimId(String claimId) {
        if (claimId == null || claimId.isBlank()) {
            return "player";
        }
        int settlementIndex = claimId.indexOf("_settlement");
        if (settlementIndex > 0) {
            return claimId.substring(0, settlementIndex);
        }
        return claimId;
    }

    private static void upsertPlayerClaimConfig(class_3222 player, CastleInstance claim, class_2338 tentPos) {
        Map<String, AgobPlayerClaimsConfig.Entry> entries = AgobPlayerClaimsConfig.load();
        AgobPlayerClaimsConfig.Entry existing = entries.get(claim.getId());
        String createdAt = existing != null ? existing.createdAt() : Instant.now().toString();
        entries.put(claim.getId(), new AgobPlayerClaimsConfig.Entry(createdAt, claim.getId(), player.method_7334().getName(), tentPos.method_10263(), tentPos.method_10264(), tentPos.method_10260()));
        AgobPlayerClaimsConfig.save(entries);
    }

    private static void removePlayerClaimConfigEntryIfNeeded(CastleInstance claim) {
        if (!"player_claim".equals(claim.getDefinitionId())) {
            return;
        }
        Map<String, AgobPlayerClaimsConfig.Entry> entries = AgobPlayerClaimsConfig.load();
        if (entries.remove(claim.getId()) != null) {
            AgobPlayerClaimsConfig.save(entries);
        }
    }

    private static void removeSettlementTentEntity(class_3218 world, class_2338 tentPos) {
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)tentPos), (double)2.0, (double)2.0, (double)2.0);
        for (TentEntity tentEntity : world.method_8390(TentEntity.class, searchBox, tent -> tentPos.equals((Object)tent.getOriginPos()))) {
            tentEntity.method_31472();
        }
    }

    private static void removeBoundSettlers(class_3218 world, CastleInstance claim) {
        if (world == null || claim == null || !"player_claim".equals(claim.getDefinitionId())) {
            return;
        }
        for (SettlerNpcEntity settler : world.method_18198(class_5575.method_31795(SettlerNpcEntity.class), entity -> claim.getId().equals(entity.got$getCastle()))) {
            settler.method_31472();
        }
    }

    private static void removeSettlementTentStructureIfNeeded(class_3218 world, CastleInstance claim) {
        if (!"player_claim".equals(claim.getDefinitionId())) {
            return;
        }
        class_2338 origin = claim.getBannerPos();
        class_2680 state = world.method_8320(origin);
        class_2248 class_22482 = state.method_26204();
        if (class_22482 instanceof TentCollisionBlock) {
            TentCollisionBlock tentBlock = (TentCollisionBlock)class_22482;
            tentBlock.destroyStructure((class_1937)world, origin, state, false);
            return;
        }
        GotCastles.removeSettlementTentEntity(world, origin);
    }

    private static boolean samePlayerOwner(CastleInstance left, CastleInstance right) {
        return left.getOwnerPlayerUuid() != null && right.getOwnerPlayerUuid() != null && left.getOwnerPlayerUuid().equals(right.getOwnerPlayerUuid());
    }

    private static boolean isValidClaimBanner(class_3218 world, class_2338 bannerPos) {
        class_2680 bannerState = world.method_8320(bannerPos);
        if (!(bannerState.method_26204() instanceof class_2185)) {
            return false;
        }
        int baseY = bannerPos.method_10264() - 1;
        class_2338 basePos = new class_2338(bannerPos.method_10263(), baseY, bannerPos.method_10260());
        if (world.method_8320(basePos).method_27852(class_2246.field_10205)) {
            return true;
        }
        for (int x = bannerPos.method_10263() - 1; x <= bannerPos.method_10263() + 1; ++x) {
            for (int z = bannerPos.method_10260() - 1; z <= bannerPos.method_10260() + 1; ++z) {
                if (world.method_8320(new class_2338(x, baseY, z)).method_27852(class_2246.field_10205)) continue;
                return false;
            }
        }
        return true;
    }

    private static boolean isValidPlayerSettlementTent(class_3218 world, class_2338 tentPos) {
        for (int x = tentPos.method_10263() - 1; x <= tentPos.method_10263() + 1; ++x) {
            for (int z = tentPos.method_10260() - 1; z <= tentPos.method_10260() + 1; ++z) {
                if (world.method_8320(new class_2338(x, tentPos.method_10264() - 1, z)).method_27852(class_2246.field_10085)) continue;
                return false;
            }
        }
        return true;
    }

    private static CastleInstance findClaimByFoundationBlock(CastleWorldState state, class_2338 pos) {
        for (CastleInstance claim : state.getAll()) {
            if (!GotCastles.isFoundationBlockForClaim(claim, pos)) continue;
            return claim;
        }
        return null;
    }

    private static CastleInstance repairClaimBannerReference(class_3218 world, CastleWorldState state, CastleInstance claim, class_2338 actualBannerPos) {
        if (world == null || state == null || claim == null || actualBannerPos == null) {
            return claim;
        }
        if (!GotCastles.isValidClaimBanner(world, actualBannerPos) || claim.getBannerPos().equals((Object)actualBannerPos)) {
            return claim;
        }
        CastleInstance repaired = GotCastles.copyCastleWithBannerPos(claim, actualBannerPos);
        state.put(repaired);
        return repaired;
    }

    private static CastleInstance copyCastleWithBannerPos(CastleInstance claim, class_2338 bannerPos) {
        CastleInstance repaired = new CastleInstance(claim.getId(), claim.getDefinitionId(), claim.getTier(), claim.getTemplateId(), claim.getOrigin(), bannerPos, claim.getFootprintChunks(), claim.getClaimRadiusChunks(), claim.getAreaRadiusBlocks(), claim.isNaturalClaim(), claim.getTitle(), claim.getLootTableId());
        repaired.setOwnerPlayerUuid(claim.getOwnerPlayerUuid());
        repaired.setOwnerKingdomId(claim.getOwnerKingdomId());
        repaired.setOwnerHouseId(claim.getOwnerHouseId());
        repaired.setOwnerReligionId(claim.getOwnerReligionId());
        repaired.setLordName(claim.getLordName());
        for (UUID memberUuid : claim.getMemberUuids()) {
            repaired.addMember(memberUuid);
        }
        for (CastleSubtype subtype : claim.getSubtypes()) {
            repaired.addSubtype(subtype);
        }
        repaired.setRaidState(claim.getRaidState());
        repaired.setRaidEndsAtTick(claim.getRaidEndsAtTick());
        repaired.setLastPopulationRefreshDay(claim.getLastPopulationRefreshDay());
        repaired.setLastObservedPopulationTimeOfDay(claim.getLastObservedPopulationTimeOfDay());
        repaired.setSettlementTier(claim.getSettlementTier());
        repaired.setSettlementLevel(claim.getSettlementLevel());
        repaired.setCreatedDay(claim.getCreatedDay());
        repaired.setLastSettlerOfferDay(claim.getLastSettlerOfferDay());
        repaired.setLastSettlerProductionDay(claim.getLastSettlerProductionDay());
        repaired.setSettlers(claim.getSettlers());
        repaired.setCastleNpcProfiles(claim.getCastleNpcProfiles());
        repaired.setWallGateClaims(claim.getWallGateClaims());
        for (int slot = 0; slot < Math.min(repaired.getStorageSize(), claim.getStorageSize()); ++slot) {
            repaired.getStorageItems().set(slot, (Object)((class_1799)claim.getStorageItems().get(slot)).method_7972());
        }
        return repaired;
    }

    private static boolean isFoundationBlockForClaim(CastleInstance claim, class_2338 pos) {
        class_2338 anchor = claim.getBannerPos();
        if (pos.method_10264() != anchor.method_10264() - 1) {
            return false;
        }
        return Math.abs(pos.method_10263() - anchor.method_10263()) <= 1 && Math.abs(pos.method_10260() - anchor.method_10260()) <= 1;
    }

    private static boolean isPlacementRestricted(class_3222 player, class_2338 placePos, class_1799 stack) {
        if (player.method_5687(2) || stack.method_7960()) {
            return false;
        }
        if (!(stack.method_7909() instanceof class_1747) && !(stack.method_7909() instanceof TentItem)) {
            return false;
        }
        class_3218 world = (class_3218)player.method_37908();
        if (GotCastles.findReligiousSiteAt(world, placePos) != null) {
            return true;
        }
        VillageInstance villageClaim = VillageWorldState.get(world).findClaimAt(placePos);
        if (villageClaim != null) {
            return !GotCastles.canModifyVillageClaim(player, world, villageClaim);
        }
        CastleInstance claim = GotCastles.getWorldState(world).findClaimAt(placePos);
        return claim != null && !GotCastles.canBreakInClaim(player, claim);
    }

    private static boolean canModifyVillageClaim(class_3222 player, class_3218 world, VillageInstance village) {
        if (player == null || world == null || village == null) {
            return false;
        }
        if (player.method_5687(2)) {
            return true;
        }
        CastleInstance boundCastle = GotCastles.getWorldState(world).get(village.getBoundCastleId());
        return boundCastle != null && boundCastle.getOwnerPlayerUuid() != null && boundCastle.getOwnerPlayerUuid().equals(player.method_5667());
    }

    private static Optional<class_2338> findClaimBanner(class_3218 world, class_2338 origin, class_2382 size) {
        for (int y = origin.method_10264() + size.method_10264() + 2; y >= origin.method_10264() - 4; --y) {
            for (int x = origin.method_10263(); x < origin.method_10263() + size.method_10263(); ++x) {
                for (int z = origin.method_10260(); z < origin.method_10260() + size.method_10260(); ++z) {
                    class_2338 pos = new class_2338(x, y, z);
                    if (!GotCastles.isValidClaimBanner(world, pos)) continue;
                    return Optional.of(pos);
                }
            }
        }
        return Optional.empty();
    }

    private static Kingdom resolveKingdomForBiome(class_3218 world, class_2338 pos) {
        String biomePath = world.method_23753(pos).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        return GotKingdoms.byBiomePath(biomePath);
    }

    private static void spawnCastleNpcs(class_3218 world, CastleInstance instance, Kingdom kingdom) {
        if (kingdom == null) {
            return;
        }
        boolean changed = GotCastles.ensureCastleNpcProfiles(world, instance, null);
        for (CastleNpcProfile profile : instance.getCastleNpcProfiles()) {
            if (GotCastles.spawnCastleResidentNpc(world, instance, kingdom, profile)) continue;
            GotCastles.scheduleCastleResidentRespawnRetry(world, instance, profile);
        }
        if (changed) {
            GotCastles.getWorldState(world).method_80();
        }
    }

    private static boolean spawnCastleFishermanNpc(class_3218 world, class_1299<? extends AbstractKingdomFishermanNpcEntity> entityType, CastleInstance instance, CastleNpcProfile profile) {
        AbstractKingdomFishermanNpcEntity entity = (AbstractKingdomFishermanNpcEntity)entityType.method_5883((class_1937)world);
        if (entity == null) {
            GotCastles.recordFishermanSpawnAttempt("failed", "entity_create_failed", instance, null, null, null);
            return false;
        }
        FishermanSpawnResolution resolution = GotCastles.resolveHarbourNpcPlacement(world, instance, profile);
        if (!resolution.success()) {
            GotCastles.recordFishermanSpawnAttempt("failed", resolution.reason(), instance, resolution.placement(), null, resolution.detail());
            return false;
        }
        class_2338 pointOfInterest = resolution.placement().pointOfInterest();
        class_2338 spawnPos = resolution.placement().spawnPos();
        entity.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
        entity.method_5826(profile.uuid());
        entity.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16474, null);
        entity.got$setCastle(instance.getId());
        entity.setCastleNpcId(profile.id());
        entity.setPointOfInterest(pointOfInterest);
        entity.setPointOfInterestRadius(profile.pointOfInterestRadius());
        GotCastles.applyNpcAttributes((AbstractKingdomHumanoidEntity)entity, profile);
        GotCastles.applyNpcAppearance(entity, profile);
        if (!profile.displayName().isBlank()) {
            entity.method_5665((class_2561)class_2561.method_43470((String)profile.displayName()));
        }
        world.method_8649((class_1297)entity);
        GotCastles.recordFishermanSpawnAttempt("success", "spawned", instance, resolution.placement(), spawnPos, resolution.detail());
        return true;
    }

    private static boolean spawnCastleResidentNpc(class_3218 world, CastleInstance instance, Kingdom kingdom, CastleNpcProfile profile) {
        return GotCastles.spawnCastleResidentNpc(world, instance, kingdom, profile, null);
    }

    private static boolean spawnCastleResidentNpc(class_3218 world, CastleInstance instance, Kingdom kingdom, CastleNpcProfile profile, @Nullable LoadedCastleNpcIndex loadedNpcIndex) {
        class_2338 spawnAnchor;
        class_1297 existingLord;
        class_1299<? extends AbstractKingdomNpcEntity> entityType;
        AbstractKingdomNpcEntity existingNpc;
        if (world == null || instance == null || kingdom == null || profile == null) {
            return false;
        }
        if (GotCastles.isCastleUnderActivePlayerInvasion(instance.getId())) {
            return true;
        }
        AbstractKingdomNpcEntity existingByProfile = GotCastles.findLoadedCastleNpcForProfile(world, instance, profile, loadedNpcIndex);
        if (existingByProfile != null) {
            GotCastles.reconcileLoadedCastleNpcProfile(instance, profile, existingByProfile);
            return true;
        }
        class_1297 existingTracked = world.method_14190(profile.uuid());
        if (existingTracked instanceof AbstractKingdomNpcEntity && (existingNpc = (AbstractKingdomNpcEntity)existingTracked).method_5805()) {
            if (instance.getId().equals(existingNpc.got$getCastle())) {
                GotCastles.reconcileLoadedCastleNpcProfile(instance, profile, existingNpc);
                return true;
            }
            existingNpc.method_31472();
        }
        if ((entityType = GotCastles.castleNpcTypeForRole(kingdom, profile.role())) == null) {
            return false;
        }
        if (profile.role() == KingdomNpcRole.LORD && instance.getOwnerPlayerUuid() != null) {
            return false;
        }
        if (profile.role() == KingdomNpcRole.FISHERMAN) {
            class_1299<? extends AbstractKingdomFishermanNpcEntity> fishermanType = GotEntities.fishermanTypeForKingdom(kingdom);
            if (!instance.hasSubtype(CastleSubtype.HARBOUR) || fishermanType == null) {
                return false;
            }
            return GotCastles.spawnCastleFishermanNpc(world, fishermanType, instance, profile);
        }
        AbstractKingdomNpcEntity entity = (AbstractKingdomNpcEntity)entityType.method_5883((class_1937)world);
        if (entity == null) {
            return false;
        }
        if (profile.role() == KingdomNpcRole.LORD && (existingLord = world.method_14190(profile.uuid())) instanceof AbstractKingdomNpcEntity) {
            AbstractKingdomNpcEntity existingNpc2 = (AbstractKingdomNpcEntity)existingLord;
            if (!instance.getId().equals(existingNpc2.got$getCastle())) {
                existingNpc2.method_31472();
            }
        }
        class_2338 bannerPos = instance.getBannerPos();
        class_2338 pointOfInterest = profile.pointOfInterest() != null ? profile.pointOfInterest() : bannerPos;
        class_2338 class_23382 = spawnAnchor = profile.preferredSpawnPos() != null ? profile.preferredSpawnPos() : pointOfInterest;
        if (!world.method_8393(spawnAnchor.method_10263() >> 4, spawnAnchor.method_10260() >> 4)) {
            return false;
        }
        class_2338 spawnPos = GotCastles.resolveCastleResidentSpawnPos(world, pointOfInterest, profile);
        if (!world.method_8393(spawnPos.method_10263() >> 4, spawnPos.method_10260() >> 4)) {
            return false;
        }
        double spawnY = profile.role() == KingdomNpcRole.LORD ? (double)spawnPos.method_10264() + 1.0 : (double)spawnPos.method_10264();
        entity.method_5808((double)spawnPos.method_10263() + 0.5, spawnY, (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
        entity.method_5826(profile.uuid());
        entity.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16474, null);
        entity.got$setCastle(instance.getId());
        entity.setCastleNpcId(profile.id());
        if (entity instanceof AbstractKingdomGuardNpcEntity) {
            AbstractKingdomGuardNpcEntity guard = (AbstractKingdomGuardNpcEntity)entity;
            guard.setGuardRole(profile.role());
        }
        entity.setPointOfInterest(pointOfInterest);
        entity.setPointOfInterestRadius(profile.pointOfInterestRadius());
        GotCastles.applyNpcAttributes((AbstractKingdomHumanoidEntity)entity, profile);
        GotCastles.applyNpcAppearance(entity, profile);
        if (profile.role() == KingdomNpcRole.LORD) {
            String lordName;
            String string = lordName = !profile.displayName().isBlank() ? profile.displayName() : instance.getLordName();
            if (lordName.isBlank()) {
                lordName = GotCastles.generateFallbackLordName(instance);
            }
            entity.method_5665((class_2561)class_2561.method_43470((String)lordName));
            if (instance.getLordName().isBlank()) {
                instance.setLordName(lordName);
            }
        } else if (!profile.displayName().isBlank()) {
            entity.method_5665((class_2561)class_2561.method_43470((String)profile.displayName()));
        }
        return world.method_8649((class_1297)entity);
    }

    @Nullable
    private static AbstractKingdomNpcEntity findLoadedCastleNpcForProfile(class_3218 world, CastleInstance castle, CastleNpcProfile profile, @Nullable LoadedCastleNpcIndex loadedNpcIndex) {
        AbstractKingdomNpcEntity npc2;
        class_1297 tracked;
        if (world == null || castle == null || castle.getId().isBlank() || profile == null) {
            return null;
        }
        if (loadedNpcIndex != null) {
            AbstractKingdomNpcEntity byProfile = loadedNpcIndex.byProfileId().get(profile.id());
            if (byProfile != null && byProfile.method_5805()) {
                return byProfile;
            }
            AbstractKingdomNpcEntity byUuid = loadedNpcIndex.byUuid().get(profile.uuid());
            if (byUuid != null && byUuid.method_5805()) {
                return byUuid;
            }
        }
        if ((tracked = world.method_14190(profile.uuid())) instanceof AbstractKingdomNpcEntity && (npc2 = (AbstractKingdomNpcEntity)tracked).method_5805()) {
            return npc2;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        List nearbyNpcs = world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()) && profile.id().equals(npc.getCastleNpcId()));
        if (!nearbyNpcs.isEmpty()) {
            return (AbstractKingdomNpcEntity)nearbyNpcs.getFirst();
        }
        return null;
    }

    private static void reconcileLoadedCastleNpcProfile(CastleInstance instance, CastleNpcProfile profile, AbstractKingdomNpcEntity npc) {
        if (instance == null || profile == null || npc == null) {
            return;
        }
        if (!instance.getId().equals(npc.got$getCastle())) {
            npc.got$setCastle(instance.getId());
        }
        if (!profile.id().equals(npc.getCastleNpcId())) {
            npc.setCastleNpcId(profile.id());
        }
        if (npc instanceof AbstractKingdomGuardNpcEntity) {
            AbstractKingdomGuardNpcEntity guard = (AbstractKingdomGuardNpcEntity)npc;
            guard.setGuardRole(profile.role());
        }
        class_2338 pointOfInterest = profile.pointOfInterest() != null ? profile.pointOfInterest() : instance.getBannerPos();
        npc.setPointOfInterest(pointOfInterest);
        npc.setPointOfInterestRadius(profile.pointOfInterestRadius());
        if (npc instanceof AbstractKingdomHumanoidEntity) {
            AbstractKingdomNpcEntity humanoidNpc = npc;
            GotCastles.applyNpcAttributes((AbstractKingdomHumanoidEntity)humanoidNpc, profile);
        }
        GotCastles.applyNpcAppearance(npc, profile);
        if (!profile.displayName().isBlank()) {
            npc.method_5665((class_2561)class_2561.method_43470((String)profile.displayName()));
        }
        if (!profile.uuid().equals(npc.method_5667())) {
            GotCastles.replaceCastleNpcProfileUuid(instance, profile.id(), npc.method_5667());
            class_1937 class_19372 = npc.method_37908();
            if (class_19372 instanceof class_3218) {
                class_3218 serverWorld = (class_3218)class_19372;
                GotCastles.getWorldState(serverWorld).method_80();
            }
        }
    }

    private static void replaceCastleNpcProfileUuid(CastleInstance castle, String profileId, UUID uuid) {
        if (castle == null || profileId == null || profileId.isBlank() || uuid == null) {
            return;
        }
        ArrayList<CastleNpcProfile> updated = new ArrayList<CastleNpcProfile>();
        boolean changed = false;
        for (CastleNpcProfile existing : castle.getCastleNpcProfiles()) {
            if (!profileId.equals(existing.id())) {
                updated.add(existing);
                continue;
            }
            updated.add(new CastleNpcProfile(existing.id(), uuid, existing.roleId(), existing.seedOffset(), existing.displayName(), existing.attributes(), existing.appearance(), existing.nobleSpouse(), existing.spouseProfileId(), existing.pointOfInterest(), existing.preferredSpawnPos(), existing.pointOfInterestRadius()));
            changed = true;
        }
        if (changed) {
            castle.setCastleNpcProfiles(updated);
        }
    }

    @Nullable
    private static class_1299<? extends AbstractKingdomNpcEntity> castleNpcTypeForRole(Kingdom kingdom, KingdomNpcRole role) {
        return switch (role) {
            case KingdomNpcRole.LORD -> GotEntities.lordTypeForKingdom(kingdom);
            case KingdomNpcRole.GUARD, KingdomNpcRole.PATROL, KingdomNpcRole.GATE_WATCHER -> GotEntities.guardTypeForKingdom(kingdom);
            case KingdomNpcRole.BLACKSMITH -> GotEntities.blacksmithTypeForKingdom(kingdom);
            case KingdomNpcRole.ARENA_MASTER -> GotEntities.arenaMasterTypeForKingdom(kingdom);
            case KingdomNpcRole.FISHERMAN -> GotEntities.fishermanTypeForKingdom(kingdom);
            case KingdomNpcRole.CART_RIDER -> GotEntities.cartRiderTypeForKingdom(kingdom);
            case KingdomNpcRole.CIVILIAN -> GotEntities.civilianTypeForKingdom(kingdom);
            default -> null;
        };
    }

    private static <T extends AbstractKingdomNpcEntity> T spawnCastleNpc(class_3218 world, class_1299<T> entityType, CastleInstance instance, int seedOffset) {
        AbstractKingdomNpcEntity entity = (AbstractKingdomNpcEntity)entityType.method_5883((class_1937)world);
        if (entity == null) {
            return null;
        }
        class_2338 bannerPos = instance.getBannerPos();
        class_2338 spawnPos = GotCastles.findNpcRespawnPos(world, bannerPos, seedOffset);
        entity.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
        entity.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16474, null);
        entity.got$setCastle(instance.getId());
        entity.setPointOfInterest(bannerPos);
        world.method_8649((class_1297)entity);
        return (T)entity;
    }

    private static void copyPlayerCastle(class_3222 oldPlayer, class_3222 newPlayer, boolean alive) {
        ((CastleHolder)newPlayer).got$setCastle(((CastleHolder)oldPlayer).got$getCastle());
        ((PlayerCastleTravelHolder)newPlayer).got$copyUnlockedCastlesFrom((PlayerCastleTravelHolder)oldPlayer);
    }

    private static String findCastleIdForBanner(class_3218 world, class_2338 bannerPos) {
        CastleInstance castle = GotCastles.getWorldState(world).findByBannerPos(bannerPos);
        return castle == null ? "" : castle.getId();
    }

    static class_2338 findNpcSpawnPos(class_3218 world, class_2338 bannerPos, int seedOffset) {
        int radius = 8;
        for (int attempt = 0; attempt < 12; ++attempt) {
            int z;
            int y;
            int offsetX = world.field_9229.method_39332(-radius, radius) + seedOffset % 3 - 1;
            int offsetZ = world.field_9229.method_39332(-radius, radius) + seedOffset / 3 % 3 - 1;
            int x = bannerPos.method_10263() + offsetX;
            class_2338 candidate = new class_2338(x, y = world.method_8624(class_2902.class_2903.field_13203, x, z = bannerPos.method_10260() + offsetZ), z);
            if (!world.method_8320(candidate).method_26215() || !world.method_8320(candidate.method_10084()).method_26215() || !world.method_8316(candidate).method_15769()) continue;
            return candidate;
        }
        return bannerPos.method_10084();
    }

    private static class_2338 findNpcRespawnPos(class_3218 world, class_2338 bannerPos, int seedOffset) {
        class_2338 directPos = bannerPos.method_10084();
        if (world.method_8320(directPos).method_26215() && world.method_8320(directPos.method_10084()).method_26215() && world.method_8316(directPos).method_15769()) {
            return directPos;
        }
        return GotCastles.findNpcSpawnPos(world, bannerPos, seedOffset);
    }

    private static class_2338 resolveCastleResidentSpawnPos(class_3218 world, class_2338 pointOfInterest, CastleNpcProfile profile) {
        if (profile.preferredSpawnPos() != null && GotCastles.isValidNpcStandPos(world, profile.preferredSpawnPos())) {
            return profile.preferredSpawnPos();
        }
        return GotCastles.findNpcRespawnPos(world, pointOfInterest, profile.seedOffset());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void processPendingNpcRespawns(class_3218 world) {
        ArrayList<PendingNpcRespawn> dueRespawns = new ArrayList<PendingNpcRespawn>(2);
        ArrayList<PendingNpcRespawn> deferredOtherDimensions = new ArrayList<PendingNpcRespawn>();
        long worldTime = world.method_8510();
        PriorityQueue<PendingNpcRespawn> priorityQueue = PENDING_NPC_RESPAWNS;
        synchronized (priorityQueue) {
            PendingNpcRespawn pendingRespawn;
            while (dueRespawns.size() < 2 && (pendingRespawn = PENDING_NPC_RESPAWNS.peek()) != null && pendingRespawn.respawnAtTick() <= worldTime) {
                PENDING_NPC_RESPAWNS.poll();
                if (!pendingRespawn.dimension().equals((Object)world.method_27983())) {
                    deferredOtherDimensions.add(pendingRespawn);
                    continue;
                }
                if (GotCastles.findActiveInvasionForCastle(pendingRespawn.castleId()) != null || GotCastles.isGuardDutyCastleProfile(world, pendingRespawn.castleId(), pendingRespawn.profileId())) continue;
                dueRespawns.add(pendingRespawn);
            }
            PENDING_NPC_RESPAWNS.addAll(deferredOtherDimensions);
        }
        for (PendingNpcRespawn pendingRespawn : dueRespawns) {
            GotCastles.spawnPendingNpcRespawn(world, pendingRespawn);
        }
    }

    private static boolean reconcileLoadedCastlePopulationBatch(class_3218 world, List<CastleInstance> castles, int budget) {
        boolean changed = false;
        int processed = 0;
        while (processed < budget && !castles.isEmpty()) {
            CastleInstance castle;
            if (castlePopulationCursor >= castles.size()) {
                castlePopulationCursor = 0;
            }
            if ("player_claim".equals((castle = castles.get(castlePopulationCursor++)).getDefinitionId())) {
                ++processed;
                continue;
            }
            if (!GotCastles.isCastlePlayerLoaded(world, castle)) {
                ++processed;
                continue;
            }
            if (!GotCastles.isCastleNpcAreaLoaded(world, castle)) {
                ++processed;
                continue;
            }
            if (GotCastles.findActiveInvasionForCastle(castle.getId()) != null || castle.getRaidState() != CastleRaidState.PEACE) {
                ++processed;
                continue;
            }
            long refreshDay = GotCastles.resolveCastleRefreshDay(castle, world);
            boolean needsImmediateRefresh = GotCastles.needsImmediateCastlePopulationRefresh(world, castle);
            if (!needsImmediateRefresh && refreshDay <= castle.getLastPopulationRefreshDay()) {
                ++processed;
                continue;
            }
            GotCastles.reconcileCastlePopulation(world, castle);
            castle.setLastPopulationRefreshDay(Math.max(refreshDay, castle.getLastPopulationRefreshDay()));
            castle.setLastObservedPopulationTimeOfDay(GotCastles.normalizedTimeOfDay(world.method_8532()));
            changed = true;
            ++processed;
        }
        return changed;
    }

    private static boolean reconcileCastlePopulation(class_3218 world, CastleInstance castle) {
        Kingdom kingdom = GotCastles.resolveKingdomForCastle(castle);
        if (kingdom == null) {
            return false;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        List castleNpcs = world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()));
        LoadedCastleNpcIndex loadedNpcIndex = LoadedCastleNpcIndex.from(castleNpcs);
        boolean changed = GotCastles.ensureCastleNpcProfiles(world, castle, castleNpcs);
        Set requiredProfileIds = castle.getCastleNpcProfiles().stream().map(CastleNpcProfile::id).collect(Collectors.toCollection(LinkedHashSet::new));
        LinkedHashSet<String> presentProfileIds = new LinkedHashSet<String>();
        for (AbstractKingdomNpcEntity npc2 : castleNpcs) {
            if (npc2.method_31481()) {
                changed = true;
                continue;
            }
            if (npc2.isIndependentMarriageNpc()) continue;
            if (!(npc2 instanceof SettlerNpcEntity) && !requiredProfileIds.contains(npc2.getCastleNpcId())) {
                npc2.method_31472();
                changed = true;
                continue;
            }
            if (npc2.getCastleNpcId().isBlank()) continue;
            presentProfileIds.add(npc2.getCastleNpcId());
        }
        for (CastleNpcProfile profile : castle.getCastleNpcProfiles()) {
            if (presentProfileIds.contains(profile.id()) || GotCastles.hasPendingNpcRespawn(world, castle.getId(), profile.id())) continue;
            if (GotCastles.spawnCastleResidentNpc(world, castle, kingdom, profile, loadedNpcIndex)) {
                changed = true;
                continue;
            }
            GotCastles.scheduleCastleResidentRespawnRetry(world, castle, profile);
        }
        if (changed) {
            GotCastles.getWorldState(world).method_80();
        }
        return changed;
    }

    private static void spawnPendingNpcRespawn(class_3218 world, PendingNpcRespawn pendingRespawn) {
        boolean alreadyPresent;
        CastleInstance castle = GotCastles.getWorldState(world).get(pendingRespawn.castleId());
        if (castle == null) {
            return;
        }
        if ("player_claim".equals(castle.getDefinitionId())) {
            return;
        }
        Kingdom kingdom = GotCastles.resolveKingdomForCastle(castle);
        if (kingdom == null) {
            return;
        }
        CastleNpcProfile profile = castle.getCastleNpcProfiles().stream().filter(entry -> entry.id().equals(pendingRespawn.profileId())).findFirst().orElse(null);
        if (profile == null) {
            return;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        boolean bl = alreadyPresent = !world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()) && profile.id().equals(npc.getCastleNpcId())).isEmpty();
        if (alreadyPresent) {
            return;
        }
        if (!GotCastles.spawnCastleResidentNpc(world, castle, kingdom, profile)) {
            GotCastles.scheduleCastleResidentRespawnRetry(world, castle, profile);
        }
    }

    private static void scheduleCastleResidentRespawnRetry(class_3218 world, CastleInstance castle, CastleNpcProfile profile) {
        if (world == null || castle == null || profile == null || !GotCastles.shouldRetryCastleResidentRespawn(profile)) {
            return;
        }
        long delayTicks = profile.role() == KingdomNpcRole.FISHERMAN ? 20L : 20L;
        GotCastles.schedulePendingNpcRespawn(world, castle.getId(), profile.id(), delayTicks);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static boolean hasPendingNpcRespawn(class_3218 world, String castleId, String profileId) {
        if (world == null || castleId == null || castleId.isBlank() || profileId == null || profileId.isBlank()) {
            return false;
        }
        PriorityQueue<PendingNpcRespawn> priorityQueue = PENDING_NPC_RESPAWNS;
        synchronized (priorityQueue) {
            for (PendingNpcRespawn pendingRespawn : PENDING_NPC_RESPAWNS) {
                if (!pendingRespawn.dimension().equals((Object)world.method_27983()) || !castleId.equals(pendingRespawn.castleId()) || !profileId.equals(pendingRespawn.profileId())) continue;
                return true;
            }
        }
        return false;
    }

    private static boolean shouldRetryCastleResidentRespawn(CastleNpcProfile profile) {
        if (profile == null) {
            return false;
        }
        if (profile.role() == KingdomNpcRole.FISHERMAN) {
            return true;
        }
        if (profile.role() == KingdomNpcRole.LORD) {
            return true;
        }
        if (profile.role().isGuardDuty()) {
            return true;
        }
        if (profile.pointOfInterest() == null) {
            return false;
        }
        return switch (profile.role()) {
            case KingdomNpcRole.BLACKSMITH, KingdomNpcRole.ARENA_MASTER, KingdomNpcRole.CART_RIDER, KingdomNpcRole.CIVILIAN -> true;
            default -> false;
        };
    }

    /*
     * WARNING - void declaration
     */
    private static boolean ensureCastleNpcProfiles(class_3218 world, CastleInstance castle, @Nullable List<? extends AbstractKingdomNpcEntity> loadedNpcs) {
        Kingdom kingdom;
        boolean changed = GotCastles.ensureArenaMasterProfiles(world, castle);
        LinkedHashMap<String, CastleNpcProfile> profilesById = new LinkedHashMap<String, CastleNpcProfile>();
        for (CastleNpcProfile profile2 : castle.getCastleNpcProfiles()) {
            profilesById.put(profile2.id(), profile2);
        }
        List<CastleNpcProfile> requiredProfiles = GotCastles.requiredCastleNpcProfiles(world, castle);
        Set requiredProfileIds = requiredProfiles.stream().map(CastleNpcProfile::id).collect(Collectors.toCollection(LinkedHashSet::new));
        if (profilesById.keySet().removeIf(profileId -> !requiredProfileIds.contains(profileId))) {
            changed = true;
        }
        for (CastleNpcProfile castleNpcProfile : requiredProfiles) {
            CastleNpcProfile existing = (CastleNpcProfile)profilesById.get(castleNpcProfile.id());
            if (existing == null) {
                profilesById.put(castleNpcProfile.id(), castleNpcProfile);
                changed = true;
                continue;
            }
            CastleNpcProfile updated = existing;
            if (existing.role() == KingdomNpcRole.LORD && !castleNpcProfile.displayName().isBlank() && !castleNpcProfile.displayName().equals(existing.displayName())) {
                updated = new CastleNpcProfile(existing.id(), existing.uuid(), existing.roleId(), existing.seedOffset(), castleNpcProfile.displayName(), existing.attributes(), existing.appearance(), existing.nobleSpouse(), castleNpcProfile.spouseProfileId().isBlank() ? existing.spouseProfileId() : castleNpcProfile.spouseProfileId(), existing.pointOfInterest(), existing.preferredSpawnPos(), existing.pointOfInterestRadius());
            }
            if (!updated.attributes().isAssigned() && castleNpcProfile.attributes().isAssigned()) {
                updated = new CastleNpcProfile(updated.id(), updated.uuid(), updated.roleId(), updated.seedOffset(), updated.displayName(), castleNpcProfile.attributes(), updated.appearance(), updated.nobleSpouse(), updated.spouseProfileId(), updated.pointOfInterest(), updated.preferredSpawnPos(), updated.pointOfInterestRadius());
            }
            if (!updated.appearance().isAssigned() && castleNpcProfile.appearance().isAssigned()) {
                updated = new CastleNpcProfile(updated.id(), updated.uuid(), updated.roleId(), updated.seedOffset(), updated.displayName(), updated.attributes(), castleNpcProfile.appearance(), updated.nobleSpouse(), updated.spouseProfileId(), updated.pointOfInterest(), updated.preferredSpawnPos(), updated.pointOfInterestRadius());
            }
            if (updated.nobleSpouse() != castleNpcProfile.nobleSpouse() || !updated.spouseProfileId().equals(castleNpcProfile.spouseProfileId())) {
                updated = new CastleNpcProfile(updated.id(), updated.uuid(), updated.roleId(), updated.seedOffset(), updated.displayName(), updated.attributes(), updated.appearance(), castleNpcProfile.nobleSpouse(), castleNpcProfile.spouseProfileId(), updated.pointOfInterest(), updated.preferredSpawnPos(), updated.pointOfInterestRadius());
            }
            if (updated.equals(existing)) continue;
            profilesById.put(castleNpcProfile.id(), updated);
            changed = true;
        }
        if (loadedNpcs != null && !loadedNpcs.isEmpty()) {
            EnumMap<KingdomNpcRole, Deque> unassignedByRole = new EnumMap<KingdomNpcRole, Deque>(KingdomNpcRole.class);
            for (Iterator<Object> profile3 : profilesById.values()) {
                unassignedByRole.computeIfAbsent(((CastleNpcProfile)((Object)profile3)).role(), ignored -> new ArrayDeque()).addLast(profile3);
            }
            List<AbstractKingdomNpcEntity> list = loadedNpcs.stream().filter(npc -> !(npc instanceof SettlerNpcEntity) && !npc.isIndependentMarriageNpc()).sorted(Comparator.comparingInt(class_1297::method_5628)).map(npc -> npc).toList();
            for (AbstractKingdomNpcEntity npc2 : list) {
                Deque queue;
                CastleNpcProfile matched;
                String string = npc2.getCastleNpcId();
                CastleNpcProfile castleNpcProfile = matched = string.isBlank() ? null : (CastleNpcProfile)profilesById.get(string);
                if (matched == null) {
                    queue = (Deque)unassignedByRole.get((Object)npc2.getNpcRole());
                    while (queue != null && !queue.isEmpty()) {
                        CastleNpcProfile candidate = (CastleNpcProfile)queue.removeFirst();
                        if (candidate.id().isBlank()) continue;
                        matched = candidate;
                        break;
                    }
                }
                if (matched == null) continue;
                if (npc2.getCastleNpcId().isBlank()) {
                    npc2.setCastleNpcId(matched.id());
                }
                if (!matched.uuid().equals(npc2.method_5667())) {
                    if (matched.role() == KingdomNpcRole.LORD) {
                        npc2.method_31472();
                        changed = true;
                        continue;
                    }
                    profilesById.put(matched.id(), new CastleNpcProfile(matched.id(), npc2.method_5667(), matched.roleId(), matched.seedOffset(), matched.displayName(), matched.attributes(), matched.appearance(), matched.nobleSpouse(), matched.spouseProfileId(), matched.pointOfInterest(), matched.preferredSpawnPos(), matched.pointOfInterestRadius()));
                    changed = true;
                }
                if ((queue = (Deque)unassignedByRole.get((Object)matched.role())) == null) continue;
                String matchedId = matched.id();
                queue.removeIf(profile -> profile.id().equals(matchedId));
            }
        }
        if ((kingdom = GotCastles.resolveKingdomForCastle(castle)) != null) {
            LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();
            for (CastleNpcProfile profile4 : profilesById.values()) {
                if (profile4.displayName().isBlank()) continue;
                linkedHashSet.add(profile4.displayName());
            }
            for (CastleNpcProfile profile2 : new ArrayList(profilesById.values())) {
                void var11_22;
                String string = profile2.displayName();
                if (profile2.role() == KingdomNpcRole.LORD) {
                    String lordName;
                    String string2 = lordName = !string.isBlank() ? string : castle.getLordName();
                    if (lordName.isBlank()) {
                        lordName = GotCastles.generateFallbackLordName(castle);
                        castle.setLordName(lordName);
                        changed = true;
                    }
                    String string3 = lordName;
                } else if (string.isBlank()) {
                    String string4 = GotCastleOwnerNames.createCommonerName(kingdom, castle.getId() + "|" + profile2.id(), linkedHashSet);
                    changed = true;
                }
                if (!var11_22.equals(profile2.displayName())) {
                    profilesById.put(profile2.id(), new CastleNpcProfile(profile2.id(), profile2.uuid(), profile2.roleId(), profile2.seedOffset(), (String)var11_22, profile2.attributes(), profile2.appearance(), profile2.nobleSpouse(), profile2.spouseProfileId(), profile2.pointOfInterest(), profile2.preferredSpawnPos(), profile2.pointOfInterestRadius()));
                    changed = true;
                }
                if (var11_22.isBlank()) continue;
                linkedHashSet.add((String)var11_22);
            }
        }
        CharacterSelectionOption characterSelectionOption = GotCastles.characterOptionForCastle(castle);
        for (CastleNpcProfile profile2 : new ArrayList(profilesById.values())) {
            CastleNpcProfile castleNpcProfile = GotCastles.ensureCastleProfileAttributes(profile2, characterSelectionOption, castle.getId() + "|" + profile2.id());
            if (castleNpcProfile.equals(profile2)) continue;
            profilesById.put(castleNpcProfile.id(), castleNpcProfile);
            changed = true;
        }
        class_2960 castleKingdomId = GotCastles.resolveCastleKingdomId(castle);
        for (CastleNpcProfile castleNpcProfile : new ArrayList(profilesById.values())) {
            CastleNpcProfile withAppearance = GotCastles.ensureCastleProfileAppearance(castleNpcProfile, castleKingdomId, castle.getId() + "|" + castleNpcProfile.id());
            if (withAppearance.equals(castleNpcProfile)) continue;
            profilesById.put(withAppearance.id(), withAppearance);
            changed = true;
        }
        if (loadedNpcs != null) {
            for (AbstractKingdomNpcEntity abstractKingdomNpcEntity : loadedNpcs) {
                CastleNpcProfile profile6;
                if (abstractKingdomNpcEntity instanceof SettlerNpcEntity || (profile6 = (CastleNpcProfile)profilesById.get(abstractKingdomNpcEntity.getCastleNpcId())) == null) continue;
                if (!profile6.displayName().isBlank()) {
                    abstractKingdomNpcEntity.method_5665((class_2561)class_2561.method_43470((String)profile6.displayName()));
                }
                GotCastles.applyNpcAttributes((AbstractKingdomHumanoidEntity)abstractKingdomNpcEntity, profile6);
                GotCastles.applyNpcAppearance(abstractKingdomNpcEntity, profile6);
            }
        }
        if (changed) {
            castle.setCastleNpcProfiles(new ArrayList<CastleNpcProfile>(profilesById.values()));
        }
        return changed;
    }

    private static boolean ensureArenaMasterProfiles(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null || castle.getCityStructureClaims().isEmpty()) {
            return false;
        }
        Map profilesById = castle.getCastleNpcProfiles().stream().collect(Collectors.toMap(CastleNpcProfile::id, profile -> profile, (first, second) -> first, LinkedHashMap::new));
        boolean changed = false;
        int arenaIndex = 0;
        for (CityStructureClaim claim : castle.getCityStructureClaims()) {
            if (!GotCastles.isArenaStructure(claim) || claim.ownerProfileId().isBlank()) continue;
            CastleNpcProfile existing = (CastleNpcProfile)profilesById.get(claim.ownerProfileId());
            class_2338 pointOfInterest = new class_2338(claim.origin().method_10263() + Math.max(0, claim.size().method_10263() / 2), claim.origin().method_10264(), claim.origin().method_10260() + Math.max(0, claim.size().method_10260() / 2));
            class_2338 spawnPos = GotCastles.findStructureInteriorSpawnPos(world, claim.origin(), claim.size(), pointOfInterest);
            if (existing == null) {
                profilesById.put(claim.ownerProfileId(), new CastleNpcProfile(claim.ownerProfileId(), UUID.nameUUIDFromBytes((claim.ownerProfileId() + ":" + castle.getDefinitionId()).getBytes(StandardCharsets.UTF_8)), KingdomNpcRole.ARENA_MASTER.name(), 227 + arenaIndex, "", GotCastles.generateNpcAttributesForRole(GotCastles.characterOptionForCastle(castle), castle.getId() + "|" + claim.ownerProfileId(), KingdomNpcRole.ARENA_MASTER), NpcAppearanceProfile.generate(GotCastles.resolveCastleKingdomId(castle), castle.getId() + "|" + claim.ownerProfileId() + "|appearance"), false, "", pointOfInterest, spawnPos, 32));
                changed = true;
            } else if (existing.role() != KingdomNpcRole.ARENA_MASTER || existing.pointOfInterest() == null || existing.preferredSpawnPos() == null) {
                profilesById.put(existing.id(), new CastleNpcProfile(existing.id(), existing.uuid(), KingdomNpcRole.ARENA_MASTER.name(), existing.seedOffset(), existing.displayName(), existing.attributes(), existing.appearance(), existing.nobleSpouse(), existing.spouseProfileId(), existing.pointOfInterest() == null ? pointOfInterest : existing.pointOfInterest(), existing.preferredSpawnPos() == null ? spawnPos : existing.preferredSpawnPos(), Math.max(existing.pointOfInterestRadius(), 32)));
                changed = true;
            }
            ++arenaIndex;
        }
        if (changed) {
            castle.setCastleNpcProfiles(new ArrayList<CastleNpcProfile>(profilesById.values()));
        }
        return changed;
    }

    private static void reconcileHouseLordAssignments(class_3218 world) {
        GotCastles.reconcileHouseLordAssignments(world, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void reconcileHouseLordAssignments(class_3218 world, boolean forceInitialDistribution) {
        if (world == null) {
            return;
        }
        if (GotCastles.isReconcilingHouseLordAssignments()) {
            return;
        }
        HOUSE_LORD_RECONCILE_DEPTH.set(HOUSE_LORD_RECONCILE_DEPTH.get() + 1);
        try {
            CastleWorldState state = GotCastles.getWorldState(world);
            LinkedHashMap<String, List> castlesByHouse = new LinkedHashMap<String, List>();
            for (CastleInstance castle2 : state.getAll()) {
                String houseId;
                String kingdomId;
                String key;
                if (castle2 == null || "player_claim".equals(castle2.getDefinitionId()) || castle2.getOwnerPlayerUuid() != null || (key = HouseLordWorldState.houseKey(kingdomId = GotCastles.canonicalKingdomId(castle2.getOwnerKingdomId()), houseId = GotCastles.resolveOwnedHouseId(castle2.getOwnerHouseId(), kingdomId, ""))).isBlank()) continue;
                castlesByHouse.computeIfAbsent(key, ignored -> new ArrayList()).add(castle2);
            }
            boolean changed = false;
            for (List houseCastles : castlesByHouse.values()) {
                String currentLord;
                if (houseCastles.isEmpty()) continue;
                houseCastles.sort(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.getTier())).thenComparing(CastleInstance::getId));
                CastleInstance seat = (CastleInstance)houseCastles.getFirst();
                String kingdomId = GotCastles.canonicalKingdomId(seat.getOwnerKingdomId());
                String houseId = GotCastles.resolveOwnedHouseId(seat.getOwnerHouseId(), kingdomId, "");
                List<HouseLordWorldState.HouseLordProfile> lords = GotCastles.ensureHouseLordProfiles(world, kingdomId, houseId);
                if (lords.isEmpty()) continue;
                Set lordNames = lords.stream().map(HouseLordWorldState.HouseLordProfile::displayName).filter(name -> !name.isBlank()).collect(Collectors.toCollection(LinkedHashSet::new));
                if (forceInitialDistribution) {
                    for (int index = 0; index < houseCastles.size(); ++index) {
                        HouseLordWorldState.HouseLordProfile desiredLord;
                        CastleInstance castle3 = (CastleInstance)houseCastles.get(index);
                        if (!GotCastles.assignCastleLordName(castle3, (desiredLord = lords.get(index % lords.size())).displayName())) continue;
                        changed = true;
                    }
                    continue;
                }
                LinkedHashMap<String, Integer> assignmentCounts = new LinkedHashMap<String, Integer>();
                for (HouseLordWorldState.HouseLordProfile lord2 : lords) {
                    assignmentCounts.put(lord2.displayName(), 0);
                }
                for (CastleInstance castle4 : houseCastles) {
                    currentLord = castle4.getLordName();
                    if (!lordNames.contains(currentLord)) continue;
                    assignmentCounts.computeIfPresent(currentLord, (ignored, count) -> count + 1);
                }
                for (CastleInstance castle4 : houseCastles) {
                    HouseLordWorldState.HouseLordProfile desiredLord;
                    currentLord = castle4.getLordName();
                    if (!currentLord.isBlank() && lordNames.contains(currentLord) || !GotCastles.assignCastleLordName(castle4, (desiredLord = lords.stream().min(Comparator.comparingInt(lord -> assignmentCounts.getOrDefault(lord.displayName(), 0)).thenComparingInt(HouseLordWorldState.HouseLordProfile::index)).orElse(lords.getFirst())).displayName())) continue;
                    assignmentCounts.computeIfPresent(desiredLord.displayName(), (ignored, count) -> count + 1);
                    changed = true;
                }
            }
            if (changed) {
                state.method_80();
            }
        }
        finally {
            int remainingDepth = HOUSE_LORD_RECONCILE_DEPTH.get() - 1;
            if (remainingDepth <= 0) {
                HOUSE_LORD_RECONCILE_DEPTH.remove();
            } else {
                HOUSE_LORD_RECONCILE_DEPTH.set(remainingDepth);
            }
        }
    }

    private static boolean isReconcilingHouseLordAssignments() {
        return HOUSE_LORD_RECONCILE_DEPTH.get() > 0;
    }

    private static boolean assignCastleLordName(CastleInstance castle, String lordName) {
        if (castle == null || lordName == null || lordName.equals(castle.getLordName())) {
            return false;
        }
        castle.setLordName(lordName);
        castle.setLastPopulationRefreshDay(-1L);
        return true;
    }

    private static List<HouseLordWorldState.HouseLordProfile> ensureHouseLordProfiles(class_3218 world, String kingdomId, String houseId) {
        return GotCastles.ensureHouseLordProfiles(world, kingdomId, houseId, null);
    }

    private static List<HouseLordWorldState.HouseLordProfile> ensureHouseLordProfiles(class_3218 world, String kingdomId, String houseId, @Nullable StrategicCastleSnapshotSet strategicCastles) {
        ArrayList<HouseLordWorldState.HouseLordProfile> lords;
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        if (world == null || canonicalKingdomId.isBlank() || resolvedHouseId.isBlank()) {
            return List.of();
        }
        HouseLordWorldState lordState = HouseLordWorldState.get(world);
        List<HouseLordWorldState.HouseLordProfile> existing = lordState.getLords(canonicalKingdomId, resolvedHouseId);
        int targetLordCount = Math.max(0, lordState.getLordCap(canonicalKingdomId, resolvedHouseId, 3));
        CharacterSelectionOption lordOption = GotCastles.characterOptionForHouse(canonicalKingdomId, resolvedHouseId);
        class_2960 parsedKingdomId = class_2960.method_12829((String)canonicalKingdomId);
        Kingdom kingdom = parsedKingdomId == null ? null : GotKingdoms.get(parsedKingdomId);
        String houseSurname = GotCastles.normalizeHouseSurname(GotCastles.resolveHouseName(resolvedHouseId, canonicalKingdomId));
        LinkedHashSet<String> usedNames = new LinkedHashSet<String>();
        for (HouseLordWorldState.HouseLordProfile lord2 : existing) {
            if (!lord2.displayName().isBlank()) {
                usedNames.add(lord2.displayName());
            }
            if (!lord2.hasSpouse() || lord2.spouseDisplayName().isBlank()) continue;
            usedNames.add(lord2.spouseDisplayName());
        }
        if (existing.size() >= targetLordCount) {
            lords = existing.stream().sorted(Comparator.comparingInt(HouseLordWorldState.HouseLordProfile::index)).limit(targetLordCount).toList();
            List<HouseLordWorldState.HouseLordProfile> upgraded = lords.stream().map(lord -> GotCastles.ensureHouseLordAttributes(lord, lordOption, kingdom, houseSurname, usedNames)).toList();
            upgraded = GotCastles.assignHouseLeader(world, canonicalKingdomId, resolvedHouseId, upgraded, strategicCastles);
            if (!(upgraded = GotCastles.normalizeSpecialHouseLordProfiles(canonicalKingdomId, resolvedHouseId, upgraded, kingdom)).equals(lords)) {
                lordState.putLords(canonicalKingdomId, resolvedHouseId, upgraded);
            }
            return upgraded;
        }
        if (kingdom == null) {
            return existing;
        }
        lords = existing.stream().map(lord -> GotCastles.ensureHouseLordAttributes(lord, lordOption, kingdom, houseSurname, usedNames)).collect(Collectors.toCollection(ArrayList::new));
        for (int index = lords.size(); index < targetLordCount; ++index) {
            String id = "house_lord:" + canonicalKingdomId + ":" + resolvedHouseId + ":" + index;
            String displayName = GotCastleOwnerNames.createHouseLordName(kingdom, houseSurname, id, usedNames);
            usedNames.add(displayName);
            lords.add(new HouseLordWorldState.HouseLordProfile(id, HouseLordWorldState.stableUuid(canonicalKingdomId, resolvedHouseId, index), canonicalKingdomId, resolvedHouseId, displayName, index == 0, index, NpcAttributeProfile.generate(lordOption, id, 20, 30), "", null, "", NpcAttributeProfile.EMPTY, NpcAppearanceProfile.EMPTY));
        }
        lords = lords.stream().map(lord -> GotCastles.ensureHouseLordAttributes(lord, lordOption, kingdom, houseSurname, usedNames)).collect(Collectors.toCollection(ArrayList::new));
        lords = new ArrayList<HouseLordWorldState.HouseLordProfile>(GotCastles.assignHouseLeader(world, canonicalKingdomId, resolvedHouseId, (List<HouseLordWorldState.HouseLordProfile>)lords, strategicCastles));
        lords = new ArrayList<HouseLordWorldState.HouseLordProfile>(GotCastles.normalizeSpecialHouseLordProfiles(canonicalKingdomId, resolvedHouseId, lords, kingdom));
        lords.sort(Comparator.comparingInt(HouseLordWorldState.HouseLordProfile::index));
        lordState.putLords(canonicalKingdomId, resolvedHouseId, lords);
        return List.copyOf(lords);
    }

    private static List<HouseLordWorldState.HouseLordProfile> normalizeSpecialHouseLordProfiles(String kingdomId, String houseId, List<HouseLordWorldState.HouseLordProfile> lords, @Nullable Kingdom kingdom) {
        if (lords == null || lords.isEmpty() || !"agob:wildling".equals(kingdomId) || !"wildling_free_folk".equals(houseId) || kingdom == null) {
            return lords == null ? List.of() : List.copyOf(lords);
        }
        List<HouseLordWorldState.HouseLordProfile> sorted = lords.stream().sorted(Comparator.comparingInt(HouseLordWorldState.HouseLordProfile::index)).toList();
        LinkedHashSet<String> usedNames = new LinkedHashSet<String>();
        usedNames.add("Mance Raider");
        ArrayList<HouseLordWorldState.HouseLordProfile> normalized = new ArrayList<HouseLordWorldState.HouseLordProfile>(sorted.size());
        for (HouseLordWorldState.HouseLordProfile lord : sorted) {
            boolean leader = lord.leader();
            String displayName = leader ? "Mance Raider" : GotCastleOwnerNames.createCommonerName(kingdom, lord.id(), usedNames);
            usedNames.add(displayName);
            normalized.add(new HouseLordWorldState.HouseLordProfile(lord.id(), lord.uuid(), lord.kingdomId(), lord.houseId(), displayName, leader, lord.index(), lord.attributes(), lord.spouseId(), lord.spouseUuid(), lord.spouseDisplayName(), lord.spouseAttributes(), lord.spouseAppearance()));
        }
        return List.copyOf(normalized);
    }

    public static List<HouseLordWorldState.HouseLordProfile> getHouseLordProfiles(class_3218 world, String kingdomId, String houseId) {
        return GotCastles.ensureHouseLordProfiles(world, kingdomId, houseId);
    }

    public static List<HouseLordWorldState.HouseLordProfile> addHouseLordProfiles(class_3218 world, String kingdomId, String houseId, int amount) {
        if (world == null || amount <= 0) {
            return List.of();
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        if (canonicalKingdomId.isBlank() || resolvedHouseId.isBlank()) {
            return List.of();
        }
        HouseLordWorldState lordState = HouseLordWorldState.get(world);
        int currentCap = lordState.getLordCap(canonicalKingdomId, resolvedHouseId, 3);
        lordState.setLordCap(canonicalKingdomId, resolvedHouseId, currentCap + amount);
        List<HouseLordWorldState.HouseLordProfile> lords = GotCastles.ensureHouseLordProfiles(world, canonicalKingdomId, resolvedHouseId);
        PlayerHouseService.broadcast(world.method_8503());
        GotCastles.syncCastleMapToPlayers(world);
        return lords.stream().sorted(Comparator.comparingInt(HouseLordWorldState.HouseLordProfile::index).reversed()).limit(amount).sorted(Comparator.comparingInt(HouseLordWorldState.HouseLordProfile::index)).toList();
    }

    public static HouseLordWorldState.HouseLordProfile removeHouseLordProfileByToken(class_3218 world, String kingdomId, String houseId, String npcToken) {
        if (world == null || npcToken == null || npcToken.isBlank()) {
            return null;
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        List<HouseLordWorldState.HouseLordProfile> lords = GotCastles.ensureHouseLordProfiles(world, canonicalKingdomId, resolvedHouseId);
        String normalized = GotCastles.normalizeCommandToken(npcToken);
        HouseLordWorldState.HouseLordProfile matched = lords.stream().filter(lord -> lord.id().equals(npcToken) || GotCastles.normalizeCommandToken(lord.id()).equals(normalized) || GotCastles.normalizeCommandToken(lord.displayName()).equals(normalized)).findFirst().orElse(null);
        if (matched == null) {
            return null;
        }
        GotCastles.removeLoadedNpcByProfile(world, matched.id());
        GotCastles.removeHouseLordProfile(world, canonicalKingdomId, resolvedHouseId, matched.id(), true);
        PlayerHouseService.broadcast(world.method_8503());
        GotCastles.syncCastleMapToPlayers(world);
        return matched;
    }

    public static int clearHouseLordProfiles(class_3218 world, String kingdomId, String houseId) {
        if (world == null) {
            return 0;
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        HouseLordWorldState lordState = HouseLordWorldState.get(world);
        List<HouseLordWorldState.HouseLordProfile> lords = lordState.getLords(canonicalKingdomId, resolvedHouseId);
        for (HouseLordWorldState.HouseLordProfile lord : lords) {
            GotCastles.removeLoadedNpcByProfile(world, lord.id());
            if (lord.spouseId().isBlank()) continue;
            GotCastles.removeLoadedNpcByProfile(world, lord.spouseId());
        }
        lordState.setLordCap(canonicalKingdomId, resolvedHouseId, 0);
        lordState.putLords(canonicalKingdomId, resolvedHouseId, List.of());
        return lords.size();
    }

    public static HouseDisbandResult disbandHouseHoldings(class_3218 world, String kingdomId, String houseId) {
        if (world == null) {
            return new HouseDisbandResult(0, 0, false);
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        if (canonicalKingdomId.isBlank() || resolvedHouseId.isBlank()) {
            return new HouseDisbandResult(0, 0, false);
        }
        List<String> alternatives = GotCastles.alternativeHouseIds(world, canonicalKingdomId, resolvedHouseId);
        String replacementHouseId = alternatives.isEmpty() ? "" : alternatives.getFirst();
        int reassigned = 0;
        int unclaimed = 0;
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if (!canonicalKingdomId.equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId())) || !resolvedHouseId.equals(GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), castle.getOwnerKingdomId(), ""))) continue;
            GotCastles.removeLoadedCastleNpcs(world, castle);
            if (replacementHouseId.isBlank()) {
                GotCastles.setCastleUncaptured(world, castle);
                ++unclaimed;
                continue;
            }
            castle.setOwnerHouseId(replacementHouseId);
            castle.setOwnerPlayerUuid(null);
            castle.setLordName("");
            castle.setCastleNpcProfiles(GotCastles.preservedCastleResidentProfiles(castle));
            castle.setLastPopulationRefreshDay(-1L);
            GotVillages.synchronizeVillageAfterCastleOwnershipChange(world, castle);
            if (!"player_claim".equals(castle.getDefinitionId())) {
                GotCastles.respawnCastlePopulation(world, castle);
            }
            ++reassigned;
        }
        GotCastles.getWorldState(world).method_80();
        GotCastles.syncStateToPlayers(world);
        GotCastles.syncCastleMapToPlayers(world);
        GotCastles.syncExternalClaimOutlines(world);
        return new HouseDisbandResult(reassigned, unclaimed, replacementHouseId.isBlank());
    }

    public static int unclaimKingdomHoldings(class_3218 world, String kingdomId) {
        if (world == null || kingdomId == null || kingdomId.isBlank()) {
            return 0;
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        int unclaimed = 0;
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if (!canonicalKingdomId.equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId()))) continue;
            GotCastles.removeLoadedCastleNpcs(world, castle);
            GotCastles.setCastleUncaptured(world, castle);
            ++unclaimed;
        }
        GotCastles.syncStateToPlayers(world);
        GotCastles.syncCastleMapToPlayers(world);
        GotCastles.syncExternalClaimOutlines(world);
        return unclaimed;
    }

    private static List<String> alternativeHouseIds(class_3218 world, String kingdomId, String excludedHouseId) {
        LinkedHashSet<String> alternatives = new LinkedHashSet<String>();
        class_2960 parsedKingdomId = class_2960.method_12829((String)kingdomId);
        if (parsedKingdomId != null) {
            for (CharacterSelectionOption option : GotCharacterOptions.getByKingdom(parsedKingdomId)) {
                if (option.id().equals(excludedHouseId)) continue;
                alternatives.add(option.id());
            }
        }
        for (PlayerHouseDefinition customHouse : PlayerHouseWorldState.get(world).getAll()) {
            if (!kingdomId.equals(GotCastles.canonicalKingdomId(customHouse.kingdomId())) || customHouse.houseId().equals(excludedHouseId)) continue;
            alternatives.add(customHouse.houseId());
        }
        return List.copyOf(alternatives);
    }

    private static void removeLoadedNpcByProfile(class_3218 world, String profileId) {
        if (world == null || profileId == null || profileId.isBlank()) {
            return;
        }
        for (class_1297 entity : world.method_27909()) {
            AbstractKingdomNpcEntity npc;
            if (!(entity instanceof AbstractKingdomNpcEntity) || !profileId.equals((npc = (AbstractKingdomNpcEntity)entity).getCastleNpcId())) continue;
            npc.method_5650(class_1297.class_5529.field_26999);
        }
    }

    private static void removeLoadedCastleNpcs(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null) {
            return;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)192.0, (double)96.0, (double)192.0);
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()))) {
            npc2.method_5650(class_1297.class_5529.field_26999);
        }
    }

    private static String normalizeCommandToken(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", "_").replaceAll("^_+|_+$", "");
    }

    private static void removeHouseLordProfile(class_3218 world, String kingdomId, String houseId, String profileId, boolean rebalanceLeadership) {
        if (world == null || profileId == null || profileId.isBlank()) {
            return;
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        HouseLordWorldState lordState = HouseLordWorldState.get(world);
        int currentCap = lordState.getLordCap(canonicalKingdomId, resolvedHouseId, 3);
        ArrayList<HouseLordWorldState.HouseLordProfile> updated = lordState.getLords(canonicalKingdomId, resolvedHouseId).stream().filter(lord -> !profileId.equals(lord.id())).collect(Collectors.toCollection(ArrayList::new));
        if (rebalanceLeadership && !updated.isEmpty()) {
            updated = new ArrayList<HouseLordWorldState.HouseLordProfile>(GotCastles.assignHouseLeader(world, canonicalKingdomId, resolvedHouseId, (List<HouseLordWorldState.HouseLordProfile>)updated));
        }
        lordState.setLordCap(canonicalKingdomId, resolvedHouseId, Math.max(0, currentCap - 1));
        lordState.putLords(canonicalKingdomId, resolvedHouseId, List.copyOf(updated));
    }

    public static boolean isKingdomLeader(class_3218 world, String kingdomId, String houseId, String lordName) {
        if (world == null) {
            return false;
        }
        KingdomLeaderWorldState.KingdomLeaderProfile leader = GotCastles.resolveOrCreateKingdomLeader(world, kingdomId);
        if (leader == null) {
            return false;
        }
        return GotCastles.canonicalKingdomId(kingdomId).equals(GotCastles.canonicalKingdomId(leader.kingdomId())) && GotCastles.resolveOwnedHouseId(houseId, GotCastles.canonicalKingdomId(kingdomId), "").equals(GotCastles.resolveOwnedHouseId(leader.houseId(), GotCastles.canonicalKingdomId(leader.kingdomId()), "")) && lordName != null && lordName.equals(leader.lordName());
    }

    public static boolean isPlayerHouseLeader(class_3218 world, UUID playerUuid, String kingdomId, String houseId) {
        if (world == null || playerUuid == null) {
            return false;
        }
        String seatCastleId = GotCastles.houseSeatCastleIdIncludingPlayer(world, kingdomId, houseId);
        if (seatCastleId.isBlank()) {
            return false;
        }
        CastleInstance seatCastle = GotCastles.getWorldState(world).get(seatCastleId);
        return seatCastle != null && playerUuid.equals(seatCastle.getOwnerPlayerUuid());
    }

    public static boolean hasPlayerHouseLeader(class_3218 world, String kingdomId, String houseId) {
        return GotCastles.resolvePlayerHouseSeatCastle(world, kingdomId, houseId) != null;
    }

    private static List<HouseLordWorldState.HouseLordProfile> houseLordProfilesForCastle(class_3218 world, CastleInstance castle) {
        String houseId;
        if (world == null || castle == null || castle.getOwnerPlayerUuid() != null) {
            return List.of();
        }
        String kingdomId = GotCastles.canonicalKingdomId(castle.getOwnerKingdomId());
        List<HouseLordWorldState.HouseLordProfile> lords = GotCastles.ensureHouseLordProfiles(world, kingdomId, houseId = GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), kingdomId, ""));
        if (lords.isEmpty()) {
            return List.of();
        }
        String seatCastleId = GotCastles.houseSeatCastleId(world, kingdomId, houseId);
        ArrayList<HouseLordWorldState.HouseLordProfile> spawning = new ArrayList<HouseLordWorldState.HouseLordProfile>();
        for (HouseLordWorldState.HouseLordProfile lord : lords) {
            String spawnCastleId = GotCastles.primarySpawnCastleIdForLord(world, kingdomId, houseId, lord.displayName(), seatCastleId);
            if (!castle.getId().equals(spawnCastleId)) continue;
            spawning.add(lord);
        }
        return List.copyOf(spawning);
    }

    private static String houseSeatCastleId(class_3218 world, String kingdomId, String houseId) {
        List<StrategicCastleSnapshot> castles = GotCastles.ownedStrategicCastlesForHouse(world, kingdomId, houseId);
        if (castles.isEmpty()) {
            return "";
        }
        castles.sort(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.tier())).thenComparing(StrategicCastleSnapshot::castleId));
        return castles.getFirst().castleId();
    }

    private static String houseSeatCastleIdIncludingPlayer(class_3218 world, String kingdomId, String houseId) {
        if (world == null) {
            return "";
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        if (canonicalKingdomId.isBlank() || resolvedHouseId.isBlank()) {
            return "";
        }
        boolean dynamicKingdom = GotKingdoms.isDynamicKingdom(class_2960.method_12829((String)canonicalKingdomId));
        return GotCastles.getWorldState(world).getAll().stream().filter(castle -> dynamicKingdom || !"player_claim".equals(castle.getDefinitionId())).filter(castle -> canonicalKingdomId.equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId()))).filter(castle -> resolvedHouseId.equals(GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), canonicalKingdomId, ""))).min(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.getTier())).thenComparing(CastleInstance::getId)).map(CastleInstance::getId).orElse("");
    }

    private static String primarySpawnCastleIdForLord(class_3218 world, String kingdomId, String houseId, String lordName, String fallbackCastleId) {
        List<StrategicCastleSnapshot> allHouseCastles = GotCastles.ownedStrategicCastlesForHouse(world, kingdomId, houseId).stream().sorted(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.tier())).thenComparing(StrategicCastleSnapshot::castleId)).toList();
        return GotCastles.primarySpawnCastleIdForLord(world, lordName, fallbackCastleId, allHouseCastles);
    }

    private static String primarySpawnCastleIdForLord(class_3218 world, String lordName, String fallbackCastleId, List<StrategicCastleSnapshot> houseCastles) {
        if (lordName == null || lordName.isBlank()) {
            return fallbackCastleId == null ? "" : fallbackCastleId;
        }
        List<StrategicCastleSnapshot> allHouseCastles = houseCastles.stream().sorted(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.tier())).thenComparing(StrategicCastleSnapshot::castleId)).toList();
        if (allHouseCastles.isEmpty()) {
            return fallbackCastleId == null ? "" : fallbackCastleId;
        }
        long routeDay = GotCastles.currentLordRouteDay(world);
        List<StrategicCastleSnapshot> ownedCastles = allHouseCastles.stream().filter(castle -> lordName.equals(castle.lordName())).toList();
        if (!ownedCastles.isEmpty()) {
            return ownedCastles.get(Math.floorMod(routeDay, ownedCastles.size())).castleId();
        }
        int index = GotCastles.deterministicLordRouteIndex(lordName, routeDay, allHouseCastles.size());
        return allHouseCastles.get(index).castleId();
    }

    private static long currentLordRouteDay(class_3218 world) {
        if (world == null) {
            return 0L;
        }
        return GotGameRules.affectedByTimeSkip(world) ? GotGameRules.skipAwareDay(world.method_8510(), world.method_8532()) : GotGameRules.regularDay(world.method_8510());
    }

    private static int deterministicLordRouteIndex(String lordName, long day, int bound) {
        if (bound <= 1) {
            return 0;
        }
        long value = 0x9E3779B97F4A7C15L ^ day;
        for (int index = 0; index < lordName.length(); ++index) {
            value ^= (long)lordName.charAt(index);
            value *= -4658895280553007687L;
            value ^= value >>> 27;
        }
        value ^= value >>> 31;
        return Math.floorMod(value, bound);
    }

    private static int lordPartyCapacity(class_3218 world, String kingdomId, String lordName, String fallbackCastleId) {
        if (world == null || lordName == null || lordName.isBlank()) {
            return fallbackCastleId == null || fallbackCastleId.isBlank() ? 0 : 1;
        }
        return GotCastles.lordPartyCapacity(GotCastles.snapshotStrategicCastles(world), kingdomId, lordName, fallbackCastleId);
    }

    private static int lordPartyCapacity(StrategicCastleSnapshotSet strategicCastles, String kingdomId, String lordName, String fallbackCastleId) {
        if (lordName == null || lordName.isBlank()) {
            return fallbackCastleId == null || fallbackCastleId.isBlank() ? 0 : 1;
        }
        int count = (int)strategicCastles.castlesForKingdom(kingdomId).stream().filter(castle -> castle.ownerPlayerUuid() == null).filter(castle -> !castle.playerClaim()).filter(castle -> lordName.equals(castle.lordName())).count();
        return Math.max(count, fallbackCastleId == null || fallbackCastleId.isBlank() ? 0 : 1);
    }

    private static StrategicCastleSnapshot resolveCurrentLordOriginCastle(class_3218 world, String kingdomId, String lordName, String fallbackCastleId) {
        if (world == null) {
            return null;
        }
        return GotCastles.resolveCurrentLordOriginCastle(GotCastles.snapshotStrategicCastles(world), world, kingdomId, lordName, fallbackCastleId);
    }

    private static StrategicCastleSnapshot resolveCurrentLordOriginCastle(StrategicCastleSnapshotSet strategicCastles, class_3218 world, String kingdomId, String lordName, String fallbackCastleId) {
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        List<StrategicCastleSnapshot> ownedCastles = strategicCastles.castlesForKingdom(canonicalKingdomId).stream().filter(castle -> castle.ownerPlayerUuid() == null).filter(castle -> !castle.playerClaim()).filter(castle -> lordName != null && lordName.equals(castle.lordName())).sorted(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.tier())).thenComparing(StrategicCastleSnapshot::castleId)).toList();
        if (!ownedCastles.isEmpty()) {
            long routeDay = GotCastles.currentLordRouteDay(world);
            return ownedCastles.get(Math.floorMod(routeDay, ownedCastles.size()));
        }
        return strategicCastles.resolve(fallbackCastleId);
    }

    private static List<CastleInstance> ownedCastlesForHouse(class_3218 world, String kingdomId, String houseId) {
        if (world == null) {
            return List.of();
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        if (canonicalKingdomId.isBlank() || resolvedHouseId.isBlank()) {
            return List.of();
        }
        return GotCastles.getWorldState(world).getAll().stream().filter(castle -> castle.getOwnerPlayerUuid() == null).filter(castle -> !"player_claim".equals(castle.getDefinitionId())).filter(castle -> canonicalKingdomId.equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId()))).filter(castle -> resolvedHouseId.equals(GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), canonicalKingdomId, ""))).collect(Collectors.toCollection(ArrayList::new));
    }

    private static List<StrategicCastleSnapshot> ownedStrategicCastlesForHouse(class_3218 world, String kingdomId, String houseId) {
        if (world == null) {
            return List.of();
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        if (canonicalKingdomId.isBlank() || resolvedHouseId.isBlank()) {
            return List.of();
        }
        return GotCastles.getStrategicCastlesForKingdom(world, canonicalKingdomId).stream().filter(castle -> castle.ownerPlayerUuid() == null).filter(castle -> !castle.playerClaim()).filter(castle -> canonicalKingdomId.equals(GotCastles.canonicalKingdomId(castle.ownerKingdomId()))).filter(castle -> resolvedHouseId.equals(GotCastles.resolveOwnedHouseId(castle.ownerHouseId(), canonicalKingdomId, ""))).collect(Collectors.toCollection(ArrayList::new));
    }

    private static List<StrategicCastleSnapshot> ownedStrategicCastlesForHouse(StrategicCastleSnapshotSet strategicCastles, String kingdomId, String houseId) {
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        if (canonicalKingdomId.isBlank() || resolvedHouseId.isBlank()) {
            return new ArrayList<StrategicCastleSnapshot>();
        }
        return strategicCastles.castlesForKingdom(canonicalKingdomId).stream().filter(castle -> castle.ownerPlayerUuid() == null).filter(castle -> !castle.playerClaim()).filter(castle -> canonicalKingdomId.equals(GotCastles.canonicalKingdomId(castle.ownerKingdomId()))).filter(castle -> resolvedHouseId.equals(GotCastles.resolveOwnedHouseId(castle.ownerHouseId(), canonicalKingdomId, ""))).collect(Collectors.toCollection(ArrayList::new));
    }

    @Nullable
    private static KingdomLeaderWorldState.KingdomLeaderProfile resolveOrCreateKingdomLeader(class_3218 world, String kingdomId) {
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        if (world == null || canonicalKingdomId.isBlank()) {
            return null;
        }
        KingdomLeaderWorldState leaderState = KingdomLeaderWorldState.get(world);
        KingdomLeaderWorldState.KingdomLeaderProfile existing = leaderState.getLeader(canonicalKingdomId);
        if (existing != null && GotCastles.isValidKingdomLeader(world, existing)) {
            return existing;
        }
        KingdomLeaderWorldState.KingdomLeaderProfile resolved = GotCastles.chooseKingdomLeader(world, canonicalKingdomId);
        if (resolved != null) {
            leaderState.putLeader(resolved);
        }
        return resolved;
    }

    private static boolean isValidKingdomLeader(class_3218 world, KingdomLeaderWorldState.KingdomLeaderProfile leader) {
        if (world == null || leader == null || leader.kingdomId().isBlank() || leader.houseId().isBlank() || leader.lordName().isBlank()) {
            return false;
        }
        if (leader.lordId().startsWith("player:")) {
            String playerUuidString = leader.lordId().substring("player:".length());
            UUID playerUuid = GotCastles.parseUuid(playerUuidString);
            if (playerUuid == null) {
                return false;
            }
            CastleInstance castle = GotCastles.getWorldState(world).get(leader.castleId());
            return castle != null && playerUuid.equals(castle.getOwnerPlayerUuid()) && GotCastles.canonicalKingdomId(leader.kingdomId()).equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId())) && GotCastles.resolveOwnedHouseId(leader.houseId(), leader.kingdomId(), "").equals(GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), castle.getOwnerKingdomId(), ""));
        }
        return GotCastles.ensureHouseLordProfiles(world, leader.kingdomId(), leader.houseId()).stream().anyMatch(lord -> leader.lordName().equals(lord.displayName()) && leader.lordId().equals(lord.id()));
    }

    @Nullable
    private static KingdomLeaderWorldState.KingdomLeaderProfile chooseKingdomLeader(class_3218 world, String kingdomId) {
        List<StrategicCastleSnapshot> bestCastles;
        if (world == null || kingdomId == null || kingdomId.isBlank()) {
            return null;
        }
        KingdomLeaderWorldState.KingdomLeaderProfile playerLeader = GotCastles.resolvePlayerKingdomLeader(world, kingdomId);
        if (playerLeader != null) {
            return playerLeader;
        }
        List<StrategicCastleSnapshot> castles = GotCastles.getStrategicCastlesForKingdom(world, kingdomId).stream().filter(castle -> castle.ownerPlayerUuid() == null).filter(castle -> !castle.playerClaim()).sorted(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.tier())).thenComparing(StrategicCastleSnapshot::castleId)).toList();
        if (castles.isEmpty()) {
            return null;
        }
        int bestRank = GotCastles.houseSeatRank(castles.getFirst().tier());
        int chosenIndex = (bestCastles = castles.stream().filter(castle -> GotCastles.houseSeatRank(castle.tier()) == bestRank).toList()).size() <= 1 ? 0 : Math.floorMod((int)(world.method_8412() ^ (long)kingdomId.hashCode()), bestCastles.size());
        StrategicCastleSnapshot chosenCastle = bestCastles.get(chosenIndex);
        HouseLordWorldState.HouseLordProfile chosenLord = GotCastles.chooseLeaderForCastle(world, chosenCastle);
        if (chosenLord == null) {
            return null;
        }
        return new KingdomLeaderWorldState.KingdomLeaderProfile(kingdomId, chosenLord.houseId(), chosenLord.id(), chosenLord.displayName(), chosenCastle.castleId());
    }

    @Nullable
    private static KingdomLeaderWorldState.KingdomLeaderProfile resolvePlayerKingdomLeader(class_3218 world, String kingdomId) {
        if (world == null || kingdomId == null || kingdomId.isBlank()) {
            return null;
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        boolean dynamicKingdom = GotKingdoms.isDynamicKingdom(class_2960.method_12829((String)canonicalKingdomId));
        return GotCastles.getWorldState(world).getAll().stream().filter(castle -> castle.getOwnerPlayerUuid() != null).filter(castle -> dynamicKingdom || !"player_claim".equals(castle.getDefinitionId())).filter(castle -> canonicalKingdomId.equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId()))).min(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.getTier())).thenComparing(CastleInstance::getId)).map(castle -> new KingdomLeaderWorldState.KingdomLeaderProfile(canonicalKingdomId, GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), canonicalKingdomId, ""), "player:" + String.valueOf(castle.getOwnerPlayerUuid()), castle.getLordName(), castle.getId())).orElse(null);
    }

    @Nullable
    private static HouseLordWorldState.HouseLordProfile chooseLeaderForCastle(class_3218 world, StrategicCastleSnapshot castle) {
        String houseId;
        if (world == null || castle == null) {
            return null;
        }
        String kingdomId = GotCastles.canonicalKingdomId(castle.ownerKingdomId());
        List<HouseLordWorldState.HouseLordProfile> candidates = GotCastles.ensureHouseLordProfiles(world, kingdomId, houseId = GotCastles.resolveOwnedHouseId(castle.ownerHouseId(), kingdomId, "")).stream().filter(lord -> castle.castleId().equals(GotCastles.primarySpawnCastleIdForLord(world, kingdomId, houseId, lord.displayName(), castle.castleId()))).sorted(Comparator.comparing(lord -> !lord.leader()).thenComparingInt(HouseLordWorldState.HouseLordProfile::index).thenComparing(HouseLordWorldState.HouseLordProfile::displayName)).toList();
        if (!candidates.isEmpty()) {
            return candidates.getFirst();
        }
        List<HouseLordWorldState.HouseLordProfile> all = GotCastles.ensureHouseLordProfiles(world, kingdomId, houseId).stream().sorted(Comparator.comparing(lord -> !lord.leader()).thenComparingInt(HouseLordWorldState.HouseLordProfile::index).thenComparing(HouseLordWorldState.HouseLordProfile::displayName)).toList();
        return all.isEmpty() ? null : all.getFirst();
    }

    private static List<HouseLordWorldState.HouseLordProfile> assignHouseLeader(class_3218 world, String kingdomId, String houseId, List<HouseLordWorldState.HouseLordProfile> lords) {
        return GotCastles.assignHouseLeader(world, kingdomId, houseId, lords, null);
    }

    private static List<HouseLordWorldState.HouseLordProfile> assignHouseLeader(class_3218 world, String kingdomId, String houseId, List<HouseLordWorldState.HouseLordProfile> lords, @Nullable StrategicCastleSnapshotSet strategicCastles) {
        String seatCastleId;
        if (world == null || lords == null || lords.isEmpty()) {
            return lords == null ? List.of() : List.copyOf(lords);
        }
        CastleInstance playerSeatCastle = GotCastles.resolvePlayerHouseSeatCastle(world, kingdomId, houseId);
        if (playerSeatCastle != null && playerSeatCastle.getOwnerPlayerUuid() != null) {
            ArrayList<HouseLordWorldState.HouseLordProfile> cleared = new ArrayList<HouseLordWorldState.HouseLordProfile>(lords.size());
            for (HouseLordWorldState.HouseLordProfile lord2 : lords) {
                if (!lord2.leader()) {
                    cleared.add(lord2);
                    continue;
                }
                cleared.add(new HouseLordWorldState.HouseLordProfile(lord2.id(), lord2.uuid(), lord2.kingdomId(), lord2.houseId(), lord2.displayName(), false, lord2.index(), lord2.attributes(), lord2.spouseId(), lord2.spouseUuid(), lord2.spouseDisplayName(), lord2.spouseAttributes(), lord2.spouseAppearance()));
            }
            return List.copyOf(cleared);
        }
        List<StrategicCastleSnapshot> houseCastles = strategicCastles == null ? GotCastles.ownedStrategicCastlesForHouse(world, kingdomId, houseId) : GotCastles.ownedStrategicCastlesForHouse(strategicCastles, kingdomId, houseId);
        houseCastles.sort(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.tier())).thenComparing(StrategicCastleSnapshot::castleId));
        String string = seatCastleId = houseCastles.isEmpty() ? "" : houseCastles.getFirst().castleId();
        if (seatCastleId.isBlank()) {
            return List.copyOf(lords);
        }
        HouseLordWorldState.HouseLordProfile leader = lords.stream().filter(lord -> seatCastleId.equals(GotCastles.primarySpawnCastleIdForLord(world, lord.displayName(), seatCastleId, houseCastles))).sorted(Comparator.comparingInt(HouseLordWorldState.HouseLordProfile::index).thenComparing(HouseLordWorldState.HouseLordProfile::displayName)).findFirst().orElseGet(() -> lords.stream().sorted(Comparator.comparingInt(HouseLordWorldState.HouseLordProfile::index).thenComparing(HouseLordWorldState.HouseLordProfile::displayName)).findFirst().orElse(null));
        if (leader == null) {
            return List.copyOf(lords);
        }
        ArrayList<HouseLordWorldState.HouseLordProfile> assigned = new ArrayList<HouseLordWorldState.HouseLordProfile>(lords.size());
        for (HouseLordWorldState.HouseLordProfile lord3 : lords) {
            boolean isLeader = leader.id().equals(lord3.id());
            if (lord3.leader() == isLeader) {
                assigned.add(lord3);
                continue;
            }
            assigned.add(new HouseLordWorldState.HouseLordProfile(lord3.id(), lord3.uuid(), lord3.kingdomId(), lord3.houseId(), lord3.displayName(), isLeader, lord3.index(), lord3.attributes(), lord3.spouseId(), lord3.spouseUuid(), lord3.spouseDisplayName(), lord3.spouseAttributes(), lord3.spouseAppearance()));
        }
        return List.copyOf(assigned);
    }

    @Nullable
    private static CastleInstance resolvePlayerHouseSeatCastle(class_3218 world, String kingdomId, String houseId) {
        if (world == null) {
            return null;
        }
        String seatCastleId = GotCastles.houseSeatCastleIdIncludingPlayer(world, kingdomId, houseId);
        if (seatCastleId.isBlank()) {
            return null;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(seatCastleId);
        return castle != null && castle.getOwnerPlayerUuid() != null ? castle : null;
    }

    public static boolean hasPlayerKingdomLeader(class_3218 world, String kingdomId, String houseId) {
        if (world == null) {
            return false;
        }
        KingdomLeaderWorldState.KingdomLeaderProfile leader = GotCastles.resolveOrCreateKingdomLeader(world, kingdomId);
        if (leader == null || !leader.lordId().startsWith("player:")) {
            return false;
        }
        return GotCastles.canonicalKingdomId(kingdomId).equals(GotCastles.canonicalKingdomId(leader.kingdomId())) && GotCastles.resolveOwnedHouseId(houseId, GotCastles.canonicalKingdomId(kingdomId), "").equals(GotCastles.resolveOwnedHouseId(leader.houseId(), GotCastles.canonicalKingdomId(leader.kingdomId()), ""));
    }

    public static boolean hasPlayerKingdomLeader(class_3218 world, String kingdomId) {
        if (world == null) {
            return false;
        }
        KingdomLeaderWorldState.KingdomLeaderProfile leader = GotCastles.resolveOrCreateKingdomLeader(world, kingdomId);
        return leader != null && !leader.kingdomId().isBlank() && leader.lordId().startsWith("player:") && GotCastles.canonicalKingdomId(kingdomId).equals(GotCastles.canonicalKingdomId(leader.kingdomId()));
    }

    public static String playerRuledKingdomId(class_3218 world, class_3222 player) {
        if (world == null || player == null) {
            return "";
        }
        String playerKingdomId = GotCastles.canonicalKingdomId(((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedKingdomId());
        if (playerKingdomId.isBlank()) {
            return "";
        }
        KingdomLeaderWorldState.KingdomLeaderProfile leader = GotCastles.resolveOrCreateKingdomLeader(world, playerKingdomId);
        return leader != null && ("player:" + String.valueOf(player.method_5667())).equals(leader.lordId()) ? playerKingdomId : "";
    }

    public static void cancelKingdomAiWarPartiesBetween(class_3218 world, String firstKingdomId, String secondKingdomId) {
        if (world == null) {
            return;
        }
        String first = GotCastles.canonicalKingdomId(firstKingdomId);
        String second = GotCastles.canonicalKingdomId(secondKingdomId);
        if (first.isBlank() || second.isBlank()) {
            return;
        }
        ArrayList<String> targetCastleIds = new ArrayList<String>();
        for (KingdomAiInvasionWorldState.KingdomAiInvasion invasion : KingdomAiInvasionWorldState.get(world).getAll()) {
            String attacker = GotCastles.canonicalKingdomId(invasion.attackerKingdomId());
            String defender = GotCastles.canonicalKingdomId(invasion.defenderKingdomId());
            if ((!first.equals(attacker) || !second.equals(defender)) && (!first.equals(defender) || !second.equals(attacker))) continue;
            targetCastleIds.add(invasion.targetCastleId());
        }
        for (String targetCastleId : targetCastleIds) {
            GotCastles.endKingdomAiInvasion(world, targetCastleId, false);
        }
        if (!targetCastleIds.isEmpty()) {
            GotCastles.syncCastleMapToPlayers(world);
            GotCastles.syncStateToPlayers(world);
        }
    }

    public static String resolveHouseLordLocationName(class_3218 world, String kingdomId, String houseId, String lordName) {
        if (world == null || lordName == null || lordName.isBlank()) {
            return "";
        }
        String castleId = GotCastles.primarySpawnCastleIdForLord(world, kingdomId, houseId, lordName, GotCastles.houseSeatCastleId(world, kingdomId, houseId));
        return GotCastles.resolveCastleLocationName(world, castleId);
    }

    public static String resolvePlayerOwnedHouseLordRouteCastleId(class_3218 world, UUID ownerPlayerUuid, String kingdomId, String houseId, String lordName, String fallbackCastleId) {
        if (world == null || ownerPlayerUuid == null) {
            return fallbackCastleId == null ? "" : fallbackCastleId;
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        if (canonicalKingdomId.isBlank() || resolvedHouseId.isBlank()) {
            return fallbackCastleId == null ? "" : fallbackCastleId;
        }
        List<StrategicCastleSnapshot> allHouseCastles = GotCastles.getStrategicCastlesForKingdom(world, canonicalKingdomId).stream().filter(castle -> resolvedHouseId.equals(GotCastles.resolveOwnedHouseId(castle.ownerHouseId(), canonicalKingdomId, ""))).filter(castle -> ownerPlayerUuid.equals(castle.ownerPlayerUuid()) || castle.ownerPlayerUuid() == null).sorted(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.tier())).thenComparing(StrategicCastleSnapshot::castleId)).toList();
        if (allHouseCastles.isEmpty()) {
            return fallbackCastleId == null ? "" : fallbackCastleId;
        }
        long routeDay = GotCastles.currentLordRouteDay(world);
        int index = GotCastles.deterministicLordRouteIndex(lordName == null ? resolvedHouseId : lordName, routeDay, allHouseCastles.size());
        return allHouseCastles.get(index).castleId();
    }

    public static String resolvePlayerOwnedDynastyRouteCastleId(class_3218 world, UUID ownerPlayerUuid, String kingdomId, String houseId, String lordName, String fallbackCastleId) {
        List<StrategicCastleSnapshot> kingdomCastles = world == null ? List.of() : GotCastles.getStrategicCastlesForKingdom(world, kingdomId);
        return GotCastles.resolvePlayerOwnedDynastyRouteCastleId(world, ownerPlayerUuid, kingdomId, houseId, lordName, fallbackCastleId, kingdomCastles);
    }

    private static String resolvePlayerOwnedDynastyRouteCastleId(class_3218 world, UUID ownerPlayerUuid, String kingdomId, String houseId, String lordName, String fallbackCastleId, List<StrategicCastleSnapshot> kingdomCastles) {
        if (world == null || ownerPlayerUuid == null || lordName == null || lordName.isBlank()) {
            return fallbackCastleId == null ? "" : fallbackCastleId;
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        if (canonicalKingdomId.isBlank() || resolvedHouseId.isBlank()) {
            return fallbackCastleId == null ? "" : fallbackCastleId;
        }
        List<StrategicCastleSnapshot> dynastyCastles = kingdomCastles.stream().filter(castle -> ownerPlayerUuid.equals(castle.ownerPlayerUuid())).filter(castle -> resolvedHouseId.equals(GotCastles.resolveOwnedHouseId(castle.ownerHouseId(), canonicalKingdomId, ""))).filter(castle -> lordName.equals(castle.lordName())).sorted(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.tier())).thenComparing(StrategicCastleSnapshot::castleId)).toList();
        if (dynastyCastles.isEmpty()) {
            return fallbackCastleId == null ? "" : fallbackCastleId;
        }
        long routeDay = GotCastles.currentLordRouteDay(world);
        int index = GotCastles.deterministicLordRouteIndex(lordName, routeDay, dynastyCastles.size());
        return dynastyCastles.get(index).castleId();
    }

    public static String resolveCastleLocationName(class_3218 world, String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return "";
        }
        StrategicCastleSnapshot snapshot = GotCastles.resolveStrategicCastle(world, castleId);
        if (snapshot != null && !snapshot.displayName().isBlank()) {
            return snapshot.displayName();
        }
        VillageInstance village = VillageWorldState.get(world).get(castleId);
        if (village != null && !village.getName().isBlank()) {
            return village.getName();
        }
        return castleId;
    }

    private static int houseSeatRank(CastleTier tier) {
        return switch (tier == null ? CastleTier.SETTLEMENT : tier) {
            default -> throw new MatchException(null, null);
            case CastleTier.CAPITAL -> 0;
            case CastleTier.LORDSHIP -> 1;
            case CastleTier.SETTLEMENT -> 2;
        };
    }

    private static List<CastleNpcProfile> requiredCastleNpcProfiles(class_3218 world, CastleInstance castle) {
        List<CastleNpcProfile> existingResidentProfiles;
        ArrayList<CastleNpcProfile> profiles = new ArrayList<CastleNpcProfile>();
        if (castle.getOwnerPlayerUuid() == null) {
            List<HouseLordWorldState.HouseLordProfile> houseLords = GotCastles.houseLordProfilesForCastle(world, castle);
            if (houseLords.isEmpty()) {
                profiles.add(GotCastles.createCastleNpcProfile(castle, KingdomNpcRole.LORD, 0, 99));
            } else {
                HouseLineageService.ensureLineage(world, castle.getOwnerKingdomId(), castle.getOwnerHouseId(), houseLords);
                for (HouseLordWorldState.HouseLordProfile lord : houseLords) {
                    NpcAppearanceProfile lordAppearance = HouseLineageService.lordAppearance(world, castle.getOwnerKingdomId(), castle.getOwnerHouseId(), lord, houseLords);
                    profiles.add(new CastleNpcProfile(lord.id(), lord.uuid(), KingdomNpcRole.LORD.name(), 99 + lord.index(), lord.displayName(), lord.attributes(), lordAppearance, false, lord.spouseId()));
                    if (!lord.hasSpouse()) continue;
                    profiles.add(new CastleNpcProfile(lord.spouseId(), lord.spouseUuid(), KingdomNpcRole.CIVILIAN.name(), 149 + lord.index(), lord.spouseDisplayName(), lord.spouseAttributes(), lord.spouseAppearance(), true, lord.id()));
                }
            }
        } else {
            String kingdomId = GotCastles.canonicalKingdomId(castle.getOwnerKingdomId());
            String houseId = GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), kingdomId, "");
            for (DynastyNpcWorldState.FamilyMemberProfile dynasty : GotDynastyNpcs.adultMembersForHouse(world, kingdomId, houseId)) {
                if (!castle.getId().equals(dynasty.castleId())) continue;
                profiles.add(new CastleNpcProfile(dynasty.id(), dynasty.entityUuid(), KingdomNpcRole.LORD.name(), 99, dynasty.displayName(), dynasty.attributes(), NpcAppearanceProfile.EMPTY, false, ""));
            }
        }
        if ((existingResidentProfiles = castle.getCastleNpcProfiles().stream().filter(profile -> {
            KingdomNpcRole role = profile.role();
            if (role == KingdomNpcRole.LORD || role.isGuardDuty()) {
                return false;
            }
            return role != KingdomNpcRole.CIVILIAN || !profile.nobleSpouse();
        }).toList()).isEmpty()) {
            profiles.addAll(GotCastles.createFallbackCastleResidentProfiles(castle));
        } else {
            profiles.addAll(existingResidentProfiles);
        }
        for (int index = 0; index < GotCastles.guardCountFor(castle.getTier()); ++index) {
            profiles.add(GotCastles.createCastleNpcProfile(castle, KingdomNpcRole.GUARD, index, 700 + index));
        }
        List<class_2338> gatePositions = castle.getWallGateClaims().stream().map(WallGateClaim::pointOfInterest).sorted(Comparator.comparingLong(class_2338::method_10063)).toList();
        profiles.addAll(GotCastles.createPatrolGuardProfiles(world, castle, gatePositions));
        profiles.addAll(GotCastles.createGateWatcherProfiles(world, castle, gatePositions));
        return profiles;
    }

    private static List<CastleNpcProfile> createPatrolGuardProfiles(class_3218 world, CastleInstance castle, List<class_2338> gatePositions) {
        ArrayList<CastleNpcProfile> profiles = new ArrayList<CastleNpcProfile>();
        List<class_2338> anchors = GotCastles.resolveCastleRoadPatrolAnchors(world, castle, gatePositions);
        for (int index = 0; index < 8; ++index) {
            class_2338 pointOfInterest = anchors.get(index % anchors.size());
            class_2338 spawnPos = GotCastles.findNearestNpcStandPos(world, pointOfInterest, 6, 14, index);
            profiles.add(GotCastles.createCastleNpcProfile(castle, KingdomNpcRole.PATROL, index, 900 + index, pointOfInterest, spawnPos, Math.max(32, Math.min(96, castle.getAreaRadiusBlocks() / 2))));
        }
        return profiles;
    }

    private static List<CastleNpcProfile> createGateWatcherProfiles(class_3218 world, CastleInstance castle, List<class_2338> gatePositions) {
        if (gatePositions.isEmpty()) {
            return castle.getCastleNpcProfiles().stream().filter(profile -> profile.role() == KingdomNpcRole.GATE_WATCHER).toList();
        }
        ArrayList<CastleNpcProfile> profiles = new ArrayList<CastleNpcProfile>();
        for (int gateIndex = 0; gateIndex < gatePositions.size(); ++gateIndex) {
            class_2338 gatePos = gatePositions.get(gateIndex);
            class_2338 pointOfInterest = GotCastles.surfaceAt(world, gatePos.method_10263(), gatePos.method_10260(), gatePos.method_10264());
            for (int watcherIndex = 0; watcherIndex < 2; ++watcherIndex) {
                int profileIndex = gateIndex * 2 + watcherIndex;
                class_2338 spawnPos = GotCastles.findNearestNpcStandPos(world, pointOfInterest, 2 + watcherIndex * 2, 6, profileIndex);
                profiles.add(GotCastles.createCastleNpcProfile(castle, KingdomNpcRole.GATE_WATCHER, profileIndex, 1000 + profileIndex, pointOfInterest, spawnPos, 6));
            }
        }
        Set generatedIds = profiles.stream().map(CastleNpcProfile::id).collect(Collectors.toCollection(LinkedHashSet::new));
        for (CastleNpcProfile existing : castle.getCastleNpcProfiles()) {
            if (existing.role() != KingdomNpcRole.GATE_WATCHER || !generatedIds.add(existing.id())) continue;
            profiles.add(existing);
        }
        return profiles;
    }

    private static List<class_2338> resolveCastleRoadPatrolAnchors(class_3218 world, CastleInstance castle, List<class_2338> gates) {
        class_2338 banner = castle.getBannerPos();
        int radius = Math.max(32, Math.min(96, castle.getAreaRadiusBlocks()));
        ArrayList<class_2338> anchors = new ArrayList<class_2338>();
        if (gates.size() >= 2) {
            double xAverage = gates.stream().mapToInt(class_2382::method_10263).average().orElse(banner.method_10263());
            double zAverage = gates.stream().mapToInt(class_2382::method_10260).average().orElse(banner.method_10260());
            int xSpread = gates.stream().mapToInt(pos -> Math.abs(pos.method_10263() - (int)Math.round(xAverage))).max().orElse(0);
            int zSpread = gates.stream().mapToInt(pos -> Math.abs(pos.method_10260() - (int)Math.round(zAverage))).max().orElse(0);
            if (zSpread >= xSpread) {
                int roadX = (int)Math.round(xAverage);
                int minZ = gates.stream().mapToInt(class_2382::method_10260).min().orElse(banner.method_10260() - radius);
                int maxZ = gates.stream().mapToInt(class_2382::method_10260).max().orElse(banner.method_10260() + radius);
                GotCastles.addRoadAnchors(world, anchors, roadX, minZ, roadX, maxZ, 6);
                GotCastles.addRoadAnchors(world, anchors, Math.min(roadX, banner.method_10263()), banner.method_10260(), Math.max(roadX, banner.method_10263()), banner.method_10260(), 3);
            } else {
                int roadZ = (int)Math.round(zAverage);
                int minX = gates.stream().mapToInt(class_2382::method_10263).min().orElse(banner.method_10263() - radius);
                int maxX = gates.stream().mapToInt(class_2382::method_10263).max().orElse(banner.method_10263() + radius);
                GotCastles.addRoadAnchors(world, anchors, minX, roadZ, maxX, roadZ, 6);
                GotCastles.addRoadAnchors(world, anchors, banner.method_10263(), Math.min(roadZ, banner.method_10260()), banner.method_10263(), Math.max(roadZ, banner.method_10260()), 3);
            }
        }
        if (anchors.isEmpty()) {
            GotCastles.addRoadAnchors(world, anchors, banner.method_10263(), banner.method_10260() - radius, banner.method_10263(), banner.method_10260() + radius, 4);
            GotCastles.addRoadAnchors(world, anchors, banner.method_10263() - radius, banner.method_10260(), banner.method_10263() + radius, banner.method_10260(), 4);
        }
        while (anchors.size() < 8) {
            int index = anchors.size();
            int x = banner.method_10263() + ((index & 1) == 0 ? 1 : -1) * (12 + index * 5);
            int z = banner.method_10260() + ((index & 2) == 0 ? 1 : -1) * (12 + index * 3);
            anchors.add(GotCastles.surfaceAt(world, x, z, banner.method_10264()));
        }
        return List.copyOf(anchors);
    }

    private static void addRoadAnchors(class_3218 world, List<class_2338> anchors, int startX, int startZ, int endX, int endZ, int count) {
        for (int index = 0; index < count; ++index) {
            float t = count <= 1 ? 0.5f : ((float)index + 0.5f) / (float)count;
            int x = Math.round(class_3532.method_48781((float)t, (int)startX, (int)endX));
            int z = Math.round(class_3532.method_48781((float)t, (int)startZ, (int)endZ));
            anchors.add(GotCastles.surfaceAt(world, x, z, 0));
        }
    }

    private static class_2338 surfaceAt(class_3218 world, int x, int z, int fallbackY) {
        int y = world.method_8624(class_2902.class_2903.field_13203, x, z);
        if (y <= world.method_31607()) {
            y = fallbackY;
        }
        return new class_2338(x, y, z);
    }

    private static class_2338 findNearestNpcStandPos(class_3218 world, class_2338 origin, int minRadius, int maxRadius, int seedOffset) {
        int attempts = Math.max(12, (maxRadius - minRadius + 1) * 6);
        for (int attempt = 0; attempt < attempts; ++attempt) {
            int z;
            int angleIndex = seedOffset * 7 + attempt * 5;
            double angle = (double)(angleIndex % 32) * 0.19634954084936207;
            int radius = minRadius + Math.floorMod(seedOffset + attempt, Math.max(1, maxRadius - minRadius + 1));
            int x = origin.method_10263() + class_3532.method_15357((double)(Math.cos(angle) * (double)radius));
            class_2338 candidate = GotCastles.surfaceAt(world, x, z = origin.method_10260() + class_3532.method_15357((double)(Math.sin(angle) * (double)radius)), origin.method_10264());
            if (!GotCastles.isValidNpcStandPos(world, candidate)) continue;
            return candidate;
        }
        return GotCastles.isValidNpcStandPos(world, origin) ? origin : GotCastles.findNpcRespawnPos(world, origin, seedOffset);
    }

    private static List<CastleNpcProfile> preservedCastleResidentProfiles(CastleInstance castle) {
        if (castle == null) {
            return List.of();
        }
        return castle.getCastleNpcProfiles().stream().filter(profile -> {
            KingdomNpcRole role = profile.role();
            return role != KingdomNpcRole.LORD && !role.isGuardDuty();
        }).toList();
    }

    private static List<CastleNpcProfile> createFallbackCastleResidentProfiles(CastleInstance castle) {
        int index;
        ArrayList<CastleNpcProfile> profiles = new ArrayList<CastleNpcProfile>();
        CastleCityTierPlan tierPlan = CastleCityTierRegistry.forTier(castle.getTier());
        int civilianIndex = 0;
        for (index = 0; index < tierPlan.blacksmithCount(); ++index) {
            profiles.add(GotCastles.createCastleNpcProfile(castle, KingdomNpcRole.BLACKSMITH, index, 199 + index));
        }
        for (index = 0; index < tierPlan.arenaCount(); ++index) {
            profiles.add(GotCastles.createCastleNpcProfile(castle, KingdomNpcRole.ARENA_MASTER, index, 227 + index));
        }
        for (index = 0; index < tierPlan.barnCount(); ++index) {
            profiles.add(GotCastles.createCastleNpcProfile(castle, KingdomNpcRole.CART_RIDER, index, 271 + index));
        }
        if (castle.hasSubtype(CastleSubtype.HARBOUR)) {
            profiles.add(GotCastles.createCastleNpcProfile(castle, KingdomNpcRole.FISHERMAN, 0, 331));
        }
        for (index = 0; index < tierPlan.houseCount() + tierPlan.marketCount() + tierPlan.tavernCount(); ++index) {
            profiles.add(GotCastles.createCastleNpcProfile(castle, KingdomNpcRole.CIVILIAN, civilianIndex, 500 + civilianIndex));
            ++civilianIndex;
        }
        return List.copyOf(profiles);
    }

    private static CastleNpcProfile createCastleNpcProfile(CastleInstance castle, KingdomNpcRole role, int slotIndex, int seedOffset) {
        return GotCastles.createCastleNpcProfile(castle, role, slotIndex, seedOffset, null, null, 0);
    }

    private static CastleNpcProfile createCastleNpcProfile(CastleInstance castle, KingdomNpcRole role, int slotIndex, int seedOffset, @Nullable class_2338 pointOfInterest, @Nullable class_2338 preferredSpawnPos, int pointOfInterestRadius) {
        String id = castle.getId() + ":npc:" + role.name().toLowerCase(Locale.ROOT) + ":" + slotIndex;
        UUID uuid = UUID.nameUUIDFromBytes((id + ":" + castle.getDefinitionId()).getBytes(StandardCharsets.UTF_8));
        CharacterSelectionOption option = GotCastles.characterOptionForCastle(castle);
        return new CastleNpcProfile(id, uuid, role.name(), seedOffset, "", GotCastles.generateNpcAttributesForRole(option, castle.getId() + "|" + id, role), NpcAppearanceProfile.generate(GotCastles.resolveCastleKingdomId(castle), castle.getId() + "|" + id + "|appearance"), false, "", pointOfInterest, preferredSpawnPos, pointOfInterestRadius);
    }

    private static void seedCastleStructureResidentProfiles(CastleInstance castle, List<PlacedCastleStructure> placedStructures) {
        if (castle == null || placedStructures == null || placedStructures.isEmpty()) {
            return;
        }
        int civilianIndex = 0;
        int blacksmithIndex = 0;
        int arenaMasterIndex = 0;
        int cartRiderIndex = 0;
        int fishermanIndex = 0;
        int cityStructureIndex = 0;
        block7: for (PlacedCastleStructure placedStructure : placedStructures) {
            int seedOffset;
            int slotIndex;
            KingdomNpcRole role = placedStructure.plan().residentRole();
            switch (role) {
                case BLACKSMITH: {
                    slotIndex = blacksmithIndex++;
                    seedOffset = 199 + slotIndex;
                    break;
                }
                case ARENA_MASTER: {
                    slotIndex = arenaMasterIndex++;
                    seedOffset = 227 + slotIndex;
                    break;
                }
                case CART_RIDER: {
                    slotIndex = cartRiderIndex++;
                    seedOffset = 271 + slotIndex;
                    break;
                }
                case FISHERMAN: {
                    slotIndex = fishermanIndex++;
                    seedOffset = 331 + slotIndex;
                    break;
                }
                case CIVILIAN: {
                    slotIndex = civilianIndex++;
                    seedOffset = 500 + slotIndex;
                    break;
                }
                default: {
                    continue block7;
                }
            }
            CastleNpcProfile profile = GotCastles.createCastleNpcProfile(castle, role, slotIndex, seedOffset, placedStructure.placement().pointOfInterest(), placedStructure.placement().residentSpawnPos(), placedStructure.plan().pointOfInterestRadius());
            castle.addCastleNpcProfile(profile);
            if (GotCastles.isOutskirtsRole(role)) continue;
            castle.addCityStructureClaim(new CityStructureClaim(castle.getId() + ":structure:" + cityStructureIndex++, placedStructure.plan().structurePath(), placedStructure.placement().footprint(), placedStructure.placement().origin(), placedStructure.placement().size(), profile.id()));
        }
    }

    private static PendingCastleGeneration.PlacedStructureState toPendingPlacedStructureState(PlacedCastleStructure placedStructure) {
        return new PendingCastleGeneration.PlacedStructureState(placedStructure.plan().structurePath(), placedStructure.plan().residentRole().name(), placedStructure.plan().pointOfInterestRadius(), placedStructure.placement().footprint(), placedStructure.placement().origin(), placedStructure.placement().size(), placedStructure.placement().pointOfInterest(), placedStructure.placement().residentSpawnPos());
    }

    private static PendingCastleGeneration.PlacedStructureState toPendingPlacedStructureState(CastleWorldgenStructurePlan structurePlan) {
        CastleStructureResidentPlan residentPlan = structurePlan.residentPlan();
        return new PendingCastleGeneration.PlacedStructureState(structurePlan.structurePath(), residentPlan == null ? KingdomNpcRole.CIVILIAN.name() : residentPlan.residentRole().name(), residentPlan == null ? 0 : residentPlan.pointOfInterestRadius(), structurePlan.footprint(), structurePlan.placementOrigin(), structurePlan.size(), structurePlan.pointOfInterest(), structurePlan.residentSpawnPos());
    }

    private static PendingCastleGeneration.PlannedStructureState toPendingPlannedStructureState(CastleStructureResidentPlan structurePlan) {
        return new PendingCastleGeneration.PlannedStructureState(structurePlan.structurePath(), structurePlan.residentRole().name(), structurePlan.pointOfInterestRadius());
    }

    private static CastleStructureResidentPlan fromPendingPlannedStructureState(PendingCastleGeneration.PlannedStructureState structurePlan) {
        KingdomNpcRole residentRole;
        try {
            residentRole = KingdomNpcRole.valueOf(structurePlan.residentRoleId());
        }
        catch (IllegalArgumentException ignored) {
            residentRole = KingdomNpcRole.CIVILIAN;
        }
        return new CastleStructureResidentPlan(structurePlan.structurePath(), residentRole, structurePlan.pointOfInterestRadius());
    }

    private static List<PlacedCastleStructure> fromPendingPlacedStructureStates(List<PendingCastleGeneration.PlacedStructureState> placedStructures) {
        ArrayList<PlacedCastleStructure> results = new ArrayList<PlacedCastleStructure>(placedStructures.size());
        for (PendingCastleGeneration.PlacedStructureState placedStructure : placedStructures) {
            KingdomNpcRole residentRole;
            try {
                residentRole = KingdomNpcRole.valueOf(placedStructure.residentRoleId());
            }
            catch (IllegalArgumentException ignored) {
                residentRole = KingdomNpcRole.CIVILIAN;
            }
            results.add(new PlacedCastleStructure(new CastleStructureResidentPlan(placedStructure.structurePath(), residentRole, placedStructure.pointOfInterestRadius()), new StructurePlacementResult(placedStructure.footprint(), placedStructure.origin(), placedStructure.size(), placedStructure.pointOfInterest(), placedStructure.residentSpawnPos())));
        }
        return List.copyOf(results);
    }

    private static boolean pendingGenerationHasPlacedRole(PendingCastleGeneration pendingGeneration, KingdomNpcRole role) {
        if (pendingGeneration == null || role == null) {
            return false;
        }
        for (PendingCastleGeneration.PlacedStructureState placedStructure : pendingGeneration.placedStructures()) {
            if (!role.name().equals(placedStructure.residentRoleId())) continue;
            return true;
        }
        return false;
    }

    private static List<CastleStructureResidentPlan> cachedPendingStructurePlans(PendingCastleGeneration pendingGeneration, CastleCityTierPlan tierPlan) {
        if (!pendingGeneration.plannedStructures().isEmpty()) {
            return pendingGeneration.plannedStructures().stream().map(GotCastles::fromPendingPlannedStructureState).toList();
        }
        class_5819 random = class_5819.method_43049((long)((pendingGeneration.bannerPos().method_10063() ^ (long)pendingGeneration.castleId().hashCode()) + (pendingGeneration.dynamic() ? 17L + (long)pendingGeneration.tier().ordinal() * 31L : 0L)));
        ClaimStructureSelection selection = GotCastles.selectClaimStructurePlacements(pendingGeneration.kingdomId(), pendingGeneration.tier(), tierPlan.totalInteriorStructures() + 8, random);
        if (selection == null) {
            return List.of();
        }
        pendingGeneration.setPlannedStructures(selection.orderedPlacements().stream().map(GotCastles::toPendingPlannedStructureState).toList());
        return selection.orderedPlacements();
    }

    private static List<class_1923> cachedPendingDynamicBuildingChunks(class_3218 world, PendingCastleGeneration pendingGeneration, CastleCityTierPlan tierPlan) {
        List<class_1923> cachedChunks;
        class_3341 coreReservedFootprint = GotCastles.dynamicStoneCoreReservedFootprint(world, world.method_14183(), pendingGeneration.castleId(), pendingGeneration.bannerPos(), pendingGeneration.structureOrigin(), pendingGeneration.structureSize());
        if (!pendingGeneration.dynamicBuildingChunks().isEmpty() && (cachedChunks = pendingGeneration.dynamicBuildingChunks().stream().map(chunk -> new class_1923(chunk.x(), chunk.z())).toList()).stream().noneMatch(chunk -> GotCastles.chunkIntersectsBlockBox(chunk, coreReservedFootprint))) {
            return cachedChunks;
        }
        class_5819 random = class_5819.method_43049((long)((pendingGeneration.bannerPos().method_10063() ^ (long)pendingGeneration.castleId().hashCode()) + 17L + (long)pendingGeneration.tier().ordinal() * 31L));
        List<class_1923> buildingChunks = GotCastles.selectDynamicCastleBuildingChunks(pendingGeneration.bannerPos(), tierPlan, pendingGeneration.northSouthGates(), coreReservedFootprint, random, GotCastles.isOriginalNightsWatchCastle(pendingGeneration.kingdomId()) ? 1 : 0);
        pendingGeneration.setDynamicBuildingChunks(buildingChunks.stream().map(chunk -> new PendingCastleGeneration.ChunkPosState(chunk.field_9181, chunk.field_9180)).toList());
        return buildingChunks;
    }

    private static CastleNpcProfile ensureCastleProfileAttributes(CastleNpcProfile profile, CharacterSelectionOption option, String seedKey) {
        if (profile == null || profile.attributes().isAssigned()) {
            return profile;
        }
        return new CastleNpcProfile(profile.id(), profile.uuid(), profile.roleId(), profile.seedOffset(), profile.displayName(), GotCastles.generateNpcAttributesForRole(option, seedKey, profile.role()), profile.appearance(), profile.nobleSpouse(), profile.spouseProfileId(), profile.pointOfInterest(), profile.preferredSpawnPos(), profile.pointOfInterestRadius());
    }

    private static CastleNpcProfile ensureCastleProfileAppearance(CastleNpcProfile profile, class_2960 kingdomId, String seedKey) {
        if (profile == null || profile.appearance().isAssigned()) {
            return profile;
        }
        return new CastleNpcProfile(profile.id(), profile.uuid(), profile.roleId(), profile.seedOffset(), profile.displayName(), profile.attributes(), NpcAppearanceProfile.generate(kingdomId, seedKey + "|appearance"), profile.nobleSpouse(), profile.spouseProfileId(), profile.pointOfInterest(), profile.preferredSpawnPos(), profile.pointOfInterestRadius());
    }

    private static void applyNpcAttributes(AbstractKingdomHumanoidEntity entity, CastleNpcProfile profile) {
        if (entity != null && profile != null) {
            entity.setNpcAttributes(profile.attributes());
        }
    }

    private static void applyNpcAppearance(AbstractKingdomNpcEntity entity, CastleNpcProfile profile) {
        if (entity != null && profile != null) {
            entity.setNpcAppearance(profile.appearance());
            entity.setNobleSpouse(profile.nobleSpouse());
            entity.setSpouseProfileId(profile.spouseProfileId());
            if (entity instanceof AbstractKingdomLordNpcEntity) {
                class_3218 serverWorld;
                HouseLineageWorldState.LordHeritageProfile heritage;
                AbstractKingdomLordNpcEntity lordNpc = (AbstractKingdomLordNpcEntity)entity;
                class_1937 class_19372 = entity.method_37908();
                if (class_19372 instanceof class_3218 && (heritage = HouseLineageService.findLordHeritage(serverWorld = (class_3218)class_19372, profile.id())) != null) {
                    lordNpc.setLordHeritage(heritage.heritage());
                    lordNpc.setRelatedLordId(heritage.relatedLordId());
                }
            }
        }
    }

    private static void applyNpcAttributes(AbstractKingdomHumanoidEntity entity, SettlerProfile profile) {
        if (entity != null && profile != null) {
            entity.setNpcAttributes(profile.attributes());
        }
    }

    private static CharacterSelectionOption characterOptionForCastle(CastleInstance castle) {
        if (castle == null) {
            return null;
        }
        String kingdomId = GotCastles.canonicalKingdomId(castle.getOwnerKingdomId());
        String houseId = GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), kingdomId, "");
        return GotCastles.characterOptionForHouse(kingdomId, houseId);
    }

    private static class_2960 resolveCastleKingdomId(CastleInstance castle) {
        if (castle == null) {
            return null;
        }
        return class_2960.method_12829((String)GotCastles.canonicalKingdomId(castle.getOwnerKingdomId()));
    }

    private static CharacterSelectionOption characterOptionForHouse(String kingdomId, String houseId) {
        CharacterSelectionOption houseOption;
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        CharacterSelectionOption characterSelectionOption = houseOption = resolvedHouseId.isBlank() ? null : GotCharacterOptions.get(resolvedHouseId);
        if (houseOption != null) {
            return houseOption;
        }
        class_2960 parsedKingdomId = class_2960.method_12829((String)canonicalKingdomId);
        return GotCharacterOptions.getByKingdomId(parsedKingdomId);
    }

    private static HouseLordWorldState.HouseLordProfile ensureHouseLordAttributes(HouseLordWorldState.HouseLordProfile lord, CharacterSelectionOption option, @Nullable Kingdom kingdom, String houseSurname, LinkedHashSet<String> usedNames) {
        if (lord == null) {
            return lord;
        }
        NpcAttributeProfile lordAttributes = lord.attributes().isAssigned() ? lord.attributes() : NpcAttributeProfile.generate(option, lord.id(), 20, 30);
        Object spouseId = lord.spouseId();
        UUID spouseUuid = lord.spouseUuid();
        String spouseDisplayName = lord.spouseDisplayName();
        NpcAttributeProfile spouseAttributes = lord.spouseAttributes();
        NpcAppearanceProfile spouseAppearance = lord.spouseAppearance();
        boolean shouldHaveSpouse = GotCastles.shouldGenerateAiLordSpouse(lord);
        if (shouldHaveSpouse) {
            if (((String)spouseId).isBlank()) {
                spouseId = lord.id() + ":spouse";
            }
            if (spouseUuid == null) {
                spouseUuid = UUID.nameUUIDFromBytes(("ai_lord_spouse:" + (String)spouseId).getBytes(StandardCharsets.UTF_8));
            }
            if (spouseDisplayName.isBlank() && kingdom != null) {
                spouseDisplayName = GotCastleOwnerNames.createFullName(kingdom, houseSurname, ((String)spouseId).hashCode());
                if (usedNames != null && !usedNames.isEmpty()) {
                    int attempt = 0;
                    while (usedNames.contains(spouseDisplayName) && attempt++ < 8) {
                        spouseDisplayName = GotCastleOwnerNames.createFullName(kingdom, houseSurname, ((String)spouseId + "|" + attempt).hashCode());
                    }
                }
            }
            if (!spouseDisplayName.isBlank() && usedNames != null) {
                usedNames.add(spouseDisplayName);
            }
            if (!spouseAttributes.isAssigned()) {
                spouseAttributes = NpcAttributeProfile.generate(option, (String)spouseId);
            }
            if (!spouseAppearance.isAssigned()) {
                String lordGender = HouseLineageService.deterministicLordGender(lord.id(), lord.index());
                spouseAppearance = HouseLineageService.generateAppearance(lord.kingdomId(), (String)spouseId, HouseLineageService.oppositeGender(lordGender));
            }
        } else {
            spouseId = "";
            spouseUuid = null;
            spouseDisplayName = "";
            spouseAttributes = NpcAttributeProfile.EMPTY;
            spouseAppearance = NpcAppearanceProfile.EMPTY;
        }
        return new HouseLordWorldState.HouseLordProfile(lord.id(), lord.uuid(), lord.kingdomId(), lord.houseId(), lord.displayName(), lord.leader(), lord.index(), lordAttributes, (String)spouseId, spouseUuid, spouseDisplayName, spouseAttributes, spouseAppearance);
    }

    private static boolean shouldGenerateAiLordSpouse(HouseLordWorldState.HouseLordProfile lord) {
        if (lord == null) {
            return false;
        }
        if (lord.leader()) {
            return true;
        }
        int chance = 25;
        long seed = 0x6C8E9CF570932BD5L ^ (long)lord.id().hashCode();
        return Math.floorMod((int)seed, 100) < chance;
    }

    private static NpcAppearanceProfile generateFemaleLordSpouseAppearance(String kingdomId, String seedKey) {
        class_2960 parsedKingdomId = class_2960.method_12829((String)kingdomId);
        Random random = new Random((long)(seedKey == null ? "" : seedKey).hashCode() * 31L + -7046029254386353131L);
        List<Integer> clothesOptions = NpcAppearanceProfile.availableClothesVariants(parsedKingdomId);
        int clothesVariant = clothesOptions.get(random.nextInt(clothesOptions.size()));
        return new NpcAppearanceProfile("Female", 1 + random.nextInt(5), 1 + random.nextInt(5), clothesVariant);
    }

    private static NpcAttributeProfile generateNpcAttributesForRole(CharacterSelectionOption option, String seedKey, KingdomNpcRole role) {
        if (role == KingdomNpcRole.LORD) {
            return NpcAttributeProfile.generate(option, seedKey, 20, 30);
        }
        if (role.isGuardDuty()) {
            return NpcAttributeProfile.generate(option, seedKey, 10, 20);
        }
        return NpcAttributeProfile.generate(option, seedKey, 0, 10);
    }

    private static Kingdom resolveKingdomForCastle(CastleInstance castle) {
        if (castle == null) {
            return null;
        }
        String kingdomId = !castle.getOwnerKingdomId().isBlank() ? castle.getOwnerKingdomId() : Optional.ofNullable(presetMetadataByCastleId.get(castle.getId())).map(CastlePresetMetadata::kingdomId).orElse("");
        class_2960 parsed = class_2960.method_12829((String)kingdomId);
        return parsed == null ? null : GotKingdoms.get(GotCastles.normalizeKingdomId(parsed));
    }

    private static boolean isCastlePlayerLoaded(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null) {
            return false;
        }
        int areaRadiusBlocks = Math.max(96, castle.getAreaRadiusBlocks() + 48);
        double maxDistanceSquared = (double)areaRadiusBlocks * (double)areaRadiusBlocks;
        class_243 center = class_243.method_24953((class_2382)castle.getBannerPos());
        for (class_3222 player : world.method_18456()) {
            if (player.method_7325() || !(player.method_5707(center) <= maxDistanceSquared)) continue;
            return true;
        }
        return false;
    }

    private static boolean isCastleNpcAreaLoaded(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null) {
            return false;
        }
        class_2338 bannerPos = castle.getBannerPos();
        return world.method_8393(bannerPos.method_10263() >> 4, bannerPos.method_10260() >> 4);
    }

    private static boolean needsImmediateCastlePopulationRefresh(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null) {
            return false;
        }
        if (!GotCastles.isCastleNpcAreaLoaded(world, castle)) {
            return false;
        }
        if (GotCastles.findActiveInvasionForCastle(castle.getId()) != null) {
            return false;
        }
        Kingdom kingdom = GotCastles.resolveKingdomForCastle(castle);
        if (kingdom == null) {
            return false;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        List castleNpcs = world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()));
        GotCastles.ensureCastleNpcProfiles(world, castle, castleNpcs);
        Set requiredProfileIds = castle.getCastleNpcProfiles().stream().map(CastleNpcProfile::id).collect(Collectors.toCollection(LinkedHashSet::new));
        if (requiredProfileIds.isEmpty()) {
            return false;
        }
        LinkedHashSet<String> presentProfileIds = new LinkedHashSet<String>();
        for (AbstractKingdomNpcEntity npc2 : castleNpcs) {
            if (npc2 instanceof SettlerNpcEntity || npc2.isIndependentMarriageNpc()) continue;
            String npcProfileId = npc2.getCastleNpcId();
            if (npcProfileId.isBlank() || !requiredProfileIds.contains(npcProfileId)) {
                return true;
            }
            presentProfileIds.add(npcProfileId);
        }
        return !presentProfileIds.containsAll(requiredProfileIds);
    }

    private static long resolveCastleRefreshDay(CastleInstance castle, class_3218 world) {
        long day = GotGameRules.affectedByTimeSkip(world) ? GotGameRules.skipAwareDay(world.method_8510(), world.method_8532()) : GotGameRules.regularDay(world.method_8510());
        long currentTimeOfDay = GotCastles.normalizedTimeOfDay(world.method_8532());
        long lastObserved = castle.getLastObservedPopulationTimeOfDay();
        if (GotGameRules.affectedByTimeSkip(world) && lastObserved >= 0L && currentTimeOfDay < lastObserved) {
            return Math.max(day, castle.getLastPopulationRefreshDay() + 1L);
        }
        return day;
    }

    private static long normalizedTimeOfDay(long timeOfDay) {
        long normalized = timeOfDay % 24000L;
        return normalized < 0L ? normalized + 24000L : normalized;
    }

    public static class_3222 resolvePlayerAttacker(class_1282 source) {
        class_1676 projectile;
        class_1297 class_12972;
        if (source == null) {
            return null;
        }
        class_1297 attacker = source.method_5529();
        if (attacker instanceof class_3222) {
            class_3222 player = (class_3222)attacker;
            return player;
        }
        class_1297 direct = source.method_5526();
        if (direct instanceof class_1676 && (class_12972 = (projectile = (class_1676)direct).method_24921()) instanceof class_3222) {
            class_3222 player = (class_3222)class_12972;
            return player;
        }
        return null;
    }

    public static void onCastleNpcKilled(class_3218 world, AbstractKingdomNpcEntity entity) {
        SettlerNpcEntity settler;
        if (world == null || entity == null || entity.got$getCastle().isBlank()) {
            return;
        }
        ActiveTitleChallenge titleChallenge = GotCastles.findActiveTitleChallengeForLord(entity);
        if (titleChallenge != null && entity instanceof AbstractKingdomLordNpcEntity) {
            AbstractKingdomLordNpcEntity challengedLord = (AbstractKingdomLordNpcEntity)entity;
            GotCastles.completeTitleChallenge(world, titleChallenge, challengedLord);
            return;
        }
        if (entity instanceof SettlerNpcEntity && (settler = (SettlerNpcEntity)entity).isAcceptedSettler()) {
            CastleInstance castle = GotCastles.getWorldState(world).get(settler.got$getCastle());
            if (castle != null) {
                long nextDay = GotGameRules.trackedDay(world) + 1L;
                ArrayList<SettlerProfile> updated = new ArrayList<SettlerProfile>();
                for (SettlerProfile profile : castle.getSettlers()) {
                    if (profile.id().equals(settler.getSettlerId())) {
                        updated.add(new SettlerProfile(profile.id(), profile.displayName(), profile.optionId(), profile.professionId(), profile.skillTierId(), true, nextDay, profile.attributes()));
                        continue;
                    }
                    updated.add(profile);
                }
                castle.setSettlers(updated);
                GotCastles.getWorldState(world).method_80();
            }
            return;
        }
        if (VillageWorldState.get(world).get(entity.got$getCastle()) != null) {
            GotVillages.onVillageNpcKilled(world, entity);
            return;
        }
        String profileId = entity.getCastleNpcId();
        boolean guardDutyCastleNpc = GotCastles.isGuardDutyCastleNpc(world, entity);
        CastleRecaptureState recapture = GotCastles.getWorldState(world).getRecapture(entity.got$getCastle());
        if (recapture != null && recapture.getStage() == CastleRecaptureStage.INVASION) {
            CastleInstance castle;
            if (recapture.isAttacker(entity.method_5667())) {
                CastleInstance castle2;
                recapture.removeAttackerUuid(entity.method_5667());
                GotCastles.getWorldState(world).method_80();
                if (recapture.getAttackerUuids().isEmpty() && (castle2 = GotCastles.getWorldState(world).get(recapture.getCastleId())) != null) {
                    GotCastles.winCastleRecaptureDefense(world, castle2, recapture);
                }
            } else if (!GotCastles.hasLivingCastleDefenders(world, recapture.getCastleId()) && (castle = GotCastles.getWorldState(world).get(recapture.getCastleId())) != null) {
                GotCastles.resolveSuccessfulRecapture(world, castle, recapture, (class_2561)class_2561.method_43470((String)"Recapture failed. Your guards were wiped out.").method_27692(class_124.field_1061));
            }
            if (guardDutyCastleNpc) {
                GotCastles.markCastlePopulationHandledForToday(world, entity.got$getCastle());
            }
            return;
        }
        ActiveCastleInvasion invasion = GotCastles.findActiveInvasionForCastle(entity.got$getCastle());
        if (invasion != null) {
            GotCastles.markCastlePopulationHandledForToday(world, entity.got$getCastle());
            GotCastles.recordRaidKill(invasion, entity);
            GotCastles.checkCastleInvasionObjectiveProgress(world, invasion);
            return;
        }
        if (profileId.isBlank()) {
            return;
        }
        if (guardDutyCastleNpc) {
            GotCastles.markCastlePopulationHandledForToday(world, entity.got$getCastle());
            return;
        }
        if (GotCastles.isNpcPermadeath(entity)) {
            GotCastles.handleCastleNpcPermadeath(world, entity);
            return;
        }
        long ticksUntilNextDay = Math.max(1L, 24000L - Math.floorMod(world.method_8532(), 24000L));
        GotCastles.schedulePendingNpcRespawn(world, entity.got$getCastle(), profileId, ticksUntilNextDay);
    }

    private static boolean isGuardDutyCastleNpc(class_3218 world, AbstractKingdomNpcEntity entity) {
        if (entity == null) {
            return false;
        }
        if (entity.getNpcRole().isGuardDuty()) {
            return true;
        }
        return GotCastles.isGuardDutyCastleProfile(world, entity.got$getCastle(), entity.getCastleNpcId());
    }

    private static void markCastlePopulationHandledForToday(class_3218 world, String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            return;
        }
        long today = GotCastles.resolveCastleRefreshDay(castle, world);
        if (today > castle.getLastPopulationRefreshDay()) {
            castle.setLastPopulationRefreshDay(today);
            castle.setLastObservedPopulationTimeOfDay(GotCastles.normalizedTimeOfDay(world.method_8532()));
            GotCastles.getWorldState(world).method_80();
        }
    }

    private static boolean isGuardDutyCastleProfile(class_3218 world, String castleId, String profileId) {
        if (world == null || castleId == null || castleId.isBlank() || profileId == null || profileId.isBlank()) {
            return false;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            return false;
        }
        return castle.getCastleNpcProfiles().stream().filter(profile -> profileId.equals(profile.id())).map(CastleNpcProfile::role).anyMatch(KingdomNpcRole::isGuardDuty);
    }

    public static void ensureCastleNpcIdentity(class_3218 world, AbstractKingdomNpcEntity npc) {
        if (world == null || npc == null || npc instanceof SettlerNpcEntity || npc instanceof AbstractDynastyNpcEntity || npc.isIndependentMarriageNpc() || npc.got$getCastle().isBlank()) {
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(npc.got$getCastle());
        if (castle == null || "player_claim".equals(castle.getDefinitionId())) {
            return;
        }
        if (GotCastles.ensureCastleNpcProfiles(world, castle, List.of(npc))) {
            GotCastles.getWorldState(world).method_80();
        }
    }

    private static void handleCastleNpcPermadeath(class_3218 world, AbstractKingdomNpcEntity entity) {
        CastleInstance castle = GotCastles.getWorldState(world).get(entity.got$getCastle());
        if (castle == null) {
            return;
        }
        if (entity instanceof AbstractKingdomLordNpcEntity) {
            AbstractKingdomLordNpcEntity lord = (AbstractKingdomLordNpcEntity)entity;
            GotCastles.handleLordPermadeath(world, castle, lord);
            return;
        }
        GotCastles.replaceCastleNpcProfile(world, castle, entity.getCastleNpcId(), entity.getNpcRole());
    }

    private static void replaceCastleNpcProfile(class_3218 world, CastleInstance castle, String profileId, KingdomNpcRole role) {
        if (world == null || castle == null || profileId == null || profileId.isBlank()) {
            return;
        }
        ArrayList<CastleNpcProfile> updated = new ArrayList<CastleNpcProfile>();
        boolean changed = false;
        for (CastleNpcProfile profile : castle.getCastleNpcProfiles()) {
            if (!profileId.equals(profile.id())) {
                updated.add(profile);
                continue;
            }
            changed = true;
            updated.add(GotCastles.regenerateCastleNpcProfile(castle, profile, role, world.method_8510()));
        }
        if (!changed) {
            return;
        }
        castle.setCastleNpcProfiles(updated);
        castle.setLastPopulationRefreshDay(-1L);
        GotCastles.getWorldState(world).method_80();
    }

    private static CastleNpcProfile regenerateCastleNpcProfile(CastleInstance castle, CastleNpcProfile profile, KingdomNpcRole role, long seedSalt) {
        String seedKey = castle.getId() + "|" + profile.id() + "|" + seedSalt;
        CharacterSelectionOption option = GotCastles.characterOptionForCastle(castle);
        return new CastleNpcProfile(profile.id(), UUID.randomUUID(), role.name(), profile.seedOffset(), "", GotCastles.generateNpcAttributesForRole(option, seedKey, role), NpcAppearanceProfile.generate(GotCastles.resolveCastleKingdomId(castle), seedKey + "|appearance"), profile.nobleSpouse(), profile.spouseProfileId(), profile.pointOfInterest(), profile.preferredSpawnPos(), profile.pointOfInterestRadius());
    }

    private static void handleLordPermadeath(class_3218 world, CastleInstance castle, AbstractKingdomLordNpcEntity lord) {
        String kingdomId = GotCastles.canonicalKingdomId(castle.getOwnerKingdomId());
        String houseId = GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), kingdomId, "");
        String lordProfileId = lord.getCastleNpcId();
        String lordName = lord.method_5477().getString();
        boolean wasKingdomLeader = GotCastles.isKingdomLeader(world, kingdomId, houseId, lordName);
        GotCastles.announceLordPermadeath(world, castle, lordName, kingdomId, houseId);
        List<HouseLordWorldState.HouseLordProfile> candidates = GotCastles.ensureHouseLordProfiles(world, kingdomId, houseId).stream().filter(profile -> !lordProfileId.equals(profile.id())).sorted(Comparator.comparing(profile -> !profile.leader()).thenComparingInt(HouseLordWorldState.HouseLordProfile::index).thenComparing(HouseLordWorldState.HouseLordProfile::displayName)).toList();
        if (!candidates.isEmpty()) {
            HouseLordWorldState.HouseLordProfile successor = candidates.getFirst();
            GotCastles.reassignLordTitlesToAiSuccessor(world, kingdomId, houseId, lordName, successor.displayName(), wasKingdomLeader);
            GotCastles.removeHouseLordProfile(world, kingdomId, houseId, lordProfileId, true);
            return;
        }
        class_3222 playerSuccessor = GotCastles.resolveAlphabeticalPlayerSuccessor(world, kingdomId, houseId);
        if (playerSuccessor != null) {
            GotCastles.reassignLordTitlesToPlayerSuccessor(world, kingdomId, houseId, lordName, playerSuccessor, wasKingdomLeader);
            GotCastles.removeHouseLordProfile(world, kingdomId, houseId, lordProfileId, true);
            return;
        }
        GotCastles.removeHouseLordProfile(world, kingdomId, houseId, lordProfileId, false);
        GotCastles.removeHouseFromWorld(world, kingdomId, houseId);
    }

    private static void reassignLordTitlesToAiSuccessor(class_3218 world, String kingdomId, String houseId, String previousLordName, String successorLordName, boolean wasKingdomLeader) {
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if (castle.getOwnerPlayerUuid() != null || !kingdomId.equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId())) || !houseId.equals(GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), castle.getOwnerKingdomId(), "")) || !previousLordName.equals(castle.getLordName())) continue;
            castle.setLordName(successorLordName);
            castle.setLastPopulationRefreshDay(-1L);
        }
        if (wasKingdomLeader) {
            String successorProfileId = GotCastles.resolveHouseLordProfileId(world, GotCastles.getWorldState(world).get(GotCastles.houseSeatCastleId(world, kingdomId, houseId)), successorLordName);
            KingdomLeaderWorldState.get(world).putLeader(new KingdomLeaderWorldState.KingdomLeaderProfile(kingdomId, houseId, successorProfileId, successorLordName, GotCastles.houseSeatCastleId(world, kingdomId, houseId)));
        }
        GotCastles.getWorldState(world).method_80();
    }

    private static void reassignLordTitlesToPlayerSuccessor(class_3218 world, String kingdomId, String houseId, String previousLordName, class_3222 player, boolean wasKingdomLeader) {
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        profile.setSelectedOptionId(houseId);
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if (!kingdomId.equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId())) || !houseId.equals(GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), castle.getOwnerKingdomId(), "")) || !previousLordName.equals(castle.getLordName())) continue;
            castle.setOwnerPlayerUuid(player.method_5667());
            castle.setOwnerKingdomId(profile.getSelectedKingdomId());
            castle.setOwnerHouseId(houseId);
            castle.setOwnerReligionId(profile.getSelectedReligionId());
            castle.setLordName(player.method_7334().getName());
            castle.setCastleNpcProfiles(GotCastles.preservedCastleResidentProfiles(castle));
            castle.setLastPopulationRefreshDay(-1L);
            GotVillages.synchronizeVillageAfterCastleOwnershipChange(world, castle);
        }
        GotCastles.updatePlayerOwnedHouseId(world, player.method_5667(), houseId);
        if (wasKingdomLeader) {
            KingdomLeaderWorldState.get(world).putLeader(new KingdomLeaderWorldState.KingdomLeaderProfile(kingdomId, houseId, "player:" + player.method_5845(), player.method_7334().getName(), GotCastles.houseSeatCastleId(world, kingdomId, houseId)));
        }
        GotCastles.getWorldState(world).method_80();
        GotCharacters.sendCharacterSelectionState(player);
        GotCastles.syncCastleMapToPlayers(world);
    }

    @Nullable
    private static class_3222 resolveAlphabeticalPlayerSuccessor(class_3218 world, String kingdomId, String houseId) {
        if (world == null || world.method_8503() == null) {
            return null;
        }
        return world.method_8503().method_3760().method_14571().stream().filter(player -> {
            PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
            return kingdomId.equals(GotCastles.canonicalKingdomId(profile.getSelectedKingdomId())) && houseId.equals(GotCastles.resolveOwnedHouseId(profile.getSelectedOptionId(), profile.getSelectedKingdomId(), ""));
        }).sorted(Comparator.comparing(player -> player.method_7334().getName(), String.CASE_INSENSITIVE_ORDER)).findFirst().orElse(null);
    }

    private static void removeHouseFromWorld(class_3218 world, String kingdomId, String houseId) {
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if (!kingdomId.equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId())) || !houseId.equals(GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), castle.getOwnerKingdomId(), ""))) continue;
            GotCastles.setCastleUncaptured(world, castle);
        }
    }

    private static boolean isCastleUncaptured(@Nullable CastleInstance castle) {
        return castle != null && castle.getOwnerPlayerUuid() == null && castle.getOwnerKingdomId().isBlank() && castle.getOwnerHouseId().isBlank();
    }

    private static void setCastleUncaptured(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null) {
            return;
        }
        castle.setOwnerPlayerUuid(null);
        castle.setOwnerKingdomId("");
        castle.setOwnerHouseId("");
        castle.setOwnerReligionId("");
        castle.setLordName("");
        castle.setCastleNpcProfiles(GotCastles.preservedCastleResidentProfiles(castle));
        castle.setRaidState(CastleRaidState.PEACE);
        castle.setRaidEndsAtTick(0L);
        castle.setLastPopulationRefreshDay(Long.MAX_VALUE);
        GotCastles.getWorldState(world).method_80();
        GotVillages.synchronizeVillageAfterCastleOwnershipChange(world, castle);
        GotCastles.syncStateToPlayers(world);
        GotCastles.syncCastleMapToPlayers(world);
    }

    private static void announceLordPermadeath(class_3218 world, CastleInstance castle, String lordName, String kingdomId, String houseId) {
        String castleName = GotCastles.displayNameForCastle(castle);
        String line = lordName + " has permanently fallen at " + castleName;
        GotCastles.appendKingdomEventLine(world, line, GotCastles.houseSigilTextureId(houseId, kingdomId), kingdomId);
        GotCastles.announceKingdomConflictResult(world, castleName, "Lord Permanently Slain", kingdomId, kingdomId);
    }

    public static void handleSettlerDecision(class_3222 player, int targetEntityId, boolean accept) {
        class_1937 class_19372;
        if (player == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        class_1297 entity = world.method_8469(targetEntityId);
        if (!(entity instanceof SettlerNpcEntity)) {
            return;
        }
        SettlerNpcEntity settler = (SettlerNpcEntity)entity;
        CastleInstance castle = GotCastles.getWorldState(world).get(settler.got$getCastle());
        if (castle == null || castle.getOwnerPlayerUuid() == null || !castle.getOwnerPlayerUuid().equals(player.method_5667())) {
            return;
        }
        if (accept) {
            long nextDay = GotGameRules.trackedDay(world) + 1L;
            String settlerId = settler.getSettlerId();
            if (settlerId.isBlank()) {
                return;
            }
            long acceptedCount = castle.getSettlers().stream().filter(SettlerProfile::accepted).count();
            if (acceptedCount >= (long)GotCastles.settlerCapacityFor(castle)) {
                player.method_7353((class_2561)class_2561.method_43470((String)"Your settlement is at capacity.").method_27692(class_124.field_1061), true);
                return;
            }
            ArrayList<SettlerProfile> updated = new ArrayList<SettlerProfile>();
            boolean foundProfile = false;
            for (SettlerProfile profile : castle.getSettlers()) {
                if (profile.id().equals(settlerId)) {
                    updated.add(new SettlerProfile(profile.id(), profile.displayName(), profile.optionId(), profile.professionId(), profile.skillTierId(), true, nextDay, profile.attributes()));
                    foundProfile = true;
                    continue;
                }
                updated.add(profile);
            }
            if (!foundProfile) {
                updated.add(GotCastles.profileFromAcceptedSettlerEntity(settler, nextDay));
            }
            castle.setSettlers(updated);
            settler.setAcceptedSettler(true);
            GotCastles.getWorldState(world).method_80();
            player.method_7353((class_2561)class_2561.method_43470((String)"Settler accepted into the settlement.").method_27692(class_124.field_1065), true);
            return;
        }
        castle.removeSettler(settler.getSettlerId());
        GotCastles.getWorldState(world).method_80();
        settler.method_31472();
        player.method_7353((class_2561)class_2561.method_43470((String)"Settler turned away.").method_27692(class_124.field_1054), true);
    }

    private static SettlerProfile profileFromAcceptedSettlerEntity(SettlerNpcEntity settler, long offeredDay) {
        class_2561 customName;
        String settlerId = settler.getSettlerId();
        CharacterSelectionOption option = settler.getCharacterOption();
        NpcAttributeProfile attributes = settler.getNpcAttributes();
        if (!attributes.isAssigned() && option != null) {
            attributes = NpcAttributeProfile.generate(option, settlerId);
        }
        String displayName = (customName = settler.method_5797()) != null ? customName.getString() : settler.method_5477().getString();
        return new SettlerProfile(settlerId, displayName, settler.getSettlerOptionId(), settler.getProfession().getId(), settler.getSkillTier().getId(), true, offeredDay, attributes);
    }

    public static void handleRemoveSettler(class_3222 player, String castleId, String settlerId) {
        class_1937 class_19372;
        if (player == null || castleId == null || castleId.isBlank() || settlerId == null || settlerId.isBlank() || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null || castle.getOwnerPlayerUuid() == null || !castle.getOwnerPlayerUuid().equals(player.method_5667())) {
            return;
        }
        castle.removeSettler(settlerId);
        for (SettlerNpcEntity settler : world.method_18198(class_5575.method_31795(SettlerNpcEntity.class), entity -> castleId.equals(entity.got$getCastle()) && settlerId.equals(entity.getSettlerId()))) {
            settler.method_31472();
        }
        GotCastles.getWorldState(world).method_80();
        GotCastles.openCastleScreen(player, castleId);
    }

    public static void handleSettlementLevelUp(class_3222 player, String castleId) {
        class_1937 class_19372;
        if (player == null || castleId == null || castleId.isBlank() || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null || !"player_claim".equals(castle.getDefinitionId()) || castle.getOwnerPlayerUuid() == null || !castle.getOwnerPlayerUuid().equals(player.method_5667())) {
            return;
        }
        if (!castle.getOwnerKingdomId().isBlank() && PlayerKingdomWorldState.get(world).getByKingdomId(castle.getOwnerKingdomId()) != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This settlement is now a kingdom seat.").method_27692(class_124.field_1054), true);
            GotCastles.openCastleScreen(player, castleId);
            return;
        }
        SettlerLevelRule rule = GotCastles.settlerRuleFor(castle);
        if (!GotCastles.meetsSettlerLevelRequirements(castle, rule)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Settlement requirements not met.").method_27692(class_124.field_1061), true);
            GotCastles.openCastleScreen(player, castleId);
            return;
        }
        if (castle.getSettlementLevel() < 5) {
            castle.setSettlementLevel(castle.getSettlementLevel() + 1);
        } else if (rule.nextTier() != null) {
            castle.setSettlementTier(rule.nextTier());
            castle.setSettlementLevel(1);
        } else {
            player.method_7353((class_2561)class_2561.method_43470((String)"This settlement is already at its highest tier.").method_27692(class_124.field_1054), true);
            GotCastles.openCastleScreen(player, castleId);
            return;
        }
        GotCastles.getWorldState(world).method_80();
        player.method_7353((class_2561)class_2561.method_43470((String)("Settlement advanced to " + castle.getSettlementTier().getDisplayName() + " Level " + castle.getSettlementLevel() + ".")).method_27692(class_124.field_1065), true);
        GotCastles.openCastleScreen(player, castleId);
    }

    private static boolean meetsSettlerLevelRequirements(CastleInstance castle, SettlerLevelRule rule) {
        long acceptedCount = castle.getSettlers().stream().filter(SettlerProfile::accepted).count();
        if (acceptedCount < (long)rule.requiredSettlers()) {
            return false;
        }
        return GotCastles.acceptedProfessionCount(castle, SettlerProfession.MINER) >= (long)rule.requiredMiners() && GotCastles.acceptedProfessionCount(castle, SettlerProfession.LUMBERJACK) >= (long)rule.requiredLumberjacks() && GotCastles.acceptedProfessionCount(castle, SettlerProfession.FARMER) >= (long)rule.requiredFarmers();
    }

    private static long acceptedProfessionCount(CastleInstance castle, SettlerProfession profession) {
        return castle.getSettlers().stream().filter(SettlerProfile::accepted).filter(profile -> profile.profession() == profession).count();
    }

    private static boolean hasLivingCastleNpcs(class_3218 world, String castleId) {
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            return false;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        return !world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castleId.equals(npc.got$getCastle())).isEmpty();
    }

    private static int countLivingCrusadeTargets(class_3218 world, String castleId) {
        return GotCastles.countLivingInvasionTargets(world, castleId, GotCastles::isCrusadeTargetNpc);
    }

    private static int countLivingConquerTargets(class_3218 world, String castleId) {
        return GotCastles.countLivingInvasionTargets(world, castleId, GotCastles::isConquerTargetNpc);
    }

    private static int countLivingInvasionTargets(class_3218 world, String castleId, Predicate<AbstractKingdomNpcEntity> predicate) {
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            return 0;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        return world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castleId.equals(npc.got$getCastle()) && predicate.test((AbstractKingdomNpcEntity)npc)).size();
    }

    private static boolean isCrusadeTargetNpc(AbstractKingdomNpcEntity npc) {
        return npc != null && npc.getNpcRole() == KingdomNpcRole.CIVILIAN && !(npc instanceof AbstractKingdomCaptainNpcEntity) && !(npc instanceof SettlerNpcEntity) && !npc.isIndependentMarriageNpc();
    }

    private static boolean isConquerTargetNpc(AbstractKingdomNpcEntity npc) {
        return npc != null && (npc.getNpcRole().isGuardDuty() || npc.getNpcRole() == KingdomNpcRole.LORD);
    }

    private static boolean isRaidProfessionNpc(AbstractKingdomNpcEntity npc) {
        if (npc == null) {
            return false;
        }
        KingdomNpcRole role = npc.getNpcRole();
        return role == KingdomNpcRole.BLACKSMITH || role == KingdomNpcRole.ELDER || role == KingdomNpcRole.ARENA_MASTER || role == KingdomNpcRole.FISHERMAN || role == KingdomNpcRole.CART_RIDER || npc instanceof AbstractKingdomCaptainNpcEntity;
    }

    private static String objectiveTextFor(class_3218 world, ActiveCastleInvasion invasion) {
        if (invasion == null) {
            return "";
        }
        return switch (invasion.mode()) {
            default -> throw new MatchException(null, null);
            case CastleInvasionMode.CRUSADE -> "Kill " + GotCastles.countLivingCrusadeTargets(world, invasion.castleId()) + " Peasant NPC's";
            case CastleInvasionMode.RAID -> "Kill as many NPCs as possible";
            case CastleInvasionMode.CONQUER, CastleInvasionMode.CHALLENGE -> invasion.awaitingBannerBreak() ? "Break the Castle Banner" : "Kill the Guards and Lords";
        };
    }

    private static void syncCastleInvasionObjective(class_3218 world, ActiveCastleInvasion invasion) {
        if (world == null || invasion == null) {
            return;
        }
        class_3222 player = world.method_8503().method_3760().method_14602(invasion.playerUuid());
        if (GotCastles.canSendPlayPackets(player)) {
            ServerPlayNetworking.send((class_3222)player, (class_8710)new CastleInvasionStatePayload(true, invasion.castleId(), invasion.mode().getId(), GotCastles.objectiveTextFor(world, invasion)));
        }
    }

    private static void handleInvasionDefendersCleared(class_3218 world, ActiveCastleInvasion invasion) {
        CastleInstance castle = GotCastles.getWorldState(world).get(invasion.castleId());
        class_3222 player = world.method_8503().method_3760().method_14602(invasion.playerUuid());
        if (castle == null || player == null) {
            GotCastles.failCastleInvasion(world, invasion.playerUuid(), true);
            return;
        }
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        switch (invasion.mode()) {
            case CRUSADE: {
                castle.setOwnerReligionId(profile.getSelectedReligionId());
                GotCastles.deferCastlePopulationRefreshUntilNextDay(world, castle);
                GotCastles.rewardSuccessfulInvasion(player, CastleInvasionMode.CRUSADE);
                PledgeQuestService.onInvasionResolved(player, CastleInvasionMode.CRUSADE, true);
                player.method_7353((class_2561)class_2561.method_43470((String)"Crusade complete. The castle follows your faith.").method_27692(class_124.field_1065), false);
                GotCastles.appendPlayerInvasionOutcomeLog(world, invasion, castle, true);
                GotCastles.endCastleInvasion(world, player.method_5667(), castle.getId(), true);
                break;
            }
            case RAID: {
                GotCastles.completeRaidInvasion(world, invasion, true);
                break;
            }
            case CHALLENGE: {
                invasion.setAwaitingBannerBreak(true);
                player.method_7353((class_2561)class_2561.method_43470((String)"Break the banner to challenge this castle.").method_27692(class_124.field_1065), false);
                GotCastles.syncCastleInvasionObjective(world, invasion);
                break;
            }
            case CONQUER: {
                invasion.setAwaitingBannerBreak(true);
                player.method_7353((class_2561)class_2561.method_43470((String)"Break the banner to conquer this castle.").method_27692(class_124.field_1065), false);
                GotCastles.syncCastleInvasionObjective(world, invasion);
            }
        }
        GotCastles.syncStateToPlayers(world);
    }

    private static void completeRaidInvasion(class_3218 world, ActiveCastleInvasion invasion, boolean allNpcsKilled) {
        if (world == null || invasion == null) {
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(invasion.castleId());
        class_3222 player = world.method_8503().method_3760().method_14602(invasion.playerUuid());
        if (castle == null || player == null) {
            GotCastles.failCastleInvasion(world, invasion.playerUuid(), true);
            return;
        }
        int raidReputation = invasion.raidNormalKills() * 2 + invasion.raidProfessionKills() * 4 + invasion.raidGuardKills() * 8 + invasion.raidLordKills() * 15;
        if (raidReputation > 0) {
            ReputationExperienceService.addExperience(player, raidReputation);
        }
        if (allNpcsKilled && raidReputation > 0) {
            GotCastles.rewardSuccessfulInvasion(player, CastleInvasionMode.RAID);
        }
        GotCastles.deferCastlePopulationRefreshUntilNextDay(world, castle);
        PledgeQuestService.onInvasionResolved(player, CastleInvasionMode.RAID, true);
        player.method_7353((class_2561)class_2561.method_43470((String)"Raid complete. The defenders regroup.").method_27692(class_124.field_1065), false);
        GotCastles.appendPlayerInvasionOutcomeLog(world, invasion, castle, true);
        GotCastles.endCastleInvasion(world, player.method_5667(), castle.getId(), true);
        GotCastles.syncStateToPlayers(world);
    }

    private static void recordRaidKill(ActiveCastleInvasion invasion, AbstractKingdomNpcEntity npc) {
        if (invasion == null || npc == null || invasion.mode() != CastleInvasionMode.RAID) {
            return;
        }
        KingdomNpcRole role = npc.getNpcRole();
        if (role == KingdomNpcRole.LORD) {
            invasion.addRaidLordKill();
        } else if (role.isGuardDuty()) {
            invasion.addRaidGuardKill();
        } else if (GotCastles.isRaidProfessionNpc(npc)) {
            invasion.addRaidProfessionKill();
        } else {
            invasion.addRaidNormalKill();
        }
    }

    private static void checkCastleInvasionObjectiveProgress(class_3218 world, ActiveCastleInvasion invasion) {
        if (world == null || invasion == null) {
            return;
        }
        switch (invasion.mode()) {
            case CRUSADE: {
                if (GotCastles.countLivingCrusadeTargets(world, invasion.castleId()) <= 0) {
                    GotCastles.handleInvasionDefendersCleared(world, invasion);
                    break;
                }
                GotCastles.syncCastleInvasionObjective(world, invasion);
                break;
            }
            case RAID: {
                if (GotCastles.hasLivingCastleNpcs(world, invasion.castleId())) break;
                GotCastles.completeRaidInvasion(world, invasion, true);
                break;
            }
            case CONQUER: 
            case CHALLENGE: {
                if (invasion.awaitingBannerBreak() || GotCastles.countLivingConquerTargets(world, invasion.castleId()) > 0) break;
                GotCastles.handleInvasionDefendersCleared(world, invasion);
            }
        }
    }

    private static void completeConquest(class_3218 world, class_3222 player, CastleInstance castle) {
        if (!GotCastles.canAcquireConqueredCastle(world, player, castle.getTier(), castle.getId())) {
            GotCastles.failCastleInvasion(world, player.method_5667(), true);
            return;
        }
        ActiveCastleInvasion invasion = ACTIVE_CASTLE_INVASIONS.get(player.method_5667());
        CastleInvasionMode mode = invasion == null ? CastleInvasionMode.CONQUER : invasion.mode();
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        castle.setOwnerPlayerUuid(player.method_5667());
        castle.setOwnerKingdomId(profile.getSelectedKingdomId());
        castle.setOwnerHouseId(profile.getSelectedOptionId());
        castle.setOwnerReligionId(profile.getSelectedReligionId());
        castle.setLordName(player.method_7334().getName());
        GotVillages.synchronizeVillageAfterCastleOwnershipChange(world, castle);
        GotCastles.deferCastlePopulationRefreshUntilNextDay(world, castle);
        GotCastles.resetCastleRecaptureState(world, castle, player.method_5667());
        GotCastles.rewardSuccessfulInvasion(player, mode);
        PledgeQuestService.onInvasionResolved(player, mode, true);
        player.method_7353((class_2561)class_2561.method_43470((String)(mode == CastleInvasionMode.CHALLENGE ? "Castle challenged successfully." : "Castle conquered.")).method_27692(class_124.field_1065), false);
        if (invasion != null) {
            GotCastles.appendPlayerInvasionOutcomeLog(world, invasion, castle, true);
        }
        GotCastles.endCastleInvasion(world, player.method_5667(), castle.getId(), true);
        GotCastles.syncStateToPlayers(world);
        GotCastles.syncCastleMapToPlayers(world);
    }

    static void respawnCastlePopulation(class_3218 world, CastleInstance castle) {
        if (castle == null || "player_claim".equals(castle.getDefinitionId())) {
            return;
        }
        if (GotCastles.isCastleUnderActivePlayerInvasion(castle.getId())) {
            GotCastles.deferCastlePopulationRefreshUntilNextDay(world, castle);
            return;
        }
        GotCastles.clearPendingNpcRespawns(castle.getId());
        GotCastles.markCastleNpcGlowing(world, castle.getId(), false);
        Kingdom kingdom = GotCastles.resolveKingdomForCastle(castle);
        if (kingdom == null) {
            return;
        }
        castle.setCastleNpcProfiles(GotCastles.preservedCastleResidentProfiles(castle));
        GotCastles.spawnCastleNpcs(world, castle, kingdom);
    }

    private static void deferCastlePopulationRefreshUntilNextDay(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null || "player_claim".equals(castle.getDefinitionId())) {
            return;
        }
        GotCastles.clearPendingNpcRespawns(castle.getId());
        GotCastles.markCastleNpcGlowing(world, castle.getId(), false);
        castle.setCastleNpcProfiles(GotCastles.preservedCastleResidentProfiles(castle));
        GotCastles.markCastlePopulationHandledForToday(world, castle.getId());
        GotCastles.getWorldState(world).method_80();
    }

    public static void refreshCastlePopulationForMarriage(class_3218 world, String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            return;
        }
        GotCastles.respawnCastlePopulation(world, castle);
    }

    public static void replaceCastleNpcProfileAfterMarriage(class_3218 world, String castleId, String profileId) {
        boolean present;
        if (world == null || castleId == null || castleId.isBlank() || profileId == null || profileId.isBlank()) {
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null || "player_claim".equals(castle.getDefinitionId())) {
            return;
        }
        Kingdom kingdom = GotCastles.resolveKingdomForCastle(castle);
        if (kingdom == null) {
            return;
        }
        CastleNpcProfile profile = castle.getCastleNpcProfiles().stream().filter(candidate -> profileId.equals(candidate.id())).findFirst().orElse(null);
        if (profile == null) {
            GotCastles.ensureCastleNpcProfiles(world, castle, null);
            profile = castle.getCastleNpcProfiles().stream().filter(candidate -> profileId.equals(candidate.id())).findFirst().orElse(null);
        }
        if (profile == null) {
            return;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        boolean bl = present = !world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castleId.equals(npc.got$getCastle()) && profileId.equals(npc.getCastleNpcId())).isEmpty();
        if (!present) {
            GotCastles.spawnCastleResidentNpc(world, castle, kingdom, profile);
        }
    }

    private static void triggerInvasionReinforcements(class_3218 world, ActiveCastleInvasion invasion, CastleInstance castle, class_3222 player) {
        invasion.setReinforcementsSpawned(true);
        Kingdom kingdom = GotCastles.resolveKingdomForCastle(castle);
        if (kingdom == null) {
            return;
        }
        class_1299<? extends AbstractKingdomGuardNpcEntity> guardType = GotEntities.guardTypeForKingdom(kingdom);
        if (guardType == null) {
            return;
        }
        int reinforcementCount = Math.max(1, (GotCastles.baseGuardCountFor(castle) + 1) / 2);
        reinforcementCount += GotCastles.reinforcementBonusFor(invasion.mode(), castle);
        long expiresAtTick = world.method_8510() + 1200L;
        for (int index = 0; index < reinforcementCount; ++index) {
            AbstractKingdomGuardNpcEntity reinforcement = GotCastles.spawnCastleNpc(world, guardType, castle, 1700 + index);
            if (reinforcement == null) continue;
            if (invasion.mode() == CastleInvasionMode.CONQUER || invasion.mode() == CastleInvasionMode.CHALLENGE) {
                GotCastles.applyConquerBuffs(reinforcement);
            }
            reinforcement.alertAgainst(player, expiresAtTick);
        }
        player.method_7353((class_2561)class_2561.method_43470((String)"Reinforcements have arrived").method_27692(class_124.field_1061), true);
    }

    private static int baseGuardCountFor(CastleInstance castle) {
        return GotCastles.guardCountFor(castle.getTier());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void clearPendingNpcRespawns(String castleId) {
        if (castleId == null || castleId.isBlank()) {
            return;
        }
        PriorityQueue<PendingNpcRespawn> priorityQueue = PENDING_NPC_RESPAWNS;
        synchronized (priorityQueue) {
            PENDING_NPC_RESPAWNS.removeIf(respawn -> castleId.equals(respawn.castleId()));
        }
    }

    static ActiveCastleInvasion findActiveInvasionForCastle(String castleId) {
        for (ActiveCastleInvasion invasion : ACTIVE_CASTLE_INVASIONS.values()) {
            if (!castleId.equals(invasion.castleId())) continue;
            return invasion;
        }
        return null;
    }

    private static boolean isCastleUnderActivePlayerInvasion(String castleId) {
        return castleId != null && !castleId.isBlank() && GotCastles.findActiveInvasionForCastle(castleId) != null;
    }

    private static void endCastleInvasion(class_3218 world, UUID playerUuid, String castleId, boolean startCooldown) {
        GotCastles.endCastleInvasion(world, playerUuid, castleId, startCooldown, true);
    }

    private static void endCastleInvasion(class_3218 world, UUID playerUuid, String castleId, boolean startCooldown, boolean notifyPlayer) {
        CastleInstance castle;
        if (castleId != null && !castleId.isBlank()) {
            ACTIVE_GATE_BREACHES.entrySet().removeIf(entry -> castleId.equals(((GateBreachProgress)entry.getValue()).castleId()));
        }
        ActiveCastleInvasion invasion = ACTIVE_CASTLE_INVASIONS.remove(playerUuid);
        class_3222 player = world.method_8503().method_3760().method_14602(playerUuid);
        if (invasion != null) {
            invasion.bossBar().method_14094();
        }
        CastleInstance castleInstance = castle = castleId == null || castleId.isBlank() ? null : GotCastles.getWorldState(world).get(castleId);
        if (castle != null) {
            if (invasion != null && (invasion.mode() == CastleInvasionMode.CONQUER || invasion.mode() == CastleInvasionMode.CHALLENGE)) {
                GotCastles.setConquerCastleBuffs(world, castle.getId(), false);
            }
            GotCastles.markCastleNpcGlowing(world, castle.getId(), false);
            if (startCooldown) {
                GotCastles.startCastleCooldown(world, castle);
            } else {
                castle.setRaidState(CastleRaidState.PEACE);
                castle.setRaidEndsAtTick(0L);
            }
        }
        if (notifyPlayer && GotCastles.canSendPlayPackets(player)) {
            ServerPlayNetworking.send((class_3222)player, (class_8710)new CastleInvasionStatePayload(false, castleId == null ? "" : castleId, "", ""));
        }
        GotCastles.setCastleGatesInvasionMode(world, castleId, false);
    }

    public static String getActiveInvasionCastleId(UUID playerUuid) {
        if (playerUuid == null) {
            return "";
        }
        ActiveCastleInvasion invasion = ACTIVE_CASTLE_INVASIONS.get(playerUuid);
        return invasion == null ? "" : invasion.castleId();
    }

    private static void markCastleNpcGlowing(class_3218 world, String castleId, boolean glowing) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            return;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castleId.equals(npc.got$getCastle()))) {
            npc2.method_5834(glowing);
        }
    }

    private static void startCastleCooldown(class_3218 world, CastleInstance castle) {
        castle.setRaidState(CastleRaidState.RAIDABLE);
        castle.setRaidEndsAtTick(world.method_8510() + 6000L);
    }

    private static void failCastleInvasion(class_3218 world, UUID playerUuid, boolean startCooldown) {
        GotCastles.failCastleInvasion(world, playerUuid, startCooldown, true);
    }

    private static void failCastleInvasion(class_3218 world, UUID playerUuid, boolean startCooldown, boolean notifyPlayer) {
        CastleInstance castle;
        ActiveCastleInvasion invasion = ACTIVE_CASTLE_INVASIONS.get(playerUuid);
        if (invasion == null) {
            return;
        }
        class_3222 player = world.method_8503().method_3760().method_14602(playerUuid);
        if (notifyPlayer && GotCastles.canSendPlayPackets(player)) {
            PledgeQuestService.onInvasionResolved(player, invasion.mode(), false);
            player.method_7353((class_2561)class_2561.method_43470((String)"Invasion failed.").method_27692(class_124.field_1061), false);
        }
        if ((castle = GotCastles.getWorldState(world).get(invasion.castleId())) != null) {
            GotCastles.appendPlayerInvasionOutcomeLog(world, invasion, castle, false);
        }
        GotCastles.endCastleInvasion(world, playerUuid, invasion.castleId(), startCooldown, notifyPlayer);
    }

    private static void failTitleChallenge(class_3218 world, ActiveTitleChallenge challenge, boolean timeExpired) {
        if (world == null || challenge == null) {
            return;
        }
        ACTIVE_TITLE_CHALLENGES.remove(challenge.playerUuid());
        class_3222 player = world.method_8503().method_3760().method_14602(challenge.playerUuid());
        class_1297 entity = world.method_14190(challenge.lordUuid());
        if (entity instanceof AbstractKingdomLordNpcEntity) {
            AbstractKingdomLordNpcEntity lord = (AbstractKingdomLordNpcEntity)entity;
            lord.resetCastleThreat();
            if (player != null) {
                ((PlayerNpcRelationsHolder)player).got$getNpcRelationsProfile().setRelation(challenge.lordProfileId(), -20);
                GotNpcRelations.sendDialogueRelation(player, lord);
            }
        }
        if (player != null && player.method_5805()) {
            GotCastles.teleportTitleChallengePlayerBack(world, player, challenge);
            player.method_7353((class_2561)class_2561.method_43470((String)(timeExpired ? "Title challenge failed." : "Title challenge ended.")).method_27692(class_124.field_1061), false);
        }
    }

    private static void teleportTitleChallengePlayerBack(class_3218 world, class_3222 player, ActiveTitleChallenge challenge) {
        if (world == null || player == null || challenge == null) {
            return;
        }
        GotCastles.markArenaTeleport((class_1297)player);
        player.method_48105(world, (double)challenge.returnPos().method_10263() + 0.5, (double)challenge.returnPos().method_10264(), (double)challenge.returnPos().method_10260() + 0.5, Set.of(), challenge.returnYaw(), challenge.returnPitch());
        ARENA_ENTITY_SNAPSHOTS.remove(player.method_5667());
        ARENA_TELEPORT_GRACE.remove(player.method_5667());
        ARENA_LOGOUT_RELEASES.remove(player.method_5667());
    }

    private static void completeTitleChallenge(class_3218 world, ActiveTitleChallenge challenge, AbstractKingdomLordNpcEntity lord) {
        if (world == null || challenge == null) {
            return;
        }
        ACTIVE_TITLE_CHALLENGES.remove(challenge.playerUuid());
        class_3222 player = world.method_8503().method_3760().method_14602(challenge.playerUuid());
        CastleInstance sourceCastle = GotCastles.getWorldState(world).get(challenge.castleId());
        if (player == null || sourceCastle == null) {
            return;
        }
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        String houseId = GotCastles.resolveOwnedHouseId(challenge.houseId(), challenge.kingdomId(), "");
        boolean wasKingdomLeader = GotCastles.isKingdomLeader(world, challenge.kingdomId(), houseId, challenge.lordName());
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if (castle.getOwnerPlayerUuid() != null || !challenge.kingdomId().equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId())) || !houseId.equals(GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), castle.getOwnerKingdomId(), ""))) continue;
            castle.setOwnerPlayerUuid(player.method_5667());
            castle.setOwnerKingdomId(profile.getSelectedKingdomId());
            castle.setOwnerHouseId(houseId);
            castle.setOwnerReligionId(profile.getSelectedReligionId());
            castle.setLordName(player.method_7334().getName());
            castle.setCastleNpcProfiles(GotCastles.preservedCastleResidentProfiles(castle));
            castle.setLastPopulationRefreshDay(-1L);
            GotVillages.synchronizeVillageAfterCastleOwnershipChange(world, castle);
        }
        profile.setSelectedOptionId(houseId);
        GotCastles.updatePlayerOwnedHouseId(world, player.method_5667(), houseId);
        GotCastles.removeHouseLordProfile(world, challenge.kingdomId(), houseId, challenge.lordProfileId(), true);
        if (wasKingdomLeader) {
            KingdomLeaderWorldState.get(world).putLeader(new KingdomLeaderWorldState.KingdomLeaderProfile(challenge.kingdomId(), houseId, "player:" + player.method_5845(), player.method_7334().getName(), challenge.castleId()));
        }
        player.method_7353((class_2561)class_2561.method_43470((String)"Title challenge won. You claimed the house holdings.").method_27692(class_124.field_1065), false);
        lord.resetCastleThreat();
        GotCastles.teleportTitleChallengePlayerBack(world, player, challenge);
        GotCastles.syncStateToPlayers(world);
        GotCastles.syncCastleMapToPlayers(world);
        GotCharacters.sendCharacterSelectionState(player);
    }

    private static void appendPlayerInvasionAttemptLog(class_3218 world, ActiveCastleInvasion invasion, CastleInstance castle) {
        if (world == null || invasion == null || castle == null) {
            return;
        }
        String line = invasion.playerName() + " attempts to " + GotCastles.displayNameFor(invasion.mode()) + " " + GotCastles.displayNameForCastle(castle);
        GotCastles.appendKingdomEventLine(world, line, GotCastles.houseSigilTextureId(invasion.attackerHouseId(), invasion.attackerKingdomId()), invasion.attackerKingdomId(), invasion.defenderKingdomId());
        GotCastles.announceKingdomConflictAttempt(world, GotCastles.displayNameForCastle(castle), GotCastles.displayNameFor(invasion.mode()) + " Incoming");
    }

    private static void appendPlayerInvasionOutcomeLog(class_3218 world, ActiveCastleInvasion invasion, CastleInstance castle, boolean successful) {
        if (world == null || invasion == null || castle == null) {
            return;
        }
        String line = invasion.playerName() + " " + (successful ? "successfully" : "unsuccessfully") + " " + GotCastles.pastTenseFor(invasion.mode()) + " " + GotCastles.displayNameForCastle(castle);
        GotCastles.appendKingdomEventLine(world, line, GotCastles.houseSigilTextureId(invasion.attackerHouseId(), invasion.attackerKingdomId()), invasion.attackerKingdomId(), invasion.defenderKingdomId());
        GotCastles.announceKingdomConflictResult(world, GotCastles.displayNameForCastle(castle), GotCastles.displayNameFor(invasion.mode()) + (successful ? " Successful" : " Unsuccessful"), successful ? invasion.attackerKingdomId() : invasion.defenderKingdomId(), successful ? invasion.defenderKingdomId() : invasion.attackerKingdomId());
    }

    private static void appendKingdomEventLine(class_3218 world, String line, String iconTextureId, String ... kingdomIds) {
        LinkedHashSet<String> uniqueKingdomIds = new LinkedHashSet<String>();
        for (String kingdomId : kingdomIds) {
            if (kingdomId == null || kingdomId.isBlank()) continue;
            uniqueKingdomIds.add(kingdomId);
        }
        for (String kingdomId : uniqueKingdomIds) {
            GotKingdomEventLogs.append(world, kingdomId, line, iconTextureId);
        }
    }

    private static String houseSigilTextureId(String houseId, String kingdomId) {
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, GotCastles.canonicalKingdomId(kingdomId), "");
        CharacterSelectionOption option = GotCharacterOptions.get(resolvedHouseId);
        return option == null ? "" : option.texture().toString();
    }

    static void announceKingdomConflictAttempt(class_3218 world, String title, String description) {
        if (world == null) {
            return;
        }
        for (class_3222 player : world.method_18456()) {
            ModAdvancementService.send(player, "kingdom_conflict_attempt", title, description, class_1802.field_8371, -11187, -11187);
        }
    }

    static void announceKingdomConflictResult(class_3218 world, String title, String description, String winningKingdomId, String losingKingdomId) {
        if (world == null) {
            return;
        }
        for (class_3222 player : world.method_18456()) {
            PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
            String playerKingdomId = GotCastles.canonicalKingdomId(profile.getSelectedKingdomId());
            int color = -11187;
            if (!playerKingdomId.isBlank()) {
                if (playerKingdomId.equals(GotCastles.canonicalKingdomId(winningKingdomId))) {
                    color = -10497171;
                } else if (playerKingdomId.equals(GotCastles.canonicalKingdomId(losingKingdomId))) {
                    color = -2074022;
                }
            }
            ModAdvancementService.send(player, "kingdom_conflict_result", title, description, class_1802.field_8862, color, color);
        }
    }

    private static String pastTenseFor(CastleInvasionMode mode) {
        return switch (mode) {
            default -> throw new MatchException(null, null);
            case CastleInvasionMode.CRUSADE -> "Crusaded";
            case CastleInvasionMode.RAID -> "Raided";
            case CastleInvasionMode.CHALLENGE -> "Challenged";
            case CastleInvasionMode.CONQUER -> "Conquered";
        };
    }

    private static void rewardSuccessfulInvasion(class_3222 player, CastleInvasionMode mode) {
        boolean skillStateChanged = false;
        switch (mode) {
            case RAID: {
                skillStateChanged |= SkillExperienceService.addPoints(player, SkillAttributeType.INTELLIGENCE, 2) > 0;
                skillStateChanged |= SkillExperienceService.addPoints(player, SkillAttributeType.LUCK, 1) > 0;
                ReputationExperienceService.addExperience(player, 25);
                break;
            }
            case CRUSADE: {
                skillStateChanged |= SkillExperienceService.addPoints(player, SkillAttributeType.CHARISMA, 1) > 0;
                ReputationExperienceService.addExperience(player, 30);
                break;
            }
            case CONQUER: 
            case CHALLENGE: {
                skillStateChanged |= SkillExperienceService.addPoints(player, SkillAttributeType.HEALTH, 1) > 0;
                skillStateChanged |= SkillExperienceService.addPoints(player, SkillAttributeType.INTELLIGENCE, 1) > 0;
                skillStateChanged |= SkillExperienceService.addPoints(player, SkillAttributeType.LUCK, 1) > 0;
                skillStateChanged |= SkillExperienceService.addPoints(player, SkillAttributeType.CHARISMA, 1) > 0;
                ReputationExperienceService.addExperience(player, 50);
            }
        }
        if (skillStateChanged) {
            GotCharacters.sendCharacterSelectionState(player);
        }
    }

    private static boolean canAcquirePlayerSettlement(class_3218 world, class_3222 player) {
        return world != null && player != null;
    }

    private static boolean canAcquireConqueredCastle(class_3218 world, class_3222 player, CastleTier tier, @Nullable String targetCastleId) {
        return world != null && player != null && tier != null;
    }

    private static int countOwnedPlayerSettlements(class_3218 world, UUID playerUuid) {
        int ownedCount = 0;
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if (!"player_claim".equals(castle.getDefinitionId()) || !playerUuid.equals(castle.getOwnerPlayerUuid())) continue;
            ++ownedCount;
        }
        return ownedCount;
    }

    private static int countOwnedCastlesByTier(class_3218 world, UUID playerUuid, CastleTier tier, @Nullable String excludedCastleId) {
        int ownedCount = 0;
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if ("player_claim".equals(castle.getDefinitionId()) || excludedCastleId != null && excludedCastleId.equals(castle.getId()) || castle.getTier() != tier || !playerUuid.equals(castle.getOwnerPlayerUuid())) continue;
            ++ownedCount;
        }
        return ownedCount;
    }

    public static void updatePlayerOwnedHouseId(class_3218 world, UUID playerUuid, String houseId) {
        if (world == null || playerUuid == null || houseId == null || houseId.isBlank()) {
            return;
        }
        boolean changed = false;
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if (!playerUuid.equals(castle.getOwnerPlayerUuid()) || houseId.equals(castle.getOwnerHouseId())) continue;
            castle.setOwnerHouseId(houseId);
            changed = true;
        }
        if (changed) {
            GotCastles.getWorldState(world).method_80();
            GotCastles.syncStateToPlayers(world);
            GotCastles.syncCastleMapToPlayers(world);
        }
    }

    public static void updatePlayerOwnedCastlesToKingdom(class_3218 world, UUID playerUuid, String kingdomId, String houseId) {
        if (world == null || playerUuid == null || kingdomId == null || kingdomId.isBlank() || houseId == null || houseId.isBlank()) {
            return;
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        boolean changed = false;
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if (!playerUuid.equals(castle.getOwnerPlayerUuid())) continue;
            boolean castleChanged = false;
            if (!canonicalKingdomId.equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId()))) {
                castle.setOwnerKingdomId(canonicalKingdomId);
                castleChanged = true;
            }
            if (!houseId.equals(GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), canonicalKingdomId, ""))) {
                castle.setOwnerHouseId(houseId);
                castleChanged = true;
            }
            if (!castleChanged) continue;
            GotVillages.synchronizeVillageAfterCastleOwnershipChange(world, castle);
            if (!"player_claim".equals(castle.getDefinitionId())) {
                GotCastles.respawnCastlePopulation(world, castle);
            }
            changed = true;
        }
        if (changed) {
            GotCastles.getWorldState(world).method_80();
        }
    }

    public static void updateDynastyOwnedCastlesForPlayer(class_3218 world, class_3222 player) {
        if (world == null || player == null) {
            return;
        }
        GotCastles.updateDynastyOwnedCastlesForPlayer(world, player.method_5667(), ((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedKingdomId(), GotNpcMarriage.resolveCurrentPlayerHouseId(player));
    }

    public static void updateDynastyOwnedCastlesForPlayer(class_3218 world, UUID playerUuid, String kingdomId, String houseId) {
        if (world == null || playerUuid == null) {
            return;
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        if (canonicalKingdomId.isBlank() || resolvedHouseId.isBlank()) {
            return;
        }
        String religionId = GotCastles.defaultReligionIdForKingdom(canonicalKingdomId);
        boolean changed = false;
        for (CastleInstance castle : GotCastles.getWorldState(world).getAll()) {
            if (!playerUuid.equals(castle.getOwnerPlayerUuid()) || "player_claim".equals(castle.getDefinitionId()) || !GotDynastyNpcs.isDynastyOwnedLordName(world, playerUuid, castle.getLordName())) continue;
            boolean castleChanged = false;
            if (!canonicalKingdomId.equals(GotCastles.canonicalKingdomId(castle.getOwnerKingdomId()))) {
                castle.setOwnerKingdomId(canonicalKingdomId);
                castleChanged = true;
            }
            if (!resolvedHouseId.equals(GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), canonicalKingdomId, ""))) {
                castle.setOwnerHouseId(resolvedHouseId);
                castleChanged = true;
            }
            if (!religionId.isBlank() && !religionId.equals(castle.getOwnerReligionId())) {
                castle.setOwnerReligionId(religionId);
                castleChanged = true;
            }
            if (!castleChanged) continue;
            GotCastles.respawnCastlePopulation(world, castle);
            changed = true;
        }
        if (changed) {
            GotCastles.getWorldState(world).method_80();
            GotCastles.syncStateToPlayers(world);
            GotCastles.syncCastleMapToPlayers(world);
        }
    }

    private static int reinforcementBonusFor(CastleInvasionMode mode, CastleInstance castle) {
        if (mode == null || castle == null) {
            return 0;
        }
        int bonus = switch (mode) {
            default -> throw new MatchException(null, null);
            case CastleInvasionMode.CRUSADE -> 0;
            case CastleInvasionMode.RAID -> {
                if (castle.getTier() == CastleTier.LORDSHIP || castle.getTier() == CastleTier.CAPITAL) {
                    yield 10;
                }
                yield 5;
            }
            case CastleInvasionMode.CONQUER, CastleInvasionMode.CHALLENGE -> castle.getTier() == CastleTier.LORDSHIP || castle.getTier() == CastleTier.CAPITAL ? 10 : 8;
        };
        return castle.getTier() == CastleTier.CAPITAL ? bonus + 6 : bonus;
    }

    private static int civilianCountFor(CastleTier tier) {
        return switch (tier) {
            default -> throw new MatchException(null, null);
            case CastleTier.CAPITAL -> 16;
            case CastleTier.LORDSHIP -> 8;
            case CastleTier.SETTLEMENT -> 4;
        };
    }

    static int guardCountFor(CastleTier tier) {
        return switch (tier) {
            default -> throw new MatchException(null, null);
            case CastleTier.CAPITAL -> 8;
            case CastleTier.LORDSHIP -> 4;
            case CastleTier.SETTLEMENT -> 2;
        };
    }

    private static void setConquerCastleBuffs(class_3218 world, String castleId, boolean enabled) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            return;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castleId.equals(npc.got$getCastle()))) {
            if (enabled) {
                GotCastles.applyConquerBuffs(npc2);
                continue;
            }
            GotCastles.removeConquerBuffs(npc2);
        }
    }

    private static void applyConquerBuffs(AbstractKingdomNpcEntity npc) {
        if (npc == null) {
            return;
        }
        GotCastles.applyCastleNpcModifier(npc.method_5996(class_5134.field_23721), CONQUER_CASTLE_ATTACK_DAMAGE_MODIFIER_ID, 1.0);
        GotCastles.applyCastleNpcModifier(npc.method_5996(class_5134.field_23716), CONQUER_CASTLE_HEALTH_MODIFIER_ID, 2.0);
        npc.method_6033(Math.min((float)((double)npc.method_6032() + 2.0), npc.method_6063()));
    }

    private static void removeConquerBuffs(AbstractKingdomNpcEntity npc) {
        if (npc == null) {
            return;
        }
        GotCastles.removeCastleNpcModifier(npc.method_5996(class_5134.field_23721), CONQUER_CASTLE_ATTACK_DAMAGE_MODIFIER_ID);
        GotCastles.removeCastleNpcModifier(npc.method_5996(class_5134.field_23716), CONQUER_CASTLE_HEALTH_MODIFIER_ID);
        if (npc.method_6032() > npc.method_6063()) {
            npc.method_6033(npc.method_6063());
        }
    }

    private static void applyCastleNpcModifier(@Nullable class_1324 attribute, class_2960 modifierId, double amount) {
        if (attribute == null) {
            return;
        }
        attribute.method_6200(modifierId);
        attribute.method_26837(new class_1322(modifierId, amount, class_1322.class_1323.field_6328));
    }

    private static void removeCastleNpcModifier(@Nullable class_1324 attribute, class_2960 modifierId) {
        if (attribute == null) {
            return;
        }
        attribute.method_6200(modifierId);
    }

    @Nullable
    private static ActiveTitleChallenge findActiveTitleChallengeForLord(AbstractKingdomNpcEntity npc) {
        if (npc == null) {
            return null;
        }
        for (ActiveTitleChallenge challenge : ACTIVE_TITLE_CHALLENGES.values()) {
            if (!npc.method_5667().equals(challenge.lordUuid()) && (npc.getCastleNpcId().isBlank() || !npc.getCastleNpcId().equals(challenge.lordProfileId()))) continue;
            return challenge;
        }
        return null;
    }

    private static boolean isNpcPermadeath(AbstractKingdomNpcEntity entity) {
        return entity != null && entity.method_59922().method_43057() < 0.1f;
    }

    public static void onCastleNpcHurt(class_3218 world, AbstractKingdomNpcEntity victim, class_3222 attacker) {
        if (world == null || victim == null || attacker == null || victim.got$getCastle().isBlank()) {
            return;
        }
        ActiveTitleChallenge activeChallenge = GotCastles.findActiveTitleChallengeForLord(victim);
        if (activeChallenge != null && attacker.method_5667().equals(activeChallenge.playerUuid())) {
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(victim.got$getCastle());
        if (castle == null) {
            if (VillageWorldState.get(world).get(victim.got$getCastle()) != null) {
                GotVillages.onVillageNpcHurt(world, victim, attacker);
            }
            return;
        }
        long expiresAtTick = world.method_8510() + 1200L;
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && castle.getId().equals(npc.got$getCastle()))) {
            if (!npc2.getNpcRole().isGuardDuty() && npc2.getNpcRole() != KingdomNpcRole.LORD) continue;
            npc2.alertAgainst(attacker, expiresAtTick);
        }
        if (victim.getNpcRole() == KingdomNpcRole.CIVILIAN || victim.getNpcRole() == KingdomNpcRole.BLACKSMITH || victim.getNpcRole() == KingdomNpcRole.ARENA_MASTER) {
            victim.alertAgainst(attacker, expiresAtTick);
        }
    }

    public static boolean isWithinCastleClaim(class_3218 world, String castleId, class_2338 pos) {
        if (world == null || castleId == null || castleId.isBlank() || pos == null) {
            return false;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            return GotVillages.isWithinVillageClaim(world, castleId, pos);
        }
        int chunkX = pos.method_10263() >> 4;
        int chunkZ = pos.method_10260() >> 4;
        int centerChunkX = castle.getBannerPos().method_10263() >> 4;
        int centerChunkZ = castle.getBannerPos().method_10260() >> 4;
        int radius = castle.getClaimRadiusChunks();
        return Math.abs(chunkX - centerChunkX) <= radius && Math.abs(chunkZ - centerChunkZ) <= radius;
    }

    private static class_2338 alignPlanOrigin(CastlePlacementPlan plan, class_3499 template) {
        return GotCastles.alignPlanOrigin(plan.origin(), plan.floorY(), plan.footprintBlocks(), template);
    }

    private static class_2338 alignPlanOrigin(class_2338 planOrigin, int floorY, int footprintBlocks, class_3499 template) {
        class_2382 size = template.method_15160();
        int originX = planOrigin.method_10263() + Math.max(0, (footprintBlocks - size.method_10263()) / 2);
        int originZ = planOrigin.method_10260() + Math.max(0, (footprintBlocks - size.method_10260()) / 2);
        return new class_2338(originX, floorY, originZ);
    }

    private static Optional<DynamicStoneCastleLayout> placeDynamicStoneCastle(class_3218 world, String castleId, CastlePlacementPlan plan, DynamicStoneCastleTierConfig config) {
        return GotCastles.placeDynamicStoneCastle(world, castleId, plan.origin(), plan.floorY(), plan.footprintBlocks(), plan.blendRadius(), config);
    }

    private static Optional<DynamicStoneCastleLayout> placeDynamicStoneCastle(class_3218 world, String castleId, class_2338 planOrigin, int floorY, int footprintBlocks, int blendRadius, DynamicStoneCastleTierConfig config) {
        class_3499 claimTemplate;
        class_3485 templateManager = world.method_14183();
        Optional<CastleKeepTemplate> keepTemplate = GotCastles.loadCastleKeepTemplate(world, templateManager, castleId);
        class_3499 class_34992 = claimTemplate = keepTemplate.isPresent() ? null : (class_3499)templateManager.method_15094(STONE_CASTLE_CLAIM_STRUCTURE_ID).orElse(null);
        if (keepTemplate.isEmpty() && claimTemplate == null) {
            GotMod.LOGGER.warn("Missing dynamic stone castle claim piece for {}", (Object)castleId);
            return Optional.empty();
        }
        CastlePlacementPlan placementPlan = new CastlePlacementPlan(castleId, config.tier(), null, planOrigin, floorY, floorY, floorY, Math.max(1, GotCastles.ceilDiv(footprintBlocks, 16)), footprintBlocks, blendRadius);
        DynamicCastleWorldgenFootprint worldgenFootprint = GotCastles.resolveDynamicWorldgenFootprint(world, castleId, placementPlan, config.tier()).orElse(null);
        if (worldgenFootprint == null) {
            return Optional.empty();
        }
        class_2382 footprintSize = worldgenFootprint.size();
        class_2338 footprintOrigin = worldgenFootprint.origin();
        int floorBlockY = floorY + 1;
        int edgeBlendWidth = DynamicCastleTerrainApplier.terrainMergeRadius(blendRadius);
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(castleId);
        String kingdomId = metadata != null ? metadata.kingdomId() : "";
        GotCastles.prepareCastlePad(world, footprintOrigin, footprintSize, floorY, 0, GotCastles.usesSouthOnlyCastleGeneration(castleId), planOrigin, kingdomId);
        GotCastles.flattenDynamicCastleClaimSurface(world, GotCastles.expandDynamicFlattenOrigin(footprintOrigin), GotCastles.expandDynamicFlattenSize(footprintSize), floorY, planOrigin, kingdomId);
        GotCastles.mergeCastlePlatformBoundary(world, footprintOrigin, footprintSize, floorY, edgeBlendWidth, GotCastles.usesSouthOnlyCastleGeneration(castleId), planOrigin, kingdomId);
        int clearanceHeight = keepTemplate.map(template -> Math.max(28, template.size().method_10264() + 4)).orElse(28);
        GotCastles.clearBuildVolume(world, footprintOrigin, footprintSize, floorBlockY, clearanceHeight);
        Optional<DynamicCastleCoreWorldgenPlan> corePlan = GotCastles.resolveDynamicCoreWorldgenPlan(world, castleId, placementPlan, config.tier());
        if (corePlan.isEmpty()) {
            return Optional.empty();
        }
        Optional<class_2338> bannerPos = DynamicCastleCoreApplier.findExistingBanner(world, corePlan.get());
        if (bannerPos.isEmpty()) {
            bannerPos = DynamicCastleCoreApplier.applyFull(world, corePlan.get());
        }
        return bannerPos.map(pos -> new DynamicStoneCastleLayout(footprintOrigin, footprintSize, (class_2338)pos));
    }

    private static Optional<CastleKeepTemplate> loadCastleKeepTemplate(class_3218 world, class_3485 templateManager, String castleId) {
        String keepFolder = GotCastles.normalizeCastleKeepFolder(castleId);
        if (keepFolder.isBlank()) {
            keepFolder = GotCastles.resolveDefaultCastleKeepFolder(castleId);
        }
        Optional<CastleKeepTemplate> castleKeep = GotCastles.loadCastleKeepTemplateFolder(world, templateManager, castleId, keepFolder);
        String fallbackKeepFolder = GotCastles.resolveDefaultCastleKeepFolder(castleId);
        if (castleKeep.isPresent() || fallbackKeepFolder.equals(keepFolder)) {
            return castleKeep;
        }
        Optional<CastleKeepTemplate> defaultKeep = GotCastles.loadCastleKeepTemplateFolder(world, templateManager, castleId, fallbackKeepFolder);
        if (defaultKeep.isPresent() && LOGGED_DEFAULT_CASTLE_KEEP_CASTLES.add(castleId)) {
            GotMod.LOGGER.info("Using default castle keep {} for {}", (Object)fallbackKeepFolder, (Object)castleId);
        }
        return defaultKeep;
    }

    private static String resolveDefaultCastleKeepFolder(String castleId) {
        CastlePresetMetadata metadata;
        CastlePresetMetadata castlePresetMetadata = metadata = castleId == null || castleId.isBlank() ? null : presetMetadataByCastleId.get(castleId);
        if (metadata != null) {
            String kingdomId = GotCastles.canonicalKingdomId(metadata.kingdomId());
            if (GotKingdoms.THE_NORTH.getId().toString().equals(kingdomId)) {
                return NORTH_DEFAULT_CASTLE_KEEP_FOLDER;
            }
            if (GotKingdoms.DORNE.getId().toString().equals(kingdomId)) {
                return DORNE_DEFAULT_CASTLE_KEEP_FOLDER;
            }
            if (WESTERLANDS_KINGDOM_ID.equals(kingdomId)) {
                return WESTERLANDS_DEFAULT_CASTLE_KEEP_FOLDER;
            }
        }
        return DEFAULT_CASTLE_KEEP_FOLDER;
    }

    private static Optional<CastleKeepTemplate> loadCastleKeepTemplateFolder(class_3218 world, class_3485 templateManager, String castleId, String keepFolder) {
        return CASTLE_KEEP_TEMPLATE_CACHE.computeIfAbsent(keepFolder, key -> GotCastles.loadCastleKeepTemplateFolderUncached(world, templateManager, castleId, key));
    }

    private static Optional<CastleKeepTemplate> loadCastleKeepTemplateFolderUncached(class_3218 world, class_3485 templateManager, String castleId, String keepFolder) {
        String resourceRoot = "structure/castle/keep/" + keepFolder;
        CastleKeepLayout layout = GotCastles.castleKeepLayout(keepFolder);
        Map resources = world.method_8503().method_34864().method_14488(resourceRoot, path -> path.method_12832().endsWith(".nbt"));
        if (resources.isEmpty()) {
            return Optional.empty();
        }
        HashMap<Integer, CastleKeepPiece> piecesByPart = new HashMap<Integer, CastleKeepPiece>();
        for (class_2960 resourceId : resources.keySet()) {
            GotCastles.parseCastleKeepPiece(templateManager, resourceRoot, resourceId).ifPresent(piece -> {
                CastleKeepPiece existing = (CastleKeepPiece)piecesByPart.get(piece.partIndex());
                if (existing == null || GotCastles.isPreferredCastleKeepPartName(keepFolder, piece.structureId(), existing.structureId())) {
                    piecesByPart.put(piece.partIndex(), (CastleKeepPiece)piece);
                }
            });
        }
        List<CastleKeepPiece> pieces = GotCastles.arrangeCastleKeepPieces(keepFolder, layout, piecesByPart);
        if (pieces.isEmpty()) {
            GotMod.LOGGER.warn("Castle keep folder {} exists for {} but no keep part pieces could be loaded", (Object)resourceRoot, (Object)castleId);
            return Optional.empty();
        }
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxZ = Integer.MIN_VALUE;
        for (CastleKeepPiece piece2 : pieces) {
            class_3341 bounds = GotCastles.castleKeepPieceLocalBounds(piece2, layout.rotation());
            minX = Math.min(minX, bounds.method_35415());
            minY = Math.min(minY, bounds.method_35416());
            minZ = Math.min(minZ, bounds.method_35417());
            maxX = Math.max(maxX, bounds.method_35418() + 1);
            maxY = Math.max(maxY, bounds.method_35419() + 1);
            maxZ = Math.max(maxZ, bounds.method_35420() + 1);
        }
        class_2382 minOffset = new class_2382(minX, minY, minZ);
        class_2382 size = new class_2382(Math.max(1, maxX - minX), Math.max(1, maxY - minY), Math.max(1, maxZ - minZ));
        return Optional.of(new CastleKeepTemplate(keepFolder, pieces, minOffset, size, layout.rotation()));
    }

    private static Optional<CastleKeepPiece> parseCastleKeepPiece(class_3485 templateManager, String resourceRoot, class_2960 resourceId) {
        String path = resourceId.method_12832();
        if (!path.startsWith(resourceRoot + "/") || !path.endsWith(".nbt")) {
            return Optional.empty();
        }
        String fileName = path.substring(path.lastIndexOf(47) + 1, path.length() - ".nbt".length());
        Optional<Integer> partIndex = GotCastles.parseCastleKeepPartIndex(fileName);
        if (partIndex.isEmpty()) {
            return Optional.empty();
        }
        String templatePath = path.substring("structure/".length(), path.length() - ".nbt".length());
        class_2960 templateId = class_2960.method_60655((String)resourceId.method_12836(), (String)templatePath);
        class_3499 template = templateManager.method_15094(templateId).orElse(null);
        if (template == null) {
            GotMod.LOGGER.warn("Missing castle keep chunk template {}", (Object)templateId);
            return Optional.empty();
        }
        return Optional.of(new CastleKeepPiece(partIndex.get(), templateId, template, class_2382.field_11176));
    }

    private static Optional<Integer> parseCastleKeepPartIndex(String fileName) {
        int separator = fileName.lastIndexOf(95);
        if (separator < 0 || separator >= fileName.length() - 1) {
            return Optional.empty();
        }
        try {
            int partIndex = Integer.parseInt(fileName.substring(separator + 1));
            return partIndex >= 1 && partIndex <= 4 ? Optional.of(partIndex) : Optional.empty();
        }
        catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private static boolean isPreferredCastleKeepPartName(String keepFolder, class_2960 candidate, class_2960 existing) {
        return GotCastles.castleKeepPartPriority(keepFolder, candidate) > GotCastles.castleKeepPartPriority(keepFolder, existing);
    }

    private static int castleKeepPartPriority(String keepFolder, class_2960 structureId) {
        String fileName = structureId.method_12832().substring(structureId.method_12832().lastIndexOf(47) + 1);
        if (fileName.startsWith("keep_" + keepFolder + "_")) {
            return 3;
        }
        if (fileName.startsWith("keep_oldcastle_")) {
            return 2;
        }
        return 1;
    }

    private static List<CastleKeepPiece> arrangeCastleKeepPieces(String keepFolder, CastleKeepLayout layout, Map<Integer, CastleKeepPiece> piecesByPart) {
        CastleKeepPiece part1 = piecesByPart.get(1);
        CastleKeepPiece part2 = piecesByPart.get(2);
        CastleKeepPiece part3 = piecesByPart.get(3);
        CastleKeepPiece part4 = piecesByPart.get(4);
        CastleKeepArrangement arrangement = layout.arrangement();
        if (part1 != null && part2 == null && part3 == null && part4 == null) {
            return List.of(part1.withOffset(class_2382.field_11176));
        }
        if (arrangement == CastleKeepArrangement.LEFT_RIGHT_12) {
            if (part1 == null || part2 == null) {
                GotMod.LOGGER.warn("Castle keep {} needs parts 1 and 2 for left/right arrangement but found parts {}", (Object)keepFolder, piecesByPart.keySet());
                return List.of();
            }
            int leftWidth = GotCastles.rotatedTemplateSize(part1, layout.rotation()).method_10263();
            ArrayList<CastleKeepPiece> arranged = new ArrayList<CastleKeepPiece>(2);
            arranged.add(part1.withOffset(class_2382.field_11176));
            arranged.add(part2.withOffset(new class_2382(leftWidth, 0, 0)));
            return arranged;
        }
        if (part1 == null || part2 == null || part3 == null || part4 == null) {
            GotMod.LOGGER.warn("Castle keep {} needs four numbered pieces but found parts {}", (Object)keepFolder, piecesByPart.keySet());
            return List.of();
        }
        CastleKeepPiece topLeft = switch (arrangement) {
            default -> throw new MatchException(null, null);
            case CastleKeepArrangement.GRID_13_24 -> part1;
            case CastleKeepArrangement.GRID_34_12 -> part3;
            case CastleKeepArrangement.LEFT_RIGHT_12 -> throw new IllegalStateException("LEFT_RIGHT_12 should have returned earlier");
        };
        CastleKeepPiece topRight = switch (arrangement) {
            default -> throw new MatchException(null, null);
            case CastleKeepArrangement.GRID_13_24 -> part3;
            case CastleKeepArrangement.GRID_34_12 -> part4;
            case CastleKeepArrangement.LEFT_RIGHT_12 -> throw new IllegalStateException("LEFT_RIGHT_12 should have returned earlier");
        };
        CastleKeepPiece bottomLeft = switch (arrangement) {
            default -> throw new MatchException(null, null);
            case CastleKeepArrangement.GRID_13_24 -> part2;
            case CastleKeepArrangement.GRID_34_12 -> part1;
            case CastleKeepArrangement.LEFT_RIGHT_12 -> throw new IllegalStateException("LEFT_RIGHT_12 should have returned earlier");
        };
        CastleKeepPiece bottomRight = switch (arrangement) {
            default -> throw new MatchException(null, null);
            case CastleKeepArrangement.GRID_13_24 -> part4;
            case CastleKeepArrangement.GRID_34_12 -> part2;
            case CastleKeepArrangement.LEFT_RIGHT_12 -> throw new IllegalStateException("LEFT_RIGHT_12 should have returned earlier");
        };
        int leftColumnWidth = Math.max(GotCastles.rotatedTemplateSize(topLeft, layout.rotation()).method_10263(), GotCastles.rotatedTemplateSize(bottomLeft, layout.rotation()).method_10263());
        int topRowDepth = Math.max(GotCastles.rotatedTemplateSize(topLeft, layout.rotation()).method_10260(), GotCastles.rotatedTemplateSize(topRight, layout.rotation()).method_10260());
        ArrayList<CastleKeepPiece> arranged = new ArrayList<CastleKeepPiece>(4);
        arranged.add(topLeft.withOffset(class_2382.field_11176));
        arranged.add(bottomLeft.withOffset(new class_2382(0, 0, topRowDepth)));
        arranged.add(topRight.withOffset(new class_2382(leftColumnWidth, 0, 0)));
        arranged.add(bottomRight.withOffset(new class_2382(leftColumnWidth, 0, topRowDepth)));
        return arranged;
    }

    private static class_2382 rotatedTemplateSize(CastleKeepPiece piece, class_2470 rotation) {
        class_3341 bounds = GotCastles.castleKeepPieceLocalBounds(piece.withOffset(class_2382.field_11176), rotation);
        return new class_2382(bounds.method_35414(), bounds.method_14660(), bounds.method_14663());
    }

    private static class_3341 castleKeepPieceLocalBounds(CastleKeepPiece piece, class_2470 rotation) {
        class_2338 origin = new class_2338(piece.offset().method_10263(), piece.offset().method_10264(), piece.offset().method_10260());
        return piece.template().method_27267(origin, rotation, class_2338.field_10980, class_2415.field_11302);
    }

    private static String normalizeCastleKeepFolder(String castleId) {
        String normalized;
        if (castleId == null) {
            return "";
        }
        return switch (normalized = castleId.trim().toLowerCase(Locale.ROOT).replace(' ', '_').replace('-', '_')) {
            case "craster_s_keep" -> "crasters_keep";
            default -> normalized;
        };
    }

    private static class_2382 expandDynamicFootprintForKeep(class_2382 footprintSize, CastleKeepTemplate keepTemplate) {
        class_2382 keepSize = keepTemplate.size();
        int x = Math.max(footprintSize.method_10263(), keepSize.method_10263());
        int y = Math.max(footprintSize.method_10264(), keepSize.method_10264());
        int z = Math.max(footprintSize.method_10260(), keepSize.method_10260());
        return new class_2382(x, y, z);
    }

    private static CastleKeepLayout castleKeepLayout(String keepFolder) {
        return switch (keepFolder) {
            case WESTERLANDS_DEFAULT_CASTLE_KEEP_FOLDER -> CastleKeepLayout.of(CastleKeepArrangement.GRID_34_12);
            case NORTH_DEFAULT_CASTLE_KEEP_FOLDER -> CastleKeepLayout.of(CastleKeepArrangement.LEFT_RIGHT_12);
            default -> CastleKeepLayout.of(CastleKeepArrangement.GRID_13_24);
        };
    }

    private static void placeCastleKeep(class_3218 world, CastleKeepTemplate keepTemplate, class_2338 keepOrigin) {
        if (world == null || keepTemplate == null || keepOrigin == null) {
            return;
        }
        class_2470 keepRotation = keepTemplate.rotation();
        class_3492 placement = new class_3492().method_15133(false).method_15131(false).method_27264(false).method_15123(keepRotation).method_16184((class_3491)class_3793.field_16721);
        for (CastleKeepPiece piece : keepTemplate.pieces()) {
            class_2338 pieceOrigin = keepOrigin.method_10081(piece.offset());
            piece.template().method_15172((class_5425)world, pieceOrigin, pieceOrigin, placement, world.field_9229, 50);
        }
    }

    private static boolean placeHorizontalStoneSide(class_3218 world, class_3499 cornerTemplate, class_3499 wallTemplate, class_3499 gateTemplate, int minX, int z, int y, boolean southSide, boolean gated, int gatedSideWallSegmentsPerSide, int solidSideWallSegments) {
        int index;
        class_2470 leftCornerRotation = southSide ? class_2470.field_11465 : class_2470.field_11467;
        class_2470 rightCornerRotation = southSide ? class_2470.field_11464 : class_2470.field_11463;
        class_2470 wallRotation = southSide ? class_2470.field_11464 : class_2470.field_11467;
        class_2470 gateRotation = southSide ? class_2470.field_11464 : class_2470.field_11467;
        class_2382 cornerSize = GotCastles.rotatedTemplateSize(cornerTemplate, leftCornerRotation);
        class_2382 wallSize = GotCastles.rotatedTemplateSize(wallTemplate, wallRotation);
        class_2382 gateSize = GotCastles.rotatedTemplateSize(gateTemplate, gateRotation);
        int offsetX = minX;
        if (!GotCastles.placeStructureAtMin(world, cornerTemplate, leftCornerRotation, offsetX, y, z)) {
            return false;
        }
        offsetX += cornerSize.method_10263();
        int straightWallZ = z + (southSide ? cornerSize.method_10260() - wallSize.method_10260() : 0);
        int straightGateZ = z + (southSide ? cornerSize.method_10260() - gateSize.method_10260() : 0);
        int wallSegments = gated ? gatedSideWallSegmentsPerSide : solidSideWallSegments;
        for (index = 0; index < wallSegments; ++index) {
            if (!GotCastles.placeStructureAtMin(world, wallTemplate, wallRotation, offsetX, y, straightWallZ)) {
                return false;
            }
            offsetX += wallSize.method_10263();
        }
        if (gated) {
            if (!GotCastles.placeStructureAtMin(world, gateTemplate, gateRotation, offsetX, y, straightGateZ)) {
                return false;
            }
            offsetX += gateSize.method_10263();
            for (index = 0; index < gatedSideWallSegmentsPerSide; ++index) {
                if (!GotCastles.placeStructureAtMin(world, wallTemplate, wallRotation, offsetX, y, straightWallZ)) {
                    return false;
                }
                offsetX += wallSize.method_10263();
            }
        }
        return GotCastles.placeStructureAtMin(world, cornerTemplate, rightCornerRotation, offsetX, y, z);
    }

    private static boolean placeVerticalStoneSide(class_3218 world, class_3499 cornerTemplate, class_3499 wallTemplate, class_3499 gateTemplate, int x, int minZ, int y, boolean eastSide, boolean gated, int gatedSideWallSegmentsPerSide, int solidSideWallSegments) {
        int index;
        class_2470 topCornerRotation = eastSide ? class_2470.field_11463 : class_2470.field_11467;
        class_2470 bottomCornerRotation = eastSide ? class_2470.field_11464 : class_2470.field_11465;
        class_2470 wallRotation = eastSide ? class_2470.field_11463 : class_2470.field_11465;
        class_2470 gateRotation = eastSide ? class_2470.field_11463 : class_2470.field_11465;
        class_2382 cornerSize = GotCastles.rotatedTemplateSize(cornerTemplate, topCornerRotation);
        class_2382 wallSize = GotCastles.rotatedTemplateSize(wallTemplate, wallRotation);
        class_2382 gateSize = GotCastles.rotatedTemplateSize(gateTemplate, gateRotation);
        int offsetZ = minZ;
        if (!GotCastles.placeStructureAtMin(world, cornerTemplate, topCornerRotation, x, y, offsetZ)) {
            return false;
        }
        offsetZ += cornerSize.method_10260();
        int straightWallX = x + (eastSide ? cornerSize.method_10263() - wallSize.method_10263() : 0);
        int straightGateX = x + (eastSide ? cornerSize.method_10263() - gateSize.method_10263() : 0);
        int wallSegments = gated ? gatedSideWallSegmentsPerSide : solidSideWallSegments;
        for (index = 0; index < wallSegments; ++index) {
            if (!GotCastles.placeStructureAtMin(world, wallTemplate, wallRotation, straightWallX, y, offsetZ)) {
                return false;
            }
            offsetZ += wallSize.method_10260();
        }
        if (gated) {
            if (!GotCastles.placeStructureAtMin(world, gateTemplate, gateRotation, straightGateX, y, offsetZ)) {
                return false;
            }
            offsetZ += gateSize.method_10260();
            for (index = 0; index < gatedSideWallSegmentsPerSide; ++index) {
                if (!GotCastles.placeStructureAtMin(world, wallTemplate, wallRotation, straightWallX, y, offsetZ)) {
                    return false;
                }
                offsetZ += wallSize.method_10260();
            }
        }
        return GotCastles.placeStructureAtMin(world, cornerTemplate, bottomCornerRotation, x, y, offsetZ);
    }

    private static List<PlacedCastleStructure> spawnClaimStructures(class_3218 world, String castleId, CastleTier tier, String kingdomId, Set<CastleSubtype> subtypes, class_2338 bannerPos, class_2338 castleOrigin, class_2382 castleSize) {
        CastleCityTierPlan tierPlan = CastleCityTierRegistry.forTier(tier);
        List<class_1923> candidateChunks = GotCastles.collectClaimStructureChunks(bannerPos, castleOrigin, castleSize, tierPlan.claimRadiusChunks());
        if (candidateChunks.isEmpty()) {
            return List.of();
        }
        class_5819 random = class_5819.method_43049((long)(bannerPos.method_10063() ^ (long)castleId.hashCode()));
        Collections.shuffle(candidateChunks, new Random(random.method_43055()));
        ClaimStructureSelection selection = GotCastles.selectClaimStructurePlacements(kingdomId, tier, candidateChunks.size(), random);
        if (selection == null) {
            return List.of();
        }
        class_3485 templateManager = world.method_14183();
        ArrayList<PlacedCastleStructure> placedStructures = new ArrayList<PlacedCastleStructure>(GotCastles.placeClaimStructuresInChunks(world, templateManager, candidateChunks, selection.orderedPlacements(), kingdomId));
        GotCastles.placeDedicatedBarnStructure(world, templateManager, castleId, kingdomId, bannerPos, castleOrigin, castleSize, tierPlan, castleOrigin.method_10264()).ifPresent(placedStructures::add);
        if (subtypes.contains((Object)CastleSubtype.HARBOUR)) {
            GotCastles.placeDedicatedHarbourStructure(world, templateManager, castleId, kingdomId, bannerPos, GotCastles.collectClaimHarbourStructureChunks(bannerPos, castleOrigin, castleSize, tierPlan.claimRadiusChunks()), GotCastles.chunkAreaFor(castleOrigin, castleSize), tierPlan.claimRadiusChunks(), castleOrigin.method_10264()).ifPresent(placedStructures::add);
        }
        return List.copyOf(placedStructures);
    }

    private static List<PlacedCastleStructure> spawnDynamicStoneClaimStructures(class_3218 world, String castleId, CastleTier tier, String kingdomId, Set<CastleSubtype> subtypes, class_2338 bannerPos, class_2338 castleOrigin, class_2382 castleSize) {
        class_3485 templateManager;
        class_3341 coreReservedFootprint;
        CastleCityTierPlan tierPlan = CastleCityTierRegistry.forTier(tier);
        class_5819 random = class_5819.method_43049((long)((bannerPos.method_10063() ^ (long)castleId.hashCode()) + 17L + (long)tier.ordinal() * 31L));
        boolean northSouthGates = ((castleId == null ? 0 : castleId.hashCode()) >>> 1 & 1) == 0;
        List<class_1923> buildingChunks = GotCastles.selectDynamicCastleBuildingChunks(bannerPos, tierPlan, northSouthGates, coreReservedFootprint = GotCastles.dynamicStoneCoreReservedFootprint(world, templateManager = world.method_14183(), castleId, bannerPos, castleOrigin, castleSize), random, GotCastles.isOriginalNightsWatchCastle(kingdomId) ? 1 : 0);
        ClaimStructureSelection selection = GotCastles.selectClaimStructurePlacements(kingdomId, tier, buildingChunks.size(), random);
        if (selection == null) {
            return List.of();
        }
        ArrayList<class_3341> reservedFootprints = new ArrayList<class_3341>(GotCastles.dynamicStoneReservedFootprints(coreReservedFootprint));
        ArrayList<class_1923> availableChunks = new ArrayList<class_1923>(buildingChunks);
        ArrayList<PlacedCastleStructure> placedStructures = new ArrayList<PlacedCastleStructure>();
        for (CastleStructureResidentPlan structurePlan : selection.orderedPlacements()) {
            DynamicStructurePlacement placement = GotCastles.placeStructureInAvailableChunkPlot(world, templateManager, availableChunks, reservedFootprints, structurePlan.structurePath(), random, kingdomId);
            if (placement == null) continue;
            reservedFootprints.add(placement.placement().footprint());
            placedStructures.add(new PlacedCastleStructure(structurePlan, placement.placement()));
        }
        List<class_1923> wallStructureChunks = !placedStructures.isEmpty() ? GotCastles.collectDynamicWallStructureChunksFromPlacements(placedStructures) : buildingChunks;
        List<class_1923> anchorChunks = GotCastles.collectDynamicCastleAnchorChunks(coreReservedFootprint, wallStructureChunks);
        DynamicStoneWallPlan wallPlan = GotCastles.createDynamicStoneWallPlan(world, bannerPos, tierPlan, anchorChunks, northSouthGates, GotCastles.isOriginalNightsWatchCastle(kingdomId) ? 1 : 0);
        GotCastles.placeDynamicCastleRoads(world, bannerPos, anchorChunks, wallPlan);
        if (GotCastles.shouldGenerateDynamicCastleWalls(kingdomId) && !GotCastles.placeDynamicStoneWallPlan(world, templateManager, wallPlan, castleOrigin.method_10264() + 1, kingdomId, null)) {
            GotMod.LOGGER.warn("Failed to place dynamic stone castle wall outline for {}", (Object)castleId);
        }
        GotCastles.placeDedicatedBarnStructure(world, templateManager, castleId, bannerPos, kingdomId, tierPlan, wallPlan, castleOrigin.method_10264()).ifPresent(placedStructures::add);
        if (subtypes.contains((Object)CastleSubtype.HARBOUR)) {
            List<class_1923> harbourChunks = GotCastles.collectDynamicHarbourStructureChunks(bannerPos, wallPlan, tierPlan.claimRadiusChunks());
            Collections.shuffle(harbourChunks, new Random(random.method_43055()));
            GotCastles.placeDedicatedHarbourStructure(world, templateManager, castleId, kingdomId, bannerPos, harbourChunks, new ChunkSetArea(wallPlan.interiorChunkKeys()), tierPlan.claimRadiusChunks(), castleOrigin.method_10264()).ifPresent(placedStructures::add);
        }
        ArrayList<class_3341> natureReservedFootprints = new ArrayList<class_3341>(GotCastles.dynamicStoneReservedFootprints(coreReservedFootprint));
        for (PlacedCastleStructure placedStructure : placedStructures) {
            natureReservedFootprints.add(placedStructure.placement().footprint());
        }
        class_5819 natureRandom = class_5819.method_43049((long)((bannerPos.method_10063() ^ (long)castleId.hashCode()) + 91L + (long)tier.ordinal() * 43L));
        GotCastles.placeDynamicCastleNatureFillers(world, templateManager, castleId, tier, wallPlan, anchorChunks, natureReservedFootprints, natureRandom, castleOrigin.method_10264());
        return List.copyOf(placedStructures);
    }

    @Nullable
    private static PlacedCastleStructure placeNextPendingClaimStructure(class_3218 world, PendingCastleGeneration pendingGeneration, List<CastleStructureResidentPlan> placements, int claimRadiusChunks) {
        List<class_1923> candidateChunks = GotCastles.cachedPendingClaimStructureChunks(pendingGeneration, claimRadiusChunks);
        int index = pendingGeneration.nextStructureIndex();
        if (index < 0 || index >= placements.size()) {
            return null;
        }
        HashSet<Long> usedChunkKeys = new HashSet<Long>();
        for (PendingCastleGeneration.PlacedStructureState placedStructure : pendingGeneration.placedStructures()) {
            usedChunkKeys.add(new class_1923(placedStructure.origin()).method_8324());
        }
        for (class_1923 candidateChunk : candidateChunks) {
            StructurePlacementResult placement;
            if (usedChunkKeys.contains(candidateChunk.method_8324()) || (placement = GotCastles.placeClaimStructure(world, world.method_14183(), candidateChunk, placements.get(index).structurePath(), pendingGeneration.kingdomId())) == null) continue;
            return new PlacedCastleStructure(placements.get(index), placement);
        }
        return null;
    }

    private static List<class_1923> cachedPendingClaimStructureChunks(PendingCastleGeneration pendingGeneration, int claimRadiusChunks) {
        if (!pendingGeneration.structureCandidateChunks().isEmpty()) {
            return pendingGeneration.structureCandidateChunks().stream().map(chunk -> new class_1923(chunk.x(), chunk.z())).toList();
        }
        List<class_1923> candidateChunks = GotCastles.collectClaimStructureChunks(pendingGeneration.bannerPos(), pendingGeneration.structureOrigin(), pendingGeneration.structureSize(), claimRadiusChunks);
        class_5819 random = class_5819.method_43049((long)(pendingGeneration.bannerPos().method_10063() ^ (long)pendingGeneration.castleId().hashCode()));
        Collections.shuffle(candidateChunks, new Random(random.method_43055()));
        pendingGeneration.setStructureCandidateChunks(candidateChunks.stream().map(chunk -> new PendingCastleGeneration.ChunkPosState(chunk.field_9181, chunk.field_9180)).toList());
        return candidateChunks;
    }

    @Nullable
    private static PlacedCastleStructure placeNextPendingDynamicStructure(class_3218 world, PendingCastleGeneration pendingGeneration, List<CastleStructureResidentPlan> placements) {
        class_5819 random = class_5819.method_43049((long)((pendingGeneration.bannerPos().method_10063() ^ (long)pendingGeneration.castleId().hashCode()) + 17L + (long)pendingGeneration.tier().ordinal() * 31L));
        CastleCityTierPlan tierPlan = CastleCityTierRegistry.forTier(pendingGeneration.tier());
        class_3341 coreReservedFootprint = GotCastles.dynamicStoneCoreReservedFootprint(world, world.method_14183(), pendingGeneration.castleId(), pendingGeneration.bannerPos(), pendingGeneration.structureOrigin(), pendingGeneration.structureSize());
        List<class_1923> buildingChunks = GotCastles.cachedPendingDynamicBuildingChunks(world, pendingGeneration, tierPlan);
        ArrayList<class_1923> availableChunks = new ArrayList<class_1923>(buildingChunks);
        ArrayList<class_3341> reservedFootprints = new ArrayList<class_3341>(GotCastles.dynamicStoneReservedFootprints(coreReservedFootprint));
        for (PendingCastleGeneration.PlacedStructureState placedStructure : pendingGeneration.placedStructures()) {
            reservedFootprints.add(placedStructure.footprint());
        }
        int index = pendingGeneration.nextStructureIndex();
        if (index < 0 || index >= placements.size()) {
            return null;
        }
        DynamicStructurePlacement placement = GotCastles.placeStructureInAvailableChunkPlot(world, world.method_14183(), availableChunks, reservedFootprints, placements.get(index).structurePath(), random, pendingGeneration.kingdomId());
        return placement == null ? null : new PlacedCastleStructure(placements.get(index), placement.placement());
    }

    private static List<class_1923> selectDynamicCastleBuildingChunks(class_2338 bannerPos, CastleCityTierPlan tierPlan, boolean northSouthGates, class_3341 coreReservedFootprint, class_5819 random) {
        return GotCastles.selectDynamicCastleBuildingChunks(bannerPos, tierPlan, northSouthGates, coreReservedFootprint, random, 0);
    }

    private static List<class_1923> selectDynamicCastleBuildingChunks(class_2338 bannerPos, CastleCityTierPlan tierPlan, boolean northSouthGates, class_3341 coreReservedFootprint, class_5819 random, int maxOffsetReductionChunks) {
        return CastleDynamicWallPlanningHelper.selectBuildingChunks(bannerPos, tierPlan, northSouthGates, coreReservedFootprint, random, maxOffsetReductionChunks);
    }

    @Nullable
    private static DynamicStructurePlacement placeStructureInAvailableChunkPlot(class_3218 world, class_3485 templateManager, List<class_1923> chunks, List<class_3341> reservedFootprints, String structurePath, class_5819 random, @Nullable String kingdomId) {
        class_2960 structureId = class_2960.method_60655((String)"agob", (String)structurePath);
        class_3499 template = templateManager.method_15094(structureId).orElse(null);
        if (template == null) {
            GotMod.LOGGER.warn("Missing claim structure template {}", (Object)structureId);
            return null;
        }
        for (int index = 0; index < chunks.size(); ++index) {
            class_1923 chunk = chunks.get(index);
            StructurePlacementResult placedFootprint = GotCastles.placeStructureInPlot(world, structureId, template, GotCastles.dynamicCastleChunkPlot(chunk, template), reservedFootprints, random, kingdomId);
            if (placedFootprint == null) continue;
            chunks.remove(index);
            return new DynamicStructurePlacement(chunk, placedFootprint);
        }
        return null;
    }

    private static StructurePlot dynamicCastleChunkPlot(class_1923 chunk, class_3499 template) {
        int plotSize = Math.max(24, GotCastles.dynamicCastlePlotSize(template));
        int centerX = chunk.method_8326() + 8;
        int centerZ = chunk.method_8328() + 8;
        return new StructurePlot(centerX - plotSize / 2, centerZ - plotSize / 2, plotSize, plotSize);
    }

    private static int dynamicCastlePlotSize(class_3499 template) {
        int requiredSize = 0;
        for (class_2470 rotation : List.of(class_2470.field_11467, class_2470.field_11463, class_2470.field_11464, class_2470.field_11465)) {
            class_3341 bounds = template.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
            requiredSize = Math.max(requiredSize, Math.max(bounds.method_35414(), bounds.method_14663()));
        }
        if (requiredSize <= 24) {
            return 24;
        }
        int paddedSize = requiredSize + 6;
        return Math.max(24, class_3532.method_15386((float)((float)paddedSize / 8.0f)) * 8);
    }

    private static List<class_1923> collectDynamicWallStructureChunksFromPlacements(List<PlacedCastleStructure> placedStructures) {
        LinkedHashSet<Long> chunkKeys = new LinkedHashSet<Long>();
        for (PlacedCastleStructure placedStructure : placedStructures) {
            if (GotCastles.isOutskirtsRole(placedStructure.plan().residentRole())) continue;
            GotCastles.addFootprintChunks(chunkKeys, placedStructure.placement().footprint());
        }
        return chunkKeys.stream().map(chunkKey -> new class_1923(class_1923.method_8325((long)chunkKey), class_1923.method_8332((long)chunkKey))).toList();
    }

    private static List<class_1923> collectDynamicWallStructureChunksFromPending(PendingCastleGeneration pendingGeneration) {
        LinkedHashSet<Long> chunkKeys = new LinkedHashSet<Long>();
        for (PendingCastleGeneration.PlacedStructureState placedStructure : pendingGeneration.placedStructures()) {
            if (GotCastles.isOutskirtsRoleId(placedStructure.residentRoleId())) continue;
            GotCastles.addFootprintChunks(chunkKeys, placedStructure.footprint());
        }
        return chunkKeys.stream().map(chunkKey -> new class_1923(class_1923.method_8325((long)chunkKey), class_1923.method_8332((long)chunkKey))).toList();
    }

    private static void addFootprintChunks(Set<Long> chunkKeys, class_3341 footprint) {
        int minChunkX = footprint.method_35415() >> 4;
        int maxChunkX = footprint.method_35418() >> 4;
        int minChunkZ = footprint.method_35417() >> 4;
        int maxChunkZ = footprint.method_35420() >> 4;
        for (int chunkX = minChunkX; chunkX <= maxChunkX; ++chunkX) {
            for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; ++chunkZ) {
                chunkKeys.add(class_1923.method_8331((int)chunkX, (int)chunkZ));
            }
        }
    }

    private static boolean isOutskirtsRole(KingdomNpcRole role) {
        return role == KingdomNpcRole.CART_RIDER || role == KingdomNpcRole.FISHERMAN;
    }

    private static boolean isOutskirtsRoleId(String roleId) {
        return KingdomNpcRole.CART_RIDER.name().equals(roleId) || KingdomNpcRole.FISHERMAN.name().equals(roleId);
    }

    private static DynamicStoneWallPlan createDynamicStoneWallPlan(class_3218 world, class_2338 bannerPos, CastleCityTierPlan tierPlan, List<class_1923> structureChunks, boolean northSouthGates) {
        return GotCastles.createDynamicStoneWallPlan(world, bannerPos, tierPlan, structureChunks, northSouthGates, 0);
    }

    private static DynamicStoneWallPlan createDynamicStoneWallPlan(class_3218 world, class_2338 bannerPos, CastleCityTierPlan tierPlan, List<class_1923> structureChunks, boolean northSouthGates, int gateRadiusReductionChunks) {
        CastleDynamicWallPlanningHelper.WallPlan plan = CastleDynamicWallPlanningHelper.createWallPlan(bannerPos, tierPlan, structureChunks, northSouthGates, gateRadiusReductionChunks, 6, 2);
        return new DynamicStoneWallPlan(plan.outerMinX(), plan.outerMinZ(), plan.outerWidth(), plan.outerDepth(), plan.northSouthGates(), plan.roadX(), plan.roadZ(), plan.interiorChunkKeys());
    }

    private static boolean isOriginalNightsWatchCastle(@Nullable String kingdomId) {
        return "agob:the_nights_watch".equals(kingdomId);
    }

    private static boolean placeDynamicStoneWallPlan(class_3218 world, class_3485 templateManager, DynamicStoneWallPlan plan, int y, @Nullable String kingdomId, @Nullable List<WallGateClaim> wallGateClaims) {
        class_3499 wallTemplate = GotCastles.loadDynamicWallTemplate(templateManager, GotCastles.getDynamicWallStructureIdForWorldgen(kingdomId), STONE_CASTLE_WALL_STRUCTURE_ID);
        class_3499 cornerTemplate = GotCastles.loadDynamicWallTemplate(templateManager, GotCastles.getDynamicCornerStructureIdForWorldgen(kingdomId), STONE_CASTLE_CORNER_STRUCTURE_ID);
        class_3499 gateTemplate = GotCastles.loadDynamicWallTemplate(templateManager, GotCastles.getDynamicGateStructureIdForWorldgen(kingdomId), STONE_CASTLE_GATE_STRUCTURE_ID);
        if (wallTemplate == null || cornerTemplate == null || gateTemplate == null) {
            return false;
        }
        HashMap<Integer, List<Integer>> northEdges = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, List<Integer>> southEdges = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, List<Integer>> westEdges = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, List<Integer>> eastEdges = new HashMap<Integer, List<Integer>>();
        for (long chunkKey : plan.interiorChunkKeys()) {
            int chunkX = class_1923.method_8325((long)chunkKey);
            int chunkZ = class_1923.method_8332((long)chunkKey);
            if (!plan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)(chunkZ - 1)))) {
                GotCastles.addDynamicPerimeterEdge(northEdges, chunkZ, chunkX);
            }
            if (!plan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)(chunkZ + 1)))) {
                GotCastles.addDynamicPerimeterEdge(southEdges, chunkZ, chunkX);
            }
            if (!plan.interiorChunkKeys().contains(class_1923.method_8331((int)(chunkX - 1), (int)chunkZ))) {
                GotCastles.addDynamicPerimeterEdge(westEdges, chunkX, chunkZ);
            }
            if (plan.interiorChunkKeys().contains(class_1923.method_8331((int)(chunkX + 1), (int)chunkZ))) continue;
            GotCastles.addDynamicPerimeterEdge(eastEdges, chunkX, chunkZ);
        }
        boolean placed = GotCastles.placeDynamicHorizontalEdgeRuns(world, wallTemplate, gateTemplate, plan, northEdges, y, class_2350.field_11043, wallGateClaims);
        placed |= GotCastles.placeDynamicHorizontalEdgeRuns(world, wallTemplate, gateTemplate, plan, southEdges, y, class_2350.field_11035, wallGateClaims);
        placed |= GotCastles.placeDynamicVerticalEdgeRuns(world, wallTemplate, gateTemplate, plan, westEdges, y, class_2350.field_11039, wallGateClaims);
        GotCastles.placeDynamicPerimeterCorners(world, cornerTemplate, plan, y);
        return placed |= GotCastles.placeDynamicVerticalEdgeRuns(world, wallTemplate, gateTemplate, plan, eastEdges, y, class_2350.field_11034, wallGateClaims);
    }

    @Nullable
    private static class_3499 loadDynamicWallTemplate(class_3485 templateManager, class_2960 preferred, class_2960 fallback) {
        class_3499 template = templateManager.method_15094(preferred).orElse(null);
        if (template != null || preferred.equals((Object)fallback)) {
            return template;
        }
        GotMod.LOGGER.warn("Missing dynamic castle wall template {}, falling back to {}", (Object)preferred, (Object)fallback);
        return templateManager.method_15094(fallback).orElse(null);
    }

    private static void addDynamicPerimeterEdge(Map<Integer, List<Integer>> edges, int fixedChunk, int runChunk) {
        edges.computeIfAbsent(fixedChunk, ignored -> new ArrayList()).add(runChunk);
    }

    private static boolean placeDynamicHorizontalEdgeRuns(class_3218 world, class_3499 wallTemplate, class_3499 gateTemplate, DynamicStoneWallPlan plan, Map<Integer, List<Integer>> edges, int y, class_2350 side, @Nullable List<WallGateClaim> wallGateClaims) {
        boolean placed = false;
        class_2470 rotation = GotCastles.wallRotationForSide(side);
        class_3341 wallBounds = wallTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        class_3341 gateBounds = gateTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        for (Map.Entry<Integer, List<Integer>> entry : edges.entrySet()) {
            int runStart;
            List<Integer> chunks = entry.getValue();
            Collections.sort(chunks);
            int previous = runStart = chunks.getFirst().intValue();
            for (int index = 1; index <= chunks.size(); ++index) {
                if (index < chunks.size() && chunks.get(index) == previous + 1) {
                    previous = chunks.get(index);
                    continue;
                }
                placed |= GotCastles.placeDynamicHorizontalEdgeRun(world, wallTemplate, gateTemplate, plan, entry.getKey(), runStart, previous, y, side, rotation, wallBounds, gateBounds, wallGateClaims);
                if (index >= chunks.size()) continue;
                previous = runStart = chunks.get(index).intValue();
            }
        }
        return placed;
    }

    private static boolean placeDynamicHorizontalEdgeRun(class_3218 world, class_3499 wallTemplate, class_3499 gateTemplate, DynamicStoneWallPlan plan, int chunkZ, int startChunkX, int endChunkX, int y, class_2350 side, class_2470 rotation, class_3341 wallBounds, class_3341 gateBounds, @Nullable List<WallGateClaim> wallGateClaims) {
        boolean hasGate;
        int runMinX = startChunkX << 4;
        int runMaxX = endChunkX + 1 << 4;
        int wallZ = side == class_2350.field_11043 ? chunkZ << 4 : (chunkZ + 1 << 4) - wallBounds.method_14663();
        boolean bl = hasGate = plan.northSouthGates() && startChunkX <= plan.roadX() >> 4 && endChunkX >= plan.roadX() >> 4;
        if (!hasGate) {
            return GotCastles.placeDynamicStraightRunX(world, wallTemplate, rotation, runMinX, runMaxX, y, wallZ, wallBounds.method_35414());
        }
        int gateWidth = gateBounds.method_35414();
        int gateMinX = class_3532.method_15340((int)(plan.roadX() - gateWidth / 2), (int)runMinX, (int)Math.max(runMinX, runMaxX - gateWidth));
        int gateZ = side == class_2350.field_11043 ? chunkZ << 4 : (chunkZ + 1 << 4) - gateBounds.method_14663();
        boolean placed = GotCastles.placeDynamicStraightRunX(world, wallTemplate, rotation, runMinX, gateMinX, y, wallZ, wallBounds.method_35414());
        boolean gatePlaced = GotCastles.placeStructureAtMin(world, gateTemplate, rotation, gateMinX, y, gateZ);
        if (gatePlaced) {
            GotCastles.addDynamicWallGateClaim(wallGateClaims, gateTemplate, rotation, gateMinX, y, gateZ);
        }
        placed |= gatePlaced;
        return placed |= GotCastles.placeDynamicStraightRunX(world, wallTemplate, rotation, gateMinX + gateWidth, runMaxX, y, wallZ, wallBounds.method_35414());
    }

    private static boolean placeDynamicVerticalEdgeRuns(class_3218 world, class_3499 wallTemplate, class_3499 gateTemplate, DynamicStoneWallPlan plan, Map<Integer, List<Integer>> edges, int y, class_2350 side, @Nullable List<WallGateClaim> wallGateClaims) {
        boolean placed = false;
        class_2470 rotation = GotCastles.wallRotationForSide(side);
        class_3341 wallBounds = wallTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        class_3341 gateBounds = gateTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        for (Map.Entry<Integer, List<Integer>> entry : edges.entrySet()) {
            int runStart;
            List<Integer> chunks = entry.getValue();
            Collections.sort(chunks);
            int previous = runStart = chunks.getFirst().intValue();
            for (int index = 1; index <= chunks.size(); ++index) {
                if (index < chunks.size() && chunks.get(index) == previous + 1) {
                    previous = chunks.get(index);
                    continue;
                }
                placed |= GotCastles.placeDynamicVerticalEdgeRun(world, wallTemplate, gateTemplate, plan, entry.getKey(), runStart, previous, y, side, rotation, wallBounds, gateBounds, wallGateClaims);
                if (index >= chunks.size()) continue;
                previous = runStart = chunks.get(index).intValue();
            }
        }
        return placed;
    }

    private static boolean placeDynamicVerticalEdgeRun(class_3218 world, class_3499 wallTemplate, class_3499 gateTemplate, DynamicStoneWallPlan plan, int chunkX, int startChunkZ, int endChunkZ, int y, class_2350 side, class_2470 rotation, class_3341 wallBounds, class_3341 gateBounds, @Nullable List<WallGateClaim> wallGateClaims) {
        boolean hasGate;
        int runMinZ = startChunkZ << 4;
        int runMaxZ = endChunkZ + 1 << 4;
        int wallX = side == class_2350.field_11039 ? chunkX << 4 : (chunkX + 1 << 4) - wallBounds.method_35414();
        boolean bl = hasGate = !plan.northSouthGates() && startChunkZ <= plan.roadZ() >> 4 && endChunkZ >= plan.roadZ() >> 4;
        if (!hasGate) {
            return GotCastles.placeDynamicStraightRunZ(world, wallTemplate, rotation, wallX, y, runMinZ, runMaxZ, wallBounds.method_14663());
        }
        int gateDepth = gateBounds.method_14663();
        int gateMinZ = class_3532.method_15340((int)(plan.roadZ() - gateDepth / 2), (int)runMinZ, (int)Math.max(runMinZ, runMaxZ - gateDepth));
        int gateX = side == class_2350.field_11039 ? chunkX << 4 : (chunkX + 1 << 4) - gateBounds.method_35414();
        boolean placed = GotCastles.placeDynamicStraightRunZ(world, wallTemplate, rotation, wallX, y, runMinZ, gateMinZ, wallBounds.method_14663());
        boolean gatePlaced = GotCastles.placeStructureAtMin(world, gateTemplate, rotation, gateX, y, gateMinZ);
        if (gatePlaced) {
            GotCastles.addDynamicWallGateClaim(wallGateClaims, gateTemplate, rotation, gateX, y, gateMinZ);
        }
        placed |= gatePlaced;
        return placed |= GotCastles.placeDynamicStraightRunZ(world, wallTemplate, rotation, wallX, y, gateMinZ + gateDepth, runMaxZ, wallBounds.method_14663());
    }

    private static void addDynamicWallGateClaim(@Nullable List<WallGateClaim> wallGateClaims, class_3499 gateTemplate, class_2470 rotation, int minX, int y, int minZ) {
        if (wallGateClaims == null) {
            return;
        }
        class_3341 localBounds = gateTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
        class_2382 size = new class_2382(localBounds.method_35414(), localBounds.method_14660(), localBounds.method_14663());
        class_2338 origin = new class_2338(minX - localBounds.method_35415(), y - localBounds.method_35416(), minZ - localBounds.method_35417());
        class_3341 footprint = new class_3341(minX, y, minZ, minX + size.method_10263() - 1, y + size.method_10264() - 1, minZ + size.method_10260() - 1);
        class_2338 pointOfInterest = new class_2338(minX + size.method_10263() / 2, y, minZ + size.method_10260() / 2);
        wallGateClaims.add(new WallGateClaim(footprint, origin, size, pointOfInterest));
    }

    private static boolean placeDynamicStraightRunX(class_3218 world, class_3499 template, class_2470 rotation, int minX, int maxXExclusive, int y, int z, int pieceLength) {
        if (maxXExclusive <= minX || pieceLength <= 0) {
            return false;
        }
        boolean placed = false;
        int previousX = Integer.MIN_VALUE;
        int x = minX;
        while (x + pieceLength <= maxXExclusive) {
            placed |= GotCastles.placeStructureAtMin(world, template, rotation, x, y, z);
            previousX = x;
            x += pieceLength;
        }
        if (previousX + pieceLength < maxXExclusive && (x = Math.max(minX, maxXExclusive - pieceLength)) != previousX) {
            placed |= GotCastles.placeStructureAtMin(world, template, rotation, x, y, z);
        }
        return placed;
    }

    private static boolean placeDynamicStraightRunZ(class_3218 world, class_3499 template, class_2470 rotation, int x, int y, int minZ, int maxZExclusive, int pieceLength) {
        if (maxZExclusive <= minZ || pieceLength <= 0) {
            return false;
        }
        boolean placed = false;
        int previousZ = Integer.MIN_VALUE;
        int z = minZ;
        while (z + pieceLength <= maxZExclusive) {
            placed |= GotCastles.placeStructureAtMin(world, template, rotation, x, y, z);
            previousZ = z;
            z += pieceLength;
        }
        if (previousZ + pieceLength < maxZExclusive && (z = Math.max(minZ, maxZExclusive - pieceLength)) != previousZ) {
            placed |= GotCastles.placeStructureAtMin(world, template, rotation, x, y, z);
        }
        return placed;
    }

    private static class_2470 wallRotationForSide(class_2350 side) {
        return switch (side) {
            case class_2350.field_11043 -> class_2470.field_11467;
            case class_2350.field_11035 -> class_2470.field_11464;
            case class_2350.field_11039 -> class_2470.field_11465;
            case class_2350.field_11034 -> class_2470.field_11463;
            default -> class_2470.field_11467;
        };
    }

    private static boolean isDynamicGateEdge(DynamicStoneWallPlan plan, int chunkX, int chunkZ, class_2350 side) {
        int roadChunkX = plan.roadX() >> 4;
        int roadChunkZ = plan.roadZ() >> 4;
        if (plan.northSouthGates()) {
            return chunkX == roadChunkX && (side == class_2350.field_11043 && !plan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)(chunkZ - 1))) || side == class_2350.field_11035 && !plan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)(chunkZ + 1))));
        }
        return chunkZ == roadChunkZ && (side == class_2350.field_11039 && !plan.interiorChunkKeys().contains(class_1923.method_8331((int)(chunkX - 1), (int)chunkZ)) || side == class_2350.field_11034 && !plan.interiorChunkKeys().contains(class_1923.method_8331((int)(chunkX + 1), (int)chunkZ)));
    }

    private static void placeDynamicPerimeterCorners(class_3218 world, class_3499 cornerTemplate, DynamicStoneWallPlan plan, int y) {
        LinkedHashSet<CallSite> placedCorners = new LinkedHashSet<CallSite>();
        for (long chunkKey : plan.interiorChunkKeys()) {
            int chunkX = class_1923.method_8325((long)chunkKey);
            int chunkZ = class_1923.method_8332((long)chunkKey);
            for (int vertexX = chunkX; vertexX <= chunkX + 1; ++vertexX) {
                for (int vertexZ = chunkZ; vertexZ <= chunkZ + 1; ++vertexZ) {
                    class_2470 rotation;
                    String vertexKey = vertexX + "," + vertexZ;
                    if (!placedCorners.add((CallSite)((Object)vertexKey)) || (rotation = GotCastles.dynamicCornerRotation(plan.interiorChunkKeys(), vertexX, vertexZ)) == null) continue;
                    class_3341 bounds = cornerTemplate.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
                    int x = (vertexX << 4) - bounds.method_35414() / 2;
                    int z = (vertexZ << 4) - bounds.method_14663() / 2;
                    GotCastles.placeStructureAtMin(world, cornerTemplate, rotation, x, y, z);
                }
            }
        }
    }

    @Nullable
    private static class_2470 dynamicCornerRotation(Set<Long> interiorChunks, int vertexX, int vertexZ) {
        boolean southeast;
        boolean southwest;
        boolean northeast;
        boolean northwest = interiorChunks.contains(class_1923.method_8331((int)(vertexX - 1), (int)(vertexZ - 1)));
        int filled = (northwest ? 1 : 0) + ((northeast = interiorChunks.contains(class_1923.method_8331((int)vertexX, (int)(vertexZ - 1)))) ? 1 : 0) + ((southwest = interiorChunks.contains(class_1923.method_8331((int)(vertexX - 1), (int)vertexZ))) ? 1 : 0) + ((southeast = interiorChunks.contains(class_1923.method_8331((int)vertexX, (int)vertexZ))) ? 1 : 0);
        if (filled == 0 || filled == 2 || filled == 4) {
            return null;
        }
        if (southeast && filled == 1 || !southeast && filled == 3) {
            return class_2470.field_11467;
        }
        if (southwest && filled == 1 || !southwest && filled == 3) {
            return class_2470.field_11463;
        }
        if (northwest && filled == 1 || !northwest && filled == 3) {
            return class_2470.field_11464;
        }
        return class_2470.field_11465;
    }

    private static void placeDynamicCastleRoads(class_3218 world, class_2338 bannerPos, List<class_1923> structureChunks, DynamicStoneWallPlan plan) {
        HashMap<Long, Integer> roadDistances = new HashMap<Long, Integer>();
        LinkedHashSet<Long> capCandidates = new LinkedHashSet<Long>();
        if (plan.northSouthGates()) {
            GotCastles.recordRoadStripX(roadDistances, capCandidates, plan.roadX(), plan.outerMinZ() - 16, plan.outerMinZ() + plan.outerDepth() + 15);
            for (class_1923 class_19232 : structureChunks) {
                buildingCenterX = class_19232.method_8326() + 8;
                buildingCenterZ = class_19232.method_8328() + 8;
                GotCastles.recordRoadStripZ(roadDistances, capCandidates, buildingCenterZ, Math.min(plan.roadX(), buildingCenterX), Math.max(plan.roadX(), buildingCenterX));
            }
        } else {
            GotCastles.recordRoadStripZ(roadDistances, capCandidates, plan.roadZ(), plan.outerMinX() - 16, plan.outerMinX() + plan.outerWidth() + 15);
            for (class_1923 class_19233 : structureChunks) {
                buildingCenterX = class_19233.method_8326() + 8;
                buildingCenterZ = class_19233.method_8328() + 8;
                GotCastles.recordRoadStripX(roadDistances, capCandidates, buildingCenterX, Math.min(plan.roadZ(), buildingCenterZ), Math.max(plan.roadZ(), buildingCenterZ));
            }
        }
        for (Map.Entry entry : roadDistances.entrySet()) {
            long key = (Long)entry.getKey();
            int x = class_2338.method_10061((long)key);
            int z = class_2338.method_10083((long)key);
            boolean north = roadDistances.containsKey(class_2338.method_10064((int)x, (int)0, (int)(z - 1)));
            boolean south = roadDistances.containsKey(class_2338.method_10064((int)x, (int)0, (int)(z + 1)));
            boolean west = roadDistances.containsKey(class_2338.method_10064((int)(x - 1), (int)0, (int)z));
            boolean east = roadDistances.containsKey(class_2338.method_10064((int)(x + 1), (int)0, (int)z));
            int neighborCount = (north ? 1 : 0) + (south ? 1 : 0) + (west ? 1 : 0) + (east ? 1 : 0);
            boolean intersection = !(!north && !south || !west && !east);
            boolean borderCap = capCandidates.contains(key) && !intersection && neighborCount <= 3;
            GotCastles.placeRoadSurface(world, bannerPos, x, z, (Integer)entry.getValue(), intersection, borderCap);
        }
    }

    private static void placeDynamicCastleNatureFillers(class_3218 world, class_3485 templateManager, String castleId, CastleTier tier, DynamicStoneWallPlan wallPlan, List<class_1923> roadAnchorChunks, List<class_3341> reservedFootprints, class_5819 random, int floorY) {
        if (NATURE_STRUCTURE_PATHS.isEmpty() || wallPlan.interiorChunkKeys().isEmpty()) {
            return;
        }
        ArrayList<class_3341> allReservedFootprints = new ArrayList<class_3341>(reservedFootprints);
        allReservedFootprints.addAll(GotCastles.dynamicRoadReservedFootprints(wallPlan, roadAnchorChunks, floorY));
        allReservedFootprints.addAll(GotCastles.dynamicWallReservedFootprints(wallPlan, floorY));
        List<StructurePlot> plots = GotCastles.dynamicNatureFillerPlots(wallPlan, allReservedFootprints, floorY);
        if (plots.isEmpty()) {
            return;
        }
        Collections.shuffle(plots, new Random(random.method_43055()));
        ArrayList<String> naturePaths = new ArrayList<String>(NATURE_STRUCTURE_PATHS);
        Collections.shuffle(naturePaths, new Random(random.method_43055()));
        int targetCount = Math.min(plots.size(), GotCastles.dynamicNatureFillerTargetCount(tier));
        int placed = 0;
        int pathIndex = Math.floorMod(castleId == null ? 0 : castleId.hashCode(), naturePaths.size());
        int failedAttempts = 0;
        int maxFailedAttempts = Math.max(naturePaths.size(), targetCount * 4);
        while (placed < targetCount && !plots.isEmpty() && failedAttempts < maxFailedAttempts) {
            String structurePath = (String)naturePaths.get(pathIndex);
            pathIndex = (pathIndex + 1) % naturePaths.size();
            if (GotCastles.placeStructureInAvailablePlot(world, templateManager, plots, allReservedFootprints, structurePath, random)) {
                ++placed;
                failedAttempts = 0;
                continue;
            }
            ++failedAttempts;
        }
        if (placed > 0) {
            GotMod.LOGGER.debug("Placed {} nature filler structures inside dynamic castle {}", (Object)placed, (Object)castleId);
        }
    }

    private static int dynamicNatureFillerTargetCount(CastleTier tier) {
        return switch (tier == null ? CastleTier.SETTLEMENT : tier) {
            default -> throw new MatchException(null, null);
            case CastleTier.SETTLEMENT -> 40;
            case CastleTier.LORDSHIP -> 56;
            case CastleTier.CAPITAL -> 72;
        };
    }

    private static List<StructurePlot> dynamicNatureFillerPlots(DynamicStoneWallPlan wallPlan, List<class_3341> reservedFootprints, int floorY) {
        ArrayList<StructurePlot> plots = new ArrayList<StructurePlot>();
        int minX = wallPlan.outerMinX() + 8;
        int maxX = wallPlan.outerMinX() + wallPlan.outerWidth() - 8 - 16;
        int minZ = wallPlan.outerMinZ() + 8;
        int maxZ = wallPlan.outerMinZ() + wallPlan.outerDepth() - 8 - 16;
        for (int x = minX; x <= maxX; x += 16) {
            for (int z = minZ; z <= maxZ; z += 16) {
                class_3341 plotBounds = new class_3341(x, floorY, z, x + 16 - 1, floorY + 18, z + 16 - 1);
                if (!GotCastles.isPlotInsideDynamicInterior(wallPlan, plotBounds) || GotCastles.intersectsAnyBlockBox(plotBounds, reservedFootprints)) continue;
                plots.add(new StructurePlot(x, z, 16, 16));
            }
        }
        return plots;
    }

    private static boolean isPlotInsideDynamicInterior(DynamicStoneWallPlan wallPlan, class_3341 plotBounds) {
        int minChunkX = plotBounds.method_35415() >> 4;
        int maxChunkX = plotBounds.method_35418() >> 4;
        int minChunkZ = plotBounds.method_35417() >> 4;
        int maxChunkZ = plotBounds.method_35420() >> 4;
        for (int chunkX = minChunkX; chunkX <= maxChunkX; ++chunkX) {
            for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; ++chunkZ) {
                if (wallPlan.interiorChunkKeys().contains(class_1923.method_8331((int)chunkX, (int)chunkZ))) continue;
                return false;
            }
        }
        return true;
    }

    private static List<class_3341> dynamicRoadReservedFootprints(DynamicStoneWallPlan wallPlan, List<class_1923> structureChunks, int floorY) {
        ArrayList<class_3341> roadFootprints = new ArrayList<class_3341>();
        if (wallPlan.northSouthGates()) {
            roadFootprints.add(GotCastles.roadFootprintX(wallPlan.roadX(), wallPlan.outerMinZ() - 16, wallPlan.outerMinZ() + wallPlan.outerDepth() + 15, floorY));
            for (class_1923 chunk : structureChunks) {
                int buildingCenterX = chunk.method_8326() + 8;
                int buildingCenterZ = chunk.method_8328() + 8;
                roadFootprints.add(GotCastles.roadFootprintZ(buildingCenterZ, Math.min(wallPlan.roadX(), buildingCenterX), Math.max(wallPlan.roadX(), buildingCenterX), floorY));
            }
        } else {
            roadFootprints.add(GotCastles.roadFootprintZ(wallPlan.roadZ(), wallPlan.outerMinX() - 16, wallPlan.outerMinX() + wallPlan.outerWidth() + 15, floorY));
            for (class_1923 chunk : structureChunks) {
                int buildingCenterX = chunk.method_8326() + 8;
                int buildingCenterZ = chunk.method_8328() + 8;
                roadFootprints.add(GotCastles.roadFootprintX(buildingCenterX, Math.min(wallPlan.roadZ(), buildingCenterZ), Math.max(wallPlan.roadZ(), buildingCenterZ), floorY));
            }
        }
        return roadFootprints;
    }

    private static class_3341 roadFootprintX(int centerX, int minZ, int maxZ, int floorY) {
        return new class_3341(centerX - 2, floorY, minZ, centerX + 2, floorY + 18, maxZ);
    }

    private static class_3341 roadFootprintZ(int centerZ, int minX, int maxX, int floorY) {
        return new class_3341(minX, floorY, centerZ - 2, maxX, floorY + 18, centerZ + 2);
    }

    private static List<class_3341> dynamicWallReservedFootprints(DynamicStoneWallPlan wallPlan, int floorY) {
        int minX = wallPlan.outerMinX() - 8;
        int maxX = wallPlan.outerMinX() + wallPlan.outerWidth() + 8;
        int minZ = wallPlan.outerMinZ() - 8;
        int maxZ = wallPlan.outerMinZ() + wallPlan.outerDepth() + 8;
        int innerMinX = wallPlan.outerMinX() + 8;
        int innerMaxX = wallPlan.outerMinX() + wallPlan.outerWidth() - 8;
        int innerMinZ = wallPlan.outerMinZ() + 8;
        int innerMaxZ = wallPlan.outerMinZ() + wallPlan.outerDepth() - 8;
        return List.of(new class_3341(minX, floorY, minZ, maxX, floorY + 18, innerMinZ), new class_3341(minX, floorY, innerMaxZ, maxX, floorY + 18, maxZ), new class_3341(minX, floorY, innerMinZ, innerMinX, floorY + 18, innerMaxZ), new class_3341(innerMaxX, floorY, innerMinZ, maxX, floorY + 18, innerMaxZ));
    }

    private static void recordRoadStripX(Map<Long, Integer> roadDistances, Set<Long> capCandidates, int centerX, int minZ, int maxZ) {
        for (int z = minZ; z <= maxZ; ++z) {
            for (int x = centerX - 2; x <= centerX + 2; ++x) {
                GotCastles.recordRoadCell(roadDistances, x, z, Math.abs(x - centerX));
            }
        }
        GotCastles.recordRoadCapRow(capCandidates, centerX, minZ);
        GotCastles.recordRoadCapRow(capCandidates, centerX, maxZ);
    }

    private static void recordRoadStripZ(Map<Long, Integer> roadDistances, Set<Long> capCandidates, int centerZ, int minX, int maxX) {
        for (int x = minX; x <= maxX; ++x) {
            for (int z = centerZ - 2; z <= centerZ + 2; ++z) {
                GotCastles.recordRoadCell(roadDistances, x, z, Math.abs(z - centerZ));
            }
        }
        GotCastles.recordRoadCapColumn(capCandidates, minX, centerZ);
        GotCastles.recordRoadCapColumn(capCandidates, maxX, centerZ);
    }

    private static void recordRoadCell(Map<Long, Integer> roadDistances, int x, int z, int distanceFromCenter) {
        long key = class_2338.method_10064((int)x, (int)0, (int)z);
        roadDistances.merge(key, distanceFromCenter, Math::min);
    }

    private static void recordRoadCapRow(Set<Long> capCandidates, int centerX, int z) {
        for (int x = centerX - 2; x <= centerX + 2; ++x) {
            capCandidates.add(class_2338.method_10064((int)x, (int)0, (int)z));
        }
    }

    private static void recordRoadCapColumn(Set<Long> capCandidates, int x, int centerZ) {
        for (int z = centerZ - 2; z <= centerZ + 2; ++z) {
            capCandidates.add(class_2338.method_10064((int)x, (int)0, (int)z));
        }
    }

    private static void placeRoadSurface(class_3218 world, class_2338 bannerPos, int x, int z, int distanceFromCenter, boolean intersection, boolean borderCap) {
        int y = world.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
        if (y <= world.method_31607()) {
            return;
        }
        class_2338 pos = new class_2338(x, y, z);
        class_2680 state = world.method_8320(pos);
        if (state.method_27852(class_2246.field_10477)) {
            world.method_8652(pos, class_2246.field_10124.method_9564(), 2);
            pos = pos.method_10074();
            state = world.method_8320(pos);
        }
        if (!GotCastles.isRoadReplaceableSurface(state)) {
            return;
        }
        class_2680 roadState = borderCap ? class_2246.field_10255.method_9564() : GotCastles.mixedRoadSurface(state, GotCastles.roadNoise(bannerPos, x, z), intersection ? 0 : distanceFromCenter);
        world.method_8652(pos, roadState, 2);
        class_2338 above = pos.method_10084();
        if (!world.method_8320(above).method_26215() && world.method_8320(above).method_26227().method_15769()) {
            world.method_8652(above, class_2246.field_10124.method_9564(), 2);
        }
    }

    private static boolean isRoadReplaceableSurface(class_2680 state) {
        return state.method_27852(class_2246.field_10219) || state.method_27852(class_2246.field_10566) || state.method_27852(class_2246.field_10194) || state.method_27852(class_2246.field_10253) || state.method_27852(class_2246.field_10255) || state.method_27852(class_2246.field_10520) || state.method_27852(class_2246.field_10340) || state.method_27852(class_2246.field_10491) || state.method_27852(class_2246.field_10102) || state.method_27852(class_2246.field_9979) || state.method_27852(class_2246.field_10467) || state.method_27852(class_2246.field_10361) || state.method_27852(class_2246.field_22091) || state.method_27852(class_2246.field_23869);
    }

    private static double roadNoise(class_2338 bannerPos, int x, int z) {
        long value = (long)x * -7046029254386353131L ^ (long)z * -4658895280553007687L ^ bannerPos.method_10063() * -7723592293110705685L;
        value ^= value >>> 30;
        value *= -4658895280553007687L;
        value ^= value >>> 27;
        value *= -7723592293110705685L;
        value ^= value >>> 31;
        return (double)(value & 0xFFFFFFL) / 1.6777216E7;
    }

    private static class_2680 mixedRoadSurface(class_2680 original, double noise, int distanceFromCenter) {
        double edgeBias = (double)distanceFromCenter / (double)Math.max(1, 2);
        double keepOriginalChance = 0.08 + edgeBias * 0.28;
        if (noise < keepOriginalChance) {
            return original;
        }
        double adjusted = (noise - keepOriginalChance) / Math.max(0.01, 1.0 - keepOriginalChance);
        if (original.method_27852(class_2246.field_10102) || original.method_27852(class_2246.field_9979) || original.method_27852(class_2246.field_10467) || original.method_27852(class_2246.field_10361)) {
            if (adjusted < 0.42) {
                return original.method_27852(class_2246.field_10102) ? class_2246.field_10102.method_9564() : class_2246.field_9979.method_9564();
            }
            if (adjusted < 0.7) {
                return class_2246.field_10467.method_9564();
            }
            if (adjusted < 0.92) {
                return class_2246.field_10255.method_9564();
            }
            return class_2246.field_10361.method_9564();
        }
        if (original.method_27852(class_2246.field_10491)) {
            if (adjusted < 0.28) {
                return class_2246.field_10491.method_9564();
            }
            if (adjusted < 0.58) {
                return class_2246.field_10253.method_9564();
            }
            if (adjusted < 0.86) {
                return class_2246.field_10194.method_9564();
            }
            return class_2246.field_10255.method_9564();
        }
        if (original.method_27852(class_2246.field_10255)) {
            if (adjusted < 0.38) {
                return class_2246.field_10255.method_9564();
            }
            if (adjusted < 0.68) {
                return class_2246.field_10253.method_9564();
            }
            return class_2246.field_10194.method_9564();
        }
        if (original.method_27852(class_2246.field_22091) || original.method_27852(class_2246.field_23869) || original.method_27852(class_2246.field_10340)) {
            if (adjusted < 0.38) {
                return original.method_27852(class_2246.field_23869) ? class_2246.field_23869.method_9564() : (original.method_27852(class_2246.field_22091) ? class_2246.field_22091.method_9564() : class_2246.field_10340.method_9564());
            }
            if (adjusted < 0.72) {
                return class_2246.field_10255.method_9564();
            }
            return class_2246.field_10445.method_9564();
        }
        if (adjusted < 0.48) {
            return class_2246.field_10194.method_9564();
        }
        if (adjusted < 0.73) {
            return class_2246.field_10253.method_9564();
        }
        if (adjusted < 0.88) {
            return class_2246.field_10566.method_9564();
        }
        return original.method_27852(class_2246.field_10520) ? class_2246.field_10520.method_9564() : class_2246.field_10219.method_9564();
    }

    private static List<class_3341> dynamicStoneReservedFootprints(class_3341 coreReservedFootprint) {
        return List.of(coreReservedFootprint);
    }

    private static class_3341 dynamicStoneCoreReservedFootprint(class_3218 world, class_3485 templateManager, String castleId, class_2338 bannerPos, class_2338 footprintOrigin, class_2382 footprintSize) {
        Optional<CastleKeepTemplate> keepTemplate = GotCastles.loadCastleKeepTemplate(world, templateManager, castleId);
        if (keepTemplate.isPresent() && GotCastles.isPositiveSize(footprintSize)) {
            return GotCastles.expandBlockBoxXZ(GotCastles.castleKeepReservedFootprint(keepTemplate.get(), footprintOrigin, footprintSize), 8);
        }
        return GotCastles.dynamicStoneBannerReservedFootprint(bannerPos);
    }

    private static class_3341 castleKeepReservedFootprint(CastleKeepTemplate keepTemplate, class_2338 footprintOrigin, class_2382 footprintSize) {
        class_2382 keepSize = keepTemplate.size();
        class_2382 minOffset = keepTemplate.minOffset();
        int assemblyMinX = footprintOrigin.method_10263() + Math.max(0, (footprintSize.method_10263() - keepSize.method_10263()) / 2);
        int assemblyMinZ = footprintOrigin.method_10260() + Math.max(0, (footprintSize.method_10260() - keepSize.method_10260()) / 2);
        int keepOriginX = assemblyMinX - minOffset.method_10263();
        int keepOriginY = footprintOrigin.method_10264() + 1 - minOffset.method_10264();
        int keepOriginZ = assemblyMinZ - minOffset.method_10260();
        return new class_3341(keepOriginX + minOffset.method_10263(), keepOriginY + minOffset.method_10264(), keepOriginZ + minOffset.method_10260(), keepOriginX + minOffset.method_10263() + keepSize.method_10263() - 1, keepOriginY + minOffset.method_10264() + Math.max(18, keepSize.method_10264() + 4), keepOriginZ + minOffset.method_10260() + keepSize.method_10260() - 1);
    }

    private static boolean isPositiveSize(class_2382 size) {
        return size != null && size.method_10263() > 0 && size.method_10264() > 0 && size.method_10260() > 0;
    }

    private static class_3341 expandBlockBoxXZ(class_3341 bounds, int amount) {
        int clampedAmount = Math.max(0, amount);
        return new class_3341(bounds.method_35415() - clampedAmount, bounds.method_35416(), bounds.method_35417() - clampedAmount, bounds.method_35418() + clampedAmount, bounds.method_35419(), bounds.method_35420() + clampedAmount);
    }

    private static class_3341 dynamicStoneBannerReservedFootprint(class_2338 bannerPos) {
        int minX = bannerPos.method_10263() - 8;
        int minZ = bannerPos.method_10260() - 8;
        int maxX = bannerPos.method_10263() + 8;
        int maxZ = bannerPos.method_10260() + 8;
        return new class_3341(minX, bannerPos.method_10264() - 1, minZ, maxX, bannerPos.method_10264() + 18, maxZ);
    }

    private static List<class_1923> collectDynamicCastleAnchorChunks(class_3341 coreReservedFootprint, List<class_1923> structureChunks) {
        LinkedHashSet<Long> chunkKeys = new LinkedHashSet<Long>();
        GotCastles.addFootprintChunks(chunkKeys, coreReservedFootprint);
        for (class_1923 structureChunk : structureChunks) {
            chunkKeys.add(structureChunk.method_8324());
        }
        return chunkKeys.stream().map(chunkKey -> new class_1923(class_1923.method_8325((long)chunkKey), class_1923.method_8332((long)chunkKey))).toList();
    }

    private static boolean chunkIntersectsBlockBox(class_1923 chunkPos, class_3341 bounds) {
        return CastleStructurePlacementHelper.chunkIntersectsBlockBox(chunkPos, bounds);
    }

    public static boolean shouldGenerateDynamicCastleWalls(@Nullable String kingdomId) {
        return !"agob:wildling".equals(kingdomId);
    }

    public static List<CastleStructureResidentPlan> createCastleStructurePlansForWorldgen(@Nullable String kingdomId, CastleTier tier, String castleId, class_2338 bannerPos) {
        CastleCityTierPlan tierPlan = CastleCityTierRegistry.forTier(tier == null ? CastleTier.SETTLEMENT : tier);
        if (castleId == null) {
            castleId = "";
        }
        if (bannerPos == null) {
            bannerPos = class_2338.field_10980;
        }
        class_5819 random = class_5819.method_43049((long)((bannerPos.method_10063() ^ (long)castleId.hashCode()) + 17L + (long)tierPlan.tier().ordinal() * 31L));
        ClaimStructureSelection selection = GotCastles.selectClaimStructurePlacements(kingdomId == null ? "" : kingdomId, tierPlan.tier(), tierPlan.totalInteriorStructures() + 8, random);
        return selection == null ? List.of() : selection.placements();
    }

    @Nullable
    private static ClaimStructureSelection selectClaimStructurePlacements(String kingdomId, CastleTier tier, int candidateSlotCount, class_5819 random) {
        CastleCityTierPlan tierPlan = CastleCityTierRegistry.forTier(tier == null ? CastleTier.SETTLEMENT : tier);
        if (candidateSlotCount < tierPlan.totalInteriorStructures()) {
            return null;
        }
        List<CastleStructureResidentPlan> placements = CastleKingdomCityPlanRegistry.resolve(kingdomId).createBaseStructurePlan(tierPlan, random);
        return placements.size() == tierPlan.totalInteriorStructures() ? new ClaimStructureSelection(List.copyOf(placements)) : null;
    }

    private static List<PlacedCastleStructure> placeClaimStructuresInChunks(class_3218 world, class_3485 templateManager, List<class_1923> candidateChunks, List<CastleStructureResidentPlan> placements, @Nullable String kingdomId) {
        ArrayList<PlacedCastleStructure> placedStructures = new ArrayList<PlacedCastleStructure>();
        ArrayList<class_1923> availableChunks = new ArrayList<class_1923>(candidateChunks);
        for (CastleStructureResidentPlan structurePlan : placements) {
            if (availableChunks.isEmpty()) break;
            StructurePlacementResult placement = GotCastles.placeClaimStructureInAvailableChunk(world, templateManager, availableChunks, structurePlan.structurePath(), kingdomId);
            if (placement == null) continue;
            placedStructures.add(new PlacedCastleStructure(structurePlan, placement));
        }
        return placedStructures;
    }

    @Nullable
    private static StructurePlacementResult placeClaimStructureInAvailableChunk(class_3218 world, class_3485 templateManager, List<class_1923> availableChunks, String structurePath, @Nullable String kingdomId) {
        for (int index = 0; index < availableChunks.size(); ++index) {
            class_1923 chunkPos = availableChunks.get(index);
            StructurePlacementResult placement = GotCastles.placeClaimStructure(world, templateManager, chunkPos, structurePath, kingdomId);
            if (placement == null) continue;
            availableChunks.remove(index);
            return placement;
        }
        return null;
    }

    private static boolean placeStructureInAvailablePlot(class_3218 world, class_3485 templateManager, List<StructurePlot> plots, List<class_3341> reservedFootprints, String structurePath, class_5819 random) {
        for (int index = 0; index < plots.size(); ++index) {
            StructurePlot plot = plots.get(index);
            StructurePlacementResult placedFootprint = GotCastles.placeStructureInPlot(world, templateManager, plot, reservedFootprints, structurePath, random, null);
            if (placedFootprint == null) continue;
            plots.remove(index);
            reservedFootprints.add(placedFootprint.footprint());
            return true;
        }
        return false;
    }

    @Nullable
    private static StructurePlacementResult placeStructureInPlot(class_3218 world, class_3485 templateManager, StructurePlot plot, List<class_3341> reservedFootprints, String structurePath, class_5819 random, @Nullable String kingdomId) {
        class_2960 structureId = class_2960.method_60655((String)"agob", (String)structurePath);
        class_3499 template = templateManager.method_15094(structureId).orElse(null);
        if (template == null) {
            GotMod.LOGGER.warn("Missing claim structure template {}", (Object)structureId);
            return null;
        }
        return GotCastles.placeStructureInPlot(world, structureId, template, plot, reservedFootprints, random, kingdomId);
    }

    @Nullable
    private static StructurePlacementResult placeStructureInPlot(class_3218 world, class_2960 structureId, class_3499 template, StructurePlot plot, List<class_3341> reservedFootprints, class_5819 random, @Nullable String kingdomId) {
        StructureSurfaceStats surfaceStats;
        ArrayList<class_2470> rotations = new ArrayList<class_2470>(List.of(class_2470.field_11467, class_2470.field_11463, class_2470.field_11464, class_2470.field_11465));
        Collections.shuffle(rotations, new Random(random.method_43055()));
        class_2470 chosenRotation = null;
        class_2382 chosenSize = null;
        class_3341 chosenLocalBounds = null;
        int chosenPadding = 3;
        int bestScore = Integer.MAX_VALUE;
        for (int padding = 3; padding >= 0; --padding) {
            int paddedWidth = Math.max(0, plot.width() - padding * 2);
            int paddedDepth = Math.max(0, plot.depth() - padding * 2);
            for (class_2470 rotation : rotations) {
                int score;
                class_3341 localBounds = template.method_27267(class_2338.field_10980, rotation, class_2338.field_10980, class_2415.field_11302);
                class_2382 rotatedSize = new class_2382(localBounds.method_35414(), localBounds.method_14660(), localBounds.method_14663());
                if (rotatedSize.method_10263() > paddedWidth || rotatedSize.method_10260() > paddedDepth || (score = padding * 1000 + (paddedWidth - rotatedSize.method_10263()) + (paddedDepth - rotatedSize.method_10260())) >= bestScore) continue;
                bestScore = score;
                chosenRotation = rotation;
                chosenSize = rotatedSize;
                chosenLocalBounds = localBounds;
                chosenPadding = padding;
            }
        }
        if (chosenRotation == null || chosenSize == null || chosenLocalBounds == null) {
            return null;
        }
        int paddedWidth = Math.max(0, plot.width() - chosenPadding * 2);
        int paddedDepth = Math.max(0, plot.depth() - chosenPadding * 2);
        int plotMinX = plot.minX() + chosenPadding;
        int plotMinZ = plot.minZ() + chosenPadding;
        int footprintMinX = plotMinX + Math.max(0, (paddedWidth - chosenSize.method_10263()) / 2);
        int footprintMinZ = plotMinZ + Math.max(0, (paddedDepth - chosenSize.method_10260()) / 2);
        int startY = world.method_8624(class_2902.class_2903.field_13203, footprintMinX + chosenSize.method_10263() / 2, footprintMinZ + chosenSize.method_10260() / 2);
        class_2338 footprintOrigin = new class_2338(footprintMinX, startY, footprintMinZ);
        if (!GotCastles.isValidClaimStructureFootprint(world, footprintOrigin, chosenSize, surfaceStats = GotCastles.sampleStructureSurface(world, footprintOrigin, chosenSize), footprintOrigin.method_10264() - 1)) {
            return null;
        }
        int floorY = GotCastles.resolveStructureFloorY(surfaceStats);
        footprintOrigin = new class_2338(footprintMinX, floorY + 1, footprintMinZ);
        class_2338 placementOrigin = GotCastles.structurePlacementOriginForMin(chosenLocalBounds, footprintOrigin.method_10263(), footprintOrigin.method_10264(), footprintOrigin.method_10260());
        class_3341 placementBounds = GotCastles.offsetBlockBox(chosenLocalBounds, placementOrigin);
        class_3341 reservedPlacementBounds = new class_3341(placementBounds.method_35415(), footprintOrigin.method_10264(), placementBounds.method_35417(), placementBounds.method_35418(), footprintOrigin.method_10264() + 18, placementBounds.method_35420());
        if (GotCastles.intersectsAnyBlockBox(reservedPlacementBounds, reservedFootprints)) {
            return null;
        }
        GotCastles.prepareStructurePad(world, footprintOrigin, chosenSize, floorY, 4, kingdomId);
        GotCastles.clearBuildVolume(world, footprintOrigin, chosenSize, footprintOrigin.method_10264(), 18);
        class_3492 placement = new class_3492().method_15133(false).method_15131(true).method_27264(false).method_15123(chosenRotation).method_16184((class_3491)class_3793.field_16721);
        if (!template.method_15172((class_5425)world, placementOrigin, placementOrigin, placement, world.field_9229, 3)) {
            GotMod.LOGGER.warn("Failed to place claim structure {} in dynamic plot {}", (Object)structureId, (Object)plot);
            return null;
        }
        GotCastles.stabilizeStructureFooting(world, footprintOrigin, chosenSize, footprintOrigin.method_10264());
        return GotCastles.createStructurePlacementResult(world, reservedPlacementBounds, footprintOrigin, chosenSize);
    }

    private static class_2338 structurePlacementOriginForMin(class_3341 localBounds, int minX, int y, int minZ) {
        return CastleStructurePlacementHelper.structurePlacementOriginForMin(localBounds, minX, y, minZ);
    }

    private static class_3341 offsetBlockBox(class_3341 bounds, class_2338 origin) {
        return CastleStructurePlacementHelper.offsetBlockBox(bounds, origin);
    }

    private static boolean intersectsAnyBlockBox(class_3341 bounds, List<class_3341> others) {
        return CastleStructurePlacementHelper.intersectsAnyBlockBox(bounds, others);
    }

    private static boolean blockBoxesIntersect(class_3341 first, class_3341 second) {
        return CastleStructurePlacementHelper.blockBoxesIntersect(first, second);
    }

    private static boolean placeStructureAtMin(class_3218 world, class_3499 template, class_2470 rotation, int minX, int y, int minZ) {
        return CastleStructurePlacementHelper.placeStructureAtMin(world, template, rotation, minX, y, minZ);
    }

    private static class_2382 rotatedTemplateSize(class_3499 template, class_2470 rotation) {
        return CastleStructurePlacementHelper.rotatedTemplateSize(template, rotation);
    }

    private static void clearBuildVolume(class_3218 world, class_2338 origin, class_2382 size, int floorY, int clearanceHeight) {
        PendingCastleGeneration scratch = new PendingCastleGeneration("", "", 0, class_2338.field_10980);
        while (!GotCastles.clearBuildVolumeBatch(world, origin, size, floorY, clearanceHeight, scratch)) {
        }
    }

    private static boolean clearBuildVolumeBatch(class_3218 world, class_2338 origin, class_2382 size, int floorY, int clearanceHeight, PendingCastleGeneration pendingGeneration) {
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        int minY = floorY;
        int maxY = floorY + Math.max(0, clearanceHeight);
        class_2338.class_2339 mutable = new class_2338.class_2339();
        int startX = minX + pendingGeneration.coreCursor();
        int endX = Math.min(maxX, startX + 8 - 1);
        for (int x = startX; x <= endX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                for (int y = minY; y <= maxY; ++y) {
                    mutable.method_10103(x, y, z);
                    if (world.method_8320((class_2338)mutable).method_26215()) continue;
                    world.method_8652((class_2338)mutable, class_2246.field_10124.method_9564(), 50);
                }
            }
        }
        int nextCursor = pendingGeneration.coreCursor() + (endX - startX + 1);
        pendingGeneration.setCoreCursor(nextCursor);
        return endX >= maxX;
    }

    private static StructurePlacementResult createStructurePlacementResult(class_3218 world, class_3341 footprint, class_2338 origin, class_2382 size) {
        class_2338 pointOfInterest = new class_2338(origin.method_10263() + Math.max(0, size.method_10263() / 2), origin.method_10264(), origin.method_10260() + Math.max(0, size.method_10260() / 2));
        class_2338 residentSpawnPos = GotCastles.findStructureInteriorSpawnPos(world, origin, size, pointOfInterest);
        return new StructurePlacementResult(footprint, origin, size, pointOfInterest, residentSpawnPos);
    }

    private static List<class_1923> collectClaimStructureChunks(class_2338 bannerPos, class_2338 castleOrigin, class_2382 castleSize, int claimRadiusChunks) {
        return CastleStructureChunkPlanner.collectClaimStructureChunks(bannerPos, castleOrigin, castleSize, claimRadiusChunks, GotCastles::isWaterMapChunk);
    }

    private static List<class_1923> collectDynamicHarbourStructureChunks(class_2338 bannerPos, DynamicStoneWallPlan wallPlan, int claimRadiusChunks) {
        ChunkSetArea forbiddenArea = new ChunkSetArea(wallPlan.interiorChunkKeys());
        return CastleStructureChunkPlanner.collectFilteredChunks(bannerPos, claimRadiusChunks, chunkPos -> !GotCastles.isWaterMapChunk(chunkPos) && GotCastles.isWaterAdjacentLandChunk(chunkPos) && !GotCastles.isNearForbiddenArea(chunkPos, forbiddenArea, 2));
    }

    private static List<class_1923> collectClaimHarbourStructureChunks(class_2338 bannerPos, class_2338 castleOrigin, class_2382 castleSize, int claimRadiusChunks) {
        ArrayList<class_1923> candidates = new ArrayList<class_1923>();
        ChunkArea castleArea = GotCastles.chunkAreaFor(castleOrigin, castleSize);
        for (class_1923 chunkPos : GotCastles.collectClaimStructureChunks(bannerPos, castleOrigin, castleSize, claimRadiusChunks)) {
            if (!GotCastles.isWaterAdjacentLandChunk(chunkPos) || GotCastles.isNearForbiddenArea(chunkPos, castleArea, 2)) continue;
            candidates.add(chunkPos);
        }
        return candidates;
    }

    private static List<class_1923> collectDynamicBarnStructureChunks(class_2338 bannerPos, DynamicStoneWallPlan wallPlan, int claimRadiusChunks) {
        int wallMinChunkX = wallPlan.outerMinX() >> 4;
        int wallMaxChunkX = wallPlan.outerMinX() + wallPlan.outerWidth() - 1 >> 4;
        int wallMinChunkZ = wallPlan.outerMinZ() >> 4;
        int wallMaxChunkZ = wallPlan.outerMinZ() + wallPlan.outerDepth() - 1 >> 4;
        int gateChunkX = wallPlan.roadX() >> 4;
        int gateChunkZ = wallPlan.roadZ() >> 4;
        int searchRadius = claimRadiusChunks + 4;
        return CastleStructureChunkPlanner.collectFilteredChunks(bannerPos, searchRadius, chunkPos -> !(GotCastles.isWaterMapChunk(chunkPos) || !CastleStructureChunkPlanner.isAtLeastBufferOutsideArea(chunkPos, wallMinChunkX, wallMaxChunkX, wallMinChunkZ, wallMaxChunkZ, 3) || wallPlan.northSouthGates() && Math.abs(chunkPos.field_9181 - gateChunkX) <= 2 || !wallPlan.northSouthGates() && Math.abs(chunkPos.field_9180 - gateChunkZ) <= 2));
    }

    private static List<class_1923> collectClaimBarnStructureChunks(class_2338 bannerPos, class_2338 castleOrigin, class_2382 castleSize, int claimRadiusChunks) {
        int castleMinChunkX = castleOrigin.method_10263() >> 4;
        int castleMinChunkZ = castleOrigin.method_10260() >> 4;
        int castleMaxChunkX = castleOrigin.method_10263() + castleSize.method_10263() - 1 >> 4;
        int castleMaxChunkZ = castleOrigin.method_10260() + castleSize.method_10260() - 1 >> 4;
        int searchRadius = claimRadiusChunks + 4;
        int centerChunkX = bannerPos.method_10263() >> 4;
        int centerChunkZ = bannerPos.method_10260() >> 4;
        return CastleStructureChunkPlanner.collectFilteredChunks(bannerPos, searchRadius, chunkPos -> !GotCastles.isWaterMapChunk(chunkPos) && CastleStructureChunkPlanner.isAtLeastBufferOutsideArea(chunkPos, castleMinChunkX, castleMaxChunkX, castleMinChunkZ, castleMaxChunkZ, 3) && Math.abs(chunkPos.field_9181 - centerChunkX) > 2 && Math.abs(chunkPos.field_9180 - centerChunkZ) > 2);
    }

    private static ChunkArea chunkAreaFor(class_2338 origin, class_2382 size) {
        return CastleStructureChunkPlanner.chunkAreaFor(origin, size);
    }

    private static List<class_1923> collectShorelineClaimStructureChunks(class_3218 world, List<class_1923> candidateChunks) {
        return CastleStructureChunkPlanner.collectShorelineChunks(candidateChunks, GotCastles::isWaterAdjacentLandChunk);
    }

    private static Optional<PlacedCastleStructure> placeDedicatedHarbourStructure(class_3218 world, class_3485 templateManager, String castleId, String kingdomId, class_2338 bannerPos, List<class_1923> candidateChunks, ChunkExclusion forbiddenHarbourArea, int claimRadiusChunks, int floorY) {
        class_1923 harbourWaterChunk = GotCastles.findDedicatedHarbourWaterChunk(world, castleId, bannerPos, candidateChunks, forbiddenHarbourArea, claimRadiusChunks);
        if (harbourWaterChunk == null) {
            return Optional.empty();
        }
        List<CastleStructureResidentPlan> harbourStructures = CastleHarbourPlanRegistry.structures();
        if (harbourStructures.isEmpty()) {
            return Optional.empty();
        }
        StructurePlacementResult placement = GotCastles.placeHarbourStructure(world, templateManager, harbourWaterChunk, harbourStructures.getFirst().structurePath(), bannerPos, kingdomId, floorY);
        return placement == null ? Optional.empty() : Optional.of(new PlacedCastleStructure(harbourStructures.getFirst(), placement));
    }

    private static Optional<PlacedCastleStructure> placeDedicatedBarnStructure(class_3218 world, class_3485 templateManager, String castleId, String kingdomId, class_2338 bannerPos, class_2338 castleOrigin, class_2382 castleSize, CastleCityTierPlan tierPlan, int floorY) {
        CastleStructureResidentPlan barnPlan = CastleOutskirtsPlanRegistry.barnStructure();
        List<class_1923> candidateChunks = GotCastles.collectClaimBarnStructureChunks(bannerPos, castleOrigin, castleSize, tierPlan.claimRadiusChunks());
        class_1923 barnChunk = GotCastles.findDedicatedBarnChunk(castleId, bannerPos, candidateChunks, GotCastles.chunkAreaFor(castleOrigin, castleSize), null);
        if (barnChunk == null) {
            return Optional.empty();
        }
        StructurePlacementResult placement = GotCastles.placeClaimStructureAtFloor(world, templateManager, barnChunk, barnPlan.structurePath(), kingdomId, floorY);
        return placement == null ? Optional.empty() : Optional.of(new PlacedCastleStructure(barnPlan, placement));
    }

    private static Optional<PlacedCastleStructure> placeDedicatedBarnStructure(class_3218 world, class_3485 templateManager, String castleId, class_2338 bannerPos, String kingdomId, CastleCityTierPlan tierPlan, DynamicStoneWallPlan wallPlan, int floorY) {
        CastleStructureResidentPlan barnPlan = CastleOutskirtsPlanRegistry.barnStructure();
        List<class_1923> candidateChunks = GotCastles.collectDynamicBarnStructureChunks(bannerPos, wallPlan, tierPlan.claimRadiusChunks());
        class_1923 barnChunk = GotCastles.findDedicatedBarnChunk(castleId, bannerPos, candidateChunks, new ChunkSetArea(wallPlan.interiorChunkKeys()), wallPlan);
        if (barnChunk == null) {
            return Optional.empty();
        }
        StructurePlacementResult placement = GotCastles.placeClaimStructureAtFloor(world, templateManager, barnChunk, barnPlan.structurePath(), kingdomId, floorY);
        return placement == null ? Optional.empty() : Optional.of(new PlacedCastleStructure(barnPlan, placement));
    }

    @Nullable
    private static class_1923 findDedicatedBarnChunk(String castleId, class_2338 bannerPos, List<class_1923> candidateChunks, @Nullable ChunkExclusion forbiddenArea, @Nullable DynamicStoneWallPlan wallPlan) {
        return CastleStructureChunkPlanner.selectNearestShuffledChunk(castleId, bannerPos, candidateChunks, 53, chunkPos -> {
            if (GotCastles.isNearForbiddenArea(chunkPos, forbiddenArea, 3)) {
                return false;
            }
            if (wallPlan != null) {
                int gateChunkX = wallPlan.roadX() >> 4;
                int gateChunkZ = wallPlan.roadZ() >> 4;
                if (wallPlan.northSouthGates() && Math.abs(chunkPos.field_9181 - gateChunkX) <= 2) {
                    return false;
                }
                if (!wallPlan.northSouthGates() && Math.abs(chunkPos.field_9180 - gateChunkZ) <= 2) {
                    return false;
                }
            }
            return true;
        });
    }

    @Nullable
    private static class_1923 findDedicatedHarbourChunk(class_3218 world, String castleId, class_2338 bannerPos, List<class_1923> candidateChunks, @Nullable ChunkExclusion forbiddenHarbourArea) {
        List<class_1923> shorelineChunks = GotCastles.collectShorelineClaimStructureChunks(world, candidateChunks);
        if (shorelineChunks.isEmpty()) {
            GotMod.LOGGER.warn("Harbour castle {} has no shoreline land chunk for dedicated harbour structure", (Object)castleId);
            return null;
        }
        class_1923 harbourChunk = CastleStructureChunkPlanner.selectNearestShuffledChunk(castleId, bannerPos, shorelineChunks, 31, chunkPos -> !GotCastles.isNearForbiddenArea(chunkPos, forbiddenHarbourArea, 2));
        if (harbourChunk != null) {
            return harbourChunk;
        }
        GotMod.LOGGER.warn("Harbour castle {} has no valid shoreline harbour chunk after wall/keep filtering", (Object)castleId);
        return null;
    }

    private static class_1923 findDedicatedHarbourWaterChunk(class_3218 world, String castleId, class_2338 bannerPos, List<class_1923> candidateChunks, ChunkExclusion forbiddenHarbourArea, int claimRadiusChunks) {
        List<class_1923> shorelineChunks = GotCastles.collectShorelineClaimStructureChunks(world, candidateChunks);
        if (shorelineChunks.isEmpty()) {
            GotMod.LOGGER.warn("Harbour castle {} has no shoreline land chunk for dedicated harbour structure", (Object)castleId);
            return null;
        }
        shorelineChunks.sort(Comparator.comparingInt(chunkPos -> chunkPos.field_9181).thenComparingInt(chunkPos -> chunkPos.field_9180));
        class_5819 random = class_5819.method_43049((long)((bannerPos.method_10063() ^ (long)castleId.hashCode()) + 31L));
        Collections.shuffle(shorelineChunks, new Random(random.method_43055()));
        for (class_1923 shorelineChunk : shorelineChunks) {
            class_1923 waterChunk = GotCastles.findAdjacentWaterChunk(shorelineChunk, forbiddenHarbourArea, 2);
            if (waterChunk == null || !GotCastles.isWithinClaimRadius(waterChunk, bannerPos, claimRadiusChunks)) continue;
            return waterChunk;
        }
        class_1923 fallbackWaterChunk = GotCastles.findNearbyHarbourWaterChunk(bannerPos, candidateChunks, forbiddenHarbourArea, claimRadiusChunks);
        if (fallbackWaterChunk != null) {
            return fallbackWaterChunk;
        }
        GotMod.LOGGER.warn("Harbour castle {} has no adjacent water chunk for dedicated harbour structure", (Object)castleId);
        return null;
    }

    private static FishermanSpawnResolution resolveHarbourNpcPlacement(class_3218 world, CastleInstance instance, CastleNpcProfile profile) {
        if (profile != null && profile.pointOfInterest() != null) {
            class_2338 pointOfInterest = profile.pointOfInterest();
            class_2338 spawnPos = GotCastles.resolveCastleResidentSpawnPos(world, pointOfInterest, profile);
            return FishermanSpawnResolution.success(new HarbourNpcPlacement(pointOfInterest, spawnPos), "poi=" + pointOfInterest.method_23854() + ",spawn=" + spawnPos.method_23854());
        }
        Optional<HarbourStructurePlacement> structurePlacement = GotCastles.findHarbourStructurePlacement(world, instance);
        if (structurePlacement.isEmpty()) {
            return FishermanSpawnResolution.failed("no_harbour_structure_placement", "castle=" + instance.getId());
        }
        Optional<class_2338> poiBlock = GotCastles.findHarbourPoiBlock(world, structurePlacement.get());
        if (poiBlock.isEmpty()) {
            return FishermanSpawnResolution.failed("no_cartography_poi", "castle=" + instance.getId() + ",origin=" + structurePlacement.get().origin().method_23854());
        }
        class_2338 poi = poiBlock.get();
        Optional<class_2338> nearbySpawn = GotCastles.findWalkablePosNear(world, poi, 3);
        if (nearbySpawn.isPresent()) {
            return FishermanSpawnResolution.success(new HarbourNpcPlacement(poi, nearbySpawn.get()), "poi=" + poi.method_23854() + ",spawn=" + nearbySpawn.get().method_23854());
        }
        class_2338 interiorSpawn = GotCastles.findStructureInteriorSpawnPos(world, structurePlacement.get().origin(), structurePlacement.get().size(), poi);
        if (!GotCastles.isValidNpcStandPos(world, interiorSpawn)) {
            return FishermanSpawnResolution.failed("no_valid_spawn_pos", "castle=" + instance.getId() + ",poi=" + poi.method_23854() + ",fallback=" + interiorSpawn.method_23854());
        }
        return FishermanSpawnResolution.success(new HarbourNpcPlacement(poi, interiorSpawn), "poi=" + poi.method_23854() + ",spawn=" + interiorSpawn.method_23854());
    }

    private static Optional<HarbourStructurePlacement> findHarbourStructurePlacement(class_3218 world, CastleInstance instance) {
        if (world == null || instance == null || !instance.hasSubtype(CastleSubtype.HARBOUR)) {
            return Optional.empty();
        }
        class_2382 castleSize = new class_2382(instance.getFootprintChunks() * 16, 12, instance.getFootprintChunks() * 16);
        List<class_1923> candidateChunks = GotCastles.collectClaimHarbourStructureChunks(instance.getBannerPos(), instance.getOrigin(), castleSize, instance.getClaimRadiusChunks());
        class_1923 harbourChunk = GotCastles.findDedicatedHarbourChunk(world, instance.getId(), instance.getBannerPos(), candidateChunks, GotCastles.chunkAreaFor(instance.getOrigin(), castleSize));
        if (harbourChunk == null) {
            return Optional.empty();
        }
        class_3499 template = world.method_14183().method_15094(class_2960.method_60655((String)"agob", (String)CastleHarbourPlanRegistry.structures().getFirst().structurePath())).orElse(null);
        if (template == null) {
            return Optional.empty();
        }
        class_2338 origin = GotCastles.findChunkStructureOrigin(world, harbourChunk, template.method_15160());
        StructureSurfaceStats surfaceStats = GotCastles.sampleStructureSurface(world, origin, template.method_15160());
        int floorY = GotCastles.resolveStructureFloorY(surfaceStats);
        origin = new class_2338(origin.method_10263(), floorY + 1, origin.method_10260());
        class_2338 pointOfInterest = new class_2338(origin.method_10263() + Math.max(0, template.method_15160().method_10263() / 2), origin.method_10264(), origin.method_10260() + Math.max(0, template.method_15160().method_10260() / 2));
        return Optional.of(new HarbourStructurePlacement(harbourChunk, origin, template.method_15160(), pointOfInterest));
    }

    private static class_2338 findStructureInteriorSpawnPos(class_3218 world, class_2338 origin, class_2382 size, class_2338 fallback) {
        int centerX = origin.method_10263() + Math.max(0, size.method_10263() / 2);
        int centerZ = origin.method_10260() + Math.max(0, size.method_10260() / 2);
        for (int radius = 0; radius <= Math.max(size.method_10263(), size.method_10260()); ++radius) {
            ArrayList<class_2338> candidates = new ArrayList<class_2338>();
            for (int x = origin.method_10263(); x < origin.method_10263() + size.method_10263(); ++x) {
                for (int z = origin.method_10260(); z < origin.method_10260() + size.method_10260(); ++z) {
                    if (Math.abs(x - centerX) + Math.abs(z - centerZ) != radius) continue;
                    candidates.add(new class_2338(x, origin.method_10264(), z));
                }
            }
            for (class_2338 candidate : candidates) {
                if (world.method_8320(candidate).method_51366() || !world.method_8320(candidate.method_10084()).method_26215() || world.method_8320(candidate.method_10074()).method_26215() || !world.method_8316(candidate).method_15769()) continue;
                return candidate;
            }
        }
        return GotCastles.findNpcRespawnPos(world, fallback, 331);
    }

    private static Optional<class_2338> findHarbourPoiBlock(class_3218 world, HarbourStructurePlacement placement) {
        int minY = Math.max(world.method_31607(), placement.origin().method_10264() - 2);
        int maxY = Math.min(world.method_31600() - 1, placement.origin().method_10264() + placement.size().method_10264() + 6);
        for (int y = minY; y <= maxY; ++y) {
            for (int x = placement.waterChunk().method_8326(); x <= placement.waterChunk().method_8327(); ++x) {
                for (int z = placement.waterChunk().method_8328(); z <= placement.waterChunk().method_8329(); ++z) {
                    class_2338 pos = new class_2338(x, y, z);
                    if (!world.method_8320(pos).method_27852(class_2246.field_16336)) continue;
                    return Optional.of(pos);
                }
            }
        }
        return Optional.empty();
    }

    private static Optional<class_2338> findWalkablePosNear(class_3218 world, class_2338 center, int radius) {
        for (int distance = 0; distance <= radius; ++distance) {
            for (int dx = -distance; dx <= distance; ++dx) {
                for (int dz = -distance; dz <= distance; ++dz) {
                    if (Math.abs(dx) + Math.abs(dz) != distance) continue;
                    class_2338 base = center.method_10069(dx, 0, dz);
                    for (int dy = -1; dy <= 1; ++dy) {
                        class_2338 candidate = base.method_10086(dy);
                        if (!GotCastles.isValidNpcStandPos(world, candidate)) continue;
                        return Optional.of(candidate);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private static boolean isValidNpcStandPos(class_3218 world, class_2338 candidate) {
        return !world.method_8320(candidate).method_51366() && world.method_8320(candidate.method_10084()).method_26215() && !world.method_8320(candidate.method_10074()).method_26215() && world.method_8316(candidate).method_15769();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void schedulePendingNpcRespawn(class_3218 world, String castleId, String profileId, long delayTicks) {
        if (world == null || castleId == null || castleId.isBlank() || profileId == null || profileId.isBlank()) {
            return;
        }
        if (GotCastles.findActiveInvasionForCastle(castleId) != null) {
            return;
        }
        if (GotCastles.isGuardDutyCastleProfile(world, castleId, profileId)) {
            return;
        }
        PriorityQueue<PendingNpcRespawn> priorityQueue = PENDING_NPC_RESPAWNS;
        synchronized (priorityQueue) {
            PENDING_NPC_RESPAWNS.removeIf(respawn -> respawn.dimension().equals((Object)world.method_27983()) && castleId.equals(respawn.castleId()) && profileId.equals(respawn.profileId()));
            PENDING_NPC_RESPAWNS.add(new PendingNpcRespawn((class_5321<class_1937>)world.method_27983(), castleId, profileId, world.method_8510() + Math.max(1L, delayTicks)));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void recordFishermanSpawnAttempt(String result, String reason, CastleInstance castle, @Nullable HarbourNpcPlacement placement, @Nullable class_2338 spawnPos, @Nullable String detail) {
        Map<String, FishermanSpawnLogCounter> map = FISHERMAN_SPAWN_LOG_COUNTS;
        synchronized (map) {
            String key = result + "|" + reason;
            FishermanSpawnLogCounter counter = FISHERMAN_SPAWN_LOG_COUNTS.computeIfAbsent(key, unused -> new FishermanSpawnLogCounter(result, reason));
            ++counter.count;
            counter.sampleCastleId = castle == null ? "n/a" : castle.getId();
            counter.sampleBanner = castle == null ? "n/a" : castle.getBannerPos().method_23854();
            counter.samplePoi = placement == null ? "n/a" : placement.pointOfInterest().method_23854();
            counter.sampleSpawn = spawnPos == null ? "n/a" : spawnPos.method_23854();
            counter.sampleDetail = detail == null || detail.isBlank() ? "n/a" : detail;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void flushFishermanSpawnAttemptLogs(long now, boolean force) {
        Map<String, FishermanSpawnLogCounter> map = FISHERMAN_SPAWN_LOG_COUNTS;
        synchronized (map) {
            if (!force && now - fishermanSpawnLogWindowStartMs < 5000L) {
                return;
            }
            if (!FISHERMAN_SPAWN_LOG_COUNTS.isEmpty()) {
                ArrayList<FishermanSpawnLogCounter> counters = new ArrayList<FishermanSpawnLogCounter>(FISHERMAN_SPAWN_LOG_COUNTS.values());
                counters.sort((left, right) -> Integer.compare(right.count, left.count));
                FISHERMAN_SPAWN_LOG_COUNTS.clear();
            }
            fishermanSpawnLogWindowStartMs = now;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void recordVoyageControlState(ActiveVoyageSession session, @Nullable class_1297 ship, @Nullable class_1297 captain, @Nullable class_3222 player) {
        String detail;
        String reason = "captain_driver";
        String controller = "n/a";
        String controllerUuid = "n/a";
        String passengers = "n/a";
        if (!(ship instanceof ShipEntity)) {
            reason = ship == null ? "missing_ship" : "non_ship_entity";
            detail = "shipClass=" + (ship == null ? "null" : ship.getClass().getSimpleName());
        } else {
            ShipEntity voyageShip = (ShipEntity)ship;
            if (captain == null || captain.method_31481()) {
                reason = "missing_captain";
                detail = "captainRemoved=" + (captain != null && captain.method_31481());
            } else if (player == null || player.method_31481()) {
                reason = "missing_player";
                detail = "playerRemoved=" + (player != null && player.method_31481());
            } else {
                class_1309 controllingPassenger = voyageShip.method_5642();
                controller = controllingPassenger == null ? "null" : controllingPassenger.method_5864().toString();
                controllerUuid = controllingPassenger == null ? "n/a" : controllingPassenger.method_5845();
                passengers = GotCastles.describeVoyagePassengers(voyageShip);
                boolean shipHasCaptain = voyageShip.method_5626(captain);
                boolean shipHasPlayer = voyageShip.method_5626((class_1297)player);
                reason = !shipHasCaptain ? "missing_captain_passenger" : (!shipHasPlayer ? "missing_player_passenger" : (!voyageShip.isVoyageMode() ? "voyage_mode_off" : (controllingPassenger == captain ? "captain_driver" : (controllingPassenger == player ? "player_driver" : (controllingPassenger == null ? "no_controller" : "other_driver")))));
                detail = "shipHasCaptain=" + shipHasCaptain + ",shipHasPlayer=" + shipHasPlayer + ",captainVehicle=" + GotCastles.entityVehicleName(captain) + ",playerVehicle=" + GotCastles.entityVehicleName((class_1297)player) + ",voyageMode=" + voyageShip.isVoyageMode();
            }
        }
        Map<String, VoyageControlLogCounter> map = VOYAGE_CONTROL_LOG_COUNTS;
        synchronized (map) {
            VoyageControlLogCounter counter = VOYAGE_CONTROL_LOG_COUNTS.computeIfAbsent(reason, VoyageControlLogCounter::new);
            ++counter.count;
            counter.sampleVoyageId = session.voyageId();
            counter.sampleSourceCastle = session.sourceCastleId();
            counter.sampleDestinationCastle = session.destinationCastleId();
            counter.sampleController = controller;
            counter.sampleControllerUuid = controllerUuid;
            counter.samplePassengers = passengers;
            counter.sampleDetail = detail;
        }
    }

    private static String describeVoyagePassengers(ShipEntity ship) {
        ArrayList<CallSite> passengerEntries = new ArrayList<CallSite>();
        for (class_1297 passenger : ship.method_5685()) {
            passengerEntries.add((CallSite)((Object)(String.valueOf(passenger.method_5864()) + ":" + passenger.method_5845())));
        }
        return passengerEntries.isEmpty() ? "[]" : ((Object)passengerEntries).toString();
    }

    private static String entityVehicleName(class_1297 entity) {
        if (entity == null || entity.method_5854() == null) {
            return "n/a";
        }
        return String.valueOf(entity.method_5854().method_5864()) + ":" + entity.method_5854().method_5845();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void flushVoyageControlLogs(long now, boolean force) {
        Map<String, VoyageControlLogCounter> map = VOYAGE_CONTROL_LOG_COUNTS;
        synchronized (map) {
            if (!force && now - voyageControlLogWindowStartMs < 5000L) {
                return;
            }
            if (!VOYAGE_CONTROL_LOG_COUNTS.isEmpty()) {
                VOYAGE_CONTROL_LOG_COUNTS.clear();
            }
            voyageControlLogWindowStartMs = now;
        }
    }

    public static int calculateVoyageGoldCost(class_2338 fromBannerPos, class_2338 toBannerPos) {
        if (fromBannerPos == null || toBannerPos == null) {
            return 5;
        }
        double distance = Math.sqrt(fromBannerPos.method_10262((class_2382)toBannerPos));
        return class_3532.method_15340((int)Math.round((float)(distance / 300.0)), (int)5, (int)80);
    }

    public static int calculateVoyageGoldCost(class_2338 fromBannerPos, class_2338 toBannerPos, VesselVariant variant) {
        int baseCost = GotCastles.calculateVoyageGoldCost(fromBannerPos, toBannerPos);
        if (variant == VesselVariant.GALLEY) {
            return class_3532.method_15340((int)((int)Math.ceil((double)baseCost * 1.5)), (int)5, (int)120);
        }
        return baseCost;
    }

    public static int calculateLandTravelGoldCost(class_2338 fromBannerPos, class_2338 toBannerPos) {
        return GotCastles.calculateLandTravelGoldCost(fromBannerPos, toBannerPos, "cart");
    }

    public static int calculateLandTravelGoldCost(class_2338 fromBannerPos, class_2338 toBannerPos, @Nullable String cartTypeId) {
        if (fromBannerPos == null || toBannerPos == null) {
            return 5;
        }
        double distance = Math.sqrt(fromBannerPos.method_10262((class_2382)toBannerPos));
        int baseCost = class_3532.method_15340((int)Math.round((float)(distance / 100.0)), (int)5, (int)50);
        if ("cart_2horse".equalsIgnoreCase(cartTypeId)) {
            return class_3532.method_15340((int)((int)Math.ceil((double)baseCost * 1.5)), (int)5, (int)75);
        }
        return baseCost;
    }

    private static boolean playerHasItemCount(class_3222 player, class_1792 item, int amount) {
        if (player == null || item == null || amount <= 0) {
            return false;
        }
        return InventoryItemHelper.hasItemCount(player, item, amount);
    }

    private static void removeItems(class_3222 player, class_1792 item, int amount) {
        InventoryItemHelper.removeItems(player, item, amount);
    }

    public static void handleVoyageRequest(class_3222 player, int targetEntityId, String destinationCastleId, @Nullable String shipTypeId) {
        if (player == null || destinationCastleId == null || destinationCastleId.isBlank()) {
            return;
        }
        class_1297 entity = player.method_37908().method_8469(targetEntityId);
        if (entity instanceof AbstractKingdomCartRiderNpcEntity) {
            AbstractKingdomCartRiderNpcEntity cartRider = (AbstractKingdomCartRiderNpcEntity)entity;
            GotCastles.handleLandTravelRequest(player, cartRider, destinationCastleId, shipTypeId);
            return;
        }
        if (!(entity instanceof AbstractKingdomFishermanNpcEntity)) {
            GotMod.LOGGER.info("Voyage request rejected: targetEntityId={} reason=not_fisherman", (Object)targetEntityId);
            return;
        }
        AbstractKingdomFishermanNpcEntity fisherman = (AbstractKingdomFishermanNpcEntity)entity;
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            GotMod.LOGGER.info("Voyage request rejected: player={} reason=missing_world", (Object)player.method_5477().getString());
            return;
        }
        CastleInstance sourceCastle = fisherman.got$getCastle().isBlank() ? null : GotCastles.getWorldState(world).get(fisherman.got$getCastle());
        VoyageDestination destination = GotCastles.resolveVoyageDestination(world, destinationCastleId);
        if (sourceCastle == null || destination == null) {
            GotMod.LOGGER.info("Voyage request rejected: player={} reason=invalid_castles sourceCastle={} destinationCastle={} destinationHarbour={}", new Object[]{player.method_5477().getString(), sourceCastle == null ? "null" : sourceCastle.getId(), destinationCastleId, destination != null});
            player.method_7353((class_2561)class_2561.method_43470((String)"Voyage unavailable from this harbour.").method_27692(class_124.field_1061), true);
            return;
        }
        Optional<VoyagePath> route = GotCastles.getVoyageNetwork().findPath(sourceCastle.getId(), destination.id());
        if (route.isEmpty()) {
            GotMod.LOGGER.info("Voyage request rejected: player={} sourceCastle={} destinationCastle={} reason=no_route", new Object[]{player.method_5477().getString(), sourceCastle.getId(), destination.id()});
            player.method_7353((class_2561)class_2561.method_43470((String)"No sea route could be found for this voyage.").method_27692(class_124.field_1061), true);
            return;
        }
        VesselVariant voyageVariant = "galley".equalsIgnoreCase(shipTypeId) ? VesselVariant.GALLEY : VesselVariant.SHIP;
        int goldCost = GotCastles.calculateVoyageGoldCost(sourceCastle.getBannerPos(), destination.travelPos(), voyageVariant);
        if (!GotEconomy.hasLegacyGoldIngots((class_1657)player, goldCost)) {
            GotMod.LOGGER.info("Voyage request rejected: player={} sourceCastle={} destinationCastle={} reason=insufficient_gold cost={}", new Object[]{player.method_5477().getString(), sourceCastle.getId(), destination.id(), goldCost});
            player.method_7353((class_2561)class_2561.method_43470((String)"You do not have enough coin for this voyage.").method_27692(class_124.field_1061), true);
            return;
        }
        GotCastles.cancelVoyage(player, false);
        Kingdom kingdom = GotCastles.resolveKingdomForCastle(sourceCastle);
        class_1299<? extends AbstractKingdomCaptainNpcEntity> captainType = GotEntities.captainTypeForKingdom(kingdom);
        class_1923 harbourChunk = GotCastles.resolveVoyageHarbourChunk(fisherman);
        if (captainType == null || harbourChunk == null) {
            GotMod.LOGGER.info("Voyage request rejected: player={} sourceCastle={} reason={} kingdom={} fishermanPoi={} fishermanPos={}", new Object[]{player.method_5477().getString(), sourceCastle.getId(), captainType == null ? "missing_captain_type" : "missing_harbour_chunk", kingdom == null ? "null" : kingdom.getId(), fisherman.getPointOfInterest() == null ? "n/a" : fisherman.getPointOfInterest().method_23854(), fisherman.method_24515().method_23854()});
            player.method_7353((class_2561)class_2561.method_43470((String)"Voyage route could not be prepared here.").method_27692(class_124.field_1061), true);
            return;
        }
        class_1923 spawnChunk = GotCastles.findVoyageSpawnChunk(world, harbourChunk, route.get());
        class_2338 spawnBlock = new class_2338(spawnChunk.method_8326() + 8, world.method_8615() + 1, spawnChunk.method_8328() + 8);
        ShipEntity ship = switch (voyageVariant) {
            default -> throw new MatchException(null, null);
            case VesselVariant.GALLEY -> (ShipEntity)GotEntities.GALLEY.method_5883((class_1937)world);
            case VesselVariant.LONGSHIP, VesselVariant.SHIP -> (ShipEntity)GotEntities.SHIP.method_5883((class_1937)world);
        };
        AbstractKingdomCaptainNpcEntity captain = (AbstractKingdomCaptainNpcEntity)captainType.method_5883((class_1937)world);
        if (ship == null || captain == null) {
            GotMod.LOGGER.info("Voyage request rejected: player={} sourceCastle={} reason=entity_create_failed ship={} captain={}", new Object[]{player.method_5477().getString(), sourceCastle.getId(), ship != null, captain != null});
            player.method_7353((class_2561)class_2561.method_43470((String)"Voyage vessel could not be created.").method_27692(class_124.field_1061), true);
            return;
        }
        String voyageId = UUID.randomUUID().toString();
        String voyageTag = GotCastles.voyageTag(voyageId);
        float spawnYaw = GotCastles.computeVoyageSpawnYaw(route.get(), spawnBlock);
        ship.method_5808((double)spawnBlock.method_10263() + 0.5, spawnBlock.method_10264(), (double)spawnBlock.method_10260() + 0.5, spawnYaw, 0.0f);
        captain.method_5808((double)spawnBlock.method_10263() + 0.5, (double)spawnBlock.method_10264() + 1.0, (double)spawnBlock.method_10260() + 0.5, spawnYaw, 0.0f);
        captain.method_5943((class_5425)world, world.method_8404(spawnBlock), class_3730.field_16467, null);
        captain.got$setCastle(sourceCastle.getId());
        captain.setPointOfInterest(spawnBlock);
        ship.method_5780(voyageTag);
        captain.method_5780(voyageTag);
        world.method_8649((class_1297)ship);
        world.method_8649((class_1297)captain);
        if (!captain.method_5873((class_1297)ship, true)) {
            captain.method_31472();
            ship.method_31472();
            player.method_7353((class_2561)class_2561.method_43470((String)"Voyage captain could not board the ship.").method_27692(class_124.field_1061), true);
            return;
        }
        player.method_48105(world, (double)spawnBlock.method_10263() + 0.5, (double)spawnBlock.method_10264() + 1.0, (double)spawnBlock.method_10260() + 0.5, Set.of(), player.method_36454(), player.method_36455());
        if (!player.method_5873((class_1297)ship, true)) {
            captain.method_31472();
            ship.method_31472();
            player.method_7353((class_2561)class_2561.method_43470((String)"You could not board the ship.").method_27692(class_124.field_1061), true);
            return;
        }
        ship.setVoyageMode(captain.method_5667(), player.method_5667());
        GotEconomy.payLegacyGoldIngots(player, goldCost);
        GotMod.LOGGER.info("Voyage started: player={} sourceCastle={} destinationCastle={} harbourChunk={} spawnChunk={} spawnBlock={} shipUuid={} captainUuid={}", new Object[]{player.method_5477().getString(), sourceCastle.getId(), destination.id(), harbourChunk, spawnChunk, spawnBlock.method_23854(), ship.method_5667(), captain.method_5667()});
        ActiveVoyageSession session = new ActiveVoyageSession((class_5321<class_1937>)world.method_27983(), player.method_5667(), voyageId, voyageTag, ship.method_5667(), captain.method_5667(), sourceCastle.getId(), destination.id(), route.get().worldPoints(), new class_243((double)destination.arrivalCenter().method_10263() + 0.5, (double)world.method_8615() + 1.0, (double)destination.arrivalCenter().method_10260() + 0.5), destination.arrivalRadiusBlocks());
        session.alignToSpawn(new class_243((double)spawnBlock.method_10263() + 0.5, (double)world.method_8615() + 1.0, (double)spawnBlock.method_10260() + 0.5));
        ACTIVE_VOYAGES.put(player.method_5667(), session);
        GotCastles.checkpointActiveVoyage(world, session, (class_1297)ship, true);
        GotCastles.syncVoyageState(player, true, voyageId);
        GotMainQuests.onSeaVoyageTaken(player);
        player.method_7353((class_2561)class_2561.method_43470((String)("Voyage selected to " + destination.name() + " by " + voyageVariant.id() + " for " + GotEconomy.formatLegacyGoldIngots(goldCost) + ".")).method_27692(class_124.field_1065), true);
    }

    private static void handleLandTravelRequest(class_3222 player, AbstractKingdomCartRiderNpcEntity cartRider, String destinationCastleId, @Nullable String shipTypeId) {
        AbstractKingdomCartRiderNpcEntity driver;
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return;
        }
        VoyageDestination sourceCastle = GotCastles.resolveLandTravelDestination(world, cartRider.got$getCastle());
        VoyageDestination destination = GotCastles.resolveLandTravelDestination(world, destinationCastleId);
        if (sourceCastle == null || destination == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Land route unavailable from this castle.").method_27692(class_124.field_1061), true);
            return;
        }
        if (!LandTravelRouteHelper.canTravelByLand(sourceCastle.travelPos().method_10263(), sourceCastle.travelPos().method_10260(), destination.travelPos().method_10263(), destination.travelPos().method_10260())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"No land route could be found for this ride.").method_27692(class_124.field_1061), true);
            return;
        }
        boolean useTwoHorseCart = "cart_2horse".equalsIgnoreCase(shipTypeId);
        int goldCost = GotCastles.calculateLandTravelGoldCost(sourceCastle.travelPos(), destination.travelPos(), useTwoHorseCart ? "cart_2horse" : "cart");
        if (!GotEconomy.hasLegacyGoldIngots((class_1657)player, goldCost)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You do not have enough coin for this ride.").method_27692(class_124.field_1061), true);
            return;
        }
        GotCastles.cancelVoyage(player, false);
        Kingdom kingdom = GotCastles.resolveKingdomForTravelDestination(world, sourceCastle.id());
        class_1299<? extends AbstractKingdomCartRiderNpcEntity> driverType = GotEntities.cartRiderTypeForKingdom(kingdom);
        class_1297 cart = useTwoHorseCart ? GotEntities.CART_2HORSE.method_5883((class_1937)world) : GotEntities.CART.method_5883((class_1937)world);
        SumpterEntity horse = (SumpterEntity)GotEntities.SUMPTER.method_5883((class_1937)world);
        SumpterEntity secondaryHorse = useTwoHorseCart ? (SumpterEntity)GotEntities.SUMPTER.method_5883((class_1937)world) : null;
        AbstractKingdomCartRiderNpcEntity abstractKingdomCartRiderNpcEntity = driver = driverType == null ? null : (AbstractKingdomCartRiderNpcEntity)driverType.method_5883((class_1937)world);
        if (cart == null || horse == null || driver == null || useTwoHorseCart && secondaryHorse == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"The cart ride could not be prepared.").method_27692(class_124.field_1061), true);
            return;
        }
        class_2338 spawnPos = GotCastles.resolveLandTravelDeparturePos(world, sourceCastle.arrivalCenter(), Math.max(16, sourceCastle.arrivalRadiusBlocks()), destination.travelPos());
        float yaw = GotCastles.yawTowards(spawnPos, destination.travelPos());
        class_243 horseForward = GotCastles.horizontalForward(yaw);
        class_243 teamCenter = new class_243((double)spawnPos.method_10263() + 0.5, GotCastles.sampleSurfaceY(world, spawnPos.method_10263(), spawnPos.method_10260()), (double)spawnPos.method_10260() + 0.5);
        class_243 cartPos = teamCenter.method_1020(horseForward.method_1021(2.15));
        cart.method_5808(cartPos.field_1352, GotCastles.sampleSurfaceY(world, class_3532.method_15357((double)cartPos.field_1352), class_3532.method_15357((double)cartPos.field_1350)) + 0.28, cartPos.field_1350, yaw, 0.0f);
        class_243 horseRight = GotCastles.horizontalRight(yaw);
        class_243 primaryHorsePos = useTwoHorseCart ? teamCenter.method_1019(horseRight.method_1021(-1.15)) : teamCenter;
        horse.method_5808(primaryHorsePos.field_1352, GotCastles.sampleSurfaceY(world, class_3532.method_15357((double)primaryHorsePos.field_1352), class_3532.method_15357((double)primaryHorsePos.field_1350)), primaryHorsePos.field_1350, yaw, 0.0f);
        if (secondaryHorse != null) {
            class_243 secondaryHorsePos = teamCenter.method_1019(horseRight.method_1021(1.15));
            secondaryHorse.method_5808(secondaryHorsePos.field_1352, GotCastles.sampleSurfaceY(world, class_3532.method_15357((double)secondaryHorsePos.field_1352), class_3532.method_15357((double)secondaryHorsePos.field_1350)), secondaryHorsePos.field_1350, yaw, 0.0f);
        }
        driver.method_5808(cart.method_23317(), cart.method_23318() + 1.0, cart.method_23321(), yaw, 0.0f);
        driver.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16467, null);
        driver.got$setCastle(sourceCastle.id());
        driver.setPointOfInterest(sourceCastle.arrivalCenter());
        String voyageId = UUID.randomUUID().toString();
        String voyageTag = GotCastles.voyageTag(voyageId);
        cart.method_5780(voyageTag);
        horse.method_5780(voyageTag);
        if (secondaryHorse != null) {
            secondaryHorse.method_5780(voyageTag);
        }
        driver.method_5780(voyageTag);
        world.method_8649(cart);
        world.method_8649((class_1297)horse);
        if (secondaryHorse != null) {
            world.method_8649((class_1297)secondaryHorse);
        }
        world.method_8649((class_1297)driver);
        if (cart instanceof CartEntity) {
            CartEntity singleCart = (CartEntity)cart;
            singleCart.attachHorseEntity((class_1496)horse);
            driver.method_5873((class_1297)horse, true);
        } else if (cart instanceof TwoHorseCartEntity) {
            TwoHorseCartEntity twoHorseCart = (TwoHorseCartEntity)cart;
            twoHorseCart.attachHorseEntity((class_1496)horse);
            if (secondaryHorse != null) {
                twoHorseCart.attachHorseEntity((class_1496)secondaryHorse);
            }
            twoHorseCart.refreshDraftFormation();
            driver.method_5873(twoHorseCart, true);
        }
        player.method_48105(world, cart.method_23317(), cart.method_23318() + 1.0, cart.method_23321(), Set.of(), yaw, player.method_36455());
        if (!player.method_5873(cart, true)) {
            driver.method_31472();
            horse.method_31472();
            if (secondaryHorse != null) {
                secondaryHorse.method_31472();
            }
            cart.method_31472();
            player.method_7353((class_2561)class_2561.method_43470((String)"You could not board the cart.").method_27692(class_124.field_1061), true);
            return;
        }
        GotEconomy.payLegacyGoldIngots(player, goldCost);
        ActiveLandTravelSession session = new ActiveLandTravelSession((class_5321<class_1937>)world.method_27983(), player.method_5667(), voyageId, voyageTag, cart.method_5667(), horse.method_5667(), secondaryHorse == null ? null : secondaryHorse.method_5667(), driver.method_5667(), sourceCastle.id(), destination.id(), new class_243((double)destination.arrivalCenter().method_10263() + 0.5, 0.0, (double)destination.arrivalCenter().method_10260() + 0.5), destination.arrivalRadiusBlocks());
        ACTIVE_LAND_TRAVELS.put(player.method_5667(), session);
        GotCastles.persistActiveLandTravel(world, session);
        GotCastles.syncVoyageState(player, true, voyageId);
        GotMainQuests.onLandVoyageTaken(player);
        player.method_7353((class_2561)class_2561.method_43470((String)((useTwoHorseCart ? "2 horse cart" : "Cart") + " ride selected to " + destination.name() + " for " + GotEconomy.formatLegacyGoldIngots(goldCost) + ".")).method_27692(class_124.field_1065), true);
    }

    @Nullable
    private static class_1923 resolveVoyageHarbourChunk(AbstractKingdomFishermanNpcEntity fisherman) {
        class_1923 entityChunk;
        if (fisherman == null) {
            return null;
        }
        class_2338 poi = fisherman.getPointOfInterest();
        if (poi != null) {
            class_1923 poiChunk = new class_1923(poi);
            if (GotCastles.isWaterMapChunk(poiChunk)) {
                return poiChunk;
            }
            class_1923 adjacent = GotCastles.findAdjacentWaterChunk(poiChunk);
            if (adjacent != null) {
                return adjacent;
            }
        }
        if (GotCastles.isWaterMapChunk(entityChunk = new class_1923(fisherman.method_24515()))) {
            return entityChunk;
        }
        return GotCastles.findAdjacentWaterChunk(entityChunk);
    }

    @Nullable
    private static VoyageDestination resolveVoyageDestination(class_3218 world, String castleId) {
        if (castleId == null || castleId.isBlank()) {
            return null;
        }
        CastleInstance placed = GotCastles.getWorldState(world).get(castleId);
        if (placed != null) {
            CastlePresetMetadata metadata = presetMetadataByCastleId.get(castleId);
            String name = metadata != null && !metadata.name().isBlank() ? metadata.name() : castleId;
            return new VoyageDestination(castleId, name, placed.getBannerPos(), placed.getClaimRadiusChunks() * 16);
        }
        OverlayCastleSite site = GotCastles.findOverlaySite(castleId);
        if (site == null) {
            return null;
        }
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(castleId);
        if (!GotCastles.resolveSubtypes(site, metadata).contains((Object)CastleSubtype.HARBOUR)) {
            return null;
        }
        String name = metadata != null && !metadata.name().isBlank() ? metadata.name() : GotCastles.pendingDisplayName(site);
        return new VoyageDestination(castleId, name, site.anchor(), CLAIM_RADIUS_CHUNKS * 16);
    }

    @Nullable
    private static VoyageDestination resolveLandTravelDestination(class_3218 world, String castleId) {
        if (castleId == null || castleId.isBlank()) {
            return null;
        }
        CastleInstance placed = GotCastles.getWorldState(world).get(castleId);
        if (placed != null) {
            CastlePresetMetadata metadata = presetMetadataByCastleId.get(castleId);
            String name = metadata != null && !metadata.name().isBlank() ? metadata.name() : castleId;
            return new VoyageDestination(castleId, name, placed.getBannerPos(), placed.getClaimRadiusChunks() * 16);
        }
        VillageInstance village = VillageWorldState.get(world).get(castleId);
        if (village != null) {
            return new VoyageDestination(castleId, village.getName(), village.getCenterPos(), village.getClaimRadiusChunks() * 16);
        }
        OverlayCastleSite site = GotCastles.findOverlaySite(castleId);
        if (site == null) {
            return null;
        }
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(castleId);
        String name = metadata != null && !metadata.name().isBlank() ? metadata.name() : GotCastles.pendingDisplayName(site);
        return new VoyageDestination(castleId, name, site.anchor(), CLAIM_RADIUS_CHUNKS * 16);
    }

    private static Kingdom resolveKingdomForTravelDestination(class_3218 world, String destinationId) {
        VillageInstance village;
        CastleInstance castle;
        CastleInstance castleInstance = castle = destinationId == null || destinationId.isBlank() ? null : GotCastles.getWorldState(world).get(destinationId);
        if (castle != null) {
            return GotCastles.resolveKingdomForCastle(castle);
        }
        VillageInstance villageInstance = village = destinationId == null || destinationId.isBlank() ? null : VillageWorldState.get(world).get(destinationId);
        if (village != null) {
            class_2960 kingdomId = class_2960.method_12829((String)village.getKingdomId());
            return kingdomId == null ? null : GotKingdoms.get(kingdomId);
        }
        return null;
    }

    private static float computeVoyageSpawnYaw(VoyagePath route, class_2338 spawnBlock) {
        class_243 origin = new class_243((double)spawnBlock.method_10263() + 0.5, 0.0, (double)spawnBlock.method_10260() + 0.5);
        for (class_243 point : route.worldPoints()) {
            class_243 delta = new class_243(point.field_1352 - origin.field_1352, 0.0, point.field_1350 - origin.field_1350);
            if (delta.method_1027() < 4.0) continue;
            return (float)(Math.atan2(delta.field_1350, delta.field_1352) * 57.29577951308232) - 90.0f;
        }
        return 0.0f;
    }

    public static void cancelVoyage(class_3222 player, boolean notifyPlayer) {
        if (player == null) {
            return;
        }
        ActiveVoyageSession session = GotCastles.resolveActiveVoyageSession(player);
        if (session != null) {
            ACTIVE_VOYAGES.remove(player.method_5667());
        }
        ActiveLandTravelSession landSession = ACTIVE_LAND_TRAVELS.remove(player.method_5667());
        if (session == null) {
            if (landSession != null) {
                GotCastles.cancelLandTravel(player, landSession);
                if (notifyPlayer) {
                    player.method_7353((class_2561)class_2561.method_43470((String)"Voyage cancelled.").method_27692(class_124.field_1061), true);
                }
                return;
            }
            if (GotCastles.cancelVoyageFromTrackedShip(player)) {
                if (notifyPlayer) {
                    player.method_7353((class_2561)class_2561.method_43470((String)"Voyage cancelled.").method_27692(class_124.field_1061), true);
                }
                return;
            }
            GotMod.LOGGER.info("Voyage cancel ignored player={} playerUuid={} reason=no_active_session vehicle={} vehicleUuid={}", new Object[]{player.method_5477().getString(), player.method_5845(), player.method_5854() == null ? "null" : player.method_5854().method_5864(), player.method_5854() == null ? "n/a" : player.method_5854().method_5845()});
            GotCastles.syncVoyageState(player, false, "");
            return;
        }
        GotMod.LOGGER.info("Voyage cancel executing player={} playerUuid={} voyageId={} shipUuid={} captainUuid={} dimension={}", new Object[]{player.method_5477().getString(), player.method_5845(), session.voyageId(), session.shipUuid(), session.captainUuid(), session.dimension().method_29177()});
        class_3218 world = player.method_5682().method_3847(session.dimension());
        if (world != null) {
            GotCastles.removePersistedVoyage(world, player.method_5667());
            class_1297 ship = GotCastles.findEntityByUuid(world, session.shipUuid());
            class_1297 captain = GotCastles.resolveVoyageCaptainEntity(world, ship, session.captainUuid());
            if (ship instanceof ShipEntity) {
                ShipEntity voyageShip = (ShipEntity)ship;
                voyageShip.setVoyageMode(null, null);
            }
            if (player.method_5765() && ship != null && player.method_5854() == ship) {
                player.method_5848();
            }
            if (captain != null) {
                captain.method_31472();
            }
            if (ship != null) {
                ship.method_31472();
            }
        }
        GotCastles.syncVoyageState(player, false, "");
        if (notifyPlayer) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Voyage cancelled.").method_27692(class_124.field_1061), true);
        }
    }

    public static boolean shouldSuppressVoyageSneak(class_3222 player) {
        return player != null && (ACTIVE_VOYAGES.containsKey(player.method_5667()) || ACTIVE_LAND_TRAVELS.containsKey(player.method_5667()));
    }

    private static void cancelLandTravel(class_3222 player, ActiveLandTravelSession session) {
        class_3218 world = player.method_5682().method_3847(session.dimension());
        if (world == null) {
            GotCastles.syncVoyageState(player, false, "");
            return;
        }
        class_1297 cart = GotCastles.findEntityByUuid(world, session.cartUuid());
        class_1297 horse = GotCastles.findEntityByUuid(world, session.horseUuid());
        class_1297 secondaryHorse = session.secondaryHorseUuid() == null ? null : GotCastles.findEntityByUuid(world, session.secondaryHorseUuid());
        class_1297 driver = GotCastles.findEntityByUuid(world, session.driverUuid());
        if (player.method_5854() == cart) {
            player.method_5848();
        }
        if (driver != null && !driver.method_31481()) {
            driver.method_31472();
        }
        if (horse != null && !horse.method_31481()) {
            horse.method_31472();
        }
        if (secondaryHorse != null && !secondaryHorse.method_31481()) {
            secondaryHorse.method_31472();
        }
        if (cart != null && !cart.method_31481()) {
            cart.method_31472();
        }
        if (world != null) {
            GotCastles.removePersistedLandTravel(world, player.method_5667());
        }
        GotCastles.syncVoyageState(player, false, "");
    }

    public static void handleVoyageShipDestroyed(ShipEntity ship) {
        ActiveVoyageSession candidate2;
        if (ship == null || ship.method_37908().field_9236 || ship.method_31481()) {
            return;
        }
        ActiveVoyageSession session = null;
        for (ActiveVoyageSession candidate2 : ACTIVE_VOYAGES.values()) {
            if (!candidate2.shipUuid().equals(ship.method_5667())) continue;
            session = candidate2;
            break;
        }
        ship.setVoyageMode(null, null);
        if (session == null) {
            return;
        }
        ACTIVE_VOYAGES.remove(session.playerUuid());
        candidate2 = ship.method_37908();
        if (candidate2 instanceof class_3218) {
            class_3222 player;
            class_3218 world = (class_3218)candidate2;
            GotCastles.removePersistedVoyage(world, session.playerUuid());
            class_1297 captain = GotCastles.resolveVoyageCaptainEntity(world, (class_1297)ship, session.captainUuid());
            if (captain != null && !captain.method_31481()) {
                captain.method_31472();
            }
            if ((player = world.method_8503().method_3760().method_14602(session.playerUuid())) != null) {
                if (player.method_5854() == ship) {
                    player.method_5848();
                }
                GotCastles.syncVoyageState(player, false, "");
            }
        }
    }

    private static void tickActiveVoyages(class_3218 world) {
        if (ACTIVE_VOYAGES.isEmpty()) {
            return;
        }
        ArrayList<UUID> toRemove = new ArrayList<UUID>();
        for (Map.Entry<UUID, ActiveVoyageSession> entry : ACTIVE_VOYAGES.entrySet()) {
            ShipEntity voyageShip;
            boolean invalid;
            boolean missingEntity;
            ActiveVoyageSession session = entry.getValue();
            if (session.dimension() != world.method_27983()) continue;
            class_3222 player = world.method_8503().method_3760().method_14602(session.playerUuid());
            class_1297 ship = GotCastles.findEntityByUuid(world, session.shipUuid());
            class_1297 captain = GotCastles.findEntityByUuid(world, session.captainUuid());
            boolean bl = missingEntity = ship == null || captain == null;
            if (player == null && missingEntity || missingEntity && !session.missingEntityGraceExpired(world.method_8510(), 200L)) continue;
            if (!missingEntity) {
                session.clearMissingEntityGrace();
            }
            if (VOYAGE_DEBUG_LOGGING) {
                GotCastles.recordVoyageControlState(session, ship, captain, player);
            }
            boolean bl2 = invalid = ship == null || captain == null || ship.method_31481() || captain.method_31481();
            if (!invalid) {
                boolean bl3 = invalid = !GotCastles.ensureVoyagePassengers(ship, captain, player, session.playerUuid());
            }
            if (!invalid) {
                if (!GotCastles.advanceVoyage(world, session, ship, captain)) continue;
                if (player == null) {
                    if (!(ship instanceof ShipEntity)) continue;
                    voyageShip = (ShipEntity)ship;
                    voyageShip.setVoyageAutopilot(voyageShip.method_36454(), 0.0);
                    continue;
                }
                if (ship instanceof ShipEntity) {
                    voyageShip = (ShipEntity)ship;
                    voyageShip.setVoyageMode(null, null);
                }
                if (player.method_5765() && player.method_5854() == ship) {
                    player.method_5848();
                }
                captain.method_31472();
                ship.method_31472();
                GotCastles.removePersistedVoyage(world, entry.getKey());
                GotCastles.syncVoyageState(player, false, "");
                toRemove.add(entry.getKey());
                continue;
            }
            if (ship instanceof ShipEntity) {
                voyageShip = (ShipEntity)ship;
                voyageShip.setVoyageMode(null, null);
            }
            if (captain != null && !captain.method_31481()) {
                captain.method_31472();
            }
            if (ship != null && !ship.method_31481()) {
                ship.method_31472();
            }
            if (player != null) {
                GotCastles.syncVoyageState(player, false, "");
            }
            GotCastles.removePersistedVoyage(world, entry.getKey());
            toRemove.add(entry.getKey());
        }
        for (UUID playerUuid : toRemove) {
            ACTIVE_VOYAGES.remove(playerUuid);
        }
    }

    private static void tickActiveLandTravels(class_3218 world) {
        if (ACTIVE_LAND_TRAVELS.isEmpty()) {
            return;
        }
        ArrayList<UUID> toRemove = new ArrayList<UUID>();
        for (Map.Entry<UUID, ActiveLandTravelSession> entry : ACTIVE_LAND_TRAVELS.entrySet()) {
            class_1496 horse2;
            class_1496 secondaryHorse;
            AbstractKingdomCartRiderNpcEntity driver;
            class_1496 horse;
            class_1297 secondaryHorseEntity;
            class_1297 cartEntity;
            class_3222 player;
            ActiveLandTravelSession session;
            block19: {
                class_1297 driverEntity;
                class_1297 horseEntity;
                block18: {
                    boolean missingEntity;
                    session = entry.getValue();
                    if (session.dimension() != world.method_27983() || (player = world.method_8503().method_3760().method_14602(session.playerUuid())) == null || player.method_31481()) continue;
                    cartEntity = GotCastles.findEntityByUuid(world, session.cartUuid());
                    horseEntity = GotCastles.findEntityByUuid(world, session.horseUuid());
                    secondaryHorseEntity = session.secondaryHorseUuid() == null ? null : GotCastles.findEntityByUuid(world, session.secondaryHorseUuid());
                    driverEntity = GotCastles.findEntityByUuid(world, session.driverUuid());
                    boolean bl = missingEntity = cartEntity == null || horseEntity == null || session.secondaryHorseUuid() != null && secondaryHorseEntity == null || driverEntity == null;
                    if (missingEntity && !session.missingEntityGraceExpired(world.method_8510(), 200L)) continue;
                    if (!missingEntity) {
                        session.clearMissingEntityGrace();
                    }
                    if (!GotCastles.isLandTravelCartEntity(cartEntity) || !(horseEntity instanceof class_1496)) break block18;
                    horse = (class_1496)horseEntity;
                    if (session.secondaryHorseUuid() != null && !(secondaryHorseEntity instanceof class_1496) || !(driverEntity instanceof AbstractKingdomCartRiderNpcEntity)) break block18;
                    driver = (AbstractKingdomCartRiderNpcEntity)driverEntity;
                    if (!cartEntity.method_31481() && !horse.method_31481() && (secondaryHorseEntity == null || !secondaryHorseEntity.method_31481()) && !driver.method_31481()) break block19;
                }
                GotCastles.syncVoyageState(player, false, "");
                if (driverEntity != null && !driverEntity.method_31481()) {
                    driverEntity.method_31472();
                }
                if (horseEntity != null && !horseEntity.method_31481()) {
                    horseEntity.method_31472();
                }
                if (secondaryHorseEntity != null && !secondaryHorseEntity.method_31481()) {
                    secondaryHorseEntity.method_31472();
                }
                if (cartEntity != null && !cartEntity.method_31481()) {
                    cartEntity.method_31472();
                }
                GotCastles.removePersistedLandTravel(world, entry.getKey());
                toRemove.add(entry.getKey());
                continue;
            }
            class_1496 class_14962 = secondaryHorse = secondaryHorseEntity instanceof class_1496 ? (horse2 = (class_1496)secondaryHorseEntity) : null;
            if (cartEntity instanceof CartEntity) {
                if (driver.method_5854() != horse) {
                    driver.method_5873((class_1297)horse, true);
                }
            } else if (driver.method_5854() != cartEntity) {
                driver.method_5873(cartEntity, true);
            }
            if (player.method_5854() != cartEntity) {
                player.method_5873(cartEntity, true);
            }
            if (VOYAGE_DEBUG_LOGGING) {
                GotCastles.maybeLogLandTravelState(world, session, cartEntity, horse, secondaryHorse, driver, player);
            }
            if (!GotCastles.advanceLandTravel(world, session, player, cartEntity, horse, secondaryHorse, driver)) continue;
            toRemove.add(entry.getKey());
        }
        for (UUID playerUuid : toRemove) {
            ACTIVE_LAND_TRAVELS.remove(playerUuid);
        }
    }

    /*
     * Unable to fully structure code
     */
    private static boolean advanceLandTravel(class_3218 world, ActiveLandTravelSession session, class_3222 player, class_1297 cart, class_1496 horse, @Nullable class_1496 secondaryHorse, AbstractKingdomCartRiderNpcEntity driver) {
        if (!(cart instanceof TwoHorseCartEntity)) ** GOTO lbl-1000
        twoHorseCart = (TwoHorseCartEntity)cart;
        if (secondaryHorse != null) {
            v0 = twoHorseCart.getDraftTeamCenterPos();
        } else lbl-1000:
        // 2 sources

        {
            v0 = horse.method_19538();
        }
        horsePos = v0;
        target = session.arrivalCenter();
        toTarget = new class_243(target.field_1352 - horsePos.field_1352, 0.0, target.field_1350 - horsePos.field_1350);
        arrivalDistanceSq = toTarget.method_1027();
        if (arrivalDistanceSq <= (double)session.arrivalRadiusBlocks() * (double)session.arrivalRadiusBlocks()) {
            targetPos = class_2338.method_49637((double)target.field_1352, (double)GotCastles.sampleSurfaceY(world, class_3532.method_15357((double)target.field_1352), class_3532.method_15357((double)target.field_1350)), (double)target.field_1350);
            arrivalPos = GotCastles.findNpcRespawnPos(world, targetPos, 913);
            if (player.method_5854() == cart) {
                player.method_5848();
            }
            player.method_48105(world, (double)arrivalPos.method_10263() + 0.5, (double)arrivalPos.method_10264() + 1.0, (double)arrivalPos.method_10260() + 0.5, Set.of(), player.method_36454(), player.method_36455());
            driver.method_31472();
            horse.method_31472();
            if (secondaryHorse != null) {
                secondaryHorse.method_31472();
            }
            cart.method_31472();
            GotCastles.removePersistedLandTravel(world, player.method_5667());
            GotCastles.syncVoyageState(player, false, "");
            return true;
        }
        if (arrivalDistanceSq < 1.0E-4) {
            return false;
        }
        if (world.method_8510() >= session.nextRepathTick || session.currentPathNodes.isEmpty() || session.currentPathIndex >= session.currentPathNodes.size()) {
            GotCastles.refreshLandTravelPath(world, horse, session);
        }
        if (session.currentPathNodes.isEmpty() || session.currentPathIndex >= session.currentPathNodes.size()) {
            return false;
        }
        while (session.currentPathIndex < session.currentPathNodes.size()) {
            n = session.currentPathNodes.get(session.currentPathIndex);
            toN = new class_243(n.field_1352 - horsePos.field_1352, 0.0, n.field_1350 - horsePos.field_1350);
            if (toN.method_1027() > 9.0) break;
            ++session.currentPathIndex;
        }
        if (session.currentPathIndex >= session.currentPathNodes.size()) {
            return false;
        }
        nodePos = session.currentPathNodes.get(session.currentPathIndex);
        toNode = new class_243(nodePos.field_1352 - horsePos.field_1352, 0.0, nodePos.field_1350 - horsePos.field_1350);
        nodeDistanceSq = toNode.method_1027();
        if (nodeDistanceSq < 1.0E-4) {
            return false;
        }
        direction = toNode.method_1029();
        nextIndex = session.currentPathIndex + 1;
        if (nextIndex < session.currentPathNodes.size()) {
            nextNode = session.currentPathNodes.get(nextIndex);
            toNext = new class_243(nextNode.field_1352 - horsePos.field_1352, 0.0, nextNode.field_1350 - horsePos.field_1350);
            blendWeight = class_3532.method_15350((double)(1.0 - Math.sqrt(nodeDistanceSq) / 6.0), (double)0.0, (double)0.5);
            direction = direction.method_1019(toNext.method_1029().method_1021(blendWeight)).method_1029();
        }
        speedLimit = horse.method_45325(class_5134.field_23719);
        if (cart instanceof TwoHorseCartEntity) {
            twoHorseCart = (TwoHorseCartEntity)cart;
            if (secondaryHorse != null) {
                speedLimit = twoHorseCart.getDraftTravelSpeed();
            }
        }
        step = Math.min(speedLimit, Math.sqrt(nodeDistanceSq));
        yaw = (float)(Math.atan2(direction.field_1350, direction.field_1352) * 57.29577951308232) - 90.0f;
        driver.method_36456(yaw);
        driver.method_5636(yaw);
        driver.method_5847(yaw);
        driver.field_5982 = yaw;
        driver.field_6220 = yaw;
        driver.field_6259 = yaw;
        if (cart instanceof TwoHorseCartEntity) {
            twoHorseCart = (TwoHorseCartEntity)cart;
            if (secondaryHorse != null) {
                if (!twoHorseCart.steerDraftTeam(yaw, step)) {
                    session.nextRepathTick = 0L;
                    return false;
                }
                return false;
            }
        }
        nextX = horsePos.field_1352 + direction.field_1352 * step;
        nextZ = horsePos.field_1350 + direction.field_1350 * step;
        nextY = GotCastles.sampleSurfaceY(world, class_3532.method_15357((double)nextX), class_3532.method_15357((double)nextZ));
        if (Math.abs(nextY - horsePos.field_1351) > 1.1) {
            session.nextRepathTick = 0L;
            return false;
        }
        horse.method_5808(nextX, nextY, nextZ, yaw, 0.0f);
        horse.method_18800(direction.field_1352 * step, 0.0, direction.field_1350 * step);
        horse.method_5636(yaw);
        horse.method_5847(yaw);
        horse.field_5982 = yaw;
        horse.field_6220 = yaw;
        horse.field_6259 = yaw;
        return false;
    }

    private static void maybeLogLandTravelState(class_3218 world, ActiveLandTravelSession session, class_1297 cart, class_1496 horse, @Nullable class_1496 secondaryHorse, AbstractKingdomCartRiderNpcEntity driver, class_3222 player) {
        long currentTick = world.method_8510();
        if (currentTick < session.nextDebugLogTick()) {
            return;
        }
        session.scheduleNextDebugLogTick(currentTick + 40L);
        class_243 cartPos = cart.method_19538();
        class_243 horsePos = horse.method_19538();
        String secondaryHorseText = secondaryHorse == null ? "none" : String.format("%.2f,%.2f,%.2f", secondaryHorse.method_23317(), secondaryHorse.method_23318(), secondaryHorse.method_23321());
        GotMod.LOGGER.debug("[land-travel-debug] voyageId={} player={} cartType={} cartPos={},{},{} cartYaw={} cartPitch={} horsePos={},{},{} horseYaw={} secondaryHorsePos={} driverVehicle={} driverYaw={}", new Object[]{session.voyageId(), player.method_5477().getString(), cart.method_5864().method_5882(), String.format("%.2f", cartPos.field_1352), String.format("%.2f", cartPos.field_1351), String.format("%.2f", cartPos.field_1350), String.format("%.2f", Float.valueOf(cart.method_36454())), String.format("%.2f", Float.valueOf(cart.method_36455())), String.format("%.2f", horsePos.field_1352), String.format("%.2f", horsePos.field_1351), String.format("%.2f", horsePos.field_1350), String.format("%.2f", Float.valueOf(horse.method_36454())), secondaryHorseText, driver.method_5854() == null ? "none" : driver.method_5854().method_5864().method_5882(), String.format("%.2f", Float.valueOf(driver.method_36454()))});
    }

    private static void refreshLandTravelPath(class_3218 world, class_1496 horse, ActiveLandTravelSession session) {
        class_243 horsePos = horse.method_19538();
        class_2338 localTarget = GotCastles.resolveLandTravelLocalTarget(horsePos, session.arrivalCenter());
        class_11 path = horse.method_5942().method_6348(localTarget, 160);
        if (path == null || path.method_38() <= 0) {
            session.currentPathNodes = List.of(new class_243((double)localTarget.method_10263() + 0.5, 0.0, (double)localTarget.method_10260() + 0.5));
            session.currentPathIndex = 0;
            session.nextRepathTick = world.method_8510() + 40L;
            return;
        }
        ArrayList<class_243> nodes = new ArrayList<class_243>();
        for (int index = path.method_39(); index < path.method_38(); ++index) {
            class_243 nodePos = path.method_47((class_1297)horse, index);
            nodes.add(new class_243(nodePos.field_1352, 0.0, nodePos.field_1350));
        }
        session.currentPathNodes = List.copyOf(nodes);
        session.currentPathIndex = 0;
        session.nextRepathTick = world.method_8510() + 60L;
    }

    private static class_2338 resolveLandTravelLocalTarget(class_243 currentPos, class_243 finalTarget) {
        class_243 toTarget = new class_243(finalTarget.field_1352 - currentPos.field_1352, 0.0, finalTarget.field_1350 - currentPos.field_1350);
        double distance = toTarget.method_1033();
        if (distance <= 128.0) {
            return class_2338.method_49637((double)finalTarget.field_1352, (double)finalTarget.field_1351, (double)finalTarget.field_1350);
        }
        class_243 normalized = toTarget.method_1029();
        class_243 localTarget = currentPos.method_1019(normalized.method_1021(128.0));
        return class_2338.method_49637((double)localTarget.field_1352, (double)0.0, (double)localTarget.field_1350);
    }

    private static void cleanupDanglingLandTravelEntities(class_3218 world, ActiveLandTravelSession session, class_1297 currentCart, class_1496 currentHorse, @Nullable class_1496 secondaryHorse, AbstractKingdomCartRiderNpcEntity currentDriver) {
        for (class_1297 entity : world.method_27909()) {
            UUID uuid;
            boolean keep;
            if (entity.method_31481() || !entity.method_5752().contains(session.voyageTag()) || (keep = (uuid = entity.method_5667()).equals(currentCart.method_5667()) || uuid.equals(currentHorse.method_5667()) || secondaryHorse != null && uuid.equals(secondaryHorse.method_5667()) || uuid.equals(currentDriver.method_5667()))) continue;
            entity.method_31472();
        }
    }

    private static boolean advanceVoyage(class_3218 world, ActiveVoyageSession session, class_1297 shipEntity, class_1297 captainEntity) {
        class_243 toSteeringTarget;
        double steeringDistance;
        VoyageAvoidanceDecision avoidance;
        ShipEntity ship;
        block13: {
            block12: {
                if (!(shipEntity instanceof ShipEntity)) break block12;
                ship = (ShipEntity)shipEntity;
                if (captainEntity instanceof AbstractKingdomCaptainNpcEntity) break block13;
            }
            return true;
        }
        AbstractKingdomCaptainNpcEntity captain = (AbstractKingdomCaptainNpcEntity)captainEntity;
        double arrivalDistanceSq = ship.method_19538().method_1025(session.arrivalCenter());
        if (arrivalDistanceSq <= (double)(session.arrivalRadiusBlocks() * session.arrivalRadiusBlocks())) {
            return true;
        }
        class_243 target = session.currentTarget();
        if (target == null) {
            return true;
        }
        class_243 shipPos = ship.method_19538();
        class_243 toTarget = new class_243(target.field_1352 - shipPos.field_1352, 0.0, target.field_1350 - shipPos.field_1350);
        double distance = toTarget.method_1033();
        if (distance < 24.0) {
            if (session.advanceToNextWaypoint()) {
                return true;
            }
            target = session.currentTarget();
            if (target == null) {
                return true;
            }
            toTarget = new class_243(target.field_1352 - shipPos.field_1352, 0.0, target.field_1350 - shipPos.field_1350);
            distance = toTarget.method_1033();
        }
        if (distance < 0.001) {
            return false;
        }
        class_243 direction = toTarget.method_1029();
        session.clearExpiredAvoidance(world.method_8510());
        if (session.hasAvoidanceTarget() && session.isNearAvoidanceTarget(shipPos, 16.0)) {
            session.clearAvoidanceTarget();
        }
        class_243 steeringTarget = session.currentSteeringTarget(target);
        if (!(session.hasAvoidanceTarget() && GotCastles.isVoyageDetourNavigable(world, shipPos, steeringTarget) || (avoidance = GotCastles.findVoyageAvoidance(world, session, ship, shipPos, steeringTarget, direction)) == null)) {
            session.setAvoidanceTarget(avoidance.detourTarget(), world.method_8510() + 60L);
            steeringTarget = avoidance.detourTarget();
        }
        if ((steeringDistance = (toSteeringTarget = new class_243(steeringTarget.field_1352 - shipPos.field_1352, 0.0, steeringTarget.field_1350 - shipPos.field_1350)).method_1033()) < 0.001) {
            steeringTarget = target;
            toSteeringTarget = toTarget;
            steeringDistance = distance;
        }
        class_243 steeringDirection = toSteeringTarget.method_1029();
        double desiredSpeed = session.increaseSpeed(0.01);
        double speedCap = session.hasAvoidanceTarget() ? 0.42 : 0.55;
        double motionScale = Math.min(Math.min(desiredSpeed, speedCap), Math.max(0.18, steeringDistance / 20.0));
        float targetYaw = (float)(Math.atan2(steeringDirection.field_1350, steeringDirection.field_1352) * 57.29577951308232) - 90.0f;
        ship.setVoyageAutopilot(targetYaw, motionScale);
        float newYaw = GotCastles.lerpAngleDegrees(ship.method_36454(), targetYaw, 6.0f);
        captain.method_36456(newYaw);
        captain.method_5636(newYaw);
        captain.method_5847(newYaw);
        GotCastles.checkpointActiveVoyage(world, session, (class_1297)ship, false);
        return false;
    }

    @Nullable
    private static VoyageAvoidanceDecision findVoyageAvoidance(class_3218 world, ActiveVoyageSession session, ShipEntity ship, class_243 shipPos, class_243 target, class_243 direction) {
        VoyageObstacle obstacle = GotCastles.findVoyageObstacleAhead(world, session, ship, direction);
        if (obstacle == null) {
            return null;
        }
        class_243 starboard = new class_243(direction.field_1350, 0.0, -direction.field_1352);
        class_243 port = starboard.method_1021(-1.0);
        double baseOffset = obstacle.radius() + 20.0;
        double[] multipliers = new double[]{1.0, 1.45};
        ArrayList<VoyageAvoidanceDecision> candidates = new ArrayList<VoyageAvoidanceDecision>();
        int sideOrder = GotCastles.preferredAvoidanceSide(session);
        for (double multiplier : multipliers) {
            candidates.add(GotCastles.scoreVoyageAvoidanceCandidate(world, shipPos, target, direction, starboard, obstacle, baseOffset * multiplier, sideOrder == 0 ? 12 : 8));
            candidates.add(GotCastles.scoreVoyageAvoidanceCandidate(world, shipPos, target, direction, port, obstacle, baseOffset * multiplier, sideOrder == 1 ? 12 : 8));
        }
        return candidates.stream().filter(Objects::nonNull).max(Comparator.comparingInt(VoyageAvoidanceDecision::score)).orElse(null);
    }

    @Nullable
    private static VoyageObstacle findVoyageObstacleAhead(class_3218 world, ActiveVoyageSession session, ShipEntity ship, class_243 direction) {
        class_243 shipPos = ship.method_19538();
        class_238 searchBox = ship.method_5829().method_18804(direction.method_1021(96.0)).method_1009(20.0, 4.0, 20.0);
        class_1297 nearest = null;
        double nearestForwardDistance = Double.POSITIVE_INFINITY;
        for (class_1297 candidate : world.method_8333((class_1297)ship, searchBox, entity -> GotCastles.shouldAvoidVoyageEntity(session, ship, entity))) {
            class_243 delta = candidate.method_19538().method_1020(shipPos);
            class_243 horizontal = new class_243(delta.field_1352, 0.0, delta.field_1350);
            double forwardDistance = horizontal.method_1026(direction);
            if (forwardDistance <= 0.0 || forwardDistance > 96.0) continue;
            class_243 lateralOffset = horizontal.method_1020(direction.method_1021(forwardDistance));
            double candidateRadius = GotCastles.voyageAvoidanceRadius(candidate);
            if (lateralOffset.method_1027() > Math.pow(candidateRadius + 20.0, 2.0) || !(forwardDistance < nearestForwardDistance)) continue;
            nearestForwardDistance = forwardDistance;
            nearest = candidate;
        }
        if (nearest == null) {
            return GotCastles.findVoyageTerrainObstacleAhead(world, shipPos, direction);
        }
        return new VoyageObstacle(nearest.method_19538(), GotCastles.voyageAvoidanceRadius(nearest));
    }

    @Nullable
    private static VoyageObstacle findVoyageTerrainObstacleAhead(class_3218 world, class_243 shipPos, class_243 direction) {
        for (double distance = 12.0; distance <= 96.0; distance += 12.0) {
            class_243 probe = shipPos.method_1019(direction.method_1021(distance));
            class_1923 chunk = new class_1923(class_2338.method_49638((class_2374)probe));
            if (GotCastles.isUsableVoyageWaterChunk(world, chunk)) continue;
            return new VoyageObstacle(probe, 12.0);
        }
        return null;
    }

    private static boolean shouldAvoidVoyageEntity(ActiveVoyageSession session, ShipEntity ship, class_1297 entity) {
        if (entity == null || entity.method_31481() || !entity.method_5805() || entity == ship) {
            return false;
        }
        if (entity.method_5667().equals(session.captainUuid()) || entity.method_5667().equals(session.playerUuid())) {
            return false;
        }
        return entity instanceof ShipEntity || entity instanceof class_1309 || entity instanceof class_1676;
    }

    private static double voyageAvoidanceRadius(class_1297 entity) {
        double widthRadius = Math.max((double)entity.method_17681() * 0.5, 1.5);
        if (entity instanceof ShipEntity) {
            ShipEntity ship = (ShipEntity)entity;
            return Math.max(widthRadius, (double)ship.method_17681() * 0.75 + 6.0);
        }
        return widthRadius + 2.0;
    }

    @Nullable
    private static VoyageAvoidanceDecision scoreVoyageAvoidanceCandidate(class_3218 world, class_243 shipPos, class_243 target, class_243 forward, class_243 side, VoyageObstacle obstacle, double offset, int sideBias) {
        class_243 detour = obstacle.position().method_1019(side.method_1021(offset)).method_1019(forward.method_1021(Math.max(12.0, obstacle.radius() * 0.5)));
        if (!GotCastles.isVoyageDetourNavigable(world, shipPos, detour) || !GotCastles.isVoyageDetourNavigable(world, detour, target)) {
            return null;
        }
        int score = sideBias + GotCastles.scoreVoyageWaterOpenness(world, detour);
        return new VoyageAvoidanceDecision(detour, score);
    }

    private static boolean isVoyageDetourNavigable(class_3218 world, class_243 from, class_243 to) {
        class_243 delta = to.method_1020(from);
        double horizontalDistance = delta.method_37267();
        if (horizontalDistance < 0.001) {
            return true;
        }
        class_243 step = delta.method_1021(1.0 / Math.max(1.0, Math.ceil(horizontalDistance / 12.0)));
        class_243 probe = from;
        int samples = Math.max(1, (int)Math.ceil(horizontalDistance / 12.0));
        for (int index = 0; index <= samples; ++index) {
            class_1923 chunk = new class_1923(class_2338.method_49638((class_2374)probe));
            if (!GotCastles.isUsableVoyageWaterChunk(world, chunk)) {
                return false;
            }
            probe = probe.method_1019(step);
        }
        return true;
    }

    private static int scoreVoyageWaterOpenness(class_3218 world, class_243 point) {
        class_1923 center = new class_1923(class_2338.method_49638((class_2374)point));
        int open = 0;
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dz = -1; dz <= 1; ++dz) {
                class_1923 candidate = new class_1923(center.field_9181 + dx, center.field_9180 + dz);
                if (!(world != null ? GotCastles.isUsableVoyageWaterChunk(world, candidate) : GotCastles.isWaterMapChunk(candidate))) continue;
                ++open;
            }
        }
        return open;
    }

    private static int preferredAvoidanceSide(ActiveVoyageSession session) {
        return 0;
    }

    private static boolean ensureVoyagePassengers(class_1297 shipEntity, class_1297 captainEntity, @Nullable class_3222 player, UUID playerUuid) {
        ShipEntity ship;
        block6: {
            block5: {
                if (!(shipEntity instanceof ShipEntity)) break block5;
                ship = (ShipEntity)shipEntity;
                if (captainEntity instanceof AbstractKingdomCaptainNpcEntity) break block6;
            }
            return false;
        }
        AbstractKingdomCaptainNpcEntity captain = (AbstractKingdomCaptainNpcEntity)captainEntity;
        ship.setVoyageMode(captain.method_5667(), playerUuid);
        if (captain.method_5854() != ship && !captain.method_5873((class_1297)ship, true)) {
            return false;
        }
        if (player != null && player.method_5854() != ship && !player.method_5873((class_1297)ship, true)) {
            return false;
        }
        return ship.method_5626((class_1297)captain) && (player == null || ship.method_5626((class_1297)player)) && ship.method_5642() == captain;
    }

    private static float lerpAngleDegrees(float current, float target, float maxStep) {
        return current + class_3532.method_15363((float)class_3532.method_15393((float)(target - current)), (float)(-maxStep), (float)maxStep);
    }

    private static double sampleSurfaceY(class_3218 world, int x, int z) {
        return world.method_8624(class_2902.class_2903.field_13203, x, z);
    }

    private static float yawTowards(class_2338 from, class_2338 to) {
        double dx = to.method_10263() - from.method_10263();
        double dz = to.method_10260() - from.method_10260();
        return (float)(Math.atan2(dz, dx) * 57.29577951308232) - 90.0f;
    }

    private static class_243 horizontalForward(float yawDegrees) {
        float yawRadians = yawDegrees * ((float)Math.PI / 180);
        return new class_243((double)(-class_3532.method_15374((float)yawRadians)), 0.0, (double)class_3532.method_15362((float)yawRadians));
    }

    private static class_243 horizontalRight(float yawDegrees) {
        float yawRadians = yawDegrees * ((float)Math.PI / 180);
        return new class_243((double)class_3532.method_15362((float)yawRadians), 0.0, (double)class_3532.method_15374((float)yawRadians));
    }

    private static class_2338 resolveLandTravelDeparturePos(class_3218 world, class_2338 originPos, int departureRadiusBlocks, class_2338 destinationPos) {
        int startDistance;
        class_2338 bannerPos = originPos;
        class_243 direction = new class_243((double)(destinationPos.method_10263() - bannerPos.method_10263()), 0.0, (double)(destinationPos.method_10260() - bannerPos.method_10260()));
        direction = direction.method_1027() < 1.0E-4 ? new class_243(0.0, 0.0, 1.0) : direction.method_1029();
        for (int distance = startDistance = Math.max(departureRadiusBlocks + 20, 40); distance <= startDistance + 64; distance += 8) {
            int z;
            int x = bannerPos.method_10263() + class_3532.method_15357((double)(direction.field_1352 * (double)distance));
            class_2338 candidate = new class_2338(x, (int)GotCastles.sampleSurfaceY(world, x, z = bannerPos.method_10260() + class_3532.method_15357((double)(direction.field_1350 * (double)distance))), z);
            class_2338 resolved = GotCastles.findNearestLandTravelStandPos(world, candidate, 6);
            if (resolved == null) continue;
            return resolved;
        }
        return GotCastles.findNpcRespawnPos(world, bannerPos, 611);
    }

    @Nullable
    private static class_2338 findNearestLandTravelStandPos(class_3218 world, class_2338 center, int radius) {
        for (int range = 0; range <= radius; ++range) {
            for (int dx = -range; dx <= range; ++dx) {
                for (int dz = -range; dz <= range; ++dz) {
                    int z;
                    int x = center.method_10263() + dx;
                    class_2338 candidate = new class_2338(x, (int)GotCastles.sampleSurfaceY(world, x, z = center.method_10260() + dz), z);
                    if (!GotCastles.isValidNpcStandPos(world, candidate)) continue;
                    return candidate;
                }
            }
        }
        return null;
    }

    private static class_1923 findVoyageSpawnChunk(class_3218 world, class_1923 harbourChunk, VoyagePath route) {
        class_1923 routeSpawnChunk = GotCastles.findVoyageSpawnChunkAlongRoute(world, harbourChunk, route);
        if (routeSpawnChunk != null) {
            return routeSpawnChunk;
        }
        List<DirectionChoice> directions = List.of(new DirectionChoice(1, 0), new DirectionChoice(-1, 0), new DirectionChoice(0, 1), new DirectionChoice(0, -1));
        class_243 preferredDirection = GotCastles.preferredVoyageSpawnDirection(harbourChunk, route);
        class_1923 fallbackChunk = null;
        int fallbackScore = Integer.MIN_VALUE;
        for (int distance = 1; distance <= 16; ++distance) {
            class_1923 bestChunk = null;
            int bestScore = Integer.MIN_VALUE;
            for (DirectionChoice choice : directions) {
                class_1923 candidate = new class_1923(harbourChunk.field_9181 + choice.dx() * distance, harbourChunk.field_9180 + choice.dz() * distance);
                if (!GotCastles.isUsableVoyageWaterChunk(world, candidate)) continue;
                double alignment = preferredDirection == null ? 0.0 : preferredDirection.method_1026(new class_243((double)choice.dx(), 0.0, (double)choice.dz()));
                int score = GotCastles.countOpenSeaChunks(candidate, choice.dx(), choice.dz(), 5) * 1000 + (int)Math.round(alignment * 250.0) + distance;
                if (fallbackChunk == null || score > fallbackScore) {
                    fallbackChunk = candidate;
                    fallbackScore = score;
                }
                if (bestChunk != null && score <= bestScore) continue;
                bestChunk = candidate;
                bestScore = score;
            }
            if (bestChunk == null || distance < 4) continue;
            return bestChunk;
        }
        return fallbackChunk != null ? fallbackChunk : harbourChunk;
    }

    @Nullable
    private static class_1923 findVoyageSpawnChunkAlongRoute(class_3218 world, class_1923 harbourChunk, @Nullable VoyagePath route) {
        if (world == null || route == null) {
            return null;
        }
        long harbourKey = class_1923.method_8331((int)harbourChunk.field_9181, (int)harbourChunk.field_9180);
        LinkedHashSet<Long> seenChunks = new LinkedHashSet<Long>();
        for (class_243 point : route.worldPoints()) {
            int chunkDistance;
            class_1923 chunk = new class_1923(class_2338.method_49638((class_2374)point));
            long chunkKey = class_1923.method_8331((int)chunk.field_9181, (int)chunk.field_9180);
            if (!seenChunks.add(chunkKey) || chunkKey == harbourKey || (chunkDistance = Math.max(Math.abs(chunk.field_9181 - harbourChunk.field_9181), Math.abs(chunk.field_9180 - harbourChunk.field_9180))) < 4) continue;
            if (GotCastles.isUsableVoyageWaterChunk(world, chunk)) {
                return chunk;
            }
            if (chunkDistance <= 16) continue;
            break;
        }
        return null;
    }

    @Nullable
    private static class_243 preferredVoyageSpawnDirection(class_1923 harbourChunk, @Nullable VoyagePath route) {
        if (route == null) {
            return null;
        }
        class_243 harbourCenter = new class_243((double)harbourChunk.method_8326() + 8.0, 0.0, (double)harbourChunk.method_8328() + 8.0);
        for (class_243 point : route.worldPoints()) {
            class_243 delta = new class_243(point.field_1352 - harbourCenter.field_1352, 0.0, point.field_1350 - harbourCenter.field_1350);
            if (delta.method_1027() < 64.0) continue;
            return delta.method_1029();
        }
        return null;
    }

    private static boolean isUsableVoyageWaterChunk(class_3218 world, class_1923 chunkPos) {
        if (world == null || !world.method_8393(chunkPos.field_9181, chunkPos.field_9180) || !GotCastles.isWaterMapChunk(chunkPos)) {
            return false;
        }
        int seaLevel = world.method_8615();
        for (int sampleX = chunkPos.method_8326() + 2; sampleX <= chunkPos.method_8326() + 14; sampleX += 4) {
            for (int sampleZ = chunkPos.method_8328() + 2; sampleZ <= chunkPos.method_8328() + 14; sampleZ += 4) {
                class_2338 waterPos = new class_2338(sampleX, seaLevel, sampleZ);
                if (!world.method_8316(waterPos).method_15767(class_3486.field_15517)) {
                    return false;
                }
                if (!world.method_8320(waterPos.method_10084()).method_26215()) {
                    return false;
                }
                if (!(GotCastles.sampleSurfaceY(world, sampleX, sampleZ) > (double)seaLevel + 1.0)) continue;
                return false;
            }
        }
        return true;
    }

    private static int countOpenSeaChunks(class_1923 start, int dx, int dz, int maxDistance) {
        int count = 0;
        for (int step = 1; step <= maxDistance && GotCastles.isWaterMapChunk(new class_1923(start.field_9181 + dx * step, start.field_9180 + dz * step)); ++step) {
            ++count;
        }
        return count;
    }

    private static String voyageTag(String voyageId) {
        return VOYAGE_TAG_PREFIX + voyageId.replace("-", "");
    }

    private static void syncVoyageState(class_3222 player, boolean active, String voyageId) {
        if (player == null) {
            return;
        }
        ServerPlayNetworking.send((class_3222)player, (class_8710)new VoyageStatePayload(active, active ? voyageId : ""));
    }

    private static void syncVoyageState(class_3222 player, boolean active, String voyageId, PacketSender sender) {
        if (player == null) {
            return;
        }
        sender.sendPacket((class_8710)new VoyageStatePayload(active, active ? voyageId : ""));
    }

    public static boolean isActiveLandTravelDriver(AbstractKingdomCartRiderNpcEntity driver) {
        if (driver == null) {
            return false;
        }
        UUID driverUuid = driver.method_5667();
        for (ActiveLandTravelSession session : ACTIVE_LAND_TRAVELS.values()) {
            if (!driverUuid.equals(session.driverUuid())) continue;
            return true;
        }
        return false;
    }

    @Nullable
    private static ActiveVoyageSession resolveActiveVoyageSession(class_3222 player) {
        ActiveVoyageSession session = ACTIVE_VOYAGES.get(player.method_5667());
        if (session != null) {
            return session;
        }
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return null;
        }
        GotCastles.hydrateActiveTravelSessions(world);
        session = ACTIVE_VOYAGES.get(player.method_5667());
        if (session != null) {
            return session;
        }
        VoyageWorldState.PersistedVoyageSession persisted = VoyageWorldState.get(world).sessions().get(player.method_5667());
        if (persisted == null) {
            return null;
        }
        session = ActiveVoyageSession.fromPersisted((class_5321<class_1937>)world.method_27983(), persisted);
        ACTIVE_VOYAGES.put(player.method_5667(), session);
        return session;
    }

    @Nullable
    private static ActiveLandTravelSession resolveActiveLandTravelSession(class_3222 player) {
        ActiveLandTravelSession session = ACTIVE_LAND_TRAVELS.get(player.method_5667());
        if (session != null) {
            return session;
        }
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return null;
        }
        GotCastles.hydrateActiveTravelSessions(world);
        return ACTIVE_LAND_TRAVELS.get(player.method_5667());
    }

    private static boolean cancelVoyageFromTrackedShip(class_3222 player) {
        ShipEntity ship;
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            GotCastles.syncVoyageState(player, false, "");
            return false;
        }
        class_1297 class_12972 = player.method_5854();
        if (!(class_12972 instanceof ShipEntity && (ship = (ShipEntity)class_12972).isVoyageMode() && player.method_5667().equals(ship.getVoyagePlayerUuid()))) {
            GotCastles.syncVoyageState(player, false, "");
            return false;
        }
        ship.setVoyageMode(null, null);
        class_1297 captain = GotCastles.resolveVoyageCaptainEntity(world, (class_1297)ship, ship.getVoyageCaptainUuid());
        if (captain != null && !captain.method_31481()) {
            captain.method_31472();
        }
        player.method_5848();
        ship.method_31472();
        GotCastles.removePersistedVoyage(world, player.method_5667());
        GotCastles.syncVoyageState(player, false, "");
        return true;
    }

    private static void hydrateActiveTravelSessions(class_3218 world) {
        if (!HYDRATED_TRAVEL_WORLDS.add(world)) {
            return;
        }
        VoyageWorldState state = VoyageWorldState.get(world);
        for (VoyageWorldState.PersistedVoyageSession persistedVoyageSession : state.sessions().values()) {
            ACTIVE_VOYAGES.putIfAbsent(persistedVoyageSession.playerUuid(), ActiveVoyageSession.fromPersisted((class_5321<class_1937>)world.method_27983(), persistedVoyageSession));
        }
        for (VoyageWorldState.PersistedLandTravelSession persistedLandTravelSession : state.landSessions().values()) {
            ACTIVE_LAND_TRAVELS.putIfAbsent(persistedLandTravelSession.playerUuid(), ActiveLandTravelSession.fromPersisted((class_5321<class_1937>)world.method_27983(), persistedLandTravelSession));
        }
    }

    private static void checkpointActiveVoyage(class_3218 world, ActiveVoyageSession session, @Nullable class_1297 ship, boolean force) {
        class_243 shipPos = ship == null || ship.method_31481() ? null : ship.method_19538();
        long worldTime = world.method_8510();
        if (!force && !session.shouldCheckpoint(worldTime, shipPos, 200L, 64.0)) {
            return;
        }
        VoyageWorldState.get(world).put(session.toPersisted());
        session.markCheckpoint(worldTime, shipPos);
    }

    private static void persistActiveLandTravel(class_3218 world, ActiveLandTravelSession session) {
        VoyageWorldState.get(world).putLand(session.toPersisted());
    }

    private static void removePersistedVoyage(class_3218 world, UUID playerUuid) {
        VoyageWorldState.get(world).remove(playerUuid);
    }

    private static void removePersistedLandTravel(class_3218 world, UUID playerUuid) {
        VoyageWorldState.get(world).removeLand(playerUuid);
    }

    private static void checkpointActiveTravelSessions(MinecraftServer server) {
        class_3218 world;
        for (ActiveVoyageSession activeVoyageSession : ACTIVE_VOYAGES.values()) {
            world = server.method_3847(activeVoyageSession.dimension());
            if (world == null) continue;
            GotCastles.checkpointActiveVoyage(world, activeVoyageSession, GotCastles.findEntityByUuid(world, activeVoyageSession.shipUuid()), true);
        }
        for (ActiveLandTravelSession activeLandTravelSession : ACTIVE_LAND_TRAVELS.values()) {
            world = server.method_3847(activeLandTravelSession.dimension());
            if (world == null) continue;
            GotCastles.persistActiveLandTravel(world, activeLandTravelSession);
        }
    }

    private static void clearRuntimeWorldState(class_3218 world) {
        LEGACY_GATE_DISCOVERY.remove(world);
        HYDRATED_TRAVEL_WORLDS.remove(world);
        class_5321 dimension = world.method_27983();
        ACTIVE_VOYAGES.values().removeIf(session -> session.dimension().equals((Object)dimension));
        ACTIVE_LAND_TRAVELS.values().removeIf(session -> session.dimension().equals((Object)dimension));
    }

    private static void clearRuntimeTravelState() {
        ACTIVE_VOYAGES.clear();
        ACTIVE_LAND_TRAVELS.clear();
        HYDRATED_TRAVEL_WORLDS.clear();
        LEGACY_GATE_DISCOVERY.clear();
        VOYAGE_CONTROL_LOG_COUNTS.clear();
        RECONCILED_PLAYER_CLAIM_SERVERS.clear();
    }

    private static void restoreVoyageOnJoin(class_3218 world, class_3222 player, PacketSender sender) {
        AbstractKingdomCaptainNpcEntity captainNpc;
        ShipEntity voyageShip;
        ActiveVoyageSession session;
        block7: {
            block6: {
                session = GotCastles.resolveActiveVoyageSession(player);
                if (session == null) {
                    GotCastles.syncVoyageState(player, false, "", sender);
                    return;
                }
                class_1297 ship = GotCastles.findEntityByUuid(world, session.shipUuid());
                class_1297 captain = GotCastles.findEntityByUuid(world, session.captainUuid());
                if (ship == null || captain == null) {
                    session.missingEntityGraceExpired(world.method_8510(), 200L);
                    GotCastles.syncVoyageState(player, true, session.voyageId(), sender);
                    return;
                }
                if (!(ship instanceof ShipEntity)) break block6;
                voyageShip = (ShipEntity)ship;
                if (!(captain instanceof AbstractKingdomCaptainNpcEntity)) break block6;
                captainNpc = (AbstractKingdomCaptainNpcEntity)captain;
                if (!ship.method_31481() && !captain.method_31481()) break block7;
            }
            ACTIVE_VOYAGES.remove(player.method_5667());
            GotCastles.removePersistedVoyage(world, player.method_5667());
            GotCastles.syncVoyageState(player, false, "", sender);
            return;
        }
        player.method_48105(world, voyageShip.method_23317(), voyageShip.method_23318() + 1.0, voyageShip.method_23321(), Set.of(), voyageShip.method_36454(), player.method_36455());
        if (!GotCastles.ensureVoyagePassengers((class_1297)voyageShip, (class_1297)captainNpc, player, player.method_5667())) {
            GotCastles.syncVoyageState(player, true, session.voyageId(), sender);
            return;
        }
        GotCastles.syncVoyageState(player, true, session.voyageId(), sender);
    }

    private static void restoreLandTravelOnJoin(class_3218 world, class_3222 player, PacketSender sender) {
        class_1496 horseEntity2;
        AbstractKingdomCartRiderNpcEntity driverEntity;
        class_1496 horseEntity;
        class_1297 secondaryHorse;
        class_1297 cart;
        ActiveLandTravelSession session;
        block12: {
            block11: {
                session = GotCastles.resolveActiveLandTravelSession(player);
                if (session == null) {
                    return;
                }
                cart = GotCastles.findEntityByUuid(world, session.cartUuid());
                class_1297 horse = GotCastles.findEntityByUuid(world, session.horseUuid());
                secondaryHorse = session.secondaryHorseUuid() == null ? null : GotCastles.findEntityByUuid(world, session.secondaryHorseUuid());
                class_1297 driver = GotCastles.findEntityByUuid(world, session.driverUuid());
                if (cart == null || horse == null || session.secondaryHorseUuid() != null && secondaryHorse == null || driver == null) {
                    session.missingEntityGraceExpired(world.method_8510(), 200L);
                    GotCastles.syncVoyageState(player, true, session.voyageId(), sender);
                    return;
                }
                if (!GotCastles.isLandTravelCartEntity(cart) || !(horse instanceof class_1496)) break block11;
                horseEntity = (class_1496)horse;
                if (session.secondaryHorseUuid() != null && !(secondaryHorse instanceof class_1496) || !(driver instanceof AbstractKingdomCartRiderNpcEntity)) break block11;
                driverEntity = (AbstractKingdomCartRiderNpcEntity)driver;
                if (!cart.method_31481() && !horse.method_31481() && (secondaryHorse == null || !secondaryHorse.method_31481()) && !driver.method_31481()) break block12;
            }
            ACTIVE_LAND_TRAVELS.remove(player.method_5667());
            GotCastles.removePersistedLandTravel(world, player.method_5667());
            GotCastles.syncVoyageState(player, false, "", sender);
            return;
        }
        class_1496 secondaryHorseEntity = secondaryHorse instanceof class_1496 ? (horseEntity2 = (class_1496)secondaryHorse) : null;
        GotCastles.cleanupDanglingLandTravelEntities(world, session, cart, horseEntity, secondaryHorseEntity, driverEntity);
        player.method_48105(world, cart.method_23317(), cart.method_23318() + 1.0, cart.method_23321(), Set.of(), cart.method_36454(), player.method_36455());
        if (cart instanceof CartEntity) {
            if (driverEntity.method_5854() != horseEntity) {
                driverEntity.method_5873((class_1297)horseEntity, true);
            }
        } else if (driverEntity.method_5854() != cart) {
            driverEntity.method_5873(cart, true);
        }
        if (player.method_5854() != cart) {
            player.method_5873(cart, true);
        }
        GotCastles.syncVoyageState(player, true, session.voyageId(), sender);
    }

    private static boolean isLandTravelCartEntity(@Nullable class_1297 entity) {
        return entity instanceof CartEntity || entity instanceof TwoHorseCartEntity;
    }

    @Nullable
    private static class_1297 findEntityByUuid(class_3218 world, UUID uuid) {
        if (world == null || uuid == null) {
            return null;
        }
        return world.method_14190(uuid);
    }

    @Nullable
    private static class_1297 resolveVoyageCaptainEntity(class_3218 world, @Nullable class_1297 shipEntity, @Nullable UUID preferredCaptainUuid) {
        class_1297 captain = GotCastles.findEntityByUuid(world, preferredCaptainUuid);
        if (captain != null) {
            return captain;
        }
        if (!(shipEntity instanceof ShipEntity)) {
            return null;
        }
        ShipEntity ship = (ShipEntity)shipEntity;
        UUID trackedCaptainUuid = ship.getVoyageCaptainUuid();
        if (trackedCaptainUuid != null && !trackedCaptainUuid.equals(preferredCaptainUuid) && (captain = GotCastles.findEntityByUuid(world, trackedCaptainUuid)) != null) {
            return captain;
        }
        for (class_1297 passenger : ship.method_5685()) {
            if (!(passenger instanceof AbstractKingdomCaptainNpcEntity)) continue;
            return passenger;
        }
        return null;
    }

    private static class_1923 findAdjacentWaterChunk(class_1923 shorelineChunk) {
        return GotCastles.findAdjacentWaterChunk(shorelineChunk, null);
    }

    private static class_1923 findAdjacentWaterChunk(class_1923 shorelineChunk, @Nullable ChunkExclusion forbiddenArea) {
        return GotCastles.findAdjacentWaterChunk(shorelineChunk, forbiddenArea, 0);
    }

    private static class_1923 findAdjacentWaterChunk(class_1923 shorelineChunk, @Nullable ChunkExclusion forbiddenArea, int forbiddenBufferChunks) {
        for (class_1923 neighbor : List.of(new class_1923(shorelineChunk.field_9181 + 1, shorelineChunk.field_9180), new class_1923(shorelineChunk.field_9181 - 1, shorelineChunk.field_9180), new class_1923(shorelineChunk.field_9181, shorelineChunk.field_9180 + 1), new class_1923(shorelineChunk.field_9181, shorelineChunk.field_9180 - 1))) {
            if (!GotCastles.isWaterMapChunk(neighbor) || GotCastles.isNearForbiddenArea(neighbor, forbiddenArea, forbiddenBufferChunks)) continue;
            return neighbor;
        }
        return null;
    }

    private static class_1923 findNearbyHarbourWaterChunk(class_2338 bannerPos, List<class_1923> candidateChunks, @Nullable ChunkExclusion forbiddenArea, int claimRadiusChunks) {
        LinkedHashSet<Long> testedWaterChunks = new LinkedHashSet<Long>();
        ArrayList<class_1923> waterChunks = new ArrayList<class_1923>();
        for (class_1923 candidateChunk : candidateChunks) {
            for (class_1923 neighbor : List.of(new class_1923(candidateChunk.field_9181 + 1, candidateChunk.field_9180), new class_1923(candidateChunk.field_9181 - 1, candidateChunk.field_9180), new class_1923(candidateChunk.field_9181, candidateChunk.field_9180 + 1), new class_1923(candidateChunk.field_9181, candidateChunk.field_9180 - 1))) {
                if (!testedWaterChunks.add(class_1923.method_8331((int)neighbor.field_9181, (int)neighbor.field_9180)) || !GotCastles.isWaterMapChunk(neighbor) || GotCastles.isNearForbiddenArea(neighbor, forbiddenArea, 2) || !GotCastles.isWithinClaimRadius(neighbor, bannerPos, claimRadiusChunks) || !GotCastles.hasAdjacentLandChunk(neighbor)) continue;
                waterChunks.add(neighbor);
            }
        }
        if (waterChunks.isEmpty()) {
            return null;
        }
        waterChunks.sort(Comparator.comparingDouble(chunk -> GotCastles.squaredDistanceToChunkCenter(chunk, bannerPos)));
        return (class_1923)waterChunks.getFirst();
    }

    private static boolean isWithinClaimRadius(class_1923 chunkPos, class_2338 bannerPos, int claimRadiusChunks) {
        int centerChunkX = bannerPos.method_10263() >> 4;
        int centerChunkZ = bannerPos.method_10260() >> 4;
        return Math.abs(chunkPos.field_9181 - centerChunkX) <= claimRadiusChunks && Math.abs(chunkPos.field_9180 - centerChunkZ) <= claimRadiusChunks;
    }

    private static boolean hasAdjacentLandChunk(class_1923 waterChunk) {
        return GotCastles.hasAdjacentLandChunk(waterChunk, null, 0);
    }

    private static boolean hasAdjacentLandChunk(class_1923 waterChunk, @Nullable ChunkExclusion forbiddenArea, int forbiddenBufferChunks) {
        for (class_1923 neighbor : List.of(new class_1923(waterChunk.field_9181 + 1, waterChunk.field_9180), new class_1923(waterChunk.field_9181 - 1, waterChunk.field_9180), new class_1923(waterChunk.field_9181, waterChunk.field_9180 + 1), new class_1923(waterChunk.field_9181, waterChunk.field_9180 - 1))) {
            if (GotCastles.isWaterMapChunk(neighbor) || GotCastles.isNearForbiddenArea(neighbor, forbiddenArea, forbiddenBufferChunks)) continue;
            return true;
        }
        return false;
    }

    private static boolean isNearForbiddenArea(class_1923 chunkPos, @Nullable ChunkExclusion forbiddenArea, int radiusChunks) {
        if (forbiddenArea == null) {
            return false;
        }
        int radius = Math.max(0, radiusChunks);
        for (int offsetX = -radius; offsetX <= radius; ++offsetX) {
            for (int offsetZ = -radius; offsetZ <= radius; ++offsetZ) {
                if (!forbiddenArea.contains(new class_1923(chunkPos.field_9181 + offsetX, chunkPos.field_9180 + offsetZ))) continue;
                return true;
            }
        }
        return false;
    }

    private static boolean isWaterAdjacentLandChunk(class_1923 chunkPos) {
        if (GotCastles.isWaterMapChunk(chunkPos)) {
            return false;
        }
        for (class_1923 neighbor : List.of(new class_1923(chunkPos.field_9181 + 1, chunkPos.field_9180), new class_1923(chunkPos.field_9181 - 1, chunkPos.field_9180), new class_1923(chunkPos.field_9181, chunkPos.field_9180 + 1), new class_1923(chunkPos.field_9181, chunkPos.field_9180 - 1))) {
            if (!GotCastles.isWaterMapChunk(neighbor)) continue;
            return true;
        }
        return false;
    }

    @Nullable
    private static DynamicStoneCastleTierConfig dynamicStoneCastleConfig(CastleTier tier) {
        return switch (tier) {
            default -> throw new MatchException(null, null);
            case CastleTier.SETTLEMENT -> new DynamicStoneCastleTierConfig(DYNAMIC_STONE_SETTLEMENT_TEMPLATE_ID, CastleTier.SETTLEMENT);
            case CastleTier.LORDSHIP -> new DynamicStoneCastleTierConfig(DYNAMIC_STONE_LORDSHIP_TEMPLATE_ID, CastleTier.LORDSHIP);
            case CastleTier.CAPITAL -> new DynamicStoneCastleTierConfig(DYNAMIC_STONE_CAPITAL_TEMPLATE_ID, CastleTier.CAPITAL);
        };
    }

    private static class_2382 dynamicStoneFootprintSize(DynamicStoneCastleTierConfig config) {
        int footprintBlocks = CastleCityTierRegistry.forTier(config.tier()).flattenFootprintChunks() * 16;
        return new class_2382(footprintBlocks, 12, footprintBlocks);
    }

    @Nullable
    private static StructurePlacementResult placeClaimStructure(class_3218 world, class_3485 templateManager, class_1923 chunkPos, String structurePath, @Nullable String kingdomId) {
        class_2960 structureId = class_2960.method_60655((String)"agob", (String)structurePath);
        class_3499 template = templateManager.method_15094(structureId).orElse(null);
        if (template == null) {
            GotMod.LOGGER.warn("Missing claim structure template {}", (Object)structureId);
            return null;
        }
        class_2338 origin = GotCastles.findChunkStructureOrigin(world, chunkPos, template.method_15160());
        StructureSurfaceStats surfaceStats = GotCastles.sampleStructureSurface(world, origin, template.method_15160());
        if (!GotCastles.isValidClaimStructureFootprint(world, origin, template.method_15160(), surfaceStats, origin.method_10264() - 1)) {
            GotMod.LOGGER.debug("Skipping claim structure {} in chunk {} due to uneven terrain", (Object)structureId, (Object)chunkPos);
            return null;
        }
        int floorY = GotCastles.resolveStructureFloorY(surfaceStats);
        origin = new class_2338(origin.method_10263(), floorY + 1, origin.method_10260());
        GotCastles.prepareStructurePad(world, origin, template.method_15160(), floorY, 4, kingdomId);
        class_3492 placement = new class_3492().method_15133(false).method_15131(true).method_27264(false);
        if (!template.method_15172((class_5425)world, origin, origin, placement, world.field_9229, 3)) {
            GotMod.LOGGER.warn("Failed to place claim structure {} in chunk {}", (Object)structureId, (Object)chunkPos);
            return null;
        }
        GotCastles.stabilizeStructureFooting(world, origin, template.method_15160(), origin.method_10264());
        class_3341 footprint = new class_3341(origin.method_10263(), origin.method_10264(), origin.method_10260(), origin.method_10263() + template.method_15160().method_10263() - 1, origin.method_10264() + 18, origin.method_10260() + template.method_15160().method_10260() - 1);
        return GotCastles.createStructurePlacementResult(world, footprint, origin, template.method_15160());
    }

    @Nullable
    private static StructurePlacementResult placeClaimStructureAtFloor(class_3218 world, class_3485 templateManager, class_1923 chunkPos, String structurePath, @Nullable String kingdomId, int floorY) {
        class_2960 structureId = class_2960.method_60655((String)"agob", (String)structurePath);
        class_3499 template = templateManager.method_15094(structureId).orElse(null);
        if (template == null) {
            GotMod.LOGGER.warn("Missing claim structure template {}", (Object)structureId);
            return null;
        }
        class_2338 origin = GotCastles.findChunkStructureOriginAtY(chunkPos, template.method_15160(), floorY + 1);
        GotCastles.prepareStructurePad(world, origin, template.method_15160(), floorY, 4, kingdomId);
        class_3492 placement = new class_3492().method_15133(false).method_15131(true).method_27264(false);
        if (!template.method_15172((class_5425)world, origin, origin, placement, world.field_9229, 3)) {
            GotMod.LOGGER.warn("Failed to place claim structure {} in chunk {}", (Object)structureId, (Object)chunkPos);
            return null;
        }
        GotCastles.stabilizeStructureFooting(world, origin, template.method_15160(), origin.method_10264());
        class_3341 footprint = new class_3341(origin.method_10263(), origin.method_10264(), origin.method_10260(), origin.method_10263() + template.method_15160().method_10263() - 1, origin.method_10264() + 18, origin.method_10260() + template.method_15160().method_10260() - 1);
        return GotCastles.createStructurePlacementResult(world, footprint, origin, template.method_15160());
    }

    @Nullable
    private static StructurePlacementResult placeHarbourStructure(class_3218 world, class_3485 templateManager, class_1923 waterChunk, String structurePath, class_2338 bannerPos, String kingdomId, int floorY) {
        class_2960 structureId = class_2960.method_60655((String)"agob", (String)structurePath);
        class_3499 template = templateManager.method_15094(structureId).orElse(null);
        if (template == null) {
            GotMod.LOGGER.warn("Missing harbour structure template {}", (Object)structureId);
            return null;
        }
        class_2338 origin = GotCastles.findChunkStructureOriginAtY(waterChunk, template.method_15160(), floorY + 1);
        SurfacePalette palette = GotCastles.resolveKingdomSurfacePalette(world, bannerPos, kingdomId);
        GotCastles.prepareHarbourChunkPad(world, waterChunk, template.method_15160(), floorY, palette, GotCastles.resolvePreferredHarbourShoreChunk(waterChunk, bannerPos), bannerPos);
        GotCastles.prepareStructurePadWithPalette(world, origin, template.method_15160(), floorY, 4, palette);
        class_3492 placement = new class_3492().method_15133(false).method_15131(true).method_27264(false);
        if (!template.method_15172((class_5425)world, origin, origin, placement, world.field_9229, 3)) {
            GotMod.LOGGER.warn("Failed to place harbour structure {} in chunk {}", (Object)structureId, (Object)waterChunk);
            return null;
        }
        GotCastles.stabilizeStructureFootingWithPalette(world, origin, template.method_15160(), origin.method_10264(), palette);
        class_3341 footprint = new class_3341(origin.method_10263(), origin.method_10264(), origin.method_10260(), origin.method_10263() + template.method_15160().method_10263() - 1, origin.method_10264() + 18, origin.method_10260() + template.method_15160().method_10260() - 1);
        return GotCastles.createStructurePlacementResult(world, footprint, origin, template.method_15160());
    }

    private static class_2338 findChunkStructureOrigin(class_3218 world, class_1923 chunkPos, class_2382 templateSize) {
        return CastleStructurePlacementHelper.findChunkStructureOrigin(world, chunkPos, templateSize);
    }

    private static class_2338 findChunkStructureOriginAtY(class_1923 chunkPos, class_2382 templateSize, int y) {
        return CastleStructurePlacementHelper.findChunkStructureOriginAtY(chunkPos, templateSize, y);
    }

    private static StructureSurfaceStats sampleStructureSurface(class_3218 world, class_2338 origin, class_2382 size) {
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        int totalY = 0;
        int sampleCount = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        ArrayList<Integer> heights = new ArrayList<Integer>();
        for (int x = minX; x <= maxX; x += Math.max(1, size.method_10263() / 4)) {
            for (int z = minZ; z <= maxZ; z += Math.max(1, size.method_10260() / 4)) {
                int surfaceY = world.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
                totalY += surfaceY;
                ++sampleCount;
                minY = Math.min(minY, surfaceY);
                maxY = Math.max(maxY, surfaceY);
                heights.add(surfaceY);
            }
        }
        if (sampleCount == 0) {
            return new StructureSurfaceStats(origin.method_10264(), origin.method_10264(), origin.method_10264(), List.of());
        }
        int averageY = Math.round((float)totalY / (float)sampleCount);
        return new StructureSurfaceStats(averageY, minY, maxY, List.copyOf(heights));
    }

    private static boolean isValidClaimStructureFootprint(class_3218 world, class_2338 origin, class_2382 size, StructureSurfaceStats surfaceStats, int anchorY) {
        if (surfaceStats.heights().isEmpty()) {
            return false;
        }
        int terrainDeltaLimit = 10;
        int floorOffsetLimit = 4;
        if (GotCastles.isSnowyClaimStructureArea(world, origin, size)) {
            terrainDeltaLimit = 20;
            floorOffsetLimit = 18;
        }
        if (surfaceStats.maxY() - surfaceStats.minY() > terrainDeltaLimit) {
            return false;
        }
        int floorY = GotCastles.resolveStructureFloorY(surfaceStats);
        return Math.abs(floorY - anchorY) <= floorOffsetLimit;
    }

    private static boolean isSnowyClaimStructureArea(class_3218 world, class_2338 origin, class_2382 size) {
        if (world == null || origin == null || size == null) {
            return false;
        }
        int centerX = origin.method_10263() + Math.max(0, size.method_10263() / 2);
        int centerZ = origin.method_10260() + Math.max(0, size.method_10260() / 2);
        String biomePath = world.method_23753(new class_2338(centerX, origin.method_10264(), centerZ)).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        return biomePath.contains("always_winter") || biomePath.contains("wildling") || biomePath.contains("snow") || biomePath.contains("frozen") || biomePath.contains("the_wall");
    }

    private static int resolveStructureFloorY(StructureSurfaceStats surfaceStats) {
        ArrayList<Integer> heights = new ArrayList<Integer>(surfaceStats.heights());
        if (heights.isEmpty()) {
            return surfaceStats.averageY();
        }
        Collections.sort(heights);
        int median = GotCastles.percentile(heights, 0.5);
        int upperQuartile = GotCastles.percentile(heights, 0.75);
        int floorY = Math.round((float)(median + upperQuartile) / 2.0f);
        return class_3532.method_15340((int)floorY, (int)median, (int)surfaceStats.maxY());
    }

    private static void prepareStructurePad(class_3218 world, class_2338 origin, class_2382 size, int floorY, int blendRadius) {
        GotCastles.prepareStructurePad(world, origin, size, floorY, blendRadius, null);
    }

    private static void prepareStructurePad(class_3218 world, class_2338 origin, class_2382 size, int floorY, int blendRadius, @Nullable String kingdomId) {
        GotCastles.prepareStructurePadWithSurfaceStyle(world, origin, size, floorY, blendRadius, null, GotCastles.resolvePlatformSurfaceStyle(world, origin, kingdomId));
    }

    private static void prepareStructurePadWithPalette(class_3218 world, class_2338 origin, class_2382 size, int floorY, int blendRadius, SurfacePalette forcedPalette) {
        GotCastles.prepareStructurePadWithSurfaceStyle(world, origin, size, floorY, blendRadius, forcedPalette, PlatformSurfaceStyle.FORCED);
    }

    private static void prepareStructurePadWithSurfaceStyle(class_3218 world, class_2338 origin, class_2382 size, int floorY, int blendRadius, @Nullable SurfacePalette forcedPalette, PlatformSurfaceStyle surfaceStyle) {
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        for (int x = minX - blendRadius; x <= maxX + blendRadius; ++x) {
            for (int z = minZ - blendRadius; z <= maxZ + blendRadius; ++z) {
                int y;
                int clearTop;
                int distance = GotCastles.outsideDistance(x, z, minX, maxX, minZ, maxZ);
                if (distance > blendRadius) continue;
                int currentSurfaceY = world.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
                float alpha = distance == 0 ? 1.0f : 1.0f - (float)distance / (float)(blendRadius + 1);
                int desiredY = Math.round(class_3532.method_48781((float)alpha, (int)currentSurfaceY, (int)floorY));
                int targetY = Math.max(desiredY, currentSurfaceY - 3);
                SurfacePalette palette = forcedPalette != null ? forcedPalette : GotCastles.paletteForSurfaceStyle(surfaceStyle, GotCastles.resolveSurfacePalette(world, x, z));
                class_2680 topSurface = forcedPalette != null ? palette.surface() : GotCastles.platformSurfaceState(surfaceStyle, palette, origin, x, z);
                for (int y2 = clearTop = Math.max(currentSurfaceY + 6, targetY + 6); y2 > targetY; --y2) {
                    world.method_8652(new class_2338(x, y2, z), class_2246.field_10124.method_9564(), 2);
                }
                world.method_8652(new class_2338(x, targetY, z), topSurface, 2);
                int substrateDepth = surfaceStyle == PlatformSurfaceStyle.DESERT ? 3 : 2;
                for (int depth = 1; depth <= substrateDepth && (y = targetY - depth) > world.method_31607(); ++depth) {
                    world.method_8652(new class_2338(x, y, z), palette.subsurface(), 50);
                }
                GotCastles.fillSupportColumn(world, x, z, targetY - substrateDepth - 1, palette);
                GotCastles.decoratePlatformSurface(world, origin, x, targetY, z, surfaceStyle, topSurface, distance);
            }
        }
    }

    private static void stabilizeStructureFooting(class_3218 world, class_2338 origin, class_2382 size, int floorY) {
        GotCastles.stabilizeStructureFootingWithPalette(world, origin, size, floorY, null);
    }

    private static void stabilizeStructureFootingWithPalette(class_3218 world, class_2338 origin, class_2382 size, int floorY, SurfacePalette forcedPalette) {
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        for (int x = minX; x <= maxX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                SurfacePalette palette = forcedPalette != null ? forcedPalette : GotCastles.resolveSurfacePalette(world, x, z);
                class_2338.class_2339 mutable = new class_2338.class_2339(x, floorY - 1, z);
                while (mutable.method_10264() > world.method_31607() && GotCastles.needsCastleSupportFill(world.method_8320((class_2338)mutable))) {
                    world.method_8652((class_2338)mutable, palette.subsurface(), 2);
                    mutable.method_10100(0, -1, 0);
                }
            }
        }
    }

    private static void fillSupportColumn(class_3218 world, int x, int z, int startY, SurfacePalette palette) {
        int depth = 0;
        class_2338.class_2339 mutable = new class_2338.class_2339();
        for (int y = startY; y > world.method_31607(); --y) {
            mutable.method_10103(x, y, z);
            if (!GotCastles.needsCastleSupportFill(world.method_8320((class_2338)mutable))) break;
            world.method_8652((class_2338)mutable, depth < 2 ? palette.subsurface() : palette.deep(), 50);
            ++depth;
        }
    }

    private static void fillSupportColumnToY(class_3218 world, int x, int z, int startY, int stopYInclusive, SurfacePalette palette) {
        if (startY < stopYInclusive) {
            return;
        }
        int depth = 0;
        class_2338.class_2339 mutable = new class_2338.class_2339();
        for (int y = startY; y >= stopYInclusive && y > world.method_31607(); --y) {
            mutable.method_10103(x, y, z);
            if (!GotCastles.needsCastleSupportFill(world.method_8320((class_2338)mutable))) continue;
            world.method_8652((class_2338)mutable, depth < 2 ? palette.subsurface() : palette.deep(), 50);
            ++depth;
        }
    }

    private static int resolveLocalSupportBaseY(class_3218 world, int x, int z, int currentSurfaceY) {
        return currentSurfaceY;
    }

    private static boolean needsCastleSupportFill(class_2680 state) {
        return state.method_26215() || !state.method_26227().method_15769();
    }

    private static void prepareHarbourChunkPad(class_3218 world, class_1923 waterChunk, int floorY, SurfacePalette palette) {
        GotCastles.prepareHarbourChunkPad(world, waterChunk, new class_2382(8, 1, 8), floorY, palette, null, class_2338.field_10980);
    }

    private static void prepareHarbourChunkPad(class_3218 world, class_1923 waterChunk, class_2382 structureSize, int floorY, SurfacePalette palette, @Nullable class_1923 shorelineChunk, class_2338 bannerPos) {
        class_2338 structureOrigin = GotCastles.findChunkStructureOriginAtY(waterChunk, structureSize, floorY + 1);
        double centerX = (double)structureOrigin.method_10263() + (double)structureSize.method_10263() / 2.0;
        double centerZ = (double)structureOrigin.method_10260() + (double)structureSize.method_10260() / 2.0;
        double shoreX = shorelineChunk != null ? (double)shorelineChunk.method_8326() + 8.0 : (double)waterChunk.method_8326() + 8.0;
        double shoreZ = shorelineChunk != null ? (double)shorelineChunk.method_8328() + 8.0 : (double)waterChunk.method_8328() + 8.0;
        double platformRadiusX = (double)structureSize.method_10263() * 0.95 + 13.0;
        double platformRadiusZ = (double)structureSize.method_10260() * 0.95 + 13.0;
        double peninsulaWidthAtShore = Math.max(18.0, (double)Math.min(structureSize.method_10263(), structureSize.method_10260()) * 0.8 + 10.0);
        double peninsulaWidthAtStructure = peninsulaWidthAtShore + 8.0;
        int minX = Math.min(waterChunk.method_8326(), shorelineChunk != null ? shorelineChunk.method_8326() : waterChunk.method_8326()) - 14;
        int maxX = Math.max(waterChunk.method_8327(), shorelineChunk != null ? shorelineChunk.method_8327() : waterChunk.method_8327()) + 14;
        int minZ = Math.min(waterChunk.method_8328(), shorelineChunk != null ? shorelineChunk.method_8328() : waterChunk.method_8328()) - 14;
        int maxZ = Math.max(waterChunk.method_8329(), shorelineChunk != null ? shorelineChunk.method_8329() : waterChunk.method_8329()) + 14;
        for (int x = minX; x <= maxX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                int y;
                boolean mapWater = GotCastles.isWaterMapPixel(GotCastles.worldToMapPixel(x), GotCastles.worldToMapPixel(z));
                int seabedY = world.method_8624(class_2902.class_2903.field_13200, x, z) - 1;
                int currentSurfaceY = world.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
                double sampleX = (double)x + 0.5;
                double sampleZ = (double)z + 0.5;
                double waterChunkFill = GotCastles.smoothstep(1.0 - class_3532.method_15350((double)(GotCastles.distanceToExpandedChunk(waterChunk, sampleX, sampleZ, 8.0) / 18.0), (double)0.0, (double)1.0));
                double footprintFill = GotCastles.harbourFootprintFactor(sampleX, sampleZ, structureOrigin, structureSize, 10);
                double platformFactor = GotCastles.harbourPlatformFactor(sampleX, sampleZ, centerX, centerZ, platformRadiusX, platformRadiusZ);
                double peninsulaFactor = shorelineChunk == null ? 0.0 : GotCastles.harbourPeninsulaFactor(sampleX, sampleZ, shoreX, shoreZ, centerX, centerZ, peninsulaWidthAtShore, peninsulaWidthAtStructure);
                double shorelineLift = shorelineChunk == null ? 0.0 : GotCastles.smoothstep(1.0 - class_3532.method_15350((double)(GotCastles.distanceToExpandedChunk(shorelineChunk, sampleX, sampleZ, 12.0) / 20.0), (double)0.0, (double)1.0));
                double landFactor = Math.max(Math.max(platformFactor, footprintFill), Math.max(peninsulaFactor, Math.max(shorelineLift * 0.72, waterChunkFill * 0.78)));
                if (landFactor < 0.05) continue;
                double variation = (Math.sin(((double)x + (double)bannerPos.method_10263() * 0.23) / 7.0) + Math.cos(((double)z - (double)bannerPos.method_10260() * 0.17) / 8.5) + Math.sin((double)(x - z + bannerPos.method_10263()) / 15.0)) / 3.0;
                variation = (variation + 1.0) * 0.5;
                int baseY = mapWater ? seabedY + 1 : Math.max(seabedY + 1, Math.min(currentSurfaceY, floorY - 2));
                int shorelineY = mapWater ? floorY - 2 : Math.max(currentSurfaceY, floorY - 2);
                int crestY = platformFactor > 0.72 ? floorY : floorY - 1;
                int targetY = Math.round(class_3532.method_48781((float)((float)landFactor), (int)baseY, (int)Math.max(shorelineY, crestY)));
                if (platformFactor > 0.78) {
                    targetY = Math.max(targetY, floorY);
                }
                if (footprintFill > 0.42) {
                    targetY = Math.max(targetY, floorY);
                }
                if (waterChunkFill > 0.64) {
                    targetY = Math.max(targetY, floorY - 1);
                }
                if (peninsulaFactor > 0.42) {
                    targetY = Math.max(targetY, floorY - 1);
                }
                if (variation > 0.72 && landFactor > 0.2) {
                    targetY = Math.min(floorY + (platformFactor > 0.82 ? 1 : 0), targetY + 1);
                } else if (variation < 0.18 && mapWater) {
                    targetY = Math.max(seabedY + 1, targetY - 1);
                }
                if (!mapWater) {
                    targetY = Math.max(targetY, currentSurfaceY);
                }
                if (mapWater) {
                    int clearTop;
                    for (y = clearTop = Math.max(world.method_8615() + 5, targetY + 4); y > targetY; --y) {
                        world.method_8652(new class_2338(x, y, z), class_2246.field_10124.method_9564(), 2);
                    }
                }
                class_2680 topBlock = targetY >= world.method_8615() - 1 ? palette.surface() : palette.subsurface();
                world.method_8652(new class_2338(x, targetY, z), topBlock, 2);
                GotCastles.fillSupportColumn(world, x, z, targetY - 1, palette);
                if (targetY >= world.method_8615()) continue;
                for (y = targetY + 1; y <= world.method_8615(); ++y) {
                    world.method_8652(new class_2338(x, y, z), class_2246.field_10382.method_9564(), 2);
                }
            }
        }
    }

    @Nullable
    private static class_1923 resolvePreferredHarbourShoreChunk(class_1923 waterChunk, class_2338 bannerPos) {
        ArrayList<class_1923> shorelineChunks = new ArrayList<class_1923>();
        for (class_1923 neighbor : List.of(new class_1923(waterChunk.field_9181 + 1, waterChunk.field_9180), new class_1923(waterChunk.field_9181 - 1, waterChunk.field_9180), new class_1923(waterChunk.field_9181, waterChunk.field_9180 + 1), new class_1923(waterChunk.field_9181, waterChunk.field_9180 - 1))) {
            if (GotCastles.isWaterMapChunk(neighbor)) continue;
            shorelineChunks.add(neighbor);
        }
        if (shorelineChunks.isEmpty()) {
            return null;
        }
        shorelineChunks.sort(Comparator.comparingDouble(chunk -> GotCastles.squaredDistanceToChunkCenter(chunk, bannerPos)));
        return (class_1923)shorelineChunks.getFirst();
    }

    private static double squaredDistanceToChunkCenter(class_1923 chunkPos, class_2338 pos) {
        return CastleStructurePlacementHelper.squaredDistanceToChunkCenter(chunkPos, pos);
    }

    private static double harbourPlatformFactor(double x, double z, double centerX, double centerZ, double radiusX, double radiusZ) {
        double normalizedX = (x - centerX) / radiusX;
        double normalizedZ = (z - centerZ) / radiusZ;
        double distance = Math.sqrt(normalizedX * normalizedX + normalizedZ * normalizedZ);
        if (distance >= 1.0) {
            return 0.0;
        }
        return GotCastles.smoothstep(1.0 - distance);
    }

    private static double harbourFootprintFactor(double x, double z, class_2338 origin, class_2382 size, int margin) {
        int minX = origin.method_10263() - margin;
        int maxX = origin.method_10263() + size.method_10263() - 1 + margin;
        int minZ = origin.method_10260() - margin;
        int maxZ = origin.method_10260() + size.method_10260() - 1 + margin;
        double distance = GotCastles.distanceToRect(x, z, minX, (double)maxX + 1.0, minZ, (double)maxZ + 1.0);
        double factor = 1.0 - class_3532.method_15350((double)(distance / Math.max(10.0, (double)margin + 4.0)), (double)0.0, (double)1.0);
        return GotCastles.smoothstep(factor);
    }

    private static double harbourPeninsulaFactor(double x, double z, double shoreX, double shoreZ, double centerX, double centerZ, double widthAtShore, double widthAtStructure) {
        double dx = centerX - shoreX;
        double dz = centerZ - shoreZ;
        double lengthSquared = dx * dx + dz * dz;
        if (lengthSquared < 1.0) {
            return 0.0;
        }
        double progress = class_3532.method_15350((double)(((x - shoreX) * dx + (z - shoreZ) * dz) / lengthSquared), (double)0.0, (double)1.0);
        double closestX = shoreX + dx * progress;
        double closestZ = shoreZ + dz * progress;
        double width = class_3532.method_16436((double)((float)progress), (double)widthAtShore, (double)widthAtStructure);
        double distance = Math.sqrt((x - closestX) * (x - closestX) + (z - closestZ) * (z - closestZ));
        double ribbonFactor = 1.0 - class_3532.method_15350((double)(distance / width), (double)0.0, (double)1.0);
        return GotCastles.smoothstep(ribbonFactor) * GotCastles.smoothstep(progress);
    }

    private static double distanceToExpandedChunk(class_1923 chunkPos, double x, double z, double expansion) {
        return CastleStructurePlacementHelper.distanceToExpandedChunk(chunkPos, x, z, expansion);
    }

    private static double distanceToRect(double x, double z, double minX, double maxX, double minZ, double maxZ) {
        return CastleStructurePlacementHelper.distanceToRect(x, z, minX, maxX, minZ, maxZ);
    }

    private static double smoothstep(double value) {
        return CastleStructurePlacementHelper.smoothstep(value);
    }

    private static class_2338 expandDynamicFlattenOrigin(class_2338 origin) {
        return origin.method_10069(0, 0, 0);
    }

    private static class_2382 expandDynamicFlattenSize(class_2382 size) {
        int extra = 0;
        return new class_2382(size.method_10263() + extra, size.method_10264(), size.method_10260() + extra);
    }

    private static void flattenDynamicCastleClaimSurface(class_3218 world, class_2338 origin, class_2382 size, int floorY, class_2338 bannerPos, @Nullable String kingdomId) {
        PendingCastleGeneration scratch = new PendingCastleGeneration("", "", 0, bannerPos);
        scratch.setKingdomId(kingdomId);
        while (!GotCastles.flattenDynamicCastleClaimSurfaceBatch(world, origin, size, floorY, scratch)) {
        }
    }

    private static void mergeCastlePlatformBoundary(class_3218 world, class_2338 origin, class_2382 size, int floorY, int mergeRadius, boolean southOnlyGeneration, class_2338 bannerPos, @Nullable String kingdomId) {
        PendingCastleGeneration scratch = new PendingCastleGeneration("", "", 0, bannerPos);
        scratch.setKingdomId(kingdomId);
        while (!GotCastles.mergeCastlePlatformBoundaryBatch(world, origin, size, floorY, mergeRadius, scratch, southOnlyGeneration)) {
        }
    }

    private static boolean flattenDynamicCastleClaimSurfaceBatch(class_3218 world, class_2338 origin, class_2382 size, int floorY, PendingCastleGeneration pendingGeneration) {
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        class_2338.class_2339 mutable = new class_2338.class_2339();
        PlatformSurfaceStyle surfaceStyle = GotCastles.resolvePlatformSurfaceStyle(world, pendingGeneration.bannerPos(), pendingGeneration.kingdomId());
        int startX = minX + pendingGeneration.coreCursor();
        int endX = Math.min(maxX, startX + 8 - 1);
        for (int x = startX; x <= endX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                int y;
                int clearTop;
                int currentSurfaceY = world.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
                int supportBaseY = GotCastles.resolveLocalSupportBaseY(world, x, z, currentSurfaceY);
                int targetFloorY = floorY;
                SurfacePalette palette = GotCastles.paletteForSurfaceStyle(surfaceStyle, GotCastles.resolveSurfacePalette(world, x, z));
                for (int y2 = clearTop = Math.max(currentSurfaceY + 8, targetFloorY + 28 + 2); y2 > targetFloorY; --y2) {
                    mutable.method_10103(x, y2, z);
                    if (world.method_8320((class_2338)mutable).method_26215()) continue;
                    world.method_8652((class_2338)mutable, class_2246.field_10124.method_9564(), 50);
                }
                class_2680 topSurface = GotCastles.platformSurfaceState(surfaceStyle, palette, pendingGeneration.bannerPos(), x, z);
                GotCastles.setCastleGenerationBlockState(world, mutable, x, targetFloorY, z, topSurface);
                for (int depth = 1; depth <= 8 && (y = targetFloorY - depth) > world.method_31607(); ++depth) {
                    GotCastles.setCastleGenerationBlockState(world, mutable, x, y, z, depth < 3 ? palette.subsurface() : palette.deep());
                }
                GotCastles.fillSupportColumnToY(world, x, z, targetFloorY - 9, Math.max(world.method_31607(), supportBaseY - 2), palette);
                GotCastles.decoratePlatformSurface(world, pendingGeneration.bannerPos(), x, targetFloorY, z, surfaceStyle, topSurface, 0);
            }
        }
        int nextCursor = pendingGeneration.coreCursor() + (endX - startX + 1);
        pendingGeneration.setCoreCursor(nextCursor);
        return endX >= maxX;
    }

    private static boolean mergeCastlePlatformBoundaryBatch(class_3218 world, class_2338 origin, class_2382 size, int floorY, int blendWidth, PendingCastleGeneration pendingGeneration, boolean southOnlyGeneration) {
        if (blendWidth <= 0) {
            return true;
        }
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        int ringMinX = minX - blendWidth;
        int ringMaxX = maxX + blendWidth;
        int ringMinZ = minZ - blendWidth;
        int ringMaxZ = maxZ + blendWidth;
        class_2338.class_2339 mutable = new class_2338.class_2339();
        PlatformSurfaceStyle surfaceStyle = GotCastles.resolvePlatformSurfaceStyle(world, pendingGeneration.bannerPos(), pendingGeneration.kingdomId());
        int startX = ringMinX + pendingGeneration.coreCursor();
        int endX = Math.min(ringMaxX, startX + 8 - 1);
        for (int x = startX; x <= endX; ++x) {
            for (int z = ringMinZ; z <= ringMaxZ; ++z) {
                int y;
                int clearTop;
                int distance = GotCastles.outsideDistance(x, z, minX, maxX, minZ, maxZ);
                if (distance <= 0 || distance > blendWidth) continue;
                int currentSurfaceY = world.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
                int supportBaseY = GotCastles.resolveLocalSupportBaseY(world, x, z, currentSurfaceY);
                int targetY = GotCastles.resolveCastlePlatformOuterBlendHeight(world, currentSurfaceY, distance, floorY, blendWidth);
                if (targetY == currentSurfaceY) continue;
                SurfacePalette palette = GotCastles.paletteForSurfaceStyle(surfaceStyle, GotCastles.resolveSurfacePalette(world, x, z));
                for (int y2 = clearTop = Math.max(currentSurfaceY + 4, targetY + 4); y2 > targetY; --y2) {
                    mutable.method_10103(x, y2, z);
                    if (world.method_8320((class_2338)mutable).method_26215()) continue;
                    world.method_8652((class_2338)mutable, class_2246.field_10124.method_9564(), 50);
                }
                class_2680 topSurface = GotCastles.platformSurfaceState(surfaceStyle, palette, pendingGeneration.bannerPos(), x, z);
                GotCastles.setCastleGenerationBlockState(world, mutable, x, targetY, z, topSurface);
                for (int depth = 1; depth <= 6 && (y = targetY - depth) > world.method_31607(); ++depth) {
                    GotCastles.setCastleGenerationBlockState(world, mutable, x, y, z, depth < 3 ? palette.subsurface() : palette.deep());
                }
                GotCastles.fillSupportColumnToY(world, x, z, targetY - 7, Math.max(world.method_31607(), supportBaseY - 2), palette);
                GotCastles.decoratePlatformSurface(world, pendingGeneration.bannerPos(), x, targetY, z, surfaceStyle, topSurface, distance);
            }
        }
        int nextCursor = pendingGeneration.coreCursor() + (endX - startX + 1);
        pendingGeneration.setCoreCursor(nextCursor);
        return endX >= ringMaxX;
    }

    private static int resolveCastlePlatformOuterBlendHeight(class_3218 world, int naturalSurfaceY, int outsideDistance, int floorY, int blendWidth) {
        double platformWeight = 1.0 - class_3532.method_15350((double)((double)outsideDistance / (double)Math.max(1, blendWidth)), (double)0.0, (double)1.0);
        double alpha = GotCastles.smoothstep(platformWeight);
        int targetY = Math.round((float)class_3532.method_16436((double)alpha, (double)naturalSurfaceY, (double)floorY));
        return Math.max(world.method_31607() + 1, targetY);
    }

    private static void prepareCastlePad(class_3218 world, class_2338 origin, class_2382 size, int floorY, int blendRadius, boolean southOnlyGeneration, class_2338 bannerPos, @Nullable String kingdomId) {
        PendingCastleGeneration scratch = new PendingCastleGeneration("", "", 0, bannerPos);
        scratch.setKingdomId(kingdomId);
        while (!GotCastles.prepareCastlePadBatch(world, origin, size, floorY, blendRadius, scratch, null, southOnlyGeneration)) {
        }
    }

    private static boolean prepareCastlePadBatch(class_3218 world, class_2338 origin, class_2382 size, int floorY, int blendRadius, PendingCastleGeneration pendingGeneration, @Nullable SurfacePalette forcedPalette, boolean southOnlyGeneration) {
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        class_2338.class_2339 mutable = new class_2338.class_2339();
        PlatformSurfaceStyle surfaceStyle = forcedPalette != null ? PlatformSurfaceStyle.FORCED : GotCastles.resolvePlatformSurfaceStyle(world, pendingGeneration.bannerPos(), pendingGeneration.kingdomId());
        int startX = minX - blendRadius + pendingGeneration.coreCursor();
        int endX = Math.min(maxX + blendRadius, startX + 8 - 1);
        for (int x = startX; x <= endX; ++x) {
            int padMinZ;
            for (int z = padMinZ = southOnlyGeneration ? minZ : minZ - blendRadius; z <= maxZ + blendRadius; ++z) {
                int y;
                int clearTop;
                int distance = GotCastles.outsideDistance(x, z, minX, maxX, minZ, maxZ);
                if (distance > blendRadius) continue;
                int currentSurfaceY = world.method_8624(class_2902.class_2903.field_13203, x, z) - 1;
                float alpha = distance == 0 ? 1.0f : 1.0f - (float)distance / (float)(blendRadius + 1);
                int desiredY = Math.round(class_3532.method_48781((float)alpha, (int)currentSurfaceY, (int)floorY));
                int targetY = Math.max(desiredY, currentSurfaceY - 4);
                int supportBaseY = GotCastles.resolveLocalSupportBaseY(world, x, z, currentSurfaceY);
                SurfacePalette palette = forcedPalette != null ? forcedPalette : GotCastles.paletteForSurfaceStyle(surfaceStyle, GotCastles.resolveSurfacePalette(world, x, z));
                for (int y2 = clearTop = Math.max(currentSurfaceY + 6, targetY + 6); y2 > targetY; --y2) {
                    mutable.method_10103(x, y2, z);
                    if (world.method_8320((class_2338)mutable).method_26215()) continue;
                    world.method_8652((class_2338)mutable, class_2246.field_10124.method_9564(), 50);
                }
                class_2680 topSurface = forcedPalette != null ? palette.surface() : GotCastles.platformSurfaceState(surfaceStyle, palette, pendingGeneration.bannerPos(), x, z);
                GotCastles.setCastleGenerationBlockState(world, mutable, x, targetY, z, topSurface);
                for (int depth = 1; depth <= 6 && (y = targetY - depth) > world.method_31607(); ++depth) {
                    GotCastles.setCastleGenerationBlockState(world, mutable, x, y, z, depth < 3 ? palette.subsurface() : palette.deep());
                }
                if (distance == 0) {
                    GotCastles.fillSupportColumn(world, x, z, targetY - 7, palette);
                } else {
                    GotCastles.fillSupportColumnToY(world, x, z, targetY - 7, Math.max(world.method_31607(), supportBaseY - 2), palette);
                }
                GotCastles.decoratePlatformSurface(world, pendingGeneration.bannerPos(), x, targetY, z, surfaceStyle, topSurface, distance);
            }
        }
        int nextCursor = pendingGeneration.coreCursor() + (endX - startX + 1);
        pendingGeneration.setCoreCursor(nextCursor);
        return endX >= maxX + blendRadius;
    }

    private static void setCastleGenerationBlockState(class_3218 world, class_2338.class_2339 mutable, int x, int y, int z, class_2680 state) {
        mutable.method_10103(x, y, z);
        if (!world.method_8320((class_2338)mutable).equals(state)) {
            world.method_8652((class_2338)mutable, state, 50);
        }
    }

    private static int outsideDistance(int x, int z, int minX, int maxX, int minZ, int maxZ) {
        int dx = x < minX ? minX - x : Math.max(0, x - maxX);
        int dz = z < minZ ? minZ - z : Math.max(0, z - maxZ);
        return Math.max(dx, dz);
    }

    private static PlatformSurfaceStyle resolvePlatformSurfaceStyle(class_3218 world, class_2338 referencePos, @Nullable String kingdomId) {
        class_2960 normalized;
        class_2960 class_29602 = normalized = kingdomId == null ? null : GotCastles.normalizeKingdomId(class_2960.method_12829((String)kingdomId));
        if (GotKingdoms.THE_NORTH.getId().equals((Object)normalized) || GotKingdoms.THE_NIGHTS_WATCH.getId().equals((Object)normalized) || GotKingdoms.WILDLING.getId().equals((Object)normalized)) {
            return PlatformSurfaceStyle.SNOWY;
        }
        if (GotKingdoms.DORNE.getId().equals((Object)normalized) || GotKingdoms.MEEREEN.getId().equals((Object)normalized) || GotKingdoms.YUNKAI.getId().equals((Object)normalized) || GotKingdoms.ASTAPOR.getId().equals((Object)normalized) || GotKingdoms.QARTH.getId().equals((Object)normalized) || GotKingdoms.VOLANTIS.getId().equals((Object)normalized) || GotKingdoms.TYROSH.getId().equals((Object)normalized) || GotKingdoms.MYR.getId().equals((Object)normalized) || GotKingdoms.PENTOS.getId().equals((Object)normalized) || GotKingdoms.GOLDEN_COMPANY.getId().equals((Object)normalized)) {
            return PlatformSurfaceStyle.DESERT;
        }
        if (GotKingdoms.THE_IRON_ISLANDS.getId().equals((Object)normalized)) {
            return PlatformSurfaceStyle.ROCKY;
        }
        if (GotKingdoms.TARGARYEN.getId().equals((Object)normalized) || GotKingdoms.BLACKFYRE.getId().equals((Object)normalized)) {
            return PlatformSurfaceStyle.VOLCANIC;
        }
        String biomePath = world.method_23753(new class_2338(referencePos.method_10263(), world.method_8615(), referencePos.method_10260())).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        if (biomePath.contains("dragonstone")) {
            return PlatformSurfaceStyle.VOLCANIC;
        }
        if (biomePath.contains("dorne") || biomePath.contains("slavers_bay") || biomePath.contains("step_stones") || biomePath.contains("desert")) {
            return PlatformSurfaceStyle.DESERT;
        }
        if (biomePath.contains("winter") || biomePath.contains("north") || biomePath.contains("snow") || biomePath.contains("frozen") || biomePath.contains("the_wall")) {
            return PlatformSurfaceStyle.SNOWY;
        }
        if (biomePath.contains("iron_islands") || biomePath.contains("rock") || biomePath.contains("stone_shore")) {
            return PlatformSurfaceStyle.ROCKY;
        }
        return PlatformSurfaceStyle.GRASS;
    }

    private static SurfacePalette paletteForSurfaceStyle(PlatformSurfaceStyle style, SurfacePalette biomePalette) {
        return switch (style) {
            default -> throw new MatchException(null, null);
            case PlatformSurfaceStyle.DESERT -> new SurfacePalette(class_2246.field_10102.method_9564(), class_2246.field_9979.method_9564(), class_2246.field_9979.method_9564());
            case PlatformSurfaceStyle.SNOWY -> new SurfacePalette(class_2246.field_10219.method_9564(), class_2246.field_10566.method_9564(), class_2246.field_10340.method_9564());
            case PlatformSurfaceStyle.ROCKY -> new SurfacePalette(class_2246.field_10255.method_9564(), class_2246.field_10253.method_9564(), class_2246.field_10340.method_9564());
            case PlatformSurfaceStyle.VOLCANIC -> new SurfacePalette(class_2246.field_22091.method_9564(), class_2246.field_23869.method_9564(), class_2246.field_23869.method_9564());
            case PlatformSurfaceStyle.FORCED, PlatformSurfaceStyle.GRASS -> biomePalette;
        };
    }

    private static class_2680 platformSurfaceState(PlatformSurfaceStyle style, SurfacePalette palette, class_2338 anchor, int x, int z) {
        double noise = GotCastles.platformNoise(anchor, x, z, 0);
        return switch (style) {
            default -> throw new MatchException(null, null);
            case PlatformSurfaceStyle.DESERT -> {
                if (noise < 0.58) {
                    yield class_2246.field_10102.method_9564();
                }
                if (noise < 0.82) {
                    yield class_2246.field_9979.method_9564();
                }
                if (noise < 0.94) {
                    yield class_2246.field_10467.method_9564();
                }
                yield class_2246.field_10361.method_9564();
            }
            case PlatformSurfaceStyle.SNOWY -> {
                if (noise < 0.08) {
                    yield class_2246.field_10253.method_9564();
                }
                yield class_2246.field_10219.method_9564();
            }
            case PlatformSurfaceStyle.ROCKY -> {
                if (noise < 0.48) {
                    yield class_2246.field_10255.method_9564();
                }
                if (noise < 0.72) {
                    yield class_2246.field_10253.method_9564();
                }
                if (noise < 0.88) {
                    yield class_2246.field_10340.method_9564();
                }
                yield class_2246.field_10219.method_9564();
            }
            case PlatformSurfaceStyle.GRASS -> {
                if (noise < 0.06) {
                    yield class_2246.field_10253.method_9564();
                }
                if (noise < 0.1) {
                    yield class_2246.field_10566.method_9564();
                }
                yield class_2246.field_10219.method_9564();
            }
            case PlatformSurfaceStyle.VOLCANIC, PlatformSurfaceStyle.FORCED -> palette.surface();
        };
    }

    private static void decoratePlatformSurface(class_3218 world, class_2338 anchor, int x, int y, int z, PlatformSurfaceStyle style, class_2680 surfaceState, int distanceFromFootprint) {
        if (style == PlatformSurfaceStyle.FORCED) {
            return;
        }
        class_2338 above = new class_2338(x, y + 1, z);
        if (!world.method_8320(above).method_26215()) {
            return;
        }
        double noise = GotCastles.platformNoise(anchor, x, z, 1);
        if (style == PlatformSurfaceStyle.SNOWY) {
            if (noise > 0.1) {
                int extraLayer = (int)Math.floor(GotCastles.platformNoise(anchor, x, z, 2) * 3.0);
                int edgeBonus = distanceFromFootprint > 0 && GotCastles.platformNoise(anchor, x, z, 3) > 0.72 ? 1 : 0;
                int layers = class_3532.method_15340((int)(1 + extraLayer + edgeBonus), (int)1, (int)4);
                world.method_8652(above, (class_2680)class_2246.field_10477.method_9564().method_11657((class_2769)class_2488.field_11518, (Comparable)Integer.valueOf(layers)), 50);
            }
            return;
        }
        if (style == PlatformSurfaceStyle.DESERT) {
            if (surfaceState.method_27852(class_2246.field_10102) && noise < 0.018) {
                world.method_8652(above, class_2246.field_10428.method_9564(), 50);
            }
            return;
        }
        if ((style == PlatformSurfaceStyle.GRASS || style == PlatformSurfaceStyle.ROCKY) && surfaceState.method_27852(class_2246.field_10219)) {
            double d = style == PlatformSurfaceStyle.ROCKY ? 0.035 : 0.085;
            if (noise < d) {
                class_2680 foliage = GotCastles.platformNoise(anchor, x, z, 2) < 0.22 ? class_2246.field_10112.method_9564() : class_2246.field_10479.method_9564();
                world.method_8652(above, foliage, 50);
            }
        }
    }

    private static double platformNoise(class_2338 anchor, int x, int z, int salt) {
        long value = (long)x * -7046029254386353131L ^ (long)z * -4658895280553007687L ^ anchor.method_10063() * -7723592293110705685L ^ (long)salt * -2960836687051489901L;
        value ^= value >>> 30;
        value *= -4658895280553007687L;
        value ^= value >>> 27;
        value *= -7723592293110705685L;
        value ^= value >>> 31;
        return (double)(value & 0xFFFFFFL) / 1.6777216E7;
    }

    private static SurfacePalette resolveSurfacePalette(class_3218 world, int x, int z) {
        String biomePath = world.method_23753(new class_2338(x, world.method_8615(), z)).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        if (biomePath.contains("dragonstone")) {
            return new SurfacePalette(class_2246.field_22091.method_9564(), class_2246.field_23869.method_9564(), class_2246.field_23869.method_9564());
        }
        if (biomePath.contains("dorne") || biomePath.contains("slavers_bay") || biomePath.contains("step_stones")) {
            return new SurfacePalette(class_2246.field_10102.method_9564(), class_2246.field_9979.method_9564(), class_2246.field_10340.method_9564());
        }
        if (biomePath.contains("winter") || biomePath.contains("north")) {
            return new SurfacePalette(class_2246.field_10491.method_9564(), class_2246.field_10566.method_9564(), class_2246.field_10340.method_9564());
        }
        if (biomePath.contains("iron_islands")) {
            return new SurfacePalette(class_2246.field_10255.method_9564(), class_2246.field_10566.method_9564(), class_2246.field_10340.method_9564());
        }
        return new SurfacePalette(class_2246.field_10219.method_9564(), class_2246.field_10566.method_9564(), class_2246.field_10340.method_9564());
    }

    private static SurfacePalette resolveKingdomSurfacePalette(class_3218 world, class_2338 bannerPos, String kingdomId) {
        PlatformSurfaceStyle style = GotCastles.resolvePlatformSurfaceStyle(world, bannerPos, kingdomId);
        return GotCastles.paletteForSurfaceStyle(style, GotCastles.resolveSurfacePalette(world, bannerPos.method_10263(), bannerPos.method_10260()));
    }

    private static int percentile(List<Integer> sortedValues, double percentile) {
        int index = (int)Math.round((double)(sortedValues.size() - 1) * percentile);
        index = Math.max(0, Math.min(index, sortedValues.size() - 1));
        return sortedValues.get(index);
    }

    private static String generatedCastleName(class_6880<class_1959> biome, CastleTier tier, int index) {
        String path = biome.method_40230().map(key -> key.method_29177().method_12832()).orElse("unknown");
        String biomeLabel = path.replace('_', ' ');
        String[] words = biomeLabel.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            if (word.isBlank()) continue;
            if (!builder.isEmpty()) {
                builder.append(' ');
            }
            builder.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
        }
        String prefix = tier == CastleTier.CAPITAL ? "Capital of " : (tier == CastleTier.LORDSHIP ? "Lordship of " : "Settlement of ");
        return prefix + String.valueOf(builder) + " " + index;
    }

    private static List<OverlayCastleSite> loadOverlaySites() {
        return CastleOverlayDataLoader.loadOverlaySites(presetReferencesByLegacyId, WOODEN_OVERLAY_PATH, 6302572, SETTLEMENT_DEFINITION_ID, STONE_OVERLAY_PATH, 65337, LORDSHIP_DEFINITION_ID, GotCastles.class.getClassLoader());
    }

    private static BufferedImage loadImage(String resourcePath) {
        return CastleMapResourceHelper.loadImage(resourcePath, GotCastles.class.getClassLoader());
    }

    private static class_2338 pixelToWorld(int pixelX, int pixelZ) {
        return CastleMapResourceHelper.pixelToWorld(pixelX, pixelZ);
    }

    private static Set<Integer> loadKnownMapBiomeColors() {
        return CastleMapResourceHelper.loadKnownMapBiomeColors(DIMENSION_PATH, GotCastles.class.getClassLoader());
    }

    private static Set<Integer> loadWaterMapColors() {
        return CastleMapResourceHelper.loadWaterMapColors(DIMENSION_PATH, GotCastles.class.getClassLoader());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Map<String, CastleDefinition> loadDefinitions() {
        LinkedHashMap<String, CastleDefinition> definitions = new LinkedHashMap<String, CastleDefinition>();
        definitions.put(SETTLEMENT_DEFINITION_ID, GotCastles.defaultDefinition(SETTLEMENT_DEFINITION_ID, "Overlay Settlement", CastleTier.SETTLEMENT));
        definitions.put(LORDSHIP_DEFINITION_ID, GotCastles.defaultDefinition(LORDSHIP_DEFINITION_ID, "Overlay Lordship", CastleTier.LORDSHIP));
        try (InputStream stream = GotCastles.class.getClassLoader().getResourceAsStream(DEFINITIONS_PATH);){
            if (stream == null) {
                GotMod.LOGGER.warn("Missing castle definitions resource {}", (Object)DEFINITIONS_PATH);
                LinkedHashMap<String, CastleDefinition> linkedHashMap2 = definitions;
                return linkedHashMap2;
            }
            JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            JsonArray castles = root.getAsJsonArray("castles");
            for (JsonElement element : castles) {
                CastleDefinition definition = GotCastles.parseDefinition(element.getAsJsonObject());
                definitions.put(definition.id(), definition);
            }
            LinkedHashMap<String, CastleDefinition> linkedHashMap = definitions;
            return linkedHashMap;
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to load castle definitions from data/agob/castles/definitions.json", e);
        }
    }

    private static void loadPresetMetadata() {
        presetMetadataByCastleId = CastleOverlayDataLoader.loadPresetMetadata(overlaySites, presetReferencesByCastleId, PRESETS_ROOT, GotCastles.class.getClassLoader());
    }

    private static Optional<CastlePresetMetadata> loadCastlePreset(OverlayCastleSite site) {
        return Optional.ofNullable(presetMetadataByCastleId.get(site.castleId()));
    }

    private static Set<CastleSubtype> parseSubtypeSet(JsonObject castle) {
        LinkedHashSet<CastleSubtype> subtypes = new LinkedHashSet<CastleSubtype>();
        if (!castle.has("subtypes")) {
            return Set.of();
        }
        for (JsonElement element : castle.getAsJsonArray("subtypes")) {
            CastleSubtype subtype = CastleSubtype.byId(element.getAsString());
            if (subtype == null) continue;
            subtypes.add(subtype);
        }
        return Set.copyOf(subtypes);
    }

    private static CastleTier resolveEffectiveCastleTier(@Nullable CastlePresetMetadata metadata, CastleTier fallback) {
        if (metadata == null || metadata.tier().isBlank()) {
            return fallback;
        }
        CastleTier metadataTier = CastleTier.byId(metadata.tier());
        if (metadataTier == CastleTier.CAPITAL) {
            return CastleTier.CAPITAL;
        }
        if (metadataTier == CastleTier.LORDSHIP && CAPITAL_CASTLE_IDS.contains(metadata.castleId())) {
            return CastleTier.CAPITAL;
        }
        return metadataTier;
    }

    private static void loadOverlayPresetReferences() {
        OverlayPresetReferences references = CastleOverlayDataLoader.loadOverlayPresetReferences(DEFINITIONS_PATH, GotCastles.class.getClassLoader());
        presetReferencesByLegacyId = references.byLegacyId();
        presetReferencesByCastleId = references.byCastleId();
    }

    private static Set<CastleSubtype> resolveSubtypes(OverlayCastleSite site, CastlePresetMetadata metadata) {
        LinkedHashSet<CastleSubtype> subtypes = new LinkedHashSet<CastleSubtype>();
        if (metadata != null) {
            subtypes.addAll(metadata.subtypes());
        }
        if (GotCastles.isInferredHarbourSite(site)) {
            subtypes.add(CastleSubtype.HARBOUR);
        }
        return Set.copyOf(subtypes);
    }

    private static List<String> resolveSubtypeIds(CastleInstance castle, CastlePresetMetadata metadata) {
        OverlayCastleSite site;
        LinkedHashSet<CastleSubtype> subtypes = new LinkedHashSet<CastleSubtype>(castle.getSubtypes());
        if (subtypes.isEmpty() && (site = GotCastles.findOverlaySite(castle.getId())) != null) {
            subtypes.addAll(GotCastles.resolveSubtypes(site, metadata));
        }
        return GotCastles.subtypeIds(subtypes);
    }

    private static List<String> resolveSubtypeIds(OverlayCastleSite site, CastlePresetMetadata metadata) {
        return GotCastles.subtypeIds(GotCastles.resolveSubtypes(site, metadata));
    }

    private static List<String> subtypeIds(Set<CastleSubtype> subtypes) {
        return subtypes.stream().map(CastleSubtype::getId).toList();
    }

    private static boolean isInferredHarbourSite(OverlayCastleSite site) {
        return site != null && GotCastles.hasAdjacentWaterPixel(site.anchor());
    }

    private static boolean hasAdjacentWaterPixel(class_2338 anchor) {
        int pixelX = GotCastles.worldToMapPixel(anchor.method_10263());
        int pixelZ = GotCastles.worldToMapPixel(anchor.method_10260());
        for (int offsetX = -1; offsetX <= 1; ++offsetX) {
            for (int offsetZ = -1; offsetZ <= 1; ++offsetZ) {
                if (offsetX == 0 && offsetZ == 0 || !GotCastles.isWaterMapPixel(pixelX + offsetX, pixelZ + offsetZ)) continue;
                return true;
            }
        }
        return false;
    }

    private static int worldToMapPixel(int worldCoordinate) {
        return CastleMapResourceHelper.worldToMapPixel(worldCoordinate);
    }

    private static boolean isWaterMapChunk(class_1923 chunkPos) {
        return CastleMapResourceHelper.isWaterMapChunk(chunkPos, worldMapImage, WORLD_MAP_PATH, knownMapBiomeColors, waterMapColors, GotCastles.class.getClassLoader());
    }

    public static boolean isWaterMapChunkForWorldgen(class_1923 chunkPos) {
        return chunkPos == null || GotCastles.isWaterMapChunk(chunkPos);
    }

    private static boolean isWaterMapPixel(int pixelX, int pixelZ) {
        return CastleMapResourceHelper.isWaterMapPixel(pixelX, pixelZ, worldMapImage, WORLD_MAP_PATH, knownMapBiomeColors, waterMapColors, GotCastles.class.getClassLoader());
    }

    private static void syncInferredSubtypePresetFiles() {
        Path presetsRoot = Path.of(System.getProperty("user.dir"), DEV_PRESETS_ROOT);
        if (!Files.isDirectory(presetsRoot, new LinkOption[0])) {
            return;
        }
        boolean updatedAny = false;
        for (OverlayCastleSite site : overlaySites) {
            Path presetPath;
            if (!GotCastles.isInferredHarbourSite(site) || !Files.exists(presetPath = presetsRoot.resolve(site.castleId() + ".json"), new LinkOption[0])) continue;
            try {
                Object element2;
                String content = Files.readString(presetPath, StandardCharsets.UTF_8);
                JsonObject root = JsonParser.parseString((String)content).getAsJsonObject();
                LinkedHashSet<String> subtypeIds = new LinkedHashSet<String>();
                if (root.has("subtypes")) {
                    for (Object element2 : root.getAsJsonArray("subtypes")) {
                        subtypeIds.add(element2.getAsString().toLowerCase(Locale.ROOT));
                    }
                }
                if (!subtypeIds.add(CastleSubtype.HARBOUR.getId())) continue;
                JsonArray subtypesArray = new JsonArray();
                element2 = subtypeIds.iterator();
                while (element2.hasNext()) {
                    String subtypeId = (String)element2.next();
                    subtypesArray.add(subtypeId);
                }
                root.add("subtypes", (JsonElement)subtypesArray);
                String updated = new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)root) + System.lineSeparator();
                Files.writeString(presetPath, (CharSequence)updated, StandardCharsets.UTF_8, new OpenOption[0]);
                updatedAny = true;
            }
            catch (IOException e) {
                GotMod.LOGGER.warn("Failed to sync inferred subtype preset {}", (Object)presetPath, (Object)e);
            }
        }
        if (updatedAny) {
            GotMod.LOGGER.info("Synced inferred harbour subtypes into preset json files under {}", (Object)presetsRoot);
        }
    }

    static String defaultReligionIdForKingdom(String kingdomId) {
        class_2960 identifier = GotCastles.normalizeKingdomId(class_2960.method_12829((String)kingdomId));
        Kingdom kingdom = identifier == null ? null : GotKingdoms.get(identifier);
        return kingdom == null || kingdom.getReligion() == null ? "" : kingdom.getReligion().getId();
    }

    private static CastleDefinition defaultDefinition(String id, String displayName, CastleTier tier) {
        CastlePlacementRules rules = tier == CastleTier.LORDSHIP || tier == CastleTier.CAPITAL ? new CastlePlacementRules(3, 48, 8, 10, 4, 6, 10) : new CastlePlacementRules(3, 48, 8, 12, 4, 6, 10);
        LinkedHashSet<CastleWoodType> woods = new LinkedHashSet<CastleWoodType>(Arrays.asList(CastleWoodType.values()));
        return new CastleDefinition(id, displayName, tier, null, List.of(), Set.of(), woods, rules);
    }

    private static Integer parseColor(String value) {
        String normalized = value.trim();
        if (normalized.startsWith("#")) {
            normalized = normalized.substring(1);
        }
        if (normalized.length() != 6) {
            throw new IllegalStateException("Invalid overlay color value " + value);
        }
        return Integer.parseInt(normalized, 16);
    }

    private static String formatColor(int color) {
        return String.format("#%06X", color);
    }

    private static CastleDefinition parseDefinition(JsonObject root) {
        String id = root.get("id").getAsString();
        String displayName = root.get("display_name").getAsString();
        CastleTier tier = CastleTier.byId(root.get("tier").getAsString());
        String kingdomValue = root.has("kingdom_id") ? root.get("kingdom_id").getAsString() : "";
        class_2960 kingdomId = kingdomValue.isBlank() ? null : class_2960.method_60654((String)kingdomValue);
        List<String> allowedBiomes = root.has("allowed_biomes") ? root.getAsJsonArray("allowed_biomes").asList().stream().map(JsonElement::getAsString).toList() : List.of();
        LinkedHashSet<String> allowedReligionIds = new LinkedHashSet<String>();
        if (root.has("allowed_religion_ids")) {
            for (Object element : root.getAsJsonArray("allowed_religion_ids")) {
                allowedReligionIds.add(element.getAsString());
            }
        }
        LinkedHashSet<CastleWoodType> preferredWoodTypes = new LinkedHashSet<CastleWoodType>();
        if (root.has("preferred_wood_types")) {
            for (JsonElement element : root.getAsJsonArray("preferred_wood_types")) {
                preferredWoodTypes.add(CastleWoodType.byId(element.getAsString()));
            }
        }
        CastlePlacementRules placementRules = CastlePlacementRules.defaultRules();
        if (root.has("placement")) {
            JsonObject placement = root.getAsJsonObject("placement");
            placementRules = new CastlePlacementRules(GotCastles.getInt(placement, "footprint_chunks", placementRules.footprintChunks()), GotCastles.getInt(placement, "scan_radius_blocks", placementRules.scanRadiusBlocks()), GotCastles.getInt(placement, "surface_sample_step", placementRules.surfaceSampleStep()), GotCastles.getInt(placement, "max_terrain_delta", placementRules.maxTerrainDelta()), GotCastles.getInt(placement, "max_water_samples", placementRules.maxWaterSamples()), GotCastles.getInt(placement, "pad_depth", placementRules.padDepth()), GotCastles.getInt(placement, "blend_radius", placementRules.blendRadius()));
        }
        return new CastleDefinition(id, displayName, tier, kingdomId, allowedBiomes, Set.copyOf(allowedReligionIds), Set.copyOf(preferredWoodTypes), placementRules);
    }

    private static void registerCommands(CommandDispatcher<class_2168> dispatcher, class_7157 registryAccess, class_2170.class_5364 environment) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"castle").requires(source -> source.method_9259(2))).then(class_2170.method_9247((String)"recapture").then(((RequiredArgumentBuilder)class_2170.method_9244((String)"castle_id", (ArgumentType)StringArgumentType.word()).executes(context -> GotCastles.executeRecaptureCastle((class_2168)context.getSource(), StringArgumentType.getString((CommandContext)context, (String)"castle_id"), -1))).then(class_2170.method_9244((String)"seconds", (ArgumentType)IntegerArgumentType.integer((int)1)).executes(context -> GotCastles.executeRecaptureCastle((class_2168)context.getSource(), StringArgumentType.getString((CommandContext)context, (String)"castle_id"), IntegerArgumentType.getInteger((CommandContext)context, (String)"seconds"))))))).then(class_2170.method_9247((String)"invade").then(class_2170.method_9244((String)"invading_castle_id", (ArgumentType)StringArgumentType.word()).then(((RequiredArgumentBuilder)class_2170.method_9244((String)"target_castle_id", (ArgumentType)StringArgumentType.word()).executes(context -> GotCastles.executeKingdomAiCastleInvade((class_2168)context.getSource(), StringArgumentType.getString((CommandContext)context, (String)"invading_castle_id"), StringArgumentType.getString((CommandContext)context, (String)"target_castle_id"), -1))).then(class_2170.method_9244((String)"seconds", (ArgumentType)IntegerArgumentType.integer((int)1)).executes(context -> GotCastles.executeKingdomAiCastleInvade((class_2168)context.getSource(), StringArgumentType.getString((CommandContext)context, (String)"invading_castle_id"), StringArgumentType.getString((CommandContext)context, (String)"target_castle_id"), IntegerArgumentType.getInteger((CommandContext)context, (String)"seconds")))))))).then(class_2170.method_9247((String)"goto").then(class_2170.method_9244((String)"castle_id", (ArgumentType)StringArgumentType.word()).executes(context -> GotCastles.executeGotoCastle((class_2168)context.getSource(), StringArgumentType.getString((CommandContext)context, (String)"castle_id"))))));
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"findcastles").requires(source -> source.method_9259(2))).executes(context -> GotCastles.executeFindCastles((class_2168)context.getSource())));
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"findcamps").requires(source -> source.method_9259(2))).executes(context -> GotCastles.executeFindCamps((class_2168)context.getSource())));
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"findweirwoods").requires(source -> source.method_9259(2))).executes(context -> GotCastles.executeFindWeirwoods((class_2168)context.getSource())));
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"attributes").requires(source -> source.method_9259(2))).then(class_2170.method_9247((String)"add").then(class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).then(class_2170.method_9244((String)"attribute", (ArgumentType)StringArgumentType.word()).suggests((context, builder) -> GotCastles.suggestSkillAttributes(builder)).then(class_2170.method_9244((String)"amount", (ArgumentType)IntegerArgumentType.integer((int)0)).executes(context -> GotCastles.executeAttributeCommand((class_2168)context.getSource(), class_2186.method_9315((CommandContext)context, (String)"player"), StringArgumentType.getString((CommandContext)context, (String)"attribute"), IntegerArgumentType.getInteger((CommandContext)context, (String)"amount"), AttributeOperation.ADD))))))).then(class_2170.method_9247((String)"set").then(class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).then(class_2170.method_9244((String)"attribute", (ArgumentType)StringArgumentType.word()).suggests((context, builder) -> GotCastles.suggestSkillAttributes(builder)).then(class_2170.method_9244((String)"amount", (ArgumentType)IntegerArgumentType.integer((int)0)).executes(context -> GotCastles.executeAttributeCommand((class_2168)context.getSource(), class_2186.method_9315((CommandContext)context, (String)"player"), StringArgumentType.getString((CommandContext)context, (String)"attribute"), IntegerArgumentType.getInteger((CommandContext)context, (String)"amount"), AttributeOperation.SET))))))).then(class_2170.method_9247((String)"remove").then(class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).then(class_2170.method_9244((String)"attribute", (ArgumentType)StringArgumentType.word()).suggests((context, builder) -> GotCastles.suggestSkillAttributes(builder)).then(class_2170.method_9244((String)"amount", (ArgumentType)IntegerArgumentType.integer((int)0)).executes(context -> GotCastles.executeAttributeCommand((class_2168)context.getSource(), class_2186.method_9315((CommandContext)context, (String)"player"), StringArgumentType.getString((CommandContext)context, (String)"attribute"), IntegerArgumentType.getInteger((CommandContext)context, (String)"amount"), AttributeOperation.REMOVE)))))));
    }

    private static CompletableFuture<Suggestions> suggestSkillAttributes(SuggestionsBuilder builder) {
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            builder.suggest(type.getId());
        }
        return builder.buildFuture();
    }

    private static int executeAttributeCommand(class_2168 source, class_3222 player, String attributeId, int amount, AttributeOperation operation) {
        SkillAttributeType type = SkillAttributeType.byId(attributeId.toLowerCase(Locale.ROOT));
        if (type == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)("Unknown attribute '" + attributeId + "'.")));
            return 0;
        }
        int changed = switch (operation) {
            default -> throw new MatchException(null, null);
            case AttributeOperation.ADD -> SkillExperienceService.addPoints(player, type, amount);
            case AttributeOperation.REMOVE -> SkillExperienceService.removePoints(player, type, amount);
            case AttributeOperation.SET -> GotCastles.setPlayerSkillPoints(player, type, amount);
        };
        GotCharacters.sendCharacterSelectionState(player);
        int currentPoints = ((PlayerSkillHolder)player).got$getSkillProfile().get(type).getPoints();
        source.method_9226(() -> class_2561.method_43470((String)(operation.label + " " + changed + " " + type.getDisplayName() + " point(s) for " + player.method_7334().getName() + ". Current: " + currentPoints)), true);
        return changed > 0 || operation == AttributeOperation.SET ? 1 : 0;
    }

    private static int setPlayerSkillPoints(class_3222 player, SkillAttributeType type, int amount) {
        SkillAttributeState state = ((PlayerSkillHolder)player).got$getSkillProfile().get(type);
        int clampedAmount = class_3532.method_15340((int)amount, (int)0, (int)state.getMaxPoints());
        int previousPoints = state.getPoints();
        state.setPoints(clampedAmount);
        if (state.getPoints() >= state.getMaxPoints()) {
            state.setExperience(0);
        }
        return Math.abs(state.getPoints() - previousPoints);
    }

    private static int executeRecaptureCastle(class_2168 source, String castleId, int secondsOverride) {
        Long incomingOverrideTicks;
        class_3218 world = source.method_9211().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Iceandfire dimension is not loaded."));
            return 0;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Unknown castle id."));
            return 0;
        }
        if ("player_claim".equals(castle.getDefinitionId()) || castle.getOwnerPlayerUuid() == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Only player-owned invasion castles can be recaptured."));
            return 0;
        }
        if (GotCastles.findActiveInvasionForCastle(castle.getId()) != null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"That castle is already under invasion."));
            return 0;
        }
        Long l = incomingOverrideTicks = secondsOverride > 0 ? Long.valueOf((long)secondsOverride * 20L) : null;
        if (GotCastles.startIncomingCastleRecapture(world, castle, true, incomingOverrideTicks)) {
            source.method_9226(() -> class_2561.method_43470((String)("Started recapture event for " + GotCastles.displayNameForCastle(castle) + (String)(secondsOverride > 0 ? " with " + secondsOverride + "s incoming time." : "."))), false);
            return 1;
        }
        source.method_9213((class_2561)class_2561.method_43470((String)"Could not start recapture event."));
        return 0;
    }

    private static int executeKingdomAiCastleInvade(class_2168 source, String invadingCastleId, String targetCastleId, int secondsOverride) {
        Long warmupTicks;
        class_3218 world = source.method_9211().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Iceandfire dimension is not loaded."));
            return 0;
        }
        StrategicCastleSnapshot invadingCastle = GotCastles.resolveStrategicCastle(world, invadingCastleId);
        if (invadingCastle == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Unknown invading castle id."));
            return 0;
        }
        StrategicCastleSnapshot targetCastle = GotCastles.resolveStrategicCastle(world, targetCastleId);
        if (targetCastle == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Unknown target castle id."));
            return 0;
        }
        Long l = warmupTicks = secondsOverride > 0 ? Long.valueOf((long)secondsOverride * 20L) : null;
        if (!GotCastles.startKingdomAiInvasion(world, invadingCastle, targetCastle, warmupTicks)) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Could not start kingdom invasion event."));
            return 0;
        }
        source.method_9226(() -> class_2561.method_43470((String)("Started kingdom invasion from " + invadingCastle.displayName() + " against " + targetCastle.displayName() + (String)(secondsOverride > 0 ? " with " + secondsOverride + "s warmup." : "."))), false);
        return 1;
    }

    @Nullable
    private static StrategicCastleSnapshot resolveStrategicCastle(class_3218 world, String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return null;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        String normalizedId = castleId.trim().toLowerCase(Locale.ROOT).replace(' ', '_').replace('-', '_');
        CastleInstance placed = state.get(castleId);
        if (placed == null) {
            placed = state.get(normalizedId);
        }
        if (placed == null) {
            for (CastleInstance castle : state.getAll()) {
                if (!castle.getId().equalsIgnoreCase(castleId) && !castle.getId().equalsIgnoreCase(normalizedId)) continue;
                placed = castle;
                break;
            }
        }
        if (placed != null) {
            CastlePresetMetadata metadata = presetMetadataByCastleId.get(placed.getId());
            CastleTier effectiveTier = GotCastles.resolveEffectiveCastleTier(metadata, placed.getTier());
            return new StrategicCastleSnapshot(placed.getId(), GotCastles.displayNameForCastle(placed), placed.getBannerPos(), effectiveTier, placed.hasSubtype(CastleSubtype.HARBOUR), GotCastles.canonicalKingdomId(placed.getOwnerKingdomId()), GotCastles.resolveOwnedHouseId(placed.getOwnerHouseId(), GotCastles.canonicalKingdomId(placed.getOwnerKingdomId()), metadata != null ? metadata.houseId() : ""), placed.getOwnerReligionId(), GotCastles.resolveLordName(world, placed), placed.getOwnerPlayerUuid(), "player_claim".equals(placed.getDefinitionId()), true, Math.max(0L, placed.getRaidEndsAtTick()));
        }
        OverlayCastleSite site = GotCastles.findOverlaySite(castleId);
        if (site == null) {
            site = GotCastles.findOverlaySite(normalizedId);
        }
        if (site == null || state.isRejected(site.castleId())) {
            return null;
        }
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(site.castleId());
        String defaultKingdomId = GotCastles.resolvePendingCastleKingdomId(world, site, metadata);
        String defaultReligionId = GotCastles.resolvePendingCastleReligionId(metadata, defaultKingdomId);
        String defaultLordName = GotCastles.resolvePendingLordName(world, site, metadata, defaultKingdomId);
        PendingCastleOwnershipWorldState.PendingCastleOwnership pending = PendingCastleOwnershipWorldState.get(world).get(site.castleId());
        CastleTier tier = GotCastles.resolveEffectiveCastleTier(metadata, LORDSHIP_DEFINITION_ID.equals(site.definitionId()) ? CastleTier.LORDSHIP : CastleTier.SETTLEMENT);
        return new StrategicCastleSnapshot(site.castleId(), metadata != null && !metadata.name().isBlank() ? metadata.name() : GotCastles.pendingDisplayName(site), site.anchor(), tier, GotCastles.resolveSubtypes(site, metadata).contains((Object)CastleSubtype.HARBOUR), pending != null && !pending.ownerKingdomId().isBlank() ? GotCastles.canonicalKingdomId(pending.ownerKingdomId()) : GotCastles.canonicalKingdomId(defaultKingdomId), GotCastles.resolveOwnedHouseId(pending != null ? pending.ownerHouseId() : "", pending != null && !pending.ownerKingdomId().isBlank() ? GotCastles.canonicalKingdomId(pending.ownerKingdomId()) : GotCastles.canonicalKingdomId(defaultKingdomId), metadata != null ? metadata.houseId() : ""), pending != null && !pending.ownerReligionId().isBlank() ? pending.ownerReligionId() : defaultReligionId, pending != null && !pending.lordName().isBlank() ? pending.lordName() : defaultLordName, null, false, false, pending != null ? pending.raidEndsAtTick() : 0L);
    }

    private static StrategicCastleSnapshotSet snapshotStrategicCastles(class_3218 world) {
        StrategicCastleSnapshot snapshot;
        CastleWorldState state = GotCastles.getWorldState(world);
        ArrayList<StrategicCastleSnapshot> all = new ArrayList<StrategicCastleSnapshot>();
        LinkedHashMap<String, StrategicCastleSnapshot> byCastleId = new LinkedHashMap<String, StrategicCastleSnapshot>();
        LinkedHashMap<String, List<StrategicCastleSnapshot>> byKingdomId = new LinkedHashMap<String, List<StrategicCastleSnapshot>>();
        for (CastleInstance castle : state.getAll()) {
            snapshot = GotCastles.resolveStrategicCastle(world, castle.getId());
            if (snapshot == null) continue;
            GotCastles.addStrategicCastleSnapshot(snapshot, all, byCastleId, byKingdomId);
        }
        for (OverlayCastleSite site : overlaySites) {
            if (state.contains(site.castleId()) || state.isRejected(site.castleId()) || (snapshot = GotCastles.resolveStrategicCastle(world, site.castleId())) == null) continue;
            GotCastles.addStrategicCastleSnapshot(snapshot, all, byCastleId, byKingdomId);
        }
        LinkedHashMap immutableByKingdom = new LinkedHashMap();
        byKingdomId.forEach((kingdomId, castles) -> immutableByKingdom.put(kingdomId, List.copyOf(castles)));
        return new StrategicCastleSnapshotSet(List.copyOf(all), Map.copyOf(byCastleId), Map.copyOf(immutableByKingdom));
    }

    private static void addStrategicCastleSnapshot(StrategicCastleSnapshot snapshot, List<StrategicCastleSnapshot> all, Map<String, StrategicCastleSnapshot> byCastleId, Map<String, List<StrategicCastleSnapshot>> byKingdomId) {
        all.add(snapshot);
        byCastleId.put(snapshot.castleId(), snapshot);
        byKingdomId.computeIfAbsent(GotCastles.canonicalKingdomId(snapshot.ownerKingdomId()), ignored -> new ArrayList()).add(snapshot);
    }

    public static List<StrategicCastleSnapshot> getStrategicCastlesForKingdom(class_3218 world, String kingdomId) {
        if (world == null) {
            return List.of();
        }
        return GotCastles.snapshotStrategicCastles(world).castlesForKingdom(kingdomId);
    }

    public static List<StrategicCastleSnapshot> getStrategicAiInvadersForKingdom(class_3218 world, String kingdomId) {
        if (world == null) {
            return List.of();
        }
        return GotCastles.getStrategicAiInvadersForKingdom(world, kingdomId, GotCastles.snapshotStrategicCastles(world));
    }

    private static List<StrategicCastleSnapshot> getStrategicAiInvadersForKingdom(class_3218 world, String kingdomId, StrategicCastleSnapshotSet strategicCastles) {
        String houseId;
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        if (canonicalKingdomId.isBlank()) {
            return List.of();
        }
        List<StrategicCastleSnapshot> kingdomCastles = strategicCastles.castlesForKingdom(canonicalKingdomId);
        LinkedHashMap<String, List> castlesByHouse = new LinkedHashMap<String, List>();
        for (StrategicCastleSnapshot strategicCastleSnapshot : kingdomCastles) {
            String houseId2;
            String houseKey;
            if (strategicCastleSnapshot.ownerPlayerUuid() != null || strategicCastleSnapshot.playerClaim() || (houseKey = HouseLordWorldState.houseKey(canonicalKingdomId, houseId2 = GotCastles.resolveOwnedHouseId(strategicCastleSnapshot.ownerHouseId(), canonicalKingdomId, ""))).isBlank()) continue;
            castlesByHouse.computeIfAbsent(houseKey, ignored -> new ArrayList()).add(strategicCastleSnapshot);
        }
        ArrayList<StrategicCastleSnapshot> invaders = new ArrayList<StrategicCastleSnapshot>();
        for (Object houseCastles : castlesByHouse.values()) {
            houseCastles.sort(Comparator.comparingInt(castle -> GotCastles.houseSeatRank(castle.tier())).thenComparing(StrategicCastleSnapshot::castleId));
            StrategicCastleSnapshot seat = (StrategicCastleSnapshot)houseCastles.getFirst();
            houseId = GotCastles.resolveOwnedHouseId(seat.ownerHouseId(), canonicalKingdomId, "");
            String seatCastleId = seat.castleId();
            for (HouseLordWorldState.HouseLordProfile lord : GotCastles.ensureHouseLordProfiles(world, canonicalKingdomId, houseId, strategicCastles)) {
                String spawnCastleId = GotCastles.primarySpawnCastleIdForLord(world, lord.displayName(), seatCastleId, (List<StrategicCastleSnapshot>)houseCastles);
                StrategicCastleSnapshot origin = strategicCastles.resolve(spawnCastleId);
                if (origin == null) continue;
                invaders.add(new StrategicCastleSnapshot(GotCastles.strategicLordCastleId(origin.castleId(), lord.id()), origin.displayName(), origin.bannerPos(), origin.tier(), origin.harbourCastle(), origin.ownerKingdomId(), origin.ownerHouseId(), origin.ownerReligionId(), lord.displayName(), origin.ownerPlayerUuid(), origin.playerClaim(), origin.generated(), origin.raidEndsAtTick()));
            }
        }
        LinkedHashSet<CallSite> linkedHashSet = new LinkedHashSet<CallSite>();
        for (StrategicCastleSnapshot castle3 : kingdomCastles) {
            String routeCastleId;
            StrategicCastleSnapshot origin;
            String dynastyKey;
            if (castle3.ownerPlayerUuid() == null || castle3.playerClaim() || castle3.lordName().isBlank()) continue;
            houseId = GotCastles.resolveOwnedHouseId(castle3.ownerHouseId(), canonicalKingdomId, "");
            if (!GotDynastyNpcs.isDynastyOwnedLordName(world, castle3.ownerPlayerUuid(), castle3.lordName()) || !linkedHashSet.add((CallSite)((Object)(dynastyKey = String.valueOf(castle3.ownerPlayerUuid()) + "|" + houseId + "|" + castle3.lordName()))) || (origin = strategicCastles.resolve(routeCastleId = GotCastles.resolvePlayerOwnedDynastyRouteCastleId(world, castle3.ownerPlayerUuid(), canonicalKingdomId, houseId, castle3.lordName(), castle3.castleId(), kingdomCastles))) == null) continue;
            invaders.add(new StrategicCastleSnapshot(GotCastles.strategicLordCastleId(origin.castleId(), "dynasty:" + String.valueOf(castle3.ownerPlayerUuid()) + ":" + castle3.lordName()), origin.displayName(), origin.bannerPos(), origin.tier(), origin.harbourCastle(), canonicalKingdomId, houseId, origin.ownerReligionId(), castle3.lordName(), castle3.ownerPlayerUuid(), false, origin.generated(), origin.raidEndsAtTick()));
        }
        if (!invaders.isEmpty() || !GotKingdoms.isCastlelessOriginKingdom(canonicalKingdomId)) {
            return List.copyOf(invaders);
        }
        StrategicCastleSnapshot origin = GotCastles.getCastlelessKingdomInvasionOrigin(world, canonicalKingdomId, strategicCastles);
        return origin == null ? List.of() : List.of(origin);
    }

    @Nullable
    public static StrategicCastleSnapshot getCastlelessKingdomInvasionOrigin(class_3218 world, String kingdomId) {
        return world == null ? null : GotCastles.getCastlelessKingdomInvasionOrigin(world, kingdomId, GotCastles.snapshotStrategicCastles(world));
    }

    @Nullable
    private static StrategicCastleSnapshot getCastlelessKingdomInvasionOrigin(class_3218 world, String kingdomId, StrategicCastleSnapshotSet strategicCastles) {
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        if (!GotKingdoms.isCastlelessOriginKingdom(canonicalKingdomId) || !strategicCastles.castlesForKingdom(canonicalKingdomId).isEmpty()) {
            return null;
        }
        class_2338 origin = GotCastles.castlelessFixedOrigin(canonicalKingdomId);
        if (origin == null) {
            origin = GotCastles.findMapColorOrigin(GotCastles.castlelessOriginColor(canonicalKingdomId));
        }
        if (origin == null) {
            return null;
        }
        String houseId = switch (canonicalKingdomId) {
            case "agob:blackfyre" -> "blackfyre_blackfyre";
            case "agob:golden_company" -> "golden_company";
            case "agob:meereen" -> "meereen_meereen";
            case "agob:yunkai" -> "yunkai_yunkai";
            case "agob:astapor" -> "astapor_astapor";
            case "agob:qarth" -> "qarth_qarth";
            default -> "";
        };
        String displayName = switch (canonicalKingdomId) {
            case "agob:blackfyre" -> "Blackfyre Origin";
            case "agob:golden_company" -> "Golden Company Origin";
            case "agob:meereen" -> "Meereen Origin";
            case "agob:yunkai" -> "Yunkai Origin";
            case "agob:astapor" -> "Astapor Origin";
            case "agob:qarth" -> "Qarth Origin";
            default -> "Castleless Origin";
        };
        String lordName = GotCastles.castlelessOriginLordName(canonicalKingdomId);
        return new StrategicCastleSnapshot(GotCastles.castlelessOriginCastleId(canonicalKingdomId), displayName, origin, CastleTier.SETTLEMENT, true, canonicalKingdomId, houseId, GotCastles.defaultReligionIdForKingdom(canonicalKingdomId), lordName, null, false, false, 0L);
    }

    private static boolean isCastlelessOriginId(String castleId) {
        return castleId != null && castleId.startsWith("castleless_origin_");
    }

    private static boolean isDispossessedKingdom(class_3218 world, String kingdomId) {
        return GotCastles.isDispossessedKingdom(world, kingdomId, GotCastles.snapshotStrategicCastles(world));
    }

    private static boolean isDispossessedKingdom(class_3218 world, String kingdomId, StrategicCastleSnapshotSet strategicCastles) {
        String canonical = GotCastles.canonicalKingdomId(kingdomId);
        if (canonical.isBlank() || GotKingdoms.isCastlelessOriginKingdom(canonical)) {
            return false;
        }
        boolean isAiWarKingdom = GotKingdoms.AI_WAR_KINGDOMS.stream().anyMatch(k -> canonical.equals(k.getId().toString()));
        if (!isAiWarKingdom) {
            return false;
        }
        boolean hasOriginalCastles = overlaySites.stream().anyMatch(site -> {
            CastlePresetMetadata meta = presetMetadataByCastleId.get(site.castleId());
            return meta != null && canonical.equals(GotCastles.canonicalKingdomId(meta.kingdomId()));
        });
        if (!hasOriginalCastles) {
            return false;
        }
        return strategicCastles.castlesForKingdom(canonical).isEmpty();
    }

    @Nullable
    private static StrategicCastleSnapshot buildDispossessedOriginSnapshot(class_3218 world, String kingdomId) {
        String canonical = GotCastles.canonicalKingdomId(kingdomId);
        long seed = world.method_8412() ^ (long)canonical.hashCode() ^ GotGameRules.trackedDay(world) / 3L;
        seed ^= seed >>> 33;
        seed *= -49064778989728563L;
        seed ^= seed >>> 33;
        int originX = (int)((seed & 0xFFFFL) % 16000L) - 8000;
        int originZ = (int)((seed >>> 16 & 0xFFFFL) % 16000L) - 8000;
        class_2338 origin = new class_2338(originX, 64, originZ);
        String lordName = GotCastles.resolvePendingLordNameForKingdom(world, canonical);
        String houseId = GotCastles.resolveOwnedHouseId("", canonical, "");
        return new StrategicCastleSnapshot("dispossessed_origin_" + canonical.replace(':', '_'), "Dispossessed Origin", origin, CastleTier.SETTLEMENT, true, canonical, houseId, GotCastles.defaultReligionIdForKingdom(canonical), lordName, null, false, false, 0L);
    }

    private static String resolvePendingLordNameForKingdom(class_3218 world, String kingdomId) {
        for (OverlayCastleSite site : overlaySites) {
            String name;
            CastlePresetMetadata meta = presetMetadataByCastleId.get(site.castleId());
            if (meta == null || !kingdomId.equals(GotCastles.canonicalKingdomId(meta.kingdomId())) || (name = GotCastles.resolvePendingLordName(world, site, meta, kingdomId)).isBlank()) continue;
            return name;
        }
        return "Unknown Lord";
    }

    private static String strategicLordCastleId(String castleId, String lordId) {
        if (castleId == null || castleId.isBlank()) {
            return "";
        }
        if (lordId == null || lordId.isBlank()) {
            return castleId;
        }
        return castleId + "#lord:" + Integer.toHexString(lordId.hashCode());
    }

    private static String realCastleIdForStrategicId(String castleId) {
        if (castleId == null) {
            return "";
        }
        int marker = castleId.indexOf("#lord:");
        return marker < 0 ? castleId : castleId.substring(0, marker);
    }

    private static String castlelessOriginCastleId(String kingdomId) {
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        int separatorIndex = canonicalKingdomId.indexOf(58);
        return "castleless_origin_" + (separatorIndex >= 0 ? canonicalKingdomId.substring(separatorIndex + 1) : canonicalKingdomId);
    }

    @Nullable
    private static UUID parseUuid(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return UUID.fromString(value);
        }
        catch (IllegalArgumentException exception) {
            return null;
        }
    }

    private static String castlelessOriginLordName(String kingdomId) {
        return switch (GotCastles.canonicalKingdomId(kingdomId)) {
            case "agob:blackfyre" -> "Daemon Blackfyre";
            case "agob:golden_company" -> "Myles Toyne";
            case "agob:meereen" -> "Hizdahr Loraq";
            case "agob:yunkai" -> "Grazdan mo Eraz";
            case "agob:astapor" -> "Kraznys mo Nakloz";
            case "agob:qarth" -> "Xaro Daxos";
            default -> "Unknown Captain";
        };
    }

    private static int castlelessOriginColor(String kingdomId) {
        return switch (GotCastles.canonicalKingdomId(kingdomId)) {
            case "agob:blackfyre" -> 30326;
            case "agob:golden_company" -> 5786380;
            default -> 0;
        };
    }

    @Nullable
    private static class_2338 castlelessFixedOrigin(String kingdomId) {
        return switch (GotCastles.canonicalKingdomId(kingdomId)) {
            case "agob:meereen" -> new class_2338(5600, 64, 6500);
            case "agob:yunkai" -> new class_2338(3600, 64, 8500);
            case "agob:astapor" -> new class_2338(1200, 64, 10500);
            case "agob:qarth" -> new class_2338(11800, 64, 22500);
            default -> null;
        };
    }

    @Nullable
    private static class_2338 findMapColorOrigin(int targetColor) {
        if (targetColor == 0) {
            return null;
        }
        return CASTLELESS_COLOR_ORIGIN_CACHE.computeIfAbsent(targetColor, color -> Optional.ofNullable(GotCastles.scanMapColorOrigin(color))).orElse(null);
    }

    @Nullable
    private static class_2338 scanMapColorOrigin(int targetColor) {
        return CastleMapResourceHelper.mapColorOrigin(targetColor, worldMapImage, WORLD_MAP_PATH, GotCastles.class.getClassLoader());
    }

    private static String resolvePendingCastleKingdomId(class_3218 world, OverlayCastleSite site, @Nullable CastlePresetMetadata metadata) {
        Kingdom kingdom = GotCastles.resolveKingdomForBiome(world, site.anchor());
        return GotCastles.canonicalKingdomId(metadata != null && !metadata.kingdomId().isBlank() ? metadata.kingdomId() : (kingdom != null ? kingdom.getId().toString() : ""));
    }

    private static String resolvePendingCastleReligionId(@Nullable CastlePresetMetadata metadata, String kingdomId) {
        return metadata != null && !metadata.religionId().isBlank() ? metadata.religionId() : GotCastles.defaultReligionIdForKingdom(kingdomId);
    }

    private static int strategicCastleCooldownSeconds(class_3218 world, StrategicCastleSnapshot castle) {
        if (world == null || castle == null) {
            return 0;
        }
        long endsAt = castle.raidEndsAtTick();
        if (endsAt <= 0L) {
            return 0;
        }
        long remaining = Math.max(0L, endsAt - world.method_8510());
        return class_3532.method_15386((float)((float)remaining / 20.0f));
    }

    private static void setStrategicRaidEndsAtTick(class_3218 world, String castleId, long raidEndsAtTick) {
        StrategicCastleSnapshot snapshot;
        CastleInstance placed = GotCastles.getWorldState(world).get(castleId);
        if (placed != null) {
            placed.setRaidState(CastleRaidState.CONTESTED);
            placed.setRaidEndsAtTick(raidEndsAtTick);
        }
        if ((snapshot = GotCastles.resolveStrategicCastle(world, castleId)) != null && !snapshot.generated()) {
            GotCastles.putPendingCastleOwnership(world, snapshot.castleId(), snapshot.ownerPlayerUuid() == null ? "" : snapshot.ownerPlayerUuid().toString(), snapshot.ownerKingdomId(), snapshot.ownerHouseId(), snapshot.ownerReligionId(), snapshot.lordName(), raidEndsAtTick);
        }
    }

    private static void putPendingCastleOwnership(class_3218 world, String castleId, String ownerPlayerUuid, String ownerKingdomId, String ownerHouseId, String ownerReligionId, String lordName, long raidEndsAtTick) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return;
        }
        String kingdomId = GotCastles.canonicalKingdomId(ownerKingdomId);
        PendingCastleOwnershipWorldState.get(world).put(new PendingCastleOwnershipWorldState.PendingCastleOwnership(castleId, ownerPlayerUuid, kingdomId, GotCastles.resolveOwnedHouseId(ownerHouseId, kingdomId, ""), ownerReligionId, lordName, raidEndsAtTick));
    }

    private static int executeGotoCastle(class_2168 source, String castleId) {
        OverlayCastleSite site;
        class_3218 world = source.method_9211().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Iceandfire dimension is not loaded."));
            return 0;
        }
        if (castleId == null || castleId.isBlank()) {
            source.method_9213((class_2561)class_2561.method_43470((String)("No worldgen castle plan exists for " + castleId + ".")));
            return 0;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        CastleInstance castle = state.get(castleId);
        OverlayCastleSite overlayCastleSite = site = castle == null ? GotCastles.findOverlaySite(castleId) : null;
        if (castle == null && site == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)("No worldgen castle plan exists for " + castleId + ".")));
            return 0;
        }
        class_2338 anchor = castle != null ? castle.getBannerPos() : site.anchor();
        class_2338 arrival = GotCastles.estimateInitialCastleTeleportPos(world, anchor);
        String displayName = castle != null ? GotCastles.displayNameForCastle(castle) : GotCastles.pendingDisplayName(site);
        class_1297 entity = source.method_9228();
        if (entity instanceof class_3222) {
            CastleInstance preparedCastle;
            class_3222 player = (class_3222)entity;
            CastleInstance castleInstance = preparedCastle = castle != null ? castle : GotCastles.ensureWorldgenCastleClaim(world, castleId, true, false);
            if (preparedCastle == null) {
                source.method_9213((class_2561)class_2561.method_43470((String)("Could not prepare castle " + castleId + ".")));
                return 0;
            }
            arrival = GotCastles.estimateInitialCastleTeleportPos(world, preparedCastle.getBannerPos());
            player.method_48105(world, (double)arrival.method_10263() + 0.5, (double)arrival.method_10264(), (double)arrival.method_10260() + 0.5, Set.of(), player.method_36454(), player.method_36455());
            player.method_24830(true);
            player.method_18800(0.0, 0.0, 0.0);
            String preparedDisplayName = preparedCastle != null ? GotCastles.displayNameForCastle(preparedCastle) : displayName;
            source.method_9226(() -> class_2561.method_43470((String)("Teleported to " + preparedDisplayName + ".")), false);
            return 1;
        }
        CastleInstance preparedCastle = castle != null ? castle : GotCastles.ensureWorldgenCastleClaim(world, castleId, true);
        String preparedDisplayName = preparedCastle != null ? GotCastles.displayNameForCastle(preparedCastle) : displayName;
        class_2338 preparedArrival = arrival;
        source.method_9226(() -> class_2561.method_43470((String)("Prepared " + preparedDisplayName + " at " + preparedArrival.method_23854())), false);
        return 1;
    }

    private static class_2338 estimateInitialCastleTeleportPos(class_3218 world, class_2338 anchor) {
        int surfaceY = world.method_8615() + 64;
        class_2794 class_27942 = world.method_14178().method_12129();
        if (class_27942 instanceof MapChunkGenerator) {
            MapChunkGenerator mapGenerator = (MapChunkGenerator)class_27942;
            surfaceY = mapGenerator.estimateMotionBlockingSurfaceY(anchor.method_10263(), anchor.method_10260());
        }
        int y = class_3532.method_15340((int)(surfaceY + 4), (int)(world.method_31607() + 2), (int)(world.method_31600() - 2));
        return new class_2338(anchor.method_10263(), y, anchor.method_10260());
    }

    private static int executeFindCastles(class_2168 source) {
        class_5250 line;
        String name;
        class_3218 world = source.method_9211().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Iceandfire dimension is not loaded."));
            return 0;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        if (overlaySites.isEmpty() && state.getAll().isEmpty()) {
            source.method_9213((class_2561)class_2561.method_43470((String)"No castles have been generated yet."));
            return 0;
        }
        source.method_9226(() -> class_2561.method_43470((String)("Claims and castles in iceandfire: " + state.getAll().size())), false);
        for (CastleInstance castle : state.getAll()) {
            class_2338 pos = castle.getBannerPos();
            HireableCampDefinition camp = GotCastles.getHireableCampDefinition(castle);
            String clickCommand = camp == null ? "/castle goto " + castle.getId() : "/execute in " + String.valueOf(GotDimensionKeys.ICEANDFIRE.method_29177()) + " run tp @s " + pos.method_10263() + " " + pos.method_10264() + " " + pos.method_10260();
            class_5250 coords = class_2561.method_43470((String)("[" + pos.method_10263() + ", " + pos.method_10264() + ", " + pos.method_10260() + "]")).method_10862(class_2583.field_24360.method_10977(class_124.field_1075).method_10958(new class_2558(class_2558.class_2559.field_11750, clickCommand)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)class_2561.method_43470((String)(camp == null ? "Prepare and teleport to castle" : "Teleport to claim banner")))));
            String owner = camp != null ? "Camp" : (!castle.getOwnerKingdomId().isBlank() ? "Kingdom" : (castle.getOwnerPlayerUuid() != null ? "Player" : "Unowned"));
            String string = name = camp != null ? camp.claimName() : GotCastles.displayNameForCastle(castle);
            String tierName = camp != null ? camp.tierName() : (castle.getTier() != null ? castle.getTier().getDisplayName() : "Unknown");
            line = class_2561.method_43470((String)("- " + name + " ")).method_10852((class_2561)class_2561.method_43470((String)("(" + owner + ", " + tierName + ") ")).method_27692(class_124.field_1080)).method_10852((class_2561)coords);
            source.method_9226(() -> line, false);
        }
        if (state.getAll().size() == overlaySites.size()) {
            return state.getAll().size();
        }
        source.method_9226(() -> class_2561.method_43470((String)("Pending overlay castle sites: " + overlaySites.size())), false);
        for (OverlayCastleSite site : overlaySites) {
            CastleInstance castle = state.get(site.castleId());
            if (castle != null) continue;
            boolean rejected = state.isRejected(site.castleId());
            class_2338 pos = castle != null ? castle.getOrigin() : new class_2338(site.anchor().method_10263(), 120, site.anchor().method_10260());
            String clickCommand = rejected ? "/execute in " + String.valueOf(GotDimensionKeys.ICEANDFIRE.method_29177()) + " run tp @s " + pos.method_10263() + " " + pos.method_10264() + " " + pos.method_10260() : "/castle goto " + site.castleId();
            class_5250 coords = class_2561.method_43470((String)("[" + pos.method_10263() + ", " + pos.method_10264() + ", " + pos.method_10260() + "]")).method_10862(class_2583.field_24360.method_10977(class_124.field_1075).method_10958(new class_2558(class_2558.class_2559.field_11750, clickCommand)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)class_2561.method_43470((String)(rejected ? "Teleport to rejected castle site" : "Prepare and teleport to castle")))));
            String string = name = castle != null ? GotCastles.displayNameForCastle(castle) : GotCastles.pendingDisplayName(site);
            String status = castle != null ? castle.getTier().getDisplayName() : (rejected ? "Rejected" : "Pending");
            line = class_2561.method_43470((String)("- " + name + " ")).method_10852((class_2561)class_2561.method_43470((String)("(" + status + ") ")).method_27692(class_124.field_1080)).method_10852((class_2561)coords);
            source.method_9226(() -> line, false);
        }
        return state.getAll().size();
    }

    private static int executeFindCamps(class_2168 source) {
        class_3218 world = source.method_9211().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Iceandfire dimension is not loaded."));
            return 0;
        }
        if (DynamicTroopRegistry.getHireableCampDefinitions().isEmpty()) {
            source.method_9213((class_2561)class_2561.method_43470((String)"No hireable camps are configured."));
            return 0;
        }
        class_2338 origin = class_2338.method_49638((class_2374)source.method_9222());
        List<MapChunkGenerator.PredictedHireableCampSite> camps = MapChunkGenerator.findNearestHireableCampSites(world, origin, 10);
        if (camps.isEmpty()) {
            source.method_9213((class_2561)class_2561.method_43470((String)"No matching hireable camp sites were found near the current position."));
            return 0;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        source.method_9226(() -> class_2561.method_43470((String)("Nearest predicted hireable camps: " + camps.size())), false);
        for (MapChunkGenerator.PredictedHireableCampSite camp : camps) {
            class_2338 pos = camp.teleportPos();
            String clickCommand = "/execute in " + String.valueOf(GotDimensionKeys.ICEANDFIRE.method_29177()) + " run tp @s " + pos.method_10263() + " " + pos.method_10264() + " " + pos.method_10260();
            class_5250 coords = class_2561.method_43470((String)("[" + pos.method_10263() + ", " + pos.method_10264() + ", " + pos.method_10260() + "]")).method_10862(class_2583.field_24360.method_10977(class_124.field_1075).method_10958(new class_2558(class_2558.class_2559.field_11750, clickCommand)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)class_2561.method_43470((String)"Teleport to predicted camp site"))));
            boolean generated = state.contains(GotCastles.hireableCampClaimId(camp.definitionId(), camp.chunkPos()));
            String kingdomName = GotCastles.resolveKingdomName(camp.kingdomId());
            class_5250 line = class_2561.method_43470((String)("- " + camp.claimName() + " ")).method_10852((class_2561)class_2561.method_43470((String)("(" + (generated ? "Generated" : "Predicted") + ", " + kingdomName + ") ")).method_27692(class_124.field_1080)).method_10852((class_2561)coords);
            source.method_9226(() -> line, false);
        }
        return camps.size();
    }

    private static int executeFindWeirwoods(class_2168 source) {
        class_3218 world = source.method_9211().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Iceandfire dimension is not loaded."));
            return 0;
        }
        class_2338 origin = class_2338.method_49638((class_2374)source.method_9222());
        List<PredictedWeirwoodSite> weirwoods = GotCastles.findNearestWeirwoodSites(world, origin, 3);
        if (weirwoods.isEmpty()) {
            source.method_9213((class_2561)class_2561.method_43470((String)"No matching weirwood tree sites were found near the current position."));
            return 0;
        }
        WeirwoodTreeWorldState state = WeirwoodTreeWorldState.get(world);
        source.method_9226(() -> class_2561.method_43470((String)("Nearest weirwood trees: " + weirwoods.size())), false);
        for (PredictedWeirwoodSite weirwood : weirwoods) {
            class_2338 pos = weirwood.pos();
            String clickCommand = "/execute in " + String.valueOf(GotDimensionKeys.ICEANDFIRE.method_29177()) + " run tp @s " + pos.method_10263() + " " + pos.method_10264() + " " + pos.method_10260();
            class_5250 coords = class_2561.method_43470((String)("[" + pos.method_10263() + ", " + pos.method_10264() + ", " + pos.method_10260() + "]")).method_10862(class_2583.field_24360.method_10977(class_124.field_1075).method_10958(new class_2558(class_2558.class_2559.field_11750, clickCommand)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)class_2561.method_43470((String)"Teleport to weirwood tree"))));
            boolean generated = state.contains(weirwood.castleId());
            class_5250 line = class_2561.method_43470((String)("- " + weirwood.castleName() + " ")).method_10852((class_2561)class_2561.method_43470((String)("(" + (generated ? "Generated" : "Predicted") + ") ")).method_27692(class_124.field_1080)).method_10852((class_2561)coords);
            source.method_9226(() -> line, false);
        }
        return weirwoods.size();
    }

    private static String pendingDisplayName(OverlayCastleSite site) {
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(site.castleId());
        if (metadata != null && !metadata.name().isBlank()) {
            return metadata.name();
        }
        CastleDefinition definition = GotCastles.get(site.definitionId());
        String label = definition != null ? definition.displayName() : site.definitionId();
        return label + " " + site.index();
    }

    public static String displayNameForCastle(CastleInstance castle) {
        if ("player_claim".equals(castle.getDefinitionId())) {
            return "Player Settlement";
        }
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(castle.getId());
        if (metadata != null && !metadata.name().isBlank()) {
            return metadata.name();
        }
        return castle.getTitle().displayName();
    }

    public static CastleTier resolveCastleTier(class_3218 world, String castleId) {
        StrategicCastleSnapshot snapshot = GotCastles.resolveStrategicCastle(world, castleId);
        return snapshot == null ? CastleTier.SETTLEMENT : snapshot.tier();
    }

    public static String resolveHouseIdForCastle(@Nullable CastleInstance castle) {
        if (castle == null) {
            return "";
        }
        String kingdomId = GotCastles.canonicalKingdomId(castle.getOwnerKingdomId());
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(castle.getId());
        return GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), kingdomId, metadata != null ? metadata.houseId() : "");
    }

    public static void resetPlayerHoldingsForRepledge(class_3218 world, class_3222 player) {
        if (world == null || player == null) {
            return;
        }
        UUID playerUuid = player.method_5667();
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        String originalKingdomId = character.getOriginalSelectedKingdomId();
        String originalHouseId = character.getOriginalSelectedOptionId();
        CastleWorldState state = GotCastles.getWorldState(world);
        ActiveCastleInvasion activeInvasion = ACTIVE_CASTLE_INVASIONS.get(playerUuid);
        if (activeInvasion != null) {
            GotCastles.endCastleInvasion(world, playerUuid, activeInvasion.castleId(), false);
        }
        for (CastleInstance castle : List.copyOf(state.getAll())) {
            if (!playerUuid.equals(castle.getOwnerPlayerUuid())) continue;
            PendingCastleOwnershipWorldState.get(world).remove(castle.getId());
            if ("player_claim".equals(castle.getDefinitionId())) {
                GotCastles.endCastleRecapture(world, castle.getId(), false, false);
                GotCastles.removeBoundSettlers(world, castle);
                GotCastles.removeSettlementTentStructureIfNeeded(world, castle);
                state.remove(castle.getId());
                GotCastles.clearPriorityCastleGeneration(world, castle.getId());
                GotCastles.removePlayerClaimConfigEntryIfNeeded(castle);
                continue;
            }
            if (state.getRecapture(castle.getId()) != null) {
                GotCastles.endCastleRecapture(world, castle.getId(), false, false);
            }
            if (KingdomAiInvasionWorldState.get(world).get(castle.getId()) != null) {
                GotCastles.endKingdomAiInvasion(world, castle.getId(), false);
                PendingCastleOwnershipWorldState.get(world).remove(castle.getId());
            }
            if (GotDynastyNpcs.isDynastyOwnedLordName(world, playerUuid, castle.getLordName())) {
                castle.setOwnerPlayerUuid(playerUuid);
                castle.setOwnerKingdomId(originalKingdomId);
                castle.setOwnerHouseId(GotCastles.resolveOwnedHouseId(originalHouseId, originalKingdomId, ""));
                castle.setOwnerReligionId(GotCastles.defaultReligionIdForKingdom(originalKingdomId));
                GotCastles.respawnCastlePopulation(world, castle);
                continue;
            }
            String kingdomId = GotCastles.originalKingdomIdFor(castle);
            CastlePresetMetadata metadata = presetMetadataByCastleId.get(castle.getId());
            castle.setOwnerPlayerUuid(null);
            castle.setOwnerKingdomId(kingdomId);
            castle.setOwnerHouseId(GotCastles.resolveOwnedHouseId("", kingdomId, metadata != null ? metadata.houseId() : ""));
            castle.setOwnerReligionId(GotCastles.originalReligionIdFor(castle, kingdomId));
            castle.setLordName("");
            GotCastles.respawnCastlePopulation(world, castle);
        }
        GotCastles.syncStateToPlayers(world);
        GotCastles.syncCastleMapToPlayers(world);
    }

    private static int getInt(JsonObject root, String key, int fallback) {
        return root.has(key) ? root.get(key).getAsInt() : fallback;
    }

    private static boolean reconcilePlayerSettlementSettlersBatch(class_3218 world, List<CastleInstance> castles, int budget) {
        boolean changed = false;
        int processed = 0;
        while (processed < budget && !castles.isEmpty()) {
            CastleInstance castle;
            if (playerSettlementCursor >= castles.size()) {
                playerSettlementCursor = 0;
            }
            if (!"player_claim".equals((castle = castles.get(playerSettlementCursor++)).getDefinitionId())) {
                ++processed;
                continue;
            }
            if (!world.method_8393(castle.getBannerPos().method_10263() >> 4, castle.getBannerPos().method_10260() >> 4)) {
                ++processed;
                continue;
            }
            long refreshDay = GotCastles.resolveCastleRefreshDay(castle, world);
            if (refreshDay > castle.getCreatedDay() && castle.getLastSettlerProductionDay() < refreshDay) {
                changed |= GotCastles.produceSettlerResources(world, castle, refreshDay);
            }
            if (refreshDay > castle.getCreatedDay() && castle.getLastSettlerOfferDay() < refreshDay) {
                GotCastles.refreshPendingSettlers(world, castle, refreshDay);
                castle.setLastSettlerOfferDay(refreshDay);
                changed = true;
            }
            changed |= GotCastles.reconcileAcceptedSettlers(world, castle, refreshDay);
            castle.setLastPopulationRefreshDay(Math.max(castle.getLastPopulationRefreshDay(), refreshDay));
            castle.setLastObservedPopulationTimeOfDay(GotCastles.normalizedTimeOfDay(world.method_8532()));
            ++processed;
        }
        return changed;
    }

    private static boolean produceSettlerResources(class_3218 world, CastleInstance castle, long refreshDay) {
        boolean changed = false;
        for (SettlerProfile profile : castle.getSettlers()) {
            class_1799 produced;
            if (!profile.accepted() || (produced = GotCastles.createSettlerProductionStack(world, castle, profile, refreshDay)).method_7960()) continue;
            GotCastles.insertIntoSettlementStorage(castle, produced);
            changed = true;
        }
        castle.setLastSettlerProductionDay(refreshDay);
        return changed;
    }

    private static boolean reconcileAcceptedSettlers(class_3218 world, CastleInstance castle, long currentDay) {
        boolean changed = GotCastles.ensureSettlerProfileAttributes(castle);
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)castle.getBannerPos()), (double)128.0, (double)64.0, (double)128.0);
        List livingSettlers = world.method_8390(SettlerNpcEntity.class, searchBox, settler -> settler.method_5805() && castle.getId().equals(settler.got$getCastle()) && settler.isAcceptedSettler());
        HashSet<String> seenSettlerIds = new HashSet<String>();
        for (SettlerNpcEntity settler2 : livingSettlers) {
            String settlerId = settler2.getSettlerId();
            if (settlerId.isBlank() || seenSettlerIds.add(settlerId)) continue;
            settler2.method_31472();
            changed = true;
        }
        if (changed) {
            livingSettlers = world.method_8390(SettlerNpcEntity.class, searchBox, settler -> settler.method_5805() && castle.getId().equals(settler.got$getCastle()) && settler.isAcceptedSettler());
        }
        HashSet<String> knownProfileIds = new HashSet<String>();
        long acceptedCount = 0L;
        for (SettlerProfile profile : castle.getSettlers()) {
            knownProfileIds.add(profile.id());
            if (!profile.accepted()) continue;
            ++acceptedCount;
        }
        ArrayList<SettlerProfile> recoveredProfiles = null;
        int capacity = GotCastles.settlerCapacityFor(castle);
        for (SettlerNpcEntity settler3 : livingSettlers) {
            String settlerId = settler3.getSettlerId();
            if (settlerId.isBlank() || knownProfileIds.contains(settlerId) || acceptedCount >= (long)capacity) continue;
            if (recoveredProfiles == null) {
                recoveredProfiles = new ArrayList<SettlerProfile>(castle.getSettlers());
            }
            recoveredProfiles.add(GotCastles.profileFromAcceptedSettlerEntity(settler3, currentDay));
            knownProfileIds.add(settlerId);
            ++acceptedCount;
            changed = true;
        }
        if (recoveredProfiles != null) {
            castle.setSettlers(recoveredProfiles);
        }
        for (SettlerProfile profile : castle.getSettlers()) {
            if (!profile.accepted() || currentDay < profile.offeredDay()) continue;
            SettlerNpcEntity existingSettler = livingSettlers.stream().filter(settler -> profile.id().equals(settler.getSettlerId())).findFirst().orElse(null);
            if (existingSettler == null) {
                GotCastles.spawnSettler(world, castle, profile, true);
                changed = true;
                continue;
            }
            GotCastles.applyNpcAttributes((AbstractKingdomHumanoidEntity)existingSettler, profile);
            if (profile.displayName().isBlank()) continue;
            existingSettler.method_5665((class_2561)class_2561.method_43470((String)profile.displayName()));
        }
        return changed;
    }

    private static boolean ensureSettlerProfileAttributes(CastleInstance castle) {
        boolean changed = false;
        ArrayList<SettlerProfile> updated = new ArrayList<SettlerProfile>();
        for (SettlerProfile profile : castle.getSettlers()) {
            if (profile.attributes().isAssigned()) {
                updated.add(profile);
                continue;
            }
            CharacterSelectionOption option = GotCharacterOptions.get(profile.optionId());
            if (option == null) {
                option = GotCastles.characterOptionForCastle(castle);
            }
            updated.add(new SettlerProfile(profile.id(), profile.displayName(), profile.optionId(), profile.professionId(), profile.skillTierId(), profile.accepted(), profile.offeredDay(), NpcAttributeProfile.generate(option, profile.id())));
            changed = true;
        }
        if (changed) {
            castle.setSettlers(updated);
        }
        return changed;
    }

    private static class_1799 createSettlerProductionStack(class_3218 world, CastleInstance castle, SettlerProfile profile, long refreshDay) {
        List<SettlerProfessionProductionRegistry.ProductionEntry> entries = SettlerProfessionProductionRegistry.getEntries(profile.profession());
        if (entries.isEmpty()) {
            return class_1799.field_8037;
        }
        int entryIndex = GotCastles.deterministicRange(castle, refreshDay, profile.id().hashCode() ^ profile.profession().ordinal(), entries.size());
        class_1792 item = GotCastles.resolveProductionItem(world, castle, entries.get(entryIndex));
        if (item == null) {
            return class_1799.field_8037;
        }
        int amount = GotCastles.settlerProductionAmount(castle, profile, refreshDay);
        return amount <= 0 ? class_1799.field_8037 : new class_1799((class_1935)item, amount);
    }

    private static class_1792 resolveProductionItem(class_3218 world, CastleInstance castle, SettlerProfessionProductionRegistry.ProductionEntry entry) {
        return switch (entry.type()) {
            default -> throw new MatchException(null, null);
            case SettlerProfessionProductionRegistry.ProductionEntryType.ITEM -> {
                if (entry.itemId() == null) {
                    yield null;
                }
                yield (class_1792)class_7923.field_41178.method_10223(entry.itemId());
            }
            case SettlerProfessionProductionRegistry.ProductionEntryType.BIOME_LOG -> {
                switch (GotCastles.settlementWoodType(world, castle)) {
                    case SPRUCE: {
                        yield class_1802.field_8684;
                    }
                    case BIRCH: {
                        yield class_1802.field_8170;
                    }
                    case ACACIA: {
                        yield class_1802.field_8820;
                    }
                    case JUNGLE: {
                        yield class_1802.field_8125;
                    }
                    case DARK_OAK: {
                        yield class_1802.field_8652;
                    }
                    case MANGROVE: {
                        yield class_1802.field_37512;
                    }
                    case BAMBOO: {
                        yield class_1802.field_41066;
                    }
                    case CHERRY: {
                        yield class_1802.field_42692;
                    }
                }
                yield class_1802.field_8583;
            }
            case SettlerProfessionProductionRegistry.ProductionEntryType.BIOME_PLANKS -> {
                switch (GotCastles.settlementWoodType(world, castle)) {
                    case SPRUCE: {
                        yield class_1802.field_8113;
                    }
                    case BIRCH: {
                        yield class_1802.field_8191;
                    }
                    case ACACIA: {
                        yield class_1802.field_8651;
                    }
                    case JUNGLE: {
                        yield class_1802.field_8842;
                    }
                    case DARK_OAK: {
                        yield class_1802.field_8404;
                    }
                    case MANGROVE: {
                        yield class_1802.field_37507;
                    }
                    case BAMBOO: {
                        yield class_1802.field_40213;
                    }
                    case CHERRY: {
                        yield class_1802.field_42687;
                    }
                }
                yield class_1802.field_8118;
            }
        };
    }

    private static int settlerProductionAmount(CastleInstance castle, SettlerProfile profile, long refreshDay) {
        IntRange range = switch (profile.profession()) {
            default -> throw new MatchException(null, null);
            case SettlerProfession.FARMER -> {
                switch (profile.skillTier()) {
                    default: {
                        throw new MatchException(null, null);
                    }
                    case WOOD: {
                        yield new IntRange(1, 2);
                    }
                    case STONE: {
                        yield new IntRange(3, 4);
                    }
                    case COPPER: {
                        yield new IntRange(4, 5);
                    }
                    case IRON: {
                        yield new IntRange(6, 7);
                    }
                    case STEEL: 
                }
                yield new IntRange(8, 9);
            }
            case SettlerProfession.LUMBERJACK -> {
                switch (profile.skillTier()) {
                    default: {
                        throw new MatchException(null, null);
                    }
                    case WOOD: {
                        yield new IntRange(2, 4);
                    }
                    case STONE: {
                        yield new IntRange(4, 6);
                    }
                    case COPPER: {
                        yield new IntRange(6, 8);
                    }
                    case IRON: {
                        yield new IntRange(8, 10);
                    }
                    case STEEL: 
                }
                yield new IntRange(11, 16);
            }
            case SettlerProfession.MINER -> {
                switch (profile.skillTier()) {
                    default: {
                        throw new MatchException(null, null);
                    }
                    case WOOD: {
                        yield new IntRange(4, 6);
                    }
                    case STONE: {
                        yield new IntRange(6, 8);
                    }
                    case COPPER: {
                        yield new IntRange(8, 10);
                    }
                    case IRON: {
                        yield new IntRange(11, 16);
                    }
                    case STEEL: 
                }
                yield new IntRange(20, 24);
            }
        };
        return range.min() + GotCastles.deterministicRange(castle, refreshDay, profile.id().hashCode() ^ profile.skillTier().ordinal() ^ profile.profession().ordinal() * 97, range.max() - range.min() + 1);
    }

    private static int deterministicRange(CastleInstance castle, long currentDay, int salt, int bound) {
        if (bound <= 0) {
            return 0;
        }
        long seed = (long)castle.getId().hashCode() * 31L ^ currentDay * 17L ^ (long)salt;
        seed ^= seed >>> 33;
        seed *= -49064778989728563L;
        seed ^= seed >>> 33;
        return Math.floorMod((int)seed, bound);
    }

    private static void insertIntoSettlementStorage(CastleInstance castle, class_1799 stack) {
        if (stack.method_7960()) {
            return;
        }
        for (class_1799 existing : castle.getStorageItems()) {
            int transfer;
            if (existing.method_7960() || !class_1799.method_31577((class_1799)existing, (class_1799)stack) || (transfer = Math.min(stack.method_7947(), existing.method_7914() - existing.method_7947())) <= 0) continue;
            existing.method_7933(transfer);
            stack.method_7934(transfer);
            if (!stack.method_7960()) continue;
            return;
        }
        for (int slot = 0; slot < castle.getStorageItems().size(); ++slot) {
            if (!((class_1799)castle.getStorageItems().get(slot)).method_7960()) continue;
            castle.getStorageItems().set(slot, (Object)stack.method_7972());
            stack.method_7939(0);
            return;
        }
    }

    private static CastleWoodType settlementWoodType(class_3218 world, CastleInstance castle) {
        String biomePath = world.method_23753(castle.getBannerPos()).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        if (biomePath.contains("north") || biomePath.contains("taiga") || biomePath.contains("snow") || biomePath.contains("wolfswood") || biomePath.contains("always_winter")) {
            return CastleWoodType.SPRUCE;
        }
        if (biomePath.contains("birch")) {
            return CastleWoodType.BIRCH;
        }
        if (biomePath.contains("rainwood") || biomePath.contains("jungle")) {
            return CastleWoodType.JUNGLE;
        }
        if (biomePath.contains("savanna") || biomePath.contains("dorne") || biomePath.contains("desert")) {
            return CastleWoodType.ACACIA;
        }
        if (biomePath.contains("swamp") || biomePath.contains("mangrove")) {
            return CastleWoodType.MANGROVE;
        }
        if (biomePath.contains("dark") || biomePath.contains("haunted")) {
            return CastleWoodType.DARK_OAK;
        }
        if (biomePath.contains("bamboo")) {
            return CastleWoodType.BAMBOO;
        }
        if (biomePath.contains("cherry") || biomePath.contains("blossom")) {
            return CastleWoodType.CHERRY;
        }
        return CastleWoodType.OAK;
    }

    private static void refreshPendingSettlers(class_3218 world, CastleInstance castle, long currentDay) {
        castle.setSettlers(castle.getSettlers().stream().filter(SettlerProfile::accepted).toList());
        GotCastles.clearPendingSettlerEntities(world, castle.getId());
        int desired = GotCastles.dailySettlerOffersFor(castle, currentDay);
        for (int index = 0; index < desired; ++index) {
            SettlerProfile profile = GotCastles.createSettlerProfile(world, castle, currentDay, index);
            castle.addSettler(profile);
            GotCastles.spawnSettler(world, castle, profile, false);
        }
    }

    private static void clearPendingSettlerEntities(class_3218 world, String castleId) {
        for (SettlerNpcEntity settler : world.method_18198(class_5575.method_31795(SettlerNpcEntity.class), entity -> castleId.equals(entity.got$getCastle()) && !entity.isAcceptedSettler())) {
            settler.method_31472();
        }
    }

    private static int dailySettlerOffersFor(CastleInstance castle, long currentDay) {
        SettlerLevelRule rule = GotCastles.settlerRuleFor(castle);
        if (rule.maxDailyOffers() <= rule.minDailyOffers()) {
            return rule.minDailyOffers();
        }
        return rule.minDailyOffers() + Math.floorMod(castle.getId().hashCode() + (int)currentDay, rule.maxDailyOffers() - rule.minDailyOffers() + 1);
    }

    private static int settlerCapacityFor(CastleInstance castle) {
        return GotCastles.settlerRuleFor(castle).maxAcceptedSettlers();
    }

    private static String nextLevelRequirementLabel(CastleInstance castle) {
        return "";
    }

    private static SettlerLevelRule settlerRuleFor(CastleInstance castle) {
        PlayerSettlementTier tier = castle.getSettlementTier();
        int level = Math.max(1, Math.min(5, castle.getSettlementLevel()));
        return switch (tier) {
            default -> throw new MatchException(null, null);
            case PlayerSettlementTier.PLAYER_SETTLEMENT -> {
                switch (level) {
                    case 1: {
                        yield new SettlerLevelRule(0, 1, 1, 1, 0, 0, 0, null);
                    }
                    case 2: {
                        yield new SettlerLevelRule(0, 1, 2, 2, 0, 0, 0, null);
                    }
                    case 3: {
                        yield new SettlerLevelRule(0, 2, 3, 3, 0, 0, 0, null);
                    }
                    case 4: {
                        yield new SettlerLevelRule(0, 2, 5, 5, 1, 1, 1, null);
                    }
                }
                yield new SettlerLevelRule(1, 3, 6, 6, 2, 2, 2, PlayerSettlementTier.SETTLEMENT);
            }
            case PlayerSettlementTier.SETTLEMENT -> {
                switch (level) {
                    case 1: {
                        yield new SettlerLevelRule(1, 3, 8, 8, 0, 0, 0, null);
                    }
                    case 2: {
                        yield new SettlerLevelRule(1, 3, 10, 10, 0, 0, 0, null);
                    }
                    case 3: {
                        yield new SettlerLevelRule(1, 3, 12, 12, 0, 0, 0, null);
                    }
                    case 4: {
                        yield new SettlerLevelRule(1, 4, 14, 14, 2, 2, 2, null);
                    }
                }
                yield new SettlerLevelRule(1, 4, 16, 16, 3, 3, 3, PlayerSettlementTier.LORDSHIP);
            }
            case PlayerSettlementTier.LORDSHIP -> {
                switch (level) {
                    case 1: {
                        yield new SettlerLevelRule(1, 4, 18, 18, 0, 0, 0, null);
                    }
                    case 2: {
                        yield new SettlerLevelRule(1, 4, 20, 20, 0, 0, 0, null);
                    }
                    case 3: {
                        yield new SettlerLevelRule(2, 4, 22, 22, 0, 0, 0, null);
                    }
                    case 4: {
                        yield new SettlerLevelRule(2, 5, 24, 24, 3, 3, 3, null);
                    }
                }
                yield new SettlerLevelRule(2, 5, 26, 26, 4, 4, 4, PlayerSettlementTier.CAPITAL);
            }
            case PlayerSettlementTier.CAPITAL -> {
                switch (level) {
                    case 1: {
                        yield new SettlerLevelRule(2, 5, 28, 28, 0, 0, 0, null);
                    }
                    case 2: {
                        yield new SettlerLevelRule(2, 5, 30, 30, 0, 0, 0, null);
                    }
                    case 3: {
                        yield new SettlerLevelRule(2, 6, 32, 32, 0, 0, 0, null);
                    }
                    case 4: {
                        yield new SettlerLevelRule(3, 6, 34, 34, 4, 4, 4, null);
                    }
                }
                yield new SettlerLevelRule(3, 6, 36, 36, 5, 5, 5, null);
            }
        };
    }

    private static SettlerProfile createSettlerProfile(class_3218 world, CastleInstance castle, long currentDay, int index) {
        CharacterSelectionOption option = GotCastles.chooseSettlerOption(world, castle, index);
        SettlerProfession profession = SettlerProfession.values()[Math.floorMod(castle.getId().hashCode() + index + (int)currentDay, SettlerProfession.values().length)];
        SettlerSkillTier skillTier = GotCastles.chooseSettlerSkillTier(castle, currentDay, index);
        String settlerId = castle.getId() + "_settler_" + currentDay + "_" + index;
        String displayName = GotCastles.buildSettlerName(castle, option, settlerId);
        return new SettlerProfile(settlerId, displayName, option.id(), profession.getId(), skillTier.getId(), false, currentDay, NpcAttributeProfile.generate(option, settlerId));
    }

    private static String buildSettlerName(CastleInstance castle, CharacterSelectionOption option, String settlerId) {
        LinkedHashSet<String> usedNames = new LinkedHashSet<String>();
        for (SettlerProfile profile : castle.getSettlers()) {
            if (profile.displayName().isBlank()) continue;
            usedNames.add(profile.displayName());
        }
        Kingdom kingdom = GotKingdoms.get(option.kingdomId());
        if (kingdom == null) {
            return GotCastleOwnerNames.createCommonerName(option.kingdomId(), castle.getId() + "|" + settlerId, usedNames);
        }
        String houseSurname = GotCastles.normalizeHouseSurname(option.houseDisplayName());
        return GotCastleOwnerNames.createHouseLordName(kingdom, houseSurname, castle.getId() + "|" + settlerId, usedNames);
    }

    private static CharacterSelectionOption chooseSettlerOption(class_3218 world, CastleInstance castle, int index) {
        CharacterSelectionOption houseOption;
        List<CharacterSelectionOption> allOptions = GotCharacterOptions.getAll();
        if (allOptions.isEmpty()) {
            throw new IllegalStateException("No character options loaded for settler generation");
        }
        String biomePath = world.method_23753(castle.getBannerPos()).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        List<CharacterSelectionOption> biomeOptions = allOptions.stream().filter(option -> option.spawnBiomes().stream().anyMatch(biome -> biome.equalsIgnoreCase(biomePath))).toList();
        class_2960 ownerKingdomId = class_2960.method_12829((String)castle.getOwnerKingdomId());
        List<Object> kingdomOptions = ownerKingdomId == null ? List.of() : GotCharacterOptions.getByKingdom(ownerKingdomId);
        CharacterSelectionOption characterSelectionOption = houseOption = castle.getOwnerHouseId().isBlank() ? null : GotCharacterOptions.get(castle.getOwnerHouseId());
        if (houseOption != null) {
            return houseOption;
        }
        int roll = Math.floorMod(castle.getBannerPos().hashCode() + index + (int)world.method_8532(), 100);
        if (roll < 60 && !biomeOptions.isEmpty()) {
            return biomeOptions.get(Math.floorMod(index + biomePath.hashCode(), biomeOptions.size()));
        }
        if (roll < 90 && !kingdomOptions.isEmpty()) {
            return (CharacterSelectionOption)kingdomOptions.get(Math.floorMod(index + castle.getOwnerKingdomId().hashCode(), kingdomOptions.size()));
        }
        if (houseOption != null) {
            return houseOption;
        }
        if (!kingdomOptions.isEmpty()) {
            return (CharacterSelectionOption)kingdomOptions.get(Math.floorMod(index, kingdomOptions.size()));
        }
        if (!biomeOptions.isEmpty()) {
            return biomeOptions.get(Math.floorMod(index, biomeOptions.size()));
        }
        return allOptions.get(Math.floorMod(index, allOptions.size()));
    }

    private static SettlerSkillTier chooseSettlerSkillTier(CastleInstance castle, long currentDay, int index) {
        int[] cumulative;
        int roll = GotCastles.deterministicPercent(castle, currentDay, index, 1597463007L);
        if (roll < (cumulative = GotCastles.settlerSkillDistribution(castle))[0]) {
            return SettlerSkillTier.WOOD;
        }
        if (roll < cumulative[1]) {
            return SettlerSkillTier.STONE;
        }
        if (roll < cumulative[2]) {
            return SettlerSkillTier.COPPER;
        }
        if (roll < cumulative[3]) {
            return SettlerSkillTier.IRON;
        }
        return SettlerSkillTier.STEEL;
    }

    private static int deterministicPercent(CastleInstance castle, long currentDay, int index, long salt) {
        long seed = salt;
        seed ^= (long)castle.getId().hashCode() * -7046029254386353131L;
        seed ^= currentDay * -4658895280553007687L;
        seed ^= ((long)index + 1L) * -7723592293110705685L;
        seed ^= seed >>> 30;
        seed *= -4658895280553007687L;
        seed ^= seed >>> 27;
        seed *= -7723592293110705685L;
        seed ^= seed >>> 31;
        return Math.floorMod((int)seed, 100);
    }

    private static int[] settlerSkillDistribution(CastleInstance castle) {
        int[] nArray;
        block0 : switch (castle.getSettlementTier()) {
            default: {
                throw new MatchException(null, null);
            }
            case PLAYER_SETTLEMENT: {
                switch (castle.getSettlementLevel()) {
                    case 1: {
                        int[] nArray2 = new int[4];
                        nArray2[0] = 80;
                        nArray2[1] = 100;
                        nArray2[2] = 100;
                        nArray = nArray2;
                        nArray2[3] = 100;
                        break block0;
                    }
                    case 2: {
                        int[] nArray3 = new int[4];
                        nArray3[0] = 70;
                        nArray3[1] = 100;
                        nArray3[2] = 100;
                        nArray = nArray3;
                        nArray3[3] = 100;
                        break block0;
                    }
                    case 3: {
                        int[] nArray4 = new int[4];
                        nArray4[0] = 60;
                        nArray4[1] = 100;
                        nArray4[2] = 100;
                        nArray = nArray4;
                        nArray4[3] = 100;
                        break block0;
                    }
                    case 4: {
                        int[] nArray5 = new int[4];
                        nArray5[0] = 50;
                        nArray5[1] = 90;
                        nArray5[2] = 100;
                        nArray = nArray5;
                        nArray5[3] = 100;
                        break block0;
                    }
                }
                int[] nArray6 = new int[4];
                nArray6[0] = 30;
                nArray6[1] = 85;
                nArray6[2] = 100;
                nArray = nArray6;
                nArray6[3] = 100;
                break;
            }
            case SETTLEMENT: {
                switch (castle.getSettlementLevel()) {
                    case 1: {
                        int[] nArray7 = new int[4];
                        nArray7[0] = 0;
                        nArray7[1] = 80;
                        nArray7[2] = 100;
                        nArray = nArray7;
                        nArray7[3] = 100;
                        break block0;
                    }
                    case 2: {
                        int[] nArray8 = new int[4];
                        nArray8[0] = 0;
                        nArray8[1] = 65;
                        nArray8[2] = 100;
                        nArray = nArray8;
                        nArray8[3] = 100;
                        break block0;
                    }
                    case 3: {
                        int[] nArray9 = new int[4];
                        nArray9[0] = 0;
                        nArray9[1] = 50;
                        nArray9[2] = 90;
                        nArray = nArray9;
                        nArray9[3] = 100;
                        break block0;
                    }
                    case 4: {
                        int[] nArray10 = new int[4];
                        nArray10[0] = 0;
                        nArray10[1] = 35;
                        nArray10[2] = 80;
                        nArray = nArray10;
                        nArray10[3] = 100;
                        break block0;
                    }
                }
                int[] nArray11 = new int[4];
                nArray11[0] = 0;
                nArray11[1] = 20;
                nArray11[2] = 70;
                nArray = nArray11;
                nArray11[3] = 100;
                break;
            }
            case LORDSHIP: {
                switch (castle.getSettlementLevel()) {
                    case 1: {
                        int[] nArray12 = new int[4];
                        nArray12[0] = 0;
                        nArray12[1] = 0;
                        nArray12[2] = 70;
                        nArray = nArray12;
                        nArray12[3] = 100;
                        break block0;
                    }
                    case 2: {
                        int[] nArray13 = new int[4];
                        nArray13[0] = 0;
                        nArray13[1] = 0;
                        nArray13[2] = 50;
                        nArray = nArray13;
                        nArray13[3] = 100;
                        break block0;
                    }
                    case 3: {
                        int[] nArray14 = new int[4];
                        nArray14[0] = 0;
                        nArray14[1] = 0;
                        nArray14[2] = 35;
                        nArray = nArray14;
                        nArray14[3] = 100;
                        break block0;
                    }
                    case 4: {
                        int[] nArray15 = new int[4];
                        nArray15[0] = 0;
                        nArray15[1] = 0;
                        nArray15[2] = 20;
                        nArray = nArray15;
                        nArray15[3] = 100;
                        break block0;
                    }
                }
                int[] nArray16 = new int[4];
                nArray16[0] = 0;
                nArray16[1] = 0;
                nArray16[2] = 10;
                nArray = nArray16;
                nArray16[3] = 100;
                break;
            }
            case CAPITAL: {
                switch (castle.getSettlementLevel()) {
                    case 1: {
                        int[] nArray17 = new int[4];
                        nArray17[0] = 0;
                        nArray17[1] = 0;
                        nArray17[2] = 0;
                        nArray = nArray17;
                        nArray17[3] = 70;
                        break block0;
                    }
                    case 2: {
                        int[] nArray18 = new int[4];
                        nArray18[0] = 0;
                        nArray18[1] = 0;
                        nArray18[2] = 0;
                        nArray = nArray18;
                        nArray18[3] = 50;
                        break block0;
                    }
                    case 3: {
                        int[] nArray19 = new int[4];
                        nArray19[0] = 0;
                        nArray19[1] = 0;
                        nArray19[2] = 0;
                        nArray = nArray19;
                        nArray19[3] = 35;
                        break block0;
                    }
                    case 4: {
                        int[] nArray20 = new int[4];
                        nArray20[0] = 0;
                        nArray20[1] = 0;
                        nArray20[2] = 0;
                        nArray = nArray20;
                        nArray20[3] = 20;
                        break block0;
                    }
                }
                int[] nArray21 = new int[4];
                nArray21[0] = 0;
                nArray21[1] = 0;
                nArray21[2] = 0;
                nArray = nArray21;
                nArray21[3] = 10;
            }
        }
        return nArray;
    }

    private static void spawnSettler(class_3218 world, CastleInstance castle, SettlerProfile profile, boolean accepted) {
        SettlerNpcEntity entity = (SettlerNpcEntity)GotEntities.SETTLER_NPC.method_5883((class_1937)world);
        if (entity == null) {
            return;
        }
        class_2338 spawnPos = GotCastles.findNpcRespawnPos(world, castle.getBannerPos(), Math.abs(profile.id().hashCode()));
        entity.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
        entity.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16474, null);
        entity.got$setCastle(castle.getId());
        entity.setPointOfInterest(castle.getBannerPos());
        entity.setSettlerId(profile.id());
        entity.setSettlerOptionId(profile.optionId());
        entity.setProfession(profile.profession());
        entity.setSkillTier(profile.skillTier());
        entity.setAcceptedSettler(accepted || profile.accepted());
        GotCastles.applyNpcAttributes((AbstractKingdomHumanoidEntity)entity, profile);
        entity.method_5665((class_2561)class_2561.method_43470((String)profile.displayName()));
        world.method_8649((class_1297)entity);
    }

    private static OpenCastleScreenPayload buildCastleScreenPayload(class_3218 world, @Nullable class_3222 player, String castleId) {
        int settlerCapacity;
        boolean foundedKingdom;
        String castleName;
        String religionId;
        String houseId;
        String kingdomId;
        CastleWorldState state = GotCastles.getWorldState(world);
        CastleInstance castle = state.get(castleId);
        HireableCampDefinition camp = GotCastles.getHireableCampDefinition(castle);
        CastlePresetMetadata metadata = presetMetadataByCastleId.get(castleId);
        OverlayCastleSite site = GotCastles.findOverlaySite(castleId);
        if (castle == null && metadata == null && site == null) {
            return null;
        }
        String string = camp != null ? "" : (kingdomId = GotCastles.canonicalKingdomId(castle != null && !castle.getOwnerKingdomId().isBlank() ? castle.getOwnerKingdomId() : (metadata != null ? metadata.kingdomId() : "")));
        String string2 = camp != null ? "" : (houseId = GotCastles.resolveOwnedHouseId(castle != null ? castle.getOwnerHouseId() : "", kingdomId, metadata != null ? metadata.houseId() : ""));
        String string3 = camp != null ? "" : (castle != null && !castle.getOwnerReligionId().isBlank() ? castle.getOwnerReligionId() : (religionId = metadata != null && !metadata.religionId().isBlank() ? metadata.religionId() : GotCastles.defaultReligionIdForKingdom(kingdomId)));
        String string4 = camp != null ? camp.claimName() : (castle != null ? GotCastles.displayNameForCastle(castle) : (castleName = site != null ? GotCastles.pendingDisplayName(site) : castleId));
        String lordName = camp != null ? "" : (castle != null ? GotCastles.resolveLordName(world, castle) : (site != null ? GotCastles.resolvePendingLordName(world, site, metadata, kingdomId) : ""));
        String kingdomName = GotCastles.resolveKingdomName(world, kingdomId);
        String houseName = GotCastles.resolveHouseName(world, houseId, kingdomId);
        String religionName = GotCastles.resolveReligionName(religionId);
        boolean playerSettlement = castle != null && "player_claim".equals(castle.getDefinitionId());
        boolean bannerEditorOwner = castle != null && player != null && player.method_5667().equals(castle.getOwnerPlayerUuid());
        boolean settlementOwner = playerSettlement && player != null && player.method_5667().equals(castle.getOwnerPlayerUuid());
        boolean bl = foundedKingdom = playerSettlement && castle != null && !castle.getOwnerKingdomId().isBlank() && PlayerKingdomWorldState.get(world).getByKingdomId(castle.getOwnerKingdomId()) != null;
        if (settlementOwner && GotCastles.reconcileAcceptedSettlers(world, castle, GotGameRules.trackedDay(world))) {
            state.method_80();
        }
        int settlerCount = playerSettlement ? (int)castle.getSettlers().stream().filter(SettlerProfile::accepted).count() : -1;
        int n = settlerCapacity = playerSettlement ? GotCastles.settlerCapacityFor(castle) : -1;
        String tierName = camp != null ? camp.tierName() : (castle != null ? ("player_claim".equals(castle.getDefinitionId()) ? castle.getSettlementTier().getDisplayName() : castle.getTier().getDisplayName()) : (metadata != null && !metadata.tier().isBlank() ? GotCastles.resolveEffectiveCastleTier(metadata, CastleTier.SETTLEMENT).getDisplayName() : (site != null && LORDSHIP_DEFINITION_ID.equals(site.definitionId()) ? CastleTier.LORDSHIP.getDisplayName() : CastleTier.SETTLEMENT.getDisplayName())));
        return new OpenCastleScreenPayload(castleId, castleName, lordName, kingdomId, kingdomName, houseId, houseName, religionId, religionName, tierName, GotCastles.canAccessCastleStorage(player, world, castle), bannerEditorOwner, settlementOwner, foundedKingdom, playerSettlement ? castle.getSettlementTier().getId() : "", playerSettlement ? castle.getSettlementLevel() : 0, settlerCount, settlerCapacity, playerSettlement ? GotCastles.nextLevelRequirementLabel(castle) : "", castle != null && settlementOwner ? GotCastles.buildSettlerEntries(castle) : List.of());
    }

    public static void handleCreateKingdomRequest(class_3222 player, String castleId, String requestedKingdomName, String parentKingdomId) {
        class_1937 class_19372;
        if (player == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        if (castleId == null || castleId.isBlank()) {
            GotCastles.handleCreateStandaloneKingdomRequest(player, requestedKingdomName, parentKingdomId);
            return;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle == null || !"player_claim".equals(castle.getDefinitionId()) || !player.method_5667().equals(castle.getOwnerPlayerUuid())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You can only found a kingdom from your own settlement.").method_27692(class_124.field_1061), true);
            return;
        }
        PlayerKingdomWorldState kingdomState = PlayerKingdomWorldState.get(world);
        if (!castle.getOwnerKingdomId().isBlank() && kingdomState.getByKingdomId(castle.getOwnerKingdomId()) != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This settlement already founded a kingdom.").method_27692(class_124.field_1054), true);
            GotCastles.openCastleScreen(player, castleId);
            return;
        }
        if (kingdomState.getByOwnerUuid(player.method_5845()) != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You already founded a kingdom.").method_27692(class_124.field_1061), true);
            GotCastles.openCastleScreen(player, castleId);
            return;
        }
        if (castle.getSettlementLevel() < 5) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Your settlement must be level 5 before it can become a kingdom.").method_27692(class_124.field_1061), true);
            return;
        }
        ReputationTitle title = ((PlayerReputationHolder)player).got$getReputationProfile().getTitle();
        if (title != ReputationTitle.KING && title != ReputationTitle.MAGISTER && title != ReputationTitle.PROPHET) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Only a King, Magister, or Prophet can found a kingdom.").method_27692(class_124.field_1061), true);
            return;
        }
        class_2960 parentId = class_2960.method_12829((String)parentKingdomId);
        if (parentId == null || GotKingdoms.get(parentId) == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Choose a valid parent culture.").method_27692(class_124.field_1061), true);
            return;
        }
        String kingdomName = GotCastles.sanitizeKingdomName(requestedKingdomName);
        if (kingdomName.isBlank()) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Kingdom names must contain letters.").method_27692(class_124.field_1061), true);
            return;
        }
        String kingdomPath = GotCastles.kingdomIdPathFromName(kingdomName);
        if (kingdomPath.isBlank()) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Kingdom names must contain letters or numbers.").method_27692(class_124.field_1061), true);
            return;
        }
        class_2960 kingdomIdentifier = class_2960.method_60655((String)"agob", (String)kingdomPath);
        if (GotKingdoms.hasKingdomId(kingdomIdentifier) || kingdomState.getByKingdomId(kingdomIdentifier.toString()) != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"A kingdom with that id already exists.").method_27692(class_124.field_1061), true);
            return;
        }
        if (!GotKingdoms.registerDynamicKingdom(kingdomIdentifier, kingdomName, parentId)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Could not register that kingdom.").method_27692(class_124.field_1061), true);
            return;
        }
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        String houseId = GotCastles.resolveOwnedHouseId(character.getSelectedOptionId(), character.getSelectedKingdomId(), castle.getOwnerHouseId());
        String kingdomId = kingdomIdentifier.toString();
        houseId = GotCastles.migratePlayerHouseToKingdom(world, player, kingdomId, houseId);
        kingdomState.put(new PlayerKingdomDefinition(player.method_5845(), kingdomId, kingdomName, parentId.toString(), castle.getId(), houseId));
        GotCastles.initializeDynamicKingdomRelations(world, kingdomId, parentId.toString());
        GotCastles.updatePlayerOwnedCastlesToKingdom(world, player.method_5667(), kingdomId, houseId);
        castle.setClaimRadiusChunks(4);
        GotCastles.getWorldState(world).method_80();
        GotCastles.getWorldState(world).put(castle);
        character.setSelectedKingdomId(kingdomId);
        character.setSelectedOptionId(houseId);
        GotCastles.carryPlayerFamilyToKingdom(world, player, kingdomId, houseId, castle.getId());
        ((PlayerCastleTravelHolder)player).got$unlockCastle(castle.getId());
        GotCastles.updateDynastyOwnedCastlesForPlayer(world, player);
        GotCharacters.sendCharacterSelectionState(player);
        PlayerHouseService.broadcast(player.method_5682());
        GotRelations.syncToPlayers(world);
        GotCastles.syncStateToPlayers(world);
        GotCastles.syncClaimOutlines(world);
        GotCastles.syncCastleMapToPlayers(world);
        player.method_7353((class_2561)class_2561.method_43470((String)(kingdomName + " has been founded.")).method_27692(class_124.field_1065), true);
        GotCastles.openCastleScreen(player, castleId);
    }

    private static void handleCreateStandaloneKingdomRequest(class_3222 player, String requestedKingdomName, String parentKingdomId) {
        class_1937 class_19372;
        if (player == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        if (!player.method_5687(2)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Only operators can create standalone kingdoms.").method_27692(class_124.field_1061), true);
            return;
        }
        PlayerKingdomWorldState kingdomState = PlayerKingdomWorldState.get(world);
        if (kingdomState.getByOwnerUuid(player.method_5845()) != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You already founded a kingdom.").method_27692(class_124.field_1061), true);
            return;
        }
        class_2960 parentId = class_2960.method_12829((String)parentKingdomId);
        if (parentId == null || GotKingdoms.get(parentId) == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Choose a valid parent culture.").method_27692(class_124.field_1061), true);
            return;
        }
        String kingdomName = GotCastles.sanitizeKingdomName(requestedKingdomName);
        if (kingdomName.isBlank()) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Kingdom names must contain letters.").method_27692(class_124.field_1061), true);
            return;
        }
        String kingdomPath = GotCastles.kingdomIdPathFromName(kingdomName);
        if (kingdomPath.isBlank()) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Kingdom names must contain letters or numbers.").method_27692(class_124.field_1061), true);
            return;
        }
        class_2960 kingdomIdentifier = class_2960.method_60655((String)"agob", (String)kingdomPath);
        if (GotKingdoms.hasKingdomId(kingdomIdentifier) || kingdomState.getByKingdomId(kingdomIdentifier.toString()) != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"A kingdom with that id already exists.").method_27692(class_124.field_1061), true);
            return;
        }
        if (!GotKingdoms.registerDynamicKingdom(kingdomIdentifier, kingdomName, parentId)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Could not register that kingdom.").method_27692(class_124.field_1061), true);
            return;
        }
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        String kingdomId = kingdomIdentifier.toString();
        String houseId = GotCastles.resolveOwnedHouseId(character.getSelectedOptionId(), character.getSelectedKingdomId(), GotCharacterOptions.getPrimaryHouseId(parentId));
        houseId = GotCastles.migratePlayerHouseToKingdom(world, player, kingdomId, houseId);
        kingdomState.put(new PlayerKingdomDefinition(player.method_5845(), kingdomId, kingdomName, parentId.toString(), "", houseId));
        GotCastles.initializeDynamicKingdomRelations(world, kingdomId, parentId.toString());
        character.setSelectedKingdomId(kingdomId);
        character.setSelectedOptionId(houseId);
        GotCastles.carryPlayerFamilyToKingdom(world, player, kingdomId, houseId, "");
        GotCharacters.sendCharacterSelectionState(player);
        PlayerHouseService.broadcast(player.method_5682());
        GotRelations.syncToPlayers(world);
        GotCastles.syncStateToPlayers(world);
        GotCastles.syncClaimOutlines(world);
        GotCastles.syncCastleMapToPlayers(world);
        player.method_7353((class_2561)class_2561.method_43470((String)(kingdomName + " has been founded.")).method_27692(class_124.field_1065), true);
    }

    private static void initializeDynamicKingdomRelations(class_3218 world, String kingdomId, String parentKingdomId) {
        KingdomRelationsWorldState relations = KingdomRelationsWorldState.get(world);
        relations.setRelation(kingdomId, kingdomId, 100);
        LinkedHashSet<String> knownKingdomIds = new LinkedHashSet<String>(relations.getKingdomIds());
        for (Kingdom kingdom : GotKingdoms.getAll()) {
            knownKingdomIds.add(kingdom.getId().toString());
        }
        for (String otherKingdomId : knownKingdomIds) {
            if (otherKingdomId == null || otherKingdomId.isBlank() || otherKingdomId.equals(kingdomId)) continue;
            relations.setRelation(kingdomId, otherKingdomId, relations.getRelation(parentKingdomId, otherKingdomId));
            relations.setRelation(otherKingdomId, kingdomId, relations.getRelation(otherKingdomId, parentKingdomId));
        }
    }

    private static String migratePlayerHouseToKingdom(class_3218 world, class_3222 player, String kingdomId, String fallbackHouseId) {
        PlayerHouseDefinition customHouse = PlayerHouseService.getForOwner(world, player.method_5667());
        if (customHouse == null) {
            return fallbackHouseId;
        }
        PlayerHouseWorldState.get(world).put(new PlayerHouseDefinition(customHouse.ownerUuid(), customHouse.houseId(), kingdomId, customHouse.lordHouseId(), customHouse.displayName(), customHouse.sigilColors(), customHouse.memberUuids()));
        return customHouse.houseId();
    }

    private static void carryPlayerFamilyToKingdom(class_3218 world, class_3222 player, String kingdomId, String houseId, String castleId) {
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (marriage.isMarried()) {
            class_1297 entity;
            marriage.setSpouseKingdomId(kingdomId);
            marriage.setSpouseHouseId(houseId);
            if (marriage.getSpouseNpcUuid() != null && (entity = world.method_14190(marriage.getSpouseNpcUuid())) instanceof AbstractDynastyNpcEntity) {
                AbstractDynastyNpcEntity dynastyNpc = (AbstractDynastyNpcEntity)entity;
                dynastyNpc.setDynastyKingdomId(class_2960.method_60654((String)kingdomId));
                dynastyNpc.setDynastyHouseId(houseId);
            }
        }
        DynastyNpcWorldState dynastyState = DynastyNpcWorldState.get(world);
        for (DynastyNpcWorldState.FamilyMemberProfile profile : dynastyState.getByOwner(player.method_5845())) {
            DynastyNpcWorldState.FamilyMemberProfile updated = new DynastyNpcWorldState.FamilyMemberProfile(profile.id(), profile.entityUuid(), profile.ownerPlayerUuid(), player.method_5477().getString(), profile.otherParentName(), profile.boundMarriedPlayerUuid(), profile.boundMarriedPlayerName(), kingdomId, houseId, profile.displayName(), profile.bornDay(), profile.castleId().isBlank() ? castleId : profile.castleId(), profile.stage(), profile.ownerSmallArms(), profile.summonCooldownEndsAtMs(), profile.attributes());
            dynastyState.put(updated);
            class_1297 entity = world.method_14190(profile.entityUuid());
            if (!(entity instanceof AbstractDynastyNpcEntity)) continue;
            AbstractDynastyNpcEntity dynastyNpc = (AbstractDynastyNpcEntity)entity;
            dynastyNpc.setDynastyKingdomId(class_2960.method_60654((String)kingdomId));
            dynastyNpc.setDynastyHouseId(houseId);
        }
    }

    private static String sanitizeKingdomName(String requestedName) {
        if (requestedName == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        boolean lastSpace = true;
        for (int index = 0; index < requestedName.length() && builder.length() < 24; ++index) {
            char ch = requestedName.charAt(index);
            if (Character.isLetter(ch)) {
                builder.append(ch);
                lastSpace = false;
                continue;
            }
            if (!Character.isWhitespace(ch) || lastSpace) continue;
            builder.append(' ');
            lastSpace = true;
        }
        return builder.toString().trim();
    }

    private static String kingdomIdPathFromName(String kingdomName) {
        if (kingdomName == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        boolean lastUnderscore = true;
        for (int index = 0; index < kingdomName.length(); ++index) {
            char ch = Character.toLowerCase(kingdomName.charAt(index));
            if (ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9') {
                builder.append(ch);
                lastUnderscore = false;
                continue;
            }
            if (!Character.isWhitespace(ch) && ch != '_' && ch != '-' || lastUnderscore || builder.length() <= 0) continue;
            builder.append('_');
            lastUnderscore = true;
        }
        while (builder.length() > 0 && builder.charAt(builder.length() - 1) == '_') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    private static List<OpenCastleScreenPayload.SettlerEntry> buildSettlerEntries(CastleInstance castle) {
        ArrayList<OpenCastleScreenPayload.SettlerEntry> entries = new ArrayList<OpenCastleScreenPayload.SettlerEntry>();
        for (SettlerProfile profile : castle.getSettlers()) {
            CharacterSelectionOption option;
            if (!profile.accepted() || (option = GotCharacterOptions.get(profile.optionId())) == null) continue;
            SettlerProfession profession = profile.profession();
            SettlerSkillTier tier = profile.skillTier();
            entries.add(new OpenCastleScreenPayload.SettlerEntry(profile.id(), profile.displayName(), profile.optionId(), option.texture().toString(), profession.getId(), profession.getDisplayName(), tier.getId(), tier.getDisplayName(), profession.indicatorFor(tier)));
        }
        return List.copyOf(entries);
    }

    private static boolean isExistingHouseLordName(class_3218 world, CastleInstance castle, String lordName) {
        if (world == null || castle == null || lordName == null || lordName.isBlank() || castle.getOwnerPlayerUuid() != null || GotCastles.isHireableCampClaim(castle)) {
            return false;
        }
        String kingdomId = GotCastles.canonicalKingdomId(castle.getOwnerKingdomId());
        String houseId = GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), kingdomId, "");
        if (kingdomId.isBlank() || houseId.isBlank()) {
            return false;
        }
        return HouseLordWorldState.get(world).getLords(kingdomId, houseId).stream().anyMatch(lord -> lordName.equals(lord.displayName()));
    }

    private static String resolveExistingHouseLordNameForCastleId(class_3218 world, String castleId, String kingdomId, String houseId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return "";
        }
        String canonicalKingdomId = GotCastles.canonicalKingdomId(kingdomId);
        String resolvedHouseId = GotCastles.resolveOwnedHouseId(houseId, canonicalKingdomId, "");
        if (canonicalKingdomId.isBlank() || resolvedHouseId.isBlank()) {
            return "";
        }
        List<HouseLordWorldState.HouseLordProfile> lords = HouseLordWorldState.get(world).getLords(canonicalKingdomId, resolvedHouseId).stream().sorted(Comparator.comparingInt(HouseLordWorldState.HouseLordProfile::index)).toList();
        if (lords.isEmpty()) {
            return "";
        }
        List<HouseCastleNameTarget> targets = GotCastles.houseCastleNameTargets(world, canonicalKingdomId, resolvedHouseId);
        for (int index = 0; index < targets.size(); ++index) {
            if (!castleId.equals(targets.get(index).castleId())) continue;
            return lords.get(index % lords.size()).displayName();
        }
        return lords.getFirst().displayName();
    }

    private static List<HouseCastleNameTarget> houseCastleNameTargets(class_3218 world, String kingdomId, String houseId) {
        String castleHouseId;
        String castleKingdomId;
        CastlePresetMetadata metadata;
        ArrayList<HouseCastleNameTarget> targets = new ArrayList<HouseCastleNameTarget>();
        CastleWorldState state = GotCastles.getWorldState(world);
        for (CastleInstance castle : state.getAll()) {
            if (castle == null || castle.getOwnerPlayerUuid() != null || "player_claim".equals(castle.getDefinitionId())) continue;
            metadata = presetMetadataByCastleId.get(castle.getId());
            castleKingdomId = GotCastles.canonicalKingdomId(castle.getOwnerKingdomId());
            castleHouseId = GotCastles.resolveOwnedHouseId(castle.getOwnerHouseId(), castleKingdomId, metadata != null ? metadata.houseId() : "");
            if (!kingdomId.equals(castleKingdomId) || !houseId.equals(castleHouseId)) continue;
            targets.add(new HouseCastleNameTarget(castle.getId(), GotCastles.resolveEffectiveCastleTier(metadata, castle.getTier())));
        }
        for (OverlayCastleSite site : overlaySites) {
            if (state.contains(site.castleId()) || state.isRejected(site.castleId())) continue;
            metadata = presetMetadataByCastleId.get(site.castleId());
            castleKingdomId = GotCastles.resolvePendingCastleKingdomId(world, site, metadata);
            castleHouseId = GotCastles.resolveOwnedHouseId("", castleKingdomId, metadata != null ? metadata.houseId() : "");
            if (!kingdomId.equals(castleKingdomId) || !houseId.equals(castleHouseId)) continue;
            CastleTier tier = GotCastles.resolveEffectiveCastleTier(metadata, LORDSHIP_DEFINITION_ID.equals(site.definitionId()) ? CastleTier.LORDSHIP : CastleTier.SETTLEMENT);
            targets.add(new HouseCastleNameTarget(site.castleId(), tier));
        }
        targets.sort(Comparator.comparingInt(target -> GotCastles.houseSeatRank(target.tier())).thenComparing(HouseCastleNameTarget::castleId));
        return List.copyOf(targets);
    }

    private static String resolvePendingLordName(class_3218 world, OverlayCastleSite site, @Nullable CastlePresetMetadata metadata, String kingdomId) {
        String effectiveKingdomId = GotCastles.canonicalKingdomId(kingdomId != null && !kingdomId.isBlank() ? kingdomId : (metadata != null ? metadata.kingdomId() : ""));
        if (effectiveKingdomId.isBlank()) {
            Kingdom kingdom = GotCastles.resolveKingdomForBiome(world, site.anchor());
            effectiveKingdomId = kingdom == null ? "" : kingdom.getId().toString();
        }
        return GotCastles.generateFallbackLordName(site.castleId(), effectiveKingdomId, metadata != null ? metadata.houseId() : "", site.anchor());
    }

    public static String resolveLordName(class_3218 world, CastleInstance castle) {
        if (GotCastles.isHireableCampClaim(castle)) {
            return "";
        }
        if (castle.getOwnerPlayerUuid() != null) {
            String playerName;
            class_3222 owner = world.method_8503().method_3760().method_14602(castle.getOwnerPlayerUuid());
            String string = playerName = owner != null ? owner.method_7334().getName() : castle.getLordName();
            if (playerName.isBlank()) {
                playerName = "Unknown";
            }
            if (!playerName.equals(castle.getLordName())) {
                castle.setLordName(playerName);
                GotCastles.getWorldState(world).method_80();
            }
            return playerName;
        }
        if (!castle.getLordName().isBlank()) {
            return castle.getLordName();
        }
        String generatedName = GotCastles.generateFallbackLordName(castle);
        if (!generatedName.isBlank()) {
            castle.setLordName(generatedName);
            GotCastles.getWorldState(world).method_80();
            return generatedName;
        }
        return "";
    }

    public static void registerHireableCampClaim(class_3218 world, HireableCampDefinition definition, class_1923 chunkPos) {
        String claimId;
        if (world == null || definition == null || chunkPos == null) {
            return;
        }
        CastleWorldState state = GotCastles.getWorldState(world);
        if (state.contains(claimId = GotCastles.hireableCampClaimId(definition.id(), chunkPos))) {
            return;
        }
        class_2338 bannerPos = GotCastles.findHireableCampAnchor(world, chunkPos);
        if (bannerPos == null) {
            GotMod.LOGGER.warn("Could not register hireable camp {} in chunk {},{} because no cauldron/campfire anchor was found", new Object[]{definition.id(), chunkPos.field_9181, chunkPos.field_9180});
            return;
        }
        if (state.findClaimAt(bannerPos) != null) {
            return;
        }
        CastleTitle title = new CastleTitle(claimId + "_title", claimId, definition.claimName(), CastleTier.SETTLEMENT);
        CastleInstance claim = new CastleInstance(claimId, definition.id(), CastleTier.SETTLEMENT, class_2960.method_60655((String)"agob", (String)definition.structurePath()), bannerPos, bannerPos, 1, Math.max(0, definition.claimRadiusChunks()), 24, false, title, CastleTier.SETTLEMENT.defaultLootTableId());
        state.put(claim);
    }

    private static String hireableCampClaimId(String definitionId, class_1923 chunkPos) {
        return definitionId + "_" + chunkPos.field_9181 + "_" + chunkPos.field_9180;
    }

    @Nullable
    private static class_2338 findHireableCampAnchor(class_3218 world, class_1923 chunkPos) {
        class_2338 expectedCenter = new class_2338(chunkPos.method_8326() + 8, world.method_8615(), chunkPos.method_8328() + 8);
        class_2338 bestMatch = null;
        double bestDistance = Double.MAX_VALUE;
        for (int chunkX = chunkPos.field_9181 - 1; chunkX <= chunkPos.field_9181 + 1; ++chunkX) {
            for (int chunkZ = chunkPos.field_9180 - 1; chunkZ <= chunkPos.field_9180 + 1; ++chunkZ) {
                class_1923 scanChunk = new class_1923(chunkX, chunkZ);
                for (int y = world.method_31600(); y >= world.method_31607(); --y) {
                    for (int x = scanChunk.method_8326(); x <= scanChunk.method_8327(); ++x) {
                        for (int z = scanChunk.method_8328(); z <= scanChunk.method_8329(); ++z) {
                            double distance;
                            class_2338 pos = new class_2338(x, y, z);
                            class_2680 state = world.method_8320(pos);
                            if (!GotCastles.isCauldronBlock(state) || !GotCastles.isCampfireBlock(world.method_8320(pos.method_10074())) || !((distance = pos.method_10262((class_2382)expectedCenter)) < bestDistance)) continue;
                            bestDistance = distance;
                            bestMatch = pos.method_10062();
                        }
                    }
                }
            }
        }
        return bestMatch;
    }

    private static boolean isHireableCampClaim(@Nullable CastleInstance claim) {
        return GotCastles.getHireableCampDefinition(claim) != null;
    }

    @Nullable
    private static HireableCampDefinition getHireableCampDefinition(@Nullable CastleInstance claim) {
        return claim == null ? null : DynamicTroopRegistry.getHireableCamp(claim.getDefinitionId());
    }

    private static boolean isHireableCampAnchorBlock(CastleInstance claim, class_2338 pos, class_2680 state) {
        if (!GotCastles.isWithinClaimCenterChunk(claim, pos)) {
            return false;
        }
        return GotCastles.isCauldronBlock(state) || GotCastles.isCampfireBlock(state);
    }

    private static boolean isWithinClaimCenterChunk(CastleInstance claim, class_2338 pos) {
        return claim != null && claim.getBannerPos().method_10263() >> 4 == pos.method_10263() >> 4 && claim.getBannerPos().method_10260() >> 4 == pos.method_10260() >> 4;
    }

    private static boolean isCauldronBlock(class_2680 state) {
        return state.method_26204() instanceof class_2275;
    }

    private static boolean isCampfireBlock(class_2680 state) {
        return state.method_27852(class_2246.field_17350) || state.method_27852(class_2246.field_23860);
    }

    private static String generateFallbackLordName(CastleInstance castle) {
        return GotCastles.generateFallbackLordName(castle.getId(), castle.getOwnerKingdomId(), castle.getOwnerHouseId(), castle.getBannerPos());
    }

    private static String generateFallbackLordName(String castleId, String ownerKingdomId, String ownerHouseId, class_2338 nameAnchor) {
        if (nameAnchor == null) {
            return "";
        }
        if ("agob:the_nights_watch".equals(ownerKingdomId)) {
            return GotCastleOwnerNames.createFullNameFromAnyPool(nameAnchor.method_10063() ^ (long)castleId.hashCode());
        }
        if (ownerKingdomId == null || ownerKingdomId.isBlank()) {
            return "";
        }
        class_2960 parsedKingdomId = class_2960.method_12829((String)ownerKingdomId);
        if (parsedKingdomId == null) {
            return "";
        }
        Kingdom kingdom = GotKingdoms.get(parsedKingdomId);
        if (kingdom == null) {
            return "";
        }
        if ("agob:wildling".equals(ownerKingdomId)) {
            if ("hardhome".equals(castleId)) {
                return "Mance Raider";
            }
            return GotCastleOwnerNames.createCommonerName(kingdom, castleId + "|" + nameAnchor.method_10063(), Set.of());
        }
        String houseSurname = GotCastles.normalizeHouseSurname(GotCastles.resolveHouseName(ownerHouseId, ownerKingdomId));
        return GotCastleOwnerNames.createFullName(kingdom, houseSurname, nameAnchor.method_10063() ^ (long)castleId.hashCode());
    }

    private static String resolveKingdomName(String kingdomId) {
        class_2960 parsed = GotCastles.normalizeKingdomId(class_2960.method_12829((String)kingdomId));
        if (parsed == null) {
            return "";
        }
        Kingdom kingdom = GotKingdoms.get(parsed);
        return kingdom == null ? "" : kingdom.getName();
    }

    private static String resolveKingdomName(class_3218 world, String kingdomId) {
        PlayerKingdomDefinition definition;
        if (world != null && (definition = PlayerKingdomWorldState.get(world).getByKingdomId(kingdomId)) != null && !definition.displayName().isBlank()) {
            return definition.displayName();
        }
        String name = GotCastles.resolveKingdomName(kingdomId);
        if (!name.isBlank() || world == null) {
            return name;
        }
        return "";
    }

    private static CharacterSelectionOption resolveHouseOption(String houseId, String kingdomId) {
        CharacterSelectionOption option = GotCharacterOptions.get(houseId);
        if (option != null) {
            return option;
        }
        class_2960 kingdomIdentifier = GotCastles.normalizeKingdomId(class_2960.method_12829((String)kingdomId));
        return kingdomIdentifier == null ? null : GotCharacterOptions.getByKingdomId(kingdomIdentifier);
    }

    static String resolveOwnedHouseId(String explicitHouseId, String kingdomId, String fallbackHouseId) {
        if (PlayerHouseService.isCustomHouseId(explicitHouseId)) {
            return explicitHouseId;
        }
        CharacterSelectionOption explicit = GotCharacterOptions.get(explicitHouseId);
        if (explicit != null) {
            return explicit.id();
        }
        if (PlayerHouseService.isCustomHouseId(fallbackHouseId)) {
            return fallbackHouseId;
        }
        CharacterSelectionOption fallback = GotCharacterOptions.get(fallbackHouseId);
        if (fallback != null) {
            return fallback.id();
        }
        class_2960 kingdomIdentifier = GotCastles.normalizeKingdomId(class_2960.method_12829((String)kingdomId));
        return kingdomIdentifier == null ? "" : GotCharacterOptions.getPrimaryHouseId(kingdomIdentifier);
    }

    private static String resolveHouseName(String houseId, String kingdomId) {
        CharacterSelectionOption option = GotCastles.resolveHouseOption(houseId, kingdomId);
        return option == null ? "" : option.houseDisplayName();
    }

    private static String resolveHouseName(class_3218 world, String houseId, String kingdomId) {
        PlayerHouseDefinition customHouse;
        if (PlayerHouseService.isCustomHouseId(houseId) && (customHouse = PlayerHouseService.get(world, houseId)) != null && !customHouse.displayName().isBlank()) {
            return customHouse.displayName();
        }
        return GotCastles.resolveHouseName(houseId, kingdomId);
    }

    private static String normalizeHouseSurname(String houseName) {
        if (houseName == null || houseName.isBlank()) {
            return "";
        }
        int parentheticalIndex = houseName.indexOf(40);
        String normalized = (parentheticalIndex >= 0 ? houseName.substring(0, parentheticalIndex) : houseName).trim();
        return normalized;
    }

    static String canonicalKingdomId(String kingdomId) {
        class_2960 normalized = GotCastles.normalizeKingdomId(class_2960.method_12829((String)kingdomId));
        return normalized == null ? (kingdomId == null ? "" : kingdomId) : normalized.toString();
    }

    private static String resolveReligionName(String religionId) {
        if (religionId == null || religionId.isBlank()) {
            return "";
        }
        ReligionSelectionOption option = GotReligionOptions.get(religionId);
        return option == null ? "" : option.displayName();
    }

    private static OverlayCastleSite findOverlaySite(String castleId) {
        for (OverlayCastleSite site : overlaySites) {
            if (!site.castleId().equals(castleId)) continue;
            return site;
        }
        return null;
    }

    static {
        CASTLELESS_COLOR_ORIGIN_CACHE = new HashMap<Integer, Optional<class_2338>>();
        PENDING_CLAIM_CHECKS = new ConcurrentLinkedQueue();
        PENDING_NPC_RESPAWNS = new PriorityQueue<PendingNpcRespawn>(Comparator.comparingLong(PendingNpcRespawn::respawnAtTick).thenComparing(PendingNpcRespawn::castleId).thenComparing(PendingNpcRespawn::profileId));
        FORCED_CASTLE_GENERATION_CHUNKS = new HashMap<class_5321<class_1937>, Set<Long>>();
        PRIORITIZED_CASTLE_GENERATION_IDS = new HashMap<class_5321<class_1937>, LinkedHashSet<String>>();
        CACHED_WEIRWOOD_SITE_PREDICTIONS = new HashMap<class_5321<class_1937>, Map<String, class_2338>>();
        ACTIVE_CASTLE_INVASIONS = new LinkedHashMap<UUID, ActiveCastleInvasion>();
        ACTIVE_TITLE_CHALLENGES = new LinkedHashMap<UUID, ActiveTitleChallenge>();
        ACTIVE_TOURNEY_CHALLENGES = new LinkedHashMap<UUID, ActiveTourneyChallenge>();
        TITLE_CHALLENGE_COOLDOWNS = new LinkedHashMap<UUID, Long>();
        ARENA_BOUNDARY_CACHES = new HashMap<class_5321<class_1937>, ArenaBoundaryCache>();
        ARENA_ENTITY_SNAPSHOTS = new LinkedHashMap<UUID, ArenaEntitySnapshot>();
        ARENA_TELEPORT_GRACE = new HashSet<UUID>();
        ARENA_LOGOUT_RELEASES = new HashSet<UUID>();
        ACTIVE_KINGDOM_AI_INVASIONS = new LinkedHashMap<String, ActiveKingdomAiInvasion>();
        ACTIVE_VOYAGES = new LinkedHashMap<UUID, ActiveVoyageSession>();
        ACTIVE_LAND_TRAVELS = new LinkedHashMap<UUID, ActiveLandTravelSession>();
        HYDRATED_TRAVEL_WORLDS = Collections.newSetFromMap(new IdentityHashMap());
        LEGACY_GATE_DISCOVERY = new IdentityHashMap<class_3218, LegacyGateDiscoveryState>();
        FISHERMAN_SPAWN_LOG_COUNTS = new LinkedHashMap<String, FishermanSpawnLogCounter>();
        VOYAGE_CONTROL_LOG_COUNTS = new LinkedHashMap<String, VoyageControlLogCounter>();
        CLAIM_OUTLINE_PAYLOAD_SIGNATURES = new HashMap<class_5321<class_1937>, Integer>();
        fishermanSpawnLogWindowStartMs = System.currentTimeMillis();
        voyageControlLogWindowStartMs = System.currentTimeMillis();
        RECONCILED_PLAYER_CLAIM_SERVERS = Collections.newSetFromMap(new IdentityHashMap());
        LOGGED_HARBOUR_CASTLE_SERVERS = Collections.newSetFromMap(new IdentityHashMap());
        CASTLE_KEEP_TEMPLATE_CACHE = new ConcurrentHashMap<String, Optional<CastleKeepTemplate>>();
        LOGGED_DEFAULT_CASTLE_KEEP_CASTLES = ConcurrentHashMap.newKeySet();
        HOUSE_LORD_RECONCILE_DEPTH = ThreadLocal.withInitial(() -> 0);
        MARKET_STRUCTURE_PATHS = List.of("new/market_stalls/market_stall_1", "new/market_stalls/market_stall_2", "new/market_stalls/market_stall_3", "new/market_stalls/market_stall_4", "new/market_stalls/market_stall_5");
        NATURE_STRUCTURE_PATHS = List.of("new/nature_pack/nature_pack_1", "new/nature_pack/nature_pack_2", "new/nature_pack/nature_pack_3", "new/nature_pack/nature_pack_4", "new/nature_pack/nature_pack_5", "new/nature_pack/nature_pack_6", "new/nature_pack/nature_pack_7", "new/nature_pack/nature_pack_8", "new/nature_pack/nature_pack_9", "new/nature_pack/nature_pack_10", "new/nature_pack/nature_pack_11", "new/nature_pack/nature_pack_12", "new/nature_pack/nature_pack_13", "new/nature_pack/nature_pack_14", "new/nature_pack/nature_pack_15", "new/nature_pack/nature_pack_16", "new/nature_pack/nature_pack_17", "new/nature_pack/nature_pack_18", "new/nature_pack/nature_pack_19", "new/nature_pack/nature_pack_20", "new/nature_pack/nature_pack_21", "new/nature_pack/nature_pack_22", "new/nature_pack/nature_pack_23", "new/nature_pack/nature_pack_24", "new/nature_pack/nature_pack_25", "new/nature_pack/nature_pack_26", "new/nature_pack/nature_pack_27", "new/nature_pack/nature_pack_28", "new/nature_pack/nature_pack_29", "new/nature_pack/nature_pack_30", "new/nature_pack/nature_pack_31", "new/nature_pack/nature_pack_32", "new/nature_pack/nature_pack_33", "new/nature_pack/nature_pack_34", "new/nature_pack/nature_pack_35", "new/nature_pack/nature_pack_36", "new/nature_pack/nature_pack_37", "new/nature_pack/nature_pack_38", "new/nature_pack/nature_pack_39", "new/nature_pack/nature_pack_40", "new/nature_pack/nature_pack_41", "new/nature_pack/nature_pack_42", "new/nature_pack/nature_pack_43", "new/nature_pack/nature_pack_44");
        WEIRWOOD_FACE_LOOT_TABLE_ID = class_2960.method_60655((String)"agob", (String)"weirwood/bloody_face");
        STONE_CASTLE_CLAIM_STRUCTURE_ID = class_2960.method_60655((String)"agob", (String)"castle/stone/castle_claim");
        STONE_CASTLE_WALL_STRUCTURE_ID = class_2960.method_60655((String)"agob", (String)"castle/stone/stone_wall_wall");
        STONE_CASTLE_CORNER_STRUCTURE_ID = class_2960.method_60655((String)"agob", (String)"castle/stone/stone_wall_corner");
        STONE_CASTLE_GATE_STRUCTURE_ID = class_2960.method_60655((String)"agob", (String)"castle/stone/stone_wall_gate");
        DYNAMIC_STONE_SETTLEMENT_TEMPLATE_ID = class_2960.method_60655((String)"agob", (String)"castle/stone/dynamic_settlement");
        DYNAMIC_STONE_LORDSHIP_TEMPLATE_ID = class_2960.method_60655((String)"agob", (String)"castle/stone/dynamic_lordship");
        DYNAMIC_STONE_CAPITAL_TEMPLATE_ID = class_2960.method_60655((String)"agob", (String)"castle/stone/dynamic_capital");
    }

    public record StrategicCastleSnapshot(String castleId, String displayName, class_2338 bannerPos, CastleTier tier, boolean harbourCastle, String ownerKingdomId, String ownerHouseId, String ownerReligionId, String lordName, @Nullable UUID ownerPlayerUuid, boolean playerClaim, boolean generated, long raidEndsAtTick) {
    }

    public record TitleChallengeAvailability(boolean available, int cooldownSeconds) {
    }

    public record TourneyAvailability(boolean available, boolean attended) {
    }

    public static enum TourneyDifficulty {
        EASY(1, 20.0, 16),
        MEDIUM(2, 30.0, 32),
        HARD(4, 35.0, 32);

        private final int opponentCount;
        private final double health;
        private final int goldDragons;

        private TourneyDifficulty(int opponentCount, double health, int goldDragons) {
            this.opponentCount = opponentCount;
            this.health = health;
            this.goldDragons = goldDragons;
        }

        private int opponentCount() {
            return this.opponentCount;
        }

        private double health() {
            return this.health;
        }

        private int goldDragons() {
            return this.goldDragons;
        }
    }

    private record StrategicCastleSnapshotSet(List<StrategicCastleSnapshot> all, Map<String, StrategicCastleSnapshot> byCastleId, Map<String, List<StrategicCastleSnapshot>> byKingdomId) {
        private List<StrategicCastleSnapshot> castlesForKingdom(String kingdomId) {
            String canonical = GotCastles.canonicalKingdomId(kingdomId);
            return canonical.isBlank() ? List.of() : this.byKingdomId.getOrDefault(canonical, List.of());
        }

        @Nullable
        private StrategicCastleSnapshot resolve(String castleId) {
            return castleId == null ? null : this.byCastleId.get(GotCastles.realCastleIdForStrategicId(castleId));
        }
    }

    private static final class LegacyGateDiscoveryState {
        private final ArrayDeque<LegacyGateClaimCursor> claimCursors = new ArrayDeque();
        private long nextSweepTick = Long.MIN_VALUE;

        private LegacyGateDiscoveryState() {
        }

        private boolean shouldStartSweep(long worldTime) {
            return this.claimCursors.isEmpty() && worldTime >= this.nextSweepTick;
        }

        private void beginSweep(Collection<CastleInstance> castles, long worldTime) {
            this.claimCursors.clear();
            for (CastleInstance castle : castles) {
                if (castle == null) continue;
                this.claimCursors.addLast(new LegacyGateClaimCursor(castle));
            }
            if (this.claimCursors.isEmpty()) {
                this.nextSweepTick = worldTime + 1200L;
            }
        }

        private void submitLoadedClaimChunks(class_3218 world, long worldTime, int maxChunks, int maxPendingChunks) {
            for (int submitted = 0; submitted < maxChunks && !this.claimCursors.isEmpty(); ++submitted) {
                if (GateLocator.pendingLegacyDiscoveryChunks(world) >= maxPendingChunks) {
                    return;
                }
                LegacyGateClaimCursor cursor = this.claimCursors.removeFirst();
                class_1923 chunk = cursor.nextChunk();
                if (cursor.hasNext()) {
                    this.claimCursors.addLast(cursor);
                }
                if (!world.method_8393(chunk.field_9181, chunk.field_9180)) continue;
                GateLocator.submitLegacyDiscovery(world, chunk);
            }
            if (this.claimCursors.isEmpty()) {
                this.nextSweepTick = worldTime + 1200L;
            }
        }
    }

    private record PendingGenerationBatchResult(int operations, boolean planned) {
    }

    public record HouseDisbandResult(int reassignedCastles, int unclaimedCastles, boolean noReplacementHouse) {
    }

    private record HouseCastleNameTarget(String castleId, CastleTier tier) {
    }

    public record VillageAnchorTarget(String castleId, String castleName, String kingdomId, int worldX, int worldZ, boolean generated) {
    }

    private static final class LegacyGateClaimCursor {
        private final int minChunkX;
        private final int maxChunkX;
        private final int maxChunkZ;
        private int nextChunkX;
        private int nextChunkZ;

        private LegacyGateClaimCursor(CastleInstance castle) {
            class_2338 bannerPos = castle.getBannerPos();
            int radiusBlocks = Math.max(24, castle.getClaimRadiusChunks() * 16);
            this.minChunkX = Math.floorDiv(bannerPos.method_10263() - radiusBlocks, 16);
            this.maxChunkX = Math.floorDiv(bannerPos.method_10263() + radiusBlocks, 16);
            this.nextChunkX = this.minChunkX;
            this.nextChunkZ = Math.floorDiv(bannerPos.method_10260() - radiusBlocks, 16);
            this.maxChunkZ = Math.floorDiv(bannerPos.method_10260() + radiusBlocks, 16);
        }

        private class_1923 nextChunk() {
            class_1923 next = new class_1923(this.nextChunkX, this.nextChunkZ);
            ++this.nextChunkX;
            if (this.nextChunkX > this.maxChunkX) {
                this.nextChunkX = this.minChunkX;
                ++this.nextChunkZ;
            }
            return next;
        }

        private boolean hasNext() {
            return this.nextChunkZ <= this.maxChunkZ;
        }
    }
}

