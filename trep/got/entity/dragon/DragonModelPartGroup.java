/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity.dragon;

import java.util.Locale;
import org.jetbrains.annotations.Nullable;

public enum DragonModelPartGroup {
    PLAYER("player", false),
    NECK("neck", true),
    WINGS("wings", true),
    LEGS("legs", true),
    TAIL("tail", true),
    TORSO("torso", true),
    STOMACH("stomach", true),
    SEAT("seat", false);

    private final String id;
    private final boolean modular;

    private DragonModelPartGroup(String id, boolean modular) {
        this.id = id;
        this.modular = modular;
    }

    public String id() {
        return this.id;
    }

    public boolean isModular() {
        return this.modular;
    }

    @Nullable
    public static DragonModelPartGroup byId(@Nullable String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        String normalized = id.trim().toLowerCase(Locale.ROOT);
        if ("wing".equals(normalized)) {
            return WINGS;
        }
        for (DragonModelPartGroup group : DragonModelPartGroup.values()) {
            if (!group.id.equals(normalized)) continue;
            return group;
        }
        return null;
    }
}

