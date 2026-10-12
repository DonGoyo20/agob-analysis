/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
 *  net.minecraft.class_124
 *  net.minecraft.class_2168
 *  net.minecraft.class_2170
 *  net.minecraft.class_2186
 *  net.minecraft.class_2561
 *  net.minecraft.class_3222
 */
package trep.got.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.List;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.class_124;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2186;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import trep.got.quest.PlayerQuestHolder;
import trep.got.quest.PlayerQuestProfile;
import trep.got.quest.QuestInstance;
import trep.got.registry.GotQuests;

public final class QuestAdminCommands {
    private QuestAdminCommands() {
    }

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registries, environment) -> QuestAdminCommands.register((CommandDispatcher<class_2168>)dispatcher));
    }

    private static void register(CommandDispatcher<class_2168> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"quest").requires(source -> source.method_9259(2))).then(class_2170.method_9247((String)"remove").then(class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).then(class_2170.method_9244((String)"index", (ArgumentType)IntegerArgumentType.integer((int)1, (int)3)).executes(context -> QuestAdminCommands.remove((class_2168)context.getSource(), class_2186.method_9315((CommandContext)context, (String)"player"), IntegerArgumentType.getInteger((CommandContext)context, (String)"index")))))));
    }

    private static int remove(class_2168 source, class_3222 player, int index) {
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        List<QuestInstance> quests = profile.getActiveQuests();
        if (index > quests.size()) {
            source.method_9213((class_2561)class_2561.method_43470((String)(player.method_5477().getString() + " only has " + quests.size() + " active side quest(s).")));
            return 0;
        }
        QuestInstance removed = quests.get(index - 1);
        profile.removeQuest(removed.id());
        GotQuests.sendQuestState(player);
        source.method_9226(() -> class_2561.method_43470((String)("Removed side quest " + index + " (" + removed.templateId() + ") from " + player.method_5477().getString() + ".")).method_27692(class_124.field_1065), true);
        return 1;
    }
}

