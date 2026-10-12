/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package trep.got.world.event.context;

import net.minecraft.class_2487;

public record WorldEventRouteContext(boolean onRoad, int roadDistanceBlocks, boolean nearHarbour, String nearestHarbourId, int harbourDistanceBlocks, boolean seaRouteNode) {
    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10556("on_road", this.onRoad);
        nbt.method_10569("road_distance_blocks", this.roadDistanceBlocks);
        nbt.method_10556("near_harbour", this.nearHarbour);
        nbt.method_10582("nearest_harbour_id", this.nearestHarbourId);
        nbt.method_10569("harbour_distance_blocks", this.harbourDistanceBlocks);
        nbt.method_10556("sea_route_node", this.seaRouteNode);
        return nbt;
    }

    public static WorldEventRouteContext fromNbt(class_2487 nbt) {
        return new WorldEventRouteContext(nbt.method_10577("on_road"), nbt.method_10550("road_distance_blocks"), nbt.method_10577("near_harbour"), nbt.method_10558("nearest_harbour_id"), nbt.method_10550("harbour_distance_blocks"), nbt.method_10577("sea_route_node"));
    }
}

