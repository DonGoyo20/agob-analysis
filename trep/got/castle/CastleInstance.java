/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_2338
 *  net.minecraft.class_2371
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.castle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import net.minecraft.class_7225;
import trep.got.castle.CastleNpcProfile;
import trep.got.castle.CastleRaidState;
import trep.got.castle.CastleSubtype;
import trep.got.castle.CastleTier;
import trep.got.castle.CastleTitle;
import trep.got.castle.CityStructureClaim;
import trep.got.castle.PlayerSettlementTier;
import trep.got.castle.SettlerProfile;
import trep.got.castle.WallGateClaim;

public final class CastleInstance {
    private final String id;
    private final String definitionId;
    private final CastleTier tier;
    private final class_2960 templateId;
    private final class_2338 origin;
    private final class_2338 bannerPos;
    private final int footprintChunks;
    private int claimRadiusChunks;
    private final int areaRadiusBlocks;
    private final boolean naturalClaim;
    private final CastleTitle title;
    private final class_2960 lootTableId;
    private UUID ownerPlayerUuid;
    private String ownerKingdomId;
    private String ownerHouseId;
    private String ownerReligionId;
    private String lordName;
    private final Set<UUID> memberUuids = new LinkedHashSet<UUID>();
    private final Set<CastleSubtype> subtypes = new LinkedHashSet<CastleSubtype>();
    private final class_2371<class_1799> storageItems;
    private CastleRaidState raidState = CastleRaidState.PEACE;
    private long raidEndsAtTick;
    private long lastPopulationRefreshDay = -1L;
    private long lastObservedPopulationTimeOfDay = -1L;
    private PlayerSettlementTier settlementTier = PlayerSettlementTier.PLAYER_SETTLEMENT;
    private int settlementLevel = 1;
    private long createdDay = -1L;
    private long lastSettlerOfferDay = -1L;
    private long lastSettlerProductionDay = -1L;
    private final List<SettlerProfile> settlers = new ArrayList<SettlerProfile>();
    private final List<CastleNpcProfile> castleNpcProfiles = new ArrayList<CastleNpcProfile>();
    private final List<CityStructureClaim> cityStructureClaims = new ArrayList<CityStructureClaim>();
    private final List<WallGateClaim> wallGateClaims = new ArrayList<WallGateClaim>();

    public CastleInstance(String id, String definitionId, CastleTier tier, class_2960 templateId, class_2338 origin, class_2338 bannerPos, int footprintChunks, int claimRadiusChunks, int areaRadiusBlocks, boolean naturalClaim, CastleTitle title, class_2960 lootTableId) {
        this.id = id;
        this.definitionId = definitionId;
        this.tier = tier;
        this.templateId = templateId;
        this.origin = origin.method_10062();
        this.bannerPos = bannerPos.method_10062();
        this.footprintChunks = footprintChunks;
        this.claimRadiusChunks = claimRadiusChunks;
        this.areaRadiusBlocks = areaRadiusBlocks;
        this.naturalClaim = naturalClaim;
        this.title = title;
        this.lootTableId = lootTableId;
        this.storageItems = class_2371.method_10213((int)(tier.getStorageRows() * 9), (Object)class_1799.field_8037);
    }

    public String getId() {
        return this.id;
    }

    public String getDefinitionId() {
        return this.definitionId;
    }

    public CastleTier getTier() {
        return this.tier;
    }

    public class_2960 getTemplateId() {
        return this.templateId;
    }

    public class_2338 getOrigin() {
        return this.origin;
    }

    public class_2338 getBannerPos() {
        return this.bannerPos;
    }

    public int getFootprintChunks() {
        return this.footprintChunks;
    }

    public int getClaimRadiusChunks() {
        return this.claimRadiusChunks;
    }

    public void setClaimRadiusChunks(int claimRadiusChunks) {
        this.claimRadiusChunks = Math.max(0, claimRadiusChunks);
    }

    public int getAreaRadiusBlocks() {
        return this.areaRadiusBlocks;
    }

    public boolean isNaturalClaim() {
        return this.naturalClaim;
    }

    public CastleTitle getTitle() {
        return this.title;
    }

    public class_2960 getLootTableId() {
        return this.lootTableId;
    }

    public UUID getOwnerPlayerUuid() {
        return this.ownerPlayerUuid;
    }

    public void setOwnerPlayerUuid(UUID ownerPlayerUuid) {
        this.ownerPlayerUuid = ownerPlayerUuid;
    }

    public String getOwnerKingdomId() {
        return this.ownerKingdomId == null ? "" : this.ownerKingdomId;
    }

    public void setOwnerKingdomId(String ownerKingdomId) {
        this.ownerKingdomId = ownerKingdomId == null ? "" : ownerKingdomId;
    }

    public String getOwnerHouseId() {
        return this.ownerHouseId == null ? "" : this.ownerHouseId;
    }

    public void setOwnerHouseId(String ownerHouseId) {
        this.ownerHouseId = ownerHouseId == null ? "" : ownerHouseId;
    }

    public String getOwnerReligionId() {
        return this.ownerReligionId == null ? "" : this.ownerReligionId;
    }

    public void setOwnerReligionId(String ownerReligionId) {
        this.ownerReligionId = ownerReligionId == null ? "" : ownerReligionId;
    }

    public String getLordName() {
        return this.lordName == null ? "" : this.lordName;
    }

    public void setLordName(String lordName) {
        this.lordName = lordName == null ? "" : lordName;
    }

    public Set<UUID> getMemberUuids() {
        return Set.copyOf(this.memberUuids);
    }

    public void addMember(UUID playerUuid) {
        if (playerUuid != null) {
            this.memberUuids.add(playerUuid);
        }
    }

    public void removeMember(UUID playerUuid) {
        this.memberUuids.remove(playerUuid);
    }

    public Set<CastleSubtype> getSubtypes() {
        return Collections.unmodifiableSet(this.subtypes);
    }

    public boolean hasSubtype(CastleSubtype subtype) {
        return subtype != null && this.subtypes.contains((Object)subtype);
    }

    public void addSubtype(CastleSubtype subtype) {
        if (subtype != null) {
            this.subtypes.add(subtype);
        }
    }

    public CastleRaidState getRaidState() {
        return this.raidState;
    }

    public void setRaidState(CastleRaidState raidState) {
        this.raidState = raidState == null ? CastleRaidState.PEACE : raidState;
    }

    public long getRaidEndsAtTick() {
        return this.raidEndsAtTick;
    }

    public void setRaidEndsAtTick(long raidEndsAtTick) {
        this.raidEndsAtTick = raidEndsAtTick;
    }

    public long getLastPopulationRefreshDay() {
        return this.lastPopulationRefreshDay;
    }

    public void setLastPopulationRefreshDay(long lastPopulationRefreshDay) {
        this.lastPopulationRefreshDay = lastPopulationRefreshDay;
    }

    public long getLastObservedPopulationTimeOfDay() {
        return this.lastObservedPopulationTimeOfDay;
    }

    public void setLastObservedPopulationTimeOfDay(long lastObservedPopulationTimeOfDay) {
        this.lastObservedPopulationTimeOfDay = Math.max(-1L, lastObservedPopulationTimeOfDay);
    }

    public int getStorageRows() {
        return this.isPlayerSettlement() ? CastleInstance.settlementStorageRows(this.settlementTier) : this.tier.getStorageRows();
    }

    public PlayerSettlementTier getSettlementTier() {
        return this.settlementTier;
    }

    public void setSettlementTier(PlayerSettlementTier settlementTier) {
        this.settlementTier = settlementTier == null ? PlayerSettlementTier.PLAYER_SETTLEMENT : settlementTier;
        this.resizeStorage(this.getStorageRows() * 9);
    }

    public int getSettlementLevel() {
        return this.settlementLevel;
    }

    public void setSettlementLevel(int settlementLevel) {
        this.settlementLevel = Math.max(1, Math.min(5, settlementLevel));
    }

    public long getCreatedDay() {
        return this.createdDay;
    }

    public void setCreatedDay(long createdDay) {
        this.createdDay = createdDay;
    }

    public long getLastSettlerOfferDay() {
        return this.lastSettlerOfferDay;
    }

    public void setLastSettlerOfferDay(long lastSettlerOfferDay) {
        this.lastSettlerOfferDay = lastSettlerOfferDay;
    }

    public long getLastSettlerProductionDay() {
        return this.lastSettlerProductionDay;
    }

    public void setLastSettlerProductionDay(long lastSettlerProductionDay) {
        this.lastSettlerProductionDay = lastSettlerProductionDay;
    }

    public List<SettlerProfile> getSettlers() {
        return List.copyOf(this.settlers);
    }

    public void setSettlers(List<SettlerProfile> settlers) {
        this.settlers.clear();
        if (settlers != null) {
            this.settlers.addAll(settlers);
        }
    }

    public void addSettler(SettlerProfile settler) {
        if (settler != null) {
            this.settlers.add(settler);
        }
    }

    public void removeSettler(String settlerId) {
        this.settlers.removeIf(settler -> settler.id().equals(settlerId));
    }

    public List<CastleNpcProfile> getCastleNpcProfiles() {
        return List.copyOf(this.castleNpcProfiles);
    }

    public void setCastleNpcProfiles(List<CastleNpcProfile> profiles) {
        this.castleNpcProfiles.clear();
        if (profiles != null) {
            this.castleNpcProfiles.addAll(profiles);
        }
    }

    public void addCastleNpcProfile(CastleNpcProfile profile) {
        if (profile != null) {
            this.castleNpcProfiles.add(profile);
        }
    }

    public List<CityStructureClaim> getCityStructureClaims() {
        return List.copyOf(this.cityStructureClaims);
    }

    public void setCityStructureClaims(List<CityStructureClaim> claims) {
        this.cityStructureClaims.clear();
        if (claims != null) {
            this.cityStructureClaims.addAll(claims);
        }
    }

    public void addCityStructureClaim(CityStructureClaim claim) {
        if (claim != null) {
            this.cityStructureClaims.add(claim);
        }
    }

    public List<WallGateClaim> getWallGateClaims() {
        return List.copyOf(this.wallGateClaims);
    }

    public void setWallGateClaims(List<WallGateClaim> claims) {
        this.wallGateClaims.clear();
        if (claims != null) {
            this.wallGateClaims.addAll(claims);
        }
    }

    public void addWallGateClaim(WallGateClaim claim) {
        if (claim != null) {
            this.wallGateClaims.add(claim);
        }
    }

    public int getStorageSize() {
        return this.storageItems.size();
    }

    public class_2371<class_1799> getStorageItems() {
        return this.storageItems;
    }

    private boolean isPlayerSettlement() {
        return "player_claim".equals(this.definitionId);
    }

    private static int settlementStorageRows(PlayerSettlementTier tier) {
        return switch (tier == null ? PlayerSettlementTier.PLAYER_SETTLEMENT : tier) {
            default -> throw new MatchException(null, null);
            case PlayerSettlementTier.PLAYER_SETTLEMENT -> 1;
            case PlayerSettlementTier.SETTLEMENT -> 2;
            case PlayerSettlementTier.LORDSHIP -> 3;
            case PlayerSettlementTier.CAPITAL -> 4;
        };
    }

    private void resizeStorage(int newSize) {
        if (newSize <= 0 || newSize == this.storageItems.size()) {
            return;
        }
        class_2371 resized = class_2371.method_10213((int)newSize, (Object)class_1799.field_8037);
        for (int slot = 0; slot < Math.min(this.storageItems.size(), resized.size()); ++slot) {
            resized.set(slot, (Object)((class_1799)this.storageItems.get(slot)));
        }
        this.storageItems.clear();
        this.storageItems.addAll((Collection)resized);
    }

    public class_2487 toNbt(class_7225.class_7874 registries) {
        class_2487 nbt = new class_2487();
        nbt.method_10582("id", this.id);
        nbt.method_10582("definition_id", this.definitionId);
        nbt.method_10582("tier", this.tier.getId());
        nbt.method_10582("template_id", this.templateId.toString());
        nbt.method_10544("origin", this.origin.method_10063());
        nbt.method_10544("banner_pos", this.bannerPos.method_10063());
        nbt.method_10569("footprint_chunks", this.footprintChunks);
        nbt.method_10569("claim_radius_chunks", this.claimRadiusChunks);
        nbt.method_10569("area_radius_blocks", this.areaRadiusBlocks);
        nbt.method_10556("natural_claim", this.naturalClaim);
        nbt.method_10566("title", (class_2520)this.title.toNbt());
        nbt.method_10582("loot_table_id", this.lootTableId.toString());
        if (this.ownerPlayerUuid != null) {
            nbt.method_25927("owner_player_uuid", this.ownerPlayerUuid);
        }
        nbt.method_10582("owner_kingdom_id", this.ownerKingdomId == null ? "" : this.ownerKingdomId);
        nbt.method_10582("owner_house_id", this.ownerHouseId == null ? "" : this.ownerHouseId);
        nbt.method_10582("owner_religion_id", this.ownerReligionId == null ? "" : this.ownerReligionId);
        nbt.method_10582("lord_name", this.lordName == null ? "" : this.lordName);
        class_2499 members = new class_2499();
        for (UUID uUID : this.memberUuids) {
            members.add((Object)class_2519.method_23256((String)uUID.toString()));
        }
        nbt.method_10566("member_uuids", (class_2520)members);
        class_2499 subtypeList = new class_2499();
        for (CastleSubtype castleSubtype : this.subtypes) {
            subtypeList.add((Object)class_2519.method_23256((String)castleSubtype.getId()));
        }
        nbt.method_10566("subtypes", (class_2520)subtypeList);
        nbt.method_10582("raid_state", this.raidState.name());
        nbt.method_10544("raid_ends_at_tick", this.raidEndsAtTick);
        nbt.method_10544("last_population_refresh_day", this.lastPopulationRefreshDay);
        nbt.method_10544("last_observed_population_time_of_day", this.lastObservedPopulationTimeOfDay);
        nbt.method_10582("settlement_tier_id", this.settlementTier.getId());
        nbt.method_10569("settlement_level", this.settlementLevel);
        nbt.method_10544("created_day", this.createdDay);
        nbt.method_10544("last_settler_offer_day", this.lastSettlerOfferDay);
        nbt.method_10544("last_settler_production_day", this.lastSettlerProductionDay);
        class_2499 class_24992 = new class_2499();
        for (SettlerProfile settlerProfile : this.settlers) {
            class_24992.add((Object)settlerProfile.toNbt());
        }
        nbt.method_10566("settlers", (class_2520)class_24992);
        class_2499 class_24993 = new class_2499();
        for (CastleNpcProfile castleNpcProfile : this.castleNpcProfiles) {
            class_24993.add((Object)castleNpcProfile.toNbt());
        }
        nbt.method_10566("castle_npcs", (class_2520)class_24993);
        class_2499 class_24994 = new class_2499();
        for (CityStructureClaim cityStructureClaim : this.cityStructureClaims) {
            class_24994.add((Object)cityStructureClaim.toNbt());
        }
        nbt.method_10566("city_structures", (class_2520)class_24994);
        class_2499 class_24995 = new class_2499();
        for (WallGateClaim claim : this.wallGateClaims) {
            class_24995.add((Object)claim.toNbt());
        }
        nbt.method_10566("wall_gates", (class_2520)class_24995);
        class_2499 class_24996 = new class_2499();
        for (int slot = 0; slot < this.storageItems.size(); ++slot) {
            class_2520 encoded;
            class_1799 stack = (class_1799)this.storageItems.get(slot);
            if (stack.method_7960() || !((encoded = stack.method_57358(registries)) instanceof class_2487)) continue;
            class_2487 stackNbt = (class_2487)encoded;
            stackNbt.method_10569("slot", slot);
            class_24996.add((Object)stackNbt);
        }
        nbt.method_10566("storage_items", (class_2520)class_24996);
        return nbt;
    }

    public static CastleInstance fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        CastleInstance instance = new CastleInstance(nbt.method_10558("id"), nbt.method_10558("definition_id"), CastleTier.byId(nbt.method_10558("tier")), class_2960.method_60654((String)nbt.method_10558("template_id")), class_2338.method_10092((long)nbt.method_10537("origin")), nbt.method_10545("banner_pos") ? class_2338.method_10092((long)nbt.method_10537("banner_pos")) : class_2338.method_10092((long)nbt.method_10537("origin")), nbt.method_10550("footprint_chunks"), nbt.method_10545("claim_radius_chunks") ? nbt.method_10550("claim_radius_chunks") : 4, nbt.method_10550("area_radius_blocks"), nbt.method_10577("natural_claim"), CastleTitle.fromNbt(nbt.method_10562("title")), class_2960.method_60654((String)nbt.method_10558("loot_table_id")));
        if (nbt.method_25928("owner_player_uuid")) {
            instance.setOwnerPlayerUuid(nbt.method_25926("owner_player_uuid"));
        }
        instance.setOwnerKingdomId(nbt.method_10558("owner_kingdom_id"));
        instance.setOwnerHouseId(nbt.method_10558("owner_house_id"));
        instance.setOwnerReligionId(nbt.method_10558("owner_religion_id"));
        instance.setLordName(nbt.method_10558("lord_name"));
        class_2499 members = nbt.method_10554("member_uuids", 8);
        for (int index = 0; index < members.size(); ++index) {
            instance.addMember(UUID.fromString(members.method_10608(index)));
        }
        class_2499 subtypeList = nbt.method_10554("subtypes", 8);
        for (int index = 0; index < subtypeList.size(); ++index) {
            CastleSubtype subtype = CastleSubtype.byId(subtypeList.method_10608(index));
            if (subtype == null) continue;
            instance.addSubtype(subtype);
        }
        instance.setRaidState(CastleRaidState.byId(nbt.method_10558("raid_state")));
        instance.setRaidEndsAtTick(nbt.method_10537("raid_ends_at_tick"));
        if (nbt.method_10545("last_population_refresh_day")) {
            instance.setLastPopulationRefreshDay(nbt.method_10537("last_population_refresh_day"));
        }
        if (nbt.method_10545("last_observed_population_time_of_day")) {
            instance.setLastObservedPopulationTimeOfDay(nbt.method_10537("last_observed_population_time_of_day"));
        }
        if (nbt.method_10545("settlement_tier_id")) {
            instance.setSettlementTier(PlayerSettlementTier.byId(nbt.method_10558("settlement_tier_id")));
        }
        if (nbt.method_10545("settlement_level")) {
            instance.setSettlementLevel(nbt.method_10550("settlement_level"));
        }
        if (nbt.method_10545("created_day")) {
            instance.setCreatedDay(nbt.method_10537("created_day"));
        }
        if (nbt.method_10545("last_settler_offer_day")) {
            instance.setLastSettlerOfferDay(nbt.method_10537("last_settler_offer_day"));
        }
        if (nbt.method_10545("last_settler_production_day")) {
            instance.setLastSettlerProductionDay(nbt.method_10537("last_settler_production_day"));
        }
        class_2499 settlersList = nbt.method_10554("settlers", 10);
        for (int index = 0; index < settlersList.size(); ++index) {
            instance.addSettler(SettlerProfile.fromNbt(settlersList.method_10602(index)));
        }
        class_2499 castleNpcList = nbt.method_10554("castle_npcs", 10);
        for (int index = 0; index < castleNpcList.size(); ++index) {
            instance.addCastleNpcProfile(CastleNpcProfile.fromNbt(castleNpcList.method_10602(index)));
        }
        class_2499 cityStructureList = nbt.method_10554("city_structures", 10);
        for (int index = 0; index < cityStructureList.size(); ++index) {
            instance.addCityStructureClaim(CityStructureClaim.fromNbt(cityStructureList.method_10602(index)));
        }
        class_2499 wallGateList = nbt.method_10554("wall_gates", 10);
        for (int index = 0; index < wallGateList.size(); ++index) {
            instance.addWallGateClaim(WallGateClaim.fromNbt(wallGateList.method_10602(index)));
        }
        class_2499 storageList = nbt.method_10554("storage_items", 10);
        for (int index = 0; index < storageList.size(); ++index) {
            class_2487 stackNbt = storageList.method_10602(index);
            int slot = stackNbt.method_10550("slot");
            if (slot < 0 || slot >= instance.storageItems.size()) continue;
            instance.storageItems.set(slot, (Object)class_1799.method_57359((class_7225.class_7874)registries, (class_2487)stackNbt));
        }
        return instance;
    }
}

