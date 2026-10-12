/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
 *  net.minecraft.class_1297
 *  net.minecraft.class_2960
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4608
 *  net.minecraft.class_5601
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_7833
 *  net.minecraft.class_897
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.class_1297;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5601;
import net.minecraft.class_5617;
import net.minecraft.class_7833;
import net.minecraft.class_897;
import trep.got.client.entity.TentEntityModel;
import trep.got.entity.TentEntity;

@Environment(value=EnvType.CLIENT)
public final class TentEntityRenderer
extends class_897<TentEntity> {
    private static final class_5601 MODEL_LAYER = new class_5601(class_2960.method_60655((String)"agob", (String)"tent"), "main");
    private static final class_2960 TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/block/tent.png");
    private final TentEntityModel model;

    public TentEntityRenderer(class_5617.class_5618 context) {
        super(context);
        this.model = new TentEntityModel(context.method_32167(MODEL_LAYER));
        this.field_4673 = 0.0f;
    }

    public static void initialize() {
        EntityModelLayerRegistry.registerModelLayer((class_5601)MODEL_LAYER, TentEntityModel::getTexturedModelData);
    }

    public void render(TentEntity entity, float yaw, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light) {
        matrices.method_22903();
        matrices.method_22904(0.0, 1.5, 0.0);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(180.0f - entity.method_58149().method_10144()));
        matrices.method_22904(-1.0, 0.0, -1.0);
        matrices.method_22905(1.0f, -1.0f, -1.0f);
        class_4588 consumer = vertexConsumers.getBuffer(this.model.method_23500(TEXTURE));
        this.model.renderCloth(entity, matrices, consumer, light, class_4608.field_21444, -1);
        matrices.method_22909();
        super.method_3936((class_1297)entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    public class_2960 getTexture(TentEntity entity) {
        return TEXTURE;
    }
}

