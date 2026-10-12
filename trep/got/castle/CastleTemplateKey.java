/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.castle;

import net.minecraft.class_2960;
import trep.got.castle.CastleTier;
import trep.got.castle.CastleWoodType;

public record CastleTemplateKey(CastleTier tier, CastleWoodType woodType) {
    public class_2960 structureId() {
        if (this.tier == CastleTier.LORDSHIP || this.tier == CastleTier.CAPITAL) {
            return class_2960.method_60655((String)"agob", (String)"castle_stone");
        }
        return class_2960.method_60655((String)"agob", (String)("castle_wood_" + this.woodType.getId()));
    }
}

