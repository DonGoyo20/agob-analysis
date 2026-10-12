/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext
 *  net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents
 *  net.minecraft.class_1297
 *  net.minecraft.class_1921
 *  net.minecraft.class_238
 *  net.minecraft.class_310
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_761
 */
package trep.got.client.village;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.class_1297;
import net.minecraft.class_1921;
import net.minecraft.class_238;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_761;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.network.VillageRaidStatePayload;

@Environment(value=EnvType.CLIENT)
public final class VillageRaidClientState {
    private static boolean active;
    private static String villageId;

    private VillageRaidClientState() {
    }

    public static void initialize() {
        WorldRenderEvents.AFTER_TRANSLUCENT.register(VillageRaidClientState::render);
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> VillageRaidClientState.clear());
    }

    public static void apply(VillageRaidStatePayload payload, class_310 client) {
        client.execute(() -> {
            active = payload.active();
            villageId = payload.active() ? payload.villageId() : "";
        });
    }

    public static void clear() {
        active = false;
        villageId = "";
    }

    public static boolean isActive() {
        return active;
    }

    public static String getVillageId() {
        return villageId;
    }

    private static void render(WorldRenderContext context) {
        class_310 client = class_310.method_1551();
        if (!active || villageId.isBlank() || client.field_1687 == null) {
            return;
        }
        class_4587 matrices = context.matrixStack();
        if (matrices == null) {
            return;
        }
        class_4597.class_4598 consumers = client.method_22940().method_23000();
        class_4588 lines = consumers.getBuffer(class_1921.method_23594());
        matrices.method_22903();
        matrices.method_22904(-context.camera().method_19326().field_1352, -context.camera().method_19326().field_1351, -context.camera().method_19326().field_1350);
        for (class_1297 entity : client.field_1687.method_18112()) {
            AbstractKingdomNpcEntity npc;
            if (!(entity instanceof AbstractKingdomNpcEntity) || !(npc = (AbstractKingdomNpcEntity)entity).method_5805() || !villageId.equals(npc.got$getCastle())) continue;
            class_238 box = npc.method_5829().method_1009(0.08, 0.1, 0.08);
            class_761.method_22982((class_4587)matrices, (class_4588)lines, (class_238)box, (float)0.95f, (float)0.15f, (float)0.15f, (float)0.95f);
        }
        matrices.method_22909();
        consumers.method_22993();
    }

    static {
        villageId = "";
    }
}

