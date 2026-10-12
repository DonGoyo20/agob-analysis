/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1262
 *  net.minecraft.class_1278
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1799
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2371
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2586
 *  net.minecraft.class_2680
 *  net.minecraft.class_3218
 *  net.minecraft.class_3908
 *  net.minecraft.class_3913
 *  net.minecraft.class_7225$class_7874
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block.entity;

import java.util.stream.IntStream;
import net.minecraft.class_1262;
import net.minecraft.class_1278;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3908;
import net.minecraft.class_3913;
import net.minecraft.class_7225;
import org.jetbrains.annotations.Nullable;
import trep.got.inventory.ImplementedInventory;
import trep.got.item.BrewItem;
import trep.got.registry.GotBlockEntities;
import trep.got.screen.AgingBarrelScreenHandler;

public final class AgingBarrelBlockEntity
extends class_2586
implements ImplementedInventory,
class_1278,
class_3908 {
    private static final int[] ALL_SLOTS = IntStream.range(0, 27).toArray();
    private static final long AGING_SYNC_INTERVAL_TICKS = 20L;
    private final class_2371<class_1799> items = class_2371.method_10213((int)27, (Object)class_1799.field_8037);
    private final class_3913 propertyDelegate = new class_3913(){

        public int method_17390(int index) {
            return index == 0 ? AgingBarrelBlockEntity.this.agingTime : 0;
        }

        public void method_17391(int index, int value) {
            if (index == 0) {
                AgingBarrelBlockEntity.this.agingTime = value;
            }
        }

        public int method_17389() {
            return 1;
        }
    };
    private int agingTime;
    private long lastAgingDay = -1L;

    public AgingBarrelBlockEntity(class_2338 pos, class_2680 state) {
        super(GotBlockEntities.AGING_BARREL, pos, state);
    }

    @Override
    public class_2371<class_1799> getItems() {
        return this.items;
    }

    public class_3913 getPropertyDelegate() {
        return this.propertyDelegate;
    }

    public class_2561 method_5476() {
        return class_2561.method_43471((String)"container.agob.aging_barrel");
    }

    public class_1703 createMenu(int syncId, class_1661 playerInventory, class_1657 player) {
        return new AgingBarrelScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void method_5431() {
        super.method_5431();
        if (this.field_11863 != null) {
            this.field_11863.method_8413(this.field_11867, this.method_11010(), this.method_11010(), 2);
        }
    }

    protected void method_11007(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11007(nbt, registries);
        class_1262.method_5426((class_2487)nbt, this.items, (class_7225.class_7874)registries);
        nbt.method_10569("aging_time", this.agingTime);
        nbt.method_10544("last_aging_day", this.lastAgingDay);
    }

    protected void method_11014(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11014(nbt, registries);
        class_1262.method_5429((class_2487)nbt, this.items, (class_7225.class_7874)registries);
        this.agingTime = nbt.method_10550("aging_time");
        this.lastAgingDay = nbt.method_10545("last_aging_day") ? nbt.method_10537("last_aging_day") : -1L;
    }

    public int[] method_5494(class_2350 side) {
        return ALL_SLOTS;
    }

    public boolean method_5492(int slot, class_1799 stack, @Nullable class_2350 dir) {
        return true;
    }

    public boolean method_5493(int slot, class_1799 stack, class_2350 dir) {
        return true;
    }

    public static void tick(class_3218 world, class_2338 pos, class_2680 state, AgingBarrelBlockEntity blockEntity) {
        boolean dayAdvanced;
        long currentDay = Math.max(0L, world.method_8532() / 24000L);
        boolean bl = dayAdvanced = blockEntity.lastAgingDay < 0L || currentDay > blockEntity.lastAgingDay;
        if (!dayAdvanced && world.method_8510() % 20L != 0L) {
            return;
        }
        blockEntity.synchronizeWithWorldDay(world);
    }

    public void synchronizeWithWorldDay(class_3218 world) {
        long currentDay = Math.max(0L, world.method_8532() / 24000L);
        this.agingTime = (int)Math.max(0L, world.method_8532() % 24000L);
        if (this.lastAgingDay < 0L) {
            this.lastAgingDay = currentDay;
            this.method_5431();
            return;
        }
        long elapsedDays = currentDay - this.lastAgingDay;
        if (elapsedDays <= 0L) {
            return;
        }
        this.lastAgingDay = currentDay;
        boolean changed = false;
        for (int slot = 0; slot < this.items.size(); ++slot) {
            class_1799 stack = (class_1799)this.items.get(slot);
            if (stack.method_7960() || !(stack.method_7909() instanceof BrewItem)) continue;
            BrewItem.setAgeDays(stack, BrewItem.getAgeDays(stack) + (int)elapsedDays);
            changed = true;
        }
        if (changed) {
            this.method_5431();
        }
    }
}

