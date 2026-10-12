/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_2960
 *  net.minecraft.class_3542
 *  net.minecraft.class_7923
 */
package trep.got.block;

import net.minecraft.class_1792;
import net.minecraft.class_2960;
import net.minecraft.class_3542;
import net.minecraft.class_7923;

public enum ChairWoodType implements class_3542
{
    OAK("oak", "Oak", class_2960.method_60656((String)"oak_planks"), class_2960.method_60656((String)"oak_log"), "minecraft:block/oak_planks", "agob:block/base/oak_base"),
    SPRUCE("spruce", "Spruce", class_2960.method_60656((String)"spruce_planks"), class_2960.method_60656((String)"spruce_log"), "minecraft:block/spruce_planks", "agob:block/base/spruce_base"),
    BIRCH("birch", "Birch", class_2960.method_60656((String)"birch_planks"), class_2960.method_60656((String)"birch_log"), "minecraft:block/birch_planks", "agob:block/base/birch_base"),
    JUNGLE("jungle", "Jungle", class_2960.method_60656((String)"jungle_planks"), class_2960.method_60656((String)"jungle_log"), "minecraft:block/jungle_planks", "agob:block/base/jungle_base"),
    ACACIA("acacia", "Acacia", class_2960.method_60656((String)"acacia_planks"), class_2960.method_60656((String)"acacia_log"), "minecraft:block/acacia_planks", "agob:block/base/acacia_base"),
    DARK_OAK("dark_oak", "Dark Oak", class_2960.method_60656((String)"dark_oak_planks"), class_2960.method_60656((String)"dark_oak_log"), "minecraft:block/dark_oak_planks", "agob:block/base/dark_oak_base"),
    MANGROVE("mangrove", "Mangrove", class_2960.method_60656((String)"mangrove_planks"), class_2960.method_60656((String)"mangrove_log"), "minecraft:block/mangrove_planks", "agob:block/base/mangrove_base"),
    CHERRY("cherry", "Cherry", class_2960.method_60656((String)"cherry_planks"), class_2960.method_60656((String)"cherry_log"), "minecraft:block/cherry_planks", "agob:block/base/cherry_base"),
    BAMBOO("bamboo", "Bamboo", class_2960.method_60656((String)"bamboo_planks"), class_2960.method_60656((String)"bamboo_block"), "minecraft:block/bamboo_planks", "agob:block/base/bamboo_base"),
    CRIMSON("crimson", "Crimson", class_2960.method_60656((String)"crimson_planks"), class_2960.method_60656((String)"crimson_stem"), "minecraft:block/crimson_planks", "agob:block/base/crimson_base"),
    WARPED("warped", "Warped", class_2960.method_60656((String)"warped_planks"), class_2960.method_60656((String)"warped_stem"), "minecraft:block/warped_planks", "agob:block/base/warped_base"),
    WEIRWOOD("weirwood", "Weirwood", class_2960.method_60655((String)"agob", (String)"weirwood_planks"), class_2960.method_60655((String)"agob", (String)"weirwood_log"), "agob:block/weirwood_planks", "agob:block/base/weirwood_base");

    private final String id;
    private final String displayName;
    private final class_2960 planksId;
    private final class_2960 logId;
    private final String itemTexture;
    private final String furnitureTexture;

    private ChairWoodType(String id, String displayName, class_2960 planksId, class_2960 logId, String itemTexture, String furnitureTexture) {
        this.id = id;
        this.displayName = displayName;
        this.planksId = planksId;
        this.logId = logId;
        this.itemTexture = itemTexture;
        this.furnitureTexture = furnitureTexture;
    }

    public String displayName() {
        return this.displayName;
    }

    public class_1792 planksItem() {
        return (class_1792)class_7923.field_41178.method_10223(this.planksId);
    }

    public class_1792 logItem() {
        return (class_1792)class_7923.field_41178.method_10223(this.logId);
    }

    public String itemTexture() {
        return this.itemTexture;
    }

    public String furnitureTexture() {
        return this.furnitureTexture;
    }

    public static ChairWoodType byId(String id) {
        if (id == null || id.isBlank()) {
            return DARK_OAK;
        }
        for (ChairWoodType type : ChairWoodType.values()) {
            if (!type.id.equals(id)) continue;
            return type;
        }
        return DARK_OAK;
    }

    public String method_15434() {
        return this.id;
    }
}

