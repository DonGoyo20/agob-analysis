/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1959
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_3222
 *  net.minecraft.class_5321
 */
package trep.got.quest;

import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1959;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_5321;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.WildlingRaiderNpcEntity;
import trep.got.kingdom.ReputationTitle;
import trep.got.quest.PlayerQuestHolder;
import trep.got.quest.PlayerQuestProfile;
import trep.got.quest.QuestInstance;
import trep.got.quest.QuestTemplateRegistry;
import trep.got.registry.GotQuests;
import trep.got.world.biome.GotBiomeKeys;

public final class RangerExploreQuestLogic {
    public static final String TEMPLATE_ID = "nights_watch_ranger_explore";
    public static final String STAGE_TRAVEL = "travel";
    public static final String STAGE_KILL_RAIDERS = "kill_raiders";
    public static final String STAGE_RETURN = "return";
    public static final String METADATA_STAGE = "stage";
    public static final String METADATA_RAIDER_KILLS = "raider_kills";
    public static final int REQUIRED_RAIDER_KILLS = 10;
    private static final int AVAILABILITY_CHANCE_PERCENT = 35;

    private RangerExploreQuestLogic() {
    }

    public static QuestInstance createQuest(UUID playerUuid, AbstractKingdomNpcEntity npc, long worldTime, long offerCycle, ReputationTitle reputationTitle) {
        if (playerUuid == null || !(npc instanceof AbstractKingdomLordNpcEntity) || npc.getKingdomId() == null) {
            return null;
        }
        if (!"agob:the_nights_watch".equals(npc.getKingdomId().toString())) {
            return null;
        }
        long seed = QuestTemplateRegistry.computeOfferSeed(playerUuid, npc.method_5667(), offerCycle, TEMPLATE_ID);
        int availabilityRoll = Math.floorMod((int)(seed * 37L ^ seed >>> 11), 100);
        if (availabilityRoll >= 35) {
            return null;
        }
        long durationDays = 5L;
        long dueAtTick = worldTime + durationDays * 24000L;
        String questId = "quest_" + Math.abs((playerUuid.toString() + npc.method_5845() + worldTime + TEMPLATE_ID).hashCode());
        int reputation = reputationTitle == ReputationTitle.PEASANT ? 28 : 26;
        class_2487 metadata = new class_2487();
        metadata.method_10582(METADATA_STAGE, STAGE_TRAVEL);
        metadata.method_10569(METADATA_RAIDER_KILLS, 0);
        return new QuestInstance(questId, TEMPLATE_ID, npc.method_5667(), npc.method_5476().getString(), QuestTemplateRegistry.offerKey(npc.method_5667(), offerCycle), class_2960.method_60656((String)"iron_sword").toString(), 0, worldTime, dueAtTick, reputation, 6, 0, "charisma", 1, "", 0, "", "", "", "", class_2960.method_60655((String)"agob", (String)"wildling_chestplate").toString(), metadata);
    }

    public static void tickPlayer(class_3222 player) {
        if (player == null || player.method_51469().method_8510() % 20L != 0L) {
            return;
        }
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        for (QuestInstance quest : profile.getActiveQuests()) {
            if (!TEMPLATE_ID.equals(quest.templateId()) || !STAGE_TRAVEL.equals(RangerExploreQuestLogic.stage(quest)) || !RangerExploreQuestLogic.isAlwaysWinterBiome(player)) continue;
            class_2487 metadata = quest.metadata().method_10553();
            metadata.method_10582(METADATA_STAGE, STAGE_KILL_RAIDERS);
            metadata.method_10569(METADATA_RAIDER_KILLS, 0);
            profile.updateQuest(quest.withMetadata(metadata));
            player.method_7353((class_2561)class_2561.method_43470((String)"You have crossed beyond the Wall. Hunt down 10 wildling raiders."), true);
            GotQuests.sendQuestState(player);
        }
    }

    public static void onKilledEntity(class_3222 player, class_1297 killedEntity) {
        if (player == null || !(killedEntity instanceof WildlingRaiderNpcEntity)) {
            return;
        }
        PlayerQuestProfile profile = ((PlayerQuestHolder)player).got$getQuestProfile();
        for (QuestInstance quest : profile.getActiveQuests()) {
            if (!TEMPLATE_ID.equals(quest.templateId()) || !STAGE_KILL_RAIDERS.equals(RangerExploreQuestLogic.stage(quest))) continue;
            class_2487 metadata = quest.metadata().method_10553();
            int kills = Math.min(10, metadata.method_10550(METADATA_RAIDER_KILLS) + 1);
            metadata.method_10569(METADATA_RAIDER_KILLS, kills);
            if (kills >= 10) {
                metadata.method_10582(METADATA_STAGE, STAGE_RETURN);
                player.method_7353((class_2561)class_2561.method_43470((String)"The raider party is broken. Return to the Lord Commander with the news."), true);
            }
            profile.updateQuest(quest.withMetadata(metadata));
            GotQuests.sendQuestState(player);
        }
    }

    public static boolean canComplete(QuestInstance quest) {
        return quest != null && TEMPLATE_ID.equals(quest.templateId()) && STAGE_RETURN.equals(RangerExploreQuestLogic.stage(quest));
    }

    public static String stage(QuestInstance quest) {
        return quest == null ? STAGE_TRAVEL : quest.metadata().method_10558(METADATA_STAGE);
    }

    public static int raiderKills(QuestInstance quest) {
        return quest == null ? 0 : quest.metadata().method_10550(METADATA_RAIDER_KILLS);
    }

    private static boolean isAlwaysWinterBiome(class_3222 player) {
        return player.method_51469().method_23753(player.method_24515()).method_40230().map(RangerExploreQuestLogic::isAlwaysWinterKey).orElse(false);
    }

    private static boolean isAlwaysWinterKey(class_5321<class_1959> key) {
        return key.equals(GotBiomeKeys.ALWAYS_WINTER) || key.equals(GotBiomeKeys.ALWAYS_WINTER_FOREST) || key.equals(GotBiomeKeys.ALWAYS_WINTER_HILLS);
    }
}

