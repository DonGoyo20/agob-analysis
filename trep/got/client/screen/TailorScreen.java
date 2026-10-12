/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_1011
 *  net.minecraft.class_1043
 *  net.minecraft.class_1044
 *  net.minecraft.class_1309
 *  net.minecraft.class_156
 *  net.minecraft.class_1767
 *  net.minecraft.class_1769
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_437
 *  net.minecraft.class_490
 *  net.minecraft.class_5348
 *  net.minecraft.class_8685
 *  net.minecraft.class_8710
 *  net.minecraft.class_8786
 */
package trep.got.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_1044;
import net.minecraft.class_1309;
import net.minecraft.class_156;
import net.minecraft.class_1767;
import net.minecraft.class_1769;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_490;
import net.minecraft.class_5348;
import net.minecraft.class_8685;
import net.minecraft.class_8710;
import net.minecraft.class_8786;
import trep.got.blacksmith.BlacksmithRecipeIngredient;
import trep.got.character.PlayerSkinBuilderProfile;
import trep.got.client.character.CharacterPreviewPlayerEntity;
import trep.got.client.character.PlayerSkinBuilderClothingRegistry;
import trep.got.client.character.PlayerSkinBuilderSkinResolver;
import trep.got.client.gui.PartyMenuPanelRenderer;
import trep.got.client.tailor.TailorClientState;
import trep.got.client.util.ClientTextureHelper;
import trep.got.network.RequestTailorActionPayload;
import trep.got.registry.GotRecipes;
import trep.got.skill.SkillAttributeType;
import trep.got.tailor.TailorRecipe;

@Environment(value=EnvType.CLIENT)
public final class TailorScreen
extends class_437 {
    private static final int CLOTHING_PANEL_SOURCE_WIDTH = 74;
    private static final int CLOTHING_PANEL_SOURCE_HEIGHT = 96;
    private static final int RESOURCE_PANEL_SOURCE_WIDTH = 64;
    private static final int RESOURCE_PANEL_SOURCE_HEIGHT = 96;
    private static final int OPTION_SOURCE_WIDTH = 32;
    private static final int OPTION_SOURCE_HEIGHT = 48;
    private static final int CRAFT_SOURCE_WIDTH = 48;
    private static final int CRAFT_SOURCE_HEIGHT = 22;
    private static final int TYPE_BUTTON_SOURCE_SIZE = 22;
    private static final float PANEL_SIZE_SCALE = 0.75f;
    private static final float RESOURCE_CONTENT_SCALE = 0.675f;
    private static final int DYE_SLOT_SIZE = 16;
    private static final int DYE_SLOT_GAP = 2;
    private static final float CLOTHING_VIEWPORT_LEFT = 5.0f;
    private static final float CLOTHING_VIEWPORT_RIGHT = 69.0f;
    private static final float CLOTHING_VIEWPORT_TOP = 5.0f;
    private static final float CLOTHING_VIEWPORT_BOTTOM = 90.0f;
    private static final class_2960 PANEL_CLOTHING = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/panel_clothing_options.png");
    private static final class_2960 PANEL_RESOURCES = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/panel_resources.png");
    private static final class_2960 OPTION = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/clothing_option.png");
    private static final class_2960 OPTION_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/clothing_option_hover.png");
    private static final class_2960 OPTION_SELECTED = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/clothing_option_selected.png");
    private static final class_2960 OPTION_LOCKED = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/clothing_option_locked.png");
    private static final class_2960 OPTION_LOCKED_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/clothing_option_locked_hover.png");
    private static final class_2960 OPTION_UNDISCOVERED = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/clothing_option_undiscovered.png");
    private static final class_2960 OPTION_UNDISCOVERED_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/clothing_option_undiscovered_hover.png");
    private static final class_2960 CRAFT = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/craft.png");
    private static final class_2960 CRAFT_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/craft_hover.png");
    private static final class_2960 CRAFT_LOCKED = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/craft_locked.png");
    private static final class_2960 TYPE_BUTTON = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/type_button.png");
    private static final class_2960 TYPE_BUTTON_ACTIVE = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/type_button_active.png");
    private static final class_2960 TYPE_ALL = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/all.png");
    private static final class_2960 TYPE_SHIRT = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/shirt.png");
    private static final class_2960 TYPE_PANTS = class_2960.method_60655((String)"agob", (String)"textures/ui/tailor/pants.png");
    private static final class_2960 TRANSPARENT_SKIN_ID = class_2960.method_60655((String)"agob", (String)"tailor/transparent_skin");
    private static boolean transparentSkinRegistered;
    private ClothingFilter clothingFilter = ClothingFilter.ALL;
    private String selectedSlot = "shirt";
    private String selectedClothingId = "";
    private String selectedDyeId = "none";
    private int scrollOffset;
    private boolean scrollbarDragging;
    private double scrollbarDragOffset;
    private long nearbyLoomCacheTick = Long.MIN_VALUE;
    private long nearbyLoomCachePos = Long.MIN_VALUE;
    private boolean nearbyLoomCacheValue;

    public TailorScreen() {
        super((class_2561)class_2561.method_43471((String)"screen.agob.tailor"));
        PlayerSkinBuilderProfile profile = TailorClientState.getSkinBuilderProfile();
        if (!profile.shirtId().isBlank()) {
            this.selectedSlot = "shirt";
            this.selectedClothingId = profile.shirtId();
            this.selectedDyeId = profile.shirtDyeId();
        } else if (!profile.pantsId().isBlank()) {
            this.selectedSlot = "pants";
            this.selectedClothingId = profile.pantsId();
            this.selectedDyeId = profile.pantsDyeId();
        }
    }

    public boolean method_25421() {
        return false;
    }

    protected void method_57734(float delta) {
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        TailorTooltip topTooltip;
        super.method_25420(context, mouseX, mouseY, delta);
        Layout layout = this.layout();
        TailorScreen.drawTexture(context, PANEL_CLOTHING, layout.leftX, layout.panelY, layout.panelW, layout.panelH);
        TailorScreen.drawTexture(context, PANEL_RESOURCES, layout.rightX, layout.panelY, layout.resourceW, layout.panelH);
        TailorTooltip typeTooltip = this.renderTypeButtons(context, layout, mouseX, mouseY);
        TailorTooltip tooltip = this.renderClothingOptions(context, layout, mouseX, mouseY);
        this.renderScrollbar(context, layout, mouseX, mouseY);
        this.renderPlayerPreview(context, layout, mouseX, mouseY);
        TailorTooltip dyeTooltip = this.renderDyeSlots(context, layout, mouseX, mouseY);
        TailorTooltip craftTooltip = this.renderCraftButton(context, layout, mouseX, mouseY);
        TailorTooltip resourceTooltip = this.renderResources(context, layout, mouseX, mouseY);
        TailorTooltip tailorTooltip = resourceTooltip != null ? resourceTooltip : (craftTooltip != null ? craftTooltip : (dyeTooltip != null ? dyeTooltip : (topTooltip = tooltip != null ? tooltip : typeTooltip)));
        if (topTooltip != null) {
            topTooltip.draw(context, this.field_22793, mouseX, mouseY);
        }
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        ClothingEntry selected;
        Iterator<DyeSlot> scrollbar;
        if (button != 0) {
            return super.method_25402(mouseX, mouseY, button);
        }
        Layout layout = this.layout();
        for (TypeButtonBounds typeButtonBounds : this.typeButtonBounds(layout)) {
            if (!TailorScreen.isWithin(mouseX, mouseY, typeButtonBounds.x, typeButtonBounds.y, typeButtonBounds.size, typeButtonBounds.size)) continue;
            this.clothingFilter = typeButtonBounds.filter;
            this.scrollOffset = 0;
            this.scrollbarDragging = false;
            this.ensureSelectedEntryVisible();
            return true;
        }
        if (this.needsScrollbar() && TailorScreen.isWithin(mouseX, mouseY, ((ScrollbarMetrics)((Object)(scrollbar = this.scrollbarMetrics(layout)))).x(), ((ScrollbarMetrics)((Object)scrollbar)).y(), ((ScrollbarMetrics)((Object)scrollbar)).width(), ((ScrollbarMetrics)((Object)scrollbar)).height())) {
            if (TailorScreen.isWithin(mouseX, mouseY, ((ScrollbarMetrics)((Object)scrollbar)).x(), ((ScrollbarMetrics)((Object)scrollbar)).thumbY(), ((ScrollbarMetrics)((Object)scrollbar)).width(), ((ScrollbarMetrics)((Object)scrollbar)).thumbHeight())) {
                this.scrollbarDragging = true;
                this.scrollbarDragOffset = mouseY - (double)((ScrollbarMetrics)((Object)scrollbar)).thumbY();
            } else {
                this.setScrollFromThumbCenter(mouseY, ((ScrollbarMetrics)((Object)scrollbar)).y(), ((ScrollbarMetrics)((Object)scrollbar)).thumbHeight());
            }
            return true;
        }
        for (EntryBounds entryBounds : this.entryBounds(layout)) {
            if (!TailorScreen.isWithin(mouseX, mouseY, entryBounds.x, entryBounds.y, entryBounds.w, entryBounds.h)) continue;
            ClothingEntry entry = entryBounds.entry;
            if (!entry.discovered()) {
                return true;
            }
            this.selectedSlot = entry.slot();
            this.selectedClothingId = entry.id();
            String string = this.selectedDyeId = entry.isEquipped(TailorClientState.getSkinBuilderProfile()) ? entry.equippedDyeId(TailorClientState.getSkinBuilderProfile()) : "none";
            if (this.isDyeUnlocked(entry)) {
                ClientPlayNetworking.send((class_8710)new RequestTailorActionPayload("equip", entry.slot(), entry.id(), this.selectedDyeId));
            }
            return true;
        }
        for (DyeSlot dyeSlot : this.dyeSlots(layout)) {
            if (!TailorScreen.isWithin(mouseX, mouseY, dyeSlot.x, dyeSlot.y, 16, 16)) continue;
            ClothingEntry selected2 = this.selectedEntry();
            this.selectedDyeId = dyeSlot.dyeId;
            if (selected2 != null && selected2.discovered() && this.isDyeUnlocked(selected2)) {
                ClientPlayNetworking.send((class_8710)new RequestTailorActionPayload("equip", selected2.slot(), selected2.id(), this.selectedDyeId));
            }
            return true;
        }
        if (TailorScreen.isWithin(mouseX, mouseY, layout.craftX, layout.craftY, layout.craftW, layout.craftH) && (selected = this.selectedEntry()) != null && selected.discovered()) {
            boolean bl = this.isDyeUnlocked(selected);
            if (bl || this.canCraft(selected)) {
                ClientPlayNetworking.send((class_8710)new RequestTailorActionPayload(bl ? "equip" : "craft", selected.slot(), selected.id(), this.selectedDyeId));
            }
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        boolean overScrollbar;
        Layout layout = this.layout();
        boolean overPanel = TailorScreen.isWithin(mouseX, mouseY, layout.leftX, layout.panelY, layout.panelW, layout.panelH);
        ScrollbarMetrics scrollbar = this.scrollbarMetrics(layout);
        boolean bl = overScrollbar = this.needsScrollbar() && TailorScreen.isWithin(mouseX, mouseY, scrollbar.x(), scrollbar.y(), scrollbar.width(), scrollbar.height());
        if (!overPanel && !overScrollbar) {
            return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
        }
        this.scrollOffset = class_3532.method_15340((int)(this.scrollOffset - (int)Math.signum(verticalAmount)), (int)0, (int)this.maxScrollRows());
        return true;
    }

    public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.scrollbarDragging) {
            Layout layout = this.layout();
            if (!this.needsScrollbar()) {
                this.scrollbarDragging = false;
                this.scrollOffset = 0;
                return true;
            }
            ScrollbarMetrics scrollbar = this.scrollbarMetrics(layout);
            this.setScrollFromThumbY(mouseY - this.scrollbarDragOffset, scrollbar.y(), scrollbar.thumbHeight());
            return true;
        }
        return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        this.scrollbarDragging = false;
        return super.method_25406(mouseX, mouseY, button);
    }

    private TailorTooltip renderClothingOptions(class_332 context, Layout layout, int mouseX, int mouseY) {
        TailorTooltip tooltip = null;
        context.method_44379(layout.innerLeftX, layout.innerY, layout.innerRightX, layout.innerBottomY);
        for (EntryBounds bounds : this.entryBounds(layout)) {
            CharacterPreviewPlayerEntity preview;
            ClothingEntry entry = bounds.entry;
            boolean hovered = TailorScreen.isWithin(mouseX, mouseY, bounds.x, bounds.y, bounds.w, bounds.h);
            boolean equipped = entry.isEquipped(TailorClientState.getSkinBuilderProfile());
            class_2960 texture = TailorScreen.optionTexture(entry, hovered, equipped);
            TailorScreen.drawTexture(context, texture, bounds.x, bounds.y, bounds.w, bounds.h);
            if (entry.discovered() && (preview = this.clothingPreview(entry)) != null) {
                class_490.method_2486((class_332)context, (int)(bounds.x + 4), (int)(bounds.y + 4), (int)(bounds.x + bounds.w - 4), (int)(bounds.y + bounds.h - 4), (int)Math.max(18, bounds.h / 2), (float)0.0f, (float)mouseX, (float)mouseY, (class_1309)preview);
            }
            if (!hovered) continue;
            tooltip = entry.discovered() ? TailorTooltip.clothing((class_2561)class_2561.method_43470((String)entry.label()), TailorClientState.getClothingAttributes(entry.id())) : TailorTooltip.text((class_2561)class_2561.method_43470((String)"Undiscovered"));
        }
        context.method_44380();
        return tooltip;
    }

    private TailorTooltip renderTypeButtons(class_332 context, Layout layout, int mouseX, int mouseY) {
        TailorTooltip tooltip = null;
        float cycleTime = (float)(class_156.method_658() % 24000L) / 1000.0f;
        for (TypeButtonBounds bounds : this.typeButtonBounds(layout)) {
            boolean active = bounds.filter == this.clothingFilter;
            boolean hovered = TailorScreen.isWithin(mouseX, mouseY, bounds.x, bounds.y, bounds.size, bounds.size);
            TailorScreen.drawTexture(context, active ? TYPE_BUTTON_ACTIVE : TYPE_BUTTON, bounds.x, bounds.y, bounds.size, bounds.size);
            TailorScreen.drawCenteredTexture(context, bounds.filter.icon, bounds.x, bounds.y, bounds.size, bounds.size, bounds.filter.red(cycleTime), bounds.filter.green(cycleTime), bounds.filter.blue(cycleTime));
            if (!hovered) continue;
            tooltip = TailorTooltip.text(bounds.filter.tooltip);
        }
        return tooltip;
    }

    private void renderPlayerPreview(class_332 context, Layout layout, int mouseX, int mouseY) {
        if (this.field_22787 == null || this.field_22787.field_1687 == null) {
            return;
        }
        CharacterPreviewPlayerEntity preview = PlayerSkinBuilderSkinResolver.createPreviewEntity(this.field_22787.field_1687, this.previewProfile());
        if (preview != null) {
            class_490.method_2486((class_332)context, (int)layout.playerX, (int)layout.playerY, (int)layout.playerRightX, (int)layout.playerBottomY, (int)Math.max(42, layout.playerH / 2), (float)0.0f, (float)mouseX, (float)mouseY, (class_1309)preview);
        }
    }

    private TailorTooltip renderDyeSlots(class_332 context, Layout layout, int mouseX, int mouseY) {
        TailorTooltip tooltip = null;
        for (DyeSlot dyeSlot : this.dyeSlots(layout)) {
            boolean hovered = TailorScreen.isWithin(mouseX, mouseY, dyeSlot.x, dyeSlot.y, 16, 16);
            TailorScreen.drawTexture(context, dyeSlot.texture, dyeSlot.x, dyeSlot.y, 16, 16);
            context.method_51427(dyeSlot.stack, dyeSlot.x, dyeSlot.y);
            if (dyeSlot.dyeId.equals(this.selectedDyeId)) {
                TailorScreen.drawOutline(context, dyeSlot.x - 1, dyeSlot.y - 1, 18, 18, -1);
            }
            if (!hovered) continue;
            context.method_25294(dyeSlot.x, dyeSlot.y, dyeSlot.x + 16, dyeSlot.y + 16, 0x55FFFFFF);
            tooltip = dyeSlot.tooltip;
        }
        return tooltip;
    }

    private TailorTooltip renderCraftButton(class_332 context, Layout layout, int mouseX, int mouseY) {
        boolean hovered;
        ClothingEntry selected = this.selectedEntry();
        boolean unlocked = this.isDyeUnlocked(selected);
        boolean craftable = selected != null && selected.discovered() && this.canCraft(selected);
        boolean enabled = selected != null && selected.discovered() && (unlocked || craftable);
        boolean bl = hovered = enabled && TailorScreen.isWithin(mouseX, mouseY, layout.craftX, layout.craftY, layout.craftW, layout.craftH);
        class_2960 texture = enabled ? (hovered ? CRAFT_HOVER : CRAFT) : CRAFT_LOCKED;
        TailorScreen.drawTexture(context, texture, layout.craftX, layout.craftY, layout.craftW, layout.craftH);
        if (TailorScreen.isWithin(mouseX, mouseY, layout.craftX, layout.craftY, layout.craftW, layout.craftH) && selected != null && selected.discovered()) {
            return TailorTooltip.text((class_2561)class_2561.method_43470((String)(unlocked ? "Equip" : "Craft")));
        }
        return null;
    }

    private TailorTooltip renderResources(class_332 context, Layout layout, int mouseX, int mouseY) {
        ClothingEntry selected = this.selectedEntry();
        if (selected == null || !selected.discovered() || this.isDyeUnlocked(selected)) {
            return null;
        }
        Optional<TailorRecipe> recipe = this.findRecipe(selected);
        if (recipe.isEmpty()) {
            return null;
        }
        TailorTooltip tooltip = null;
        int y = layout.panelY + TailorScreen.panelScaled(22);
        int lineHeight = TailorScreen.resourceLineHeight();
        int iconSize = TailorScreen.resourceIconSize();
        int textOffsetX = TailorScreen.resourceScaled(22);
        int textOffsetY = TailorScreen.resourceScaled(5);
        int resourceX = layout.rightX + TailorScreen.panelScaled(18);
        for (BlacksmithRecipeIngredient ingredient : recipe.get().ingredients()) {
            class_1799 stack = ingredient.createPreviewStack();
            if (stack.method_7960()) continue;
            class_1799 iconStack = stack.method_46651(1);
            int x = resourceX;
            TailorScreen.drawScaledItem(context, iconStack, x, y, 0.675f);
            int has = this.countInInventory(ingredient);
            TailorScreen.drawScaledText(context, this.field_22793, has + " / " + ingredient.count(), x + textOffsetX, y + textOffsetY, has >= ingredient.count() ? -1 : -34953, 0.675f);
            if (TailorScreen.isWithin(mouseX, mouseY, x, y, iconSize, iconSize)) {
                tooltip = TailorTooltip.item(iconStack);
            }
            y += lineHeight;
        }
        class_1799 dyeStack = this.selectedDyeStack();
        if (!dyeStack.method_7960()) {
            int x = resourceX;
            TailorScreen.drawScaledItem(context, dyeStack, x, y, 0.675f);
            int has = this.countDyeInInventory(this.selectedDyeId);
            TailorScreen.drawScaledText(context, this.field_22793, has + " / 4", x + textOffsetX, y + textOffsetY, has >= 4 ? -1 : -34953, 0.675f);
            if (TailorScreen.isWithin(mouseX, mouseY, x, y, iconSize, iconSize)) {
                tooltip = TailorTooltip.item(dyeStack);
            }
            y += lineHeight;
        }
        class_1799 loomStack = new class_1799((class_1935)class_2246.field_10083);
        int x = resourceX;
        boolean nearbyLoom = this.hasNearbyLoom();
        String loomText = "4 Block near";
        TailorScreen.drawScaledText(context, this.field_22793, loomText, x, y + textOffsetY, nearbyLoom ? -1 : -34953, 0.675f);
        int loomIconX = x + Math.round((float)this.field_22793.method_1727(loomText) * 0.675f) + 5;
        TailorScreen.drawScaledItem(context, loomStack, loomIconX, y, 0.675f);
        if (TailorScreen.isWithin(mouseX, mouseY, loomIconX, y, iconSize, iconSize)) {
            tooltip = TailorTooltip.item(loomStack);
        }
        return tooltip;
    }

    private List<EntryBounds> entryBounds(Layout layout) {
        int startIndex;
        List<ClothingEntry> entries = this.entries();
        ArrayList<EntryBounds> bounds = new ArrayList<EntryBounds>();
        this.scrollOffset = class_3532.method_15340((int)this.scrollOffset, (int)0, (int)TailorScreen.maxScrollRows(entries));
        for (int index = startIndex = this.scrollOffset * 2; index < entries.size(); ++index) {
            int w;
            int visibleIndex = index - startIndex;
            int col = visibleIndex % 2;
            int row = visibleIndex / 2;
            int x = layout.innerLeftX + col * layout.cardW;
            int y = layout.innerY + row * layout.cardH;
            int n = w = col == 1 ? layout.innerRightX - x : layout.cardW;
            if (y >= layout.innerBottomY) break;
            bounds.add(new EntryBounds(entries.get(index), x, y, w, layout.cardH));
        }
        return bounds;
    }

    private List<ClothingEntry> entries() {
        ClothingEntry entry;
        ArrayList<ClothingEntry> entries = new ArrayList<ClothingEntry>();
        boolean nearbyLoom = this.hasNearbyLoom();
        if (this.clothingFilter.includes("shirt")) {
            for (PlayerSkinBuilderClothingRegistry.ClothingOption option : PlayerSkinBuilderClothingRegistry.shirts()) {
                entry = ClothingEntry.of(option, "shirt");
                entries.add(entry.withSortRank(this.entrySortRank(entry, nearbyLoom)));
            }
        }
        if (this.clothingFilter.includes("pants")) {
            for (PlayerSkinBuilderClothingRegistry.ClothingOption option : PlayerSkinBuilderClothingRegistry.pants()) {
                entry = ClothingEntry.of(option, "pants");
                entries.add(entry.withSortRank(this.entrySortRank(entry, nearbyLoom)));
            }
        }
        entries.sort((first, second) -> Integer.compare(first.sortRank(), second.sortRank()));
        return entries;
    }

    private int entrySortRank(ClothingEntry entry, boolean nearbyLoom) {
        if (entry.unlocked() || this.hasCraftRequirements(entry, nearbyLoom)) {
            return 0;
        }
        if (entry.discovered()) {
            return 1;
        }
        return 2;
    }

    private int totalRows() {
        return (this.entries().size() + 1) / 2;
    }

    private int maxScrollRows() {
        return TailorScreen.maxScrollRows(this.entries());
    }

    private static int maxScrollRows(List<ClothingEntry> entries) {
        int rows = (entries.size() + 1) / 2;
        return Math.max(0, rows - 2);
    }

    private ClothingEntry selectedEntry() {
        return this.entries().stream().filter(entry -> entry.slot().equals(this.selectedSlot) && entry.id().equals(this.selectedClothingId)).findFirst().orElse(null);
    }

    private void ensureSelectedEntryVisible() {
        if (this.selectedEntry() != null) {
            return;
        }
        List<ClothingEntry> entries = this.entries();
        if (entries.isEmpty()) {
            this.selectedClothingId = "";
            this.selectedDyeId = "none";
            return;
        }
        PlayerSkinBuilderProfile profile = TailorClientState.getSkinBuilderProfile();
        ClothingEntry equipped = entries.stream().filter(entry -> entry.isEquipped(profile)).findFirst().orElse(entries.getFirst());
        this.selectedSlot = equipped.slot();
        this.selectedClothingId = equipped.id();
        this.selectedDyeId = equipped.isEquipped(profile) ? equipped.equippedDyeId(profile) : "none";
    }

    private CharacterPreviewPlayerEntity clothingPreview(ClothingEntry entry) {
        class_310 client = class_310.method_1551();
        if (client.field_1687 == null || client.field_1724 == null) {
            return null;
        }
        TailorScreen.registerTransparentSkin(client);
        class_8685 skin = new class_8685(TRANSPARENT_SKIN_ID, "", null, null, client.field_1724.method_52814().comp_1629(), true);
        PlayerSkinBuilderProfile base = new PlayerSkinBuilderProfile(false, TailorClientState.getSkinBuilderProfile().gender(), 1, "", "", "", "");
        String dyeId = this.previewDyeId(entry);
        PlayerSkinBuilderProfile profile = "shirt".equals(entry.slot()) ? base.withShirt(entry.id(), dyeId) : base.withPants(entry.id(), dyeId);
        return new CharacterPreviewPlayerEntity(client.field_1687, skin, client.field_1724, profile);
    }

    private PlayerSkinBuilderProfile previewProfile() {
        PlayerSkinBuilderProfile profile = TailorClientState.getSkinBuilderProfile();
        ClothingEntry selected = this.selectedEntry();
        if (selected == null || !selected.discovered()) {
            return profile;
        }
        return "shirt".equals(selected.slot()) ? profile.withShirt(selected.id(), this.selectedDyeId) : profile.withPants(selected.id(), this.selectedDyeId);
    }

    private String previewDyeId(ClothingEntry entry) {
        if (entry.slot().equals(this.selectedSlot) && entry.id().equals(this.selectedClothingId)) {
            return this.selectedDyeId;
        }
        if (entry.isEquipped(TailorClientState.getSkinBuilderProfile())) {
            return entry.equippedDyeId(TailorClientState.getSkinBuilderProfile());
        }
        return "none";
    }

    private Optional<TailorRecipe> findRecipe(ClothingEntry entry) {
        if (this.field_22787 == null || this.field_22787.field_1687 == null) {
            return Optional.empty();
        }
        return this.field_22787.field_1687.method_8433().method_30027(GotRecipes.TAILOR_TYPE).stream().map(class_8786::comp_1933).filter(recipe -> recipe.matches(entry.slot(), entry.id())).findFirst();
    }

    private boolean canCraft(ClothingEntry entry) {
        if (entry == null || !entry.discovered()) {
            return false;
        }
        if (!this.hasCraftRequirements(entry, this.hasNearbyLoom())) {
            return false;
        }
        class_1799 dyeStack = this.selectedDyeStack();
        return dyeStack.method_7960() || this.countDyeInInventory(this.selectedDyeId) >= 4;
    }

    private boolean hasCraftRequirements(ClothingEntry entry, boolean nearbyLoom) {
        if (entry == null || !entry.discovered() || !nearbyLoom) {
            return false;
        }
        Optional<TailorRecipe> recipe = this.findRecipe(entry);
        if (recipe.isEmpty()) {
            return false;
        }
        for (BlacksmithRecipeIngredient ingredient : recipe.get().ingredients()) {
            if (this.countInInventory(ingredient) >= ingredient.count()) continue;
            return false;
        }
        return true;
    }

    private boolean hasNearbyLoom() {
        if (this.field_22787 == null || this.field_22787.field_1687 == null || this.field_22787.field_1724 == null) {
            return false;
        }
        class_2338 origin = this.field_22787.field_1724.method_24515();
        long tick = this.field_22787.field_1687.method_8510();
        long posKey = origin.method_10063();
        if (this.nearbyLoomCacheTick == tick && this.nearbyLoomCachePos == posKey) {
            return this.nearbyLoomCacheValue;
        }
        this.nearbyLoomCacheTick = tick;
        this.nearbyLoomCachePos = posKey;
        for (class_2338 pos : class_2338.method_25996((class_2338)origin, (int)4, (int)4, (int)4)) {
            if (!this.field_22787.field_1687.method_8320(pos).method_27852(class_2246.field_10083)) continue;
            this.nearbyLoomCacheValue = true;
            return true;
        }
        this.nearbyLoomCacheValue = false;
        return false;
    }

    private int countInInventory(BlacksmithRecipeIngredient ingredient) {
        if (this.field_22787 == null || this.field_22787.field_1724 == null) {
            return 0;
        }
        int count = 0;
        for (int slot = 0; slot < this.field_22787.field_1724.method_31548().method_5439(); ++slot) {
            class_1799 stack = this.field_22787.field_1724.method_31548().method_5438(slot);
            if (!ingredient.ingredient().method_8093(stack)) continue;
            count += stack.method_7947();
        }
        return count;
    }

    private int countDyeInInventory(String dyeId) {
        if (this.field_22787 == null || this.field_22787.field_1724 == null) {
            return 0;
        }
        class_1767 color = class_1767.method_7793((String)dyeId, null);
        if (color == null) {
            return 0;
        }
        class_1769 dyeItem = class_1769.method_7803((class_1767)color);
        int count = 0;
        for (int slot = 0; slot < this.field_22787.field_1724.method_31548().method_5439(); ++slot) {
            class_1799 stack = this.field_22787.field_1724.method_31548().method_5438(slot);
            if (!stack.method_31574((class_1792)dyeItem)) continue;
            count += stack.method_7947();
        }
        return count;
    }

    private class_1799 selectedDyeStack() {
        class_1767 color = class_1767.method_7793((String)this.selectedDyeId, null);
        return color == null ? class_1799.field_8037 : new class_1799((class_1935)class_1769.method_7803((class_1767)color));
    }

    private boolean isDyeUnlocked(ClothingEntry entry) {
        return entry != null && TailorClientState.isDyeUnlocked(entry.slot(), entry.id(), this.selectedDyeId);
    }

    private static class_2960 optionTexture(ClothingEntry entry, boolean hovered, boolean equipped) {
        if (!entry.discovered()) {
            return hovered ? OPTION_UNDISCOVERED_HOVER : OPTION_UNDISCOVERED;
        }
        if (equipped) {
            return OPTION_SELECTED;
        }
        if (!entry.unlocked()) {
            return hovered ? OPTION_LOCKED_HOVER : OPTION_LOCKED;
        }
        return hovered ? OPTION_HOVER : OPTION;
    }

    private static void registerTransparentSkin(class_310 client) {
        if (transparentSkinRegistered) {
            return;
        }
        client.method_1531().method_4616(TRANSPARENT_SKIN_ID, (class_1044)new class_1043(new class_1011(64, 64, true)));
        transparentSkinRegistered = true;
    }

    private Layout layout() {
        float baseScale = Math.max(1.0f, Math.min((float)this.field_22789 * 0.3f / 74.0f, (float)this.field_22790 * 0.7f / 96.0f));
        float scale = baseScale * 0.75f;
        int panelW = Math.round(74.0f * scale);
        int panelH = Math.round(96.0f * scale);
        int resourceW = Math.round(64.0f * scale);
        int panelY = Math.round((float)this.field_22790 * 0.15f);
        int leftX = Math.round((float)this.field_22789 * 0.05f);
        int rightX = Math.round((float)this.field_22789 * 0.65f);
        int innerLeftX = leftX + Math.round(5.0f * scale);
        int innerRightX = leftX + Math.round(69.0f * scale);
        int innerY = panelY + Math.round(5.0f * scale);
        int innerBottomY = panelY + Math.round(90.0f * scale);
        int viewportW = Math.max(2, innerRightX - innerLeftX);
        int cardW = viewportW / 2;
        int cardH = Math.round(48.0f * scale);
        int playerX = Math.round((float)this.field_22789 * 0.38f);
        int playerY = Math.round((float)this.field_22790 * 0.15f);
        int playerRightX = Math.round((float)this.field_22789 * 0.62f);
        int playerBottomY = Math.round((float)this.field_22790 * 0.7f);
        float craftScale = baseScale * 0.5f;
        int craftW = Math.round(48.0f * craftScale);
        int craftH = Math.round(22.0f * craftScale);
        int craftX = (this.field_22789 - craftW) / 2;
        int craftY = Math.round((float)this.field_22790 * 0.79f) + 32;
        int dyeRowW = 16 + class_1767.values().length * 18;
        int dyeRowX = (this.field_22789 - dyeRowW) / 2;
        int dyeRowY = craftY - 16 - 6;
        int typeButtonSize = Math.round(22.0f * scale);
        int typeButtonX = leftX;
        int typeButtonY = panelY - typeButtonSize;
        return new Layout(scale, panelY, panelW, panelH, resourceW, leftX, rightX, innerLeftX, innerRightX, innerY, innerBottomY, cardW, cardH, playerX, playerY, playerRightX, playerBottomY, playerBottomY - playerY, craftX, craftY, craftW, craftH, dyeRowX, dyeRowY, typeButtonX, typeButtonY, typeButtonSize);
    }

    private List<TypeButtonBounds> typeButtonBounds(Layout layout) {
        ArrayList<TypeButtonBounds> bounds = new ArrayList<TypeButtonBounds>();
        ClothingFilter[] filters = ClothingFilter.values();
        for (int index = 0; index < filters.length; ++index) {
            bounds.add(new TypeButtonBounds(filters[index], layout.typeButtonX + index * layout.typeButtonSize, layout.typeButtonY, layout.typeButtonSize));
        }
        return bounds;
    }

    private boolean needsScrollbar() {
        return this.maxScrollRows() > 0;
    }

    private void renderScrollbar(class_332 context, Layout layout, int mouseX, int mouseY) {
        if (!this.needsScrollbar()) {
            this.scrollbarDragging = false;
            this.scrollOffset = 0;
            return;
        }
        ScrollbarMetrics scrollbar = this.scrollbarMetrics(layout);
        context.method_25294(scrollbar.x(), scrollbar.y(), scrollbar.x() + scrollbar.width(), scrollbar.y() + scrollbar.height(), 1713377290);
        context.method_49601(scrollbar.x(), scrollbar.y(), scrollbar.width(), scrollbar.height(), -1433700523);
        boolean hovered = TailorScreen.isWithin(mouseX, mouseY, scrollbar.x(), scrollbar.thumbY(), scrollbar.width(), scrollbar.thumbHeight());
        context.method_25291(PartyMenuPanelRenderer.SCROLLER_TEXTURE, scrollbar.x(), scrollbar.thumbY(), 0, 0.0f, 0.0f, 6, 27, 6, 27);
        if (hovered || this.scrollbarDragging) {
            context.method_25294(scrollbar.x(), scrollbar.thumbY(), scrollbar.x() + scrollbar.width(), scrollbar.thumbY() + scrollbar.thumbHeight(), 0x22FFFFFF);
        }
    }

    private ScrollbarMetrics scrollbarMetrics(Layout layout) {
        int x = layout.innerRightX + Math.max(1, Math.round(layout.scale));
        int y = layout.innerY;
        int height = layout.innerBottomY - layout.innerY;
        int thumbHeight = Math.min(height, 27);
        int thumbTravel = height - thumbHeight;
        int maxScroll = this.maxScrollRows();
        int thumbY = y + (maxScroll <= 0 ? 0 : Math.round((float)this.scrollOffset / (float)maxScroll * (float)thumbTravel));
        return new ScrollbarMetrics(x, y, 6, height, thumbY, thumbHeight);
    }

    private void setScrollFromThumbCenter(double mouseY, int scrollbarY, int thumbHeight) {
        this.setScrollFromThumbY(mouseY - (double)thumbHeight / 2.0, scrollbarY, thumbHeight);
    }

    private void setScrollFromThumbY(double thumbY, int scrollbarY, int thumbHeight) {
        int maxScroll = this.maxScrollRows();
        if (maxScroll <= 0) {
            this.scrollOffset = 0;
            return;
        }
        int height = this.layout().innerBottomY - this.layout().innerY;
        int thumbTravel = height - thumbHeight;
        if (thumbTravel <= 0) {
            this.scrollOffset = 0;
            return;
        }
        double clampedThumbY = class_3532.method_15350((double)thumbY, (double)scrollbarY, (double)(scrollbarY + thumbTravel));
        double progress = (clampedThumbY - (double)scrollbarY) / (double)thumbTravel;
        this.scrollOffset = class_3532.method_15340((int)((int)Math.round(progress * (double)maxScroll)), (int)0, (int)maxScroll);
    }

    private List<DyeSlot> dyeSlots(Layout layout) {
        ArrayList<DyeSlot> slots = new ArrayList<DyeSlot>();
        slots.add(new DyeSlot("none", layout.dyeRowX, layout.dyeRowY, new class_1799((class_1935)class_1802.field_8077), TailorTooltip.text((class_2561)class_2561.method_43470((String)"None")), class_2960.method_60655((String)"agob", (String)"textures/ui/slot.png")));
        int index = 1;
        for (class_1767 color : class_1767.values()) {
            int x = layout.dyeRowX + index * 18;
            class_1799 stack = new class_1799((class_1935)class_1769.method_7803((class_1767)color));
            slots.add(new DyeSlot(color.method_7792(), x, layout.dyeRowY, stack, TailorTooltip.item(stack), class_2960.method_60655((String)"agob", (String)"textures/ui/slot.png")));
            ++index;
        }
        return slots;
    }

    private static boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    private static void drawTexture(class_332 context, class_2960 texture, int x, int y, int width, int height) {
        ClientTextureHelper.TextureSize size = ClientTextureHelper.resolveTextureSize(class_310.method_1551(), texture);
        context.method_25293(texture, x, y, width, height, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
    }

    private static void drawCenteredTexture(class_332 context, class_2960 texture, int x, int y, int width, int height) {
        TailorScreen.drawCenteredTexture(context, texture, x, y, width, height, 1.0f, 1.0f, 1.0f);
    }

    private static void drawCenteredTexture(class_332 context, class_2960 texture, int x, int y, int width, int height, float red, float green, float blue) {
        ClientTextureHelper.TextureSize size = ClientTextureHelper.resolveTextureSize(class_310.method_1551(), texture);
        float scale = Math.min((float)width / 22.0f, (float)height / 22.0f);
        int drawW = Math.round((float)size.width() * scale);
        int drawH = Math.round((float)size.height() * scale);
        int drawX = x + (width - drawW) / 2;
        int drawY = y + (height - drawH) / 2;
        RenderSystem.setShaderColor((float)red, (float)green, (float)blue, (float)1.0f);
        context.method_25293(texture, drawX, drawY, drawW, drawH, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private static int resourceScaled(int value) {
        return Math.round((float)value * 0.675f);
    }

    private static int panelScaled(int value) {
        return Math.round((float)value * 0.75f);
    }

    private static int resourceIconSize() {
        return TailorScreen.resourceScaled(16);
    }

    private static int resourceLineHeight() {
        return TailorScreen.resourceScaled(24);
    }

    private static void drawScaledItem(class_332 context, class_1799 stack, int x, int y, float scale) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_51427(stack, 0, 0);
        context.method_51448().method_22909();
    }

    private static void drawScaledText(class_332 context, class_327 textRenderer, String text, int x, int y, int color, float scale) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25303(textRenderer, text, 0, 0, color);
        context.method_51448().method_22909();
    }

    private static void drawOutline(class_332 context, int x, int y, int width, int height, int color) {
        context.method_25294(x, y, x + width, y + 1, color);
        context.method_25294(x, y + height - 1, x + width, y + height, color);
        context.method_25294(x, y, x + 1, y + height, color);
        context.method_25294(x + width - 1, y, x + width, y + height, color);
    }

    @Environment(value=EnvType.CLIENT)
    private static enum ClothingFilter {
        ALL("all", TYPE_ALL, (class_2561)class_2561.method_43470((String)"All"), 0.0f, 0.075f),
        SHIRT("shirt", TYPE_SHIRT, (class_2561)class_2561.method_43470((String)"Shirts"), 0.37f, 0.055f),
        PANTS("pants", TYPE_PANTS, (class_2561)class_2561.method_43470((String)"Pants"), 0.71f, 0.095f);

        private final String slot;
        private final class_2960 icon;
        private final class_2561 tooltip;
        private final float phase;
        private final float speed;

        private ClothingFilter(String slot, class_2960 icon, class_2561 tooltip, float phase, float speed) {
            this.slot = slot;
            this.icon = icon;
            this.tooltip = tooltip;
            this.phase = phase;
            this.speed = speed;
        }

        boolean includes(String slot) {
            return "all".equals(this.slot) || this.slot.equals(slot);
        }

        float red(float time) {
            return this.colorComponent(time, 0.0f);
        }

        float green(float time) {
            return this.colorComponent(time, 0.33f);
        }

        float blue(float time) {
            return this.colorComponent(time, 0.66f);
        }

        private float colorComponent(float time, float channelOffset) {
            return 0.55f + 0.45f * (float)Math.sin((double)(time * this.speed + this.phase + channelOffset) * Math.PI * 2.0);
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record Layout(float scale, int panelY, int panelW, int panelH, int resourceW, int leftX, int rightX, int innerLeftX, int innerRightX, int innerY, int innerBottomY, int cardW, int cardH, int playerX, int playerY, int playerRightX, int playerBottomY, int playerH, int craftX, int craftY, int craftW, int craftH, int dyeRowX, int dyeRowY, int typeButtonX, int typeButtonY, int typeButtonSize) {
    }

    @Environment(value=EnvType.CLIENT)
    private record TailorTooltip(class_2561 text, class_1799 stack, List<AttributeTooltipLine> attributeLines) {
        static TailorTooltip text(class_2561 text) {
            return new TailorTooltip(text, class_1799.field_8037, List.of());
        }

        static TailorTooltip item(class_1799 stack) {
            return new TailorTooltip((class_2561)class_2561.method_43473(), stack, List.of());
        }

        static TailorTooltip clothing(class_2561 text, Map<SkillAttributeType, Integer> attributes) {
            ArrayList<AttributeTooltipLine> lines = new ArrayList<AttributeTooltipLine>();
            for (SkillAttributeType type : List.of(SkillAttributeType.HEALTH, SkillAttributeType.CHARISMA, SkillAttributeType.LUCK, SkillAttributeType.RIDING)) {
                int amount = attributes.getOrDefault((Object)type, 0);
                if (amount <= 0) continue;
                lines.add(new AttributeTooltipLine(type, amount));
            }
            return new TailorTooltip(text, class_1799.field_8037, List.copyOf(lines));
        }

        void draw(class_332 context, class_327 textRenderer, int mouseX, int mouseY) {
            if (!this.attributeLines.isEmpty()) {
                this.drawAttributeTooltip(context, textRenderer, mouseX, mouseY);
                return;
            }
            if (!this.stack.method_7960()) {
                context.method_51446(textRenderer, this.stack, mouseX, mouseY);
                return;
            }
            context.method_51438(textRenderer, this.text, mouseX, mouseY);
        }

        private void drawAttributeTooltip(class_332 context, class_327 textRenderer, int mouseX, int mouseY) {
            class_310 client = class_310.method_1551();
            int iconSize = 9;
            Objects.requireNonNull(textRenderer);
            int lineHeight = 9 + 3;
            int padding = 6;
            int width = textRenderer.method_27525((class_5348)this.text);
            for (AttributeTooltipLine line : this.attributeLines) {
                String lineText = "+" + line.amount() + " " + line.type().getDisplayName();
                width = Math.max(width, iconSize + 4 + textRenderer.method_1727(lineText));
            }
            Objects.requireNonNull(textRenderer);
            int height = padding * 2 + 9 + 4 + this.attributeLines.size() * lineHeight;
            int x = class_3532.method_15340((int)(mouseX + 12), (int)8, (int)(client.method_22683().method_4486() - (width += padding * 2) - 8));
            int y = class_3532.method_15340((int)(mouseY - height - 8), (int)8, (int)(client.method_22683().method_4502() - height - 8));
            context.method_51448().method_22903();
            context.method_51448().method_46416(0.0f, 0.0f, 620.0f);
            context.method_25294(x, y, x + width, y + height, -267386864);
            context.method_49601(x, y, width, height, -11513776);
            int lineY = y + padding;
            context.method_27535(textRenderer, this.text, x + padding, lineY, -2570870);
            Objects.requireNonNull(textRenderer);
            lineY += 9 + 4;
            for (AttributeTooltipLine line : this.attributeLines) {
                int lineX = x + padding;
                context.method_25293(line.type().getIconTexture(), lineX, lineY - 1, iconSize, iconSize, 0.0f, 0.0f, 16, 16, 16, 16);
                context.method_27535(textRenderer, (class_2561)class_2561.method_43470((String)("+" + line.amount() + " " + line.type().getDisplayName())), lineX += iconSize + 4, lineY, -1);
                lineY += lineHeight;
            }
            context.method_51448().method_22909();
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record TypeButtonBounds(ClothingFilter filter, int x, int y, int size) {
    }

    @Environment(value=EnvType.CLIENT)
    private record ScrollbarMetrics(int x, int y, int width, int height, int thumbY, int thumbHeight) {
    }

    @Environment(value=EnvType.CLIENT)
    private record EntryBounds(ClothingEntry entry, int x, int y, int w, int h) {
    }

    @Environment(value=EnvType.CLIENT)
    private record ClothingEntry(String slot, String id, String label, boolean discovered, boolean unlocked, int sortRank) {
        static ClothingEntry of(PlayerSkinBuilderClothingRegistry.ClothingOption option, String slot) {
            return new ClothingEntry(slot, option.id(), option.displayName(), TailorClientState.isDiscovered(slot, option.id()), TailorClientState.isUnlocked(slot, option.id()), 2);
        }

        ClothingEntry withSortRank(int sortRank) {
            return new ClothingEntry(this.slot, this.id, this.label, this.discovered, this.unlocked, sortRank);
        }

        boolean isEquipped(PlayerSkinBuilderProfile profile) {
            return "shirt".equals(this.slot) ? this.id.equals(profile.shirtId()) : this.id.equals(profile.pantsId());
        }

        String equippedDyeId(PlayerSkinBuilderProfile profile) {
            return "shirt".equals(this.slot) ? profile.shirtDyeId() : profile.pantsDyeId();
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record DyeSlot(String dyeId, int x, int y, class_1799 stack, TailorTooltip tooltip, class_2960 texture) {
    }

    @Environment(value=EnvType.CLIENT)
    private record AttributeTooltipLine(SkillAttributeType type, int amount) {
    }
}

