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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record KingdomRelationsPayload(List<KingdomEntry> kingdomRelations, List<PlayerEntry> playerRelations, List<WarEntry> warEntries, String playerKingdomId, boolean playerKing, Map<String, Long> diplomacyCooldownEndsAtTickByKingdom) implements class_8710
{
    public static final class_8710.class_9154<KingdomRelationsPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"kingdom_relations"));
    public static final class_9139<class_9129, KingdomRelationsPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.kingdomRelations.size());
        for (KingdomEntry kingdomEntry : value.kingdomRelations) {
            buf.method_10814(kingdomEntry.fromKingdomId());
            buf.method_10814(kingdomEntry.toKingdomId());
            buf.method_10804(kingdomEntry.relation() + 100);
        }
        buf.method_10804(value.playerRelations.size());
        for (PlayerEntry playerEntry : value.playerRelations) {
            buf.method_10814(playerEntry.kingdomId());
            buf.method_10804(playerEntry.relation() + 100);
        }
        buf.method_10804(value.warEntries.size());
        for (WarEntry warEntry : value.warEntries) {
            buf.method_10814(warEntry.attackerKingdomId());
            buf.method_10814(warEntry.defenderKingdomId());
            buf.method_10804(warEntry.startedDay());
            buf.method_10804(warEntry.endsAtDay());
        }
        buf.method_10814(value.playerKingdomId());
        buf.method_52964(value.playerKing());
        buf.method_10804(value.diplomacyCooldownEndsAtTickByKingdom.size());
        for (Map.Entry entry : value.diplomacyCooldownEndsAtTickByKingdom.entrySet()) {
            buf.method_10814((String)entry.getKey());
            buf.method_52974(((Long)entry.getValue()).longValue());
        }
    }, buf -> {
        int kingdomSize = buf.method_10816();
        ArrayList<KingdomEntry> kingdomEntries = new ArrayList<KingdomEntry>(kingdomSize);
        for (int index = 0; index < kingdomSize; ++index) {
            kingdomEntries.add(new KingdomEntry(buf.method_19772(), buf.method_19772(), buf.method_10816() - 100));
        }
        int playerSize = buf.method_10816();
        ArrayList<PlayerEntry> playerEntries = new ArrayList<PlayerEntry>(playerSize);
        for (int index = 0; index < playerSize; ++index) {
            playerEntries.add(new PlayerEntry(buf.method_19772(), buf.method_10816() - 100));
        }
        int warSize = buf.method_10816();
        ArrayList<WarEntry> warEntries = new ArrayList<WarEntry>(warSize);
        for (int index = 0; index < warSize; ++index) {
            warEntries.add(new WarEntry(buf.method_19772(), buf.method_19772(), buf.method_10816(), buf.method_10816()));
        }
        String playerKingdomId = buf.method_19772();
        boolean playerKing = buf.readBoolean();
        int cooldownSize = buf.method_10816();
        LinkedHashMap<String, Long> cooldowns = new LinkedHashMap<String, Long>();
        for (int index = 0; index < cooldownSize; ++index) {
            cooldowns.put(buf.method_19772(), buf.readLong());
        }
        return new KingdomRelationsPayload(List.copyOf(kingdomEntries), List.copyOf(playerEntries), List.copyOf(warEntries), playerKingdomId, playerKing, Map.copyOf(cooldowns));
    });

    public KingdomRelationsPayload {
        kingdomRelations = List.copyOf(kingdomRelations == null ? List.of() : kingdomRelations);
        playerRelations = List.copyOf(playerRelations == null ? List.of() : playerRelations);
        warEntries = List.copyOf(warEntries == null ? List.of() : warEntries);
        playerKingdomId = playerKingdomId == null ? "" : playerKingdomId;
        diplomacyCooldownEndsAtTickByKingdom = Map.copyOf(diplomacyCooldownEndsAtTickByKingdom == null ? Map.of() : diplomacyCooldownEndsAtTickByKingdom);
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    public record KingdomEntry(String fromKingdomId, String toKingdomId, int relation) {
    }

    public record PlayerEntry(String kingdomId, int relation) {
    }

    public record WarEntry(String attackerKingdomId, String defenderKingdomId, int startedDay, int endsAtDay) {
    }
}

