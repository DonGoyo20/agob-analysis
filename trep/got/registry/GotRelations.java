/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.fabricmc.fabric.api.networking.v1.PacketSender
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_1297
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_8710
 */
package trep.got.registry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1297;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import trep.got.castle.CastleAreaOwnershipResolver;
import trep.got.castle.CastleInstance;
import trep.got.character.PlayerCharacterHolder;
import trep.got.day.DailyTaskContext;
import trep.got.day.DailyTaskRegistry;
import trep.got.entity.AbstractKingdomBlacksmithNpcEntity;
import trep.got.entity.AbstractKingdomFishermanNpcEntity;
import trep.got.entity.AbstractKingdomGuardNpcEntity;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.RobberNpcEntity;
import trep.got.entity.WildlingRaiderNpcEntity;
import trep.got.network.KingdomRelationsPayload;
import trep.got.registry.GotCastles;
import trep.got.registry.GotPlayerKingdomActions;
import trep.got.relations.KingdomRelationsWorldState;
import trep.got.relations.KingdomWarService;
import trep.got.relations.PlayerKingdomRelationsHolder;
import trep.got.relations.PlayerKingdomRelationsProfile;
import trep.got.world.dimension.GotDimensionKeys;

public final class GotRelations {
    private GotRelations() {
    }

    public static void initialize() {
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            PlayerKingdomRelationsProfile source = ((PlayerKingdomRelationsHolder)oldPlayer).got$getKingdomRelationsProfile();
            ((PlayerKingdomRelationsHolder)newPlayer).got$getKingdomRelationsProfile().copyFrom(source);
        });
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> GotRelations.syncToPlayer(handler.field_14140, sender));
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(GotRelations::onKilledEntity);
        DailyTaskRegistry.register(class_2960.method_60655((String)"agob", (String)"kingdom_wars"), GotRelations::runDailyWarTask);
    }

    private static void runDailyWarTask(class_3218 world, DailyTaskContext context) {
        if (GotDimensionKeys.ICEANDFIRE.equals((Object)world.method_27983())) {
            KingdomWarService.tick(world);
        }
    }

    public static void initializePlayerRelationsFromCurrentKingdom(class_3222 player, String kingdomId) {
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null || kingdomId == null || kingdomId.isBlank()) {
            return;
        }
        PlayerKingdomRelationsProfile profile = ((PlayerKingdomRelationsHolder)player).got$getKingdomRelationsProfile();
        profile.initializeFromKingdom(kingdomId, KingdomRelationsWorldState.get(world));
        GotRelations.syncToPlayer(player);
    }

    public static void syncToPlayer(class_3222 player) {
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return;
        }
        ServerPlayNetworking.send((class_3222)player, (class_8710)GotRelations.buildPayload(player, world));
    }

    private static void syncToPlayer(class_3222 player, PacketSender sender) {
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return;
        }
        sender.sendPacket((class_8710)GotRelations.buildPayload(player, world));
    }

    public static void syncToPlayers(class_3218 world) {
        if (world == null) {
            return;
        }
        for (class_3222 player : world.method_8503().method_3760().method_14571()) {
            if (!player.method_51469().method_27983().equals(GotDimensionKeys.ICEANDFIRE)) continue;
            ServerPlayNetworking.send((class_3222)player, (class_8710)GotRelations.buildPayload(player, world));
        }
    }

    private static void onKilledEntity(class_3218 world, class_1297 attacker, class_1297 killedEntity) {
        if (!(attacker instanceof class_3222)) {
            return;
        }
        class_3222 player = (class_3222)attacker;
        CastleInstance areaCastle = CastleAreaOwnershipResolver.findCastleAt(world, killedEntity.method_24515());
        if (areaCastle == null) {
            return;
        }
        String kingdomId = areaCastle.getOwnerKingdomId();
        if (kingdomId == null || kingdomId.isBlank()) {
            return;
        }
        int delta = GotRelations.relationDeltaForKill(killedEntity);
        if (delta == 0) {
            return;
        }
        PlayerKingdomRelationsProfile profile = ((PlayerKingdomRelationsHolder)player).got$getKingdomRelationsProfile();
        int updatedRelation = profile.addRelation(kingdomId, delta);
        if (killedEntity instanceof AbstractKingdomNpcEntity) {
            AbstractKingdomNpcEntity killedNpc = (AbstractKingdomNpcEntity)killedEntity;
            GotPlayerKingdomActions.recordNpcKill(player, kingdomId, killedNpc, updatedRelation);
        } else {
            GotPlayerKingdomActions.recordRelation(player, kingdomId, updatedRelation);
        }
        GotRelations.syncToPlayer(player);
    }

    private static int relationDeltaForKill(class_1297 killedEntity) {
        if (killedEntity instanceof RobberNpcEntity || killedEntity instanceof WildlingRaiderNpcEntity) {
            return 1;
        }
        if (!(killedEntity instanceof AbstractKingdomNpcEntity)) {
            return 0;
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)killedEntity;
        if (npc instanceof AbstractKingdomLordNpcEntity) {
            return -10;
        }
        if (npc instanceof AbstractKingdomGuardNpcEntity) {
            return -5;
        }
        if (npc instanceof AbstractKingdomBlacksmithNpcEntity || npc instanceof AbstractKingdomFishermanNpcEntity) {
            return -3;
        }
        return -2;
    }

    private static KingdomRelationsPayload buildPayload(class_3222 player, class_3218 world) {
        KingdomRelationsWorldState state = KingdomRelationsWorldState.get(world);
        state.clearExpiredDiplomacyCooldowns(world.method_8510());
        PlayerKingdomRelationsProfile playerProfile = ((PlayerKingdomRelationsHolder)player).got$getKingdomRelationsProfile();
        ArrayList<KingdomRelationsPayload.KingdomEntry> kingdomEntries = new ArrayList<KingdomRelationsPayload.KingdomEntry>();
        for (Map.Entry<String, Map<String, Integer>> entry : state.asMap().entrySet()) {
            for (Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()) {
                kingdomEntries.add(new KingdomRelationsPayload.KingdomEntry(entry.getKey(), entry2.getKey(), entry2.getValue()));
            }
        }
        ArrayList<KingdomRelationsPayload.PlayerEntry> playerEntries = new ArrayList<KingdomRelationsPayload.PlayerEntry>();
        for (Map.Entry<String, Integer> entry : playerProfile.asMap().entrySet()) {
            playerEntries.add(new KingdomRelationsPayload.PlayerEntry(entry.getKey(), entry.getValue()));
        }
        String string = ((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedKingdomId();
        if (!string.isBlank() && playerProfile.isEmpty()) {
            for (String string2 : state.getKingdomIds()) {
                int relation = string2.equals(string) ? 100 : state.getRelation(string, string2);
                playerEntries.add(new KingdomRelationsPayload.PlayerEntry(string2, relation));
            }
        }
        ArrayList<KingdomRelationsPayload.WarEntry> arrayList = new ArrayList<KingdomRelationsPayload.WarEntry>();
        for (KingdomRelationsWorldState.ActiveWar war : state.getActiveWars()) {
            arrayList.add(new KingdomRelationsPayload.WarEntry(war.attackerKingdomId(), war.defenderKingdomId(), (int)Math.max(0L, war.startedDay()), (int)Math.max(0L, war.endsAtDay())));
        }
        String string3 = GotCastles.playerRuledKingdomId(world, player);
        LinkedHashMap<String, Long> diplomacyCooldowns = new LinkedHashMap<String, Long>();
        if (!string3.isBlank()) {
            for (String kingdomId : state.getKingdomIds()) {
                long cooldownEndsAtTick;
                if (kingdomId.equals(string3) || (cooldownEndsAtTick = state.getDiplomacyCooldownEndsAtTick(string3, kingdomId)) <= world.method_8510()) continue;
                diplomacyCooldowns.put(kingdomId, cooldownEndsAtTick);
            }
        }
        return new KingdomRelationsPayload(List.copyOf(kingdomEntries), List.copyOf(playerEntries), List.copyOf(arrayList), string3, !string3.isBlank() && string3.equals(GotCastles.canonicalKingdomId(string)), Map.copyOf(diplomacyCooldowns));
    }
}

