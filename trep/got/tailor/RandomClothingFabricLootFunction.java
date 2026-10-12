/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.Products$P1
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  com.mojang.serialization.codecs.RecordCodecBuilder$Instance
 *  net.minecraft.class_120
 *  net.minecraft.class_1799
 *  net.minecraft.class_47
 *  net.minecraft.class_5339
 *  net.minecraft.class_5341
 */
package trep.got.tailor;

import com.mojang.datafixers.Products;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.class_120;
import net.minecraft.class_1799;
import net.minecraft.class_47;
import net.minecraft.class_5339;
import net.minecraft.class_5341;
import trep.got.item.ClothingFabricItem;
import trep.got.registry.GotItems;
import trep.got.registry.GotLootFunctions;
import trep.got.tailor.ClothingPatternDefinition;
import trep.got.tailor.ClothingPatternRegistry;

public final class RandomClothingFabricLootFunction
extends class_120 {
    public static final MapCodec<RandomClothingFabricLootFunction> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        Products.P1 conditions = RandomClothingFabricLootFunction.method_53344((RecordCodecBuilder.Instance)instance);
        return conditions.apply((Applicative)instance, RandomClothingFabricLootFunction::new);
    });

    public RandomClothingFabricLootFunction(List<class_5341> conditions) {
        super(conditions);
    }

    public class_5339<RandomClothingFabricLootFunction> method_29321() {
        return GotLootFunctions.RANDOM_CLOTHING_FABRIC;
    }

    protected class_1799 method_522(class_1799 stack, class_47 context) {
        if (!stack.method_31574(GotItems.FABRIC)) {
            return stack;
        }
        ClothingPatternDefinition pattern = ClothingPatternRegistry.random(context.method_294());
        if (pattern == null || pattern.clothingId().isBlank()) {
            return stack;
        }
        ClothingFabricItem.applyClothingData(stack, pattern.slot(), pattern.clothingId());
        return stack;
    }
}

