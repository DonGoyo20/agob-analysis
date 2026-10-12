/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1297
 *  net.minecraft.class_1921
 *  net.minecraft.class_2350
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4608
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_7833
 *  net.minecraft.class_897
 *  org.joml.Vector3f
 */
package trep.got.client.entity;

import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1921;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5617;
import net.minecraft.class_7833;
import net.minecraft.class_897;
import org.joml.Vector3f;
import trep.got.client.entity.EntityRenderGeometry;
import trep.got.client.entity.VesselTextureManager;
import trep.got.entity.ShipEntity;
import trep.got.entity.ShipModelData;
import trep.got.entity.VesselMaterialProfile;
import trep.got.entity.VesselVariant;

@Environment(value=EnvType.CLIENT)
public final class ShipEntityRenderer
extends class_897<ShipEntity> {
    private static final float SAIL_BEND_RENDER_SCALE = 0.5f;

    public ShipEntityRenderer(class_5617.class_5618 context) {
        super(context);
        this.field_4673 = 4.6f;
    }

    public static void initialize() {
        for (VesselVariant variant : VesselVariant.values()) {
            ShipModelData.get(variant);
        }
    }

    public void render(ShipEntity entity, float yaw, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light) {
        matrices.method_22903();
        float renderYaw = class_3532.method_17821((float)tickDelta, (float)entity.field_5982, (float)entity.method_36454());
        float wobbleTicks = (float)entity.method_54295() - tickDelta;
        float wobbleStrength = Math.max(entity.method_54294() - tickDelta, 0.0f);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(180.0f - renderYaw));
        if (wobbleTicks > 0.0f) {
            matrices.method_22907(class_7833.field_40714.rotationDegrees(class_3532.method_15374((float)wobbleTicks) * wobbleTicks * wobbleStrength / 10.0f * (float)entity.method_54296()));
        }
        matrices.method_46416(0.0f, (float)entity.getModelYOffset(), 0.0f);
        matrices.method_22905(2.0f, 2.0f, 2.0f);
        ShipModelData model = ShipModelData.get(entity.getVesselVariant());
        class_243 pivot = model.pivot();
        matrices.method_46416((float)(-pivot.field_1352), (float)(-pivot.field_1351), (float)(-pivot.field_1350));
        class_4588 consumer = vertexConsumers.getBuffer(class_1921.method_23578((class_2960)this.getTexture(entity)));
        float sailBend = class_3532.method_16439((float)tickDelta, (float)(entity.field_5982 == entity.method_36454() ? entity.getSailBend() : entity.getSailBend()), (float)entity.getSailBend());
        ShipEntityRenderer.renderGroup(model.root(), matrices, consumer, light, class_4608.field_21444, new Vector3f(0.0f, 0.0f, 0.0f), model, sailBend);
        matrices.method_22909();
        super.method_3936((class_1297)entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    private static void renderGroup(ShipModelData.GroupNode group, class_4587 matrices, class_4588 consumer, int light, int overlay, Vector3f parentOrigin, ShipModelData model, float sailBend) {
        matrices.method_22903();
        matrices.method_46416((group.origin().x() - parentOrigin.x()) / 16.0f, (group.origin().y() - parentOrigin.y()) / 16.0f, (group.origin().z() - parentOrigin.z()) / 16.0f);
        matrices.method_22907(class_7833.field_40714.rotationDegrees(group.rotation().x()));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(group.rotation().y()));
        matrices.method_22907(class_7833.field_40718.rotationDegrees(group.rotation().z()));
        for (ShipModelData.RenderNode child : group.children()) {
            if (child instanceof ShipModelData.GroupNode) {
                ShipModelData.GroupNode childGroup = (ShipModelData.GroupNode)child;
                ShipEntityRenderer.renderGroup(childGroup, matrices, consumer, light, overlay, group.origin(), model, sailBend);
                continue;
            }
            ShipEntityRenderer.renderCube((ShipModelData.CubeNode)child, matrices, consumer, light, overlay, group.origin(), model, sailBend);
        }
        matrices.method_22909();
    }

    private static void renderCube(ShipModelData.CubeNode cube, class_4587 matrices, class_4588 consumer, int light, int overlay, Vector3f parentOrigin, ShipModelData model, float sailBend) {
        boolean flatZ;
        matrices.method_22903();
        matrices.method_46416((cube.origin().x() - parentOrigin.x()) / 16.0f, (cube.origin().y() - parentOrigin.y()) / 16.0f, (cube.origin().z() - parentOrigin.z()) / 16.0f);
        matrices.method_22907(class_7833.field_40714.rotationDegrees(cube.rotation().x()));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(cube.rotation().y()));
        matrices.method_22907(class_7833.field_40718.rotationDegrees(cube.rotation().z()));
        float x0 = (cube.from().x() - cube.origin().x()) / 16.0f;
        float y0 = (cube.from().y() - cube.origin().y()) / 16.0f;
        float z0 = (cube.from().z() - cube.origin().z()) / 16.0f;
        float x1 = (cube.to().x() - cube.origin().x()) / 16.0f;
        float y1 = (cube.to().y() - cube.origin().y()) / 16.0f;
        float z1 = (cube.to().z() - cube.origin().z()) / 16.0f;
        boolean flatX = Math.abs(x1 - x0) < 1.0E-5f;
        boolean flatY = Math.abs(y1 - y0) < 1.0E-5f;
        boolean bl = flatZ = Math.abs(z1 - z0) < 1.0E-5f;
        if (ShipEntityRenderer.isSailCloth(cube.name(), flatZ)) {
            ShipEntityRenderer.renderSailCloth(cube, matrices, consumer, light, overlay, model, x0, y0, z0, x1, y1, sailBend);
            matrices.method_22909();
            return;
        }
        for (Map.Entry<class_2350, ShipModelData.FaceUv> entry : cube.faces().entrySet()) {
            ShipModelData.FaceUv uv = entry.getValue();
            float minU = uv.u0() / (float)model.textureWidth();
            float minV = uv.v0() / (float)model.textureHeight();
            float maxU = uv.u1() / (float)model.textureWidth();
            float maxV = uv.v1() / (float)model.textureHeight();
            switch (entry.getKey()) {
                case field_11043: {
                    if (flatZ) {
                        EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 0.0f, 0.0f, -1.0f, x1, y0, z0, maxU, maxV, x0, y0, z0, minU, maxV, x0, y1, z0, minU, minV, x1, y1, z0, maxU, minV);
                        break;
                    }
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 0.0f, 0.0f, -1.0f, x1, y0, z0, maxU, maxV, x0, y0, z0, minU, maxV, x0, y1, z0, minU, minV, x1, y1, z0, maxU, minV);
                    break;
                }
                case field_11035: {
                    if (flatZ) break;
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 0.0f, 0.0f, 1.0f, x0, y0, z1, minU, maxV, x1, y0, z1, maxU, maxV, x1, y1, z1, maxU, minV, x0, y1, z1, minU, minV);
                    break;
                }
                case field_11039: {
                    if (flatX) {
                        EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, -1.0f, 0.0f, 0.0f, x0, y0, z0, minU, maxV, x0, y0, z1, maxU, maxV, x0, y1, z1, maxU, minV, x0, y1, z0, minU, minV);
                        break;
                    }
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, -1.0f, 0.0f, 0.0f, x0, y0, z0, minU, maxV, x0, y0, z1, maxU, maxV, x0, y1, z1, maxU, minV, x0, y1, z0, minU, minV);
                    break;
                }
                case field_11034: {
                    if (flatX) break;
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 1.0f, 0.0f, 0.0f, x1, y0, z1, minU, maxV, x1, y0, z0, maxU, maxV, x1, y1, z0, maxU, minV, x1, y1, z1, minU, minV);
                    break;
                }
                case field_11036: {
                    if (flatY) {
                        EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 0.0f, 1.0f, 0.0f, x0, y1, z0, minU, minV, x1, y1, z0, maxU, minV, x1, y1, z1, maxU, maxV, x0, y1, z1, minU, maxV);
                        break;
                    }
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 0.0f, 1.0f, 0.0f, x0, y1, z0, minU, minV, x1, y1, z0, maxU, minV, x1, y1, z1, maxU, maxV, x0, y1, z1, minU, maxV);
                    break;
                }
                case field_11033: {
                    if (flatY) break;
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 0.0f, -1.0f, 0.0f, x0, y0, z1, minU, minV, x1, y0, z1, maxU, minV, x1, y0, z0, maxU, maxV, x0, y0, z0, minU, maxV);
                }
            }
        }
        matrices.method_22909();
    }

    private static void renderSailCloth(ShipModelData.CubeNode cube, class_4587 matrices, class_4588 consumer, int light, int overlay, ShipModelData model, float x0, float y0, float z0, float x1, float y1, float sailBend) {
        ShipModelData.FaceUv uv = cube.faces().get(class_2350.field_11043);
        if (uv == null) {
            return;
        }
        float minU = uv.u0() / (float)model.textureWidth();
        float minV = uv.v0() / (float)model.textureHeight();
        float maxU = uv.u1() / (float)model.textureWidth();
        float maxV = uv.v1() / (float)model.textureHeight();
        int strips = 8;
        for (int index = 0; index < strips; ++index) {
            float t0 = (float)index / (float)strips;
            float t1 = (float)(index + 1) / (float)strips;
            float rowY0 = class_3532.method_16439((float)t0, (float)y0, (float)y1);
            float rowY1 = class_3532.method_16439((float)t1, (float)y0, (float)y1);
            float bulge0 = -ShipEntityRenderer.sailCurve(t0) * sailBend * 0.5f;
            float bulge1 = -ShipEntityRenderer.sailCurve(t1) * sailBend * 0.5f;
            float rowV0 = class_3532.method_16439((float)t0, (float)maxV, (float)minV);
            float rowV1 = class_3532.method_16439((float)t1, (float)maxV, (float)minV);
            EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 0.0f, 0.0f, -1.0f, x1, rowY0, z0 + bulge0, maxU, rowV0, x0, rowY0, z0 + bulge0, minU, rowV0, x0, rowY1, z0 + bulge1, minU, rowV1, x1, rowY1, z0 + bulge1, maxU, rowV1);
        }
    }

    private static float sailCurve(float t) {
        float centered = t * 2.0f - 1.0f;
        return Math.max(0.0f, 1.0f - centered * centered);
    }

    private static boolean isSailCloth(String cubeName, boolean flatZ) {
        if (!flatZ) {
            return false;
        }
        String lower = cubeName.toLowerCase();
        return lower.equals("sail_upper") || lower.contains("sail");
    }

    public class_2960 getTexture(ShipEntity entity) {
        return VesselTextureManager.getTextureId(entity.getVesselVariant(), VesselMaterialProfile.fromEntity(entity));
    }
}

