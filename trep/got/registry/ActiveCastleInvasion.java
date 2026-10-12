/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1259$class_1260
 *  net.minecraft.class_1259$class_1261
 *  net.minecraft.class_1937
 *  net.minecraft.class_2561
 *  net.minecraft.class_3213
 *  net.minecraft.class_3532
 *  net.minecraft.class_5321
 */
package trep.got.registry;

import java.util.UUID;
import net.minecraft.class_1259;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_3213;
import net.minecraft.class_3532;
import net.minecraft.class_5321;
import trep.got.castle.CastleInvasionMode;
import trep.got.registry.GotCastles;

final class ActiveCastleInvasion {
    private final class_5321<class_1937> dimension;
    private final UUID playerUuid;
    private final String playerName;
    private final String attackerKingdomId;
    private final String attackerHouseId;
    private final String defenderKingdomId;
    private final String castleId;
    private final CastleInvasionMode mode;
    private final long endsAtTick;
    private final long durationTicks;
    private final class_3213 bossBar;
    private boolean awaitingBannerBreak;
    private boolean reinforcementsSpawned;
    private int raidNormalKills;
    private int raidProfessionKills;
    private int raidGuardKills;
    private int raidLordKills;

    ActiveCastleInvasion(class_5321<class_1937> dimension, UUID playerUuid, String playerName, String attackerKingdomId, String attackerHouseId, String defenderKingdomId, String castleId, CastleInvasionMode mode, long endsAtTick, long durationTicks) {
        this.dimension = dimension;
        this.playerUuid = playerUuid;
        this.playerName = playerName == null ? "Unknown Player" : playerName;
        this.attackerKingdomId = attackerKingdomId == null ? "" : attackerKingdomId;
        this.attackerHouseId = attackerHouseId == null ? "" : attackerHouseId;
        this.defenderKingdomId = defenderKingdomId == null ? "" : defenderKingdomId;
        this.castleId = castleId;
        this.mode = mode;
        this.endsAtTick = endsAtTick;
        this.durationTicks = durationTicks;
        this.bossBar = new class_3213((class_2561)class_2561.method_43470((String)""), class_1259.class_1260.field_5784, class_1259.class_1261.field_5795);
    }

    class_5321<class_1937> dimension() {
        return this.dimension;
    }

    UUID playerUuid() {
        return this.playerUuid;
    }

    String playerName() {
        return this.playerName;
    }

    String attackerKingdomId() {
        return this.attackerKingdomId;
    }

    String attackerHouseId() {
        return this.attackerHouseId;
    }

    String defenderKingdomId() {
        return this.defenderKingdomId;
    }

    String castleId() {
        return this.castleId;
    }

    CastleInvasionMode mode() {
        return this.mode;
    }

    long endsAtTick() {
        return this.endsAtTick;
    }

    long halfwayTick() {
        return this.endsAtTick - this.durationTicks / 2L;
    }

    class_3213 bossBar() {
        return this.bossBar;
    }

    boolean awaitingBannerBreak() {
        return this.awaitingBannerBreak;
    }

    void setAwaitingBannerBreak(boolean awaitingBannerBreak) {
        this.awaitingBannerBreak = awaitingBannerBreak;
    }

    boolean reinforcementsSpawned() {
        return this.reinforcementsSpawned;
    }

    void setReinforcementsSpawned(boolean reinforcementsSpawned) {
        this.reinforcementsSpawned = reinforcementsSpawned;
    }

    void addRaidNormalKill() {
        ++this.raidNormalKills;
    }

    void addRaidProfessionKill() {
        ++this.raidProfessionKills;
    }

    void addRaidGuardKill() {
        ++this.raidGuardKills;
    }

    void addRaidLordKill() {
        ++this.raidLordKills;
    }

    int raidNormalKills() {
        return this.raidNormalKills;
    }

    int raidProfessionKills() {
        return this.raidProfessionKills;
    }

    int raidGuardKills() {
        return this.raidGuardKills;
    }

    int raidLordKills() {
        return this.raidLordKills;
    }

    void updateBossBar(long remainingTicks) {
        this.bossBar.method_5413((class_2561)class_2561.method_43470((String)(ActiveCastleInvasion.displayNameFor(this.mode) + " | " + GotCastles.formatRemainingTime(remainingTicks))));
        float percent = this.durationTicks <= 0L ? 0.0f : class_3532.method_15363((float)((float)remainingTicks / (float)this.durationTicks), (float)0.0f, (float)1.0f);
        this.bossBar.method_5408(percent);
    }

    private static String displayNameFor(CastleInvasionMode mode) {
        return switch (mode) {
            default -> throw new MatchException(null, null);
            case CastleInvasionMode.CRUSADE -> "Crusade";
            case CastleInvasionMode.RAID -> "Raid";
            case CastleInvasionMode.CHALLENGE -> "Challenge";
            case CastleInvasionMode.CONQUER -> "Conquer";
        };
    }
}

