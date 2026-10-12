/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_5250
 */
package trep.got.client.gui.character;

import java.util.ArrayList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_5250;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.gui.CharacterMenuPanelRenderer;
import trep.got.client.gui.character.CharacterMenuRenderState;
import trep.got.client.gui.character.CharacterMenuSection;
import trep.got.client.house.PlayerHouseRenderHelper;
import trep.got.client.util.ClientTextureHelper;
import trep.got.kingdom.ReputationProfile;
import trep.got.kingdom.ReputationTitle;
import trep.got.kingdom.ReputationTitleHelpData;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotReputationTitles;

@Environment(value=EnvType.CLIENT)
public final class KingdomCharacterMenuSection
implements CharacterMenuSection {
    private static final class_2960 REPUTATION_BAR_EMPTY = class_2960.method_60655((String)"agob", (String)"textures/ui/experience/reputation_expbar_empty.png");
    private static final class_2960 REPUTATION_BAR_FILLED = class_2960.method_60655((String)"agob", (String)"textures/ui/experience/reputation_expbar_filled.png");
    private static final int ICON_SIZE = 9;
    private static final int LINE_HEIGHT = 10;
    private static final int REPUTATION_BAR_WIDTH = 102;
    private static final int REPUTATION_BAR_HEIGHT = 5;
    private static final float REPUTATION_BAR_SCALE = 0.7f;
    private static final int REPUTATION_BAR_X = 0;
    private static final int REPUTATION_BAR_Y = 11;
    private static final int INFO_U = 178;
    private static final int INFO_V = 18;
    private static final int INFO_HOVER_V = 34;
    private static final int INFO_DRAW_SIZE = 9;
    private static final int INFO_X = 76;

    @Override
    public int getHeight(class_327 textRenderer, CharacterMenuRenderState state, int width) {
        return 40;
    }

    @Override
    public void render(class_332 context, class_327 textRenderer, CharacterMenuRenderState state, int x, int y, int width, int mouseX, int mouseY) {
        CharacterSelectionOption kingdomOption = KingdomCharacterMenuSection.getSelectedKingdomOption(state);
        class_2960 houseTexture = KingdomCharacterMenuSection.getSelectedHouseTexture(state);
        String houseLabel = KingdomCharacterMenuSection.getSelectedHouseLabel(state);
        int kingdomTextY = y + 1;
        if (kingdomOption == null) {
            context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)"Kingdom: Unchosen"), x, kingdomTextY, 0xFFFFFF);
            KingdomCharacterMenuSection.renderReputation(context, textRenderer, x, y + 20, mouseX, mouseY);
            return;
        }
        int houseTextY = y + 10 + 1;
        KingdomCharacterMenuSection.drawScaledKingdomIcon(context, kingdomOption.texture(), x, y);
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)kingdomOption.displayName()), x + 9 + 4, kingdomTextY, 0xFFFFFF);
        if (houseTexture != null && !houseLabel.isBlank()) {
            KingdomCharacterMenuSection.drawScaledKingdomIcon(context, houseTexture, x, y + 10);
            context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)houseLabel), x + 9 + 4, houseTextY, 0xFFFFFF);
        } else {
            context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)"House: Unchosen"), x, houseTextY, 0xFFFFFF);
        }
        KingdomCharacterMenuSection.renderReputation(context, textRenderer, x, y + 20, mouseX, mouseY);
    }

    private static void drawScaledKingdomIcon(class_332 context, class_2960 texture, int x, int y) {
        ClientTextureHelper.TextureSize size = ClientTextureHelper.resolveTextureSize(class_310.method_1551(), texture);
        float scale = Math.min(9.0f / (float)size.width(), 9.0f / (float)size.height());
        int offsetX = Math.max(0, Math.round((9.0f - (float)size.width() * scale) / 2.0f));
        int offsetY = Math.max(0, Math.round((9.0f - (float)size.height() * scale) / 2.0f));
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)(x + offsetX), (float)(y + offsetY), 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
        context.method_51448().method_22909();
    }

    private static CharacterSelectionOption getSelectedKingdomOption(CharacterMenuRenderState state) {
        if (!state.hasPlayer()) {
            return null;
        }
        String kingdomId = CharacterSelectionClientState.getSelectedKingdomId();
        if (kingdomId.isBlank()) {
            return null;
        }
        class_2960 identifier = class_2960.method_12829((String)kingdomId);
        if (identifier == null) {
            return null;
        }
        return GotCharacterOptions.getByKingdomId(identifier);
    }

    private static class_2960 getSelectedHouseTexture(CharacterMenuRenderState state) {
        if (!state.hasPlayer()) {
            return null;
        }
        String optionId = CharacterSelectionClientState.getSelectedOptionId();
        if (optionId.isBlank()) {
            return null;
        }
        return PlayerHouseRenderHelper.resolveTexture(optionId, CharacterSelectionClientState.getSelectedKingdomId());
    }

    private static String getSelectedHouseLabel(CharacterMenuRenderState state) {
        if (!state.hasPlayer()) {
            return "";
        }
        String optionId = CharacterSelectionClientState.getSelectedOptionId();
        if (optionId.isBlank()) {
            return "";
        }
        return PlayerHouseRenderHelper.resolveDisplayName(optionId, CharacterSelectionClientState.getSelectedKingdomId());
    }

    private static void renderReputation(class_332 context, class_327 textRenderer, int x, int y, int mouseX, int mouseY) {
        ReputationProfile reputation = CharacterSelectionClientState.getReputationProfile();
        ReputationTitle title = reputation.getTitle();
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)title.getDisplayName()), x, y + 1, 0xFFFFFF);
        KingdomCharacterMenuSection.drawReputationBar(context, x + 0, y + 11, reputation.getProgressRatio());
        int infoY = y + 11 - 2;
        int infoX = x + 76;
        boolean hovered = KingdomCharacterMenuSection.isWithin(mouseX, mouseY, infoX, infoY, 9, 9);
        KingdomCharacterMenuSection.drawInfoIcon(context, textRenderer, infoX, infoY, hovered);
        if (hovered) {
            KingdomCharacterMenuSection.drawReputationTooltip(context, textRenderer, title, mouseX, mouseY);
        }
    }

    private static void drawReputationBar(class_332 context, int x, int y, float progress) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(0.7f, 0.7f, 1.0f);
        context.method_25291(REPUTATION_BAR_EMPTY, 0, 0, 0, 0.0f, 0.0f, 102, 5, 102, 5);
        int filledWidth = Math.round(102.0f * progress);
        if (filledWidth > 0) {
            context.method_25291(REPUTATION_BAR_FILLED, 0, 0, 0, 0.0f, 0.0f, filledWidth, 5, 102, 5);
        }
        context.method_51448().method_22909();
    }

    private static void drawInfoIcon(class_332 context, class_327 textRenderer, int x, int y, boolean hovered) {
        context.method_25291(CharacterMenuPanelRenderer.PANEL_TEXTURE, x, y, 0, 178.0f, (float)(hovered ? 34 : 18), 9, 9, 256, 256);
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)"?"), x + 2, y + 1, 0xFFFFFF);
    }

    private static void drawReputationTooltip(class_332 context, class_327 textRenderer, ReputationTitle title, int mouseX, int mouseY) {
        ReputationTitleHelpData helpData = GotReputationTitles.get(title);
        if (helpData == null) {
            return;
        }
        ArrayList<class_5250> lines = new ArrayList<class_5250>();
        for (String line : helpData.helpLines()) {
            lines.add(class_2561.method_43470((String)line));
        }
        if (!lines.isEmpty()) {
            lines.add(class_2561.method_43473());
        }
        context.method_51434(textRenderer, lines, mouseX, mouseY);
    }

    private static boolean isWithin(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}

