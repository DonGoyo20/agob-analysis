/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1498
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_4048
 *  net.minecraft.class_5132$class_5133
 *  net.minecraft.class_5134
 */
package trep.got.entity;

import java.util.UUID;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1498;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_4048;
import net.minecraft.class_5132;
import net.minecraft.class_5134;
import trep.got.entity.DynamicTroopEntity;
import trep.got.entity.PartyTroopEntity;
import trep.got.troop.GotTroops;

public final class PartyHorseEntity
extends class_1498 {
    private static final String OWNER_UUID_KEY = "gotmod.party_horse_owner_uuid";
    private static final String UNIT_ID_KEY = "gotmod.party_horse_unit_id";
    private UUID ownerUuid;
    private int unitId = -1;

    public PartyHorseEntity(class_1299<? extends PartyHorseEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.method_6766(true);
        this.method_6749(100);
    }

    public static class_5132.class_5133 createAttributes() {
        return class_1498.method_26899().method_26868(class_5134.field_23719, 0.28).method_26868(class_5134.field_23716, 24.0);
    }

    public UUID method_6139() {
        return this.ownerUuid;
    }

    public void method_6732(UUID ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    public int getUnitId() {
        return this.unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public class_1269 method_5992(class_1657 player, class_1268 hand) {
        return class_1269.field_5814;
    }

    public boolean method_5643(class_1282 source, float amount) {
        class_1297 attacker = source.method_5529();
        class_1297 sourceEntity = source.method_5526();
        if (GotTroops.areAlliedPartyEntities(attacker, (class_1297)this) || GotTroops.areAlliedPartyEntities(sourceEntity, (class_1297)this)) {
            return false;
        }
        return super.method_5643(source, amount);
    }

    protected boolean method_5818(class_1297 passenger) {
        if (passenger instanceof PartyTroopEntity) {
            PartyTroopEntity troop = (PartyTroopEntity)passenger;
            return troop.getUnitId() == this.unitId;
        }
        return this.unitId < 0 && passenger instanceof DynamicTroopEntity;
    }

    protected class_243 method_52533(class_1297 passenger, class_4048 dimensions, float scaleFactor) {
        class_243 pos = super.method_52533(passenger, dimensions, scaleFactor);
        return pos.method_1031(0.0, -1.0, 0.0);
    }

    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        if (this.ownerUuid != null) {
            nbt.method_25927(OWNER_UUID_KEY, this.ownerUuid);
        }
        nbt.method_10569(UNIT_ID_KEY, this.unitId);
    }

    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        if (nbt.method_25928(OWNER_UUID_KEY)) {
            this.ownerUuid = nbt.method_25926(OWNER_UUID_KEY);
        }
        if (nbt.method_10545(UNIT_ID_KEY)) {
            this.unitId = nbt.method_10550(UNIT_ID_KEY);
        }
    }
}

