/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
 *  net.minecraft.class_2960
 *  net.minecraft.class_4587
 *  net.minecraft.class_5601
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_583
 *  net.minecraft.class_927
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_5601;
import net.minecraft.class_5617;
import net.minecraft.class_583;
import net.minecraft.class_927;
import trep.got.client.entity.DirewolfEntityModel;
import trep.got.entity.DirewolfEntity;

@Environment(value=EnvType.CLIENT)
public final class DirewolfEntityRenderer
extends class_927<DirewolfEntity, DirewolfEntityModel> {
    private static final class_5601 MODEL_LAYER = new class_5601(class_2960.method_60655((String)"agob", (String)"direwolf"), "main");
    private static final class_2960[] TEXTURES = new class_2960[]{class_2960.method_60655((String)"agob", (String)"textures/entity/direwolf/wolf_1.png"), class_2960.method_60655((String)"agob", (String)"textures/entity/direwolf/wolf_2.png"), class_2960.method_60655((String)"agob", (String)"textures/entity/direwolf/wolf_3.png"), class_2960.method_60655((String)"agob", (String)"textures/entity/direwolf/wolf_4.png")};

    public DirewolfEntityRenderer(class_5617.class_5618 context) {
        super(context, (class_583)new DirewolfEntityModel(context.method_32167(MODEL_LAYER)), 0.75f);
    }

    public static void initialize() {
        EntityModelLayerRegistry.registerModelLayer((class_5601)MODEL_LAYER, DirewolfEntityModel::getTexturedModelData);
    }

    public class_2960 getTexture(DirewolfEntity entity) {
        int index = Math.max(0, Math.min(TEXTURES.length - 1, entity.getDirewolfVariant() - 1));
        return TEXTURES[index];
    }

    protected void scale(DirewolfEntity entity, class_4587 matrices, float amount) {
        float scale = entity.method_6109() ? 0.8f : 1.45f;
        matrices.method_22905(scale, scale, scale);
    }
}

