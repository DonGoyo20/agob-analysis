/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record VillageMapPayload(List<Entry> villages) implements class_8710
{
    public static final class_8710.class_9154<VillageMapPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"village_map"));
    public static final class_9139<class_9129, VillageMapPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.villages.size());
        for (Entry entry : value.villages) {
            buf.method_10814(entry.id());
            buf.method_10814(entry.name());
            buf.method_10814(entry.boundCastleId());
            buf.method_10814(entry.boundCastleName());
            buf.method_10814(entry.kingdomId());
            buf.method_10804(entry.worldX());
            buf.method_10804(entry.worldZ());
            buf.method_52964(entry.unlocked());
            buf.method_52964(entry.resourceId() != null);
            if (entry.resourceId() == null) continue;
            buf.method_10812(entry.resourceId());
        }
    }, buf -> {
        int size = buf.method_10816();
        ArrayList<Entry> villages = new ArrayList<Entry>(size);
        for (int index = 0; index < size; ++index) {
            String id = buf.method_19772();
            String name = buf.method_19772();
            String boundCastleId = buf.method_19772();
            String boundCastleName = buf.method_19772();
            String kingdomId = buf.method_19772();
            int worldX = buf.method_10816();
            int worldZ = buf.method_10816();
            boolean unlocked = buf.readBoolean();
            class_2960 resourceId = buf.readBoolean() ? buf.method_10810() : null;
            villages.add(new Entry(id, name, boundCastleId, boundCastleName, kingdomId, worldX, worldZ, unlocked, resourceId));
        }
        return new VillageMapPayload(List.copyOf(villages));
    });

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    public record Entry(String id, String name, String boundCastleId, String boundCastleName, String kingdomId, int worldX, int worldZ, boolean unlocked, class_2960 resourceId) {
    }
}

