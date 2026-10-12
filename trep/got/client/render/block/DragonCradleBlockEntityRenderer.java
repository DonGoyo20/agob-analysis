/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1799
 *  net.minecraft.class_2246
 *  net.minecraft.class_2350
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_3922
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4608
 *  net.minecraft.class_5614$class_5615
 *  net.minecraft.class_776
 *  net.minecraft.class_811
 *  net.minecraft.class_827
 *  net.minecraft.class_918
 */
package trep.got.client.render.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3922;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5614;
import net.minecraft.class_776;
import net.minecraft.class_811;
import net.minecraft.class_827;
import net.minecraft.class_918;
import trep.got.block.DragonCradleBlock;
import trep.got.block.entity.DragonCradleBlockEntity;

@Environment(value=EnvType.CLIENT)
public final class DragonCradleBlockEntityRenderer
implements class_827<DragonCradleBlockEntity> {
    private final class_776 blockRenderManager;
    private final class_918 itemRenderer;

    public DragonCradleBlockEntityRenderer(class_5614.class_5615 context) {
        this.blockRenderManager = context.method_32141();
        this.itemRenderer = context.method_43335();
    }

    public void render(DragonCradleBlockEntity entity, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light, int overlay) {
        if (((Boolean)entity.method_11010().method_11654((class_2769)DragonCradleBlock.ACTIVATED)).booleanValue()) {
            this.renderCampfire(entity, matrices, vertexConsumers, light);
        }
        if (entity.hasEgg()) {
            this.renderFixed(entity.getEggStack(), 0.5f, 0.93f, 0.5f, 0.62f, entity, matrices, vertexConsumers, light);
        }
    }

    private void renderCampfire(DragonCradleBlockEntity entity, class_4587 matrices, class_4597 vertexConsumers, int light) {
        class_2680 campfireState = (class_2680)((class_2680)class_2246.field_17350.method_9564().method_11657((class_2769)class_3922.field_17352, (Comparable)Boolean.valueOf(true))).method_11657((class_2769)class_3922.field_17564, (Comparable)((class_2350)entity.method_11010().method_11654((class_2769)DragonCradleBlock.FACING)));
        matrices.method_22903();
        this.blockRenderManager.method_3353(campfireState, matrices, vertexConsumers, light, class_4608.field_21444);
        matrices.method_22909();
    }

    private void renderFixed(class_1799 stack, float x, float y, float z, float scale, DragonCradleBlockEntity entity, class_4587 matrices, class_4597 vertexConsumers, int light) {
        matrices.method_22903();
        matrices.method_46416(x, y, z);
        matrices.method_22905(scale, scale, scale);
        this.itemRenderer.method_23178(stack, class_811.field_4319, light, class_4608.field_21444, matrices, vertexConsumers, entity.method_10997(), 0);
        matrices.method_22909();
    }
}

