/*
 * Decompiled with CFR 0.152.
 */
package trep.got.quest;

import java.util.LinkedHashMap;
import java.util.Map;

public final class QuestDebugLogBuffer {
    private static final long WINDOW_MS = 10000L;
    private static final Map<String, Summary> SUMMARIES = new LinkedHashMap<String, Summary>();
    private static long nextFlushAtMs = System.currentTimeMillis() + 10000L;

    private QuestDebugLogBuffer() {
    }

    public static synchronized void record(String channel, String key, String detail) {
        Summary summary = SUMMARIES.computeIfAbsent(channel + "|" + key, ignored -> new Summary(channel, key));
        ++summary.count;
        if (summary.sample == null && detail != null && !detail.isBlank()) {
            summary.sample = detail;
        }
        QuestDebugLogBuffer.flushIfNeeded();
    }

    public static synchronized void flushNow() {
        QuestDebugLogBuffer.flush(true);
    }

    private static void flushIfNeeded() {
        if (System.currentTimeMillis() >= nextFlushAtMs) {
            QuestDebugLogBuffer.flush(false);
        }
    }

    private static void flush(boolean force) {
        long now = System.currentTimeMillis();
        if (!force && now < nextFlushAtMs) {
            return;
        }
        if (!SUMMARIES.isEmpty()) {
            SUMMARIES.clear();
        }
        nextFlushAtMs = now + 10000L;
    }

    private static final class Summary {
        private final String channel;
        private final String key;
        private int count;
        private String sample;

        private Summary(String channel, String key) {
            this.channel = channel;
            this.key = key;
        }
    }
}

