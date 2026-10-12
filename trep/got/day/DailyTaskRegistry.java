/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_5321
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.day;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_5321;
import net.minecraft.server.MinecraftServer;
import trep.got.GotMod;
import trep.got.day.DailyTaskContext;
import trep.got.day.DailyTaskHandler;
import trep.got.registry.GotGameRules;

public final class DailyTaskRegistry {
    private static final int MAX_TASKS_PER_SERVER_TICK = 1;
    private static final int MAX_DAYS_QUEUED_AT_ONCE = 64;
    private static final Map<class_2960, DailyTaskHandler> TASKS = new LinkedHashMap<class_2960, DailyTaskHandler>();
    private static final Map<class_5321<class_1937>, ObservedWorldDay> OBSERVED_DAYS = new LinkedHashMap<class_5321<class_1937>, ObservedWorldDay>();
    private static final Map<class_5321<class_1937>, Deque<QueuedDailyTask>> QUEUES = new LinkedHashMap<class_5321<class_1937>, Deque<QueuedDailyTask>>();
    private static final Map<class_5321<class_1937>, Long> MANUAL_DAYS = new LinkedHashMap<class_5321<class_1937>, Long>();

    private DailyTaskRegistry() {
    }

    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(DailyTaskRegistry::tickServer);
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            OBSERVED_DAYS.clear();
            QUEUES.clear();
            MANUAL_DAYS.clear();
        });
    }

    public static void register(class_2960 id, DailyTaskHandler handler) {
        if (id == null || handler == null) {
            return;
        }
        if (TASKS.putIfAbsent(id, handler) != null) {
            throw new IllegalStateException("Duplicate daily task id " + String.valueOf(id));
        }
    }

    public static void queueManualDay(class_3218 world) {
        if (world == null || !GotGameRules.affectedByTimeSkip(world)) {
            return;
        }
        class_5321 key = world.method_27983();
        MANUAL_DAYS.put((class_5321<class_1937>)key, MANUAL_DAYS.getOrDefault(key, 0L) + 1L);
    }

    private static void tickServer(MinecraftServer server) {
        for (class_3218 world : server.method_3738()) {
            DailyTaskRegistry.observeWorld(world);
        }
        DailyTaskRegistry.drainQueues(server);
    }

    private static void observeWorld(class_3218 world) {
        if (world == null) {
            return;
        }
        class_5321 key = world.method_27983();
        boolean affectedByTimeSkip = GotGameRules.affectedByTimeSkip(world);
        long currentDay = GotGameRules.trackedDay(world);
        long currentTimeOfDay = GotGameRules.normalizeTimeOfDay(world.method_8532());
        ObservedWorldDay observed = OBSERVED_DAYS.get(key);
        if (observed == null || observed.affectedByTimeSkip != affectedByTimeSkip) {
            long manualDays;
            OBSERVED_DAYS.put((class_5321<class_1937>)key, new ObservedWorldDay(currentDay, currentTimeOfDay, affectedByTimeSkip));
            Long manualDaysValue = MANUAL_DAYS.remove(key);
            long l = manualDays = manualDaysValue == null ? 0L : manualDaysValue;
            if (manualDays > 0L) {
                DailyTaskRegistry.queueDays(world, currentDay, manualDays, true);
            }
            return;
        }
        long detectedDays = 0L;
        if (currentDay > observed.day) {
            detectedDays = currentDay - observed.day;
        } else if (affectedByTimeSkip && currentTimeOfDay < observed.normalizedTimeOfDay) {
            detectedDays = 1L;
            currentDay = Math.max(currentDay, observed.day + 1L);
        }
        Long manualDaysValue = MANUAL_DAYS.remove(key);
        long manualDays = manualDaysValue == null ? 0L : manualDaysValue;
        long daysToQueue = Math.max(detectedDays, manualDays);
        if (daysToQueue > 0L) {
            DailyTaskRegistry.queueDays(world, Math.max(currentDay, observed.day + daysToQueue), daysToQueue, manualDays > 0L);
        }
        observed.update(Math.max(currentDay, observed.day + daysToQueue), currentTimeOfDay);
    }

    private static void queueDays(class_3218 world, long latestDay, long daysToQueue, boolean manualTimeSet) {
        if (TASKS.isEmpty()) {
            return;
        }
        long cappedDays = Math.min(Math.max(0L, daysToQueue), 64L);
        if (daysToQueue > cappedDays) {
            GotMod.LOGGER.warn("Daily task queue capped {} skipped days to {} for {}", new Object[]{daysToQueue, cappedDays, world.method_27983().method_29177()});
        }
        Deque queue = QUEUES.computeIfAbsent((class_5321<class_1937>)world.method_27983(), ignored -> new ArrayDeque());
        long firstDay = Math.max(0L, latestDay - cappedDays + 1L);
        List<Map.Entry<class_2960, DailyTaskHandler>> tasks = List.copyOf(TASKS.entrySet());
        for (long day = firstDay; day <= latestDay; ++day) {
            DailyTaskContext context = new DailyTaskContext(day, world.method_8510(), manualTimeSet);
            for (Map.Entry<class_2960, DailyTaskHandler> task : tasks) {
                queue.add(new QueuedDailyTask(task.getKey(), task.getValue(), context));
            }
        }
    }

    private static void drainQueues(MinecraftServer server) {
        if (QUEUES.isEmpty()) {
            return;
        }
        int processed = 0;
        Iterator<Map.Entry<class_5321<class_1937>, Deque<QueuedDailyTask>>> iterator = QUEUES.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<class_5321<class_1937>, Deque<QueuedDailyTask>> entry = iterator.next();
            class_3218 world = server.method_3847(entry.getKey());
            Deque<QueuedDailyTask> queue = entry.getValue();
            while (world != null && processed < 1 && !queue.isEmpty()) {
                QueuedDailyTask task = queue.poll();
                if (task == null) continue;
                DailyTaskRegistry.runTask(world, task);
                ++processed;
            }
            if (queue.isEmpty()) {
                iterator.remove();
            }
            if (processed < true) continue;
            return;
        }
    }

    private static void runTask(class_3218 world, QueuedDailyTask task) {
        try {
            task.handler.run(world, task.context);
        }
        catch (RuntimeException exception) {
            GotMod.LOGGER.error("Daily task {} failed for {} day {}", new Object[]{task.id, world.method_27983().method_29177(), task.context.day(), exception});
        }
    }

    private static final class ObservedWorldDay {
        private long day;
        private long normalizedTimeOfDay;
        private final boolean affectedByTimeSkip;

        private ObservedWorldDay(long day, long normalizedTimeOfDay, boolean affectedByTimeSkip) {
            this.day = day;
            this.normalizedTimeOfDay = normalizedTimeOfDay;
            this.affectedByTimeSkip = affectedByTimeSkip;
        }

        private void update(long day, long normalizedTimeOfDay) {
            this.day = day;
            this.normalizedTimeOfDay = normalizedTimeOfDay;
        }
    }

    private record QueuedDailyTask(class_2960 id, DailyTaskHandler handler, DailyTaskContext context) {
    }
}

