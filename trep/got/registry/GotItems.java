/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_1738
 *  net.minecraft.class_1738$class_8051
 *  net.minecraft.class_1740
 *  net.minecraft.class_1741
 *  net.minecraft.class_1743
 *  net.minecraft.class_1745
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1794
 *  net.minecraft.class_1798
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1810
 *  net.minecraft.class_1821
 *  net.minecraft.class_1829
 *  net.minecraft.class_1832
 *  net.minecraft.class_1834
 *  net.minecraft.class_1856
 *  net.minecraft.class_1887
 *  net.minecraft.class_1893
 *  net.minecraft.class_1935
 *  net.minecraft.class_2248
 *  net.minecraft.class_2378
 *  net.minecraft.class_2960
 *  net.minecraft.class_3481
 *  net.minecraft.class_4174
 *  net.minecraft.class_4174$class_4175
 *  net.minecraft.class_5321
 *  net.minecraft.class_6862
 *  net.minecraft.class_6880
 *  net.minecraft.class_7706
 *  net.minecraft.class_7923
 *  net.minecraft.class_9282
 *  net.minecraft.class_9334
 */
package trep.got.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1738;
import net.minecraft.class_1740;
import net.minecraft.class_1741;
import net.minecraft.class_1743;
import net.minecraft.class_1745;
import net.minecraft.class_1792;
import net.minecraft.class_1794;
import net.minecraft.class_1798;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1810;
import net.minecraft.class_1821;
import net.minecraft.class_1829;
import net.minecraft.class_1832;
import net.minecraft.class_1834;
import net.minecraft.class_1856;
import net.minecraft.class_1887;
import net.minecraft.class_1893;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3481;
import net.minecraft.class_4174;
import net.minecraft.class_5321;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_7706;
import net.minecraft.class_7923;
import net.minecraft.class_9282;
import net.minecraft.class_9334;
import trep.got.block.CupBlock;
import trep.got.item.BlacksmithHammerItem;
import trep.got.item.BrewItem;
import trep.got.item.CartItem;
import trep.got.item.CatapultItem;
import trep.got.item.ClothingFabricItem;
import trep.got.item.CupItem;
import trep.got.item.CustomMeleeWeaponItem;
import trep.got.item.GateMechanismItem;
import trep.got.item.GuidebookItem;
import trep.got.item.JournalItem;
import trep.got.item.LongbowItem;
import trep.got.item.RuneFragmentItem;
import trep.got.item.RuneItem;
import trep.got.item.ScorpionCrossbowItem;
import trep.got.item.SealedLetterItem;
import trep.got.item.ShipItem;
import trep.got.item.ShipmentItem;
import trep.got.item.SpearItem;
import trep.got.item.TentItem;
import trep.got.item.TwoHorseCartItem;
import trep.got.item.VariantMaceItem;
import trep.got.item.VariantShieldItem;
import trep.got.item.WorldMapItem;
import trep.got.registry.GotArmorMaterials;
import trep.got.registry.GotBannerPatterns;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotEntities;

public final class GotItems {
    private static final class_1832 COPPER_TOOL_MATERIAL = new class_1832(){

        public int method_8025() {
            return 190;
        }

        public float method_8027() {
            return 5.0f;
        }

        public float method_8028() {
            return 1.0f;
        }

        public class_6862<class_2248> method_58419() {
            return class_3481.field_49928;
        }

        public int method_8026() {
            return 13;
        }

        public class_1856 method_8023() {
            return class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_27022});
        }
    };
    private static final class_1832 STEEL_TOOL_MATERIAL = new class_1832(){

        public int method_8025() {
            return 1561;
        }

        public float method_8027() {
            return 8.0f;
        }

        public float method_8028() {
            return 3.0f;
        }

        public class_6862<class_2248> method_58419() {
            return class_3481.field_49927;
        }

        public int method_8026() {
            return 10;
        }

        public class_1856 method_8023() {
            return class_1856.method_8091((class_1935[])new class_1935[]{STEEL_INGOT});
        }
    };
    private static final class_1832 VALYRIAN_STEEL_TOOL_MATERIAL = new class_1832(){

        public int method_8025() {
            return 3122;
        }

        public float method_8027() {
            return 9.0f;
        }

        public float method_8028() {
            return 4.5f;
        }

        public class_6862<class_2248> method_58419() {
            return class_3481.field_49927;
        }

        public int method_8026() {
            return 10;
        }

        public class_1856 method_8023() {
            return class_1856.method_8091((class_1935[])new class_1935[]{VALYRIAN_STEEL_INGOT});
        }
    };
    public static final class_1792 VALYRIAN_STEEL_INGOT = GotItems.register("valyrian_steel_ingot", new class_1792(new class_1792.class_1793()));
    public static final class_1792 ICE_SWORD = GotItems.register("ice_sword", new CustomMeleeWeaponItem(VALYRIAN_STEEL_TOOL_MATERIAL, 3.5f, -3.4f, 3.0f, new class_1792.class_1793()));
    public static final class_1792 OATHKEEPER = GotItems.register("oathkeeper", new class_1829(VALYRIAN_STEEL_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1829.method_57394((class_1832)VALYRIAN_STEEL_TOOL_MATERIAL, (int)3, (float)-2.4f))));
    public static final class_1792 BLACKFYRE = GotItems.register("blackfyre", new class_1829(VALYRIAN_STEEL_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1829.method_57394((class_1832)VALYRIAN_STEEL_TOOL_MATERIAL, (int)3, (float)-2.4f))));
    public static final class_1792 LONGCLAW = GotItems.register("longclaw", new class_1829(VALYRIAN_STEEL_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1829.method_57394((class_1832)VALYRIAN_STEEL_TOOL_MATERIAL, (int)3, (float)-2.4f))));
    public static final class_1792 WIDOWSWAIL = GotItems.register("widowswail", new class_1829(VALYRIAN_STEEL_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1829.method_57394((class_1832)VALYRIAN_STEEL_TOOL_MATERIAL, (int)3, (float)-2.4f))));
    public static final class_1792 HEARTSBANE = GotItems.register("heartsbane", new class_1829(VALYRIAN_STEEL_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1829.method_57394((class_1832)VALYRIAN_STEEL_TOOL_MATERIAL, (int)3, (float)-2.4f))));
    public static final class_1792 DARKSISTER = GotItems.register("darksister", new class_1829(VALYRIAN_STEEL_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1829.method_57394((class_1832)VALYRIAN_STEEL_TOOL_MATERIAL, (int)3, (float)-2.4f))));
    public static final class_1738 LANNISTER_HELMET = GotItems.register("lannister_helmet", new class_1738(GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 LANNISTER_CHESTPLATE = GotItems.register("lannister_chestplate", new class_1738(GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 LANNISTER_LEGGINGS = GotItems.register("lannister_leggings", new class_1738(GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 LANNISTER_BOOTS = GotItems.register("lannister_boots", new class_1738(GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 CLEGANE_HELMET = GotItems.register("clegane_helmet", new class_1738(GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 CLEGANE_CHESTPLATE = GotItems.register("clegane_chestplate", new class_1738(GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 CLEGANE_LEGGINGS = GotItems.register("clegane_leggings", new class_1738(GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 CLEGANE_BOOTS = GotItems.register("clegane_boots", new class_1738(GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 MARBRAND_HELMET = GotItems.registerArmor("marbrand_helmet", GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41934);
    public static final class_1738 MARBRAND_CHESTPLATE = GotItems.registerArmor("marbrand_chestplate", GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41935);
    public static final class_1738 MARBRAND_LEGGINGS = GotItems.registerArmor("marbrand_leggings", GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41936);
    public static final class_1738 MARBRAND_BOOTS = GotItems.registerArmor("marbrand_boots", GotArmorMaterials.LANNISTER, class_1738.class_8051.field_41937);
    public static final class_1792 LANNISTER_PLATE = GotItems.register("lannister_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1792 MARBRAND_PLATE = GotItems.register("marbrand_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1792 ARRYN_PLATE = GotItems.register("arryn_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 MARTELL_HELMET = GotItems.register("martell_helmet", new class_1738(GotArmorMaterials.MARTELL, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 MARTELL_CHESTPLATE = GotItems.register("martell_chestplate", new class_1738(GotArmorMaterials.MARTELL, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 MARTELL_LEGGINGS = GotItems.register("martell_leggings", new class_1738(GotArmorMaterials.MARTELL, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 MARTELL_BOOTS = GotItems.register("martell_boots", new class_1738(GotArmorMaterials.MARTELL, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 MARTELL_PLATE = GotItems.register("martell_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 DAYNE_HELMET = GotItems.registerArmor("dayne_helmet", GotArmorMaterials.MARTELL, class_1738.class_8051.field_41934);
    public static final class_1738 DAYNE_CHESTPLATE = GotItems.registerArmor("dayne_chestplate", GotArmorMaterials.MARTELL, class_1738.class_8051.field_41935);
    public static final class_1738 DAYNE_LEGGINGS = GotItems.registerArmor("dayne_leggings", GotArmorMaterials.MARTELL, class_1738.class_8051.field_41936);
    public static final class_1738 DAYNE_BOOTS = GotItems.registerArmor("dayne_boots", GotArmorMaterials.MARTELL, class_1738.class_8051.field_41937);
    public static final class_1792 DAYNE_PLATE = GotItems.register("dayne_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 YRONWOOD_HELMET = GotItems.registerArmor("yronwood_helmet", GotArmorMaterials.MARTELL, class_1738.class_8051.field_41934);
    public static final class_1738 YRONWOOD_CHESTPLATE = GotItems.registerArmor("yronwood_chestplate", GotArmorMaterials.MARTELL, class_1738.class_8051.field_41935);
    public static final class_1738 YRONWOOD_LEGGINGS = GotItems.registerArmor("yronwood_leggings", GotArmorMaterials.MARTELL, class_1738.class_8051.field_41936);
    public static final class_1738 YRONWOOD_BOOTS = GotItems.registerArmor("yronwood_boots", GotArmorMaterials.MARTELL, class_1738.class_8051.field_41937);
    public static final class_1792 YRONWOOD_PLATE = GotItems.register("yronwood_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 ARRYN_HELMET = GotItems.register("arryn_helmet", new class_1738(GotArmorMaterials.ARRYN, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 ARRYN_CHESTPLATE = GotItems.register("arryn_chestplate", new class_1738(GotArmorMaterials.ARRYN, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 ARRYN_LEGGINGS = GotItems.register("arryn_leggings", new class_1738(GotArmorMaterials.ARRYN, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 ARRYN_BOOTS = GotItems.register("arryn_boots", new class_1738(GotArmorMaterials.ARRYN, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 BARATHEON_HELMET = GotItems.register("baratheon_helmet", new class_1738(GotArmorMaterials.BARATHEON, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 BARATHEON_STAG_HELMET = GotItems.register("baratheon_stag_helmet", new class_1738(GotArmorMaterials.BARATHEON, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 BARATHEON_CHESTPLATE = GotItems.register("baratheon_chestplate", new class_1738(GotArmorMaterials.BARATHEON, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 BARATHEON_LEGGINGS = GotItems.register("baratheon_leggings", new class_1738(GotArmorMaterials.BARATHEON, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 BARATHEON_BOOTS = GotItems.register("baratheon_boots", new class_1738(GotArmorMaterials.BARATHEON, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 BARATHEON_PLATE = GotItems.register("baratheon_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 PYKE_HELMET = GotItems.register("pyke_helmet", new class_1738(GotArmorMaterials.PYKE, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 PYKE_CHESTPLATE = GotItems.register("pyke_chestplate", new class_1738(GotArmorMaterials.PYKE, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 PYKE_LEGGINGS = GotItems.register("pyke_leggings", new class_1738(GotArmorMaterials.PYKE, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 PYKE_BOOTS = GotItems.register("pyke_boots", new class_1738(GotArmorMaterials.PYKE, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 PYKE_PLATE = GotItems.register("pyke_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 HARLAW_HELMET = GotItems.registerArmor("harlaw_helmet", GotArmorMaterials.PYKE, class_1738.class_8051.field_41934);
    public static final class_1738 HARLAW_CHESTPLATE = GotItems.registerArmor("harlaw_chestplate", GotArmorMaterials.PYKE, class_1738.class_8051.field_41935);
    public static final class_1738 HARLAW_LEGGINGS = GotItems.registerArmor("harlaw_leggings", GotArmorMaterials.PYKE, class_1738.class_8051.field_41936);
    public static final class_1738 HARLAW_BOOTS = GotItems.registerArmor("harlaw_boots", GotArmorMaterials.PYKE, class_1738.class_8051.field_41937);
    public static final class_1792 HARLAW_PLATE = GotItems.register("harlaw_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 TYRELL_HELMET = GotItems.register("tyrell_helmet", new class_1738(GotArmorMaterials.TYRELL, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 TYRELL_CHESTPLATE = GotItems.register("tyrell_chestplate", new class_1738(GotArmorMaterials.TYRELL, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 TYRELL_LEGGINGS = GotItems.register("tyrell_leggings", new class_1738(GotArmorMaterials.TYRELL, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 TYRELL_BOOTS = GotItems.register("tyrell_boots", new class_1738(GotArmorMaterials.TYRELL, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 TYRELL_PLATE = GotItems.register("tyrell_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 HIGHTOWER_HELMET = GotItems.registerArmor("hightower_helmet", GotArmorMaterials.TYRELL, class_1738.class_8051.field_41934);
    public static final class_1738 HIGHTOWER_CHESTPLATE = GotItems.registerArmor("hightower_chestplate", GotArmorMaterials.TYRELL, class_1738.class_8051.field_41935);
    public static final class_1738 HIGHTOWER_LEGGINGS = GotItems.registerArmor("hightower_leggings", GotArmorMaterials.TYRELL, class_1738.class_8051.field_41936);
    public static final class_1738 HIGHTOWER_BOOTS = GotItems.registerArmor("hightower_boots", GotArmorMaterials.TYRELL, class_1738.class_8051.field_41937);
    public static final class_1792 HIGHTOWER_PLATE = GotItems.register("hightower_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 DRAGONSTONE_HELMET = GotItems.register("dragonstone_helmet", new class_1738(GotArmorMaterials.DRAGONSTONE, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 DRAGONSTONE_CHESTPLATE = GotItems.register("dragonstone_chestplate", new class_1738(GotArmorMaterials.DRAGONSTONE, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 DRAGONSTONE_LEGGINGS = GotItems.register("dragonstone_leggings", new class_1738(GotArmorMaterials.DRAGONSTONE, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 DRAGONSTONE_BOOTS = GotItems.register("dragonstone_boots", new class_1738(GotArmorMaterials.DRAGONSTONE, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 DRAGONSTONE_PLATE = GotItems.register("dragonstone_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 VELARYON_HELMET = GotItems.registerArmor("velaryon_helmet", GotArmorMaterials.DRAGONSTONE, class_1738.class_8051.field_41934);
    public static final class_1738 VELARYON_CHESTPLATE = GotItems.registerArmor("velaryon_chestplate", GotArmorMaterials.DRAGONSTONE, class_1738.class_8051.field_41935);
    public static final class_1738 VELARYON_LEGGINGS = GotItems.registerArmor("velaryon_leggings", GotArmorMaterials.DRAGONSTONE, class_1738.class_8051.field_41936);
    public static final class_1738 VELARYON_BOOTS = GotItems.registerArmor("velaryon_boots", GotArmorMaterials.DRAGONSTONE, class_1738.class_8051.field_41937);
    public static final class_1792 VELARYON_PLATE = GotItems.register("velaryon_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1792 GATE_MECHANISM = GotItems.register("gate_mechanism", new GateMechanismItem(GotBlocks.GATE_MECHANISM, new class_1792.class_1793()));
    public static final class_1738 KINGSGUARD_HELMET = GotItems.register("kingsguard_helmet", new class_1738(GotArmorMaterials.KINGSGUARD, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 KINGSGUARD_CHESTPLATE = GotItems.register("kingsguard_chestplate", new class_1738(GotArmorMaterials.KINGSGUARD, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 KINGSGUARD_LEGGINGS = GotItems.register("kingsguard_leggings", new class_1738(GotArmorMaterials.KINGSGUARD, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 KINGSGUARD_BOOTS = GotItems.register("kingsguard_boots", new class_1738(GotArmorMaterials.KINGSGUARD, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 KINGSGUARD_PLATE = GotItems.register("kingsguard_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 GOLDCLOAK_HELMET = GotItems.register("goldcloak_helmet", new class_1738(GotArmorMaterials.GOLDCLOAK, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 GOLDCLOAK_CHESTPLATE = GotItems.register("goldcloak_chestplate", new class_1738(GotArmorMaterials.GOLDCLOAK, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 GOLDCLOAK_LEGGINGS = GotItems.register("goldcloak_leggings", new class_1738(GotArmorMaterials.GOLDCLOAK, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 GOLDCLOAK_BOOTS = GotItems.register("goldcloak_boots", new class_1738(GotArmorMaterials.GOLDCLOAK, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 GOLDCLOAK_PLATE = GotItems.register("goldcloak_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 TULLY_HELMET = GotItems.register("tully_helmet", new class_1738(GotArmorMaterials.TULLY, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 TULLY_CHESTPLATE = GotItems.register("tully_chestplate", new class_1738(GotArmorMaterials.TULLY, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 TULLY_LEGGINGS = GotItems.register("tully_leggings", new class_1738(GotArmorMaterials.TULLY, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 TULLY_BOOTS = GotItems.register("tully_boots", new class_1738(GotArmorMaterials.TULLY, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 TULLY_PLATE = GotItems.register("tully_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 BLACKWOOD_HELMET = GotItems.registerArmor("blackwood_helmet", GotArmorMaterials.TULLY, class_1738.class_8051.field_41934);
    public static final class_1738 BLACKWOOD_CHESTPLATE = GotItems.registerArmor("blackwood_chestplate", GotArmorMaterials.TULLY, class_1738.class_8051.field_41935);
    public static final class_1738 BLACKWOOD_LEGGINGS = GotItems.registerArmor("blackwood_leggings", GotArmorMaterials.TULLY, class_1738.class_8051.field_41936);
    public static final class_1738 BLACKWOOD_BOOTS = GotItems.registerArmor("blackwood_boots", GotArmorMaterials.TULLY, class_1738.class_8051.field_41937);
    public static final class_1792 BLACKWOOD_PLATE = GotItems.register("blackwood_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 STARK_HELMET = GotItems.register("stark_helmet", new class_1738(GotArmorMaterials.STARK, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 STARK_CHESTPLATE = GotItems.register("stark_chestplate", new class_1738(GotArmorMaterials.STARK, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 STARK_LEGGINGS = GotItems.register("stark_leggings", new class_1738(GotArmorMaterials.STARK, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 STARK_BOOTS = GotItems.register("stark_boots", new class_1738(GotArmorMaterials.STARK, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 STARK_PLATE = GotItems.register("stark_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 KARSTARK_HELMET = GotItems.registerArmor("karstark_helmet", GotArmorMaterials.STARK, class_1738.class_8051.field_41934);
    public static final class_1738 KARSTARK_CHESTPLATE = GotItems.registerArmor("karstark_chestplate", GotArmorMaterials.STARK, class_1738.class_8051.field_41935);
    public static final class_1738 KARSTARK_LEGGINGS = GotItems.registerArmor("karstark_leggings", GotArmorMaterials.STARK, class_1738.class_8051.field_41936);
    public static final class_1738 KARSTARK_BOOTS = GotItems.registerArmor("karstark_boots", GotArmorMaterials.STARK, class_1738.class_8051.field_41937);
    public static final class_1792 KARSTARK_PLATE = GotItems.register("karstark_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 THE_NIGHTS_WATCH_HELMET = GotItems.registerArmor("the_nights_watch_helmet", GotArmorMaterials.THE_NIGHTS_WATCH, class_1738.class_8051.field_41934);
    public static final class_1738 THE_NIGHTS_WATCH_CHESTPLATE = GotItems.registerArmor("the_nights_watch_chestplate", GotArmorMaterials.THE_NIGHTS_WATCH, class_1738.class_8051.field_41935);
    public static final class_1738 THE_NIGHTS_WATCH_LEGGINGS = GotItems.registerArmor("the_nights_watch_leggings", GotArmorMaterials.THE_NIGHTS_WATCH, class_1738.class_8051.field_41936);
    public static final class_1738 THE_NIGHTS_WATCH_BOOTS = GotItems.registerArmor("the_nights_watch_boots", GotArmorMaterials.THE_NIGHTS_WATCH, class_1738.class_8051.field_41937);
    public static final class_1792 THE_NIGHTS_WATCH_PLATE = GotItems.register("the_nights_watch_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 WILDLING_HELMET = GotItems.registerArmor("wildling_helmet", GotArmorMaterials.WILDLING, class_1738.class_8051.field_41934);
    public static final class_1738 WILDLING_CHESTPLATE = GotItems.registerArmor("wildling_chestplate", GotArmorMaterials.WILDLING, class_1738.class_8051.field_41935);
    public static final class_1738 WILDLING_LEGGINGS = GotItems.registerArmor("wildling_leggings", GotArmorMaterials.WILDLING, class_1738.class_8051.field_41936);
    public static final class_1738 WILDLING_BOOTS = GotItems.registerArmor("wildling_boots", GotArmorMaterials.WILDLING, class_1738.class_8051.field_41937);
    public static final class_1738 TARGARYEN_HELMET = GotItems.registerArmor("targaryen_helmet", GotArmorMaterials.TARGARYEN, class_1738.class_8051.field_41934);
    public static final class_1738 TARGARYEN_CHESTPLATE = GotItems.registerArmor("targaryen_chestplate", GotArmorMaterials.TARGARYEN, class_1738.class_8051.field_41935);
    public static final class_1738 TARGARYEN_LEGGINGS = GotItems.registerArmor("targaryen_leggings", GotArmorMaterials.TARGARYEN, class_1738.class_8051.field_41936);
    public static final class_1738 TARGARYEN_BOOTS = GotItems.registerArmor("targaryen_boots", GotArmorMaterials.TARGARYEN, class_1738.class_8051.field_41937);
    public static final class_1792 TARGARYEN_PLATE = GotItems.register("targaryen_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 BRAAVOS_HELMET = GotItems.registerArmor("braavos_helmet", GotArmorMaterials.BRAAVOS, class_1738.class_8051.field_41934);
    public static final class_1738 BRAAVOS_CHESTPLATE = GotItems.registerArmor("braavos_chestplate", GotArmorMaterials.BRAAVOS, class_1738.class_8051.field_41935);
    public static final class_1738 BRAAVOS_LEGGINGS = GotItems.registerArmor("braavos_leggings", GotArmorMaterials.BRAAVOS, class_1738.class_8051.field_41936);
    public static final class_1738 BRAAVOS_BOOTS = GotItems.registerArmor("braavos_boots", GotArmorMaterials.BRAAVOS, class_1738.class_8051.field_41937);
    public static final class_1792 BRAAVOS_PLATE = GotItems.register("braavos_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 PENTOS_HELMET = GotItems.registerArmor("pentos_helmet", GotArmorMaterials.PENTOS, class_1738.class_8051.field_41934);
    public static final class_1738 PENTOS_CHESTPLATE = GotItems.registerArmor("pentos_chestplate", GotArmorMaterials.PENTOS, class_1738.class_8051.field_41935);
    public static final class_1738 PENTOS_LEGGINGS = GotItems.registerArmor("pentos_leggings", GotArmorMaterials.PENTOS, class_1738.class_8051.field_41936);
    public static final class_1738 PENTOS_BOOTS = GotItems.registerArmor("pentos_boots", GotArmorMaterials.PENTOS, class_1738.class_8051.field_41937);
    public static final class_1792 PENTOS_PLATE = GotItems.register("pentos_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 VOLANTIS_HELMET = GotItems.registerArmor("volantis_helmet", GotArmorMaterials.VOLANTIS, class_1738.class_8051.field_41934);
    public static final class_1738 VOLANTIS_CHESTPLATE = GotItems.registerArmor("volantis_chestplate", GotArmorMaterials.VOLANTIS, class_1738.class_8051.field_41935);
    public static final class_1738 VOLANTIS_LEGGINGS = GotItems.registerArmor("volantis_leggings", GotArmorMaterials.VOLANTIS, class_1738.class_8051.field_41936);
    public static final class_1738 VOLANTIS_BOOTS = GotItems.registerArmor("volantis_boots", GotArmorMaterials.VOLANTIS, class_1738.class_8051.field_41937);
    public static final class_1792 VOLANTIS_PLATE = GotItems.register("volantis_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 TYROSH_HELMET = GotItems.registerArmor("tyrosh_helmet", GotArmorMaterials.TYROSH, class_1738.class_8051.field_41934);
    public static final class_1738 TYROSH_CHESTPLATE = GotItems.registerArmor("tyrosh_chestplate", GotArmorMaterials.TYROSH, class_1738.class_8051.field_41935);
    public static final class_1738 TYROSH_LEGGINGS = GotItems.registerArmor("tyrosh_leggings", GotArmorMaterials.TYROSH, class_1738.class_8051.field_41936);
    public static final class_1738 TYROSH_BOOTS = GotItems.registerArmor("tyrosh_boots", GotArmorMaterials.TYROSH, class_1738.class_8051.field_41937);
    public static final class_1792 TYROSH_PLATE = GotItems.register("tyrosh_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 MYR_HELMET = GotItems.registerArmor("myr_helmet", GotArmorMaterials.MYR, class_1738.class_8051.field_41934);
    public static final class_1738 MYR_CHESTPLATE = GotItems.registerArmor("myr_chestplate", GotArmorMaterials.MYR, class_1738.class_8051.field_41935);
    public static final class_1738 MYR_LEGGINGS = GotItems.registerArmor("myr_leggings", GotArmorMaterials.MYR, class_1738.class_8051.field_41936);
    public static final class_1738 MYR_BOOTS = GotItems.registerArmor("myr_boots", GotArmorMaterials.MYR, class_1738.class_8051.field_41937);
    public static final class_1792 MYR_PLATE = GotItems.register("myr_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 BLACKFYRE_HELMET = GotItems.registerArmor("blackfyre_helmet", GotArmorMaterials.BLACKFYRE, class_1738.class_8051.field_41934);
    public static final class_1738 BLACKFYRE_CHESTPLATE = GotItems.registerArmor("blackfyre_chestplate", GotArmorMaterials.BLACKFYRE, class_1738.class_8051.field_41935);
    public static final class_1738 BLACKFYRE_LEGGINGS = GotItems.registerArmor("blackfyre_leggings", GotArmorMaterials.BLACKFYRE, class_1738.class_8051.field_41936);
    public static final class_1738 BLACKFYRE_BOOTS = GotItems.registerArmor("blackfyre_boots", GotArmorMaterials.BLACKFYRE, class_1738.class_8051.field_41937);
    public static final class_1792 BLACKFYRE_PLATE = GotItems.register("blackfyre_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 GOLDEN_COMPANY_HELMET = GotItems.registerArmor("golden_company_helmet", GotArmorMaterials.GOLDEN_COMPANY, class_1738.class_8051.field_41934);
    public static final class_1738 GOLDEN_COMPANY_CHESTPLATE = GotItems.registerArmor("golden_company_chestplate", GotArmorMaterials.GOLDEN_COMPANY, class_1738.class_8051.field_41935);
    public static final class_1738 GOLDEN_COMPANY_LEGGINGS = GotItems.registerArmor("golden_company_leggings", GotArmorMaterials.GOLDEN_COMPANY, class_1738.class_8051.field_41936);
    public static final class_1738 GOLDEN_COMPANY_BOOTS = GotItems.registerArmor("golden_company_boots", GotArmorMaterials.GOLDEN_COMPANY, class_1738.class_8051.field_41937);
    public static final class_1792 GOLDEN_COMPANY_PLATE = GotItems.register("golden_company_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 MEEREEN_HELMET = GotItems.registerArmor("meereen_helmet", GotArmorMaterials.MEEREEN, class_1738.class_8051.field_41934);
    public static final class_1738 MEEREEN_CHESTPLATE = GotItems.registerArmor("meereen_chestplate", GotArmorMaterials.MEEREEN, class_1738.class_8051.field_41935);
    public static final class_1738 MEEREEN_LEGGINGS = GotItems.registerArmor("meereen_leggings", GotArmorMaterials.MEEREEN, class_1738.class_8051.field_41936);
    public static final class_1738 MEEREEN_BOOTS = GotItems.registerArmor("meereen_boots", GotArmorMaterials.MEEREEN, class_1738.class_8051.field_41937);
    public static final class_1792 MEEREEN_PLATE = GotItems.register("meereen_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 YUNKAI_HELMET = GotItems.registerArmor("yunkai_helmet", GotArmorMaterials.YUNKAI, class_1738.class_8051.field_41934);
    public static final class_1738 YUNKAI_CHESTPLATE = GotItems.registerArmor("yunkai_chestplate", GotArmorMaterials.YUNKAI, class_1738.class_8051.field_41935);
    public static final class_1738 YUNKAI_LEGGINGS = GotItems.registerArmor("yunkai_leggings", GotArmorMaterials.YUNKAI, class_1738.class_8051.field_41936);
    public static final class_1738 YUNKAI_BOOTS = GotItems.registerArmor("yunkai_boots", GotArmorMaterials.YUNKAI, class_1738.class_8051.field_41937);
    public static final class_1792 YUNKAI_PLATE = GotItems.register("yunkai_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 ASTAPOR_HELMET = GotItems.registerArmor("astapor_helmet", GotArmorMaterials.ASTAPOR, class_1738.class_8051.field_41934);
    public static final class_1738 ASTAPOR_CHESTPLATE = GotItems.registerArmor("astapor_chestplate", GotArmorMaterials.ASTAPOR, class_1738.class_8051.field_41935);
    public static final class_1738 ASTAPOR_LEGGINGS = GotItems.registerArmor("astapor_leggings", GotArmorMaterials.ASTAPOR, class_1738.class_8051.field_41936);
    public static final class_1738 ASTAPOR_BOOTS = GotItems.registerArmor("astapor_boots", GotArmorMaterials.ASTAPOR, class_1738.class_8051.field_41937);
    public static final class_1792 ASTAPOR_PLATE = GotItems.register("astapor_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1738 QARTH_HELMET = GotItems.registerArmor("qarth_helmet", GotArmorMaterials.QARTH, class_1738.class_8051.field_41934);
    public static final class_1738 QARTH_CHESTPLATE = GotItems.registerArmor("qarth_chestplate", GotArmorMaterials.QARTH, class_1738.class_8051.field_41935);
    public static final class_1738 QARTH_LEGGINGS = GotItems.registerArmor("qarth_leggings", GotArmorMaterials.QARTH, class_1738.class_8051.field_41936);
    public static final class_1738 QARTH_BOOTS = GotItems.registerArmor("qarth_boots", GotArmorMaterials.QARTH, class_1738.class_8051.field_41937);
    public static final class_1792 QARTH_PLATE = GotItems.register("qarth_plate", new class_1792(new class_1792.class_1793()));
    public static final class_1792 WOODEN_SPEAR = GotItems.register("wooden_spear", GotItems.createSpear((class_1832)class_1834.field_8922, false));
    public static final class_1792 STONE_SPEAR = GotItems.register("stone_spear", GotItems.createSpear((class_1832)class_1834.field_8927, false));
    public static final class_1792 COPPER_SPEAR = GotItems.register("copper_spear", GotItems.createSpear(COPPER_TOOL_MATERIAL, false));
    public static final class_1792 WOODEN_HALBERD = GotItems.register("wooden_halberd", GotItems.createHalberd((class_1832)class_1834.field_8922));
    public static final class_1792 STONE_HALBERD = GotItems.register("stone_halberd", GotItems.createHalberd((class_1832)class_1834.field_8927));
    public static final class_1792 COPPER_HALBERD = GotItems.register("copper_halberd", GotItems.createHalberd(COPPER_TOOL_MATERIAL));
    public static final class_1792 WOODEN_KNIFE = GotItems.register("wooden_knife", GotItems.createKnife((class_1832)class_1834.field_8922));
    public static final class_1792 STONE_KNIFE = GotItems.register("stone_knife", GotItems.createKnife((class_1832)class_1834.field_8927));
    public static final class_1792 COPPER_KNIFE = GotItems.register("copper_knife", GotItems.createKnife(COPPER_TOOL_MATERIAL));
    public static final class_1792 IRON_KNIFE = GotItems.register("iron_knife", GotItems.createKnife((class_1832)class_1834.field_8923));
    public static final class_1792 STEEL_KNIFE = GotItems.register("steel_knife", GotItems.createKnife(STEEL_TOOL_MATERIAL));
    public static final class_1792 WOODEN_GREATAXE = GotItems.register("wooden_greataxe", GotItems.createGreataxe((class_1832)class_1834.field_8922));
    public static final class_1792 STONE_GREATAXE = GotItems.register("stone_greataxe", GotItems.createGreataxe((class_1832)class_1834.field_8927));
    public static final class_1792 COPPER_GREATAXE = GotItems.register("copper_greataxe", GotItems.createGreataxe(COPPER_TOOL_MATERIAL));
    public static final class_1792 IRON_GREATAXE = GotItems.register("iron_greataxe", GotItems.createGreataxe((class_1832)class_1834.field_8923));
    public static final class_1792 STEEL_GREATAXE = GotItems.register("steel_greataxe", GotItems.createGreataxe(STEEL_TOOL_MATERIAL));
    public static final class_1792 WOODEN_WARHAMMER = GotItems.register("wooden_warhammer", GotItems.createWarhammer((class_1832)class_1834.field_8922));
    public static final class_1792 STONE_WARHAMMER = GotItems.register("stone_warhammer", GotItems.createWarhammer((class_1832)class_1834.field_8927));
    public static final class_1792 COPPER_WARHAMMER = GotItems.register("copper_warhammer", GotItems.createWarhammer(COPPER_TOOL_MATERIAL));
    public static final class_1792 IRON_WARHAMMER = GotItems.register("iron_warhammer", GotItems.createWarhammer((class_1832)class_1834.field_8923));
    public static final class_1792 STEEL_WARHAMMER = GotItems.register("steel_warhammer", GotItems.createWarhammer(STEEL_TOOL_MATERIAL));
    public static final class_1792 WOODEN_MACE = GotItems.register("wooden_mace", GotItems.createMace((class_1832)class_1834.field_8922));
    public static final class_1792 STONE_MACE = GotItems.register("stone_mace", GotItems.createMace((class_1832)class_1834.field_8927));
    public static final class_1792 COPPER_MACE = GotItems.register("copper_mace", GotItems.createMace(COPPER_TOOL_MATERIAL));
    public static final class_1792 IRON_MACE = GotItems.register("iron_mace", GotItems.createMace((class_1832)class_1834.field_8923));
    public static final class_1792 STEEL_MACE = GotItems.register("steel_mace", GotItems.createMace(STEEL_TOOL_MATERIAL));
    public static final class_1792 COPPER_NUGGET = GotItems.register("copper_nugget", new class_1792(new class_1792.class_1793()));
    public static final class_1792 COPPER_CROWN = GotItems.register("copper_crown", new class_1792(new class_1792.class_1793()));
    public static final class_1792 SILVER_NUGGET = GotItems.register("silver_nugget", new class_1792(new class_1792.class_1793()));
    public static final class_1792 SILVER_STAG = GotItems.register("silver_stag", new class_1792(new class_1792.class_1793()));
    public static final class_1792 GOLDEN_DRAGON = GotItems.register("golden_dragon", new class_1792(new class_1792.class_1793()));
    public static final class_1792 RAW_SILVER = GotItems.register("raw_silver", new class_1792(new class_1792.class_1793()));
    public static final class_1792 SILVER_INGOT = GotItems.register("silver_ingot", new class_1792(new class_1792.class_1793()));
    public static final class_1792 STEEL_LUMP = GotItems.register("steel_lump", new class_1792(new class_1792.class_1793()));
    public static final class_1792 STEEL_INGOT = GotItems.register("steel_ingot", new class_1792(new class_1792.class_1793()));
    public static final class_1792 STEEL_PLATE = GotItems.register("plate_steel", new class_1792(new class_1792.class_1793()));
    public static final class_1792 DEER_ANTLERS = GotItems.register("deer_antlers", new class_1792(new class_1792.class_1793()));
    public static final class_1738 COPPER_HELMET = GotItems.register("copper_helmet", new class_1738(GotArmorMaterials.COPPER, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 COPPER_CHESTPLATE = GotItems.register("copper_chestplate", new class_1738(GotArmorMaterials.COPPER, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 COPPER_LEGGINGS = GotItems.register("copper_leggings", new class_1738(GotArmorMaterials.COPPER, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 COPPER_BOOTS = GotItems.register("copper_boots", new class_1738(GotArmorMaterials.COPPER, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 GAMBESON_HELMET = GotItems.register("gambeson_helmet", new class_1738(GotArmorMaterials.GAMBESON, class_1738.class_8051.field_41934, GotItems.dyedGambesonSettings()));
    public static final class_1738 GAMBESON_CHESTPLATE = GotItems.register("gambeson_chestplate", new class_1738(GotArmorMaterials.GAMBESON, class_1738.class_8051.field_41935, GotItems.dyedGambesonSettings()));
    public static final class_1738 GAMBESON_LEGGINGS = GotItems.register("gambeson_leggings", new class_1738(GotArmorMaterials.GAMBESON, class_1738.class_8051.field_41936, GotItems.dyedGambesonSettings()));
    public static final class_1738 GAMBESON_BOOTS = GotItems.register("gambeson_boots", new class_1738(GotArmorMaterials.GAMBESON, class_1738.class_8051.field_41937, GotItems.dyedGambesonSettings()));
    public static final class_1738 FAITHMILITANT_HELMET = GotItems.register("faithmilitant_helmet", new class_1738(class_1740.field_7892, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 FAITHMILITANT_CHESTPLATE = GotItems.register("faithmilitant_chestplate", new class_1738(class_1740.field_7892, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 FAITHMILITANT_LEGGINGS = GotItems.register("faithmilitant_leggings", new class_1738(class_1740.field_7892, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 FAITHMILITANT_BOOTS = GotItems.register("faithmilitant_boots", new class_1738(class_1740.field_7892, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 STEEL_HELMET = GotItems.register("steel_helmet", new class_1738(GotArmorMaterials.STEEL, class_1738.class_8051.field_41934, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 STEEL_CHESTPLATE = GotItems.register("steel_chestplate", new class_1738(GotArmorMaterials.STEEL, class_1738.class_8051.field_41935, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 STEEL_LEGGINGS = GotItems.register("steel_leggings", new class_1738(GotArmorMaterials.STEEL, class_1738.class_8051.field_41936, new class_1792.class_1793().method_7889(1)));
    public static final class_1738 STEEL_BOOTS = GotItems.register("steel_boots", new class_1738(GotArmorMaterials.STEEL, class_1738.class_8051.field_41937, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 COPPER_SWORD = GotItems.register("copper_sword", new class_1829(COPPER_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1829.method_57394((class_1832)COPPER_TOOL_MATERIAL, (int)3, (float)-2.4f))));
    public static final class_1792 COPPER_PICKAXE = GotItems.register("copper_pickaxe", new class_1810(COPPER_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1810.method_57346((class_1832)COPPER_TOOL_MATERIAL, (float)1.0f, (float)-2.8f))));
    public static final class_1792 COPPER_AXE = GotItems.register("copper_axe", new class_1743(COPPER_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1743.method_57346((class_1832)COPPER_TOOL_MATERIAL, (float)7.0f, (float)-3.2f))));
    public static final class_1792 COPPER_SHOVEL = GotItems.register("copper_shovel", new class_1821(COPPER_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1821.method_57346((class_1832)COPPER_TOOL_MATERIAL, (float)1.5f, (float)-3.0f))));
    public static final class_1792 COPPER_HOE = GotItems.register("copper_hoe", new class_1794(COPPER_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1794.method_57346((class_1832)COPPER_TOOL_MATERIAL, (float)-1.0f, (float)-2.0f))));
    public static final class_1792 STEEL_SWORD = GotItems.register("steel_sword", new class_1829(STEEL_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1829.method_57394((class_1832)STEEL_TOOL_MATERIAL, (int)3, (float)-2.4f))));
    public static final class_1792 STEEL_PICKAXE = GotItems.register("steel_pickaxe", new class_1810(STEEL_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1810.method_57346((class_1832)STEEL_TOOL_MATERIAL, (float)1.0f, (float)-2.8f))));
    public static final class_1792 STEEL_AXE = GotItems.register("steel_axe", new class_1743(STEEL_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1743.method_57346((class_1832)STEEL_TOOL_MATERIAL, (float)5.0f, (float)-3.0f))));
    public static final class_1792 STEEL_SHOVEL = GotItems.register("steel_shovel", new class_1821(STEEL_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1821.method_57346((class_1832)STEEL_TOOL_MATERIAL, (float)1.5f, (float)-3.0f))));
    public static final class_1792 STEEL_HOE = GotItems.register("steel_hoe", new class_1794(STEEL_TOOL_MATERIAL, new class_1792.class_1793().method_57348(class_1794.method_57346((class_1832)STEEL_TOOL_MATERIAL, (float)-3.0f, (float)0.0f))));
    public static final class_1792 GOLDEN_SPEAR = GotItems.register("golden_spear", GotItems.createSpear((class_1832)class_1834.field_8929, false));
    public static final class_1792 IRON_SPEAR = GotItems.register("iron_spear", GotItems.createSpear((class_1832)class_1834.field_8923, false));
    public static final class_1792 DIAMOND_SPEAR = GotItems.register("diamond_spear", GotItems.createSpear((class_1832)class_1834.field_8930, false));
    public static final class_1792 STEEL_SPEAR = GotItems.register("steel_spear", GotItems.createSpear(STEEL_TOOL_MATERIAL, false));
    public static final class_1792 NETHERITE_SPEAR = GotItems.register("netherite_spear", GotItems.createSpear((class_1832)class_1834.field_22033, true));
    public static final class_1792 IRON_HALBERD = GotItems.register("iron_halberd", GotItems.createHalberd((class_1832)class_1834.field_8923));
    public static final class_1792 STEEL_HALBERD = GotItems.register("steel_halberd", GotItems.createHalberd(STEEL_TOOL_MATERIAL));
    public static final class_1792 ROUND_SHIELD = GotItems.register("round_shield", new VariantShieldItem(new class_1792.class_1793().method_7889(1).method_7895(384)));
    public static final class_1792 SQUARE_SHIELD = GotItems.register("square_shield", new VariantShieldItem(new class_1792.class_1793().method_7889(1).method_7895(420)));
    public static final class_1792 KITE_SHIELD = GotItems.register("kite_shield", new VariantShieldItem(new class_1792.class_1793().method_7889(1).method_7895(456)));
    public static final class_1792 ARRYN_BANNER_PATTERN = GotItems.register("arryn_banner_pattern", new class_1745(GotBannerPatterns.itemTag("arryn"), new class_1792.class_1793().method_7889(1)));
    public static final class_1792 BARATHEON_BANNER_PATTERN = GotItems.register("baratheon_banner_pattern", new class_1745(GotBannerPatterns.itemTag("baratheon"), new class_1792.class_1793().method_7889(1)));
    public static final class_1792 GREYJOY_BANNER_PATTERN = GotItems.register("greyjoy_banner_pattern", new class_1745(GotBannerPatterns.itemTag("greyjoy"), new class_1792.class_1793().method_7889(1)));
    public static final class_1792 LANNISTER_BANNER_PATTERN = GotItems.register("lannister_banner_pattern", new class_1745(GotBannerPatterns.itemTag("lannister"), new class_1792.class_1793().method_7889(1)));
    public static final class_1792 MARTELL_BANNER_PATTERN = GotItems.register("martell_banner_pattern", new class_1745(GotBannerPatterns.itemTag("martell"), new class_1792.class_1793().method_7889(1)));
    public static final class_1792 STARK_BANNER_PATTERN = GotItems.register("stark_banner_pattern", new class_1745(GotBannerPatterns.itemTag("stark"), new class_1792.class_1793().method_7889(1)));
    public static final class_1792 TULLY_BANNER_PATTERN = GotItems.register("tully_banner_pattern", new class_1745(GotBannerPatterns.itemTag("tully"), new class_1792.class_1793().method_7889(1)));
    public static final class_1792 BLACKSMITH_HAMMER = GotItems.register("blacksmith_hammer", new BlacksmithHammerItem(new class_1792.class_1793().method_7889(1).method_7895(100).method_57348(BlacksmithHammerItem.createAttributeModifiers())));
    public static final class_1792 WORLD_MAP = GotItems.register("world_map", new WorldMapItem(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 JOURNAL = GotItems.register("journal", new JournalItem(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 GUIDEBOOK = GotItems.register("guidebook", new GuidebookItem(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 LOCKPICK = GotItems.register("lockpick", new class_1792(new class_1792.class_1793().method_7889(16)));
    public static final class_1792 TENT = GotItems.register("tent", new TentItem(new class_1792.class_1793().method_7889(16)));
    public static final class_1792 CART = GotItems.register("cart", new CartItem(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 CART_2HORSE = GotItems.register("cart_2horse", new TwoHorseCartItem(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 CATAPULT = GotItems.register("catapult", new CatapultItem(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 SCORPION_CROSSBOW = GotItems.register("scorpion_crossbow", new ScorpionCrossbowItem(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 LONGBOW = GotItems.register("longbow", new LongbowItem(new class_1792.class_1793().method_7889(1).method_7895(384)));
    public static final class_1792 SHIP = GotItems.register("ship", new ShipItem(GotEntities.SHIP, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 GALLEY = GotItems.register("galley", new ShipItem(GotEntities.GALLEY, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 LONGSHIP = GotItems.register("longship", new ShipItem(GotEntities.LONGSHIP, new class_1792.class_1793().method_7889(1)));
    public static final class_1792 QUEST_MARKER_ONE = GotItems.register("quest_marker_one", new class_1792(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 QUEST_MARKER_TWO = GotItems.register("quest_marker_two", new class_1792(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 QUEST_MARKER_THREE = GotItems.register("quest_marker_three", new class_1792(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 SHIPMENT_ITEM = GotItems.register("shipment_item", new ShipmentItem(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 SEALED_LETTER = GotItems.register("sealed_letter", new SealedLetterItem(new class_1792.class_1793().method_7889(1)));
    public static final class_1792 FLAX_SEEDS = GotItems.register("flax_seeds", new class_1798(GotBlocks.FLAX_CROP, new class_1792.class_1793()));
    public static final class_1792 FLAX_BUNDLE = GotItems.register("flax_bundle", new class_1792(new class_1792.class_1793()));
    public static final class_1792 FABRIC = GotItems.register("fabric", new ClothingFabricItem(new class_1792.class_1793().method_7889(16)));
    public static final class_1792 EMPTY_CUP = GotItems.register("empty_cup", new CupItem((CupBlock)GotBlocks.CUP_BLOCK, new class_1792.class_1793()));
    public static final class_1792 EMPTY_SILVER_CUP = GotItems.register("empty_silver_cup", new CupItem((CupBlock)GotBlocks.SILVER_CUP_BLOCK, new class_1792.class_1793()));
    public static final class_1792 HOP = GotItems.register("hop", new class_1792(new class_1792.class_1793().method_19265(GotItems.lightSnackFood())));
    public static final class_1792 HOP_SEEDS = GotItems.register("hop_seeds", new class_1798(GotBlocks.HOP_CROP, new class_1792.class_1793()));
    public static final class_1792 PURPLE_GRAPES = GotItems.register("purple_grapes", new class_1792(new class_1792.class_1793().method_19265(GotItems.lightSnackFood())));
    public static final class_1792 GREEN_GRAPES = GotItems.register("green_grapes", new class_1798(GotBlocks.GRAPE_CROP, new class_1792.class_1793().method_19265(GotItems.lightSnackFood())));
    public static final class_1792 TOMATO = GotItems.register("tomato", new class_1798(GotBlocks.TOMATO_CROP, new class_1792.class_1793().method_19265(GotItems.lightSnackFood())));
    public static final class_1792 ORANGE = GotItems.register("orange", new class_1798(GotBlocks.ORANGE_CROP, new class_1792.class_1793().method_19265(GotItems.lightSnackFood())));
    public static final class_1792 TURNIP = GotItems.register("turnip", new class_1798(GotBlocks.TURNIP_CROP, new class_1792.class_1793().method_19265(GotItems.lightSnackFood())));
    public static final class_1792 CHAMPIGNON = GotItems.register("champignon", new class_1798(GotBlocks.CHAMPIGNON_CROP, new class_1792.class_1793().method_19265(GotItems.lightSnackFood())));
    public static final class_1792 SLICED_BREAD = GotItems.register("sliced_bread", new class_1792(new class_1792.class_1793().method_19265(GotItems.slicedBreadFood())));
    public static final class_1792 BEEF_SANDWICH = GotItems.register("beef_sandwich", new class_1792(new class_1792.class_1793().method_19265(GotItems.sandwichFood(10, 0.8f))));
    public static final class_1792 BEEF_TOMATO_SANDWICH = GotItems.register("beef_tomato_sandwich", new class_1792(new class_1792.class_1793().method_19265(GotItems.sandwichFood(11, 0.8f))));
    public static final class_1792 TOMATO_SANDWICH = GotItems.register("tomato_sandwich", new class_1792(new class_1792.class_1793().method_19265(GotItems.sandwichFood(3, 0.4f))));
    public static final class_1792 TOMATO_SOUP = GotItems.register("tomato_soup", new class_1792(new class_1792.class_1793().method_7889(1).method_7896(class_1802.field_8428).method_19265(GotItems.soupFood())));
    public static final class_1792 VEGETABLE_SOUP = GotItems.register("vegetable_soup", new class_1792(new class_1792.class_1793().method_7889(1).method_7896(class_1802.field_8428).method_19265(GotItems.soupFood())));
    public static final class_1792 BEER = GotItems.register("beer", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(3, 0.5f, new class_1293(class_1294.field_5898, 1200, 0)), EMPTY_CUP, BrewItem.itemLoreLine("Absorption for (1:00)")));
    public static final class_1792 APPLE_JUICE = GotItems.register("apple_juice", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(3, 0.5f, new class_1293(class_1294.field_5922, 1200, 0)), EMPTY_CUP, BrewItem.itemLoreLine("Saturation for (1:00)")));
    public static final class_1792 VODKA = GotItems.register("vodka", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(3, 0.5f, new class_1293(class_1294.field_5917, 1800, 0)), EMPTY_CUP, BrewItem.itemLoreLine("Haste for (1:30)")));
    public static final class_1792 NETHER_WART_STOUT = GotItems.register("nether_wart_stout", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(4, 0.6f, new class_1293(class_1294.field_5918, 2400, 0)), EMPTY_CUP, BrewItem.itemLoreLine("Fire Resistance for (2:00)")));
    public static final class_1792 CHORUS_FRUIT_CIDER = GotItems.register("chorus_fruit_cider", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(3, 0.5f, new class_1293(class_1294.field_5902, 200, 0)), EMPTY_CUP, BrewItem.itemLoreLine("Levitation for (0:10)")));
    public static final class_1792 MELON_MEAD = GotItems.register("melon_mead", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(3, 0.5f, new class_1293(class_1294.field_5924, 600, 0)), EMPTY_CUP, BrewItem.itemLoreLine("Regeneration for (0:30)")));
    public static final class_1792 CARROT_LAGER = GotItems.register("carrot_lager", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(3, 0.5f, new class_1293(class_1294.field_5925, 3600, 0)), EMPTY_CUP, BrewItem.itemLoreLine("Night Vision for (3:00)")));
    public static final class_1792 BEETROOT_BREW = GotItems.register("beetroot_brew", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(3, 0.5f, new class_1293(class_1294.field_5915, 1, 1)), EMPTY_CUP, BrewItem.itemLoreLine("Instant Health II")));
    public static final class_1792 PUMPKIN_ALE = GotItems.register("pumpkin_ale", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(3, 0.5f, new class_1293(class_1294.field_5907, 1800, 0)), EMPTY_CUP, BrewItem.itemLoreLine("Resistance for (1:30)")));
    public static final class_1792 SWEET_BERRY_WINE = GotItems.register("sweet_berry_wine", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(4, 0.7f, new class_1293(class_1294.field_5904, 1200, 0)), EMPTY_CUP, BrewItem.itemLoreLine("Speed for (1:00)")));
    public static final class_1792 KELP_KOMBUCHA = GotItems.register("kelp_kombucha", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(3, 0.5f, new class_1293(class_1294.field_5923, 3600, 0)), EMPTY_CUP, BrewItem.itemLoreLine("Water Breathing for (3:00)")));
    public static final class_1792 GLOW_BERRY_GOSE = GotItems.register("glow_berry_gose", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(4, 0.7f, new class_1293(class_1294.field_5912, 1800, 0)), EMPTY_CUP, BrewItem.itemLoreLine("Glowing for (1:30)")));
    public static final class_1792 RED_WINE_SILVER_CUP = GotItems.register("red_wine_silver_cup", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(4, 0.7f, new class_1293(class_1294.field_5910, 1200, 1)), EMPTY_SILVER_CUP, BrewItem.itemLoreLine("Strength II for (1:00)")));
    public static final class_1792 WHITE_WINE_SILVER_CUP = GotItems.register("white_wine_silver_cup", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(4, 0.7f, new class_1293(class_1294.field_5904, 1200, 1)), EMPTY_SILVER_CUP, BrewItem.itemLoreLine("Speed II for (1:00)")));
    public static final class_1792 SPOILED_BREW = GotItems.register("spoiled_brew", new BrewItem(new class_1792.class_1793().method_7889(1), GotItems.brewFood(1, 0.7f, new class_1293(class_1294.field_5916, 1200, 1)), EMPTY_CUP, BrewItem.itemLoreLine("Nausea II for (1:00)")));
    public static final class_1792 RUNE_FRAGMENT = GotItems.register("rune_fragment", new RuneFragmentItem(new class_1792.class_1793()));
    public static final RuneItem RUNE_UNBREAKING = GotItems.register("rune_unbreaking", new RuneItem((class_5321<class_1887>)class_1893.field_9119, 3, "What is dead may never die", new class_1792.class_1793().method_7889(1)));
    public static final RuneItem RUNE_POWER = GotItems.register("rune_power", new RuneItem((class_5321<class_1887>)class_1893.field_9103, 5, "In the name of the Seven", new class_1792.class_1793().method_7889(1)));
    public static final RuneItem RUNE_FIRE = GotItems.register("rune_fire", new RuneItem((class_5321<class_1887>)class_1893.field_9124, 2, "For the Lord of Light", new class_1792.class_1793().method_7889(1)));
    public static final RuneItem RUNE_PROTECTION = GotItems.register("rune_protection", new RuneItem((class_5321<class_1887>)class_1893.field_9111, 4, "By the Old Gods", new class_1792.class_1793().method_7889(1)));

    private GotItems() {
    }

    public static void initialize() {
        GotArmorMaterials.initialize();
        ItemGroupEvents.modifyEntriesEvent((class_5321)class_7706.field_40202).register(entries -> {
            entries.method_45421((class_1935)COPPER_SWORD);
            entries.method_45421((class_1935)COPPER_HELMET);
            entries.method_45421((class_1935)COPPER_CHESTPLATE);
            entries.method_45421((class_1935)COPPER_LEGGINGS);
            entries.method_45421((class_1935)COPPER_BOOTS);
            entries.method_45421((class_1935)STEEL_SWORD);
            entries.method_45421((class_1935)STEEL_HELMET);
            entries.method_45421((class_1935)STEEL_CHESTPLATE);
            entries.method_45421((class_1935)STEEL_LEGGINGS);
            entries.method_45421((class_1935)STEEL_BOOTS);
            entries.method_45421((class_1935)WOODEN_SPEAR);
            entries.method_45421((class_1935)STONE_SPEAR);
            entries.method_45421((class_1935)COPPER_SPEAR);
            entries.method_45421((class_1935)GOLDEN_SPEAR);
            entries.method_45421((class_1935)IRON_SPEAR);
            entries.method_45421((class_1935)DIAMOND_SPEAR);
            entries.method_45421((class_1935)STEEL_SPEAR);
            entries.method_45421((class_1935)NETHERITE_SPEAR);
            entries.method_45421((class_1935)WOODEN_HALBERD);
            entries.method_45421((class_1935)STONE_HALBERD);
            entries.method_45421((class_1935)COPPER_HALBERD);
            entries.method_45421((class_1935)IRON_HALBERD);
            entries.method_45421((class_1935)STEEL_HALBERD);
            entries.method_45421((class_1935)WOODEN_KNIFE);
            entries.method_45421((class_1935)STONE_KNIFE);
            entries.method_45421((class_1935)COPPER_KNIFE);
            entries.method_45421((class_1935)IRON_KNIFE);
            entries.method_45421((class_1935)STEEL_KNIFE);
            entries.method_45421((class_1935)WOODEN_MACE);
            entries.method_45421((class_1935)STONE_MACE);
            entries.method_45421((class_1935)COPPER_MACE);
            entries.method_45421((class_1935)IRON_MACE);
            entries.method_45421((class_1935)STEEL_MACE);
            entries.method_45421((class_1935)WOODEN_GREATAXE);
            entries.method_45421((class_1935)STONE_GREATAXE);
            entries.method_45421((class_1935)COPPER_GREATAXE);
            entries.method_45421((class_1935)IRON_GREATAXE);
            entries.method_45421((class_1935)STEEL_GREATAXE);
            entries.method_45421((class_1935)WOODEN_WARHAMMER);
            entries.method_45421((class_1935)STONE_WARHAMMER);
            entries.method_45421((class_1935)COPPER_WARHAMMER);
            entries.method_45421((class_1935)IRON_WARHAMMER);
            entries.method_45421((class_1935)STEEL_WARHAMMER);
            entries.method_45421((class_1935)ROUND_SHIELD);
            entries.method_45421((class_1935)SQUARE_SHIELD);
            entries.method_45421((class_1935)KITE_SHIELD);
            entries.method_45421((class_1935)LONGBOW);
            entries.method_45421((class_1935)LANNISTER_HELMET);
            entries.method_45421((class_1935)LANNISTER_CHESTPLATE);
            entries.method_45421((class_1935)LANNISTER_LEGGINGS);
            entries.method_45421((class_1935)LANNISTER_BOOTS);
            entries.method_45421((class_1935)CLEGANE_HELMET);
            entries.method_45421((class_1935)CLEGANE_CHESTPLATE);
            entries.method_45421((class_1935)CLEGANE_LEGGINGS);
            entries.method_45421((class_1935)CLEGANE_BOOTS);
            entries.method_45421((class_1935)MARBRAND_HELMET);
            entries.method_45421((class_1935)MARBRAND_CHESTPLATE);
            entries.method_45421((class_1935)MARBRAND_LEGGINGS);
            entries.method_45421((class_1935)MARBRAND_BOOTS);
            entries.method_45421((class_1935)LANNISTER_PLATE);
            entries.method_45421((class_1935)MARBRAND_PLATE);
            entries.method_45421((class_1935)ARRYN_HELMET);
            entries.method_45421((class_1935)ARRYN_CHESTPLATE);
            entries.method_45421((class_1935)ARRYN_LEGGINGS);
            entries.method_45421((class_1935)ARRYN_BOOTS);
            entries.method_45421((class_1935)ARRYN_PLATE);
            entries.method_45421((class_1935)MARTELL_HELMET);
            entries.method_45421((class_1935)MARTELL_CHESTPLATE);
            entries.method_45421((class_1935)MARTELL_LEGGINGS);
            entries.method_45421((class_1935)MARTELL_BOOTS);
            entries.method_45421((class_1935)MARTELL_PLATE);
            entries.method_45421((class_1935)DAYNE_HELMET);
            entries.method_45421((class_1935)DAYNE_CHESTPLATE);
            entries.method_45421((class_1935)DAYNE_LEGGINGS);
            entries.method_45421((class_1935)DAYNE_BOOTS);
            entries.method_45421((class_1935)DAYNE_PLATE);
            entries.method_45421((class_1935)YRONWOOD_HELMET);
            entries.method_45421((class_1935)YRONWOOD_CHESTPLATE);
            entries.method_45421((class_1935)YRONWOOD_LEGGINGS);
            entries.method_45421((class_1935)YRONWOOD_BOOTS);
            entries.method_45421((class_1935)YRONWOOD_PLATE);
            entries.method_45421((class_1935)BARATHEON_HELMET);
            entries.method_45421((class_1935)BARATHEON_STAG_HELMET);
            entries.method_45421((class_1935)BARATHEON_CHESTPLATE);
            entries.method_45421((class_1935)BARATHEON_LEGGINGS);
            entries.method_45421((class_1935)BARATHEON_BOOTS);
            entries.method_45421((class_1935)BARATHEON_PLATE);
            entries.method_45421((class_1935)PYKE_HELMET);
            entries.method_45421((class_1935)PYKE_CHESTPLATE);
            entries.method_45421((class_1935)PYKE_LEGGINGS);
            entries.method_45421((class_1935)PYKE_BOOTS);
            entries.method_45421((class_1935)PYKE_PLATE);
            entries.method_45421((class_1935)HARLAW_HELMET);
            entries.method_45421((class_1935)HARLAW_CHESTPLATE);
            entries.method_45421((class_1935)HARLAW_LEGGINGS);
            entries.method_45421((class_1935)HARLAW_BOOTS);
            entries.method_45421((class_1935)HARLAW_PLATE);
            entries.method_45421((class_1935)TYRELL_HELMET);
            entries.method_45421((class_1935)TYRELL_CHESTPLATE);
            entries.method_45421((class_1935)TYRELL_LEGGINGS);
            entries.method_45421((class_1935)TYRELL_BOOTS);
            entries.method_45421((class_1935)TYRELL_PLATE);
            entries.method_45421((class_1935)HIGHTOWER_HELMET);
            entries.method_45421((class_1935)HIGHTOWER_CHESTPLATE);
            entries.method_45421((class_1935)HIGHTOWER_LEGGINGS);
            entries.method_45421((class_1935)HIGHTOWER_BOOTS);
            entries.method_45421((class_1935)HIGHTOWER_PLATE);
            entries.method_45421((class_1935)DRAGONSTONE_HELMET);
            entries.method_45421((class_1935)DRAGONSTONE_CHESTPLATE);
            entries.method_45421((class_1935)DRAGONSTONE_LEGGINGS);
            entries.method_45421((class_1935)DRAGONSTONE_BOOTS);
            entries.method_45421((class_1935)DRAGONSTONE_PLATE);
            entries.method_45421((class_1935)VELARYON_HELMET);
            entries.method_45421((class_1935)VELARYON_CHESTPLATE);
            entries.method_45421((class_1935)VELARYON_LEGGINGS);
            entries.method_45421((class_1935)VELARYON_BOOTS);
            entries.method_45421((class_1935)VELARYON_PLATE);
            entries.method_45421((class_1935)KINGSGUARD_HELMET);
            entries.method_45421((class_1935)KINGSGUARD_CHESTPLATE);
            entries.method_45421((class_1935)KINGSGUARD_LEGGINGS);
            entries.method_45421((class_1935)KINGSGUARD_BOOTS);
            entries.method_45421((class_1935)KINGSGUARD_PLATE);
            entries.method_45421((class_1935)GOLDCLOAK_HELMET);
            entries.method_45421((class_1935)GOLDCLOAK_CHESTPLATE);
            entries.method_45421((class_1935)GOLDCLOAK_LEGGINGS);
            entries.method_45421((class_1935)GOLDCLOAK_BOOTS);
            entries.method_45421((class_1935)GOLDCLOAK_PLATE);
            entries.method_45421((class_1935)TULLY_HELMET);
            entries.method_45421((class_1935)TULLY_CHESTPLATE);
            entries.method_45421((class_1935)TULLY_LEGGINGS);
            entries.method_45421((class_1935)TULLY_BOOTS);
            entries.method_45421((class_1935)TULLY_PLATE);
            entries.method_45421((class_1935)BLACKWOOD_HELMET);
            entries.method_45421((class_1935)BLACKWOOD_CHESTPLATE);
            entries.method_45421((class_1935)BLACKWOOD_LEGGINGS);
            entries.method_45421((class_1935)BLACKWOOD_BOOTS);
            entries.method_45421((class_1935)BLACKWOOD_PLATE);
            entries.method_45421((class_1935)STARK_HELMET);
            entries.method_45421((class_1935)STARK_CHESTPLATE);
            entries.method_45421((class_1935)STARK_LEGGINGS);
            entries.method_45421((class_1935)STARK_BOOTS);
            entries.method_45421((class_1935)STARK_PLATE);
            entries.method_45421((class_1935)KARSTARK_HELMET);
            entries.method_45421((class_1935)KARSTARK_CHESTPLATE);
            entries.method_45421((class_1935)KARSTARK_LEGGINGS);
            entries.method_45421((class_1935)KARSTARK_BOOTS);
            entries.method_45421((class_1935)KARSTARK_PLATE);
            entries.method_45421((class_1935)THE_NIGHTS_WATCH_HELMET);
            entries.method_45421((class_1935)THE_NIGHTS_WATCH_CHESTPLATE);
            entries.method_45421((class_1935)THE_NIGHTS_WATCH_LEGGINGS);
            entries.method_45421((class_1935)THE_NIGHTS_WATCH_BOOTS);
            entries.method_45421((class_1935)THE_NIGHTS_WATCH_PLATE);
            entries.method_45421((class_1935)TARGARYEN_HELMET);
            entries.method_45421((class_1935)TARGARYEN_CHESTPLATE);
            entries.method_45421((class_1935)TARGARYEN_LEGGINGS);
            entries.method_45421((class_1935)TARGARYEN_BOOTS);
            entries.method_45421((class_1935)TARGARYEN_PLATE);
            entries.method_45421((class_1935)BRAAVOS_HELMET);
            entries.method_45421((class_1935)BRAAVOS_CHESTPLATE);
            entries.method_45421((class_1935)BRAAVOS_LEGGINGS);
            entries.method_45421((class_1935)BRAAVOS_BOOTS);
            entries.method_45421((class_1935)BRAAVOS_PLATE);
            entries.method_45421((class_1935)PENTOS_HELMET);
            entries.method_45421((class_1935)PENTOS_CHESTPLATE);
            entries.method_45421((class_1935)PENTOS_LEGGINGS);
            entries.method_45421((class_1935)PENTOS_BOOTS);
            entries.method_45421((class_1935)PENTOS_PLATE);
            entries.method_45421((class_1935)VOLANTIS_HELMET);
            entries.method_45421((class_1935)VOLANTIS_CHESTPLATE);
            entries.method_45421((class_1935)VOLANTIS_LEGGINGS);
            entries.method_45421((class_1935)VOLANTIS_BOOTS);
            entries.method_45421((class_1935)VOLANTIS_PLATE);
            entries.method_45421((class_1935)TYROSH_HELMET);
            entries.method_45421((class_1935)TYROSH_CHESTPLATE);
            entries.method_45421((class_1935)TYROSH_LEGGINGS);
            entries.method_45421((class_1935)TYROSH_BOOTS);
            entries.method_45421((class_1935)TYROSH_PLATE);
            entries.method_45421((class_1935)MYR_HELMET);
            entries.method_45421((class_1935)MYR_CHESTPLATE);
            entries.method_45421((class_1935)MYR_LEGGINGS);
            entries.method_45421((class_1935)MYR_BOOTS);
            entries.method_45421((class_1935)MYR_PLATE);
            entries.method_45421((class_1935)BLACKFYRE_HELMET);
            entries.method_45421((class_1935)BLACKFYRE_CHESTPLATE);
            entries.method_45421((class_1935)BLACKFYRE_LEGGINGS);
            entries.method_45421((class_1935)BLACKFYRE_BOOTS);
            entries.method_45421((class_1935)BLACKFYRE_PLATE);
            entries.method_45421((class_1935)GOLDEN_COMPANY_HELMET);
            entries.method_45421((class_1935)GOLDEN_COMPANY_CHESTPLATE);
            entries.method_45421((class_1935)GOLDEN_COMPANY_LEGGINGS);
            entries.method_45421((class_1935)GOLDEN_COMPANY_BOOTS);
            entries.method_45421((class_1935)GOLDEN_COMPANY_PLATE);
            entries.method_45421((class_1935)MEEREEN_HELMET);
            entries.method_45421((class_1935)MEEREEN_CHESTPLATE);
            entries.method_45421((class_1935)MEEREEN_LEGGINGS);
            entries.method_45421((class_1935)MEEREEN_BOOTS);
            entries.method_45421((class_1935)MEEREEN_PLATE);
            entries.method_45421((class_1935)YUNKAI_HELMET);
            entries.method_45421((class_1935)YUNKAI_CHESTPLATE);
            entries.method_45421((class_1935)YUNKAI_LEGGINGS);
            entries.method_45421((class_1935)YUNKAI_BOOTS);
            entries.method_45421((class_1935)YUNKAI_PLATE);
            entries.method_45421((class_1935)ASTAPOR_HELMET);
            entries.method_45421((class_1935)ASTAPOR_CHESTPLATE);
            entries.method_45421((class_1935)ASTAPOR_LEGGINGS);
            entries.method_45421((class_1935)ASTAPOR_BOOTS);
            entries.method_45421((class_1935)ASTAPOR_PLATE);
            entries.method_45421((class_1935)QARTH_HELMET);
            entries.method_45421((class_1935)QARTH_CHESTPLATE);
            entries.method_45421((class_1935)QARTH_LEGGINGS);
            entries.method_45421((class_1935)QARTH_BOOTS);
            entries.method_45421((class_1935)QARTH_PLATE);
            entries.method_45421((class_1935)FAITHMILITANT_HELMET);
            entries.method_45421((class_1935)FAITHMILITANT_CHESTPLATE);
            entries.method_45421((class_1935)FAITHMILITANT_LEGGINGS);
            entries.method_45421((class_1935)FAITHMILITANT_BOOTS);
            entries.method_45421((class_1935)GAMBESON_HELMET);
            entries.method_45421((class_1935)GAMBESON_CHESTPLATE);
            entries.method_45421((class_1935)GAMBESON_LEGGINGS);
            entries.method_45421((class_1935)GAMBESON_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent((class_5321)class_7706.field_41060).register(entries -> {
            entries.method_45421((class_1935)COPPER_PICKAXE);
            entries.method_45421((class_1935)COPPER_AXE);
            entries.method_45421((class_1935)COPPER_SHOVEL);
            entries.method_45421((class_1935)COPPER_HOE);
            entries.method_45421((class_1935)STEEL_PICKAXE);
            entries.method_45421((class_1935)STEEL_AXE);
            entries.method_45421((class_1935)STEEL_SHOVEL);
            entries.method_45421((class_1935)STEEL_HOE);
            entries.method_45421((class_1935)BLACKSMITH_HAMMER);
            entries.method_45421((class_1935)WORLD_MAP);
            entries.method_45421((class_1935)JOURNAL);
            entries.method_45421((class_1935)GUIDEBOOK);
            entries.method_45421((class_1935)LOCKPICK);
            entries.method_45421((class_1935)TENT);
            entries.method_45421((class_1935)CART);
            entries.method_45421((class_1935)CART_2HORSE);
            entries.method_45421((class_1935)CATAPULT);
            entries.method_45421((class_1935)SCORPION_CROSSBOW);
            entries.method_45421((class_1935)SHIP);
            entries.method_45421((class_1935)GALLEY);
            entries.method_45421((class_1935)LONGSHIP);
            entries.method_45421((class_1935)GATE_MECHANISM);
        });
        ItemGroupEvents.modifyEntriesEvent((class_5321)class_7706.field_41062).register(entries -> {
            entries.method_45421((class_1935)COPPER_NUGGET);
            entries.method_45421((class_1935)COPPER_CROWN);
            entries.method_45421((class_1935)SILVER_NUGGET);
            entries.method_45421((class_1935)SILVER_STAG);
            entries.method_45421((class_1935)GOLDEN_DRAGON);
            entries.method_45421((class_1935)RAW_SILVER);
            entries.method_45421((class_1935)SILVER_INGOT);
            entries.method_45421((class_1935)STEEL_LUMP);
            entries.method_45421((class_1935)STEEL_INGOT);
            entries.method_45421((class_1935)STEEL_PLATE);
            entries.method_45421((class_1935)DEER_ANTLERS);
            entries.method_45421((class_1935)FLAX_SEEDS);
            entries.method_45421((class_1935)FLAX_BUNDLE);
            entries.method_45421((class_1935)FABRIC);
            entries.method_45421((class_1935)HOP);
            entries.method_45421((class_1935)HOP_SEEDS);
            entries.method_45421((class_1935)PURPLE_GRAPES);
            entries.method_45421((class_1935)GREEN_GRAPES);
            entries.method_45421((class_1935)TOMATO);
            entries.method_45421((class_1935)ORANGE);
            entries.method_45421((class_1935)TURNIP);
            entries.method_45421((class_1935)CHAMPIGNON);
            entries.method_45421((class_1935)SLICED_BREAD);
            entries.method_45421((class_1935)BEEF_SANDWICH);
            entries.method_45421((class_1935)BEEF_TOMATO_SANDWICH);
            entries.method_45421((class_1935)TOMATO_SANDWICH);
            entries.method_45421((class_1935)TOMATO_SOUP);
            entries.method_45421((class_1935)VEGETABLE_SOUP);
            entries.method_45421((class_1935)EMPTY_CUP);
            entries.method_45421((class_1935)EMPTY_SILVER_CUP);
            entries.method_45421((class_1935)RUNE_FRAGMENT);
            entries.method_45421((class_1935)RUNE_UNBREAKING);
            entries.method_45421((class_1935)RUNE_POWER);
            entries.method_45421((class_1935)RUNE_FIRE);
            entries.method_45421((class_1935)RUNE_PROTECTION);
            entries.method_45421((class_1935)ARRYN_BANNER_PATTERN);
            entries.method_45421((class_1935)BARATHEON_BANNER_PATTERN);
            entries.method_45421((class_1935)GREYJOY_BANNER_PATTERN);
            entries.method_45421((class_1935)LANNISTER_BANNER_PATTERN);
            entries.method_45421((class_1935)MARTELL_BANNER_PATTERN);
            entries.method_45421((class_1935)STARK_BANNER_PATTERN);
            entries.method_45421((class_1935)TULLY_BANNER_PATTERN);
        });
        ItemGroupEvents.modifyEntriesEvent((class_5321)class_7706.field_41061).register(entries -> {
            entries.method_45421((class_1935)BEER);
            entries.method_45421((class_1935)APPLE_JUICE);
            entries.method_45421((class_1935)VODKA);
            entries.method_45421((class_1935)NETHER_WART_STOUT);
            entries.method_45421((class_1935)CHORUS_FRUIT_CIDER);
            entries.method_45421((class_1935)MELON_MEAD);
            entries.method_45421((class_1935)CARROT_LAGER);
            entries.method_45421((class_1935)BEETROOT_BREW);
            entries.method_45421((class_1935)PUMPKIN_ALE);
            entries.method_45421((class_1935)SWEET_BERRY_WINE);
            entries.method_45421((class_1935)KELP_KOMBUCHA);
            entries.method_45421((class_1935)GLOW_BERRY_GOSE);
            entries.method_45421((class_1935)RED_WINE_SILVER_CUP);
            entries.method_45421((class_1935)WHITE_WINE_SILVER_CUP);
            entries.method_45421((class_1935)SPOILED_BREW);
            entries.method_45421((class_1935)PURPLE_GRAPES);
            entries.method_45421((class_1935)GREEN_GRAPES);
            entries.method_45421((class_1935)TOMATO);
            entries.method_45421((class_1935)ORANGE);
            entries.method_45421((class_1935)TURNIP);
            entries.method_45421((class_1935)CHAMPIGNON);
            entries.method_45421((class_1935)SLICED_BREAD);
            entries.method_45421((class_1935)BEEF_SANDWICH);
            entries.method_45421((class_1935)BEEF_TOMATO_SANDWICH);
            entries.method_45421((class_1935)TOMATO_SANDWICH);
            entries.method_45421((class_1935)TOMATO_SOUP);
            entries.method_45421((class_1935)VEGETABLE_SOUP);
        });
    }

    private static <T extends class_1792> T register(String path, T item) {
        return (T)((class_1792)class_2378.method_10230((class_2378)class_7923.field_41178, (class_2960)class_2960.method_60655((String)"agob", (String)path), item));
    }

    private static class_1738 registerArmor(String path, class_6880<class_1741> material, class_1738.class_8051 type) {
        return GotItems.register(path, new class_1738(material, type, new class_1792.class_1793().method_7889(1)));
    }

    private static class_1792.class_1793 dyedGambesonSettings() {
        return new class_1792.class_1793().method_7889(1).method_57349(class_9334.field_49644, (Object)new class_9282(-6265536, false));
    }

    private static class_4174 brewFood(int nutrition, float saturation, class_1293 effect) {
        return new class_4174.class_4175().method_19238(nutrition).method_19237(saturation).method_19240().method_19239(effect, 1.0f).method_19242();
    }

    private static class_4174 snackFood() {
        return new class_4174.class_4175().method_19238(1).method_19237(0.7f).method_19242();
    }

    private static class_4174 lightSnackFood() {
        return new class_4174.class_4175().method_19238(1).method_19237(0.2f).method_19242();
    }

    private static class_4174 slicedBreadFood() {
        return new class_4174.class_4175().method_19238(2).method_19237(0.6f).method_19242();
    }

    private static class_4174 sandwichFood(int nutrition, float saturation) {
        return new class_4174.class_4175().method_19238(nutrition).method_19237(saturation).method_19242();
    }

    private static class_4174 soupFood() {
        return new class_4174.class_4175().method_19238(6).method_19237(0.6f).method_19242();
    }

    public static class_1792 spearForTier(int tier) {
        return tier >= 2 ? IRON_SPEAR : STONE_SPEAR;
    }

    public static boolean isVariantShield(class_1799 stack) {
        return GotItems.isVariantShield(stack.method_7909());
    }

    public static boolean isVariantShield(class_1792 item) {
        return item == ROUND_SHIELD || item == SQUARE_SHIELD || item == KITE_SHIELD;
    }

    private static SpearItem createSpear(class_1832 material, boolean fireproof) {
        class_1792.class_1793 settings = new class_1792.class_1793().method_7889(1);
        if (fireproof) {
            settings = settings.method_24359();
        }
        return new SpearItem(material, GotItems.spearAttackDamageBonus(material), GotItems.swingDurationFor(material), 2.0f, 5.5f, 0.25f, 12, 10, GotItems.chargeDamageMultiplierFor(material), GotItems.minSpeedForChargeDamage(material), GotItems.minSpeedForDismount(material), GotItems.engageWindowTicksFor(material), settings);
    }

    private static SpearItem createHalberd(class_1832 material) {
        return new SpearItem(material, GotItems.halberdAttackDamageBonus(material), GotItems.halberdSwingDurationFor(material), 2.0f, 5.5f, 0.25f, 14, 10, GotItems.halberdChargeDamageMultiplierFor(material), GotItems.minSpeedForChargeDamage(material), GotItems.minSpeedForDismount(material), GotItems.engageWindowTicksFor(material), new class_1792.class_1793().method_7889(1));
    }

    private static class_1792 createGreataxe(class_1832 material) {
        return new class_1743(material, new class_1792.class_1793().method_7889(1).method_57348(class_1743.method_57346((class_1832)material, (float)7.0f, (float)-3.0f)));
    }

    private static class_1792 createKnife(class_1832 material) {
        return new CustomMeleeWeaponItem(material, 1.0f, -1.8f, 2.5f, new class_1792.class_1793().method_7889(1).method_7895(material.method_8025()));
    }

    private static class_1792 createWarhammer(class_1832 material) {
        return new class_1743(material, new class_1792.class_1793().method_7889(1).method_57348(class_1743.method_57346((class_1832)material, (float)8.0f, (float)-3.45f)));
    }

    private static class_1792 createMace(class_1832 material) {
        return new VariantMaceItem(material, 3.0f, -3.4f, GotItems.smashDamageMultiplierFor(material), new class_1792.class_1793().method_7895(material.method_8025()));
    }

    private static float swingDurationFor(class_1832 material) {
        if (material == class_1834.field_8922) {
            return 0.65f;
        }
        if (material == class_1834.field_8927) {
            return 0.75f;
        }
        if (material == COPPER_TOOL_MATERIAL) {
            return 0.8f;
        }
        if (material == class_1834.field_8929) {
            return 0.95f;
        }
        if (material == class_1834.field_8923) {
            return 0.85f;
        }
        if (material == STEEL_TOOL_MATERIAL) {
            return 1.0f;
        }
        if (material == VALYRIAN_STEEL_TOOL_MATERIAL) {
            return 0.85f;
        }
        if (material == class_1834.field_8930) {
            return 1.05f;
        }
        return 1.15f;
    }

    private static float chargeDamageMultiplierFor(class_1832 material) {
        if (material == class_1834.field_8922) {
            return 0.7f;
        }
        if (material == class_1834.field_8927) {
            return 0.8f;
        }
        if (material == COPPER_TOOL_MATERIAL) {
            return 0.85f;
        }
        if (material == class_1834.field_8929) {
            return 0.7f;
        }
        if (material == class_1834.field_8923) {
            return 0.9f;
        }
        if (material == STEEL_TOOL_MATERIAL) {
            return 1.1f;
        }
        if (material == VALYRIAN_STEEL_TOOL_MATERIAL) {
            return 1.2f;
        }
        if (material == class_1834.field_8930) {
            return 1.075f;
        }
        return 1.2f;
    }

    private static float minSpeedForChargeDamage(class_1832 material) {
        if (material == class_1834.field_8922) {
            return 1.2f;
        }
        if (material == class_1834.field_8927) {
            return 1.15f;
        }
        if (material == COPPER_TOOL_MATERIAL) {
            return 1.1f;
        }
        if (material == class_1834.field_8929) {
            return 1.1f;
        }
        if (material == class_1834.field_8923) {
            return 1.0f;
        }
        if (material == STEEL_TOOL_MATERIAL) {
            return 0.95f;
        }
        if (material == VALYRIAN_STEEL_TOOL_MATERIAL) {
            return 0.9f;
        }
        if (material == class_1834.field_8930) {
            return 0.95f;
        }
        return 0.9f;
    }

    private static float minSpeedForDismount(class_1832 material) {
        if (material == class_1834.field_8922) {
            return 2.4f;
        }
        if (material == class_1834.field_8927) {
            return 2.2f;
        }
        if (material == COPPER_TOOL_MATERIAL) {
            return 2.1f;
        }
        if (material == class_1834.field_8929) {
            return 2.2f;
        }
        if (material == class_1834.field_8923) {
            return 2.0f;
        }
        if (material == STEEL_TOOL_MATERIAL) {
            return 1.9f;
        }
        if (material == VALYRIAN_STEEL_TOOL_MATERIAL) {
            return 1.7f;
        }
        if (material == class_1834.field_8930) {
            return 1.9f;
        }
        return 1.8f;
    }

    private static float spearAttackDamageBonus(class_1832 material) {
        if (material == class_1834.field_8922) {
            return 0.0f;
        }
        if (material == class_1834.field_8927) {
            return 1.0f;
        }
        if (material == COPPER_TOOL_MATERIAL) {
            return 1.5f;
        }
        if (material == class_1834.field_8929) {
            return 0.0f;
        }
        if (material == class_1834.field_8923) {
            return 2.0f;
        }
        if (material == STEEL_TOOL_MATERIAL) {
            return 3.0f;
        }
        if (material == VALYRIAN_STEEL_TOOL_MATERIAL) {
            return 3.5f;
        }
        if (material == class_1834.field_8930) {
            return 3.0f;
        }
        return 4.0f;
    }

    private static float halberdAttackDamageBonus(class_1832 material) {
        return GotItems.spearAttackDamageBonus(material) + 1.0f;
    }

    private static float halberdSwingDurationFor(class_1832 material) {
        if (material == class_1834.field_8922) {
            return 0.9f;
        }
        if (material == class_1834.field_8927) {
            return 1.0f;
        }
        if (material == COPPER_TOOL_MATERIAL) {
            return 1.05f;
        }
        if (material == class_1834.field_8923) {
            return 1.1f;
        }
        if (material == STEEL_TOOL_MATERIAL) {
            return 1.2f;
        }
        if (material == VALYRIAN_STEEL_TOOL_MATERIAL) {
            return 1.3f;
        }
        return GotItems.swingDurationFor(material) + 0.2f;
    }

    private static float halberdChargeDamageMultiplierFor(class_1832 material) {
        return GotItems.chargeDamageMultiplierFor(material) + 0.1f;
    }

    private static int engageWindowTicksFor(class_1832 material) {
        if (material == class_1834.field_8922) {
            return 100;
        }
        if (material == class_1834.field_8927) {
            return 90;
        }
        if (material == COPPER_TOOL_MATERIAL) {
            return 80;
        }
        if (material == class_1834.field_8929) {
            return 80;
        }
        if (material == class_1834.field_8923) {
            return 70;
        }
        if (material == STEEL_TOOL_MATERIAL) {
            return 60;
        }
        if (material == VALYRIAN_STEEL_TOOL_MATERIAL) {
            return 50;
        }
        if (material == class_1834.field_8930) {
            return 60;
        }
        return 50;
    }

    private static float smashDamageMultiplierFor(class_1832 material) {
        if (material == class_1834.field_8922) {
            return 0.65f;
        }
        if (material == class_1834.field_8927) {
            return 0.8f;
        }
        if (material == COPPER_TOOL_MATERIAL) {
            return 0.9f;
        }
        if (material == class_1834.field_8923) {
            return 1.0f;
        }
        if (material == STEEL_TOOL_MATERIAL) {
            return 1.15f;
        }
        if (material == VALYRIAN_STEEL_TOOL_MATERIAL) {
            return 1.2f;
        }
        return 1.0f;
    }
}

