/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1299
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 */
package trep.got.entity;

import net.minecraft.class_1268;
import net.minecraft.class_1299;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import trep.got.block.GateLocator;
import trep.got.block.GateMechanismBlock;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.registry.GotCastles;
import trep.got.registry.GotItems;

public abstract class AbstractKingdomBlacksmithNpcEntity
extends AbstractKingdomNpcEntity {
    private static final int GATE_REPAIR_TICKS = 160;
    private static final double GATE_REPAIR_HORIZONTAL_REACH = 1.75;
    private static final int GATE_REPAIR_VERTICAL_REACH = 6;
    private class_2338 repairingGatePos;
    private int gateRepairTicks;

    protected AbstractKingdomBlacksmithNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    public KingdomNpcRole getNpcRole() {
        return KingdomNpcRole.BLACKSMITH;
    }

    @Override
    public class_1799 getIndicatorStack() {
        return new class_1799((class_1935)GotItems.BLACKSMITH_HAMMER);
    }

    @Override
    public boolean supportsDialogueInteraction() {
        return true;
    }

    @Override
    public void method_5773() {
        class_3218 serverWorld;
        block3: {
            block2: {
                class_1937 class_19372;
                super.method_5773();
                if (this.method_37908().method_8608() || !((class_19372 = this.method_37908()) instanceof class_3218)) break block2;
                serverWorld = (class_3218)class_19372;
                if (!this.isInDialogueScene()) break block3;
            }
            return;
        }
        this.tickGateRepair(serverWorld);
    }

    private void tickGateRepair(class_3218 world) {
        String castleId = this.got$getCastle();
        if (castleId.isBlank() || GotCastles.isCastleUnderAnyInvasion(world, castleId)) {
            this.clearRepairTarget();
            return;
        }
        if (this.repairingGatePos == null || !this.isBrokenCastleGate(world, castleId, this.repairingGatePos)) {
            this.repairingGatePos = this.findNearestBrokenCastleGate(world, castleId);
            this.gateRepairTicks = 0;
            if (this.repairingGatePos == null) {
                return;
            }
        }
        class_243 repairAnchor = this.repairAnchor(this.repairingGatePos);
        if (!this.isWithinRepairReach(this.repairingGatePos)) {
            this.method_5942().method_6337(repairAnchor.field_1352, repairAnchor.field_1351, repairAnchor.field_1350, 1.0);
            this.gateRepairTicks = 0;
            return;
        }
        this.method_5942().method_6340();
        if (this.field_6012 % 10 == 0) {
            this.method_23667(class_1268.field_5808, true);
        }
        ++this.gateRepairTicks;
        if (this.gateRepairTicks < 160) {
            return;
        }
        class_2680 state = world.method_8320(this.repairingGatePos);
        if (state.method_26204() instanceof GateMechanismBlock && ((Boolean)state.method_11654((class_2769)GateMechanismBlock.BROKEN)).booleanValue()) {
            GateMechanismBlock.repairBrokenGate(world, this.repairingGatePos, state);
        }
        this.clearRepairTarget();
    }

    private boolean isWithinRepairReach(class_2338 gatePos) {
        double dx = (double)gatePos.method_10263() + 0.5 - this.method_23317();
        double dz = (double)gatePos.method_10260() + 0.5 - this.method_23321();
        double horizontalDistanceSq = dx * dx + dz * dz;
        int verticalDistance = Math.abs(gatePos.method_10264() - this.method_31478());
        return horizontalDistanceSq <= 3.0625 && verticalDistance <= 6;
    }

    private class_243 repairAnchor(class_2338 gatePos) {
        return class_243.method_24955((class_2382)gatePos.method_10074());
    }

    private class_2338 findNearestBrokenCastleGate(class_3218 world, String castleId) {
        class_2338 nearest = null;
        double bestDistance = Double.MAX_VALUE;
        for (class_2338 gatePos : GateLocator.positions(world)) {
            double distance;
            if (!this.isBrokenCastleGate(world, castleId, gatePos) || !((distance = this.method_5707(class_243.method_24953((class_2382)gatePos))) < bestDistance)) continue;
            bestDistance = distance;
            nearest = gatePos.method_10062();
        }
        return nearest;
    }

    private boolean isBrokenCastleGate(class_3218 world, String castleId, class_2338 pos) {
        if (world == null || castleId == null || castleId.isBlank() || pos == null) {
            return false;
        }
        if (!world.method_8393(pos.method_10263() >> 4, pos.method_10260() >> 4)) {
            return false;
        }
        if (!GotCastles.isWithinCastleClaim(world, castleId, pos)) {
            return false;
        }
        class_2680 state = world.method_8320(pos);
        return state.method_26204() instanceof GateMechanismBlock && (Boolean)state.method_11654((class_2769)GateMechanismBlock.BROKEN) != false;
    }

    private void clearRepairTarget() {
        this.repairingGatePos = null;
        this.gateRepairTicks = 0;
    }

    @Override
    public abstract class_2960 getKingdomId();
}

