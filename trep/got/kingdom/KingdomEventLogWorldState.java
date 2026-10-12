/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.kingdom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;

public final class KingdomEventLogWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.kingdom_event_logs";
    private static final class_18.class_8645<KingdomEventLogWorldState> TYPE = new class_18.class_8645(KingdomEventLogWorldState::new, KingdomEventLogWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, List<LogEntry>> logsByKingdom = new LinkedHashMap<String, List<LogEntry>>();

    public static KingdomEventLogWorldState get(class_3218 world) {
        return (KingdomEventLogWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
    }

    public List<LogEntry> getLogEntries(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return List.of();
        }
        return List.copyOf(this.logsByKingdom.getOrDefault(kingdomId, List.of()));
    }

    public Map<String, List<LogEntry>> asMap() {
        LinkedHashMap copy = new LinkedHashMap();
        for (Map.Entry<String, List<LogEntry>> entry : this.logsByKingdom.entrySet()) {
            copy.put(entry.getKey(), List.copyOf((Collection)entry.getValue()));
        }
        return Map.copyOf(copy);
    }

    public void append(String kingdomId, String line) {
        this.append(kingdomId, line, "");
    }

    public void append(String kingdomId, String line, String iconTextureId) {
        if (kingdomId == null || kingdomId.isBlank() || line == null || line.isBlank()) {
            return;
        }
        this.logsByKingdom.computeIfAbsent(kingdomId, ignored -> new ArrayList()).add(new LogEntry(line, iconTextureId));
        this.method_80();
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2487 logs = new class_2487();
        for (Map.Entry<String, List<LogEntry>> entry : this.logsByKingdom.entrySet()) {
            class_2499 lines = new class_2499();
            for (LogEntry line : entry.getValue()) {
                lines.add((Object)line.toNbt());
            }
            logs.method_10566(entry.getKey(), (class_2520)lines);
        }
        nbt.method_10566("logs_by_kingdom", (class_2520)logs);
        return nbt;
    }

    private static KingdomEventLogWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        KingdomEventLogWorldState state = new KingdomEventLogWorldState();
        class_2487 logs = nbt.method_10562("logs_by_kingdom");
        for (String kingdomId : logs.method_10541()) {
            Object line;
            class_2499 lines = logs.method_10554(kingdomId, 10);
            ArrayList<Object> entries = new ArrayList<Object>(lines.size());
            for (int index = 0; index < lines.size(); ++index) {
                class_2487 compound;
                class_2520 class_25202 = lines.method_10534(index);
                if (!(class_25202 instanceof class_2487) || ((LogEntry)(line = LogEntry.fromNbt(compound = (class_2487)class_25202))).line().isBlank()) continue;
                entries.add(line);
            }
            if (entries.isEmpty()) {
                class_2499 legacyLines = logs.method_10554(kingdomId, 8);
                for (int index = 0; index < legacyLines.size(); ++index) {
                    line = legacyLines.method_10608(index);
                    if (((String)line).isBlank()) continue;
                    entries.add(new LogEntry((String)line, ""));
                }
            }
            if (entries.isEmpty()) continue;
            state.logsByKingdom.put(kingdomId, entries);
        }
        return state;
    }

    public record LogEntry(String line, String iconTextureId) {
        public LogEntry {
            line = line == null ? "" : line;
            iconTextureId = iconTextureId == null ? "" : iconTextureId;
        }

        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("line", this.line);
            nbt.method_10582("icon_texture_id", this.iconTextureId);
            return nbt;
        }

        public static LogEntry fromNbt(class_2487 nbt) {
            return new LogEntry(nbt.method_10558("line"), nbt.method_10558("icon_texture_id"));
        }
    }
}

