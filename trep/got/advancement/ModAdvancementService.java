/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_1792
 *  net.minecraft.class_2960
 *  net.minecraft.class_3222
 *  net.minecraft.class_7923
 *  net.minecraft.class_8710
 */
package trep.got.advancement;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1792;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_7923;
import net.minecraft.class_8710;
import trep.got.advancement.ModAdvancementType;
import trep.got.network.ModAdvancementPayload;

public final class ModAdvancementService {
    private ModAdvancementService() {
    }

    public static void send(class_3222 player, ModAdvancementType type, String title, String description, class_1792 iconItem) {
        ModAdvancementService.send(player, type.getId(), title, description, iconItem, -11187, -1);
    }

    public static void send(class_3222 player, String typeId, String title, String description, class_1792 iconItem, int titleColor, int descriptionColor) {
        ModAdvancementService.send(player, typeId, title, description, iconItem, titleColor, descriptionColor, "", "");
    }

    public static void send(class_3222 player, String typeId, String title, String description, class_1792 iconItem, int titleColor, int descriptionColor, String topTextureId, String bottomTextureId) {
        class_2960 itemId = class_7923.field_41178.method_10221((Object)iconItem);
        ServerPlayNetworking.send((class_3222)player, (class_8710)new ModAdvancementPayload(typeId, title, description, itemId.toString(), titleColor, descriptionColor, topTextureId == null ? "" : topTextureId, bottomTextureId == null ? "" : bottomTextureId));
    }
}

