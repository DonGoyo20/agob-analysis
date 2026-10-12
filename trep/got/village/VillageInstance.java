/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 */
package trep.got.village;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import trep.got.castle.CastleNpcProfile;

public final class VillageInstance {
    public static final int DEFAULT_STOCK_CAPACITY = 100;
    public static final int DEFAULT_STARTING_STOCK = 50;
    private final String id;
    private final String name;
    private String boundCastleId;
    private String boundCastleName;
    private String kingdomId;
    private final class_2338 centerPos;
    private final int claimRadiusChunks;
    private UUID ownerPlayerUuid;
    private String elderName;
    private List<CastleNpcProfile> npcProfiles = new ArrayList<CastleNpcProfile>();
    private class_2960 resourceId;
    private boolean structuresGenerated;
    private int stockCapacity = 100;
    private int stockAmount = 50;
    private long lastStockGrowthDay = -1L;
    private long lastObservedTimeOfDay = -1L;
    private long lastPopulationRefreshDay = -1L;
    private long lastObservedPopulationTimeOfDay = -1L;
    private long raidEndsAtTick;
    private long raidCooldownEndsAtTick;
    private long blockedProductionDay = -1L;

    public VillageInstance(String id, String name, String boundCastleId, String boundCastleName, String kingdomId, class_2338 centerPos, int claimRadiusChunks) {
        this.id = id;
        this.name = name;
        this.boundCastleId = boundCastleId;
        this.boundCastleName = boundCastleName;
        this.kingdomId = kingdomId == null ? "" : kingdomId;
        this.centerPos = centerPos.method_10062();
        this.claimRadiusChunks = claimRadiusChunks;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getBoundCastleId() {
        return this.boundCastleId;
    }

    public void setBoundCastleId(String boundCastleId) {
        this.boundCastleId = boundCastleId == null ? "" : boundCastleId;
    }

    public String getBoundCastleName() {
        return this.boundCastleName;
    }

    public void setBoundCastleName(String boundCastleName) {
        this.boundCastleName = boundCastleName == null ? "" : boundCastleName;
    }

    public String getKingdomId() {
        return this.kingdomId;
    }

    public void setKingdomId(String kingdomId) {
        this.kingdomId = kingdomId == null ? "" : kingdomId;
    }

    public UUID getOwnerPlayerUuid() {
        return this.ownerPlayerUuid;
    }

    public void setOwnerPlayerUuid(UUID ownerPlayerUuid) {
        this.ownerPlayerUuid = ownerPlayerUuid;
    }

    public class_2338 getCenterPos() {
        return this.centerPos;
    }

    public int getClaimRadiusChunks() {
        return this.claimRadiusChunks;
    }

    public String getElderName() {
        return this.elderName == null ? "" : this.elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName == null ? "" : elderName;
    }

    public List<CastleNpcProfile> getNpcProfiles() {
        return List.copyOf(this.npcProfiles);
    }

    public void setNpcProfiles(List<CastleNpcProfile> npcProfiles) {
        this.npcProfiles = npcProfiles == null ? new ArrayList<CastleNpcProfile>() : new ArrayList<CastleNpcProfile>(npcProfiles);
    }

    public class_2960 getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(class_2960 resourceId) {
        this.resourceId = resourceId;
    }

    public boolean hasGeneratedStructures() {
        return this.structuresGenerated;
    }

    public void setStructuresGenerated(boolean structuresGenerated) {
        this.structuresGenerated = structuresGenerated;
    }

    public int getStockCapacity() {
        return Math.max(0, this.stockCapacity);
    }

    public void setStockCapacity(int stockCapacity) {
        this.stockCapacity = Math.max(0, stockCapacity);
        if (this.stockAmount > this.stockCapacity) {
            this.stockAmount = this.stockCapacity;
        }
    }

    public int getStockAmount() {
        return Math.max(0, Math.min(this.stockAmount, this.getStockCapacity()));
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = Math.max(0, Math.min(stockAmount, this.getStockCapacity()));
    }

    public long getLastStockGrowthDay() {
        return this.lastStockGrowthDay;
    }

    public void setLastStockGrowthDay(long lastStockGrowthDay) {
        this.lastStockGrowthDay = lastStockGrowthDay;
    }

    public long getLastObservedTimeOfDay() {
        return this.lastObservedTimeOfDay;
    }

    public void setLastObservedTimeOfDay(long lastObservedTimeOfDay) {
        this.lastObservedTimeOfDay = Math.max(-1L, lastObservedTimeOfDay);
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

    public long getRaidEndsAtTick() {
        return this.raidEndsAtTick;
    }

    public void setRaidEndsAtTick(long raidEndsAtTick) {
        this.raidEndsAtTick = Math.max(0L, raidEndsAtTick);
    }

    public long getRaidCooldownEndsAtTick() {
        return this.raidCooldownEndsAtTick;
    }

    public void setRaidCooldownEndsAtTick(long raidCooldownEndsAtTick) {
        this.raidCooldownEndsAtTick = Math.max(0L, raidCooldownEndsAtTick);
    }

    public long getBlockedProductionDay() {
        return this.blockedProductionDay;
    }

    public void setBlockedProductionDay(long blockedProductionDay) {
        this.blockedProductionDay = blockedProductionDay;
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("id", this.id);
        nbt.method_10582("name", this.name);
        nbt.method_10582("bound_castle_id", this.boundCastleId);
        nbt.method_10582("bound_castle_name", this.boundCastleName);
        nbt.method_10582("kingdom_id", this.kingdomId);
        if (this.ownerPlayerUuid != null) {
            nbt.method_25927("owner_player_uuid", this.ownerPlayerUuid);
        }
        nbt.method_10544("center_pos", this.centerPos.method_10063());
        nbt.method_10569("claim_radius_chunks", this.claimRadiusChunks);
        nbt.method_10582("elder_name", this.getElderName());
        class_2499 npcProfileList = new class_2499();
        for (CastleNpcProfile profile : this.npcProfiles) {
            npcProfileList.add((Object)profile.toNbt());
        }
        nbt.method_10566("npc_profiles", (class_2520)npcProfileList);
        if (this.resourceId != null) {
            nbt.method_10582("resource_id", this.resourceId.toString());
        }
        nbt.method_10556("structures_generated", this.structuresGenerated);
        nbt.method_10569("stock_capacity", this.getStockCapacity());
        nbt.method_10569("stock_amount", this.getStockAmount());
        nbt.method_10544("last_stock_growth_day", this.lastStockGrowthDay);
        nbt.method_10544("last_observed_time_of_day", this.lastObservedTimeOfDay);
        nbt.method_10544("last_population_refresh_day", this.lastPopulationRefreshDay);
        nbt.method_10544("last_observed_population_time_of_day", this.lastObservedPopulationTimeOfDay);
        nbt.method_10544("raid_ends_at_tick", this.raidEndsAtTick);
        nbt.method_10544("raid_cooldown_ends_at_tick", this.raidCooldownEndsAtTick);
        nbt.method_10544("blocked_production_day", this.blockedProductionDay);
        return nbt;
    }

    public static VillageInstance fromNbt(class_2487 nbt) {
        VillageInstance village = new VillageInstance(nbt.method_10558("id"), nbt.method_10558("name"), nbt.method_10558("bound_castle_id"), nbt.method_10558("bound_castle_name"), nbt.method_10558("kingdom_id"), class_2338.method_10092((long)nbt.method_10537("center_pos")), nbt.method_10550("claim_radius_chunks"));
        village.setElderName(nbt.method_10558("elder_name"));
        if (nbt.method_10573("npc_profiles", 9)) {
            class_2499 profileList = nbt.method_10554("npc_profiles", 10);
            ArrayList<CastleNpcProfile> profiles = new ArrayList<CastleNpcProfile>();
            for (class_2520 element : profileList) {
                if (!(element instanceof class_2487)) continue;
                class_2487 compound = (class_2487)element;
                profiles.add(CastleNpcProfile.fromNbt(compound));
            }
            village.setNpcProfiles(profiles);
        }
        if (nbt.method_25928("owner_player_uuid")) {
            village.setOwnerPlayerUuid(nbt.method_25926("owner_player_uuid"));
        }
        if (nbt.method_10545("resource_id")) {
            village.setResourceId(class_2960.method_12829((String)nbt.method_10558("resource_id")));
        }
        if (nbt.method_10545("structures_generated")) {
            village.setStructuresGenerated(nbt.method_10577("structures_generated"));
        }
        if (nbt.method_10545("stock_capacity")) {
            village.setStockCapacity(nbt.method_10550("stock_capacity"));
        }
        if (nbt.method_10545("stock_amount")) {
            village.setStockAmount(nbt.method_10550("stock_amount"));
        }
        if (nbt.method_10545("last_stock_growth_day")) {
            village.setLastStockGrowthDay(nbt.method_10537("last_stock_growth_day"));
        }
        if (nbt.method_10545("last_observed_time_of_day")) {
            village.setLastObservedTimeOfDay(nbt.method_10537("last_observed_time_of_day"));
        }
        if (nbt.method_10545("last_population_refresh_day")) {
            village.setLastPopulationRefreshDay(nbt.method_10537("last_population_refresh_day"));
        }
        if (nbt.method_10545("last_observed_population_time_of_day")) {
            village.setLastObservedPopulationTimeOfDay(nbt.method_10537("last_observed_population_time_of_day"));
        }
        if (nbt.method_10545("raid_ends_at_tick")) {
            village.setRaidEndsAtTick(nbt.method_10537("raid_ends_at_tick"));
        }
        if (nbt.method_10545("raid_cooldown_ends_at_tick")) {
            village.setRaidCooldownEndsAtTick(nbt.method_10537("raid_cooldown_ends_at_tick"));
        }
        if (nbt.method_10545("blocked_production_day")) {
            village.setBlockedProductionDay(nbt.method_10537("blocked_production_day"));
        }
        return village;
    }
}

