/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_9290
 *  net.minecraft.class_9334
 */
package trep.got.item;

import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_9290;
import net.minecraft.class_9334;
import trep.got.item.QuestDeliveryItemData;
import trep.got.registry.GotItems;

public final class SealedLetterItem
extends class_1792 {
    public SealedLetterItem(class_1792.class_1793 settings) {
        super(settings);
    }

    public static class_1799 createStack(String questId, String ownerUuid, String targetCastleName, String lordName) {
        class_1799 stack = new class_1799((class_1935)GotItems.SEALED_LETTER);
        QuestDeliveryItemData.write(stack, questId, ownerUuid);
        stack.method_57379(class_9334.field_49631, (Object)class_2561.method_43470((String)"Sealed Letter").method_27694(style -> style.method_10978(Boolean.valueOf(false))));
        stack.method_57379(class_9334.field_49632, (Object)new class_9290(List.of(class_2561.method_43470((String)("Deliver to " + (targetCastleName == null || targetCastleName.isBlank() ? "Unknown Castle" : targetCastleName))).method_27692(class_124.field_1080).method_27694(style -> style.method_10978(Boolean.valueOf(false))), class_2561.method_43470((String)("Destined for " + (lordName == null || lordName.isBlank() ? "Unknown Lord" : lordName))).method_27692(class_124.field_1080).method_27694(style -> style.method_10978(Boolean.valueOf(false))))));
        return stack;
    }
}

