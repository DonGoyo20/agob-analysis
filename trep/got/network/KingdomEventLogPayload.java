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

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record KingdomEventLogPayload(List<KingdomEntry> kingdoms) implements class_8710
{
    public static final class_8710.class_9154<KingdomEventLogPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"kingdom_event_logs"));
    public static final class_9139<class_9129, KingdomEventLogPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.kingdoms.size());
        for (KingdomEntry kingdom : value.kingdoms) {
            buf.method_10814(kingdom.kingdomId());
            buf.method_10804(kingdom.entries().size());
            for (LogEntry line : kingdom.entries()) {
                buf.method_10814(line.line());
                buf.method_10814(line.iconTextureId());
            }
        }
    }, buf -> {
        int kingdomCount = buf.method_10816();
        ArrayList<KingdomEntry> kingdoms = new ArrayList<KingdomEntry>(kingdomCount);
        for (int kingdomIndex = 0; kingdomIndex < kingdomCount; ++kingdomIndex) {
            String kingdomId = buf.method_19772();
            int lineCount = buf.method_10816();
            ArrayList<LogEntry> lines = new ArrayList<LogEntry>(lineCount);
            for (int lineIndex = 0; lineIndex < lineCount; ++lineIndex) {
                lines.add(new LogEntry(buf.method_19772(), buf.method_19772()));
            }
            kingdoms.add(new KingdomEntry(kingdomId, List.copyOf(lines)));
        }
        return new KingdomEventLogPayload(List.copyOf(kingdoms));
    });

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    public record LogEntry(String line, String iconTextureId) {
    }

    public record KingdomEntry(String kingdomId, List<LogEntry> entries) {
    }
}

