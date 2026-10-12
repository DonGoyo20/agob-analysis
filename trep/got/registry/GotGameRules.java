/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory
 *  net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry
 *  net.minecraft.class_1928
 *  net.minecraft.class_1928$class_4310
 *  net.minecraft.class_1928$class_4313
 *  net.minecraft.class_1928$class_4314
 *  net.minecraft.class_1928$class_5198
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_5219
 *  net.minecraft.class_5268
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import java.util.List;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.class_1928;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5219;
import net.minecraft.class_5268;
import org.jetbrains.annotations.Nullable;
import trep.got.world.dimension.GotDimensionKeys;

public final class GotGameRules {
    private static final int CUSTOM_SLEEP_MIN_TIMER_TO_SKIP = 95;
    public static final class_1928.class_4313<class_1928.class_4310> AGOB_AFFECTED_BY_TIME_SKIP = GameRuleRegistry.register((String)"agobAffectedByTimeSkip", (class_1928.class_5198)class_1928.class_5198.field_24098, (class_1928.class_4314)GameRuleFactory.createBooleanRule((boolean)true));

    private GotGameRules() {
    }

    public static void initialize() {
        ServerTickEvents.END_WORLD_TICK.register(GotGameRules::handleCustomDimensionSleep);
    }

    public static boolean affectedByTimeSkip(@Nullable class_3218 world) {
        return world == null || world.method_8450().method_8355(AGOB_AFFECTED_BY_TIME_SKIP);
    }

    public static long regularDay(long worldTime) {
        return Math.max(0L, worldTime / 24000L);
    }

    public static long skipAwareDay(long worldTime, long timeOfDay) {
        return Math.max(GotGameRules.regularDay(worldTime), Math.max(0L, timeOfDay / 24000L));
    }

    public static long normalizeTimeOfDay(long timeOfDay) {
        long normalized = timeOfDay % 24000L;
        return normalized < 0L ? normalized + 24000L : normalized;
    }

    public static long trackedTime(long worldTime, long timeOfDay, boolean affectedByTimeSkip) {
        if (!affectedByTimeSkip) {
            return worldTime;
        }
        return GotGameRules.skipAwareDay(worldTime, timeOfDay) * 24000L + GotGameRules.normalizeTimeOfDay(timeOfDay);
    }

    public static long trackedDay(@Nullable class_3218 world) {
        if (world == null) {
            return 0L;
        }
        return GotGameRules.affectedByTimeSkip(world) ? GotGameRules.skipAwareDay(world.method_8510(), world.method_8532()) : GotGameRules.regularDay(world.method_8510());
    }

    public static long trackedTime(@Nullable class_3218 world) {
        if (world == null) {
            return 0L;
        }
        return GotGameRules.trackedTime(world.method_8510(), world.method_8532(), GotGameRules.affectedByTimeSkip(world));
    }

    public static long trackedDayTime(long worldTime, long timeOfDay, boolean affectedByTimeSkip) {
        return (affectedByTimeSkip ? GotGameRules.skipAwareDay(worldTime, timeOfDay) : GotGameRules.regularDay(worldTime)) * 24000L;
    }

    public static long trackedDayTime(@Nullable class_3218 world) {
        if (world == null) {
            return 0L;
        }
        return GotGameRules.trackedDay(world) * 24000L;
    }

    private static void setSharedDayTime(class_3218 world, long dayTime) {
        class_5268 mainWorldProperties;
        class_5219 saveProperties;
        if (world == null) {
            return;
        }
        if (world.method_8503() != null && (saveProperties = world.method_8503().method_27728()) != null && (mainWorldProperties = saveProperties.method_27859()) != null) {
            mainWorldProperties.method_29035(dayTime);
            return;
        }
        world.method_29199(dayTime);
    }

    private static void handleCustomDimensionSleep(class_3218 world) {
        if (world == null) {
            return;
        }
        if (!GotDimensionKeys.ICEANDFIRE.equals((Object)world.method_27983())) {
            return;
        }
        if (!world.method_23886() && !world.method_8546()) {
            return;
        }
        List players = world.method_18456();
        int eligiblePlayers = 0;
        int sleepingPlayers = 0;
        int minimumSleepTimer = Integer.MAX_VALUE;
        for (class_3222 player : players) {
            if (player.method_7325()) continue;
            ++eligiblePlayers;
            if (!player.method_6113()) continue;
            ++sleepingPlayers;
            minimumSleepTimer = Math.min(minimumSleepTimer, player.method_7297());
        }
        if (eligiblePlayers <= 0 || sleepingPlayers <= 0) {
            return;
        }
        int sleepingPercentage = world.method_8450().method_8356(class_1928.field_28357);
        int requiredPlayers = Math.max(1, (int)Math.ceil((double)(eligiblePlayers * sleepingPercentage) / 100.0));
        if (sleepingPlayers < requiredPlayers) {
            return;
        }
        if (minimumSleepTimer < 95) {
            return;
        }
        long nextDayMorning = (world.method_8532() / 24000L + 1L) * 24000L + 1000L;
        GotGameRules.setSharedDayTime(world, nextDayMorning);
        if (world.method_8419() || world.method_8546()) {
            world.method_27910(0, 0, false, false);
        }
        for (class_3222 player : players) {
            if (!player.method_6113()) continue;
            player.method_7358(false, true);
        }
    }
}

