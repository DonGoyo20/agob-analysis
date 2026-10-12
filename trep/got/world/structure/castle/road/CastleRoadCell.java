/*
 * Decompiled with CFR 0.152.
 */
package trep.got.world.structure.castle.road;

public record CastleRoadCell(int x, int y, int z, int distanceFromCenter, Kind kind) {
    public CastleRoadCell {
        distanceFromCenter = Math.max(0, distanceFromCenter);
        kind = kind == null ? Kind.ROUTE : kind;
    }

    public static enum Kind {
        ROUTE,
        GATE_APRON,
        BUILDING_APRON;

    }
}

