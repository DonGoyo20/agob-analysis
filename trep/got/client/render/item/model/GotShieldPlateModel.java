/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1058
 *  net.minecraft.class_1088
 *  net.minecraft.class_1767
 *  net.minecraft.class_4587
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4722
 *  net.minecraft.class_4730
 *  net.minecraft.class_5253$class_5254
 *  net.minecraft.class_6880
 *  net.minecraft.class_9307
 *  net.minecraft.class_9307$class_9308
 *  org.joml.Matrix4f
 */
package trep.got.client.render.item.model;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1058;
import net.minecraft.class_1088;
import net.minecraft.class_1767;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4722;
import net.minecraft.class_4730;
import net.minecraft.class_5253;
import net.minecraft.class_6880;
import net.minecraft.class_9307;
import org.joml.Matrix4f;

@Environment(value=EnvType.CLIENT)
public abstract class GotShieldPlateModel {
    private static final float MODEL_SCALE = 0.0625f;
    private static final float Z_OFFSET = 0.0624375f;

    public final void renderBanner(class_4587 matrices, class_4597 consumers, int light, int overlay, class_1767 baseColor, class_9307 patterns, boolean glint) {
        GotShieldPlateModel.renderLayer(this.getPlateQuads(), matrices, consumers, class_1088.field_21557, baseColor, light, overlay, glint);
        for (int i = 0; i < patterns.comp_2428().size() && i < 16; ++i) {
            class_9307.class_9308 layer = (class_9307.class_9308)patterns.comp_2428().get(i);
            GotShieldPlateModel.renderLayer(this.getPlateQuads(), matrices, consumers, class_4722.method_33083((class_6880)layer.comp_2429()), layer.comp_2430(), light, overlay, glint);
        }
    }

    protected abstract List<Quad> getPlateQuads();

    private static void renderLayer(List<Quad> quads, class_4587 matrices, class_4597 consumers, class_4730 spriteId, class_1767 color, int light, int overlay, boolean glint) {
        class_1058 sprite = spriteId.method_24148();
        class_4588 atlasConsumer = spriteId.method_30001(consumers, atlasId -> class_4722.method_24067(), glint);
        class_4588 consumer = sprite.method_24108(atlasConsumer);
        class_4587.class_4665 entry = matrices.method_23760();
        Matrix4f positionMatrix = entry.method_23761();
        int rgb = color.method_7787();
        float red = (float)class_5253.class_5254.method_27765((int)rgb) / 255.0f;
        float green = (float)class_5253.class_5254.method_27766((int)rgb) / 255.0f;
        float blue = (float)class_5253.class_5254.method_27767((int)rgb) / 255.0f;
        for (Quad quad : quads) {
            consumer.method_22918(positionMatrix, quad.x1() * 0.0625f, quad.y2() * 0.0625f, 0.0624375f).method_22915(red, green, blue, 1.0f).method_22913(quad.u1(), quad.v2()).method_22922(overlay).method_60803(light).method_60831(entry, 0.0f, 0.0f, -1.0f);
            consumer.method_22918(positionMatrix, quad.x2() * 0.0625f, quad.y2() * 0.0625f, 0.0624375f).method_22915(red, green, blue, 1.0f).method_22913(quad.u2(), quad.v2()).method_22922(overlay).method_60803(light).method_60831(entry, 0.0f, 0.0f, -1.0f);
            consumer.method_22918(positionMatrix, quad.x2() * 0.0625f, quad.y1() * 0.0625f, 0.0624375f).method_22915(red, green, blue, 1.0f).method_22913(quad.u2(), quad.v1()).method_22922(overlay).method_60803(light).method_60831(entry, 0.0f, 0.0f, -1.0f);
            consumer.method_22918(positionMatrix, quad.x1() * 0.0625f, quad.y1() * 0.0625f, 0.0624375f).method_22915(red, green, blue, 1.0f).method_22913(quad.u1(), quad.v1()).method_22922(overlay).method_60803(light).method_60831(entry, 0.0f, 0.0f, -1.0f);
        }
    }

    @Environment(value=EnvType.CLIENT)
    protected record Quad(float x1, float y1, float x2, float y2, float u1, float v1, float u2, float v2) {
    }
}

