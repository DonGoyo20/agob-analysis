/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2561
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 */
package trep.got.client.gui.character;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_327;
import net.minecraft.class_332;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.gui.character.CharacterMenuRenderState;
import trep.got.client.gui.character.CharacterMenuSection;
import trep.got.registry.GotReligionOptions;
import trep.got.religion.ReligionSelectionOption;

@Environment(value=EnvType.CLIENT)
public final class ReligionCharacterMenuSection
implements CharacterMenuSection {
    private static final int ICON_SIZE = 9;
    private static final int LINE_HEIGHT = 10;
    private static final int SOURCE_TEXTURE_SIZE = 48;

    @Override
    public int getHeight(class_327 textRenderer, CharacterMenuRenderState state, int width) {
        return 10;
    }

    @Override
    public void render(class_332 context, class_327 textRenderer, CharacterMenuRenderState state, int x, int y, int width, int mouseX, int mouseY) {
        ReligionSelectionOption option = ReligionCharacterMenuSection.getSelectedReligion(state);
        int textY = y + 1;
        if (option == null) {
            context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)"Religion: Unchosen"), x, textY, 0xFFFFFF);
            return;
        }
        ReligionCharacterMenuSection.drawScaledReligionIcon(context, option, x, y);
        context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)option.displayName()), x + 9 + 4, textY, 0xFFFFFF);
    }

    private static void drawScaledReligionIcon(class_332 context, ReligionSelectionOption option, int x, int y) {
        float scale = 0.1875f;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(option.texture(), 0, 0, 0, 0.0f, 0.0f, 48, 48, 48, 48);
        context.method_51448().method_22909();
    }

    private static ReligionSelectionOption getSelectedReligion(CharacterMenuRenderState state) {
        if (!state.hasPlayer()) {
            return null;
        }
        String religionId = CharacterSelectionClientState.getSelectedReligionId();
        if (religionId.isBlank()) {
            return null;
        }
        return GotReligionOptions.get(religionId);
    }
}

