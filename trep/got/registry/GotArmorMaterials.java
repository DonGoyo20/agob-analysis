/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1738$class_8051
 *  net.minecraft.class_1741
 *  net.minecraft.class_1741$class_9196
 *  net.minecraft.class_1802
 *  net.minecraft.class_1856
 *  net.minecraft.class_1935
 *  net.minecraft.class_2378
 *  net.minecraft.class_2960
 *  net.minecraft.class_3417
 *  net.minecraft.class_6880
 *  net.minecraft.class_7923
 */
package trep.got.registry;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1738;
import net.minecraft.class_1741;
import net.minecraft.class_1802;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3417;
import net.minecraft.class_6880;
import net.minecraft.class_7923;
import trep.got.registry.GotItems;

public final class GotArmorMaterials {
    private static final Map<class_1738.class_8051, Integer> IRON_DEFENSE = GotArmorMaterials.defense(2, 5, 6, 2);
    private static final Map<class_1738.class_8051, Integer> DIAMOND_DEFENSE = GotArmorMaterials.defense(3, 6, 8, 3);
    private static final Map<class_1738.class_8051, Integer> DRAGONSTONE_DEFENSE = GotArmorMaterials.defense(3, 6, 7, 3);
    private static final Map<class_1738.class_8051, Integer> GOLDCLOAK_DEFENSE = GotArmorMaterials.defense(3, 5, 7, 3);
    public static final class_6880<class_1741> COPPER = GotArmorMaterials.register("copper", new class_1741(IRON_DEFENSE, 9, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_27022}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"copper"))), 0.0f, 0.0f));
    public static final class_6880<class_1741> STEEL = GotArmorMaterials.register("steel", new class_1741(DIAMOND_DEFENSE, 10, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{GotItems.STEEL_INGOT}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"steel"))), 2.0f, 0.0f));
    public static final class_6880<class_1741> LANNISTER = GotArmorMaterials.register("lannister", new class_1741(IRON_DEFENSE, 9, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8620}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"lannister"))), 0.0f, 0.0f));
    public static final class_6880<class_1741> ARRYN = GotArmorMaterials.register("arryn", new class_1741(IRON_DEFENSE, 9, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8620}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"arryn"))), 0.0f, 0.0f));
    public static final class_6880<class_1741> BARATHEON = GotArmorMaterials.register("baratheon", new class_1741(IRON_DEFENSE, 9, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8620}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"baratheon"))), 0.0f, 0.0f));
    public static final class_6880<class_1741> TYRELL = GotArmorMaterials.register("tyrell", new class_1741(IRON_DEFENSE, 9, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8620}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"tyrell"))), 0.0f, 0.0f));
    public static final class_6880<class_1741> DRAGONSTONE = GotArmorMaterials.register("dragonstone", new class_1741(DRAGONSTONE_DEFENSE, 9, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8620}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"dragonstone"))), 1.0f, 0.0f));
    public static final class_6880<class_1741> TULLY = GotArmorMaterials.register("tully", new class_1741(IRON_DEFENSE, 9, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8620}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"tully"))), 0.0f, 0.0f));
    public static final class_6880<class_1741> STARK = GotArmorMaterials.register("stark", new class_1741(IRON_DEFENSE, 9, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8620}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"stark"))), 0.0f, 0.0f));
    public static final class_6880<class_1741> MARTELL = GotArmorMaterials.register("martell", new class_1741(IRON_DEFENSE, 9, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8620}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"martell"))), 0.0f, 0.0f));
    public static final class_6880<class_1741> PYKE = GotArmorMaterials.register("pyke", new class_1741(IRON_DEFENSE, 9, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8620}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"pyke"))), 0.0f, 0.0f));
    public static final class_6880<class_1741> KINGSGUARD = GotArmorMaterials.register("kingsguard", new class_1741(DIAMOND_DEFENSE, 15, class_3417.field_21866, () -> class_1856.method_8091((class_1935[])new class_1935[]{GotItems.STEEL_INGOT}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"kingsguard"))), 3.0f, 0.1f));
    public static final class_6880<class_1741> GOLDCLOAK = GotArmorMaterials.register("goldcloak", new class_1741(GOLDCLOAK_DEFENSE, 12, class_3417.field_14761, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8695}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"goldcloak"))), 1.0f, 0.0f));
    public static final class_6880<class_1741> GAMBESON = GotArmorMaterials.register("gambeson", new class_1741(((class_1741)COPPER.comp_349()).comp_2298(), 9, class_3417.field_14581, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8745, class_1802.field_27022}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)"gambeson"), "", true)), 0.0f, 0.0f));
    public static final class_6880<class_1741> THE_NIGHTS_WATCH = GotArmorMaterials.iron("the_nights_watch");
    public static final class_6880<class_1741> WILDLING = GotArmorMaterials.iron("wildling");
    public static final class_6880<class_1741> TARGARYEN = GotArmorMaterials.iron("targaryen");
    public static final class_6880<class_1741> BRAAVOS = GotArmorMaterials.iron("braavos");
    public static final class_6880<class_1741> PENTOS = GotArmorMaterials.iron("pentos");
    public static final class_6880<class_1741> VOLANTIS = GotArmorMaterials.iron("volantis");
    public static final class_6880<class_1741> TYROSH = GotArmorMaterials.iron("tyrosh");
    public static final class_6880<class_1741> MYR = GotArmorMaterials.iron("myr");
    public static final class_6880<class_1741> BLACKFYRE = GotArmorMaterials.iron("blackfyre");
    public static final class_6880<class_1741> GOLDEN_COMPANY = GotArmorMaterials.iron("golden_company");
    public static final class_6880<class_1741> MEEREEN = GotArmorMaterials.iron("meereen");
    public static final class_6880<class_1741> YUNKAI = GotArmorMaterials.iron("yunkai");
    public static final class_6880<class_1741> ASTAPOR = GotArmorMaterials.iron("astapor");
    public static final class_6880<class_1741> QARTH = GotArmorMaterials.iron("qarth");

    private GotArmorMaterials() {
    }

    public static void initialize() {
    }

    private static Map<class_1738.class_8051, Integer> defense(int boots, int leggings, int chestplate, int helmet) {
        EnumMap<class_1738.class_8051, Integer> defense = new EnumMap<class_1738.class_8051, Integer>(class_1738.class_8051.class);
        defense.put(class_1738.class_8051.field_41937, boots);
        defense.put(class_1738.class_8051.field_41936, leggings);
        defense.put(class_1738.class_8051.field_41935, chestplate);
        defense.put(class_1738.class_8051.field_41934, helmet);
        defense.put(class_1738.class_8051.field_48838, chestplate);
        return defense;
    }

    private static class_6880<class_1741> register(String path, class_1741 material) {
        return class_2378.method_47985((class_2378)class_7923.field_48976, (class_2960)class_2960.method_60655((String)"agob", (String)path), (Object)material);
    }

    private static class_6880<class_1741> iron(String path) {
        return GotArmorMaterials.register(path, new class_1741(IRON_DEFENSE, 9, class_3417.field_14862, () -> class_1856.method_8091((class_1935[])new class_1935[]{class_1802.field_8620}), List.of(new class_1741.class_9196(class_2960.method_60655((String)"agob", (String)path))), 0.0f, 0.0f));
    }
}

