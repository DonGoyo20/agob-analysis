/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_1799
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_437
 *  net.minecraft.class_5250
 *  net.minecraft.class_746
 *  net.minecraft.class_7532
 *  net.minecraft.class_8685
 *  net.minecraft.class_8710
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_5250;
import net.minecraft.class_746;
import net.minecraft.class_7532;
import net.minecraft.class_8685;
import net.minecraft.class_8710;
import org.jetbrains.annotations.Nullable;
import trep.got.castle.CastleTier;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.house.PlayerHouseRenderHelper;
import trep.got.client.map.CastleFastTravelClientState;
import trep.got.client.map.CastleMapClientState;
import trep.got.client.map.CastleTerritoryClientState;
import trep.got.client.map.VillageMapClientState;
import trep.got.client.quest.QuestJournalClientState;
import trep.got.client.registry.GotKeyBindings;
import trep.got.client.util.ClientTextureHelper;
import trep.got.kingdom.Kingdom;
import trep.got.network.CastleMapPayload;
import trep.got.network.RequestCastleScreenPayload;
import trep.got.network.TeleportMapPayload;
import trep.got.network.VillageMapPayload;
import trep.got.quest.QuestTemplate;
import trep.got.quest.QuestTemplateRegistry;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotReligionOptions;
import trep.got.registry.GotVillageResources;
import trep.got.religion.ReligionSelectionOption;
import trep.got.village.resource.VillageResource;
import trep.got.voyage.VoyageLane;
import trep.got.war.LordWarPartyHelper;
import trep.got.world.dimension.GotDimensionKeys;

@Environment(value=EnvType.CLIENT)
public class WorldMapScreen
extends class_437 {
    private static final class_2960 MAP_BACKGROUND = class_2960.method_60655((String)"minecraft", (String)"textures/map/map_background.png");
    private static final class_2960 MAP_BACKGROUND_CHECKERBOARD = class_2960.method_60655((String)"minecraft", (String)"textures/map/map_background_checkerboard.png");
    private static final class_2960 WORLD_MAP_TEXTURE = class_2960.method_60655((String)"agob", (String)"map.png");
    private static final class_2960 ROAD_MAP_TEXTURE = class_2960.method_60655((String)"agob", (String)"map_road.png");
    private static final class_2960 CASTLE_WOOD_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/castle_wood.png");
    private static final class_2960 CASTLE_STONE_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/castle_stone.png");
    private static final class_2960 CASTLE_CAPITAL_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/castle_capital.png");
    private static final class_2960 TOURNEY_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/tourney.png");
    private static final class_2960 HARBOUR_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/anchor.png");
    private static final class_2960 ROAD_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/attributes_riding.png");
    private static final class_2960 WAR_PARTY_TOGGLE_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_blank.png");
    private static final class_2960 MAP_ICON_FRAME = class_2960.method_60655((String)"agob", (String)"textures/ui/icon_map_frame.png");
    private static final class_2960 MAP_ICON_FRAME_ACTIVE = class_2960.method_60655((String)"agob", (String)"textures/ui/icon_frame_active.png");
    private static final class_2960 CASTLE_TOOLTIP_BACKGROUND = class_2960.method_60655((String)"agob", (String)"textures/ui/castle_tooltip/background.png");
    private static final class_2960 CASTLE_TOOLTIP_FRAME = class_2960.method_60655((String)"agob", (String)"textures/ui/castle_tooltip/frame.png");
    private static final class_2960 QUEST_BANNER_ICON = class_2960.method_60655((String)"minecraft", (String)"textures/map/decorations/white_banner.png");
    private static final class_2960 VILLAGE_ICON = class_2960.method_60655((String)"minecraft", (String)"textures/map/decorations/plains_village.png");
    private static final class_2960 FAST_TRAVEL_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/fast_travel.png");
    private static final class_2960 FAST_TRAVEL_HOVER_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/fast_travel_hover.png");
    private static final class_2960 FAST_TRAVEL_LOCKED_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/fast_travel_locked.png");
    private static final class_2960 FAST_TRAVEL_EFFECT_ICON = class_2960.method_60655((String)"minecraft", (String)"textures/mob_effect/speed.png");
    private static final class_2960 FAST_TRAVEL_COOLDOWN_ICON = class_2960.method_60655((String)"minecraft", (String)"textures/mob_effect/slowness.png");
    private static final class_2960 PARTY_BADGE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/badge_background.png");
    private static final class_2960 PARTY_INDICATOR_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/icon_indicator.png");
    private static final int OUTER_MARGIN = 24;
    private static final int FRAME_PADDING = 18;
    private static final int MARKER_SIZE = 3;
    private static final int CASTLE_MARKER_SIZE = 4;
    private static final int QUEST_MARKER_SIZE = 4;
    private static final int VILLAGE_MARKER_SIZE = 3;
    private static final int TOOLTIP_ICON_SIZE = 9;
    private static final int TOOLTIP_PADDING_X = 10;
    private static final int TOOLTIP_PADDING_Y = 10;
    private static final int TOOLTIP_MIN_WIDTH = 118;
    private static final int TOOLTIP_MAX_WIDTH = 172;
    private static final int CASTLE_TOOLTIP_GRID = 5;
    private static final float CASTLE_TOOLTIP_PANEL_SCALE = 0.5f;
    private static final int FAST_TRAVEL_WIDTH = 72;
    private static final int FAST_TRAVEL_HEIGHT = 24;
    private static final int FAST_TRAVEL_MARGIN_TOP = 6;
    private static final int BUTTON_ICON_SIZE = 8;
    private static final int BUTTON_ICON_GAP = 4;
    private static final int PARTY_BADGE_SIZE = 14;
    private static final int PARTY_SIGIL_SIZE = 10;
    private static final int PARTY_INDICATOR_SIZE = 9;
    private static final float FAST_TRAVEL_CONTENT_SCALE = 0.9f;
    private static final float MIN_ZOOM = 1.0f;
    private static final float MAX_ZOOM = 6.0f;
    private static final float ZOOM_STEP = 0.25f;
    private static final int MAP_TOGGLE_SIZE = 19;
    private static final int MAP_TOGGLE_ICON_SIZE = 11;
    private static final int MAP_TOGGLE_GAP = 6;
    private static float cachedZoom = 1.0f;
    private static float cachedViewOriginX = 0.0f;
    private static float cachedViewOriginY = 0.0f;
    private static boolean cachedShowSeaRoutes = true;
    private static boolean cachedShowRoads = true;
    private static boolean cachedShowVillages = true;
    private static boolean cachedShowWarParties = true;
    private static final Map<class_2960, ClientTextureHelper.TextureSize> TEXTURE_SIZE_CACHE = new HashMap<class_2960, ClientTextureHelper.TextureSize>();
    private int frameX;
    private int frameY;
    private int frameSize;
    private int mapX;
    private int mapY;
    private int mapSize;
    private float zoom = cachedZoom;
    private float viewOriginX;
    private float viewOriginY;
    private boolean showSeaRoutes = cachedShowSeaRoutes;
    private boolean showRoads = cachedShowRoads;
    private boolean showVillages = cachedShowVillages;
    private boolean showWarParties = cachedShowWarParties;
    private boolean rightDragging;
    private double lastDragMouseX;
    private double lastDragMouseY;
    private int lastMouseX;
    private int lastMouseY;
    private CastleMapPayload.Entry lockedCastle;
    private TooltipLayout lockedTooltipLayout;
    private VillageMapPayload.Entry lockedVillage;
    private VillageTooltipLayout lockedVillageTooltipLayout;

    public WorldMapScreen() {
        super((class_2561)class_2561.method_43470((String)"World Map"));
    }

    public static void open() {
        class_310 client = class_310.method_1551();
        client.method_1507((class_437)new WorldMapScreen());
    }

    protected void method_57734(float delta) {
    }

    protected void method_25426() {
        super.method_25426();
        this.viewOriginX = cachedViewOriginX;
        this.viewOriginY = cachedViewOriginY;
        this.clampView();
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        VillageTooltipLayout hoveredVillageTooltipLayout;
        QuestTooltipLayout hoveredQuestTooltipLayout;
        WarPartyTooltipLayout hoveredWarPartyTooltipLayout;
        TooltipLayout hoveredTooltipLayout;
        this.lastMouseX = mouseX;
        this.lastMouseY = mouseY;
        this.frameSize = Math.min(this.field_22789, this.field_22790) - 48;
        this.frameX = (this.field_22789 - this.frameSize) / 2;
        this.frameY = (this.field_22790 - this.frameSize) / 2;
        this.mapX = this.frameX + 18;
        this.mapY = this.frameY + 18;
        this.mapSize = this.frameSize - 36;
        context.method_25290(MAP_BACKGROUND, this.frameX, this.frameY, 0.0f, 0.0f, this.frameSize, this.frameSize, this.frameSize, this.frameSize);
        context.method_25290(MAP_BACKGROUND_CHECKERBOARD, this.mapX, this.mapY, 0.0f, 0.0f, this.mapSize, this.mapSize, this.mapSize, this.mapSize);
        this.clampView();
        float viewportSize = this.getViewportSize();
        context.method_25293(WORLD_MAP_TEXTURE, this.mapX, this.mapY, this.mapSize, this.mapSize, this.viewOriginX, this.viewOriginY, class_3532.method_15386((float)viewportSize), class_3532.method_15386((float)viewportSize), 3000, 3000);
        class_2960 territoryTexture = CastleTerritoryClientState.getTexture();
        if (territoryTexture != null) {
            context.method_25293(territoryTexture, this.mapX, this.mapY, this.mapSize, this.mapSize, this.viewOriginX, this.viewOriginY, class_3532.method_15386((float)viewportSize), class_3532.method_15386((float)viewportSize), 3000, 3000);
        }
        if (this.showRoads) {
            context.method_25293(ROAD_MAP_TEXTURE, this.mapX, this.mapY, this.mapSize, this.mapSize, this.viewOriginX, this.viewOriginY, class_3532.method_15386((float)viewportSize), class_3532.method_15386((float)viewportSize), 3000, 3000);
        }
        if (this.showSeaRoutes) {
            this.drawVoyageLanes(context);
        }
        this.drawMapToggleButtons(context, mouseX, mouseY);
        context.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 8, 0xFFFFFF);
        class_746 player = this.field_22787.field_1724;
        class_5250 statusText = class_2561.method_43473();
        CastleMapPayload.Entry hoveredCastle = null;
        CastleMapPayload.WarPartyEntry hoveredWarParty = null;
        QuestJournalClientState.QuestLocationMarker hoveredQuestMarker = null;
        VillageMapPayload.Entry hoveredVillage = null;
        if (player != null) {
            boolean inTargetDimension = player.method_37908().method_27983().equals(GotDimensionKeys.ICEANDFIRE);
            String dimensionLabel = inTargetDimension ? "Iceandfire" : "Outside iceandfire";
            context.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)dimensionLabel), this.frameX, this.frameY + this.frameSize + 6, 0xFFFFFF);
            if (inTargetDimension) {
                MapHoverTarget hoverTarget;
                int iconY;
                int iconX;
                for (QuestJournalClientState.QuestLocationMarker marker : QuestJournalClientState.getQuestLocationMarkers()) {
                    float markerScreenX = this.projectToScreenMapCoordinate(marker.x(), true);
                    float markerScreenY = this.projectToScreenMapCoordinate(marker.z(), false);
                    if (markerScreenX < 0.0f || markerScreenX >= (float)this.mapSize || markerScreenY < 0.0f || markerScreenY >= (float)this.mapSize) continue;
                    iconX = this.mapX + class_3532.method_15375((float)markerScreenX) - 2;
                    iconY = this.mapY + class_3532.method_15375((float)markerScreenY) - 2;
                    this.drawQuestMarker(context, iconX, iconY, QuestJournalClientState.getQuestColor(marker.questIndex()));
                    if (mouseX < iconX || mouseX >= iconX + 4 || mouseY < iconY || mouseY >= iconY + 4) continue;
                    hoveredQuestMarker = marker;
                    hoveredCastle = null;
                    hoveredVillage = null;
                }
                if (this.showVillages) {
                    for (VillageMapPayload.Entry village : VillageMapClientState.getVillages()) {
                        float villageScreenX = this.projectToScreenMapCoordinate(village.worldX(), true);
                        float villageScreenY = this.projectToScreenMapCoordinate(village.worldZ(), false);
                        if (villageScreenX < 0.0f || villageScreenX >= (float)this.mapSize || villageScreenY < 0.0f || villageScreenY >= (float)this.mapSize) continue;
                        iconX = this.mapX + class_3532.method_15375((float)villageScreenX) - 1;
                        iconY = this.mapY + class_3532.method_15375((float)villageScreenY) - 1;
                        this.drawScaledFullTexture(context, VILLAGE_ICON, iconX, iconY, 3, 3);
                    }
                }
                for (CastleMapPayload.Entry castle : CastleMapClientState.getCastles()) {
                    float castleScreenX = this.projectToScreenMapCoordinate(castle.worldX(), true);
                    float castleScreenY = this.projectToScreenMapCoordinate(castle.worldZ(), false);
                    if (castleScreenX < 0.0f || castleScreenX >= (float)this.mapSize || castleScreenY < 0.0f || castleScreenY >= (float)this.mapSize) continue;
                    iconX = this.mapX + class_3532.method_15375((float)castleScreenX) - 2;
                    iconY = this.mapY + class_3532.method_15375((float)castleScreenY) - 2;
                    context.method_25290(WorldMapScreen.iconFor(castle), iconX, iconY, 0.0f, 0.0f, 4, 4, 4, 4);
                    if (!castle.tourneyActive()) continue;
                    context.method_25290(TOURNEY_ICON, iconX + 4, iconY, 0.0f, 0.0f, 4, 4, 4, 4);
                }
                if (this.showWarParties) {
                    hoveredWarParty = this.drawWarParties(context, mouseX, mouseY);
                }
                if (hoveredQuestMarker == null && (hoverTarget = this.findMapHoverTarget(mouseX, mouseY)) != null) {
                    hoveredCastle = hoverTarget.castle();
                    hoveredVillage = hoverTarget.village();
                }
                if (hoveredWarParty != null) {
                    hoveredCastle = null;
                    hoveredVillage = null;
                }
                int markerX = this.mapX + this.toScreenMapCoordinate(player.method_23317(), true) - 1;
                int markerY = this.mapY + this.toScreenMapCoordinate(player.method_23321(), false) - 1;
                class_7532.method_52722((class_332)context, (class_8685)player.method_52814(), (int)markerX, (int)markerY, (int)3);
                statusText = class_2561.method_43470((String)("Hover X: " + class_3532.method_15357((double)player.method_23317()) + " Z: " + class_3532.method_15357((double)player.method_23321())));
            }
        }
        if (this.isInsideMap(mouseX, mouseY)) {
            int hoverX = this.toWorldCoordinate(mouseX - this.mapX, true);
            int hoverZ = this.toWorldCoordinate(mouseY - this.mapY, false);
            statusText = class_2561.method_43470((String)("Hover X: " + hoverX + " Z: " + hoverZ));
        }
        if (!statusText.getString().isEmpty()) {
            context.method_27535(this.field_22793, (class_2561)statusText, this.frameX, this.frameY + this.frameSize + 18, 0xFFFFFF);
        }
        TooltipLayout tooltipLayout = hoveredTooltipLayout = hoveredCastle != null ? this.computeTooltipLayout(hoveredCastle, mouseX + 12, mouseY + 12, false) : null;
        if (hoveredTooltipLayout != null && this.lockedCastle == null && this.lockedVillage == null) {
            this.drawCastleTooltip(context, hoveredTooltipLayout, mouseX, mouseY);
        }
        WarPartyTooltipLayout warPartyTooltipLayout = hoveredWarPartyTooltipLayout = hoveredWarParty != null ? this.computeWarPartyTooltipLayout(hoveredWarParty, mouseX + 12, mouseY + 12) : null;
        if (hoveredWarPartyTooltipLayout != null && this.lockedCastle == null && this.lockedVillage == null) {
            this.drawWarPartyTooltip(context, hoveredWarPartyTooltipLayout);
        }
        QuestTooltipLayout questTooltipLayout = hoveredQuestTooltipLayout = hoveredQuestMarker != null ? this.computeQuestTooltipLayout(hoveredQuestMarker, mouseX + 12, mouseY + 12) : null;
        if (hoveredQuestTooltipLayout != null) {
            this.drawQuestTooltip(context, hoveredQuestTooltipLayout);
        }
        VillageTooltipLayout villageTooltipLayout = hoveredVillageTooltipLayout = hoveredVillage != null ? this.computeVillageTooltipLayout(hoveredVillage, mouseX + 12, mouseY + 12, false) : null;
        if (hoveredVillageTooltipLayout != null && this.lockedVillage == null && this.lockedCastle == null) {
            this.drawVillageTooltip(context, hoveredVillageTooltipLayout, mouseX, mouseY);
        }
        if (this.lockedTooltipLayout != null && this.lockedCastle != null) {
            this.drawCastleTooltip(context, this.lockedTooltipLayout, mouseX, mouseY);
        }
        if (this.lockedVillageTooltipLayout != null && this.lockedVillage != null) {
            this.drawVillageTooltip(context, this.lockedVillageTooltipLayout, mouseX, mouseY);
        }
        super.method_25394(context, mouseX, mouseY, delta);
    }

    public boolean method_25421() {
        return false;
    }

    private void drawVoyageLanes(class_332 context) {
        for (VoyageLane lane : CastleMapClientState.getVoyageNetwork().lanes()) {
            for (int index = 1; index < lane.worldPoints().size(); ++index) {
                float endY;
                float endX;
                float startY;
                float startX = this.projectToScreenMapCoordinate(lane.worldPoints().get((int)(index - 1)).field_1352, true);
                ClippedLine clipped = this.clipLineToViewport(startX, startY = this.projectToScreenMapCoordinate(lane.worldPoints().get((int)(index - 1)).field_1350, false), endX = this.projectToScreenMapCoordinate(lane.worldPoints().get((int)index).field_1352, true), endY = this.projectToScreenMapCoordinate(lane.worldPoints().get((int)index).field_1350, false));
                if (clipped == null) continue;
                int drawStartX = this.mapX + class_3532.method_15375((float)clipped.startX());
                int drawStartY = this.mapY + class_3532.method_15375((float)clipped.startY());
                int drawEndX = this.mapX + class_3532.method_15375((float)clipped.endX());
                int drawEndY = this.mapY + class_3532.method_15375((float)clipped.endY());
                this.drawLine(context, drawStartX, drawStartY, drawEndX, drawEndY, -1433872129);
                this.drawLine(context, drawStartX, drawStartY + 1, drawEndX, drawEndY + 1, 1715100274);
            }
        }
    }

    private void drawMapToggleButtons(class_332 context, int mouseX, int mouseY) {
        this.drawMapToggleButton(context, this.toggleButtonBounds(0), HARBOUR_ICON, this.showSeaRoutes, mouseX, mouseY);
        this.drawMapToggleButton(context, this.toggleButtonBounds(1), ROAD_ICON, this.showRoads, mouseX, mouseY);
        this.drawMapToggleButton(context, this.toggleButtonBounds(2), VILLAGE_ICON, this.showVillages, mouseX, mouseY);
        this.drawMapToggleButton(context, this.toggleButtonBounds(3), WAR_PARTY_TOGGLE_ICON, this.showWarParties, mouseX, mouseY);
    }

    private void drawMapToggleButton(class_332 context, ButtonBounds bounds, class_2960 icon, boolean active, int mouseX, int mouseY) {
        boolean hovered = mouseX >= bounds.x() && mouseX < bounds.x() + bounds.size() && mouseY >= bounds.y() && mouseY < bounds.y() + bounds.size();
        context.method_25290(active || hovered ? MAP_ICON_FRAME_ACTIVE : MAP_ICON_FRAME, bounds.x(), bounds.y(), 0.0f, 0.0f, bounds.size(), bounds.size(), bounds.size(), bounds.size());
        int iconX = bounds.x() + (bounds.size() - 11) / 2;
        int iconY = bounds.y() + (bounds.size() - 11) / 2;
        context.method_25290(icon, iconX, iconY, 0.0f, 0.0f, 11, 11, 11, 11);
    }

    private ButtonBounds toggleButtonBounds(int index) {
        int x = this.frameX - 19 - 8;
        int y = this.mapY + index * 25;
        return new ButtonBounds(x, y, 19);
    }

    @Nullable
    private ClippedLine clipLineToViewport(float startX, float startY, float endX, float endY) {
        float min = 0.0f;
        float max = (float)this.mapSize - 1.0f;
        float deltaX = endX - startX;
        float deltaY = endY - startY;
        float t0 = 0.0f;
        float t1 = 1.0f;
        float[] p = new float[]{-deltaX, deltaX, -deltaY, deltaY};
        float[] q = new float[]{startX - min, max - startX, startY - min, max - startY};
        for (int index = 0; index < 4; ++index) {
            if (Math.abs(p[index]) < 1.0E-5f) {
                if (!(q[index] < 0.0f)) continue;
                return null;
            }
            float ratio = q[index] / p[index];
            if (p[index] < 0.0f) {
                if (ratio > t1) {
                    return null;
                }
                t0 = Math.max(t0, ratio);
                continue;
            }
            if (ratio < t0) {
                return null;
            }
            t1 = Math.min(t1, ratio);
        }
        return new ClippedLine(startX + deltaX * t0, startY + deltaY * t0, startX + deltaX * t1, startY + deltaY * t1);
    }

    private void drawLine(class_332 context, int startX, int startY, int endX, int endY, int color) {
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        int stepX = startX < endX ? 1 : -1;
        int stepY = startY < endY ? 1 : -1;
        int error = deltaX - deltaY;
        int x = startX;
        int y = startY;
        while (true) {
            context.method_25294(x, y, x + 1, y + 1, color);
            if (x == endX && y == endY) break;
            int doubleError = error * 2;
            if (doubleError > -deltaY) {
                error -= deltaY;
                x += stepX;
            }
            if (doubleError >= deltaX) continue;
            error += deltaX;
            y += stepY;
        }
    }

    private void drawQuestMarker(class_332 context, int x, int y, int color) {
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        RenderSystem.setShaderColor((float)red, (float)green, (float)blue, (float)1.0f);
        this.drawScaledFullTexture(context, QUEST_BANNER_ICON, x, y, 4, 4);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button == 0) {
            ButtonBounds seaBounds = this.toggleButtonBounds(0);
            if (seaBounds.contains(mouseX, mouseY)) {
                cachedShowSeaRoutes = this.showSeaRoutes = !this.showSeaRoutes;
                return true;
            }
            ButtonBounds roadBounds = this.toggleButtonBounds(1);
            if (roadBounds.contains(mouseX, mouseY)) {
                cachedShowRoads = this.showRoads = !this.showRoads;
                return true;
            }
            ButtonBounds villageBounds = this.toggleButtonBounds(2);
            if (villageBounds.contains(mouseX, mouseY)) {
                cachedShowVillages = this.showVillages = !this.showVillages;
                return true;
            }
            ButtonBounds warPartyBounds = this.toggleButtonBounds(3);
            if (warPartyBounds.contains(mouseX, mouseY)) {
                cachedShowWarParties = this.showWarParties = !this.showWarParties;
                return true;
            }
        }
        if (button == 0 && this.lockedTooltipLayout != null && this.isInsideFastTravelButton(mouseX, mouseY, this.lockedTooltipLayout)) {
            if (this.lockedCastle != null && this.lockedCastle.unlocked() && !CastleFastTravelClientState.hasCooldown()) {
                CastleFastTravelClientState.start(this.lockedCastle.id());
            }
            this.lockedCastle = null;
            this.lockedTooltipLayout = null;
            this.lockedVillage = null;
            this.lockedVillageTooltipLayout = null;
            return true;
        }
        if (button == 0 && this.lockedVillageTooltipLayout != null && this.isInsideVillageFastTravelButton(mouseX, mouseY, this.lockedVillageTooltipLayout)) {
            if (this.lockedVillage != null && this.lockedVillage.unlocked() && !CastleFastTravelClientState.hasCooldown()) {
                CastleFastTravelClientState.start(this.lockedVillage.id());
            }
            this.lockedCastle = null;
            this.lockedTooltipLayout = null;
            this.lockedVillage = null;
            this.lockedVillageTooltipLayout = null;
            return true;
        }
        if (button == 0 && this.lockedTooltipLayout != null && this.isInsideTooltip(mouseX, mouseY, this.lockedTooltipLayout)) {
            return true;
        }
        if (button == 0 && this.lockedVillageTooltipLayout != null && this.isInsideVillageTooltip(mouseX, mouseY, this.lockedVillageTooltipLayout)) {
            return true;
        }
        if (button == 1 && this.isInsideMap(mouseX, mouseY)) {
            CastleMapPayload.Entry clickedCastle = this.findCastleAt(mouseX, mouseY);
            if (clickedCastle != null) {
                ClientPlayNetworking.send((class_8710)new RequestCastleScreenPayload(clickedCastle.id()));
                return true;
            }
            this.rightDragging = true;
            this.lastDragMouseX = mouseX;
            this.lastDragMouseY = mouseY;
            return true;
        }
        if (button == 0 && this.isInsideMap(mouseX, mouseY)) {
            MapHoverTarget clickedTarget = this.findMapHoverTarget(mouseX, mouseY);
            if (clickedTarget != null) {
                if (clickedTarget.castle() != null) {
                    this.lockedCastle = clickedTarget.castle();
                    this.lockedTooltipLayout = this.computeTooltipLayout(clickedTarget.castle(), (int)mouseX + 12, (int)mouseY + 12, true);
                    this.lockedVillage = null;
                    this.lockedVillageTooltipLayout = null;
                    return true;
                }
                if (clickedTarget.village() != null) {
                    this.lockedVillage = clickedTarget.village();
                    this.lockedVillageTooltipLayout = this.computeVillageTooltipLayout(clickedTarget.village(), (int)mouseX + 12, (int)mouseY + 12, true);
                    this.lockedCastle = null;
                    this.lockedTooltipLayout = null;
                    return true;
                }
            }
            this.lockedCastle = null;
            this.lockedTooltipLayout = null;
            this.lockedVillage = null;
            this.lockedVillageTooltipLayout = null;
            return true;
        }
        if (button == 0) {
            this.lockedCastle = null;
            this.lockedTooltipLayout = null;
            this.lockedVillage = null;
            this.lockedVillageTooltipLayout = null;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        if (button == 1) {
            this.rightDragging = false;
            return true;
        }
        return super.method_25406(mouseX, mouseY, button);
    }

    public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (button == 1 && this.rightDragging) {
            float viewportSize = this.getViewportSize();
            float pixelsPerScreenPixel = viewportSize / (float)this.mapSize;
            this.viewOriginX -= (float)((mouseX - this.lastDragMouseX) * (double)pixelsPerScreenPixel);
            this.viewOriginY -= (float)((mouseY - this.lastDragMouseY) * (double)pixelsPerScreenPixel);
            this.lastDragMouseX = mouseX;
            this.lastDragMouseY = mouseY;
            this.clampView();
            return true;
        }
        return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
    }

    public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (!this.isInsideMap(mouseX, mouseY) || verticalAmount == 0.0) {
            return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
        }
        float oldZoom = this.zoom;
        this.zoom = class_3532.method_15363((float)(this.zoom + (verticalAmount > 0.0 ? 0.25f : -0.25f)), (float)1.0f, (float)6.0f);
        if (this.zoom == oldZoom) {
            return true;
        }
        cachedZoom = this.zoom;
        double localMouseX = mouseX - (double)this.mapX;
        double localMouseY = mouseY - (double)this.mapY;
        float oldViewportSize = 3000.0f / oldZoom;
        float newViewportSize = this.getViewportSize();
        float oldSourceX = this.viewOriginX + (float)(localMouseX / (double)this.mapSize) * oldViewportSize;
        float oldSourceY = this.viewOriginY + (float)(localMouseY / (double)this.mapSize) * oldViewportSize;
        this.viewOriginX = oldSourceX - (float)(localMouseX / (double)this.mapSize) * newViewportSize;
        this.viewOriginY = oldSourceY - (float)(localMouseY / (double)this.mapSize) * newViewportSize;
        this.clampView();
        return true;
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (WorldMapScreen.isKeyPressed(GotKeyBindings.MAP_TELEPORT, keyCode, scanCode) && this.tryCreativeMapTeleport()) {
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    private boolean isInsideMap(double mouseX, double mouseY) {
        return mouseX >= (double)this.mapX && mouseX < (double)(this.mapX + this.mapSize) && mouseY >= (double)this.mapY && mouseY < (double)(this.mapY + this.mapSize);
    }

    private float getViewportSize() {
        return 3000.0f / this.zoom;
    }

    private int toScreenMapCoordinate(double blockCoordinate, boolean horizontal) {
        return class_3532.method_15340((int)class_3532.method_15375((float)this.projectToScreenMapCoordinate(blockCoordinate, horizontal)), (int)0, (int)(this.mapSize - 1));
    }

    private float projectToScreenMapCoordinate(double blockCoordinate, boolean horizontal) {
        double normalized = (blockCoordinate + 48000.0) / 96000.0;
        float mapPixel = (float)(normalized * 3000.0);
        float viewOrigin = horizontal ? this.viewOriginX : this.viewOriginY;
        float viewportSize = this.getViewportSize();
        return (mapPixel - viewOrigin) / viewportSize * (float)this.mapSize;
    }

    private int toWorldCoordinate(double mapCoordinate, boolean horizontal) {
        float viewportSize = this.getViewportSize();
        float viewOrigin = horizontal ? this.viewOriginX : this.viewOriginY;
        double normalized = class_3532.method_15350((double)(mapCoordinate / (double)this.mapSize), (double)0.0, (double)0.999999);
        double sourcePixel = (double)viewOrigin + normalized * (double)viewportSize;
        double worldNormalized = sourcePixel / 3000.0;
        return class_3532.method_15357((double)(worldNormalized * 96000.0 - 48000.0));
    }

    private void clampView() {
        float viewportSize = this.getViewportSize();
        float maxOrigin = 3000.0f - viewportSize;
        this.viewOriginX = class_3532.method_15363((float)this.viewOriginX, (float)0.0f, (float)Math.max(0.0f, maxOrigin));
        this.viewOriginY = class_3532.method_15363((float)this.viewOriginY, (float)0.0f, (float)Math.max(0.0f, maxOrigin));
        cachedViewOriginX = this.viewOriginX;
        cachedViewOriginY = this.viewOriginY;
    }

    private void drawCastleTooltip(class_332 context, TooltipLayout layout, int mouseX, int mouseY) {
        CharacterSelectionOption kingdomOption = this.resolveKingdomOption(layout.castle().kingdomId());
        class_2960 houseTexture = PlayerHouseRenderHelper.resolveTexture(layout.castle().houseId(), layout.castle().kingdomId());
        ReligionSelectionOption religionOption = this.resolveReligionOption(layout.castle().religionId());
        String tierLabel = WorldMapScreen.tierLabel(layout.castle());
        String kingdomLabel = WorldMapScreen.kingdomDisplayName(layout.castle().kingdomId(), kingdomOption);
        String houseLabel = PlayerHouseRenderHelper.resolveDisplayName(layout.castle().houseId(), layout.castle().kingdomId());
        String religionLabel = religionOption != null ? religionOption.displayName() : layout.castle().religionId();
        boolean harbour = layout.castle().subtypeIds().contains("harbour");
        List<QuestJournalClientState.CastleQuestIndicator> questIndicators = QuestJournalClientState.getCastleQuestIndicators(layout.castle().id());
        this.drawCastleTooltipPanel(context, layout.x(), layout.y(), layout.width(), layout.height());
        int tooltipX = layout.contentX();
        int lineY = layout.contentY();
        context.method_25303(this.field_22793, layout.castle().name(), tooltipX, lineY, -596021);
        context.method_25303(this.field_22793, tierLabel, tooltipX, lineY += 10, -4608099);
        this.drawTooltipLineWithIcon(context, tooltipX, lineY += 10, this.kingdomIconFor(layout.castle(), kingdomOption), kingdomLabel);
        this.drawTooltipLineWithIcon(context, tooltipX, lineY += 10, houseTexture, houseLabel);
        this.drawTooltipLineWithIcon(context, tooltipX, lineY += 10, religionOption != null ? religionOption.texture() : null, religionLabel);
        if (harbour) {
            this.drawTooltipLineWithIcon(context, tooltipX, lineY += 10, HARBOUR_ICON, "Harbour");
        }
        for (QuestJournalClientState.CastleQuestIndicator indicator : questIndicators) {
            this.drawTooltipQuestLine(context, tooltipX, lineY += 10, indicator);
        }
        if (layout.locked()) {
            boolean hovered;
            boolean cooldown = CastleFastTravelClientState.hasCooldown();
            boolean unlocked = layout.castle().unlocked();
            boolean interactive = unlocked && !cooldown;
            boolean bl = hovered = interactive && this.isInsideFastTravelButton(mouseX, mouseY, layout);
            class_2960 texture = interactive ? (hovered ? FAST_TRAVEL_HOVER_TEXTURE : FAST_TRAVEL_TEXTURE) : FAST_TRAVEL_LOCKED_TEXTURE;
            this.drawScaledFullTexture(context, texture, layout.buttonX(), layout.buttonY(), 72, 24);
            String label = cooldown ? CastleFastTravelClientState.cooldownLabel() : (unlocked ? "Fast Travel" : "Locked");
            int color = interactive ? -596021 : -3094860;
            int scaledIconSize = Math.max(1, Math.round(7.2f));
            int scaledGap = Math.max(1, Math.round(3.6f));
            int labelWidth = Math.round((float)this.field_22793.method_1727(label) * 0.9f);
            class_2960 icon = cooldown ? FAST_TRAVEL_COOLDOWN_ICON : (unlocked ? FAST_TRAVEL_EFFECT_ICON : null);
            int iconWidth = icon != null ? scaledIconSize + scaledGap : 0;
            int contentWidth = iconWidth + labelWidth;
            int contentX = layout.buttonX() + (72 - contentWidth) / 2;
            if (icon != null) {
                int iconY = layout.buttonY() + (24 - scaledIconSize) / 2;
                this.drawScaledFullTexture(context, icon, contentX, iconY, scaledIconSize, scaledIconSize);
            }
            int textX = contentX + iconWidth;
            int textY = layout.buttonY() + (24 - Math.round(7.2f)) / 2;
            context.method_51448().method_22903();
            context.method_51448().method_46416((float)textX, (float)textY, 0.0f);
            context.method_51448().method_22905(0.9f, 0.9f, 1.0f);
            context.method_25303(this.field_22793, label, 0, 0, color);
            context.method_51448().method_22909();
        }
    }

    private void drawQuestTooltip(class_332 context, QuestTooltipLayout layout) {
        this.drawCastleTooltipPanel(context, layout.x(), layout.y(), layout.width(), layout.height());
        int tooltipX = layout.contentX();
        int lineY = layout.contentY();
        context.method_25303(this.field_22793, layout.questName(), tooltipX, lineY, -596021);
        context.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)("(" + layout.marker().x() + ", " + layout.marker().y() + ", " + layout.marker().z() + ")")).method_27695(new class_124[]{class_124.field_1080, class_124.field_1056}), tooltipX, lineY += 10, -4608099);
    }

    private void drawVillageTooltip(class_332 context, VillageTooltipLayout layout, int mouseX, int mouseY) {
        this.drawCastleTooltipPanel(context, layout.x(), layout.y(), layout.width(), layout.height());
        int tooltipX = layout.contentX();
        int lineY = layout.contentY();
        VillageResource resource = GotVillageResources.get(layout.village().resourceId());
        context.method_25303(this.field_22793, layout.village().name(), tooltipX, lineY, -596021);
        context.method_27535(this.field_22793, (class_2561)class_2561.method_43471((String)"tooltip.agob.village"), tooltipX, lineY += 10, -4608099);
        context.method_25303(this.field_22793, "Castle: " + layout.village().boundCastleName(), tooltipX, lineY += 10, -1);
        lineY += 10;
        if (resource != null) {
            this.drawVillageResourceLine(context, tooltipX, lineY, resource);
            lineY += 10;
        }
        context.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)("(" + layout.village().worldX() + ", " + layout.village().worldZ() + ")")).method_27695(new class_124[]{class_124.field_1080, class_124.field_1056}), tooltipX, lineY, -4608099);
        if (layout.locked()) {
            boolean hovered;
            boolean cooldown = CastleFastTravelClientState.hasCooldown();
            boolean unlocked = layout.village().unlocked();
            boolean interactive = unlocked && !cooldown;
            boolean bl = hovered = interactive && this.isInsideVillageFastTravelButton(mouseX, mouseY, layout);
            class_2960 texture = interactive ? (hovered ? FAST_TRAVEL_HOVER_TEXTURE : FAST_TRAVEL_TEXTURE) : FAST_TRAVEL_LOCKED_TEXTURE;
            this.drawScaledFullTexture(context, texture, layout.buttonX(), layout.buttonY(), 72, 24);
            String label = cooldown ? CastleFastTravelClientState.cooldownLabel() : (unlocked ? "Fast Travel" : "Locked");
            int color = interactive ? -596021 : -3094860;
            int scaledIconSize = Math.max(1, Math.round(7.2f));
            int scaledGap = Math.max(1, Math.round(3.6f));
            int labelWidth = Math.round((float)this.field_22793.method_1727(label) * 0.9f);
            class_2960 icon = cooldown ? FAST_TRAVEL_COOLDOWN_ICON : (unlocked ? FAST_TRAVEL_EFFECT_ICON : null);
            int iconWidth = icon != null ? scaledIconSize + scaledGap : 0;
            int contentWidth = iconWidth + labelWidth;
            int contentX = layout.buttonX() + (72 - contentWidth) / 2;
            if (icon != null) {
                int iconY = layout.buttonY() + (24 - scaledIconSize) / 2;
                this.drawScaledFullTexture(context, icon, contentX, iconY, scaledIconSize, scaledIconSize);
            }
            int textX = contentX + iconWidth;
            int textY = layout.buttonY() + (24 - Math.round(7.2f)) / 2;
            context.method_51448().method_22903();
            context.method_51448().method_46416((float)textX, (float)textY, 0.0f);
            context.method_51448().method_22905(0.9f, 0.9f, 1.0f);
            context.method_25303(this.field_22793, label, 0, 0, color);
            context.method_51448().method_22909();
        }
    }

    private void drawWarPartyTooltip(class_332 context, WarPartyTooltipLayout layout) {
        this.drawCastleTooltipPanel(context, layout.x(), layout.y(), layout.width(), layout.height());
        int tooltipX = layout.contentX();
        int lineY = layout.contentY();
        context.method_25303(this.field_22793, layout.lordName(), tooltipX, lineY, -596021);
        context.method_25303(this.field_22793, layout.etaLabel(), tooltipX, lineY += 10, -1);
    }

    private WarPartyTooltipLayout computeWarPartyTooltipLayout(CastleMapPayload.WarPartyEntry warParty, int preferredX, int preferredY) {
        long currentProgressTick = CastleMapClientState.currentProgressTick(this.field_22787);
        long remainingTicks = warParty.arrived() ? 0L : Math.max(0L, warParty.arrivalTick() - currentProgressTick);
        String etaLabel = "ETA: " + WorldMapScreen.formatMinutesSeconds(remainingTicks);
        int contentWidth = Math.max(this.field_22793.method_1727(warParty.lordName()), this.field_22793.method_1727(etaLabel));
        int width = class_3532.method_15340((int)(contentWidth + 20), (int)118, (int)172);
        int height = 30;
        int x = class_3532.method_15340((int)preferredX, (int)8, (int)(this.field_22789 - width - 8));
        int y = class_3532.method_15340((int)preferredY, (int)8, (int)(this.field_22790 - height - 8));
        return new WarPartyTooltipLayout(warParty.lordName(), etaLabel, x, y, width, height, x + 10, y + 10);
    }

    private TooltipLayout computeTooltipLayout(CastleMapPayload.Entry castle, int preferredX, int preferredY, boolean locked) {
        CharacterSelectionOption kingdomOption = this.resolveKingdomOption(castle.kingdomId());
        ReligionSelectionOption religionOption = this.resolveReligionOption(castle.religionId());
        String tierLabel = WorldMapScreen.tierLabel(castle);
        String kingdomLabel = WorldMapScreen.kingdomDisplayName(castle.kingdomId(), kingdomOption);
        String houseLabel = PlayerHouseRenderHelper.resolveDisplayName(castle.houseId(), castle.kingdomId());
        String religionLabel = religionOption != null ? religionOption.displayName() : castle.religionId();
        boolean harbour = castle.subtypeIds().contains("harbour");
        List<QuestJournalClientState.CastleQuestIndicator> questIndicators = QuestJournalClientState.getCastleQuestIndicators(castle.id());
        int contentWidth = 0;
        contentWidth = Math.max(contentWidth, this.field_22793.method_1727(castle.name()));
        contentWidth = Math.max(contentWidth, this.field_22793.method_1727(tierLabel));
        contentWidth = Math.max(contentWidth, this.field_22793.method_1727(kingdomLabel) + 14);
        contentWidth = Math.max(contentWidth, this.field_22793.method_1727(houseLabel) + 14);
        contentWidth = Math.max(contentWidth, this.field_22793.method_1727(religionLabel) + 14);
        if (harbour) {
            contentWidth = Math.max(contentWidth, this.field_22793.method_1727("Harbour") + 14);
        }
        for (QuestJournalClientState.CastleQuestIndicator indicator : questIndicators) {
            contentWidth = Math.max(contentWidth, this.field_22793.method_1727(indicator.label()) + 14);
        }
        int lineCount = 5 + (harbour ? 1 : 0) + questIndicators.size();
        int contentHeight = 8 + (lineCount - 1) * 10;
        int width = class_3532.method_15340((int)(contentWidth + 20), (int)118, (int)172);
        int height = contentHeight + 20 + 2;
        int x = class_3532.method_15340((int)preferredX, (int)8, (int)(this.field_22789 - width - 8));
        int totalHeight = height + (locked ? 30 : 0);
        int y = class_3532.method_15340((int)preferredY, (int)8, (int)(this.field_22790 - totalHeight - 8));
        int buttonX = x + (width - 72) / 2;
        int buttonY = y + height + 6;
        return new TooltipLayout(castle, x, y, width, height, x + 10, y + 10, locked, buttonX, buttonY);
    }

    private static String formatMinutesSeconds(long ticks) {
        long totalSeconds = Math.max(0L, ticks / 20L);
        long minutes = totalSeconds / 60L;
        long seconds = totalSeconds % 60L;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private QuestTooltipLayout computeQuestTooltipLayout(QuestJournalClientState.QuestLocationMarker marker, int preferredX, int preferredY) {
        String questName = this.resolveQuestName(marker.templateId());
        String coords = "(" + marker.x() + ", " + marker.y() + ", " + marker.z() + ")";
        int contentWidth = Math.max(this.field_22793.method_1727(questName), this.field_22793.method_1727(coords));
        int width = class_3532.method_15340((int)(contentWidth + 20), (int)118, (int)172);
        int height = 40;
        int x = class_3532.method_15340((int)preferredX, (int)8, (int)(this.field_22789 - width - 8));
        int y = class_3532.method_15340((int)preferredY, (int)8, (int)(this.field_22790 - height - 8));
        return new QuestTooltipLayout(marker, questName, x, y, width, height, x + 10, y + 10);
    }

    private VillageTooltipLayout computeVillageTooltipLayout(VillageMapPayload.Entry village, int preferredX, int preferredY, boolean locked) {
        VillageResource resource = GotVillageResources.get(village.resourceId());
        String typeLabel = class_2561.method_43471((String)"tooltip.agob.village").getString();
        String castleLabel = "Castle: " + village.boundCastleName();
        String outputLabel = resource == null ? "" : class_2561.method_43469((String)"tooltip.agob.output", (Object[])new Object[]{resource.getDisplayName()}).getString();
        String coordsLabel = "(" + village.worldX() + ", " + village.worldZ() + ")";
        int contentWidth = 0;
        contentWidth = Math.max(contentWidth, this.field_22793.method_1727(village.name()));
        contentWidth = Math.max(contentWidth, this.field_22793.method_1727(typeLabel));
        contentWidth = Math.max(contentWidth, this.field_22793.method_1727(castleLabel));
        if (!outputLabel.isBlank()) {
            contentWidth = Math.max(contentWidth, this.field_22793.method_1727(outputLabel) + 14);
        }
        contentWidth = Math.max(contentWidth, this.field_22793.method_1727(coordsLabel));
        int width = class_3532.method_15340((int)(contentWidth + 20), (int)118, (int)172);
        int lineCount = resource == null ? 4 : 5;
        int height = lineCount * 10 + 20;
        int x = class_3532.method_15340((int)preferredX, (int)8, (int)(this.field_22789 - width - 8));
        int totalHeight = height + (locked ? 30 : 0);
        int y = class_3532.method_15340((int)preferredY, (int)8, (int)(this.field_22790 - totalHeight - 8));
        int buttonX = x + (width - 72) / 2;
        int buttonY = y + height + 6;
        return new VillageTooltipLayout(village, x, y, width, height, x + 10, y + 10, locked, buttonX, buttonY);
    }

    private String resolveQuestName(String templateId) {
        QuestTemplate template = QuestTemplateRegistry.get(templateId);
        return template == null ? templateId : template.displayName();
    }

    private boolean isInsideTooltip(double mouseX, double mouseY, TooltipLayout layout) {
        return mouseX >= (double)layout.x() && mouseX < (double)(layout.x() + layout.width()) && mouseY >= (double)layout.y() && mouseY < (double)(layout.y() + layout.height());
    }

    private boolean isInsideFastTravelButton(double mouseX, double mouseY, TooltipLayout layout) {
        if (!layout.locked()) {
            return false;
        }
        return mouseX >= (double)layout.buttonX() && mouseX < (double)(layout.buttonX() + 72) && mouseY >= (double)layout.buttonY() && mouseY < (double)(layout.buttonY() + 24);
    }

    private boolean isInsideVillageTooltip(double mouseX, double mouseY, VillageTooltipLayout layout) {
        int height = layout.height() + (layout.locked() ? 30 : 0);
        return mouseX >= (double)layout.x() && mouseX < (double)(layout.x() + layout.width()) && mouseY >= (double)layout.y() && mouseY < (double)(layout.y() + height);
    }

    private boolean isInsideVillageFastTravelButton(double mouseX, double mouseY, VillageTooltipLayout layout) {
        if (!layout.locked()) {
            return false;
        }
        return mouseX >= (double)layout.buttonX() && mouseX < (double)(layout.buttonX() + 72) && mouseY >= (double)layout.buttonY() && mouseY < (double)(layout.buttonY() + 24);
    }

    private boolean tryCreativeMapTeleport() {
        if (this.field_22787 == null || this.field_22787.field_1724 == null || !this.field_22787.field_1724.method_7337() || !this.isInsideMap(this.lastMouseX, this.lastMouseY)) {
            return false;
        }
        int targetX = this.toWorldCoordinate(this.lastMouseX - this.mapX, true);
        int targetZ = this.toWorldCoordinate(this.lastMouseY - this.mapY, false);
        ClientPlayNetworking.send((class_8710)new TeleportMapPayload(targetX, targetZ));
        return true;
    }

    private static boolean isKeyPressed(class_304 binding, int keyCode, int scanCode) {
        return binding.method_1417(keyCode, scanCode);
    }

    private static class_2960 iconFor(CastleMapPayload.Entry castle) {
        if (castle.capital()) {
            return CASTLE_CAPITAL_ICON;
        }
        return castle.stone() ? CASTLE_STONE_ICON : CASTLE_WOOD_ICON;
    }

    private static String tierLabel(CastleMapPayload.Entry castle) {
        return CastleTier.byId(castle.tierId()).getDisplayName();
    }

    private CastleMapPayload.WarPartyEntry drawWarParties(class_332 context, int mouseX, int mouseY) {
        CastleMapPayload.WarPartyEntry hovered = null;
        double hoveredDistanceSq = Double.MAX_VALUE;
        long currentProgressTick = CastleMapClientState.currentProgressTick(this.field_22787);
        for (CastleMapPayload.WarPartyEntry warParty : CastleMapClientState.getWarParties()) {
            double centerY;
            double centerX;
            double distanceSq;
            double chunkX = warParty.arrived() ? (double)warParty.targetChunkX() : LordWarPartyHelper.currentChunkCoordinate(warParty.startChunkX(), warParty.targetChunkX(), warParty.departureTick(), warParty.arrivalTick(), currentProgressTick);
            double chunkZ = warParty.arrived() ? (double)warParty.targetChunkZ() : LordWarPartyHelper.currentChunkCoordinate(warParty.startChunkZ(), warParty.targetChunkZ(), warParty.departureTick(), warParty.arrivalTick(), currentProgressTick);
            float screenX = this.projectToScreenMapCoordinate(chunkX * 16.0 + 8.0, true);
            float screenY = this.projectToScreenMapCoordinate(chunkZ * 16.0 + 8.0, false);
            if (screenX < 0.0f || screenX >= (float)this.mapSize || screenY < 0.0f || screenY >= (float)this.mapSize) continue;
            int badgeX = this.mapX + class_3532.method_15375((float)screenX) - 7;
            int badgeY = this.mapY + class_3532.method_15375((float)screenY) - 14;
            int indicatorX = this.mapX + class_3532.method_15375((float)screenX) - 4;
            int indicatorY = badgeY + 14 - 1;
            this.drawScaledFullTexture(context, PARTY_BADGE_TEXTURE, badgeX, badgeY, 14, 14);
            this.drawFittedTexture(context, PlayerHouseRenderHelper.resolveTexture(warParty.attackerHouseId(), warParty.attackerKingdomId()), badgeX + 2, badgeY + 2, 10, 10);
            this.drawScaledFullTexture(context, PARTY_INDICATOR_TEXTURE, indicatorX, indicatorY, 9, 9);
            if (mouseX < badgeX || mouseX >= badgeX + 14 || mouseY < badgeY || mouseY >= badgeY + 14 || !((distanceSq = WorldMapScreen.squaredDistance(mouseX, mouseY, centerX = (double)badgeX + 7.0, centerY = (double)badgeY + 7.0)) < hoveredDistanceSq)) continue;
            hoveredDistanceSq = distanceSq;
            hovered = warParty;
        }
        return hovered;
    }

    private CastleMapPayload.Entry findCastleAt(double mouseX, double mouseY) {
        MapHoverTarget target = this.findMapHoverTarget(mouseX, mouseY);
        return target != null ? target.castle() : null;
    }

    private VillageMapPayload.Entry findVillageAt(double mouseX, double mouseY) {
        MapHoverTarget target = this.findMapHoverTarget(mouseX, mouseY);
        return target != null ? target.village() : null;
    }

    private MapHoverTarget findMapHoverTarget(double mouseX, double mouseY) {
        CastleMapPayload.Entry hoveredCastle = null;
        double castleDistanceSq = Double.MAX_VALUE;
        for (CastleMapPayload.Entry castle : CastleMapClientState.getCastles()) {
            double centerY;
            double centerX;
            double distanceSq;
            float castleScreenX = this.projectToScreenMapCoordinate(castle.worldX(), true);
            float castleScreenY = this.projectToScreenMapCoordinate(castle.worldZ(), false);
            if (castleScreenX < 0.0f || castleScreenX >= (float)this.mapSize || castleScreenY < 0.0f || castleScreenY >= (float)this.mapSize) continue;
            int iconX = this.mapX + class_3532.method_15375((float)castleScreenX) - 2;
            int iconY = this.mapY + class_3532.method_15375((float)castleScreenY) - 2;
            if (!(mouseX >= (double)iconX) || !(mouseX < (double)(iconX + 4)) || !(mouseY >= (double)iconY) || !(mouseY < (double)(iconY + 4)) || !((distanceSq = WorldMapScreen.squaredDistance(mouseX, mouseY, centerX = (double)iconX + 2.0, centerY = (double)iconY + 2.0)) < castleDistanceSq)) continue;
            castleDistanceSq = distanceSq;
            hoveredCastle = castle;
        }
        VillageMapPayload.Entry hoveredVillage = null;
        double villageDistanceSq = Double.MAX_VALUE;
        if (this.showVillages) {
            for (VillageMapPayload.Entry village : VillageMapClientState.getVillages()) {
                double centerY;
                double centerX;
                double distanceSq;
                float villageScreenX = this.projectToScreenMapCoordinate(village.worldX(), true);
                float villageScreenY = this.projectToScreenMapCoordinate(village.worldZ(), false);
                if (villageScreenX < 0.0f || villageScreenX >= (float)this.mapSize || villageScreenY < 0.0f || villageScreenY >= (float)this.mapSize) continue;
                int iconX = this.mapX + class_3532.method_15375((float)villageScreenX) - 1;
                int iconY = this.mapY + class_3532.method_15375((float)villageScreenY) - 1;
                if (!(mouseX >= (double)iconX) || !(mouseX < (double)(iconX + 3)) || !(mouseY >= (double)iconY) || !(mouseY < (double)(iconY + 3)) || !((distanceSq = WorldMapScreen.squaredDistance(mouseX, mouseY, centerX = (double)iconX + 1.5, centerY = (double)iconY + 1.5)) < villageDistanceSq)) continue;
                villageDistanceSq = distanceSq;
                hoveredVillage = village;
            }
        }
        if (hoveredCastle == null && hoveredVillage == null) {
            return null;
        }
        if (hoveredCastle != null && (hoveredVillage == null || castleDistanceSq <= villageDistanceSq)) {
            return new MapHoverTarget(hoveredCastle, null);
        }
        return new MapHoverTarget(null, hoveredVillage);
    }

    private static double squaredDistance(double startX, double startY, double endX, double endY) {
        double dx = startX - endX;
        double dy = startY - endY;
        return dx * dx + dy * dy;
    }

    private void drawTooltipLineWithIcon(class_332 context, int x, int y, class_2960 iconTexture, String text) {
        if (iconTexture != null) {
            this.drawFittedTexture(context, iconTexture, x, y, 9, 9);
            context.method_25303(this.field_22793, text, x + 12, y + 1, -1);
            return;
        }
        context.method_25303(this.field_22793, text, x, y + 1, -1);
    }

    private void drawTooltipQuestLine(class_332 context, int x, int y, QuestJournalClientState.CastleQuestIndicator indicator) {
        class_1799 stack = indicator.markerStack();
        if (!stack.method_7960()) {
            context.method_51427(stack, x - 1, y - 3);
            context.method_25303(this.field_22793, indicator.label(), x + 12, y + 1, indicator.color());
            return;
        }
        context.method_25303(this.field_22793, indicator.label(), x, y + 1, indicator.color());
    }

    private void drawVillageResourceLine(class_332 context, int x, int y, VillageResource resource) {
        String label = class_2561.method_43469((String)"tooltip.agob.output", (Object[])new Object[]{resource.getDisplayName()}).getString();
        int labelWidth = this.field_22793.method_1727(label);
        int iconX = x + labelWidth + 4;
        class_2960 iconTexture = resource.getIconTexture();
        if (iconTexture != null) {
            context.method_25303(this.field_22793, label, x, y + 1, -1);
            this.drawFittedTexture(context, iconTexture, iconX, y, 8, 8);
            return;
        }
        class_1799 stack = resource.getIconStack();
        if (!stack.method_7960()) {
            context.method_25303(this.field_22793, label, x, y + 1, -1);
            context.method_51448().method_22903();
            context.method_51448().method_46416((float)iconX, (float)y, 0.0f);
            context.method_51448().method_22905(0.5f, 0.5f, 1.0f);
            context.method_51427(stack, 0, 0);
            context.method_51448().method_22909();
            return;
        }
        context.method_25303(this.field_22793, label, x, y + 1, -1);
    }

    private void drawScaledFullTexture(class_332 context, class_2960 texture, int x, int y, int drawWidth, int drawHeight) {
        ClientTextureHelper.TextureSize size = this.resolveTextureSize(texture);
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905((float)drawWidth / (float)size.width(), (float)drawHeight / (float)size.height(), 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
        context.method_51448().method_22909();
    }

    private void drawFittedTexture(class_332 context, class_2960 texture, int x, int y, int boxWidth, int boxHeight) {
        ClientTextureHelper.TextureSize size = this.resolveTextureSize(texture);
        float scale = Math.min((float)boxWidth / (float)size.width(), (float)boxHeight / (float)size.height());
        int offsetX = Math.max(0, Math.round(((float)boxWidth - (float)size.width() * scale) / 2.0f));
        int offsetY = Math.max(0, Math.round(((float)boxHeight - (float)size.height() * scale) / 2.0f));
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)(x + offsetX), (float)(y + offsetY), 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
        context.method_51448().method_22909();
    }

    private void drawCastleTooltipPanel(class_332 context, int x, int y, int width, int height) {
        int nativeWidth = Math.max(1, class_3532.method_15386((float)((float)width / 0.5f)));
        int nativeHeight = Math.max(1, class_3532.method_15386((float)((float)height / 0.5f)));
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(0.5f, 0.5f, 1.0f);
        this.drawFiveSliceTexture(context, CASTLE_TOOLTIP_BACKGROUND, 0, 0, nativeWidth, nativeHeight);
        this.drawFiveSliceTexture(context, CASTLE_TOOLTIP_FRAME, 0, 0, nativeWidth, nativeHeight);
        context.method_51448().method_22909();
    }

    private void drawFiveSliceTexture(class_332 context, class_2960 texture, int x, int y, int width, int height) {
        ClientTextureHelper.TextureSize size = this.resolveTextureSize(texture);
        int tileWidth = Math.max(1, size.width() / 5);
        int tileHeight = Math.max(1, size.height() / 5);
        int leftWidth = Math.min(tileWidth * 2, width / 2);
        int rightWidth = Math.min(tileWidth * 2, width - leftWidth);
        int topHeight = Math.min(tileHeight * 2, height / 2);
        int bottomHeight = Math.min(tileHeight * 2, height - topHeight);
        int centerWidth = Math.max(0, width - leftWidth - rightWidth);
        int centerHeight = Math.max(0, height - topHeight - bottomHeight);
        int sourceLeftWidth = tileWidth * 2;
        int sourceRightX = tileWidth * 3;
        int sourceTopHeight = tileHeight * 2;
        int sourceBottomY = tileHeight * 3;
        this.drawTextureRegion(context, texture, x, y, leftWidth, topHeight, 0, 0, sourceLeftWidth, sourceTopHeight, size);
        this.drawTextureRegion(context, texture, x + width - rightWidth, y, rightWidth, topHeight, sourceRightX, 0, sourceLeftWidth, sourceTopHeight, size);
        this.drawTextureRegion(context, texture, x, y + height - bottomHeight, leftWidth, bottomHeight, 0, sourceBottomY, sourceLeftWidth, sourceTopHeight, size);
        this.drawTextureRegion(context, texture, x + width - rightWidth, y + height - bottomHeight, rightWidth, bottomHeight, sourceRightX, sourceBottomY, sourceLeftWidth, sourceTopHeight, size);
        if (centerWidth > 0) {
            this.drawTiledRegionHorizontal(context, texture, x + leftWidth, y, centerWidth, topHeight, tileWidth * 2, 0, tileWidth, sourceTopHeight, size);
            this.drawTiledRegionHorizontal(context, texture, x + leftWidth, y + height - bottomHeight, centerWidth, bottomHeight, tileWidth * 2, sourceBottomY, tileWidth, sourceTopHeight, size);
        }
        if (centerHeight > 0) {
            this.drawTiledRegionVertical(context, texture, x, y + topHeight, leftWidth, centerHeight, 0, tileHeight * 2, sourceLeftWidth, tileHeight, size);
            this.drawTiledRegionVertical(context, texture, x + width - rightWidth, y + topHeight, rightWidth, centerHeight, sourceRightX, tileHeight * 2, sourceLeftWidth, tileHeight, size);
        }
        if (centerWidth > 0 && centerHeight > 0) {
            this.drawTiledRegion(context, texture, x + leftWidth, y + topHeight, centerWidth, centerHeight, tileWidth * 2, tileHeight * 2, tileWidth, tileHeight, size);
        }
    }

    private void drawTiledRegionHorizontal(class_332 context, class_2960 texture, int x, int y, int drawWidth, int drawHeight, int u, int v, int regionWidth, int regionHeight, ClientTextureHelper.TextureSize size) {
        int segmentWidth;
        for (int drawn = 0; drawn < drawWidth; drawn += segmentWidth) {
            segmentWidth = Math.min(regionWidth, drawWidth - drawn);
            this.drawTextureRegion(context, texture, x + drawn, y, segmentWidth, drawHeight, u, v, segmentWidth, regionHeight, size);
        }
    }

    private void drawTiledRegionVertical(class_332 context, class_2960 texture, int x, int y, int drawWidth, int drawHeight, int u, int v, int regionWidth, int regionHeight, ClientTextureHelper.TextureSize size) {
        int segmentHeight;
        for (int drawn = 0; drawn < drawHeight; drawn += segmentHeight) {
            segmentHeight = Math.min(regionHeight, drawHeight - drawn);
            this.drawTextureRegion(context, texture, x, y + drawn, drawWidth, segmentHeight, u, v, regionWidth, segmentHeight, size);
        }
    }

    private void drawTiledRegion(class_332 context, class_2960 texture, int x, int y, int drawWidth, int drawHeight, int u, int v, int regionWidth, int regionHeight, ClientTextureHelper.TextureSize size) {
        int segmentHeight;
        for (int drawnY = 0; drawnY < drawHeight; drawnY += segmentHeight) {
            int segmentWidth;
            segmentHeight = Math.min(regionHeight, drawHeight - drawnY);
            for (int drawnX = 0; drawnX < drawWidth; drawnX += segmentWidth) {
                segmentWidth = Math.min(regionWidth, drawWidth - drawnX);
                this.drawTextureRegion(context, texture, x + drawnX, y + drawnY, segmentWidth, segmentHeight, u, v, segmentWidth, segmentHeight, size);
            }
        }
    }

    private void drawTextureRegion(class_332 context, class_2960 texture, int x, int y, int drawWidth, int drawHeight, int u, int v, int regionWidth, int regionHeight, ClientTextureHelper.TextureSize size) {
        if (drawWidth <= 0 || drawHeight <= 0 || regionWidth <= 0 || regionHeight <= 0) {
            return;
        }
        context.method_25293(texture, x, y, drawWidth, drawHeight, (float)u, (float)v, regionWidth, regionHeight, size.width(), size.height());
    }

    private ClientTextureHelper.TextureSize resolveTextureSize(class_2960 texture) {
        ClientTextureHelper.TextureSize cached = TEXTURE_SIZE_CACHE.get(texture);
        if (cached != null) {
            return cached;
        }
        ClientTextureHelper.TextureSize resolved = ClientTextureHelper.resolveTextureSize(this.field_22787, texture);
        TEXTURE_SIZE_CACHE.put(texture, resolved);
        return resolved;
    }

    private CharacterSelectionOption resolveKingdomOption(String kingdomId) {
        class_2960 id = class_2960.method_12829((String)kingdomId);
        if (id == null) {
            return null;
        }
        return GotCharacterOptions.getByKingdomId(id);
    }

    private class_2960 kingdomIconFor(CastleMapPayload.Entry castle, CharacterSelectionOption fallbackOption) {
        class_2960 houseTexture;
        CastleMapPayload.DynamicKingdomEntry dynamicKingdom = CastleMapClientState.getDynamicKingdom(castle.kingdomId());
        if (dynamicKingdom != null && !dynamicKingdom.houseId().isBlank() && (houseTexture = PlayerHouseRenderHelper.resolveTexture(dynamicKingdom.houseId(), castle.kingdomId())) != null) {
            return houseTexture;
        }
        return fallbackOption == null ? null : fallbackOption.texture();
    }

    private static String kingdomDisplayName(String kingdomId, CharacterSelectionOption fallbackOption) {
        CastleMapPayload.DynamicKingdomEntry dynamicKingdom = CastleMapClientState.getDynamicKingdom(kingdomId);
        if (dynamicKingdom != null && !dynamicKingdom.displayName().isBlank()) {
            return dynamicKingdom.displayName();
        }
        if (fallbackOption != null && !fallbackOption.displayName().isBlank()) {
            return fallbackOption.displayName();
        }
        class_2960 id = class_2960.method_12829((String)kingdomId);
        Kingdom kingdom = id == null ? null : GotKingdoms.get(id);
        return kingdom == null || kingdom.getName().isBlank() ? kingdomId : kingdom.getName();
    }

    private ReligionSelectionOption resolveReligionOption(String religionId) {
        return religionId == null || religionId.isBlank() ? null : GotReligionOptions.get(religionId);
    }

    @Environment(value=EnvType.CLIENT)
    private record MapHoverTarget(CastleMapPayload.Entry castle, VillageMapPayload.Entry village) {
    }

    @Environment(value=EnvType.CLIENT)
    private record TooltipLayout(CastleMapPayload.Entry castle, int x, int y, int width, int height, int contentX, int contentY, boolean locked, int buttonX, int buttonY) {
    }

    @Environment(value=EnvType.CLIENT)
    private record WarPartyTooltipLayout(String lordName, String etaLabel, int x, int y, int width, int height, int contentX, int contentY) {
    }

    @Environment(value=EnvType.CLIENT)
    private record QuestTooltipLayout(QuestJournalClientState.QuestLocationMarker marker, String questName, int x, int y, int width, int height, int contentX, int contentY) {
    }

    @Environment(value=EnvType.CLIENT)
    private record VillageTooltipLayout(VillageMapPayload.Entry village, int x, int y, int width, int height, int contentX, int contentY, boolean locked, int buttonX, int buttonY) {
    }

    @Environment(value=EnvType.CLIENT)
    private record ClippedLine(float startX, float startY, float endX, float endY) {
    }

    @Environment(value=EnvType.CLIENT)
    private record ButtonBounds(int x, int y, int size) {
        private boolean contains(double mouseX, double mouseY) {
            return mouseX >= (double)this.x && mouseX < (double)(this.x + this.size) && mouseY >= (double)this.y && mouseY < (double)(this.y + this.size);
        }
    }
}

