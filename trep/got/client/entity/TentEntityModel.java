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
import trep.got.client.physics.ClientPhysicsRegistry;
import trep.got.client.physics.TentClothState;
import trep.got.entity.TentEntity;

@Environment(value=EnvType.CLIENT)
public final class TentEntityModel
extends class_583<TentEntity> {
    private static final float CLOTH_WIDTH = 2.0f;
    private static final float CLOTH_MIN_X = -1.0625f;
    private static final float CLOTH_MAX_X = 0.9375f;
    private static final float TENT_ANGLE = 0.3927f;
    private static final float CLOTH_RENDER_SCALE = 0.7f;
    private static final float TOP_CLAMP_Y = -3.40625f;
    private static final float TOP_CLAMP_Z_NORTH = 1.40625f;
    private static final float TOP_CLAMP_Z_SOUTH = 1.59375f;
    private final class_630 tent;
    private final class_630 woolTop;
    private final class_630 pillar;

    public TentEntityModel(class_630 root) {
        this.tent = root.method_32086("tent");
        this.woolTop = this.tent.method_32086("wool_top");
        this.pillar = this.tent.method_32086("pillar");
    }

    public static class_5607 getTexturedModelData() {
        class_5609 modelData = new class_5609();
        class_5610 root = modelData.method_32111();
        class_5610 tent = root.method_32117("tent", class_5606.method_32108(), class_5603.method_32090((float)9.0f, (float)24.0f, (float)-24.0f));
        tent.method_32117("wool_top", class_5606.method_32108().method_32101(-3, 9).method_32098(-17.0f, -54.5f, 22.5f, 32.0f, 0.0f, 3.0f, new class_5605(0.0f)), class_5603.field_27701);
        tent.method_32117("pillar", class_5606.method_32108().method_32101(0, 32).method_32098(-17.0f, 30.5f, -7.5f, 3.0f, 13.0f, 3.0f, new class_5605(0.0f)).method_32101(0, 32).method_32098(-17.0f, 17.5f, -7.5f, 3.0f, 13.0f, 3.0f, new class_5605(0.0f)).method_32101(12, 36).method_32098(-18.0f, 15.0f, -8.5f, 5.0f, 3.0f, 5.0f, new class_5605(0.0f)).method_32101(0, 32).method_32098(-17.0f, 2.0f, -7.5f, 3.0f, 13.0f, 3.0f, new class_5605(0.0f)).method_32101(0, 32).method_32098(-17.0f, -11.0f, -7.5f, 3.0f, 13.0f, 3.0f, new class_5605(0.0f)), class_5603.method_32091((float)15.0f, (float)-43.5f, (float)30.0f, (float)0.0f, (float)0.0f, (float)0.0f));
        return class_5607.method_32110((class_5609)modelData, (int)64, (int)64);
    }

    public void setAngles(TentEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void method_2828(class_4587 matrices, class_4588 vertices, int light, int overlay, int color) {
        throw new UnsupportedOperationException("Use renderCloth");
    }

    public void renderCloth(TentEntity entity, class_4587 matrices, class_4588 vertices, int light, int overlay, int color) {
        TentClothState clothState = ClientPhysicsRegistry.tickTentWalls(entity);
        matrices.method_22903();
        this.tent.method_22703(matrices);
        this.woolTop.method_22699(matrices, vertices, light, overlay, color);
        TentEntityModel.renderWallGroup(matrices, vertices, light, overlay, color, clothState.bend(), true);
        TentEntityModel.renderWallGroup(matrices, vertices, light, overlay, color, clothState.bend(), false);
        this.pillar.method_22699(matrices, vertices, light, overlay, color);
        matrices.method_22909();
    }

    private static void renderWallGroup(class_4587 matrices, class_4588 vertices, int light, int overlay, int color, float bend, boolean northSide) {
        float sag = bend * 0.7f / 16.0f;
        float bottomY = 0.0f;
        float bottomZ = northSide ? 0.0f : 3.0f;
        float topY = -3.40625f;
        float topZ = northSide ? 1.40625f : 1.59375f;
        float inwardSign = northSide ? 1.0f : -1.0f;
        for (int segment = 0; segment < 3; ++segment) {
            float segmentT0 = (float)segment / 3.0f;
            float segmentT1 = (float)(segment + 1) / 3.0f;
            int strips = 4;
            for (int strip = 0; strip < strips; ++strip) {
                float localT0 = (float)strip / (float)strips;
                float localT1 = (float)(strip + 1) / (float)strips;
                float t0 = class_3532.method_16439((float)localT0, (float)segmentT0, (float)segmentT1);
                float t1 = class_3532.method_16439((float)localT1, (float)segmentT0, (float)segmentT1);
                float y0 = class_3532.method_16439((float)t0, (float)bottomY, (float)topY);
                float y1 = class_3532.method_16439((float)t1, (float)bottomY, (float)topY);
                float z0 = class_3532.method_16439((float)t0, (float)bottomZ, (float)topZ) + inwardSign * TentEntityModel.wallCurve(t0) * sag;
                float z1 = class_3532.method_16439((float)t1, (float)bottomZ, (float)topZ) + inwardSign * TentEntityModel.wallCurve(t1) * sag;
                float v0 = class_3532.method_16439((float)localT0, (float)0.0f, (float)0.25f);
                float v1 = class_3532.method_16439((float)localT1, (float)0.0f, (float)0.25f);
                TentEntityModel.emitQuad(vertices, matrices, light, overlay, color, northSide ? 0.0f : 0.0f, 0.0f, northSide ? -1.0f : 1.0f, new Vertex(0.9375f, y0, z0, 0.5f, v0), new Vertex(-1.0625f, y0, z0, 0.0f, v0), new Vertex(-1.0625f, y1, z1, 0.0f, v1), new Vertex(0.9375f, y1, z1, 0.5f, v1));
            }
        }
    }

    private static float wallCurve(float t) {
        float centered = t * 2.0f - 1.0f;
        return Math.max(0.0f, 1.0f - centered * centered);
    }

    private static void emitQuad(class_4588 consumer, class_4587 matrices, int light, int overlay, int color, float normalX, float normalY, float normalZ, Vertex v0, Vertex v1, Vertex v2, Vertex v3) {
        TentEntityModel.emitVertex(consumer, matrices, light, overlay, color, normalX, normalY, normalZ, v0);
        TentEntityModel.emitVertex(consumer, matrices, light, overlay, color, normalX, normalY, normalZ, v1);
        TentEntityModel.emitVertex(consumer, matrices, light, overlay, color, normalX, normalY, normalZ, v2);
        TentEntityModel.emitVertex(consumer, matrices, light, overlay, color, normalX, normalY, normalZ, v3);
    }

    private static void emitVertex(class_4588 consumer, class_4587 matrices, int light, int overlay, int color, float normalX, float normalY, float normalZ, Vertex vertex) {
        consumer.method_56824(matrices.method_23760(), vertex.x(), vertex.y(), vertex.z()).method_39415(color).method_22913(vertex.u(), vertex.v()).method_22922(overlay).method_60803(light).method_60831(matrices.method_23760(), normalX, normalY, normalZ);
    }

    @Environment(value=EnvType.CLIENT)
    private record Vertex(float x, float y, float z, float u, float v) {
    }
}

