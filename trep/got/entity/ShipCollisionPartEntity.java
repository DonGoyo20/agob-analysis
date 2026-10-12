/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_4048
 *  net.minecraft.class_4050
 */
package trep.got.entity;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_4048;
import net.minecraft.class_4050;
import trep.got.entity.ShipEntity;
import trep.got.registry.GotEntities;

public final class ShipCollisionPartEntity
extends class_1297 {
    private static final class_2940<Integer> OWNER_ID = class_2945.method_12791(ShipCollisionPartEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Integer> PART_INDEX = class_2945.method_12791(ShipCollisionPartEntity.class, (class_2941)class_2943.field_13327);
    private class_238 collisionBox = class_238.method_30048((class_243)this.method_19538(), (double)0.01, (double)0.01, (double)0.01);

    public ShipCollisionPartEntity(class_1299<? extends ShipCollisionPartEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.field_5960 = false;
        this.method_5875(true);
    }

    public ShipCollisionPartEntity(class_1937 world, ShipEntity owner, int partIndex) {
        this(GotEntities.SHIP_COLLISION_PART, world);
        this.setOwner(owner, partIndex);
    }

    protected void method_5693(class_2945.class_9222 builder) {
        builder.method_56912(OWNER_ID, (Object)-1);
        builder.method_56912(PART_INDEX, (Object)0);
    }

    protected void method_5749(class_2487 nbt) {
        this.field_6011.method_12778(OWNER_ID, (Object)nbt.method_10550("owner_id"));
        this.field_6011.method_12778(PART_INDEX, (Object)nbt.method_10550("part_index"));
    }

    protected void method_5652(class_2487 nbt) {
        nbt.method_10569("owner_id", ((Integer)this.field_6011.method_12789(OWNER_ID)).intValue());
        nbt.method_10569("part_index", ((Integer)this.field_6011.method_12789(PART_INDEX)).intValue());
    }

    public void method_5773() {
        super.method_5773();
        this.method_31472();
    }

    protected class_238 method_33332() {
        return this.collisionBox;
    }

    public class_4048 method_18377(class_4050 pose) {
        double width = Math.max(this.collisionBox.method_17939(), this.collisionBox.method_17941());
        double height = Math.max(this.collisionBox.method_17940(), 0.01);
        return class_4048.method_18385((float)((float)Math.max(width, 0.01)), (float)((float)height));
    }

    public boolean method_5863() {
        return false;
    }

    public boolean method_5732() {
        return false;
    }

    public boolean method_5810() {
        return false;
    }

    public boolean method_30948() {
        return true;
    }

    public boolean method_30949(class_1297 other) {
        ShipCollisionPartEntity part;
        if (other == this || other == this.getOwner()) {
            return false;
        }
        if (other instanceof ShipCollisionPartEntity && (part = (ShipCollisionPartEntity)other).getOwner() == this.getOwner()) {
            return false;
        }
        return super.method_30949(other);
    }

    public boolean method_5779(class_1297 entity) {
        return entity == this || entity == this.getOwner();
    }

    public boolean method_31746() {
        return false;
    }

    private void setOwner(ShipEntity owner, int partIndex) {
        this.field_6011.method_12778(OWNER_ID, (Object)owner.method_5628());
        this.field_6011.method_12778(PART_INDEX, (Object)partIndex);
    }

    private ShipEntity getOwner() {
        ShipEntity ship;
        class_1297 entity = this.method_37908().method_8469(((Integer)this.field_6011.method_12789(OWNER_ID)).intValue());
        return entity instanceof ShipEntity ? (ship = (ShipEntity)entity) : null;
    }
}

