/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle;

import java.util.Set;

public interface PlayerCastleTravelHolder {
    public Set<String> got$getUnlockedCastleIds();

    public boolean got$unlockCastle(String var1);

    public void got$clearUnlockedCastles();

    public void got$copyUnlockedCastlesFrom(PlayerCastleTravelHolder var1);

    default public boolean got$hasUnlockedCastle(String castleId) {
        return castleId != null && !castleId.isBlank() && this.got$getUnlockedCastleIds().contains(castleId);
    }
}

