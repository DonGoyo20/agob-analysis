/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 */
package trep.got.registry;

import java.util.Collection;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import trep.got.village.resource.CropVillageResource;
import trep.got.village.resource.HorseVillageResource;
import trep.got.village.resource.OreVillageResource;
import trep.got.village.resource.VillageResource;
import trep.got.village.resource.VillageResourceRegistry;
import trep.got.village.resource.WoodVillageResource;

public final class GotVillageResources {
    private static final VillageResourceRegistry REGISTRY = new VillageResourceRegistry();
    public static final VillageResource WHEAT = GotVillageResources.register(new CropVillageResource(class_2960.method_60655((String)"agob", (String)"wheat"), (class_2561)class_2561.method_43471((String)"resource.agob.wheat"), class_1802.field_8861));
    public static final VillageResource CARROTS = GotVillageResources.register(new CropVillageResource(class_2960.method_60655((String)"agob", (String)"carrots"), (class_2561)class_2561.method_43471((String)"resource.agob.carrots"), class_1802.field_8179));
    public static final VillageResource POTATOES = GotVillageResources.register(new CropVillageResource(class_2960.method_60655((String)"agob", (String)"potatoes"), (class_2561)class_2561.method_43471((String)"resource.agob.potatoes"), class_1802.field_8567));
    public static final VillageResource HORSES = GotVillageResources.register(new HorseVillageResource(class_2960.method_60655((String)"agob", (String)"horses"), (class_2561)class_2561.method_43471((String)"resource.agob.horses")));
    public static final VillageResource WOOD_OAK = GotVillageResources.register(new WoodVillageResource(class_2960.method_60655((String)"agob", (String)"wood_oak"), (class_2561)class_2561.method_43471((String)"resource.agob.wood_oak"), class_1802.field_8583));
    public static final VillageResource WOOD_SPRUCE = GotVillageResources.register(new WoodVillageResource(class_2960.method_60655((String)"agob", (String)"wood_spruce"), (class_2561)class_2561.method_43471((String)"resource.agob.wood_spruce"), class_1802.field_8684));
    public static final VillageResource WOOD_BIRCH = GotVillageResources.register(new WoodVillageResource(class_2960.method_60655((String)"agob", (String)"wood_birch"), (class_2561)class_2561.method_43471((String)"resource.agob.wood_birch"), class_1802.field_8170));
    public static final VillageResource WOOD_JUNGLE = GotVillageResources.register(new WoodVillageResource(class_2960.method_60655((String)"agob", (String)"wood_jungle"), (class_2561)class_2561.method_43471((String)"resource.agob.wood_jungle"), class_1802.field_8125));
    public static final VillageResource WOOD_ACACIA = GotVillageResources.register(new WoodVillageResource(class_2960.method_60655((String)"agob", (String)"wood_acacia"), (class_2561)class_2561.method_43471((String)"resource.agob.wood_acacia"), class_1802.field_8820));
    public static final VillageResource WOOD_DARK_OAK = GotVillageResources.register(new WoodVillageResource(class_2960.method_60655((String)"agob", (String)"wood_dark_oak"), (class_2561)class_2561.method_43471((String)"resource.agob.wood_dark_oak"), class_1802.field_8652));
    public static final VillageResource COPPER_ORE = GotVillageResources.register(new OreVillageResource(class_2960.method_60655((String)"agob", (String)"copper_ore"), (class_2561)class_2561.method_43471((String)"resource.agob.copper_ore"), class_1802.field_33401));
    public static final VillageResource IRON_ORE = GotVillageResources.register(new OreVillageResource(class_2960.method_60655((String)"agob", (String)"iron_ore"), (class_2561)class_2561.method_43471((String)"resource.agob.iron_ore"), class_1802.field_33400));
    public static final VillageResource GOLD_ORE = GotVillageResources.register(new OreVillageResource(class_2960.method_60655((String)"agob", (String)"gold_ore"), (class_2561)class_2561.method_43471((String)"resource.agob.gold_ore"), class_1802.field_33402));

    private GotVillageResources() {
    }

    public static VillageResource register(VillageResource resource) {
        return REGISTRY.register(resource);
    }

    public static VillageResource get(class_2960 id) {
        return id == null ? null : REGISTRY.get(id);
    }

    public static Collection<VillageResource> getAll() {
        return REGISTRY.getAll();
    }
}

