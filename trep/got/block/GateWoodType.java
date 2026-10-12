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

public enum GateWoodType implements class_3542
{
    OAK("oak", "Oak", "oak_trapdoor", "stripped_oak_log"),
    SPRUCE("spruce", "Spruce", "spruce_trapdoor", "stripped_spruce_log"),
    BIRCH("birch", "Birch", "birch_trapdoor", "stripped_birch_log"),
    JUNGLE("jungle", "Jungle", "jungle_trapdoor", "stripped_jungle_log"),
    ACACIA("acacia", "Acacia", "acacia_trapdoor", "stripped_acacia_log"),
    DARK_OAK("dark_oak", "Dark Oak", "dark_oak_trapdoor", "stripped_dark_oak_log"),
    MANGROVE("mangrove", "Mangrove", "mangrove_trapdoor", "stripped_mangrove_log"),
    CHERRY("cherry", "Cherry", "cherry_trapdoor", "stripped_cherry_log"),
    BAMBOO("bamboo", "Bamboo", "bamboo_trapdoor", "stripped_bamboo_block"),
    CRIMSON("crimson", "Crimson", "crimson_trapdoor", "stripped_crimson_stem"),
    WARPED("warped", "Warped", "warped_trapdoor", "stripped_warped_stem");

    private final String id;
    private final String displayName;
    private final class_2960 trapdoorId;
    private final class_2960 strippedLogId;

    private GateWoodType(String id, String displayName, String trapdoorName, String strippedLogName) {
        this.id = id;
        this.displayName = displayName;
        this.trapdoorId = class_2960.method_60656((String)trapdoorName);
        this.strippedLogId = class_2960.method_60656((String)strippedLogName);
    }

    public String displayName() {
        return this.displayName;
    }

    public String translationSuffix() {
        return this.id;
    }

    public class_2960 trapdoorTextureId() {
        return class_2960.method_60655((String)this.trapdoorId.method_12836(), (String)this.trapdoorId.method_12832());
    }

    public class_1792 strippedLogItem() {
        return (class_1792)class_7923.field_41178.method_10223(this.strippedLogId);
    }

    public static GateWoodType fromItem(class_1792 item) {
        for (GateWoodType type : GateWoodType.values()) {
            if (type.strippedLogItem() != item) continue;
            return type;
        }
        return null;
    }

    public static GateWoodType byId(String id) {
        if (id == null || id.isBlank()) {
            return SPRUCE;
        }
        for (GateWoodType type : GateWoodType.values()) {
            if (!type.id.equals(id)) continue;
            return type;
        }
        return SPRUCE;
    }

    public static GateWoodType fromItemOrDefault(class_1792 item) {
        GateWoodType type = GateWoodType.fromItem(item);
        return type == null ? SPRUCE : type;
    }

    public String method_15434() {
        return this.id;
    }
}

