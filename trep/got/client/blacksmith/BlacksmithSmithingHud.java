/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_239
 *  net.minecraft.class_2680
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3965
 *  net.minecraft.class_9779
 */
package trep.got.client.blacksmith;

import java.util.HashSet;
import java.util.Set;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3965;
import net.minecraft.class_9779;
import trep.got.blacksmith.BlacksmithCraftSession;
import trep.got.blacksmith.BlacksmithCrafting;
import trep.got.blacksmith.BlacksmithWorkbenchAccess;
import trep.got.block.BlacksmithFurnaceBlock;
import trep.got.block.BlacksmithTableBlock;
import trep.got.item.BlacksmithHammerRules;

@Environment(value=EnvType.CLIENT)
public final class BlacksmithSmithingHud {
    private static final class_2960 BAR_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/experience/experience_smithing.png");
    private static final class_2960 INDICATOR_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/experience/indicator.png");
    private static final int BAR_WIDTH = 111;
    private static final int BAR_HEIGHT = 5;
    private static final int INDICATOR_WIDTH = 6;
    private static final int INDICATOR_HEIGHT = 9;
    private static final int SCALE = 3;
    private static final Set<class_2338> FINISHED_FURNACES = new HashSet<class_2338>();

    private BlacksmithSmithingHud() {
    }

    public static void initialize() {
        HudRenderCallback.EVENT.register(BlacksmithSmithingHud::renderHud);
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> FINISHED_FURNACES.clear());
    }

    public static boolean isFinished(class_2338 furnaceOrigin) {
        return FINISHED_FURNACES.contains(furnaceOrigin);
    }

    public static void markFinished(class_2338 furnaceOrigin, String reason) {
        FINISHED_FURNACES.add(furnaceOrigin);
    }

    private static void renderHud(class_332 context, class_9779 tickCounter) {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null || client.field_1687 == null || client.field_1755 != null) {
            return;
        }
        class_1799 mainHand = client.field_1724.method_6047();
        if (!BlacksmithHammerRules.isHammer(mainHand)) {
            return;
        }
        class_239 class_2392 = client.field_1765;
        if (!(class_2392 instanceof class_3965)) {
            return;
        }
        class_3965 hit = (class_3965)class_2392;
        class_2680 state = client.field_1687.method_8320(hit.method_17777());
        if (!(state.method_26204() instanceof BlacksmithFurnaceBlock) && !(state.method_26204() instanceof BlacksmithTableBlock)) {
            return;
        }
        BlacksmithWorkbenchAccess.WorkbenchPair pair = BlacksmithWorkbenchAccess.findPair((class_1937)client.field_1687, hit.method_17777(), state);
        if (pair == null) {
            return;
        }
        BlacksmithCraftSession session = BlacksmithCrafting.getSession((class_1937)client.field_1687, pair);
        if (session == null) {
            FINISHED_FURNACES.remove(pair.furnaceOrigin());
            return;
        }
        if (session.isComplete()) {
            BlacksmithSmithingHud.markFinished(pair.furnaceOrigin(), "client_complete");
            return;
        }
        if (FINISHED_FURNACES.contains(pair.furnaceOrigin())) {
            if (session.completedHits() == 0 && session.smithUuid().equals(client.field_1724.method_5667())) {
                FINISHED_FURNACES.remove(pair.furnaceOrigin());
            } else {
                return;
            }
        }
        if (!session.smithUuid().equals(client.field_1724.method_5667())) {
            return;
        }
        int drawWidth = 333;
        int drawHeight = 15;
        int x = (context.method_51421() - drawWidth) / 2;
        int y = context.method_51443() - 72;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(3.0f, 3.0f, 1.0f);
        context.method_25291(BAR_TEXTURE, 0, 0, 0, 0.0f, 0.0f, 111, 5, 111, 5);
        context.method_51448().method_22909();
        float delta = tickCounter.method_60637(false);
        float indicatorProgress = session.indicatorProgress(client.field_1687.method_8510(), delta);
        float indicatorX = (float)x + indicatorProgress * (float)(drawWidth - 18);
        float indicatorY = (float)y - (float)(27 - drawHeight) / 2.0f;
        context.method_51448().method_22903();
        context.method_51448().method_46416(indicatorX, indicatorY, 0.0f);
        context.method_51448().method_22905(3.0f, 3.0f, 1.0f);
        context.method_25291(INDICATOR_TEXTURE, 0, 0, 0, 0.0f, 0.0f, 6, 9, 6, 9);
        context.method_51448().method_22909();
        String progress = session.completedHits() + 1 + "/3";
        context.method_51433(client.field_1772, progress, x + drawWidth + 8, y - 2, -1515064, true);
    }
}

