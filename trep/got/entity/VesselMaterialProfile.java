/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_2248
 *  net.minecraft.class_2487
 *  net.minecraft.class_2960
 *  net.minecraft.class_7923
 *  net.minecraft.class_9279
 *  net.minecraft.class_9331
 *  net.minecraft.class_9334
 */
package trep.got.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2248;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.minecraft.class_9279;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import trep.got.entity.ShipEntity;

public record VesselMaterialProfile(WoodFamily plankWoodFamily, WoodFamily logWoodFamily, WoolFamily woolFamily) {
    private static final String PLANK_WOOD_KEY = "VesselWood";
    private static final String LOG_WOOD_KEY = "VesselLogWood";
    private static final String WOOL_KEY = "VesselWool";
    public static final VesselMaterialProfile DEFAULT = new VesselMaterialProfile(WoodFamily.OAK, WoodFamily.OAK, WoolFamily.WHITE);

    public static VesselMaterialProfile fromStack(class_1799 stack) {
        class_9279 component = (class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302);
        if (component.method_57458()) {
            return DEFAULT;
        }
        class_2487 nbt = component.method_57461();
        return VesselMaterialProfile.fromIds(nbt.method_10558(PLANK_WOOD_KEY), nbt.method_10558(LOG_WOOD_KEY), nbt.method_10558(WOOL_KEY));
    }

    public static VesselMaterialProfile fromEntity(ShipEntity ship) {
        return VesselMaterialProfile.fromIds(ship.getPlankWoodMaterialId(), ship.getLogWoodMaterialId(), ship.getWoolMaterialId());
    }

    public static VesselMaterialProfile fromIds(String plankWoodId, String logWoodId, String woolId) {
        WoodFamily plankWoodFamily = WoodFamily.byPlankId(plankWoodId);
        WoodFamily logWoodFamily = WoodFamily.byPlankId(logWoodId);
        WoolFamily woolFamily = WoolFamily.byBlockId(woolId);
        WoodFamily resolvedPlank = plankWoodFamily == null ? WoodFamily.OAK : plankWoodFamily;
        WoodFamily resolvedLog = logWoodFamily == null ? resolvedPlank : logWoodFamily;
        return new VesselMaterialProfile(resolvedPlank, resolvedLog, woolFamily == null ? WoolFamily.WHITE : woolFamily);
    }

    public void apply(class_1799 stack) {
        class_2487 nbt = new class_2487();
        nbt.method_10582(PLANK_WOOD_KEY, this.plankWoodFamily.plankId().toString());
        nbt.method_10582(LOG_WOOD_KEY, this.logWoodFamily.plankId().toString());
        nbt.method_10582(WOOL_KEY, this.woolFamily.blockId().toString());
        class_9279.method_57453((class_9331)class_9334.field_49628, (class_1799)stack, (class_2487)nbt);
    }

    public String plankWoodLabel() {
        return this.plankWoodFamily.displayName() + " Planks";
    }

    public String logWoodLabel() {
        return this.logWoodFamily.displayName() + " Log";
    }

    public String woolLabel() {
        return this.woolFamily.displayName();
    }

    public class_2960 plankTexture() {
        return this.plankWoodFamily.plankTexture();
    }

    public class_2960 strippedSideTexture() {
        return this.logWoodFamily.strippedSideTexture();
    }

    public class_2960 woolTexture() {
        return this.woolFamily.texture();
    }

    private static class_2960 textureForBlockId(class_2960 blockId) {
        return class_2960.method_60655((String)blockId.method_12836(), (String)("textures/block/" + blockId.method_12832() + ".png"));
    }

    private static class_2960 parseId(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        try {
            return class_2960.method_60654((String)id);
        }
        catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    public record WoodFamily(String displayName, class_2960 plankId, class_2960 fenceId, class_2960 logId, class_2960 strippedLogId) {
        private static final Map<class_2960, WoodFamily> BY_PLANK_ID = new LinkedHashMap<class_2960, WoodFamily>();
        public static final WoodFamily OAK = WoodFamily.register("Oak", "oak");
        public static final WoodFamily SPRUCE = WoodFamily.register("Spruce", "spruce");
        public static final WoodFamily BIRCH = WoodFamily.register("Birch", "birch");
        public static final WoodFamily JUNGLE = WoodFamily.register("Jungle", "jungle");
        public static final WoodFamily ACACIA = WoodFamily.register("Acacia", "acacia");
        public static final WoodFamily DARK_OAK = WoodFamily.register("Dark Oak", "dark_oak");
        public static final WoodFamily MANGROVE = WoodFamily.register("Mangrove", "mangrove");
        public static final WoodFamily CHERRY = WoodFamily.register("Cherry", "cherry");
        public static final WoodFamily BAMBOO = WoodFamily.register("Bamboo", "bamboo", "bamboo_fence", "bamboo_block", "stripped_bamboo_block");
        public static final WoodFamily CRIMSON = WoodFamily.register("Crimson", "crimson", "crimson_fence", "crimson_stem", "stripped_crimson_stem");
        public static final WoodFamily WARPED = WoodFamily.register("Warped", "warped", "warped_fence", "warped_stem", "stripped_warped_stem");

        private static WoodFamily register(String displayName, String baseName) {
            return WoodFamily.register(displayName, baseName + "_planks", baseName + "_fence", baseName + "_log", "stripped_" + baseName + "_log");
        }

        private static WoodFamily register(String displayName, String plankName, String fenceName, String logName, String strippedLogName) {
            WoodFamily family = new WoodFamily(displayName, class_2960.method_60656((String)plankName), class_2960.method_60656((String)fenceName), class_2960.method_60656((String)logName), class_2960.method_60656((String)strippedLogName));
            BY_PLANK_ID.put(family.plankId, family);
            return family;
        }

        public static WoodFamily byPlankId(String id) {
            class_2960 identifier = VesselMaterialProfile.parseId(id);
            if (identifier == null) {
                return OAK;
            }
            return BY_PLANK_ID.get(identifier);
        }

        public static Collection<WoodFamily> values() {
            return BY_PLANK_ID.values();
        }

        public class_2960 plankTexture() {
            return VesselMaterialProfile.textureForBlockId(this.plankId);
        }

        public class_2960 strippedSideTexture() {
            return VesselMaterialProfile.textureForBlockId(this.strippedLogId);
        }

        public class_1792 plankItem() {
            return (class_1792)class_7923.field_41178.method_10223(this.plankId);
        }

        public class_1792 fenceItem() {
            return (class_1792)class_7923.field_41178.method_10223(this.fenceId);
        }

        public class_1792 logItem() {
            return (class_1792)class_7923.field_41178.method_10223(this.logId);
        }

        public class_1792 strippedLogItem() {
            return (class_1792)class_7923.field_41178.method_10223(this.strippedLogId);
        }
    }

    public record WoolFamily(String displayName, class_2960 blockId) {
        private static final Map<class_2960, WoolFamily> BY_BLOCK_ID = new LinkedHashMap<class_2960, WoolFamily>();
        public static final WoolFamily WHITE = WoolFamily.register("White Wool", "white_wool");
        public static final WoolFamily ORANGE = WoolFamily.register("Orange Wool", "orange_wool");
        public static final WoolFamily MAGENTA = WoolFamily.register("Magenta Wool", "magenta_wool");
        public static final WoolFamily LIGHT_BLUE = WoolFamily.register("Light Blue Wool", "light_blue_wool");
        public static final WoolFamily YELLOW = WoolFamily.register("Yellow Wool", "yellow_wool");
        public static final WoolFamily LIME = WoolFamily.register("Lime Wool", "lime_wool");
        public static final WoolFamily PINK = WoolFamily.register("Pink Wool", "pink_wool");
        public static final WoolFamily GRAY = WoolFamily.register("Gray Wool", "gray_wool");
        public static final WoolFamily LIGHT_GRAY = WoolFamily.register("Light Gray Wool", "light_gray_wool");
        public static final WoolFamily CYAN = WoolFamily.register("Cyan Wool", "cyan_wool");
        public static final WoolFamily PURPLE = WoolFamily.register("Purple Wool", "purple_wool");
        public static final WoolFamily BLUE = WoolFamily.register("Blue Wool", "blue_wool");
        public static final WoolFamily BROWN = WoolFamily.register("Brown Wool", "brown_wool");
        public static final WoolFamily GREEN = WoolFamily.register("Green Wool", "green_wool");
        public static final WoolFamily RED = WoolFamily.register("Red Wool", "red_wool");
        public static final WoolFamily BLACK = WoolFamily.register("Black Wool", "black_wool");

        private static WoolFamily register(String displayName, String blockName) {
            WoolFamily family = new WoolFamily(displayName, class_2960.method_60656((String)blockName));
            BY_BLOCK_ID.put(family.blockId, family);
            return family;
        }

        public static WoolFamily byBlockId(String id) {
            class_2960 identifier = VesselMaterialProfile.parseId(id);
            if (identifier == null) {
                return WHITE;
            }
            return BY_BLOCK_ID.get(identifier);
        }

        public static Collection<WoolFamily> values() {
            return BY_BLOCK_ID.values();
        }

        public class_2960 texture() {
            return VesselMaterialProfile.textureForBlockId(this.blockId);
        }

        public class_2248 block() {
            return (class_2248)class_7923.field_41175.method_10223(this.blockId);
        }
    }
}

