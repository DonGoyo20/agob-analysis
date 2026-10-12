/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 */
package trep.got.entity;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.ShipEntity;

public abstract class AbstractKingdomCaptainNpcEntity
extends AbstractKingdomNpcEntity {
    protected AbstractKingdomCaptainNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    public boolean supportsDialogueInteraction() {
        return false;
    }

    @Override
    public class_1799 getIndicatorStack() {
        return class_1799.field_8037;
    }

    public class_243 method_55668(class_1297 vehicle) {
        ShipEntity ship;
        class_243 attachment = super.method_55668(vehicle);
        if (vehicle instanceof ShipEntity && (ship = (ShipEntity)vehicle).isVoyageMode()) {
            return new class_243(0.0, attachment.field_1351, 0.0);
        }
        return attachment;
    }

    @Override
    public void method_5773() {
        super.method_5773();
        this.tickVoyageMount();
    }

    public void method_6091(class_243 movementInput) {
        ShipEntity ship;
        class_1297 class_12972 = this.method_5854();
        if (class_12972 instanceof ShipEntity && (ship = (ShipEntity)class_12972).isVoyageMode()) {
            this.method_18799(class_243.field_1353);
            return;
        }
        super.method_6091(movementInput);
    }

    private void tickVoyageMount() {
        ShipEntity ship;
        class_1297 class_12972 = this.method_5854();
        if (!(class_12972 instanceof ShipEntity) || !(ship = (ShipEntity)class_12972).isVoyageMode()) {
            return;
        }
        this.method_5942().method_6340();
        this.method_18799(class_243.field_1353);
        class_243 seatPos = ship.getSeatWorldPos(0);
        double targetY = seatPos.field_1351 - (double)this.method_17682() * 0.5;
        this.method_5814(seatPos.field_1352, targetY, seatPos.field_1350);
        this.field_6014 = seatPos.field_1352;
        this.field_6036 = targetY;
        this.field_5969 = seatPos.field_1350;
        this.field_6038 = seatPos.field_1352;
        this.field_5971 = targetY;
        this.field_5989 = seatPos.field_1350;
        float yaw = ship.method_36454();
        this.method_36456(yaw);
        this.field_5982 = yaw;
        this.field_6283 = yaw;
        this.field_6220 = yaw;
        this.field_6241 = yaw;
        this.field_6259 = yaw;
        this.method_36457(0.0f);
        this.field_6004 = 0.0f;
    }

    @Override
    public abstract class_2960 getKingdomId();
}

