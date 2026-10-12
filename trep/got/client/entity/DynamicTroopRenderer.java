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
import trep.got.entity.DynamicTroopEntity;
import trep.got.registry.GotCharacterOptions;
import trep.got.troop.DynamicTroopDefinition;

@Environment(value=EnvType.CLIENT)
public final class DynamicTroopRenderer
extends class_922<DynamicTroopEntity, class_591<DynamicTroopEntity>> {
    private final class_591<DynamicTroopEntity> wideModel = (class_591)this.method_4038();
    private final class_591<DynamicTroopEntity> slimModel;
    private final class_8685 fallbackSkin;

    public DynamicTroopRenderer(class_5617.class_5618 context) {
        super(context, new AgobCombatPlayerEntityModel(context.method_32167(class_5602.field_27577), false), 0.5f);
        this.slimModel = new AgobCombatPlayerEntityModel<DynamicTroopEntity>(context.method_32167(class_5602.field_27581), true);
        this.method_4046((class_3887)new class_970((class_3883)this, new class_572(context.method_32167(class_5602.field_27579)), new class_572(context.method_32167(class_5602.field_27580)), context.method_48481()));
        this.method_4046((class_3887)new class_989((class_3883)this, context.method_43338()));
        this.method_4046(new KingdomNpcIndicatorFeatureRenderer(this, context.method_32168(), context.method_32166()));
        class_310 client = class_310.method_1551();
        this.fallbackSkin = client.field_1724 != null ? client.field_1724.method_52814() : class_1068.method_4648((UUID)UUID.nameUUIDFromBytes("dynamic_troop".getBytes(StandardCharsets.UTF_8)));
    }

    public void render(DynamicTroopEntity livingEntity, float f, float g, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i) {
        this.field_4737 = DynamicTroopRenderer.usesSmallArms(livingEntity) ? this.slimModel : this.wideModel;
        super.method_4054((class_1309)livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public class_2960 getTexture(DynamicTroopEntity entity) {
        class_2960 texture;
        CharacterSelectionOption option = DynamicTroopRenderer.kingdomOption(entity);
        DynamicTroopDefinition definition = entity.getTroopDefinition();
        class_2960 class_29602 = definition != null && definition.skinTexture() != null ? definition.skinTexture() : (texture = option != null ? option.previewSkinTexture() : null);
        if (texture == null) {
            return this.fallbackSkin.comp_1626();
        }
        return CustomPlayerSkinResolver.resolve(class_310.method_1551(), texture, option != null && option.smallArms(), this.fallbackSkin, "dynamic_troop").comp_1626();
    }

    protected boolean hasLabel(DynamicTroopEntity entity) {
        return false;
    }

    private static boolean usesSmallArms(DynamicTroopEntity entity) {
        CharacterSelectionOption option = DynamicTroopRenderer.kingdomOption(entity);
        return option != null && option.smallArms();
    }

    private static CharacterSelectionOption kingdomOption(DynamicTroopEntity entity) {
        return GotCharacterOptions.getByKingdomId(entity.getKingdomId());
    }
}

