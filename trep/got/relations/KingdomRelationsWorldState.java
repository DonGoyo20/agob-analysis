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
package trep.got.relations;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;
import trep.got.relations.KingdomRelationPresetRegistry;
import trep.got.relations.KingdomRelationsService;

public final class KingdomRelationsWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.kingdom_relations";
    private static final class_18.class_8645<KingdomRelationsWorldState> TYPE = new class_18.class_8645(KingdomRelationsWorldState::new, KingdomRelationsWorldState::fromNbt, class_4284.field_45077);
    private final Map<String, Map<String, Integer>> relations = new LinkedHashMap<String, Map<String, Integer>>();
    private final Map<String, ActiveWar> activeWars = new LinkedHashMap<String, ActiveWar>();
    private final Map<String, PendingWarDeclaration> pendingWarDeclarations = new LinkedHashMap<String, PendingWarDeclaration>();
    private final Map<String, Map<String, Long>> declarationCooldownDays = new LinkedHashMap<String, Map<String, Long>>();
    private final Map<String, Long> diplomacyCooldownEndsAtTickByPair = new LinkedHashMap<String, Long>();
    private final Map<String, Long> nextDeclarationDayByKingdom = new LinkedHashMap<String, Long>();
    private boolean initialized;
    private long lastWarRefreshDay = -1L;
    private long lastObservedWarTimeOfDay = -1L;
    private long queuedManualWarDays;

    public static KingdomRelationsWorldState get(class_3218 world) {
        KingdomRelationsWorldState state = (KingdomRelationsWorldState)world.method_17983().method_17924(TYPE, STATE_ID);
        state.ensureInitialized();
        return state;
    }

    public Set<String> getKingdomIds() {
        return Set.copyOf(this.relations.keySet());
    }

    public int getRelation(String fromKingdomId, String toKingdomId) {
        if (fromKingdomId == null || toKingdomId == null) {
            return 0;
        }
        if (fromKingdomId.equals(toKingdomId)) {
            return 100;
        }
        return this.relations.getOrDefault(fromKingdomId, Map.of()).getOrDefault(toKingdomId, 0);
    }

    public void setRelation(String fromKingdomId, String toKingdomId, int relation) {
        if (fromKingdomId == null || fromKingdomId.isBlank() || toKingdomId == null || toKingdomId.isBlank()) {
            return;
        }
        this.relations.computeIfAbsent(fromKingdomId, ignored -> new LinkedHashMap()).put(toKingdomId, fromKingdomId.equals(toKingdomId) ? 100 : KingdomRelationsService.clamp(relation));
        this.method_80();
    }

    public Map<String, Map<String, Integer>> asMap() {
        LinkedHashMap<String, Map<String, Integer>> copy = new LinkedHashMap<String, Map<String, Integer>>();
        for (Map.Entry<String, Map<String, Integer>> entry : this.relations.entrySet()) {
            copy.put(entry.getKey(), Map.copyOf(entry.getValue()));
        }
        return Map.copyOf(copy);
    }

    public List<ActiveWar> getActiveWars() {
        return List.copyOf(this.activeWars.values());
    }

    public boolean isAtWar(String firstKingdomId, String secondKingdomId) {
        if (firstKingdomId == null || secondKingdomId == null || firstKingdomId.isBlank() || secondKingdomId.isBlank()) {
            return false;
        }
        if (firstKingdomId.equals(secondKingdomId)) {
            return false;
        }
        return this.activeWars.containsKey(KingdomRelationsWorldState.warKey(firstKingdomId, secondKingdomId));
    }

    public int activeWarCount(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return 0;
        }
        int count = 0;
        for (ActiveWar war : this.activeWars.values()) {
            if (!kingdomId.equals(war.attackerKingdomId()) && !kingdomId.equals(war.defenderKingdomId())) continue;
            ++count;
        }
        return count;
    }

    public List<PendingWarDeclaration> getPendingWarDeclarations() {
        return List.copyOf(this.pendingWarDeclarations.values());
    }

    public boolean hasPendingWarDeclaration(String attackerKingdomId, String defenderKingdomId) {
        if (attackerKingdomId == null || attackerKingdomId.isBlank() || defenderKingdomId == null || defenderKingdomId.isBlank()) {
            return false;
        }
        return this.pendingWarDeclarations.containsKey(KingdomRelationsWorldState.pendingWarKey(attackerKingdomId, defenderKingdomId));
    }

    public boolean hasPendingWarDeclarationForAttacker(String attackerKingdomId) {
        if (attackerKingdomId == null || attackerKingdomId.isBlank()) {
            return false;
        }
        for (PendingWarDeclaration declaration : this.pendingWarDeclarations.values()) {
            if (!attackerKingdomId.equals(declaration.attackerKingdomId())) continue;
            return true;
        }
        return false;
    }

    public void addPendingWarDeclaration(PendingWarDeclaration declaration) {
        if (declaration == null || declaration.attackerKingdomId().isBlank() || declaration.defenderKingdomId().isBlank()) {
            return;
        }
        this.pendingWarDeclarations.put(KingdomRelationsWorldState.pendingWarKey(declaration.attackerKingdomId(), declaration.defenderKingdomId()), declaration);
        this.method_80();
    }

    public PendingWarDeclaration removePendingWarDeclaration(String attackerKingdomId, String defenderKingdomId) {
        if (attackerKingdomId == null || attackerKingdomId.isBlank() || defenderKingdomId == null || defenderKingdomId.isBlank()) {
            return null;
        }
        PendingWarDeclaration removed = this.pendingWarDeclarations.remove(KingdomRelationsWorldState.pendingWarKey(attackerKingdomId, defenderKingdomId));
        if (removed != null) {
            this.method_80();
        }
        return removed;
    }

    public long getPairDeclarationReadyDay(String attackerKingdomId, String defenderKingdomId) {
        if (attackerKingdomId == null || defenderKingdomId == null || attackerKingdomId.isBlank() || defenderKingdomId.isBlank()) {
            return 0L;
        }
        return this.declarationCooldownDays.getOrDefault(attackerKingdomId, Map.of()).getOrDefault(defenderKingdomId, 0L);
    }

    public void setPairDeclarationReadyDay(String attackerKingdomId, String defenderKingdomId, long readyDay) {
        if (attackerKingdomId == null || defenderKingdomId == null || attackerKingdomId.isBlank() || defenderKingdomId.isBlank()) {
            return;
        }
        this.declarationCooldownDays.computeIfAbsent(attackerKingdomId, ignored -> new LinkedHashMap()).put(defenderKingdomId, Math.max(0L, readyDay));
        this.method_80();
    }

    public long getDiplomacyCooldownEndsAtTick(String firstKingdomId, String secondKingdomId) {
        if (firstKingdomId == null || secondKingdomId == null || firstKingdomId.isBlank() || secondKingdomId.isBlank()) {
            return 0L;
        }
        return this.diplomacyCooldownEndsAtTickByPair.getOrDefault(KingdomRelationsWorldState.warKey(firstKingdomId, secondKingdomId), 0L);
    }

    public void setDiplomacyCooldownEndsAtTick(String firstKingdomId, String secondKingdomId, long endsAtTick) {
        if (firstKingdomId == null || secondKingdomId == null || firstKingdomId.isBlank() || secondKingdomId.isBlank()) {
            return;
        }
        String key = KingdomRelationsWorldState.warKey(firstKingdomId, secondKingdomId);
        long clamped = Math.max(0L, endsAtTick);
        if (clamped <= 0L) {
            if (this.diplomacyCooldownEndsAtTickByPair.remove(key) != null) {
                this.method_80();
            }
            return;
        }
        this.diplomacyCooldownEndsAtTickByPair.put(key, clamped);
        this.method_80();
    }

    public void clearExpiredDiplomacyCooldowns(long currentTick) {
        if (this.diplomacyCooldownEndsAtTickByPair.entrySet().removeIf(entry -> (Long)entry.getValue() <= currentTick)) {
            this.method_80();
        }
    }

    public long getNextDeclarationDay(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return 0L;
        }
        return this.nextDeclarationDayByKingdom.getOrDefault(kingdomId, 0L);
    }

    public void setNextDeclarationDay(String kingdomId, long readyDay) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return;
        }
        this.nextDeclarationDayByKingdom.put(kingdomId, Math.max(0L, readyDay));
        this.method_80();
    }

    public void addActiveWar(ActiveWar war) {
        if (war == null || war.attackerKingdomId().isBlank() || war.defenderKingdomId().isBlank()) {
            return;
        }
        this.activeWars.put(KingdomRelationsWorldState.warKey(war.attackerKingdomId(), war.defenderKingdomId()), war);
        this.method_80();
    }

    public ActiveWar removeActiveWar(String firstKingdomId, String secondKingdomId) {
        if (firstKingdomId == null || secondKingdomId == null || firstKingdomId.isBlank() || secondKingdomId.isBlank()) {
            return null;
        }
        ActiveWar removed = this.activeWars.remove(KingdomRelationsWorldState.warKey(firstKingdomId, secondKingdomId));
        if (removed != null) {
            this.method_80();
        }
        return removed;
    }

    public long getLastWarRefreshDay() {
        return this.lastWarRefreshDay;
    }

    public void setLastWarRefreshDay(long lastWarRefreshDay) {
        this.lastWarRefreshDay = lastWarRefreshDay;
        this.method_80();
    }

    public long getLastObservedWarTimeOfDay() {
        return this.lastObservedWarTimeOfDay;
    }

    public void setLastObservedWarTimeOfDay(long lastObservedWarTimeOfDay) {
        this.lastObservedWarTimeOfDay = Math.max(-1L, lastObservedWarTimeOfDay);
        this.method_80();
    }

    public long getQueuedManualWarDays() {
        return this.queuedManualWarDays;
    }

    public void queueManualWarDay() {
        ++this.queuedManualWarDays;
        this.method_80();
    }

    public void consumeQueuedManualWarDays(long amount) {
        if (amount <= 0L || this.queuedManualWarDays <= 0L) {
            return;
        }
        this.queuedManualWarDays = Math.max(0L, this.queuedManualWarDays - amount);
        this.method_80();
    }

    public void ensureInitialized() {
        if (this.initialized) {
            return;
        }
        this.relations.clear();
        this.relations.putAll(KingdomRelationPresetRegistry.createPresetMatrix());
        for (String kingdomId : this.relations.keySet()) {
            this.nextDeclarationDayByKingdom.putIfAbsent(kingdomId, 0L);
            this.declarationCooldownDays.putIfAbsent(kingdomId, new LinkedHashMap());
        }
        this.initialized = true;
        this.method_80();
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2487 matrix = new class_2487();
        for (Map.Entry<String, Map<String, Integer>> entry : this.relations.entrySet()) {
            class_2487 class_24872 = new class_2487();
            for (Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()) {
                class_24872.method_10569(entry2.getKey(), entry2.getValue().intValue());
            }
            matrix.method_10566(entry.getKey(), (class_2520)class_24872);
        }
        nbt.method_10566("relations", (class_2520)matrix);
        class_2499 activeWarList = new class_2499();
        for (ActiveWar activeWar : this.activeWars.values()) {
            activeWarList.add((Object)activeWar.toNbt());
        }
        nbt.method_10566("active_wars", (class_2520)activeWarList);
        class_2499 class_24992 = new class_2499();
        for (PendingWarDeclaration pendingWarDeclaration : this.pendingWarDeclarations.values()) {
            class_24992.add((Object)pendingWarDeclaration.toNbt());
        }
        nbt.method_10566("pending_war_declarations", (class_2520)class_24992);
        class_2487 class_24873 = new class_2487();
        for (Map.Entry<String, Map<String, Long>> entry : this.declarationCooldownDays.entrySet()) {
            class_2487 class_24874 = new class_2487();
            for (Map.Entry<String, Long> cooldown : entry.getValue().entrySet()) {
                class_24874.method_10544(cooldown.getKey(), cooldown.getValue().longValue());
            }
            class_24873.method_10566(entry.getKey(), (class_2520)class_24874);
        }
        nbt.method_10566("pair_declaration_cooldowns", (class_2520)class_24873);
        class_2487 class_24875 = new class_2487();
        for (Map.Entry<String, Long> entry : this.diplomacyCooldownEndsAtTickByPair.entrySet()) {
            class_24875.method_10544(entry.getKey(), entry.getValue().longValue());
        }
        nbt.method_10566("diplomacy_cooldowns", (class_2520)class_24875);
        class_2487 class_24876 = new class_2487();
        for (Map.Entry<String, Long> entry : this.nextDeclarationDayByKingdom.entrySet()) {
            class_24876.method_10544(entry.getKey(), entry.getValue().longValue());
        }
        nbt.method_10566("next_declaration_days", (class_2520)class_24876);
        nbt.method_10556("initialized", this.initialized);
        nbt.method_10544("last_war_refresh_day", this.lastWarRefreshDay);
        nbt.method_10544("last_observed_war_time_of_day", this.lastObservedWarTimeOfDay);
        nbt.method_10544("queued_manual_war_days", this.queuedManualWarDays);
        return nbt;
    }

    private static KingdomRelationsWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        class_2487 compound;
        class_2487 row;
        KingdomRelationsWorldState state = new KingdomRelationsWorldState();
        class_2487 matrix = nbt.method_10562("relations");
        for (String fromKingdomId : matrix.method_10541()) {
            class_2487 rowNbt = matrix.method_10562(fromKingdomId);
            row = new LinkedHashMap();
            Iterator iterator = rowNbt.method_10541().iterator();
            while (iterator.hasNext()) {
                String toKingdomId;
                row.put(toKingdomId, fromKingdomId.equals(toKingdomId = (String)iterator.next()) ? 100 : KingdomRelationsService.clamp(rowNbt.method_10550(toKingdomId)));
            }
            state.relations.put(fromKingdomId, (Map<String, Integer>)row);
        }
        if (nbt.method_10573("active_wars", 9)) {
            class_2499 activeWarList = nbt.method_10554("active_wars", 10);
            for (class_2520 element : activeWarList) {
                ActiveWar war;
                if (!(element instanceof class_2487) || (war = ActiveWar.fromNbt(compound = (class_2487)element)).attackerKingdomId().isBlank() || war.defenderKingdomId().isBlank()) continue;
                state.activeWars.put(KingdomRelationsWorldState.warKey(war.attackerKingdomId(), war.defenderKingdomId()), war);
            }
        }
        if (nbt.method_10573("pending_war_declarations", 9)) {
            class_2499 pendingWarList = nbt.method_10554("pending_war_declarations", 10);
            for (class_2520 element : pendingWarList) {
                PendingWarDeclaration declaration;
                if (!(element instanceof class_2487) || (declaration = PendingWarDeclaration.fromNbt(compound = (class_2487)element)).attackerKingdomId().isBlank() || declaration.defenderKingdomId().isBlank()) continue;
                state.pendingWarDeclarations.put(KingdomRelationsWorldState.pendingWarKey(declaration.attackerKingdomId(), declaration.defenderKingdomId()), declaration);
            }
        }
        if (nbt.method_10573("pair_declaration_cooldowns", 10)) {
            class_2487 pairCooldowns = nbt.method_10562("pair_declaration_cooldowns");
            for (String attackerKingdomId : pairCooldowns.method_10541()) {
                row = pairCooldowns.method_10562(attackerKingdomId);
                LinkedHashMap<String, Long> cooldowns = new LinkedHashMap<String, Long>();
                for (String defenderKingdomId : row.method_10541()) {
                    cooldowns.put(defenderKingdomId, row.method_10537(defenderKingdomId));
                }
                state.declarationCooldownDays.put(attackerKingdomId, cooldowns);
            }
        }
        if (nbt.method_10573("diplomacy_cooldowns", 10)) {
            class_2487 diplomacyCooldowns = nbt.method_10562("diplomacy_cooldowns");
            for (String pairKey : diplomacyCooldowns.method_10541()) {
                state.diplomacyCooldownEndsAtTickByPair.put(pairKey, Math.max(0L, diplomacyCooldowns.method_10537(pairKey)));
            }
        }
        if (nbt.method_10573("next_declaration_days", 10)) {
            class_2487 declarationDays = nbt.method_10562("next_declaration_days");
            for (String kingdomId : declarationDays.method_10541()) {
                state.nextDeclarationDayByKingdom.put(kingdomId, declarationDays.method_10537(kingdomId));
            }
        }
        state.initialized = nbt.method_10577("initialized");
        if (nbt.method_10545("last_war_refresh_day")) {
            state.lastWarRefreshDay = nbt.method_10537("last_war_refresh_day");
        }
        if (nbt.method_10545("last_observed_war_time_of_day")) {
            state.lastObservedWarTimeOfDay = nbt.method_10537("last_observed_war_time_of_day");
        }
        if (nbt.method_10545("queued_manual_war_days")) {
            state.queuedManualWarDays = Math.max(0L, nbt.method_10537("queued_manual_war_days"));
        }
        return state;
    }

    private static String warKey(String firstKingdomId, String secondKingdomId) {
        return firstKingdomId.compareTo(secondKingdomId) <= 0 ? firstKingdomId + "|" + secondKingdomId : secondKingdomId + "|" + firstKingdomId;
    }

    private static String pendingWarKey(String attackerKingdomId, String defenderKingdomId) {
        return attackerKingdomId + "->" + defenderKingdomId;
    }

    public record ActiveWar(String attackerKingdomId, String defenderKingdomId, long startedDay, long endsAtDay) {
        public ActiveWar {
            attackerKingdomId = attackerKingdomId == null ? "" : attackerKingdomId;
            defenderKingdomId = defenderKingdomId == null ? "" : defenderKingdomId;
        }

        public boolean involves(String kingdomId) {
            return kingdomId != null && (kingdomId.equals(this.attackerKingdomId) || kingdomId.equals(this.defenderKingdomId));
        }

        public String otherKingdom(String kingdomId) {
            if (kingdomId == null || kingdomId.isBlank()) {
                return "";
            }
            if (kingdomId.equals(this.attackerKingdomId)) {
                return this.defenderKingdomId;
            }
            if (kingdomId.equals(this.defenderKingdomId)) {
                return this.attackerKingdomId;
            }
            return "";
        }

        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("attacker_kingdom_id", this.attackerKingdomId);
            nbt.method_10582("defender_kingdom_id", this.defenderKingdomId);
            nbt.method_10544("started_day", this.startedDay);
            nbt.method_10544("ends_at_day", this.endsAtDay);
            return nbt;
        }

        public static ActiveWar fromNbt(class_2487 nbt) {
            return new ActiveWar(nbt.method_10558("attacker_kingdom_id"), nbt.method_10558("defender_kingdom_id"), nbt.method_10537("started_day"), nbt.method_10537("ends_at_day"));
        }
    }

    public record PendingWarDeclaration(String attackerKingdomId, String defenderKingdomId, String actorName, long scheduledDay, long scheduledTimeOfDay) {
        public PendingWarDeclaration {
            attackerKingdomId = attackerKingdomId == null ? "" : attackerKingdomId;
            defenderKingdomId = defenderKingdomId == null ? "" : defenderKingdomId;
            actorName = actorName == null ? "" : actorName;
            scheduledDay = Math.max(0L, scheduledDay);
            scheduledTimeOfDay = Math.max(0L, scheduledTimeOfDay);
        }

        public class_2487 toNbt() {
            class_2487 nbt = new class_2487();
            nbt.method_10582("attacker_kingdom_id", this.attackerKingdomId);
            nbt.method_10582("defender_kingdom_id", this.defenderKingdomId);
            nbt.method_10582("actor_name", this.actorName);
            nbt.method_10544("scheduled_day", this.scheduledDay);
            nbt.method_10544("scheduled_time_of_day", this.scheduledTimeOfDay);
            return nbt;
        }

        public static PendingWarDeclaration fromNbt(class_2487 nbt) {
            return new PendingWarDeclaration(nbt.method_10558("attacker_kingdom_id"), nbt.method_10558("defender_kingdom_id"), nbt.method_10558("actor_name"), nbt.method_10537("scheduled_day"), nbt.method_10537("scheduled_time_of_day"));
        }
    }
}

