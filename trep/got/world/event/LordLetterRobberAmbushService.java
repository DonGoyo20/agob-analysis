/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2680
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_5321
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.world.event;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2902;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5321;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import trep.got.entity.RobberNpcEntity;
import trep.got.network.OpenNpcDialoguePayload;
import trep.got.quest.PlayerQuestHolder;
import trep.got.quest.PlayerQuestProfile;
import trep.got.registry.GotCharacters;
import trep.got.registry.GotEntities;
import trep.got.registry.GotQuests;
import trep.got.skill.SkillAttributeType;
import trep.got.skill.SkillExperienceService;
import trep.got.world.event.WorldEventOccurrence;
import trep.got.world.event.context.WorldEventPlayerSnapshot;

public final class LordLetterRobberAmbushService {
    private static final String TRIGGER_ID = "lord_letter_robber_ambush";
    private static final int ROBBER_COUNT = 3;
    private static final double SPAWN_DISTANCE = 5.5;
    private static final double MIN_SPAWN_DISTANCE = 4.0;
    private static final double MAX_SPAWN_DISTANCE = 8.0;
    private static final double ESCAPE_DISTANCE = 42.0;
    private static final long AMBUSH_TICK_INTERVAL_TICKS = 20L;
    private static final Map<UUID, ActiveAmbush> ACTIVE_AMBUSHES = new LinkedHashMap<UUID, ActiveAmbush>();

    private LordLetterRobberAmbushService() {
    }

    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(LordLetterRobberAmbushService::tickServer);
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(LordLetterRobberAmbushService::onKilledEntity);
    }

    public static void handleOccurrence(class_3222 player, WorldEventPlayerSnapshot snapshot, WorldEventOccurrence occurrence) {
        String targetLordName;
        class_3218 world;
        block11: {
            block10: {
                if (player == null || snapshot == null || occurrence == null || !TRIGGER_ID.equals(occurrence.triggerId())) {
                    return;
                }
                class_1937 class_19372 = player.method_37908();
                if (!(class_19372 instanceof class_3218)) break block10;
                world = (class_3218)class_19372;
                if (!ACTIVE_AMBUSHES.containsKey(player.method_5667())) break block11;
            }
            return;
        }
        String questId = occurrence.details().getOrDefault("quest_id", "");
        if (questId.isBlank()) {
            return;
        }
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        if (profile.hasRobberAmbushTriggered(questId) || profile.findById(questId) == null) {
            return;
        }
        class_2338 anchor = LordLetterRobberAmbushService.findAmbushAnchor(world, player);
        if (anchor == null) {
            return;
        }
        String groupId = UUID.randomUUID().toString();
        List<RobberNpcEntity> robbers = LordLetterRobberAmbushService.spawnRobbers(world, anchor, groupId, targetLordName = occurrence.details().getOrDefault("target_lord_name", "the local lord"));
        if (robbers.isEmpty()) {
            return;
        }
        RobberNpcEntity leader = robbers.getFirst();
        if (!leader.forceBeginDialogue(player)) {
            for (RobberNpcEntity robber : robbers) {
                robber.method_31472();
            }
            return;
        }
        profile.markRobberAmbushTriggered(questId);
        ACTIVE_AMBUSHES.put(player.method_5667(), new ActiveAmbush(player.method_5667(), (class_5321<class_1937>)world.method_27983(), questId, groupId, targetLordName, robbers.stream().map(class_1297::method_5667).toList(), false));
        ServerPlayNetworking.send((class_3222)player, (class_8710)new OpenNpcDialoguePayload(leader.method_5628()));
    }

    public static void handleDecision(class_3222 player, int targetEntityId, boolean accept) {
        RobberNpcEntity leader;
        class_1937 class_19372;
        if (player == null || !((class_19372 = player.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        class_1297 entity = world.method_8469(targetEntityId);
        if (!(entity instanceof RobberNpcEntity) || !(leader = (RobberNpcEntity)entity).isAmbushLeader()) {
            return;
        }
        ActiveAmbush ambush = ACTIVE_AMBUSHES.get(player.method_5667());
        if (ambush == null || !ambush.groupId().equals(leader.getAmbushGroupId())) {
            return;
        }
        if (accept) {
            LordLetterRobberAmbushService.applyQuestFailureOutcome(player, ambush.questId());
            LordLetterRobberAmbushService.cleanupAmbush(world.method_8503(), ambush);
            return;
        }
        LordLetterRobberAmbushService.activateRobbers(world, ambush, player);
        ACTIVE_AMBUSHES.put(player.method_5667(), ambush.withHostileResolved(true));
    }

    private static void onKilledEntity(class_3218 world, class_1297 attacker, class_1297 killedEntity) {
        if (!(killedEntity instanceof class_3222)) {
            return;
        }
        class_3222 player = (class_3222)killedEntity;
        if (!(attacker instanceof RobberNpcEntity)) {
            return;
        }
        RobberNpcEntity robber = (RobberNpcEntity)attacker;
        ActiveAmbush ambush = ACTIVE_AMBUSHES.get(player.method_5667());
        if (ambush == null || !ambush.groupId().equals(robber.getAmbushGroupId())) {
            return;
        }
        LordLetterRobberAmbushService.applyQuestFailureOutcome(player, ambush.questId());
        LordLetterRobberAmbushService.cleanupAmbush(world.method_8503(), ambush);
    }

    private static void tickServer(MinecraftServer server) {
        if (server == null || ACTIVE_AMBUSHES.isEmpty() || (long)server.method_3780() % 20L != 0L) {
            return;
        }
        for (ActiveAmbush ambush : List.copyOf(ACTIVE_AMBUSHES.values())) {
            boolean anyClose;
            class_3222 player = server.method_3760().method_14602(ambush.playerUuid());
            class_3218 world = server.method_3847(ambush.worldKey());
            if (player == null || world == null) {
                ACTIVE_AMBUSHES.remove(ambush.playerUuid());
                continue;
            }
            List<RobberNpcEntity> robbers = LordLetterRobberAmbushService.resolveRobbers(world, ambush);
            if (robbers.isEmpty()) {
                ACTIVE_AMBUSHES.remove(ambush.playerUuid());
                continue;
            }
            if (!ambush.hostileResolved() || (anyClose = robbers.stream().anyMatch(robber -> robber.method_5858((class_1297)player) <= 1764.0))) continue;
            LordLetterRobberAmbushService.cleanupAmbush(server, ambush);
        }
    }

    private static List<RobberNpcEntity> spawnRobbers(class_3218 world, class_2338 anchor, String groupId, String targetLordName) {
        ArrayList<RobberNpcEntity> robbers = new ArrayList<RobberNpcEntity>();
        for (int index = 0; index < 3; ++index) {
            RobberNpcEntity robber;
            class_2338 spawnPos;
            class_2338 class_23382 = spawnPos = index == 0 ? anchor : LordLetterRobberAmbushService.findCompanionPos(world, anchor, index);
            if (spawnPos == null || (robber = (RobberNpcEntity)GotEntities.ROBBER_NPC.method_5883((class_1937)world)) == null) continue;
            robber.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
            if (index == 0) {
                robber.configureAmbushLeader(groupId, targetLordName);
                robber.method_5665((class_2561)class_2561.method_43470((String)"Robber Leader"));
            } else {
                robber.configureAmbushMember(groupId);
            }
            world.method_8649((class_1297)robber);
            robbers.add(robber);
        }
        return robbers;
    }

    private static void activateRobbers(class_3218 world, ActiveAmbush ambush, class_3222 player) {
        for (RobberNpcEntity robber : LordLetterRobberAmbushService.resolveRobbers(world, ambush)) {
            robber.activateAmbushAggression((class_1309)player);
        }
    }

    private static List<RobberNpcEntity> resolveRobbers(class_3218 world, ActiveAmbush ambush) {
        ArrayList<RobberNpcEntity> robbers = new ArrayList<RobberNpcEntity>();
        for (UUID uuid : ambush.robberUuids()) {
            RobberNpcEntity robber;
            class_1297 entity = world.method_14190(uuid);
            if (!(entity instanceof RobberNpcEntity) || !(robber = (RobberNpcEntity)entity).method_5805()) continue;
            robbers.add(robber);
        }
        return robbers;
    }

    private static void cleanupAmbush(MinecraftServer server, ActiveAmbush ambush) {
        class_3218 world = server.method_3847(ambush.worldKey());
        if (world != null) {
            for (UUID uuid : ambush.robberUuids()) {
                class_1297 entity = world.method_14190(uuid);
                if (!(entity instanceof RobberNpcEntity)) continue;
                RobberNpcEntity robber = (RobberNpcEntity)entity;
                robber.method_31472();
            }
        }
        ACTIVE_AMBUSHES.remove(ambush.playerUuid());
    }

    private static void applyQuestFailureOutcome(class_3222 player, String questId) {
        if (!GotQuests.failQuest(player, questId, "Quest failed")) {
            return;
        }
        SkillExperienceService.removePoints(player, SkillAttributeType.CHARISMA, 1);
        SkillExperienceService.removePoints(player, SkillAttributeType.INTELLIGENCE, 1);
        GotCharacters.sendCharacterSelectionState(player);
        player.method_7353((class_2561)class_2561.method_43470((String)"You lost 1 Charisma and 1 Intelligence."), false);
    }

    private static class_2338 findAmbushAnchor(class_3218 world, class_3222 player) {
        class_243 forward = player.method_5828(1.0f);
        class_243 horizontal = new class_243(forward.field_1352, 0.0, forward.field_1350);
        if (horizontal.method_1027() < 1.0E-4) {
            horizontal = new class_243(1.0, 0.0, 0.0);
        }
        class_243 normalized = horizontal.method_1029();
        class_243 right = new class_243(-normalized.field_1350, 0.0, normalized.field_1352);
        double[] distances = new double[]{5.5, 4.0, 8.0};
        class_243[] directions = new class_243[]{normalized, normalized.method_1019(right.method_1021(0.55)).method_1029(), normalized.method_1019(right.method_1021(-0.55)).method_1029(), right, right.method_1021(-1.0), normalized.method_1021(-1.0)};
        for (double distance : distances) {
            for (class_243 direction : directions) {
                class_2338 candidate = LordLetterRobberAmbushService.safeSpawnPos(world, class_2338.method_49638((class_2374)player.method_19538().method_1019(direction.method_1021(distance))));
                if (candidate == null || !(candidate.method_10262((class_2382)player.method_24515()) <= 100.0)) continue;
                return candidate;
            }
        }
        return null;
    }

    private static class_2338 findCompanionPos(class_3218 world, class_2338 anchor, int index) {
        int[][] offsets = new int[][]{{2, 0}, {-2, 0}, {0, 2}, {0, -2}, {2, 2}, {-2, -2}};
        int[] offset = offsets[Math.floorMod(index - 1, offsets.length)];
        return LordLetterRobberAmbushService.safeSpawnPos(world, anchor.method_10069(offset[0], 0, offset[1]));
    }

    private static class_2338 safeSpawnPos(class_3218 world, class_2338 origin) {
        int z;
        int x = origin.method_10263();
        int feetY = world.method_8624(class_2902.class_2903.field_13203, x, z = origin.method_10260());
        if (feetY <= world.method_31607()) {
            return null;
        }
        class_2338 groundPos = new class_2338(x, feetY - 1, z);
        class_2338 feetPos = groundPos.method_10084();
        class_2338 headPos = feetPos.method_10084();
        class_2680 groundState = world.method_8320(groundPos);
        if (!groundState.method_51366()) {
            return null;
        }
        if (!world.method_8320(feetPos).method_26215() || !world.method_8320(headPos).method_26215()) {
            return null;
        }
        return feetPos;
    }

    private record ActiveAmbush(UUID playerUuid, class_5321<class_1937> worldKey, String questId, String groupId, String targetLordName, List<UUID> robberUuids, boolean hostileResolved) {
        private ActiveAmbush withHostileResolved(boolean hostileResolved) {
            return new ActiveAmbush(this.playerUuid, this.worldKey, this.questId, this.groupId, this.targetLordName, this.robberUuids, hostileResolved);
        }
    }
}

