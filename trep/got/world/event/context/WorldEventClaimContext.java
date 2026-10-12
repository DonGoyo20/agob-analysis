/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package trep.got.world.event.context;

import net.minecraft.class_2487;
import trep.got.world.event.context.WorldEventClaimType;

public record WorldEventClaimContext(boolean inClaim, String castleId, String castleName, WorldEventClaimType claimType, boolean harbourCastle) {
    public static final WorldEventClaimContext NONE = new WorldEventClaimContext(false, "", "", WorldEventClaimType.NONE, false);

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10556("in_claim", this.inClaim);
        nbt.method_10582("castle_id", this.castleId);
        nbt.method_10582("castle_name", this.castleName);
        nbt.method_10582("claim_type", this.claimType.id());
        nbt.method_10556("harbour_castle", this.harbourCastle);
        return nbt;
    }

    public static WorldEventClaimContext fromNbt(class_2487 nbt) {
        return new WorldEventClaimContext(nbt.method_10577("in_claim"), nbt.method_10558("castle_id"), nbt.method_10558("castle_name"), WorldEventClaimType.byId(nbt.method_10558("claim_type")), nbt.method_10577("harbour_castle"));
    }
}

