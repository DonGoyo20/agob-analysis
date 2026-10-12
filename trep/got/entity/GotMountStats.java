/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1320
 *  net.minecraft.class_1324
 *  net.minecraft.class_1496
 *  net.minecraft.class_3532
 *  net.minecraft.class_5134
 *  net.minecraft.class_6880
 */
package trep.got.entity;

import net.minecraft.class_1320;
import net.minecraft.class_1324;
import net.minecraft.class_1496;
import net.minecraft.class_3532;
import net.minecraft.class_5134;
import net.minecraft.class_6880;

final class GotMountStats {
    private GotMountStats() {
    }

    static void applyRandomizedStats(class_1496 mount, StatRange range) {
        GotMountStats.setAttribute(mount, (class_6880<class_1320>)class_5134.field_23719, GotMountStats.randomBetween(mount, range.minMovementSpeed(), range.maxMovementSpeed()));
        GotMountStats.setAttribute(mount, (class_6880<class_1320>)class_5134.field_23728, GotMountStats.randomBetween(mount, range.minJumpStrength(), range.maxJumpStrength()));
        GotMountStats.setAttribute(mount, (class_6880<class_1320>)class_5134.field_23716, GotMountStats.randomBetween(mount, range.minHealth(), range.maxHealth()));
        mount.method_6033(mount.method_6063());
    }

    static void applyBredStats(class_1496 child, class_1496 firstParent, class_1496 secondParent, StatRange range) {
        GotMountStats.setAttribute(child, (class_6880<class_1320>)class_5134.field_23719, GotMountStats.inheritAttribute(firstParent, secondParent, (class_6880<class_1320>)class_5134.field_23719, range.minMovementSpeed(), range.maxMovementSpeed()));
        GotMountStats.setAttribute(child, (class_6880<class_1320>)class_5134.field_23728, GotMountStats.inheritAttribute(firstParent, secondParent, (class_6880<class_1320>)class_5134.field_23728, range.minJumpStrength(), range.maxJumpStrength()));
        GotMountStats.setAttribute(child, (class_6880<class_1320>)class_5134.field_23716, GotMountStats.inheritAttribute(firstParent, secondParent, (class_6880<class_1320>)class_5134.field_23716, range.minHealth(), range.maxHealth()));
        child.method_6033(child.method_6063());
    }

    private static double inheritAttribute(class_1496 firstParent, class_1496 secondParent, class_6880<class_1320> attribute, double min, double max) {
        double average = (GotMountStats.getBaseValue(firstParent, attribute, min) + GotMountStats.getBaseValue(secondParent, attribute, min)) * 0.5;
        double variance = (max - min) * 0.08;
        double bredValue = average + (firstParent.method_59922().method_43058() * 2.0 - 1.0) * variance;
        return class_3532.method_15350((double)bredValue, (double)min, (double)max);
    }

    private static double getBaseValue(class_1496 horse, class_6880<class_1320> attribute, double fallback) {
        class_1324 instance = horse.method_5996(attribute);
        return instance == null ? fallback : instance.method_6201();
    }

    private static void setAttribute(class_1496 mount, class_6880<class_1320> attribute, double value) {
        class_1324 instance = mount.method_5996(attribute);
        if (instance != null) {
            instance.method_6192(value);
        }
    }

    private static double randomBetween(class_1496 mount, double min, double max) {
        return min + mount.method_59922().method_43058() * (max - min);
    }

    record StatRange(double minMovementSpeed, double maxMovementSpeed, double minJumpStrength, double maxJumpStrength, double minHealth, double maxHealth) {
    }
}

