/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1314
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3532
 */
package trep.got.entity;

import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1314;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3532;
import trep.got.character.PlayerCharacterHolder;
import trep.got.entity.AbstractKingdomHumanoidEntity;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationTitle;

public abstract class AbstractDialogueHumanoidEntity
extends AbstractKingdomHumanoidEntity {
    private static final double DIALOGUE_HOLD_RANGE = 7.0;
    private UUID dialoguePlayerUuid;

    protected AbstractDialogueHumanoidEntity(class_1299<? extends class_1314> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    public void method_5773() {
        super.method_5773();
        if (this.method_37908().method_8608()) {
            return;
        }
        this.tickDialogueState();
    }

    public boolean isInDialogueScene() {
        return this.dialoguePlayerUuid != null;
    }

    public boolean supportsDialogueInteraction() {
        return false;
    }

    public boolean beginDialogue(class_3222 player) {
        if (!this.supportsDialogueInteraction()) {
            return false;
        }
        if (player == null || !player.method_5805() || this.method_5858((class_1297)player) > 49.0) {
            return false;
        }
        return this.beginDialogueInternal(player);
    }

    public boolean forceBeginDialogue(class_3222 player) {
        if (!this.supportsDialogueInteraction()) {
            return false;
        }
        if (player == null || !player.method_5805() || player.method_37908() != this.method_37908()) {
            return false;
        }
        return this.beginDialogueInternal(player);
    }

    private boolean beginDialogueInternal(class_3222 player) {
        this.dialoguePlayerUuid = player.method_5667();
        this.method_5942().method_6340();
        this.agob$playHumanoidAnimation(this.resolvePlayerInteractionAnimation(player));
        return true;
    }

    protected int resolvePlayerInteractionAnimation(class_3222 player) {
        if (this.shouldBowToPlayer(player)) {
            return 3;
        }
        return this.method_59922().method_43056() ? 1 : 2;
    }

    private boolean shouldBowToPlayer(class_3222 player) {
        PlayerCharacterHolder characterHolder;
        block5: {
            block4: {
                if (player == null || this.getKingdomId() == null || !(player instanceof PlayerCharacterHolder)) break block4;
                characterHolder = (PlayerCharacterHolder)player;
                if (player instanceof PlayerReputationHolder) break block5;
            }
            return false;
        }
        PlayerReputationHolder reputationHolder = (PlayerReputationHolder)player;
        ReputationTitle title = reputationHolder.got$getReputationProfile().getTitle();
        if (title == null || title.getTier() < ReputationTitle.LORD.getTier()) {
            return false;
        }
        String playerKingdomId = characterHolder.got$getCharacterProfile().getSelectedKingdomId();
        return !playerKingdomId.isBlank() && playerKingdomId.equals(this.getKingdomId().toString());
    }

    public void endDialogue(UUID playerUuid) {
        if (playerUuid == null || !playerUuid.equals(this.dialoguePlayerUuid)) {
            return;
        }
        this.dialoguePlayerUuid = null;
    }

    public class_1799 getIndicatorStack() {
        return class_1799.field_8037;
    }

    private boolean tickDialogueState() {
        class_1937 class_19372;
        if (this.dialoguePlayerUuid == null || !((class_19372 = this.method_37908()) instanceof class_3218)) {
            return false;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        class_3222 player = serverWorld.method_8503().method_3760().method_14602(this.dialoguePlayerUuid);
        if (player == null || !player.method_5805() || player.method_37908() != this.method_37908() || this.method_5858((class_1297)player) > 49.0) {
            this.dialoguePlayerUuid = null;
            return false;
        }
        this.method_5942().method_6340();
        this.method_18800(0.0, 0.0, 0.0);
        class_243 from = this.method_19538().method_1031(0.0, Math.min(2.0, (double)this.method_17682()), 0.0);
        class_243 to = player.method_33571();
        double dx = to.field_1352 - from.field_1352;
        double dz = to.field_1350 - from.field_1350;
        float targetYaw = (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        float yaw = this.method_36454() + class_3532.method_15393((float)(targetYaw - this.method_36454())) * 0.35f;
        this.method_36456(yaw);
        this.method_5847(yaw);
        this.method_5636(yaw);
        return true;
    }
}

