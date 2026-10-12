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
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_364
 *  net.minecraft.class_437
 *  net.minecraft.class_474
 *  net.minecraft.class_5348
 *  net.minecraft.class_5481
 */
package trep.got.client.screen;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_437;
import net.minecraft.class_474;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import trep.got.client.mainquest.MainQuestClientState;
import trep.got.client.quest.QuestJournalClientState;
import trep.got.network.MainQuestStatePayload;
import trep.got.network.QuestJournalStatePayload;
import trep.got.quest.QuestTemplate;
import trep.got.quest.QuestTemplateRegistry;
import trep.got.registry.GotItems;
import trep.got.skill.SkillAttributeType;

@Environment(value=EnvType.CLIENT)
public final class JournalScreen
extends class_437 {
    private static final class_2960 UI_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/guidebook_ui.png");
    private static final class_2960 BOOKMARK_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/bookmark.png");
    private static final class_2960 BOOKMARK_HOVER_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/bookmark_hover.png");
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
    private static final int PAGE_TEXT_HEIGHT = 129;
    private static final int TURN_BUTTON_Y = 161;
    private static final int LINE_GAP = 2;
    private static final int SECTION_GAP = 6;
    private static final int BOOKMARK_SIZE = 21;
    private static final int BOOKMARK_GAP = 2;
    private static final int BOOKMARK_X_OFFSET = 15;
    private static final int BOOKMARK_Y_OFFSET = -20;
    private static final int BOOKMARK_ICON_SIZE = 16;
    private JournalTab currentTab = JournalTab.MAIN_QUESTS;
    private int currentSpreadIndex;
    private class_474 nextPageButton;
    private class_474 previousPageButton;

    public JournalScreen() {
        super((class_2561)class_2561.method_43470((String)"Journal"));
    }

    public static void open() {
        class_310.method_1551().method_1507((class_437)new JournalScreen());
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
        this.previousPageButton = (class_474)this.method_37063((class_364)new class_474(originX + 15 + 4, originY + 161, false, button -> this.setSpread(this.currentSpreadIndex - 1), true));
        this.nextPageButton = (class_474)this.method_37063((class_364)new class_474(originX + 143 + 113 - 27, originY + 161, true, button -> this.setSpread(this.currentSpreadIndex + 1), true));
        this.currentSpreadIndex = Math.min(this.currentSpreadIndex, Math.max(0, this.spreadCount() - 1));
        this.updatePageButtons();
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        int originX = (this.field_22789 - 271) / 2;
        int originY = (this.field_22790 - 180) / 2;
        context.method_25294(0, 0, this.field_22789, this.field_22790, 0);
        context.method_25291(UI_TEXTURE, originX, originY, 0, 0.0f, 0.0f, 271, 180, 271, 180);
        this.drawJournalTabs(context, originX, originY, mouseX, mouseY);
        List<PageData> pages = this.buildPages();
        this.drawPage(context, originX + 15, originY + 11, this.pageAt(pages, this.currentSpreadIndex * 2));
        this.drawPage(context, originX + 143, originY + 11, this.pageAt(pages, this.currentSpreadIndex * 2 + 1));
        super.method_25394(context, mouseX, mouseY, delta);
        JournalTab hoveredTab = this.hoveredTab(originX, originY, mouseX, mouseY);
        if (hoveredTab != null) {
            context.method_51438(this.field_22793, (class_2561)class_2561.method_43470((String)hoveredTab.displayName()), mouseX, mouseY);
        }
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        int originY;
        int originX;
        JournalTab clickedTab;
        if (button == 0 && (clickedTab = this.hoveredTab(originX = (this.field_22789 - 271) / 2, originY = (this.field_22790 - 180) / 2, mouseX, mouseY)) != null) {
            this.setTab(clickedTab);
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    private void drawJournalTabs(class_332 context, int originX, int originY, int mouseX, int mouseY) {
        for (JournalTab tab : JournalTab.values()) {
            int tabX = this.tabX(originX, tab);
            int tabY = this.tabY(originY);
            boolean highlighted = tab == this.currentTab || this.isInside(mouseX, mouseY, tabX, tabY, 21, 21);
            class_2960 texture = highlighted ? BOOKMARK_HOVER_TEXTURE : BOOKMARK_TEXTURE;
            context.method_25291(texture, tabX, tabY, 0, 0.0f, 0.0f, 21, 21, 21, 21);
            int iconX = tabX + 2;
            int iconY = tabY + 2;
            context.method_51427(tab.icon(), iconX, iconY);
        }
    }

    private void drawPage(class_332 context, int pageX, int pageY, PageData page) {
        if (page == null) {
            return;
        }
        int x = pageX + 4;
        int y = pageY + 6;
        for (PageLine line : page.lines()) {
            context.method_51430(this.field_22793, line.text(), x, y, line.color(), false);
            Objects.requireNonNull(this.field_22793);
            y += 9 + 2;
        }
        if (!page.marker().method_7960()) {
            context.method_51427(page.marker(), pageX + 113 - 20, pageY + 6);
        }
    }

    private List<PageData> buildPages() {
        if (this.currentTab == JournalTab.MAIN_QUESTS) {
            return this.buildMainQuestPages();
        }
        List<QuestJournalStatePayload.Entry> quests = this.questsForCurrentTab();
        if (quests.isEmpty()) {
            return this.emptyPagesForCurrentTab();
        }
        ArrayList<PageData> pages = new ArrayList<PageData>();
        for (int index = 0; index < quests.size(); ++index) {
            QuestJournalStatePayload.Entry quest = quests.get(index);
            List<PageLine> wrappedLines = this.wrapQuestLines(quest, index, this.field_22793);
            class_1799 marker = QuestJournalClientState.getQuestMarkerStack(QuestJournalClientState.getActiveQuests().indexOf(quest));
            pages.addAll(this.paginateQuest(wrappedLines, marker));
        }
        return pages;
    }

    private List<PageData> buildMainQuestPages() {
        List<MainQuestStatePayload.Entry> quests = MainQuestClientState.activeQuests();
        List<QuestJournalStatePayload.Entry> pledgeQuests = QuestJournalClientState.getActiveQuests().stream().filter(QuestJournalStatePayload.Entry::mainQuest).toList();
        if (quests.isEmpty() && pledgeQuests.isEmpty()) {
            return this.emptyPagesForCurrentTab();
        }
        ArrayList<PageData> pages = new ArrayList<PageData>();
        for (MainQuestStatePayload.Entry entry : quests) {
            pages.addAll(this.paginateQuest(this.wrapMainQuestLines(entry, this.field_22793), new class_1799((class_1935)class_1802.field_8529)));
        }
        for (QuestJournalStatePayload.Entry entry : pledgeQuests) {
            int questIndex = QuestJournalClientState.getActiveQuests().indexOf(entry);
            class_1799 marker = QuestJournalClientState.getQuestMarkerStack(questIndex);
            pages.addAll(this.paginateQuest(this.wrapQuestLines(entry, questIndex, this.field_22793), marker));
        }
        return pages;
    }

    private List<PageData> paginateQuest(List<PageLine> lines, class_1799 marker) {
        Objects.requireNonNull(this.field_22793);
        int maxLines = Math.max(1, 129 / (9 + 2));
        ArrayList<PageData> pages = new ArrayList<PageData>();
        for (int start = 0; start < lines.size(); start += maxLines) {
            int end = Math.min(lines.size(), start + maxLines);
            pages.add(new PageData(List.copyOf(lines.subList(start, end)), start == 0 ? marker : class_1799.field_8037));
        }
        return pages;
    }

    private List<PageLine> wrapQuestLines(QuestJournalStatePayload.Entry quest, int questIndex, class_327 renderer) {
        ArrayList<PageLine> lines = new ArrayList<PageLine>();
        String topLabel = "main_pledge".equals(quest.templateId()) ? "Main Quest" : "Quest " + (questIndex + 1);
        this.appendWrapped(lines, renderer, topLabel, -14017776);
        this.appendWrapped(lines, renderer, this.questTitle(quest), -10861261);
        lines.add(this.blankLine());
        if ("main_pledge".equals(quest.templateId())) {
            this.appendWrapped(lines, renderer, "Pledged from " + quest.targetCastleName() + ".", -10861261);
            this.appendWrapped(lines, renderer, "Return to " + quest.targetCastleName() + " after the invasion outcome resolves.", -10861261);
            lines.add(this.blankLine());
        }
        this.appendWrapped(lines, renderer, "What to do:", -14017776);
        this.appendWrapped(lines, renderer, "Next: " + this.objectiveText(quest), -14017776);
        lines.add(this.blankLine());
        this.appendWrapped(lines, renderer, "Deadline: " + this.dueText(quest), -10861261);
        this.appendWrapped(lines, renderer, this.rewardSummary(quest), -10861261);
        if (!quest.giverNpcName().isBlank()) {
            this.appendWrapped(lines, renderer, "Quest giver: " + quest.giverNpcName(), -10861261);
        }
        return lines;
    }

    private List<PageLine> wrapMainQuestLines(MainQuestStatePayload.Entry quest, class_327 renderer) {
        ArrayList<PageLine> lines = new ArrayList<PageLine>();
        this.appendWrapped(lines, renderer, "Main Quest", -14017776);
        this.appendWrapped(lines, renderer, quest.displayName(), -10861261);
        lines.add(this.blankLine());
        for (String line : quest.description()) {
            this.appendWrapped(lines, renderer, line, -14017776);
        }
        lines.add(this.blankLine());
        for (int index = 0; index < quest.steps().size(); ++index) {
            MainQuestStatePayload.Step step = quest.steps().get(index);
            String prefix = index < quest.currentStepIndex() ? "Done: " : (index == quest.currentStepIndex() ? "Next: " : "Then: ");
            this.appendWrapped(lines, renderer, prefix + step.text() + this.mainQuestStepProgressText(step), index <= quest.currentStepIndex() ? -14017776 : -10861261);
        }
        lines.add(this.blankLine());
        this.appendWrapped(lines, renderer, this.mainQuestRewardSummary(quest), -10861261);
        return lines;
    }

    private void appendWrapped(List<PageLine> lines, class_327 renderer, String text, int color) {
        for (class_5481 line : renderer.method_1728((class_5348)class_2561.method_43470((String)text), 105)) {
            lines.add(new PageLine(line, color));
        }
    }

    private PageLine blankLine() {
        return new PageLine(class_2561.method_43470((String)"").method_30937(), -14017776);
    }

    private PageData pageAt(List<PageData> pages, int pageIndex) {
        return pageIndex >= 0 && pageIndex < pages.size() ? pages.get(pageIndex) : null;
    }

    private List<QuestJournalStatePayload.Entry> questsForCurrentTab() {
        return switch (this.currentTab.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> List.of();
            case 1 -> this.sideQuestEntries();
            case 2 -> this.letterEntries();
        };
    }

    private List<QuestJournalStatePayload.Entry> sideQuestEntries() {
        return QuestJournalClientState.getActiveQuests().stream().filter(quest -> !quest.mainQuest()).toList();
    }

    private List<QuestJournalStatePayload.Entry> letterEntries() {
        return List.of();
    }

    private List<PageData> emptyPagesForCurrentTab() {
        return List.of(new PageData(List.of(new PageLine(class_2561.method_43470((String)this.currentTab.displayName()).method_30937(), -14017776), this.blankLine(), new PageLine(class_2561.method_43470((String)this.currentTab.emptyLineOne()).method_30937(), -14017776), new PageLine(class_2561.method_43470((String)this.currentTab.emptyLineTwo()).method_30937(), -14017776), new PageLine(class_2561.method_43470((String)this.currentTab.emptyLineThree()).method_30937(), -14017776)), class_1799.field_8037));
    }

    private int spreadCount() {
        return Math.max(1, (this.buildPages().size() + 1) / 2);
    }

    private void setTab(JournalTab tab) {
        if (tab != this.currentTab) {
            this.currentTab = tab;
            this.currentSpreadIndex = 0;
            this.updatePageButtons();
        }
    }

    private void setSpread(int index) {
        this.currentSpreadIndex = Math.max(0, Math.min(index, this.spreadCount() - 1));
        this.updatePageButtons();
    }

    private void updatePageButtons() {
        if (this.previousPageButton != null) {
            boolean bl = this.previousPageButton.field_22764 = this.currentSpreadIndex > 0;
        }
        if (this.nextPageButton != null) {
            this.nextPageButton.field_22764 = this.currentSpreadIndex < this.spreadCount() - 1;
        }
    }

    private JournalTab hoveredTab(int originX, int originY, double mouseX, double mouseY) {
        for (JournalTab tab : JournalTab.values()) {
            if (!this.isInside(mouseX, mouseY, this.tabX(originX, tab), this.tabY(originY), 21, 21)) continue;
            return tab;
        }
        return null;
    }

    private int tabX(int originX, JournalTab tab) {
        return originX + 15 + tab.ordinal() * 23;
    }

    private int tabY(int originY) {
        return originY + -20;
    }

    private boolean isInside(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    private String questTitle(QuestJournalStatePayload.Entry quest) {
        QuestTemplate template = QuestTemplateRegistry.get(quest.templateId());
        return "main_pledge".equals(quest.templateId()) ? "Pledge to a Banner" : (template != null ? template.displayName() : "Quest");
    }

    private String objectiveText(QuestJournalStatePayload.Entry quest) {
        if ("main_pledge".equals(quest.templateId())) {
            String action = switch (quest.objectiveActionId()) {
                case "crusade" -> "Crusade";
                case "raid" -> "Raid";
                default -> "Conquer";
            };
            return quest.objectiveComplete() ? "Step complete. Return to the lord of " + quest.targetCastleName() + "." : "Lead a " + action + " at a castle of your choosing. When that invasion ends in success or failure, return to the lord of " + quest.targetCastleName() + ".";
        }
        if ("valyrian_rumor".equals(quest.templateId())) {
            if ("confirm_identity".equals(quest.objectiveActionId())) {
                return "Travel to a Targaryen-owned castle and ask a Targaryen lord to confirm the rumor.";
            }
            if ("return_to_giver".equals(quest.objectiveActionId())) {
                return "Deliver the news back to " + quest.giverNpcName() + ".";
            }
            return "Investigate the rumor of true Valyrians in the south.";
        }
        if (!quest.targetCastleName().isBlank()) {
            return quest.itemId().equals("agob:sealed_letter") && !quest.targetNpcName().isBlank() ? "Deliver the letter to " + quest.targetNpcName() + " at " + quest.targetCastleName() + "." : "Deliver the shipment to " + quest.targetCastleName() + ".";
        }
        ItemStackNameHelper itemName = new ItemStackNameHelper(QuestJournalClientState.resolveItem(quest.itemId()));
        return "Bring " + quest.requiredCount() + " " + itemName.value() + ".";
    }

    private String dueText(QuestJournalStatePayload.Entry quest) {
        if ("valyrian_rumor".equals(quest.templateId())) {
            return "No deadline";
        }
        long remainingTicks = Math.max(0L, quest.dueAtTick() - (this.field_22787 == null || this.field_22787.field_1687 == null ? 0L : QuestJournalClientState.currentQuestTime(this.field_22787.field_1687)));
        long remainingDays = Math.max(1L, (remainingTicks + 23999L) / 24000L);
        return remainingDays + " day(s)";
    }

    private String rewardSummary(QuestJournalStatePayload.Entry quest) {
        SkillAttributeType type;
        if ("valyrian_rumor".equals(quest.templateId())) {
            return "Rewards: Dragon Egg, Dragon Cradle";
        }
        ArrayList<CallSite> rewards = new ArrayList<CallSite>();
        if (quest.reputationExperience() > 0) {
            rewards.add((CallSite)((Object)("Rep +" + quest.reputationExperience())));
        }
        if (quest.goldIngots() > 0) {
            rewards.add((CallSite)((Object)(quest.goldIngots() + " Gold Ingot" + (quest.goldIngots() == 1 ? "" : "s"))));
        }
        if (quest.ironIngots() > 0) {
            rewards.add((CallSite)((Object)(quest.ironIngots() + " Iron Ingot" + (quest.ironIngots() == 1 ? "" : "s"))));
        }
        if (quest.skillPoints() > 0 && !quest.skillTypeId().isBlank()) {
            type = SkillAttributeType.byId(quest.skillTypeId());
            rewards.add((CallSite)((Object)((type == null ? "Skill" : type.getDisplayName()) + " +" + quest.skillPoints())));
        }
        if (quest.bonusSkillPoints() > 0 && !quest.bonusSkillTypeId().isBlank()) {
            type = SkillAttributeType.byId(quest.bonusSkillTypeId());
            rewards.add((CallSite)((Object)((type == null ? "Skill" : type.getDisplayName()) + " +" + quest.bonusSkillPoints())));
        }
        return rewards.isEmpty() ? "Rewards: None" : "Rewards: " + String.join((CharSequence)", ", rewards);
    }

    private String mainQuestRewardSummary(MainQuestStatePayload.Entry quest) {
        if (quest.rewards().isEmpty()) {
            return "Rewards: None";
        }
        StringBuilder builder = new StringBuilder("Rewards: ");
        for (MainQuestStatePayload.Reward reward : quest.rewards()) {
            ItemStackNameHelper itemName = new ItemStackNameHelper(QuestJournalClientState.resolveItem(reward.itemId()));
            builder.append(reward.count()).append(' ').append(itemName.value()).append(' ');
        }
        return builder.toString().trim();
    }

    private String mainQuestStepProgressText(MainQuestStatePayload.Step step) {
        if (step.required() <= 0 || step.required() == 1 && step.progress() <= 0) {
            return "";
        }
        int progress = Math.max(0, Math.min(step.progress(), step.required()));
        return " (" + progress + "/" + step.required() + ")";
    }

    @Environment(value=EnvType.CLIENT)
    private static enum JournalTab {
        MAIN_QUESTS("Main Quests", new class_1799((class_1935)class_1802.field_8529), "No active", "main quests.", ""),
        SIDE_QUESTS("Side Quests", new class_1799((class_1935)class_1802.field_8407), "No side quests.", "Speak to townsfolk", "when a Quest appears."),
        LETTERS("Letters", new class_1799((class_1935)GotItems.SEALED_LETTER), "No letters.", "Custom requests", "will appear here.");

        private final String displayName;
        private final class_1799 icon;
        private final String emptyLineOne;
        private final String emptyLineTwo;
        private final String emptyLineThree;

        private JournalTab(String displayName, class_1799 icon, String emptyLineOne, String emptyLineTwo, String emptyLineThree) {
            this.displayName = displayName;
            this.icon = icon;
            this.emptyLineOne = emptyLineOne;
            this.emptyLineTwo = emptyLineTwo;
            this.emptyLineThree = emptyLineThree;
        }

        private String displayName() {
            return this.displayName;
        }

        private class_1799 icon() {
            return this.icon;
        }

        private String emptyLineOne() {
            return this.emptyLineOne;
        }

        private String emptyLineTwo() {
            return this.emptyLineTwo;
        }

        private String emptyLineThree() {
            return this.emptyLineThree;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record PageData(List<PageLine> lines, class_1799 marker) {
    }

    @Environment(value=EnvType.CLIENT)
    private record PageLine(class_5481 text, int color) {
    }

    @Environment(value=EnvType.CLIENT)
    private record ItemStackNameHelper(class_1792 item) {
        private String value() {
            return this.item == null || this.item == class_1802.field_8162 ? "items" : this.item.method_7848().getString();
        }
    }
}

