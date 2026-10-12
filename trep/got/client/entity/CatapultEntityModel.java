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
import trep.got.entity.CatapultEntity;

@Environment(value=EnvType.CLIENT)
public final class CatapultEntityModel
extends class_583<CatapultEntity> {
    private final class_630 drawbar;
    private final class_630 cart;
    private final class_630 beam;
    private final class_630 beamSurface;
    private final class_630 wheelsFront;
    private final class_630 wheelsBack;

    public CatapultEntityModel(class_630 root) {
        this.drawbar = root.method_32086("drawbar");
        this.cart = this.drawbar.method_32086("cart");
        this.beam = this.cart.method_32086("beam");
        this.beamSurface = this.beam.method_32086("beam_surface");
        class_630 wheels = this.cart.method_32086("wheels");
        this.wheelsFront = wheels.method_32086("wheels_front");
        this.wheelsBack = wheels.method_32086("wheels_back");
    }

    public static class_5607 getTexturedModelData() {
        class_5609 modelData = new class_5609();
        class_5610 root = modelData.method_32111();
        class_5610 drawbar = root.method_32117("drawbar", class_5606.method_32108().method_32101(32, 0).method_32098(-2.0f, -1.0f, -11.0f, 4.0f, 2.0f, 12.0f, new class_5605(0.0f)).method_32101(32, 0).method_32098(-2.0f, -3.0f, -19.0f, 4.0f, 2.0f, 12.0f, new class_5605(0.0f)), class_5603.method_32090((float)0.0f, (float)10.0f, (float)-17.0f));
        drawbar.method_32117("drawbar_r1", class_5606.method_32108().method_32101(32, 0).method_32098(-2.0f, -1.0f, -6.0f, 4.0f, 2.0f, 12.0f, new class_5605(0.0f)), class_5603.method_32091((float)-6.0f, (float)-2.0f, (float)-21.0f, (float)0.0f, (float)1.5708f, (float)0.0f));
        drawbar.method_32117("drawbar_r2", class_5606.method_32108().method_32101(32, 0).method_32098(-2.0f, -1.0f, -6.0f, 4.0f, 2.0f, 12.0f, new class_5605(0.0f)), class_5603.method_32091((float)6.0f, (float)-2.0f, (float)-21.0f, (float)0.0f, (float)1.5708f, (float)0.0f));
        drawbar.method_32117("horse_top_1", class_5606.method_32108(), class_5603.method_32090((float)-11.0f, (float)-2.0f, (float)-21.0f));
        drawbar.method_32117("horse_top_2", class_5606.method_32108(), class_5603.method_32090((float)11.0f, (float)-2.0f, (float)-21.0f));
        class_5610 cart = drawbar.method_32117("cart", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)14.0f, (float)15.0f));
        class_5610 beam = cart.method_32117("beam", class_5606.method_32108().method_32101(0, 41).method_32098(-2.0f, -1.0f, 0.0f, 4.0f, 1.0f, 22.0f, new class_5605(0.0f)).method_32101(12, 53).method_32098(-3.0f, -3.0f, 22.0f, 6.0f, 1.0f, 1.0f, new class_5605(0.0f)).method_32101(12, 48).method_32098(3.0f, -3.0f, 16.0f, 1.0f, 1.0f, 6.0f, new class_5605(0.0f)).method_32101(12, 53).method_32098(-3.0f, -3.0f, 15.0f, 6.0f, 1.0f, 1.0f, new class_5605(0.0f)).method_32101(12, 48).method_32098(-4.0f, -3.0f, 16.0f, 1.0f, 1.0f, 6.0f, new class_5605(0.0f)), class_5603.method_32090((float)0.0f, (float)-15.0f, (float)2.0f));
        beam.method_32117("beam_surface", class_5606.method_32108().method_32101(7, 48).method_32098(-19.0f, -19.0f, 37.0f, 6.0f, 1.0f, 6.0f, new class_5605(0.0f)), class_5603.method_32090((float)16.0f, (float)17.0f, (float)-21.0f));
        cart.method_32117("seat_driver", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)-10.0f, (float)-8.0f));
        cart.method_32117("seat_player", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)-10.0f, (float)8.0f));
        cart.method_32117("body", class_5606.method_32108().method_32101(0, 30).method_32098(-12.0f, -12.0f, -1.0f, 24.0f, 2.0f, 32.0f, new class_5605(0.0f)).method_32101(21, 19).method_32096().method_32098(10.0f, -20.0f, 14.0f, 2.0f, 8.0f, 2.0f, new class_5605(0.0f)).method_32106(false).method_32101(21, 19).method_32098(-12.0f, -20.0f, 14.0f, 2.0f, 8.0f, 2.0f, new class_5605(0.0f)).method_32101(20, 54).method_32098(-10.0f, -20.0f, 14.0f, 20.0f, 2.0f, 2.0f, new class_5605(0.0f)).method_32101(21, 19).method_32096().method_32098(-12.0f, -20.0f, -1.0f, 2.0f, 8.0f, 2.0f, new class_5605(0.0f)).method_32106(false).method_32101(0, 0).method_32098(-10.0f, -20.0f, -1.0f, 20.0f, 8.0f, 2.0f, new class_5605(0.0f)).method_32101(21, 19).method_32098(10.0f, -20.0f, -1.0f, 2.0f, 8.0f, 2.0f, new class_5605(0.0f)).method_32101(0, 33).method_32098(6.0f, -10.0f, 1.0f, 3.0f, 3.0f, 28.0f, new class_5605(0.0f)).method_32101(0, 33).method_32098(-9.0f, -10.0f, 1.0f, 3.0f, 3.0f, 28.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-14.0f, -7.0f, 2.0f, 28.0f, 2.0f, 2.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-14.0f, -7.0f, 26.0f, 28.0f, 2.0f, 2.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-6.0f, -10.0f, 7.0f, 12.0f, 2.0f, 2.0f, new class_5605(0.0f)).method_32101(0, 0).method_32098(-6.0f, -10.0f, 21.0f, 12.0f, 2.0f, 2.0f, new class_5605(0.0f)), class_5603.method_32090((float)0.0f, (float)2.0f, (float)-13.0f));
        class_5610 wheels = cart.method_32117("wheels", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)-2.0f, (float)2.0f));
        wheels.method_32117("wheels_front", class_5606.method_32108().method_32101(0, 16).method_32098(-17.0f, -4.33f, -4.0f, 2.0f, 8.0f, 8.0f, new class_5605(0.0f)).method_32101(0, 16).method_32098(13.0f, -4.33f, -4.0f, 2.0f, 8.0f, 8.0f, new class_5605(0.0f)), class_5603.method_32090((float)1.0f, (float)-1.5f, (float)-12.0f));
        wheels.method_32117("wheels_back", class_5606.method_32108().method_32101(0, 16).method_32098(-17.0f, -4.33f, -4.0f, 2.0f, 8.0f, 8.0f, new class_5605(0.0f)).method_32101(0, 16).method_32098(13.0f, -4.33f, -4.0f, 2.0f, 8.0f, 8.0f, new class_5605(0.0f)), class_5603.method_32090((float)1.0f, (float)-1.5f, (float)12.0f));
        return class_5607.method_32110((class_5609)modelData, (int)64, (int)64);
    }

    public void setAngles(CatapultEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void setWheelRotation(float wheelRotation) {
        this.wheelsFront.field_3654 = wheelRotation;
        this.wheelsBack.field_3654 = wheelRotation;
    }

    public void setBeamPitch(float beamPitch) {
        this.beam.field_3654 = beamPitch;
    }

    public void applyBeamSurfaceTransform(class_4587 matrices) {
        this.drawbar.method_22703(matrices);
        this.cart.method_22703(matrices);
        this.beam.method_22703(matrices);
        this.beamSurface.method_22703(matrices);
    }

    public void method_2828(class_4587 matrices, class_4588 vertices, int light, int overlay, int color) {
        this.drawbar.method_22699(matrices, vertices, light, overlay, color);
    }
}

