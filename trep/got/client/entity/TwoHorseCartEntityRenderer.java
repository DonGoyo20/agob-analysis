/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
 *  net.minecraft.class_1297
 *  net.minecraft.class_1496
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 *  net.minecraft.class_3532
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
import net.minecraft.class_1496;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5601;
import net.minecraft.class_5617;
import net.minecraft.class_7833;
import net.minecraft.class_897;
import trep.got.client.entity.TwoHorseCartEntityModel;
import trep.got.client.entity.VehicleTetherRenderer;
import trep.got.entity.TwoHorseCartEntity;

@Environment(value=EnvType.CLIENT)
public final class TwoHorseCartEntityRenderer
extends class_897<TwoHorseCartEntity> {
    private static final class_5601 MODEL_LAYER = new class_5601(class_2960.method_60655((String)"agob", (String)"cart_2horse"), "main");
    private static final class_2960 TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/entity/cart/wooden_cart.png");
    private final TwoHorseCartEntityModel model;

    public TwoHorseCartEntityRenderer(class_5617.class_5618 context) {
        super(context);
        this.model = new TwoHorseCartEntityModel(context.method_32167(MODEL_LAYER));
        this.field_4673 = 1.3f;
    }

    public static void initialize() {
        EntityModelLayerRegistry.registerModelLayer((class_5601)MODEL_LAYER, TwoHorseCartEntityModel::getTexturedModelData);
    }

    public void render(TwoHorseCartEntity entity, float yaw, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light) {
        matrices.method_22903();
        matrices.method_22904(0.0, 1.5, 0.0);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(180.0f - class_3532.method_17821((float)tickDelta, (float)entity.field_5982, (float)entity.method_36454())));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(class_3532.method_16439((float)tickDelta, (float)entity.field_6004, (float)entity.method_36455())));
        matrices.method_22905(1.0f, -1.0f, -1.0f);
        this.model.setWheelRotation(entity.getWheelRotation(tickDelta));
        class_4588 consumer = vertexConsumers.getBuffer(this.model.method_23500(TEXTURE));
        this.model.method_2828(matrices, consumer, light, class_4608.field_21444, -1);
        matrices.method_22909();
        this.renderDrawbarTether(entity, 0, tickDelta, matrices, vertexConsumers);
        this.renderDrawbarTether(entity, 1, tickDelta, matrices, vertexConsumers);
        super.method_3936((class_1297)entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    private void renderDrawbarTether(TwoHorseCartEntity cart, int slot, float tickDelta, class_4587 matrices, class_4597 vertexConsumers) {
        class_1496 horse = cart.getAttachedHorseForRendering(slot);
        if (horse == null) {
            return;
        }
        double cartX = class_3532.method_16436((double)tickDelta, (double)cart.field_6014, (double)cart.method_23317());
        double cartY = class_3532.method_16436((double)tickDelta, (double)cart.field_6036, (double)cart.method_23318());
        double cartZ = class_3532.method_16436((double)tickDelta, (double)cart.field_5969, (double)cart.method_23321());
        class_243 hitchPos = cart.getHorseHitchPos(slot, tickDelta);
        VehicleTetherRenderer.render(matrices, vertexConsumers, hitchPos.field_1352 - cartX, hitchPos.field_1351 - cartY, hitchPos.field_1350 - cartZ, class_3532.method_16436((double)tickDelta, (double)horse.field_6014, (double)horse.method_23317()) - cartX, class_3532.method_16436((double)tickDelta, (double)horse.field_6036, (double)horse.method_23318()) + (double)horse.method_17682() * 0.7 - cartY, class_3532.method_16436((double)tickDelta, (double)horse.field_5969, (double)horse.method_23321()) - cartZ);
    }

    public class_2960 getTexture(TwoHorseCartEntity entity) {
        return TEXTURE;
    }
}

