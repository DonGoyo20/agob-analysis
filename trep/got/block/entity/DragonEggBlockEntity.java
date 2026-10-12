/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2487
 *  net.minecraft.class_2586
 *  net.minecraft.class_2596
 *  net.minecraft.class_2602
 *  net.minecraft.class_2622
 *  net.minecraft.class_2680
 *  net.minecraft.class_7225$class_7874
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block.entity;

import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2586;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_7225;
import org.jetbrains.annotations.Nullable;
import trep.got.entity.dragon.DragonEggColor;
import trep.got.registry.GotBlockEntities;

public final class DragonEggBlockEntity
extends class_2586 {
    private DragonEggColor color = DragonEggColor.DEFAULT;

    public DragonEggBlockEntity(class_2338 pos, class_2680 state) {
        super(GotBlockEntities.DRAGON_EGG, pos, state);
    }

    public DragonEggColor getColor() {
        return this.color;
    }

    public void setColor(DragonEggColor color) {
        this.color = color == null ? DragonEggColor.DEFAULT : color;
        this.sync();
    }

    private void sync() {
        this.method_5431();
        if (this.field_11863 != null) {
            this.field_11863.method_8413(this.field_11867, this.method_11010(), this.method_11010(), 3);
        }
    }

    protected void method_11007(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11007(nbt, registries);
        nbt.method_10582("DragonEggColor", this.color.id());
    }

    protected void method_11014(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11014(nbt, registries);
        this.color = DragonEggColor.byId(nbt.method_10558("DragonEggColor"));
    }

    public class_2487 method_16887(class_7225.class_7874 registries) {
        return this.method_38244(registries);
    }

    @Nullable
    public class_2596<class_2602> method_38235() {
        return class_2622.method_38585((class_2586)this);
    }
}

