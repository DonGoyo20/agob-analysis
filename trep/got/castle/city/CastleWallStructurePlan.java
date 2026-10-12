/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle.city;

public record CastleWallStructurePlan(String wallPath, String cornerPath, String gatePath) {
    public static CastleWallStructurePlan stone() {
        return CastleWallStructurePlan.variant("stone", "stone");
    }

    public static CastleWallStructurePlan wood() {
        return CastleWallStructurePlan.variant("wood", "wood");
    }

    public static CastleWallStructurePlan sandstone() {
        return CastleWallStructurePlan.variant("sandstone", "sandstone");
    }

    public static CastleWallStructurePlan brick() {
        return CastleWallStructurePlan.variant("brick", "brick");
    }

    public static CastleWallStructurePlan mossy() {
        return CastleWallStructurePlan.variant("mossy", "mossy");
    }

    public static CastleWallStructurePlan deepslate() {
        return new CastleWallStructurePlan("castle/deepslate/deepslate_wall_wall", "castle/deepslate/deepslate_wall_corner", "castle/deepslate/deepslate_wall_gate_");
    }

    private static CastleWallStructurePlan variant(String folder, String prefix) {
        return new CastleWallStructurePlan("castle/" + folder + "/" + prefix + "_wall_wall", "castle/" + folder + "/" + prefix + "_wall_corner", "castle/" + folder + "/" + prefix + "_wall_gate");
    }
}

