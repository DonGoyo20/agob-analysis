/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1799
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
import net.minecraft.class_1799;
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
import trep.got.blacksmith.BlacksmithCraftSession;
import trep.got.blacksmith.BlacksmithCrafting;
import trep.got.blacksmith.BlacksmithWorkbenchAccess;
import trep.got.block.AbstractMultiBlockMachineBlock;
import trep.got.block.entity.BlacksmithTableBlockEntity;
import trep.got.client.blacksmith.BlacksmithSmithingHud;

@Environment(value=EnvType.CLIENT)
public final class BlacksmithTableSessionRenderer
implements class_827<BlacksmithTableBlockEntity> {
    private static final float[][] ITEM_SLOTS = new float[][]{{0.35f, 0.32f}, {0.72f, 0.36f}, {0.4f, 0.68f}, {0.74f, 0.72f}};
    private final class_918 itemRenderer;

    public BlacksmithTableSessionRenderer(class_5614.class_5615 context) {
        this.itemRenderer = context.method_43335();
    }

    public void render(BlacksmithTableBlockEntity entity, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light, int overlay) {
        if (entity.method_10997() == null || entity.method_11015()) {
            return;
        }
        BlacksmithWorkbenchAccess.WorkbenchPair pair = BlacksmithWorkbenchAccess.findPair(entity.method_10997(), entity.method_11016(), entity.method_11010());
        if (pair == null) {
            return;
        }
        BlacksmithCraftSession session = BlacksmithCrafting.getSession(entity.method_10997(), pair);
        if (session == null) {
            return;
        }
        if (session.isComplete()) {
            BlacksmithSmithingHud.markFinished(pair.furnaceOrigin(), "client_complete_renderer");
            return;
        }
        if (BlacksmithSmithingHud.isFinished(pair.furnaceOrigin())) {
            return;
        }
        class_2350 facing = (class_2350)entity.method_11010().method_11654((class_2769)AbstractMultiBlockMachineBlock.FACING);
        for (int index = 0; index < Math.min(session.reservedStacks().size(), ITEM_SLOTS.length); ++index) {
            class_1799 stack = session.reservedStacks().get(index);
            if (stack.method_7960()) continue;
            this.renderPile(stack, matrices, vertexConsumers, light, facing, ITEM_SLOTS[index][0], ITEM_SLOTS[index][1], entity);
        }
    }

    private void renderPile(class_1799 stack, class_4587 matrices, class_4597 vertexConsumers, int light, class_2350 facing, float localX, float localZ, BlacksmithTableBlockEntity entity) {
        int copies = stack.method_7947() <= 1 ? 1 : Math.min(4, 1 + stack.method_7947() / 2);
        for (int copy = 0; copy < copies; ++copy) {
            matrices.method_22903();
            float spreadX = (float)(copy % 2) * 0.04f;
            float spreadZ = (float)(copy / 2) * 0.04f;
            matrices.method_46416(localX + spreadX, 0.64f + (float)copy * 0.02f, localZ + spreadZ);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(BlacksmithTableSessionRenderer.rotationFor(facing) + (float)copy * 8.0f));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(90.0f));
            float scale = 0.38f;
            matrices.method_22905(scale, scale, scale);
            this.itemRenderer.method_23178(stack, class_811.field_4319, light, class_4608.field_21444, matrices, vertexConsumers, entity.method_10997(), 0);
            matrices.method_22909();
        }
    }

    private static float rotationFor(class_2350 facing) {
        return switch (facing) {
            case class_2350.field_11035 -> 180.0f;
            case class_2350.field_11039 -> 90.0f;
            case class_2350.field_11034 -> -90.0f;
            default -> 0.0f;
        };
    }
}

