/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.networking.v1.PacketSender
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_1799
 *  net.minecraft.class_1934
 *  net.minecraft.class_1935
 *  net.minecraft.class_1959
 *  net.minecraft.class_1966
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3532
 *  net.minecraft.class_5321
 *  net.minecraft.class_7924
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1799;
import net.minecraft.class_1934;
import net.minecraft.class_1935;
import net.minecraft.class_1959;
import net.minecraft.class_1966;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2902;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3532;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import trep.got.GotMod;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.character.PlayerGeneticTrait;
import trep.got.character.PlayerPersonalityTrait;
import trep.got.character.PlayerSkinBuilderProfile;
import trep.got.character.PlayerTraitEffects;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationProfile;
import trep.got.network.CharacterSelectionStatePayload;
import trep.got.network.CharacterSpawnSearchPayload;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotItems;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotRelations;
import trep.got.registry.GotReligionOptions;
import trep.got.religion.ReligionSelectionOption;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.PlayerSkillProfile;
import trep.got.skill.SkillAttributeType;
import trep.got.tailor.ClothingPatternRegistry;
import trep.got.world.dimension.GotDimensionKeys;
import trep.got.world.gen.MapBiomeSource;

public final class GotCharacters {
    private static final Map<UUID, PendingSpawnRequest> PENDING_SPAWNS = new ConcurrentHashMap<UUID, PendingSpawnRequest>();
    private static final int[] RESPAWN_RADII = new int[]{0, 16, 32, 48, 64, 96, 128, 192};
    private static final int CASTLE_SPAWN_MIN_DISTANCE = 900;
    private static final int CASTLE_SPAWN_MAX_DISTANCE = 1200;
    private static final int CASTLE_SPAWN_ATTEMPTS = 48;

    private GotCharacters() {
    }

    public static void initialize() {
        GotCharacterOptions.initialize();
        GotReligionOptions.initialize();
        ServerPlayerEvents.COPY_FROM.register(GotCharacters::copyCharacterProfile);
        ServerPlayerEvents.AFTER_RESPAWN.register(GotCharacters::handleRespawn);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            GotCharacters.queueStartingSpawn(handler.field_14140);
            GotCharacters.sendCharacterSelectionState(handler.field_14140, sender);
        });
        ServerTickEvents.END_SERVER_TICK.register(GotCharacters::flushPendingSpawns);
    }

    public static boolean assignKingdom(class_3222 player, String religionId, String kingdomId, String optionId, String geneticTraitId, String[] personalityTraitIds, PlayerSkinBuilderProfile skinBuilderProfile) {
        ReligionSelectionOption religionOption = GotReligionOptions.get(religionId);
        if (religionOption == null) {
            return false;
        }
        class_2960 parsedKingdomId = class_2960.method_12829((String)kingdomId);
        if (parsedKingdomId == null) {
            return false;
        }
        Kingdom kingdom = GotKingdoms.get(parsedKingdomId);
        if (kingdom == null) {
            return false;
        }
        CharacterSelectionOption option = GotCharacterOptions.get(optionId);
        if (option == null || !option.kingdomId().equals((Object)parsedKingdomId)) {
            return false;
        }
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        boolean firstSelection = !profile.hasSelection();
        PlayerGeneticTrait geneticTrait = PlayerGeneticTrait.byId(geneticTraitId);
        Set<PlayerPersonalityTrait> personalityTraits = PlayerTraitEffects.resolvePersonalityTraits(Arrays.asList(personalityTraitIds));
        profile.setSelectedReligionId(religionOption.id());
        profile.setSelectedKingdomId(kingdom.getId().toString());
        profile.setSelectedOptionId(optionId);
        profile.setSelectedGeneticTraitId(geneticTrait == null ? "" : geneticTrait.id());
        profile.setSelectedPersonalityTraitIds(personalityTraits.stream().map(PlayerPersonalityTrait::id).collect(Collectors.toCollection(LinkedHashSet::new)));
        profile.setSkinBuilderProfile(skinBuilderProfile);
        profile.getTailorProfile().unlockDefaultClothes(profile.getSkinBuilderProfile());
        profile.initializeOriginalSelection(kingdom.getId().toString(), optionId);
        PlayerSkillProfile skillProfile = ((PlayerSkillHolder)player).got$getSkillProfile();
        skillProfile.applyStartingPoints(PlayerTraitEffects.mergeStartingPoints(option.startingSkillPoints(), geneticTrait, personalityTraits));
        skillProfile.setMaxPointBonuses(PlayerTraitEffects.mergeMaxCapBonuses(option.maxSkillCapBonuses(), geneticTrait));
        if (firstSelection) {
            player.method_7336(class_1934.field_9215);
            GotCharacters.giveStarterItems(player);
            GotRelations.initializePlayerRelationsFromCurrentKingdom(player, kingdom.getId().toString());
        }
        GotMod.LOGGER.info("Starting spawn search for player {} option {} kingdom {} with spawn biomes {}", new Object[]{player.method_7334().getName(), option.id(), kingdom.getId(), option.spawnBiomes()});
        ServerPlayNetworking.send((class_3222)player, (class_8710)new CharacterSpawnSearchPayload(true));
        GotCharacters.teleportToStartingDimension(player, PendingSpawnRequest.optionSpawn(option.spawnBiomes(), kingdom.getId().toString()));
        ServerPlayNetworking.send((class_3222)player, (class_8710)GotCharacters.createSelectionStatePayload(profile, skillProfile, ((PlayerReputationHolder)player).got$getReputationProfile()));
        if (!firstSelection) {
            GotRelations.syncToPlayer(player);
        }
        ServerPlayNetworking.send((class_3222)player, (class_8710)new CharacterSpawnSearchPayload(false));
        GotMod.LOGGER.info("Attached kingdom {} to player {}", (Object)kingdom.getName(), (Object)player.method_7334().getName());
        return true;
    }

    public static void updatePlayerSkinBuilder(class_3222 player, PlayerSkinBuilderProfile skinBuilderProfile) {
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        profile.setSkinBuilderProfile(skinBuilderProfile);
        profile.getTailorProfile().unlockDefaultClothes(profile.getSkinBuilderProfile());
        GotCharacters.sendCharacterSelectionState(player);
    }

    private static void giveStarterItems(class_3222 player) {
        GotCharacters.giveStack(player, new class_1799((class_1935)GotItems.GUIDEBOOK));
        GotCharacters.giveStack(player, new class_1799((class_1935)GotItems.WORLD_MAP));
    }

    private static void giveStack(class_3222 player, class_1799 stack) {
        if (!player.method_31548().method_7394(stack)) {
            player.method_7328(stack, false);
        }
    }

    private static void copyCharacterProfile(class_3222 oldPlayer, class_3222 newPlayer, boolean alive) {
        PlayerCharacterProfile source = ((PlayerCharacterHolder)oldPlayer).got$getCharacterProfile();
        ((PlayerCharacterHolder)newPlayer).got$getCharacterProfile().copyFrom(source);
    }

    private static void handleRespawn(class_3222 oldPlayer, class_3222 newPlayer, boolean alive) {
        if (!oldPlayer.method_37908().method_27983().equals(GotDimensionKeys.ICEANDFIRE)) {
            return;
        }
        if (newPlayer.method_37908().method_27983().equals(GotDimensionKeys.ICEANDFIRE)) {
            return;
        }
        class_3218 world = newPlayer.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            GotMod.LOGGER.warn("Unable to respawn {} in iceandfire because the world is missing", (Object)newPlayer.method_7334().getName());
            return;
        }
        class_2338 respawnAnchor = oldPlayer.method_26280();
        if (respawnAnchor == null || !oldPlayer.method_26281().equals(GotDimensionKeys.ICEANDFIRE)) {
            respawnAnchor = oldPlayer.method_24515();
        }
        class_2338 respawnPos = GotCharacters.findNearbyRespawnPosition(world, respawnAnchor);
        GotCharacters.teleportPlayerSafely(newPlayer, world, respawnPos);
    }

    public static void sendCharacterSelectionState(class_3222 player) {
        ServerPlayNetworking.send((class_3222)player, (class_8710)GotCharacters.createSelectionStatePayload(((PlayerCharacterHolder)player).got$getCharacterProfile(), ((PlayerSkillHolder)player).got$getSkillProfile(), ((PlayerReputationHolder)player).got$getReputationProfile()));
    }

    private static void sendCharacterSelectionState(class_3222 player, PacketSender sender) {
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        PlayerSkillProfile skillProfile = ((PlayerSkillHolder)player).got$getSkillProfile();
        ReputationProfile reputationProfile = ((PlayerReputationHolder)player).got$getReputationProfile();
        sender.sendPacket((class_8710)GotCharacters.createSelectionStatePayload(profile, skillProfile, reputationProfile));
    }

    private static CharacterSelectionStatePayload createSelectionStatePayload(PlayerCharacterProfile profile, PlayerSkillProfile skillProfile, ReputationProfile reputationProfile) {
        int[] skillPoints = new int[SkillAttributeType.ALL.size()];
        int[] skillMaxPoints = new int[SkillAttributeType.ALL.size()];
        int[] shirtSkillBonuses = new int[SkillAttributeType.ALL.size()];
        int[] pantsSkillBonuses = new int[SkillAttributeType.ALL.size()];
        Map<SkillAttributeType, Integer> shirtAttributes = ClothingPatternRegistry.attributesFor(profile.getSkinBuilderProfile().shirtId());
        Map<SkillAttributeType, Integer> pantsAttributes = ClothingPatternRegistry.attributesFor(profile.getSkinBuilderProfile().pantsId());
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            skillPoints[type.ordinal()] = skillProfile.get(type).getPoints();
            skillMaxPoints[type.ordinal()] = skillProfile.getMaxPoints(type);
            shirtSkillBonuses[type.ordinal()] = shirtAttributes.getOrDefault((Object)type, 0);
            pantsSkillBonuses[type.ordinal()] = pantsAttributes.getOrDefault((Object)type, 0);
        }
        return new CharacterSelectionStatePayload(profile.getSelectedReligionId(), profile.getSelectedKingdomId(), profile.getSelectedOptionId(), profile.getOriginalSelectedKingdomId(), profile.getOriginalSelectedOptionId(), profile.getSelectedGeneticTraitId(), (String[])profile.getSelectedPersonalityTraitIds().toArray(String[]::new), profile.getSkinBuilderProfile(), skillPoints, skillMaxPoints, shirtSkillBonuses, pantsSkillBonuses, reputationProfile.getExperience(), reputationProfile.getTitle().getId(), reputationProfile.getCurrentRoute(), reputationProfile.hasKnighthoodCeremonyCompleted(), reputationProfile.hasSellswordContractCompleted(), reputationProfile.isBaptised());
    }

    private static void queueStartingSpawn(class_3222 player) {
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        if (profile.hasSelection()) {
            return;
        }
        player.method_7336(class_1934.field_9219);
        if (!GotCharacters.teleportToStartingDimension(player, PendingSpawnRequest.defaultSpawn())) {
            PENDING_SPAWNS.put(player.method_5667(), PendingSpawnRequest.defaultSpawn());
        }
    }

    private static void flushPendingSpawns(MinecraftServer server) {
        if (PENDING_SPAWNS.isEmpty()) {
            return;
        }
        ArrayList<Map.Entry<UUID, PendingSpawnRequest>> entries = new ArrayList<Map.Entry<UUID, PendingSpawnRequest>>(PENDING_SPAWNS.entrySet());
        for (Map.Entry entry : entries) {
            class_3222 player = server.method_3760().method_14602((UUID)entry.getKey());
            if (player == null || player.method_31481() || player.field_13987 == null) {
                PENDING_SPAWNS.remove(entry.getKey());
                continue;
            }
            if (!GotCharacters.teleportToStartingDimension(player, (PendingSpawnRequest)entry.getValue())) continue;
            PENDING_SPAWNS.remove(entry.getKey());
        }
    }

    private static boolean teleportToStartingDimension(class_3222 player, PendingSpawnRequest request) {
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            GotMod.LOGGER.warn("Spawn search aborted for {} because iceandfire world is missing", (Object)player.method_7334().getName());
            return false;
        }
        if (request.spawnBiomes().isEmpty() && player.method_37908().method_27983().equals(GotDimensionKeys.ICEANDFIRE)) {
            GotMod.LOGGER.info("Skipping default spawn teleport for {} because player is already in iceandfire", (Object)player.method_7334().getName());
            return true;
        }
        class_2338 targetPos = GotCharacters.findTargetSpawn(world, request);
        int x = targetPos.method_10263();
        int z = targetPos.method_10260();
        int y = GotCharacters.findSurfaceTeleportY(world, x, z, request.allowedBiomes());
        class_2338 finalPos = new class_2338(x, y, z);
        GotMod.LOGGER.info("Teleporting {} to iceandfire spawn at ({}, {}, {}) biome={} requested_biomes={}", new Object[]{player.method_7334().getName(), x, y, z, GotCharacters.getBiomeName(world, finalPos), request.spawnBiomes()});
        player.method_26284(GotDimensionKeys.ICEANDFIRE, finalPos, 0.0f, true, false);
        GotCharacters.teleportPlayerSafely(player, world, finalPos);
        return true;
    }

    private static int findSurfaceTeleportY(class_3218 world, int x, int z, List<class_5321<class_1959>> allowedBiomes) {
        int[] radii;
        world.method_8497(x >> 4, z >> 4);
        for (int radius : radii = new int[]{0, 2, 4, 6, 8}) {
            for (int offsetX = -radius; offsetX <= radius; ++offsetX) {
                for (int offsetZ = -radius; offsetZ <= radius; ++offsetZ) {
                    boolean validBiome;
                    int sampleZ;
                    int sampleX;
                    int feetY;
                    if (radius > 0 && Math.abs(offsetX) < radius && Math.abs(offsetZ) < radius || (feetY = GotCharacters.findSafeFeetYInColumn(world, sampleX = x + offsetX, sampleZ = z + offsetZ, allowedBiomes)) <= world.method_31607()) continue;
                    class_2338 feetPos = new class_2338(sampleX, feetY, sampleZ);
                    class_2338 headPos = feetPos.method_10084();
                    class_2338 groundPos = feetPos.method_10074();
                    boolean bl = validBiome = allowedBiomes.isEmpty() || allowedBiomes.stream().anyMatch(key -> world.method_23753(feetPos).method_40225(key));
                    if (!validBiome || !GotCharacters.isSafeSpawnGround(world, groundPos, world.method_8320(groundPos)) || !GotCharacters.isSafeSpawnSpace(world.method_8320(feetPos), world.method_8320(headPos))) continue;
                    return feetPos.method_10264();
                }
            }
        }
        return GotCharacters.findSafeTeleportY(world, x, z, allowedBiomes);
    }

    private static void teleportPlayerSafely(class_3222 player, class_3218 world, class_2338 targetPos) {
        GotCharacters.clearSpawnSpace(world, targetPos);
        class_2338 arrivalPos = targetPos.method_10086(2);
        GotCharacters.clearSpawnSpace(world, arrivalPos);
        player.method_48105(world, (double)arrivalPos.method_10263() + 0.5, (double)arrivalPos.method_10264(), (double)arrivalPos.method_10260() + 0.5, Set.of(), player.method_36454(), player.method_36455());
        player.method_24830(true);
        player.method_18800(0.0, 0.0, 0.0);
    }

    private static class_2338 findNearbyRespawnPosition(class_3218 world, class_2338 anchor) {
        for (int radius : RESPAWN_RADII) {
            for (int attempt = 0; attempt < 8; ++attempt) {
                int y;
                int x = anchor.method_10263();
                int z = anchor.method_10260();
                if (radius > 0) {
                    x += world.field_9229.method_39332(-radius, radius);
                    z += world.field_9229.method_39332(-radius, radius);
                }
                if ((y = GotCharacters.findSafeTeleportY(world, x, z, List.of())) <= world.method_31607()) continue;
                return new class_2338(x, y, z);
            }
        }
        int fallbackY = GotCharacters.findSafeTeleportY(world, anchor.method_10263(), anchor.method_10260(), List.of());
        return new class_2338(anchor.method_10263(), fallbackY, anchor.method_10260());
    }

    private static class_2338 findTargetSpawn(class_3218 world, PendingSpawnRequest request) {
        Optional<class_2338> castleSpawn = GotCharacters.findSpawnNearKingdomCastle(world, request);
        if (castleSpawn.isPresent()) {
            class_2338 chosen = castleSpawn.get();
            GotMod.LOGGER.info("Using kingdom castle spawn at ({}, {}, {}) biome={} kingdom={}", new Object[]{chosen.method_10263(), chosen.method_10264(), chosen.method_10260(), GotCharacters.getBiomeName(world, chosen), request.kingdomId()});
            return chosen;
        }
        if (request.spawnBiomes().isEmpty()) {
            GotMod.LOGGER.info("Using default world spawn {} because no spawn biomes were requested", (Object)world.method_43126());
            return world.method_43126();
        }
        class_1966 class_19662 = world.method_14178().method_12129().method_12098();
        if (class_19662 instanceof MapBiomeSource) {
            MapBiomeSource mapBiomeSource = (MapBiomeSource)class_19662;
            List<class_2338> candidates = mapBiomeSource.findSpawnCandidates(request.spawnBiomes(), 24);
            GotMod.LOGGER.info("Found {} spawn candidates for biomes {}", (Object)candidates.size(), request.spawnBiomes());
            for (class_2338 candidate : candidates) {
                GotMod.LOGGER.info("Checking spawn candidate ({}, {}) base_biome={}", new Object[]{candidate.method_10263(), candidate.method_10260(), GotCharacters.getBiomeName(world, candidate)});
                Optional<class_2338> resolved = GotCharacters.findNearbySpawnInBiomes(world, candidate, request.allowedBiomes());
                if (!resolved.isPresent()) continue;
                class_2338 chosen = resolved.get();
                GotMod.LOGGER.info("Resolved spawn candidate to safe position ({}, {}, {}) biome={}", new Object[]{chosen.method_10263(), chosen.method_10264(), chosen.method_10260(), GotCharacters.getBiomeName(world, chosen)});
                return resolved.get();
            }
        }
        GotMod.LOGGER.warn("Falling back to world spawn {} for requested biomes {}", (Object)world.method_43126(), request.spawnBiomes());
        return world.method_43126();
    }

    private static Optional<class_2338> findSpawnNearKingdomCastle(class_3218 world, PendingSpawnRequest request) {
        if (request.kingdomId().isBlank()) {
            return Optional.empty();
        }
        List<class_2338> anchors = GotCastles.getIndexedCastleAnchorsForKingdom(request.kingdomId());
        if (anchors.isEmpty()) {
            return Optional.empty();
        }
        ArrayList<class_2338> shuffled = new ArrayList<class_2338>(anchors);
        Collections.shuffle(shuffled, new Random(world.field_9229.method_43055()));
        for (class_2338 castleAnchor : shuffled) {
            Optional<class_2338> spawn = GotCharacters.findSpawnAroundCastleAnchor(world, castleAnchor, request.allowedBiomes());
            if (!spawn.isPresent()) continue;
            return spawn;
        }
        return Optional.empty();
    }

    private static Optional<class_2338> findSpawnAroundCastleAnchor(class_3218 world, class_2338 castleAnchor, List<class_5321<class_1959>> allowedBiomes) {
        int minDistanceSq = 810000;
        int maxDistanceSq = 1440000;
        for (int attempt = 0; attempt < 48; ++attempt) {
            int y;
            int dz;
            double angle = world.field_9229.method_43058() * (Math.PI * 2);
            int distance = world.field_9229.method_39332(900, 1200);
            int x = castleAnchor.method_10263() + class_3532.method_15357((double)(Math.cos(angle) * (double)distance));
            int z = castleAnchor.method_10260() + class_3532.method_15357((double)(Math.sin(angle) * (double)distance));
            int dx = x - castleAnchor.method_10263();
            int distanceSq = dx * dx + (dz = z - castleAnchor.method_10260()) * dz;
            if (distanceSq < minDistanceSq || distanceSq > maxDistanceSq || (y = GotCharacters.findSafeTeleportY(world, x, z, allowedBiomes)) <= world.method_31607() || y <= world.method_8615()) continue;
            class_2338 spawnPos = new class_2338(x, y, z);
            if (!allowedBiomes.isEmpty() && allowedBiomes.stream().noneMatch(key -> world.method_23753(spawnPos).method_40225(key))) continue;
            return Optional.of(spawnPos);
        }
        int ringStep = 64;
        for (int radius = 900; radius <= 1200; radius += ringStep) {
            for (int angleStep = 0; angleStep < 16; ++angleStep) {
                int z;
                double angle = Math.PI * 2 * (double)angleStep / 16.0;
                int x = castleAnchor.method_10263() + class_3532.method_15357((double)(Math.cos(angle) * (double)radius));
                int y = GotCharacters.findSafeTeleportY(world, x, z = castleAnchor.method_10260() + class_3532.method_15357((double)(Math.sin(angle) * (double)radius)), allowedBiomes);
                if (y <= world.method_31607() || y <= world.method_8615()) continue;
                class_2338 spawnPos = new class_2338(x, y, z);
                if (!allowedBiomes.isEmpty() && allowedBiomes.stream().noneMatch(key -> world.method_23753(spawnPos).method_40225(key))) continue;
                return Optional.of(spawnPos);
            }
        }
        return Optional.empty();
    }

    private static int findSafeTeleportY(class_3218 world, int x, int z, List<class_5321<class_1959>> allowedBiomes) {
        world.method_8497(x >> 4, z >> 4);
        int surfaceFeetY = GotCharacters.findSafeFeetYInColumn(world, x, z, allowedBiomes);
        if (surfaceFeetY > world.method_31607()) {
            return surfaceFeetY;
        }
        int startY = world.method_8624(class_2902.class_2903.field_13203, x, z);
        int minGroundY = Math.max(world.method_31607(), startY - 8);
        for (int groundY = startY - 1; groundY >= minGroundY; --groundY) {
            boolean validBiome;
            class_2338 groundPos = new class_2338(x, groundY, z);
            class_2338 feetPos = groundPos.method_10084();
            class_2338 headPos = feetPos.method_10084();
            class_2338 biomePos = feetPos;
            class_2680 groundState = world.method_8320(groundPos);
            class_2680 feetState = world.method_8320(feetPos);
            class_2680 headState = world.method_8320(headPos);
            boolean bl = validBiome = allowedBiomes.isEmpty() || allowedBiomes.stream().anyMatch(key -> world.method_23753(biomePos).method_40225(key));
            if (!validBiome || !GotCharacters.isSafeSpawnGround(world, groundPos, groundState) || !GotCharacters.isSafeSpawnSpace(feetState, headState)) continue;
            return feetPos.method_10264();
        }
        return Math.max(world.method_8615() + 1, startY);
    }

    private static int findSafeFeetYInColumn(class_3218 world, int x, int z, List<class_5321<class_1959>> allowedBiomes) {
        world.method_8497(x >> 4, z >> 4);
        int topY = world.method_8624(class_2902.class_2903.field_13203, x, z);
        int maxGroundY = Math.min(world.method_31600() - 2, topY);
        int minGroundY = Math.max(world.method_31607(), maxGroundY - 8);
        for (int groundY = maxGroundY; groundY >= minGroundY; --groundY) {
            boolean validBiome;
            class_2338 groundPos = new class_2338(x, groundY, z);
            class_2338 feetPos = groundPos.method_10084();
            class_2338 headPos = feetPos.method_10084();
            boolean bl = validBiome = allowedBiomes.isEmpty() || allowedBiomes.stream().anyMatch(key -> world.method_23753(feetPos).method_40225(key));
            if (!validBiome || !GotCharacters.isSafeSpawnGround(world, groundPos, world.method_8320(groundPos)) || !GotCharacters.isSafeSpawnSpace(world.method_8320(feetPos), world.method_8320(headPos))) continue;
            return feetPos.method_10264();
        }
        return world.method_31607();
    }

    private static boolean isSafeSpawnSpace(class_2680 feetState, class_2680 headState) {
        return feetState.method_26215() && headState.method_26215();
    }

    private static boolean isSafeSpawnGround(class_3218 world, class_2338 groundPos, class_2680 groundState) {
        if (!groundState.method_51366()) {
            return false;
        }
        if (!world.method_8316(groundPos).method_15769()) {
            return false;
        }
        return !groundState.method_27852(class_2246.field_10092) && !groundState.method_27852(class_2246.field_17350) && !groundState.method_27852(class_2246.field_23860);
    }

    private static void clearSpawnSpace(class_3218 world, class_2338 feetPos) {
        for (int offset = 0; offset <= 3; ++offset) {
            class_2338 pos = feetPos.method_10086(offset);
            class_2680 state = world.method_8320(pos);
            if (state.method_26215()) continue;
            world.method_8652(pos, class_2246.field_10124.method_9564(), 3);
        }
    }

    private static Optional<class_2338> findNearbySpawnInBiomes(class_3218 world, class_2338 center, List<class_5321<class_1959>> allowedBiomes) {
        int[] radii;
        for (int radius : radii = new int[]{0, 64, 128, 256, 384, 512, 768, 1024, 1536, 2048}) {
            int step = radius == 0 ? 1 : 64;
            for (int x = center.method_10263() - radius; x <= center.method_10263() + radius; x += step) {
                for (int z = center.method_10260() - radius; z <= center.method_10260() + radius; z += step) {
                    int y;
                    if (radius > 0 && Math.abs(x - center.method_10263()) < radius && Math.abs(z - center.method_10260()) < radius) continue;
                    class_2338 pos = new class_2338(x, 0, z);
                    if (allowedBiomes.stream().noneMatch(key -> world.method_23753(pos).method_40225(key)) || (y = GotCharacters.findSafeTeleportY(world, x, z, allowedBiomes)) <= world.method_8615()) continue;
                    return Optional.of(new class_2338(x, y, z));
                }
            }
        }
        return Optional.empty();
    }

    private static String getBiomeName(class_3218 world, class_2338 pos) {
        return world.method_23753(pos).method_40230().map(key -> key.method_29177().toString()).orElse("unknown");
    }

    private static List<class_5321<class_1959>> getAllowedSpawnBiomes(List<String> spawnBiomes) {
        ArrayList<class_5321> biomeKeys = new ArrayList<class_5321>();
        for (String biomePath : spawnBiomes) {
            biomeKeys.add(class_5321.method_29179((class_5321)class_7924.field_41236, (class_2960)class_2960.method_60655((String)"agob", (String)biomePath)));
        }
        return List.copyOf(biomeKeys);
    }

    private record PendingSpawnRequest(List<String> spawnBiomes, List<class_5321<class_1959>> allowedBiomes, String kingdomId) {
        private static PendingSpawnRequest defaultSpawn() {
            return new PendingSpawnRequest(List.of(), List.of(), "");
        }

        private static PendingSpawnRequest optionSpawn(List<String> spawnBiomes, String kingdomId) {
            return new PendingSpawnRequest(List.copyOf(spawnBiomes), GotCharacters.getAllowedSpawnBiomes(spawnBiomes), kingdomId == null ? "" : kingdomId);
        }
    }
}

