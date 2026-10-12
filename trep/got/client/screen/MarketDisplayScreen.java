/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_342
 *  net.minecraft.class_364
 *  net.minecraft.class_437
 *  net.minecraft.class_465
 *  net.minecraft.class_7923
 */
package trep.got.client.screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_364;
import net.minecraft.class_437;
import net.minecraft.class_465;
import net.minecraft.class_7923;
import trep.got.screen.MarketDisplayScreenHandler;

@Environment(value=EnvType.CLIENT)
public final class MarketDisplayScreen
extends class_465<MarketDisplayScreenHandler> {
    private static final class_2960 TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/market_display_gui.png");
    private static final class_2960 SEARCH_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/market_display_search.png");
    private static final int SLOT_SIZE = 18;
    private static final int ARROW_U = 196;
    private static final int ARROW_V = 36;
    private static final int ARROW_WIDTH = 8;
    private static final int ARROW_HEIGHT = 13;
    private static final int SELL_ROW_X = 8;
    private static final int SELL_ROW_Y = 18;
    private static final int AMOUNT_ROW_X = 8;
    private static final int AMOUNT_ROW_Y = 36;
    private static final int DIVIDER_ROW_X = 8;
    private static final int DIVIDER_ROW_Y = 54;
    private static final int DIVIDER_SLOT_HEIGHT = 16;
    private static final int PRICE_ROW_X = 8;
    private static final int PRICE_ROW_Y = 70;
    private static final int RESULT_ROW_X = 8;
    private static final int RESULT_ROW_Y = 88;
    private static final int ARROW_OFFSET_X = 4;
    private static final int ARROW_OFFSET_Y = 0;
    private static final class_2960 SEARCH_SCROLLER_TEXTURE = class_2960.method_60656((String)"container/creative_inventory/scroller");
    private static final class_2960 SEARCH_SCROLLER_DISABLED_TEXTURE = class_2960.method_60656((String)"container/creative_inventory/scroller_disabled");

    public MarketDisplayScreen(MarketDisplayScreenHandler handler, class_1661 inventory, class_2561 title) {
        super((class_1703)handler, inventory, title);
        this.field_2792 = 176;
        this.field_2779 = 194;
        this.field_25270 = 101;
        this.field_25267 = 0;
        this.field_25268 = 0;
    }

    protected void method_2388(class_332 context, int mouseX, int mouseY) {
        int hoveredDivider = this.getHoveredDividerColumn(mouseX, mouseY);
        if (hoveredDivider >= 0) {
            context.method_25291(TEXTURE, 12 + hoveredDivider * 18, 54, 0, 196.0f, 36.0f, 8, 13, 256, 256);
        }
        for (int column = 0; column < 9; ++column) {
            class_1799 priceDisplay;
            class_1799 sellDisplay = this.getVirtualSellAmountDisplay(column);
            if (!sellDisplay.method_7960()) {
                context.method_51427(sellDisplay, 8 + column * 18, 36);
                context.method_51431(this.field_22793, sellDisplay, 8 + column * 18, 36);
            }
            if ((priceDisplay = ((MarketDisplayScreenHandler)this.field_2797).getPriceStack(column)).method_7960()) continue;
            context.method_51427(priceDisplay, 8 + column * 18, 70);
            context.method_51431(this.field_22793, priceDisplay, 8 + column * 18, 70);
        }
    }

    protected void method_2389(class_332 context, float delta, int mouseX, int mouseY) {
        context.method_25291(TEXTURE, this.field_2776, this.field_2800, 0, 0.0f, 0.0f, this.field_2792, this.field_2779, 256, 256);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        int amountColumn = this.getHoveredAmountColumn(mouseX, mouseY);
        if (amountColumn >= 0) {
            int buttonId;
            boolean shift = class_437.method_25442();
            int n = buttonId = button == 1 ? MarketDisplayScreenHandler.saleDecreaseButton(amountColumn, shift) : MarketDisplayScreenHandler.saleIncreaseButton(amountColumn, shift);
            if (this.field_22787 != null && this.field_22787.field_1761 != null) {
                this.field_22787.field_1761.method_2900(((MarketDisplayScreenHandler)this.field_2797).field_7763, buttonId);
                return true;
            }
            return false;
        }
        int priceColumn = this.getHoveredPriceColumn(mouseX, mouseY);
        if (priceColumn >= 0) {
            int buttonId;
            class_1799 priceStack = ((MarketDisplayScreenHandler)this.field_2797).getPriceStack(priceColumn);
            if (button == 1 && class_437.method_25442()) {
                this.openSearchScreen(priceColumn);
                return true;
            }
            if (priceStack.method_7960()) {
                this.openSearchScreen(priceColumn);
                return true;
            }
            int n = buttonId = button == 1 ? MarketDisplayScreenHandler.priceDecreaseButton(priceColumn) : MarketDisplayScreenHandler.priceIncreaseButton(priceColumn);
            if (this.field_22787 != null && this.field_22787.field_1761 != null) {
                this.field_22787.field_1761.method_2900(((MarketDisplayScreenHandler)this.field_2797).field_7763, buttonId);
                return true;
            }
            return false;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    protected void method_2380(class_332 context, int mouseX, int mouseY) {
        int dividerColumn = this.getHoveredDividerColumn(mouseX, mouseY);
        if (dividerColumn >= 0) {
            context.method_51434(this.field_22793, this.getDividerTooltip(dividerColumn), mouseX, mouseY);
            return;
        }
        int amountColumn = this.getHoveredAmountColumn(mouseX, mouseY);
        if (amountColumn >= 0) {
            context.method_51434(this.field_22793, this.getAmountTooltip(), mouseX, mouseY);
            return;
        }
        int priceColumn = this.getHoveredPriceColumn(mouseX, mouseY);
        if (priceColumn >= 0) {
            context.method_51434(this.field_22793, this.getPriceTooltip(priceColumn), mouseX, mouseY);
            return;
        }
        super.method_2380(context, mouseX, mouseY);
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.method_25420(context, mouseX, mouseY, delta);
        super.method_25394(context, mouseX, mouseY, delta);
        this.method_2380(context, mouseX, mouseY);
    }

    private class_1799 getVirtualSellAmountDisplay(int column) {
        class_1799 sellStack = ((MarketDisplayScreenHandler)this.field_2797).method_7611(column).method_7677();
        if (sellStack.method_7960()) {
            return class_1799.field_8037;
        }
        class_1799 display = sellStack.method_7972();
        display.method_7939(((MarketDisplayScreenHandler)this.field_2797).getSaleAmount(column));
        return display;
    }

    private List<class_2561> getDividerTooltip(int column) {
        ArrayList<class_2561> lines = new ArrayList<class_2561>();
        class_1799 sellStack = ((MarketDisplayScreenHandler)this.field_2797).method_7611(column).method_7677();
        class_1799 priceStack = ((MarketDisplayScreenHandler)this.field_2797).getPriceStack(column);
        lines.add((class_2561)(sellStack.method_7960() ? class_2561.method_43470((String)"Empty") : class_2561.method_43470((String)(((MarketDisplayScreenHandler)this.field_2797).getSaleAmount(column) + " " + sellStack.method_7964().getString()))));
        lines.add((class_2561)class_2561.method_43470((String)"Sell for"));
        lines.add((class_2561)(priceStack.method_7960() ? class_2561.method_43470((String)"Free") : class_2561.method_43470((String)(priceStack.method_7947() + " " + priceStack.method_7964().getString()))));
        return lines;
    }

    private List<class_2561> getPriceTooltip(int column) {
        ArrayList<class_2561> lines = new ArrayList<class_2561>();
        class_1799 priceStack = ((MarketDisplayScreenHandler)this.field_2797).getPriceStack(column);
        if (priceStack.method_7960()) {
            lines.add((class_2561)class_2561.method_43470((String)"Choose receive item"));
            lines.add((class_2561)class_2561.method_43470((String)"Click: open item search"));
            return lines;
        }
        lines.add((class_2561)class_2561.method_43470((String)(priceStack.method_7947() + " " + priceStack.method_7964().getString())));
        lines.add((class_2561)class_2561.method_43470((String)"Left click: increase amount"));
        lines.add((class_2561)class_2561.method_43470((String)"Right click: decrease amount"));
        lines.add((class_2561)class_2561.method_43470((String)"Shift + right click: change item"));
        return lines;
    }

    private List<class_2561> getAmountTooltip() {
        return List.of(class_2561.method_43470((String)"Left click: increase amount"), class_2561.method_43470((String)"Right click: decrease amount"), class_2561.method_43470((String)"Shift + left click: increase by 8"), class_2561.method_43470((String)"Shift + right click: decrease by 8"));
    }

    private int getHoveredAmountColumn(double mouseX, double mouseY) {
        return this.getHoveredColumn(mouseX, mouseY, 8, 36, 18, 18);
    }

    private int getHoveredPriceColumn(double mouseX, double mouseY) {
        return this.getHoveredColumn(mouseX, mouseY, 8, 70, 18, 18);
    }

    private int getHoveredDividerColumn(double mouseX, double mouseY) {
        return this.getHoveredColumn(mouseX, mouseY, 8, 54, 18, 16);
    }

    private int getHoveredColumn(double mouseX, double mouseY, int startX, int startY, int width, int height) {
        int localX = (int)mouseX - this.field_2776;
        int localY = (int)mouseY - this.field_2800;
        for (int column = 0; column < 9; ++column) {
            int columnX = startX + column * 18;
            if (localX < columnX || localX >= columnX + width || localY < startY || localY >= startY + height) continue;
            return column;
        }
        return -1;
    }

    private void openSearchScreen(int column) {
        if (this.field_22787 != null) {
            this.field_22787.method_1507((class_437)new MarketDisplaySearchScreen(this, column));
        }
    }

    @Environment(value=EnvType.CLIENT)
    private static final class MarketDisplaySearchScreen
    extends class_437 {
        private static final int GRID_COLUMNS = 9;
        private static final int GRID_ROWS = 5;
        private static final int SLOT_SIZE = 18;
        private static final int SEARCH_X = 82;
        private static final int SEARCH_Y = 6;
        private static final int SEARCH_WIDTH = 89;
        private static final int SEARCH_HEIGHT = 10;
        private static final int GRID_X = 9;
        private static final int GRID_Y = 18;
        private static final int SCROLL_X = 174;
        private static final int SCROLL_Y = 18;
        private static final int SCROLL_HEIGHT = 90;
        private static final int SCROLL_THUMB_HEIGHT = 15;
        private final MarketDisplayScreen parent;
        private final int column;
        private final List<class_1799> allItems = new ArrayList<class_1799>();
        private final List<class_1799> filteredItems = new ArrayList<class_1799>();
        private class_342 searchBox;
        private float scrollPosition;
        private boolean scrolling;
        private int left;
        private int top;

        private MarketDisplaySearchScreen(MarketDisplayScreen parent, int column) {
            super((class_2561)class_2561.method_43473());
            this.parent = parent;
            this.column = column;
        }

        protected void method_25426() {
            super.method_25426();
            this.left = (this.field_22789 - 195) / 2;
            this.top = (this.field_22790 - 166) / 2;
            this.searchBox = new class_342(this.field_22793, this.left + 82, this.top + 6, 89, 10, (class_2561)class_2561.method_43473());
            this.searchBox.method_1858(false);
            this.searchBox.method_1880(50);
            this.searchBox.method_1863(text -> this.refreshSearchResults());
            this.method_25429((class_364)this.searchBox);
            this.method_48265((class_364)this.searchBox);
            this.searchBox.method_25365(true);
            this.refreshAvailableItems();
            this.refreshSearchResults();
        }

        public void method_25419() {
            if (this.field_22787 != null) {
                this.field_22787.method_1507((class_437)this.parent);
            }
        }

        public boolean method_25421() {
            return false;
        }

        public boolean method_25400(char chr, int modifiers) {
            return this.searchBox.method_25400(chr, modifiers) || super.method_25400(chr, modifiers);
        }

        public boolean method_25404(int keyCode, int scanCode, int modifiers) {
            if (this.searchBox.method_25404(keyCode, scanCode, modifiers)) {
                return true;
            }
            if (keyCode == 256) {
                this.method_25419();
                return true;
            }
            return super.method_25404(keyCode, scanCode, modifiers);
        }

        public boolean method_25402(double mouseX, double mouseY, int button) {
            if (this.searchBox.method_25402(mouseX, mouseY, button)) {
                return true;
            }
            int hoveredIndex = this.getHoveredItemIndex(mouseX, mouseY);
            if (hoveredIndex >= 0 && hoveredIndex < this.getVisibleItems().size()) {
                class_1799 chosen = this.getVisibleItems().get(hoveredIndex);
                class_310 client = this.field_22787;
                if (client != null && client.field_1761 != null) {
                    client.field_1761.method_2900(((MarketDisplayScreenHandler)((MarketDisplayScreen)this.parent).field_2797).field_7763, MarketDisplayScreenHandler.selectPriceButton(this.column, chosen.method_7909()));
                    client.method_1507((class_437)this.parent);
                }
                return true;
            }
            if (this.isInScrollbar(mouseX, mouseY) && this.hasScrollbar()) {
                this.scrolling = true;
                return true;
            }
            return super.method_25402(mouseX, mouseY, button);
        }

        public boolean method_25406(double mouseX, double mouseY, int button) {
            this.scrolling = false;
            return super.method_25406(mouseX, mouseY, button);
        }

        public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
            if (this.scrolling && this.hasScrollbar()) {
                this.scrollPosition = (float)((mouseY - (double)(this.top + 18) - 7.5) / 75.0);
                this.scrollPosition = Math.max(0.0f, Math.min(1.0f, this.scrollPosition));
                return true;
            }
            return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
        }

        public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
            if (!this.hasScrollbar()) {
                return false;
            }
            int hiddenRows = this.getHiddenRows();
            this.scrollPosition = (float)((double)this.scrollPosition - verticalAmount / (double)hiddenRows);
            this.scrollPosition = Math.max(0.0f, Math.min(1.0f, this.scrollPosition));
            return true;
        }

        public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
            this.parent.method_2389(context, delta, mouseX, mouseY);
            context.method_25291(SEARCH_TEXTURE, this.left, this.top, 0, 0.0f, 0.0f, 195, 166, 256, 256);
            this.searchBox.method_25394(context, mouseX, mouseY, delta);
            List<class_1799> visibleItems = this.getVisibleItems();
            int hoveredIndex = this.getHoveredItemIndex(mouseX, mouseY);
            for (int index = 0; index < visibleItems.size(); ++index) {
                int row = index / 9;
                int column = index % 9;
                int itemX = this.left + 9 + column * 18;
                int itemY = this.top + 18 + row * 18;
                if (index == hoveredIndex) {
                    context.method_25294(itemX, itemY, itemX + 16, itemY + 16, -2130706433);
                }
                context.method_51427(visibleItems.get(index), itemX, itemY);
                context.method_51431(this.field_22793, visibleItems.get(index), itemX, itemY);
            }
            this.renderScrollbar(context);
            if (hoveredIndex >= 0 && hoveredIndex < visibleItems.size()) {
                context.method_51446(this.field_22793, visibleItems.get(hoveredIndex), mouseX, mouseY);
            }
        }

        private void refreshAvailableItems() {
            this.allItems.clear();
            for (class_1792 item : class_7923.field_41178) {
                if (item == class_1802.field_8162) continue;
                this.allItems.add(new class_1799((class_1935)item));
            }
        }

        private void refreshSearchResults() {
            String query;
            this.filteredItems.clear();
            String string = query = this.searchBox == null ? "" : this.searchBox.method_1882().trim().toLowerCase(Locale.ROOT);
            if (query.isEmpty()) {
                this.filteredItems.addAll(this.allItems);
            } else {
                boolean tagSearch = query.startsWith("#");
                String filteredQuery = tagSearch ? query.substring(1) : query;
                for (class_1799 stack : this.allItems) {
                    if (!this.matchesQuery(stack, filteredQuery, tagSearch)) continue;
                    this.filteredItems.add(stack);
                }
            }
            this.scrollPosition = 0.0f;
        }

        private boolean matchesQuery(class_1799 stack, String query, boolean tagSearch) {
            if (query.isBlank()) {
                return true;
            }
            if (tagSearch) {
                return class_7923.field_41178.method_47983((Object)stack.method_7909()).method_40228().anyMatch(tag -> tag.comp_327().toString().toLowerCase(Locale.ROOT).contains(query));
            }
            String displayName = stack.method_7964().getString().toLowerCase(Locale.ROOT);
            if (displayName.contains(query)) {
                return true;
            }
            return class_7923.field_41178.method_10221((Object)stack.method_7909()).toString().toLowerCase(Locale.ROOT).contains(query);
        }

        private void renderScrollbar(class_332 context) {
            class_2960 texture = this.hasScrollbar() ? SEARCH_SCROLLER_TEXTURE : SEARCH_SCROLLER_DISABLED_TEXTURE;
            int thumbY = this.top + 18 + (this.hasScrollbar() ? (int)(75.0f * this.scrollPosition) : 0);
            context.method_52706(texture, this.left + 174, thumbY, 12, 15);
            if (!this.hasScrollbar()) {
                return;
            }
        }

        private List<class_1799> getVisibleItems() {
            int startRow = (int)(this.scrollPosition * (float)this.getHiddenRows() + 0.5f);
            int startIndex = startRow * 9;
            int endIndex = Math.min(startIndex + 45, this.filteredItems.size());
            return this.filteredItems.subList(startIndex, endIndex);
        }

        private int getHoveredItemIndex(double mouseX, double mouseY) {
            int localX = (int)mouseX - this.left;
            int localY = (int)mouseY - this.top;
            if (localX < 9 || localY < 18) {
                return -1;
            }
            int column = (localX - 9) / 18;
            int row = (localY - 18) / 18;
            if (column < 0 || column >= 9 || row < 0 || row >= 5) {
                return -1;
            }
            int visibleIndex = row * 9 + column;
            if (visibleIndex >= this.getVisibleItems().size()) {
                return -1;
            }
            return visibleIndex;
        }

        private int getHiddenRows() {
            return Math.max(0, (this.filteredItems.size() + 9 - 1) / 9 - 5);
        }

        private boolean hasScrollbar() {
            return this.filteredItems.size() > 45;
        }

        private boolean isInScrollbar(double mouseX, double mouseY) {
            return mouseX >= (double)(this.left + 174) && mouseX < (double)(this.left + 174 + 12) && mouseY >= (double)(this.top + 18) && mouseY < (double)(this.top + 18 + 90);
        }
    }
}

