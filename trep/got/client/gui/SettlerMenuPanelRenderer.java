/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1799
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 */
package trep.got.client.gui;

import java.util.List;
import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import trep.got.client.util.ClientTextureHelper;
import trep.got.network.OpenCastleScreenPayload;

@Environment(value=EnvType.CLIENT)
public final class SettlerMenuPanelRenderer {
    public static final class_2960 PANEL_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/character_party.png");
    public static final class_2960 REMOVE_ICON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/icon_minus.png");
    public static final int PANEL_TEXTURE_SIZE = 256;
    public static final int PANEL_WIDTH = 144;
    public static final int PANEL_HEIGHT = 144;
    public static final int PANEL_GAP = 4;
    private static final int CONTENT_X = 8;
    private static final int CONTENT_Y = 8;
    private static final int CONTENT_WIDTH = 128;
    private static final int HEADER_HEIGHT = 12;
    private static final int ENTRY_HEIGHT = 14;
    private static final int ENTRY_EXPANDED_HEIGHT = 31;
    private static final int ENTRY_GAP = 3;
    private static final int ICON_SIZE = 16;
    private static final int ENTRY_BORDER = -7637163;
    private static final int ENTRY_FILL = 857084685;
    private static final int ENTRY_HOVER = 1150273535;
    private static final int ENTRY_SELECTED = 1429875222;
    private static final int TEXT_Y = 4;
    private static final int TOOL_X = 4;
    private static final int HOUSE_X = 18;
    private static final int NAME_X = 31;
    private static final int NAME_VIEWPORT_WIDTH = 74;
    private static final int REMOVE_ICON_X = 108;
    private static final int ACTION_ICON_Y_OFFSET = 11;

    private SettlerMenuPanelRenderer() {
    }

    public static void render(class_332 context, int x, int y, int mouseX, int mouseY, List<OpenCastleScreenPayload.SettlerEntry> entries, String selectedSettlerId, int settlerCount, int capacity) {
        context.method_25291(PANEL_TEXTURE, x, y, 0, 0.0f, 0.0f, 144, 144, 256, 256);
        class_327 textRenderer = class_310.method_1551().field_1772;
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)("Settlers " + settlerCount + "/" + capacity + ":")), x + 8, y + 8, -1);
        int entryX = x + 8;
        int entryY = y + 8 + 12;
        for (OpenCastleScreenPayload.SettlerEntry entry : entries) {
            boolean expanded = entry.settlerId().equals(selectedSettlerId);
            int entryHeight = expanded ? 31 : 14;
            boolean hovered = SettlerMenuPanelRenderer.isWithin(mouseX, mouseY, entryX, entryY, 128, entryHeight);
            SettlerMenuPanelRenderer.drawEntry(context, textRenderer, entry, entryX, entryY, expanded, hovered, mouseX, mouseY);
            if ((entryY += entryHeight + 3) <= y + 144 - 8) continue;
            break;
        }
    }

    public static String handleClick(int panelX, int panelY, double mouseX, double mouseY, List<OpenCastleScreenPayload.SettlerEntry> entries, String selectedSettlerId) {
        int entryX = panelX + 8;
        int entryY = panelY + 8 + 12;
        for (OpenCastleScreenPayload.SettlerEntry entry : entries) {
            int entryHeight;
            boolean expanded = entry.settlerId().equals(selectedSettlerId);
            int n = entryHeight = expanded ? 31 : 14;
            if (SettlerMenuPanelRenderer.isWithin(mouseX, mouseY, entryX, entryY, 128, entryHeight)) {
                if (expanded && SettlerMenuPanelRenderer.isWithin(mouseX, mouseY, entryX + 108, entryY + 11, 16, 16)) {
                    return "remove:" + entry.settlerId();
                }
                return expanded ? "" : entry.settlerId();
            }
            entryY += entryHeight + 3;
        }
        return null;
    }

    public static boolean isWithinPanel(int panelX, int panelY, double mouseX, double mouseY) {
        return SettlerMenuPanelRenderer.isWithin(mouseX, mouseY, panelX, panelY, 144, 144);
    }

    private static void drawEntry(class_332 context, class_327 textRenderer, OpenCastleScreenPayload.SettlerEntry entry, int x, int y, boolean expanded, boolean hovered, int mouseX, int mouseY) {
        int height = expanded ? 31 : 14;
        context.method_25294(x, y, x + 128, y + height, expanded ? 1429875222 : 857084685);
        context.method_49601(x, y, 128, height, -7637163);
        if (hovered) {
            context.method_25294(x + 1, y + 1, x + 128 - 1, y + height - 1, 1150273535);
        }
        SettlerMenuPanelRenderer.drawItem(context, entry.toolStack(), x + 4, y + 2);
        SettlerMenuPanelRenderer.drawTexture(context, class_2960.method_60654((String)entry.houseTextureId()), x + 18, y + 2, 9, 9);
        SettlerMenuPanelRenderer.drawScrollingName(context, textRenderer, entry.displayName(), x + 31, y + 4, hovered);
        if (!expanded) {
            return;
        }
        int iconY = y + 11;
        context.method_25291(REMOVE_ICON_TEXTURE, x + 108, iconY, 0, 0.0f, 0.0f, 16, 16, 16, 16);
        if (SettlerMenuPanelRenderer.isWithin(mouseX, mouseY, x + 108, iconY, 16, 16)) {
            context.method_51438(textRenderer, (class_2561)class_2561.method_43470((String)"Remove Settler"), mouseX, mouseY);
        }
    }

    private static void drawScrollingName(class_332 context, class_327 textRenderer, String name, int x, int y, boolean hovered) {
        int textWidth = textRenderer.method_1727(name);
        if (textWidth <= 74) {
            context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)name), x, y, 0xFFFFFF);
            return;
        }
        Objects.requireNonNull(textRenderer);
        context.method_44379(x, y, x + 74, y + 9 + 2);
        int overflow = textWidth - 74;
        int offset = 0;
        if (hovered) {
            offset = Math.max(0, Math.min(overflow, (int)(System.currentTimeMillis() / 40L % (long)(overflow + 20)) - 10));
        }
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)name), x - offset, y, 0xFFFFFF);
        context.method_44380();
    }

    private static void drawTexture(class_332 context, class_2960 texture, int x, int y, int width, int height) {
        ClientTextureHelper.TextureSize size = texture.method_12832().contains("/sigil/") ? ClientTextureHelper.resolveTextureSize(class_310.method_1551(), texture) : new ClientTextureHelper.TextureSize(16, 16);
        float scale = Math.min((float)width / (float)size.width(), (float)height / (float)size.height());
        int offsetX = Math.max(0, Math.round(((float)width - (float)size.width() * scale) / 2.0f));
        int offsetY = Math.max(0, Math.round(((float)height - (float)size.height() * scale) / 2.0f));
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)(x + offsetX), (float)(y + offsetY), 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
        context.method_51448().method_22909();
    }

    private static void drawItem(class_332 context, class_1799 stack, int x, int y) {
        if (stack == null || stack.method_7960()) {
            return;
        }
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(0.5625f, 0.5625f, 1.0f);
        context.method_51427(stack, 0, 0);
        context.method_51448().method_22909();
    }

    private static boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }
}

