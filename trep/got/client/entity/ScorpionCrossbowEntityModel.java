/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
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
import trep.got.entity.ScorpionCrossbowEntity;

@Environment(value=EnvType.CLIENT)
public final class ScorpionCrossbowEntityModel
extends class_583<ScorpionCrossbowEntity> {
    private final class_630 base;
    private final class_630 crossbow;
    private final class_630 reload;
    private final class_630 string;

    public ScorpionCrossbowEntityModel(class_630 root) {
        this.base = root.method_32086("base");
        this.crossbow = this.base.method_32086("crossbow");
        this.reload = this.crossbow.method_32086("reload");
        this.string = this.crossbow.method_32086("string");
        this.string.field_3665 = false;
    }

    public static class_5607 getTexturedModelData() {
        class_5609 modelData = new class_5609();
        class_5610 modelPartData = modelData.method_32111();
        class_5610 base = modelPartData.method_32117("base", class_5606.method_32108().method_32101(0, 0).method_32098(-10.0f, -2.0f, -13.0f, 20.0f, 2.0f, 4.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-10.0f, -2.0f, 9.0f, 20.0f, 2.0f, 4.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-9.0f, -4.0f, -9.0f, 18.0f, 2.0f, 18.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-5.0f, -7.0f, -5.0f, 10.0f, 3.0f, 10.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(9.0f, -2.0f, -9.0f, 4.0f, 2.0f, 18.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-13.0f, -2.0f, -9.0f, 4.0f, 2.0f, 18.0f, new class_5605(0.0f)), class_5603.method_32090((float)0.0f, (float)24.0f, (float)0.0f));
        class_5610 crossbow = base.method_32117("crossbow", class_5606.method_32108().method_32101(0, 0).method_32098(-3.0f, -14.0f, -3.0f, 6.0f, 19.0f, 6.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(3.0f, -17.0f, -11.0f, 3.0f, 4.0f, 13.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-6.0f, -17.0f, -11.0f, 3.0f, 4.0f, 13.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(3.5f, -16.0f, -3.0f, 2.0f, 4.0f, 2.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-5.5f, -16.0f, -3.0f, 2.0f, 4.0f, 2.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(5.0f, -18.5f, -15.0f, 9.5f, 2.5f, 3.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-14.5f, -18.5f, -15.0f, 9.5f, 2.5f, 3.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(10.0f, -21.0f, -15.0f, 5.5f, 2.5f, 3.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-15.5f, -21.0f, -15.0f, 5.5f, 2.5f, 3.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-3.0f, -18.0f, -16.0f, 6.0f, 4.0f, 20.0f, new class_5605(0.0f)), class_5603.method_32090((float)0.0f, (float)-6.0f, (float)0.0f));
        crossbow.method_32117("reload", class_5606.method_32108().method_32101(0, 0).method_32098(-12.0f, -1.0f, -2.0f, 24.0f, 2.0f, 3.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(12.0f, -1.5f, -1.5f, 2.0f, 3.0f, 3.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-14.0f, -1.5f, -1.5f, 2.0f, 3.0f, 3.0f, new class_5605(0.0f)), class_5603.method_32090((float)0.0f, (float)-12.0f, (float)0.0f));
        crossbow.method_32117("string", class_5606.method_32108().method_32101(0, 61).method_32098(-14.0f, -2.5f, -0.4f, 28.0f, 3.0f, 0.0f, new class_5605(0.0f)), class_5603.method_32090((float)0.0f, (float)-18.0f, (float)-13.0f));
        crossbow.method_32117("projectile", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)-18.0f, (float)-14.0f));
        crossbow.method_32117("projectile_end_pos", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)-18.0f, (float)-4.0f));
        class_5610 chair = crossbow.method_32117("chair", class_5606.method_32108().method_32101(0, 0).method_32098(-6.0f, -13.0f, 2.0f, 12.0f, 2.0f, 6.0f, new class_5605(0.0f)).method_32101(0, 56).method_32098(-5.0f, -14.0f, 3.0f, 10.0f, 1.0f, 7.0f, new class_5605(0.0f)).method_32101(0, 54).method_32098(-5.0f, -22.0f, 10.0f, 10.0f, 9.0f, 1.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-6.0f, -23.0f, 11.0f, 12.0f, 10.0f, 3.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-6.0f, -13.0f, 8.0f, 12.0f, 2.0f, 6.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-2.0f, -11.0f, 1.0f, 4.0f, 9.0f, 6.0f, new class_5605(0.0f)), class_5603.method_32090((float)0.0f, (float)0.0f, (float)2.0f));
        chair.method_32117("seat", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)-10.0f, (float)7.0f));
        return class_5607.method_32110((class_5609)modelData, (int)64, (int)64);
    }

    public void setAngles(ScorpionCrossbowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void setAim(float relativeYawDegrees, float pitchDegrees, float shootRecoilDegrees) {
        this.crossbow.field_3675 = relativeYawDegrees * ((float)Math.PI / 180);
        this.crossbow.field_3654 = (pitchDegrees + shootRecoilDegrees) * ((float)Math.PI / 180);
    }

    public void setReloadSpin(float reloadSpinDegrees) {
        this.reload.field_3654 = reloadSpinDegrees * ((float)Math.PI / 180);
    }

    public void applyCrossbowTransform(class_4587 matrices) {
        this.base.method_22703(matrices);
        this.crossbow.method_22703(matrices);
    }

    public void applyStringTransform(class_4587 matrices) {
        this.applyCrossbowTransform(matrices);
        this.string.method_22703(matrices);
    }

    public void method_2828(class_4587 matrices, class_4588 vertices, int light, int overlay, int color) {
        this.base.method_22699(matrices, vertices, light, overlay, color);
    }
}

