/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1297
 *  net.minecraft.class_2960
 *  net.minecraft.class_4604
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_897
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_2960;
import net.minecraft.class_4604;
import net.minecraft.class_5617;
import net.minecraft.class_897;

@Environment(value=EnvType.CLIENT)
public final class InvisibleEntityRenderer<T extends class_1297>
extends class_897<T> {
    private static final class_2960 DUMMY_TEXTURE = class_2960.method_60656((String)"textures/misc/white.png");

    public InvisibleEntityRenderer(class_5617.class_5618 context) {
        super(context);
    }

    public boolean method_3933(T entity, class_4604 frustum, double x, double y, double z) {
        return false;
    }

    public class_2960 method_3931(T entity) {
        return DUMMY_TEXTURE;
    }
}

