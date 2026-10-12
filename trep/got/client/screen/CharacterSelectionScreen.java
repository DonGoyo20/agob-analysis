/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_1109
 *  net.minecraft.class_1113
 *  net.minecraft.class_1309
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3417
 *  net.minecraft.class_3532
 *  net.minecraft.class_437
 *  net.minecraft.class_490
 *  net.minecraft.class_5250
 *  net.minecraft.class_5348
 *  net.minecraft.class_6880
 *  net.minecraft.class_742
 *  net.minecraft.class_8710
 */
package trep.got.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_1309;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3417;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_490;
import net.minecraft.class_5250;
import net.minecraft.class_5348;
import net.minecraft.class_6880;
import net.minecraft.class_742;
import net.minecraft.class_8710;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.PlayerGeneticTrait;
import trep.got.character.PlayerPersonalityTrait;
import trep.got.character.PlayerSkinBuilderProfile;
import trep.got.character.PlayerTraitEffects;
import trep.got.client.animations.UiAnimation;
import trep.got.client.animations.UiEasing;
import trep.got.client.character.CharacterOptionLoader;
import trep.got.client.character.CharacterPreviewPlayerEntity;
import trep.got.client.character.CharacterPreviewSkinResolver;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.character.PlayerSkinBuilderOptions;
import trep.got.client.character.PlayerSkinBuilderSkinResolver;
import trep.got.client.religion.ReligionOptionLoader;
import trep.got.network.SelectCharacterPayload;
import trep.got.registry.GotKingdoms;
import trep.got.religion.ReligionSelectionOption;
import trep.got.skill.SkillAttributeType;

@Environment(value=EnvType.CLIENT)
public class CharacterSelectionScreen
extends class_437 {
    private static final class_2960 UI_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/character_selection.png");
    private static final class_2960 RELIGION_FRAME_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/religion_frame.png");
    private static final class_2960 DIALOGUE_OPTION_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/dialogue_option.png");
    private static final class_2960 DIALOGUE_OPTION_WIDE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/dialogue_option_wide.png");
    private static final class_2960 COBBLESTONE_TEXTURE = class_2960.method_60656((String)"textures/block/cobblestone.png");
    private static final class_2960 STONE_TEXTURE = class_2960.method_60656((String)"textures/block/stone.png");
    private static final class_2960 ANDESITE_TEXTURE = class_2960.method_60656((String)"textures/block/andesite.png");
    private static final List<class_2960> BACKGROUND_TILES = List.of(COBBLESTONE_TEXTURE, STONE_TEXTURE, ANDESITE_TEXTURE);
    private static final int OPTIONS_PER_PAGE = 4;
    private static final int ATLAS_SIZE = 256;
    private static final int RELIGION_TEXTURE_SIZE = 48;
    private static final int KINGDOM_TEXTURE_WIDTH = 48;
    private static final int KINGDOM_TEXTURE_HEIGHT = 62;
    private static final int FRAME_U = 16;
    private static final int FRAME_V = 16;
    private static final int FRAME_WIDTH = 32;
    private static final int FRAME_HEIGHT = 64;
    private static final int CHOOSE_U = 8;
    private static final int CHOOSE_V = 88;
    private static final int CHOOSE_HOVER_V = 120;
    private static final int CHOOSE_WIDTH = 48;
    private static final int CHOOSE_HEIGHT = 16;
    private static final int CONFIRM_U = 16;
    private static final int CONFIRM_V = 160;
    private static final int CONFIRM_HOVER_V = 144;
    private static final int CONFIRM_WIDTH = 64;
    private static final int CONFIRM_HEIGHT = 16;
    private static final int LEFT_ARROW_U = 16;
    private static final int RIGHT_ARROW_U = 32;
    private static final int ARROW_V = 192;
    private static final int ARROW_HOVER_V = 176;
    private static final int ARROW_SIZE = 16;
    private static final int REVIEW_PANEL_EXTRA_HEIGHT = 16;
    private static final int REVIEW_FOOTER_OFFSET_Y = 16;
    private static final int DESCRIPTION_U = 160;
    private static final int DESCRIPTION_V = 128;
    private static final int DESCRIPTION_WIDTH = 96;
    private static final int DESCRIPTION_HEIGHT = 128;
    private static final int TILE_SIZE = 24;
    private static final int CARD_COLUMNS = 4;
    private static final int CARD_WIDTH = 78;
    private static final int CARD_HEIGHT = 122;
    private static final int CARD_GAP = 8;
    private static final int FRAME_DRAW_WIDTH = 46;
    private static final int FRAME_DRAW_HEIGHT = 92;
    private static final int FRAME_ENTITY_SIZE = 28;
    private static final int CHOOSE_DRAW_WIDTH = 40;
    private static final int CHOOSE_DRAW_HEIGHT = 13;
    private static final int DETAIL_FRAME_WIDTH = 82;
    private static final int DETAIL_FRAME_HEIGHT = 164;
    private static final int DETAIL_ENTITY_SIZE = 50;
    private static final int DETAIL_DESCRIPTION_WIDTH = 100;
    private static final int DETAIL_DESCRIPTION_HEIGHT = 132;
    private static final int RELIGION_FRAME_DRAW_SIZE = 52;
    private static final int RELIGION_ICON_DRAW_SIZE = 44;
    private static final int RELIGION_STAGGER_OFFSET = 0;
    private static final float STAT_TEXT_SCALE = 0.665f;
    private static final int STAT_TEXT_INSET_X = 6;
    private static final int STAT_TEXT_INSET_Y = 4;
    private static final int STAT_TEXT_LINE_HEIGHT = 12;
    private static final int STAT_ICON_SOURCE_SIZE = 16;
    private static final int STAT_ICON_DRAW_SIZE = 10;
    private static final int STAT_ICON_GAP = 3;
    private static final int KINGDOM_LABEL_ICON_DRAW_SIZE = 9;
    private static final int KINGDOM_LABEL_ICON_GAP = 3;
    private static final int BOOSTED_CAP_TEXT_COLOR = 10940556;
    private static final int REDUCED_CAP_TEXT_COLOR = 0xFFAAAA;
    private static final int TRAIT_BUTTON_WIDTH = 64;
    private static final int TRAIT_BUTTON_WIDE_WIDTH = 96;
    private static final int TRAIT_BUTTON_HEIGHT = 16;
    private static final int TRAIT_SECTION_GAP = 14;
    private static final int TRAIT_BUTTON_GAP = 4;
    private static final int TRAIT_TOOLTIP_ICON_SIZE = 9;
    private static final int TRAIT_TOOLTIP_LINE_HEIGHT = 12;
    private static final float TRAIT_PANEL_SCALE = 0.625f;
    private static final int BUILDER_LABEL_WIDTH = 78;
    private static final int BUILDER_OPTION_SOURCE_WIDTH = 96;
    private static final int BUILDER_OPTION_SOURCE_HEIGHT = 16;
    private static final int BUILDER_OPTION_DRAW_WIDTH = 38;
    private static final int BUILDER_OPTION_DRAW_HEIGHT = 7;
    private static final int BUILDER_ARROW_DRAW_SIZE = 8;
    private static final int BUILDER_ROW_GAP = 12;
    private static final float BUILDER_LABEL_SCALE = 0.58f;
    private static final float BUILDER_VALUE_SCALE = 0.44f;
    private static final int BUILDER_ROW_COUNT = 7;
    private final List<ReligionSelectionOption> religionOptions = new ArrayList<ReligionSelectionOption>();
    private final List<CharacterSelectionOption> options = new ArrayList<CharacterSelectionOption>();
    private final UiAnimation introFade = new UiAnimation(20);
    private final UiAnimation stepTransition = new UiAnimation(18);
    private ReligionSelectionOption selectedReligion;
    private RegionTab selectedRegion;
    private CharacterSelectionOption selectedKingdomOption;
    private CharacterSelectionOption selectedOption;
    private PlayerGeneticTrait selectedGeneticTrait;
    private final LinkedHashSet<PlayerPersonalityTrait> selectedPersonalityTraits = new LinkedHashSet();
    private PlayerSkinBuilderProfile skinBuilderProfile = PlayerSkinBuilderProfile.DEFAULT;
    private boolean playerBuilderComplete;
    private int currentPage;
    private int religionPage;
    private boolean submitting;

    public CharacterSelectionScreen() {
        super((class_2561)class_2561.method_43470((String)"Character Selection"));
        this.introFade.playForward();
    }

    protected void method_25426() {
        super.method_25426();
        this.religionOptions.clear();
        this.religionOptions.addAll(ReligionOptionLoader.load());
        this.options.clear();
        this.options.addAll(CharacterOptionLoader.load());
        this.currentPage = Math.min(this.currentPage, Math.max(0, this.pageCount() - 1));
        this.religionPage = Math.min(this.religionPage, Math.max(0, this.religionPageCount() - 1));
        if (this.selectedGeneticTrait == null && !CharacterSelectionClientState.getSelectedGeneticTraitId().isBlank()) {
            this.selectedGeneticTrait = PlayerGeneticTrait.byId(CharacterSelectionClientState.getSelectedGeneticTraitId());
        }
        if (this.selectedPersonalityTraits.isEmpty()) {
            for (String traitId : CharacterSelectionClientState.getSelectedPersonalityTraitIds()) {
                PlayerPersonalityTrait trait = PlayerPersonalityTrait.byId(traitId);
                if (trait == null) continue;
                this.selectedPersonalityTraits.add(trait);
            }
        }
        this.skinBuilderProfile = this.ensureSkinBuilderDefaults(CharacterSelectionClientState.getSkinBuilderProfile());
    }

    public void method_25393() {
        super.method_25393();
        this.introFade.tick();
        this.stepTransition.tick();
        if (this.selectedOption != null && this.stepTransition.isAtStart() && !this.stepTransition.isRunning() && !this.submitting) {
            this.selectedOption = null;
        }
    }

    public boolean method_25422() {
        return false;
    }

    public boolean method_25421() {
        return false;
    }

    protected void method_57734(float delta) {
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        int fadeAlpha;
        this.renderPatternedBackground(context);
        int panelWidth = Math.min(this.field_22789 - 64, 960);
        int panelHeight = Math.min(this.field_22790 - 56, 560);
        int panelX = (this.field_22789 - panelWidth) / 2;
        int panelY = (this.field_22790 - panelHeight) / 2;
        float introProgress = this.introFade.eased();
        float reviewProgress = this.stepTransition.eased();
        boolean showPlayerBuilderUi = !this.playerBuilderComplete;
        boolean showReligionUi = this.playerBuilderComplete && this.selectedReligion == null;
        boolean showRegionUi = this.playerBuilderComplete && this.selectedReligion != null && this.selectedRegion == null && this.selectedKingdomOption == null && this.selectedOption == null && this.stepTransition.isAtStart();
        boolean showKingdomUi = this.selectedReligion != null && this.selectedRegion != null && this.selectedKingdomOption == null && this.selectedOption == null && this.stepTransition.isAtStart();
        boolean showHouseUi = this.selectedReligion != null && this.selectedRegion != null && this.selectedKingdomOption != null && this.selectedOption == null && this.stepTransition.isAtStart();
        boolean showReviewUi = this.selectedOption != null && this.stepTransition.isAtEnd() && !this.stepTransition.isPlayingBackward();
        float religionAlpha = showReligionUi ? introProgress : 0.0f;
        float regionAlpha = showRegionUi ? introProgress : 0.0f;
        float kingdomAlpha = showKingdomUi || showHouseUi ? introProgress : 0.0f;
        float reviewAlpha = showReviewUi ? introProgress : 0.0f;
        TraitTooltipData hoveredTraitTooltip = null;
        this.renderPanel(context, panelX, panelY, panelWidth, panelHeight);
        this.renderHeader(context, panelY, showPlayerBuilderUi, showReligionUi, showRegionUi, showKingdomUi, showHouseUi, showReviewUi, introProgress);
        this.renderPlayerBuilder(context, mouseX, mouseY, panelX, panelY, panelWidth, panelHeight, showPlayerBuilderUi ? introProgress : 0.0f);
        this.renderReligionEntries(context, mouseX, mouseY, panelX, panelY, panelWidth, panelHeight, religionAlpha);
        this.renderRegionEntries(context, mouseX, mouseY, panelX, panelY, panelWidth, panelHeight, regionAlpha);
        this.renderBrowseEntries(context, mouseX, mouseY, panelX, panelY, panelWidth, panelHeight, kingdomAlpha);
        this.renderAnimatedSelectedOption(context, mouseX, mouseY, panelX, panelY, panelWidth, panelHeight, reviewProgress, introProgress);
        this.renderDescriptionPanel(context, panelX, panelY, panelWidth, panelHeight, reviewAlpha);
        hoveredTraitTooltip = this.renderTraitPanel(context, mouseX, mouseY, panelX, panelY, panelWidth, panelHeight, reviewAlpha);
        this.renderKingdomBackButton(context, mouseX, mouseY, panelX, panelY, panelWidth, panelHeight, Math.max(regionAlpha, kingdomAlpha));
        this.renderFooterButtons(context, mouseX, mouseY, panelX, panelY, panelWidth, panelHeight, reviewAlpha);
        this.renderPageControls(context, mouseX, mouseY, panelY, panelHeight, religionAlpha, kingdomAlpha);
        if (hoveredTraitTooltip != null) {
            this.drawTraitTooltip(context, mouseX, mouseY, hoveredTraitTooltip);
        }
        if (this.submitting) {
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Confirming selection..."), this.field_22789 / 2, panelY + panelHeight - 70, CharacterSelectionScreen.withAlpha(0xFFFFFF, introProgress));
        }
        if ((fadeAlpha = class_3532.method_15375((float)((1.0f - introProgress) * 255.0f))) > 0) {
            context.method_25294(0, 0, this.field_22789, this.field_22790, fadeAlpha << 24);
        }
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button != 0 || this.submitting) {
            return super.method_25402(mouseX, mouseY, button);
        }
        int panelWidth = Math.min(this.field_22789 - 64, 960);
        int panelHeight = Math.min(this.field_22790 - 56, 560);
        int panelX = (this.field_22789 - panelWidth) / 2;
        int panelY = (this.field_22790 - panelHeight) / 2;
        if (!this.playerBuilderComplete) {
            return this.handlePlayerBuilderClick(mouseX, mouseY, panelX, panelY, panelWidth, panelHeight) || super.method_25402(mouseX, mouseY, button);
        }
        if (this.selectedReligion == null) {
            if (this.handleReligionPageButtons(mouseX, mouseY, panelY, panelHeight)) {
                return true;
            }
            return this.handleReligionButtons(mouseX, mouseY, panelX, panelY, panelWidth, panelHeight) || super.method_25402(mouseX, mouseY, button);
        }
        if (this.selectedRegion == null && this.selectedOption == null && this.stepTransition.isAtStart()) {
            if (this.handleBrowseBackButton(mouseX, mouseY, panelX, panelY, panelWidth, panelHeight)) {
                return true;
            }
            return this.handleRegionButtons(mouseX, mouseY, panelX, panelY, panelWidth, panelHeight) || super.method_25402(mouseX, mouseY, button);
        }
        if (this.selectedOption == null && this.stepTransition.isAtStart()) {
            if (this.handleBrowseBackButton(mouseX, mouseY, panelX, panelY, panelWidth, panelHeight)) {
                return true;
            }
            if (this.handleChooseButtons(mouseX, mouseY, panelX, panelY, panelWidth, panelHeight)) {
                return true;
            }
            return this.handlePageButtons(mouseX, mouseY, panelY, panelHeight) || super.method_25402(mouseX, mouseY, button);
        }
        if (this.handleTraitButtons(mouseX, mouseY, panelX, panelY, panelWidth, panelHeight)) {
            return true;
        }
        return this.handleFooterButtons(mouseX, mouseY, panelX, panelY, panelWidth, panelHeight) || super.method_25402(mouseX, mouseY, button);
    }

    private void renderPanel(class_332 context, int panelX, int panelY, int panelWidth, int panelHeight) {
        context.method_25294(panelX - 4, panelY - 4, panelX + panelWidth + 4, panelY + panelHeight + 4, -388447838);
        context.method_25294(panelX - 1, panelY - 1, panelX + panelWidth + 1, panelY + panelHeight + 1, -12372949);
        context.method_25294(panelX, panelY, panelX + panelWidth, panelY + panelHeight, -367717099);
        context.method_25294(panelX + 10, panelY + 10, panelX + panelWidth - 10, panelY + panelHeight - 10, -869586908);
        context.method_25294(panelX + 18, panelY + 18, panelX + panelWidth - 18, panelY + panelHeight - 18, -2011161832);
    }

    private void renderHeader(class_332 context, int panelY, boolean showPlayerBuilderUi, boolean showReligionUi, boolean showRegionUi, boolean showKingdomUi, boolean showHouseUi, boolean showReviewUi, float introAlpha) {
        if (showPlayerBuilderUi) {
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Build Your Character"), this.field_22789 / 2, panelY + 22, CharacterSelectionScreen.withAlpha(16181195, introAlpha));
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Choose the skin layers for this run"), this.field_22789 / 2, panelY + 40, CharacterSelectionScreen.withAlpha(12169117, introAlpha));
        } else if (showReligionUi) {
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Choose Your Religion"), this.field_22789 / 2, panelY + 22, CharacterSelectionScreen.withAlpha(16181195, introAlpha));
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Select a faith to continue"), this.field_22789 / 2, panelY + 40, CharacterSelectionScreen.withAlpha(12169117, introAlpha));
        } else if (showRegionUi) {
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Choose Your Region"), this.field_22789 / 2, panelY + 22, CharacterSelectionScreen.withAlpha(16181195, introAlpha));
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Select a continent to continue"), this.field_22789 / 2, panelY + 40, CharacterSelectionScreen.withAlpha(12169117, introAlpha));
        } else if (showKingdomUi) {
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Choose Your Kingdom"), this.field_22789 / 2, panelY + 22, CharacterSelectionScreen.withAlpha(16181195, introAlpha));
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Select a kingdom to continue"), this.field_22789 / 2, panelY + 40, CharacterSelectionScreen.withAlpha(12169117, introAlpha));
        } else if (showHouseUi) {
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Choose Your House"), this.field_22789 / 2, panelY + 22, CharacterSelectionScreen.withAlpha(16181195, introAlpha));
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)CharacterSelectionScreen.getKingdomLabel(this.selectedKingdomOption)), this.field_22789 / 2, panelY + 40, CharacterSelectionScreen.withAlpha(12169117, introAlpha));
        } else if (showReviewUi) {
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Choose Your House"), this.field_22789 / 2, panelY + 22, CharacterSelectionScreen.withAlpha(16181195, introAlpha));
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Review your selection"), this.field_22789 / 2, panelY + 40, CharacterSelectionScreen.withAlpha(12169117, introAlpha));
        }
    }

    private void renderReligionEntries(class_332 context, int mouseX, int mouseY, int panelX, int panelY, int panelWidth, int panelHeight, float alpha) {
        if (alpha <= 0.0f) {
            return;
        }
        int totalRowWidth = 336;
        int startX = panelX + (panelWidth - totalRowWidth) / 2;
        int startY = panelY + (panelHeight - 122) / 2;
        List<ReligionSelectionOption> pageOptions = this.religionPageOptions();
        for (int index = 0; index < pageOptions.size(); ++index) {
            ReligionSelectionOption option = pageOptions.get(index);
            int x = startX + index * 86;
            this.renderReligionCard(context, mouseX, mouseY, option, x, this.religionCardY(startY, index), alpha);
        }
    }

    private void renderPlayerBuilder(class_332 context, int mouseX, int mouseY, int panelX, int panelY, int panelWidth, int panelHeight, float alpha) {
        int buttonY;
        int buttonX;
        if (alpha <= 0.0f) {
            return;
        }
        this.skinBuilderProfile = this.ensureSkinBuilderDefaults(this.skinBuilderProfile);
        List<PlayerBuilderRow> rows = this.playerBuilderRows();
        int frameDrawWidth = 82;
        int frameDrawHeight = 164;
        int contentTop = panelY + 64;
        int contentBottom = panelY + panelHeight - 36;
        int contentHeight = Math.max(frameDrawHeight, contentBottom - contentTop);
        int frameX = class_3532.method_15340((int)(panelX + panelWidth / 4 - frameDrawWidth / 2), (int)(panelX + 36), (int)(panelX + panelWidth / 2 - frameDrawWidth - 24));
        int frameY = contentTop + (contentHeight - frameDrawHeight) / 2;
        class_742 preview = this.getPlayerBuilderPreview();
        if (preview != null) {
            class_490.method_2486((class_332)context, (int)(frameX + 6), (int)(frameY + 8), (int)(frameX + frameDrawWidth - 6), (int)(frameY + frameDrawHeight - 6), (int)50, (float)0.0f, (float)mouseX, (float)mouseY, (class_1309)preview);
        }
        int rowX = CharacterSelectionScreen.playerBuilderRowsX(panelX, panelWidth);
        int rowY = CharacterSelectionScreen.playerBuilderRowsY(panelY, panelHeight, rows.size());
        for (int index = 0; index < rows.size(); ++index) {
            this.renderPlayerBuilderRow(context, mouseX, mouseY, rows.get(index), rowX, rowY + index * 12, alpha);
        }
        boolean hovered = CharacterSelectionScreen.isWithin(mouseX, mouseY, buttonX = panelX + (panelWidth - 64) / 2, buttonY = panelY + panelHeight - 16 - 8, 64, 16);
        CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, buttonX, buttonY, 16, hovered ? 144 : 160, 64, 16, 64, 16, alpha);
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Continue"), buttonX + 32, buttonY + 4, CharacterSelectionScreen.withAlpha(hovered ? 15782746 : 0xFFFFFF, alpha));
    }

    private void renderPlayerBuilderRow(class_332 context, int mouseX, int mouseY, PlayerBuilderRow row, int x, int y, float alpha) {
        float rowAlpha = row.active() ? alpha : alpha * 0.45f;
        int controlY = y + 3;
        int leftX = x + 78 + 7;
        int buttonX = leftX + 8 + 3;
        int rightX = buttonX + 38 + 3;
        boolean leftHovered = row.active() && CharacterSelectionScreen.isWithin(mouseX, mouseY, leftX, controlY, 8, 8);
        boolean rightHovered = row.active() && CharacterSelectionScreen.isWithin(mouseX, mouseY, rightX, controlY, 8, 8);
        this.drawFittedText(context, (class_2561)class_2561.method_43470((String)row.label()), x, controlY + 2, CharacterSelectionScreen.withAlpha(16181195, rowAlpha), 78, 0.58f);
        CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, leftX, controlY, 16, leftHovered ? 176 : 192, 16, 16, 8, 8, rowAlpha);
        CharacterSelectionScreen.drawStandaloneTexture(context, DIALOGUE_OPTION_WIDE_TEXTURE, buttonX, controlY, 38, 7, 96, 16, rowAlpha);
        CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, rightX, controlY, 32, rightHovered ? 176 : 192, 16, 16, 8, 8, rowAlpha);
        this.drawFittedCenteredText(context, (class_2561)class_2561.method_43470((String)row.valueLabel()), (float)buttonX + 19.0f, (float)controlY + 1.0f, CharacterSelectionScreen.withAlpha(row.active() ? 0xFFFFFF : 12169117, rowAlpha), 30, 0.44f);
    }

    private static int playerBuilderRowsX(int panelX, int panelWidth) {
        int totalWidth = 145;
        int idealX = panelX + panelWidth * 3 / 4 - totalWidth / 2 - 48;
        int minX = panelX + panelWidth / 2 + 18;
        int maxX = panelX + panelWidth - totalWidth - 42;
        return class_3532.method_15340((int)idealX, (int)minX, (int)Math.max(minX, maxX));
    }

    private static int playerBuilderRowsY(int panelY, int panelHeight, int rowCount) {
        int totalHeight = Math.max(7, 79);
        int contentTop = panelY + 66;
        int contentBottom = panelY + panelHeight - 42;
        return class_3532.method_15340((int)(contentTop + (Math.max(totalHeight, contentBottom - contentTop) - totalHeight) / 2), (int)contentTop, (int)Math.max(contentTop, contentBottom - totalHeight));
    }

    private void renderReligionCard(class_332 context, int mouseX, int mouseY, ReligionSelectionOption option, int x, int y, float alpha) {
        int frameX = x + 13;
        int frameY = y + 18;
        CharacterSelectionScreen.drawStandaloneTexture(context, RELIGION_FRAME_TEXTURE, frameX, frameY, 52, 52, 48, 48, alpha);
        int iconInset = 4;
        CharacterSelectionScreen.drawStandaloneTexture(context, option.texture(), frameX + iconInset, frameY + iconInset, 44, 44, 48, 48, alpha);
        int labelY = frameY + 52 + 8;
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)option.displayName()), x + 39, labelY, CharacterSelectionScreen.withAlpha(0xFFFFFF, alpha));
        int buttonX = x + 19;
        int buttonY = labelY + 8;
        boolean hovered = CharacterSelectionScreen.isWithin(mouseX, mouseY, buttonX, buttonY, 40, 13);
        CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, buttonX, buttonY, 8, hovered ? 120 : 88, 48, 16, 40, 13, alpha);
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Select"), buttonX + 20, buttonY + 3, CharacterSelectionScreen.withAlpha(hovered ? 15782746 : 0xFFFFFF, alpha));
    }

    private void renderRegionEntries(class_332 context, int mouseX, int mouseY, int panelX, int panelY, int panelWidth, int panelHeight, float alpha) {
        if (alpha <= 0.0f) {
            return;
        }
        RegionTab[] regions = RegionTab.values();
        int cardWidth = 96;
        int cardHeight = 52;
        int gap = 24;
        int totalRowWidth = cardWidth * regions.length + gap * (regions.length - 1);
        int startX = panelX + (panelWidth - totalRowWidth) / 2;
        int startY = panelY + (panelHeight - cardHeight) / 2;
        for (int index = 0; index < regions.length; ++index) {
            RegionTab region = regions[index];
            int x = startX + index * (cardWidth + gap);
            int titleY = startY + 4;
            this.drawScaledCenteredText(context, (class_2561)class_2561.method_43470((String)region.displayName()), (float)x + (float)cardWidth / 2.0f, titleY, CharacterSelectionScreen.withAlpha(16181195, alpha), 1.35f);
            int buttonX = x + (cardWidth - 40) / 2;
            int buttonY = titleY + 24;
            boolean hovered = CharacterSelectionScreen.isWithin(mouseX, mouseY, buttonX, buttonY, 40, 13);
            CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, buttonX, buttonY, 8, hovered ? 120 : 88, 48, 16, 40, 13, alpha);
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Select"), buttonX + 20, buttonY + 3, CharacterSelectionScreen.withAlpha(hovered ? 15782746 : 0xFFFFFF, alpha));
        }
    }

    private void renderBrowseEntries(class_332 context, int mouseX, int mouseY, int panelX, int panelY, int panelWidth, int panelHeight, float alpha) {
        if (alpha <= 0.0f || this.selectedOption != null) {
            return;
        }
        List<CharacterSelectionOption> pageOptions = this.pageOptions();
        int totalRowWidth = 78 * pageOptions.size() + 8 * Math.max(0, pageOptions.size() - 1);
        int startX = panelX + (panelWidth - totalRowWidth) / 2;
        int startY = panelY + (panelHeight - 122) / 2 + 14;
        for (int index = 0; index < pageOptions.size(); ++index) {
            CharacterSelectionOption option = pageOptions.get(index);
            int x = startX + index * 86;
            this.drawOptionCard(context, mouseX, mouseY, option, x, startY, 46, 92, 28, alpha, true, true, this.selectedKingdomOption == null ? CharacterSelectionScreen.getKingdomLabel(option) : CharacterSelectionScreen.getHouseLabel(option));
        }
    }

    private void renderAnimatedSelectedOption(class_332 context, int mouseX, int mouseY, int panelX, int panelY, int panelWidth, int panelHeight, float detailProgress, float alpha) {
        if (this.selectedOption == null) {
            return;
        }
        List<CharacterSelectionOption> pageOptions = this.pageOptions();
        int totalRowWidth = 78 * pageOptions.size() + 8 * Math.max(0, pageOptions.size() - 1);
        int rowStartX = panelX + (panelWidth - totalRowWidth) / 2;
        int rowY = panelY + (panelHeight - 122) / 2 + 14;
        int indexInPage = pageOptions.indexOf(this.selectedOption);
        if (indexInPage < 0) {
            indexInPage = 0;
        }
        float startX = rowStartX + indexInPage * 86;
        float startY = rowY;
        float targetX = panelX + 32;
        float targetY = (float)panelY + (float)panelHeight / 2.0f - 61.0f + 2.0f;
        float currentX = UiEasing.lerp(detailProgress, startX, targetX);
        float currentY = UiEasing.lerp(detailProgress, startY, targetY);
        float frameWidth = UiEasing.lerp(detailProgress, 46.0f, 82.0f);
        float frameHeight = UiEasing.lerp(detailProgress, 92.0f, 164.0f);
        float entitySize = UiEasing.lerp(detailProgress, 28.0f, 50.0f);
        this.drawSelectedEntity(context, mouseX, mouseY, Math.round(currentX), Math.round(currentY), Math.round(frameWidth), Math.round(frameHeight), Math.round(entitySize), alpha);
        float startLabelX = startX + 39.0f;
        float startLabelY = startY + 8.0f + 92.0f + 6.0f;
        float targetLabelX = targetX + 41.0f;
        float targetLabelY = targetY + 14.0f;
        float currentLabelX = UiEasing.lerp(detailProgress, startLabelX, targetLabelX);
        float currentLabelY = UiEasing.lerp(detailProgress, startLabelY, targetLabelY);
        float headingScale = UiEasing.lerp(detailProgress, 1.0f, 1.5f);
        this.drawScaledCenteredText(context, (class_2561)class_2561.method_43470((String)CharacterSelectionScreen.getHouseLabel(this.selectedOption)), currentLabelX, currentLabelY, CharacterSelectionScreen.withAlpha(0xFFFFFF, alpha), headingScale);
    }

    private void drawOptionCard(class_332 context, int mouseX, int mouseY, CharacterSelectionOption option, int x, int y, int frameDrawWidth, int frameDrawHeight, int entitySize, float alpha, boolean showButton, boolean showFrame, String label) {
        int buttonY;
        int buttonX;
        class_742 previewPlayer;
        int frameX = x + (78 - frameDrawWidth) / 2;
        int frameY = y + 8;
        if (showFrame) {
            CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, frameX, frameY, 16, 16, 32, 64, frameDrawWidth, frameDrawHeight, alpha);
        }
        if ((previewPlayer = this.getPreviewPlayer(option)) != null) {
            int entityInset = Math.max(4, frameDrawWidth / 8);
            class_490.method_2486((class_332)context, (int)(frameX + entityInset), (int)(frameY + entityInset), (int)(frameX + frameDrawWidth - entityInset), (int)(frameY + frameDrawHeight - entityInset), (int)entitySize, (float)0.0f, (float)mouseX, (float)mouseY, (class_1309)previewPlayer);
        }
        int labelY = frameY + frameDrawHeight + 6;
        if (showButton) {
            this.drawCenteredLabelWithIcon(context, option.texture(), 48, 62, 9, 3, (class_2561)class_2561.method_43470((String)label), (float)x + 39.0f, labelY, CharacterSelectionScreen.withAlpha(0xFFFFFF, alpha), alpha);
        }
        if (!showButton) {
            return;
        }
        boolean hovered = CharacterSelectionScreen.isWithin(mouseX, mouseY, buttonX = x + 19, buttonY = labelY + 8, 40, 13);
        CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, buttonX, buttonY, 8, hovered ? 120 : 88, 48, 16, 40, 13, alpha);
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Select"), buttonX + 20, buttonY + 3, CharacterSelectionScreen.withAlpha(hovered ? 15782746 : 0xFFFFFF, alpha));
    }

    private void drawSelectedEntity(class_332 context, int mouseX, int mouseY, int x, int y, int frameDrawWidth, int frameDrawHeight, int entitySize, float alpha) {
        class_742 previewPlayer = this.getPreviewPlayer(this.selectedOption);
        if (previewPlayer == null || alpha <= 0.0f) {
            return;
        }
        context.method_51448().method_22903();
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        int frameX = x + (78 - frameDrawWidth) / 2;
        int frameY = y + 8;
        int entityInset = Math.max(4, frameDrawWidth / 8);
        float scaleMultiplier = this.previewScaleMultiplier();
        int baseTop = frameY + entityInset;
        int baseBottom = frameY + frameDrawHeight - entityInset;
        int baseHeight = Math.max(1, baseBottom - baseTop);
        int anchoredTop = baseBottom - Math.max(1, Math.round((float)baseHeight * scaleMultiplier));
        class_490.method_2486((class_332)context, (int)(frameX + entityInset), (int)anchoredTop, (int)(frameX + frameDrawWidth - entityInset), (int)baseBottom, (int)Math.max(1, Math.round((float)entitySize * this.previewScaleMultiplier())), (float)0.0f, (float)mouseX, (float)mouseY, (class_1309)previewPlayer);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        context.method_51448().method_22909();
    }

    private void renderDescriptionPanel(class_332 context, int panelX, int panelY, int panelWidth, int panelHeight, float alpha) {
        if (this.selectedOption == null || this.selectedReligion == null || alpha <= 0.0f) {
            return;
        }
        int descriptionX = panelX + panelWidth - 100 - 44;
        int descriptionY = CharacterSelectionScreen.reviewDescriptionTop(panelY, panelHeight);
        CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, descriptionX, descriptionY, 160, 128, 96, 128, 100, 132, alpha);
        this.renderReviewStatLines(context, descriptionX, descriptionY, alpha);
    }

    private void renderReviewStatLines(class_332 context, int descriptionX, int descriptionY, float alpha) {
        EnumMap<SkillAttributeType, Integer> startingPoints = this.resolvedStartingPoints();
        EnumMap<SkillAttributeType, Integer> maxCapBonuses = this.resolvedMaxCapBonuses();
        int maxLabelWidth = SkillAttributeType.ALL.stream().mapToInt(type -> this.field_22793.method_1727(type.getDisplayName() + ":")).max().orElse(0);
        int textColor = CharacterSelectionScreen.withAlpha(0xFFFFFF, alpha);
        float labelX = descriptionX + 6;
        float valueX = labelX + (float)(13 + maxLabelWidth + 12) * 0.665f;
        float startY = descriptionY + 4;
        for (int index = 0; index < SkillAttributeType.ALL.size(); ++index) {
            SkillAttributeType type2 = SkillAttributeType.ALL.get(index);
            int amount = Math.max(0, startingPoints.getOrDefault((Object)type2, 0));
            int maxBonus = maxCapBonuses.getOrDefault((Object)type2, 0);
            int maxPoints = 100 + maxBonus;
            float lineY = startY + (float)(index * 12);
            this.drawScaledIcon(context, type2.getIconTexture(), labelX, lineY, 10, 16, alpha, 0.665f);
            this.drawScaledText(context, (class_2561)class_2561.method_43470((String)(type2.getDisplayName() + ":")), labelX + 10.0f + 3.0f, lineY, textColor, 0.665f);
            class_5250 starterText = class_2561.method_43470((String)(amount + "/"));
            this.drawScaledText(context, (class_2561)starterText, valueX, lineY, textColor, 0.665f);
            float maxValueX = valueX + (float)this.field_22793.method_27525((class_5348)starterText) * 0.665f;
            int maxTextColor = CharacterSelectionScreen.withAlpha(maxBonus > 0 ? 10940556 : (maxBonus < 0 ? 0xFFAAAA : 0xFFFFFF), alpha);
            this.drawScaledText(context, (class_2561)class_2561.method_43470((String)String.valueOf(maxPoints)), maxValueX, lineY, maxTextColor, 0.665f);
        }
        CharacterSelectionOption kingdomOption = CharacterOptionLoader.loadForKingdom(this.selectedOption.kingdomId()).stream().filter(CharacterSelectionOption::primary).findFirst().orElse(this.selectedKingdomOption != null ? this.selectedKingdomOption : this.selectedOption);
        float houseLineY = startY + (float)(SkillAttributeType.ALL.size() * 12);
        this.drawScaledIcon(context, this.selectedOption.texture(), labelX, houseLineY, 10, 48, 62, alpha, 0.665f);
        this.drawScaledText(context, (class_2561)class_2561.method_43470((String)CharacterSelectionScreen.getHouseLabel(this.selectedOption)), labelX + 10.0f + 3.0f, houseLineY, textColor, 0.665f);
        float kingdomLineY = houseLineY + 12.0f;
        this.drawScaledIcon(context, kingdomOption.texture(), labelX, kingdomLineY, 10, 48, 62, alpha, 0.665f);
        this.drawScaledText(context, (class_2561)class_2561.method_43470((String)CharacterSelectionScreen.getKingdomLabel(kingdomOption)), labelX + 10.0f + 3.0f, kingdomLineY, textColor, 0.665f);
        float religionLineY = kingdomLineY + 12.0f;
        this.drawScaledIcon(context, this.selectedReligion.texture(), labelX, religionLineY, 10, 48, alpha, 0.665f);
        this.drawScaledText(context, (class_2561)class_2561.method_43470((String)this.selectedReligion.displayName()), labelX + 10.0f + 3.0f, religionLineY, textColor, 0.665f);
    }

    private void renderKingdomBackButton(class_332 context, int mouseX, int mouseY, int panelX, int panelY, int panelWidth, int panelHeight, float alpha) {
        int buttonY;
        int buttonX;
        if (this.selectedReligion == null || this.selectedOption != null || alpha <= 0.0f) {
            return;
        }
        boolean hovered = CharacterSelectionScreen.isWithin(mouseX, mouseY, buttonX = 8, buttonY = this.field_22790 - 16 - 8, 64, 16);
        CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, buttonX, buttonY, 16, hovered ? 144 : 160, 64, 16, 64, 16, alpha);
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Back"), buttonX + 32, buttonY + 4, CharacterSelectionScreen.withAlpha(hovered ? 15782746 : 0xFFFFFF, alpha));
    }

    private void renderFooterButtons(class_332 context, int mouseX, int mouseY, int panelX, int panelY, int panelWidth, int panelHeight, float alpha) {
        if (this.selectedOption == null || alpha <= 0.0f) {
            return;
        }
        int gap = 16;
        int totalWidth = 128 + gap;
        int startX = panelX + (panelWidth - totalWidth) / 2;
        int y = CharacterSelectionScreen.reviewFooterButtonY(panelY, panelHeight);
        boolean backHovered = CharacterSelectionScreen.isWithin(mouseX, mouseY, startX, y, 64, 16);
        boolean confirmHovered = CharacterSelectionScreen.isWithin(mouseX, mouseY, startX + 64 + gap, y, 64, 16);
        CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, startX, y, 16, backHovered ? 144 : 160, 64, 16, 64, 16, alpha);
        CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, startX + 64 + gap, y, 16, confirmHovered ? 144 : 160, 64, 16, 64, 16, alpha);
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Back"), startX + 32, y + 4, CharacterSelectionScreen.withAlpha(backHovered ? 15782746 : 0xFFFFFF, alpha));
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Confirm"), startX + 64 + gap + 32, y + 4, CharacterSelectionScreen.withAlpha(confirmHovered ? 15782746 : 0xFFFFFF, alpha));
    }

    private void renderPageControls(class_332 context, int mouseX, int mouseY, int panelY, int panelHeight, float religionAlpha, float browseAlpha) {
        boolean hovered;
        int arrowSize = Math.round(28.0f);
        int y = panelY + panelHeight / 2 - arrowSize / 2;
        int leftX = 8;
        int rightX = this.field_22789 - arrowSize - 8;
        if (religionAlpha > 0.0f && this.religionPageCount() > 1) {
            boolean hovered2;
            if (this.religionPage > 0) {
                hovered2 = CharacterSelectionScreen.isWithin(mouseX, mouseY, leftX, y, arrowSize, arrowSize);
                CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, leftX, y, 16, hovered2 ? 176 : 192, 16, 16, arrowSize, arrowSize, religionAlpha);
            }
            if (this.religionPage < this.religionPageCount() - 1) {
                hovered2 = CharacterSelectionScreen.isWithin(mouseX, mouseY, rightX, y, arrowSize, arrowSize);
                CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, rightX, y, 32, hovered2 ? 176 : 192, 16, 16, arrowSize, arrowSize, religionAlpha);
            }
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)("Page " + (this.religionPage + 1) + " / " + this.religionPageCount())), this.field_22789 / 2, panelY + panelHeight + 12, CharacterSelectionScreen.withAlpha(13682357, religionAlpha));
            return;
        }
        if (this.pageCount() <= 1 || browseAlpha <= 0.0f) {
            return;
        }
        if (this.currentPage > 0) {
            hovered = CharacterSelectionScreen.isWithin(mouseX, mouseY, leftX, y, arrowSize, arrowSize);
            CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, leftX, y, 16, hovered ? 176 : 192, 16, 16, arrowSize, arrowSize, browseAlpha);
        }
        if (this.currentPage < this.pageCount() - 1) {
            hovered = CharacterSelectionScreen.isWithin(mouseX, mouseY, rightX, y, arrowSize, arrowSize);
            CharacterSelectionScreen.drawScaledTexture(context, UI_TEXTURE, rightX, y, 32, hovered ? 176 : 192, 16, 16, arrowSize, arrowSize, browseAlpha);
        }
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)("Page " + (this.currentPage + 1) + " / " + this.pageCount())), this.field_22789 / 2, panelY + panelHeight + 12, CharacterSelectionScreen.withAlpha(13682357, browseAlpha));
    }

    private boolean handleReligionButtons(double mouseX, double mouseY, int panelX, int panelY, int panelWidth, int panelHeight) {
        int totalRowWidth = 336;
        int startX = panelX + (panelWidth - totalRowWidth) / 2;
        int startY = panelY + (panelHeight - 122) / 2;
        List<ReligionSelectionOption> pageOptions = this.religionPageOptions();
        for (int index = 0; index < pageOptions.size(); ++index) {
            int x = startX + index * 86;
            int buttonX = x + 19;
            int entryY = this.religionCardY(startY, index);
            int labelY = entryY + 18 + 52 + 8;
            int buttonY = labelY + 12;
            if (!CharacterSelectionScreen.isWithin(mouseX, mouseY, buttonX, buttonY, 40, 13)) continue;
            this.playButtonClickSound();
            this.selectedReligion = pageOptions.get(index);
            return true;
        }
        return false;
    }

    private boolean handlePlayerBuilderClick(double mouseX, double mouseY, int panelX, int panelY, int panelWidth, int panelHeight) {
        List<PlayerBuilderRow> rows = this.playerBuilderRows();
        int rowX = CharacterSelectionScreen.playerBuilderRowsX(panelX, panelWidth);
        int rowY = CharacterSelectionScreen.playerBuilderRowsY(panelY, panelHeight, rows.size());
        for (int index = 0; index < rows.size(); ++index) {
            if (!rows.get(index).active()) continue;
            int y = rowY + index * 12 + 3;
            int leftX = rowX + 78 + 7;
            int buttonX = leftX + 8 + 3;
            int rightX = buttonX + 38 + 3;
            if (CharacterSelectionScreen.isWithin(mouseX, mouseY, leftX, y, 8, 8)) {
                this.playButtonClickSound();
                this.cyclePlayerBuilderRow(rows.get(index), -1);
                return true;
            }
            if (!CharacterSelectionScreen.isWithin(mouseX, mouseY, rightX, y, 8, 8) && !CharacterSelectionScreen.isWithin(mouseX, mouseY, buttonX, y, 38, 7)) continue;
            this.playButtonClickSound();
            this.cyclePlayerBuilderRow(rows.get(index), 1);
            return true;
        }
        int buttonX = panelX + (panelWidth - 64) / 2;
        int buttonY = panelY + panelHeight - 16 - 8;
        if (CharacterSelectionScreen.isWithin(mouseX, mouseY, buttonX, buttonY, 64, 16)) {
            this.playButtonClickSound();
            this.skinBuilderProfile = this.ensureSkinBuilderDefaults(this.skinBuilderProfile);
            CharacterSelectionClientState.setSkinBuilderProfile(this.skinBuilderProfile);
            this.playerBuilderComplete = true;
            return true;
        }
        return false;
    }

    private List<PlayerBuilderRow> playerBuilderRows() {
        boolean npcBase = this.skinBuilderProfile.npcBaseSkin();
        ArrayList<PlayerBuilderRow> rows = new ArrayList<PlayerBuilderRow>();
        rows.add(new PlayerBuilderRow(PlayerBuilderPart.BASE_SKIN, "Base Skin", npcBase ? "NPC" : "Player", true));
        rows.add(new PlayerBuilderRow(PlayerBuilderPart.GENDER, "Gender", npcBase ? this.skinBuilderProfile.gender() : "NPC Only", npcBase));
        rows.add(new PlayerBuilderRow(PlayerBuilderPart.SKIN_TONE, "Skin Tone", npcBase ? CharacterSelectionScreen.optionLabel(PlayerSkinBuilderOptions.skinTones(), CharacterSelectionScreen.skinToneId(this.skinBuilderProfile.skinTone())) : "NPC Only", npcBase));
        rows.add(new PlayerBuilderRow(PlayerBuilderPart.FACE, "Face", npcBase ? CharacterSelectionScreen.optionLabel(PlayerSkinBuilderOptions.faces(this.skinBuilderProfile.gender()), this.skinBuilderProfile.faceId()) : "NPC Only", npcBase));
        rows.add(new PlayerBuilderRow(PlayerBuilderPart.HAIR, "Hair", CharacterSelectionScreen.optionLabel(PlayerSkinBuilderOptions.hair(this.skinBuilderProfile.gender(), true), this.skinBuilderProfile.hairId()), true));
        rows.add(new PlayerBuilderRow(PlayerBuilderPart.SHIRT, "Shirt", CharacterSelectionScreen.optionLabel(PlayerSkinBuilderOptions.shirts(!npcBase), this.skinBuilderProfile.shirtId()), true));
        rows.add(new PlayerBuilderRow(PlayerBuilderPart.PANTS, "Pants", CharacterSelectionScreen.optionLabel(PlayerSkinBuilderOptions.pants(!npcBase), this.skinBuilderProfile.pantsId()), true));
        return rows;
    }

    private void cyclePlayerBuilderRow(PlayerBuilderRow row, int delta) {
        switch (row.part().ordinal()) {
            case 0: {
                this.skinBuilderProfile = this.ensureSkinBuilderDefaults(this.skinBuilderProfile.withNpcBaseSkin(!this.skinBuilderProfile.npcBaseSkin()));
                break;
            }
            case 1: {
                this.skinBuilderProfile = this.ensureSkinBuilderDefaults(this.skinBuilderProfile.withGender(this.skinBuilderProfile.isMale() ? "Female" : "Male"));
                break;
            }
            case 2: {
                String nextId = CharacterSelectionScreen.cycledId(PlayerSkinBuilderOptions.skinTones(), CharacterSelectionScreen.skinToneId(this.skinBuilderProfile.skinTone()), delta);
                this.skinBuilderProfile = this.ensureSkinBuilderDefaults(this.skinBuilderProfile.withSkinTone(CharacterSelectionScreen.skinToneNumber(nextId, this.skinBuilderProfile.skinTone())));
                break;
            }
            case 3: {
                this.skinBuilderProfile = this.ensureSkinBuilderDefaults(this.skinBuilderProfile.withFaceId(CharacterSelectionScreen.cycledId(PlayerSkinBuilderOptions.faces(this.skinBuilderProfile.gender()), this.skinBuilderProfile.faceId(), delta)));
                break;
            }
            case 4: {
                this.skinBuilderProfile = this.ensureSkinBuilderDefaults(this.skinBuilderProfile.withHairId(CharacterSelectionScreen.cycledId(PlayerSkinBuilderOptions.hair(this.skinBuilderProfile.gender(), true), this.skinBuilderProfile.hairId(), delta)));
                break;
            }
            case 5: {
                this.skinBuilderProfile = this.ensureSkinBuilderDefaults(this.skinBuilderProfile.withShirtId(CharacterSelectionScreen.cycledId(PlayerSkinBuilderOptions.shirts(!this.skinBuilderProfile.npcBaseSkin()), this.skinBuilderProfile.shirtId(), delta)));
                break;
            }
            case 6: {
                this.skinBuilderProfile = this.ensureSkinBuilderDefaults(this.skinBuilderProfile.withPantsId(CharacterSelectionScreen.cycledId(PlayerSkinBuilderOptions.pants(!this.skinBuilderProfile.npcBaseSkin()), this.skinBuilderProfile.pantsId(), delta)));
            }
        }
        CharacterSelectionClientState.setSkinBuilderProfile(this.skinBuilderProfile);
    }

    private PlayerSkinBuilderProfile ensureSkinBuilderDefaults(PlayerSkinBuilderProfile profile) {
        List<PlayerSkinBuilderOptions.LayerOption> hair;
        PlayerSkinBuilderProfile resolved;
        PlayerSkinBuilderProfile playerSkinBuilderProfile = resolved = profile == null ? PlayerSkinBuilderProfile.DEFAULT : profile;
        if (resolved.npcBaseSkin()) {
            List<PlayerSkinBuilderOptions.LayerOption> pants;
            List<PlayerSkinBuilderOptions.LayerOption> shirts;
            List<PlayerSkinBuilderOptions.LayerOption> faces;
            List<PlayerSkinBuilderOptions.LayerOption> skinTones = PlayerSkinBuilderOptions.skinTones();
            if (!skinTones.isEmpty() && !CharacterSelectionScreen.containsOption(skinTones, CharacterSelectionScreen.skinToneId(resolved.skinTone()))) {
                resolved = resolved.withSkinTone(CharacterSelectionScreen.skinToneNumber(skinTones.get(0).id(), 1));
            }
            if (!(faces = PlayerSkinBuilderOptions.faces(resolved.gender())).isEmpty() && !CharacterSelectionScreen.containsOption(faces, resolved.faceId())) {
                resolved = resolved.withFaceId(faces.get(0).id());
            }
            if (!(shirts = PlayerSkinBuilderOptions.shirts(false)).isEmpty() && !CharacterSelectionScreen.containsOption(shirts, resolved.shirtId())) {
                resolved = resolved.withShirtId(shirts.get(0).id());
            }
            if (!(pants = PlayerSkinBuilderOptions.pants(false)).isEmpty() && !CharacterSelectionScreen.containsOption(pants, resolved.pantsId())) {
                resolved = resolved.withPantsId(pants.get(0).id());
            }
        }
        if (!CharacterSelectionScreen.containsOption(hair = PlayerSkinBuilderOptions.hair(resolved.gender(), true), resolved.hairId())) {
            resolved = resolved.withHairId("");
        }
        return resolved;
    }

    private static boolean containsOption(List<PlayerSkinBuilderOptions.LayerOption> options, String id) {
        return options.stream().anyMatch(option -> option.id().equals(id == null ? "" : id));
    }

    private static String optionLabel(List<PlayerSkinBuilderOptions.LayerOption> options, String id) {
        String safeId = id == null ? "" : id;
        return options.stream().filter(option -> option.id().equals(safeId)).map(PlayerSkinBuilderOptions.LayerOption::label).findFirst().orElse(safeId.isBlank() ? "None" : safeId);
    }

    private static String cycledId(List<PlayerSkinBuilderOptions.LayerOption> options, String selectedId, int delta) {
        if (options.isEmpty()) {
            return "";
        }
        String safeId = selectedId == null ? "" : selectedId;
        int index = 0;
        for (int optionIndex = 0; optionIndex < options.size(); ++optionIndex) {
            if (!options.get(optionIndex).id().equals(safeId)) continue;
            index = optionIndex;
            break;
        }
        return options.get(Math.floorMod(index + delta, options.size())).id();
    }

    private static String skinToneId(int skinTone) {
        return "skin_" + Math.max(1, skinTone);
    }

    private static int skinToneNumber(String id, int fallback) {
        if (id == null) {
            return fallback;
        }
        int index = id.lastIndexOf(95);
        if (index < 0 || index >= id.length() - 1) {
            return fallback;
        }
        try {
            return Math.max(1, Integer.parseInt(id.substring(index + 1)));
        }
        catch (NumberFormatException e) {
            return fallback;
        }
    }

    private boolean handleRegionButtons(double mouseX, double mouseY, int panelX, int panelY, int panelWidth, int panelHeight) {
        RegionTab[] regions = RegionTab.values();
        int cardWidth = 96;
        int cardHeight = 52;
        int gap = 24;
        int totalRowWidth = cardWidth * regions.length + gap * (regions.length - 1);
        int startX = panelX + (panelWidth - totalRowWidth) / 2;
        int startY = panelY + (panelHeight - cardHeight) / 2;
        for (int index = 0; index < regions.length; ++index) {
            int x = startX + index * (cardWidth + gap);
            int buttonX = x + (cardWidth - 40) / 2;
            int buttonY = startY + 28;
            if (!CharacterSelectionScreen.isWithin(mouseX, mouseY, buttonX, buttonY, 40, 13)) continue;
            this.playButtonClickSound();
            this.selectedRegion = regions[index];
            this.currentPage = 0;
            return true;
        }
        return false;
    }

    private int religionCardY(int startY, int index) {
        return startY + (index % 2 == 0 ? 0 : 0);
    }

    private boolean handleBrowseBackButton(double mouseX, double mouseY, int panelX, int panelY, int panelWidth, int panelHeight) {
        if (this.selectedReligion == null || this.selectedOption != null) {
            return false;
        }
        int buttonX = 8;
        int buttonY = this.field_22790 - 16 - 8;
        if (CharacterSelectionScreen.isWithin(mouseX, mouseY, buttonX, buttonY, 64, 16)) {
            this.playButtonClickSound();
            if (this.selectedKingdomOption != null) {
                this.selectedKingdomOption = null;
            } else if (this.selectedRegion != null) {
                this.selectedRegion = null;
            } else {
                this.selectedReligion = null;
            }
            this.currentPage = 0;
            return true;
        }
        return false;
    }

    private boolean handleChooseButtons(double mouseX, double mouseY, int panelX, int panelY, int panelWidth, int panelHeight) {
        List<CharacterSelectionOption> pageOptions = this.pageOptions();
        int totalRowWidth = 78 * pageOptions.size() + 8 * Math.max(0, pageOptions.size() - 1);
        int startX = panelX + (panelWidth - totalRowWidth) / 2;
        int startY = panelY + (panelHeight - 122) / 2 + 14;
        for (int index = 0; index < pageOptions.size(); ++index) {
            int x = startX + index * 86;
            int buttonX = x + 19;
            int labelY = startY + 8 + 92 + 6;
            int buttonY = labelY + 8;
            if (!CharacterSelectionScreen.isWithin(mouseX, mouseY, buttonX, buttonY, 40, 13)) continue;
            this.playButtonClickSound();
            if (this.selectedKingdomOption == null) {
                this.selectedKingdomOption = pageOptions.get(index);
                this.currentPage = 0;
            } else {
                this.selectedOption = pageOptions.get(index);
                this.stepTransition.playForward();
            }
            return true;
        }
        return false;
    }

    private boolean handleFooterButtons(double mouseX, double mouseY, int panelX, int panelY, int panelWidth, int panelHeight) {
        if (this.selectedReligion == null || this.selectedOption == null || this.stepTransition.eased() < 0.98f || this.stepTransition.isRunning()) {
            return false;
        }
        int gap = 16;
        int totalWidth = 128 + gap;
        int startX = panelX + (panelWidth - totalWidth) / 2;
        int y = CharacterSelectionScreen.reviewFooterButtonY(panelY, panelHeight);
        if (CharacterSelectionScreen.isWithin(mouseX, mouseY, startX, y, 64, 16)) {
            this.playButtonClickSound();
            this.stepTransition.playBackward();
            return true;
        }
        if (CharacterSelectionScreen.isWithin(mouseX, mouseY, startX + 64 + gap, y, 64, 16)) {
            this.playButtonClickSound();
            this.submitting = true;
            CharacterSelectionClientState.setSpawnSearchInProgress(true, this.field_22787);
            ClientPlayNetworking.send((class_8710)new SelectCharacterPayload(this.selectedReligion.id(), this.selectedOption.kingdomId().toString(), this.selectedOption.id(), this.selectedGeneticTrait == null ? "" : this.selectedGeneticTrait.id(), (String[])this.selectedPersonalityTraits.stream().map(PlayerPersonalityTrait::id).toArray(String[]::new), this.ensureSkinBuilderDefaults(this.skinBuilderProfile)));
            return true;
        }
        return false;
    }

    private boolean handlePageButtons(double mouseX, double mouseY, int panelY, int panelHeight) {
        if (this.pageCount() <= 1) {
            return false;
        }
        int arrowSize = Math.round(28.0f);
        int y = panelY + panelHeight / 2 - arrowSize / 2;
        int leftX = 8;
        int rightX = this.field_22789 - arrowSize - 8;
        if (this.currentPage > 0 && CharacterSelectionScreen.isWithin(mouseX, mouseY, leftX, y, arrowSize, arrowSize)) {
            this.playButtonClickSound();
            --this.currentPage;
            return true;
        }
        if (this.currentPage < this.pageCount() - 1 && CharacterSelectionScreen.isWithin(mouseX, mouseY, rightX, y, arrowSize, arrowSize)) {
            this.playButtonClickSound();
            ++this.currentPage;
            return true;
        }
        return false;
    }

    private boolean handleReligionPageButtons(double mouseX, double mouseY, int panelY, int panelHeight) {
        if (this.religionPageCount() <= 1) {
            return false;
        }
        int arrowSize = Math.round(28.0f);
        int y = panelY + panelHeight / 2 - arrowSize / 2;
        int leftX = 8;
        int rightX = this.field_22789 - arrowSize - 8;
        if (this.religionPage > 0 && CharacterSelectionScreen.isWithin(mouseX, mouseY, leftX, y, arrowSize, arrowSize)) {
            this.playButtonClickSound();
            --this.religionPage;
            return true;
        }
        if (this.religionPage < this.religionPageCount() - 1 && CharacterSelectionScreen.isWithin(mouseX, mouseY, rightX, y, arrowSize, arrowSize)) {
            this.playButtonClickSound();
            ++this.religionPage;
            return true;
        }
        return false;
    }

    private static int reviewFooterButtonY(int panelY, int panelHeight) {
        return panelY + panelHeight - 16 - 8 + 16;
    }

    private static int reviewDescriptionTop(int panelY, int panelHeight) {
        return panelY + panelHeight / 2 - 42 + 6 - 16;
    }

    private void renderPatternedBackground(class_332 context) {
        for (int x = 0; x < this.field_22789; x += 24) {
            for (int y = 0; y < this.field_22790; y += 24) {
                int pattern = Math.floorMod(x / 24 * 7 + y / 24 * 11 + (x / 24 ^ y / 24), BACKGROUND_TILES.size());
                context.method_25291(BACKGROUND_TILES.get(pattern), x, y, 0, 0.0f, 0.0f, 24, 24, 16, 16);
                context.method_25294(x, y, x + 24, y + 24, 0x44000000);
            }
        }
        context.method_25294(0, 0, this.field_22789, this.field_22790, -2013265920);
    }

    private List<CharacterSelectionOption> pageOptions() {
        int start = this.currentPage * 4;
        List<CharacterSelectionOption> browseOptions = this.browseOptions();
        int end = Math.min(start + 4, browseOptions.size());
        if (start >= end) {
            return List.of();
        }
        return browseOptions.subList(start, end);
    }

    private List<CharacterSelectionOption> browseOptions() {
        if (this.selectedKingdomOption == null) {
            if (this.selectedRegion == null) {
                return List.of();
            }
            return this.options.stream().filter(option -> this.selectedRegion.contains(option.kingdomId().toString())).toList();
        }
        return CharacterOptionLoader.loadForKingdom(this.selectedKingdomOption.kingdomId());
    }

    private List<ReligionSelectionOption> religionPageOptions() {
        int start = this.religionPage * 4;
        int end = Math.min(start + 4, this.religionOptions.size());
        if (start >= end) {
            return List.of();
        }
        return this.religionOptions.subList(start, end);
    }

    private int religionPageCount() {
        return Math.max(1, class_3532.method_15386((float)((float)this.religionOptions.size() / 4.0f)));
    }

    private class_742 getPreviewPlayer(CharacterSelectionOption option) {
        if (option == null) {
            return class_310.method_1551().field_1724;
        }
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null || client.field_1687 == null) {
            return client.field_1724;
        }
        CharacterPreviewPlayerEntity previewPlayer = CharacterPreviewSkinResolver.createPreviewEntity(client.field_1687, option, this.selectedGeneticTrait);
        return previewPlayer == null ? client.field_1724 : previewPlayer;
    }

    private class_742 getPlayerBuilderPreview() {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null || client.field_1687 == null) {
            return client.field_1724;
        }
        CharacterPreviewPlayerEntity preview = PlayerSkinBuilderSkinResolver.createPreviewEntity(client.field_1687, this.skinBuilderProfile);
        return preview == null ? client.field_1724 : preview;
    }

    private TraitTooltipData renderTraitPanel(class_332 context, int mouseX, int mouseY, int panelX, int panelY, int panelWidth, int panelHeight, float alpha) {
        if (this.selectedOption == null || alpha <= 0.0f) {
            return null;
        }
        int startX = panelX + panelWidth / 2 - 48;
        int startY = CharacterSelectionScreen.reviewDescriptionTop(panelY, panelHeight);
        int textColor = CharacterSelectionScreen.withAlpha(0xFFFFFF, alpha);
        TraitTooltipData hoveredTooltip = null;
        this.drawScaledText(context, (class_2561)class_2561.method_43470((String)"Genetic Traits:"), startX, startY, textColor, 0.625f);
        int geneticY = startY + CharacterSelectionScreen.scaledTrait(14);
        for (PlayerGeneticTrait trait : PlayerGeneticTrait.ALL) {
            boolean selected;
            boolean bl = selected = this.selectedGeneticTrait == trait;
            if (this.drawTraitButton(context, mouseX, mouseY, trait.displayName(), startX + CharacterSelectionScreen.scaledTrait(16), geneticY, 64, selected, alpha)) {
                hoveredTooltip = this.createTooltip(trait);
            }
            geneticY += CharacterSelectionScreen.scaledTrait(20);
        }
        int personalityHeaderY = geneticY + CharacterSelectionScreen.scaledTrait(14);
        this.drawScaledText(context, (class_2561)class_2561.method_43470((String)"Personality Traits:"), startX, personalityHeaderY, textColor, 0.625f);
        int personalityY = personalityHeaderY + CharacterSelectionScreen.scaledTrait(14);
        for (PlayerPersonalityTrait trait : PlayerPersonalityTrait.ALL) {
            boolean selected = this.selectedPersonalityTraits.contains((Object)trait);
            if (this.drawTraitButton(context, mouseX, mouseY, trait.displayName(), startX, personalityY, 96, selected, alpha)) {
                hoveredTooltip = this.createTooltip(trait);
            }
            personalityY += CharacterSelectionScreen.scaledTrait(20);
        }
        return hoveredTooltip;
    }

    private boolean drawTraitButton(class_332 context, int mouseX, int mouseY, String label, int x, int y, int width, boolean selected, float alpha) {
        int drawWidth = CharacterSelectionScreen.scaledTrait(width);
        int drawHeight = CharacterSelectionScreen.scaledTrait(16);
        class_2960 texture = width > 64 ? DIALOGUE_OPTION_WIDE_TEXTURE : DIALOGUE_OPTION_TEXTURE;
        context.method_51448().method_22903();
        context.method_51448().method_46416(0.0f, 0.0f, 250.0f);
        CharacterSelectionScreen.drawStandaloneTexture(context, texture, x, y, drawWidth, drawHeight, width, 16, alpha);
        if (selected) {
            context.method_25294(x + 1, y + 1, x + drawWidth - 1, y + drawHeight - 1, CharacterSelectionScreen.withAlpha(7295261, alpha * 0.66f));
        } else if (CharacterSelectionScreen.isWithin(mouseX, mouseY, x, y, drawWidth, drawHeight)) {
            context.method_25294(x + 1, y + 1, x + drawWidth - 1, y + drawHeight - 1, CharacterSelectionScreen.withAlpha(0x555555, alpha * 0.45f));
        }
        this.drawScaledCenteredText(context, (class_2561)class_2561.method_43470((String)label), (float)x + (float)drawWidth / 2.0f, (float)y + 2.0f, CharacterSelectionScreen.withAlpha(selected ? 15782746 : 0xFFFFFF, alpha), 0.625f);
        context.method_51448().method_22909();
        return CharacterSelectionScreen.isWithin(mouseX, mouseY, x, y, drawWidth, drawHeight);
    }

    private boolean handleTraitButtons(double mouseX, double mouseY, int panelX, int panelY, int panelWidth, int panelHeight) {
        if (this.selectedOption == null || this.stepTransition.eased() < 0.98f || this.stepTransition.isRunning()) {
            return false;
        }
        int startX = panelX + panelWidth / 2 - 48;
        int startY = CharacterSelectionScreen.reviewDescriptionTop(panelY, panelHeight);
        int geneticY = startY + CharacterSelectionScreen.scaledTrait(14);
        for (PlayerGeneticTrait trait : PlayerGeneticTrait.ALL) {
            int x = startX + CharacterSelectionScreen.scaledTrait(16);
            if (CharacterSelectionScreen.isWithin(mouseX, mouseY, x, geneticY, CharacterSelectionScreen.scaledTrait(64), CharacterSelectionScreen.scaledTrait(16))) {
                this.playButtonClickSound();
                this.selectedGeneticTrait = this.selectedGeneticTrait == trait ? null : trait;
                return true;
            }
            geneticY += CharacterSelectionScreen.scaledTrait(20);
        }
        int personalityY = geneticY + CharacterSelectionScreen.scaledTrait(28);
        for (PlayerPersonalityTrait trait : PlayerPersonalityTrait.ALL) {
            if (CharacterSelectionScreen.isWithin(mouseX, mouseY, startX, personalityY, CharacterSelectionScreen.scaledTrait(96), CharacterSelectionScreen.scaledTrait(16))) {
                this.playButtonClickSound();
                if (this.selectedPersonalityTraits.contains((Object)trait)) {
                    this.selectedPersonalityTraits.remove((Object)trait);
                } else if (this.selectedPersonalityTraits.size() < 2) {
                    this.selectedPersonalityTraits.add(trait);
                }
                return true;
            }
            personalityY += CharacterSelectionScreen.scaledTrait(20);
        }
        return false;
    }

    private EnumMap<SkillAttributeType, Integer> resolvedStartingPoints() {
        return PlayerTraitEffects.mergeStartingPoints(this.selectedOption.startingSkillPoints(), this.selectedGeneticTrait, this.selectedPersonalityTraits);
    }

    private EnumMap<SkillAttributeType, Integer> resolvedMaxCapBonuses() {
        return PlayerTraitEffects.mergeMaxCapBonuses(this.selectedOption.maxSkillCapBonuses(), this.selectedGeneticTrait);
    }

    private TraitTooltipData createTooltip(PlayerGeneticTrait trait) {
        ArrayList<TraitTooltipLine> lines = new ArrayList<TraitTooltipLine>();
        lines.add(new TraitTooltipLine(null, trait.scaleMultiplier() < 1.0f ? String.format(Locale.ROOT, "Sized down %.2fx", Float.valueOf(trait.scaleMultiplier())) : String.format(Locale.ROOT, "Sized up %.2fx", Float.valueOf(trait.scaleMultiplier())), -1));
        this.appendSkillModifierLines(lines, trait.maxCapModifiers(), true);
        this.appendSkillModifierLines(lines, trait.startingPointModifiers(), false);
        return new TraitTooltipData(trait.displayName(), lines);
    }

    private TraitTooltipData createTooltip(PlayerPersonalityTrait trait) {
        ArrayList<TraitTooltipLine> lines = new ArrayList<TraitTooltipLine>();
        this.appendSkillModifierLines(lines, trait.startingPointModifiers(), false);
        return new TraitTooltipData(trait.displayName(), lines);
    }

    private void appendSkillModifierLines(List<TraitTooltipLine> lines, Map<SkillAttributeType, Integer> modifiers, boolean maxCap) {
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            int amount = modifiers.getOrDefault((Object)type, 0);
            if (amount == 0) continue;
            String text = (amount > 0 ? "+" : "") + amount + " " + type.getDisplayName() + (maxCap ? " Cap" : "");
            lines.add(new TraitTooltipLine(type.getIconTexture(), text, amount > 0 ? 10940556 : 0xFFAAAA));
        }
    }

    private void drawTraitTooltip(class_332 context, int mouseX, int mouseY, TraitTooltipData tooltip) {
        int textWidth = this.field_22793.method_1727(tooltip.title());
        for (TraitTooltipLine line : tooltip.lines()) {
            int iconWidth = line.iconTexture() == null ? 0 : 13;
            textWidth = Math.max(textWidth, this.field_22793.method_1727(line.text()) + iconWidth);
        }
        int width = textWidth + 12;
        int height = 14 + tooltip.lines().size() * 12 + 6;
        int x = Math.min(mouseX + 12, this.field_22789 - width - 8);
        int y = Math.min(mouseY + 12, this.field_22790 - height - 8);
        context.method_51448().method_22903();
        context.method_51448().method_46416(0.0f, 0.0f, 500.0f);
        context.method_25294(x, y, x + width, y + height, -267386864);
        context.method_49601(x, y, width, height, -11513776);
        context.method_25303(this.field_22793, tooltip.title(), x + 6, y + 5, -1);
        int lineY = y + 18;
        for (TraitTooltipLine line : tooltip.lines()) {
            if (line.iconTexture() != null) {
                this.drawScaledIcon(context, line.iconTexture(), x + 6, lineY, 9, 16, 1.0f, 1.0f);
                context.method_25303(this.field_22793, line.text(), x + 6 + 9 + 4, lineY + 1, line.color());
            } else {
                context.method_25303(this.field_22793, line.text(), x + 6, lineY + 1, line.color());
            }
            lineY += 12;
        }
        context.method_51448().method_22909();
    }

    private float previewScaleMultiplier() {
        return this.selectedGeneticTrait == null ? 1.0f : this.selectedGeneticTrait.scaleMultiplier();
    }

    private static int scaledTrait(int sourcePixels) {
        return Math.max(1, Math.round((float)sourcePixels * 0.625f));
    }

    private static String getKingdomLabel(CharacterSelectionOption option) {
        String name = option.displayName();
        if (name.length() > 10 && name.startsWith("The ")) {
            return name.substring(4);
        }
        return name;
    }

    private static String getHouseLabel(CharacterSelectionOption option) {
        return option.houseDisplayName().isBlank() ? CharacterSelectionScreen.getKingdomLabel(option) : option.houseDisplayName();
    }

    private int pageCount() {
        return Math.max(1, class_3532.method_15386((float)((float)this.browseOptions().size() / 4.0f)));
    }

    private static boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    private static int withAlpha(int rgb, float alpha) {
        int alphaBits = class_3532.method_15340((int)((int)(alpha * 255.0f)), (int)0, (int)255) << 24;
        return alphaBits | rgb;
    }

    private void playButtonClickSound() {
        if (this.field_22787 == null) {
            return;
        }
        this.field_22787.method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, (float)1.0f));
    }

    private void drawScaledCenteredText(class_332 context, class_2561 text, float centerX, float y, int color, float scale) {
        context.method_51448().method_22903();
        context.method_51448().method_46416(centerX, y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        float textX = (float)(-this.field_22793.method_27525((class_5348)text)) / 2.0f;
        context.method_27535(this.field_22793, text, Math.round(textX), 0, color);
        context.method_51448().method_22909();
    }

    private void drawCenteredLabelWithIcon(class_332 context, class_2960 texture, int textureWidth, int textureHeight, int drawSize, int gap, class_2561 text, float centerX, float y, int color, float alpha) {
        int textWidth = this.field_22793.method_27525((class_5348)text);
        float totalWidth = drawSize + gap + textWidth;
        float startX = centerX - totalWidth / 2.0f;
        this.drawScaledIcon(context, texture, startX, y, drawSize, textureWidth, textureHeight, alpha, 1.0f);
        context.method_27535(this.field_22793, text, Math.round(startX + (float)drawSize + (float)gap), Math.round(y + 1.0f), color);
    }

    private void drawScaledText(class_332 context, class_2561 text, float x, float y, int color, float scale) {
        context.method_51448().method_22903();
        context.method_51448().method_46416(x, y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_27535(this.field_22793, text, 0, 0, color);
        context.method_51448().method_22909();
    }

    private void drawFittedText(class_332 context, class_2561 text, float x, float y, int color, int maxWidth, float maxScale) {
        int textWidth = Math.max(1, this.field_22793.method_27525((class_5348)text));
        float scale = Math.min(maxScale, (float)maxWidth / (float)textWidth);
        this.drawScaledText(context, text, x, y, color, scale);
    }

    private void drawFittedCenteredText(class_332 context, class_2561 text, float centerX, float y, int color, int maxWidth, float maxScale) {
        int textWidth = Math.max(1, this.field_22793.method_27525((class_5348)text));
        float scale = Math.min(maxScale, (float)maxWidth / (float)textWidth);
        context.method_51448().method_22903();
        context.method_51448().method_46416(centerX, y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_27535(this.field_22793, text, Math.round((float)(-textWidth) / 2.0f), 0, color);
        context.method_51448().method_22909();
    }

    private void drawScaledIcon(class_332 context, class_2960 texture, float x, float y, int drawSize, int textureWidth, int textureHeight, float alpha, float scale) {
        context.method_51448().method_22903();
        float fitScale = Math.min((float)drawSize / (float)textureWidth, (float)drawSize / (float)textureHeight);
        float iconScale = scale * fitScale;
        float offsetX = Math.max(0.0f, ((float)drawSize - (float)textureWidth * fitScale * scale) / 2.0f);
        float offsetY = Math.max(0.0f, ((float)drawSize - (float)textureHeight * fitScale * scale) / 2.0f);
        context.method_51448().method_46416(x + offsetX, y + offsetY, 0.0f);
        context.method_51448().method_22905(iconScale, iconScale, 1.0f);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, textureWidth, textureHeight, textureWidth, textureHeight);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        context.method_51448().method_22909();
    }

    private void drawScaledIcon(class_332 context, class_2960 texture, float x, float y, int drawSize, int textureSize, float alpha, float scale) {
        this.drawScaledIcon(context, texture, x, y, drawSize, textureSize, textureSize, alpha, scale);
    }

    private static void drawScaledTexture(class_332 context, class_2960 texture, int x, int y, int u, int v, int width, int height, int drawWidth, int drawHeight, float alpha) {
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        context.method_25293(texture, x, y, drawWidth, drawHeight, (float)u, (float)v, width, height, 256, 256);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private static void drawStandaloneTexture(class_332 context, class_2960 texture, int x, int y, int drawWidth, int drawHeight, int textureWidth, int textureHeight, float alpha) {
        float scaleX = (float)drawWidth / (float)textureWidth;
        float scaleY = (float)drawHeight / (float)textureHeight;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scaleX, scaleY, 1.0f);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, textureWidth, textureHeight, textureWidth, textureHeight);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        context.method_51448().method_22909();
    }

    @Environment(value=EnvType.CLIENT)
    private static enum RegionTab {
        WESTEROS("Westeros"),
        ESSOS("Essos"),
        THE_WALL("The Wall");

        private final String displayName;

        private RegionTab(String displayName) {
            this.displayName = displayName;
        }

        private String displayName() {
            return this.displayName;
        }

        private boolean contains(String kingdomId) {
            if (this == WESTEROS) {
                return GotKingdoms.isWesterosiKingdom(kingdomId) && !"agob:the_nights_watch".equals(kingdomId) && !"agob:wildling".equals(kingdomId);
            }
            if (this == ESSOS) {
                return GotKingdoms.isEssosKingdom(kingdomId);
            }
            return "agob:the_nights_watch".equals(kingdomId) || "agob:wildling".equals(kingdomId);
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record TraitTooltipData(String title, List<TraitTooltipLine> lines) {
    }

    @Environment(value=EnvType.CLIENT)
    private record PlayerBuilderRow(PlayerBuilderPart part, String label, String valueLabel, boolean active) {
    }

    @Environment(value=EnvType.CLIENT)
    private static enum PlayerBuilderPart {
        BASE_SKIN,
        GENDER,
        SKIN_TONE,
        FACE,
        HAIR,
        SHIRT,
        PANTS;

    }

    @Environment(value=EnvType.CLIENT)
    private record TraitTooltipLine(class_2960 iconTexture, String text, int color) {
    }
}

