/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1703
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.recipe;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import org.jetbrains.annotations.Nullable;
import trep.got.character.PlayerCharacterHolder;

public final class RuneCraftingContext {
    private static final ThreadLocal<class_1657> CURRENT_PLAYER = new ThreadLocal();
    private static final ThreadLocal<Boolean> CURRENT_ALTAR_CRAFTING = ThreadLocal.withInitial(() -> false);
    private static final Set<class_1703> ALTAR_HANDLERS = Collections.newSetFromMap(Collections.synchronizedMap(new WeakHashMap()));

    private RuneCraftingContext() {
    }

    public static void setCurrentPlayer(@Nullable class_1657 player) {
        if (player == null) {
            CURRENT_PLAYER.remove();
            return;
        }
        CURRENT_PLAYER.set(player);
    }

    public static void setCurrentAltarCrafting(boolean altarCrafting) {
        if (altarCrafting) {
            CURRENT_ALTAR_CRAFTING.set(true);
            return;
        }
        CURRENT_ALTAR_CRAFTING.remove();
    }

    public static boolean isCurrentAltarCrafting() {
        return Boolean.TRUE.equals(CURRENT_ALTAR_CRAFTING.get());
    }

    public static void markAltarHandler(class_1703 handler) {
        ALTAR_HANDLERS.add(handler);
    }

    public static boolean isAltarHandler(@Nullable class_1703 handler) {
        return handler != null && ALTAR_HANDLERS.contains(handler);
    }

    public static void clear() {
        CURRENT_PLAYER.remove();
        CURRENT_ALTAR_CRAFTING.remove();
    }

    @Nullable
    public static String getCurrentReligionId() {
        class_1657 player = CURRENT_PLAYER.get();
        if (!(player instanceof PlayerCharacterHolder)) {
            return null;
        }
        PlayerCharacterHolder holder = (PlayerCharacterHolder)player;
        String religionId = holder.got$getCharacterProfile().getSelectedReligionId();
        return religionId == null || religionId.isBlank() ? null : religionId;
    }
}

