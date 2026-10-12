/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_437
 *  net.minecraft.class_8710
 */
package trep.got.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_8710;
import trep.got.client.gui.CharacterMenuClientState;
import trep.got.client.screen.UnitTreeScreen;
import trep.got.client.troop.TroopRosterClientState;
import trep.got.client.util.ClientTextureHelper;
import trep.got.network.RequestStoreTroopsPayload;
import trep.got.network.RequestSummonTroopsPayload;
import trep.got.network.RequestUpgradeTroopPayload;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.DynamicTroopRegistry;

@Environment(value=EnvType.CLIENT)
public final class PartyMenuPanelRenderer {
    public static final class_2960 PANEL_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/character_party.png");
    public static final class_2960 UNIT_ENTRY_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/unit_entry.png");
    public static final class_2960 UNIT_ENTRY_EXPANDED_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/unit_entry_uncollapsed.png");
    public static final class_2960 SCROLLER_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/scroller.png");
    public static final class_2960 UPGRADE_ICON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/icon_upgrade.png");
    public static final class_2960 UPGRADE_ICON_HOVER_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/icon_upgrade_hover.png");
    public static final class_2960 UPGRADE_ICON_LOCKED_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/icon_upgrade_locked.png");
    public static final class_2960 UNIT_TREE_ICON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/icon_unit_tree.png");
    public static final class_2960 SUMMON_BUTTON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/war_toggle.png");
    public static final class_2960 SUMMON_BUTTON_HOVER_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/war_toggle_hover.png");
    public static final class_2960 STORE_BUTTON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/unit_store.png");
    public static final class_2960 STORE_BUTTON_HOVER_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/unit_store_hover.png");
    public static final class_2960 STORE_OVERLAY_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/unit_store_chest.png");
    public static final class_2960 STORE_OVERLAY_HOVER_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/unit_store_chest_hover.png");
    public static final class_2960 DEFAULT_SIGIL_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_blank.png");
    private static final Map<class_2960, class_2960> SIGIL_TEXTURES = new LinkedHashMap<class_2960, class_2960>(Map.ofEntries(Map.entry(class_2960.method_60655((String)"agob", (String)"the_north"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_north.png")), Map.entry(class_2960.method_60655((String)"agob", (String)"the_vale"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_vale.png")), Map.entry(class_2960.method_60655((String)"agob", (String)"the_riverlands"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_riverlands.png")), Map.entry(class_2960.method_60655((String)"agob", (String)"the_westerlands"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_westerlands.png")), Map.entry(class_2960.method_60655((String)"agob", (String)"iron_islands"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_ironborn.png")), Map.entry(class_2960.method_60655((String)"agob", (String)"the_reach"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_reach.png")), Map.entry(class_2960.method_60655((String)"agob", (String)"the_stormlands"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_stormlands.png")), Map.entry(class_2960.method_60655((String)"agob", (String)"dorne"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_dorne.png")), Map.entry(class_2960.method_60655((String)"agob", (String)"the_crownlands"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_crownlands.png")), Map.entry(class_2960.method_60655((String)"agob", (String)"the_nights_watch"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_nights_watch.png")), Map.entry(class_2960.method_60655((String)"agob", (String)"wildling"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_blank.png"))));
    public static final int PANEL_TEXTURE_SIZE = 256;
    public static final int PANEL_U = 0;
    public static final int PANEL_V = 0;
    public static final int PANEL_WIDTH = 144;
    public static final int PANEL_HEIGHT = 144;
    public static final int PANEL_GAP = 4;
    public static final int FOOTER_GAP = 4;
    private static final int CONTENT_X = 8;
    private static final int CONTENT_Y = 8;
    private static final int CONTENT_WIDTH = 128;
    private static final int HEADER_HEIGHT = 12;
    private static final int ENTRY_HEIGHT = 16;
    private static final int ENTRY_EXPANDED_HEIGHT = 32;
    private static final int ENTRY_GAP = 3;
    private static final int ENTRY_HOVER = 1150273535;
    private static final int TEXT_NAME_X = 4;
    private static final int NAME_SIGIL_SIZE = 9;
    private static final int NAME_SIGIL_GAP = 4;
    private static final int TEXT_Y = 4;
    private static final int ICON_SIZE = 16;
    private static final int ACTION_MARGIN = 4;
    private static final int UNIT_TREE_ICON_X = 4;
    private static final int ACTION_ICON_Y_OFFSET = 12;
    private static final int ENTRY_DRAW_WIDTH = 96;
    private static final int ENTRY_SOURCE_WIDTH = 96;
    private static final int ENTRY_SOURCE_HEIGHT = 16;
    private static final int ENTRY_EXPANDED_SOURCE_HEIGHT = 32;
    private static final int ENTRY_LIST_MAX_VISIBLE = 6;
    private static final int ENTRY_VIEWPORT_HEIGHT = 111;
    public static final int SCROLLBAR_WIDTH = 6;
    public static final int SCROLLBAR_GAP = 3;
    public static final int SCROLLER_SOURCE_WIDTH = 6;
    public static final int SCROLLER_SOURCE_HEIGHT = 27;
    public static final int SCROLLBAR_TRACK_FILL = 1713377290;
    public static final int SCROLLBAR_TRACK_BORDER = -1433700523;
    private static final float FOOTER_SCALE = 0.5f;
    private static final int SUMMON_BUTTON_TEXTURE_WIDTH = 96;
    private static final int SUMMON_BUTTON_TEXTURE_HEIGHT = 32;
    private static final int STORE_BUTTON_TEXTURE_SIZE = 32;
    private static final int SUMMON_BUTTON_WIDTH = 48;
    private static final int SUMMON_BUTTON_HEIGHT = 16;
    private static final int STORE_BUTTON_SIZE = 16;
    private static final int FOOTER_ROW_WIDTH = 68;
    private static final int FOOTER_HEIGHT = Math.max(16, 16);
    private static final int FOOTER_X = 38;
    private static final int STORE_BUTTON_X = 38;
    private static final int STORE_BUTTON_Y = 148;
    private static final int SUMMON_BUTTON_X = 58;
    private static final int SUMMON_BUTTON_Y = 148;
    private static double scrollOffset;
    private static boolean scrollbarDragging;
    private static double scrollbarDragOffset;

    private PartyMenuPanelRenderer() {
    }

    public static void render(class_332 context, int x, int y, int mouseX, int mouseY) {
        context.method_25291(PANEL_TEXTURE, x, y, 0, 0.0f, 0.0f, 144, 144, 256, 256);
        class_310 client = class_310.method_1551();
        class_327 textRenderer = client.field_1772;
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)("Party " + TroopRosterClientState.getTotalCount() + "/" + TroopRosterClientState.getCapacity() + ":")), x + 8, y + 8, -1);
        List<TroopEntry> entries = PartyMenuPanelRenderer.getEntries();
        PartyMenuPanelRenderer.clampScroll(entries);
        int entryViewportX = x + 8;
        int entryViewportY = y + 8 + 12;
        boolean needsScrollbar = PartyMenuPanelRenderer.needsScrollbar(entries);
        int entryAreaWidth = 128 - (needsScrollbar ? 9 : 0);
        int entryWidth = Math.min(96, entryAreaWidth);
        int entryDrawX = entryViewportX + (entryAreaWidth - entryWidth) / 2;
        int contentHeight = PartyMenuPanelRenderer.contentHeight(entries);
        context.method_44379(entryViewportX, entryViewportY, entryViewportX + entryAreaWidth, entryViewportY + 111);
        int currentY = entryViewportY - (int)Math.floor(scrollOffset);
        for (TroopEntry entry : entries) {
            int entryHeight;
            boolean expanded = entry.troopId().equals(CharacterMenuClientState.getSelectedPartyTroopId());
            int n = entryHeight = expanded ? 32 : 16;
            if (currentY + entryHeight >= entryViewportY && currentY <= entryViewportY + 111) {
                boolean hovered = PartyMenuPanelRenderer.isWithin(mouseX, mouseY, entryDrawX, currentY, entryWidth, entryHeight);
                PartyMenuPanelRenderer.drawEntry(context, textRenderer, entry, entryDrawX, currentY, entryWidth, expanded, hovered, mouseX, mouseY);
            }
            currentY += entryHeight + 3;
        }
        context.method_44380();
        if (needsScrollbar) {
            PartyMenuPanelRenderer.drawScrollbar(context, x + 8 + entryAreaWidth + 3, entryViewportY, contentHeight, mouseX, mouseY);
        }
        boolean summonHovered = PartyMenuPanelRenderer.isWithin(mouseX, mouseY, x + 58, y + 148, 48, 16);
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)(x + 58), (float)(y + 148), 0.0f);
        context.method_51448().method_22905(0.5f, 0.5f, 1.0f);
        context.method_25291(summonHovered ? SUMMON_BUTTON_HOVER_TEXTURE : SUMMON_BUTTON_TEXTURE, 0, 0, 0, 0.0f, 0.0f, 96, 32, 96, 32);
        context.method_51448().method_22909();
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)"Summon"), x + 58 + (48 - textRenderer.method_1727("Summon")) / 2, y + 148 + 4, -1);
        boolean storeHovered = PartyMenuPanelRenderer.isWithin(mouseX, mouseY, x + 38, y + 148, 16, 16);
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)(x + 38), (float)(y + 148), 0.0f);
        context.method_51448().method_22905(0.5f, 0.5f, 1.0f);
        context.method_25291(storeHovered ? STORE_BUTTON_HOVER_TEXTURE : STORE_BUTTON_TEXTURE, 0, 0, 0, 0.0f, 0.0f, 32, 32, 32, 32);
        context.method_25291(storeHovered ? STORE_OVERLAY_HOVER_TEXTURE : STORE_OVERLAY_TEXTURE, 0, 0, 0, 0.0f, 0.0f, 32, 32, 32, 32);
        context.method_51448().method_22909();
    }

    public static boolean handleClick(int panelX, int panelY, double mouseX, double mouseY) {
        ScrollbarMetrics scrollbar;
        List<TroopEntry> entries = PartyMenuPanelRenderer.getEntries();
        PartyMenuPanelRenderer.clampScroll(entries);
        if (PartyMenuPanelRenderer.needsScrollbar(entries) && PartyMenuPanelRenderer.isWithin(mouseX, mouseY, (scrollbar = PartyMenuPanelRenderer.scrollbarMetrics(panelX, panelY, entries)).x(), scrollbar.y(), scrollbar.width(), scrollbar.height())) {
            if (PartyMenuPanelRenderer.isWithin(mouseX, mouseY, scrollbar.x(), scrollbar.thumbY(), scrollbar.width(), scrollbar.thumbHeight())) {
                scrollbarDragging = true;
                scrollbarDragOffset = mouseY - (double)scrollbar.thumbY();
            } else {
                PartyMenuPanelRenderer.setScrollFromThumbCenter(entries, mouseY, scrollbar.y(), scrollbar.thumbHeight());
            }
            return true;
        }
        ClickAction action = PartyMenuPanelRenderer.getClickAction(panelX, panelY, mouseX, mouseY, entries);
        if (action == null) {
            CharacterMenuClientState.setSelectedPartyTroopId("");
            return false;
        }
        switch (action.type().ordinal()) {
            case 0: {
                return true;
            }
            case 1: {
                CharacterMenuClientState.setSelectedPartyTroopId(action.troopId());
                break;
            }
            case 2: {
                CharacterMenuClientState.setSelectedPartyTroopId("");
                break;
            }
            case 3: {
                ClientPlayNetworking.send((class_8710)new RequestSummonTroopsPayload());
                break;
            }
            case 4: {
                ClientPlayNetworking.send((class_8710)new RequestStoreTroopsPayload());
                break;
            }
            case 5: {
                ClientPlayNetworking.send((class_8710)new RequestUpgradeTroopPayload(action.troopId()));
                break;
            }
            case 6: {
                class_310.method_1551().method_1507((class_437)new UnitTreeScreen(class_310.method_1551().field_1755, class_2960.method_12829((String)action.troopId())));
            }
        }
        return true;
    }

    public static boolean handleMouseDragged(int panelX, int panelY, double mouseX, double mouseY) {
        if (!scrollbarDragging) {
            return false;
        }
        List<TroopEntry> entries = PartyMenuPanelRenderer.getEntries();
        if (!PartyMenuPanelRenderer.needsScrollbar(entries)) {
            scrollbarDragging = false;
            return false;
        }
        ScrollbarMetrics scrollbar = PartyMenuPanelRenderer.scrollbarMetrics(panelX, panelY, entries);
        PartyMenuPanelRenderer.setScrollFromThumbY(entries, mouseY - scrollbarDragOffset, scrollbar.y(), scrollbar.thumbHeight());
        return true;
    }

    public static void endScrollbarDrag() {
        scrollbarDragging = false;
    }

    public static boolean handleMouseScrolled(int panelX, int panelY, double mouseX, double mouseY, double verticalAmount) {
        List<TroopEntry> entries = PartyMenuPanelRenderer.getEntries();
        if (!PartyMenuPanelRenderer.needsScrollbar(entries)) {
            scrollbarDragging = false;
            scrollOffset = 0.0;
            return false;
        }
        int viewportX = panelX + 8;
        int viewportY = panelY + 8 + 12;
        int entryWidth = 128;
        if (!PartyMenuPanelRenderer.isWithin(mouseX, mouseY, viewportX, viewportY, entryWidth + 3 + 6, 111)) {
            return false;
        }
        scrollOffset = class_3532.method_15350((double)(scrollOffset - verticalAmount * 19.0), (double)0.0, (double)PartyMenuPanelRenderer.maxScroll(entries));
        return true;
    }

    private static ClickAction getClickAction(int panelX, int panelY, double mouseX, double mouseY, List<TroopEntry> entries) {
        if (PartyMenuPanelRenderer.isWithin(mouseX, mouseY, panelX + 58, panelY + 148, 48, 16)) {
            return new ClickAction(ClickType.SUMMON, "");
        }
        if (PartyMenuPanelRenderer.isWithin(mouseX, mouseY, panelX + 38, panelY + 148, 16, 16)) {
            return new ClickAction(ClickType.STORE, "");
        }
        boolean hasScrollbar = PartyMenuPanelRenderer.needsScrollbar(entries);
        int entryAreaWidth = 128 - (hasScrollbar ? 9 : 0);
        int entryWidth = Math.min(96, entryAreaWidth);
        int entryX = panelX + 8 + (entryAreaWidth - entryWidth) / 2;
        int entryY = panelY + 8 + 12 - (int)Math.floor(scrollOffset);
        for (TroopEntry entry : entries) {
            int entryHeight;
            boolean expanded = entry.troopId().equals(CharacterMenuClientState.getSelectedPartyTroopId());
            int n = entryHeight = expanded ? 32 : 16;
            if (entryY + entryHeight >= panelY + 8 + 12 && entryY <= panelY + 8 + 12 + 111 && PartyMenuPanelRenderer.isWithin(mouseX, mouseY, entryX, entryY, entryWidth, entryHeight)) {
                if (expanded) {
                    int upgradeIconX = entryX + entryWidth - 4 - 16;
                    if (PartyMenuPanelRenderer.isWithin(mouseX, mouseY, upgradeIconX, entryY + 12, 16, 16)) {
                        if (entry.upgradeableCount() > 0) {
                            return new ClickAction(ClickType.UPGRADE, entry.troopId());
                        }
                        return new ClickAction(ClickType.NONE, entry.troopId());
                    }
                    if (PartyMenuPanelRenderer.isWithin(mouseX, mouseY, entryX + 4, entryY + 12, 16, 16)) {
                        return new ClickAction(ClickType.OPEN_TREE, entry.troopId());
                    }
                    return new ClickAction(ClickType.COLLAPSE, entry.troopId());
                }
                return new ClickAction(ClickType.SELECT, entry.troopId());
            }
            entryY += entryHeight + 3;
        }
        return null;
    }

    public static boolean isWithinPanel(int panelX, int panelY, double mouseX, double mouseY) {
        return mouseX >= (double)panelX && mouseX < (double)(panelX + 144) && mouseY >= (double)panelY && mouseY < (double)(panelY + 144 + 4 + FOOTER_HEIGHT);
    }

    private static void drawEntry(class_332 context, class_327 textRenderer, TroopEntry entry, int x, int y, int width, boolean expanded, boolean hovered, int mouseX, int mouseY) {
        int height = expanded ? 32 : 16;
        context.method_25291(expanded ? UNIT_ENTRY_EXPANDED_TEXTURE : UNIT_ENTRY_TEXTURE, x, y, 0, 0.0f, 0.0f, 96, expanded ? 32 : 16, 96, expanded ? 32 : 16);
        if (hovered) {
            context.method_25294(x + 1, y + 1, x + width - 1, y + height - 1, 1150273535);
        }
        int amountX = x + width - 30;
        PartyMenuPanelRenderer.drawName(context, textRenderer, entry, x + 4, y + 4, hovered, Math.max(18, amountX - (x + 4 + 9 + 4) - 4));
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)Integer.toString(entry.count())), amountX, y + 4, 0xFFFFFF);
        if (entry.upgradeableCount() > 0) {
            context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)("+" + entry.upgradeableCount())), amountX + 12, y + 4, -10929);
        }
        if (!expanded) {
            return;
        }
        int iconY = y + 12;
        int upgradeIconX = x + width - 4 - 16;
        PartyMenuPanelRenderer.drawActionIcon(context, UNIT_TREE_ICON_TEXTURE, x + 4, iconY, true, PartyMenuPanelRenderer.isWithin(mouseX, mouseY, x + 4, iconY, 16, 16));
        PartyMenuPanelRenderer.drawUpgradeIcon(context, upgradeIconX, iconY, entry.upgradeableCount() > 0, PartyMenuPanelRenderer.isWithin(mouseX, mouseY, upgradeIconX, iconY, 16, 16));
    }

    private static void drawActionIcon(class_332 context, class_2960 texture, int x, int y, boolean enabled, boolean hovered) {
        float brightness = hovered && enabled ? 1.15f : 1.0f;
        float alpha = enabled ? 1.0f : 0.35f;
        RenderSystem.setShaderColor((float)brightness, (float)brightness, (float)brightness, (float)alpha);
        context.method_25291(texture, x, y, 0, 0.0f, 0.0f, 16, 16, 16, 16);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private static void drawUpgradeIcon(class_332 context, int x, int y, boolean enabled, boolean hovered) {
        class_2960 texture = enabled ? (hovered ? UPGRADE_ICON_HOVER_TEXTURE : UPGRADE_ICON_TEXTURE) : UPGRADE_ICON_LOCKED_TEXTURE;
        context.method_25291(texture, x, y, 0, 0.0f, 0.0f, 16, 16, 16, 16);
    }

    private static void drawName(class_332 context, class_327 textRenderer, TroopEntry entry, int x, int y, boolean hovered, int nameViewportWidth) {
        class_2960 sigilTexture = PartyMenuPanelRenderer.resolveSigilTexture(entry.kingdomId());
        PartyMenuPanelRenderer.drawScaledFullTexture(context, sigilTexture, x, y, 9, 9);
        int textX = x + 9 + 4;
        String name = entry.displayName();
        int textWidth = textRenderer.method_1727(name);
        if (textWidth <= nameViewportWidth) {
            context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)name), textX, y, 0xFFFFFF);
            return;
        }
        Objects.requireNonNull(textRenderer);
        context.method_44379(textX, y, textX + nameViewportWidth, y + 9 + 2);
        int offset = 0;
        if (hovered) {
            long time = System.currentTimeMillis();
            int overflow = textWidth - nameViewportWidth;
            offset = Math.min(overflow, (int)(time / 40L % (long)(overflow + 20)) - 10);
            offset = Math.max(0, offset);
        }
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)name), textX - offset, y, 0xFFFFFF);
        context.method_44380();
    }

    private static void drawScaledFullTexture(class_332 context, class_2960 texture, int x, int y, int drawWidth, int drawHeight) {
        ClientTextureHelper.TextureSize size = ClientTextureHelper.resolveTextureSize(class_310.method_1551(), texture);
        float scale = Math.min((float)drawWidth / (float)size.width(), (float)drawHeight / (float)size.height());
        int offsetX = Math.max(0, Math.round(((float)drawWidth - (float)size.width() * scale) / 2.0f));
        int offsetY = Math.max(0, Math.round(((float)drawHeight - (float)size.height() * scale) / 2.0f));
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)(x + offsetX), (float)(y + offsetY), 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
        context.method_51448().method_22909();
    }

    private static class_2960 resolveSigilTexture(class_2960 kingdomId) {
        if (kingdomId == null) {
            return DEFAULT_SIGIL_TEXTURE;
        }
        return SIGIL_TEXTURES.getOrDefault(kingdomId, DEFAULT_SIGIL_TEXTURE);
    }

    private static List<TroopEntry> getEntries() {
        ArrayList<TroopEntry> entries = new ArrayList<TroopEntry>();
        for (Map.Entry<String, Integer> entry : TroopRosterClientState.troopCounts().entrySet()) {
            DynamicTroopDefinition definition = DynamicTroopRegistry.get(entry.getKey());
            String name = definition != null ? definition.displayName() : entry.getKey();
            int upgradeableCount = TroopRosterClientState.getUpgradeableCount(class_2960.method_12829((String)entry.getKey()));
            entries.add(new TroopEntry(entry.getKey(), definition != null ? definition.kingdomId() : null, name, entry.getValue(), upgradeableCount));
        }
        entries.sort(Comparator.comparing(TroopEntry::displayName));
        return entries;
    }

    private static boolean needsScrollbar(List<TroopEntry> entries) {
        return entries.size() > 6;
    }

    private static int contentHeight(List<TroopEntry> entries) {
        int height = 0;
        for (int index = 0; index < entries.size(); ++index) {
            boolean expanded = entries.get(index).troopId().equals(CharacterMenuClientState.getSelectedPartyTroopId());
            height += expanded ? 32 : 16;
            if (index >= entries.size() - 1) continue;
            height += 3;
        }
        return height;
    }

    private static double maxScroll(List<TroopEntry> entries) {
        return Math.max(0.0, (double)(PartyMenuPanelRenderer.contentHeight(entries) - 111));
    }

    private static void clampScroll(List<TroopEntry> entries) {
        if (!PartyMenuPanelRenderer.needsScrollbar(entries)) {
            scrollOffset = 0.0;
            return;
        }
        scrollOffset = class_3532.method_15350((double)scrollOffset, (double)0.0, (double)PartyMenuPanelRenderer.maxScroll(entries));
    }

    private static void drawScrollbar(class_332 context, int x, int y, int contentHeight, int mouseX, int mouseY) {
        ScrollbarMetrics scrollbar = PartyMenuPanelRenderer.scrollbarMetrics(x, y, contentHeight);
        context.method_25294(scrollbar.x(), scrollbar.y(), scrollbar.x() + scrollbar.width(), scrollbar.y() + scrollbar.height(), 1713377290);
        context.method_49601(scrollbar.x(), scrollbar.y(), scrollbar.width(), scrollbar.height(), -1433700523);
        boolean hovered = PartyMenuPanelRenderer.isWithin(mouseX, mouseY, scrollbar.x(), scrollbar.thumbY(), scrollbar.width(), scrollbar.thumbHeight());
        context.method_25291(SCROLLER_TEXTURE, scrollbar.x(), scrollbar.thumbY(), 0, 0.0f, 0.0f, 6, 27, 6, 27);
        if (hovered || scrollbarDragging) {
            context.method_25294(scrollbar.x(), scrollbar.thumbY(), scrollbar.x() + scrollbar.width(), scrollbar.thumbY() + scrollbar.thumbHeight(), 0x22FFFFFF);
        }
    }

    private static ScrollbarMetrics scrollbarMetrics(int panelX, int panelY, List<TroopEntry> entries) {
        return PartyMenuPanelRenderer.scrollbarMetrics(panelX + 8 + 122, panelY + 8 + 12, PartyMenuPanelRenderer.contentHeight(entries));
    }

    private static ScrollbarMetrics scrollbarMetrics(int x, int y, int contentHeight) {
        int height = 111;
        int thumbHeight = Math.min(height, 27);
        double maxScroll = Math.max(0.0, (double)(contentHeight - height));
        int thumbTravel = height - thumbHeight;
        int thumbY = y + (maxScroll <= 0.0 ? 0 : (int)Math.round(scrollOffset / maxScroll * (double)thumbTravel));
        return new ScrollbarMetrics(x, y, 6, height, thumbY, thumbHeight);
    }

    private static void setScrollFromThumbCenter(List<TroopEntry> entries, double mouseY, int scrollbarY, int thumbHeight) {
        PartyMenuPanelRenderer.setScrollFromThumbY(entries, mouseY - (double)thumbHeight / 2.0, scrollbarY, thumbHeight);
    }

    private static void setScrollFromThumbY(List<TroopEntry> entries, double thumbY, int scrollbarY, int thumbHeight) {
        double maxScroll = PartyMenuPanelRenderer.maxScroll(entries);
        if (maxScroll <= 0.0) {
            scrollOffset = 0.0;
            return;
        }
        ScrollbarMetrics scrollbar = PartyMenuPanelRenderer.scrollbarMetrics(0, scrollbarY, PartyMenuPanelRenderer.contentHeight(entries));
        int thumbTravel = scrollbar.height() - scrollbar.thumbHeight();
        if (thumbTravel <= 0) {
            scrollOffset = 0.0;
            return;
        }
        double clampedThumbY = class_3532.method_15350((double)thumbY, (double)scrollbarY, (double)(scrollbarY + thumbTravel));
        double progress = (clampedThumbY - (double)scrollbarY) / (double)thumbTravel;
        scrollOffset = class_3532.method_15350((double)(progress * maxScroll), (double)0.0, (double)maxScroll);
    }

    private static boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    @Environment(value=EnvType.CLIENT)
    private record TroopEntry(String troopId, class_2960 kingdomId, String displayName, int count, int upgradeableCount) {
    }

    @Environment(value=EnvType.CLIENT)
    private record ScrollbarMetrics(int x, int y, int width, int height, int thumbY, int thumbHeight) {
    }

    @Environment(value=EnvType.CLIENT)
    private record ClickAction(ClickType type, String troopId) {
    }

    @Environment(value=EnvType.CLIENT)
    private static enum ClickType {
        NONE,
        SELECT,
        COLLAPSE,
        SUMMON,
        STORE,
        UPGRADE,
        OPEN_TREE;

    }
}

