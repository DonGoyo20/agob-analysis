/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_1297
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_3675
 *  net.minecraft.class_3675$class_306
 *  net.minecraft.class_3675$class_307
 *  net.minecraft.class_746
 *  net.minecraft.class_8710
 *  org.lwjgl.glfw.GLFW
 */
package trep.got.client.dragon;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_1297;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import net.minecraft.class_746;
import net.minecraft.class_8710;
import org.lwjgl.glfw.GLFW;
import trep.got.client.registry.GotKeyBindings;
import trep.got.entity.DragonEntity;
import trep.got.mixin.client.KeyBindingAccessor;
import trep.got.network.RequestDragonFlightPayload;

@Environment(value=EnvType.CLIENT)
public final class DragonMountClientController {
    private static int clientTicks;
    private static boolean previousJumpPressed;
    private static int lastJumpPressTick;

    private DragonMountClientController() {
    }

    public static void initialize() {
        ClientTickEvents.END_CLIENT_TICK.register(DragonMountClientController::tick);
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> DragonMountClientController.reset());
    }

    private static void tick(class_310 client) {
        class_1297 class_12972;
        ++clientTicks;
        class_746 player = client.field_1724;
        if (player == null || !((class_12972 = player.method_5854()) instanceof DragonEntity)) {
            previousJumpPressed = false;
            return;
        }
        DragonEntity dragon = (DragonEntity)class_12972;
        if (player.method_31549().field_7479) {
            player.method_31549().field_7479 = false;
        }
        boolean jumpPressed = client.field_1690.field_1903.method_1434();
        boolean fireBreathPressed = DragonMountClientController.isHeld(client, GotKeyBindings.DRAGON_DRACARYS);
        float forwardInput = player.field_3913.field_3905;
        float sidewaysInput = player.field_3913.field_3907;
        dragon.setClientMountedFlightInput(jumpPressed, forwardInput, sidewaysInput);
        dragon.setClientLocalFireBreathHeld(fireBreathPressed);
        if (jumpPressed && !previousJumpPressed) {
            if (clientTicks - lastJumpPressTick <= 7) {
                ClientPlayNetworking.send((class_8710)new RequestDragonFlightPayload(true, jumpPressed, fireBreathPressed, forwardInput, sidewaysInput));
                lastJumpPressTick = Integer.MIN_VALUE;
            } else {
                lastJumpPressTick = clientTicks;
            }
        }
        ClientPlayNetworking.send((class_8710)new RequestDragonFlightPayload(false, jumpPressed, fireBreathPressed, forwardInput, sidewaysInput));
        previousJumpPressed = jumpPressed;
    }

    private static void reset() {
        clientTicks = 0;
        previousJumpPressed = false;
        lastJumpPressTick = Integer.MIN_VALUE;
    }

    private static boolean isHeld(class_310 client, class_304 binding) {
        if (binding.method_1415()) {
            return false;
        }
        class_3675.class_306 boundKey = ((KeyBindingAccessor)binding).agob$getBoundKey();
        long windowHandle = client.method_22683().method_4490();
        return switch (boundKey.method_1442()) {
            default -> throw new MatchException(null, null);
            case class_3675.class_307.field_1668 -> class_3675.method_15987((long)windowHandle, (int)boundKey.method_1444());
            case class_3675.class_307.field_1671 -> {
                if (GLFW.glfwGetKey((long)windowHandle, (int)boundKey.method_1444()) == 1) {
                    yield true;
                }
                yield false;
            }
            case class_3675.class_307.field_1672 -> GLFW.glfwGetMouseButton((long)windowHandle, (int)boundKey.method_1444()) == 1;
        };
    }

    static {
        lastJumpPressTick = Integer.MIN_VALUE;
    }
}

