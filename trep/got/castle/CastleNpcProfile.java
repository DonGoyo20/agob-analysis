/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package trep.got.castle;

import java.util.UUID;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import trep.got.character.NpcAppearanceProfile;
import trep.got.character.NpcAttributeProfile;
import trep.got.entity.KingdomNpcRole;

public record CastleNpcProfile(String id, UUID uuid, String roleId, int seedOffset, String displayName, NpcAttributeProfile attributes, NpcAppearanceProfile appearance, boolean nobleSpouse, String spouseProfileId, class_2338 pointOfInterest, class_2338 preferredSpawnPos, int pointOfInterestRadius) {
    public CastleNpcProfile {
        id = id == null ? "" : id;
        uuid = uuid == null ? UUID.randomUUID() : uuid;
        roleId = roleId == null ? KingdomNpcRole.CIVILIAN.name() : roleId;
        displayName = displayName == null ? "" : displayName;
        attributes = attributes == null ? NpcAttributeProfile.EMPTY : attributes;
        appearance = appearance == null ? NpcAppearanceProfile.EMPTY : appearance;
        spouseProfileId = spouseProfileId == null ? "" : spouseProfileId;
        pointOfInterest = pointOfInterest == null ? null : pointOfInterest.method_10062();
        preferredSpawnPos = preferredSpawnPos == null ? null : preferredSpawnPos.method_10062();
        pointOfInterestRadius = Math.max(0, pointOfInterestRadius);
    }

    public CastleNpcProfile(String id, UUID uuid, String roleId, int seedOffset, String displayName, NpcAttributeProfile attributes) {
        this(id, uuid, roleId, seedOffset, displayName, attributes, NpcAppearanceProfile.EMPTY, false, "", null, null, 0);
    }

    public CastleNpcProfile(String id, UUID uuid, String roleId, int seedOffset, String displayName) {
        this(id, uuid, roleId, seedOffset, displayName, NpcAttributeProfile.EMPTY, NpcAppearanceProfile.EMPTY, false, "", null, null, 0);
    }

    public CastleNpcProfile(String id, UUID uuid, String roleId, int seedOffset, String displayName, NpcAttributeProfile attributes, NpcAppearanceProfile appearance, boolean nobleSpouse, String spouseProfileId) {
        this(id, uuid, roleId, seedOffset, displayName, attributes, appearance, nobleSpouse, spouseProfileId, null, null, 0);
    }

    public KingdomNpcRole role() {
        try {
            return KingdomNpcRole.valueOf(this.roleId);
        }
        catch (IllegalArgumentException ignored) {
            return KingdomNpcRole.CIVILIAN;
        }
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("id", this.id);
        nbt.method_25927("uuid", this.uuid);
        nbt.method_10582("role_id", this.roleId);
        nbt.method_10569("seed_offset", this.seedOffset);
        nbt.method_10582("display_name", this.displayName);
        nbt.method_10566("attributes", (class_2520)this.attributes.toNbt());
        nbt.method_10566("appearance", (class_2520)this.appearance.toNbt());
        nbt.method_10556("noble_spouse", this.nobleSpouse);
        nbt.method_10582("spouse_profile_id", this.spouseProfileId);
        if (this.pointOfInterest != null) {
            nbt.method_10544("point_of_interest", this.pointOfInterest.method_10063());
        }
        if (this.preferredSpawnPos != null) {
            nbt.method_10544("preferred_spawn_pos", this.preferredSpawnPos.method_10063());
        }
        nbt.method_10569("point_of_interest_radius", this.pointOfInterestRadius);
        return nbt;
    }

    public static CastleNpcProfile fromNbt(class_2487 nbt) {
        NpcAttributeProfile attributes = nbt.method_10573("attributes", 10) ? NpcAttributeProfile.fromNbt(nbt.method_10562("attributes")) : NpcAttributeProfile.EMPTY;
        NpcAppearanceProfile appearance = nbt.method_10573("appearance", 10) ? NpcAppearanceProfile.fromNbt(nbt.method_10562("appearance")) : NpcAppearanceProfile.EMPTY;
        return new CastleNpcProfile(nbt.method_10558("id"), nbt.method_25928("uuid") ? nbt.method_25926("uuid") : UUID.randomUUID(), nbt.method_10558("role_id"), nbt.method_10550("seed_offset"), nbt.method_10558("display_name"), attributes, appearance, nbt.method_10577("noble_spouse"), nbt.method_10558("spouse_profile_id"), nbt.method_10545("point_of_interest") ? class_2338.method_10092((long)nbt.method_10537("point_of_interest")) : null, nbt.method_10545("preferred_spawn_pos") ? class_2338.method_10092((long)nbt.method_10537("preferred_spawn_pos")) : null, nbt.method_10550("point_of_interest_radius"));
    }
}

