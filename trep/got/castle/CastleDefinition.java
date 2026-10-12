/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.castle;

import java.util.List;
import java.util.Set;
import net.minecraft.class_2960;
import trep.got.castle.CastlePlacementRules;
import trep.got.castle.CastleTier;
import trep.got.castle.CastleWoodType;

public record CastleDefinition(String id, String displayName, CastleTier tier, class_2960 kingdomId, List<String> allowedBiomes, Set<String> allowedReligionIds, Set<CastleWoodType> preferredWoodTypes, CastlePlacementRules placementRules) {
    public boolean allowsBiome(String biomePath) {
        return this.allowedBiomes.isEmpty() || this.allowedBiomes.contains(biomePath);
    }
}

