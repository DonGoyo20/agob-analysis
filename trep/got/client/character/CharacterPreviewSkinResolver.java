/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 *  net.minecraft.class_1304
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_5134
 *  net.minecraft.class_638
 *  net.minecraft.class_746
 *  net.minecraft.class_7923
 *  net.minecraft.class_8685
 */
package trep.got.client.character;

import java.io.IOException;
import java.io.InputStream;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1011;
import net.minecraft.class_1304;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_5134;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_7923;
import net.minecraft.class_8685;
import trep.got.character.CharacterSelectionOption;
import trep.got.character.NpcAppearanceProfile;
import trep.got.character.PlayerGeneticTrait;
import trep.got.character.PlayerTraitEffects;
import trep.got.client.character.CharacterPreviewPlayerEntity;
import trep.got.client.character.CustomPlayerSkinResolver;
import trep.got.client.character.PlayerSkinBuilderSkinResolver;
import trep.got.registry.GotKingdomArmor;

@Environment(value=EnvType.CLIENT)
public final class CharacterPreviewSkinResolver {
    private static final class_2960 WILDLING_FEMALE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/entity/npc/female/wildling_female.png");

    private CharacterPreviewSkinResolver() {
    }

    public static CharacterPreviewPlayerEntity createPreviewEntity(class_638 world, CharacterSelectionOption option, PlayerGeneticTrait geneticTrait) {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null) {
            return null;
        }
        class_8685 skinTextures = CharacterPreviewSkinResolver.resolveSkinTextures(client, option);
        CharacterPreviewPlayerEntity preview = new CharacterPreviewPlayerEntity(world, skinTextures, client.field_1724);
        CharacterPreviewSkinResolver.applyEquipment(preview, option);
        float scaleMultiplier = PlayerTraitEffects.resolveScaleMultiplier(geneticTrait);
        if (preview.method_5996(class_5134.field_47760) != null) {
            preview.method_5996(class_5134.field_47760).method_6192((double)scaleMultiplier);
            preview.method_18382();
        }
        return preview;
    }

    public static CharacterPreviewPlayerEntity createNpcPreviewEntity(class_638 world, CharacterSelectionOption option, NpcAppearanceProfile appearance) {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null || option == null) {
            return null;
        }
        class_8685 skinTextures = CharacterPreviewSkinResolver.resolveNpcSkinTextures(client, option, appearance);
        CharacterPreviewPlayerEntity preview = new CharacterPreviewPlayerEntity(world, skinTextures, client.field_1724);
        CharacterPreviewSkinResolver.applyEquipment(preview, option);
        return preview;
    }

    private static class_8685 resolveSkinTextures(class_310 client, CharacterSelectionOption option) {
        class_2960 previewSkinTexture = option.previewSkinTexture();
        if (previewSkinTexture == null) {
            return PlayerSkinBuilderSkinResolver.withoutOverride(() -> ((class_746)client.field_1724).method_52814());
        }
        class_8685 fallback = PlayerSkinBuilderSkinResolver.withoutOverride(() -> ((class_746)client.field_1724).method_52814());
        return CustomPlayerSkinResolver.resolve(client, previewSkinTexture, option.smallArms(), fallback, "character_preview");
    }

    private static class_8685 resolveNpcSkinTextures(class_310 client, CharacterSelectionOption option, NpcAppearanceProfile appearance) {
        if (appearance != null && appearance.isFemale()) {
            if (option != null && option.kingdomId() != null && "wildling".equals(option.kingdomId().method_12832())) {
                return CustomPlayerSkinResolver.resolve(client, WILDLING_FEMALE_TEXTURE, true, client.field_1724.method_52814(), "wildling_house_npc_preview");
            }
            String clothesPath = option == null ? "" : NpcAppearanceProfile.resolveClothesTexturePath(option.kingdomId(), appearance.clothesVariant());
            String skinPath = NpcAppearanceProfile.resolveSkinTexturePath(appearance.skinVariant());
            String hairPath = NpcAppearanceProfile.resolveHairTexturePath(appearance.hairVariant());
            class_8685 fallback = PlayerSkinBuilderSkinResolver.withoutOverride(() -> ((class_746)client.field_1724).method_52814());
            if (!(clothesPath.isBlank() || skinPath.isBlank() || hairPath.isBlank())) {
                String compositeKey = String.valueOf(option.kingdomId()) + "|" + appearance.gender() + "|" + appearance.skinVariant() + "|" + appearance.hairVariant() + "|" + appearance.clothesVariant() + "|house_preview";
                return CustomPlayerSkinResolver.resolveComposite(client, compositeKey, true, fallback, "house_npc_preview", () -> CharacterPreviewSkinResolver.composeFemaleSkin(client, skinPath, clothesPath, hairPath));
            }
        } else if (appearance != null && appearance.isAssigned() && option != null) {
            String clothesPath = NpcAppearanceProfile.resolveMaleClothesTexturePath(option.kingdomId(), appearance.clothesVariant());
            String facePath = NpcAppearanceProfile.resolveMaleFaceTexturePath(appearance.skinVariant(), appearance.hairVariant());
            class_8685 fallback = PlayerSkinBuilderSkinResolver.withoutOverride(() -> ((class_746)client.field_1724).method_52814());
            if (!clothesPath.isBlank() && !facePath.isBlank()) {
                String compositeKey = String.valueOf(option.kingdomId()) + "|" + appearance.gender() + "|" + appearance.skinVariant() + "|" + appearance.hairVariant() + "|" + appearance.clothesVariant() + "|house_preview";
                return CustomPlayerSkinResolver.resolveComposite(client, compositeKey, false, fallback, "house_npc_preview", () -> CharacterPreviewSkinResolver.composeMaleSkin(client, clothesPath, facePath));
            }
        }
        return CharacterPreviewSkinResolver.resolveSkinTextures(client, option);
    }

    /*
     * Exception decompiling
     */
    private static class_1011 composeFemaleSkin(class_310 client, String skinPath, String clothesPath, String hairPath) throws IOException {
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

    private static class_1011 composeMaleSkin(class_310 client, String clothesPath, String facePath) throws IOException {
        try (class_1011 clothes = CharacterPreviewSkinResolver.read(client, class_2960.method_60655((String)"agob", (String)clothesPath));){
            class_1011 class_10112;
            block12: {
                class_1011 face = CharacterPreviewSkinResolver.read(client, class_2960.method_60655((String)"agob", (String)facePath));
                try {
                    class_1011 output = new class_1011(clothes.method_4307(), clothes.method_4323(), true);
                    CharacterPreviewSkinResolver.copy(output, clothes);
                    CharacterPreviewSkinResolver.blend(output, face);
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
                target.method_4305(x, y, CharacterPreviewSkinResolver.alphaBlend(target.method_4315(x, y), overlay.method_4315(x, y)));
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
        int outRed = CharacterPreviewSkinResolver.blendChannel(base >> 16 & 0xFF, overlay >> 16 & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outGreen = CharacterPreviewSkinResolver.blendChannel(base >> 8 & 0xFF, overlay >> 8 & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outBlue = CharacterPreviewSkinResolver.blendChannel(base & 0xFF, overlay & 0xFF, baseAlpha, overlayAlpha, outAlpha);
        int outAlphaInt = Math.min(255, Math.max(0, Math.round(outAlpha * 255.0f)));
        return outAlphaInt << 24 | outRed << 16 | outGreen << 8 | outBlue;
    }

    private static int blendChannel(int base, int overlay, float baseAlpha, float overlayAlpha, float outAlpha) {
        float value = ((float)overlay * overlayAlpha + (float)base * baseAlpha * (1.0f - overlayAlpha)) / outAlpha;
        return Math.min(255, Math.max(0, Math.round(value)));
    }

    private static void applyEquipment(CharacterPreviewPlayerEntity preview, CharacterSelectionOption option) {
        class_1792[] houseArmor = GotKingdomArmor.armorForId(option.houseArmorId());
        if (houseArmor != null) {
            preview.method_5673(class_1304.field_6169, new class_1799((class_1935)houseArmor[0]));
            preview.method_5673(class_1304.field_6174, new class_1799((class_1935)houseArmor[1]));
            preview.method_5673(class_1304.field_6172, new class_1799((class_1935)houseArmor[2]));
            preview.method_5673(class_1304.field_6166, new class_1799((class_1935)houseArmor[3]));
        } else {
            CharacterPreviewSkinResolver.equip(preview, class_1304.field_6169, option.equipment().head());
            CharacterPreviewSkinResolver.equip(preview, class_1304.field_6174, option.equipment().chest());
            CharacterPreviewSkinResolver.equip(preview, class_1304.field_6172, option.equipment().legs());
            CharacterPreviewSkinResolver.equip(preview, class_1304.field_6166, option.equipment().feet());
        }
        CharacterPreviewSkinResolver.equip(preview, class_1304.field_6173, option.equipment().mainHand());
        CharacterPreviewSkinResolver.equip(preview, class_1304.field_6171, option.equipment().offHand());
    }

    private static void equip(CharacterPreviewPlayerEntity preview, class_1304 slot, String itemId) {
        if (itemId == null || itemId.isBlank()) {
            preview.method_5673(slot, class_1799.field_8037);
            return;
        }
        class_2960 id = class_2960.method_12829((String)itemId);
        if (id == null) {
            preview.method_5673(slot, class_1799.field_8037);
            return;
        }
        class_1792 item = (class_1792)class_7923.field_41178.method_10223(id);
        preview.method_5673(slot, item == null ? class_1799.field_8037 : new class_1799((class_1935)item));
    }
}

