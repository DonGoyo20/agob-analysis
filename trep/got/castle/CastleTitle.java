/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package trep.got.castle;

import net.minecraft.class_2487;
import trep.got.castle.CastleTier;

public record CastleTitle(String id, String castleId, String displayName, CastleTier tier) {
    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("id", this.id);
        nbt.method_10582("castle_id", this.castleId);
        nbt.method_10582("display_name", this.displayName);
        nbt.method_10582("tier", this.tier.getId());
        return nbt;
    }

    public static CastleTitle fromNbt(class_2487 nbt) {
        return new CastleTitle(nbt.method_10558("id"), nbt.method_10558("castle_id"), nbt.method_10558("display_name"), CastleTier.byId(nbt.method_10558("tier")));
    }
}

