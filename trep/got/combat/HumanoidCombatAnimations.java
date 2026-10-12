/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_1496
 *  net.minecraft.class_1743
 *  net.minecraft.class_1799
 *  net.minecraft.class_1829
 *  net.minecraft.class_9362
 */
package trep.got.combat;

import net.minecraft.class_1309;
import net.minecraft.class_1496;
import net.minecraft.class_1743;
import net.minecraft.class_1799;
import net.minecraft.class_1829;
import net.minecraft.class_9362;
import trep.got.combat.HumanoidCombatAnimationProfile;
import trep.got.item.BlacksmithHammerItem;
import trep.got.item.SpearItem;

public final class HumanoidCombatAnimations {
    private HumanoidCombatAnimations() {
    }

    public static HumanoidCombatAnimationProfile resolve(class_1309 entity) {
        if (entity == null) {
            return HumanoidCombatAnimationProfile.NONE;
        }
        class_1799 mainHand = entity.method_6047();
        if (mainHand.method_7960()) {
            return HumanoidCombatAnimationProfile.NONE;
        }
        if (mainHand.method_7909() instanceof SpearItem) {
            return entity.method_5854() instanceof class_1496 ? HumanoidCombatAnimationProfile.LANCE : HumanoidCombatAnimationProfile.SPEAR;
        }
        if (mainHand.method_7909() instanceof BlacksmithHammerItem || mainHand.method_7909() instanceof class_9362) {
            return HumanoidCombatAnimationProfile.HAMMER;
        }
        if (mainHand.method_7909() instanceof class_1829 || mainHand.method_7909() instanceof class_1743) {
            return HumanoidCombatAnimationProfile.SWORD;
        }
        return HumanoidCombatAnimationProfile.SWORD;
    }
}

