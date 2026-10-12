/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1068
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3883
 *  net.minecraft.class_3887
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_5602
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_572
 *  net.minecraft.class_591
 *  net.minecraft.class_640
 *  net.minecraft.class_8685
 *  net.minecraft.class_922
 *  net.minecraft.class_970
 *  net.minecraft.class_989
 */
package trep.got.client.entity;

import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1068;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_5602;
import net.minecraft.class_5617;
import net.minecraft.class_572;
import net.minecraft.class_591;
import net.minecraft.class_640;
import net.minecraft.class_8685;
import net.minecraft.class_922;
import net.minecraft.class_970;
import net.minecraft.class_989;
import trep.got.client.entity.AgobCombatPlayerEntityModel;
import trep.got.client.entity.KingdomNpcIndicatorFeatureRenderer;
import trep.got.entity.AbstractDynastyNpcEntity;

@Environment(value=EnvType.CLIENT)
public final class DynastyNpcRenderer<T extends AbstractDynastyNpcEntity>
extends class_922<T, class_591<T>> {
    private final class_591<T> wideModel = (class_591)this.method_4038();
    private final class_591<T> slimModel;

    public DynastyNpcRenderer(class_5617.class_5618 context) {
        super(context, new AgobCombatPlayerEntityModel(context.method_32167(class_5602.field_27577), false), 0.5f);
        this.slimModel = new AgobCombatPlayerEntityModel(context.method_32167(class_5602.field_27581), true);
        this.method_4046((class_3887)new class_970((class_3883)this, new class_572(context.method_32167(class_5602.field_27579)), new class_572(context.method_32167(class_5602.field_27580)), context.method_48481()));
        this.method_4046((class_3887)new class_989((class_3883)this, context.method_43338()));
        this.method_4046(new KingdomNpcIndicatorFeatureRenderer(this, context.method_32168(), context.method_32166()));
    }

    public void render(T entity, float yaw, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light) {
        this.field_4737 = entity != null && ((AbstractDynastyNpcEntity)entity).isOwnerSmallArms() ? this.slimModel : this.wideModel;
        super.method_4054(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    public class_2960 getTexture(T entity) {
        class_8685 ownerSkin = DynastyNpcRenderer.resolveOwnerSkin(entity);
        return ownerSkin == null ? class_1068.method_4649() : ownerSkin.comp_1626();
    }

    protected boolean hasLabel(T entity) {
        return false;
    }

    private static class_8685 resolveOwnerSkin(AbstractDynastyNpcEntity entity) {
        if (entity == null || entity.getOwnerPlayerUuid().isBlank()) {
            return null;
        }
        class_310 client = class_310.method_1551();
        try {
            class_640 entry;
            UUID ownerUuid = UUID.fromString(entity.getOwnerPlayerUuid());
            if (client.field_1724 != null && ownerUuid.equals(client.field_1724.method_5667())) {
                return client.field_1724.method_52814();
            }
            if (client.method_1562() != null && (entry = client.method_1562().method_2871(ownerUuid)) != null) {
                return entry.method_52810();
            }
            return class_1068.method_4648((UUID)ownerUuid);
        }
        catch (IllegalArgumentException exception) {
            return null;
        }
    }
}

