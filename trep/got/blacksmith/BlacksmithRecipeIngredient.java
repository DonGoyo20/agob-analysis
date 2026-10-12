/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.class_1799
 *  net.minecraft.class_1856
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.blacksmith;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record BlacksmithRecipeIngredient(class_1856 ingredient, int count) {
    public static final Codec<BlacksmithRecipeIngredient> CODEC = RecordCodecBuilder.create(instance -> instance.group((App)class_1856.field_46096.fieldOf("ingredient").forGetter(BlacksmithRecipeIngredient::ingredient), (App)Codec.INT.optionalFieldOf("count", (Object)1).forGetter(BlacksmithRecipeIngredient::count)).apply((Applicative)instance, BlacksmithRecipeIngredient::new));
    public static final class_9139<class_9129, BlacksmithRecipeIngredient> PACKET_CODEC = class_9139.method_56438((value, buf) -> {
        class_1856.field_48355.encode(buf, (Object)value.ingredient);
        buf.method_10804(value.count);
    }, buf -> new BlacksmithRecipeIngredient((class_1856)class_1856.field_48355.decode(buf), buf.method_10816()));

    public BlacksmithRecipeIngredient {
        if (count < 1) {
            throw new IllegalArgumentException("Blacksmith ingredient count must be at least 1");
        }
    }

    public class_1799 createPreviewStack() {
        class_1799[] stacks = this.ingredient.method_8105();
        if (stacks.length == 0) {
            return class_1799.field_8037;
        }
        return stacks[0].method_46651(this.count);
    }
}

