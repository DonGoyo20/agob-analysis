/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_5819
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity.dragon;

import java.util.List;
import java.util.Locale;
import net.minecraft.class_5819;
import org.jetbrains.annotations.Nullable;
import trep.got.entity.DragonModelType;

public enum DragonOrigin {
    WESTEROSI("westerosi"),
    VALYRIAN("valyrian");

    public static final DragonOrigin DEFAULT;
    private static final List<DragonOrigin> SPAWN_ORIGINS;
    private final String id;

    private DragonOrigin(String id) {
        this.id = id;
    }

    public String id() {
        return this.id;
    }

    public DragonModelType defaultModelType() {
        return switch (this.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> DragonModelType.NORMAL;
            case 1 -> DragonModelType.VALYRIAN;
        };
    }

    public static DragonOrigin random(class_5819 random) {
        return SPAWN_ORIGINS.get(random.method_43048(SPAWN_ORIGINS.size()));
    }

    public static DragonOrigin byId(@Nullable String id) {
        if (id == null || id.isBlank()) {
            return DEFAULT;
        }
        String normalized = id.trim().toLowerCase(Locale.ROOT);
        for (DragonOrigin origin : DragonOrigin.values()) {
            if (!origin.id.equals(normalized)) continue;
            return origin;
        }
        return DEFAULT;
    }

    static {
        DEFAULT = WESTEROSI;
        SPAWN_ORIGINS = List.of(DragonOrigin.values());
    }
}

