/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1799
 *  net.minecraft.class_2350
 *  net.minecraft.class_2680
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

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5614;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_827;
import net.minecraft.class_918;
import trep.got.block.KitchenwareBlock;
import trep.got.block.entity.KitchenwareBlockEntity;
import trep.got.registry.GotBlocks;

@Environment(value=EnvType.CLIENT)
public final class KitchenwareBlockEntityRenderer
implements class_827<KitchenwareBlockEntity> {
    private static final float[][] SINGLE_SLOT = new float[][]{{0.5f, 0.5f}};
    private static final float[][] FOUR_SLOTS = new float[][]{{0.39f, 0.39f}, {0.61f, 0.39f}, {0.39f, 0.61f}, {0.61f, 0.61f}};
    private final class_918 itemRenderer;

    public KitchenwareBlockEntityRenderer(class_5614.class_5615 context) {
        this.itemRenderer = context.method_43335();
    }

    public void render(KitchenwareBlockEntity entity, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light, int overlay) {
        List<class_1799> ingredients = entity.getDisplayStacks();
        if (ingredients.stream().allMatch(class_1799::method_7960)) {
            return;
        }
        class_2680 state = entity.method_11010();
        class_2350 facing = state.method_28498((class_2769)KitchenwareBlock.FACING) ? (class_2350)state.method_11654((class_2769)KitchenwareBlock.FACING) : class_2350.field_11043;
        float[][] slots = state.method_27852(GotBlocks.POT) || state.method_27852(GotBlocks.PAN) ? FOUR_SLOTS : SINGLE_SLOT;
        float height = KitchenwareBlockEntityRenderer.renderHeight(state);
        float scale = KitchenwareBlockEntityRenderer.renderScale(state);
        for (int index = 0; index < ingredients.size() && index < slots.length; ++index) {
            class_1799 stack = ingredients.get(index);
            if (stack.method_7960()) continue;
            this.renderIngredient(stack, matrices, vertexConsumers, light, facing, slots[index][0], slots[index][1], height, scale, entity, index);
        }
    }

    private void renderIngredient(class_1799 stack, class_4587 matrices, class_4597 vertexConsumers, int light, class_2350 facing, float localX, float localZ, float height, float scale, KitchenwareBlockEntity entity, int index) {
        matrices.method_22903();
        matrices.method_46416(0.5f, height, 0.5f);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-facing.method_10144()));
        matrices.method_46416(localX - 0.5f, (float)index * 0.0125f, localZ - 0.5f);
        matrices.method_22907(class_7833.field_40716.rotationDegrees((index % 2 == 0 ? -8.0f : 8.0f) + (float)index * 3.0f));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(90.0f));
        matrices.method_22905(scale, scale, scale);
        this.itemRenderer.method_23178(stack, class_811.field_4319, light, class_4608.field_21444, matrices, vertexConsumers, entity.method_10997(), index);
        matrices.method_22909();
    }

    private static float renderHeight(class_2680 state) {
        if (state.method_27852(GotBlocks.POT)) {
            return 0.38f;
        }
        if (state.method_27852(GotBlocks.PAN)) {
            return 0.18f;
        }
        if (state.method_27852(GotBlocks.PLATE)) {
            return 0.16f;
        }
        return 0.13f;
    }

    private static float renderScale(class_2680 state) {
        if (state.method_27852(GotBlocks.POT)) {
            return 0.24f;
        }
        if (state.method_27852(GotBlocks.PAN)) {
            return 0.28f;
        }
        return 0.36f;
    }
}

