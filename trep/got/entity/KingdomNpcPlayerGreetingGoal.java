/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1352
 *  net.minecraft.class_1352$class_4134
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3532
 */
package trep.got.entity;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1352;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3532;
import trep.got.entity.AbstractKingdomNpcEntity;

final class KingdomNpcPlayerGreetingGoal
extends class_1352 {
    private static final double GREETING_RANGE = 16.0;
    private static final int GREETING_TICKS = 15;
    private final AbstractKingdomNpcEntity npc;
    private class_3222 targetPlayer;
    private long nextAttemptAt = -1L;
    private long greetingUntil;

    KingdomNpcPlayerGreetingGoal(AbstractKingdomNpcEntity npc) {
        this.npc = npc;
        this.method_6265(EnumSet.of(class_1352.class_4134.field_18405, class_1352.class_4134.field_18406));
    }

    public boolean method_6264() {
        class_3218 world;
        block6: {
            block5: {
                class_1937 class_19372 = this.npc.method_37908();
                if (!(class_19372 instanceof class_3218)) break block5;
                world = (class_3218)class_19372;
                if (!this.npc.isInDialogueScene() && this.npc.method_5968() == null && !this.npc.isMarriagePartyCompanion()) break block6;
            }
            return false;
        }
        long now = world.method_8510();
        if (this.nextAttemptAt < 0L) {
            this.nextAttemptAt = now + 80L + (long)this.npc.method_59922().method_43048(160);
            return false;
        }
        if (now < this.nextAttemptAt || this.npc.method_59922().method_43057() > 0.35f) {
            return false;
        }
        this.nextAttemptAt = now + 120L + (long)this.npc.method_59922().method_43048(220);
        this.targetPlayer = this.pickTargetPlayer(world, now);
        return this.targetPlayer != null;
    }

    public boolean method_6266() {
        class_3218 world;
        block3: {
            block2: {
                class_1937 class_19372 = this.npc.method_37908();
                if (!(class_19372 instanceof class_3218)) break block2;
                world = (class_3218)class_19372;
                if (this.targetPlayer != null && this.targetPlayer.method_5805()) break block3;
            }
            return false;
        }
        return world.method_8510() < this.greetingUntil && this.npc.method_5858((class_1297)this.targetPlayer) <= 256.0;
    }

    public void method_6269() {
        class_3218 world;
        block3: {
            block2: {
                class_1937 class_19372 = this.npc.method_37908();
                if (!(class_19372 instanceof class_3218)) break block2;
                world = (class_3218)class_19372;
                if (this.targetPlayer != null) break block3;
            }
            return;
        }
        this.greetingUntil = world.method_8510() + 15L;
        this.npc.startPlayerGreeting(this.targetPlayer);
        this.facePlayer();
    }

    public void method_6268() {
        this.npc.method_5942().method_6340();
        this.facePlayer();
    }

    public void method_6270() {
        this.targetPlayer = null;
        this.greetingUntil = 0L;
    }

    private class_3222 pickTargetPlayer(class_3218 world, long now) {
        List candidates = world.method_18766(player -> player != null && player.method_5805() && this.npc.canGreetPlayer((class_3222)player, now) && this.npc.method_5858((class_1297)player) <= 256.0 && this.npc.method_6057((class_1297)player));
        if (candidates.isEmpty()) {
            return null;
        }
        candidates.sort(Comparator.comparingDouble(arg_0 -> ((AbstractKingdomNpcEntity)this.npc).method_5858(arg_0)));
        return (class_3222)candidates.get(this.npc.method_59922().method_43048(Math.min(3, candidates.size())));
    }

    private void facePlayer() {
        if (this.targetPlayer == null) {
            return;
        }
        this.npc.method_5988().method_6226((class_1297)this.targetPlayer, 30.0f, 30.0f);
        class_243 delta = this.targetPlayer.method_19538().method_1020(this.npc.method_19538());
        float targetYaw = (float)(Math.toDegrees(Math.atan2(delta.field_1350, delta.field_1352)) - 90.0);
        float yaw = this.npc.method_36454() + class_3532.method_15393((float)(targetYaw - this.npc.method_36454())) * 0.35f;
        this.npc.method_36456(yaw);
        this.npc.method_5847(yaw);
        this.npc.method_5636(yaw);
    }
}

