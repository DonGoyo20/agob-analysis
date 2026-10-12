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
 *  net.minecraft.class_437
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_437;
import org.jetbrains.annotations.Nullable;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.house.PlayerHouseRenderHelper;
import trep.got.client.util.ClientTextureHelper;
import trep.got.network.OpenVillageScreenPayload;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotReligionOptions;
import trep.got.registry.GotVillageResources;
import trep.got.religion.ReligionSelectionOption;
import trep.got.village.resource.VillageResource;

@Environment(value=EnvType.CLIENT)
public final class VillageScreen
extends class_437 {
    private static final class_2960 UI_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/castle_ui.png");
    private static final class_2960 HORSE_ICON_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/attributes_riding.png");
    private static final int TEXTURE_SIZE = 256;
    private static final int UI_SOURCE_WIDTH = 160;
    private static final int UI_SOURCE_HEIGHT = 192;
    private static final int TITLE_LEFT = 5;
    private static final int TITLE_RIGHT = 154;
    private static final int TITLE_Y = 10;
    private static final int CONTENT_LEFT = 5;
    private static final int CONTENT_RIGHT = 154;
    private static final int CONTENT_Y = 24;
    private static final int LINE_HEIGHT = 14;
    private static final int ICON_SIZE = 9;
    private static final int LINE_ICON_TEXT_GAP = 4;
    private static final int PRODUCT_LINE_GAP = 5;
    private final OpenVillageScreenPayload payload;
    @Nullable
    private final class_437 parent;

    public VillageScreen(OpenVillageScreenPayload payload, @Nullable class_437 parent) {
        super((class_2561)class_2561.method_43470((String)payload.villageName()));
        this.payload = payload;
        this.parent = parent;
    }

    public static void open(OpenVillageScreenPayload payload, @Nullable class_437 parent) {
        class_310.method_1551().method_1507((class_437)new VillageScreen(payload, parent));
    }

    public void method_25419() {
        if (this.field_22787 != null) {
            this.field_22787.method_1507(this.parent);
        }
    }

    protected void method_57734(float delta) {
    }

    public boolean method_25421() {
        return false;
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        super.method_25420(context, mouseX, mouseY, delta);
        float scale = this.resolveScale();
        int panelX = (this.field_22789 - Math.round(160.0f * scale)) / 2;
        int panelY = (this.field_22790 - Math.round(192.0f * scale)) / 2;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)panelX, (float)panelY, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(UI_TEXTURE, 0, 0, 0, 0.0f, 0.0f, 160, 192, 256, 256);
        context.method_51448().method_22909();
        int titleLeft = panelX + VillageScreen.scaled(5, scale);
        int titleRight = panelX + VillageScreen.scaled(154, scale);
        int titleWidth = titleRight - titleLeft;
        int titleY = panelY + VillageScreen.scaled(10, scale);
        context.method_27534(this.field_22793, this.fitText(this.field_22793, this.payload.villageName(), titleWidth), titleLeft + titleWidth / 2, titleY, -727358);
        int contentX = panelX + VillageScreen.scaled(5, scale);
        int contentY = panelY + VillageScreen.scaled(24, scale);
        int contentWidth = VillageScreen.scaled(149, scale);
        CharacterSelectionOption kingdomOption = this.resolveKingdomOption(this.payload.kingdomId());
        class_2960 houseTexture = PlayerHouseRenderHelper.resolveTexture(this.payload.houseId(), this.payload.kingdomId());
        ReligionSelectionOption religionOption = this.resolveReligionOption(this.payload.religionId());
        this.drawTextLine(context, (class_2561)class_2561.method_43471((String)"tooltip.agob.village"), contentX, contentY, contentWidth);
        this.drawIconLine(context, kingdomOption != null ? kingdomOption.texture() : null, VillageScreen.fallback(this.payload.kingdomName(), "Unknown Kingdom"), contentX, contentY += VillageScreen.scaled(14, scale), contentWidth, scale);
        this.drawIconLine(context, houseTexture, VillageScreen.fallback(PlayerHouseRenderHelper.resolveDisplayName(this.payload.houseId(), this.payload.kingdomId()), "Unknown House"), contentX, contentY += VillageScreen.scaled(14, scale), contentWidth, scale);
        this.drawIconLine(context, religionOption != null ? religionOption.texture() : null, VillageScreen.fallback(this.payload.religionName(), "Unknown Religion"), contentX, contentY += VillageScreen.scaled(14, scale), contentWidth, scale);
        this.drawProductLine(context, contentX, contentY += VillageScreen.scaled(19, scale), contentWidth, scale);
        super.method_25394(context, mouseX, mouseY, delta);
    }

    private void drawProductLine(class_332 context, int x, int y, int width, float scale) {
        VillageResource resource = GotVillageResources.get(class_2960.method_12829((String)this.payload.resourceId()));
        String label = this.payload.currentStock() + " / " + this.payload.maxStock();
        int iconSize = Math.max(10, Math.round(9.0f * scale));
        int textX = x;
        if (resource == null) {
            context.method_25303(this.field_22793, label, x, y, -724761);
            return;
        }
        if (resource == GotVillageResources.HORSES) {
            this.drawScaledFullTexture(context, HORSE_ICON_TEXTURE, x, y, iconSize, iconSize);
            textX += Math.max(12, Math.round(13.0f * scale));
        } else {
            class_1799 stack = resource.getIconStack().method_7972();
            if (!stack.method_7960()) {
                context.method_51448().method_22903();
                context.method_51448().method_46416((float)x, (float)y, 0.0f);
                context.method_51448().method_22905((float)iconSize / 16.0f, (float)iconSize / 16.0f, 1.0f);
                context.method_51427(stack, 0, 0);
                context.method_51448().method_22909();
                textX += Math.max(12, Math.round(13.0f * scale));
            }
        }
        context.method_27535(this.field_22793, this.fitText(this.field_22793, label, width - (textX - x)), textX, y + 1, -724761);
    }

    private void drawTextLine(class_332 context, class_2561 text, int x, int y, int width) {
        context.method_27535(this.field_22793, this.fitText(this.field_22793, text.getString(), width), x, y, -724761);
    }

    private void drawIconLine(class_332 context, @Nullable class_2960 iconTexture, String label, int x, int y, int width, float scale) {
        int textX = x;
        if (iconTexture != null) {
            int iconSize = Math.max(9, Math.round(9.0f * scale));
            this.drawScaledFullTexture(context, iconTexture, x, y, iconSize, iconSize);
            textX += Math.max(12, Math.round(13.0f * scale));
        }
        context.method_27535(this.field_22793, this.fitText(this.field_22793, label, width - (textX - x)), textX, y + 1, -724761);
    }

    private void drawScaledFullTexture(class_332 context, class_2960 texture, int x, int y, int drawWidth, int drawHeight) {
        ClientTextureHelper.TextureSize size = ClientTextureHelper.resolveTextureSize(this.field_22787, texture);
        float scale = Math.min((float)drawWidth / (float)size.width(), (float)drawHeight / (float)size.height());
        int offsetX = Math.max(0, Math.round(((float)drawWidth - (float)size.width() * scale) / 2.0f));
        int offsetY = Math.max(0, Math.round(((float)drawHeight - (float)size.height() * scale) / 2.0f));
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)(x + offsetX), (float)(y + offsetY), 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
        context.method_51448().method_22909();
    }

    private CharacterSelectionOption resolveKingdomOption(String kingdomId) {
        class_2960 id = class_2960.method_12829((String)kingdomId);
        return id == null ? null : GotCharacterOptions.getByKingdomId(id);
    }

    private ReligionSelectionOption resolveReligionOption(String religionId) {
        return religionId == null || religionId.isBlank() ? null : GotReligionOptions.get(religionId);
    }

    private class_2561 fitText(class_327 textRenderer, String value, int maxWidth) {
        String trimmed = textRenderer.method_27523(value, Math.max(0, maxWidth));
        if (trimmed.length() == value.length()) {
            return class_2561.method_43470((String)trimmed);
        }
        String ellipsis = "...";
        String clipped = textRenderer.method_27523(value, Math.max(0, maxWidth - textRenderer.method_1727(ellipsis)));
        return class_2561.method_43470((String)(clipped + ellipsis));
    }

    private static String fallback(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }

    private float resolveScale() {
        float availableWidth = Math.max(160.0f, (float)this.field_22789 - 48.0f);
        float availableHeight = Math.max(192.0f, (float)this.field_22790 - 48.0f);
        return Math.min(2.0f, Math.min(availableWidth / 160.0f, availableHeight / 192.0f));
    }

    private static int scaled(int sourcePixels, float scale) {
        return Math.round((float)sourcePixels * scale);
    }
}

