/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_8710
 */
package trep.got.client.screen;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_8710;
import trep.got.castle.CastleInvasionMode;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.quest.QuestJournalClientState;
import trep.got.kingdom.ReputationTitle;
import trep.got.network.ClaimOutlinePayload;
import trep.got.network.RequestCastleInvasionPayload;

@Environment(value=EnvType.CLIENT)
public final class CastleInvasionScreen
extends class_437 {
    private static final class_2960 CRUSADE = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_crusade.png");
    private static final class_2960 CRUSADE_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_crusade_hover.png");
    private static final class_2960 CRUSADE_LOCKED = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_crusade_locked.png");
    private static final class_2960 RAID = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_raid.png");
    private static final class_2960 RAID_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_raid_hover.png");
    private static final class_2960 RAID_LOCKED = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_raid_locked.png");
    private static final class_2960 CONQUER = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_conquer.png");
    private static final class_2960 CONQUER_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_conquer_hover.png");
    private static final class_2960 CONQUER_LOCKED = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_conquer_locked.png");
    private static final int BUTTON_SIZE = 72;
    private static final int BUTTON_GAP = 42;
    private static final float HOVER_SCALE = 1.1f;
    private final ClaimOutlinePayload.Entry claim;
    private final class_437 parent;
    private final List<Option> options;
    private final Runnable onConfirm;

    public CastleInvasionScreen(ClaimOutlinePayload.Entry claim, class_437 parent) {
        this(claim, parent, null);
    }

    public CastleInvasionScreen(ClaimOutlinePayload.Entry claim, class_437 parent, Runnable onConfirm) {
        super((class_2561)class_2561.method_43470((String)"Invasion"));
        this.claim = claim;
        this.parent = parent;
        this.onConfirm = onConfirm;
        CastleInvasionMode finalOwnershipMode = this.resolveFinalOwnershipMode();
        this.options = List.of(this.buildOption(CastleInvasionMode.CRUSADE), this.buildOption(CastleInvasionMode.RAID), this.buildOption(finalOwnershipMode));
    }

    public static void open(ClaimOutlinePayload.Entry claim, class_437 parent) {
        CastleInvasionScreen.open(claim, parent, null);
    }

    public static void open(ClaimOutlinePayload.Entry claim, class_437 parent, Runnable onConfirm) {
        class_310.method_1551().method_1507((class_437)new CastleInvasionScreen(claim, parent, onConfirm));
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

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return super.method_25402(mouseX, mouseY, button);
        }
        for (PositionedOption option : this.positionedOptions()) {
            if (!CastleInvasionScreen.isWithin(mouseX, mouseY, option.x(), option.y(), option.size(), option.size())) continue;
            if (!option.option().unlocked()) {
                return true;
            }
            ClientPlayNetworking.send((class_8710)new RequestCastleInvasionPayload(this.claim.id(), option.option().mode().getId()));
            if (this.onConfirm != null) {
                this.onConfirm.run();
            }
            this.method_25419();
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.method_25420(context, mouseX, mouseY, delta);
        int titleY = this.field_22790 / 2 - 72;
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Invasion Mode"), this.field_22789 / 2, titleY, -727358);
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)this.claim.name()), this.field_22789 / 2, titleY + 14, -1);
        for (PositionedOption option : this.positionedOptions()) {
            boolean hovered = CastleInvasionScreen.isWithin(mouseX, mouseY, option.x(), option.y(), option.size(), option.size());
            this.drawOption(context, option, hovered);
        }
        super.method_25394(context, mouseX, mouseY, delta);
    }

    private void drawOption(class_332 context, PositionedOption option, boolean hovered) {
        Option data = option.option();
        boolean enlarge = hovered;
        float scale = enlarge ? 1.1f : 1.0f;
        int drawSize = Math.round((float)option.size() * scale);
        int drawX = option.centerX() - drawSize / 2;
        int drawY = option.centerY() - drawSize / 2;
        class_2960 texture = data.unlocked() ? (hovered ? data.hoverTexture() : data.texture()) : data.lockedTexture();
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)drawX, (float)drawY, 0.0f);
        context.method_51448().method_22905((float)drawSize / 48.0f, (float)drawSize / 48.0f, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, 48, 48, 48, 48);
        context.method_51448().method_22909();
        int textY = option.y() + option.size() + 8;
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)data.label()), option.centerX(), textY, -1);
        if (!data.unlocked()) {
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)data.lockReason()), option.centerX(), textY + 10, -38037);
        }
    }

    private List<PositionedOption> positionedOptions() {
        int totalWidth = 72 * this.options.size() + 42 * (this.options.size() - 1);
        int startX = (this.field_22789 - totalWidth) / 2;
        int centerY = this.field_22790 / 2;
        return List.of(new PositionedOption(this.options.get(0), startX, centerY, 72), new PositionedOption(this.options.get(1), startX + 72 + 42, centerY, 72), new PositionedOption(this.options.get(2), startX + 228, centerY, 72));
    }

    private Option buildOption(CastleInvasionMode mode) {
        String playerReligion = CharacterSelectionClientState.getSelectedReligionId();
        String playerKingdom = CharacterSelectionClientState.getSelectedKingdomId();
        ReputationTitle title = CharacterSelectionClientState.getReputationTitle();
        String activePledgeObjective = QuestJournalClientState.getActivePledgeObjectiveActionId();
        return switch (mode) {
            default -> throw new MatchException(null, null);
            case CastleInvasionMode.CRUSADE -> {
                if (this.claim.cooldownSeconds() > 0) {
                    yield new Option(mode, "Crusade", false, "Attacked recently", CRUSADE, CRUSADE_HOVER, CRUSADE_LOCKED);
                }
                boolean faithMismatch = !playerReligion.isBlank() && !this.claim.religionId().isBlank() && !playerReligion.equals(this.claim.religionId());
                boolean unlocked = faithMismatch && (title.canPerformInvasion(mode) || mode.getId().equals(activePledgeObjective));
                String reason = !faithMismatch ? "Need different faith" : "Title cannot Crusade";
                yield new Option(mode, "Crusade", unlocked, reason, CRUSADE, CRUSADE_HOVER, CRUSADE_LOCKED);
            }
            case CastleInvasionMode.RAID -> {
                if (this.claim.cooldownSeconds() > 0) {
                    yield new Option(mode, "Raid", false, "Attacked recently", RAID, RAID_HOVER, RAID_LOCKED);
                }
                boolean unlocked = title.canPerformInvasion(mode) || mode.getId().equals(activePledgeObjective);
                yield new Option(mode, "Raid", unlocked, "Title cannot Raid", RAID, RAID_HOVER, RAID_LOCKED);
            }
            case CastleInvasionMode.CHALLENGE -> {
                if (this.claim.cooldownSeconds() > 0) {
                    yield new Option(mode, "Challenge", false, "Attacked recently", CONQUER, CONQUER_HOVER, CONQUER_LOCKED);
                }
                if (this.claim.kingdomId().isBlank()) {
                    yield new Option(mode, "Challenge", false, "Need captured castle", CONQUER, CONQUER_HOVER, CONQUER_LOCKED);
                }
                boolean sameKingdom = !playerKingdom.isBlank() && !this.claim.kingdomId().isBlank() && playerKingdom.equals(this.claim.kingdomId());
                boolean unlocked = sameKingdom && (title.canPerformInvasion(mode) || CastleInvasionMode.CONQUER.getId().equals(activePledgeObjective));
                String reason = !sameKingdom ? "Need own kingdom" : "Title cannot Conquer";
                yield new Option(mode, "Challenge", unlocked, reason, CONQUER, CONQUER_HOVER, CONQUER_LOCKED);
            }
            case CastleInvasionMode.CONQUER -> {
                boolean unlocked;
                if (this.claim.cooldownSeconds() > 0) {
                    yield new Option(mode, "Conquer", false, "Attacked recently", CONQUER, CONQUER_HOVER, CONQUER_LOCKED);
                }
                boolean uncaptured = this.claim.kingdomId().isBlank();
                boolean differentKingdom = uncaptured || !playerKingdom.isBlank() && !this.claim.kingdomId().isBlank() && !playerKingdom.equals(this.claim.kingdomId());
                boolean v1 = unlocked = differentKingdom && (title.canPerformInvasion(mode) || mode.getId().equals(activePledgeObjective));
                String reason = uncaptured ? "Title cannot Conquer" : (!differentKingdom ? "Need foreign kingdom" : "Title cannot Conquer");
                yield new Option(mode, "Conquer", unlocked, reason, CONQUER, CONQUER_HOVER, CONQUER_LOCKED);
            }
        };
    }

    private CastleInvasionMode resolveFinalOwnershipMode() {
        String playerKingdom = CharacterSelectionClientState.getSelectedKingdomId();
        boolean sameKingdom = !this.claim.kingdomId().isBlank() && !playerKingdom.isBlank() && playerKingdom.equals(this.claim.kingdomId());
        return sameKingdom ? CastleInvasionMode.CHALLENGE : CastleInvasionMode.CONQUER;
    }

    private static boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }

    @Environment(value=EnvType.CLIENT)
    private record Option(CastleInvasionMode mode, String label, boolean unlocked, String lockReason, class_2960 texture, class_2960 hoverTexture, class_2960 lockedTexture) {
    }

    @Environment(value=EnvType.CLIENT)
    private record PositionedOption(Option option, int x, int centerY, int size) {
        int y() {
            return this.centerY - this.size / 2;
        }

        int centerX() {
            return this.x + this.size / 2;
        }
    }
}

