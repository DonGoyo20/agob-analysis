/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2487
 *  net.minecraft.class_2586
 *  net.minecraft.class_2596
 *  net.minecraft.class_2602
 *  net.minecraft.class_2622
 *  net.minecraft.class_2680
 *  net.minecraft.class_3218
 *  net.minecraft.class_7225$class_7874
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block.entity;

import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2586;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_7225;
import org.jetbrains.annotations.Nullable;
import trep.got.registry.GotBlockEntities;

public final class CupBlockEntity
extends class_2586 {
    private class_1799 storedStack = class_1799.field_8037;
    private int liquidColor = -1;

    public CupBlockEntity(class_2338 pos, class_2680 state) {
        super(GotBlockEntities.CUP, pos, state);
    }

    public class_1799 getStoredStack() {
        return this.storedStack.method_7972();
    }

    public int getLiquidColor() {
        return this.liquidColor;
    }

    public void setStoredStack(class_1799 stack, int liquidColor) {
        class_1937 class_19372;
        this.storedStack = stack.method_46651(1);
        this.liquidColor = liquidColor;
        this.method_5431();
        if (this.field_11863 != null) {
            this.field_11863.method_8413(this.field_11867, this.method_11010(), this.method_11010(), 3);
        }
        if ((class_19372 = this.field_11863) instanceof class_3218) {
            class_3218 serverWorld = (class_3218)class_19372;
            serverWorld.method_14178().method_14128(this.field_11867);
        }
    }

    protected void method_11007(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11007(nbt, registries);
        nbt.method_10566("stored_stack", this.storedStack.method_57358(registries));
        nbt.method_10569("liquid_color", this.liquidColor);
    }

    protected void method_11014(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11014(nbt, registries);
        this.storedStack = nbt.method_10545("stored_stack") ? class_1799.method_57359((class_7225.class_7874)registries, (class_2487)nbt.method_10562("stored_stack")) : class_1799.field_8037;
        this.liquidColor = nbt.method_10545("liquid_color") ? nbt.method_10550("liquid_color") : -1;
    }

    public class_2487 method_16887(class_7225.class_7874 registries) {
        return this.method_38244(registries);
    }

    @Nullable
    public class_2596<class_2602> method_38235() {
        return class_2622.method_38585((class_2586)this);
    }
}

