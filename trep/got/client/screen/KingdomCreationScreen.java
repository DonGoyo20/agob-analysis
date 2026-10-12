/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_1309
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_490
 *  net.minecraft.class_5250
 *  net.minecraft.class_5348
 *  net.minecraft.class_742
 *  net.minecraft.class_746
 *  net.minecraft.class_8710
 */
package trep.got.client.screen;

import java.util.List;
import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_1309;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_490;
import net.minecraft.class_5250;
import net.minecraft.class_5348;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_8710;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.house.PlayerHouseClientState;
import trep.got.client.house.PlayerHouseRenderHelper;
import trep.got.client.house.PlayerSigilTextureManager;
import trep.got.client.util.ClientTextureHelper;
import trep.got.network.OpenCastleScreenPayload;
import trep.got.network.PlayerHouseStatePayload;
import trep.got.network.RequestCreateKingdomPayload;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotKingdoms;

@Environment(value=EnvType.CLIENT)
public final class KingdomCreationScreen
extends class_437 {
    private static final class_2960 BACKGROUND = class_2960.method_60655((String)"agob", (String)"textures/ui/house_screen.png");
    private static final class_2960 KINGDOM_SLOT = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_slot.png");
    private static final class_2960 KINGDOM_SLOT_ACTIVE = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_slot_active.png");
    private static final class_2960 KINGDOM_TOGGLE = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_toggle.png");
    private static final class_2960 KINGDOM_TOGGLE_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_toggle_hover.png");
    private static final class_2960 DIALOGUE_OPTION_WIDE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/dialogue_option_wide.png");
    private static final int UI_WIDTH = 176;
    private static final int UI_HEIGHT = 144;
    private static final int BUTTON_WIDTH = 96;
    private static final int BUTTON_HEIGHT = 16;
    private static final int TOGGLE_WIDTH = 64;
    private static final int TOGGLE_HEIGHT = 32;
    private static final int SLOT_SIZE = 20;
    private static final int SLOT_GAP = 4;
    private static final int GRID_COLUMNS = 4;
    private static final int SIGIL_SIZE = 42;
    private static final int TEXT_COLOR = -1450302;
    private static final int HOVER_TEXT_COLOR = -994470;
    private final OpenCastleScreenPayload castlePayload;
    private final class_437 parent;
    private Step step = Step.NAME;
    private RegionTab tab = RegionTab.WESTEROS;
    private String kingdomName = "";
    private int cursor;
    private boolean inputFocused = true;
    private class_2960 selectedCultureId;

    public KingdomCreationScreen(OpenCastleScreenPayload castlePayload, class_437 parent) {
        super((class_2561)class_2561.method_43470((String)"Create Kingdom"));
        this.castlePayload = castlePayload;
        this.parent = parent;
        this.selectedCultureId = this.defaultCulture();
    }

    public KingdomCreationScreen(class_437 parent) {
        this(null, parent);
    }

    public static void open(OpenCastleScreenPayload castlePayload, class_437 parent) {
        class_310.method_1551().method_1507((class_437)new KingdomCreationScreen(castlePayload, parent));
    }

    public static void openStandalone(class_437 parent) {
        class_310.method_1551().method_1507((class_437)new KingdomCreationScreen(parent));
    }

    public boolean method_25421() {
        return false;
    }

    protected void method_57734(float delta) {
    }

    public void method_25419() {
        if (this.field_22787 != null) {
            this.field_22787.method_1507(this.parent);
        }
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        super.method_25420(context, mouseX, mouseY, delta);
        int uiX = this.uiX();
        int uiY = this.uiY();
        context.method_25291(BACKGROUND, uiX, uiY, 0, 0.0f, 0.0f, 176, 144, 176, 144);
        this.drawBannerColumn(context, mouseX, mouseY, uiX, uiY);
        switch (this.step.ordinal()) {
            case 0: {
                this.drawNameStep(context, mouseX, mouseY, uiX, uiY);
                break;
            }
            case 1: {
                this.drawCultureStep(context, mouseX, mouseY, uiX, uiY);
                break;
            }
            case 2: {
                this.drawOverviewStep(context, mouseX, mouseY, uiX, uiY);
            }
        }
        this.drawActionButton(context, mouseX, mouseY);
        super.method_25394(context, mouseX, mouseY, delta);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return super.method_25402(mouseX, mouseY, button);
        }
        if (this.step == Step.NAME && KingdomCreationScreen.isWithin(mouseX, mouseY, this.inputX(), this.inputY(), this.inputWidth(), 16)) {
            this.inputFocused = true;
            this.cursor = this.cursorForMouse(mouseX);
            return true;
        }
        if (this.step == Step.CULTURE) {
            int uiX = this.uiX();
            int uiY = this.uiY();
            if (KingdomCreationScreen.isWithin(mouseX, mouseY, KingdomCreationScreen.toggleX(uiX), KingdomCreationScreen.toggleY(uiY), 64, 32)) {
                this.tab = this.tab.next();
                this.selectedCultureId = this.defaultCulture();
                return true;
            }
            class_2960 clickedCulture = this.cultureAt(mouseX, mouseY, uiX, uiY);
            if (clickedCulture != null) {
                this.selectedCultureId = clickedCulture;
                return true;
            }
        }
        if (KingdomCreationScreen.isWithin(mouseX, mouseY, this.actionButtonX(), this.actionButtonY(), 96, 16)) {
            if (this.step == Step.NAME && !this.normalizedKingdomName().isBlank()) {
                this.step = Step.CULTURE;
                this.inputFocused = false;
                return true;
            }
            if (this.step == Step.CULTURE && this.selectedCultureId != null) {
                this.step = Step.OVERVIEW;
                return true;
            }
            if (this.step == Step.OVERVIEW && this.selectedCultureId != null && !this.normalizedKingdomName().isBlank()) {
                String castleId = this.castlePayload == null ? "" : this.castlePayload.castleId();
                ClientPlayNetworking.send((class_8710)new RequestCreateKingdomPayload(castleId, this.normalizedKingdomName(), this.selectedCultureId.toString()));
                this.method_25419();
                return true;
            }
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25400(char chr, int modifiers) {
        if (this.step != Step.NAME || !this.inputFocused) {
            return super.method_25400(chr, modifiers);
        }
        if ((Character.isLetterOrDigit(chr) || chr == ' ') && this.kingdomName.length() < 24) {
            this.kingdomName = this.kingdomName.substring(0, this.cursor) + chr + this.kingdomName.substring(this.cursor);
            ++this.cursor;
            return true;
        }
        return true;
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.method_25419();
            return true;
        }
        if (this.step != Step.NAME || !this.inputFocused) {
            return super.method_25404(keyCode, scanCode, modifiers);
        }
        if (keyCode == 259 && this.cursor > 0) {
            this.kingdomName = this.kingdomName.substring(0, this.cursor - 1) + this.kingdomName.substring(this.cursor);
            --this.cursor;
            return true;
        }
        if (keyCode == 261 && this.cursor < this.kingdomName.length()) {
            this.kingdomName = this.kingdomName.substring(0, this.cursor) + this.kingdomName.substring(this.cursor + 1);
            return true;
        }
        if (keyCode == 263) {
            this.cursor = Math.max(0, this.cursor - 1);
            return true;
        }
        if (keyCode == 262) {
            this.cursor = Math.min(this.kingdomName.length(), this.cursor + 1);
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    private void drawBannerColumn(class_332 context, int mouseX, int mouseY, int uiX, int uiY) {
        class_746 class_7462;
        int sigilX = uiX + 14;
        int sigilY = uiY + 40;
        class_2960 texture = this.currentBannerTexture();
        if (texture != null) {
            KingdomCreationScreen.drawFittedTexture(context, texture, sigilX, sigilY, 42, 42);
        }
        if (this.step == Step.OVERVIEW && this.field_22787 != null && (class_7462 = this.field_22787.field_1724) instanceof class_742) {
            class_746 player = class_7462;
            class_490.method_2486((class_332)context, (int)(sigilX + 2), (int)(sigilY - 8), (int)(sigilX + 42 - 2), (int)(sigilY + 42 + 8), (int)28, (float)0.0f, (float)mouseX, (float)mouseY, (class_1309)player);
        }
        this.drawScaledCenteredText(context, (class_2561)class_2561.method_43470((String)(this.field_22787 == null || this.field_22787.field_1724 == null ? "Banner" : this.field_22787.field_1724.method_5477().getString())), sigilX - 4, sigilY + 42 + 5, 50, 0.7f);
    }

    private void drawNameStep(class_332 context, int mouseX, int mouseY, int uiX, int uiY) {
        int x = this.inputX();
        int y = this.inputY();
        context.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)"Kingdom Name"), x, y - 14, -1450302);
        context.method_25294(x, y, x + this.inputWidth(), y + 16, 0x66000000);
        context.method_49601(x, y, this.inputWidth(), 16, this.inputFocused ? -1450302 : -9478072);
        context.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)this.kingdomName), x + 4, y + 4, -1);
        if (this.inputFocused && System.currentTimeMillis() / 500L % 2L == 0L) {
            int cursorX = x + 4 + this.field_22793.method_1727(this.kingdomName.substring(0, this.cursor));
            context.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)"|"), cursorX, y + 4, -1);
        }
    }

    private void drawCultureStep(class_332 context, int mouseX, int mouseY, int uiX, int uiY) {
        int toggleY;
        int toggleX = KingdomCreationScreen.toggleX(uiX);
        boolean hovered = KingdomCreationScreen.isWithin(mouseX, mouseY, toggleX, toggleY = KingdomCreationScreen.toggleY(uiY), 64, 32);
        context.method_25290(hovered ? KINGDOM_TOGGLE_HOVER : KINGDOM_TOGGLE, toggleX, toggleY, 0.0f, 0.0f, 64, 32, 64, 32);
        class_5250 class_52502 = class_2561.method_43470((String)this.tab.label());
        Objects.requireNonNull(this.field_22793);
        context.method_27534(this.field_22793, (class_2561)class_52502, toggleX + 32, toggleY + (32 - 9) / 2, hovered ? -994470 : -1450302);
        for (class_2960 kingdomId : this.cultureOptions()) {
            int index = this.cultureOptions().indexOf(kingdomId);
            int x = KingdomCreationScreen.gridX(uiX) + index % 4 * 24;
            int y = KingdomCreationScreen.gridY(uiY) + index / 4 * 24;
            boolean selected = kingdomId.equals((Object)this.selectedCultureId);
            boolean slotHovered = KingdomCreationScreen.isWithin(mouseX, mouseY, x, y, 20, 20);
            context.method_25290(selected || slotHovered ? KINGDOM_SLOT_ACTIVE : KINGDOM_SLOT, x, y, 0.0f, 0.0f, 20, 20, 20, 20);
            CharacterSelectionOption option = GotCharacterOptions.getByKingdomId(kingdomId);
            if (option != null) {
                KingdomCreationScreen.drawFittedTexture(context, option.texture(), x + 4, y + 4, 12, 12);
            }
            if (!slotHovered) continue;
            context.method_51438(this.field_22793, (class_2561)class_2561.method_43470((String)this.displayName(kingdomId)), mouseX, mouseY);
        }
    }

    private void drawOverviewStep(class_332 context, int mouseX, int mouseY, int uiX, int uiY) {
        int x = uiX + 68;
        int y = uiY + 48;
        context.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)this.normalizedKingdomName()), x, y, -1);
        context.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)("Culture: " + this.displayName(this.selectedCultureId))), x, y + 16, -1450302);
    }

    private void drawActionButton(class_332 context, int mouseX, int mouseY) {
        int x = this.actionButtonX();
        int y = this.actionButtonY();
        boolean hovered = KingdomCreationScreen.isWithin(mouseX, mouseY, x, y, 96, 16);
        context.method_25290(DIALOGUE_OPTION_WIDE_TEXTURE, x, y, 0.0f, 0.0f, 96, 16, 96, 16);
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)(this.step == Step.OVERVIEW ? "Create" : "Next")), x + 48, y + 4, hovered ? -994470 : -1);
    }

    private class_2960 currentBannerTexture() {
        PlayerHouseStatePayload.Entry customHouse;
        class_310 client = class_310.method_1551();
        if (client != null && client.field_1724 != null && (customHouse = PlayerHouseClientState.getByOwnerUuid(client.field_1724.method_5845())) != null) {
            return PlayerSigilTextureManager.getTextureId(customHouse);
        }
        return PlayerHouseRenderHelper.resolveTexture(CharacterSelectionClientState.getSelectedOptionId(), CharacterSelectionClientState.getSelectedKingdomId());
    }

    private class_2960 cultureAt(double mouseX, double mouseY, int uiX, int uiY) {
        List<class_2960> options = this.cultureOptions();
        for (int index = 0; index < options.size(); ++index) {
            int y;
            int x = KingdomCreationScreen.gridX(uiX) + index % 4 * 24;
            if (!KingdomCreationScreen.isWithin(mouseX, mouseY, x, y = KingdomCreationScreen.gridY(uiY) + index / 4 * 24, 20, 20)) continue;
            return options.get(index);
        }
        return null;
    }

    private List<class_2960> cultureOptions() {
        return GotCharacterOptions.getPrimaryOptions().stream().map(CharacterSelectionOption::kingdomId).filter(kingdomId -> this.tab == RegionTab.WESTEROS ? GotKingdoms.isWesterosiKingdom(kingdomId.toString()) : GotKingdoms.isEssosKingdom(kingdomId.toString())).toList();
    }

    private class_2960 defaultCulture() {
        List<class_2960> options = this.cultureOptions();
        return options.isEmpty() ? null : options.get(0);
    }

    private String displayName(class_2960 kingdomId) {
        if (kingdomId == null || GotKingdoms.get(kingdomId) == null) {
            return "Unknown";
        }
        return GotKingdoms.get(kingdomId).getName();
    }

    private String normalizedKingdomName() {
        return this.kingdomName.trim().replaceAll(" +", " ");
    }

    private int cursorForMouse(double mouseX) {
        int localX = Math.max(0, (int)mouseX - this.inputX() - 4);
        for (int index = 0; index <= this.kingdomName.length(); ++index) {
            if (this.field_22793.method_1727(this.kingdomName.substring(0, index)) < localX) continue;
            return index;
        }
        return this.kingdomName.length();
    }

    private int uiX() {
        return (this.field_22789 - 176) / 2;
    }

    private int uiY() {
        return (this.field_22790 - 144) / 2;
    }

    private int actionButtonX() {
        return (this.field_22789 - 96) / 2;
    }

    private int actionButtonY() {
        return this.uiY() + 144 + 8;
    }

    private int inputX() {
        return this.uiX() + 68;
    }

    private int inputY() {
        return this.uiY() + 58;
    }

    private int inputWidth() {
        return 92;
    }

    private static int toggleX(int uiX) {
        return uiX + 84;
    }

    private static int toggleY(int uiY) {
        return uiY + 8;
    }

    private static int gridX(int uiX) {
        return uiX + 68;
    }

    private static int gridY(int uiY) {
        return uiY + 42;
    }

    private void drawScaledCenteredText(class_332 context, class_2561 text, int x, int y, int width, float scale) {
        int textWidth = this.field_22793.method_27525((class_5348)text);
        float effectiveScale = Math.min(scale, (float)width / (float)Math.max(1, textWidth));
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x + (float)width / 2.0f, (float)y, 0.0f);
        context.method_51448().method_22905(effectiveScale, effectiveScale, 1.0f);
        context.method_27534(this.field_22793, text, 0, 0, -1450302);
        context.method_51448().method_22909();
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

    private static boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    @Environment(value=EnvType.CLIENT)
    private static enum Step {
        NAME,
        CULTURE,
        OVERVIEW;

    }

    @Environment(value=EnvType.CLIENT)
    private static enum RegionTab {
        WESTEROS("Westeros"),
        ESSOS("Essos");

        private final String label;

        private RegionTab(String label) {
            this.label = label;
        }

        private String label() {
            return this.label;
        }

        private RegionTab next() {
            return this == WESTEROS ? ESSOS : WESTEROS;
        }
    }
}

