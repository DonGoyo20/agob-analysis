/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.DynamicOps
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2487
 *  net.minecraft.class_2509
 *  net.minecraft.class_2520
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

import com.mojang.serialization.DynamicOps;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2509;
import net.minecraft.class_2520;
import net.minecraft.class_2586;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_7225;
import org.jetbrains.annotations.Nullable;
import trep.got.blacksmith.BlacksmithCraftSession;
import trep.got.blacksmith.BlacksmithWorkbenchAccess;
import trep.got.registry.GotBlockEntities;

public final class BlacksmithFurnaceBlockEntity
extends class_2586 {
    @Nullable
    private BlacksmithWorkbenchAccess.WorkbenchPair pair;
    @Nullable
    private BlacksmithCraftSession session;

    public BlacksmithFurnaceBlockEntity(class_2338 pos, class_2680 state) {
        super(GotBlockEntities.BLACKSMITH_FURNACE, pos, state);
    }

    @Nullable
    public BlacksmithWorkbenchAccess.WorkbenchPair getPair() {
        return this.pair;
    }

    @Nullable
    public BlacksmithCraftSession getSession() {
        return this.session;
    }

    public void startSession(class_2338 tableOrigin, BlacksmithCraftSession session) {
        this.pair = new BlacksmithWorkbenchAccess.WorkbenchPair(this.field_11867, tableOrigin);
        this.session = session;
        this.syncSession();
    }

    public void clearSession() {
        this.session = null;
        this.pair = null;
        this.syncSession();
    }

    public void syncSession() {
        class_1937 class_19372;
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
        if (this.pair != null) {
            nbt.method_10566("table_origin", (class_2520)class_2338.field_25064.encodeStart((DynamicOps)class_2509.field_11560, (Object)this.pair.tableOrigin()).getOrThrow());
        }
        if (this.session != null) {
            nbt.method_10566("craft_session", (class_2520)this.session.toNbt(registries));
        }
    }

    protected void method_11014(class_2487 nbt, class_7225.class_7874 registries) {
        class_2338 tableOrigin;
        super.method_11014(nbt, registries);
        this.pair = null;
        this.session = null;
        if (nbt.method_10545("table_origin") && (tableOrigin = (class_2338)class_2338.field_25064.parse((DynamicOps)class_2509.field_11560, (Object)nbt.method_10580("table_origin")).result().orElse(null)) != null) {
            this.pair = new BlacksmithWorkbenchAccess.WorkbenchPair(this.field_11867, tableOrigin);
        }
        if (nbt.method_10545("craft_session")) {
            this.session = BlacksmithCraftSession.fromNbt(nbt.method_10562("craft_session"), registries);
        }
    }

    public class_2487 method_16887(class_7225.class_7874 registries) {
        return this.method_38244(registries);
    }

    @Nullable
    public class_2596<class_2602> method_38235() {
        return class_2622.method_38585((class_2586)this);
    }
}

