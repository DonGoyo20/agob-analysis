/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_7923
 *  net.minecraft.class_9279
 *  net.minecraft.class_9280
 *  net.minecraft.class_9290
 *  net.minecraft.class_9331
 *  net.minecraft.class_9334
 */
package trep.got.item;

import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.minecraft.class_9279;
import net.minecraft.class_9280;
import net.minecraft.class_9290;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import trep.got.item.QuestDeliveryItemData;
import trep.got.registry.GotItems;

public final class ShipmentItem
extends class_1792 {
    private static final String DISPLAY_ITEM_ID_KEY = "ShipmentDisplayItemId";
    private static final String TARGET_CASTLE_ID_KEY = "ShipmentTargetCastleId";
    private static final String TARGET_CASTLE_NAME_KEY = "ShipmentTargetCastleName";
    private static final String MODEL_VARIANT_KEY = "ShipmentModelVariant";

    public ShipmentItem(class_1792.class_1793 settings) {
        super(settings);
    }

    public static class_1799 createStack(String questId, String ownerUuid, String displayItemId, String targetCastleId, String targetCastleName) {
        class_1799 stack = new class_1799((class_1935)GotItems.SHIPMENT_ITEM);
        class_2487 nbt = QuestDeliveryItemData.baseNbt(questId, ownerUuid);
        nbt.method_10582(DISPLAY_ITEM_ID_KEY, displayItemId == null ? "" : displayItemId);
        nbt.method_10582(TARGET_CASTLE_ID_KEY, targetCastleId == null ? "" : targetCastleId);
        nbt.method_10582(TARGET_CASTLE_NAME_KEY, targetCastleName == null ? "" : targetCastleName);
        nbt.method_10569(MODEL_VARIANT_KEY, ShipmentItem.modelVariant(displayItemId));
        class_9279.method_57453((class_9331)class_9334.field_49628, (class_1799)stack, (class_2487)nbt);
        stack.method_57379(class_9334.field_49637, (Object)new class_9280(ShipmentItem.modelVariant(displayItemId)));
        stack.method_57379(class_9334.field_49631, (Object)ShipmentItem.displayName(displayItemId));
        stack.method_57379(class_9334.field_49632, (Object)new class_9290(List.of(class_2561.method_43470((String)"Shipment Item:").method_27692(class_124.field_1080).method_27694(style -> style.method_10978(Boolean.valueOf(false))), class_2561.method_43470((String)("- " + (targetCastleName == null || targetCastleName.isBlank() ? "Unknown Castle" : targetCastleName))).method_27692(class_124.field_1068).method_27694(style -> style.method_10978(Boolean.valueOf(false))))));
        return stack;
    }

    public static boolean isShipment(class_1799 stack) {
        return stack != null && stack.method_31574(GotItems.SHIPMENT_ITEM);
    }

    private static class_2561 displayName(String displayItemId) {
        class_1792 item = ShipmentItem.itemFor(displayItemId);
        return item.method_7848().method_27661().method_27694(style -> style.method_10978(Boolean.valueOf(false)));
    }

    private static int modelVariant(String displayItemId) {
        class_1792 item = ShipmentItem.itemFor(displayItemId);
        if (item == class_1802.field_8713) {
            return 1;
        }
        if (item == class_1802.field_8695) {
            return 2;
        }
        return 0;
    }

    private static class_1792 itemFor(String displayItemId) {
        class_2960 id = class_2960.method_12829((String)displayItemId);
        if (id == null) {
            return class_1802.field_8620;
        }
        class_1792 item = (class_1792)class_7923.field_41178.method_10223(id);
        return item == null || item == class_1802.field_8162 ? class_1802.field_8620 : item;
    }
}

