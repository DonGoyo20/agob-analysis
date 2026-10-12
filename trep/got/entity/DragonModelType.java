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
import trep.got.entity.dragon.DragonOrigin;

public enum DragonModelType {
    NORMAL("normal", DragonOrigin.WESTEROSI, "/assets/agob/models/entity/dragon_red.bbmodel", "default", "_default", "dragon_template_default"),
    SPIKY("spiky", DragonOrigin.WESTEROSI, "/assets/agob/models/entity/dragon_spiky.bbmodel", "spikes", "_spikes", "dragon_template_spikes"),
    LONGNECK("longneck", DragonOrigin.WESTEROSI, "/assets/agob/models/entity/dragon_longneck.bbmodel", "_longneck", "long_neck", "dragon_template_longneck"),
    VALYRIAN("valyrian", DragonOrigin.VALYRIAN, "/assets/agob/models/entity/dragon_template_valyria.bbmodel", "dragon_template_valyria");

    public static final DragonModelType DEFAULT;
    private final String id;
    private final DragonOrigin origin;
    private final String resourcePath;
    private final List<String> aliases;

    private DragonModelType(String id, DragonOrigin origin, String resourcePath, String ... aliases) {
        this.id = id;
        this.origin = origin;
        this.resourcePath = resourcePath;
        this.aliases = List.of(aliases);
    }

    public String id() {
        return this.id;
    }

    public DragonOrigin origin() {
        return this.origin;
    }

    public String resourcePath() {
        return this.resourcePath;
    }

    public static List<DragonModelType> spawnTypes(DragonOrigin origin) {
        DragonOrigin resolvedOrigin = origin == null ? DragonOrigin.DEFAULT : origin;
        return List.of(DragonModelType.values()).stream().filter(type -> type.origin == resolvedOrigin).toList();
    }

    public static DragonModelType byId(@Nullable String id) {
        if (id == null || id.isBlank()) {
            return DEFAULT;
        }
        String normalized = id.trim().toLowerCase(Locale.ROOT);
        for (DragonModelType type : DragonModelType.values()) {
            if (!type.id.equals(normalized) && !type.aliases.contains(normalized)) continue;
            return type;
        }
        return DEFAULT;
    }

    static {
        DEFAULT = NORMAL;
    }
}

