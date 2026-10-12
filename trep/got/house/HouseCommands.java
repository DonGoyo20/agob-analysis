/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
 *  net.minecraft.class_124
 *  net.minecraft.class_2168
 *  net.minecraft.class_2170
 *  net.minecraft.class_2170$class_5364
 *  net.minecraft.class_2172
 *  net.minecraft.class_2186
 *  net.minecraft.class_2558
 *  net.minecraft.class_2558$class_2559
 *  net.minecraft.class_2561
 *  net.minecraft.class_2568
 *  net.minecraft.class_2568$class_5247
 *  net.minecraft.class_2583
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_5250
 *  net.minecraft.class_7157
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.house;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.class_124;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2172;
import net.minecraft.class_2186;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2583;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5250;
import net.minecraft.class_7157;
import net.minecraft.server.MinecraftServer;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.house.PlayerHouseDefinition;
import trep.got.house.PlayerHouseService;
import trep.got.house.PlayerHouseWorldState;

public final class HouseCommands {
    private static final long INVITE_DURATION_MS = 60000L;
    private static final Map<UUID, List<PendingInvite>> PENDING_INVITES = new ConcurrentHashMap<UUID, List<PendingInvite>>();

    private HouseCommands() {
    }

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register(HouseCommands::registerCommands);
    }

    private static void registerCommands(CommandDispatcher<class_2168> dispatcher, class_7157 registries, class_2170.class_5364 environment) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"house").then(class_2170.method_9247((String)"invite").then(class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).executes(context -> HouseCommands.invite((CommandContext<class_2168>)context, class_2186.method_9315((CommandContext)context, (String)"player")))))).then(class_2170.method_9247((String)"kick").then(class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).executes(context -> HouseCommands.kick((CommandContext<class_2168>)context, class_2186.method_9315((CommandContext)context, (String)"player")))))).then(class_2170.method_9247((String)"leave").executes(HouseCommands::leave))).then(class_2170.method_9247((String)"accept").then(class_2170.method_9244((String)"house_name", (ArgumentType)StringArgumentType.greedyString()).suggests(HouseCommands::suggestPendingInvites).executes(context -> HouseCommands.accept((CommandContext<class_2168>)context, StringArgumentType.getString((CommandContext)context, (String)"house_name")))))).then(class_2170.method_9247((String)"deny").then(class_2170.method_9244((String)"house_name", (ArgumentType)StringArgumentType.greedyString()).suggests(HouseCommands::suggestPendingInvites).executes(context -> HouseCommands.deny((CommandContext<class_2168>)context, StringArgumentType.getString((CommandContext)context, (String)"house_name"))))));
    }

    private static int invite(CommandContext<class_2168> context, class_3222 target) {
        class_3222 inviter = HouseCommands.requirePlayer(context);
        if (inviter == null) {
            return 0;
        }
        if (inviter.method_5667().equals(target.method_5667())) {
            inviter.method_7353((class_2561)class_2561.method_43470((String)"You cannot invite yourself to your own house.").method_27692(class_124.field_1061), true);
            return 0;
        }
        class_3218 world = PlayerHouseService.resolveHouseStateWorld(inviter.method_5682(), inviter.method_51469());
        PlayerHouseDefinition house = PlayerHouseService.getForOwner(world, inviter.method_5667());
        if (house == null) {
            inviter.method_7353((class_2561)class_2561.method_43470((String)"You must found your own house before inviting players.").method_27692(class_124.field_1061), true);
            return 0;
        }
        PlayerHouseDefinition targetHouse = PlayerHouseService.getForParticipant(world, target.method_5667());
        if (targetHouse != null && targetHouse.houseId().equals(house.houseId())) {
            inviter.method_7353((class_2561)class_2561.method_43470((String)(target.method_5477().getString() + " is already in your house.")).method_27692(class_124.field_1054), true);
            return 0;
        }
        PendingInvite invite = new PendingInvite(inviter.method_5667(), inviter.method_5477().getString(), house.houseId(), house.displayName(), System.currentTimeMillis() + 60000L);
        PENDING_INVITES.computeIfAbsent(target.method_5667(), ignored -> new ArrayList()).removeIf(existing -> existing.houseId().equals(house.houseId()));
        PENDING_INVITES.computeIfAbsent(target.method_5667(), ignored -> new ArrayList()).add(invite);
        target.method_7353((class_2561)class_2561.method_43470((String)("You have been invited by " + inviter.method_5477().getString() + " to join the house of " + house.displayName())).method_27692(class_124.field_1065), false);
        target.method_7353((class_2561)HouseCommands.inviteActionText(house.displayName()), false);
        inviter.method_7353((class_2561)class_2561.method_43470((String)("Invited " + target.method_5477().getString() + " to House " + house.displayName() + ".")).method_27692(class_124.field_1060), true);
        return 1;
    }

    private static int accept(CommandContext<class_2168> context, String houseName) {
        class_3222 player = HouseCommands.requirePlayer(context);
        if (player == null) {
            return 0;
        }
        PendingInvite invite = HouseCommands.findInvite(player, houseName);
        if (invite == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"No active invite found for that house.").method_27692(class_124.field_1061), true);
            return 0;
        }
        class_3218 world = PlayerHouseService.resolveHouseStateWorld(player.method_5682(), player.method_51469());
        PlayerHouseWorldState state = PlayerHouseWorldState.get(world);
        PlayerHouseDefinition house = state.getByHouseId(invite.houseId());
        if (house == null) {
            HouseCommands.removeInvite(player.method_5667(), invite.houseId());
            player.method_7353((class_2561)class_2561.method_43470((String)"That house no longer exists.").method_27692(class_124.field_1061), true);
            return 0;
        }
        HouseCommands.leaveCurrentCustomHouse(world, player, house.houseId());
        HouseCommands.addMember(world, house, player.method_5845());
        PlayerHouseService.applyHouseSelection(player, house.kingdomId(), house.houseId());
        HouseCommands.removeInvite(player.method_5667(), invite.houseId());
        PlayerHouseService.broadcast(player.method_5682());
        player.method_7353((class_2561)class_2561.method_43470((String)("You joined House " + house.displayName() + ".")).method_27692(class_124.field_1060), true);
        class_3222 inviter = player.method_5682().method_3760().method_14602(invite.inviterUuid());
        if (inviter != null) {
            inviter.method_7353((class_2561)class_2561.method_43470((String)(player.method_5477().getString() + " joined House " + house.displayName() + ".")).method_27692(class_124.field_1060), true);
        }
        return 1;
    }

    private static int deny(CommandContext<class_2168> context, String houseName) {
        class_3222 player = HouseCommands.requirePlayer(context);
        if (player == null) {
            return 0;
        }
        PendingInvite invite = HouseCommands.findInvite(player, houseName);
        if (invite == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"No active invite found for that house.").method_27692(class_124.field_1061), true);
            return 0;
        }
        HouseCommands.removeInvite(player.method_5667(), invite.houseId());
        player.method_7353((class_2561)class_2561.method_43470((String)("Denied the invite to House " + invite.houseName() + ".")).method_27692(class_124.field_1054), true);
        class_3222 inviter = player.method_5682().method_3760().method_14602(invite.inviterUuid());
        if (inviter != null) {
            inviter.method_7353((class_2561)class_2561.method_43470((String)(player.method_5477().getString() + " denied your house invite.")).method_27692(class_124.field_1054), true);
        }
        return 1;
    }

    private static int leave(CommandContext<class_2168> context) {
        class_3222 player = HouseCommands.requirePlayer(context);
        if (player == null) {
            return 0;
        }
        class_3218 world = PlayerHouseService.resolveHouseStateWorld(player.method_5682(), player.method_51469());
        PlayerHouseDefinition currentHouse = PlayerHouseService.getForParticipant(world, player.method_5667());
        if (currentHouse == null) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You are not part of a custom house.").method_27692(class_124.field_1061), true);
            return 0;
        }
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        String originalKingdomId = character.getOriginalSelectedKingdomId();
        String originalHouseId = character.getOriginalSelectedOptionId();
        if (originalKingdomId.isBlank() || originalHouseId.isBlank()) {
            player.method_7353((class_2561)class_2561.method_43470((String)"Your original house could not be resolved.").method_27692(class_124.field_1061), true);
            return 0;
        }
        HouseCommands.leaveCurrentCustomHouse(world, player, "");
        PlayerHouseService.applyHouseSelection(player, originalKingdomId, originalHouseId);
        PlayerHouseService.broadcast(player.method_5682());
        player.method_7353((class_2561)class_2561.method_43470((String)("You left House " + currentHouse.displayName() + ".")).method_27692(class_124.field_1054), true);
        return 1;
    }

    private static int kick(CommandContext<class_2168> context, class_3222 target) {
        class_3222 leader = HouseCommands.requirePlayer(context);
        if (leader == null) {
            return 0;
        }
        class_3218 world = PlayerHouseService.resolveHouseStateWorld(leader.method_5682(), leader.method_51469());
        PlayerHouseDefinition house = PlayerHouseService.getForOwner(world, leader.method_5667());
        if (house == null) {
            leader.method_7353((class_2561)class_2561.method_43470((String)"You must lead your own house to kick members.").method_27692(class_124.field_1061), true);
            return 0;
        }
        if (leader.method_5667().equals(target.method_5667())) {
            leader.method_7353((class_2561)class_2561.method_43470((String)"Use another house invite to leave your own house.").method_27692(class_124.field_1061), true);
            return 0;
        }
        if (!house.memberUuids().contains(target.method_5845())) {
            leader.method_7353((class_2561)class_2561.method_43470((String)(target.method_5477().getString() + " is not a member of your house.")).method_27692(class_124.field_1061), true);
            return 0;
        }
        HouseCommands.removeMember(world, house, target.method_5845());
        PlayerHouseService.applyHouseSelection(target, house.kingdomId(), house.lordHouseId());
        PlayerHouseService.broadcast(leader.method_5682());
        leader.method_7353((class_2561)class_2561.method_43470((String)("Kicked " + target.method_5477().getString() + " from House " + house.displayName() + ".")).method_27692(class_124.field_1054), true);
        target.method_7353((class_2561)class_2561.method_43470((String)("You were removed from House " + house.displayName() + ".")).method_27692(class_124.field_1061), true);
        return 1;
    }

    private static class_3222 requirePlayer(CommandContext<class_2168> context) {
        try {
            return ((class_2168)context.getSource()).method_9207();
        }
        catch (Exception exception) {
            ((class_2168)context.getSource()).method_9213((class_2561)class_2561.method_43470((String)"Only players can use this command."));
            return null;
        }
    }

    private static class_5250 inviteActionText(String houseName) {
        return class_2561.method_43470((String)"").method_10852((class_2561)class_2561.method_43470((String)"[ACCEPT]").method_10862(class_2583.field_24360.method_10977(class_124.field_1060).method_10958(new class_2558(class_2558.class_2559.field_11750, "/house accept " + houseName)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)class_2561.method_43470((String)"Accept house invite"))))).method_10852((class_2561)class_2561.method_43470((String)" ")).method_10852((class_2561)class_2561.method_43470((String)"[DENY]").method_10862(class_2583.field_24360.method_10977(class_124.field_1061).method_10958(new class_2558(class_2558.class_2559.field_11750, "/house deny " + houseName)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)class_2561.method_43470((String)"Deny house invite")))));
    }

    private static PendingInvite findInvite(class_3222 player, String houseName) {
        HouseCommands.pruneExpiredInvites(player.method_5667());
        List invites = PENDING_INVITES.getOrDefault(player.method_5667(), List.of());
        String normalized = HouseCommands.normalizeInviteName(houseName);
        for (PendingInvite invite : invites) {
            if (!HouseCommands.normalizeInviteName(invite.houseName()).equals(normalized) && !invite.houseId().equals(houseName)) continue;
            return invite;
        }
        return null;
    }

    private static void pruneExpiredInvites(UUID playerUuid) {
        List<PendingInvite> invites = PENDING_INVITES.get(playerUuid);
        if (invites == null) {
            return;
        }
        long now = System.currentTimeMillis();
        invites.removeIf(invite -> invite.expiresAtMs() <= now);
        if (invites.isEmpty()) {
            PENDING_INVITES.remove(playerUuid);
        }
    }

    private static void removeInvite(UUID playerUuid, String houseId) {
        List<PendingInvite> invites = PENDING_INVITES.get(playerUuid);
        if (invites == null) {
            return;
        }
        invites.removeIf(invite -> invite.houseId().equals(houseId));
        if (invites.isEmpty()) {
            PENDING_INVITES.remove(playerUuid);
        }
    }

    private static CompletableFuture<Suggestions> suggestPendingInvites(CommandContext<class_2168> context, SuggestionsBuilder builder) {
        class_3222 player = HouseCommands.requirePlayer(context);
        if (player == null) {
            return Suggestions.empty();
        }
        HouseCommands.pruneExpiredInvites(player.method_5667());
        return class_2172.method_9264(PENDING_INVITES.getOrDefault(player.method_5667(), List.of()).stream().map(PendingInvite::houseName), (SuggestionsBuilder)builder);
    }

    private static void leaveCurrentCustomHouse(class_3218 world, class_3222 player, String destinationHouseId) {
        PlayerHouseWorldState state = PlayerHouseWorldState.get(world);
        PlayerHouseDefinition current = state.getByParticipantUuid(player.method_5845());
        if (current == null || current.houseId().equals(destinationHouseId)) {
            return;
        }
        if (!current.ownerUuid().equals(player.method_5845())) {
            HouseCommands.removeMember(world, current, player.method_5845());
            return;
        }
        LinkedHashSet<String> remainingMembers = new LinkedHashSet<String>(current.memberUuids());
        if (remainingMembers.isEmpty()) {
            state.remove(current.houseId());
            return;
        }
        String newOwner = remainingMembers.stream().min(Comparator.comparing(uuid -> HouseCommands.playerNameFor(world.method_8503(), uuid), String.CASE_INSENSITIVE_ORDER)).orElse("");
        remainingMembers.remove(newOwner);
        state.put(new PlayerHouseDefinition(newOwner, current.houseId(), current.kingdomId(), current.lordHouseId(), current.displayName(), current.sigilColors(), remainingMembers));
        class_3222 newLeader = HouseCommands.resolveOnlinePlayer(world.method_8503(), newOwner);
        if (newLeader != null) {
            newLeader.method_7353((class_2561)class_2561.method_43470((String)("You are now the leader of House " + current.displayName() + ".")).method_27692(class_124.field_1065), true);
        }
    }

    private static void addMember(class_3218 world, PlayerHouseDefinition house, String playerUuid) {
        if (house.ownerUuid().equals(playerUuid)) {
            return;
        }
        LinkedHashSet<String> members = new LinkedHashSet<String>(house.memberUuids());
        members.add(playerUuid);
        PlayerHouseWorldState.get(world).put(new PlayerHouseDefinition(house.ownerUuid(), house.houseId(), house.kingdomId(), house.lordHouseId(), house.displayName(), house.sigilColors(), members));
    }

    private static void removeMember(class_3218 world, PlayerHouseDefinition house, String playerUuid) {
        LinkedHashSet<String> members = new LinkedHashSet<String>(house.memberUuids());
        members.remove(playerUuid);
        PlayerHouseWorldState.get(world).put(new PlayerHouseDefinition(house.ownerUuid(), house.houseId(), house.kingdomId(), house.lordHouseId(), house.displayName(), house.sigilColors(), members));
    }

    private static class_3222 resolveOnlinePlayer(MinecraftServer server, String uuid) {
        try {
            return server.method_3760().method_14602(UUID.fromString(uuid));
        }
        catch (IllegalArgumentException exception) {
            return null;
        }
    }

    private static String playerNameFor(MinecraftServer server, String uuid) {
        class_3222 player = HouseCommands.resolveOnlinePlayer(server, uuid);
        return player == null ? uuid : player.method_5477().getString();
    }

    private static String normalizeInviteName(String houseName) {
        return houseName == null ? "" : houseName.trim().toLowerCase(Locale.ROOT).replaceAll(" +", " ");
    }

    private record PendingInvite(UUID inviterUuid, String inviterName, String houseId, String houseName, long expiresAtMs) {
    }
}

