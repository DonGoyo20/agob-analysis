/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import java.util.Arrays;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record TroopRosterPayload(String[] troopIds, int[] troopCounts, int[] troopUpgradeableCounts, int totalCount, int capacity, int upgradeableCount) implements class_8710
{
    public static final class_8710.class_9154<TroopRosterPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"troop_roster"));
    public static final class_9139<class_9129, TroopRosterPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.troopIds.length);
        for (int index = 0; index < value.troopIds.length; ++index) {
            buf.method_10814(value.troopIds[index]);
            buf.method_10804(index < value.troopCounts.length ? value.troopCounts[index] : 0);
            buf.method_10804(index < value.troopUpgradeableCounts.length ? value.troopUpgradeableCounts[index] : 0);
        }
        buf.method_10804(value.totalCount);
        buf.method_10804(value.capacity);
        buf.method_10804(value.upgradeableCount);
    }, buf -> {
        int size = buf.method_10816();
        String[] troopIds = new String[size];
        int[] troopCounts = new int[size];
        int[] troopUpgradeableCounts = new int[size];
        for (int index = 0; index < size; ++index) {
            troopIds[index] = buf.method_19772();
            troopCounts[index] = buf.method_10816();
            troopUpgradeableCounts[index] = buf.method_10816();
        }
        return new TroopRosterPayload(troopIds, troopCounts, troopUpgradeableCounts, buf.method_10816(), buf.method_10816(), buf.method_10816());
    });

    public TroopRosterPayload {
        troopIds = Arrays.copyOf(troopIds, troopIds.length);
        troopCounts = Arrays.copyOf(troopCounts, troopCounts.length);
        troopUpgradeableCounts = Arrays.copyOf(troopUpgradeableCounts, troopUpgradeableCounts.length);
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

