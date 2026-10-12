/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.screen;

import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record DragonStatsScreenOpeningData(int entityId) {
    public static final class_9139<class_9129, DragonStatsScreenOpeningData> PACKET_CODEC = class_9139.method_56438((value, buf) -> buf.method_10804(value.entityId()), buf -> new DragonStatsScreenOpeningData(buf.method_10816()));
}

