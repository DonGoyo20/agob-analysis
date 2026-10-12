/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1322
 *  net.minecraft.class_1322$class_1323
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1799
 *  net.minecraft.class_5134
 *  net.minecraft.class_9274
 *  net.minecraft.class_9285
 */
package trep.got.item;

import net.minecraft.class_1322;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_5134;
import net.minecraft.class_9274;
import net.minecraft.class_9285;

public final class BlacksmithHammerItem
extends class_1792 {
    private static final double ATTACK_DAMAGE = 4.0;
    private static final double ATTACK_SPEED = -3.5;

    public BlacksmithHammerItem(class_1792.class_1793 settings) {
        super(settings);
    }

    public static class_9285 createAttributeModifiers() {
        return class_9285.method_57480().method_57487(class_5134.field_23721, new class_1322(field_8006, 4.0, class_1322.class_1323.field_6328), class_9274.field_49217).method_57487(class_5134.field_23723, new class_1322(field_8001, -3.5, class_1322.class_1323.field_6328), class_9274.field_49217).method_57486();
    }

    public boolean method_7870(class_1799 stack) {
        return true;
    }

    public int method_7837() {
        return 10;
    }
}

