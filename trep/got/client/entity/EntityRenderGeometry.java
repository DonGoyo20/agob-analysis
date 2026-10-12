/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_4587
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_4588
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_4587;
import net.minecraft.class_4588;

@Environment(value=EnvType.CLIENT)
public final class EntityRenderGeometry {
    private EntityRenderGeometry() {
    }

    public static void emitQuad(class_4588 consumer, class_4587 matrices, int light, int overlay, float normalX, float normalY, float normalZ, float x0, float y0, float z0, float u0, float v0, float x1, float y1, float z1, float u1, float v1, float x2, float y2, float z2, float u2, float v2, float x3, float y3, float z3, float u3, float v3) {
        class_4587.class_4665 entry = matrices.method_23760();
        EntityRenderGeometry.emitVertex(consumer, entry, light, overlay, normalX, normalY, normalZ, x0, y0, z0, u0, v0);
        EntityRenderGeometry.emitVertex(consumer, entry, light, overlay, normalX, normalY, normalZ, x1, y1, z1, u1, v1);
        EntityRenderGeometry.emitVertex(consumer, entry, light, overlay, normalX, normalY, normalZ, x2, y2, z2, u2, v2);
        EntityRenderGeometry.emitVertex(consumer, entry, light, overlay, normalX, normalY, normalZ, x3, y3, z3, u3, v3);
    }

    private static void emitVertex(class_4588 consumer, class_4587.class_4665 entry, int light, int overlay, float normalX, float normalY, float normalZ, float x, float y, float z, float u, float v) {
        consumer.method_56824(entry, x, y, z).method_1336(255, 255, 255, 255).method_22913(u, v).method_22922(overlay).method_60803(light).method_60831(entry, normalX, normalY, normalZ);
    }
}

