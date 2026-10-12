/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity.dragon;

import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import trep.got.entity.DragonModelType;
import trep.got.entity.dragon.DragonModelPartDefinition;
import trep.got.entity.dragon.DragonModelPartGroup;
import trep.got.entity.dragon.DragonOrigin;

public final class DragonModelPartRegistry {
    private static final Map<DragonModelPartGroup, Map<String, DragonModelPartDefinition>> PARTS = new EnumMap<DragonModelPartGroup, Map<String, DragonModelPartDefinition>>(DragonModelPartGroup.class);

    private DragonModelPartRegistry() {
    }

    private static void register(DragonModelPartGroup group, String id, DragonModelType sourceModelType, String ... aliases) {
        DragonModelPartDefinition definition = new DragonModelPartDefinition(id, group, sourceModelType);
        Map<String, DragonModelPartDefinition> groupParts = PARTS.get((Object)group);
        groupParts.put(DragonModelPartRegistry.normalize(id), definition);
        for (String alias : aliases) {
            groupParts.put(DragonModelPartRegistry.normalize(alias), definition);
        }
    }

    public static DragonModelPartDefinition defaultPart(DragonModelPartGroup group, DragonOrigin origin) {
        return switch (origin == null ? DragonOrigin.DEFAULT : origin) {
            default -> throw new MatchException(null, null);
            case DragonOrigin.WESTEROSI -> DragonModelPartRegistry.byId(group, "normal", DragonOrigin.WESTEROSI);
            case DragonOrigin.VALYRIAN -> DragonModelPartRegistry.byId(group, "valyrian", DragonOrigin.VALYRIAN);
        };
    }

    public static DragonModelPartDefinition byId(DragonModelPartGroup group, @Nullable String id, DragonOrigin origin) {
        if (group == null || !group.isModular()) {
            DragonOrigin resolvedOrigin = origin == null ? DragonOrigin.DEFAULT : origin;
            return new DragonModelPartDefinition(resolvedOrigin == DragonOrigin.VALYRIAN ? "valyrian" : "normal", group, resolvedOrigin.defaultModelType());
        }
        DragonOrigin resolvedOrigin = origin == null ? DragonOrigin.DEFAULT : origin;
        DragonModelPartDefinition definition = PARTS.get((Object)group).get(DragonModelPartRegistry.normalize(id));
        if (definition != null && definition.origin() == resolvedOrigin) {
            return definition;
        }
        return DragonModelPartRegistry.defaultPart(group, resolvedOrigin);
    }

    public static Collection<DragonModelPartDefinition> parts(DragonModelPartGroup group, DragonOrigin origin) {
        DragonOrigin resolvedOrigin = origin == null ? DragonOrigin.DEFAULT : origin;
        return List.copyOf(PARTS.get((Object)group).values().stream().distinct().filter(definition -> definition.origin() == resolvedOrigin).toList());
    }

    private static String normalize(@Nullable String id) {
        return id == null || id.isBlank() ? "normal" : id.trim().toLowerCase(Locale.ROOT);
    }

    static {
        for (DragonModelPartGroup group : DragonModelPartGroup.values()) {
            PARTS.put(group, new LinkedHashMap());
        }
        for (DragonModelPartGroup group : DragonModelPartGroup.values()) {
            if (!group.isModular()) continue;
            DragonModelPartRegistry.register(group, "normal", DragonModelType.NORMAL, "default", "regular");
            DragonModelPartRegistry.register(group, "spiky", DragonModelType.SPIKY, "spikes");
            DragonModelPartRegistry.register(group, "longneck", DragonModelType.LONGNECK, "_longneck", "long_neck");
            DragonModelPartRegistry.register(group, "valyrian", DragonModelType.VALYRIAN, "dragon_template_valyria");
        }
    }
}

