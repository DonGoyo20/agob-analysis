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
 *  net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext
 *  net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents
 *  net.minecraft.class_1109
 *  net.minecraft.class_1113
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1531
 *  net.minecraft.class_1675
 *  net.minecraft.class_1920
 *  net.minecraft.class_1921
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2573
 *  net.minecraft.class_2586
 *  net.minecraft.class_268
 *  net.minecraft.class_2680
 *  net.minecraft.class_269
 *  net.minecraft.class_2741
 *  net.minecraft.class_2769
 *  net.minecraft.class_2960
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_3414
 *  net.minecraft.class_3417
 *  net.minecraft.class_3532
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_4608
 *  net.minecraft.class_5498
 *  net.minecraft.class_5575
 *  net.minecraft.class_761
 *  net.minecraft.class_8710
 *  net.minecraft.class_898
 */
package trep.got.client.commander;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1675;
import net.minecraft.class_1920;
import net.minecraft.class_1921;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2573;
import net.minecraft.class_2586;
import net.minecraft.class_268;
import net.minecraft.class_2680;
import net.minecraft.class_269;
import net.minecraft.class_2741;
import net.minecraft.class_2769;
import net.minecraft.class_2960;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3532;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5498;
import net.minecraft.class_5575;
import net.minecraft.class_761;
import net.minecraft.class_8710;
import net.minecraft.class_898;
import trep.got.client.castle.CastleInvasionClientState;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.claim.ClaimOutlineClientState;
import trep.got.client.registry.GotKeyBindings;
import trep.got.client.screen.CastleInvasionScreen;
import trep.got.client.screen.VillageRaidScreen;
import trep.got.client.troop.TroopRosterClientState;
import trep.got.client.village.VillageRaidClientState;
import trep.got.entity.AbstractKingdomHumanoidEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.DynamicTroopEntity;
import trep.got.entity.PartyHorseEntity;
import trep.got.entity.PartyTroopEntity;
import trep.got.entity.WarPlaceholderEntity;
import trep.got.network.ClaimOutlinePayload;
import trep.got.network.RequestCommanderActionPayload;
import trep.got.network.RequestCommanderBlockTargetPayload;
import trep.got.network.RequestCommanderTargetPayload;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotEntities;
import trep.got.troop.CommanderActionType;
import trep.got.troop.DynamicTroopUnitType;
import trep.got.troop.GotTroops;
import trep.got.troop.PartyFormationType;

@Environment(value=EnvType.CLIENT)
public final class CommanderClientState {
    private static final class_2960 WHEEL_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/command/wheel.png");
    private static final class_2960 WHEEL_SELECT_1_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/command/wheel_select_1.png");
    private static final class_2960 WHEEL_SELECT_2_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/command/wheel_select_2.png");
    private static final class_2960 WHEEL_SELECT_3_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/command/wheel_select_3.png");
    private static final class_2960 WHEEL_SELECT_4_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/command/wheel_select_4.png");
    private static final class_2960 WHEEL_SELECT_5_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/command/wheel_select_5.png");
    private static final class_2960 WHEEL_SELECT_6_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/command/wheel_select_6.png");
    private static final class_2960 CHARGE_ICON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/war_icon_charge.png");
    private static final class_2960 MOVE_ICON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/war_icon_move.png");
    private static final class_2960 FORMATION_ICON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/war_icon_formation.png");
    private static final class_2960 RECALL_ICON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/war_icon_recall.png");
    private static final class_2960 FOLLOW_ME_ICON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/war_icon_followme.png");
    private static final class_2960 HALT_ICON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/war_icon_halt.png");
    private static final int WHEEL_TEXTURE_WIDTH = 148;
    private static final int WHEEL_TEXTURE_HEIGHT = 125;
    private static final float WHEEL_BASE_SCALE = 2.0f;
    private static final int WHEEL_MARGIN = 16;
    private static final int OPTION_ICON_BASE_SIZE = 18;
    private static final int OPTION_TEXT_MAX_BASE_WIDTH = 38;
    private static final int HUD_PROMPT_GAP = 12;
    private static final int TIMED_ACTIVE_TICKS = 10;
    private static final float GHOST_BANNER_ALPHA = 0.3f;
    private static final float GHOST_PLACEHOLDER_ALPHA = 0.22f;
    private static final double GHOST_BANNER_REACH = 32.0;
    private static final double COMMANDER_TARGET_REACH = 40.0;
    private static final String COMMANDER_TARGET_TEAM_NAME = "agob_commander_target";
    private static final int TARGET_PROMPT_COLOR = -24159;
    private static final int FORMATION_PROMPT_COLOR = -11413;
    private static final int CONFIRM_PROMPT_COLOR = -7558;
    private static final class_2573 GHOST_BANNER = new class_2573(class_2338.field_10980, class_2246.field_10154.method_9564());
    private static final class_3414[] CONFIRM_SOUNDS = new class_3414[]{class_3417.field_15175, class_3417.field_19152, class_3417.field_14815};
    private static final WheelOption[] WHEEL_OPTIONS = new WheelOption[]{new WheelOption(CommanderActionType.CHARGE, WHEEL_SELECT_1_TEXTURE, 52, 27, new int[]{31, 14, 45, 7, 50, 5, 54, 5, 69, 12, 73, 14, 73, 39, 70, 41, 53, 49, 51, 49, 42, 45, 32, 40, 31, 39}), new WheelOption(CommanderActionType.MOVE, WHEEL_SELECT_2_TEXTURE, 95, 27, new int[]{75, 14, 76, 13, 78, 12, 93, 5, 97, 5, 102, 7, 116, 14, 116, 39, 115, 40, 95, 50, 77, 41, 75, 39}), new WheelOption(CommanderActionType.FORMATION, WHEEL_SELECT_3_TEXTURE, 117, 62, new int[]{94, 75, 96, 51, 117, 40, 118, 40, 123, 42, 137, 49, 138, 50, 138, 74, 137, 75, 123, 82, 118, 84, 115, 84}), new WheelOption(CommanderActionType.RECALL, WHEEL_SELECT_4_TEXTURE, 95, 98, new int[]{75, 87, 77, 85, 91, 78, 94, 77, 97, 77, 108, 82, 116, 86, 116, 110, 102, 117, 97, 119, 93, 119, 78, 112, 76, 111, 75, 110}), new WheelOption(CommanderActionType.FOLLOW_ME, WHEEL_SELECT_5_TEXTURE, 52, 98, new int[]{31, 86, 39, 82, 50, 77, 53, 77, 56, 78, 73, 85, 73, 110, 69, 112, 54, 119, 50, 119, 45, 117, 31, 110}), new WheelOption(CommanderActionType.HALT, WHEEL_SELECT_6_TEXTURE, 30, 62, new int[]{9, 50, 10, 49, 24, 42, 29, 40, 30, 40, 49, 50, 51, 52, 53, 75, 32, 84, 29, 84, 24, 82, 10, 75, 9, 74})};
    private static boolean active;
    private static boolean wheelOpen;
    private static CommanderActionType armedAction;
    private static CommanderActionType timedVisualAction;
    private static int timedVisualTicks;
    private static class_2338 ghostBannerPos;
    private static final ArrayList<WarPlaceholderEntity> ghostPlaceholders;
    private static PartyFormationType formationPreview;
    private static String statusLabel;
    private static int statusTicks;
    private static boolean cursorUnlockedByCommander;
    private static ClaimOutlinePayload.Entry invasionPreviewClaim;
    private static class_1297 lookedTargetEntity;
    private static class_2338 lookedTargetBlock;
    private static ClaimOutlinePayload.Entry lookedTargetClaim;
    private static class_1297 glowingTargetEntity;
    private static boolean glowingTargetWasGlowing;
    private static String glowingTargetPreviousTeamName;

    private CommanderClientState() {
    }

    public static void initialize() {
        ClientTickEvents.END_CLIENT_TICK.register(CommanderClientState::tick);
        HudRenderCallback.EVENT.register((context, tickDelta) -> CommanderClientState.renderHud(context));
        WorldRenderEvents.AFTER_TRANSLUCENT.register(CommanderClientState::renderGhostBanner);
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> CommanderClientState.clear());
    }

    public static boolean isActive() {
        return active;
    }

    public static void close() {
        CommanderClientState.clear();
    }

    private static void tick(class_310 client) {
        while (GotKeyBindings.COMMANDER_MODE.method_1436()) {
            active = !active;
            CommanderClientState.playCommanderToggleSound(client);
            if (active) {
                wheelOpen = true;
                CommanderClientState.unlockCursor(client);
                continue;
            }
            CommanderClientState.clear();
        }
        if (!active || client.field_1724 == null || client.field_1687 == null || client.field_1755 != null) {
            if (lookedTargetEntity != null || lookedTargetBlock != null || glowingTargetEntity != null) {
                lookedTargetEntity = null;
                lookedTargetBlock = null;
                lookedTargetClaim = null;
                CommanderClientState.clearTargetGlow(client);
            }
            return;
        }
        if (wheelOpen) {
            CommanderClientState.unlockCursor(client);
        }
        if (!CommanderClientState.canTargetEntityOrGate(armedAction)) {
            lookedTargetEntity = null;
            lookedTargetBlock = null;
            lookedTargetClaim = null;
            CommanderClientState.clearTargetGlow(client);
        } else {
            CommanderClientState.updateLookTarget(client);
        }
        if (timedVisualTicks > 0 && --timedVisualTicks <= 0) {
            timedVisualAction = null;
        }
        if (statusTicks > 0 && statusTicks != Integer.MAX_VALUE && --statusTicks <= 0) {
            statusLabel = "";
        }
        ghostBannerPos = CommanderClientState.showsGhostPreview(armedAction) ? CommanderClientState.resolveGhostBannerPos(client) : null;
        ClaimOutlinePayload.Entry entry = invasionPreviewClaim = ghostBannerPos != null ? ClaimOutlineClientState.getClaimAt(ghostBannerPos) : null;
        while (GotKeyBindings.COMMAND_CHARGE.method_1436()) {
            CommanderClientState.handleActionKey(client, CommanderActionType.CHARGE);
        }
        while (GotKeyBindings.COMMAND_MOVE.method_1436()) {
            CommanderClientState.handleActionKey(client, CommanderActionType.MOVE);
        }
        while (GotKeyBindings.COMMAND_FORMATION.method_1436()) {
            CommanderClientState.handleActionKey(client, CommanderActionType.FORMATION);
        }
        while (GotKeyBindings.COMMAND_RECALL.method_1436()) {
            CommanderClientState.handleActionKey(client, CommanderActionType.RECALL);
        }
        while (GotKeyBindings.COMMAND_FOLLOW_ME.method_1436()) {
            CommanderClientState.handleActionKey(client, CommanderActionType.FOLLOW_ME);
        }
        while (GotKeyBindings.COMMAND_HALT.method_1436()) {
            CommanderClientState.handleActionKey(client, CommanderActionType.HALT);
        }
    }

    private static void handleActionKey(class_310 client, CommanderActionType action) {
        if (action == CommanderActionType.RECALL && timedVisualAction == action && timedVisualTicks > 0) {
            return;
        }
        switch (action) {
            case CHARGE: 
            case MOVE: {
                CommanderClientState.armAction(client, action);
                break;
            }
            case FORMATION: {
                CommanderClientState.cycleFormation(client);
                break;
            }
            case FOLLOW_ME: 
            case HALT: {
                CommanderClientState.confirmImmediateAction(client, action);
                break;
            }
            case RECALL: {
                ClientPlayNetworking.send((class_8710)new RequestCommanderActionPayload(action.id(), true, class_2338.field_10980, formationPreview.id()));
                CommanderClientState.closeWheel(client);
                timedVisualAction = action;
                timedVisualTicks = 10;
                armedAction = null;
                ghostBannerPos = null;
                statusLabel = "Troops recalled to player";
                statusTicks = 10;
                CommanderClientState.playConfirmSound(client);
            }
        }
    }

    private static void armAction(class_310 client, CommanderActionType action) {
        armedAction = action;
        statusLabel = action.displayName();
        statusTicks = Integer.MAX_VALUE;
        CommanderClientState.closeWheel(client);
        CommanderClientState.playConfirmSound(client);
    }

    private static void cycleFormation(class_310 client) {
        formationPreview = formationPreview.next();
        armedAction = null;
        ghostBannerPos = null;
        statusLabel = formationPreview.displayName();
        statusTicks = Integer.MAX_VALUE;
        wheelOpen = true;
        CommanderClientState.unlockCursor(client);
        CommanderClientState.playConfirmSound(client);
    }

    private static class_2338 resolveGhostBannerPos(class_310 client) {
        class_3965 blockHitResult;
        class_1297 cameraEntity = client.method_1560();
        if (cameraEntity == null) {
            return null;
        }
        class_239 hitResult = cameraEntity.method_5745(32.0, 1.0f, false);
        if (!(hitResult instanceof class_3965) || (blockHitResult = (class_3965)hitResult).method_17783() != class_239.class_240.field_1332) {
            return null;
        }
        class_2338 basePos = blockHitResult.method_17777();
        class_2338 bannerPos = basePos.method_10084();
        if (client.field_1687 == null) {
            return null;
        }
        if (client.field_1687.method_8320(basePos).method_26220((class_1922)client.field_1687, basePos).method_1110()) {
            return null;
        }
        if (!client.field_1687.method_8320(bannerPos).method_26215() || !client.field_1687.method_8316(bannerPos).method_15769()) {
            return null;
        }
        return bannerPos.method_10062();
    }

    private static void renderHud(class_332 context) {
        int promptY;
        class_310 client = class_310.method_1551();
        if (!active || client.field_1724 == null || client.field_1690.method_31044() == class_5498.field_26666) {
            return;
        }
        class_327 textRenderer = client.field_1772;
        WheelLayout layout = CommanderClientState.wheelLayout(context);
        if (wheelOpen) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            WheelOption hovered = CommanderClientState.hoveredWheelOption(client, layout);
            WheelOption highlighted = hovered != null ? hovered : CommanderClientState.optionForAction(armedAction != null ? armedAction : timedVisualAction);
            CommanderClientState.drawScaledTexture(context, WHEEL_TEXTURE, layout.x(), layout.y(), layout.scale(), 148, 125);
            if (highlighted != null) {
                CommanderClientState.drawScaledTexture(context, highlighted.hoverTexture(), layout.x(), layout.y(), layout.scale(), 148, 125);
            }
            for (WheelOption option : WHEEL_OPTIONS) {
                CommanderClientState.renderWheelOption(context, textRenderer, layout, option, option == highlighted);
            }
            RenderSystem.disableBlend();
        }
        int n = promptY = wheelOpen ? layout.y() + layout.height() + 12 : context.method_51443() / 2 + 48;
        if (!statusLabel.isBlank()) {
            context.method_27534(textRenderer, (class_2561)class_2561.method_43470((String)statusLabel), context.method_51421() / 2, promptY, -7558);
            promptY += 12;
        }
        if (armedAction != null && armedAction != CommanderActionType.RECALL) {
            context.method_27534(textRenderer, (class_2561)class_2561.method_43470((String)"RIGHT CLICK TO CONFIRM"), context.method_51421() / 2, promptY, -11413);
            promptY += 12;
        }
        if (CommanderClientState.canTargetEntityOrGate(armedAction) && (lookedTargetEntity != null || lookedTargetBlock != null)) {
            context.method_27534(textRenderer, (class_2561)class_2561.method_43470((String)(lookedTargetBlock != null ? "Target Gate" : "Target")), context.method_51421() / 2, promptY, -24159);
        }
    }

    private static void renderWheelOption(class_332 context, class_327 textRenderer, WheelLayout layout, WheelOption option, boolean highlighted) {
        int centerX = layout.x() + Math.round((float)option.centerX() * layout.scale());
        int centerY = layout.y() + Math.round((float)option.centerY() * layout.scale());
        int iconSize = Math.max(18, Math.round(18.0f * layout.scale()));
        int iconY = centerY - Math.round(15.0f * layout.scale());
        CommanderClientState.drawScaledTexture(context, CommanderClientState.iconTextureFor(option.action()), centerX - iconSize / 2, iconY, (float)iconSize / 32.0f, 32, 32);
        int textColor = highlighted ? -1 : -1187133;
        CommanderClientState.drawCenteredScaledText(context, textRenderer, option.action().displayName(), centerX, centerY + Math.round(5.0f * layout.scale()), 38.0f * layout.scale(), textColor, 0.72f * layout.scale());
    }

    private static void drawCenteredScaledText(class_332 context, class_327 textRenderer, String text, int centerX, int y, float maxWidth, int color, float preferredScale) {
        int width = Math.max(1, textRenderer.method_1727(text));
        float scale = Math.min(preferredScale, maxWidth / (float)width);
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)centerX, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)text), -width / 2, 0, color);
        context.method_51448().method_22909();
    }

    private static void drawScaledTexture(class_332 context, class_2960 texture, int x, int y, float scale, int textureWidth, int textureHeight) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, textureWidth, textureHeight, textureWidth, textureHeight);
        context.method_51448().method_22909();
    }

    private static void renderGhostBanner(WorldRenderContext context) {
        class_310 client = class_310.method_1551();
        if (!active || client.field_1724 == null || client.field_1687 == null) {
            return;
        }
        class_4587 matrices = context.matrixStack();
        if (matrices == null) {
            return;
        }
        class_4597.class_4598 consumers = client.method_22940().method_23000();
        RenderSystem.enableBlend();
        if (ghostBannerPos != null) {
            AlphaVertexConsumerProvider alphaConsumers = new AlphaVertexConsumerProvider((class_4597)consumers, 0.3f);
            matrices.method_22903();
            matrices.method_22904((double)ghostBannerPos.method_10263() - context.camera().method_19326().field_1352, (double)ghostBannerPos.method_10264() - context.camera().method_19326().field_1351, (double)ghostBannerPos.method_10260() - context.camera().method_19326().field_1350);
            GHOST_BANNER.method_31664((class_2680)class_2246.field_10154.method_9564().method_11657((class_2769)class_2741.field_12532, (Comparable)Integer.valueOf(CommanderClientState.bannerRotation(client.field_1724.method_36454()))));
            client.method_31975().method_23077((class_2586)GHOST_BANNER, matrices, (class_4597)alphaConsumers, 0xF000F0, class_4608.field_21444);
            matrices.method_22909();
            CommanderClientState.renderGhostPlaceholders(client, context, matrices, consumers);
        } else {
            ghostPlaceholders.clear();
        }
        CommanderClientState.renderGateTargetOutline(client, context, matrices, consumers);
        RenderSystem.disableBlend();
        consumers.method_22993();
    }

    private static void renderGateTargetOutline(class_310 client, WorldRenderContext context, class_4587 matrices, class_4597.class_4598 consumers) {
        if (lookedTargetBlock == null || client.field_1687 == null) {
            return;
        }
        class_4588 lines = consumers.getBuffer(class_1921.method_23594());
        matrices.method_22903();
        matrices.method_22904(-context.camera().method_19326().field_1352, -context.camera().method_19326().field_1351, -context.camera().method_19326().field_1350);
        class_761.method_22982((class_4587)matrices, (class_4588)lines, (class_238)new class_238(lookedTargetBlock), (float)0.95f, (float)0.25f, (float)0.15f, (float)0.95f);
        matrices.method_22909();
    }

    private static void renderGhostPlaceholders(class_310 client, WorldRenderContext context, class_4587 matrices, class_4597.class_4598 consumers) {
        if (ghostBannerPos == null || client.field_1687 == null || client.field_1724 == null) {
            ghostPlaceholders.clear();
            return;
        }
        List<class_2338> previewSlots = CommanderClientState.previewSlots(client);
        if (previewSlots.isEmpty()) {
            ghostPlaceholders.clear();
            return;
        }
        while (ghostPlaceholders.size() < previewSlots.size()) {
            ghostPlaceholders.add(new WarPlaceholderEntity((class_1299<? extends DynamicTroopEntity>)GotEntities.WAR_PLACEHOLDER, (class_1937)client.field_1687));
        }
        if (ghostPlaceholders.size() > previewSlots.size()) {
            ghostPlaceholders.subList(previewSlots.size(), ghostPlaceholders.size()).clear();
        }
        class_898 renderDispatcher = client.method_1561();
        AlphaVertexConsumerProvider alphaConsumers = new AlphaVertexConsumerProvider((class_4597)consumers, 0.22f);
        float previewYaw = CommanderClientState.quantizedPreviewYaw(client.field_1724.method_36454());
        for (int index = 0; index < previewSlots.size(); ++index) {
            class_2338 slot = previewSlots.get(index);
            WarPlaceholderEntity placeholder = ghostPlaceholders.get(index);
            double renderX = (double)slot.method_10263() + 0.5;
            double renderY = slot.method_10264();
            double renderZ = (double)slot.method_10260() + 0.5;
            placeholder.method_5808(renderX, renderY, renderZ, previewYaw, 0.0f);
            placeholder.field_6014 = renderX;
            placeholder.field_6036 = renderY;
            placeholder.field_5969 = renderZ;
            placeholder.method_5636(previewYaw);
            placeholder.method_5847(previewYaw);
            placeholder.field_6220 = previewYaw;
            placeholder.field_6259 = previewYaw;
            placeholder.field_5982 = previewYaw;
            placeholder.method_36457(0.0f);
            placeholder.field_6004 = 0.0f;
            placeholder.field_6251 = 0.0f;
            placeholder.field_6279 = 0;
            placeholder.field_42108.method_48567(0.0f);
            placeholder.field_6012 = client.field_1724.field_6012;
            placeholder.setPreviewMode(CommanderClientState.previewModeForClaim(invasionPreviewClaim));
            matrices.method_22903();
            renderDispatcher.method_3954((class_1297)placeholder, renderX - context.camera().method_19326().field_1352, renderY - context.camera().method_19326().field_1351, renderZ - context.camera().method_19326().field_1350, placeholder.method_36454(), context.tickCounter().method_60637(false), matrices, (class_4597)alphaConsumers, class_761.method_23794((class_1920)client.field_1687, (class_2338)slot));
            matrices.method_22909();
        }
    }

    private static int bannerRotation(float yaw) {
        return Math.floorMod(class_3532.method_15375((float)((yaw + 180.0f) * 16.0f / 360.0f + 0.5f)), 16);
    }

    private static float quantizedPreviewYaw(float yaw) {
        return (float)Math.round(yaw / 45.0f) * 45.0f;
    }

    private static List<class_2338> previewSlots(class_310 client) {
        List unitTypes;
        if (client.field_1687 == null || client.field_1724 == null || ghostBannerPos == null) {
            return List.of();
        }
        UUID ownerUuid = client.field_1724.method_5667();
        class_238 searchBox = client.field_1724.method_5829().method_1009(512.0, 128.0, 512.0);
        ArrayList deployedTroops = new ArrayList(client.field_1687.method_18023(class_5575.method_31795(PartyTroopEntity.class), searchBox, troop -> troop.method_5805() && ownerUuid.equals(troop.getOwnerUuid())));
        ArrayList companions = new ArrayList(client.field_1687.method_18023(class_5575.method_31795(AbstractKingdomNpcEntity.class), searchBox, npc -> npc.method_5805() && npc.isMarriagePartyCompanion() && ownerUuid.toString().equals(npc.getMarriedPlayerUuid())));
        if (deployedTroops.isEmpty()) {
            deployedTroops = new ArrayList(client.field_1687.method_18023(class_5575.method_31795(PartyTroopEntity.class), searchBox, class_1309::method_5805));
        }
        if (!deployedTroops.isEmpty() || !companions.isEmpty()) {
            unitTypes = deployedTroops.stream().sorted(Comparator.comparingInt(troop -> troop.getTroopDefinition() == null ? Integer.MAX_VALUE : GotTroops.formationGroupIndex(troop.getTroopDefinition().unitType())).thenComparingInt(PartyTroopEntity::getUnitId)).map(troop -> troop.getTroopDefinition() == null ? DynamicTroopUnitType.MELEE : troop.getTroopDefinition().unitType()).collect(Collectors.toCollection(ArrayList::new));
            for (int index = 0; index < companions.size(); ++index) {
                unitTypes.add(DynamicTroopUnitType.MELEE);
            }
        } else {
            unitTypes = CommanderClientState.unitTypesFromRoster();
        }
        if (unitTypes.isEmpty()) {
            return List.of();
        }
        return GotTroops.calculateFormationSlotsForUnitTypes((class_1937)client.field_1687, class_243.method_24955((class_2382)ghostBannerPos), client.field_1724.method_36454(), unitTypes, formationPreview, false);
    }

    private static List<DynamicTroopUnitType> unitTypesFromRoster() {
        ArrayList<DynamicTroopUnitType> unitTypes = new ArrayList<DynamicTroopUnitType>();
        CommanderClientState.addUnitTypes(unitTypes, DynamicTroopUnitType.MELEE, TroopRosterClientState.getCountByUnitType(DynamicTroopUnitType.MELEE));
        CommanderClientState.addUnitTypes(unitTypes, DynamicTroopUnitType.ARCHER, TroopRosterClientState.getCountByUnitType(DynamicTroopUnitType.ARCHER));
        CommanderClientState.addUnitTypes(unitTypes, DynamicTroopUnitType.CAVALRY, TroopRosterClientState.getCountByUnitType(DynamicTroopUnitType.CAVALRY));
        return unitTypes;
    }

    private static void addUnitTypes(List<DynamicTroopUnitType> unitTypes, DynamicTroopUnitType unitType, int count) {
        for (int index = 0; index < count; ++index) {
            unitTypes.add(unitType);
        }
    }

    private static void clear() {
        class_310 client = class_310.method_1551();
        active = false;
        wheelOpen = false;
        armedAction = null;
        timedVisualAction = null;
        timedVisualTicks = 0;
        ghostBannerPos = null;
        ghostPlaceholders.clear();
        formationPreview = PartyFormationType.LINE;
        statusLabel = "";
        statusTicks = 0;
        invasionPreviewClaim = null;
        lookedTargetEntity = null;
        lookedTargetBlock = null;
        lookedTargetClaim = null;
        CommanderClientState.clearTargetGlow(client);
        if (cursorUnlockedByCommander && client.field_1755 == null) {
            client.field_1729.method_1612();
        }
        cursorUnlockedByCommander = false;
    }

    public static boolean handleRightClick() {
        class_310 client = class_310.method_1551();
        if (!active || client.field_1724 == null || client.field_1687 == null || client.field_1755 != null) {
            return false;
        }
        if (armedAction == null) {
            return wheelOpen;
        }
        if (CommanderClientState.canTargetEntityOrGate(armedAction) && (lookedTargetEntity != null || lookedTargetBlock != null)) {
            return CommanderClientState.confirmLookTarget(client);
        }
        if (CommanderClientState.usesGhostLocation(armedAction)) {
            CommanderClientState.confirmLocationAction(client, armedAction);
            return true;
        }
        return true;
    }

    public static boolean handleLeftClick(double rawMouseX, double rawMouseY) {
        class_310 client = class_310.method_1551();
        if (!active || !wheelOpen || client.field_1724 == null || client.field_1687 == null || client.field_1755 != null) {
            return false;
        }
        WheelLayout layout = CommanderClientState.wheelLayout(client.method_22683().method_4486(), client.method_22683().method_4502());
        WheelOption option = CommanderClientState.hoveredWheelOption(client, layout, rawMouseX, rawMouseY);
        if (option != null) {
            CommanderClientState.handleActionKey(client, option.action());
        }
        return true;
    }

    private static boolean confirmLookTarget(class_310 client) {
        class_1297 target = lookedTargetEntity;
        class_2338 targetBlock = lookedTargetBlock;
        ClaimOutlinePayload.Entry claim = lookedTargetClaim;
        Runnable confirm = () -> {
            if (client.field_1724 == null) {
                return;
            }
            if (target != null) {
                ClientPlayNetworking.send((class_8710)new RequestCommanderTargetPayload(target.method_5628(), client.field_1724.method_36454()));
                statusLabel = "Target assigned";
            } else if (targetBlock != null) {
                ClientPlayNetworking.send((class_8710)new RequestCommanderBlockTargetPayload(targetBlock, client.field_1724.method_36454()));
                statusLabel = "Gate target assigned";
            }
            timedVisualAction = CommanderActionType.CHARGE;
            timedVisualTicks = 10;
            armedAction = null;
            ghostBannerPos = null;
            statusTicks = 10;
            CommanderClientState.playConfirmSound(client);
        };
        if (claim != null && !CommanderClientState.isActiveInvasionTarget(claim)) {
            CommanderClientState.openInvasionScreen(claim, client.field_1755, confirm);
            return true;
        }
        confirm.run();
        return true;
    }

    private static void confirmLocationAction(class_310 client, CommanderActionType action) {
        if (ghostBannerPos == null) {
            statusLabel = "Aim at valid ground";
            statusTicks = 10;
            return;
        }
        if (action != CommanderActionType.FORMATION && invasionPreviewClaim != null && !CommanderClientState.isActiveInvasionTarget(invasionPreviewClaim)) {
            CommanderClientState.openInvasionScreen(invasionPreviewClaim, client.field_1755, () -> CommanderClientState.sendLocationAction(client, action, ghostBannerPos));
            return;
        }
        CommanderClientState.sendLocationAction(client, action, ghostBannerPos);
    }

    private static void sendLocationAction(class_310 client, CommanderActionType action, class_2338 targetPos) {
        ClientPlayNetworking.send((class_8710)new RequestCommanderActionPayload(action.id(), true, targetPos, formationPreview.id()));
        CommanderClientState.completeAction(client, action, action == CommanderActionType.FORMATION ? formationPreview.displayName() : action.displayName());
    }

    private static void confirmImmediateAction(class_310 client, CommanderActionType action) {
        ClientPlayNetworking.send((class_8710)new RequestCommanderActionPayload(action.id(), true, class_2338.field_10980, formationPreview.id()));
        CommanderClientState.completeAction(client, action, action == CommanderActionType.FOLLOW_ME ? "Troops following" : "Troops halted");
    }

    private static void completeAction(class_310 client, CommanderActionType action, String label) {
        CommanderClientState.closeWheel(client);
        timedVisualAction = action;
        timedVisualTicks = 10;
        armedAction = null;
        ghostBannerPos = null;
        statusLabel = label;
        statusTicks = 10;
        CommanderClientState.playConfirmSound(client);
    }

    private static void closeWheel(class_310 client) {
        wheelOpen = false;
        if (cursorUnlockedByCommander && client != null && client.field_1755 == null) {
            client.field_1729.method_1612();
        }
        cursorUnlockedByCommander = false;
    }

    private static WheelLayout wheelLayout(class_332 context) {
        return CommanderClientState.wheelLayout(context.method_51421(), context.method_51443());
    }

    private static WheelLayout wheelLayout(int screenWidth, int screenHeight) {
        float maxScaleX = ((float)screenWidth - 32.0f) / 148.0f;
        float maxScaleY = ((float)screenHeight - 32.0f - 36.0f) / 125.0f;
        float scale = class_3532.method_15363((float)Math.min(2.0f, Math.min(maxScaleX, maxScaleY)), (float)1.0f, (float)2.0f);
        int width = Math.round(148.0f * scale);
        int height = Math.round(125.0f * scale);
        return new WheelLayout((screenWidth - width) / 2, Math.max(16, (screenHeight - height) / 2 - 18), width, height, scale);
    }

    private static WheelOption hoveredWheelOption(class_310 client, WheelLayout layout) {
        return CommanderClientState.hoveredWheelOption(client, layout, client.field_1729.method_1603(), client.field_1729.method_1604());
    }

    private static WheelOption hoveredWheelOption(class_310 client, WheelLayout layout, double rawMouseX, double rawMouseY) {
        double mouseX = CommanderClientState.scaledMouseX(client, rawMouseX);
        double mouseY = CommanderClientState.scaledMouseY(client, rawMouseY);
        double localX = (mouseX - (double)layout.x()) / (double)layout.scale();
        double localY = (mouseY - (double)layout.y()) / (double)layout.scale();
        if (localX < 0.0 || localY < 0.0 || localX >= 148.0 || localY >= 125.0) {
            return null;
        }
        for (WheelOption option : WHEEL_OPTIONS) {
            if (!option.contains(localX, localY)) continue;
            return option;
        }
        return null;
    }

    private static double scaledMouseX(class_310 client, double rawMouseX) {
        return rawMouseX * (double)client.method_22683().method_4486() / (double)client.method_22683().method_4480();
    }

    private static double scaledMouseY(class_310 client, double rawMouseY) {
        return rawMouseY * (double)client.method_22683().method_4502() / (double)client.method_22683().method_4507();
    }

    private static WheelOption optionForAction(CommanderActionType action) {
        if (action == null) {
            return null;
        }
        for (WheelOption option : WHEEL_OPTIONS) {
            if (option.action() != action) continue;
            return option;
        }
        return null;
    }

    private static boolean usesGhostLocation(CommanderActionType action) {
        return action == CommanderActionType.CHARGE || action == CommanderActionType.MOVE;
    }

    private static boolean showsGhostPreview(CommanderActionType action) {
        return action == CommanderActionType.CHARGE || action == CommanderActionType.MOVE;
    }

    private static boolean canTargetEntityOrGate(CommanderActionType action) {
        return action == CommanderActionType.CHARGE;
    }

    private static void unlockCursor(class_310 client) {
        if (client != null && !cursorUnlockedByCommander) {
            client.field_1729.method_1610();
            cursorUnlockedByCommander = true;
        }
    }

    private static void updateLookTarget(class_310 client) {
        class_1297 newTarget = CommanderClientState.resolveLookTarget(client);
        class_2338 newBlockTarget = newTarget == null ? CommanderClientState.resolveGateTarget(client) : null;
        ClaimOutlinePayload.Entry claim = newTarget != null ? CommanderClientState.resolveTargetClaim(newTarget) : CommanderClientState.resolveTargetClaim(newBlockTarget);
        lookedTargetEntity = newTarget;
        lookedTargetBlock = newBlockTarget;
        lookedTargetClaim = claim;
        CommanderClientState.updateTargetGlow(client, newTarget);
    }

    private static class_1297 resolveLookTarget(class_310 client) {
        class_238 searchBox;
        class_3966 entityHitResult;
        if (client.field_1724 == null || client.field_1687 == null) {
            return null;
        }
        class_243 start = client.field_1724.method_5836(1.0f);
        class_243 direction = client.field_1724.method_5828(1.0f);
        class_243 end = start.method_1019(direction.method_1021(40.0));
        class_239 blockHitResult = client.field_1724.method_5745(40.0, 1.0f, false);
        double maxEntityDistanceSq = 1600.0;
        if (blockHitResult != null && blockHitResult.method_17783() == class_239.class_240.field_1332) {
            maxEntityDistanceSq = start.method_1025(blockHitResult.method_17784());
        }
        if ((entityHitResult = class_1675.method_18075((class_1297)client.field_1724, (class_243)start, (class_243)end, (class_238)(searchBox = client.field_1724.method_5829().method_18804(direction.method_1021(40.0)).method_1014(1.5)), entity -> entity.method_5805() && entity != client.field_1724, (double)maxEntityDistanceSq)) == null) {
            return null;
        }
        class_1297 entity2 = entityHitResult.method_17782();
        if (entity2 == null || !entity2.method_5805() || entity2 == client.field_1724 || entity2 instanceof class_1531) {
            return null;
        }
        if (CommanderClientState.isOwnUnitEntity(client, entity2)) {
            return null;
        }
        return entity2;
    }

    private static ClaimOutlinePayload.Entry resolveTargetClaim(class_1297 entity) {
        AbstractKingdomHumanoidEntity kingdomHumanoid;
        if (!(entity instanceof AbstractKingdomHumanoidEntity) || (kingdomHumanoid = (AbstractKingdomHumanoidEntity)entity).got$getCastle().isBlank()) {
            return null;
        }
        ClaimOutlinePayload.Entry claim = ClaimOutlineClientState.getClaimById(kingdomHumanoid.got$getCastle());
        return claim != null ? claim : ClaimOutlineClientState.getClaimAt(entity.method_24515());
    }

    private static ClaimOutlinePayload.Entry resolveTargetClaim(class_2338 pos) {
        return pos == null ? null : ClaimOutlineClientState.getClaimAt(pos);
    }

    private static class_2338 resolveGateTarget(class_310 client) {
        class_3965 blockHitResult;
        class_1297 cameraEntity = client.method_1560();
        if (cameraEntity == null || client.field_1687 == null) {
            return null;
        }
        class_239 hitResult = cameraEntity.method_5745(40.0, 1.0f, false);
        if (!(hitResult instanceof class_3965) || (blockHitResult = (class_3965)hitResult).method_17783() != class_239.class_240.field_1332) {
            return null;
        }
        class_2338 pos = blockHitResult.method_17777();
        return client.field_1687.method_8320(pos).method_27852(GotBlocks.GATE_FENCE) ? pos.method_10062() : null;
    }

    private static boolean isOwnUnitEntity(class_310 client, class_1297 entity) {
        if (client.field_1724 == null) {
            return false;
        }
        UUID playerUuid = client.field_1724.method_5667();
        if (entity instanceof PartyTroopEntity) {
            PartyTroopEntity troop = (PartyTroopEntity)entity;
            return playerUuid.equals(troop.getOwnerUuid());
        }
        if (entity instanceof PartyHorseEntity) {
            PartyHorseEntity horse = (PartyHorseEntity)entity;
            if (playerUuid.equals(horse.method_6139())) {
                return true;
            }
            for (class_1297 passenger : horse.method_5685()) {
                PartyTroopEntity troop;
                if (!(passenger instanceof PartyTroopEntity) || !playerUuid.equals((troop = (PartyTroopEntity)passenger).getOwnerUuid())) continue;
                return true;
            }
            return false;
        }
        if (entity instanceof AbstractKingdomNpcEntity) {
            AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)entity;
            return npc.isMarriagePartyCompanion() && playerUuid.toString().equals(npc.getMarriedPlayerUuid());
        }
        return false;
    }

    private static void updateTargetGlow(class_310 client, class_1297 target) {
        String scoreHolder;
        class_268 previousTeam;
        if (glowingTargetEntity == target) {
            return;
        }
        CommanderClientState.clearTargetGlow(client);
        if (client.field_1687 == null || target == null) {
            return;
        }
        class_269 scoreboard = client.field_1687.method_8428();
        class_268 team = scoreboard.method_1153(COMMANDER_TARGET_TEAM_NAME);
        if (team == null) {
            team = scoreboard.method_1171(COMMANDER_TARGET_TEAM_NAME);
            team.method_1141(class_124.field_1061);
        }
        glowingTargetPreviousTeamName = (previousTeam = scoreboard.method_1164(scoreHolder = target.method_5820())) == null ? null : previousTeam.method_1197();
        glowingTargetWasGlowing = target.method_5851();
        scoreboard.method_1172(scoreHolder, team);
        target.method_5834(true);
        glowingTargetEntity = target;
    }

    private static void clearTargetGlow(class_310 client) {
        class_268 previousTeam;
        if (client == null || client.field_1687 == null || glowingTargetEntity == null) {
            glowingTargetEntity = null;
            glowingTargetWasGlowing = false;
            glowingTargetPreviousTeamName = null;
            return;
        }
        class_269 scoreboard = client.field_1687.method_8428();
        String scoreHolder = glowingTargetEntity.method_5820();
        scoreboard.method_1195(scoreHolder);
        if (glowingTargetPreviousTeamName != null && (previousTeam = scoreboard.method_1153(glowingTargetPreviousTeamName)) != null) {
            scoreboard.method_1172(scoreHolder, previousTeam);
        }
        glowingTargetEntity.method_5834(glowingTargetWasGlowing);
        glowingTargetEntity = null;
        glowingTargetWasGlowing = false;
        glowingTargetPreviousTeamName = null;
    }

    private static WarPlaceholderEntity.PreviewMode previewModeForClaim(ClaimOutlinePayload.Entry claim) {
        if (claim == null) {
            return WarPlaceholderEntity.PreviewMode.NORMAL;
        }
        String playerKingdom = CharacterSelectionClientState.getSelectedKingdomId();
        if (!playerKingdom.isBlank() && playerKingdom.equals(claim.kingdomId())) {
            return WarPlaceholderEntity.PreviewMode.RAID;
        }
        return WarPlaceholderEntity.PreviewMode.INVADE;
    }

    private static boolean isActiveInvasionTarget(ClaimOutlinePayload.Entry claim) {
        if (claim == null) {
            return false;
        }
        if ("village".equals(claim.tier())) {
            return VillageRaidClientState.isActive() && claim.id().equals(VillageRaidClientState.getVillageId());
        }
        return CastleInvasionClientState.isActive() && claim.id().equals(CastleInvasionClientState.getCastleId());
    }

    private static void openInvasionScreen(ClaimOutlinePayload.Entry claim, class_437 parent, Runnable onConfirm) {
        if (claim == null) {
            return;
        }
        if ("village".equals(claim.tier())) {
            VillageRaidScreen.open(claim, parent, onConfirm);
            return;
        }
        CastleInvasionScreen.open(claim, parent, onConfirm);
    }

    private static void playCommanderToggleSound(class_310 client) {
        if (client == null) {
            return;
        }
        client.method_1483().method_4873((class_1113)class_1109.method_4758((class_3414)class_3417.field_22463, (float)1.0f));
    }

    private static void playConfirmSound(class_310 client) {
        if (client == null) {
            return;
        }
        int index = Math.floorMod(System.nanoTime(), CONFIRM_SOUNDS.length);
        client.method_1483().method_4873((class_1113)class_1109.method_4758((class_3414)CONFIRM_SOUNDS[index], (float)1.0f));
    }

    private static class_304 keyBindingFor(CommanderActionType action) {
        return switch (action) {
            default -> throw new MatchException(null, null);
            case CommanderActionType.CHARGE -> GotKeyBindings.COMMAND_CHARGE;
            case CommanderActionType.MOVE -> GotKeyBindings.COMMAND_MOVE;
            case CommanderActionType.FORMATION -> GotKeyBindings.COMMAND_FORMATION;
            case CommanderActionType.RECALL -> GotKeyBindings.COMMAND_RECALL;
            case CommanderActionType.FOLLOW_ME -> GotKeyBindings.COMMAND_FOLLOW_ME;
            case CommanderActionType.HALT -> GotKeyBindings.COMMAND_HALT;
        };
    }

    private static String boundKeyLabel(class_304 keyBinding) {
        String translation = keyBinding.method_16007().getString();
        return translation.length() > 2 ? translation.substring(0, Math.min(3, translation.length())).toUpperCase() : translation.toUpperCase();
    }

    private static class_2960 iconTextureFor(CommanderActionType action) {
        return switch (action) {
            default -> throw new MatchException(null, null);
            case CommanderActionType.CHARGE -> CHARGE_ICON_TEXTURE;
            case CommanderActionType.MOVE -> MOVE_ICON_TEXTURE;
            case CommanderActionType.FORMATION -> FORMATION_ICON_TEXTURE;
            case CommanderActionType.RECALL -> RECALL_ICON_TEXTURE;
            case CommanderActionType.FOLLOW_ME -> FOLLOW_ME_ICON_TEXTURE;
            case CommanderActionType.HALT -> HALT_ICON_TEXTURE;
        };
    }

    static {
        ghostPlaceholders = new ArrayList();
        formationPreview = PartyFormationType.LINE;
        statusLabel = "";
    }

    @Environment(value=EnvType.CLIENT)
    private record WheelLayout(int x, int y, int width, int height, float scale) {
    }

    @Environment(value=EnvType.CLIENT)
    private record WheelOption(CommanderActionType action, class_2960 hoverTexture, int centerX, int centerY, int[] polygon) {
        boolean contains(double x, double y) {
            boolean inside = false;
            int pointCount = this.polygon.length / 2;
            int i = 0;
            int j = pointCount - 1;
            while (i < pointCount) {
                boolean intersects;
                double xi = this.polygon[i * 2];
                double yi = this.polygon[i * 2 + 1];
                double xj = this.polygon[j * 2];
                double yj = this.polygon[j * 2 + 1];
                boolean bl = intersects = yi > y != yj > y && x < (xj - xi) * (y - yi) / (yj - yi) + xi;
                if (intersects) {
                    inside = !inside;
                }
                j = i++;
            }
            return inside;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record AlphaVertexConsumerProvider(class_4597 delegate, float alphaScale) implements class_4597
    {
        public class_4588 getBuffer(class_1921 layer) {
            return new AlphaVertexConsumer(this.delegate.getBuffer(layer), this.alphaScale);
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record AlphaVertexConsumer(class_4588 delegate, float alphaScale) implements class_4588
    {
        public class_4588 method_22912(float x, float y, float z) {
            this.delegate.method_22912(x, y, z);
            return this;
        }

        public class_4588 method_1336(int red, int green, int blue, int alpha) {
            this.delegate.method_1336(red, green, blue, Math.max(0, Math.min(255, Math.round((float)alpha * this.alphaScale))));
            return this;
        }

        public class_4588 method_22913(float u, float v) {
            this.delegate.method_22913(u, v);
            return this;
        }

        public class_4588 method_60796(int u, int v) {
            this.delegate.method_60796(u, v);
            return this;
        }

        public class_4588 method_22921(int u, int v) {
            this.delegate.method_22921(u, v);
            return this;
        }

        public class_4588 method_22914(float x, float y, float z) {
            this.delegate.method_22914(x, y, z);
            return this;
        }
    }
}

