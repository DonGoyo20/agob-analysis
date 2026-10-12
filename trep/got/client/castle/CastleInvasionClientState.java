/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
 *  net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext
 *  net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents
 *  net.minecraft.class_1297
 *  net.minecraft.class_1921
 *  net.minecraft.class_238
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_761
 *  net.minecraft.class_9779
 */
package trep.got.client.castle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.class_1297;
import net.minecraft.class_1921;
import net.minecraft.class_238;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_761;
import net.minecraft.class_9779;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.network.CastleInvasionStatePayload;

@Environment(value=EnvType.CLIENT)
public final class CastleInvasionClientState {
    private static final double RENDER_TRACKING_RANGE_SQ = 65536.0;
    private static boolean active;
    private static String castleId;
    private static String objectiveText;

    private CastleInvasionClientState() {
    }

    public static void initialize() {
        WorldRenderEvents.AFTER_TRANSLUCENT.register(CastleInvasionClientState::render);
        HudRenderCallback.EVENT.register(CastleInvasionClientState::renderHud);
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> CastleInvasionClientState.clear());
    }

    public static void apply(CastleInvasionStatePayload payload, class_310 client) {
        client.execute(() -> {
            active = payload.active();
            castleId = payload.active() ? payload.castleId() : "";
            objectiveText = payload.active() ? payload.objectiveText() : "";
        });
    }

    public static void clear() {
        active = false;
        castleId = "";
        objectiveText = "";
    }

    public static boolean isActive() {
        return active;
    }

    public static String getCastleId() {
        return castleId;
    }

    private static void render(WorldRenderContext context) {
        class_310 client = class_310.method_1551();
        if (!active || castleId.isBlank() || client.field_1724 == null || client.field_1687 == null) {
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
            if (entity.method_5858((class_1297)client.field_1724) > 65536.0 || !(entity instanceof AbstractKingdomNpcEntity) || !(npc = (AbstractKingdomNpcEntity)entity).method_5805() || !castleId.equals(npc.got$getCastle())) continue;
            class_238 box = npc.method_5829().method_1009(0.08, 0.1, 0.08);
            class_761.method_22982((class_4587)matrices, (class_4588)lines, (class_238)box, (float)0.95f, (float)0.15f, (float)0.15f, (float)0.95f);
        }
        matrices.method_22909();
        consumers.method_22993();
    }

    private static void renderHud(class_332 context, class_9779 tickCounter) {
        class_310 client = class_310.method_1551();
        if (!active || objectiveText.isBlank() || client.field_1724 == null || client.field_1687 == null || client.field_1755 != null) {
            return;
        }
        context.method_25300(client.field_1772, "Objective: " + objectiveText, context.method_51421() / 2, 22, -1);
    }

    static {
        castleId = "";
        objectiveText = "";
    }
}

