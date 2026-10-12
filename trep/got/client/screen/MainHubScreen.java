/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_5250
 *  net.minecraft.class_5348
 *  net.minecraft.class_8710
 */
package trep.got.client.screen;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_5250;
import net.minecraft.class_5348;
import net.minecraft.class_8710;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.house.PlayerHouseClientState;
import trep.got.client.house.PlayerHouseRenderHelper;
import trep.got.client.screen.HouseScreen;
import trep.got.client.screen.JournalScreen;
import trep.got.client.screen.KingdomScreen;
import trep.got.client.screen.WorldMapScreen;
import trep.got.client.util.ClientTextureHelper;
import trep.got.network.PlayerHouseStatePayload;
import trep.got.network.RequestTailorScreenPayload;

@Environment(value=EnvType.CLIENT)
public final class MainHubScreen
extends class_437 {
    private static final int ICON_SIZE = 32;
    private static final int ICON_GAP = 54;
    private static final int TITLE_GAP = 14;
    private static final int HOVER_TEXT_COLOR = -858456;
    private static final int TEXT_COLOR = -1;
    private static final class_2960 BLANK_SIGIL = class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_blank.png");
    private static final Option TAILOR_OPTION = new Option("screen.agob.main_hub.tailor", () -> class_2960.method_60655((String)"agob", (String)"textures/ui/main/main_tailor.png"), () -> ClientPlayNetworking.send((class_8710)new RequestTailorScreenPayload()));
    private static final List<Option> OPTIONS = List.of(new Option("screen.agob.main_hub.map", () -> class_2960.method_60655((String)"agob", (String)"textures/ui/main/main_map.png"), WorldMapScreen::open), new Option("screen.agob.main_hub.kingdom", () -> class_2960.method_60655((String)"agob", (String)"textures/ui/main/main_kingdom.png"), KingdomScreen::open), new Option("screen.agob.main_hub.journal", () -> class_2960.method_60655((String)"agob", (String)"textures/ui/main/main_journal.png"), JournalScreen::open), new Option("screen.agob.main_hub.house", MainHubScreen::playerHouseIcon, HouseScreen::open));

    public MainHubScreen() {
        super((class_2561)class_2561.method_43471((String)"screen.agob.main_hub"));
    }

    public static void open() {
        class_310.method_1551().method_1507((class_437)new MainHubScreen());
    }

    public boolean method_25421() {
        return false;
    }

    protected void method_57734(float delta) {
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        super.method_25420(context, mouseX, mouseY, delta);
        int totalWidth = OPTIONS.size() * 32 + (OPTIONS.size() - 1) * 54;
        int startX = (this.field_22789 - totalWidth) / 2;
        int iconY = this.field_22790 / 2 - 16;
        for (int index = 0; index < OPTIONS.size(); ++index) {
            Option option = OPTIONS.get(index);
            int iconX = startX + index * 86;
            this.renderOption(context, option, iconX, iconY, mouseX, mouseY);
        }
        this.renderOption(context, TAILOR_OPTION, (this.field_22789 - 32) / 2, iconY + 32 + 16, mouseX, mouseY);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return super.method_25402(mouseX, mouseY, button);
        }
        int totalWidth = OPTIONS.size() * 32 + (OPTIONS.size() - 1) * 54;
        int startX = (this.field_22789 - totalWidth) / 2;
        int iconY = this.field_22790 / 2 - 16;
        for (int index = 0; index < OPTIONS.size(); ++index) {
            Option option = OPTIONS.get(index);
            int iconX = startX + index * 86;
            if (!this.isPointWithinIcon(iconX, iconY, mouseX, mouseY)) continue;
            option.action().run();
            return true;
        }
        int tailorX = (this.field_22789 - 32) / 2;
        int tailorY = iconY + 32 + 16;
        if (this.isPointWithinIcon(tailorX, tailorY, mouseX, mouseY)) {
            TAILOR_OPTION.action().run();
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    private void renderOption(class_332 context, Option option, int iconX, int iconY, int mouseX, int mouseY) {
        boolean hovered = this.isPointWithinIcon(iconX, iconY, mouseX, mouseY);
        class_5250 title = class_2561.method_43471((String)option.translationKey());
        int titleWidth = this.field_22793.method_27525((class_5348)title);
        int titleX = iconX + 16 - titleWidth / 2;
        Objects.requireNonNull(this.field_22793);
        int titleY = iconY - 9 - 14;
        context.method_27535(this.field_22793, (class_2561)title, titleX, titleY, hovered ? -858456 : -1);
        MainHubScreen.drawFittedTexture(context, option.icon().get(), iconX, iconY, 32, 32);
    }

    private boolean isPointWithinIcon(int iconX, int iconY, double mouseX, double mouseY) {
        return mouseX >= (double)iconX && mouseX < (double)(iconX + 32) && mouseY >= (double)iconY && mouseY < (double)(iconY + 32);
    }

    private static class_2960 playerHouseIcon() {
        class_2960 customTexture;
        PlayerHouseStatePayload.Entry customHouse;
        class_310 client = class_310.method_1551();
        if (client.field_1724 != null && (customHouse = PlayerHouseClientState.getByOwnerUuid(client.field_1724.method_5845())) != null && (customTexture = PlayerHouseRenderHelper.resolveTexture(customHouse.houseId(), customHouse.kingdomId())) != null) {
            return customTexture;
        }
        class_2960 texture = PlayerHouseRenderHelper.resolveTexture(CharacterSelectionClientState.getSelectedOptionId(), CharacterSelectionClientState.getSelectedKingdomId());
        return texture == null ? BLANK_SIGIL : texture;
    }

    private static void drawFittedTexture(class_332 context, class_2960 texture, int x, int y, int boxWidth, int boxHeight) {
        ClientTextureHelper.TextureSize size = ClientTextureHelper.resolveTextureSize(class_310.method_1551(), texture);
        float scale = Math.min((float)boxWidth / (float)size.width(), (float)boxHeight / (float)size.height());
        int drawWidth = Math.max(1, Math.round((float)size.width() * scale));
        int drawHeight = Math.max(1, Math.round((float)size.height() * scale));
        int drawX = x + (boxWidth - drawWidth) / 2;
        int drawY = y + (boxHeight - drawHeight) / 2;
        context.method_25293(texture, drawX, drawY, drawWidth, drawHeight, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
    }

    @Environment(value=EnvType.CLIENT)
    private record Option(String translationKey, Supplier<class_2960> icon, Runnable action) {
    }
}

