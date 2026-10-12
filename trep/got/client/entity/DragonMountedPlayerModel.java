/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1664
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_5603
 *  net.minecraft.class_5605
 *  net.minecraft.class_5606
 *  net.minecraft.class_5607
 *  net.minecraft.class_5609
 *  net.minecraft.class_5610
 *  net.minecraft.class_572
 *  net.minecraft.class_583
 *  net.minecraft.class_591
 *  net.minecraft.class_630
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1664;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_572;
import net.minecraft.class_583;
import net.minecraft.class_591;
import net.minecraft.class_630;

@Environment(value=EnvType.CLIENT)
public final class DragonMountedPlayerModel
extends class_583<class_1309> {
    private final class_630 player;
    private final class_630 waist;
    private final class_630 head;
    private final class_630 body;
    private final class_630 rightArm;
    private final class_630 leftArm;
    private final class_630 rightLeg;
    private final class_630 leftLeg;

    public DragonMountedPlayerModel(class_630 root) {
        this.player = root.method_32086("player");
        this.waist = this.player.method_32086("Waist2");
        this.head = this.waist.method_32086("Head3");
        this.body = this.waist.method_32086("Body3");
        this.rightArm = this.waist.method_32086("Right_Arm2");
        this.leftArm = this.waist.method_32086("Left_Arm2");
        this.rightLeg = this.player.method_32086("Right_Leg2");
        this.leftLeg = this.player.method_32086("Left_Leg2");
    }

    public static class_5607 getTexturedModelData() {
        class_5609 modelData = new class_5609();
        class_5610 root = modelData.method_32111();
        class_5610 player = root.method_32117("player", class_5606.method_32108(), class_5603.method_32091((float)0.0f, (float)-9.0f, (float)9.0f, (float)1.5708f, (float)0.0f, (float)0.0f));
        class_5610 waist = player.method_32117("Waist2", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)0.0f, (float)0.0f));
        waist.method_32117("Head3", class_5606.method_32108().method_32101(0, 0).method_32098(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, new class_5605(0.0f)).method_32101(32, 0).method_32098(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, new class_5605(0.5f)), class_5603.method_32091((float)0.0f, (float)-12.0f, (float)0.0f, (float)-1.1345f, (float)0.0f, (float)0.0f));
        waist.method_32117("Body3", class_5606.method_32108().method_32101(16, 16).method_32098(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, new class_5605(0.0f)).method_32101(16, 32).method_32098(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, new class_5605(0.25f)), class_5603.method_32090((float)0.0f, (float)-12.0f, (float)0.0f));
        waist.method_32117("Right_Arm2", class_5606.method_32108().method_32101(40, 16).method_32098(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.0f)).method_32101(40, 32).method_32098(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.25f)), class_5603.method_32091((float)-5.0f, (float)-10.0f, (float)0.0f, (float)-2.9234f, (float)0.0f, (float)0.0f));
        waist.method_32117("Left_Arm2", class_5606.method_32108().method_32101(32, 48).method_32098(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.0f)).method_32101(48, 48).method_32098(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.25f)), class_5603.method_32091((float)5.0f, (float)-10.0f, (float)0.0f, (float)-2.9234f, (float)0.0f, (float)0.0f));
        player.method_32117("Right_Leg2", class_5606.method_32108().method_32101(0, 16).method_32098(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.0f)).method_32101(0, 32).method_32098(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.25f)), class_5603.method_32090((float)-1.9f, (float)0.0f, (float)0.0f));
        player.method_32117("Left_Leg2", class_5606.method_32108().method_32101(16, 48).method_32098(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.0f)).method_32101(0, 48).method_32098(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.25f)), class_5603.method_32091((float)1.9f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)-0.0349f));
        return class_5607.method_32110((class_5609)modelData, (int)64, (int)64);
    }

    public void setAngles(class_1309 entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    public void applyRootTransform(class_4587 matrices) {
        this.player.method_22703(matrices);
    }

    public void applyTo(class_591<?> model, class_1309 rider) {
        model.method_2805(true);
        model.field_3398.method_17138(this.head);
        model.field_3391.method_17138(this.body);
        model.field_3401.method_17138(this.rightArm);
        model.field_27433.method_17138(this.leftArm);
        model.field_3392.method_17138(this.rightLeg);
        model.field_3397.method_17138(this.leftLeg);
        model.field_3394.method_17138(model.field_3398);
        model.field_3483.method_17138(model.field_3391);
        model.field_3486.method_17138(model.field_3401);
        model.field_3484.method_17138(model.field_27433);
        model.field_3479.method_17138(model.field_3392);
        model.field_3482.method_17138(model.field_3397);
        if (rider instanceof class_1657) {
            class_1657 playerEntity = (class_1657)rider;
            model.field_3394.field_3665 = playerEntity.method_7348(class_1664.field_7563);
            model.field_3483.field_3665 = playerEntity.method_7348(class_1664.field_7564);
            model.field_3486.field_3665 = playerEntity.method_7348(class_1664.field_7570);
            model.field_3484.field_3665 = playerEntity.method_7348(class_1664.field_7568);
            model.field_3479.field_3665 = playerEntity.method_7348(class_1664.field_7565);
            model.field_3482.field_3665 = playerEntity.method_7348(class_1664.field_7566);
        } else {
            model.field_3394.field_3665 = false;
            model.field_3483.field_3665 = false;
            model.field_3486.field_3665 = false;
            model.field_3484.field_3665 = false;
            model.field_3479.field_3665 = false;
            model.field_3482.field_3665 = false;
        }
    }

    public void applyTo(class_572<?> model) {
        model.method_2805(true);
        model.field_3398.method_17138(this.head);
        model.field_3391.method_17138(this.body);
        model.field_3401.method_17138(this.rightArm);
        model.field_27433.method_17138(this.leftArm);
        model.field_3392.method_17138(this.rightLeg);
        model.field_3397.method_17138(this.leftLeg);
        if (model.field_3394 != null) {
            model.field_3394.method_17138(model.field_3398);
        }
    }

    public void method_2828(class_4587 matrices, class_4588 vertices, int light, int overlay, int color) {
        this.player.method_22699(matrices, vertices, light, overlay, color);
    }
}

