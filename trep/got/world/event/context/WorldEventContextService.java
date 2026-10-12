/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1496
 *  net.minecraft.class_1923
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_5321
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.world.event.context;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import net.minecraft.class_1297;
import net.minecraft.class_1496;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5321;
import net.minecraft.server.MinecraftServer;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleSubtype;
import trep.got.castle.CastleWorldState;
import trep.got.entity.AbstractTieredHorseEntity;
import trep.got.entity.ShipEntity;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationProfile;
import trep.got.quest.PlayerQuestHolder;
import trep.got.quest.PlayerQuestProfile;
import trep.got.quest.QuestInstance;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.PlayerSkillProfile;
import trep.got.skill.SkillAttributeType;
import trep.got.voyage.VoyageHarbourNode;
import trep.got.voyage.VoyageMapResources;
import trep.got.voyage.VoyageNetwork;
import trep.got.voyage.VoyageNetworkBuilder;
import trep.got.voyage.VoyageWorldState;
import trep.got.world.event.context.WorldEventClaimContext;
import trep.got.world.event.context.WorldEventClaimType;
import trep.got.world.event.context.WorldEventMapContext;
import trep.got.world.event.context.WorldEventPlayerSnapshot;
import trep.got.world.event.context.WorldEventProgressContext;
import trep.got.world.event.context.WorldEventRouteContext;
import trep.got.world.event.context.WorldEventTravelContext;
import trep.got.world.event.context.WorldEventVitalsContext;
import trep.got.world.map.RoadMapHelper;

public final class WorldEventContextService {
    private static final String ROAD_MAP_PATH = "assets/agob/map_road.png";
    private static final int ROAD_COLOR = 13154156;
    private static final int ROAD_SCAN_RADIUS_PIXELS = 4;
    private static final int ROAD_BLOCK_THRESHOLD = 64;
    private static final int HARBOUR_NEAR_THRESHOLD_BLOCKS = 256;
    private static final Set<String> CAPITAL_CASTLES = Set.of("winterfell", "eyrie", "riverrun", "casterly_rock", "pyke", "highgarden", "storm_s_end", "sunspear", "king_s_landing");
    private static BufferedImage roadMapImage;
    private static VoyageNetwork cachedVoyageNetwork;
    private static int cachedHarbourCount;
    private static final Map<class_5321<class_1937>, Map<Long, CachedHarbourDistance>> HARBOUR_DISTANCE_CACHE;

    private WorldEventContextService() {
    }

    public static WorldEventPlayerSnapshot capture(class_3222 player, MinecraftServer server, long worldTick) {
        class_3218 world = player.method_51469();
        class_2338 pos = player.method_24515();
        WorldEventMapContext map = WorldEventContextService.captureMapContext(world, pos);
        WorldEventClaimContext claim = WorldEventContextService.captureClaimContext(world, pos);
        WorldEventRouteContext route = WorldEventContextService.captureRouteContext(world, pos, claim);
        WorldEventTravelContext travel = WorldEventContextService.captureTravelContext(player);
        WorldEventVitalsContext vitals = new WorldEventVitalsContext(player.method_6032(), player.method_6063(), player.method_7344().method_7586(), player.method_7344().method_7589());
        WorldEventProgressContext progress = WorldEventContextService.captureProgressContext(player, world);
        String biomeId = world.method_23753(pos).method_40230().map(key -> key.method_29177().toString()).orElse("minecraft:unknown");
        return new WorldEventPlayerSnapshot(player.method_5667(), player.method_7334().getName(), worldTick, biomeId, map, claim, WorldEventContextService.refineRouteContext(route, map), travel, vitals, progress);
    }

    private static WorldEventMapContext captureMapContext(class_3218 world, class_2338 pos) {
        int pixelX = WorldEventContextService.worldToMapPixel(pos.method_10263());
        int pixelZ = WorldEventContextService.worldToMapPixel(pos.method_10260());
        boolean insideKnownMap = pixelX >= 0 && pixelZ >= 0 && pixelX < 3000 && pixelZ < 3000;
        return new WorldEventMapContext(world.method_27983().method_29177().toString(), pos.method_10263(), pos.method_10264(), pos.method_10260(), pixelX, pixelZ, insideKnownMap);
    }

    private static WorldEventClaimContext captureClaimContext(class_3218 world, class_2338 pos) {
        CastleInstance claim = CastleWorldState.get(world).findClaimAt(pos);
        if (claim == null) {
            return WorldEventClaimContext.NONE;
        }
        WorldEventClaimType claimType = WorldEventContextService.resolveClaimType(claim);
        return new WorldEventClaimContext(true, claim.getId(), claim.getTitle().displayName(), claimType, claim.hasSubtype(CastleSubtype.HARBOUR));
    }

    private static WorldEventRouteContext captureRouteContext(class_3218 world, class_2338 pos, WorldEventClaimContext claim) {
        String nearestHarbourId;
        VoyageNetwork voyageNetwork = WorldEventContextService.getVoyageNetwork(world);
        CachedHarbourDistance cached = WorldEventContextService.cachedNearestHarbour(world, pos, voyageNetwork);
        String string = claim.harbourCastle() ? claim.castleId() : (nearestHarbourId = cached == null ? "" : cached.harbourId());
        int harbourDistance = claim.harbourCastle() ? 0 : (cached == null ? Integer.MAX_VALUE : cached.distanceBlocks());
        return new WorldEventRouteContext(false, Integer.MAX_VALUE, harbourDistance <= 256, nearestHarbourId, harbourDistance, voyageNetwork.nodesById().containsKey(nearestHarbourId));
    }

    private static WorldEventRouteContext refineRouteContext(WorldEventRouteContext base, WorldEventMapContext map) {
        int roadDistance = map.insideKnownMap() ? WorldEventContextService.distanceToRoadBlocks(map.mapPixelX(), map.mapPixelZ()) : Integer.MAX_VALUE;
        boolean onRoad = roadDistance <= 64;
        return new WorldEventRouteContext(onRoad, roadDistance, base.nearHarbour(), base.nearestHarbourId(), base.harbourDistanceBlocks(), base.seaRouteNode());
    }

    private static WorldEventTravelContext captureTravelContext(class_3222 player) {
        class_1297 vehicle = player.method_5854();
        if (vehicle instanceof ShipEntity) {
            ShipEntity ship = (ShipEntity)vehicle;
            return new WorldEventTravelContext(ship.isVoyageMode() ? "voyage_vessel" : "vessel", false, "", true, ship.getVesselVariant().id());
        }
        if (vehicle instanceof AbstractTieredHorseEntity) {
            AbstractTieredHorseEntity horse = (AbstractTieredHorseEntity)vehicle;
            return new WorldEventTravelContext("mounted", true, horse.method_5864().method_35050(), false, "");
        }
        if (vehicle instanceof class_1496) {
            class_1496 horse = (class_1496)vehicle;
            return new WorldEventTravelContext("mounted", true, horse.method_5864().method_35050(), false, "");
        }
        return new WorldEventTravelContext("on_foot", false, "", false, "");
    }

    private static WorldEventProgressContext captureProgressContext(class_3222 player, class_3218 world) {
        PlayerSkillProfile skillProfile = ((PlayerSkillHolder)player).got$getSkillProfile();
        LinkedHashMap<String, Integer> skillPoints = new LinkedHashMap<String, Integer>();
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            skillPoints.put(type.getId(), skillProfile.get(type).getPoints());
        }
        ReputationProfile reputation = ((PlayerReputationHolder)player).got$getReputationProfile();
        PlayerQuestProfile quests = ((PlayerQuestHolder)player).got$getQuestProfile();
        QuestInstance activeLordLetterQuest = quests.getActiveQuests().stream().filter(quest -> "lord_letter_delivery".equals(quest.templateId())).filter(quest -> !quests.hasRobberAmbushTriggered(quest.id())).sorted(Comparator.comparingLong(QuestInstance::acceptedAtTick)).findFirst().orElse(null);
        boolean onVoyage = VoyageWorldState.get(world).sessions().containsKey(player.method_5667());
        return new WorldEventProgressContext(skillPoints, reputation.getTitle().getId(), reputation.getExperience(), quests.activeQuestCount() > 0, quests.activeQuestCount(), onVoyage, activeLordLetterQuest == null ? "" : activeLordLetterQuest.id(), activeLordLetterQuest == null ? "" : activeLordLetterQuest.targetNpcName(), activeLordLetterQuest == null ? 0L : activeLordLetterQuest.acceptedAtTick(), activeLordLetterQuest != null && quests.hasRobberAmbushTriggered(activeLordLetterQuest.id()));
    }

    private static WorldEventClaimType resolveClaimType(CastleInstance claim) {
        if (claim.getOwnerPlayerUuid() != null && !claim.isNaturalClaim()) {
            return WorldEventClaimType.PLAYER_SETTLEMENT;
        }
        if (CAPITAL_CASTLES.contains(claim.getId())) {
            return WorldEventClaimType.CAPITAL;
        }
        String definitionId = claim.getDefinitionId().toLowerCase(Locale.ROOT);
        if (definitionId.contains("lordship")) {
            return WorldEventClaimType.LORDSHIP;
        }
        return WorldEventClaimType.SETTLEMENT;
    }

    private static int worldToMapPixel(int worldCoordinate) {
        return Math.floorDiv(worldCoordinate + 48000, 32);
    }

    private static int distanceToRoadBlocks(int pixelX, int pixelZ) {
        BufferedImage image = WorldEventContextService.roadMap();
        if (pixelX < 0 || pixelZ < 0 || pixelX >= image.getWidth() || pixelZ >= image.getHeight()) {
            return Integer.MAX_VALUE;
        }
        if (WorldEventContextService.isRoadPixel(image, pixelX, pixelZ)) {
            return 0;
        }
        int bestPixelDistance = Integer.MAX_VALUE;
        for (int offsetZ = -4; offsetZ <= 4; ++offsetZ) {
            for (int offsetX = -4; offsetX <= 4; ++offsetX) {
                int sampleX = pixelX + offsetX;
                int sampleZ = pixelZ + offsetZ;
                if (sampleX < 0 || sampleZ < 0 || sampleX >= image.getWidth() || sampleZ >= image.getHeight() || !WorldEventContextService.isRoadPixel(image, sampleX, sampleZ)) continue;
                bestPixelDistance = Math.min(bestPixelDistance, Math.abs(offsetX) + Math.abs(offsetZ));
            }
        }
        if (bestPixelDistance == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return bestPixelDistance * 32;
    }

    private static boolean isRoadPixel(BufferedImage image, int pixelX, int pixelZ) {
        return RoadMapHelper.isRoadAtPixelPosition(image, (double)pixelX + 0.5, (double)pixelZ + 0.5, 13154156, 0.24);
    }

    private static BufferedImage roadMap() {
        BufferedImage bufferedImage;
        block10: {
            if (roadMapImage != null) {
                return roadMapImage;
            }
            InputStream stream = WorldEventContextService.class.getClassLoader().getResourceAsStream(ROAD_MAP_PATH);
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing road map resource assets/agob/map_road.png");
                }
                bufferedImage = roadMapImage = ImageIO.read(stream);
                if (stream == null) break block10;
            }
            catch (Throwable throwable) {
                try {
                    if (stream != null) {
                        try {
                            stream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (IOException e) {
                    throw new IllegalStateException("Failed to load road map resource assets/agob/map_road.png", e);
                }
            }
            stream.close();
        }
        return bufferedImage;
    }

    private static VoyageNetwork getVoyageNetwork(class_3218 world) {
        ArrayList<VoyageHarbourNode> harbours = new ArrayList<VoyageHarbourNode>();
        for (CastleInstance castle : CastleWorldState.get(world).getAll()) {
            if (!castle.hasSubtype(CastleSubtype.HARBOUR)) continue;
            harbours.add(new VoyageHarbourNode(castle.getId(), castle.getTitle().displayName(), castle.getBannerPos().method_10263(), castle.getBannerPos().method_10260()));
        }
        if (cachedVoyageNetwork != null && cachedHarbourCount == harbours.size()) {
            return cachedVoyageNetwork;
        }
        HARBOUR_DISTANCE_CACHE.clear();
        cachedHarbourCount = harbours.size();
        cachedVoyageNetwork = VoyageNetworkBuilder.build(VoyageMapResources.worldMapImage(), VoyageMapResources.waterMapColors(), harbours);
        return cachedVoyageNetwork;
    }

    private static CachedHarbourDistance cachedNearestHarbour(class_3218 world, class_2338 pos, VoyageNetwork voyageNetwork) {
        long chunkKey = class_1923.method_8331((int)(pos.method_10263() >> 4), (int)(pos.method_10260() >> 4));
        Map worldCache = HARBOUR_DISTANCE_CACHE.computeIfAbsent((class_5321<class_1937>)world.method_27983(), ignored -> new HashMap());
        CachedHarbourDistance cached = (CachedHarbourDistance)worldCache.get(chunkKey);
        if (cached != null) {
            return cached;
        }
        VoyageHarbourNode nearestHarbour = null;
        double nearestHarbourSquaredDistance = Double.POSITIVE_INFINITY;
        for (VoyageHarbourNode node : voyageNetwork.nodesById().values()) {
            double squaredDistance = node.worldPos().method_40081((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260());
            if (!(squaredDistance < nearestHarbourSquaredDistance)) continue;
            nearestHarbourSquaredDistance = squaredDistance;
            nearestHarbour = node;
        }
        CachedHarbourDistance resolved = nearestHarbour == null ? new CachedHarbourDistance("", Integer.MAX_VALUE) : new CachedHarbourDistance(nearestHarbour.id(), (int)Math.sqrt(nearestHarbourSquaredDistance));
        worldCache.put(chunkKey, resolved);
        return resolved;
    }

    static {
        cachedHarbourCount = -1;
        HARBOUR_DISTANCE_CACHE = new HashMap<class_5321<class_1937>, Map<Long, CachedHarbourDistance>>();
    }

    private record CachedHarbourDistance(String harbourId, int distanceBlocks) {
    }
}

