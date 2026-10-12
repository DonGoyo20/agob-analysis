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
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2382
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_7923
 *  net.minecraft.class_8710
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.registry;

import java.util.List;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2382;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_7923;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import trep.got.advancement.ModAdvancementService;
import trep.got.advancement.ModAdvancementType;
import trep.got.castle.CityStructureClaim;
import trep.got.dialogue.NpcChatAction;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.RobberNpcEntity;
import trep.got.mainquest.MainQuestContext;
import trep.got.mainquest.MainQuestProgress;
import trep.got.mainquest.MainQuestReward;
import trep.got.mainquest.MainQuestStep;
import trep.got.mainquest.MainQuestStepType;
import trep.got.mainquest.MainQuestTemplate;
import trep.got.mainquest.MainQuestTemplateRegistry;
import trep.got.mainquest.PlayerMainQuestHolder;
import trep.got.mainquest.PlayerMainQuestProfile;
import trep.got.network.MainQuestStatePayload;
import trep.got.troop.CommanderActionType;
import trep.got.village.VillageInstance;

public final class GotMainQuests {
    public static final class_2960 STARTER_QUEST_ID = class_2960.method_60655((String)"agob", (String)"starter_quest");
    public static final class_2960 SOCIALIZE_QUEST_ID = class_2960.method_60655((String)"agob", (String)"socialize_quest");
    public static final class_2960 SMITHING_QUEST_ID = class_2960.method_60655((String)"agob", (String)"smithing_quest");
    public static final class_2960 GREET_LORD_QUEST_ID = class_2960.method_60655((String)"agob", (String)"greet_lord_quest");
    public static final class_2960 TROOP_HIRE_QUEST_ID = class_2960.method_60655((String)"agob", (String)"troop_hire");
    public static final class_2960 VILLAGE_VISIT_QUEST_ID = class_2960.method_60655((String)"agob", (String)"village_visit");
    public static final class_2960 VOYAGER_QUEST_ID = class_2960.method_60655((String)"agob", (String)"voyager");
    public static final class_2960 PROTECTOR_QUEST_ID = class_2960.method_60655((String)"agob", (String)"protector");
    public static final class_2960 SETTLER_QUEST_ID = class_2960.method_60655((String)"agob", (String)"settler");
    private static final long PLAYER_CHECK_INTERVAL_TICKS = 20L;
    private static final String TALKED_NPCS_KEY = "talked_npc_ids";
    private static final String HIRED_TROOPS_KEY = "hired_troops";
    private static final String COMMANDER_ACTIONS_KEY = "commander_actions";
    private static final String KILLED_ROBBERS_KEY = "killed_robbers";
    private static final String VOYAGE_TYPES_KEY = "voyage_types";
    private static final String LAND_VOYAGE_VALUE = "land";
    private static final String SEA_VOYAGE_VALUE = "sea";

    private GotMainQuests() {
    }

    public static void initialize() {
        MainQuestTemplateRegistry.initialize();
        ServerPlayerEvents.COPY_FROM.register(GotMainQuests::copyProfile);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            GotMainQuests.ensureAutoStartQuests(handler.field_14140);
            GotMainQuests.sendMainQuestState(handler.field_14140, sender);
        });
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(GotMainQuests::onKilledEntity);
        ServerTickEvents.END_SERVER_TICK.register(GotMainQuests::tickServer);
    }

    public static boolean giveQuest(class_3222 player, class_2960 questId) {
        if (player == null || questId == null) {
            return false;
        }
        MainQuestTemplate template = MainQuestTemplateRegistry.get(questId);
        if (template == null) {
            return false;
        }
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        if (profile.hasCompleted(questId) || profile.hasActive(questId)) {
            return false;
        }
        boolean added = profile.addActive(new MainQuestProgress(questId, player.method_37908().method_8510()));
        if (added) {
            player.method_7353((class_2561)class_2561.method_43470((String)("Main quest started: " + template.displayName())).method_27692(class_124.field_1065), true);
            ModAdvancementService.send(player, ModAdvancementType.QUEST_ACCEPTED, "Main Quest Started", template.displayName(), class_1802.field_8529);
            GotMainQuests.sendMainQuestState(player);
        }
        return added;
    }

    public static boolean takeQuest(class_3222 player, class_2960 questId) {
        if (player == null || questId == null) {
            return false;
        }
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        boolean removed = profile.removeActive(questId);
        if (removed) {
            GotMainQuests.sendMainQuestState(player);
        }
        return removed;
    }

    public static boolean hasCompletedPledgePrerequisites(class_3222 player) {
        if (player == null) {
            return false;
        }
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        return profile.hasCompleted(SOCIALIZE_QUEST_ID) && profile.hasCompleted(SMITHING_QUEST_ID) && profile.hasCompleted(GREET_LORD_QUEST_ID);
    }

    public static void onNpcDialogueStarted(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null) {
            return;
        }
        if (GotMainQuests.advanceTalkToNpcs(player, npc)) {
            GotMainQuests.sendMainQuestState(player);
        }
    }

    public static void onNpcChatAction(class_3222 player, class_1297 entity, NpcChatAction action) {
        if (player == null || action != NpcChatAction.TALK || !(entity instanceof AbstractKingdomLordNpcEntity)) {
            return;
        }
        if (GotMainQuests.advanceMatchingEventStep(player, MainQuestStepType.CHAT_WITH_LORD)) {
            GotMainQuests.sendMainQuestState(player);
        }
    }

    public static void onBlacksmithScreenOpened(class_3222 player) {
        boolean changed = GotMainQuests.tickPlayer(player);
        if (GotMainQuests.advanceMatchingEventStep(player, MainQuestStepType.OPEN_BLACKSMITH_SCREEN)) {
            changed = true;
        }
        if (changed) {
            GotMainQuests.sendMainQuestState(player);
        }
    }

    public static void onBlacksmithItemCrafted(class_3222 player) {
        boolean changed = GotMainQuests.tickPlayer(player);
        if (GotMainQuests.advanceMatchingEventStep(player, MainQuestStepType.OPEN_BLACKSMITH_SCREEN)) {
            changed = true;
        }
        if (GotMainQuests.advanceMatchingEventStep(player, MainQuestStepType.CRAFT_BLACKSMITH_ITEM)) {
            changed = true;
        }
        if (changed) {
            GotMainQuests.sendMainQuestState(player);
        }
    }

    public static void onTroopHired(class_3222 player) {
        if (GotMainQuests.advanceCounterStep(player, MainQuestStepType.HIRE_TROOPS, HIRED_TROOPS_KEY)) {
            GotMainQuests.sendMainQuestState(player);
        }
    }

    public static void onCommanderAction(class_3222 player, CommanderActionType actionType) {
        if (player == null || actionType == null) {
            return;
        }
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        boolean changed = false;
        for (MainQuestProgress progress : profile.activeQuests()) {
            MainQuestTemplate template = MainQuestTemplateRegistry.get(progress.questId());
            MainQuestStep step = template == null ? null : template.stepAt(progress.stepIndex());
            if (step == null || step.type() != MainQuestStepType.EXECUTE_COMMANDER_ACTIONS) continue;
            if (progress.addToSet(COMMANDER_ACTIONS_KEY, actionType.id())) {
                changed = true;
            }
            if (progress.setSize(COMMANDER_ACTIONS_KEY) < Math.max(1, step.requiredCount())) continue;
            GotMainQuests.advanceCompletedStep(player, profile, progress, template);
            changed = true;
        }
        if (changed) {
            GotMainQuests.sendMainQuestState(player);
        }
    }

    public static void onVillageDiscovered(class_3222 player, VillageInstance village) {
        if (player == null || village == null) {
            return;
        }
        if (GotMainQuests.advanceMatchingEventStep(player, MainQuestStepType.DISCOVER_VILLAGE_CLAIM)) {
            GotMainQuests.sendMainQuestState(player);
        }
    }

    public static void onLandVoyageTaken(class_3222 player) {
        if (GotMainQuests.advanceVoyageStep(player, LAND_VOYAGE_VALUE)) {
            GotMainQuests.sendMainQuestState(player);
        }
    }

    public static void onSeaVoyageTaken(class_3222 player) {
        if (GotMainQuests.advanceVoyageStep(player, SEA_VOYAGE_VALUE)) {
            GotMainQuests.sendMainQuestState(player);
        }
    }

    public static void onPlayerSettlementCreated(class_3222 player) {
        boolean changed = GotMainQuests.tickPlayer(player);
        if (GotMainQuests.advanceSettlementCreation(player)) {
            changed = true;
        }
        if (changed) {
            GotMainQuests.sendMainQuestState(player);
        }
    }

    public static void sendMainQuestState(class_3222 player) {
        ServerPlayNetworking.send((class_3222)player, (class_8710)GotMainQuests.createPayload(player));
    }

    private static void sendMainQuestState(class_3222 player, PacketSender sender) {
        sender.sendPacket((class_8710)GotMainQuests.createPayload(player));
    }

    private static void copyProfile(class_3222 oldPlayer, class_3222 newPlayer, boolean alive) {
        ((PlayerMainQuestHolder)newPlayer).got$getMainQuestProfile().copyFrom(((PlayerMainQuestHolder)oldPlayer).got$getMainQuestProfile());
    }

    private static void tickServer(MinecraftServer server) {
        if (server == null || (long)server.method_3780() % 20L != 0L) {
            return;
        }
        for (class_3222 player : server.method_3760().method_14571()) {
            GotMainQuests.ensureAutoStartQuests(player);
            if (!GotMainQuests.tickPlayer(player)) continue;
            GotMainQuests.sendMainQuestState(player);
        }
    }

    private static void ensureAutoStartQuests(class_3222 player) {
        for (MainQuestTemplate template : MainQuestTemplateRegistry.autoStartTemplates()) {
            GotMainQuests.giveQuest(player, template.id());
        }
        GotMainQuests.ensureFollowUpQuest(player, STARTER_QUEST_ID, SOCIALIZE_QUEST_ID);
        GotMainQuests.ensureFollowUpQuest(player, SOCIALIZE_QUEST_ID, SMITHING_QUEST_ID);
        GotMainQuests.ensureFollowUpQuest(player, SMITHING_QUEST_ID, GREET_LORD_QUEST_ID);
        GotMainQuests.ensureFollowUpQuest(player, GREET_LORD_QUEST_ID, TROOP_HIRE_QUEST_ID);
        GotMainQuests.ensureFollowUpQuest(player, TROOP_HIRE_QUEST_ID, VILLAGE_VISIT_QUEST_ID);
        GotMainQuests.ensureFollowUpQuest(player, VILLAGE_VISIT_QUEST_ID, VOYAGER_QUEST_ID);
        GotMainQuests.ensureFollowUpQuest(player, VOYAGER_QUEST_ID, PROTECTOR_QUEST_ID);
        GotMainQuests.ensureFollowUpQuest(player, PROTECTOR_QUEST_ID, SETTLER_QUEST_ID);
    }

    private static void ensureFollowUpQuest(class_3222 player, class_2960 completedQuestId, class_2960 nextQuestId) {
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        if (profile.hasCompleted(completedQuestId) && !profile.hasCompleted(nextQuestId) && !profile.hasActive(nextQuestId)) {
            GotMainQuests.giveQuest(player, nextQuestId);
        }
    }

    private static boolean tickPlayer(class_3222 player) {
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        List<MainQuestProgress> activeQuests = profile.activeQuests();
        if (activeQuests.isEmpty()) {
            return false;
        }
        MainQuestContext context = MainQuestContext.of(player);
        boolean changed = false;
        block0: for (MainQuestProgress progress : activeQuests) {
            MainQuestTemplate template = MainQuestTemplateRegistry.get(progress.questId());
            if (template == null) {
                profile.removeActive(progress.questId());
                changed = true;
                continue;
            }
            if (progress.stepIndex() >= template.steps().size()) {
                GotMainQuests.completeQuest(player, profile, template);
                changed = true;
                continue;
            }
            while (GotMainQuests.isCurrentStepComplete(template, progress, context)) {
                GotMainQuests.advanceCompletedStep(player, profile, progress, template);
                changed = true;
                if (progress.stepIndex() < template.steps().size()) continue;
                continue block0;
            }
        }
        return changed;
    }

    private static boolean advanceTalkToNpcs(class_3222 player, AbstractKingdomNpcEntity npc) {
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        boolean changed = false;
        for (MainQuestProgress progress : profile.activeQuests()) {
            MainQuestTemplate template = MainQuestTemplateRegistry.get(progress.questId());
            MainQuestStep step = template == null ? null : template.stepAt(progress.stepIndex());
            if (step == null || step.type() != MainQuestStepType.TALK_TO_NPCS) continue;
            if (progress.addToSet(TALKED_NPCS_KEY, npc.method_5845())) {
                changed = true;
            }
            if (progress.setSize(TALKED_NPCS_KEY) < Math.max(1, step.requiredCount())) continue;
            GotMainQuests.advanceCompletedStep(player, profile, progress, template);
            changed = true;
        }
        return changed;
    }

    private static boolean advanceMatchingEventStep(class_3222 player, MainQuestStepType type) {
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        boolean changed = false;
        for (MainQuestProgress progress : profile.activeQuests()) {
            MainQuestTemplate template = MainQuestTemplateRegistry.get(progress.questId());
            MainQuestStep step = template == null ? null : template.stepAt(progress.stepIndex());
            if (step == null || step.type() != type) continue;
            GotMainQuests.advanceCompletedStep(player, profile, progress, template);
            changed = true;
        }
        return changed;
    }

    private static boolean advanceCounterStep(class_3222 player, MainQuestStepType type, String key) {
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        boolean changed = false;
        for (MainQuestProgress progress : profile.activeQuests()) {
            MainQuestTemplate template = MainQuestTemplateRegistry.get(progress.questId());
            MainQuestStep step = template == null ? null : template.stepAt(progress.stepIndex());
            if (step == null || step.type() != type) continue;
            int count = progress.getCounter(key) + 1;
            progress.setCounter(key, count);
            changed = true;
            if (count < Math.max(1, step.requiredCount())) continue;
            GotMainQuests.advanceCompletedStep(player, profile, progress, template);
        }
        return changed;
    }

    private static boolean advanceVoyageStep(class_3222 player, String voyageType) {
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        MainQuestContext context = MainQuestContext.of(player);
        boolean changed = false;
        block0: for (MainQuestProgress progress : profile.activeQuests()) {
            MainQuestTemplate template = MainQuestTemplateRegistry.get(progress.questId());
            if (template == null || template.steps().stream().noneMatch(GotMainQuests::isVoyageStep)) continue;
            if (progress.addToSet(VOYAGE_TYPES_KEY, voyageType)) {
                changed = true;
            }
            while (GotMainQuests.isCurrentStepComplete(template, progress, context)) {
                GotMainQuests.advanceCompletedStep(player, profile, progress, template);
                changed = true;
                if (progress.stepIndex() < template.steps().size()) continue;
                continue block0;
            }
        }
        return changed;
    }

    private static boolean advanceSettlementCreation(class_3222 player) {
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        boolean changed = false;
        for (MainQuestProgress progress : profile.activeQuests()) {
            MainQuestStep step;
            if (!SETTLER_QUEST_ID.equals((Object)progress.questId())) continue;
            MainQuestTemplate template = MainQuestTemplateRegistry.get(progress.questId());
            MainQuestStep mainQuestStep = step = template == null ? null : template.stepAt(progress.stepIndex());
            while (step != null && (step.type() == MainQuestStepType.HAVE_ITEM_COUNT || step.type() == MainQuestStepType.CREATE_PLAYER_SETTLEMENT)) {
                GotMainQuests.advanceCompletedStep(player, profile, progress, template);
                changed = true;
                step = template.stepAt(progress.stepIndex());
            }
        }
        return changed;
    }

    private static boolean isVoyageStep(MainQuestStep step) {
        return step.type() == MainQuestStepType.TAKE_LAND_VOYAGE || step.type() == MainQuestStepType.TAKE_SEA_VOYAGE;
    }

    private static void advanceCompletedStep(class_3222 player, PlayerMainQuestProfile profile, MainQuestProgress progress, MainQuestTemplate template) {
        MainQuestStep completedStep = template.stepAt(progress.stepIndex());
        progress.advance();
        if (completedStep != null) {
            GotMainQuests.announceStepCompleted(player, template, completedStep);
        }
        if (progress.stepIndex() >= template.steps().size()) {
            GotMainQuests.completeQuest(player, profile, template);
        }
    }

    private static void announceStepCompleted(class_3222 player, MainQuestTemplate template, MainQuestStep step) {
        player.method_7353((class_2561)class_2561.method_43470((String)("Main quest step completed: " + step.text())).method_27692(class_124.field_1060), true);
        ModAdvancementService.send(player, ModAdvancementType.QUEST_COMPLETED, "Main Quest Step Complete", template.displayName() + ": " + step.text(), class_1802.field_8674);
    }

    private static boolean isCurrentStepComplete(MainQuestTemplate template, MainQuestProgress progress, MainQuestContext context) {
        MainQuestStep step = template.stepAt(progress.stepIndex());
        if (step == null) {
            return false;
        }
        if (step.type() == MainQuestStepType.ENTER_CASTLE_CLAIM) {
            return context.castleClaim() != null;
        }
        if (step.type() == MainQuestStepType.ENTER_CITY_STRUCTURE) {
            return GotMainQuests.isInsideMatchingCityStructure(step, context);
        }
        if (step.type() == MainQuestStepType.HAVE_ITEM_COUNT) {
            return GotMainQuests.hasItemCount(context.player(), step.itemId(), Math.max(1, step.requiredCount()));
        }
        if (step.type() == MainQuestStepType.TAKE_LAND_VOYAGE) {
            return progress.hasInSet(VOYAGE_TYPES_KEY, LAND_VOYAGE_VALUE);
        }
        if (step.type() == MainQuestStepType.TAKE_SEA_VOYAGE) {
            return progress.hasInSet(VOYAGE_TYPES_KEY, SEA_VOYAGE_VALUE);
        }
        return false;
    }

    private static void onKilledEntity(class_3218 world, class_1297 entity, class_1297 killedEntity) {
        class_3222 player;
        block5: {
            block4: {
                if (!(entity instanceof class_3222)) break block4;
                player = (class_3222)entity;
                if (killedEntity instanceof RobberNpcEntity) break block5;
            }
            return;
        }
        if (GotMainQuests.advanceCounterStep(player, MainQuestStepType.KILL_ROBBERS, KILLED_ROBBERS_KEY)) {
            GotMainQuests.sendMainQuestState(player);
        }
    }

    private static boolean isInsideMatchingCityStructure(MainQuestStep step, MainQuestContext context) {
        if (context.castleClaim() == null) {
            return false;
        }
        for (CityStructureClaim claim : context.castleClaim().getCityStructureClaims()) {
            if (!step.matchesStructurePath(claim.structurePath()) || !claim.footprint().method_14662((class_2382)context.pos())) continue;
            return true;
        }
        return false;
    }

    private static boolean hasItemCount(class_3222 player, String itemId, int requiredCount) {
        return GotMainQuests.countInventoryItem(player, itemId, requiredCount) >= requiredCount;
    }

    private static int countInventoryItem(class_3222 player, String itemId, int cap) {
        class_2960 id = class_2960.method_12829((String)itemId);
        if (id == null) {
            return 0;
        }
        class_1792 item = (class_1792)class_7923.field_41178.method_10223(id);
        int count = 0;
        for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
            class_1799 stack = player.method_31548().method_5438(slot);
            if (!stack.method_31574(item) || cap <= 0 || (count += stack.method_7947()) < cap) continue;
            return cap;
        }
        return count;
    }

    private static int stepProgressRequired(MainQuestStep step) {
        return switch (step.type()) {
            case MainQuestStepType.TALK_TO_NPCS, MainQuestStepType.HIRE_TROOPS, MainQuestStepType.EXECUTE_COMMANDER_ACTIONS, MainQuestStepType.KILL_ROBBERS, MainQuestStepType.HAVE_ITEM_COUNT -> Math.max(1, step.requiredCount());
            default -> 1;
        };
    }

    private static int stepProgressValue(class_3222 player, MainQuestProgress progress, MainQuestStep step) {
        int required = GotMainQuests.stepProgressRequired(step);
        if (GotMainQuests.templateStepCompleted(progress, step)) {
            return required;
        }
        int value = switch (step.type()) {
            case MainQuestStepType.TALK_TO_NPCS -> progress.setSize(TALKED_NPCS_KEY);
            case MainQuestStepType.HIRE_TROOPS -> progress.getCounter(HIRED_TROOPS_KEY);
            case MainQuestStepType.EXECUTE_COMMANDER_ACTIONS -> progress.setSize(COMMANDER_ACTIONS_KEY);
            case MainQuestStepType.KILL_ROBBERS -> progress.getCounter(KILLED_ROBBERS_KEY);
            case MainQuestStepType.TAKE_LAND_VOYAGE -> {
                if (progress.hasInSet(VOYAGE_TYPES_KEY, LAND_VOYAGE_VALUE)) {
                    yield 1;
                }
                yield 0;
            }
            case MainQuestStepType.TAKE_SEA_VOYAGE -> {
                if (progress.hasInSet(VOYAGE_TYPES_KEY, SEA_VOYAGE_VALUE)) {
                    yield 1;
                }
                yield 0;
            }
            case MainQuestStepType.HAVE_ITEM_COUNT -> GotMainQuests.countInventoryItem(player, step.itemId(), required);
            default -> 0;
        };
        return Math.max(0, Math.min(value, required));
    }

    private static boolean templateStepCompleted(MainQuestProgress progress, MainQuestStep step) {
        MainQuestTemplate template = MainQuestTemplateRegistry.get(progress.questId());
        return template != null && template.steps().indexOf(step) < progress.stepIndex();
    }

    private static void completeQuest(class_3222 player, PlayerMainQuestProfile profile, MainQuestTemplate template) {
        for (MainQuestReward reward : template.rewards()) {
            class_1799 stack = new class_1799((class_1935)reward.item(), reward.count());
            player.method_31548().method_7398(stack);
        }
        profile.complete(template.id());
        player.method_7353((class_2561)class_2561.method_43470((String)("Main quest completed: " + template.displayName())).method_27692(class_124.field_1065), true);
        ModAdvancementService.send(player, ModAdvancementType.QUEST_COMPLETED, "Main Quest Completed", template.displayName(), class_1802.field_8674);
        if (template.nextQuestId() != null) {
            GotMainQuests.giveQuest(player, template.nextQuestId());
        }
    }

    private static MainQuestStatePayload createPayload(class_3222 player) {
        PlayerMainQuestProfile profile = ((PlayerMainQuestHolder)player).got$getMainQuestProfile();
        List<MainQuestStatePayload.Entry> active = profile.activeQuests().stream().map(progress -> {
            MainQuestTemplate template = MainQuestTemplateRegistry.get(progress.questId());
            if (template == null) {
                return null;
            }
            return new MainQuestStatePayload.Entry(template.id().toString(), template.displayName(), template.description(), template.steps().stream().map(step -> new MainQuestStatePayload.Step(step.id(), step.text(), GotMainQuests.stepProgressValue(player, progress, step), GotMainQuests.stepProgressRequired(step))).toList(), progress.stepIndex(), template.rewards().stream().map(reward -> new MainQuestStatePayload.Reward(reward.itemId().toString(), reward.count())).toList());
        }).filter(entry -> entry != null).toList();
        List<String> completed = profile.completedQuestIds().stream().map(class_2960::toString).toList();
        return new MainQuestStatePayload(active, completed);
    }
}

