/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1498
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2960
 *  net.minecraft.class_3419
 *  net.minecraft.class_7923
 */
package trep.got.troop;

import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1498;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_3419;
import net.minecraft.class_7923;
import trep.got.GotMod;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.TroopStatResolver;

public final class DynamicTroopLoadoutApplier {
    private DynamicTroopLoadoutApplier() {
    }

    public static void clearEquipment(class_1309 entity) {
        if (entity == null) {
            return;
        }
        for (class_1304 slot : class_1304.values()) {
            entity.method_5673(slot, class_1799.field_8037);
        }
    }

    public static void applyEquipment(class_1309 entity, DynamicTroopDefinition definition) {
        if (entity == null || definition == null) {
            return;
        }
        DynamicTroopLoadoutApplier.clearEquipment(entity);
        for (class_1304 slot : class_1304.values()) {
            class_2960 itemId = definition.equipment(slot);
            if (itemId == null) continue;
            class_1792 item = (class_1792)class_7923.field_41178.method_10223(itemId);
            if (item == class_1802.field_8162) {
                GotMod.LOGGER.error("Invalid troop equipment item {} for troop {}", (Object)itemId, (Object)definition.id());
                continue;
            }
            entity.method_5673(slot, new class_1799((class_1935)item));
        }
    }

    public static void applyMountEquipment(class_1498 horse, DynamicTroopDefinition definition) {
        class_1792 armor;
        class_2960 armorId;
        class_1792 saddle;
        if (horse == null || definition == null) {
            return;
        }
        TroopStatResolver.applyMountAttributes(horse, definition);
        class_2960 saddleId = definition.mountEquipment("saddle");
        if (saddleId != null && (saddle = (class_1792)class_7923.field_41178.method_10223(saddleId)) != class_1802.field_8162) {
            horse.method_6576(new class_1799((class_1935)saddle), class_3419.field_15254);
        }
        if ((armorId = definition.mountEquipment("armor")) != null && (armor = (class_1792)class_7923.field_41178.method_10223(armorId)) != class_1802.field_8162) {
            horse.method_56680().method_5447(1, new class_1799((class_1935)armor));
        }
    }
}

