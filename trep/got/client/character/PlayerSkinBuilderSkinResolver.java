/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_638
 *  net.minecraft.class_746
 *  net.minecraft.class_8685
 */
package trep.got.client.character;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1011;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_8685;
import trep.got.character.PlayerSkinBuilderProfile;
import trep.got.client.character.CharacterPreviewPlayerEntity;
import trep.got.client.character.CustomPlayerSkinResolver;
import trep.got.client.character.PlayerSkinBuilderLayerTextureResolver;
import trep.got.client.character.PlayerSkinBuilderOptions;

@Environment(value=EnvType.CLIENT)
public final class PlayerSkinBuilderSkinResolver {
    private static final ThreadLocal<Boolean> SUPPRESS_OVERRIDE = ThreadLocal.withInitial(() -> false);

    private PlayerSkinBuilderSkinResolver() {
    }

    public static boolean shouldSuppressOverride() {
        return SUPPRESS_OVERRIDE.get();
    }

    public static <T> T withoutOverride(Supplier<T> supplier) {
        boolean previous = SUPPRESS_OVERRIDE.get();
        SUPPRESS_OVERRIDE.set(true);
        try {
            T t = supplier.get();
            return t;
        }
        finally {
            SUPPRESS_OVERRIDE.set(previous);
        }
    }

    public static CharacterPreviewPlayerEntity createPreviewEntity(class_638 world, PlayerSkinBuilderProfile profile) {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null) {
            return null;
        }
        class_8685 vanilla = PlayerSkinBuilderSkinResolver.withoutOverride(() -> ((class_746)client.field_1724).method_52814());
        class_8685 skinTextures = PlayerSkinBuilderSkinResolver.resolve(client, vanilla, profile);
        return new CharacterPreviewPlayerEntity(world, skinTextures, client.field_1724, profile);
    }

    public static class_8685 resolve(class_310 client, class_8685 vanilla, PlayerSkinBuilderProfile profile) {
        if (client == null || vanilla == null || profile == null || !profile.hasCustomLayer()) {
            return vanilla;
        }
        if (!profile.npcBaseSkin()) {
            return vanilla;
        }
        boolean smallArms = "Female".equals(profile.gender());
        String key = profile.npcBaseSkin() + "|" + profile.gender() + "|" + profile.skinTone() + "|" + profile.faceId() + "|" + profile.hairId() + "|" + profile.shirtId() + "|" + profile.shirtDyeId() + "|" + profile.pantsId() + "|" + profile.pantsDyeId() + "|" + String.valueOf(vanilla.comp_1626()) + "|player_builder";
        return CustomPlayerSkinResolver.resolveComposite(client, key, smallArms, vanilla, "player_skin_builder", () -> PlayerSkinBuilderSkinResolver.compose(client, vanilla, profile));
    }

    private static class_1011 compose(class_310 client, class_8685 vanilla, PlayerSkinBuilderProfile profile) throws IOException {
        class_1011 base = PlayerSkinBuilderSkinResolver.read(client, PlayerSkinBuilderOptions.texture("skin/skin_" + profile.skinTone()));
        if (base == null) {
            return null;
        }
        try {
            String faceFolder = "Female".equals(profile.gender()) ? "face/female/" : "face/male/";
            PlayerSkinBuilderSkinResolver.blendIfPresent(client, base, profile.faceId().isBlank() ? null : PlayerSkinBuilderOptions.texture(faceFolder + profile.faceId()));
            String hairFolder = "Female".equals(profile.gender()) ? "hair/female/" : "hair/male/";
            PlayerSkinBuilderSkinResolver.blendIfPresent(client, base, profile.shirtId().isBlank() ? null : PlayerSkinBuilderOptions.texture("clothes/shirt/" + profile.shirtId()), profile.shirtDyeId());
            PlayerSkinBuilderSkinResolver.blendIfPresent(client, base, profile.pantsId().isBlank() ? null : PlayerSkinBuilderOptions.texture("clothes/pants/" + profile.pantsId()), profile.pantsDyeId());
            PlayerSkinBuilderSkinResolver.blendIfPresent(client, base, profile.hairId().isBlank() ? null : PlayerSkinBuilderOptions.texture(hairFolder + profile.hairId()));
            return base;
        }
        catch (IOException e) {
            base.close();
            throw e;
        }
    }

    private static class_1011 read(class_310 client, class_2960 texture) throws IOException {
        try (InputStream stream = client.method_1478().open(texture);){
            class_1011 class_10112 = class_1011.method_4309((InputStream)stream);
            return class_10112;
        }
    }

    private static void blendIfPresent(class_310 client, class_1011 target, class_2960 texture) throws IOException {
        PlayerSkinBuilderSkinResolver.blendIfPresent(client, target, texture, "none");
    }

    private static void blendIfPresent(class_310 client, class_1011 target, class_2960 texture, String dyeId) throws IOException {
        if (texture == null) {
            return;
        }
        try (class_1011 overlay = PlayerSkinBuilderSkinResolver.read(client, texture);){
            class_1011 normalized = PlayerSkinBuilderLayerTextureResolver.normalizeLayerImage(overlay);
            PlayerSkinBuilderLayerTextureResolver.tintLayer(normalized, dyeId);
            PlayerSkinBuilderSkinResolver.blend(target, normalized);
            if (normalized != overlay) {
                normalized.close();
            }
        }
    }

    private static void blend(class_1011 target, class_1011 overlay) {
        int width = Math.min(target.method_4307(), overlay.method_4307());
        int height = Math.min(target.method_4323(), overlay.method_4323());
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                target.method_4305(x, y, PlayerSkinBuilderSkinResolver.alphaBlend(target.method_4315(x, y), overlay.method_4315(x, y)));
            }
        }
    }

    private static int alphaBlend(int base, int overlay) {
        float overlayAlpha = (float)(overlay >>> 24 & 0xFF) / 255.0f;
        if (overlayAlpha <= 0.0f) {
            return base;
        }
        float baseAlpha = (float)(base >>> 24 & 0xFF) / 255.0f;
        float outAlpha = overlayAlpha + baseAlpha * (1.0f - overlayAlpha);
        if (outAlpha <= 0.0f) {
            return 0;
        }
        int outRed = PlayerSkinBuilderSkinResolver.blendChannel(base >> 16 & 0xFF, overlay >> 16 & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outGreen = PlayerSkinBuilderSkinResolver.blendChannel(base >> 8 & 0xFF, overlay >> 8 & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outBlue = PlayerSkinBuilderSkinResolver.blendChannel(base & 0xFF, overlay & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outAlphaInt = Math.min(255, Math.max(0, Math.round(outAlpha * 255.0f)));
        return outAlphaInt << 24 | outRed << 16 | outGreen << 8 | outBlue;
    }

    private static int blendChannel(int base, int overlay, float baseAlpha, float overlayAlpha, float outAlpha) {
        float value = ((float)overlay * overlayAlpha + (float)base * baseAlpha * (1.0f - overlayAlpha)) / outAlpha;
        return Math.min(255, Math.max(0, Math.round(value)));
    }
}

