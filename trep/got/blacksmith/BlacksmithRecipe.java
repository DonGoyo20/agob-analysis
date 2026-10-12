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
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 *  net.minecraft.class_3956
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_7923
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 *  net.minecraft.class_9695
 */
package trep.got.blacksmith;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1799;
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
import net.minecraft.class_9695;
import trep.got.blacksmith.BlacksmithRecipeIngredient;
import trep.got.registry.GotRecipes;

public record BlacksmithRecipe(class_2960 kingdomId, String groupId, int order, int requiredSmithingLevel, float speed, List<BlacksmithRecipeIngredient> ingredients, class_1799 result) implements class_1860<class_9695>
{
    private static final Codec<class_1799> RESULT_CODEC = RecordCodecBuilder.create(instance -> instance.group((App)class_2960.field_25139.fieldOf("item").forGetter(stack -> class_7923.field_41178.method_10221((Object)stack.method_7909())), (App)Codec.INT.optionalFieldOf("count", (Object)1).forGetter(class_1799::method_7947)).apply((Applicative)instance, (itemId, count) -> new class_1799((class_1935)class_7923.field_41178.method_10223(itemId), count.intValue())));
    public static final MapCodec<BlacksmithRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group((App)class_2960.field_25139.fieldOf("kingdom").forGetter(BlacksmithRecipe::kingdomId), (App)Codec.STRING.fieldOf("group").forGetter(BlacksmithRecipe::groupId), (App)Codec.INT.optionalFieldOf("order", (Object)0).forGetter(BlacksmithRecipe::order), (App)Codec.INT.optionalFieldOf("required_smithing_level", (Object)0).forGetter(BlacksmithRecipe::requiredSmithingLevel), (App)Codec.floatRange((float)0.1f, (float)1.0f).optionalFieldOf("speed", (Object)Float.valueOf(0.6f)).forGetter(BlacksmithRecipe::speed), (App)BlacksmithRecipeIngredient.CODEC.listOf().fieldOf("ingredients").forGetter(BlacksmithRecipe::ingredients), (App)RESULT_CODEC.fieldOf("result").forGetter(BlacksmithRecipe::result)).apply((Applicative)instance, BlacksmithRecipe::new));
    public static final class_9139<class_9129, BlacksmithRecipe> PACKET_CODEC = class_9139.method_56438((value, buf) -> {
        class_2960.field_48267.encode(buf, (Object)value.kingdomId);
        buf.method_10814(value.groupId);
        buf.method_10804(value.order);
        buf.method_10804(value.requiredSmithingLevel);
        buf.method_52941(value.speed);
        buf.method_10804(value.ingredients.size());
        for (BlacksmithRecipeIngredient ingredient : value.ingredients) {
            BlacksmithRecipeIngredient.PACKET_CODEC.encode(buf, (Object)ingredient);
        }
        class_1799.field_48349.encode(buf, (Object)value.result);
    }, buf -> {
        class_2960 kingdomId = (class_2960)class_2960.field_48267.decode(buf);
        String groupId = buf.method_19772();
        int order = buf.method_10816();
        int requiredSmithingLevel = buf.method_10816();
        float speed = buf.readFloat();
        int ingredientCount = buf.method_10816();
        ArrayList<BlacksmithRecipeIngredient> ingredients = new ArrayList<BlacksmithRecipeIngredient>(ingredientCount);
        for (int index = 0; index < ingredientCount; ++index) {
            ingredients.add((BlacksmithRecipeIngredient)BlacksmithRecipeIngredient.PACKET_CODEC.decode(buf));
        }
        return new BlacksmithRecipe(kingdomId, groupId, order, requiredSmithingLevel, speed, List.copyOf(ingredients), (class_1799)class_1799.field_48349.decode(buf));
    });

    public BlacksmithRecipe {
        if (ingredients.size() > 4) {
            throw new IllegalArgumentException("Blacksmith recipes support at most 4 ingredients");
        }
        if (requiredSmithingLevel < 0) {
            throw new IllegalArgumentException("Blacksmith recipe required smithing level cannot be negative");
        }
        if (speed < 0.1f || speed > 1.0f) {
            throw new IllegalArgumentException("Blacksmith recipe speed must be within 0.1 and 1.0");
        }
        ingredients = List.copyOf(ingredients);
        result = result.method_7972();
    }

    public boolean method_8115(class_9695 input, class_1937 world) {
        return false;
    }

    public class_1799 method_8116(class_9695 input, class_7225.class_7874 registries) {
        return this.result.method_7972();
    }

    public boolean method_8113(int width, int height) {
        return true;
    }

    public class_1799 method_8110(class_7225.class_7874 registries) {
        return this.result.method_7972();
    }

    public class_1865<?> method_8119() {
        return GotRecipes.BLACKSMITH_SERIALIZER;
    }

    public class_3956<?> method_17716() {
        return GotRecipes.BLACKSMITH_TYPE;
    }
}

