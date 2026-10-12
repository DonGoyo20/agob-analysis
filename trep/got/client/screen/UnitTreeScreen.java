/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_490
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.screen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_490;
import org.jetbrains.annotations.Nullable;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.troop.TroopPreviewFactory;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.DynamicTroopRegistry;
import trep.got.troop.TroopStatResolver;

@Environment(value=EnvType.CLIENT)
public final class UnitTreeScreen
extends class_437 {
    private static final class_2960 UI_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/unit_tree.png");
    private static final class_2960 ENTRY_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/player_entry.png");
    private static final class_2960 BACKGROUND_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/unit_tree_16x16_backing.png");
    private static final class_2960 SLOT_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/slot.png");
    private static final class_2960 HEART_ICON = class_2960.method_60656((String)"hud/heart/full");
    private static final class_2960 ARMOR_ICON = class_2960.method_60656((String)"hud/armor_full");
    private static final class_2960 SPEED_ICON = class_2960.method_60655((String)"minecraft", (String)"textures/mob_effect/speed.png");
    private static final int UI_WIDTH = 480;
    private static final int UI_HEIGHT = 288;
    private static final int VIEWPORT_X = 12;
    private static final int VIEWPORT_Y = 12;
    private static final int VIEWPORT_WIDTH = 456;
    private static final int VIEWPORT_HEIGHT = 264;
    private static final int ENTRY_WIDTH = 64;
    private static final int ENTRY_HEIGHT = 96;
    private static final int SLOT_SIZE = 16;
    private static final int SLOT_GAP = 2;
    private static final int SLOT_ROW_GAP = 2;
    private static final int ARMOR_ROW_Y = 100;
    private static final int HAND_ROW_Y = 118;
    private static final int NODE_HEIGHT = 136;
    private static final int ENTRY_SPACING_X = 24;
    private static final int ENTRY_SPACING_Y = 28;
    private final class_437 returnScreen;
    private final class_2960 troopId;
    private final List<LayoutNode> nodes = new ArrayList<LayoutNode>();
    private final List<Connection> connections = new ArrayList<Connection>();
    private double scrollY;
    private double lastDragY;
    private boolean dragging;
    private int contentHeight;
    @Nullable
    private TroopTooltip hoveredTooltip;

    public UnitTreeScreen(class_437 returnScreen, class_2960 troopId) {
        super((class_2561)class_2561.method_43470((String)"Unit Tree"));
        this.returnScreen = returnScreen;
        this.troopId = troopId;
        this.buildLayout();
    }

    protected void method_25426() {
        super.method_25426();
        this.clampScroll();
    }

    public boolean method_25421() {
        return false;
    }

    protected void method_57734(float delta) {
    }

    public void method_25419() {
        if (this.field_22787 != null) {
            this.field_22787.method_1507(this.returnScreen);
        }
    }

    public boolean method_25422() {
        return true;
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (this.field_22787 != null && this.field_22787.field_1690.field_1822.method_1417(keyCode, scanCode)) {
            this.method_25419();
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button == 0 && this.isWithinViewport(mouseX, mouseY)) {
            this.dragging = true;
            this.lastDragY = mouseY;
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.dragging && button == 0) {
            this.scrollY -= mouseY - this.lastDragY;
            this.lastDragY = mouseY;
            this.clampScroll();
            return true;
        }
        return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        if (button == 0) {
            this.dragging = false;
        }
        return super.method_25406(mouseX, mouseY, button);
    }

    public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (this.isWithinViewport(mouseX, mouseY)) {
            this.scrollY -= verticalAmount * 14.0;
            this.clampScroll();
            return true;
        }
        return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.hoveredTooltip = null;
        context.method_25294(0, 0, this.field_22789, this.field_22790, -871362544);
        int left = (this.field_22789 - 480) / 2;
        int top = (this.field_22790 - 288) / 2;
        context.method_25291(UI_TEXTURE, left, top, 0, 0.0f, 0.0f, 480, 288, 480, 288);
        this.renderViewport(context, left, top, mouseX, mouseY);
        super.method_25394(context, mouseX, mouseY, delta);
        if (this.hoveredTooltip != null) {
            this.drawTroopTooltip(context, mouseX, mouseY, this.hoveredTooltip);
        }
    }

    private void renderViewport(class_332 context, int left, int top, int mouseX, int mouseY) {
        int viewportLeft = left + 12;
        int viewportTop = top + 12;
        context.method_44379(viewportLeft, viewportTop, viewportLeft + 456, viewportTop + 264);
        int backgroundOffsetY = (int)Math.floor(this.scrollY);
        this.renderTiledBackground(context, viewportLeft, viewportTop, backgroundOffsetY);
        this.renderLines(context, viewportLeft, viewportTop);
        this.renderNodes(context, viewportLeft, viewportTop, mouseX, mouseY);
        context.method_44380();
    }

    private void renderTiledBackground(class_332 context, int viewportLeft, int viewportTop, int backgroundOffsetY) {
        int tile = 16;
        for (int x = 0; x < 456; x += tile) {
            for (int y = -tile; y < 264 + tile; y += tile) {
                int drawY = viewportTop + y - Math.floorMod(backgroundOffsetY, tile);
                context.method_25291(BACKGROUND_TEXTURE, viewportLeft + x, drawY, 0, 0.0f, 0.0f, tile, tile, tile, tile);
            }
        }
    }

    private void renderLines(class_332 context, int viewportLeft, int viewportTop) {
        for (Connection connection : this.connections) {
            int parentCenterX = viewportLeft + connection.parent().x() + 32;
            int parentBottomY = viewportTop + connection.parent().y() - (int)this.scrollY + 136;
            int childCenterX = viewportLeft + connection.child().x() + 32;
            int childTopY = viewportTop + connection.child().y() - (int)this.scrollY;
            int midY = parentBottomY + 10;
            context.method_25294(parentCenterX - 1, parentBottomY, parentCenterX + 1, midY, -1644826);
            context.method_25294(Math.min(parentCenterX, childCenterX), midY - 1, Math.max(parentCenterX, childCenterX), midY + 1, -1644826);
            context.method_25294(childCenterX - 1, midY, childCenterX + 1, childTopY, -1644826);
        }
    }

    private void renderNodes(class_332 context, int viewportLeft, int viewportTop, int mouseX, int mouseY) {
        for (LayoutNode node : this.nodes) {
            int drawX = viewportLeft + node.x();
            int drawY = viewportTop + node.y() - (int)this.scrollY;
            if (drawY + 136 < viewportTop || drawY > viewportTop + 264) continue;
            context.method_25291(ENTRY_TEXTURE, drawX, drawY, 0, 0.0f, 0.0f, 64, 96, 64, 96);
            TroopPreviewFactory.TroopPreview preview = node.preview();
            if (preview != null) {
                this.renderTroopPreview(context, drawX, drawY, mouseX, mouseY, preview);
            }
            this.renderEquipmentRows(context, drawX, drawY, preview);
            if (!this.isWithin(mouseX, mouseY, drawX, drawY, 64, 136)) continue;
            this.hoveredTooltip = this.createTooltip(node.definition());
        }
    }

    private TroopTooltip createTooltip(DynamicTroopDefinition definition) {
        return new TroopTooltip(definition.displayName(), definition.tier(), TroopStatResolver.baseHealth(definition), TroopStatResolver.totalArmor(definition), TroopStatResolver.totalDamage(definition), TroopStatResolver.speedVsPlayer(definition), definition.hasReputationRequirement() ? definition.reputationRequirementLabel() : "", definition.meetsReputationRequirement(CharacterSelectionClientState.getReputationTitle()), definition.hasReligionRequirement() ? definition.religionRequirementLabel() : "", definition.meetsReligionRequirement(CharacterSelectionClientState.getSelectedReligionId()), definition.hasDisallowedHireKingdoms() ? definition.disallowedHireKingdomsLabel() : "", definition.canBeHiredByKingdom(CharacterSelectionClientState.getSelectedKingdomId()), definition.hasHouseRequirement() ? definition.houseRequirementLabel() : "", definition.meetsHouseRequirement(CharacterSelectionClientState.getSelectedOptionId()));
    }

    private void drawTroopTooltip(class_332 context, int mouseX, int mouseY, TroopTooltip tooltip) {
        boolean hasHouseRequirement;
        List<String> lines = List.of(this.formatStat(tooltip.health()), this.formatStat(tooltip.armor()), this.formatStat(tooltip.damage()), String.format(Locale.ROOT, "%.2fx", tooltip.speed()));
        int textWidth = this.field_22793.method_1727(tooltip.name());
        for (String line : lines) {
            textWidth = Math.max(textWidth, this.field_22793.method_1727(line));
        }
        boolean hasRequirement = !tooltip.requirementLabel().isBlank();
        boolean hasReligionRequirement = !tooltip.religionRequirementLabel().isBlank();
        boolean hasKingdomRestriction = !tooltip.kingdomRestrictionLabel().isBlank();
        boolean bl = hasHouseRequirement = !tooltip.houseRequirementLabel().isBlank();
        if (hasRequirement) {
            textWidth = Math.max(textWidth, this.field_22793.method_1727(tooltip.requirementLabel()));
        }
        if (hasReligionRequirement) {
            textWidth = Math.max(textWidth, this.field_22793.method_1727(tooltip.religionRequirementLabel()));
        }
        if (hasKingdomRestriction) {
            textWidth = Math.max(textWidth, this.field_22793.method_1727(tooltip.kingdomRestrictionLabel()));
        }
        if (hasHouseRequirement) {
            textWidth = Math.max(textWidth, this.field_22793.method_1727(tooltip.houseRequirementLabel()));
        }
        int width = textWidth + 28;
        int height = 14 + lines.size() * 12 + (hasRequirement ? 12 : 0) + (hasReligionRequirement ? 12 : 0) + (hasKingdomRestriction ? 12 : 0) + (hasHouseRequirement ? 12 : 0) + 6;
        int x = Math.min(mouseX + 12, this.field_22789 - width - 8);
        int y = Math.min(mouseY + 12, this.field_22790 - height - 8);
        context.method_51448().method_22903();
        context.method_51448().method_46416(0.0f, 0.0f, 500.0f);
        context.method_25294(x, y, x + width, y + height, -267386864);
        context.method_49601(x, y, width, height, -11513776);
        context.method_25303(this.field_22793, tooltip.name(), x + 6, y + 5, -1);
        int lineY = y + 18;
        this.drawStatLine(context, x + 6, lineY, HEART_ICON, class_1799.field_8037, this.formatStat(tooltip.health()));
        this.drawStatLine(context, x + 6, lineY += 12, ARMOR_ICON, class_1799.field_8037, this.formatStat(tooltip.armor()));
        this.drawStatLine(context, x + 6, lineY += 12, null, new class_1799((class_1935)class_1802.field_8371), this.formatStat(tooltip.damage()));
        this.drawStatLine(context, x + 6, lineY += 12, SPEED_ICON, class_1799.field_8037, String.format(Locale.ROOT, "%.2fx", tooltip.speed()));
        if (hasRequirement) {
            context.method_25303(this.field_22793, tooltip.requirementLabel(), x + 6, (lineY += 12) + 1, tooltip.requirementMet() ? -1 : -24416);
        }
        if (hasReligionRequirement) {
            context.method_25303(this.field_22793, tooltip.religionRequirementLabel(), x + 6, (lineY += 12) + 1, tooltip.religionRequirementMet() ? -1 : -24416);
        }
        if (hasKingdomRestriction) {
            context.method_25303(this.field_22793, tooltip.kingdomRestrictionLabel(), x + 6, (lineY += 12) + 1, tooltip.kingdomRestrictionMet() ? -1 : -24416);
        }
        if (hasHouseRequirement) {
            context.method_25303(this.field_22793, tooltip.houseRequirementLabel(), x + 6, (lineY += 12) + 1, tooltip.houseRequirementMet() ? -1 : -24416);
        }
        context.method_51448().method_22909();
    }

    private void drawStatLine(class_332 context, int x, int y, @Nullable class_2960 texture, class_1799 stack, String text) {
        if (!stack.method_7960()) {
            context.method_51427(stack, x, y - 3);
        } else if (texture != null) {
            if (texture.method_12832().startsWith("hud/")) {
                context.method_52706(texture, x, y, 9, 9);
            } else {
                context.method_25291(texture, x, y, 0, 0.0f, 0.0f, 9, 9, 18, 18);
            }
        }
        context.method_25303(this.field_22793, text, x + 13, y + 1, -1);
    }

    private String formatStat(double value) {
        if (Math.abs(value - Math.rint(value)) < 0.05) {
            return Integer.toString((int)Math.rint(value));
        }
        return String.format(Locale.ROOT, "%.1f", value);
    }

    private void renderEquipmentRows(class_332 context, int x, int y, @Nullable TroopPreviewFactory.TroopPreview preview) {
        int armorRowWidth = 70;
        int armorStartX = x + (64 - armorRowWidth) / 2;
        class_1304[] armorSlots = new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166};
        for (int index = 0; index < armorSlots.length; ++index) {
            this.renderSlot(context, armorStartX + index * 18, y + 100, preview != null ? preview.equipmentEntity().method_6118(armorSlots[index]) : class_1799.field_8037);
        }
        int handRowWidth = 34;
        int handStartX = x + (64 - handRowWidth) / 2;
        this.renderSlot(context, handStartX, y + 118, preview != null ? preview.equipmentEntity().method_6118(class_1304.field_6173) : class_1799.field_8037);
        this.renderSlot(context, handStartX + 16 + 2, y + 118, preview != null ? preview.equipmentEntity().method_6118(class_1304.field_6171) : class_1799.field_8037);
    }

    private void renderSlot(class_332 context, int x, int y, class_1799 stack) {
        context.method_25291(SLOT_TEXTURE, x, y, 0, 0.0f, 0.0f, 16, 16, 16, 16);
        if (!stack.method_7960()) {
            context.method_51427(stack, x, y);
        }
    }

    private void renderTroopPreview(class_332 context, int drawX, int drawY, int mouseX, int mouseY, TroopPreviewFactory.TroopPreview preview) {
        if (preview.mountEntity() != null) {
            int horseLeft = drawX + 4;
            int horseTop = drawY + 30;
            int horseRight = drawX + 64 - 4;
            int horseBottom = drawY + 96 - 6;
            class_490.method_2486((class_332)context, (int)horseLeft, (int)horseTop, (int)horseRight, (int)horseBottom, (int)Math.round(22.0f * preview.scaleMultiplier()), (float)0.0f, (float)mouseX, (float)mouseY, (class_1309)preview.mountEntity());
            class_490.method_2486((class_332)context, (int)(drawX + 13), (int)(drawY + 18), (int)(drawX + 64 - 13), (int)(drawY + 96 - 28), (int)Math.round(20.0f * preview.scaleMultiplier()), (float)0.0f, (float)mouseX, (float)mouseY, (class_1309)preview.renderEntity());
            return;
        }
        class_490.method_2486((class_332)context, (int)(drawX + 7), (int)(drawY + 24), (int)(drawX + 64 - 7), (int)(drawY + 96 - 8), (int)Math.round(28.0f * preview.scaleMultiplier()), (float)0.0f, (float)mouseX, (float)mouseY, (class_1309)preview.renderEntity());
    }

    private void buildLayout() {
        this.nodes.clear();
        this.connections.clear();
        DynamicTroopDefinition selected = DynamicTroopRegistry.get(this.troopId);
        if (selected == null) {
            this.contentHeight = 0;
            return;
        }
        DynamicTroopDefinition root = this.findRoot(selected);
        TreeModel rootModel = this.buildModel(root, new HashSet<class_2960>());
        if (rootModel == null) {
            this.contentHeight = 0;
            return;
        }
        int treeWidth = rootModel.subtreeWidth();
        int startX = Math.max(0, (456 - treeWidth) / 2);
        this.layoutModel(rootModel, startX, 0);
        int maxBottom = 0;
        for (LayoutNode node : this.nodes) {
            maxBottom = Math.max(maxBottom, node.y() + 136);
        }
        this.contentHeight = maxBottom;
    }

    @Nullable
    private TreeModel buildModel(DynamicTroopDefinition definition, Set<class_2960> visited) {
        int subtreeWidth;
        if (definition == null || !visited.add(definition.id())) {
            return null;
        }
        ArrayList<TreeModel> children = new ArrayList<TreeModel>();
        for (class_2960 targetId : definition.upgradeTargets()) {
            DynamicTroopDefinition childDefinition = DynamicTroopRegistry.get(targetId);
            TreeModel child = this.buildModel(childDefinition, visited);
            if (child == null) continue;
            children.add(child);
        }
        if (children.isEmpty()) {
            subtreeWidth = 64;
        } else {
            int totalChildrenWidth = 0;
            for (int index = 0; index < children.size(); ++index) {
                if (index > 0) {
                    totalChildrenWidth += 24;
                }
                totalChildrenWidth += ((TreeModel)children.get(index)).subtreeWidth();
            }
            subtreeWidth = Math.max(64, totalChildrenWidth);
        }
        return new TreeModel(definition, children, subtreeWidth);
    }

    private void layoutModel(TreeModel model, int subtreeLeft, int depth) {
        int y = depth * 164;
        int nodeX = subtreeLeft + (model.subtreeWidth() - 64) / 2;
        LayoutNode layoutNode = new LayoutNode(model.definition(), nodeX, y, TroopPreviewFactory.createPreview(model.definition()));
        this.nodes.add(layoutNode);
        if (model.children().isEmpty()) {
            return;
        }
        int childrenTotalWidth = 0;
        for (int index = 0; index < model.children().size(); ++index) {
            if (index > 0) {
                childrenTotalWidth += 24;
            }
            childrenTotalWidth += model.children().get(index).subtreeWidth();
        }
        int childLeft = subtreeLeft + (model.subtreeWidth() - childrenTotalWidth) / 2;
        for (TreeModel child : model.children()) {
            int childNodeX = childLeft + (child.subtreeWidth() - 64) / 2;
            LayoutNode placeholderChild = new LayoutNode(child.definition(), childNodeX, (depth + 1) * 164, null);
            this.connections.add(new Connection(layoutNode, placeholderChild));
            this.layoutModel(child, childLeft, depth + 1);
            childLeft += child.subtreeWidth() + 24;
        }
    }

    private DynamicTroopDefinition findRoot(DynamicTroopDefinition definition) {
        DynamicTroopDefinition current = definition;
        DynamicTroopDefinition parent;
        while ((parent = this.findParent(current)) != null) {
            current = parent;
        }
        return current;
    }

    @Nullable
    private DynamicTroopDefinition findParent(DynamicTroopDefinition child) {
        for (DynamicTroopDefinition candidate : DynamicTroopRegistry.getByKingdom(child.kingdomId())) {
            if (!candidate.upgradeTargets().contains(child.id())) continue;
            return candidate;
        }
        return null;
    }

    private boolean isWithinViewport(double mouseX, double mouseY) {
        int left = (this.field_22789 - 480) / 2 + 12;
        int top = (this.field_22790 - 288) / 2 + 12;
        return mouseX >= (double)left && mouseX < (double)(left + 456) && mouseY >= (double)top && mouseY < (double)(top + 264);
    }

    private boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    private void clampScroll() {
        int maxScroll = Math.max(0, this.contentHeight - 264);
        this.scrollY = Math.max(0.0, Math.min(this.scrollY, (double)maxScroll));
    }

    @Environment(value=EnvType.CLIENT)
    private record TroopTooltip(String name, int tier, double health, double armor, double damage, double speed, String requirementLabel, boolean requirementMet, String religionRequirementLabel, boolean religionRequirementMet, String kingdomRestrictionLabel, boolean kingdomRestrictionMet, String houseRequirementLabel, boolean houseRequirementMet) {
    }

    @Environment(value=EnvType.CLIENT)
    private record Connection(LayoutNode parent, LayoutNode child) {
    }

    @Environment(value=EnvType.CLIENT)
    private record LayoutNode(DynamicTroopDefinition definition, int x, int y, @Nullable TroopPreviewFactory.TroopPreview preview) {
    }

    @Environment(value=EnvType.CLIENT)
    private record TreeModel(DynamicTroopDefinition definition, List<TreeModel> children, int subtreeWidth) {
    }
}

