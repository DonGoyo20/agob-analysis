/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1893
 *  net.minecraft.class_6880
 *  net.minecraft.class_9304
 *  net.minecraft.class_9334
 */
package trep.got.item;

import net.minecraft.class_1799;
import net.minecraft.class_1893;
import net.minecraft.class_6880;
import net.minecraft.class_9304;
import net.minecraft.class_9334;
import trep.got.registry.GotItems;

public final class BlacksmithHammerRules {
    private BlacksmithHammerRules() {
    }

    public static boolean isHammer(class_1799 stack) {
        return stack.method_31574(GotItems.BLACKSMITH_HAMMER);
    }

    public static boolean hasOnlyAllowedEnchantments(class_1799 stack) {
        return BlacksmithHammerRules.hasOnlyAllowedEnchantments((class_9304)stack.method_57825(class_9334.field_49633, (Object)class_9304.field_49385)) && BlacksmithHammerRules.hasOnlyAllowedEnchantments((class_9304)stack.method_57825(class_9334.field_49643, (Object)class_9304.field_49385));
    }

    private static boolean hasOnlyAllowedEnchantments(class_9304 component) {
        for (class_6880 enchantment : component.method_57534()) {
            if (enchantment.method_40225(class_1893.field_9119)) continue;
            return false;
        }
        return true;
    }
}

