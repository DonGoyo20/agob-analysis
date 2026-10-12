/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_1297
 *  net.minecraft.class_1304
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
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
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
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
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import trep.got.castle.CastleHolder;
import trep.got.castle.CastleInstance;
import trep.got.castle.SettlerProfession;
import trep.got.castle.SettlerSkillTier;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.day.DailyTaskContext;
import trep.got.day.DailyTaskRegistry;
import trep.got.entity.AbstractDynastyNpcEntity;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.entity.SettlerNpcEntity;
import trep.got.family.DynastyNpcWorldState;
import trep.got.house.PlayerHouseDefinition;
import trep.got.house.PlayerHouseService;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationTitle;
import trep.got.network.NpcChatResultPayload;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotDynastyNpcs;
import trep.got.registry.GotEntities;
import trep.got.registry.GotItems;
import trep.got.registry.GotKingdomArmor;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotNpcRelations;
import trep.got.registry.GotPlayerKingdomActions;
import trep.got.registry.GotVillages;
import trep.got.relations.NpcRelationTier;
import trep.got.relations.PlayerMarriageHolder;
import trep.got.relations.PlayerMarriageProfile;
import trep.got.relations.PlayerNpcRelationsHolder;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeType;
import trep.got.troop.CommanderActionType;
import trep.got.troop.GotTroops;
import trep.got.troop.PartyFormationType;
import trep.got.village.VillageInstance;
import trep.got.village.VillageWorldState;
import trep.got.world.dimension.GotDimensionKeys;

public final class GotNpcMarriage {
    private static final long SUMMON_COOLDOWN_MS = 120000L;

    private GotNpcMarriage() {
    }

    public static void initialize() {
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            PlayerMarriageProfile source = ((PlayerMarriageHolder)oldPlayer).got$getMarriageProfile();
            ((PlayerMarriageHolder)newPlayer).got$getMarriageProfile().copyFrom(source);
        });
        DailyTaskRegistry.register(class_2960.method_60655((String)"agob", (String)"marriage_sync"), GotNpcMarriage::runDailyMarriageSync);
    }

    public static boolean canShowMarryOption(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null) {
            return false;
        }
        if (GotNpcMarriage.isNightsWatchMarriageBlocked(player, npc)) {
            return false;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (marriage.isMarried()) {
            return false;
        }
        return GotNpcMarriage.canMarryNpc(player.method_5845(), npc, GotNpcMarriage.hasRequiredLordMarriageTitle(player), GotNpcRelations.getRelation(player, npc));
    }

    public static boolean canMarryNpc(String playerUuid, AbstractKingdomNpcEntity npc, boolean hasRequiredLordMarriageTitle, int relationValue) {
        AbstractDynastyNpcEntity dynastyNpc;
        if (playerUuid == null || playerUuid.isBlank() || npc == null) {
            return false;
        }
        if (GotNpcMarriage.isNightsWatchNpc(npc)) {
            return false;
        }
        if (GotDynastyNpcs.isChildNpc(npc)) {
            return false;
        }
        if (GotDynastyNpcs.isDynastyNpc(npc) && npc instanceof AbstractDynastyNpcEntity && playerUuid.equals((dynastyNpc = (AbstractDynastyNpcEntity)npc).getOwnerPlayerUuid())) {
            return false;
        }
        if (npc.isMarriedToPlayer() || npc.isNobleSpouse()) {
            return false;
        }
        if (npc.getNpcRole() == KingdomNpcRole.LORD && !hasRequiredLordMarriageTitle) {
            return false;
        }
        return NpcRelationTier.fromValue(relationValue).ordinal() >= NpcRelationTier.TRUSTED.ordinal();
    }

    public static boolean isMarriedTo(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null) {
            return false;
        }
        return player.method_5845().equals(npc.getMarriedPlayerUuid()) && ((PlayerMarriageHolder)player).got$getMarriageProfile().getSpouseNpcId().equals(npc.getPersistentNpcId());
    }

    public static void handleMarryAction(class_3222 player, AbstractKingdomNpcEntity npc) {
        boolean accepted;
        if (player == null || npc == null) {
            return;
        }
        if (GotNpcMarriage.isNightsWatchMarriageBlocked(player, npc)) {
            return;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (marriage.isMarried() || !GotNpcMarriage.canShowMarryOption(player, npc)) {
            return;
        }
        class_5819 random = player.method_59922();
        float acceptedChance = GotNpcMarriage.baseMarriageSuccessChance(player, npc);
        boolean bl = accepted = random.method_43057() < acceptedChance;
        if (!accepted) {
            ((PlayerNpcRelationsHolder)player).got$getNpcRelationsProfile().setRelation(npc.getPersistentNpcId(), 0);
            GotNpcMarriage.sendResult(player, npc, "negative", random.method_43055(), 0);
            return;
        }
        String originalNpcId = npc.getPersistentNpcId();
        int carriedRelation = GotNpcRelations.getRelation(player, npc);
        boolean dynastyMarriage = GotDynastyNpcs.isDynastyNpc(npc);
        String marriageNpcId = dynastyMarriage ? originalNpcId : "married_" + player.method_5845().replace("-", "") + "_" + Math.abs(npc.method_5667().hashCode());
        String sourceCastleId = npc.got$getCastle();
        boolean sourceVillage = player.method_51469() != null && VillageWorldState.get(player.method_51469()).get(sourceCastleId) != null;
        boolean sourceSettler = npc instanceof SettlerNpcEntity;
        boolean lordMarriage = !dynastyMarriage && (npc.getNpcRole() == KingdomNpcRole.LORD || npc instanceof AbstractKingdomLordNpcEntity);
        marriage.clear();
        marriage.setSpouseNpcId(marriageNpcId);
        marriage.setSpouseNpcUuid(npc.method_5667());
        marriage.setSpouseName(npc.method_5477().getString());
        marriage.setSpouseKingdomId(npc.getKingdomId().toString());
        marriage.setSpouseHouseId(GotNpcMarriage.resolveCurrentPlayerHouseId(player));
        marriage.setSourceCastleId(sourceCastleId);
        marriage.setSourceVillage(sourceVillage);
        marriage.setSourceSettler(sourceSettler);
        marriage.setLordMarriage(lordMarriage);
        marriage.setSpouseFamilyStageId(GotDynastyNpcs.familyStage(npc).id());
        marriage.setSpouseAttributes(npc.getNpcAttributes());
        marriage.setSpouseAppearance(npc.getNpcAppearance());
        marriage.setBabyCooldownUnlockDay(0L);
        npc.setCastleNpcId(marriageNpcId);
        npc.setMarriedPlayerUuid(player.method_5845());
        npc.setMarriedPlayerName(player.method_5477().getString());
        npc.setIndependentMarriageNpc(true);
        npc.setBabyCooldownUnlockDay(marriage.getBabyCooldownUnlockDay());
        npc.method_5673(class_1304.field_6173, class_1799.field_8037);
        npc.method_5673(class_1304.field_6171, class_1799.field_8037);
        if (!lordMarriage) {
            npc.setNobleSpouse(true);
            GotNpcMarriage.applySpouseHouseIdentity(player, npc);
        }
        marriage.setSpouseName(npc.method_5477().getString());
        if (npc instanceof SettlerNpcEntity) {
            SettlerNpcEntity settler = (SettlerNpcEntity)npc;
            settler.setAcceptedSettler(false);
            settler.setProfession(SettlerProfession.FARMER);
            settler.setSkillTier(SettlerSkillTier.WOOD);
        }
        if (!dynastyMarriage && !originalNpcId.isBlank()) {
            ((PlayerNpcRelationsHolder)player).got$getNpcRelationsProfile().setRelation(originalNpcId, 0);
        }
        ((PlayerNpcRelationsHolder)player).got$getNpcRelationsProfile().setRelation(marriageNpcId, carriedRelation);
        marriage.setTransferredCastleIds(lordMarriage ? GotNpcMarriage.transferLordCastlesToPlayer(player, npc) : List.of());
        if (dynastyMarriage && player.method_51469() != null) {
            GotDynastyNpcs.bindDynastyToMarriedPlayer(player.method_51469(), marriageNpcId, player);
        }
        String destinationCastleId = GotNpcMarriage.resolveMarriageDestinationCastleId(player, sourceCastleId);
        GotNpcMarriage.moveSpouseToCastle(player.method_51469(), npc, destinationCastleId);
        if (!sourceSettler) {
            GotNpcMarriage.refreshSourcePopulation(player.method_51469(), sourceCastleId, sourceVillage, originalNpcId);
        }
        PlayerHouseService.broadcast(player.method_5682());
        GotNpcMarriage.sendResult(player, npc, "positive", random.method_43055(), carriedRelation);
    }

    public static void handleDivorceAction(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null || !GotNpcMarriage.isMarriedTo(player, npc)) {
            return;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (player.method_51469() != null && GotDynastyNpcs.FamilyStage.DYNASTY.id().equals(marriage.getSpouseFamilyStageId())) {
            GotDynastyNpcs.clearDynastyMarriageBinding(player.method_51469(), marriage.getSpouseNpcId());
        }
        ((PlayerNpcRelationsHolder)player).got$getNpcRelationsProfile().setRelation(marriage.getSpouseNpcId(), -10);
        GotNpcMarriage.restoreTransferredLordCastles(player, marriage, npc);
        npc.setMarriedPlayerUuid("");
        npc.setMarriedPlayerName("");
        npc.setIndependentMarriageNpc(false);
        npc.setNobleSpouse(false);
        npc.setMarriagePartyCompanion(false);
        npc.setBabyCooldownUnlockDay(0L);
        npc.clearPartyCommandState();
        GotNpcMarriage.clearPartyCompanionLoadout(npc);
        npc.method_31472();
        marriage.clear();
        PlayerHouseService.broadcast(player.method_5682());
        GotNpcMarriage.sendResult(player, npc, "negative", player.method_59922().method_43055(), -10);
    }

    public static void handleSummonRequest(class_3222 player) {
        if (player == null || player.method_51469() == null) {
            return;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (!marriage.isMarried() || marriage.isSpouseInParty()) {
            PlayerHouseService.broadcast(player.method_5682());
            return;
        }
        long nowMs = System.currentTimeMillis();
        if (marriage.getSummonCooldownEndsAtMs() > nowMs) {
            PlayerHouseService.broadcast(player.method_5682());
            return;
        }
        AbstractKingdomNpcEntity spouse = GotNpcMarriage.ensureSpouseEntityNearPlayer(player.method_51469(), player, marriage);
        if (spouse == null) {
            return;
        }
        marriage.setSpouseInParty(false);
        marriage.setSummonedUntilDay(GotNpcMarriage.currentMarriageRouteDay(player.method_51469()));
        marriage.setSummonCooldownEndsAtMs(nowMs + 120000L);
        GotNpcMarriage.applyNormalMarriageState(player, spouse, marriage);
        GotNpcMarriage.teleportSpouseNearPlayer(player, spouse);
        PlayerHouseService.broadcast(player.method_5682());
    }

    public static void handleTakeToPartyAction(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null || !GotNpcMarriage.isMarriedTo(player, npc)) {
            return;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        marriage.setSpouseInParty(true);
        marriage.setSummonedUntilDay(-1L);
        GotNpcMarriage.applyPartyMarriageState(player, npc, marriage);
        GotNpcMarriage.teleportSpouseNearPlayer(player, npc);
        PlayerHouseService.broadcast(player.method_5682());
    }

    public static void handleAskToLeaveAction(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null || !GotNpcMarriage.isMarriedTo(player, npc)) {
            return;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        marriage.setSpouseInParty(false);
        marriage.setSummonedUntilDay(-1L);
        GotNpcMarriage.applyNormalMarriageState(player, npc, marriage);
        String destinationCastleId = GotNpcMarriage.resolveMarriageDestinationCastleId(player, marriage.getSourceCastleId());
        if (!destinationCastleId.isBlank() && !GotNpcMarriage.moveSpouseToCastle(player.method_51469(), npc, destinationCastleId)) {
            npc.got$setCastle(destinationCastleId);
            npc.method_31472();
        }
        PlayerHouseService.broadcast(player.method_5682());
    }

    public static void synchronizeMarriage(class_3218 world, class_3222 player) {
        if (world == null || player == null) {
            return;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (!marriage.isMarried()) {
            return;
        }
        long currentDay = GotNpcMarriage.currentMarriageRouteDay(world);
        AbstractKingdomNpcEntity spouse = GotNpcMarriage.findSpouseEntity(world, marriage);
        String destinationCastleId = GotNpcMarriage.resolveMarriageDestinationCastleId(player, marriage.getSourceCastleId());
        if (marriage.isSpouseInParty()) {
            spouse = GotNpcMarriage.ensureSpouseEntityNearPlayer(world, player, marriage);
            if (spouse != null) {
                GotNpcMarriage.applyPartyMarriageState(player, spouse, marriage);
            }
            return;
        }
        if (marriage.getSummonedUntilDay() == currentDay) {
            spouse = GotNpcMarriage.ensureSpouseEntityNearPlayer(world, player, marriage);
            if (spouse != null) {
                GotNpcMarriage.applyNormalMarriageState(player, spouse, marriage);
            }
            return;
        }
        marriage.setSummonedUntilDay(-1L);
        if (spouse == null) {
            GotNpcMarriage.respawnMarriageSpouse(world, player, marriage, destinationCastleId, null);
            return;
        }
        GotNpcMarriage.applyNormalMarriageState(player, spouse, marriage);
        if (!(destinationCastleId.isBlank() || destinationCastleId.equals(spouse.got$getCastle()) || GotNpcMarriage.moveSpouseToCastle(world, spouse, destinationCastleId))) {
            spouse.got$setCastle(destinationCastleId);
            spouse.method_31472();
        }
    }

    private static void runDailyMarriageSync(class_3218 world, DailyTaskContext context) {
        if (world == null || !GotDimensionKeys.ICEANDFIRE.equals((Object)world.method_27983())) {
            return;
        }
        MinecraftServer server = world.method_8503();
        for (class_3222 player : server.method_3760().method_14571()) {
            if (!((PlayerMarriageHolder)player).got$getMarriageProfile().isMarried()) continue;
            GotNpcMarriage.synchronizeMarriage(world, player);
        }
    }

    private static void refreshSourcePopulation(class_3218 world, String sourceCastleId, boolean sourceVillage, String originalNpcId) {
        if (world == null || sourceCastleId == null || sourceCastleId.isBlank()) {
            return;
        }
        if (sourceVillage) {
            GotVillages.refreshVillagePopulationForMarriage(world, sourceCastleId);
        } else {
            GotCastles.replaceCastleNpcProfileAfterMarriage(world, sourceCastleId, originalNpcId);
        }
    }

    public static String resolveMarriageLocationName(class_3218 world, class_3222 player) {
        if (world == null || player == null) {
            return "";
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (!marriage.isMarried()) {
            return "";
        }
        if (marriage.isSpouseInParty() || marriage.getSummonedUntilDay() == GotNpcMarriage.currentMarriageRouteDay(world)) {
            return "With " + player.method_5477().getString();
        }
        String destinationCastleId = GotNpcMarriage.resolveMarriageDestinationCastleId(player, marriage.getSourceCastleId());
        return GotCastles.resolveCastleLocationName(world, destinationCastleId);
    }

    public static String resolveMarriageDestinationCastleIdForPlayer(class_3222 player) {
        if (player == null) {
            return "";
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        return GotNpcMarriage.resolveMarriageDestinationCastleId(player, marriage.getSourceCastleId());
    }

    private static boolean moveSpouseToCastle(class_3218 world, AbstractKingdomNpcEntity npc, String castleId) {
        if (world == null || npc == null || castleId == null || castleId.isBlank()) {
            return false;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle != null) {
            class_2338 target = castle.getBannerPos();
            npc.got$setCastle(castleId);
            npc.setPointOfInterest(target);
            npc.method_5808((double)target.method_10263() + 0.5, target.method_10264(), (double)target.method_10260() + 0.5, npc.method_36454(), npc.method_36455());
            return true;
        }
        VillageInstance village = VillageWorldState.get(world).get(castleId);
        if (village != null) {
            class_2338 target = village.getCenterPos();
            npc.got$setCastle(castleId);
            npc.setPointOfInterest(target);
            npc.method_5808((double)target.method_10263() + 0.5, target.method_10264(), (double)target.method_10260() + 0.5, npc.method_36454(), npc.method_36455());
            return true;
        }
        return false;
    }

    private static String resolveMarriageDestinationCastleId(class_3222 player, String fallbackCastleId) {
        if (player == null || player.method_51469() == null) {
            return fallbackCastleId == null ? "" : fallbackCastleId;
        }
        List<CastleInstance> ownedCastles = GotCastles.getWorldState(player.method_51469()).getAll().stream().filter(castle -> player.method_5667().equals(castle.getOwnerPlayerUuid())).filter(castle -> !"player_claim".equals(castle.getDefinitionId())).sorted(Comparator.comparing(CastleInstance::getId)).toList();
        if (!ownedCastles.isEmpty()) {
            long day = player.method_51469().method_8532() / 24000L;
            return ownedCastles.get(Math.floorMod((int)(day + (long)player.method_5667().hashCode()), ownedCastles.size())).getId();
        }
        String boundCastleId = ((CastleHolder)player).got$getCastle();
        if (boundCastleId != null && !boundCastleId.isBlank()) {
            return boundCastleId;
        }
        return fallbackCastleId == null ? "" : fallbackCastleId;
    }

    private static List<String> transferLordCastlesToPlayer(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null || player.method_51469() == null) {
            return List.of();
        }
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        String newKingdomId = character.getSelectedKingdomId();
        String newHouseId = GotNpcMarriage.resolveCurrentPlayerHouseId(player);
        String lordName = npc.method_5477().getString();
        ArrayList<String> transferred = new ArrayList<String>();
        for (CastleInstance castle : GotCastles.getWorldState(player.method_51469()).getAll()) {
            if (castle.getOwnerPlayerUuid() != null || !npc.getKingdomId().toString().equals(castle.getOwnerKingdomId()) || !lordName.equals(castle.getLordName())) continue;
            castle.setOwnerPlayerUuid(player.method_5667());
            castle.setOwnerKingdomId(newKingdomId);
            castle.setOwnerHouseId(newHouseId);
            castle.setLordName(player.method_5477().getString());
            transferred.add(castle.getId());
        }
        if (!transferred.isEmpty()) {
            GotCastles.getWorldState(player.method_51469()).method_80();
        }
        return List.copyOf(transferred);
    }

    private static void restoreTransferredLordCastles(class_3222 player, PlayerMarriageProfile marriage, AbstractKingdomNpcEntity spouse) {
        if (player == null || marriage == null || spouse == null || player.method_51469() == null) {
            return;
        }
        for (String castleId : marriage.getTransferredCastleIds()) {
            CastleInstance castle = GotCastles.getWorldState(player.method_51469()).get(castleId);
            if (castle == null || !player.method_5667().equals(castle.getOwnerPlayerUuid())) continue;
            castle.setOwnerPlayerUuid(null);
            castle.setOwnerKingdomId(marriage.getSpouseKingdomId());
            castle.setOwnerHouseId(marriage.getSpouseHouseId());
            castle.setLordName(spouse.method_5477().getString());
            GotCastles.refreshCastlePopulationForMarriage(player.method_51469(), castleId);
        }
        GotCastles.getWorldState(player.method_51469()).method_80();
    }

    @Nullable
    private static AbstractKingdomNpcEntity findSpouseEntity(class_3218 world, PlayerMarriageProfile marriage) {
        if (world == null || marriage == null || marriage.getSpouseNpcUuid() == null) {
            return null;
        }
        class_1297 direct = world.method_14190(marriage.getSpouseNpcUuid());
        if (direct instanceof AbstractKingdomNpcEntity) {
            AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)direct;
            return npc;
        }
        return null;
    }

    @Nullable
    private static AbstractKingdomNpcEntity findSpouseEntity(class_3218 world, PlayerMarriageProfile marriage, @Nullable String knownCastleId, boolean loadKnownChunk) {
        AbstractKingdomNpcEntity direct = GotNpcMarriage.findSpouseEntity(world, marriage);
        if (direct != null || !loadKnownChunk || world == null || marriage == null) {
            return direct;
        }
        class_2338 knownPos = GotNpcMarriage.resolveKnownSpousePos(world, knownCastleId, marriage.getSourceCastleId());
        if (knownPos == null) {
            return null;
        }
        GotNpcMarriage.loadChunkAt(world, knownPos);
        direct = GotNpcMarriage.findSpouseEntity(world, marriage);
        if (direct != null) {
            return direct;
        }
        class_238 searchBox = class_238.method_30048((class_243)class_243.method_24953((class_2382)knownPos), (double)192.0, (double)96.0, (double)192.0);
        List matches = world.method_8390(AbstractKingdomNpcEntity.class, searchBox, npc -> npc.method_5805() && marriage.getSpouseNpcId().equals(npc.getPersistentNpcId()) && (marriage.getSpouseNpcUuid() == null || marriage.getSpouseNpcUuid().equals(npc.method_5667())));
        return matches.isEmpty() ? null : (AbstractKingdomNpcEntity)matches.getFirst();
    }

    private static AbstractKingdomNpcEntity ensureSpouseEntity(class_3218 world, class_3222 player, PlayerMarriageProfile marriage, @Nullable String destinationCastleId) {
        AbstractKingdomNpcEntity spouse = GotNpcMarriage.findSpouseEntity(world, marriage, destinationCastleId, false);
        if (spouse != null) {
            return spouse;
        }
        return GotNpcMarriage.respawnMarriageSpouse(world, player, marriage, destinationCastleId, null);
    }

    private static AbstractKingdomNpcEntity ensureSpouseEntityNearPlayer(class_3218 world, class_3222 player, PlayerMarriageProfile marriage) {
        String destinationCastleId = GotNpcMarriage.resolveMarriageDestinationCastleId(player, marriage.getSourceCastleId());
        AbstractKingdomNpcEntity spouse = GotNpcMarriage.findSpouseEntity(world, marriage, destinationCastleId, true);
        if (spouse != null) {
            return spouse;
        }
        class_2338 summonPos = GotTroops.findTroopSummonPos(world, player.method_24515(), marriage.getSpouseNpcId().hashCode());
        return GotNpcMarriage.respawnMarriageSpouse(world, player, marriage, "", summonPos);
    }

    @Nullable
    private static AbstractKingdomNpcEntity respawnMarriageSpouse(class_3218 world, class_3222 player, PlayerMarriageProfile marriage, @Nullable String destinationCastleId, @Nullable class_2338 overridePos) {
        class_2338 targetPos;
        AbstractKingdomNpcEntity spouse;
        Kingdom kingdom;
        if (world == null || player == null || marriage == null) {
            return null;
        }
        class_2960 kingdomId = class_2960.method_12829((String)marriage.getSpouseKingdomId());
        Kingdom kingdom2 = kingdom = kingdomId == null ? null : GotKingdoms.get(kingdomId);
        if (kingdom == null) {
            return null;
        }
        if (GotDynastyNpcs.FamilyStage.DYNASTY.id().equals(marriage.getSpouseFamilyStageId())) {
            spouse = (AbstractKingdomNpcEntity)GotEntities.DYNASTY_NPC.method_5883((class_1937)world);
            if (spouse instanceof AbstractDynastyNpcEntity) {
                AbstractDynastyNpcEntity dynastyNpc = (AbstractDynastyNpcEntity)spouse;
                DynastyNpcWorldState.FamilyMemberProfile profile = DynastyNpcWorldState.get(world).get(marriage.getSpouseNpcId());
                if (profile != null) {
                    dynastyNpc.setDynastyKingdomId(class_2960.method_60654((String)profile.kingdomId()));
                    dynastyNpc.setDynastyHouseId(profile.houseId());
                    dynastyNpc.setOwnerPlayerUuid(profile.ownerPlayerUuid());
                    dynastyNpc.setOwnerPlayerName(profile.ownerPlayerName());
                    dynastyNpc.setOtherParentName(profile.otherParentName());
                    dynastyNpc.setFamilyStage(GotDynastyNpcs.FamilyStage.DYNASTY);
                    dynastyNpc.setBornDay(profile.bornDay());
                    dynastyNpc.setOwnerSmallArms(profile.ownerSmallArms());
                }
            }
        } else {
            Object entityType;
            Object object = entityType = marriage.isLordMarriage() ? GotEntities.lordTypeForKingdom(kingdom) : GotEntities.civilianTypeForKingdom(kingdom);
            if (entityType == null) {
                return null;
            }
            spouse = (AbstractKingdomNpcEntity)entityType.method_5883((class_1937)world);
        }
        if (spouse == null) {
            return null;
        }
        if (overridePos != null) {
            targetPos = overridePos;
        } else {
            if (destinationCastleId == null || destinationCastleId.isBlank()) {
                return null;
            }
            CastleInstance castle = GotCastles.getWorldState(world).get(destinationCastleId);
            if (castle != null) {
                targetPos = castle.getBannerPos();
            } else {
                VillageInstance village = VillageWorldState.get(world).get(destinationCastleId);
                if (village == null) {
                    return null;
                }
                targetPos = village.getCenterPos();
            }
        }
        spouse.method_5808((double)targetPos.method_10263() + 0.5, targetPos.method_10264(), (double)targetPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
        spouse.method_5826(marriage.getSpouseNpcUuid() == null ? UUID.nameUUIDFromBytes(marriage.getSpouseNpcId().getBytes(StandardCharsets.UTF_8)) : marriage.getSpouseNpcUuid());
        spouse.method_5943((class_5425)world, world.method_8404(targetPos), class_3730.field_16474, null);
        spouse.got$setCastle(destinationCastleId == null ? "" : destinationCastleId);
        spouse.setCastleNpcId(marriage.getSpouseNpcId());
        spouse.setPointOfInterest(targetPos);
        spouse.setMarriedPlayerUuid(player.method_5845());
        spouse.setMarriedPlayerName(player.method_5477().getString());
        spouse.setIndependentMarriageNpc(true);
        spouse.setNobleSpouse(!marriage.isLordMarriage());
        spouse.setBabyCooldownUnlockDay(marriage.getBabyCooldownUnlockDay());
        spouse.setNpcAttributes(marriage.getSpouseAttributes());
        spouse.setNpcAppearance(marriage.getSpouseAppearance());
        spouse.method_5665((class_2561)class_2561.method_43470((String)marriage.getSpouseName()));
        GotNpcMarriage.clearPartyCompanionLoadout(spouse);
        if (!marriage.isLordMarriage()) {
            GotNpcMarriage.applySpouseHouseIdentity(player, spouse);
            marriage.setSpouseName(spouse.method_5477().getString());
        }
        if (GotDynastyNpcs.FamilyStage.DYNASTY.id().equals(marriage.getSpouseFamilyStageId())) {
            GotDynastyNpcs.bindDynastyToMarriedPlayer(world, marriage.getSpouseNpcId(), player);
        }
        world.method_8649((class_1297)spouse);
        if (marriage.isSpouseInParty()) {
            GotNpcMarriage.applyPartyMarriageState(player, spouse, marriage);
        } else {
            GotNpcMarriage.applyNormalMarriageState(player, spouse, marriage);
        }
        return spouse;
    }

    private static void applyNormalMarriageState(class_3222 player, AbstractKingdomNpcEntity spouse, PlayerMarriageProfile marriage) {
        if (player == null || spouse == null || marriage == null) {
            return;
        }
        spouse.setMarriedPlayerUuid(player.method_5845());
        spouse.setMarriedPlayerName(player.method_5477().getString());
        spouse.setIndependentMarriageNpc(true);
        spouse.setMarriagePartyCompanion(false);
        spouse.setBabyCooldownUnlockDay(marriage.getBabyCooldownUnlockDay());
        spouse.clearPartyCommandState();
        GotNpcMarriage.clearPartyCompanionLoadout(spouse);
        if (!marriage.isLordMarriage()) {
            spouse.setNobleSpouse(true);
            GotNpcMarriage.applySpouseHouseIdentity(player, spouse);
            marriage.setSpouseName(spouse.method_5477().getString());
        }
    }

    private static void applyPartyMarriageState(class_3222 player, AbstractKingdomNpcEntity spouse, PlayerMarriageProfile marriage) {
        GotNpcMarriage.applyNormalMarriageState(player, spouse, marriage);
        spouse.setMarriagePartyCompanion(true);
        spouse.setPartyCommandType(CommanderActionType.RECALL);
        spouse.setPartyCommandOrigin(spouse.method_24515());
        spouse.setPartyFormationType(PartyFormationType.LINE);
        spouse.setPartyCommandTarget(player.method_24515());
        spouse.setPartyCommandFacingYaw(player.method_36454());
        GotNpcMarriage.equipPartyCompanionLoadout(player, spouse);
    }

    private static void equipPartyCompanionLoadout(class_3222 player, AbstractKingdomNpcEntity spouse) {
        if (player == null || spouse == null) {
            return;
        }
        class_1792[] armor = GotCastles.resolveArmorForHouse(GotNpcMarriage.resolveCurrentPlayerHouseId(player), spouse.getKingdomId());
        if (armor == null) {
            armor = GotKingdomArmor.armorForKingdom(spouse.getKingdomId());
        }
        spouse.method_5673(class_1304.field_6173, new class_1799((class_1935)GotItems.STEEL_SWORD));
        spouse.method_5673(class_1304.field_6171, class_1799.field_8037);
        if (armor != null && armor.length >= 4) {
            spouse.method_5673(class_1304.field_6169, new class_1799((class_1935)armor[0]));
            spouse.method_5673(class_1304.field_6174, new class_1799((class_1935)armor[1]));
            spouse.method_5673(class_1304.field_6172, new class_1799((class_1935)armor[2]));
            spouse.method_5673(class_1304.field_6166, new class_1799((class_1935)armor[3]));
            return;
        }
        spouse.method_5673(class_1304.field_6169, new class_1799((class_1935)class_1802.field_8743));
        spouse.method_5673(class_1304.field_6174, new class_1799((class_1935)class_1802.field_8523));
        spouse.method_5673(class_1304.field_6172, new class_1799((class_1935)class_1802.field_8396));
        spouse.method_5673(class_1304.field_6166, new class_1799((class_1935)class_1802.field_8660));
    }

    private static void clearPartyCompanionLoadout(AbstractKingdomNpcEntity spouse) {
        if (spouse == null) {
            return;
        }
        spouse.method_5673(class_1304.field_6173, class_1799.field_8037);
        spouse.method_5673(class_1304.field_6171, class_1799.field_8037);
        spouse.method_5673(class_1304.field_6169, class_1799.field_8037);
        spouse.method_5673(class_1304.field_6174, class_1799.field_8037);
        spouse.method_5673(class_1304.field_6172, class_1799.field_8037);
        spouse.method_5673(class_1304.field_6166, class_1799.field_8037);
    }

    private static void teleportSpouseNearPlayer(class_3222 player, AbstractKingdomNpcEntity spouse) {
        if (player == null || spouse == null || player.method_51469() == null) {
            return;
        }
        class_2338 summonPos = GotTroops.findTroopSummonPos(player.method_51469(), player.method_24515(), spouse.method_5628());
        spouse.method_5808((double)summonPos.method_10263() + 0.5, summonPos.method_10264(), (double)summonPos.method_10260() + 0.5, player.method_36454(), spouse.method_36455());
        spouse.setPointOfInterest(summonPos);
        spouse.setPartyCommandTarget(player.method_24515());
    }

    @Nullable
    private static class_2338 resolveKnownSpousePos(class_3218 world, @Nullable String primaryCastleId, @Nullable String fallbackCastleId) {
        class_2338 primary = GotNpcMarriage.resolveCastleOrVillagePos(world, primaryCastleId);
        if (primary != null) {
            return primary;
        }
        return GotNpcMarriage.resolveCastleOrVillagePos(world, fallbackCastleId);
    }

    @Nullable
    private static class_2338 resolveCastleOrVillagePos(class_3218 world, @Nullable String castleId) {
        if (world == null || castleId == null || castleId.isBlank()) {
            return null;
        }
        CastleInstance castle = GotCastles.getWorldState(world).get(castleId);
        if (castle != null) {
            return castle.getBannerPos();
        }
        VillageInstance village = VillageWorldState.get(world).get(castleId);
        return village == null ? null : village.getCenterPos();
    }

    private static void loadChunkAt(class_3218 world, class_2338 pos) {
        if (world == null || pos == null) {
            return;
        }
        world.method_8497(pos.method_10263() >> 4, pos.method_10260() >> 4);
    }

    private static void applySpouseHouseIdentity(class_3222 player, AbstractKingdomNpcEntity npc) {
        String houseName = GotNpcMarriage.resolveHouseName(player.method_51469(), GotNpcMarriage.resolveCurrentPlayerHouseId(player), ((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedKingdomId());
        String surname = GotNpcMarriage.normalizeHouseSurname(houseName);
        if (surname.isBlank()) {
            return;
        }
        String firstName = GotNpcMarriage.extractFirstName(npc.method_5477().getString());
        npc.method_5665((class_2561)class_2561.method_43470((String)(firstName + " " + surname)));
    }

    public static void handleChildbirthDeath(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null) {
            return;
        }
        PlayerMarriageProfile marriage = ((PlayerMarriageHolder)player).got$getMarriageProfile();
        if (player.method_51469() != null && GotDynastyNpcs.FamilyStage.DYNASTY.id().equals(marriage.getSpouseFamilyStageId())) {
            GotDynastyNpcs.clearDynastyMarriageBinding(player.method_51469(), marriage.getSpouseNpcId());
        }
        GotNpcMarriage.restoreTransferredLordCastles(player, marriage, npc);
        npc.setMarriedPlayerUuid("");
        npc.setMarriedPlayerName("");
        npc.setIndependentMarriageNpc(false);
        npc.setNobleSpouse(false);
        npc.setMarriagePartyCompanion(false);
        npc.setBabyCooldownUnlockDay(0L);
        npc.method_31472();
        marriage.clear();
        PlayerHouseService.broadcast(player.method_5682());
    }

    public static String resolveCurrentPlayerHouseId(class_3222 player) {
        PlayerHouseDefinition customHouse = PlayerHouseService.getForParticipant(player.method_51469(), player.method_5667());
        if (customHouse != null) {
            return customHouse.houseId();
        }
        return ((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedOptionId();
    }

    public static String resolveHouseName(class_3218 world, String houseId, String kingdomId) {
        PlayerHouseDefinition customHouse;
        if (PlayerHouseService.isCustomHouseId(houseId) && (customHouse = PlayerHouseService.get(world, houseId)) != null && !customHouse.displayName().isBlank()) {
            return customHouse.displayName();
        }
        CharacterSelectionOption option = GotCharacterOptions.get(houseId);
        if (option != null && !option.houseDisplayName().isBlank()) {
            return option.houseDisplayName();
        }
        class_2960 kingdomIdentifier = class_2960.method_12829((String)kingdomId);
        CharacterSelectionOption fallback = kingdomIdentifier == null ? null : GotCharacterOptions.getByKingdomId(kingdomIdentifier);
        return fallback == null ? "" : fallback.houseDisplayName();
    }

    public static String normalizeHouseSurname(String houseName) {
        if (houseName == null || houseName.isBlank()) {
            return "";
        }
        int parentheticalIndex = houseName.indexOf(40);
        return (parentheticalIndex >= 0 ? houseName.substring(0, parentheticalIndex) : houseName).trim();
    }

    public static String extractFirstName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            return "Unknown";
        }
        int separator = fullName.indexOf(32);
        return separator > 0 ? fullName.substring(0, separator).trim() : fullName.trim();
    }

    public static boolean isNightsWatchPlayer(class_3222 player) {
        if (player == null) {
            return false;
        }
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        return profile != null && "agob:the_nights_watch".equals(profile.getSelectedKingdomId());
    }

    public static boolean isNightsWatchNpc(@Nullable AbstractKingdomNpcEntity npc) {
        return npc != null && npc.getKingdomId() != null && "the_nights_watch".equals(npc.getKingdomId().method_12832());
    }

    private static boolean isNightsWatchMarriageBlocked(class_3222 player, AbstractKingdomNpcEntity npc) {
        return GotNpcMarriage.isNightsWatchPlayer(player) || GotNpcMarriage.isNightsWatchNpc(npc);
    }

    private static long currentMarriageRouteDay(class_3218 world) {
        return world == null ? -1L : world.method_8532() / 24000L;
    }

    public static float charismaSuccessBonus(class_3222 player) {
        if (player == null) {
            return 0.0f;
        }
        int charisma = ((PlayerSkillHolder)player).got$getSkillProfile().get(SkillAttributeType.CHARISMA).getPoints();
        return Math.max(0.0f, (float)charisma * 0.005f);
    }

    private static float baseMarriageSuccessChance(class_3222 player, AbstractKingdomNpcEntity npc) {
        float base = npc != null && npc.getNpcRole() == KingdomNpcRole.LORD ? 0.1f : 0.25f;
        return Math.min(1.0f, base + GotNpcMarriage.charismaSuccessBonus(player));
    }

    private static boolean hasRequiredLordMarriageTitle(class_3222 player) {
        if (player == null) {
            return false;
        }
        ReputationTitle title = ((PlayerReputationHolder)player).got$getReputationProfile().getTitle();
        return title != null && title.getTier() >= 2;
    }

    private static void sendResult(class_3222 player, AbstractKingdomNpcEntity npc, String responseTypeId, long seed, int updatedRelation) {
        String kingdomId = npc.getKingdomId() == null ? "" : npc.getKingdomId().toString();
        GotPlayerKingdomActions.DialogueContext dialogueContext = GotPlayerKingdomActions.chooseDialogueContext(player, kingdomId, updatedRelation, seed);
        ServerPlayNetworking.send((class_3222)player, (class_8710)new NpcChatResultPayload(npc.method_5628(), responseTypeId, seed, updatedRelation, kingdomId, dialogueContext.contextId(), dialogueContext.subjectName()));
    }
}

