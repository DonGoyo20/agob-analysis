/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package trep.got.castle;

import net.minecraft.class_2487;
import net.minecraft.class_2520;
import trep.got.castle.SettlerProfession;
import trep.got.castle.SettlerSkillTier;
import trep.got.character.NpcAttributeProfile;

public record SettlerProfile(String id, String displayName, String optionId, String professionId, String skillTierId, boolean accepted, long offeredDay, NpcAttributeProfile attributes) {
    public SettlerProfile {
        id = id == null ? "" : id;
        displayName = displayName == null ? "" : displayName;
        optionId = optionId == null ? "" : optionId;
        professionId = professionId == null ? SettlerProfession.FARMER.getId() : professionId;
        skillTierId = skillTierId == null ? SettlerSkillTier.WOOD.getId() : skillTierId;
        attributes = attributes == null ? NpcAttributeProfile.EMPTY : attributes;
    }

    public SettlerProfile(String id, String displayName, String optionId, String professionId, String skillTierId, boolean accepted, long offeredDay) {
        this(id, displayName, optionId, professionId, skillTierId, accepted, offeredDay, NpcAttributeProfile.EMPTY);
    }

    public SettlerProfession profession() {
        return SettlerProfession.byId(this.professionId);
    }

    public SettlerSkillTier skillTier() {
        return SettlerSkillTier.byId(this.skillTierId);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("id", this.id);
        nbt.method_10582("display_name", this.displayName);
        nbt.method_10582("option_id", this.optionId);
        nbt.method_10582("profession_id", this.professionId);
        nbt.method_10582("skill_tier_id", this.skillTierId);
        nbt.method_10556("accepted", this.accepted);
        nbt.method_10544("offered_day", this.offeredDay);
        nbt.method_10566("attributes", (class_2520)this.attributes.toNbt());
        return nbt;
    }

    public static SettlerProfile fromNbt(class_2487 nbt) {
        NpcAttributeProfile attributes = nbt.method_10573("attributes", 10) ? NpcAttributeProfile.fromNbt(nbt.method_10562("attributes")) : NpcAttributeProfile.EMPTY;
        return new SettlerProfile(nbt.method_10558("id"), nbt.method_10558("display_name"), nbt.method_10558("option_id"), nbt.method_10558("profession_id"), nbt.method_10558("skill_tier_id"), nbt.method_10577("accepted"), nbt.method_10537("offered_day"), attributes);
    }
}

