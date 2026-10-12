/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 *  net.minecraft.class_3222
 *  net.minecraft.class_5321
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.registry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_5321;
import net.minecraft.server.MinecraftServer;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.registry.GotKingdoms;
import trep.got.relations.PlayerKingdomActionHistoryWorldState;
import trep.got.relations.PlayerKingdomRelationsHolder;

public final class GotPlayerKingdomActions {
    public static final String CONTEXT_NONE = "";
    public static final String CONTEXT_SELF_POSITIVE = "self_positive";
    public static final String CONTEXT_SELF_NEGATIVE = "self_negative";
    public static final String CONTEXT_OTHER_BAD_KILLS = "other_bad_kills";
    public static final String CONTEXT_OTHER_BAD_LOCKPICKING = "other_bad_lockpicking";
    public static final String CONTEXT_OTHER_GOOD = "other_good";
    private static final int BAD_RELATION_THRESHOLD = -30;
    private static final int GOOD_RELATION_THRESHOLD = 60;
    private static final int KILL_GOSSIP_THRESHOLD = 3;
    private static final int TRAVEL_SAMPLE_TICKS = 20;
    private static final Map<UUID, TravelSample> TRAVEL_SAMPLES = new LinkedHashMap<UUID, TravelSample>();
    private static int travelTickCounter;

    private GotPlayerKingdomActions() {
    }

    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(GotPlayerKingdomActions::tickTravel);
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> TRAVEL_SAMPLES.remove(handler.field_14140.method_5667()));
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            TRAVEL_SAMPLES.clear();
            travelTickCounter = 0;
        });
    }

    public static void recordRelation(class_3222 player, String kingdomId, int relation) {
        if (player == null || kingdomId == null || kingdomId.isBlank()) {
            return;
        }
        PlayerKingdomActionHistoryWorldState.get(player.method_51469()).recordRelation(player.method_5667(), player.method_5477().getString(), GotPlayerKingdomActions.canonicalKingdomId(kingdomId), relation);
    }

    public static void recordNpcKill(class_3222 player, String kingdomId, AbstractKingdomNpcEntity killedNpc, int relation) {
        if (player == null || kingdomId == null || kingdomId.isBlank() || killedNpc == null) {
            return;
        }
        PlayerKingdomActionHistoryWorldState.get(player.method_51469()).recordNpcKill(player.method_5667(), player.method_5477().getString(), GotPlayerKingdomActions.canonicalKingdomId(kingdomId), killedNpc.getNpcRole(), relation);
    }

    public static void recordLockpickingCaught(class_3222 player, String kingdomId) {
        if (player == null || kingdomId == null || kingdomId.isBlank()) {
            return;
        }
        String canonicalKingdomId = GotPlayerKingdomActions.canonicalKingdomId(kingdomId);
        int relation = ((PlayerKingdomRelationsHolder)player).got$getKingdomRelationsProfile().getRelation(canonicalKingdomId);
        PlayerKingdomActionHistoryWorldState.get(player.method_51469()).recordLockpickingCaught(player.method_5667(), player.method_5477().getString(), canonicalKingdomId, relation);
    }

    public static DialogueContext chooseDialogueContext(class_3222 viewer, String kingdomId, int npcRelation, long seed) {
        if (viewer == null || kingdomId == null || kingdomId.isBlank()) {
            return DialogueContext.EMPTY;
        }
        String canonicalKingdomId = GotPlayerKingdomActions.canonicalKingdomId(kingdomId);
        int kingdomRelation = ((PlayerKingdomRelationsHolder)viewer).got$getKingdomRelationsProfile().getRelation(canonicalKingdomId);
        GotPlayerKingdomActions.recordRelation(viewer, canonicalKingdomId, kingdomRelation);
        if (kingdomRelation <= -30 || npcRelation <= -11) {
            return new DialogueContext(CONTEXT_SELF_NEGATIVE, viewer.method_5477().getString());
        }
        if (kingdomRelation >= 60 || npcRelation > 10) {
            return new DialogueContext(CONTEXT_SELF_POSITIVE, viewer.method_5477().getString());
        }
        PlayerKingdomActionHistoryWorldState state = PlayerKingdomActionHistoryWorldState.get(viewer.method_51469());
        ArrayList<WeightedContext> candidates = new ArrayList<WeightedContext>();
        for (PlayerKingdomActionHistoryWorldState.PlayerEntry player : state.players()) {
            PlayerKingdomActionHistoryWorldState.KingdomEntry kingdom;
            if (viewer.method_5667().equals(player.playerUuid()) || (kingdom = player.getKingdom(canonicalKingdomId)) == null) continue;
            String playerName = GotPlayerKingdomActions.cleanPlayerName(player.playerName());
            if (kingdom.relation() <= -30 && kingdom.totalNpcKills() >= 3) {
                candidates.add(new WeightedContext(CONTEXT_OTHER_BAD_KILLS, playerName, 300 + kingdom.totalNpcKills()));
                continue;
            }
            if (kingdom.relation() <= -30 && kingdom.lockpickingCaught() > 0) {
                candidates.add(new WeightedContext(CONTEXT_OTHER_BAD_LOCKPICKING, playerName, 200 + kingdom.lockpickingCaught()));
                continue;
            }
            if (kingdom.relation() < 60) continue;
            candidates.add(new WeightedContext(CONTEXT_OTHER_GOOD, playerName, kingdom.relation()));
        }
        if (candidates.isEmpty()) {
            return DialogueContext.EMPTY;
        }
        candidates.sort(Comparator.comparingInt(WeightedContext::weight).reversed());
        int bound = Math.min(5, candidates.size());
        WeightedContext selected = (WeightedContext)candidates.get(Math.floorMod((int)(seed ^ seed >>> 32), bound));
        return new DialogueContext(selected.contextId(), selected.subjectName());
    }

    private static void tickTravel(MinecraftServer server) {
        if (++travelTickCounter < 20) {
            return;
        }
        travelTickCounter = 0;
        for (class_3222 player : server.method_3760().method_14571()) {
            double deltaZ;
            double deltaX;
            double distance;
            long wholeBlocks;
            double z;
            double x;
            class_5321 worldKey;
            UUID playerUuid = player.method_5667();
            TravelSample previous = TRAVEL_SAMPLES.put(playerUuid, new TravelSample((class_5321<class_1937>)(worldKey = player.method_51469().method_27983()), x = player.method_23317(), z = player.method_23321()));
            if (previous == null || !previous.worldKey().equals((Object)worldKey) || (wholeBlocks = (long)Math.floor(distance = Math.sqrt((deltaX = previous.x() - x) * deltaX + (deltaZ = previous.z() - z) * deltaZ))) <= 0L || wholeBlocks >= 512L) continue;
            PlayerKingdomActionHistoryWorldState.get(player.method_51469()).addBlocksTravelled(playerUuid, player.method_5477().getString(), wholeBlocks);
        }
    }

    private static String canonicalKingdomId(String kingdomId) {
        class_2960 id = class_2960.method_12829((String)kingdomId);
        if (id == null) {
            return kingdomId;
        }
        class_2960 cultureId = GotKingdoms.cultureKingdomId(id);
        return cultureId == null ? kingdomId : cultureId.toString();
    }

    private static String cleanPlayerName(String playerName) {
        if (playerName == null || playerName.isBlank()) {
            return "someone";
        }
        String trimmed = playerName.trim();
        return trimmed.length() <= 24 ? trimmed : trimmed.substring(0, 24);
    }

    public record DialogueContext(String contextId, String subjectName) {
        public static final DialogueContext EMPTY = new DialogueContext("", "");

        public DialogueContext {
            contextId = contextId == null ? GotPlayerKingdomActions.CONTEXT_NONE : contextId;
            subjectName = subjectName == null ? GotPlayerKingdomActions.CONTEXT_NONE : subjectName;
        }
    }

    private record WeightedContext(String contextId, String subjectName, int weight) {
    }

    private record TravelSample(class_5321<class_1937> worldKey, double x, double z) {
    }
}

