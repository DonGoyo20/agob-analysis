/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package trep.got.relations;

import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.class_2487;
import net.minecraft.class_2520;

public final class PlayerNpcRelationsProfile {
    private final LinkedHashMap<String, Integer> relationsByNpcId = new LinkedHashMap();

    public int getRelation(String npcId) {
        if (npcId == null || npcId.isBlank()) {
            return 0;
        }
        return this.relationsByNpcId.getOrDefault(npcId, 0);
    }

    public void setRelation(String npcId, int relation) {
        if (npcId == null || npcId.isBlank()) {
            return;
        }
        this.relationsByNpcId.put(npcId, PlayerNpcRelationsProfile.clamp(relation));
    }

    public int addRelation(String npcId, int delta) {
        int updated = PlayerNpcRelationsProfile.clamp(this.getRelation(npcId) + delta);
        this.setRelation(npcId, updated);
        return updated;
    }

    public void copyFrom(PlayerNpcRelationsProfile other) {
        this.relationsByNpcId.clear();
        if (other != null) {
            this.relationsByNpcId.putAll(other.relationsByNpcId);
        }
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        class_2487 relations = new class_2487();
        for (Map.Entry<String, Integer> entry : this.relationsByNpcId.entrySet()) {
            relations.method_10569(entry.getKey(), entry.getValue().intValue());
        }
        nbt.method_10566("npc_relations", (class_2520)relations);
        return nbt;
    }

    public static PlayerNpcRelationsProfile fromNbt(class_2487 nbt) {
        PlayerNpcRelationsProfile profile = new PlayerNpcRelationsProfile();
        class_2487 relations = nbt.method_10562("npc_relations");
        for (String npcId : relations.method_10541()) {
            profile.setRelation(npcId, relations.method_10550(npcId));
        }
        return profile;
    }

    private static int clamp(int value) {
        return Math.max(-100, Math.min(100, value));
    }
}

