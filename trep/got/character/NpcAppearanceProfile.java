/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2960
 */
package trep.got.character;

import java.io.InputStream;
import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_2487;
import net.minecraft.class_2960;

public record NpcAppearanceProfile(String gender, int skinVariant, int hairVariant, int clothesVariant) {
    private static final String FEMALE_TEXTURE_PREFIX = "textures/entity/npc/female/";
    private static final String FEMALE_SKIN_FOLDER = "textures/entity/npc/female/skin/";
    private static final String FEMALE_HAIR_FOLDER = "textures/entity/npc/female/hair/";
    private static final String FEMALE_CLOTHES_FOLDER = "textures/entity/npc/female/clothes/";
    private static final String MALE_TEXTURE_PREFIX = "textures/entity/npc/male/";
    private static final String MALE_CLOTHES_FOLDER = "textures/entity/npc/male/clothes/";
    private static final String MALE_FACE_FOLDER = "textures/entity/npc/male/face/";
    private static final List<String> MALE_FACE_TEXTURES = List.of("peasant_astapor.png", "peasant_blackfyre.png", "peasant_braavos.png", "peasant_crownlands.png", "peasant_dorne.png", "peasant_golden_company.png", "peasant_ironislands.png", "peasant_meereen.png", "peasant_myr.png", "peasant_north.png", "peasant_pentos.png", "peasant_qarth.png", "peasant_reach.png", "peasant_riverlands.png", "peasant_stormlands.png", "peasant_targaryen.png", "peasant_tyrosh.png", "peasant_vale.png", "peasant_volantis.png", "peasant_westerlands.png", "peasant_wildling.png", "peasant_yunkai.png", "wildling.png");
    public static final String MALE = "Male";
    public static final String FEMALE = "Female";
    public static final NpcAppearanceProfile EMPTY = new NpcAppearanceProfile("", 1, 1, 0);
    public static final NpcAppearanceProfile DEFAULT_MALE = new NpcAppearanceProfile("Male", 1, 1, 0);
    private static final String RESOURCE_ROOT = "assets/agob/";
    private static final Map<String, List<Integer>> CLOTHES_VARIANTS = new ConcurrentHashMap<String, List<Integer>>();
    private static final Map<String, List<Integer>> MALE_CLOTHES_VARIANTS = new ConcurrentHashMap<String, List<Integer>>();
    private static final List<String> AVAILABLE_MALE_FACE_TEXTURES = NpcAppearanceProfile.findMaleFaceTextures();

    public NpcAppearanceProfile {
        gender = NpcAppearanceProfile.normalizeGender(gender);
        skinVariant = NpcAppearanceProfile.clampLayerVariant(skinVariant);
        hairVariant = NpcAppearanceProfile.clampLayerVariant(hairVariant);
        clothesVariant = NpcAppearanceProfile.clampClothesVariant(clothesVariant);
    }

    public boolean isAssigned() {
        return !this.gender.isBlank();
    }

    public boolean isFemale() {
        return FEMALE.equals(this.gender);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("gender", this.gender);
        nbt.method_10569("skin_variant", this.skinVariant);
        nbt.method_10569("hair_variant", this.hairVariant);
        nbt.method_10569("clothes_variant", this.clothesVariant);
        return nbt;
    }

    public static NpcAppearanceProfile fromNbt(class_2487 nbt) {
        if (nbt == null || nbt.method_33133()) {
            return EMPTY;
        }
        return new NpcAppearanceProfile(nbt.method_10558("gender"), nbt.method_10573("skin_variant", 99) ? nbt.method_10550("skin_variant") : 1, nbt.method_10573("hair_variant", 99) ? nbt.method_10550("hair_variant") : 1, nbt.method_10573("clothes_variant", 99) ? nbt.method_10550("clothes_variant") : 0);
    }

    public static NpcAppearanceProfile generate(class_2960 kingdomId, String seedKey) {
        Random random = new Random(NpcAppearanceProfile.stableSeed(seedKey));
        String gender = random.nextBoolean() ? FEMALE : MALE;
        int skinVariant = 1 + random.nextInt(5);
        int hairVariant = 1 + random.nextInt(5);
        List<Integer> clothesOptions = FEMALE.equals(gender) ? NpcAppearanceProfile.availableClothesVariants(kingdomId) : NpcAppearanceProfile.availableMaleClothesVariants(kingdomId);
        int clothesVariant = clothesOptions.get(random.nextInt(clothesOptions.size()));
        return new NpcAppearanceProfile(gender, skinVariant, hairVariant, clothesVariant);
    }

    public static List<Integer> availableClothesVariants(class_2960 kingdomId) {
        String kingdomKey = NpcAppearanceProfile.resolveKingdomClothesKey(kingdomId);
        if (kingdomKey.isBlank()) {
            return List.of(Integer.valueOf(0));
        }
        return CLOTHES_VARIANTS.computeIfAbsent(kingdomKey, NpcAppearanceProfile::findClothesVariants);
    }

    public static String resolveClothesTexturePath(class_2960 kingdomId, int clothesVariant) {
        String basePath;
        String kingdomKey = NpcAppearanceProfile.resolveKingdomClothesKey(kingdomId);
        if (kingdomKey.isBlank()) {
            return "";
        }
        if (clothesVariant > 0) {
            String variantPath = "textures/entity/npc/female/clothes/clothes_" + kingdomKey + "_" + clothesVariant + ".png";
            if (NpcAppearanceProfile.resourceExists(variantPath)) {
                return variantPath;
            }
            String legacyVariantPath = "textures/entity/npc/female/clothes_" + kingdomKey + "_" + clothesVariant + ".png";
            if (NpcAppearanceProfile.resourceExists(legacyVariantPath)) {
                return legacyVariantPath;
            }
        }
        if (NpcAppearanceProfile.resourceExists(basePath = "textures/entity/npc/female/clothes/clothes_" + kingdomKey + ".png")) {
            return basePath;
        }
        String namedBasePath = FEMALE_CLOTHES_FOLDER + kingdomKey + ".png";
        if (NpcAppearanceProfile.resourceExists(namedBasePath)) {
            return namedBasePath;
        }
        String legacyBasePath = "textures/entity/npc/female/clothes_" + kingdomKey + ".png";
        if (NpcAppearanceProfile.resourceExists(legacyBasePath)) {
            return legacyBasePath;
        }
        String legacyNamedBasePath = FEMALE_TEXTURE_PREFIX + kingdomKey + ".png";
        return NpcAppearanceProfile.resourceExists(legacyNamedBasePath) ? legacyNamedBasePath : "";
    }

    public static List<Integer> availableMaleClothesVariants(class_2960 kingdomId) {
        String kingdomKey = NpcAppearanceProfile.resolveKingdomClothesKey(kingdomId);
        if (kingdomKey.isBlank()) {
            return List.of(Integer.valueOf(0));
        }
        return MALE_CLOTHES_VARIANTS.computeIfAbsent(kingdomKey, NpcAppearanceProfile::findMaleClothesVariants);
    }

    public static String resolveMaleClothesTexturePath(class_2960 kingdomId, int clothesVariant) {
        String variantPath;
        String wildlingPath;
        String kingdomKey = NpcAppearanceProfile.resolveKingdomClothesKey(kingdomId);
        if (kingdomKey.isBlank()) {
            return "";
        }
        if (kingdomId != null && "wildling".equals(kingdomId.method_12832()) && NpcAppearanceProfile.resourceExists(wildlingPath = "textures/entity/npc/male/clothes/wildling.png")) {
            return wildlingPath;
        }
        if (clothesVariant > 0 && NpcAppearanceProfile.resourceExists(variantPath = "textures/entity/npc/male/clothes/clothes_" + kingdomKey + "_" + clothesVariant + ".png")) {
            return variantPath;
        }
        String basePath = "textures/entity/npc/male/clothes/clothes_" + kingdomKey + ".png";
        if (NpcAppearanceProfile.resourceExists(basePath)) {
            return basePath;
        }
        String namedBasePath = MALE_CLOTHES_FOLDER + kingdomKey + ".png";
        return NpcAppearanceProfile.resourceExists(namedBasePath) ? namedBasePath : "";
    }

    public static String resolveMaleFaceTexturePath(int skinVariant, int hairVariant) {
        if (AVAILABLE_MALE_FACE_TEXTURES.isEmpty()) {
            return "";
        }
        int index = Math.floorMod((NpcAppearanceProfile.clampLayerVariant(skinVariant) - 1) * 5 + (NpcAppearanceProfile.clampLayerVariant(hairVariant) - 1), AVAILABLE_MALE_FACE_TEXTURES.size());
        return AVAILABLE_MALE_FACE_TEXTURES.get(index);
    }

    public static String resolveSkinTexturePath(int skinVariant) {
        String folderPath = "textures/entity/npc/female/skin/skin_" + NpcAppearanceProfile.clampLayerVariant(skinVariant) + ".png";
        if (NpcAppearanceProfile.resourceExists(folderPath)) {
            return folderPath;
        }
        String legacyPath = "textures/entity/npc/female/skin_" + NpcAppearanceProfile.clampLayerVariant(skinVariant) + ".png";
        return NpcAppearanceProfile.resourceExists(legacyPath) ? legacyPath : "";
    }

    public static String resolveHairTexturePath(int hairVariant) {
        String folderPath = "textures/entity/npc/female/hair/hair_" + NpcAppearanceProfile.clampLayerVariant(hairVariant) + ".png";
        if (NpcAppearanceProfile.resourceExists(folderPath)) {
            return folderPath;
        }
        String legacyPath = "textures/entity/npc/female/hair_" + NpcAppearanceProfile.clampLayerVariant(hairVariant) + ".png";
        return NpcAppearanceProfile.resourceExists(legacyPath) ? legacyPath : "";
    }

    public static String resolveKingdomClothesKey(class_2960 kingdomId) {
        if (kingdomId == null) {
            return "";
        }
        return switch (kingdomId.method_12832()) {
            case "the_north" -> "north";
            case "the_reach" -> "reach";
            case "the_westerlands" -> "westerlands";
            case "the_riverlands" -> "riverlands";
            case "the_crownlands" -> "crownlands";
            case "the_stormlands" -> "stormlands";
            case "the_vale" -> "vale";
            case "iron_islands" -> "ironislands";
            case "the_nights_watch", "wildling" -> "wildling";
            default -> kingdomId.method_12832();
        };
    }

    private static List<Integer> findClothesVariants(String kingdomKey) {
        ArrayList<Integer> variants = new ArrayList<Integer>();
        if (NpcAppearanceProfile.resourceExists("textures/entity/npc/female/clothes/clothes_" + kingdomKey + ".png") || NpcAppearanceProfile.resourceExists(FEMALE_CLOTHES_FOLDER + kingdomKey + ".png") || NpcAppearanceProfile.resourceExists("textures/entity/npc/female/clothes_" + kingdomKey + ".png") || NpcAppearanceProfile.resourceExists(FEMALE_TEXTURE_PREFIX + kingdomKey + ".png")) {
            variants.add(0);
        }
        for (int variant = 1; variant <= 2; ++variant) {
            if (!NpcAppearanceProfile.resourceExists("textures/entity/npc/female/clothes/clothes_" + kingdomKey + "_" + variant + ".png") && !NpcAppearanceProfile.resourceExists("textures/entity/npc/female/clothes_" + kingdomKey + "_" + variant + ".png")) continue;
            variants.add(variant);
        }
        return variants.isEmpty() ? List.of(Integer.valueOf(0)) : List.copyOf(variants);
    }

    private static List<Integer> findMaleClothesVariants(String kingdomKey) {
        ArrayList<Integer> variants = new ArrayList<Integer>();
        if (NpcAppearanceProfile.resourceExists("textures/entity/npc/male/clothes/clothes_" + kingdomKey + ".png") || NpcAppearanceProfile.resourceExists(MALE_CLOTHES_FOLDER + kingdomKey + ".png")) {
            variants.add(0);
        }
        for (int variant = 1; variant <= 2; ++variant) {
            if (!NpcAppearanceProfile.resourceExists("textures/entity/npc/male/clothes/clothes_" + kingdomKey + "_" + variant + ".png")) continue;
            variants.add(variant);
        }
        return variants.isEmpty() ? List.of(Integer.valueOf(0)) : List.copyOf(variants);
    }

    private static List<String> findMaleFaceTextures() {
        ArrayList<CallSite> textures = new ArrayList<CallSite>();
        for (String fileName : MALE_FACE_TEXTURES) {
            String path = MALE_FACE_FOLDER + fileName;
            if (!NpcAppearanceProfile.resourceExists(path)) continue;
            textures.add((CallSite)((Object)path));
        }
        return List.copyOf(textures);
    }

    private static boolean resourceExists(String path) {
        boolean bl;
        block8: {
            InputStream stream = NpcAppearanceProfile.class.getClassLoader().getResourceAsStream(RESOURCE_ROOT + path);
            try {
                boolean bl2 = bl = stream != null;
                if (stream == null) break block8;
            }
            catch (Throwable throwable) {
                try {
                    if (stream != null) {
                        try {
                            stream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (Exception ignored) {
                    return false;
                }
            }
            stream.close();
        }
        return bl;
    }

    private static String normalizeGender(String gender) {
        if (gender == null || gender.isBlank()) {
            return "";
        }
        return "female".equals(gender.trim().toLowerCase(Locale.ROOT)) ? FEMALE : MALE;
    }

    private static int clampLayerVariant(int variant) {
        return Math.max(1, Math.min(5, variant));
    }

    private static int clampClothesVariant(int variant) {
        return Math.max(0, Math.min(2, variant));
    }

    private static long stableSeed(String key) {
        String value = key == null ? "" : key;
        long seed = 1469598103934665603L;
        for (int index = 0; index < value.length(); ++index) {
            seed ^= (long)value.charAt(index);
            seed *= 1099511628211L;
        }
        return seed;
    }
}

