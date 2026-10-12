/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2960
 *  net.minecraft.class_3883
 *  net.minecraft.class_3887
 *  net.minecraft.class_5602
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_572
 *  net.minecraft.class_591
 *  net.minecraft.class_922
 *  net.minecraft.class_970
 *  net.minecraft.class_989
 */
package trep.got.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_5602;
import net.minecraft.class_5617;
import net.minecraft.class_572;
import net.minecraft.class_591;
import net.minecraft.class_922;
import net.minecraft.class_970;
import net.minecraft.class_989;
import trep.got.client.entity.AgobCombatPlayerEntityModel;
import trep.got.entity.AbstractWorldEventNpcEntity;

@Environment(value=EnvType.CLIENT)
public final class WorldEventNpcRenderer<T extends AbstractWorldEventNpcEntity>
extends class_922<T, class_591<T>> {
    public WorldEventNpcRenderer(class_5617.class_5618 context) {
        super(context, new AgobCombatPlayerEntityModel(context.method_32167(class_5602.field_27577), false), 0.5f);
        this.method_4046((class_3887)new class_970((class_3883)this, new class_572(context.method_32167(class_5602.field_27579)), new class_572(context.method_32167(class_5602.field_27580)), context.method_48481()));
        this.method_4046((class_3887)new class_989((class_3883)this, context.method_43338()));
    }

    public class_2960 getTexture(T entity) {
        return ((AbstractWorldEventNpcEntity)entity).getTextureId();
    }

    protected boolean hasLabel(T entity) {
        return false;
    }
}

