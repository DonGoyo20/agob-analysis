/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1309
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3883
 *  net.minecraft.class_3887
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_583
 *  net.minecraft.class_591
 *  net.minecraft.class_742
 */
package trep.got.client.character;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_583;
import net.minecraft.class_591;
import net.minecraft.class_742;
import trep.got.character.PlayerSkinBuilderProfile;
import trep.got.client.character.CharacterPreviewPlayerEntity;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.character.PlayerSkinBuilderLayerTextureResolver;
import trep.got.client.character.PlayerSkinBuilderOptions;

@Environment(value=EnvType.CLIENT)
public final class PlayerSkinBuilderFeatureRenderer
extends class_3887<class_742, class_591<class_742>> {
    public PlayerSkinBuilderFeatureRenderer(class_3883<class_742, class_591<class_742>> context) {
        super(context);
    }

    public void render(class_4587 matrices, class_4597 vertexConsumers, int light, class_742 player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        PlayerSkinBuilderProfile profile = PlayerSkinBuilderFeatureRenderer.resolveProfile(player);
        if (profile == null || profile.npcBaseSkin()) {
            return;
        }
        this.renderLayer(matrices, vertexConsumers, light, player, PlayerSkinBuilderFeatureRenderer.textureOrNull("clothes/shirt/", profile.shirtId()), profile.shirtDyeId());
        this.renderLayer(matrices, vertexConsumers, light, player, PlayerSkinBuilderFeatureRenderer.textureOrNull("clothes/pants/", profile.pantsId()), profile.pantsDyeId());
        String hairFolder = "Female".equals(profile.gender()) ? "hair/female/" : "hair/male/";
        this.renderLayer(matrices, vertexConsumers, light, player, PlayerSkinBuilderFeatureRenderer.textureOrNull(hairFolder, profile.hairId()), "none");
    }

    private static PlayerSkinBuilderProfile resolveProfile(class_742 player) {
        if (player instanceof CharacterPreviewPlayerEntity) {
            CharacterPreviewPlayerEntity preview = (CharacterPreviewPlayerEntity)player;
            return preview.getSkinBuilderProfile();
        }
        class_310 client = class_310.method_1551();
        if (client != null && client.field_1724 == player) {
            return CharacterSelectionClientState.getSkinBuilderProfile();
        }
        return null;
    }

    private static class_2960 textureOrNull(String folder, String id) {
        return id == null || id.isBlank() ? null : PlayerSkinBuilderOptions.texture(folder + id);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void renderLayer(class_4587 matrices, class_4597 vertexConsumers, int light, class_742 player, class_2960 texture, String dyeId) {
        if (texture == null) {
            return;
        }
        texture = PlayerSkinBuilderLayerTextureResolver.resolve(class_310.method_1551(), texture, dyeId);
        class_591 model = (class_591)this.method_17165();
        boolean hatVisible = model.field_3394.field_3665;
        boolean jacketVisible = model.field_3483.field_3665;
        boolean leftSleeveVisible = model.field_3484.field_3665;
        boolean rightSleeveVisible = model.field_3486.field_3665;
        boolean leftPantsVisible = model.field_3482.field_3665;
        boolean rightPantsVisible = model.field_3479.field_3665;
        model.field_3394.field_3665 = true;
        model.field_3483.field_3665 = true;
        model.field_3484.field_3665 = true;
        model.field_3486.field_3665 = true;
        model.field_3482.field_3665 = true;
        model.field_3479.field_3665 = true;
        try {
            PlayerSkinBuilderFeatureRenderer.method_23199((class_583)model, (class_2960)texture, (class_4587)matrices, (class_4597)vertexConsumers, (int)light, (class_1309)player, (int)-1);
        }
        finally {
            model.field_3394.field_3665 = hatVisible;
            model.field_3483.field_3665 = jacketVisible;
            model.field_3484.field_3665 = leftSleeveVisible;
            model.field_3486.field_3665 = rightSleeveVisible;
            model.field_3482.field_3665 = leftPantsVisible;
            model.field_3479.field_3665 = rightPantsVisible;
        }
    }
}

