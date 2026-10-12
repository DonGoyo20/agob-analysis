/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2350
 *  net.minecraft.class_2769
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4608
 *  net.minecraft.class_5614$class_5615
 *  net.minecraft.class_7833
 *  net.minecraft.class_811
 *  net.minecraft.class_827
 *  net.minecraft.class_918
 */
package trep.got.client.render.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2350;
import net.minecraft.class_2769;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5614;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_827;
import net.minecraft.class_918;
import trep.got.block.MarketDisplayBlock;
import trep.got.block.entity.MarketDisplayBlockEntity;

@Environment(value=EnvType.CLIENT)
public final class MarketDisplayBlockEntityRenderer
implements class_827<MarketDisplayBlockEntity> {
    private final class_918 itemRenderer;

    public MarketDisplayBlockEntityRenderer(class_5614.class_5615 context) {
        this.itemRenderer = context.method_43335();
    }

    public void render(MarketDisplayBlockEntity entity, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light, int overlay) {
        if (entity.getRenderedDisplayStack().method_7960()) {
            return;
        }
        class_2350 facing = (class_2350)entity.method_11010().method_11654((class_2769)MarketDisplayBlock.FACING);
        matrices.method_22903();
        matrices.method_46416(0.5f, 1.08f, 0.5f);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-facing.method_10144()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(5.0f));
        matrices.method_22905(0.55f, 0.55f, 0.55f);
        this.itemRenderer.method_23178(entity.getRenderedDisplayStack(), class_811.field_4319, light, class_4608.field_21444, matrices, vertexConsumers, entity.method_10997(), 0);
        matrices.method_22909();
    }
}

