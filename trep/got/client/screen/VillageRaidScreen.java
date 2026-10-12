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

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_8710;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.kingdom.ReputationTitle;
import trep.got.network.ClaimOutlinePayload;
import trep.got.network.RequestVillageRaidPayload;

@Environment(value=EnvType.CLIENT)
public final class VillageRaidScreen
extends class_437 {
    private static final class_2960 RAID = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_raid.png");
    private static final class_2960 RAID_HOVER = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_raid_hover.png");
    private static final class_2960 RAID_LOCKED = class_2960.method_60655((String)"agob", (String)"textures/ui/claim_raid_locked.png");
    private static final int BUTTON_SIZE = 72;
    private static final float HOVER_SCALE = 1.1f;
    private final ClaimOutlinePayload.Entry claim;
    private final class_437 parent;
    private final boolean unlocked;
    private final String lockReason;
    private final Runnable onConfirm;

    public VillageRaidScreen(ClaimOutlinePayload.Entry claim, class_437 parent, Runnable onConfirm) {
        super((class_2561)class_2561.method_43470((String)"Village Raid"));
        this.claim = claim;
        this.parent = parent;
        this.onConfirm = onConfirm;
        ReputationTitle title = CharacterSelectionClientState.getReputationTitle();
        String playerKingdom = CharacterSelectionClientState.getSelectedKingdomId();
        if (claim.cooldownSeconds() > 0) {
            this.unlocked = false;
            this.lockReason = "Village recovering";
        } else if (!title.isAtLeast(ReputationTitle.SOLDIER)) {
            this.unlocked = false;
            this.lockReason = "Need Soldier title";
        } else if (playerKingdom.isBlank() || playerKingdom.equals(claim.kingdomId())) {
            this.unlocked = false;
            this.lockReason = "Cannot raid own kingdom";
        } else {
            this.unlocked = true;
            this.lockReason = "";
        }
    }

    public static void open(ClaimOutlinePayload.Entry claim, class_437 parent) {
        VillageRaidScreen.open(claim, parent, null);
    }

    public static void open(ClaimOutlinePayload.Entry claim, class_437 parent, Runnable onConfirm) {
        class_310.method_1551().method_1507((class_437)new VillageRaidScreen(claim, parent, onConfirm));
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
        int x = (this.field_22789 - 72) / 2;
        int y = this.field_22790 / 2 - 36;
        if (!VillageRaidScreen.isWithin(mouseX, mouseY, x, y, 72, 72)) {
            return super.method_25402(mouseX, mouseY, button);
        }
        if (!this.unlocked) {
            return true;
        }
        ClientPlayNetworking.send((class_8710)new RequestVillageRaidPayload(this.claim.id()));
        if (this.onConfirm != null) {
            this.onConfirm.run();
        }
        this.method_25419();
        return true;
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.method_25420(context, mouseX, mouseY, delta);
        int titleY = this.field_22790 / 2 - 72;
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Village Raid"), this.field_22789 / 2, titleY, -727358);
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)this.claim.name()), this.field_22789 / 2, titleY + 14, -1);
        int x = (this.field_22789 - 72) / 2;
        int y = this.field_22790 / 2 - 36;
        boolean hovered = VillageRaidScreen.isWithin(mouseX, mouseY, x, y, 72, 72);
        this.drawOption(context, x, y, hovered);
        super.method_25394(context, mouseX, mouseY, delta);
    }

    private void drawOption(class_332 context, int x, int y, boolean hovered) {
        float scale = hovered ? 1.1f : 1.0f;
        int drawSize = Math.round(72.0f * scale);
        int drawX = x + (72 - drawSize) / 2;
        int drawY = y + (72 - drawSize) / 2;
        class_2960 texture = this.unlocked ? (hovered ? RAID_HOVER : RAID) : RAID_LOCKED;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)drawX, (float)drawY, 0.0f);
        context.method_51448().method_22905((float)drawSize / 48.0f, (float)drawSize / 48.0f, 1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, 48, 48, 48, 48);
        context.method_51448().method_22909();
        int textY = y + 72 + 8;
        context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)"Raid Village"), this.field_22789 / 2, textY, -1);
        if (!this.unlocked) {
            context.method_27534(this.field_22793, (class_2561)class_2561.method_43470((String)this.lockReason), this.field_22789 / 2, textY + 10, -38037);
        }
    }

    private static boolean isWithin(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= (double)x && mouseX < (double)(x + width) && mouseY >= (double)y && mouseY < (double)(y + height);
    }
}

