/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_2960
 */
package trep.got.quest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_2960;
import trep.got.api.internal.AgobAddonRegistry;
import trep.got.entity.AbstractKingdomGuardNpcEntity;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.kingdom.ReputationTitle;
import trep.got.quest.QuestDebugLogBuffer;
import trep.got.quest.QuestInstance;
import trep.got.quest.QuestReward;
import trep.got.quest.QuestRewardType;
import trep.got.quest.QuestTemplate;
import trep.got.registry.GotDynastyNpcs;
import trep.got.skill.SkillAttributeType;

public final class QuestTemplateRegistry {
    private static final String SIDE_QUEST_INDEX_PATH = "data/agob/quests/side/templates.json";
    private static final String SIDE_QUEST_ROOT = "data/agob/quests/side/";
    private static final long OFFER_WINDOW_TICKS = 24000L;
    private static final int OFFER_ROTATION_DAYS = 3;
    private static Map<String, QuestTemplate> byId = Map.of();
    private static List<QuestTemplate> templates = List.of();

    private QuestTemplateRegistry() {
    }

    public static void initialize() {
        QuestTemplate template;
        LinkedHashMap<String, QuestTemplate> loaded = new LinkedHashMap<String, QuestTemplate>();
        for (String id : QuestTemplateRegistry.loadIndex()) {
            template = QuestTemplateRegistry.loadTemplate(id);
            loaded.put(template.id(), template);
        }
        for (String resourcePath : AgobAddonRegistry.questTemplateResources()) {
            template = QuestTemplateRegistry.loadTemplateResource(resourcePath);
            QuestTemplate previous = loaded.putIfAbsent(template.id(), template);
            if (previous == null) continue;
            throw new IllegalStateException("Duplicate quest template id: " + template.id());
        }
        for (QuestTemplate template2 : AgobAddonRegistry.questTemplates()) {
            QuestTemplate previous = loaded.putIfAbsent(template2.id(), template2);
            if (previous == null) continue;
            throw new IllegalStateException("Duplicate quest template id: " + template2.id());
        }
        byId = Map.copyOf(loaded);
        templates = List.copyOf(loaded.values());
    }

    public static QuestTemplate get(String id) {
        return byId.get(id);
    }

    public static long offerWindowTicks() {
        return 24000L;
    }

    public static QuestInstance previewForPlayer(UUID playerUuid, AbstractKingdomNpcEntity npc, long offerCycle, long worldTime, ReputationTitle reputationTitle) {
        if (playerUuid == null || npc == null) {
            return null;
        }
        List<QuestTemplate> eligible = QuestTemplateRegistry.eligibleTemplates(npc, offerCycle);
        if (eligible.isEmpty()) {
            return null;
        }
        ArrayList<QuestTemplate> ordered = new ArrayList<QuestTemplate>(eligible);
        QuestTemplateRegistry.sortOfferTemplates(ordered, npc);
        int startIndex = QuestTemplateRegistry.isNightsWatchNpc(npc) ? 0 : Math.floorMod((int)QuestTemplateRegistry.computeOfferSeed(playerUuid, npc.method_5667(), offerCycle, "template_rotation"), ordered.size());
        for (int index = 0; index < ordered.size(); ++index) {
            QuestTemplate template = (QuestTemplate)ordered.get((startIndex + index) % ordered.size());
            QuestInstance instance = template.createInstance(playerUuid, npc, worldTime, offerCycle, reputationTitle);
            if (instance == null) continue;
            return instance;
        }
        return null;
    }

    public static QuestInstance previewForPlayer(UUID playerUuid, AbstractKingdomNpcEntity npc, long offerCycle, long worldTime) {
        return QuestTemplateRegistry.previewForPlayer(playerUuid, npc, offerCycle, worldTime, ReputationTitle.PEASANT);
    }

    public static QuestTemplate resolveOfferTemplate(AbstractKingdomNpcEntity npc, long offerCycle) {
        List<QuestTemplate> eligible = QuestTemplateRegistry.eligibleTemplates(npc, offerCycle);
        if (eligible.isEmpty()) {
            return null;
        }
        ArrayList<QuestTemplate> ordered = new ArrayList<QuestTemplate>(eligible);
        QuestTemplateRegistry.sortOfferTemplates(ordered, npc);
        int startIndex = QuestTemplateRegistry.isNightsWatchNpc(npc) ? 0 : Math.floorMod((int)QuestTemplateRegistry.computeOfferSeed(new UUID(0L, 0L), npc.method_5667(), offerCycle, "template_rotation"), ordered.size());
        return (QuestTemplate)ordered.get(startIndex);
    }

    private static List<QuestTemplate> eligibleTemplates(AbstractKingdomNpcEntity npc, long offerCycle) {
        if (npc == null || templates.isEmpty()) {
            return List.of();
        }
        if (npc instanceof AbstractKingdomGuardNpcEntity && !(npc instanceof AbstractKingdomLordNpcEntity)) {
            return List.of();
        }
        if (QuestTemplateRegistry.isNightsWatchNpc(npc)) {
            return templates.stream().filter(template -> "destroy_wildling_raider_camp".equals(template.id()) || "nights_watch_ranger_explore".equals(template.id())).filter(template -> template.matches(npc)).sorted(Comparator.comparingInt(QuestTemplateRegistry::nightsWatchTemplatePriority).thenComparing(QuestTemplate::id)).toList();
        }
        List<QuestTemplate> eligible = templates.stream().filter(template -> template.matches(npc)).toList();
        if (eligible.isEmpty()) {
            if (npc instanceof AbstractKingdomLordNpcEntity) {
                QuestDebugLogBuffer.record("lord_letter_delivery", "template_blocked:no_matching_templates", "giver=" + npc.method_5476().getString() + ",giver_uuid=" + npc.method_5845() + ",giver_castle=" + npc.got$getCastle() + ",offer_cycle=" + offerCycle + ",side=" + (npc.method_37908() == null ? "<unknown>" : (npc.method_37908().method_8608() ? "client" : "server")));
            }
            return List.of();
        }
        return eligible;
    }

    private static void sortOfferTemplates(List<QuestTemplate> ordered, AbstractKingdomNpcEntity npc) {
        if (QuestTemplateRegistry.isNightsWatchNpc(npc)) {
            ordered.sort(Comparator.comparingInt(QuestTemplateRegistry::nightsWatchTemplatePriority).thenComparing(QuestTemplate::id));
            return;
        }
        ordered.sort(Comparator.comparing(QuestTemplate::id));
    }

    private static boolean isNightsWatchNpc(AbstractKingdomNpcEntity npc) {
        return npc != null && npc.getKingdomId() != null && "agob:the_nights_watch".equals(npc.getKingdomId().toString());
    }

    private static int nightsWatchTemplatePriority(QuestTemplate template) {
        if ("nights_watch_ranger_explore".equals(template.id())) {
            return 0;
        }
        if ("destroy_wildling_raider_camp".equals(template.id())) {
            return 1;
        }
        return 100;
    }

    public static String offerKey(UUID npcUuid, long offerCycle) {
        return String.valueOf(npcUuid) + ":" + offerCycle;
    }

    public static long computeOfferSeed(UUID playerUuid, UUID npcUuid, long offerCycle, String templateId) {
        return playerUuid.getMostSignificantBits() ^ playerUuid.getLeastSignificantBits() ^ npcUuid.getMostSignificantBits() ^ npcUuid.getLeastSignificantBits() ^ offerCycle ^ (long)templateId.hashCode();
    }

    private static List<String> loadIndex() {
        List<String> list;
        block10: {
            InputStream stream = QuestTemplateRegistry.class.getClassLoader().getResourceAsStream(SIDE_QUEST_INDEX_PATH);
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing side quest template index data/agob/quests/side/templates.json");
                }
                JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                JsonArray array = root.getAsJsonArray("templates");
                ArrayList<String> ids = new ArrayList<String>();
                for (int index = 0; index < array.size(); ++index) {
                    ids.add(array.get(index).getAsString());
                }
                list = List.copyOf(ids);
                if (stream == null) break block10;
            }
            catch (Throwable throwable) {
                try {
                    if (stream != null) {
                        try {
                            stream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (Exception exception) {
                    throw new IllegalStateException("Failed to load side quest template index data/agob/quests/side/templates.json", exception);
                }
            }
            stream.close();
        }
        return list;
    }

    private static QuestTemplate loadTemplate(String id) {
        String path = SIDE_QUEST_ROOT + id + ".json";
        return QuestTemplateRegistry.loadTemplateResource(path);
    }

    private static QuestTemplate loadTemplateResource(String path) {
        QuestTemplate questTemplate;
        block23: {
            String label = path.startsWith(SIDE_QUEST_ROOT) ? path.substring(SIDE_QUEST_ROOT.length(), path.length() - ".json".length()) : path;
            InputStream stream = QuestTemplateRegistry.class.getClassLoader().getResourceAsStream(path);
            try {
                class_2960 primaryItemId;
                if (stream == null) {
                    throw new IllegalStateException("Missing quest template " + path);
                }
                JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                ArrayList<QuestReward> rewards = new ArrayList<QuestReward>();
                for (Object element : root.getAsJsonArray("rewards")) {
                    JsonObject reward = element.getAsJsonObject();
                    rewards.add(new QuestReward(QuestRewardType.byId(reward.get("type").getAsString()), reward.get("min").getAsInt(), reward.get("max").getAsInt(), reward.has("skill") ? SkillAttributeType.byId(reward.get("skill").getAsString()) : null, reward.has("per_item") && reward.get("per_item").getAsBoolean(), reward.has("chance_percent") ? reward.get("chance_percent").getAsInt() : 100, reward.has("peasant_bonus") ? reward.get("peasant_bonus").getAsInt() : 0));
                }
                LinkedHashSet<KingdomNpcRole> allowedRoles = new LinkedHashSet<KingdomNpcRole>();
                if (root.has("allowed_roles")) {
                    for (Object element : root.getAsJsonArray("allowed_roles")) {
                        allowedRoles.add(KingdomNpcRole.valueOf(element.getAsString().toUpperCase(Locale.ROOT)));
                    }
                }
                LinkedHashSet<String> allowedKingdomIds = new LinkedHashSet<String>();
                if (root.has("allowed_kingdom_ids")) {
                    for (Object element : root.getAsJsonArray("allowed_kingdom_ids")) {
                        allowedKingdomIds.add(class_2960.method_60654((String)element.getAsString()).toString());
                    }
                }
                LinkedHashSet<GotDynastyNpcs.FamilyStage> allowedFamilyStages = new LinkedHashSet<GotDynastyNpcs.FamilyStage>();
                if (root.has("allowed_family_stages")) {
                    for (Object element : root.getAsJsonArray("allowed_family_stages")) {
                        allowedFamilyStages.add(GotDynastyNpcs.FamilyStage.byId(element.getAsString()));
                    }
                }
                ArrayList<class_2960> itemIds = new ArrayList<class_2960>();
                if (root.has("item_ids")) {
                    for (JsonElement element : root.getAsJsonArray("item_ids")) {
                        itemIds.add(class_2960.method_60654((String)element.getAsString()));
                    }
                }
                if (root.has("item_id")) {
                    primaryItemId = class_2960.method_60654((String)root.get("item_id").getAsString());
                } else if (!itemIds.isEmpty()) {
                    primaryItemId = (class_2960)itemIds.getFirst();
                } else {
                    throw new IllegalStateException("Quest template " + label + " must define item_id or item_ids");
                }
                if (itemIds.isEmpty()) {
                    itemIds.add(primaryItemId);
                }
                questTemplate = new QuestTemplate(root.get("id").getAsString(), root.get("display_name").getAsString(), primaryItemId, List.copyOf(itemIds), root.get("min_count").getAsInt(), root.get("max_count").getAsInt(), root.get("min_duration_days").getAsInt(), root.get("max_duration_days").getAsInt(), Set.copyOf(allowedRoles), Set.copyOf(allowedKingdomIds), Set.copyOf(allowedFamilyStages), rewards);
                if (stream == null) break block23;
            }
            catch (Throwable throwable) {
                try {
                    if (stream != null) {
                        try {
                            stream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (Exception exception) {
                    throw new IllegalStateException("Failed to load quest template " + label, exception);
                }
            }
            stream.close();
        }
        return questTemplate;
    }
}

