/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 */
package trep.got.client.map;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import trep.got.network.VillageMapPayload;

@Environment(value=EnvType.CLIENT)
public final class VillageMapClientState {
    private static List<VillageMapPayload.Entry> villages = List.of();

    private VillageMapClientState() {
    }

    public static void apply(VillageMapPayload payload, class_310 client) {
        client.execute(() -> {
            villages = payload.villages();
        });
    }

    public static List<VillageMapPayload.Entry> getVillages() {
        return villages;
    }

    public static void clear() {
        villages = List.of();
    }
}

