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
import trep.got.relations.KingdomRelationsService;
import trep.got.relations.KingdomRelationsWorldState;

public final class PlayerKingdomRelationsProfile {
    private final Map<String, Integer> relationsByKingdomId = new LinkedHashMap<String, Integer>();

    public int getRelation(String kingdomId) {
        return this.relationsByKingdomId.getOrDefault(kingdomId, 0);
    }

    public void setRelation(String kingdomId, int relation) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return;
        }
        this.relationsByKingdomId.put(kingdomId, KingdomRelationsService.clamp(relation));
    }

    public int addRelation(String kingdomId, int delta) {
        int updated = this.getRelation(kingdomId) + delta;
        this.setRelation(kingdomId, updated);
        return this.getRelation(kingdomId);
    }

    public boolean isEmpty() {
        return this.relationsByKingdomId.isEmpty();
    }

    public Map<String, Integer> asMap() {
        return Map.copyOf(this.relationsByKingdomId);
    }

    public void initializeFromKingdom(String kingdomId, KingdomRelationsWorldState state) {
        this.relationsByKingdomId.clear();
        for (String targetKingdomId : state.getKingdomIds()) {
            int relation = targetKingdomId.equals(kingdomId) ? 100 : state.getRelation(kingdomId, targetKingdomId);
            this.relationsByKingdomId.put(targetKingdomId, relation);
        }
    }

    public void copyFrom(PlayerKingdomRelationsProfile other) {
        this.relationsByKingdomId.clear();
        this.relationsByKingdomId.putAll(other.relationsByKingdomId);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        class_2487 relations = new class_2487();
        for (Map.Entry<String, Integer> entry : this.relationsByKingdomId.entrySet()) {
            relations.method_10569(entry.getKey(), entry.getValue().intValue());
        }
        nbt.method_10566("kingdom_relations", (class_2520)relations);
        return nbt;
    }

    public static PlayerKingdomRelationsProfile fromNbt(class_2487 nbt) {
        PlayerKingdomRelationsProfile profile = new PlayerKingdomRelationsProfile();
        class_2487 relations = nbt.method_10562("kingdom_relations");
        for (String kingdomId : relations.method_10541()) {
            profile.setRelation(kingdomId, relations.method_10550(kingdomId));
        }
        return profile;
    }
}

