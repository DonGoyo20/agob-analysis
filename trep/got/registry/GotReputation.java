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
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.minecraft.class_2168
 *  net.minecraft.class_2170
 *  net.minecraft.class_2170$class_5364
 *  net.minecraft.class_2172
 *  net.minecraft.class_2186
 *  net.minecraft.class_2561
 *  net.minecraft.class_3222
 *  net.minecraft.class_7157
 */
package trep.got.registry;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2172;
import net.minecraft.class_2186;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_7157;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationExperienceService;
import trep.got.kingdom.ReputationProfile;
import trep.got.kingdom.ReputationTitle;
import trep.got.registry.GotReputationTitles;

public final class GotReputation {
    private GotReputation() {
    }

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register(GotReputation::registerCommands);
        ServerPlayerEvents.COPY_FROM.register(GotReputation::copyProfile);
        ReputationExperienceService.register();
        GotReputationTitles.initialize();
    }

    private static void copyProfile(class_3222 oldPlayer, class_3222 newPlayer, boolean alive) {
        ReputationProfile source = ((PlayerReputationHolder)oldPlayer).got$getReputationProfile();
        ((PlayerReputationHolder)newPlayer).got$getReputationProfile().copyFrom(source);
    }

    private static void registerCommands(CommandDispatcher<class_2168> dispatcher, class_7157 registries, class_2170.class_5364 environment) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"reputation").requires(source -> source.method_9259(2))).then(class_2170.method_9247((String)"set").then(class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).then(class_2170.method_9244((String)"title", (ArgumentType)StringArgumentType.word()).suggests(GotReputation::suggestTitles).executes(context -> GotReputation.executeSetTitle((CommandContext<class_2168>)context, class_2186.method_9315((CommandContext)context, (String)"player"), StringArgumentType.getString((CommandContext)context, (String)"title"))))))).then(((LiteralArgumentBuilder)class_2170.method_9247((String)"exp").then(class_2170.method_9247((String)"add").then(class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).then(class_2170.method_9244((String)"amount", (ArgumentType)IntegerArgumentType.integer((int)1)).executes(context -> GotReputation.executeAddExp((CommandContext<class_2168>)context, class_2186.method_9315((CommandContext)context, (String)"player"), IntegerArgumentType.getInteger((CommandContext)context, (String)"amount"))))))).then(class_2170.method_9247((String)"remove").then(class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).then(class_2170.method_9244((String)"amount", (ArgumentType)IntegerArgumentType.integer((int)1)).executes(context -> GotReputation.executeRemoveExp((CommandContext<class_2168>)context, class_2186.method_9315((CommandContext)context, (String)"player"), IntegerArgumentType.getInteger((CommandContext)context, (String)"amount"))))))));
    }

    private static CompletableFuture<Suggestions> suggestTitles(CommandContext<class_2168> context, SuggestionsBuilder builder) {
        return class_2172.method_9264(ReputationTitle.ALL.stream().map(ReputationTitle::getId), (SuggestionsBuilder)builder);
    }

    private static int executeSetTitle(CommandContext<class_2168> context, class_3222 player, String titleId) {
        ReputationTitle title = ReputationTitle.byId(titleId);
        ReputationExperienceService.setTitle(player, title);
        ((class_2168)context.getSource()).method_9226(() -> class_2561.method_43470((String)("Set reputation title of " + player.method_5477().getString() + " to " + title.getDisplayName())), true);
        return 1;
    }

    private static int executeAddExp(CommandContext<class_2168> context, class_3222 player, int amount) {
        ReputationExperienceService.addExperience(player, amount);
        ((class_2168)context.getSource()).method_9226(() -> class_2561.method_43470((String)("Added " + amount + " reputation exp to " + player.method_5477().getString())), true);
        return 1;
    }

    private static int executeRemoveExp(CommandContext<class_2168> context, class_3222 player, int amount) {
        ReputationExperienceService.removeExperience(player, amount);
        ((class_2168)context.getSource()).method_9226(() -> class_2561.method_43470((String)("Removed " + amount + " reputation exp from " + player.method_5477().getString())), true);
        return 1;
    }
}

