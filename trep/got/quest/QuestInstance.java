/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1263
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 *  net.minecraft.class_7923
 */
package trep.got.quest;

import java.util.UUID;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.item.QuestDeliveryItemData;
import trep.got.quest.PledgeQuestLogic;
import trep.got.quest.ValyrianRumorQuestLogic;

public record QuestInstance(String id, String templateId, UUID giverNpcUuid, String giverNpcName, String offerKey, String itemId, int requiredCount, long acceptedAtTick, long dueAtTick, int reputationExperience, int goldIngots, int ironIngots, String skillTypeId, int skillPoints, String bonusSkillTypeId, int bonusSkillPoints, String targetRoleId, String targetCastleId, String targetCastleName, String targetNpcName, String displayItemId, class_2487 metadata) {
    public QuestInstance {
        metadata = metadata == null ? new class_2487() : metadata.method_10553();
    }

    public boolean isExpired(long worldTime) {
        return worldTime > this.dueAtTick;
    }

    public boolean canComplete(class_1657 player) {
        if (player == null) {
            return false;
        }
        if ("main_pledge".equals(this.templateId)) {
            return PledgeQuestLogic.isObjectiveComplete(this);
        }
        if ("valyrian_rumor".equals(this.templateId)) {
            return ValyrianRumorQuestLogic.canComplete(this);
        }
        if (this.isShipmentQuest()) {
            return QuestInstance.countShipmentItem((class_1263)player.method_31548(), this.id) >= this.requiredCount;
        }
        class_1792 item = this.requiredItem();
        return item != null && QuestInstance.countItem((class_1263)player.method_31548(), item) >= this.requiredCount;
    }

    public class_1792 requiredItem() {
        class_2960 id = class_2960.method_12829((String)this.itemId);
        return id == null ? null : (class_1792)class_7923.field_41178.method_10223(id);
    }

    public class_1792 displayItem() {
        class_2960 id = class_2960.method_12829((String)this.displayItemId);
        return id == null ? this.requiredItem() : (class_1792)class_7923.field_41178.method_10223(id);
    }

    public boolean isShipmentQuest() {
        return !this.targetCastleId.isBlank();
    }

    public boolean matchesNpc(AbstractKingdomNpcEntity npc) {
        if (npc == null) {
            return false;
        }
        if ("main_pledge".equals(this.templateId)) {
            return this.targetCastleId.equals(npc.got$getCastle()) && this.targetRoleId.equalsIgnoreCase(npc.getNpcRole().name());
        }
        if ("valyrian_rumor".equals(this.templateId)) {
            return ValyrianRumorQuestLogic.matchesNpc(this, npc);
        }
        if (this.isShipmentQuest()) {
            return this.targetCastleId.equals(npc.got$getCastle()) && this.targetRoleId.equalsIgnoreCase(npc.getNpcRole().name());
        }
        return this.giverNpcUuid.equals(npc.method_5667());
    }

    public QuestInstance withRewards(int reputationExperience, int goldIngots, int ironIngots, String skillTypeId, int skillPoints) {
        return new QuestInstance(this.id, this.templateId, this.giverNpcUuid, this.giverNpcName, this.offerKey, this.itemId, this.requiredCount, this.acceptedAtTick, this.dueAtTick, reputationExperience, goldIngots, ironIngots, skillTypeId, skillPoints, this.bonusSkillTypeId, this.bonusSkillPoints, this.targetRoleId, this.targetCastleId, this.targetCastleName, this.targetNpcName, this.displayItemId, this.metadata);
    }

    public QuestInstance withMetadata(class_2487 metadata) {
        return new QuestInstance(this.id, this.templateId, this.giverNpcUuid, this.giverNpcName, this.offerKey, this.itemId, this.requiredCount, this.acceptedAtTick, this.dueAtTick, this.reputationExperience, this.goldIngots, this.ironIngots, this.skillTypeId, this.skillPoints, this.bonusSkillTypeId, this.bonusSkillPoints, this.targetRoleId, this.targetCastleId, this.targetCastleName, this.targetNpcName, this.displayItemId, metadata);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("id", this.id);
        nbt.method_10582("template_id", this.templateId);
        nbt.method_25927("giver_npc_uuid", this.giverNpcUuid);
        nbt.method_10582("giver_npc_name", this.giverNpcName);
        nbt.method_10582("offer_key", this.offerKey);
        nbt.method_10582("item_id", this.itemId);
        nbt.method_10569("required_count", this.requiredCount);
        nbt.method_10544("accepted_at_tick", this.acceptedAtTick);
        nbt.method_10544("due_at_tick", this.dueAtTick);
        nbt.method_10569("reputation_experience", this.reputationExperience);
        nbt.method_10569("gold_ingots", this.goldIngots);
        nbt.method_10569("iron_ingots", this.ironIngots);
        nbt.method_10582("skill_type_id", this.skillTypeId);
        nbt.method_10569("skill_points", this.skillPoints);
        nbt.method_10582("bonus_skill_type_id", this.bonusSkillTypeId);
        nbt.method_10569("bonus_skill_points", this.bonusSkillPoints);
        nbt.method_10582("target_role_id", this.targetRoleId);
        nbt.method_10582("target_castle_id", this.targetCastleId);
        nbt.method_10582("target_castle_name", this.targetCastleName);
        nbt.method_10582("target_npc_name", this.targetNpcName);
        nbt.method_10582("display_item_id", this.displayItemId);
        nbt.method_10566("metadata", (class_2520)this.metadata.method_10553());
        return nbt;
    }

    public static QuestInstance fromNbt(class_2487 nbt) {
        return new QuestInstance(nbt.method_10558("id"), nbt.method_10558("template_id"), nbt.method_25926("giver_npc_uuid"), nbt.method_10558("giver_npc_name"), nbt.method_10558("offer_key"), nbt.method_10558("item_id"), nbt.method_10550("required_count"), nbt.method_10537("accepted_at_tick"), nbt.method_10537("due_at_tick"), nbt.method_10550("reputation_experience"), nbt.method_10550("gold_ingots"), nbt.method_10550("iron_ingots"), nbt.method_10558("skill_type_id"), nbt.method_10550("skill_points"), nbt.method_10558("bonus_skill_type_id"), nbt.method_10550("bonus_skill_points"), nbt.method_10558("target_role_id"), nbt.method_10558("target_castle_id"), nbt.method_10558("target_castle_name"), nbt.method_10558("target_npc_name"), nbt.method_10545("display_item_id") ? nbt.method_10558("display_item_id") : nbt.method_10558("item_id"), nbt.method_10573("metadata", 10) ? nbt.method_10562("metadata") : new class_2487());
    }

    private static int countItem(class_1263 inventory, class_1792 item) {
        int count = 0;
        for (int slot = 0; slot < inventory.method_5439(); ++slot) {
            class_1799 stack = inventory.method_5438(slot);
            if (!stack.method_31574(item)) continue;
            count += stack.method_7947();
        }
        return count;
    }

    private static int countShipmentItem(class_1263 inventory, String questId) {
        int count = 0;
        for (int slot = 0; slot < inventory.method_5439(); ++slot) {
            class_1799 stack = inventory.method_5438(slot);
            if (!QuestDeliveryItemData.matchesQuest(stack, questId)) continue;
            count += stack.method_7947();
        }
        return count;
    }
}

