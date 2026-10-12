/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
 *  net.fabricmc.fabric.api.event.player.UseEntityCallback
 *  net.minecraft.class_1109
 *  net.minecraft.class_1113
 *  net.minecraft.class_124
 *  net.minecraft.class_1269
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3414
 *  net.minecraft.class_3532
 *  net.minecraft.class_746
 *  net.minecraft.class_8710
 *  net.minecraft.class_9779
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.dialogue;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_124;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3414;
import net.minecraft.class_3532;
import net.minecraft.class_746;
import net.minecraft.class_8710;
import net.minecraft.class_9779;
import org.jetbrains.annotations.Nullable;
import trep.got.GotMod;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.NpcAttributeProfile;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.character.PlayerGeneticTrait;
import trep.got.character.PlayerPersonalityTrait;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.dialogue.DialogueResponseRegistry;
import trep.got.client.dialogue.DialogueResponseType;
import trep.got.client.house.PlayerHouseClientState;
import trep.got.client.mainquest.MainQuestClientState;
import trep.got.client.map.CastleMapClientState;
import trep.got.client.map.VillageMapClientState;
import trep.got.client.quest.QuestJournalClientState;
import trep.got.client.screen.PlayerHouseSigilScreen;
import trep.got.client.troop.TroopRosterClientState;
import trep.got.dialogue.NpcChatAction;
import trep.got.economy.GotEconomy;
import trep.got.entity.AbstractDialogueHumanoidEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.HireableUnitEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.entity.RobberNpcEntity;
import trep.got.entity.SettlerNpcEntity;
import trep.got.entity.VesselVariant;
import trep.got.entity.WildlingRaiderNpcEntity;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.ReputationExperienceService;
import trep.got.kingdom.ReputationPledgeOption;
import trep.got.kingdom.ReputationProgressionService;
import trep.got.kingdom.ReputationRoute;
import trep.got.kingdom.ReputationTitle;
import trep.got.network.BanditCampNegotiationResultPayload;
import trep.got.network.CastleMapPayload;
import trep.got.network.NpcChatResultPayload;
import trep.got.network.NpcDialogueRelationPayload;
import trep.got.network.PlayerHouseStatePayload;
import trep.got.network.QuestJournalStatePayload;
import trep.got.network.RequestBanditCampDecisionPayload;
import trep.got.network.RequestBlacksmithTroopUpgradePayload;
import trep.got.network.RequestCreatePlayerHousePayload;
import trep.got.network.RequestGateWarningActionPayload;
import trep.got.network.RequestHireTroopPayload;
import trep.got.network.RequestKnighthoodPayload;
import trep.got.network.RequestLockpickingExcusePayload;
import trep.got.network.RequestNpcChatActionPayload;
import trep.got.network.RequestQuestActionPayload;
import trep.got.network.RequestRobberAmbushDecisionPayload;
import trep.got.network.RequestSettlerDecisionPayload;
import trep.got.network.RequestVillageTradePayload;
import trep.got.network.RequestVoyagePayload;
import trep.got.network.SetNpcDialogueStatePayload;
import trep.got.network.VillageMapPayload;
import trep.got.quest.PledgeQuestLogic;
import trep.got.quest.QuestActionType;
import trep.got.quest.QuestDebugLogBuffer;
import trep.got.quest.QuestInstance;
import trep.got.quest.QuestNegotiationOutcome;
import trep.got.quest.QuestNegotiationService;
import trep.got.quest.QuestTemplateRegistry;
import trep.got.quest.ValyrianRumorQuestLogic;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotDynastyNpcs;
import trep.got.registry.GotItems;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotNpcMarriage;
import trep.got.registry.GotReligionOptions;
import trep.got.registry.GotSounds;
import trep.got.relations.NpcRelationTier;
import trep.got.religion.ReligionSelectionOption;
import trep.got.skill.SkillAttributeType;
import trep.got.travel.LandTravelRouteHelper;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.DynamicTroopRegistry;
import trep.got.troop.GotTroops;
import trep.got.voyage.VoyageNetwork;

@Environment(value=EnvType.CLIENT)
public final class DialogueSceneClientState {
    private static final class_2960 OPTION_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/dialogue_option.png");
    private static final class_2960 OPTION_WIDE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/dialogue_option_wide.png");
    private static final class_2960 SHIP_ITEM_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/item/ship.png");
    private static final class_2960 GALLEY_ITEM_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/item/galley.png");
    private static final class_2960 CART_ITEM_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/item/cart.png");
    private static final class_2960 CART_2HORSE_ITEM_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/item/cart_2horse.png");
    private static final class_2960 PAGE_BACKWARD_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"textures/gui/sprites/recipe_book/page_backward.png");
    private static final class_2960 PAGE_BACKWARD_HIGHLIGHTED_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"textures/gui/sprites/recipe_book/page_backward_highlighted.png");
    private static final class_2960 PAGE_FORWARD_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"textures/gui/sprites/recipe_book/page_forward.png");
    private static final class_2960 PAGE_FORWARD_HIGHLIGHTED_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"textures/gui/sprites/recipe_book/page_forward_highlighted.png");
    private static final class_2960 CHAT_BUBBLE_TOP = class_2960.method_60655((String)"agob", (String)"textures/ui/chat_bubble_top.png");
    private static final class_2960 CHAT_BUBBLE_MIDDLE = class_2960.method_60655((String)"agob", (String)"textures/ui/chat_bubble_middle.png");
    private static final class_2960 CHAT_BUBBLE_BOTTOM = class_2960.method_60655((String)"agob", (String)"textures/ui/chat_bubble_bottom.png");
    private static final class_2960 RELATION_ENEMY = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_relation_enemy.png");
    private static final class_2960 RELATION_SUSPICIOUS = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_relation_suspicious.png");
    private static final class_2960 RELATION_NEUTRAL = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_relation_neutral.png");
    private static final class_2960 RELATION_FRIEND = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_relation_friend.png");
    private static final class_2960 RELATION_ALLY = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_relation_ally.png");
    private static final int TRANSITION_TICKS = 20;
    private static final int PORTRAIT_SIZE = 24;
    private static final int OPTION_WIDTH = 64;
    private static final int OPTION_WIDE_WIDTH = 96;
    private static final int OPTION_HEIGHT = 16;
    private static final int OPTION_GAP = 4;
    private static final int OPTION_ICON_SIZE = 8;
    private static final int OPTION_ICON_GAP = 3;
    private static final int PAGE_ARROW_WIDTH = 12;
    private static final int PAGE_ARROW_HEIGHT = 17;
    private static final int PAGE_ARROW_GAP = 6;
    private static final int HUD_MARGIN_X = 28;
    private static final int HUD_MARGIN_Y = 56;
    private static final int PORTRAIT_Y_OFFSET = -16;
    private static final int BUBBLE_TOP_TEXTURE_WIDTH = 32;
    private static final int BUBBLE_BODY_TEXTURE_WIDTH = 128;
    private static final int BUBBLE_TOP_HEIGHT = 4;
    private static final int BUBBLE_MIDDLE_HEIGHT = 8;
    private static final int BUBBLE_BOTTOM_HEIGHT = 4;
    private static final int BUBBLE_SCALE = 2;
    private static final int BUBBLE_WIDTH = 256;
    private static final int BUBBLE_TOP_DRAW_HEIGHT = 8;
    private static final int BUBBLE_MIDDLE_DRAW_HEIGHT = 16;
    private static final int BUBBLE_BOTTOM_DRAW_HEIGHT = 8;
    private static final int BUBBLE_TEXT_PADDING_X = 16;
    private static final int BUBBLE_TEXT_PADDING_TOP = 8;
    private static final int BUBBLE_TEXT_PADDING_BOTTOM = 8;
    private static final int BUBBLE_BOTTOM_MARGIN = 38;
    private static final int BUBBLE_X_OFFSET = -16;
    private static final int BUBBLE_ITEM_ICON_SIZE = 9;
    private static final int BUBBLE_ITEM_TEXT_GAP = 3;
    private static final int BUBBLE_TEXT_WIDTH = 224;
    private static final int TYPEWRITER_TICKS_PER_CHARACTER = 1;
    private static final int TYPING_SOUND_INTERVAL_TICKS = 20;
    private static final int AUTO_CLOSE_DELAY_TICKS = 16;
    private static final float TYPING_SOUND_VOLUME = 0.55f;
    private static final float TYPING_SOUND_PITCH_MIN = 0.96f;
    private static final float TYPING_SOUND_PITCH_MAX = 1.04f;
    private static final double DIALOGUE_RANGE = 7.0;
    private static final double FORCED_DIALOGUE_RANGE = 10.0;
    private static final int REOPEN_COOLDOWN_TICKS = 12;
    private static final class_3414[] DIALOGUE_TYPING_SOUNDS = new class_3414[]{GotSounds.DIALOGUE_WRITING_1, GotSounds.DIALOGUE_WRITING_2};
    private static boolean active;
    private static int targetEntityId;
    private static int sceneTicks;
    private static boolean cursorUnlockedByDialogue;
    private static int reopenCooldownTicks;
    private static int blockedEntityId;
    private static boolean interactionLatch;
    private static BubbleContent activeBubbleContent;
    private static int responseStartedAtTick;
    private static int lastTypedCharacterCount;
    private static int nextTypingSoundTick;
    private static int lastTypingSoundIndex;
    private static int pendingAutoCloseTick;
    private static DialoguePage currentPage;
    private static int currentVoyageIndex;
    private static int currentVoyageShipIndex;
    private static int currentUpgradeTroopIndex;
    private static int currentUpgradeTargetIndex;
    private static String currentNpcRelationId;
    private static int currentNpcRelationValue;
    private static String currentNpcKingdomId;
    private static String currentDialogueContextId;
    private static String currentDialogueSubjectName;
    private static NpcAttributeProfile currentNpcAttributes;
    private static boolean currentTitleChallengeAvailable;
    private static int currentTitleChallengeCooldownSeconds;
    private static boolean currentTourneyAvailable;
    private static boolean currentTourneyAttended;
    private static boolean lockpickingCaughtResolving;
    private static boolean gateWarningResolving;
    private static boolean serverForcedClose;

    private DialogueSceneClientState() {
    }

    public static void initialize() {
        DialogueResponseRegistry.initialize();
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            AbstractDialogueHumanoidEntity dialogueEntity;
            if (!(world.method_8608() && entity instanceof AbstractDialogueHumanoidEntity && (dialogueEntity = (AbstractDialogueHumanoidEntity)entity).supportsDialogueInteraction())) {
                return class_1269.field_5811;
            }
            class_310 client = class_310.method_1551();
            if (client.field_1724 == null || client.field_1755 != null) {
                return class_1269.field_5811;
            }
            if (interactionLatch) {
                GotMod.LOGGER.info("Dialogue interaction ignored by latch: entityId={} active={} targetEntityId={} cooldownTicks={}", new Object[]{entity.method_5628(), active, targetEntityId, reopenCooldownTicks});
                return class_1269.field_5814;
            }
            if (reopenCooldownTicks > 0) {
                GotMod.LOGGER.info("Dialogue interaction blocked by cooldown: entityId={} blockedEntityId={} cooldownTicks={} active={} targetEntityId={}", new Object[]{entity.method_5628(), blockedEntityId, reopenCooldownTicks, active, targetEntityId});
                if (blockedEntityId == entity.method_5628()) {
                    return class_1269.field_5814;
                }
                return class_1269.field_5814;
            }
            GotMod.LOGGER.info("Dialogue interaction received: entityId={} active={} targetEntityId={} blockedEntityId={} cooldownTicks={}", new Object[]{entity.method_5628(), active, targetEntityId, blockedEntityId, reopenCooldownTicks});
            interactionLatch = true;
            DialogueSceneClientState.start(entity);
            return class_1269.field_5814;
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (interactionLatch && client.field_1724 != null && !client.field_1690.field_1904.method_1434()) {
                GotMod.LOGGER.info("Dialogue interaction latch released");
                interactionLatch = false;
            }
            DialogueSceneClientState.tick(client);
        });
        HudRenderCallback.EVENT.register(DialogueSceneClientState::renderHud);
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> DialogueSceneClientState.clear(false));
    }

    public static boolean isActive() {
        return active;
    }

    public static void openFromServer(int entityId, NpcAttributeProfile attributes, class_310 client) {
        client.execute(() -> {
            if (client.field_1687 == null) {
                return;
            }
            class_1297 entity = client.field_1687.method_8469(entityId);
            if (entity != null) {
                DialogueSceneClientState.start(entity, false, true);
                currentNpcAttributes = attributes == null ? NpcAttributeProfile.EMPTY : attributes;
            }
        });
    }

    public static void close() {
        GotMod.LOGGER.info("Dialogue close requested: active={} targetEntityId={}", (Object)active, (Object)targetEntityId);
        DialogueSceneClientState.clear(true);
    }

    public static void openLockpickingCaught(int entityId, class_310 client) {
        client.execute(() -> {
            if (client.field_1687 == null) {
                return;
            }
            class_1297 entity = client.field_1687.method_8469(entityId);
            if (entity == null) {
                return;
            }
            DialogueSceneClientState.start(entity, false, true);
            currentPage = DialoguePage.LOCKPICKING_CAUGHT;
            lockpickingCaughtResolving = false;
            DialogueSceneClientState.updateBubbleContent(BubbleContent.ofText("What are you doing in here?"), true);
        });
    }

    public static void openGateWarning(int entityId, class_310 client) {
        client.execute(() -> {
            if (client.field_1687 == null) {
                return;
            }
            class_1297 entity = client.field_1687.method_8469(entityId);
            if (entity == null) {
                return;
            }
            DialogueSceneClientState.start(entity, false, true);
            currentPage = DialoguePage.GATE_WARNING;
            gateWarningResolving = false;
            DialogueSceneClientState.updateBubbleContent(BubbleContent.ofText("You are not welcome here. Turn around."), true);
        });
    }

    public static void closeFromServer(int entityId, class_310 client) {
        client.execute(() -> {
            if (!active || targetEntityId != entityId) {
                return;
            }
            serverForcedClose = true;
            DialogueSceneClientState.clear(false);
        });
    }

    public static void applyBanditCampNegotiationResult(BanditCampNegotiationResultPayload payload, class_310 client) {
        client.execute(() -> {
            if (!active || payload.targetEntityId() != targetEntityId) {
                return;
            }
            currentPage = DialoguePage.QUEST_BANDIT_CAMP_RESULT;
            activeBubbleContent = BubbleContent.ofText(payload.success() ? "Aye, fair enough. We'll take our business elsewhere. Return to the one who sent you and tell them the road is clear." : "No bargains then. Draw steel.");
            DialogueSceneClientState.resetTypewriterState();
        });
    }

    public static void applyNpcRelation(NpcDialogueRelationPayload payload, class_310 client) {
        client.execute(() -> {
            if (!active || payload.targetEntityId() != targetEntityId) {
                return;
            }
            currentNpcRelationId = payload.npcId();
            currentNpcRelationValue = payload.relation();
            currentNpcKingdomId = payload.kingdomId();
            currentDialogueContextId = payload.dialogueContextId();
            currentDialogueSubjectName = payload.dialogueSubjectName();
            currentNpcAttributes = payload.attributes();
            currentTitleChallengeAvailable = payload.titleChallengeAvailable();
            currentTitleChallengeCooldownSeconds = payload.titleChallengeCooldownSeconds();
            currentTourneyAvailable = payload.tourneyAvailable();
            currentTourneyAttended = payload.tourneyAttended();
            class_1297 entity = DialogueSceneClientState.getTargetEntity(client);
            if (currentPage == DialoguePage.ROOT && DialogueSceneClientState.shouldUseRelationOpening(entity)) {
                DialogueSceneClientState.showResponse(DialogueSceneClientState.responseTypeForRelation(currentNpcRelationValue), entity == null ? (long)sceneTicks : (long)(entity.method_5628() + sceneTicks), false);
            }
        });
    }

    public static void applyNpcChatResult(NpcChatResultPayload payload, class_310 client) {
        client.execute(() -> {
            if (!active || payload.targetEntityId() != targetEntityId) {
                return;
            }
            DialogueResponseType responseType = DialogueResponseType.byId(payload.responseTypeId());
            currentNpcRelationValue = payload.relation();
            currentNpcKingdomId = payload.kingdomId();
            currentDialogueContextId = payload.dialogueContextId();
            currentDialogueSubjectName = payload.dialogueSubjectName();
            currentPage = DialoguePage.CHAT_RESULT;
            DialogueSceneClientState.showResponse(responseType == null ? DialogueResponseType.NEUTRAL : responseType, payload.responseSeed(), true);
        });
    }

    public static boolean handleClick(double mouseX, double mouseY) {
        class_310 client = class_310.method_1551();
        if (!active || client.field_1724 == null) {
            return false;
        }
        double scaledMouseX = DialogueSceneClientState.getScaledMouseX(client, mouseX);
        double scaledMouseY = DialogueSceneClientState.getScaledMouseY(client, mouseY);
        DialogueLayout layout = DialogueSceneClientState.getLayout(client);
        List<DialogueOption> options = DialogueSceneClientState.getOptions(client);
        if (currentPage == DialoguePage.VOYAGE_SELECT) {
            int rightX;
            int leftX;
            VoyagePageState voyageState = DialogueSceneClientState.getVoyagePageState(client);
            if (voyageState.canCycleShipType()) {
                int shipY = layout.optionsY();
                leftX = layout.optionsX() - 12 - 6;
                rightX = layout.optionsX() + 64 + 6;
                if (DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, leftX, shipY, 12, 17)) {
                    DialogueSceneClientState.cycleVoyageShipType(client, -1);
                    return true;
                }
                if (DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, rightX, shipY, 12, 17)) {
                    DialogueSceneClientState.cycleVoyageShipType(client, 1);
                    return true;
                }
            }
            if (voyageState.canCycleDestination()) {
                int voyageY = layout.optionsY() + 20;
                leftX = layout.optionsX() - 12 - 6;
                rightX = layout.optionsX() + 64 + 6;
                if (DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, leftX, voyageY, 12, 17)) {
                    DialogueSceneClientState.cycleVoyageChoice(client, -1);
                    return true;
                }
                if (DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, rightX, voyageY, 12, 17)) {
                    DialogueSceneClientState.cycleVoyageChoice(client, 1);
                    return true;
                }
            }
        }
        if (currentPage == DialoguePage.BLACKSMITH_UPGRADE_SELECT) {
            BlacksmithUpgradePageState upgradeState = DialogueSceneClientState.getBlacksmithUpgradePageState(client);
            int leftX = layout.optionsX() - 12 - 6;
            int rightXWide = layout.optionsX() + 96 + 6;
            int troopY = layout.optionsY();
            if (DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, leftX, troopY, 12, 17)) {
                DialogueSceneClientState.cycleBlacksmithUpgradeTroop(client, -1);
                return true;
            }
            if (DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, rightXWide, troopY, 12, 17)) {
                DialogueSceneClientState.cycleBlacksmithUpgradeTroop(client, 1);
                return true;
            }
            int targetY = layout.optionsY() + 20;
            if (DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, leftX, targetY, 12, 17)) {
                DialogueSceneClientState.cycleBlacksmithUpgradeTarget(client, -1);
                return true;
            }
            if (DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, rightXWide, targetY, 12, 17)) {
                DialogueSceneClientState.cycleBlacksmithUpgradeTarget(client, 1);
                return true;
            }
        }
        for (int index = 0; index < options.size(); ++index) {
            DialogueOption option = options.get(index);
            int width = option.wide() ? 96 : 64;
            int y = layout.optionsY() + index * 20;
            int x = layout.optionsX() + (option.wide() ? 0 : (layout.maxOptionWidth() - 64) / 2);
            if (!DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, x, y, width, 16)) continue;
            if (option.enabled()) {
                DialogueSceneClientState.activateOption(option);
            }
            return true;
        }
        return true;
    }

    private static void start(class_1297 entity) {
        DialogueSceneClientState.start(entity, true, false);
    }

    private static void start(class_1297 entity, boolean notifyServer, boolean bypassCooldown) {
        class_310 client = class_310.method_1551();
        if (bypassCooldown) {
            reopenCooldownTicks = 0;
            blockedEntityId = -1;
        }
        if (reopenCooldownTicks > 0) {
            GotMod.LOGGER.info("Dialogue start ignored by cooldown: entityId={} blockedEntityId={} cooldownTicks={}", new Object[]{entity.method_5628(), blockedEntityId, reopenCooldownTicks});
            if (blockedEntityId == entity.method_5628()) {
                return;
            }
            return;
        }
        if (active && targetEntityId == entity.method_5628()) {
            GotMod.LOGGER.info("Dialogue start ignored because already active for entityId={}", (Object)entity.method_5628());
            return;
        }
        if (active && targetEntityId != entity.method_5628()) {
            GotMod.LOGGER.info("Dialogue retargeting from entityId={} to entityId={}", (Object)targetEntityId, (Object)entity.method_5628());
            DialogueSceneClientState.clear(false);
        }
        active = true;
        targetEntityId = entity.method_5628();
        blockedEntityId = entity.method_5628();
        sceneTicks = 0;
        currentPage = DialoguePage.ROOT;
        currentVoyageIndex = 0;
        currentVoyageShipIndex = 0;
        currentUpgradeTroopIndex = 0;
        currentUpgradeTargetIndex = 0;
        pendingAutoCloseTick = -1;
        client.field_1690.field_1904.method_23481(false);
        if (client.field_1724 != null) {
            client.field_1724.method_6075();
        }
        DialogueSceneClientState.setOpeningResponse(entity);
        GotMod.LOGGER.info("Dialogue opened: entityId={} blockedEntityId={}", (Object)targetEntityId, (Object)blockedEntityId);
        if (notifyServer) {
            ClientPlayNetworking.send((class_8710)new SetNpcDialogueStatePayload(targetEntityId, true));
        }
        if (!cursorUnlockedByDialogue) {
            client.field_1729.method_1610();
            cursorUnlockedByDialogue = true;
        }
    }

    private static void tick(class_310 client) {
        if (reopenCooldownTicks > 0 && --reopenCooldownTicks == 0) {
            blockedEntityId = -1;
        }
        if (!active) {
            return;
        }
        class_746 player = client.field_1724;
        class_1297 entity = DialogueSceneClientState.getTargetEntity(client);
        if (player == null || entity == null || client.field_1687 == null) {
            GotMod.LOGGER.info("Dialogue clearing because scene references are invalid: playerPresent={} entityPresent={} worldPresent={} targetEntityId={}", new Object[]{player != null, entity != null, client.field_1687 != null, targetEntityId});
            DialogueSceneClientState.clear(false);
            return;
        }
        if (client.field_1755 != null) {
            GotMod.LOGGER.info("Dialogue clearing because another screen is open: screenClass={} targetEntityId={}", (Object)client.field_1755.getClass().getName(), (Object)targetEntityId);
            DialogueSceneClientState.clear(false);
            return;
        }
        double dialogueRange = DialogueSceneClientState.activeDialogueRange();
        if (player.method_5858(entity) > dialogueRange * dialogueRange || !entity.method_5805()) {
            GotMod.LOGGER.info("Dialogue clearing because target is invalid: entityId={} alive={} distanceSq={}", new Object[]{targetEntityId, entity.method_5805(), player.method_5858(entity)});
            DialogueSceneClientState.clear(false);
            return;
        }
        ++sceneTicks;
        DialogueSceneClientState.tickTypingSound(client);
        float progress = DialogueSceneClientState.getTransitionProgress();
        class_243 playerLookTarget = DialogueSceneClientState.getNpcLookPosition(entity);
        float targetPlayerYaw = DialogueSceneClientState.getYawTowards(player.method_33571(), playerLookTarget);
        float targetPlayerPitch = DialogueSceneClientState.getPitchTowards(player.method_33571(), playerLookTarget);
        player.method_36456(DialogueSceneClientState.lerpAngle(player.method_36454(), targetPlayerYaw, progress));
        player.method_5847(player.method_36454());
        player.method_5636(player.method_36454());
        player.method_36457(class_3532.method_16439((float)progress, (float)player.method_36455(), (float)targetPlayerPitch));
        player.method_18800(0.0, 0.0, 0.0);
        if (entity instanceof AbstractDialogueHumanoidEntity) {
            AbstractDialogueHumanoidEntity npc = (AbstractDialogueHumanoidEntity)entity;
            class_243 npcLookStart = npc.method_19538().method_1031(0.0, Math.min(2.0, (double)npc.method_17682()), 0.0);
            float targetNpcYaw = DialogueSceneClientState.getYawTowards(npcLookStart, player.method_33571());
            float currentHeadYaw = npc.method_5791();
            float newHeadYaw = DialogueSceneClientState.lerpAngle(currentHeadYaw, targetNpcYaw, progress);
            npc.method_36456(DialogueSceneClientState.lerpAngle(npc.method_36454(), targetNpcYaw, progress));
            npc.method_5847(newHeadYaw);
            npc.method_5636(DialogueSceneClientState.lerpAngle(npc.field_6283, targetNpcYaw, progress * 0.6f));
        }
        if (pendingAutoCloseTick >= 0 && sceneTicks >= pendingAutoCloseTick) {
            DialogueSceneClientState.close();
        }
    }

    private static void renderHud(class_332 context, class_9779 tickCounter) {
        HireRequirementTooltip optionTooltip;
        HireRequirementTooltip hireTooltip;
        class_310 client = class_310.method_1551();
        if (!active || client.field_1724 == null) {
            return;
        }
        List<DialogueOption> options = DialogueSceneClientState.getOptions(client);
        DialogueLayout layout = DialogueSceneClientState.getLayout(client);
        double scaledMouseX = DialogueSceneClientState.getScaledMouseX(client, client.field_1729.method_1603());
        double scaledMouseY = DialogueSceneClientState.getScaledMouseY(client, client.field_1729.method_1604());
        DialogueSceneClientState.drawSpeechBubble(context, client, layout.bubbleX(), layout.bubbleY());
        for (int index = 0; index < options.size(); ++index) {
            DialogueOption option = options.get(index);
            int y = layout.optionsY() + index * 20;
            int x = layout.optionsX() + (option.wide() ? 0 : (layout.maxOptionWidth() - 64) / 2);
            int width = option.wide() ? 96 : 64;
            boolean hovered = DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, x, y, width, 16);
            DialogueSceneClientState.drawOption(context, client, x, y, option, hovered);
        }
        if (currentPage == DialoguePage.VOYAGE_SELECT) {
            DialogueSceneClientState.drawVoyageArrows(context, client, layout, scaledMouseX, scaledMouseY);
        }
        if (currentPage == DialoguePage.BLACKSMITH_UPGRADE_SELECT) {
            DialogueSceneClientState.drawBlacksmithUpgradeArrows(context, client, layout, scaledMouseX, scaledMouseY);
        }
        if ((hireTooltip = DialogueSceneClientState.getLockedHireTooltip(client, layout, options, scaledMouseX, scaledMouseY)) != null) {
            DialogueSceneClientState.drawHireRequirementTooltip(context, client, scaledMouseX, scaledMouseY, hireTooltip);
        }
        if ((optionTooltip = DialogueSceneClientState.getOptionTooltip(layout, options, scaledMouseX, scaledMouseY)) != null) {
            DialogueSceneClientState.drawHireRequirementTooltip(context, client, scaledMouseX, scaledMouseY, optionTooltip);
        }
        DialogueSceneClientState.drawDialogueTargetName(context, client, layout, scaledMouseX, scaledMouseY);
        DialogueSceneClientState.drawNpcRelation(context, client, layout, scaledMouseX, scaledMouseY);
    }

    private static void drawOption(class_332 context, class_310 client, int x, int y, DialogueOption option, boolean hovered) {
        int padding;
        int maxTextWidth;
        class_2960 iconTexture;
        String label = option.label();
        boolean enabled = option.enabled();
        int currentWidth = option.wide() ? 96 : 64;
        class_2960 texture = option.wide() ? OPTION_WIDE_TEXTURE : OPTION_TEXTURE;
        float brightness = hovered && enabled ? 1.12f : 1.0f;
        RenderSystem.setShaderColor((float)brightness, (float)brightness, (float)brightness, (float)1.0f);
        context.method_25291(texture, x, y, 0, 0.0f, 0.0f, currentWidth, 16, currentWidth, 16);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (!enabled && option.showDisabledOverlay()) {
            context.method_25294(x, y, x + currentWidth, y + 16, -1728053248);
        }
        int iconWidth = (iconTexture = option.iconTexture()) != null ? 11 : 0;
        int textWidth = client.field_1772.method_1727(label);
        if (textWidth > (maxTextWidth = currentWidth - (padding = 4) * 2 - iconWidth)) {
            int overflow = textWidth - maxTextWidth;
            int stopTicks = 40;
            int totalTicks = overflow * 2 + stopTicks * 2;
            int time = sceneTicks % totalTicks;
            int offset = time < stopTicks ? 0 : (time < stopTicks + overflow ? time - stopTicks : (time < stopTicks * 2 + overflow ? overflow : overflow - (time - (stopTicks * 2 + overflow))));
            int textX = x + padding + iconWidth;
            int textY = y + 4;
            if (iconTexture != null) {
                context.method_25291(iconTexture, x + padding, y + 4, 0, 0.0f, 0.0f, 8, 8, 8, 8);
            }
            context.method_44379(textX, y, textX + maxTextWidth, y + 16);
            int textColor = enabled || !option.showDisabledOverlay() ? 0xFFFFFF : 0xA0A0A0;
            context.method_27535(client.field_1772, (class_2561)class_2561.method_43470((String)label), textX - offset, textY, textColor);
            context.method_44380();
        } else {
            int contentWidth = iconWidth + textWidth;
            int textX = x + (currentWidth - contentWidth) / 2 + iconWidth;
            int textY = y + 4;
            if (iconTexture != null) {
                context.method_25291(iconTexture, textX - 3 - 8, y + 4, 0, 0.0f, 0.0f, 8, 8, 8, 8);
            }
            int textColor = enabled || !option.showDisabledOverlay() ? 0xFFFFFF : 0xA0A0A0;
            context.method_27535(client.field_1772, (class_2561)class_2561.method_43470((String)label), textX, textY, textColor);
        }
    }

    private static void drawVoyageArrows(class_332 context, class_310 client, DialogueLayout layout, double scaledMouseX, double scaledMouseY) {
        boolean rightHovered;
        boolean leftHovered;
        int y;
        VoyagePageState voyageState = DialogueSceneClientState.getVoyagePageState(client);
        int leftX = layout.optionsX() - 12 - 6;
        int rightX = layout.optionsX() + 64 + 6;
        if (voyageState.canCycleShipType()) {
            y = layout.optionsY();
            leftHovered = DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, leftX, y, 12, 17);
            rightHovered = DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, rightX, y, 12, 17);
            context.method_25291(leftHovered ? PAGE_BACKWARD_HIGHLIGHTED_TEXTURE : PAGE_BACKWARD_TEXTURE, leftX, y, 0, 0.0f, 0.0f, 12, 17, 12, 17);
            context.method_25291(rightHovered ? PAGE_FORWARD_HIGHLIGHTED_TEXTURE : PAGE_FORWARD_TEXTURE, rightX, y, 0, 0.0f, 0.0f, 12, 17, 12, 17);
        }
        if (voyageState.canCycleDestination()) {
            y = layout.optionsY() + 16 + 4;
            leftHovered = DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, leftX, y, 12, 17);
            rightHovered = DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, rightX, y, 12, 17);
            context.method_25291(leftHovered ? PAGE_BACKWARD_HIGHLIGHTED_TEXTURE : PAGE_BACKWARD_TEXTURE, leftX, y, 0, 0.0f, 0.0f, 12, 17, 12, 17);
            context.method_25291(rightHovered ? PAGE_FORWARD_HIGHLIGHTED_TEXTURE : PAGE_FORWARD_TEXTURE, rightX, y, 0, 0.0f, 0.0f, 12, 17, 12, 17);
        }
    }

    private static void drawBlacksmithUpgradeArrows(class_332 context, class_310 client, DialogueLayout layout, double scaledMouseX, double scaledMouseY) {
        BlacksmithUpgradePageState upgradeState = DialogueSceneClientState.getBlacksmithUpgradePageState(client);
        int leftX = layout.optionsX() - 12 - 6;
        int rightXWide = layout.optionsX() + 96 + 6;
        DialogueSceneClientState.drawArrowPair(context, leftX, rightXWide, layout.optionsY(), scaledMouseX, scaledMouseY, upgradeState.canCycleTroops());
        DialogueSceneClientState.drawArrowPair(context, leftX, rightXWide, layout.optionsY() + 16 + 4, scaledMouseX, scaledMouseY, upgradeState.canCycleTargets());
    }

    private static void drawArrowPair(class_332 context, int leftX, int rightX, int y, double scaledMouseX, double scaledMouseY, boolean enabled) {
        boolean leftHovered = enabled && DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, leftX, y, 12, 17);
        boolean rightHovered = enabled && DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, rightX, y, 12, 17);
        context.method_25291(leftHovered ? PAGE_BACKWARD_HIGHLIGHTED_TEXTURE : PAGE_BACKWARD_TEXTURE, leftX, y, 0, 0.0f, 0.0f, 12, 17, 12, 17);
        context.method_25291(rightHovered ? PAGE_FORWARD_HIGHLIGHTED_TEXTURE : PAGE_FORWARD_TEXTURE, rightX, y, 0, 0.0f, 0.0f, 12, 17, 12, 17);
        if (!enabled) {
            context.method_25294(leftX, y, leftX + 12, y + 17, -1728053248);
            context.method_25294(rightX, y, rightX + 12, y + 17, -1728053248);
        }
    }

    private static void drawSpeechBubble(class_332 context, class_310 client, int x, int y) {
        List<BubbleRenderLine> allLines = DialogueSceneClientState.getAllBubbleLines(client);
        Objects.requireNonNull(client.field_1772);
        int lineHeight = 9 + 1;
        int bubbleHeight = DialogueSceneClientState.getBubbleHeight(client, allLines);
        int totalInnerHeight = 8 + Math.max(lineHeight, allLines.size() * lineHeight) + 8;
        int middleSectionCount = Math.max(1, class_3532.method_15386((float)((float)totalInnerHeight / 16.0f)));
        DialogueSceneClientState.drawBubbleSection(context, CHAT_BUBBLE_TOP, x, y, 32, 4, 256, 8);
        for (int index = 0; index < middleSectionCount; ++index) {
            DialogueSceneClientState.drawBubbleSection(context, CHAT_BUBBLE_MIDDLE, x, y + 8 + index * 16, 128, 8, 256, 16);
        }
        DialogueSceneClientState.drawBubbleSection(context, CHAT_BUBBLE_BOTTOM, x, y + bubbleHeight - 8, 128, 4, 256, 8);
        int textX = x + 16;
        int textY = y + 8;
        for (BubbleRenderLine line : DialogueSceneClientState.getVisibleBubbleLines(client)) {
            int lineX = textX;
            for (BubbleRenderSegment segment : line.segments()) {
                if (segment.item() != null) {
                    context.method_51427(new class_1799((class_1935)segment.item()), lineX, textY - 4);
                    lineX += segment.width();
                    continue;
                }
                context.method_51439(client.field_1772, (class_2561)class_2561.method_43470((String)segment.text()), lineX, textY, -16777216, false);
                lineX += segment.width();
            }
            textY += lineHeight;
        }
    }

    private static void drawNpcRelation(class_332 context, class_310 client, DialogueLayout layout, double scaledMouseX, double scaledMouseY) {
        if (currentNpcRelationId.isBlank()) {
            return;
        }
        int bubbleHeight = DialogueSceneClientState.getBubbleHeight(client, DialogueSceneClientState.getAllBubbleLines(client));
        int width = 40;
        int height = 10;
        int x = layout.bubbleX() + 256 - width - 12;
        int y = layout.bubbleY() + bubbleHeight - height - 10;
        NpcRelationTier tier = NpcRelationTier.fromValue(currentNpcRelationValue);
        context.method_25291(DialogueSceneClientState.relationTexture(tier), x, y + 1, 0, 0.0f, 0.0f, 8, 8, 8, 8);
        context.method_27535(client.field_1772, (class_2561)class_2561.method_43470((String)DialogueSceneClientState.formatRelationValue(currentNpcRelationValue)), x + 11, y, -1);
        if (DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, x, y, width, height)) {
            context.method_51438(client.field_1772, (class_2561)class_2561.method_43470((String)("Relation: " + tier.displayName() + " (" + DialogueSceneClientState.formatRelationValue(currentNpcRelationValue) + ")")), (int)scaledMouseX, (int)scaledMouseY);
        }
    }

    private static void drawDialogueTargetName(class_332 context, class_310 client, DialogueLayout layout, double scaledMouseX, double scaledMouseY) {
        class_1297 entity = DialogueSceneClientState.getTargetEntity(client);
        if (!(entity instanceof AbstractKingdomNpcEntity)) {
            return;
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)entity;
        String name = npc.method_5476().getString();
        if (name.isBlank()) {
            return;
        }
        int x = layout.bubbleX() + 4;
        int n = layout.bubbleY();
        Objects.requireNonNull(client.field_1772);
        int y = n - 9 - 6;
        context.method_27535(client.field_1772, (class_2561)class_2561.method_43470((String)name), x, y, -1);
        int width = client.field_1772.method_1727(name);
        if (currentNpcAttributes.isAssigned()) {
            Objects.requireNonNull(client.field_1772);
            if (DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, x, y, width, 9)) {
                DialogueSceneClientState.drawNpcAttributeTooltip(context, client, scaledMouseX, scaledMouseY, currentNpcAttributes);
            }
        }
    }

    private static void drawNpcAttributeTooltip(class_332 context, class_310 client, double scaledMouseX, double scaledMouseY, NpcAttributeProfile attributes) {
        ArrayList<NpcAttributeTooltipLine> lines = new ArrayList<NpcAttributeTooltipLine>();
        PlayerGeneticTrait geneticTrait = attributes.geneticTrait();
        if (geneticTrait != null) {
            lines.add(new NpcAttributeTooltipLine(null, geneticTrait.displayName(), -2570870, false));
        }
        for (PlayerPersonalityTrait playerPersonalityTrait : attributes.personalityTraits()) {
            lines.add(new NpcAttributeTooltipLine(null, playerPersonalityTrait.displayName(), -2570870, false));
        }
        boolean gapBeforeAttributes = !lines.isEmpty();
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            int amount = attributes.skillPoints().getOrDefault((Object)type, 0);
            int max = 100;
            if (geneticTrait != null) {
                max += geneticTrait.maxCapModifiers().getOrDefault((Object)type, 0).intValue();
            }
            lines.add(new NpcAttributeTooltipLine(type.getIconTexture(), type.getDisplayName() + " " + amount + " / " + max, -1, gapBeforeAttributes));
            gapBeforeAttributes = false;
        }
        if (lines.isEmpty()) {
            return;
        }
        int n = 9;
        Objects.requireNonNull(client.field_1772);
        int lineHeight = 9 + 3;
        int padding = 6;
        int width = 0;
        int height = padding * 2 - 1;
        for (NpcAttributeTooltipLine line : lines) {
            int lineWidth = client.field_1772.method_1727(line.text());
            if (line.iconTexture() != null) {
                lineWidth += n + 4;
            }
            width = Math.max(width, lineWidth);
            height += lineHeight + (line.gapBefore() ? 3 : 0);
        }
        int x = class_3532.method_15340((int)((int)scaledMouseX + 12), (int)8, (int)(client.method_22683().method_4486() - (width += padding * 2) - 8));
        int y = class_3532.method_15340((int)((int)scaledMouseY - height - 8), (int)8, (int)(client.method_22683().method_4502() - height - 8));
        context.method_51448().method_22903();
        context.method_51448().method_46416(0.0f, 0.0f, 620.0f);
        context.method_25294(x, y, x + width, y + height, -267386864);
        context.method_49601(x, y, width, height, -11513776);
        int lineY = y + padding;
        for (NpcAttributeTooltipLine line : lines) {
            if (line.gapBefore()) {
                lineY += 3;
            }
            int lineX = x + padding;
            if (line.iconTexture() != null) {
                context.method_25291(line.iconTexture(), lineX, lineY - 1, 0, 0.0f, 0.0f, n, n, n, n);
                lineX += n + 4;
            }
            context.method_27535(client.field_1772, (class_2561)class_2561.method_43470((String)line.text()), lineX, lineY, line.color());
            lineY += lineHeight;
        }
        context.method_51448().method_22909();
    }

    private static void drawBubbleSection(class_332 context, class_2960 texture, int x, int y, int sourceWidth, int sourceHeight, int drawWidth, int drawHeight) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905((float)drawWidth / (float)sourceWidth, (float)drawHeight / (float)sourceHeight, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, sourceWidth, sourceHeight, sourceWidth, sourceHeight);
        context.method_51448().method_22909();
    }

    private static DialogueLayout getLayout(class_310 client) {
        List<DialogueOption> options = DialogueSceneClientState.getOptions(client);
        int maxOptionWidth = 64;
        for (DialogueOption option : options) {
            if (!option.wide()) continue;
            maxOptionWidth = Math.max(maxOptionWidth, 96);
        }
        float progress = DialogueSceneClientState.getTransitionProgress();
        int baseX = client.method_22683().method_4486() - 28 - maxOptionWidth;
        int totalHeight = 16 * options.size() + 4 * Math.max(0, options.size() - 1);
        int optionsX = baseX + Math.round((1.0f - progress) * 12.0f);
        int optionsY = client.method_22683().method_4502() - 56 - totalHeight + -16 + 2;
        List<BubbleRenderLine> lines = DialogueSceneClientState.getAllBubbleLines(client);
        int bubbleHeight = DialogueSceneClientState.getBubbleHeight(client, lines);
        int bubbleX = (client.method_22683().method_4486() - 256) / 2 + -16;
        int bubbleY = client.method_22683().method_4502() - 38 - bubbleHeight;
        return new DialogueLayout(0, 0, optionsX, optionsY, bubbleX, bubbleY, maxOptionWidth);
    }

    private static List<DialogueOption> getOptions(class_310 client) {
        boolean unlocked;
        ArrayList<DialogueOption> options = new ArrayList<DialogueOption>();
        if (DialogueSceneClientState.isHireDialogue(client)) {
            options.add(new DialogueOption("Hire", DialogueOptionAction.HIRE, DialogueSceneClientState.canHireDisplayedTroop(client), true, null, false));
            options.add(new DialogueOption("Exit", DialogueOptionAction.EXIT, true, true, null, false));
            return options;
        }
        if (currentPage == DialoguePage.VOYAGE_SELECT) {
            VoyagePageState voyageState = DialogueSceneClientState.getVoyagePageState(client);
            if (voyageState.selection() != null) {
                options.add(new DialogueOption(voyageState.selection().shipType().displayName(), DialogueOptionAction.NONE, false, false, voyageState.selection().shipType().iconTexture(), false));
                boolean canAfford = DialogueSceneClientState.canAffordVoyageSelection(client, voyageState.selection());
                String destinationLabel = voyageState.selection().castle().name();
                options.add(new DialogueOption(destinationLabel, DialogueOptionAction.VOYAGE_CONFIRM, canAfford, true, null, DialogueSceneClientState.shouldUseWideOption(destinationLabel)));
            }
            options.add(new DialogueOption("Return", DialogueOptionAction.RETURN, true, true, null, false));
            return options;
        }
        if (currentPage == DialoguePage.BLACKSMITH_UPGRADE_SELECT) {
            BlacksmithUpgradePageState upgradeState = DialogueSceneClientState.getBlacksmithUpgradePageState(client);
            if (upgradeState.selection() != null) {
                options.add(new DialogueOption(upgradeState.selection().source().displayName(), DialogueOptionAction.NONE, false, false, null, true));
                options.add(new DialogueOption(upgradeState.selection().target().displayName(), DialogueOptionAction.NONE, false, false, null, true));
                options.add(new DialogueOption("Confirm", DialogueOptionAction.BLACKSMITH_UPGRADE_CONFIRM, DialogueSceneClientState.canAffordBlacksmithUpgradeSelection(client, upgradeState.selection()), true, null, false));
            } else {
                options.add(new DialogueOption("No Troops Ready", DialogueOptionAction.NONE, false, true, null, true));
                options.add(new DialogueOption("No Targets", DialogueOptionAction.NONE, false, true, null, true));
                options.add(new DialogueOption("Confirm", DialogueOptionAction.BLACKSMITH_UPGRADE_CONFIRM, false, true, null, false));
            }
            options.add(new DialogueOption("Back", DialogueOptionAction.RETURN, true, true, null, false));
            return options;
        }
        if (currentPage == DialoguePage.CHAT_SELECT) {
            options.add(new DialogueOption("Talk", DialogueOptionAction.CHAT_TALK, true, true, null, false));
            options.add(new DialogueOption("Intimidate", DialogueOptionAction.CHAT_INTIMIDATE, true, true, null, true));
            if (DialogueSceneClientState.isChildNpc()) {
                options.add(new DialogueOption("Compliment", DialogueOptionAction.CHAT_COMPLIMENT, true, true, null, false));
            } else {
                options.add(new DialogueOption("Flirt", DialogueOptionAction.CHAT_FLIRT, DialogueSceneClientState.canFlirtWithNpc(), true, null, false));
            }
            if (DialogueSceneClientState.canTryForBabyCurrentNpc()) {
                long remainingDays = DialogueSceneClientState.remainingTryForBabyDays(client);
                Object label = remainingDays > 0L ? "Try for Baby (" + remainingDays + "d)" : "Try for Baby";
                options.add(new DialogueOption((String)label, DialogueOptionAction.CHAT_TRY_FOR_BABY, remainingDays <= 0L, true, null, true));
            }
            if (DialogueSceneClientState.canMarryCurrentNpc()) {
                options.add(new DialogueOption("Marry", DialogueOptionAction.CHAT_MARRY, true, true, null, false));
            }
            if (DialogueSceneClientState.canDivorceCurrentNpc()) {
                options.add(new DialogueOption("Divorce", DialogueOptionAction.CHAT_DIVORCE, true, true, null, true));
            }
            if (DialogueSceneClientState.canTakeToPartyCurrentNpc()) {
                options.add(new DialogueOption("Take to Party", DialogueOptionAction.CHAT_TAKE_TO_PARTY, true, true, null, true));
            }
            if (DialogueSceneClientState.canAskToLeaveCurrentNpc()) {
                options.add(new DialogueOption("Ask to Leave", DialogueOptionAction.CHAT_ASK_TO_LEAVE, true, true, null, true));
            }
            options.add(new DialogueOption("Return", DialogueOptionAction.RETURN, true, true, null, false));
            return options;
        }
        if (currentPage == DialoguePage.CHAT_RESULT) {
            return options;
        }
        if (currentPage == DialoguePage.TOURNEY_SELECT) {
            options.add(new DialogueOption("Easy", DialogueOptionAction.TOURNEY_EASY, true, true, null, false, DialogueSceneClientState.buildTourneyTooltip("1 opponent, iron weapon, full iron armor, 20 health.")));
            options.add(new DialogueOption("Medium", DialogueOptionAction.TOURNEY_MEDIUM, true, true, null, true, DialogueSceneClientState.buildTourneyTooltip("2 opponents, steel weapons, matching armor, 30 health.")));
            options.add(new DialogueOption("Hard", DialogueOptionAction.TOURNEY_HARD, true, true, null, false, DialogueSceneClientState.buildTourneyTooltip("4 opponents, steel swords, Kingsguard armor, 35 health.")));
            options.add(new DialogueOption("Return", DialogueOptionAction.RETURN, true, true, null, false));
            return options;
        }
        if (currentPage == DialoguePage.LOCKPICKING_CAUGHT) {
            options.add(new DialogueOption("I'm lost", DialogueOptionAction.LOCKPICKING_LOST, true, true, SkillAttributeType.CHARISMA.getIconTexture(), true));
            options.add(new DialogueOption("Intimidate", DialogueOptionAction.LOCKPICKING_INTIMIDATE, true, true, SkillAttributeType.INTELLIGENCE.getIconTexture(), true));
            options.add(new DialogueOption("Lie", DialogueOptionAction.LOCKPICKING_LIE, true, true, SkillAttributeType.LUCK.getIconTexture(), false));
            return options;
        }
        if (currentPage == DialoguePage.GATE_WARNING) {
            options.add(new DialogueOption("Walk away", DialogueOptionAction.GATE_WALK_AWAY, true, true, null, true));
            boolean canBribe = GotEconomy.hasLegacyGoldIngots((class_1657)client.field_1724, 8);
            options.add(new DialogueOption("Bribe", DialogueOptionAction.GATE_BRIBE, canBribe, true, null, false, canBribe ? null : DialogueSceneClientState.buildGoldCostTooltip(8)));
            return options;
        }
        if (currentPage == DialoguePage.QUEST_OFFER) {
            if (DialogueSceneClientState.isValyrianQuestOffer(client)) {
                if (DialogueSceneClientState.isOriginTargaryen()) {
                    options.add(new DialogueOption("I'm a Targaryen", DialogueOptionAction.VALYRIAN_TARGARYEN_CLAIM, true, true, SkillAttributeType.CHARISMA.getIconTexture(), true, DialogueSceneClientState.buildValyrianClaimTooltip()));
                } else {
                    options.add(new DialogueOption("Accept", DialogueOptionAction.QUEST_ACCEPT, true, true, null, false));
                }
                options.add(new DialogueOption("Reject", DialogueOptionAction.QUEST_REJECT, true, true, null, false));
                return options;
            }
            if (DialogueSceneClientState.shouldShowQuestOption(client)) {
                boolean canNegotiate = DialogueSceneClientState.hasNegotiationUnlocked();
                options.add(new DialogueOption(canNegotiate ? "Negotiate" : "Locked", DialogueOptionAction.QUEST_NEGOTIATE, canNegotiate, true, null, false));
            }
            options.add(new DialogueOption("Accept", DialogueOptionAction.QUEST_ACCEPT, true, true, null, false));
            options.add(new DialogueOption("Reject", DialogueOptionAction.QUEST_REJECT, true, true, null, false));
            return options;
        }
        if (currentPage == DialoguePage.ROBBER_AMBUSH) {
            options.add(new DialogueOption("Accept", DialogueOptionAction.ROBBER_ACCEPT, true, true, null, false));
            options.add(new DialogueOption("Reject", DialogueOptionAction.ROBBER_REJECT, true, true, null, false));
            return options;
        }
        if (currentPage == DialoguePage.QUEST_BANDIT_CAMP) {
            options.add(new DialogueOption("Attack", DialogueOptionAction.BANDIT_ATTACK, true, true, null, false));
            options.add(new DialogueOption("Negotiate", DialogueOptionAction.BANDIT_NEGOTIATE, true, true, null, false));
            return options;
        }
        if (currentPage == DialoguePage.QUEST_BANDIT_CAMP_RESULT) {
            options.add(new DialogueOption("Exit", DialogueOptionAction.EXIT, true, true, null, false));
            return options;
        }
        if (DialogueSceneClientState.isPendingSettler(client)) {
            options.add(new DialogueOption("Accept", DialogueOptionAction.SETTLER_ACCEPT, true, true, null, false));
            options.add(new DialogueOption("Reject", DialogueOptionAction.SETTLER_REJECT, true, true, null, false));
            options.add(new DialogueOption("Exit", DialogueOptionAction.EXIT, true, true, null, false));
            return options;
        }
        if (currentPage == DialoguePage.PLEDGE_PATH) {
            for (ReputationPledgeOption option : DialogueSceneClientState.visiblePledgeOptions(client)) {
                options.add(new DialogueOption(option.label(), DialogueOptionAction.PLEDGE_ROUTE, true, true, null, option.label().length() > 12, null, option.actionId()));
            }
            options.add(new DialogueOption("Return", DialogueOptionAction.RETURN, true, true, null, false));
            return options;
        }
        if (currentPage == DialoguePage.PLEDGE_REWARD_SELECT) {
            options.add(new DialogueOption("Lord's Sigil", DialogueOptionAction.PLEDGE_COMPLETE_LORD_SIGIL, true, true, null, true));
            options.add(new DialogueOption("Create Sigil", DialogueOptionAction.PLEDGE_CREATE_SIGIL, true, true, null, true));
            options.add(new DialogueOption("Return", DialogueOptionAction.RETURN, true, true, null, false));
            return options;
        }
        if (currentPage == DialoguePage.REVOKE_PLEDGE_CONFIRM) {
            options.add(new DialogueOption("Confirm", DialogueOptionAction.REVOKE_PLEDGE_CONFIRM, true, true, null, false));
            options.add(new DialogueOption("Return", DialogueOptionAction.RETURN, true, true, null, false));
            return options;
        }
        options.add(new DialogueOption("Chat", DialogueOptionAction.CHAT_OPEN, true, true, null, false));
        if (DialogueSceneClientState.shouldShowTitleChallengeOption(client)) {
            unlocked = currentTitleChallengeAvailable && currentTitleChallengeCooldownSeconds <= 0;
            options.add(new DialogueOption("Challenge for Title", DialogueOptionAction.CHAT_CHALLENGE_FOR_TITLE, unlocked, true, null, true, unlocked ? null : DialogueSceneClientState.buildTitleChallengeCooldownTooltip()));
        }
        if (DialogueSceneClientState.shouldShowTourneyOption(client)) {
            unlocked = currentTourneyAvailable && !currentTourneyAttended;
            options.add(new DialogueOption("Attend Tourney", DialogueOptionAction.TOURNEY_OPEN, unlocked, true, null, true, unlocked ? null : DialogueSceneClientState.buildTourneyLockedTooltip()));
        }
        if (DialogueSceneClientState.shouldShowValyrianConfirmOption(client)) {
            options.add(new DialogueOption("Confirm Identity", DialogueOptionAction.VALYRIAN_CONFIRM_IDENTITY, true, true, null, true));
        } else if (DialogueSceneClientState.shouldShowValyrianDeliverNewsOption(client)) {
            options.add(new DialogueOption("Deliver News", DialogueOptionAction.VALYRIAN_DELIVER_NEWS, true, true, null, false));
        } else if (DialogueSceneClientState.shouldShowDeliverOption(client)) {
            options.add(new DialogueOption("Deliver", DialogueOptionAction.QUEST_DELIVER, true, true, null, false));
        } else if (DialogueSceneClientState.shouldShowQuestOption(client)) {
            options.add(new DialogueOption("Quest", DialogueOptionAction.QUEST, true, true, null, false));
        }
        if (DialogueSceneClientState.shouldShowValyrianDeliverDragonOption(client)) {
            options.add(new DialogueOption("Deliver Dragon", DialogueOptionAction.VALYRIAN_DELIVER_DRAGON, true, true, null, true));
        }
        if (DialogueSceneClientState.shouldShowPledgeOption(client)) {
            unlocked = DialogueSceneClientState.canOpenPledgePage(client);
            options.add(new DialogueOption("Pledge", DialogueOptionAction.PLEDGE, unlocked, true, null, false));
        }
        if (DialogueSceneClientState.shouldShowRevokePledgeOption(client)) {
            options.add(new DialogueOption("Revoke Pledge", DialogueOptionAction.REVOKE_PLEDGE, true, true, null, true));
        }
        if (DialogueSceneClientState.shouldShowKnighthoodOption(client)) {
            options.add(new DialogueOption("Knighthood", DialogueOptionAction.KNIGHTHOOD, true, true, null, false));
        }
        if (DialogueSceneClientState.shouldShowVoyageOption(client)) {
            options.add(new DialogueOption("Voyage", DialogueOptionAction.VOYAGE, true, true, null, false));
        }
        if (DialogueSceneClientState.shouldShowBlacksmithUpgradeOption(client)) {
            options.add(new DialogueOption("Upgrade", DialogueOptionAction.BLACKSMITH_UPGRADE, true, true, null, false));
        }
        if (DialogueSceneClientState.shouldShowVillageTradeOption(client)) {
            options.add(new DialogueOption("Trade", DialogueOptionAction.TRADE, true, true, null, false));
        }
        options.add(new DialogueOption("Exit", DialogueOptionAction.EXIT, true, true, null, false));
        return options;
    }

    private static boolean shouldShowKnighthoodOption(class_310 client) {
        AbstractKingdomNpcEntity npc;
        if (DialogueSceneClientState.shouldShowPledgeOption(client)) {
            return false;
        }
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity) || (npc = (AbstractKingdomNpcEntity)target).getNpcRole() != KingdomNpcRole.LORD) {
            return false;
        }
        return ReputationExperienceService.isKnighthoodAvailable(CharacterSelectionClientState.getReputationProfile(), CharacterSelectionClientState.getSelectedKingdomId());
    }

    private static boolean shouldShowPledgeOption(class_310 client) {
        AbstractKingdomNpcEntity npc;
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity) || (npc = (AbstractKingdomNpcEntity)target).getNpcRole() != KingdomNpcRole.LORD) {
            return false;
        }
        return CharacterSelectionClientState.getReputationTitle() == ReputationTitle.SOLDIER;
    }

    private static boolean shouldShowRevokePledgeOption(class_310 client) {
        boolean isRepledged;
        AbstractKingdomNpcEntity npc;
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity) || (npc = (AbstractKingdomNpcEntity)target).getNpcRole() != KingdomNpcRole.LORD) {
            return false;
        }
        if (CharacterSelectionClientState.getReputationTitle() == ReputationTitle.SOLDIER && CharacterSelectionClientState.getReputationProfile().getCurrentRoute() == ReputationRoute.NONE) {
            return false;
        }
        boolean bl = isRepledged = !CharacterSelectionClientState.getSelectedKingdomId().equals(CharacterSelectionClientState.getOriginalSelectedKingdomId()) || !CharacterSelectionClientState.getSelectedOptionId().equals(CharacterSelectionClientState.getOriginalSelectedOptionId());
        if (!isRepledged) {
            return false;
        }
        CastleMapPayload.Entry castle = DialogueSceneClientState.findCastleEntryForNpc(npc);
        return castle != null && castle.kingdomId().equals(CharacterSelectionClientState.getSelectedKingdomId()) && castle.houseId().equals(CharacterSelectionClientState.getSelectedOptionId());
    }

    private static boolean canOpenPledgePage(class_310 client) {
        if (!DialogueSceneClientState.shouldShowPledgeOption(client)) {
            return false;
        }
        return PledgeQuestLogic.hasReachedSoldierCrossroads(CharacterSelectionClientState.getReputationProfile()) && MainQuestClientState.hasCompletedPledgePrerequisites() && !DialogueSceneClientState.visiblePledgeOptions(client).isEmpty();
    }

    private static List<ReputationPledgeOption> visiblePledgeOptions(class_310 client) {
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) {
            return ReputationProgressionService.availablePledgeOptions(CharacterSelectionClientState.getSelectedKingdomId(), CharacterSelectionClientState.getReputationProfile());
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        CastleMapPayload.Entry castle = DialogueSceneClientState.findCastleEntryForNpc(npc);
        if (castle != null) {
            return ReputationProgressionService.sharedPledgeOptions(CharacterSelectionClientState.getSelectedKingdomId(), castle.kingdomId(), CharacterSelectionClientState.getReputationProfile());
        }
        class_2960 npcKingdomId = npc.getKingdomId();
        return ReputationProgressionService.sharedPledgeOptions(CharacterSelectionClientState.getSelectedKingdomId(), npcKingdomId == null ? "" : npcKingdomId.toString(), CharacterSelectionClientState.getReputationProfile());
    }

    private static boolean shouldShowVoyageOption(class_310 client) {
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) {
            return false;
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        if (npc.isNobleSpouse()) {
            return false;
        }
        return npc.getNpcRole() == KingdomNpcRole.FISHERMAN || npc.getNpcRole() == KingdomNpcRole.CART_RIDER;
    }

    private static boolean shouldShowBlacksmithUpgradeOption(class_310 client) {
        AbstractKingdomNpcEntity npc;
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        return target instanceof AbstractKingdomNpcEntity && !(npc = (AbstractKingdomNpcEntity)target).isNobleSpouse() && npc.getNpcRole() == KingdomNpcRole.BLACKSMITH;
    }

    private static boolean shouldShowVillageTradeOption(class_310 client) {
        AbstractKingdomNpcEntity npc;
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        return target instanceof AbstractKingdomNpcEntity && !(npc = (AbstractKingdomNpcEntity)target).isNobleSpouse() && npc.getNpcRole() == KingdomNpcRole.ELDER;
    }

    private static boolean isPendingSettler(class_310 client) {
        SettlerNpcEntity settler;
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        return target instanceof SettlerNpcEntity && !(settler = (SettlerNpcEntity)target).isIndependentMarriageNpc() && !settler.isAcceptedSettler();
    }

    private static void activateOption(DialogueOption option) {
        class_310 client = class_310.method_1551();
        switch (option.action().ordinal()) {
            case 0: {
                DialogueSceneClientState.setResponse(DialogueResponseType.NEUTRAL, targetEntityId + sceneTicks);
                break;
            }
            case 1: {
                DialogueSceneClientState.openChatPage();
                break;
            }
            case 2: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.TALK);
                break;
            }
            case 3: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.INTIMIDATE);
                break;
            }
            case 4: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.CHALLENGE_FOR_TITLE);
                break;
            }
            case 5: {
                currentPage = DialoguePage.TOURNEY_SELECT;
                activeBubbleContent = BubbleContent.ofText("Choose your challenge.");
                DialogueSceneClientState.resetTypewriterState();
                break;
            }
            case 6: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.TOURNEY_EASY);
                break;
            }
            case 7: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.TOURNEY_MEDIUM);
                break;
            }
            case 8: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.TOURNEY_HARD);
                break;
            }
            case 9: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.COMPLIMENT);
                break;
            }
            case 10: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.FLIRT);
                break;
            }
            case 11: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.TRY_FOR_BABY);
                break;
            }
            case 12: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.MARRY);
                break;
            }
            case 13: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.DIVORCE);
                break;
            }
            case 14: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.TAKE_TO_PARTY);
                break;
            }
            case 15: {
                DialogueSceneClientState.requestNpcChatAction(NpcChatAction.ASK_TO_LEAVE);
                break;
            }
            case 16: {
                DialogueSceneClientState.openQuestOfferPage(client);
                break;
            }
            case 17: {
                DialogueSceneClientState.negotiateQuest();
                break;
            }
            case 18: {
                DialogueSceneClientState.acceptQuest();
                break;
            }
            case 19: {
                DialogueSceneClientState.rejectQuest();
                break;
            }
            case 20: {
                DialogueSceneClientState.deliverQuest();
                break;
            }
            case 21: {
                DialogueSceneClientState.sendValyrianAction(QuestActionType.VALYRIAN_TARGARYEN_CLAIM, DialogueResponseType.POSITIVE);
                break;
            }
            case 22: {
                DialogueSceneClientState.sendValyrianAction(QuestActionType.VALYRIAN_CONFIRM_IDENTITY, DialogueResponseType.POSITIVE);
                break;
            }
            case 23: {
                DialogueSceneClientState.sendValyrianAction(QuestActionType.VALYRIAN_DELIVER_NEWS, DialogueResponseType.POSITIVE);
                break;
            }
            case 24: {
                DialogueSceneClientState.sendValyrianAction(QuestActionType.VALYRIAN_DELIVER_DRAGON, DialogueResponseType.POSITIVE);
                break;
            }
            case 25: {
                DialogueSceneClientState.resolveRobberAmbush(true);
                break;
            }
            case 26: {
                DialogueSceneClientState.resolveRobberAmbush(false);
                break;
            }
            case 27: {
                DialogueSceneClientState.resolveBanditCamp(false);
                break;
            }
            case 28: {
                DialogueSceneClientState.resolveBanditCamp(true);
                break;
            }
            case 29: {
                DialogueSceneClientState.resolveLockpickingCaught("lost");
                break;
            }
            case 30: {
                DialogueSceneClientState.resolveLockpickingCaught("intimidate");
                break;
            }
            case 31: {
                DialogueSceneClientState.resolveLockpickingCaught("lie");
                break;
            }
            case 32: {
                DialogueSceneClientState.resolveGateWarning("walk_away");
                break;
            }
            case 33: {
                DialogueSceneClientState.resolveGateWarning("bribe");
                break;
            }
            case 34: {
                DialogueSceneClientState.openPledgePage(client);
                break;
            }
            case 35: {
                DialogueSceneClientState.choosePledge(QuestActionType.PLEDGE_KNIGHT);
                break;
            }
            case 36: {
                DialogueSceneClientState.choosePledge(QuestActionType.PLEDGE_HEDGE_KNIGHT);
                break;
            }
            case 37: {
                DialogueSceneClientState.choosePledge(QuestActionType.PLEDGE_PRIEST);
                break;
            }
            case 38: {
                DialogueSceneClientState.choosePledge(option.actionData());
                break;
            }
            case 39: {
                DialogueSceneClientState.completePledgeWithLordSigil();
                break;
            }
            case 40: {
                DialogueSceneClientState.openPlayerHouseSigilEditor();
                break;
            }
            case 41: {
                DialogueSceneClientState.openRevokePledgePage(client);
                break;
            }
            case 42: {
                DialogueSceneClientState.confirmRevokePledge();
                break;
            }
            case 43: {
                DialogueSceneClientState.openVoyagePage(client);
                break;
            }
            case 44: {
                DialogueSceneClientState.confirmVoyage(client);
                break;
            }
            case 45: {
                DialogueSceneClientState.openBlacksmithUpgradePage(client);
                break;
            }
            case 46: {
                DialogueSceneClientState.confirmBlacksmithUpgrade(client);
                break;
            }
            case 48: {
                DialogueSceneClientState.returnToRootPage(client);
                break;
            }
            case 53: {
                DialogueSceneClientState.close();
                break;
            }
            case 49: {
                if (targetEntityId < 0) break;
                ClientPlayNetworking.send((class_8710)new RequestKnighthoodPayload(targetEntityId));
                break;
            }
            case 50: {
                if (targetEntityId < 0) break;
                ClientPlayNetworking.send((class_8710)new RequestHireTroopPayload(targetEntityId));
                DialogueSceneClientState.close();
                break;
            }
            case 51: {
                if (targetEntityId < 0) break;
                ClientPlayNetworking.send((class_8710)new RequestSettlerDecisionPayload(targetEntityId, true));
                DialogueSceneClientState.close();
                break;
            }
            case 52: {
                if (targetEntityId < 0) break;
                ClientPlayNetworking.send((class_8710)new RequestSettlerDecisionPayload(targetEntityId, false));
                DialogueSceneClientState.close();
                break;
            }
            case 47: {
                if (targetEntityId < 0) break;
                ClientPlayNetworking.send((class_8710)new RequestVillageTradePayload(targetEntityId));
                DialogueSceneClientState.close();
            }
        }
    }

    @Nullable
    private static class_1297 getTargetEntity(class_310 client) {
        return client.field_1687 == null ? null : client.field_1687.method_8469(targetEntityId);
    }

    private static class_243 getNpcLookPosition(class_1297 entity) {
        return entity.method_19538().method_1031(0.0, Math.min(2.0, (double)entity.method_17682()), 0.0);
    }

    private static float getYawTowards(class_243 from, class_243 to) {
        double dx = to.field_1352 - from.field_1352;
        double dz = to.field_1350 - from.field_1350;
        return (float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0));
    }

    private static float getPitchTowards(class_243 from, class_243 to) {
        double dx = to.field_1352 - from.field_1352;
        double dy = to.field_1351 - from.field_1351;
        double dz = to.field_1350 - from.field_1350;
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        return (float)(-Math.toDegrees(Math.atan2(dy, horizontal)));
    }

    private static float lerpAngle(float from, float to, float progress) {
        return from + class_3532.method_15393((float)(to - from)) * class_3532.method_15363((float)progress, (float)0.0f, (float)1.0f);
    }

    private static float getTransitionProgress() {
        return class_3532.method_15363((float)((float)sceneTicks / 20.0f), (float)0.0f, (float)1.0f);
    }

    private static boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    private static double getScaledMouseX(class_310 client, double rawMouseX) {
        return rawMouseX * (double)client.method_22683().method_4486() / (double)client.method_22683().method_4480();
    }

    private static double getScaledMouseY(class_310 client, double rawMouseY) {
        return rawMouseY * (double)client.method_22683().method_4502() / (double)client.method_22683().method_4507();
    }

    private static double activeDialogueRange() {
        return currentPage == DialoguePage.LOCKPICKING_CAUGHT || currentPage == DialoguePage.GATE_WARNING ? 10.0 : 7.0;
    }

    private static void clear(boolean playerInitiated) {
        class_310 client = class_310.method_1551();
        GotMod.LOGGER.info("Dialogue cleared: targetEntityId={} blockedEntityId={} active={} sceneTicks={} cooldownBeforeSet={} playerInitiated={}", new Object[]{targetEntityId, blockedEntityId, active, sceneTicks, reopenCooldownTicks, playerInitiated});
        if (!serverForcedClose && targetEntityId >= 0 && client.field_1724 != null && client.method_1562() != null) {
            if (playerInitiated && currentPage == DialoguePage.LOCKPICKING_CAUGHT && !lockpickingCaughtResolving) {
                lockpickingCaughtResolving = true;
                ClientPlayNetworking.send((class_8710)new RequestLockpickingExcusePayload(targetEntityId, "escape"));
            }
            if (playerInitiated && currentPage == DialoguePage.GATE_WARNING && !gateWarningResolving) {
                gateWarningResolving = true;
                ClientPlayNetworking.send((class_8710)new RequestGateWarningActionPayload(targetEntityId, "walk_away"));
            }
            ClientPlayNetworking.send((class_8710)new SetNpcDialogueStatePayload(targetEntityId, false));
        }
        active = false;
        targetEntityId = -1;
        sceneTicks = 0;
        currentPage = DialoguePage.ROOT;
        currentVoyageIndex = 0;
        currentVoyageShipIndex = 0;
        currentUpgradeTroopIndex = 0;
        currentUpgradeTargetIndex = 0;
        currentNpcRelationId = "";
        currentNpcRelationValue = 0;
        currentNpcKingdomId = "";
        currentDialogueContextId = "";
        currentDialogueSubjectName = "";
        currentNpcAttributes = NpcAttributeProfile.EMPTY;
        currentTitleChallengeAvailable = false;
        currentTitleChallengeCooldownSeconds = 0;
        currentTourneyAvailable = false;
        currentTourneyAttended = false;
        lockpickingCaughtResolving = false;
        gateWarningResolving = false;
        serverForcedClose = false;
        activeBubbleContent = BubbleContent.ofText("");
        responseStartedAtTick = 0;
        lastTypedCharacterCount = 0;
        nextTypingSoundTick = 0;
        lastTypingSoundIndex = -1;
        pendingAutoCloseTick = -1;
        if (cursorUnlockedByDialogue && client.field_1755 == null) {
            client.field_1729.method_1612();
        }
        cursorUnlockedByDialogue = false;
        reopenCooldownTicks = 12;
    }

    private static void setOpeningResponse(class_1297 entity) {
        WildlingRaiderNpcEntity raider;
        RobberNpcEntity robber;
        SettlerNpcEntity settlerNpc;
        if (entity instanceof HireableUnitEntity) {
            HireableUnitEntity hireableUnit = (HireableUnitEntity)entity;
            activeBubbleContent = DialogueSceneClientState.buildHireBubbleContent(hireableUnit);
            DialogueSceneClientState.resetTypewriterState();
            return;
        }
        if (entity instanceof SettlerNpcEntity && !(settlerNpc = (SettlerNpcEntity)entity).isIndependentMarriageNpc()) {
            activeBubbleContent = DialogueSceneClientState.buildSettlerBubbleContent(settlerNpc);
            DialogueSceneClientState.resetTypewriterState();
            return;
        }
        if (entity instanceof RobberNpcEntity && (robber = (RobberNpcEntity)entity).isAmbushLeader() && robber.isAwaitingAmbushDecision()) {
            currentPage = DialoguePage.ROBBER_AMBUSH;
            activeBubbleContent = BubbleContent.ofText("I'll be taking that letter for " + robber.getTargetLordName() + ". Let us not make a mess alright? Just hand over the letter and we will be on our way");
            DialogueSceneClientState.resetTypewriterState();
            return;
        }
        if (entity instanceof RobberNpcEntity && (robber = (RobberNpcEntity)entity).isQuestCampLeader() && robber.isAwaitingQuestCampDecision()) {
            currentPage = DialoguePage.QUEST_BANDIT_CAMP;
            activeBubbleContent = BubbleContent.ofText("Who goes there and what do you want?");
            DialogueSceneClientState.resetTypewriterState();
            return;
        }
        if (entity instanceof WildlingRaiderNpcEntity && (raider = (WildlingRaiderNpcEntity)entity).isQuestCampLeader() && raider.isAwaitingQuestCampDecision()) {
            currentPage = DialoguePage.QUEST_BANDIT_CAMP;
            activeBubbleContent = BubbleContent.ofText("You are far from your wall, crow. Speak fast.");
            DialogueSceneClientState.resetTypewriterState();
            return;
        }
        DialogueSceneClientState.setResponse(DialogueResponseType.NEUTRAL, entity.method_5628());
    }

    private static void setResponse(DialogueResponseType type, long seed) {
        DialogueSceneClientState.showResponse(type, seed, false);
    }

    private static void showResponse(DialogueResponseType type, long seed, boolean autoClose) {
        activeBubbleContent = BubbleContent.ofText(DialogueResponseRegistry.pick(type, currentNpcKingdomId, seed, currentDialogueContextId, currentDialogueSubjectName));
        DialogueSceneClientState.resetTypewriterState();
        pendingAutoCloseTick = autoClose ? responseStartedAtTick + activeBubbleContent.visibleCharacterCount() * 1 + 16 : -1;
    }

    private static DialogueResponseType responseTypeForRelation(int relation) {
        NpcRelationTier tier = NpcRelationTier.fromValue(relation);
        return switch (tier) {
            default -> throw new MatchException(null, null);
            case NpcRelationTier.HOSTILE, NpcRelationTier.UNFRIENDLY -> DialogueResponseType.NEGATIVE;
            case NpcRelationTier.FRIENDLY, NpcRelationTier.TRUSTED -> DialogueResponseType.POSITIVE;
            case NpcRelationTier.NEUTRAL -> DialogueResponseType.NEUTRAL;
        };
    }

    private static boolean shouldUseRelationOpening(class_1297 entity) {
        return entity instanceof AbstractKingdomNpcEntity && !(entity instanceof HireableUnitEntity) && !(entity instanceof SettlerNpcEntity) && !(entity instanceof RobberNpcEntity) && !(entity instanceof WildlingRaiderNpcEntity);
    }

    private static BubbleContent buildHireBubbleContent(HireableUnitEntity hireableUnit) {
        DynamicTroopDefinition definition = hireableUnit.getTroopDefinition();
        if (definition == null) {
            return BubbleContent.ofText("Looking for some extra swords?");
        }
        class_1792 costItem = GotTroops.resolveHireCostItem(definition);
        int costAmount = GotTroops.resolveHireCostAmount(definition);
        String[] variations = new String[]{"Looking for some extra swords?", "Need another blade in your ranks?", "Coin buys loyalty for the day."};
        String opening = variations[Math.floorMod(hireableUnit.method_5628(), variations.length)];
        return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text(opening)), BubbleLogicalLine.of(BubbleToken.text("It will cost you "), BubbleToken.item(costItem), BubbleToken.text(" " + costAmount))));
    }

    private static BubbleContent buildSettlerBubbleContent(SettlerNpcEntity settlerNpc) {
        String profession = settlerNpc.getProfession().getDisplayName();
        String skillTier = settlerNpc.getSkillTier().getDisplayName();
        String origin = settlerNpc.getCharacterOption() != null && !settlerNpc.getCharacterOption().houseDisplayName().isBlank() ? settlerNpc.getCharacterOption().houseDisplayName() : settlerNpc.getKingdomId().method_12832();
        String opening = settlerNpc.isAcceptedSettler() ? "I serve this settlement now." : "I could make a home here, if you'll have me.";
        return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text(opening)), BubbleLogicalLine.of(BubbleToken.text(skillTier + " " + profession + " from " + origin)), BubbleLogicalLine.of(BubbleToken.text(settlerNpc.isAcceptedSettler() ? "My work is already sworn." : "Will you accept me into this settlement?"))));
    }

    private static boolean shouldShowQuestOption(class_310 client) {
        AbstractKingdomNpcEntity npc;
        block8: {
            block7: {
                class_1297 target = DialogueSceneClientState.getTargetEntity(client);
                if (!(target instanceof AbstractKingdomNpcEntity)) break block7;
                npc = (AbstractKingdomNpcEntity)target;
                if (client.field_1724 != null && client.field_1687 != null) break block8;
            }
            return false;
        }
        if (DialogueSceneClientState.shouldShowValyrianQuestOption(client)) {
            return true;
        }
        if (QuestJournalClientState.isTemplateNpcOnCooldown(npc.method_5667(), "lord_letter_delivery", client.field_1687.method_8510())) {
            return false;
        }
        QuestJournalStatePayload.Entry activeQuest = QuestJournalClientState.findCompletableByNpc(npc, client.field_1724);
        if (activeQuest != null) {
            return false;
        }
        if (QuestJournalClientState.isNpcOnCooldown(npc.method_5667(), client.field_1687.method_8510())) {
            return false;
        }
        QuestInstance preview = QuestTemplateRegistry.previewForPlayer(client.field_1724.method_5667(), npc, QuestJournalClientState.getOfferCycle(), client.field_1687.method_8510());
        return preview != null && !QuestJournalClientState.hasCompletedOffer(preview.offerKey());
    }

    private static boolean shouldShowDeliverOption(class_310 client) {
        AbstractKingdomNpcEntity npc;
        block5: {
            block4: {
                class_1297 target = DialogueSceneClientState.getTargetEntity(client);
                if (!(target instanceof AbstractKingdomNpcEntity)) break block4;
                npc = (AbstractKingdomNpcEntity)target;
                if (client.field_1724 != null) break block5;
            }
            return false;
        }
        QuestJournalStatePayload.Entry activeQuest = QuestJournalClientState.findCompletableByNpc(npc, client.field_1724);
        if (activeQuest != null && "valyrian_rumor".equals(activeQuest.templateId())) {
            return false;
        }
        return activeQuest != null && QuestJournalClientState.canCompleteQuest(client.field_1724, activeQuest);
    }

    private static boolean shouldShowValyrianQuestOption(class_310 client) {
        AbstractKingdomNpcEntity npc;
        block6: {
            block5: {
                class_1297 target = DialogueSceneClientState.getTargetEntity(client);
                if (!(target instanceof AbstractKingdomNpcEntity)) break block5;
                npc = (AbstractKingdomNpcEntity)target;
                if (client.field_1724 != null) break block6;
            }
            return false;
        }
        if (!DialogueSceneClientState.isClientPentosCastleLord(npc) || CharacterSelectionClientState.getReputationTitle().getTier() < ReputationTitle.KNIGHT.getTier()) {
            return false;
        }
        if (QuestJournalClientState.getActiveQuests().stream().anyMatch(quest -> "valyrian_rumor".equals(quest.templateId()))) {
            return false;
        }
        return !QuestJournalClientState.hasCompletedOffer(ValyrianRumorQuestLogic.completedOfferKey(client.field_1724.method_5667()));
    }

    private static boolean isValyrianQuestOffer(class_310 client) {
        return currentPage == DialoguePage.QUEST_OFFER && DialogueSceneClientState.shouldShowValyrianQuestOption(client);
    }

    private static boolean shouldShowValyrianConfirmOption(class_310 client) {
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) {
            return false;
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        QuestJournalStatePayload.Entry quest = DialogueSceneClientState.findValyrianQuest();
        return quest != null && "confirm_identity".equals(quest.objectiveActionId()) && DialogueSceneClientState.isClientTargaryenCastleLord(npc);
    }

    private static boolean shouldShowValyrianDeliverNewsOption(class_310 client) {
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) {
            return false;
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        QuestJournalStatePayload.Entry quest = DialogueSceneClientState.findValyrianQuest();
        return quest != null && "return_to_giver".equals(quest.objectiveActionId()) && quest.giverNpcUuid().equals(npc.method_5667().toString());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean shouldShowValyrianDeliverDragonOption(class_310 client) {
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) return false;
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        if (client.field_1724 == null) return false;
        if (!DialogueSceneClientState.isClientTargaryenCastleLord(npc)) return false;
        if (!QuestJournalClientState.hasCompletedOffer(ValyrianRumorQuestLogic.completedOfferKey(client.field_1724.method_5667()))) return false;
        if (!DialogueSceneClientState.hasInventoryItem(client.field_1724, GotBlocks.DRAGON_EGG.method_8389())) return false;
        if (!DialogueSceneClientState.hasInventoryItem(client.field_1724, GotBlocks.DRAGON_CRADLE.method_8389())) return false;
        return true;
    }

    private static QuestJournalStatePayload.Entry findValyrianQuest() {
        for (QuestJournalStatePayload.Entry quest : QuestJournalClientState.getActiveQuests()) {
            if (!"valyrian_rumor".equals(quest.templateId())) continue;
            return quest;
        }
        return null;
    }

    private static boolean isClientPentosCastleLord(AbstractKingdomNpcEntity npc) {
        return DialogueSceneClientState.isClientKingdomCastleLord(npc, "agob:pentos", "");
    }

    private static boolean isClientTargaryenCastleLord(AbstractKingdomNpcEntity npc) {
        return DialogueSceneClientState.isClientKingdomCastleLord(npc, "agob:targaryen", "targaryen_targaryen");
    }

    private static boolean isClientKingdomCastleLord(AbstractKingdomNpcEntity npc, String kingdomId, String houseId) {
        if (npc.getNpcRole() != KingdomNpcRole.LORD || npc.getKingdomId() == null || !kingdomId.equals(npc.getKingdomId().toString())) {
            return false;
        }
        for (CastleMapPayload.Entry castle : CastleMapClientState.getCastles()) {
            if (!castle.id().equals(npc.got$getCastle())) continue;
            return kingdomId.equals(castle.kingdomId()) && (houseId.isBlank() || houseId.equals(castle.houseId()));
        }
        return false;
    }

    private static boolean isOriginTargaryen() {
        return "agob:targaryen".equals(CharacterSelectionClientState.getOriginalSelectedKingdomId()) && "targaryen_targaryen".equals(CharacterSelectionClientState.getOriginalSelectedOptionId());
    }

    private static boolean hasInventoryItem(class_746 player, class_1792 item) {
        for (int slot = 0; slot < player.method_31548().method_5439(); ++slot) {
            if (!player.method_31548().method_5438(slot).method_31574(item)) continue;
            return true;
        }
        return false;
    }

    private static int inventoryCount(class_310 client, class_1792 item) {
        if (client.field_1724 == null) {
            return 0;
        }
        int count = 0;
        for (int slot = 0; slot < client.field_1724.method_31548().method_5439(); ++slot) {
            class_1799 stack = client.field_1724.method_31548().method_5438(slot);
            if (!stack.method_31574(item)) continue;
            count += stack.method_7947();
        }
        return count;
    }

    private static boolean hasNegotiationUnlocked() {
        return CharacterSelectionClientState.getSkillPoints(SkillAttributeType.CHARISMA) >= 50;
    }

    private static void openQuestOfferPage(class_310 client) {
        if (client == null || client.field_1724 == null || client.field_1687 == null || targetEntityId < 0) {
            return;
        }
        if (!DialogueSceneClientState.shouldShowQuestOption(client)) {
            return;
        }
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) {
            return;
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        if (QuestJournalClientState.getActiveQuests().size() >= 3) {
            client.field_1724.method_7353((class_2561)class_2561.method_43470((String)"Cannot take on more quests!").method_27692(class_124.field_1061), true);
            DialogueSceneClientState.close();
            return;
        }
        currentPage = DialoguePage.QUEST_OFFER;
        activeBubbleContent = DialogueSceneClientState.buildQuestBubbleContent(client, npc, null);
        DialogueSceneClientState.resetTypewriterState();
    }

    private static void openChatPage() {
        currentPage = DialoguePage.CHAT_SELECT;
        DialogueSceneClientState.updateBubbleContent(BubbleContent.ofText("What would you say?"), true);
        pendingAutoCloseTick = -1;
    }

    private static void openVoyagePage(class_310 client) {
        if (client == null) {
            return;
        }
        currentPage = DialoguePage.VOYAGE_SELECT;
        DialogueSceneClientState.refreshVoyageBubble(client);
    }

    private static void openBlacksmithUpgradePage(class_310 client) {
        if (client == null) {
            return;
        }
        currentPage = DialoguePage.BLACKSMITH_UPGRADE_SELECT;
        currentUpgradeTroopIndex = 0;
        currentUpgradeTargetIndex = 0;
        DialogueSceneClientState.refreshBlacksmithUpgradeBubble(client);
    }

    private static void openPledgePage(class_310 client) {
        if (client == null || !DialogueSceneClientState.canOpenPledgePage(client)) {
            return;
        }
        currentPage = DialoguePage.PLEDGE_PATH;
        DialogueSceneClientState.updateBubbleContent(BubbleContent.ofText("You may pledge to my banner. But for what will you fight? For glory, for your faith, or for the people?"), false);
    }

    private static void openRevokePledgePage(class_310 client) {
        if (client == null || !DialogueSceneClientState.shouldShowRevokePledgeOption(client)) {
            return;
        }
        currentPage = DialoguePage.REVOKE_PLEDGE_CONFIRM;
        DialogueSceneClientState.updateBubbleContent(BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text("If you revoke your pledge, you will return to your original kingdom and house.")), BubbleLogicalLine.of(BubbleToken.text("Your relations, castles, settlements, quests, and troops will be reset.")), BubbleLogicalLine.of(BubbleToken.text("You keep your inventory and skills, and return to Soldier ready to pledge again.")))), false);
    }

    private static void openPledgeRewardPage() {
        currentPage = DialoguePage.PLEDGE_REWARD_SELECT;
        DialogueSceneClientState.updateBubbleContent(BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text("You have done as asked and earned your place beneath my banner.")), BubbleLogicalLine.of(BubbleToken.text("Will you continue under my sigil, or raise a house of your own?")))), false);
    }

    private static void returnToRootPage(class_310 client) {
        currentPage = DialoguePage.ROOT;
        currentUpgradeTroopIndex = 0;
        currentUpgradeTargetIndex = 0;
        pendingAutoCloseTick = -1;
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (target != null) {
            DialogueSceneClientState.setOpeningResponse(target);
            return;
        }
        DialogueSceneClientState.setResponse(DialogueResponseType.NEUTRAL, (long)(targetEntityId + sceneTicks) + 91L);
    }

    private static void resolveRobberAmbush(boolean accept) {
        if (targetEntityId >= 0) {
            ClientPlayNetworking.send((class_8710)new RequestRobberAmbushDecisionPayload(targetEntityId, accept));
        }
        DialogueSceneClientState.close();
    }

    private static void resolveBanditCamp(boolean negotiate) {
        if (targetEntityId < 0) {
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestBanditCampDecisionPayload(targetEntityId, negotiate));
        if (!negotiate) {
            DialogueSceneClientState.close();
        }
    }

    private static void resolveLockpickingCaught(String optionId) {
        if (targetEntityId < 0) {
            return;
        }
        lockpickingCaughtResolving = true;
        ClientPlayNetworking.send((class_8710)new RequestLockpickingExcusePayload(targetEntityId, optionId));
        DialogueSceneClientState.close();
    }

    private static void resolveGateWarning(String actionId) {
        if (targetEntityId < 0) {
            return;
        }
        gateWarningResolving = true;
        ClientPlayNetworking.send((class_8710)new RequestGateWarningActionPayload(targetEntityId, actionId));
        DialogueSceneClientState.close();
    }

    private static void cycleVoyageChoice(class_310 client, int direction) {
        VoyagePageState voyageState = DialogueSceneClientState.getVoyagePageState(client);
        if (!voyageState.canCycleDestination()) {
            return;
        }
        currentVoyageIndex = Math.floorMod(currentVoyageIndex + direction, voyageState.destinations().size());
        DialogueSceneClientState.refreshVoyageBubble(client);
    }

    private static void cycleVoyageShipType(class_310 client, int direction) {
        VoyagePageState voyageState = DialogueSceneClientState.getVoyagePageState(client);
        if (!voyageState.canCycleShipType()) {
            return;
        }
        currentVoyageShipIndex = Math.floorMod(currentVoyageShipIndex + direction, voyageState.shipTypes().size());
        DialogueSceneClientState.refreshVoyageBubble(client);
    }

    private static void cycleBlacksmithUpgradeTroop(class_310 client, int direction) {
        BlacksmithUpgradePageState upgradeState = DialogueSceneClientState.getBlacksmithUpgradePageState(client);
        if (!upgradeState.canCycleTroops()) {
            return;
        }
        currentUpgradeTroopIndex = Math.floorMod(currentUpgradeTroopIndex + direction, upgradeState.sourceTroops().size());
        currentUpgradeTargetIndex = 0;
        DialogueSceneClientState.refreshBlacksmithUpgradeBubble(client);
    }

    private static void cycleBlacksmithUpgradeTarget(class_310 client, int direction) {
        BlacksmithUpgradePageState upgradeState = DialogueSceneClientState.getBlacksmithUpgradePageState(client);
        if (!upgradeState.canCycleTargets()) {
            return;
        }
        currentUpgradeTargetIndex = Math.floorMod(currentUpgradeTargetIndex + direction, upgradeState.targets().size());
        DialogueSceneClientState.refreshBlacksmithUpgradeBubble(client);
    }

    private static void confirmVoyage(class_310 client) {
        VoyagePageState voyageState = DialogueSceneClientState.getVoyagePageState(client);
        VoyageSelection selection = voyageState.selection();
        if (selection == null || targetEntityId < 0) {
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestVoyagePayload(targetEntityId, selection.castle().id(), selection.shipType().id()));
        DialogueSceneClientState.close();
    }

    private static void confirmBlacksmithUpgrade(class_310 client) {
        BlacksmithUpgradePageState upgradeState = DialogueSceneClientState.getBlacksmithUpgradePageState(client);
        BlacksmithUpgradeSelection selection = upgradeState.selection();
        if (selection == null || targetEntityId < 0 || !DialogueSceneClientState.canAffordBlacksmithUpgradeSelection(client, selection)) {
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestBlacksmithTroopUpgradePayload(targetEntityId, selection.source().id().toString(), selection.target().id().toString()));
    }

    private static void acceptQuest() {
        if (targetEntityId < 0) {
            return;
        }
        class_310 client = class_310.method_1551();
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (target instanceof AbstractKingdomNpcEntity) {
            AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
            QuestDebugLogBuffer.record("quest_accept", "client_click_accept", "target_entity_id=" + targetEntityId + ",npc=" + npc.method_5476().getString() + ",npc_uuid=" + npc.method_5845() + ",npc_role=" + npc.getNpcRole().name() + ",npc_castle=" + npc.got$getCastle());
        }
        ClientPlayNetworking.send((class_8710)new RequestQuestActionPayload(targetEntityId, QuestActionType.ACCEPT));
        currentPage = DialoguePage.ROOT;
        DialogueSceneClientState.setResponse(DialogueResponseType.POSITIVE, targetEntityId + sceneTicks);
    }

    private static void negotiateQuest() {
        if (targetEntityId < 0) {
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestQuestActionPayload(targetEntityId, QuestActionType.NEGOTIATE));
    }

    private static void rejectQuest() {
        currentPage = DialoguePage.ROOT;
        DialogueSceneClientState.setResponse(DialogueResponseType.NEUTRAL, (long)(targetEntityId + sceneTicks) + 17L);
    }

    /*
     * Unable to fully structure code
     */
    private static void deliverQuest() {
        client = class_310.method_1551();
        target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) ** GOTO lbl-1000
        npc = (AbstractKingdomNpcEntity)target;
        if (client.field_1724 != null) {
            v0 = QuestJournalClientState.findCompletableByNpc(npc, client.field_1724);
        } else lbl-1000:
        // 2 sources

        {
            v0 = activeQuest = null;
        }
        if (activeQuest != null && "main_pledge".equals(activeQuest.templateId()) && QuestJournalClientState.canCompleteQuest(client.field_1724, activeQuest)) {
            DialogueSceneClientState.openPledgeRewardPage();
            return;
        }
        if (DialogueSceneClientState.targetEntityId < 0) {
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestQuestActionPayload(DialogueSceneClientState.targetEntityId, QuestActionType.DELIVER));
        DialogueSceneClientState.currentPage = DialoguePage.ROOT;
        DialogueSceneClientState.setResponse(DialogueResponseType.POSITIVE, (long)(DialogueSceneClientState.targetEntityId + DialogueSceneClientState.sceneTicks) + 37L);
    }

    private static void sendValyrianAction(QuestActionType actionType, DialogueResponseType responseType) {
        if (targetEntityId < 0 || actionType == null) {
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestQuestActionPayload(targetEntityId, actionType));
        currentPage = DialoguePage.ROOT;
        DialogueSceneClientState.setResponse(responseType, (long)(targetEntityId + sceneTicks) + 43L);
    }

    private static void choosePledge(QuestActionType actionType) {
        if (targetEntityId < 0 || actionType == null) {
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestQuestActionPayload(targetEntityId, actionType));
        currentPage = DialoguePage.ROOT;
        DialogueSceneClientState.setResponse(DialogueResponseType.POSITIVE, (long)(targetEntityId + sceneTicks) + 53L);
    }

    private static void choosePledge(@Nullable String actionId) {
        if (targetEntityId < 0 || actionId == null || actionId.isBlank()) {
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestQuestActionPayload(targetEntityId, actionId));
        currentPage = DialoguePage.ROOT;
        DialogueSceneClientState.setResponse(DialogueResponseType.POSITIVE, (long)(targetEntityId + sceneTicks) + 53L);
    }

    private static void completePledgeWithLordSigil() {
        if (targetEntityId < 0) {
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestQuestActionPayload(targetEntityId, QuestActionType.PLEDGE_COMPLETE_LORD_SIGIL));
        currentPage = DialoguePage.ROOT;
        DialogueSceneClientState.setResponse(DialogueResponseType.POSITIVE, (long)(targetEntityId + sceneTicks) + 71L);
    }

    private static void openPlayerHouseSigilEditor() {
        PlayerHouseStatePayload.Entry existingHouse;
        if (targetEntityId < 0) {
            return;
        }
        class_310 client = class_310.method_1551();
        if (client.field_1724 != null && (existingHouse = PlayerHouseClientState.getByOwnerUuid(client.field_1724.method_5845())) != null) {
            ClientPlayNetworking.send((class_8710)new RequestCreatePlayerHousePayload(targetEntityId, existingHouse.displayName(), existingHouse.sigilColors(), true));
            DialogueSceneClientState.close();
            return;
        }
        DialogueSceneClientState.close();
        PlayerHouseSigilScreen.open(targetEntityId, true);
    }

    private static void confirmRevokePledge() {
        if (targetEntityId < 0) {
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestQuestActionPayload(targetEntityId, QuestActionType.REVOKE_PLEDGE));
        DialogueSceneClientState.close();
    }

    private static void refreshVoyageBubble(class_310 client) {
        VoyagePageState voyageState = DialogueSceneClientState.getVoyagePageState(client);
        VoyageSelection selection = voyageState.selection();
        if (selection == null) {
            DialogueSceneClientState.updateBubbleContent(BubbleContent.ofText(DialogueSceneClientState.isCartRiderTarget(client) ? "No nearby land routes available." : "No harbour destinations available."), false);
            return;
        }
        ArrayList<BubbleLogicalLine> lines = new ArrayList<BubbleLogicalLine>();
        lines.add(BubbleLogicalLine.of(BubbleToken.text(selection.shipType().categoryLabel() + ": " + selection.shipType().displayName())));
        lines.add(BubbleLogicalLine.of(BubbleToken.text("Voyage to " + selection.castle().name() + "?")));
        lines.add(BubbleLogicalLine.of(BubbleToken.text("Cost: "), BubbleToken.item(GotItems.GOLDEN_DRAGON), BubbleToken.text(" " + selection.goldCost() * 2)));
        if (voyageState.canCycleShipType() || voyageState.canCycleDestination()) {
            lines.add(BubbleLogicalLine.of(BubbleToken.text("Use the arrows to browse available routes.")));
        }
        DialogueSceneClientState.updateBubbleContent(BubbleContent.ofLines(lines), false);
    }

    private static void refreshBlacksmithUpgradeBubble(class_310 client) {
        BlacksmithUpgradePageState upgradeState = DialogueSceneClientState.getBlacksmithUpgradePageState(client);
        BlacksmithUpgradeSelection selection = upgradeState.selection();
        if (selection == null) {
            DialogueSceneClientState.updateBubbleContent(BubbleContent.ofText("You have no troops available for upgrading."), false);
            return;
        }
        ArrayList<BubbleLogicalLine> lines = new ArrayList<BubbleLogicalLine>();
        lines.add(BubbleLogicalLine.of(BubbleToken.text("I can upgrade your " + selection.source().displayName() + " into " + selection.target().displayName() + " for")));
        lines.add(BubbleLogicalLine.of(BubbleToken.item(GotItems.GOLDEN_DRAGON), BubbleToken.text(" " + selection.goldCost() * 2)));
        for (String trait : selection.target().traits()) {
            lines.add(BubbleLogicalLine.of(BubbleToken.text("- " + DialogueSceneClientState.formatTrait(trait))));
        }
        DialogueSceneClientState.updateBubbleContent(BubbleContent.ofLines(lines), false);
    }

    private static VoyagePageState getVoyagePageState(class_310 client) {
        List<CastleMapPayload.Entry> destinations = DialogueSceneClientState.collectVoyageDestinations(client);
        List<VoyageShipType> shipTypes = DialogueSceneClientState.collectVoyageTypes(client);
        if (destinations.isEmpty()) {
            currentVoyageIndex = 0;
            currentVoyageShipIndex = 0;
            return new VoyagePageState(destinations, shipTypes, null);
        }
        currentVoyageIndex = Math.floorMod(currentVoyageIndex, destinations.size());
        currentVoyageShipIndex = Math.floorMod(currentVoyageShipIndex, shipTypes.size());
        CastleMapPayload.Entry selected = destinations.get(currentVoyageIndex);
        VoyageShipType shipType = shipTypes.get(currentVoyageShipIndex);
        class_2338 fromBannerPos = DialogueSceneClientState.resolveCurrentVoyageBannerPos(client);
        class_2338 toBannerPos = new class_2338(selected.travelX(), 0, selected.travelZ());
        int goldCost = DialogueSceneClientState.isCartRiderTarget(client) ? GotCastles.calculateLandTravelGoldCost(fromBannerPos, toBannerPos, shipType.id()) : GotCastles.calculateVoyageGoldCost(fromBannerPos, toBannerPos, shipType.variantOrDefault());
        return new VoyagePageState(destinations, shipTypes, new VoyageSelection(selected, shipType, goldCost));
    }

    private static BlacksmithUpgradePageState getBlacksmithUpgradePageState(class_310 client) {
        List<DynamicTroopDefinition> sourceTroops = TroopRosterClientState.troopCounts().keySet().stream().map(id -> DynamicTroopRegistry.get(class_2960.method_12829((String)id))).filter(definition -> definition != null && !definition.upgradeTargets().isEmpty()).toList();
        if (sourceTroops.isEmpty()) {
            currentUpgradeTroopIndex = 0;
            currentUpgradeTargetIndex = 0;
            return new BlacksmithUpgradePageState(sourceTroops, List.of(), null);
        }
        DynamicTroopDefinition source = sourceTroops.get(currentUpgradeTroopIndex = Math.floorMod(currentUpgradeTroopIndex, sourceTroops.size()));
        List<DynamicTroopDefinition> targets = source.upgradeTargets().stream().map(DynamicTroopRegistry::get).filter(Objects::nonNull).toList();
        if (targets.isEmpty()) {
            currentUpgradeTargetIndex = 0;
            return new BlacksmithUpgradePageState(sourceTroops, targets, null);
        }
        currentUpgradeTargetIndex = Math.floorMod(currentUpgradeTargetIndex, targets.size());
        DynamicTroopDefinition target = targets.get(currentUpgradeTargetIndex);
        return new BlacksmithUpgradePageState(sourceTroops, targets, new BlacksmithUpgradeSelection(source, target, GotTroops.goldCostForUpgrade(source, target)));
    }

    private static List<CastleMapPayload.Entry> collectVoyageDestinations(class_310 client) {
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) {
            return List.of();
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        String currentCastleId = npc.got$getCastle();
        CastleMapPayload.Entry sourceCastle = DialogueSceneClientState.findTravelLocationEntry(currentCastleId);
        if (npc.getNpcRole() == KingdomNpcRole.CART_RIDER) {
            if (sourceCastle == null) {
                return List.of();
            }
            ArrayList<CastleMapPayload.Entry> destinations = new ArrayList<CastleMapPayload.Entry>();
            for (CastleMapPayload.Entry castle : DialogueSceneClientState.collectTravelLocationEntries()) {
                if (currentCastleId.equals(castle.id()) || !LandTravelRouteHelper.canTravelByLand(sourceCastle.travelX(), sourceCastle.travelZ(), castle.travelX(), castle.travelZ())) continue;
                destinations.add(castle);
            }
            return destinations;
        }
        VoyageNetwork network = CastleMapClientState.getVoyageNetwork();
        ArrayList<CastleMapPayload.Entry> destinations = new ArrayList<CastleMapPayload.Entry>();
        for (CastleMapPayload.Entry castle : CastleMapClientState.getCastles()) {
            if (!castle.subtypeIds().contains("harbour") || !currentCastleId.isBlank() && currentCastleId.equals(castle.id()) || currentCastleId.isBlank() || network.findPath(currentCastleId, castle.id()).isEmpty()) continue;
            destinations.add(castle);
        }
        return destinations;
    }

    private static List<VoyageShipType> collectVoyageTypes(class_310 client) {
        if (DialogueSceneClientState.isCartRiderTarget(client)) {
            return List.of(new VoyageShipType("cart", "Cart", CART_ITEM_TEXTURE, "Cart", null), new VoyageShipType("cart_2horse", "2 Horse Cart", CART_2HORSE_ITEM_TEXTURE, "Cart", null));
        }
        return List.of(new VoyageShipType(VesselVariant.SHIP.id(), "Ship", SHIP_ITEM_TEXTURE, "Ship", VesselVariant.SHIP), new VoyageShipType(VesselVariant.GALLEY.id(), "Galley", GALLEY_ITEM_TEXTURE, "Ship", VesselVariant.GALLEY));
    }

    private static boolean isCartRiderTarget(class_310 client) {
        AbstractKingdomNpcEntity npc;
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        return target instanceof AbstractKingdomNpcEntity && (npc = (AbstractKingdomNpcEntity)target).getNpcRole() == KingdomNpcRole.CART_RIDER;
    }

    private static boolean canAffordVoyageSelection(class_310 client, VoyageSelection selection) {
        if (client.field_1724 == null || selection == null) {
            return false;
        }
        return GotEconomy.hasLegacyGoldIngots((class_1657)client.field_1724, selection.goldCost());
    }

    private static void updateBubbleContent(BubbleContent content, boolean resetTypewriter) {
        activeBubbleContent = content;
        pendingAutoCloseTick = -1;
        if (resetTypewriter) {
            DialogueSceneClientState.resetTypewriterState();
        }
    }

    private static void requestNpcChatAction(NpcChatAction action) {
        if (targetEntityId < 0 || action == null) {
            return;
        }
        ClientPlayNetworking.send((class_8710)new RequestNpcChatActionPayload(targetEntityId, action.id()));
    }

    private static boolean canFlirtWithNpc() {
        if (DialogueSceneClientState.isChildNpc()) {
            return false;
        }
        return NpcRelationTier.fromValue(currentNpcRelationValue).ordinal() >= NpcRelationTier.FRIENDLY.ordinal();
    }

    private static boolean isChildNpc() {
        AbstractKingdomNpcEntity npc;
        class_310 client = class_310.method_1551();
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        return target instanceof AbstractKingdomNpcEntity && GotDynastyNpcs.isChildNpc(npc = (AbstractKingdomNpcEntity)target);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean canTryForBabyCurrentNpc() {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null) {
            return false;
        }
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) return false;
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        if (!client.field_1724.method_5845().equals(npc.getMarriedPlayerUuid())) return false;
        return true;
    }

    private static long remainingTryForBabyDays(class_310 client) {
        AbstractKingdomNpcEntity npc;
        block3: {
            block2: {
                class_1297 target = DialogueSceneClientState.getTargetEntity(client);
                if (!(target instanceof AbstractKingdomNpcEntity)) break block2;
                npc = (AbstractKingdomNpcEntity)target;
                if (client.field_1687 != null) break block3;
            }
            return 0L;
        }
        long currentDay = Math.max(client.field_1687.method_8510() / 24000L, client.field_1687.method_8532() / 24000L);
        return Math.max(0L, npc.getBabyCooldownUnlockDay() - currentDay);
    }

    private static boolean canMarryCurrentNpc() {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null) {
            return false;
        }
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)client.field_1724).got$getCharacterProfile();
        if (profile != null && "agob:the_nights_watch".equals(profile.getSelectedKingdomId())) {
            return false;
        }
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) {
            return false;
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        return GotNpcMarriage.canMarryNpc(client.field_1724.method_5845(), npc, npc.getNpcRole() != KingdomNpcRole.LORD || CharacterSelectionClientState.getReputationProfile().getTitle().getTier() >= 2, currentNpcRelationValue);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean canDivorceCurrentNpc() {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null) {
            return false;
        }
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) return false;
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        if (!client.field_1724.method_5845().equals(npc.getMarriedPlayerUuid())) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean canTakeToPartyCurrentNpc() {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null) {
            return false;
        }
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) return false;
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        if (!client.field_1724.method_5845().equals(npc.getMarriedPlayerUuid())) return false;
        if (npc.isMarriagePartyCompanion()) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean canAskToLeaveCurrentNpc() {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null) {
            return false;
        }
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) return false;
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        if (!client.field_1724.method_5845().equals(npc.getMarriedPlayerUuid())) return false;
        if (!npc.isMarriagePartyCompanion()) return false;
        return true;
    }

    private static class_2338 resolveCurrentVoyageBannerPos(class_310 client) {
        AbstractKingdomNpcEntity npc;
        String currentCastleId;
        CastleMapPayload.Entry location;
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (target instanceof AbstractKingdomNpcEntity && (location = DialogueSceneClientState.findTravelLocationEntry(currentCastleId = (npc = (AbstractKingdomNpcEntity)target).got$getCastle())) != null) {
            return new class_2338(location.travelX(), 0, location.travelZ());
        }
        if (client.field_1724 != null) {
            return client.field_1724.method_24515();
        }
        return class_2338.field_10980;
    }

    private static List<CastleMapPayload.Entry> collectTravelLocationEntries() {
        ArrayList<CastleMapPayload.Entry> entries = new ArrayList<CastleMapPayload.Entry>(CastleMapClientState.getCastles());
        for (VillageMapPayload.Entry village : VillageMapClientState.getVillages()) {
            entries.add(DialogueSceneClientState.toTravelEntry(village));
        }
        return entries;
    }

    private static CastleMapPayload.Entry findTravelLocationEntry(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        for (CastleMapPayload.Entry castle : CastleMapClientState.getCastles()) {
            if (!id.equals(castle.id())) continue;
            return castle;
        }
        for (VillageMapPayload.Entry village : VillageMapClientState.getVillages()) {
            if (!id.equals(village.id())) continue;
            return DialogueSceneClientState.toTravelEntry(village);
        }
        return null;
    }

    @Nullable
    private static CastleMapPayload.Entry findCastleEntryForNpc(AbstractKingdomNpcEntity npc) {
        if (npc == null) {
            return null;
        }
        String castleId = npc.got$getCastle();
        if (castleId == null || castleId.isBlank()) {
            return null;
        }
        for (CastleMapPayload.Entry castle : CastleMapClientState.getCastles()) {
            if (!castleId.equals(castle.id())) continue;
            return castle;
        }
        return null;
    }

    private static CastleMapPayload.Entry toTravelEntry(VillageMapPayload.Entry village) {
        return new CastleMapPayload.Entry(village.id(), village.worldX(), village.worldZ(), village.worldX(), village.worldZ(), false, false, "village", village.name(), "", village.kingdomId(), "", village.kingdomId(), "", "", "", "", List.of(), true, false);
    }

    private static boolean shouldUseWideOption(String label) {
        return class_310.method_1551().field_1772.method_1727(label) > 54;
    }

    private static BubbleContent buildQuestBubbleContent(class_310 client, AbstractKingdomNpcEntity npc, @Nullable QuestJournalStatePayload.Entry activeQuest) {
        if (client.field_1724 == null || client.field_1687 == null) {
            return BubbleContent.ofText("No quest available.");
        }
        if (activeQuest != null) {
            if ("valyrian_rumor".equals(activeQuest.templateId())) {
                if ("confirm_identity".equals(activeQuest.objectiveActionId())) {
                    return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text("The rumor points south, to the dragon banners.")), BubbleLogicalLine.of(BubbleToken.text("Find a Targaryen lord and confirm whether true Valyrians remain.")), BubbleLogicalLine.of(BubbleToken.text("This quest cannot be cancelled."))));
                }
                return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text("The Targaryen lord has confirmed the news.")), BubbleLogicalLine.of(BubbleToken.text("Return to " + activeQuest.giverNpcName() + " and deliver it.")), BubbleLogicalLine.of(BubbleToken.text("This quest cannot be cancelled."))));
            }
            if ("nearby_bandit_camp_destroy".equals(activeQuest.templateId())) {
                long days = Math.max(1L, Math.max(0L, activeQuest.dueAtTick() - QuestJournalClientState.currentQuestTime(client.field_1687) + 23999L) / 24000L);
                return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text(activeQuest.objectiveComplete() ? "The bandit camp has been broken." : "The nearby bandit camp still plagues our castle.")), BubbleLogicalLine.of(BubbleToken.text("You have " + days + " day(s) left.")), BubbleLogicalLine.of(BubbleToken.text(activeQuest.objectiveComplete() ? "Return and finish the quest." : "Destroy it and report back to me."))));
            }
            if ("destroy_wildling_raider_camp".equals(activeQuest.templateId())) {
                long days = Math.max(1L, Math.max(0L, activeQuest.dueAtTick() - QuestJournalClientState.currentQuestTime(client.field_1687) + 23999L) / 24000L);
                return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text(activeQuest.objectiveComplete() ? "The wildling raider camp has been broken." : "A wildling raider camp is hiding in the Gift.")), BubbleLogicalLine.of(BubbleToken.text("You have " + days + " day(s) left.")), BubbleLogicalLine.of(BubbleToken.text(activeQuest.objectiveComplete() ? "Return and finish the quest." : "Destroy it and report back to me."))));
            }
            if ("nights_watch_ranger_explore".equals(activeQuest.templateId())) {
                long days = Math.max(1L, Math.max(0L, activeQuest.dueAtTick() - QuestJournalClientState.currentQuestTime(client.field_1687) + 23999L) / 24000L);
                String stage = activeQuest.objectiveActionId();
                String line = "return".equals(stage) ? "You have news from beyond the Wall." : ("kill_raiders".equals(stage) ? "Find and kill 10 wildling raiders beyond the Wall." : "Go beyond the Wall into the Lands of Always Winter.");
                return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text(line)), BubbleLogicalLine.of(BubbleToken.text("You have " + days + " day(s) left.")), BubbleLogicalLine.of(BubbleToken.text(activeQuest.objectiveComplete() ? "Report what you found." : "The realm needs your eyes in the true north."))));
            }
            class_1792 item = QuestJournalClientState.resolveItem(activeQuest.displayItemId());
            long days = Math.max(1L, Math.max(0L, activeQuest.dueAtTick() - QuestJournalClientState.currentQuestTime(client.field_1687) + 23999L) / 24000L);
            if (!activeQuest.targetCastleName().isBlank()) {
                String destinationLine = activeQuest.itemId().equals("agob:sealed_letter") ? "Letter received for " + activeQuest.targetNpcName() + " of " + activeQuest.targetCastleName() + "." : "Shipment received for " + activeQuest.targetCastleName() + ".";
                return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text(destinationLine)), BubbleLogicalLine.of(BubbleToken.text("You have " + days + " day(s) left.")), BubbleLogicalLine.of(BubbleToken.text(QuestJournalClientState.canCompleteQuest(client.field_1724, activeQuest) ? "You have what I asked for. Finish the quest." : "Return with the shipment in hand."))));
            }
            return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text("Deliver me " + activeQuest.requiredCount() + " "), BubbleToken.item(item), BubbleToken.text(" " + item.method_7848().getString())), BubbleLogicalLine.of(BubbleToken.text("You have " + days + " day(s) left.")), BubbleLogicalLine.of(BubbleToken.text(QuestJournalClientState.canCompleteQuest(client.field_1724, activeQuest) ? "You have what I asked for. Finish the quest." : "Return when you carry the full delivery."))));
        }
        if (DialogueSceneClientState.shouldShowValyrianQuestOption(client)) {
            ArrayList<BubbleLogicalLine> lines = new ArrayList<BubbleLogicalLine>();
            lines.add(BubbleLogicalLine.of(BubbleToken.text("There are whispers from the south: true Valyrians, not dead, not gone.")));
            lines.add(BubbleLogicalLine.of(BubbleToken.text("Investigate the rumor and bring me proof.")));
            lines.add(BubbleLogicalLine.of(BubbleToken.text("This quest cannot be cancelled once started.")));
            if (DialogueSceneClientState.isOriginTargaryen()) {
                lines.add(BubbleLogicalLine.of(BubbleToken.text("Charisma 36+: believed, quest complete.")));
                lines.add(BubbleLogicalLine.of(BubbleToken.text("Below 36: not believed, seek Targaryen confirmation.")));
            }
            lines.add(BubbleLogicalLine.of(BubbleToken.text("Reward: Dragon Egg, Dragon Cradle")));
            return BubbleContent.ofLines(lines);
        }
        QuestInstance preview = QuestTemplateRegistry.previewForPlayer(client.field_1724.method_5667(), npc, QuestJournalClientState.getOfferCycle(), client.field_1687.method_8510());
        if (preview == null || QuestJournalClientState.isNpcOnCooldown(npc.method_5667(), client.field_1687.method_8510()) || preview != null && QuestJournalClientState.isTemplateNpcOnCooldown(npc.method_5667(), preview.templateId(), client.field_1687.method_8510()) || QuestJournalClientState.hasCompletedOffer(preview.offerKey())) {
            return BubbleContent.ofText("No quest available.");
        }
        QuestNegotiationOutcome negotiationOutcome = QuestJournalClientState.getNegotiationOutcome(preview.offerKey());
        if (negotiationOutcome != null) {
            preview = QuestNegotiationService.applyOutcome(preview, negotiationOutcome);
        }
        if ("nearby_bandit_camp_destroy".equals(preview.templateId())) {
            long days = Math.max(1L, (preview.dueAtTick() - preview.acceptedAtTick() + 23999L) / 24000L);
            return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text("My Lord has requested I deliver you this quest. A Bandit Camp nearby has plagued our castle, destroy it and report back to me and you will be handsomely rewarded.")), BubbleLogicalLine.of(BubbleToken.text("You have " + days + " day(s) to deal with it.")), BubbleLogicalLine.of(BubbleToken.text("Reward: 24 Reputation EXP, 12 Golden Dragons, 1 Charisma Skill Point"))));
        }
        if ("destroy_wildling_raider_camp".equals(preview.templateId())) {
            long days = Math.max(1L, (preview.dueAtTick() - preview.acceptedAtTick() + 23999L) / 24000L);
            return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text("A wildling raider camp has taken root in the Gift. Burn it out and report back.")), BubbleLogicalLine.of(BubbleToken.text("You have " + days + " day(s) to deal with it.")), BubbleLogicalLine.of(BubbleToken.text("Reward: 24 Reputation EXP, 12 Golden Dragons, 1 Charisma Skill Point"))));
        }
        if ("nights_watch_ranger_explore".equals(preview.templateId())) {
            long days = Math.max(1L, (preview.dueAtTick() - preview.acceptedAtTick() + 23999L) / 24000L);
            return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text("Ride beyond the Wall into the Lands of Always Winter.")), BubbleLogicalLine.of(BubbleToken.text("Find and kill 10 wildling raiders, then return with your report.")), BubbleLogicalLine.of(BubbleToken.text("You have " + days + " day(s). Reward: 28 Reputation EXP, 12 Golden Dragons, 1 Charisma Skill Point"))));
        }
        class_1792 item = QuestJournalClientState.resolveItem(preview.displayItemId());
        long days = Math.max(1L, (preview.dueAtTick() - preview.acceptedAtTick() + 23999L) / 24000L);
        ArrayList<BubbleLogicalLine> lines = new ArrayList<BubbleLogicalLine>();
        if (negotiationOutcome == QuestNegotiationOutcome.SUCCESS) {
            lines.add(BubbleLogicalLine.of(BubbleToken.text(DialogueSceneClientState.pickNegotiationLine(true, npc.method_5628()))));
        } else if (negotiationOutcome == QuestNegotiationOutcome.FAILURE) {
            lines.add(BubbleLogicalLine.of(BubbleToken.text(DialogueSceneClientState.pickNegotiationLine(false, npc.method_5628()))));
        }
        if (preview.isShipmentQuest()) {
            String deliveryText = preview.itemId().equals("agob:sealed_letter") ? " " + item.method_7848().getString() + " to " + preview.targetNpcName() + " at " + preview.targetCastleName() + "." : " " + item.method_7848().getString() + " shipment to " + preview.targetCastleName() + ".";
            lines.add(BubbleLogicalLine.of(BubbleToken.text("Take this "), BubbleToken.item(item), BubbleToken.text(deliveryText)));
        } else {
            lines.add(BubbleLogicalLine.of(BubbleToken.text("Deliver me " + preview.requiredCount() + " "), BubbleToken.item(item), BubbleToken.text(" " + item.method_7848().getString())));
        }
        lines.add(BubbleLogicalLine.of(BubbleToken.text("within " + days + " day(s).")));
        lines.addAll(DialogueSceneClientState.buildRewardLines(preview));
        return BubbleContent.ofLines(lines);
    }

    private static String pickNegotiationLine(boolean success, long seed) {
        String[] stringArray;
        if (success) {
            String[] stringArray2 = new String[3];
            stringArray2[0] = "You bargain well. These terms stand.";
            stringArray2[1] = "Very well, I will sweeten the offer.";
            stringArray = stringArray2;
            stringArray2[2] = "You drove a harder bargain than most.";
        } else {
            String[] stringArray3 = new String[3];
            stringArray3[0] = "You pressed too far. Those are my terms.";
            stringArray3[1] = "Greed has cost you a better reward.";
            stringArray = stringArray3;
            stringArray3[2] = "You misjudged the bargain. It stands.";
        }
        String[] lines = stringArray;
        return lines[Math.floorMod((int)seed, lines.length)];
    }

    public static void onQuestJournalUpdated(class_310 client) {
        client.execute(() -> {
            if (!active || currentPage != DialoguePage.QUEST_OFFER || client.field_1724 == null) {
                return;
            }
            class_1297 target = DialogueSceneClientState.getTargetEntity(client);
            if (target instanceof AbstractKingdomNpcEntity) {
                AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
                activeBubbleContent = DialogueSceneClientState.buildQuestBubbleContent(client, npc, null);
                DialogueSceneClientState.resetTypewriterState();
            }
        });
    }

    public static void onTroopRosterUpdated(class_310 client) {
        client.execute(() -> {
            if (!active || currentPage != DialoguePage.BLACKSMITH_UPGRADE_SELECT) {
                return;
            }
            DialogueSceneClientState.refreshBlacksmithUpgradeBubble(client);
        });
    }

    private static boolean canAffordBlacksmithUpgradeSelection(class_310 client, BlacksmithUpgradeSelection selection) {
        if (client == null || client.field_1724 == null || selection == null) {
            return false;
        }
        if (!selection.target().meetsReputationRequirement(CharacterSelectionClientState.getReputationTitle())) {
            return false;
        }
        if (!selection.target().meetsReligionRequirement(CharacterSelectionClientState.getSelectedReligionId())) {
            return false;
        }
        if (!selection.target().meetsHouseRequirement(CharacterSelectionClientState.getSelectedOptionId())) {
            return false;
        }
        return GotEconomy.hasLegacyGoldIngots((class_1657)client.field_1724, selection.goldCost());
    }

    private static String formatTrait(String trait) {
        if (trait == null || trait.isBlank()) {
            return "";
        }
        String[] parts = trait.replace('_', ' ').split(" ");
        StringBuilder builder = new StringBuilder();
        for (String part : parts) {
            if (part.isBlank()) continue;
            if (!builder.isEmpty()) {
                builder.append(' ');
            }
            builder.append(Character.toUpperCase(part.charAt(0)));
            if (part.length() <= 1) continue;
            builder.append(part.substring(1));
        }
        return builder.toString();
    }

    private static List<BubbleLogicalLine> buildRewardLines(QuestInstance quest) {
        SkillAttributeType type;
        ArrayList<BubbleLogicalLine> lines = new ArrayList<BubbleLogicalLine>();
        lines.add(BubbleLogicalLine.of(BubbleToken.text("Reward:")));
        if (quest.reputationExperience() > 0) {
            lines.add(BubbleLogicalLine.of(BubbleToken.text("- " + quest.reputationExperience() + " Reputation EXP")));
        }
        if (quest.goldIngots() > 0) {
            lines.add(BubbleLogicalLine.of(BubbleToken.text("- " + quest.goldIngots() * 2 + " "), BubbleToken.item(GotItems.GOLDEN_DRAGON), BubbleToken.text(" Golden Dragons")));
        }
        if (quest.ironIngots() > 0) {
            lines.add(BubbleLogicalLine.of(BubbleToken.text("- " + quest.ironIngots() + " "), BubbleToken.item(class_1802.field_8620), BubbleToken.text(" Iron Ingots")));
        }
        if (quest.skillPoints() > 0 && !quest.skillTypeId().isBlank()) {
            type = SkillAttributeType.byId(quest.skillTypeId());
            lines.add(BubbleLogicalLine.of(BubbleToken.text("- " + quest.skillPoints() + " " + (String)(type == null ? "Skill Points" : type.getDisplayName() + " Skill Points"))));
        }
        if (quest.bonusSkillPoints() > 0 && !quest.bonusSkillTypeId().isBlank()) {
            type = SkillAttributeType.byId(quest.bonusSkillTypeId());
            lines.add(BubbleLogicalLine.of(BubbleToken.text("- " + quest.bonusSkillPoints() + " " + (String)(type == null ? "Skill Points" : type.getDisplayName() + " Skill Points"))));
        }
        return lines;
    }

    private static void tickTypingSound(class_310 client) {
        if (client.field_1724 == null || client.method_1483() == null) {
            return;
        }
        int totalCharacters = activeBubbleContent.visibleCharacterCount();
        if (totalCharacters <= 0) {
            lastTypedCharacterCount = 0;
            return;
        }
        int visibleCharacters = DialogueSceneClientState.getVisibleCharacterCount();
        if (visibleCharacters <= lastTypedCharacterCount) {
            return;
        }
        lastTypedCharacterCount = visibleCharacters;
        if (visibleCharacters >= totalCharacters || sceneTicks < nextTypingSoundTick) {
            return;
        }
        int soundIndex = DialogueSceneClientState.pickTypingSoundIndex();
        float pitch = ThreadLocalRandom.current().nextFloat(0.96f, 1.04f);
        client.method_1483().method_4873((class_1113)class_1109.method_4757((class_3414)DIALOGUE_TYPING_SOUNDS[soundIndex], (float)0.55f, (float)pitch));
        nextTypingSoundTick = sceneTicks + 20;
    }

    private static int getVisibleCharacterCount() {
        return Math.max(0, (sceneTicks - responseStartedAtTick) / 1);
    }

    private static void resetTypewriterState() {
        responseStartedAtTick = sceneTicks;
        lastTypedCharacterCount = 0;
        nextTypingSoundTick = sceneTicks;
        lastTypingSoundIndex = -1;
    }

    private static int pickTypingSoundIndex() {
        if (DIALOGUE_TYPING_SOUNDS.length <= 1) {
            return 0;
        }
        int nextIndex = ThreadLocalRandom.current().nextInt(DIALOGUE_TYPING_SOUNDS.length);
        if (nextIndex == lastTypingSoundIndex) {
            nextIndex = (nextIndex + 1) % DIALOGUE_TYPING_SOUNDS.length;
        }
        lastTypingSoundIndex = nextIndex;
        return nextIndex;
    }

    private static List<BubbleRenderLine> getVisibleBubbleLines(class_310 client) {
        if (client == null || client.field_1772 == null) {
            return List.of();
        }
        return activeBubbleContent.layout(client, DialogueSceneClientState.getVisibleCharacterCount());
    }

    private static List<BubbleRenderLine> getAllBubbleLines(class_310 client) {
        if (client == null || client.field_1772 == null) {
            return List.of();
        }
        return activeBubbleContent.layout(client, Integer.MAX_VALUE);
    }

    private static int getBubbleHeight(class_310 client, List<BubbleRenderLine> lines) {
        Objects.requireNonNull(client.field_1772);
        int lineHeight = 9 + 1;
        int contentHeight = Math.max(lineHeight, lines.size() * lineHeight);
        int totalInnerHeight = 8 + contentHeight + 8;
        int middleSectionCount = Math.max(1, class_3532.method_15386((float)((float)totalInnerHeight / 16.0f)));
        return 8 + middleSectionCount * 16 + 8;
    }

    private static class_2960 relationTexture(NpcRelationTier tier) {
        return switch (tier) {
            default -> throw new MatchException(null, null);
            case NpcRelationTier.HOSTILE -> RELATION_ENEMY;
            case NpcRelationTier.UNFRIENDLY -> RELATION_SUSPICIOUS;
            case NpcRelationTier.NEUTRAL -> RELATION_NEUTRAL;
            case NpcRelationTier.FRIENDLY -> RELATION_FRIEND;
            case NpcRelationTier.TRUSTED -> RELATION_ALLY;
        };
    }

    private static String formatRelationValue(int relation) {
        return relation > 0 ? "+" + relation : Integer.toString(relation);
    }

    private static boolean isHireDialogue(class_310 client) {
        return DialogueSceneClientState.getTargetEntity(client) instanceof HireableUnitEntity;
    }

    private static boolean canHireDisplayedTroop(class_310 client) {
        HireableUnitEntity hireableUnit;
        block12: {
            block11: {
                class_1297 class_12972 = DialogueSceneClientState.getTargetEntity(client);
                if (!(class_12972 instanceof HireableUnitEntity)) break block11;
                hireableUnit = (HireableUnitEntity)class_12972;
                if (client.field_1724 != null) break block12;
            }
            return false;
        }
        if (!TroopRosterClientState.hasCapacityForOneMore()) {
            return false;
        }
        DynamicTroopDefinition definition = hireableUnit.getTroopDefinition();
        if (definition == null) {
            return false;
        }
        if (!definition.meetsReputationRequirement(CharacterSelectionClientState.getReputationTitle())) {
            return false;
        }
        if (!definition.meetsReligionRequirement(CharacterSelectionClientState.getSelectedReligionId())) {
            return false;
        }
        if (!definition.canBeHiredByKingdom(CharacterSelectionClientState.getSelectedKingdomId())) {
            return false;
        }
        if (!definition.meetsHouseRequirement(CharacterSelectionClientState.getSelectedOptionId())) {
            return false;
        }
        class_1792 costItem = GotTroops.resolveHireCostItem(definition);
        int costAmount = GotTroops.resolveHireCostAmount(definition);
        if (GotEconomy.isCurrency(costItem)) {
            return GotEconomy.hasCurrencyValue((class_1657)client.field_1724, GotEconomy.priceValue(costItem, costAmount));
        }
        int count = 0;
        for (int slot = 0; slot < client.field_1724.method_31548().method_5439(); ++slot) {
            class_1799 stack = client.field_1724.method_31548().method_5438(slot);
            if (!stack.method_31574(costItem)) continue;
            count += stack.method_7947();
        }
        return count >= costAmount;
    }

    @Nullable
    private static HireRequirementTooltip getLockedHireTooltip(class_310 client, DialogueLayout layout, List<DialogueOption> options, double scaledMouseX, double scaledMouseY) {
        class_2960 icon;
        Object label;
        HireableUnitEntity hireableUnit;
        block13: {
            block12: {
                int y;
                if (!DialogueSceneClientState.isHireDialogue(client) || options.isEmpty()) {
                    return null;
                }
                DialogueOption option = options.getFirst();
                if (option.enabled()) {
                    return null;
                }
                int width = option.wide() ? 96 : 64;
                int x = layout.optionsX() + (option.wide() ? 0 : (layout.maxOptionWidth() - 64) / 2);
                if (!DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, x, y = layout.optionsY(), width, 16)) {
                    return null;
                }
                class_1297 class_12972 = DialogueSceneClientState.getTargetEntity(client);
                if (!(class_12972 instanceof HireableUnitEntity)) break block12;
                hireableUnit = (HireableUnitEntity)class_12972;
                if (client.field_1724 != null) break block13;
            }
            return null;
        }
        DynamicTroopDefinition definition = hireableUnit.getTroopDefinition();
        if (definition == null) {
            return null;
        }
        ArrayList<HireRequirementLine> lines = new ArrayList<HireRequirementLine>();
        lines.add(new HireRequirementLine("Requirements:", true, null, class_1799.field_8037));
        class_1792 costItem = GotTroops.resolveHireCostItem(definition);
        int costAmount = GotTroops.resolveHireCostAmount(definition);
        String costLabel = "  " + costAmount + " " + new class_1799((class_1935)costItem).method_7964().getString();
        boolean canAffordCost = GotEconomy.isCurrency(costItem) ? GotEconomy.hasCurrencyValue((class_1657)client.field_1724, GotEconomy.priceValue(costItem, costAmount)) : DialogueSceneClientState.inventoryCount(client, costItem) >= costAmount;
        lines.add(new HireRequirementLine(costLabel, canAffordCost, null, new class_1799((class_1935)costItem)));
        if (definition.hasReputationRequirement()) {
            String label2 = definition.reputationRequirementLabel().replace("Requires ", "").replace("+ Title", " Title");
            lines.add(new HireRequirementLine(label2, definition.meetsReputationRequirement(CharacterSelectionClientState.getReputationTitle()), null, class_1799.field_8037));
        }
        if (definition.hasReligionRequirement()) {
            ReligionSelectionOption religionOption = GotReligionOptions.get(definition.requiredReligionId());
            label = religionOption != null ? religionOption.displayName() + " Religion" : definition.religionRequirementLabel().replace("Requires ", "");
            icon = religionOption != null ? religionOption.texture() : null;
            lines.add(new HireRequirementLine((String)label, definition.meetsReligionRequirement(CharacterSelectionClientState.getSelectedReligionId()), icon, class_1799.field_8037));
        }
        if (definition.hasDisallowedHireKingdoms()) {
            String selectedKingdomId = CharacterSelectionClientState.getSelectedKingdomId();
            for (String kingdomId : definition.disallowedHireKingdomIds()) {
                Kingdom kingdom;
                class_2960 kingdomIdentifier = class_2960.method_12829((String)kingdomId);
                CharacterSelectionOption kingdomOption = kingdomIdentifier == null ? null : GotCharacterOptions.getByKingdomId(kingdomIdentifier);
                Kingdom kingdom2 = kingdom = kingdomIdentifier == null ? null : GotKingdoms.get(kingdomIdentifier);
                String kingdomName = kingdomOption != null && !kingdomOption.displayName().isBlank() ? kingdomOption.displayName() : (kingdom != null && !kingdom.getName().isBlank() ? kingdom.getName() : kingdomId);
                String label3 = "Cannot be " + kingdomName;
                class_2960 icon2 = kingdomOption != null ? kingdomOption.texture() : null;
                lines.add(new HireRequirementLine(label3, !kingdomId.equals(selectedKingdomId), icon2, class_1799.field_8037));
            }
        }
        if (definition.hasHouseRequirement()) {
            CharacterSelectionOption houseOption = GotCharacterOptions.get(definition.requiredHouseId());
            label = houseOption != null && !houseOption.houseDisplayName().isBlank() ? houseOption.houseDisplayName() : definition.houseRequirementLabel().replace("Requires ", "");
            icon = houseOption != null ? houseOption.texture() : null;
            lines.add(new HireRequirementLine((String)label, definition.meetsHouseRequirement(CharacterSelectionClientState.getSelectedOptionId()), icon, class_1799.field_8037));
        }
        return new HireRequirementTooltip(lines);
    }

    @Nullable
    private static HireRequirementTooltip getOptionTooltip(DialogueLayout layout, List<DialogueOption> options, double scaledMouseX, double scaledMouseY) {
        for (int index = 0; index < options.size(); ++index) {
            int y;
            DialogueOption option = options.get(index);
            if (option.tooltip() == null) continue;
            int width = option.wide() ? 96 : 64;
            int x = layout.optionsX() + (option.wide() ? 0 : (layout.maxOptionWidth() - 64) / 2);
            if (!DialogueSceneClientState.isWithin(scaledMouseX, scaledMouseY, x, y = layout.optionsY() + index * 20, width, 16)) continue;
            return option.tooltip();
        }
        return null;
    }

    private static HireRequirementTooltip buildValyrianClaimTooltip() {
        int charisma = CharacterSelectionClientState.getSkillPoints(SkillAttributeType.CHARISMA);
        return new HireRequirementTooltip(List.of(new HireRequirementLine("Chance 1: Charisma 36+", charisma > 35, SkillAttributeType.CHARISMA.getIconTexture(), class_1799.field_8037), new HireRequirementLine("Response 1: Lord believes you", true, null, class_1799.field_8037), new HireRequirementLine("Response 2: Lord refuses proof", false, null, class_1799.field_8037)));
    }

    private static HireRequirementTooltip buildGoldCostTooltip(int cost) {
        boolean owned = GotEconomy.hasLegacyGoldIngots((class_1657)class_310.method_1551().field_1724, cost);
        return new HireRequirementTooltip(List.of(new HireRequirementLine("Requires:", true, null, class_1799.field_8037), new HireRequirementLine("  " + GotEconomy.formatLegacyGoldIngots(cost), owned, null, GotEconomy.itemStackForLegacyGoldIngots(cost))));
    }

    @Nullable
    private static HireRequirementTooltip buildTitleChallengeCooldownTooltip() {
        if (currentTitleChallengeCooldownSeconds <= 0) {
            return null;
        }
        return new HireRequirementTooltip(List.of(new HireRequirementLine("Cooldown " + DialogueSceneClientState.formatCooldownLabel(currentTitleChallengeCooldownSeconds), false, null, class_1799.field_8037)));
    }

    private static HireRequirementTooltip buildTourneyTooltip(String line) {
        return new HireRequirementTooltip(List.of(new HireRequirementLine(line, true, null, class_1799.field_8037)));
    }

    private static HireRequirementTooltip buildTourneyLockedTooltip() {
        String line = currentTourneyAttended ? "Already attempted this tourney." : "No tourney is hosted here today.";
        return new HireRequirementTooltip(List.of(new HireRequirementLine(line, false, null, class_1799.field_8037)));
    }

    private static String formatCooldownLabel(int totalSeconds) {
        int clamped = Math.max(0, totalSeconds);
        int minutes = clamped / 60;
        int seconds = clamped % 60;
        return String.format(Locale.ROOT, "%02d:%02d", minutes, seconds);
    }

    private static boolean shouldShowTitleChallengeOption(class_310 client) {
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        if (!(target instanceof AbstractKingdomNpcEntity)) {
            return false;
        }
        AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
        if (npc.getNpcRole() != KingdomNpcRole.LORD || npc.isNobleSpouse()) {
            return false;
        }
        ReputationTitle title = CharacterSelectionClientState.getReputationTitle();
        return title != null && title.isAtLeast(ReputationTitle.LORD);
    }

    private static boolean shouldShowTourneyOption(class_310 client) {
        AbstractKingdomNpcEntity npc;
        class_1297 target = DialogueSceneClientState.getTargetEntity(client);
        return target instanceof AbstractKingdomNpcEntity && (npc = (AbstractKingdomNpcEntity)target).getNpcRole() == KingdomNpcRole.ARENA_MASTER;
    }

    private static void drawHireRequirementTooltip(class_332 context, class_310 client, double scaledMouseX, double scaledMouseY, HireRequirementTooltip tooltip) {
        int iconSize = 9;
        Objects.requireNonNull(client.field_1772);
        int lineHeight = 9 + 3;
        int padding = 6;
        int width = 0;
        for (HireRequirementLine line : tooltip.lines()) {
            int lineWidth = client.field_1772.method_1727(line.text());
            if (!line.itemIcon().method_7960() || line.textureIcon() != null) {
                lineWidth += iconSize + 4;
            }
            width = Math.max(width, lineWidth);
        }
        int height = tooltip.lines().size() * lineHeight + padding * 2 - 1;
        int x = class_3532.method_15340((int)((int)scaledMouseX + 12), (int)8, (int)(client.method_22683().method_4486() - (width += padding * 2) - 8));
        int y = class_3532.method_15340((int)((int)scaledMouseY - height - 8), (int)8, (int)(client.method_22683().method_4502() - height - 8));
        context.method_51448().method_22903();
        context.method_51448().method_46416(0.0f, 0.0f, 600.0f);
        context.method_25294(x, y, x + width, y + height, -267386864);
        context.method_49601(x, y, width, height, -11513776);
        int lineY = y + padding;
        for (HireRequirementLine line : tooltip.lines()) {
            int lineX = x + padding;
            if (!line.itemIcon().method_7960()) {
                context.method_51427(line.itemIcon(), lineX, lineY - 2);
                lineX += iconSize + 4;
            } else if (line.textureIcon() != null) {
                context.method_25291(line.textureIcon(), lineX, lineY - 1, 0, 0.0f, 0.0f, iconSize, iconSize, iconSize, iconSize);
                lineX += iconSize + 4;
            }
            int color = line.text().startsWith("Response 1:") ? -8323200 : (line.text().startsWith("Response 2:") ? -32640 : (line.met() ? -1 : -32640));
            context.method_27535(client.field_1772, (class_2561)class_2561.method_43470((String)line.text()), lineX, lineY, color);
            lineY += lineHeight;
        }
        context.method_51448().method_22909();
    }

    static {
        targetEntityId = -1;
        blockedEntityId = -1;
        activeBubbleContent = BubbleContent.ofText("");
        lastTypingSoundIndex = -1;
        pendingAutoCloseTick = -1;
        currentPage = DialoguePage.ROOT;
        currentNpcRelationId = "";
        currentNpcKingdomId = "";
        currentDialogueContextId = "";
        currentDialogueSubjectName = "";
        currentNpcAttributes = NpcAttributeProfile.EMPTY;
    }

    @Environment(value=EnvType.CLIENT)
    private record DialogueLayout(int portraitX, int portraitY, int optionsX, int optionsY, int bubbleX, int bubbleY, int maxOptionWidth) {
    }

    @Environment(value=EnvType.CLIENT)
    private static enum DialoguePage {
        ROOT,
        CHAT_SELECT,
        CHAT_RESULT,
        TOURNEY_SELECT,
        QUEST_OFFER,
        PLEDGE_PATH,
        PLEDGE_REWARD_SELECT,
        REVOKE_PLEDGE_CONFIRM,
        VOYAGE_SELECT,
        BLACKSMITH_UPGRADE_SELECT,
        ROBBER_AMBUSH,
        QUEST_BANDIT_CAMP,
        QUEST_BANDIT_CAMP_RESULT,
        LOCKPICKING_CAUGHT,
        GATE_WARNING;

    }

    @Environment(value=EnvType.CLIENT)
    private record VoyagePageState(List<CastleMapPayload.Entry> destinations, List<VoyageShipType> shipTypes, @Nullable VoyageSelection selection) {
        private boolean canCycleDestination() {
            return this.selection != null && this.destinations.size() > 1;
        }

        private boolean canCycleShipType() {
            return this.selection != null && this.shipTypes.size() > 1;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record BlacksmithUpgradePageState(List<DynamicTroopDefinition> sourceTroops, List<DynamicTroopDefinition> targets, @Nullable BlacksmithUpgradeSelection selection) {
        private boolean canCycleTroops() {
            return this.sourceTroops.size() > 1;
        }

        private boolean canCycleTargets() {
            return this.targets.size() > 1;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record DialogueOption(String label, DialogueOptionAction action, boolean enabled, boolean showDisabledOverlay, @Nullable class_2960 iconTexture, boolean wide, @Nullable HireRequirementTooltip tooltip, @Nullable String actionData) {
        private DialogueOption(String label, DialogueOptionAction action, boolean enabled, boolean showDisabledOverlay, @Nullable class_2960 iconTexture, boolean wide) {
            this(label, action, enabled, showDisabledOverlay, iconTexture, wide, null, null);
        }

        private DialogueOption(String label, DialogueOptionAction action, boolean enabled, boolean showDisabledOverlay, @Nullable class_2960 iconTexture, boolean wide, @Nullable HireRequirementTooltip tooltip) {
            this(label, action, enabled, showDisabledOverlay, iconTexture, wide, tooltip, null);
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record HireRequirementTooltip(List<HireRequirementLine> lines) {
    }

    @Environment(value=EnvType.CLIENT)
    private record BubbleRenderLine(List<BubbleRenderSegment> segments) {
    }

    @Environment(value=EnvType.CLIENT)
    private record BubbleRenderSegment(String text, class_1792 item, int width) {
    }

    @Environment(value=EnvType.CLIENT)
    private record NpcAttributeTooltipLine(@Nullable class_2960 iconTexture, String text, int color, boolean gapBefore) {
    }

    @Environment(value=EnvType.CLIENT)
    private static enum DialogueOptionAction {
        NONE,
        CHAT_OPEN,
        CHAT_TALK,
        CHAT_INTIMIDATE,
        CHAT_CHALLENGE_FOR_TITLE,
        TOURNEY_OPEN,
        TOURNEY_EASY,
        TOURNEY_MEDIUM,
        TOURNEY_HARD,
        CHAT_COMPLIMENT,
        CHAT_FLIRT,
        CHAT_TRY_FOR_BABY,
        CHAT_MARRY,
        CHAT_DIVORCE,
        CHAT_TAKE_TO_PARTY,
        CHAT_ASK_TO_LEAVE,
        QUEST,
        QUEST_NEGOTIATE,
        QUEST_ACCEPT,
        QUEST_REJECT,
        QUEST_DELIVER,
        VALYRIAN_TARGARYEN_CLAIM,
        VALYRIAN_CONFIRM_IDENTITY,
        VALYRIAN_DELIVER_NEWS,
        VALYRIAN_DELIVER_DRAGON,
        ROBBER_ACCEPT,
        ROBBER_REJECT,
        BANDIT_ATTACK,
        BANDIT_NEGOTIATE,
        LOCKPICKING_LOST,
        LOCKPICKING_INTIMIDATE,
        LOCKPICKING_LIE,
        GATE_WALK_AWAY,
        GATE_BRIBE,
        PLEDGE,
        PLEDGE_KNIGHT,
        PLEDGE_HEDGE_KNIGHT,
        PLEDGE_PRIEST,
        PLEDGE_ROUTE,
        PLEDGE_COMPLETE_LORD_SIGIL,
        PLEDGE_CREATE_SIGIL,
        REVOKE_PLEDGE,
        REVOKE_PLEDGE_CONFIRM,
        VOYAGE,
        VOYAGE_CONFIRM,
        BLACKSMITH_UPGRADE,
        BLACKSMITH_UPGRADE_CONFIRM,
        TRADE,
        RETURN,
        KNIGHTHOOD,
        HIRE,
        SETTLER_ACCEPT,
        SETTLER_REJECT,
        EXIT;

    }

    @Environment(value=EnvType.CLIENT)
    private record VoyageSelection(CastleMapPayload.Entry castle, VoyageShipType shipType, int goldCost) {
    }

    @Environment(value=EnvType.CLIENT)
    private record VoyageShipType(String id, String displayName, class_2960 iconTexture, String categoryLabel, @Nullable VesselVariant variant) {
        private VesselVariant variantOrDefault() {
            return this.variant == null ? VesselVariant.SHIP : this.variant;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record BlacksmithUpgradeSelection(DynamicTroopDefinition source, DynamicTroopDefinition target, int goldCost) {
    }

    @Environment(value=EnvType.CLIENT)
    private record BubbleContent(List<BubbleLogicalLine> lines) {
        private static BubbleContent ofText(String text) {
            return BubbleContent.ofLines(List.of(BubbleLogicalLine.of(BubbleToken.text(text))));
        }

        private static BubbleContent ofLines(List<BubbleLogicalLine> lines) {
            return new BubbleContent(List.copyOf(lines));
        }

        private int visibleCharacterCount() {
            int count = 0;
            for (BubbleLogicalLine line : this.lines) {
                count += line.visibleCharacterCount();
            }
            return count;
        }

        private List<BubbleRenderLine> layout(class_310 client, int visibleCharacters) {
            ArrayList<BubbleRenderLine> rendered = new ArrayList<BubbleRenderLine>();
            int[] remaining = new int[]{visibleCharacters};
            for (BubbleLogicalLine line : this.lines) {
                rendered.addAll(line.layout(client, remaining));
                if (remaining[0] > 0) continue;
                break;
            }
            return rendered;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record BubbleToken(@Nullable String text, @Nullable class_1792 item) {
        private static BubbleToken text(String text) {
            return new BubbleToken(text, null);
        }

        private static BubbleToken item(class_1792 item) {
            return new BubbleToken(null, item);
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record BubbleLogicalLine(List<BubbleToken> tokens) {
        private static BubbleLogicalLine of(BubbleToken ... tokens) {
            return new BubbleLogicalLine(List.of(tokens));
        }

        private int visibleCharacterCount() {
            int count = 0;
            for (BubbleToken token : this.tokens) {
                if (token.text() == null) continue;
                count += token.text().length();
            }
            return count;
        }

        private List<BubbleRenderLine> layout(class_310 client, int[] remainingCharacters) {
            ArrayList<BubbleRenderLine> lines = new ArrayList<BubbleRenderLine>();
            MutableBubbleRenderLine current = new MutableBubbleRenderLine();
            for (BubbleToken token : this.tokens) {
                if (token.item() != null) {
                    int itemWidth = 12;
                    if (current.width + itemWidth > 224 && !current.segments.isEmpty()) {
                        lines.add(current.freeze());
                        current = new MutableBubbleRenderLine();
                    }
                    current.addItem(token.item(), itemWidth);
                    continue;
                }
                String text = token.text();
                int visible = Math.min(text.length(), remainingCharacters[0]);
                String clipped = text.substring(0, visible);
                remainingCharacters[0] = remainingCharacters[0] - visible;
                for (int index = 0; index < clipped.length(); ++index) {
                    String piece = String.valueOf(clipped.charAt(index));
                    int width = client.field_1772.method_1727(piece);
                    if (current.width + width > 224 && !current.segments.isEmpty()) {
                        lines.add(current.freeze());
                        current = new MutableBubbleRenderLine();
                        if (" ".equals(piece)) continue;
                    }
                    current.addText(piece, width);
                }
                if (visible >= text.length()) continue;
                break;
            }
            if (!current.segments.isEmpty()) {
                lines.add(current.freeze());
            }
            return lines;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record HireRequirementLine(String text, boolean met, @Nullable class_2960 textureIcon, class_1799 itemIcon) {
    }

    @Environment(value=EnvType.CLIENT)
    private static final class MutableBubbleRenderLine {
        private final List<BubbleRenderSegment> segments = new ArrayList<BubbleRenderSegment>();
        private int width;

        private MutableBubbleRenderLine() {
        }

        private void addText(String text, int segmentWidth) {
            BubbleRenderSegment last;
            if (!this.segments.isEmpty() && (last = this.segments.getLast()).item() == null) {
                this.segments.set(this.segments.size() - 1, new BubbleRenderSegment(last.text() + text, null, last.width() + segmentWidth));
                this.width += segmentWidth;
                return;
            }
            this.segments.add(new BubbleRenderSegment(text, null, segmentWidth));
            this.width += segmentWidth;
        }

        private void addItem(class_1792 item, int segmentWidth) {
            this.segments.add(new BubbleRenderSegment("", item, segmentWidth));
            this.width += segmentWidth;
        }

        private BubbleRenderLine freeze() {
            return new BubbleRenderLine(List.copyOf(this.segments));
        }
    }
}

