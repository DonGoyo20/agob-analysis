/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1309
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 */
package trep.got.client.spear;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import trep.got.item.SpearCombat;
import trep.got.item.SpearItem;

@Environment(value=EnvType.CLIENT)
public final class SpearClientPose {
    private SpearClientPose() {
    }

    public static boolean isLancePose(class_1309 entity, class_1799 stack) {
        SpearItem spear;
        class_1792 class_17922 = stack.method_7909();
        return class_17922 instanceof SpearItem && SpearCombat.isLanceActive(entity, spear = (SpearItem)class_17922);
    }

    public static boolean isBracePose(class_1309 entity, class_1799 stack) {
        return stack.method_7909() instanceof SpearItem && SpearCombat.isBraceActive(entity);
    }

    public static boolean isChargePose(class_1309 entity, class_1799 stack) {
        if (!(stack.method_7909() instanceof SpearItem) || entity == null) {
            return false;
        }
        class_1799 activeItem = entity.method_6030();
        return !activeItem.method_7960() && activeItem.method_7909() == stack.method_7909() && entity.method_6014() > 0;
    }

    public static SpearCombat.ChargeStage getChargeStage(class_1309 entity, SpearItem spear) {
        int elapsedUseTicks = spear.method_7881(entity.method_6030(), entity) - entity.method_6014();
        return SpearCombat.resolveChargeStage(spear, elapsedUseTicks);
    }

    public static void applyFirstPersonPose(class_4587 matrices, boolean leftHanded, boolean lance, boolean brace) {
        if (lance) {
            matrices.method_46416(0.0f, -0.03f, 0.18f);
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-15.0f));
            return;
        }
        if (brace) {
            matrices.method_46416(0.0f, 0.02f, 0.08f);
            matrices.method_22907(class_7833.field_40714.rotationDegrees(15.0f));
        }
    }

    public static void applyFirstPersonChargePose(class_4587 matrices, boolean leftHanded, SpearCombat.ChargeStage stage) {
        float handSign = leftHanded ? -1.0f : 1.0f;
        switch (stage) {
            case ENGAGED: {
                matrices.method_46416(handSign * 0.08f, -0.18f, 0.42f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(handSign * 7.0f));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-32.0f));
                break;
            }
            case TIRED: {
                matrices.method_46416(handSign * 0.12f, -0.1f, 0.34f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(handSign * 22.0f));
                matrices.method_22907(class_7833.field_40718.rotationDegrees(handSign * 12.0f));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-14.0f));
                break;
            }
            case DISENGAGED: {
                matrices.method_46416(handSign * 0.18f, -0.52f, 0.16f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(handSign * 28.0f));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(18.0f));
            }
        }
    }

    public static void applyThirdPersonPose(class_4587 matrices, boolean leftHanded, boolean lance, boolean brace) {
        if (lance) {
            matrices.method_46416(0.0f, 0.0f, 0.16f);
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-15.0f));
            return;
        }
        if (brace) {
            matrices.method_46416(0.0f, 0.03f, 0.06f);
            matrices.method_22907(class_7833.field_40714.rotationDegrees(15.0f));
        }
    }

    public static void applyThirdPersonChargePose(class_4587 matrices, boolean leftHanded, SpearCombat.ChargeStage stage) {
        float handSign = leftHanded ? -1.0f : 1.0f;
        switch (stage) {
            case ENGAGED: {
                matrices.method_46416(handSign * 0.04f, -0.08f, 0.18f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(handSign * 10.0f));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-24.0f));
                break;
            }
            case TIRED: {
                matrices.method_46416(handSign * 0.08f, -0.03f, 0.12f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(handSign * 22.0f));
                matrices.method_22907(class_7833.field_40718.rotationDegrees(handSign * 10.0f));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-8.0f));
                break;
            }
            case DISENGAGED: {
                matrices.method_46416(handSign * 0.1f, -0.18f, 0.04f);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(handSign * 26.0f));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(18.0f));
            }
        }
    }
}

