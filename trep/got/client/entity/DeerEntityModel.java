/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_5603
 *  net.minecraft.class_5605
 *  net.minecraft.class_5606
 *  net.minecraft.class_5607
 *  net.minecraft.class_5609
 *  net.minecraft.class_5610
 *  net.minecraft.class_583
 *  net.minecraft.class_630
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_583;
import net.minecraft.class_630;
import trep.got.entity.DeerEntity;

@Environment(value=EnvType.CLIENT)
public final class DeerEntityModel
extends class_583<DeerEntity> {
    private static final float HEAD_PIVOT_Y = -4.7553f;
    private static final float HEAD_PIVOT_Z = -13.9729f;
    private final class_630 body;
    private final class_630 backlegRight;
    private final class_630 backlegLeft;
    private final class_630 frontlegLeft;
    private final class_630 frontlegRight;
    private final class_630 head;
    private final class_630 horns;
    private float headPitchModifier;
    private float headNeckOffset;
    private float headbuttProgress;

    public DeerEntityModel(class_630 root) {
        this.body = root.method_32086("body");
        this.backlegRight = this.body.method_32086("backleg_right");
        this.backlegLeft = this.body.method_32086("backleg_left");
        this.frontlegLeft = this.body.method_32086("frontleg_left");
        this.frontlegRight = this.body.method_32086("frontleg_right");
        class_630 torso = this.body.method_32086("torso");
        this.head = torso.method_32086("head");
        this.horns = this.head.method_32086("horns");
    }

    public static class_5607 getTexturedModelData() {
        class_5609 modelData = new class_5609();
        class_5610 modelPartData = modelData.method_32111();
        class_5610 body = modelPartData.method_32117("body", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)25.0f, (float)-2.0f));
        body.method_32117("backleg_right", class_5606.method_32108().method_32101(0, 33).method_32098(-1.5f, -0.25f, -3.0f, 3.0f, 5.0f, 5.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-1.0f, 4.75f, -1.0f, 2.0f, 6.0f, 3.0f, new class_5605(0.0f)), class_5603.method_32090((float)-3.0f, (float)-11.75f, (float)7.0f));
        body.method_32117("backleg_left", class_5606.method_32108().method_32101(0, 33).method_32098(-1.5f, -0.25f, -3.0f, 3.0f, 5.0f, 5.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-1.0f, 4.75f, -1.0f, 2.0f, 6.0f, 3.0f, new class_5605(0.0f)), class_5603.method_32090((float)2.0f, (float)-11.75f, (float)7.0f));
        body.method_32117("frontleg_left", class_5606.method_32108().method_32101(0, 33).method_32098(-1.5f, 0.0f, -2.75f, 3.0f, 5.0f, 5.0f, new class_5605(0.0f)).method_32101(42, 22).method_32098(-1.0f, 3.0f, -1.25f, 2.0f, 9.0f, 3.0f, new class_5605(0.0f)), class_5603.method_32090((float)3.0f, (float)-13.0f, (float)-4.75f));
        body.method_32117("frontleg_right", class_5606.method_32108().method_32101(0, 33).method_32096().method_32098(-1.0f, 0.0f, -2.75f, 3.0f, 5.0f, 5.0f, new class_5605(0.0f)).method_32106(false).method_32101(42, 22).method_32098(-1.5f, 3.0f, -1.25f, 2.0f, 9.0f, 3.0f, new class_5605(0.0f)), class_5603.method_32090((float)-3.5f, (float)-13.0f, (float)-4.75f));
        class_5610 torso = body.method_32117("torso", class_5606.method_32108().method_32101(0, 19).method_32098(-3.5f, -6.2551f, -14.0521f, 7.0f, 8.0f, 6.0f, new class_5605(0.0f)), class_5603.method_32090((float)-0.5f, (float)-10.7449f, (float)7.0521f));
        torso.method_32117("body_r1", class_5606.method_32108().method_32101(0, 0).method_32098(-4.0f, -16.8f, -2.5f, 7.0f, 8.0f, 11.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.5f, (float)10.7449f, (float)-7.0521f, (float)-0.0873f, (float)0.0f, (float)0.0f));
        class_5610 tail = torso.method_32117("tail", class_5606.method_32108(), class_5603.method_32090((float)0.5f, (float)-4.3632f, (float)2.2187f));
        tail.method_32117("tail_r1", class_5606.method_32108().method_32101(20, 19).method_32098(-2.0f, -14.4023f, 6.7183f, 3.0f, 4.0f, 2.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.0f, (float)16.0f, (float)-2.0f, (float)0.3491f, (float)0.0f, (float)0.0f));
        class_5610 head = torso.method_32117("head", class_5606.method_32108(), class_5603.method_32090((float)0.0591f, (float)-4.7553f, (float)-13.9729f));
        head.method_32117("cape_r1", class_5606.method_32108().method_32101(26, 26).method_32098(-2.5f, -20.0f, -3.0f, 4.0f, 8.0f, 4.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.4409f, (float)14.6781f, (float)2.9806f, (float)0.2618f, (float)0.0f, (float)0.0f));
        head.method_32117("ear2_r1", class_5606.method_32108().method_32101(11, 33).method_32098(-1.5f, -1.0f, -0.5f, 3.0f, 2.0f, 1.0f, new class_5605(0.0f)), class_5603.method_32091((float)-2.9902f, (float)-7.9829f, (float)-4.0152f, (float)0.1933f, (float)-0.5823f, (float)0.2909f));
        head.method_32117("ear1_r1", class_5606.method_32108().method_32101(11, 33).method_32098(-1.5f, -1.0f, -0.5f, 3.0f, 2.0f, 1.0f, new class_5605(0.0f)), class_5603.method_32091((float)2.872f, (float)-7.9829f, (float)-4.0152f, (float)0.1933f, (float)0.5823f, (float)-0.2909f));
        head.method_32117("noose_r1", class_5606.method_32108().method_32101(41, 6).method_32098(-2.0f, -22.0f, -19.0f, 3.0f, 3.0f, 4.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.4409f, (float)9.442f, (float)14.2086f, (float)0.3054f, (float)0.0f, (float)0.0f));
        head.method_32117("head_r1", class_5606.method_32108().method_32101(25, 0).method_32098(-2.5f, -23.0f, -16.0f, 4.0f, 4.0f, 6.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.4409f, (float)12.1278f, (float)12.0378f, (float)0.1745f, (float)0.0f, (float)0.0f));
        head.method_32117("neck_r1", class_5606.method_32108().method_32101(16, 34).method_32098(-2.0f, -20.0f, -3.0f, 3.0f, 6.0f, 4.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.4409f, (float)11.5003f, (float)6.9208f, (float)0.48f, (float)0.0f, (float)0.0f));
        head.method_32117("neck_r2", class_5606.method_32108().method_32101(31, 14).method_32098(-3.0f, -14.0f, 3.0f, 5.0f, 3.0f, 5.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.4409f, (float)11.5003f, (float)6.9208f, (float)0.9163f, (float)0.0f, (float)0.0f));
        class_5610 horns = head.method_32117("horns", class_5606.method_32108(), class_5603.method_32090((float)14.449f, (float)12.1252f, (float)-6.0019f));
        horns.method_32117("horn_r1", class_5606.method_32108().method_32101(0, 43).method_32098(-3.0879f, -6.6247f, -2.163f, 5.0f, 8.0f, 1.0f, new class_5605(0.0f)), class_5603.method_32091((float)-13.2031f, (float)-20.3641f, (float)4.498f, (float)-0.602f, (float)-0.0073f, (float)0.6913f));
        horns.method_32117("horn2_r1", class_5606.method_32108().method_32101(11, 44).method_32098(-4.0f, -31.0f, -12.0f, 5.0f, 8.0f, 1.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.0f, (float)0.0f, (float)0.0f, (float)-0.5591f, (float)0.1059f, (float)-0.6471f));
        body.method_32117("seat", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)-15.0f, (float)1.0f));
        return class_5607.method_32110((class_5609)modelData, (int)64, (int)64);
    }

    public void animateModel(DeerEntity entity, float limbAngle, float limbDistance, float tickDelta) {
        this.headNeckOffset = entity.getNeckAngle(tickDelta);
        this.headPitchModifier = entity.getHeadAngle(tickDelta);
        this.headbuttProgress = entity.getHeadbuttAnimationProgress(tickDelta);
    }

    public void setAngles(DeerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float headbutt = class_3532.method_15374((float)(this.headbuttProgress * (float)Math.PI));
        this.head.field_3656 = -4.7553f + this.headNeckOffset * 6.0f;
        this.head.field_3655 = -13.9729f - headbutt * 2.0f;
        this.head.field_3675 = headYaw * ((float)Math.PI / 180);
        this.head.field_3654 = (this.headNeckOffset > 0.0f ? this.headPitchModifier : headPitch * ((float)Math.PI / 180)) + headbutt * 0.7f;
        this.horns.field_3665 = !entity.isSheared() && !entity.method_6109();
        float stride = limbAngle * 0.6662f;
        this.backlegRight.field_3654 = class_3532.method_15362((float)stride) * 1.4f * limbDistance;
        this.backlegLeft.field_3654 = class_3532.method_15362((float)(stride + (float)Math.PI)) * 1.4f * limbDistance;
        this.frontlegRight.field_3654 = class_3532.method_15362((float)(stride + (float)Math.PI)) * 1.4f * limbDistance;
        this.frontlegLeft.field_3654 = class_3532.method_15362((float)stride) * 1.4f * limbDistance;
    }

    public void method_2828(class_4587 matrices, class_4588 vertices, int light, int overlay, int color) {
        if (this.field_3448) {
            matrices.method_22903();
            matrices.method_22905(0.5f, 0.5f, 0.5f);
            matrices.method_46416(0.0f, 1.5f, 0.0f);
            this.body.method_22699(matrices, vertices, light, overlay, color);
            matrices.method_22909();
            return;
        }
        this.body.method_22699(matrices, vertices, light, overlay, color);
    }
}

