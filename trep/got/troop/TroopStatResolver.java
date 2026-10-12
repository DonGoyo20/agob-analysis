/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1320
 *  net.minecraft.class_1324
 *  net.minecraft.class_1498
 *  net.minecraft.class_1738
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2960
 *  net.minecraft.class_5134
 *  net.minecraft.class_6880
 *  net.minecraft.class_7923
 *  net.minecraft.class_9285
 *  net.minecraft.class_9285$class_9287
 *  net.minecraft.class_9334
 */
package trep.got.troop;

import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1320;
import net.minecraft.class_1324;
import net.minecraft.class_1498;
import net.minecraft.class_1738;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_5134;
import net.minecraft.class_6880;
import net.minecraft.class_7923;
import net.minecraft.class_9285;
import net.minecraft.class_9334;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.DynamicTroopUnitType;

public final class TroopStatResolver {
    public static final double DEFAULT_HEALTH = 20.0;
    public static final double DEFAULT_DAMAGE = 3.0;
    public static final double NORMAL_MOB_WALK_SPEED = 0.23;
    public static final double DEFAULT_SPEED = 0.23;
    public static final double DEFAULT_CAVALRY_SPEED = 0.28;
    public static final double PLAYER_BASE_SPEED = 0.1;
    public static final double DEFAULT_ARROW_DAMAGE = 2.0;

    private TroopStatResolver() {
    }

    public static double baseHealth(DynamicTroopDefinition definition) {
        return definition == null || definition.baseHealth() <= 0.0 ? 20.0 : definition.baseHealth();
    }

    public static double baseDamage(DynamicTroopDefinition definition) {
        return definition == null || definition.baseDamage() <= 0.0 ? 3.0 : definition.baseDamage();
    }

    public static double baseSpeed(DynamicTroopDefinition definition) {
        if (definition == null || definition.baseSpeed() <= 0.0) {
            return definition != null && definition.unitType() == DynamicTroopUnitType.CAVALRY ? 0.28 : 0.23;
        }
        return definition.unitType() == DynamicTroopUnitType.CAVALRY ? definition.baseSpeed() : Math.max(0.23, definition.baseSpeed());
    }

    public static double totalArmor(DynamicTroopDefinition definition) {
        if (definition == null) {
            return 0.0;
        }
        double total = 0.0;
        for (class_1304 slot : new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166}) {
            total += TroopStatResolver.armorValue(TroopStatResolver.stackFor(definition.equipment(slot)));
        }
        return total;
    }

    public static double weaponDamage(DynamicTroopDefinition definition) {
        if (definition == null) {
            return 0.0;
        }
        return TroopStatResolver.attributeValue(TroopStatResolver.stackFor(definition.equipment(class_1304.field_6173)), (class_6880<class_1320>)class_5134.field_23721);
    }

    public static double totalDamage(DynamicTroopDefinition definition) {
        if (definition == null) {
            return 0.0;
        }
        double base = TroopStatResolver.baseDamage(definition);
        if (definition.unitType() == DynamicTroopUnitType.ARCHER) {
            return base + 2.0;
        }
        return base + TroopStatResolver.weaponDamage(definition);
    }

    public static double speedVsPlayer(DynamicTroopDefinition definition) {
        double speed = definition != null && definition.unitType() == DynamicTroopUnitType.CAVALRY ? TroopStatResolver.cavalrySpeed(definition) : TroopStatResolver.baseSpeed(definition);
        return speed / 0.1;
    }

    public static void applyTroopAttributes(class_1309 entity, DynamicTroopDefinition definition) {
        if (entity == null || definition == null) {
            return;
        }
        TroopStatResolver.setBaseValue(entity.method_5996(class_5134.field_23716), TroopStatResolver.baseHealth(definition));
        TroopStatResolver.setBaseValue(entity.method_5996(class_5134.field_23721), TroopStatResolver.baseDamage(definition));
        TroopStatResolver.setBaseValue(entity.method_5996(class_5134.field_23719), definition.unitType() == DynamicTroopUnitType.CAVALRY ? 0.23 : TroopStatResolver.baseSpeed(definition));
        if (entity.method_6032() > entity.method_6063() || entity.method_6032() <= 0.0f) {
            entity.method_6033(entity.method_6063());
        }
    }

    public static void applyMountAttributes(class_1498 horse, DynamicTroopDefinition definition) {
        if (horse == null || definition == null || definition.unitType() != DynamicTroopUnitType.CAVALRY) {
            return;
        }
        TroopStatResolver.setBaseValue(horse.method_5996(class_5134.field_23719), TroopStatResolver.cavalrySpeed(definition));
    }

    public static double cavalrySpeed(DynamicTroopDefinition definition) {
        return definition == null ? 0.28 : Math.max(0.28, TroopStatResolver.baseSpeed(definition));
    }

    private static void setBaseValue(class_1324 attribute, double value) {
        if (attribute != null) {
            attribute.method_6192(value);
        }
    }

    private static double attributeValue(class_1799 stack, class_6880<class_1320> attribute) {
        if (stack == null || stack.method_7960()) {
            return 0.0;
        }
        class_9285 modifiers = (class_9285)stack.method_58658().method_57830(class_9334.field_49636, (Object)stack.method_7909().method_7844());
        double value = 0.0;
        for (class_9285.class_9287 entry : modifiers.comp_2393()) {
            if (entry.comp_2395().comp_349() != attribute.comp_349()) continue;
            switch (entry.comp_2396().comp_2450()) {
                case field_6328: {
                    value += entry.comp_2396().comp_2449();
                    break;
                }
                case field_6330: {
                    value += entry.comp_2396().comp_2449();
                    break;
                }
                case field_6331: {
                    value += entry.comp_2396().comp_2449();
                }
            }
        }
        return value;
    }

    private static double armorValue(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return 0.0;
        }
        class_1792 item = stack.method_7909();
        if (item instanceof class_1738) {
            class_1738 armorItem = (class_1738)item;
            return armorItem.method_7687();
        }
        return TroopStatResolver.attributeValue(stack, (class_6880<class_1320>)class_5134.field_23724);
    }

    private static class_1799 stackFor(class_2960 itemId) {
        if (itemId == null) {
            return class_1799.field_8037;
        }
        class_1792 item = (class_1792)class_7923.field_41178.method_10223(itemId);
        return item == null || item == class_1802.field_8162 ? class_1799.field_8037 : new class_1799((class_1935)item);
    }
}

