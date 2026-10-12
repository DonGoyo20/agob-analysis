/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_2960
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.blacksmith;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record BlacksmithRecipeDisplay(class_2960 recipeId, class_2960 kingdomId, String groupId, int order, int requiredSmithingLevel, float speed, class_1799 result, List<class_1799> ingredientPreviews) {
    public static final class_9139<class_9129, BlacksmithRecipeDisplay> PACKET_CODEC = class_9139.method_56438((value, buf) -> {
        class_2960.field_48267.encode(buf, (Object)value.recipeId);
        class_2960.field_48267.encode(buf, (Object)value.kingdomId);
        buf.method_10814(value.groupId);
        buf.method_10804(value.order);
        buf.method_10804(value.requiredSmithingLevel);
        buf.method_52941(value.speed);
        class_1799.field_48349.encode(buf, (Object)value.result);
        buf.method_10804(value.ingredientPreviews.size());
        for (class_1799 stack : value.ingredientPreviews) {
            class_1799.field_48349.encode(buf, (Object)stack);
        }
    }, buf -> {
        class_2960 recipeId = (class_2960)class_2960.field_48267.decode(buf);
        class_2960 kingdomId = (class_2960)class_2960.field_48267.decode(buf);
        String groupId = buf.method_19772();
        int order = buf.method_10816();
        int requiredSmithingLevel = buf.method_10816();
        float speed = buf.readFloat();
        class_1799 result = (class_1799)class_1799.field_48349.decode(buf);
        int ingredientCount = buf.method_10816();
        ArrayList<class_1799> ingredientPreviews = new ArrayList<class_1799>(ingredientCount);
        for (int index = 0; index < ingredientCount; ++index) {
            ingredientPreviews.add((class_1799)class_1799.field_48349.decode(buf));
        }
        return new BlacksmithRecipeDisplay(recipeId, kingdomId, groupId, order, requiredSmithingLevel, speed, result, List.copyOf(ingredientPreviews));
    });

    public BlacksmithRecipeDisplay {
        result = result.method_7972();
        ingredientPreviews = ingredientPreviews.stream().map(class_1799::method_7972).toList();
    }
}

