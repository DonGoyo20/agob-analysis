/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1665
 *  net.minecraft.class_1667
 *  net.minecraft.class_1921
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
 *  net.minecraft.class_954
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1665;
import net.minecraft.class_1667;
import net.minecraft.class_1921;
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
import net.minecraft.class_954;
import trep.got.client.entity.ScorpionCrossbowEntityModel;
import trep.got.client.physics.ClientPhysicsRegistry;
import trep.got.client.physics.ScorpionCrossbowStringState;
import trep.got.entity.ScorpionCrossbowEntity;

@Environment(value=EnvType.CLIENT)
public final class ScorpionCrossbowEntityRenderer
extends class_897<ScorpionCrossbowEntity> {
    private static final class_5601 MODEL_LAYER = new class_5601(class_2960.method_60655((String)"agob", (String)"scorpion_crossbow"), "main");
    private static final class_2960 TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/entity/scorpion_crossbow.png");
    private static class_1667 displayArrow;
    private final ScorpionCrossbowEntityModel model;
    private final class_954 arrowRenderer;

    public ScorpionCrossbowEntityRenderer(class_5617.class_5618 context) {
        super(context);
        this.model = new ScorpionCrossbowEntityModel(context.method_32167(MODEL_LAYER));
        this.arrowRenderer = new class_954(context);
        this.field_4673 = 1.2f;
    }

    public static void initialize() {
        EntityModelLayerRegistry.registerModelLayer((class_5601)MODEL_LAYER, ScorpionCrossbowEntityModel::getTexturedModelData);
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            displayArrow = null;
        });
    }

    public void render(ScorpionCrossbowEntity entity, float yaw, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light) {
        float baseYaw = class_3532.method_17821((float)tickDelta, (float)entity.field_5982, (float)entity.method_36454());
        float aimYaw = entity.getAimYaw(tickDelta);
        float relativeYaw = aimYaw + 180.0f - baseYaw;
        float aimPitch = entity.getAimPitch(tickDelta);
        float shootRecoil = ScorpionCrossbowEntityRenderer.getShootRecoilDegrees(entity.getShootAnimationProgress(tickDelta));
        ScorpionCrossbowStringState stringState = ClientPhysicsRegistry.tickScorpionCrossbowString(entity);
        float stringProgress = stringState.drawProgress(tickDelta);
        float reloadSpin = entity.isReloading() ? -360.0f * stringProgress : 0.0f;
        matrices.method_22903();
        matrices.method_22904(0.0, 1.5, 0.0);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(180.0f - baseYaw));
        matrices.method_22905(1.0f, -1.0f, -1.0f);
        this.model.setAim(relativeYaw, aimPitch, shootRecoil);
        this.model.setReloadSpin(reloadSpin);
        class_4588 consumer = vertexConsumers.getBuffer(this.model.method_23500(TEXTURE));
        this.model.method_2828(matrices, consumer, light, class_4608.field_21444, -1);
        this.renderDrawnString(stringProgress, matrices, vertexConsumers, light);
        if (entity.isLoaded() || entity.isReloading()) {
            this.renderLoadedArrow(entity, matrices, vertexConsumers, light);
        }
        matrices.method_22909();
        super.method_3936((class_1297)entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    private void renderDrawnString(float drawProgress, class_4587 matrices, class_4597 vertexConsumers, int light) {
        float centerZ = ScorpionCrossbowEntityRenderer.smooth(drawProgress) * 9.0f;
        matrices.method_22903();
        this.model.applyStringTransform(matrices);
        class_4588 consumer = vertexConsumers.getBuffer(class_1921.method_23578((class_2960)TEXTURE));
        ScorpionCrossbowEntityRenderer.drawStringPlane(consumer, matrices, centerZ, light);
        matrices.method_22909();
    }

    private void renderLoadedArrow(ScorpionCrossbowEntity entity, class_4587 matrices, class_4597 vertexConsumers, int light) {
        if (displayArrow == null || displayArrow.method_37908() != entity.method_37908()) {
            displayArrow = new class_1667(class_1299.field_6122, entity.method_37908());
        }
        class_1667 arrow = displayArrow;
        arrow.method_36456(180.0f);
        arrow.field_5982 = 180.0f;
        arrow.method_36457(0.0f);
        arrow.field_6004 = 0.0f;
        matrices.method_22903();
        this.model.applyCrossbowTransform(matrices);
        matrices.method_22904(0.0, -1.125, -0.875);
        this.arrowRenderer.method_3875((class_1665)arrow, 180.0f, 0.0f, matrices, vertexConsumers, light);
        matrices.method_22909();
    }

    private static void drawStringPlane(class_4588 consumer, class_4587 matrices, float centerZ, int light) {
        float leftX = -14.0f;
        float centerX = 0.0f;
        float rightX = 14.0f;
        float topY = -2.5f;
        float bottomY = 0.5f;
        float endpointZ = -0.4f;
        float uLeft = 0.0f;
        float uCenter = 0.21875f;
        float uRight = 0.4375f;
        float vTop = 0.953125f;
        float vBottom = 1.0f;
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, leftX, topY, endpointZ, uLeft, vTop, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, leftX, bottomY, endpointZ, uLeft, vBottom, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, centerX, bottomY, centerZ, uCenter, vBottom, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, centerX, topY, centerZ, uCenter, vTop, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, centerX, topY, centerZ, uCenter, vTop, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, centerX, bottomY, centerZ, uCenter, vBottom, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, rightX, bottomY, endpointZ, uRight, vBottom, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, rightX, topY, endpointZ, uRight, vTop, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, centerX, topY, centerZ, uCenter, vTop, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, centerX, bottomY, centerZ, uCenter, vBottom, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, leftX, bottomY, endpointZ, uLeft, vBottom, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, leftX, topY, endpointZ, uLeft, vTop, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, rightX, topY, endpointZ, uRight, vTop, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, rightX, bottomY, endpointZ, uRight, vBottom, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, centerX, bottomY, centerZ, uCenter, vBottom, light);
        ScorpionCrossbowEntityRenderer.vertex(consumer, matrices, centerX, topY, centerZ, uCenter, vTop, light);
    }

    private static void vertex(class_4588 consumer, class_4587 matrices, float x, float y, float z, float u, float v, int light) {
        consumer.method_56824(matrices.method_23760(), x / 16.0f, y / 16.0f, z / 16.0f).method_1336(255, 255, 255, 255).method_22913(u, v).method_22922(class_4608.field_21444).method_60803(light).method_60831(matrices.method_23760(), 0.0f, 1.0f, 0.0f);
    }

    private static float smooth(float value) {
        return value * value * (3.0f - 2.0f * value);
    }

    private static float getShootRecoilDegrees(float progress) {
        if (progress <= 0.0f) {
            return 0.0f;
        }
        float peak = 0.1666f;
        if (progress <= peak) {
            return class_3532.method_16439((float)(progress / peak), (float)0.0f, (float)-8.0f);
        }
        return class_3532.method_16439((float)((progress - peak) / (1.0f - peak)), (float)-8.0f, (float)0.0f);
    }

    public class_2960 getTexture(ScorpionCrossbowEntity entity) {
        return TEXTURE;
    }
}

