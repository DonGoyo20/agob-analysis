/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
 *  net.fabricmc.fabric.api.networking.v1.PacketSender
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_1297
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.house;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.castle.HouseLordWorldState;
import trep.got.castle.KingdomLeaderWorldState;
import trep.got.castle.PendingCastleOwnershipWorldState;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.NpcAppearanceProfile;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.entity.AbstractDynastyNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.family.DynastyNpcWorldState;
import trep.got.house.HouseLineageService;
import trep.got.house.HouseLineageWorldState;
import trep.got.house.PlayerHouseDefinition;
import trep.got.house.PlayerHouseWorldState;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationTitle;
import trep.got.network.PlayerHouseStatePayload;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotCharacters;
import trep.got.registry.GotDynastyNpcs;
import trep.got.registry.GotNpcMarriage;
import trep.got.relations.PlayerMarriageHolder;
import trep.got.relations.PlayerMarriageProfile;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.PlayerSkillProfile;
import trep.got.skill.SkillAttributeType;
import trep.got.world.dimension.GotDimensionKeys;

public final class PlayerHouseService {
    private static final Map<MinecraftServer, SyncTracker> SYNC_TRACKERS = new IdentityHashMap<MinecraftServer, SyncTracker>();
    private static boolean initialized;

    private PlayerHouseService() {
    }

    public static void initialize() {
        if (initialized) {
            return;
        }
        initialized = true;
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            SyncTracker tracker = SYNC_TRACKERS.get(server);
            if (tracker != null) {
                tracker.forget(handler.field_14140.method_5667());
            }
            PlayerHouseService.markDirty(server);
            server.execute(() -> PlayerHouseService.broadcastIfChanged(server));
        });
        ServerLifecycleEvents.SERVER_STOPPED.register(SYNC_TRACKERS::remove);
    }

    @Nullable
    public static PlayerHouseDefinition get(class_3218 world, String houseId) {
        return world == null ? null : PlayerHouseWorldState.get(world).getByHouseId(houseId);
    }

    @Nullable
    public static PlayerHouseDefinition getForOwner(class_3218 world, UUID ownerUuid) {
        return world == null || ownerUuid == null ? null : PlayerHouseWorldState.get(world).getByOwnerUuid(ownerUuid.toString());
    }

    @Nullable
    public static PlayerHouseDefinition getForMember(class_3218 world, UUID memberUuid) {
        return world == null || memberUuid == null ? null : PlayerHouseWorldState.get(world).getByMemberUuid(memberUuid.toString());
    }

    @Nullable
    public static PlayerHouseDefinition getForParticipant(class_3218 world, UUID playerUuid) {
        return world == null || playerUuid == null ? null : PlayerHouseWorldState.get(world).getByParticipantUuid(playerUuid.toString());
    }

    public static void applyHouseSelection(class_3222 player, String kingdomId, String houseId) {
        if (player == null) {
            return;
        }
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        character.setSelectedKingdomId(kingdomId);
        character.setSelectedOptionId(houseId);
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (marriage.isMarried()) {
            marriage.setSpouseKingdomId(kingdomId);
            marriage.setSpouseHouseId(houseId);
        }
        PlayerHouseService.syncDynastyIdentity(player, kingdomId, houseId);
        GotCastles.updatePlayerOwnedCastlesToKingdom(player.method_51469(), player.method_5667(), kingdomId, houseId);
        GotCastles.updateDynastyOwnedCastlesForPlayer(player.method_51469(), player);
        GotCharacters.sendCharacterSelectionState(player);
        GotCastles.syncExternalClaimOutlines(player.method_51469());
        GotCastles.syncCastleMapToPlayers(player.method_51469());
    }

    public static PlayerHouseDefinition createOrReplace(class_3222 player, String kingdomId, String lordHouseId, String displayName, int[] sigilColors) {
        PlayerHouseDefinition existing = PlayerHouseService.getForOwner(player.method_51469(), player.method_5667());
        PlayerHouseDefinition definition = new PlayerHouseDefinition(player.method_5845(), PlayerHouseService.createHouseId(player.method_5667()), kingdomId, lordHouseId, displayName, sigilColors, existing == null ? Set.of() : existing.memberUuids());
        PlayerHouseWorldState.get(player.method_51469()).put(definition);
        return definition;
    }

    public static boolean isCustomHouseId(String houseId) {
        return houseId != null && houseId.startsWith("player_house_");
    }

    public static void syncTo(class_3222 player) {
        class_3218 stateWorld = PlayerHouseService.resolveHouseStateWorld(player.method_5682(), player.method_51469());
        ServerPlayNetworking.send((class_3222)player, (class_8710)PlayerHouseService.createPayload(stateWorld));
        PlayerHouseService.tracker(player.method_5682()).markSent(player, stateWorld);
    }

    public static void syncTo(class_3222 player, PacketSender sender) {
        class_3218 stateWorld = PlayerHouseService.resolveHouseStateWorld(player.method_5682(), player.method_51469());
        sender.sendPacket((class_8710)PlayerHouseService.createPayload(stateWorld));
        PlayerHouseService.tracker(player.method_5682()).markSent(player, stateWorld);
    }

    public static void broadcast(MinecraftServer server) {
        PlayerHouseService.markDirty(server);
        PlayerHouseService.broadcastIfChanged(server);
    }

    public static void markDirty(MinecraftServer server) {
        if (server == null) {
            return;
        }
        PlayerHouseService.tracker(server).markDirty();
    }

    public static void broadcastIfChanged(MinecraftServer server) {
        if (server == null) {
            return;
        }
        List players = server.method_3760().method_14571();
        if (players.isEmpty()) {
            return;
        }
        SyncTracker tracker = PlayerHouseService.tracker(server);
        tracker.observeSourceSignature(PlayerHouseService.currentSourceSignature(server, players));
        boolean needsPayload = false;
        for (Object player : players) {
            class_3218 stateWorld;
            if (!tracker.needsSync((class_3222)player, stateWorld = PlayerHouseService.resolveHouseStateWorld(server, player.method_51469()))) continue;
            needsPayload = true;
            break;
        }
        if (!needsPayload) {
            return;
        }
        IdentityHashMap<class_3218, PlayerHouseStatePayload> payloadsByWorld = new IdentityHashMap<class_3218, PlayerHouseStatePayload>();
        for (class_3222 player : players) {
            class_3218 stateWorld = PlayerHouseService.resolveHouseStateWorld(server, player.method_51469());
            payloadsByWorld.computeIfAbsent(stateWorld, PlayerHouseService::createPayload);
        }
        long postBuildSourceSignature = PlayerHouseService.currentSourceSignature(server, players);
        tracker.observeSourceSignature(postBuildSourceSignature);
        for (class_3222 player : players) {
            class_3218 stateWorld;
            if (!tracker.needsSync(player, stateWorld = PlayerHouseService.resolveHouseStateWorld(server, player.method_51469()))) continue;
            ServerPlayNetworking.send((class_3222)player, (class_8710)((class_8710)payloadsByWorld.get(stateWorld)));
            tracker.markSent(player, stateWorld);
        }
        tracker.recordSourceSignature(postBuildSourceSignature);
    }

    public static void syncOnJoin(MinecraftServer server, class_3222 joiningPlayer, PacketSender sender) {
        if (server == null || joiningPlayer == null) {
            return;
        }
        List players = server.method_3760().method_14571();
        if (players.isEmpty()) {
            return;
        }
        PlayerHouseService.markDirty(server);
        SyncTracker tracker = PlayerHouseService.tracker(server);
        IdentityHashMap<class_3218, PlayerHouseStatePayload> payloadsByWorld = new IdentityHashMap<class_3218, PlayerHouseStatePayload>();
        for (class_3222 player : players) {
            class_3218 stateWorld = PlayerHouseService.resolveHouseStateWorld(server, player.method_51469());
            PlayerHouseStatePayload payload = payloadsByWorld.computeIfAbsent(stateWorld, PlayerHouseService::createPayload);
            if (player == joiningPlayer) {
                sender.sendPacket((class_8710)payload);
            } else {
                ServerPlayNetworking.send((class_3222)player, (class_8710)payload);
            }
            tracker.markSent(player, stateWorld);
        }
        tracker.recordSourceSignature(PlayerHouseService.currentSourceSignature(server, players));
    }

    public static PlayerHouseStatePayload createPayload(class_3218 world) {
        class_3218 stateWorld = PlayerHouseService.resolveHouseStateWorld(world == null ? null : world.method_8503(), world);
        if (stateWorld == null) {
            return new PlayerHouseStatePayload(List.of(), List.of(), List.of());
        }
        List<PlayerHouseStatePayload.Entry> entries = PlayerHouseWorldState.get(stateWorld).getAll().stream().map(definition -> new PlayerHouseStatePayload.Entry(definition.ownerUuid(), definition.houseId(), definition.kingdomId(), definition.lordHouseId(), definition.displayName(), definition.sigilColors())).toList();
        List<PlayerHouseStatePayload.MemberEntry> members = PlayerHouseService.createMemberEntries(stateWorld);
        members = new ArrayList<PlayerHouseStatePayload.MemberEntry>(members);
        members.addAll(PlayerHouseService.createAiLordSpouseEntries(stateWorld));
        members.addAll(HouseLineageService.createAiLineageMemberEntries(stateWorld));
        List<PlayerHouseStatePayload.HouseLordEntry> houseLords = PlayerHouseService.createHouseLordEntries(stateWorld);
        return new PlayerHouseStatePayload(entries, List.copyOf(members), houseLords);
    }

    public static class_3218 resolveHouseStateWorld(MinecraftServer server, class_3218 fallback) {
        class_3218 gotWorld;
        if (server != null && (gotWorld = server.method_3847(GotDimensionKeys.ICEANDFIRE)) != null) {
            return gotWorld;
        }
        return fallback;
    }

    private static List<PlayerHouseStatePayload.MemberEntry> createMemberEntries(class_3218 world) {
        if (world == null || world.method_8503() == null) {
            return List.of();
        }
        ArrayList<PlayerHouseStatePayload.MemberEntry> members = new ArrayList<PlayerHouseStatePayload.MemberEntry>();
        for (class_3222 player : world.method_8503().method_3760().method_14571()) {
            PlayerCharacterProfile characterProfile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
            String kingdomId = characterProfile.getSelectedKingdomId();
            String houseId = characterProfile.getSelectedOptionId();
            PlayerHouseDefinition customHouse = PlayerHouseService.getForParticipant(world, player.method_5667());
            if (customHouse != null) {
                kingdomId = customHouse.kingdomId();
                houseId = customHouse.houseId();
            }
            if (kingdomId.isBlank() || houseId.isBlank()) continue;
            ReputationTitle title = ((PlayerReputationHolder)player).got$getReputationProfile().getTitle();
            PlayerSkillProfile skillProfile = ((PlayerSkillHolder)player).got$getSkillProfile();
            List<String> roleIconIds = PlayerHouseService.roleIconsForPlayerMember(world, player, kingdomId, houseId);
            members.add(new PlayerHouseStatePayload.MemberEntry(player.method_5845(), player.method_5845(), player.method_5477().getString(), kingdomId, houseId, title == null ? "" : title.getDisplayName(), PlayerHouseService.resolvePlayerMarriageName(world, player), "", "", 0, "", roleIconIds, false, 0L, false, "", 1, 1, 0, PlayerHouseService.skillPoints(skillProfile), PlayerHouseService.skillMaxPoints(skillProfile)));
            PlayerHouseService.appendMarriedNpcEntry(world, player, members, kingdomId, houseId);
            PlayerHouseService.appendDynastyEntries(world, player, members, kingdomId, houseId);
        }
        return List.copyOf(members);
    }

    private static void appendMarriedNpcEntry(class_3218 world, class_3222 player, List<PlayerHouseStatePayload.MemberEntry> members, String kingdomId, String houseId) {
        AbstractKingdomNpcEntity npc;
        if (world == null || player == null) {
            return;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (!marriage.isMarried()) {
            return;
        }
        String spouseId = marriage.getSpouseNpcId();
        String spouseName = marriage.getSpouseName();
        class_1297 spouse = marriage.getSpouseNpcUuid() == null ? null : world.method_14190(marriage.getSpouseNpcUuid());
        AbstractKingdomNpcEntity spouseNpc = spouse instanceof AbstractKingdomNpcEntity ? (npc = (AbstractKingdomNpcEntity)spouse) : null;
        DynastyNpcWorldState.FamilyMemberProfile dynastyProfile = DynastyNpcWorldState.get(world).get(spouseId);
        if (spouseNpc != null) {
            spouseName = spouseNpc.method_5477().getString();
        }
        NpcAppearanceProfile appearance = spouseNpc == null ? marriage.getSpouseAppearance() : spouseNpc.getNpcAppearance();
        String locationName = GotNpcMarriage.resolveMarriageLocationName(world, player);
        members.add(new PlayerHouseStatePayload.MemberEntry(spouseId, player.method_5845(), spouseName, kingdomId, houseId, "", player.method_5477().getString(), locationName, (String)(dynastyProfile == null ? "" : dynastyProfile.ownerPlayerName() + " / " + dynastyProfile.otherParentName()), 0, dynastyProfile == null ? "" : dynastyProfile.stage().id(), List.of("married"), marriage.isSpouseInParty(), marriage.getSummonCooldownEndsAtMs(), true, appearance.gender(), appearance.skinVariant(), appearance.hairVariant(), appearance.clothesVariant(), new int[SkillAttributeType.ALL.size()], PlayerHouseService.defaultSkillMaxPoints()));
    }

    private static void appendDynastyEntries(class_3218 world, class_3222 player, List<PlayerHouseStatePayload.MemberEntry> members, String kingdomId, String houseId) {
        for (DynastyNpcWorldState.FamilyMemberProfile profile : GotDynastyNpcs.membersForOwner(world, player.method_5667())) {
            members.add(new PlayerHouseStatePayload.MemberEntry(profile.id(), player.method_5845(), profile.displayName(), kingdomId, houseId, "", "", GotCastles.resolveCastleLocationName(world, profile.castleId()), profile.ownerPlayerName() + " / " + profile.otherParentName(), Math.max(0, (int)(GotDynastyNpcs.currentTrackedDay(world) - profile.bornDay())), profile.stage().id(), List.of("child"), false, profile.summonCooldownEndsAtMs(), true, "", 1, 1, 0, PlayerHouseService.skillPoints(profile.attributes().skillPoints()), PlayerHouseService.defaultSkillMaxPoints()));
        }
    }

    private static String resolvePlayerMarriageName(class_3218 world, class_3222 player) {
        String string;
        class_1297 spouse;
        if (world == null || player == null) {
            return "";
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (!marriage.isMarried()) {
            return "";
        }
        class_1297 class_12972 = spouse = marriage.getSpouseNpcUuid() == null ? null : world.method_14190(marriage.getSpouseNpcUuid());
        if (spouse instanceof AbstractKingdomNpcEntity) {
            AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)spouse;
            string = npc.method_5477().getString();
        } else {
            string = marriage.getSpouseName();
        }
        return string;
    }

    private static void syncDynastyIdentity(class_3222 player, String kingdomId, String houseId) {
        String displayName;
        class_3218 world = player.method_51469();
        DynastyNpcWorldState state = DynastyNpcWorldState.get(world);
        String houseName = GotNpcMarriage.resolveHouseName(world, houseId, kingdomId);
        String surname = GotNpcMarriage.normalizeHouseSurname(houseName);
        for (DynastyNpcWorldState.FamilyMemberProfile profile : state.getByOwner(player.method_5845())) {
            displayName = surname.isBlank() ? profile.displayName() : GotNpcMarriage.extractFirstName(profile.displayName()) + " " + surname;
            DynastyNpcWorldState.FamilyMemberProfile updated = profile.withKingdomHouseIdentity(kingdomId, houseId, displayName, player.method_5477().getString());
            state.put(updated);
            class_1297 class_12972 = world.method_14190(updated.entityUuid());
            if (!(class_12972 instanceof AbstractDynastyNpcEntity)) continue;
            AbstractDynastyNpcEntity dynastyNpc = (AbstractDynastyNpcEntity)class_12972;
            class_2960 parsedKingdom = class_2960.method_12829((String)kingdomId);
            dynastyNpc.setDynastyKingdomId(parsedKingdom);
            dynastyNpc.setDynastyHouseId(houseId);
            dynastyNpc.setOwnerPlayerName(player.method_5477().getString());
            dynastyNpc.method_5665((class_2561)class_2561.method_43470((String)displayName));
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (marriage.getSpouseNpcUuid() != null && (displayName = world.method_14190(marriage.getSpouseNpcUuid())) instanceof AbstractDynastyNpcEntity) {
            AbstractDynastyNpcEntity dynastySpouse = (AbstractDynastyNpcEntity)((Object)displayName);
            class_2960 parsedKingdom = class_2960.method_12829((String)kingdomId);
            dynastySpouse.setDynastyKingdomId(parsedKingdom);
            dynastySpouse.setDynastyHouseId(houseId);
        }
    }

    private static List<PlayerHouseStatePayload.HouseLordEntry> createHouseLordEntries(class_3218 world) {
        if (world == null) {
            return List.of();
        }
        ArrayList<PlayerHouseStatePayload.HouseLordEntry> entries = new ArrayList<PlayerHouseStatePayload.HouseLordEntry>();
        for (CharacterSelectionOption option : GotCharacterOptions.getAll()) {
            String kingdomId = option.kingdomId().toString();
            for (HouseLordWorldState.HouseLordProfile lord : GotCastles.getHouseLordProfiles(world, kingdomId, option.id())) {
                entries.add(new PlayerHouseStatePayload.HouseLordEntry(kingdomId, option.id(), lord.displayName(), lord.hasSpouse() ? lord.spouseDisplayName() : "", GotCastles.resolveHouseLordLocationName(world, kingdomId, option.id(), lord.displayName()), PlayerHouseService.roleIconsForHouseLord(world, kingdomId, option.id(), lord), PlayerHouseService.skillPoints(lord.attributes().skillPoints())));
            }
        }
        return List.copyOf(entries);
    }

    private static List<PlayerHouseStatePayload.MemberEntry> createAiLordSpouseEntries(class_3218 world) {
        if (world == null) {
            return List.of();
        }
        ArrayList<PlayerHouseStatePayload.MemberEntry> entries = new ArrayList<PlayerHouseStatePayload.MemberEntry>();
        for (CharacterSelectionOption option : GotCharacterOptions.getAll()) {
            String kingdomId = option.kingdomId().toString();
            for (HouseLordWorldState.HouseLordProfile lord : GotCastles.getHouseLordProfiles(world, kingdomId, option.id())) {
                if (!lord.hasSpouse()) continue;
                entries.add(new PlayerHouseStatePayload.MemberEntry(lord.spouseId(), "", lord.spouseDisplayName(), kingdomId, option.id(), "", lord.displayName(), GotCastles.resolveHouseLordLocationName(world, kingdomId, option.id(), lord.displayName()), "", 0, "", List.of(), false, 0L, true, lord.spouseAppearance().gender(), lord.spouseAppearance().skinVariant(), lord.spouseAppearance().hairVariant(), lord.spouseAppearance().clothesVariant(), new int[SkillAttributeType.ALL.size()], PlayerHouseService.defaultSkillMaxPoints()));
            }
        }
        return List.copyOf(entries);
    }

    private static List<String> roleIconsForHouseLord(class_3218 world, String kingdomId, String houseId, HouseLordWorldState.HouseLordProfile lord) {
        ArrayList<String> roleIcons = new ArrayList<String>();
        if (!"agob:the_nights_watch".equals(kingdomId) && !GotCastles.hasPlayerKingdomLeader(world, kingdomId, houseId) && GotCastles.isKingdomLeader(world, kingdomId, houseId, lord.displayName())) {
            roleIcons.add("king");
        }
        if (lord != null && lord.leader() && !GotCastles.hasPlayerHouseLeader(world, kingdomId, houseId)) {
            roleIcons.add("house_leader");
        }
        return List.copyOf(roleIcons);
    }

    private static List<String> roleIconsForPlayerMember(class_3218 world, class_3222 player, String kingdomId, String houseId) {
        if (world == null || player == null) {
            return List.of();
        }
        ArrayList<String> roleIcons = new ArrayList<String>();
        if (!"agob:the_nights_watch".equals(kingdomId) && GotCastles.isKingdomLeader(world, kingdomId, houseId, player.method_7334().getName())) {
            roleIcons.add("king");
        }
        if (GotCastles.isPlayerHouseLeader(world, player.method_5667(), kingdomId, houseId)) {
            roleIcons.add("house_leader");
        }
        return List.copyOf(roleIcons);
    }

    private static int[] skillPoints(PlayerSkillProfile profile) {
        int[] values = new int[SkillAttributeType.ALL.size()];
        for (int index = 0; index < SkillAttributeType.ALL.size(); ++index) {
            values[index] = profile.get(SkillAttributeType.ALL.get(index)).getPoints();
        }
        return values;
    }

    private static int[] skillMaxPoints(PlayerSkillProfile profile) {
        int[] values = new int[SkillAttributeType.ALL.size()];
        for (int index = 0; index < SkillAttributeType.ALL.size(); ++index) {
            values[index] = profile.get(SkillAttributeType.ALL.get(index)).getMaxPoints();
        }
        return values;
    }

    private static int[] skillPoints(Map<SkillAttributeType, Integer> skillPoints) {
        int[] values = new int[SkillAttributeType.ALL.size()];
        for (int index = 0; index < SkillAttributeType.ALL.size(); ++index) {
            values[index] = skillPoints.getOrDefault((Object)SkillAttributeType.ALL.get(index), 0);
        }
        return values;
    }

    private static int[] defaultSkillMaxPoints() {
        int[] values = new int[SkillAttributeType.ALL.size()];
        for (int index = 0; index < values.length; ++index) {
            values[index] = 100;
        }
        return values;
    }

    private static String createHouseId(UUID ownerUuid) {
        String compactUuid = ownerUuid == null ? "unknown" : ownerUuid.toString().replace("-", "");
        return "player_house_" + compactUuid.toLowerCase(Locale.ROOT);
    }

    private static SyncTracker tracker(MinecraftServer server) {
        return SYNC_TRACKERS.computeIfAbsent(server, ignored -> new SyncTracker());
    }

    private static long currentSourceSignature(MinecraftServer server, List<class_3222> players) {
        long signature = PlayerHouseService.onlineMemberSignature(server, players);
        ArrayList<class_3218> stateWorlds = new ArrayList<class_3218>();
        for (class_3222 player : players) {
            class_3218 stateWorld = PlayerHouseService.resolveHouseStateWorld(server, player.method_51469());
            if (stateWorld == null || !stateWorlds.stream().noneMatch(existing -> existing == stateWorld)) continue;
            stateWorlds.add(stateWorld);
        }
        for (class_3218 stateWorld : stateWorlds) {
            signature = PlayerHouseService.mix(signature, stateWorld.method_27983().method_29177().hashCode());
            signature = PlayerHouseService.mix(signature, PlayerHouseWorldState.get(stateWorld).getRevision());
            signature = PlayerHouseService.mix(signature, DynastyNpcWorldState.get(stateWorld).getRevision());
            signature = PlayerHouseService.mix(signature, HouseLineageWorldState.get(stateWorld).getRevision());
            signature = PlayerHouseService.mix(signature, HouseLordWorldState.get(stateWorld).getRevision());
            signature = PlayerHouseService.mix(signature, KingdomLeaderWorldState.get(stateWorld).getRevision());
            signature = PlayerHouseService.mix(signature, PendingCastleOwnershipWorldState.get(stateWorld).getRevision());
            signature = PlayerHouseService.mixCastleRoleSources(signature, CastleWorldState.get(stateWorld));
        }
        return signature;
    }

    private static long mixCastleRoleSources(long signature, CastleWorldState state) {
        List<CastleInstance> castles = state.getAll().stream().sorted(Comparator.comparing(CastleInstance::getId)).toList();
        signature = PlayerHouseService.mix(signature, castles.size());
        for (CastleInstance castle : castles) {
            signature = PlayerHouseService.mix(signature, castle.getId().hashCode());
            signature = PlayerHouseService.mix(signature, castle.getDefinitionId().hashCode());
            signature = PlayerHouseService.mix(signature, castle.getTier().ordinal());
            signature = PlayerHouseService.mix(signature, castle.getTitle() == null ? 0L : (long)castle.getTitle().hashCode());
            signature = PlayerHouseService.mix(signature, castle.getOwnerPlayerUuid() == null ? 0L : (long)castle.getOwnerPlayerUuid().hashCode());
            signature = PlayerHouseService.mix(signature, castle.getOwnerKingdomId().hashCode());
            signature = PlayerHouseService.mix(signature, castle.getOwnerHouseId().hashCode());
            signature = PlayerHouseService.mix(signature, castle.getLordName().hashCode());
        }
        return signature;
    }

    private static long onlineMemberSignature(MinecraftServer server, List<class_3222> players) {
        long signature = -3750763034362895579L;
        for (class_3222 player : players) {
            class_1297 spouse;
            signature = PlayerHouseService.mix(signature, player.method_5667().hashCode());
            signature = PlayerHouseService.mix(signature, player.method_5477().getString().hashCode());
            PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
            signature = PlayerHouseService.mix(signature, character.getSelectedKingdomId().hashCode());
            signature = PlayerHouseService.mix(signature, character.getSelectedOptionId().hashCode());
            ReputationTitle title = ((PlayerReputationHolder)player).got$getReputationProfile().getTitle();
            signature = PlayerHouseService.mix(signature, title == null ? 0L : (long)(title.ordinal() + 1));
            PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
            signature = PlayerHouseService.mix(signature, marriage.getSpouseNpcId().hashCode());
            signature = PlayerHouseService.mix(signature, marriage.getSpouseNpcUuid() == null ? 0L : (long)marriage.getSpouseNpcUuid().hashCode());
            signature = PlayerHouseService.mix(signature, marriage.getSpouseName().hashCode());
            signature = PlayerHouseService.mix(signature, marriage.getSpouseKingdomId().hashCode());
            signature = PlayerHouseService.mix(signature, marriage.getSpouseHouseId().hashCode());
            signature = PlayerHouseService.mix(signature, marriage.getSpouseFamilyStageId().hashCode());
            signature = PlayerHouseService.mix(signature, marriage.getSpouseAppearance().hashCode());
            signature = PlayerHouseService.mix(signature, marriage.isSpouseInParty() ? 1L : 0L);
            signature = PlayerHouseService.mix(signature, marriage.getSummonCooldownEndsAtMs());
            class_3218 stateWorld = PlayerHouseService.resolveHouseStateWorld(server, player.method_51469());
            class_1297 class_12972 = spouse = marriage.getSpouseNpcUuid() == null ? null : stateWorld.method_14190(marriage.getSpouseNpcUuid());
            if (spouse instanceof AbstractKingdomNpcEntity) {
                AbstractKingdomNpcEntity spouseNpc = (AbstractKingdomNpcEntity)spouse;
                signature = PlayerHouseService.mix(signature, spouseNpc.method_5477().getString().hashCode());
                signature = PlayerHouseService.mix(signature, spouseNpc.getNpcAppearance().hashCode());
            }
            PlayerSkillProfile skills = ((PlayerSkillHolder)player).got$getSkillProfile();
            for (SkillAttributeType type : SkillAttributeType.ALL) {
                signature = PlayerHouseService.mix(signature, skills.get(type).getPoints());
                signature = PlayerHouseService.mix(signature, skills.get(type).getMaxPoints());
            }
        }
        return signature;
    }

    private static long mix(long signature, long value) {
        return (signature ^ value) * 1099511628211L;
    }

    private static final class SyncTracker {
        private final Map<UUID, RecipientRevision> recipients = new HashMap<UUID, RecipientRevision>();
        private long revision = 1L;
        private long sourceSignature;
        private boolean sourceSignatureInitialized;

        private SyncTracker() {
        }

        private void markDirty() {
            if (this.revision == Long.MAX_VALUE) {
                this.revision = 1L;
                this.recipients.clear();
                return;
            }
            ++this.revision;
        }

        private boolean needsSync(class_3222 player, class_3218 stateWorld) {
            RecipientRevision sent = this.recipients.get(player.method_5667());
            return sent == null || sent.revision != this.revision || sent.stateWorld != stateWorld;
        }

        private void observeSourceSignature(long sourceSignature) {
            if (!this.sourceSignatureInitialized) {
                this.sourceSignature = sourceSignature;
                this.sourceSignatureInitialized = true;
                return;
            }
            if (this.sourceSignature != sourceSignature) {
                this.sourceSignature = sourceSignature;
                this.markDirty();
            }
        }

        private void recordSourceSignature(long sourceSignature) {
            this.sourceSignature = sourceSignature;
            this.sourceSignatureInitialized = true;
        }

        private void markSent(class_3222 player, class_3218 stateWorld) {
            this.recipients.put(player.method_5667(), new RecipientRevision(stateWorld, this.revision));
        }

        private void forget(UUID playerUuid) {
            this.recipients.remove(playerUuid);
        }
    }

    private record RecipientRevision(class_3218 stateWorld, long revision) {
    }
}

