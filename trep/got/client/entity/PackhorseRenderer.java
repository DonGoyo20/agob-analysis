/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1492
 *  net.minecraft.class_2960
 *  net.minecraft.class_5602
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_883
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1492;
import net.minecraft.class_2960;
import net.minecraft.class_5602;
import net.minecraft.class_5617;
import net.minecraft.class_883;
import trep.got.entity.PackhorseEntity;

@Environment(value=EnvType.CLIENT)
public final class PackhorseRenderer
extends class_883 {
    private static final class_2960 TEXTURE = class_2960.method_60656((String)"textures/entity/horse/mule.png");

    public PackhorseRenderer(class_5617.class_5618 context) {
        super(context, 0.75f, class_5602.field_27616);
    }

    public class_2960 method_3894(class_1492 entity) {
        return TEXTURE;
    }

    public class_2960 getTexture(PackhorseEntity entity) {
        return TEXTURE;
    }
}

