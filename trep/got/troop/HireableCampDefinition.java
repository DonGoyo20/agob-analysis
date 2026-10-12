/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.troop;

import java.util.List;
import net.minecraft.class_2960;

public record HireableCampDefinition(String id, String structurePath, String claimName, String tierName, List<String> spawnKingdomIds, boolean allowOtherKingdomSpawns, int otherKingdomSpawnPercent, int claimRadiusChunks, int minSpawnCount, int maxSpawnCount, List<class_2960> troopIds) {
    public HireableCampDefinition {
        spawnKingdomIds = List.copyOf(spawnKingdomIds);
        troopIds = List.copyOf(troopIds);
    }

    public boolean spawnsInKingdom(class_2960 kingdomId) {
        if (kingdomId == null) {
            return false;
        }
        String normalized = kingdomId.toString();
        return this.spawnKingdomIds.stream().anyMatch(normalized::equals);
    }

    public boolean canSpawnInKingdom(class_2960 kingdomId, long locationSeed) {
        if (this.spawnsInKingdom(kingdomId)) {
            return true;
        }
        if (!this.allowOtherKingdomSpawns) {
            return false;
        }
        int chancePercent = Math.max(0, Math.min(100, this.otherKingdomSpawnPercent));
        if (chancePercent <= 0) {
            return false;
        }
        return Math.floorMod(locationSeed, 100L) < (long)chancePercent;
    }

    public int desiredSpawnCount(String siteId, long currentDay) {
        int min = Math.max(0, this.minSpawnCount);
        int max = Math.max(min, this.maxSpawnCount);
        if (max <= min) {
            return min;
        }
        return min + Math.floorMod(siteId.hashCode() * 31 + (int)currentDay, max - min + 1);
    }
}

