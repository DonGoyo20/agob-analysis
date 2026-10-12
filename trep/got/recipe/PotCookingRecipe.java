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
 *  net.minecraft.class_1856
 *  net.minecraft.class_1860
 *  net.minecraft.class_1865
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 *  net.minecraft.class_3956
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_7923
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.recipe;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1860;
import net.minecraft.class_1865;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_3956;
import net.minecraft.class_7225;
import net.minecraft.class_7923;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import trep.got.recipe.PotCookingRecipeInput;
import trep.got.registry.GotRecipes;

public record PotCookingRecipe(List<class_1856> ingredients, class_1856 container, class_1799 result, int cookingTime) implements class_1860<PotCookingRecipeInput>
{
    private static final Codec<class_1799> RESULT_CODEC = RecordCodecBuilder.create(instance -> instance.group((App)class_2960.field_25139.fieldOf("item").forGetter(stack -> class_7923.field_41178.method_10221((Object)stack.method_7909())), (App)Codec.INT.optionalFieldOf("count", (Object)1).forGetter(class_1799::method_7947)).apply((Applicative)instance, (itemId, count) -> new class_1799((class_1935)class_7923.field_41178.method_10223(itemId), count.intValue())));
    public static final MapCodec<PotCookingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group((App)class_1856.field_46096.listOf().fieldOf("ingredients").forGetter(PotCookingRecipe::ingredients), (App)class_1856.field_46096.fieldOf("container").forGetter(PotCookingRecipe::container), (App)RESULT_CODEC.fieldOf("result").forGetter(PotCookingRecipe::result), (App)Codec.INT.optionalFieldOf("cooking_time", (Object)200).forGetter(PotCookingRecipe::cookingTime)).apply((Applicative)instance, PotCookingRecipe::new));
    public static final class_9139<class_9129, PotCookingRecipe> PACKET_CODEC = class_9139.method_56438((value, buf) -> {
        buf.method_10804(value.ingredients.size());
        for (class_1856 ingredient : value.ingredients) {
            class_1856.field_48355.encode(buf, (Object)ingredient);
        }
        class_1856.field_48355.encode(buf, (Object)value.container);
        class_1799.field_48349.encode(buf, (Object)value.result);
        buf.method_10804(value.cookingTime);
    }, buf -> {
        int count = buf.method_10816();
        ArrayList<class_1856> ingredients = new ArrayList<class_1856>(count);
        for (int index = 0; index < count; ++index) {
            ingredients.add((class_1856)class_1856.field_48355.decode(buf));
        }
        class_1856 container = (class_1856)class_1856.field_48355.decode(buf);
        class_1799 result = (class_1799)class_1799.field_48349.decode(buf);
        int cookingTime = buf.method_10816();
        return new PotCookingRecipe(List.copyOf(ingredients), container, result, cookingTime);
    });

    public PotCookingRecipe {
        if (ingredients.size() != 4) {
            throw new IllegalArgumentException("Pot cooking recipes require exactly 4 ingredients");
        }
        ingredients = List.copyOf(ingredients);
        result = result.method_7972();
        cookingTime = Math.max(1, cookingTime);
    }

    public boolean matches(PotCookingRecipeInput input, class_1937 world) {
        for (int slot = 0; slot < this.ingredients.size(); ++slot) {
            if (this.ingredients.get(slot).method_8093(input.method_59984(slot))) continue;
            return false;
        }
        return this.container.method_8093(input.method_59984(4));
    }

    public class_1799 craft(PotCookingRecipeInput input, class_7225.class_7874 registries) {
        return this.result.method_7972();
    }

    public boolean method_8113(int width, int height) {
        return true;
    }

    public class_1799 method_8110(class_7225.class_7874 registries) {
        return this.result.method_7972();
    }

    public class_1865<?> method_8119() {
        return GotRecipes.POT_COOKING_SERIALIZER;
    }

    public class_3956<?> method_17716() {
        return GotRecipes.POT_COOKING_TYPE;
    }
}

