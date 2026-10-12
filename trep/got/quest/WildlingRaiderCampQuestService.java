/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1923
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2680
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_5321
 *  net.minecraft.class_7924
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.quest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2902;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import trep.got.block.TentCollisionBlock;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.TentEntity;
import trep.got.entity.WildlingRaiderNpcEntity;
import trep.got.network.BanditCampNegotiationResultPayload;
import trep.got.network.OpenNpcDialoguePayload;
import trep.got.quest.PlayerQuestHolder;
import trep.got.quest.PlayerQuestProfile;
import trep.got.quest.QuestInstance;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotEntities;
import trep.got.registry.GotQuests;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeType;
import trep.got.world.biome.GotBiomeKeys;

public final class WildlingRaiderCampQuestService {
    public static final String OBJECTIVE_COMPLETE_KEY = "objective_complete";
    private static final int MIN_RAIDERS = 6;
    private static final int MAX_RAIDERS = 10;
    private static final double PROXIMITY_RANGE = 16.0;
    private static final TentCollisionBlock TENT_BLOCK = (TentCollisionBlock)GotBlocks.TENT_COLLISION;
    private static final Map<UUID, Long> DIALOGUE_COOLDOWNS = new HashMap<UUID, Long>();

    private WildlingRaiderCampQuestService() {
    }

    public static void initialize() {
        ServerPlayerEvents.AFTER_RESPAWN.register(WildlingRaiderCampQuestService::handleRespawn);
    }

    public static void checkPlayerProximity(WildlingRaiderNpcEntity leader, class_3222 player) {
        if (!leader.isQuestCampLeader() || !leader.isAwaitingQuestCampDecision()) {
            return;
        }
        if (leader.method_5858((class_1297)player) > 256.0) {
            return;
        }
        long currentTime = leader.method_37908().method_8510();
        if (DIALOGUE_COOLDOWNS.getOrDefault(player.method_5667(), 0L) > currentTime) {
            return;
        }
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        QuestInstance quest = profile.findById(leader.getQuestCampQuestId());
        if (quest != null && !WildlingRaiderCampQuestService.canComplete(quest) && !leader.isInDialogueScene()) {
            if (leader.forceBeginDialogue(player)) {
                DIALOGUE_COOLDOWNS.put(player.method_5667(), currentTime + 80L);
                ServerPlayNetworking.send((class_3222)player, (class_8710)new OpenNpcDialoguePayload(leader.method_5628()));
            } else {
                DIALOGUE_COOLDOWNS.put(player.method_5667(), currentTime + 40L);
            }
        }
    }

    public static void onRaiderKilled(WildlingRaiderNpcEntity killedRaider) {
        class_238 searchArea;
        boolean survivors;
        String questId = killedRaider.getQuestCampQuestId();
        if (killedRaider.method_37908().method_8608() || questId.isBlank()) {
            return;
        }
        class_3218 world = (class_3218)killedRaider.method_37908();
        boolean bl = survivors = !world.method_8390(WildlingRaiderNpcEntity.class, searchArea = killedRaider.method_5829().method_1014(32.0), raider -> raider.method_5805() && questId.equals(raider.getQuestCampQuestId())).isEmpty();
        if (!survivors) {
            world.method_18766(player -> ((PlayerQuestHolder)player).got$getQuestProfile().findById(questId) != null).forEach(player -> WildlingRaiderCampQuestService.markObjectiveComplete(player, ((PlayerQuestHolder)player).got$getQuestProfile().findById(questId), world));
        }
    }

    public static QuestInstance activateQuest(class_3222 player, AbstractKingdomNpcEntity npc, QuestInstance quest) {
        if (player == null || npc == null || quest == null || !"destroy_wildling_raider_camp".equals(quest.templateId())) {
            return null;
        }
        class_1937 class_19372 = player.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return null;
        }
        class_3218 world = (class_3218)class_19372;
        CastleInstance castle = CastleWorldState.get(world).get(npc.got$getCastle());
        if (castle == null) {
            return null;
        }
        CampPlacement placement = WildlingRaiderCampQuestService.findPlacement(world, castle);
        if (placement == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"No valid wildling raider camp site found in the Gift.").method_27692(class_124.field_1061), true);
            return null;
        }
        TentEntity tent = WildlingRaiderCampQuestService.spawnTent(world, placement.origin(), placement.facing(), quest.id());
        if (tent == null) {
            return null;
        }
        int raiderCount = 6 + Math.floorMod(quest.id().hashCode(), 5);
        WildlingRaiderCampQuestService.spawnRaiders(world, placement, quest.id(), raiderCount);
        class_2487 metadata = quest.metadata().method_10553();
        metadata.method_10569("camp_chunk_x", placement.chunk().field_9181);
        metadata.method_10569("camp_chunk_z", placement.chunk().field_9180);
        metadata.method_10569("camp_origin_x", placement.origin().method_10263());
        metadata.method_10569("camp_origin_y", placement.origin().method_10264());
        metadata.method_10569("camp_origin_z", placement.origin().method_10260());
        metadata.method_10582("camp_world", world.method_27983().method_29177().toString());
        metadata.method_10556(OBJECTIVE_COMPLETE_KEY, false);
        return quest.withMetadata(metadata);
    }

    public static boolean canComplete(QuestInstance quest) {
        return quest != null && quest.metadata().method_10577(OBJECTIVE_COMPLETE_KEY);
    }

    public static void onQuestCompleted(class_3222 player, QuestInstance quest) {
        class_3218 world = WildlingRaiderCampQuestService.resolveQuestWorld(player.method_5682(), quest);
        if (world != null) {
            WildlingRaiderCampQuestService.discardTent(world, quest);
            WildlingRaiderCampQuestService.discardQuestRaiders(world, quest);
        }
    }

    public static void onQuestFailed(class_3222 player, QuestInstance quest) {
        class_3218 world = WildlingRaiderCampQuestService.resolveQuestWorld(player.method_5682(), quest);
        if (world != null) {
            WildlingRaiderCampQuestService.discardQuestRaiders(world, quest);
        }
    }

    public static void handleDecision(class_3222 player, int targetEntityId, boolean negotiate) {
        WildlingRaiderNpcEntity leader;
        class_1937 class_19372;
        if (player == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        class_1297 entity = world.method_8469(targetEntityId);
        if (!(entity instanceof WildlingRaiderNpcEntity) || !(leader = (WildlingRaiderNpcEntity)entity).isQuestCampLeader()) {
            return;
        }
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        QuestInstance quest = profile.findById(leader.getQuestCampQuestId());
        if (quest == null) {
            return;
        }
        leader.endDialogue(player.method_5667());
        if (!negotiate) {
            WildlingRaiderCampQuestService.activateCampAggression(world, quest, player);
        } else {
            double chance = Math.min(11.0, 1.0 + (double)((PlayerSkillHolder)player).got$getSkillProfile().get(SkillAttributeType.CHARISMA).getPoints() * 0.1);
            if (player.method_59922().method_43058() < chance / 100.0) {
                WildlingRaiderCampQuestService.markObjectiveComplete(player, quest, world);
            } else {
                WildlingRaiderCampQuestService.activateCampAggression(world, quest, player);
            }
        }
        ServerPlayNetworking.send((class_3222)player, (class_8710)new BanditCampNegotiationResultPayload(targetEntityId, negotiate));
    }

    public static void onQuestCampRaiderDamaged(WildlingRaiderNpcEntity raider, class_1282 source) {
        class_3222 player;
        QuestInstance quest;
        if (raider.method_37908().method_8608() || raider.getQuestCampQuestId().isBlank()) {
            return;
        }
        class_1297 class_12972 = source.method_5529();
        if (class_12972 instanceof class_3222 && (quest = ((PlayerQuestHolder)(player = (class_3222)class_12972)).got$getQuestProfile().findById(raider.getQuestCampQuestId())) != null) {
            WildlingRaiderCampQuestService.activateCampAggression((class_3218)raider.method_37908(), quest, player);
        }
    }

    private static void markObjectiveComplete(class_3222 player, QuestInstance quest, class_3218 world) {
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        class_2487 metadata = quest.metadata().method_10553();
        metadata.method_10556(OBJECTIVE_COMPLETE_KEY, true);
        profile.updateQuest(quest.withMetadata(metadata));
        WildlingRaiderCampQuestService.discardTent(world, quest);
        WildlingRaiderCampQuestService.discardQuestRaiders(world, quest);
        GotQuests.sendQuestState(player);
        player.method_7353((class_2561)class_2561.method_43470((String)"The wildling raider camp has been dealt with.").method_27692(class_124.field_1054), true);
    }

    private static void activateCampAggression(class_3218 world, QuestInstance quest, class_3222 player) {
        WildlingRaiderCampQuestService.getQuestRaiders(world, quest).forEach(raider -> {
            raider.setAwaitingQuestCampDecision(false);
            raider.activateAmbushAggression((class_1309)player);
        });
    }

    private static List<WildlingRaiderNpcEntity> spawnRaiders(class_3218 world, CampPlacement placement, String questId, int count) {
        ArrayList<WildlingRaiderNpcEntity> raiders = new ArrayList<WildlingRaiderNpcEntity>();
        List<class_2338> candidates = WildlingRaiderCampQuestService.collectSpawnCandidates(world, placement.origin(), placement.chunk());
        for (int index = 0; index < Math.min(count, candidates.size()); ++index) {
            WildlingRaiderNpcEntity raider = (WildlingRaiderNpcEntity)GotEntities.WILDLING_RAIDER_NPC.method_5883((class_1937)world);
            if (raider == null) continue;
            class_2338 spawnPos = candidates.get(index);
            raider.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
            raider.configureQuestCamp(questId, index == 0);
            if (index == 0) {
                raider.method_5665((class_2561)class_2561.method_43470((String)"Wildling Raider Leader"));
            }
            world.method_8649((class_1297)raider);
            raiders.add(raider);
        }
        return raiders;
    }

    private static TentEntity spawnTent(class_3218 world, class_2338 origin, class_2350 facing, String questId) {
        if (!TENT_BLOCK.canPlaceStructure((class_1937)world, origin, facing)) {
            return null;
        }
        TentEntity tent = new TentEntity(GotEntities.TENT, (class_1937)world);
        tent.setOriginPos(origin);
        tent.setFacing(facing);
        tent.setQuestId(questId);
        TentCollisionBlock.Vec3 center = TENT_BLOCK.centerForEntity(origin, facing);
        tent.method_5808(center.x(), center.y(), center.z(), facing.method_10144(), 0.0f);
        if (world.method_8649((class_1297)tent)) {
            TENT_BLOCK.placeStructure(world, origin, facing, tent.method_5667());
            return tent;
        }
        return null;
    }

    private static CampPlacement findPlacement(class_3218 world, CastleInstance castle) {
        int minR;
        int cx = castle.getBannerPos().method_10263() >> 4;
        int cz = castle.getBannerPos().method_10260() >> 4;
        for (int r = minR = Math.max(4, castle.getClaimRadiusChunks() + 2); r <= minR + 7; ++r) {
            for (int x = -r; x <= r; ++x) {
                for (int z = -r; z <= r; ++z) {
                    CampPlacement placement;
                    if (Math.max(Math.abs(x), Math.abs(z)) != r || (placement = WildlingRaiderCampQuestService.validatePlacement(world, castle, new class_1923(cx + x, cz + z))) == null) continue;
                    return placement;
                }
            }
        }
        return null;
    }

    private static CampPlacement validatePlacement(class_3218 world, CastleInstance castle, class_1923 chunk) {
        class_2338 center = new class_2338(chunk.method_33940(), world.method_8615() + 1, chunk.method_33942());
        if (!WildlingRaiderCampQuestService.isGiftBiome(world, center) || !WildlingRaiderCampQuestService.isGiftChunkArea(world, chunk) || WildlingRaiderCampQuestService.isAttachedToClaim(world, chunk)) {
            return null;
        }
        class_2338 origin = WildlingRaiderCampQuestService.safeTentOrigin(world, chunk);
        if (origin == null || !WildlingRaiderCampQuestService.isGiftBiome(world, origin)) {
            return null;
        }
        class_2350 facing = WildlingRaiderCampQuestService.directionToward(origin, castle.getBannerPos());
        return TENT_BLOCK.canPlaceStructure((class_1937)world, origin, facing) ? new CampPlacement(chunk, origin, facing) : null;
    }

    private static boolean isAttachedToClaim(class_3218 world, class_1923 chunk) {
        for (int x = -1; x <= 1; ++x) {
            for (int z = -1; z <= 1; ++z) {
                if (CastleWorldState.get(world).findClaimAt(new class_2338((chunk.field_9181 + x) * 16 + 8, 64, (chunk.field_9180 + z) * 16 + 8)) == null) continue;
                return true;
            }
        }
        return false;
    }

    private static boolean isGiftBiome(class_3218 world, class_2338 pos) {
        return world.method_23753(pos).method_40230().map(key -> key.equals(GotBiomeKeys.THE_GIFT)).orElse(false);
    }

    private static boolean isGiftChunk(class_3218 world, class_1923 chunk) {
        return WildlingRaiderCampQuestService.isGiftBiome(world, new class_2338(chunk.method_8326() + 2, world.method_8615() + 1, chunk.method_8328() + 2)) && WildlingRaiderCampQuestService.isGiftBiome(world, new class_2338(chunk.method_8327() - 2, world.method_8615() + 1, chunk.method_8328() + 2)) && WildlingRaiderCampQuestService.isGiftBiome(world, new class_2338(chunk.method_8326() + 2, world.method_8615() + 1, chunk.method_8329() - 2)) && WildlingRaiderCampQuestService.isGiftBiome(world, new class_2338(chunk.method_8327() - 2, world.method_8615() + 1, chunk.method_8329() - 2));
    }

    private static boolean isGiftChunkArea(class_3218 world, class_1923 chunk) {
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dz = -1; dz <= 1; ++dz) {
                if (WildlingRaiderCampQuestService.isGiftChunk(world, new class_1923(chunk.field_9181 + dx, chunk.field_9180 + dz))) continue;
                return false;
            }
        }
        return true;
    }

    private static class_2338 safeTentOrigin(class_3218 world, class_1923 chunk) {
        int y = world.method_8624(class_2902.class_2903.field_13203, chunk.method_33940(), chunk.method_33942());
        class_2338 pos = new class_2338(chunk.method_33940(), y, chunk.method_33942());
        class_2680 floor = world.method_8320(pos.method_10074());
        return floor.method_51366() ? pos : null;
    }

    private static class_2350 directionToward(class_2338 from, class_2338 target) {
        int dx = target.method_10263() - from.method_10263();
        int dz = target.method_10260() - from.method_10260();
        return Math.abs(dx) > Math.abs(dz) ? (dx > 0 ? class_2350.field_11034 : class_2350.field_11039) : (dz > 0 ? class_2350.field_11035 : class_2350.field_11043);
    }

    private static List<class_2338> collectSpawnCandidates(class_3218 world, class_2338 origin, class_1923 chunk) {
        ArrayList<class_2338> list = new ArrayList<class_2338>();
        for (int x = chunk.method_8326() + 2; x <= chunk.method_8327() - 2; x += 3) {
            for (int z = chunk.method_8328() + 2; z <= chunk.method_8329() - 2; z += 3) {
                class_2338 pos = new class_2338(x, world.method_8624(class_2902.class_2903.field_13203, x, z), z);
                if (!(pos.method_10262((class_2382)origin) > 16.0) || !world.method_8320(pos.method_10074()).method_51366() || !WildlingRaiderCampQuestService.isGiftBiome(world, pos)) continue;
                list.add(pos);
            }
        }
        return list;
    }

    private static List<WildlingRaiderNpcEntity> getQuestRaiders(class_3218 world, QuestInstance quest) {
        return world.method_8390(WildlingRaiderNpcEntity.class, WildlingRaiderCampQuestService.questSearchBox(world, quest), raider -> quest.id().equals(raider.getQuestCampQuestId()) && raider.method_5805());
    }

    private static void discardQuestRaiders(class_3218 world, QuestInstance quest) {
        WildlingRaiderCampQuestService.getQuestRaiders(world, quest).forEach(class_1297::method_31472);
    }

    private static void discardTent(class_3218 world, QuestInstance quest) {
        world.method_8390(TentEntity.class, WildlingRaiderCampQuestService.questSearchBox(world, quest), tent -> quest.id().equals(tent.getQuestId())).forEach(tent -> {
            class_2248 patt0$temp = world.method_8320(tent.getOriginPos()).method_26204();
            if (patt0$temp instanceof TentCollisionBlock) {
                TentCollisionBlock block = (TentCollisionBlock)patt0$temp;
                block.destroyStructure((class_1937)world, tent.getOriginPos(), world.method_8320(tent.getOriginPos()), false);
            } else {
                tent.method_31472();
            }
        });
    }

    private static class_238 questSearchBox(class_3218 world, QuestInstance quest) {
        class_2487 metadata = quest.metadata();
        class_1923 chunk = new class_1923(metadata.method_10550("camp_chunk_x"), metadata.method_10550("camp_chunk_z"));
        return new class_238((double)chunk.method_8326(), (double)world.method_31607(), (double)chunk.method_8328(), (double)(chunk.method_8327() + 1), (double)world.method_31600(), (double)(chunk.method_8329() + 1)).method_1014(10.0);
    }

    private static class_3218 resolveQuestWorld(MinecraftServer server, QuestInstance quest) {
        class_2960 id = class_2960.method_12829((String)quest.metadata().method_10558("camp_world"));
        return server.method_3847(class_5321.method_29179((class_5321)class_7924.field_41223, (class_2960)(id != null ? id : class_2960.method_60654((String)"overworld"))));
    }

    private static void handleRespawn(class_3222 oldPlayer, class_3222 newPlayer, boolean alive) {
        if (alive) {
            return;
        }
        ((PlayerQuestHolder)newPlayer).got$getQuestProfile().getActiveQuests().stream().filter(quest -> "destroy_wildling_raider_camp".equals(quest.templateId())).forEach(quest -> GotQuests.failQuest(newPlayer, quest.id(), "Player died."));
    }

    private record CampPlacement(class_1923 chunk, class_2338 origin, class_2350 facing) {
    }
}

