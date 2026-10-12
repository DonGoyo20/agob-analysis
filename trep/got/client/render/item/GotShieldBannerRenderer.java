/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1767
 *  net.minecraft.class_1799
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_9307
 *  net.minecraft.class_9334
 */
package trep.got.client.render.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_9307;
import net.minecraft.class_9334;
import trep.got.client.render.item.model.GotShieldPlateModel;
import trep.got.client.render.item.model.KiteShieldPlateModel;
import trep.got.client.render.item.model.RoundShieldPlateModel;
import trep.got.client.render.item.model.SquareShieldPlateModel;
import trep.got.registry.GotItems;

@Environment(value=EnvType.CLIENT)
public final class GotShieldBannerRenderer {
    private GotShieldBannerRenderer() {
    }

    public static void render(class_1799 stack, class_4587 matrices, class_4597 consumers, int light, int overlay) {
        class_9307 patterns = (class_9307)stack.method_57825(class_9334.field_49619, (Object)class_9307.field_49404);
        class_1767 baseColor = (class_1767)stack.method_57824(class_9334.field_49620);
        if (patterns.comp_2428().isEmpty() && baseColor == null) {
            return;
        }
        GotShieldPlateModel model = GotShieldBannerRenderer.getModel(stack);
        if (model == null) {
            return;
        }
        model.renderBanner(matrices, consumers, light, overlay, baseColor != null ? baseColor : class_1767.field_7952, patterns, stack.method_7958());
    }

    private static GotShieldPlateModel getModel(class_1799 stack) {
        if (stack.method_31574(GotItems.SQUARE_SHIELD)) {
            return SquareShieldPlateModel.INSTANCE;
        }
        if (stack.method_31574(GotItems.ROUND_SHIELD)) {
            return RoundShieldPlateModel.INSTANCE;
        }
        if (stack.method_31574(GotItems.KITE_SHIELD)) {
            return KiteShieldPlateModel.INSTANCE;
        }
        return null;
    }
}

