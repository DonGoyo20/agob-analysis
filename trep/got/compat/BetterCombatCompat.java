/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.class_1309
 */
package trep.got.compat;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1309;

public final class BetterCombatCompat {
    private static final String BETTER_COMBAT_MOD_ID = "bettercombat";

    private BetterCombatCompat() {
    }

    public static boolean isLoaded() {
        return FabricLoader.getInstance().isModLoaded(BETTER_COMBAT_MOD_ID);
    }

    public static boolean shouldUseNativePlayerSpearControls(class_1309 entity) {
        return true;
    }
}

