/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_2168
 *  net.minecraft.class_2170
 *  net.minecraft.class_2172
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2172;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import trep.got.castle.HouseLordWorldState;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.house.PlayerHouseDefinition;
import trep.got.house.PlayerHouseService;
import trep.got.house.PlayerHouseWorldState;
import trep.got.kingdom.PlayerKingdomWorldState;
import trep.got.network.OpenCreateHouseScreenPayload;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacterOptions;

public final class HouseAdminCommands {
    private static final String NIGHTS_WATCH_KINGDOM = "agob:the_nights_watch";
    private static final String WILDLING_KINGDOM = "agob:wildling";

    private HouseAdminCommands() {
    }

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registries, environment) -> HouseAdminCommands.register((CommandDispatcher<class_2168>)dispatcher));
    }

    private static void register(CommandDispatcher<class_2168> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"house").then(((LiteralArgumentBuilder)class_2170.method_9247((String)"create").requires(source -> source.method_9259(2))).executes(context -> HouseAdminCommands.openCreateScreen((class_2168)context.getSource())))).then(((LiteralArgumentBuilder)class_2170.method_9247((String)"disband").requires(source -> source.method_9259(2))).then(class_2170.method_9244((String)"house_id", (ArgumentType)StringArgumentType.word()).suggests((context, builder) -> class_2172.method_9265(HouseAdminCommands.houseSuggestions(HouseAdminCommands.resolveWorld((class_2168)context.getSource())), (SuggestionsBuilder)builder)).executes(context -> HouseAdminCommands.disband((class_2168)context.getSource(), StringArgumentType.getString((CommandContext)context, (String)"house_id")))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"npc").requires(source -> source.method_9259(2))).then(class_2170.method_9247((String)"add").then(class_2170.method_9244((String)"house_id", (ArgumentType)StringArgumentType.word()).suggests((context, builder) -> class_2172.method_9265(HouseAdminCommands.houseSuggestions(HouseAdminCommands.resolveWorld((class_2168)context.getSource())), (SuggestionsBuilder)builder)).then(class_2170.method_9244((String)"amount", (ArgumentType)IntegerArgumentType.integer((int)1, (int)100)).executes(context -> HouseAdminCommands.addNpc((CommandContext<class_2168>)context)))))).then(class_2170.method_9247((String)"remove").then(class_2170.method_9244((String)"house_id", (ArgumentType)StringArgumentType.word()).suggests((context, builder) -> class_2172.method_9265(HouseAdminCommands.houseSuggestions(HouseAdminCommands.resolveWorld((class_2168)context.getSource())), (SuggestionsBuilder)builder)).then(class_2170.method_9244((String)"npc_id", (ArgumentType)StringArgumentType.word()).suggests((context, builder) -> class_2172.method_9265(HouseAdminCommands.npcSuggestions((CommandContext<class_2168>)context), (SuggestionsBuilder)builder)).executes(context -> HouseAdminCommands.removeNpc((CommandContext<class_2168>)context)))))));
    }

    private static int openCreateScreen(class_2168 source) {
        class_3222 player;
        try {
            player = source.method_9207();
        }
        catch (Exception exception) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Only players can open the house creation screen."));
            return 0;
        }
        ServerPlayNetworking.send((class_3222)player, (class_8710)new OpenCreateHouseScreenPayload());
        return 1;
    }

    private static int disband(class_2168 source, String houseId) {
        class_3218 world = HouseAdminCommands.resolveWorld(source);
        HouseRef house = HouseAdminCommands.resolveHouse(world, houseId);
        if (house == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Unknown house id."));
            return 0;
        }
        int resetPlayers = HouseAdminCommands.resetPlayersFromHouse(source.method_9211(), world, house);
        int removedNpcs = GotCastles.clearHouseLordProfiles(world, house.kingdomId(), house.houseId());
        PlayerHouseWorldState.get(world).remove(house.houseId());
        GotCastles.HouseDisbandResult holdings = GotCastles.disbandHouseHoldings(world, house.kingdomId(), house.houseId());
        boolean removedKingdom = false;
        if (holdings.noReplacementHouse() && (removedKingdom = PlayerKingdomWorldState.get(world).remove(house.kingdomId()))) {
            resetPlayers += HouseAdminCommands.resetPlayersFromKingdom(source.method_9211(), world, house.kingdomId());
        }
        PlayerHouseService.broadcast(source.method_9211());
        int finalResetPlayers = resetPlayers;
        boolean finalRemovedKingdom = removedKingdom;
        source.method_9226(() -> class_2561.method_43470((String)("Disbanded House " + house.displayName() + ". Removed " + removedNpcs + " NPC(s), reset " + finalResetPlayers + " player(s), reassigned " + holdings.reassignedCastles() + " castle(s), unclaimed " + holdings.unclaimedCastles() + " castle(s)" + (finalRemovedKingdom ? ", and disbanded its kingdom." : "."))).method_27692(class_124.field_1065), true);
        return 1;
    }

    private static int addNpc(CommandContext<class_2168> context) {
        class_3218 world = HouseAdminCommands.resolveWorld((class_2168)context.getSource());
        HouseRef house = HouseAdminCommands.resolveHouse(world, StringArgumentType.getString(context, (String)"house_id"));
        if (house == null) {
            ((class_2168)context.getSource()).method_9213((class_2561)class_2561.method_43470((String)"Unknown house id."));
            return 0;
        }
        int amount = IntegerArgumentType.getInteger(context, (String)"amount");
        List<HouseLordWorldState.HouseLordProfile> added = GotCastles.addHouseLordProfiles(world, house.kingdomId(), house.houseId(), amount);
        ((class_2168)context.getSource()).method_9226(() -> class_2561.method_43470((String)("Added " + added.size() + " NPC(s) to House " + house.displayName() + ".")).method_27692(class_124.field_1065), true);
        return added.isEmpty() ? 0 : 1;
    }

    private static int removeNpc(CommandContext<class_2168> context) {
        class_3218 world = HouseAdminCommands.resolveWorld((class_2168)context.getSource());
        HouseRef house = HouseAdminCommands.resolveHouse(world, StringArgumentType.getString(context, (String)"house_id"));
        if (house == null) {
            ((class_2168)context.getSource()).method_9213((class_2561)class_2561.method_43470((String)"Unknown house id."));
            return 0;
        }
        String npcToken = StringArgumentType.getString(context, (String)"npc_id");
        HouseLordWorldState.HouseLordProfile removed = GotCastles.removeHouseLordProfileByToken(world, house.kingdomId(), house.houseId(), npcToken);
        if (removed == null) {
            ((class_2168)context.getSource()).method_9213((class_2561)class_2561.method_43470((String)"Unknown NPC for that house."));
            return 0;
        }
        ((class_2168)context.getSource()).method_9226(() -> class_2561.method_43470((String)("Removed " + removed.displayName() + " from House " + house.displayName() + ".")).method_27692(class_124.field_1065), true);
        return 1;
    }

    static int resetPlayersFromKingdom(MinecraftServer server, class_3218 world, String kingdomId) {
        int reset = 0;
        for (class_3222 player : server.method_3760().method_14571()) {
            PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
            if (!kingdomId.equals(profile.getSelectedKingdomId())) continue;
            Selection fallback = HouseAdminCommands.fallbackSelection(profile, kingdomId, "");
            PlayerHouseService.applyHouseSelection(player, fallback.kingdomId(), fallback.houseId());
            ++reset;
        }
        return reset;
    }

    static void removeCustomHousesInKingdom(class_3218 world, String kingdomId) {
        PlayerHouseWorldState state = PlayerHouseWorldState.get(world);
        for (PlayerHouseDefinition house : List.copyOf(state.getAll())) {
            if (!kingdomId.equals(house.kingdomId())) continue;
            state.remove(house.houseId());
        }
    }

    private static int resetPlayersFromHouse(MinecraftServer server, class_3218 world, HouseRef house) {
        int reset = 0;
        for (class_3222 player : server.method_3760().method_14571()) {
            boolean affected;
            PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
            PlayerHouseDefinition customHouse = PlayerHouseService.getForParticipant(world, player.method_5667());
            boolean bl = affected = house.houseId().equals(profile.getSelectedOptionId()) || customHouse != null && house.houseId().equals(customHouse.houseId());
            if (!affected) continue;
            Selection target = HouseAdminCommands.fallbackSelection(profile, house.kingdomId(), house.houseId());
            PlayerHouseService.applyHouseSelection(player, target.kingdomId(), target.houseId());
            ++reset;
        }
        return reset;
    }

    private static Selection fallbackSelection(PlayerCharacterProfile profile, String deletedKingdomId, String deletedHouseId) {
        boolean originDeleted;
        String originKingdomId = profile.getOriginalSelectedKingdomId();
        String originHouseId = profile.getOriginalSelectedOptionId();
        boolean bl = originDeleted = originKingdomId.isBlank() || originHouseId.isBlank() || originKingdomId.equals(deletedKingdomId) || !deletedHouseId.isBlank() && originHouseId.equals(deletedHouseId);
        if (!originDeleted) {
            return new Selection(originKingdomId, originHouseId);
        }
        String fallbackKingdom = NIGHTS_WATCH_KINGDOM.equals(originKingdomId) ? WILDLING_KINGDOM : NIGHTS_WATCH_KINGDOM;
        String fallbackHouse = GotCharacterOptions.getPrimaryHouseId(class_2960.method_60654((String)fallbackKingdom));
        profile.setOriginalSelectedKingdomId(fallbackKingdom);
        profile.setOriginalSelectedOptionId(fallbackHouse);
        return new Selection(fallbackKingdom, fallbackHouse);
    }

    private static List<String> houseSuggestions(class_3218 world) {
        ArrayList<String> suggestions = new ArrayList<String>();
        for (CharacterSelectionOption option : GotCharacterOptions.getAll()) {
            suggestions.add(option.id());
        }
        for (PlayerHouseDefinition house : PlayerHouseWorldState.get(world).getAll()) {
            suggestions.add(house.houseId());
        }
        suggestions.sort(String.CASE_INSENSITIVE_ORDER);
        return suggestions;
    }

    private static List<String> npcSuggestions(CommandContext<class_2168> context) {
        class_3218 world = HouseAdminCommands.resolveWorld((class_2168)context.getSource());
        HouseRef house = HouseAdminCommands.resolveHouse(world, StringArgumentType.getString(context, (String)"house_id"));
        if (house == null) {
            return List.of();
        }
        return GotCastles.getHouseLordProfiles(world, house.kingdomId(), house.houseId()).stream().sorted(Comparator.comparing(HouseLordWorldState.HouseLordProfile::displayName, String.CASE_INSENSITIVE_ORDER)).map(lord -> HouseAdminCommands.normalizeToken(lord.displayName())).toList();
    }

    private static HouseRef resolveHouse(class_3218 world, String houseId) {
        PlayerHouseDefinition customHouse = PlayerHouseWorldState.get(world).getByHouseId(houseId);
        if (customHouse != null) {
            return new HouseRef(customHouse.kingdomId(), customHouse.houseId(), customHouse.displayName());
        }
        CharacterSelectionOption option = GotCharacterOptions.get(houseId);
        if (option != null) {
            return new HouseRef(option.kingdomId().toString(), option.id(), option.houseDisplayName());
        }
        return null;
    }

    private static class_3218 resolveWorld(class_2168 source) {
        return PlayerHouseService.resolveHouseStateWorld(source.method_9211(), source.method_9225());
    }

    private static String normalizeToken(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", "_").replaceAll("^_+|_+$", "");
    }

    private record HouseRef(String kingdomId, String houseId, String displayName) {
    }

    private record Selection(String kingdomId, String houseId) {
    }
}

