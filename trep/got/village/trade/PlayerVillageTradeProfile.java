/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 */
package trep.got.village.trade;

import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;

public final class PlayerVillageTradeProfile {
    private static final int LEVEL_TWO_TRADES_REQUIRED = 4;
    private static final int LEVEL_THREE_TRADES_REQUIRED = 10;
    private final Map<String, VillageTradeProgress> progressByVillage = new LinkedHashMap<String, VillageTradeProgress>();

    public VillageTradeProgress getProgress(String villageId) {
        if (villageId == null || villageId.isBlank()) {
            return VillageTradeProgress.EMPTY;
        }
        return this.progressByVillage.getOrDefault(villageId, VillageTradeProgress.EMPTY);
    }

    public VillageTradeProgress recordTrade(String villageId) {
        if (villageId == null || villageId.isBlank()) {
            return VillageTradeProgress.EMPTY;
        }
        VillageTradeProgress updated = this.getProgress(villageId).withCompletedTrades(this.getProgress(villageId).completedTrades() + 1);
        this.progressByVillage.put(villageId, updated);
        return updated;
    }

    public int getLevel(String villageId) {
        int trades = this.getProgress(villageId).completedTrades();
        if (trades >= 10) {
            return 3;
        }
        if (trades >= 4) {
            return 2;
        }
        return 1;
    }

    public int getLevelProgress(String villageId) {
        int trades = this.getProgress(villageId).completedTrades();
        int level = this.getLevel(villageId);
        return switch (level) {
            case 1 -> trades;
            case 2 -> trades - 4;
            default -> trades - 10;
        };
    }

    public int getTradesRequiredForNextLevel(String villageId) {
        int level = this.getLevel(villageId);
        return switch (level) {
            case 1 -> 4;
            case 2 -> 6;
            default -> 0;
        };
    }

    public class_2487 toNbt() {
        class_2487 root = new class_2487();
        class_2499 villages = new class_2499();
        for (Map.Entry<String, VillageTradeProgress> entry : this.progressByVillage.entrySet()) {
            if (entry.getKey().isBlank()) continue;
            class_2487 village = new class_2487();
            village.method_10582("village_id", entry.getKey());
            village.method_10569("completed_trades", entry.getValue().completedTrades());
            villages.add((Object)village);
        }
        root.method_10566("villages", (class_2520)villages);
        return root;
    }

    public static PlayerVillageTradeProfile fromNbt(class_2487 root) {
        PlayerVillageTradeProfile profile = new PlayerVillageTradeProfile();
        class_2499 villages = root.method_10554("villages", 10);
        for (int index = 0; index < villages.size(); ++index) {
            class_2487 village = villages.method_10602(index);
            String villageId = village.method_10558("village_id");
            if (villageId.isBlank()) continue;
            profile.progressByVillage.put(villageId, new VillageTradeProgress(Math.max(0, village.method_10550("completed_trades"))));
        }
        return profile;
    }

    public record VillageTradeProgress(int completedTrades) {
        private static final VillageTradeProgress EMPTY = new VillageTradeProgress(0);

        public VillageTradeProgress withCompletedTrades(int completedTrades) {
            return new VillageTradeProgress(Math.max(0, completedTrades));
        }
    }
}

