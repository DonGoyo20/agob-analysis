/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 */
package trep.got.castle;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import trep.got.castle.CastleRecaptureStage;

public final class CastleRecaptureState {
    private final String castleId;
    private UUID playerUuid;
    private CastleRecaptureStage stage = CastleRecaptureStage.DORMANT;
    private long nextCheckTick;
    private long incomingEndsAtTick;
    private boolean playerEnteredZone;
    private int attackerCount;
    private String attackingKingdomId = "";
    private String attackingLordName = "";
    private final Set<UUID> attackerUuids = new LinkedHashSet<UUID>();

    public CastleRecaptureState(String castleId) {
        this.castleId = castleId == null ? "" : castleId;
    }

    public String getCastleId() {
        return this.castleId;
    }

    public UUID getPlayerUuid() {
        return this.playerUuid;
    }

    public void setPlayerUuid(UUID playerUuid) {
        this.playerUuid = playerUuid;
    }

    public CastleRecaptureStage getStage() {
        return this.stage;
    }

    public void setStage(CastleRecaptureStage stage) {
        this.stage = stage == null ? CastleRecaptureStage.DORMANT : stage;
    }

    public long getNextCheckTick() {
        return this.nextCheckTick;
    }

    public void setNextCheckTick(long nextCheckTick) {
        this.nextCheckTick = nextCheckTick;
    }

    public long getIncomingEndsAtTick() {
        return this.incomingEndsAtTick;
    }

    public void setIncomingEndsAtTick(long incomingEndsAtTick) {
        this.incomingEndsAtTick = incomingEndsAtTick;
    }

    public boolean hasPlayerEnteredZone() {
        return this.playerEnteredZone;
    }

    public void setPlayerEnteredZone(boolean playerEnteredZone) {
        this.playerEnteredZone = playerEnteredZone;
    }

    public int getAttackerCount() {
        return this.attackerCount;
    }

    public void setAttackerCount(int attackerCount) {
        this.attackerCount = Math.max(0, attackerCount);
    }

    public String getAttackingKingdomId() {
        return this.attackingKingdomId;
    }

    public void setAttackingKingdomId(String attackingKingdomId) {
        this.attackingKingdomId = attackingKingdomId == null ? "" : attackingKingdomId;
    }

    public String getAttackingLordName() {
        return this.attackingLordName;
    }

    public void setAttackingLordName(String attackingLordName) {
        this.attackingLordName = attackingLordName == null ? "" : attackingLordName;
    }

    public Set<UUID> getAttackerUuids() {
        return Set.copyOf(this.attackerUuids);
    }

    public void clearAttackers() {
        this.attackerUuids.clear();
        this.attackerCount = 0;
    }

    public void addAttackerUuid(UUID attackerUuid) {
        if (attackerUuid != null) {
            this.attackerUuids.add(attackerUuid);
        }
    }

    public void removeAttackerUuid(UUID attackerUuid) {
        this.attackerUuids.remove(attackerUuid);
    }

    public boolean isAttacker(UUID entityUuid) {
        return entityUuid != null && this.attackerUuids.contains(entityUuid);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("castle_id", this.castleId);
        if (this.playerUuid != null) {
            nbt.method_25927("player_uuid", this.playerUuid);
        }
        nbt.method_10582("stage", this.stage.getId());
        nbt.method_10544("next_check_tick", this.nextCheckTick);
        nbt.method_10544("incoming_ends_at_tick", this.incomingEndsAtTick);
        nbt.method_10556("player_entered_zone", this.playerEnteredZone);
        nbt.method_10569("attacker_count", this.attackerCount);
        nbt.method_10582("attacking_kingdom_id", this.attackingKingdomId);
        nbt.method_10582("attacking_lord_name", this.attackingLordName);
        class_2499 attackers = new class_2499();
        for (UUID attackerUuid : this.attackerUuids) {
            attackers.add((Object)class_2519.method_23256((String)attackerUuid.toString()));
        }
        nbt.method_10566("attacker_uuids", (class_2520)attackers);
        return nbt;
    }

    public static CastleRecaptureState fromNbt(class_2487 nbt) {
        CastleRecaptureState state = new CastleRecaptureState(nbt.method_10558("castle_id"));
        if (nbt.method_25928("player_uuid")) {
            state.setPlayerUuid(nbt.method_25926("player_uuid"));
        }
        state.setStage(CastleRecaptureStage.byId(nbt.method_10558("stage")));
        state.setNextCheckTick(nbt.method_10537("next_check_tick"));
        state.setIncomingEndsAtTick(nbt.method_10537("incoming_ends_at_tick"));
        state.setPlayerEnteredZone(nbt.method_10577("player_entered_zone"));
        state.setAttackerCount(nbt.method_10550("attacker_count"));
        state.setAttackingKingdomId(nbt.method_10558("attacking_kingdom_id"));
        state.setAttackingLordName(nbt.method_10558("attacking_lord_name"));
        class_2499 attackers = nbt.method_10554("attacker_uuids", 8);
        for (int index = 0; index < attackers.size(); ++index) {
            try {
                state.addAttackerUuid(UUID.fromString(attackers.method_10608(index)));
                continue;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
        }
        return state;
    }
}

