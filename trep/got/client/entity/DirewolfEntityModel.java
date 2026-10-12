/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_5597
 *  net.minecraft.class_5603
 *  net.minecraft.class_5605
 *  net.minecraft.class_5606
 *  net.minecraft.class_5607
 *  net.minecraft.class_5609
 *  net.minecraft.class_5610
 *  net.minecraft.class_630
 *  net.minecraft.class_7184
 *  net.minecraft.class_7187
 *  org.joml.Vector3f
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_5597;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_630;
import net.minecraft.class_7184;
import net.minecraft.class_7187;
import org.joml.Vector3f;
import trep.got.client.entity.DirewolfAnimations;
import trep.got.entity.DirewolfEntity;

@Environment(value=EnvType.CLIENT)
public class DirewolfEntityModel
extends class_5597<DirewolfEntity> {
    private static final Vector3f ANIMATION_VECTOR = new Vector3f();
    private final class_630 root;
    private final class_630 body;
    private final class_630 head;

    public DirewolfEntityModel(class_630 root) {
        this.root = root;
        this.body = root.method_32086("body");
        class_630 torso = this.body.method_32086("torso");
        this.head = torso.method_32086("head");
    }

    public static class_5607 getTexturedModelData() {
        class_5609 modelData = new class_5609();
        class_5610 modelPartData = modelData.method_32111();
        class_5610 body = modelPartData.method_32117("body", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)27.0f, (float)-2.0f));
        class_5610 backlegRight = body.method_32117("backleg_right", class_5606.method_32108().method_32101(0, 33).method_32098(-1.5f, -0.25f, -3.0f, 3.0f, 5.0f, 5.0f, new class_5605(0.0f)), class_5603.method_32091((float)-3.5f, (float)-12.75f, (float)7.0f, (float)0.3927f, (float)0.0f, (float)0.0f));
        class_5610 bone4 = backlegRight.method_32117("bone4", class_5606.method_32108().method_32101(0, 0).method_32098(-1.0f, 0.4489f, -1.1592f, 2.0f, 4.0f, 3.0f, new class_5605(0.0f)), class_5603.method_32090((float)0.0f, (float)4.3011f, (float)0.1592f));
        class_5610 bone7 = bone4.method_32117("bone7", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)4.4489f, (float)0.3408f));
        bone7.method_32117("leg3_r1", class_5606.method_32108().method_32101(2, 1).method_32098(0.0f, -2.0f, -0.5f, 1.0f, 3.0f, 2.0f, new class_5605(0.0f)), class_5603.method_32091((float)-0.5f, (float)0.0f, (float)-2.0f, (float)-0.7854f, (float)0.0f, (float)0.0f));
        bone7.method_32117("leg4_r1", class_5606.method_32108().method_32101(0, 14).method_32098(-1.0f, -0.1585f, -3.7304f, 2.0f, 1.0f, 4.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.0f, (float)1.0f, (float)-1.0f, (float)-0.3927f, (float)0.0f, (float)0.0f));
        class_5610 backlegLeft = body.method_32117("backleg_left", class_5606.method_32108().method_32101(0, 33).method_32098(-1.5f, -0.25f, -3.0f, 3.0f, 5.0f, 5.0f, new class_5605(0.0f)), class_5603.method_32091((float)2.5f, (float)-12.75f, (float)7.0f, (float)0.3927f, (float)0.0f, (float)0.0f));
        class_5610 bone3 = backlegLeft.method_32117("bone3", class_5606.method_32108().method_32101(0, 0).method_32098(-1.0f, 0.4489f, -1.1592f, 2.0f, 4.0f, 3.0f, new class_5605(0.0f)), class_5603.method_32090((float)0.0f, (float)4.3011f, (float)0.1592f));
        class_5610 bone8 = bone3.method_32117("bone8", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)4.4489f, (float)0.3408f));
        bone8.method_32117("leg3_r2", class_5606.method_32108().method_32101(2, 1).method_32098(0.0f, -2.0f, -0.5f, 1.0f, 3.0f, 2.0f, new class_5605(0.0f)), class_5603.method_32091((float)-0.5f, (float)0.0f, (float)-2.0f, (float)-0.7854f, (float)0.0f, (float)0.0f));
        bone8.method_32117("leg4_r2", class_5606.method_32108().method_32101(0, 14).method_32098(-1.0f, -0.1585f, -3.7304f, 2.0f, 1.0f, 4.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.0f, (float)1.0f, (float)-1.0f, (float)-0.3927f, (float)0.0f, (float)0.0f));
        class_5610 frontlegLeft = body.method_32117("frontleg_left", class_5606.method_32108().method_32101(0, 33).method_32098(-1.5f, 0.0f, -2.75f, 3.0f, 5.0f, 5.0f, new class_5605(0.0f)), class_5603.method_32091((float)3.0f, (float)-13.0f, (float)-4.75f, (float)0.3927f, (float)0.0f, (float)0.0f));
        class_5610 bone2 = frontlegLeft.method_32117("bone2", class_5606.method_32108().method_32101(42, 22).method_32098(-1.0f, -2.0511f, -1.8259f, 2.0f, 7.0f, 3.0f, new class_5605(0.0f)), class_5603.method_32090((float)0.0f, (float)4.0511f, (float)0.5759f));
        class_5610 bone6 = bone2.method_32117("bone6", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)4.9489f, (float)0.6741f));
        bone6.method_32117("leg3_r3", class_5606.method_32108().method_32101(2, 1).method_32098(0.0f, -2.0f, -0.5f, 1.0f, 3.0f, 2.0f, new class_5605(0.0f)), class_5603.method_32091((float)-0.5f, (float)0.0f, (float)-2.0f, (float)-0.7854f, (float)0.0f, (float)0.0f));
        bone6.method_32117("leg4_r3", class_5606.method_32108().method_32101(0, 14).method_32098(-1.0f, -0.1585f, -3.7304f, 2.0f, 1.0f, 4.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.0f, (float)1.0f, (float)-1.0f, (float)-0.3927f, (float)0.0f, (float)0.0f));
        class_5610 frontlegRight = body.method_32117("frontleg_right", class_5606.method_32108().method_32101(0, 33).method_32096().method_32098(-2.0f, 0.0f, -2.75f, 3.0f, 5.0f, 5.0f, new class_5605(0.0f)).method_32106(false), class_5603.method_32091((float)-3.5f, (float)-13.0f, (float)-4.75f, (float)0.3927f, (float)0.0f, (float)0.0f));
        class_5610 bone = frontlegRight.method_32117("bone", class_5606.method_32108().method_32101(42, 22).method_32098(-1.0f, -2.0511f, -1.8259f, 2.0f, 7.0f, 3.0f, new class_5605(0.0f)), class_5603.method_32090((float)-0.5f, (float)4.0511f, (float)0.5759f));
        class_5610 bone5 = bone.method_32117("bone5", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)4.9489f, (float)0.6741f));
        bone5.method_32117("leg3_r4", class_5606.method_32108().method_32101(2, 1).method_32098(0.0f, -2.0f, -0.5f, 1.0f, 3.0f, 2.0f, new class_5605(0.0f)), class_5603.method_32091((float)-0.5f, (float)0.0f, (float)-2.0f, (float)-0.7854f, (float)0.0f, (float)0.0f));
        bone5.method_32117("leg4_r4", class_5606.method_32108().method_32101(0, 14).method_32098(-1.0f, 0.0f, -1.5f, 2.0f, 1.0f, 4.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.0f, (float)0.0f, (float)-3.0f, (float)-0.3927f, (float)0.0f, (float)0.0f));
        class_5610 torso = body.method_32117("torso", class_5606.method_32108().method_32101(0, 0).method_32098(-3.5f, -4.7449f, -8.4479f, 7.0f, 8.0f, 11.0f, new class_5605(0.0f)).method_32101(0, 19).method_32098(-3.5f, -4.7551f, -14.4521f, 7.0f, 8.0f, 6.0f, new class_5605(0.0f)), class_5603.method_32090((float)-0.5f, (float)-10.7449f, (float)7.0521f));
        class_5610 tail = torso.method_32117("tail", class_5606.method_32108(), class_5603.method_32090((float)0.5f, (float)-4.3632f, (float)2.2187f));
        tail.method_32117("tail_r1", class_5606.method_32108().method_32101(20, 19).method_32098(-2.0f, -14.4023f, 6.7183f, 3.0f, 4.0f, 2.0f, new class_5605(0.0f)).method_32101(20, 22).method_32098(-2.0f, -10.4023f, 6.7183f, 3.0f, 1.0f, 2.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.0f, (float)17.0f, (float)-2.0f, (float)0.3491f, (float)0.0f, (float)0.0f));
        class_5610 head = torso.method_32117("head", class_5606.method_32108(), class_5603.method_32090((float)0.0591f, (float)-4.7553f, (float)-13.9729f));
        head.method_32117("head_r1", class_5606.method_32108().method_32101(34, 3).method_32098(0.5f, -23.0f, -13.0f, 2.0f, 4.0f, 3.0f, new class_5605(0.0f)).method_32101(34, 3).method_32096().method_32098(-3.5f, -23.0f, -13.0f, 2.0f, 4.0f, 3.0f, new class_5605(0.0f)).method_32106(false).method_32101(30, 3).method_32098(-1.5f, -23.0f, -13.0f, 2.0f, 2.0f, 3.0f, new class_5605(0.0f)), class_5603.method_32091((float)0.4409f, (float)19.1278f, (float)11.0379f, (float)0.1745f, (float)0.0f, (float)0.0f));
        head.method_32117("head_r2", class_5606.method_32108().method_32101(30, 22).method_32098(-1.5f, -1.0f, -0.5f, 3.0f, 2.0f, 1.0f, new class_5605(0.0f)), class_5603.method_32091((float)1.4409f, (float)-2.7146f, (float)-3.1229f, (float)0.1745f, (float)0.0f, (float)1.5708f));
        head.method_32117("head_r3", class_5606.method_32108().method_32101(11, 33).method_32098(-1.5f, -1.0f, -0.5f, 3.0f, 2.0f, 1.0f, new class_5605(0.0f)), class_5603.method_32091((float)-1.5591f, (float)-2.7146f, (float)-3.1229f, (float)0.1745f, (float)0.0f, (float)-1.5708f));
        head.method_32117("head_r4", class_5606.method_32108().method_32101(41, 6).method_32098(-0.5f, -22.0f, -16.0f, 3.0f, 3.0f, 4.0f, new class_5605(0.0f)), class_5603.method_32091((float)-1.0591f, (float)20.1278f, (float)11.0379f, (float)0.1745f, (float)0.0f, (float)0.0f));
        head.method_32117("neck_r1", class_5606.method_32108().method_32101(31, 14).method_32098(-2.5f, -1.5f, -2.5f, 5.0f, 3.0f, 5.0f, new class_5605(0.0f)), class_5603.method_32091((float)-0.0591f, (float)1.5273f, (float)-1.6479f, (float)1.5708f, (float)0.0f, (float)0.0f));
        body.method_32117("seat", class_5606.method_32108(), class_5603.method_32090((float)0.0f, (float)-15.0f, (float)1.0f));
        return class_5607.method_32110((class_5609)modelData, (int)64, (int)64);
    }

    public class_630 method_32008() {
        return this.root;
    }

    public void setAngles(DirewolfEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root.method_32088().forEach(class_630::method_41923);
        if (entity.method_6172()) {
            this.method_52185(DirewolfAnimations.SIT);
        } else if (limbDistance > 0.01f) {
            this.method_48741(DirewolfAnimations.WALK, limbAngle, limbDistance, 1.0f, 1.0f);
        } else {
            class_7187.method_41827((class_5597)this, (class_7184)DirewolfAnimations.IDLE, (long)((long)(animationProgress * 50.0f)), (float)1.0f, (Vector3f)ANIMATION_VECTOR);
        }
        this.head.field_3675 += headYaw * ((float)Math.PI / 180);
        this.head.field_3654 += headPitch * ((float)Math.PI / 180);
    }
}

