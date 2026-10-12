/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1531
 *  net.minecraft.class_1657
 *  net.minecraft.class_1665
 *  net.minecraft.class_1667
 *  net.minecraft.class_1743
 *  net.minecraft.class_1753
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2680
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3417
 *  net.minecraft.class_3532
 */
package trep.got.entity;

import java.util.Comparator;
import java.util.UUID;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1657;
import net.minecraft.class_1665;
import net.minecraft.class_1667;
import net.minecraft.class_1743;
import net.minecraft.class_1753;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3417;
import net.minecraft.class_3532;
import trep.got.block.GateFenceBlock;
import trep.got.entity.DynamicTroopEntity;
import trep.got.entity.PartyHorseEntity;
import trep.got.item.BowCombatHelper;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotCastles;
import trep.got.troop.CommanderActionType;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.DynamicTroopUnitType;
import trep.got.troop.GotTroops;
import trep.got.troop.PartyFormationType;

public final class PartyTroopEntity
extends DynamicTroopEntity {
    private static final String OWNER_UUID_KEY = "gotmod.party_owner_uuid";
    private static final String UNIT_ID_KEY = "gotmod.party_unit_id";
    private static final String COMMAND_TYPE_KEY = "gotmod.command_type";
    private static final String COMMAND_TARGET_KEY = "gotmod.command_target";
    private static final String COMMAND_ORIGIN_KEY = "gotmod.command_origin";
    private static final String FORMATION_TYPE_KEY = "gotmod.formation_type";
    private static final String TARGET_ENTITY_UUID_KEY = "gotmod.target_entity_uuid";
    private static final String TARGET_GATE_POS_KEY = "gotmod.target_gate_pos";
    private static final String COMMAND_FACING_YAW_KEY = "gotmod.command_facing_yaw";
    private static final double CHARGE_ATTACK_RANGE = 8.0;
    private static final double CHARGE_PATH_MAX_DRIFT = 4.0;
    private static final int GATE_BREAK_BASE_WORK = 24;
    private static final double GATE_BREAK_REACH = 1.35;
    private UUID ownerUuid;
    private int unitId = -1;
    private CommanderActionType commandType = CommanderActionType.RECALL;
    private class_2338 commandTarget;
    private class_2338 commandOrigin;
    private PartyFormationType formationType = PartyFormationType.LINE;
    private UUID targetEntityUuid;
    private class_2338 targetGatePos;
    private float commandFacingYaw;
    private int gateBreakWork;
    private class_1309 cachedInvasionTarget;
    private int invasionTargetCacheTick = -1;
    private boolean cachedAllyInLineOfFire;
    private int allyLineOfFireCacheTick = -1;
    private class_1309 allyLineOfFireTarget;

    public PartyTroopEntity(class_1299<? extends PartyTroopEntity> entityType, class_1937 world) {
        super((class_1299<? extends DynamicTroopEntity>)entityType, world);
    }

    public UUID getOwnerUuid() {
        return this.ownerUuid;
    }

    public void setOwnerUuid(UUID ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    public int getUnitId() {
        return this.unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public class_1269 method_5992(class_1657 player, class_1268 hand) {
        if (this.method_37908().method_8608()) {
            return class_1269.field_5812;
        }
        if (player instanceof class_3222) {
            class_3222 serverPlayer = (class_3222)player;
            if (this.ownerUuid != null && this.ownerUuid.equals(serverPlayer.method_5667())) {
                this.method_5942().method_6340();
                this.agob$playHumanoidAnimation(3);
                return class_1269.field_5812;
            }
        }
        return super.method_5992(player, hand);
    }

    public CommanderActionType getCommandType() {
        return this.commandType;
    }

    public void setCommandType(CommanderActionType commandType) {
        this.commandType = commandType == null ? CommanderActionType.MOVE : commandType;
    }

    public class_2338 getCommandTarget() {
        return this.commandTarget;
    }

    public void setCommandTarget(class_2338 commandTarget) {
        this.commandTarget = commandTarget == null ? null : commandTarget.method_10062();
    }

    public class_2338 getCommandOrigin() {
        return this.commandOrigin;
    }

    public void setCommandOrigin(class_2338 commandOrigin) {
        this.commandOrigin = commandOrigin == null ? null : commandOrigin.method_10062();
    }

    public PartyFormationType getFormationType() {
        return this.formationType;
    }

    public void setFormationType(PartyFormationType formationType) {
        this.formationType = formationType == null ? PartyFormationType.LINE : formationType;
    }

    public UUID getTargetEntityUuid() {
        return this.targetEntityUuid;
    }

    public void setTargetEntityUuid(UUID targetEntityUuid) {
        this.targetEntityUuid = targetEntityUuid;
    }

    public float getCommandFacingYaw() {
        return this.commandFacingYaw;
    }

    public void setCommandFacingYaw(float commandFacingYaw) {
        this.commandFacingYaw = commandFacingYaw;
    }

    public class_2338 getTargetGatePos() {
        return this.targetGatePos;
    }

    public void setTargetGatePos(class_2338 targetGatePos) {
        this.targetGatePos = targetGatePos == null ? null : targetGatePos.method_10062();
        this.gateBreakWork = 0;
    }

    @Override
    public void method_5773() {
        class_1309 hostile;
        class_1309 playerConflictTarget;
        class_3218 serverWorld;
        block25: {
            block24: {
                super.method_5773();
                class_1937 class_19372 = this.method_37908();
                if (!(class_19372 instanceof class_3218)) break block24;
                serverWorld = (class_3218)class_19372;
                if (this.field_6012 % 8 == 0 && !this.isInDialogueScene()) break block25;
            }
            return;
        }
        class_3222 owner = this.getOwner(serverWorld);
        if (owner == null || !owner.method_5805()) {
            return;
        }
        if (this.commandType == CommanderActionType.HALT) {
            this.method_5942().method_6340();
            class_1297 class_12972 = this.method_5854();
            if (class_12972 instanceof PartyHorseEntity) {
                PartyHorseEntity horse = (PartyHorseEntity)class_12972;
                horse.method_5942().method_6340();
            }
            return;
        }
        if (this.commandTarget == null && this.commandType != CommanderActionType.FOLLOW_ME) {
            this.method_5942().method_6340();
            return;
        }
        DynamicTroopDefinition definition = this.getTroopDefinition();
        if (definition == null) {
            return;
        }
        if (this.commandType == CommanderActionType.FOLLOW_ME) {
            class_2338 followSlot = GotTroops.calculateTargetFormationSlot(serverWorld, this.ownerUuid, this.unitId, owner.method_19538(), owner.method_36454(), this.formationType);
            this.moveToCommandSlot(class_243.method_24955((class_2382)followSlot), this.formationMoveSpeed(definition));
            return;
        }
        if (this.targetEntityUuid != null) {
            class_1297 explicitTarget = serverWorld.method_14190(this.targetEntityUuid);
            if (explicitTarget == null || !explicitTarget.method_5805()) {
                this.targetEntityUuid = null;
            } else {
                this.tickExplicitTarget(serverWorld, explicitTarget, definition);
                return;
            }
        }
        if (this.targetGatePos != null) {
            if (this.tickGateTarget(serverWorld, definition)) {
                return;
            }
            this.clearGateBreakOverlay(serverWorld);
            this.targetGatePos = null;
        }
        if ((playerConflictTarget = GotTroops.findPlayerConflictTarget(serverWorld, this.ownerUuid, (class_1309)this)) != null) {
            this.tickCombatTarget(serverWorld, playerConflictTarget, definition);
            return;
        }
        class_1309 invasionTarget = this.findInvasionTarget(serverWorld);
        if (invasionTarget != null) {
            this.tickCombatTarget(serverWorld, invasionTarget, definition);
            return;
        }
        class_243 targetCenter = class_243.method_24955((class_2382)this.commandTarget);
        double moveSpeed = this.formationMoveSpeed(definition);
        if (this.commandType == CommanderActionType.CHARGE && this.shouldHoldChargePath(targetCenter) && (hostile = this.findChargeTarget(serverWorld, owner, targetCenter)) != null) {
            double distance = this.method_5858((class_1297)hostile);
            if (definition.unitType() == DynamicTroopUnitType.ARCHER) {
                if (distance <= 196.0 && !this.hasAllyInLineOfFire(serverWorld, hostile) && this.field_6012 % BowCombatHelper.getAttackIntervalTicks(this.method_6118(class_1304.field_6173), 12) == 0) {
                    this.shootAt(hostile);
                }
            } else if (distance <= 9.0 && this.field_6012 % 8 == 0) {
                this.method_6121((class_1297)hostile);
            } else if (distance <= 64.0) {
                this.moveTowards(hostile.method_19538(), this.chargePursuitSpeed(definition));
            }
        }
        this.moveToCommandSlot(targetCenter, moveSpeed);
    }

    private void tickCombatTarget(class_3218 world, class_1309 target, DynamicTroopDefinition definition) {
        double distance = this.method_5858((class_1297)target);
        if (definition.unitType() == DynamicTroopUnitType.ARCHER) {
            if (distance <= 196.0 && !this.hasAllyInLineOfFire(world, target) && this.field_6012 % BowCombatHelper.getAttackIntervalTicks(this.method_6118(class_1304.field_6173), 12) == 0) {
                this.shootAt(target);
            } else {
                this.moveTowards(target.method_19538(), this.chargePursuitSpeed(definition));
            }
        } else if (distance <= 9.0 && this.field_6012 % 8 == 0) {
            this.method_6121((class_1297)target);
        } else {
            this.moveTowards(target.method_19538(), this.chargePursuitSpeed(definition));
        }
    }

    private void moveToCommandSlot(class_243 targetCenter, double moveSpeed) {
        if (this.method_5707(targetCenter) > 0.7224999999999999) {
            this.moveTowards(targetCenter, moveSpeed);
        } else {
            this.method_5942().method_6340();
            class_1297 class_12972 = this.method_5854();
            if (class_12972 instanceof PartyHorseEntity) {
                PartyHorseEntity horse = (PartyHorseEntity)class_12972;
                horse.method_5942().method_6340();
            }
        }
    }

    public boolean method_5643(class_1282 source, float amount) {
        class_1297 attacker = source.method_5529();
        class_1297 sourceEntity = source.method_5526();
        if (GotTroops.areAlliedPartyEntities(attacker, (class_1297)this) || GotTroops.areAlliedPartyEntities(sourceEntity, (class_1297)this)) {
            return false;
        }
        return super.method_5643(source, amount);
    }

    public void method_6078(class_1282 damageSource) {
        if (!this.method_37908().method_8608()) {
            class_1297 class_12972 = this.method_5854();
            if (class_12972 instanceof PartyHorseEntity) {
                PartyHorseEntity horse = (PartyHorseEntity)class_12972;
                horse.method_31472();
            }
            GotTroops.onPartyTroopKilled(this);
        }
        super.method_6078(damageSource);
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        if (this.ownerUuid != null) {
            nbt.method_25927(OWNER_UUID_KEY, this.ownerUuid);
        }
        nbt.method_10569(UNIT_ID_KEY, this.unitId);
        nbt.method_10582(COMMAND_TYPE_KEY, this.commandType.id());
        nbt.method_10582(FORMATION_TYPE_KEY, this.formationType.id());
        if (this.commandTarget != null) {
            nbt.method_10544(COMMAND_TARGET_KEY, this.commandTarget.method_10063());
        }
        if (this.commandOrigin != null) {
            nbt.method_10544(COMMAND_ORIGIN_KEY, this.commandOrigin.method_10063());
        }
        if (this.targetEntityUuid != null) {
            nbt.method_25927(TARGET_ENTITY_UUID_KEY, this.targetEntityUuid);
        }
        if (this.targetGatePos != null) {
            nbt.method_10544(TARGET_GATE_POS_KEY, this.targetGatePos.method_10063());
        }
        nbt.method_10548(COMMAND_FACING_YAW_KEY, this.commandFacingYaw);
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        if (nbt.method_25928(OWNER_UUID_KEY)) {
            this.ownerUuid = nbt.method_25926(OWNER_UUID_KEY);
        }
        if (nbt.method_10545(UNIT_ID_KEY)) {
            this.unitId = nbt.method_10550(UNIT_ID_KEY);
        }
        if (nbt.method_10545(COMMAND_TYPE_KEY)) {
            this.commandType = CommanderActionType.byId(nbt.method_10558(COMMAND_TYPE_KEY));
        }
        if (nbt.method_10545(FORMATION_TYPE_KEY)) {
            this.formationType = PartyFormationType.byId(nbt.method_10558(FORMATION_TYPE_KEY));
        }
        if (nbt.method_10545(COMMAND_TARGET_KEY)) {
            this.commandTarget = class_2338.method_10092((long)nbt.method_10537(COMMAND_TARGET_KEY));
        }
        if (nbt.method_10545(COMMAND_ORIGIN_KEY)) {
            this.commandOrigin = class_2338.method_10092((long)nbt.method_10537(COMMAND_ORIGIN_KEY));
        }
        if (nbt.method_25928(TARGET_ENTITY_UUID_KEY)) {
            this.targetEntityUuid = nbt.method_25926(TARGET_ENTITY_UUID_KEY);
        }
        if (nbt.method_10545(TARGET_GATE_POS_KEY)) {
            this.targetGatePos = class_2338.method_10092((long)nbt.method_10537(TARGET_GATE_POS_KEY));
        }
        if (nbt.method_10545(COMMAND_FACING_YAW_KEY)) {
            this.commandFacingYaw = nbt.method_10583(COMMAND_FACING_YAW_KEY);
        }
    }

    private class_3222 getOwner(class_3218 world) {
        return this.ownerUuid == null ? null : world.method_8503().method_3760().method_14602(this.ownerUuid);
    }

    private class_1309 findChargeTarget(class_3218 world, class_3222 owner, class_243 bannerTarget) {
        class_238 searchBox = this.method_5829().method_18804(bannerTarget.method_1020(this.method_19538())).method_1009(8.0, 4.0, 8.0);
        return world.method_8390(class_1309.class, searchBox, entity -> entity.method_5805() && entity != owner && entity != this && !GotTroops.areAlliedPartyEntities((class_1297)this, (class_1297)entity) && !(entity instanceof class_1531) && this.isWithinChargePath((class_1309)entity, bannerTarget)).stream().min(Comparator.comparingDouble(arg_0 -> ((PartyTroopEntity)this).method_5858(arg_0))).orElse(null);
    }

    private void tickExplicitTarget(class_3218 world, class_1297 explicitTarget, DynamicTroopDefinition definition) {
        class_2338 slotPos;
        this.commandTarget = slotPos = GotTroops.calculateTargetFormationSlot(world, this.ownerUuid, this.unitId, explicitTarget.method_19538(), this.commandFacingYaw, this.formationType);
        class_243 slotCenter = class_243.method_24955((class_2382)slotPos);
        double moveSpeed = this.formationMoveSpeed(definition);
        double targetDistanceSq = this.method_5858(explicitTarget);
        if (explicitTarget instanceof class_1309) {
            class_1309 livingTarget = (class_1309)explicitTarget;
            if (definition.unitType() == DynamicTroopUnitType.ARCHER) {
                if (targetDistanceSq <= 196.0 && !this.hasAllyInLineOfFire(world, livingTarget) && this.field_6012 % BowCombatHelper.getAttackIntervalTicks(this.method_6118(class_1304.field_6173), 12) == 0) {
                    this.shootAt(livingTarget);
                }
            } else if (targetDistanceSq <= 9.0 && this.field_6012 % 8 == 0) {
                this.method_6121((class_1297)livingTarget);
            }
        }
        if (this.method_5707(slotCenter) > 0.7224999999999999) {
            this.moveTowards(slotCenter, moveSpeed);
        } else {
            this.method_5942().method_6340();
            class_1297 class_12972 = this.method_5854();
            if (class_12972 instanceof PartyHorseEntity) {
                PartyHorseEntity horse = (PartyHorseEntity)class_12972;
                horse.method_5942().method_6340();
            }
        }
    }

    private boolean tickGateTarget(class_3218 world, DynamicTroopDefinition definition) {
        class_2680 gateState = world.method_8320(this.targetGatePos);
        if (!gateState.method_27852(GotBlocks.GATE_FENCE)) {
            this.clearGateBreakOverlay(world);
            this.gateBreakWork = 0;
            return false;
        }
        class_243 gateCenter = class_243.method_24953((class_2382)this.targetGatePos);
        class_243 breachStandPos = this.gateBreachStandPos();
        this.commandTarget = class_2338.method_49638((class_2374)breachStandPos);
        double breachDistanceSq = this.method_19538().method_1025(breachStandPos);
        if (breachDistanceSq > 1.8225000000000002) {
            this.moveTowards(breachStandPos, this.chargePursuitSpeed(definition));
            return true;
        }
        this.method_5942().method_6340();
        class_1297 class_12972 = this.method_5854();
        if (class_12972 instanceof PartyHorseEntity) {
            PartyHorseEntity horse = (PartyHorseEntity)class_12972;
            horse.method_5942().method_6340();
        }
        this.method_6104(class_1268.field_5808);
        this.gateBreakWork += this.gateBreakWorkPerTick(gateState);
        world.method_8517(this.method_5628(), this.targetGatePos, PartyTroopEntity.gateBreakStage(this.gateBreakWork, 24));
        if (this.gateBreakWork < 24) {
            return true;
        }
        GateFenceBlock.breakGateColumn(world, this.targetGatePos);
        this.clearGateBreakOverlay(world);
        this.gateBreakWork = 0;
        return true;
    }

    private class_243 gateBreachStandPos() {
        class_243 gateCenter = class_243.method_24955((class_2382)this.targetGatePos);
        double yawRadians = Math.toRadians(this.commandFacingYaw);
        class_243 forward = new class_243(-Math.sin(yawRadians), 0.0, Math.cos(yawRadians));
        class_243 right = new class_243(forward.field_1350, 0.0, -forward.field_1352);
        int laneIndex = Math.floorMod(this.unitId, 5) - 2;
        double lateralOffset = (double)laneIndex * 0.9;
        double depthOffset = 1.05 + (double)Math.floorDiv(Math.max(this.unitId, 0), 5) * 0.35;
        return gateCenter.method_1020(forward.method_1021(depthOffset)).method_1019(right.method_1021(lateralOffset));
    }

    private class_1309 findInvasionTarget(class_3218 world) {
        if (this.invasionTargetCacheTick >= 0 && this.field_6012 - this.invasionTargetCacheTick < 16 && (this.cachedInvasionTarget == null || this.cachedInvasionTarget.method_5805())) {
            return this.cachedInvasionTarget;
        }
        this.invasionTargetCacheTick = this.field_6012;
        this.cachedInvasionTarget = GotCastles.findBattleTargetForTroop(world, this);
        return this.cachedInvasionTarget;
    }

    private int gateBreakWorkPerTick(class_2680 state) {
        float speed;
        class_1799 stack = this.method_6047();
        float f = speed = stack.method_7960() ? 1.0f : Math.max(1.0f, stack.method_7924(state));
        if (stack.method_7909() instanceof class_1743) {
            speed *= 1.5f;
        }
        return Math.max(1, class_3532.method_15375((float)speed));
    }

    private static int gateBreakStage(int accumulatedWork, int requiredWork) {
        if (requiredWork <= 0) {
            return 9;
        }
        return class_3532.method_15340((int)(accumulatedWork * 10 / requiredWork), (int)0, (int)9);
    }

    private void clearGateBreakOverlay(class_3218 world) {
        if (world != null && this.targetGatePos != null) {
            world.method_8517(this.method_5628(), this.targetGatePos, -1);
        }
    }

    private void moveTowards(class_243 pos, double speed) {
        class_1297 class_12972 = this.method_5854();
        if (class_12972 instanceof PartyHorseEntity) {
            PartyHorseEntity horse = (PartyHorseEntity)class_12972;
            horse.method_5942().method_6337(pos.field_1352, pos.field_1351, pos.field_1350, speed);
            return;
        }
        this.method_5942().method_6337(pos.field_1352, pos.field_1351, pos.field_1350, speed);
    }

    private void shootAt(class_1309 target) {
        class_1799 weapon = this.method_6118(class_1304.field_6173);
        if (!(weapon.method_7909() instanceof class_1753)) {
            return;
        }
        class_1667 arrow = new class_1667(this.method_37908(), (class_1309)this, new class_1799((class_1935)class_1802.field_8107), weapon);
        BowCombatHelper.applyProjectileModifiers(weapon, (class_1665)arrow);
        double dx = target.method_23317() - this.method_23317();
        double dz = target.method_23321() - this.method_23321();
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
        double dy = target.method_23323(0.3333333333333333) - arrow.method_23318() + horizontalDistance * 0.2;
        arrow.method_7485(dx, dy, dz, 1.6f, 12.0f);
        this.method_37908().method_8649((class_1297)arrow);
        this.method_5783(class_3417.field_14633, 1.0f, 0.8f + this.method_59922().method_43057() * 0.4f);
    }

    private boolean shouldHoldChargePath(class_243 bannerTarget) {
        if (this.method_6032() <= this.method_6063() * 0.25f) {
            return false;
        }
        if (this.commandOrigin == null) {
            return true;
        }
        return PartyTroopEntity.distanceToPath(this.method_19538(), class_243.method_24955((class_2382)this.commandOrigin), bannerTarget) <= 4.0;
    }

    private boolean isWithinChargePath(class_1309 entity, class_243 bannerTarget) {
        if (this.method_5858((class_1297)entity) > 64.0) {
            return false;
        }
        class_243 origin = this.commandOrigin == null ? this.method_19538() : class_243.method_24955((class_2382)this.commandOrigin);
        return PartyTroopEntity.distanceToPath(entity.method_19538(), origin, bannerTarget) <= 2.5;
    }

    private double formationMoveSpeed(DynamicTroopDefinition definition) {
        return switch (definition.unitType()) {
            default -> throw new MatchException(null, null);
            case DynamicTroopUnitType.MELEE -> {
                if (this.formationType == PartyFormationType.LOOSE) {
                    yield 1.05;
                }
                yield 1.0;
            }
            case DynamicTroopUnitType.ARCHER -> {
                if (this.formationType == PartyFormationType.LOOSE) {
                    yield 1.05;
                }
                yield 1.0;
            }
            case DynamicTroopUnitType.CAVALRY -> this.formationType == PartyFormationType.LOOSE ? 1.4 : 1.25;
        };
    }

    private double chargePursuitSpeed(DynamicTroopDefinition definition) {
        return switch (definition.unitType()) {
            default -> throw new MatchException(null, null);
            case DynamicTroopUnitType.MELEE -> 1.1;
            case DynamicTroopUnitType.ARCHER -> 1.1;
            case DynamicTroopUnitType.CAVALRY -> 1.45;
        };
    }

    private boolean hasAllyInLineOfFire(class_3218 world, class_1309 target) {
        if (this.allyLineOfFireCacheTick >= 0 && this.field_6012 - this.allyLineOfFireCacheTick < 16 && target == this.allyLineOfFireTarget) {
            return this.cachedAllyInLineOfFire;
        }
        this.allyLineOfFireCacheTick = this.field_6012;
        this.allyLineOfFireTarget = target;
        class_243 origin = this.method_33571();
        class_243 destination = target.method_19538().method_1031(0.0, (double)target.method_17682() * 0.5, 0.0);
        double targetDistanceSq = origin.method_1025(destination);
        class_238 searchBox = new class_238(origin, destination).method_1014(1.25);
        this.cachedAllyInLineOfFire = world.method_8390(class_1309.class, searchBox, entity -> entity.method_5805() && entity != this && !(entity instanceof PartyHorseEntity) && this.isFriendlyEntity((class_1309)entity)).stream().anyMatch(ally -> {
            class_243 allyCenter = ally.method_19538().method_1031(0.0, (double)ally.method_17682() * 0.5, 0.0);
            return origin.method_1025(allyCenter) < targetDistanceSq && PartyTroopEntity.distanceToPath(allyCenter, origin, destination) <= 0.9;
        });
        return this.cachedAllyInLineOfFire;
    }

    private boolean isFriendlyEntity(class_1309 entity) {
        return GotTroops.areAlliedPartyEntities((class_1297)this, (class_1297)entity);
    }

    private static double distanceToPath(class_243 point, class_243 start, class_243 end) {
        class_243 segment = end.method_1020(start);
        double lengthSquared = segment.method_1027();
        if (lengthSquared < 1.0E-6) {
            return point.method_1022(start);
        }
        double t = Math.max(0.0, Math.min(1.0, point.method_1020(start).method_1026(segment) / lengthSquared));
        class_243 closest = start.method_1019(segment.method_1021(t));
        return point.method_1022(closest);
    }
}

