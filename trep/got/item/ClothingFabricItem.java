/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1271
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1792$class_9635
 *  net.minecraft.class_1799
 *  net.minecraft.class_1836
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_3222
 *  net.minecraft.class_9279
 *  net.minecraft.class_9290
 *  net.minecraft.class_9334
 */
package trep.got.item;

import java.util.List;
import java.util.Locale;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_9279;
import net.minecraft.class_9290;
import net.minecraft.class_9334;
import trep.got.registry.GotItems;
import trep.got.tailor.GotTailors;

public final class ClothingFabricItem
extends class_1792 {
    public static final String SLOT_KEY = "TailorSlot";
    public static final String CLOTHING_ID_KEY = "TailorClothing";

    public ClothingFabricItem(class_1792.class_1793 settings) {
        super(settings);
    }

    public static class_1799 createStack(String slot, String clothingId) {
        class_1799 stack = new class_1799((class_1935)GotItems.FABRIC);
        ClothingFabricItem.applyClothingData(stack, slot, clothingId);
        return stack;
    }

    public static void applyClothingData(class_1799 stack, String slot, String clothingId) {
        class_2487 nbt = ((class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302)).method_57461();
        nbt.method_10582(SLOT_KEY, ClothingFabricItem.sanitize(slot));
        nbt.method_10582(CLOTHING_ID_KEY, ClothingFabricItem.sanitize(clothingId));
        stack.method_57379(class_9334.field_49628, (Object)class_9279.method_57456((class_2487)nbt));
        stack.method_57379(class_9334.field_49632, (Object)new class_9290(List.of(ClothingFabricItem.loreLine(clothingId))));
    }

    public class_1271<class_1799> method_7836(class_1937 world, class_1657 user, class_1268 hand) {
        class_1799 stack = user.method_5998(hand);
        if (world.field_9236) {
            return class_1271.method_29237((Object)stack, (boolean)true);
        }
        if (!(user instanceof class_3222)) {
            return class_1271.method_22430((Object)stack);
        }
        class_3222 player = (class_3222)user;
        class_2487 nbt = ((class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302)).method_57461();
        String slot = ClothingFabricItem.readString(nbt, SLOT_KEY, "slot", "Slot");
        String clothingId = ClothingFabricItem.readString(nbt, CLOTHING_ID_KEY, "clothing", "Clothing", "clothing_id", "ClothingId", "ClothingName");
        if (slot.isBlank() || clothingId.isBlank()) {
            player.method_7353((class_2561)class_2561.method_43470((String)"This fabric has no clothing pattern."), true);
            return class_1271.method_29237((Object)stack, (boolean)false);
        }
        GotTailors.DiscoveryResult result = GotTailors.discoverFromFabric(player, slot, clothingId);
        if (result == GotTailors.DiscoveryResult.DISCOVERED) {
            if (!player.method_7337()) {
                stack.method_7934(1);
            }
            player.method_7353((class_2561)class_2561.method_43470((String)("Discovered " + ClothingFabricItem.displayName(clothingId) + ".")), true);
        } else if (result == GotTailors.DiscoveryResult.ALREADY_KNOWN) {
            player.method_7353((class_2561)class_2561.method_43470((String)"You already know this clothing pattern."), true);
        } else {
            player.method_7353((class_2561)class_2561.method_43470((String)"This fabric pattern cannot be used."), true);
        }
        return class_1271.method_29237((Object)stack, (boolean)false);
    }

    public void method_7851(class_1799 stack, class_1792.class_9635 context, List<class_2561> tooltip, class_1836 type) {
        if (stack.method_57826(class_9334.field_49632)) {
            return;
        }
        String clothingId = ClothingFabricItem.clothingId(stack);
        if (!clothingId.isBlank()) {
            tooltip.add(ClothingFabricItem.loreLine(clothingId));
        }
    }

    public static String clothingId(class_1799 stack) {
        class_2487 nbt = ((class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302)).method_57461();
        return ClothingFabricItem.readString(nbt, CLOTHING_ID_KEY, "clothing", "Clothing", "clothing_id", "ClothingId", "ClothingName");
    }

    private static class_2561 loreLine(String clothingId) {
        return class_2561.method_43470((String)ClothingFabricItem.displayName(clothingId)).method_27692(class_124.field_1080).method_27694(style -> style.method_10978(Boolean.valueOf(false)));
    }

    private static String displayName(String clothingId) {
        String value = ClothingFabricItem.sanitize(clothingId).replace('_', ' ');
        if (value.isBlank()) {
            return "";
        }
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

    private static String sanitize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT).replace("..", "");
    }

    private static String readString(class_2487 nbt, String ... keys) {
        for (String key : keys) {
            String value = ClothingFabricItem.sanitize(nbt.method_10558(key));
            if (value.isBlank()) continue;
            return value;
        }
        return "";
    }
}

