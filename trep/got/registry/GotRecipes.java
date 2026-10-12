/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1865
 *  net.minecraft.class_1866
 *  net.minecraft.class_2378
 *  net.minecraft.class_2960
 *  net.minecraft.class_3956
 *  net.minecraft.class_7923
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1865;
import net.minecraft.class_1866;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3956;
import net.minecraft.class_7923;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import trep.got.blacksmith.BlacksmithRecipe;
import trep.got.recipe.BeerBrewingRecipe;
import trep.got.recipe.GalleyCraftingRecipe;
import trep.got.recipe.GateMechanismRepairingRecipe;
import trep.got.recipe.LongshipCraftingRecipe;
import trep.got.recipe.PotCookingRecipe;
import trep.got.recipe.RuneCraftingRecipe;
import trep.got.recipe.ShipCraftingRecipe;
import trep.got.tailor.TailorRecipe;

public final class GotRecipes {
    public static final class_3956<BlacksmithRecipe> BLACKSMITH_TYPE = class_3956.method_17726((String)"blacksmith");
    public static final class_3956<BeerBrewingRecipe> BEER_BREWING_TYPE = class_3956.method_17726((String)"beer_brewing");
    public static final class_3956<PotCookingRecipe> POT_COOKING_TYPE = class_3956.method_17726((String)"pot_cooking");
    public static final class_3956<TailorRecipe> TAILOR_TYPE = class_3956.method_17726((String)"tailor");
    public static final class_1865<BlacksmithRecipe> BLACKSMITH_SERIALIZER = (class_1865)class_2378.method_10230((class_2378)class_7923.field_41189, (class_2960)class_2960.method_60655((String)"agob", (String)"blacksmith"), (Object)new class_1865<BlacksmithRecipe>(){

        public MapCodec<BlacksmithRecipe> method_53736() {
            return BlacksmithRecipe.CODEC;
        }

        public class_9139<class_9129, BlacksmithRecipe> method_56104() {
            return BlacksmithRecipe.PACKET_CODEC;
        }
    });
    public static final class_1865<BeerBrewingRecipe> BEER_BREWING_SERIALIZER = (class_1865)class_2378.method_10230((class_2378)class_7923.field_41189, (class_2960)class_2960.method_60655((String)"agob", (String)"beer_brewing"), (Object)new class_1865<BeerBrewingRecipe>(){

        public MapCodec<BeerBrewingRecipe> method_53736() {
            return BeerBrewingRecipe.CODEC;
        }

        public class_9139<class_9129, BeerBrewingRecipe> method_56104() {
            return BeerBrewingRecipe.PACKET_CODEC;
        }
    });
    public static final class_1865<PotCookingRecipe> POT_COOKING_SERIALIZER = (class_1865)class_2378.method_10230((class_2378)class_7923.field_41189, (class_2960)class_2960.method_60655((String)"agob", (String)"pot_cooking"), (Object)new class_1865<PotCookingRecipe>(){

        public MapCodec<PotCookingRecipe> method_53736() {
            return PotCookingRecipe.CODEC;
        }

        public class_9139<class_9129, PotCookingRecipe> method_56104() {
            return PotCookingRecipe.PACKET_CODEC;
        }
    });
    public static final class_1865<TailorRecipe> TAILOR_SERIALIZER = (class_1865)class_2378.method_10230((class_2378)class_7923.field_41189, (class_2960)class_2960.method_60655((String)"agob", (String)"tailor"), (Object)new class_1865<TailorRecipe>(){

        public MapCodec<TailorRecipe> method_53736() {
            return TailorRecipe.CODEC;
        }

        public class_9139<class_9129, TailorRecipe> method_56104() {
            return TailorRecipe.PACKET_CODEC;
        }
    });
    public static final class_1865<ShipCraftingRecipe> SHIP_CRAFTING_SERIALIZER = (class_1865)class_2378.method_10230((class_2378)class_7923.field_41189, (class_2960)class_2960.method_60655((String)"agob", (String)"ship_crafting"), (Object)new class_1866(ShipCraftingRecipe::new));
    public static final class_1865<GalleyCraftingRecipe> GALLEY_CRAFTING_SERIALIZER = (class_1865)class_2378.method_10230((class_2378)class_7923.field_41189, (class_2960)class_2960.method_60655((String)"agob", (String)"galley_crafting"), (Object)new class_1866(GalleyCraftingRecipe::new));
    public static final class_1865<LongshipCraftingRecipe> LONGSHIP_CRAFTING_SERIALIZER = (class_1865)class_2378.method_10230((class_2378)class_7923.field_41189, (class_2960)class_2960.method_60655((String)"agob", (String)"longship_crafting"), (Object)new class_1866(LongshipCraftingRecipe::new));
    public static final class_1865<GateMechanismRepairingRecipe> GATE_MECHANISM_REPAIRING_SERIALIZER = (class_1865)class_2378.method_10230((class_2378)class_7923.field_41189, (class_2960)class_2960.method_60655((String)"agob", (String)"gate_mechanism_repairing"), (Object)new class_1866(GateMechanismRepairingRecipe::new));
    public static final class_1865<RuneCraftingRecipe> RUNE_CRAFTING_SERIALIZER = (class_1865)class_2378.method_10230((class_2378)class_7923.field_41189, (class_2960)class_2960.method_60655((String)"agob", (String)"rune_crafting"), (Object)new class_1866(RuneCraftingRecipe::new));

    private GotRecipes() {
    }

    public static void initialize() {
    }
}

