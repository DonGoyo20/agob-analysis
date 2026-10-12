/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 */
package trep.got.relations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import trep.got.character.NpcAppearanceProfile;
import trep.got.character.NpcAttributeProfile;

public final class PlayerMarriageProfile {
    private String spouseNpcId = "";
    private UUID spouseNpcUuid;
    private String spouseName = "";
    private String spouseKingdomId = "";
    private String spouseHouseId = "";
    private String sourceCastleId = "";
    private boolean sourceVillage;
    private boolean sourceSettler;
    private boolean lordMarriage;
    private String spouseFamilyStageId = "";
    private NpcAttributeProfile spouseAttributes = NpcAttributeProfile.EMPTY;
    private NpcAppearanceProfile spouseAppearance = NpcAppearanceProfile.EMPTY;
    private long summonCooldownEndsAtMs;
    private long summonedUntilDay = -1L;
    private long babyCooldownUnlockDay;
    private boolean spouseInParty;
    private final List<String> transferredCastleIds = new ArrayList<String>();

    public boolean isMarried() {
        return !this.spouseNpcId.isBlank();
    }

    public String getSpouseNpcId() {
        return this.spouseNpcId;
    }

    public void setSpouseNpcId(String spouseNpcId) {
        this.spouseNpcId = spouseNpcId == null ? "" : spouseNpcId;
    }

    public UUID getSpouseNpcUuid() {
        return this.spouseNpcUuid;
    }

    public void setSpouseNpcUuid(UUID spouseNpcUuid) {
        this.spouseNpcUuid = spouseNpcUuid;
    }

    public String getSpouseName() {
        return this.spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName == null ? "" : spouseName;
    }

    public String getSpouseKingdomId() {
        return this.spouseKingdomId;
    }

    public void setSpouseKingdomId(String spouseKingdomId) {
        this.spouseKingdomId = spouseKingdomId == null ? "" : spouseKingdomId;
    }

    public String getSpouseHouseId() {
        return this.spouseHouseId;
    }

    public void setSpouseHouseId(String spouseHouseId) {
        this.spouseHouseId = spouseHouseId == null ? "" : spouseHouseId;
    }

    public String getSourceCastleId() {
        return this.sourceCastleId;
    }

    public void setSourceCastleId(String sourceCastleId) {
        this.sourceCastleId = sourceCastleId == null ? "" : sourceCastleId;
    }

    public boolean isSourceVillage() {
        return this.sourceVillage;
    }

    public void setSourceVillage(boolean sourceVillage) {
        this.sourceVillage = sourceVillage;
    }

    public boolean isSourceSettler() {
        return this.sourceSettler;
    }

    public void setSourceSettler(boolean sourceSettler) {
        this.sourceSettler = sourceSettler;
    }

    public boolean isLordMarriage() {
        return this.lordMarriage;
    }

    public void setLordMarriage(boolean lordMarriage) {
        this.lordMarriage = lordMarriage;
    }

    public NpcAttributeProfile getSpouseAttributes() {
        return this.spouseAttributes;
    }

    public String getSpouseFamilyStageId() {
        return this.spouseFamilyStageId;
    }

    public void setSpouseFamilyStageId(String spouseFamilyStageId) {
        this.spouseFamilyStageId = spouseFamilyStageId == null ? "" : spouseFamilyStageId;
    }

    public void setSpouseAttributes(NpcAttributeProfile spouseAttributes) {
        this.spouseAttributes = spouseAttributes == null ? NpcAttributeProfile.EMPTY : spouseAttributes;
    }

    public NpcAppearanceProfile getSpouseAppearance() {
        return this.spouseAppearance;
    }

    public void setSpouseAppearance(NpcAppearanceProfile spouseAppearance) {
        this.spouseAppearance = spouseAppearance == null ? NpcAppearanceProfile.EMPTY : spouseAppearance;
    }

    public List<String> getTransferredCastleIds() {
        return List.copyOf(this.transferredCastleIds);
    }

    public long getSummonCooldownEndsAtMs() {
        return this.summonCooldownEndsAtMs;
    }

    public void setSummonCooldownEndsAtMs(long summonCooldownEndsAtMs) {
        this.summonCooldownEndsAtMs = Math.max(0L, summonCooldownEndsAtMs);
    }

    public long getSummonedUntilDay() {
        return this.summonedUntilDay;
    }

    public void setSummonedUntilDay(long summonedUntilDay) {
        this.summonedUntilDay = summonedUntilDay;
    }

    public boolean isSpouseInParty() {
        return this.spouseInParty;
    }

    public long getBabyCooldownUnlockDay() {
        return this.babyCooldownUnlockDay;
    }

    public void setBabyCooldownUnlockDay(long babyCooldownUnlockDay) {
        this.babyCooldownUnlockDay = Math.max(0L, babyCooldownUnlockDay);
    }

    public void setSpouseInParty(boolean spouseInParty) {
        this.spouseInParty = spouseInParty;
    }

    public void setTransferredCastleIds(List<String> castleIds) {
        this.transferredCastleIds.clear();
        if (castleIds != null) {
            for (String castleId : castleIds) {
                if (castleId == null || castleId.isBlank()) continue;
                this.transferredCastleIds.add(castleId);
            }
        }
    }

    public void clear() {
        this.spouseNpcId = "";
        this.spouseNpcUuid = null;
        this.spouseName = "";
        this.spouseKingdomId = "";
        this.spouseHouseId = "";
        this.sourceCastleId = "";
        this.sourceVillage = false;
        this.sourceSettler = false;
        this.lordMarriage = false;
        this.spouseFamilyStageId = "";
        this.spouseAttributes = NpcAttributeProfile.EMPTY;
        this.spouseAppearance = NpcAppearanceProfile.EMPTY;
        this.summonCooldownEndsAtMs = 0L;
        this.summonedUntilDay = -1L;
        this.babyCooldownUnlockDay = 0L;
        this.spouseInParty = false;
        this.transferredCastleIds.clear();
    }

    public void copyFrom(PlayerMarriageProfile other) {
        this.clear();
        if (other == null) {
            return;
        }
        this.spouseNpcId = other.spouseNpcId;
        this.spouseNpcUuid = other.spouseNpcUuid;
        this.spouseName = other.spouseName;
        this.spouseKingdomId = other.spouseKingdomId;
        this.spouseHouseId = other.spouseHouseId;
        this.sourceCastleId = other.sourceCastleId;
        this.sourceVillage = other.sourceVillage;
        this.sourceSettler = other.sourceSettler;
        this.lordMarriage = other.lordMarriage;
        this.spouseFamilyStageId = other.spouseFamilyStageId;
        this.spouseAttributes = other.spouseAttributes;
        this.spouseAppearance = other.spouseAppearance;
        this.summonCooldownEndsAtMs = other.summonCooldownEndsAtMs;
        this.summonedUntilDay = other.summonedUntilDay;
        this.babyCooldownUnlockDay = other.babyCooldownUnlockDay;
        this.spouseInParty = other.spouseInParty;
        this.transferredCastleIds.addAll(other.transferredCastleIds);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("spouse_npc_id", this.spouseNpcId);
        if (this.spouseNpcUuid != null) {
            nbt.method_25927("spouse_npc_uuid", this.spouseNpcUuid);
        }
        nbt.method_10582("spouse_name", this.spouseName);
        nbt.method_10582("spouse_kingdom_id", this.spouseKingdomId);
        nbt.method_10582("spouse_house_id", this.spouseHouseId);
        nbt.method_10582("source_castle_id", this.sourceCastleId);
        nbt.method_10556("source_village", this.sourceVillage);
        nbt.method_10556("source_settler", this.sourceSettler);
        nbt.method_10556("lord_marriage", this.lordMarriage);
        nbt.method_10582("spouse_family_stage_id", this.spouseFamilyStageId);
        nbt.method_10566("spouse_attributes", (class_2520)this.spouseAttributes.toNbt());
        nbt.method_10566("spouse_appearance", (class_2520)this.spouseAppearance.toNbt());
        nbt.method_10544("summon_cooldown_ends_at_ms", this.summonCooldownEndsAtMs);
        nbt.method_10544("summoned_until_day", this.summonedUntilDay);
        nbt.method_10544("baby_cooldown_unlock_day", this.babyCooldownUnlockDay);
        nbt.method_10556("spouse_in_party", this.spouseInParty);
        class_2499 transferred = new class_2499();
        for (String castleId : this.transferredCastleIds) {
            transferred.add((Object)class_2519.method_23256((String)castleId));
        }
        nbt.method_10566("transferred_castles", (class_2520)transferred);
        return nbt;
    }

    public static PlayerMarriageProfile fromNbt(class_2487 nbt) {
        PlayerMarriageProfile profile = new PlayerMarriageProfile();
        profile.setSpouseNpcId(nbt.method_10558("spouse_npc_id"));
        if (nbt.method_25928("spouse_npc_uuid")) {
            profile.setSpouseNpcUuid(nbt.method_25926("spouse_npc_uuid"));
        }
        profile.setSpouseName(nbt.method_10558("spouse_name"));
        profile.setSpouseKingdomId(nbt.method_10558("spouse_kingdom_id"));
        profile.setSpouseHouseId(nbt.method_10558("spouse_house_id"));
        profile.setSourceCastleId(nbt.method_10558("source_castle_id"));
        profile.setSourceVillage(nbt.method_10577("source_village"));
        profile.setSourceSettler(nbt.method_10577("source_settler"));
        profile.setLordMarriage(nbt.method_10577("lord_marriage"));
        profile.setSpouseFamilyStageId(nbt.method_10558("spouse_family_stage_id"));
        if (nbt.method_10573("spouse_attributes", 10)) {
            profile.setSpouseAttributes(NpcAttributeProfile.fromNbt(nbt.method_10562("spouse_attributes")));
        }
        if (nbt.method_10573("spouse_appearance", 10)) {
            profile.setSpouseAppearance(NpcAppearanceProfile.fromNbt(nbt.method_10562("spouse_appearance")));
        }
        profile.setSummonCooldownEndsAtMs(nbt.method_10537("summon_cooldown_ends_at_ms"));
        profile.setSummonedUntilDay(nbt.method_10573("summoned_until_day", 99) ? nbt.method_10537("summoned_until_day") : -1L);
        profile.setBabyCooldownUnlockDay(nbt.method_10573("baby_cooldown_unlock_day", 99) ? nbt.method_10537("baby_cooldown_unlock_day") : 0L);
        profile.setSpouseInParty(nbt.method_10577("spouse_in_party"));
        if (nbt.method_10573("transferred_castles", 9)) {
            class_2499 transferred = nbt.method_10554("transferred_castles", 8);
            ArrayList<String> castleIds = new ArrayList<String>();
            for (int index = 0; index < transferred.size(); ++index) {
                String castleId = transferred.method_10608(index);
                if (castleId.isBlank()) continue;
                castleIds.add(castleId);
            }
            profile.setTransferredCastleIds(castleIds);
        }
        return profile;
    }
}

