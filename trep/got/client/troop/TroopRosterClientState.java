/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2960
 */
package trep.got.client.troop;

import java.util.LinkedHashMap;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import trep.got.network.TroopRosterPayload;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.DynamicTroopRegistry;
import trep.got.troop.DynamicTroopUnitType;

@Environment(value=EnvType.CLIENT)
public final class TroopRosterClientState {
    private static final LinkedHashMap<String, Integer> troopCounts = new LinkedHashMap();
    private static final LinkedHashMap<String, Integer> troopUpgradeableCounts = new LinkedHashMap();
    private static int totalCount;
    private static int capacity;
    private static int upgradeableCount;

    private TroopRosterClientState() {
    }

    public static void clear() {
        troopCounts.clear();
        troopUpgradeableCounts.clear();
        totalCount = 0;
        capacity = 0;
        upgradeableCount = 0;
    }

    public static void apply(TroopRosterPayload payload) {
        troopCounts.clear();
        troopUpgradeableCounts.clear();
        for (int index = 0; index < payload.troopIds().length; ++index) {
            troopCounts.put(payload.troopIds()[index], index < payload.troopCounts().length ? payload.troopCounts()[index] : 0);
            troopUpgradeableCounts.put(payload.troopIds()[index], index < payload.troopUpgradeableCounts().length ? payload.troopUpgradeableCounts()[index] : 0);
        }
        totalCount = payload.totalCount();
        capacity = payload.capacity();
        upgradeableCount = payload.upgradeableCount();
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public static int getCapacity() {
        return capacity;
    }

    public static int getUpgradeableCount() {
        return upgradeableCount;
    }

    public static boolean hasCapacityForOneMore() {
        return totalCount < capacity;
    }

    public static int getCount(class_2960 troopId) {
        return troopId == null ? 0 : troopCounts.getOrDefault(troopId.toString(), 0);
    }

    public static int getUpgradeableCount(class_2960 troopId) {
        if (troopId == null) {
            return 0;
        }
        return troopUpgradeableCounts.getOrDefault(troopId.toString(), 0);
    }

    public static int getCountByUnitType(DynamicTroopUnitType unitType) {
        int total = 0;
        for (Map.Entry<String, Integer> entry : troopCounts.entrySet()) {
            DynamicTroopDefinition definition = DynamicTroopRegistry.get(class_2960.method_12829((String)entry.getKey()));
            if (definition == null || definition.unitType() != unitType) continue;
            total += entry.getValue().intValue();
        }
        return total;
    }

    public static Map<String, Integer> troopCounts() {
        return Map.copyOf(troopCounts);
    }
}

