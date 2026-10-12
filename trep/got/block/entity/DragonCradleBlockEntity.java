/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2487
 *  net.minecraft.class_2586
 *  net.minecraft.class_2596
 *  net.minecraft.class_2602
 *  net.minecraft.class_2622
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_3218
 *  net.minecraft.class_7225$class_7874
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block.entity;

import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2586;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_7225;
import org.jetbrains.annotations.Nullable;
import trep.got.block.DragonCradleBlock;
import trep.got.entity.DragonEntity;
import trep.got.entity.dragon.DragonBondWorldState;
import trep.got.entity.dragon.DragonHatchlingFactory;
import trep.got.registry.GotBlockEntities;

public final class DragonCradleBlockEntity
extends class_2586 {
    private static final long HATCH_CHECK_INTERVAL_TICKS = 3600L;
    private static final float HATCH_CHANCE = 0.1f;
    private class_1799 eggStack = class_1799.field_8037;
    @Nullable
    private UUID ownerUuid;
    private long nextHatchCheckTime;

    public DragonCradleBlockEntity(class_2338 pos, class_2680 state) {
        super(GotBlockEntities.DRAGON_CRADLE, pos, state);
    }

    public class_1799 getEggStack() {
        return this.eggStack;
    }

    public boolean hasEgg() {
        return !this.eggStack.method_7960();
    }

    @Nullable
    public UUID getOwnerUuid() {
        return this.ownerUuid;
    }

    public void setEgg(class_1799 stack, UUID ownerUuid, long worldTime) {
        this.eggStack = stack.method_46651(1);
        this.ownerUuid = ownerUuid;
        this.nextHatchCheckTime = worldTime + 3600L;
        this.sync();
    }

    public class_1799 takeEgg() {
        class_1799 stack = this.eggStack.method_7972();
        this.eggStack = class_1799.field_8037;
        this.ownerUuid = null;
        this.nextHatchCheckTime = 0L;
        this.sync();
        return stack;
    }

    public void clearEggAfterHatch() {
        this.eggStack = class_1799.field_8037;
        this.ownerUuid = null;
        this.nextHatchCheckTime = 0L;
        this.sync();
    }

    public static void tick(class_1937 world, class_2338 pos, class_2680 state, DragonCradleBlockEntity cradle) {
        class_3218 serverWorld;
        block7: {
            block6: {
                if (!(world instanceof class_3218)) break block6;
                serverWorld = (class_3218)world;
                if (cradle.hasEgg() && cradle.ownerUuid != null && world.method_8510() >= cradle.nextHatchCheckTime) break block7;
            }
            return;
        }
        DragonBondWorldState bonds = DragonBondWorldState.get(serverWorld);
        if (bonds.hasLivingDragon(serverWorld, cradle.ownerUuid)) {
            cradle.nextHatchCheckTime = world.method_8510() + 3600L;
            cradle.sync();
            return;
        }
        if (world.field_9229.method_43057() >= 0.1f) {
            cradle.nextHatchCheckTime = world.method_8510() + 3600L;
            cradle.sync();
            return;
        }
        DragonEntity dragon = DragonHatchlingFactory.create(serverWorld, pos, cradle.ownerUuid);
        if (dragon == null || !serverWorld.method_8649((class_1297)dragon)) {
            cradle.nextHatchCheckTime = world.method_8510() + 3600L;
            cradle.sync();
            return;
        }
        bonds.bind(cradle.ownerUuid, dragon);
        cradle.clearEggAfterHatch();
        serverWorld.method_8652(pos, (class_2680)state.method_11657((class_2769)DragonCradleBlock.HAS_EGG, (Comparable)Boolean.valueOf(false)), 3);
    }

    private void sync() {
        this.method_5431();
        if (this.field_11863 != null) {
            this.field_11863.method_8413(this.field_11867, this.method_11010(), this.method_11010(), 3);
        }
    }

    protected void method_11007(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11007(nbt, registries);
        if (!this.eggStack.method_7960()) {
            nbt.method_10566("egg_stack", this.eggStack.method_57358(registries));
        }
        if (this.ownerUuid != null) {
            nbt.method_25927("owner", this.ownerUuid);
        }
        nbt.method_10544("next_hatch_check_time", this.nextHatchCheckTime);
    }

    protected void method_11014(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11014(nbt, registries);
        this.eggStack = nbt.method_10545("egg_stack") ? class_1799.method_57359((class_7225.class_7874)registries, (class_2487)nbt.method_10562("egg_stack")) : class_1799.field_8037;
        this.ownerUuid = nbt.method_25928("owner") ? nbt.method_25926("owner") : null;
        this.nextHatchCheckTime = nbt.method_10537("next_hatch_check_time");
    }

    public class_2487 method_16887(class_7225.class_7874 registries) {
        return this.method_38244(registries);
    }

    @Nullable
    public class_2596<class_2602> method_38235() {
        return class_2622.method_38585((class_2586)this);
    }
}

