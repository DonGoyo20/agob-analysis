/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.networking.v1.PacketSender
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1588
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2680
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3414
 *  net.minecraft.class_3417
 *  net.minecraft.class_3419
 *  net.minecraft.class_3532
 *  net.minecraft.class_3730
 *  net.minecraft.class_5425
 *  net.minecraft.class_5575
 *  net.minecraft.class_7923
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.troop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1588;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2902;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3532;
import net.minecraft.class_3730;
import net.minecraft.class_5425;
import net.minecraft.class_5575;
import net.minecraft.class_7923;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import trep.got.GotMod;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.day.DailyTaskContext;
import trep.got.day.DailyTaskRegistry;
import trep.got.economy.GotEconomy;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.HireableUnitEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.entity.PartyHorseEntity;
import trep.got.entity.PartyTroopEntity;
import trep.got.item.InventoryItemHelper;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationProfile;
import trep.got.kingdom.ReputationTitle;
import trep.got.network.RequestCommanderActionPayload;
import trep.got.network.TroopRosterPayload;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotCastles;
import trep.got.registry.GotEntities;
import trep.got.registry.GotGameRules;
import trep.got.registry.GotItems;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotMainQuests;
import trep.got.troop.CommanderActionType;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.DynamicTroopLoadoutApplier;
import trep.got.troop.DynamicTroopRegistry;
import trep.got.troop.DynamicTroopUnitType;
import trep.got.troop.HireableCampDefinition;
import trep.got.troop.HireableUnitWorldState;
import trep.got.troop.MarriageCompanionFormationCache;
import trep.got.troop.PartyFormationType;
import trep.got.troop.PlayerTroopHolder;
import trep.got.troop.PlayerTroopProfile;
import trep.got.village.VillageInstance;
import trep.got.village.VillageWorldState;
import trep.got.world.dimension.GotDimensionKeys;

public final class GotTroops {
    private static final class_238 PARTY_COMPANION_SCAN_BOX = new class_238(-3.0E7, -128.0, -3.0E7, 3.0E7, 1024.0, 3.0E7);
    private static final Map<UUID, PlayerConflict> PLAYER_CONFLICTS = new HashMap<UUID, PlayerConflict>();
    private static final long DAY_LENGTH = 24000L;
    private static final long HIRE_WINDOW_START = 1000L;
    private static final long HIRE_WINDOW_END = 12000L;
    private static final long HIREABLE_SPAWN_SCAN_INTERVAL_TICKS = 100L;
    private static final long PLAYER_CONFLICT_DURATION_TICKS = 1200L;
    private static final int MAX_SPAWN_RADIUS = 8;
    private static final int FORMATION_GROUP_GAP = 5;
    private static final int CASTLE_HIREABLE_MIN_COUNT = 8;
    private static final int CASTLE_HIREABLE_MAX_COUNT = 10;

    private GotTroops() {
    }

    public static void initialize() {
        MarriageCompanionFormationCache.initialize();
        ServerPlayerEvents.COPY_FROM.register(GotTroops::copyProfile);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            GotTroops.reconcileDeployedTroopsOnJoin(handler.field_14140);
            GotTroops.sendRoster(handler.field_14140, sender);
        });
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            GotTroops.clearPlayerConflicts(handler.field_14140.method_5667());
            GotTroops.cleanupDeployedTroopsOnDisconnect(handler.field_14140);
        });
        ServerTickEvents.END_SERVER_TICK.register(GotTroops::tickServer);
        DailyTaskRegistry.register(class_2960.method_60655((String)"agob", (String)"hireable_units"), GotTroops::runDailyHireableTask);
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(GotTroops::onKilledEntity);
    }

    public static void sendRoster(class_3222 player) {
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        ServerPlayNetworking.send((class_3222)player, (class_8710)GotTroops.createRosterPayload(player, profile));
    }

    private static void sendRoster(class_3222 player, PacketSender sender) {
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        sender.sendPacket((class_8710)GotTroops.createRosterPayload(player, profile));
    }

    private static TroopRosterPayload createRosterPayload(class_3222 player, PlayerTroopProfile profile) {
        List entries = profile.troopCounts().entrySet().stream().sorted(Map.Entry.comparingByKey()).toList();
        String[] troopIds = new String[entries.size()];
        int[] troopCounts = new int[entries.size()];
        int[] troopUpgradeableCounts = new int[entries.size()];
        for (int index = 0; index < entries.size(); ++index) {
            troopIds[index] = (String)entries.get(index).getKey();
            troopCounts[index] = (Integer)entries.get(index).getValue();
            troopUpgradeableCounts[index] = profile.getUpgradeableCount(class_2960.method_12829((String)troopIds[index]));
        }
        ReputationProfile reputation = ((PlayerReputationHolder)player).got$getReputationProfile();
        return new TroopRosterPayload(troopIds, troopCounts, troopUpgradeableCounts, profile.totalTroopCount(), profile.capacityFor(reputation.getTitle()), profile.getUpgradeableCount());
    }

    public static void resetTroopsForPlayer(class_3222 player) {
        if (player == null) {
            return;
        }
        GotTroops.cleanupDeployedTroopsOnDisconnect(player);
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        profile.clear();
        GotTroops.sendRoster(player);
    }

    public static boolean tryHire(class_3222 player, HireableUnitEntity entity) {
        if (player == null || entity == null || !entity.method_5805() || player.method_5858((class_1297)entity) > 36.0) {
            return false;
        }
        DynamicTroopDefinition definition = entity.getTroopDefinition();
        if (definition == null) {
            return false;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        ReputationProfile reputation = ((PlayerReputationHolder)player).got$getReputationProfile();
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        if (!profile.canHireOne(reputation.getTitle())) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Your troop limit is full.").method_27692(class_124.field_1061), true);
            return false;
        }
        if (!definition.meetsReputationRequirement(reputation.getTitle())) {
            player.method_7353((class_2561)class_2561.method_43470((String)definition.reputationRequirementLabel()).method_27692(class_124.field_1061), true);
            return false;
        }
        if (!definition.meetsReligionRequirement(character.getSelectedReligionId())) {
            player.method_7353((class_2561)class_2561.method_43470((String)definition.religionRequirementLabel()).method_27692(class_124.field_1061), true);
            return false;
        }
        if (!definition.canBeHiredByKingdom(character.getSelectedKingdomId())) {
            player.method_7353((class_2561)class_2561.method_43470((String)definition.disallowedHireKingdomsLabel()).method_27692(class_124.field_1061), true);
            return false;
        }
        if (!definition.meetsHouseRequirement(character.getSelectedOptionId())) {
            player.method_7353((class_2561)class_2561.method_43470((String)definition.houseRequirementLabel()).method_27692(class_124.field_1061), true);
            return false;
        }
        class_1792 costItem = GotTroops.resolveHireCostItem(definition);
        int costAmount = GotTroops.resolveHireCostAmount(definition);
        if (GotEconomy.isCurrency(costItem) ? !GotEconomy.hasCurrencyValue((class_1657)player, GotEconomy.priceValue(costItem, costAmount)) : !GotTroops.playerHasItemCount(player, costItem, costAmount)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You cannot afford this troop.").method_27692(class_124.field_1061), true);
            return false;
        }
        if (GotEconomy.isCurrency(costItem)) {
            GotEconomy.payCurrency(player, costItem, costAmount);
        } else {
            GotTroops.removeItems(player, costItem, costAmount);
        }
        profile.addTroop(definition.id(), 1);
        GotTroops.sendRoster(player);
        GotMainQuests.onTroopHired(player);
        entity.discardMount();
        entity.method_31472();
        return true;
    }

    public static int tryUpgradeTroopType(class_3222 player, class_2960 troopId) {
        if (player == null || troopId == null) {
            return 0;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        int upgraded = profile.upgradeEligibleUnits(troopId);
        if (upgraded > 0) {
            GotTroops.syncDeployedTroops(player.method_5682(), player, profile);
            GotTroops.sendRoster(player);
        }
        return upgraded;
    }

    public static boolean tryUpgradeTroopAtBlacksmith(class_3222 player, AbstractKingdomNpcEntity blacksmith, class_2960 troopId, class_2960 targetTroopId) {
        if (player == null || blacksmith == null || troopId == null || targetTroopId == null) {
            return false;
        }
        if (blacksmith.getNpcRole() != KingdomNpcRole.BLACKSMITH || !blacksmith.method_5805() || player.method_5858((class_1297)blacksmith) > 36.0) {
            return false;
        }
        DynamicTroopDefinition source = DynamicTroopRegistry.get(troopId);
        DynamicTroopDefinition target = DynamicTroopRegistry.get(targetTroopId);
        if (source == null || target == null || !source.upgradeTargets().contains(targetTroopId)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"That troop cannot be upgraded here.").method_27692(class_124.field_1061), true);
            return false;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        ReputationTitle currentTitle = ((PlayerReputationHolder)player).got$getReputationProfile().getTitle();
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        String currentReligionId = character.getSelectedReligionId();
        if (!target.meetsReputationRequirement(currentTitle)) {
            player.method_7353((class_2561)class_2561.method_43470((String)target.reputationRequirementLabel()).method_27692(class_124.field_1061), true);
            return false;
        }
        if (!target.meetsReligionRequirement(currentReligionId)) {
            player.method_7353((class_2561)class_2561.method_43470((String)target.religionRequirementLabel()).method_27692(class_124.field_1061), true);
            return false;
        }
        if (!target.canBeHiredByKingdom(character.getSelectedKingdomId())) {
            player.method_7353((class_2561)class_2561.method_43470((String)target.disallowedHireKingdomsLabel()).method_27692(class_124.field_1061), true);
            return false;
        }
        if (!target.meetsHouseRequirement(character.getSelectedOptionId())) {
            player.method_7353((class_2561)class_2561.method_43470((String)target.houseRequirementLabel()).method_27692(class_124.field_1061), true);
            return false;
        }
        if (profile.getCount(troopId) <= 0) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You have no troops of that type for upgrading.").method_27692(class_124.field_1061), true);
            return false;
        }
        int goldCost = GotTroops.goldCostForUpgrade(source, target);
        if (!GotEconomy.hasLegacyGoldIngots((class_1657)player, goldCost)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You do not have enough coin for this upgrade.").method_27692(class_124.field_1061), true);
            return false;
        }
        if (!profile.upgradeFirstAnyUnit(troopId, targetTroopId)) {
            player.method_7353((class_2561)class_2561.method_43470((String)"That troop cannot be upgraded right now.").method_27692(class_124.field_1061), true);
            return false;
        }
        GotEconomy.payLegacyGoldIngots(player, goldCost);
        GotTroops.syncDeployedTroops(player.method_5682(), player, profile);
        GotTroops.sendRoster(player);
        player.method_7353((class_2561)class_2561.method_43470((String)("Upgraded " + source.displayName() + " into " + target.displayName() + " for " + GotEconomy.formatLegacyGoldIngots(goldCost) + ".")).method_27692(class_124.field_1065), true);
        return true;
    }

    public static int trySummonTroops(class_3222 player) {
        return GotTroops.summonTroops(player, false);
    }

    public static int forceSummonTroops(class_3222 player) {
        return GotTroops.summonTroops(player, true);
    }

    private static int summonTroops(class_3222 player, boolean ignoreCooldown) {
        if (player == null || player.method_51469() == null) {
            return 0;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        if (profile.totalTroopCount() <= 0) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You have no troops to summon.").method_27692(class_124.field_1061), true);
            return 0;
        }
        long cooldownTicks = profile.getSummonCooldownTicksRemaining(player.method_51469().method_8510());
        if (!ignoreCooldown && cooldownTicks > 0L) {
            int seconds = class_3532.method_15384((double)((double)cooldownTicks / 20.0));
            player.method_7353((class_2561)class_2561.method_43470((String)("You can summon again in " + seconds + "s.")).method_27692(class_124.field_1061), true);
            return 0;
        }
        List<PartyUnitAssignment> assignments = GotTroops.createAssignments(profile.units());
        if (assignments.isEmpty()) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Your troops could not be summoned.").method_27692(class_124.field_1061), true);
            return 0;
        }
        class_2338 fallbackOrigin = player.method_24515();
        class_243 anchorPos = player.method_19538();
        List<class_2338> slots = GotTroops.calculateFormationSlots(player.method_51469(), anchorPos, player.method_36454(), assignments, profile.getFormationType(), true);
        int affectedUnits = 0;
        int blockedUnits = 0;
        for (int index = 0; index < assignments.size(); ++index) {
            PartyUnitAssignment assignment = assignments.get(index);
            PlayerTroopProfile.TroopUnitInstance unit = assignment.unit();
            class_2338 requestedSlot = index < slots.size() ? slots.get(index) : fallbackOrigin;
            class_2338 slotPos = GotTroops.resolveSafeTroopSummonPos(player.method_51469(), requestedSlot, fallbackOrigin, unit.unitId());
            if (slotPos == null) {
                ++blockedUnits;
                continue;
            }
            PartyTroopEntity deployed = GotTroops.findDeployedTroop(player.method_5682(), player.method_5667(), unit);
            if (deployed != null) {
                GotTroops.applyCommandToTroop(deployed, CommanderActionType.RECALL, slotPos, profile.getFormationType());
                GotTroops.teleportPartyTroopToPos(deployed, player, slotPos);
                ++affectedUnits;
                continue;
            }
            PartyTroopEntity spawned = GotTroops.spawnPartyTroop(player, unit, slotPos, CommanderActionType.RECALL, profile.getFormationType());
            if (spawned == null) continue;
            profile.bindDeployedEntity(unit.unitId(), spawned.method_5667());
            ++affectedUnits;
        }
        if (affectedUnits > 0) {
            profile.markSummoned(player.method_51469().method_8510());
            player.method_51469().method_8396(null, player.method_24515(), class_3417.field_14704, class_3419.field_15248, 0.9f, 1.0f);
            GotTroops.sendRoster(player);
        }
        if (affectedUnits == 0 && blockedUnits > 0) {
            player.method_7353((class_2561)class_2561.method_43470((String)"No safe place found to summon your troops.").method_27692(class_124.field_1061), true);
        }
        return affectedUnits;
    }

    public static int goldCostForUpgrade(DynamicTroopDefinition source, DynamicTroopDefinition target) {
        if (source == null || target == null) {
            return 0;
        }
        return switch (source.unitType()) {
            default -> throw new MatchException(null, null);
            case DynamicTroopUnitType.CAVALRY -> 12;
            case DynamicTroopUnitType.MELEE -> 10;
            case DynamicTroopUnitType.ARCHER -> 8;
        };
    }

    public static void handleCommanderAction(class_3222 player, RequestCommanderActionPayload payload) {
        int slotIndex;
        class_2338 anchor;
        if (player == null || payload == null) {
            return;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        CommanderActionType actionType = CommanderActionType.byId(payload.actionId());
        PartyFormationType formationType = PartyFormationType.byId(payload.formationId());
        profile.setFormationType(formationType);
        List<PartyTroopEntity> deployedTroops = GotTroops.getDeployedTroops(player.method_5682(), player.method_5667(), profile);
        List<AbstractKingdomNpcEntity> companions = GotTroops.getMarriagePartyCompanions(player.method_5682(), player.method_5667());
        if (deployedTroops.isEmpty() && companions.isEmpty()) {
            return;
        }
        if (actionType == CommanderActionType.FOLLOW_ME || actionType == CommanderActionType.HALT) {
            GotTroops.applyImmediateCommanderAction(player, actionType, formationType, deployedTroops, companions);
            GotMainQuests.onCommanderAction(player, actionType);
            return;
        }
        switch (actionType) {
            default: {
                throw new MatchException(null, null);
            }
            case RECALL: {
                class_2338 class_23382 = GotTroops.findRecallAnchor(player.method_51469(), player);
                break;
            }
            case CHARGE: 
            case MOVE: 
            case FORMATION: {
                class_2338 class_23382;
                if (payload.hasTarget()) {
                    class_23382 = payload.targetPos();
                    break;
                }
                class_23382 = null;
                break;
            }
            case FOLLOW_ME: 
            case HALT: {
                class_2338 class_23382 = anchor = null;
            }
        }
        if (anchor == null) {
            return;
        }
        List<PartyUnitAssignment> assignments = GotTroops.createAssignments(profile.units()).stream().filter(assignment -> deployedTroops.stream().anyMatch(troop -> troop.getUnitId() == assignment.unit().unitId())).toList();
        ArrayList<DynamicTroopUnitType> unitTypes = new ArrayList<DynamicTroopUnitType>(assignments.stream().map(assignment -> assignment.definition().unitType()).toList());
        for (int index = 0; index < companions.size(); ++index) {
            unitTypes.add(DynamicTroopUnitType.MELEE);
        }
        List<class_2338> slots = GotTroops.calculateFormationSlotsForUnitTypes((class_1937)player.method_51469(), class_243.method_24955((class_2382)anchor), player.method_36454(), unitTypes, formationType, false);
        for (PartyTroopEntity troop : deployedTroops) {
            int index = GotTroops.findAssignmentIndex(assignments, troop.getUnitId());
            if (index < 0 || index >= slots.size()) continue;
            GotTroops.applyCommandToTroop(troop, actionType == CommanderActionType.FORMATION ? CommanderActionType.MOVE : actionType, slots.get(index), formationType);
        }
        for (int index = 0; index < companions.size() && (slotIndex = assignments.size() + index) < slots.size(); ++index) {
            GotTroops.applyCommandToMarriageCompanion(companions.get(index), actionType == CommanderActionType.FORMATION ? CommanderActionType.MOVE : actionType, slots.get(slotIndex), formationType, player.method_36454());
        }
        GotMainQuests.onCommanderAction(player, actionType);
    }

    private static void applyImmediateCommanderAction(class_3222 player, CommanderActionType actionType, PartyFormationType formationType, List<PartyTroopEntity> deployedTroops, List<AbstractKingdomNpcEntity> companions) {
        for (PartyTroopEntity troop : deployedTroops) {
            troop.setCommandType(actionType);
            troop.setCommandOrigin(troop.method_24515());
            troop.setCommandTarget(actionType == CommanderActionType.HALT ? troop.method_24515() : null);
            troop.setFormationType(formationType);
            troop.setTargetEntityUuid(null);
            troop.setTargetGatePos(null);
            troop.method_5942().method_6340();
            class_1297 class_12972 = troop.method_5854();
            if (!(class_12972 instanceof PartyHorseEntity)) continue;
            PartyHorseEntity horse = (PartyHorseEntity)class_12972;
            horse.method_5942().method_6340();
        }
        for (AbstractKingdomNpcEntity companion : companions) {
            companion.setPartyCommandType(actionType);
            companion.setPartyCommandOrigin(companion.method_24515());
            companion.setPartyCommandTarget(actionType == CommanderActionType.HALT ? companion.method_24515() : null);
            companion.setPartyFormationType(formationType);
            companion.setPartyTargetEntityUuid(null);
            companion.setPartyTargetGatePos(null);
            companion.setPartyCommandFacingYaw(player.method_36454());
            companion.method_5942().method_6340();
        }
    }

    public static void handleCommanderEntityTarget(class_3222 player, int targetEntityId, float facingYaw) {
        if (player == null || player.method_37908() == null) {
            return;
        }
        class_1297 target = player.method_37908().method_8469(targetEntityId);
        if (target == null || !target.method_5805()) {
            return;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        List<PartyTroopEntity> deployedTroops = GotTroops.getDeployedTroops(player.method_5682(), player.method_5667(), profile);
        List<AbstractKingdomNpcEntity> companions = GotTroops.getMarriagePartyCompanions(player.method_5682(), player.method_5667());
        if (deployedTroops.isEmpty() && companions.isEmpty()) {
            return;
        }
        for (PartyTroopEntity troop : deployedTroops) {
            troop.setCommandType(CommanderActionType.CHARGE);
            troop.setCommandOrigin(troop.method_24515());
            troop.setFormationType(profile.getFormationType());
            troop.setTargetEntityUuid(target.method_5667());
            troop.setTargetGatePos(null);
            troop.setCommandFacingYaw(facingYaw);
        }
        for (AbstractKingdomNpcEntity companion : companions) {
            companion.setPartyCommandType(CommanderActionType.CHARGE);
            companion.setPartyCommandOrigin(companion.method_24515());
            companion.setPartyFormationType(profile.getFormationType());
            companion.setPartyTargetEntityUuid(target.method_5667());
            companion.setPartyTargetGatePos(null);
            companion.setPartyCommandFacingYaw(facingYaw);
        }
        GotMainQuests.onCommanderAction(player, CommanderActionType.CHARGE);
    }

    public static void handleCommanderBlockTarget(class_3222 player, class_2338 targetPos, float facingYaw) {
        if (player == null || player.method_51469() == null || targetPos == null) {
            return;
        }
        if (!player.method_51469().method_8320(targetPos).method_27852(GotBlocks.GATE_FENCE)) {
            return;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        List<PartyTroopEntity> deployedTroops = GotTroops.getDeployedTroops(player.method_5682(), player.method_5667(), profile);
        List<AbstractKingdomNpcEntity> companions = GotTroops.getMarriagePartyCompanions(player.method_5682(), player.method_5667());
        if (deployedTroops.isEmpty() && companions.isEmpty()) {
            return;
        }
        for (PartyTroopEntity troop : deployedTroops) {
            troop.setCommandType(CommanderActionType.CHARGE);
            troop.setCommandOrigin(troop.method_24515());
            troop.setFormationType(profile.getFormationType());
            troop.setTargetEntityUuid(null);
            troop.setTargetGatePos(targetPos);
            troop.setCommandTarget(GotTroops.calculateTargetFormationSlot(player.method_51469(), player.method_5667(), troop.getUnitId(), class_243.method_24955((class_2382)targetPos), facingYaw, profile.getFormationType()));
            troop.setCommandFacingYaw(facingYaw);
        }
        List<class_2338> companionSlots = GotTroops.calculateFormationSlotsForUnitTypes((class_1937)player.method_51469(), class_243.method_24955((class_2382)targetPos), facingYaw, Collections.nCopies(companions.size(), DynamicTroopUnitType.MELEE), profile.getFormationType(), false);
        for (int index = 0; index < companions.size(); ++index) {
            AbstractKingdomNpcEntity companion = companions.get(index);
            companion.setPartyCommandType(CommanderActionType.CHARGE);
            companion.setPartyCommandOrigin(companion.method_24515());
            companion.setPartyFormationType(profile.getFormationType());
            companion.setPartyTargetEntityUuid(null);
            companion.setPartyTargetGatePos(targetPos);
            companion.setPartyCommandTarget(index < companionSlots.size() ? companionSlots.get(index) : targetPos);
            companion.setPartyCommandFacingYaw(facingYaw);
        }
        GotMainQuests.onCommanderAction(player, CommanderActionType.CHARGE);
    }

    public static int tryStoreTroops(class_3222 player) {
        if (player == null || player.method_5682() == null) {
            return 0;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        int storedUnits = 0;
        for (PlayerTroopProfile.TroopUnitInstance unit : profile.units()) {
            PartyTroopEntity deployed = GotTroops.findDeployedTroop(player.method_5682(), player.method_5667(), unit);
            if (deployed == null) {
                if (unit.deployedEntityUuid() == null) continue;
                profile.clearDeployedEntity(unit.unitId());
                continue;
            }
            profile.clearDeployedEntity(unit.unitId());
            class_1297 class_12972 = deployed.method_5854();
            if (class_12972 instanceof PartyHorseEntity) {
                PartyHorseEntity horse = (PartyHorseEntity)class_12972;
                horse.method_31472();
            }
            deployed.method_31472();
            ++storedUnits;
        }
        if (storedUnits > 0) {
            player.method_51469().method_8396(null, player.method_24515(), (class_3414)class_3417.field_14862.comp_349(), class_3419.field_15248, 0.9f, 1.0f);
            GotTroops.sendRoster(player);
        }
        return storedUnits;
    }

    public static void onPartyTroopKilled(PartyTroopEntity entity) {
        class_1937 class_19372;
        if (entity == null || entity.getOwnerUuid() == null || !((class_19372 = entity.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        class_3222 owner = serverWorld.method_8503().method_3760().method_14602(entity.getOwnerUuid());
        if (owner == null) {
            return;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)owner).got$getTroopProfile();
        if (profile.removeUnitById(entity.getUnitId())) {
            GotTroops.sendRoster(owner);
        }
    }

    public static class_1792 resolveHireCostItem(DynamicTroopDefinition definition) {
        class_2960 itemId = definition != null && definition.hireCostItem() != null ? definition.hireCostItem() : GotEconomy.currencyId(GotItems.GOLDEN_DRAGON);
        class_1792 item = (class_1792)class_7923.field_41178.method_10223(itemId);
        if (item == class_1802.field_8162) {
            GotMod.LOGGER.error("Invalid hire cost item: {}", (Object)itemId);
            return GotItems.GOLDEN_DRAGON;
        }
        return item;
    }

    public static int resolveHireCostAmount(DynamicTroopDefinition definition) {
        if (definition == null) {
            return 1;
        }
        if (definition.hireCostAmount() > 0) {
            return definition.hireCostAmount();
        }
        int base = switch (definition.unitType()) {
            default -> throw new MatchException(null, null);
            case DynamicTroopUnitType.MELEE -> 2;
            case DynamicTroopUnitType.ARCHER -> 3;
            case DynamicTroopUnitType.CAVALRY -> 5;
        };
        return (base + Math.max(0, definition.tier() - 1) * 2) * 2;
    }

    private static void copyProfile(class_3222 oldPlayer, class_3222 newPlayer, boolean alive) {
        ((PlayerTroopHolder)newPlayer).got$getTroopProfile().copyFrom(((PlayerTroopHolder)oldPlayer).got$getTroopProfile());
    }

    private static void onKilledEntity(class_3218 world, class_1297 entity, class_1297 killedEntity) {
        int experience;
        class_3222 player;
        block6: {
            block5: {
                if (!(entity instanceof class_3222)) break block5;
                player = (class_3222)entity;
                if (killedEntity != null && killedEntity != player) break block6;
            }
            return;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        if (profile.totalTroopCount() <= 0) {
            return;
        }
        int n = experience = killedEntity instanceof class_1588 ? 10 : 5;
        if (profile.addExperienceToNextUnit(experience) > 0) {
            GotTroops.sendRoster(player);
        }
    }

    public static void onPartyOwnerDamaged(class_3222 owner, @Nullable class_1297 attacker) {
        class_1309 livingAttacker;
        class_3222 hostilePlayer;
        if (owner == null || attacker == null || owner.method_51469() == null || !owner.method_5805()) {
            return;
        }
        if (GotTroops.areAlliedPartyEntities((class_1297)owner, attacker)) {
            return;
        }
        UUID hostileOwnerUuid = GotTroops.partyOwnerUuid(attacker);
        if (hostileOwnerUuid != null && !hostileOwnerUuid.equals(owner.method_5667())) {
            GotTroops.registerPlayerConflict(owner, hostileOwnerUuid, attacker.method_5667());
            return;
        }
        if (attacker instanceof class_3222 && (hostilePlayer = (class_3222)attacker).method_5805() && hostilePlayer.method_7256((class_1657)owner)) {
            GotTroops.registerPlayerConflict(owner, hostilePlayer.method_5667(), hostilePlayer.method_5667());
            return;
        }
        if (attacker instanceof class_1309 && (livingAttacker = (class_1309)attacker).method_5805()) {
            GotTroops.assignDefensiveTarget(owner, livingAttacker.method_5667());
        }
    }

    private static void registerPlayerConflict(class_3222 owner, UUID hostileOwnerUuid, UUID directTargetUuid) {
        long expiresAt = owner.method_51469().method_8510() + 1200L;
        PLAYER_CONFLICTS.put(owner.method_5667(), new PlayerConflict(hostileOwnerUuid, directTargetUuid, expiresAt));
        GotTroops.assignDefensiveTarget(owner, directTargetUuid != null ? directTargetUuid : hostileOwnerUuid);
    }

    private static void clearPlayerConflicts(UUID playerUuid) {
        if (playerUuid == null) {
            return;
        }
        PLAYER_CONFLICTS.remove(playerUuid);
        PLAYER_CONFLICTS.entrySet().removeIf(entry -> playerUuid.equals(((PlayerConflict)entry.getValue()).hostileOwnerUuid()));
    }

    private static void assignDefensiveTarget(class_3222 owner, UUID targetUuid) {
        if (owner == null || targetUuid == null || owner.method_5682() == null) {
            return;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)owner).got$getTroopProfile();
        PartyFormationType formationType = profile.getFormationType();
        for (PartyTroopEntity troop : GotTroops.getDeployedTroops(owner.method_5682(), owner.method_5667(), profile)) {
            troop.setCommandType(CommanderActionType.CHARGE);
            troop.setCommandOrigin(troop.method_24515());
            troop.setFormationType(formationType);
            troop.setTargetEntityUuid(targetUuid);
            troop.setTargetGatePos(null);
            troop.setCommandFacingYaw(owner.method_36454());
        }
        for (AbstractKingdomNpcEntity companion : GotTroops.getMarriagePartyCompanions(owner.method_5682(), owner.method_5667())) {
            companion.setPartyCommandType(CommanderActionType.CHARGE);
            companion.setPartyCommandOrigin(companion.method_24515());
            companion.setPartyFormationType(formationType);
            companion.setPartyTargetEntityUuid(targetUuid);
            companion.setPartyTargetGatePos(null);
            companion.setPartyCommandFacingYaw(owner.method_36454());
        }
    }

    @Nullable
    public static class_1309 findPlayerConflictTarget(class_3218 world, UUID ownerUuid, class_1309 seeker) {
        class_3222 hostilePlayer;
        class_1309 livingTarget;
        class_1297 directTarget;
        if (world == null || ownerUuid == null || seeker == null) {
            return null;
        }
        PlayerConflict conflict = PLAYER_CONFLICTS.get(ownerUuid);
        if (conflict == null) {
            return null;
        }
        if (conflict.expiresAtTick() <= world.method_8510()) {
            PLAYER_CONFLICTS.remove(ownerUuid);
            return null;
        }
        ArrayList<Object> candidates = new ArrayList<Object>();
        class_1297 class_12972 = directTarget = conflict.directTargetUuid() == null ? null : world.method_14190(conflict.directTargetUuid());
        if (directTarget instanceof class_1309 && (livingTarget = (class_1309)directTarget).method_5805() && !GotTroops.areAlliedPartyEntities((class_1297)seeker, (class_1297)livingTarget)) {
            candidates.add(livingTarget);
        }
        if ((hostilePlayer = world.method_8503().method_3760().method_14602(conflict.hostileOwnerUuid())) != null && hostilePlayer.method_5805() && hostilePlayer.method_37908() == world && !GotTroops.areAlliedPartyEntities((class_1297)seeker, (class_1297)hostilePlayer)) {
            candidates.add(hostilePlayer);
        }
        class_238 searchBox = seeker.method_5829().method_1009(48.0, 16.0, 48.0);
        candidates.addAll(world.method_18023(class_5575.method_31795(PartyTroopEntity.class), searchBox, troop -> troop.method_5805() && conflict.hostileOwnerUuid().equals(troop.getOwnerUuid()) && !GotTroops.areAlliedPartyEntities((class_1297)seeker, (class_1297)troop)));
        candidates.addAll(world.method_18023(class_5575.method_31795(AbstractKingdomNpcEntity.class), searchBox, npc -> npc.method_5805() && npc.isMarriagePartyCompanion() && conflict.hostileOwnerUuid().toString().equals(npc.getMarriedPlayerUuid()) && !GotTroops.areAlliedPartyEntities((class_1297)seeker, (class_1297)npc)));
        return candidates.stream().filter(candidate -> candidate != seeker).min(Comparator.comparingDouble(arg_0 -> ((class_1309)seeker).method_5858(arg_0))).orElse(null);
    }

    public static boolean areAlliedPartyEntities(@Nullable class_1297 first, @Nullable class_1297 second) {
        if (first == null || second == null || first == second) {
            return false;
        }
        UUID firstOwner = GotTroops.partyOwnerUuid(first);
        UUID secondOwner = GotTroops.partyOwnerUuid(second);
        return firstOwner != null && firstOwner.equals(secondOwner);
    }

    @Nullable
    private static UUID partyOwnerUuid(class_1297 entity) {
        AbstractKingdomNpcEntity npc;
        if (entity instanceof PartyTroopEntity) {
            PartyTroopEntity troop = (PartyTroopEntity)entity;
            return troop.getOwnerUuid();
        }
        if (entity instanceof PartyHorseEntity) {
            PartyHorseEntity horse = (PartyHorseEntity)entity;
            return horse.method_6139();
        }
        if (entity instanceof class_3222) {
            class_3222 player = (class_3222)entity;
            return player.method_5667();
        }
        if (entity instanceof AbstractKingdomNpcEntity && (npc = (AbstractKingdomNpcEntity)entity).isMarriagePartyCompanion()) {
            try {
                return UUID.fromString(npc.getMarriedPlayerUuid());
            }
            catch (IllegalArgumentException exception) {
                return null;
            }
        }
        return null;
    }

    public static class_2338 findTroopSummonPos(class_3218 world, class_2338 origin, int seed) {
        class_2338 safePos = GotTroops.findSafeTroopSummonPos(world, origin, seed);
        return safePos != null ? safePos : origin.method_10084();
    }

    @Nullable
    private static class_2338 findSafeTroopSummonPos(class_3218 world, class_2338 origin, int seed) {
        for (int attempt = 0; attempt < 12; ++attempt) {
            double angle = (double)(seed + attempt) * 0.78539816339 % (Math.PI * 2);
            int radius = 2 + attempt % 4;
            int x = origin.method_10263() + class_3532.method_15357((double)(Math.cos(angle) * (double)radius));
            int z = origin.method_10260() + class_3532.method_15357((double)(Math.sin(angle) * (double)radius));
            int y = GotTroops.findNearbyGroundY((class_1937)world, origin.method_10264(), x, z);
            class_2338 feetPos = new class_2338(x, y, z);
            if (!GotTroops.isSafeSummonPos((class_1937)world, feetPos)) continue;
            return feetPos;
        }
        return null;
    }

    private static void tickServer(MinecraftServer server) {
        class_3218 world = server.method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null || world.method_8510() % 100L != 0L) {
            return;
        }
        HireableUnitWorldState hireableState = HireableUnitWorldState.get(world);
        hireableState.tick(world.method_8510(), world.method_8532(), GotGameRules.affectedByTimeSkip(world));
        long timeOfDay = world.method_8532() % 24000L;
        if (timeOfDay < 1000L || timeOfDay > 12000L) {
            return;
        }
        long currentDay = hireableState.getSpawnCycle();
        CastleWorldState state = GotCastles.getWorldState(world);
        VillageWorldState villageState = VillageWorldState.get(world);
        HashSet<String> processedCastleIds = new HashSet<String>();
        HashSet<String> processedVillageIds = new HashSet<String>();
        HashSet<String> processedCampIds = new HashSet<String>();
        for (class_3222 player : world.method_18456()) {
            HireableCampDefinition camp;
            Kingdom kingdom;
            VillageInstance village;
            Kingdom kingdom2;
            if (player == null || !player.method_5805() || player.method_7325()) continue;
            class_2338 playerPos = player.method_24515();
            CastleInstance castle = state.findClaimAt(playerPos);
            if (castle != null && castle.isNaturalClaim() && processedCastleIds.add(castle.getId()) && (kingdom2 = GotKingdoms.get(class_2960.method_12829((String)castle.getOwnerKingdomId()))) != null) {
                GotTroops.spawnCastleHireablesForCycle(world, hireableState, castle, kingdom2, currentDay, playerPos);
            }
            if ((village = villageState.findClaimAt(playerPos)) != null && processedVillageIds.add(village.getId()) && (kingdom = GotKingdoms.get(class_2960.method_12829((String)village.getKingdomId()))) != null) {
                GotTroops.spawnVillageHireablesForCycle(world, hireableState, village, kingdom, currentDay, playerPos);
            }
            if (castle == null || !processedCampIds.add(castle.getId()) || (camp = DynamicTroopRegistry.getHireableCamp(castle.getDefinitionId())) == null) continue;
            GotTroops.spawnHireableCampForCycle(world, hireableState, castle, camp, currentDay, playerPos);
        }
    }

    private static void runDailyHireableTask(class_3218 world, DailyTaskContext context) {
        if (!GotDimensionKeys.ICEANDFIRE.equals((Object)world.method_27983())) {
            return;
        }
        HireableUnitWorldState hireableState = HireableUnitWorldState.get(world);
        hireableState.tick(world.method_8510(), world.method_8532(), GotGameRules.affectedByTimeSkip(world));
        long timeOfDay = world.method_8532() % 24000L;
        long currentDay = hireableState.getSpawnCycle();
        boolean activeWindow = timeOfDay >= 1000L && timeOfDay <= 12000L;
        GotTroops.cleanupExpiredHireables(world, hireableState, timeOfDay, currentDay, activeWindow);
    }

    public static boolean shouldExpireHireable(class_3218 world, HireableUnitEntity entity) {
        if (world == null || entity == null || !entity.method_5805()) {
            return false;
        }
        HireableUnitWorldState hireableState = HireableUnitWorldState.get(world);
        hireableState.tick(world.method_8510(), world.method_8532(), GotGameRules.affectedByTimeSkip(world));
        long timeOfDay = world.method_8532() % 24000L;
        long currentDay = hireableState.getSpawnCycle();
        boolean activeWindow = timeOfDay >= 1000L && timeOfDay <= 12000L;
        return GotTroops.shouldExpireHireable(entity, currentDay, activeWindow);
    }

    private static boolean shouldExpireHireable(HireableUnitEntity entity, long currentDay, boolean activeWindow) {
        if (entity == null || !entity.method_5805()) {
            return false;
        }
        if (entity.getSpawnDay() < 0L || entity.got$getCastle().isBlank()) {
            return true;
        }
        return !activeWindow || entity.getSpawnDay() != currentDay;
    }

    private static void cleanupExpiredHireables(class_3218 world, HireableUnitWorldState hireableState, long timeOfDay, long currentDay, boolean activeWindow) {
        for (HireableUnitEntity entity : world.method_18198(class_5575.method_31795(HireableUnitEntity.class), hireable -> true)) {
            if (!GotTroops.shouldExpireHireable(entity, currentDay, activeWindow)) continue;
            entity.discardMount();
            entity.method_31472();
        }
        GotTroops.cleanupOrphanedHireableMounts(world);
    }

    public static void clearHireablesForSite(class_3218 world, String siteId) {
        if (world == null || siteId == null || siteId.isBlank()) {
            return;
        }
        for (HireableUnitEntity entity : world.method_18198(class_5575.method_31795(HireableUnitEntity.class), hireable -> siteId.equals(hireable.got$getCastle()))) {
            entity.discardMount();
            entity.method_31472();
        }
        GotTroops.cleanupOrphanedHireableMounts(world);
    }

    private static void spawnCastleHireablesForCycle(class_3218 world, HireableUnitWorldState hireableState, CastleInstance castle, Kingdom kingdom, long currentDay, class_2338 activePos) {
        if (hireableState.hasSpawnedForDay(castle.getId(), currentDay)) {
            GotTroops.trimCastleHireablesForCycle(world, castle, currentDay);
            return;
        }
        List existingCycleUnits = world.method_18198(class_5575.method_31795(HireableUnitEntity.class), entity -> castle.getId().equals(entity.got$getCastle()) && entity.getSpawnDay() == currentDay);
        if (!existingCycleUnits.isEmpty()) {
            GotTroops.trimCastleHireablesForCycle(existingCycleUnits, castle, currentDay);
            hireableState.markSpawnedForDay(castle.getId(), currentDay);
            return;
        }
        int desiredCount = GotTroops.desiredCastleHireableCount(castle.getId(), currentDay);
        List<DynamicTroopDefinition> hireablePool = GotTroops.getCastleHireablePool(kingdom);
        if (hireablePool.isEmpty()) {
            return;
        }
        int spawnedCount = 0;
        class_2338 pointOfInterest = GotTroops.resolveActiveHireablePointOfInterest(castle.getBannerPos(), activePos);
        for (int index = 0; index < desiredCount; ++index) {
            DynamicTroopDefinition definition = hireablePool.get(Math.floorMod(index + (int)currentDay, hireablePool.size()));
            if (!GotTroops.spawnHireable(world, castle.getId(), pointOfInterest, definition, currentDay, 500 + index)) continue;
            ++spawnedCount;
        }
        if (spawnedCount > 0) {
            hireableState.markSpawnedForDay(castle.getId(), currentDay);
        }
    }

    private static void spawnVillageHireablesForCycle(class_3218 world, HireableUnitWorldState hireableState, VillageInstance village, Kingdom kingdom, long currentDay, class_2338 activePos) {
        if (hireableState.hasSpawnedForDay(village.getId(), currentDay)) {
            GotTroops.trimHireablesForCycle(world, village.getId(), currentDay);
            return;
        }
        List<? extends HireableUnitEntity> existingCycleUnits = GotTroops.getExistingHireablesForCycle(world, village.getId(), currentDay);
        if (!existingCycleUnits.isEmpty()) {
            GotTroops.trimHireablesForCycle(existingCycleUnits, village.getId(), currentDay);
            hireableState.markSpawnedForDay(village.getId(), currentDay);
            return;
        }
        List<DynamicTroopDefinition> hireablePool = GotTroops.getVillageHireablePool(kingdom);
        if (hireablePool.isEmpty()) {
            return;
        }
        int desiredCount = 1 + Math.floorMod(village.getId().hashCode() * 31 + (int)currentDay, 5);
        int spawnedCount = 0;
        class_2338 pointOfInterest = GotTroops.resolveActiveHireablePointOfInterest(village.getCenterPos(), activePos);
        for (int index = 0; index < desiredCount; ++index) {
            DynamicTroopDefinition definition = hireablePool.get(Math.floorMod(index + (int)currentDay, hireablePool.size()));
            if (!GotTroops.spawnHireable(world, village.getId(), pointOfInterest, definition, currentDay, 900 + index)) continue;
            ++spawnedCount;
        }
        if (spawnedCount > 0) {
            hireableState.markSpawnedForDay(village.getId(), currentDay);
        }
    }

    private static void spawnHireableCampForCycle(class_3218 world, HireableUnitWorldState hireableState, CastleInstance claim, HireableCampDefinition camp, long currentDay, class_2338 activePos) {
        if (hireableState.hasSpawnedForDay(claim.getId(), currentDay)) {
            GotTroops.trimHireablesForCycle(world, claim.getId(), currentDay, camp.desiredSpawnCount(claim.getId(), currentDay));
            return;
        }
        List<? extends HireableUnitEntity> existingCycleUnits = GotTroops.getExistingHireablesForCycle(world, claim.getId(), currentDay);
        if (!existingCycleUnits.isEmpty()) {
            GotTroops.trimHireablesForCycle(existingCycleUnits, claim.getId(), currentDay, camp.desiredSpawnCount(claim.getId(), currentDay));
            hireableState.markSpawnedForDay(claim.getId(), currentDay);
            return;
        }
        List<DynamicTroopDefinition> hireablePool = GotTroops.getHireableCampPool(camp);
        if (hireablePool.isEmpty()) {
            return;
        }
        int desiredCount = camp.desiredSpawnCount(claim.getId(), currentDay);
        int spawnedCount = 0;
        class_2338 pointOfInterest = GotTroops.resolveActiveHireablePointOfInterest(claim.getBannerPos(), activePos);
        for (int index = 0; index < desiredCount; ++index) {
            DynamicTroopDefinition definition = hireablePool.get(Math.floorMod(index + (int)currentDay, hireablePool.size()));
            if (!GotTroops.spawnHireable(world, claim.getId(), pointOfInterest, definition, currentDay, 1300 + index)) continue;
            ++spawnedCount;
        }
        if (spawnedCount > 0) {
            hireableState.markSpawnedForDay(claim.getId(), currentDay);
        }
    }

    private static void trimCastleHireablesForCycle(class_3218 world, CastleInstance castle, long currentDay) {
        GotTroops.trimHireablesForCycle(world, castle.getId(), currentDay, GotTroops.desiredCastleHireableCount(castle.getId(), currentDay));
    }

    private static void trimCastleHireablesForCycle(List<? extends HireableUnitEntity> existingCycleUnits, CastleInstance castle, long currentDay) {
        GotTroops.trimHireablesForCycle(existingCycleUnits, castle.getId(), currentDay, GotTroops.desiredCastleHireableCount(castle.getId(), currentDay));
    }

    private static void trimHireablesForCycle(class_3218 world, String siteId, long currentDay) {
        GotTroops.trimHireablesForCycle(world, siteId, currentDay, GotTroops.desiredCountForSite(siteId, currentDay, 1, 5));
    }

    private static void trimHireablesForCycle(List<? extends HireableUnitEntity> existingCycleUnits, String siteId, long currentDay) {
        GotTroops.trimHireablesForCycle(existingCycleUnits, siteId, currentDay, GotTroops.desiredCountForSite(siteId, currentDay, 1, 5));
    }

    private static void trimHireablesForCycle(class_3218 world, String siteId, long currentDay, int desiredCount) {
        GotTroops.trimHireablesForCycle(GotTroops.getExistingHireablesForCycle(world, siteId, currentDay), siteId, currentDay, desiredCount);
    }

    private static void trimHireablesForCycle(List<? extends HireableUnitEntity> existingCycleUnits, String siteId, long currentDay, int desiredCount) {
        if (existingCycleUnits.size() <= desiredCount) {
            GotTroops.cleanupOrphanedHireableMounts(existingCycleUnits.isEmpty() ? null : (class_3218)existingCycleUnits.getFirst().method_37908());
            return;
        }
        ArrayList<? extends HireableUnitEntity> sortedUnits = new ArrayList<HireableUnitEntity>(existingCycleUnits);
        sortedUnits.sort(Comparator.comparingInt(entity -> entity.field_6012));
        for (int index = desiredCount; index < sortedUnits.size(); ++index) {
            ((HireableUnitEntity)sortedUnits.get(index)).discardMount();
            ((HireableUnitEntity)sortedUnits.get(index)).method_31472();
        }
        GotTroops.cleanupOrphanedHireableMounts(sortedUnits.isEmpty() ? null : (class_3218)((HireableUnitEntity)sortedUnits.getFirst()).method_37908());
    }

    private static void cleanupOrphanedHireableMounts(class_3218 world) {
        if (world == null) {
            return;
        }
        for (PartyHorseEntity horse2 : world.method_18198(class_5575.method_31795(PartyHorseEntity.class), horse -> horse.method_5805() && horse.method_6139() == null && horse.getUnitId() < 0 && !horse.method_5782())) {
            horse2.method_31472();
        }
    }

    private static List<? extends HireableUnitEntity> getExistingHireablesForCycle(class_3218 world, String siteId, long currentDay) {
        return world.method_18198(class_5575.method_31795(HireableUnitEntity.class), entity -> siteId.equals(entity.got$getCastle()) && entity.getSpawnDay() == currentDay);
    }

    private static List<DynamicTroopDefinition> getCastleHireablePool(Kingdom kingdom) {
        return DynamicTroopRegistry.getByKingdom(kingdom.getId()).stream().filter(definition -> definition.tier() == 1).filter(definition -> !definition.villageSpawnOnly()).sorted(Comparator.comparing(definition -> definition.id().toString())).toList();
    }

    private static List<DynamicTroopDefinition> getVillageHireablePool(Kingdom kingdom) {
        return DynamicTroopRegistry.getByKingdom(kingdom.getId()).stream().filter(definition -> definition.tier() == 1).filter(DynamicTroopDefinition::villageSpawnOnly).sorted(Comparator.comparing(definition -> definition.id().toString())).toList();
    }

    private static List<DynamicTroopDefinition> getHireableCampPool(HireableCampDefinition camp) {
        return camp.troopIds().stream().map(DynamicTroopRegistry::get).filter(Objects::nonNull).toList();
    }

    private static int desiredCountForSite(String siteId, long currentDay, int min, int max) {
        int clampedMin = Math.max(0, min);
        int clampedMax = Math.max(clampedMin, max);
        if (clampedMax <= clampedMin) {
            return clampedMin;
        }
        return clampedMin + Math.floorMod(siteId.hashCode() * 31 + (int)currentDay, clampedMax - clampedMin + 1);
    }

    private static int desiredCastleHireableCount(String siteId, long currentDay) {
        return GotTroops.desiredCountForSite(siteId, currentDay, 8, 10);
    }

    private static boolean spawnHireable(class_3218 world, String siteId, class_2338 pointOfInterest, DynamicTroopDefinition definition, long currentDay, int seedOffset) {
        HireableUnitEntity entity = (HireableUnitEntity)GotEntities.HIREABLE_UNIT.method_5883((class_1937)world);
        if (entity == null) {
            return false;
        }
        class_2338 spawnPos = GotTroops.findSpawnPos(world, pointOfInterest, seedOffset);
        entity.got$setCastle(siteId);
        entity.setPointOfInterest(pointOfInterest);
        entity.setSpawnDay(currentDay);
        entity.setTroopId(definition.id().toString());
        entity.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, world.field_9229.method_43057() * 360.0f, 0.0f);
        entity.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16467, null);
        if (!world.method_8649((class_1297)entity)) {
            GotMod.LOGGER.warn("Failed to spawn hireable troop {} for {} at {}", new Object[]{definition.id(), siteId, spawnPos.method_23854()});
            return false;
        }
        GotTroops.attachHireableCavalryMountIfNeeded(world, entity, spawnPos);
        return true;
    }

    private static PartyTroopEntity spawnPartyTroop(class_3222 player, PlayerTroopProfile.TroopUnitInstance unit, class_2338 spawnPos, CommanderActionType commandType, PartyFormationType formationType) {
        class_3218 world = player.method_51469();
        if (!GotTroops.isSafeSummonPos((class_1937)world, spawnPos)) {
            GotMod.LOGGER.warn("Refusing to summon party troop {} for {} at unsafe position {}", new Object[]{unit.unitId(), player.method_5477().getString(), spawnPos.method_23854()});
            return null;
        }
        PartyTroopEntity entity = (PartyTroopEntity)GotEntities.PARTY_TROOP.method_5883((class_1937)world);
        if (entity == null) {
            return null;
        }
        entity.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, player.method_36454(), 0.0f);
        entity.method_5943((class_5425)world, world.method_8404(spawnPos), class_3730.field_16467, null);
        entity.setOwnerUuid(player.method_5667());
        entity.setUnitId(unit.unitId());
        entity.setTroopId(unit.troopId());
        GotTroops.applyCommandToTroop(entity, commandType, spawnPos, formationType);
        if (!world.method_8649((class_1297)entity)) {
            GotMod.LOGGER.warn("Failed to spawn party troop {} ({}) for {} at {}", new Object[]{unit.unitId(), unit.troopId(), player.method_5477().getString(), spawnPos.method_23854()});
            return null;
        }
        GotTroops.attachCavalryMountIfNeeded(world, player, entity, spawnPos);
        return entity;
    }

    private static PartyTroopEntity findDeployedTroop(MinecraftServer server, UUID ownerUuid, PlayerTroopProfile.TroopUnitInstance unit) {
        if (server == null || ownerUuid == null || unit == null) {
            return null;
        }
        if (unit.deployedEntityUuid() != null) {
            for (class_3218 world : server.method_3738()) {
                PartyTroopEntity partyTroop;
                class_1297 entity = world.method_14190(unit.deployedEntityUuid());
                if (!(entity instanceof PartyTroopEntity) || !ownerUuid.equals((partyTroop = (PartyTroopEntity)entity).getOwnerUuid()) || partyTroop.getUnitId() != unit.unitId() || !partyTroop.method_5805()) continue;
                return partyTroop;
            }
        }
        return GotTroops.findDeployedTroopByUnitId(server, ownerUuid, unit.unitId());
    }

    private static void teleportPartyTroopToPos(PartyTroopEntity entity, class_3222 player, class_2338 spawnPos) {
        class_1297 class_12972 = entity.method_5854();
        if (class_12972 instanceof PartyHorseEntity) {
            PartyHorseEntity horse = (PartyHorseEntity)class_12972;
            horse.method_48105(player.method_51469(), (double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, Set.of(), player.method_36454(), player.method_36455());
            if (!entity.method_5765()) {
                entity.method_5873((class_1297)horse, true);
            }
            horse.method_24201((class_1297)entity);
            horse.method_5942().method_6340();
            return;
        }
        entity.method_48105(player.method_51469(), (double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, Set.of(), player.method_36454(), player.method_36455());
        entity.method_5942().method_6340();
    }

    private static void syncDeployedTroops(MinecraftServer server, class_3222 owner, PlayerTroopProfile profile) {
        if (server == null || owner == null) {
            return;
        }
        for (PlayerTroopProfile.TroopUnitInstance unit : profile.units()) {
            PartyTroopEntity deployed = GotTroops.findDeployedTroop(server, owner.method_5667(), unit);
            if (deployed != null) {
                deployed.setTroopId(unit.troopId());
                deployed.setFormationType(profile.getFormationType());
                continue;
            }
            if (unit.deployedEntityUuid() == null) continue;
            profile.clearDeployedEntity(unit.unitId());
        }
    }

    private static PartyTroopEntity findDeployedTroopByUnitId(MinecraftServer server, UUID ownerUuid, int unitId) {
        if (server == null || ownerUuid == null || unitId < 0) {
            return null;
        }
        for (class_3218 world : server.method_3738()) {
            Iterator iterator = world.method_18198(class_5575.method_31795(PartyTroopEntity.class), entity -> entity.method_5805() && ownerUuid.equals(entity.getOwnerUuid()) && entity.getUnitId() == unitId).iterator();
            if (!iterator.hasNext()) continue;
            PartyTroopEntity troop = (PartyTroopEntity)iterator.next();
            return troop;
        }
        return null;
    }

    private static void reconcileDeployedTroopsOnJoin(class_3222 player) {
        if (player == null || player.method_5682() == null) {
            return;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        HashMap<Integer, PlayerTroopProfile.TroopUnitInstance> profileUnits = new HashMap<Integer, PlayerTroopProfile.TroopUnitInstance>();
        for (PlayerTroopProfile.TroopUnitInstance unit : profile.units()) {
            profileUnits.put(unit.unitId(), unit);
        }
        HashMap<Integer, PartyTroopEntity> liveTroops = new HashMap<Integer, PartyTroopEntity>();
        ArrayList<PartyTroopEntity> duplicates = new ArrayList<PartyTroopEntity>();
        for (class_3218 world : player.method_5682().method_3738()) {
            for (PartyTroopEntity troop : world.method_18198(class_5575.method_31795(PartyTroopEntity.class), entity -> entity.method_5805() && player.method_5667().equals(entity.getOwnerUuid()))) {
                if (!profileUnits.containsKey(troop.getUnitId())) {
                    GotTroops.discardPartyTroopAndMount(troop);
                    continue;
                }
                PartyTroopEntity existing = liveTroops.putIfAbsent(troop.getUnitId(), troop);
                if (existing == null) continue;
                duplicates.add(troop);
            }
        }
        for (PartyTroopEntity duplicate : duplicates) {
            GotTroops.discardPartyTroopAndMount(duplicate);
        }
        for (PlayerTroopProfile.TroopUnitInstance unit : profile.units()) {
            PartyTroopEntity live = (PartyTroopEntity)liveTroops.get(unit.unitId());
            if (live != null) {
                profile.bindDeployedEntity(unit.unitId(), live.method_5667());
                continue;
            }
            if (unit.deployedEntityUuid() == null) continue;
            profile.clearDeployedEntity(unit.unitId());
        }
        if (liveTroops.isEmpty()) {
            return;
        }
        List<PartyUnitAssignment> assignments = GotTroops.createAssignments(profile.units()).stream().filter(assignment -> liveTroops.containsKey(assignment.unit().unitId())).toList();
        List<class_2338> slots = GotTroops.calculateFormationSlots(player.method_51469(), player.method_19538(), player.method_36454(), assignments, profile.getFormationType(), true);
        for (int index = 0; index < assignments.size(); ++index) {
            PartyTroopEntity troop;
            troop = (PartyTroopEntity)liveTroops.get(assignments.get(index).unit().unitId());
            if (troop == null) continue;
            class_2338 slotPos = index < slots.size() ? slots.get(index) : GotTroops.findTroopSummonPos(player.method_51469(), player.method_24515(), troop.getUnitId());
            GotTroops.applyCommandToTroop(troop, CommanderActionType.RECALL, slotPos, profile.getFormationType());
            GotTroops.teleportPartyTroopToPos(troop, player, slotPos);
        }
    }

    private static void cleanupDeployedTroopsOnDisconnect(class_3222 player) {
        if (player == null || player.method_5682() == null) {
            return;
        }
        PlayerTroopProfile profile = ((PlayerTroopHolder)player).got$getTroopProfile();
        UUID ownerUuid = player.method_5667();
        for (class_3218 world : player.method_5682().method_3738()) {
            for (PartyTroopEntity troop : world.method_18198(class_5575.method_31795(PartyTroopEntity.class), entity -> entity.method_5805() && ownerUuid.equals(entity.getOwnerUuid()))) {
                GotTroops.discardPartyTroopAndMount(troop);
            }
            for (PartyHorseEntity horse : world.method_18198(class_5575.method_31795(PartyHorseEntity.class), entity -> entity.method_5805() && ownerUuid.equals(entity.method_6139()))) {
                horse.method_31472();
            }
        }
        for (PlayerTroopProfile.TroopUnitInstance unit : profile.units()) {
            if (unit.deployedEntityUuid() == null) continue;
            profile.clearDeployedEntity(unit.unitId());
        }
    }

    private static void discardPartyTroopAndMount(PartyTroopEntity troop) {
        if (troop == null) {
            return;
        }
        class_1297 class_12972 = troop.method_5854();
        if (class_12972 instanceof PartyHorseEntity) {
            PartyHorseEntity horse = (PartyHorseEntity)class_12972;
            horse.method_31472();
        }
        troop.method_31472();
    }

    private static class_2338 findSpawnPos(class_3218 world, class_2338 bannerPos, int seedOffset) {
        for (int attempt = 0; attempt < 12; ++attempt) {
            int z;
            int y;
            int offsetX = world.field_9229.method_39332(-8, 8) + seedOffset % 3 - 1;
            int offsetZ = world.field_9229.method_39332(-8, 8) + seedOffset / 3 % 3 - 1;
            int x = bannerPos.method_10263() + offsetX;
            class_2338 candidate = new class_2338(x, y = world.method_8624(class_2902.class_2903.field_13203, x, z = bannerPos.method_10260() + offsetZ), z);
            if (!world.method_8320(candidate).method_26215() || !world.method_8320(candidate.method_10084()).method_26215() || !world.method_8316(candidate).method_15769()) continue;
            return candidate;
        }
        return bannerPos.method_10084();
    }

    private static class_2338 resolveActiveHireablePointOfInterest(class_2338 defaultPos, class_2338 activePos) {
        if (activePos == null) {
            return defaultPos;
        }
        return activePos;
    }

    private static PartyHorseEntity attachCavalryMountIfNeeded(class_3218 world, class_3222 player, PartyTroopEntity troop, class_2338 spawnPos) {
        DynamicTroopDefinition definition = troop.getTroopDefinition();
        if (definition == null || definition.unitType() != DynamicTroopUnitType.CAVALRY) {
            return null;
        }
        PartyHorseEntity horse = (PartyHorseEntity)GotEntities.PARTY_HORSE.method_5883((class_1937)world);
        if (horse == null) {
            return null;
        }
        horse.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, player.method_36454(), 0.0f);
        horse.method_6732(player.method_5667());
        horse.setUnitId(troop.getUnitId());
        horse.method_6766(true);
        DynamicTroopLoadoutApplier.applyMountEquipment(horse, definition);
        if (!horse.method_6725()) {
            horse.method_6576(new class_1799((class_1935)class_1802.field_8175), class_3419.field_15254);
        }
        if (!world.method_8649((class_1297)horse)) {
            GotMod.LOGGER.warn("Failed to spawn party horse for unit {} owned by {} at {}", new Object[]{troop.getUnitId(), player.method_5477().getString(), spawnPos.method_23854()});
            return null;
        }
        troop.method_5808(horse.method_23317(), horse.method_23318(), horse.method_23321(), horse.method_36454(), horse.method_36455());
        troop.method_5873((class_1297)horse, true);
        horse.method_24201((class_1297)troop);
        return horse;
    }

    private static PartyHorseEntity attachHireableCavalryMountIfNeeded(class_3218 world, HireableUnitEntity troop, class_2338 spawnPos) {
        DynamicTroopDefinition definition = troop.getTroopDefinition();
        if (definition == null || definition.unitType() != DynamicTroopUnitType.CAVALRY) {
            return null;
        }
        PartyHorseEntity horse = (PartyHorseEntity)GotEntities.PARTY_HORSE.method_5883((class_1937)world);
        if (horse == null) {
            return null;
        }
        horse.method_5808((double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5, troop.method_36454(), 0.0f);
        horse.setUnitId(-1);
        horse.method_6766(true);
        DynamicTroopLoadoutApplier.applyMountEquipment(horse, definition);
        if (!horse.method_6725()) {
            horse.method_6576(new class_1799((class_1935)class_1802.field_8175), class_3419.field_15254);
        }
        world.method_8649((class_1297)horse);
        troop.method_5808(horse.method_23317(), horse.method_23318(), horse.method_23321(), horse.method_36454(), horse.method_36455());
        troop.method_5873((class_1297)horse, true);
        horse.method_24201((class_1297)troop);
        return horse;
    }

    private static void applyCommandToTroop(PartyTroopEntity entity, CommanderActionType commandType, class_2338 targetPos, PartyFormationType formationType) {
        entity.setCommandType(commandType);
        entity.setCommandOrigin(entity.method_24515());
        entity.setCommandTarget(targetPos);
        entity.setFormationType(formationType);
        entity.setTargetEntityUuid(null);
        entity.setTargetGatePos(null);
    }

    private static void applyCommandToMarriageCompanion(AbstractKingdomNpcEntity entity, CommanderActionType commandType, class_2338 targetPos, PartyFormationType formationType, float facingYaw) {
        entity.setPartyCommandType(commandType);
        entity.setPartyCommandOrigin(entity.method_24515());
        entity.setPartyCommandTarget(targetPos);
        entity.setPartyFormationType(formationType);
        entity.setPartyTargetEntityUuid(null);
        entity.setPartyTargetGatePos(null);
        entity.setPartyCommandFacingYaw(facingYaw);
    }

    private static List<PartyUnitAssignment> createAssignments(List<PlayerTroopProfile.TroopUnitInstance> units) {
        ArrayList<PartyUnitAssignment> assignments = new ArrayList<PartyUnitAssignment>();
        for (PlayerTroopProfile.TroopUnitInstance unit : units) {
            DynamicTroopDefinition definition = DynamicTroopRegistry.get(unit.troopId());
            if (definition == null) continue;
            assignments.add(new PartyUnitAssignment(unit, definition));
        }
        assignments.sort(Comparator.comparingInt(assignment -> GotTroops.formationGroupIndex(assignment.definition().unitType())).thenComparingInt(assignment -> assignment.unit().unitId()));
        return assignments;
    }

    private static List<PartyTroopEntity> getDeployedTroops(MinecraftServer server, UUID ownerUuid, PlayerTroopProfile profile) {
        ArrayList<PartyTroopEntity> troops = new ArrayList<PartyTroopEntity>();
        for (PlayerTroopProfile.TroopUnitInstance unit : profile.units()) {
            PartyTroopEntity deployed = GotTroops.findDeployedTroop(server, ownerUuid, unit);
            if (deployed == null) continue;
            troops.add(deployed);
        }
        troops.sort(Comparator.comparingInt(PartyTroopEntity::getUnitId));
        return troops;
    }

    public static List<AbstractKingdomNpcEntity> getMarriagePartyCompanions(MinecraftServer server, UUID ownerUuid) {
        if (server == null || ownerUuid == null) {
            return List.of();
        }
        class_3218 world = server.method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return List.of();
        }
        return world.method_8390(AbstractKingdomNpcEntity.class, PARTY_COMPANION_SCAN_BOX, npc -> npc.method_5805() && npc.isMarriagePartyCompanion() && ownerUuid.toString().equals(npc.getMarriedPlayerUuid())).stream().sorted(Comparator.comparingInt(class_1297::method_5628)).toList();
    }

    private static int findAssignmentIndex(List<PartyUnitAssignment> assignments, int unitId) {
        for (int index = 0; index < assignments.size(); ++index) {
            if (assignments.get(index).unit().unitId() != unitId) continue;
            return index;
        }
        return -1;
    }

    private static List<class_2338> calculateFormationSlots(class_3218 world, class_243 anchorCenter, float yaw, List<PartyUnitAssignment> assignments, PartyFormationType formationType, boolean behindAnchor) {
        return GotTroops.calculateFormationSlotsForUnitTypes((class_1937)world, anchorCenter, yaw, assignments.stream().map(assignment -> assignment.definition().unitType()).toList(), formationType, behindAnchor);
    }

    public static List<class_2338> calculateFormationSlotsForUnitTypes(class_1937 world, class_243 anchorCenter, float yaw, List<DynamicTroopUnitType> unitTypes, PartyFormationType formationType, boolean behindAnchor) {
        ArrayList<class_2338> results = new ArrayList<class_2338>();
        if (unitTypes.isEmpty()) {
            return results;
        }
        class_243 facing = class_243.method_1030((float)0.0f, (float)yaw);
        class_243 flatFacing = new class_243(facing.field_1352, 0.0, facing.field_1350).method_1029();
        if (flatFacing.method_1027() < 1.0E-4) {
            flatFacing = new class_243(0.0, 0.0, 1.0);
        }
        class_243 forward = behindAnchor ? flatFacing.method_1021(-1.0) : flatFacing;
        class_243 right = new class_243(-forward.field_1350, 0.0, forward.field_1352);
        class_243 baseCenter = behindAnchor ? anchorCenter.method_1019(forward.method_1021(3.0)) : anchorCenter;
        for (DynamicTroopUnitType unitType : List.of(DynamicTroopUnitType.MELEE, DynamicTroopUnitType.ARCHER, DynamicTroopUnitType.CAVALRY)) {
            List<DynamicTroopUnitType> group = unitTypes.stream().filter(value -> value == unitType).toList();
            if (group.isEmpty()) continue;
            int groupIndex = GotTroops.formationGroupIndex(unitType);
            class_243 groupCenter = baseCenter.method_1019(forward.method_1021((double)(groupIndex * 6)));
            for (int index = 0; index < group.size(); ++index) {
                class_243 desired = switch (formationType) {
                    default -> throw new MatchException(null, null);
                    case PartyFormationType.LINE -> GotTroops.lineFormationPos(groupCenter, right, index, group.size());
                    case PartyFormationType.BOX -> GotTroops.boxFormationPos(groupCenter, forward, right, index, group.size());
                    case PartyFormationType.LOOSE -> GotTroops.looseFormationPos(groupCenter, forward, right, index, group.size());
                };
                results.add(GotTroops.resolveFormationPos(world, desired));
            }
        }
        return results;
    }

    public static class_2338 calculateTargetFormationSlot(class_3218 world, UUID ownerUuid, int unitId, class_243 anchorCenter, float yaw, PartyFormationType formationType) {
        if (world == null || ownerUuid == null || unitId < 0) {
            return class_2338.method_49638((class_2374)anchorCenter);
        }
        ArrayList<PartyTroopEntity> deployedTroops = new ArrayList<PartyTroopEntity>(world.method_18198(class_5575.method_31795(PartyTroopEntity.class), entity -> entity.method_5805() && ownerUuid.equals(entity.getOwnerUuid())));
        if (deployedTroops.isEmpty()) {
            return class_2338.method_49638((class_2374)anchorCenter);
        }
        deployedTroops.sort(Comparator.comparingInt(troop -> troop.getTroopDefinition() == null ? Integer.MAX_VALUE : GotTroops.formationGroupIndex(troop.getTroopDefinition().unitType())).thenComparingInt(PartyTroopEntity::getUnitId));
        List<DynamicTroopUnitType> unitTypes = deployedTroops.stream().map(troop -> troop.getTroopDefinition() == null ? DynamicTroopUnitType.MELEE : troop.getTroopDefinition().unitType()).toList();
        List<class_2338> slots = GotTroops.calculateFormationSlotsForUnitTypes((class_1937)world, anchorCenter, yaw, unitTypes, formationType, true);
        for (int index = 0; index < deployedTroops.size(); ++index) {
            if (((PartyTroopEntity)deployedTroops.get(index)).getUnitId() != unitId) continue;
            return index < slots.size() ? slots.get(index) : class_2338.method_49638((class_2374)anchorCenter);
        }
        return class_2338.method_49638((class_2374)anchorCenter);
    }

    public static class_2338 calculateMarriageCompanionFormationSlot(class_3218 world, UUID ownerUuid, AbstractKingdomNpcEntity companion, class_243 anchorCenter, float yaw, PartyFormationType formationType) {
        if (world == null || ownerUuid == null || companion == null) {
            return class_2338.method_49638((class_2374)anchorCenter);
        }
        return MarriageCompanionFormationCache.getSlot(world, ownerUuid, companion, anchorCenter, yaw, formationType);
    }

    public static void onMarriageCompanionMembershipChanged(AbstractKingdomNpcEntity companion, String previousOwnerUuid, boolean previousPartyCompanion, String currentOwnerUuid, boolean currentPartyCompanion) {
        MarriageCompanionFormationCache.onMembershipChanged(companion, previousOwnerUuid, previousPartyCompanion, currentOwnerUuid, currentPartyCompanion);
    }

    private static class_243 lineFormationPos(class_243 center, class_243 right, int index, int total) {
        double offset = (double)index - (double)(total - 1) / 2.0;
        return center.method_1019(right.method_1021(offset));
    }

    private static class_243 boxFormationPos(class_243 center, class_243 forward, class_243 right, int index, int total) {
        int columns = Math.max(1, class_3532.method_15384((double)Math.sqrt(total)));
        int row = index / columns;
        int column = index % columns;
        double xOffset = (double)column - (double)(columns - 1) / 2.0;
        return center.method_1019(right.method_1021(xOffset)).method_1019(forward.method_1021((double)(-row)));
    }

    private static class_243 looseFormationPos(class_243 center, class_243 forward, class_243 right, int index, int total) {
        if (total <= 1) {
            return center;
        }
        double angle = Math.PI * 2 * (double)index / (double)total;
        double radius = 1.5 + (double)(index % 3);
        return center.method_1019(right.method_1021(Math.cos(angle) * radius)).method_1019(forward.method_1021(Math.sin(angle) * radius));
    }

    private static class_2338 resolveFormationPos(class_1937 world, class_243 desired) {
        int baseX = class_3532.method_15357((double)desired.field_1352);
        int baseZ = class_3532.method_15357((double)desired.field_1350);
        int baseY = class_3532.method_15357((double)desired.field_1351);
        class_2338 bestPos = null;
        double bestDistance = Double.MAX_VALUE;
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dz = -1; dz <= 1; ++dz) {
                double distance;
                int x = baseX + dx;
                int z = baseZ + dz;
                int y = GotTroops.findNearbyGroundY(world, baseY, x, z);
                class_2338 pos = new class_2338(x, y, z);
                if (!GotTroops.isSafeSummonPos(world, pos) || !((distance = pos.method_40081(desired.field_1352, desired.field_1351, desired.field_1350)) < bestDistance)) continue;
                bestDistance = distance;
                bestPos = pos;
            }
        }
        return bestPos != null ? bestPos : new class_2338(baseX, baseY, baseZ);
    }

    @Nullable
    private static class_2338 resolveSafeTroopSummonPos(class_3218 world, class_2338 requestedPos, class_2338 fallbackOrigin, int seed) {
        if (GotTroops.isSafeSummonPos((class_1937)world, requestedPos)) {
            return requestedPos;
        }
        return GotTroops.findSafeTroopSummonPos(world, fallbackOrigin, seed);
    }

    public static int formationGroupIndex(DynamicTroopUnitType unitType) {
        return switch (unitType) {
            default -> throw new MatchException(null, null);
            case DynamicTroopUnitType.MELEE -> 0;
            case DynamicTroopUnitType.ARCHER -> 1;
            case DynamicTroopUnitType.CAVALRY -> 2;
        };
    }

    private static class_2338 findRecallAnchor(class_3218 world, class_3222 player) {
        class_243 facing = class_243.method_1030((float)0.0f, (float)player.method_36454());
        class_243 behind = player.method_19538().method_1020(facing.method_1029().method_1021(4.0));
        return GotTroops.resolveFormationPos((class_1937)world, behind);
    }

    private static class_2338 findFormationAnchor(List<PartyTroopEntity> deployedTroops) {
        if (deployedTroops.isEmpty()) {
            return null;
        }
        double x = 0.0;
        double y = 0.0;
        double z = 0.0;
        for (PartyTroopEntity troop : deployedTroops) {
            x += troop.method_23317();
            y += troop.method_23318();
            z += troop.method_23321();
        }
        int count = deployedTroops.size();
        return class_2338.method_49637((double)(x / (double)count), (double)(y / (double)count), (double)(z / (double)count));
    }

    private static int findNearbyGroundY(class_1937 world, int originY, int x, int z) {
        int maxY;
        int minY = Math.max(world.method_31607() + 1, originY - 2);
        for (int y = maxY = Math.min(world.method_8624(class_2902.class_2903.field_13203, x, z), originY + 2); y >= minY; --y) {
            class_2338 pos = new class_2338(x, y, z);
            if (!GotTroops.isSafeSummonPos(world, pos)) continue;
            return y;
        }
        return originY;
    }

    private static boolean isSafeSummonPos(class_1937 world, class_2338 feetPos) {
        class_2338 belowPos = feetPos.method_10074();
        class_2338 headPos = feetPos.method_10084();
        class_2680 belowState = world.method_8320(belowPos);
        class_2680 feetState = world.method_8320(feetPos);
        class_2680 headState = world.method_8320(headPos);
        if (!belowState.method_51366() || !world.method_8316(belowPos).method_15769()) {
            return false;
        }
        if (!feetState.method_26215() || !headState.method_26215()) {
            return false;
        }
        return !belowState.method_27852(class_2246.field_10092) && !belowState.method_27852(class_2246.field_17350) && !belowState.method_27852(class_2246.field_23860);
    }

    private static boolean playerHasItemCount(class_3222 player, class_1792 item, int amount) {
        return InventoryItemHelper.hasItemCount(player, item, amount);
    }

    private static void removeItems(class_3222 player, class_1792 item, int amount) {
        InventoryItemHelper.removeItems(player, item, amount);
    }

    private record PartyUnitAssignment(PlayerTroopProfile.TroopUnitInstance unit, DynamicTroopDefinition definition) {
    }

    private record PlayerConflict(UUID hostileOwnerUuid, UUID directTargetUuid, long expiresAtTick) {
    }
}

