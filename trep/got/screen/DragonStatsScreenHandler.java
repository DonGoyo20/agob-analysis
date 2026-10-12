/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1263
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1735
 *  net.minecraft.class_1799
 *  net.minecraft.class_3913
 *  net.minecraft.class_3919
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.screen;

import net.minecraft.class_1263;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_3913;
import net.minecraft.class_3919;
import org.jetbrains.annotations.Nullable;
import trep.got.entity.DragonEntity;
import trep.got.registry.GotScreenHandlers;
import trep.got.screen.DragonStatsScreenOpeningData;

public final class DragonStatsScreenHandler
extends class_1703 {
    private static final int PROPERTY_COUNT = 4;
    @Nullable
    private final DragonEntity dragon;
    private final int entityId;
    private final class_3913 properties;

    private DragonStatsScreenHandler(int syncId, class_1661 playerInventory, @Nullable DragonEntity dragon, int entityId, class_3913 properties) {
        super(GotScreenHandlers.DRAGON_STATS, syncId);
        this.dragon = dragon;
        this.entityId = entityId;
        this.properties = properties;
        this.method_17360(properties);
        this.addPlayerSlots(playerInventory);
    }

    public static DragonStatsScreenHandler createServer(int syncId, class_1661 playerInventory, DragonEntity dragon) {
        return new DragonStatsScreenHandler(syncId, playerInventory, dragon, dragon.method_5628(), new DragonStatsPropertyDelegate(dragon));
    }

    public static DragonStatsScreenHandler createClient(int syncId, class_1661 playerInventory, DragonStatsScreenOpeningData openingData) {
        return new DragonStatsScreenHandler(syncId, playerInventory, null, openingData.entityId(), (class_3913)new class_3919(4));
    }

    @Nullable
    public DragonEntity getDragon() {
        return this.dragon;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public int getAgeDays() {
        return this.properties.method_17390(0);
    }

    public int getAgeMaxDays() {
        return this.properties.method_17390(1);
    }

    public int getHunger() {
        return this.properties.method_17390(2);
    }

    public int getHungerMax() {
        return this.properties.method_17390(3);
    }

    public boolean method_7597(class_1657 player) {
        return this.dragon == null || this.dragon.method_5805() && player.method_56094((class_1297)this.dragon, 4.0);
    }

    public class_1799 method_7601(class_1657 player, int slotIndex) {
        class_1799 original = class_1799.field_8037;
        class_1735 slot = (class_1735)this.field_7761.get(slotIndex);
        if (slot != null && slot.method_7681()) {
            class_1799 stack = slot.method_7677();
            original = stack.method_7972();
            if (slotIndex < 27 ? !this.method_7616(stack, 27, 36, false) : !this.method_7616(stack, 0, 27, false)) {
                return class_1799.field_8037;
            }
            if (stack.method_7960()) {
                slot.method_53512(class_1799.field_8037);
            } else {
                slot.method_7668();
            }
        }
        return original;
    }

    private void addPlayerSlots(class_1661 playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.method_7621(new class_1735((class_1263)playerInventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }
        for (int column = 0; column < 9; ++column) {
            this.method_7621(new class_1735((class_1263)playerInventory, column, 8 + column * 18, 142));
        }
    }

    private record DragonStatsPropertyDelegate(DragonEntity dragon) implements class_3913
    {
        public int method_17390(int index) {
            return switch (index) {
                case 0 -> this.dragon.getAgeDays();
                case 1 -> this.dragon.getAgeMaxDays();
                case 2 -> this.dragon.getHunger();
                case 3 -> this.dragon.getHungerMax();
                default -> 0;
            };
        }

        public void method_17391(int index, int value) {
        }

        public int method_17389() {
            return 4;
        }
    }
}

