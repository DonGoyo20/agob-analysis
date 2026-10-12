/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_2960
 */
package trep.got.combat;

import net.minecraft.class_1309;
import net.minecraft.class_2960;
import trep.got.combat.HumanoidCombatAnimationProfile;
import trep.got.combat.HumanoidCombatAnimations;

public final class BetterCombatAnimationResolver {
    private static final class_2960[] SWORD_ANIMATIONS = new class_2960[]{class_2960.method_60655((String)"bettercombat", (String)"one_handed_slash_horizontal_right"), class_2960.method_60655((String)"bettercombat", (String)"one_handed_slash_horizontal_left"), class_2960.method_60655((String)"bettercombat", (String)"one_handed_stab")};
    private static final class_2960 SPEAR_ANIMATION = class_2960.method_60655((String)"bettercombat", (String)"two_handed_stab_right");
    private static final class_2960 LANCE_ANIMATION = class_2960.method_60655((String)"bettercombat", (String)"one_handed_stab_mounted");
    private static final class_2960 HAMMER_ANIMATION = class_2960.method_60655((String)"bettercombat", (String)"two_handed_slam");

    private BetterCombatAnimationResolver() {
    }

    public static class_2960 resolve(class_1309 entity, int comboIndex) {
        HumanoidCombatAnimationProfile profile = HumanoidCombatAnimations.resolve(entity);
        return switch (profile) {
            case HumanoidCombatAnimationProfile.SWORD -> SWORD_ANIMATIONS[Math.floorMod(comboIndex, SWORD_ANIMATIONS.length)];
            case HumanoidCombatAnimationProfile.SPEAR -> SPEAR_ANIMATION;
            case HumanoidCombatAnimationProfile.LANCE -> LANCE_ANIMATION;
            case HumanoidCombatAnimationProfile.HAMMER -> HAMMER_ANIMATION;
            default -> null;
        };
    }
}

