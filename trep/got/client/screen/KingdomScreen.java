/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_437
 *  net.minecraft.class_5250
 *  net.minecraft.class_5348
 *  net.minecraft.class_5481
 *  net.minecraft.class_8710
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.screen;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_5250;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import net.minecraft.class_8710;
import org.jetbrains.annotations.Nullable;
import trep.got.castle.CastleTier;
import trep.got.character.CharacterEquipmentPreset;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.house.PlayerHouseClientState;
import trep.got.client.house.PlayerHouseRenderHelper;
import trep.got.client.house.PlayerSigilTextureManager;
import trep.got.client.kingdom.KingdomEventLogClientState;
import trep.got.client.map.CastleMapClientState;
import trep.got.client.map.KingdomTerritoryClientState;
import trep.got.client.registry.GotKeyBindings;
import trep.got.client.relations.KingdomRelationsClientState;
import trep.got.client.util.ClientJsonConfigHelper;
import trep.got.client.util.ClientTextureHelper;
import trep.got.kingdom.Kingdom;
import trep.got.network.CastleMapPayload;
import trep.got.network.KingdomEventLogPayload;
import trep.got.network.PlayerHouseStatePayload;
import trep.got.network.RequestKingdomDiplomacyPayload;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotKingdoms;
import trep.got.relations.KingdomRelationTier;

@Environment(value=EnvType.CLIENT)
public final class KingdomScreen
extends class_437 {
    private static final class_2960 MAP_BACKGROUND = class_2960.method_60655((String)"minecraft", (String)"textures/map/map_background.png");
    private static final class_2960 MAP_BACKGROUND_CHECKERBOARD = class_2960.method_60655((String)"minecraft", (String)"textures/map/map_background_checkerboard.png");
    private static final class_2960 BROWN_MAP_TEXTURE = class_2960.method_60655((String)"agob", (String)"map_brownscaled.png");
    private static final class_2960 KINGDOM_SLOT = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_slot.png");
    private static final class_2960 KINGDOM_SLOT_ACTIVE = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_slot_active.png");
    private static final class_2960 KINGDOM_TOGGLE = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_toggle.png");
    private static final class_2960 KINGDOM_TOGGLE_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_toggle_hover.png");
    private static final class_2960 KINGDOM_ENTRY = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_entry.png");
    private static final class_2960 RELATION_ENEMY = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_relation_enemy.png");
    private static final class_2960 RELATION_SUSPICIOUS = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_relation_suspicious.png");
    private static final class_2960 RELATION_NEUTRAL = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_relation_neutral.png");
    private static final class_2960 RELATION_FRIEND = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_relation_friend.png");
    private static final class_2960 RELATION_ALLY = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_relation_ally.png");
    private static final class_2960 RELATION_SELECTOR = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_relation_selector.png");
    private static final class_2960 LOG_CONTAINER = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_log_container.png");
    private static final class_2960 LOG_ENTRY = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_log_entry.png");
    private static final class_2960 DIALOGUE_OPTION_WIDE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/dialogue_option_wide.png");
    private static final class_2960 CASTLE_WOOD_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/castle_wood.png");
    private static final class_2960 CASTLE_STONE_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/castle_stone.png");
    private static final class_2960 CASTLE_CAPITAL_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/castle_capital.png");
    private static final int OUTER_MARGIN = 24;
    private static final int FRAME_PADDING = 18;
    private static final int MAP_LEFT_PANEL_WIDTH = 58;
    private static final int MAP_RIGHT_PANEL_WIDTH = 144;
    private static final int SLOT_SIZE = 20;
    private static final int SLOT_GAP = 4;
    private static final int SLOT_COLUMNS = 2;
    private static final int KINGDOM_RAIL_WIDTH = 44;
    private static final int KINGDOM_TOGGLE_WIDTH = 64;
    private static final int KINGDOM_TOGGLE_HEIGHT = 32;
    private static final int KINGDOM_TOGGLE_GAP = 4;
    private static final int ENTRY_WIDTH = 128;
    private static final int ENTRY_HEIGHT = 176;
    private static final int ENTRY_TEXT_X = 10;
    private static final int ENTRY_TEXT_Y = 12;
    private static final int ENTRY_SCALE_ICON = 10;
    private static final int ENTRY_TITLE_OFFSET_Y = 12;
    private static final int ENTRY_KING_OFFSET_Y = 26;
    private static final int ENTRY_CASTLES_LABEL_OFFSET_Y = 46;
    private static final int ENTRY_CASTLE_COUNTS_OFFSET_Y = 60;
    private static final int ENTRY_RELATIONS_LABEL_OFFSET_Y = 72;
    private static final int ENTRY_RELATION_ROW_OFFSET_Y = 86;
    private static final int ENTRY_HOUSES_LABEL_OFFSET_Y = 108;
    private static final int ENTRY_HOUSE_ROW_OFFSET_Y = 122;
    private static final int ENTRY_WAR_LABEL_OFFSET_Y = 140;
    private static final int ENTRY_WAR_ROW_OFFSET_Y = 154;
    private static final int ROOT_X_OFFSET = 32;
    private static final int RELATION_ICON_SIZE = 14;
    private static final int RELATION_ICON_GAP = 4;
    private static final int RELATION_SELECTOR_SIZE = 18;
    private static final int HOUSE_SIGIL_SIZE = 12;
    private static final int HOUSE_SIGIL_GAP = 6;
    private static final int MAX_INLINE_SIGILS = 4;
    private static final int LOG_PANEL_WIDTH = 128;
    private static final int LOG_PANEL_HEIGHT = 64;
    private static final int LOG_PANEL_TOP_GAP = 8;
    private static final int LOG_VIEWPORT_PADDING_Y = 8;
    private static final int LOG_ENTRY_TEXTURE_WIDTH = 128;
    private static final int LOG_ENTRY_TEXTURE_HEIGHT = 48;
    private static final int LOG_ENTRY_SLICE_HEIGHT = 16;
    private static final int LOG_ENTRY_DRAW_WIDTH = 112;
    private static final int LOG_ENTRY_TEXT_PADDING_X = 10;
    private static final int LOG_ENTRY_TEXT_PADDING_Y = 6;
    private static final int LOG_ENTRY_GAP = 4;
    private static final int LOG_ENTRY_ICON_SIZE = 12;
    private static final int LOG_ENTRY_ICON_GAP = 5;
    private static final float MIN_ZOOM = 1.0f;
    private static final float MAX_ZOOM = 6.0f;
    private static final float ZOOM_STEP = 0.25f;
    private static final int DIPLOMACY_BUTTON_WIDTH = 96;
    private static final int DIPLOMACY_BUTTON_HEIGHT = 16;
    private static final int DIPLOMACY_BUTTON_BOTTOM_MARGIN = 14;
    private static final Map<String, class_2960> SIGILS = Map.ofEntries(Map.entry("agob:the_north", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_north.png")), Map.entry("agob:the_nights_watch", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_nights_watch.png")), Map.entry("agob:wildling", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_blank.png")), Map.entry("agob:the_vale", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_vale.png")), Map.entry("agob:the_riverlands", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_riverlands.png")), Map.entry("agob:the_westerlands", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_westerlands.png")), Map.entry("agob:iron_islands", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_ironborn.png")), Map.entry("agob:the_reach", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_reach.png")), Map.entry("agob:the_stormlands", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_stormlands.png")), Map.entry("agob:dorne", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_dorne.png")), Map.entry("agob:the_crownlands", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_crownlands.png")), Map.entry("agob:targaryen", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_targaryen.png")), Map.entry("agob:braavos", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_braavos.png")), Map.entry("agob:pentos", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_pentos.png")), Map.entry("agob:volantis", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_volantis.png")), Map.entry("agob:tyrosh", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_tyrosh.png")), Map.entry("agob:myr", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_pahl.png")), Map.entry("agob:blackfyre", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_blackfyre.png")), Map.entry("agob:golden_company", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_goldencompany.png")), Map.entry("agob:meereen", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_meereen.png")), Map.entry("agob:yunkai", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_yunkai.png")), Map.entry("agob:astapor", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_astapor.png")), Map.entry("agob:qarth", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_qarth.png")));
    private static float cachedZoom = 1.0f;
    private static float cachedViewOriginX = 0.0f;
    private static float cachedViewOriginY = 0.0f;
    private static String cachedSelectedKingdomId = "agob:the_north";
    private static final String CONFIG_FILE_NAME = "got_kingdom_screen.json";
    private static boolean cachedShowingEssos;
    private static boolean preferencesLoaded;
    private int frameX;
    private int frameY;
    private int frameWidth;
    private int frameHeight;
    private int mapX;
    private int mapY;
    private int mapSize;
    private float zoom = cachedZoom;
    private float viewOriginX;
    private float viewOriginY;
    private boolean leftDragging;
    private double lastDragMouseX;
    private double lastDragMouseY;
    private double eventLogScrollY;
    private String selectedKingdomId = cachedSelectedKingdomId;
    private boolean showingEssos = cachedShowingEssos;
    private String lastEventLogKingdomId = "";
    private int lastMouseX;
    private int lastMouseY;

    public KingdomScreen() {
        super((class_2561)class_2561.method_43470((String)"Kingdom"));
        KingdomScreen.loadPreferences(class_310.method_1551());
        this.showingEssos = cachedShowingEssos;
        if (!this.currentKingdomOrder().contains(this.selectedKingdomId)) {
            cachedSelectedKingdomId = this.selectedKingdomId = this.currentKingdomOrder().getFirst();
        }
    }

    public static void open() {
        class_310 client = class_310.method_1551();
        client.method_1507((class_437)new KingdomScreen());
    }

    private static void loadPreferences(class_310 client) {
        if (preferencesLoaded) {
            return;
        }
        preferencesLoaded = true;
        ClientJsonConfigHelper.load(client, CONFIG_FILE_NAME, "Failed to load kingdom screen client state").ifPresent(root -> {
            boolean bl = cachedShowingEssos = root.has("showing_essos") && root.get("showing_essos").getAsBoolean();
            if (root.has("selected_kingdom_id")) {
                String selected = root.get("selected_kingdom_id").getAsString();
                if (KingdomScreen.currentKingdomOrder(true).contains(selected) || KingdomScreen.currentKingdomOrder(false).contains(selected)) {
                    cachedSelectedKingdomId = selected;
                }
            }
        });
    }

    private static void savePreferences(class_310 client) {
        if (client == null) {
            return;
        }
        JsonObject root = new JsonObject();
        root.addProperty("showing_essos", Boolean.valueOf(cachedShowingEssos));
        root.addProperty("selected_kingdom_id", cachedSelectedKingdomId);
        ClientJsonConfigHelper.save(client, CONFIG_FILE_NAME, root, "Failed to save kingdom screen client state");
    }

    protected void method_57734(float delta) {
    }

    protected void method_25426() {
        super.method_25426();
        this.viewOriginX = cachedViewOriginX;
        this.viewOriginY = cachedViewOriginY;
        if (!this.currentKingdomOrder().contains(this.selectedKingdomId)) {
            cachedSelectedKingdomId = this.selectedKingdomId = this.currentKingdomOrder().getFirst();
        }
        this.snapEventLogToBottom();
        this.clampView();
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        String displayKingdomId;
        this.lastMouseX = mouseX;
        this.lastMouseY = mouseY;
        this.frameHeight = this.field_22790 - 48;
        this.mapSize = this.frameHeight - 36;
        this.frameWidth = this.mapSizeTargetWidth();
        this.frameX = (this.field_22789 - this.frameWidth) / 2;
        this.frameY = (this.field_22790 - this.frameHeight) / 2 - 16;
        this.mapX = (this.field_22789 - this.mapSize) / 2 - 32;
        this.mapY = this.frameY + 18;
        this.frameX = this.mapX - 58 - 18;
        String previewKingdomId = this.hoveredKingdom(mouseX, mouseY);
        String string = displayKingdomId = previewKingdomId != null ? previewKingdomId : this.selectedKingdomId;
        if (!this.selectedKingdomId.equals(this.lastEventLogKingdomId)) {
            this.snapEventLogToBottom();
            this.lastEventLogKingdomId = this.selectedKingdomId;
        }
        context.method_25290(MAP_BACKGROUND, this.mapX - 18, this.mapY - 18, 0.0f, 0.0f, this.mapSize + 36, this.mapSize + 36, this.mapSize + 36, this.mapSize + 36);
        context.method_25290(MAP_BACKGROUND_CHECKERBOARD, this.mapX, this.mapY, 0.0f, 0.0f, this.mapSize, this.mapSize, this.mapSize, this.mapSize);
        this.clampView();
        float viewportSize = this.getViewportSize();
        context.method_25293(BROWN_MAP_TEXTURE, this.mapX, this.mapY, this.mapSize, this.mapSize, this.viewOriginX, this.viewOriginY, class_3532.method_15386((float)viewportSize), class_3532.method_15386((float)viewportSize), 3000, 3000);
        class_2960 territoryTexture = KingdomTerritoryClientState.getTexture(CastleMapClientState.getCastles(), displayKingdomId, this.field_22787);
        if (territoryTexture != null) {
            context.method_25293(territoryTexture, this.mapX, this.mapY, this.mapSize, this.mapSize, this.viewOriginX, this.viewOriginY, class_3532.method_15386((float)viewportSize), class_3532.method_15386((float)viewportSize), 3000, 3000);
        }
        this.drawKingdomRail(context, mouseX, mouseY);
        this.drawKingdomEntry(context);
        this.drawDiplomacyButton(context, mouseX, mouseY);
        String hoveredKingdomId = this.hoveredKingdom(mouseX, mouseY);
        if (hoveredKingdomId != null) {
            context.method_51438(this.field_22793, (class_2561)class_2561.method_43470((String)KingdomScreen.kingdomDisplayName(hoveredKingdomId)), mouseX, mouseY);
        } else {
            KingdomRelationTier hoveredRelationTier = this.hoveredRelationTier(mouseX, mouseY);
            if (hoveredRelationTier != null) {
                context.method_51438(this.field_22793, (class_2561)class_2561.method_43470((String)KingdomScreen.relationLabel(hoveredRelationTier)), mouseX, mouseY);
            } else {
                CharacterSelectionOption hoveredHouse = this.hoveredHouseOption(mouseX, mouseY);
                if (hoveredHouse != null) {
                    context.method_51438(this.field_22793, (class_2561)class_2561.method_43470((String)hoveredHouse.houseDisplayName()), mouseX, mouseY);
                } else {
                    List<SigilTooltipEntry> houseOverflow = this.hoveredHouseOverflow(mouseX, mouseY);
                    if (!houseOverflow.isEmpty()) {
                        this.drawSigilListTooltip(context, mouseX, mouseY, houseOverflow);
                    } else {
                        String hoveredWarKingdomId = this.hoveredWarKingdom(mouseX, mouseY);
                        if (hoveredWarKingdomId != null) {
                            context.method_51438(this.field_22793, (class_2561)class_2561.method_43470((String)KingdomScreen.kingdomDisplayName(hoveredWarKingdomId)), mouseX, mouseY);
                        } else {
                            class_2561 tooltip;
                            List<SigilTooltipEntry> warOverflow = this.hoveredWarOverflow(mouseX, mouseY);
                            if (!warOverflow.isEmpty()) {
                                this.drawSigilListTooltip(context, mouseX, mouseY, warOverflow);
                            } else if (this.isInsideDiplomacyButton(mouseX, mouseY) && (tooltip = this.diplomacyTooltip()) != null) {
                                context.method_51438(this.field_22793, tooltip, mouseX, mouseY);
                            }
                        }
                    }
                }
            }
        }
        super.method_25394(context, mouseX, mouseY, delta);
    }

    public boolean method_25421() {
        return false;
    }

    public void method_25432() {
        cachedShowingEssos = this.showingEssos;
        cachedSelectedKingdomId = this.selectedKingdomId;
        KingdomScreen.savePreferences(this.field_22787);
        super.method_25432();
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button == 0) {
            if (this.isInsideKingdomToggle(mouseX, mouseY)) {
                this.toggleContinent();
                return true;
            }
            String hovered = this.hoveredKingdom(mouseX, mouseY);
            if (hovered != null) {
                this.selectedKingdomId = hovered;
                cachedSelectedKingdomId = hovered;
                KingdomScreen.savePreferences(this.field_22787);
                this.snapEventLogToBottom();
                return true;
            }
            if (this.isInsideDiplomacyButton(mouseX, mouseY)) {
                this.handleDiplomacyButtonPress();
                return true;
            }
            if (this.isInsideMap(mouseX, mouseY)) {
                this.leftDragging = true;
                this.lastDragMouseX = mouseX;
                this.lastDragMouseY = mouseY;
                return true;
            }
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        if (button == 0) {
            this.leftDragging = false;
            return true;
        }
        return super.method_25406(mouseX, mouseY, button);
    }

    public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (button == 0 && this.leftDragging) {
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
        if (this.isInsideEventLog(mouseX, mouseY) && verticalAmount != 0.0) {
            this.eventLogScrollY -= verticalAmount * 14.0;
            this.clampEventLogScroll();
            return true;
        }
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
        if (KingdomScreen.isBoundKeyPressed(GotKeyBindings.KINGDOM_SCREEN, keyCode, scanCode)) {
            this.method_25419();
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    private void drawKingdomRail(class_332 context, int mouseX, int mouseY) {
        int buttonX = this.kingdomToggleX();
        int buttonY = this.kingdomToggleY();
        boolean toggleHovered = this.isInsideKingdomToggle(mouseX, mouseY);
        context.method_25290(toggleHovered ? KINGDOM_TOGGLE_HOVER : KINGDOM_TOGGLE, buttonX, buttonY, 0.0f, 0.0f, 64, 32, 64, 32);
        class_5250 class_52502 = class_2561.method_43470((String)(this.showingEssos ? "Essos" : "Westeros"));
        Objects.requireNonNull(this.field_22793);
        context.method_27534(this.field_22793, (class_2561)class_52502, buttonX + 32, buttonY + (32 - 9) / 2, toggleHovered ? 0xFFFF55 : 0xFFFFFF);
        int startX = this.kingdomRailX();
        int startY = this.kingdomRailY();
        List<String> kingdoms = this.currentKingdomOrder();
        for (int index = 0; index < kingdoms.size(); ++index) {
            String kingdomId = kingdoms.get(index);
            int slotX = startX + index % 2 * 24;
            int slotY = startY + index / 2 * 24;
            boolean active = kingdomId.equals(this.selectedKingdomId) || kingdomId.equals(this.hoveredKingdom(mouseX, mouseY));
            context.method_25290(active ? KINGDOM_SLOT_ACTIVE : KINGDOM_SLOT, slotX, slotY, 0.0f, 0.0f, 20, 20, 20, 20);
            class_2960 sigil = KingdomScreen.sigilForKingdom(kingdomId);
            if (sigil == null) continue;
            int sigilSize = 8;
            int sigilX = slotX + (20 - sigilSize) / 2;
            int sigilY = slotY + (20 - sigilSize) / 2;
            this.drawFittedTexture(context, sigil, sigilX, sigilY, sigilSize, sigilSize);
        }
    }

    private void drawKingdomEntry(class_332 context) {
        KingdomSummary summary = this.buildSummary(this.selectedKingdomId);
        int entryX = this.kingdomEntryX();
        int entryY = this.kingdomEntryY();
        context.method_25290(KINGDOM_ENTRY, entryX, entryY, 0.0f, 0.0f, 128, 176, 128, 176);
        int textX = entryX + 10;
        context.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)KingdomScreen.kingdomDisplayName(summary.kingdomId())), textX, entryY + 12, -596021);
        context.method_25303(this.field_22793, "King: " + summary.kingName(), textX, entryY + 26, -1780034);
        context.method_25303(this.field_22793, "Castles:", textX, entryY + 46, -1780034);
        this.drawCastleCount(context, CASTLE_CAPITAL_ICON, summary.capitals(), textX, entryY + 60);
        this.drawCastleCount(context, CASTLE_STONE_ICON, summary.lordships(), textX + 24, entryY + 60);
        this.drawCastleCount(context, CASTLE_WOOD_ICON, summary.settlements(), textX + 48, entryY + 60);
        context.method_25303(this.field_22793, "Relations:", textX, entryY + 72, -1780034);
        this.drawRelationRow(context, textX, entryY + 86, summary.kingdomId());
        context.method_25303(this.field_22793, "Houses:", textX, entryY + 108, -1780034);
        this.drawHouseRow(context, textX, entryY + 122, summary.kingdomId());
        context.method_25303(this.field_22793, "War:", textX, entryY + 140, -1780034);
        this.drawWarRow(context, textX, entryY + 154, summary.kingdomId());
        this.drawEventLog(context);
    }

    private void drawDiplomacyButton(class_332 context, int mouseX, int mouseY) {
        if (!this.shouldShowDiplomacyButton()) {
            return;
        }
        int buttonX = this.diplomacyButtonX();
        int buttonY = this.diplomacyButtonY();
        boolean hovered = this.isInsideDiplomacyButton(mouseX, mouseY);
        boolean onCooldown = this.diplomacyCooldownRemainingTicks() > 0L;
        context.method_51448().method_22903();
        context.method_51448().method_46416(0.0f, 0.0f, 200.0f);
        context.method_25290(DIALOGUE_OPTION_WIDE_TEXTURE, buttonX, buttonY, 0.0f, 0.0f, 96, 16, 96, 16);
        int color = onCooldown ? -6645094 : (hovered ? -3896 : -1);
        context.method_27534(this.field_22793, this.diplomacyButtonText(), buttonX + 48, buttonY + 4, color);
        context.method_51448().method_22909();
    }

    private void drawHouseRow(class_332 context, int x, int y, String kingdomId) {
        List<CharacterSelectionOption> houses = this.housesForKingdom(kingdomId);
        int visibleCount = Math.min(4, houses.size());
        for (int index = 0; index < visibleCount; ++index) {
            CharacterSelectionOption house = houses.get(index);
            int iconX = x + index * 18;
            this.drawFittedTexture(context, house.texture(), iconX, y, 12, 12);
        }
        if (houses.size() > 4) {
            this.drawOverflowIndicator(context, x + 72, y);
        }
    }

    private void drawWarRow(class_332 context, int x, int y, String kingdomId) {
        List<SigilTooltipEntry> warTargets = this.warSigilEntries(kingdomId);
        if (warTargets.isEmpty()) {
            context.method_25303(this.field_22793, "None", x, y + 2, -4609396);
            return;
        }
        int visibleCount = Math.min(4, warTargets.size());
        for (int index = 0; index < visibleCount; ++index) {
            SigilTooltipEntry warTarget = warTargets.get(index);
            int iconX = x + index * 18;
            this.drawFittedTexture(context, warTarget.texture(), iconX, y, 12, 12);
        }
        if (warTargets.size() > 4) {
            this.drawOverflowIndicator(context, x + 72, y);
        }
    }

    private void drawOverflowIndicator(class_332 context, int x, int y) {
        context.method_25303(this.field_22793, "+", x + 2, y + 2, -1);
    }

    private void drawFittedTexture(class_332 context, class_2960 texture, int x, int y, int boxWidth, int boxHeight) {
        ClientTextureHelper.TextureSize size = ClientTextureHelper.resolveTextureSize(class_310.method_1551(), texture);
        float scale = Math.min((float)boxWidth / (float)size.width(), (float)boxHeight / (float)size.height());
        int drawWidth = Math.max(1, Math.round((float)size.width() * scale));
        int drawHeight = Math.max(1, Math.round((float)size.height() * scale));
        int drawX = x + (boxWidth - drawWidth) / 2;
        int drawY = y + (boxHeight - drawHeight) / 2;
        context.method_25293(texture, drawX, drawY, drawWidth, drawHeight, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
    }

    private void drawRelationRow(class_332 context, int x, int y, String targetKingdomId) {
        String playerKingdomId = CharacterSelectionClientState.getSelectedKingdomId();
        KingdomRelationTier tier = KingdomRelationsClientState.getEffectivePlayerRelationTier(playerKingdomId, targetKingdomId);
        class_2960[] icons = new class_2960[]{RELATION_ENEMY, RELATION_SUSPICIOUS, RELATION_NEUTRAL, RELATION_FRIEND, RELATION_ALLY};
        for (int index = 0; index < icons.length; ++index) {
            int iconX = x + index * 18;
            context.method_25290(icons[index], iconX, y, 0.0f, 0.0f, 14, 14, 14, 14);
        }
        int selectorIndex = switch (tier) {
            default -> throw new MatchException(null, null);
            case KingdomRelationTier.ENEMY -> 0;
            case KingdomRelationTier.SUSPICIOUS -> 1;
            case KingdomRelationTier.NEUTRAL -> 2;
            case KingdomRelationTier.FRIEND -> 3;
            case KingdomRelationTier.ALLY -> 4;
        };
        int selectorX = x + selectorIndex * 18 - 2;
        int selectorY = y - 2;
        context.method_25290(RELATION_SELECTOR, selectorX, selectorY, 0.0f, 0.0f, 18, 18, 18, 18);
    }

    private KingdomRelationTier hoveredRelationTier(int mouseX, int mouseY) {
        int entryX = this.kingdomEntryX();
        int entryY = this.kingdomEntryY();
        int relationY = entryY + 86;
        int relationX = entryX + 10;
        KingdomRelationTier[] tiers = new KingdomRelationTier[]{KingdomRelationTier.ENEMY, KingdomRelationTier.SUSPICIOUS, KingdomRelationTier.NEUTRAL, KingdomRelationTier.FRIEND, KingdomRelationTier.ALLY};
        for (int index = 0; index < tiers.length; ++index) {
            int iconX = relationX + index * 18;
            if (mouseX < iconX || mouseX >= iconX + 14 || mouseY < relationY || mouseY >= relationY + 14) continue;
            return tiers[index];
        }
        return null;
    }

    private CharacterSelectionOption hoveredHouseOption(int mouseX, int mouseY) {
        int x = this.kingdomEntryX() + 10;
        int y = this.kingdomEntryY() + 122;
        List<CharacterSelectionOption> houses = this.housesForKingdom(this.selectedKingdomId);
        for (int index = 0; index < Math.min(4, houses.size()); ++index) {
            int iconX = x + index * 18;
            if (mouseX < iconX || mouseX >= iconX + 12 || mouseY < y || mouseY >= y + 12) continue;
            return houses.get(index);
        }
        return null;
    }

    private List<SigilTooltipEntry> hoveredHouseOverflow(int mouseX, int mouseY) {
        int x = this.kingdomEntryX() + 10 + 72;
        int y = this.kingdomEntryY() + 122;
        List<CharacterSelectionOption> houses = this.housesForKingdom(this.selectedKingdomId);
        if (houses.size() <= 4 || mouseX < x || mouseX >= x + 12 || mouseY < y || mouseY >= y + 12) {
            return List.of();
        }
        ArrayList<SigilTooltipEntry> entries = new ArrayList<SigilTooltipEntry>();
        for (int index = 4; index < houses.size(); ++index) {
            CharacterSelectionOption house = houses.get(index);
            entries.add(new SigilTooltipEntry((class_2561)class_2561.method_43470((String)house.houseDisplayName()), house.texture()));
        }
        return List.copyOf(entries);
    }

    private String hoveredWarKingdom(int mouseX, int mouseY) {
        int x = this.kingdomEntryX() + 10;
        int y = this.kingdomEntryY() + 154;
        List<SigilTooltipEntry> warTargets = this.warSigilEntries(this.selectedKingdomId);
        for (int index = 0; index < Math.min(4, warTargets.size()); ++index) {
            int iconX = x + index * 18;
            if (mouseX < iconX || mouseX >= iconX + 12 || mouseY < y || mouseY >= y + 12) continue;
            return warTargets.get(index).kingdomId();
        }
        return null;
    }

    private List<SigilTooltipEntry> hoveredWarOverflow(int mouseX, int mouseY) {
        int x = this.kingdomEntryX() + 10 + 72;
        int y = this.kingdomEntryY() + 154;
        List<SigilTooltipEntry> warTargets = this.warSigilEntries(this.selectedKingdomId);
        if (warTargets.size() <= 4 || mouseX < x || mouseX >= x + 12 || mouseY < y || mouseY >= y + 12) {
            return List.of();
        }
        return List.copyOf(warTargets.subList(4, warTargets.size()));
    }

    private static String relationLabel(KingdomRelationTier tier) {
        return switch (tier) {
            default -> throw new MatchException(null, null);
            case KingdomRelationTier.ENEMY -> "Enemy";
            case KingdomRelationTier.SUSPICIOUS -> "Suspicious";
            case KingdomRelationTier.NEUTRAL -> "Neutral";
            case KingdomRelationTier.FRIEND -> "Friend";
            case KingdomRelationTier.ALLY -> "Ally";
        };
    }

    private static List<String> sortedWarTargets(String kingdomId) {
        ArrayList<String> warTargets = new ArrayList<String>(KingdomRelationsClientState.getActiveWarTargets(kingdomId));
        warTargets.sort(String::compareTo);
        return List.copyOf(warTargets);
    }

    private List<SigilTooltipEntry> warSigilEntries(String kingdomId) {
        ArrayList<SigilTooltipEntry> entries = new ArrayList<SigilTooltipEntry>();
        for (String warTarget : KingdomScreen.sortedWarTargets(kingdomId)) {
            class_2960 sigil = KingdomScreen.sigilForKingdom(warTarget);
            if (sigil == null) continue;
            entries.add(new SigilTooltipEntry((class_2561)class_2561.method_43470((String)KingdomScreen.kingdomDisplayName(warTarget)), sigil, warTarget));
        }
        return List.copyOf(entries);
    }

    private void drawSigilListTooltip(class_332 context, int mouseX, int mouseY, List<SigilTooltipEntry> entries) {
        if (entries.isEmpty()) {
            return;
        }
        int iconSize = 12;
        Objects.requireNonNull(this.field_22793);
        int lineHeight = Math.max(9, iconSize) + 3;
        int maxTextWidth = 0;
        for (SigilTooltipEntry entry : entries) {
            maxTextWidth = Math.max(maxTextWidth, this.field_22793.method_27525((class_5348)entry.label()));
        }
        int boxX = mouseX + 12;
        int boxY = mouseY - 4;
        int width = iconSize + 8 + maxTextWidth + 10;
        int height = entries.size() * lineHeight + 8;
        context.method_51448().method_22903();
        context.method_51448().method_46416(0.0f, 0.0f, 400.0f);
        context.method_25294(boxX - 4, boxY - 4, boxX + width, boxY + height, -267386864);
        context.method_49601(boxX - 4, boxY - 4, width + 4, height + 4, -1862866997);
        int lineY = boxY;
        for (SigilTooltipEntry entry : entries) {
            this.drawFittedTexture(context, entry.texture(), boxX, lineY, iconSize, iconSize);
            context.method_51439(this.field_22793, entry.label(), boxX + iconSize + 6, lineY + 2, -596021, false);
            lineY += lineHeight;
        }
        context.method_51448().method_22909();
    }

    private void drawCastleCount(class_332 context, class_2960 icon, int count, int x, int y) {
        context.method_25290(icon, x, y, 0.0f, 0.0f, 10, 10, 10, 10);
        context.method_25303(this.field_22793, String.valueOf(count), x + 10 + 2, y + 1, -1780034);
    }

    private KingdomSummary buildSummary(String kingdomId) {
        List<CastleMapPayload.Entry> owned = CastleMapClientState.getCastles().stream().filter(castle -> kingdomId.equals(castle.kingdomId())).sorted(Comparator.comparing(CastleMapPayload.Entry::name)).toList();
        int capitals = 0;
        int lordships = 0;
        int settlements = 0;
        String kingName = "Unknown";
        for (CastleMapPayload.Entry castle2 : owned) {
            CastleTier tier = CastleTier.byId(castle2.tierId());
            switch (tier) {
                case CAPITAL: {
                    ++capitals;
                    if (!"Unknown".equals(kingName)) break;
                    kingName = castle2.lordName();
                    break;
                }
                case LORDSHIP: {
                    ++lordships;
                    break;
                }
                case SETTLEMENT: {
                    ++settlements;
                }
            }
        }
        if ("Unknown".equals(kingName) && !owned.isEmpty()) {
            kingName = owned.getFirst().lordName();
        }
        return new KingdomSummary(kingdomId, kingName, capitals, lordships, settlements);
    }

    private String hoveredKingdom(double mouseX, double mouseY) {
        int startX = this.kingdomRailX();
        int startY = this.kingdomRailY();
        List<String> kingdoms = this.currentKingdomOrder();
        for (int index = 0; index < kingdoms.size(); ++index) {
            int slotX = startX + index % 2 * 24;
            int slotY = startY + index / 2 * 24;
            if (!(mouseX >= (double)slotX) || !(mouseX < (double)(slotX + 20)) || !(mouseY >= (double)slotY) || !(mouseY < (double)(slotY + 20))) continue;
            return kingdoms.get(index);
        }
        return null;
    }

    private List<String> currentKingdomOrder() {
        return KingdomScreen.currentKingdomOrder(this.showingEssos);
    }

    private static List<String> currentKingdomOrder(boolean showingEssos) {
        List<String> filtered = Stream.concat(GotCharacterOptions.getPrimaryOptions().stream().map(option -> option.kingdomId().toString()), GotKingdoms.getAll().stream().map(kingdom -> kingdom.getId().toString())).distinct().filter(kingdomId -> showingEssos ? GotKingdoms.isEssosKingdom(kingdomId) : GotKingdoms.isWesterosiKingdom(kingdomId)).toList();
        if (!filtered.isEmpty()) {
            return filtered;
        }
        return GotCharacterOptions.getPrimaryOptions().stream().map(option -> option.kingdomId().toString()).distinct().toList();
    }

    private int kingdomRailX() {
        return this.kingdomToggleX() + 10;
    }

    private int kingdomRailY() {
        return this.mapY + 32 + 4;
    }

    private int kingdomToggleX() {
        int panelRight = this.mapX - 18;
        int preferredX = panelRight - 64;
        return class_3532.method_15340((int)preferredX, (int)2, (int)Math.max(2, this.field_22789 - 64 - 2));
    }

    private int kingdomToggleY() {
        return this.mapY;
    }

    private boolean isInsideKingdomToggle(double mouseX, double mouseY) {
        int x = this.kingdomToggleX();
        int y = this.kingdomToggleY();
        return mouseX >= (double)x && mouseX < (double)(x + 64) && mouseY >= (double)y && mouseY < (double)(y + 32);
    }

    private void toggleContinent() {
        cachedShowingEssos = this.showingEssos = !this.showingEssos;
        if (!this.currentKingdomOrder().contains(this.selectedKingdomId)) {
            cachedSelectedKingdomId = this.selectedKingdomId = this.currentKingdomOrder().getFirst();
            this.snapEventLogToBottom();
        }
        KingdomScreen.savePreferences(this.field_22787);
    }

    private boolean isInsideMap(double mouseX, double mouseY) {
        return mouseX >= (double)this.mapX && mouseX < (double)(this.mapX + this.mapSize) && mouseY >= (double)this.mapY && mouseY < (double)(this.mapY + this.mapSize);
    }

    private void drawEventLog(class_332 context) {
        int panelX = this.eventLogPanelX();
        int panelY = this.eventLogPanelY();
        int panelWidth = this.eventLogPanelWidth();
        int panelHeight = this.eventLogPanelHeight();
        if (panelHeight <= 16) {
            return;
        }
        RenderSystem.enableBlend();
        context.method_25291(LOG_CONTAINER, panelX, panelY, 0, 0.0f, 0.0f, panelWidth, panelHeight, panelWidth, panelHeight);
        int viewportX = panelX + (panelWidth - 112) / 2;
        int viewportY = panelY + 8;
        int viewportWidth = 112;
        int viewportHeight = panelHeight - 16;
        List<KingdomEventLogPayload.LogEntry> lines = KingdomEventLogClientState.getLogEntries(this.selectedKingdomId);
        this.clampEventLogScroll();
        context.method_44379(viewportX, viewportY, viewportX + viewportWidth, viewportY + viewportHeight);
        if (lines.isEmpty()) {
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"No world events logged"), panelX + panelWidth / 2, viewportY + viewportHeight / 2 - 4, -4609396);
            context.method_44380();
            return;
        }
        int drawY = viewportY - (int)this.eventLogScrollY;
        for (KingdomEventLogPayload.LogEntry line : lines) {
            int availableTextWidth = viewportWidth - 20 - this.logEntryIconWidth(line);
            List<class_5481> wrappedLines = this.wrapLogLine(line.line(), availableTextWidth);
            int entryHeight = this.logEntryHeight(wrappedLines);
            if (drawY + entryHeight >= viewportY && drawY <= viewportY + viewportHeight) {
                this.drawLogEntryBackground(context, viewportX, drawY, entryHeight);
                int textX = viewportX + 10;
                class_2960 icon = this.parseLogEntryIcon(line);
                if (icon != null) {
                    int iconY = drawY + Math.max(6, (entryHeight - 12) / 2);
                    this.drawFittedTexture(context, icon, textX, iconY, 12, 12);
                    textX += 17;
                }
                int n = wrappedLines.size();
                Objects.requireNonNull(this.field_22793);
                int totalTextHeight = n * 9 + Math.max(0, wrappedLines.size() - 1);
                int textY = drawY + Math.max(6, (entryHeight - totalTextHeight) / 2);
                for (class_5481 wrappedLine : wrappedLines) {
                    context.method_35720(this.field_22793, wrappedLine, textX, textY, -596021);
                    Objects.requireNonNull(this.field_22793);
                    textY += 9 + 1;
                }
            }
            drawY += entryHeight + 4;
        }
        context.method_44380();
        RenderSystem.disableBlend();
    }

    private int eventLogPanelX() {
        return this.kingdomEntryX();
    }

    private int eventLogPanelY() {
        return this.kingdomEntryY() + 176 + 8 - 8;
    }

    private int eventLogPanelWidth() {
        return 128;
    }

    private int eventLogPanelHeight() {
        return 64;
    }

    private boolean isInsideEventLog(double mouseX, double mouseY) {
        int panelX = this.eventLogPanelX();
        int panelY = this.eventLogPanelY();
        int panelWidth = this.eventLogPanelWidth();
        int panelHeight = this.eventLogPanelHeight();
        return panelHeight > 0 && mouseX >= (double)panelX && mouseX < (double)(panelX + panelWidth) && mouseY >= (double)panelY && mouseY < (double)(panelY + panelHeight);
    }

    private void snapEventLogToBottom() {
        this.eventLogScrollY = this.maxEventLogScroll();
    }

    private void clampEventLogScroll() {
        this.eventLogScrollY = class_3532.method_15350((double)this.eventLogScrollY, (double)0.0, (double)this.maxEventLogScroll());
    }

    private double maxEventLogScroll() {
        int contentHeight = 0;
        for (KingdomEventLogPayload.LogEntry line : KingdomEventLogClientState.getLogEntries(this.selectedKingdomId)) {
            int availableTextWidth = 92 - this.logEntryIconWidth(line);
            contentHeight += this.logEntryHeight(this.wrapLogLine(line.line(), availableTextWidth)) + 4;
        }
        int viewportHeight = Math.max(0, this.eventLogPanelHeight() - 16);
        return Math.max(0.0, (double)(contentHeight - viewportHeight));
    }

    private int kingdomEntryX() {
        return this.mapX + this.mapSize + 22;
    }

    private int kingdomEntryY() {
        return this.mapY + (this.mapSize - 176) / 2 - 24;
    }

    private boolean shouldShowDiplomacyButton() {
        return KingdomRelationsClientState.isPlayerKing() && !KingdomRelationsClientState.getPlayerKingdomId().isBlank() && !KingdomRelationsClientState.getPlayerKingdomId().equals(this.selectedKingdomId);
    }

    private int diplomacyButtonX() {
        return this.mapX + (this.mapSize - 96) / 2;
    }

    private int diplomacyButtonY() {
        return this.mapY + this.mapSize - 16 - 14;
    }

    private boolean isInsideDiplomacyButton(double mouseX, double mouseY) {
        if (!this.shouldShowDiplomacyButton()) {
            return false;
        }
        int x = this.diplomacyButtonX();
        int y = this.diplomacyButtonY();
        return mouseX >= (double)x && mouseX < (double)(x + 96) && mouseY >= (double)y && mouseY < (double)(y + 16);
    }

    private class_2561 diplomacyButtonText() {
        return class_2561.method_43470((String)(KingdomRelationsClientState.isAtWar(KingdomRelationsClientState.getPlayerKingdomId(), this.selectedKingdomId) ? "Propose Peace" : "Declare War"));
    }

    private long diplomacyCooldownRemainingTicks() {
        class_310 client = class_310.method_1551();
        if (client.field_1687 == null) {
            return 0L;
        }
        long endsAtTick = KingdomRelationsClientState.getDiplomacyCooldownEndsAtTick(this.selectedKingdomId);
        return Math.max(0L, endsAtTick - client.field_1687.method_8510());
    }

    @Nullable
    private class_2561 diplomacyTooltip() {
        long remainingTicks = this.diplomacyCooldownRemainingTicks();
        if (remainingTicks <= 0L) {
            return null;
        }
        long totalSeconds = (remainingTicks + 19L) / 20L;
        long minutes = totalSeconds / 60L;
        long seconds = totalSeconds % 60L;
        return class_2561.method_43470((String)String.format(Locale.ROOT, "Cooldown %02d:%02d", minutes, seconds));
    }

    private void handleDiplomacyButtonPress() {
        if (!this.shouldShowDiplomacyButton() || this.diplomacyCooldownRemainingTicks() > 0L) {
            return;
        }
        boolean proposePeace = KingdomRelationsClientState.isAtWar(KingdomRelationsClientState.getPlayerKingdomId(), this.selectedKingdomId);
        ClientPlayNetworking.send((class_8710)new RequestKingdomDiplomacyPayload(this.selectedKingdomId, proposePeace));
    }

    private List<class_5481> wrapLogLine(String line, int maxWidth) {
        return this.field_22793.method_1728((class_5348)class_2561.method_43470((String)line), Math.max(1, maxWidth));
    }

    private int logEntryIconWidth(KingdomEventLogPayload.LogEntry entry) {
        return this.parseLogEntryIcon(entry) == null ? 0 : 17;
    }

    @Nullable
    private class_2960 parseLogEntryIcon(KingdomEventLogPayload.LogEntry entry) {
        if (entry == null || entry.iconTextureId() == null || entry.iconTextureId().isBlank()) {
            return null;
        }
        return class_2960.method_12829((String)entry.iconTextureId());
    }

    private int logEntryHeight(List<class_5481> wrappedLines) {
        int lineCount = Math.max(1, wrappedLines.size());
        Objects.requireNonNull(this.field_22793);
        int contentHeight = 12 + lineCount * 9 + Math.max(0, lineCount - 1);
        int minimumHeight = 32;
        if (contentHeight <= minimumHeight) {
            return minimumHeight;
        }
        int extra = contentHeight - minimumHeight;
        int middleRepeats = Math.max(1, class_3532.method_15386((float)((float)extra / 16.0f)));
        return minimumHeight + middleRepeats * 16;
    }

    private void drawLogEntryBackground(class_332 context, int x, int y, int height) {
        int drawHeight;
        context.method_25291(LOG_ENTRY, x, y, 0, 0.0f, 0.0f, 112, 16, 128, 48);
        int middleHeight = Math.max(0, height - 32);
        int middleY = y + 16;
        for (int remainingMiddleHeight = middleHeight; remainingMiddleHeight > 0; remainingMiddleHeight -= drawHeight) {
            drawHeight = Math.min(16, remainingMiddleHeight);
            context.method_25291(LOG_ENTRY, x, middleY, 0, 0.0f, 16.0f, 112, drawHeight, 128, 48);
            middleY += drawHeight;
        }
        context.method_25291(LOG_ENTRY, x, y + height - 16, 0, 0.0f, 32.0f, 112, 16, 128, 48);
    }

    private float getViewportSize() {
        return 3000.0f / this.zoom;
    }

    private void clampView() {
        float viewportSize = this.getViewportSize();
        float maxOrigin = 3000.0f - viewportSize;
        this.viewOriginX = class_3532.method_15363((float)this.viewOriginX, (float)0.0f, (float)Math.max(0.0f, maxOrigin));
        this.viewOriginY = class_3532.method_15363((float)this.viewOriginY, (float)0.0f, (float)Math.max(0.0f, maxOrigin));
        cachedViewOriginX = this.viewOriginX;
        cachedViewOriginY = this.viewOriginY;
    }

    private static String path(String kingdomId) {
        int separator = kingdomId.indexOf(58);
        return separator >= 0 ? kingdomId.substring(separator + 1) : kingdomId;
    }

    private static String kingdomDisplayName(String kingdomId) {
        CharacterSelectionOption option;
        CastleMapPayload.DynamicKingdomEntry dynamicKingdom = CastleMapClientState.getDynamicKingdom(kingdomId);
        if (dynamicKingdom != null && !dynamicKingdom.displayName().isBlank()) {
            return dynamicKingdom.displayName();
        }
        class_2960 identifier = class_2960.method_12829((String)kingdomId);
        CharacterSelectionOption characterSelectionOption = option = identifier == null ? null : GotCharacterOptions.getByKingdomId(identifier);
        if (option != null && !option.displayName().isBlank()) {
            return option.displayName();
        }
        Kingdom kingdom = identifier == null ? null : GotKingdoms.get(identifier);
        return kingdom != null && !kingdom.getName().isBlank() ? kingdom.getName() : KingdomScreen.path(kingdomId);
    }

    private static class_2960 sigilForKingdom(String kingdomId) {
        CharacterSelectionOption option;
        class_2960 houseTexture;
        CastleMapPayload.DynamicKingdomEntry dynamicKingdom = CastleMapClientState.getDynamicKingdom(kingdomId);
        if (dynamicKingdom != null && !dynamicKingdom.houseId().isBlank() && (houseTexture = PlayerHouseRenderHelper.resolveTexture(dynamicKingdom.houseId(), kingdomId)) != null) {
            return houseTexture;
        }
        class_2960 identifier = class_2960.method_12829((String)kingdomId);
        CharacterSelectionOption characterSelectionOption = option = identifier == null ? null : GotCharacterOptions.getByKingdomId(identifier);
        if (option != null) {
            return option.texture();
        }
        return SIGILS.get(kingdomId);
    }

    private static boolean isBoundKeyPressed(class_304 binding, int keyCode, int scanCode) {
        return binding.method_1417(keyCode, scanCode);
    }

    private int mapSizeTargetWidth() {
        return 202 + this.mapSize + 36;
    }

    private List<CharacterSelectionOption> housesForKingdom(String kingdomId) {
        class_2960 id = class_2960.method_12829((String)kingdomId);
        if (id == null) {
            return List.of();
        }
        boolean dynamicKingdom = GotKingdoms.isDynamicKingdom(id) || CastleMapClientState.getDynamicKingdom(kingdomId) != null;
        ArrayList<CharacterSelectionOption> houses = dynamicKingdom ? new ArrayList<CharacterSelectionOption>() : new ArrayList<CharacterSelectionOption>(GotCharacterOptions.getByKingdom(id));
        for (PlayerHouseStatePayload.Entry customHouse : PlayerHouseClientState.getAll()) {
            if (!kingdomId.equals(customHouse.kingdomId())) continue;
            houses.add(new CharacterSelectionOption(customHouse.houseId(), id, PlayerSigilTextureManager.getTextureId(customHouse), null, false, KingdomScreen.kingdomDisplayName(kingdomId), customHouse.displayName(), false, new CharacterEquipmentPreset("", "", "", "", "", ""), Map.of(), Map.of(), "", List.of()));
        }
        return List.copyOf(houses);
    }

    @Environment(value=EnvType.CLIENT)
    private record KingdomSummary(String kingdomId, String kingName, int capitals, int lordships, int settlements) {
    }

    @Environment(value=EnvType.CLIENT)
    private record SigilTooltipEntry(class_2561 label, class_2960 texture, @Nullable String kingdomId) {
        private SigilTooltipEntry(class_2561 label, class_2960 texture) {
            this(label, texture, null);
        }
    }
}

