/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
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
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_3222
 *  net.minecraft.class_7157
 */
package trep.got.blacksmith;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.class_124;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2172;
import net.minecraft.class_2186;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_7157;
import trep.got.blacksmith.BlacksmithKingdomUnlocks;
import trep.got.kingdom.Kingdom;
import trep.got.registry.GotKingdoms;
import trep.got.skill.PlayerSkillHolder;

public final class BlacksmithCommands {
    private BlacksmithCommands() {
    }

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register(BlacksmithCommands::registerCommands);
    }

    private static void registerCommands(CommandDispatcher<class_2168> dispatcher, class_7157 registries, class_2170.class_5364 environment) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"blacksmith").requires(source -> source.method_9259(2))).then(((RequiredArgumentBuilder)class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).then(class_2170.method_9247((String)"all").executes(context -> BlacksmithCommands.executeUnlockAll((CommandContext<class_2168>)context, class_2186.method_9315((CommandContext)context, (String)"player"))))).then(class_2170.method_9244((String)"kingdom", (ArgumentType)StringArgumentType.word()).suggests(BlacksmithCommands::suggestKingdoms).executes(context -> BlacksmithCommands.executeUnlockKingdom((CommandContext<class_2168>)context, class_2186.method_9315((CommandContext)context, (String)"player"), StringArgumentType.getString((CommandContext)context, (String)"kingdom"))))));
    }

    private static CompletableFuture<Suggestions> suggestKingdoms(CommandContext<class_2168> context, SuggestionsBuilder builder) {
        return class_2172.method_9264(BlacksmithKingdomUnlocks.unlockableKingdomTabs().stream().map(class_2960::method_12832), (SuggestionsBuilder)builder);
    }

    private static int executeUnlockAll(CommandContext<class_2168> context, class_3222 player) {
        if (!(player instanceof PlayerSkillHolder)) {
            ((class_2168)context.getSource()).method_9213((class_2561)class_2561.method_43470((String)"That player cannot receive blacksmith unlocks."));
            return 0;
        }
        PlayerSkillHolder skillHolder = (PlayerSkillHolder)player;
        int granted = 0;
        for (class_2960 kingdomId : BlacksmithKingdomUnlocks.unlockableKingdomTabs()) {
            if (BlacksmithKingdomUnlocks.isUnlocked(player, kingdomId) || !skillHolder.got$getSkillProfile().unlockBlacksmithKingdom(kingdomId)) continue;
            ++granted;
        }
        int finalGranted = granted;
        ((class_2168)context.getSource()).method_9226(() -> class_2561.method_43470((String)("Unlocked " + finalGranted + " blacksmith kingdoms for " + player.method_5477().getString() + ".")).method_27692(class_124.field_1065), true);
        return finalGranted > 0 ? finalGranted : 1;
    }

    private static int executeUnlockKingdom(CommandContext<class_2168> context, class_3222 player, String kingdomName) {
        class_2960 kingdomId = BlacksmithCommands.resolveKingdomId(kingdomName);
        if (kingdomId == null) {
            ((class_2168)context.getSource()).method_9213((class_2561)class_2561.method_43470((String)("Unknown blacksmith kingdom '" + kingdomName + "'.")));
            return 0;
        }
        if (!(player instanceof PlayerSkillHolder)) {
            ((class_2168)context.getSource()).method_9213((class_2561)class_2561.method_43470((String)"That player cannot receive blacksmith unlocks."));
            return 0;
        }
        PlayerSkillHolder skillHolder = (PlayerSkillHolder)player;
        if (BlacksmithKingdomUnlocks.isUnlocked(player, kingdomId)) {
            ((class_2168)context.getSource()).method_9226(() -> class_2561.method_43470((String)(player.method_5477().getString() + " already has " + BlacksmithCommands.displayKingdomName(kingdomId) + " unlocked.")).method_27692(class_124.field_1054), true);
            return 1;
        }
        if (!skillHolder.got$getSkillProfile().unlockBlacksmithKingdom(kingdomId)) {
            ((class_2168)context.getSource()).method_9213((class_2561)class_2561.method_43470((String)"Could not unlock that blacksmith kingdom."));
            return 0;
        }
        ((class_2168)context.getSource()).method_9226(() -> class_2561.method_43470((String)("Unlocked " + BlacksmithCommands.displayKingdomName(kingdomId) + " blacksmith recipes for " + player.method_5477().getString() + ".")).method_27692(class_124.field_1065), true);
        return 1;
    }

    private static class_2960 resolveKingdomId(String kingdomName) {
        String normalized;
        String string = normalized = kingdomName == null ? "" : kingdomName.trim().toLowerCase(Locale.ROOT);
        if (normalized.isBlank()) {
            return null;
        }
        for (class_2960 kingdomId : BlacksmithKingdomUnlocks.unlockableKingdomTabs()) {
            if (!kingdomId.method_12832().equals(normalized) && !kingdomId.toString().equals(normalized)) continue;
            return kingdomId;
        }
        class_2960 parsed = class_2960.method_12829((String)(normalized.contains(":") ? normalized : "agob:" + normalized));
        if (parsed != null && BlacksmithKingdomUnlocks.unlockableKingdomTabs().contains(parsed)) {
            return parsed;
        }
        return null;
    }

    private static String displayKingdomName(class_2960 kingdomId) {
        if (kingdomId == null) {
            return "";
        }
        if (BlacksmithKingdomUnlocks.isMaterialTab(kingdomId)) {
            return switch (kingdomId.method_12832()) {
                case "copper" -> "Copper";
                case "iron" -> "Iron";
                case "steel" -> "Steel";
                default -> kingdomId.method_12832();
            };
        }
        Kingdom kingdom = GotKingdoms.get(kingdomId);
        return kingdom != null ? kingdom.getName() : kingdomId.toString();
    }
}

