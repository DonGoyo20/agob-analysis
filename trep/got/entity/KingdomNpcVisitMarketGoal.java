/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1352
 *  net.minecraft.class_1352$class_4134
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_3218
 */
package trep.got.entity;

import java.util.EnumSet;
import net.minecraft.class_1352;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_3218;
import trep.got.castle.CastleInstance;
import trep.got.castle.CityStructureClaim;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcCastleGoalSupport;

final class KingdomNpcVisitMarketGoal
extends class_1352 {
    private final AbstractKingdomNpcEntity npc;
    private class_2338 target;
    private long nextAttemptAt = -1L;
    private int browseTicks;

    KingdomNpcVisitMarketGoal(AbstractKingdomNpcEntity npc) {
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
            this.nextAttemptAt = now + (long)this.npc.method_59922().method_43048(1200);
            return false;
        }
        if (now < this.nextAttemptAt || !this.npc.canStartNpcSocialInteraction(now)) {
            return false;
        }
        this.nextAttemptAt = now + 1200L + (long)this.npc.method_59922().method_43048(2600);
        if (this.npc.method_59922().method_43057() > 0.42f) {
            return false;
        }
        CastleInstance castle = KingdomNpcCastleGoalSupport.castleFor(this.npc, world);
        if (castle == null) {
            return false;
        }
        CityStructureClaim market = KingdomNpcCastleGoalSupport.market(castle, this.npc).orElse(null);
        if (market == null) {
            return false;
        }
        this.target = KingdomNpcCastleGoalSupport.findInteriorTarget(world, market);
        return this.target != null && this.npc.method_24515().method_10262((class_2382)this.target) > 16.0;
    }

    public boolean method_6266() {
        class_3218 world;
        class_1937 class_19372 = this.npc.method_37908();
        if (!(class_19372 instanceof class_3218) || KingdomNpcCastleGoalSupport.isNight(world = (class_3218)class_19372) || this.npc.isInDialogueScene() || this.npc.method_5968() != null) {
            return false;
        }
        return this.browseTicks > 0 || !this.npc.method_5942().method_6357();
    }

    public void method_6269() {
        this.browseTicks = 0;
        if (this.target != null) {
            this.npc.method_5942().method_6337((double)this.target.method_10263() + 0.5, (double)this.target.method_10264(), (double)this.target.method_10260() + 0.5, 0.95);
        }
    }

    public void method_6268() {
        if (this.target == null) {
            return;
        }
        if (this.browseTicks > 0) {
            --this.browseTicks;
            this.npc.method_5942().method_6340();
            return;
        }
        if (KingdomNpcCastleGoalSupport.isAt(this.target, this.npc.method_19538(), 2.0)) {
            this.browseTicks = 100 + this.npc.method_59922().method_43048(180);
            this.npc.method_5942().method_6340();
            return;
        }
        if (this.npc.method_5942().method_6357() && this.npc.field_6012 % 20 == 0) {
            this.npc.method_5942().method_6337((double)this.target.method_10263() + 0.5, (double)this.target.method_10264(), (double)this.target.method_10260() + 0.5, 0.95);
        }
    }

    public void method_6270() {
        this.target = null;
        this.browseTicks = 0;
    }
}

