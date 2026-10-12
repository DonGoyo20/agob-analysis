/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1059
 *  net.minecraft.class_1297
 *  net.minecraft.class_2680
 *  net.minecraft.class_2960
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4608
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_776
 *  net.minecraft.class_7833
 *  net.minecraft.class_897
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1059;
import net.minecraft.class_1297;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5617;
import net.minecraft.class_776;
import net.minecraft.class_7833;
import net.minecraft.class_897;
import trep.got.entity.CatapultMunitionEntity;

@Environment(value=EnvType.CLIENT)
public final class CatapultMunitionEntityRenderer
extends class_897<CatapultMunitionEntity> {
    private final class_776 blockRenderManager;

    public CatapultMunitionEntityRenderer(class_5617.class_5618 context) {
        super(context);
        this.blockRenderManager = context.method_43337();
        this.field_4673 = 0.5f;
    }

    public void render(CatapultMunitionEntity entity, float yaw, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light) {
        class_2680 blockState = entity.getMunitionBlock().method_9564();
        matrices.method_22903();
        matrices.method_22907(class_7833.field_40716.rotationDegrees(class_3532.method_17821((float)tickDelta, (float)entity.field_5982, (float)entity.method_36454())));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(-class_3532.method_16439((float)tickDelta, (float)entity.field_6004, (float)entity.method_36455())));
        matrices.method_22907(class_7833.field_40718.rotationDegrees(((float)entity.field_6012 + tickDelta) * 18.0f));
        matrices.method_22905(0.375f, 0.375f, 0.375f);
        matrices.method_22904(-0.5, -0.5, -0.5);
        this.blockRenderManager.method_3353(blockState, matrices, vertexConsumers, light, class_4608.field_21444);
        matrices.method_22909();
        super.method_3936((class_1297)entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    public class_2960 getTexture(CatapultMunitionEntity entity) {
        return class_1059.field_5275;
    }
}

