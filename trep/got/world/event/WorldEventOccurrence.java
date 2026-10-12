/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 */
package trep.got.world.event;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;

public record WorldEventOccurrence(UUID playerUuid, String triggerId, String summary, long worldTick, Map<String, String> details) {
    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_25927("player_uuid", this.playerUuid);
        nbt.method_10582("trigger_id", this.triggerId);
        nbt.method_10582("summary", this.summary);
        nbt.method_10544("world_tick", this.worldTick);
        class_2499 detailList = new class_2499();
        for (Map.Entry<String, String> entry : this.details.entrySet()) {
            class_2487 detail = new class_2487();
            detail.method_10582("key", entry.getKey());
            detail.method_10582("value", entry.getValue());
            detailList.add((Object)detail);
        }
        nbt.method_10566("details", (class_2520)detailList);
        return nbt;
    }

    public static WorldEventOccurrence fromNbt(class_2487 nbt) {
        LinkedHashMap<String, String> details = new LinkedHashMap<String, String>();
        class_2499 detailList = nbt.method_10554("details", 10);
        for (int index = 0; index < detailList.size(); ++index) {
            class_2487 detail = detailList.method_10602(index);
            String key = detail.method_10558("key");
            if (key.isBlank()) continue;
            details.put(key, detail.method_10558("value"));
        }
        return new WorldEventOccurrence(nbt.method_25926("player_uuid"), nbt.method_10558("trigger_id"), nbt.method_10558("summary"), nbt.method_10537("world_tick"), Map.copyOf(details));
    }
}

