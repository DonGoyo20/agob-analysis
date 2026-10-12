/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_1259$class_1260
 *  net.minecraft.class_1259$class_1261
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1304
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1922
 *  net.minecraft.class_1923
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2680
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_2960
 *  net.minecraft.class_3213
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3485
 *  net.minecraft.class_3492
 *  net.minecraft.class_3499
 *  net.minecraft.class_3532
 *  net.minecraft.class_3730
 *  net.minecraft.class_5321
 *  net.minecraft.class_5425
 *  net.minecraft.class_5819
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_1259;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1922;
import net.minecraft.class_1923;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2902;
import net.minecraft.class_2960;
import net.minecraft.class_3213;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3485;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_3532;
import net.minecraft.class_3730;
import net.minecraft.class_5321;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import trep.got.GotMod;
import trep.got.advancement.ModAdvancementService;
import trep.got.castle.CastleAreaOwnershipResolver;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleNpcProfile;
import trep.got.castle.CastleTier;
import trep.got.castle.CastleWorldState;
import trep.got.castle.PlayerCastleTravelHolder;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.NpcAppearanceProfile;
import trep.got.character.NpcAttributeProfile;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.day.DailyTaskContext;
import trep.got.day.DailyTaskRegistry;
import trep.got.entity.AbstractKingdomBlacksmithNpcEntity;
import trep.got.entity.AbstractKingdomCartRiderNpcEntity;
import trep.got.entity.AbstractKingdomGuardNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.AbstractKingdomVillageElderNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.house.PlayerHouseDefinition;
import trep.got.house.PlayerHouseService;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationExperienceService;
import trep.got.kingdom.ReputationTitle;
import trep.got.network.OpenVillageScreenPayload;
import trep.got.network.VillageMapPayload;
import trep.got.network.VillageRaidStatePayload;
import trep.got.registry.GotCastleOwnerNames;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotEntities;
import trep.got.registry.GotGameRules;
import trep.got.registry.GotItems;
import trep.got.registry.GotKingdomEventLogs;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotMainQuests;
import trep.got.registry.GotReligionOptions;
import trep.got.registry.GotVillageResources;
import trep.got.relations.KingdomWarService;
import trep.got.religion.ReligionSelectionOption;
import trep.got.troop.GotTroops;
import trep.got.village.VillageAiRaidWorldState;
import trep.got.village.VillageInstance;
import trep.got.village.VillageNameRegistry;
import trep.got.village.VillageWorldState;
import trep.got.village.economy.VillageStockService;
import trep.got.village.resource.VillageResource;
import trep.got.voyage.VoyageMapResources;
import trep.got.world.dimension.GotDimensionKeys;

public final class GotVillages {
    private static final int MIN_DISTANCE_BLOCKS = 1000;
    private static final int MAX_DISTANCE_BLOCKS = 1500;
    private static final int CLAIM_RADIUS_CHUNKS = 1;
    private static final int MAX_ATTEMPTS_PER_CASTLE = 96;
    private static final int MAX_VILLAGES_PER_TICK = 1;
    private static final long VILLAGE_GENERATION_SCAN_INTERVAL_TICKS = 200L;
    private static final long PRIORITY_VILLAGE_GENERATION_SCAN_INTERVAL_TICKS = 10L;
    private static final int VILLAGE_GENERATION_ACTIVE_RADIUS_BLOCKS = 384;
    private static final long VILLAGE_MAINTENANCE_INTERVAL_TICKS = 200L;
    private static final long VILLAGE_MAINTENANCE_STEP_INTERVAL_TICKS = 200L;
    private static final int VILLAGE_STOCK_SYNC_BUDGET = 8;
    private static final int VILLAGE_POPULATION_RECONCILE_BUDGET = 3;
    private static final long VILLAGE_DISCOVERY_INTERVAL_TICKS = 200L;
    private static final long VILLAGE_ACTIVE_RAID_TICK_INTERVAL_TICKS = 20L;
    private static final long VILLAGE_AI_RAID_TICK_INTERVAL_TICKS = 200L;
    private static final int VILLAGER_COUNT = 4;
    private static final int TERRAIN_TOLERANCE = 4;
    private static final int VILLAGE_PLATFORM_MARGIN = 4;
    private static final int VILLAGE_PLATFORM_BLEND_RADIUS = 8;
    private static final int VILLAGE_PLATFORM_FILL_DEPTH = 8;
    private static final int VILLAGE_PLATFORM_CLEAR_HEIGHT = 8;
    private static final String CLAIM_STRUCTURE = "farm/claim";
    private static final String FARMSTEAD_STRUCTURE = "farm/farmstead";
    private static final String BARN_STRUCTURE = "farm/barn";
    private static final String SMITHY_STRUCTURE = "farm/smithy";
    private static final int VILLAGE_GENERATION_BLOCK_FLAGS = 50;
    private static final int ANNOUNCEMENT_GOLD = -727358;
    private static final int ANNOUNCEMENT_GREEN = -10497171;
    private static final int ANNOUNCEMENT_RED = -2074022;
    private static final long VILLAGE_RAID_DURATION_TICKS = 6000L;
    private static final long VILLAGE_RAID_COOLDOWN_TICKS = 6000L;
    private static final long VILLAGE_AI_RAID_COOLDOWN_TICKS = 3000L;
    private static final long VILLAGE_AI_RAID_DECISION_WINDOW_TICKS = 36000L;
    private static final long VILLAGE_AI_RAID_WARMUP_TICKS = 1200L;
    private static final int VILLAGE_AI_RAID_TRIGGER_DENOMINATOR = 5;
    private static final int VILLAGE_RAID_GUARD_COUNT = 4;
    private static final Map<UUID, ActiveVillageRaid> ACTIVE_VILLAGE_RAIDS = new LinkedHashMap<UUID, ActiveVillageRaid>();
    private static final Map<class_5321<class_1937>, LinkedHashSet<String>> PRIORITIZED_VILLAGE_GENERATION_IDS = new LinkedHashMap<class_5321<class_1937>, LinkedHashSet<String>>();
    private static final Map<class_5321<class_1937>, Set<Long>> FORCED_VILLAGE_GENERATION_CHUNKS = new LinkedHashMap<class_5321<class_1937>, Set<Long>>();
    private static CachedLandmass cachedLandmass;
    private static CachedPlannedVillages cachedPlannedVillages;
    private static int villageStockSyncCursor;
    private static int villagePopulationCursor;

    private GotVillages() {
    }

    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(GotVillages::tickServer);
        DailyTaskRegistry.register(class_2960.method_60655((String)"agob", (String)"village_maintenance"), GotVillages::runDailyVillageMaintenanceTask);
        DailyTaskRegistry.register(class_2960.method_60655((String)"agob", (String)"village_ai_raids"), GotVillages::runDailyVillageAiRaidTask);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            class_3218 world = server.method_3847(GotDimensionKeys.ICEANDFIRE);
            if (world != null) {
                sender.sendPacket((class_8710)GotVillages.buildVillageMapPayload(world, handler.field_14140));
            }
        });
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            class_3218 world;
            class_1937 patt0$temp = newPlayer.method_37908();
            if (patt0$temp instanceof class_3218 && (world = (class_3218)patt0$temp).method_27983().equals(GotDimensionKeys.ICEANDFIRE)) {
                ServerPlayNetworking.send((class_3222)newPlayer, (class_8710)GotVillages.buildVillageMapPayload(world, newPlayer));
            }
        });
    }

    public static VillageWorldState getWorldState(class_3218 world) {
        return VillageWorldState.get(world);
    }

    public static boolean isVillageTravelReady(class_3218 world, String villageId) {
        if (world == null || villageId == null || villageId.isBlank()) {
            return false;
        }
        VillageInstance village = GotVillages.getWorldState(world).get(villageId);
        return village != null && village.hasGeneratedStructures();
    }

    public static boolean requestPriorityVillageGeneration(class_3218 world, String villageId) {
        if (world == null || villageId == null || villageId.isBlank()) {
            return false;
        }
        VillageInstance existing = GotVillages.getWorldState(world).get(villageId);
        if (existing != null && existing.hasGeneratedStructures()) {
            GotVillages.clearPriorityVillageGeneration(world, villageId);
            return true;
        }
        Map<String, PlannedVillage> plannedVillages = GotVillages.planVillages(world);
        PlannedVillage plannedVillage = GotVillages.findPlannedVillage(plannedVillages, villageId);
        if (plannedVillage == null) {
            return false;
        }
        GotVillages.ensurePlannedVillagesPersisted(world, plannedVillages);
        PRIORITIZED_VILLAGE_GENERATION_IDS.computeIfAbsent((class_5321<class_1937>)world.method_27983(), ignored -> new LinkedHashSet()).add(plannedVillage.id());
        return true;
    }

    public static String resolveVillageLocationName(class_3218 world, String villageId) {
        if (world == null || villageId == null || villageId.isBlank()) {
            return "";
        }
        VillageInstance village = GotVillages.getWorldState(world).get(villageId);
        if (village != null && !village.getName().isBlank()) {
            return village.getName();
        }
        PlannedVillage plannedVillage = GotVillages.findPlannedVillage(GotVillages.planVillages(world), villageId);
        return plannedVillage == null ? villageId : plannedVillage.name();
    }

    public static VillageMapPayload buildVillageMapPayload(class_3218 world, class_3222 player) {
        LinkedHashMap<String, PlannedVillage> plannedVillages = new LinkedHashMap<String, PlannedVillage>();
        for (VillageInstance village : GotVillages.getWorldState(world).getAll()) {
            GotVillages.ensureVillageEconomyData(world, village);
            CastleInstance boundCastle = GotVillages.findBoundCastle(world, village.getBoundCastleId());
            String boundCastleId = boundCastle != null ? boundCastle.getId() : village.getBoundCastleId();
            String boundCastleName = boundCastle != null ? GotVillages.displayNameForBoundCastle(boundCastle) : village.getBoundCastleName();
            String kingdomId = boundCastle != null && !boundCastle.getOwnerKingdomId().isBlank() ? boundCastle.getOwnerKingdomId() : village.getKingdomId();
            plannedVillages.put(village.getId(), new PlannedVillage(village.getId(), village.getName(), boundCastleId, boundCastleName, kingdomId, village.getCenterPos(), true, village.getResourceId()));
        }
        for (PlannedVillage plannedVillage : GotVillages.planVillages(world).values()) {
            plannedVillages.putIfAbsent(plannedVillage.id(), plannedVillage);
        }
        GotVillages.ensurePlannedVillagesPersisted(world, plannedVillages);
        PlayerCastleTravelHolder travelHolder = (PlayerCastleTravelHolder)player;
        List<VillageMapPayload.Entry> villages = plannedVillages.values().stream().map(planned -> new VillageMapPayload.Entry(planned.id(), planned.name(), planned.boundCastleId(), planned.boundCastleName(), planned.kingdomId(), planned.centerPos().method_10263(), planned.centerPos().method_10260(), travelHolder.got$hasUnlockedCastle(planned.id()), planned.resourceId())).toList();
        return new VillageMapPayload(villages);
    }

    public static void openVillageScreen(class_3222 player, String villageId) {
        class_3218 world;
        block5: {
            block4: {
                class_1937 class_19372 = player.method_37908();
                if (!(class_19372 instanceof class_3218)) break block4;
                world = (class_3218)class_19372;
                if (villageId != null && !villageId.isBlank()) break block5;
            }
            return;
        }
        OpenVillageScreenPayload payload = GotVillages.buildVillageScreenPayload(world, villageId);
        if (payload != null) {
            ServerPlayNetworking.send((class_3222)player, (class_8710)payload);
            return;
        }
        player.method_7353((class_2561)class_2561.method_43470((String)"Unable to open village details right now.").method_27692(class_124.field_1061), true);
    }

    public static void handleVillageRaidRequest(class_3222 player, String villageId) {
        class_3218 world;
        block6: {
            block5: {
                class_1937 class_19372 = player.method_37908();
                if (!(class_19372 instanceof class_3218)) break block5;
                world = (class_3218)class_19372;
                if (villageId != null && !villageId.isBlank()) break block6;
            }
            return;
        }
        VillageInstance village = GotVillages.getWorldState(world).get(villageId);
        if (village == null || !GotVillages.canStartVillageRaid(player, world, village)) {
            return;
        }
        long endsAtTick = world.method_8510() + 6000L;
        ActiveVillageRaid raid = new ActiveVillageRaid((class_5321<class_1937>)world.method_27983(), player.method_5667(), village.getId(), endsAtTick, 6000L);
        raid.bossBar().method_14088(player);
        raid.updateBossBar(6000L);
        ACTIVE_VILLAGE_RAIDS.put(player.method_5667(), raid);
        village.setRaidEndsAtTick(endsAtTick);
        village.setRaidCooldownEndsAtTick(world.method_8510() + 6000L);
        GotVillages.clearVillagePopulation(world, village.getId());
        Kingdom kingdom = GotVillages.resolvedVillageKingdom(world, village);
        if (kingdom != null) {
            GotVillages.spawnVillagePopulation(world, village, kingdom);
            GotVillages.spawnVillageRaidGuards(world, village, player, endsAtTick, kingdom);
        }
        GotVillages.alertVillageToPlayer(world, village, player, endsAtTick);
        GotTroops.forceSummonTroops(player);
        ServerPlayNetworking.send((class_3222)player, (class_8710)new VillageRaidStatePayload(true, village.getId()));
        GotVillages.syncVillages(world);
    }

    public static void onVillageNpcKilled(class_3218 world, AbstractKingdomNpcEntity entity) {
        ActiveVillageRaid raid;
        if (world == null || entity == null || entity.got$getCastle().isBlank()) {
            return;
        }
        if (entity.method_59922().method_43057() < 0.1f) {
            GotVillages.replaceVillageNpcProfile(world, entity);
        }
        if ((raid = GotVillages.findActiveVillageRaid(entity.got$getCastle())) != null && !GotVillages.hasLivingVillageNpcs(world, raid.villageId())) {
            GotVillages.completeVillageRaid(world, raid);
        }
    }

    public static void onVillageNpcHurt(class_3218 world, AbstractKingdomNpcEntity victim, class_3222 attacker) {
        if (world == null || victim == null || attacker == null || victim.got$getCastle().isBlank()) {
            return;
        }
        VillageInstance village = GotVillages.getWorldState(world).get(victim.got$getCastle());
        if (village != null) {
            GotVillages.alertVillageToPlayer(world, village, attacker, world.method_8510() + 2400L);
        }
    }

    public static boolean isWithinVillageClaim(class_3218 world, String villageId, class_2338 pos) {
        if (world == null || villageId == null || villageId.isBlank() || pos == null) {
            return false;
        }
        VillageInstance village = GotVillages.getWorldState(world).get(villageId);
        if (village == null) {
            return false;
        }
        int chunkX = pos.method_10263() >> 4;
        int chunkZ = pos.method_10260() >> 4;
        int centerChunkX = village.getCenterPos().method_10263() >> 4;
        int centerChunkZ = village.getCenterPos().method_10260() >> 4;
        int radius = village.getClaimRadiusChunks();
        return Math.abs(chunkX - centerChunkX) <= radius && Math.abs(chunkZ - centerChunkZ) <= radius;
    }

    public static int villageCooldownSeconds(class_3218 world, VillageInstance village) {
        long endsAt;
        if (world == null || village == null) {
            return 0;
        }
        ActiveVillageRaid activeRaid = GotVillages.findActiveVillageRaid(village.getId());
        long l = endsAt = activeRaid != null ? activeRaid.endsAtTick() : village.getRaidCooldownEndsAtTick();
        if (endsAt <= 0L) {
            return 0;
        }
        long remaining = Math.max(0L, endsAt - world.method_8510());
        return class_3532.method_15386((float)((float)remaining / 20.0f));
    }

    public static String getActiveVillageRaidId(UUID playerUuid) {
        if (playerUuid == null) {
            return "";
        }
        ActiveVillageRaid raid = ACTIVE_VILLAGE_RAIDS.get(playerUuid);
        return raid == null ? "" : raid.villageId();
    }

    public static boolean collectCastleTaxFromVillage(class_3218 world, VillageInstance village) {
        if (world == null || village == null || village.getResourceId() == null) {
            return false;
        }
        GotVillages.ensureVillageEconomyData(world, village);
        VillageStockService.synchronizeVillage(village, world);
        CastleInstance castle = GotVillages.findBoundCastle(world, village.getBoundCastleId());
        if (castle == null && (village.getBoundCastleId() == null || village.getBoundCastleId().isBlank())) {
            return false;
        }
        CastleTier targetTier = castle != null ? castle.getTier() : GotVillages.inferredBoundCastleTier(world, village.getBoundCastleId());
        double taxRate = switch (targetTier) {
            default -> throw new MatchException(null, null);
            case CastleTier.SETTLEMENT -> 0.1;
            case CastleTier.LORDSHIP -> 0.2;
            case CastleTier.CAPITAL -> 0.25;
        };
        int stockAfterGrowth = village.getStockAmount();
        int taxAmount = class_3532.method_15384((double)((double)stockAfterGrowth * taxRate));
        if (taxAmount <= 0) {
            return false;
        }
        int moved = GotVillages.transferVillageTaxToCastle(world, village, castle, village.getBoundCastleId(), taxAmount);
        if (moved <= 0) {
            return false;
        }
        village.setStockAmount(Math.max(0, stockAfterGrowth - moved));
        GotVillages.getWorldState(world).method_80();
        return true;
    }

    public static void synchronizeVillageAfterCastleOwnershipChange(class_3218 world, CastleInstance castle) {
        if (world == null || castle == null) {
            return;
        }
        VillageInstance village = GotVillages.getWorldState(world).findByBoundCastle(castle.getId());
        if (village == null) {
            return;
        }
        village.setBoundCastleId(castle.getId());
        village.setBoundCastleName(GotVillages.displayNameForBoundCastle(castle));
        village.setKingdomId(castle.getOwnerKingdomId());
        village.setOwnerPlayerUuid(castle.getOwnerPlayerUuid());
        Kingdom kingdom = GotVillages.resolvedVillageKingdom(world, village);
        if (village.hasGeneratedStructures() && kingdom != null && GotVillages.isAreaLoaded(world, village.getCenterPos(), village.getClaimRadiusChunks())) {
            GotVillages.clearVillagePopulation(world, village.getId());
            GotVillages.spawnVillagePopulation(world, village, kingdom);
        }
        GotVillages.getWorldState(world).method_80();
        GotVillages.syncVillages(world);
    }

    private static String displayNameForBoundCastle(CastleInstance castle) {
        if (castle == null) {
            return "";
        }
        String displayName = GotCastles.displayNameForCastle(castle);
        return displayName == null || displayName.isBlank() ? castle.getId() : displayName;
    }

    private static void tickServer(MinecraftServer server) {
        class_3218 world = server.method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return;
        }
        long worldTime = world.method_8510();
        if (worldTime % 200L == 0L) {
            GotVillages.runVillageMaintenanceBatch(world);
        }
        if (worldTime % 20L == 0L) {
            GotVillages.tickActiveVillageRaids(world);
        }
        if (worldTime % 200L == 0L) {
            GotVillages.tickVillageAiRaids(world);
        }
        boolean plannedVillagesChanged = false;
        int placed = 0;
        Map<String, PlannedVillage> plannedVillages = Map.of();
        if (GotVillages.shouldProcessVillageGeneration(world)) {
            plannedVillages = GotVillages.planVillagesForGeneration(world);
            GotVillages.updateForcedVillageGenerationChunks(world, plannedVillages);
            plannedVillagesChanged = GotVillages.ensurePlannedVillagesPersisted(world, plannedVillages);
            for (PlannedVillage planned : GotVillages.selectPlannedVillagesForGeneration(world, plannedVillages)) {
                if (!planned.generated() && GotVillages.tryGenerateVillage(world, planned) && ++placed >= 1) break;
            }
        }
        if (plannedVillagesChanged || placed > 0) {
            GotVillages.syncVillages(world);
        }
        if (worldTime % 200L == 0L) {
            GotVillages.updateVillageDiscovery(world);
        }
    }

    private static void runDailyVillageMaintenanceTask(class_3218 world, DailyTaskContext context) {
        if (!GotDimensionKeys.ICEANDFIRE.equals((Object)world.method_27983())) {
            return;
        }
        GotVillages.runVillageMaintenanceBatch(world);
    }

    private static void runDailyVillageAiRaidTask(class_3218 world, DailyTaskContext context) {
        if (!GotDimensionKeys.ICEANDFIRE.equals((Object)world.method_27983())) {
            return;
        }
        GotVillages.tickVillageAiRaids(world);
        GotVillages.scheduleVillageAiRaids(world);
    }

    private static void runVillageMaintenanceBatch(class_3218 world) {
        VillageWorldState state = GotVillages.getWorldState(world);
        ArrayList<VillageInstance> villages = new ArrayList<VillageInstance>(state.getAll());
        if (villages.isEmpty()) {
            villageStockSyncCursor = 0;
            villagePopulationCursor = 0;
            return;
        }
        boolean changed = GotVillages.synchronizeVillageStockBatch(world, villages, 8);
        if (changed |= GotVillages.reconcileLoadedVillagePopulationBatch(world, villages, 3)) {
            state.method_80();
        }
    }

    private static boolean synchronizeVillageStockBatch(class_3218 world, List<VillageInstance> villages, int budget) {
        boolean changed = false;
        for (int processed = 0; processed < budget && !villages.isEmpty(); ++processed) {
            if (villageStockSyncCursor >= villages.size()) {
                villageStockSyncCursor = 0;
            }
            VillageInstance village = villages.get(villageStockSyncCursor++);
            GotVillages.ensureVillageEconomyData(world, village);
            changed |= VillageStockService.synchronizeVillage(village, world);
        }
        return changed;
    }

    private static boolean shouldProcessVillageGeneration(class_3218 world) {
        if (GotVillages.hasPriorityVillageGeneration(world)) {
            return world.method_8510() % 10L == 0L;
        }
        return world.method_8510() % 200L == 0L;
    }

    private static boolean tryGenerateVillage(class_3218 world, PlannedVillage planned) {
        Kingdom kingdom;
        if (!GotVillages.isAreaLoaded(world, planned.centerPos(), 1)) {
            return false;
        }
        GotCastles.VillageAnchorTarget target = GotVillages.resolveVillageAnchorTarget(world, planned.boundCastleId());
        if (target == null) {
            return false;
        }
        class_2960 kingdomKey = class_2960.method_12829((String)planned.kingdomId());
        Kingdom kingdom2 = kingdom = kingdomKey == null ? null : GotKingdoms.get(kingdomKey);
        if (kingdom == null) {
            return false;
        }
        Kingdom placementKingdom = GotVillages.resolveVillagePlacementKingdom(world, target, kingdom);
        VillageWorldState state = GotVillages.getWorldState(world);
        VillageInstance village = state.get(planned.id());
        if (!(GotVillages.isValidGeneratedVillageCenter(world, GotVillages.getWorldState(world), target, placementKingdom, planned.centerPos()) || village != null && !village.hasGeneratedStructures() && GotVillages.canGeneratePersistedVillageAtPlannedCenter(world, target, planned.centerPos()))) {
            return false;
        }
        if (!GotVillages.placeVillageStructures(world, planned.centerPos())) {
            return false;
        }
        if (village == null) {
            village = GotVillages.createVillage(world, target, kingdom, planned.centerPos());
            if (village == null) {
                return false;
            }
            state.put(village);
        }
        village.setBoundCastleId(target.castleId());
        village.setBoundCastleName(target.castleName());
        village.setKingdomId(kingdom.getId().toString());
        village.setResourceId(planned.resourceId());
        village.setStructuresGenerated(true);
        state.method_80();
        GotVillages.clearPriorityVillageGeneration(world, planned.id());
        GotVillages.spawnVillagePopulation(world, village, kingdom);
        GotCastles.syncExternalClaimOutlines(world);
        GotMod.LOGGER.info("Generated village {} for castle {} at {} with resource {}", new Object[]{village.getName(), village.getBoundCastleName(), village.getCenterPos().method_23854(), village.getResourceId()});
        return true;
    }

    private static VillageInstance createVillage(class_3218 world, GotCastles.VillageAnchorTarget target, Kingdom kingdom, class_2338 center) {
        class_1923 centerChunk = new class_1923(center);
        String castleName = target.castleName();
        String villageId = target.castleId() + "_village";
        String villageName = VillageNameRegistry.resolve(target.castleId(), castleName);
        VillageInstance village = new VillageInstance(villageId, villageName, target.castleId(), castleName, kingdom.getId().toString(), new class_2338(centerChunk.method_33940(), world.method_8624(class_2902.class_2903.field_13203, centerChunk.method_33940(), centerChunk.method_33942()), centerChunk.method_33942()), 1);
        VillageStockService.initializeVillage(village, world.method_8510());
        GotVillages.initializeVillagePopulationCycle(village, world);
        village.setStructuresGenerated(true);
        return village;
    }

    private static boolean placeVillageStructures(class_3218 world, class_2338 center) {
        class_3485 templateManager = world.method_14183();
        class_1923 centerChunk = new class_1923(center);
        ArrayList<VillageStructurePlan> plans = new ArrayList<VillageStructurePlan>();
        VillageStructurePlan claim = GotVillages.planVillageStructure(world, templateManager, new class_1923(centerChunk.field_9181, centerChunk.field_9180), CLAIM_STRUCTURE, true);
        if (claim == null) {
            return false;
        }
        plans.add(claim);
        GotVillages.addVillageStructurePlan(plans, GotVillages.planVillageStructure(world, templateManager, new class_1923(centerChunk.field_9181 - 1, centerChunk.field_9180), FARMSTEAD_STRUCTURE, false));
        GotVillages.addVillageStructurePlan(plans, GotVillages.planVillageStructure(world, templateManager, new class_1923(centerChunk.field_9181 + 1, centerChunk.field_9180), BARN_STRUCTURE, false));
        GotVillages.addVillageStructurePlan(plans, GotVillages.planVillageStructure(world, templateManager, new class_1923(centerChunk.field_9181, centerChunk.field_9180 - 1), SMITHY_STRUCTURE, false));
        int villageFloorY = GotVillages.resolveVillageStructureFloorY(world, center, plans);
        GotVillages.prepareConnectedVillagePlatform(world, plans, villageFloorY);
        for (VillageStructurePlan plan : plans) {
            if (GotVillages.placePlannedVillageStructure(world, plan, villageFloorY) || !plan.required()) continue;
            return false;
        }
        return true;
    }

    private static void addVillageStructurePlan(List<VillageStructurePlan> plans, VillageStructurePlan plan) {
        if (plan != null) {
            plans.add(plan);
        }
    }

    private static boolean isValidVillageCenter(class_3218 world, VillageWorldState villageState, CastleInstance castle, Kingdom kingdom, class_2338 center) {
        if (villageState.overlapsClaim(center, 1)) {
            return false;
        }
        class_1923 centerChunk = new class_1923(center);
        for (int offsetX = -1; offsetX <= 1; ++offsetX) {
            for (int offsetZ = -1; offsetZ <= 1; ++offsetZ) {
                class_1923 chunk = new class_1923(centerChunk.field_9181 + offsetX, centerChunk.field_9180 + offsetZ);
                class_2338 sample = new class_2338(chunk.method_33940(), world.method_8615(), chunk.method_33942());
                if (CastleAreaOwnershipResolver.findCastleAt(world, sample) != castle) {
                    return false;
                }
                Kingdom biomeKingdom = GotVillages.kingdomForBiome(world, sample);
                if (biomeKingdom != null && biomeKingdom.getId().equals((Object)kingdom.getId())) continue;
                return false;
            }
        }
        return true;
    }

    private static boolean isValidGeneratedVillageCenter(class_3218 world, VillageWorldState villageState, GotCastles.VillageAnchorTarget target, Kingdom kingdom, class_2338 center) {
        class_2338 anchor = new class_2338(target.worldX(), center.method_10264(), target.worldZ());
        if (!GotVillages.isOnSameIsland(anchor, center)) {
            return false;
        }
        return GotVillages.isValidPlannedVillageCenter(world, villageState, target, kingdom, center, 0, 1500);
    }

    private static boolean canGeneratePersistedVillageAtPlannedCenter(class_3218 world, GotCastles.VillageAnchorTarget target, class_2338 center) {
        if (world == null || target == null || center == null) {
            return false;
        }
        class_2338 anchor = new class_2338(target.worldX(), center.method_10264(), target.worldZ());
        return GotVillages.isOnSameIsland(anchor, center);
    }

    private static Map<String, PlannedVillage> planVillages(class_3218 world) {
        VillageWorldState villageState = GotVillages.getWorldState(world);
        List<GotCastles.VillageAnchorTarget> targets = GotCastles.collectVillageAnchorTargets(world).stream().sorted(Comparator.comparing(GotCastles.VillageAnchorTarget::castleId)).toList();
        return GotVillages.planVillagesForTargets(world, villageState, targets, true);
    }

    private static Map<String, PlannedVillage> planVillagesForGeneration(class_3218 world) {
        VillageWorldState villageState = GotVillages.getWorldState(world);
        List<GotCastles.VillageAnchorTarget> targets = GotCastles.collectVillageAnchorTargets(world).stream().filter(target -> GotVillages.isVillageGenerationTarget(world, villageState, target)).sorted(Comparator.comparing(GotCastles.VillageAnchorTarget::castleId)).toList();
        return GotVillages.planVillagesForTargets(world, villageState, targets, false);
    }

    private static Map<String, PlannedVillage> planVillagesForTargets(class_3218 world, VillageWorldState villageState, List<GotCastles.VillageAnchorTarget> targets, boolean useCache) {
        int villageSignature = GotVillages.plannedVillageStateSignature(villageState);
        int targetSignature = GotVillages.villageAnchorSignature(targets);
        if (useCache && cachedPlannedVillages != null && cachedPlannedVillages.villageSignature() == villageSignature && cachedPlannedVillages.targetSignature() == targetSignature) {
            return cachedPlannedVillages.plannedByCastleId();
        }
        LinkedHashMap<String, PlannedVillage> planned = new LinkedHashMap<String, PlannedVillage>();
        ArrayList<class_2338> reservedCenters = new ArrayList<class_2338>();
        for (VillageInstance village : villageState.getAll()) {
            reservedCenters.add(village.getCenterPos());
        }
        for (GotCastles.VillageAnchorTarget target : targets) {
            VillageInstance existing = villageState.findByBoundCastle(target.castleId());
            if (existing != null) {
                planned.put(target.castleId(), new PlannedVillage(existing.getId(), existing.getName(), existing.getBoundCastleId(), existing.getBoundCastleName(), existing.getKingdomId(), existing.getCenterPos(), existing.hasGeneratedStructures(), existing.getResourceId()));
                continue;
            }
            PlannedVillage next = GotVillages.planVillage(world, target, reservedCenters, planned.values());
            if (next == null) continue;
            planned.put(target.castleId(), next);
            reservedCenters.add(next.centerPos());
        }
        Map<String, PlannedVillage> plannedByCastleId = Collections.unmodifiableMap(new LinkedHashMap(planned));
        if (useCache) {
            cachedPlannedVillages = new CachedPlannedVillages(villageSignature, targetSignature, plannedByCastleId);
        }
        return plannedByCastleId;
    }

    private static boolean isVillageGenerationTarget(class_3218 world, VillageWorldState villageState, GotCastles.VillageAnchorTarget target) {
        if (world == null || villageState == null || target == null) {
            return false;
        }
        String villageId = target.castleId() + "_village";
        LinkedHashSet<String> prioritizedIds = PRIORITIZED_VILLAGE_GENERATION_IDS.get(world.method_27983());
        if (prioritizedIds != null && prioritizedIds.contains(villageId)) {
            return true;
        }
        double maxDistanceSquared = 147456.0;
        VillageInstance existing = villageState.findByBoundCastle(target.castleId());
        return existing != null && !existing.hasGeneratedStructures() && GotVillages.nearestPlayerDistanceSq(world, existing.getCenterPos()) <= maxDistanceSquared;
    }

    private static boolean ensurePlannedVillagesPersisted(class_3218 world, Map<String, PlannedVillage> plannedVillages) {
        if (world == null || plannedVillages.isEmpty()) {
            return false;
        }
        VillageWorldState state = GotVillages.getWorldState(world);
        boolean changed = false;
        for (PlannedVillage planned : plannedVillages.values()) {
            VillageInstance existing = state.get(planned.id());
            if (existing != null) {
                if (planned.resourceId() != null && existing.getResourceId() == null) {
                    existing.setResourceId(planned.resourceId());
                    changed = true;
                }
                if (!planned.boundCastleName().equals(existing.getBoundCastleName())) {
                    existing.setBoundCastleName(planned.boundCastleName());
                    changed = true;
                }
                if (planned.kingdomId().equals(existing.getKingdomId())) continue;
                existing.setKingdomId(planned.kingdomId());
                changed = true;
                continue;
            }
            VillageInstance placeholder = new VillageInstance(planned.id(), planned.name(), planned.boundCastleId(), planned.boundCastleName(), planned.kingdomId(), planned.centerPos(), 1);
            placeholder.setResourceId(planned.resourceId());
            placeholder.setStructuresGenerated(false);
            VillageStockService.initializeVillage(placeholder, world.method_8510());
            GotVillages.initializeVillagePopulationCycle(placeholder, world);
            state.put(placeholder);
            changed = true;
        }
        if (changed) {
            state.method_80();
        }
        return changed;
    }

    private static List<PlannedVillage> selectPlannedVillagesForGeneration(class_3218 world, Map<String, PlannedVillage> plannedVillages) {
        if (plannedVillages.isEmpty()) {
            return List.of();
        }
        ArrayList<PlannedVillage> ordered = new ArrayList<PlannedVillage>(plannedVillages.values());
        LinkedHashSet<String> prioritizedIds = PRIORITIZED_VILLAGE_GENERATION_IDS.get(world.method_27983());
        if (prioritizedIds != null && !prioritizedIds.isEmpty()) {
            ordered.removeIf(planned -> !prioritizedIds.contains(planned.id()));
            ordered.sort(Comparator.comparingDouble(planned -> GotVillages.villageInterestDistanceSq(world, planned)));
            return ordered;
        }
        ordered.removeIf(planned -> !GotVillages.isVillageGenerationCandidate(world, planned));
        ordered.sort(Comparator.comparingDouble(planned -> GotVillages.villageInterestDistanceSq(world, planned)));
        return ordered;
    }

    private static boolean hasPriorityVillageGeneration(class_3218 world) {
        LinkedHashSet<String> prioritizedIds = PRIORITIZED_VILLAGE_GENERATION_IDS.get(world.method_27983());
        return prioritizedIds != null && !prioritizedIds.isEmpty();
    }

    private static void clearPriorityVillageGeneration(class_3218 world, String villageId) {
        if (world == null || villageId == null || villageId.isBlank()) {
            return;
        }
        LinkedHashSet<String> prioritizedIds = PRIORITIZED_VILLAGE_GENERATION_IDS.get(world.method_27983());
        if (prioritizedIds == null) {
            return;
        }
        prioritizedIds.remove(villageId);
        if (prioritizedIds.isEmpty()) {
            PRIORITIZED_VILLAGE_GENERATION_IDS.remove(world.method_27983());
        }
    }

    private static void updateForcedVillageGenerationChunks(class_3218 world, Map<String, PlannedVillage> plannedVillages) {
        LinkedHashSet<Long> desiredChunks = new LinkedHashSet<Long>();
        PlannedVillage villageToForce = GotVillages.findPriorityVillagePlan(world, plannedVillages);
        if (villageToForce != null) {
            int centerChunkX = villageToForce.centerPos().method_10263() >> 4;
            int centerChunkZ = villageToForce.centerPos().method_10260() >> 4;
            for (int chunkX = centerChunkX - 1; chunkX <= centerChunkX + 1; ++chunkX) {
                for (int chunkZ = centerChunkZ - 1; chunkZ <= centerChunkZ + 1; ++chunkZ) {
                    desiredChunks.add(class_1923.method_8331((int)chunkX, (int)chunkZ));
                }
            }
        }
        Set previousChunks = FORCED_VILLAGE_GENERATION_CHUNKS.computeIfAbsent((class_5321<class_1937>)world.method_27983(), ignored -> new LinkedHashSet());
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

    private static PlannedVillage findPriorityVillagePlan(class_3218 world, Map<String, PlannedVillage> plannedVillages) {
        LinkedHashSet<String> prioritizedIds = PRIORITIZED_VILLAGE_GENERATION_IDS.get(world.method_27983());
        if (prioritizedIds == null || prioritizedIds.isEmpty()) {
            return null;
        }
        ArrayList<String> staleIds = new ArrayList<String>();
        for (String villageId : prioritizedIds) {
            if (villageId == null || villageId.isBlank()) {
                staleIds.add(villageId);
                continue;
            }
            VillageInstance existing = GotVillages.getWorldState(world).get(villageId);
            if (existing != null && existing.hasGeneratedStructures()) {
                staleIds.add(villageId);
                continue;
            }
            PlannedVillage plannedVillage = GotVillages.findPlannedVillage(plannedVillages, villageId);
            if (plannedVillage == null) continue;
            if (!staleIds.isEmpty()) {
                staleIds.forEach(id -> GotVillages.clearPriorityVillageGeneration(world, id));
            }
            return plannedVillage;
        }
        if (!staleIds.isEmpty()) {
            staleIds.forEach(id -> GotVillages.clearPriorityVillageGeneration(world, id));
        }
        return null;
    }

    private static PlannedVillage findPlannedVillage(Map<String, PlannedVillage> plannedVillages, String villageIdOrCastleId) {
        if (plannedVillages == null || villageIdOrCastleId == null || villageIdOrCastleId.isBlank()) {
            return null;
        }
        PlannedVillage direct = plannedVillages.get(villageIdOrCastleId);
        if (direct != null) {
            return direct;
        }
        for (PlannedVillage plannedVillage : plannedVillages.values()) {
            if (!villageIdOrCastleId.equals(plannedVillage.id()) && !villageIdOrCastleId.equals(plannedVillage.boundCastleId())) continue;
            return plannedVillage;
        }
        return null;
    }

    private static boolean isVillageGenerationCandidate(class_3218 world, PlannedVillage plannedVillage) {
        if (world == null || plannedVillage == null) {
            return false;
        }
        VillageInstance existing = GotVillages.getWorldState(world).get(plannedVillage.id());
        if (existing != null && existing.hasGeneratedStructures()) {
            return false;
        }
        double maxDistanceSquared = 147456.0;
        return GotVillages.villageInterestDistanceSq(world, plannedVillage) <= maxDistanceSquared;
    }

    private static double villageInterestDistanceSq(class_3218 world, PlannedVillage plannedVillage) {
        double nearestDistanceSq = GotVillages.nearestPlayerDistanceSq(world, plannedVillage.centerPos());
        GotCastles.VillageAnchorTarget target = GotVillages.resolveVillageAnchorTarget(world, plannedVillage.boundCastleId());
        if (target != null) {
            class_2338 anchor = new class_2338(target.worldX(), plannedVillage.centerPos().method_10264(), target.worldZ());
            nearestDistanceSq = Math.min(nearestDistanceSq, GotVillages.nearestPlayerDistanceSq(world, anchor));
        }
        return nearestDistanceSq;
    }

    private static double nearestPlayerDistanceSq(class_3218 world, class_2338 pos) {
        if (world == null || pos == null) {
            return Double.MAX_VALUE;
        }
        double nearestDistanceSq = Double.MAX_VALUE;
        class_243 center = class_243.method_24953((class_2382)pos);
        for (class_3222 player : world.method_18456()) {
            double distanceSq;
            if (player.method_7325() || !((distanceSq = player.method_5707(center)) < nearestDistanceSq)) continue;
            nearestDistanceSq = distanceSq;
        }
        return nearestDistanceSq;
    }

    private static PlannedVillage planVillage(class_3218 world, GotCastles.VillageAnchorTarget target, List<class_2338> reservedCenters, Collection<PlannedVillage> existingPlans) {
        Kingdom kingdom;
        class_2960 kingdomId = class_2960.method_12829((String)target.kingdomId());
        Kingdom kingdom2 = kingdom = kingdomId == null ? null : GotKingdoms.get(kingdomId);
        if (kingdom == null) {
            return null;
        }
        Kingdom placementKingdom = GotVillages.resolveVillagePlacementKingdom(world, target, kingdom);
        VillageWorldState villageState = GotVillages.getWorldState(world);
        class_2338 anchor = new class_2338(target.worldX(), world.method_8615(), target.worldZ());
        long seed = anchor.method_10063() ^ (long)target.castleId().hashCode();
        class_5819 random = class_5819.method_43049((long)seed);
        String villageId = target.castleId() + "_village";
        String villageName = VillageNameRegistry.resolve(target.castleId(), target.castleName());
        for (int distanceReduction = 0; distanceReduction <= 1500; distanceReduction += 100) {
            int minDistance = Math.max(0, 1000 - distanceReduction);
            int maxDistance = Math.max(minDistance + 100, 1500 - distanceReduction);
            for (int attempt = 0; attempt < 96; ++attempt) {
                int z;
                int y;
                double angle = random.method_43058() * Math.PI * 2.0;
                double distance = (double)minDistance + random.method_43058() * (double)Math.max(1, maxDistance - minDistance);
                int x = target.worldX() + class_3532.method_15357((double)(Math.cos(angle) * distance));
                class_2338 center = new class_2338(x, y = world.method_8624(class_2902.class_2903.field_13203, x, z = target.worldZ() + class_3532.method_15357((double)(Math.sin(angle) * distance))), z);
                if (!GotVillages.isValidPlannedVillageCenter(world, villageState, target, placementKingdom, center, minDistance, maxDistance) || GotVillages.overlapsReservedCenters(center, reservedCenters)) continue;
                class_2960 resourceId = GotVillages.selectResource(world, center, kingdom, random, GotVillages.plannedVillagesForKingdom(villageState, existingPlans, kingdom.getId().toString(), target.castleId()));
                return new PlannedVillage(villageId, villageName, target.castleId(), target.castleName(), kingdom.getId().toString(), center, false, resourceId);
            }
        }
        return null;
    }

    private static OpenVillageScreenPayload buildVillageScreenPayload(class_3218 world, String villageId) {
        ReligionSelectionOption religionOption;
        String houseName;
        class_2960 kingdomKey;
        Kingdom kingdom;
        VillageInstance village = GotVillages.getWorldState(world).get(villageId);
        if (village == null) {
            return null;
        }
        GotVillages.ensureVillageEconomyData(world, village);
        if (village.getResourceId() == null) {
            village.setResourceId(GotVillageResources.WHEAT.getId());
            GotVillages.getWorldState(world).method_80();
        }
        VillageStockService.synchronizeVillage(village, world);
        String kingdomId = village.getKingdomId();
        String houseId = "";
        String religionId = "";
        CastleInstance boundCastle = GotVillages.findBoundCastle(world, village.getBoundCastleId());
        if (boundCastle != null) {
            if (boundCastle.getOwnerKingdomId() != null && !boundCastle.getOwnerKingdomId().isBlank()) {
                kingdomId = boundCastle.getOwnerKingdomId();
            }
            houseId = boundCastle.getOwnerHouseId();
            religionId = boundCastle.getOwnerReligionId();
        }
        Kingdom kingdom2 = kingdom = (kingdomKey = class_2960.method_12829((String)kingdomId)) == null ? null : GotKingdoms.get(kingdomKey);
        if ((houseId == null || houseId.isBlank()) && kingdomKey != null) {
            houseId = GotCharacterOptions.getPrimaryHouseId(kingdomKey);
        }
        if ((religionId == null || religionId.isBlank()) && kingdom != null) {
            religionId = kingdom.getReligion().getId();
        }
        String kingdomName = kingdom == null ? "" : kingdom.getName();
        CharacterSelectionOption houseOption = houseId == null || houseId.isBlank() ? null : GotCharacterOptions.get(houseId);
        String string = houseName = houseOption == null ? "" : houseOption.houseDisplayName();
        if (houseName.isBlank() && PlayerHouseService.isCustomHouseId(houseId)) {
            PlayerHouseDefinition customHouse = PlayerHouseService.get(world, houseId);
            houseName = customHouse == null ? "" : customHouse.displayName();
        }
        ReligionSelectionOption religionSelectionOption = religionOption = religionId == null || religionId.isBlank() ? null : GotReligionOptions.get(religionId);
        String religionName = religionOption == null ? (kingdom != null ? kingdom.getReligion().getDisplayName() : "") : religionOption.displayName();
        return new OpenVillageScreenPayload(village.getId(), village.getName(), kingdomId == null ? "" : kingdomId, kingdomName, houseId == null ? "" : houseId, houseName, religionId == null ? "" : religionId, religionName, village.getResourceId().toString(), village.getStockAmount(), village.getStockCapacity());
    }

    public static void ensureVillageEconomyData(class_3218 world, VillageInstance village) {
        if (world == null || village == null) {
            return;
        }
        boolean changed = false;
        if (village.getResourceId() == null) {
            Kingdom kingdom = GotVillages.resolvedVillageKingdom(world, village);
            class_5819 random = class_5819.method_43049((long)(village.getCenterPos().method_10063() ^ (long)village.getId().hashCode()));
            class_2960 resourceId = kingdom == null ? GotVillageResources.WHEAT.getId() : GotVillages.selectResource(world, village.getCenterPos(), kingdom, random, GotVillages.plannedVillagesForKingdom(GotVillages.getWorldState(world), List.of(), GotVillages.resolvedVillageKingdomId(world, village), village.getBoundCastleId()));
            village.setResourceId(resourceId);
            changed = true;
        }
        if (village.getStockCapacity() <= 0) {
            village.setStockCapacity(100);
            changed = true;
        }
        if (village.getLastStockGrowthDay() < 0L) {
            village.setStockAmount(Math.min(50, village.getStockCapacity()));
            village.setLastStockGrowthDay(Math.max(Math.max(0L, world.method_8510() / 24000L), Math.max(0L, world.method_8532() / 24000L)));
            village.setLastObservedTimeOfDay(Math.max(0L, world.method_8532() % 24000L));
            changed = true;
        }
        if (village.getLastPopulationRefreshDay() < 0L || village.getLastObservedPopulationTimeOfDay() < 0L) {
            GotVillages.initializeVillagePopulationCycle(village, world);
            changed = true;
        }
        if (changed) {
            GotVillages.getWorldState(world).method_80();
        }
    }

    private static void initializeVillagePopulationCycle(VillageInstance village, class_3218 world) {
        if (village == null || world == null) {
            return;
        }
        village.setLastPopulationRefreshDay(Math.max(0L, world.method_8510() / 24000L));
        village.setLastObservedPopulationTimeOfDay(GotVillages.normalizedTimeOfDay(world.method_8532()));
    }

    private static class_2960 selectResource(class_3218 world, class_2338 pos, Kingdom kingdom, class_5819 random, List<VillageInstance> kingdomVillages) {
        String kingdomPath = kingdom.getId().method_12832();
        String biomePath = world.method_23753(pos).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        Map<class_2960, Integer> counts = GotVillages.resourceCounts(kingdomVillages);
        ArrayList<WeightedVillageResource> weights = new ArrayList<WeightedVillageResource>();
        GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WHEAT, 6);
        GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.CARROTS, 4);
        GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.POTATOES, 4);
        VillageResource biomeWood = GotVillages.preferredWoodForBiome(biomePath, kingdomPath);
        if (biomeWood != null) {
            GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), biomeWood, 4);
        }
        if (biomePath.contains("forest") || biomePath.contains("wood") || biomePath.contains("rainwood")) {
            GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), biomeWood != null ? biomeWood : GotVillageResources.WOOD_OAK, 2);
        }
        if (biomePath.contains("hills") || biomePath.contains("moor")) {
            GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.IRON_ORE, 2);
        }
        if (biomePath.contains("oasis")) {
            GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.HORSES, 2);
            GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.CARROTS, 2);
        }
        if (biomePath.contains("orchard") || biomePath.contains("valley") || biomePath.contains("meadow")) {
            GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.HORSES, 2);
            GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WHEAT, 2);
        }
        if (biomePath.contains("marsh")) {
            GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.CARROTS, 2);
        }
        switch (kingdomPath) {
            case "the_north": {
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.IRON_ORE, 4);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WOOD_SPRUCE, 4);
                break;
            }
            case "iron_islands": {
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.IRON_ORE, 5);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WOOD_DARK_OAK, 3);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.HORSES, 1);
                break;
            }
            case "dorne": {
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.COPPER_ORE, 5);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WOOD_ACACIA, 4);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.HORSES, 2);
                break;
            }
            case "the_westerlands": {
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.GOLD_ORE, 5);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.IRON_ORE, 2);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.HORSES, 2);
                break;
            }
            case "the_reach": {
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.HORSES, 5);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.GOLD_ORE, 2);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WHEAT, 3);
                break;
            }
            case "the_vale": {
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.HORSES, 4);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WOOD_BIRCH, 3);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.IRON_ORE, 2);
                break;
            }
            case "the_riverlands": {
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WHEAT, 3);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.CARROTS, 3);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WOOD_OAK, 3);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.HORSES, 1);
                break;
            }
            case "the_stormlands": {
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WOOD_JUNGLE, 2);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WOOD_DARK_OAK, 3);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.HORSES, 2);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.IRON_ORE, 1);
                break;
            }
            case "the_crownlands": {
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.HORSES, 3);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WOOD_OAK, 2);
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.GOLD_ORE, 1);
                break;
            }
            default: {
                GotVillages.addWeightedResource(weights, counts, kingdomVillages.size(), GotVillageResources.WOOD_OAK, 2);
            }
        }
        if (weights.isEmpty()) {
            return GotVillageResources.WHEAT.getId();
        }
        int totalWeight = 0;
        for (WeightedVillageResource weighted : weights) {
            totalWeight += weighted.weight();
        }
        int roll = random.method_43048(totalWeight);
        for (WeightedVillageResource weighted : weights) {
            if ((roll -= weighted.weight()) >= 0) continue;
            return weighted.resource().getId();
        }
        return ((WeightedVillageResource)weights.get(weights.size() - 1)).resource().getId();
    }

    private static List<VillageInstance> plannedVillagesForKingdom(VillageWorldState villageState, Collection<PlannedVillage> existingPlans, String kingdomId, String excludedCastleId) {
        ArrayList<VillageInstance> villages = new ArrayList<VillageInstance>();
        for (VillageInstance village : villageState.getAll()) {
            if (!kingdomId.equals(village.getKingdomId()) || excludedCastleId.equals(village.getBoundCastleId())) continue;
            villages.add(village);
        }
        for (PlannedVillage plannedVillage : existingPlans) {
            if (!kingdomId.equals(plannedVillage.kingdomId()) || excludedCastleId.equals(plannedVillage.boundCastleId())) continue;
            VillageInstance village = new VillageInstance(plannedVillage.id(), plannedVillage.name(), plannedVillage.boundCastleId(), plannedVillage.boundCastleName(), plannedVillage.kingdomId(), plannedVillage.centerPos(), 1);
            village.setResourceId(plannedVillage.resourceId());
            villages.add(village);
        }
        return villages;
    }

    private static Map<class_2960, Integer> resourceCounts(List<VillageInstance> villages) {
        LinkedHashMap<class_2960, Integer> counts = new LinkedHashMap<class_2960, Integer>();
        for (VillageInstance village : villages) {
            class_2960 resourceId = village.getResourceId();
            if (resourceId == null) continue;
            counts.merge(resourceId, 1, Integer::sum);
        }
        return counts;
    }

    private static void addWeightedResource(List<WeightedVillageResource> weights, Map<class_2960, Integer> counts, int kingdomVillageCount, VillageResource resource, int baseWeight) {
        if (resource == null || baseWeight <= 0) {
            return;
        }
        int duplicates = counts.getOrDefault(resource.getId(), 0);
        int adjustedWeight = Math.max(0, baseWeight - duplicates * 2);
        if (duplicates > 0 && duplicates == kingdomVillageCount && kingdomVillageCount > 0) {
            adjustedWeight = Math.min(adjustedWeight, 1);
        }
        if (adjustedWeight > 0) {
            weights.add(new WeightedVillageResource(resource, adjustedWeight));
        }
    }

    private static VillageResource preferredWoodForBiome(String biomePath, String kingdomPath) {
        if (biomePath.contains("wolfswood") || biomePath.contains("always_winter") || biomePath.contains("north")) {
            return GotVillageResources.WOOD_SPRUCE;
        }
        if (biomePath.contains("rainwood")) {
            return GotVillageResources.WOOD_JUNGLE;
        }
        if (biomePath.contains("kingswood") || biomePath.contains("goldgrass") || biomePath.contains("orchard")) {
            return GotVillageResources.WOOD_OAK;
        }
        if (biomePath.contains("vale")) {
            return GotVillageResources.WOOD_BIRCH;
        }
        if (biomePath.contains("dorne")) {
            return GotVillageResources.WOOD_ACACIA;
        }
        if (biomePath.contains("iron_islands")) {
            return GotVillageResources.WOOD_DARK_OAK;
        }
        return switch (kingdomPath) {
            case "the_north" -> GotVillageResources.WOOD_SPRUCE;
            case "the_vale" -> GotVillageResources.WOOD_BIRCH;
            case "dorne" -> GotVillageResources.WOOD_ACACIA;
            case "iron_islands" -> GotVillageResources.WOOD_DARK_OAK;
            case "the_stormlands" -> GotVillageResources.WOOD_JUNGLE;
            default -> GotVillageResources.WOOD_OAK;
        };
    }

    private static boolean isValidPlannedVillageCenter(class_3218 world, VillageWorldState villageState, GotCastles.VillageAnchorTarget target, Kingdom kingdom, class_2338 center, int minDistance, int maxDistance) {
        if (villageState.overlapsClaim(center, 1)) {
            return false;
        }
        class_2338 anchor = new class_2338(target.worldX(), center.method_10264(), target.worldZ());
        if (!GotVillages.isWithinVillageRing(anchor, center, minDistance, maxDistance)) {
            return false;
        }
        if (!GotVillages.isOnSameIsland(anchor, center)) {
            return false;
        }
        class_1923 centerChunk = new class_1923(center);
        for (int offsetX = -1; offsetX <= 1; ++offsetX) {
            for (int offsetZ = -1; offsetZ <= 1; ++offsetZ) {
                class_1923 chunk = new class_1923(centerChunk.field_9181 + offsetX, centerChunk.field_9180 + offsetZ);
                class_2338 sample = new class_2338(chunk.method_33940(), world.method_8615(), chunk.method_33942());
                Kingdom biomeKingdom = GotVillages.kingdomForBiome(world, sample);
                if (biomeKingdom != null && biomeKingdom.getId().equals((Object)kingdom.getId())) continue;
                return false;
            }
        }
        return true;
    }

    private static boolean isWithinVillageRing(class_2338 anchor, class_2338 center, int minDistance, int maxDistance) {
        double distanceSquared = anchor.method_10262((class_2382)center);
        double minDistanceSquared = (double)minDistance * (double)minDistance;
        double maxDistanceSquared = (double)maxDistance * (double)maxDistance;
        return distanceSquared >= minDistanceSquared && distanceSquared <= maxDistanceSquared;
    }

    private static Kingdom resolveVillagePlacementKingdom(class_3218 world, GotCastles.VillageAnchorTarget target, Kingdom fallbackKingdom) {
        if (world == null || target == null) {
            return fallbackKingdom;
        }
        class_2338 anchor = new class_2338(target.worldX(), world.method_8615(), target.worldZ());
        Kingdom biomeKingdom = GotVillages.kingdomForBiome(world, anchor);
        return biomeKingdom != null ? biomeKingdom : fallbackKingdom;
    }

    private static boolean isOnSameIsland(class_2338 anchor, class_2338 center) {
        CachedLandmass landmass = GotVillages.getLandmass();
        if (landmass == null) {
            return true;
        }
        return landmass.isSameLandComponent(anchor, center);
    }

    private static boolean overlapsReservedCenters(class_2338 center, List<class_2338> reservedCenters) {
        int chunkX = center.method_10263() >> 4;
        int chunkZ = center.method_10260() >> 4;
        for (class_2338 reserved : reservedCenters) {
            boolean overlapZ;
            int reservedChunkX = reserved.method_10263() >> 4;
            int reservedChunkZ = reserved.method_10260() >> 4;
            boolean overlapX = Math.abs(chunkX - reservedChunkX) <= 2;
            boolean bl = overlapZ = Math.abs(chunkZ - reservedChunkZ) <= 2;
            if (!overlapX || !overlapZ) continue;
            return true;
        }
        return false;
    }

    private static VillageStructurePlan planVillageStructure(class_3218 world, class_3485 templateManager, class_1923 chunkPos, String structurePath, boolean required) {
        class_2960 structureId = class_2960.method_60655((String)"agob", (String)structurePath);
        class_3499 template = templateManager.method_15094(structureId).orElse(null);
        if (template == null) {
            GotMod.LOGGER.warn("Missing village structure template {}", (Object)structureId);
            return null;
        }
        PlacementCandidate candidate = GotVillages.findVillageStructurePlacement(world, chunkPos, template.method_15160(), required);
        if (candidate == null) {
            GotMod.LOGGER.info("Skipping village structure {} in chunk {} due to invalid surface", (Object)structureId, (Object)chunkPos);
            return null;
        }
        class_2338 origin = candidate.origin();
        SurfaceStats stats = candidate.stats();
        if (required && !stats.valid()) {
            GotMod.LOGGER.info("Skipping village structure {} in chunk {} due to invalid surface", (Object)structureId, (Object)chunkPos);
            return null;
        }
        return new VillageStructurePlan(structureId, template, origin, template.method_15160(), stats, required);
    }

    private static boolean placePlannedVillageStructure(class_3218 world, VillageStructurePlan plan, int floorY) {
        class_2338 origin = new class_2338(plan.origin().method_10263(), floorY + 1, plan.origin().method_10260());
        class_3492 placement = new class_3492().method_15133(false).method_15131(false).method_27264(false);
        if (!plan.template().method_15172((class_5425)world, origin, origin, placement, world.field_9229, 50)) {
            GotMod.LOGGER.warn("Failed to place village structure {}", (Object)plan.structureId());
            return false;
        }
        GotVillages.stabilizeStructureFooting(world, origin, plan.size(), floorY);
        return true;
    }

    private static PlacementCandidate findVillageStructurePlacement(class_3218 world, class_1923 chunkPos, class_2382 templateSize, boolean aggressivePad) {
        if (aggressivePad) {
            class_2338 origin = GotVillages.findChunkStructureOrigin(world, chunkPos, templateSize);
            return new PlacementCandidate(origin, GotVillages.sampleSurface(world, origin, templateSize));
        }
        int availableX = Math.max(0, 16 - templateSize.method_10263());
        int availableZ = Math.max(0, 16 - templateSize.method_10260());
        int[] offsetsX = GotVillages.candidateOffsets(availableX);
        int[] offsetsZ = GotVillages.candidateOffsets(availableZ);
        PlacementCandidate best = null;
        int bestPenalty = Integer.MAX_VALUE;
        for (int offsetX : offsetsX) {
            for (int offsetZ : offsetsZ) {
                int x = chunkPos.method_8326() + offsetX;
                int z = chunkPos.method_8328() + offsetZ;
                int y = world.method_8624(class_2902.class_2903.field_13203, x + templateSize.method_10263() / 2, z + templateSize.method_10260() / 2);
                class_2338 origin = new class_2338(x, y, z);
                SurfaceStats stats = GotVillages.sampleLooseSurface(world, origin, templateSize);
                int penalty = Math.abs(offsetX - availableX / 2) + Math.abs(offsetZ - availableZ / 2);
                if (best != null && penalty >= bestPenalty) continue;
                best = new PlacementCandidate(origin, stats);
                bestPenalty = penalty;
            }
        }
        return best;
    }

    private static int[] candidateOffsets(int available) {
        if (available <= 0) {
            return new int[]{0};
        }
        int center = available / 2;
        return new int[]{center, 0, available, Math.max(0, center / 2), Math.min(available, center + Math.max(1, available / 4))};
    }

    private static void spawnVillagePopulation(class_3218 world, VillageInstance village, Kingdom kingdom) {
        if (world == null || village == null || kingdom == null) {
            return;
        }
        GotVillages.reconcileVillagePopulation(world, village, kingdom);
    }

    private static void spawnVillageCivilian(class_3218 world, VillageInstance village, class_1299<? extends AbstractKingdomNpcEntity> civilianType, int seedOffset) {
        AbstractKingdomNpcEntity villager = (AbstractKingdomNpcEntity)civilianType.method_5883((class_1937)world);
        if (villager == null) {
            return;
        }
        class_2338 spawnPos = GotVillages.findVillageSpawnPos(world, village.getCenterPos(), seedOffset);
        villager.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
        villager.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16474, null);
        villager.got$setCastle(village.getId());
        villager.setPointOfInterest(village.getCenterPos());
        GotVillages.ensureVillageNpcIdentity(world, villager);
        world.method_8649((class_1297)villager);
    }

    private static void spawnVillageElder(class_3218 world, VillageInstance village, Kingdom kingdom, class_1299<? extends AbstractKingdomVillageElderNpcEntity> elderType) {
        AbstractKingdomVillageElderNpcEntity elder = (AbstractKingdomVillageElderNpcEntity)elderType.method_5883((class_1937)world);
        if (elder == null) {
            return;
        }
        class_2338 spawnPos = GotVillages.findVillageBannerSpawnPos(world, village.getCenterPos());
        elder.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
        elder.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16474, null);
        elder.got$setCastle(village.getId());
        elder.setPointOfInterest(village.getCenterPos());
        String elderName = village.getElderName().isBlank() ? GotCastleOwnerNames.createFullName(kingdom, village.getCenterPos().method_10063() ^ (long)village.getId().hashCode()) : village.getElderName();
        village.setElderName(elderName);
        GotVillages.ensureVillageNpcIdentity(world, elder);
        world.method_8649((class_1297)elder);
    }

    private static void spawnVillageServiceNpc(class_3218 world, VillageInstance village, class_1299<? extends AbstractKingdomNpcEntity> entityType, int seedOffset) {
        if (entityType == null) {
            return;
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)entityType.method_5883((class_1937)world);
        if (npc == null) {
            return;
        }
        class_2338 spawnPos = GotVillages.findVillageSpawnPos(world, village.getCenterPos(), seedOffset);
        npc.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
        npc.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16474, null);
        npc.got$setCastle(village.getId());
        npc.setPointOfInterest(village.getCenterPos());
        GotVillages.ensureVillageNpcIdentity(world, npc);
        world.method_8649((class_1297)npc);
    }

    private static boolean reconcileLoadedVillagePopulationBatch(class_3218 world, List<VillageInstance> villages, int budget) {
        boolean changed = false;
        int processed = 0;
        while (processed < budget && !villages.isEmpty()) {
            if (villagePopulationCursor >= villages.size()) {
                villagePopulationCursor = 0;
            }
            VillageInstance village = villages.get(villagePopulationCursor++);
            GotVillages.ensureVillageEconomyData(world, village);
            if (!village.hasGeneratedStructures()) {
                ++processed;
                continue;
            }
            GotVillages.ensureVillageNpcProfiles(village, world.method_8390(AbstractKingdomNpcEntity.class, class_238.method_30048((class_243)class_243.method_24953((class_2382)village.getCenterPos()), (double)96.0, (double)48.0, (double)96.0), npc -> npc.method_5805() && village.getId().equals(npc.got$getCastle())));
            if (!GotVillages.isVillagePlayerLoaded(world, village) || GotVillages.findActiveVillageRaid(village.getId()) != null) {
                ++processed;
                continue;
            }
            long refreshDay = GotVillages.resolveVillageRefreshDay(village, world);
            boolean needsImmediateRefresh = GotVillages.needsImmediateVillagePopulationRefresh(world, village);
            if (!needsImmediateRefresh && refreshDay <= village.getLastPopulationRefreshDay()) {
                ++processed;
                continue;
            }
            Kingdom kingdom = GotVillages.resolvedVillageKingdom(world, village);
            if (kingdom != null) {
                GotVillages.reconcileVillagePopulation(world, village, kingdom);
            }
            village.setLastPopulationRefreshDay(Math.max(refreshDay, village.getLastPopulationRefreshDay()));
            village.setLastObservedPopulationTimeOfDay(GotVillages.normalizedTimeOfDay(world.method_8532()));
            changed = true;
            ++processed;
        }
        return changed;
    }

    private static void reconcileVillagePopulation(class_3218 world, VillageInstance village, Kingdom kingdom) {
        class_1299<? extends AbstractKingdomNpcEntity> civilianType = GotEntities.civilianTypeForKingdom(kingdom);
        class_1299<? extends AbstractKingdomVillageElderNpcEntity> elderType = GotEntities.elderTypeForKingdom(kingdom);
        class_1299<? extends AbstractKingdomBlacksmithNpcEntity> blacksmithType = GotEntities.blacksmithTypeForKingdom(kingdom);
        class_1299<? extends AbstractKingdomCartRiderNpcEntity> cartRiderType = GotEntities.cartRiderTypeForKingdom(kingdom);
        if (civilianType == null || elderType == null) {
            return;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)village.getCenterPos()), (double)96.0, (double)48.0, (double)96.0);
        int civilians = 0;
        int elders = 0;
        int blacksmiths = 0;
        int cartRiders = 0;
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && village.getId().equals(npc.got$getCastle()))) {
            if (npc2 instanceof AbstractKingdomVillageElderNpcEntity) {
                ++elders;
                continue;
            }
            if (npc2 instanceof AbstractKingdomBlacksmithNpcEntity) {
                ++blacksmiths;
                continue;
            }
            if (npc2 instanceof AbstractKingdomCartRiderNpcEntity) {
                ++cartRiders;
                continue;
            }
            if (npc2 instanceof AbstractKingdomGuardNpcEntity) continue;
            ++civilians;
        }
        for (int index = civilians; index < 4; ++index) {
            GotVillages.spawnVillageCivilian(world, village, civilianType, 101 + index);
        }
        if (elders < 1) {
            GotVillages.spawnVillageElder(world, village, kingdom, elderType);
        }
        if (blacksmiths < 1) {
            GotVillages.spawnVillageServiceNpc(world, village, blacksmithType, 241);
        }
        if (cartRiders < 1) {
            GotVillages.spawnVillageServiceNpc(world, village, cartRiderType, 283);
        }
    }

    public static void ensureVillageNpcIdentity(class_3218 world, AbstractKingdomNpcEntity npc) {
        if (world == null || npc == null || npc.isIndependentMarriageNpc() || npc.got$getCastle().isBlank()) {
            return;
        }
        VillageInstance village = GotVillages.getWorldState(world).get(npc.got$getCastle());
        if (village == null) {
            return;
        }
        if (GotVillages.ensureVillageNpcProfiles(village, List.of(npc))) {
            GotVillages.getWorldState(world).method_80();
        }
    }

    /*
     * WARNING - void declaration
     */
    private static boolean ensureVillageNpcProfiles(VillageInstance village, List<? extends AbstractKingdomNpcEntity> loadedNpcs) {
        Kingdom kingdom;
        if (village == null) {
            return false;
        }
        LinkedHashMap<String, CastleNpcProfile> profilesById = new LinkedHashMap<String, CastleNpcProfile>();
        for (CastleNpcProfile castleNpcProfile : village.getNpcProfiles()) {
            if (castleNpcProfile == null || castleNpcProfile.id().isBlank()) continue;
            profilesById.put(castleNpcProfile.id(), castleNpcProfile);
        }
        boolean changed = false;
        for (CastleNpcProfile castleNpcProfile : GotVillages.requiredVillageNpcProfiles(village)) {
            CastleNpcProfile existing = (CastleNpcProfile)profilesById.get(castleNpcProfile.id());
            if (existing == null) {
                profilesById.put(castleNpcProfile.id(), castleNpcProfile);
                changed = true;
                continue;
            }
            if (!existing.attributes().isAssigned() && castleNpcProfile.attributes().isAssigned()) {
                profilesById.put(existing.id(), new CastleNpcProfile(existing.id(), existing.uuid(), existing.roleId(), existing.seedOffset(), existing.displayName(), castleNpcProfile.attributes(), existing.appearance(), existing.nobleSpouse(), existing.spouseProfileId()));
                changed = true;
                continue;
            }
            if (existing.appearance().isAssigned() || !castleNpcProfile.appearance().isAssigned()) continue;
            profilesById.put(existing.id(), new CastleNpcProfile(existing.id(), existing.uuid(), existing.roleId(), existing.seedOffset(), existing.displayName(), existing.attributes(), castleNpcProfile.appearance(), existing.nobleSpouse(), existing.spouseProfileId()));
            changed = true;
        }
        LinkedHashMap<KingdomNpcRole, ArrayDeque> linkedHashMap = new LinkedHashMap<KingdomNpcRole, ArrayDeque>();
        for (Object profile4 : profilesById.values()) {
            linkedHashMap.computeIfAbsent(((CastleNpcProfile)profile4).role(), ignored -> new ArrayDeque()).addLast(profile4);
        }
        if (loadedNpcs != null) {
            List<AbstractKingdomNpcEntity> list = loadedNpcs.stream().filter(npc -> !npc.isIndependentMarriageNpc()).sorted(Comparator.comparingInt(class_1297::method_5628)).map(npc -> npc).toList();
            for (AbstractKingdomNpcEntity abstractKingdomNpcEntity : list) {
                void var9_21;
                void var9_24;
                ArrayDeque queue;
                CastleNpcProfile castleNpcProfile;
                String npcId = abstractKingdomNpcEntity.getCastleNpcId();
                CastleNpcProfile castleNpcProfile2 = castleNpcProfile = npcId.isBlank() ? null : (CastleNpcProfile)profilesById.get(npcId);
                if (castleNpcProfile == null) {
                    queue = (ArrayDeque)linkedHashMap.get((Object)abstractKingdomNpcEntity.getNpcRole());
                    while (queue != null && !queue.isEmpty()) {
                        CastleNpcProfile candidate = (CastleNpcProfile)queue.removeFirst();
                        if (candidate.id().isBlank()) continue;
                        CastleNpcProfile castleNpcProfile3 = candidate;
                        break;
                    }
                }
                if (var9_24 == null) continue;
                if (abstractKingdomNpcEntity.getCastleNpcId().isBlank()) {
                    abstractKingdomNpcEntity.setCastleNpcId(var9_24.id());
                    changed = true;
                }
                if (!var9_24.uuid().equals(abstractKingdomNpcEntity.method_5667())) {
                    CastleNpcProfile castleNpcProfile4 = new CastleNpcProfile(var9_24.id(), abstractKingdomNpcEntity.method_5667(), var9_24.roleId(), var9_24.seedOffset(), var9_24.displayName(), var9_24.attributes(), var9_24.appearance(), var9_24.nobleSpouse(), var9_24.spouseProfileId());
                    profilesById.put(castleNpcProfile4.id(), castleNpcProfile4);
                    changed = true;
                }
                if ((queue = (ArrayDeque)linkedHashMap.get((Object)var9_21.role())) == null) continue;
                String matchedId = var9_21.id();
                queue.removeIf(profile -> profile.id().equals(matchedId));
            }
        }
        if ((kingdom = GotVillages.resolvedVillageKingdomById(village.getKingdomId())) != null) {
            LinkedHashSet<String> usedNames = new LinkedHashSet<String>();
            for (CastleNpcProfile profile5 : profilesById.values()) {
                if (profile5.displayName().isBlank()) continue;
                usedNames.add(profile5.displayName());
            }
            for (CastleNpcProfile profile2 : new ArrayList(profilesById.values())) {
                void var9_29;
                String string = profile2.displayName();
                if (string.isBlank()) {
                    String string2 = GotCastleOwnerNames.createCommonerName(kingdom, village.getId() + "|" + profile2.id(), usedNames);
                }
                if (profile2.role() == KingdomNpcRole.ELDER && !var9_29.equals(village.getElderName())) {
                    village.setElderName((String)var9_29);
                    changed = true;
                }
                if (!var9_29.equals(profile2.displayName())) {
                    profilesById.put(profile2.id(), new CastleNpcProfile(profile2.id(), profile2.uuid(), profile2.roleId(), profile2.seedOffset(), (String)var9_29, profile2.attributes(), profile2.appearance(), profile2.nobleSpouse(), profile2.spouseProfileId()));
                    changed = true;
                }
                if (var9_29.isBlank()) continue;
                usedNames.add((String)var9_29);
            }
        }
        CharacterSelectionOption profileOption = GotVillages.characterOptionForVillage(village);
        for (CastleNpcProfile profile2 : new ArrayList(profilesById.values())) {
            CastleNpcProfile castleNpcProfile = GotVillages.ensureVillageProfileAttributes(profile2, profileOption, village.getId() + "|" + profile2.id());
            if (castleNpcProfile.equals(profile2)) continue;
            profilesById.put(castleNpcProfile.id(), castleNpcProfile);
            changed = true;
        }
        class_2960 class_29602 = class_2960.method_12829((String)village.getKingdomId());
        for (CastleNpcProfile castleNpcProfile : new ArrayList(profilesById.values())) {
            CastleNpcProfile withAppearance = GotVillages.ensureVillageProfileAppearance(castleNpcProfile, class_29602, village.getId() + "|" + castleNpcProfile.id());
            if (withAppearance.equals(castleNpcProfile)) continue;
            profilesById.put(withAppearance.id(), withAppearance);
            changed = true;
        }
        if (loadedNpcs != null) {
            for (AbstractKingdomNpcEntity abstractKingdomNpcEntity : loadedNpcs) {
                CastleNpcProfile profile7 = (CastleNpcProfile)profilesById.get(abstractKingdomNpcEntity.getCastleNpcId());
                if (profile7 == null) continue;
                if (!profile7.displayName().isBlank()) {
                    abstractKingdomNpcEntity.method_5665((class_2561)class_2561.method_43470((String)profile7.displayName()));
                }
                abstractKingdomNpcEntity.setNpcAttributes(profile7.attributes());
                abstractKingdomNpcEntity.setNpcAppearance(profile7.appearance());
            }
        }
        if (changed) {
            village.setNpcProfiles(new ArrayList<CastleNpcProfile>(profilesById.values()));
        }
        return changed;
    }

    public static void refreshVillagePopulationForMarriage(class_3218 world, String villageId) {
        if (world == null || villageId == null || villageId.isBlank()) {
            return;
        }
        VillageInstance village = GotVillages.getWorldState(world).get(villageId);
        if (village == null) {
            return;
        }
        Kingdom kingdom = GotVillages.resolvedVillageKingdom(world, village);
        if (kingdom != null) {
            GotVillages.reconcileVillagePopulation(world, village, kingdom);
        }
    }

    private static List<CastleNpcProfile> requiredVillageNpcProfiles(VillageInstance village) {
        ArrayList<CastleNpcProfile> profiles = new ArrayList<CastleNpcProfile>();
        profiles.add(GotVillages.createVillageNpcProfile(village, KingdomNpcRole.ELDER, 0, 37));
        profiles.add(GotVillages.createVillageNpcProfile(village, KingdomNpcRole.BLACKSMITH, 0, 241));
        profiles.add(GotVillages.createVillageNpcProfile(village, KingdomNpcRole.CART_RIDER, 0, 283));
        for (int index = 0; index < 4; ++index) {
            profiles.add(GotVillages.createVillageNpcProfile(village, KingdomNpcRole.CIVILIAN, index, 101 + index));
        }
        return profiles;
    }

    private static CastleNpcProfile createVillageNpcProfile(VillageInstance village, KingdomNpcRole role, int slotIndex, int seedOffset) {
        String id = village.getId() + ":npc:" + role.name().toLowerCase(Locale.ROOT) + ":" + slotIndex;
        UUID uuid = UUID.nameUUIDFromBytes((id + ":" + village.getKingdomId()).getBytes(StandardCharsets.UTF_8));
        class_2960 kingdomId = class_2960.method_12829((String)village.getKingdomId());
        return new CastleNpcProfile(id, uuid, role.name(), seedOffset, "", NpcAttributeProfile.generate(GotVillages.characterOptionForVillage(village), village.getId() + "|" + id, 0, 10), NpcAppearanceProfile.generate(kingdomId, village.getId() + "|" + id + "|appearance"), false, "");
    }

    private static void replaceVillageNpcProfile(class_3218 world, AbstractKingdomNpcEntity entity) {
        VillageInstance village = GotVillages.getWorldState(world).get(entity.got$getCastle());
        if (village == null || entity.getCastleNpcId().isBlank()) {
            return;
        }
        ArrayList<CastleNpcProfile> updated = new ArrayList<CastleNpcProfile>();
        boolean changed = false;
        for (CastleNpcProfile profile : village.getNpcProfiles()) {
            if (!entity.getCastleNpcId().equals(profile.id())) {
                updated.add(profile);
                continue;
            }
            String seedKey = village.getId() + "|" + profile.id() + "|" + world.method_8510();
            updated.add(new CastleNpcProfile(profile.id(), UUID.randomUUID(), profile.roleId(), profile.seedOffset(), "", NpcAttributeProfile.generate(GotVillages.characterOptionForVillage(village), seedKey, 0, 10), NpcAppearanceProfile.generate(class_2960.method_12829((String)village.getKingdomId()), seedKey + "|appearance"), profile.nobleSpouse(), profile.spouseProfileId()));
            changed = true;
        }
        if (!changed) {
            return;
        }
        village.setNpcProfiles(updated);
        village.setLastPopulationRefreshDay(-1L);
        GotVillages.getWorldState(world).method_80();
    }

    private static CastleNpcProfile ensureVillageProfileAttributes(CastleNpcProfile profile, CharacterSelectionOption option, String seedKey) {
        if (profile == null || profile.attributes().isAssigned()) {
            return profile;
        }
        return new CastleNpcProfile(profile.id(), profile.uuid(), profile.roleId(), profile.seedOffset(), profile.displayName(), NpcAttributeProfile.generate(option, seedKey, 0, 10), profile.appearance(), profile.nobleSpouse(), profile.spouseProfileId());
    }

    private static CastleNpcProfile ensureVillageProfileAppearance(CastleNpcProfile profile, class_2960 kingdomId, String seedKey) {
        if (profile == null || profile.appearance().isAssigned()) {
            return profile;
        }
        return new CastleNpcProfile(profile.id(), profile.uuid(), profile.roleId(), profile.seedOffset(), profile.displayName(), profile.attributes(), NpcAppearanceProfile.generate(kingdomId, seedKey + "|appearance"), profile.nobleSpouse(), profile.spouseProfileId());
    }

    private static CharacterSelectionOption characterOptionForVillage(VillageInstance village) {
        if (village == null) {
            return null;
        }
        class_2960 kingdomId = class_2960.method_12829((String)village.getKingdomId());
        return GotCharacterOptions.getByKingdomId(kingdomId);
    }

    private static Kingdom resolvedVillageKingdomById(String kingdomId) {
        class_2960 parsed = class_2960.method_12829((String)kingdomId);
        return parsed == null ? null : GotKingdoms.get(parsed);
    }

    private static class_2338 findVillageSpawnPos(class_3218 world, class_2338 center, int seedOffset) {
        class_5819 random = class_5819.method_43049((long)(center.method_10063() + (long)seedOffset));
        for (int attempt = 0; attempt < 24; ++attempt) {
            int x = center.method_10263() + random.method_39332(-8, 8);
            int z = center.method_10260() + random.method_39332(-8, 8);
            int y = world.method_8624(class_2902.class_2903.field_13203, x, z);
            class_2338 ground = new class_2338(x, y - 1, z);
            class_2338 feet = ground.method_10084();
            if (!world.method_8320(ground).method_26212((class_1922)world, ground) || !world.method_8320(feet).method_26215() || !world.method_8320(feet.method_10084()).method_26215()) continue;
            return feet;
        }
        return center.method_10084();
    }

    private static class_2338 findVillageBannerSpawnPos(class_3218 world, class_2338 center) {
        class_2338 feet = center.method_10084();
        if (world.method_8320(feet).method_26215() && world.method_8320(feet.method_10084()).method_26215()) {
            return feet;
        }
        return GotVillages.findVillageSpawnPos(world, center, 199);
    }

    private static void announceVillage(class_3222 player, VillageInstance village) {
        if (player == null || village == null) {
            return;
        }
        ModAdvancementService.send(player, "village_generated", village.getName(), "Castle: " + village.getBoundCastleName(), GotVillages.iconItemForKingdom(village.getKingdomId()), -727358, -727358);
    }

    private static class_1792 iconItemForKingdom(String kingdomId) {
        String path;
        class_2960 parsed = class_2960.method_12829((String)kingdomId);
        return switch (path = parsed == null ? "" : parsed.method_12832()) {
            case "the_north" -> GotItems.STARK_BANNER_PATTERN;
            case "the_vale" -> GotItems.ARRYN_BANNER_PATTERN;
            case "the_riverlands" -> GotItems.TULLY_BANNER_PATTERN;
            case "the_westerlands" -> GotItems.LANNISTER_BANNER_PATTERN;
            case "iron_islands" -> GotItems.GREYJOY_BANNER_PATTERN;
            case "the_reach" -> GotItems.TYRELL_PLATE;
            case "the_stormlands" -> GotItems.BARATHEON_BANNER_PATTERN;
            case "dorne" -> GotItems.MARTELL_BANNER_PATTERN;
            case "the_crownlands" -> GotItems.DRAGONSTONE_PLATE;
            default -> class_1802.field_16315;
        };
    }

    private static Kingdom resolveVillageKingdom(class_3218 world, CastleInstance castle) {
        Kingdom kingdom;
        class_2960 id = class_2960.method_12829((String)castle.getOwnerKingdomId());
        if (id != null && (kingdom = GotKingdoms.get(id)) != null) {
            return kingdom;
        }
        return GotVillages.kingdomForBiome(world, castle.getBannerPos());
    }

    private static Kingdom kingdomForBiome(class_3218 world, class_2338 pos) {
        String biomePath = world.method_23753(pos).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        return GotKingdoms.byBiomePath(biomePath);
    }

    private static class_2338 findChunkStructureOrigin(class_3218 world, class_1923 chunkPos, class_2382 templateSize) {
        int availableX = Math.max(0, 16 - templateSize.method_10263());
        int availableZ = Math.max(0, 16 - templateSize.method_10260());
        int x = chunkPos.method_8326() + availableX / 2;
        int z = chunkPos.method_8328() + availableZ / 2;
        int y = world.method_8624(class_2902.class_2903.field_13203, x + templateSize.method_10263() / 2, z + templateSize.method_10260() / 2);
        return new class_2338(x, y, z);
    }

    private static int resolveVillageStructureFloorY(class_3218 world, class_2338 center, List<VillageStructurePlan> plans) {
        ArrayList<Integer> floorYs = new ArrayList<Integer>();
        floorYs.add(world.method_8624(class_2902.class_2903.field_13203, center.method_10263(), center.method_10260()) - 1);
        for (VillageStructurePlan plan : plans) {
            if (!plan.stats().valid()) continue;
            floorYs.add(plan.stats().floorY());
        }
        floorYs.sort(Integer::compareTo);
        return (Integer)floorYs.get(floorYs.size() * 3 / 4);
    }

    private static boolean isAreaLoaded(class_3218 world, class_2338 center, int radiusChunks) {
        int centerChunkX = center.method_10263() >> 4;
        int centerChunkZ = center.method_10260() >> 4;
        for (int chunkX = centerChunkX - radiusChunks; chunkX <= centerChunkX + radiusChunks; ++chunkX) {
            for (int chunkZ = centerChunkZ - radiusChunks; chunkZ <= centerChunkZ + radiusChunks; ++chunkZ) {
                if (world.method_8393(chunkX, chunkZ)) continue;
                return false;
            }
        }
        return true;
    }

    private static SurfaceStats sampleSurface(class_3218 world, class_2338 origin, class_2382 size) {
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int totalY = 0;
        int samples = 0;
        for (int x = 0; x < size.method_10263(); x += 2) {
            for (int z = 0; z < size.method_10260(); z += 2) {
                int worldZ;
                int topY;
                int worldX = origin.method_10263() + x;
                class_2338 topPos = new class_2338(worldX, topY = world.method_8624(class_2902.class_2903.field_13203, worldX, worldZ = origin.method_10260() + z) - 1, worldZ);
                class_2680 state = world.method_8320(topPos);
                if (state.method_27852(class_2246.field_10382) || state.method_27852(class_2246.field_10164) || !state.method_26212((class_1922)world, topPos)) {
                    return SurfaceStats.invalid();
                }
                minY = Math.min(minY, topY);
                maxY = Math.max(maxY, topY);
                totalY += topY;
                ++samples;
            }
        }
        if (samples == 0 || maxY - minY > 4) {
            return SurfaceStats.invalid();
        }
        return new SurfaceStats(true, Math.round((float)totalY / (float)samples));
    }

    private static SurfaceStats sampleLooseSurface(class_3218 world, class_2338 origin, class_2382 size) {
        int totalY = 0;
        int samples = 0;
        for (int x = 0; x < size.method_10263(); x += 2) {
            for (int z = 0; z < size.method_10260(); z += 2) {
                int worldX = origin.method_10263() + x;
                int worldZ = origin.method_10260() + z;
                int topY = world.method_8624(class_2902.class_2903.field_13203, worldX, worldZ) - 1;
                totalY += topY;
                ++samples;
            }
        }
        if (samples == 0) {
            int fallbackY = world.method_8624(class_2902.class_2903.field_13203, origin.method_10263(), origin.method_10260()) - 1;
            return new SurfaceStats(true, fallbackY);
        }
        return new SurfaceStats(true, Math.round((float)totalY / (float)samples));
    }

    private static void stabilizeStructureFooting(class_3218 world, class_2338 origin, class_2382 size, int floorY) {
        for (int x = 0; x < size.method_10263(); ++x) {
            for (int z = 0; z < size.method_10260(); ++z) {
                int worldX = origin.method_10263() + x;
                int worldZ = origin.method_10260() + z;
                for (int y = floorY; y >= floorY - 6; --y) {
                    class_2338 pos = new class_2338(worldX, y, worldZ);
                    if (!world.method_8320(pos).method_26215()) continue;
                    world.method_8652(pos, class_2246.field_10566.method_9564(), 50);
                }
            }
        }
    }

    private static void prepareConnectedVillagePlatform(class_3218 world, List<VillageStructurePlan> plans, int floorY) {
        if (plans.isEmpty()) {
            return;
        }
        VillagePlatformBounds bounds = VillagePlatformBounds.from(plans).expand(4);
        int minX = bounds.minX() - 8;
        int maxX = bounds.maxX() + 8;
        int minZ = bounds.minZ() - 8;
        int maxZ = bounds.maxZ() + 8;
        for (int worldX = minX; worldX <= maxX; ++worldX) {
            for (int worldZ = minZ; worldZ <= maxZ; ++worldZ) {
                int distance = GotVillages.distanceToRect(worldX, worldZ, bounds);
                if (distance == 0) {
                    GotVillages.flattenColumnToHeight(world, worldX, worldZ, floorY);
                    continue;
                }
                if (distance > 8) continue;
                int targetY = floorY - Math.max(0, distance - 2);
                GotVillages.blendColumnToHeight(world, worldX, worldZ, targetY);
            }
        }
    }

    private static int distanceToRect(int worldX, int worldZ, VillagePlatformBounds bounds) {
        int dx = 0;
        if (worldX < bounds.minX()) {
            dx = bounds.minX() - worldX;
        } else if (worldX > bounds.maxX()) {
            dx = worldX - bounds.maxX();
        }
        int dz = 0;
        if (worldZ < bounds.minZ()) {
            dz = bounds.minZ() - worldZ;
        } else if (worldZ > bounds.maxZ()) {
            dz = worldZ - bounds.maxZ();
        }
        return Math.max(dx, dz);
    }

    private static void flattenColumnToHeight(class_3218 world, int worldX, int worldZ, int floorY) {
        class_2338 pos;
        int y;
        for (y = floorY; y >= floorY - 8; --y) {
            pos = new class_2338(worldX, y, worldZ);
            if (y == floorY) {
                world.method_8652(pos, class_2246.field_10219.method_9564(), 50);
                continue;
            }
            if (!world.method_8320(pos).method_26215()) continue;
            world.method_8652(pos, class_2246.field_10566.method_9564(), 50);
        }
        for (y = floorY + 1; y <= floorY + 8; ++y) {
            pos = new class_2338(worldX, y, worldZ);
            if (world.method_8320(pos).method_26215()) continue;
            world.method_8652(pos, class_2246.field_10124.method_9564(), 50);
        }
    }

    private static void blendColumnToHeight(class_3218 world, int worldX, int worldZ, int targetY) {
        int topY = world.method_8624(class_2902.class_2903.field_13203, worldX, worldZ) - 1;
        if (topY > targetY) {
            for (int y = topY; y > targetY; --y) {
                class_2338 pos = new class_2338(worldX, y, worldZ);
                if (world.method_8320(pos).method_26215()) continue;
                world.method_8652(pos, class_2246.field_10124.method_9564(), 50);
            }
        }
        int fillBottomY = Math.min(topY, targetY) - 4;
        for (int y = targetY; y >= fillBottomY; --y) {
            class_2338 pos = new class_2338(worldX, y, worldZ);
            if (y == targetY) {
                world.method_8652(pos, class_2246.field_10219.method_9564(), 50);
                continue;
            }
            if (!world.method_8320(pos).method_26215()) continue;
            world.method_8652(pos, class_2246.field_10566.method_9564(), 50);
        }
    }

    private static void syncVillages(class_3218 world) {
        for (class_3222 player : world.method_18456()) {
            ServerPlayNetworking.send((class_3222)player, (class_8710)GotVillages.buildVillageMapPayload(world, player));
        }
    }

    private static void updateVillageDiscovery(class_3218 world) {
        VillageWorldState state = GotVillages.getWorldState(world);
        for (class_3222 player : world.method_18456()) {
            PlayerCastleTravelHolder holder;
            VillageInstance claim = state.findClaimAt(player.method_24515());
            if (claim == null || !(holder = (PlayerCastleTravelHolder)player).got$unlockCastle(claim.getId())) continue;
            GotVillages.announceVillage(player, claim);
            ServerPlayNetworking.send((class_3222)player, (class_8710)GotVillages.buildVillageMapPayload(world, player));
            GotMainQuests.onVillageDiscovered(player, claim);
        }
    }

    private static GotCastles.VillageAnchorTarget resolveVillageAnchorTarget(class_3218 world, String castleId) {
        if (castleId == null || castleId.isBlank()) {
            return null;
        }
        for (GotCastles.VillageAnchorTarget target : GotCastles.collectVillageAnchorTargets(world)) {
            if (!castleId.equals(target.castleId())) continue;
            return target;
        }
        return null;
    }

    private static int plannedVillageStateSignature(VillageWorldState villageState) {
        int hash = 1;
        for (VillageInstance village : villageState.getAll()) {
            hash = 31 * hash + village.getId().hashCode();
            hash = 31 * hash + village.getBoundCastleId().hashCode();
            hash = 31 * hash + village.getKingdomId().hashCode();
            hash = 31 * hash + village.getCenterPos().hashCode();
            hash = 31 * hash + Boolean.hashCode(village.hasGeneratedStructures());
            hash = 31 * hash + (village.getResourceId() == null ? 0 : village.getResourceId().hashCode());
        }
        return hash;
    }

    private static int villageAnchorSignature(List<GotCastles.VillageAnchorTarget> targets) {
        int hash = 1;
        for (GotCastles.VillageAnchorTarget target : targets) {
            hash = 31 * hash + target.castleId().hashCode();
            hash = 31 * hash + target.castleName().hashCode();
            hash = 31 * hash + target.kingdomId().hashCode();
            hash = 31 * hash + target.worldX();
            hash = 31 * hash + target.worldZ();
            hash = 31 * hash + Boolean.hashCode(target.generated());
        }
        return hash;
    }

    private static CachedLandmass getLandmass() {
        BufferedImage image = VoyageMapResources.worldMapImage();
        Set<Integer> waterColors = VoyageMapResources.waterMapColors();
        int signature = System.identityHashCode(image) ^ waterColors.hashCode();
        if (cachedLandmass != null && cachedLandmass.signature() == signature) {
            return cachedLandmass;
        }
        cachedLandmass = CachedLandmass.build(image, waterColors, signature);
        return cachedLandmass;
    }

    private static void visitLandNeighbor(BufferedImage image, Set<Integer> waterColors, int width, int height, int x, int z, boolean[] visited, int[] componentIds, int componentId, ArrayDeque<Integer> queue) {
        if (x < 0 || z < 0 || x >= width || z >= height) {
            return;
        }
        int index = GotVillages.indexOf(x, z, width);
        if (visited[index] || !GotVillages.isLandPixel(image, waterColors, x, z)) {
            return;
        }
        visited[index] = true;
        componentIds[index] = componentId;
        queue.addLast(index);
    }

    private static boolean isLandPixel(BufferedImage image, Set<Integer> waterColors, int x, int z) {
        int argb = image.getRGB(x, z);
        int alpha = argb >>> 24 & 0xFF;
        if (alpha <= 0) {
            return false;
        }
        int rgb = argb & 0xFFFFFF;
        return !waterColors.contains(rgb);
    }

    private static int worldToPixel(int worldCoordinate, int imageSize) {
        double mapPixel = (double)(worldCoordinate + 48000) / 32.0;
        double scaled = mapPixel * ((double)imageSize / 3000.0);
        return class_3532.method_15340((int)((int)Math.floor(scaled)), (int)0, (int)(imageSize - 1));
    }

    private static int indexOf(int x, int z, int width) {
        return z * width + x;
    }

    private static void tickVillageAiRaids(class_3218 world) {
        VillageAiRaidWorldState state = VillageAiRaidWorldState.get(world);
        for (VillageAiRaidWorldState.VillageAiRaid raid : List.copyOf(state.getAll())) {
            CastleInstance defendingCastle;
            VillageInstance village = GotVillages.getWorldState(world).get(raid.targetVillageId());
            CastleInstance invadingCastle = CastleWorldState.get(world).get(raid.invadingCastleId());
            CastleInstance castleInstance = defendingCastle = village == null ? null : CastleWorldState.get(world).get(village.getBoundCastleId());
            if (village == null || invadingCastle == null || defendingCastle == null) {
                GotVillages.endVillageAiRaid(world, raid.targetVillageId(), false);
                continue;
            }
            if (world.method_8510() < raid.warmupEndsAtTick()) continue;
            GotVillages.resolveVillageAiRaid(world, raid, village, invadingCastle, defendingCastle);
        }
    }

    private static void scheduleVillageAiRaids(class_3218 world) {
        if (world == null || world.method_8510() <= 0L) {
            return;
        }
        ArrayList<CastleInstance> invadingCastles = new ArrayList<CastleInstance>();
        for (CastleInstance castle : CastleWorldState.get(world).getAll()) {
            if (!GotVillages.isEligibleAiVillageInvader(world, castle)) continue;
            invadingCastles.add(castle);
        }
        invadingCastles.sort(Comparator.comparing(CastleInstance::getId));
        for (CastleInstance invadingCastle : invadingCastles) {
            VillageInstance targetVillage;
            if (!GotVillages.shouldRollVillageAiRaid(world, invadingCastle) || world.field_9229.method_43048(5) != 0 || (targetVillage = GotVillages.findVillageAiTarget(world, invadingCastle)) == null || !GotVillages.startVillageAiRaid(world, invadingCastle, targetVillage)) continue;
            return;
        }
    }

    private static boolean shouldRollVillageAiRaid(class_3218 world, CastleInstance invadingCastle) {
        if (world == null || invadingCastle == null) {
            return false;
        }
        long window = Math.max(20L, 36000L);
        long offset = Math.floorMod((long)invadingCastle.getId().hashCode(), window);
        return world.method_8510() % window == offset;
    }

    private static boolean isEligibleAiVillageInvader(class_3218 world, CastleInstance castle) {
        return castle != null && castle.getOwnerPlayerUuid() == null && !castle.getOwnerKingdomId().isBlank() && !castle.getLordName().isBlank() && !GotCastles.isCastleUnderAnyInvasion(world, castle.getId());
    }

    @Nullable
    private static VillageInstance findVillageAiTarget(class_3218 world, CastleInstance invadingCastle) {
        ArrayList<VillageInstance> candidates = new ArrayList<VillageInstance>();
        for (VillageInstance village : GotVillages.getWorldState(world).getAll()) {
            CastleInstance targetCastle;
            if (!GotVillages.canStartVillageAiRaid(world, invadingCastle, village, targetCastle = CastleWorldState.get(world).get(village.getBoundCastleId()))) continue;
            candidates.add(village);
        }
        if (candidates.isEmpty()) {
            return null;
        }
        return (VillageInstance)candidates.get(world.field_9229.method_43048(candidates.size()));
    }

    private static boolean startVillageAiRaid(class_3218 world, CastleInstance invadingCastle, VillageInstance targetVillage) {
        if (world == null || invadingCastle == null || targetVillage == null) {
            return false;
        }
        CastleInstance defendingCastle = CastleWorldState.get(world).get(targetVillage.getBoundCastleId());
        if (!GotVillages.canStartVillageAiRaid(world, invadingCastle, targetVillage, defendingCastle)) {
            return false;
        }
        String attackerLordName = invadingCastle.getLordName().isBlank() ? "Unknown Lord" : invadingCastle.getLordName();
        VillageAiRaidWorldState.VillageAiRaid raid = new VillageAiRaidWorldState.VillageAiRaid(invadingCastle.getId(), targetVillage.getId(), attackerLordName, GotVillages.normalizeKingdomId(invadingCastle.getOwnerKingdomId()), GotVillages.normalizeKingdomId(GotVillages.resolvedVillageKingdomId(world, targetVillage)), 1200L, world.method_8510() + 1200L);
        VillageAiRaidWorldState.get(world).put(raid);
        targetVillage.setRaidEndsAtTick(raid.warmupEndsAtTick());
        targetVillage.setRaidCooldownEndsAtTick(raid.warmupEndsAtTick());
        GotVillages.getWorldState(world).method_80();
        GotVillages.appendVillageAiRaidAttemptLog(world, raid, targetVillage);
        GotVillages.syncVillages(world);
        return true;
    }

    private static boolean canStartVillageAiRaid(class_3218 world, CastleInstance invadingCastle, VillageInstance targetVillage, CastleInstance targetCastle) {
        if (world == null || invadingCastle == null || targetVillage == null || targetCastle == null) {
            return false;
        }
        if (invadingCastle.getId().equals(targetCastle.getId())) {
            return false;
        }
        if (invadingCastle.getOwnerPlayerUuid() != null || targetCastle.getOwnerPlayerUuid() != null) {
            return false;
        }
        if (GotCastles.isCastleUnderAnyInvasion(world, invadingCastle.getId()) || GotCastles.isCastleUnderAnyInvasion(world, targetCastle.getId())) {
            return false;
        }
        if (GotVillages.findActiveVillageRaid(targetVillage.getId()) != null || VillageAiRaidWorldState.get(world).get(targetVillage.getId()) != null) {
            return false;
        }
        long remainingCooldown = Math.max(0L, targetVillage.getRaidCooldownEndsAtTick() - world.method_8510());
        if (remainingCooldown > 0L) {
            return false;
        }
        String attackerKingdomId = GotVillages.normalizeKingdomId(invadingCastle.getOwnerKingdomId());
        String defenderKingdomId = GotVillages.normalizeKingdomId(GotVillages.resolvedVillageKingdomId(world, targetVillage));
        if (attackerKingdomId.isBlank() || defenderKingdomId.isBlank() || attackerKingdomId.equals(defenderKingdomId)) {
            return false;
        }
        return KingdomWarService.isAtWar(world, attackerKingdomId, defenderKingdomId);
    }

    private static void resolveVillageAiRaid(class_3218 world, VillageAiRaidWorldState.VillageAiRaid raid, VillageInstance village, CastleInstance invadingCastle, CastleInstance defendingCastle) {
        double successChance = GotVillages.attackerChanceForVillageAiRaid(defendingCastle.getTier(), invadingCastle.getTier());
        if (world.field_9229.method_43058() < successChance) {
            GotVillages.completeVillageAiRaid(world, raid, village, invadingCastle);
        } else {
            GotVillages.failVillageAiRaid(world, raid, village);
        }
    }

    private static double attackerChanceForVillageAiRaid(CastleTier defenderTier, CastleTier attackerTier) {
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

    private static void completeVillageAiRaid(class_3218 world, VillageAiRaidWorldState.VillageAiRaid raid, VillageInstance village, CastleInstance invadingCastle) {
        int movedLoot;
        VillageStockService.synchronizeVillage(village, world);
        int requestedLoot = village.getStockAmount() / 4;
        int n = movedLoot = requestedLoot <= 0 ? 0 : GotVillages.transferVillageTaxToCastle(world, village, invadingCastle, invadingCastle.getId(), requestedLoot);
        if (movedLoot > 0) {
            VillageStockService.consumeStock(world, village, movedLoot);
        }
        VillageStockService.blockNextDayProduction(village, world.method_8510());
        GotVillages.appendVillageAiRaidOutcomeLog(world, raid, village, true);
        GotVillages.endVillageAiRaid(world, village.getId(), true);
    }

    private static void failVillageAiRaid(class_3218 world, VillageAiRaidWorldState.VillageAiRaid raid, VillageInstance village) {
        GotVillages.appendVillageAiRaidOutcomeLog(world, raid, village, false);
        GotVillages.endVillageAiRaid(world, village.getId(), true);
    }

    private static void endVillageAiRaid(class_3218 world, String villageId, boolean startCooldown) {
        if (world == null || villageId == null || villageId.isBlank()) {
            return;
        }
        VillageAiRaidWorldState.get(world).remove(villageId);
        VillageInstance village = GotVillages.getWorldState(world).get(villageId);
        if (village != null) {
            village.setRaidEndsAtTick(0L);
            village.setRaidCooldownEndsAtTick(startCooldown ? world.method_8510() + 3000L : 0L);
        }
        GotVillages.getWorldState(world).method_80();
        GotVillages.syncVillages(world);
    }

    private static void tickActiveVillageRaids(class_3218 world) {
        long worldTime = world.method_8510();
        for (ActiveVillageRaid raid : List.copyOf(ACTIVE_VILLAGE_RAIDS.values())) {
            if (!world.method_27983().equals(raid.dimension())) continue;
            class_3222 player = world.method_8503().method_3760().method_14602(raid.playerUuid());
            VillageInstance village = GotVillages.getWorldState(world).get(raid.villageId());
            if (player == null || !player.method_5805() || player.method_37908() != world || village == null) {
                GotVillages.failVillageRaid(world, raid, true);
                continue;
            }
            long remainingTicks = Math.max(0L, raid.endsAtTick() - worldTime);
            raid.updateBossBar(remainingTicks);
            if (worldTime < raid.endsAtTick()) continue;
            GotVillages.failVillageRaid(world, raid, true);
        }
        boolean changed = false;
        for (VillageInstance village : GotVillages.getWorldState(world).getAll()) {
            if (GotVillages.findActiveVillageRaid(village.getId()) != null || village.getRaidEndsAtTick() <= 0L || worldTime < village.getRaidEndsAtTick()) continue;
            village.setRaidEndsAtTick(0L);
            changed = true;
        }
        if (changed) {
            GotVillages.syncVillages(world);
        }
    }

    private static boolean canStartVillageRaid(class_3222 player, class_3218 world, VillageInstance village) {
        if (!GotCastles.getActiveInvasionCastleId(player.method_5667()).isBlank()) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Finish your current invasion first.").method_27692(class_124.field_1061), true);
            return false;
        }
        if (ACTIVE_VILLAGE_RAIDS.containsKey(player.method_5667())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Finish your current village raid first.").method_27692(class_124.field_1061), true);
            return false;
        }
        if (GotVillages.findActiveVillageRaid(village.getId()) != null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This village is already under raid.").method_27692(class_124.field_1061), true);
            return false;
        }
        if (GotVillages.villageCooldownSeconds(world, village) > 0) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This village was attacked recently.").method_27692(class_124.field_1061), true);
            return false;
        }
        ReputationTitle title = ((PlayerReputationHolder)player).got$getReputationProfile().getTitle();
        if (!title.isAtLeast(ReputationTitle.SOLDIER)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You need at least the Soldier title to raid villages.").method_27692(class_124.field_1061), true);
            return false;
        }
        String playerKingdomId = ((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedKingdomId();
        String villageKingdomId = GotVillages.resolvedVillageKingdomId(world, village);
        if (playerKingdomId.isBlank() || playerKingdomId.equals(villageKingdomId)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You cannot raid a village of your own kingdom.").method_27692(class_124.field_1061), true);
            return false;
        }
        return true;
    }

    private static void alertVillageToPlayer(class_3218 world, VillageInstance village, class_3222 player, long expiresAtTick) {
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)village.getCenterPos()), (double)96.0, (double)48.0, (double)96.0);
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && village.getId().equals(npc.got$getCastle()))) {
            npc2.alertAgainst(player, expiresAtTick);
        }
    }

    private static void spawnVillageRaidGuards(class_3218 world, VillageInstance village, class_3222 player, long expiresAtTick, Kingdom kingdom) {
        class_1299<? extends AbstractKingdomGuardNpcEntity> guardType = GotEntities.guardTypeForKingdom(kingdom);
        if (guardType == null) {
            return;
        }
        for (int index = 0; index < 4; ++index) {
            AbstractKingdomGuardNpcEntity guard = (AbstractKingdomGuardNpcEntity)guardType.method_5883((class_1937)world);
            if (guard == null) continue;
            class_2338 spawnPos = GotVillages.findVillageBannerSpawnPos(world, village.getCenterPos());
            guard.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
            guard.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16467, null);
            guard.got$setCastle(village.getId());
            guard.setPointOfInterest(village.getCenterPos());
            GotVillages.applyVillageGuardHouseArmor(world, village, kingdom, guard);
            guard.alertAgainst(player, expiresAtTick);
            world.method_8649((class_1297)guard);
        }
    }

    private static void applyVillageGuardHouseArmor(class_3218 world, VillageInstance village, Kingdom kingdom, AbstractKingdomGuardNpcEntity guard) {
        class_1792[] armor = GotCastles.resolveArmorForCastle(world, village.getBoundCastleId(), kingdom.getId());
        if (armor == null || armor.length < 4) {
            return;
        }
        guard.method_5673(class_1304.field_6169, new class_1799((class_1935)armor[0]));
        guard.method_5673(class_1304.field_6174, new class_1799((class_1935)armor[1]));
        guard.method_5673(class_1304.field_6172, new class_1799((class_1935)armor[2]));
        guard.method_5673(class_1304.field_6166, new class_1799((class_1935)armor[3]));
    }

    private static boolean hasLivingVillageNpcs(class_3218 world, String villageId) {
        VillageInstance village = GotVillages.getWorldState(world).get(villageId);
        if (village == null) {
            return false;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)village.getCenterPos()), (double)96.0, (double)48.0, (double)96.0);
        return !world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && villageId.equals(npc.got$getCastle())).isEmpty();
    }

    private static void completeVillageRaid(class_3218 world, ActiveVillageRaid raid) {
        class_3222 player = world.method_8503().method_3760().method_14602(raid.playerUuid());
        VillageInstance village = GotVillages.getWorldState(world).get(raid.villageId());
        if (player == null || village == null) {
            GotVillages.failVillageRaid(world, raid, true);
            return;
        }
        VillageStockService.synchronizeVillage(village, world);
        ReputationExperienceService.addExperience(player, 25);
        class_1799 reward = GotVillages.createVillageRaidReward(village);
        if (!reward.method_7960()) {
            int rewardCount = reward.method_7947();
            String rewardName = reward.method_7964().getString();
            VillageStockService.consumeStock(world, village, rewardCount);
            player.method_7270(reward);
            player.method_7353((class_2561)class_2561.method_43470((String)("Village raid complete. You seized " + rewardCount + " " + rewardName + ". This village will not produce tomorrow.")).method_27692(class_124.field_1065), false);
        } else {
            player.method_7353((class_2561)class_2561.method_43470((String)"Village raid complete. This village had no stock to seize and will not produce tomorrow.").method_27692(class_124.field_1065), false);
        }
        GotVillages.endVillageRaid(world, raid, true);
    }

    private static void failVillageRaid(class_3218 world, ActiveVillageRaid raid, boolean startCooldown) {
        class_3222 player = world.method_8503().method_3760().method_14602(raid.playerUuid());
        if (player != null) {
            ReputationExperienceService.removeExperience(player, 20);
            player.method_7353((class_2561)class_2561.method_43470((String)"Village raid failed. This village will not produce tomorrow.").method_27692(class_124.field_1061), false);
        }
        GotVillages.endVillageRaid(world, raid, startCooldown);
    }

    private static void endVillageRaid(class_3218 world, ActiveVillageRaid raid, boolean startCooldown) {
        ACTIVE_VILLAGE_RAIDS.remove(raid.playerUuid());
        raid.bossBar().method_14094();
        class_3222 player = world.method_8503().method_3760().method_14602(raid.playerUuid());
        VillageInstance village = GotVillages.getWorldState(world).get(raid.villageId());
        if (player != null) {
            ServerPlayNetworking.send((class_3222)player, (class_8710)new VillageRaidStatePayload(false, raid.villageId()));
        }
        if (village != null) {
            village.setRaidEndsAtTick(0L);
            if (!startCooldown) {
                village.setRaidCooldownEndsAtTick(0L);
            }
            if (startCooldown) {
                VillageStockService.blockNextDayProduction(village, world.method_8510());
            }
            if (village.hasGeneratedStructures()) {
                GotVillages.clearVillagePopulation(world, village.getId());
                Kingdom kingdom = GotVillages.resolvedVillageKingdom(world, village);
                if (kingdom != null) {
                    GotVillages.spawnVillagePopulation(world, village, kingdom);
                }
            }
        }
        GotVillages.syncVillages(world);
    }

    private static void clearVillagePopulation(class_3218 world, String villageId) {
        VillageInstance village = GotVillages.getWorldState(world).get(villageId);
        if (village == null) {
            return;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)village.getCenterPos()), (double)96.0, (double)48.0, (double)96.0);
        for (AbstractKingdomNpcEntity npc2 : world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> villageId.equals(npc.got$getCastle()))) {
            npc2.method_31472();
        }
    }

    private static ActiveVillageRaid findActiveVillageRaid(String villageId) {
        for (ActiveVillageRaid raid : ACTIVE_VILLAGE_RAIDS.values()) {
            if (!villageId.equals(raid.villageId())) continue;
            return raid;
        }
        return null;
    }

    private static Kingdom resolvedVillageKingdom(class_3218 world, VillageInstance village) {
        class_2960 kingdomId = class_2960.method_12829((String)GotVillages.resolvedVillageKingdomId(world, village));
        return kingdomId == null ? null : GotKingdoms.get(kingdomId);
    }

    private static String resolvedVillageKingdomId(class_3218 world, VillageInstance village) {
        CastleInstance boundCastle = GotVillages.findBoundCastle(world, village == null ? "" : village.getBoundCastleId());
        if (boundCastle != null && boundCastle.getOwnerKingdomId() != null && !boundCastle.getOwnerKingdomId().isBlank()) {
            return boundCastle.getOwnerKingdomId();
        }
        return village == null ? "" : village.getKingdomId();
    }

    private static CastleInstance findBoundCastle(class_3218 world, String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return null;
        }
        CastleWorldState state = CastleWorldState.get(world);
        CastleInstance direct = state.get(castleId);
        if (direct != null) {
            return direct;
        }
        String normalizedId = castleId.trim().toLowerCase(Locale.ROOT).replace(' ', '_').replace('-', '_');
        CastleInstance normalized = state.get(normalizedId);
        if (normalized != null) {
            return normalized;
        }
        for (CastleInstance castle : state.getAll()) {
            String currentId = castle.getId();
            if (!castleId.equalsIgnoreCase(currentId) && !normalizedId.equals(currentId.trim().toLowerCase(Locale.ROOT).replace(' ', '_').replace('-', '_'))) continue;
            return castle;
        }
        return null;
    }

    private static String formatRemainingTime(long remainingTicks) {
        int totalSeconds = Math.max(0, class_3532.method_15386((float)((float)remainingTicks / 20.0f)));
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return minutes + "m " + seconds + "s";
    }

    private static boolean isVillagePlayerLoaded(class_3218 world, VillageInstance village) {
        if (world == null || village == null) {
            return false;
        }
        class_243 center = class_243.method_24953((class_2382)village.getCenterPos());
        double maxDistance = Math.max(96.0, (double)village.getClaimRadiusChunks() * 16.0 + 48.0);
        double maxDistanceSquared = maxDistance * maxDistance;
        for (class_3222 player : world.method_18456()) {
            if (!(player.method_5707(center) <= maxDistanceSquared)) continue;
            return true;
        }
        return false;
    }

    private static boolean needsImmediateVillagePopulationRefresh(class_3218 world, VillageInstance village) {
        if (world == null || village == null || !village.hasGeneratedStructures()) {
            return false;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)village.getCenterPos()), (double)96.0, (double)48.0, (double)96.0);
        List villageNpcs = world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && village.getId().equals(npc.got$getCastle()));
        GotVillages.ensureVillageNpcProfiles(village, villageNpcs);
        int civilians = 0;
        int elders = 0;
        int blacksmiths = 0;
        int cartRiders = 0;
        for (AbstractKingdomNpcEntity npc2 : villageNpcs) {
            if (npc2 instanceof AbstractKingdomVillageElderNpcEntity) {
                ++elders;
                continue;
            }
            if (npc2 instanceof AbstractKingdomBlacksmithNpcEntity) {
                ++blacksmiths;
                continue;
            }
            if (npc2 instanceof AbstractKingdomCartRiderNpcEntity) {
                ++cartRiders;
                continue;
            }
            if (npc2 instanceof AbstractKingdomGuardNpcEntity) continue;
            ++civilians;
        }
        return civilians < 4 || elders < 1 || blacksmiths < 1 || cartRiders < 1;
    }

    private static long resolveVillageRefreshDay(VillageInstance village, class_3218 world) {
        long day = GotGameRules.regularDay(world.method_8510());
        long normalizedTimeOfDay = GotVillages.normalizedTimeOfDay(world.method_8532());
        if (village != null && GotGameRules.affectedByTimeSkip(world) && village.getLastObservedPopulationTimeOfDay() >= 0L && normalizedTimeOfDay < village.getLastObservedPopulationTimeOfDay()) {
            return Math.max(day, village.getLastPopulationRefreshDay() + 1L);
        }
        return day;
    }

    private static long normalizedTimeOfDay(long timeOfDay) {
        long normalized = timeOfDay % 24000L;
        return normalized < 0L ? normalized + 24000L : normalized;
    }

    private static void appendVillageAiRaidAttemptLog(class_3218 world, VillageAiRaidWorldState.VillageAiRaid raid, VillageInstance village) {
        String line = raid.attackerLordName() + " attempts to Raid Village " + village.getName();
        GotVillages.appendVillageKingdomEventLine(world, line, raid.attackerKingdomId(), raid.defenderKingdomId());
        GotVillages.announceVillageConflictAttempt(world, village.getName(), "Village Raid Incoming");
    }

    private static void appendVillageAiRaidOutcomeLog(class_3218 world, VillageAiRaidWorldState.VillageAiRaid raid, VillageInstance village, boolean successful) {
        String line = raid.attackerLordName() + " " + (successful ? "successfully" : "unsuccessfully") + " raided village " + village.getName();
        GotVillages.appendVillageKingdomEventLine(world, line, raid.attackerKingdomId(), raid.defenderKingdomId());
        GotVillages.announceVillageConflictResult(world, village.getName(), successful ? "Village Raid Successful" : "Village Raid Unsuccessful", successful ? raid.attackerKingdomId() : raid.defenderKingdomId(), successful ? raid.defenderKingdomId() : raid.attackerKingdomId());
    }

    private static void appendVillageKingdomEventLine(class_3218 world, String line, String ... kingdomIds) {
        LinkedHashSet<String> uniqueKingdomIds = new LinkedHashSet<String>();
        for (String kingdomId : kingdomIds) {
            String normalized = GotVillages.normalizeKingdomId(kingdomId);
            if (normalized.isBlank()) continue;
            uniqueKingdomIds.add(normalized);
        }
        for (String kingdomId : uniqueKingdomIds) {
            GotKingdomEventLogs.append(world, kingdomId, line);
        }
    }

    private static void announceVillageConflictAttempt(class_3218 world, String title, String description) {
        if (world == null) {
            return;
        }
        for (class_3222 player : world.method_18456()) {
            ModAdvancementService.send(player, "village_conflict_attempt", title, description, class_1802.field_8371, -727358, -727358);
        }
    }

    private static void announceVillageConflictResult(class_3218 world, String title, String description, String winningKingdomId, String losingKingdomId) {
        if (world == null) {
            return;
        }
        for (class_3222 player : world.method_18456()) {
            PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
            String playerKingdomId = GotVillages.normalizeKingdomId(profile.getSelectedKingdomId());
            int color = -727358;
            if (!playerKingdomId.isBlank()) {
                if (playerKingdomId.equals(GotVillages.normalizeKingdomId(winningKingdomId))) {
                    color = -10497171;
                } else if (playerKingdomId.equals(GotVillages.normalizeKingdomId(losingKingdomId))) {
                    color = -2074022;
                }
            }
            ModAdvancementService.send(player, "village_conflict_result", title, description, class_1802.field_8862, color, color);
        }
    }

    private static String normalizeKingdomId(String kingdomId) {
        if (kingdomId == null) {
            return "";
        }
        String trimmed = kingdomId.trim();
        if (trimmed.isEmpty()) {
            return "";
        }
        class_2960 parsed = class_2960.method_12829((String)trimmed);
        return parsed == null ? trimmed : parsed.toString();
    }

    private static class_1799 createVillageRaidReward(VillageInstance village) {
        if (village == null || village.getResourceId() == null) {
            return class_1799.field_8037;
        }
        int available = village.getStockAmount();
        int rewardAmount = available / 4;
        if (rewardAmount <= 0) {
            return class_1799.field_8037;
        }
        return GotVillages.createVillageOutputTransferStack(village, rewardAmount);
    }

    private static int transferVillageTaxToCastle(class_3218 world, VillageInstance village, CastleInstance castle, String castleId, int amount) {
        int inserted;
        if (world == null || village == null || amount <= 0 || castle == null && (castleId == null || castleId.isBlank())) {
            return 0;
        }
        class_1799 prototype = GotVillages.createVillageOutputTransferStack(village, amount);
        if (prototype.method_7960()) {
            return 0;
        }
        int moved = 0;
        for (int remaining = amount; remaining > 0; remaining -= inserted) {
            class_1799 chunk = prototype.method_7972();
            chunk.method_7939(Math.min(remaining, chunk.method_7914()));
            int n = inserted = castle != null ? GotVillages.insertIntoCastleStorage(world, castle, chunk) : CastleWorldState.get(world).queueGhostStorage(castleId, chunk);
            if (inserted <= 0) break;
            moved += inserted;
        }
        return moved;
    }

    private static CastleTier inferredBoundCastleTier(class_3218 world, String castleId) {
        return GotCastles.resolveCastleTier(world, castleId);
    }

    private static class_1799 createVillageOutputTransferStack(VillageInstance village, int amount) {
        class_1799 stack;
        if (village == null || village.getResourceId() == null || amount <= 0) {
            return class_1799.field_8037;
        }
        VillageResource resource = GotVillageResources.get(village.getResourceId());
        if (resource == null) {
            return class_1799.field_8037;
        }
        class_1799 class_17992 = stack = resource.isHorse() ? new class_1799((class_1935)class_1802.field_17528) : resource.getIconStack().method_7972();
        if (stack.method_7960()) {
            return class_1799.field_8037;
        }
        stack.method_7939(Math.min(amount, stack.method_7914()));
        return stack;
    }

    private static int insertIntoCastleStorage(class_3218 world, CastleInstance castle, class_1799 stack) {
        int move;
        if (world == null || castle == null || stack.method_7960()) {
            return 0;
        }
        int inserted = 0;
        for (class_1799 stored : castle.getStorageItems()) {
            int space;
            if (stack.method_7960()) break;
            if (stored.method_7960() || !class_1799.method_31577((class_1799)stored, (class_1799)stack) || (space = Math.min(stored.method_7914(), 64) - stored.method_7947()) <= 0) continue;
            move = Math.min(space, stack.method_7947());
            stored.method_7933(move);
            stack.method_7934(move);
            inserted += move;
        }
        if (!stack.method_7960()) {
            for (int slot = 0; slot < castle.getStorageItems().size(); ++slot) {
                class_1799 stored;
                stored = (class_1799)castle.getStorageItems().get(slot);
                if (!stored.method_7960()) continue;
                class_1799 movedStack = stack.method_7972();
                move = Math.min(stack.method_7947(), movedStack.method_7914());
                movedStack.method_7939(move);
                castle.getStorageItems().set(slot, (Object)movedStack);
                stack.method_7934(move);
                inserted += move;
                if (stack.method_7960()) break;
            }
        }
        if (inserted > 0) {
            CastleWorldState.get(world).method_80();
        }
        return inserted;
    }

    private record PlannedVillage(String id, String name, String boundCastleId, String boundCastleName, String kingdomId, class_2338 centerPos, boolean generated, class_2960 resourceId) {
    }

    private static final class ActiveVillageRaid {
        private final class_5321<class_1937> dimension;
        private final UUID playerUuid;
        private final String villageId;
        private final long endsAtTick;
        private final long durationTicks;
        private final class_3213 bossBar = new class_3213((class_2561)class_2561.method_43470((String)""), class_1259.class_1260.field_5784, class_1259.class_1261.field_5795);

        private ActiveVillageRaid(class_5321<class_1937> dimension, UUID playerUuid, String villageId, long endsAtTick, long durationTicks) {
            this.dimension = dimension;
            this.playerUuid = playerUuid;
            this.villageId = villageId;
            this.endsAtTick = endsAtTick;
            this.durationTicks = durationTicks;
        }

        private class_5321<class_1937> dimension() {
            return this.dimension;
        }

        private UUID playerUuid() {
            return this.playerUuid;
        }

        private String villageId() {
            return this.villageId;
        }

        private long endsAtTick() {
            return this.endsAtTick;
        }

        private class_3213 bossBar() {
            return this.bossBar;
        }

        private void updateBossBar(long remainingTicks) {
            this.bossBar.method_5413((class_2561)class_2561.method_43470((String)("Village Raid | " + GotVillages.formatRemainingTime(remainingTicks))));
            float percent = this.durationTicks <= 0L ? 0.0f : class_3532.method_15363((float)((float)remainingTicks / (float)this.durationTicks), (float)0.0f, (float)1.0f);
            this.bossBar.method_5408(percent);
        }
    }

    private record VillageStructurePlan(class_2960 structureId, class_3499 template, class_2338 origin, class_2382 size, SurfaceStats stats, boolean required) {
    }

    private record CachedPlannedVillages(int villageSignature, int targetSignature, Map<String, PlannedVillage> plannedByCastleId) {
    }

    private record WeightedVillageResource(VillageResource resource, int weight) {
    }

    private record CachedLandmass(int width, int height, int[] componentIds, int signature) {
        private static CachedLandmass build(BufferedImage image, Set<Integer> waterColors, int signature) {
            int width = image.getWidth();
            int height = image.getHeight();
            int[] componentIds = new int[width * height];
            Arrays.fill(componentIds, -1);
            boolean[] visited = new boolean[width * height];
            ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
            int nextComponentId = 0;
            for (int z = 0; z < height; ++z) {
                for (int x = 0; x < width; ++x) {
                    int index = GotVillages.indexOf(x, z, width);
                    if (visited[index] || !GotVillages.isLandPixel(image, waterColors, x, z)) continue;
                    visited[index] = true;
                    componentIds[index] = nextComponentId;
                    queue.addLast(index);
                    while (!queue.isEmpty()) {
                        int current = (Integer)queue.removeFirst();
                        int currentX = current % width;
                        int currentZ = current / width;
                        GotVillages.visitLandNeighbor(image, waterColors, width, height, currentX + 1, currentZ, visited, componentIds, nextComponentId, queue);
                        GotVillages.visitLandNeighbor(image, waterColors, width, height, currentX - 1, currentZ, visited, componentIds, nextComponentId, queue);
                        GotVillages.visitLandNeighbor(image, waterColors, width, height, currentX, currentZ + 1, visited, componentIds, nextComponentId, queue);
                        GotVillages.visitLandNeighbor(image, waterColors, width, height, currentX, currentZ - 1, visited, componentIds, nextComponentId, queue);
                    }
                    ++nextComponentId;
                }
            }
            return new CachedLandmass(width, height, componentIds, signature);
        }

        private boolean isSameLandComponent(class_2338 first, class_2338 second) {
            int firstComponent = this.componentAt(first.method_10263(), first.method_10260());
            if (firstComponent < 0) {
                return false;
            }
            return firstComponent == this.componentAt(second.method_10263(), second.method_10260());
        }

        private int componentAt(int worldX, int worldZ) {
            int pixelX = GotVillages.worldToPixel(worldX, this.width);
            int pixelZ = GotVillages.worldToPixel(worldZ, this.height);
            return this.componentIds[GotVillages.indexOf(pixelX, pixelZ, this.width)];
        }
    }

    private record PlacementCandidate(class_2338 origin, SurfaceStats stats) {
    }

    private record SurfaceStats(boolean valid, int floorY) {
        private static SurfaceStats invalid() {
            return new SurfaceStats(false, 0);
        }
    }

    private record VillagePlatformBounds(int minX, int maxX, int minZ, int maxZ) {
        private static VillagePlatformBounds from(List<VillageStructurePlan> plans) {
            int minX = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int minZ = Integer.MAX_VALUE;
            int maxZ = Integer.MIN_VALUE;
            for (VillageStructurePlan plan : plans) {
                minX = Math.min(minX, plan.origin().method_10263());
                maxX = Math.max(maxX, plan.origin().method_10263() + plan.size().method_10263() - 1);
                minZ = Math.min(minZ, plan.origin().method_10260());
                maxZ = Math.max(maxZ, plan.origin().method_10260() + plan.size().method_10260() - 1);
            }
            return new VillagePlatformBounds(minX, maxX, minZ, maxZ);
        }

        private VillagePlatformBounds expand(int margin) {
            return new VillagePlatformBounds(this.minX - margin, this.maxX + margin, this.minZ - margin, this.maxZ + margin);
        }
    }

    private record StructurePlacement(class_1923 chunkPos, String structurePath) {
    }
}

