/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_1299
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1314
 *  net.minecraft.class_1352
 *  net.minecraft.class_1366
 *  net.minecraft.class_1399
 *  net.minecraft.class_1657
 *  net.minecraft.class_1665
 *  net.minecraft.class_1667
 *  net.minecraft.class_1753
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3417
 */
package trep.got.entity;

import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1314;
import net.minecraft.class_1352;
import net.minecraft.class_1366;
import net.minecraft.class_1399;
import net.minecraft.class_1657;
import net.minecraft.class_1665;
import net.minecraft.class_1667;
import net.minecraft.class_1753;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3417;
import trep.got.character.PlayerCharacterHolder;
import trep.got.entity.DynamicTroopEntity;
import trep.got.entity.PartyHorseEntity;
import trep.got.item.BowCombatHelper;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.GotTroops;

public final class HireableUnitEntity
extends DynamicTroopEntity {
    private static final String SPAWN_DAY_KEY = "gotmod.hireable_spawn_day";
    private long spawnDay = -1L;

    public HireableUnitEntity(class_1299<? extends HireableUnitEntity> entityType, class_1937 world) {
        super((class_1299<? extends DynamicTroopEntity>)entityType, world);
    }

    @Override
    protected void method_5959() {
        super.method_5959();
        this.field_6201.method_6277(2, (class_1352)new class_1366((class_1314)this, 1.1, false));
        this.field_6185.method_6277(1, (class_1352)new class_1399((class_1314)this, new Class[0]));
    }

    @Override
    public boolean supportsDialogueInteraction() {
        return true;
    }

    @Override
    public boolean beginDialogue(class_3222 player) {
        if (!this.canInteractWith(player)) {
            return false;
        }
        return super.beginDialogue(player);
    }

    @Override
    public boolean forceBeginDialogue(class_3222 player) {
        if (!this.canInteractWith(player)) {
            return false;
        }
        return super.forceBeginDialogue(player);
    }

    @Override
    public class_1799 getIndicatorStack() {
        return new class_1799((class_1935)class_1802.field_8687);
    }

    public long getSpawnDay() {
        return this.spawnDay;
    }

    public void setSpawnDay(long spawnDay) {
        this.spawnDay = spawnDay;
    }

    @Override
    public void method_5773() {
        class_1309 target;
        super.method_5773();
        class_1937 class_19372 = this.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        if (this.field_6012 % 10 == 0) {
            this.acquireRestrictedKingdomTarget();
        }
        if (!((target = this.method_5968()) == null || target.method_5805() && this.isBlockedTarget(target))) {
            this.method_5980(null);
            target = null;
        }
        if (target != null && this.usesBowLoadout()) {
            this.tickBowCombat(target);
        }
        if (this.field_6012 % 20 != 0) {
            return;
        }
        if (GotTroops.shouldExpireHireable(serverWorld, this)) {
            this.discardMount();
            this.method_31472();
        }
    }

    public void method_6078(class_1282 damageSource) {
        this.discardMount();
        super.method_6078(damageSource);
    }

    public void method_5650(class_1297.class_5529 reason) {
        this.discardMount();
        super.method_5650(reason);
    }

    public void discardMount() {
        class_1297 class_12972;
        if (!this.method_37908().method_8608() && (class_12972 = this.method_5854()) instanceof PartyHorseEntity) {
            PartyHorseEntity horse = (PartyHorseEntity)class_12972;
            horse.method_31472();
        }
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10544(SPAWN_DAY_KEY, this.spawnDay);
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        if (nbt.method_10545(SPAWN_DAY_KEY)) {
            this.spawnDay = nbt.method_10537(SPAWN_DAY_KEY);
        }
    }

    private boolean canInteractWith(class_3222 player) {
        return player != null && !this.isBlockedKingdom(player);
    }

    private boolean isBlockedKingdom(class_3222 player) {
        if (player == null) {
            return false;
        }
        DynamicTroopDefinition definition = this.getTroopDefinition();
        if (definition == null) {
            return false;
        }
        String kingdomId = ((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedKingdomId();
        return !definition.canBeHiredByKingdom(kingdomId);
    }

    private boolean isBlockedTarget(class_1309 target) {
        if (!(target instanceof class_3222)) {
            return false;
        }
        class_3222 player = (class_3222)target;
        return this.isBlockedKingdom(player);
    }

    private void acquireRestrictedKingdomTarget() {
        class_3222 serverPlayer;
        DynamicTroopDefinition definition = this.getTroopDefinition();
        if (definition == null || !definition.hasDisallowedHireKingdoms() || this.isInDialogueScene()) {
            return;
        }
        class_1309 currentTarget = this.method_5968();
        if (currentTarget != null && currentTarget.method_5805() && this.isBlockedTarget(currentTarget) && this.method_6057((class_1297)currentTarget)) {
            return;
        }
        this.method_5980(null);
        class_1657 nearestPlayer = this.method_37908().method_18460((class_1297)this, 16.0);
        if (nearestPlayer instanceof class_3222 && (serverPlayer = (class_3222)nearestPlayer).method_5805() && this.isBlockedKingdom(serverPlayer) && this.method_6057((class_1297)serverPlayer)) {
            this.method_5980((class_1309)serverPlayer);
        }
    }

    private boolean usesBowLoadout() {
        return BowCombatHelper.isBow(this.method_6118(class_1304.field_6173));
    }

    private void tickBowCombat(class_1309 target) {
        this.method_5988().method_6226((class_1297)target, 30.0f, 30.0f);
        double distanceSq = this.method_5858((class_1297)target);
        if (distanceSq > 100.0) {
            this.method_5942().method_6335((class_1297)target, 1.05);
            return;
        }
        this.method_5942().method_6340();
        class_1799 bow = this.method_6118(class_1304.field_6173);
        if (this.method_6057((class_1297)target) && this.field_6012 % BowCombatHelper.getAttackIntervalTicks(bow, 20) == 0) {
            this.shootAt(target);
        }
    }

    private void shootAt(class_1309 target) {
        class_1799 bow = this.method_6118(class_1304.field_6173);
        if (!(bow.method_7909() instanceof class_1753)) {
            return;
        }
        class_1667 arrow = new class_1667(this.method_37908(), (class_1309)this, new class_1799((class_1935)class_1802.field_8107), bow);
        BowCombatHelper.applyProjectileModifiers(bow, (class_1665)arrow);
        double dx = target.method_23317() - this.method_23317();
        double dz = target.method_23321() - this.method_23321();
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
        double dy = target.method_23323(0.3333333333333333) - arrow.method_23318() + horizontalDistance * 0.2;
        arrow.method_7485(dx, dy, dz, 1.6f, 12.0f);
        this.method_37908().method_8649((class_1297)arrow);
        this.method_5783(class_3417.field_14633, 1.0f, 0.8f + this.method_59922().method_43057() * 0.4f);
    }
}

