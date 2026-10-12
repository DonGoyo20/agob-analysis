/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 */
package trep.got.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import trep.got.client.gui.character.CharacterMenuRenderState;
import trep.got.client.gui.character.CharacterMenuSection;
import trep.got.client.gui.character.CharacterMenuSectionRegistry;

@Environment(value=EnvType.CLIENT)
public final class CharacterMenuPanelRenderer {
    public static final class_2960 PANEL_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/character_skills.png");
    public static final int PANEL_TEXTURE_SIZE = 256;
    public static final int PANEL_U = 0;
    public static final int PANEL_V = 0;
    public static final int PANEL_WIDTH = 144;
    public static final int PANEL_HEIGHT = 144;
    public static final int PANEL_GAP = 4;
    private static final int CONTENT_X = 8;
    private static final int CONTENT_Y = 8;
    private static final int CONTENT_WIDTH = 128;
    private static final int SECTION_GAP = 0;

    private CharacterMenuPanelRenderer() {
    }

    public static void render(class_332 context, int x, int y, int mouseX, int mouseY) {
        context.method_25291(PANEL_TEXTURE, x, y, 0, 0.0f, 0.0f, 144, 144, 256, 256);
        class_310 client = class_310.method_1551();
        class_327 textRenderer = client.field_1772;
        CharacterMenuRenderState state = CharacterMenuRenderState.create(client);
        int sectionX = x + 8;
        int sectionY = y + 8;
        for (int index = 0; index < CharacterMenuSectionRegistry.getSections().size(); ++index) {
            CharacterMenuSection section = CharacterMenuSectionRegistry.getSections().get(index);
            int sectionHeight = section.getHeight(textRenderer, state, 128);
            section.render(context, textRenderer, state, sectionX, sectionY, 128, mouseX, mouseY);
            sectionY += sectionHeight;
            if (index >= CharacterMenuSectionRegistry.getSections().size() - 1) continue;
            sectionY += 0;
        }
    }
}

