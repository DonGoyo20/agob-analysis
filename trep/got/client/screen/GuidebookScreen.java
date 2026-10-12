/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_2583
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_364
 *  net.minecraft.class_437
 *  net.minecraft.class_474
 *  net.minecraft.class_5250
 *  net.minecraft.class_5348
 *  net.minecraft.class_5481
 */
package trep.got.client.screen;

import java.lang.runtime.SwitchBootstraps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_437;
import net.minecraft.class_474;
import net.minecraft.class_5250;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import trep.got.client.quest.QuestJournalClientState;
import trep.got.economy.GotEconomy;
import trep.got.kingdom.Kingdom;
import trep.got.network.QuestJournalStatePayload;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotCastles;
import trep.got.registry.GotItems;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotReligionOptions;
import trep.got.religion.ReligionSelectionOption;
import trep.got.skill.SkillAttributeType;

@Environment(value=EnvType.CLIENT)
public final class GuidebookScreen
extends class_437 {
    private static final class_2960 UI_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/guidebook_ui.png");
    private static final int UI_WIDTH = 271;
    private static final int UI_HEIGHT = 180;
    private static final int LEFT_PAGE_X = 15;
    private static final int RIGHT_PAGE_X = 143;
    private static final int PAGE_Y = 11;
    private static final int PAGE_WIDTH = 113;
    private static final int PAGE_HEIGHT = 151;
    private static final int PAGE_INNER_X = 4;
    private static final int PAGE_INNER_Y = 6;
    private static final int PAGE_TEXT_WIDTH = 105;
    private static final float TITLE_SCALE = 1.125f;
    private static final float BODY_TEXT_SCALE = 0.75f;
    private static final int QUEST_ENTRY_HEIGHT = 40;
    private static final int QUEST_ENTRY_GAP = 3;
    private static final int QUEST_ENTRY_TEXT_LEFT = 20;
    private static final int QUEST_ENTRY_TEXT_RIGHT_PADDING = 4;
    private static final int QUEST_ENTRY_INNER_TOP = 4;
    private static final int QUEST_ENTRY_LINE_GAP = 2;
    private static final int TURN_BUTTON_Y = 161;
    private static final float KINGDOM_ENTRY_SCALE = 0.66f;
    private static final int KINGDOM_CONTAINER_PADDING = 2;
    private static final int KINGDOM_ENTRY_HEIGHT = 20;
    private static final int KINGDOM_ICON_SIZE = 6;
    private static final int KINGDOM_LINE_GAP = 6;
    private static final int KINGDOM_ENTRY_TOP_PADDING = 2;
    private static final int KINGDOM_ROSTER_Y_OFFSET = -8;
    private static final int SIGIL_TEXTURE_WIDTH = 48;
    private static final int SIGIL_TEXTURE_HEIGHT = 62;
    private static final int SMALL_ICON_TEXTURE_SIZE = 16;
    private static final class_2960 CASTLE_STONE_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/castle_stone.png");
    private static final int BLACKSMITH_PREVIEW_WIDTH = 105;
    private static final int BLACKSMITH_PREVIEW_HEIGHT = 118;
    private static final float BLACKSMITH_TABLE_PREVIEW_SCALE = 2.8f;
    private static final float BLACKSMITH_FURNACE_PREVIEW_SCALE = 3.05f;
    private static final float BLACKSMITH_LABEL_SCALE = 0.75f;
    private static final List<class_2960> KINGDOM_GUIDE_ORDER = List.of(class_2960.method_60655((String)"agob", (String)"the_north"), class_2960.method_60655((String)"agob", (String)"the_vale"), class_2960.method_60655((String)"agob", (String)"the_riverlands"), class_2960.method_60655((String)"agob", (String)"the_westerlands"), class_2960.method_60655((String)"agob", (String)"iron_islands"), class_2960.method_60655((String)"agob", (String)"the_reach"), class_2960.method_60655((String)"agob", (String)"the_stormlands"), class_2960.method_60655((String)"agob", (String)"dorne"));
    private static final Map<class_2960, class_2960> KINGDOM_TEXTURES = Map.of(class_2960.method_60655((String)"agob", (String)"the_north"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_north.png"), class_2960.method_60655((String)"agob", (String)"the_vale"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_vale.png"), class_2960.method_60655((String)"agob", (String)"the_riverlands"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_riverlands.png"), class_2960.method_60655((String)"agob", (String)"the_westerlands"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_westerlands.png"), class_2960.method_60655((String)"agob", (String)"iron_islands"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_ironborn.png"), class_2960.method_60655((String)"agob", (String)"the_reach"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_reach.png"), class_2960.method_60655((String)"agob", (String)"the_stormlands"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_stormlands.png"), class_2960.method_60655((String)"agob", (String)"dorne"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_dorne.png"), class_2960.method_60655((String)"agob", (String)"the_nights_watch"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_nights_watch.png"), class_2960.method_60655((String)"agob", (String)"wildling"), class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_blank.png"));
    private GuidebookPage currentPage = GuidebookPage.INDEX;
    private final List<ClickableRegion> clickableRegions = new ArrayList<ClickableRegion>();
    private class_474 nextPageButton;
    private class_474 previousPageButton;

    public GuidebookScreen() {
        super((class_2561)class_2561.method_43470((String)"Guidebook"));
    }

    public static void open() {
        class_310.method_1551().method_1507((class_437)new GuidebookScreen());
    }

    public boolean method_25421() {
        return false;
    }

    protected void method_57734(float delta) {
    }

    protected void method_25426() {
        super.method_25426();
        int originX = (this.field_22789 - 271) / 2;
        int originY = (this.field_22790 - 180) / 2;
        this.previousPageButton = (class_474)this.method_37063((class_364)new class_474(originX + 15 + 4, originY + 161, false, button -> this.setPage(this.currentPage.previous()), true));
        this.nextPageButton = (class_474)this.method_37063((class_364)new class_474(originX + 143 + 113 - 27, originY + 161, true, button -> this.setPage(this.currentPage.next()), true));
        this.updatePageButtons();
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        int originX = (this.field_22789 - 271) / 2;
        int originY = (this.field_22790 - 180) / 2;
        this.clickableRegions.clear();
        context.method_25294(0, 0, this.field_22789, this.field_22790, 0);
        context.method_25291(UI_TEXTURE, originX, originY, 0, 0.0f, 0.0f, 271, 180, 271, 180);
        BookSpread spread = this.currentPage.spread();
        this.drawPage(context, originX + 15, originY + 11, spread.leftPage());
        this.drawPage(context, originX + 143, originY + 11, spread.rightPage());
        super.method_25394(context, mouseX, mouseY, delta);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button == 0) {
            for (ClickableRegion region : this.clickableRegions) {
                if (!region.contains(mouseX, mouseY)) continue;
                region.action().run();
                return true;
            }
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    private void drawPage(class_332 context, int pageX, int pageY, PageContent page) {
        if (page == null) {
            return;
        }
        class_327 renderer = this.field_22793;
        int contentX = pageX + 4;
        int contentY = pageY + 6;
        if (!page.title().isBlank()) {
            this.drawScaledText(context, page.title(), contentX, contentY, -14017776, 1.125f);
            contentY += 16;
        }
        for (PageElement element : page.elements()) {
            PageElement pageElement;
            Objects.requireNonNull(element);
            int n = 0;
            switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{ParagraphElement.class, IndexElement.class, QuestListElement.class, KingdomRosterElement.class, BlacksmithPreviewElement.class}, (Object)pageElement, n)) {
                case 0: {
                    ParagraphElement paragraph = (ParagraphElement)pageElement;
                    contentY = this.drawParagraph(context, paragraph, contentX, contentY, renderer);
                    break;
                }
                case 1: {
                    IndexElement index = (IndexElement)pageElement;
                    contentY = this.drawIndexLine(context, pageX, contentX, contentY, index);
                    break;
                }
                case 2: {
                    QuestListElement ignored = (QuestListElement)pageElement;
                    this.drawQuestList(context, contentX, contentY, renderer);
                    break;
                }
                case 3: {
                    KingdomRosterElement ignored = (KingdomRosterElement)pageElement;
                    this.drawKingdomRoster(context, contentX, contentY);
                    break;
                }
                case 4: {
                    BlacksmithPreviewElement ignored = (BlacksmithPreviewElement)pageElement;
                    this.drawBlacksmithPreview(context, contentX, contentY);
                    break;
                }
            }
        }
    }

    private int drawParagraph(class_332 context, ParagraphElement paragraph, int x, int y, class_327 renderer) {
        for (class_5481 line : renderer.method_1728((class_5348)class_2561.method_43470((String)paragraph.text()), 105)) {
            this.drawScaledOrderedText(context, line, x, y, paragraph.color(), 0.75f);
            Objects.requireNonNull(renderer);
            y += Math.max(1, Math.round((float)(9 + 2) * 0.75f));
        }
        return y + 2;
    }

    private int drawIndexLine(class_332 context, int pageX, int x, int y, IndexElement index) {
        class_5250 text = class_2561.method_43470((String)index.label()).method_10862(class_2583.field_24360.method_30938(Boolean.valueOf(true)));
        this.drawScaledText(context, index.label(), x, y, -14017776, 0.75f, class_2583.field_24360.method_30938(Boolean.valueOf(true)));
        int width = Math.round((float)this.field_22793.method_27525((class_5348)text) * 0.75f);
        Objects.requireNonNull(this.field_22793);
        int height = Math.max(1, Math.round(9.0f * 0.75f));
        this.clickableRegions.add(new ClickableRegion(pageX + 4, y, width, height, index.action()));
        Objects.requireNonNull(this.field_22793);
        return y + Math.max(1, Math.round((float)(9 + 4) * 0.75f));
    }

    private void drawQuestList(class_332 context, int x, int y, class_327 renderer) {
        List<QuestJournalStatePayload.Entry> quests = QuestJournalClientState.getActiveQuests();
        if (quests.isEmpty()) {
            for (class_5481 line : renderer.method_1728((class_5348)class_2561.method_43470((String)"No active quests. Speak to townsfolk or lords when a Quest button appears in dialogue."), 105)) {
                context.method_51430(renderer, line, x, y, -14017776, false);
                Objects.requireNonNull(renderer);
                y += 9 + 2;
            }
            return;
        }
        int maxEntries = 3;
        for (int index = 0; index < Math.min(maxEntries, quests.size()); ++index) {
            y = this.drawQuestEntry(context, x, y, quests.get(index));
        }
    }

    private int drawQuestEntry(class_332 context, int x, int y, QuestJournalStatePayload.Entry quest) {
        int boxX = x;
        int boxY = y;
        int boxWidth = 105;
        QuestEntryLayout layout = this.layoutQuestEntry(quest);
        int boxHeight = Math.max(40, layout.height());
        int questIndex = QuestJournalClientState.findQuestIndexByNpc(UUID.fromString(quest.giverNpcUuid()));
        class_1799 marker = QuestJournalClientState.getQuestMarkerStack(questIndex);
        this.drawBox(context, boxX, boxY, boxWidth, boxHeight, -14017776);
        if (!marker.method_7960()) {
            context.method_51427(marker, boxX + 3, boxY + 2);
        }
        class_1792 deliveryItem = QuestJournalClientState.resolveItem(quest.displayItemId());
        if (!"valyrian_rumor".equals(quest.templateId()) && deliveryItem != null && deliveryItem != class_1802.field_8162) {
            context.method_51427(new class_1799((class_1935)deliveryItem), boxX + 3, boxY + 18);
        }
        int textY = boxY + 4;
        for (class_5481 line : layout.lines()) {
            context.method_51430(this.field_22793, line, boxX + 20, textY, -14017776, false);
            Objects.requireNonNull(this.field_22793);
            textY += 9 + 2;
        }
        int dividerY = textY + 1;
        context.method_25294(boxX + 3, dividerY, boxX + boxWidth - 3, dividerY + 1, -14017776);
        int rewardY = dividerY + 4;
        for (class_5481 line : layout.rewards()) {
            context.method_51430(this.field_22793, line, boxX + 5, rewardY, -14017776, false);
            Objects.requireNonNull(this.field_22793);
            rewardY += 9 + 2;
        }
        return y + boxHeight + 3;
    }

    private QuestEntryLayout layoutQuestEntry(QuestJournalStatePayload.Entry quest) {
        String heading;
        String string = heading = "main_pledge".equals(quest.templateId()) ? "Main Quest" : "Quest";
        String target = "main_pledge".equals(quest.templateId()) ? "Pledge to a Banner" : ("valyrian_rumor".equals(quest.templateId()) ? this.valyrianTargetText(quest) : (quest.targetCastleName().isBlank() ? "Bring x" + quest.requiredCount() : (quest.itemId().equals("agob:sealed_letter") && !quest.targetNpcName().isBlank() ? "Deliver to " + quest.targetNpcName() : "Deliver to " + quest.targetCastleName())));
        String rewards = this.rewardSummary(quest);
        ArrayList<class_5481> lines = new ArrayList<class_5481>();
        lines.addAll(this.field_22793.method_1728((class_5348)class_2561.method_43470((String)heading), 81));
        lines.addAll(this.field_22793.method_1728((class_5348)class_2561.method_43470((String)target), 81));
        List rewardLines = this.field_22793.method_1728((class_5348)class_2561.method_43470((String)rewards), 95);
        int n = lines.size();
        Objects.requireNonNull(this.field_22793);
        int topHeight = n * (9 + 2) + 4;
        int n2 = rewardLines.size();
        Objects.requireNonNull(this.field_22793);
        int rewardHeight = n2 * (9 + 2) + 6;
        return new QuestEntryLayout(lines, rewardLines, topHeight + rewardHeight + 3);
    }

    private void drawRewardItemLine(class_332 context, int x, int y, QuestJournalStatePayload.Entry quest) {
        if (quest.goldIngots() > 0) {
            context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)String.valueOf(quest.goldIngots() * 2)), x, y, -14017776, false);
            context.method_51427(new class_1799((class_1935)GotItems.GOLDEN_DRAGON), x + 12, y - 4);
            return;
        }
        if (quest.ironIngots() > 0) {
            context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)String.valueOf(quest.ironIngots())), x, y, -14017776, false);
            context.method_51427(new class_1799((class_1935)class_1802.field_8620), x + 12, y - 4);
            return;
        }
        if (quest.skillPoints() > 0) {
            context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)String.valueOf(quest.skillPoints())), x, y, -14017776, false);
            SkillAttributeType type = SkillAttributeType.byId(quest.skillTypeId());
            context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)(type == null ? "Skill" : type.getDisplayName())), x + 12, y, -14017776, false);
            return;
        }
        if (quest.bonusSkillPoints() > 0) {
            context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)String.valueOf(quest.bonusSkillPoints())), x, y, -14017776, false);
            SkillAttributeType type = SkillAttributeType.byId(quest.bonusSkillTypeId());
            context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)(type == null ? "Skill" : type.getDisplayName())), x + 12, y, -14017776, false);
            return;
        }
        context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)"0"), x, y, -14017776, false);
    }

    private String rewardSummary(QuestJournalStatePayload.Entry quest) {
        SkillAttributeType type;
        if ("valyrian_rumor".equals(quest.templateId())) {
            return "Rewards Dragon Egg, Dragon Cradle";
        }
        StringBuilder builder = new StringBuilder();
        if (quest.reputationExperience() > 0) {
            builder.append("Rep ").append(quest.reputationExperience()).append(' ');
        }
        if (quest.goldIngots() > 0) {
            builder.append(GotEconomy.formatLegacyGoldIngots(quest.goldIngots())).append(' ');
        }
        if (quest.ironIngots() > 0) {
            builder.append("Iron ").append(quest.ironIngots()).append(' ');
        }
        if (quest.skillPoints() > 0 && !quest.skillTypeId().isBlank()) {
            type = SkillAttributeType.byId(quest.skillTypeId());
            builder.append(type == null ? "Skill" : type.getDisplayName()).append(" +").append(quest.skillPoints()).append(' ');
        }
        if (quest.bonusSkillPoints() > 0 && !quest.bonusSkillTypeId().isBlank()) {
            type = SkillAttributeType.byId(quest.bonusSkillTypeId());
            builder.append(type == null ? "Skill" : type.getDisplayName()).append(" +").append(quest.bonusSkillPoints()).append(' ');
        }
        return builder.isEmpty() ? "Rewards 0" : builder.toString().trim();
    }

    private String valyrianTargetText(QuestJournalStatePayload.Entry quest) {
        if ("confirm_identity".equals(quest.objectiveActionId())) {
            return "Confirm Identity";
        }
        if ("return_to_giver".equals(quest.objectiveActionId())) {
            return "Deliver News";
        }
        return "Investigate Rumor";
    }

    private void drawBox(class_332 context, int x, int y, int width, int height, int color) {
        context.method_25294(x, y, x + width, y + 1, color);
        context.method_25294(x, y + height - 1, x + width, y + height, color);
        context.method_25294(x, y, x + 1, y + height, color);
        context.method_25294(x + width - 1, y, x + width, y + height, color);
    }

    private void drawKingdomRoster(class_332 context, int x, int y) {
        int containerWidth = 105;
        int containerHeight = 20 * KINGDOM_GUIDE_ORDER.size() + 4;
        Map<class_2960, Integer> castleCounts = GotCastles.getCastleCountsByKingdom();
        int entryY = (y -= 8) + 2;
        for (int index = 0; index < KINGDOM_GUIDE_ORDER.size(); ++index) {
            class_2960 kingdomId = KINGDOM_GUIDE_ORDER.get(index);
            Kingdom kingdom = GotKingdoms.get(kingdomId);
            if (kingdom != null) {
                this.drawKingdomEntry(context, x + 2, entryY, kingdom, castleCounts.getOrDefault(kingdomId, 0));
            }
            entryY += 20;
        }
    }

    private void drawKingdomEntry(class_332 context, int x, int y, Kingdom kingdom, int castleCount) {
        class_2960 sigilTexture = KINGDOM_TEXTURES.get(kingdom.getId());
        class_2960 religionTexture = this.resolveReligionTexture(kingdom.getReligion().getId());
        int textX = x + 6 + 3;
        int lineY = y + 2;
        if (sigilTexture != null) {
            this.drawScaledIcon(context, sigilTexture, x, lineY, 6, 48, 62);
        }
        this.drawScaledText(context, kingdom.getName(), textX, lineY, -14017776, 0.66f);
        if (religionTexture != null) {
            this.drawScaledIcon(context, religionTexture, x, lineY + 6, 6, 16, 16);
        }
        String religionName = this.resolveReligionName(kingdom.getReligion().getId());
        this.drawScaledText(context, religionName, textX, lineY + 6, -14017776, 0.66f);
        this.drawScaledIcon(context, CASTLE_STONE_ICON, x, lineY + 12, 6, 16, 16);
        this.drawScaledText(context, castleCount + " castles", textX, lineY + 12, -14017776, 0.66f);
    }

    private void drawBlacksmithPreview(class_332 context, int x, int y) {
        int previewX = x;
        int previewY = y + 2;
        int previewBottom = previewY + 118;
        context.method_25294(previewX + 8, previewBottom - 18, previewX + 105 - 8, previewBottom - 17, 1429940762);
        context.method_25294(previewX + 12, previewBottom - 10, previewX + 105 - 4, previewBottom - 9, 1144728090);
        this.drawScaledItem(context, new class_1799((class_1935)GotBlocks.BLACKSMITH_FURNACE), previewX + 48, previewY + 18, 3.05f);
        this.drawScaledItem(context, new class_1799((class_1935)GotBlocks.BLACKSMITH_TABLE), previewX + 22, previewY + 40, 2.8f);
        this.drawScaledText(context, "Table 2x2x1", previewX + 8, previewBottom - 18, -14017776, 0.75f);
        this.drawScaledText(context, "Furnace 2x2x2", previewX + 50, previewBottom - 36, -14017776, 0.75f);
    }

    private void drawScaledItem(class_332 context, class_1799 stack, int x, int y, float scale) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_51427(stack, 0, 0);
        context.method_51448().method_22909();
    }

    private void drawScaledIcon(class_332 context, class_2960 texture, int x, int y, int maxSize, int textureWidth, int textureHeight) {
        float scale = Math.min((float)maxSize / (float)textureWidth, (float)maxSize / (float)textureHeight);
        int drawWidth = Math.max(1, Math.round((float)textureWidth * scale));
        int drawHeight = Math.max(1, Math.round((float)textureHeight * scale));
        int offsetX = (maxSize - drawWidth) / 2;
        int offsetY = (maxSize - drawHeight) / 2;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)(x + offsetX), (float)(y + offsetY), 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, textureWidth, textureHeight, textureWidth, textureHeight);
        context.method_51448().method_22909();
    }

    private String resolveReligionName(String religionId) {
        ReligionSelectionOption option = GotReligionOptions.get(religionId);
        return option != null ? option.displayName() : religionId;
    }

    private class_2960 resolveReligionTexture(String religionId) {
        ReligionSelectionOption option = GotReligionOptions.get(religionId);
        if (option == null) {
            return null;
        }
        String path = option.texture().method_12832();
        if (!path.endsWith(".png")) {
            return option.texture();
        }
        return class_2960.method_60655((String)option.texture().method_12836(), (String)path.replace(".png", "_small.png"));
    }

    private void drawScaledText(class_332 context, String text, int x, int y, int color, float scale) {
        this.drawScaledText(context, text, x, y, color, scale, class_2583.field_24360);
    }

    private void drawScaledText(class_332 context, String text, int x, int y, int color, float scale, class_2583 style) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)text).method_10862(style), 0, 0, color, false);
        context.method_51448().method_22909();
    }

    private void drawScaledOrderedText(class_332 context, class_5481 text, int x, int y, int color, float scale) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_51430(this.field_22793, text, 0, 0, color, false);
        context.method_51448().method_22909();
    }

    private static void openPage(String pageId) {
        GuidebookScreen.openPage(GuidebookPage.valueOf(pageId));
    }

    private static void openPage(GuidebookPage page) {
        class_310 client = class_310.method_1551();
        class_437 class_4372 = client.field_1755;
        if (class_4372 instanceof GuidebookScreen) {
            GuidebookScreen screen = (GuidebookScreen)class_4372;
            screen.setPage(page);
        }
    }

    private void setPage(GuidebookPage page) {
        this.currentPage = page;
        this.updatePageButtons();
    }

    private void updatePageButtons() {
        if (this.previousPageButton != null) {
            this.previousPageButton.field_22764 = this.currentPage.hasPrevious();
        }
        if (this.nextPageButton != null) {
            this.nextPageButton.field_22764 = this.currentPage.hasNext();
        }
    }

    @Environment(value=EnvType.CLIENT)
    private static enum GuidebookPage {
        INDEX(new BookSpread(new PageContent("Guide to AGoB", List.of(new ParagraphElement("Index I", -14017776), new IndexElement("1. Getting Started", () -> GuidebookScreen.openPage("GETTING_STARTED_ONE")), new IndexElement("2. First Steps", () -> GuidebookScreen.openPage("FIRST_STEPS_ONE")), new IndexElement("3. Map And Travel", () -> GuidebookScreen.openPage("MAP_AND_TRAVEL_ONE")), new IndexElement("4. NPC Roles", () -> GuidebookScreen.openPage("NPC_ROLES_ONE")), new IndexElement("5. Quests", () -> GuidebookScreen.openPage("QUESTS_ONE")))), new PageContent("Guide Index II", List.of(new ParagraphElement("Index II", -14017776), new IndexElement("6. Reputation", () -> GuidebookScreen.openPage("REPUTATION_ONE")), new IndexElement("7. Skills", () -> GuidebookScreen.openPage("SKILLS_ONE")), new IndexElement("8. Kingdoms", () -> GuidebookScreen.openPage("KINGDOMS_ONE")), new IndexElement("9. Troops", () -> GuidebookScreen.openPage("TROOPS_ONE")), new IndexElement("10. Commander", () -> GuidebookScreen.openPage("COMMANDER_ONE")))))),
        INDEX_TWO(new BookSpread(new PageContent("Guide Index III", List.of(new IndexElement("11. Castles", () -> GuidebookScreen.openPage("CASTLES_ONE")), new IndexElement("12. Blacksmithing", () -> GuidebookScreen.openPage("BLACKSMITHING_ONE")), new ParagraphElement("Use the index to jump straight to the system you need.", -14017776))), new PageContent("", List.of(new ParagraphElement("The guidebook is practical on purpose.", -14017776), new ParagraphElement("Use it to learn what matters now, then come back when a new system opens up.", -14017776))))),
        GETTING_STARTED_ONE(new BookSpread(new PageContent("Getting Started", List.of(new ParagraphElement("Pick a kingdom and religion first. That choice shapes your identity, spawn region, and the feel of your run.", -14017776), new ParagraphElement("After arriving, open your world map and learn where your nearby castles and roads are.", -14017776))), new PageContent("Your Main Goals", List.of(new ParagraphElement("Talk to local NPCs early. They are the main doorway into quests, gear, and regional progression.", -14017776), new ParagraphElement("Build reputation.", -14017776), new ParagraphElement("Find castles worth returning to.", -14017776), new ParagraphElement("Start collecting gear, quest rewards, and future troop support.", -14017776))))),
        FIRST_STEPS_ONE(new BookSpread(new PageContent("First Steps", List.of(new ParagraphElement("Your first useful move is simple: speak to townsfolk, guards, or lords until you find a quest offer.", -14017776), new ParagraphElement("Accept one job, check your journal, and finish it before wandering too far from the region.", -14017776), new ParagraphElement("That gives you a clear route, early rewards, and a reason to revisit the local castle network.", -14017776))), new PageContent("Early Priorities", List.of(new ParagraphElement("Keep the map, journal, and guidebook close at hand.", -14017776), new ParagraphElement("Learn one nearby castle, one useful NPC cluster, and one safe road out of your area.", -14017776), new ParagraphElement("Do not rush every system at once. Quests and travel naturally lead you into the others.", -14017776))))),
        MAP_AND_TRAVEL_ONE(new BookSpread(new PageContent("Map And Travel", List.of(new ParagraphElement("This mod is built around a custom realm, so navigation matters more than in a normal vanilla world.", -14017776), new ParagraphElement("Use the world map to understand where kingdoms, castles, and long travel routes sit around you.", -14017776))), new PageContent("Travel Advice", List.of(new ParagraphElement("Use roads and strongholds as anchors.", -14017776), new ParagraphElement("Treat castles like hubs, not background scenery.", -14017776))))),
        NPC_ROLES_ONE(new BookSpread(new PageContent("NPC Roles", List.of(new ParagraphElement("Commoners fill the world and help make regions feel lived in.", -14017776), new ParagraphElement("Guards mark kingdom control and the military presence of a region.", -14017776), new ParagraphElement("Lords matter most for power, castles, and higher-value interaction.", -14017776))), new PageContent("", List.of(new ParagraphElement("Blacksmiths support gear progression and are worth checking whenever you reach a castle.", -14017776), new ParagraphElement("If you want progress, look for the NPCs who can offer quests, connect you to castles, or help you gear up.", -14017776))))),
        QUESTS_ONE(new BookSpread(new PageContent("Quests", List.of(new ParagraphElement("Quests appear in NPC dialogue when that NPC is currently carrying an offer.", -14017776), new ParagraphElement("Accepting a quest adds it to your journal and gives you a clear short-term goal.", -14017776), new ParagraphElement("Use quests to build reputation, gather useful materials, and stay pointed toward meaningful places.", -14017776))), new PageContent("Active Quests", List.of(new QuestListElement())))),
        REPUTATION_ONE(new BookSpread(new PageContent("Reputation", List.of(new ParagraphElement("Reputation is one of your main long-term goals. It tracks standing rather than raw survival.", -14017776), new ParagraphElement("Quests and faction activity help move you upward through titles and social weight.", -14017776), new ParagraphElement("If you are unsure what to do next, advancing your reputation is always a solid answer.", -14017776))), new PageContent("Titles", List.of(new ParagraphElement("Titles show how far you have climbed.", -14017776), new ParagraphElement("Think of them as proof that your actions in the realm are starting to matter.", -14017776), new ParagraphElement("Progress here turns wandering into a real character arc.", -14017776))))),
        SKILLS_ONE(new BookSpread(new PageContent("Skills", List.of(new ParagraphElement("Health helps you endure danger.", -14017776), new ParagraphElement("Charisma and trading support stronger social and economic progress.", -14017776), new ParagraphElement("Intelligence and luck improve the quality of your overall journey.", -14017776))), new PageContent("Using Skills", List.of(new ParagraphElement("Do not think of skills as a separate minigame.", -14017776), new ParagraphElement("They grow out of the same loop as quests, travel, gear, and faction progress.", -14017776), new ParagraphElement("Build the character style you want to play instead of spreading everything thin.", -14017776))))),
        KINGDOMS_ONE(new BookSpread(new PageContent("Kingdoms", List.of(new ParagraphElement("The kingdoms of Westeros each carry their own banners, faiths, and strongholds.", -14017776), new ParagraphElement("Your starting choice is not just visual flavor. It changes the region and identity of your run.", -14017776), new ParagraphElement("This spread shows the main realms, their religion, and their total castles.", -14017776))), new PageContent("", List.of(new KingdomRosterElement())))),
        TROOPS_ONE(new BookSpread(new PageContent("Troops", List.of(new ParagraphElement("Troops turn you from a traveler into a leader.", -14017776), new ParagraphElement("Recruit them, keep them alive, and upgrade them over time instead of treating them as disposable bodies.", -14017776), new ParagraphElement("Different kingdoms support different troop identities, so your region choice continues to matter here.", -14017776))), new PageContent("Party Flow", List.of(new ParagraphElement("Hire troops when you can support them.", -14017776), new ParagraphElement("Store or summon them as your needs change.", -14017776), new ParagraphElement("Use upgrades to turn a small force into a reliable warband.", -14017776))))),
        COMMANDER_ONE(new BookSpread(new PageContent("Commander", List.of(new ParagraphElement("Commander controls let you direct a group instead of fighting as if you were alone.", -14017776), new ParagraphElement("The core decisions are simple: move, charge, reform, and recall.", -14017776), new ParagraphElement("Use them to keep troops organized and stop your line from scattering into chaos.", -14017776))), new PageContent("Practical Use", List.of(new ParagraphElement("Move before a fight to claim good ground.", -14017776), new ParagraphElement("Charge only when the moment is right.", -14017776), new ParagraphElement("Recall damaged or exposed troops before a bad push becomes a wipe.", -14017776))))),
        CASTLES_ONE(new BookSpread(new PageContent("Castles", List.of(new ParagraphElement("Castles are some of the most important locations in the mod.", -14017776), new ParagraphElement("They connect travel, kingdom identity, blacksmithing, power, and future progression.", -14017776), new ParagraphElement("When exploring a region, learn its castles first. They are the safest way to understand the area.", -14017776))), new PageContent("Why They Matter", List.of(new ParagraphElement("Castles are hubs.", -14017776), new ParagraphElement("They help you orient yourself on the map.", -14017776), new ParagraphElement("They are where many of the mod's larger systems begin to connect.", -14017776))))),
        BLACKSMITHING_ONE(new BookSpread(new PageContent("Blacksmithing", List.of(new ParagraphElement("Castle smithies hold two multiblock stations used for blacksmithing.", -14017776), new ParagraphElement("The blacksmith table forms a 2x2x1 workstation, while the furnace rises as a 2x2x2 forge.", -14017776), new ParagraphElement("Both structures must be complete before they can be used.", -14017776))), new PageContent("", List.of(new BlacksmithPreviewElement())))),
        BLACKSMITHING_TWO(new BookSpread(new PageContent("Smithing Use", List.of(new ParagraphElement("Blacksmithing is part of faction progression, not just decoration inside castles.", -14017776), new ParagraphElement("Return to smithies when you want better gear, regional equipment, or a reason to revisit important strongholds.", -14017776), new ParagraphElement("Treat a good smithy as a home base for equipment progress.", -14017776))), new PageContent("Practical Advice", List.of(new ParagraphElement("Learn where your nearest working smithy is.", -14017776), new ParagraphElement("Check castles often as your resources improve.", -14017776), new ParagraphElement("Gear progression feels much better when tied to repeatable castle routes.", -14017776)))));

        private static final GuidebookPage[] VALUES;
        private final BookSpread spread;

        private GuidebookPage(BookSpread spread) {
            this.spread = spread;
        }

        private BookSpread spread() {
            return this.spread;
        }

        private GuidebookPage previous() {
            int index = this.ordinal();
            return index <= 0 ? this : VALUES[index - 1];
        }

        private GuidebookPage next() {
            int index = this.ordinal();
            return index >= VALUES.length - 1 ? this : VALUES[index + 1];
        }

        private boolean hasPrevious() {
            return this.ordinal() > 0;
        }

        private boolean hasNext() {
            return this.ordinal() < VALUES.length - 1;
        }

        static {
            VALUES = GuidebookPage.values();
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record BookSpread(PageContent leftPage, PageContent rightPage) {
    }

    @Environment(value=EnvType.CLIENT)
    private record PageContent(String title, List<PageElement> elements) {
    }

    @Environment(value=EnvType.CLIENT)
    private record ClickableRegion(int x, int y, int width, int height, Runnable action) {
        private boolean contains(double mouseX, double mouseY) {
            return mouseX >= (double)this.x && mouseX <= (double)(this.x + this.width) && mouseY >= (double)this.y && mouseY <= (double)(this.y + this.height);
        }
    }

    @Environment(value=EnvType.CLIENT)
    private static sealed interface PageElement
    permits ParagraphElement, IndexElement, QuestListElement, KingdomRosterElement, BlacksmithPreviewElement {
    }

    @Environment(value=EnvType.CLIENT)
    private record ParagraphElement(String text, int color) implements PageElement
    {
    }

    @Environment(value=EnvType.CLIENT)
    private record IndexElement(String label, Runnable action) implements PageElement
    {
    }

    @Environment(value=EnvType.CLIENT)
    private record QuestListElement() implements PageElement
    {
    }

    @Environment(value=EnvType.CLIENT)
    private record KingdomRosterElement() implements PageElement
    {
    }

    @Environment(value=EnvType.CLIENT)
    private record BlacksmithPreviewElement() implements PageElement
    {
    }

    @Environment(value=EnvType.CLIENT)
    private record QuestEntryLayout(List<class_5481> lines, List<class_5481> rewards, int height) {
    }
}

