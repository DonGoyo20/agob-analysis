/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.class_1799
 *  net.minecraft.class_1860
 *  net.minecraft.class_1865
 *  net.minecraft.class_1937
 *  net.minecraft.class_3956
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 *  net.minecraft.class_9695
 */
package trep.got.tailor;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_1799;
import net.minecraft.class_1860;
import net.minecraft.class_1865;
import net.minecraft.class_1937;
import net.minecraft.class_3956;
import net.minecraft.class_7225;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_9695;
import trep.got.blacksmith.BlacksmithRecipeIngredient;
import trep.got.registry.GotRecipes;

public record TailorRecipe(String slot, String clothingId, List<BlacksmithRecipeIngredient> ingredients) implements class_1860<class_9695>
{
    public static final MapCodec<TailorRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group((App)Codec.STRING.fieldOf("slot").forGetter(TailorRecipe::slot), (App)Codec.STRING.fieldOf("clothing").forGetter(TailorRecipe::clothingId), (App)BlacksmithRecipeIngredient.CODEC.listOf().optionalFieldOf("ingredients", List.of()).forGetter(TailorRecipe::ingredients)).apply((Applicative)instance, TailorRecipe::new));
    public static final class_9139<class_9129, TailorRecipe> PACKET_CODEC = class_9139.method_56438((value, buf) -> {
        buf.method_10814(value.slot);
        buf.method_10814(value.clothingId);
        buf.method_10804(value.ingredients.size());
        for (BlacksmithRecipeIngredient ingredient : value.ingredients) {
            BlacksmithRecipeIngredient.PACKET_CODEC.encode(buf, (Object)ingredient);
        }
    }, buf -> {
        String slot = buf.method_19772();
        String clothingId = buf.method_19772();
        int ingredientCount = buf.method_10816();
        ArrayList<BlacksmithRecipeIngredient> ingredients = new ArrayList<BlacksmithRecipeIngredient>(ingredientCount);
        for (int index = 0; index < ingredientCount; ++index) {
            ingredients.add((BlacksmithRecipeIngredient)BlacksmithRecipeIngredient.PACKET_CODEC.decode(buf));
        }
        return new TailorRecipe(slot, clothingId, ingredients);
    });

    public TailorRecipe {
        slot = TailorRecipe.normalize(slot);
        clothingId = TailorRecipe.normalize(clothingId);
        ingredients = List.copyOf(ingredients);
    }

    public boolean matches(String slot, String clothingId) {
        return this.slot.equals(TailorRecipe.normalize(slot)) && this.clothingId.equals(TailorRecipe.normalize(clothingId));
    }

    public boolean method_8115(class_9695 input, class_1937 world) {
        return false;
    }

    public class_1799 method_8116(class_9695 input, class_7225.class_7874 registries) {
        return class_1799.field_8037;
    }

    public boolean method_8113(int width, int height) {
        return true;
    }

    public class_1799 method_8110(class_7225.class_7874 registries) {
        return class_1799.field_8037;
    }

    public class_1865<?> method_8119() {
        return GotRecipes.TAILOR_SERIALIZER;
    }

    public class_3956<?> method_17716() {
        return GotRecipes.TAILOR_TYPE;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}

