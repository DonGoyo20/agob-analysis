/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 */
package trep.got.quest;

import java.util.UUID;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.kingdom.ReputationTitle;
import trep.got.quest.QuestInstance;
import trep.got.quest.QuestTemplateRegistry;

public final class BanditCampQuestLogic {
    public static final String TEMPLATE_ID = "nearby_bandit_camp_destroy";
    public static final String METADATA_GIVER_CASTLE_ID = "giver_castle_id";
    public static final String METADATA_GIVER_CASTLE_NAME = "giver_castle_name";
    public static final String METADATA_CAMP_CHUNK_X = "camp_chunk_x";
    public static final String METADATA_CAMP_CHUNK_Z = "camp_chunk_z";
    public static final String METADATA_CAMP_ORIGIN_X = "camp_origin_x";
    public static final String METADATA_CAMP_ORIGIN_Y = "camp_origin_y";
    public static final String METADATA_CAMP_ORIGIN_Z = "camp_origin_z";
    public static final String METADATA_CAMP_WORLD = "camp_world";
    private static final int AVAILABILITY_CHANCE_PERCENT = 9;

    private BanditCampQuestLogic() {
    }

    public static QuestInstance createQuest(UUID playerUuid, AbstractKingdomNpcEntity npc, long worldTime, long offerCycle, ReputationTitle reputationTitle) {
        String castleId;
        if (playerUuid == null || npc == null || npc.got$getCastle().isBlank() || npc.method_37908() == null) {
            return null;
        }
        String castleName = castleId = npc.got$getCastle();
        if (!npc.method_37908().method_8608()) {
            CastleInstance castle = CastleWorldState.get((class_3218)npc.method_37908()).get(castleId);
            if (castle == null) {
                return null;
            }
            castleName = castle.getTitle().displayName();
        }
        if (castleName.isBlank()) {
            return null;
        }
        long seed = QuestTemplateRegistry.computeOfferSeed(playerUuid, npc.method_5667(), offerCycle, TEMPLATE_ID);
        int availabilityRoll = Math.floorMod((int)(seed * 43L ^ seed >>> 9), 100);
        if (availabilityRoll >= 9) {
            return null;
        }
        long durationDays = 3L;
        long dueAtTick = worldTime + durationDays * 24000L;
        String questId = "quest_" + Math.abs((playerUuid.toString() + npc.method_5845() + worldTime + TEMPLATE_ID).hashCode());
        int reputation = reputationTitle == ReputationTitle.PEASANT ? 24 : 22;
        class_2487 metadata = new class_2487();
        metadata.method_10582(METADATA_GIVER_CASTLE_ID, castleId);
        metadata.method_10582(METADATA_GIVER_CASTLE_NAME, castleName);
        return new QuestInstance(questId, TEMPLATE_ID, npc.method_5667(), npc.method_5476().getString(), QuestTemplateRegistry.offerKey(npc.method_5667(), offerCycle), class_2960.method_60656((String)"iron_sword").toString(), 0, worldTime, dueAtTick, reputation, 6, 0, "charisma", 1, "", 0, "", "", "", "", class_2960.method_60655((String)"agob", (String)"tent").toString(), metadata);
    }
}

