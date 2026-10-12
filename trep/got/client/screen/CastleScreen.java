/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_5250
 *  net.minecraft.class_8710
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.screen;

import java.util.ArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_5250;
import net.minecraft.class_8710;
import org.jetbrains.annotations.Nullable;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.gui.PartyMenuPanelRenderer;
import trep.got.client.gui.SettlerMenuPanelRenderer;
import trep.got.client.house.PlayerHouseRenderHelper;
import trep.got.client.map.CastleMapClientState;
import trep.got.client.screen.KingdomCreationScreen;
import trep.got.client.screen.PlayerHouseSigilScreen;
import trep.got.client.util.ClientTextureHelper;
import trep.got.kingdom.ReputationTitle;
import trep.got.network.CastleMapPayload;
import trep.got.network.OpenCastleScreenPayload;
import trep.got.network.RequestCastleStoragePayload;
import trep.got.network.RequestRemoveSettlerPayload;
import trep.got.network.RequestSettlementLevelUpPayload;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotReligionOptions;
import trep.got.religion.ReligionSelectionOption;

@Environment(value=EnvType.CLIENT)
public final class CastleScreen
extends class_437 {
    private static final class_2960 UI_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/castle_ui.png");
    private static final class_2960 BANNER_EDITOR_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/drawing/paint.png");
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
    private static final int STORAGE_BUTTON_SOURCE_SIZE = 32;
    private static final float STORAGE_BUTTON_SCALE = 0.55f;
    private static final float STORAGE_BUTTON_HOVER_SCALE = 0.62f;
    private static final int STORAGE_BUTTON_OUTER_GAP = 6;
    private static final int STORAGE_BUTTON_TOP_OFFSET = 4;
    private static final int LEVEL_SLOT_U = 206;
    private static final int LEVEL_SLOT_V = 142;
    private static final int LEVEL_SLOT_LOCKED_V = 190;
    private static final int LEVEL_SLOT_SIZE = 20;
    private static final int TIER_ICON_U = 176;
    private static final int TIER_ICON_SIZE = 16;
    private static final int TIER_ICON_STEP_V = 16;
    private static final int LEVEL_ROW_Y = 150;
    private static final int LEVEL_ROW_START_X = 30;
    private static final int LEVEL_ROW_GAP = 5;
    private static final int OWNER_SETTLEMENT_SHIFT_X = 64;
    private static final class_2960 DIALOGUE_OPTION_WIDE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/dialogue_option_wide.png");
    private static final class_2960 LOCKED_ENTRY = class_2960.method_60655((String)"agob", (String)"textures/ui/locked_entry.png");
    private static final int KINGDOM_CREATE_BUTTON_WIDTH = 96;
    private static final int KINGDOM_CREATE_BUTTON_HEIGHT = 16;
    private static final int KINGDOM_CREATE_BUTTON_GAP = 7;
    private static final int LOCKED_ICON_SIZE = 10;
    private final OpenCastleScreenPayload payload;
    @Nullable
    private final class_437 parent;
    private String selectedSettlerId = "";

    public CastleScreen(OpenCastleScreenPayload payload, @Nullable class_437 parent) {
        super((class_2561)class_2561.method_43470((String)payload.castleName()));
        this.payload = payload;
        this.parent = parent;
    }

    public static void open(OpenCastleScreenPayload payload, @Nullable class_437 parent) {
        class_310.method_1551().method_1507((class_437)new CastleScreen(payload, parent));
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
        int panelX = (this.field_22789 - Math.round(160.0f * scale)) / 2 - this.ownerSettlementShift();
        int panelY = (this.field_22790 - Math.round(192.0f * scale)) / 2;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)panelX, (float)panelY, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(UI_TEXTURE, 0, 0, 0, 0.0f, 0.0f, 160, 192, 256, 256);
        context.method_51448().method_22909();
        int titleLeft = panelX + CastleScreen.scaled(5, scale);
        int titleRight = panelX + CastleScreen.scaled(154, scale);
        int titleWidth = titleRight - titleLeft;
        context.method_27534(this.field_22793, this.fitText(this.field_22793, this.payload.castleName(), titleWidth), titleLeft + titleWidth / 2, panelY + CastleScreen.scaled(10, scale), -727358);
        int contentX = panelX + CastleScreen.scaled(5, scale);
        int contentY = panelY + CastleScreen.scaled(24, scale);
        int contentWidth = CastleScreen.scaled(149, scale);
        this.drawTextLine(context, (class_2561)class_2561.method_43470((String)(this.ownerTitlePrefix() + CastleScreen.fallback(this.payload.lordName(), "Unknown"))), contentX, contentY, contentWidth);
        CharacterSelectionOption kingdomOption = this.resolveKingdomOption(this.payload.kingdomId());
        class_2960 houseTexture = PlayerHouseRenderHelper.resolveTexture(this.payload.houseId(), this.payload.kingdomId());
        ReligionSelectionOption religionOption = this.resolveReligionOption(this.payload.religionId());
        this.drawIconLine(context, this.kingdomIconFor(kingdomOption), CastleScreen.fallback(this.payload.kingdomName(), "Unknown Kingdom"), contentX, contentY += CastleScreen.scaled(14, scale), contentWidth);
        this.drawIconLine(context, houseTexture, CastleScreen.fallback(PlayerHouseRenderHelper.resolveDisplayName(this.payload.houseId(), this.payload.kingdomId()), "Unknown House"), contentX, contentY += CastleScreen.scaled(14, scale), contentWidth);
        this.drawIconLine(context, religionOption != null ? religionOption.texture() : null, CastleScreen.fallback(this.payload.religionName(), "Unknown Religion"), contentX, contentY += CastleScreen.scaled(14, scale), contentWidth);
        this.drawTextLine(context, (class_2561)class_2561.method_43470((String)CastleScreen.fallback(this.payload.tierName(), "Settlement")), contentX, contentY += CastleScreen.scaled(14, scale), contentWidth);
        if (this.payload.settlerCapacity() >= 0) {
            this.drawTextLine(context, (class_2561)class_2561.method_43470((String)("Settlers " + this.payload.settlers() + "/" + this.payload.settlerCapacity())), contentX, contentY += CastleScreen.scaled(14, scale), contentWidth);
            if (!this.payload.foundedKingdom()) {
                this.drawSettlementLevelRow(context, panelX, panelY, scale, mouseX, mouseY);
                this.drawCreateKingdomButton(context, panelX, panelY, scale, mouseX, mouseY);
            }
        }
        this.drawStorageButton(context, panelX, panelY, scale, mouseX, mouseY);
        this.drawBannerEditorButton(context, panelX, panelY, scale, mouseX, mouseY);
        if (this.payload.settlementOwner()) {
            int settlerPanelX = panelX + CastleScreen.scaled(160, scale) + 4;
            int settlerPanelY = panelY;
            SettlerMenuPanelRenderer.render(context, settlerPanelX, settlerPanelY, mouseX, mouseY, this.payload.settlerEntries(), this.selectedSettlerId, this.payload.settlers(), this.payload.settlerCapacity());
        }
        super.method_25394(context, mouseX, mouseY, delta);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        int panelY;
        int panelX;
        if (button != 0) {
            return super.method_25402(mouseX, mouseY, button);
        }
        if (this.payload.canAccessStorage() && this.isWithinStorageButton(mouseX, mouseY)) {
            ClientPlayNetworking.send((class_8710)new RequestCastleStoragePayload(this.payload.castleId()));
            return true;
        }
        if (this.payload.canEditBanner() && this.isWithinBannerEditorButton(mouseX, mouseY)) {
            PlayerHouseSigilScreen.open(-1);
            return true;
        }
        if (!this.payload.foundedKingdom()) {
            int levelSlot = this.hoveredSettlementLevelSlot(mouseX, mouseY);
            if (levelSlot > 0 && this.isClickableSettlementLevel(levelSlot)) {
                ClientPlayNetworking.send((class_8710)new RequestSettlementLevelUpPayload(this.payload.castleId()));
                return true;
            }
            if (this.isWithinCreateKingdomButton(mouseX, mouseY)) {
                if (this.canCreateKingdom()) {
                    KingdomCreationScreen.open(this.payload, this);
                }
                return true;
            }
        }
        if (this.payload.settlementOwner() && SettlerMenuPanelRenderer.isWithinPanel(panelX = (this.field_22789 - Math.round(160.0f * this.resolveScale())) / 2 - this.ownerSettlementShift() + CastleScreen.scaled(160, this.resolveScale()) + 4, panelY = (this.field_22790 - Math.round(192.0f * this.resolveScale())) / 2, mouseX, mouseY)) {
            String result = SettlerMenuPanelRenderer.handleClick(panelX, panelY, mouseX, mouseY, this.payload.settlerEntries(), this.selectedSettlerId);
            if (result == null) {
                return true;
            }
            if (result.startsWith("remove:")) {
                ClientPlayNetworking.send((class_8710)new RequestRemoveSettlerPayload(this.payload.castleId(), result.substring("remove:".length())));
                return true;
            }
            this.selectedSettlerId = result;
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    private void drawSettlementLevelRow(class_332 context, int panelX, int panelY, float scale, int mouseX, int mouseY) {
        for (int index = 0; index < 5; ++index) {
            int level = index + 1;
            boolean active = index < this.payload.settlementLevel();
            boolean clickable = this.isClickableSettlementLevel(level);
            int x = panelX + CastleScreen.scaled(30 + index * 25, scale);
            int y = panelY + CastleScreen.scaled(150, scale);
            this.drawUiSlice(context, x, y, 20, 20, 206, active || clickable ? 142 : 190, scale);
            int iconV = switch (this.payload.settlementTierId()) {
                case "settlement" -> 16;
                case "lordship" -> 32;
                case "capital" -> 48;
                default -> 0;
            };
            int iconU = active || clickable ? 176 : 192;
            int iconX = x + (CastleScreen.scaled(20, scale) - CastleScreen.scaled(16, scale)) / 2;
            int iconY = y + (CastleScreen.scaled(20, scale) - CastleScreen.scaled(16, scale)) / 2;
            this.drawUiSlice(context, iconX, iconY, 16, 16, iconU, 64 + iconV, scale);
            if (!CastleScreen.isWithin(mouseX, mouseY, x, y, CastleScreen.scaled(20, scale), CastleScreen.scaled(20, scale))) continue;
            if (clickable) {
                context.method_25294(x + 1, y + 1, x + CastleScreen.scaled(20, scale) - 1, y + CastleScreen.scaled(20, scale) - 1, 861392895);
            }
            context.method_51434(this.field_22793, this.levelTooltip(level, clickable), mouseX, mouseY);
        }
    }

    private void drawCreateKingdomButton(class_332 context, int panelX, int panelY, float scale, int mouseX, int mouseY) {
        if (!this.shouldShowCreateKingdomButton()) {
            return;
        }
        int x = CastleScreen.createKingdomButtonX(panelX, scale);
        int y = CastleScreen.createKingdomButtonY(panelY, scale);
        int width = CastleScreen.scaled(96, scale);
        int height = CastleScreen.scaled(16, scale);
        boolean hovered = CastleScreen.isWithin(mouseX, mouseY, x, y, width, height);
        boolean unlocked = this.canCreateKingdom();
        context.method_25290(DIALOGUE_OPTION_WIDE_TEXTURE, x, y, 0.0f, 0.0f, width, height, width, height);
        if (!unlocked) {
            context.method_25294(x, y, x + width, y + height, -1728053248);
            int iconSize = CastleScreen.scaled(10, scale);
            context.method_25290(LOCKED_ENTRY, x + CastleScreen.scaled(5, scale), y + (height - iconSize) / 2, 0.0f, 0.0f, iconSize, iconSize, 10, 10);
        } else if (hovered) {
            context.method_25294(x + 1, y + 1, x + width - 1, y + height - 1, 0x33FFFFFF);
        }
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Create Kingdom"), x + width / 2, y + CastleScreen.scaled(4, scale), unlocked ? -1 : -6250336);
        if (hovered && !unlocked) {
            context.method_51438(this.field_22793, (class_2561)class_2561.method_43470((String)"Requires King, Magister, or Prophet"), mouseX, mouseY);
        }
    }

    private List<class_2561> levelTooltip(int level, boolean clickable) {
        ArrayList<class_2561> tooltip = new ArrayList<class_2561>();
        tooltip.add((class_2561)class_2561.method_43470((String)("Level " + level)));
        tooltip.add((class_2561)class_2561.method_43470((String)"Requirements:"));
        LevelRequirement requirement = this.requirementForDisplayedLevel(level);
        if (requirement == null || requirement.requiredSettlers() <= 0) {
            tooltip.add((class_2561)class_2561.method_43470((String)"- None"));
        } else {
            tooltip.add((class_2561)class_2561.method_43470((String)("- " + requirement.requiredSettlers() + " settlers")));
            if (requirement.requiredMiners() > 0) {
                tooltip.add((class_2561)class_2561.method_43470((String)("- " + requirement.requiredMiners() + " miners")));
            }
            if (requirement.requiredLumberjacks() > 0) {
                tooltip.add((class_2561)class_2561.method_43470((String)("- " + requirement.requiredLumberjacks() + " lumberjacks")));
            }
            if (requirement.requiredFarmers() > 0) {
                tooltip.add((class_2561)class_2561.method_43470((String)("- " + requirement.requiredFarmers() + " farmers")));
            }
        }
        if (clickable) {
            LevelRequirement currentRequirement = this.currentUpgradeRequirement();
            tooltip.add((class_2561)class_2561.method_43470((String)(currentRequirement != null && currentRequirement.nextTierName() != null ? "Click to advance to " + currentRequirement.nextTierName() : "Click to level up")));
        }
        return tooltip;
    }

    private int ownerSettlementShift() {
        return this.payload.settlementOwner() ? CastleScreen.scaled(64, this.resolveScale()) : 0;
    }

    private int hoveredSettlementLevelSlot(double mouseX, double mouseY) {
        if (this.payload.settlerCapacity() < 0) {
            return -1;
        }
        float scale = this.resolveScale();
        int panelX = (this.field_22789 - Math.round(160.0f * scale)) / 2 - this.ownerSettlementShift();
        int panelY = (this.field_22790 - Math.round(192.0f * scale)) / 2;
        for (int index = 0; index < 5; ++index) {
            int y;
            int x = panelX + CastleScreen.scaled(30 + index * 25, scale);
            if (!CastleScreen.isWithin(mouseX, mouseY, x, y = panelY + CastleScreen.scaled(150, scale), CastleScreen.scaled(20, scale), CastleScreen.scaled(20, scale))) continue;
            return index + 1;
        }
        return -1;
    }

    private boolean shouldShowCreateKingdomButton() {
        return this.payload.settlementOwner() && !this.payload.foundedKingdom() && "player_settlement".equals(this.payload.settlementTierId()) && this.payload.settlementLevel() >= 5;
    }

    private boolean canCreateKingdom() {
        ReputationTitle title = CharacterSelectionClientState.getReputationTitle();
        return this.shouldShowCreateKingdomButton() && (title == ReputationTitle.KING || title == ReputationTitle.MAGISTER || title == ReputationTitle.PROPHET);
    }

    private boolean isWithinCreateKingdomButton(double mouseX, double mouseY) {
        if (!this.shouldShowCreateKingdomButton()) {
            return false;
        }
        float scale = this.resolveScale();
        int panelX = (this.field_22789 - Math.round(160.0f * scale)) / 2 - this.ownerSettlementShift();
        int panelY = (this.field_22790 - Math.round(192.0f * scale)) / 2;
        return CastleScreen.isWithin(mouseX, mouseY, CastleScreen.createKingdomButtonX(panelX, scale), CastleScreen.createKingdomButtonY(panelY, scale), CastleScreen.scaled(96, scale), CastleScreen.scaled(16, scale));
    }

    private static int createKingdomButtonX(int panelX, float scale) {
        return panelX + CastleScreen.scaled(32, scale);
    }

    private static int createKingdomButtonY(int panelY, float scale) {
        return panelY + CastleScreen.scaled(177, scale);
    }

    private boolean isClickableSettlementLevel(int level) {
        if (this.payload.foundedKingdom() || !this.payload.settlementOwner() || !this.canAdvanceCurrentSettlementLevel()) {
            return false;
        }
        if (this.payload.settlementLevel() < 5) {
            return level == this.payload.settlementLevel() + 1;
        }
        LevelRequirement currentRequirement = this.currentUpgradeRequirement();
        return level == 5 && currentRequirement != null && currentRequirement.nextTierName() != null;
    }

    private boolean canAdvanceCurrentSettlementLevel() {
        LevelRequirement requirement = this.currentUpgradeRequirement();
        if (requirement == null || this.payload.settlers() < requirement.requiredSettlers()) {
            return false;
        }
        return this.acceptedProfessionCount("miner") >= requirement.requiredMiners() && this.acceptedProfessionCount("lumberjack") >= requirement.requiredLumberjacks() && this.acceptedProfessionCount("farmer") >= requirement.requiredFarmers();
    }

    private int acceptedProfessionCount(String professionId) {
        int count = 0;
        for (OpenCastleScreenPayload.SettlerEntry entry : this.payload.settlerEntries()) {
            if (!professionId.equals(entry.professionId())) continue;
            ++count;
        }
        return count;
    }

    @Nullable
    private LevelRequirement currentUpgradeRequirement() {
        return this.requirementForUpgrade(this.payload.settlementTierId(), this.payload.settlementLevel());
    }

    @Nullable
    private LevelRequirement requirementForDisplayedLevel(int displayedLevel) {
        if (displayedLevel <= 1) {
            return new LevelRequirement(0, 0, 0, 0, null);
        }
        return this.requirementForUpgrade(this.payload.settlementTierId(), displayedLevel - 1);
    }

    @Nullable
    private LevelRequirement requirementForUpgrade(String tierId, int currentLevel) {
        return switch (tierId) {
            case "player_settlement" -> {
                switch (currentLevel) {
                    case 1: {
                        yield new LevelRequirement(1, 0, 0, 0, null);
                    }
                    case 2: {
                        yield new LevelRequirement(2, 0, 0, 0, null);
                    }
                    case 3: {
                        yield new LevelRequirement(3, 0, 0, 0, null);
                    }
                    case 4: {
                        yield new LevelRequirement(5, 1, 1, 1, null);
                    }
                    case 5: {
                        yield new LevelRequirement(6, 2, 2, 2, "Settlement");
                    }
                }
                yield null;
            }
            case "settlement" -> {
                switch (currentLevel) {
                    case 1: {
                        yield new LevelRequirement(8, 0, 0, 0, null);
                    }
                    case 2: {
                        yield new LevelRequirement(10, 0, 0, 0, null);
                    }
                    case 3: {
                        yield new LevelRequirement(12, 0, 0, 0, null);
                    }
                    case 4: {
                        yield new LevelRequirement(14, 2, 2, 2, null);
                    }
                    case 5: {
                        yield new LevelRequirement(16, 3, 3, 3, "Lordship");
                    }
                }
                yield null;
            }
            case "lordship" -> {
                switch (currentLevel) {
                    case 1: {
                        yield new LevelRequirement(18, 0, 0, 0, null);
                    }
                    case 2: {
                        yield new LevelRequirement(20, 0, 0, 0, null);
                    }
                    case 3: {
                        yield new LevelRequirement(22, 0, 0, 0, null);
                    }
                    case 4: {
                        yield new LevelRequirement(24, 3, 3, 3, null);
                    }
                    case 5: {
                        yield new LevelRequirement(26, 4, 4, 4, "Capital");
                    }
                }
                yield null;
            }
            case "capital" -> {
                switch (currentLevel) {
                    case 1: {
                        yield new LevelRequirement(28, 0, 0, 0, null);
                    }
                    case 2: {
                        yield new LevelRequirement(30, 0, 0, 0, null);
                    }
                    case 3: {
                        yield new LevelRequirement(32, 0, 0, 0, null);
                    }
                    case 4: {
                        yield new LevelRequirement(34, 4, 4, 4, null);
                    }
                    case 5: {
                        yield new LevelRequirement(36, 5, 5, 5, null);
                    }
                }
                yield null;
            }
            default -> null;
        };
    }

    private void drawUiSlice(class_332 context, int x, int y, int width, int height, int u, int v, float scale) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(UI_TEXTURE, 0, 0, 0, (float)u, (float)v, width, height, 256, 256);
        context.method_51448().method_22909();
    }

    private void drawTextLine(class_332 context, class_2561 text, int x, int y, int width) {
        context.method_27535(this.field_22793, this.fitText(this.field_22793, text.getString(), width), x, y, -724761);
    }

    private void drawIconLine(class_332 context, @Nullable class_2960 iconTexture, String label, int x, int y, int width) {
        int textX = x;
        if (iconTexture != null) {
            int iconSize = Math.max(9, Math.round(9.0f * this.resolveScale()));
            this.drawScaledFullTexture(context, iconTexture, x, y, iconSize, iconSize);
            textX += Math.max(12, Math.round(13.0f * this.resolveScale()));
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

    @Nullable
    private class_2960 kingdomIconFor(@Nullable CharacterSelectionOption fallbackOption) {
        class_2960 houseTexture;
        CastleMapPayload.DynamicKingdomEntry dynamicKingdom = CastleMapClientState.getDynamicKingdom(this.payload.kingdomId());
        if (dynamicKingdom != null && !dynamicKingdom.houseId().isBlank() && (houseTexture = PlayerHouseRenderHelper.resolveTexture(dynamicKingdom.houseId(), this.payload.kingdomId())) != null) {
            return houseTexture;
        }
        return fallbackOption == null ? null : fallbackOption.texture();
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

    private void drawStorageButton(class_332 context, int panelX, int panelY, float scale, int mouseX, int mouseY) {
        int y;
        if (!this.payload.canAccessStorage()) {
            return;
        }
        int size = Math.round(17.6f);
        int x = this.storageButtonX(panelX, scale);
        boolean hovered = CastleScreen.isWithin(mouseX, mouseY, x, y = panelY + CastleScreen.scaled(4, scale), size, size);
        float drawScale = hovered ? 0.62f : 0.55f;
        int drawSize = Math.round(32.0f * drawScale);
        int drawX = x + (size - drawSize) / 2;
        int drawY = y + (size - drawSize) / 2;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)drawX, (float)drawY, 0.0f);
        context.method_51448().method_22905(drawScale, drawScale, 1.0f);
        context.method_25291(hovered ? PartyMenuPanelRenderer.STORE_BUTTON_HOVER_TEXTURE : PartyMenuPanelRenderer.STORE_BUTTON_TEXTURE, 0, 0, 0, 0.0f, 0.0f, 32, 32, 32, 32);
        context.method_25291(hovered ? PartyMenuPanelRenderer.STORE_OVERLAY_HOVER_TEXTURE : PartyMenuPanelRenderer.STORE_OVERLAY_TEXTURE, 0, 0, 0, 0.0f, 0.0f, 32, 32, 32, 32);
        context.method_51448().method_22909();
        if (hovered) {
            context.method_51438(this.field_22793, (class_2561)class_2561.method_43470((String)"Open Storage"), mouseX, mouseY);
        }
    }

    private void drawBannerEditorButton(class_332 context, int panelX, int panelY, float scale, int mouseX, int mouseY) {
        int y;
        if (!this.payload.canEditBanner()) {
            return;
        }
        int size = Math.round(17.6f);
        int x = this.storageButtonX(panelX, scale);
        boolean hovered = CastleScreen.isWithin(mouseX, mouseY, x, y = panelY + CastleScreen.scaled(4, scale) + size + CastleScreen.scaled(4, scale), size, size);
        float drawScale = hovered ? 0.62f : 0.55f;
        int drawSize = Math.round(32.0f * drawScale);
        int drawX = x + (size - drawSize) / 2;
        int drawY = y + (size - drawSize) / 2;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)drawX, (float)drawY, 0.0f);
        context.method_51448().method_22905(drawScale, drawScale, 1.0f);
        context.method_25291(hovered ? PartyMenuPanelRenderer.STORE_BUTTON_HOVER_TEXTURE : PartyMenuPanelRenderer.STORE_BUTTON_TEXTURE, 0, 0, 0, 0.0f, 0.0f, 32, 32, 32, 32);
        context.method_25290(BANNER_EDITOR_ICON, 8, 8, 0.0f, 0.0f, 16, 16, 16, 16);
        context.method_51448().method_22909();
        if (hovered) {
            ArrayList<class_5250> tooltip = new ArrayList<class_5250>();
            tooltip.add(class_2561.method_43470((String)"Banner Editor"));
            if (!PlayerHouseRenderHelper.isCustomHouse(CharacterSelectionClientState.getSelectedOptionId())) {
                tooltip.add(class_2561.method_43470((String)"Cannot revert back to").method_27692(class_124.field_1061));
                tooltip.add(class_2561.method_43470((String)"Lord's Banner after creation.").method_27692(class_124.field_1061));
            }
            context.method_51434(this.field_22793, tooltip, mouseX, mouseY);
        }
    }

    private boolean isWithinStorageButton(double mouseX, double mouseY) {
        float scale = this.resolveScale();
        int size = Math.round(17.6f);
        int panelX = (this.field_22789 - Math.round(160.0f * scale)) / 2 - this.ownerSettlementShift();
        int panelY = (this.field_22790 - Math.round(192.0f * scale)) / 2;
        int x = this.storageButtonX(panelX, scale);
        int y = panelY + CastleScreen.scaled(4, scale);
        return CastleScreen.isWithin(mouseX, mouseY, x, y, size, size);
    }

    private boolean isWithinBannerEditorButton(double mouseX, double mouseY) {
        float scale = this.resolveScale();
        int size = Math.round(17.6f);
        int panelX = (this.field_22789 - Math.round(160.0f * scale)) / 2 - this.ownerSettlementShift();
        int panelY = (this.field_22790 - Math.round(192.0f * scale)) / 2;
        int x = this.storageButtonX(panelX, scale);
        int y = panelY + CastleScreen.scaled(4, scale) + size + CastleScreen.scaled(4, scale);
        return CastleScreen.isWithin(mouseX, mouseY, x, y, size, size);
    }

    private int storageButtonX(int panelX, float scale) {
        if (this.payload.settlementOwner()) {
            return panelX - CastleScreen.scaled(6, scale) - Math.round(17.6f);
        }
        return panelX + CastleScreen.scaled(160, scale) + CastleScreen.scaled(6, scale);
    }

    private String ownerTitlePrefix() {
        return "agob:the_nights_watch".equals(this.payload.kingdomId()) ? "Lord Commander " : "Lord ";
    }

    private float resolveScale() {
        float availableWidth = Math.max(160.0f, (float)this.field_22789 - 48.0f);
        float availableHeight = Math.max(192.0f, (float)this.field_22790 - 48.0f);
        return Math.min(2.0f, Math.min(availableWidth / 160.0f, availableHeight / 192.0f));
    }

    private static String fallback(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }

    private static int scaled(int sourcePixels, float scale) {
        return Math.round((float)sourcePixels * scale);
    }

    private static boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    @Environment(value=EnvType.CLIENT)
    private record LevelRequirement(int requiredSettlers, int requiredMiners, int requiredLumberjacks, int requiredFarmers, @Nullable String nextTierName) {
    }

    @Environment(value=EnvType.CLIENT)
    private record TextureSize(int width, int height) {
        private static final TextureSize DEFAULT = new TextureSize(16, 16);
    }
}

