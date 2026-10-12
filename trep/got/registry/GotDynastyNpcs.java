/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3730
 *  net.minecraft.class_5425
 *  net.minecraft.class_5819
 *  net.minecraft.server.MinecraftServer
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3730;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import trep.got.castle.CastleInstance;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.NpcAttributeProfile;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerGeneticTrait;
import trep.got.character.PlayerPersonalityTrait;
import trep.got.day.DailyTaskContext;
import trep.got.day.DailyTaskRegistry;
import trep.got.entity.AbstractDynastyNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.ChildNpcEntity;
import trep.got.family.DynastyNpcWorldState;
import trep.got.house.PlayerHouseService;
import trep.got.kingdom.Kingdom;
import trep.got.registry.GotCastleOwnerNames;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotEntities;
import trep.got.registry.GotGameRules;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotNpcMarriage;
import trep.got.relations.PlayerMarriageHolder;
import trep.got.relations.PlayerMarriageProfile;
import trep.got.relations.PlayerNpcRelationsHolder;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.PlayerSkillProfile;
import trep.got.skill.SkillAttributeType;
import trep.got.village.VillageInstance;
import trep.got.village.VillageWorldState;
import trep.got.world.dimension.GotDimensionKeys;

public final class GotDynastyNpcs {
    public static final int TRY_FOR_BABY_COOLDOWN_DAYS = 7;
    private static final long FAMILY_SYNC_INTERVAL_TICKS = 100L;
    private static final long FAMILY_SUMMON_COOLDOWN_MS = 120000L;
    private static final int ADULT_AGE_DAYS = 10;
    private static final int BASE_RELATION = 20;

    private GotDynastyNpcs() {
    }

    public static void initialize() {
        PlayerHouseService.initialize();
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> PlayerHouseService.broadcast(newPlayer.method_5682()));
        ServerTickEvents.END_SERVER_TICK.register(GotDynastyNpcs::tickServer);
        DailyTaskRegistry.register(class_2960.method_60655((String)"agob", (String)"dynasty_npcs"), GotDynastyNpcs::runDailyDynastyTask);
    }

    public static long currentTrackedDay(@Nullable class_3218 world) {
        if (world == null) {
            return 0L;
        }
        return DynastyNpcWorldState.get(world).getTrackedDay();
    }

    public static FamilyStage familyStage(@Nullable AbstractKingdomNpcEntity npc) {
        if (npc instanceof AbstractDynastyNpcEntity) {
            AbstractDynastyNpcEntity dynastyNpc = (AbstractDynastyNpcEntity)npc;
            return dynastyNpc.getFamilyStage();
        }
        return FamilyStage.STANDARD;
    }

    public static boolean isChildNpc(@Nullable AbstractKingdomNpcEntity npc) {
        return GotDynastyNpcs.familyStage(npc) == FamilyStage.CHILD;
    }

    public static boolean isDynastyNpc(@Nullable AbstractKingdomNpcEntity npc) {
        return GotDynastyNpcs.familyStage(npc) == FamilyStage.DYNASTY;
    }

    public static boolean canTryForBaby(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null || !GotNpcMarriage.isMarriedTo(player, npc)) {
            return false;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        return GotDynastyNpcs.currentTrackedDay(player.method_51469()) >= marriage.getBabyCooldownUnlockDay();
    }

    public static long remainingBabyCooldownDays(class_3222 player) {
        if (player == null || player.method_51469() == null) {
            return 0L;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        return Math.max(0L, marriage.getBabyCooldownUnlockDay() - GotDynastyNpcs.currentTrackedDay(player.method_51469()));
    }

    public static void handleTryForBabyAction(class_3222 player, AbstractKingdomNpcEntity spouse) {
        boolean success;
        if (player == null || spouse == null || player.method_51469() == null || !GotNpcMarriage.isMarriedTo(player, spouse)) {
            return;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        long currentDay = GotDynastyNpcs.currentTrackedDay(player.method_51469());
        if (marriage.getBabyCooldownUnlockDay() > currentDay) {
            player.method_7353((class_2561)class_2561.method_43470((String)("You must wait " + (marriage.getBabyCooldownUnlockDay() - currentDay) + " more days.")).method_27692(class_124.field_1061), true);
            return;
        }
        marriage.setBabyCooldownUnlockDay(currentDay + 7L);
        spouse.setBabyCooldownUnlockDay(marriage.getBabyCooldownUnlockDay());
        class_5819 random = player.method_59922();
        float roll = random.method_43057();
        boolean spouseDies = roll >= 0.99f;
        boolean bl = success = spouseDies || roll < 0.9f;
        if (!success) {
            player.method_7353((class_2561)class_2561.method_43470((String)"It was not the right time for a child.").method_27692(class_124.field_1065), true);
            PlayerHouseService.broadcast(player.method_5682());
            return;
        }
        DynastyNpcWorldState.FamilyMemberProfile childProfile = GotDynastyNpcs.createChildProfile(player, spouse);
        DynastyNpcWorldState.get(player.method_51469()).put(childProfile);
        GotDynastyNpcs.spawnOrRespawn(player.method_51469(), childProfile, player.method_24515(), true);
        GotDynastyNpcs.ensureInitialRelation(player, childProfile.id());
        if (spouseDies) {
            GotNpcMarriage.handleChildbirthDeath(player, spouse);
            player.method_7353((class_2561)class_2561.method_43470((String)(spouse.method_5477().getString() + " died in childbirth.")).method_27692(class_124.field_1079), false);
        } else {
            player.method_7353((class_2561)class_2561.method_43470((String)"A child has been born into your house.").method_27692(class_124.field_1060), true);
        }
        PlayerHouseService.broadcast(player.method_5682());
    }

    public static void syncEntityFromWorldState(class_3218 world, AbstractDynastyNpcEntity entity) {
        if (world == null || entity == null || entity.getPersistentNpcId().isBlank()) {
            return;
        }
        DynastyNpcWorldState.FamilyMemberProfile profile = DynastyNpcWorldState.get(world).get(entity.getPersistentNpcId());
        if (profile == null) {
            return;
        }
        String resolvedName = GotDynastyNpcs.createDynastyDisplayName(world, class_2960.method_60654((String)profile.kingdomId()), profile.houseId(), profile.ownerPlayerName(), profile.id());
        if (!resolvedName.equals(profile.displayName())) {
            DynastyNpcWorldState.get(world).put(profile.withDisplayName(resolvedName));
            PlayerHouseService.markDirty(world.method_8503());
            profile = DynastyNpcWorldState.get(world).get(profile.id());
        }
        GotDynastyNpcs.applyProfile(entity, profile);
    }

    public static void onFamilyNpcKilled(class_3218 world, AbstractDynastyNpcEntity entity) {
        if (world == null || entity == null || entity.getPersistentNpcId().isBlank()) {
            return;
        }
        DynastyNpcWorldState.FamilyMemberProfile profile = DynastyNpcWorldState.get(world).get(entity.getPersistentNpcId());
        if (profile == null) {
            return;
        }
        class_3222 owner = GotDynastyNpcs.resolveOwner(world, profile.ownerPlayerUuid());
        String updatedCastleId = profile.castleId();
        if (profile.stage() == FamilyStage.CHILD && owner != null) {
            updatedCastleId = GotDynastyNpcs.resolveChildCastleId(world, owner, profile.castleId());
        } else if (profile.stage() == FamilyStage.DYNASTY) {
            updatedCastleId = GotDynastyNpcs.resolveDynastyCastleId(world, GotDynastyNpcs.parseUuid(profile.ownerPlayerUuid()), profile.kingdomId(), profile.houseId(), profile.displayName(), profile.castleId());
        }
        DynastyNpcWorldState.get(world).put(profile.withCastleId(updatedCastleId));
        PlayerHouseService.broadcast(world.method_8503());
    }

    public static List<DynastyNpcWorldState.FamilyMemberProfile> membersForOwner(class_3218 world, UUID ownerUuid) {
        if (world == null || ownerUuid == null) {
            return List.of();
        }
        return DynastyNpcWorldState.get(world).getByOwner(ownerUuid.toString()).stream().filter(profile -> !GotDynastyNpcs.isBoundToAnotherPlayer(world, profile, ownerUuid.toString())).sorted(Comparator.comparing(DynastyNpcWorldState.FamilyMemberProfile::bornDay).thenComparing(DynastyNpcWorldState.FamilyMemberProfile::displayName)).toList();
    }

    public static List<DynastyNpcWorldState.FamilyMemberProfile> adultMembersForHouse(class_3218 world, String kingdomId, String houseId) {
        if (world == null || kingdomId == null || kingdomId.isBlank() || houseId == null || houseId.isBlank()) {
            return List.of();
        }
        return DynastyNpcWorldState.get(world).all().stream().filter(profile -> profile.stage() == FamilyStage.DYNASTY).filter(profile -> kingdomId.equals(profile.kingdomId()) && houseId.equals(profile.houseId())).filter(profile -> profile.boundMarriedPlayerUuid().isBlank()).filter(profile -> !GotDynastyNpcs.isCurrentlyMarried(world, profile.id())).sorted(Comparator.comparing(DynastyNpcWorldState.FamilyMemberProfile::displayName)).toList();
    }

    public static boolean isDynastyOwnedLordName(class_3218 world, UUID ownerUuid, String lordName) {
        if (world == null || ownerUuid == null || lordName == null || lordName.isBlank()) {
            return false;
        }
        return GotDynastyNpcs.membersForOwner(world, ownerUuid).stream().anyMatch(profile -> profile.stage() == FamilyStage.DYNASTY && lordName.equals(profile.displayName()));
    }

    public static void bindDynastyToMarriedPlayer(class_3218 world, String memberId, class_3222 marriedPlayer) {
        if (world == null || memberId == null || memberId.isBlank() || marriedPlayer == null) {
            return;
        }
        DynastyNpcWorldState.FamilyMemberProfile profile = DynastyNpcWorldState.get(world).get(memberId);
        if (profile == null) {
            return;
        }
        DynastyNpcWorldState.get(world).put(profile.withMarriageBinding(marriedPlayer.method_5845(), marriedPlayer.method_5477().getString()));
    }

    public static void clearDynastyMarriageBinding(class_3218 world, String memberId) {
        if (world == null || memberId == null || memberId.isBlank()) {
            return;
        }
        DynastyNpcWorldState.FamilyMemberProfile profile = DynastyNpcWorldState.get(world).get(memberId);
        if (profile == null || profile.boundMarriedPlayerUuid().isBlank()) {
            return;
        }
        DynastyNpcWorldState.get(world).put(profile.clearMarriageBinding());
    }

    private static void tickServer(MinecraftServer server) {
        if (server == null || (long)server.method_3780() % 100L != 0L) {
            return;
        }
        class_3218 world = server.method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return;
        }
        DynastyNpcWorldState state = DynastyNpcWorldState.get(world);
        List<DynastyNpcWorldState.FamilyMemberProfile> profiles = state.all();
        if (!profiles.isEmpty()) {
            LinkedHashMap<UUID, class_3222> eligibleOwners = new LinkedHashMap<UUID, class_3222>();
            long trackedDay = state.getTrackedDay();
            for (DynastyNpcWorldState.FamilyMemberProfile profile : profiles) {
                class_3222 owner;
                DynastyNpcWorldState.FamilyMemberProfile synced = GotDynastyNpcs.syncProfileHouseIdentity(world, profile, owner = GotDynastyNpcs.resolveOwner(world, profile.ownerPlayerUuid()));
                if (synced != profile) {
                    state.put(synced);
                    profile = synced;
                }
                if (profile.stage() == FamilyStage.CHILD && trackedDay - profile.bornDay() >= 10L) {
                    profile = profile.withStage(FamilyStage.DYNASTY);
                    state.put(profile);
                    GotDynastyNpcs.convertLoadedChildToDynasty(world, profile.id());
                }
                if (owner != null && profile.stage() == FamilyStage.DYNASTY) {
                    eligibleOwners.putIfAbsent(owner.method_5667(), owner);
                }
                GotDynastyNpcs.spawnOrRespawn(world, profile, null, false);
            }
            for (class_3222 owner : eligibleOwners.values()) {
                GotCastles.updateDynastyOwnedCastlesForPlayer(world, owner);
            }
        }
        PlayerHouseService.broadcastIfChanged(server);
    }

    private static void runDailyDynastyTask(class_3218 world, DailyTaskContext context) {
        if (!GotDimensionKeys.ICEANDFIRE.equals((Object)world.method_27983())) {
            return;
        }
        DynastyNpcWorldState.get(world).tick(world.method_8510(), world.method_8532(), GotGameRules.affectedByTimeSkip(world));
    }

    private static DynastyNpcWorldState.FamilyMemberProfile createChildProfile(class_3222 player, AbstractKingdomNpcEntity spouse) {
        class_2960 kingdomId = GotDynastyNpcs.resolvePlayerKingdomId(player);
        String houseId = GotNpcMarriage.resolveCurrentPlayerHouseId(player);
        String ownerName = player.method_5477().getString();
        String displayName = GotDynastyNpcs.createDynastyDisplayName(player.method_51469(), kingdomId, houseId, ownerName, spouse.getPersistentNpcId());
        String childId = "dynasty_" + player.method_5845().replace("-", "") + "_" + Math.abs((spouse.getPersistentNpcId() + ":" + GotDynastyNpcs.currentTrackedDay(player.method_51469()) + ":" + displayName).hashCode());
        return new DynastyNpcWorldState.FamilyMemberProfile(childId, UUID.nameUUIDFromBytes(childId.getBytes(StandardCharsets.UTF_8)), player.method_5845(), ownerName, spouse.method_5477().getString(), "", "", kingdomId.toString(), houseId, displayName, GotDynastyNpcs.currentTrackedDay(player.method_51469()), GotDynastyNpcs.resolveChildCastleId(player, spouse), FamilyStage.CHILD, GotDynastyNpcs.resolveOwnerSmallArms(player), 0L, GotDynastyNpcs.buildChildAttributes(player, spouse));
    }

    private static NpcAttributeProfile buildChildAttributes(class_3222 player, AbstractKingdomNpcEntity spouse) {
        EnumMap<SkillAttributeType, Integer> merged = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        Map<SkillAttributeType, Integer> spouseSkills = spouse.getNpcAttributes().skillPoints();
        Map<SkillAttributeType, Integer> playerSkills = GotDynastyNpcs.playerSkillPoints(((PlayerSkillHolder)player).got$getSkillProfile());
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            int average = Math.round((float)(playerSkills.getOrDefault((Object)type, 0) + spouseSkills.getOrDefault((Object)type, 0)) / 2.0f);
            merged.put(type, average);
        }
        String geneticTraitId = "";
        class_5819 random = player.method_59922();
        if (!PlayerGeneticTrait.ALL.isEmpty() && random.method_43048(100) < 10) {
            geneticTraitId = PlayerGeneticTrait.ALL.get(random.method_43048(PlayerGeneticTrait.ALL.size())).id();
        }
        LinkedHashSet<String> personalityTraits = new LinkedHashSet<String>();
        for (int roll = 0; roll < 2; ++roll) {
            if (PlayerPersonalityTrait.ALL.isEmpty() || random.method_43048(100) >= 25) continue;
            PlayerPersonalityTrait trait = PlayerPersonalityTrait.ALL.get(random.method_43048(PlayerPersonalityTrait.ALL.size()));
            personalityTraits.add(trait.id());
        }
        return new NpcAttributeProfile(geneticTraitId, List.copyOf(personalityTraits), merged);
    }

    private static void ensureInitialRelation(class_3222 player, String childId) {
        if (player == null || childId == null || childId.isBlank()) {
            return;
        }
        ((PlayerNpcRelationsHolder)player).got$getNpcRelationsProfile().setRelation(childId, 20);
    }

    private static void spawnOrRespawn(class_3218 world, DynastyNpcWorldState.FamilyMemberProfile profile, @Nullable class_2338 preferredPos, boolean forceTeleportToPlayer) {
        class_2338 spawnPos;
        if (world == null || profile == null) {
            return;
        }
        class_1297 existing = GotDynastyNpcs.findTrackedFamilyEntity(world, profile, forceTeleportToPlayer);
        if (existing instanceof AbstractDynastyNpcEntity) {
            AbstractDynastyNpcEntity dynastyNpc = (AbstractDynastyNpcEntity)existing;
            GotDynastyNpcs.applyProfile(dynastyNpc, profile);
            if (forceTeleportToPlayer && preferredPos != null) {
                dynastyNpc.method_5808((double)preferredPos.method_10263() + 0.5, preferredPos.method_10264(), (double)preferredPos.method_10260() + 0.5, dynastyNpc.method_36454(), dynastyNpc.method_36455());
                dynastyNpc.setPointOfInterest(preferredPos);
            }
            return;
        }
        class_2338 class_23382 = spawnPos = preferredPos != null ? preferredPos : GotDynastyNpcs.resolveSpawnPos(world, profile);
        if (!forceTeleportToPlayer && !world.method_8393(spawnPos.method_10263() >> 4, spawnPos.method_10260() >> 4)) {
            return;
        }
        if (forceTeleportToPlayer) {
            GotDynastyNpcs.loadChunkAt(world, spawnPos);
        }
        if ((existing = world.method_14190(profile.entityUuid())) instanceof AbstractDynastyNpcEntity) {
            AbstractDynastyNpcEntity dynastyNpc = (AbstractDynastyNpcEntity)existing;
            GotDynastyNpcs.applyProfile(dynastyNpc, profile);
            return;
        }
        if (existing != null) {
            return;
        }
        AbstractDynastyNpcEntity npc = GotDynastyNpcs.createEntity(world, profile);
        if (npc == null) {
            return;
        }
        npc.method_5826(profile.entityUuid());
        npc.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
        npc.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16474, null);
        GotDynastyNpcs.applyProfile(npc, profile);
        npc.setPointOfInterest(spawnPos);
        world.method_8649((class_1297)npc);
        class_3222 owner = npc.resolveOwner(world);
        if (owner != null) {
            npc.ensureDefaultRelation(owner);
        }
    }

    @Nullable
    private static class_1297 findTrackedFamilyEntity(class_3218 world, DynastyNpcWorldState.FamilyMemberProfile profile, boolean loadKnownChunk) {
        if (world == null || profile == null) {
            return null;
        }
        class_1297 direct = world.method_14190(profile.entityUuid());
        if (direct != null) {
            return direct;
        }
        if (!loadKnownChunk) {
            return null;
        }
        class_2338 knownPos = GotDynastyNpcs.resolveSpawnPos(world, profile);
        GotDynastyNpcs.loadChunkAt(world, knownPos);
        direct = world.method_14190(profile.entityUuid());
        if (direct != null) {
            return direct;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)knownPos), (double)192.0, (double)96.0, (double)192.0);
        List matches = world.method_8390(AbstractDynastyNpcEntity.class, searchBox, npc -> npc.method_5805() && (profile.entityUuid().equals(npc.method_5667()) || profile.id().equals(npc.getPersistentNpcId())));
        return matches.isEmpty() ? null : (class_1297)matches.getFirst();
    }

    private static void loadChunkAt(class_3218 world, class_2338 pos) {
        if (world == null || pos == null) {
            return;
        }
        world.method_8497(pos.method_10263() >> 4, pos.method_10260() >> 4);
    }

    @Nullable
    private static AbstractDynastyNpcEntity createEntity(class_3218 world, DynastyNpcWorldState.FamilyMemberProfile profile) {
        AbstractDynastyNpcEntity npc;
        AbstractDynastyNpcEntity abstractDynastyNpcEntity = npc = profile.stage() == FamilyStage.DYNASTY ? (AbstractDynastyNpcEntity)GotEntities.DYNASTY_NPC.method_5883((class_1937)world) : (AbstractDynastyNpcEntity)GotEntities.CHILD_NPC.method_5883((class_1937)world);
        if (npc != null) {
            npc.method_5826(profile.entityUuid());
        }
        return npc;
    }

    private static void applyProfile(AbstractDynastyNpcEntity npc, DynastyNpcWorldState.FamilyMemberProfile profile) {
        npc.setDynastyKingdomId(class_2960.method_60654((String)profile.kingdomId()));
        npc.setCastleNpcId(profile.id());
        npc.setDynastyHouseId(profile.houseId());
        npc.setOwnerPlayerUuid(profile.ownerPlayerUuid());
        npc.setOwnerPlayerName(profile.ownerPlayerName());
        npc.setOtherParentName(profile.otherParentName());
        npc.setFamilyStage(profile.stage());
        npc.setBornDay(profile.bornDay());
        npc.setOwnerSmallArms(profile.ownerSmallArms());
        npc.got$setCastle(profile.castleId());
        npc.setNpcAttributes(profile.attributes());
        npc.method_5665((class_2561)class_2561.method_43470((String)profile.displayName()));
        class_1937 class_19372 = npc.method_37908();
        if (class_19372 instanceof class_3218) {
            class_3218 world = (class_3218)class_19372;
            if (!profile.castleId().isBlank()) {
                npc.setPointOfInterest(GotDynastyNpcs.resolveSpawnPos(world, profile));
            }
        }
    }

    private static class_2338 resolveSpawnPos(class_3218 world, DynastyNpcWorldState.FamilyMemberProfile profile) {
        if (world == null || profile == null) {
            return class_2338.field_10980;
        }
        String castleId = profile.castleId();
        if (!castleId.isBlank()) {
            CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
            if (castle != null) {
                return castle.getBannerPos();
            }
            VillageInstance village = VillageWorldState.get(world).get(castleId);
            if (village != null) {
                return village.getCenterPos();
            }
        }
        try {
            class_3222 owner = world.method_8503().method_3760().method_14602(UUID.fromString(profile.ownerPlayerUuid()));
            if (owner != null) {
                return owner.method_24515();
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        return class_2338.field_10980;
    }

    private static DynastyNpcWorldState.FamilyMemberProfile syncProfileHouseIdentity(class_3218 world, DynastyNpcWorldState.FamilyMemberProfile profile, @Nullable class_3222 owner) {
        String castleId;
        if (owner == null) {
            return profile;
        }
        String houseId = GotNpcMarriage.resolveCurrentPlayerHouseId(owner);
        String displayName = GotDynastyNpcs.createDynastyDisplayName(world, GotDynastyNpcs.resolvePlayerKingdomId(owner), houseId, owner.method_5477().getString(), profile.id());
        String string = castleId = profile.stage() == FamilyStage.CHILD ? GotDynastyNpcs.resolveChildCastleId(world, owner, profile.castleId()) : GotDynastyNpcs.resolveDynastyCastleId(world, owner.method_5667(), GotDynastyNpcs.resolvePlayerKingdomId(owner).toString(), houseId, profile.displayName(), profile.castleId());
        if (houseId.equals(profile.houseId()) && displayName.equals(profile.displayName()) && castleId.equals(profile.castleId()) && owner.method_5477().getString().equals(profile.ownerPlayerName())) {
            return profile;
        }
        return new DynastyNpcWorldState.FamilyMemberProfile(profile.id(), profile.entityUuid(), profile.ownerPlayerUuid(), owner.method_5477().getString(), profile.otherParentName(), profile.boundMarriedPlayerUuid(), profile.boundMarriedPlayerName(), GotDynastyNpcs.resolvePlayerKingdomId(owner).toString(), houseId, displayName, profile.bornDay(), castleId, profile.stage(), GotDynastyNpcs.resolveOwnerSmallArms(owner), profile.summonCooldownEndsAtMs(), profile.attributes());
    }

    private static void convertLoadedChildToDynasty(class_3218 world, String memberId) {
        class_1297 existing;
        class_1297 class_12972 = existing = world == null ? null : GotDynastyNpcs.findFamilyEntity(world, memberId);
        if (!(existing instanceof ChildNpcEntity)) {
            return;
        }
        ChildNpcEntity child = (ChildNpcEntity)existing;
        class_2338 spawnPos = child.method_24515();
        float yaw = child.method_36454();
        float pitch = child.method_36455();
        child.method_31472();
        DynastyNpcWorldState.FamilyMemberProfile profile = DynastyNpcWorldState.get(world).get(memberId);
        if (profile == null) {
            return;
        }
        AbstractDynastyNpcEntity dynasty = (AbstractDynastyNpcEntity)GotEntities.DYNASTY_NPC.method_5883((class_1937)world);
        if (dynasty == null) {
            return;
        }
        dynasty.method_5826(profile.entityUuid());
        dynasty.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, yaw, pitch);
        dynasty.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16468, null);
        GotDynastyNpcs.applyProfile(dynasty, profile);
        world.method_8649((class_1297)dynasty);
    }

    @Nullable
    private static class_1297 findFamilyEntity(class_3218 world, String memberId) {
        if (world == null || memberId == null || memberId.isBlank()) {
            return null;
        }
        DynastyNpcWorldState.FamilyMemberProfile profile = DynastyNpcWorldState.get(world).get(memberId);
        return profile == null ? null : world.method_14190(profile.entityUuid());
    }

    private static String resolveDynastyCastleId(class_3218 world, UUID ownerUuid, String kingdomId, String houseId, String lordName, String fallbackCastleId) {
        if (world == null || ownerUuid == null) {
            return fallbackCastleId == null ? "" : fallbackCastleId;
        }
        return GotCastles.resolvePlayerOwnedDynastyRouteCastleId(world, ownerUuid, kingdomId, houseId, lordName, fallbackCastleId);
    }

    private static String createDynastyDisplayName(class_3218 world, class_2960 kingdomId, String houseId, String ownerPlayerName, String uniqueKey) {
        String houseName = GotNpcMarriage.resolveHouseName(world, houseId, kingdomId.toString());
        String surname = GotNpcMarriage.normalizeHouseSurname(houseName);
        Kingdom kingdom = GotKingdoms.get(kingdomId);
        String generated = GotCastleOwnerNames.createFullName(kingdom, surname, uniqueKey == null ? 0L : (long)uniqueKey.hashCode());
        if (surname.isBlank()) {
            return generated;
        }
        String firstName = GotNpcMarriage.extractFirstName(generated);
        return firstName + " " + surname;
    }

    private static class_2960 resolvePlayerKingdomId(class_3222 player) {
        String kingdomId = ((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedKingdomId();
        return class_2960.method_60654((String)kingdomId);
    }

    private static boolean resolveOwnerSmallArms(class_3222 player) {
        CharacterSelectionOption option = GotCharacterOptions.get(((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedOptionId());
        return option != null && option.smallArms();
    }

    private static Map<SkillAttributeType, Integer> playerSkillPoints(PlayerSkillProfile profile) {
        EnumMap<SkillAttributeType, Integer> values = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            values.put(type, profile.get(type).getPoints());
        }
        return values;
    }

    @Nullable
    private static class_3222 resolveOwner(class_3218 world, String ownerPlayerUuid) {
        if (world == null || ownerPlayerUuid == null || ownerPlayerUuid.isBlank()) {
            return null;
        }
        try {
            return world.method_8503().method_3760().method_14602(UUID.fromString(ownerPlayerUuid));
        }
        catch (IllegalArgumentException exception) {
            return null;
        }
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

    private static boolean isCurrentlyMarried(class_3218 world, String npcId) {
        if (world == null || npcId == null || npcId.isBlank() || world.method_8503() == null) {
            return false;
        }
        for (class_3222 player : world.method_8503().method_3760().method_14571()) {
            PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
            if (!marriage.isMarried() || !npcId.equals(marriage.getSpouseNpcId())) continue;
            return true;
        }
        return false;
    }

    private static boolean isBoundToAnotherPlayer(class_3218 world, DynastyNpcWorldState.FamilyMemberProfile profile, String ownerPlayerUuid) {
        AbstractKingdomNpcEntity npc;
        class_1297 entity;
        if (profile == null || ownerPlayerUuid == null || ownerPlayerUuid.isBlank()) {
            return false;
        }
        if (!profile.boundMarriedPlayerUuid().isBlank()) {
            return !ownerPlayerUuid.equals(profile.boundMarriedPlayerUuid());
        }
        if (!GotDynastyNpcs.isCurrentlyMarried(world, profile.id())) {
            return false;
        }
        class_1297 class_12972 = entity = world == null ? null : world.method_14190(profile.entityUuid());
        if (entity instanceof AbstractKingdomNpcEntity && !(npc = (AbstractKingdomNpcEntity)entity).getMarriedPlayerUuid().isBlank()) {
            return !ownerPlayerUuid.equals(npc.getMarriedPlayerUuid());
        }
        if (world != null && world.method_8503() != null) {
            for (class_3222 player : world.method_8503().method_3760().method_14571()) {
                PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
                if (!marriage.isMarried() || !profile.id().equals(marriage.getSpouseNpcId())) continue;
                return !ownerPlayerUuid.equals(player.method_5845());
            }
        }
        return false;
    }

    private static String resolveChildCastleId(class_3222 player, AbstractKingdomNpcEntity spouse) {
        if (spouse != null && !spouse.got$getCastle().isBlank()) {
            return spouse.got$getCastle();
        }
        return GotDynastyNpcs.resolveChildCastleId(player.method_51469(), player, "");
    }

    private static String resolveChildCastleId(class_3218 world, class_3222 player, String fallbackCastleId) {
        AbstractKingdomNpcEntity spouseNpc;
        class_1297 spouse;
        PlayerMarriageProfile marriage;
        if (player == null) {
            return fallbackCastleId == null ? "" : fallbackCastleId;
        }
        if (world != null && (marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile()).isMarried() && marriage.getSpouseNpcUuid() != null && (spouse = world.method_14190(marriage.getSpouseNpcUuid())) instanceof AbstractKingdomNpcEntity && !(spouseNpc = (AbstractKingdomNpcEntity)spouse).got$getCastle().isBlank()) {
            return spouseNpc.got$getCastle();
        }
        String castleId = GotNpcMarriage.resolveMarriageDestinationCastleIdForPlayer(player);
        return castleId.isBlank() ? (fallbackCastleId == null ? "" : fallbackCastleId) : castleId;
    }

    public static void handleSummonRequest(class_3222 player, String memberId) {
        if (player == null || player.method_51469() == null || memberId == null || memberId.isBlank()) {
            return;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (marriage.isMarried() && memberId.equals(marriage.getSpouseNpcId())) {
            GotNpcMarriage.handleSummonRequest(player);
            return;
        }
        DynastyNpcWorldState state = DynastyNpcWorldState.get(player.method_51469());
        DynastyNpcWorldState.FamilyMemberProfile profile = state.get(memberId);
        if (profile == null || !player.method_5845().equals(profile.ownerPlayerUuid()) || GotDynastyNpcs.isBoundToAnotherPlayer(player.method_51469(), profile, player.method_5845())) {
            return;
        }
        long nowMs = System.currentTimeMillis();
        if (profile.summonCooldownEndsAtMs() > nowMs) {
            PlayerHouseService.broadcast(player.method_5682());
            return;
        }
        state.put(profile.withSummonCooldownEndsAtMs(nowMs + 120000L));
        GotDynastyNpcs.spawnOrRespawn(player.method_51469(), state.get(memberId), player.method_24515(), true);
        PlayerHouseService.broadcast(player.method_5682());
    }

    public static enum FamilyStage {
        STANDARD("standard"),
        CHILD("child"),
        DYNASTY("dynasty");

        private final String id;

        private FamilyStage(String id) {
            this.id = id;
        }

        public String id() {
            return this.id;
        }

        public static FamilyStage byId(String id) {
            for (FamilyStage value : FamilyStage.values()) {
                if (!value.id.equals(id)) continue;
                return value;
            }
            return CHILD;
        }
    }
}

