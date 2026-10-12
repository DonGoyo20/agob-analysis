/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.relations;

import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;
import trep.got.entity.KingdomNpcRole;
import trep.got.relations.KingdomRelationsService;

public final class PlayerKingdomActionHistoryWorldState
extends class_18 {
    private static final String STATE_ID = "gotmod.player_kingdom_action_history";
    private static final class_18.class_8645<PlayerKingdomActionHistoryWorldState> TYPE = new class_18.class_8645(PlayerKingdomActionHistoryWorldState::new, PlayerKingdomActionHistoryWorldState::fromNbt, class_4284.field_45077);
    private final Map<UUID, PlayerEntry> players = new LinkedHashMap<UUID, PlayerEntry>();

    public static PlayerKingdomActionHistoryWorldState get(class_3218 world) {
        return (PlayerKingdomActionHistoryWorldState)world.method_8503().method_30002().method_17983().method_17924(TYPE, STATE_ID);
    }

    public PlayerEntry getOrCreatePlayer(UUID playerUuid, String playerName) {
        PlayerEntry entry = this.players.computeIfAbsent(playerUuid, uuid -> new PlayerEntry((UUID)uuid, playerName));
        entry.setPlayerName(playerName);
        return entry;
    }

    public Collection<PlayerEntry> players() {
        return this.players.values();
    }

    public void recordRelation(UUID playerUuid, String playerName, String kingdomId, int relation) {
        if (playerUuid == null || kingdomId == null || kingdomId.isBlank()) {
            return;
        }
        this.getOrCreatePlayer(playerUuid, playerName).getOrCreateKingdom(kingdomId).setRelation(relation);
        this.method_80();
    }

    public void recordNpcKill(UUID playerUuid, String playerName, String kingdomId, KingdomNpcRole role, int relation) {
        if (playerUuid == null || kingdomId == null || kingdomId.isBlank() || role == null) {
            return;
        }
        KingdomEntry kingdom = this.getOrCreatePlayer(playerUuid, playerName).getOrCreateKingdom(kingdomId);
        kingdom.setRelation(relation);
        kingdom.addNpcKill(role);
        this.method_80();
    }

    public void recordLockpickingCaught(UUID playerUuid, String playerName, String kingdomId, int relation) {
        if (playerUuid == null || kingdomId == null || kingdomId.isBlank()) {
            return;
        }
        KingdomEntry kingdom = this.getOrCreatePlayer(playerUuid, playerName).getOrCreateKingdom(kingdomId);
        kingdom.setRelation(relation);
        kingdom.addLockpickingCaught();
        this.method_80();
    }

    public void addBlocksTravelled(UUID playerUuid, String playerName, long blocks) {
        if (playerUuid == null || blocks <= 0L) {
            return;
        }
        this.getOrCreatePlayer(playerUuid, playerName).addBlocksTravelled(blocks);
        this.method_80();
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 playerList = new class_2499();
        for (PlayerEntry player : this.players.values()) {
            playerList.add((Object)player.toNbt());
        }
        nbt.method_10566("players", (class_2520)playerList);
        return nbt;
    }

    private static PlayerKingdomActionHistoryWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        PlayerKingdomActionHistoryWorldState state = new PlayerKingdomActionHistoryWorldState();
        class_2499 playerList = nbt.method_10554("players", 10);
        for (int index = 0; index < playerList.size(); ++index) {
            PlayerEntry player = PlayerEntry.fromNbt(playerList.method_10602(index));
            state.players.put(player.playerUuid(), player);
        }
        return state;
    }

    public static final class PlayerEntry {
        private final UUID playerUuid;
        private String playerName;
        private long blocksTravelled;
        private final Map<String, KingdomEntry> kingdoms = new LinkedHashMap<String, KingdomEntry>();

        private PlayerEntry(UUID playerUuid, String playerName) {
            this.playerUuid = playerUuid;
            this.setPlayerName(playerName);
        }

        public UUID playerUuid() {
            return this.playerUuid;
        }

        public String playerName() {
            return this.playerName;
        }

        public long blocksTravelled() {
            return this.blocksTravelled;
        }

        public KingdomEntry getKingdom(String kingdomId) {
            return kingdomId == null ? null : this.kingdoms.get(kingdomId);
        }

        private KingdomEntry getOrCreateKingdom(String kingdomId) {
            return this.kingdoms.computeIfAbsent(kingdomId, KingdomEntry::new);
        }

        private void setPlayerName(String playerName) {
            if (playerName != null && !playerName.isBlank()) {
                this.playerName = playerName;
            } else if (this.playerName == null) {
                this.playerName = "Someone";
            }
        }

        private void addBlocksTravelled(long blocks) {
            this.blocksTravelled = Math.max(0L, this.blocksTravelled + blocks);
        }

        private class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_25927("uuid", this.playerUuid);
            nbt.method_10582("name", this.playerName);
            nbt.method_10544("blocks_travelled", this.blocksTravelled);
            class_2499 kingdomList = new class_2499();
            for (KingdomEntry kingdom : this.kingdoms.values()) {
                kingdomList.add((Object)kingdom.toNbt());
            }
            nbt.method_10566("kingdoms", (class_2520)kingdomList);
            return nbt;
        }

        private static PlayerEntry fromNbt(class_2487 nbt) {
            PlayerEntry entry = new PlayerEntry(nbt.method_25926("uuid"), nbt.method_10558("name"));
            entry.blocksTravelled = Math.max(0L, nbt.method_10537("blocks_travelled"));
            class_2499 kingdomList = nbt.method_10554("kingdoms", 10);
            for (int index = 0; index < kingdomList.size(); ++index) {
                KingdomEntry kingdom = KingdomEntry.fromNbt(kingdomList.method_10602(index));
                if (kingdom.kingdomId().isBlank()) continue;
                entry.kingdoms.put(kingdom.kingdomId(), kingdom);
            }
            return entry;
        }
    }

    public static final class KingdomEntry {
        private final String kingdomId;
        private int relation;
        private int lockpickingCaught;
        private final EnumMap<KingdomNpcRole, Integer> npcKillsByRole = new EnumMap(KingdomNpcRole.class);

        private KingdomEntry(String kingdomId) {
            this.kingdomId = kingdomId == null ? "" : kingdomId;
        }

        public String kingdomId() {
            return this.kingdomId;
        }

        public int relation() {
            return this.relation;
        }

        public int lockpickingCaught() {
            return this.lockpickingCaught;
        }

        public int totalNpcKills() {
            int total = 0;
            for (int count : this.npcKillsByRole.values()) {
                total += count;
            }
            return total;
        }

        public int npcKills(KingdomNpcRole role) {
            return role == null ? 0 : this.npcKillsByRole.getOrDefault((Object)role, 0);
        }

        private void setRelation(int relation) {
            this.relation = KingdomRelationsService.clamp(relation);
        }

        private void addNpcKill(KingdomNpcRole role) {
            this.npcKillsByRole.merge(role, 1, Integer::sum);
        }

        private void addLockpickingCaught() {
            ++this.lockpickingCaught;
        }

        private class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("kingdom_id", this.kingdomId);
            nbt.method_10569("relation", this.relation);
            nbt.method_10569("lockpicking_caught", this.lockpickingCaught);
            class_2487 kills = new class_2487();
            for (Map.Entry<KingdomNpcRole, Integer> entry : this.npcKillsByRole.entrySet()) {
                kills.method_10569(entry.getKey().name(), Math.max(0, entry.getValue()));
            }
            nbt.method_10566("npc_kills", (class_2520)kills);
            return nbt;
        }

        private static KingdomEntry fromNbt(class_2487 nbt) {
            KingdomEntry entry = new KingdomEntry(nbt.method_10558("kingdom_id"));
            entry.setRelation(nbt.method_10550("relation"));
            entry.lockpickingCaught = Math.max(0, nbt.method_10550("lockpicking_caught"));
            class_2487 kills = nbt.method_10562("npc_kills");
            for (String roleId : kills.method_10541()) {
                try {
                    KingdomNpcRole role = KingdomNpcRole.valueOf(roleId);
                    entry.npcKillsByRole.put(role, Math.max(0, kills.method_10550(roleId)));
                }
                catch (IllegalArgumentException illegalArgumentException) {}
            }
            return entry;
        }
    }
}

