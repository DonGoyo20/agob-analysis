/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_1043
 *  net.minecraft.class_1044
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_8710
 */
package trep.got.client.screen;

import java.io.IOException;
import java.util.ArrayDeque;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_1043;
import net.minecraft.class_1044;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_8710;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.house.PlayerHouseClientState;
import trep.got.client.house.PlayerSigilTemplate;
import trep.got.house.PlayerHouseNameValidator;
import trep.got.network.PlayerHouseStatePayload;
import trep.got.network.RequestCreatePlayerHousePayload;
import trep.got.registry.GotCharacterOptions;

@Environment(value=EnvType.CLIENT)
public final class PlayerHouseSigilScreen
extends class_437 {
    private static final class_2960 MENU_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/dynamic_menu.png");
    private static final class_2960 TOOL_BUTTON = class_2960.method_60655((String)"agob", (String)"textures/ui/drawing/backing_button.png");
    private static final class_2960 TOOL_BUTTON_SELECTED = class_2960.method_60655((String)"agob", (String)"textures/ui/drawing/backing_button_selected.png");
    private static final class_2960 PAINT_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/drawing/paint.png");
    private static final class_2960 BRUSH_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/drawing/brush.png");
    private static final class_2960 FILL_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/drawing/fill.png");
    private static final class_2960 ERASER_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/drawing/eraser.png");
    private static final class_2960 CONFIRM_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/dialogue_option.png");
    private static final class_2960 PREVIEW_TEXTURE_ID = class_2960.method_60655((String)"agob", (String)"textures/ui/generated/player_house_preview");
    private static final int MENU_WIDTH = 160;
    private static final int MENU_HEIGHT = 80;
    private static final int TOOL_BUTTON_SIZE = 20;
    private static final int TOOL_ICON_SIZE = 16;
    private static final int CONTROL_CLUSTER_SHIFT_X = -4;
    private static final int TEXT_VIEWPORT_LEFT = 6;
    private static final int TEXT_VIEWPORT_RIGHT = 155;
    private static final int KINGDOM_LABEL_Y = 6;
    private static final int TEXT_BASELINE_Y = 14;
    private static final int CONFIRM_WIDTH = 64;
    private static final int CONFIRM_HEIGHT = 16;
    private static final int SLIDER_LABEL_X = 99;
    private static final int SLIDER_BAR_X = 109;
    private static final int SLIDER_BAR_WIDTH = 40;
    private static final int SLIDER_BAR_HEIGHT = 6;
    private static final int SLIDER_KNOB_WIDTH = 3;
    private static final int RED_SLIDER_Y = 35;
    private static final int GREEN_SLIDER_Y = 47;
    private static final int BLUE_SLIDER_Y = 59;
    private static final int CONFIRM_OFFSET_X = 8;
    private static final int CONFIRM_OFFSET_Y = 8;
    private final int targetEntityId;
    private final boolean completePledgeOnConfirm;
    private final int templateWidth;
    private final int templateHeight;
    private final int[] sigilColors;
    private Tool selectedTool = Tool.PAINT;
    private int red = 143;
    private int green = 29;
    private int blue = 33;
    private String houseName = "";
    private boolean drawing;
    private int activeSlider = -1;
    private class_1043 previewTexture;
    private boolean previewDirty = true;
    private int menuX;
    private int menuY;
    private int previewX;
    private int previewY;
    private int previewWidth;
    private int previewHeight;

    public PlayerHouseSigilScreen(int targetEntityId, boolean completePledgeOnConfirm) {
        super((class_2561)class_2561.method_43470((String)"Create House Sigil"));
        class_310 client = class_310.method_1551();
        this.targetEntityId = targetEntityId;
        this.completePledgeOnConfirm = completePledgeOnConfirm;
        this.templateWidth = PlayerSigilTemplate.width(client);
        this.templateHeight = PlayerSigilTemplate.height(client);
        this.sigilColors = new int[PlayerSigilTemplate.drawablePixelCount(client)];
        this.loadExistingHouseState();
    }

    public static void open(int targetEntityId) {
        PlayerHouseSigilScreen.open(targetEntityId, false);
    }

    public static void open(int targetEntityId, boolean completePledgeOnConfirm) {
        class_310.method_1551().method_1507((class_437)new PlayerHouseSigilScreen(targetEntityId, completePledgeOnConfirm));
    }

    public boolean method_25421() {
        return false;
    }

    protected void method_57734(float delta) {
    }

    public void method_25393() {
        super.method_25393();
        if (this.previewDirty) {
            this.rebuildPreviewTexture();
        }
    }

    public void method_25419() {
        if (this.previewTexture != null) {
            this.previewTexture.close();
            this.previewTexture = null;
        }
        super.method_25419();
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        super.method_25420(context, mouseX, mouseY, delta);
        this.menuX = (this.field_22789 - 160) / 2;
        this.menuY = Math.max(18, this.field_22790 / 2 - 118);
        this.layoutPreview();
        context.method_25290(MENU_TEXTURE, this.menuX, this.menuY, 0.0f, 0.0f, 160, 80, 160, 80);
        this.drawNameField(context);
        this.drawTools(context, mouseX, mouseY);
        this.drawColorPicker(context, mouseX, mouseY);
        this.drawConfirmButton(context, mouseX, mouseY);
        this.drawPreview(context);
        super.method_25394(context, mouseX, mouseY, delta);
    }

    public boolean method_25400(char chr, int modifiers) {
        if (!Character.isLetter(chr) && chr != ' ') {
            return false;
        }
        if (this.houseName.length() >= 15) {
            return true;
        }
        String candidate = this.houseName + chr;
        String sanitized = PlayerHouseNameValidator.sanitize(candidate);
        if (sanitized.length() <= 15) {
            this.houseName = sanitized;
        }
        return true;
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 259 && !this.houseName.isEmpty()) {
            this.houseName = this.houseName.substring(0, this.houseName.length() - 1).trim();
            return true;
        }
        if ((keyCode == 257 || keyCode == 335) && this.canConfirm()) {
            this.confirm();
            return true;
        }
        if (keyCode == 256) {
            this.method_25419();
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return super.method_25402(mouseX, mouseY, button);
        }
        if (this.handleToolClick(mouseX, mouseY) || this.handleSliderClick(mouseX, mouseY)) {
            return true;
        }
        if (PlayerHouseSigilScreen.isWithin(mouseX, mouseY, this.confirmButtonX(), this.confirmButtonY(), 64, 16) && this.canConfirm()) {
            this.confirm();
            return true;
        }
        if (this.paintAt(mouseX, mouseY)) {
            this.drawing = this.selectedTool != Tool.FILL;
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        if (button == 0) {
            this.drawing = false;
            this.activeSlider = -1;
        }
        return super.method_25406(mouseX, mouseY, button);
    }

    public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (button == 0 && this.activeSlider >= 0) {
            this.updateSlider(this.activeSlider, mouseX);
            return true;
        }
        if (button == 0 && this.drawing) {
            return this.paintAt(mouseX, mouseY);
        }
        return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
    }

    private void drawNameField(class_332 context) {
        String kingdomLabel = this.resolveKingdomLabel();
        String caret = this.field_22787 != null && (this.field_22787.field_1724 == null || this.field_22787.field_1724.field_6012 / 6 % 2 == 0) ? "|" : "";
        String text = this.houseName.isEmpty() ? caret : this.houseName + caret;
        int viewportX = this.menuX + 6;
        int viewportWidth = 150;
        int kingdomWidth = this.field_22793.method_1727(kingdomLabel);
        context.method_51433(this.field_22793, kingdomLabel, viewportX + Math.max(0, (viewportWidth - kingdomWidth) / 2), this.menuY + 6, -7701926, false);
        int textWidth = this.field_22793.method_1727(text);
        int textX = viewportX + Math.max(0, (viewportWidth - textWidth) / 2);
        context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)text), textX, this.menuY + 14, -1122382, false);
    }

    private void drawTools(class_332 context, int mouseX, int mouseY) {
        int startX = this.menuX + 10 + -4;
        int y = this.menuY + 42;
        Tool[] tools = Tool.values();
        for (int index = 0; index < tools.length; ++index) {
            int x = startX + index * 24;
            Tool tool = tools[index];
            boolean hovered = PlayerHouseSigilScreen.isWithin(mouseX, mouseY, x, y, 20, 20);
            context.method_25290(tool == this.selectedTool || hovered ? TOOL_BUTTON_SELECTED : TOOL_BUTTON, x, y, 0.0f, 0.0f, 20, 20, 20, 20);
            context.method_25290(tool.iconTexture(), x + 2, y + 2, 0.0f, 0.0f, 16, 16, 16, 16);
        }
    }

    private void drawColorPicker(class_332 context, int mouseX, int mouseY) {
        this.drawSlider(context, mouseX, mouseY, 0, this.red, 35, -38037);
        this.drawSlider(context, mouseX, mouseY, 1, this.green, 47, -9709951);
        this.drawSlider(context, mouseX, mouseY, 2, this.blue, 59, -9728513);
        context.method_25294(this.menuX + 150 + -4, this.menuY + 35, this.menuX + 156 + -4, this.menuY + 63, this.currentColor());
    }

    private void drawSlider(class_332 context, int mouseX, int mouseY, int slider, int value, int y, int accentColor) {
        int labelY = this.menuY + y - 1;
        int barX = this.menuX + 109 + -4;
        int barY = this.menuY + y;
        context.method_25294(barX, barY, barX + 40, barY + 6, -13818337);
        int fillWidth = Math.max(1, Math.round((float)value / 255.0f * 40.0f));
        context.method_25294(barX, barY, barX + fillWidth, barY + 6, accentColor);
        int knobX = barX + Math.max(0, Math.min(37, Math.round((float)value / 255.0f * 37.0f)));
        context.method_25294(knobX, barY - 1, knobX + 3, barY + 6 + 1, -924740);
        if (PlayerHouseSigilScreen.isWithin(mouseX, mouseY, barX, barY - 2, 40, 10) || this.activeSlider == slider) {
            context.method_51433(this.field_22793, Integer.toString(value), barX + 40 - this.field_22793.method_1727(Integer.toString(value)), labelY - 1, -924740, false);
        }
    }

    private void drawConfirmButton(class_332 context, int mouseX, int mouseY) {
        int x = this.confirmButtonX();
        int y = this.confirmButtonY();
        boolean hovered = PlayerHouseSigilScreen.isWithin(mouseX, mouseY, x, y, 64, 16);
        context.method_25290(CONFIRM_TEXTURE, x, y, 0.0f, 0.0f, 64, 16, 64, 16);
        int color = this.canConfirm() ? (hovered ? -596824 : -1) : -8424864;
        String label = "Confirm";
        context.method_51433(this.field_22793, label, x + (64 - this.field_22793.method_1727(label)) / 2, y + 4, color, false);
    }

    private void drawPreview(class_332 context) {
        if (this.previewTexture == null) {
            return;
        }
        context.method_25293(PREVIEW_TEXTURE_ID, this.previewX, this.previewY, this.previewWidth, this.previewHeight, 0.0f, 0.0f, this.templateWidth, this.templateHeight, this.templateWidth, this.templateHeight);
    }

    private void layoutPreview() {
        int availableTop = this.menuY + 80 + 12;
        int availableBottom = this.field_22790 - 22;
        int maxHeight = Math.max(this.templateHeight, availableBottom - availableTop);
        float scale = Math.min((float)this.field_22789 * 0.46f / (float)this.templateWidth, (float)maxHeight / (float)this.templateHeight);
        scale = Math.max(2.0f, Math.min(scale, 4.5f));
        this.previewWidth = Math.max(this.templateWidth, Math.round((float)this.templateWidth * scale));
        this.previewHeight = Math.max(this.templateHeight, Math.round((float)this.templateHeight * scale));
        this.previewX = (this.field_22789 - this.previewWidth) / 2;
        this.previewY = availableTop + Math.max(0, (maxHeight - this.previewHeight) / 2);
    }

    private boolean handleToolClick(double mouseX, double mouseY) {
        int startX = this.menuX + 10 + -4;
        int y = this.menuY + 42;
        Tool[] tools = Tool.values();
        for (int index = 0; index < tools.length; ++index) {
            int x = startX + index * 24;
            if (!PlayerHouseSigilScreen.isWithin(mouseX, mouseY, x, y, 20, 20)) continue;
            this.selectedTool = tools[index];
            return true;
        }
        return false;
    }

    private boolean handleSliderClick(double mouseX, double mouseY) {
        int[] sliderYs = new int[]{35, 47, 59};
        for (int slider = 0; slider < sliderYs.length; ++slider) {
            int barX = this.menuX + 109 + -4;
            int barY = this.menuY + sliderYs[slider];
            if (!PlayerHouseSigilScreen.isWithin(mouseX, mouseY, barX, barY - 2, 40, 10)) continue;
            this.activeSlider = slider;
            this.updateSlider(slider, mouseX);
            return true;
        }
        return false;
    }

    private boolean paintAt(double mouseX, double mouseY) {
        int pixelX = PlayerHouseSigilScreen.toPixelCoordinate(mouseX, this.previewX, this.previewWidth, this.templateWidth);
        int pixelY = PlayerHouseSigilScreen.toPixelCoordinate(mouseY, this.previewY, this.previewHeight, this.templateHeight);
        if (pixelX < 0 || pixelY < 0) {
            return false;
        }
        int color = this.currentColor();
        switch (this.selectedTool.ordinal()) {
            case 0: {
                this.paintPixel(pixelX, pixelY, color);
                break;
            }
            case 1: {
                this.applyBrush(pixelX, pixelY, color);
                break;
            }
            case 3: {
                this.applyBrush(pixelX, pixelY, 0);
                break;
            }
            case 2: {
                this.floodFill(pixelX, pixelY, color);
            }
        }
        return true;
    }

    private void paintPixel(int pixelX, int pixelY, int color) {
        int index = PlayerSigilTemplate.drawableColorIndex(class_310.method_1551(), pixelX, pixelY);
        if (index < 0 || index >= this.sigilColors.length || this.sigilColors[index] == color) {
            return;
        }
        this.sigilColors[index] = color;
        this.previewDirty = true;
    }

    private void applyBrush(int centerX, int centerY, int color) {
        for (int offsetY = -1; offsetY <= 1; ++offsetY) {
            for (int offsetX = -1; offsetX <= 1; ++offsetX) {
                if (offsetX * offsetX + offsetY * offsetY > 2) continue;
                this.paintPixel(centerX + offsetX, centerY + offsetY, color);
            }
        }
    }

    private void floodFill(int startX, int startY, int replacementColor) {
        int startIndex = PlayerSigilTemplate.drawableColorIndex(class_310.method_1551(), startX, startY);
        if (startIndex < 0 || startIndex >= this.sigilColors.length) {
            return;
        }
        int targetColor = this.sigilColors[startIndex];
        if (targetColor == replacementColor) {
            return;
        }
        boolean[] visited = new boolean[this.sigilColors.length];
        ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
        queue.add(new int[]{startX, startY});
        while (!queue.isEmpty()) {
            int[] point = (int[])queue.removeFirst();
            int pixelX = point[0];
            int pixelY = point[1];
            int colorIndex = PlayerSigilTemplate.drawableColorIndex(class_310.method_1551(), pixelX, pixelY);
            if (colorIndex < 0 || visited[colorIndex] || this.sigilColors[colorIndex] != targetColor) continue;
            visited[colorIndex] = true;
            this.sigilColors[colorIndex] = replacementColor;
            queue.add(new int[]{pixelX + 1, pixelY});
            queue.add(new int[]{pixelX - 1, pixelY});
            queue.add(new int[]{pixelX, pixelY + 1});
            queue.add(new int[]{pixelX, pixelY - 1});
        }
        this.previewDirty = true;
    }

    private void rebuildPreviewTexture() {
        if (this.field_22787 == null) {
            return;
        }
        try {
            if (this.previewTexture != null) {
                this.previewTexture.close();
            }
            this.previewTexture = new class_1043(PlayerSigilTemplate.createImage(this.field_22787, this.sigilColors));
            this.field_22787.method_1531().method_4616(PREVIEW_TEXTURE_ID, (class_1044)this.previewTexture);
            this.previewDirty = false;
        }
        catch (IOException exception) {
            throw new IllegalStateException("Failed to rebuild player house preview", exception);
        }
    }

    private boolean canConfirm() {
        return PlayerHouseNameValidator.isValid(this.houseName);
    }

    private void confirm() {
        ClientPlayNetworking.send((class_8710)new RequestCreatePlayerHousePayload(this.targetEntityId, this.houseName, this.sigilColors, this.completePledgeOnConfirm));
        this.method_25419();
    }

    private void updateSlider(int slider, double mouseX) {
        int barX = this.menuX + 109 + -4;
        float ratio = (float)((mouseX - (double)barX) / 40.0);
        int value = Math.max(0, Math.min(255, Math.round(ratio * 255.0f)));
        switch (slider) {
            case 0: {
                this.red = value;
                break;
            }
            case 1: {
                this.green = value;
                break;
            }
            case 2: {
                this.blue = value;
                break;
            }
            default: {
                return;
            }
        }
    }

    private int currentColor() {
        return 0xFF000000 | this.red << 16 | this.green << 8 | this.blue;
    }

    private int confirmButtonX() {
        return Math.min(this.field_22789 - 64 - 8, this.menuX + 160 + 8);
    }

    private int confirmButtonY() {
        return Math.min(this.field_22790 - 16 - 8, this.menuY + 80 - 16 + 8);
    }

    private String resolveKingdomLabel() {
        class_2960 kingdomId = class_2960.method_12829((String)CharacterSelectionClientState.getSelectedKingdomId());
        CharacterSelectionOption option = kingdomId == null ? null : GotCharacterOptions.getByKingdomId(kingdomId);
        return option == null ? "House" : option.displayName();
    }

    private void loadExistingHouseState() {
        PlayerHouseStatePayload.Entry existingHouse;
        PlayerHouseStatePayload.Entry entry = existingHouse = this.field_22787 != null && this.field_22787.field_1724 != null ? PlayerHouseClientState.getByOwnerUuid(this.field_22787.field_1724.method_5845()) : null;
        if (existingHouse == null) {
            existingHouse = PlayerHouseClientState.getByHouseId(CharacterSelectionClientState.getSelectedOptionId());
        }
        if (existingHouse == null) {
            return;
        }
        this.houseName = PlayerHouseNameValidator.sanitize(existingHouse.displayName());
        int[] existingColors = existingHouse.sigilColors();
        System.arraycopy(existingColors, 0, this.sigilColors, 0, Math.min(existingColors.length, this.sigilColors.length));
        this.previewDirty = true;
    }

    private static int toPixelCoordinate(double mouse, int origin, int drawSize, int textureSize) {
        if (mouse < (double)origin || mouse >= (double)(origin + drawSize)) {
            return -1;
        }
        return Math.min(textureSize - 1, (int)((mouse - (double)origin) * (double)textureSize / (double)drawSize));
    }

    private static boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    @Environment(value=EnvType.CLIENT)
    private static enum Tool {
        PAINT(PAINT_ICON),
        BRUSH(BRUSH_ICON),
        FILL(FILL_ICON),
        ERASER(ERASER_ICON);

        private final class_2960 iconTexture;

        private Tool(class_2960 iconTexture) {
            this.iconTexture = iconTexture;
        }

        private class_2960 iconTexture() {
            return this.iconTexture;
        }
    }
}

