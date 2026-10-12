/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1352
 *  net.minecraft.class_1352$class_4134
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_3218
 */
package trep.got.entity;

import java.util.EnumSet;
import net.minecraft.class_1352;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import trep.got.castle.CastleInstance;
import trep.got.castle.CityStructureClaim;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcCastleGoalSupport;

final class KingdomNpcNightHomeGoal
extends class_1352 {
    private final AbstractKingdomNpcEntity npc;
    private CityStructureClaim house;
    private class_2338 doorPos;
    private class_2338 interiorPos;
    private boolean passedDoor;
    private long nextAttemptAt = -1L;

    KingdomNpcNightHomeGoal(AbstractKingdomNpcEntity npc) {
        this.npc = npc;
        this.method_6265(EnumSet.of(class_1352.class_4134.field_18405, class_1352.class_4134.field_18406));
    }

    public boolean method_6264() {
        class_3218 world;
        class_1937 class_19372 = this.npc.method_37908();
        if (!(class_19372 instanceof class_3218) || !KingdomNpcCastleGoalSupport.isNight(world = (class_3218)class_19372)) {
            return false;
        }
        long now = world.method_8510();
        if (this.nextAttemptAt < 0L) {
            this.nextAttemptAt = now + (long)this.npc.method_59922().method_43048(180);
            return false;
        }
        if (now < this.nextAttemptAt || this.npc.isInDialogueScene() || this.npc.method_5968() != null || this.npc.isMarriagePartyCompanion()) {
            return false;
        }
        this.nextAttemptAt = now + 200L + (long)this.npc.method_59922().method_43048(160);
        CastleInstance castle = KingdomNpcCastleGoalSupport.castleFor(this.npc, world);
        this.house = KingdomNpcCastleGoalSupport.ownedHouse(this.npc, castle).orElse(null);
        if (this.house == null) {
            return false;
        }
        KingdomNpcCastleGoalSupport.HomeDoorTarget doorTarget = KingdomNpcCastleGoalSupport.findHomeDoorTarget(world, this.house).orElse(null);
        if (doorTarget != null) {
            this.doorPos = doorTarget.doorPos();
            this.interiorPos = doorTarget.interiorPos();
        } else {
            this.doorPos = null;
            this.interiorPos = KingdomNpcCastleGoalSupport.findInteriorTarget(world, this.house);
        }
        this.passedDoor = this.doorPos == null || KingdomNpcCastleGoalSupport.contains(this.house.footprint(), this.npc.method_24515());
        return this.interiorPos != null;
    }

    public boolean method_6266() {
        class_3218 world;
        class_1937 class_19372 = this.npc.method_37908();
        if (!(class_19372 instanceof class_3218) || !KingdomNpcCastleGoalSupport.isNight(world = (class_3218)class_19372)) {
            return false;
        }
        return this.house != null && !this.npc.isInDialogueScene() && this.npc.method_5968() == null;
    }

    public void method_6269() {
        this.moveToCurrentTarget();
    }

    public void method_6268() {
        class_3218 world;
        block10: {
            block9: {
                class_1937 class_19372 = this.npc.method_37908();
                if (!(class_19372 instanceof class_3218)) break block9;
                world = (class_3218)class_19372;
                if (this.house != null && this.interiorPos != null) break block10;
            }
            return;
        }
        if (!this.passedDoor && this.doorPos != null) {
            if (KingdomNpcCastleGoalSupport.isAt(this.doorPos, this.npc.method_19538(), 2.2)) {
                KingdomNpcCastleGoalSupport.openDoor(world, this.npc, this.doorPos);
                this.passedDoor = true;
                this.moveToCurrentTarget();
            } else if (this.npc.method_5942().method_6357() && this.npc.field_6012 % 20 == 0) {
                this.moveToCurrentTarget();
            }
            return;
        }
        if (KingdomNpcCastleGoalSupport.contains(this.house.footprint(), this.npc.method_24515()) || KingdomNpcCastleGoalSupport.isAt(this.interiorPos, this.npc.method_19538(), 1.7)) {
            this.npc.method_5942().method_6340();
            return;
        }
        if (this.npc.method_5942().method_6357() && this.npc.field_6012 % 20 == 0) {
            this.moveToCurrentTarget();
        }
    }

    public void method_6270() {
        this.house = null;
        this.doorPos = null;
        this.interiorPos = null;
        this.passedDoor = false;
    }

    private void moveToCurrentTarget() {
        class_2338 target;
        class_2338 class_23382 = target = !this.passedDoor && this.doorPos != null ? this.doorPos : this.interiorPos;
        if (target != null) {
            this.npc.method_5942().method_6337((double)target.method_10263() + 0.5, (double)target.method_10264(), (double)target.method_10260() + 0.5, 1.05);
        }
    }
}

