/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package trep.got.kingdom;

import net.minecraft.class_2487;

public record PlayerKingdomDefinition(String ownerUuid, String kingdomId, String displayName, String parentKingdomId, String castleId, String houseId) {
    public PlayerKingdomDefinition {
        ownerUuid = ownerUuid == null ? "" : ownerUuid;
        kingdomId = kingdomId == null ? "" : kingdomId;
        displayName = displayName == null ? "" : displayName;
        parentKingdomId = parentKingdomId == null ? "" : parentKingdomId;
        castleId = castleId == null ? "" : castleId;
        houseId = houseId == null ? "" : houseId;
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("owner_uuid", this.ownerUuid);
        nbt.method_10582("kingdom_id", this.kingdomId);
        nbt.method_10582("display_name", this.displayName);
        nbt.method_10582("parent_kingdom_id", this.parentKingdomId);
        nbt.method_10582("castle_id", this.castleId);
        nbt.method_10582("house_id", this.houseId);
        return nbt;
    }

    public static PlayerKingdomDefinition fromNbt(class_2487 nbt) {
        return new PlayerKingdomDefinition(nbt.method_10558("owner_uuid"), nbt.method_10558("kingdom_id"), nbt.method_10558("display_name"), nbt.method_10558("parent_kingdom_id"), nbt.method_10558("castle_id"), nbt.method_10558("house_id"));
    }
}

