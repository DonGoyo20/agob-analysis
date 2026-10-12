/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry.castle.overlay;

import java.util.Set;
import trep.got.castle.CastleSubtype;

public record CastlePresetMetadata(String castleId, String name, String lore, String kingdomId, String houseId, String houseArmorId, String religionId, String tier, Set<CastleSubtype> subtypes, boolean generateVillage) {
}

