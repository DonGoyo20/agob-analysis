/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
 *  net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
 *  net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry$TexturedModelDataProvider
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1921
 *  net.minecraft.class_1935
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4608
 *  net.minecraft.class_5601
 *  net.minecraft.class_572
 *  net.minecraft.class_630
 *  net.minecraft.class_9282
 */
package trep.got.client.render.armor;

import java.util.function.Function;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5601;
import net.minecraft.class_572;
import net.minecraft.class_630;
import net.minecraft.class_9282;
import trep.got.client.entity.EntityRenderGeometry;
import trep.got.client.physics.CapeClothState;
import trep.got.client.physics.ClientPhysicsRegistry;
import trep.got.client.render.armor.ArrynArmorModelLayer1;
import trep.got.client.render.armor.ArrynArmorModelLayer2;
import trep.got.client.render.armor.BaratheonArmorModelLayer1;
import trep.got.client.render.armor.BaratheonArmorModelLayer2;
import trep.got.client.render.armor.BaratheonStagArmorModelLayer1;
import trep.got.client.render.armor.CleganeArmorModelLayer1;
import trep.got.client.render.armor.DragonstoneArmorModelLayer1;
import trep.got.client.render.armor.DragonstoneArmorModelLayer2;
import trep.got.client.render.armor.LannisterArmorModelLayer1;
import trep.got.client.render.armor.LannisterArmorModelLayer2;
import trep.got.client.render.armor.MartellArmorModelLayer1;
import trep.got.client.render.armor.MartellArmorModelLayer2;
import trep.got.client.render.armor.PykeArmorModelLayer1;
import trep.got.client.render.armor.PykeArmorModelLayer2;
import trep.got.client.render.armor.StarkArmorModelLayer1;
import trep.got.client.render.armor.StarkArmorModelLayer2;
import trep.got.client.render.armor.TullyArmorModelLayer1;
import trep.got.client.render.armor.TullyArmorModelLayer2;
import trep.got.client.render.armor.TyrellArmorModelLayer1;
import trep.got.client.render.armor.TyrellArmorModelLayer2;
import trep.got.client.render.item.RuneGlintTextures;
import trep.got.registry.GotItems;

@Environment(value=EnvType.CLIENT)
public final class HouseArmorRenderer
implements ArmorRenderer {
    private static final class_2960 LANNISTER_CAPE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/armor/cape/lannister_cape.png");
    private static final class_2960 STARK_CAPE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/armor/cape/stark_cape.png");
    private static final class_2960 DRAGONSTONE_CAPE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/armor/cape/dragonstone_cape.png");
    private static final class_2960 PYKE_CAPE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/armor/cape/pyke_cape.png");
    private static final class_2960 KINGSGUARD_CAPE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/armor/cape/kingsguard_cape.png");
    private static final class_2960 GOLDCLOAK_CAPE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/armor/cape/goldcloak_cape.png");
    private static final float CAPE_WIDTH = 0.625f;
    private static final float CAPE_HEIGHT = 1.1875f;
    private static final float CAPE_PLANE_Z = -0.125f;
    private static final float CAPE_PIVOT_Y = 0.25f;
    private static final float CAPE_PIVOT_Z = 0.3125f;
    private static final HouseArmorRenderer LANNISTER = HouseArmorRenderer.create("lannister_armor", "textures/armor/armor_layer_1.png", "textures/armor/armor_layer_2.png", LannisterArmorModelLayer1::new, LannisterArmorModelLayer2::new, -1.0f, (class_1792)GotItems.LANNISTER_HELMET, (class_1792)GotItems.LANNISTER_CHESTPLATE, (class_1792)GotItems.LANNISTER_LEGGINGS, (class_1792)GotItems.LANNISTER_BOOTS);
    private static final HouseArmorRenderer ARRYN = HouseArmorRenderer.create("arryn_armor", "textures/armor/arryn_armor_layer_1.png", "textures/armor/arryn_armor_layer_2.png", ArrynArmorModelLayer1::new, ArrynArmorModelLayer2::new, -1.0f, (class_1792)GotItems.ARRYN_HELMET, (class_1792)GotItems.ARRYN_CHESTPLATE, (class_1792)GotItems.ARRYN_LEGGINGS, (class_1792)GotItems.ARRYN_BOOTS);
    private static final HouseArmorRenderer BARATHEON = HouseArmorRenderer.create("baratheon_armor", "textures/armor/baratheon_armor_layer_1.png", "textures/armor/baratheon_armor_layer_2.png", BaratheonArmorModelLayer1::new, BaratheonArmorModelLayer2::new, -1.0f, (class_1792)GotItems.BARATHEON_HELMET, (class_1792)GotItems.BARATHEON_CHESTPLATE, (class_1792)GotItems.BARATHEON_LEGGINGS, (class_1792)GotItems.BARATHEON_BOOTS);
    private static final HouseArmorRenderer BARATHEON_STAG = HouseArmorRenderer.createHelmet("baratheon_stag_armor", "textures/armor/baratheon_stag_armor_layer_1.png", "textures/armor/baratheon_armor_layer_2.png", BaratheonStagArmorModelLayer1::new, BaratheonArmorModelLayer2::new, -1.0f, (class_1792)GotItems.BARATHEON_STAG_HELMET);
    private static final HouseArmorRenderer CLEGANE = HouseArmorRenderer.create("clegane_armor", "textures/armor/clegane_armor_layer_1.png", "textures/armor/clegane_armor_layer_2.png", CleganeArmorModelLayer1::new, BaratheonArmorModelLayer2::new, -1.0f, (class_1792)GotItems.CLEGANE_HELMET, (class_1792)GotItems.CLEGANE_CHESTPLATE, (class_1792)GotItems.CLEGANE_LEGGINGS, (class_1792)GotItems.CLEGANE_BOOTS);
    private static final HouseArmorRenderer MARBRAND = HouseArmorRenderer.create("marbrand_armor", "textures/armor/marbrand_armor_layer_1.png", "textures/armor/marbrand_armor_layer_2.png", CleganeArmorModelLayer1::new, BaratheonArmorModelLayer2::new, -1.0f, (class_1792)GotItems.MARBRAND_HELMET, (class_1792)GotItems.MARBRAND_CHESTPLATE, (class_1792)GotItems.MARBRAND_LEGGINGS, (class_1792)GotItems.MARBRAND_BOOTS);
    private static final HouseArmorRenderer TYRELL = HouseArmorRenderer.create("tyrell_armor", "textures/armor/tyrell_armor_layer_1.png", "textures/armor/tyrell_armor_layer_2.png", TyrellArmorModelLayer1::new, TyrellArmorModelLayer2::new, -1.0f, (class_1792)GotItems.TYRELL_HELMET, (class_1792)GotItems.TYRELL_CHESTPLATE, (class_1792)GotItems.TYRELL_LEGGINGS, (class_1792)GotItems.TYRELL_BOOTS);
    private static final HouseArmorRenderer HIGHTOWER = HouseArmorRenderer.create("hightower_armor", "textures/armor/hightower_armor_layer_1.png", "textures/armor/hightower_armor_layer_2.png", CleganeArmorModelLayer1::new, BaratheonArmorModelLayer2::new, -1.0f, (class_1792)GotItems.HIGHTOWER_HELMET, (class_1792)GotItems.HIGHTOWER_CHESTPLATE, (class_1792)GotItems.HIGHTOWER_LEGGINGS, (class_1792)GotItems.HIGHTOWER_BOOTS);
    private static final HouseArmorRenderer DRAGONSTONE = HouseArmorRenderer.create("dragonstone_armor", "textures/armor/dragonstone_armor_layer_1.png", "textures/armor/dragonstone_armor_layer_2.png", DragonstoneArmorModelLayer1::new, DragonstoneArmorModelLayer2::new, -1.0f, (class_1792)GotItems.DRAGONSTONE_HELMET, (class_1792)GotItems.DRAGONSTONE_CHESTPLATE, (class_1792)GotItems.DRAGONSTONE_LEGGINGS, (class_1792)GotItems.DRAGONSTONE_BOOTS);
    private static final HouseArmorRenderer VELARYON = HouseArmorRenderer.create("velaryon_armor", "textures/armor/velaryon_armor_layer_1.png", "textures/armor/velaryon_armor_layer_2.png", CleganeArmorModelLayer1::new, BaratheonArmorModelLayer2::new, -1.0f, (class_1792)GotItems.VELARYON_HELMET, (class_1792)GotItems.VELARYON_CHESTPLATE, (class_1792)GotItems.VELARYON_LEGGINGS, (class_1792)GotItems.VELARYON_BOOTS);
    private static final HouseArmorRenderer KINGSGUARD = HouseArmorRenderer.create("kingsguard_armor", "textures/armor/kingsguard_layer_1.png", "textures/armor/kingsguard_layer_2.png", ArrynArmorModelLayer1::new, ArrynArmorModelLayer2::new, -1.0f, (class_1792)GotItems.KINGSGUARD_HELMET, (class_1792)GotItems.KINGSGUARD_CHESTPLATE, (class_1792)GotItems.KINGSGUARD_LEGGINGS, (class_1792)GotItems.KINGSGUARD_BOOTS);
    private static final HouseArmorRenderer TULLY = HouseArmorRenderer.create("tully_armor", "textures/armor/tully_armor_layer_1.png", "textures/armor/tully_armor_layer_2.png", TullyArmorModelLayer1::new, TullyArmorModelLayer2::new, -1.0f, (class_1792)GotItems.TULLY_HELMET, (class_1792)GotItems.TULLY_CHESTPLATE, (class_1792)GotItems.TULLY_LEGGINGS, (class_1792)GotItems.TULLY_BOOTS);
    private static final HouseArmorRenderer BLACKWOOD = HouseArmorRenderer.create("blackwood_armor", "textures/armor/blackwood_armor_layer_1.png", "textures/armor/blackwood_armor_layer_2.png", CleganeArmorModelLayer1::new, BaratheonArmorModelLayer2::new, -1.0f, (class_1792)GotItems.BLACKWOOD_HELMET, (class_1792)GotItems.BLACKWOOD_CHESTPLATE, (class_1792)GotItems.BLACKWOOD_LEGGINGS, (class_1792)GotItems.BLACKWOOD_BOOTS);
    private static final HouseArmorRenderer STARK = HouseArmorRenderer.create("stark_armor", "textures/armor/stark_armor_layer_1.png", "textures/armor/stark_armor_layer_2.png", StarkArmorModelLayer1::new, StarkArmorModelLayer2::new, -1.0f, (class_1792)GotItems.STARK_HELMET, (class_1792)GotItems.STARK_CHESTPLATE, (class_1792)GotItems.STARK_LEGGINGS, (class_1792)GotItems.STARK_BOOTS);
    private static final HouseArmorRenderer KARSTARK = HouseArmorRenderer.create("karstark_armor", "textures/armor/karstark_armor_layer_1.png", "textures/armor/karstark_armor_layer_2.png", CleganeArmorModelLayer1::new, BaratheonArmorModelLayer2::new, -1.0f, (class_1792)GotItems.KARSTARK_HELMET, (class_1792)GotItems.KARSTARK_CHESTPLATE, (class_1792)GotItems.KARSTARK_LEGGINGS, (class_1792)GotItems.KARSTARK_BOOTS);
    private static final HouseArmorRenderer MARTELL = HouseArmorRenderer.create("martell_armor", "textures/armor/martell_armor_layer_1.png", "textures/armor/martell_armor_layer_2.png", MartellArmorModelLayer1::new, MartellArmorModelLayer2::new, -1.0f, (class_1792)GotItems.MARTELL_HELMET, (class_1792)GotItems.MARTELL_CHESTPLATE, (class_1792)GotItems.MARTELL_LEGGINGS, (class_1792)GotItems.MARTELL_BOOTS);
    private static final HouseArmorRenderer DAYNE = HouseArmorRenderer.create("dayne_armor", "textures/armor/dayne_armor_layer_1.png", "textures/armor/dayne_armor_layer_2.png", CleganeArmorModelLayer1::new, BaratheonArmorModelLayer2::new, -1.0f, (class_1792)GotItems.DAYNE_HELMET, (class_1792)GotItems.DAYNE_CHESTPLATE, (class_1792)GotItems.DAYNE_LEGGINGS, (class_1792)GotItems.DAYNE_BOOTS);
    private static final HouseArmorRenderer YRONWOOD = HouseArmorRenderer.create("yronwood_armor", "textures/armor/yronwood_armor_layer_1.png", "textures/armor/yronwood_armor_layer_2.png", CleganeArmorModelLayer1::new, BaratheonArmorModelLayer2::new, -1.0f, (class_1792)GotItems.YRONWOOD_HELMET, (class_1792)GotItems.YRONWOOD_CHESTPLATE, (class_1792)GotItems.YRONWOOD_LEGGINGS, (class_1792)GotItems.YRONWOOD_BOOTS);
    private static final HouseArmorRenderer FAITHMILITANT = HouseArmorRenderer.create("faithmilitant_armor", "textures/armor/faithmilitant_armor_layer_1.png", "textures/armor/faithmilitant_armor_layer_2.png", MartellArmorModelLayer1::new, MartellArmorModelLayer2::new, -1.0f, (class_1792)GotItems.FAITHMILITANT_HELMET, (class_1792)GotItems.FAITHMILITANT_CHESTPLATE, (class_1792)GotItems.FAITHMILITANT_LEGGINGS, (class_1792)GotItems.FAITHMILITANT_BOOTS);
    private static final HouseArmorRenderer GOLDCLOAK = HouseArmorRenderer.create("goldcloak_armor", "textures/armor/goldcloak_armor_layer_1.png", "textures/armor/goldcloak_armor_layer_2.png", MartellArmorModelLayer1::new, MartellArmorModelLayer2::new, -1.0f, (class_1792)GotItems.GOLDCLOAK_HELMET, (class_1792)GotItems.GOLDCLOAK_CHESTPLATE, (class_1792)GotItems.GOLDCLOAK_LEGGINGS, (class_1792)GotItems.GOLDCLOAK_BOOTS);
    private static final HouseArmorRenderer PYKE = HouseArmorRenderer.create("pyke_armor", "textures/armor/pyke_armor_layer_1.png", "textures/armor/pyke_armor_layer_2.png", PykeArmorModelLayer1::new, PykeArmorModelLayer2::new, -1.0f, (class_1792)GotItems.PYKE_HELMET, (class_1792)GotItems.PYKE_CHESTPLATE, (class_1792)GotItems.PYKE_LEGGINGS, (class_1792)GotItems.PYKE_BOOTS);
    private static final HouseArmorRenderer HARLAW = HouseArmorRenderer.create("harlaw_armor", "textures/armor/harlaw_armor_layer_1.png", "textures/armor/harlaw_armor_layer_2.png", CleganeArmorModelLayer1::new, BaratheonArmorModelLayer2::new, -1.0f, (class_1792)GotItems.HARLAW_HELMET, (class_1792)GotItems.HARLAW_CHESTPLATE, (class_1792)GotItems.HARLAW_LEGGINGS, (class_1792)GotItems.HARLAW_BOOTS);
    private static final HouseArmorRenderer GAMBESON = HouseArmorRenderer.createDyeable("gambeson_armor", "textures/armor/gambeson_armor_layer_1.png", "textures/armor/gambeson_armor_layer_2.png", MartellArmorModelLayer1::new, MartellArmorModelLayer2::new, -1.0f, (class_1792)GotItems.GAMBESON_HELMET, (class_1792)GotItems.GAMBESON_CHESTPLATE, (class_1792)GotItems.GAMBESON_LEGGINGS, (class_1792)GotItems.GAMBESON_BOOTS);
    private static final HouseArmorRenderer THE_NIGHTS_WATCH = HouseArmorRenderer.createDefault("the_nights_watch", (class_1792)GotItems.THE_NIGHTS_WATCH_HELMET, (class_1792)GotItems.THE_NIGHTS_WATCH_CHESTPLATE, (class_1792)GotItems.THE_NIGHTS_WATCH_LEGGINGS, (class_1792)GotItems.THE_NIGHTS_WATCH_BOOTS);
    private static final HouseArmorRenderer WILDLING = HouseArmorRenderer.create("wildling_armor", "textures/armor/wildling_armor_layer_1.png", "textures/armor/wildling_armor_layer_2.png", StarkArmorModelLayer1::new, StarkArmorModelLayer2::new, -1.0f, (class_1792)GotItems.WILDLING_HELMET, (class_1792)GotItems.WILDLING_CHESTPLATE, (class_1792)GotItems.WILDLING_LEGGINGS, (class_1792)GotItems.WILDLING_BOOTS);
    private static final HouseArmorRenderer TARGARYEN = HouseArmorRenderer.create("targaryen_armor", "textures/armor/targaryen_layer_1.png", "textures/models/armor/targaryen_layer_2.png", TyrellArmorModelLayer1::new, TyrellArmorModelLayer2::new, -1.0f, (class_1792)GotItems.TARGARYEN_HELMET, (class_1792)GotItems.TARGARYEN_CHESTPLATE, (class_1792)GotItems.TARGARYEN_LEGGINGS, (class_1792)GotItems.TARGARYEN_BOOTS);
    private static final HouseArmorRenderer BRAAVOS = HouseArmorRenderer.createDefault("braavos", (class_1792)GotItems.BRAAVOS_HELMET, (class_1792)GotItems.BRAAVOS_CHESTPLATE, (class_1792)GotItems.BRAAVOS_LEGGINGS, (class_1792)GotItems.BRAAVOS_BOOTS);
    private static final HouseArmorRenderer PENTOS = HouseArmorRenderer.createDefault("pentos", (class_1792)GotItems.PENTOS_HELMET, (class_1792)GotItems.PENTOS_CHESTPLATE, (class_1792)GotItems.PENTOS_LEGGINGS, (class_1792)GotItems.PENTOS_BOOTS);
    private static final HouseArmorRenderer VOLANTIS = HouseArmorRenderer.createDefault("volantis", (class_1792)GotItems.VOLANTIS_HELMET, (class_1792)GotItems.VOLANTIS_CHESTPLATE, (class_1792)GotItems.VOLANTIS_LEGGINGS, (class_1792)GotItems.VOLANTIS_BOOTS);
    private static final HouseArmorRenderer TYROSH = HouseArmorRenderer.createDefault("tyrosh", (class_1792)GotItems.TYROSH_HELMET, (class_1792)GotItems.TYROSH_CHESTPLATE, (class_1792)GotItems.TYROSH_LEGGINGS, (class_1792)GotItems.TYROSH_BOOTS);
    private static final HouseArmorRenderer MYR = HouseArmorRenderer.createDefault("myr", (class_1792)GotItems.MYR_HELMET, (class_1792)GotItems.MYR_CHESTPLATE, (class_1792)GotItems.MYR_LEGGINGS, (class_1792)GotItems.MYR_BOOTS);
    private static final HouseArmorRenderer BLACKFYRE = HouseArmorRenderer.create("blackfyre_armor", "textures/armor/blackfyre_layer_1.png", "textures/models/armor/blackfyre_layer_2.png", BaratheonArmorModelLayer1::new, MartellArmorModelLayer2::new, -1.0f, (class_1792)GotItems.BLACKFYRE_HELMET, (class_1792)GotItems.BLACKFYRE_CHESTPLATE, (class_1792)GotItems.BLACKFYRE_LEGGINGS, (class_1792)GotItems.BLACKFYRE_BOOTS);
    private static final HouseArmorRenderer GOLDEN_COMPANY = HouseArmorRenderer.createDefault("golden_company", (class_1792)GotItems.GOLDEN_COMPANY_HELMET, (class_1792)GotItems.GOLDEN_COMPANY_CHESTPLATE, (class_1792)GotItems.GOLDEN_COMPANY_LEGGINGS, (class_1792)GotItems.GOLDEN_COMPANY_BOOTS);
    private static final HouseArmorRenderer MEEREEN = HouseArmorRenderer.createDefault("meereen", (class_1792)GotItems.MEEREEN_HELMET, (class_1792)GotItems.MEEREEN_CHESTPLATE, (class_1792)GotItems.MEEREEN_LEGGINGS, (class_1792)GotItems.MEEREEN_BOOTS);
    private static final HouseArmorRenderer YUNKAI = HouseArmorRenderer.createDefault("yunkai", (class_1792)GotItems.YUNKAI_HELMET, (class_1792)GotItems.YUNKAI_CHESTPLATE, (class_1792)GotItems.YUNKAI_LEGGINGS, (class_1792)GotItems.YUNKAI_BOOTS);
    private static final HouseArmorRenderer ASTAPOR = HouseArmorRenderer.createDefault("astapor", (class_1792)GotItems.ASTAPOR_HELMET, (class_1792)GotItems.ASTAPOR_CHESTPLATE, (class_1792)GotItems.ASTAPOR_LEGGINGS, (class_1792)GotItems.ASTAPOR_BOOTS);
    private static final HouseArmorRenderer QARTH = HouseArmorRenderer.createDefault("qarth", (class_1792)GotItems.QARTH_HELMET, (class_1792)GotItems.QARTH_CHESTPLATE, (class_1792)GotItems.QARTH_LEGGINGS, (class_1792)GotItems.QARTH_BOOTS);
    private final class_5601 layer1Id;
    private final class_5601 layer2Id;
    private final class_2960 layer1Texture;
    private final class_2960 layer2Texture;
    private final Function<class_630, class_572<class_1309>> layer1Factory;
    private final Function<class_630, class_572<class_1309>> layer2Factory;
    private final float headPivotOffset;
    private class_572<class_1309> layer1Model;
    private class_572<class_1309> layer2Model;
    private class_1792[] items;
    private boolean dyeable;

    private HouseArmorRenderer(class_5601 layer1Id, class_5601 layer2Id, class_2960 layer1Texture, class_2960 layer2Texture, Function<class_630, class_572<class_1309>> layer1Factory, Function<class_630, class_572<class_1309>> layer2Factory, float headPivotOffset) {
        this.layer1Id = layer1Id;
        this.layer2Id = layer2Id;
        this.layer1Texture = layer1Texture;
        this.layer2Texture = layer2Texture;
        this.layer1Factory = layer1Factory;
        this.layer2Factory = layer2Factory;
        this.headPivotOffset = headPivotOffset;
    }

    public static void initialize() {
        HouseArmorRenderer.register(LANNISTER, LannisterArmorModelLayer1::getTexturedModelData, LannisterArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(ARRYN, ArrynArmorModelLayer1::getTexturedModelData, ArrynArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(BARATHEON, BaratheonArmorModelLayer1::getTexturedModelData, BaratheonArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(BARATHEON_STAG, BaratheonStagArmorModelLayer1::getTexturedModelData, BaratheonArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(CLEGANE, CleganeArmorModelLayer1::getTexturedModelData, BaratheonArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(MARBRAND, CleganeArmorModelLayer1::getTexturedModelData, BaratheonArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(TYRELL, TyrellArmorModelLayer1::getTexturedModelData, TyrellArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(HIGHTOWER, CleganeArmorModelLayer1::getTexturedModelData, BaratheonArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(DRAGONSTONE, DragonstoneArmorModelLayer1::getTexturedModelData, DragonstoneArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(VELARYON, CleganeArmorModelLayer1::getTexturedModelData, BaratheonArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(KINGSGUARD, ArrynArmorModelLayer1::getTexturedModelData, ArrynArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(TULLY, TullyArmorModelLayer1::getTexturedModelData, TullyArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(BLACKWOOD, CleganeArmorModelLayer1::getTexturedModelData, BaratheonArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(STARK, StarkArmorModelLayer1::getTexturedModelData, StarkArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(KARSTARK, CleganeArmorModelLayer1::getTexturedModelData, BaratheonArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(MARTELL, MartellArmorModelLayer1::getTexturedModelData, MartellArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(DAYNE, CleganeArmorModelLayer1::getTexturedModelData, BaratheonArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(YRONWOOD, CleganeArmorModelLayer1::getTexturedModelData, BaratheonArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(FAITHMILITANT, MartellArmorModelLayer1::getTexturedModelData, MartellArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(GOLDCLOAK, MartellArmorModelLayer1::getTexturedModelData, MartellArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(PYKE, PykeArmorModelLayer1::getTexturedModelData, PykeArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(HARLAW, CleganeArmorModelLayer1::getTexturedModelData, BaratheonArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(GAMBESON, MartellArmorModelLayer1::getTexturedModelData, MartellArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.registerDefault(THE_NIGHTS_WATCH);
        HouseArmorRenderer.register(WILDLING, StarkArmorModelLayer1::getTexturedModelData, StarkArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.register(TARGARYEN, TyrellArmorModelLayer1::getTexturedModelData, TyrellArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.registerDefault(BRAAVOS);
        HouseArmorRenderer.registerDefault(PENTOS);
        HouseArmorRenderer.registerDefault(VOLANTIS);
        HouseArmorRenderer.registerDefault(TYROSH);
        HouseArmorRenderer.registerDefault(MYR);
        HouseArmorRenderer.register(BLACKFYRE, BaratheonArmorModelLayer1::getTexturedModelData, MartellArmorModelLayer2::getTexturedModelData);
        HouseArmorRenderer.registerDefault(GOLDEN_COMPANY);
        HouseArmorRenderer.registerDefault(MEEREEN);
        HouseArmorRenderer.registerDefault(YUNKAI);
        HouseArmorRenderer.registerDefault(ASTAPOR);
        HouseArmorRenderer.registerDefault(QARTH);
    }

    public void render(class_4587 matrices, class_4597 vertexConsumers, class_1799 stack, class_1309 entity, class_1304 slot, int light, class_572<class_1309> contextModel) {
        class_2960 texture;
        RuneGlintTextures.pushCurrentStack(stack);
        class_572<class_1309> model = this.resolveModel(stack, slot);
        contextModel.method_2818(model);
        model.method_2805(false);
        switch (slot) {
            case field_6169: {
                model.field_3398.field_3656 = contextModel.field_3398.field_3656 + this.headPivotOffset;
                model.field_3398.field_3665 = true;
                class_2960 class_29602 = this.layer1Texture;
                break;
            }
            case field_6174: {
                model.field_3391.field_3665 = true;
                model.field_3401.field_3665 = true;
                model.field_27433.field_3665 = true;
                class_2960 class_29602 = this.layer1Texture;
                break;
            }
            case field_6172: {
                model.field_3391.field_3665 = true;
                model.field_3392.field_3665 = true;
                model.field_3397.field_3665 = true;
                class_2960 class_29602 = this.layer2Texture;
                break;
            }
            case field_6166: {
                model.field_3392.field_3665 = true;
                model.field_3397.field_3665 = true;
                class_2960 class_29602 = this.layer1Texture;
                break;
            }
            default: {
                class_2960 class_29602 = texture = null;
            }
        }
        if (texture != null) {
            if (this.dyeable) {
                HouseArmorRenderer.renderDyedPart(matrices, vertexConsumers, light, stack, model, texture);
            } else {
                ArmorRenderer.renderPart((class_4587)matrices, (class_4597)vertexConsumers, (int)light, (class_1799)stack, model, (class_2960)texture);
            }
            class_2960 capeTexture = HouseArmorRenderer.getCapeTexture(stack);
            if (slot == class_1304.field_6174 && capeTexture != null) {
                HouseArmorRenderer.renderCape(matrices, vertexConsumers, light, entity, model, capeTexture);
            }
        }
        RuneGlintTextures.clearCurrentStack();
    }

    private static void renderDyedPart(class_4587 matrices, class_4597 vertexConsumers, int light, class_1799 stack, class_572<class_1309> model, class_2960 texture) {
        int color = 0xFF000000 | class_9282.method_57470((class_1799)stack, (int)-6265536);
        class_4588 consumer = vertexConsumers.getBuffer(model.method_23500(texture));
        model.method_2828(matrices, consumer, light, class_4608.field_21444, color);
    }

    private class_572<class_1309> resolveModel(class_1799 stack, class_1304 slot) {
        if (slot == class_1304.field_6169 && stack.method_31574((class_1792)GotItems.KINGSGUARD_HELMET)) {
            return TYRELL.getLayer1Model();
        }
        return slot == class_1304.field_6172 ? this.getLayer2Model() : this.getLayer1Model();
    }

    private static class_2960 getCapeTexture(class_1799 stack) {
        if (stack.method_31574((class_1792)GotItems.LANNISTER_CHESTPLATE)) {
            return LANNISTER_CAPE_TEXTURE;
        }
        if (stack.method_31574((class_1792)GotItems.STARK_CHESTPLATE)) {
            return STARK_CAPE_TEXTURE;
        }
        if (stack.method_31574((class_1792)GotItems.DRAGONSTONE_CHESTPLATE)) {
            return DRAGONSTONE_CAPE_TEXTURE;
        }
        if (stack.method_31574((class_1792)GotItems.KINGSGUARD_CHESTPLATE)) {
            return KINGSGUARD_CAPE_TEXTURE;
        }
        if (stack.method_31574((class_1792)GotItems.PYKE_CHESTPLATE)) {
            return PYKE_CAPE_TEXTURE;
        }
        if (stack.method_31574((class_1792)GotItems.GOLDCLOAK_CHESTPLATE)) {
            return GOLDCLOAK_CAPE_TEXTURE;
        }
        return null;
    }

    private static void renderCape(class_4587 matrices, class_4597 vertexConsumers, int light, class_1309 entity, class_572<class_1309> model, class_2960 capeTexture) {
        CapeClothState state = ClientPhysicsRegistry.tickLannisterCape(entity);
        class_4588 consumer = vertexConsumers.getBuffer(class_1921.method_23578((class_2960)capeTexture));
        matrices.method_22903();
        model.field_3391.method_22703(matrices);
        matrices.method_46416(0.0f, 0.25f, 0.3125f);
        HouseArmorRenderer.renderCapeCloth(matrices, consumer, light, state.bend());
        matrices.method_22909();
    }

    private static void renderCapeCloth(class_4587 matrices, class_4588 consumer, int light, float bend) {
        int strips = 10;
        float halfWidth = 0.3125f;
        for (int index = 0; index < strips; ++index) {
            float t0 = (float)index / (float)strips;
            float t1 = (float)(index + 1) / (float)strips;
            float y0 = class_3532.method_16439((float)t0, (float)-0.3125f, (float)0.875f);
            float y1 = class_3532.method_16439((float)t1, (float)-0.3125f, (float)0.875f);
            float z0 = -0.125f + HouseArmorRenderer.capeCurve(t0) * bend;
            float z1 = -0.125f + HouseArmorRenderer.capeCurve(t1) * bend;
            float v0 = class_3532.method_16439((float)t0, (float)0.0f, (float)0.59375f);
            float v1 = class_3532.method_16439((float)t1, (float)0.0f, (float)0.59375f);
            EntityRenderGeometry.emitQuad(consumer, matrices, light, class_4608.field_21444, 0.0f, 0.0f, -1.0f, halfWidth, y0, z0, 0.15625f, v0, -halfWidth, y0, z0, 0.0f, v0, -halfWidth, y1, z1, 0.0f, v1, halfWidth, y1, z1, 0.15625f, v1);
        }
    }

    private static float capeCurve(float t) {
        return t * t;
    }

    private class_572<class_1309> getLayer1Model() {
        if (this.layer1Model == null) {
            this.layer1Model = this.layer1Factory.apply(class_310.method_1551().method_31974().method_32072(this.layer1Id));
        }
        return this.layer1Model;
    }

    private class_572<class_1309> getLayer2Model() {
        if (this.layer2Model == null) {
            this.layer2Model = this.layer2Factory.apply(class_310.method_1551().method_31974().method_32072(this.layer2Id));
        }
        return this.layer2Model;
    }

    private static HouseArmorRenderer create(String id, String layer1Texture, String layer2Texture, Function<class_630, class_572<class_1309>> layer1Factory, Function<class_630, class_572<class_1309>> layer2Factory, float headPivotOffset, class_1792 helmet, class_1792 chestplate, class_1792 leggings, class_1792 boots) {
        class_5601 layer1Id = new class_5601(class_2960.method_60655((String)"agob", (String)id), "layer_1");
        class_5601 layer2Id = new class_5601(class_2960.method_60655((String)"agob", (String)id), "layer_2");
        HouseArmorRenderer renderer = new HouseArmorRenderer(layer1Id, layer2Id, class_2960.method_60655((String)"agob", (String)layer1Texture), class_2960.method_60655((String)"agob", (String)layer2Texture), layer1Factory, layer2Factory, headPivotOffset);
        renderer.items = new class_1792[]{helmet, chestplate, leggings, boots};
        return renderer;
    }

    private static HouseArmorRenderer createDefault(String id, class_1792 helmet, class_1792 chestplate, class_1792 leggings, class_1792 boots) {
        return HouseArmorRenderer.create(id + "_armor", "textures/armor/" + id + "_layer_1.png", "textures/models/armor/" + id + "_layer_2.png", MartellArmorModelLayer1::new, MartellArmorModelLayer2::new, -1.0f, helmet, chestplate, leggings, boots);
    }

    private static HouseArmorRenderer createHelmet(String id, String layer1Texture, String layer2Texture, Function<class_630, class_572<class_1309>> layer1Factory, Function<class_630, class_572<class_1309>> layer2Factory, float headPivotOffset, class_1792 helmet) {
        HouseArmorRenderer renderer = HouseArmorRenderer.create(id, layer1Texture, layer2Texture, layer1Factory, layer2Factory, headPivotOffset, helmet, helmet, helmet, helmet);
        renderer.items = new class_1792[]{helmet};
        return renderer;
    }

    private static HouseArmorRenderer createDyeable(String id, String layer1Texture, String layer2Texture, Function<class_630, class_572<class_1309>> layer1Factory, Function<class_630, class_572<class_1309>> layer2Factory, float headPivotOffset, class_1792 helmet, class_1792 chestplate, class_1792 leggings, class_1792 boots) {
        HouseArmorRenderer renderer = HouseArmorRenderer.create(id, layer1Texture, layer2Texture, layer1Factory, layer2Factory, headPivotOffset, helmet, chestplate, leggings, boots);
        renderer.dyeable = true;
        return renderer;
    }

    private static void register(HouseArmorRenderer renderer, EntityModelLayerRegistry.TexturedModelDataProvider layer1Provider, EntityModelLayerRegistry.TexturedModelDataProvider layer2Provider) {
        EntityModelLayerRegistry.registerModelLayer((class_5601)renderer.layer1Id, (EntityModelLayerRegistry.TexturedModelDataProvider)layer1Provider);
        EntityModelLayerRegistry.registerModelLayer((class_5601)renderer.layer2Id, (EntityModelLayerRegistry.TexturedModelDataProvider)layer2Provider);
        ArmorRenderer.register((ArmorRenderer)renderer, (class_1935[])renderer.items);
    }

    private static void registerDefault(HouseArmorRenderer renderer) {
        HouseArmorRenderer.register(renderer, MartellArmorModelLayer1::getTexturedModelData, MartellArmorModelLayer2::getTexturedModelData);
    }
}

