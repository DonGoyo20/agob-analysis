/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_2960
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.troop;

import java.util.List;
import java.util.Map;
import net.minecraft.class_1304;
import net.minecraft.class_2960;
import org.jetbrains.annotations.Nullable;
import trep.got.character.CharacterSelectionOption;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.Religion;
import trep.got.kingdom.ReputationRoute;
import trep.got.kingdom.ReputationTitle;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotKingdoms;
import trep.got.troop.DynamicTroopUnitType;

public record DynamicTroopDefinition(class_2960 id, class_2960 kingdomId, String displayName, DynamicTroopUnitType unitType, int tier, double baseHealth, double baseDamage, double baseSpeed, boolean villageSpawnOnly, @Nullable String requiredReligionId, @Nullable String requiredHouseId, List<String> disallowedHireKingdomIds, @Nullable ReputationTitle requiredReputationTitle, List<String> traits, List<class_2960> upgradeTargets, class_2960 skinTexture, class_2960 hireCostItem, int hireCostAmount, Map<class_1304, class_2960> equipment, Map<String, class_2960> mountEquipment) {
    public DynamicTroopDefinition(class_2960 id, class_2960 kingdomId, String displayName, DynamicTroopUnitType unitType, int tier, double baseHealth, double baseDamage, double baseSpeed, boolean villageSpawnOnly, @Nullable String requiredReligionId, @Nullable String requiredHouseId, List<String> disallowedHireKingdomIds, @Nullable ReputationTitle requiredReputationTitle, List<String> traits, List<class_2960> upgradeTargets, class_2960 skinTexture, class_2960 hireCostItem, int hireCostAmount, Map<class_1304, class_2960> equipment, Map<String, class_2960> mountEquipment) {
        disallowedHireKingdomIds = List.copyOf(disallowedHireKingdomIds);
        traits = List.copyOf(traits);
        upgradeTargets = List.copyOf(upgradeTargets);
        equipment = Map.copyOf(equipment);
        mountEquipment = Map.copyOf(mountEquipment);
    }

    public class_2960 equipment(class_1304 slot) {
        return this.equipment.get(slot);
    }

    public class_2960 mountEquipment(String key) {
        return this.mountEquipment.get(key);
    }

    public boolean hasReputationRequirement() {
        return this.requiredReputationTitle != null;
    }

    public boolean hasReligionRequirement() {
        return this.requiredReligionId != null && !this.requiredReligionId.isBlank();
    }

    public boolean hasHouseRequirement() {
        return this.requiredHouseId != null && !this.requiredHouseId.isBlank();
    }

    public boolean hasDisallowedHireKingdoms() {
        return !this.disallowedHireKingdomIds.isEmpty();
    }

    public boolean meetsReputationRequirement(@Nullable ReputationTitle currentTitle) {
        if (this.requiredReputationTitle == null) {
            return true;
        }
        if (currentTitle == null) {
            return false;
        }
        if (this.requiredReputationTitle.getRoute() != ReputationRoute.NONE) {
            return currentTitle.getRoute() == this.requiredReputationTitle.getRoute() && currentTitle.getTier() >= this.requiredReputationTitle.getTier();
        }
        return currentTitle.isAtLeast(this.requiredReputationTitle);
    }

    public boolean meetsReligionRequirement(@Nullable String currentReligionId) {
        if (!this.hasReligionRequirement()) {
            return true;
        }
        return this.requiredReligionId.equals(currentReligionId);
    }

    public boolean meetsHouseRequirement(@Nullable String currentHouseId) {
        if (!this.hasHouseRequirement()) {
            return true;
        }
        return this.requiredHouseId.equals(currentHouseId);
    }

    public boolean canBeHiredByKingdom(@Nullable String currentKingdomId) {
        if (!this.hasDisallowedHireKingdoms()) {
            return true;
        }
        if (currentKingdomId == null || currentKingdomId.isBlank()) {
            return true;
        }
        return !this.disallowedHireKingdomIds.contains(currentKingdomId);
    }

    public String reputationRequirementLabel() {
        if (this.requiredReputationTitle == null) {
            return "";
        }
        return "Requires " + this.requiredReputationTitle.getDisplayName() + "+ Title";
    }

    public String religionRequirementLabel() {
        if (!this.hasReligionRequirement()) {
            return "";
        }
        Religion religion = Religion.byId(this.requiredReligionId);
        String displayName = religion != null ? religion.getDisplayName() : this.requiredReligionId;
        return "Requires " + displayName;
    }

    public String houseRequirementLabel() {
        if (!this.hasHouseRequirement()) {
            return "";
        }
        CharacterSelectionOption option = GotCharacterOptions.get(this.requiredHouseId);
        String displayName = option != null && !option.houseDisplayName().isBlank() ? option.houseDisplayName() : this.requiredHouseId;
        return "Requires " + displayName;
    }

    public String disallowedHireKingdomsLabel() {
        if (!this.hasDisallowedHireKingdoms()) {
            return "";
        }
        String names = this.disallowedHireKingdomIds.stream().map(this::resolveKingdomName).reduce((left, right) -> left + ", " + right).orElse("");
        return "Cannot be " + names;
    }

    private String resolveKingdomName(String kingdomId) {
        CharacterSelectionOption option;
        class_2960 identifier = class_2960.method_12829((String)kingdomId);
        CharacterSelectionOption characterSelectionOption = option = identifier == null ? null : GotCharacterOptions.getByKingdomId(identifier);
        if (option != null && !option.displayName().isBlank()) {
            return option.displayName();
        }
        Kingdom kingdom = identifier == null ? null : GotKingdoms.get(identifier);
        return kingdom != null && !kingdom.getName().isBlank() ? kingdom.getName() : kingdomId;
    }
}

