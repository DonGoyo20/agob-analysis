/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.castle;

import net.minecraft.class_2960;

public enum CastleTier {
    SETTLEMENT("settlement", "Settlement"),
    LORDSHIP("lordship", "Lordship"),
    CAPITAL("capital", "Capital");

    private final String id;
    private final String displayName;

    private CastleTier(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public int getStorageRows() {
        return switch (this.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> 1;
            case 1 -> 2;
            case 2 -> 3;
        };
    }

    public class_2960 defaultLootTableId() {
        String lootTier = this == CAPITAL ? CastleTier.LORDSHIP.id : this.id;
        return class_2960.method_60655((String)"agob", (String)("castle/" + lootTier));
    }

    public static CastleTier byId(String id) {
        for (CastleTier tier : CastleTier.values()) {
            if (!tier.id.equals(id)) continue;
            return tier;
        }
        return SETTLEMENT;
    }
}

