/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1068
 *  net.minecraft.class_1309
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
 *  net.minecraft.class_8685
 *  net.minecraft.class_922
 *  net.minecraft.class_970
 *  net.minecraft.class_989
 */
package trep.got.client.entity;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1068;
import net.minecraft.class_1309;
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
import net.minecraft.class_8685;
import net.minecraft.class_922;
import net.minecraft.class_970;
import net.minecraft.class_989;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.character.CustomPlayerSkinResolver;
import trep.got.client.entity.AgobCombatPlayerEntityModel;
import trep.got.client.entity.KingdomNpcIndicatorFeatureRenderer;
import trep.got.entity.SettlerNpcEntity;

@Environment(value=EnvType.CLIENT)
public final class SettlerNpcRenderer
extends class_922<SettlerNpcEntity, class_591<SettlerNpcEntity>> {
    private final class_591<SettlerNpcEntity> wideModel = (class_591)this.method_4038();
    private final class_591<SettlerNpcEntity> slimModel;
    private final class_8685 fallbackSkin;

    public SettlerNpcRenderer(class_5617.class_5618 context) {
        super(context, new AgobCombatPlayerEntityModel(context.method_32167(class_5602.field_27577), false), 0.5f);
        this.slimModel = new AgobCombatPlayerEntityModel<SettlerNpcEntity>(context.method_32167(class_5602.field_27581), true);
        this.method_4046((class_3887)new class_970((class_3883)this, new class_572(context.method_32167(class_5602.field_27579)), new class_572(context.method_32167(class_5602.field_27580)), context.method_48481()));
        this.method_4046((class_3887)new class_989((class_3883)this, context.method_43338()));
        this.method_4046(new KingdomNpcIndicatorFeatureRenderer(this, context.method_32168(), context.method_32166()));
        class_310 client = class_310.method_1551();
        this.fallbackSkin = client.field_1724 != null ? client.field_1724.method_52814() : class_1068.method_4648((UUID)UUID.nameUUIDFromBytes("settler_npc".getBytes(StandardCharsets.UTF_8)));
    }

    public void render(SettlerNpcEntity entity, float yaw, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light) {
        this.field_4737 = SettlerNpcRenderer.usesSmallArms(entity) ? this.slimModel : this.wideModel;
        super.method_4054((class_1309)entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    public class_2960 getTexture(SettlerNpcEntity entity) {
        class_2960 texture;
        CharacterSelectionOption option = entity.getCharacterOption();
        class_2960 class_29602 = texture = option != null ? option.previewSkinTexture() : null;
        if (texture == null) {
            return this.fallbackSkin.comp_1626();
        }
        return CustomPlayerSkinResolver.resolve(class_310.method_1551(), texture, option.smallArms(), this.fallbackSkin, "settler_npc").comp_1626();
    }

    protected boolean hasLabel(SettlerNpcEntity entity) {
        return false;
    }

    private static boolean usesSmallArms(SettlerNpcEntity entity) {
        CharacterSelectionOption option = entity.getCharacterOption();
        return option != null && option.smallArms();
    }
}

