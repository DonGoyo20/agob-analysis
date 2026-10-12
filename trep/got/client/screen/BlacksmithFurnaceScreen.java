/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_465
 *  net.minecraft.class_5250
 *  net.minecraft.class_5348
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.screen;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_465;
import net.minecraft.class_5250;
import net.minecraft.class_5348;
import org.jetbrains.annotations.Nullable;
import trep.got.blacksmith.BlacksmithGroupDefinition;
import trep.got.blacksmith.BlacksmithKingdomUnlocks;
import trep.got.blacksmith.BlacksmithRecipeDisplay;
import trep.got.blacksmith.BlacksmithRecipeSorting;
import trep.got.blacksmith.BlacksmithScreenHandler;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.util.ClientJsonConfigHelper;
import trep.got.client.util.ClientTextureHelper;
import trep.got.kingdom.Kingdom;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotItems;
import trep.got.registry.GotKingdoms;
import trep.got.skill.SkillAttributeType;

@Environment(value=EnvType.CLIENT)
public final class BlacksmithFurnaceScreen
extends class_465<BlacksmithScreenHandler> {
    private static final class_2960 UI_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/blacksmith_furnace_ui.png");
    private static final class_2960 BLACKSMITH_TOGGLE = class_2960.method_60655((String)"agob", (String)"textures/ui/blacksmith_toggle.png");
    private static final class_2960 BLACKSMITH_TOGGLE_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/blacksmith_toggle_hover.png");
    private static final class_2960 LOCKED_ENTRY = class_2960.method_60655((String)"agob", (String)"textures/ui/locked_entry.png");
    private static final class_2960 PAGE_BACKWARD = class_2960.method_60656((String)"widget/page_backward");
    private static final class_2960 PAGE_BACKWARD_HIGHLIGHTED = class_2960.method_60656((String)"widget/page_backward_highlighted");
    private static final int BLACKSMITH_TOGGLE_WIDTH = 96;
    private static final int BLACKSMITH_TOGGLE_HEIGHT = 32;
    private static final int BLACKSMITH_TOGGLE_GAP = 5;
    private static final int CONTAINER_X = 5;
    private static final int CONTAINER_Y = 5;
    private static final int SLOT_SIZE = 16;
    private static final int SLOT_COLUMNS = 8;
    private static final int KINGDOM_ENTRY_SIZE = 32;
    private static final int LOCKED_ENTRY_ICON_SIZE = 14;
    private static final int KINGDOM_COLUMNS = 4;
    private static final int LOWER_SECTION_OFFSET = 32;
    private static final int RETURN_BUTTON_X = 6;
    private static final int RETURN_BUTTON_Y = 130;
    private static final int RETURN_BUTTON_WIDTH = 23;
    private static final int RETURN_BUTTON_HEIGHT = 13;
    private static final int INGREDIENT_ROW_Y = 112;
    private static final int CRAFT_BUTTON_Y = 112;
    private static final List<class_2960> MATERIAL_TAB_ORDER = List.of(class_2960.method_60655((String)"agob", (String)"copper"), class_2960.method_60655((String)"agob", (String)"iron"), class_2960.method_60655((String)"agob", (String)"steel"));
    private static final Map<class_2960, class_1799> MATERIAL_TAB_ITEMS = Map.of(class_2960.method_60655((String)"agob", (String)"copper"), new class_1799((class_1935)class_1802.field_27022), class_2960.method_60655((String)"agob", (String)"iron"), new class_1799((class_1935)class_1802.field_8620), class_2960.method_60655((String)"agob", (String)"steel"), new class_1799((class_1935)GotItems.STEEL_INGOT));
    private static final String CONFIG_FILE_NAME = "got_blacksmith_furnace_screen.json";
    private static KingdomSection cachedSection = KingdomSection.WESTEROS;
    private static boolean preferencesLoaded;
    @Nullable
    private class_2960 selectedKingdomId;
    @Nullable
    private String selectedGroupId;
    @Nullable
    private class_2960 selectedRecipeId;
    private class_1799 hoveredStack = class_1799.field_8037;
    @Nullable
    private class_2561 hoveredText;
    private int hoveredLockedSmithingLevel = -1;
    @Nullable
    private class_2960 hoveredLockedKingdomId;
    private int hoveredLockedKingdomRequiredIntelligence = -1;
    private boolean hoveredLockedKingdomTalked;
    private KingdomSection selectedSection = cachedSection;

    public BlacksmithFurnaceScreen(BlacksmithScreenHandler handler, class_1661 inventory, class_2561 title) {
        super((class_1703)handler, inventory, title);
        BlacksmithFurnaceScreen.loadPreferences(class_310.method_1551());
        this.selectedSection = this.sanitizeSection(cachedSection);
        this.field_2792 = 137;
        this.field_2779 = 149;
        this.field_25267 = Integer.MIN_VALUE;
        this.field_25268 = Integer.MIN_VALUE;
        this.field_25269 = Integer.MIN_VALUE;
        this.field_25270 = Integer.MIN_VALUE;
    }

    private static void loadPreferences(class_310 client) {
        if (preferencesLoaded) {
            return;
        }
        preferencesLoaded = true;
        ClientJsonConfigHelper.load(client, CONFIG_FILE_NAME, "Failed to load blacksmith furnace client state").ifPresent(root -> {
            if (root.has("section")) {
                cachedSection = BlacksmithFurnaceScreen.parseSection(root.get("section").getAsString(), KingdomSection.WESTEROS);
            } else if (root.has("showing_essos") && root.get("showing_essos").getAsBoolean()) {
                cachedSection = KingdomSection.ESSOS;
            }
        });
    }

    private static void savePreferences(class_310 client) {
        if (client == null) {
            return;
        }
        JsonObject root = new JsonObject();
        root.addProperty("section", cachedSection.name());
        ClientJsonConfigHelper.save(client, CONFIG_FILE_NAME, root, "Failed to save blacksmith furnace client state");
    }

    public void method_25432() {
        cachedSection = this.selectedSection;
        BlacksmithFurnaceScreen.savePreferences(this.field_22787);
        super.method_25432();
    }

    protected void method_2389(class_332 context, float delta, int mouseX, int mouseY) {
        this.hoveredStack = class_1799.field_8037;
        this.hoveredText = null;
        this.hoveredLockedSmithingLevel = -1;
        this.hoveredLockedKingdomId = null;
        this.hoveredLockedKingdomRequiredIntelligence = -1;
        this.hoveredLockedKingdomTalked = false;
        this.drawContinentToggle(context, mouseX, mouseY);
        context.method_25291(UI_TEXTURE, this.field_2776, this.field_2800, 0, 0.0f, 0.0f, this.field_2792, this.field_2779, 256, 256);
        if (this.selectedKingdomId == null) {
            this.drawKingdomSelection(context, mouseX, mouseY);
            return;
        }
        this.drawReturnButton(context, mouseX, mouseY);
        if (this.selectedGroupId == null) {
            this.drawGroupSelection(context, mouseX, mouseY);
            return;
        }
        this.drawRecipeSelection(context, mouseX, mouseY);
    }

    protected void method_2388(class_332 context, int mouseX, int mouseY) {
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.method_25420(context, mouseX, mouseY, delta);
        super.method_25394(context, mouseX, mouseY, delta);
        if (this.hoveredLockedKingdomRequiredIntelligence >= 0 && this.hoveredLockedKingdomId != null) {
            this.drawKingdomLockedTooltip(context, mouseX, mouseY, this.hoveredLockedKingdomId, this.hoveredLockedKingdomTalked, this.hoveredLockedKingdomRequiredIntelligence);
        } else if (this.hoveredLockedSmithingLevel >= 0) {
            this.drawLockedTooltip(context, mouseX, mouseY, this.hoveredLockedSmithingLevel);
        } else if (!this.hoveredStack.method_7960()) {
            context.method_51446(this.field_22793, this.hoveredStack, mouseX, mouseY);
        } else if (this.hoveredText != null) {
            context.method_51438(this.field_22793, this.hoveredText, mouseX, mouseY);
        }
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return super.method_25402(mouseX, mouseY, button);
        }
        if (this.isInsideContinentToggle(mouseX, mouseY)) {
            this.cycleSection();
            return true;
        }
        if (this.selectedKingdomId == null) {
            class_2960 kingdomId = this.getHoveredKingdom(mouseX, mouseY);
            if (kingdomId != null) {
                if (this.isKingdomLocked(kingdomId)) {
                    return this.tryUnlockKingdom(kingdomId);
                }
                this.selectedKingdomId = kingdomId;
                return true;
            }
        } else {
            if (this.isInside(mouseX, mouseY, this.field_2776 + 6, this.field_2800 + 130, 23, 13)) {
                if (this.selectedGroupId != null) {
                    this.selectedGroupId = null;
                    this.selectedRecipeId = null;
                } else {
                    this.selectedKingdomId = null;
                }
                return true;
            }
            if (this.selectedGroupId == null) {
                BlacksmithGroupDefinition group = this.getHoveredGroup(mouseX, mouseY);
                if (group != null) {
                    this.selectedGroupId = group.id();
                    this.selectedRecipeId = this.findFirstSelectableRecipeId(this.getRecipesForSelectedGroup());
                    return true;
                }
            } else {
                if (this.trySelectRecipe(mouseX, mouseY)) {
                    return true;
                }
                if (this.isInside(mouseX, mouseY, this.field_2776 + 91, this.field_2800 + 112, 35, 21)) {
                    return this.tryCraftSelectedRecipe();
                }
            }
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    private void drawKingdomSelection(class_332 context, int mouseX, int mouseY) {
        List<class_2960> tabOrder = this.currentTabOrder();
        for (int index = 0; index < tabOrder.size(); ++index) {
            class_1799 stack;
            class_2960 kingdomId = tabOrder.get(index);
            int buttonX = this.field_2776 + 5 + index % 4 * 32;
            int buttonY = this.field_2800 + 5 + index / 4 * 32;
            boolean hovered = this.isInside(mouseX, mouseY, buttonX, buttonY, 32, 32);
            boolean selected = kingdomId.equals((Object)this.selectedKingdomId);
            boolean locked = this.isKingdomLocked(kingdomId);
            context.method_25291(UI_TEXTURE, buttonX, buttonY, 0, 165.0f, 133.0f, 32, 32, 256, 256);
            class_2960 sigil = this.resolveKingdomTexture(kingdomId);
            if (sigil != null) {
                this.drawFittedSigil(context, sigil, buttonX, buttonY + 2, 32, 32);
            } else {
                stack = MATERIAL_TAB_ITEMS.getOrDefault(kingdomId, class_1799.field_8037);
                if (!stack.method_7960()) {
                    context.method_51427(stack, buttonX + 8, buttonY + 8);
                }
            }
            if (locked) {
                context.method_25294(buttonX, buttonY, buttonX + 32, buttonY + 32, -1728053248);
                this.drawFittedSigil(context, LOCKED_ENTRY, buttonX + 9, buttonY + 9, 14, 14);
            }
            if (hovered || selected) {
                context.method_25294(buttonX, buttonY, buttonX + 32, buttonY + 32, 0x66FFFFFF);
            }
            if (!hovered) continue;
            if (locked) {
                this.hoveredLockedKingdomId = kingdomId;
                this.hoveredLockedKingdomRequiredIntelligence = ((BlacksmithScreenHandler)this.field_2797).getOpeningData().nextUnlockIntelligenceRequirement();
                this.hoveredLockedKingdomTalked = ((BlacksmithScreenHandler)this.field_2797).getOpeningData().hasTalkedToBlacksmith(kingdomId);
                continue;
            }
            stack = MATERIAL_TAB_ITEMS.getOrDefault(kingdomId, class_1799.field_8037);
            this.hoveredText = stack.method_7960() ? class_2561.method_43470((String)this.displayNameForKingdom(kingdomId)) : stack.method_7964();
        }
    }

    private void drawContinentToggle(class_332 context, int mouseX, int mouseY) {
        int buttonY;
        int buttonX = this.continentToggleX();
        boolean hovered = this.isInside(mouseX, mouseY, buttonX, buttonY = this.continentToggleY(), 96, 32);
        context.method_25291(hovered ? BLACKSMITH_TOGGLE_HOVER : BLACKSMITH_TOGGLE, buttonX, buttonY, 0, 0.0f, 0.0f, 96, 32, 96, 32);
        class_5250 label = class_2561.method_43470((String)this.selectedSection.label);
        int labelX = buttonX + (96 - this.field_22793.method_27525((class_5348)label)) / 2;
        int labelY = buttonY + 12;
        context.method_51439(this.field_22793, (class_2561)label, labelX + 1, labelY + 1, -16777216, false);
        context.method_51439(this.field_22793, (class_2561)label, labelX, labelY, hovered ? -171 : -1, false);
    }

    private void drawFittedSigil(class_332 context, class_2960 texture, int x, int y, int boxWidth, int boxHeight) {
        ClientTextureHelper.TextureSize size = ClientTextureHelper.resolveTextureSize(this.field_22787, texture);
        float scale = Math.min((float)boxWidth / (float)size.width(), (float)boxHeight / (float)size.height());
        int offsetX = Math.max(0, Math.round(((float)boxWidth - (float)size.width() * scale) / 2.0f));
        int offsetY = Math.max(0, Math.round(((float)boxHeight - (float)size.height() * scale) / 2.0f));
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)(x + offsetX), (float)(y + offsetY), 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
        context.method_51448().method_22909();
    }

    private void drawGroupSelection(class_332 context, int mouseX, int mouseY) {
        List<BlacksmithGroupDefinition> groups = ((BlacksmithScreenHandler)this.field_2797).getOpeningData().groups().stream().filter(group -> group.kingdomId().equals((Object)this.selectedKingdomId)).sorted(Comparator.comparingInt(BlacksmithGroupDefinition::order).thenComparing(BlacksmithGroupDefinition::displayName)).toList();
        for (int index = 0; index < groups.size(); ++index) {
            BlacksmithGroupDefinition group2 = groups.get(index);
            int slotX = this.field_2776 + 5 + index % 8 * 16;
            int slotY = this.field_2800 + 5 + index / 8 * 16;
            boolean hovered = this.isInside(mouseX, mouseY, slotX, slotY, 16, 16);
            this.drawSlotFrame(context, slotX, slotY, hovered);
            class_1799 icon = group2.createIconStack();
            context.method_51427(icon, slotX, slotY);
            if (!hovered) continue;
            this.hoveredStack = icon;
            this.hoveredText = class_2561.method_43470((String)group2.displayName());
        }
    }

    private void drawRecipeSelection(class_332 context, int mouseX, int mouseY) {
        boolean hovered;
        int slotY;
        int slotX;
        List<BlacksmithRecipeDisplay> recipes = this.getRecipesForSelectedGroup();
        for (int index = 0; index < recipes.size(); ++index) {
            BlacksmithRecipeDisplay recipe = recipes.get(index);
            slotX = this.field_2776 + 5 + index % 8 * 16;
            slotY = this.field_2800 + 5 + index / 8 * 16;
            hovered = this.isInside(mouseX, mouseY, slotX, slotY, 16, 16);
            boolean selected = recipe.recipeId().equals((Object)this.selectedRecipeId);
            boolean locked = this.isRecipeLocked(recipe);
            this.drawSlotFrame(context, slotX, slotY, hovered || selected);
            context.method_51427(recipe.result(), slotX, slotY);
            context.method_51431(this.field_22793, recipe.result(), slotX, slotY);
            if (locked) {
                context.method_25294(slotX, slotY, slotX + 16, slotY + 16, -2013265920);
                context.method_49601(slotX, slotY, 16, 16, -2909542);
            }
            if (!hovered) continue;
            if (locked) {
                this.hoveredLockedSmithingLevel = recipe.requiredSmithingLevel();
                continue;
            }
            this.hoveredStack = recipe.result();
        }
        BlacksmithRecipeDisplay selectedRecipe = this.getSelectedRecipe();
        if (selectedRecipe == null) {
            return;
        }
        for (int index = 0; index < Math.min(4, selectedRecipe.ingredientPreviews().size()); ++index) {
            slotX = this.field_2776 + 16 + index * 16;
            slotY = this.field_2800 + 112;
            hovered = this.isInside(mouseX, mouseY, slotX, slotY, 16, 16);
            this.drawSlotFrame(context, slotX, slotY, hovered);
            class_1799 ingredient = selectedRecipe.ingredientPreviews().get(index);
            context.method_51427(ingredient, slotX, slotY);
            context.method_51431(this.field_22793, ingredient, slotX, slotY);
            if (!hovered) continue;
            this.hoveredStack = ingredient;
        }
        boolean locked = this.isRecipeLocked(selectedRecipe);
        boolean hovered2 = this.isInside(mouseX, mouseY, this.field_2776 + 91, this.field_2800 + 112, 35, 21);
        context.method_25291(UI_TEXTURE, this.field_2776 + 91, this.field_2800 + 112, 0, 166.0f, 69.0f, 35, 21, 256, 256);
        if (locked) {
            context.method_25294(this.field_2776 + 91, this.field_2800 + 112, this.field_2776 + 126, this.field_2800 + 112 + 21, -2013265920);
        }
        if (hovered2) {
            context.method_25294(this.field_2776 + 91, this.field_2800 + 112, this.field_2776 + 126, this.field_2800 + 112 + 21, 0x33FFFFFF);
            if (locked) {
                this.hoveredLockedSmithingLevel = selectedRecipe.requiredSmithingLevel();
            } else {
                this.hoveredText = class_2561.method_43470((String)"Craft");
            }
        }
    }

    private void drawReturnButton(class_332 context, int mouseX, int mouseY) {
        boolean hovered = this.isInside(mouseX, mouseY, this.field_2776 + 6, this.field_2800 + 130, 23, 13);
        context.method_52706(hovered ? PAGE_BACKWARD_HIGHLIGHTED : PAGE_BACKWARD, this.field_2776 + 6, this.field_2800 + 130, 23, 13);
    }

    private void drawSlotFrame(class_332 context, int x, int y, boolean active) {
        context.method_25291(UI_TEXTURE, x, y, 0, 176.0f, active ? 48.0f : 32.0f, 16, 16, 256, 256);
    }

    @Nullable
    private class_2960 getHoveredKingdom(double mouseX, double mouseY) {
        List<class_2960> tabOrder = this.currentTabOrder();
        for (int index = 0; index < tabOrder.size(); ++index) {
            int buttonX = this.field_2776 + 5 + index % 4 * 32;
            int buttonY = this.field_2800 + 5 + index / 4 * 32;
            if (!this.isInside(mouseX, mouseY, buttonX, buttonY, 32, 32)) continue;
            return tabOrder.get(index);
        }
        return null;
    }

    @Nullable
    private BlacksmithGroupDefinition getHoveredGroup(double mouseX, double mouseY) {
        List<BlacksmithGroupDefinition> groups = ((BlacksmithScreenHandler)this.field_2797).getOpeningData().groups().stream().filter(group -> group.kingdomId().equals((Object)this.selectedKingdomId)).sorted(Comparator.comparingInt(BlacksmithGroupDefinition::order).thenComparing(BlacksmithGroupDefinition::displayName)).toList();
        for (int index = 0; index < groups.size(); ++index) {
            int slotX = this.field_2776 + 5 + index % 8 * 16;
            int slotY = this.field_2800 + 5 + index / 8 * 16;
            if (!this.isInside(mouseX, mouseY, slotX, slotY, 16, 16)) continue;
            return groups.get(index);
        }
        return null;
    }

    private List<BlacksmithRecipeDisplay> getRecipesForSelectedGroup() {
        if (this.selectedKingdomId == null || this.selectedGroupId == null) {
            return List.of();
        }
        return ((BlacksmithScreenHandler)this.field_2797).getOpeningData().recipes().stream().filter(recipe -> recipe.kingdomId().equals((Object)this.selectedKingdomId) && recipe.groupId().equals(this.selectedGroupId)).sorted(BlacksmithRecipeSorting.displayComparator()).toList();
    }

    @Nullable
    private BlacksmithRecipeDisplay getSelectedRecipe() {
        if (this.selectedRecipeId == null) {
            return null;
        }
        return this.getRecipesForSelectedGroup().stream().filter(recipe -> recipe.recipeId().equals((Object)this.selectedRecipeId)).findFirst().orElse(null);
    }

    private boolean trySelectRecipe(double mouseX, double mouseY) {
        List<BlacksmithRecipeDisplay> recipes = this.getRecipesForSelectedGroup();
        for (int index = 0; index < recipes.size(); ++index) {
            int slotX = this.field_2776 + 5 + index % 8 * 16;
            int slotY = this.field_2800 + 5 + index / 8 * 16;
            if (!this.isInside(mouseX, mouseY, slotX, slotY, 16, 16)) continue;
            BlacksmithRecipeDisplay recipe = recipes.get(index);
            if (this.isRecipeLocked(recipe)) {
                return true;
            }
            this.selectedRecipeId = recipe.recipeId();
            return true;
        }
        return false;
    }

    private boolean tryCraftSelectedRecipe() {
        BlacksmithRecipeDisplay selectedRecipe = this.getSelectedRecipe();
        if (selectedRecipe == null || this.isRecipeLocked(selectedRecipe) || this.field_22787 == null || this.field_22787.field_1761 == null) {
            return false;
        }
        int buttonId = ((BlacksmithScreenHandler)this.field_2797).getCraftButtonId(selectedRecipe.recipeId());
        if (buttonId < 0) {
            return false;
        }
        this.field_22787.field_1761.method_2900(((BlacksmithScreenHandler)this.field_2797).field_7763, buttonId);
        return true;
    }

    private boolean tryUnlockKingdom(class_2960 kingdomId) {
        if (!this.canUnlockKingdomOnClient(kingdomId) || this.field_22787 == null || this.field_22787.field_1761 == null) {
            return true;
        }
        int buttonId = ((BlacksmithScreenHandler)this.field_2797).getUnlockButtonId(kingdomId);
        if (buttonId < 0) {
            return true;
        }
        this.field_22787.field_1761.method_2900(((BlacksmithScreenHandler)this.field_2797).field_7763, buttonId);
        return true;
    }

    private boolean isInside(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    private int continentToggleX() {
        return this.field_2776 + (this.field_2792 - 96) / 2;
    }

    private int continentToggleY() {
        return Math.max(2, this.field_2800 - 32 - 5);
    }

    private boolean isInsideContinentToggle(double mouseX, double mouseY) {
        return this.isInside(mouseX, mouseY, this.continentToggleX(), this.continentToggleY(), 96, 32);
    }

    private void selectSection(KingdomSection section) {
        KingdomSection sanitized = this.sanitizeSection(section);
        if (sanitized == this.selectedSection) {
            return;
        }
        this.selectedSection = sanitized;
        cachedSection = sanitized;
        this.selectedKingdomId = null;
        this.selectedGroupId = null;
        this.selectedRecipeId = null;
        BlacksmithFurnaceScreen.savePreferences(this.field_22787);
    }

    private void cycleSection() {
        List<KingdomSection> sections = this.availableSections();
        int currentIndex = sections.indexOf((Object)this.selectedSection);
        int nextIndex = currentIndex < 0 ? 0 : (currentIndex + 1) % sections.size();
        this.selectSection(sections.get(nextIndex));
    }

    private List<class_2960> currentTabOrder() {
        ArrayList<class_2960> tabs = new ArrayList<class_2960>(switch (this.selectedSection.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> BlacksmithKingdomUnlocks.westerosTabs();
            case 1 -> BlacksmithKingdomUnlocks.essosTabs();
            case 2 -> BlacksmithKingdomUnlocks.addonTabs();
        });
        if (this.selectedSection == KingdomSection.WESTEROS) {
            tabs.addAll(MATERIAL_TAB_ORDER);
        }
        return tabs;
    }

    private List<KingdomSection> availableSections() {
        ArrayList<KingdomSection> sections = new ArrayList<KingdomSection>();
        sections.add(KingdomSection.WESTEROS);
        sections.add(KingdomSection.ESSOS);
        if (BlacksmithKingdomUnlocks.hasAddonTabs()) {
            sections.add(KingdomSection.ADDON);
        }
        return List.copyOf(sections);
    }

    private KingdomSection sanitizeSection(KingdomSection section) {
        return this.availableSections().contains((Object)section) ? section : KingdomSection.WESTEROS;
    }

    private static KingdomSection parseSection(String value, KingdomSection fallback) {
        if (value == null || value.isBlank()) {
            return fallback;
        }
        try {
            return KingdomSection.valueOf(value.trim().toUpperCase(Locale.ROOT));
        }
        catch (IllegalArgumentException exception) {
            return fallback;
        }
    }

    @Nullable
    private class_2960 resolveKingdomTexture(class_2960 kingdomId) {
        if (kingdomId == null || BlacksmithKingdomUnlocks.isMaterialTab(kingdomId)) {
            return null;
        }
        CharacterSelectionOption option = GotCharacterOptions.getByKingdomId(kingdomId);
        return option == null ? null : option.texture();
    }

    private String displayNameForKingdom(class_2960 kingdomId) {
        if (kingdomId == null) {
            return "";
        }
        if (BlacksmithKingdomUnlocks.isMaterialTab(kingdomId)) {
            return switch (kingdomId.method_12832()) {
                case "copper" -> "Copper";
                case "iron" -> "Iron";
                case "steel" -> "Steel";
                default -> kingdomId.method_12832();
            };
        }
        Kingdom kingdom = GotKingdoms.get(kingdomId);
        return kingdom != null ? kingdom.getName() : kingdomId.toString();
    }

    private boolean isKingdomLocked(class_2960 kingdomId) {
        return !((BlacksmithScreenHandler)this.field_2797).getOpeningData().isKingdomUnlocked(kingdomId);
    }

    private boolean canUnlockKingdomOnClient(class_2960 kingdomId) {
        return !BlacksmithKingdomUnlocks.isMaterialTab(kingdomId) && ((BlacksmithScreenHandler)this.field_2797).getOpeningData().hasTalkedToBlacksmith(kingdomId) && CharacterSelectionClientState.getSkillPoints(SkillAttributeType.INTELLIGENCE) >= ((BlacksmithScreenHandler)this.field_2797).getOpeningData().nextUnlockIntelligenceRequirement();
    }

    private boolean isRecipeLocked(BlacksmithRecipeDisplay recipe) {
        return recipe.requiredSmithingLevel() > CharacterSelectionClientState.getSkillPoints(SkillAttributeType.SMITHING);
    }

    @Nullable
    private class_2960 findFirstSelectableRecipeId(List<BlacksmithRecipeDisplay> recipes) {
        for (BlacksmithRecipeDisplay recipe : recipes) {
            if (this.isRecipeLocked(recipe)) continue;
            return recipe.recipeId();
        }
        return recipes.isEmpty() ? null : recipes.get(0).recipeId();
    }

    private void drawLockedTooltip(class_332 context, int mouseX, int mouseY, int requiredSmithingLevel) {
        class_5250 header = class_2561.method_43470((String)"Requirements not met:").method_54663(0xFFB7B7);
        class_5250 requirement = class_2561.method_43470((String)("- " + requiredSmithingLevel + " Required")).method_54663(0xFFB7B7);
        int iconSize = 10;
        int boxX = mouseX + 12;
        int boxY = mouseY - 4;
        int width = Math.max(this.field_22793.method_27525((class_5348)header), this.field_22793.method_27525((class_5348)requirement) + iconSize + 4) + 10;
        int height = 24;
        context.method_51448().method_22903();
        context.method_51448().method_46416(0.0f, 0.0f, 400.0f);
        context.method_25294(boxX - 4, boxY - 4, boxX + width, boxY + height, -267386864);
        context.method_49601(boxX - 4, boxY - 4, width + 4, height + 4, -1862289481);
        context.method_51439(this.field_22793, (class_2561)header, boxX, boxY, 0xFFB7B7, false);
        int lineY = boxY + 12;
        context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)"-").method_54663(0xFFB7B7), boxX, lineY, 0xFFB7B7, false);
        this.drawTooltipSkillIcon(context, SkillAttributeType.SMITHING, boxX + 8, lineY - 1, iconSize);
        context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)(requiredSmithingLevel + " Required")).method_54663(0xFFB7B7), boxX + 8 + iconSize + 4, lineY, 0xFFB7B7, false);
        context.method_51448().method_22909();
    }

    private void drawKingdomLockedTooltip(class_332 context, int mouseX, int mouseY, class_2960 kingdomId, boolean talkedToBlacksmith, int requiredIntelligence) {
        class_5250 header = class_2561.method_43470((String)"Locked:").method_54663(-34182);
        class_5250 talkLine = class_2561.method_43470((String)("- Talk to " + this.displayNameForKingdom(kingdomId) + " Blacksmith")).method_54663(talkedToBlacksmith ? -9699477 : -34182);
        boolean hasIntelligence = CharacterSelectionClientState.getSkillPoints(SkillAttributeType.INTELLIGENCE) >= requiredIntelligence;
        int intelligenceColor = hasIntelligence ? -7076 : -34182;
        int iconSize = 10;
        int boxX = mouseX + 12;
        int boxY = mouseY - 4;
        String intelligenceText = requiredIntelligence + " Intelligence Points";
        int lineTwoWidth = this.field_22793.method_1727("- Obtain ") + iconSize + 4 + this.field_22793.method_1727(intelligenceText);
        int width = Math.max(Math.max(this.field_22793.method_27525((class_5348)header), this.field_22793.method_27525((class_5348)talkLine)), lineTwoWidth) + 10;
        int height = 36;
        context.method_51448().method_22903();
        context.method_51448().method_46416(0.0f, 0.0f, 400.0f);
        context.method_25294(boxX - 4, boxY - 4, boxX + width, boxY + height, -267386864);
        context.method_49601(boxX - 4, boxY - 4, width + 4, height + 4, -1862305158);
        context.method_51439(this.field_22793, (class_2561)header, boxX, boxY, -34182, false);
        context.method_51439(this.field_22793, (class_2561)talkLine, boxX, boxY + 12, talkedToBlacksmith ? -9699477 : -34182, false);
        int lineY = boxY + 24;
        context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)"- Obtain ").method_54663(intelligenceColor), boxX, lineY, intelligenceColor, false);
        int iconX = boxX + this.field_22793.method_1727("- Obtain ");
        this.drawTooltipSkillIcon(context, SkillAttributeType.INTELLIGENCE, iconX, lineY - 1, iconSize);
        context.method_51439(this.field_22793, (class_2561)class_2561.method_43470((String)intelligenceText).method_54663(intelligenceColor), iconX + iconSize + 4, lineY, intelligenceColor, false);
        context.method_51448().method_22909();
    }

    private void drawTooltipSkillIcon(class_332 context, SkillAttributeType type, int x, int y, int size) {
        float scale = (float)size / 16.0f;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_25291(type.getIconTexture(), 0, 0, 0, 0.0f, 0.0f, 16, 16, 16, 16);
        context.method_51448().method_22909();
    }

    @Environment(value=EnvType.CLIENT)
    private static enum KingdomSection {
        WESTEROS("Westeros"),
        ESSOS("Essos"),
        ADDON("Addon");

        private final String label;

        private KingdomSection(String label) {
            this.label = label;
        }
    }
}

