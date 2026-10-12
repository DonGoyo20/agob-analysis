/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 */
package trep.got.troop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import trep.got.kingdom.ReputationTitle;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.DynamicTroopRegistry;
import trep.got.troop.DynamicTroopUnitType;
import trep.got.troop.PartyFormationType;

public final class PlayerTroopProfile {
    private static final long SUMMON_COOLDOWN_PER_UNIT_TICKS = 100L;
    private static final long SUMMON_COOLDOWN_MAX_TICKS = 3600L;
    private final ArrayList<TroopUnitInstance> units = new ArrayList();
    private int nextUnitId = 1;
    private int experienceCursor;
    private long lastSummonTime = Long.MIN_VALUE;
    private PartyFormationType formationType = PartyFormationType.LINE;

    public Map<String, Integer> troopCounts() {
        LinkedHashMap<String, Integer> counts = new LinkedHashMap<String, Integer>();
        for (TroopUnitInstance unit : this.units) {
            counts.merge(unit.troopId(), 1, Integer::sum);
        }
        return Map.copyOf(counts);
    }

    public int getCount(class_2960 troopId) {
        if (troopId == null) {
            return 0;
        }
        int count = 0;
        String troopKey = troopId.toString();
        for (TroopUnitInstance unit : this.units) {
            if (!troopKey.equals(unit.troopId())) continue;
            ++count;
        }
        return count;
    }

    public int totalTroopCount() {
        return this.units.size();
    }

    public List<TroopUnitInstance> units() {
        return List.copyOf(this.units);
    }

    public int getUpgradeableCount() {
        int total = 0;
        for (TroopUnitInstance unit : this.units) {
            if (!PlayerTroopProfile.canUpgrade(unit)) continue;
            ++total;
        }
        return total;
    }

    public int getUpgradeableCount(class_2960 troopId) {
        if (troopId == null) {
            return 0;
        }
        int total = 0;
        String troopKey = troopId.toString();
        for (TroopUnitInstance unit : this.units) {
            if (!troopKey.equals(unit.troopId()) || !PlayerTroopProfile.canUpgrade(unit)) continue;
            ++total;
        }
        return total;
    }

    public int getCountByUnitType(DynamicTroopUnitType unitType) {
        int total = 0;
        for (TroopUnitInstance unit : this.units) {
            DynamicTroopDefinition definition = DynamicTroopRegistry.get(unit.troopId());
            if (definition == null || definition.unitType() != unitType) continue;
            ++total;
        }
        return total;
    }

    public int capacityFor(ReputationTitle title) {
        if (title == null) {
            return 10;
        }
        return switch (title.getTier()) {
            case 0 -> 10;
            case 1 -> 25;
            case 2 -> 50;
            case 3 -> 100;
            default -> 200;
        };
    }

    public boolean canHireOne(ReputationTitle title) {
        return this.totalTroopCount() < this.capacityFor(title);
    }

    public void addTroop(class_2960 troopId, int amount) {
        if (troopId == null || amount <= 0) {
            return;
        }
        for (int index = 0; index < amount; ++index) {
            this.units.add(new TroopUnitInstance(this.nextUnitId++, troopId.toString(), 0, null));
        }
    }

    public void bindDeployedEntity(int unitId, UUID deployedEntityUuid) {
        for (int index = 0; index < this.units.size(); ++index) {
            TroopUnitInstance unit = this.units.get(index);
            if (unit.unitId() != unitId) continue;
            this.units.set(index, unit.withDeployedEntityUuid(deployedEntityUuid));
            return;
        }
    }

    public void clearDeployedEntity(int unitId) {
        for (int index = 0; index < this.units.size(); ++index) {
            TroopUnitInstance unit = this.units.get(index);
            if (unit.unitId() != unitId) continue;
            this.units.set(index, unit.withDeployedEntityUuid(null));
            return;
        }
    }

    public void clearDeployedEntity(UUID deployedEntityUuid) {
        if (deployedEntityUuid == null) {
            return;
        }
        for (int index = 0; index < this.units.size(); ++index) {
            TroopUnitInstance unit = this.units.get(index);
            if (!deployedEntityUuid.equals(unit.deployedEntityUuid())) continue;
            this.units.set(index, unit.withDeployedEntityUuid(null));
            return;
        }
    }

    public int addExperienceToNextUnit(int amount) {
        if (amount <= 0 || this.units.isEmpty()) {
            return 0;
        }
        int size = this.units.size();
        int start = Math.floorMod(this.experienceCursor, size);
        for (int offset = 0; offset < size; ++offset) {
            int index = (start + offset) % size;
            TroopUnitInstance unit = this.units.get(index);
            DynamicTroopDefinition definition = DynamicTroopRegistry.get(unit.troopId());
            if (definition == null || definition.upgradeTargets().isEmpty()) continue;
            int max = PlayerTroopProfile.requiredExperienceForUpgrade(definition);
            int nextExperience = Math.min(max, unit.experience() + amount);
            this.units.set(index, unit.withExperience(nextExperience));
            this.experienceCursor = index + 1;
            return amount;
        }
        return 0;
    }

    public int upgradeEligibleUnits(class_2960 troopId) {
        if (troopId == null) {
            return 0;
        }
        String troopKey = troopId.toString();
        int upgraded = 0;
        for (int index = 0; index < this.units.size(); ++index) {
            TroopUnitInstance unit = this.units.get(index);
            if (!troopKey.equals(unit.troopId()) || !PlayerTroopProfile.canUpgrade(unit)) continue;
            DynamicTroopDefinition definition = DynamicTroopRegistry.get(unit.troopId());
            class_2960 targetId = definition.upgradeTargets().getFirst();
            this.units.set(index, new TroopUnitInstance(unit.unitId(), targetId.toString(), 0, unit.deployedEntityUuid()));
            ++upgraded;
        }
        return upgraded;
    }

    public boolean upgradeFirstEligibleUnit(class_2960 troopId, class_2960 targetTroopId) {
        if (troopId == null || targetTroopId == null) {
            return false;
        }
        String troopKey = troopId.toString();
        for (int index = 0; index < this.units.size(); ++index) {
            DynamicTroopDefinition definition;
            TroopUnitInstance unit = this.units.get(index);
            if (!troopKey.equals(unit.troopId()) || !PlayerTroopProfile.canUpgrade(unit) || (definition = DynamicTroopRegistry.get(unit.troopId())) == null || !definition.upgradeTargets().contains(targetTroopId)) continue;
            this.units.set(index, new TroopUnitInstance(unit.unitId(), targetTroopId.toString(), 0, unit.deployedEntityUuid()));
            return true;
        }
        return false;
    }

    public boolean upgradeFirstAnyUnit(class_2960 troopId, class_2960 targetTroopId) {
        if (troopId == null || targetTroopId == null) {
            return false;
        }
        String troopKey = troopId.toString();
        for (int index = 0; index < this.units.size(); ++index) {
            TroopUnitInstance unit = this.units.get(index);
            if (!troopKey.equals(unit.troopId())) continue;
            this.units.set(index, new TroopUnitInstance(unit.unitId(), targetTroopId.toString(), 0, unit.deployedEntityUuid()));
            return true;
        }
        return false;
    }

    public boolean removeUnitById(int unitId) {
        for (int index = 0; index < this.units.size(); ++index) {
            if (this.units.get(index).unitId() != unitId) continue;
            this.units.remove(index);
            this.experienceCursor = Math.floorMod(this.experienceCursor, Math.max(1, this.units.size() + 1));
            return true;
        }
        return false;
    }

    public long getSummonCooldownTicksRemaining(long currentTime) {
        if (this.lastSummonTime == Long.MIN_VALUE || this.units.isEmpty()) {
            return 0L;
        }
        long duration = Math.min(3600L, (long)this.units.size() * 100L);
        long remaining = this.lastSummonTime + duration - currentTime;
        return PlayerTroopProfile.clamp(remaining, 0L, duration);
    }

    private static long clamp(long value, long min, long max) {
        return Math.max(min, Math.min(max, value));
    }

    public void markSummoned(long currentTime) {
        this.lastSummonTime = currentTime;
    }

    public PartyFormationType getFormationType() {
        return this.formationType;
    }

    public PartyFormationType toggleFormationType() {
        this.formationType = this.formationType.next();
        return this.formationType;
    }

    public void setFormationType(PartyFormationType formationType) {
        this.formationType = formationType == null ? PartyFormationType.LINE : formationType;
    }

    public List<TroopUnitInstance> getUnitsForTroop(class_2960 troopId) {
        if (troopId == null) {
            return List.of();
        }
        String troopKey = troopId.toString();
        return this.units.stream().filter(unit -> troopKey.equals(unit.troopId())).sorted(Comparator.comparingInt(TroopUnitInstance::unitId)).toList();
    }

    public void copyFrom(PlayerTroopProfile other) {
        this.units.clear();
        this.units.addAll(other.units);
        this.nextUnitId = other.nextUnitId;
        this.experienceCursor = other.experienceCursor;
        this.lastSummonTime = other.lastSummonTime;
        this.formationType = other.formationType;
    }

    public void clear() {
        this.units.clear();
        this.nextUnitId = 1;
        this.experienceCursor = 0;
        this.lastSummonTime = Long.MIN_VALUE;
        this.formationType = PartyFormationType.LINE;
    }

    public class_2487 toNbt() {
        class_2487 root = new class_2487();
        class_2499 troops = new class_2499();
        for (TroopUnitInstance unit : this.units) {
            class_2487 troop = new class_2487();
            troop.method_10569("unit_id", unit.unitId());
            troop.method_10582("troop_id", unit.troopId());
            troop.method_10569("experience", unit.experience());
            if (unit.deployedEntityUuid() != null) {
                troop.method_25927("deployed_entity_uuid", unit.deployedEntityUuid());
            }
            troops.add((Object)troop);
        }
        root.method_10566("troops", (class_2520)troops);
        root.method_10569("next_unit_id", this.nextUnitId);
        root.method_10569("experience_cursor", this.experienceCursor);
        root.method_10544("last_summon_time", this.lastSummonTime);
        root.method_10582("formation_type", this.formationType.id());
        return root;
    }

    public static PlayerTroopProfile fromNbt(class_2487 root) {
        PlayerTroopProfile profile = new PlayerTroopProfile();
        class_2499 troops = root.method_10554("troops", 10);
        for (int index = 0; index < troops.size(); ++index) {
            class_2487 troop = troops.method_10602(index);
            String troopId = troop.method_10558("troop_id");
            if (troopId.isBlank()) continue;
            if (troop.method_10545("unit_id")) {
                int unitId = troop.method_10550("unit_id");
                int experience = Math.max(0, troop.method_10550("experience"));
                UUID deployedEntityUuid = troop.method_25928("deployed_entity_uuid") ? troop.method_25926("deployed_entity_uuid") : null;
                profile.units.add(new TroopUnitInstance(unitId, troopId, experience, deployedEntityUuid));
                profile.nextUnitId = Math.max(profile.nextUnitId, unitId + 1);
                continue;
            }
            int count = troop.method_10550("count");
            for (int amount = 0; amount < count; ++amount) {
                profile.units.add(new TroopUnitInstance(profile.nextUnitId++, troopId, 0, null));
            }
        }
        if (root.method_10545("next_unit_id")) {
            profile.nextUnitId = Math.max(profile.nextUnitId, root.method_10550("next_unit_id"));
        }
        if (root.method_10545("experience_cursor")) {
            profile.experienceCursor = Math.max(0, root.method_10550("experience_cursor"));
        }
        if (root.method_10545("last_summon_time")) {
            profile.lastSummonTime = root.method_10537("last_summon_time");
        }
        if (root.method_10545("formation_type")) {
            profile.formationType = PartyFormationType.byId(root.method_10558("formation_type"));
        }
        return profile;
    }

    public static int requiredExperienceForUpgrade(DynamicTroopDefinition definition) {
        if (definition == null) {
            return 0;
        }
        return 100 + Math.max(0, definition.tier() - 1) * 75;
    }

    private static boolean canUpgrade(TroopUnitInstance unit) {
        DynamicTroopDefinition definition = DynamicTroopRegistry.get(unit.troopId());
        return definition != null && !definition.upgradeTargets().isEmpty() && unit.experience() >= PlayerTroopProfile.requiredExperienceForUpgrade(definition);
    }

    public record TroopUnitInstance(int unitId, String troopId, int experience, UUID deployedEntityUuid) {
        public TroopUnitInstance withExperience(int experience) {
            return new TroopUnitInstance(this.unitId, this.troopId, Math.max(0, experience), this.deployedEntityUuid);
        }

        public TroopUnitInstance withTroopId(String troopId) {
            return new TroopUnitInstance(this.unitId, troopId, this.experience, this.deployedEntityUuid);
        }

        public TroopUnitInstance withDeployedEntityUuid(UUID deployedEntityUuid) {
            return new TroopUnitInstance(this.unitId, this.troopId, this.experience, deployedEntityUuid);
        }
    }
}

