/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_2960
 */
package trep.got.registry;

import java.util.Locale;
import net.minecraft.class_1792;
import net.minecraft.class_2960;
import trep.got.registry.GotItems;
import trep.got.registry.GotKingdoms;

public final class GotKingdomArmor {
    private GotKingdomArmor() {
    }

    public static class_1792[] armorForId(String armorId) {
        class_1792[] class_1792Array;
        if (armorId == null || armorId.isBlank()) {
            return null;
        }
        String normalized = armorId.trim().toLowerCase(Locale.ROOT);
        if (normalized.endsWith("_armor")) {
            normalized = normalized.substring(0, normalized.length() - "_armor".length());
        }
        switch (normalized) {
            case "nights_watch": 
            case "the_nights_watch": {
                class_1792[] class_1792Array2 = new class_1792[4];
                class_1792Array2[0] = GotItems.THE_NIGHTS_WATCH_HELMET;
                class_1792Array2[1] = GotItems.THE_NIGHTS_WATCH_CHESTPLATE;
                class_1792Array2[2] = GotItems.THE_NIGHTS_WATCH_LEGGINGS;
                class_1792Array = class_1792Array2;
                class_1792Array2[3] = GotItems.THE_NIGHTS_WATCH_BOOTS;
                break;
            }
            case "wildling": {
                class_1792[] class_1792Array3 = new class_1792[4];
                class_1792Array3[0] = GotItems.WILDLING_HELMET;
                class_1792Array3[1] = GotItems.WILDLING_CHESTPLATE;
                class_1792Array3[2] = GotItems.WILDLING_LEGGINGS;
                class_1792Array = class_1792Array3;
                class_1792Array3[3] = GotItems.WILDLING_BOOTS;
                break;
            }
            case "lannister": 
            case "the_westerlands": {
                class_1792[] class_1792Array4 = new class_1792[4];
                class_1792Array4[0] = GotItems.LANNISTER_HELMET;
                class_1792Array4[1] = GotItems.LANNISTER_CHESTPLATE;
                class_1792Array4[2] = GotItems.LANNISTER_LEGGINGS;
                class_1792Array = class_1792Array4;
                class_1792Array4[3] = GotItems.LANNISTER_BOOTS;
                break;
            }
            case "clegane": {
                class_1792[] class_1792Array5 = new class_1792[4];
                class_1792Array5[0] = GotItems.CLEGANE_HELMET;
                class_1792Array5[1] = GotItems.CLEGANE_CHESTPLATE;
                class_1792Array5[2] = GotItems.CLEGANE_LEGGINGS;
                class_1792Array = class_1792Array5;
                class_1792Array5[3] = GotItems.CLEGANE_BOOTS;
                break;
            }
            case "marbrand": {
                class_1792[] class_1792Array6 = new class_1792[4];
                class_1792Array6[0] = GotItems.MARBRAND_HELMET;
                class_1792Array6[1] = GotItems.MARBRAND_CHESTPLATE;
                class_1792Array6[2] = GotItems.MARBRAND_LEGGINGS;
                class_1792Array = class_1792Array6;
                class_1792Array6[3] = GotItems.MARBRAND_BOOTS;
                break;
            }
            case "arryn": 
            case "the_vale": {
                class_1792[] class_1792Array7 = new class_1792[4];
                class_1792Array7[0] = GotItems.ARRYN_HELMET;
                class_1792Array7[1] = GotItems.ARRYN_CHESTPLATE;
                class_1792Array7[2] = GotItems.ARRYN_LEGGINGS;
                class_1792Array = class_1792Array7;
                class_1792Array7[3] = GotItems.ARRYN_BOOTS;
                break;
            }
            case "martell": 
            case "dorne": {
                class_1792[] class_1792Array8 = new class_1792[4];
                class_1792Array8[0] = GotItems.MARTELL_HELMET;
                class_1792Array8[1] = GotItems.MARTELL_CHESTPLATE;
                class_1792Array8[2] = GotItems.MARTELL_LEGGINGS;
                class_1792Array = class_1792Array8;
                class_1792Array8[3] = GotItems.MARTELL_BOOTS;
                break;
            }
            case "dayne": {
                class_1792[] class_1792Array9 = new class_1792[4];
                class_1792Array9[0] = GotItems.DAYNE_HELMET;
                class_1792Array9[1] = GotItems.DAYNE_CHESTPLATE;
                class_1792Array9[2] = GotItems.DAYNE_LEGGINGS;
                class_1792Array = class_1792Array9;
                class_1792Array9[3] = GotItems.DAYNE_BOOTS;
                break;
            }
            case "yronwood": {
                class_1792[] class_1792Array10 = new class_1792[4];
                class_1792Array10[0] = GotItems.YRONWOOD_HELMET;
                class_1792Array10[1] = GotItems.YRONWOOD_CHESTPLATE;
                class_1792Array10[2] = GotItems.YRONWOOD_LEGGINGS;
                class_1792Array = class_1792Array10;
                class_1792Array10[3] = GotItems.YRONWOOD_BOOTS;
                break;
            }
            case "pyke": 
            case "iron_islands": {
                class_1792[] class_1792Array11 = new class_1792[4];
                class_1792Array11[0] = GotItems.PYKE_HELMET;
                class_1792Array11[1] = GotItems.PYKE_CHESTPLATE;
                class_1792Array11[2] = GotItems.PYKE_LEGGINGS;
                class_1792Array = class_1792Array11;
                class_1792Array11[3] = GotItems.PYKE_BOOTS;
                break;
            }
            case "harlaw": {
                class_1792[] class_1792Array12 = new class_1792[4];
                class_1792Array12[0] = GotItems.HARLAW_HELMET;
                class_1792Array12[1] = GotItems.HARLAW_CHESTPLATE;
                class_1792Array12[2] = GotItems.HARLAW_LEGGINGS;
                class_1792Array = class_1792Array12;
                class_1792Array12[3] = GotItems.HARLAW_BOOTS;
                break;
            }
            case "baratheon": 
            case "the_stormlands": {
                class_1792[] class_1792Array13 = new class_1792[4];
                class_1792Array13[0] = GotItems.BARATHEON_HELMET;
                class_1792Array13[1] = GotItems.BARATHEON_CHESTPLATE;
                class_1792Array13[2] = GotItems.BARATHEON_LEGGINGS;
                class_1792Array = class_1792Array13;
                class_1792Array13[3] = GotItems.BARATHEON_BOOTS;
                break;
            }
            case "tyrell": 
            case "the_reach": {
                class_1792[] class_1792Array14 = new class_1792[4];
                class_1792Array14[0] = GotItems.TYRELL_HELMET;
                class_1792Array14[1] = GotItems.TYRELL_CHESTPLATE;
                class_1792Array14[2] = GotItems.TYRELL_LEGGINGS;
                class_1792Array = class_1792Array14;
                class_1792Array14[3] = GotItems.TYRELL_BOOTS;
                break;
            }
            case "hightower": {
                class_1792[] class_1792Array15 = new class_1792[4];
                class_1792Array15[0] = GotItems.HIGHTOWER_HELMET;
                class_1792Array15[1] = GotItems.HIGHTOWER_CHESTPLATE;
                class_1792Array15[2] = GotItems.HIGHTOWER_LEGGINGS;
                class_1792Array = class_1792Array15;
                class_1792Array15[3] = GotItems.HIGHTOWER_BOOTS;
                break;
            }
            case "dragonstone": 
            case "the_crownlands": {
                class_1792[] class_1792Array16 = new class_1792[4];
                class_1792Array16[0] = GotItems.DRAGONSTONE_HELMET;
                class_1792Array16[1] = GotItems.DRAGONSTONE_CHESTPLATE;
                class_1792Array16[2] = GotItems.DRAGONSTONE_LEGGINGS;
                class_1792Array = class_1792Array16;
                class_1792Array16[3] = GotItems.DRAGONSTONE_BOOTS;
                break;
            }
            case "velaryon": {
                class_1792[] class_1792Array17 = new class_1792[4];
                class_1792Array17[0] = GotItems.VELARYON_HELMET;
                class_1792Array17[1] = GotItems.VELARYON_CHESTPLATE;
                class_1792Array17[2] = GotItems.VELARYON_LEGGINGS;
                class_1792Array = class_1792Array17;
                class_1792Array17[3] = GotItems.VELARYON_BOOTS;
                break;
            }
            case "goldcloak": {
                class_1792[] class_1792Array18 = new class_1792[4];
                class_1792Array18[0] = GotItems.GOLDCLOAK_HELMET;
                class_1792Array18[1] = GotItems.GOLDCLOAK_CHESTPLATE;
                class_1792Array18[2] = GotItems.GOLDCLOAK_LEGGINGS;
                class_1792Array = class_1792Array18;
                class_1792Array18[3] = GotItems.GOLDCLOAK_BOOTS;
                break;
            }
            case "tully": 
            case "the_riverlands": {
                class_1792[] class_1792Array19 = new class_1792[4];
                class_1792Array19[0] = GotItems.TULLY_HELMET;
                class_1792Array19[1] = GotItems.TULLY_CHESTPLATE;
                class_1792Array19[2] = GotItems.TULLY_LEGGINGS;
                class_1792Array = class_1792Array19;
                class_1792Array19[3] = GotItems.TULLY_BOOTS;
                break;
            }
            case "blackwood": {
                class_1792[] class_1792Array20 = new class_1792[4];
                class_1792Array20[0] = GotItems.BLACKWOOD_HELMET;
                class_1792Array20[1] = GotItems.BLACKWOOD_CHESTPLATE;
                class_1792Array20[2] = GotItems.BLACKWOOD_LEGGINGS;
                class_1792Array = class_1792Array20;
                class_1792Array20[3] = GotItems.BLACKWOOD_BOOTS;
                break;
            }
            case "stark": 
            case "the_north": {
                class_1792[] class_1792Array21 = new class_1792[4];
                class_1792Array21[0] = GotItems.STARK_HELMET;
                class_1792Array21[1] = GotItems.STARK_CHESTPLATE;
                class_1792Array21[2] = GotItems.STARK_LEGGINGS;
                class_1792Array = class_1792Array21;
                class_1792Array21[3] = GotItems.STARK_BOOTS;
                break;
            }
            case "karstark": {
                class_1792[] class_1792Array22 = new class_1792[4];
                class_1792Array22[0] = GotItems.KARSTARK_HELMET;
                class_1792Array22[1] = GotItems.KARSTARK_CHESTPLATE;
                class_1792Array22[2] = GotItems.KARSTARK_LEGGINGS;
                class_1792Array = class_1792Array22;
                class_1792Array22[3] = GotItems.KARSTARK_BOOTS;
                break;
            }
            case "targaryen": {
                class_1792[] class_1792Array23 = new class_1792[4];
                class_1792Array23[0] = GotItems.TARGARYEN_HELMET;
                class_1792Array23[1] = GotItems.TARGARYEN_CHESTPLATE;
                class_1792Array23[2] = GotItems.TARGARYEN_LEGGINGS;
                class_1792Array = class_1792Array23;
                class_1792Array23[3] = GotItems.TARGARYEN_BOOTS;
                break;
            }
            case "braavos": {
                class_1792[] class_1792Array24 = new class_1792[4];
                class_1792Array24[0] = GotItems.BRAAVOS_HELMET;
                class_1792Array24[1] = GotItems.BRAAVOS_CHESTPLATE;
                class_1792Array24[2] = GotItems.BRAAVOS_LEGGINGS;
                class_1792Array = class_1792Array24;
                class_1792Array24[3] = GotItems.BRAAVOS_BOOTS;
                break;
            }
            case "pentos": {
                class_1792[] class_1792Array25 = new class_1792[4];
                class_1792Array25[0] = GotItems.PENTOS_HELMET;
                class_1792Array25[1] = GotItems.PENTOS_CHESTPLATE;
                class_1792Array25[2] = GotItems.PENTOS_LEGGINGS;
                class_1792Array = class_1792Array25;
                class_1792Array25[3] = GotItems.PENTOS_BOOTS;
                break;
            }
            case "volantis": {
                class_1792[] class_1792Array26 = new class_1792[4];
                class_1792Array26[0] = GotItems.VOLANTIS_HELMET;
                class_1792Array26[1] = GotItems.VOLANTIS_CHESTPLATE;
                class_1792Array26[2] = GotItems.VOLANTIS_LEGGINGS;
                class_1792Array = class_1792Array26;
                class_1792Array26[3] = GotItems.VOLANTIS_BOOTS;
                break;
            }
            case "tyrosh": {
                class_1792[] class_1792Array27 = new class_1792[4];
                class_1792Array27[0] = GotItems.TYROSH_HELMET;
                class_1792Array27[1] = GotItems.TYROSH_CHESTPLATE;
                class_1792Array27[2] = GotItems.TYROSH_LEGGINGS;
                class_1792Array = class_1792Array27;
                class_1792Array27[3] = GotItems.TYROSH_BOOTS;
                break;
            }
            case "myr": {
                class_1792[] class_1792Array28 = new class_1792[4];
                class_1792Array28[0] = GotItems.MYR_HELMET;
                class_1792Array28[1] = GotItems.MYR_CHESTPLATE;
                class_1792Array28[2] = GotItems.MYR_LEGGINGS;
                class_1792Array = class_1792Array28;
                class_1792Array28[3] = GotItems.MYR_BOOTS;
                break;
            }
            case "blackfyre": {
                class_1792[] class_1792Array29 = new class_1792[4];
                class_1792Array29[0] = GotItems.BLACKFYRE_HELMET;
                class_1792Array29[1] = GotItems.BLACKFYRE_CHESTPLATE;
                class_1792Array29[2] = GotItems.BLACKFYRE_LEGGINGS;
                class_1792Array = class_1792Array29;
                class_1792Array29[3] = GotItems.BLACKFYRE_BOOTS;
                break;
            }
            case "golden_company": {
                class_1792[] class_1792Array30 = new class_1792[4];
                class_1792Array30[0] = GotItems.GOLDEN_COMPANY_HELMET;
                class_1792Array30[1] = GotItems.GOLDEN_COMPANY_CHESTPLATE;
                class_1792Array30[2] = GotItems.GOLDEN_COMPANY_LEGGINGS;
                class_1792Array = class_1792Array30;
                class_1792Array30[3] = GotItems.GOLDEN_COMPANY_BOOTS;
                break;
            }
            case "meereen": {
                class_1792[] class_1792Array31 = new class_1792[4];
                class_1792Array31[0] = GotItems.MEEREEN_HELMET;
                class_1792Array31[1] = GotItems.MEEREEN_CHESTPLATE;
                class_1792Array31[2] = GotItems.MEEREEN_LEGGINGS;
                class_1792Array = class_1792Array31;
                class_1792Array31[3] = GotItems.MEEREEN_BOOTS;
                break;
            }
            case "yunkai": {
                class_1792[] class_1792Array32 = new class_1792[4];
                class_1792Array32[0] = GotItems.YUNKAI_HELMET;
                class_1792Array32[1] = GotItems.YUNKAI_CHESTPLATE;
                class_1792Array32[2] = GotItems.YUNKAI_LEGGINGS;
                class_1792Array = class_1792Array32;
                class_1792Array32[3] = GotItems.YUNKAI_BOOTS;
                break;
            }
            case "astapor": {
                class_1792[] class_1792Array33 = new class_1792[4];
                class_1792Array33[0] = GotItems.ASTAPOR_HELMET;
                class_1792Array33[1] = GotItems.ASTAPOR_CHESTPLATE;
                class_1792Array33[2] = GotItems.ASTAPOR_LEGGINGS;
                class_1792Array = class_1792Array33;
                class_1792Array33[3] = GotItems.ASTAPOR_BOOTS;
                break;
            }
            case "qarth": {
                class_1792[] class_1792Array34 = new class_1792[4];
                class_1792Array34[0] = GotItems.QARTH_HELMET;
                class_1792Array34[1] = GotItems.QARTH_CHESTPLATE;
                class_1792Array34[2] = GotItems.QARTH_LEGGINGS;
                class_1792Array = class_1792Array34;
                class_1792Array34[3] = GotItems.QARTH_BOOTS;
                break;
            }
            case "faithmilitant": {
                class_1792[] class_1792Array35 = new class_1792[4];
                class_1792Array35[0] = GotItems.FAITHMILITANT_HELMET;
                class_1792Array35[1] = GotItems.FAITHMILITANT_CHESTPLATE;
                class_1792Array35[2] = GotItems.FAITHMILITANT_LEGGINGS;
                class_1792Array = class_1792Array35;
                class_1792Array35[3] = GotItems.FAITHMILITANT_BOOTS;
                break;
            }
            case "gambeson": {
                class_1792[] class_1792Array36 = new class_1792[4];
                class_1792Array36[0] = GotItems.GAMBESON_HELMET;
                class_1792Array36[1] = GotItems.GAMBESON_CHESTPLATE;
                class_1792Array36[2] = GotItems.GAMBESON_LEGGINGS;
                class_1792Array = class_1792Array36;
                class_1792Array36[3] = GotItems.GAMBESON_BOOTS;
                break;
            }
            case "kingsguard": {
                class_1792[] class_1792Array37 = new class_1792[4];
                class_1792Array37[0] = GotItems.KINGSGUARD_HELMET;
                class_1792Array37[1] = GotItems.KINGSGUARD_CHESTPLATE;
                class_1792Array37[2] = GotItems.KINGSGUARD_LEGGINGS;
                class_1792Array = class_1792Array37;
                class_1792Array37[3] = GotItems.KINGSGUARD_BOOTS;
                break;
            }
            default: {
                class_1792Array = null;
            }
        }
        return class_1792Array;
    }

    public static class_1792[] armorForKingdom(class_2960 kingdomId) {
        if (kingdomId == null) {
            return null;
        }
        class_1792[] armor = GotKingdomArmor.armorForId(kingdomId.method_12832());
        if (armor != null) {
            return armor;
        }
        class_2960 cultureKingdomId = GotKingdoms.cultureKingdomId(kingdomId);
        return cultureKingdomId == null || cultureKingdomId.equals((Object)kingdomId) ? null : GotKingdomArmor.armorForId(cultureKingdomId.method_12832());
    }
}

