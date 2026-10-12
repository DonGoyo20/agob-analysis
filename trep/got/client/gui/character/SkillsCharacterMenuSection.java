/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2561
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_5250
 */
package trep.got.client.gui.character;

import java.util.ArrayList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_5250;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.gui.CharacterMenuPanelRenderer;
import trep.got.client.gui.character.CharacterMenuRenderState;
import trep.got.client.gui.character.CharacterMenuSection;
import trep.got.registry.GotSkillAttributes;
import trep.got.skill.SkillAttributeHelpData;
import trep.got.skill.SkillAttributeType;

@Environment(value=EnvType.CLIENT)
public final class SkillsCharacterMenuSection
implements CharacterMenuSection {
    private static final int ICON_SOURCE_SIZE = 16;
    private static final int ICON_SIZE = 9;
    private static final int ICON_TEXT_GAP = 4;
    private static final int LINE_HEIGHT = 10;
    private static final int VALUE_COLUMN_OFFSET = 80;
    private static final int INFO_U = 178;
    private static final int INFO_V = 18;
    private static final int INFO_HOVER_V = 34;
    private static final int INFO_TEXTURE_SIZE = 12;
    private static final int INFO_DRAW_SIZE = 9;
    private static final int INFO_X = 120;

    @Override
    public int getHeight(class_327 textRenderer, CharacterMenuRenderState state, int width) {
        return SkillAttributeType.ALL.size() * 10;
    }

    @Override
    public void render(class_332 context, class_327 textRenderer, CharacterMenuRenderState state, int x, int y, int width, int mouseX, int mouseY) {
        for (int index = 0; index < SkillAttributeType.ALL.size(); ++index) {
            SkillAttributeType type = SkillAttributeType.ALL.get(index);
            int iconY = y + index * 10;
            int lineY = iconY + 1;
            SkillsCharacterMenuSection.drawScaledSkillIcon(context, type, x, iconY);
            context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)(type.getDisplayName() + ":")), x + 9 + 4, lineY, 0xFFFFFF);
            int points = CharacterSelectionClientState.getEffectiveSkillPoints(type);
            int clothingBonus = CharacterSelectionClientState.getShirtSkillBonus(type) + CharacterSelectionClientState.getPantsSkillBonus(type);
            String valueText = points + "/" + CharacterSelectionClientState.getSkillMaxPoints(type);
            context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)valueText), x + 80, lineY, clothingBonus > 0 ? 0xB8FFB8 : 0xFFFFFF);
            int infoX = x + 120;
            boolean hovered = SkillsCharacterMenuSection.isWithin(mouseX, mouseY, infoX, iconY, 9, 9);
            SkillsCharacterMenuSection.drawInfoIcon(context, textRenderer, infoX, iconY, hovered);
            if (!hovered) continue;
            SkillsCharacterMenuSection.drawHelpTooltip(context, textRenderer, type, mouseX, mouseY);
        }
    }

    private static void drawScaledSkillIcon(class_332 context, SkillAttributeType type, int x, int y) {
        float scale = 0.5625f;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(type.getIconTexture(), 0, 0, 0, 0.0f, 0.0f, 16, 16, 16, 16);
        context.method_51448().method_22909();
    }

    private static void drawInfoIcon(class_332 context, class_327 textRenderer, int x, int y, boolean hovered) {
        context.method_25291(CharacterMenuPanelRenderer.PANEL_TEXTURE, x, y, 0, 178.0f, (float)(hovered ? 34 : 18), 9, 9, 256, 256);
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)"?"), x + 2, y + 1, 0xFFFFFF);
    }

    private static void drawHelpTooltip(class_332 context, class_327 textRenderer, SkillAttributeType type, int mouseX, int mouseY) {
        SkillAttributeHelpData helpData = GotSkillAttributes.get(type);
        if (helpData == null) {
            return;
        }
        ArrayList<class_5250> lines = new ArrayList<class_5250>();
        for (String line : helpData.helpLines()) {
            lines.add(class_2561.method_43470((String)line));
        }
        int shirtBonus = CharacterSelectionClientState.getShirtSkillBonus(type);
        int pantsBonus = CharacterSelectionClientState.getPantsSkillBonus(type);
        if (shirtBonus > 0 || pantsBonus > 0) {
            lines.add(class_2561.method_43470((String)""));
            lines.add(class_2561.method_43470((String)("Base: " + CharacterSelectionClientState.getSkillPoints(type))));
            if (shirtBonus > 0) {
                lines.add(class_2561.method_43470((String)("Shirt: +" + shirtBonus)));
            }
            if (pantsBonus > 0) {
                lines.add(class_2561.method_43470((String)("Pants: +" + pantsBonus)));
            }
        }
        context.method_51434(textRenderer, lines, mouseX, mouseY);
    }

    private static boolean isWithin(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}

