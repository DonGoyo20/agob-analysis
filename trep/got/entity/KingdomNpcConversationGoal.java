/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1352
 *  net.minecraft.class_1352$class_4134
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_3218
 *  net.minecraft.class_3532
 */
package trep.got.entity;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1352;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_3532;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcCastleGoalSupport;

final class KingdomNpcConversationGoal
extends class_1352 {
    private static final double SEARCH_RANGE = 18.0;
    private static final double TALK_RANGE = 2.35;
    private static final int RESERVATION_TICKS = 220;
    private static final int MIN_TALK_TICKS = 90;
    private static final int TALK_TICK_VARIANCE = 90;
    private static final int ANIMATION_VARIANTS = 4;
    private final AbstractKingdomNpcEntity npc;
    private AbstractKingdomNpcEntity partner;
    private long nextAttemptAt = -1L;
    private long talkUntil;
    private boolean talking;

    KingdomNpcConversationGoal(AbstractKingdomNpcEntity npc) {
        this.npc = npc;
        this.method_6265(EnumSet.of(class_1352.class_4134.field_18405, class_1352.class_4134.field_18406));
    }

    public boolean method_6264() {
        class_3218 world;
        class_1937 class_19372 = this.npc.method_37908();
        if (!(class_19372 instanceof class_3218) || KingdomNpcCastleGoalSupport.isNight(world = (class_3218)class_19372)) {
            return false;
        }
        long now = world.method_8510();
        if (this.nextAttemptAt < 0L) {
            this.nextAttemptAt = now + (long)this.npc.method_59922().method_43048(900);
            return false;
        }
        if (now < this.nextAttemptAt || !this.npc.canStartNpcSocialInteraction(now)) {
            return false;
        }
        this.nextAttemptAt = now + 700L + (long)this.npc.method_59922().method_43048(1500);
        if (this.npc.method_59922().method_43057() > 0.34f) {
            return false;
        }
        this.partner = this.pickPartner(world, now);
        if (this.partner == null) {
            return false;
        }
        long reservedUntil = now + 220L;
        if (!this.npc.reserveNpcSocialInteraction(this.partner, reservedUntil)) {
            this.partner = null;
            return false;
        }
        if (!this.partner.reserveNpcSocialInteraction(this.npc, reservedUntil)) {
            this.npc.clearNpcSocialInteraction(this.partner);
            this.partner = null;
            return false;
        }
        return true;
    }

    public boolean method_6266() {
        class_3218 world;
        block6: {
            block5: {
                class_1937 class_19372 = this.npc.method_37908();
                if (!(class_19372 instanceof class_3218)) break block5;
                world = (class_3218)class_19372;
                if (this.partner != null && this.partner.method_5805()) break block6;
            }
            return false;
        }
        if (KingdomNpcCastleGoalSupport.isNight(world) || this.npc.isInDialogueScene() || this.partner.isInDialogueScene()) {
            return false;
        }
        if (this.talking) {
            return world.method_8510() < this.talkUntil;
        }
        return this.npc.method_5858((class_1297)this.partner) <= 324.0 && !this.npc.method_5942().method_6357();
    }

    public void method_6269() {
        this.talking = false;
        this.talkUntil = 0L;
        if (this.partner != null) {
            this.npc.method_5942().method_6335((class_1297)this.partner, 1.0);
        }
    }

    public void method_6268() {
        class_3218 world;
        block7: {
            block6: {
                class_1937 class_19372 = this.npc.method_37908();
                if (!(class_19372 instanceof class_3218)) break block6;
                world = (class_3218)class_19372;
                if (this.partner != null) break block7;
            }
            return;
        }
        this.lookAtEachOther();
        if (!this.talking && this.npc.method_5858((class_1297)this.partner) <= 5.522500000000001) {
            this.startTalking(world);
            return;
        }
        if (!this.talking && this.npc.field_6012 % 20 == 0) {
            this.npc.method_5942().method_6335((class_1297)this.partner, 1.0);
        }
        if (this.talking) {
            this.npc.method_5942().method_6340();
            this.partner.method_5942().method_6340();
        }
    }

    public void method_6270() {
        if (this.partner != null) {
            this.npc.clearNpcSocialInteraction(this.partner);
            this.partner.clearNpcSocialInteraction(this.npc);
        }
        this.partner = null;
        this.talking = false;
        this.talkUntil = 0L;
    }

    private AbstractKingdomNpcEntity pickPartner(class_3218 world, long now) {
        class_238 box = class_238.method_30048((class_243)this.npc.method_19538(), (double)36.0, (double)6.0, (double)36.0);
        List candidates = world.method_8390(AbstractKingdomNpcEntity.class, box, candidate -> candidate != this.npc && candidate.canStartNpcSocialInteraction(now) && this.npc.got$getCastle().equals(candidate.got$getCastle()) && candidate.method_5968() == null && !candidate.isMarriagePartyCompanion() && this.npc.method_6057((class_1297)candidate));
        if (candidates.isEmpty()) {
            return null;
        }
        candidates.sort(Comparator.comparingDouble(arg_0 -> ((AbstractKingdomNpcEntity)this.npc).method_5858(arg_0)));
        return (AbstractKingdomNpcEntity)candidates.get(this.npc.method_59922().method_43048(Math.min(4, candidates.size())));
    }

    private void startTalking(class_3218 world) {
        this.talking = true;
        this.talkUntil = world.method_8510() + 90L + (long)this.npc.method_59922().method_43048(90);
        int baseVariant = Math.floorMod(this.npc.method_5667().hashCode() + this.partner.method_5667().hashCode() + this.npc.field_6012, 4);
        this.npc.startNpcTalking(this.partner, this.talkUntil, baseVariant);
        this.partner.startNpcTalking(this.npc, this.talkUntil, (baseVariant + 1 + this.partner.method_59922().method_43048(3)) % 4);
        this.lookAtEachOther();
    }

    private void lookAtEachOther() {
        if (this.partner == null) {
            return;
        }
        this.npc.method_5988().method_6226((class_1297)this.partner, 30.0f, 30.0f);
        this.partner.method_5988().method_6226((class_1297)this.npc, 30.0f, 30.0f);
        KingdomNpcConversationGoal.smoothFace(this.npc, this.partner.method_19538());
        KingdomNpcConversationGoal.smoothFace(this.partner, this.npc.method_19538());
    }

    private static void smoothFace(AbstractKingdomNpcEntity entity, class_243 target) {
        class_243 delta = target.method_1020(entity.method_19538());
        float targetYaw = (float)(Math.toDegrees(Math.atan2(delta.field_1350, delta.field_1352)) - 90.0);
        float yaw = entity.method_36454() + class_3532.method_15393((float)(targetYaw - entity.method_36454())) * 0.22f;
        entity.method_36456(yaw);
        entity.method_5847(yaw);
        entity.method_5636(yaw);
    }
}

