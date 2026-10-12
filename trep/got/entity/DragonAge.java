/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity;

import java.util.List;
import java.util.Locale;
import org.jetbrains.annotations.Nullable;

public enum DragonAge {
    BABY("baby", 0.25f, false, "young", "hatchling"),
    ADOLESCENT("adolescent", 1.0f, true, "teen", "juvenile", "normal", "default"),
    ADULT("adult", 2.0f, true, "grown");

    public static final DragonAge DEFAULT;
    private final String id;
    private final float scale;
    private final boolean rideable;
    private final List<String> aliases;

    private DragonAge(String id, float scale, boolean rideable, String ... aliases) {
        this.id = id;
        this.scale = scale;
        this.rideable = rideable;
        this.aliases = List.of(aliases);
    }

    public String id() {
        return this.id;
    }

    public float scale() {
        return this.scale;
    }

    public boolean rideable() {
        return this.rideable;
    }

    public static DragonAge byId(@Nullable String id) {
        if (id == null || id.isBlank()) {
            return DEFAULT;
        }
        String normalized = id.trim().toLowerCase(Locale.ROOT);
        for (DragonAge age : DragonAge.values()) {
            if (!age.id.equals(normalized) && !age.aliases.contains(normalized)) continue;
            return age;
        }
        return DEFAULT;
    }

    static {
        DEFAULT = ADOLESCENT;
    }
}

