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
 *  net.minecraft.class_8685
 *  net.minecraft.class_922
 *  net.minecraft.class_970
 *  net.minecraft.class_989
 */
package trep.got.client.entity;

import java.nio.charset.StandardCharsets;
import java.util.Map;
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
import net.minecraft.class_8685;
import net.minecraft.class_922;
import net.minecraft.class_970;
import net.minecraft.class_989;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.character.CustomPlayerSkinResolver;
import trep.got.client.entity.AgobCombatPlayerEntityModel;
import trep.got.client.entity.FemaleNpcTextureManager;
import trep.got.client.entity.KingdomNpcIndicatorFeatureRenderer;
import trep.got.client.entity.MaleNpcTextureManager;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.registry.GotCharacterOptions;

@Environment(value=EnvType.CLIENT)
public final class KingdomNpcRenderer<T extends AbstractKingdomNpcEntity>
extends class_922<T, class_591<T>> {
    private static final Map<class_2960, class_2960> SPECIAL_PREVIEW_TEXTURES = Map.of(class_2960.method_60655((String)"agob", (String)"the_nights_watch"), class_2960.method_60655((String)"agob", (String)"textures/entity/npc/peasant_wildling.png"));
    private final class_591<T> wideModel = (class_591)this.method_4038();
    private final class_591<T> slimModel;
    private final class_8685 fallbackSkin;
    private final CharacterSelectionOption option;
    private final class_2960 kingdomId;

    public KingdomNpcRenderer(class_5617.class_5618 context, class_2960 kingdomId) {
        super(context, new AgobCombatPlayerEntityModel(context.method_32167(class_5602.field_27577), false), 0.5f);
        this.slimModel = new AgobCombatPlayerEntityModel(context.method_32167(class_5602.field_27581), true);
        this.kingdomId = kingdomId;
        this.option = GotCharacterOptions.getByKingdomId(kingdomId);
        boolean slim = KingdomNpcRenderer.isSlim(kingdomId);
        this.method_4046((class_3887)new class_970((class_3883)this, new class_572(context.method_32167(slim ? class_5602.field_27582 : class_5602.field_27579)), new class_572(context.method_32167(slim ? class_5602.field_27583 : class_5602.field_27580)), context.method_48481()));
        this.method_4046((class_3887)new class_989((class_3883)this, context.method_43338()));
        this.method_4046(new KingdomNpcIndicatorFeatureRenderer(this, context.method_32168(), context.method_32166()));
        class_310 client = class_310.method_1551();
        this.fallbackSkin = client.field_1724 != null ? client.field_1724.method_52814() : class_1068.method_4648((UUID)UUID.nameUUIDFromBytes(kingdomId.toString().getBytes(StandardCharsets.UTF_8)));
    }

    public void render(T entity, float yaw, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light) {
        this.field_4737 = this.usesSlimModel(entity) ? this.slimModel : this.wideModel;
        super.method_4054(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    public class_2960 getTexture(T entity) {
        class_2960 femaleTexture = FemaleNpcTextureManager.resolve(this.fallbackSkin, this.option, entity);
        if (femaleTexture != null) {
            return femaleTexture;
        }
        class_2960 maleTexture = MaleNpcTextureManager.resolve(this.fallbackSkin, entity);
        if (maleTexture != null) {
            return maleTexture;
        }
        if (this.option == null || this.option.previewSkinTexture() == null) {
            class_2960 specialPreview = SPECIAL_PREVIEW_TEXTURES.get(this.kingdomId);
            if (specialPreview != null) {
                return CustomPlayerSkinResolver.resolve(class_310.method_1551(), specialPreview, this.usesSlimModel(entity), this.fallbackSkin, "kingdom_npc").comp_1626();
            }
            return this.fallbackSkin.comp_1626();
        }
        return CustomPlayerSkinResolver.resolve(class_310.method_1551(), this.option.previewSkinTexture(), this.usesSlimModel(entity), this.fallbackSkin, "kingdom_npc").comp_1626();
    }

    protected boolean hasLabel(T entity) {
        return false;
    }

    private static boolean isSlim(class_2960 kingdomId) {
        CharacterSelectionOption option = GotCharacterOptions.getByKingdomId(kingdomId);
        if (option != null) {
            return option.smallArms();
        }
        return false;
    }

    private boolean usesSlimModel(T entity) {
        return entity != null && ((AbstractKingdomNpcEntity)entity).getNpcAppearance() != null && ((AbstractKingdomNpcEntity)entity).getNpcAppearance().isFemale() || KingdomNpcRenderer.isSlim(this.kingdomId);
    }
}

