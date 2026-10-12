/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
 *  net.minecraft.class_304
 *  net.minecraft.class_3675$class_307
 */
package trep.got.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_304;
import net.minecraft.class_3675;

@Environment(value=EnvType.CLIENT)
public final class GotKeyBindings {
    public static final String CATEGORY = "category.agob";
    public static final class_304 COMMANDER_MODE = GotKeyBindings.register("key.agob.commander_mode", 82);
    public static final class_304 COMMAND_CHARGE = GotKeyBindings.register("key.agob.command_charge", 86);
    public static final class_304 COMMAND_MOVE = GotKeyBindings.register("key.agob.command_move", 66);
    public static final class_304 COMMAND_FORMATION = GotKeyBindings.register("key.agob.command_formation", 78);
    public static final class_304 COMMAND_RECALL = GotKeyBindings.register("key.agob.command_recall", 77);
    public static final class_304 COMMAND_FOLLOW_ME = GotKeyBindings.register("key.agob.command_follow_me", 44);
    public static final class_304 COMMAND_HALT = GotKeyBindings.register("key.agob.command_halt", 46);
    public static final class_304 MAIN_MENU = GotKeyBindings.register("key.agob.main_menu", 74);
    public static final class_304 KINGDOM_SCREEN = GotKeyBindings.register("key.agob.kingdom_screen", 75);
    public static final class_304 MAP_TELEPORT = GotKeyBindings.register("key.agob.map_teleport", 72);
    public static final class_304 DRAGON_DRACARYS = GotKeyBindings.register("key.agob.dragon_dracarys", 45);

    private GotKeyBindings() {
    }

    public static void initialize() {
    }

    private static class_304 register(String translationKey, int keyCode) {
        return KeyBindingHelper.registerKeyBinding((class_304)new class_304(translationKey, class_3675.class_307.field_1668, keyCode, CATEGORY));
    }
}

