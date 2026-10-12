/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_2487
 *  net.minecraft.class_9279
 *  net.minecraft.class_9331
 *  net.minecraft.class_9334
 */
package trep.got.item;

import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_9279;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import trep.got.quest.PlayerQuestProfile;
import trep.got.registry.GotItems;

public final class QuestDeliveryItemData {
    public static final String QUEST_ID_KEY = "QuestDeliveryId";
    public static final String OWNER_UUID_KEY = "QuestDeliveryOwnerUuid";

    private QuestDeliveryItemData() {
    }

    public static void write(class_1799 stack, String questId, String ownerUuid) {
        class_2487 nbt = new class_2487();
        nbt.method_10582(QUEST_ID_KEY, questId == null ? "" : questId);
        nbt.method_10582(OWNER_UUID_KEY, ownerUuid == null ? "" : ownerUuid);
        class_9279.method_57453((class_9331)class_9334.field_49628, (class_1799)stack, (class_2487)nbt);
    }

    public static class_2487 baseNbt(String questId, String ownerUuid) {
        class_2487 nbt = new class_2487();
        nbt.method_10582(QUEST_ID_KEY, questId == null ? "" : questId);
        nbt.method_10582(OWNER_UUID_KEY, ownerUuid == null ? "" : ownerUuid);
        return nbt;
    }

    public static boolean isQuestDeliveryItem(class_1799 stack) {
        return stack != null && !stack.method_7960() && (stack.method_31574(GotItems.SHIPMENT_ITEM) || stack.method_31574(GotItems.SEALED_LETTER));
    }

    public static boolean matchesQuest(class_1799 stack, String questId) {
        return QuestDeliveryItemData.isQuestDeliveryItem(stack) && !questId.isBlank() && questId.equals(QuestDeliveryItemData.getString(stack, QUEST_ID_KEY));
    }

    public static boolean isValidForQuest(class_1799 stack, PlayerQuestProfile profile) {
        if (!QuestDeliveryItemData.isQuestDeliveryItem(stack) || profile == null) {
            return false;
        }
        String questId = QuestDeliveryItemData.getString(stack, QUEST_ID_KEY);
        return !questId.isBlank() && profile.hasActiveQuest(questId);
    }

    private static String getString(class_1799 stack, String key) {
        class_9279 component = (class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302);
        if (component.method_57458()) {
            return "";
        }
        return component.method_57461().method_10558(key);
    }
}

