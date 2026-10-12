/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_4738
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1313
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_3532
 *  net.minecraft.class_4048
 *  net.minecraft.class_4050
 */
package trep.got.entity;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1313;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3532;
import net.minecraft.class_4048;
import net.minecraft.class_4050;
import trep.got.block.SeatFurnitureBlock;
import trep.got.registry.GotEntities;

public final class SeatEntity
extends class_1297 {
    private static final class_2940<class_2338> SEAT_BLOCK_POS = class_2945.method_12791(SeatEntity.class, (class_2941)class_2943.field_13324);
    private static final class_2940<Integer> HORIZONTAL_FACING = class_2945.method_12791(SeatEntity.class, (class_2941)class_2943.field_13327);

    public SeatEntity(class_1299<? extends SeatEntity> type, class_1937 world) {
        super(type, world);
        this.field_5960 = true;
        this.method_5875(true);
        this.method_5648(true);
    }

    public SeatEntity(class_1937 world, class_2338 seatBlockPos, class_243 seatPos, class_2350 facing) {
        this(GotEntities.SEAT, world);
        this.setSeatBlockPos(seatBlockPos);
        this.setFacing(facing);
        this.method_5808(seatPos.field_1352, seatPos.field_1351, seatPos.field_1350, SeatFurnitureBlock.yawFor(facing), 0.0f);
    }

    public class_2338 getSeatBlockPos() {
        return (class_2338)this.field_6011.method_12789(SEAT_BLOCK_POS);
    }

    protected void method_5693(class_2945.class_9222 builder) {
        builder.method_56912(SEAT_BLOCK_POS, (Object)class_2338.field_10980);
        builder.method_56912(HORIZONTAL_FACING, (Object)class_2350.field_11043.method_10161());
    }

    public void method_5773() {
        super.method_5773();
        this.method_18799(class_243.field_1353);
        this.method_5784(class_1313.field_6308, class_243.field_1353);
        if (this.method_37908().field_9236) {
            return;
        }
        class_2680 state = this.method_37908().method_8320(this.getSeatBlockPos());
        if (!(state.method_26204() instanceof SeatFurnitureBlock) || !this.method_5782()) {
            this.method_31472();
            return;
        }
        this.setFacing((class_2350)state.method_11654((class_2769)SeatFurnitureBlock.field_11177));
        this.applyPassengerBodyYaw();
        float seatYaw = this.method_36454();
        float minYaw = seatYaw - 90.0f;
        float maxYaw = seatYaw + 90.0f;
        for (class_1297 passenger : this.method_5685()) {
            passenger.method_36456(class_3532.method_15363((float)passenger.method_36454(), (float)minYaw, (float)maxYaw));
            passenger.field_5982 = class_3532.method_15363((float)passenger.field_5982, (float)minYaw, (float)maxYaw);
        }
    }

    protected void method_5865(class_1297 passenger, class_1297.class_4738 positionUpdater) {
        if (!this.method_5626(passenger)) {
            return;
        }
        positionUpdater.accept(passenger, this.method_23317(), this.method_23318(), this.method_23321());
        this.applyPassengerBodyYaw(passenger);
    }

    public void method_5644(class_1297 passenger) {
        this.applyPassengerBodyYaw(passenger);
    }

    public class_243 method_24829(class_1309 passenger) {
        class_2350 facing = this.getSeatFacing();
        class_2338 seatBlockPos = this.getSeatBlockPos();
        class_243 front = new class_243((double)facing.method_10148(), 0.0, (double)facing.method_10165()).method_1021(0.75);
        return new class_243((double)seatBlockPos.method_10263() + 0.5 + front.field_1352, (double)seatBlockPos.method_10264() + 0.1, (double)seatBlockPos.method_10260() + 0.5 + front.field_1350);
    }

    public boolean method_31746() {
        return false;
    }

    public boolean method_5863() {
        return false;
    }

    public boolean method_49108() {
        return false;
    }

    public boolean method_5732() {
        return false;
    }

    public class_4048 method_18377(class_4050 pose) {
        return class_4048.method_18385((float)0.01f, (float)0.01f);
    }

    protected void method_5749(class_2487 nbt) {
    }

    protected void method_5652(class_2487 nbt) {
    }

    private class_2350 getSeatFacing() {
        return class_2350.method_10139((int)((Integer)this.field_6011.method_12789(HORIZONTAL_FACING)));
    }

    private void setFacing(class_2350 facing) {
        class_2350 horizontalFacing = facing.method_10166().method_10179() ? facing : class_2350.field_11043;
        this.field_6011.method_12778(HORIZONTAL_FACING, (Object)horizontalFacing.method_10161());
        this.method_36456(SeatFurnitureBlock.yawFor(horizontalFacing));
        this.field_5982 = this.method_36454();
    }

    private void setSeatBlockPos(class_2338 seatBlockPos) {
        this.field_6011.method_12778(SEAT_BLOCK_POS, (Object)seatBlockPos.method_10062());
    }

    private void applyPassengerBodyYaw() {
        for (class_1297 passenger : this.method_5685()) {
            this.applyPassengerBodyYaw(passenger);
        }
    }

    private void applyPassengerBodyYaw(class_1297 passenger) {
        float yaw = SeatFurnitureBlock.yawFor(this.getSeatFacing());
        if (passenger instanceof class_1309) {
            class_1309 living = (class_1309)passenger;
            living.method_5636(yaw);
            living.field_6220 = yaw;
        }
    }
}

