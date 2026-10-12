/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1767
 *  net.minecraft.class_2487
 *  net.minecraft.class_9129
 */
package trep.got.character;

import java.util.Locale;
import net.minecraft.class_1767;
import net.minecraft.class_2487;
import net.minecraft.class_9129;

public record PlayerSkinBuilderProfile(boolean npcBaseSkin, String gender, int skinTone, String faceId, String hairId, String shirtId, String pantsId, String shirtDyeId, String pantsDyeId) {
    public static final String NO_DYE = "none";
    public static final PlayerSkinBuilderProfile DEFAULT = new PlayerSkinBuilderProfile(false, "Male", 1, "", "", "", "", "none", "none");

    public PlayerSkinBuilderProfile(boolean npcBaseSkin, String gender, int skinTone, String faceId, String hairId, String shirtId, String pantsId) {
        this(npcBaseSkin, gender, skinTone, faceId, hairId, shirtId, pantsId, NO_DYE, NO_DYE);
    }

    public PlayerSkinBuilderProfile {
        gender = PlayerSkinBuilderProfile.normalizeGender(gender);
        skinTone = PlayerSkinBuilderProfile.clampSkinTone(skinTone);
        faceId = PlayerSkinBuilderProfile.sanitizeId(faceId);
        hairId = PlayerSkinBuilderProfile.sanitizeId(hairId);
        shirtId = PlayerSkinBuilderProfile.sanitizeId(shirtId);
        pantsId = PlayerSkinBuilderProfile.sanitizeId(pantsId);
        shirtDyeId = PlayerSkinBuilderProfile.sanitizeDyeId(shirtDyeId);
        pantsDyeId = PlayerSkinBuilderProfile.sanitizeDyeId(pantsDyeId);
        if (!npcBaseSkin) {
            faceId = "";
        }
    }

    public boolean isMale() {
        return "Male".equals(this.gender);
    }

    public boolean hasCustomLayer() {
        return this.npcBaseSkin || !this.hairId.isBlank() || !this.shirtId.isBlank() || !this.pantsId.isBlank();
    }

    public PlayerSkinBuilderProfile withNpcBaseSkin(boolean npcBaseSkin) {
        return new PlayerSkinBuilderProfile(npcBaseSkin, this.gender, this.skinTone, this.faceId, this.hairId, this.shirtId, this.pantsId, this.shirtDyeId, this.pantsDyeId);
    }

    public PlayerSkinBuilderProfile withGender(String gender) {
        return new PlayerSkinBuilderProfile(this.npcBaseSkin, gender, this.skinTone, "", "", this.shirtId, this.pantsId, this.shirtDyeId, this.pantsDyeId);
    }

    public PlayerSkinBuilderProfile withSkinTone(int skinTone) {
        return new PlayerSkinBuilderProfile(this.npcBaseSkin, this.gender, skinTone, this.faceId, this.hairId, this.shirtId, this.pantsId, this.shirtDyeId, this.pantsDyeId);
    }

    public PlayerSkinBuilderProfile withFaceId(String faceId) {
        return new PlayerSkinBuilderProfile(this.npcBaseSkin, this.gender, this.skinTone, faceId, this.hairId, this.shirtId, this.pantsId, this.shirtDyeId, this.pantsDyeId);
    }

    public PlayerSkinBuilderProfile withHairId(String hairId) {
        return new PlayerSkinBuilderProfile(this.npcBaseSkin, this.gender, this.skinTone, this.faceId, hairId, this.shirtId, this.pantsId, this.shirtDyeId, this.pantsDyeId);
    }

    public PlayerSkinBuilderProfile withShirtId(String shirtId) {
        return this.withShirt(shirtId, NO_DYE);
    }

    public PlayerSkinBuilderProfile withPantsId(String pantsId) {
        return this.withPants(pantsId, NO_DYE);
    }

    public PlayerSkinBuilderProfile withShirt(String shirtId, String dyeId) {
        return new PlayerSkinBuilderProfile(this.npcBaseSkin, this.gender, this.skinTone, this.faceId, this.hairId, shirtId, this.pantsId, dyeId, this.pantsDyeId);
    }

    public PlayerSkinBuilderProfile withPants(String pantsId, String dyeId) {
        return new PlayerSkinBuilderProfile(this.npcBaseSkin, this.gender, this.skinTone, this.faceId, this.hairId, this.shirtId, pantsId, this.shirtDyeId, dyeId);
    }

    public PlayerSkinBuilderProfile withShirtDyeId(String dyeId) {
        return new PlayerSkinBuilderProfile(this.npcBaseSkin, this.gender, this.skinTone, this.faceId, this.hairId, this.shirtId, this.pantsId, dyeId, this.pantsDyeId);
    }

    public PlayerSkinBuilderProfile withPantsDyeId(String dyeId) {
        return new PlayerSkinBuilderProfile(this.npcBaseSkin, this.gender, this.skinTone, this.faceId, this.hairId, this.shirtId, this.pantsId, this.shirtDyeId, dyeId);
    }

    public void write(class_9129 buf) {
        buf.method_52964(this.npcBaseSkin);
        buf.method_10814(this.gender);
        buf.method_10804(this.skinTone);
        buf.method_10814(this.faceId);
        buf.method_10814(this.hairId);
        buf.method_10814(this.shirtId);
        buf.method_10814(this.pantsId);
        buf.method_10814(this.shirtDyeId);
        buf.method_10814(this.pantsDyeId);
    }

    public static PlayerSkinBuilderProfile read(class_9129 buf) {
        return new PlayerSkinBuilderProfile(buf.readBoolean(), buf.method_19772(), buf.method_10816(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772());
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10556("npc_base_skin", this.npcBaseSkin);
        nbt.method_10582("gender", this.gender);
        nbt.method_10569("skin_tone", this.skinTone);
        nbt.method_10582("face_id", this.faceId);
        nbt.method_10582("hair_id", this.hairId);
        nbt.method_10582("shirt_id", this.shirtId);
        nbt.method_10582("pants_id", this.pantsId);
        nbt.method_10582("shirt_dye_id", this.shirtDyeId);
        nbt.method_10582("pants_dye_id", this.pantsDyeId);
        return nbt;
    }

    public static PlayerSkinBuilderProfile fromNbt(class_2487 nbt) {
        if (nbt == null || nbt.method_33133()) {
            return DEFAULT;
        }
        return new PlayerSkinBuilderProfile(nbt.method_10577("npc_base_skin"), nbt.method_10558("gender"), nbt.method_10573("skin_tone", 99) ? nbt.method_10550("skin_tone") : 1, nbt.method_10558("face_id"), nbt.method_10558("hair_id"), nbt.method_10558("shirt_id"), nbt.method_10558("pants_id"), nbt.method_10558("shirt_dye_id"), nbt.method_10558("pants_dye_id"));
    }

    private static String normalizeGender(String gender) {
        if (gender == null || gender.isBlank()) {
            return "Male";
        }
        return "female".equals(gender.trim().toLowerCase(Locale.ROOT)) ? "Female" : "Male";
    }

    private static int clampSkinTone(int skinTone) {
        return Math.max(1, skinTone);
    }

    private static String sanitizeId(String id) {
        if (id == null) {
            return "";
        }
        String value = id.trim().toLowerCase(Locale.ROOT);
        if (value.equals(NO_DYE)) {
            return "";
        }
        int slashIndex = (value = value.replace('\\', '/').replace(".png", "")).lastIndexOf(47);
        if (slashIndex >= 0) {
            value = value.substring(slashIndex + 1);
        }
        return PlayerSkinBuilderProfile.migrateLegacyClothingId(value.replace("..", ""));
    }

    private static String sanitizeDyeId(String id) {
        if (id == null || id.isBlank()) {
            return NO_DYE;
        }
        String value = id.trim().toLowerCase(Locale.ROOT);
        if (value.equals(NO_DYE)) {
            return NO_DYE;
        }
        return class_1767.method_7793((String)value, null) == null ? NO_DYE : value;
    }

    private static String migrateLegacyClothingId(String id) {
        return switch (id) {
            case "clothes_astapor" -> "astapori_linen";
            case "clothes_blackfyre" -> "blackfyre_court_attire";
            case "clothes_braavos" -> "braavosi_water_dancer_garb";
            case "clothes_crownlands" -> "crownlands_court_dress";
            case "clothes_dorne" -> "dornish_silks";
            case "clothes_dorne_2" -> "sunspear_riding_garb";
            case "clothes_golden_company" -> "golden_company_uniform";
            case "clothes_ironislands" -> "ironborn_salt_wools";
            case "clothes_meereen" -> "meereenese_linen";
            case "clothes_myr" -> "myrish_merchant_silks";
            case "clothes_north" -> "northern_woolens";
            case "clothes_north_2" -> "winterfell_riding_leathers";
            case "clothes_pentos" -> "pentoshi_merchant_robes";
            case "clothes_qarth" -> "qartheen_silk";
            case "clothes_reach" -> "reachman_court_attire";
            case "clothes_reach_2" -> "highgarden_field_dress";
            case "clothes_riverlands" -> "riverlander_wool_garb";
            case "clothes_stormlands" -> "stormlander_riding_gear";
            case "clothes_targaryen" -> "targaryen_court_attire";
            case "clothes_tyrosh" -> "tyroshi_dyed_silks";
            case "clothes_vale" -> "valeman_wool_garb";
            case "clothes_vale_2" -> "eyrie_riding_leathers";
            case "clothes_volantis" -> "volantene_noble_robes";
            case "clothes_westerlands" -> "westerlands_court_dress";
            case "clothes_westerlands_2" -> "lannisport_riding_gear";
            case "clothes_wildling" -> "free_folk_furs";
            case "clothes_yunkai" -> "yunkish_linen";
            case "essosi" -> "essosi_traveler_garb";
            default -> id;
        };
    }
}

