/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_8685
 */
package trep.got.client.entity;

import java.io.IOException;
import java.io.InputStream;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1011;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_8685;
import trep.got.character.NpcAppearanceProfile;
import trep.got.client.character.CustomPlayerSkinResolver;
import trep.got.entity.AbstractKingdomNpcEntity;

@Environment(value=EnvType.CLIENT)
final class MaleNpcTextureManager {
    private MaleNpcTextureManager() {
    }

    static class_2960 resolve(class_8685 fallbackSkin, AbstractKingdomNpcEntity entity) {
        if (entity == null) {
            return null;
        }
        NpcAppearanceProfile appearance = entity.getNpcAppearance();
        if (appearance == null || appearance.isFemale()) {
            return null;
        }
        String clothesPath = NpcAppearanceProfile.resolveMaleClothesTexturePath(entity.getKingdomId(), appearance.clothesVariant());
        String facePath = NpcAppearanceProfile.resolveMaleFaceTexturePath(appearance.skinVariant(), appearance.hairVariant());
        if (clothesPath.isBlank() || facePath.isBlank()) {
            return null;
        }
        class_310 client = class_310.method_1551();
        String compositeKey = String.valueOf(entity.getKingdomId()) + "|" + appearance.gender() + "|" + appearance.skinVariant() + "|" + appearance.hairVariant() + "|" + appearance.clothesVariant();
        return CustomPlayerSkinResolver.resolveComposite(client, compositeKey, false, fallbackSkin, "male_kingdom_npc", () -> MaleNpcTextureManager.compose(client, clothesPath, facePath)).comp_1626();
    }

    private static class_1011 compose(class_310 client, String clothesPath, String facePath) throws IOException {
        class_2960 clothesTexture = class_2960.method_60655((String)"agob", (String)clothesPath);
        class_2960 faceTexture = class_2960.method_60655((String)"agob", (String)facePath);
        try (class_1011 clothes = MaleNpcTextureManager.read(client, clothesTexture);){
            class_1011 class_10112;
            block12: {
                class_1011 face = MaleNpcTextureManager.read(client, faceTexture);
                try {
                    class_1011 output = new class_1011(clothes.method_4307(), clothes.method_4323(), true);
                    MaleNpcTextureManager.copy(output, clothes);
                    MaleNpcTextureManager.blend(output, face);
                    class_10112 = output;
                    if (face == null) break block12;
                }
                catch (Throwable throwable) {
                    if (face != null) {
                        try {
                            face.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                face.close();
            }
            return class_10112;
        }
    }

    private static class_1011 read(class_310 client, class_2960 texture) throws IOException {
        try (InputStream stream = client.method_1478().open(texture);){
            class_1011 class_10112 = class_1011.method_4309((InputStream)stream);
            return class_10112;
        }
    }

    private static void copy(class_1011 target, class_1011 source) {
        for (int x = 0; x < target.method_4307(); ++x) {
            for (int y = 0; y < target.method_4323(); ++y) {
                target.method_4305(x, y, source.method_4315(x, y));
            }
        }
    }

    private static void blend(class_1011 target, class_1011 overlay) {
        int width = Math.min(target.method_4307(), overlay.method_4307());
        int height = Math.min(target.method_4323(), overlay.method_4323());
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                target.method_4305(x, y, MaleNpcTextureManager.alphaBlend(target.method_4315(x, y), overlay.method_4315(x, y)));
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
        int outRed = MaleNpcTextureManager.blendChannel(base >> 16 & 0xFF, overlay >> 16 & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outGreen = MaleNpcTextureManager.blendChannel(base >> 8 & 0xFF, overlay >> 8 & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outBlue = MaleNpcTextureManager.blendChannel(base & 0xFF, overlay & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outAlphaInt = Math.min(255, Math.max(0, Math.round(outAlpha * 255.0f)));
        return outAlphaInt << 24 | outRed << 16 | outGreen << 8 | outBlue;
    }

    private static int blendChannel(int base, int overlay, float baseAlpha, float overlayAlpha, float outAlpha) {
        float value = ((float)overlay * overlayAlpha + (float)base * baseAlpha * (1.0f - overlayAlpha)) / outAlpha;
        return Math.min(255, Math.max(0, Math.round(value)));
    }
}

