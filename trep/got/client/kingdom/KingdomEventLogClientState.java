/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 */
package trep.got.client.kingdom;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import trep.got.network.KingdomEventLogPayload;

@Environment(value=EnvType.CLIENT)
public final class KingdomEventLogClientState {
    private static Map<String, List<KingdomEventLogPayload.LogEntry>> logsByKingdom = Map.of();

    private KingdomEventLogClientState() {
    }

    public static void apply(KingdomEventLogPayload payload, class_310 client) {
        client.execute(() -> {
            LinkedHashMap<String, List<KingdomEventLogPayload.LogEntry>> next = new LinkedHashMap<String, List<KingdomEventLogPayload.LogEntry>>();
            for (KingdomEventLogPayload.KingdomEntry kingdom : payload.kingdoms()) {
                next.put(kingdom.kingdomId(), List.copyOf(kingdom.entries()));
            }
            logsByKingdom = Map.copyOf(next);
        });
    }

    public static List<KingdomEventLogPayload.LogEntry> getLogEntries(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return List.of();
        }
        return logsByKingdom.getOrDefault(kingdomId, List.of());
    }

    public static void clear() {
        logsByKingdom = Map.of();
    }
}

