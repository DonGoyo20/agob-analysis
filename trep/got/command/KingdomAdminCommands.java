/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
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
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_8710
 */
package trep.got.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2172;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import trep.got.command.HouseAdminCommands;
import trep.got.house.PlayerHouseService;
import trep.got.kingdom.PlayerKingdomDefinition;
import trep.got.kingdom.PlayerKingdomWorldState;
import trep.got.network.OpenCreateKingdomScreenPayload;
import trep.got.registry.GotCastles;

public final class KingdomAdminCommands {
    private KingdomAdminCommands() {
    }

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registries, environment) -> KingdomAdminCommands.register((CommandDispatcher<class_2168>)dispatcher));
    }

    private static void register(CommandDispatcher<class_2168> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"kingdom").requires(source -> source.method_9259(2))).then(class_2170.method_9247((String)"create").executes(context -> KingdomAdminCommands.openCreateScreen((class_2168)context.getSource())))).then(class_2170.method_9247((String)"disband").then(class_2170.method_9244((String)"kingdom_id", (ArgumentType)StringArgumentType.word()).suggests((context, builder) -> {
            class_3218 world = PlayerHouseService.resolveHouseStateWorld(((class_2168)context.getSource()).method_9211(), ((class_2168)context.getSource()).method_9225());
            return class_2172.method_9264(PlayerKingdomWorldState.get(world).getAll().stream().filter(definition -> definition.castleId().isBlank()).map(PlayerKingdomDefinition::kingdomId), (SuggestionsBuilder)builder);
        }).executes(context -> KingdomAdminCommands.disband((class_2168)context.getSource(), StringArgumentType.getString((CommandContext)context, (String)"kingdom_id"))))));
    }

    private static int openCreateScreen(class_2168 source) {
        class_3222 player;
        try {
            player = source.method_9207();
        }
        catch (Exception exception) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Only players can open the kingdom creation screen."));
            return 0;
        }
        ServerPlayNetworking.send((class_3222)player, (class_8710)new OpenCreateKingdomScreenPayload());
        return 1;
    }

    private static int disband(class_2168 source, String kingdomId) {
        class_3218 world = PlayerHouseService.resolveHouseStateWorld(source.method_9211(), source.method_9225());
        PlayerKingdomWorldState state = PlayerKingdomWorldState.get(world);
        PlayerKingdomDefinition definition = state.getByKingdomId(kingdomId);
        if (definition == null) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Unknown player-created kingdom."));
            return 0;
        }
        if (!definition.castleId().isBlank()) {
            source.method_9213((class_2561)class_2561.method_43470((String)"Only kingdoms created through /kingdom create can be disbanded with this command."));
            return 0;
        }
        int resetPlayers = HouseAdminCommands.resetPlayersFromKingdom(source.method_9211(), world, definition.kingdomId());
        int unclaimedCastles = GotCastles.unclaimKingdomHoldings(world, definition.kingdomId());
        state.remove(definition.kingdomId());
        HouseAdminCommands.removeCustomHousesInKingdom(world, definition.kingdomId());
        PlayerHouseService.broadcast(source.method_9211());
        source.method_9226(() -> class_2561.method_43470((String)("Disbanded " + definition.displayName() + ". Reset " + resetPlayers + " player(s), unclaimed " + unclaimedCastles + " castle(s).")).method_27692(class_124.field_1065), true);
        return 1;
    }
}

