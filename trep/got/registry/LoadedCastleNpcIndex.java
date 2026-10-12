/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import trep.got.entity.AbstractKingdomNpcEntity;

record LoadedCastleNpcIndex(Map<UUID, AbstractKingdomNpcEntity> byUuid, Map<String, AbstractKingdomNpcEntity> byProfileId) {
    static LoadedCastleNpcIndex from(List<AbstractKingdomNpcEntity> npcs) {
        if (npcs == null || npcs.isEmpty()) {
            return new LoadedCastleNpcIndex(Map.of(), Map.of());
        }
        HashMap<UUID, AbstractKingdomNpcEntity> byUuid = new HashMap<UUID, AbstractKingdomNpcEntity>();
        HashMap<String, AbstractKingdomNpcEntity> byProfileId = new HashMap<String, AbstractKingdomNpcEntity>();
        for (AbstractKingdomNpcEntity npc : npcs) {
            if (npc == null || !npc.method_5805()) continue;
            byUuid.putIfAbsent(npc.method_5667(), npc);
            if (npc.getCastleNpcId().isBlank()) continue;
            byProfileId.putIfAbsent(npc.getCastleNpcId(), npc);
        }
        return new LoadedCastleNpcIndex(byUuid, byProfileId);
    }
}

