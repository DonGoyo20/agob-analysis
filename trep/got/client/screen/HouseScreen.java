/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_437
 *  net.minecraft.class_490
 *  net.minecraft.class_5250
 *  net.minecraft.class_742
 *  net.minecraft.class_8710
 */
package trep.got.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_490;
import net.minecraft.class_5250;
import net.minecraft.class_742;
import net.minecraft.class_8710;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.NpcAppearanceProfile;
import trep.got.client.character.CharacterPreviewSkinResolver;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.gui.PartyMenuPanelRenderer;
import trep.got.client.house.PlayerHouseClientState;
import trep.got.client.house.PlayerHouseRenderHelper;
import trep.got.client.house.PlayerSigilTextureManager;
import trep.got.client.map.CastleMapClientState;
import trep.got.client.screen.MainHubScreen;
import trep.got.client.util.ClientTextureHelper;
import trep.got.network.CastleMapPayload;
import trep.got.network.PlayerHouseStatePayload;
import trep.got.network.RequestSummonMarriedNpcPayload;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotKingdoms;
import trep.got.skill.SkillAttributeType;

@Environment(value=EnvType.CLIENT)
public final class HouseScreen
extends class_437 {
    private static final class_2960 BACKGROUND = class_2960.method_60655((String)"agob", (String)"textures/ui/house_screen.png");
    private static final class_2960 KINGDOM_SLOT = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_slot.png");
    private static final class_2960 KINGDOM_SLOT_ACTIVE = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_slot_active.png");
    private static final class_2960 KINGDOM_TOGGLE = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_toggle.png");
    private static final class_2960 KINGDOM_TOGGLE_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/kingdom/kingdom_toggle_hover.png");
    private static final class_2960 CHARACTER_SELECTION = class_2960.method_60655((String)"agob", (String)"textures/ui/character_selection.png");
    private static final class_2960 BLANK_SIGIL = class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_blank.png");
    private static final class_2960 ROLE_BADGE_BACKGROUND = class_2960.method_60655((String)"agob", (String)"textures/ui/badge_background.png");
    private static final class_2960 ROLE_MARRIAGE_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/wedding.png");
    private static final class_2960 ROLE_KING_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/king.png");
    private static final class_2960 ROLE_DNA_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/dna.png");
    private static final class_2960 ROLE_HOUSE_LEADER_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/leader.png");
    private static final class_2960 SPEED_ICON = class_2960.method_60655((String)"minecraft", (String)"textures/mob_effect/speed.png");
    private static final class_2960 SLOWNESS_ICON = class_2960.method_60655((String)"minecraft", (String)"textures/mob_effect/slowness.png");
    private static final int UI_WIDTH = 176;
    private static final int UI_HEIGHT = 144;
    private static final int SLOT_SIZE = 20;
    private static final int SLOT_GAP = 4;
    private static final int HOUSE_GRID_COLUMNS = 6;
    private static final int HOUSE_GRID_ROWS = 3;
    private static final int HOUSE_GRID_WIDTH = 140;
    private static final int HOUSE_GRID_HEIGHT = 68;
    private static final int HOUSE_ICON_SIZE = 12;
    private static final int DETAIL_SIGIL_SIZE = 42;
    private static final int TOGGLE_WIDTH = 64;
    private static final int TOGGLE_HEIGHT = 32;
    private static final int ENTRY_WIDTH = 96;
    private static final int ENTRY_HEIGHT = 16;
    private static final int ENTRY_GAP = 3;
    private static final int ENTRY_VISIBLE_COUNT = 3;
    private static final int ENTRY_HOVER = 1150273535;
    private static final int ENTRY_ICON_SIZE = 9;
    private static final int ENTRY_ICON_GAP = 4;
    private static final int ENTRY_ROLE_BADGE_SIZE = 15;
    private static final int ENTRY_ROLE_ICON_SIZE = 9;
    private static final int ENTRY_ROLE_ICON_GAP = 2;
    private static final int ENTRY_ROLE_ICON_SPACING = 3;
    private static final int ENTRY_ROLE_BADGE_VERTICAL_OVERFLOW = Math.max(0, 0);
    private static final int BUTTON_ATLAS_SIZE = 256;
    private static final int CONFIRM_U = 16;
    private static final int CONFIRM_V = 160;
    private static final int CONFIRM_HOVER_V = 144;
    private static final int CONFIRM_WIDTH = 64;
    private static final int CONFIRM_HEIGHT = 16;
    private static final int TEXT_COLOR = -1450302;
    private static final int HOVER_TEXT_COLOR = -994470;
    private static final int STAT_ICON_SOURCE_SIZE = 16;
    private static final int STAT_ICON_DRAW_SIZE = 10;
    private static final int STAT_ICON_GAP = 3;
    private static final int STAT_LINE_HEIGHT = 12;
    private static final float STAT_TEXT_SCALE = 0.625f;
    private static final int BOOSTED_CAP_TEXT_COLOR = 10940556;
    private static final int SUMMON_ICON_SIZE = 8;
    private static final int SUMMON_ICON_GAP = 3;
    private RegionTab tab = RegionTab.WESTEROS;
    private HouseEntry selectedHouse;
    private MemberEntry selectedMember;
    private int houseScrollRow;
    private double memberScrollOffset;
    private boolean memberScrollbarDragging;
    private double memberScrollbarDragOffset;
    private String previewKey = "";
    private class_742 previewEntity;
    private class_2561 hoveredRoleTooltip;

    public HouseScreen() {
        super((class_2561)class_2561.method_43470((String)"House"));
    }

    public static void open() {
        class_310.method_1551().method_1507((class_437)new HouseScreen());
    }

    public boolean method_25421() {
        return false;
    }

    protected void method_57734(float delta) {
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.hoveredRoleTooltip = null;
        super.method_25420(context, mouseX, mouseY, delta);
        int uiX = this.uiX();
        int uiY = this.uiY();
        if (this.selectedHouse == null && this.selectedMember != null) {
            this.clearSelectedMember();
        }
        context.method_25291(BACKGROUND, uiX, uiY, 0, 0.0f, 0.0f, 176, 144, 176, 144);
        if (this.selectedHouse == null) {
            this.drawToggle(context, mouseX, mouseY, uiX, uiY);
        }
        if (this.selectedHouse == null) {
            this.drawHouseGrid(context, mouseX, mouseY, uiX, uiY);
        } else {
            this.drawHouseDetails(context, mouseX, mouseY, uiX, uiY);
        }
        this.drawBackButton(context, mouseX, mouseY, uiX, uiY);
        if (this.hoveredRoleTooltip != null) {
            context.method_51438(this.field_22793, this.hoveredRoleTooltip, mouseX, mouseY);
        }
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        HouseEntry clicked;
        if (button != 0) {
            return super.method_25402(mouseX, mouseY, button);
        }
        int uiX = this.uiX();
        int uiY = this.uiY();
        if (this.selectedHouse == null && HouseScreen.isWithin(mouseX, mouseY, HouseScreen.toggleX(uiX), HouseScreen.toggleY(uiY), 64, 32)) {
            this.tab = this.tab.next();
            this.selectedHouse = null;
            this.clearSelectedMember();
            this.houseScrollRow = 0;
            this.memberScrollOffset = 0.0;
            this.memberScrollbarDragging = false;
            return true;
        }
        if (this.selectedMember != null && HouseScreen.isWithin(mouseX, mouseY, this.returnButtonX(), HouseScreen.returnButtonY(uiY), 64, 16)) {
            this.clearSelectedMember();
            return true;
        }
        if (this.canShowSummonButton() && HouseScreen.isWithin(mouseX, mouseY, this.summonButtonX(), HouseScreen.returnButtonY(uiY), 64, 16)) {
            if (!this.isSummonOnCooldown()) {
                ClientPlayNetworking.send((class_8710)new RequestSummonMarriedNpcPayload(this.selectedMember == null ? "" : this.selectedMember.entryId()));
            }
            return true;
        }
        if (this.selectedMember == null && HouseScreen.isWithin(mouseX, mouseY, HouseScreen.backButtonX(uiX), HouseScreen.backButtonY(uiY), 64, 16)) {
            if (this.selectedHouse != null) {
                this.selectedHouse = null;
                this.clearSelectedMember();
                this.memberScrollOffset = 0.0;
                this.memberScrollbarDragging = false;
            } else {
                MainHubScreen.open();
            }
            return true;
        }
        if (this.selectedHouse != null && this.selectedMember == null) {
            ScrollbarMetrics scrollbar;
            List<MemberEntry> entries = this.memberEntries(this.selectedHouse);
            this.clampMemberScroll(entries);
            if (HouseScreen.needsMemberScrollbar(entries) && HouseScreen.isWithin(mouseX, mouseY, (scrollbar = this.memberScrollbarMetrics(uiX, uiY, entries)).x(), scrollbar.y(), scrollbar.width(), scrollbar.height())) {
                if (HouseScreen.isWithin(mouseX, mouseY, scrollbar.x(), scrollbar.thumbY(), scrollbar.width(), scrollbar.thumbHeight())) {
                    this.memberScrollbarDragging = true;
                    this.memberScrollbarDragOffset = mouseY - (double)scrollbar.thumbY();
                } else {
                    this.setMemberScrollFromThumbCenter(entries, mouseY, scrollbar.y(), scrollbar.thumbHeight());
                }
                return true;
            }
            MemberEntry clickedMember = this.memberAt(mouseX, mouseY, entries, uiX, uiY);
            if (clickedMember != null) {
                this.selectedMember = clickedMember;
                this.memberScrollbarDragging = false;
                return true;
            }
        }
        if (this.selectedHouse == null && (clicked = this.houseAt(mouseX, mouseY, this.houseEntries(), uiX, uiY)) != null) {
            this.selectedHouse = clicked;
            this.clearSelectedMember();
            this.memberScrollOffset = 0.0;
            this.memberScrollbarDragging = false;
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.memberScrollbarDragging && this.selectedHouse != null) {
            List<MemberEntry> entries = this.memberEntries(this.selectedHouse);
            if (!HouseScreen.needsMemberScrollbar(entries)) {
                this.memberScrollbarDragging = false;
                return false;
            }
            ScrollbarMetrics scrollbar = this.memberScrollbarMetrics(this.uiX(), this.uiY(), entries);
            this.setMemberScrollFromThumbY(entries, mouseY - this.memberScrollbarDragOffset, scrollbar.y(), scrollbar.thumbHeight());
            return true;
        }
        return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        this.memberScrollbarDragging = false;
        return super.method_25406(mouseX, mouseY, button);
    }

    public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        int uiX = this.uiX();
        int uiY = this.uiY();
        if (this.selectedHouse == null && HouseScreen.isWithin(mouseX, mouseY, HouseScreen.gridX(uiX), HouseScreen.gridY(uiY), 140, 68)) {
            int maxRow = this.maxHouseScrollRow(this.houseEntries());
            this.houseScrollRow = class_3532.method_15340((int)(this.houseScrollRow - (int)Math.signum(verticalAmount)), (int)0, (int)maxRow);
            return true;
        }
        if (this.selectedHouse != null && HouseScreen.isWithin(mouseX, mouseY, HouseScreen.memberX(uiX), HouseScreen.memberY(uiY), HouseScreen.memberScrollHitWidth(this.memberEntries(this.selectedHouse)), HouseScreen.memberViewportHeight())) {
            if (this.selectedMember != null) {
                return false;
            }
            List<MemberEntry> entries = this.memberEntries(this.selectedHouse);
            if (!HouseScreen.needsMemberScrollbar(entries)) {
                this.memberScrollbarDragging = false;
                this.memberScrollOffset = 0.0;
                return false;
            }
            this.memberScrollOffset = class_3532.method_15350((double)(this.memberScrollOffset - verticalAmount * 19.0), (double)0.0, (double)HouseScreen.maxMemberScroll(entries));
            return true;
        }
        return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    private void drawToggle(class_332 context, int mouseX, int mouseY, int uiX, int uiY) {
        int y;
        int x = HouseScreen.toggleX(uiX);
        boolean hovered = HouseScreen.isWithin(mouseX, mouseY, x, y = HouseScreen.toggleY(uiY), 64, 32);
        context.method_25290(hovered ? KINGDOM_TOGGLE_HOVER : KINGDOM_TOGGLE, x, y, 0.0f, 0.0f, 64, 32, 64, 32);
        class_5250 class_52502 = class_2561.method_43470((String)this.tab.label());
        Objects.requireNonNull(this.field_22793);
        context.method_27534(this.field_22793, (class_2561)class_52502, x + 32, y + (32 - 9) / 2, hovered ? -994470 : -1450302);
    }

    private void drawHouseGrid(class_332 context, int mouseX, int mouseY, int uiX, int uiY) {
        int visibleIndex;
        int row;
        List<HouseEntry> entries = this.houseEntries();
        int startIndex = this.houseScrollRow * 6;
        context.method_44379(HouseScreen.gridX(uiX), HouseScreen.gridY(uiY), HouseScreen.gridX(uiX) + 140, HouseScreen.gridY(uiY) + 68);
        for (int index = startIndex; index < entries.size() && (row = (visibleIndex = index - startIndex) / 6) < 3; ++index) {
            int y;
            int column = visibleIndex % 6;
            HouseEntry house = entries.get(index);
            int x = HouseScreen.gridX(uiX) + column * 24;
            boolean hovered = HouseScreen.isWithin(mouseX, mouseY, x, y = HouseScreen.gridY(uiY) + row * 24, 20, 20);
            context.method_25290(hovered ? KINGDOM_SLOT_ACTIVE : KINGDOM_SLOT, x, y, 0.0f, 0.0f, 20, 20, 20, 20);
            HouseScreen.drawFittedTexture(context, house.texture(), x + 4, y + 4, 12, 12);
        }
        context.method_44380();
    }

    private void drawHouseDetails(class_332 context, int mouseX, int mouseY, int uiX, int uiY) {
        int sigilX = uiX + 14;
        int sigilY = uiY + 40;
        HouseScreen.drawFittedTexture(context, this.selectedHouse.texture(), sigilX, sigilY, 42, 42);
        if (this.selectedMember != null) {
            this.drawSelectedMemberPreview(context, mouseX, mouseY, sigilX, sigilY);
            this.drawScaledCenteredText(context, this.selectedMember.displayName(), sigilX - 4, sigilY + 42 + 5, 50, 0.7f);
            this.drawAttributeRows(context, this.selectedMember, HouseScreen.memberX(uiX), HouseScreen.memberY(uiY) - 32);
            this.drawReturnButton(context, mouseX, mouseY, uiY);
            return;
        }
        this.drawScaledCenteredText(context, this.selectedHouse.displayName(), sigilX - 4, sigilY + 42 + 5, 50, 0.7f);
        List<MemberEntry> entries = this.memberEntries(this.selectedHouse);
        int x = HouseScreen.memberX(uiX);
        int y = HouseScreen.memberY(uiY);
        this.clampMemberScroll(entries);
        context.method_44379(x - HouseScreen.maxRoleIconDrawWidth(entries), y - ENTRY_ROLE_BADGE_VERTICAL_OVERFLOW, x + 96, y + HouseScreen.memberViewportHeight() + ENTRY_ROLE_BADGE_VERTICAL_OVERFLOW);
        int currentY = y - (int)Math.floor(this.memberScrollOffset);
        for (MemberEntry entry : entries) {
            if (currentY + 16 >= y && currentY <= y + HouseScreen.memberViewportHeight()) {
                boolean hovered = HouseScreen.isWithin(mouseX, mouseY, x, currentY, 96, 16);
                this.drawMemberEntry(context, entry, x, currentY, hovered, mouseX, mouseY);
            }
            currentY += 19;
        }
        context.method_44380();
        if (HouseScreen.needsMemberScrollbar(entries)) {
            this.drawMemberScrollbar(context, uiX, uiY, entries, mouseX, mouseY);
        }
    }

    private void drawMemberEntry(class_332 context, MemberEntry entry, int x, int y, boolean hovered, int mouseX, int mouseY) {
        this.drawRoleIcons(context, entry.roleIconIds(), entry.kingdomId(), x, y, mouseX, mouseY);
        context.method_25290(PartyMenuPanelRenderer.UNIT_ENTRY_TEXTURE, x, y, 0.0f, 0.0f, 96, 16, 96, 16);
        if (hovered) {
            context.method_25294(x, y, x + 96, y + 16, 1150273535);
        }
        int textX = x + 5;
        int textY = y + 4;
        class_2960 icon = this.resolveMemberSigil(entry);
        HouseScreen.drawFittedTexture(context, icon, textX, y + 3, 9, 9);
        int viewportWidth = 96 - ((textX += 13) - x) - 5;
        String displayName = this.field_22793.method_27523(entry.displayName(), viewportWidth);
        context.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)displayName), textX, textY, hovered ? -994470 : -1);
    }

    private class_2960 resolveMemberSigil(MemberEntry entry) {
        if (this.selectedHouse == null) {
            return BLANK_SIGIL;
        }
        class_2960 resolved = PlayerHouseRenderHelper.resolveTexture(this.selectedHouse.houseId(), entry.kingdomId());
        return resolved != null ? resolved : BLANK_SIGIL;
    }

    private void drawRoleIcons(class_332 context, List<String> roleIconIds, String kingdomId, int x, int y, int mouseX, int mouseY) {
        if (roleIconIds == null || roleIconIds.isEmpty()) {
            return;
        }
        int totalWidth = roleIconIds.size() * 15 + (roleIconIds.size() - 1) * 3;
        int iconX = x - 2 - totalWidth;
        int badgeY = y + 0;
        for (String roleIconId : roleIconIds) {
            class_2960 roleIcon = HouseScreen.resolveRoleIcon(roleIconId);
            if (roleIcon != null) {
                HouseScreen.drawFittedTexture(context, ROLE_BADGE_BACKGROUND, iconX, badgeY, 15, 15);
                int innerIconX = iconX + 3;
                int innerIconY = badgeY + 3;
                HouseScreen.drawFittedTexture(context, roleIcon, innerIconX, innerIconY, 9, 9);
                if (HouseScreen.isWithin(mouseX, mouseY, iconX, badgeY, 15, 15)) {
                    this.hoveredRoleTooltip = HouseScreen.roleIconTooltip(roleIconId, kingdomId);
                }
            }
            iconX += 18;
        }
    }

    private static int maxRoleIconDrawWidth(List<MemberEntry> entries) {
        int maxCount = 0;
        for (MemberEntry entry : entries) {
            maxCount = Math.max(maxCount, entry.roleIconIds() == null ? 0 : entry.roleIconIds().size());
        }
        if (maxCount <= 0) {
            return 17;
        }
        return 2 + maxCount * 15 + (maxCount - 1) * 3;
    }

    private static class_2960 resolveRoleIcon(String roleIconId) {
        return switch (roleIconId == null ? "" : roleIconId) {
            case "married" -> ROLE_MARRIAGE_ICON;
            case "king" -> ROLE_KING_ICON;
            case "child" -> ROLE_DNA_ICON;
            case "house_leader" -> ROLE_HOUSE_LEADER_ICON;
            default -> null;
        };
    }

    private static class_2561 roleIconTooltip(String roleIconId, String kingdomId) {
        return class_2561.method_43470((String)(switch (roleIconId == null ? "" : roleIconId) {
            case "king" -> "King";
            case "married" -> "Married";
            case "child" -> "Child";
            case "house_leader" -> {
                if ("agob:the_nights_watch".equals(kingdomId)) {
                    yield "Lord Commander";
                }
                yield "House Leader";
            }
            default -> "";
        }));
    }

    private void drawBackButton(class_332 context, int mouseX, int mouseY, int uiX, int uiY) {
        int y;
        int x;
        if (this.selectedMember != null) {
            return;
        }
        boolean hovered = HouseScreen.isWithin(mouseX, mouseY, x = HouseScreen.backButtonX(uiX), y = HouseScreen.backButtonY(uiY), 64, 16);
        HouseScreen.drawScaledTexture(context, CHARACTER_SELECTION, x, y, 16, hovered ? 144 : 160, 64, 16, 64, 16);
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Back"), x + 32, y + 4, hovered ? -994470 : -1);
    }

    private void drawReturnButton(class_332 context, int mouseX, int mouseY, int uiY) {
        int y;
        int x;
        boolean hovered = HouseScreen.isWithin(mouseX, mouseY, x = this.returnButtonX(), y = HouseScreen.returnButtonY(uiY), 64, 16);
        HouseScreen.drawScaledTexture(context, CHARACTER_SELECTION, x, y, 16, hovered ? 144 : 160, 64, 16, 64, 16);
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Return"), x + 32, y + 4, hovered ? -994470 : -1);
        if (this.canShowSummonButton()) {
            this.drawSummonButton(context, mouseX, mouseY, y);
        }
    }

    private void drawSummonButton(class_332 context, int mouseX, int mouseY, int y) {
        int x = this.summonButtonX();
        boolean hovered = HouseScreen.isWithin(mouseX, mouseY, x, y, 64, 16);
        boolean cooldown = this.isSummonOnCooldown();
        HouseScreen.drawScaledTexture(context, CHARACTER_SELECTION, x, y, 16, hovered ? 144 : 160, 64, 16, 64, 16);
        if (cooldown) {
            context.method_25294(x, y, x + 64, y + 16, -1728053248);
        }
        class_2960 icon = cooldown ? SLOWNESS_ICON : SPEED_ICON;
        String label = cooldown ? this.summonCooldownLabel() : "Summon";
        int textWidth = this.field_22793.method_1727(label);
        int contentWidth = 11 + textWidth;
        int textX = x + (64 - contentWidth) / 2 + 8 + 3;
        int iconX = textX - 3 - 8;
        context.method_25291(icon, iconX, y + 4, 0, 0.0f, 0.0f, 8, 8, 8, 8);
        context.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)label), textX, y + 4, cooldown ? -6250336 : (hovered ? -994470 : -1));
    }

    private HouseEntry houseAt(double mouseX, double mouseY, List<HouseEntry> entries, int uiX, int uiY) {
        int startIndex;
        for (int index = startIndex = this.houseScrollRow * 6; index < entries.size(); ++index) {
            int y;
            int visibleIndex = index - startIndex;
            int row = visibleIndex / 6;
            if (row >= 3) {
                return null;
            }
            int column = visibleIndex % 6;
            int x = HouseScreen.gridX(uiX) + column * 24;
            if (!HouseScreen.isWithin(mouseX, mouseY, x, y = HouseScreen.gridY(uiY) + row * 24, 20, 20)) continue;
            return entries.get(index);
        }
        return null;
    }

    private MemberEntry memberAt(double mouseX, double mouseY, List<MemberEntry> entries, int uiX, int uiY) {
        int x = HouseScreen.memberX(uiX);
        int viewportY = HouseScreen.memberY(uiY);
        int currentY = viewportY - (int)Math.floor(this.memberScrollOffset);
        for (MemberEntry entry : entries) {
            if (currentY + 16 >= viewportY && currentY <= viewportY + HouseScreen.memberViewportHeight() && HouseScreen.isWithin(mouseX, mouseY, x, currentY, 96, 16)) {
                return entry;
            }
            currentY += 19;
        }
        return null;
    }

    private List<HouseEntry> houseEntries() {
        LinkedHashMap<String, HouseEntry> entries = new LinkedHashMap<String, HouseEntry>();
        if (this.tab == RegionTab.PLAYER) {
            ArrayList<PlayerHouseStatePayload.Entry> customHouses = new ArrayList<PlayerHouseStatePayload.Entry>(PlayerHouseClientState.getAll());
            customHouses.sort(Comparator.comparing(PlayerHouseStatePayload.Entry::displayName));
            for (PlayerHouseStatePayload.Entry customHouse : customHouses) {
                entries.put(HouseScreen.key(customHouse.kingdomId(), customHouse.houseId()), new HouseEntry(customHouse.houseId(), customHouse.kingdomId(), customHouse.displayName(), PlayerSigilTextureManager.getTextureId(customHouse), true));
            }
            return List.copyOf(entries.values());
        }
        for (CharacterSelectionOption option : GotCharacterOptions.getAll()) {
            String kingdomId = option.kingdomId().toString();
            if (this.tab == RegionTab.WESTEROS && !GotKingdoms.isWesterosiKingdom(kingdomId) || this.tab == RegionTab.ESSOS && !GotKingdoms.isEssosKingdom(kingdomId)) continue;
            entries.put(HouseScreen.key(kingdomId, option.id()), new HouseEntry(option.id(), kingdomId, option.houseDisplayName(), option.texture(), false));
        }
        return List.copyOf(entries.values());
    }

    private List<MemberEntry> memberEntries(HouseEntry house) {
        ArrayList<MemberEntry> entries = new ArrayList<MemberEntry>();
        if (!house.playerHouse()) {
            for (PlayerHouseStatePayload.HouseLordEntry houseLordEntry : PlayerHouseClientState.getHouseLords(house.kingdomId(), house.houseId())) {
                if (houseLordEntry.displayName().isBlank()) continue;
                entries.add(new MemberEntry("", houseLordEntry.displayName(), MemberKind.LORD, "", houseLordEntry.marriedToName(), houseLordEntry.locationName(), "", 0, "", houseLordEntry.roleIconIds(), false, 0L, house.kingdomId(), "", 1, 1, 0, houseLordEntry.skillPoints(), HouseScreen.defaultSkillMaxPoints()));
            }
            if (entries.isEmpty()) {
                entries.addAll(this.fallbackCastleLordEntries(house));
            }
        }
        LinkedHashMap<String, MemberEntry> playerEntries = new LinkedHashMap<String, MemberEntry>();
        for (PlayerHouseStatePayload.MemberEntry player : PlayerHouseClientState.getMembers(house.kingdomId(), house.houseId())) {
            playerEntries.put(player.entryId(), new MemberEntry(player.entryId(), player.playerName(), HouseScreen.resolveMemberKind(player), player.playerUuid(), player.marriedToName(), player.locationName(), player.parentsLine(), player.ageDays(), player.familyStageId(), player.roleIconIds(), player.spouseInParty(), player.summonCooldownEndsAtMs(), player.kingdomId(), player.npcGender(), player.npcSkinVariant(), player.npcHairVariant(), player.npcClothesVariant(), player.skillPoints(), player.skillMaxPoints()));
        }
        class_310 class_3102 = class_310.method_1551();
        if (class_3102.field_1724 != null && HouseScreen.isCurrentPlayerHouse(house)) {
            PlayerHouseStatePayload.MemberEntry syncedPlayer = PlayerHouseClientState.getMembers(house.kingdomId(), house.houseId()).stream().filter(entry -> client.field_1724.method_5845().equals(entry.playerUuid())).findFirst().orElse(null);
            playerEntries.put(class_3102.field_1724.method_5845(), new MemberEntry(class_3102.field_1724.method_5845(), class_3102.field_1724.method_5477().getString(), MemberKind.PLAYER, class_3102.field_1724.method_5845(), syncedPlayer == null ? "" : syncedPlayer.marriedToName(), syncedPlayer == null ? "" : syncedPlayer.locationName(), syncedPlayer == null ? "" : syncedPlayer.parentsLine(), syncedPlayer == null ? 0 : syncedPlayer.ageDays(), syncedPlayer == null ? "" : syncedPlayer.familyStageId(), syncedPlayer == null ? List.of() : syncedPlayer.roleIconIds(), false, 0L, house.kingdomId(), "", 1, 1, 0, HouseScreen.currentPlayerSkillPoints(), HouseScreen.currentPlayerSkillMaxPoints()));
        }
        entries.addAll(playerEntries.values());
        return List.copyOf(entries);
    }

    private List<MemberEntry> fallbackCastleLordEntries(HouseEntry house) {
        LinkedHashMap<String, MemberEntry> fallback = new LinkedHashMap<String, MemberEntry>();
        int[] skills = HouseScreen.fallbackLordSkillPoints(house);
        for (CastleMapPayload.Entry castle : CastleMapClientState.getCastles()) {
            String lordName;
            if (!house.kingdomId().equals(castle.kingdomId()) || !house.houseId().equals(castle.houseId()) || (lordName = castle.lordName()) == null || lordName.isBlank()) continue;
            fallback.putIfAbsent(lordName, new MemberEntry("", lordName, MemberKind.LORD, "", "", castle.name(), "", 0, "", List.of(), false, 0L, house.kingdomId(), "", 1, 1, 0, skills, HouseScreen.defaultSkillMaxPoints()));
        }
        return List.copyOf(fallback.values());
    }

    private static int[] fallbackLordSkillPoints(HouseEntry house) {
        CharacterSelectionOption option = GotCharacterOptions.get(house.houseId());
        if (option == null) {
            class_2960 kingdomId = class_2960.method_12829((String)house.kingdomId());
            option = kingdomId == null ? null : GotCharacterOptions.getByKingdomId(kingdomId);
        }
        int[] values = new int[SkillAttributeType.ALL.size()];
        if (option == null) {
            return values;
        }
        for (int index = 0; index < SkillAttributeType.ALL.size(); ++index) {
            values[index] = option.startingSkillPoints().getOrDefault((Object)SkillAttributeType.ALL.get(index), 0);
        }
        return values;
    }

    private static boolean isCurrentPlayerHouse(HouseEntry house) {
        PlayerHouseStatePayload.Entry customHouse;
        class_310 client = class_310.method_1551();
        if (client != null && client.field_1724 != null && (customHouse = PlayerHouseClientState.getByOwnerUuid(client.field_1724.method_5845())) != null) {
            return house.houseId().equals(customHouse.houseId()) && house.kingdomId().equals(customHouse.kingdomId());
        }
        return house.houseId().equals(CharacterSelectionClientState.getSelectedOptionId()) && house.kingdomId().equals(CharacterSelectionClientState.getSelectedKingdomId());
    }

    private void clearSelectedMember() {
        this.selectedMember = null;
        this.previewKey = "";
        this.previewEntity = null;
    }

    private int maxHouseScrollRow(List<HouseEntry> entries) {
        int rows = (int)Math.ceil((double)entries.size() / 6.0);
        return Math.max(0, rows - 3);
    }

    private static boolean needsMemberScrollbar(List<MemberEntry> entries) {
        return entries.size() > 3;
    }

    private static int memberContentHeight(List<MemberEntry> entries) {
        if (entries.isEmpty()) {
            return 0;
        }
        return entries.size() * 16 + (entries.size() - 1) * 3;
    }

    private static int memberScrollHitWidth(List<MemberEntry> entries) {
        return 96 + (HouseScreen.needsMemberScrollbar(entries) ? 9 : 0);
    }

    private static double maxMemberScroll(List<MemberEntry> entries) {
        return Math.max(0.0, (double)(HouseScreen.memberContentHeight(entries) - HouseScreen.memberViewportHeight()));
    }

    private void clampMemberScroll(List<MemberEntry> entries) {
        if (!HouseScreen.needsMemberScrollbar(entries)) {
            this.memberScrollbarDragging = false;
            this.memberScrollOffset = 0.0;
            return;
        }
        this.memberScrollOffset = class_3532.method_15350((double)this.memberScrollOffset, (double)0.0, (double)HouseScreen.maxMemberScroll(entries));
    }

    private void drawMemberScrollbar(class_332 context, int uiX, int uiY, List<MemberEntry> entries, int mouseX, int mouseY) {
        ScrollbarMetrics scrollbar = this.memberScrollbarMetrics(uiX, uiY, entries);
        context.method_25294(scrollbar.x(), scrollbar.y(), scrollbar.x() + scrollbar.width(), scrollbar.y() + scrollbar.height(), 1713377290);
        context.method_49601(scrollbar.x(), scrollbar.y(), scrollbar.width(), scrollbar.height(), -1433700523);
        boolean hovered = HouseScreen.isWithin(mouseX, mouseY, scrollbar.x(), scrollbar.thumbY(), scrollbar.width(), scrollbar.thumbHeight());
        context.method_25291(PartyMenuPanelRenderer.SCROLLER_TEXTURE, scrollbar.x(), scrollbar.thumbY(), 0, 0.0f, 0.0f, 6, 27, 6, 27);
        if (hovered || this.memberScrollbarDragging) {
            context.method_25294(scrollbar.x(), scrollbar.thumbY(), scrollbar.x() + scrollbar.width(), scrollbar.thumbY() + scrollbar.thumbHeight(), 0x22FFFFFF);
        }
    }

    private ScrollbarMetrics memberScrollbarMetrics(int uiX, int uiY, List<MemberEntry> entries) {
        return this.memberScrollbarMetrics(HouseScreen.memberX(uiX) + 96 + 3, HouseScreen.memberY(uiY), HouseScreen.memberContentHeight(entries));
    }

    private ScrollbarMetrics memberScrollbarMetrics(int x, int y, int contentHeight) {
        int height = HouseScreen.memberViewportHeight();
        int thumbHeight = Math.min(height, 27);
        double maxScroll = Math.max(0.0, (double)(contentHeight - height));
        int thumbTravel = height - thumbHeight;
        int thumbY = y + (maxScroll <= 0.0 ? 0 : (int)Math.round(this.memberScrollOffset / maxScroll * (double)thumbTravel));
        return new ScrollbarMetrics(x, y, 6, height, thumbY, thumbHeight);
    }

    private void setMemberScrollFromThumbCenter(List<MemberEntry> entries, double mouseY, int scrollbarY, int thumbHeight) {
        this.setMemberScrollFromThumbY(entries, mouseY - (double)thumbHeight / 2.0, scrollbarY, thumbHeight);
    }

    private void setMemberScrollFromThumbY(List<MemberEntry> entries, double thumbY, int scrollbarY, int thumbHeight) {
        double maxScroll = HouseScreen.maxMemberScroll(entries);
        if (maxScroll <= 0.0) {
            this.memberScrollOffset = 0.0;
            return;
        }
        ScrollbarMetrics scrollbar = this.memberScrollbarMetrics(0, scrollbarY, HouseScreen.memberContentHeight(entries));
        int thumbTravel = scrollbar.height() - scrollbar.thumbHeight();
        if (thumbTravel <= 0) {
            this.memberScrollOffset = 0.0;
            return;
        }
        double clampedThumbY = class_3532.method_15350((double)thumbY, (double)scrollbarY, (double)(scrollbarY + thumbTravel));
        double progress = (clampedThumbY - (double)scrollbarY) / (double)thumbTravel;
        this.memberScrollOffset = class_3532.method_15350((double)(progress * maxScroll), (double)0.0, (double)maxScroll);
    }

    private void drawSelectedMemberPreview(class_332 context, int mouseX, int mouseY, int sigilX, int sigilY) {
        class_742 entity = this.resolveSelectedMemberPreview();
        if (entity == null) {
            return;
        }
        class_490.method_2486((class_332)context, (int)(sigilX + 2), (int)(sigilY - 8), (int)(sigilX + 42 - 2), (int)(sigilY + 42 + 8), (int)28, (float)0.0f, (float)mouseX, (float)mouseY, (class_1309)entity);
    }

    private class_742 resolveSelectedMemberPreview() {
        if (this.selectedMember == null || this.selectedHouse == null) {
            return null;
        }
        class_310 client = class_310.method_1551();
        if (!(this.selectedMember.kind() != MemberKind.PLAYER && this.selectedMember.kind() != MemberKind.CHILD && this.selectedMember.kind() != MemberKind.DYNASTY || this.selectedMember.playerUuid().isBlank())) {
            return HouseScreen.playerEntity(this.selectedMember.playerUuid());
        }
        String key = this.selectedHouse.kingdomId() + "|" + this.selectedHouse.houseId() + "|" + this.selectedMember.displayName() + "|" + this.selectedMember.npcGender() + "|" + this.selectedMember.npcSkinVariant() + "|" + this.selectedMember.npcHairVariant() + "|" + this.selectedMember.npcClothesVariant();
        if (this.previewEntity != null && key.equals(this.previewKey)) {
            return this.previewEntity;
        }
        CharacterSelectionOption option = GotCharacterOptions.get(this.selectedHouse.houseId());
        if (this.selectedMember.kind() == MemberKind.NPC_SPOUSE) {
            class_2960 memberKingdomId = class_2960.method_12829((String)this.selectedMember.kingdomId());
            CharacterSelectionOption characterSelectionOption = option = memberKingdomId == null ? option : GotCharacterOptions.getByKingdomId(memberKingdomId);
        }
        if (option == null) {
            class_2960 kingdomId = class_2960.method_12829((String)this.selectedHouse.kingdomId());
            CharacterSelectionOption characterSelectionOption = option = kingdomId == null ? null : GotCharacterOptions.getByKingdomId(kingdomId);
        }
        if (client.field_1687 == null || option == null) {
            return null;
        }
        this.previewKey = key;
        this.previewEntity = this.selectedMember.kind() == MemberKind.NPC_SPOUSE || !this.selectedMember.npcGender().isBlank() ? CharacterPreviewSkinResolver.createNpcPreviewEntity(client.field_1687, option, new NpcAppearanceProfile(this.selectedMember.npcGender(), this.selectedMember.npcSkinVariant(), this.selectedMember.npcHairVariant(), this.selectedMember.npcClothesVariant())) : CharacterPreviewSkinResolver.createPreviewEntity(client.field_1687, option, null);
        if (this.previewEntity != null) {
            this.previewEntity.method_5673(class_1304.field_6169, class_1799.field_8037);
        }
        return this.previewEntity;
    }

    private static class_742 playerEntity(String uuid) {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null) {
            return null;
        }
        if (client.field_1724.method_5845().equals(uuid)) {
            return client.field_1724;
        }
        if (client.field_1687 == null) {
            return client.field_1724;
        }
        try {
            UUID parsedUuid = UUID.fromString(uuid);
            for (class_742 player : client.field_1687.method_18456()) {
                if (!player.method_5667().equals(parsedUuid)) continue;
                return player;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        return client.field_1724;
    }

    private void drawAttributeRows(class_332 context, MemberEntry member, int x, int y) {
        int baseY = y;
        if (!member.marriedToName().isBlank()) {
            this.drawScaledText(context, (class_2561)class_2561.method_43470((String)("Married to: " + member.marriedToName())), x, y, -1450302, 0.625f);
            baseY += 12;
        }
        if (!member.locationName().isBlank()) {
            this.drawScaledText(context, (class_2561)class_2561.method_43470((String)("Location: " + member.locationName())), x, baseY, -1450302, 0.625f);
            baseY += 12;
        }
        if (!member.parentsLine().isBlank()) {
            this.drawScaledText(context, (class_2561)class_2561.method_43470((String)"Parents:"), x, baseY, -1450302, 0.625f);
            this.drawScaledText(context, (class_2561)class_2561.method_43470((String)member.parentsLine()), x, baseY += 12, -1450302, 0.625f);
            baseY += 12;
        }
        if (member.kind() == MemberKind.CHILD) {
            this.drawScaledText(context, (class_2561)class_2561.method_43470((String)("Age: " + member.ageDays())), x, baseY, -1450302, 0.625f);
            baseY += 12;
        }
        for (int index = 0; index < SkillAttributeType.ALL.size(); ++index) {
            SkillAttributeType type = SkillAttributeType.ALL.get(index);
            int amount = HouseScreen.skillValue(member.skillPoints(), index, 0);
            int max = HouseScreen.skillValue(member.skillMaxPoints(), index, 100);
            int lineY = baseY + index * 12;
            HouseScreen.drawScaledIcon(context, type.getIconTexture(), x, lineY, 10, 16, 0.625f);
            int textX = x + Math.round(8.125f);
            this.drawScaledText(context, (class_2561)class_2561.method_43470((String)(type.getDisplayName() + " " + amount + "/")), textX, lineY, -1450302, 0.625f);
            int maxX = textX + Math.round((float)this.field_22793.method_1727(type.getDisplayName() + " " + amount + "/") * 0.625f);
            this.drawScaledText(context, (class_2561)class_2561.method_43470((String)String.valueOf(max)), maxX, lineY, max > 100 ? 10940556 : -1450302, 0.625f);
        }
    }

    private void drawScaledText(class_332 context, class_2561 text, float x, float y, int color, float scale) {
        context.method_51448().method_22903();
        context.method_51448().method_46416(x, y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_27535(this.field_22793, text, 0, 0, color);
        context.method_51448().method_22909();
    }

    private static void drawScaledIcon(class_332 context, class_2960 texture, float x, float y, int drawSize, int textureSize, float scale) {
        context.method_51448().method_22903();
        float fitScale = (float)drawSize / (float)textureSize;
        float iconScale = scale * fitScale;
        float offset = Math.max(0.0f, ((float)drawSize - (float)textureSize * fitScale * scale) / 2.0f);
        context.method_51448().method_46416(x + offset, y + offset, 0.0f);
        context.method_51448().method_22905(iconScale, iconScale, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, textureSize, textureSize, textureSize, textureSize);
        context.method_51448().method_22909();
    }

    private static int[] currentPlayerSkillPoints() {
        int[] values = new int[SkillAttributeType.ALL.size()];
        for (int index = 0; index < SkillAttributeType.ALL.size(); ++index) {
            values[index] = CharacterSelectionClientState.getSkillPoints(SkillAttributeType.ALL.get(index));
        }
        return values;
    }

    private static int[] currentPlayerSkillMaxPoints() {
        int[] values = new int[SkillAttributeType.ALL.size()];
        for (int index = 0; index < SkillAttributeType.ALL.size(); ++index) {
            values[index] = CharacterSelectionClientState.getSkillMaxPoints(SkillAttributeType.ALL.get(index));
        }
        return values;
    }

    private static int[] defaultSkillMaxPoints() {
        int[] values = new int[SkillAttributeType.ALL.size()];
        for (int index = 0; index < values.length; ++index) {
            values[index] = 100;
        }
        return values;
    }

    private static int skillValue(int[] values, int index, int defaultValue) {
        return values == null || index < 0 || index >= values.length ? defaultValue : values[index];
    }

    private static MemberKind resolveMemberKind(PlayerHouseStatePayload.MemberEntry entry) {
        if (!entry.npc()) {
            return MemberKind.PLAYER;
        }
        return switch (entry.familyStageId()) {
            case "child" -> MemberKind.CHILD;
            case "dynasty" -> MemberKind.DYNASTY;
            default -> MemberKind.NPC_SPOUSE;
        };
    }

    private void drawScaledCenteredText(class_332 context, String text, int x, int y, int width, float scale) {
        String value = text == null ? "" : text;
        int textWidth = this.field_22793.method_1727(value);
        float effectiveScale = Math.min(scale, (float)width / (float)Math.max(1, textWidth));
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x + (float)width / 2.0f, (float)y, 0.0f);
        context.method_51448().method_22905(effectiveScale, effectiveScale, 1.0f);
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)value), 0, 0, -1450302);
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

    private static void drawScaledTexture(class_332 context, class_2960 texture, int x, int y, int u, int v, int width, int height, int drawWidth, int drawHeight) {
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        context.method_25293(texture, x, y, drawWidth, drawHeight, (float)u, (float)v, width, height, 256, 256);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private int uiX() {
        return (this.field_22789 - 176) / 2;
    }

    private int uiY() {
        return (this.field_22790 - 144) / 2;
    }

    private int returnButtonX() {
        return (this.field_22789 - 64) / 2;
    }

    private int summonButtonX() {
        return this.returnButtonX() - 64 - 4;
    }

    private static int toggleX(int uiX) {
        return uiX + 56;
    }

    private static int toggleY(int uiY) {
        return uiY + 5;
    }

    private static int gridX(int uiX) {
        return uiX + 18;
    }

    private static int gridY(int uiY) {
        return uiY + 42;
    }

    private static int memberX(int uiX) {
        return uiX + 68;
    }

    private static int memberY(int uiY) {
        return uiY + 40;
    }

    private static int memberViewportHeight() {
        return 54;
    }

    private boolean canShowSummonButton() {
        return !(this.selectedHouse == null || this.selectedMember == null || this.selectedMember.kind() != MemberKind.NPC_SPOUSE && this.selectedMember.kind() != MemberKind.CHILD && this.selectedMember.kind() != MemberKind.DYNASTY || !HouseScreen.isCurrentPlayerHouse(this.selectedHouse) || !HouseScreen.isOwnedByCurrentPlayer(this.selectedMember) || this.selectedMember.kind() == MemberKind.NPC_SPOUSE && this.selectedMember.spouseInParty());
    }

    private static boolean isOwnedByCurrentPlayer(MemberEntry member) {
        class_310 client = class_310.method_1551();
        return client != null && client.field_1724 != null && member != null && client.field_1724.method_5845().equals(member.playerUuid());
    }

    private boolean isSummonOnCooldown() {
        return this.selectedMember != null && this.selectedMember.summonCooldownEndsAtMs() > System.currentTimeMillis();
    }

    private String summonCooldownLabel() {
        if (this.selectedMember == null) {
            return "2:00";
        }
        long remainingMs = Math.max(0L, this.selectedMember.summonCooldownEndsAtMs() - System.currentTimeMillis());
        long totalSeconds = (remainingMs + 999L) / 1000L;
        long minutes = totalSeconds / 60L;
        long seconds = totalSeconds % 60L;
        return minutes + ":" + (seconds < 10L ? "0" : "") + seconds;
    }

    private static int backButtonX(int uiX) {
        return uiX + 56;
    }

    private static int backButtonY(int uiY) {
        return uiY + 144 - 16 - 4;
    }

    private static int returnButtonY(int uiY) {
        return uiY + 144 + 8;
    }

    private static boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    private static String key(String kingdomId, String houseId) {
        return kingdomId + "|" + houseId;
    }

    @Environment(value=EnvType.CLIENT)
    private static enum RegionTab {
        WESTEROS("Westeros"),
        ESSOS("Essos"),
        PLAYER("Player");

        private final String label;

        private RegionTab(String label) {
            this.label = label;
        }

        private String label() {
            return this.label;
        }

        private RegionTab next() {
            RegionTab[] values = RegionTab.values();
            return values[(this.ordinal() + 1) % values.length];
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record HouseEntry(String houseId, String kingdomId, String displayName, class_2960 texture, boolean playerHouse) {
    }

    @Environment(value=EnvType.CLIENT)
    private record MemberEntry(String entryId, String displayName, MemberKind kind, String playerUuid, String marriedToName, String locationName, String parentsLine, int ageDays, String familyStageId, List<String> roleIconIds, boolean spouseInParty, long summonCooldownEndsAtMs, String kingdomId, String npcGender, int npcSkinVariant, int npcHairVariant, int npcClothesVariant, int[] skillPoints, int[] skillMaxPoints) {
    }

    @Environment(value=EnvType.CLIENT)
    private record ScrollbarMetrics(int x, int y, int width, int height, int thumbY, int thumbHeight) {
    }

    @Environment(value=EnvType.CLIENT)
    private static enum MemberKind {
        LORD,
        PLAYER,
        NPC_SPOUSE,
        CHILD,
        DYNASTY;

    }
}

