/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1309
 *  net.minecraft.class_5603
 *  net.minecraft.class_5605
 *  net.minecraft.class_5606
 *  net.minecraft.class_5607
 *  net.minecraft.class_5609
 *  net.minecraft.class_5610
 *  net.minecraft.class_572
 *  net.minecraft.class_630
 */
package trep.got.client.render.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_572;
import net.minecraft.class_630;

@Environment(value=EnvType.CLIENT)
public class TullyArmorModelLayer1
extends class_572<class_1309> {
    public TullyArmorModelLayer1(class_630 root) {
        super(root);
    }

    public static class_5607 getTexturedModelData() {
        class_5609 modelData = new class_5609();
        class_5610 root = modelData.method_32111();
        root.method_32117("head", class_5606.method_32108().method_32101(0, 0).method_32098(-4.0f, -7.0f, -4.0f, 8.0f, 8.0f, 8.0f, new class_5605(0.75f)).method_32101(32, 0).method_32098(-4.0f, -7.0f, -4.0f, 8.0f, 8.0f, 8.0f, new class_5605(1.0f)), class_5603.field_27701);
        root.method_32117("hat", class_5606.method_32108(), class_5603.field_27701);
        root.method_32117("body", class_5606.method_32108().method_32101(16, 16).method_32098(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, new class_5605(0.75f)), class_5603.field_27701);
        root.method_32117("right_arm", class_5606.method_32108().method_32101(40, 16).method_32098(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.75f)), class_5603.method_32090((float)-5.0f, (float)2.0f, (float)0.0f));
        root.method_32117("left_arm", class_5606.method_32108().method_32101(40, 16).method_32096().method_32098(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.75f)).method_32106(false), class_5603.method_32090((float)5.0f, (float)2.0f, (float)0.0f));
        root.method_32117("right_leg", class_5606.method_32108().method_32101(0, 16).method_32098(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.75f)), class_5603.method_32090((float)-1.9f, (float)12.0f, (float)0.0f));
        root.method_32117("left_leg", class_5606.method_32108().method_32101(0, 16).method_32096().method_32098(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, new class_5605(0.75f)).method_32106(false), class_5603.method_32090((float)1.9f, (float)12.0f, (float)0.0f));
        return class_5607.method_32110((class_5609)modelData, (int)64, (int)32);
    }
}

