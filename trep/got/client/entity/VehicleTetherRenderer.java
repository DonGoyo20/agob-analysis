/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1921
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1921;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;

@Environment(value=EnvType.CLIENT)
final class VehicleTetherRenderer {
    private static final int SEGMENTS = 24;

    private VehicleTetherRenderer() {
    }

    static void render(class_4587 matrices, class_4597 vertexConsumers, double startX, double startY, double startZ, double endX, double endY, double endZ) {
        class_4588 consumer = vertexConsumers.getBuffer(class_1921.method_23594());
        class_4587.class_4665 matrixEntry = matrices.method_23760();
        for (int index = 0; index < 24; ++index) {
            float t0 = (float)index / 24.0f;
            float t1 = (float)(index + 1) / 24.0f;
            int shade = (index & 1) == 0 ? 96 : 70;
            VehicleTetherRenderer.emitPoint(consumer, matrixEntry, startX, startY, startZ, endX, endY, endZ, t0, shade);
            VehicleTetherRenderer.emitPoint(consumer, matrixEntry, startX, startY, startZ, endX, endY, endZ, t1, shade);
        }
    }

    private static void emitPoint(class_4588 consumer, class_4587.class_4665 matrices, double startX, double startY, double startZ, double endX, double endY, double endZ, float progress, int shade) {
        double sag = Math.sin(Math.PI * (double)progress) * 0.08;
        consumer.method_56824(matrices, (float)class_3532.method_16436((double)progress, (double)startX, (double)endX), (float)(class_3532.method_16436((double)progress, (double)startY, (double)endY) - sag), (float)class_3532.method_16436((double)progress, (double)startZ, (double)endZ)).method_1336(shade, shade - 16, shade - 34, 255).method_60831(matrices, 0.0f, 1.0f, 0.0f);
    }
}

