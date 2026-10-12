/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3218
 */
package trep.got.village.economy;

import net.minecraft.class_3218;
import trep.got.registry.GotGameRules;
import trep.got.registry.GotVillageResources;
import trep.got.registry.GotVillages;
import trep.got.village.VillageInstance;
import trep.got.village.VillageWorldState;

public final class VillageStockService {
    public static final int DAILY_STOCK_GROWTH = 10;

    private VillageStockService() {
    }

    public static void initializeVillage(VillageInstance village, long worldTime) {
        if (village == null) {
            return;
        }
        village.setStockCapacity(100);
        village.setStockAmount(Math.min(50, village.getStockCapacity()));
        village.setLastStockGrowthDay(VillageStockService.currentDay(worldTime, worldTime));
        village.setLastObservedTimeOfDay(VillageStockService.normalizeTimeOfDay(worldTime));
    }

    public static void synchronizeAll(class_3218 world) {
        if (world == null) {
            return;
        }
        VillageWorldState state = VillageWorldState.get(world);
        boolean changed = false;
        for (VillageInstance village : state.getAll()) {
            GotVillages.ensureVillageEconomyData(world, village);
            changed |= VillageStockService.synchronizeVillage(village, world);
        }
        if (changed) {
            state.method_80();
        }
    }

    public static int getAvailableStock(class_3218 world, VillageInstance village) {
        if (village == null) {
            return 0;
        }
        if (world != null && VillageStockService.synchronizeVillage(village, world)) {
            VillageWorldState.get(world).method_80();
        }
        return village.getStockAmount();
    }

    public static boolean consumeStock(class_3218 world, VillageInstance village, int amount) {
        if (world == null || village == null || amount <= 0) {
            return false;
        }
        boolean changedFromGrowth = VillageStockService.synchronizeVillage(village, world);
        if (village.getStockAmount() < amount) {
            if (changedFromGrowth) {
                VillageWorldState.get(world).method_80();
            }
            return false;
        }
        village.setStockAmount(village.getStockAmount() - amount);
        VillageWorldState.get(world).method_80();
        return true;
    }

    public static boolean synchronizeVillage(VillageInstance village, class_3218 world) {
        if (village == null || world == null) {
            return false;
        }
        GotVillages.ensureVillageEconomyData(world, village);
        return VillageStockService.synchronizeVillage(village, world, world.method_8510(), world.method_8532());
    }

    public static boolean synchronizeVillage(VillageInstance village, long worldTime) {
        return VillageStockService.synchronizeVillage(village, null, worldTime, worldTime);
    }

    public static boolean synchronizeVillage(VillageInstance village, long worldTime, long timeOfDay) {
        return VillageStockService.synchronizeVillage(village, null, worldTime, timeOfDay);
    }

    public static boolean synchronizeVillage(VillageInstance village, class_3218 world, long worldTime, long timeOfDay) {
        long day;
        if (village == null) {
            return false;
        }
        if (world != null) {
            GotVillages.ensureVillageEconomyData(world, village);
        }
        boolean affectedByTimeSkip = world == null || GotGameRules.affectedByTimeSkip(world);
        long normalizedTimeOfDay = VillageStockService.normalizeTimeOfDay(timeOfDay);
        long l = day = affectedByTimeSkip ? VillageStockService.currentDay(worldTime, timeOfDay) : VillageStockService.currentDay(worldTime);
        if (village.getLastStockGrowthDay() < 0L) {
            village.setLastStockGrowthDay(day);
            village.setLastObservedTimeOfDay(normalizedTimeOfDay);
            if (village.getStockCapacity() <= 0) {
                village.setStockCapacity(100);
            }
            if (village.getStockAmount() <= 0) {
                village.setStockAmount(Math.min(50, village.getStockCapacity()));
            }
            return true;
        }
        long effectiveDay = day;
        if (affectedByTimeSkip && village.getLastObservedTimeOfDay() >= 0L && normalizedTimeOfDay < village.getLastObservedTimeOfDay()) {
            effectiveDay = Math.max(effectiveDay, village.getLastStockGrowthDay() + 1L);
        }
        if (effectiveDay <= village.getLastStockGrowthDay()) {
            village.setLastObservedTimeOfDay(normalizedTimeOfDay);
            return false;
        }
        if (village.getStockAmount() >= village.getStockCapacity()) {
            village.setLastStockGrowthDay(effectiveDay);
            village.setLastObservedTimeOfDay(normalizedTimeOfDay);
            return false;
        }
        long firstGrowthDay = village.getLastStockGrowthDay() + 1L;
        if (world == null || village.getBoundCastleId().isBlank()) {
            int producedPerDay = VillageStockService.dailyProductionFor(village, effectiveDay);
            if (producedPerDay > 0) {
                long producedDays = effectiveDay - village.getLastStockGrowthDay();
                long blockedDay = village.getBlockedProductionDay();
                if (blockedDay >= firstGrowthDay && blockedDay <= effectiveDay) {
                    --producedDays;
                }
                if (producedDays > 0L) {
                    long totalProduced = producedDays * (long)producedPerDay;
                    village.setStockAmount((int)Math.min((long)village.getStockCapacity(), (long)village.getStockAmount() + totalProduced));
                }
            }
            village.setLastStockGrowthDay(effectiveDay);
            village.setLastObservedTimeOfDay(normalizedTimeOfDay);
            return true;
        }
        boolean changed = false;
        for (long growthDay = firstGrowthDay; growthDay <= effectiveDay; ++growthDay) {
            int produced = VillageStockService.dailyProductionFor(village, growthDay);
            if (produced <= 0 || village.getStockAmount() >= village.getStockCapacity()) continue;
            village.setStockAmount(Math.min(village.getStockCapacity(), village.getStockAmount() + produced));
            changed = true;
            if (world == null) continue;
            changed |= GotVillages.collectCastleTaxFromVillage(world, village);
        }
        village.setLastStockGrowthDay(effectiveDay);
        village.setLastObservedTimeOfDay(normalizedTimeOfDay);
        return true;
    }

    public static int dailyProductionFor(VillageInstance village, long worldDay) {
        if (village != null && village.getBlockedProductionDay() == worldDay) {
            return 0;
        }
        if (village != null && village.getResourceId() != null && (village.getResourceId().equals((Object)GotVillageResources.HORSES.getId()) || village.getResourceId().equals((Object)GotVillageResources.COPPER_ORE.getId()) || village.getResourceId().equals((Object)GotVillageResources.IRON_ORE.getId()) || village.getResourceId().equals((Object)GotVillageResources.GOLD_ORE.getId()))) {
            return 5;
        }
        return 10;
    }

    public static void blockNextDayProduction(VillageInstance village, long worldTime) {
        if (village == null) {
            return;
        }
        village.setBlockedProductionDay(VillageStockService.currentDay(worldTime, worldTime) + 1L);
    }

    private static long currentDay(long worldTime) {
        return Math.max(0L, worldTime / 24000L);
    }

    private static long currentDay(long worldTime, long timeOfDay) {
        return Math.max(VillageStockService.currentDay(worldTime), VillageStockService.currentDay(timeOfDay));
    }

    private static long normalizeTimeOfDay(long timeOfDay) {
        long normalized = timeOfDay % 24000L;
        return normalized < 0L ? normalized + 24000L : normalized;
    }
}

