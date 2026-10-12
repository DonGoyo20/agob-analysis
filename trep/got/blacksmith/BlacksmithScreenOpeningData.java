/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2960
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.blacksmith;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.class_2338;
import net.minecraft.class_2960;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import trep.got.blacksmith.BlacksmithGroupDefinition;
import trep.got.blacksmith.BlacksmithRecipeDisplay;

public record BlacksmithScreenOpeningData(class_2338 furnaceOrigin, class_2338 tableOrigin, List<BlacksmithGroupDefinition> groups, List<BlacksmithRecipeDisplay> recipes, String ownKingdomId, Set<String> unlockedKingdomIds, Set<String> talkedBlacksmithKingdomIds, int nextUnlockIntelligenceRequirement) {
    public static final class_9139<class_9129, BlacksmithScreenOpeningData> PACKET_CODEC = class_9139.method_56438((value, buf) -> {
        class_2338.field_48404.encode(buf, (Object)value.furnaceOrigin);
        class_2338.field_48404.encode(buf, (Object)value.tableOrigin);
        buf.method_10804(value.groups.size());
        for (BlacksmithGroupDefinition group : value.groups) {
            BlacksmithGroupDefinition.PACKET_CODEC.encode(buf, (Object)group);
        }
        buf.method_10804(value.recipes.size());
        for (BlacksmithRecipeDisplay recipe : value.recipes) {
            BlacksmithRecipeDisplay.PACKET_CODEC.encode(buf, (Object)recipe);
        }
        buf.method_10814(value.ownKingdomId);
        BlacksmithScreenOpeningData.writeStringSet(buf, value.unlockedKingdomIds);
        BlacksmithScreenOpeningData.writeStringSet(buf, value.talkedBlacksmithKingdomIds);
        buf.method_10804(value.nextUnlockIntelligenceRequirement);
    }, buf -> {
        class_2338 furnaceOrigin = (class_2338)class_2338.field_48404.decode(buf);
        class_2338 tableOrigin = (class_2338)class_2338.field_48404.decode(buf);
        int groupCount = buf.method_10816();
        ArrayList<BlacksmithGroupDefinition> groups = new ArrayList<BlacksmithGroupDefinition>(groupCount);
        for (int index = 0; index < groupCount; ++index) {
            groups.add((BlacksmithGroupDefinition)BlacksmithGroupDefinition.PACKET_CODEC.decode(buf));
        }
        int recipeCount = buf.method_10816();
        ArrayList<BlacksmithRecipeDisplay> recipes = new ArrayList<BlacksmithRecipeDisplay>(recipeCount);
        for (int index = 0; index < recipeCount; ++index) {
            recipes.add((BlacksmithRecipeDisplay)BlacksmithRecipeDisplay.PACKET_CODEC.decode(buf));
        }
        String ownKingdomId = buf.method_19772();
        Set<String> unlockedKingdomIds = BlacksmithScreenOpeningData.readStringSet(buf);
        Set<String> talkedBlacksmithKingdomIds = BlacksmithScreenOpeningData.readStringSet(buf);
        int nextUnlockIntelligenceRequirement = buf.method_10816();
        return new BlacksmithScreenOpeningData(furnaceOrigin, tableOrigin, List.copyOf(groups), List.copyOf(recipes), ownKingdomId, unlockedKingdomIds, talkedBlacksmithKingdomIds, nextUnlockIntelligenceRequirement);
    });

    public boolean isOwnKingdom(class_2960 kingdomId) {
        return kingdomId != null && kingdomId.toString().equals(this.ownKingdomId);
    }

    public boolean isKingdomUnlocked(class_2960 kingdomId) {
        return kingdomId != null && this.unlockedKingdomIds.contains(kingdomId.toString());
    }

    public boolean hasTalkedToBlacksmith(class_2960 kingdomId) {
        return kingdomId != null && this.talkedBlacksmithKingdomIds.contains(kingdomId.toString());
    }

    private static void writeStringSet(class_9129 buf, Set<String> values) {
        buf.method_10804(values.size());
        for (String value : values) {
            buf.method_10814(value);
        }
    }

    private static Set<String> readStringSet(class_9129 buf) {
        int count = buf.method_10816();
        LinkedHashSet<String> values = new LinkedHashSet<String>(count);
        for (int index = 0; index < count; ++index) {
            values.add(buf.method_19772());
        }
        return Set.copyOf(values);
    }
}

