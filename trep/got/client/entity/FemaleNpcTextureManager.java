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
import trep.got.character.CharacterSelectionOption;
import trep.got.character.NpcAppearanceProfile;
import trep.got.client.character.CustomPlayerSkinResolver;
import trep.got.entity.AbstractKingdomNpcEntity;

@Environment(value=EnvType.CLIENT)
final class FemaleNpcTextureManager {
    private static final class_2960 WILDLING_FEMALE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/entity/npc/female/wildling_female.png");

    private FemaleNpcTextureManager() {
    }

    static class_2960 resolve(class_8685 fallbackSkin, CharacterSelectionOption option, AbstractKingdomNpcEntity entity) {
        if (entity == null) {
            return null;
        }
        NpcAppearanceProfile appearance = entity.getNpcAppearance();
        if (appearance == null || !appearance.isFemale()) {
            return null;
        }
        if (option != null && option.kingdomId() != null && "wildling".equals(option.kingdomId().method_12832())) {
            class_310 client = class_310.method_1551();
            return CustomPlayerSkinResolver.resolve(client, WILDLING_FEMALE_TEXTURE, true, fallbackSkin, "female_wildling_npc").comp_1626();
        }
        String clothesPath = NpcAppearanceProfile.resolveClothesTexturePath(entity.getKingdomId(), appearance.clothesVariant());
        if (clothesPath.isBlank()) {
            return null;
        }
        class_310 client = class_310.method_1551();
        String compositeKey = String.valueOf(entity.getKingdomId()) + "|" + appearance.gender() + "|" + appearance.skinVariant() + "|" + appearance.hairVariant() + "|" + appearance.clothesVariant();
        return CustomPlayerSkinResolver.resolveComposite(client, compositeKey, true, fallbackSkin, "female_kingdom_npc", () -> FemaleNpcTextureManager.compose(client, appearance, clothesPath)).comp_1626();
    }

    /*
     * Exception decompiling
     */
    private static class_1011 compose(class_310 client, NpcAppearanceProfile appearance, String clothesPath) throws IOException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
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
                target.method_4305(x, y, FemaleNpcTextureManager.alphaBlend(target.method_4315(x, y), overlay.method_4315(x, y)));
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
        int outRed = FemaleNpcTextureManager.blendChannel(base >> 16 & 0xFF, overlay >> 16 & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outGreen = FemaleNpcTextureManager.blendChannel(base >> 8 & 0xFF, overlay >> 8 & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outBlue = FemaleNpcTextureManager.blendChannel(base & 0xFF, overlay & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outAlphaInt = Math.min(255, Math.max(0, Math.round(outAlpha * 255.0f)));
        return outAlphaInt << 24 | outRed << 16 | outGreen << 8 | outBlue;
    }

    private static int blendChannel(int base, int overlay, float baseAlpha, float overlayAlpha, float outAlpha) {
        float value = ((float)overlay * overlayAlpha + (float)base * baseAlpha * (1.0f - overlayAlpha)) / outAlpha;
        return Math.min(255, Math.max(0, Math.round(value)));
    }
}

