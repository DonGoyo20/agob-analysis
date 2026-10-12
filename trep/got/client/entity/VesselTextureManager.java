/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 *  net.minecraft.class_1043
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3298
 */
package trep.got.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3298;
import trep.got.entity.VesselMaterialProfile;
import trep.got.entity.VesselVariant;

@Environment(value=EnvType.CLIENT)
public final class VesselTextureManager {
    private static final Map<TextureKey, class_2960> CACHE = new HashMap<TextureKey, class_2960>();

    private VesselTextureManager() {
    }

    public static class_2960 getTextureId(VesselVariant variant, VesselMaterialProfile profile) {
        TextureKey key = new TextureKey(variant, profile.plankWoodFamily().plankId().toString(), profile.logWoodFamily().plankId().toString(), profile.woolFamily().blockId().toString());
        return CACHE.computeIfAbsent(key, VesselTextureManager::createTexture);
    }

    private static class_2960 createTexture(TextureKey key) {
        class_310 client = class_310.method_1551();
        try {
            class_1011 image = VesselTextureManager.composeTexture(key.variant, VesselMaterialProfile.fromIds(key.plankWoodId, key.logWoodId, key.woolId));
            return client.method_1531().method_4617("vessel/" + key.variant.id() + "/" + VesselTextureManager.sanitize(key.plankWoodId) + "_" + VesselTextureManager.sanitize(key.logWoodId) + "_" + VesselTextureManager.sanitize(key.woolId), new class_1043(image));
        }
        catch (IOException exception) {
            throw new IllegalStateException("Failed to build vessel texture for " + key.variant.id(), exception);
        }
    }

    /*
     * Exception decompiling
     */
    private static class_1011 composeTexture(VesselVariant variant, VesselMaterialProfile profile) throws IOException {
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

    private static void paintGalley(class_1011 output, class_1011 planks, class_1011 strippedLog, class_1011 wool) {
        for (int column = 0; column < 8; ++column) {
            int x = column * 16;
            boolean aftMaterial = column >= 6;
            VesselTextureManager.tile(output, aftMaterial ? strippedLog : planks, x, 0, 16, 64);
            VesselTextureManager.tile(output, aftMaterial ? wool : planks, x, 64, 16, 64);
        }
    }

    private static void tile(class_1011 output, class_1011 source, int destX, int destY, int width, int height) {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int sourceX = Math.floorMod(x, source.method_4307());
                int sourceY = Math.floorMod(y, source.method_4323());
                output.method_4305(destX + x, destY + y, source.method_4315(sourceX, sourceY));
            }
        }
    }

    private static class_1011 readTexture(class_2960 id) throws IOException {
        class_3298 resource = (class_3298)class_310.method_1551().method_1478().method_14486(id).orElseThrow(() -> new IOException("Missing texture " + String.valueOf(id)));
        try (InputStream stream = resource.method_14482();){
            class_1011 class_10112 = class_1011.method_4309((InputStream)stream);
            return class_10112;
        }
    }

    private static String sanitize(String id) {
        return id.replace(':', '_').replace('/', '_');
    }

    @Environment(value=EnvType.CLIENT)
    private record TextureKey(VesselVariant variant, String plankWoodId, String logWoodId, String woolId) {
    }
}

