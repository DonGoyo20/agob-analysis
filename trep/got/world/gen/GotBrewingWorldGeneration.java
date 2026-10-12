/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.biome.v1.BiomeModifications
 *  net.minecraft.class_2893$class_2895
 *  net.minecraft.class_2960
 *  net.minecraft.class_5321
 *  net.minecraft.class_6796
 *  net.minecraft.class_7924
 */
package trep.got.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.class_2893;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_6796;
import net.minecraft.class_7924;

public final class GotBrewingWorldGeneration {
    private static final class_5321<class_6796> HOP_PLACED = class_5321.method_29179((class_5321)class_7924.field_41245, (class_2960)class_2960.method_60655((String)"agob", (String)"hop_placed"));
    private static final class_5321<class_6796> GRAPE_PLACED = class_5321.method_29179((class_5321)class_7924.field_41245, (class_2960)class_2960.method_60655((String)"agob", (String)"grape_placed"));
    private static final class_5321<class_6796> WILD_TOMATO_PLACED = class_5321.method_29179((class_5321)class_7924.field_41245, (class_2960)class_2960.method_60655((String)"agob", (String)"wild_tomato_placed"));
    private static final class_5321<class_6796> WILD_ORANGE_PLACED = class_5321.method_29179((class_5321)class_7924.field_41245, (class_2960)class_2960.method_60655((String)"agob", (String)"wild_orange_placed"));
    private static final class_5321<class_6796> WILD_TURNIP_PLACED = class_5321.method_29179((class_5321)class_7924.field_41245, (class_2960)class_2960.method_60655((String)"agob", (String)"wild_turnip_placed"));
    private static final class_5321<class_6796> WILD_CHAMPIGNON_PLACED = class_5321.method_29179((class_5321)class_7924.field_41245, (class_2960)class_2960.method_60655((String)"agob", (String)"wild_champignon_placed"));

    private GotBrewingWorldGeneration() {
    }

    public static void initialize() {
        BiomeModifications.addFeature(context -> GotBrewingWorldGeneration.isHopBiome(context.getBiomeKey().method_29177().method_12832()), (class_2893.class_2895)class_2893.class_2895.field_13178, HOP_PLACED);
        BiomeModifications.addFeature(context -> GotBrewingWorldGeneration.isGrapeBiome(context.getBiomeKey().method_29177().method_12832()), (class_2893.class_2895)class_2893.class_2895.field_13178, GRAPE_PLACED);
        BiomeModifications.addFeature(context -> GotBrewingWorldGeneration.isTomatoBiome(context.getBiomeKey().method_29177().method_12832()), (class_2893.class_2895)class_2893.class_2895.field_13178, WILD_TOMATO_PLACED);
        BiomeModifications.addFeature(context -> GotBrewingWorldGeneration.isOrangeBiome(context.getBiomeKey().method_29177().method_12832()), (class_2893.class_2895)class_2893.class_2895.field_13178, WILD_ORANGE_PLACED);
        BiomeModifications.addFeature(context -> GotBrewingWorldGeneration.isTurnipBiome(context.getBiomeKey().method_29177().method_12832()), (class_2893.class_2895)class_2893.class_2895.field_13178, WILD_TURNIP_PLACED);
        BiomeModifications.addFeature(context -> GotBrewingWorldGeneration.isChampignonBiome(context.getBiomeKey().method_29177().method_12832()), (class_2893.class_2895)class_2893.class_2895.field_13178, WILD_CHAMPIGNON_PLACED);
    }

    private static boolean isHopBiome(String biomePath) {
        return biomePath.startsWith("the_riverlands") || biomePath.startsWith("the_stormlands") || biomePath.startsWith("the_reach") || biomePath.startsWith("the_westerlands") || biomePath.startsWith("the_crownlands");
    }

    private static boolean isGrapeBiome(String biomePath) {
        return "dorne_oasis".equals(biomePath) || biomePath.startsWith("the_reach");
    }

    private static boolean isTomatoBiome(String biomePath) {
        return GotBrewingWorldGeneration.isAllowedCropBiome(biomePath) && (biomePath.startsWith("the_reach") || biomePath.startsWith("the_crownlands") || biomePath.startsWith("the_stormlands") || biomePath.startsWith("dorne") || biomePath.startsWith("volantis") || biomePath.startsWith("slavers_bay") || biomePath.startsWith("step_stones"));
    }

    private static boolean isOrangeBiome(String biomePath) {
        return GotBrewingWorldGeneration.isAllowedCropBiome(biomePath) && (biomePath.startsWith("dorne") || biomePath.startsWith("the_reach") || biomePath.startsWith("the_crownlands") || biomePath.startsWith("volantis") || biomePath.startsWith("slavers_bay") || biomePath.startsWith("step_stones") || "pentos".equals(biomePath) || "tyrosh".equals(biomePath) || "myr".equals(biomePath));
    }

    private static boolean isTurnipBiome(String biomePath) {
        return GotBrewingWorldGeneration.isAllowedCropBiome(biomePath) && (biomePath.startsWith("the_north") || biomePath.startsWith("the_riverlands") || biomePath.startsWith("the_vale") || biomePath.startsWith("the_reach") || biomePath.startsWith("the_stormlands") || biomePath.startsWith("the_crownlands") || biomePath.startsWith("the_westerlands") || biomePath.startsWith("iron_islands"));
    }

    private static boolean isChampignonBiome(String biomePath) {
        return GotBrewingWorldGeneration.isAllowedCropBiome(biomePath) && (biomePath.contains("forest") || biomePath.contains("woods") || biomePath.contains("rainwood") || biomePath.contains("kingswood") || biomePath.contains("marsh") || biomePath.contains("moor"));
    }

    private static boolean isAllowedCropBiome(String biomePath) {
        return !biomePath.startsWith("always_winter") && !"the_gift".equals(biomePath) && !"the_wall".equals(biomePath) && !"sea".equals(biomePath);
    }
}

